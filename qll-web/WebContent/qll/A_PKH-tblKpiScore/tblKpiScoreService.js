
angular.module('MetronicApp').factory('tblKpiScoreService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_KPI_SCORE;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        
	    	listAllDepartment:listAllDepartment,
	    	infoDetailByDoSearch:infoDetailByDoSearch,
	        deleteObj:deleteObj,
	        updateObj:updateObj,
	        insertObj:insertObj,
	        decImportFile:decImportFile,
	        decExportFile:decExportFile,
	        genExportFile:genExportFile
	       
	        
	    };
	   
	     return factory;
	     
	    
	    
	     
	    function listAllDepartment(obj) {
            return Restangular.all(serviceUrl+"/ListAllDepartment").post(obj); 	 
        }
	    function infoDetailByDoSearch(obj) {
            return Restangular.all(serviceUrl+"/InfoDetailByDoSearch").post(obj); 	 
        }
	    function deleteObj(obj) {
            return Restangular.all(serviceUrl+"/delete").post(obj); 	 
        }
	    
	    function updateObj(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	    
	    function insertObj(obj) {
            return Restangular.all(serviceUrl+"/insert").post(obj); 	 
        }
	    
	    function decImportFile(obj) {
            return Restangular.all(serviceUrl+"/decidedImportFile").post(obj); 	 
        }
	    
	    function decExportFile(obj) {
            return Restangular.all(serviceUrl+"/decidedExportFile").post(obj); 	 
        }
	    
	    function genExportFile(obj) {
            return Restangular.all(serviceUrl+"/generalExportFile").post(obj); 	 
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
