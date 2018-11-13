
angular.module('MetronicApp').factory('stockGoodService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	var serviceStockGoods = RestEndpoint.STOCK_GOODS_URL ;    
	var factory = {
			getstockGoodsDetail:getstockGoodsDetail,
			getstockGoodsDetailSerial:getstockGoodsDetailSerial
	    };
	 
	     return factory;
	    
	     
	   
	     function getstockGoodsDetail(data){
		    	return Restangular.all(serviceStockGoods+"/stockGoods/detailStockGoods").post(data); 
		    }
	     function getstockGoodsDetailSerial(data){
		    	return Restangular.all(serviceStockGoods+"/stockGoods/detailStockGoodsSerial").post(data); 
		    }
	}]);
