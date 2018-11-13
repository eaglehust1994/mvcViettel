
angular.module('MetronicApp').factory('importPXKService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_TYPE_PXK;
		var serviceUrl1 = RestEndpoint.TBL_QLTS_THUC_XUAT_THEO_KY_SERVICE_URL;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        getall:getall,
	        importPhatKpi:importPhatKpi,
	        exportExcelGrid:exportExcelGrid,
	        updateChiNhanh:updateChiNhanh,
	        updateSLByNV:updateSLByNV,
	        updatePathImg:updatePathImg,
	        doSearchByPXK1:doSearchByPXK1,
	        updateNhanVien:updateNhanVien,
	    };
	   
	     return factory;
	     
	    function getall() {
            return Restangular.all(serviceUrl+"/getAllDLHaTang").post(); 	 
        }
	    function importPhatKpi(obj) {
            return Restangular.all(serviceUrl+"/importPhatKpi").post(obj); 	 
        }
	    function exportExcelGrid(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
        }
	    function updateChiNhanh(obj) {
            return Restangular.all(serviceUrl1+"/updateChiNhanh").post(obj); 	 
        }
	    function updateSLByNV(obj) {
            return Restangular.all(serviceUrl1+"/updateSLByNV").post(obj); 	 
        }
	    function updatePathImg(obj) {
            return Restangular.all(serviceUrl+"/updatePathImg").post(obj); 	 
        }
	    
	    function doSearchByPXK1(obj) {
            return Restangular.all(serviceUrl1+"/doSearchByPXK").post(obj); 	 
        }
	    function updateNhanVien(obj) {
            return Restangular.all(serviceUrl1+"/updateNhanVien").post(obj); 	 
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
