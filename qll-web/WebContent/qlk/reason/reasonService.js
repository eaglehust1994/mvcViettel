
angular.module('MetronicApp').factory('reasonService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
	        remove:remove,
	        createReason:createReason,
	        updateReason:updateReason,
	        doSearch : doSearch,
	        getApply : getApply
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createReason(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateReason(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }

	    function getApply(obj)
	    {
	    	return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL +"/getForComboBox").post(obj); 	 
	    }
	}]);
