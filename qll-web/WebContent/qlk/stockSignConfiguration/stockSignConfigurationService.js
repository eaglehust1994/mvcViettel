angular
		.module('MetronicApp')
		.factory(
				'stockSignConfigurationService',
				[
						'$http',
						'$q',
						'RestEndpoint',
						'Restangular',
						'$kWindow',
						function($http, $q, RestEndpoint, Restangular, $kWindow) {
							var serviceUrl = RestEndpoint.STOCK_SIGN_SERVICE_URL;
							var factory = {
								getApply : getApply,
								doSearchBusinessType : doSearchBusinessType,
								remove : remove,
								createStockSignConfiguration : createStockSignConfiguration,
								updateStockSignConfiguration : updateStockSignConfiguration,
							};

							return factory;

							function getApply(obj) {
								return Restangular.all(
										RestEndpoint.APP_PARAM_SERVICE_URL
												+ "/getForComboBox").post(obj);
							}

							function doSearchBusinessType(obj) {
								return Restangular.all(
										serviceUrl + "/doSearchBusinessType")
										.post(obj);
							}
							function remove(obj) {
								return Restangular.all(serviceUrl + "/remove")
										.post(obj);
							}

							function createStockSignConfiguration(obj) {
								return Restangular.all(serviceUrl + "/add")
										.post(obj);
							}

							function updateStockSignConfiguration(obj) {
								return Restangular.all(serviceUrl + "/update")
										.post(obj);
							}
						} ]);