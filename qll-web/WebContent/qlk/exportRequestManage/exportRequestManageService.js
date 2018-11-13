angular.module('MetronicApp').factory('exReqManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.ORDER_SERVICE_URL;
	 	var orderGoodsUrl = RestEndpoint.ORDER_GOODS_URL;
	 	//var data=[{reasonId:1,code:'fix',name:'test'}]
	    var factory = {
	        remove:remove,
	        doSearch:doSearch,
	        getReasonForComboBox:getReasonForComboBox,
	        dulplicate:  dulplicate,
	        getOrder: getOrder,
	        doGoodsSearch: doGoodsSearch
	    };
	 
	     return factory;
	 
	     function doGoodsSearch(obj) {
	    	 return Restangular.all(orderGoodsUrl+"/doSearchGoodsForExportReq").post(obj); 	 
		}
	     
	     function getOrder(id){
	    	 return Restangular.all(serviceUrl+"/getOrder").post(id); 	 
	     }
	     
	    function dulplicate(data){
	    	return Restangular.all(serviceUrl+"/repExportReq").post(data)
	    }

	    function remove(data){
	    	return Restangular.all(serviceUrl+"/removeExportOrder").post(data)
	    }
	    
	    function doSearch(obj){
	    	return Restangular
			.all(serviceUrl + "/doSearchExportRequirement").post(obj);
	    }
   
	    function getReasonForComboBox(obj) {
	    	return Restangular
			.all(RestEndpoint.REASON_URL+ "/getForComboBox").post(obj);
		}
	    
	}]);