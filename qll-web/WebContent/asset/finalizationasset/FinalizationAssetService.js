
angular.module('MetronicApp').factory('finalizationassetService', ['$http', '$q', 'RestEndpoint', 'PopupConst', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, PopupConst, Restangular, $kWindow){
	var serviceUrl = RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL;   
	var factory = {
		getDetail: getDetail,
		searchAssetCost: searchAssetCost,
		getCatAssetFolder: getCatAssetFolder,
		getMerEntity: getMerEntity,
		getConstructionAcceptance: getConstructionAcceptance,
		updateCatAsset: updateCatAsset
	};
	return factory;
	
	function updateCatAsset(obj){

		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/update" +"/put").post(obj);
	}
	
	function getConstructionAcceptance(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getConstructionAcceptance").post(obj);
	}
	
	function getMerEntity(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getMerEntity").post(obj);
	}
	
	function getDetail(obj){		
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getDetail").post(obj);
	}
	
	function searchAssetCost(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/searchAssetCost").post(obj);
	}

	function getCatAssetFolder(scope){
		return Restangular.one(RestEndpoint.LONG_TERM_ASSET_URL + "/getFolder").get();
	}
}]);
