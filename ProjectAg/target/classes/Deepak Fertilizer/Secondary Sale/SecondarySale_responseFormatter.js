function converter(holder, myCode) {
	        var parentDict = {};
	        var ArrayList = Java.type('java.util.ArrayList');
	        for (i = 0; i < myCode.length; i++) {
		            if (parentDict[myCode[i][3]+"_"+myCode[i][6]] === undefined) {
		                parentDict[myCode[i][3]+"_"+myCode[i][6]] = [];
		                var object={};
		                object.outlet_erp_id=myCode[i][3];
		                object.orderNumber=myCode[i][5];
		                object.invoiceNumber=myCode[i][6];
		                object.billDate=myCode[i][8];
		                object.fromoutletid=myCode[i][18];
		                object.mode='Cash';
		                parentDict[myCode[i][3]+"_"+myCode[i][6]].main =object;
		                parentDict[myCode[i][3]+"_"+myCode[i][6]].paymentDetails=new ArrayList();
		            }
		            var paymentDetail={};
	                paymentDetail.deliveryChallan=myCode[i][17];
	                paymentDetail.sapSkuCode=myCode[i][22];
	                paymentDetail.quantity=myCode[i][25];
	                paymentDetail.unit=myCode[i][26];
	                paymentDetail.unitpriceextax=myCode[i][27];
	                paymentDetail.discountperunit=myCode[i][44];
	                paymentDetail.schemediscountperunit=myCode[i][45];
	                paymentDetail.cgstPercentage=myCode[i][59];
	                paymentDetail.cgstValue=myCode[i][60];
	                paymentDetail.cess=myCode[i][38];
	                paymentDetail.sgstPercentage=myCode[i][61];
	                paymentDetail.sgstValue=myCode[i][62];
	                paymentDetail.igstPercentage=myCode[i][63];
	                paymentDetail.igstValue=myCode[i][64];
	                paymentDetail.ugstPercentage=myCode[i][65];
	                paymentDetail.ugstValue=myCode[i][66];
		            parentDict[myCode[i][3]+"_"+myCode[i][6]].paymentDetails.add(paymentDetail);
	        }
	        var object = new ArrayList();
	        for (var key in parentDict) {
		            object.add(parentDict[key]);
	        }
	        return object;
	    }