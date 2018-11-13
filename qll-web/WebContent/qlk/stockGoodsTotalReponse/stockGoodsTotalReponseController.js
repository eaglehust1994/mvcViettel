(function() {
	'use strict';
	var controllerId = 'stockGoodsTotalReponseController';
	
	angular.module('MetronicApp').controller(controllerId, stockGoodsTotalReponseController);
	
	function stockGoodsTotalReponseController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, stockGoodsTotalReponseRsServiceRest,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stockGoodsTotalReponseSearch={
				goodsState2:2,
				reponseStatus:2
		};
		vm.stockGoodsTotalReponse={};
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
		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}

		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
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
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "stockGoodsTotalReponseRsServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockGoodsTotalReponseSearch.page = options.page
								vm.stockGoodsTotalReponseSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockGoodsTotalReponseSearch)

						}
					},					 
					pageSize: 20,
					group: {
						field: "goodsTypeName", aggregates:[
					        { field: "amountRemain", aggregate: "sum" },
					        { field: "amountOrder", aggregate: "sum" }
						],
					},
			        aggregate: [
			          { field: "amountRemain", aggregate: "sum" },
			          { field: "amountOrder", aggregate: "sum" }
			        ]
				},
				
				//dataSource: data,
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
			        template: dataItem => $("#stockGoodsTotalReponseGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, {
					title: "Mã hàng",
			        field: 'goodsCode',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng hoá",
			        field: 'goodsName',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
			        field: 'goodsStateName',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 120,
			        footerTemplate: '<span style=\'float:left;\'>Tổng</span>',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Số lượng tồn kho",
			        field: 'amountRemain',
			        width: 150,
			        aggregates: ["sum"],
			        footerTemplate: "<div style='float: right'>#= sum #",
			        groupFooterTemplate: "<div style='float: right'>{{ dataItem.amountRemain.sum }}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},
				{
					title: "Số lượng yêu cầu",
			        field: 'amountOrder',
			        width: 150,
			        aggregates: ["sum"],
			        footerTemplate: "<div style='float: right'>#= sum #",
			        groupFooterTemplate: "<div style='float: right'>{{ dataItem.amountOrder.sum }}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},
				{
					title: "Còn dư",
			        field: 'amountIssue',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{ 
					title:"Loại hàng hoá",
					field: "goodsTypeName",
					hidden: true,
			    }, {
			        field: 'goodsState',
			        hidden:true,
				}]
			});
		}
		
		function refreshGrid(d) {
			var grid = vm.stockGoodsTotalReponseGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			vm.stockGoodsTotalReponse={};
			vm.showDetail = false;
			//vm.isCreateNew = false;
		}
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.stockGoodsTotalReponseSearch={
					goodsState:2,
					reponseStatus:2
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			 // vm.showDetail = false;
			var grid =vm.stockGoodsTotalReponseGrid;
			fillDataTable([]);
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20,
					group: {
						field: "goodsTypeName", aggregates:[
					        { field: "amountRemain", aggregate: "sum" },
					        { field: "amountOrder", aggregate: "sum" }
						],
					},
			        aggregate: [
			          { field: "amountRemain", aggregate: "sum" },
			          { field: "amountOrder", aggregate: "sum" }
			        ]
				});
			}
		}
		
		// clear form
		vm.cancelStock = function(id) {
			if(id==="clearGoodsType"){
				 $('#listGoodsTypeReponse').data("kendoMultiSelect").value([]);
				 vm.stockGoodsTotalReponseSearch.listGoodsTypeReponse=null;
			}
			if(id==="clearGoods"){
				 vm.stockGoodsTotalReponseSearch.goodsName="";
			}
		}
		
		// multiselect
		$("#listGoodsTypeReponse").kendoMultiSelect({
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
					    vm.stockGoodsTotalReponseSearch.page = options.page
						vm.stockGoodsTotalReponseSearch.pageSize = options.pageSize

						return JSON.stringify(vm.stockGoodsTotalReponseSearch)
					}
				},					 
				pageSize: 20,
				
			},
		  dataTextField: "name",
		  dataValueField: "goodsTypeId"
		});
		
		
		vm.exportpdf= function(){
         	var obj2={};
         	obj2.stockId2=vm.stockGoodsTotalReponseSearch.stockId2;
         	obj2.goodsState2=vm.stockGoodsTotalReponseSearch.goodsState2;
         	obj2.listGoodsTypeReponse=vm.stockGoodsTotalReponseSearch.listGoodsTypeReponse;
         	obj2.goodsName2=vm.stockGoodsTotalReponseSearch.goodsName2;
         	obj2.reponseStatus=vm.stockGoodsTotalReponseSearch.reponseStatus;
         	obj2.reportType="PDF";
         	obj2.reportName="baocaokndu";
         	CommonService.exportReport(obj2).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/pdf'});
			        kendo.saveAs({dataURI: binarydata, fileName: "BaoCaoKhaNangDapUng" + '.pdf'});
				}, function(errResponse) {
					toastr.error("Lỗi không export PDF được!");
				});
         }
		
		vm.exportexcel= function(){
         	var obj2={};
         	obj2.stockId2=vm.stockGoodsTotalReponseSearch.stockId2;
         	obj2.goodsState2=vm.stockGoodsTotalReponseSearch.goodsState2;
         	obj2.listGoodsTypeReponse=vm.stockGoodsTotalReponseSearch.listGoodsTypeReponse;
         	obj2.goodsName2=vm.stockGoodsTotalReponseSearch.goodsName2;
         	obj2.reponseStatus=vm.stockGoodsTotalReponseSearch.reponseStatus;
         	obj2.reportType="EXCEL";
         	obj2.reportName="baocaokndu";
         	CommonService.exportReport(obj2).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
			        kendo.saveAs({dataURI: binarydata, fileName: "BaoCaoKhaNangDapUng" + '.xlsx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export EXCEL được!");
				});
         }
		
	}
})();

