angular.module('MetronicApp').factory('exportThucXuatTheoKyService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_QLTS_THUC_XUAT_THEO_KY_SERVICE_URL;
	 	var serviceUrl1 =RestEndpoint.TBL_TYPE_PXK;
//	 	var orderGoodsUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
	 	//var data=[{reasonId:1,code:'fix',name:'test'}]
	    var factory = {	       
			getGoodsDetailByOrderId : getGoodsDetailByOrderId,
			doSearch:doSearch,
			updateChiNhanh:updateChiNhanh,
			exportExcelGrid:exportExcelGrid,
			exportTHDoiSoatVatTu:exportTHDoiSoatVatTu,
			checkMaHdxl:checkMaHdxl,
			exportExcelGrid1:exportExcelGrid1,
			};
	 
	     return factory;
	 
	     
		function getGoodsDetailByOrderId(obj) {
	    	return Restangular
			.all(serviceUrl + "/getGoodsDetailByOrderId").post(obj);
		}
		 function updateChiNhanh(obj) {
	            return Restangular.all(serviceUrl+"/updateChiNhanh").post(obj); 	 
	        }
		 
		 function checkMaHdxl(obj) {
	            return Restangular.all(serviceUrl1+"/checkMaHdxl").post(obj); 	 
	        }
		
		function doSearch() {
	    	return Restangular
			.one(serviceUrl + "/getAll").get();
		}
		function exportExcelGrid(obj) {
	    	return Restangular
			.all(serviceUrl + "/exportExcelTongHopTheoNV").post(obj);
		}
		function exportExcelGrid1(obj) {
	    	return Restangular
			.all(serviceUrl + "/exportExcelTongHopTheoDV").post(obj);
		}
		
		function exportTHDoiSoatVatTu(obj) {
	    	return Restangular
			.all(serviceUrl1 + "/exportTHDoiSoatVatTu").post(obj);
		}
	    
		
	}]);