angular.module('MetronicApp').factory('createMemoryWork',[
						'RestEndpoint',
						'Restangular',
						function(RestEndpoint, Restangular) {
							var item;
							var constrWorkLogsLabelDTO;
							var isAppro;
							var aMonitorIdList;
							var bConstructIdList;
							var aDirectorIdList;
							var bDirectorIdList;
							
							var factory = {
								goTo : goTo,
								setItem : setItem,
								getItem : getItem,
								setIsAppro:setIsAppro,
								getIsAppro:getIsAppro,
								setConstrWorkLogsLabelDTO:setConstrWorkLogsLabelDTO,
								getConstrWorkLogsLabelDTO:getConstrWorkLogsLabelDTO,
								getAllConstrWorkLogs : getAllConstrWorkLogs,
								creatConstrWorkLogs : creatConstrWorkLogs,
								getEstimatesWork : getEstimatesWork,
								deleteConstrWorkLogs : deleteConstrWorkLogs,
								updateConstrWorkLogs:updateConstrWorkLogs,
								exportFileConstrWorkLogs : exportFileConstrWorkLogs,
								exportFileDoc : exportFileDoc,
								getListEmployeeByRole : getListEmployeeByRole,
								appro:appro,
								cancelAprroval : cancelAprroval,
								getAllBia:getAllBia,
								deleteConstrWorkLogsLabelDTO:deleteConstrWorkLogsLabelDTO,
								setaMonitorIdList:setaMonitorIdList,
								getaMonitorIdList:getaMonitorIdList,
								setbConstructIdList:setbConstructIdList,
								getbConstructIdList:getbConstructIdList,
								exportList:exportList,
								exportListDoc:exportListDoc,
								setaDirectorIdList:setaDirectorIdList,
								getaDirectorIdList:getaDirectorIdList,
								setbDirectorIdList:setbDirectorIdList,
								getbDirectorIdList:getbDirectorIdList,
								getRoleId:getRoleId,
								downloadInstruction:downloadInstruction,
							};
							return factory;
							/* Handle action client on a menu item */
							function cancelAprroval(dto){
						    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
						    }
							function setaMonitorIdList(aMonitorIdList) {
								this.aMonitorIdList = aMonitorIdList;
							}
							function getaMonitorIdList() {
								return this.aMonitorIdList;
							}
							function setbConstructIdList(bConstructIdList) {
								this.bConstructIdList = bConstructIdList;
							}
							function getbConstructIdList() {
								return this.bConstructIdList;
							}
							function setaDirectorIdList(aDirectorIdList) {
								this.aDirectorIdList = aDirectorIdList;
							}
							function getaDirectorIdList() {
								return this.aDirectorIdList;
							}
							function setbDirectorIdList(bDirectorIdList) {
								this.bDirectorIdList = bDirectorIdList;
							}
							
							function getbDirectorIdList() {
								return this.bDirectorIdList;
							}
							function setItem(item) {
								this.item = item;
							}
							function getItem() {
								return this.item;
							}
							
							function setIsAppro(isAppro) {
								this.isAppro = isAppro;
							}
							function getIsAppro() {
								return this.isAppro;
							}
							
							function setConstrWorkLogsLabelDTO(constrWorkLogsLabelDTO) {
								this.constrWorkLogsLabelDTO = constrWorkLogsLabelDTO;
							}
							function getConstrWorkLogsLabelDTO() {
								return this.constrWorkLogsLabelDTO;
							}
							function deleteConstrWorkLogsLabelDTO(){
								delete this.constrWorkLogsLabelDTO;
							} 
							function goTo(menuKey) {
								var template = SUGGGET_ADPAYMENT;
								setArParams(menuKey);
								setIdCheckbox(menuKey);
								setApParams(menuKey);
								postal.publish({
									channel : "Tab",
									topic : "open",
									data : template
								});
							}
							//Dosearch and getList ConstrWorkLogs
							function getAllConstrWorkLogs(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/getAllConstrWorkLogs").post(criteria);
							}
							
							//Get list Estimates Work
							function getEstimatesWork(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/getEstimatesWork").post(criteria);
							}
							
							//Creat Constr Work Logs
							function creatConstrWorkLogs(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/addConstrWorkLogs").post(criteria);
							}
							
							//Update Constr Work Logs
							function updateConstrWorkLogs(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/updateConstrWorkLogs").post(criteria);
							}
							
							//Delete Multi Constr Work Logs
							function deleteConstrWorkLogs(listCriteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/deleteConstrWorkLogs").post(listCriteria);
							}
							
							//export file pdf
							function exportFileConstrWorkLogs(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/exportFileConstrWorkLogs").post(criteria);
							}
							
							//export file docx
							function exportFileDoc(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/exportFileDoc").post(criteria);
							}
							
							//get ListEmployeeByRole
							function getListEmployeeByRole(object) {
								return Restangular
										.all(
												RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL)
										.post(object);
							}
							
							//approval
							function appro(dto) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_URL+"/appro").post(dto);
							}
							
							function getAllBia(constructId) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL+"/getAllBia").post(constructId);
							}
							
							function getRoleId() {
					            return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
					        }
							
							function exportList(objectReportID){
								return Restangular.all(RestEndpoint.CONSTR_WORK_LOGS_URL+"/exportList").post(objectReportID);
							}
							
							function exportListDoc(objectReportID){
								return Restangular.all(RestEndpoint.CONSTR_WORK_LOGS_URL+"/exportListDoc").post(objectReportID);
							}
							
							function downloadInstruction(){
								return Restangular.one(RestEndpoint.CONSTR_WORK_LOGS_URL+"/downloadInstruction").get();
							}
							
						} ]);