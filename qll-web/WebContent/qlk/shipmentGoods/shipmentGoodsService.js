
angular.module('MetronicApp').factory('shipmentGoodsService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	    		doSearchMap : doSearchMap,
	    };
	 
	     return factory;
	     
	     function doSearchMap(data)
	     {
		    	return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL + "/doSearchMap").post(data);
	     }
	}]);
