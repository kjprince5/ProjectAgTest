package com.valforma.projectag.service;

import java.math.BigInteger;

import com.valforma.projectag.model.User;
import com.valforma.projectag.vo.UserVo;
import com.valforma.valformacommon.service.AbstractUserService;

public interface UserService extends AbstractUserService<User, UserVo>{

	public UserVo getUserByEmailId(String email);

	public UserVo getUserByUsername(String userName);

	public boolean hasPermission(BigInteger userId, String functionName);
}
