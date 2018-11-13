
angular.module('MetronicApp').factory('exportPXKService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_TYPE_PXK;
	 	
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        getall:getall,
	        exportPhatKpi:exportPhatKpi,
	        exportExcelGrid:exportExcelGrid,
	    };
	   
	     return factory;
	     
	    function getall() {
            return Restangular.all(serviceUrl+"/getAllDLHaTang").post(); 	 
        }
	    function exportPhatKpi(obj) {
            return Restangular.all(serviceUrl+"/exportPhatKpi").post(obj); 	 
        }
	    function exportExcelGrid(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
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
