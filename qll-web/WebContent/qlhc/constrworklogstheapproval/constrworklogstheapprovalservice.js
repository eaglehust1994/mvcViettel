angular
		.module('MetronicApp')
		.factory(
				'constrWorkLogsTheApproval',
				[
						'RestEndpoint',
						'Restangular',
						function(RestEndpoint, Restangular) {
							var factory = {
								getListEmployeeByRole1 : getListEmployeeByRole1,
								creat : creat,
								exportFilePdf:exportFilePdf,
								exportFileDoc:exportFileDoc,
								appro:appro,
								getRoleId : getRoleId,
								updateLabel:updateLabel
							};
							return factory;

							function getListEmployeeByRole1(object) {
								return Restangular
										.all(
												RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL)
										.post(object);
							}
							function getRoleId() {
								return Restangular.all(
										RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
												+ "/getRoleId").getList();
							}
							function creat(object) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL
												+ "/creat").post(object);
							}
							function updateLabel(object) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL
												+ "/updateLabel").post(object);
							}
							
							function exportFilePdf(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL+"/exportFilePdf").post(criteria);
							}
							
							function appro(dto) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL+"/appro").post(dto);
							}

							//export file docx
							function exportFileDoc(criteria) {
								return Restangular.all(
										RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL+"/exportFileDoc").post(criteria);
							}
							
						} ]);