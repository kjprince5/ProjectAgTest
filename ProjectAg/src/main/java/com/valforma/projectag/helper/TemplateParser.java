package com.valforma.projectag.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valforma.projectag.model.IntegrationInstanceFailure;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class TemplateParser {
	Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

	public String parse(String clientCode, String templateName, String fillerObject, Object object) {

		try {
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			stringLoader.putTemplate(clientCode + "_" + templateName, fillerObject);
			cfg.setTemplateLoader(stringLoader);
			Template template = cfg.getTemplate(clientCode + "_" + templateName);
			StringWriter stringWriter = new StringWriter();
			template.process(object, stringWriter);
			stringWriter.flush();
			return stringWriter.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		
		
		
		/*
		 * ObjectHolder objectHolder= new ObjectHolder();
		 * objectHolder.getConfigs().put("0", new Config());
		 * 
		 * String[] array= {"1233444","442","3","10/17/2017","101","2"};
		 * String[] array2= {"1233444","442","3","10/17/2017","101","2"};
		 * List<String[]> list= new ArrayList<>(); list.add(array);
		 * list.add(array2); Map<String,Object> token= new LinkedHashMap<>();
		 * token.put("Token", true);
		 * objectHolder.getConfigs().get("0").getValues().put("1", token);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * String template=
		 * "{<#setting date_format=\"yyyy-MM-dd\">\"data[url]\":\"/payments/addMultiplePrimarySales\""
		 * + "<#list 0..configs['0'].values['1']?size-1 as i>" +
		 * ",\"data[postparams][Sales][${i}][Payment][warehouse_id]\":${configs['0'].values['1'][i][2]}"
		 * + ",\"data[postparams][Sales][${i}][Payment][mode]\":\"credit\"" +
		 * ",\"data[postparams][Sales][${i}][Payment][fordate]\":\"${configs['0'].values['1'][i][3]?datetime(\"MM/dd/yyyy\")?date}\""
		 * +
		 * ",\"data[postparams][Sales][${i}][Payment][billno]\":${configs['0'].values['1'][i][1]}"
		 * +
		 * ",\"data[postparams][Sales][${i}][Payment][invoiceid]\":${configs['0'].values['1'][i][0]}"
		 * +
		 * ",\"data[postparams][Sales][${i}][Paymentdetail][0][skunit_id]\":${configs['0'].values['1'][i][4]}"
		 * +
		 * ",\"data[postparams][Sales][${i}][Paymentdetail][0][quantity]\":${configs['0'].values['1'][i][5]}</#list>}"
		 * ; TemplateParser emailParser = new TemplateParser();
		 * System.out.println(emailParser.parse("one", "abc",
		 * "Y${Token?string}Y", token));
		 */
		
		List<IntegrationInstanceFailure> list = new ArrayList<>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("integrationInstanceFailureList", list);
		IntegrationInstanceFailure instanceFailure = new IntegrationInstanceFailure();
		instanceFailure.setStepId(BigInteger.ONE);
		instanceFailure.setErrorResponse("My Response");
		list.add(instanceFailure);
		
		ObjectMapper objectMapper= new ObjectMapper();
		
		try {
			

			TemplateParser templateParser= new TemplateParser();
				System.out
						.println(templateParser
						.parse("one",
								"two",
								"Hello There,\r\n       These are the failed jobs for yesterday.\r\n <table>\r\n <th>\r\n <td>\r\n Step Id\r\n </td>\r\n <td>\r\n Reason\r\n </td>\r\n </th>\r\n<#list 0..integrationInstanceFailureList?size-1 as i>\r\n<tr>\r\n <td>\r\n ${integrationInstanceFailureList[i].stepId}\r\n </td>\r\n <td>\r\n ${integrationInstanceFailureList[i].errorResponse}\r\n </td>\r\n </tr>\r\n</#list>\r\n</table>",
										root));
		
				Object map5=  	objectMapper.readValue("{  \r\n" + 
						"  \"endDate\":1530272010000,\r\n" + 
						"  \"integrationInstanceFailureList\":[  \r\n" + 
						"    [  \r\n" + 
						"      15558,\r\n" + 
						"      null,\r\n" + 
						"      \"java.lang.IllegalArgumentException: 'uriTemplate' must not be null\\r\\n\\tat org.springframework.util.Assert.hasText(Assert.java:162)\\r\\n\\tat org.springframework.web.util.UriTemplate$Parser.<init>(UriTemplate.java:179)\\r\\n\\tat org.springframework.web.util.UriTemplate$Parser.<init>(UriTemplate.java:172)\\r\\n\\tat org.springframework.web.util.UriTemplate.<init>(UriTemplate.java:65)\\r\\n\\tat org.springframework.web.client.RestTemplate.execute(RestTemplate.java:528)\\r\\n\\tat org.springframework.web.client.RestTemplate.exchange(RestTemplate.java:447)\\r\\n\\tat com.valforma.projectag.helper.RestStepProcessor.processStep(RestStepProcessor.java:263)\\r\\n\\tat com.valforma.projectag.helper.StepHelper.processStep(StepHelper.java:23)\\r\\n\\tat com.valforma.projectag.service.GenericCron.processStep(GenericCron.java:206)\\r\\n\\tat com.valforma.projectag.service.GenericCron.processJobDetails(GenericCron.java:135)\\r\\n\\tat com.valforma.projectag.service.GenericCron.startCron(GenericCron.java:68)\\r\\n\\tat com.valforma.projectag.service.GenericCron$$FastClassBySpringCGLIB$$edb293f8.invoke(<generated>)\\r\\n\\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\\r\\n\\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:649)\\r\\n\\tat com.valforma.projectag.service.GenericCron$$EnhancerBySpringCGLIB$$c4a20560.startCron(<generated>)\\r\\n\\tat sun.reflect.GeneratedMethodAccessor49.invoke(Unknown Source)\\r\\n\\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\r\\n\\tat java.lang.reflect.Method.invoke(Method.java:498)\\r\\n\\tat org.springframework.scheduling.support.ScheduledMethodRunnable.run(ScheduledMethodRunnable.java:65)\\r\\n\\tat org.springframework.scheduling.support.DelegatingErrorHandlingRunnable.run(DelegatingErrorHandlingRunnable.java:54)\\r\\n\\tat java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)\\r\\n\\tat java.util.concurrent.FutureTask.runAndReset(FutureTask.java:308)\\r\\n\\tat java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$301(ScheduledThreadPoolExecutor.java:180)\\r\\n\\tat java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:294)\\r\\n\\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\\r\\n\\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\\r\\n\\tat java.lang.Thread.run(Thread.java:748)\\r\\n\",\r\n" + 
						"      69,\r\n" + 
						"      null,\r\n" + 
						"      1530268470000,\r\n" + 
						"      1532000525000,\r\n" + 
						"      null,\r\n" + 
						"      3,\r\n" + 
						"      \"null\",\r\n" + 
						"      0,\r\n" + 
						"      null,\r\n" + 
						"      false,\r\n" + 
						"      34,\r\n" + 
						"      \"Bizom EAL Agro Product Master\"\r\n" + 
						"    ]\r\n" + 
						"  ],\r\n" + 
						"  \"startDate\":1530268409000\r\n" + 
						"}",LinkedHashMap.class);
	
		
		Object o =templateParser.parse("1", "aggd11","<#setting date_format=\"yyyy-MM-dd\"><?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
				"	<sales>\r\n" + 
				"	<sale>\r\n" + 
				"	<payment>\r\n" + 
				"	<orderid>${configs['1'].currentResponse.main.orderNumber}</orderid>\r\n" + 
				"	<invoiceid>${configs['1'].currentResponse.main.invoiceNumber}</invoiceid>\r\n" + 
				"	<outlet_erp_id>${configs['1'].currentResponse.main.outlet_erp_id}</outlet_erp_id>\r\n" + 
				"	<from_outlet_erp_id>${configs['1'].currentResponse.main.fromoutletid}</from_outlet_erp_id>\r\n" + 
				"	<mode>${configs['1'].currentResponse.main.mode}</mode>\r\n" + 
				"	\r\n" + 
				"	<#assign totalAmount=0>\r\n" + 
				"<#list 0..configs['1'].currentResponse.paymentDetails?size-1 as i>\r\n" + 
				"	<#assign uom=configs['1'].currentResponse.paymentDetails[i].unit>\r\n" + 
				"	<#assign code=configs['1'].currentResponse.paymentDetails[i].sapSkuCode?number?c >\r\n" + 
				"	<#assign weightOfSku=configs['0'].translatorMap['3'][code].Size.size_value?trim?number>\r\n" + 
				"	<#assign unitPrice=configs['1'].currentResponse.paymentDetails[i].unitpriceextax?replace(\"@#$\", \"\")?trim?number>\r\n" + 
				"<#if uom == 'MT'>\r\n" + 
				"	<#assign quantity=(configs['1'].currentResponse.paymentDetails[i].quantity?trim?number*1000/weightOfSku)>\r\n" + 
				"  <#assign totalAmount=totalAmount+(quantity*unitPrice)>\r\n" + 
				"</#if>\r\n" + 
				"<#if uom == 'KG'>\r\n" + 
				"<#assign quantity=(configs['1'].currentResponse.paymentDetails[i].quantity?trim?number*1000/weightOfSku)>\r\n" + 
				"  <#assign totalAmount=totalAmount+(quantity*unitPrice)>\r\n" + 
				"</#if>\r\n" + 
				"<#if uom == 'MTG'>\r\n" + 
				"<#assign quantity=((configs['1'].currentResponse.paymentDetails[i].quantity?trim?number/1000)*1000/weightOfSku)>\r\n" + 
				"  <#assign totalAmount=totalAmount+(quantity*unitPrice)>\r\n" + 
				"</#if>\r\n" + 
				"</#list>\r\n" + 
				"	<amount>${totalAmount?c}</amount>\r\n" + 
				"	<fordate>${configs['1'].currentResponse.main.billDate?datetime(\"dd.MM.yyyy\")?date}</fordate>\r\n" + 
				"	<partialfulfil></partialfulfil>  \r\n" + 
				"	<variableprecentdiscount></variableprecentdiscount> \r\n" + 
				"	</payment>\r\n" + 
				"	<#list 0..configs['1'].currentResponse.paymentDetails?size-1 as i>\r\n" + 
				"	<paymentdetail>\r\n" + 
				"	<skunit_erp_id>${configs['1'].currentResponse.paymentDetails[i].sapSkuCode?number?c}</skunit_erp_id>\r\n" + 
				"	<#assign uom=configs['1'].currentResponse.paymentDetails[i].unit>\r\n" + 
				"	<#assign code=configs['1'].currentResponse.paymentDetails[i].sapSkuCode?number?c >\r\n" + 
				"	<#assign weightOfSku=configs['0'].translatorMap['3'][code].Size.size_value?trim?number>\r\n" + 
				"	<#assign unitPrice=configs['1'].currentResponse.paymentDetails[i].unitpriceextax?replace(\"@#$\", \"\")?trim?number>\r\n" + 
				"<#if uom == 'MT'>\r\n" + 
				"	<#assign quantity=(configs['1'].currentResponse.paymentDetails[i].quantity?trim?number*1000/weightOfSku)>\r\n" + 
				"  <quantity>${quantity?c}</quantity>\r\n" + 
				"  <amount>${(quantity*unitPrice)?c}</amount>\r\n" + 
				"</#if>\r\n" + 
				"<#if uom == 'KG'>\r\n" + 
				"<#assign quantity=(configs['1'].currentResponse.paymentDetails[i].quantity?trim?number*1000/weightOfSku)>\r\n" + 
				" <quantity>${quantity?c}</quantity>\r\n" + 
				"  <amount>${(quantity*unitPrice)?c}</amount>\r\n" + 
				"</#if>\r\n" + 
				"<#if uom == 'MTG'>\r\n" + 
				"<#assign quantity=((configs['1'].currentResponse.paymentDetails[i].quantity?trim?number/1000)*1000/weightOfSku)>\r\n" + 
				" <quantity>${quantity?c}</quantity>\r\n" + 
				"  <amount>${(quantity*unitPrice)?c}</amount>\r\n" + 
				"</#if>\r\n" + 
				"	<status>purchased</status>\r\n" + 
				"	<#assign gst=((configs['1'].currentResponse.paymentDetails[i].cgstValue?replace(\"@#$\", \"\")?trim?number)+(configs['1'].currentResponse.paymentDetails[i].sgstValue?replace(\"@#$\", \"\")?trim?number)+(configs['1'].currentResponse.paymentDetails[i].igstValue?replace(\"@#$\", \"\")?trim?number))>\r\n" + 
				"	<gst>${gst?c}</gst>\r\n" + 
				"	<#assign cess=configs['1'].currentResponse.paymentDetails[i].cess>\r\n" + 
				"	<#if cess == ''>\r\n" + 
				"	<cess>0</cess>\r\n" + 
				"	<#else>\r\n" + 
				"	<cess>${configs['1'].currentResponse.paymentDetails[i].cess}</cess>\r\n" + 
				"	</#if>\r\n" + 
				"	<discountabsolute>${configs['1'].currentResponse.paymentDetails[i].discountperunit}</discountabsolute>\r\n" + 
				"	<unitPrice>${configs['1'].currentResponse.paymentDetails[i].unitpriceextax?replace(\"@#$\", \"\")}</unitPrice>\r\n" + 
				"	<batch_id>0</batch_id>\r\n" + 
				"	<schemediscountabsolute>${configs['1'].currentResponse.paymentDetails[i].schemediscountperunit}</schemediscountabsolute>\r\n" + 
				"	</paymentdetail>\r\n" + 
				"	</#list>\r\n" + 
				"	</sale>\r\n" + 
				"	</sales>\r\n" + 
				"",null);
		
		
		System.out.println(o);
		
	
		
	
		

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

}
