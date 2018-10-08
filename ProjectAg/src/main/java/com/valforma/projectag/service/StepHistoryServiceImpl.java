package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.StepHistoryDao;
import com.valforma.projectag.model.StepHistory;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class StepHistoryServiceImpl
extends GenericServiceImpl<StepHistory, StepHistory> implements StepHistoryService { 

	@Autowired
	StepHistoryDao stepHistoryDao;

	@Override
	public AbstractDAO<StepHistory, StepHistory> getDAO() {
		return stepHistoryDao;

	}

}
