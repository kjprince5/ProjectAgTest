package com.valforma.projectag.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NashornHelper {

	static List<String[]> list = new ArrayList<>();
	{
		list.add(new String[] { "1233444", "442", "3","fordate", "1", "2" });
		//list.add(new String[] { "1233444", "442", "3","fordate", "2", "2" });
		//list.add(new String[] { "1233445", "442", "3","fordate", "2", "2" });
	}
    static String  three="function converter(holder, object)\r\n" + 
    		"{\r\n" + 
    		"return holder.configs['1'].currentResponse;\r\n" + 
    		"}";
    static String javascript = "function converter(holder, myCode) {return  myCode.configs['0'].values['2'].Response.Result} ";
	static String javascript2="	function converter(holder, myCode) {\r\n" + 
			"	        var parentDict = {};\r\n" + 
			"	        var ArrayList = Java.type('java.util.ArrayList');\r\n" + 
			"	        for (i = 0; i < myCode.length; i++) {\r\n" + 
			"	            if (parentDict[myCode[i][0]] === undefined) {\r\n" + 
			"	                parentDict[myCode[i][0]] = [];\r\n" + 
			"	                parentDict[myCode[i][0]].main=myCode[i];\r\n" + 
			"	                parentDict[myCode[i][0]].skus=new ArrayList();\r\n" + 
			"	                \r\n" + 
			"	            }\r\n" + 
			"	            var skus = [];\r\n" + 
			"	            skus[0]=myCode[i][7];//sku\r\n" + 
			"	            skus[1]=myCode[i][8];// quantity\r\n" + 
			"	            skus[2]=myCode[i][11];// discount\r\n" + 
			"	            skus[3]=myCode[i][14];//amount\r\n" + 
			"	            skus[4]=myCode[i][10];//unit price\r\n" + 
			"	            parentDict[myCode[i][0]].skus.add(skus);\r\n" + 
			"	        }\r\n" + 
			"	        var object = new ArrayList();;\r\n" + 
			"	        for (var key in parentDict) {\r\n" + 
			"	            object.add(parentDict[key]);\r\n" + 
			"	        }\r\n" + 
			"	        return object;\r\n" + 
			"	    }";
	static String javascript3="	function converter(holder, myCode) {\r\n" + 
			"	        var parentDict = {};\r\n" + 
			"myCode= myCode['env:Body'][0].OutputParameters[0].P_PO_DETAILS_TBL[0].P_PO_DETAILS_TBL_ITEM;" +
			"	        var ArrayList = Java.type('java.util.ArrayList');\r\n" + 
			"	        for (i = 0; i < myCode.length; i++) {\r\n" + 
			"		        //if (myCode[i][28] != 'S1') {\r\n" + 
			"		            if (parentDict[myCode[i].PO_NUMBER] === undefined) {\r\n" + 
			"		                parentDict[myCode[i].PO_NUMBER] = [];\r\n" + 
			"		                parentDict[myCode[i].PO_NUMBER].main=myCode[i];\r\n" + 
			"		                parentDict[myCode[i].PO_NUMBER].itemList=new ArrayList();\r\n" + 
			"		                \r\n" + 
			"		            }\r\n" + 
			"		            var item = new ArrayList();\r\n" + 
			"		            item.add(myCode[i].CONTACT);\r\n" + 
			"		            parentDict[myCode[i].PO_NUMBER].itemList.add(item);\r\n" + 
			"		        //}\r\n" + 
			"	        }\r\n" + 
			"	        var object = new ArrayList();;\r\n" + 
			"	        for (var key in parentDict) {\r\n" + 
			"		            object.add(parentDict[key]);\r\n" + 
			"	        }\r\n" + 
			"	        return object;\r\n" + 
			"	    }\r\n" + 
			"	    ";

	static String javascript4="	function converter(holder, myCode) {\r\n" + 
			"	        var parentDict = {};\r\n" + 
			"	        \r\n" + 
			"	        if( myCode['env:Body'][0].OutputParameters[0].P_PRDNOTE_DETAILS_TBL.size()==0)\r\n" + 
			"	        return null;\r\n" + 
			"	                    \r\n" + 
			"	   myCode=    myCode['env:Body'][0].OutputParameters[0].P_PRDNOTE_DETAILS_TBL[0].P_PRDNOTE_DETAILS_TBL_ITEM;\r\n" + 
			"	        var ArrayList = Java.type('java.util.ArrayList');\r\n" + 
			"	        for (i = 0; i < myCode.length; i++) {\r\n" + 
			"		        \r\n" + 
			"		            if (parentDict[myCode[i].JOB_NAME[0]] === undefined) {\r\n" + 
			"		                parentDict[myCode[i].JOB_NAME[0]] = [];\r\n" + 
			"		                var headerObject={};\r\n" + 
			"						headerObject[\"BUSINESS_UNIT\"]=myCode[i].BUSINESS_UNIT;\r\n" + 
			"						headerObject[\"JOB_NAME\"]=myCode[i].JOB_NAME;\r\n" + 
			"						headerObject[\"DATE_LAST_MOVED\"]=myCode[i].DATE_LAST_MOVED;\r\n" + 
			"		                parentDict[myCode[i].JOB_NAME[0]].header=headerObject;\r\n" + 
			"		                parentDict[myCode[i].JOB_NAME[0]].itemList=new ArrayList();\r\n" + 
			"		                \r\n" + 
			"		            }\r\n" + 
			"		            var item = new ArrayList();\r\n" + 
			"		            var obj={};\r\n" + 
			"		            obj[\"BUSINESS_UNIT\"]=myCode[i].BUSINESS_UNIT;\r\n" + 
			"					obj[\"ORGANIZATION_ID\"]=myCode[i].ORGANIZATION_ID;\r\n" + 
			"					obj[\"WIP_ENTITY_ID\"]=myCode[i].WIP_ENTITY_ID;\r\n" + 
			"					obj[\"ITEM_CODE\"]=myCode[i].ITEM_CODE;\r\n" + 
			"					obj[\"DESCRIPTION\"]=myCode[i].DESCRIPTION;\r\n" + 
			"					obj[\"DATE_LAST_MOVED\"]=myCode[i].DATE_LAST_MOVED;\r\n" + 
			"					obj[\"OPERATION_SEQ_NUM\"]=myCode[i].OPERATION_SEQ_NUM;\r\n" + 
			"					obj[\"AVAILABLE_QUANTITY\"]=myCode[i].AVAILABLE_QUANTITY;\r\n" + 
			"					\r\n" + 
			"							\r\n" + 
			"		            \r\n" + 
			"		            item.add(obj);\r\n" + 
			"		           \r\n" + 
			"		            parentDict[myCode[i].JOB_NAME[0]].itemList.add(item);\r\n" + 
			"		     \r\n" + 
			"	        }\r\n" + 
			"	        var object = new ArrayList();;\r\n" + 
			"	        for (var key in parentDict) {\r\n" + 
			"		            object.add(parentDict[key]);\r\n" + 
			"	        }\r\n" + 
			"	        return object;\r\n" + 
			"	    }\r\n" + 
			"	    \r\n" + 
			"	    \r\n" + 
			"\r\n" + 
			"";
	static String javascript5="function converter(holder, myCode) {\r\n" + 
			"				var error= \"\";\r\n" + 
			"				for(var i =0; i <myCode.configs['1'].currentResponse.paymentDetails.length;i++ )\r\n" + 
			"				{\r\n" + 
			"				var code = Number(myCode.configs['1'].currentResponse.paymentDetails[i].sapSkuCode).toString();\r\n" + 
			"	        if(myCode.configs['0'].translatorMap['3'][code]== undefined || myCode.configs['0'].translatorMap['3'][code]==null )\r\n" + 
			"	        {\r\n" + 
			"	        error = error+\" \"+code;\r\n" + 
			"	        }\r\n" + 
			"			}\r\n" + 
			"				if(error!=\"\")\r\n" + 
			"				{\r\n" + 
			"				error = \"There are no sku details for the following sku codes in bizom :\"+ error;\r\n" + 
			"				}\r\n" + 
			"	        return error;\r\n" + 
			"	    }";
	
	Object process(String code, Object configObject, Object object) {
		ScriptEngineManager engineManager = new ScriptEngineManager(null);
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Bindings bindings = new SimpleBindings();
		bindings.put("context", object);
		engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
		Object result = null;
		try {
			engine.eval(code);
			result = ((Invocable) engine).invokeFunction("converter", configObject,object);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		/*System.out.println(result);*/
		return result;
	}

	public static void main(String[] args) throws IOException {
		String resonseBody1="[[\"8111005405\",\"0002000019\",\"0002000019\",\"20\",\"1101\",\"MNSD06\",\"20180219\",\"90200097_290\",\" 1600.000\",\"JAR\",\" 348000\",\" 0.00\",\"JOCG 6JOSG 6\",\" 41760.00\",\" 389760.00\",\"MH-14/GD-7020\",\"\",\"\",\"\",\"\",\"\",\"\",\" 100.000\",\"BOX\",\"ZDOR\",\"TAN\",\" 348000.00\",\" 41760.00\",\" 389760.00\"]]";
	
		String responseBody1="[[\"\\\"\\\",\\\"\\\",\\\"12/13/2017\\\",\\\"12/13/2017 5:00:00 AM\\\",\\\"12/13/2017 5:12:05 AM\\\",\\\"50.00\\\",\\\"\\\",\\\"\\\",\\\"11\\\",\\\"IBLPN01\\\",\\\"100.00\\\",\\\"12/13/2017\\\",\\\"12/13/2017 5:00:00 AM\\\",\\\"12/13/2017 5:43:42 AM\\\",\\\"TESTUSER01\\\",\\\"50.00\\\",\\\"\\\",\\\"0.10\\\",\\\"0.10\\\",\\\"\\\",\\\"JL_COMPY\\\",\\\"201\\\",\\\"12/13/2017\\\",\\\"12/13/2017 5:00:00 AM\\\",\\\"12/13/2017 5:10:42 AM\\\",\\\"50.00\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"\\\",\\\"0\\\",\\\"0\\\",\\\"0\\\",\\\"0\\\",\\\"P04\\\",\\\"3\\\",\\\"11\\\",\\\"1602\\\",\\\"12/13/2017\\\",\\\"12/13/2017 2:44:22 PM\\\",\\\"TESTUSER01\\\",\\\"50.00\\\",\\\"ASN01\\\",\\\"\\\",\\\"\\\",\\\"12/13/2017\\\",\\\"Verified\\\",\\\"\\\",\\\"12/13/2017\\\",\\\"12/13/2017 2:00:00 PM\\\",\\\"12/13/2017 2:44:22 PM\\\",\\\"50.00\\\",\\\"TESTUSER01\\\",\\\"LASN01\\\",\\\"\\\",\\\"91-20-011-0023\\\",\\\"G.I. 24 SWG Ducting Material supply with fitting\\\"\",\"JL_COMPY_P04_BLR_Support_IB Shipment- GRN_5_30_2018_15_52_20_263.csv\",\"2\"]]";
		String resonseBody="{\r\n" + 
				"	\"env:Header\":[], \r\n" + 
				"	\"env:Body\":[\r\n" + 
				"			{\r\n" + 
				"			\"OutputParameters\":[\r\n" + 
				"								{\r\n" + 
				"									\"OUT_REQUEST_STATUS\":[\"FAILURE\"], \r\n" + 
				"									\"P_PO_DETAILS_TBL\":[]\r\n" + 
				"								}\r\n" + 
				"							]\r\n" + 
				"			}\r\n" + 
				"		]\r\n" + 
				"}";
		
		
		
		String objectHolder	="";
		String responseBody3="";
	
		
		String responseBody4="";
		
		
		
		
		ObjectMapper mapper= new ObjectMapper();
		//System.out.println(mapper.writeValueAsString( mapper.readValue(objectHolder, Object.class)));
		NashornHelper noshornHelper = new NashornHelper();
	   Object myObject=	mapper.readValue(responseBody1, Object.class);
	  // System.out.println(myObject);
		Object object=noshornHelper.process(javascript5, mapper.readValue(objectHolder, Object.class),  mapper.readValue(responseBody3, Object.class));
		ObjectMapper objectMapper=new ObjectMapper();
		System.out.println(objectMapper.writeValueAsString(object));
	}

}
