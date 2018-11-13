
angular.module('MetronicApp').factory('stockService', ['$http', '$q','RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow
		){
	 	var serviceUrl = RestEndpoint.STOCK_URL;
	 	var serviceAppParamUrl = RestEndpoint.APP_PARAM_SERVICE_URL;
	 	var serviceStockCellUrl = RestEndpoint.STOCK_CELL_URL;
	    var factory = {
	        remove:remove,
	        createStock:createStock,
	        updateStock:updateStock,
	        doSearch : doSearch,
	        getAllAppParam: getAllAppParam,
	        getForComboBox1: getForComboBox1,
	        getForComboBox2: getForComboBox2,
	        removeStockCell: removeStockCell,
	        addListStockCell : addListStockCell,
	        getAllStockConfig : getAllStockConfig,
	        getStockConfig : getStockConfig,
	        createStockConfig : createStockConfig,
	        updateStockConfig : updateStockConfig,
	        getChangeAreaByStock:getChangeAreaByStock,
	        saveListStockCell:saveListStockCell,
	    };
	 
	     return factory;
	 
	    function remove(obj) {
            return Restangular.all(serviceUrl+"/remove").post(obj); 	 
        }
	    
	    function removeStockCell(ids) {
            return Restangular.all(serviceStockCellUrl+"/deleteList").post(ids); 	 
        }
	    function addListStockCell(lstStockCell)
	    {
	    	return Restangular.all(serviceStockCellUrl+"/lstStockCell").post(lstStockCell); 	
	    }
	    function createStock(obj) {
            return Restangular.all(serviceUrl+"/add").post(obj); 	 
        }
	    
	    function updateStock(obj) {
            return Restangular.all(serviceUrl+"/update").post(obj); 	 
        }
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    
	    function getForComboBox1(obj) {
            return Restangular.all(serviceAppParamUrl+"/getForComboBox1").post(obj); 	 
        }
	    
	    function getForComboBox2(obj) {
            return Restangular.all(serviceAppParamUrl+"/getForComboBox").post(obj); 	 
        }
	    
	   function getAllAppParam(obj) {
            return Restangular.all(serviceAppParamUrl+"/getAll").post(obj); 	 
        }
	   function getStockConfig(id)
	   {
		   return Restangular.all(RestEndpoint.STOCK_DELIVERY_CONFIG_URL+"/getStockDeliveryConfig/"+id).post(); 
	   }
	   function getAllStockConfig()
	   {
		   return Restangular.all(RestEndpoint.STOCK_DELIVERY_CONFIG_URL+"/doSearch").post(); 
		   
	   }
	   function createStockConfig(obj)
	   {
		   return Restangular.all(RestEndpoint.STOCK_DELIVERY_CONFIG_URL+"/add").post(obj); 
		   
	   }
	   function updateStockConfig(obj)
	   {
		   return Restangular.all(RestEndpoint.STOCK_DELIVERY_CONFIG_URL+"/update").post(obj);
		   
	   }
	   function getChangeAreaByStock(obj) {
           return Restangular.all(serviceUrl+"/getChangeAreaByStock").post(obj); 	 
       }
	   
	   function saveListStockCell(obj) {
           return Restangular.all(serviceUrl+"/saveListStockCell").post(obj); 	 
       }
	   
	}]);
