//angular.module('MetronicApp').factory('kiDonViService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
//	 	var serviceUrl = RestEndpoint.KI_DON_VI_URL;
////	 	var orderGoodsUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
//	 	//var data=[{reasonId:1,code:'fix',name:'test'}]
//	    var factory = {	       
//			getGoodsDetailByOrderId : getGoodsDetailByOrderId,
//			doSearchKiDv:doSearchKiDv
//			};
//	 
//	     return factory;
//	 
//	     
//		function getGoodsDetailByOrderId(obj) {
//	    	return Restangular
//			.all(serviceUrl + "/getGoodsDetailByOrderId").post(obj);
//		}
//		function doSearchKiDv() {
//	    	return Restangular
//			.one(serviceUrl + "/getAll").get();
//		}
//	    
//	}]);