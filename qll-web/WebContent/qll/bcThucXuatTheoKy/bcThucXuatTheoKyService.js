angular.module('MetronicApp').factory('bcThucXuatTheoKyService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_QLTS_THUC_XUAT_THEO_KY_SERVICE_URL;
//	 	var orderGoodsUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
	 	//var data=[{reasonId:1,code:'fix',name:'test'}]
	    var factory = {	       
			getGoodsDetailByOrderId : getGoodsDetailByOrderId,
			doSearch:doSearch,
			updateChiNhanh:updateChiNhanh,
			};
	 
	     return factory;
	 
	     
		function getGoodsDetailByOrderId(obj) {
	    	return Restangular
			.all(serviceUrl + "/getGoodsDetailByOrderId").post(obj);
		}
		 function updateChiNhanh(obj) {
	            return Restangular.all(serviceUrl+"/updateChiNhanh").post(obj); 	 
	        }
		
		function doSearch() {
	    	return Restangular
			.one(serviceUrl + "/getAll").get();
		}
	    
	}]);