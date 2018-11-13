(function() {
	'use strict';
	var controllerId = 'findSerialController';
	
	angular.module('MetronicApp').controller(controllerId, findSerialController);
	
	function findSerialController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,findSerialService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;

		vm.findSerialSearch ={};
		vm.historySearch={};
		vm.History={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.templateStock='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
        vm.templateGoods='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
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
		vm.gridCommonGoods = [ {
			title: "Mã",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
		vm.doSearchFindSerial= doSearchFindSerial;
		function doSearchFindSerial(){
			if(vm.validator.validate()){
				fillDataTableHome([]);
			var grid =vm.findSerialGrid;
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
			}
		}
		
		initFormData();
		function initFormData() {
			fillDataTableHistory([]);
		}
		function fillDataTableHome(data) {
			vm.findSerialGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				toolbar: [
                    {
                        name: "actions",
                        template:
                      	  '<div class="btn-group pull-right margin_top_button margin10">'+
                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in vm.findSerialGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                    '</label>'+                    
                    '</div></div>'
                    }
                    ],
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsSerialServiceRest/stockGoodsSerial/doSearchFindSerial",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.findSerialSearch.page = options.page
								vm.findSerialSearch.pageSize = options.pageSize

								return JSON.stringify(vm.findSerialSearch)
						}
					},					 
					pageSize: 20
				} ,
				noRecords: true,
				columnMenu: false,
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
			        template: dataItem => $("#findSerialGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
					columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Kho",
					field: 'stockName',
					 template: '<a class="#=stockName#" href="javascript:void(0);" ng-click=vm.seeHistory(dataItem)>#=stockName#</a>',
			        width: 250,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hàng",
			        field: 'goodsCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Serial",
					field: 'serial',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'amount',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Hợp đồng",
					 field: 'contractCode',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tình trạng",
					field: 'goodsStateName',
					template :  "# if(goodsStateName == 0){ #" + "#= 'Hỏng' #" + "# } " + "else if (goodsStateName == 1) { # " + "#= 'Bình thường' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Trạng thái",
					field: 'status',
					template :  "# if(status == 2){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Lịch sử",
			        template: dataItem =>
			        	'<div class="text-center #=stockId#""><button type="button"'+
					' class="btn btn-default padding-button box-shadow  #=stockId#"'+
					' ng-click=vm.seeHistory(dataItem)>'+
					'<div class="action-button edit" uib-tooltip="Lịch sử" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'
					+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		
		vm.exportExcelGrid = function(){
			var ds = $("#findSerialGrid").data("kendoGrid").dataSource;
	    	var rows = [{
		        cells: [
		          { value: "STT", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Kho", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Tên hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Serial", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Số lượng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Hợp đồng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Tình trạng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Trạng thái", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}		      
		          ]
		      }];
	    	ds.fetch(function(){
	    		var data = this.data();
	    		for (var i = 0; i < data.length; i++){
			          // push single row for every record
			        	rows.push({
				            cells: [
				              { value: i+1 , textAlign: "center"},
				              { value: data[i].stockName },
				              { value: data[i].goodsCode},
				              { value: data[i].goodsName },
				              { value: data[i].serial },
				              { value: data[i].amount },
				              { value: data[i].contractCode},
				              { value: goodsStateNameSerial(data[i].goodsStateName)},
				              { value: statusSerial(data[i].status)},
				            ]
			          })
			        }
	    		var workbook = new kendo.ooxml.Workbook({
			          sheets: [
			            {
			            	columns: [
			                // Column settings (width)
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			              ],
			              
			              // Title of the sheet
			              title: "Danh sách tra cứu serial",
			              // Rows of the sheet
			              rows: rows
			            }
			          ]
			        });
	    		kendo.saveAs({dataURI: workbook.toDataURL(), fileName: "Danh sách tra cứu serial.xlsx"});
	    	});
		}
		
		function statusSerial(x){
			if(status == 2){ return "Hết hiệu lực";} 
			else if (status == 1) { return "Hiệu lực";}
		}
		
		function goodsStateNameSerial(x){
			 if(goodsStateName == 0){return "Hỏng"; } 
			 else if (goodsStateName == 1) {return "Bình thường";}
		}
		
		
		function fillDataTableHistory(data) {
			vm.historyGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				toolbar: [
                    {
                        name: "actions",
                        template:
                      	  '<div class="btn-group pull-right margin_top_button margin10">'+
                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in caller.historyGrid.columns| filter: caller.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="caller.showHideColumnHistory(column)"> {{column.title}}'+
                    '</label>'+
                    '</div></div>'
                    
                    }
                    ],
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsSerialServiceRest/stockGoodsSerial/doSearchHistory",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                            // vm.appParamSearch.employeeId =
							// Constant.user.srvUser.catEmployeeId;
							if(vm.historySearch.endDate==""){
								vm.historySearch.endDate=null;
							}
							    vm.historySearch.page = options.page
								vm.historySearch.pageSize = options.pageSize

								return JSON.stringify(vm.historySearch)
						}
					},					 
					pageSize: 20
				} ,
				noRecords: true,
				columnMenu: false,
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
			        template: dataItem => $("#historyGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Ngày thực hiện",
					field: 'createdDate',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã yêu cầu",
			        field: 'orderCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã phiếu",
			        field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Loại yêu cầu",
					 field: 'bussinessTypeName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Người thực hiện",
					 field: 'createdByName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'status',
					 template :  "# if(status == 2){ #" + "#= 'Hỏng' #" + "# } " + "else if (status == 1) { # " + "#= 'Bình thường' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}

		vm.cancel= cancel ;
		function cancel(){
			vm.seeHistory= false;
			var d = new Date();
			var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
			vm.historySearch.startDate = datestring;
			vm.historySearch.endDate = null;
		}
		
	
		vm.doSearchHistory= doSearchHistory;
		function doSearchHistory(){
			  /* vm.showDetail = false; */
			var grid =vm.historyGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		  
		vm.seeHistory=seeHistory;
		function seeHistory(dataItem){			
				vm.historySearch=dataItem;
				var d = new Date();
				var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
				vm.historySearch.startDate = datestring;
				vm.historySearch.endDate = null;
			   var teamplateUrl="qlk/findSerial/HistoryPopup.html";
			    var title="Thông tin lịch sử xuất/nhập serial";
			    var windowId="history";
			    CommonService.populatePopupCreate(teamplateUrl,title,vm.historySearch,vm,windowId,false,'85%','85%');
			  }		
		
		vm.goTo = function(menuKey) {
			
			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel : "Tab",
					topic   : "open",
					data    : template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
			
		}
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.findSerialGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.findSerialGrid.showColumn(column);
            } else {
            	vm.findSerialGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.showHideColumnHistory=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.historyGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.historyGrid.showColumn(column);
            } else {
            	vm.historyGrid.hideColumn(column);
            }
        	
        	
        }
		
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		
	}
})();