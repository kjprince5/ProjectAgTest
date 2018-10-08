package com.valforma.projectag.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.ClientDao;
import com.valforma.projectag.model.Client;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class ClientServiceImpl extends GenericServiceImpl<Client, Client> implements ClientService {

	@Autowired
	ClientDao clientDao;

	@Override
	public AbstractDAO<Client, Client> getDAO() {
		return clientDao;
	}

	@Override
	public BigInteger getClientCodeById(String clientCode) {
		if(clientDao.getListByColumnNameAndValue("code", clientCode).size()>0)
		return clientDao.getListByColumnNameAndValue("code", clientCode).get(0).getId();
		else 
			return null;
				//getEntityByColumnNameAndValue("code", clientCode).getId();

	}

}
