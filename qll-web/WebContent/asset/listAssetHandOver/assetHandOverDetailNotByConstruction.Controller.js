(function() {
	'use strict';

	var controllerId = 'assetHandOverDetailNotByConstructionController';

	angular.module('MetronicApp').controller(controllerId,
			longTermAssetEntityController);

	/* @ngInject */
	function longTermAssetEntityController($scope, $rootScope, $timeout,
			$compile, Constant, gettextCatalog, kendoConfig, Restangular,CommonService,
			$kWindow, tscdConstantService) {
		var vm = this;
		vm.detail={};
		vm.handOverCode='';
		vm.lstMerEntityData=[];
		vm.allData=[];
		vm.exportFile=function(){
			 var postParam={};
			 postParam.handoverCode=vm.handOverCode;
			Restangular.all(tscdConstantService.rest.url.merHandOverInfoServiceUrl+"/exportMerHandOverDoc").post(postParam).then(
				function(success){
					 var link = document.createElement('a');
	                    link.download = 'exportReportBBBG.docx';
	                    link.href = 'data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,' + success.data;
	                    document.body.appendChild(link);
	                    link.click();
				},function(err){
					 if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
	                        toastr.warning(gettextCatalog.getString(err.data.errorMessage));
	                    }
	                    console.log('Không thể kết nối để lấy dữ liệu: ' + err);
				});
		}
		vm.exportFilePdf=function(){
			 var postParam={};
			 postParam.handoverCode=vm.handOverCode;
			Restangular.all(tscdConstantService.rest.url.merHandOverInfoServiceUrl+"/exportMerHandOverPdf").post(postParam).then(
				function(success){
					 var link = document.createElement('a');
	                    link.download = 'exportReportBBBG.pdf';
	                    link.href = 'data:data:application/pdf;base64,' + success.data;
	                    document.body.appendChild(link);
	                    link.click();
				},function(err){
					 if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
	                        toastr.warning(gettextCatalog.getString(err.data.errorMessage));
	                    }
	                    console.log('Không thể kết nối để lấy dữ liệu: ' + err);
				});
		}
		function init(){
			var tempData=tscdConstantService.tempData;
			if(tempData){
				//Truong hop phieu xuat tu danh sach bien ban ban giao link sang
				if(tempData.type==1){
					if(tempData.handOverCode!=null&&tempData.handOverCode!=''){
						vm.handOverCode=tempData.handOverCode;
						Restangular.one(tscdConstantService.rest.url.merHandOverInfoServiceUrl+'/getMerHandOverInfoDetail').get({"handOverCode":vm.handOverCode}).then(function(success){
							vm.detail=success.plain();
						},function(err){
							console.err(err);
						});
						
						Restangular.one(tscdConstantService.rest.url.merHandOverInfoServiceUrl+'/getMerHandOverEntityByHandOverCode').get({"handOverCode":vm.handOverCode}).then(function(success){
							var d=success.plain();
							for (var k = 0; k < d.length; k++) {
								vm.lstMerEntityData
									.push(d[k]);
							}
							//vm.lstMerEntityData.push(success.plain());
						},function(err){
							console.err(err);
						});
					}	
				}
			}
		};
		init();
	}

})();

