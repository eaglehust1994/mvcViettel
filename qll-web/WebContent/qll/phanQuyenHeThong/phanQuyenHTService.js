
angular.module('MetronicApp').factory('phanQuyenHTService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl1 = RestEndpoint.TBL_ROLES;
	 	var serviceUrl2 = RestEndpoint.TBL_ROLES_USER;
	 	var serviceUrl=RestEndpoint.TBL_USER_ROLE;
	 	
	    var factory = {
	        getRoles:getRoles,
	        insertRoles:insertRoles,
	        insertRoles8:insertRoles8,
	        getRolesInfo:getRolesInfo,
	    };
	   
	     return factory;
	 
	    
	    function getRoles() {
	    	return Restangular.all(serviceUrl1+"/selectRoles").post();
        }
	    
	    function insertRoles(obj) {
	    	return Restangular.all(serviceUrl2+"/insertRoles").post(obj);
        }
	    
	    function insertRoles8() {
	    	return Restangular.all(serviceUrl2+"/insertRoles8").post();
        }
	    function getRolesInfo(obj) {
	    	return Restangular.all(serviceUrl+"/getRoles").post(obj);
        }

	}]);
