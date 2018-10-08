package com.valforma.projectag.service;

import java.math.BigInteger;

import com.valforma.projectag.model.AbstractSettings;
import com.valforma.valformacommon.service.GenericService;

public interface AbstractSettingService<T extends AbstractSettings, U extends AbstractSettings>
extends GenericService<T, U> {

	String getValue(BigInteger clientId, String key);

	boolean equal(BigInteger clientId, String key, String value);
}
