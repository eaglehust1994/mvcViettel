angular.module('MetronicApp').factory('distanceUnloadedMaterialsList',['$http','$q','RestEndpoint','Restangular','$kWindow',
				function($http, $q, RestEndpoint, Restangular, $kWindow) {
					// Danh sach cac dich vu ma service nay cung cap
					var factory = {
							getListCR: getListCR,
							showNoiDungPS : showNoiDungPS,
				    		createDistanceUnloadConstrMinuteService : createDistanceUnloadConstrMinuteService,
				    		updateConstructionacceptance : updateConstructionacceptance,
				    		exportDistanceUnloadConstrMinutes : exportDistanceUnloadConstrMinutes,
				    		exportListDistanceUnloadConstrMinutes : exportListDistanceUnloadConstrMinutes,
				    		deleteDistanceUnloadList : deleteDistanceUnloadList,
				    		exportDocDistanceUnloadConstrMinutes : exportDocDistanceUnloadConstrMinutes,
				    		exportListDocDistanceUnloadConstrMinutes : exportListDocDistanceUnloadConstrMinutes,
				    		getListEmployeeByRole : getListEmployeeByRole,
				    		appro : appro,
				    		updateStatus : updateStatus,
				            cancelAprroval : cancelAprroval,
				    		removeConstrRecordMap : removeConstrRecordMap,
				    		getRoleId : getRoleId,
				    		getListEmployeeByConstruction : getListEmployeeByConstruction ,
				    		updateIsActiveDistanceUnloadConstrMinutes: updateIsActiveDistanceUnloadConstrMinutes
					};
					return factory;
					function cancelAprroval(dto){
				    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
				    }
					function getListEmployeeByRole(object) {
						return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);			
					}
					function getListCR(obj) {
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL + "/getAllC/").post(obj);
					}
					function showNoiDungPS(constructId) {
				        return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL+ "/getGrid/" + constructId).getList(); 	 
				    }
				    function createDistanceUnloadConstrMinuteService(a){
				    	return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL).post(a);
				    }
				    function updateConstructionacceptance(b){
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL+"/put").post(b);
				    }
				    function exportDistanceUnloadConstrMinutes(searchCriteria) {	    	
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL  + "/exportWord").post(searchCriteria);
				    }
				    function exportListDistanceUnloadConstrMinutes(searchCriteria) {	    	
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL  + "/exportWordList").post(searchCriteria);
				    }
				    function deleteDistanceUnloadList(id){
						return Restangular.one(RestEndpoint.DISTANCE_UNLOAD_LIST_URL, id).remove();
					}
				    function exportDocDistanceUnloadConstrMinutes(searchCriteria) {	    	
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL  + "/exportDoc").post(searchCriteria);
				    }
				    function exportListDocDistanceUnloadConstrMinutes(searchCriteria) {	    	
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL  + "/exportDocList").post(searchCriteria);
				    }
				    function appro(obj){
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL + "/appro").post(obj);
					}
				    function updateStatus(obj){
						return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL + "/updateStatus").post(obj);
					}
				    function removeConstrRecordMap(id){
				    	return Restangular.one(RestEndpoint.CONSTR_RE_MAP_SERVICE_URL, id).remove();
				    }
				    function getRoleId() {
				        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
				    }
				    function getListEmployeeByConstruction(item) {
						return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
					}
				    
				    function updateIsActiveDistanceUnloadConstrMinutes(id){
						return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL + "/updateIsActive").post(id);
				    }
} ]);
