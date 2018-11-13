angular.module('MetronicApp').factory('exReqManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.ORDER_SERVICE_URL;
	 	var orderGoodsUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
	 	//var data=[{reasonId:1,code:'fix',name:'test'}]
	    var factory = {
	        remove:remove,
	        doSearch:doSearch,
	        getReasonForComboBox:getReasonForComboBox,
	        dulplicate:  dulplicate,
	        getOrder: getOrder,
	        doGoodsSearch: doGoodsSearch,
	        getForExportGrid:getForExportGrid,
	        saveGoodsList:saveGoodsList,
	        setData : setData,
			getData : getData,
			getForExtOrderCheckboxes:getForExtOrderCheckboxes,
			getGoodsDetailByOrderId : getGoodsDetailByOrderId

			};
	 
	     return factory;
	 
	     function getForExtOrderCheckboxes(obj) {
				return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/getForComboBox").post(obj);
			}
	     
	     function setData(data) {
				this.data = data;
			}
			function getData() {
				return this.data;
			}
	     
	     function saveGoodsList(objList) {
	    	 return Restangular.all(orderGoodsUrl+"/saveGoods").post(objList); 	 
		}
	     
	     function getForExportGrid(obj) {
	    	 return Restangular.all(serviceUrl+"/doSearchExportRequirement").post(obj); 	 
		}
	     
	     function doGoodsSearch(obj) {
	    	 return Restangular.all(orderGoodsUrl+"/doSearchGoodsForExportReq").post(obj); 	 
		}
	     
	     function getOrder(id){
	    	 return Restangular.all(serviceUrl+"/getOrderDetail").post(id); 	 
	     }
	     
	    function dulplicate(data){
	    	return Restangular.all(serviceUrl+"/repExportReq").post(data)
	    }

	    function remove(data){
	    	return Restangular.all(serviceUrl+"/removerOrder").post(data)
	    }
	    
	    function doSearch(obj){
	    	return Restangular
			.all(serviceUrl + "/doSearchExportRequirement").post(obj);
	    }
   
	    function getReasonForComboBox(obj) {
	    	return Restangular
			.all(RestEndpoint.REASON_URL+ "/getForComboBox").post(obj);
		}
		function getGoodsDetailByOrderId(obj) {
	    	return Restangular
			.all(serviceUrl + "/getGoodsDetailByOrderId").post(obj);
		}
		
	    
	}]);