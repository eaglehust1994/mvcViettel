
angular.module('MetronicApp').factory('taskGroupService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TASK_GROUP;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        
	        doSearch:doSearch,
	        saveTaskGroup:saveTaskGroup,
	        deleteObj:deleteObj,
	        deleteListObj: deleteListObj,
	        updateTaskGroup:updateTaskGroup,
	        getDeptId:getDeptId
	     
	    };
	   
	     return factory;
	     
	    
	    
	     
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function getDeptId(obj) {
            return Restangular.all(serviceUrl+"/getDeptId").post(obj); 	 
        }
	    function saveTaskGroup(obj) {
            return Restangular.all(serviceUrl+"/saveTaskGroup").post(obj); 	 
        }
	    function deleteObj(obj) {
            return Restangular.all(serviceUrl+"/deleteObj").post(obj); 	 
        }
	    function deleteListObj(obj) {
            return Restangular.all(serviceUrl+"/deleteListObj").post(obj); 	 
        }
	    function updateTaskGroup(obj) {
            return Restangular.all(serviceUrl+"/updateTaskGroup").post(obj); 	 
        }
	    
	    function setCode(code) {
			this.code = code;
		}
		function getCode() {
			return this.code;
		}
		function setData(data) {
			this.data = data;
		}
		function getData() {
			return this.data;
		}
	    

	}]);
