angular.module('MetronicApp').factory('report_result_service',
		['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular) {
			var serviceUrl = RestEndpoint.REPORT_RESULT_QUALITY_URL;
			var factory = {
//					createReportReultQuality : createReportReultQuality,
					getListEmployeeByRole : getListEmployeeByRole,
					getListQualityResualt : getListQualityResualt,
					addQualityCableMeaReport : addQualityCableMeaReport,
//					saveOrUpdate : saveOrUpdate,
					updateQualityCableMeaReport : updateQualityCableMeaReport,
					deleteResult : deleteResult,
					exportFile : exportFile
			}
			return factory;
			
			function getListEmployeeByRole(object) {
				return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);			
			}	
			
			function addQualityCableMeaReport(objectReportResult){
				return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL).post(objectReportResult);
			}
			function getListQualityResualt(qualityCableMeaReportId) {
				return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL + "/getListQualityResualt/" + qualityCableMeaReportId).getList();
			}

			
			function updateQualityCableMeaReport(objectReportResult){
				return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL +"/put").post(objectReportResult);
			}
			
			function deleteResult(listIDResult){
				return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/deleteResult").post(listIDResult);
			}
			
			function exportFile(objectReport) {
				return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportFile").post(objectReport);
			}
			
		} ]);