
angular.module('MetronicApp').factory('popupSearchService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.WORK_ITEM_QUOTA_SERVICE_URL;
	    var factory = {
	        remove:remove,
	        createworkItemQuota:createworkItemQuota,
	        updateworkItemQuota:updateworkItemQuota,
	        doSearch : doSearch,
	        getAll: getAll,
	        exportpdf:exportpdf,
	        getAllSysGroup:getAllSysGroup,
	        getAllConstructionType:getAllConstructionType,
	        getAllCatWorkItemType:getAllCatWorkItemType,
	        getSysUser:getSysUser,
	        getAllPartner:getAllPartner,
	        getTask:getTask,
	        saveListTaskQuota:saveListTaskQuota,
	        getTaskQuota:getTaskQuota,
	        updateListTaskQuota:updateListTaskQuota,
	        getTemplateFile:getTemplateFile
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createworkItemQuota(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateworkItemQuota(obj) {
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
	    
	    function getAllSysGroup(obj) {
	    	if(obj == null || obj == {})
	    		obj = {page: 1, pageSize: 10};
			return Restangular
					.all(
							RestEndpoint.SYS_GROUP_SERVICE_URL+"/doSearch")
					.post(obj);
		}
	    
	    function getAllConstructionType(obj) {
	    	if(obj == null || obj == {})
	    		obj = {page: 1, pageSize: 10};
			return Restangular
					.all(
							RestEndpoint.CAT_CONSTURCTION_TYPE_SERVICE_URL+"/doSearch")
					.post(obj);
		}
	    
	    function getAllCatWorkItemType(obj) {
	    	if(obj == null || obj == {} )
	    		obj = {page: 1, pageSize: 10};
			return Restangular
					.all(
							RestEndpoint.WORK_ITEM_TYPE_SERVICE_URL+"/doSearch")
					.post(obj);
		}
	    
	    function getTask(obj) {
	    	if(obj == null || obj == {} || !obj.catWorkItemTypeId)
	    		obj = {page: 1, pageSize: 10, catWorkItemTypeId : 0};
			return Restangular
				.all(
						RestEndpoint.CAT_TASK_SERVICE_URL+"/doSearch")
				.post(obj);
		}
	    
	    function getAllPartner(obj) {
	    	if(obj == null || obj == {})
	    		obj = {page: 1, pageSize: 10};
			return Restangular
					.all(
							RestEndpoint.CAT_PARTNER_SERVICE_URL+"/doSearch")
					.post(obj);
		}
	    
	    function getSysUser(obj) {
	    	if(obj == null || obj == {} )
	    		obj = {page: 1, pageSize: 10};
			return Restangular
					.all(
							RestEndpoint.IMS_SYS_USER_SERVICE_URL+"/doSearch")
					.post(obj);
		}
	    
	    function saveListTaskQuota(list){
	    	   return Restangular.all(RestEndpoint.TASK_QUOTA_SERVICE_URL+"/addList").post(list); 	 
	    }
	    
	    function getTaskQuota(obj) {
	    	if(obj == null || obj == {})
	    		obj = {page: 1, pageSize: 10};
			return Restangular
				.all(
						RestEndpoint.TASK_QUOTA_SERVICE_URL+"/doSearch")
				.post(obj);
		}
	    
	    function updateListTaskQuota(obj) {
            return Restangular.all(RestEndpoint.TASK_QUOTA_SERVICE_URL+"/updateList").post(obj); 	 
        }
	    
	    function getTemplateFile(fileName) {
            var result =  Restangular.all(serviceUrl+"/getTemplateFile?").post(fileName); 	 
            console.log(result);
            return result;
        }
	    
	    
	}]);
