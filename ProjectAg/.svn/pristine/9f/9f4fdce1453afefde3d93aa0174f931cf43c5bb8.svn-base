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

import com.valforma.projectag.model.JobSettings;
import com.valforma.projectag.service.ClientService;
import com.valforma.projectag.service.JobSettingsService;
import com.valforma.valformacommon.common.AbstractObjectToEntityMapper;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.AbstractMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.AbstractClientService;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/jobSettings")
public class JobSettingsRest extends AbstractMultiTenantRoleBasedSecuredRest<JobSettings, JobSettings> {
	@Autowired
	JobSettingsService jobSettingsService;

	@Autowired
	ClientService clientService;

	@Override	
	public GenericService<JobSettings, JobSettings> getService() {
		return jobSettingsService;
	}

	@Override
	public GenericService<JobSettings, JobSettings> getUserService() {
		return jobSettingsService;
	}

	@Override
	public String rolePrefix() {
		return "jobSettings";
	}

	@RequestMapping(value = "/getJobSetting", method = RequestMethod.GET)
	public @ResponseBody Object getJobSetting(@PathVariable("clientCode") String clientCode, @ModelAttribute JobSettings t,
			@RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult, Principal principal) {
		return getService().getListByCriteria(t,"select js.ID, js.JOB_DETAIL_ID, js.VALUE, js.KEY_ from JOB_SETTINGS js where 1=1 " ,"order by js.ID desc", firstResult, maxResult);
	}

	@Override
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public  @ResponseBody Long count(@PathVariable("clientCode") String clientCode, 
			@ModelAttribute JobSettings t,Principal principal){
		return getService().getCount(t, "select count(client.ID) from CLIENT_SETTINGS ").longValue();
	}


	@Override
	@RequestMapping(method = RequestMethod.POST)
	public  @ResponseBody Response<JobSettings> save(@PathVariable("clientCode")String clientCode, 
			@RequestBody JobSettings t,Principal principal){
		return super.save(clientCode,t,principal);
	}


	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public  @ResponseBody Response<JobSettings> update(@PathVariable("clientCode")String clientCode, 
			@RequestBody JobSettings t,Principal principal){
		return super.update(clientCode,t,principal);
	}

	@Override
	public AbstractObjectToEntityMapper<List<JobSettings>> getMapper() {
		return super.getMapper();
	}	

	@Override
	public AbstractClientService<?, ?> getClientService() {
		return clientService;
	}


}

