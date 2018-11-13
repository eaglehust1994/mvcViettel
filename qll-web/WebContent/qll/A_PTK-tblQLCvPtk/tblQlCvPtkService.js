
angular.module('MetronicApp').factory('tblQlCvPtkService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_QL_CV_PTK;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	    	updateStatus:updateStatus,
	        doSearch:doSearch,
	        listTasks:listTasks,
	        saveAddCv:saveAddCv,
	        deleteObj:deleteObj,
	        deleteListObj:deleteListObj,
	        importFile:importFile,
	        importFileCV:importFileCV,
	        updatePath:updatePath,
	        exportAllCV:exportAllCV,
	        reportForQTK:reportForQTK,
	        reportTotal:reportTotal,
	        updateCV1:updateCV1,
	        updateCV2:updateCV2,
	        updateCV3:updateCV3,
	        updateCV4:updateCV4,
	        updateCV5:updateCV5,
	        getChart:getChart,
	        updateCvPtk:updateCvPtk
	    };
	   
	     return factory;
	     function importFile(obj) {
	            return Restangular.all(serviceUrl+"/importFile").post(obj); 	 
	        } 
	     function importFileCV(obj) {
	            return Restangular.all(serviceUrl+"/importFileCV").post(obj); 	 
	        } 
	    function saveAddCv(obj) {
            return Restangular.all(serviceUrl+"/saveAddCv").post(obj); 	 
        }
	   
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function listTasks(obj) {
            return Restangular.all(serviceUrl+"/listTasks").post(obj); 	 
        }
	    function deleteObj(obj) {
            return Restangular.all(serviceUrl+"/deleteObj").post(obj); 	 
        }
	    function deleteListObj(obj) {
            return Restangular.all(serviceUrl+"/deleteListObj").post(obj); 	 
        }
	    
	    function updateStatus(obj) {
            return Restangular.all(serviceUrl+"/updateStatus").post(obj); 	 
        }
	  
	    function updatePath(obj) {
            return Restangular.all(serviceUrl+"/updatePath").post(obj); 	 
        }
	    function reportForQTK(obj) {
            return Restangular.all(serviceUrl+"/reportForQTK").post(obj); 	 
        }
	    function reportTotal(obj) {
            return Restangular.all(serviceUrl+"/reportTotal").post(obj); 	 
        }
	    function exportAllCV(obj) {
            return Restangular.all(serviceUrl+"/exportAllCV").post(obj); 	 
        }
	    function updateCvPtk(obj) {
            return Restangular.all(serviceUrl+"/updateCvPtk").post(obj); 	 
        }
	    function updateCV1(obj) {
            return Restangular.all(serviceUrl+"/updateCV1").post(obj); 	 
        }
	    function updateCV2(obj) {
            return Restangular.all(serviceUrl+"/updateCV2").post(obj); 	 
        }
	    function updateCV3(obj) {
            return Restangular.all(serviceUrl+"/updateCV3").post(obj); 	 
        }
	    function updateCV4(obj) {
            return Restangular.all(serviceUrl+"/updateCV4").post(obj); 	 
        }
	    function updateCV5(obj) {
            return Restangular.all(serviceUrl+"/updateCV5").post(obj); 	 
        }
	    
	    function getChart(obj) {
            return Restangular.all(serviceUrl+"/getChart").post(obj); 	 
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
