angular.module('MetronicApp').factory('report_A_services',['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular) {
	var serviceUrl = RestEndpoint.GL_ALLOCATION_TEMPLATE_SERVICE_URL;
			var factory = {
					findByConstructId : findByConstructId
			}
			return factory;
			//LoadID list/////
			function findByConstructId(constructId) {
				return Restangular.all(RestEndpoint.A_MATERIAL_RECOVERY_LIST_URL + "/findByConstructId/" + constructId).getList();
			}
			
} ]);