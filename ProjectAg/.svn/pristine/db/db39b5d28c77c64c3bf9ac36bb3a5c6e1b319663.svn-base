package com.valforma.projectag.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.model.IntegrationInstanceFailure;
import com.valforma.projectag.service.ClientService;
import com.valforma.projectag.service.IntegrationInstanceFailureService;
import com.valforma.valformacommon.common.NotAuthorizedException;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.GenericMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/integrationInstanceFailureRest")
public class IntegrationInstanceFailureRest
		extends GenericMultiTenantRoleBasedSecuredRest<IntegrationInstanceFailure, IntegrationInstanceFailure> {
	
	public IntegrationInstanceFailureRest() {
	super(IntegrationInstanceFailure.class,IntegrationInstanceFailure.class);
	}

	@Autowired
	IntegrationInstanceFailureService integrationInstanceFailureService;

	@Autowired
	ClientService clientService;

	@Override
	public GenericService<IntegrationInstanceFailure, IntegrationInstanceFailure> getService() {
		return integrationInstanceFailureService;
	}

	@Override
	public GenericService<IntegrationInstanceFailure, IntegrationInstanceFailure> getUserService() {
		return integrationInstanceFailureService;
	}

	@Override
	public String rolePrefix() {
		return "IntegrationInstanceFailure";
	}

	@RequestMapping(value = "/getIntegrationFailure", method = RequestMethod.GET)
	public @ResponseBody Object getIntegrationFailure(@PathVariable("clientCode") String clientCode,
			@ModelAttribute IntegrationInstanceFailure t,
			@RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		super.validateAuthorization(clientCode, principal);
		return getService().getListByCriteria(t,
				"select igFail.ID, igFail.STEP_ID, igFail.FAILURE_TEMPLATE_RESPONSE, igFail.CONFIG_OBJECT_JSON, igFail.ERROR_RESPONSE, igFail.LAST_UPDATE_DATE, igFail.NEXT_REQUEST_JSON, igFail.NO_OF_RETRIES, igFail.JOB_DETAIL_ID from INTEGRATION_INSTANCE_FAILURE igFail left join STEP step on (step.ID = igFail.STEP_ID) where 1=1 AND igFail.done=0 ",
				"order by igFail.ID desc", firstResult, maxResult);
	}

	@Override
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long count(@PathVariable("clientCode") String clientCode,
			@ModelAttribute IntegrationInstanceFailure t, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		return getService()
				.getCount(t,
						"select count(igFail.ID) from INTEGRATION_INSTANCE_FAILURE igFail  left join STEP step on (step.ID = igFail.STEP_ID) where 1=1 AND igFail.done=0 ")
				.longValue();
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Response<IntegrationInstanceFailure> update(@PathVariable("clientCode") String clientCode,
			@RequestBody IntegrationInstanceFailure t, Principal principal) {
		return super.update(clientCode, t, principal);
	}

	// @RequestMapping(value = "/getIntegrationFailureByDate", method =
	// RequestMethod.GET)
	// public @ResponseBody Object
	// getIntegrationFailureByDate(@PathVariable("clientCode") String
	// clientCode,
	// @ModelAttribute IntegrationInstanceFailure t,
	// @RequestParam(value = "firstResult", required = false) int firstResult,
	// @RequestParam(value = "maxResult", required = false) int maxResult,
	// Principal principal) {
	// LocalDateTime localStartDate = LocalDateTime.of(2018, 01, 01, 0, 0, 0,
	// 0);
	// LocalDateTime localEndDate = LocalDateTime.of(2018, 02, 12, 23, 59, 59,
	// 999999999);
	// t.setClientId(BigInteger.valueOf(Long.valueOf(clientCode)));
	// return getService().getListByCriteria(t,
	// "select igFail.ID, igFail.STEP_ID, igFail.FAILURE_TEMPLATE_RESPONSE,
	// igFail.CONFIG_OBJECT_JSON, igFail.ERROR_RESPONSE,
	// igFail.LAST_UPDATE_DATE, igFail.NEXT_REQUEST_JSON, igFail.NO_OF_RETRIES
	// from INTEGRATION_INSTANCE_FAILURE igFail left join STEP step on (step.ID
	// = igFail.STEP_ID) where 1=1 AND igFail.done=0 AND ( igFail.CREATION_DATE
	// BETWEEN '"
	// + localStartDate.toString() + "' AND " + " '" + localEndDate.toString() +
	// "' ) ",
	// "order by igFail.ID desc", firstResult, maxResult);
	// }

}
