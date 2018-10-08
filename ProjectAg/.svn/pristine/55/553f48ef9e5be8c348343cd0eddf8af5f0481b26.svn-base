package com.valforma.projectag.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.valforma.projectag.dao.IntegrationInstanceFailureDao;
import com.valforma.projectag.helper.TemplateParser;
import com.valforma.projectag.model.Client;
import com.valforma.projectag.model.IntegrationInstanceFailure;
import com.valforma.projectag.model.StepSettings;
import com.valforma.projectag.service.ClientService;
import com.valforma.projectag.service.ClientSettingsService;
import com.valforma.projectag.service.IntegrationInstanceFailureService;
import com.valforma.projectag.service.Job;
import com.valforma.projectag.service.StepSettingsService;

@Component
public class DuplicateRecordDeleteJob implements Job {

	@Autowired
	ClientService clientService;

	@Autowired
	ClientSettingsService clientSerService;

	@Autowired
	StepSettingsService stepSettingService;

	@Autowired
	IntegrationInstanceFailureService integrationInstanceFailureService;

	@Autowired 
	IntegrationInstanceFailureDao integrationInstanceFailureDao;

	@Autowired
	TemplateParser templateParser;

	@Override
	public void runJob() {
		Client clnt = new Client();
		try {
			List<Client> clientList = clientService.getListByCriteria(clnt, -1, 17);
			for (Client client : clientList) {
				StepSettings clientSettings = new StepSettings();
				clientSettings.setClientId(client.getId());
				clientSettings.setKey("FAILED_RECORD_DELETION_KEY");
				List<StepSettings> stepSettings = stepSettingService.getListByCriteria(clientSettings, -1, 0);
				for (StepSettings stepSetting : stepSettings) {
					IntegrationInstanceFailure integrationInstanceFailure = new IntegrationInstanceFailure();
					integrationInstanceFailure.setStepId(stepSetting.getStepId());
					integrationInstanceFailure.setErrorResponse(stepSetting.getValue());
					integrationInstanceFailure.setDone(false);

					List<IntegrationInstanceFailure> integrationInstanceFailures = integrationInstanceFailureDao
							.getIntegrationFailureList(integrationInstanceFailure);
					for (IntegrationInstanceFailure integrationInstanceFailure2 : integrationInstanceFailures) {
						integrationInstanceFailure2.setDone(true);
						integrationInstanceFailureService.update(integrationInstanceFailure2);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
