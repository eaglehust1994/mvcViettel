
angular.module('MetronicApp').factory('stockDailyImportExportServiceRest', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.STOCK_DAILY_IMPORT_EXPORT_URL;
	    var factory = {
	        doSearch : doSearch,
	        exportpdf:exportpdf
	    };
	 
	     return factory;
	    
//	    Hàm tìm kiếm
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
//	    Hàm xuất excel, pdf
	    function exportpdf(obj) {
	    	var deferred = $q.defer();
              $http({
                    url: RestEndpoint.BASE_SERVICE_URL + "reportServiceRest"+"/exportPdf",
                    dataType: 'json',
                    method: 'POST',
                    data: obj,
                    headers: {
                        "Content-Type": "application/json"
                    },
                    responseType : 'arraybuffer',//THIS IS IMPORTANT
                }).success(function(data){
            	  deferred.resolve(data); 
              })
              .error(function(data){
            	  deferred.reject(data);
              });
             return deferred.promise;
        }
	}]);
