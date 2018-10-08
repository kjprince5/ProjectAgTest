package com.valforma.projectag.rest;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.model.JobDetail;
import com.valforma.projectag.service.ClientService;
import com.valforma.projectag.service.JobDetailService;
import com.valforma.valformacommon.common.NotAuthorizedException;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.GenericMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/jobDetailRest")
public class JobDetailRest extends GenericMultiTenantRoleBasedSecuredRest<JobDetail, JobDetail> {

	public JobDetailRest() {
		super(JobDetail.class, JobDetail.class);
	}

	@Autowired
	JobDetailService jobDetailService;

	@Autowired
	ClientService clientService;

	@Override
	public GenericService<JobDetail, JobDetail> getService() {
		return jobDetailService;
	}

	@Override
	public GenericService<JobDetail, JobDetail> getUserService() {
		return jobDetailService;
	}

	@Override
	public String rolePrefix() {
		return "jobDetail";
	}

	@RequestMapping(value = "/getJobDetailEnums", method = RequestMethod.GET)
	public @ResponseBody Object getStepType(@PathVariable("clientCode") String clientCode, Principal principal) {
		Object[] objects = new Object[2];
		objects[0] = JobDetail.Status.values();
		objects[1] = JobDetail.IntervalType.values();
		return objects;
	}

	@RequestMapping(value = "/getJobDetail", method = RequestMethod.GET)
	public @ResponseBody Object getJobDetail(@PathVariable("clientCode") String clientCode, @ModelAttribute JobDetail t,
			@RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));

			super.validateAuthorization(clientCode, principal);
			return getService().getListByCriteria(t,
					"select jd.ID,jd.JOB_INTERVAL,jd.JOB_NAME,jd.LAST_END_TIME,jd.LAST_START_TIME,jd.NEXT_RUN_TIME,jd.JOB_STATUS,jd.VERSION,jd.INTERVAL_TYPE from JOB_DETAIL jd	where 1=1 ",
					"order by jd.ID desc", firstResult, maxResult);
		
			
	
		
		
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Response<JobDetail> update(@PathVariable("clientCode") String clientCode,
			@Valid @RequestBody JobDetail t, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		return super.update(clientCode, t, principal);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Response<JobDetail> save(@PathVariable("clientCode") String clientCode,
			@Valid @RequestBody JobDetail t, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		return super.save(clientCode, t, principal);
	}

	@Override
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long count(@PathVariable("clientCode") String clientCode, @ModelAttribute JobDetail t,
			Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		
			return getService().getCount(t, "select count(jd.ID) from JOB_DETAIL jd where 1=1 ").longValue();
		
		
	}



}
