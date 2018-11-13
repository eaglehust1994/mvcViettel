angular.module('MetronicApp').factory('reportSceneAriseWeighService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	var factory = {
			doSearchSceneGenerateWork : doSearchSceneGenerateWork,
			doSearchSceneGenerateWorkList : doSearchSceneGenerateWorkList,
			doCRUD : doCRUD,
			//createReportSceneAriseWeigh : createReportSceneAriseWeigh,
			//updateReportSceneAriseWeigh :updateReportSceneAriseWeigh,
			//removeReportSceneAriseWeigh : removeReportSceneAriseWeigh,
			updateIsActiveReportSceneAriseWeigh : updateIsActiveReportSceneAriseWeigh,
			removeReportSceneAriseWeighList : removeReportSceneAriseWeighList,			
			exportFileReportSceneAriseWeigh : exportFileReportSceneAriseWeigh,
			exportListReportSceneAriseWeigh : exportListReportSceneAriseWeigh,
			exportFileDocReportSceneAriseWeigh : exportFileDocReportSceneAriseWeigh,
			exportListDocReportSceneAriseWeigh : exportListDocReportSceneAriseWeigh,
			getRoleId : getRoleId,
			cancelAprroval : cancelAprroval,
			//getListEmployeeByRole : getListEmployeeByRole,
			getItemNameByConstrId : getItemNameByConstrId,
			approReportSceneAriseWeigh : approReportSceneAriseWeigh,
			getListEmployeeByConstruction : getListEmployeeByConstruction,
			downloadBieuMau : downloadBieuMau
	    };

    return factory;
    function cancelAprroval(dto){
    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
    }
    function doSearchSceneGenerateWork(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/doSearchSceneGenerateWork").post(searchCriteria);
    }
    
    function doSearchSceneGenerateWorkList(searchCriteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL  + "/searchList").post(searchCriteria);
    }
    function doCRUD(reportSceneAriseWeigh){
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL + "/doCRUD").post(reportSceneAriseWeigh);
    }
    /*function createReportSceneAriseWeigh(reportSceneAriseWeigh){
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL).post(reportSceneAriseWeigh);
    }
	function updateReportSceneAriseWeigh(reportSceneAriseWeigh){
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL + "/put).post(reportSceneAriseWeigh);
    }*/
    
    /*function removeReportSceneAriseWeigh(id){
		return Restangular.one(RestEndpoint.SCENE_GENERATE_WORK_URL, id).remove();
	}*/
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
    function approReportSceneAriseWeigh(criteria) {	    	
		return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/appro").post(criteria);
    }
    function getListEmployeeByConstruction(item) {
		return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
	}
    function downloadBieuMau(obj){
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL+"/downloadBieuMau").post(obj);
    }
}]);