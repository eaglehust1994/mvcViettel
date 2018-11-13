angular.module('MetronicApp').factory('ProposalEvaluation', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	// Danh sach cac dich vu ma service nay cung cap
	var item ;
	var workLogs;
	var getcatEmployeeName;
	var checkGoTo;
	var factory = {
		findByConstructionId:findByConstructionId,
		getAllandSearch:getAllandSearch,
		openPartners:openPartners,
		openProject:openProject,
		getWorkItem:getWorkItem,
		setItem:setItem,
		getItem:getItem,
		setWorkLogs:setWorkLogs,
		getWorkLogs:getWorkLogs,
		setgetcatEmployeeName: setgetcatEmployeeName,
		getgetcatEmployeeName:getgetcatEmployeeName,
		getEmployeeIdByUserId:getEmployeeIdByUserId,
		checkBia:checkBia,
		setCheckGoTo:setCheckGoTo,
		getCheckGoTo:getCheckGoTo,
		getcheckProposal:getcheckProposal,
		getcheckEvaluate:getcheckEvaluate,
		importCT:importCT,
		exPortCTTDPopup:exPortCTTDPopup,
		exPortKLTD:exPortKLTD,
		importCVHD:importCVHD
	};
	return factory;
	
	function setCheckGoTo(data){
		checkGoTo=data;
	}
	
	function getCheckGoTo(){
		return checkGoTo;
	}
	
	function getEmployeeIdByUserId(item) {
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getEmployeeIdByUserId").post(item);	
	}
	
	function setgetcatEmployeeName(getcatEmployeeName){
		this.getcatEmployeeName = getcatEmployeeName;
	}
	
	function getgetcatEmployeeName(){
		return this.getcatEmployeeName;
	}
	
	function setWorkLogs(workLogs){
		this.workLogs = workLogs;
	}
	function getWorkLogs(){
		return this.workLogs;
	}
	
	function setItem(item){
		this.item = item;
	}
	function getItem(){
		return this.item;
	}
    
    function getAllandSearch(criteria){
    	return Restangular.all(RestEndpoint.CONSTR_CONSTRUCTIONS_SERVICE_URL + "/getAllandSearch").post(criteria);
	}
    function openPartners(){
    	return Restangular.all(RestEndpoint.CAT_PARTNERS_SERVICE_URL).getList(); 
	}
    function openProject(){
    	return Restangular.all(RestEndpoint.PROJ_INVEST_PROJECT_SERVICE_URL).getList(); 
	}

	function findByConstructionId(constructionId){
		return Restangular.one(RestEndpoint.CONSTR_CONSTRUCTIONS_SERVICE_URL + "/" + constructionId).get();
	}
	
	function getWorkItem(item){
    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/getWorkItem/").post(item);
    }
	function getcheckProposal(item){
    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_PROPOSAL_URL+"/getAllDetailSettlementProposal/").post(item);
    }
	function getcheckEvaluate(item){
    	return Restangular.all(RestEndpoint.DETAIL_SETTLEMENT_EVALUATE_URL+"/getAllbyConstructId/").post(item);
    }
	//ChuongNV kiem tra tao bia nhat ky chua
	function checkBia(idConstruct){
		return Restangular.all(
				RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL+"/checkBia").post(idConstruct);
    }
	
	 function importCT(item){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/importCTHD/").post(item);
	    }
	 
	 function exPortCTTDPopup(obj){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/exPortCTHD").post(obj)
	    }
	 
	 function exPortKLTD(obj){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/exPortCVHD").post(obj)
	    }
	 
	 function importCVHD(item){
	    	return Restangular.all("detailSettlementEvaluateServiceRest/detailSettlementEvaluate/imPortCVHD/").post(item);
	    }
    
}]);