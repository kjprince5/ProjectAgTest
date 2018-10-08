package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.AsyncRequestDao;
import com.valforma.projectag.model.AsyncRequest;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class AsyncRequestServiceImpl extends GenericServiceImpl<AsyncRequest, AsyncRequest>
		implements AsyncRequestService {

	@Autowired
	AsyncRequestDao asyncRequestDao;

	@Override
	public void save(AsyncRequest asyncRequest) {
		asyncRequestDao.save(asyncRequest);
	}

	@Override
	public AsyncRequest update(AsyncRequest asyncRequest) {
		return asyncRequestDao.update(asyncRequest);

	}

	@Override
	public AbstractDAO<AsyncRequest, AsyncRequest> getDAO() {
		return asyncRequestDao;

	}

}
