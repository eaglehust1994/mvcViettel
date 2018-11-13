
angular.module('MetronicApp').factory('taskService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TASK;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        
	        doSearch:doSearch,
	        checkInfoUser:checkInfoUser,
	        updateTaskStatus :updateTaskStatus 
	     
	    };
	   
	     return factory;
	     
	    
	    
	     
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function checkInfoUser(obj) {
            return Restangular.all(serviceUrl+"/checkInfoUser").post(obj); 	 
        }
	   
	    function updateTaskStatus(obj) {
            return Restangular.all(serviceUrl+"/updateTaskStatus").post(obj); 	 
        }
	   

//	    function getChartEmpYear(obj) {
//            return Restangular.all(serviceUrl+"/getChartEmpYear").post(obj); 	 
//        }
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
