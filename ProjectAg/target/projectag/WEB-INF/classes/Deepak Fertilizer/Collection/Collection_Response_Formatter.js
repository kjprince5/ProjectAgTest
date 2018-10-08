function converter(holder, myCode) {
	 var ArrayList = Java.type('java.util.ArrayList');
	var collectionApiArr = new ArrayList();;
	for (i = 0; i < myCode.length; i++) {
		
		var data = myCode[i];
		if(data[6]=='check' || false)
		{
		var dataObject = {};
		var CheckInovicedata={};
		dataObject.warehouse_erp_id = data[0];
    	dataObject.outlet_erp_id = data[0];
    	dataObject.amount = data[3];
    	dataObject.fordate = data[4];
    	dataObject.mode =data[5];
    	CheckInovicedata.checkno=data[6];
    	CheckInovicedata.bankname=data[9];
    	CheckInovicedata.checkdate=data[7];
    	dataObject.Check=CheckInovicedata;
		collectionApiArr.add(dataObject);
		
		}
		if(data[6]=='online' || false)
		{
		var dataObject = {};
		var onlineInovicedata={};
		dataObject.warehouse_erp_id = data[0];
    	dataObject.outlet_erp_id = data[0];
    	dataObject.amount = data[3];
    	dataObject.fordate = data[4];
    	dataObject.mode =data[5];
    	CheckInovicedata.checkno=data[6];
    	CheckInovicedata.bankname=data[9];
    	CheckInovicedata.checkdate=data[7];
    	dataObject.Check=onlineInovicedata;
		collectionApiArr.add(dataObject);
		}
		if(data[6]=='cash' || true)
		{
		var dataObject = {};
		dataObject.warehouse_erp_id =  data[0];
    	dataObject.outlet_erp_id = data[1];
    	dataObject.amount = data[14];
    	dataObject.fordate = data[11];
    	dataObject.comment = data[6];
    	dataObject.mode ='cash';
		collectionApiArr.add(dataObject);
		}
	}
	return collectionApiArr;
 }
 