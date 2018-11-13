(function() {
	'use strict';
	var controllerId = 'oddCableManagementController';
	
	angular.module('MetronicApp').controller(controllerId, oddCableManagementController);
	
	function oddCableManagementController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,oddCableManagementService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.oddCableManagementSearch={
				status:1,};
		vm.SearchOddCable={
				goodsState:'0',
		};
		vm.SearchOddCable2={
				goodsState:'0',
		};
		vm.oddCable = {};
		/* popup complet */
		vm.TemplateOddCable='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
       
		vm.headerTemplateOddCable='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
	      '<p class="col-md-6 text-header-auto">Tên</p>' +
	      	'</div>';
        vm.commonSearch = {name: '', code: ''};
        vm.gridCommonOddCable =  [ {
			title: "Mã",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
      
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.oddCableManagement={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDataCreatNew([]);
			vm.SearchOddCable.goodsId = null;
			vm.SearchOddCable.goodsName = null
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=oddCableId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=oddCableId#"'+
				'disble="" ng-click=vm.edit(#=oddCableId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=oddCableId#"'+
				'ng-click=vm.send(#=oddCableId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=oddCableId#"'+
				'ng-click=vm.remove(#=oddCableId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=oddCableId#"'+
				'ng-click=vm.cancelUpgradeLta(#=oddCableId#)>'+
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
                      	  /*
							 * '<div class=" pull-left ">'+ '<a class="btn
							 * green btn-outline padding-search-right"'+
							 * 'ng-click="vm.add()" uib-tooltip="Tạo mới"
							 * translate><p class="action-button add" aria-hidden="true">Tạo
							 * mới</p></a>'+ '</div>'
							 */	
                        	'<div class=" pull-left ">'+
                            '<button class="btn btn-qlk padding-search-right addQLK"'+
                            'ng-click="vm.add()" uib-tooltip="Thêm mới" translate>Thêm mới</button>'+
          					'</div>'
            				+
                          	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                        '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                        '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                        '<label ng-repeat="column in vm.oddCableManagementGrid.columns| filter: vm.gridColumnShowHideFilter">'+
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
							url: Constant.BASE_SERVICE_URL + "oddCableServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.oddCableManagementSearch.page = options.page
								vm.oddCableManagementSearch.pageSize = options.pageSize

								return JSON.stringify(vm.oddCableManagementSearch)

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
					title: "STT",
					field:"stt",
			        template: dataItem => $("#oddCableManagementGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
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
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Chiều dài tối thiểu",
			        field: 'amountMinimum',
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
			        field: 'status',
			        width: 70,
			        template :  
				        "# if(status == 1)" +
				        "{ #" + "#= 'Hiệu lực' #" + "# } " +
				        "else if (status == 0) " +
				        "{ # " + "#= 'Hết hiệu lực' #"+ "#} " +				        
				        "#",
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
						'class="#=oddCableId# icon_table"  uib-tooltip="Xóa" translate>'+
						'<i class="fa fa-trash" aria-hidden="true" ng-click=vm.remove(dataItem)></i>'+
					'</a>'
						+'</div>',
			        width: 60,
			        field:"stt"
				}
				,]
			});
		}
		function fillDataCreatNew(data) {
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
				}, {
					title: "Xóa",
			        field: 'delete',
			        width: 30,
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
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.oddCableManagementGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.oddCableManagementGrid.showColumn(column);
            } else {
            	vm.oddCableManagementGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.add = function add(){
			 vm.SearchOddCable.goodsId = null;
			 vm.SearchOddCable.goodsName = null;
			 var teamplateUrl="qlk/oddCableManagement/creatNewOddCable.html";
			 var title="Thêm mới cáp lẻ";
			 var windowId="addNewOddCableManagement"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300'); 
			 
		 }
									
		function refreshGrid(d) {
			var grid = vm.oddCableManagementGrid;
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
			vm.oddCable =dataItem;
			vm.SearchOddCable2.goodsId = dataItem.goodsCode;
			vm.SearchOddCable2.goodsName = dataItem.goodsName;
			
			var teamplateUrl="qlk/oddCableManagement/edit_oddPopup.html";
			 var title="Cập nhật cáp lẻ";
			 var windowId="editOddCableManagement"
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.oddCable,vm,windowId,false,'600','200');
			
		}
		
		
		vm.save= function save(data,isCreateNew){			
                	if(isCreateNew) {
                		data.status = '1';            		
                		data.goodsCode = vm.SearchOddCable.goodsId;
                		data.goodsName = vm.SearchOddCable.goodsName;
                		oddCableManagementService.addCreatNew(data).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.oddCableManagement.oddCableId = result;                            
                            doSearch();
                            CommonService.dismissPopup();
                            //vm.add();
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã hàng đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới cáp lẻ!"));
    		                }
        		        });
                	} else {
                		oddCableManagementService.update(data).then(function(result){
                			toastr.success("Cập nhật thành công!");
                			doSearch();
                			CommonService.dismissPopup();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã hàng đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật!"));
    		                }
        		        });
                	}
		}
		
		
		vm.remove=remove;
		function remove(dataItem){
		confirm('Xác nhận xóa',function(){
			oddCableManagementService.remove(dataItem).then(
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
			vm.oddCableManagementSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.oddCableManagementGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		vm.headerTemplate='<div class="dropdown-header k-widget k-header"></div>';
		vm.gridListOdd = [ {
			title: "TT",
			field: "code",
			width: 50
		},{
			title: "Mã hàng",
			field: "code",
			width: 120
		}, {
			title: "Tên hàng",
			field: "name",
			width: 120
		}, {
			title: "Đơn vị tính",
			field: "name",
			width: 120
		}, {
			title: "chọn",
			field: "name",
			width: 120
		}];
	}
})();