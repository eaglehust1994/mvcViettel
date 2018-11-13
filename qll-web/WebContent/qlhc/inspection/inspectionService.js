angular.module('MetronicApp').factory('inspectionService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 
		var factory = {
				goTo_DSNTHT : goTo_DSNTHT,
				getWorkItem : getWorkItem,

				getAmaterialhandoverforcontruction:getAmaterialhandoverforcontruction,
				getCompletionDrawing : getCompletionDrawing,
				getListEmployeeByConstruction : getListEmployeeByConstruction,
				getConstructionacceptance:getConstructionacceptance,
				exportFile:exportFile,
				saveConstructionacceptance:saveConstructionacceptance,
				updateConstructionacceptance:updateConstructionacceptance,
				deleteConstructionAcceptance: deleteConstructionAcceptance,
				exportWorkItem :exportWorkItem,
				exportFiledoc: exportFiledoc,
				CheckCA:CheckCA,
				getRoleId:getRoleId,
				exportMerList:exportMerList,
				getConstructionAcceptanceFolder:getConstructionAcceptanceFolder,
				setData : setData,
				getData : getData,
				addFileCal:addFileCal,
				getFileCal:getFileCal,
				deleteFileCal:deleteFileCal,
				updateIsActive:updateIsActive
	    };
	    var GOTO_DSNTHT={
				key: 'INSP_List_CONGTRINH',
				title: 'Danh sách nghiệm thu hoàn thành CT',
				templateUrl:'qlhc/inspectionList/inspectionList.html',
				lazyLoadFiles : [
					'qlhc/inspectionList/inspectionListController.js',
					'qlhc/inspectionList/inspectionListService.js'
                ]
			};
	    return factory;
	    /* Handle action client on a menu item */
		function goTo_DSNTHT(menuKey) { 
				var template = GOTO_DSNTHT;
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
		
		
		
		function getWorkItem(item){
	    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/getWorkItem/").post(item);
	    }
		
		function getAmaterialhandoverforcontruction(item){
	    	return Restangular.all(RestEndpoint.A_MATERIAL_HANDOVER_MER_LIST_SERVICE_URL+"/getAmaterialhandoverforcontructionX/").post(item);
	    }
		function getCompletionDrawing(item) {
	    	return Restangular.all(RestEndpoint.COMPLETION_DRAWING_SERVICE_URL + "/Search/").post(item);
	    }
		function getListEmployeeByConstruction(item) {
			return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
 
		}		
		
	    function saveConstructionacceptance(item) {
	    	return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL).post(item);
	    }
	    function updateConstructionacceptance(item) {
	    	return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL + "/put").post(item);
	    }
	
	    function getConstructionacceptance(obj) {
	    	return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL + "/byConstruct/").post(obj);
	    }
	    function getFileCal(obj) {
	    	return Restangular.all(RestEndpoint.UTIL_ATTACHED_DOCUMENTS_SERVICE_URL + "/getListByParentIdCA").post(obj);
	    }
	    function deleteFileCal(obj){
	    	return Restangular.all(RestEndpoint.UTIL_ATTACHED_DOCUMENTS_SERVICE_URL + "/deleteDocument").post(obj);
	    }
		
		function calculateDistributionDay(item) {
	    	return Restangular.all(RestEndpoint.M_INOUT_LINE_SERVICE_URL + "/distributionDays/").post(item);
	    }
		
		function deleteConstructionAcceptance(id)
		{
			return Restangular.one(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL, id).remove();
		}
		
		function exportFile(constructId) {
			return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL+"/exportFile").post(constructId);
		}
		function exportFiledoc(constructId) {
			return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL+"/exportFiledoc").post(constructId);
		}
		function exportWorkItem(item) {
			return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/exportWorkItem").post(item);
		}
		function getConstructionAcceptanceFolder() {
			return Restangular
					.one(
							RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL+"/folder")
					.get();
		}
		function addFileCal(item) {
			return Restangular
					.all(
							RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL+"/addFileCalculate")
					.post(item);
		}
		function exportMerList(item) {
			return Restangular.all(RestEndpoint.A_MATERIAL_HANDOVER_MER_LIST_SERVICE_URL+"/exportMerList").post(item);
		}
		function CheckCA(item) {
			return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL+"/CheckCA").post(item);
		}
		function getRoleId() {
	        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
	    }
		function setData(data) {
			this.data = data;
		}
		function getData() {
			return this.data;
		}
		function updateIsActive(id) {
			return Restangular
					.all(
							RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL
									+ "/updateIsActive")
					.post(id);
		}

	}]);
