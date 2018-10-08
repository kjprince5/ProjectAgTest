package com.valforma.projectag.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.SystemJobDetailDao;
import com.valforma.projectag.model.SystemJobDetail;
import com.valforma.projectag.model.SystemJobDetail.Status;

@Service
@Transactional
public class SystemJobDetailServiceImpl implements SystemJobDetailService {

	@Autowired
	SystemJobDetailDao systemJobDetailDao;

	@Override
	public void save(SystemJobDetail systemJobDetail) {
		systemJobDetailDao.save(systemJobDetail);
	}


	@Override
	public SystemJobDetail update(SystemJobDetail systemJobDetail) {
		return systemJobDetailDao.update(systemJobDetail);
		
	}


	@Override
	public SystemJobDetail get(String id) {
		return systemJobDetailDao.getById(id);
	}

	@Override
	public List<SystemJobDetail> searchByCriteria(SystemJobDetail systemJobDetail, int firstResult, int maxResult) {
		List<SystemJobDetail> systemJobDetails = systemJobDetailDao.getListByCriteria(systemJobDetail, -1, 0);
		return systemJobDetails;
	}


	@Override
	public void startCron(ApplicationContext applicationContext) {
		SystemJobDetail jobDetail = new SystemJobDetail();
		jobDetail.setToDateTime(new Date());
		jobDetail.setStatus(SystemJobDetail.Status.STOPPED);

		List<SystemJobDetail> jobDetails = systemJobDetailDao.getListByCriteria(jobDetail, -1, 0);
		Iterator<SystemJobDetail> jobIterator = jobDetails.iterator();
		List<SystemJobDetail> workingJobDetails = new ArrayList<SystemJobDetail>();
		try {
			for (; jobIterator.hasNext();) {
				SystemJobDetail jobDetail2 = jobIterator.next();
				jobDetail2.setLastStartTime(new Date());
				jobDetail2.setStatus(Status.RUNNING);
				systemJobDetailDao.update(jobDetail2);
				workingJobDetails.add(jobDetail2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			;
			jobIterator.remove();
		}
		for (SystemJobDetail jobDetail2 : workingJobDetails) {
			Job job = (Job) applicationContext.getBean(jobDetail2.getJobName());
			job.runJob();
		}

		for (SystemJobDetail jobDetail2 : workingJobDetails) {
			jobDetail2.setLastEndTime(new Date());
			jobDetail2.setNextRunTime(new Date(new Date().getTime() + jobDetail2.getInterval() * 60 * 1000));
			jobDetail2.setStatus(Status.STOPPED);
			systemJobDetailDao.update(jobDetail2);
		}
		
	}

}
