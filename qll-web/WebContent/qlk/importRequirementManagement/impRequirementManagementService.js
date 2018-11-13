angular.module('MetronicApp').factory('impReqManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.REASON_URL;
	 	var data=[{reasonId:1,code:'fix',name:'test'}]
	    var factory = {
	        getDetail:getDetail,
	        remove:remove,
	        getOrderDetailById : getOrderDetailById,
	        getForOrderCheckboxes:getForOrderCheckboxes,
	        getReasonForCombobox:getReasonForCombobox,
	        setData : setData,
			getData : getData,
			getForExportGrid:getForExportGrid,
			exportSerial : exportSerial,
	    };
	 
	     return factory;
	 
	    function getDetail(id){
	    	return data;
	    }

	    function remove(data){
	    	return Restangular.all(RestEndpoint.ORDER_SERVICE_URL+"/removerOrder").post(data)
	    }
	    
	    function getOrderDetailById(obj) {
	    	return Restangular
			.all(RestEndpoint.ORDER_SERVICE_URL+ "/getOrderDetail").post(obj);
		}
	    
	    function getForOrderCheckboxes(obj) {
			return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/doSearch").post(obj);
		}
	    
	    function getReasonForCombobox(obj) {
			return Restangular.all(RestEndpoint.REASON_URL + "/getForComboBox").post(obj);
		}
	    function setData(data) {
			this.data = data;
		}
		function getData() {
			return this.data;
		}
		
		function getForExportGrid(obj) {
			return Restangular.all(RestEndpoint.ORDER_SERVICE_URL + "/doSearchImportRequirement").post(obj);
		}
	    
		function exportSerial(obj){
			return Restangular.all(RestEndpoint.ORDER_SERVICE_URL + "/exportSerial").post(obj);
		}
	    
	}]);