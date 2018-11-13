angular.module('MetronicApp').factory('inspectionListService',['$http','$q','RestEndpoint','Restangular','$kWindow',
				function($http, $q, RestEndpoint, Restangular, $kWindow) {
					// Danh sach cac dich vu ma service nay cung cap
					var factory = {
						findByConstructId: findByConstructId,
						deleteConstructionAcceptance : deleteConstructionAcceptance
					};
					return factory;
					/* Handle action client on a menu item */

					function findByConstructId(constructId) {
						return Restangular.all(
								RestEndpoint.CONSTRUCTION_ACCEPTANCE_URL + "/findByConstructId/" + constructId).getList();
					}
					function deleteConstructionAcceptance(id)
					{
						return Restangular.one(RestEndpoint.CONSTRUCTION_ACCEPTANCE_URL, id).remove();
					}
} ]);
