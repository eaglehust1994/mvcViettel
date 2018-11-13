(function() {
	'use strict';
	var controllerId = 'shipmentGoodsController';
	
	angular.module('MetronicApp').controller(controllerId, shipmentGoodsController);
	
	function shipmentGoodsController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,shipmentGoodsService,shipmentDetailService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showDetail = true;
		vm.shipment={};
		vm.shipmentSearch =
		{
				type : 0,
				listStatus : ['2']
		};
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDataDetailTable([]);
		}
		
		function fillDataTable(data) {
			vm.gridGoodsOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,
				resizable: true,
				columnMenu: false,
				toolbar: [
                    {
                        name: "actions",
                        template: 
                        	 '<div class="btn-group pull-right margin-left10 margin_right10">'+
                             '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                             '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
                             '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                             '<label ng-repeat="column in vm.shipmentGoodsGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                             '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                             '</label>'+
                             '</div></div>'
                    
                    }
                    ],
				dataBound: function (e) {
				    var grid = vm.shipmentGoodsGrid;
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
				        var teamplateUrl="qlk/shipmentGoods/shipmentGoodsPopup.html";
						var title="Định lượng tỷ trọng kỹ thuật";
						var windowId="SHIPMENTGOODS";
						loadDataDetail(dataItem);
						CommonService.populatePopupCreate(teamplateUrl,title,dataItem,vm,windowId,false,'80%','60%'); 
				    });
				},
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
							
							  url: Constant.BASE_SERVICE_URL + "shipmentRsServiceRest/shipment/doSearch",
							  contentType: "application/json; charset=utf-8",
							  type: "POST"
							 
						},					
						parameterMap: function (options, type) {
							   
								  vm.shipmentSearch.page = options.page
								  vm.shipmentSearch.pageSize = options.pageSize
								  
								  return JSON.stringify(vm.shipmentSearch)
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
			        template: dataItem => $("#shipmentGoodsGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã lô hàng",
					field: 'code',
					template: dataItem => '<a class="#=shipmentId#" href="javascript:void(0);" ng-click=vm.showSix(dataItem)> {{dataItem.code}} </a>',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Mã hợp đồng",
					field: 'contractCode',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Loại lô hàng",
			        field: 'type',
			        width: 100,
			        template :  
			        "# if(type == 1){ #" + "#= 'Lô hàng nội' #" + "# } " +
			        "else if (type == 2) { # " + "#= 'Lô hàng ngoại' #"+ "#} " +
			        "#",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					
				},
				{
					title: "Ngày giao <br> hàng",
			        field: 'shiperDate',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Người tạo",
			        field: 'createdBy',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				 
				{
					title: "Đơn vị tạo",
			        field: 'projInvestProjectId',
			        width: 180,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'status',
					 template :  
					        "# if(status == 1)" +
					        "{ #" + "#= 'Mới tạo' #" + "# } " +
					        "else if (status == 2) " +
					        "{ # " + "#= 'Đã cập nhật hàng hóa' #"+ "#} " +
					        "else if (status == 3) " +
					        "{ # " + "#= 'Đã định lượng' #"+ "#} " +
					        "else if (status == 4) " +
					        "{ # " + "#= 'Đã định giá' #"+ "#} " +
					        "else if (status == 5) " +
					        "{ # " + "#= 'Đã tạo YCKT' #"+ "#} " +
					        "else if (status == 6) " +
					        "{ # " + "#= 'Đã tạo BBKT' #"+ "#} " +
					        "else if (status == 7) " +
					        "{ # " + "#= 'Đã nhập kho' #"+ "#} " +
					        "else if (status == 8) " +
					        "{ # " + "#= 'Đã hủy' #"+ "#} " +
					        "#",
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định lượng",
			        template:
					'<div class="text-center #=shipmentId#"">'+
					'<a class=" icon_table #=shipmentId#" uib-tooltip="Định lượng" translate>'+
					'<i ng-hide="dataItem.status != 2 && dataItem.status != 3" class="fa fa-pencil" aria-hidden="true" ng-click=vm.quantyfi(#=shipmentId#)></i>'+
					'</a>'+
					'<a class=" icon_table #=shipmentId#" uib-tooltip="Định lượng" translate>'+
					'<i ng-hide="dataItem.status == 3 || dataItem.status == 2" class="fa fa-trash" aria-hidden="true"></i>'+
					'</a>'
					+'</div>',
			        width: 100,
			        field:"stt"
				}
				,]
			});
		}
		
		vm.showSix = function showSix(dataItem){
// var grid = vm.shipmentGoodsGrid;
// var item=grid.table.find("tr div." +id);
// var uid=$(item).parent().parent().attr("data-uid");
// var dataItem=grid.dataSource.getByUid(uid);
			var dataItem = dataItem;
	        var teamplateUrl="qlk/shipmentDetail/shipmentDetailList.html";
	        var title="Thông tin chi tiết lô hàng";
			$kWindow.open({
				 options: {
					 modal: true,
					 title: title,
					 visible: false,
					 width: '88%',
					 height: '88%',
					 actions: ["Minimize", "Maximize", "Close"],
					 open: function () {
						 this.wrapper.children('.k-window-content').addClass("fix-footer");
						 $rootScope.$broadcast("shipment.object.data", dataItem);
					 }
				 },                
				 templateUrl: teamplateUrl
			});
		}
		
		function fillDataDetailTable(data) {
			var dataSource = new kendo.data.DataSource({
                pageSize: 20,
                data: data,
                autoSync: false,        
                schema: {
                    model: {
                        id: "shipmentGoodsDetailGrid",
                    	fields: {
                    		stt: {editable: false},
                    		goodsCode: {editable: false},
                    		goodsName: {editable: false},
                    		unitTypeName: {editable: false},
                    		amount: {editable: false},
                    	}
                    }
                }
            });
			vm.gridGoodsDetailOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: dataSource,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				columns: [{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
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
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Tên hàng",
					field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Đơn vị tính",
			        field: 'unitTypeName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Số lượng",
			        field: 'amount',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Định lượng cho 1 đơn vị HH",
			        field: 'estimatePrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Định lượng theo danh mục",
			        field: 'estimatePriceSum',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Tỷ lệ % theo danh mục",
			        field: 'estimatePricePercent',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Đơn giá theo danh mục",
			        field: 'originPrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Thành tiền theo danh mục",
			        field: 'totalOriginPrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template:
					'<div class="text-center #=shipmentGoodsId#""><a '+
					' class=" icon_table #=shipmentGoodsId#"'+
					' ng-click=vm.edit(#=shipmentGoodsId#)  uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil" aria-hidden="true"></i>'+
					'</a>'
					+'</div>',
					/*
					 * <div class="text-center #=shipmentGoodsId#""><a '+ '
					 * class=" icon_table #=shipmentGoodsId#"'+ '
					 * ng-click=vm.edit(#=shipmentGoodsId#) uib-tooltip="Sửa"
					 * translate>'+ '<i class="fa fa-pencil"
					 * aria-hidden="true"></i>'+ '</a>' +'</div>'
					 */
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		
		vm.exportExcelGrid = function(){
			var ds = $("#shipmentGoodsGrid").data("kendoGrid").dataSource;
	    	var rows = [{
		        cells: [
		          { value: "STT", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã lô hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã hợp đồng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Loại lô hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Ngày giao hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Người tạo", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Đơn vị", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Trạng thái", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}
		        ]
		      }];
	    	ds.fetch(function(){
	    		var data = this.data();
	    		for (var i = 0; i < data.length; i++){
			          // push single row for every record
			        	rows.push({
				            cells: [
				              { value: i+1 , textAlign: "center"},
				              { value: data[i].code },
				              { value: data[i].contractCode},
				              { value: TyoeOfShipment(data[i].type) },
				              { value: data[i].shiperDate },
				              { value: data[i].createdBy },
				              { value: data[i].projInvestProjectId},
				              { value: statusOfShipment(data[i].status)},
				            ]
			          })
			        }
	    		var workbook = new kendo.ooxml.Workbook({
			          sheets: [
			            {
			            	columns: [
			                // Column settings (width)
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			              ],
			              
			              // Title of the sheet
			              title: "Danh sách lô hàng",
			              // Rows of the sheet
			              rows: rows
			            }
			          ]
			        });
	    		kendo.saveAs({dataURI: workbook.toDataURL(), fileName: "Danh sách lô hàng.xlsx"});
	    	});
		}
		
		function statusOfShipment(x){
			 if(x == 1)
			 { return "Mới tạo";}
			 else if (x == 2)
			 { return "Đã cập nhật hàng hóa";}
		     else if (x == 3)
		     { return "Đã định lượng";}
		     else if (x == 4){ return "Đã định giá";}
		     else if (x == 5){ return "Đã tạo YCKT";}
		     else if (x == 6){ return "Đã tạo BBKT";}
		     else if (x == 7){ return "Đã nhập kho";}
		     else if (x == 8){ return "Đã hủy";}
		}
		
		function TypeOfShipment(x){
			if(type == 1){ 
				return "Lô hàng nội";
			}else if (type == 2) {
				return "Lô hàng ngoại";
			}
			
		}
			
		vm.add = function add(){

			 var teamplateUrl="qlk/shipmentGoods/shipmentGoodsPopup.html";
			 var title="Định lượng";
			 var windowId="SHIPMENTGOODS"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'833','600'); 
			 
		 }
		
		vm.cancel= cancel ;
		function cancel(){
				vm.shipmentGoods={};
				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.shipmentGoodsGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		vm.quantyfi = function quantyfi(id)
		{
			vm.showDetail = true;
			var grid=vm.shipmentGoodsGrid;
			var item=grid.table.find("tr div." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			var dataItem=grid.dataSource.getByUid(uid);
			vm.shipment =dataItem;
			var teamplateUrl="qlk/shipmentGoods/shipmentGoodsPopup.html";
			var title="Định lượng tỷ trọng kỹ thuật";
			var windowId="SHIPMENTGOODS";
			loadDataDetail(dataItem);
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'80%','80%');
		}
		
		function refreshGrid(d) {
			var grid = vm.shipmentGoodsDetailGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function loadDataDetail(dataItem)
		{
			shipmentGoodsService.doSearchMap(dataItem).then(function(result){
				refreshGrid(result.plain());
			}, function(errResponse){
				if (errResponse.status === 409) {
					toastr.error(gettextCatalog.getString("Error!"));
				} else {
					toastr.error(gettextCatalog.getString("NoAccess!"));
				}
			});
		}
		
		
		vm.openDepartment=openDepartment
		function openDepartment(){
			var obj={};
// obj.page=1;
// obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data=result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
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
					columns:[ {
						title: "Mã",
						field: "code",
						width: 120
					}, {
						title: "Tên",
						field: "text",
						width: 120
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%');
			});
		}
		vm.commonSearch = {name: '', code: ''};
        vm.gridCommon = [ {
			title: "Mã xxx",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
		vm.onSave=onSave;
		
		function onSave(data){
			vm.shipmentSearch.createdDeptName=data.text;
			vm.shipmentSearch.createdDeptId = data.code;
		}
		vm.cancelDept = function()
		{
			vm.shipmentSearch.createdDeptName = undefined;
			vm.shipmentSearch.createdDeptId = undefined;
		}
		vm.cancelStatis = function()
		{
			vm.shipmentSearch.listStatus = ['2'];
		}
		vm.cancelCreatedBy = function()
		{
			vm.shipmentSearch.createName = undefined;
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.shipmentGoodsGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.shipmentGoodsGrid.showColumn(column);
            } else {
            	vm.shipmentGoodsGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		// /END
	}
})();