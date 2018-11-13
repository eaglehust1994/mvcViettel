angular.module('MetronicApp').factory('creImpNoteService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	    		getOrderDetailById : getOrderDetailById,
	    		createImportNote:createImportNote,
	    		createAndRealImportNote:createAndRealImportNote,
	    		getForOrderCheckboxes:getForOrderCheckboxes,
	    		getForExportGrid:getForExportGrid,
	    		rejectOrder:rejectOrder,
	    };
	    
	    function getOrderDetailById(obj) {
	    	return Restangular
			.all(RestEndpoint.ORDER_SERVICE_URL+ "/getOrderDetail").post(obj);
		}
	    
	    function createImportNote(obj) {
	    	return Restangular
			.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/saveImportNote").post(obj);
		}
	    
	    function createAndRealImportNote(obj) {
	    	return Restangular
			.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/saveAndRealImportNote").post(obj);
		}
	    
	    function getForOrderCheckboxes(obj) {
			return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/doSearch").post(obj);
		}
	    
	    function getForExportGrid(obj) {
			return Restangular.all(RestEndpoint.ORDER_SERVICE_URL + "/doSearchForCreateImportNote").post(obj);
		}
	    
	    function rejectOrder(data){
	    	return Restangular.all(RestEndpoint.ORDER_SERVICE_URL+"/rejectOrder").post(data)
	    }
	     
		function importGoods(item) {
	        return Restangular.all(RestEndpoint.ORDER_GOOD_SERVICE_URL+ "/importGoods").post(item); 	 
	    }
		
		return factory;
	     

	}]);