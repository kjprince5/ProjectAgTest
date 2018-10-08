package com.valforma.projectag.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.valforma.projectag.model.MailSettings;
import com.valforma.valformacommon.dao.AbstractDAO;

@Repository
public class MailSettingsDao extends AbstractDAO<MailSettings, MailSettings> {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<MailSettings> getClassType() {
		return MailSettings.class;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<MailSettings> root, MailSettings example) {
		List<Predicate> predicates = new ArrayList<>();
		if (example.getJobDetailId() != null) {
			Path<Long> p = root.get("jobDetailId");
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getJobDetailId()));
		}
		if (example.getClientId() != null) {
			Path<Long> p = root.get("clientId");
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getClientId()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public String getCriteriaForVo(MailSettings customerAccountVo) {
		String query = "";

		return query;
	}

}
