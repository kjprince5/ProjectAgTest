package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.UserEventDao;
import com.valforma.projectag.model.UserEvent;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class UserEventServiceImpl extends
GenericServiceImpl<UserEvent, UserEvent> implements
UserEventService {

	@Autowired
	UserEventDao userEventDao;

	@Override
	public AbstractDAO<UserEvent, UserEvent> getDAO() {
		return userEventDao;
	}

}
