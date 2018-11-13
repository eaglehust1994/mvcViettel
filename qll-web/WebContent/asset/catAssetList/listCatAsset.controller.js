(function() {
	'use strict';
	var controllerId = 'catAssetListController';
	
	angular.module('MetronicApp').controller(controllerId, catAssetListController);
	
	function catAssetListController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,detailCatAssetUpdateService, longTermAssetEntityService,detailassetService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant,tscdConstantService) {
		var vm = this;
		vm.showSearch = true;
		vm.criteria={
			isSentErp:tscdConstantService.allId
		};
		vm.groupId;
		vm.userId;
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			vm.groupId=CommonService.getUserInfo().groupId;
			vm.userId=CommonService.getUserInfo().userId;
			fillDataTable([]);
		}
		 vm.sysUserGroupOptions = {
		            dataTextField: "name",
		            select: function(e) {
		                var dataItem = this.dataItem(e.item.index());
		                vm.criteria.useGroupId = dataItem.groupId; // thành id
		                vm.criteria.useGroupName = dataItem.groupCode;//thành name
		            },
		            pageSize: 10,
		            dataSource: {
		                serverFiltering: true,
		                transport: {
		                    read: function(options) {
		                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.criteria.useGroupName,pageSize:vm.sysUserGroupOptions.pageSize }).then(function(response){
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
		                    vm.criteria.useGroupId = null; // thành id
		                    vm.criteria.useGroupName = null;//thành name
		                }
		            },
		            ignoreCase: false
		        };
		 
		 vm.sysGroupOptions = {
		            dataTextField: "name",
		            select: function(e) {
		                var dataItem = this.dataItem(e.item.index());
		                vm.criteria.groupId = dataItem.groupId; // thành id
		                vm.criteria.groupName = dataItem.groupCode;//thành name
		            },
		            pageSize: 10,
		            dataSource: {
		                serverFiltering: true,
		                transport: {
		                    read: function(options) {
		                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.criteria.groupName,pageSize:vm.sysGroupOptions.pageSize }).then(function(response){
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
		                    vm.criteria.groupId = null; // thành id
		                    vm.criteria.groupName = null;//thành name
		                }
		            },
		            ignoreCase: false
		        };
		 vm.isShowEdit=function(dataItem){
//			 var template='ng-if="';
			 if(tscdConstantService.isNotAllowEditLtaStatus(dataItem.isSentErp)){
//				 template+=false+"'";
				 return false;
			 }
			// var template='ng-if="';
			 return true;
		 }
		 vm.isShowSendErp=function(dataItem){			 
			 if(tscdConstantService.isAllowSendErpLtaStatus(dataItem.isSentErp)){
				 return true;
			 }
			 return false;
		 }
		 vm.isShowRemove=function(dataItem){
			 if(tscdConstantService.isNotAllowEditLtaStatus(dataItem.isSentErp)){
				 return false;
			 }
			 return true;
		 }
		 vm.isShowCancelUpgrade=function(dataItem){
			 if(!tscdConstantService.isAllowSendErpLtaStatus(dataItem.isSentErp)){
				 if(dataItem.upgradeStatus!=null){
					 return true;
				 }				 
			 }
			 return false;
		 }
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=longTermAssetId#"'+
				'disble="" ng-click=vm.edit(#=longTermAssetId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=longTermAssetId#"'+
				'ng-click=vm.send(#=longTermAssetId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=longTermAssetId#"'+
				'ng-click=vm.remove(#=longTermAssetId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=longTermAssetId#"'+
				'ng-click=vm.cancelUpgradeLta(#=longTermAssetId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:{
					serverPaging: true,
					 schema: {
		                    data: function (data) {
		                        return data.rows;
		                    },
		                    total: function (data) {
		                        return data.totalRow;
		                    }
		                },
					transport: {
						read:function (e) {
							var pagingParam={
		                            size: e.data.pageSize,
		                            page:e.data.page
		                        };		                     		                  
		                        //Thuc hien viec goi service
	                        detailCatAssetUpdateService.getLstAssetPaging(vm.criteria,pagingParam).then(function (response) {
	                            e.success(response.plain());
	                        }).catch(function (err) {
	                            if(err.data && err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
	                                toastr.warning(CommService.translate(err.data.errorMessage));
	                            }
	                            e.success([]);
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err);
	                        });
						},					
						parameterMap: function (options, type) {
                              //  vm.criteria.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.criteria.page = options.page
								vm.criteria.pageSize = options.pageSize
								vm.criteria.checktype = vm.checktype.checktype

								return JSON.stringify(vm.criteria)

						}
					},					 
					pageSize: 20
				},
//				dataSource: data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#catAssetGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				, {
					title: "Thao tác",
			        template:
			        	'<div class="text-center"><button type="button"'+
					' ng-if=vm.isShowEdit(dataItem) class="btn btn-default padding-button box-shadow  #=longTermAssetId#"'+
					' ng-click=vm.edit(#=longTermAssetId#)>'+
					'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=longTermAssetId#"'+
					'ng-if=vm.isShowSendErp(dataItem) ng-click=vm.send(#=longTermAssetId#)>'+
					'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=longTermAssetId#"'+
					'ng-if=vm.isShowRemove(dataItem) ng-click=vm.remove(#=longTermAssetId#)>'+
					'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'
					+
					'<button type="button" class="btn btn-default padding-button box-shadow #=longTermAssetId#"'+
					' ng-if=vm.isShowCancelUpgrade(dataItem) ng-click=vm.cancelUpgradeLta(#=longTermAssetId#)>'+
					'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'
					+'</div>',
			        width: 150,
			        field:"stt"
				}
				, {
					title: "Mã tài sản",
					field: 'lotaCode',
			        template: '<a class="#=longTermAssetId#" href="javascript:void(0);" ng-click=vm.showDetail(#=longTermAssetId#)>#=lotaCode#</a>',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên tài sản",
			        field: 'assetNameName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại tài sản",
			        field: 'assetTypeName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị sử dụng",
			        field: 'useGroupName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị hạch toán",
			        field: 'groupName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Nguyên giá tài sản (VNĐ)",
			        field: 'originalPrice',
			        template: "# if(originalPrice == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(originalPrice, 'n') #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Giá trị KH lũy kế (VNĐ)",
			        field: 'depreciatiedValue',
			        template: "# if(depreciatiedValue == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(depreciatiedValue, 'n') #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Giá trị sổ sách còn lại (VNĐ)",
			        field: 'residualPrice',
			        template: "# if(residualPrice == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(residualPrice, 'n') #"+ "#} #",
			        width: 210,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Ngày BG ĐVSD",
			        field: 'depreciationStartDate',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, {
					title: "Phân loại Tài sản",
			        field: 'lotaType',
			        template :  "# if(lotaType == 2){ #" + "#= 'Công cụ dụng cụ' #" + "# } " + "else if (lotaType == 1) { # " + "#= 'Tài sản cố định' #"+ "#} #",
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				 {
					title: "Nguồn hình thành tài sản",
			        field: 'voucherType',
			        template :  "# if(voucherType == 2){ #" + "#= 'Mua sắm' #" + "# } " + "else if (voucherType == 1) { # " + "#= 'Xây dựng cơ bản' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thời gian đã khấu hao (ngày)",
			        field: 'depreciatedTime',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thời hạn sử dụng",
			        field: 'abc',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Trạng thái đồng bộ",
			        template :  "# if(isSentErp == 0){ #" + 
			        					"#= 'Chưa gửi tài chính' #" + "# } " + 
			        					"else if (isSentErp == 1) { # " + "#= 'Đã gửi tài chính' #"+ "#} "+
			        					"else if (isSentErp == 3) { # " + "#= 'Từ chối duyệt' #"+ "#} "+
			        					" else if (isSentErp == 2) { # " + "#= 'Đã duyệt' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					 field: 'isSentErp',
				}, {
					title: "Trạng thái hình thành TS",
					 field: 'lotaStatus',
			        template :  "# if(lotaStatus == 0){ #" + "#= 'Tạm tăng' #" + "# } " + "else if (lotaStatus == 1) { # " + "#= 'Đã quyết toán' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		
		
		
		vm.type = [{id: 0, name: 'Tất cả'},{id: 1, name: 'Tài sản cố định'}, {id: 2, name: 'Công cụ dụng cụ'}];
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
		vm.voucherType=[{id:0, name: 'Tất cả'},
					{id: 1, name: 'Xây dựng cơ bản'},
					{id: 2, name: 'Mua sắm'},
		          ]
		
		function fetchAll() {
			detailCatAssetUpdateService.fetchAll(vm.item).then(
					function(d) {
						refreshGrid(d.plain());
					}, function(errResponse) {
						toastr.error("Lỗi~~~~~~~~");
					});
		}
		
		function refreshGrid(d) {
			var grid = vm.catAssetGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.commonSearch = {
				keySearch: '',
				pageSize: '',
				level: '',
				parentId: ''
			};
			vm.searchConstruct = {
				keySearch: '',
				pageSize: '',
				level: '',
				parentId: ''
			};
			vm.useGroupSearch = {
				keySearch: '',
				pageSize: '',
				level: '',
				parentId: ''
			};
			
			vm.assetGroupSearch = {caacName: '', caacCode: '', caacLevel: 1};
			vm.lotaTypeSearch = {caacName: '', caacCode: '', caacLevel: 2};
			vm.catAssetSourceSearch = {caacName: '', caacCode: '', caacLevel: 3};
			vm.catAssetCodeSearch = {caacName: '', caacCode: '', caacLevel: 4};
			
		vm.gridCommon = [{
			title: "Mã", 
			field: "value", 
			width: 120
		}, {
			title: "Tên", 
			field: "name", 
			width: 120
		}];
		
		
		vm.status=[{id:2 , name: 'Tất cả'},
		           {id: 0, name: 'Tạm tăng'},
		           {id: 1, name: 'Đã quyết toán'},
		           ]
		vm.isSentErp=tscdConstantService.lstIsSentErpSearch;
		/*[{id:2, name: 'Tất cả'},
		           {id: 1, name: 'Đã đồng bộ'},
		           {id: 0, name: 'Chưa đồng bộ'},
		           ]*/
		
		$scope.$watch('vm.criteria.assetGroupId',function(id){
			vm.lotaTypeSearch.superParentId = id;
			if (typeof detailCatAssetUpdateService.getId() !== "undefined" && detailCatAssetUpdateService.getId() != null && detailCatAssetUpdateService.getId().toString() != ""){
				// Sua thong tin
				detailCatAssetUpdateService.setId("");
			}
			
		});
		
		$scope.$watch('vm.criteria.assetTypeId',function(id){
			vm.catAssetSourceSearch.superParentId = id;
			if (typeof detailCatAssetUpdateService.getId() !== "undefined" && detailCatAssetUpdateService.getId() != null && detailCatAssetUpdateService.getId().toString() != ""){
				// Sua thong tin
				detailCatAssetUpdateService.setId("");
			}
			else{
				// Them moi
//				vm.criteria.catAssetSourceId = '';
//				vm.criteria.catAssetSourceName = '';
//				vm.criteria.catAssetCodeId = '';
//				vm.criteria.catAssetCodeName = '';
//				vm.criteria.lotaCode = '';
			}
		});
		
		$scope.$watch('vm.criteria.assetSourceId',function(id){
			vm.catAssetCodeSearch.superParentId = id;
			if (typeof detailCatAssetUpdateService.getId() !== "undefined" && detailCatAssetUpdateService.getId() != null && detailCatAssetUpdateService.getId().toString() != ""){
				// Sua thong tin
				detailCatAssetUpdateService.setId("");
			}
			else{
				// Them moi
//				vm.criteria.catAssetCodeId = '';
//				vm.criteria.catAssetCodeName = '';
//				vm.criteria.lotaCode = '';
			}
		});
		
		vm.edit=edit;
		function edit(id){
			var grid=vm.catAssetGrid;
			var item=grid.table.find("tr a." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			vm.dataSet=	grid.dataSource.getByUid(uid);
			if(!tscdConstantService.isNotAllowEditLtaStatus(vm.dataSet.isSentErp)){
//			if(vm.dataSet.isSentErp===0){
				if(vm.dataSet.voucherType==2){//Loai tai san hinh thanh khong qua xay lap
				
					 console.debug("chuyen sang hinh thanh khong qua xay lap tu man hinh danh sach tai san");
	                $rootScope.Asset_LongTermAsset_Update_init={
	                        type:2,
	                        value:id
                    };
	                CommonService.goToMenu("Asset_LongTermAsset_Update",{});
				} else if(vm.dataSet.voucherType==1){ // tai san hinh thanh qua xay lap
					if(1==vm.dataSet.isOfferSlip){
						$rootScope.Asset_CatAssetDetail_FromOfferSlip={
			                    type:2,
			                    value:id
			                };
						 CommonService.goToMenu("Asset_HinhThanhTS_TuPhieuDeNghi",{});
					}else{
						console.debug("chuyen sang hinh thanh qua xay lap");	            
		                $rootScope.Asset_CatAssetDetail_init={
		                    type:2,
		                    value:id
		                };
		                CommonService.goToMenu("Asset_CatAssetDetail_update",{});
					}
				}
		} else {
			toastr.error(gettextCatalog.getString("Bản ghi đã được gửi tài chính!"));
		}
		}
		function goTo(menuKey) {
			var template = Constant.getTemplateUrl(menuKey);
			postal.publish({
				channel: "Tab",
				topic: "open",
				data: template
			});
			$rootScope.isCreatAsset = false;
			$rootScope.$broadcast("cat.detail.reload");
		}
		
		vm.send=send;
			function send(id){
				var grid=vm.catAssetGrid;
				var item=grid.table.find("tr a." +id);
				var uid=$(item).parent().parent().attr("data-uid");
				vm.dataSet=	grid.dataSource.getByUid(uid);
				if(tscdConstantService.isAllowSendErpLtaStatus(vm.dataSet.isSentErp)){
					detailCatAssetUpdateService.send(id).then(
							function(d) {
								toastr.success("Gửi tài chính thành công!");
								doSearch();
							}, function(errResponse) {
								console.log(errResponse);
								if (errResponse.data.status == Constant.http.BUSINESS_ERROR) {
									toastr.error(CommonService.translate(errResponse.data.errorMessage));
								}else{
									toastr.error("Gửi tài chính thất bại!");
								}
								
								
							});
				} else {
					toastr.error(gettextCatalog.getString("Bản ghi đã được gửi tài chính!"));
					return;
				}
		}
		
		vm.remove=remove;
		function remove(id){
			var grid=vm.catAssetGrid;
			var item=grid.table.find("tr a." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			vm.dataSet=	grid.dataSource.getByUid(uid);
			if(!tscdConstantService.isNotAllowEditLtaStatus(vm.dataSet.isSentErp) && confirm('Xác nhận xóa')){
				detailCatAssetUpdateService.remove(id).then(
						function(d) {
							toastr.success("Xóa thành công!");
							doSearch();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} else if(tscdConstantService.isNotAllowEditLtaStatus(vm.dataSet.isSentErp)) {
				toastr.error(gettextCatalog.getString("Bản ghi đã được gửi tài chính!"));
				return;
			}
	}
		vm.cancelUpgradeLta= cancelUpgradeLta
			function cancelUpgradeLta(id){
			var data={longTermAssetId:id};
			detailCatAssetUpdateService.cancelUpgradeLta(data).then(
					function(d) {
						toastr.success("Hủy nâng cấp thành công!");
						doSearch();
					}, function(err) {
						console.log(err);
						 if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
		                        toastr.warning(CommonService.translate(err.data.errorMessage));
		                    }
						
						
						//toastr.error("Lỗi không xóa được!");
					});
		}
		
		vm.showDetail=showDetail;
		
		function showDetail(id){
			var grid=vm.catAssetGrid;
			var item=grid.table.find("tr a." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			vm.dataSet=	grid.dataSource.getByUid(uid);
			if(voucherType==2){
				vm.dataSet.hide=true;
			} else {
				vm.dataSet.hide=false;
			}
			detailassetService.setData(vm.dataSet);
			goTo("DETAIL_ASSET");
			
			if(detailassetService.getFrist()){
				  $rootScope.$broadcast('load.data.detail');
			}
			
		}
		
		
		vm.canceldoSearch= function (){
			vm.criteria={};
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			vm.catAssetGrid.dataSource.page(1);			
		}
		
	/*	vm.exportExcel=function(){
			detailCatAssetUpdateService.exportExcel(vm.criteria).then(
					function() {
						window.location = "fileservice/download?fileName=" + data.fileName;
						toastr.success("Export thành công");
					}, function(errResponse) {
						toastr.error("Lỗi~~~~~~~~");
					});
		}*/
	}
})();