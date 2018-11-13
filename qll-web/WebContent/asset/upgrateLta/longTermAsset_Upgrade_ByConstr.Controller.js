(function() {
	'use strict';

	var controllerId = 'longTermAsset_Upgrade_ByConstrController';

	angular.module('MetronicApp').controller(controllerId,
			longTermAsset_Upgrade_ByConstrController);

	/* @ngInject */
	function longTermAsset_Upgrade_ByConstrController($scope, $rootScope, $timeout,
			$compile, Constant, gettextCatalog, kendoConfig, Restangular,CommonService,
			$kWindow, longTermAssetUpgradeService,tscdConstantService,detailassetService) {
		var vm = this;
		vm.termassetentitys = [];
		vm.allData = [];
		vm.save = save;
		
		
		vm.termassetentity = {
			voucherType: 2,
			createdSource:1
		};
		vm.updateassets = [];
		vm.catassetcode = {};
		vm.dataSave = [];
		vm.datagridCACs = [];
		vm.isCreatNew = false;
		var row = 0;
		var index = 0;
		vm.dataHide = [];
		vm.catasset = {};
		vm.folder = '';
		vm.commonSearch={};

		vm.sysGroupOptions = {
			dataTextField: "name",
			select: function (e) {
				var dataItem = this.dataItem(e.item.index());
				vm.termassetentity.groupId = dataItem.groupId; // thành id
				vm.termassetentity.groupName = dataItem.groupCode;//thành name
			},
			pageSize: 10,
			dataSource: {
				serverFiltering: true,
				transport: {
					read: function (options) {
						return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({
							keySearch: vm.termassetentity.groupName,
							pageSize: vm.sysGroupOptions.pageSize
						}).then(function (response) {
							options.success(response);
						}).catch(function (err) {
							console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
						});
					}
				}
			},
			template: '<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.groupCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
			change: function (e) {
				if (e.sender.value() === '') {
					vm.termassetentity.groupId = null; // thành id
					vm.termassetentity.groupName = null;//thành name
				}
			},
			ignoreCase: false
		};
		
		vm.sysUserGroupOptions = {
	            dataTextField: "name",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.termassetentity.useGroupId = dataItem.groupId; // thành id
	                vm.termassetentity.useGroupName = dataItem.groupCode;//thành name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post(
	                        		{keySearch: vm.termassetentity.useGroupName,
	    							pageSize: vm.sysUserGroupOptions.pageSize}).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.groupCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                    vm.termassetentity.useGroupId = null; // thành id
	                    vm.termassetentity.useGroupName = null;//thành name
	                }
	            },
	            ignoreCase: false
	        };


		longTermAssetUpgradeService.getCatAssetFolder().then(function (data) {
			vm.folder = data.folder;
		}, function (errResponse) {
			console.error('Error while getCatAssetFolder');
		});
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		// GetData
		vm.voucherType = [
          //{id: 1, name: 'Xây dựng cơ bản'},
			{id: 2, name: 'Mua sắm'}];
		vm.createdSource = [
			{id: 1, name: 'Đầu tư xây dựng'},
			{id: 2, name: 'Khoa học công nghệ'},
			{id: 3, name: 'Chi phí sản xuất kinh doanh'},
			{id: 4, name: 'Quỹ phúc lợi'},
			{id: 5, name: 'Khác'}];


		var lengthGrid = 0;
		var e = 0;

	

		

		// ...
		vm.choseFileEntity = choseFileEntity;
		function choseFileEntity() {
			$("#changeBBBGUpgrade").click();
		}

		$scope.changeFileEntity = function () {
			if (typeof $("#changeBBBGUpgrade")[0].files[0] !== "undefined") {
				$("#choseBBBGUpgrade").val(
					$("#changeBBBGUpgrade")[0].files[0].name);
				var formData = new FormData();
				var assetUpload = $("#changeBBBGUpgrade")[0].files[0];
				formData.append('assetUpload', assetUpload);
				Restangular.one(Constant.UPLOAD_RS_SERVICE).withHttpConfig(
					{
						transformRequest: angular.identity
					}).customPOST(formData, '', undefined, {
						'Content-Type': 'multipart/form-data'
					}).then(function (successResponse) {
						console.log(successResponse);
						if (successResponse.length > 0) {
							vm.termassetentity.attachName = $("#changeBBBGUpgrade")[0].files[0].name;
							vm.termassetentity.attachEncryptedPath = successResponse[0];
							vm.termassetentity.attachName=successResponse[0];
							vm.termassetentity.attachIdEncrypted=null;
							vm.termassetentity.hasUpload = 'Y';
						}
					});

			} else {
				vm.termassetentity.attachName = '';
				vm.termassetentity.hasUpload = 'N';
			}

		}

		function countDataGridNotHasLtaId(datagridCACs){
			var count=0;
			for(var i=0;i<datagridCACs.length;i++){
				if(datagridCACs.longTermAssetId==null){
					count++;
				}
			}
			return count;
		}
		// Function lay ma tai san
		function getCaacCodeFull() {
			longTermAssetUpgradeService.getCaacCodeFull(vm.catassetcode.catAssetCodeId).then(function (d) {
				vm.catassetcode.lotaIndex = d.lotaIndex;
				vm.catassetcode.subarr = vm.catassetcodes;
				vm.catassetcode.lotaIndex=vm.catassetcode.lotaIndex+countDataGridNotHasLtaId(vm.datagridCACs);
				
				vm.catassetcode.lotaCode = d.assetGroupCode + vm.catassetcode.lotaIndex;
				vm.catassetcode.total = 0;
				for (var i = 0; i < vm.catassetcodes.length; i++) {
					vm.totalinrow = vm.catassetcodes[i].count
					* vm.catassetcodes[i].originalPrice;
					vm.catassetcode.total = vm.catassetcode.total
					+ vm.totalinrow;
				}
				;
				vm.datagridCACs.push(vm.catassetcode);
			});
		}


	

		// Creat data
		function save() {
			var formData = new FormData();
			//var assetUpload = $("#changeBBBGUpgrade")[0].files[0];
			//formData.append('assetUpload', assetUpload);

			if (vm.validator.validate()) {
				
				vm.termassetentity.listAssetEntities=vm.allData;
				vm.termassetentity.lotaType = 1;
				
				vm.isCreatNew=true;
				if (vm.isCreatNew) {
					vm.isdisable = true;
					vm.notAllowToCreateMsg="";
					$("div.loading-effect").show();
					longTermAssetUpgradeService.upgradeLta(vm.termassetentity).then(function (data) {
							toastr.success(CommonService.translate("Upgrade thành công"));
							vm.isCreatNew = false;
							vm.isdisable = true;
							vm.notAllowToCreateMsg="Upgrade thành công";
							//Load lại thông tin từ chỗ capajh nhật
							if(data.plain().length>0){								
								vm.loadInfoFromLtaId(data.plain()[0].longTermAssetId);
							}
							
							$("div.loading-effect").hide();
						},
						function (errResponse) {
							if (errResponse.status == Constant.http.BUSINESS_ERROR) {
								toastr.error(CommonService.translate(errResponse.data.errMessage));
							}
							toastr.error(CommonService.translate("Lỗi xuất hiện khi nâng cấp!"));
							vm.isdisable = true;
							vm.notAllowToCreateMsg="Lỗi xuất hiện khi nâng cấp!";
							$("div.loading-effect").hide();
							return;
						});
				}else{
					//Trương hợp update
					longTermAssetUpgradeService.upgradeLta(vm.termassetentity).then(function (data) {
						toastr.success(CommonService.translate("Lưu thành công"));
						vm.isCreatNew = true;
						vm.isdisable = true;
						if(data.plain().length>0){								
							vm.loadInfoFromLtaId(data.plain()[0].longTermAssetId);
						}
						$("div.loading-effect").hide();
					},function (errResponse) {
						if (errResponse.status == 409) {
							toastr.error(CommonService.translate("Mã tài sản " + errResponse.data.valueErr + " đã tồn tại !"));
						}
						toastr.error(CommonService.translate("Lỗi xuất hiện khi tạo mới!"));
						vm.isdisable = false;
						$("div.loading-effect").hide();
						return;
					});
				}
			}

		}
		
		$scope.$watch('vm.termassetentity.catAssetCodeId', function(id){
			if (vm.termassetentity.longTermAssetId!=null&&vm.termassetentity.longTermAssetId !== 0){
				//Khon thuc hien voi case da co tai san
			}
			else{
				if (typeof id !== "undefined" && id != null && id.toString() != ""){				
					longTermAssetUpgradeService.getCaacCodeFull(id).then(function(d) {
						vm.termassetentity.lotaIndex = d.plain().lotaIndex;
						vm.termassetentity.lotaCode = d.plain().assetGroupCode+vm.termassetentity.lotaIndex ;
					}, function(errResponse){
						console.error('Error while genAssetCode');
					});
				}
				else{
					vm.termassetentity.lotaIndex = '';
					vm.termassetentity.lotaCode = '';
				}
			}
			
		
		});

		
		vm.currentLta={};
		vm.isExistLta=false;
		vm.isAllowToCreate=true;
		vm.notAllowToCreateMsg;
		vm.loadMerhandoverInfo=function(merHandOverInfoId){
			vm.isCreatNew = true;
			vm.termassetentity.handOverId=merHandOverInfoId;
			vm.termassetentity.groupName=CommonService.getUserInfo().groupName;
			vm.termassetentity.groupCode=CommonService.getUserInfo().groupCode;
			vm.termassetentity.groupId=CommonService.getUserInfo().groupId;
//			vm.termassetentity.handOverId=2086369;
			vm.isdisable = false;	
			longTermAssetUpgradeService.loadMerHandOverNotByConstrForUpgrade(vm.termassetentity.handOverId).then(function (d) {
				vm.termassetentity.catWarehouseName = d.catWarehouseName;
								
				vm.termassetentity.useGroupId=d.useGroupId;
				vm.termassetentity.useGroupName=d.sysGroupName;
				vm.termassetentity.receiverName = d.receiverName;
				vm.termassetentity.handOverDate = d.handOverDate;
				vm.termassetentity.handoverCode= d.code;
										
				//Thong tin tai sản cốc định kèm theo
				if(d.longTermAssetId!=null){
					vm.isExistLta=true;
					vm.termassetentity.longTermAssetId=d.longTermAssetId;
					vm.termassetentity.lotaCode=d.lotaCode;
					vm.termassetentity.originalPrice=d.originalPrice;
					vm.termassetentity.isSentErp=d.isSentErp;
					if(tscdConstantService.isAllowSendErpLtaStatus(vm.termassetentity.isSentErp)){
						vm.isAllowToCreate=false;
						vm.notAllowToCreateMsg=CommonService.translate('Tài sản chưa được gửi tài chính');
						vm.isdisable = true;
					}
				}
				if(d.isToAsset != null && d.isToAsset != 0){
					vm.isdisable = true;
					vm.notAllowToCreateMsg=CommonService.translate('Biên bản đã được nâng cấp vào tài sản');
					
				}
			})

			longTermAssetUpgradeService.getMerHandOverEntitybyId(vm.termassetentity.handOverId).then(function (d) {
				for (var k = 0; k < d.length; k++) {
					vm.allData.push(d[k]);
				}
				if(d.length==0&&!vm.isdisable){
					vm.isAllowToCreate=false;
					vm.isdisable=true;
					vm.notAllowToCreateMsg=CommonService.translate('Không tồn tại hàng hóa trong biên bản');
				}
			})		
		};
		
		

		vm.initForm=function(){
			var handOverInfoEntity=tscdConstantService.getTempData();
			if(handOverInfoEntity.type==1){
				//Chuyen sang man hinh
				vm.loadMerhandoverInfo(handOverInfoEntity.handOverId);
			}
		}
		vm.initForm();
		
		vm.viewDetailLta=function(longTermAssetId,voucherType){
					
			vm.dataSet={
					voucherType:2,
					isSentErp:1,
					longTermAssetId:longTermAssetId
			}
			if(vm.dataSet.voucherType==2){
				vm.dataSet.hide=true;
			} else {
				vm.dataSet.hide=false;
			}
			detailassetService.setData(vm.dataSet);
			CommonService.goToMenu("DETAIL_ASSET",{});
			
			if(detailassetService.getFrist()){
				  $rootScope.$broadcast('load.data.detail');
			}
		}
	}

})();

String.prototype.replaceAll = function(strTarget, strSubString) {
	var strText = this;
	strText = strText.toLowerCase();
	var intIndexOfMatch = strText.indexOf(strTarget);
	while (intIndexOfMatch != -1) {
		strText = strText.replace(strTarget, strSubString)
		intIndexOfMatch = strText.indexOf(strTarget);
	}
	return strText.replace('"', '');
}