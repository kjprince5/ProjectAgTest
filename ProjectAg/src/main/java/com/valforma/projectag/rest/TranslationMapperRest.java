package com.valforma.projectag.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.model.TranslationMapper;
import com.valforma.projectag.service.TranslatioinMapperService;
import com.valforma.valformacommon.common.rest.GenericMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping(value="{clientCode}/translationMapper")
public class TranslationMapperRest extends GenericMultiTenantRoleBasedSecuredRest<TranslationMapper, TranslationMapper>{
	
	public TranslationMapperRest() {
	super(TranslationMapper.class,TranslationMapper.class);
	}

	@Autowired
	TranslatioinMapperService translationMapperService;
	
	@Override
	public GenericService<TranslationMapper, TranslationMapper> getService() {
		return translationMapperService;
	}

	@Override
	public GenericService<TranslationMapper, TranslationMapper> getUserService() {
		return translationMapperService;
	}

	@Override
	public String rolePrefix() {
		return "translationMapper";
	}

}
