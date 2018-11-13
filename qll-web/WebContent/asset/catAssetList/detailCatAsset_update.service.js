angular.module('MetronicApp').factory('detailCatAssetUpdateService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	var id;
	var factory = {
		genAssetCode: genAssetCode,
		getMerEntity: getMerEntity,
		getConstructionAcceptance: getConstructionAcceptance,
		fetchAll: fetchAll,
		setId: setId,
		getId: getId,
		send: send,
		getCatAssetFolder: getCatAssetFolder,
		addCatAsset: addCatAsset,
		updateCatAsset: updateCatAsset,
		remove: remove,
		searchAssetCost: searchAssetCost,
		getDetail: getDetail,
		exportExcel:exportExcel,
		isRequiredLTA:isRequiredLTA,
		getLstAssetPaging:getLstAssetPaging,
		cancelUpgradeLta:cancelUpgradeLta,
		createLtaFromOfferSlip:createLtaFromOfferSlip,
		updateLtaFromOfferSlip:updateLtaFromOfferSlip,
		autocompleteConstrFromOfferSlip:autocompleteConstrFromOfferSlip,
		getLtaAttachDetail:getLtaAttachDetail
		
	}
	return factory;
	
	function getLtaAttachDetail(obj){
		
			return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getLtaAttachDetail").post(obj);
		
	}
	/**
	 * Tìm kiếm auto công tình từ yêu cầu bằng tay
	 */
	function autocompleteConstrFromOfferSlip(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/autocompleteConstrFromOfferSlip").post(obj);
	}
	/**
	 * Thực hiện tạo tài sản từ yêu cầu bằng tay
	 */
	function createLtaFromOfferSlip(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/createLtaFromOfferSlip").post(obj);
	}
	/**
	 * Thực hiện cập nhật tài sản từ yêu cầu bằng tay
	 */	
	function updateLtaFromOfferSlip(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/updateLtaFromOfferSlip").post(obj);
	}
	function cancelUpgradeLta(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL+"/cancelUpgradeLta").post(obj);
	}
	function getLstAssetPaging(obj,pageParam){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL +"/searchLtaPaginate").post(obj,pageParam);
	}
	function isRequiredLTA(obj){
		return Restangular.one(RestEndpoint.LONG_TERM_ASSET_URL + "/isRequiredToBeLTA").get(obj);
	}
	
	function getDetail(obj){		
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getDetail").post(obj);
	}
	
	function searchAssetCost(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/searchAssetCost").post(obj);
	}
	
	function updateCatAsset(obj){

		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/update" +"/put").post(obj);
	}
	
	function addCatAsset(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/add").post(obj);
	}
	
	function getCatAssetFolder(scope){
    	return Restangular.one(RestEndpoint.LONG_TERM_ASSET_URL + "/getFolder").get();
    }
	
	function genAssetCode(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getNewLotaIndex").post(obj);
	}
	
	function getMerEntity(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getMerEntity").post(obj);
	}
	
	function getConstructionAcceptance(obj){
		return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/getConstructionAcceptance").post(obj);
	}
	
	function fetchAll(obj){
		return Restangular.all("longTermAssetServiceRest/search").post(obj);
	}
	
	function send(id){
		return Restangular.one("longTermAssetServiceRest/send", id).get();
	}
	
	function remove(id){
		return Restangular.one("longTermAssetServiceRest/remove",id).post();
	}
	
	
	function setId(data){
		id = data;
	}
	function getId(){
		
		return id;
	}
	
	function exportExcel(obj){
		return Restangular.all("longTermAssetServiceRest/exportExcel").post(obj);
	}
}]);