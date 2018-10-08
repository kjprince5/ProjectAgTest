package com.valforma.projectag.helper;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.xml.ws.developer.WSBindingProvider;
import com.valforma.projectag.common.util.JsonUtil;
import com.valforma.projectag.contracts.ObjectHolder;
import com.valforma.projectag.dao.StepSettingsDao;
import com.valforma.projectag.model.JobDetail;
import com.valforma.projectag.model.Step;
import com.valforma.projectag.model.StepSettings;

@Component
public class SoapStepProcessor implements StepProcessor {

	@Autowired
	TemplateParser templateParser;
	
	@Autowired
	StepSettingsDao stepSettingDao;
	
	@Autowired
	NashornHelper nashornHelper;

	
	
	@Override
	public Object processStep(JobDetail jobDetail,Step step, ObjectHolder config, Integer level) throws Exception {
		if(step.getDryRunResponse()!=null && !step.getDryRunResponse().isEmpty())
		{
			return JsonUtil.parse(step.getDryRunResponse());
		}
		
		String targetNameSpace = "";
		String serviceNameForQname="";
		String portNameForQname="";
      
		/*System.out.println( config);*/
	/*	System.out.println(JsonUtil.toString( config));*/
		List<StepSettings> stepSettingsList= stepSettingDao.getListByColumnNameAndValue("stepId", step.getId());
		for (StepSettings stepSettings : stepSettingsList) {
			if(stepSettings.getKey().equals("SOAP_TARGET_NAME"))
				targetNameSpace=stepSettings.getValue();
			if(stepSettings.getKey().equals("SOAP_SERVICE_NAME"))
				serviceNameForQname=stepSettings.getValue();
			if(stepSettings.getKey().equals("SOAP_PORT_NAME"))
				portNameForQname=stepSettings.getValue();
		}
		
	
        QName serviceName = new QName(targetNameSpace, serviceNameForQname);
        QName portName = new QName(targetNameSpace, portNameForQname);
        String endpointUrl =  step.getPath();
        String SOAPAction = step.getSubType();
        String request =  templateParser.parse(step.getClientId().toString(),
				"step_unique_key_" + step.getId().toString(), step.getRequestTemplate(), config);
       System.out.println(request); 
        SOAPMessage response = invoke(serviceName, portName,endpointUrl, SOAPAction, request);
        SOAPBody body = response.getSOAPBody();
        if (body.hasFault()) {
            System.out.println(body.getFault());
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.writeTo(out);
            String outputBody = new String(out.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);
            System.out.println();
        	Object Json=XmlToJsonConverter.convert(outputBody);
          
            if (step.getResponseFormatterJs() != null && !step.getResponseFormatterJs().isEmpty())
            return nashornHelper.process(step.getResponseFormatterJs(), Json, Json);
			
            return Json;
        }
		return null;

	}

	public static void main(String[] args) throws Exception {
	
		String request="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
				"<Envelope xmlns=\"http://schemas.microsoft.com/dynamics/2011/01/documents/Message\">\r\n" + 
				"  <Header>\r\n" + 
				"    <MessageId>{d1e4853a-0311-1233-0008\r\n" + 
				"    -e84804976b33}</MessageId>\r\n" + 
				"    <LogonAsUser>Administrator</LogonAsUser>\r\n" + 
				"    <PartitionKey>PartitionKey1</PartitionKey>\r\n" + 
				"    <Company>USRT</Company>\r\n" + 
				"    <Action>http://schemas.microsoft.com/dynamics/\r\n" + 
				"    2008/01/services/TASalesOrder/find</Action>\r\n" + 
				"  </Header>\r\n" + 
				"  <Body>\r\n" + 
				"<MessageParts xmlns=\"http://schemas.microsoft.com/\r\n" + 
				"    dynamics/2011/01/documents/Message\">\r\n" + 
				"   <QueryCriteria xmlns = \"http://schemas.microsoft.com/\r\n" + 
				"     dynamics/2006/02/documents/QueryCriteria\">\r\n" + 
				"    <CriteriaElement>\r\n" + 
				"     <DataSourceName>SalesTable</DataSourceName>\r\n" + 
				"     <FieldName>SalesId</FieldName>\r\n" + 
				"     <Operator>Equal</Operator>\r\n" + 
				"     <Value1>012519</Value1>\r\n" + 
				"     <Value2/>\r\n" + 
				"    </CriteriaElement>\r\n" + 
				"   </QueryCriteria>\r\n" + 
				"  </MessageParts>\r\n" + 
				"  </Body>\r\n" + 
				"</Envelope>\r\n" + 
				"";
		String targetNameSpace = "http://tempuri.org/";
        QName serviceName = new QName(targetNameSpace, "TASalesOrder");
        QName portName = new QName(targetNameSpace, "NetTcpBinding_SalesOrderService");
        String endpointUrl = "http://dax2012:8101/DynamicsAx/Services/TASalesOrder";
        String SOAPAction = "http://schemas.microsoft.com/dynamics/2008/01/services/SalesOrderService/read";

        SOAPMessage response = invoke(serviceName, portName, endpointUrl, SOAPAction, request);
        SOAPBody body = response.getSOAPBody();
        if (body.hasFault()) {
            System.out.println(body.getFault());
        } else {
            BufferedOutputStream out = new BufferedOutputStream(System.out);
            response.writeTo(out);
            out.flush();
            System.out.println();
        }
	}

	public static void main1 (String[] args)  throws Exception 
	{
		
		String request ="<soapenv:Envelope xmlns:get=\"http://xmlns.oracle.com/apps/per/soaprovider/plsql/xxnrb_tprodnote_pkg/get_prodnote_details/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xxn=\"http://xmlns.oracle.com/apps/per/soaprovider/plsql/xxnrb_tprodnote_pkg/\">\r\n" + 
				"   <soapenv:Header>\r\n" + 
				"      <wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\r\n" + 
				"         <wsse:UsernameToken wsu:Id=\"UsernameToken-8BFEA1E6C39F621EAB15270728856261\">\r\n" + 
				"            <wsse:Username>NRB</wsse:Username>\r\n" + 
				"            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">EBS@2018</wsse:Password>\r\n" + 
				"            <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">h9kMO/1/wcLY9vkXgAS0Xw==</wsse:Nonce>\r\n" + 
				"            <wsu:Created>2018-05-23T10:54:45.621Z</wsu:Created>\r\n" + 
				"         </wsse:UsernameToken>\r\n" + 
				"      </wsse:Security>\r\n" + 
				"      <xxn:SOAHeader>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <xxn:Responsibility></xxn:Responsibility>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <xxn:RespApplication></xxn:RespApplication>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <xxn:SecurityGroup></xxn:SecurityGroup>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <xxn:NLSLanguage></xxn:NLSLanguage>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <xxn:Org_Id></xxn:Org_Id>\r\n" + 
				"      </xxn:SOAHeader>\r\n" + 
				"   </soapenv:Header>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <get:InputParameters>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <get:P_FROM_DATE>2018-04-20 10:0:00</get:P_FROM_DATE>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <get:P_TO_DATE>2018-05-26 18:58:31</get:P_TO_DATE>\r\n" + 
				"      </get:InputParameters>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		
		
		String targetNameSpace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/xxnrb_tprodnote_pkg/";
						
        QName serviceName = new QName(targetNameSpace, "XXNRB_TPRODNOTE_PKG_Service");
        QName portName = new QName(targetNameSpace, "XXNRB_TPRODNOTE_PKG_Port");
        String endpointUrl = "http://testappl1.nrb.co.in:8036/webservices/SOAProvider/plsql/xxnrb_tprodnote_pkg/";
        					
        String SOAPAction = "GET_PRODNOTE_DETAILS";

        SOAPMessage response = invoke(serviceName, portName, endpointUrl, SOAPAction, request);
        SOAPBody body = response.getSOAPBody();
        if (body.hasFault()) {
            System.out.println(body.getFault());
        } else {
            BufferedOutputStream out = new BufferedOutputStream(System.out);
            response.writeTo(out);
            
            out.flush();
            System.out.println();
        }
		/*bjectHolder obj=new ObjectHolder();
		
		TemplateParser templateParser= new TemplateParser();
		System.out.println(templateParser.parse("asfsdf1", "tempasdfasdf", request, obj));*/
		
		
	}
	
	public static SOAPMessage invoke(QName serviceName, QName portName, String endpointUrl, String soapActionUri,
			String data) throws Exception {
		Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointUrl);
		
	/*	 WebServiceFeature[] m_securityFeature =
                 new WebServiceFeature[] { new WebServiceFeature("oracle/wss_username_token_over_ssl_client_policy") };*/

		Dispatch dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);

		// The soapActionUri is set here. otherwise we get a error on .net based
		// services.
		dispatch.getRequestContext().put(Dispatch.SOAPACTION_USE_PROPERTY, true);
		dispatch.getRequestContext().put(Dispatch.SOAPACTION_URI_PROPERTY, soapActionUri);

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage message = messageFactory.createMessage();
		
		dispatch.getRequestContext().put(WSBindingProvider.USERNAME_PROPERTY,
                 "Magadmin");
		dispatch.getRequestContext().put(WSBindingProvider.PASSWORD_PROPERTY,
                 "P@gedown123");

		SOAPPart soapPart = message.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		SOAPBody body = envelope.getBody();

		StreamSource preppedMsgSrc = new StreamSource(new StringReader(data));
		soapPart.setContent(preppedMsgSrc);

		message.saveChanges();

		System.out.println(message.getSOAPBody().getFirstChild().getTextContent());
		SOAPMessage response = (SOAPMessage) dispatch.invoke(message);

		return response;
	}

}
