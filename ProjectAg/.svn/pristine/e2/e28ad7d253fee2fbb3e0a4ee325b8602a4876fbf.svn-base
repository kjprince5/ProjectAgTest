package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.ClientSettingsDao;
import com.valforma.projectag.model.ClientSettings;
import com.valforma.projectag.vo.ClientSettingsVo;
import com.valforma.valformacommon.dao.AbstractDAO;

@Service
@Transactional
public class ClientSettingsServiceImpl extends AbstractSettingServiceImpl<ClientSettings, ClientSettingsVo> implements ClientSettingsService {

	@Autowired
	ClientSettingsDao adClientSettingsDao;

	@Override
	public AbstractDAO<ClientSettings, ClientSettingsVo> getDAO() {
		return adClientSettingsDao;
	}

	@Override
	protected ClientSettingsVo intantiatateVo() {
		return new ClientSettingsVo();
	}




}
