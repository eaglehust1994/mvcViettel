(function() {
	'use strict';

	var controllerId = 'expertiseImportControllerHD';

	angular.module('MetronicApp').controller(controllerId,
			expertiseImportControllerHD);

	/* @ngInject */
	function expertiseImportControllerHD(
			$scope, $rootScope, $timeout, Constant,
			kendoConfig, ProposalEvaluation, gettextCatalog, $kWindow,
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
					ProposalEvaluation.importCVHD(item).then(function(d) {
	                   	 if (d.error) {
	                         toastr.error(gettextCatalog.getString(d.error));
	                         $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                         return;
	                     }
	                	 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                	 toastr.success("Import công việc hợp đồng thành công");
	                }, function() {
	                        toastr.error(gettextCatalog.getString("Có lỗi xảy ra!"));
	                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                    }).finally(function(){
	                    	vm.disabledButton = false;
						});
					
					
					
					
					//vm.termassetentity.attachName = $("#changeBBBG")[0].files[0].name;
					//vm.termassetentity.attachEncryptedPath = successResponse[0];
					//vm.termassetentity.hasUpload = 'Y';
				}
			});
			
			
			
			
			
			
			
			
			
   /*         $.ajax({
                url: Constant.BASE_SERVICE_URL + "fileservice/uploadTD?folder=kltd",
                type: "POST",
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function(fileName) {
                	ProposalEvaluation.importCVHD(fileName,vm.item.constructionId).then(function(d) {
                   	 if (d.error) {
                         toastr.error(gettextCatalog.getString(d.error));
                         $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                         return;
                     }
                	 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                	 toastr.success("Import công việc hợp đồng thành công");
                }, function() {
                        toastr.error(gettextCatalog.getString("Có lỗi xảy ra!"));
                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                    })
                },
                error: function() {
                    alert('Không thể upload file');
                    $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                }
            });*/
        
		}
		
	
		vm.cancelImport =	function (){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
		vm.exPortKLTD = function (){
			ProposalEvaluation.exPortKLTD(vm.item).then(function () {
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadImport?" + data.fileName;
				toastr.success("Export thành công");
			}, function (e) {
				toastr.error(gettextCatalog.getString("Lỗi !"));
			});
		}
		
	}

})();