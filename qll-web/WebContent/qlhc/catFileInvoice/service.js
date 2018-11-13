
angular.module('MetronicApp').factory('catFileInvoiceService', ['$http', '$q', 'RestEndpoint', 'PopupConst', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, PopupConst, Restangular, $kWindow){

	var SERVICE_URL = RestEndpoint.CAT_FILE_INVOICE_RS_SERVICE;

	var factory = {
		getAll: getAll,
		findById : findById,
		findByExistProfile:findByExistProfile
	};
	return factory;

	function getAll(){
		return Restangular.all(SERVICE_URL).getList();
	}

	function findById(id) {
		return Restangular.one(SERVICE_URL + "/" + id).get();
	}

	function findByExistProfile(constructionId) {
		return Restangular.all(SERVICE_URL + "/findByExistProfile/" + constructionId).getList();
	}


}]);
