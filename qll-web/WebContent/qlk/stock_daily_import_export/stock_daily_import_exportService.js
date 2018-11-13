
angular.module('MetronicApp').factory('stockDailyImportExportService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.STOCK_DAILY_IMPORT_EXPORT_URL;
	    var factory = {
	        doSearch : doSearch
	    };
	 
	     return factory;
	     
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }

	}]);
