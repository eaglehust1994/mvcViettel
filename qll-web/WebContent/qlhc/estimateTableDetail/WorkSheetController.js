(function() {
	'use strict';

	var controllerId = 'workSheetController';

	angular.module('MetronicApp').controller(controllerId,
			workSheetController);

	/* @ngInject */
	function workSheetController(
			$scope, $rootScope, $timeout, Constant,
			kendoConfig, estimatesTableDetailService, ProposalEvaluation, gettextCatalog, $kWindow,
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

			var formData = new FormData();
			formData.append('filename', file);
			$rootScope.$broadcast("importCT",formData);
		}
		
	
		vm.cancelImport =	function (){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
		vm.exPortCTTDPopup= function (){
			estimatesTableDetailService.exPortCTTDPopup(vm.item).then(function () {
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				toastr.success("Export thành công");
			}, function (e) {
				toastr.error(gettextCatalog.getString("Lỗi !"));
			});
		}
		
	}

})();