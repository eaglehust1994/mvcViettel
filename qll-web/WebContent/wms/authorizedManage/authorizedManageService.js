angular.module('MetronicApp').factory('authManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.USER_ROLE_SERVICE_URL;
	    var factory = {
	        getDetail:getDetail,
	        remove:remove,
	        doSearch:doSearch,
	        doSearchRole:doSearchRole,
			doSearchUserRoleData:doSearchUserRoleData,
	        addRole:addRole,
	        deleteUserRoleData:deleteUserRoleData,
	       insertUserRoleData:insertUserRoleData,
	    };
	 
	     return factory;
	 
	    function getDetail(id){
	    	return data;
	    }

	    function remove(obj) {
            return Restangular.all(serviceUrl + "/remove").post(obj); 	 
        }
	    function doSearch(obj){
	    	return Restangular
			.all(serviceUrl + "/doSearchSysRole").post(obj);
	    }
	    function doSearchRole(obj){
	    	return Restangular
			.all(serviceUrl + "/doSearchRole").post(obj);
	    }
	    function addRole(obj){
	    	return Restangular
			.all(serviceUrl + "/addRole").post(obj);
	    }
	    function deleteUserRoleData(obj){
	    	return Restangular
	    	.all(serviceUrl +"/deleteUserRoleData").post(obj);
	    }
	    function insertUserRoleData(obj){
	    	return Restangular
	    	.all(serviceUrl +"/insertUserRoleData").post(obj);
	    }
		function doSearchUserRoleData(data)
	     {
		    	return Restangular.all(serviceUrl+"/doSearchUserRoleData").post(data);
	     }
	}]);