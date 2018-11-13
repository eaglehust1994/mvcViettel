(function() {
	'use strict';

	var controllerId = 'longTermAssetEntity_UpdateController';

	angular.module('MetronicApp').controller(controllerId,
			longTermAssetEntityController);

	/* @ngInject */
	function longTermAssetEntityController($scope, $rootScope, $timeout,
			$compile, Constant, gettextCatalog, kendoConfig, Restangular,CommonService,
			$kWindow, longTermAssetEntityService) {
		var vm = this;
		vm.termassetentitys = [];
		vm.allData = [];
		vm.save = save;
		vm.joinMultiSelect = joinMultiSelect;
		vm.joinAll = joinAll;
		vm.joinOne = joinOne;
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


		longTermAssetEntityService.getCatAssetFolder().then(function (data) {
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
			/*{id: 1, name: 'Đầu tư xây dựng'},
			{id: 2, name: 'Khoa học công nghệ'},
			{id: 3, name: 'Chi phí sản xuất kinh doanh'},
			{id: 4, name: 'Quỹ phúc lợi'},			
			{id: 5, name: 'Khác'}*/
			{id: 1, name: 'Vốn đầu tư của chủ sở hữu'},
			{id: 2, name: 'Vốn ngân sách NN cấp'},
			{id: 3, name: 'Vốn vay'},
			{id: 4, name: 'Quỹ Phúc lợi'},
			{id: 5, name: 'Quỹ phát triển khoa học và công nghệ'},			
			{id: 6, name: 'Nguồn vốn huy động khác'}
			];


		var lengthGrid = 0;
		var e = 0;

		$scope.remove = function (element) {
			if (confirm('Xác nhận xóa')) {
				var i = $(element).attr("context");
				for (var n = 0; n < vm.datagridCACs[i].subarr.length; n++) {

					var data = new Object();
					data.name = vm.datagridCACs[i].subarr[n].name;
					data.code = vm.datagridCACs[i].subarr[n].code;
					data.isDevice = vm.datagridCACs[i].subarr[n].isDevice;
					data.serialNumber = vm.datagridCACs[i].subarr[n].serialNumber;
					data.count = vm.datagridCACs[i].subarr[n].count;
					data.originalPrice = vm.datagridCACs[i].subarr[n].originalPrice;
					data.merEntityId = vm.datagridCACs[i].subarr[n].merEntityId;
					data.handOverId = vm.datagridCACs[i].subarr[n].handOverId;
//					vm.allData.push(data);
					vm.allData.push(vm.datagridCACs[i].subarr[n]);
				}
				vm.catAssetCodeIdTemp = vm.datagridCACs[i].catAssetCodeId;
				vm.datagridCACs.splice(i, 1);
				/*longTermAssetEntityService.getCaacCodeFull(vm.catAssetCodeIdTemp).then(
					function (d) {
						vm.lotaIndexTemp = d.lotaIndex;
						for (var m = 0; m < vm.datagridCACs.length; m++) {
							if (vm.datagridCACs[m].catAssetCodeId == vm.catAssetCodeIdTemp) {
								vm.datagridCACs[m].lotaIndex = vm.lotaIndexTemp;
								vm.lotaIndexTemp = vm.lotaIndexTemp
								+ vm.datagridCACs[m].subarr.length;
							}
							vm.datagridCACs[m].gruopCode = d.assetGroupCode
							+ "."
							+ vm.datagridCACs[m].lotaIndex;
						}
					});*/
			}
		}

		// Check all
		$('#select_all').change(function () {
			var checkboxes = $(this).closest('form').find(':checkbox');
			if ($(this).is(':checked')) {
				checkboxes.prop('checked', true);
			} else {
				checkboxes.prop('checked', false);
			}
		});

		// ...
		vm.choseFileEntity = choseFileEntity;
		function choseFileEntity() {
			$("#infoAssetEntityForm #changeBBBG").click();
		}

		$scope.changeFileEntity = function () {
			if (typeof $("#infoAssetEntityForm #changeBBBG")[0].files[0] !== "undefined") {
				$("#infoAssetEntityForm #choseBBBG").val(
					$("#infoAssetEntityForm #changeBBBG")[0].files[0].name);
				var formData = new FormData();
				var assetUpload = $("#changeBBBG")[0].files[0];
				formData.append('assetUpload', assetUpload);
				Restangular.one(Constant.UPLOAD_RS_SERVICE).withHttpConfig(
					{
						transformRequest: angular.identity
					}).customPOST(formData, '', undefined, {
						'Content-Type': 'multipart/form-data'
					}).then(function (successResponse) {
						console.log(successResponse);
						if (successResponse.length > 0) {
							vm.termassetentity.attachName = $("#changeBBBG")[0].files[0].name;
							vm.termassetentity.attachEncryptedPath = successResponse[0];
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
			longTermAssetEntityService.getCaacCodeFull(vm.catassetcode.catAssetCodeId).then(function (d) {
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

		// Button Merge Cell
		function joinMultiSelect() {
			if (vm.catasset.catAssetCodeId == '' || vm.catasset.catAssetCodeId == undefined) {
				toastr.error(gettextCatalog.getString("Bạn chưa nhập tên tài sản!"));
				return;
			}
			var data = {};
			data.catAssetCodeId = vm.catasset.catAssetCodeId;
			data.caacName = vm.catasset.caacName;
			vm.catassetcodes = [];
			vm.catassetcode = {};
			if (vm.allData.length == 0) {
				toastr.error(gettextCatalog
					.getString("Hàng hoá đã được chọn hết !"));
				return;
			}
			vm.catassetcode.catAssetCodeId = data.catAssetCodeId;
			vm.catassetcode.caacName = data.caacName;
			var disItem = $("#merHandOverEntityGrid tbody tr");
			for (var i = 0; i < disItem.length; i++) {
				var checkbox = $(disItem[i]).find('input[type = "checkbox"]');
				if (checkbox.is(':checked')) {
					var selectedRow = new Object();
					selectedRow = JSON.parse($(disItem[i]).find('span').html());
					vm.catassetcodes = vm.catassetcodes.concat(selectedRow);
					checkbox.parent().parent().remove();
					for (var j = 0; j < vm.allData.length; j++) {
						if (vm.allData[j].merEntityId == selectedRow.merEntityId) {
							vm.allData.splice(j, 1);
							break;
						}
					}
				}
			}
			if (selectedRow == undefined) {
				toastr.error(gettextCatalog
					.getString("Bạn chưa chọn hàng hoá nào !"));
				return;
			}
			getCaacCodeFull();
		}

		function joinAll() {
			if (vm.catasset.catAssetCodeId == ''
				|| vm.catasset.catAssetCodeId == undefined) {
				toastr.error(gettextCatalog
					.getString("Bạn chưa nhập tên tài sản!"));
				return;
			}
			if (vm.allData.length == 0) {
				toastr.error(gettextCatalog
					.getString("Hàng hoá đã được chọn hết !"));
				return;
			}
			var data = {};
			data.catAssetCodeId = vm.catasset.catAssetCodeId;
			data.caacName = vm.catasset.caacName;
			vm.catassetcodes = [];
			vm.catassetcode = {};
			vm.catassetcode.catAssetCodeId = data.catAssetCodeId;
			vm.catassetcode.caacName = data.caacName;
			var disItem = $("#merHandOverEntityGrid tbody tr");
			for (var i = 0; i < disItem.length; i++) {

				var selectedRow = new Object();
				selectedRow = JSON.parse($(disItem[i]).find('span').html());
				vm.catassetcodes = vm.catassetcodes.concat(selectedRow);

			}
			disItem.remove();
			vm.allData = [];
			getCaacCodeFull();
		}

		function joinOne() {
			if (vm.catasset.catAssetCodeId == ''
				|| vm.catasset.catAssetCodeId == undefined) {
				toastr.error(gettextCatalog
					.getString("Bạn chưa nhập tên tài sản!"));
				return;
			}
			if (vm.allData.length == 0) {
				toastr.error(gettextCatalog
					.getString("Hàng hoá đã được chọn hết !"));
				return;
			}
			var data = {};
			data.catAssetCodeId = vm.catasset.catAssetCodeId;
			data.caacName = vm.catasset.caacName;
			vm.catassetcodes = [];
			vm.catassetcode = {};

			vm.catassetcode.catAssetCodeId = data.catAssetCodeId;
			vm.catassetcode.caacName = data.caacName;
			var disItem = $("#merHandOverEntityGrid tbody tr");
			for (var i = 0; i < disItem.length; i++) {
				var checkbox = $(disItem[i]).find('input[type = "checkbox"]');
				if (checkbox.is(':checked')) {
					var selectedRow = new Object();
					selectedRow = JSON.parse($(disItem[i]).find('span').html());
					vm.catassetcodes = vm.catassetcodes.concat(selectedRow);
					checkbox.parent().parent().remove();
					for (var j = 0; j < vm.allData.length; j++) {
						if (vm.allData[j].merEntityId == selectedRow.merEntityId) {
							vm.allData.splice(j, 1);
							break;
						}
					}
					if (selectedRow == undefined) {
						toastr.error(gettextCatalog
							.getString("Bạn chưa chọn hàng hoá nào !"));
						return;
					}
					getCaacCodeFull();
					break;
				}
			}

		}

		vm.commonSearch = {};
		vm.gridCommon = [];

		// Creat data
		function save() {
			var formData = new FormData();
			var assetUpload = $("#changeBBBG")[0].files[0];
			formData.append('assetUpload', assetUpload);

			if (vm.validator.validate()) {
				if (vm.allData.length > 0) {
					toastr
						.error(gettextCatalog
							.getString("Phải ghép toàn bộ hàng hóa trước khi lưu!"));
					return;
				}
				vm.dataSave=[];
				for (var i = 0; i < vm.datagridCACs.length; i++) {
					for (var j = 0; j < vm.datagridCACs[i].subarr.length; j++) {
						var data = new Object();
						data.longTermAssetId=vm.datagridCACs[i].longTermAssetId;
						data.catAssetCodeId = vm.datagridCACs[i].catAssetCodeId;
						data.merEntityId = vm.datagridCACs[i].subarr[j].merEntityId;
						data.handOverId = vm.termassetentity.handOverId;
						data.handoverCode = vm.termassetentity.handoverCode;
						data.createdSource = vm.termassetentity.createdSource;
						data.voucherType = vm.termassetentity.voucherType;
						data.groupId = vm.termassetentity.groupId;
						data.useGroupId=vm.termassetentity.useGroupId;
						data.depreciationStartDate=vm.termassetentity.handOverDate;
//						data.attachName = vm.termassetentity.attachName;
						
						data.attachName = vm.termassetentity.attachEncryptedPath;
						data.attachIdEncrypted=vm.termassetentity.attachIdEncrypted;
						data.hasUpload = vm.termassetentity.hasUpload;
						data.lotaIndex = vm.datagridCACs[i].lotaIndex;
//						data.lotaCode = vm.datagridCACs[i].gruopCode;
						data.lotaCode = vm.datagridCACs[i].lotaCode;
						data.nationalName=vm.datagridCACs[i].nationalName;
						data.description=vm.datagridCACs[i].description;
						data.madeYear=vm.datagridCACs[i].madeYear;
						vm.dataSave.push(data);
					}
				}

				if (vm.dataSave.length == 0) {
					toastr.error(gettextCatalog.getString("Không có bản ghi mới!"));
					return;
				}
				/*if (!vm.termassetentity.attachName) {
					toastr.error(gettextCatalog.getString("Bạn chưa chọn file đính kèm!"));
					return;
				}*/
				if (vm.isCreatNew) {
					vm.isdisable = true;
					$("div.loading-effect").show();
					longTermAssetEntityService.addLongTermAsset(vm.dataSave).then(function (data) {
							toastr.success(gettextCatalog.getString("Lưu thành công"));
							vm.isCreatNew = false;
							vm.isdisable = false;
							
							//Load lại thông tin từ chỗ capajh nhật
							if(data.plain().length>0){								
								vm.loadInfoFromLtaId(data.plain()[0].longTermAssetId);
							}
							
							$("div.loading-effect").hide();
						},
						function (errResponse) {
							if (errResponse.status == 409) {
								toastr.error(gettextCatalog.getString("Mã tài sản " + errResponse.data.valueErr + " đã tồn tại !"));
							}
							toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới!"));
							vm.isdisable = false;
							$("div.loading-effect").hide();
							return;
						});
				}else{
					//Trương hợp update
					longTermAssetEntityService.updateLTA(vm.dataSave).then(function (data) {
						toastr.success(gettextCatalog.getString("Lưu thành công"));
						vm.isCreatNew = false;
						vm.isdisable = false;
						if(data.plain().length>0){								
							vm.loadInfoFromLtaId(data.plain()[0].longTermAssetId);
						}
						$("div.loading-effect").hide();
					},function (errResponse) {
						if (errResponse.status == 409) {
							toastr.error(gettextCatalog.getString("Mã tài sản " + errResponse.data.valueErr + " đã tồn tại !"));
						}
						toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới!"));
						vm.isdisable = false;
						$("div.loading-effect").hide();
						return;
					});
				}
			}

		}

		$scope.$watch($rootScope.Asset_LongTermAsset_Update_init, function () {
			if ($rootScope.Asset_LongTermAsset_Update_init != null) {
				if ($rootScope.Asset_LongTermAsset_Update_init.type == 1) {
					vm.isCreatAsset = true;
					vm.loadMerhandoverInfo($rootScope.Asset_LongTermAsset_Update_init.value);
				}else if($rootScope.Asset_LongTermAsset_Update_init.type == 2){
					vm.isCreatAsset = false;
					vm.loadInfoFromLtaId($rootScope.Asset_LongTermAsset_Update_init.value);
						
					
				}
				$rootScope.Asset_LongTermAsset_Update_init = null;
			}
		});

		vm.loadMerhandoverInfo=function(merHandOverInfoId){
			vm.isCreatNew = true;
			vm.termassetentity.handOverId=merHandOverInfoId;
			vm.termassetentity.groupName=CommonService.getUserInfo().groupName;
			vm.termassetentity.groupCode=CommonService.getUserInfo().groupCode;
			vm.termassetentity.groupId=CommonService.getUserInfo().groupId;
			longTermAssetEntityService.getMerHandOverInfobyId(vm.termassetentity.handOverId).then(function (d) {
				vm.termassetentity.catWarehouseName = d.catWarehouseName;
				/*vm.termassetentity.useGroupId = d.useGroupId;
				vm.termassetentity.useGroupName=d.useGroupName;*/
				
				vm.termassetentity.useGroupId=d.useGroupId;
				vm.termassetentity.useGroupName=d.sysGroupName;
				vm.termassetentity.receiverName = d.receiverName;
				vm.termassetentity.handOverDate = d.handOverDate;
				vm.termassetentity.handoverCode= d.code;
				vm.isdisable = false;
			})

			longTermAssetEntityService.getMerHandOverEntitybyId(vm.termassetentity.handOverId).then(function (d) {
				for (var k = 0; k < d.length; k++) {
					vm.allData
						.push(d[k]);
				}
			})
			var disItem = $("#catAssetCodesGrid tbody tr");
			disItem.remove();
		};
		
		
		vm.loadInfoFromLtaId=function(longTermAssetId){
			$("div.loading-effect").show();
			vm.termassetentity={};
			vm.termassetentity.longTermAssetId = longTermAssetId;
			longTermAssetEntityService.getMerHandOverInfoByLtaId(longTermAssetId).then(function(d){
				vm.termassetentity.catWarehouseName = d.catWarehouseName;
				vm.termassetentity.sysGroupName = d.sysGroupName;
				//vm.termassetentity.useGroupName=d.sysGroupName;
				//vm.termassetentity.useGroupId=d.useGroupId;
				vm.termassetentity.receiverName = d.receiverName;
				vm.termassetentity.handOverDate = d.handOverDate;
				vm.termassetentity.handoverCode= d.code;
				vm.termassetentity.handOverId=d.handOverId;
				vm.isdisable = false;
				longTermAssetEntityService.getMerHandOverInfoDetailByHandOverId(vm.termassetentity.handOverId).then(function(v){
					//vm.dataUpdate = v.plain();
					vm.datagridCACs=[];
					var temp=[];
					vm.allData=[];
					for(var i=0;i<v.plain().length;i++){
						if(v.plain()[i].longTermAssetId!=null){
							if(i==0){
								vm.termassetentity.createdSource=v.plain()[i].createdSource;
								vm.termassetentity.voucherType=v.plain()[i].voucherType;
								vm.termassetentity.groupId=v.plain()[i].groupId;
								vm.termassetentity.groupName=v.plain()[i].groupName;
								vm.termassetentity.useGroupId=v.plain()[i].useGroupId;
								vm.termassetentity.useGroupName=v.plain()[i].useGroupName;
								vm.termassetentity.attachName=v.plain()[i].attachName;
								vm.termassetentity.attachIdEncrypted=v.plain()[i].attachIdEncrypted;
								vm.termassetentity.hasUpload='Y';
							}
							var exittLta=false;
							for(var j=0;j<temp.length;j++){
								if(temp[j].longTermAssetId==v.plain()[i].longTermAssetId){
									temp[j].subarr.push({
										merEntityId:v.plain()[i].merEntityId,
										name:v.plain()[i].name,
										code:v.plain()[i].code,
										isDevice:v.plain()[i].isDevice,
										count:v.plain()[i].count,
										originalPrice:v.plain()[i].originalPrice,
										total:parseFloat(v.plain()[i].count) *parseFloat( v.plain()[i].originalPrice),
										serialNumber:v.plain()[i].serialNumber
									});
									temp[j].total +=parseFloat(v.plain()[i].count) * parseFloat(v.plain()[i].originalPrice);
									exittLta=true;
									break;
								}
							}
							if(!exittLta){
								temp.push({
									longTermAssetId:v.plain()[i].longTermAssetId,
									catAssetCodeId:v.plain()[i].catAssetCodeId,
									lotaIndex:v.plain()[i].lotaIndex,
									lotaCode:v.plain()[i].lotaCode,
									nationalName:v.plain()[i].nationalName,
									description:v.plain()[i].description,
									madeYear:v.plain()[i].madeYear,
									subarr:[],
									total:0
								});
								
								temp[temp.length-1].subarr.push({									
										merEntityId:v.plain()[i].merEntityId,
										name:v.plain()[i].name,
										code:v.plain()[i].code,
										isDevice:v.plain()[i].isDevice,
										count:v.plain()[i].count,
										originalPrice:v.plain()[i].originalPrice,
										total:parseFloat(v.plain()[i].count) * parseFloat(v.plain()[i].originalPrice),
										serialNumber:v.plain()[i].serialNumber
									});
								
								temp[temp.length-1].total +=parseFloat(v.plain()[i].count) * parseFloat(v.plain()[i].originalPrice);
							}
						}else{
							vm.allData.push(v.plain()[i]);
						}
					}
					vm.datagridCACs=temp;
					$("div.loading-effect").hide();
				})
				
				
			},
			function(errResponse) {
				toastr.error(gettextCatalog.getString("Tài sản đã bị xoá hoặc không tồn tại !"));
				$("div.loading-effect").hide();
				return;
			});
			
			
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