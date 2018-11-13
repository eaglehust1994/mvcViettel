(function() {
	'use strict';
	var controllerId = 'patternRequirementManagementController';
	
	angular.module('MetronicApp').controller(controllerId, patternRequirementManagementController);
	
	function patternRequirementManagementController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,patternRequirementManagementService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.orderPattern={};
		vm.detailGoods={};
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.removeDetail=removeDetail;
// vm.showDetail = false;
		vm.patternRequirementManagementSearch={
		
		};
		vm.stockSearch={};
		vm.orderPattern={};
		vm.templateGoods='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
        vm.headerTemplate1='<div class="dropdown-header k-widget k-header">' +
        '<span>Mã</span>' +
        '<span>Tên</span>' +
        	'</div>';
        vm.commonSearch = {stt:'', name: '', code: '',goodsUnitName: ''};
        vm.gridCommonGoods = [ {
			title: "Mã hàng",
			field: "code",
			width: 80
		}, {
			title: "Tên hàng",
			field: "name",
			width: 80
		}];
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		vm.patternRequirementManagement={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDataCreatNew([]);
			fillFromName([]);
			fillDatadetailGrid([]);
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=patternRequirementManagementId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=patternRequirementManagementId#"'+
				'disble="" ng-click=vm.edit(#=patternRequirementManagementId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=patternRequirementManagementId#"'+
				'ng-click=vm.send(#=patternRequirementManagementId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=patternRequirementManagementId#"'+
				'ng-click=vm.remove(#=patternRequirementManagementId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=patternRequirementManagementId#"'+
				'ng-click=vm.cancelUpgradeLta(#=patternRequirementManagementId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		 
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				sortable: false,				
				columnMenu: false,
				toolbar: [
                    {
                        name: "actions",
                        template:
                        	'<div class=" pull-left ">'+
                            '<button class="btn btn-qlk padding-search-right addQLK"'+
                            'ng-click="vm.addCreatNew()" uib-tooltip="Thêm mới" translate>Thêm mới</button>'+
          					'</div>'
            				+
                          	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                        '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                        '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                        '<label ng-repeat="column in vm.patternRequirementManagementGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                        '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                        '</label>'+
                        '</div></div>'
                    }
                    ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderPatternServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.patternRequirementManagementSearch.page = options.page
								vm.patternRequirementManagementSearch.pageSize = options.pageSize
								return JSON.stringify(vm.patternRequirementManagementSearch)
						}
					},					 
					pageSize: 20
				},
// dataSource: data,
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
					title: "TT",
					field:"stt",
			        template: dataItem => $("#patternRequirementManagementGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 30,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Tên Mẫu",
					field: 'name',
					 template: '<a class="#=name#" href="javascript:void(0);" ng-click=vm.showDetail(dataItem)>#=name#</a>',
			        width: 180,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Người tạo",
					field: 'createdUserName',
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
					field: 'description',
					width: 180,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thao tác",
			        template:
			        	'<div class="text-center""><a '+
						' class=" icon_table "'+
						' ng-click=vm.edit(dataItem)  uib-tooltip="Sửa" translate>'+
						'<i class="fa fa-pencil" aria-hidden="true"></i>'+
						'</a>'+
							'<a type="button"'+	
								'class="#=orderPatternId# icon_table"  uib-tooltip="Xóa" translate>'+
								'<i class="fa fa-trash" aria-hidden="true" ng-click=vm.remove(dataItem)></i>'+
							'</a>'
								+'</div>',
			        width: 40,
			        field:"stt"
				}
				,]
			});
		}

		vm.openDepartment=openDepartment
		function openDepartment(){
			var obj={};
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findGoodsPopup.html';
				var title = gettextCatalog.getString("Danh sách hàng hóa");
				var data=result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					sortable: false,				
					columnMenu: false,
					dataSource:{
						serverPaging: true,
						 schema: {
							 total: function (response) {
									return response.total; // total is returned in
															// the "total" field of
															// the response
								},
								data: function (response) {
									return response.data; // data is returned in
															// the "data" field of
															// the response
								},
			                },
						transport: {
							read: {
			                        // Thuc hien viec goi service
								url: Constant.BASE_SERVICE_URL + "orderPatternGoodsServiceRest/doSearch",
								contentType: "application/json; charset=utf-8",
								type: "POST"
							},					
							parameterMap: function (options, type) {
								    vm.patternRequirementManagementSearch.page = options.page
									vm.patternRequirementManagementSearch.pageSize = options.pageSize
									return JSON.stringify(vm.patternRequirementManagementSearch)
							}
						},					 
						pageSize: 20
					},
							
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
					columns: [
						{
							title: "STT",
							field:"stt",
					        template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					        width: 30,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
						}
						,  {
							title: "Mã hàng",
							field: 'goodsCode',
					        width: 50,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
							title: "Tên hàng",
					        field: 'goodsName',
					        width: 250,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
							title: "Đơn vị tính",
					        field: 'goodsUnitName',
					        width: 50,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
						title: "Chọn",
						field: "text",
						width: 30
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%');
			});
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
// vm.showDetail = false;
			var grid =vm.patternRequirementManagementGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.patternRequirementManagementGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.patternRequirementManagementGrid.showColumn(column);
            } else {
            	vm.patternRequirementManagementGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.addNewItemForGrid=function()
		{
			vm.authorManaGrid.dataSource.data().push( { name: 'Test add ', code: 'Test add' });
		};
		
		function fillDataCreatNew(data) {
			vm.gridOptionsCreat = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,
				resizable: true,
				columnMenu: false,
				dataSource: data ,
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#authorManaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 30,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'code',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'name',
			        width: 250,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'note',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
			        field: 'note',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		vm.addCreatNew = function add(){
			 var teamplateUrl="qlk/patternRequirementManagement/creatNewSample.html";
			 var title="Tạo mới mẫu yêu cầu xuất kho";
			 var windowId="patternRequirementManagement"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','400');
			 
		 }
		
		function fillFromName(data) {
			vm.gridOptionsCreat = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				dataSource: data ,
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#authorManaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 30,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'code',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'name',
			        width: 250,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'note',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
			        field: 'note',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Xóa",
			        field: 'note',
			        width: 50,
			        template:
						'<div class="text-center #=orderPatternId#"">'+
							'<a type="button" class="#=orderPatternId# icon_table" uib-tooltip="Xóa" translate ng-click="vm.remove(#=orderPatternId#)">'+
								'<i class="fa fa-trash" aria-hidden="true"></i>'+
							'</a>'+
						'</div>',
				}
				,]
			});
		}
		vm.addfromname = function addfromname(){
			 var teamplateUrl="qlk/patternRequirementManagement/creatNewFormName.html";
			 var title="Thêm mới yêu cầu xuất kho";
			 var windowId="patternRequirementManagement"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','400'); 
			 
		 }
									
		function refreshGrid(d) {
			var grid = vm.patternRequirementManagementGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
/* doSearch(); */
			
		}
		vm.edit=edit;
		function edit(dataItem){
			vm.tax =dataItem;
			var teamplateUrl="qlk/patternRequirementManagement/edit_popup.html";
			 var title="Chỉnh sửa Mẫu yêu cầu xuất kho";
			 var windowId="patternRequirementManagementEditPopup"
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.tax,vm,windowId,false,'600','180'); 
			
		}
		
		
		vm.save= function save(data,isCreateNew){		
                	if(isCreateNew) {
                		data.status = '1';
                		patternRequirementManagementService.addCreatNew(data).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.patternRequirementManagement.patternRequirementManagementId = result;                            
                            doSearch();
                            CommonService.dismissPopup();
                            vm.addCreatNew();
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã thuế đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục loại thuế!"));
    		                }
        		        });
                	} else {
                		patternRequirementManagementService.update(data).then(function(result){
                			toastr.success("Cập nhật thành công!");
                			doSearch();
                			CommonService.dismissPopup();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã thuế đã tồn tại"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
		}
		
		vm.remove=remove;
		function remove(dataItem){
		confirm('Xác nhận xóa',function(){
			patternRequirementManagementService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							doSearch();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
		})
	}
		
		vm.canceldoSearch= function canceldoSearch(){
// vm.showDetail = false;
			vm.patternRequirementManagementSearch={
			};
			doSearch();
		}
		
		vm.viewDetail= viewDetail;
		function viewDetail(){
			  /* vm.showDetail = false; */
			var grid =vm.detailGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		vm.showDetail=showDetail;
		function showDetail(dataItem){			
				vm.detailGoods=dataItem;
				var teamplateUrl="qlk/patternRequirementManagement/creatNewFormName.html";
			    var title="Chi tiết hàng hóa";
			    var windowId="detailGrid";
			    CommonService.populatePopupCreate(teamplateUrl,title,vm.detailGoods,vm,windowId,false,'85%','85%');
			  }	
		function fillDatadetailGrid(data) {
			vm.detailGrid = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderPatternServiceRest/viewDetail",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							
							    vm.detailGoods.page = options.page
								vm.detailGoods.pageSize = options.pageSize

								return JSON.stringify(vm.detailGoods)
						}
					},					 
					pageSize: 20
				} ,
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#detailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 30,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 250,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
			        field: 'amount',
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Xóa",
			        width: 50,
			        template:
			        	'<div class="text-center"">'+
							'<a type="button"'+	
								'class="#=orderPatternGoodsId# icon_table" ng-click="caller.removeDetail(dataItem)"  uib-tooltip="Xóa" translate>'+
								'<i class="fa fa-trash" aria-hidden="true" ></i>'+
							'</a>'
								+'</div>',
								field:'amount'
				}
				,]
			});
		}
		
		function removeDetail(dataItem){
		confirm('Xác nhận xóa',function(){
			patternRequirementManagementService.removeDetail(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							viewDetail();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
		})
	}
		vm.headerTemplate='<div class="dropdown-header k-widget k-header"></div>';
	}
})();