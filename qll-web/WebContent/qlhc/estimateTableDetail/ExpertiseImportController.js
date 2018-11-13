(function() {
	'use strict';

	var controllerId = 'expertiseImportController';

	angular.module('MetronicApp').controller(controllerId,
			expertiseImportController);

	/* @ngInject */
	function expertiseImportController(
			$scope, $rootScope, $timeout, Constant,
			kendoConfig, estimatesTableDetailService, ProposalEvaluation, gettextCatalog, $kWindow,
			CommonService, Restangular, PopupConst) {
		var vm = this;
		vm.item={};
		vm.item.constructionId = ProposalEvaluation.getItem().constructId;
		vm.imPortKLTD=function(){
			var file = $('#xlf')[0].files[0];
			if (['.xlsx'].indexOf(file.name.substring(file.name.lastIndexOf('.'))) < 0) {
				toastr.warning("Mẫu import chiết tính phải là file excel(.xlsx) ...");
				return false;
			}

			var formData = new FormData();
			formData.append('filename', file);
			$rootScope.$broadcast("importKLTD",formData);
		}
		
	
		vm.cancelImport =	function (){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
		vm.exPortKLTD = function (){
			estimatesTableDetailService.exPortKLTD(vm.item).then(function () {
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				toastr.success("Export thành công");
			}, function (e) {
				toastr.error(gettextCatalog.getString("Lỗi !"));
			});
		}
		
	}

})();