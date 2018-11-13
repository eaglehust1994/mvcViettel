angular.module('MetronicApp').factory('phatFcService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_PHAT_FC;
//	 	var orderGoodsUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
	 	//var data=[{reasonId:1,code:'fix',name:'test'}]
	    var factory = {	       
			doSearch:doSearch,
			exportPhatFc:exportPhatFc,
			downloadImportTemplate:downloadImportTemplate
			};
	 
	     return factory;
	 
	     
		function doSearch() {
	    	return Restangular
			.one(serviceUrl + "/getAll").get();
		}
		
		function exportPhatFc(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
        }
		function downloadImportTemplate() {
            return Restangular.all(serviceUrl+"/downloadImportTemplate").post(); 	 
        }
	    
	}]);