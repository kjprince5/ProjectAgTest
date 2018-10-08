package com.valforma.projectag.helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class XmlToJsonConverter {

	public static Object convert(String xml) throws Exception {

		InputStream is = new ByteArrayInputStream(xml.getBytes());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(is);
		Map<String, Object> map = new HashMap<String, Object>();
		return createMap(document.getDocumentElement(), map);
	}

	private static Object createMap(Node node, Map<String, Object> parentMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		NodeList nodeList = node.getChildNodes();
		String parentName = node.getNodeName();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			String name = currentNode.getNodeName();
			Object value = null;
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(name);
				value = createMap(currentNode, map);
				if (map.containsKey(name)) {
					if (!(value instanceof Map && ((Map) value).isEmpty())) {
						Object os = map.get(name);
						((List<Object>) os).add(value);
					}
				} else {
					List<Object> objs = new LinkedList<Object>();
					if (!(value instanceof Map && ((Map) value).isEmpty())) {
						objs.add(value);
					}
					map.put(name, objs);
				}
			} else if (currentNode.getNodeType() == Node.TEXT_NODE
					|| currentNode.getNodeType() == Node.CDATA_SECTION_NODE) {
				if (!currentNode.getTextContent().trim().isEmpty()) {
					value = currentNode.getTextContent();
					if (parentMap.containsKey(parentName)) {
						Object os = parentMap.get(parentName);
						((List<Object>) os).add(value);
					} else {
						List<Object> objs = new LinkedList<Object>();
						objs.add(value);
						parentMap.put(parentName, objs);
					}
				}
			}

		}
		return map;
	}

	
	
	public static void main(String args[]) throws Exception
	{
		String xml= "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"   <env:Header/>\r\n" + 
				"   <env:Body>\r\n" + 
				"      <OutputParameters xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://xmlns.oracle.com/apps/per/soaprovider/plsql/xxnrb_itemmaster_pkg/item_master_details/\">\r\n" + 
				"         <P_ITEM_DETAILS_TBL>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510091-0127</ITEM>\r\n" + 
				"               <DESCRIPTION>20Pair Jelly Filled Cable ( Telephone Cable ) .</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Meters</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510091</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7511641-0970</ITEM>\r\n" + 
				"               <DESCRIPTION>Hydraulic Hose Pipe (Gates/ Parker Make) Ref no. 1004D, Threading Size: M14 x 1.5 both end, Length- 1.5 Mtr</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7511641</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510321-0348</ITEM>\r\n" + 
				"               <DESCRIPTION>BATTERY DISTILLED WATER CAN (35LTR).</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510321</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510221-0465</ITEM>\r\n" + 
				"               <DESCRIPTION>PVC Insulated Multistrand Cable (Polycab Make) 24 Core, 2.5 Sq. mm</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Meters</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510221</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510141-1333</ITEM>\r\n" + 
				"               <DESCRIPTION>DUPLEX THERMOCOUPLE LENGTH 750 MM TYPE NI CR/NI AL DIA 19.3 MM IN SS 310 SEAMLESS TUBE &amp; ALLLUMINIUM  HEAD WITH BEEDS &amp; CERAMIC CONNECTER WIRE GUAGE 14 SWG (FOR TRR F/C NO 2)</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510141</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7001011-0272</ITEM>\r\n" + 
				"               <DESCRIPTION>Cucking Sleeve. HMT Part no. 10- 211. Required for- HMT Gildemeister AS 48 Machine.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7001011</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7001011-0271</ITEM>\r\n" + 
				"               <DESCRIPTION>Roller Slide for Turret. HMT Part no. 19- 103. Required for- HMT Gildemeister AS 48 Machine.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7001011</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7005121-0071</ITEM>\r\n" + 
				"               <DESCRIPTION>Impeller for  Coolant Pump (Rajmane Make) Type: RV25 / 170, 3 Phase, Class: B, 0.08 KW, 2800 R.P.M., LPM- 25, Hz- 50.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7005121</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7051701-0002</ITEM>\r\n" + 
				"               <DESCRIPTION>Guide Rail Unit (Rexroth Make) R1651 813 20</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7051701</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510141-1650</ITEM>\r\n" + 
				"               <DESCRIPTION>PNEUMATIC STRAIGHT TYPE SCREW DRIVERS</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510141</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510081-0314</ITEM>\r\n" + 
				"               <DESCRIPTION>Worm Shaft. 45 O.D. x 170 mm.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510081</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510081-0315</ITEM>\r\n" + 
				"               <DESCRIPTION>Worm Gear. 60 O.D. x 35 mm.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510081</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510081-0316</ITEM>\r\n" + 
				"               <DESCRIPTION>Worm Shaft. 45 O.D. x 130 mm.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510081</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7005411-0015</ITEM>\r\n" + 
				"               <DESCRIPTION>Profibus Module (Siemens Make) Part no. 6ES6400- 1PB00- 0AA0.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7005411</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7511511-0812</ITEM>\r\n" + 
				"               <DESCRIPTION>V Belt (Fenner Make) FHP 2310</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Meters</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7511511</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7005121-0070</ITEM>\r\n" + 
				"               <DESCRIPTION>Filter Element (HYDAC Make) 0030 D 003Y U 5/03</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7005121</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7001011-0273</ITEM>\r\n" + 
				"               <DESCRIPTION>Disc Spring (Plate Spring) HMT Part no. 10- 216. Required for- HMT Gildemeister AS 48 Machine.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7001011</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7001011-0274</ITEM>\r\n" + 
				"               <DESCRIPTION>Shearing Lever for Cross Slide (Pair) HMT Part no. 08- 401. Required for- HMT Gildemeister AS 48 Machine.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7001011</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7001011-0275</ITEM>\r\n" + 
				"               <DESCRIPTION>Shearing Lever for Cross Slide (Pair) HMT Part no. 08- 403. Required for- HMT Gildemeister AS 48 Machine.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7001011</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7020031-0074</ITEM>\r\n" + 
				"               <DESCRIPTION>Modification of Inverter module, Control Card  and digital Flow Switch and PLC upgradation of S7200 with display OP77B  for machine no. NRB 58. Item code no. 90800062. Machine: GH II</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7020031</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510211-0856</ITEM>\r\n" + 
				"               <DESCRIPTION>Exhaust Fan (EBM Past Make) 712F/ 2M, 12 VDC, 90 mA, 1.1 W, Size: 65 mm x 65 mm.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7006241</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510211-0857</ITEM>\r\n" + 
				"               <DESCRIPTION>Exhaust Fan (SUNON Make) Maglev KDE1204PFV2, DC 12 Volt, 1.0 W, Size: 40 mm x 40 mm.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7006241</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510321-0909</ITEM>\r\n" + 
				"               <DESCRIPTION>Love Joy Coupling, Type - L100 with Spiders</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510321</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510021-0442</ITEM>\r\n" + 
				"               <DESCRIPTION>4 FT TUBE LIGHT FITTING (PHILIPS MAKE) WITH 36W TWO TUBE ROD, ALONGWITH COPPER CHOKE WITH REFLACTOR.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510021</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7005601-0006</ITEM>\r\n" + 
				"               <DESCRIPTION>Spares used for Repairs of NCU Card (Siemens Make) NCU 573.4, 650 Mhz, 64 MB, 6FC 5357- 0BB34- 0AA0, S T- T82050053, Version C.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7005601</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510321-0907</ITEM>\r\n" + 
				"               <DESCRIPTION>12 MM BROWN GLASS REQUIRED FOR TABLES , 12 MM BROWN GLASS 2.5 INCH X 4 INCH = 30 SQFT , GLASS POLISH (BEVELING ).</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510321</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510271-0201</ITEM>\r\n" + 
				"               <DESCRIPTION>High Frequency Spindle Drive (Vacon Make) NXP00612A2H1SSVA1A3B2 11 kW, 48 A, 230 VAC, Galvanically isolated I/O, 4% impedance inbuilt choke</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510271</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7510271-0202</ITEM>\r\n" + 
				"               <DESCRIPTION>High Frequency Spindle Drive Choke (Vacon Make) 2 HKR German HF o/p choke 7.5 kW, 38 A.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7510271</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>7005371-0101</ITEM>\r\n" + 
				"               <DESCRIPTION>Marposs Unimar. W26. Part no. 3427848426.</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>P04</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Jalna</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>7005371</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>1013180-0090</ITEM>\r\n" + 
				"               <DESCRIPTION>B9210027FS NEEDLE CAGE</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>W16</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Dharuhera</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>1013180</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>1010160-0068</ITEM>\r\n" + 
				"               <DESCRIPTION>ROLLER10X18 NEEDLE ROLLER</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>W16</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Dharuhera</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Thousand</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>1010160</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>1013120-0272</ITEM>\r\n" + 
				"               <DESCRIPTION>B60548TM CAGES TURNED-T/TM</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>W16</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Dharuhera</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>SET</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>1013120</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>1013120-0273</ITEM>\r\n" + 
				"               <DESCRIPTION>B60547TM CAGES TURNED-T/TM</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>W16</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Dharuhera</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>SET</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>1013120</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>1010160-0069</ITEM>\r\n" + 
				"               <DESCRIPTION>10X24.15  NEEDLE ROLLER</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>W16</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Dharuhera</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Thousand</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>1010160</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>1013100-0647</ITEM>\r\n" + 
				"               <DESCRIPTION>B394421 NEEDLE CAGE</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>W16</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Dharuhera</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>1013100</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"            <P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"               <ITEM>1015100-0082</ITEM>\r\n" + 
				"               <DESCRIPTION>AR8194 THRUST BEARING ASSLY</DESCRIPTION>\r\n" + 
				"               <ORGANIZATION_CODE>W16</ORGANIZATION_CODE>\r\n" + 
				"               <ORGANIZATION_NAME>Dharuhera</ORGANIZATION_NAME>\r\n" + 
				"               <PRIMARY_UNIT_OF_MEASURE>Numbers</PRIMARY_UNIT_OF_MEASURE>\r\n" + 
				"               <LONG_DESCRIPTION xsi:nil=\"true\"/>\r\n" + 
				"               <LOTCONTROL>FALSE</LOTCONTROL>\r\n" + 
				"               <SHELF_LIFE_DAYS>0</SHELF_LIFE_DAYS>\r\n" + 
				"               <LIST_PRICE>1</LIST_PRICE>\r\n" + 
				"               <UNIT_WEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_VOLUME xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_LENGTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_WIDTH xsi:nil=\"true\"/>\r\n" + 
				"               <UNIT_HEIGHT xsi:nil=\"true\"/>\r\n" + 
				"               <HAZARDOUS_MATERIAL>N</HAZARDOUS_MATERIAL>\r\n" + 
				"               <CATEGORY>1015100</CATEGORY>\r\n" + 
				"            </P_ITEM_DETAILS_TBL_ITEM>\r\n" + 
				"         </P_ITEM_DETAILS_TBL>\r\n" + 
				"         <OUT_REQUEST_STATUS>SUCCESS</OUT_REQUEST_STATUS>\r\n" + 
				"      </OutputParameters>\r\n" + 
				"   </env:Body>\r\n" + 
				"</env:Envelope>";
		System.out.println(convert(xml));
	}
}
