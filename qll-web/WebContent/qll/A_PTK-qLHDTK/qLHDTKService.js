
angular.module('MetronicApp').factory('qLHDTKService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.KQ_HD_TK;
// var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	    	doSearch:doSearch,
	        insertHd:insertHd,
	        updateHdTd:updateHdTd,
	        updateHdPopSubmit:updateHdPopSubmit,
	        exportExcelGrid:exportExcelGrid,
	        xuatBCLuong:xuatBCLuong,
	        xuatBCTH:xuatBCTH,
	        updatePath:updatePath,
	        exportAllHD:exportAllHD,
	        deleteObj:deleteObj,
	        deleteListObj:deleteListObj,
	        importFile:importFile,
	        importFileTD:importFileTD
	    };
	   
	     return factory;
	     
	     
	     function doSearch(obj) {
	            return Restangular.all("kqHdTkRsServiceRest/doSearch").post(obj); 	 
	        }
	     
	    function deleteObj(obj) {
	            return Restangular.all(serviceUrl+"/deleteObj").post(obj); 	 
	        }
	    function deleteListObj(obj) {
            return Restangular.all(serviceUrl+"/deleteListObj").post(obj); 	 
        }
	    
	    function insertHd(obj) {
            return Restangular.all(serviceUrl+"/insertHd").post(obj); 	 
        }
	    function updateHdTd(obj) {
            return Restangular.all(serviceUrl+"/updateHdTd").post(obj); 	 
        }
	    function updateHdPopSubmit(obj) {
            return Restangular.all(serviceUrl+"/updateHdPopSubmit").post(obj); 	 
        }
	    function exportExcelGrid(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
        }
	    
	    function xuatBCLuong(obj) {
            return Restangular.all(serviceUrl+"/xuatBCLuong").post(obj); 	 
        }
	    
	    function importFile(obj) {
            return Restangular.all(serviceUrl+"/importFile").post(obj); 	 
        } 
	    
	    function importFileTD(obj) {
            return Restangular.all(serviceUrl+"/importFileTD").post(obj); 	 
        }
	    function xuatBCTH(obj) {
            return Restangular.all(serviceUrl+"/xuatBCTH").post(obj); 	 
        }
	    function updatePath(obj) {
            return Restangular.all(serviceUrl+"/updatePath").post(obj); 	 
        }
	    function exportAllHD(obj) {
            return Restangular.all(serviceUrl+"/exportAllHD").post(obj); 	 
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
