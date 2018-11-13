
angular.module('MetronicApp').factory('catConstructionDeployService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.CAT_CONSTRUCTION_DEPLOY_SERVICE_URL;
	    var factory = {
	        remove:remove,
	        createCatConstructionDeploy:createCatConstructionDeploy,
	        updateCatConstructionDeploy:updateCatConstructionDeploy,
	        doSearch : doSearch,
	        getAll: getAll,
	        exportpdf:exportpdf
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createCatConstructionDeploy(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateCatConstructionDeploy(obj) {
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

	}]);
