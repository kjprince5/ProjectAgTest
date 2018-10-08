package com.valforma.projectag.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valforma.projectag.common.util.CommonMessages;
import com.valforma.projectag.model.ClientTranslation;
import com.valforma.projectag.model.TranslationMapper;
import com.valforma.projectag.service.ClientTranslationService;
import com.valforma.projectag.service.TranslatioinMapperService;
import com.valforma.valformacommon.common.NotAuthorizedException;
import com.valforma.valformacommon.common.Response;
import com.valforma.valformacommon.common.RestHelper;
import com.valforma.valformacommon.common.rest.GenericMultiTenantRoleBasedSecuredRest;
import com.valforma.valformacommon.service.GenericService;

@RestController
@RequestMapping(value = "{clientCode}/clientTranslation")
public class ClientTranslationRest
		extends GenericMultiTenantRoleBasedSecuredRest<ClientTranslation, ClientTranslation> {

	 public ClientTranslationRest() {
	super(ClientTranslation.class, ClientTranslation.class);
	}
	
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public List<ClientTranslation> getListByCriteria(@PathVariable("clientCode") String clientCode,
			@ModelAttribute ClientTranslation t, @RequestParam(value = "firstResult", required = false) int firstResult,
			@RequestParam(value = "maxResult", required = false) int maxResult, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		return super.getListByCriteria(clientCode, t, firstResult, maxResult, principal);
	}

	@Autowired
	ClientTranslationService clientTranslationService;

	@Autowired
	TranslatioinMapperService translatioinMapperService;

	@Autowired
	com.valforma.projectag.service.ClientService clientService;

	@Override
	public String rolePrefix() {
		return "clientTranslation";
	}

	@Override
	public GenericService<ClientTranslation, ClientTranslation> getService() {
		return clientTranslationService;
	}

	@Override
	public GenericService<ClientTranslation, ClientTranslation> getUserService() {
		return clientTranslationService;
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Response<ClientTranslation> save(@PathVariable("clientCode") String clientCode,
			@RequestBody ClientTranslation t, Principal principal) {
		
		super.validateAuthorization(clientCode, principal);
		
		t.setClientId(clientService.getClientCodeById(clientCode));
		RestHelper.setPrincipalDetails(t, principal);
		if (clientTranslationService.codeAndKeyValidation(t)) {
			if (t.isReciprocal()) {
				if (clientTranslationService.uniqueResolutionValidation(t)) {
					ClientTranslation clientTranslation = new ClientTranslation();
					clientTranslation.setKey(t.getValue());
					clientTranslation.setValue(t.getKey());
					clientTranslation.setKeyDescription(t.getValueDescription());
					clientTranslation.setValueDescription(t.getKeyDescription());
					clientTranslation.setReciprocal(false);
					clientTranslation.setClientId(clientService.getClientCodeById(clientCode));
					clientTranslation.setCode(translatioinMapperService
							.getEntityByColumnNameAndValue("code", t.getCode()).getReciprocalCode());
					save(clientCode, clientTranslation, principal);
					return super.save(clientCode, t, principal);
				}

				else {
					return new Response<ClientTranslation>(false, null, CommonMessages.RESOLUTION_VALIDATION);
				}
			} else {
				TranslationMapper translationMapper = translatioinMapperService.getEntityByColumnNameAndValue("code",
						t.getCode());
				if (translationMapper.getReciprocalCode() != null) {
					ClientTranslation criteria = new ClientTranslation();
					criteria.setCode(translationMapper.getReciprocalCode());
					criteria.setKey(t.getValue());
					java.util.List<ClientTranslation> clientTranslationObjectsToBeDeleted = clientTranslationService
							.getListByCriteria(criteria, -1, 0);
					for (ClientTranslation clientTranslation : clientTranslationObjectsToBeDeleted) {

						clientTranslationService.delete(clientTranslation.getId());
					}

				}

				return super.save(clientCode, t, principal);
			}
		} else {
			return new Response<ClientTranslation>(false, null, CommonMessages.KEY_CODE_VALIDATION_ERROR);
		}

	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Response<ClientTranslation> update(@PathVariable("clientCode") String clientCode,
			@RequestBody ClientTranslation t, Principal principal) {
		t.setClientId(clientService.getClientCodeById(clientCode));
		
		RestHelper.setPrincipalDetails(t, principal);
		super.validateAuthorization(clientCode, principal);
		if (clientTranslationService.codeAndKeyValidationExceptThis(t)) {
			if (t.isReciprocal()) {
				if (clientTranslationService.uniqueResolutionValidationExceptThis(t)) {
					ClientTranslation clientTranslation = new ClientTranslation();
					clientTranslation.setKey(t.getValue());
					clientTranslation.setValue(t.getKey());
					clientTranslation.setKeyDescription(t.getValueDescription());
					clientTranslation.setValueDescription(t.getKeyDescription());
					clientTranslation.setReciprocal(false);
					clientTranslation.setCode(translatioinMapperService
							.getEntityByColumnNameAndValue("code", t.getCode()).getReciprocalCode());
					save(clientCode, clientTranslation, principal);
					return super.update(clientCode, t, principal);
				}

				else {
					return new Response<ClientTranslation>(false, null, CommonMessages.RESOLUTION_VALIDATION);
				}
			} else {
				TranslationMapper translationMapper = translatioinMapperService.getEntityByColumnNameAndValue("code",
						t.getCode());
				if (translationMapper.getReciprocalCode() != null) {
					ClientTranslation criteria = new ClientTranslation();
					criteria.setCode(translationMapper.getReciprocalCode());
					criteria.setKey(t.getValue());
					java.util.List<ClientTranslation> clientTranslationObjectsToBeDeleted = clientTranslationService
							.getListByCriteria(criteria, -1, 0);
					for (ClientTranslation clientTranslation : clientTranslationObjectsToBeDeleted) {

						clientTranslationService.delete(clientTranslation.getId());
					}

				}

				return super.update(clientCode, t, principal);
			}
		} else {
			return new Response<ClientTranslation>(false, null, CommonMessages.KEY_CODE_VALIDATION_ERROR);
		}

	}

	@RequestMapping(value = "/deleteWithBigInteger", method = RequestMethod.POST)
	public @ResponseBody Response<ClientTranslation> delete(@PathVariable("clientCode") String clientCode,
			@RequestBody ClientTranslation t, Principal principal) {
		
		super.validateAuthorization(clientCode, principal);
		
		
		if (t.isReciprocal()) {
			TranslationMapper translationMapper = translatioinMapperService.getEntityByColumnNameAndValue("code",
					t.getCode());
			if (translationMapper.getReciprocalCode() != null) {
				ClientTranslation criteria = new ClientTranslation();
				criteria.setKey(t.getValue());
				criteria.setCode(translationMapper.getReciprocalCode());
				java.util.List<ClientTranslation> clientTranslationObjectsToBeDeleted = clientTranslationService
						.getListByCriteria(criteria, -1, 0);
				for (ClientTranslation clientTranslation : clientTranslationObjectsToBeDeleted) {

					clientTranslationService.delete(clientTranslation.getId());
				}

			}
		}
		clientTranslationService.delete(t.getId());
		return new Response<ClientTranslation>(true, null);
	}

}
