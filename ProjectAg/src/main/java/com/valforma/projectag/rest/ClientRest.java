package com.valforma.projectag.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.model.Client;
import com.valforma.projectag.service.ClientService;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.rest.GenericMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping("{clientCode}/client")
public class ClientRest extends GenericMultiTenantRoleBasedSecuredRest<Client, Client>{

	@Autowired
	ClientService clientService;
	
	public ClientRest() {
	super(Client.class, Client.class);
	}

	@Override
	public GenericService<Client, Client> getService() {
		return clientService;
	}

	@Override
	public GenericService<Client, Client> getUserService() {
		return clientService;
	}

	@Override
	public String rolePrefix() {
		return "client";
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public Response<Client> save(@PathVariable("clientCode") String clientCode, @RequestBody Client t, Principal principal) {
		return super.save(clientCode, t, principal);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public Response<Client> update(@PathVariable("clientCode") String clientCode,@RequestBody Client t, Principal principal) {
		return super.update(clientCode, t, principal);
	}
	
	
	
	
	
	
	
	
	
	
	

}
