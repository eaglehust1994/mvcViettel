
angular.module('MetronicApp').factory('GNVService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_GAN_NHIEM_VU;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	    	
	        doSearch:doSearch,
	        saveGanNv:saveGanNv,
	        saveAssignListTask:saveAssignListTask,
	        deleteObj:deleteObj,
	        deleteListObj:deleteListObj,
	        stringData:stringData,
	        DetailNV:DetailNV,
	        getEmpByWorkId:getEmpByWorkId,
	        updateGNV:updateGNV,
	        getDetailInfo:getDetailInfo
	    };
	   
	     return factory;
	     function stringData(p1){
	    	 return p1;
	     }
	    function saveGanNv(obj) {
	    
	    	
            return Restangular.all(serviceUrl+"/saveGanNv").post(obj); 	 
            
        }
	    function saveAssignListTask(obj) {
 	
            return Restangular.all(serviceUrl+"/saveAssignListTask").post(obj); 	 
            
        }
	   
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function getEmpByWorkId(obj) {
            return Restangular.all(serviceUrl+"/getEmpByWorkId").post(obj); 	 
        }
	    function updateGNV(obj) {
            return Restangular.all(serviceUrl+"/updateGNV").post(obj); 	 
        }
	    

	    function deleteObj(obj) {
            return Restangular.all(serviceUrl+"/deleteObj").post(obj); 	 
        }
	    function deleteListObj(obj) {
            return Restangular.all(serviceUrl+"/deleteListObj").post(obj); 	 
        }
	    
	    function DetailNV(obj) {
            return Restangular.all(serviceUrl+"/DetailNV").post(obj); 	 
        }
	    
	    function getDetailInfo(obj) {
            return Restangular.all(serviceUrl+"/getDetailInfo").post(obj); 	 
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
