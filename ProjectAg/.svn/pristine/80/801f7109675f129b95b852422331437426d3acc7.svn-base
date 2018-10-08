package com.valforma.projectag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.StepDAO;
import com.valforma.projectag.model.Step;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;


@Service
@Transactional
public class StepServiceImpl  extends GenericServiceImpl<Step, Step>implements StepService{

	@Autowired
	StepDAO stepDao;


	@Override
	public void save(Step step) {
		stepDao.save(step);	
	}


	@Override
	public Step update(Step step) {
		return stepDao.update(step);	

	}

	@Override
	public List<Step> getListByCriteria(Step step, int firstResult, int maxResult) {
		List<Step> steps= stepDao.getListByCriteria(step, -1, 0);
		return steps;
	}


	@Override
	public AbstractDAO<Step, Step> getDAO() {
		return stepDao;

	}

}
