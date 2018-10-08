package com.valforma.projectag.defn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.valforma.projectag.defn.model.EmailTemplate;
import com.valforma.valformacommon.common.CommonUtil;
import com.valforma.valformacommon.dao.AbstractDAO;
@Repository
public class EmailTemplateDao extends AbstractDAO<EmailTemplate, EmailTemplate>{

	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<EmailTemplate> getClassType() {
		return EmailTemplate.class;
	}
	
	
	

	@Override
	protected Predicate[] getSearchPredicates(Root<EmailTemplate> root, EmailTemplate example) {
		List<Predicate> predicates= new ArrayList<>();
		if(CommonUtil.isNotBlank(example.getCode()))
		{
			Path<String> p=root.get("code");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getCode()));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	


}