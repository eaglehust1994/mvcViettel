angular.module('MetronicApp').factory('hshcAuthService',['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular) {

    var factory = {
    		fetchAll: fetchAll,
    		getConstructions: getConstructions,
    		getListEmployee: getListEmployee,
    		addSettlementRight: addSettlementRight,
    		deleteSettlementRight: deleteSettlementRight,
    		getListChucVu: getListChucVu,
    		getAutoData:getAutoData,
    		getAutoDataEmail:getAutoDataEmail,
    		getAutoDataUnit:getAutoDataUnit,
    		getAutoDataPartner:getAutoDataPartner,
    		DoSearch:DoSearch,
    		saveSettlementRight:saveSettlementRight,
    		getRoleId:getRoleId
    };

    return factory;
    
    function DoSearch(object) {
    	return Restangular.all(RestEndpoint.HSHC_SEARCH_SERVICE_URL).post(object);
    }

    function fetchAll(object) {
    	return Restangular.all(RestEndpoint.HSHC_AUTH_SERVICE_URL).post(object);
    }
    
    function getAutoData(object) {
    	return Restangular.all(RestEndpoint.GET_AUTO_DATA_SERVICE_URL).post(object);
    }
    
    function getAutoDataEmail(object) {
    	return Restangular.all(RestEndpoint.GET_AUTO_DATA_EMAIL_SERVICE_URL).post(object);
    }
    
    function getAutoDataPartner(object) {
    	return Restangular.all(RestEndpoint.GET_AUTO_DATA_PARTNER_SERVICE_URL).post(object);
    }
    
    function getAutoDataUnit(object) {
    	return Restangular.all(RestEndpoint.GET_AUTO_DATA_UNIT_SERVICE_URL).post(object);
    }
    
    function getConstructions(object) {
    	return Restangular.all(RestEndpoint.GETCONSTRT_SERVICE_URL).post(object);
    	
    }
    
    function getListEmployee(object) {
    	return Restangular.all(RestEndpoint.GET_EMPLOYEE_SERVICE_URL).post(object);
    	
    }
    
    function addSettlementRight(object) {
    	return Restangular.all(RestEndpoint.ADD_NEW_SETTLEMENT_SERVICE_URL).post(object);
    	
    }
    
    function saveSettlementRight(object) {
    	return Restangular.all(RestEndpoint.SAVE_SETTLEMENT_SERVICE_URL).post(object);
    	
    }
    
    function deleteSettlementRight(object) {
    	return Restangular.all(RestEndpoint.DELETE_SETTLEMENT_SERVICE_URL).post(object);
    	
    }
    
    function getListChucVu(object) {
    	return Restangular.all(RestEndpoint.GET_LIST_CHUCVU_SERVICE_URL).post(object);
    	
    }
    
    function getRoleId() {
		return Restangular.all(
				RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
						+ "/getRoleId").getList();
	}

}]);