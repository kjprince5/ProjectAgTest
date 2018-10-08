package com.valforma.projectag.rest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.common.util.PasswordUtil;
import com.valforma.projectag.model.User;
import com.valforma.projectag.service.ClientSettingsService;
import com.valforma.projectag.service.PermissionService;
import com.valforma.projectag.service.UserService;
import com.valforma.projectag.vo.PermissionVo;
import com.valforma.projectag.vo.UserVo;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.AbstractUserRest;
import com.valforma.valformacommon.service.GenericService;


@RestController
@RequestMapping("{clientCode}/users")
public class UserRest extends AbstractUserRest<User, UserVo>{
	
	@Autowired
	UserService userService;
	
	@Autowired
	PermissionService permissionService;

	@Autowired
	ClientSettingsService clientSettingsService;

//	@Autowired
//	MailBean mailBean;

	public UserRest() {
		super(User.class, UserVo.class);
	}

	@Override
	public GenericService<User, UserVo> getService() {
		return userService;
	}

	@Override
	public GenericService<User, UserVo> getUserService() {
		return userService;
	}

	@Override
	public String rolePrefix() {
		return "user";
	}
	
	@Override
	public boolean isUpdateAuthorized() {
		return false;
	}

	@Override
	public boolean isSaveAuthorized() {
		return false;
	}

	@Override
	public boolean isDeleteAuthorized() {
		return false;
	}

	/*@Override
	public AbstractObjectToEntityMapper<List<User>> getMapper() {
		return new UserMapper();
	}*/

	@RequestMapping(value = "/getUserByEmailId", method = RequestMethod.POST)
	public @ResponseBody UserVo getUserByEmailId(@RequestBody User user) {
		return userService.getUserByEmailId(user.getEmail());

	}

	@RequestMapping(value = "/getUserByUsername", method = RequestMethod.POST)
	public @ResponseBody UserVo getUserByUsername(@RequestBody User user) {
		return userService.getUserByUsername(user.getUserName());

	}

	@RequestMapping(value = "/getPermissions", method = RequestMethod.POST)
	public @ResponseBody List<PermissionVo> getPermissions(Principal principal) {
		return permissionService.getPermissions(principal.getName());

	}

	/*
	 * @RequestMapping(value = "/usersByFunction/{functionName}" ,method =
	 * RequestMethod.GET) public @ResponseBody List<User>
	 * getusersByFunction(@PathVariable("clientCode") String
	 * clientCode,@PathVariable("functionName") String functionName,@ModelAttribute
	 * UserVo t,@RequestParam(value= "firstResult", required=false) int
	 * firstResult, @RequestParam(value="maxResult", required=false) int maxResult){
	 * System.out.println(functionName); String fname = functionName;
	 * 
	 * return getService().getListByCriteria(t,
	 * "Select user1.first_name as Name ,user1.id as userId,P.PERMISSION_NAME from USER_ user1 LEFT JOIN USER_ROLE ur on  ur.USER_ID=user1.ID LEFT JOIN ROLE_PERMISSION RP on RP.ROLE_ID=ur.ROLE_ID LEFT JOIN PERMISSION P on P.ID=RP.PERMISSION_ID where P.PERMISSION_NAME=  "
	 * +"'"+functionName+"'" , " order by user1.ID desc", firstResult, maxResult,
	 * new UserByFunctionMapper());
	 * 
	 * } /*@Override
	 * 
	 * @RequestMapping(method = RequestMethod.GET) public @ResponseBody List<User>
	 * getListByCriteria(
	 * 
	 * @PathVariable("clientCode") String clientCode,
	 * 
	 * @ModelAttribute UserVo t,
	 * 
	 * @RequestParam(value = "firstResult", required = false) int firstResult,
	 * 
	 * @RequestParam(value = "maxResult", required = false) int maxResult,Principal
	 * principal) {
	 * 
	 * return getService() .getListByCriteria( t,
	 * "select user.ID ,user.FIRST_NAME,user.LAST_NAME,user.TYPE1,user.DOB,user.EMAIL,user.PASSWORD FROM USER_ user where 1=1 "
	 * , "order by ID DESC ", firstResult, maxResult, new UserMapper());
	 * 
	 * }
	 */
	/*
	 * @Override
	 * 
	 * @RequestMapping(method = RequestMethod.GET) public @ResponseBody List<User>
	 * getListByCriteria(@PathVariable("clientCode") String
	 * clientCode, @ModelAttribute UserVo t,
	 * 
	 * @RequestParam(value = "firstResult", required = false) int firstResult,
	 * 
	 * @RequestParam(value = "maxResult", required = false) int maxResult,Principal
	 * principal) {
	 * 
	 * String
	 * query="select user.ID ,user.FIRST_NAME,user.LAST_NAME,user.EMAIL FROM USER_ user where 1=1"
	 * ;
	 * 
	 * return getService().getListByCriteria(t, query, "order by user.ID DESC",
	 * firstResult, maxResult, new UserSearchMapper());
	 * 
	 * }
	 */

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Response<User> update(@PathVariable("clientCode") String clientCode, @RequestBody User t,
			Principal principal) {
		UserVo userVo = new UserVo();
		userVo.setEmail(t.getEmail());
		List<User> users = userService.getListByCriteria(userVo, -1, 0);

		if (t.getUpdatePassword() != null) {
			Map<String, String> userMap = null;
			try {
				userMap = PasswordUtil.enctyptPassword(t.getUpdatePassword(), null);
			} catch (NoSuchAlgorithmException e) {
				//
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				//
				e.printStackTrace();
			}
			t.setPassword(userMap.get("password"));
			t.setSalt(userMap.get("salt"));
		} else {
			t.setPassword(users.get(0).getPassword());
			t.setSalt(users.get(0).getSalt());
		}
		return super.update(clientCode, t, principal);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Response<User> save(@PathVariable("clientCode") String clientCode, @RequestBody User t,
			Principal principal) {

		Map<String, String> userMap = null;
		try {
			userMap = PasswordUtil.enctyptPassword(t.getPassword(), null);
		} catch (NoSuchAlgorithmException e) {
			//
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			//
			e.printStackTrace();
		}
		t.setPassword(userMap.get("password"));
		t.setSalt(userMap.get("salt"));
		t.setEmailConfirmed(true);

		return super.save(clientCode, t, principal);
	}

	// change password method
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public boolean changePassword(@RequestBody User user)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		User persistedUser = getService().get(user.getId());

		Map<String, String> userMap = null;
		try {
			userMap = PasswordUtil.enctyptPassword(user.getPassword(), null);
		} catch (NoSuchAlgorithmException e) {
			//
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			//
			e.printStackTrace();
		}
		persistedUser.setPassword(userMap.get("password"));
		persistedUser.setSalt(userMap.get("salt"));

		getService().update(persistedUser);
		return true;
	}

	/**
	 * Sends Forgot password validation Link To Your Email.
	 */
	/*@RequestMapping(value = "/sendForgotPasswordMail", method = RequestMethod.POST)
	public @ResponseBody Response<User> sendForgotPasswordMail(@RequestBody User user) {
		UUID oneTimeKey = UUID.randomUUID();
		User userDTO = getService().getEntityByColumnNameAndValue("email", user.getEmail());

		if (userDTO == null) {
			return new Response<User>(false, user);
		}
		userDTO.setOneTimeKey(oneTimeKey.toString());

		getService().update(userDTO);
		String subject = applicationPropertiesService
				.getEntityByColumnNameAndValue("key", "FORGOT_PASSWORD_EMAIL_SUBJECT").getValue();

		String body = applicationPropertiesService.getEntityByColumnNameAndValue("key", "FORGOT_PASSWORD_EMAIL_BODY")
				.getValue();
		String contextUrl = applicationPropertiesService.getEntityByColumnNameAndValue("key", "CONTEXT_URL").getValue();
		body = body.replace("{CONTEXT_URL}", contextUrl);
		body = body.replace("{ONE_TIME_KEY}", userDTO.getOneTimeKey());
		body = body.replace("{EMAIL}", userDTO.getEmail());

		mailBean.sendMail(
				applicationPropertiesService.getEntityByColumnNameAndValue("key", "FORGOT_PASSWORD_FROM_EMAIL")
						.getValue(),
				applicationPropertiesService.getEntityByColumnNameAndValue("key", "IESTATE_REPLY_EMAIL").getValue(),
				applicationPropertiesService.getEntityByColumnNameAndValue("key", "FORGOT_PASSWORD_FROM_NAME")
						.getValue(),
				user.getEmail(), body, subject, true);
		return new Response<User>(true, user);
	}*/

	// first we need to check email link is valid or not
	@RequestMapping(value = "/linkValid", method = RequestMethod.POST)
	public boolean linkValid(@RequestBody User user) {

		if (user.getOneTimeKey() != null && user.getEmail() != null && !user.getOneTimeKey().isEmpty()
				&& !user.getEmail().isEmpty()) {
			User userDTO = getService().getEntityByColumnNameAndValue("email", user.getEmail());

			if (user.getOneTimeKey().equals(userDTO.getOneTimeKey())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// after validating the email, reseting the password to user
	@RequestMapping(value = "/changeForgotPassword", method = RequestMethod.POST)
	public @ResponseBody Response<Boolean> changeForgotPassword(@RequestBody User user)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (linkValid(user)) {
			User persistedUser = getService().getEntityByColumnNameAndValue("email", user.getEmail());
			Map<String, String> userMap = null;
			try {
				userMap = PasswordUtil.enctyptPassword(user.getPassword(), null);
			} catch (NoSuchAlgorithmException e) {
				//
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				//
				e.printStackTrace();
			}
			persistedUser.setPassword(userMap.get("password"));
			persistedUser.setSalt(userMap.get("salt"));

			// persistedUser.setPassword(user.getPassword());
			persistedUser.setOneTimeKey(null);
			getService().update(persistedUser);
			return new Response<Boolean>(true, true);
		}
		return new Response<Boolean>(false, false);
	}

	/*@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getUsersWithPermissionSalesPerson", method = RequestMethod.GET)
	public @ResponseBody List<User> getusersByFunction(@PathVariable("clientCode") String clientCode,
			@ModelAttribute UserVo t, @RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult) {

		return getService().getListByCriteria(t,
				"Select u.first_name as Name ,u.LAST_NAME,u.id as userId,P.PERMISSION_NAME from USER_ u "
						+ "LEFT JOIN USER_ROLE_JOIN ur on  ur.USER_ID=u.ID "
						+ "LEFT JOIN ROLE_PERMISSION_JOIN RP on RP.ROLE_ID=ur.ROLE_ID "
						+ "LEFT JOIN PERMISSION P on P.ID=RP.PERMISSION_ID where P.PERMISSION_NAME= 'SALES_PERSON_NAME' ",
				"order by lower(u.first_name) asc", firstResult, maxResult, new UsersWithSalePersonPermissionMapper());
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getSalesPersonsList", method = RequestMethod.GET)
	public @ResponseBody List<User> getListByCriteria1(@PathVariable("clientCode") String clientCode,
			@ModelAttribute UserVo t, @RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult) {

		return getService().getListByCriteria(t,
				"select u.ID ,u.first_name from USER_ u where 1=1 AND u.ID NOT IN ( select empH.EMPLOYEE_ID from EMPLOYEE_HIERARCHY empH ) ",
				"order by lower(u.first_name) asc", firstResult, maxResult, new UserSalesPersonsListMapper());
	}*/

	/*
	 * @RequestMapping(value = "/getSalesPersonsList", method = RequestMethod.GET)
	 * public @ResponseBody List<User>
	 * getListByCriteria1(@PathVariable("clientCode")String
	 * clientCode, @ModelAttribute UserVo t,@RequestParam(value= "firstResult",
	 * required=false) int firstResult, @RequestParam(value="maxResult",
	 * required=false) int maxResult,Principal principal){
	 * 
	 * return getService().getListByCriteria(t,
	 * "select usersTable.ID from USER_ usersTable where 1=1 AND usersTable.ID NOT IN ( select empH.EMPLOYEE_ID from EMPLOYEE_HIERARCHY empH ) "
	 * , " order by usersTable.id desc", firstResult, maxResult, new UserMapper());
	 * 
	 * }
	 */

}
