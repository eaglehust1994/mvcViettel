angular.module('MetronicApp').factory('impNoteManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
	    	getStockTransDetailById : getStockTransDetailById,
	    	getForOrderCheckboxes:getForOrderCheckboxes,
	    	getForExportGrid:getForExportGrid,
	    	removeStockTrans:removeStockTrans,
	    	updateImportNote:updateImportNote,
	    	realImportNote:realImportNote,
	    };
	    
	    function getStockTransDetailById(id) {
	    	return Restangular
			.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/getStockTransDetail").post(id);
		}
	    
	    function getForOrderCheckboxes(obj) {
			return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/doSearch").post(obj);
		}
	    
	    function getForExportGrid(obj) {
			return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL + "/doSearchImportNote").post(obj);
		}
	    
	    function removeStockTrans(obj) {
			return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL + "/removeStockTrans").post(obj);
		}
	    
	    function updateImportNote(obj) {
			return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL + "/updateImportNote").post(obj);
		}
	    
	    function realImportNote(obj) {
			return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL + "/realImportNote").post(obj);
		}
	 
	     return factory;
	 
	     
	     

	}]);