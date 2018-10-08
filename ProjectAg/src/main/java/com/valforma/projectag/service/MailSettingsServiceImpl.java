package com.valforma.projectag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.MailSettingsDao;
import com.valforma.projectag.model.MailSettings;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.GenericServiceImpl;

@Service
@Transactional
public class MailSettingsServiceImpl extends GenericServiceImpl<MailSettings, MailSettings>
		implements MailSettingsService {

	@Autowired
	MailSettingsDao mailSettingsDao;

	@Override
	public AbstractDAO<MailSettings, MailSettings> getDAO() {
		return mailSettingsDao;
	}

}
