(function() {
	'use strict';

	var controllerId = 'longTermAssetEntityController';

	angular.module('MetronicApp').controller(controllerId,
			longTermAssetEntityController);

	/* @ngInject */
	function longTermAssetEntityController($scope, $rootScope, $timeout,
			$compile, Constant, gettextCatalog, kendoConfig, Restangular,
			$kWindow, longTermAssetEntityService) {
		var vm = this;
		vm.termassetentitys = [];
		vm.allData = [];
		vm.save = save;
		vm.joinMultiSelect = joinMultiSelect;
		vm.joinAll = joinAll;
		vm.joinOne = joinOne;
		vm.termassetentity = {
			voucherType : 2
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
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.termassetentity.groupId = dataItem.groupId; // thành id
	                vm.termassetentity.groupName = dataItem.groupCode;//thành name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.termassetentity.groupName,pageSize:vm.sysGroupOptions.pageSize }).then(function(response){
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
	                    vm.termassetentity.groupId = null; // thành id
	                    vm.termassetentity.groupName = null;//thành name
	                }
	            },
	            ignoreCase: false
	        };
		
		
		longTermAssetEntityService.getCatAssetFolder().then(function(data) {
			vm.folder = data.folder;
		}, function(errResponse) {
			console.error('Error while getCatAssetFolder');
		});
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		// GetData
		vm.voucherType = [{id: 1, name: 'Xây dựng cơ bản'},
		  				  {id: 2, name: 'Mua sắm'}];
		vm.createdSource = [
		  				{id: 1, name: 'Đầu tư xây dựng'},
		  				{id: 2, name: 'Khoa học công nghệ'},
		  				{id: 3, name: 'Chi phí sản xuất kinh doanh'},
		  				{id: 4, name: 'Quỹ phúc lợi'},
		  				{id: 5, name: 'Khác'}];

		$scope.$watch('vm.termassetentity.code',function() {
			vm.termassetentitys = [];
			$scope.$watch('vm.termassetentity.handOverId',function() {
			if (vm.termassetentity.handOverId) {
				vm.isCreatNew = true;
				longTermAssetEntityService.getMerHandOverInfobyId(vm.termassetentity.handOverId).then(function(d) {
									vm.termassetentity.catWarehouseName = d.catWarehouseName;
									vm.termassetentity.sysGroupName = d.sysGroupName;
									vm.termassetentity.receiverName = d.receiverName;
									vm.termassetentity.handOverDate = d.handOverDate;
									vm.isdisable = false;
								})

				longTermAssetEntityService.getMerHandOverEntitybyId(vm.termassetentity.handOverId).then(function(d) {
									for (var k = 0; k < d.length; k++) {
										vm.data = {};
										vm.data.name = d[k].name;
										vm.data.code = d[k].code;
										vm.data.isDevice = d[k].isDevice;
										vm.data.serialNumber = d[k].serialNumber;
										vm.data.count = d[k].count;
										vm.data.originalPrice = d[k].originalPrice;
										vm.data.merEntityId = d[k].merEntityId;
										vm.data.handOverId = d[k].handOverId;
										vm.allData
												.push(vm.data);
									}
									// vm.allData
									// =
									// d.plain();
								})
				var disItem = $("#catAssetCodesGrid tbody tr");
				disItem.remove();
			}

											})
						});
		// $scope.$watch('vm.termassetentity.caacCode',function(){
		// vm.termassetentity.catAssetCodeId = '';
		// vm.termassetentity.caacName ='';
		// });
		// $scope.$watch('vm.catasset.caacName',function(){
		// vm.catasset.catAssetCodeId = '';
		// });
		var lengthGrid = 0;
		var e = 0;
		// Function
		// Remove
		$scope.remove = function(element) {
			if (confirm('Xác nhận xóa')) {
				// ...
				var i = $(element).attr("context");

				// Xoa

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
					vm.allData.push(data);
				}
				vm.catAssetCodeIdTemp = vm.datagridCACs[i].catAssetCodeId;
				vm.datagridCACs.splice(i, 1);
				longTermAssetEntityService.getCaacCodeFull(vm.catAssetCodeIdTemp).then(
								function(d) {
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
								});
			}
		}

		// Check all
		$('#select_all').change(function() {
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

		$scope.changeFileEntity = function() {
			if (typeof $("#infoAssetEntityForm #changeBBBG")[0].files[0] !== "undefined"){
				$("#infoAssetEntityForm #choseBBBG").val(
						$("#infoAssetEntityForm #changeBBBG")[0].files[0].name);
				var formData = new FormData();
				var assetUpload = $("#changeBBBG")[0].files[0];
				formData.append('assetUpload', assetUpload);
				Restangular.one(Constant.UPLOAD_RS_SERVICE).withHttpConfig(
						{
							transformRequest : angular.identity
						}).customPOST(formData, '', undefined, {
					'Content-Type' : 'multipart/form-data'
				}).then(function(successResponse) {
					console.log(successResponse);
					if(successResponse.length>0){
						vm.termassetentity.attachName = $("#changeBBBG")[0].files[0].name;
						vm.termassetentity.attachEncryptedPath = successResponse[0];
						vm.termassetentity.hasUpload = 'Y';
					}
				});
				
			}else{
				vm.termassetentity.attachName = '';
				vm.termassetentity.hasUpload = 'N';
			}
				
		}

		// Function lay ma tai san
		function getCaacCodeFull() {
			longTermAssetEntityService.getCaacCodeFull(vm.catassetcode.catAssetCodeId).then(function(d) {
								vm.catassetcode.lotaIndex = d.lotaIndex;
								vm.catassetcode.subarr = vm.catassetcodes;
								for (var j = 0; j < vm.datagridCACs.length; j++) {
									if (vm.catassetcode.catAssetCodeId == vm.datagridCACs[j].catAssetCodeId) {
										vm.catassetcode.lotaIndex = vm.catassetcode.lotaIndex
												+ vm.datagridCACs[j].subarr.length;
									}
								};
								vm.catassetcode.gruopCode = d.assetGroupCode + vm.catassetcode.lotaIndex;
								vm.catassetcode.total = 0;
								for (var i = 0; i < vm.catassetcodes.length; i++) {
									vm.totalinrow = vm.catassetcodes[i].count
											* vm.catassetcodes[i].originalPrice;
									vm.catassetcode.total = vm.catassetcode.total
											+ vm.totalinrow;
								};
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

				for (var i = 0; i < vm.datagridCACs.length; i++) {
					for (var j = 0; j < vm.datagridCACs[i].subarr.length; j++) {
						var data = new Object();
						data.catAssetCodeId = vm.datagridCACs[i].catAssetCodeId;
						data.merEntityId = vm.datagridCACs[i].subarr[j].merEntityId;
						data.handOverId = vm.termassetentity.handOverId;
						data.handoverCode = vm.termassetentity.handoverCode;
						data.createdSource = vm.termassetentity.createdSource;
						data.voucherType = vm.termassetentity.voucherType;
						data.groupId = vm.termassetentity.idsysGroup;
//						data.attachName = vm.termassetentity.attachName;
						data.attachName = vm.termassetentity.attachEncryptedPath;
						data.hasUpload = vm.termassetentity.hasUpload;
						data.lotaIndex = vm.datagridCACs[i].lotaIndex;
						data.lotaCode = vm.datagridCACs[i].gruopCode;
						vm.dataSave.push(data);
					}
				}

				if (vm.dataSave.length == 0) {
					toastr.error(gettextCatalog.getString("Không có bản ghi mới!"));
					return;
				}
				if(!vm.termassetentity.attachName){
					toastr.error(gettextCatalog.getString("Bạn chưa chọn file đính kèm!"));
					return;
				}
				if (vm.isCreatNew) {
					vm.isdisable = true;
					$("div.loading-effect").show();
					longTermAssetEntityService.addLongTermAsset(vm.dataSave).then(function() {
										toastr.success(gettextCatalog.getString("Lưu thành công"));										
										vm.isCreatNew = false;
										vm.isdisable = false;
										$("div.loading-effect").hide();
									},
									function(errResponse) {
										if(errResponse.status == 409){
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