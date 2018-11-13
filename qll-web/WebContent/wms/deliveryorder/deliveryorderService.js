
angular.module('MetronicApp').factory('deliveryOrderService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
	        remove:remove,
	        createAppParam:createAppParam,
	        updateAppParam:updateAppParam,
	        importGoods:importGoods,
//	        getOrderDetailById:getOrderDetailById,
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createAppParam(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateAppParam(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	 /*   function getOrderDetailById(obj) {
	    	return Restangular
			.all(RestEndpoint.ORDER_SERVICE_URL+ "/getOrderDetail").post(obj);
		}*/
	    
	    function importGoods(item) {
            return Restangular.all("/orderGoodsServiceRest/orderGoods/importGoods").post(item); 	 
        }

	}]);
