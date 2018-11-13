(function() {
	'use strict';
	var controllerId = 'taxController';
	
	angular.module('MetronicApp').controller(controllerId, taxController);
	
	function taxController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,taxService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.taxSearch={
				status:1,
				type: 3
		
		};
		vm.tax={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=taxId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=taxId#"'+
				'disble="" ng-click=vm.edit(#=taxId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=taxId#"'+
				'ng-click=vm.send(#=taxId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=taxId#"'+
				'ng-click=vm.remove(#=taxId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=taxId#"'+
				'ng-click=vm.cancelUpgradeLta(#=taxId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				toolbar: [
                    {
                        name: "actions",
                        template: '<div class=" pull-left ">'+
                        '<a class="btn btn-qlk padding-search-right addQLK"'+
      					'ng-click="vm.add()" uib-tooltip="Thêm mới" translate><p class="action-button add" aria-hidden="true">Tạo mới</p></a>'+
      					'</div>'	
        				+
                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in vm.taxGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                    '</label>'+
                    '</div></div>'
                    
                    }
                    ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "taxRsServiceRest/tax/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              //  vm.taxSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.taxSearch.page = options.page
								vm.taxSearch.pageSize = options.pageSize

								return JSON.stringify(vm.taxSearch)

						}
					},					 
					pageSize: 20
				},
//				dataSource: data,
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
					title: "TT",
					field:"stt",
			        template: dataItem => $("#taxGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 50,
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã thuế",
					field: 'code',
			        width: 95,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên thuế",
			        field: 'name',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thuế xuất <br> (%)",
			        field: 'value',
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Loại thuế",
					template :  "# if(type == 2){ #" + "#= 'Đầu ra' #" + "# } " + "else if (type == 1) { # " + "#= 'Đầu vào' #"+ "#} #",
			        field: 'type',
			        width: 140,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Phân bổ",
					template :  "# if(apply == 0){ #" + "#= 'Không' #" + "# } " + "else if (apply == 1) { # " + "#= 'Có' #"+ "#} #",
			        field: 'apply',
			        width: 140,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Miễn thuế",
					template :  "# if(ignore == 0){ #" + "#= 'Không' #" + "# } " + "else if (ignore == 1) { # " + "#= 'Có' #"+ "#} #",
			        field: 'ignore',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Trạng thái",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: 130,
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
					' ng-click=vm.edit(dataItem)  uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil" aria-hidden="true"></i>'+
				'</a>'+
				'<a type="button"'+	
					'class="#=taxId# icon_table"  uib-tooltip="Xóa" translate>'+
					'<i class="fa fa-trash" aria-hidden="true" ng-click=vm.remove(dataItem)></i>'+
				'</a>'
					+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
									
		function refreshGrid(d) {
			var grid = vm.taxGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
/*			doSearch();*/
		}
		
		vm.edit=edit;
		function edit(dataItem){
			vm.tax =dataItem;
			var teamplateUrl="qlk/tax/taxPopup.html";
			 var title="Cập nhật loại thuế";
			 var windowId="TAX"
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.tax,vm,windowId,false,'600','300'); 
			
		}
		
		
		vm.save= function save(data,isCreateNew){
                	if(isCreateNew) {
                		data.status = '1';
                		taxService.createTax(data).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.tax.taxId = result;                            
                            doSearch();
                            CommonService.dismissPopup();
                            vm.add();
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã thuế đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục loại thuế!"));
    		                }
        		        });
                	} else {
                		taxService.updateTax(data).then(function(result){
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
		
		
		 vm.add = function add(){
			 var teamplateUrl="qlk/tax/taxPopup.html";
			 var title="Thêm mới loại thuế";
			 var windowId="TAX"
				 vm.Tax={
			 }	 
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.Tax,vm,windowId,true,'600','300'); 
			 
		 }
		
		vm.remove=remove;
		function remove(dataItem){
		confirm('Xác nhận xóa',function(){
				taxService.remove(dataItem).then(
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
			vm.taxSearch={
					status:"1",
					type:"3",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.taxGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.taxGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.taxGrid.showColumn(column);
            } else {
            	vm.taxGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		** Filter các cột của select 
		*/	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
	}
})();