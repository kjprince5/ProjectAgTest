function converter(holder, myCode) {
	        var parentDict = {};
	        var ArrayList = Java.type('java.util.ArrayList');
	        for (i = 0; i < myCode.length; i++) {
		        
		            if (parentDict[myCode[i][1]] === undefined) {
		                parentDict[myCode[i][1]] = [];
		                parentDict[myCode[i][1]].erp_id=myCode[i][1];
		                parentDict[myCode[i][1]].name=myCode[i][4];
		                parentDict[myCode[i][1]].code=myCode[i][5];
		                parentDict[myCode[i][1]].pin=myCode[i][12];
		                parentDict[myCode[i][1]].state_id=myCode[i][13];
		                parentDict[myCode[i][1]].ownermobile=myCode[i][15];
		                parentDict[myCode[i][1]].areaname=myCode[i][19];
		                parentDict[myCode[i][1]].email=myCode[i][16];
		                parentDict[myCode[i][1]].panno=myCode[i][22];
		                parentDict[myCode[i][1]].licenseno=myCode[i][24];
		                parentDict[myCode[i][1]].licensevalidfrom=myCode[i][25];
		                parentDict[myCode[i][1]].licenseTo=myCode[i][26];
		                parentDict[myCode[i][1]].gstinno=myCode[i][40];
		            }
		     
	        }
	        var object = new ArrayList();;
	        for (var key in parentDict) {
		            object.add(parentDict[key]);
	        }
	        return object;
	    }