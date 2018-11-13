
angular.module('MetronicApp').factory('shipmentGoodsService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	    		doSearchMap : doSearchMap,
	    		removeDetail:removeDetail,
	    		downloadTemplate:downloadTemplate,
	    		downloadErrorExcel:downloadErrorExcel,
	    		add:add
	    };
	 
	     return factory;
	     
	     function doSearchMap(data)
	     {
		    	return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL + "/doSearchMap").post(data);
	     }
	     function removeDetail(obj) {
	            return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL+"/remove").post(obj); 	 
	        }
	     function add(obj) {
	            return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL+"/add").post(obj); 	 
	        }
	     function downloadTemplate(obj) {
				return Restangular
						.all(
								RestEndpoint.SHIPMENT_GOODS_URL
										+ "/exportExcelTemplate")
						.post(obj);
			}
	     function downloadErrorExcel(obj) {
				return Restangular
						.all(
								RestEndpoint.SHIPMENT_GOODS_URL
										+ "/exportExcelError")
						.post(obj);
			}
	}]);
