(function() {
	'use strict';
	var controllerId = 'stockDailyImportExportController';
	
	angular.module('MetronicApp').controller(controllerId, stockDailyImportExportController);
	
	function stockDailyImportExportController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, 
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTable = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stockDailyImportExportSearch={
				goodsIsSerial: 2
		};
		vm.stockDailyImportExport={};
		
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
      '<span>Mã</span>' +
      '<span>Tên</span>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		vm.gridCommon = [ {
			title: "Mã kho",
			field: "code",
			width: 120
		}, {
			title: "Tên kho",
			field: "name",
			width: 120
		}];
		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		
		function fillDataTable(data) {
			vm.stockDailyImportExportGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
                sortable: true,
                columnMenu: true,
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
							url: Constant.BASE_SERVICE_URL + "stockDailyImportExportServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockDailyImportExportSearch.page = options.page
								vm.stockDailyImportExportSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockDailyImportExportSearch)

						}
					},					 
					pageSize: 20,
					group: {
						field: "goodsType", 
				        aggregates: [
				          { field: "amount", aggregate: "sum" }
				        ]
				    },
			        aggregate: [
			            { field: "amount", aggregate: "sum" }
			        ] 	
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#stockDailyImportExportGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},  {
					title: "Thông tin hàng hoá",
					field: 'ie',
			        width: 300,
			        columns:[
			        	{
			        		title: "Mã hàng hoá",
			        		field: "goodsType",
                            width: 150
			        	},
			        	{
			        		title: "Tên hàng hoá",
			        		field: "goodsName",
                            width: 150
			        	},
			        	{
			        		title: "Đơn vị tính",
			        		field: "goodsUnitName",
                            width: 150
			        	}
			        ],
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tổng số lượng",
			        field: 'stock',
			        width: 400,
			        columns:[
			        	{
			        		title: "Tồn đầu kỳ",
			        		field: "goodsId",
                            width: 120
			        	},
			        	{
			        		title: "Nhập",
			        		field: "goodsName",
                            width: 80
			        	},
			        	{
			        		title: "Xuất",
			        		field: "goodsUnitName",
                            width: 80
			        	},
			        	{
			        		title: "Tồn cuối kỳ",
			        		field: "goodsUnitName",
                            width: 120
			        	}
			        ],
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'goods',
			        width: 100,
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
			var grid = vm.stockDailyImportExportGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			vm.stockDailyImportExport={};
			vm.showDetail = false;
		}
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.stockDailyImportExportSearch={
					goods_state_name:"2",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.stockDailyImportExportGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		// clear form
		vm.cancelStock = function(id) {
			if(id==="clearStock"){
				$('#stock_name').val('');
			}
			else if (id==="clearPerson"){
				$('#person_name').val('');
			}
			else if (id==="clearDate"){
				$(".k-datepicker input").val('');
			}
			else if(id==="deselect-all"){
				vm.stockDailyImportExportSearch.listStockTransType=[];
			}
		}
	}
})();

