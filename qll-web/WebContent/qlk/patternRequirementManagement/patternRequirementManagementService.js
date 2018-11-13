angular.module('MetronicApp').factory('patternRequirementManagementService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.ORDER_PATTERN_URL;
	    var factory = {
	    		 remove:remove,
	    		 addCreatNew:addCreatNew,
	    		 getForComboBox1:getForComboBox1,
	    		 getForComboBox2:getForComboBox2,
	    		 getAllAppParam:getAllAppParam,
	    		 update:update,
	    		 doSearch : doSearch,
	    		 viewDetail:viewDetail,
	    		 removeDetail:removeDetail
	    };
	 
	     return factory;
	     function remove(obj) {
	            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
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
		   function viewDetail(obj) {
			   return Restangular.all(serviceUrl+"/viewDetail").post(obj);
			}
		   function removeDetail(obj){
			   return Restangular.all(serviceUrl+"/removeDetail").post(obj);
		   }
	 
	}]);