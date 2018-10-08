package com.valforma.projectag.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.valforma.projectag.model.SystemJobDetail;
import com.valforma.projectag.model.SystemJobDetail.IntervalType;
import com.valforma.projectag.model.SystemJobDetail.Status;

@Component
public class SystemGenericCron implements ApplicationContextAware {

	@Autowired
	SystemJobDetailService systemJobDetailService;

	ApplicationContext applicationContext;

	@Scheduled(fixedDelay = 1 * 60 * 1000)
	public void startCron() {
		try {
			System.out.println("Cron Started");
			SystemJobDetail systemJobDetail = new SystemJobDetail();
			systemJobDetail.setToDateTime(new Date());
			systemJobDetail.setStatus(SystemJobDetail.Status.STOPPED);
			List<SystemJobDetail> systemJobDetails = systemJobDetailService.searchByCriteria(systemJobDetail, -1, 0);
			Iterator<SystemJobDetail> systemJobIterator = systemJobDetails.iterator();
			List<SystemJobDetail> workingSystemJobDetails = new ArrayList<SystemJobDetail>();

			try {
				for (; systemJobIterator.hasNext();) {
					SystemJobDetail systemJobDetail2 = systemJobIterator.next();
					systemJobDetail2.setLastStartTime(new Date());
					systemJobDetail2.setStatus(Status.RUNNING);
					workingSystemJobDetails.add(systemJobDetailService.update(systemJobDetail2));
				}
			} catch (Exception e) {
				System.out.println("Exception in SystemGenericCron1");
				e.printStackTrace();
				;
				systemJobIterator.remove();
			}

			for (SystemJobDetail systemJobDetail2 : workingSystemJobDetails) {
				Job job = (Job) applicationContext.getBean(systemJobDetail2.getJobName());
				job.runJob();
			}

			for (SystemJobDetail systemJobDetail2 : workingSystemJobDetails) {
				systemJobDetail2.setLastEndTime(new Date());
				if (systemJobDetail2.getIntervalType() == IntervalType.INTERVAL_AFTER_LAST_FINISH) {
					systemJobDetail2.setNextRunTime(
							new Date(new Date().getTime() + systemJobDetail2.getInterval() * 60 * 1000));
				} else {
					systemJobDetail2.setNextRunTime(new Date(
							systemJobDetail2.getNextRunTime().getTime() + systemJobDetail2.getInterval() * 60 * 1000));
				}
				systemJobDetail2.setStatus(Status.STOPPED);
				systemJobDetailService.update(systemJobDetail2);
			}
		} catch (Exception e) {
			System.out.println("Exception in SystemGenericCron2");
			e.printStackTrace();
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}

}
