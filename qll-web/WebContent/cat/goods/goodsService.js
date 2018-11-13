
angular.module('MetronicApp').factory('goodsService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.CAT_GOOD_SERVICE_URL;
	    var factory = {
	        remove:remove,
	        createcatUnit:createcatUnit,
	        updatecatUnit:updatecatUnit,
	        doSearch : doSearch,
	        getAll: getAll,
	        exportpdf:exportpdf,
	        getAllUnit:getAllUnit
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createcatUnit(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updatecatUnit(obj) {
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
	    
	    function getAllUnit(obj) {
	    	if(obj == null || obj == {})
	    		obj = {page: 1, pageSize: 10};
	    	console.log(obj);
			return Restangular
					.all(
							RestEndpoint.CAT_UNIT_SERVICE_URL+"/doSearch")
					.post(obj);
		}

	}]);
