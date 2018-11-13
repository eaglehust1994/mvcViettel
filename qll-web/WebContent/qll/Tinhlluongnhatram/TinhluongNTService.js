
angular.module('MetronicApp').factory('TinhluongNTService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_LUONG_NTRAM;
	 	
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        remove:remove,
	        doSearch:doSearch,
	        exportExcelGrid:exportExcelGrid,
			downloadImportTemplate:downloadImportTemplate,
			tinhluong:tinhluong
	    };
	   
	     return factory;
	 
	    function remove(obj) {
	    	return Restangular.all(serviceUrl+"/orderChangeGoods/remove").post(obj);
        }
	    
	    function tinhluong(obj) {
            return Restangular.all(serviceUrl+"/tinhluong").post(obj); 	 
        }
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function exportExcelGrid(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
        }
		function downloadImportTemplate() {
            return Restangular.all(serviceUrl+"/downloadImportTemplate").post(); 	 
        }
	    
	}]);
