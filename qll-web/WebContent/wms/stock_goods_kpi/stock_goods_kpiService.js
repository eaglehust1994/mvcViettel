angular.module('MetronicApp').factory(
		'stockGoodsKpiServiceRest',
		[
				'$http',
				'$q',
				'RestEndpoint',
				'Restangular',
				'$kWindow',
				function($http, $q, RestEndpoint, Restangular, $kWindow) {
					var serviceUrl = RestEndpoint.STOCK_GOODS_KPI_URL;
					var factory = {
						doSearchKpiTime : doSearchKpiTime,
						doSearchKpiAmount : doSearchKpiAmount,
						exportpdfAmount : exportpdfAmount,
						exportpdfTime : exportpdfTime
						
					};

					return factory;

					function doSearchKpiTime(obj) {
						return Restangular.all(serviceUrl + "/doSearchKpiTime")
								.post(obj);
					}
					function doSearchKpiAmount(obj) {
						return Restangular.all(
								serviceUrl + "/doSearchKpiAmount").post(obj);
					}

					 function exportpdfAmount(obj) {
					    	var deferred = $q.defer();
				              $http({
				            	  url: RestEndpoint.BASE_SERVICE_URL + "reportServiceRest"+"/exportPdf",
				                    dataType: 'json',
				                    method: 'POST',
				                    data: obj,
				                    headers: {
				                        "Content-Type": "application/json"
				                    },
				                    responseType : 'arraybuffer',//THIS IS IMPORTANT
				                }).success(function(data){
				            	  deferred.resolve(data); 
				              })
				              .error(function(data){
				            	  deferred.reject(data);
				              });
				             return deferred.promise;
				        }
					 
					 function exportpdfTime(obj) {
					    	var deferred = $q.defer();
				              $http({
				            	  url: RestEndpoint.BASE_SERVICE_URL + "reportServiceRest"+"/exportPdf",
				                    dataType: 'json',
				                    method: 'POST',
				                    data: obj,
				                    headers: {
				                        "Content-Type": "application/json"
				                    },
				                    responseType : 'arraybuffer',//THIS IS IMPORTANT
				                }).success(function(data){
				            	  deferred.resolve(data); 
				              })
				              .error(function(data){
				            	  deferred.reject(data);
				              });
				             return deferred.promise;
				        }
					 
					 
				} ]);
