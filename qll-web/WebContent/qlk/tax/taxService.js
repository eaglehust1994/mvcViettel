
angular.module('MetronicApp').factory('taxService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TAX_SERVICE_URL;
	    var factory = {
	        remove:remove,
	        createTax:createTax,
	        updateTax:updateTax
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createTax(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateTax(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/tax/doSearch").post(obj); 	 
        }

	}]);
