package com.valforma.projectag.rest;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.model.Permission;
import com.valforma.projectag.service.PermissionService;
import com.valforma.projectag.vo.PermissionVo;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.AbstractPermissionRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/permission")
public class PermissionRest extends AbstractPermissionRest<Permission, PermissionVo>{

	@Autowired
	PermissionService permissionService;

	@Override
	public GenericService<Permission, PermissionVo> getService() {

		return permissionService;
	}

	@Override
	public GenericService<Permission, PermissionVo> getUserService() {

		return permissionService;
	}

	@Override
	public String rolePrefix() {

		return "permission";
	}
	/*@RequestMapping(value = "/getDataNotInRolePermission/{roleId}" ,method = RequestMethod.GET)
	 public  @ResponseBody List<Permission> getusersByFunction(@PathVariable("clientCode") String clientCode,@PathVariable("roleId") String roleId,@ModelAttribute PermissionVo t,@RequestParam(value= "firstResult", required=false) int firstResult, @RequestParam(value="maxResult", required=false) int maxResult){
		return getService().getListByCriteria(t, "select permission.id,permission.permission_name from PERMISSION permission where permission.id not in(select permission_id from role_permission_join where role_id= "+"'"+roleId+"')" ,"order by permission.ID desc", firstResult, maxResult,new RolePermissionJoinMapper());
	}*/

	/*@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getDataNotInRolePermission/{roleId}/{orgId}" ,method = RequestMethod.GET)
	public  @ResponseBody List<Permission> getusersByFunction(@PathVariable("clientCode") String clientCode,@PathVariable("roleId") String roleId,@PathVariable("orgId") String orgId,@ModelAttribute PermissionVo t,@RequestParam(value= "firstResult", required=false) int firstResult, @RequestParam(value="maxResult", required=false) int maxResult){

		String query="select permission.id,permission.permission_name from PERMISSION permission where permission.id not in(select permission_id from role_permission_join where role_id= "+"'"+roleId+"' and org_id= "+"'"+orgId+"') " ;
		return getService().getListByCriteria(t, query," order by permission.ID desc", firstResult, maxResult,new PermissionMapper());
	}	
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	Response<Permission> update(
			@PathVariable("clientCode") String clientCode,
			@RequestBody Permission t,Principal principal) {

		return super.update(clientCode, t, principal);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody	
	Response<Permission> save(
			@PathVariable("clientCode") String clientCode,
			@RequestBody Permission t,Principal principal)  {

		return super.save(clientCode, t, principal);
	}



}

