angular.module('MetronicApp').factory('findSerialService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	        getForExportGrid:getForExportGrid,
	    };
	 
	    
	 
		function getForExportGrid(obj) {
			return Restangular.all(RestEndpoint.STOCK_GOODS_TOTAL_URL+"/stockGoodsSerial/doSearchFindSerial").post(obj); 	 
		}
		 return factory;
	}]);