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
		vm.stockGoodsTotalSearch.listStockId=[];
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.stockGoodsTotal={};
		
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
      '<p class="col-md-6 text-header-auto">Tên</p>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		
//		Khởi tạo dữ liệu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
	
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
//		Lấy dữ liệu từ bảng Stock_Goods_Total
		var record=0;
		function fillDataTable(data) {
			vm.stockGoodsTotalGridOptions = kendoConfig.getGridOptions({
				autoBind: false,
				resizable: true,
				sortable: false,
				columnMenu: false,
				scrollable: false,
				dataSource:{
					serverPaging: true,
					 schema: {
						errors: function (response) {
								if(response.error){
									toastr.error(response.error);
								}
								return response.error;
							},
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
					pageSize: 10,
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
				
				dataBound: function () {
					/* var GridDestination = $("#stockGoodsTotalGrid").data("kendoGrid");                
					 if (GridDestination.dataSource.total() < 10) {
			                GridDestination.pager.element.hide();
			            }
			            else {
			                GridDestination.pager.element.show();
			            } */
                },
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					field:"#",
			        template: function () {
						return ++record;
					},
					width: '5%',
			        filterable: false,
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}, {
					title: "Mã hàng hoá",
			        field: 'goodsCode',
			        width: '20%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên hàng hoá",
			        field: 'goodsName',
			        width: '30%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Trạng thái",
					field: 'goodsStateName',
					width: '15%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Đơn vị tính",
					field: 'goodsUnitName',
					width: '15%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Số lượng",
					field: 'amount',
			        width: '15%',
			        aggregates: ["sum"],
					format: '{0:n0}{1}',
			        //footerTemplate: "<div style='float: right'>#=sum#",
			        groupFooterTemplate: "<div style='float: right'>#=sum.toLocaleString()#",
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
				}, { 
					title:"Loại hàng hoá",
					field: "goodsTypeName",
					hidden: true,
			    }
				]
			});
		}
		
		function refreshGrid(d,grid) {
			var grid = grid;
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
		
//		Tìm kiếm
		vm.doSearch= doSearch;
		function doSearch(){
			if(!vm.validator.validate()){
				$("#stockGoodsTotalCombo").focus();
				return;
			}
			vm.showDetail = false;
			var grid =vm.stockGoodsTotalGrid;	
			fillDataTable([]);
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10,
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
				 vm.stockGoodsTotalSearch.name="";
			}
		}
		
//		multiselect loại hàng hoá
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
				pageSize: 10,
				
			},
		  dataTextField: "name",
		  dataValueField: "goodsTypeId"
		});
		
//		Xuất file PDF, Excel
		vm.exportpdf= function(){
			if(!vm.validator.validate()){
				$("#stockGoodsTotalCombo").focus();
				return;
			}
         	var obj={};
         	obj.listStockId=vm.stockGoodsTotalSearch.listStockId;
         	obj.goodsState=vm.stockGoodsTotalSearch.goodsState;
         	obj.listGoodsType=vm.stockGoodsTotalSearch.listGoodsType;
         	obj.name=vm.stockGoodsTotalSearch.name;
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
			if(!vm.validator.validate()){
				$("#stockGoodsTotalCombo").focus();
				return;
			}
         	var obj={};
         	obj.listStockId=vm.stockGoodsTotalSearch.listStockId;
         	obj.goodsState=vm.stockGoodsTotalSearch.goodsState;
         	obj.listGoodsType=vm.stockGoodsTotalSearch.listGoodsType;
         	obj.name=vm.stockGoodsTotalSearch.name;
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
		
		//OnkeyDown 
    	$(document).on("keydown", function (e) {
            if (e.keyCode === 13) {
            	$("#doSearchTotal").click();
            }
            });
    	//End
		
		//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		});
		//
		
		$scope.$on("Popup.open",function(){
			vm.stockGoodsTotalSearch.listStockId = [];
		});
		
		//Enter
		$("#stockGoodsTotalId").on("keypress", function (e) {
			if (e.keyCode === 13) {
				$("#doSearchTotal").focus();
				$("#doSearchTotal").click();
				}
		});
		//End
		
	}
})();
