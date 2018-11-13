
angular.module('MetronicApp').factory('shipmentPriceService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	//var serviceUrl = RestEndpoint.REASON_URL;
	    var factory = {
	    	setItem : setItem,
			getItem : getItem,
			doSearchMap : doSearchMap,
			createShipmentGoods :createShipmentGoods,
			getShipmentGoodsPrice : getShipmentGoodsPrice,
			getTax: getTax,
			getForExportGrid:getForExportGrid,
	    };
	  
	    return factory;
	    function setItem(data) {
			item = data;
		}

		function getItem() {
			return item;
		}
		
		function doSearchMap(data)
	     {
		    	return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL + "/doSearchMap").post(data);
	     }
		function createShipmentGoods(data){
			return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL + "/add").post(data);
		}

		function getShipmentGoodsPrice(data){
			return Restangular.all(RestEndpoint.SHIPMENT_GOODS_URL  + "/getShipmentGoodsPrice").post(data); 
		}
		function getTax(data){
			return Restangular.all(RestEndpoint.TAX_SERVICE_URL  + "/tax/doSearch").post(data); 
		}
		function getForExportGrid(obj) {
			return Restangular.all(RestEndpoint.SHIPMENT_URL + "/doSearchPrice").post(obj);
		}
	}]);
