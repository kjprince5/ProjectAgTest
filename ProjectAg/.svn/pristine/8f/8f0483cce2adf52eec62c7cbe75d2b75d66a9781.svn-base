package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.ClientTranslationDAO;
import com.valforma.projectag.model.ClientTranslation;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class ClientTranslationServiceImpl extends GenericServiceImpl<ClientTranslation, ClientTranslation>
		implements ClientTranslationService {

	@Autowired
	ClientTranslationDAO stepTranslationDao;

	@Override
	public AbstractDAO<ClientTranslation, ClientTranslation> getDAO() {
		return stepTranslationDao;
	}

	@Override
	public boolean codeAndKeyValidation(ClientTranslation clientTranslation) {
		Long count = stepTranslationDao.getCountForCodeandKeyAsCombined(clientTranslation.getCode(),
				clientTranslation.getKey(), clientTranslation.getClientId());
		if (count == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean uniqueResolutionValidation(ClientTranslation clientTranslation) {
		Long count = stepTranslationDao.getCountForResolutionViaCode(clientTranslation.getCode(),
				clientTranslation.getClientId());
		if (count == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean codeAndKeyValidationExceptThis(ClientTranslation clientTranslation) {
		Long count = stepTranslationDao.getCountForCodeandKeyAsCombinedExceptThis(clientTranslation.getCode(),
				clientTranslation.getKey(), clientTranslation.getId(), clientTranslation.getClientId());
		if (count == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean uniqueResolutionValidationExceptThis(ClientTranslation clientTranslation) {
		Long count = stepTranslationDao.getCountForResolutionViaCodeExceptThis(clientTranslation.getCode(),
				clientTranslation.getId(), clientTranslation.getClientId());
		if (count == 0)
			return true;
		else
			return false;
	}

}
