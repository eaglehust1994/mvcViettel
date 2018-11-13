(function() {
	'use strict';
	var controllerId = 'stock_transController';
	
	angular.module('MetronicApp').controller(controllerId, stock_transController);
	
	function stock_transController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,stockTransServiceRest,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stock_transSearch={
				status:1
		};
		vm.stock_trans={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
      '<span>Mã</span>' +
      '<span>Tên</span>' +
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
		fillDataTable([]);
		function initFormData() {
			fillDataTable([]);
			var obj={};
			obj.stockId="YCNK";
			obj.startDate="17/07/2017";
			obj.endDate="20/09/2017";
			CommonService.genCode(obj).then(
				function(d) {
					var String=d;
					console.log(String);
				});
		
		}
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: false,
				resizable: true,
				sortable:false,
				columnMenu:false,
				
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
							url: Constant.BASE_SERVICE_URL + "stockTransServiceRest/stockTrans/doSearchExport",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;

							if (vm.stock_transSearch.endDate == ""){
								vm.stock_transSearch.endDate = null;
							}
							    vm.stock_transSearch.page = options.page
								vm.stock_transSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stock_transSearch)
						}
					},					 
					pageSize: 20,
					group:[ 
						{ field: "stockName"},
					]
					
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
			        template: dataItem => $("#stock_transGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã yêu cầu xuất",
					field: 'orderCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã phiếu xuất",
			        field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Người xuất",
			        field: 'realIeUserName',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Ngày xuất",
					 field: 'realIeTransDate',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Đơn vị nhận",
					 field: 'deptReceiveName',
			        width: 250,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Kho nhận",
					 field: 'stockReceiveCode',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					}
				},{hidden:true,
					 field:'stockName',
					 groupHeaderTemplate:"#=value#"
				}]
			});
		}
		function refreshGrid(d) {
			var grid = vm.stock_transGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
				vm.stock_trans={};
				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		
		vm.doSearch = doSearch;
		function doSearch(){
				var grid =vm.stock_transGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20,
					group:[ 
						{ field: "stockName"}
					]
				});
			}
		}
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.stock_transGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.stock_transGrid.showColumn(column);
            } else {
            	vm.stock_transGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		** Filter các cột của select 
		*/	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			doSearch();
		}
		vm.cancelstocktrans = function(id)
		{
			if(id==="date"){
				$("#startDate11").val('');
				$("#endDate11").val('');
				vm.stock_transSearch.endDate=null;
			}
		}
		$scope.$watch(function(){
			var date= new Date();
			var dd=date.getDate();
			var MM=date.getMonth();
			var yyyy=date.getFullYear();
			if(vm.stock_transSearch.startDate==null)
				{
				$("#startDate11").kendoDatePicker({
				value:dd+"/"+MM+"/"+yyyy,
				 format: "dd/MM/yyyy",
				});
				vm.stock_transSearch.startDate=dd+"/"+MM+"/"+yyyy;
				}
			else{
				$("#startDate11").kendoDatePicker({
					 format: "dd/MM/yyyy",
					});
			}
			$("#endDate11").kendoDatePicker({
				 format: "dd/MM/yyyy",
				});
			});
		
		vm.exportpdf= function(){
         	var obj={};
         	obj.stockId=vm.stock_transSearch.stockId;
         	obj.startDate=vm.stock_transSearch.startDate;
         	obj.endDate=vm.stock_transSearch.endDate;
         	obj.reportType="PDF";
         	obj.reportName="BaoCaoPhieuXuatKhoDangDiDuong";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/pdf'});
			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo phiếu xuất kho đang đi đường" + '.pdf'});
				}, function(errResponse) {
					toastr.error("Lỗi không export PDF được!");
				});
         }
		
		vm.exportexcel= function(){
         	var obj={};
         	obj.stockId=vm.stock_transSearch.stockId;
         	obj.startDate=vm.stock_transSearch.startDate;
         	obj.endDate=vm.stock_transSearch.endDate;
         	obj.reportType="EXCEL";
         	obj.reportName="BaoCaoPhieuXuatKhoDangDiDuong";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo phiếu xuất kho đang đi đường" + '.xlsx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export Excel được!");
				});
         }
	}
})();
