
angular.module('MetronicApp').factory('phatKhacService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_PHAT_KHAC;
	 	
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        remove:remove,
	        createStock:createStock,
	        getall:getall,
	        doSearch:doSearch,
	        importPhatKhac:importPhatKhac,
	        exportExcelGrid:exportExcelGrid,
	        downloadImportTemplate:downloadImportTemplate,
	    };
	   
	     return factory;
	 
	    function remove(obj) {
	    	return Restangular.all(serviceUrl+"/orderChangeGoods/remove").post(obj);
        }
	    
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function exportExcelGrid(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
        }
	    
	  
	    function createStock(obj) {
            return Restangular.all(serviceUrl+"/orderChangeGoods/add").post(obj); 	 
        }
	    function getall() {
            return Restangular.all(serviceUrl+"/getAllDLHaTang").post(); 	 
        }
	    
	    function importPhatKhac(item) {
			return Restangular.all(serviceUrl + "/importPhatKhac").post(item);
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
	    
		 function downloadImportTemplate() {
	            return Restangular.all(serviceUrl+"/downloadImportTemplate").post(); 	 
	        }
	}]);
