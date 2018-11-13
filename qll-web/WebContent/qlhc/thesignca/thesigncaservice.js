angular
		.module('MetronicApp')
		.factory(
				'theSignCA',
				[
						'RestEndpoint',
						'Restangular',
						function(RestEndpoint, Restangular) {
							var item;
							var factory = {
								getEmployeeByListID : getEmployeeByListID,
								updateTotal :updateTotal,
								setItem : setItem,
								getItem : getItem,
								getConOrgChild : getConOrgChild
							};
							return factory;

							function setItem(item) {
								this.item = item;
							}
							function getItem() {
								return this.item;
							}
							
							// Dosearch and getList ConstrWorkLogs
							function getEmployeeByListID(info) {
								return Restangular.all(
										RestEndpoint.THE_SIGN_CA_URL
												+ "/getEmployeeByListID").post(info);
							}
							//
							function updateTotal(obj) {
								return Restangular
										.all(
												RestEndpoint.CONSTR_COMPLETE_RECORDS_MAP_RS_SERVICE
														+ "/updateTotal").post(
												obj);
							}
							
							function getConOrgChild(dto){
								return Restangular
								.all(
										RestEndpoint.CONSTR_ORGANIZATION_PLAN_URL
												+ "/getAllConstrOrganizationPlanChild").post(
														dto);
							}

						} ]);