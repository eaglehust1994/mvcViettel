angular.module('MetronicApp').factory('list_report_result_services',['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular) {
	var serviceUrl = RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL;
	var factory = {
	    	goTo : goTo,
	    	getQualityReportList : getQualityReportList,
	    	findByConstructId: findByConstructId,
	    	removeQuality : removeQuality,
	    	deleteReport : deleteReport,
	    	setItemID:setItemID,
			getItemID:getItemID,
			////
			getListEmployeeByConstruction : getListEmployeeByConstruction,
			getListEmployeeByRole : getListEmployeeByRole,
			getListQualityResualt : getListQualityResualt,
			addQualityCableMeaReport : addQualityCableMeaReport,
			updateQualityCableMeaReport : updateQualityCableMeaReport,
			deleteResult : deleteResult,
			exportFile : exportFile,
			exportList : exportList,
			exportFileDoc : exportFileDoc,
			exportListDoc : exportListDoc,
			appro : appro,
			cancelAprroval : cancelAprroval,
			exportExcel : exportExcel,
			getRoleId: getRoleId
	    };
	
	function setItemID(item){
		this.item = item;
	}
	function getItemID(){
		return this.item;
	}
	
//	    var SUGGGET_ADPAYMENT={
//	    		 key: 'Report_Result',
//	                title: 'Báo cáo kết quả đo chất lượng tuyến cáp',
//	                templateUrl: 'erp/reportresult/report_result.html',
//	                lazyLoadFiles : [
//	                'erp/reportresult/report_result_controller.js',
//                ]
//            };
	    return factory;
/* Handle action client on a menu item */
	    function cancelAprroval(dto){
	    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
	    }
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
//Load////////////////////		
		function getQualityReportList() {
			return Restangular.all(serviceUrl).getList();
		}
		//LoadID/////
		function findByConstructId(object) {
			return Restangular.all(serviceUrl + "/findByConstructId/").post(object);
		}
		///Remove Quality
		function removeQuality(qualityCableMeaReportId) {
			return Restangular.one(serviceUrl,qualityCableMeaReportId).remove();
		}
		
		// remove many
		function deleteReport(listQualityCableMeaReportId) {
			return Restangular.all(serviceUrl+"/deleteReport").post(listQualityCableMeaReportId);
		}
		
		/////////////////
		function getListEmployeeByRole(object) {
			return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);			
		}	
		
		function addQualityCableMeaReport(objectReportResult){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL).post(objectReportResult);
		}
		function getListQualityResualt(qualityCableMeaReportId) {
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL + "/getListQualityResualt/" + qualityCableMeaReportId).getList();
		}

		
		function updateQualityCableMeaReport(objectReportResult){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL + "/put").post(objectReportResult);
		}
		
		function deleteResult(listIDResult){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/deleteResult").post(listIDResult);
		}
		
		function exportFile(objectReport) {
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportFile").post(objectReport);
		}
		
		function exportList(objectReportID){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportList").post(objectReportID);
		}
		
		function exportFileDoc(objectReport) {
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportFileDoc").post(objectReport);
		}
		
		function exportListDoc(objectReportID){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportListDoc").post(objectReportID);
		}
		
		function appro(objectAppro){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/appro").post(objectAppro);
		}
		
		function exportExcel(){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportExcel").post();
		}
		
		function getRoleId() {
            return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
        }
		function getListEmployeeByConstruction(item) {
			return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
		}
		
} ]);