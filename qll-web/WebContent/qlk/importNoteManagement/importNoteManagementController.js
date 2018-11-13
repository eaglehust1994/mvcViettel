(function() {
	'use strict';
	var controllerId = 'impNoteManaController';
	
	angular.module('MetronicApp').controller(controllerId, impNoteManaController);
	
	function impNoteManaController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,impNoteManaService, impReqManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTabOne = true;
		vm.showTabOneNoteDetail = true;
		vm.showTabOneTwoDetail = false;
		vm.showTabThreeNoteDetail = false;
		
		vm.disableCheckboxes = true;
		
		vm.order={};
		vm.orderSearch ={};
		vm.orderGoodsSearch ={};
		vm.orderGoodsDetailSearch ={};
		vm.stockTransSearch ={};
		vm.stockTrans={};
		vm.stockTransDetailSearch ={};
		vm.stockTransDetailSerialSearch ={};
		vm.appParams={};
		vm.appParams.parType = 'IMPORT_ORDER_TYPE';
		vm.appParams.page = 1;
		vm.appParams.pageSize = 20;
		
		vm.showFromProvider = false;
		vm.showBeforeWarranty = false;
		vm.showAfterWarranty = false;
		vm.showFromConstruction = false;
		vm.showFromUnit = false;
		vm.showFromBorrowedGoods = false;
		vm.showFromAlternativeStock = false;
		vm.showFromProject = false;
		
		vm.stockTransSearch.listStatus = ["1"];
		vm.stockTransSearch.listSignState = ["1"];
		
		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
      '<span>Mã</span>' +
      '<span>Tên</span>' +
      	'</div>';
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
		vm.gridCreator = [  {
			title: "Tên đăng nhập",
			field: "loginName",
			width: 120
		},{
			title: "Mã nhân viên",
			field: "employeeCode",
			width: 120
		}, {
			title: "Họ tên",
			field: "fullName",
			width: 120
		}, {
			title: "Email",
			field: "email",
			width: 120
		}, {
			title: "SĐT",
			field: "phoneNumber",
			width: 120
		}];
		vm.listRemove=[{
			title: "Thao tác",
		},
		{
			title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />"
		}];
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Chưa tạo phiếu',
				2:'Đã tạo phiếu',
				3:'Đã nhập/xuất',
				4:'Đã hủy',
				5:'Đã từ chối',
			}
		},{
			field:"signState",
			data:{
				1:'Chưa trình ký',
				2:'Đã trình ký',
				3:'Đã ký',
				4:'Đã từ chối',
			}
		}];
	
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		initFormData();
		function initFormData() {
			var d = new Date();
			var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
			vm.stockTransSearch.fromDate = datestring;
			vm.stockTransSearch.toDate = null;
			
			fillDataTableImpNoteMana([]);
		}
		impNoteManaService.getForOrderCheckboxes(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
		});
		vm.onSave=onSave;
		function onSave(data, popupID){
			if(popupID == 'create'){
				vm.orderSearch.createdDeptedName = data.text;
				vm.orderSearch.createdDeptedId = data.code;
			}
		}
		
		function onChangeGoodList(){
			if ($("#goodsListGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.orderGoodsDetailSearch = dataItem;
    			fillDataTableGoodsDetail([]);
			}
			
		}
		
		function onChangeGoodListForNote(){
			if ($("#goodsListForNoteGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListForNoteGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListForNoteGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.stockTransDetailSerialSearch = dataItem;
    			fillDataTableGoodsDetailForNote([]);
			}
		}
		
		vm.exportExcelGrid = function(){
			impNoteManaService.getForExportGrid(vm.stockTransSearch).then(function(d) {
				CommonService.exportFile(vm.impNoteManaGrid,d.data,vm.listRemove,vm.listConvert);
			});
		}
		
		
		function fillDataTableImpNoteMana(data) {
			vm.impNoteManaGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataBound: function (e) {
				    var grid = $("#impNoteManaGrid").data("kendoGrid");
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
				        vm.showNoteDetail(dataItem.stockTransId);
				    });
				},
				toolbar: [
		                    {
		                        name: "actions",
		                        template: '<div class=" pull-left ">'+
		                        '<button class="btn btn-qlk padding-search-right TkQLK"'+
		      					'ng-click="vm.sendToSign()" uib-tooltip="Trình ký" translate>Trình ký</button>'+
		      					'</div>'	
		        				+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                    '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.impNoteManaGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
							 vm.count = response.total;
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "stockTransServiceRest/stockTrans/doSearchImportNote",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.stockTransSearch.page = options.page
								vm.stockTransSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockTransSearch)
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
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			        width: 35,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				},
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#impNoteManaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},  {
					title: "Mã yêu cầu",
					field: 'orderCode',
					template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(#=orderId#)>#=code#</a>',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Mã phiếu",
					field: 'code',
					template: '<a class="#=stockTransId#" href="javascript:void(0);" ng-click=vm.showNoteDetail(#=stockTransId#)>#=code#</a>',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại yêu cầu",
			        field: 'bussinessTypeName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Kho nhập",
			        field: 'stockName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Người tạo",
					 field: 'createdByName',
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
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					template: function($scope){
						if($scope.status == 1){
							return "Chưa nhập";
						}else if($scope.status == 2){
							return "Đã nhập";
						}else if($scope.status == 3){
							return "Đã hủy";
						}
					},
					
				},  {
					title: "Tình trạng ký CA",
					 field: 'signState',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					template: function($scope){
						if($scope.signState == 1){
							return "Chưa trình ký";
						}else if($scope.signState == 2){
							return "Đã trình ký";
						}else if($scope.signState == 3){
							return "Đã ký";
						}else if($scope.signState == 4){
							return "Đã từ chối";
						}
					},

				},{
					title: "Thao tác",
			        template:
			        	'<div class="text-center #=stockTransId#""><a '+
						' class=" icon_table #=stockTransId#"'+
						' uib-tooltip="Thực nhập" translate'+
						' ng-click=vm.realImport(#=stockTransId#)>'+'<i class="fa fa-download" aria-hidden="true"></i>'+
					'</a>'+
					'<a type="button"'+
					'class="#=stockTransId# icon_table"  uib-tooltip="Sửa" translate'+
						' ng-click=vm.edit(#=stockTransId#)>'+
						'<i class="fa fa-pencil" aria-hidden="true"></i>'+
					'</a>'+
					'<a type="button"'+
					'class="#=stockTransId# icon_table"  uib-tooltip="Xóa" translate'+
						' ng-click=vm.openRemovePopup(#=stockTransId#)>'+
					'<i class="fa fa-trash" aria-hidden="true"></i>	'+
					'</a>'
					+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		function fillDataTableGoodsListForNote(data) {
			vm.goodsListForNoteGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				change : onChangeGoodListForNote,
				dataBound: function(e) {
                    var grid = $("#goodsListForNoteGrid").data("kendoGrid");
                        grid.select("tr:eq(0)");
               },
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "stockTransDetailServiceRest/stockTransDetail/doSearchGoodsForImportNote",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockTransDetailSearch.page = options.page
								vm.stockTransDetailSearch.pageSize = options.pageSize
								vm.stockTransDetailSearch.stockTransId = vm.stockTrans.stockTransId
								return JSON.stringify(vm.stockTransDetailSearch)
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
			        template: dataItem => $("#goodsListForNoteGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'amountReal',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tình trạng",
					 field: 'goodsStateName',
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
		
		function fillDataTableGoodsDetailForNote(data) {
			vm.goodsDetailForNoteGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "stockTransDetailSerialServiceRest/stockTransDetailSerial/doSearchGoodsDetailForImportNote",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                           //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.stockTransDetailSerialSearch.page = options.page
								vm.stockTransDetailSerialSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockTransDetailSerialSearch)
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
			        template: dataItem => $("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Serial",
					field: 'serial',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hợp đồng",
			        field: 'contractCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Part number",
			        field: 'partNumber',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Hãng sản xuất",
					 field: 'manufacturerName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Nước sản xuất",
					 field: 'producingCountryName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Vị trí",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
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
		
		function fillDataTableGoodsList(data) {
			vm.goodsListGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				change : onChangeGoodList,
				dataBound: function(e) {
                     var grid = $("#goodsListGrid").data("kendoGrid");
                         grid.select("tr:eq(0)");
                },
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.orderGoodsSearch.page = options.page
								vm.orderGoodsSearch.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsSearch)
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
			        template: dataItem => $("#goodsListGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'amount',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tình trạng",
					 field: 'goodsStateName',
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
		
		function fillDataTableGoodsDetail(data) {
			vm.goodsDetailGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsDetailServiceRest/orderGoodsDetail/doSearchGoodsDetailForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.orderGoodsDetailSearch.page = options.page
								vm.orderGoodsDetailSearch.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsDetailSearch)
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
			        template: dataItem => $("#goodsDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Serial",
					field: 'serial',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hợp đồng",
			        field: 'contractCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Part number",
			        field: 'partNumber',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Hãng sản xuất",
					 field: 'manufacturerName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Nước sản xuất",
					 field: 'producingCountryName',
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
		
		function refreshGrid(d) {
			var grid = vm.imReqMaGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.doSearch = function(){
			var grid = $("#impNoteManaGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		vm.sendToSign = function(){
			vm.noteDetailWindow = $kWindow.open({
                options: {
                    modal: true,
                    title: "Chi tiết phiếu nhập kho",
                    visible: false,
                    resizable: true,
                    width: '1000',
                    height: '600',
                    actions: ["Minimize", "Maximize", "Close"],
			
                },
                templateUrl: "qlk/importNoteManagement/noteDetailPopUp.html"

            });
		}
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
		}
		vm.gotoTabOneNoteDetailPopUp = function(){
			vm.showTabOneNoteDetail = true;
			vm.showTabTwoNoteDetail = false;
			vm.showTabThreeNoteDetail = false;
		}
		vm.gotoTabTwoNoteDetailPopUp = function(){
			vm.showTabOneNoteDetail = false;
			vm.showTabTwoNoteDetail = true;
			vm.showTabThreeNoteDetail = false;
		}
		vm.gotoTabThreeNoteDetailPopUp = function(){
			vm.showTabOneNoteDetail = false;
			vm.showTabTwoNoteDetail = false;
			vm.showTabThreeNoteDetail = true;
		}
		
		vm.showDetail=function(id){
			var teamplateUrl="qlk/importNoteManagement/detailPopup.html";
			 var title="Chi tiết yêu cầu nhập kho";
			 var windowId="ORDER";
			 vm.order={};
			 impReqManaService.getOrderDetailById(id).then(function(d) {
				 vm.order = d.plain();
				 vm.orderGoodsSearch.orderId = vm.order.orderId;
				 fillDataTableGoodsList([]);
					CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.showNoteDetail=function(id){
			var teamplateUrl="qlk/importNoteManagement/noteDetailPopUp.html";
			 var title="Thông tin phiếu nhập kho";
			 var windowId="STOCK_TRANS";
			 vm.stockTrans={};
			 
			 impNoteManaService.getStockTransDetailById(id).then(function(d) {
				 vm.stockTrans = d.plain();
				 fillDataTableGoodsListForNote([]);
				 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.checkbox = function(){
			vm.showFromProvider = false;
			vm.showBeforeWarranty = false;
			vm.showAfterWarranty = false;
			vm.showFromConstruction = false;
			vm.showFromUnit = false;
			vm.showFromBorrowedGoods = false;
			vm.showFromAlternativeStock = false;
			vm.showFromProject = false;
			if (document.getElementById("fromProvider").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromProvider = true;
		    	vm.order.bussinessType = vm.businessTypes[0].code;
		    }
			if (document.getElementById("beforeWarranty").checked == true) {
		        //Checkbox has been checked
		    	vm.showBeforeWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[1].code;
		    }
			if (document.getElementById("afterWarranty").checked == true) {
		        //Checkbox has been checked
		    	vm.showAfterWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[2].code;
		    }
			if (document.getElementById("fromConstruction").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromConstruction = true;
		    	vm.order.bussinessType = vm.businessTypes[3].code;
		    }
			if (document.getElementById("fromUnit").checked == true) {
			        //Checkbox has been checked
			    vm.showFromUnit = true;
			    vm.order.bussinessType = vm.businessTypes[4].code;
			}
			if (document.getElementById("fromBorrowedGoods").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromBorrowedGoods = true;
		    	vm.order.bussinessType = vm.businessTypes[5].code;
		    }
			if (document.getElementById("fromAlternativeStock").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromAlternativeStock = true;
		    	vm.order.bussinessType = vm.businessTypes[6].code;
		    }
			if (document.getElementById("fromProject").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromProject = true;
		    	vm.order.bussinessType = vm.businessTypes[7].code;
		    }
			if (document.getElementById("direct").checked == true) {
		        //Checkbox has been checked
		    	vm.order.bussinessType = vm.businessTypes[8].code;
		    }
			
		}
		
		vm.goTo = function(menuKey) {
			
			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel : "Tab",
					topic   : "open",
					data    : template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
			
		}
		
		vm.edit = function(id){
			var teamplateUrl="qlk/importNoteManagement/editNotePopUp.html";
			 var title="Cập nhật phiếu nhập kho";
			 var windowId="EDIT_STOCK_TRANS";
			 vm.stockTrans={};
			 
			 impNoteManaService.getStockTransDetailById(id).then(function(d) {
				 vm.stockTrans = d.plain();
				 fillDataTableGoodsListForNote([]);
				 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
				}, function() {
					console.error('Error');
				});
		}
		
		vm.save = function(){
			impNoteManaService.updateImportNote(vm.stockTrans).then(function(d) {
				toastr.success("Cập nhật thành công!");
				}, function() {
					console.error('Error');
				});
		}
		
		vm.realImport = function(id){
			var teamplateUrl="qlk/importNoteManagement/realImportPopUp.html";
			 var title="Thực nhập phiếu nhập kho";
			 var windowId="REAL_IMPORT_STOCK_TRANS";
			 vm.stockTrans={};
			 
			 impNoteManaService.getStockTransDetailById(id).then(function(d) {
				 vm.stockTrans = d.plain();
				 fillDataTableGoodsListForNote([]);
				 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
				}, function() {
					console.error('Error');
				});
		}
		
		vm.openRemovePopup=openRemovePopup;
		function openRemovePopup(id){
			
			var grid=vm.impNoteManaGrid;
			var item=grid.table.find("tr div." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			var data =	grid.dataSource.getByUid(uid);
			vm.stockTransDelete = data;
			vm.reason ={};
			vm.reason.apply = "r4";
			vm.reason.status = 1;
			impReqManaService.getReasonForCombobox(vm.reason).then(function(d) {
				vm.dataReasonDelete = d.plain();
				data.cancelReasonName = d.plain()[0].name;
				}, function() {
					console.error('Error');
				});
			var teamplateUrl = Constant.URL_POPUP['DELETE_POPUP'];
			var title="Hủy yêu cầu nhập kho";
			var windowId="APP_PARAM"
			data.date=kendo.toString(new Date(),"dd/MM/yyyy");
			CommonService.populatePopupCreate(teamplateUrl,title,data,vm,windowId,false,'50%','50%'); 
		}
		
		vm.remove = function(){
			vm.stockTransDelete.cancelBy = 1;
			vm.stockTransDelete.cancelByName = "";
			impNoteManaService.removeStockTrans(vm.stockTransDelete).then(function(){
        		toastr.success("Xóa thành công!");
        		vm.doSearch();
            }, function(errResponse) {
			});
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.impNoteManaGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.impNoteManaGrid.showColumn(column);
            } else {
            	vm.impNoteManaGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.openDepartment=function(){
			var obj={};
//			obj.page=1;
//			obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data1=result.plain();
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
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		// =================================Clear
		// Textbox================================//
		vm.clearSearchDate = function(){
			vm.stockTransSearch.fromDate = "";
			vm.stockTransSearch.toDate = "";
		}
		vm.clearOrderCode = function(){
			vm.stockTransSearch.orderCode = "";
		}
		vm.clearSearchStatus = function(){
			vm.stockTransSearch.listStatus = [];
		}
		vm.clearSearchCreatedBy = function(){
			vm.stockTransSearch.createdByName = "";
		}
		vm.clearSearchSignState = function(){
			vm.stockTransSearch.listSignState = [];
		}
		
		// =================================End Clear
		// Textbox===============================//
	}
})();