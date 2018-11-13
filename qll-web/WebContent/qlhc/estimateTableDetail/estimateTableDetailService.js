
angular.module('MetronicApp').factory('estimatesTableDetailService', ['$http', '$q', 'RestEndpoint', 'PopupConst', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, PopupConst, Restangular, $kWindow){
	var serviceUrl = RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL;   
	var id;
	var factory = {
			fetchAllInsideContract : fetchAllInsideContract,
			fetchAllOutsideContract : fetchAllOutsideContract,
			  fetchAll:fetchAll,
			  addAll:addAll,
			  deleteData:deleteData,
			  fail:fail,
			  fetchAllExpertise:fetchAllExpertise,
			  setItem:setItem,
			  getItem:getItem,
			  getWorkItem: getWorkItem,
			  setEstimatesWorkItemId:setEstimatesWorkItemId,
			  getEstimatesWorkItemId:getEstimatesWorkItemId,
			  updateConstrAcceptWorkList: updateConstrAcceptWorkList,
			  getAllEstimatesWorkContract : getAllEstimatesWorkContract,
			  exportEstimateTable :exportEstimateTable,
			  appro: appro,
			   importCT:importCT,
			  importKL:importKL,
			  exPortKLTD:exPortKLTD,
			  exPortCTTDPopup:exPortCTTDPopup,
			  checkPoFound:checkPoFound,
			  getemployeeRandom:getemployeeRandom,
			  exPortfull:exPortfull,
			  cancelAprroval : cancelAprroval,
			  getRoleId:getRoleId,
			  getsendPersonName:getsendPersonName,
			  updateIsActive:updateIsActive,
	    };
	    return factory;
	    
	    function setItem(item){
			this.item = item;
		}
	    
	    function getItem(){
			return this.item;
		}
	    
	  //approval
		function appro(dto) {
			return Restangular.all(
					RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/appro").post(dto);
		}
		
		function cancelAprroval(dto){
	    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
	    }
	    
	    function setEstimatesWorkItemId(id){
	    	this.id = id;
	    }
	    
	    function getEstimatesWorkItemId(){
	    	return this.id;
	    }
	    
	    function getAllEstimatesWorkContract(object) {	    	
		 	return Restangular.all( RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL + "/getAllEstimatesWorkContract/").post(object);	 	   
		}
	    
	    function exportEstimateTable(item) {
			return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/exportEstimateTable").post(item);
		}
	    
	    function updateConstrAcceptWorkList(obj) {	    	
		 	return Restangular.all(RestEndpoint.CONSTR_ACCEPT_WORK_LIST_SERVICE_URL + "/put").post(obj);	 	   
		}
	    
	    function fetchAllInsideContract(obj) {	    	
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/getAllEstimatesWorkInsideContract").post(obj);	 	   
	    }
	    
	    function fetchAllOutsideContract(obj) {	    	
		 	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/getAllEstimatesWorkOutsideContract").post(obj);	 	   
		}
	    
	    function fetchAll(obj){
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/getAllbyConstructId").post(obj);	
	    }
	    function addAll(obj){
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/addAll").post(obj);
	    }
	    function deleteData(id){
	    	return Restangular.one(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL,id).remove();
	    }
	    
	    function fail(id){
	    	return Restangular.one(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/fail",id).remove();
	    }
	    
	    function getWorkItem(item){
	    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/getWorkItemDetail/").post(item);
	    }
	    
	    function fetchAllExpertise(id){
	    	return Restangular.one(RestEndpoint.CONSTR_ACCEPT_WORK_LIST_SERVICE_URL+"/getAllbyConstructId",id).getList();
	    }
	    
	    function importCT(obj){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/importCT/").post(obj)
	    }
	    
	    function importKL(item){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/importKL/").post(item)
	    }
	    
	    function exPortKLTD(obj){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/exPortKLTD").post(obj)
	    }
	    
	    function exPortCTTDPopup(obj){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/exPortCT").post(obj)
	    }
	    
	    function exPortfull(obj){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/exPortfull").post(obj)
	    }
	    
	    function checkPoFound(obj){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/checkPoFound").post(obj)
	    }
	    
	    function getemployeeRandom(obj){
	    	return  Restangular.all("QualityCableMeaReportRsServiceRest/getListEmployeeByRole").post(obj)
	    }
	    
	    function getRoleId() {
        	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
    	}
	    
	    function getsendPersonName(id){
	    	return Restangular.one("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/getsendPersonName/"+id).get()
	    }
	    function updateIsActive(id) {
			return Restangular
					.all(
							RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/updateIsActive")
					.post(id);
		}
	}]);
