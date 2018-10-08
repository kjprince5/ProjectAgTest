package com.valforma.projectag.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.UserDao;
import com.valforma.projectag.model.User;
import com.valforma.projectag.vo.UserVo;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.AbstractUserServiceImpl;

@Service
@Transactional
public class UserServiceImpl extends AbstractUserServiceImpl<User, UserVo> implements UserService{

	@Autowired
	UserDao userDAO;

	@Autowired
	DefaultTokenServices defaultTokenServices;

	@Override
	public AbstractDAO<User, UserVo> getDAO() {
		return userDAO;
	}

	@Override
	public UserVo getUserByEmailId(String email) {

		UserVo user= userDAO.getUserByEmailId(email);

		return user;
	}

	@Override
	public UserVo getUserByUsername(String userName) {

		UserVo user= userDAO.getUserByUsername(userName);

		return user;
	}
	@Override
	public boolean hasPermission(BigInteger userId, String permissionName){
		return userDAO.hasPermission(userId, permissionName);


	}

}
