(function() {
	'use strict';

	var controllerId = 'workSheetControllerHD';

	angular.module('MetronicApp').controller(controllerId,
			workSheetControllerHD);

	/* @ngInject */
	function workSheetControllerHD(
			$scope, $rootScope, $timeout, Constant,
			kendoConfig, ProposalEvaluation, gettextCatalog, $kWindow,
			CommonService, Restangular, PopupConst) {
		var vm = this;
		vm.item={};
		vm.item.constructionId = ProposalEvaluation.getItem().constructId;
		vm.imPortCTTD=function(){
			var file = $('#xlf')[0].files[0];
			if (['.xlsx'].indexOf(file.name.substring(file.name.lastIndexOf('.'))) < 0) {
				toastr.warning("Mẫu import chiết tính phải là file excel(.xlsx) ...");
				return false;
			}
			var constructionId=ProposalEvaluation.getItem().constructId;
			var formData = new FormData();
			formData.append('filename', file);
			var formData = new FormData();
			formData.append('filename', file);;
			Restangular.one(Constant.FILE_SERVICE_TEMP).withHttpConfig(
					{
						transformRequest : angular.identity
					}).customPOST(formData, '', undefined, {
				'Content-Type' : 'multipart/form-data'
			}).then(function(successResponse) {
				console.log(successResponse);
				if(successResponse.length>0){
					
					var item = {pathFile:successResponse[0],constructionId:vm.item.constructionId}
					vm.disabledButton = true;
					ProposalEvaluation.importCT(item).then(function(d) {
						 if (d.error) {
                             toastr.error(gettextCatalog.getString(d.error));
                             $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                             return;
                         }
                    	 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                    	 toastr.success("Import chiết tính thành công");
                    }, function() {
                        toastr.error(gettextCatalog.getString("Có lỗi xảy ra!"));
                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                    }).finally(function(){
                    	vm.disabledButton = false;
					});
				}
			});

		}
		vm.cancelImport =	function (){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
		vm.exPortCTHDPopup= function (){
			ProposalEvaluation.exPortCTTDPopup(vm.item).then(function () {
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadImport?" + data.fileName;
				toastr.success("Export thành công");
			}, function (e) {
				toastr.error(gettextCatalog.getString("Lỗi !"));
			});
		}
		
	}

})();