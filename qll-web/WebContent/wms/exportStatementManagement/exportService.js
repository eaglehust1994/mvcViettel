/**
 * 
 */
angular.module('MetronicApp').factory('exStaManaService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	    		getStockTransById : getStockTransById,
	    		getStockTransDetailById:getStockTransDetailById,
		        getReasonForComboBox:getReasonForComboBox,
		        remove: remove,
		        getForExportGrid:getForExportGrid,
		        getForExportDetailGrid:getForExportDetailGrid,
		        realExport:realExport,
		        importExcel:importExcel,
		        loadDetail:loadDetail,
		        getOrderDetailById:getOrderDetailById,
		        updateStockTran:updateStockTran,
		        downloadStockTransErrorExcel:downloadStockTransErrorExcel,
				downloadTemplate:downloadTemplate,
				getForExtOrderCheckboxes:getForExtOrderCheckboxes,
		        getListDeatil:getListDeatil,
				exportExcelBKVT:exportExcelBKVT
		        
	    };
	    
	    function updateStockTran(obj) {
            return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+"/updateImportStockTran").post(obj); 	 
        }
	     
		 function getForExtOrderCheckboxes(obj) {
				return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/doSearch").post(obj);
			}
	    function importExcel(item){
	    	return Restangular.all(RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+ "/importStockTrans").post(item);
	    }
	    function getOrderDetailById(obj) {
	    	return Restangular
			.all(RestEndpoint.ORDER_SERVICE_URL+ "/getOrderDetail").post(obj);
		}
	    
	    function realExport(item) {
	    	return Restangular
			.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/realExportNote").post(item);
		}
	    function loadDetail(item){
	    	return Restangular.all(RestEndpoint.STOCK_TRANS_DETAIL_SERIAL_SERVICE_URL + "/doSearchGoodsDetailSerial").post(item);;
	    }
	    
	    function  getForExportDetailGrid(obj){
	     	return Restangular
			.all(RestEndpoint.STOCK_TRANS_DETAIL_SERIAL_SERVICE_URL+ "/doSearchGoodsDetailForExportNoteByStockTransDetailId").post(obj);
	    }
	    
	     function getStockTransById(id) {
		    	return Restangular
				.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/getStockTransDetail").post(id);
			}
	     
	     function getForExportGrid(obj) {
				return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL + "/doSearchExportStatement").post(obj);
			}
	     
	     function getStockTransDetailById(dataId) {
		    	return Restangular
				.all(RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+ "/getStockTransDetail").post(dataId);
			}
	     
	     function remove(data){
		    	return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+"/removeExportStockTrans").post(data)
		    }
	   
		  function getReasonForComboBox(obj) {
		    	return Restangular
				.all(RestEndpoint.REASON_URL+ "/getForComboBox").post(obj);
			}
		  function downloadStockTransErrorExcel(obj) {
				return Restangular.all(RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+ "/exportStockTransExcelError").post(obj);
			}
		    
		    return factory;
		    
		  function getListDeatil(id) {
				return Restangular.all(RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+ "/getListStockTransDetail").post(id);
			}
		function downloadTemplate(obj) {
				return Restangular
						.all(
								RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL
										+ "/exportExcelTemplate")
						.post(obj);
			}

		function exportExcelBKVT(ids) {
				return Restangular.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/exportExcelBKVT").post(ids);
			}

	}]);