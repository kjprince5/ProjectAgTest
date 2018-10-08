package com.valforma.projectag.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.model.RolePermissionJoin;
import com.valforma.projectag.service.RolePermissionJoinService;
import com.valforma.projectag.vo.RolePermissionJoinVo;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.AbstractRolePermissionJoinRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/rolePermission")
public class RolePermissionJoinRest extends AbstractRolePermissionJoinRest<RolePermissionJoin, RolePermissionJoinVo> {

	@Autowired
	RolePermissionJoinService rolePermissionJoinService;

	@Override
	public GenericService<RolePermissionJoin, RolePermissionJoinVo> getService() {

		return rolePermissionJoinService;
	}

	@Override
	public GenericService<RolePermissionJoin, RolePermissionJoinVo> getUserService() {

		return rolePermissionJoinService;
	}

	@Override
	public String rolePrefix() {

		return "rolePermission";
	}

	/*@SuppressWarnings("deprecation")
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<RolePermissionJoin> getListByCriteria(@PathVariable("clientCode") String clientCode,
			@ModelAttribute RolePermissionJoinVo t,
			@RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult, Principal principal) {

		return getService().getListByCriteria(t,
				"SELECT rolePermission.id, rolePermission.PERMISSION_ID, rolePermission.ROLE_ID ,permission.PERMISSION_NAME FROM ROLE_PERMISSION_JOIN rolePermission left join PERMISSION permission on(permission.ID=rolePermission.PERMISSION_ID)  where 1=1  ",
				"order by rolePermission.ID DESC ", firstResult, maxResult, new RolePermissionMapper());
		// .getListByCriteria(t, "SELECT rolePermission.id,
		// rolePermission.PERMISSION_ID, rolePermission.ROLE_ID
		// ,permission.PERMISSION_NAME FROM ROLE_PERMISSION_JOIN rolePermission left
		// join PERMISSION permission on(permission.ID=rolePermission.PERMISSION_ID)
		// where 1=1 ", "order by rolePermission.ID DESC ", firstResult, maxResult, new
		// RolePermissionMapper());

	}*/

	@RequestMapping(value = "/saveRolePermission", method = RequestMethod.POST)
	public @ResponseBody Response<Boolean> save(@PathVariable("clientCode") String clientCode,
			@RequestBody RolePermissionJoin rolePermissionJoin) {

		rolePermissionJoinService.saveRolePermission(rolePermissionJoin);
		return new Response<Boolean>(true, null);

	}

	/*@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getAllDeliveryUsers", method = RequestMethod.GET)
	public @ResponseBody List<RolePermissionJoin> getusersByFunction(@PathVariable("clientCode") String clientCode,
			@ModelAttribute RolePermissionJoinVo t,
			@RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult) {

		return getService().getListByCriteria(t,
				"select roleper.Role_id, usrRole.USER_ID, usr.FIRST_NAME, usr.LAST_NAME from PERMISSION per left join ROLE_PERMISSION_JOIN roleper on (roleper.PERMISSION_ID=per.ID) left join USER_ROLE_JOIN usrRole on (usrRole.ROLE_id=roleper.Role_id) left join USER_ usr on (usr.id=usrRole.user_ID) where per.PERMISSION_NAME='DELIVERY_USER'",
				"order by roleper.ID desc", firstResult, maxResult, new UserRoleMapperforDelivery());
	}*/

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Response<RolePermissionJoin> update(@PathVariable("clientCode") String clientCode,
			@RequestBody RolePermissionJoin t, Principal principal) {

		return super.update(clientCode, t, principal);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Response<RolePermissionJoin> save(@PathVariable("clientCode") String clientCode,
			@RequestBody RolePermissionJoin t, Principal principal) {

		return super.save(clientCode, t, principal);
	}

}
