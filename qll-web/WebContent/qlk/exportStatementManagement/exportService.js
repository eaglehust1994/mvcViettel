/**
 * 
 */
angular.module('MetronicApp').factory('exStaManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	    	getStockTransDetailById : getStockTransDetailById,
	    	getStockTransDetailSerialById:getStockTransDetailSerialById,
	        getReasonForComboBox:getReasonForComboBox,
	        remove: remove
	    };
	 
	     function getStockTransDetailById(id) {
		    	return Restangular
				.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/getStockTransDetail").post(id);
			}
	     
	     function getStockTransDetailSerialById(dataId) {
		    	return Restangular
				.all(RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+ "/getStockTransDetailById").post(dataId);
			}
	     
	     function remove(data){
		    	return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+"/removeStockTrans").post(data)
		    }
	   
		  function getReasonForComboBox(obj) {
		    	return Restangular
				.all(RestEndpoint.REASON_URL+ "/getForComboBox").post(obj);
			}
		    
		    return factory;

	}]);