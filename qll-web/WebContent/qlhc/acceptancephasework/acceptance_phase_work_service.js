angular.module('MetronicApp').factory('acceptanceService', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular){
	var serviceUrl = RestEndpoint.ESTIMATES_ITEM_CHILD_URL;
	var serviceUrl = RestEndpoint.CATEGORY_ACCEPTANCE_URL;
	var dataToApproval;
	var factory = {	    	
	    	getAcceptanceItemsList : getAcceptanceItemsList,	  
	    	addCategoryAcceptance : addCategoryAcceptance,
	    	getCategoryAcceptanceById : getCategoryAcceptanceById,
	    	updateCategoryAcceptance : updateCategoryAcceptance,
	    	getAllCategoryAcceptance : getAllCategoryAcceptance,
	    	getAllAMonitorOrBInChargeByConstructId : getAllAMonitorOrBInChargeByConstructId,	    		
			deleteCategoryAcceptanceList : deleteCategoryAcceptanceList,
			deleteCategoryAcceptanceId: deleteCategoryAcceptanceId,			
			/*setCategoryAcceptanceId:setCategoryAcceptanceId,
			getCategoryAcceptanceId:getCategoryAcceptanceId,*/
			setDataToApproval:setDataToApproval,
			getDataToApproval:getDataToApproval,
			//exportFileM : exportFileM,
	    	//exportList : exportList,
	    	exportDOC:exportDOC,
	    	exportPDF:exportPDF,
	    	Approval : Approval,
	    	cancelAprroval : cancelAprroval,
	    	exportListDocCategoryAcceptence:exportListDocCategoryAcceptence,
	    	exportListPDFCategoryAcceptence:exportListPDFCategoryAcceptence,
	    	getRoleId:getRoleId
	    };
	
	return factory;
	function cancelAprroval(dto){
    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
    }
	function getAcceptanceItemsList(constructId) {
		return Restangular.all(RestEndpoint.ESTIMATES_ITEM_CHILD_URL+"/" +constructId).getList();
	}		
	
	function getAllCategoryAcceptance(constructId, contractId) {
		return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/category/" +constructId+"/" + contractId).getList();
	}
	
	function addCategoryAcceptance(obj) {
		return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/").post(obj);
	}
	
	function getCategoryAcceptanceById(categoryAcceptanceId){
		return Restangular.one(RestEndpoint.CATEGORY_ACCEPTANCE_URL+ "/detail/" +categoryAcceptanceId).get();
	}
	
	function updateCategoryAcceptance(obj) {
		return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/put").post(obj);
	}
	
	function getAllAMonitorOrBInChargeByConstructId(objectDTO) {
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL+"/getMonitorByRoleConstructId").post(objectDTO);			
	}	
	
	function deleteCategoryAcceptanceId(categoryAcceptanceId) {
    	return Restangular.one(RestEndpoint.CATEGORY_ACCEPTANCE_URL, categoryAcceptanceId).remove();
    }
	
	function deleteCategoryAcceptanceList(listObj) {
		return Restangular.all(
				RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/deleteListCategoryAcceptance/").post(listObj);
	}
	
	function setDataToApproval(dataToApproval){
		this.dataToApproval = dataToApproval;
	}
	function getDataToApproval(){
		return dataToApproval;
	}
	
	/*function exportFileM(objectReport) {
		return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/exportFileM").post(objectReport);
	}
	*/
	/*function exportList(objectReportID){
		return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/exportList").post(objectReportID);
	}*/
	  function Approval(obj){
			return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/approval").post(obj);
		}
	  
	    function exportPDF(criteria) {
			return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL + "/exportPDF").post(criteria);
		}
	    
	    function exportDOC(criteria) {
			return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL + "/exportDOC").post(criteria);
		}
	
	    function exportListDocCategoryAcceptence(searchCriteria) {	    	
			return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL  + "/exportWordListDoc").post(searchCriteria);
	    }
	    
	    function exportListPDFCategoryAcceptence(searchCriteria) {	    	
			return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL  + "/exportWordListpdf").post(searchCriteria);
	    }
	    function getRoleId() {
			return Restangular.all(
					RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
							+ "/getRoleId").getList();
		}
} ]);
