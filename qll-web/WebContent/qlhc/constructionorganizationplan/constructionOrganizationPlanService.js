angular.module('MetronicApp').factory('WorkOrganizationPlan',[
						'RestEndpoint',
						'Restangular',
						function(RestEndpoint, Restangular) {
							var factory = {
								goTo : goTo,
								getListEmployeeByRole : getListEmployeeByRole,
								addConstrOrganizationPlan : addConstrOrganizationPlan,
								updateConstrOrganizationPlan : updateConstrOrganizationPlan
							};
							var SUGGGET_ADPAYMENT = {
								key : 'CREATE_MEMORY_WORK',
								title : 'Phương án tổ chức thi công',
								templateUrl : 'qlhc/constructionorganizationplan/constructionOrganizationPlan.html',
								lazyLoadFiles : [
										'qlhc/constructionorganizationplan/constructionOrganizationPlanController.js',
										'qlhc/listconstructionorganizationplan/listConstructionOrganizationPlanService.js',
										'qlhc/ProposalEvaluation/ProposalEvaluation.service.js' ]
							};
							return factory;
							/* Handle action client on a menu item */

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
							
							function getListEmployeeByRole(object) {
								return Restangular
										.all(
												RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL)
										.post(object);
							}
							
							//Creat
							function addConstrOrganizationPlan(object) {
								return Restangular.all(
										RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL+"/addConstrOrganizationPlan").post(object);
							}
							
							//Update
							function updateConstrOrganizationPlan(object) {
								return Restangular.all(
										RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL+"/updateConstrOrganizationPlan").post(object);
							}
							

						} ]);