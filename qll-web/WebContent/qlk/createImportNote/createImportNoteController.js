(function() {
	'use strict';
	var controllerId = 'creImpNoteController';
	
	angular.module('MetronicApp').controller(controllerId, creImpNoteController);
	
	function creImpNoteController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,creImpNoteService, impReqManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTabOne = true;
		vm.showAdvancedSearch = false;
		vm.disableCheckboxes = true;
		vm.folder = '',
		vm.orderSearch ={};
		vm.orderGoodsSearch = {};
		vm.orderGoodsDetailSearch ={};
		vm.orderGoodsSearchForNote = {};
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
		
		vm.dataList={
				hederList:[],
				data:[]
		};
		vm.validateColums=Constant.COLUMS_VALIDATE.goods;
		
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
		vm.orderSearch.listStatus = ['1'];
		
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
		creImpNoteService.getForOrderCheckboxes(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
			fillDataTableCreImpNote([]);
			var obj={};
			obj.data={};
			obj.data[d.data[0].code]="Nhập từ nhà cung cấp";
			obj.data[d.data[1].code]='Nhập trước BHSC';
			obj.data[d.data[2].code]='Nhập sau BHSC';
			obj.data[d.data[3].code]='Nhập thu hồi từ công trình';
			obj.data[d.data[4].code]='Nhập thu hồi từ đơn vị';
			obj.data[d.data[5].code]='Nhập thu hồi từ hàng cho mượn';
			obj.data[d.data[6].code]='Nhập luân chuyển kho';
			obj.data[d.data[7].code]='Nhập sản phẩm của đề tài/dự án';
			obj.data[d.data[8].code]='Nhập trực tiếp';
			obj.field="bussinessType";
			vm.listConvert.push(obj);
		});
		
		initData();
		function initData(){
			var d = new Date();
			var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
			vm.orderSearch.fromDate = datestring;
			vm.orderSearch.toDate = null;
		}
		
		vm.doSearch = doSearch;
		function doSearch(){
			var grid = $("#creImpNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
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
    			
    			vm.orderGoodsDetailSearchForNote = dataItem;
    			fillDataTableGoodsDetailForNote([]);
			}
			
		}
		
		vm.listRemove=[{
			title: "Viết phiếu",
		}]
		vm.exportExcelGrid = function(){
			creImpNoteService.getForExportGrid(vm.orderSearch).then(function(d) {
				CommonService.exportFile(vm.creImpNoteGrid,d.data,vm.listRemove,vm.listConvert);
			});
		}
		function fillDataTableCreImpNote(data) {
			vm.creImpNoteGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				excel: {
			        allPages: true,
			        fileName: "Viết phiếu nhập kho.xlsx",
			        filterable:true
			    },
				dataBound: function (e) {
				    var grid = $("#creImpNoteGrid").data("kendoGrid");
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
				        vm.createImportNote(dataItem.orderId);
				    });
				},
				toolbar: [
		                    {
		                        name: "actions",
		                        template: '<div class=" ">'+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                    '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.creImpNoteGrid.columns| filter: vm.gridColumnShowHideFilter">'+
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
							url: Constant.BASE_SERVICE_URL + "orderServiceRest/order/doSearchForCreateImportNote",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.orderSearch.page = options.page
								vm.orderSearch.pageSize = options.pageSize

								return JSON.stringify(vm.orderSearch)
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
			        template: dataItem => $("#creImpNoteGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã yêu cầu",
					field: 'code',
					template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(#=orderId#)>#=code#</a>',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại yêu cầu",
			        field: 'bussinessType',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					template: function($scope){
						if($scope.bussinessType == vm.businessTypes[0].code){
							return "Nhập từ nhà cung cấp";
						}else if($scope.bussinessType == vm.businessTypes[1].code){
							return "Nhập trước BHSC";
						}else if($scope.bussinessType == vm.businessTypes[2].code){
							return "Nhập sau BHSC";
						}else if($scope.bussinessType == vm.businessTypes[3].code){
							return "Nhập thu hồi từ công trình";
						}else if($scope.bussinessType == vm.businessTypes[4].code){
							return "Nhập thu hồi từ đơn vị";
						}else if($scope.bussinessType == vm.businessTypes[5].code){
							return "Nhập thu hồi từ hàng cho mượn";
						}else if($scope.bussinessType == vm.businessTypes[6].code){
							return "Nhập luân chuyển kho";
						}else if($scope.bussinessType == vm.businessTypes[7].code){
							return "Nhập sản phẩm của đề tài/dự án";
						}else if($scope.bussinessType == vm.businessTypes[8].code){
							return "Nhập trực tiếp";
						}
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
					title: "Ngày tạo",
					 field: 'createdDate',
			        width: 200,
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
					title: "Đơn vị tạo",
					 field: 'createdDeptedName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Ký CA",
					 field: 'signState',
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
							return "Chưa tạo phiếu";
						}else if($scope.status == 2){
							return "Đã tạo phiếu";
						}else if($scope.status == 3){
							return "Đã nhập";
						}else if($scope.status == 4){
							return "Đã hủy";
						}else if($scope.status == 5){
							return "Đã từ chối";
						}
					},
				},{
					title: "Viết phiếu",
			        template:
			        	'<div class="text-center #=orderId#""><a '+
						' class=" icon_table #=orderId#"'+
						' ng-click=vm.createImportNote(#=orderId#)  uib-tooltip="Viết phiếu" translate>'+
						'<i class="fa fa-file-text" aria-hidden="true"></i>'+
					'</a>'+
					'<a type="button"'+
					'class="#=orderId# icon_table"  uib-tooltip="Từ chối yêu cầu nhập kho" translate'+
						' ng-click=vm.openRejectPopup(#=orderId#)>'+
					'<i class="fa fa-trash" aria-hidden="true"></i>	'+
					'</a>'+'</div>',
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.orderGoodsSearchForNote.page = options.page
								vm.orderGoodsSearchForNote.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsSearchForNote)
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsDetailServiceRest/orderGoodsDetail/doSearchGoodsDetailForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.orderGoodsDetailSearchForNote.page = options.page
								vm.orderGoodsDetailSearchForNote.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsDetailSearchForNote)
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
					title: "vị trí",
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
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
		}
		vm.advancedSearch = function(){
			vm.showAdvancedSearch = !vm.showAdvancedSearch;
		}
		
		vm.showDetail=function(id){
			var teamplateUrl="qlk/createImportNote/detailPopup.html";
			 var title="Chi tiết yêu cầu nhập kho";
			 var windowId="ORDER";
			 vm.order={};
			 creImpNoteService.getOrderDetailById(id).then(function(d) {
				 vm.order = d.plain();
				 vm.orderGoodsSearch.orderId = vm.order.orderId;
				 fillDataTableGoodsList([]);
					CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.createImportNote=function(id){
			var teamplateUrl="qlk/createImportNote/createImportNotePopUp.html";
			 var title="Tạo phiếu nhập kho";
			 var windowId="CREATEIMPORTNOTE";
			 vm.orderForNote={};
			 creImpNoteService.getOrderDetailById(id).then(function(d) {
				 vm.orderForNote = d.plain();
				 vm.orderGoodsSearchForNote.orderId = vm.orderForNote.orderId;
				 fillDataTableGoodsListForNote([]);
					CommonService.populatePopupCreate(teamplateUrl,title,vm.orderForNote,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.openRejectPopup = openRejectPopup;
		function openRejectPopup(id){
			var grid=vm.creImpNoteGrid;
			var item=grid.table.find("tr div." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			var data =	grid.dataSource.getByUid(uid);
			vm.orderReject = data;
			vm.reason ={};
			vm.reason.apply = "r6";
			vm.reason.status = 1;
			impReqManaService.getReasonForCombobox(vm.reason).then(function(d) {
				vm.dataReasonDelete = d.plain();
				data.cancelReasonName = d.plain()[0].name;
				}, function() {
					console.error('Error');
				});
			var teamplateUrl = "qlk/createImportNote/cancelOrderPopup.html";
			var title="Từ chối yêu cầu nhập kho";
			var windowId="APP_PARAM"
			data.date=kendo.toString(new Date(),"dd/MM/yyyy");
			CommonService.populatePopupCreate(teamplateUrl,title,data,vm,windowId,false,'50%','50%');
		}
		
		vm.cancel = function(){
			
		}
		
		vm.reject = function(){
			vm.orderReject.cancelBy = 1;
			vm.orderReject.cancelByName = "";
			creImpNoteService.rejectOrder(vm.orderReject).then(function(){
        		toastr.success("Từ chối thành công!");
        		
            }, function(errResponse) {
			});
		}
		
		vm.checkboxDetailPopup = function(){
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
		
		vm.create = function(){
			var orderGoodsDTO={}
			 orderGoodsDTO.orderId = vm.orderForNote.orderId;
			vm.orderForNote.orderGoodsDTO=orderGoodsDTO;
			creImpNoteService.createImportNote(vm.orderForNote).then(function() {
				toastr.success("Lưu thành công ");	
	     }, function(errResponse) {			    	 
	    		 toastr.error("Lỗi khi lưu");
	     });
		}
		vm.createAndRealImport= function(){
			var orderGoodsDTO={}
			 orderGoodsDTO.orderId = vm.orderForNote.orderId;
			vm.orderForNote.orderGoodsDTO=orderGoodsDTO;
			creImpNoteService.createAndRealImportNote(vm.orderForNote).then(function() {
				toastr.success("Lưu thành công");	
	     }, function(errResponse) {			    	 
	    		 toastr.error("Lỗi khi lưu");
	     });
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
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.creImpNoteGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.creImpNoteGrid.showColumn(column);
            } else {
            	vm.creImpNoteGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.onSave=onSave;
		function onSave(data, popupID){
			if(popupID == 'create'){
				vm.orderSearch.createdDeptedName = data.text;
				vm.orderSearch.createdDeptedId = data.code;
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
		
		//=================================Clear Textbox================================//
		vm.clearSearchDate = function(){
			vm.orderSearch.fromDate = "";
			vm.orderSearch.toDate = "";
		}
		vm.clearSearchStatus = function(){
			vm.orderSearch.listStatus = [];
		}
		vm.clearSearchCreatedBy = function(){
			vm.orderSearch.createdByName = "";
		}
		vm.clearSearchSignState = function(){
			vm.orderSearch.listSignState = [];
		}
		
		vm.clearSearchBusinessType = function(){
			vm.orderSearch.listBussinessType = [];
		}
		//=================================End Clear Textbox===============================//
		function getFolder() {
			Restangular.one(RestEndpoint.ORDER_GOOD_UPLOAD_FOLDER_URL+"/folder").get().then(function(data) {
				vm.folder = data.folder;
			  });
		}
		getFolder();
		
		 vm.submit=submit;
	        function submit(){
	        	
		        var formData = new FormData();
				formData.append('multipartFile', $('#file')[0].files[0]); 
		     return   $.ajax({
		            url: "/ktts-service/orderGoodsServiceRest/orderGoods/importGoods?folder="+ vm.folder,
		            type: "POST",
		            data: formData,
		            enctype: 'multipart/form-data',
		            processData: false,
		            contentType: false,
		            cache: false,
		            success:function(data) {
		                alert(data); 
		                
		                $.each(data, function(index, orderGood) {
		                	alert(orderGood.codePosition); //to print name of employee
		                });  
		                
		            }
		        });
		      
	        
            }
	}
})();