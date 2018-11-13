(function() {
	'use strict';
	var controllerId = 'reasonController';
	
	angular.module('MetronicApp').controller(controllerId, reasonController);
	
	function reasonController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,reasonService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.reasonSearch={
				status: 1,
				nameApply : ['1']
		};
		vm.appParamSearch = {
				status:1,
				parType : 'REASON_APPLY'
		};
		vm.reason={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.dataAppParamType = [""];
		initFormData();
		function initFormData() {
			fillDataTable([]);
			getApply();
		}
		function getApply(){
			reasonService.getApply(vm.appParamSearch).then(function(result){
				 vm.dataAppParamType = result;						 
			 }, function(errResponse){  
				toastr.error(gettextCatalog.getString("Xảy ra lỗi khi lấy tham số"));
		     });
		}
		
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				//sortable: false,				
				columnMenu: false,
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
                              '<label ng-repeat="column in vm.reasonGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total;
							},
							data: function (response) {
								return response.data;
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "reasonRsServiceRest/reason/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.reasonSearch.page = options.page
								vm.reasonSearch.pageSize = options.pageSize

								return JSON.stringify(vm.reasonSearch)

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
				columns: [{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#reasonGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã lý do",
					field: 'code',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên lý do",
			        field: 'name',
			        width: 180,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tình huống sử dụng",
			        field: 'nameApply',
			        width: 120,
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
			        width: 70,
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
				'class="icon_table"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" ng-click="vm.remove(dataItem)" aria-hidden="true"></i>'+
				'</a>'
					+'</div>',
			        width: 50,
			        field:"stt"
				}
				,]
			});
		}
		
		function refreshGrid(d) {
			var grid = vm.reasonGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			doSearch();
		}
		
		vm.save= function save(data,isCreateNew){
	            if(isCreateNew) {
	            	data.status = '1';
	            		reasonService.createReason(data).then(function(result){
	            			toastr.success("Thêm mới thành công!");
	                        vm.reason.reasonId = result;
	                        doSearch();
	                        CommonService.dismissPopup();
	                        vm.add();
	            		}, function(errResponse){
			                if (errResponse.status === 409) {
			                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại!"));
			                } else {
			                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục lý do!"));
			                }
	    		        });
	            	} else {
	            		reasonService.updateReason(data).then(function(result){
	            			toastr.success("Cập nhật thành công!");
	            			doSearch();
	            			CommonService.dismissPopup();
	            		}, function(errResponse){
	            			if (errResponse.status === 409) {
			                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại"));
			                } else {
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
			                }
	    		        });
	            	}
			}
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.reasonGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		vm.edit=edit;
		function edit(dataItem){
//				vm.showDetail = true;
//				var grid=vm.reasonGrid;
//				var item=grid.table.find("tr div." +id);
//				var uid=$(item).parent().parent().attr("data-uid");
//				var dataItem=grid.dataSource.getByUid(uid);
				vm.reason =dataItem;
				var teamplateUrl="qlk/reason/reasonPopup.html";
				var title="Cập nhật lý do";
				var windowId="REASON"
				CommonService.populatePopupCreate(teamplateUrl,title,vm.reason,vm,windowId,false,'750','160'); 
		}
		
		vm.add = function add(){
			var teamplateUrl="qlk/reason/reasonPopup.html";
			var title="Thêm mới lý do";
			var windowId="REASON"
			CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'600','160'); 
			 
		 }
		vm.remove=remove;
		function remove(dataItem){
			confirm('Xác nhận xóa',function(){
				reasonService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							doSearch();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
				})
		}
		vm.canceldoSearch= function canceldoSearch(){
			 vm.showDetail = false;
			vm.reasonSearch={
					status:"1",
			};
			doSearch();
		}
		vm.cancelapply = function cancelapply()
		{
			vm.reasonSearch.listApply = [];
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.reasonGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.reasonGrid.showColumn(column);
            } else {
            	vm.reasonGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
	}
})();