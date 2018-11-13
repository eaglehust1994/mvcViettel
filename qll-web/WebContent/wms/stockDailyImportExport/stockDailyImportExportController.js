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
		vm.validatorOptions = kendoConfig.get('validatorOptions');
//		Set ngày mặc định lùi 1 tháng
		vm.stockDailyImportExportSearch.startDate=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy");
		vm.stockDailyImportExportSearch.endDate=null;
//		Khởi tạo dữ liệu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
			}
		}
		
//		Lấy dữ liệu từ bảng Stock_Daily_Import_Export
		var record=0;
		function fillDataTable(data) {
			vm.stockDailyImportExportGridOptions = kendoConfig.getGridOptions({
				autoBind: false,
                sortable: false,
                resizable: true,
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
							if(vm.stockDailyImportExportSearch.endDate ===""){
								vm.stockDailyImportExportSearch.endDate = null;
							}
						    vm.stockDailyImportExportSearch.page = options.page
							vm.stockDailyImportExportSearch.pageSize = options.pageSize
							
							return JSON.stringify(vm.stockDailyImportExportSearch)
						}
					},					 
					pageSize: 10,
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
					/* var GridDestination = $("#stockDailyImportExportGrid").data("kendoGrid");                
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
					title: "<div style='margin-bottom: 14px'>STT</div>",
					field: "#",
					template: function () {
						return ++record;
					},
					width: '5%',
					footerAttributes: {style: "display:none"},
					headerAttributes: {style: "text-align:center; font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				},
				{
					title: "Thông tin hàng hoá",
					field: "#",
			        width: '52%',
			        columns:[
		        	{
		        		title: "Mã hàng hoá",
		        		field: "goodsCode",
                        width: '15%',
						footerAttributes: {style: "display:none"},
                        headerAttributes: {style: "text-align:center; font-weight: bold;"},
    					attributes: {style: "text-align:left;"},
		        	},
		        	{
		        		title: "Tên hàng hoá",
		        		field: "goodsName",
                        width: '27%',
                        headerAttributes: {style: "text-align:center;font-weight: bold;"},
						footerAttributes: {style: "display:none"},
    					attributes: {style: "text-align:left;"},
		        	},
		        	{
		        		title: "Đơn vị tính",
		        		field: "goodsUnitName",
                        width: '10%',
						footerAttributes: {colspan:"4", style:"text-align: center;"},
                        footerTemplate: '<span><b>Tổng</span>',
                        headerAttributes: {style: "text-align:center;font-weight: bold;"},
    					attributes: {style: "text-align:left;"},
		        	}
			        ],
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},
				{
					title: "Tổng số lượng",
					field: "#",
			        width: '36%',
			        columns:[
		        	{
		        		title: "Tồn đầu kỳ",
		        		field: "amount",
		        		width: '9%',
		        		aggregates: ["sum"],
						format: '{0:n0}{1}',
		        		footerTemplate: "<div style='float: right'>#=sum.toLocaleString()# </label>",
				        groupFooterTemplate: "<div style='float: right'> #=sum.toLocaleString()# </label>",
				        headerAttributes: {style: "text-align:center;font-weight: bold;"},
						attributes: {style: "text-align:right;"},
		        	},
		        	{
		        		title: "Nhập",
		        		field: "amountTotalImport",
		        		aggregates: ["sum"],
						format: '{0:n0}{1}',
		        		footerTemplate: "<div style='float: right'> #=sum.toLocaleString()# </label>",
				        groupFooterTemplate: "<div style='float: right'> #=sum.toLocaleString()# </label>",
                        width: '9%',
                        headerAttributes: {style: "text-align:center;font-weight: bold;"},
    					attributes: {style: "text-align:right;"},
		        	},
		        	{
		        		title: "Xuất",
		        		field: "amountTotalExport",
		        		aggregates: ["sum"],
						format: '{0:n0}{1}',
		        		footerTemplate: "<div style='float: right'>#=sum.toLocaleString()# </label>",
				        groupFooterTemplate: "<div style='float: right'> #=sum.toLocaleString()# </label>",
                        width: '9%',
						
                        headerAttributes: {style: "text-align:center;font-weight: bold;"},
    					attributes: {style: "text-align:right;"},
		        	},
		        	{
		        		title: "Tồn cuối kỳ",
		        		field: "amountFinal",
		        		aggregates: ["sum"],
						format: '{0:n0}{1}',
		        		//template: '<div>#= amount + amountTotalImport - amountTotalExport#',
		        		groupFooterTemplate: "<div style='float: right'>#=sum.toLocaleString()#</label>",
		        		footerTemplate: "<div style='float: right'>#= sum.toLocaleString() #" ,
                        width: '9%',
                        headerAttributes: {style: "text-align:center;font-weight: bold;"},
    					attributes: {style: "text-align:right;"},
		        	}
			        ],
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
				},
				{
					title: "<div style='margin-bottom: 14px;'>Ghi chú</div>",
			        field: "stockTransDescription",
			        width: '7%',
					headerAttributes: {style: "text-align:center;font-weight: bold;"},
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

//		Tìm kiếm
		vm.flag = true;
		vm.flag1 = true;
		vm.doSearch= doSearch;
		vm.stockDailyImportExportSearch.listStockId=[];
		function doSearch(){
			
			if((!vm.validator.validate())&&((vm.stockDailyImportExportSearch.keySearch === "")||(vm.stockDailyImportExportSearch.keySearch == null))){
				$("#stockdailystockId").focus();
				return;
			}
			else if((!vm.validator.validate())&&(vm.stockDailyImportExportSearch.keySearch != "")&&(!vm.flag1)){
				$("#startDateDailyIE").focus();
				return;
			}
			else if((vm.stockDailyImportExportSearch.keySearch != "")&&(vm.stockDailyImportExportSearch.startDate != "")&&(!vm.flag1)){
				$("#startDateDailyIE").focus();
				return;
			}
			else if((vm.stockDailyImportExportSearch.keySearch != "")&&(vm.stockDailyImportExportSearch.startDate != "")&&(!vm.flag)){
				$("#endDateDailyIEx").focus();
				return;
			}
				vm.showDetail = false;
				var grid =vm.stockDailyImportExportGrid;	
				fillDataTable([]);
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10,
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
		
//		Clear form
		vm.cancelStock = function(id) {
			if (id==="clearDate"){
				vm.stockDailyImportExportSearch.startDate = null;
				vm.stockDailyImportExportSearch.endDate = null;
				vm.errMessage="";
				vm.errMessage1="";
				vm.flag=true;
				vm.flag1=false;
			}
			else if(id==="deselect-all"){
				vm.stockDailyImportExportSearch.listStockTransType=[];
			}
		}
		
//		Export PDF, excel
		vm.exportpdf= function(){
			if((!vm.validator.validate())&&((vm.stockDailyImportExportSearch.keySearch === "")||(vm.stockDailyImportExportSearch.keySearch == null))){
				$("#stockdailystockId").focus();
				return;
			}
			else if((!vm.validator.validate())&&(vm.stockDailyImportExportSearch.keySearch != "")&&(!vm.flag1)){
				$("#startDateDailyIE").focus();
				return;
			}
			else if((vm.stockDailyImportExportSearch.keySearch != "")&&(vm.stockDailyImportExportSearch.startDate != "")&&(!vm.flag1)){
				$("#startDateDailyIE").focus();
				return;
			}
			else if((vm.stockDailyImportExportSearch.keySearch != "")&&(vm.stockDailyImportExportSearch.startDate != "")&&(!vm.flag)){
				$("#endDateDailyIEx").focus();
				return;
			}
			if (vm.stockDailyImportExportSearch.endDate == null){
				vm.stockDailyImportExportSearch.endDate=kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
			}
			var obj3={};
			obj3.listStockId=vm.stockDailyImportExportSearch.listStockId;
         	obj3.startDate=vm.stockDailyImportExportSearch.startDate;
         	obj3.endDate=vm.stockDailyImportExportSearch.endDate;
			obj3.reportType="PDF";
         	obj3.reportName="baocaoxuatnhap";
         	CommonService.exportReport(obj3).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/pdf'});
			        kendo.saveAs({dataURI: binarydata, fileName: "BaoCaoXuatNhapTheoKy" + '.pdf'});
				}, function(errResponse) {
					toastr.error("Lỗi không export PDF được!");
				});
			vm.stockDailyImportExportSearch.endDate=null;
         }
		
		vm.exportexcel= function(){
			
			if((!vm.validator.validate())&&((vm.stockDailyImportExportSearch.keySearch === "")||(vm.stockDailyImportExportSearch.keySearch == null))){
				$("#stockdailystockId").focus();
				return;
			}
			else if((!vm.validator.validate())&&(vm.stockDailyImportExportSearch.keySearch != "")&&(!vm.flag1)){
				$("#startDateDailyIE").focus();
				return;
			}
			else if((vm.stockDailyImportExportSearch.keySearch != "")&&(vm.stockDailyImportExportSearch.startDate != "")&&(!vm.flag1)){
				$("#startDateDailyIE").focus();
				return;
			}
			else if((vm.stockDailyImportExportSearch.keySearch != "")&&(vm.stockDailyImportExportSearch.startDate != "")&&(!vm.flag)){
				$("#endDateDailyIEx").focus();
				return;
			}
			
			if (vm.stockDailyImportExportSearch.endDate == null){
				vm.stockDailyImportExportSearch.endDate=kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
			}
			
         	var obj3={};
         	obj3.listStockId=vm.stockDailyImportExportSearch.listStockId;
         	obj3.startDate=vm.stockDailyImportExportSearch.startDate;
         	obj3.endDate=vm.stockDailyImportExportSearch.endDate;
         	obj3.reportType="EXCEL";
         	obj3.reportName="baocaoxuatnhap";
         	CommonService.exportReport(obj3).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
			        kendo.saveAs({dataURI: binarydata, fileName: "BaoCaoXuatNhapTheoKy" + '.xlsx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export EXCEL được!");
				});
			vm.stockDailyImportExportSearch.endDate=null;
         }
		
		//OnkeyDown 
    	$(document).on("keydown", function (e) {
            if (e.keyCode === 13) {
            	$("#doSearch").click();
            }
            });
    	//End
    	
    	//validate ngay thang
    	vm.checkErr = checkErr;
    	function checkErr() {
    		var startDate = $('#startDateDailyIE').val();
    		var endDate = $('#endDateDailyIEx').val();

    		if ($('#endDateDailyIEx').val()===""){
    			endDate = "31/12/9999";
    		}
			vm.errMessage = '';
			var d = new Date();
			var curDate = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
	        
			if(($('#stockdailystockId').val()!="")&&(!vm.validator.validate())){
				$("#endDateDailyIEx").focus();
				vm.flag = false;
				return ;
			}
			
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage = 'Ngày đến phải lớn hơn hoặc bằng ngày tạo';
	          $("#endDateDailyIEx").focus();
			  vm.flag = false;
	          return vm.errMessage;
	        }
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage = '';
			  vm.errMessage1 = '';
			  vm.flag = true;
			  vm.flag1 = true;
	          return vm.errMessage;
	        }
	    }
    	
    	//validate ngay thang
    	vm.checkErr1 = checkErr1;
    	function checkErr1() {
    		var startDate = $('#startDateDailyIE').val();  
    		var endDate = $('#endDateDailyIEx').val();
			vm.errMessage1 = '';
			var curDate = new Date();
			if ($('#endDateDailyIEx').val()===""){
    			endDate = "31/12/9999";
    		}
	        
			if(($('#stockdailystockId').val()!="")&&(!vm.validator.validate())){
				$("#startDateDailyIE").focus();
				vm.flag1 = false;
				return;
			}
			
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") > curDate){
			   vm.errMessage1 = 'Ngày tạo phải nhỏ hơn hoặc bằng ngày hiện tại';
			   $("#startDateDailyIE").focus();
			   vm.flag1 = false;
			   return vm.errMessage1;
			}
			else if(startDate <= curDate){
				   vm.errMessage1 = '';
				   vm.flag1 = true;
				   return vm.errMessage1;
				}
			if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage1 = 'Ngày tạo phải nhỏ hơn hoặc bằng ngày đến';
	          $("#startDateDailyIE").focus();
			  vm.flag1 = false;
	          return vm.errMessage1;
	        }
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
			  vm.errMessage1 = '';
			  vm.errMessage = '';
			  vm.flag1 = true;
			  vm.flag = true;
	          return vm.errMessage1;
	        }
	    }
		
		//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		});
		//
		
		//Enter
		$("#stockIEId").on("keypress", function (e) {
            if (e.keyCode === 13) {
				$("#doSearch").focus();
            	$("#doSearch").click();
				}
            });
			//End
	}
})();

