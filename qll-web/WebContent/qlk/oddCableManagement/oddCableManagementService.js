angular.module('MetronicApp').factory('oddCableManagementService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.ODD_CABLE;
	 	var factory = {
	    		 remove:remove,
	    		 deleteOddCable:deleteOddCable,
	    		 addCreatNew:addCreatNew,
	    		 getForComboBox1:getForComboBox1,
	    		 getForComboBox2:getForComboBox2,
	    		 getAllAppParam:getAllAppParam,
	    		 doSearch : doSearch,
	    		 update:update
	    };
	 
	     return factory;
	     function remove(obj) {
	            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
	        }
	     function deleteOddCable(obj) {
	            return Restangular.all(serviceUrl+"/deleteOddCable").post(obj); 	 
	        }
		    
		    function addCreatNew(obj) {
	            return Restangular.all(serviceUrl+"/add").post(obj); 	 
	        }
		    function update(obj) {
	            return Restangular.all(serviceUrl+"/update").post(obj); 	 
	        }
		    
		   function doSearch(obj) {
	            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
	        }
		    
		    function getForComboBox1(obj) {
	            return Restangular.all(serviceAppParamUrl+"/getForComboBox").post(obj); 	 
	        }
		    
		    function getForComboBox2(obj) {
	            return Restangular.all(serviceAppParamUrl+"/getForComboBox").post(obj); 	 
	        }
		    
		   function getAllAppParam(obj) {
	            return Restangular.all(serviceAppParamUrl+"/getAll").post(obj); 	 
	        }
	 
	    

	}]);