
angular.module('MetronicApp').factory('shipmentPriceService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	//var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
	    	setItem : setItem,
			getItem : getItem,
			doSearchMap : doSearchMap
	    };
	 
	    return factory;
	    function setItem(data) {
			item = data;
		}

		function getItem() {
			return item;
		}
		
		function doSearchMap(data)
	     {
		    	return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL + "/doSearchMap").post(data);
	     }
	}]);
