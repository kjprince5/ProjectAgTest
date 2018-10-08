package com.valforma.projectag.oauth2.security;



import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.valforma.projectag.common.util.PasswordUtil;
import com.valforma.projectag.dao.UserDao;
import com.valforma.projectag.model.User;
import com.valforma.projectag.model.UserEvent;
import com.valforma.projectag.model.UserEvent.Action;
import com.valforma.projectag.service.UserEventService;
import com.valforma.projectag.vo.UserVo;
import com.valforma.valformacommon.common.CommonUtil;



public class UserAuthenticationProvider implements AuthenticationProvider {
 
  @Autowired
  UserDao userDao;
  
  @Autowired
  UserEventService userEventService;
  
 
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {     
    	UserVo user= new UserVo();
        if(CommonUtil.isBlank(authentication.getPrincipal().toString()))
        {

			throw new BadCredentialsException(
					"USERNAME_REQUIRED");
        }

        
       LinkedHashMap<String, Object> properties =  (LinkedHashMap<String, Object>) authentication.getDetails();
       
        user.setUserName(authentication.getPrincipal().toString());
        user.setClientId(new BigDecimal( properties.get("clientCode").toString()));
        System.out.println("The Autherities is "+authentication.getAuthorities().toString());
        System.out.println("The Name is "+authentication.getName().toString());
        System.out.println("The Details is "+authentication.getDetails().toString());
        System.out.println("The Client Id is "+properties.get("clientCode"));
        System.out.println("The Principal is "+authentication.getPrincipal().toString());
        if(CommonUtil.isBlank(authentication.getCredentials().toString()))
        {

			throw new BadCredentialsException(
					"PASSWORD_REQUIRED");
        }
        //user.setPassword( authentication.getCredentials().toString());
        List<User> users=userDao.getListByCriteria(user, -1, 0, null, false, null);
      
        String password="";
		try {
			password = PasswordUtil.getHash(100, authentication.getCredentials().toString(), users.get(0).getSalt());
		} catch (NoSuchAlgorithmException e) {
			// 
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// 
			e.printStackTrace();
		}
        	   
		  
        
        	   

        	   if (!(password.equals(users.get(0).getPassword()))) {

        	    throw new BadCredentialsException(
        	      "PASSWORD_NOT_CORRECT");
        	   }
        	   
        	   if (users.size()==0) {
        	       	 throw new BadCredentialsException("BAD_USER_CREDENTIALS");
        	       }
      
      
        else if(!users.get(0).isEmailConfirmed())
    	{
    	 throw new BadCredentialsException("EMAIL_NOT_CONFIRMED");
    	}
        else if(!users.get(0).isActive()){
			throw new BadCredentialsException("INACTIVE_USER");
		}
        else {

                List<GrantedAuthority> grantedAuthorities =
    new ArrayList<GrantedAuthority>();
             
               UserAuthenticationToken auth =
           new UserAuthenticationToken(users.get(0).getId(),
                authentication.getCredentials(), grantedAuthorities);
               auth.setAuthenticated(true);
               SecurityContextHolder.getContext().setAuthentication(auth);
               
               UserEvent userEvent= new UserEvent();
               userEvent.setCreationDate(new Date());
               userEvent.setCreatedBy(users.get(0).getId());
               userEvent.setLastUpdatedBy(users.get(0).getId());;
               userEvent.setLastUpdateDate(new Date());
               userEvent.setClientId(new BigInteger("1"));;
               userEvent.setAction(Action.LOGIN);
               userEvent.setUserId(users.get(0).getId());
               userEvent.setUserName(users.get(0).getUserName());
               userEvent.setFullName(users.get(0).getFirstName()+" "+users.get(0).getLastName() );
               userEventService.save(userEvent);
               
                return auth;
           
        }
    }
 
    public boolean supports(Class<?> arg0) {
        return true;
    }
}