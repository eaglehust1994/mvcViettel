
	angular.module('MetronicApp').factory('suggetAdPaymentService', ['$http','$q','RestEndpoint', 'Restangular', 'Constant','$rootScope', 
		function($http, $q, RestEndpoint, Restangular, Constant,$rootScope){
	 
	    var service = RestEndpoint.ADVANCE_PAYMENT_PROPOSAL_Url;
		var factory = {
	         createAdPayment : createItem,
	         updateAdPayment : updateItem,
	         fetchAllAdPayment : fetchAllItem,
	         getAdPaymentByConstructId : getAdPaymentByConstructId
	    };
	    return factory;
		//-----------------------------------------------------------------
	    function fetchAllItem() {	    	
	 	    	return Restangular.all(service).getList();	 	   
	    }
	    function getAdPaymentByConstructId(id){
	    	var obj = Restangular.one(service+"/constructId",id).get();
	    	// return Restangular.one(service+"/constructId/"+id).getList();
	    	return obj;
	    }
	 //    function doSearch(searchCriteria) {	    	
	 //    	return Restangular.all(service + "/search").post(searchCriteria);
	 //    }
	 
	    function createItem(adPayment) {
	    	return Restangular.all(service).post(adPayment);
	    }
	 
	    function updateItem(adPayment) {
	    	return Restangular.all(service +"/put").post(adPayment);
	    }
	 //    function deleteItem(id) {
	 //    	return Restangular.one(service, id).remove();
	 //    }
	    
	 //    function openDepartmentForm(scope) {
  //           return Restangular.all(RestEndpoint.C_DEPARTMENT_SERVICE_URL).getList(); 	 
  //       }
	    	   	    
	}]);
