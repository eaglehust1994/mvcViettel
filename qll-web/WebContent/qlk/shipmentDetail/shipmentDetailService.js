
angular.module('MetronicApp').factory('shipmentDetailService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	    		doSearchFile : doSearchFile,
	    		doSearchMap : doSearchMap,
	    		getDoMapDetail : getDoMapDetail,
	    		doSearchTAX : doSearchTAX,
	    		getDataKCS : getDataKCS,
	    		getOrderByShipment : getOrderByShipment,
	    		getStockByOrder : getStockByOrder,
	    };
	 
	     return factory;
	     
	     function doSearchFile(data)
	     {
		    	return Restangular.all(RestEndpoint.ATTCHMENT_URL + "/doSearch").post(data);
	     }
	     
	     function doSearchMap(data)
	     {
		    	return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL + "/doSearchMap").post(data);
	     }
	     
	     function getDoMapDetail(data)
	     {
		    	return Restangular.all(RestEndpoint.SHIPMENT_GOODS_DETAIL_URL + "/getDoMapDetail").post(data);
	     }
	     
	     function doSearchTAX(data)
	     {
		    	return Restangular.all(RestEndpoint.SHIPMENT_TAX_URL + "/doSearchTAX").post(data);
	     }
	     function getDataKCS(data)
	     {
		    	return Restangular.all(RestEndpoint.OBJECT_REFERENCE_URL + "/getDataKCS").post(data);
	     }
	     
	     function getOrderByShipment(data)
	     {
		    	return Restangular.all(RestEndpoint.ORDER_SERVICE_URL + "/getOderByShipment").post(data);
	     }
	     
	     function getStockByOrder(data)
	     {
		    	return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL + "/getStockByOrder").post(data);
	     }
	}]);
