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
				goodsState:"2",
				typeKpi:"1",
		};
		//vm.stock_goods_kpiSearch.createdUserName = Constant.user.vpsUserToken.fullName;
		if($rootScope.stringtile){
			vm.String=$rootScope.stringtile;
			}
		vm.stock_goods_kpi={};
		vm.stock_goods_kpiSearch.listStockId=[];
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
      '<p class="col-md-6 text-header-auto">Tên</p>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		fillDataTable([]);
		var record=0;
		function fillDataTable(data) {
			// Data KPI theo Thoi gian
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: false,
				columnMenu:false,
				sortable: false,
				resizable: true,	
				scrollable:false,
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
								for(var i in response.data)
								{
									response.data[i].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId
								}
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
					pageSize: 10,
					group: [{
						   field: "text",
						   aggregates: [ { field: "text", aggregate: "count" }]
					        } ]      
				},
				noRecords: true,
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
				}, 
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
					title: "<div style='margin-bottom: 10px;'>STT</div>",
					field:"stt",
			        template: function () {
						return ++record;
					},
					width: '4%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title: "<div style='margin-bottom: 10px;'>Loại hàng hóa</div>",
					field: 'goodsTypeName',
				    width: '9%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:center;"
					}
					}, {
					title: "<div style='margin-bottom: 10px;'>Mã hàng hóa</div>",
			        field: 'goodsCode',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "<div style='margin-bottom: 10px;'>Tên hàng hóa</div>",
			        field: 'goodsName',
			        width: '13%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<div style='margin-bottom: 10px;'>Tình trạng</div>",
			        field: 'goodsStateName',
			        width: '7%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị <br> tính",
					 field: 'goodsUnitName',
					 width: '6%',
				        headerAttributes: {
							style: "text-align:center;font-weight: bold;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
					title: "<div style='margin-bottom: 10px;'>Hợp đồng</div>",
					field: 'contractCode',
			        width: '7%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<div style='margin-bottom: 10px;'>Dự án</div>",
					field: 'projectCode',
			        width: '6%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<div style='margin-bottom: 10px;'>Số lượng</div>",
					field: 'amount',
			        width: '6%',
					format: '{0:n0}{1}',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "<div style='margin-bottom: 10px;'>Serial</div>",
					field: 'serial',
			        width: '5%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thời gian quy <br> định(ngày)",
					field: 'timeQuato',
			        width: '9%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "Thời gian tồn <br> kho(ngày)",
					field: 'timeStorage',
			        width: '9%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "Thời gian vi <br> phạm(ngày)",
					field: 'timeKpi',
			        width: '9%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{ hidden: true,
					field: "text",
				      groupHeaderTemplate: "#= value #"
			    }]
			});
			//Data KPI theo so luong
			vm.gridOptions1 = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: false,
				sortable: false,				
				columnMenu:false,
				scrollable:false,
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
					pageSize: 10,
					group: [{
						   field: "text",
						   aggregates: [ { field: "text", aggregate: "count" },
							   { field: "amountRemain", aggregate: "sum" },
					            { field: "amountQuato", aggregate: "sum" }]
					        } ]     
				},
				noRecords: true,
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
				}, 
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
					title: "<div style='margin-bottom: 10px;'>STT</div>",
					field:"stt",
			        template: function () {
						return ++record;
					},
					width: 40,
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title: "<div style='margin-bottom: 10px;'>Loại hàng hóa</div>",
					field: 'goodsTypeName',
			        width: 120,
			         headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, {
					title: "<div style='margin-bottom: 10px;'>Mã hàng hóa</div>",
			        field: 'goodsCode',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "<div style='margin-bottom: 10px;'>Tên hàng hóa</div>",
			        field: 'goodsName',
			        width: 190,
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<div style='margin-bottom: 10px;'>Trạng thái</div>",
					 field: 'goodsStateName',
					  width: 70,
				        headerAttributes: {
							style: "text-align:center;font-weight: bold;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
					title: "<div style='margin-bottom: 10px;'>Đơn vị tính</div>",
					 field: 'goodsUnitName',
					 width: 80,
				        headerAttributes: {
							style: "text-align:center;font-weight: bold;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Số lượng <br> tồn kho",
						field: 'amountRemain',
				        width: 100,
						format: '{0:n0}{1}',
				        headerAttributes: {
							style: "text-align:center;font-weight: bold;"
						},
						attributes: {
							style: "text-align:right;"
						},
			            groupFooterTemplate:"<div style='float: right'>{{ dataItem.amountRemain.sum.toLocaleString()}}"
					},{
						title: "Số lượng <br> định mức",
						field: 'amountQuato',
				        width: 100,
						format: '{0:n0}{1}',
				        headerAttributes: {
							style: "text-align:center;font-weight: bold;"
						},
						attributes: {
							style: "text-align:right;"
						},
						 groupFooterTemplate:"<div style='float: right'>{{ dataItem.amountQuato.sum.toLocaleString()}}"
					},{
						title: "Số lượng <br> lệch",
						field: 'amountKpi',
				        width: 100,
						format: '{0:n0}{1}',
				        headerAttributes: {
							style: "text-align:center;font-weight: bold;"
						},
						attributes: {
							style: "text-align:right;"
						}
					},{ hidden: true,
						field: "text",
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
		//DoSearch KPI tồn kho
		vm.doSearch= doSearch;
		function doSearch(){
			trimSpace();
			if($("#other").val().length>501){
				$("#other").focus();
				vm.mess="Trường Thông tin khác không được vượt quá 501 ký tự";
				return;
			}
			var grid =vm.stock_goods_kpiGrid;	
			var grid1 =vm.stock_goods_kpiGrid1;	
			fillDataTable([]);
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10,
					group: [{
						   field: "text",
						   aggregates: [ { field: "text", aggregate: "count" }]
					        } ]   
				});
			}
			if(grid1){
				grid1.dataSource.query({
					page: 1,
					pageSize: 10,
					group: [{
						   field: "text",
						   aggregates: [ { field: "text", aggregate: "count" },
							   { field: "amountRemain", aggregate: "sum" },
					            { field: "amountQuato", aggregate: "sum" }]
					        } ]   
				});
			}
		}
		//End
		vm.canceldoSearch=function(){
			 $('#listGoodsTypeR').data("kendoMultiSelect").value([]);
             vm.stock_goods_kpiSearch.listGoodsType=null;
             vm.stock_goods_kpiSearch.goodsState="2";
		}
		
		
		$scope.$watch("vm.stock_goods_kpiSearch.typeKpi",function() {
		        if(vm.stock_goods_kpiSearch.typeKpi==="2") {
		            $scope.dataAvailable2 = true;
		             $scope.dataAvailable1 = false;
		             document.getElementById("other").disabled=true;
		             vm.stock_goods_kpiSearch.keySearch=null;
		             vm.stock_goods_kpiSearch.keySearch2=null;
		             vm.stock_goods_kpiSearch.name=null;
		             vm.canceldoSearch();
		             $("#stock_goods_kpiGrid1").data("kendoGrid").dataSource.data([]);
		        }else{
		           $scope.dataAvailable1 = true;
		            $scope.dataAvailable2 = false;
		            document.getElementById("other").disabled=false;
		            vm.stock_goods_kpiSearch.keySearch2=null;
		            vm.stock_goods_kpiSearch.name=null;
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
				 $('#listGoodsTypeR').data("kendoMultiSelect").focus();
			}
			if(id==="clearGoods"){
				 vm.stock_goods_kpiSearch.name=null;
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
					pageSize: 10,
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
            	if(vm.stock_goods_kpiSearch.typeKpi==="2"){
             	var obj={};
             	obj.listStockId=vm.stock_goods_kpiSearch.listStockId;
             	obj.goodsState=vm.stock_goods_kpiSearch.goodsState;
             	obj.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
             	obj.name=vm.stock_goods_kpiSearch.name;
             	obj.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
             	obj.reportType="PDF";
             	obj.reportName="BaocaoKPISoluong";
             	 var ds1=$("#stock_goods_kpiGrid1").data("kendoGrid").dataSource.data();
            	 if (ds1.length === 0&&obj.listStockId.length===0&&obj.goodsState==="2"&&obj.listGoodsType==null&&obj.name==null&&obj.typeKpi==="2"){
   				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
   				 return ;
   			 } 
             	CommonService.exportReport(obj).then(
    					function(data1) {
						if(data1.error){
						toastr.error(data1.error);
						return;
						}
    					var binarydata= new Blob([data1],{ type:'application/pdf'});
    			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo KPI theo số lượng" + '.pdf'});
    				}, function(errResponse) {
    					toastr.error("Lỗi không export PDF được!");
    				});
            	}
            	else{
            		var obj1={};
                 	obj1.listStockId=vm.stock_goods_kpiSearch.listStockId;
                 	obj1.goodsState=vm.stock_goods_kpiSearch.goodsState;
                 	obj1.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
                 	obj1.name=vm.stock_goods_kpiSearch.name;
                 	obj1.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
                 	obj1.keySearch=vm.stock_goods_kpiSearch.keySearch;
                 	obj1.reportType="PDF";
                 	obj1.reportName="BaocaoKPIThoigian";
                 	 var ds1=$("#stock_goods_kpiGrid").data("kendoGrid").dataSource.data();
               	 if (ds1.length === 0&&obj1.listStockId.length===0&&obj1.goodsState==="2"&&obj1.listGoodsType==null&&obj1.name==null&&obj1.typeKpi==="1"&&obj1.keySearch===""){
      				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
      				 return ;
      			 } 
                 	CommonService.exportReport(obj1).then(
        					function(data) {
							if(data.error){
						toastr.error(data.error);
						return;
						}
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
            		obj.listStockId=vm.stock_goods_kpiSearch.listStockId;
                 	obj.goodsState=vm.stock_goods_kpiSearch.goodsState;
                 	obj.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
                 	obj.name=vm.stock_goods_kpiSearch.name;
                 	obj.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
             	obj.reportType="EXCEL";
             	obj.reportName="BaocaoKPISoluong";
             	var ds1=$("#stock_goods_kpiGrid1").data("kendoGrid").dataSource.data();
           	 if (ds1.length === 0&&obj.listStockId.length===0&&obj.goodsState==="2"&&obj.listGoodsType==null&&obj.name==null&&obj.typeKpi==="2"){
  				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
  				 return ;
  			 }
             	CommonService.exportReport(obj).then(
    					function(data) {
						if(data.error){
						toastr.error(data.error);
						return;
						}
    					var binarydata= new Blob([data],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
    			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo KPI theo số lượng" + '.xlsx'});
    				}, function(errResponse) {
    					toastr.error("Lỗi không export Excel được!");
    				});
            	}
            	else{
            		var obj1={};
            		obj1.listStockId=vm.stock_goods_kpiSearch.listStockId;
                 	obj1.goodsState=vm.stock_goods_kpiSearch.goodsState;
                 	obj1.listGoodsType=vm.stock_goods_kpiSearch.listGoodsType;
                 	obj1.name=vm.stock_goods_kpiSearch.name;
                 	obj1.typeKpi=vm.stock_goods_kpiSearch.typeKpi;
                 	obj1.keySearch=vm.stock_goods_kpiSearch.keySearch;
                 	obj1.reportType="EXCEL";
                 	obj1.reportName="BaocaoKPIThoigian";
                 	var ds1=$("#stock_goods_kpiGrid").data("kendoGrid").dataSource.data();
               	 	 if (ds1.length === 0&&obj1.listStockId.length===0&&obj1.goodsState==="2"&&obj1.listGoodsType==null&&obj1.name==null&&obj1.typeKpi==="1"&&obj1.keySearch===""){
      				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
      				 return ;
      			 } 
                 	CommonService.exportReport(obj1).then(
        					function(data1) {
								if(data1.error){
								toastr.error(data1.error);
								return;
							}
        					var binarydata= new Blob([data1],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
        			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo KPI theo thời gian" + '.xlsx'});
        				}, function(errResponse) {
        					toastr.error("Lỗi không export Excel được!");
        				});
            	}
             }
            
            //AutocompleteGoods
            vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã Hàng Hóa</p>' +
            '<p class="col-md-6 text-header-auto">Tên Hàng Hóa</p>' +
            	'</div>';
  		vm.goodsOptions = {
  	            dataTextField: "name",
  	            select: function(e) {
  	                var dataItem = this.dataItem(e.item.index());
  	              vm.stock_goods_kpiSearch.code = dataItem.code; // thành id
  	              vm.stock_goods_kpiSearch.name = dataItem.name;//thành name
  	            },
  	            pageSize: 10,
  	            dataSource: {
  	                serverFiltering: true,
  	                transport: {
  	                    read: function(options) {
  	                        return Restangular.all("orderGoodsServiceRest/orderGoods/" + 'getForAutoComplete').post({name:vm.stock_goods_kpiSearch.name,pageSize:vm.goodsOptions.pageSize}).then(function(response){
  	                            options.success(response);
  	                        }).catch(function (err) {
  	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
  	                        });
  	                    }
  	                }
  	            },
  	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.code #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
  	            change: function(e) {
  	                if (e.sender.value() === '') {
  	                	vm.stock_goods_kpiSearch.code = null; // thành id
  	                	vm.stock_goods_kpiSearch.name = null;//thành name
  	                }
  	            },
  	            ignoreCase: false
  	        };
  		if(vm.stock_goods_kpiSearch.keySearch==null){
  			vm.stock_goods_kpiSearch.keySearch="";
  		}
  		//End
		
		
		//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		});
		//
		
		//OnkeyDown 
    	$(document).on("keydown", function (e) {
			if($(':focus').size()===0){
					$( "#doSearchKPI" ).click(function( event ) {
							event.stopPropagation();
							});
					}
            if (e.keyCode === 13) {
            	$("#doSearchKPI").click();
            }
            });
    	//End
		//Enter
		$("#stock_goods_kpiId").on("keypress", function (e) {
			if (e.keyCode === 13) {
				$("#doSearchKPI").focus();
				$("#doSearchKPI").click();
				}
		});
		//End
	}
})();
