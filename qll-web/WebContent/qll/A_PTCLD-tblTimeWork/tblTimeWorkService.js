
angular.module('MetronicApp').factory('tblTimeWorkService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_TIME_WORK;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        
	        doSearch:doSearch,
	     
	        importFile:importFile,
	        exportTimeLate:exportTimeLate
	     
	    };
	   
	     return factory;
	     
	    
	    
	     
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	   
	    function importFile(obj) {
            return Restangular.all(serviceUrl+"/importFile").post(obj); 	 
        }
	    
	    function exportTimeLate(obj) {
            return Restangular.all(serviceUrl+"/exportTimeLate").post(obj); 	 
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
