angular.module('MetronicApp').factory('reportSceneGenerateService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	var factory = {
			doSearchSceneGenerateWorkConstruction : doSearchSceneGenerateWorkConstruction,
			doSearchSceneGenerateWorkList : doSearchSceneGenerateWorkList,
			doCRUD : doCRUD,
			doCRUDCT : doCRUDCT,
			getWorkItemCongTrinh : getWorkItemCongTrinh,
			//createReportSceneAriseWeigh : createReportSceneAriseWeigh,
			//updateReportSceneAriseWeigh :updateReportSceneAriseWeigh,
			//removeReportSceneAriseWeigh : removeReportSceneAriseWeigh,
			updateIsActiveReportSceneAriseWeigh : updateIsActiveReportSceneAriseWeigh,
			removeReportSceneAriseWeighList : removeReportSceneAriseWeighList,
			
			exportFileReportSceneAriseWeigh : exportFileReportSceneAriseWeigh,
			exportListReportSceneAriseWeigh : exportListReportSceneAriseWeigh,
			exportFileDocReportSceneAriseWeigh : exportFileDocReportSceneAriseWeigh,
			exportListDocReportSceneAriseWeigh : exportListDocReportSceneAriseWeigh,
			//minhpvn
			exportFileReportSceneAriseWeighCT : exportFileReportSceneAriseWeighCT,
			exportListReportSceneAriseWeighCT : exportListReportSceneAriseWeighCT,
			exportFileDocReportSceneAriseWeighCT : exportFileDocReportSceneAriseWeighCT,
			exportListDocReportSceneAriseWeighCT : exportListDocReportSceneAriseWeighCT,
			getRoleId : getRoleId,
			cancelAprroval : cancelAprroval,
			//getListEmployeeByRole : getListEmployeeByRole,
			getItemNameByConstrId : getItemNameByConstrId,
			approCT : approCT,
			getListEmployeeByConstruction : getListEmployeeByConstruction,
			downloadTempleReportSceneGenerate : downloadTempleReportSceneGenerate,
			exportDataImport : exportDataImport
	    };

    return factory;
    function cancelAprroval(dto){
    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
    }
    function doSearchSceneGenerateWorkConstruction(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/doSearchSceneGenerateWorkConstruction").post(searchCriteria);
    }
    
    function doSearchSceneGenerateWorkList(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL  + "/searchList").post(searchCriteria);
    }
    function doCRUD(reportSceneAriseWeigh){
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL + "/doCRUD").post(reportSceneAriseWeigh);
    }
    //minhpvn
    function doCRUDCT(reportSceneAriseWeigh){
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL + "/doCRUDCT").post(reportSceneAriseWeigh);
    }
    function updateIsActiveReportSceneAriseWeigh(id) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/updateIsActive").post(id);
    }
    function removeReportSceneAriseWeighList(id){
		return Restangular.one(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL, id).remove();
	}
    function getRoleId() {
        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
    }
    function exportFileReportSceneAriseWeigh(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportScene").post(searchCriteria);
    }
    function exportListReportSceneAriseWeigh(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneList").post(searchCriteria);
    }
    function exportFileDocReportSceneAriseWeigh(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneDoc").post(searchCriteria);
    }
    function exportListDocReportSceneAriseWeigh(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneListDoc").post(searchCriteria);
    }
    /*function getListEmployeeByRole(criteria) {	    	
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_BY_CONSTRID_SERVICE_URL).post(criteria);
    }*/
    function getItemNameByConstrId(criteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL + "/getItemNameByConstrId").post(criteria);
    }
    function approCT(criteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/approCT").post(criteria);
    }
    function getListEmployeeByConstruction(item) {
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
	}
    function downloadTempleReportSceneGenerate(obj){
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL+"/downloadTempleReportSceneGenerate").post(obj);
    }
	function getWorkItemCongTrinh(item){
    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/getWorkItemCongTrinh/").post(item);
    }
	function exportDataImport(item) {
		return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/exportDataImport").post(item);
	}
	//minhpvn
    function exportFileReportSceneAriseWeighCT(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneCT").post(searchCriteria);
    }
    function exportListReportSceneAriseWeighCT(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneListCT").post(searchCriteria);
    }
    function exportFileDocReportSceneAriseWeighCT(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneDocCT").post(searchCriteria);
    }
    function exportListDocReportSceneAriseWeighCT(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneListDocCT").post(searchCriteria);
    }
}]);