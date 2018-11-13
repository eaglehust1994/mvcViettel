
angular.module('MetronicApp').factory('xlCvPtkService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.QL_CV_PTK;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        updateHdTd:updateHdTd,
	        doSearch:doSearch,
	        xuatBCLuong:xuatBCLuong,
	        xuatBCTH:xuatBCTH,
	        updatePathFileScan:updatePathFileScan,
	        updatePathFileExcel:updatePathFileExcel,
	        updatePathFileQtk:updatePathFileQtk,
	        updatePathFileQtDt:updatePathFileQtDt,
	        saveAddCv:saveAddCv,
	        xuatBCTHCV:xuatBCTHCV,
	        xuatBCTheoDauViec:xuatBCTheoDauViec,
	        deleteObj:deleteObj,
	        deleteListObj:deleteListObj,
	        importFile:importFile,
	        exportRowAll:exportRowAll
	    };
	   
	     return factory;
	     
	    function saveAddCv(obj) {
            return Restangular.all(serviceUrl+"/saveAddCv").post(obj); 	 
        }
	    function xuatBCTHCV() {
            return Restangular.all(serviceUrl+"/xuatBCTHCV").post(); 	 
        }
	    function updateHdTd(obj) {
            return Restangular.all(serviceUrl+"/updateHdTd").post(obj); 	 
        }
	    function exportRowAll(obj) {
            return Restangular.all(serviceUrl+"/exportRowAll").post(obj); 	 
        }
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function xuatBCLuong(obj) {
            return Restangular.all(serviceUrl+"/xuatBCLuong").post(obj); 	 
        }
	    
	    function xuatBCTH(obj) {
            return Restangular.all(serviceUrl+"/xuatBCTH").post(obj); 	 
        }
	    function importFile(obj) {
            return Restangular.all(serviceUrl+"/importFile").post(obj); 	 
        }
	    function updatePathFileScan(obj) {
            return Restangular.all(serviceUrl+"/updatePathFileScan").post(obj); 	 
        }
	    function updatePathFileExcel(obj) {
            return Restangular.all(serviceUrl+"/updatePathFileExcel").post(obj); 	 
        }
	    
	    function updatePathFileQtk(obj) {
            return Restangular.all(serviceUrl+"/updatePathFileQtk").post(obj); 	 
        }
	    function updatePathFileQtDt(obj) {
            return Restangular.all(serviceUrl+"/updatePathFileQtDt").post(obj); 	 
        }
	    function xuatBCTheoDauViec(obj) {
            return Restangular.all(serviceUrl+"/xuatBCTheoDauViec").post(obj); 	 
        }
	    function deleteObj(obj) {
            return Restangular.all(serviceUrl+"/deleteObj").post(obj); 	 
        }
	    function deleteListObj(obj) {
            return Restangular.all(serviceUrl+"/deleteListObj").post(obj); 	 
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
