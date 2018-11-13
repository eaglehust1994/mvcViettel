angular.module('MetronicApp').factory(
		'qlNhanVienService',
		[
				'$http',
				'$q',
				'RestEndpoint',
				'Restangular',
				'$kWindow',
				function($http, $q, RestEndpoint, Restangular, $kWindow) {
					var serviceUrl = RestEndpoint.TBL_NHAN_VIEN_URL;
					var testUrl = RestEndpoint.TBL_NHAN_VIEN_URL;
					// var orderGoodsUrl = RestEndpoint.ORDER_GOOD_SERVICE_URL;
					// var data=[{reasonId:1,code:'fix',name:'test'}]
					var factory = {
						getGoodsDetailByOrderId : getGoodsDetailByOrderId,
						doSearchKiDv : doSearchKiDv,
						test : test,
						exportExcelGrid : exportExcelGrid,
						getDepartment:getDepartment,
					};

					return factory;

					function getGoodsDetailByOrderId(obj) {
						return Restangular.all(
								serviceUrl + "/getGoodsDetailByOrderId").post(
								obj);
					}
					function doSearchKiDv() {
						return Restangular.one(serviceUrl + "/getAll").get();
					}
					function test() {
						return Restangular.all(testUrl + "/getAll").get();
					}
					function exportExcelGrid(obj) {
						return Restangular.all(serviceUrl + "/exportExcelGrid")
								.post(obj);
					}
					function getDepartment(obj) {
						return Restangular.all("tblDanhMucServiceRest/doSearchDepartment").post(obj);
					}
					
					
				} ]);