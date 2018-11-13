(function() {
	'use strict';
	var controllerId = 'findSerialController';
	
	angular.module('MetronicApp').controller(controllerId, findSerialController);
	
	function findSerialController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,findSerialService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.oldSearch={};
		vm.findSerialSearch ={};
		vm.historySearch={};
		vm.History={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
      '<p class="col-md-6 text-header-auto">Tên</p>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};	
		
//		Tìm kiếm serial
		vm.doSearchFindSerial= doSearchFindSerial;
		function doSearchFindSerial(){
			trimSpace();
			if (!vm.validator.validate()||($("#listSerial").val() == "")){
				$("#listSerial").focus();
				return;
			} 
			else{
				var str = $("#listSerial").val();
				str = str.replace(" ","");
				vm.findSerialSearch.serialString = str;
				fillDataTableHome([]);
				var grid =vm.findSerialGrid;
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10
					});
				}
			}
			
		}
		
//		Khởi tạo dữ liệu cho form
		initFormData();
		function initFormData() {
			//fillDataTableHome([]);
			fillDataTableHistory([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
//		Lấy dữ liệu từ bảng Stock_Goods_Serial
		var record=0;
		function fillDataTableHome(data) {
			vm.findSerialGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,
				resizable: false,
				scrollable: false,
				toolbar: [
                    {
                        name: "actions",
                        template:
                      	  '<div class="btn-group pull-right margin_top_button margin10">'+
                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                      	'<i id="xuatExcel" class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
	                    '<div uib-tooltip="Ẩn/hiện cột" class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
	                    '<label ng-repeat="column in vm.findSerialGrid.columns| filter: vm.gridColumnShowHideFilter">'+
	                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
	                    '</label>'+                    
	                    '</div></div>'
                    }
                    ],
					dataBound: function (e) {
						var grid = vm.findSerialGrid;
						grid.tbody.find("tr").dblclick(function (e) {
							var dataItem = grid.dataItem(this);
							vm.showNoteDetail(dataItem)
						});
					},
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								$("#findSerialCount").text(""+response.total);
							 	vm.count = response.total;
								return response.total; 
							},
							data: function (response) {
								return response.data; 
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
							    vm.findSerialSearch.page = options.page
								vm.findSerialSearch.pageSize = options.pageSize
								vm.oldSearch=angular.copy(vm.findSerialSearch);
								return JSON.stringify(vm.findSerialSearch)
						}
					},					 
					pageSize: 10
				} ,
				noRecords: true,
				columnMenu: false,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				dataBound: function () {
					/*var GridDestination = $("#findSerialGrid").data("kendoGrid");                
					 if (GridDestination.dataSource.total() < 10) {
			                GridDestination.pager.element.hide();
			            }
			            else {
			                GridDestination.pager.element.show();
			            }*/
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
					title: "TT",
					field:"stt",
			        template: function () {
						return ++record;
					},
					width: '5%',
					columnMenu: false,
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Kho",
					field: 'stockName',
					template: '<a class="#=stockName#" href="javascript:void(0);" ng-click=vm.seeHistory(dataItem)>#=stockName#</a>',
			        width: '15%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Mã hàng",
			        field: 'goodsCode',
			        width: '15%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: '18%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},  {
					title: "Serial",
					field: 'serial',
			        width: '10%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},  {
					title: "Hợp đồng",
					 field: 'contractCode',
			        width: '10%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},  {
					title: "Tình trạng",
					field: 'goodsState',
					template :  "# if(goodsState === '0'){ #" + "#= 'Hỏng' #" + "# } " + "else if (goodsState === '1') { # " + "#= 'Bình thường' #"+ "#} #",
			        width: '10%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},  {
					title: "Trạng thái",
					field: 'status',
					template :  "# if(status === '1'){ #" + "#= 'Trong kho' #" + "# } " 
								+ "else if (status === '2') { # " + "#= 'Đã bán' #"+ "#} #",
					width: '10%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Lịch sử",
			        template: dataItem =>
			        	'<div class="text-center #=stockId#""><button uib-tooltip="Xem lịch sử" tooltip-placement="center" ng-click=vm.seeHistory(dataItem)'+
					' class="fa btn historySerial" aria-hidden="true"></button> '
					+'</div>',
			        width: '8%',
					headerAttributes: {style: "text-align:center;font-weight: bold;"},
			        field:"stt"
				}
				,]
			});
		}
		
		vm.exportExcelGrid = function(){
			var ds = $("#findSerialGrid").data("kendoGrid").dataSource;
			var ds1 = ds.data();
			if (ds1.length == 0){
				toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
			}
			else{
					vm.oldSearch.page = null;
					vm.oldSearch.pageSize = null;
					findSerialService.getForExportGrid(vm.oldSearch).then(function(d) {
						CommonService.exportFile(vm.findSerialGrid,d.data,vm.listRemove,vm.listConvert,"Tra cứu serial");
					});
				}
		}
		
		vm.listRemove=[{
			title: "Lịch sử",
		}];
		
		vm.listConvert=[{
				field:"goodsState",
				data:{
					0:'Hỏng',
					1:'Bình thường',
				}
			},{
				field:"status",
				data:{
					1:'Trong kho',
					2:'Đã bán',
				}
			}];
//		Xuất file excel
		/* vm.exportExcelGrid = function(){
			if (!vm.validator.validate()||($("#listSerial").val() == "")){
				$("#listSerial").focus();
				return;
			} 
			var ds = $("#findSerialGrid").data("kendoGrid").dataSource;
			var totalCount = ds.total();
			var pageSizeCount = ds.pageSize();
			ds.pageSize(parseInt(totalCount));
			var ds1 = ds.data();
			 if (ds1.length == 0){
				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
			 }else {
		    	var rows = [{
			        cells: [
			          { value: "TT", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Kho", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Mã hàng", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Tên hàng", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Serial", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Số lượng", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Hợp đồng", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Tình trạng", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			          { value: "Trạng thái", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},		      
			          { value: "Lịch sử", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}		      
			          ]
			      }];
		    	ds.fetch(function(){
		    		var data = this.data();
		    		for (var i = 0; i < data.length; i++){
				          // push single row for every record
				        	rows.push({
					            cells: [
									{ value: i+1 , textAlign: "center", background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ value: data[i].stockName, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
									{ value: data[i].goodsCode, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ value: data[i].goodsName , background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ value: data[i].serial, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
									{ value: data[i].amount, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
									{ value: data[i].contractCode, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ value: goodsStateNameSerial(data[i].goodsState), background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ value: statusSerial(data[i].status), background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}
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
		    		ds.pageSize(parseInt(pageSizeCount));
		    	});
			 }
		}
		
		function statusSerial(x){
			if(x == 1){ return "Trong kho";} 
			else if (x == 3) { return "Đã bán";}
			
		}
		
		function goodsStateNameSerial(x){
			 if(x === "0"){return "Hỏng"; } 
			 else if (x === "1") {return "Bình thường";}
		} */
		
//		Xem lịch sử của từng mã Serial

		function fillDataTableHistory(data) {
			vm.historyGridOptions = kendoConfig.getGridOptions({
				autoBind: false,
				resizable: false,
				sortable: false,
				scrollable:false,
				toolbar: [
                    {
                        name: "actions",
                        template:
                      	  '<div class="btn-group pull-right margin_top_button margin10">'+
                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
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
								$("#findSerialCount2").text(""+response.total);
							 	vm.count2 = response.total;
								return response.total;
							},
							data: function (response) {
								return response.data; 
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
							if(vm.historySearch.endDate==""){
								vm.historySearch.endDate=null;
							}
							    vm.historySearch.page = options.page
								vm.historySearch.pageSize = options.pageSize
								return JSON.stringify(vm.historySearch)
						}
					},					 
					pageSize: 10
				} ,
				noRecords: true,
				columnMenu: false,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				dataBound: function () {
					/*var GridDestination = $("#historyGrid").data("kendoGrid");                
					 if (GridDestination.dataSource.total() < 10) {
			                GridDestination.pager.element.hide();
			            }
			            else {
			                GridDestination.pager.element.show();
			            }*/
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
					title: "TT",
					field:"stt",
			        template: function () {
						return ++record;
					},
					width: '5%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Ngày thực hiện",
					field: 'realIeTransDate',
			        width: '13%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}, {
					title: "Mã yêu cầu",
			        field: 'orderCode',
			        width: '18%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Mã phiếu",
			        field: 'code',
			        width: '18%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},  {
					title: "Loại yêu cầu",
					 field: 'bussinessTypeName',
			        width: '16%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},  {
					title: "Người thực hiện",
					 field: 'createdByName',
			        width:'16%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},{
					title: "Trạng thái",
					field: 'status',
					template :  "# if(status == 2){ #" + "#= 'Đã nhập/xuất' #" + "# } " + "else if (status == 1) { # " + "#= 'Chưa nhập/xuất' #"+ "#} "
								+ "else if (status == 3) { # " + "#= 'Đã hủy' #"+ "#} #",
			        width: '10%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}]
			});
		}

		vm.cancel= cancel ;
		function cancel(){
			
		}
		
//		Tìm kiếm lịch sử
		vm.flag=true;
		vm.flag1=true;
		vm.doSearchHistory= doSearchHistory;
		function doSearchHistory(){
			if ((!vm.validator.validate())&&((vm.historySearch.startDate =="")||(vm.historySearch.startDate ==null))){
				$("#startDate").focus();
				return;
			}
			else if ((vm.historySearch.startDate !="")&&(!vm.flag1)){
				$("#startDate").focus();
				return;
			}
			else if ((vm.historySearch.startDate !="")&&(!vm.flag)){
				$("#endDate").focus();
				return;
			}
			else{
				var grid =vm.historyGrid;	
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10
					});
				}
			}	
		}
		  	
		vm.seeHistory=seeHistory;
		function seeHistory(dataItem){			
				vm.historySearch=dataItem;
				//		Set ngày mặc định lùi 1 tháng
				vm.historySearch.startDate=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy");
				vm.historySearch.endDate=null;
			    var teamplateUrl="wms/findSerial/HistoryPopup.html";
			    var title="Thông tin lịch sử xuất/nhập serial";
			    var windowId="history";
			    CommonService.populatePopupCreate(teamplateUrl,title,vm.historySearch,vm,windowId,false,'85%','75%');
			    vm.errMessage = '';
				vm.errMessage1 = '';
				vm.historyGrid.dataSource.total=0;
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
		
//		Hiện/Ẩn các cột
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
		
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		
//      Xử lý nút xoá trên UI 
		vm.cancelStock = function(id) {
			if(id==="clearGoods"){
				 vm.findSerialSearch.name=null;
			}
		}
    		
		//validate ngay thang
    	vm.checkErr = checkErr;
    	function checkErr() {
    		var startDate = $('#startDate').val();
    		var endDate = $('#endDate').val();
    		if (kendo.parseDate(endDate,"dd/MM/yyyy")==null){
    			endDate = "31/12/9999";
    		}
			vm.errMessage = '';
			var d = new Date();
			var curDate = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
	        
			if (!vm.validator.validate()){
				$("#endDate").focus();
				vm.flag=false;
				return;
			}
	        if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage = 'Ngày đến phải lớn hơn hoặc bằng ngày tạo';
	          $("#endDate").focus();
			  vm.flag=false;
	          return vm.errMessage;
	        }
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage = '';  
			  vm.flag=true;
			  vm.flag1=true;
	          return vm.errMessage;
	        }
	    }
    	
    	//validate ngay thang
    	vm.checkErr1 = checkErr1;
    	function checkErr1() {
    		var startDate = $('#startDate').val(); 
			var endDate = $('#endDate').val();			
			vm.errMessage1 = '';
			var curDate = new Date();
	        if (kendo.parseDate(endDate,"dd/MM/yyyy")==null){
    			endDate = "31/12/9999";
    		}
			
			if (!vm.validator.validate()){
				$("#startDate").focus();
				vm.flag1=false;
				return;
			}
			
	        if(kendo.parseDate(startDate,"dd/MM/yyyy") > curDate){
			   vm.errMessage1 = 'Ngày tạo phải nhỏ hơn hoặc bằng ngày hiện tại';
			   $("#startDate").focus();
			   vm.flag1=false;
			   return vm.errMessage1;
			}
			else if(startDate <= curDate){
			   vm.errMessage1 = '';
			   vm.flag1=true;
			   return vm.errMessage1;
			}
			if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
	          $("#startDate").focus();
			  vm.flag1=false;
	          return vm.errMessage1;
	        }
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage1 = '';
			  vm.errMessage = '';
			  vm.flag=true;
			  vm.flag1=true;
	          return vm.errMessage1;
	        }
	    }
    	
    	vm.cancelSerial=cancelSerial;
    	function cancelSerial(){
    		CommonService.closePopup1();
    	}
		
		//Enter
		$("#findSerialId").on("keypress", function (e) {
            if (e.keyCode === 13) {
				if(!vm.validator.validate()){
					$("#listSerial").focus();
				}	
				else {
					$("#doSearchSerial").click();
				}	
			}
		});
		//End
		
		//Enter
		$("#doSearchHistory").on("keypress", function (e) {
            if (e.keyCode === 13) {
					$("#doSearchSerial").click();
				}	
		});
		
		$(document).on("keypress", function (e) {
         		switch(e.keyCode) {
         			case 27:
         				$("#cancel").click();
         				break;
         			case 13:
					if($(':focus').size()===0){
         				if (e.keyCode === 13&&!$('#listSerial:focus').length) {
							$("#doSearchSerial").click();
							break;
						}
					}
         		}
         	} );
	}
})();
