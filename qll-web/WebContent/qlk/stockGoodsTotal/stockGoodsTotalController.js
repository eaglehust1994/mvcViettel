(function() {
	'use strict';
	var controllerId = 'stockGoodsTotalController';
	
	angular.module('MetronicApp').controller(controllerId, stockGoodsTotalController);
	
	function stockGoodsTotalController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, stockGoodsTotalServiceRest,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stockGoodsTotalSearch={
				goodsState:0,
		};
		vm.stockGoodsTotal={};
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
      '<p class="col-md-6 text-header-auto">Tên</p>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		vm.gridCommon = [ {
			title: "Mã",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
		
		//Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		
		function fillDataTable(data) {
			vm.stockGoodsTotalGridOptions = kendoConfig.getGridOptions({
				autoBind: false,
				resizable: false,
				sortable: false,
				columnMenu: false,
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsTotalServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockGoodsTotalSearch.page = options.page
								vm.stockGoodsTotalSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockGoodsTotalSearch)

						}
					},					 
					pageSize: 20,
					group: {
				        field: "goodsTypeName", aggregates: [
				        	  { field: "amount", aggregate: "sum" },
				        	],
					},
					aggregate: [
				        { field: "amount", aggregate: "sum" },
			        ],
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
			        template: dataItem => $("#stockGoodsTotalGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, {
					title: "Mã hàng hoá",
			        field: 'goodsCode',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng hoá",
			        field: 'goodsName',
			        width: 160,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'goodsStateName',
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
					 width: 120,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
					title: "Số lượng",
					field: 'amount',
			        width: 120,
			        aggregates: ["sum"],
			        footerTemplate: "<div style='float: right'>#=sum#",
			        groupFooterTemplate: "<div style='float: right'>#=sum#",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},
				{ 
					title:"Loại hàng hoá",
					field: "goodsTypeName",
					hidden: true,
			    }
				]
			});
		}
		
		function refreshGrid(d) {
			var grid = vm.stockGoodsTotalGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			vm.stockGoodsTotal={};
			vm.showDetail = false;
		}
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.stockGoodsTotalSearch={
					goodsState:2,
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  //vm.showDetail = false;
			var grid =vm.stockGoodsTotalGrid;	
			fillDataTable([]);
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20,
					group: {
				        field: "goodsTypeName",  aggregates: [
				        	  { field: "amount", aggregate: "sum" },
				        	],
					},
					aggregate: [
				        { field: "amount", aggregate: "sum" },
			        ],
				});
			}
		}
		
		// clear form
		vm.cancelStock = function(id) {
			if(id==="deselect-all"){
				 $('#listGoodsType').data("kendoMultiSelect").value([]);
				 vm.stockGoodsTotalSearch.listGoodsType=null;
			}
			if(id==="clearGoods"){
				 vm.stockGoodsTotalSearch.goodsName="";
			}
		}
		
		// multiselect
		$("#listGoodsType").kendoMultiSelect({
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
						url: Constant.BASE_SERVICE_URL + "goodsTypeRsServiceRest/getList",
						contentType: "application/json; charset=utf-8",
						type: "POST"
					},					
					parameterMap: function (options, type) {
					    vm.stockGoodsTotalSearch.page = options.page
						vm.stockGoodsTotalSearch.pageSize = options.pageSize

						return JSON.stringify(vm.stockGoodsTotalSearch)
					}
				},					 
				pageSize: 20,
				
			},
		  dataTextField: "name",
		  dataValueField: "goodsTypeId"
		});
		
		vm.exportpdf= function(){
         	var obj={};
         	obj.stockId=vm.stockGoodsTotalSearch.stockId;
         	obj.goodsState=vm.stockGoodsTotalSearch.goodsState;
         	obj.listGoodsType=vm.stockGoodsTotalSearch.listGoodsType;
         	obj.goodsName=vm.stockGoodsTotalSearch.goodsName;
         	obj.reportType="PDF";
         	obj.reportName="baocaotonkho";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/pdf'});
			        kendo.saveAs({dataURI: binarydata, fileName: "BaoCaoTonKho" + '.pdf'});
				}, function(errResponse) {
					toastr.error("Lỗi không export PDF được!");
				});
         }
		
		vm.exportexcel= function(){
         	var obj={};
         	obj.stockId=vm.stockGoodsTotalSearch.stockId;
         	obj.goodsState=vm.stockGoodsTotalSearch.goodsState;
         	obj.listGoodsType=vm.stockGoodsTotalSearch.listGoodsType;
         	obj.goodsName=vm.stockGoodsTotalSearch.goodsName;
         	obj.reportType="EXCEL";
         	obj.reportName="baocaotonkho";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
			        kendo.saveAs({dataURI: binarydata, fileName: "BaoCaoTonKho" + '.xlsx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export EXCEL được!");
				});
         }
	}
})();