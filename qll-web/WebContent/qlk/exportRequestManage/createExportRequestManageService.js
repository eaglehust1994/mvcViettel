angular.module('MetronicApp').factory('createExportRequestManageService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
//	    		addImportRequirement:addImportRequirement,
//	    		searchListShipmentCode:searchListShipmentCode,
//	    		getShipmentGoodsInfoByCode:getShipmentGoodsInfoByCode,
//	    		getGoodsInfoBeforeWarrantyByCode:getGoodsInfoBeforeWarrantyByCode,
//	    		getGoodsInfoAfterWarrantyByCode:getGoodsInfoAfterWarrantyByCode,
//	    		getGoodsInfoFromConstructionByCode:getGoodsInfoFromConstructionByCode,
//	    		getGoodsInfoFromDepartmentByCode:getGoodsInfoFromDepartmentByCode,
//	    		getGoodsInfoFromLoanByCode:getGoodsInfoFromLoanByCode,
//	    		getStockTransGoodsInfoByCode:getStockTransGoodsInfoByCode,
	    		getForExtOrderCheckboxes:getForExtOrderCheckboxes,
	    		getReasons:getReasons,
	    		getForUsers:getForUsers
	    };
	    
	    function getForExtOrderCheckboxes(obj) {
			return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/getForComboBox").post(obj);
		}
	 
	    function getForUsers(obj){
	    	return Restangular.all(RestEndpoint.SYS_USER_SERVICE_URL + "/sysAllUsersQLK").post(obj);
	    }
	    
	    function getReasons(obj) {
	    	return Restangular.all(RestEndpoint.REASON_URL + "/getForComboBox").post(obj);
		}
	    
	     return factory;
	 
	    

	}]);