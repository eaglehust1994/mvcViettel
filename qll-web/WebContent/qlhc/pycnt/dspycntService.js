angular.module('MetronicApp').factory('dspycntService', ['$http', '$q', 'Constant', 'RestEndpoint', 'Restangular', function ($http, $q, Constant, RestEndpoint, Restangular) {

    var SERVICE_URI = Constant.SERVICE_URL + 'cProfitCenterRsService/cProfitCenter';
    var SERVICE_GET_ALL = '';
    var factory = {
    			getDspycnt: getDspycnt,
    	        getConTruction: getConTruction,
    	        deleteConstrAcceptanceReq: deleteConstrAcceptanceReq,
    	        setItem:setItem,
    			getItem:getItem,
    			setItem1:setItem1,
    			getItem1:getItem1,
    			getWorkItemDone:getWorkItemDone,
    			getWorkItemNotDone:getWorkItemNotDone,
    			pauseWorkItem:pauseWorkItem,
    			appro:appro,
    			getRoleId : getRoleId,
    			cancelAprroval : cancelAprroval,
    			removeConstrAcceptanceReq:removeConstrAcceptanceReq
    			
    };

    return factory;
    
    function setItem(item){
		this.item = item;
	}
	function getItem(){
		return this.item;
	}
	
	function setItem1(item1){
		this.item1 = item1;
	}
	function getItem1(){
		return this.item1;
	}
	function cancelAprroval(dto){
    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
    }
	function getWorkItemDone(object) {
    	return Restangular.all(RestEndpoint.WORK_ITEMS_SERVICE_URL).post(object);
    	
    }
	
	function getWorkItemNotDone(object) {
    	return Restangular.all(RestEndpoint.WORK_ITEMS_NOT_DONE_SERVICE_URL).post(object);
    	
    }
	
	function pauseWorkItem(object) {
    	return Restangular.all(RestEndpoint.PAUSE_WORK_ITEMS_NOT_DONE_SERVICE_URL).post(object);
    	
    }
	
    
    function getConTruction(object) {
    	return Restangular.all(RestEndpoint.INTERGRATED_CONTRACT_NAME_SERVICE_URL).post(object);
    	
    }
    
    function getDspycnt(object) {
    	return Restangular.all(RestEndpoint.LIST_CONSTR_ACCEPTANCE_REQ_SERVICE_URL).post(object);
    	
    }
    
    function deleteConstrAcceptanceReq(object) {
    	return Restangular.all(RestEndpoint.DELETE_CONSTR_ACCEPTANCE_REQ_SERVICE_URL).post(object);
    	
    }
    function removeConstrAcceptanceReq(object) {
    	return Restangular.all(RestEndpoint.CONSTR_ACCEPTANCE_REQUEST_RS_SERVICE +"/remove").post(object);
    }
    function appro(dto) {
		return Restangular.all(RestEndpoint.APPROVAL_CONSTR_ACCEPTANCE_REQ_SERVICE_URL).post(dto);
	}
    function getRoleId() {
		return Restangular.all(
				RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
						+ "/getRoleId").getList();
	}
    
}]);
