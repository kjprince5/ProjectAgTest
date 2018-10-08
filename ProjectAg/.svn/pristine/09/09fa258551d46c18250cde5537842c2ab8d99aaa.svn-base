package com.valforma.projectag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.PermissionDao;
import com.valforma.projectag.model.Permission;
import com.valforma.projectag.vo.PermissionVo;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.AbstractPermissionServiceImpl;

@Service
@Transactional
public class PermissionServiceImpl extends AbstractPermissionServiceImpl<Permission, PermissionVo> implements PermissionService{

	@Autowired
	PermissionDao permissionDAO;

	@Override
	public AbstractDAO<Permission, PermissionVo> getDAO() {
		return permissionDAO;
	}

	public List<PermissionVo> getPermissions(String permission) {


		List<PermissionVo> permissionVo=permissionDAO.getPermissions(permission);
		return permissionVo;
	}

}
