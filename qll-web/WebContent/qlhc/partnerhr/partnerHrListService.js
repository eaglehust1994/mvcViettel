angular.module('MetronicApp').factory('partnerHrService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
    var factory = {
        fetchAllPartnerHr: fetchAllPartnerHr,
        createPartnerHr: createPartnerHr,
        updatePartnerHr: updatePartnerHr,
        deleteList : deleteList,
        getCompletionFolder : getCompletionFolder
    };
 
    return factory;
    
    function fetchAllPartnerHr(obj) {   	
    	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL +"/search").post(obj); 	   
    }
    
    function createPartnerHr(partnerHr) {
    	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL).post(partnerHr);
    }
  
    function updatePartnerHr(partnerHr) {
    	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/put").post(partnerHr);
    }
  	 
    function deleteList(ids){
    	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/deleteList" + "/put").post(ids);
    }
    
    function getCompletionFolder(scope){
    	return Restangular.one(RestEndpoint.COMPLETION_PARTNER_FOLDER_URL).get();
    }
    
}]);