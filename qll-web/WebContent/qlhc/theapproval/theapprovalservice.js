angular
		.module('MetronicApp')
		.factory(
				'theApproval',
				[
						'RestEndpoint',
						'Restangular',
						function(RestEndpoint, Restangular) {
							var item;
							var factory = {
								getEmployeeByListID : getEmployeeByListID,
								updateTotalApproval :updateTotalApproval,
								setItem : setItem,
								getItem : getItem
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
							
							//click button duyet
							function updateTotalApproval(obj) {
								return Restangular
										.all(
												RestEndpoint.CONSTR_COMPLETE_RECORDS_MAP_RS_SERVICE
														+ "/updateTotalApproval").post(
												obj);
							}

						} ]);