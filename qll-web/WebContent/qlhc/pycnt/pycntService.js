angular.module('MetronicApp').factory('pycntService', ['$http', '$q', 'Constant', 'RestEndpoint', 'Restangular', function ($http, $q, Constant, RestEndpoint, Restangular) {

    var SERVICE_URI = Constant.SERVICE_URL + 'cProfitCenterRsService/cProfitCenter';
    var SERVICE_GET_ALL = '';
    var factory = {
    		getConstructions: getConstructions	,
    		getEmployeeByRole: getEmployeeByRole,
    		exportFile: exportFile,
    		setItem:setItem,
    		getThoiGianBanGiao:getThoiGianBanGiao,
			getItem:getItem,
			getPartner:getPartner,
			addConstrAcceptanceRequest:addConstrAcceptanceRequest,
			exportList:exportList,
			getListEmployeeByConstruction:getListEmployeeByConstruction,
			getRoleId:getRoleId
    };

    return factory;
    
    function setItem(item){
		this.item = item;
	}
	function getItem(){
		return this.item;
	}
    
    function getConstructions(object) {
    	return Restangular.all(RestEndpoint.GETCONSTRT_SERVICE_URL).post(object);
    	
    }
    
    function getEmployeeByRole(object) {
    	return Restangular.all(RestEndpoint.GET_EMPLOYEE_ROLL_SERVICE_URL).post(object);
    	
    }
    
 
    
    function getThoiGianBanGiao(object) {
    	return Restangular.all(RestEndpoint.GET_THOIGIAN_BANGIAO_SERVICE_URL).post(object);
    	
    }
   
    function getPartner(object) {
    	return Restangular.all(RestEndpoint.GET_PARTNER_SERVICE_URL).post(object);
    	
    }
    
    
    function exportFile(object) {
    	return Restangular.all(RestEndpoint.EXPORT_PYCNT_SERVICE_URL).post(object);
    	
    }
    
    function exportList(object) {
    	return Restangular.all(RestEndpoint.EXPORT_LIST_PYCNT_SERVICE_URL).post(object);
    	
    }
    
    function addConstrAcceptanceRequest(object) {
    	return Restangular.all(RestEndpoint.ADD_CONSTR_ACCEPTANCE_REQUEST_SERVICE_URL).post(object);
    	
    }
    
	function getListEmployeeByConstruction(item) {
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
	}
    
	function getRoleId() {
        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
    }
}]);
