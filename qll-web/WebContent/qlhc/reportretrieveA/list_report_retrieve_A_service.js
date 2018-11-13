angular.module('MetronicApp').factory('list_report_A_services',['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular) {
	var serviceUrl = RestEndpoint.A_MATERIAL_RECOVERY_MINUTES_URL
	var factory = {
	    	findByConstructId : findByConstructId,
	    	removeMaterial : removeMaterial,
	    	deleteAMaterialMinutes : deleteAMaterialMinutes,
	    	getTwoList : getTwoList,
	    	getAllListEmployeeByRole : getAllListEmployeeByRole,
	    	getListEmployeeByRole : getListEmployeeByRole,
	    	addAMaterialRecoveryMinutes : addAMaterialRecoveryMinutes,
	    	updateRecoveryList : updateRecoveryList,
	    	updateAMaterialRecoveryMinutes : updateAMaterialRecoveryMinutes,
	    	exportFileM : exportFileM,
	    	exportList : exportList,
	    	exportFileMDoc : exportFileMDoc,
	    	exportListDoc : exportListDoc,
	    	getRoleId: getRoleId,
	    	getListEmployeeByConstruction : getListEmployeeByConstruction
	    };
	
	    return factory;
		///loadData
		function findByConstructId(object){
			return Restangular.all(serviceUrl + "/findByConstructId").post(object);
		}
		//remove
		function removeMaterial(aMaterialRecoveryMinutesId) {
			return Restangular.one(serviceUrl,aMaterialRecoveryMinutesId).remove();
		}
		function deleteAMaterialMinutes(listAMaterialRecoveryMinutesId) {
			return Restangular.all(serviceUrl+"/deleteAMaterialMinutes").post(listAMaterialRecoveryMinutesId);
		}
		// sum list
		function getTwoList(constructId){
			return Restangular.all(serviceUrl + "/getTwoList/" + constructId).getList();
		}
		
		function getAllListEmployeeByRole(object){
			return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL + "/getAllListEmployeeByRole/").post(object);
		}
		
		function getListEmployeeByRole(object) {
			return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);			
		}	
		
		function addAMaterialRecoveryMinutes(objectMinutes){
			return Restangular.all(RestEndpoint.A_MATERIAL_RECOVERY_MINUTES_URL).post(objectMinutes);
		}
		
		function updateRecoveryList(amaterialRecoveryMinutesId){
			return Restangular.all(serviceUrl + "/updateRecoveryList/" + amaterialRecoveryMinutesId).getList();
		}
		
		function updateAMaterialRecoveryMinutes(objectUpdateMinutes){
			return Restangular.all(RestEndpoint.A_MATERIAL_RECOVERY_MINUTES_URL +"/put").post(objectUpdateMinutes);
		}
		
		function exportFileM(objectReport) {
			return Restangular.all(serviceUrl+"/exportFileM").post(objectReport);
		}
		
		function exportList(objectReportID){
			return Restangular.all(serviceUrl+"/exportList").post(objectReportID);
		}
		
		function exportFileMDoc(objectReport) {
			return Restangular.all(serviceUrl+"/exportFileMDoc").post(objectReport);
		}
		
		function exportListDoc(objectReportID){
			return Restangular.all(serviceUrl+"/exportListDoc").post(objectReportID);
		}
		function getRoleId() {
            return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
        }
		function getListEmployeeByConstruction(item) {
			return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
		}
		
} ]);