
angular.module('MetronicApp').factory('tblKhBcKqCvService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_KH_BCKQCV;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        
	        doSearch:doSearch,
	        deleteObj:deleteObj,
	        deleteListObj:deleteListObj,
	        importFile:importFile,
	        updateKhKd:updateKhKd,
	        getChartDeptDay:getChartDeptDay,
	        getChartDeptMonth:getChartDeptMonth,
	        getChartMonth:getChartMonth
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
	    function updateKhKd(obj) {
            return Restangular.all(serviceUrl+"/updateKhKd").post(obj); 	 
        }
	    function importFile(obj) {
            return Restangular.all(serviceUrl+"/importFile").post(obj); 	 
        } 
	    function getChartDeptDay(obj) {
            return Restangular.all(serviceUrl+"/getChartDeptDay").post(obj); 	 
        }
	    
	    function getChartDeptMonth(obj) {
            return Restangular.all(serviceUrl+"/getChartDeptMonth").post(obj); 	 
        }
	    function getChartMonth(obj) {
            return Restangular.all(serviceUrl+"/getChartMonth").post(obj); 	 
        }
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
