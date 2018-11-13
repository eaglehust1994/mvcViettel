angular.module('MetronicApp').factory('list_report_services',['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular) {
	var serviceUrl = RestEndpoint.GL_ALLOCATION_TEMPLATE_SERVICE_URL;
	var factory = {
	    	goTo : goTo
	    };
	    var SUGGGET_ADPAYMENT={
	    		key: 'Report_Retrieve_A',
                title: 'Biên bản thu hồi VTTB A cấp thừa',
                templateUrl: 'erp/reportretrieveA/reportretrievelostA.html',
                lazyLoadFiles : [
                'erp/reportretrieveA/reportretrieveAcontroller.js'
                ]
            };
	    return factory;
/* Handle action client on a menu item */
		function goTo(menuKey) {                 			
				var template = SUGGGET_ADPAYMENT;
				setArParams(menuKey);
				setIdCheckbox(menuKey);
				setApParams(menuKey);
				postal.publish({
					channel : "Tab",
					topic   : "open",
					data    : template
				});	
		}
		function setIdCheckbox(key){
			switch(key) {
				case "AR_INVOICE_HEADER":
					$rootScope.nameCheckbox = "isSyncRecord" ;
					$rootScope.nameCheckbox1 = "isDetailSync";
					$rootScope.nameCheckbox2 = "isEstimate" ;
					break;
				case "AR_DTTKDS":
					$rootScope.nameCheckbox = "isSyncRecord1" ;
					$rootScope.nameCheckbox1 = "isDetailSync1";
					$rootScope.nameCheckbox2 = "isEstimate1" ;	
					break;
				case "AR_POSTPAID":
					$rootScope.nameCheckbox = "isSyncRecord2" ;
					$rootScope.nameCheckbox1 = "isDetailSync2";
					$rootScope.nameCheckbox2 = "isEstimate2" ;
					break;			
			}
		}
		
		function setArParams(key) {
			switch(key) {
			    case "AR_INVOICE_HEADER":
			    	$rootScope.invoiceDocTypeId = 8;
			        break;
			    case "AR_DTTKDS":
			    	$rootScope.invoiceDocTypeId = 18;
			        break;
			    case "AR_POSTPAID":
			    	$rootScope.invoiceDocTypeId = 19;
			        break;
			    default:
			}
		}
		
		function setApParams(key) {
			switch(key) {
			    case "AP_BANK_PAYMENT_BILL":
			    	$rootScope.cdocumentTypeId = '4';
			        break;
			    case "AP_CASH_PAYMENT_BILL":
			    	$rootScope.cdocumentTypeId = '5';
			        break;
			    default:
			}
		}
} ]);