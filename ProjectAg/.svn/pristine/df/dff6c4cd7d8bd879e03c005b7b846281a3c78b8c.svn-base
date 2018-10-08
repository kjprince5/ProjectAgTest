package com.valforma.projectag.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*import com.valforma.projectag.mapper.ClientSettingsMapper;*/
import com.valforma.projectag.model.ClientSettings;
import com.valforma.projectag.service.ClientService;
import com.valforma.projectag.service.ClientSettingsService;
import com.valforma.projectag.vo.ClientSettingsVo;
import com.valforma.valformacommon.common.AbstractObjectToEntityMapper;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.AbstractMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.AbstractClientService;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/clientSettings")
public class ClientSettingsRest extends AbstractMultiTenantRoleBasedSecuredRest<ClientSettings, ClientSettingsVo> {
	
	@Autowired
	ClientSettingsService clientSettingsService;

	@Autowired
	ClientService clientService;

	@Override
	public GenericService<ClientSettings, ClientSettingsVo> getService() {
		return clientSettingsService;
	}

	@Override
	public GenericService<ClientSettings, ClientSettingsVo> getUserService() {
		return clientSettingsService;
	}

	@Override
	public String rolePrefix() {
		return "clientSettings";
	}

	@Override
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public  @ResponseBody Long count(@PathVariable("clientCode") String clientCode, @ModelAttribute ClientSettingsVo t,Principal principal){
		return getService()
				.getCount(t, "select count(client.ID) from CLIENT_SETTINGS ").longValue();
	}


	@Override
	@RequestMapping(method = RequestMethod.POST)
	public  @ResponseBody Response<ClientSettings> save(@PathVariable("clientCode")String clientCode, @RequestBody ClientSettings t,Principal principal){
		return super.save(clientCode,t,principal);
	}


	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public  @ResponseBody Response<ClientSettings> update(@PathVariable("clientCode")String clientCode, @RequestBody ClientSettings t,Principal principal){



		return super.update(clientCode,t,principal);
	}




	@Override
	public AbstractObjectToEntityMapper<List<ClientSettings>> getMapper() {
		return super.getMapper();
	}

	@Override
	public AbstractClientService<?, ?> getClientService() {
		return clientService;
	}



}
