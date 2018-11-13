angular.module('MetronicApp').factory('monitorMissionAssignService', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular){
		var serviceUrl = RestEndpoint.TITLE_AZIMU_SERVICE_URL;
		var serviceUrl2 = RestEndpoint.TITLE_AZIMU_LIST_SERVICE_URL;
	    var factory = {
	    	display: display,
			getRoleId:getRoleId,
			saveAll:saveAll,
			getEmployeeByRole: getEmployeeByRole,
			exportMonitorMissionAssign : exportMonitorMissionAssign,
    		exportListMonitorMissionAssign : exportListMonitorMissionAssign,
    		exportDocMonitorMissionAssign : exportDocMonitorMissionAssign,
    		exportListDocMonitorMissionAssign : exportListDocMonitorMissionAssign,
			getMonitorMissionAssign : getMonitorMissionAssign,
			getListEmployeeByConstruction : getListEmployeeByConstruction,
			getMonitorAssignFolder : getMonitorAssignFolder,
			setData : setData,
			getData : getData,
			/*getRoleId : getRoleId,*/
			remove : remove,
			cancelAprroval : cancelAprroval,
			updateMissionAssign : updateMissionAssign,
			appro:appro,
	    };
	    
	    return factory;
	    
	    function cancelAprroval(dto){
	    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
	    }
	   
	    function appro(obj){
			return Restangular.all(RestEndpoint.APPROVAL_MONITOR_MISSION_SERVICE_URL).post(obj); 
		}
	    function setData(data) {
			this.data = data;
		}
		function getData() {
			return this.data;
		}
	    function remove(id){
	    	return Restangular
			.all(
					RestEndpoint.MONITOR_MISSION_ASSIGN_URL
							+ "/updateIsActive/")
			.post(id);
	    }
	    function removeConstrRecordMap(id){
	    	return Restangular.one(RestEndpoint.CONSTR_RE_MAP_SERVICE_URL, id).remove();
	    }
	    
	    function display(data) {	    	
	    	return Restangular.all(serviceUrl+"/findByConstructId").post(data);
	    }
	    function saveAll(data) {	    	
	    	return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL +"/").post(data);
	    }
	    
	    function getEmployeeByRole(object) {
	    	return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);
	    }
	  
//	    function getRoleId() {
//	        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
//	    }
	    //ngoccx
	    function getMonitorMissionAssign(dto){
	    	return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/getMonitorMissionAssign").post(dto);
	    }
	    function getListEmployeeByConstruction(item) {
			return Restangular
					.all(
							RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
									+ "/getListEmployeeByConstruction")
					.post(item);
		}
	    function getRoleId() {
			return Restangular.all(
					RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
							+ "/getRoleId").getList();
		}
	    function getMonitorAssignFolder(){
	    	return Restangular.one(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/folder").get();
	    }
	    function updateMissionAssign(missass) {
			return Restangular
					.all(
							RestEndpoint.MONITOR_MISSION_ASSIGN_URL +"/put")
					.post(missass);
		}
	    function cancelAprroval(dto){
	    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
	    }
	    //phongpv
	    function exportDocMonitorMissionAssign(searchCriteria) {	    	
			return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/exportDoc").post(searchCriteria);
	    }
	    function exportListDocMonitorMissionAssign(searchCriteria) {	    	
			return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/exportDocList").post(searchCriteria);
	    }
	    function exportMonitorMissionAssign(searchCriteria) {	    	
			return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/exportWord").post(searchCriteria);
	    }
	    function exportListMonitorMissionAssign(searchCriteria) {	    	
			return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/exportWordList").post(searchCriteria);
	    }
	    //end phongpv
	    
	    //end
	}]);
