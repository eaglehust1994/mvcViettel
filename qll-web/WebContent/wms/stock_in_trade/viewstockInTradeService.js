
angular.module('MetronicApp').factory('viewstockInTradeService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.STOC_DAILY_REMAIN_URL;
	 	var serviceUrlStock = RestEndpoint.STOCK_GOODS_TOTAL_URL;
	 	var serviceGoodTypeUrrl = RestEndpoint.GOODS_TYPE_URL;
	 	
	    var factory = {
	        remove:remove,
	        createStock:createStock,
	        updateStock:updateStock,
	        doSearch : doSearch,
	        doSearchTotal: doSearchTotal,
	        goodType: goodType,
	        doSearchGoodTotal: doSearchGoodTotal,
	        exportCard:exportCard,
			doSearchStockGood: doSearchStockGood
	        
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function createStock(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateStock(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrlStock+"/doSearch").post(obj); 	 
        }

	    function doSearchTotal(obj) {
            return Restangular.all(serviceUrlStock+"/doSearchTotal").post(obj); 	 
        }
	    function doSearchGoodTotal(obj) {
            return Restangular.all(serviceUrlStock+"/doSearchGoodTotal").post(obj); 	 
        }
	    function goodType(obj){
	    	 return Restangular.all(serviceGoodTypeUrrl+"/getGoodTypeList").post(obj); 
	    }
	    
	    function exportCard(obj){
	    	 return Restangular.all(serviceUrl+"/exportCard").post(obj); 
	    }

		function doSearchStockGood(obj){
	    	 return Restangular.all(serviceUrlStock+"/doSearchStockGood").post(obj); 
	    }
	}]);
