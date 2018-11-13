(function() {
	'use strict';
	var controllerId = 'appParamController';
	
	angular.module('MetronicApp').controller(controllerId, appParamController);
	
	function appParamController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,appParamService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.appParamSearch={
				status:1
		};
		vm.appParam={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			var obj={};
			obj.value="YCNK";
			obj.orgValue="BBB";
			obj.stockValue="AAA";
			CommonService.genCode(obj).then(
				function(d) {
					var String=d;
					console.log(String);
				});
		
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=appParamId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=appParamId#"'+
				'disble="" ng-click=vm.edit(#=appParamId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=appParamId#"'+
				'ng-click=vm.send(#=appParamId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=appParamId#"'+
				'ng-click=vm.remove(#=appParamId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=appParamId#"'+
				'ng-click=vm.cancelUpgradeLta(#=appParamId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		 

		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				editable: false,
                reorderable: true,
				toolbar: [
                          {
                              name: "actions",
                              template: 
                            	  '<div class=" pull-left ">'+
                                  '<button class="btn btn-qlk padding-search-right addQLK"'+
                                  'ng-click="vm.add()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
                					'</div>'	
                  				+
                                	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                              '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                              '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                              '<label ng-repeat="column in vm.appParamGrid.columns| filter: vm.gridColumnShowHideFilter">'+
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
							url: Constant.BASE_SERVICE_URL + "appParamServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.appParamSearch.page = options.page
								vm.appParamSearch.pageSize = options.pageSize

								return JSON.stringify(vm.appParamSearch)

						}
					},					 
					pageSize: 10
				},
// dataSource: data,
				noRecords: true,
				columnMenu: false,
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
			        template: dataItem => $("#appParamGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Loại tham số",
			        field: 'parType',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã tham số",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên tham số",
			        field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Trạng thái",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template: dataItem =>
					'<div class="text-center""><a '+
					' class=" icon_table "'+
					'   uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil" ng-click="vm.edit(dataItem)" aria-hidden="true"></i>'+
				'</a>'+
				'<a type="button"'+
				'class="#=appParamId# icon_table"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" ng-click="vm.remove(dataItem)" aria-hidden="true"></i>'+
				'</a>'
					+'</div>',
			        width: 150,
			        field:"action"
				}
				,]
			});
		}
		
		vm.listRemove=[{
			title: "Thao tác",
		}]
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Hiệu lực',
				0:'Hết Hiệu lực'
			}
		}]
		
		
		vm.exportFile = function exportFile() {
			var data = vm.appParamGrid.dataSource.data();
				CommonService.exportFile(vm.appParamGrid,data,vm.listRemove,vm.listConvert);
		}
		
		
		function refreshGrid(d) {
			var grid = vm.appParamGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			
// vm.add = function(){
// vm.isCreateNew = true;
// vm.showDetail = true;
// vm.appParam={};
// vm.title="Thêm mới tham số ứng dụng"
// }
		 vm.add = function(){
			 var teamplateUrl="qlk/app_param/app_paramPopup.html";
			 var title="Thêm mới tham số ứng dụng";
			 var windowId="APP_PARAM"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'600','200'); 
			 
		 }
		
		vm.edit=edit;
		function edit(dataItem){
// vm.showDetail = true;
// var grid=vm.appParamGrid;
// var item=grid.table.find("tr div." +id);
// var uid=$(item).parent().parent().attr("data-uid");
// var dataItem=grid.dataSource.getByUid(uid);
			vm.appParam =dataItem;
			var teamplateUrl="qlk/app_param/app_paramPopup.html";
			 var title="Cập nhật tham số ứng dụng";
			 var windowId="APP_PARAM"
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.appParam,vm,windowId,false,'600','200'); 
			 
			
		}
		
		
		vm.save= function(data,isCreateNew){
                	if(isCreateNew) {
                		appParamService.createAppParam(data).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.appParam.appParamId = result;
                            doSearch();
                            CommonService.dismissPopup();
                            vm.add()
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
                		appParamService.updateAppParam(data).then(function(result){
                			toastr.success("Cập nhật thành công!");
                			doSearch();
                			CommonService.dismissPopup();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			
		}
		vm.remove=remove;
		function remove(dataItem){
// var grid=vm.appParamGrid;
// var item=grid.table.find("tr div." +id);
// var uid=$(item).parent().parent().attr("data-uid");
// var dataItem = grid.dataSource.getByUid(uid);
			confirm('Xác nhận xóa', function(){
				appParamService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							doSearch();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} )
	}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.appParamSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.appParamGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.appParamGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.appParamGrid.showColumn(column);
            } else {
            	vm.appParamGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		
            
            vm.exportpdf= function(){
            	var obj={};
            	appParamService.exportpdf(obj);
            }
	}
})();