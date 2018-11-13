angular.module('MetronicApp').factory('listAcceptanceService', ['$http', '$q','RestEndpoint','Restangular', 'Constant','$rootScope',
	function($http, $q, RestEndpoint, Restangular, Constant,$rootScope){
//angular.module('MetronicApp').factory('listAcceptanceService', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular){
	var serviceUrl = RestEndpoint.CATEGORY_ACCEPTANCE_URL;
	var categoryAcceptanceId ;
	var dataToApproval;
	var factory = {
			getAllCategoryAcceptance : getAllCategoryAcceptance,	
			deleteCategoryAcceptanceList : deleteCategoryAcceptanceList,
			setCategoryAcceptanceId:setCategoryAcceptanceId,
			getCategoryAcceptanceId:getCategoryAcceptanceId,
			setDataToApproval:setDataToApproval,
			getDataToApproval:getDataToApproval,			
	    };
	
	return factory;
	
	function getAllCategoryAcceptance() {
		return Restangular.all(
				RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/getAllCategoryAcceptance").getList();
	}
	
	function deleteCategoryAcceptanceList(listObj) {
		return Restangular.all(
				RestEndpoint.CATEGORY_ACCEPTANCE_URL+"/deleteListCategoryAcceptance/").post(listObj);
	}
	
	function setCategoryAcceptanceId(categoryAcceptanceId){
		this.categoryAcceptanceId = categoryAcceptanceId;
	}
	function getCategoryAcceptanceId(){
		return getCategoryAcceptanceId;
	}
	
	function setDataToApproval(dataToApproval){
		this.dataToApproval = dataToApproval;
	}
	function getDataToApproval(){
		return dataToApproval;
	}
	
		
} ]);