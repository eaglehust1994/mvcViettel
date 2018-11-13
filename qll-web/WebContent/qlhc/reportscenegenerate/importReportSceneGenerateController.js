//(function() {
//	'use strict';
//
//	var controllerId = 'importReportSceneGenerateController';
//
//	angular.module('MetronicApp').controller(controllerId,
//			importReportSceneGenerateController);
//
//	/* @ngInject */
//	function importReportSceneGenerateController(
//			$scope, $rootScope, $timeout, Constant,
//			kendoConfig, ProposalEvaluation,reportSceneGenerateService, gettextCatalog, $kWindow,
//			CommonService, Restangular, PopupConst) {
//		var vm = this;
//		vm.item={};
//		vm.item.constructId = ProposalEvaluation.getItem().constructId;
//		
//		vm.downloadTempleReportSceneGenerate = downloadTempleReportSceneGenerate;
//		vm.importReportSceneGenerate = importReportSceneGenerate;
//		vm.cancelImport =	function (){
//			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
//		}
//		
//	
//		
//		 function downloadTempleReportSceneGenerate(){
//			 reportSceneGenerateService.exportDataImport(vm.item).then(function () {
//					window.location = "fileservice/download?fileName=" + data.fileName;	        	
//			}, function (e) {
//				toastr.error(gettextCatalog.getString("Lỗi, không tải được biểu mẫu !"));
//			});
//		}
//
//		function importReportSceneGenerate(){
//			var fileInput = $('#xlf');
//			var input = fileInput.get(0);
//
//			var reader = new FileReader();
//			if (input.files.length) {
//				var textFile = input.files[0];
//				reader.readAsBinaryString(textFile);
//				reader.onload = function (e) {
//					var data = e.target.result;
//					try{
//					/* Call XLSX */
//					var workbook = XLSX.read(data, { type: "binary" });
//					/* DO SOMETHING WITH workbook HERE */
//					var first_sheet_name = workbook.SheetNames[0];
//					/* Get worksheet */
//					var worksheet = workbook.Sheets[first_sheet_name];
//					var worklistexcell = XLSX.utils.sheet_to_json(worksheet, { raw: true });
//					 }
//			        catch(err){
//			        	toastr.error("File Import phải là file excel (.xlsx)!");
//			        }
//					
//					var check = false;
//						for (var i = 0; i < worklistexcell.length; i++) {
//								worklistexcell.push(worklistexcell[i]);
//						}
//						
//					worklistexcell.splice(0,1);
//					
//					for (var i = 0; i < worklistexcell.length; i++) {
//						if(worklistexcell[i].unit){
//							check=true;
//						}else{
//							worklistexcell.splice(i,1);
//							i--;
//						}
//						
//					}
//					
//					if(check == false){
//						toastr.warning(gettextCatalog.getString("File excel không hợp lệ, hãy kiểm tra lại dữ liệu trong file excel"));
//						return;
//					}
//					
//					$rootScope.$broadcast("importReportSceneGenerate2", worklistexcell);
//					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
//				};
//			} else {
//				toastr.warning('Hãy tải lên một tập tin trước khi tiếp tục');
//			};
//		}
//		
//		///////////END///////////////
//}})();