angular
		.module('MetronicApp')
		.factory(
				'listWorkOrganizationPlan',
				[
						'RestEndpoint',
						'Restangular',
						function(RestEndpoint, Restangular) {
							var item;
							var factory = {
								getAllConstrOrganizationPlan : getAllConstrOrganizationPlan,
								deleteConstrOrganizationPlan : deleteConstrOrganizationPlan,
								exportFileListWorkOrganizationPlan: exportFileListWorkOrganizationPlan,
								addConstrOrganizationPlan:addConstrOrganizationPlan,
								getListEmployeeByRole : getListEmployeeByRole,
								getConstrOrganizationFolder: getConstrOrganizationFolder,
								getListByParentIdAndType : getListByParentIdAndType,
								deleteDocument:deleteDocument,
								getRoleId:getRoleId,
								setItem : setItem,
								getItem : getItem,
								downloadZip : downloadZip,
								downloadZipParentchild : downloadZipParentchild,
								getAttachTypeKey:getAttachTypeKey,
								getAttachTypeValue:getAttachTypeValue
							};
							return factory;
							/* Handle action client on a menu item */

							
							function setItem(item) {
								this.item = item;
							}
							function getItem() {
								return this.item;
							}

							function getInspList() {
								return Restangular
										.all(
												RestEndpoint.CONSTRUCTION_ACCEPTANCE_URL)
										.getList();
							}

							function getAllConstrOrganizationPlan(obj) {
								return Restangular
										.all(
												RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL
														+ "/getAllConstrOrganizationPlan")
										.post(obj);
							}

							function deleteConstrOrganizationPlan(
									listConstrOrgPlanId) {
								return Restangular
										.all(
												RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL
														+ "/deleteConstrOrganizationPlan")
										.post(listConstrOrgPlanId);
							}
							
							function exportFileListWorkOrganizationPlan(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL+"/exportFileListWorkOrganizationPlan").post(criteria);
							}
							
							//Creat
							function addConstrOrganizationPlan(object) {
								return Restangular.all(
										RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL+"/addConstrOrganizationPlan").post(object);
							}
							
							//get ListEmployeeByRole
							function getListEmployeeByRole(object) {
								return Restangular
										.all(
												RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL)
										.post(object);
							}
							
							function getConstrOrganizationFolder(scope){
						    	return Restangular.one(RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL+"/folder").get();
						    }
							
							function getListByParentIdAndType(parentId){
						    	return Restangular.all(RestEndpoint.UTIL_ATTACHED_DOCUMENTS_SERVICE_URL+"/getListOrganizationByParentId").post(parentId);
						    }
							
							function deleteDocument(listAttachId){
						    	return Restangular.all(RestEndpoint.UTIL_ATTACHED_DOCUMENTS_SERVICE_URL+"/deleteDocument").post(listAttachId);
						    }
							
							function getRoleId() {
					            return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
					        }
							
							function downloadZip(item){
								return Restangular
								.all(
										RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL
												+ "/downloadFile/")
								.post(item);
							}
							function downloadZipParentchild(item){
								return Restangular
								.all(
										RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL
												+ "/downloadFileParentChild/")
								.post(item);
							}
							function getAttachTypeKey() {
								return Restangular
										.one(
												RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL
												+ "/attachTypeKey")
										.get();
							}
							function getAttachTypeValue() {
								return Restangular
										.one(
												RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL
												+ "/attachTypeValue")
										.get();
							}

						} ]);