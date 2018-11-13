
angular.module('MetronicApp').factory('tblKhQlVcService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_KH_QLVC;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        
	        doSearch:doSearch,
	        deleteObj:deleteObj,
	        deleteListObj:deleteListObj,
	        updateKhNv:updateKhNv,
	        importFile:importFile,
	        getForAutoCompleteMaDv:getForAutoCompleteMaDv,
	        getChartMonth:getChartMonth,
//	        getChartDeptYear:getChartDeptYear,
	        getChartDept:getChartDept,
//	        getChartEmpYear:getChartEmpYear,
	        getChartEmpMonth:getChartEmpMonth
	     
	    };
	   
	     return factory;
	     
	    
	    
	     
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	   
	    function importFile(obj) {
            return Restangular.all(serviceUrl+"/importFile").post(obj); 	 
        }
	    
	    function deleteObj(obj) {
            return Restangular.all(serviceUrl+"/deleteObj").post(obj); 	 
        }
	    function deleteListObj(obj) {
            return Restangular.all(serviceUrl+"/deleteListObj").post(obj); 	 
        }
	    function updateKhNv(obj) {
            return Restangular.all(serviceUrl+"/updateKhNv").post(obj); 	 
        }
	    function importFile(obj) {
            return Restangular.all(serviceUrl+"/importFile").post(obj); 	 
        } 
	    function getForAutoCompleteMaDv(obj) {
            return Restangular.all(serviceUrl+"/getForAutoCompleteMaDv").post(obj); 	 
        }
	    function getChartMonth(obj) {
            return Restangular.all(serviceUrl+"/getChartMonth").post(obj); 	 
        }
	    
//	    function getChartDeptYear(obj) {
//            return Restangular.all(serviceUrl+"/getChartDeptYear").post(obj); 	 
//        }
	    
	    function getChartDept(obj) {
            return Restangular.all(serviceUrl+"/getChartDept").post(obj); 	 
        }
	    
	    function getChartEmpMonth(obj) {
            return Restangular.all(serviceUrl+"/getChartEmpMonth").post(obj); 	 
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
