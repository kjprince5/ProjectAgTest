package com.valforma.projectag.common.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.valforma.projectag.dao.UserDao;
import com.valforma.projectag.model.User;
import com.valforma.projectag.vo.UserVo;

public class UserAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserDao userDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserVo user = new UserVo();

		user.setEmail(authentication.getPrincipal().toString());
		// user.setPassword( authentication.getCredentials().toString());;
		List<User> users = userDao.getListByCriteria(user, -1, 0, null, false, null);
		if (users.size() == 0) {
			throw new BadCredentialsException("BAD_USER_CREDENTIALS");
		}

		else {

			return login(authentication, users.get(0));

		}

	}

	public Authentication login(Authentication authentication, User user) {
		try {

			String password = PasswordUtil.getHash(100, authentication.getCredentials().toString(), user.getSalt());

			if (!user.isActive()) {
				throw new BadCredentialsException("INACTIVE_USER");
			}

			if (!(password.equals(user.getPassword()))) {

				throw new BadCredentialsException("PASSWORD_NOT_CORRECT");
			} else {

				List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
				UserAuthenticationToken auth =

						new UserAuthenticationToken(user.getId(), authentication.getCredentials(), grantedAuthorities);

				return auth;

			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			//
			throw new BadCredentialsException("PASSWORD_NOT_CORRECT");
		}

	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
