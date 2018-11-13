angular.module('MetronicApp')
		.factory('stockSignConfigurationService',['$http','$q','RestEndpoint','Restangular','$kWindow',
		function($http, $q, RestEndpoint, Restangular, $kWindow) {
			var serviceUrl = RestEndpoint.STOCK_SIGN_SERVICE_URL;
			var factory = {
				doSearchBusinessType : doSearchBusinessType,
				addStockSignConfiguration : addStockSignConfiguration,
				updateStockSignConfiguration : updateStockSignConfiguration,
				getDataByID : getDataByID,
				getDataSign : getDataSign,
			};

			return factory;

//			Hàm tìm kiếm Loại nghiệp vụ cần trình ký
			function doSearchBusinessType(obj) {
				return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL + "/doSearch").post(obj);
			}
			
//			Hàm lấy danh sách người ký của kho
			function getDataByID(obj) {
				return Restangular.all(RestEndpoint.CONFIG_SIGN_VOFFICE_SERVICE_URL + "/configSignVoffice/getDataByID")
						.post(obj);
			}
			
//			Hàm thêm mới người ký
			function addStockSignConfiguration(obj) {
				return Restangular.all(RestEndpoint.CONFIG_SIGN_VOFFICE_SERVICE_URL + "/configSignVoffice/add")
						.post(obj);
			}
			
//			Hàm update người ký
			function updateStockSignConfiguration(obj) {
				return Restangular.all(RestEndpoint.CONFIG_SIGN_VOFFICE_SERVICE_URL + "/configSignVoffice/update")
						.post(obj);
			}
			
			function getDataSign(obj) {
				return Restangular.all(RestEndpoint.CONFIG_SIGN_VOFFICE_SERVICE_URL + "/configSignVoffice/getDataSign")
						.post(obj);
			}
		} ]);