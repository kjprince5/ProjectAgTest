package com.valforma.projectag.common.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.valforma.valformacommon.common.Message;

@JsonSerialize(using=CommonMessageSerializer.class)
public enum CommonMessages implements Message{
	KEY_CODE_VALIDATION_ERROR("Combination of key and code already present please enter another key", Severity.ERROR), 
	RESOLUTION_VALIDATION("For one code there can be only one Reciprocal", Severity.ERROR),
	;

	String code;
    String message;
    Severity severity;
    String[] parameters;
   
     ResourceBundle resourceBundle;
	
	@Override
	public String getCode() {
		  return getModuleCode()+code;
	}

	@Override
	public String getMessage() {
		 if(getParameters()!=null &&getParameters().length==0)
         {
          return MessageFormat.format(message, (Object[]) getParameters());
         }
         else{
          return message;
         }
	}
	
	   private CommonMessages(String message, Severity severity, String... parameters)
       {
         code=this.toString();
         this.message=MessageFormat.format(message, (Object[]) getParameters());
         this.severity=severity;
         this.parameters=parameters;
       }
      
        private CommonMessages(ResourceBundle resourceBundle, String message, Severity severity, String... parameters)
       {
         code=this.toString();
         this.message=message;
         this.severity=severity;
         this.parameters=parameters;
         this.resourceBundle=resourceBundle;
       }
	public String getLocalizedMessage() {
        return resourceBundle.containsKey(getCode())?resourceBundle.getString(getCode()):getMessage();
      }
	
	@Override
	public String getMessage(ResourceBundle resourceBundle) {
		  return resourceBundle.containsKey(getCode())?resourceBundle.getString(getCode()):getMessage();
	}

	@Override
	public String[] getParameters() {
		 return parameters;
	}

	@Override
	public Severity getSeverity() {
		 return severity;
	}

	@Override
	public String getModuleCode() {
        return "KHCORE-0001-";
	}

	

}
