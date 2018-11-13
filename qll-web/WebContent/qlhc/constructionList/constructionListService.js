angular.module('MetronicApp').factory('constructionListService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){

	
	
	
	var factory = {
	        
			openEstimatesWorkItemsForm : openEstimatesWorkItemsForm,
			openEstimatesWorkItemsFormAccept : openEstimatesWorkItemsFormAccept,
	    };

    return factory;

    
    
    function openEstimatesWorkItemsForm(scope) {
        return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_SEARCH_URL).getOne(); 	 
    }
    function openEstimatesWorkItemsFormAccept(searchAccept) {
        return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_SEARCH_ACCEPT_URL).post(searchAccept); 	 
    }
    
    

}]);