
angular.module('MetronicApp').factory('phatKhacDayMayService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_DM_THONG_TIN_LOI_DAY_MAY_URL;
	 	
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        remove:remove,
	        doSearch:doSearch,
	        exportExcelGrid:exportExcelGrid,
	        downloadImportTemplate:downloadImportTemplate
	    };
	   
	     return factory;
	 
	    function remove(obj) {
	    	return Restangular.all(serviceUrl+"/orderChangeGoods/remove").post(obj);
        }
	    
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function getAll() {
            return Restangular.all(serviceUrl+"/getAll").post(); 	 
        }
	    
	    function exportExcelGrid(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
        }
	    function downloadImportTemplate() {
            return Restangular.all(serviceUrl+"/downloadImportTemplate").post(); 	 
        }

	}]);
