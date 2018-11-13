
angular.module('MetronicApp').factory('stockGoodsKpiServiceRest', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.STOCK_GOODS_KPI_URL;
	    var factory = {
	        doSearch : doSearch
	    };
	 
	     return factory;
	     
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }

	}]);
