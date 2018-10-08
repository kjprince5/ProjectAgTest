package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.StepUniqueRecordDAO;
import com.valforma.projectag.model.StepUniqueRecord;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class StepUniqueRecordServiceImpl extends GenericServiceImpl<StepUniqueRecord, StepUniqueRecord> implements StepUniqueRecordService {

	@Autowired
	StepUniqueRecordDAO stepUniqueRecordDAO;

	@Override
	public AbstractDAO<StepUniqueRecord, StepUniqueRecord> getDAO() {
		return stepUniqueRecordDAO;
	}

	

}
