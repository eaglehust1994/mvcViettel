
angular.module('MetronicApp').factory('biddingPackageService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.BIDDING_PACKAGE_SERVICE_URL;
	    var factory = {
	        remove:remove,
	        createAppParam:createAppParam,
	        updateAppParam:updateAppParam,
	        doSearch : doSearch,
	        getAll: getAll,
	        exportpdf:exportpdf,
	        getTemplateHtml:getTemplateHtml
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createAppParam(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateAppParam(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function getAll(obj) {
            return Restangular.all(serviceUrl+"/getAll").post(obj); 	 
        }
	    
	    function exportpdf(obj) {
	    	var deferred = $q.defer();
             Restangular.all(serviceUrl+"/exportPdf").post(obj).then(
						function(data) {
							var binarydata= new Blob([data],{ type:'application/pdf'});
					        kendo.saveAs({dataURI: binarydata, fileName: "báo cáo" + '.pdf'});
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
             
             return deferred.promise;
        }
	    
	    function getTemplateHtml(url){
	    	return $templateCache.get(url);
	    }

	}]);
