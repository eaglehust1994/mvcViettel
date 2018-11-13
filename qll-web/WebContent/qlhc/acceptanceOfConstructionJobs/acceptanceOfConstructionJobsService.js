angular.module('MetronicApp').factory('acceptanceOfConstructionJobsService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){

	
	
	var item ;
	var factory = {
			showPopupWorkForm : showPopupWorkForm,
			//add, update, delete
			createWorkItemsAcceptance : createWorkItemsAcceptance,
			updateWorkItemsAcceptance :updateWorkItemsAcceptance,
			//removeWorkItemsAcceptance : removeWorkItemsAcceptance,
			doSearchWorkItemsAcceptance : doSearchWorkItemsAcceptance,
			exportFileWorkItemsAcceptance : exportFileWorkItemsAcceptance,
			exportListWorkItemsAcceptance : exportListWorkItemsAcceptance,
			exportFileDocWorkItemsAcceptance : exportFileDocWorkItemsAcceptance,
			exportListDocWorkItemsAcceptance : exportListDocWorkItemsAcceptance,
			getRoleId : getRoleId,
			//getListEmployeeByRole : getListEmployeeByRole,
			cancelAprroval : cancelAprroval,
			addWorkItemAcceptList : addWorkItemAcceptList,
			updateIsActiveWorkItemsAcceptance : updateIsActiveWorkItemsAcceptance,
			deleteWorkItemAcceptList : deleteWorkItemAcceptList,
			doSearchByWorkItemsAcceptance : doSearchByWorkItemsAcceptance,
			approWorkItemsAcceptance : approWorkItemsAcceptance,
			getListEmployeeByConstruction : getListEmployeeByConstruction,
			setItem:setItem,
			getItem:getItem,
	    };

    return factory;
    function cancelAprroval(dto){
    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
    }
    function showPopupWorkForm(id) {
        return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+ "/getGrid/" + id).getList(); 	 
    }
    // aad, update, delete
    function createWorkItemsAcceptance(workItemsAcceptance){
    	return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL).post(workItemsAcceptance);
    }
	function updateWorkItemsAcceptance(workItemsAcceptance){
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL+"/put").post(workItemsAcceptance);
    }
	/*function removeWorkItemsAcceptance(id){
		return Restangular.one(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL, id).remove();
	}*/
	function doSearchWorkItemsAcceptance(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/search").post(searchCriteria);
    }
	function exportFileWorkItemsAcceptance(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/exportWord").post(searchCriteria);
    }
	function exportListWorkItemsAcceptance(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/exportWordList").post(searchCriteria);
    }
	function exportFileDocWorkItemsAcceptance(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/exportWordDoc").post(searchCriteria);
    }
	function exportListDocWorkItemsAcceptance(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/exportWordListDoc").post(searchCriteria);
    }
	function getRoleId() {
        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
    }
	function addWorkItemAcceptList(workItemsAcceptanceList){
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL + "/addWorkItemAcceptList").post(workItemsAcceptanceList);
    }
	function updateIsActiveWorkItemsAcceptance(id){
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL + "/updateIsActive").post(id);
    }
	function deleteWorkItemAcceptList(listEstimatesWorkItemId){
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL + "/deleteWorkItemAcceptList").post(listEstimatesWorkItemId);
    }
	/*function getListEmployeeByRole(criteria) {	    	
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_BY_CONSTRID_SERVICE_URL).post(criteria);
    }*/
	function doSearchByWorkItemsAcceptance(searchCriteria) {
    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL + "/searchWorkItemsAcceptanceId").post(searchCriteria);	   
    }
	function approWorkItemsAcceptance(criteria) {	    	
		return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/appro").post(criteria);
    }
	function getListEmployeeByConstruction(item) {
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
	}
	function setItem(item){
		this.item = item;
	}
	function getItem(){
		return this.item;
	}
}]);