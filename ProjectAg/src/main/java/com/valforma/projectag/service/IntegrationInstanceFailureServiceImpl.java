package com.valforma.projectag.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.IntegrationInstanceFailureDao;
import com.valforma.projectag.model.IntegrationInstanceFailure;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class IntegrationInstanceFailureServiceImpl
		extends GenericServiceImpl<IntegrationInstanceFailure, IntegrationInstanceFailure>
		implements IntegrationInstanceFailureService {

	@Autowired
	IntegrationInstanceFailureDao instanceFailureDao;

	@Override
	public AbstractDAO<IntegrationInstanceFailure, IntegrationInstanceFailure> getDAO() {
		return instanceFailureDao;

	}

	@Override
	public List<IntegrationInstanceFailure> getIntegrationInstanceFailureWithJobDetail(IntegrationInstanceFailure criteria,Date startDate, Date endDate,String jobDetailIds) {
		List<IntegrationInstanceFailure> list=	 (List<IntegrationInstanceFailure>) instanceFailureDao.getListByCriteria(criteria, "SELECT igFail.ID,igFail.CONFIG_OBJECT_JSON,\r\n" + 
				"igFail.ERROR_RESPONSE,igFail.STEP_ID,igFail.CREATED_BY,\r\n" + 
				"igFail.CREATION_DATE,igFail.LAST_UPDATE_DATE,igFail.LAST_UPDATED_BY,\r\n" + 
				"igFail.CLIENT_ID,igFail.NEXT_REQUEST_JSON,\r\n" + 
				"igFail.NO_OF_RETRIES,igFail.FAILURE_TEMPLATE_RESPONSE,\r\n" + 
				"igFail.DONE,igFail.JOB_DETAIL_ID, jobDetail.JOB_NAME \r\n" + 
				"FROM INTEGRATION_INSTANCE_FAILURE igFail \r\n" + 
				"INNER JOIN JOB_DETAIL jobDetail ON (jobDetail.ID = igFail.JOB_DETAIL_ID AND igFail.CREATION_DATE BETWEEN '"+startDate+"' AND '"+endDate+"') where igFail.DONE = FALSE AND igFail.JOB_DETAIL_ID IN ("+jobDetailIds+") ", "order by igFail.JOB_DETAIL_ID", -1, 0);
		
			return list;
	}

}
