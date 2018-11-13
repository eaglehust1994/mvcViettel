/**
 * @author hailh10
 */
angular.module('MetronicApp').factory('CatBankBranchBakService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 		     
	    var factory = {
	        fetchAll: fetchAll,
	        create: create,
	        update: update,
	        deleteObject: deleteObject,
	        doSearch: doSearch,
	        deleteList:deleteList,	        
			getById: getById
	    };
	 
	    return factory;
	    
		
		
		function getById(id){
	    	return Restangular.one(RestEndpoint.CAT_BANK_BRANCH_BAK_SERVICE_URL + "/findById?id=" + id).get();
	    }
	 
	    function fetchAll() {
	 	    return Restangular.all(RestEndpoint.CAT_BANK_BRANCH_BAK_SERVICE_URL).getList();	 	   
	    }
	    
	    function doSearch(searchCriteria) {	    	
	    	return Restangular.all(RestEndpoint.CAT_BANK_BRANCH_BAK_SERVICE_URL + "/search").post(searchCriteria);
	    }
	 
	    function create(object) {
	    	return Restangular.all(RestEndpoint.CAT_BANK_BRANCH_BAK_SERVICE_URL).post(object);
	    }
	 
	    function update(object) {
	    	return Restangular.all(RestEndpoint.CAT_BANK_BRANCH_BAK_SERVICE_URL+"/update").post(object);
	    }
	 
	    function deleteObject(id) {
	    	return Restangular.one(RestEndpoint.CAT_BANK_BRANCH_BAK_SERVICE_URL+"/delete", id).post();
	    }
               
	    function deleteList(ids){
	    	return Restangular.all(RestEndpoint.CAT_BANK_BRANCH_BAK_SERVICE_URL + "/deleteList").post(ids);
	    }	  
	}]);
