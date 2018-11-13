
angular.module('MetronicApp').factory('establishDetailedSettlementProposalService', ['$http', '$q', 'RestEndpoint', 'PopupConst', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, PopupConst, Restangular, $kWindow){
	var serviceUrl = RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL;   
	var factory = {
			  getAllEstimatesWorkInsideContract : getAllEstimatesWorkInsideContract,
			  getAllEstimatesWorkOutsideContract : getAllEstimatesWorkOutsideContract,
			  getAllEstimatesWorkContract : getAllEstimatesWorkContract,
			  getAllDetailSettlementProposal: getAllDetailSettlementProposal,
			  getListEmployeeByRole : getListEmployeeByRole,
			  getRole : getRole,
			  addAll:addAll,
			  updateAll: updateAll,
			  addAppraisalAssignment: addAppraisalAssignment,
			  deleteData:deleteData,
			  showPopupForm: showPopupForm,
			  setItemID:setItemID,
			  getItemID:getItemID,
			  getCode: getCode,
			  setCode: setCode,
			  getEvaluatePersonId: getEvaluatePersonId,
			  setEvaluatePersonId: setEvaluatePersonId,
			  appro: appro,
			  cancelAprroval : cancelAprroval,
			  fail:fail,
			  exPortfull :exPortfull, 
			  getRoleId:getRoleId,
			  updateIsActive:updateIsActive,
	    };
	    return factory;
		
	    function cancelAprroval(dto){
	    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
	    }
	    function setEvaluatePersonId(item){
			this.item = item;
		}
	    
	    function getEvaluatePersonId(){
			return this.item;
		}
	    
	    function setCode(item){
			this.item = item;
		}
	    
	    function getCode(){
			return this.item;
		}
	    
	    function setItemID(item){
			this.item = item;
		}
		function getItemID(){
			return this.item;
		}
		
		function exPortfull(obj){
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL+"/exPortfull").post(obj)
	    }
		
		//approval
		function appro(dto) {
			return Restangular.all(
					RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL+"/appro").post(dto);
		}
		
		function deleteData(id){
	    	return Restangular.one(RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL,id).remove();
	    }
		function addAll(obj){
			return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL+"/addAll").post(obj);
		}
		function getAllEstimatesWorkContract(object) {	    	
		 	return Restangular.all(serviceUrl + "/getAllEstimatesWorkContract/").post(object);	 	   
		}
		
	    function getAllEstimatesWorkInsideContract(object) {	    	
		 	return Restangular.all(serviceUrl + "/getAllEstimatesWorkInsideContract/").post(object);	 	   
		}
	    
	    function getAllEstimatesWorkOutsideContract(object) {	    	
	    	return Restangular.all(serviceUrl + "/getAllEstimatesWorkOutsideContract/").post(object);	 	   
	    }
	    
	    function getAllDetailSettlementProposal(object) {	    	
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL + "/getAllDetailSettlementProposal/").post(object);	 	   
	    }
	    
	    function getListEmployeeByRole(object) {
	    	return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);
		}
	    
	    function getRole(id) {
	    	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_Url + "/getRoleID/").post(id);						
		}
	    
	    function updateAll(Proposal, listEst, listAcc) {
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL + "/updateManyTable/").post(Proposal, listEst, listAcc);						
	    }
	    
	    function addAppraisalAssignment(object) {
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL + "/saveDetailSettlementProposal/").post(object);						
	    }
	    
	    function showPopupForm(criteria) {
	    	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_Url + "/getAllEmployee/").post(criteria);			
		}
	    
	    function fail(obj){
	    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/fail").post(obj);
	    }
	    
	    function getRoleId() {
        	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
    	}
	    function updateIsActive(id) {
			return Restangular
					.all(
							RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL+"/updateIsActive")
					.post(id);
		}
	}]);
