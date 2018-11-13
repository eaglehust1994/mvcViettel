(function() {
	'use strict';

	var controllerId = 'importBieuMauController';

	angular.module('MetronicApp').controller(controllerId,
			importBieuMauController);

	/* @ngInject */
	function importBieuMauController(
			$scope, $rootScope, $timeout, Constant,
			kendoConfig, ProposalEvaluation,reportSceneAriseWeighService, gettextCatalog, $kWindow,
			CommonService, Restangular, PopupConst) {
		var vm = this;
		vm.item={};
		vm.item.constructId = ProposalEvaluation.getItem().constructId;
		
		vm.downloadBieuMau = downloadBieuMau;
		vm.importBieuMau = importBieuMau;
		vm.cancelImport =	function (){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
	
		
		 function downloadBieuMau(){
			 reportSceneAriseWeighService.downloadBieuMau(vm.item).then(function () {
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}, function (e) {
				toastr.error(gettextCatalog.getString("Lỗi, không tải được biểu mẫu !"));
			});
		}

		function importBieuMau(){
			var fileInput = $('#xlf');
			var input = fileInput.get(0);

			var reader = new FileReader();
			if (input.files.length) {
				var textFile = input.files[0];
				reader.readAsBinaryString(textFile);
				reader.onload = function (e) {
					var data = e.target.result;
					try{
					/* Call XLSX */
					var workbook = XLSX.read(data, { type: "binary" });
					/* DO SOMETHING WITH workbook HERE */
					var first_sheet_name = workbook.SheetNames[0];
					/* Get worksheet */
					var worksheet = workbook.Sheets[first_sheet_name];
					var array = XLSX.utils.sheet_to_json(worksheet, { raw: true });
					 }
			        catch(err){
			        	toastr.error("File Import phải là file excel (.xlsx)!");
			        }
					var worklistexcell = [];
					var check = false;
						for (var i = 0; i < array.length; i++) {
								worklistexcell.push(array[i]);
						}
						
					worklistexcell.splice(0,1);
					
					for (var i = 0; i < worklistexcell.length; i++) {
						if(worklistexcell[i].incurredQuantity){
							check=true;
						}else{
							worklistexcell.splice(i,1);
							i--;
						}
					}
					for (var i = 0; i < worklistexcell.length; i++) {
						if(worklistexcell[i].incurredContent){
							check=true;
						}else{
							worklistexcell.splice(i,1);
							i--;
						}
					}
					for (var i = 0; i < worklistexcell.length; i++) {
						if(worklistexcell[i].unit){
							check=true;
						}else{
							worklistexcell.splice(i,1);
							i--;
						}
					}
					for (var i = 0; i < worklistexcell.length; i++) {
						if(worklistexcell[i].designQuantity){
							check=true;
						}else{
							worklistexcell.splice(i,1);
							i--;
						}
					}
//					for (var i = 0; i < worklistexcell.length; i++) {
//						if(worklistexcell[i].workItemType){
//							check=true;
//						}else{
//							worklistexcell.splice(i,1);
//							i--;
//						}
//					}
//					for (var i = 0; i < worklistexcell.length; i++) {
//						if(worklistexcell[i].estimatesItemChildId){
//							check=true;
//						}else{
//							worklistexcell.splice(i,1);
//							i--;
//						}
//					}
//					for (var i = 0; i < worklistexcell.length; i++) {
//						if(worklistexcell[i].note){
//							check=true;
//						}else{
//							worklistexcell.splice(i,1);
//							i--;
//						}
//					}
					if(check == false){
						toastr.warning(gettextCatalog.getString("File excel không hợp lệ, hãy kiểm tra lại dữ liệu trong file excel"));
						return;
					}
					
					$rootScope.$broadcast("importBieuMau", worklistexcell);
					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				};
			} else {
				toastr.warning('Hãy tải lên một tập tin trước khi tiếp tục');
			};
		}
		
		///////////END///////////////
}})();