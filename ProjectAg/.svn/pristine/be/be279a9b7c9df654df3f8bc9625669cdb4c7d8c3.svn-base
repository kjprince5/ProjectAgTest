package com.valforma.projectag.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.model.StepSettings;
import com.valforma.projectag.service.ClientService;
import com.valforma.projectag.service.StepSettingsService;
import com.valforma.valformacommon.common.AbstractObjectToEntityMapper;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.AbstractMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.AbstractClientService;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/stepSettings")
public class StepSettingsRest extends AbstractMultiTenantRoleBasedSecuredRest<StepSettings, StepSettings> {
	@Autowired
	StepSettingsService stepSettingsService;

	@Autowired
	ClientService clientService;

	@Override
	public GenericService<StepSettings, StepSettings> getService() {
		return stepSettingsService;
	}

	@Override
	public GenericService<StepSettings, StepSettings> getUserService() {
		return stepSettingsService;
	}

	@Override
	public String rolePrefix() {
		return "stepSettings";
	}

	@RequestMapping(value = "/getStepSetting", method = RequestMethod.GET)
	public @ResponseBody Object getStepSetting(@PathVariable("clientCode") String clientCode,
			@ModelAttribute StepSettings t, @RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		return getService().getListByCriteria(t,
				"select stepSetting.ID, stepSetting.STEP_ID, stepSetting.VALUE, stepSetting.KEY_ from STEP_SETTINGS stepSetting where 1=1 ",
				"order by stepSetting.ID desc", firstResult, maxResult);
	}

	@Override
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long count(@PathVariable("clientCode") String clientCode, @ModelAttribute StepSettings t,
			Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		return getService().getCount(t, "select count(client.ID) from CLIENT_SETTINGS ").longValue();
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Response<StepSettings> save(@PathVariable("clientCode") String clientCode,
			@RequestBody StepSettings t, Principal principal) {
		return super.save(clientCode, t, principal);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Response<StepSettings> update(@PathVariable("clientCode") String clientCode,
			@RequestBody StepSettings t, Principal principal) {
		return super.update(clientCode, t, principal);
	}

	@Override
	public AbstractObjectToEntityMapper<List<StepSettings>> getMapper() {
		return super.getMapper();
	}

	@Override
	public AbstractClientService<?, ?> getClientService() {
		return clientService;
	}

}
