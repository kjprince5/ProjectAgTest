package com.valforma.projectag.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.valforma.projectag.model.AsyncRequest;
import com.valforma.valformacommon.dao.AbstractDAO;

@Repository
public class AsyncRequestDao extends AbstractDAO<AsyncRequest, AsyncRequest>{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		
		return entityManager;
	}

	@Override
	public Class<AsyncRequest> getClassType() {
		
		return AsyncRequest.class;
	}
	
	@Override
	protected Predicate[] getSearchPredicates(Root<AsyncRequest> root, AsyncRequest example) {
	   return null;
	}
	
	
	
	
}
