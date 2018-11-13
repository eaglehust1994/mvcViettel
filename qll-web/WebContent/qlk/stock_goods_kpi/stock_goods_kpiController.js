(function() {
	'use strict';
	var controllerId = 'stock_goods_kpiController';
	
	angular.module('MetronicApp').controller(controllerId, stock_goods_kpiController);
	
	function stock_goods_kpiController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,stockGoodsKpiServiceRest,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant,$http) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stock_goods_kpiSearch={
				goodsState:2,
				typeKpi:1,
		};
		vm.stock_goods_kpi={};
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
		function fillDataTable(data) {
			// Data KPI theo Thoi gian
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: false,
				sortable:false,
				columnMenu:false,
				resizable: true,	
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsKpiServiceRest/doSearchKpiTime",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stock_goods_kpiSearch.page = options.page
								vm.stock_goods_kpiSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stock_goods_kpiSearch)

						}
					},					 
					pageSize: 20,
					group: [{
						   field: "stockName",
						   aggregates: [ { field: "stockName", aggregate: "count" }]
					        } ]      
				},
				selectable: "multiple row",
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
			        template: dataItem => $("#stock_goods_kpiGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title: "Loại hàng hóa",
					field: 'goodsTypeName',
				    width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					}
					}, {
					title: "Mã hàng hóa",
			        field: 'goodsCode',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng hóa",
			        field: 'goodsName',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tình trạng",
			        field: 'goodsStateName',
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị <br> tính",
					 field: 'goodsUnitName',
					 width: 70,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
					title: "Hợp đồng",
					field: 'contractCode',
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Dự án",
					field: 'projectCode',
			        width: 60,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
					field: 'amount',
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Serial",
					field: 'serial',
			        width: 60,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thời gian quy <br> định(ngày)",
					field: 'timeQuato',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thời gian tồn <br> kho(ngày)",
					field: 'timeStorage',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thời gian vi <br> phạm(ngày)",
					field: 'timeKpi',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{ hidden: true,
					field: "stockName",
				      groupHeaderTemplate: "#= value #"
			    }]
			});
			//Data KPI theo so luong
			vm.gridOptions1 = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				sortable:false,
				columnMenu:false,
				/*toolbar: [
                    {
                        name: "actions",
                        template: 
                        	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                              '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                              '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                              '<label ng-repeat="column in vm.stock_goods_kpiGrid1.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn1(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                    }
                    ],*/
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsKpiServiceRest/doSearchKpiAmount",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stock_goods_kpiSearch.page = options.page
								vm.stock_goods_kpiSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stock_goods_kpiSearch)

						}
					},					 
					pageSize: 20,
					group: [{
						   field: "stockName",
						   aggregates: [ { field: "stockName", aggregate: "count" },
							   { field: "amountRemain", aggregate: "sum" },
					            { field: "amountQuato", aggregate: "sum" },
					            {field: "amountKpi", aggregate: "sum" }]
					        } ]     
				},
				selectable: "multiple row",
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
			        template: dataItem => $("#stock_goods_kpiGrid1").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title: "Loại hàng hóa",
					field: 'goodsTypeName',
			        width: 120,
			         headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hàng hóa",
			        field: 'goodsCode',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng hóa",
			        field: 'goodsName',
			        width: 190,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'goodsStateName',
					  width: 70,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
					title: "Đơn vị tính",
					 field: 'goodsUnitName',
					 width: 70,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Số lượng tồn kho",
						field: 'amountRemain',
				        width: 120,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
			            groupFooterTemplate:"{{ dataItem.amountRemain.sum}}"
					},{
						title: "Số lượng định mức",
						field: 'amountQuato',
				        width: 120,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
						 groupFooterTemplate:"{{ dataItem.amountQuato.sum}}"
					},{
						title: "Số lượng lệch",
						field: 'amountKpi',
				        width: 120,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
						 groupFooterTemplate:"{{ dataItem.amountKpi.sum}}"
					},{ hidden: true,
						field: "stockName",
					      groupHeaderTemplate: "#= value #"
				    }]
			});
		}
	
		function refreshGrid(d) {
			var grid = vm.stock_goods_kpiGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
				vm.showDetail = false;
		}
		// End ExportExcel///
		vm.doSearch= doSearch;
		function doSearch(){
			var grid =vm.stock_goods_kpiGrid;	
			var grid1 =vm.stock_goods_kpiGrid1;	
			fillDataTable([]);
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20,
					group: [{
						   field: "stockName",
						   aggregates: [ { field: "stockName", aggregate: "count" }]
					        } ]   
				});
			}
			if(grid1){
				grid1.dataSource.query({
					page: 1,
					pageSize: 20,
					group: [{
						   field: "stockName",
						   aggregates: [ { field: "stockName", aggregate: "count" },
							   { field: "amountRemain", aggregate: "sum" },
					            { field: "amountQuato", aggregate: "sum" },
					            {field: "amountKpi", aggregate: "sum" }]
					        } ]   
				});
			}
		}
		vm.canceldoSearch=function(){
			 $('#listGoodsTypeR').data("kendoMultiSelect").value([]);
             vm.stock_goods_kpiSearch.listGoodsType=null;
             vm.stock_goods_kpiSearch.goodsState="2";
		}
		
		
		$scope.$watch("vm.stock_goods_kpiSearch.typeKpi",function() {
		        if(vm.stock_goods_kpiSearch.typeKpi==2) {
		            $scope.dataAvailable2 = true;
		             $scope.dataAvailable1 = false;
		             document.getElementById("other").disabled=true;
		             vm.stock_goods_kpiSearch.keySearch=null;
		             vm.stock_goods_kpiSearch.keySearch2=null;
		             vm.stock_goods_kpiSearch.keySearchHH=null;
		             vm.canceldoSearch();
		             $("#stock_goods_kpiGrid1").data("kendoGrid").dataSource.data([]);
		        }else{
		           $scope.dataAvailable1 = true;
		            $scope.dataAvailable2 = false;
		            document.getElementById("other").disabled=false;
		            vm.stock_goods_kpiSearch.keySearch2=null;
		             vm.stock_goods_kpiSearch.keySearchHH=null;
		            vm.canceldoSearch();
		            $("#stock_goods_kpiGrid").data("kendoGrid").dataSource.data([]);
		        }
		    });
		
		vm.cancelstockkpi = function(id)
		{
			if(id==="listGoodsTypeR")
			{
				 $('#listGoodsTypeR').data("kendoMultiSelect").value([]);
				 vm.stock_goods_kpiSearch.listGoodsType=null;
			}
			if(id==="clearGoods"){
				 vm.stock_goods_kpiSearch.goodsName="";
			}
		}
		// multiselect
	
		
			$("#listGoodsTypeR").kendoMultiSelect({
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
							    vm.stock_goods_kpiSearch.page = options.page
								vm.stock_goods_kpiSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stock_goods_kpiSearch)

						}
					},					 
					pageSize: 20,
				},
				 dataTextField: "name",
			     dataValueField: "goodsTypeId",
			});
		
		
		//
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.stock_goods_kpiGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.stock_goods_kpiGrid.showColumn(column);
            } else {
            	vm.stock_goods_kpiGrid.hideColumn(column);
            }
        	
        	
        }
		vm.showHideColumn1=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.stock_goods_kpiGrid1.hideColumn(column);
            } else if (column.hidden) {
            	vm.stock_goods_kpiGrid1.showColumn(column);
            } else {
            	vm.stock_goods_kpiGrid1.hideColumn(column);
            }
        	
        	
        }
		/*
		** Filter các cột của select 
		*/	
		
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
            
            vm.exportpdfKpi= function(){
            	if(vm.stock_goods_kpiSearch.typeKpi==2){
             	var obj={};
             	obj.stockId=vm.stock_goods_kpiSearch.stockId;
             	obj.goodsState=vm.stock_goods_kpiSearch.goodsState;
             	obj.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
             	obj.goodsName=vm.stock_goods_kpiSearch.goodsName;
             	obj.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
             	obj.reportType="PDF";
             	obj.reportName="BaocaoKPISoluong";
             	CommonService.exportReport(obj).then(
    					function(data1) {
    					var binarydata= new Blob([data1],{ type:'application/pdf'});
    			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo KPI theo số lượng" + '.pdf'});
    				}, function(errResponse) {
    					toastr.error("Lỗi không export PDF được!");
    				});
            	}
            	else{
            		var obj1={};
                 	obj1.stockId=vm.stock_goods_kpiSearch.stockId;
                 	obj1.goodsState=vm.stock_goods_kpiSearch.goodsState;
                 	obj1.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
                 	obj1.goodsName=vm.stock_goods_kpiSearch.goodsName;
                 	obj1.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
                 	obj1.keySearch=vm.stock_goods_kpiSearch.keySearch;
                 	obj1.reportType="PDF";
                 	obj1.reportName="BaocaoKPIThoigian";
                 	CommonService.exportReport(obj1).then(
        					function(data) {
        					var binarydata= new Blob([data],{ type:'application/pdf'});
        			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo KPI theo thời gian" + '.pdf'});
        				}, function(errResponse) {
        					toastr.error("Lỗi không export PDF được!");
        				});
            	}
            }
             //excelRasper
            vm.exportexcelkpi= function(){
            	if(vm.stock_goods_kpiSearch.typeKpi==2){
            		var obj={};
                 	obj.stockId=vm.stock_goods_kpiSearch.stockId;
                 	obj.goodsState=vm.stock_goods_kpiSearch.goodsState;
                 	obj.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
                 	obj.goodsName=vm.stock_goods_kpiSearch.goodsName;
                 	obj.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
             	obj.reportType="EXCEL";
             	obj.reportName="BaocaoKPISoluong";
             	CommonService.exportReport(obj).then(
    					function(data) {
    					var binarydata= new Blob([data],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
    			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo KPI theo số lượng" + '.xlsx'});
    				}, function(errResponse) {
    					toastr.error("Lỗi không export Excel được!");
    				});
            	}
            	else{
            		var obj1={};
                 	obj1.stockId=vm.stock_goods_kpiSearch.stockId;
                 	obj1.goodsState=vm.stock_goods_kpiSearch.goodsState;
                 	obj1.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
                 	obj1.goodsName=vm.stock_goods_kpiSearch.goodsName;
                 	obj1.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
                 	obj1.keySearch=vm.stock_goods_kpiSearch.keySearch;
                 	obj1.reportType="EXCEL";
                 	obj1.reportName="BaocaoKPIThoigian";
                 	CommonService.exportReport(obj1).then(
        					function(data1) {
        					var binarydata= new Blob([data1],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
        			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo KPI theo thời gian" + '.xlsx'});
        				}, function(errResponse) {
        					toastr.error("Lỗi không export Excel được!");
        				});
            	}
             }
            
            //
	}
})();
