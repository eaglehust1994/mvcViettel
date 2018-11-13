(function() {
	'use strict';
	var controllerId = 'stockDailyImportExportController';
	
	angular.module('MetronicApp').controller(controllerId, stockDailyImportExportController);
	
	function stockDailyImportExportController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, stockDailyImportExportServiceRest,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;

		vm.stockDailyImportExportSearch={
			
		};
		
		vm.stockDailyImportExport={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
        vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
      '<p class="col-md-6 text-header-auto">Tên</p>' +
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
		
		var d = new Date();
		var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
		vm.stockDailyImportExportSearch.startDate = datestring;
		vm.stockDailyImportExportSearch.endDate = null;

		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		
		var rowNumber = 0;
		function fillDataTable(data) {
			vm.stockDailyImportExportGridOptions = kendoConfig.getGridOptions({
				autoBind: false,
                sortable: false,
                resizable: true,
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
							Allocated: function () {
                                return this.amount + this.amountTotalImport - this.amountTotalExport;
                            },
		                },
					transport: {
						read: {
							url: Constant.BASE_SERVICE_URL + "stockDailyImportExportServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							if ((vm.stockDailyImportExportSearch.endDate == "")||(vm.stockDailyImportExportSearch.endDate == null)){
								vm.stockDailyImportExportSearch.endDate = d.getDate()  + "/" + d.getMonth()+1 + "/" + d.getFullYear() ;
							}
						    vm.stockDailyImportExportSearch.page = options.page
							vm.stockDailyImportExportSearch.pageSize = options.pageSize
							
							return JSON.stringify(vm.stockDailyImportExportSearch)
						}
					},					 
					pageSize: 20,
					group: {
						field: "goodsTypeName", dir: "desc", aggregates: [
			        	  { field: "amount", aggregate: "sum" },
			        	  { field: "amountTotalImport", aggregate: "sum"},
			        	  { field: "amountTotalExport", aggregate: "sum" },
			        	  { field: "amountFinal", aggregate: "sum"},
			        	],
			        },
			        aggregate: [
			        	{ field: "goodsTypeName", aggregate: "count"},
				        { field: "amount", aggregate: "sum" },
				        { field: "amountTotalImport", aggregate: "sum"},
			        	{ field: "amountTotalExport", aggregate: "sum"},
			        	{ field: "amountFinal", aggregate: "sum"},
			        ],
				},
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				
				dataBound: function () {
					var sumAmount = parseFloat($("#sumAmount").html());
                    var sumAmountTotalImport = parseFloat($("#sumAmountTotalImport").html());
                    var sumAmountTotalExport = parseFloat($("#sumAmountTotalExport").html());
                    $("#sumAmountFinal").html(sumAmount + sumAmountTotalImport - sumAmountTotalExport);
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
					field: "#",
			        template: dataItem => $("#stockDailyImportExportGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
					width: 60,
			        filterable: false,
			        headerAttributes: {style: "text-align:center;"},
					attributes: {style: "text-align:center;"},
				},
				{
					title: "Thông tin hàng hoá",
			        width: 270,
			        columns:[
		        	{
		        		title: "Mã hàng hoá",
		        		field: "goodsCode",
                        width: 120,
                        headerAttributes: {style: "text-align:center;"},
    					attributes: {style: "text-align:left;"},
		        	},
		        	{
		        		title: "Tên hàng hoá",
		        		field: "goodsName",
                        width: 180,
                        headerAttributes: {style: "text-align:center;"},
    					attributes: {style: "text-align:left;"},
		        	},
		        	{
		        		title: "Đơn vị tính",
		        		field: "goodsUnitName",
                        width: 100,
                        footerTemplate: '<span style=\'float:left;\'>Tổng</span>',
                        headerAttributes: {style: "text-align:center;"},
    					attributes: {style: "text-align:left;"},
		        	}
			        ],
			        headerAttributes: {style: "text-align:center;"},
					attributes: {style: "text-align:left;"},
				},
				{
					title: "Tổng số lượng",
			        width: 360,
			        columns:[
		        	{
		        		title: "Tồn đầu kỳ",
		        		field: "amount",
		        		width: 100,
		        		aggregates: ["sum"],
		        		footerTemplate: "<div style='float: right'>#=sum# </label>",
				        groupFooterTemplate: "<div style='float: right'> #=sum# </label>",
				        headerAttributes: {style: "text-align:center;"},
						attributes: {style: "text-align:right;"},
		        	},
		        	{
		        		title: "Nhập",
		        		field: "amountTotalImport",
		        		aggregates: ["sum"],
		        		footerTemplate: "<div style='float: right'> #=sum# </label>",
				        groupFooterTemplate: "<div style='float: right'> #=sum# </label>",
                        width: 100,
                        headerAttributes: {style: "text-align:center;"},
    					attributes: {style: "text-align:right;"},
		        	},
		        	{
		        		title: "Xuất",
		        		field: "amountTotalExport",
		        		aggregates: ["sum"],
		        		footerTemplate: "<div style='float: right'>#=sum# </label>",
				        groupFooterTemplate: "<div style='float: right'> #=sum# </label>",
                        width: 100,
                        headerAttributes: {style: "text-align:center;"},
    					attributes: {style: "text-align:right;"},
		        	},
		        	{
		        		title: "Tồn cuối kỳ",
		        		field: "amountFinal",
		        		aggregates: ["sum"],
		        		//template: '<div>#= amount + amountTotalImport - amountTotalExport#',
		        		groupFooterTemplate: "<div style='float: right'>#=sum#</label>",
		        		footerTemplate: "<div style='float: right'>#= sum #" ,
                        width: 100,
                        headerAttributes: {style: "text-align:center;"},
    					attributes: {style: "text-align:right;"},
		        	}
			        ],
			        headerAttributes: {style: "text-align:center;"},
					attributes: {style: "text-align:right;"},
				},
				{
					title: "Ghi chú",
			        field: 'stockTransDescription',
			        width: 100,
			        headerAttributes: {style: "text-align:center;"},
					attributes: {style: "text-align:left;"},
				},
				{ 
					title:"Loại hàng hoá",
					field: "goodsTypeName",
					hidden: true,
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
			};
			doSearch();
		}

		vm.doSearch= doSearch;
		function doSearch(){
		  	vm.showDetail = false;
			var grid =vm.stockDailyImportExportGrid;	
			fillDataTable([]);
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20,
					group: {
				          field: "goodsTypeName", dir: "desc", aggregates: [
				        	  { field: "amount", aggregate: "sum"},
				        	  { field: "amountTotalImport", aggregate: "sum" },
				        	  { field: "amountTotalExport", aggregate: "sum" },
				        	  { field: "amountFinal", aggregate: "sum" },
				          ],
				        },
				        aggregate: [
				        	{ field: "goodsTypeName", aggregate: "count"},
					        { field: "amount", aggregate: "sum"},
					        { field: "amountTotalImport", aggregate: "sum"},
				        	{ field: "amountTotalExport", aggregate: "sum" },
				        	{ field: "amountFinal", aggregate: "sum"},
				        ],
				});
			}
		}
		
		// clear form
		vm.cancelStock = function(id) {
			if (id==="clearDate"){
				$(".k-datepicker input").val('');
			}
			else if(id==="deselect-all"){
				vm.stockDailyImportExportSearch.listStockTransType=[];
			}
		}
		
		//Export PDF, excel
		vm.exportpdf= function(){
         	var obj={};
         	stockDailyImportExportServiceRest.exportpdf(obj).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/pdf'});
			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo xuất nhập theo kỳ" + '.pdf'});
				}, function(errResponse) {
					toastr.error("Lỗi không export PDF được!");
				});
         }
	}
})();

