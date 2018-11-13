angular.module('MetronicApp').factory('tinhLuongDayMayService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_LUONG_DAY_MAY;
	 	var serviceUrl1 =RestEndpoint.TBL_TYPE_PXK;
//	 	var orderGoodsUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
	 	//var data=[{reasonId:1,code:'fix',name:'test'}]
	    var factory = {	  
	    		luongDayMay:luongDayMay,
			};
	 
	     return factory;
	     function luongDayMay(obj) {
	            return Restangular.all(serviceUrl+"/luongDayMay").post(obj); 	 
	        }
		  
	     
		
	}]);