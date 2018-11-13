angular.module('MetronicApp').factory('createExNoteService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var RserviceUrl = RestEndpoint.REASON_URL;
	 	var STDservice = RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL;
	 	var OserviceUrl = RestEndpoint.ORDER_SERVICE_URL;
	 	var OGserviceUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
		var OGDserviceUrl =  RestEndpoint.ORDER_GOOD_DETAIL_SERVICE_URL;
	    var factory = {
	    		remove:remove,
	 	        getReasonForComboBox:getReasonForComboBox,
	 	        getOrder: getOrder,
	 	        getForExportGrid:getForExportGrid,
	 	       doSearchGoodsForExportOrder:doSearchGoodsForExportOrder,
	 	       //doSearchGoodsSerials: doSearchGoodsSerials,
	 	       insertDataToNote:insertDataToNote,
	 	      createExportNote : createExportNote,
	 	     downloadTemplate:downloadTemplate,
	 	    getOrderDetailById : getOrderDetailById,
	 	   getForExtOrderCheckboxes:getForExtOrderCheckboxes,
		   downloadStockTransErrorExcel:downloadStockTransErrorExcel
	    };
	    
	     return factory;
	     
		 function downloadStockTransErrorExcel(obj) {
				return Restangular.all(RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+ "/exportStockTransExcelError").post(obj);
			}
		 
	     function getForExtOrderCheckboxes(obj) {
				return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/doSearch").post(obj);
			}
	     
	     function getOrderDetailById(obj) {
		    	return Restangular
				.all(RestEndpoint.ORDER_SERVICE_URL+ "/getOrderDetail").post(obj);
			}
	     
	     function insertDataToNote(obj) {
				return Restangular.all(STDservice + "/addMoreExportNote").post(obj);
			}
	     
	     function createExportNote(obj) {
		    	return Restangular
				.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/saveInforImport").post(obj);
			}
	     
	     function doSearchGoodsSerials(obj) {
				return Restangular.all(OGDserviceUrl + "/doSearchGoodsDetailForExportReqById").post(obj);
			}
	     
	     function getForExportGrid(obj) {
				return Restangular.all(OserviceUrl + "/doSearchExportRequirement").post(obj);
			}
	     
	     function getOrder(id){
	    	 return Restangular.all(OserviceUrl+"/getOrderDetail").post(id); 	 
	     } 
	     
	     function remove(data){
		    	return Restangular.all(OserviceUrl+"/removeExportOrder").post(data)
		  }
	     
	     function doSearchGoodsForExportOrder(data){
		    	return Restangular.all(OGserviceUrl+"/doSearchGoodsForExportOrder").post(data)
		  }
	   
		    function getReasonForComboBox(obj) {
		    	return Restangular
				.all(RserviceUrl + "/getForComboBox").post(obj);
			}
		    
		    function downloadTemplate(obj) {
				return Restangular
						.all(
								OGserviceUrl
										+ "/exportExcelTemplate")
						.post(obj);
			}

	}]);

