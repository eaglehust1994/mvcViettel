(function() {
	'use strict';

	var controllerId = 'longTermAssetEntityUpdateController';

	angular.module('MetronicApp').controller(controllerId,
			longTermAssetEntityUpdateController);

	/* @ngInject */
	function longTermAssetEntityUpdateController($scope, $rootScope, $timeout,$compile,
			Constant, gettextCatalog, kendoConfig,Restangular, $kWindow, longTermAssetEntityService) {
		var vm = this;
		vm.termassetentitys = [];
		vm.update = update;;
		vm.updateasset = {};
		vm.dataSave = [];
		vm.commonSearch = {};
		vm.gridCommon = [];

		// GetData
		vm.voucherType = [{id: 1, name: 'Xây dựng cơ bản'},
		  				  {id: 2, name: 'Mua sắm'}];
		vm.createdSource = [
		  				{id: 1, name: 'Đầu tư xây dựng'},
		  				{id: 2, name: 'Khoa học công nghệ'},
		  				{id: 3, name: 'Chi phí sản xuất kinh doanh'},
		  				{id: 4, name: 'Quỹ phúc lợi'},
		  				{id: 5, name: 'Khác'}];
	
		getDataUpdate();
		
		vm.sysGroupOptions = {
	            dataTextField: "name",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.updateasset.groupId = dataItem.groupId; // thành id
	                vm.updateasset.groupName = dataItem.groupCode;//thành name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.updateasset.groupName,pageSize:vm.sysGroupOptions.pageSize }).then(function(response){
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
	                    vm.updateasset.groupId = null; // thành id
	                    vm.updateasset.groupName = null;//thành name
	                }
	            },
	            ignoreCase: false
	        };
		
		function getDataUpdate(){
			$("div.loading-effect").show();
			vm.updateasset.longTermAssetId = longTermAssetEntityService.getId();
			longTermAssetEntityService.getDataMerHandOverId(vm.updateasset.longTermAssetId).then(function(d){
				vm.updateasset.handoverCode = d.code;
				vm.updateasset.catWarehouseName = d.catWarehouseName;
				vm.updateasset.sysGroupName = d.sysGroupName;
				vm.updateasset.idsysGroup = d.idsysGroup;
				vm.updateasset.receiverName = d.receiverName;
				vm.updateasset.handOverId = d.handOverId;
				vm.updateasset.namesysGroup = d.namesysGroup;
				vm.updateasset.createdSource = d.createdSource;
				vm.updateasset.handOverDate = d.handOverDate;
				vm.updateasset.catAssetCodeId = d.catAssetCodeId;
				vm.updateasset.voucherType = d.voucherType;
				vm.updateasset.attachName = d.attachName;
				vm.updateasset.attachNameOld = d.attachName;
				vm.isdisableupdate = false;
				longTermAssetEntityService.getMerHandOverEntityUpdate(vm.updateasset).then(function(v){
					vm.dataUpdate = v.plain();
					$("div.loading-effect").hide();
				})
			},
			function(errResponse) {
				toastr.error(gettextCatalog.getString("Tài sản đã bị xoá hoặc không tồn tại !"));
				$("div.loading-effect").hide();
				return;
			});
		}
		// Check all
		$('#select_all_update').change(function() {
		    var checkboxes = $(this).closest('form').find(':checkbox');
		    if($(this).is(':checked')) {
		        checkboxes.prop('checked', true);
		    } else {
		        checkboxes.prop('checked', false);
		    }
		});
		
		// file đính kèm
		
		// ...
		vm.choseFileEntity = choseFileEntity;
		function choseFileEntity() {
			$("#updatelongtermassetentityForm #changeBBBGUpdate").click();
		}

		$scope.changeFileEntity = function() {
			if (typeof $("#updatelongtermassetentityForm #changeBBBGUpdate")[0].files[0] !== "undefined"){
				$("#updatelongtermassetentityForm #choseBBBGUpdate").val(
						$("#updatelongtermassetentityForm #changeBBBGUpdate")[0].files[0].name);
				var formData = new FormData();
				var assetUpload = $("#changeBBBGUpdate")[0].files[0];
				formData.append('assetUpload', assetUpload);
				Restangular.one(Constant.UPLOAD_RS_SERVICE).withHttpConfig(
						{
							transformRequest : angular.identity
						}).customPOST(formData, '', undefined, {
					'Content-Type' : 'multipart/form-data'
				}).then(function(successResponse) {
					console.log(successResponse);
					if(successResponse.length>0){
						vm.updateasset.attachName = $("#changeBBBGUpdate")[0].files[0].name;
						vm.updateasset.attachEncryptedPath = successResponse[0];
						vm.updateasset.hasUpload = 'Y';
					}
				});
				
			}else{
				vm.updateasset.attachName = '';
				vm.updateasset.hasUpload = 'N';
			}
				
		}
		
		// Function	
		function update(){
			vm.updateassets = [];
			var dataItem = $("#merHandOverEntityUpdateGrid tbody tr");
		    for (var i = 0; i < dataItem.length; i++) {
		    	var checkbox = $(dataItem[i]).find('input[type = "checkbox"]');
		    	if (checkbox.is(':checked')) {
		    		  var selectedRow = new Object();
		    		  selectedRow = JSON.parse($(dataItem[i]).find('span').html());
		    		  selectedRow.longTermAssetId = longTermAssetEntityService.getId();
		    		  selectedRow.catAssetCodeId = vm.updateasset.catAssetCodeId;
		    		  selectedRow.createdSource = vm.updateasset.createdSource;
		    		  selectedRow.voucherType = vm.updateasset.voucherType;
		    		  if(vm.updateasset.attachName != vm.updateasset.attachNameOld){
		    			  selectedRow.attachName = vm.updateasset.attachEncryptedPath;
		    			  selectedRow.hasUpload = vm.updateasset.hasUpload;
		    		  }		    		  
		    		  selectedRow.hasUpload = vm.updateasset.hasUpload;
		    		  selectedRow.groupId = vm.updateasset.idsysGroup;
		    		  vm.updateassets = vm.updateassets.concat(selectedRow);		    		    		 
		    	}			    	
		    }
//		    vm.updateassets.catAssetCodeId = vm.updateasset.catAssetCodeId;
//		    	vm.updateassets = vm.updateassets.concat(vm.updateasset);
		    	vm.isdisableupdate = true;
		    	$("div.loading-effect").show();
		    	  longTermAssetEntityService.updateLongTermAsset(vm.updateassets).then(function() { 
				    	toastr.success(gettextCatalog.getString("Lưu thay đổi thành công"));
				    	vm.isdisableupdate = false;
				    	$("div.loading-effect").hide();
					}, function(errResponse) {
						toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi cập nhật!"));
						vm.isdisableupdate = false;
						$("div.loading-effect").hide();
						return;
					}); 
 
		}
	}		
	
})();
