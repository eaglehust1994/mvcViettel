angular.module('MetronicApp').factory('bgvttbacService', ['$http', '$q', 'Constant', 'RestEndpoint', 'Restangular', function ($http, $q, Constant, RestEndpoint, Restangular) {

    var SERVICE_URI = Constant.SERVICE_URL + 'cProfitCenterRsService/cProfitCenter';
    var SERVICE_GET_ALL = '';
    var factory = {
    		getConstructions: getConstructions	,
    		getEmployeeByRole: getEmployeeByRole,
    		getwareExpCmdByConstrt: getwareExpCmdByConstrt,
    		deleteWareExpCmd: deleteWareExpCmd,
    		getListAMaterial: getListAMaterial,
    		addListAmaterial: addListAmaterial,
    		addAmaterial: addAmaterial,
    		getListAMaterialHandOverMerList:getListAMaterialHandOverMerList,
    		exportFile: exportFile,
    		exportFileDoc: exportFileDoc,
    		setItem:setItem,
    		getThoiGianBanGiao:getThoiGianBanGiao,
			getItem:getItem,
			getEmployeeNameRole:getEmployeeNameRole,
			exportList:exportList,
			exportListDoc:exportListDoc,
			getwareExpCmdByPxk:getwareExpCmdByPxk,
			getEmployeeByRoleShiper:getEmployeeByRoleShiper
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
    	return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);
    }
    
    function getEmployeeByRoleShiper(object) {
    	return Restangular.all(RestEndpoint.GET_EMPLOYEE_ROLL_SERVICE_URL).post(object);
    	
    }
    
    function getwareExpCmdByConstrt(object) {
    	return Restangular.all(RestEndpoint.GET_WARE_EXP_SERVICE_URL).post(object);
    	
    }
    
    
    function getwareExpCmdByPxk(object) {
    	return Restangular.all(RestEndpoint.GET_WARE_EXP_NOTE_CODE_SERVICE_URL).post(object);
    	
    }
    
    function deleteWareExpCmd(object) {
    	return Restangular.all(RestEndpoint.DELETE_WARE_EXP_SERVICE_URL).post(object);
    	
    }
    
    function getListAMaterial(object) {
    	return Restangular.all(RestEndpoint.GET_LIST_MATERIAL_SERVICE_URL).post(object);
    	
    }
    
    function addListAmaterial(object) {
    	return Restangular.all(RestEndpoint.ADD_LIST_MATERIAL_SERVICE_URL).post(object);
    	
    }
    
    function addAmaterial(object) {
    	return Restangular.all(RestEndpoint.ADD_MATERIAL_SERVICE_URL).post(object);
    	
    }
    
    function exportFile(object) {
    	return Restangular.all(RestEndpoint.EXPORT_SERVICE_URL).post(object);
    	
    }
    
    function exportFileDoc(object) {
    	return Restangular.all(RestEndpoint.EXPORT_DOC_SERVICE_URL).post(object);
    	
    }
    
    function exportList(object) {
    	return Restangular.all(RestEndpoint.EXPORT_LIST_SERVICE_URL).post(object);
    	
    }
    
    function exportListDoc(object) {
    	return Restangular.all(RestEndpoint.EXPORT_LIST_DOC_SERVICE_URL).post(object);
    	
    }
    
    function getListAMaterialHandOverMerList(object) {
    	return Restangular.all(RestEndpoint.GET_LIST_AMATERIAL_MER_LIST_SERVICE_URL).post(object);
    	
    }
    
    function getThoiGianBanGiao(object) {
    	return Restangular.all(RestEndpoint.GET_THOIGIAN_BANGIAO_SERVICE_URL).post(object);
    	
    }
    
    function getEmployeeNameRole(object) {
    	return Restangular.all(RestEndpoint.GET_THOIGIAN_BANGIAO_SERVICE_URL).post(object);
    	
    }
    
   
   
    
}]);
