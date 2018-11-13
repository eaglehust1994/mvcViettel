(function() {
	'use strict';
	var controllerId = 'stockGoodsTotalReponseController';
	
	angular.module('MetronicApp').controller(controllerId, stockGoodsTotalReponseController);
	
	function stockGoodsTotalReponseController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, stockGoodsTotalReponseRsServiceRest,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
        vm.showDetail = false;
		vm.stockGoodsTotalReponseSearch={
				goodsState:2,
				reponseStatus:2
		};
		vm.stockGoodsTotalReponse={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
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

//		Lấy dữ liệu từ bảng Stock_Goods_Total_Reponse
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
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
					pageSize: 10,
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
				
				dataBound: function () {
					/* var GridDestination = $("#stockGoodsTotalReponseGrid").data("kendoGrid");                
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
				columns: [
				{
					title: "STT",
					field:"stt",
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
			        width: '17%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên hàng hoá",
			        field: 'goodsName',
			        width: '24%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},{
					title: "Trạng thái",
			        field: 'goodsStateName',
			        width: '10%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},{
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: '10%',
			        //footerTemplate: '<span style=\'float:left;\'>Tổng</span>',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, 
				{
					title: "Số lượng tồn kho",
			        field: 'amountRemain',
			        width: '13%',
			        aggregates: ["sum"],
					format: '{0:n0}{1}',
			        //footerTemplate: "<div style='float: right'>#= sum #",
			        groupFooterTemplate: "<div style='float: right'>{{ dataItem.amountRemain.sum.toLocaleString() }}",
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
				},
				{
					title: "Số lượng yêu cầu",
			        field: 'amountOrder',
			        width: '13%',
			        aggregates: ["sum"],
					format: '{0:n0}{1}',
			        //footerTemplate: "<div style='float: right'>#= sum #",
			        groupFooterTemplate: "<div style='float: right'>{{ dataItem.amountOrder.sum.toLocaleString() }}",
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
				},
				{
					title: "Còn dư",
			        field: 'amountIssue',
			        width: '8%',
					format: '{0:n0}{1}',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
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
		}
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.stockGoodsTotalReponseSearch={
					goodsState:2,
					reponseStatus:2
			};
			doSearch();
		}
		
//		Tìm kiếm
		vm.doSearch= doSearch;
		function doSearch(){
			if(!vm.validator.validate()){
				$("#stockResponseCombo").focus();
				return;
			}
			var grid =vm.stockGoodsTotalReponseGrid;
			fillDataTable([]);
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10,
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
		
//		clear form
		vm.cancelStock = function(id) {
			if(id==="clearGoodsType"){
				 $('#listGoodsTypeReponse').data("kendoMultiSelect").value([]);
				 vm.stockGoodsTotalReponseSearch.listGoodsType=null;
			}
			if(id==="clearGoods"){
				vm.stockGoodsTotalReponseSearch.name=null;
			}
		}
		
//		Hiển thị Multiselect Loại hàng hoá
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
				pageSize: 10,
				
			},
		  dataTextField: "name",
		  dataValueField: "goodsTypeId"
		});
		
//		Xuất file PDF, Excel
		vm.exportpdf= function(){
			if(!vm.validator.validate()){
				$("#stockResponseCombo").focus();
				return;
			}
         	var obj2={};
         	obj2.keySearch=vm.stockGoodsTotalReponseSearch.keySearch;
         	obj2.goodsState=vm.stockGoodsTotalReponseSearch.goodsState;
         	obj2.listGoodsType=vm.stockGoodsTotalReponseSearch.listGoodsType;
         	obj2.name=vm.stockGoodsTotalReponseSearch.name;
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
			if(!vm.validator.validate()){
				$("#stockResponseCombo").focus();
				return;
			}
         	var obj2={};
         	obj2.keySearch=vm.stockGoodsTotalReponseSearch.keySearch;
         	obj2.goodsState=vm.stockGoodsTotalReponseSearch.goodsState;
         	obj2.listGoodsType=vm.stockGoodsTotalReponseSearch.listGoodsType;
         	obj2.name=vm.stockGoodsTotalReponseSearch.name;
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
		
		//OnkeyDown 
    	$(document).on("keydown", function (e) {
            if (e.keyCode === 13) {
            	$("#doSearchResponse").click();
            }
            });
    	//End
    	
    	//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		});
		//
		
		//Enter
		$("#stockGoodsTotalReponseId").on("keypress", function (e) {
			if (e.keyCode === 13) {
				$("#doSearchResponse").focus();
				$("#doSearchResponse").click();
				}
		});
		//End
	}
})();

