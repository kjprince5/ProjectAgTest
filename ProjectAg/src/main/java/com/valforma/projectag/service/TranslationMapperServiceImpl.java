package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.TranslationMapperDAO;
import com.valforma.projectag.model.TranslationMapper;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class TranslationMapperServiceImpl extends GenericServiceImpl<TranslationMapper, TranslationMapper> implements TranslatioinMapperService {

	@Autowired
	TranslationMapperDAO translationMapperDao;
	
	@Override
	public AbstractDAO<TranslationMapper, TranslationMapper> getDAO() {
		// TODO Auto-generated method stub
		return translationMapperDao;
	}

}
