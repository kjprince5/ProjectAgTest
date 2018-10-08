package com.valforma.projectag.service;

import java.util.Date;
import java.util.List;

import com.valforma.projectag.model.IntegrationInstanceFailure;
import com.valforma.valformacommon.service.GenericService;

public interface IntegrationInstanceFailureService extends GenericService<com.valforma.projectag.model.IntegrationInstanceFailure, com.valforma.projectag.model.IntegrationInstanceFailure> {
	
	
	public List<IntegrationInstanceFailure> getIntegrationInstanceFailureWithJobDetail(IntegrationInstanceFailure criteria,Date startDate, Date endDate,String jobDetailIds);
}
 