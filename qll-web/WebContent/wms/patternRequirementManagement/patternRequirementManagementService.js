angular.module('MetronicApp').factory('patternRequirementManagementService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.ORDER_PATTERN_URL;
	    var factory = {
	    		 remove:remove,
	    		 addCreatNew:addCreatNew,
	    		 update:update,
	    		 doSearch : doSearch,
	    		 viewDetail:viewDetail,
	    };
	 
	     return factory;
	     
//	    Hàm xoá dữ liệu
	     function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
         }
		    
//	    Hàm thêm mới dữ liệu
	    function addCreatNew(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
		    
//		Hàm update dữ liệu
	    function update(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	    
//		Hàm tìm kiếm dữ liệu
	   function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
		    
//		Hàm xem chi tiết
	   function viewDetail(obj) {
		   return Restangular.all(serviceUrl+"/viewDetail").post(obj);
		}
	}]);