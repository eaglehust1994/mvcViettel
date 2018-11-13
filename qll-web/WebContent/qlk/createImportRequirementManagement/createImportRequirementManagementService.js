angular.module('MetronicApp').factory('createImpReqManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
	    		saveImportReq:saveImportReq,
	    		searchListShipmentCode:searchListShipmentCode,
	    		getShipmentGoodsInfoByCode:getShipmentGoodsInfoByCode,
	    		getGoodsInfoBeforeWarrantyByCode:getGoodsInfoBeforeWarrantyByCode,
	    		getGoodsInfoAfterWarrantyByCode:getGoodsInfoAfterWarrantyByCode,
	    		getGoodsInfoFromConstructionByCode:getGoodsInfoFromConstructionByCode,
	    		getGoodsInfoFromDepartmentByCode:getGoodsInfoFromDepartmentByCode,
	    		getGoodsInfoFromLoanByCode:getGoodsInfoFromLoanByCode,
	    		getStockTransGoodsInfoByCode:getStockTransGoodsInfoByCode,
	    		getForOrderCheckboxes:getForOrderCheckboxes,
	    		getGoodsDetail:getGoodsDetail,
	    		getGoodsDetailStockTrans:getGoodsDetailStockTrans,
	    		getGoodsDetailShipment:getGoodsDetailShipment,
	    		updateImportReq:updateImportReq
	    };
	    function saveImportReq(obj) {
			return Restangular
					.all(RestEndpoint.ORDER_SERVICE_URL+ "/saveImportReq").post(obj);
		}
	    
	    function updateImportReq(obj) {
			return Restangular
					.all(RestEndpoint.ORDER_SERVICE_URL+ "/updateImportReq").post(obj);
		}
	    
	    function searchListShipmentCode(code) {
			return Restangular
					.all(RestEndpoint.SHIPMENT_URL+ "/searchListShipmentCode").post(code);
		}
	    
	    function getShipmentGoodsInfoByCode(code){
	    	return Restangular
			.all(RestEndpoint.SHIPMENT_GOODS_URL+ "/getGoodsInfoByCode").post(code);
	    }
	    
	    function getGoodsInfoBeforeWarrantyByCode(code){
	    	return Restangular
			.all(RestEndpoint.OBJECT_REFERENCE_GOODS_URL+ "/getGoodsInfoBeforeWarrantyByCode").post(code);
	    }
	    
	    function getGoodsInfoAfterWarrantyByCode(code){
	    	return Restangular
			.all(RestEndpoint.OBJECT_REFERENCE_GOODS_URL+ "/getGoodsInfoAfterWarrantyByCode").post(code);
	    }
	    
	    function getGoodsInfoFromConstructionByCode(code){
	    	return Restangular
			.all(RestEndpoint.OBJECT_REFERENCE_GOODS_URL+ "/getGoodsInfoFromConstructionByCode").post(code);
	    }
	    
	    function getGoodsInfoFromDepartmentByCode(code){
	    	return Restangular
			.all(RestEndpoint.OBJECT_REFERENCE_GOODS_URL+ "/getGoodsInfoFromDepartmentByCode").post(code);
	    }
	    
	    function getGoodsInfoFromLoanByCode(code){
	    	return Restangular
			.all(RestEndpoint.OBJECT_REFERENCE_GOODS_URL+ "/getGoodsInfoFromLoanByCode").post(code);
	    }
	    
	    function getStockTransGoodsInfoByCode(code){
	    	return Restangular
			.all(RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+ "/getGoodsInfoFromAlternativeStockByCode").post(code);
	    }
	    
	    function getGoodsDetail(obj){
	    	return Restangular
			.all(RestEndpoint.OBJECT_REFERENCE_GOODS_DETAIL_URL+ "/getGoodsDetail").post(obj);
	    }
	    
	    function getGoodsDetailStockTrans(obj){
	    	return Restangular
			.all(RestEndpoint.STOCK_TRANS_DETAIL_SERIAL_SERVICE_URL+ "/getGoodsDetail").post(obj);
	    }
	    
	    function getGoodsDetailShipment(obj){
	    	return Restangular
			.all(RestEndpoint.SHIPMENT_GOODS_DETAIL_URL+ "/getGoodsDetail").post(obj);
	    }
	    
	    function getForOrderCheckboxes(obj) {
			return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/doSearch").post(obj);
		}
	    
	     return factory;
	 
	    

	}]);