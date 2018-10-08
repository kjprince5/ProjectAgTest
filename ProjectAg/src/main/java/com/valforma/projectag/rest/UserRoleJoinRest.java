package com.valforma.projectag.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*import com.valforma.projectag.mapper.UserRoleJoinMapper;*/
import com.valforma.projectag.model.UserRoleJoin;
import com.valforma.projectag.service.UserRoleJoinService;
import com.valforma.projectag.vo.UserRoleJoinVo;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.AbstractUserRoleJoinRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/userRole")
public class UserRoleJoinRest extends AbstractUserRoleJoinRest<UserRoleJoin, UserRoleJoinVo>{


	@Autowired
	UserRoleJoinService userRoleService;

	@Override
	public GenericService<UserRoleJoin, UserRoleJoinVo> getService() {

		return userRoleService;
	}

	@Override
	public GenericService<UserRoleJoin, UserRoleJoinVo> getUserService() {

		return userRoleService;
	}

	@Override
	public String rolePrefix() {

		return "userRole";
	}

	/*@SuppressWarnings("deprecation")
	@Override
	@RequestMapping( method = RequestMethod.GET)
	public @ResponseBody List<UserRoleJoin> getListByCriteria(@PathVariable("clientCode") String clientCode,@ModelAttribute UserRoleJoinVo t,
			@RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult, Principal principal) {
		return getService().getListByCriteria(t, "SELECT userRole.id, userRole.USER_ID, userRole.ROLE_ID ,role.ROLE_NAME FROM USER_ROLE_JOIN userRole left join ROLE role on(role.ID=userRole.ROLE_ID)  where 1=1  ", "order by userRole.ID DESC ", firstResult, maxResult, new UserRoleJoinMapper());

	}*/

	@RequestMapping(value="/saveUserRole" ,method = RequestMethod.POST)
	public @ResponseBody Response<Boolean> save(@PathVariable("clientCode") String clientCode,@RequestBody UserRoleJoin userRoleJoin)
	{
		userRoleService.saveUserRole(userRoleJoin);
		return new Response<Boolean>(true, null);

	}



	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	Response<UserRoleJoin> update(
			@PathVariable("clientCode") String clientCode,
			@RequestBody UserRoleJoin t,Principal principal) {

		return super.update(clientCode, t, principal);
	}



}
