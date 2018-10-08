package com.valforma.projectag.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.valforma.projectag.model.SystemJobDetail;

public interface SystemJobDetailService {

	public void save(SystemJobDetail jobDetail);

	public SystemJobDetail update(SystemJobDetail jobDetail);

	public SystemJobDetail get(String id);

	public void startCron(ApplicationContext applicationContext);

	public List<SystemJobDetail> searchByCriteria(SystemJobDetail applicationProperties, int firstResult, int maxResult);
}