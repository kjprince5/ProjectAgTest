package com.valforma.projectag.vo;

import java.math.BigInteger;

import com.valforma.projectag.model.Permission;

public class PermissionVo extends Permission{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

}
