
angular.module('MetronicApp').factory('catConstrTypeService', ['$http', '$q', 'RestEndpoint', 'PopupConst', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, PopupConst, Restangular, $kWindow){

	var SERVICE_URL = RestEndpoint.CAT_CONSTR_TYPE_RS_SERVICE;

	var factory = {
		getAll: getAll
	};
	return factory;

	function getAll(){
		return Restangular.all(SERVICE_URL).getList();
	}

}]);
