package com.valforma.projectag.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.valforma.projectag.model.Client;
import com.valforma.projectag.model.ClientSettings;
import com.valforma.projectag.vo.ClientSettingsVo;

@Repository
public class ClientSettingsDao extends AbstractSettingsDao<ClientSettings, ClientSettingsVo>{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<ClientSettings> getClassType() {
		return ClientSettings.class;
	}

	
	
	
}
