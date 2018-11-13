angular.module('MetronicApp').factory('createExportRequestManageService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
	    		saveImportReq:saveImportReq,
	     		getForExtOrderCheckboxes:getForExtOrderCheckboxes,
	    		getReasons:getReasons,
	    		getUsers:getUsers,
	    		getContracts:getContracts,
	    		getConstructions:getConstructions,
	    		getPartners:getPartners,
	    		getProjects:getProjects,
	    		doSearchImportNote:doSearchImportNote,
	    		getlistOrderGood:getlistOrderGood,
	    		remove : remove,
	    		getReasonName:getReasonName,
	    		getPartnerName:getPartnerName,
	    		getConstructionForAutoComplete:getConstructionForAutoComplete,
	    		getPartnerForAutoComplete:getPartnerForAutoComplete,
	    		getStocks:getStocks,
	    		updateImportReq:updateImportReq,
	    		getPartnerById:getPartnerById,
	    		getConstructionByCode:getConstructionByCode,
				getGoodsById:getGoodsById,
				downloadErrorExcel:downloadErrorExcel,
				getPatternGoodsByOrderPatternId : getPatternGoodsByOrderPatternId
	    };
	  
	    
	    function getStocks(obj) {
			return Restangular
					.all(RestEndpoint.STOCK_URL + "/getForAutoCompleteStock").post(obj);
		}
	    
	    function getPartnerName(obj) {
			return Restangular
					.all(RestEndpoint.CAT_OWNER_PARTNER_URL + "/getPartnerById").post(obj);
		}
	    
	    function getReasonName(obj) {
	    	return Restangular.one(RestEndpoint.REASON_URL + "/" + obj + "").get();
		}
	    
	    function getProjects(obj) {
			return Restangular
					.all("iProjInvestProjectRsServiceRest/iProjInvestProject/getForAutoComplete").post(obj);
		}
	    
	    function saveImportReq(obj) {
			return Restangular
					.all(RestEndpoint.ORDER_SERVICE_URL+ "/saveImportReq").post(obj);
		}
	    
	    function updateImportReq(obj) {
			return Restangular
					.all(RestEndpoint.ORDER_SERVICE_URL+ "/updateImportReq").post(obj);
		}
	    
	     function getConstructions(oj){
			return Restangular.all(RestEndpoint.CAT_OWNER_CONSTRUCTION_URL + "/getForAutoCompleteII").post(oj);
	    }
	     
	     function getConstructionForAutoComplete(oj){
				return Restangular.all(RestEndpoint.CAT_OWNER_CONSTRUCTION_URL + "/getForAutoComplete").post(oj);
		    }
	     
	     function getConstructionByCode(oj){
				return Restangular.all(RestEndpoint.CAT_OWNER_CONSTRUCTION_URL + "/getConstructionByCode").post(oj);
		    }
	    
	    function getPartners(oj){
			return Restangular.all(RestEndpoint.CAT_OWNER_PARTNER_URL + "/getForAutoCompleteII").post(oj);
	    }
	    
	    function getPartnerForAutoComplete(oj){
			return Restangular.all(RestEndpoint.CAT_OWNER_PARTNER_URL + "/getForAutoComplete").post(oj);
	    }
	    
	    function getPartnerById(oj){
			return Restangular.all(RestEndpoint.CAT_OWNER_PARTNER_URL + "/getPartnerById").post(oj);
	    }
	    
	    
	    
	    function remove(id)
	    {
	    	return Restangular.all(RestEndpoint.ORDER_GOOD_SERVICE_URL+"/deleteorderGood").post(id);
	    }
	    
	      function getContracts(obj){
			return Restangular.all(RestEndpoint.I_CNT_CONTRACT + "/getForAutoComplete").post(obj);
	    }
	    
	    function getlistOrderGood()
	    {
	    	return Restangular.all(RestEndpoint.ORDER_GOOD_SERVICE_URL).customGET();	 
	    }
	    
	    function doSearchImportNote(obj){
			return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL + "/getStockTransForAutoComplete").post(obj);
	    }
	    
	    function getForExtOrderCheckboxes(obj) {
			return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/getForComboBox").post(obj);
		}
	 
	     function getUsers(obj){
	    	return Restangular.all("sysUserServiceRest/user/sysAllUserswms").post(obj);
	    }
	    
	    function getReasons(obj) {
	    	return Restangular.all(RestEndpoint.REASON_URL + "/getForComboBox").post(obj);
		}
		
	    function getGoodsById(obj) {
	    	return Restangular.all(RestEndpoint.GOODS_SERVICE_URL + "/getGoodsById").post(obj);
		}
	    function downloadErrorExcel(obj) {
			return Restangular
					.all(
							RestEndpoint.ORDER_SERVICE_URL
									+ "/exportExcelErrorXK")
					.post(obj);
		}
		function getPatternGoodsByOrderPatternId(obj) {
	    	return Restangular.all("orderPatternGoodServiceRest/getPatternGoodsByOrderPatternId").post(obj);
		}

	     return factory;
	 
	    

	}]);