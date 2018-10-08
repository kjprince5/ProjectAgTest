package com.valforma.projectag.service;

import com.valforma.projectag.model.ClientTranslation;
import com.valforma.valformacommon.service.GenericService;

public interface ClientTranslationService extends GenericService<ClientTranslation, ClientTranslation> {

	
	public boolean codeAndKeyValidation(ClientTranslation clientTranslation);
	public boolean codeAndKeyValidationExceptThis(ClientTranslation clientTranslation);
	public boolean uniqueResolutionValidation(ClientTranslation clientTranslation);
	public boolean uniqueResolutionValidationExceptThis(ClientTranslation clientTranslation);
}
