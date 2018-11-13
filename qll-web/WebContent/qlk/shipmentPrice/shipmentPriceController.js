(function() {
	'use strict';
	var controllerId = 'shipmentPriceController';
	
	angular.module('MetronicApp').controller(controllerId, shipmentPriceController);
	
	function shipmentPriceController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,shipmentPriceService,shipmentDetailService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showDetail1 = true;
		vm.showDetail2 = false;
		initFormData();
		vm.taxSearch={
				status:1,
				type: 3
		
		};
		vm.shipmentSearch =
		{
				type : 0,
				listStatus : ['3']
		};
		function initFormData() {
			fillDataTable([]);
			fillData1Table([]);
			fillData2Table([]);
		}
		
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.shipmentPriceGrid;
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		function fillDataTable(data) {
			vm.gridPriceOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,
				resizable: true,
				columnMenu: false,
				toolbar: [
                    {
                        name: "actions",
                        template: 
                        	 '<div class="btn-group pull-right margin-left10 margin_right10">'+
                             '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                             '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
                             '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                             '<label ng-repeat="column in vm.shipmentPriceGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                             '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                             '</label>'+
                             '</div></div>'              
                    }
                    ],
				dataBound: function (e) {
				    var grid = vm.shipmentPriceGrid;
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
				        var teamplateUrl="qlk/shipmentDetail/shipmentDetailList.html";
				        var title="Thông tin chi tiết lô hàng";
						$kWindow.open({
							 options: {
								 modal: true,
								 title: title,
								 visible: false,
								 width: '88%',
								 height: '88%',
								 actions: ["Minimize", "Maximize", "Close"],
								 open: function () {
									 this.wrapper.children('.k-window-content').addClass("fix-footer");
									 $rootScope.$broadcast("shipment.object.data", dataItem);
								 }
							 },                
							 templateUrl: teamplateUrl
				});
						
				    });
				},
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
							url: Constant.BASE_SERVICE_URL + "shipmentRsServiceRest/shipment/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							vm.shipmentSearch.page = options.page
							vm.shipmentSearch.pageSize = options.pageSize
							return JSON.stringify(vm.shipmentSearch)
						}
					},					 
					pageSize: 20
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
				columns: [{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#shipmentPriceGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã lô hàng",
					field: 'code',
			        width: 120,
			        template: dataItem=> '<a class="#=shipmentId#" href="javascript:void(0);" ng-click=vm.showSix(dataItem)> {{dataItem.code}} </a>',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Mã hợp đồng",
					field: 'contractCode',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Loại lô hàng",
			        field: 'type',
			        width: 90,
			        template :  
			        "# if(type == 1){ #" + "#= 'Lô hàng nội' #" + "# } " +
			        "else if (type == 2) { # " + "#= 'Lô hàng ngoại' #"+ "#} " +
			        "#",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					
				},
				{
					title: "Ngày giao <br> hàng",
			        field: 'shiperDate',
			        width: 95,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Người tạo",
			        field: 'createdBy',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				 
				{
					title: "Đơn vị",
			        field: 'projInvestProjectId',
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
					 template :  
					        "# if(status == 01)" +
					        "{ #" + "#= 'Mới tạo' #" + "# } " +
					        "else if (status == 02) " +
					        "{ # " + "#= 'Đã cập nhật hàng hóa' #"+ "#} " +
					        "else if (status == 03) " +
					        "{ # " + "#= 'Đã định lượng' #"+ "#} " +
					        "else if (status == 04) " +
					        "{ # " + "#= 'Đã định giá' #"+ "#} " +
					        "else if (status == 05) " +
					        "{ # " + "#= 'Đã tạo YCKT' #"+ "#} " +
					        "else if (status == 06) " +
					        "{ # " + "#= 'Đã tạo BBKT' #"+ "#} " +
					        "else if (status == 07) " +
					        "{ # " + "#= 'Đã nhập kho' #"+ "#} " +
					        "else if (status == 08) " +
					        "{ # " + "#= 'Đã hủy' #"+ "#} " +
					        "#",
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định giá",
			        template: dataItem =>			
						'<div class="text-center #=shipmentId#"">'+
						'<a class=" icon_table #=shipmentId#" uib-tooltip="Định giá" translate>'+
						'<i ng-hide="dataItem.status != 3 && dataItem.status != 4" class="fa fa-usd" aria-hidden="true" ng-click=vm.edit(dataItem)></i>'+
						'</a>'+
						'<a class=" icon_table #=shipmentId#" uib-tooltip="Định giá" translate>'+
						'<i ng-hide="dataItem.status == 3 || dataItem.status == 4" class="fa fa-trash" aria-hidden="true"></i>'+
						'</a>'
						+'</div>',
				width: 100,
			        field:"stt"
				}
				,]
			});
		}
		
		
		vm.showSix = function showSix(dataItem){
// var grid = vm.shipmentPriceGrid;
// var item=grid.table.find("tr div." +id);
// var uid=$(item).parent().parent().attr("data-uid");
	    	var dataItem=dataItem;
	        var teamplateUrl="qlk/shipmentDetail/shipmentDetailList.html";
	        var title="Thông tin chi tiết lô hàng";
			$kWindow.open({
				 options: {
					 modal: true,
					 title: title,
					 visible: false,
					 width: '88%',
					 height: '88%',
					 actions: ["Minimize", "Maximize", "Close"],
					 open: function () {
						 this.wrapper.children('.k-window-content').addClass("fix-footer");
						 $rootScope.$broadcast("shipment.object.data", dataItem);
					 }
				 },                
				 templateUrl: teamplateUrl
			});
		}
		
		vm.exportExcelGrid = function(){
			var ds = $("#shipmentPriceGrid").data("kendoGrid").dataSource;
	    	var rows = [{
		        cells: [
		          { value: "STT", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã lô hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã hợp đồng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Loại lô hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Ngày giao hàng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Người tạo", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Đơn vị", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Trạng thái", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}
		        ]
		      }];
	    	ds.fetch(function(){
	    		var data = this.data();
	    		for (var i = 0; i < data.length; i++){
			          //push single row for every record
			        	rows.push({
				            cells: [
				              { value: i+1 , textAlign: "center"},
				              { value: data[i].code },
				              { value: data[i].contractCode},
				              { value: TyoeOfShipment(data[i].type) },
				              { value: data[i].shiperDate },
				              { value: data[i].createdBy },
				              { value: data[i].projInvestProjectId},
				              { value: statusOfShipment(data[i].status)},
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
			              ],
			              
			              // Title of the sheet
			              title: "Danh sách lô hàng",
			              // Rows of the sheet
			              rows: rows
			            }
			          ]
			        });
	    		kendo.saveAs({dataURI: workbook.toDataURL(), fileName: "Danh sách lô hàng.xlsx"});
	    	});
		}
		
		function statusOfShipment(x){
			 if(x == 1)
			 { return "Mới tạo";}
			 else if (x == 2)
			 { return "Đã cập nhật hàng hóa";}
		     else if (x == 3)
		     { return "Đã định lượng";}
		     else if (x == 4){ return "Đã định giá";}
		     else if (x == 5){ return "Đã tạo YCKT";}
		     else if (x == 6){ return "Đã tạo BBKT";}
		     else if (x == 7){ return "Đã nhập kho";}
		     else if (x == 8){ return "Đã hủy";}
		}
		
		function TypeOfShipment(x){
			if(type == 1){ 
				return "Lô hàng nội";
			}else if (type == 2) {
				return "Lô hàng ngoại";
			}
			
		}
		
		
		function fillData1Table(data) {
			vm.gridPrice1Options = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				//dataSource:data,
				dataSource:{
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
							url: Constant.BASE_SERVICE_URL + "taxRsServiceRest/tax/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              //  vm.taxSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.taxSearch.page = options.page
								vm.taxSearch.pageSize = options.pageSize

								return JSON.stringify(vm.taxSearch)

						}
					},					 
					pageSize: 20
				},
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSizes: [10, 15, 20, 25],
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#gridPrice1Options").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
					title: "Mã thuế",
					field: 'code',
			        width: 95,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên thuế",
			        field: 'name',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thuế xuất \(%)",
			        field: 'value',
			        width: 130,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại thuế",
					template :  "# if(type == 2){ #" + "#= 'Đầu ra' #" + "# } " + "else if (type == 1) { # " + "#= 'Đầu vào' #"+ "#} #",
			        field: 'type',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Phân bổ",
					template :  "# if(apply == 0){ #" + "#= 'Không' #" + "# } " + "else if (apply == 1) { # " + "#= 'Có' #"+ "#} #",
			        field: 'apply',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Miễn thuế",
					template :  "# if(ignore == 0){ #" + "#= 'Không' #" + "# } " + "else if (ignore == 1) { # " + "#= 'Có' #"+ "#} #",
			        field: 'ignore',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tiền thuế (VNĐ)",
					template :  "# if(ignore == 0){ #" + "#= 'Không' #" + "# }",
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},]
			});
		}
		
		function fillData2Table(data) {
			vm.gridPrice2Options = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:  data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSizes: [10, 15, 20, 25],
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#shipmentGoodsGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng",
					field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Đơn vị tính",
					field: 'unitTypeName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
					field: 'amount',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định lượng cho 1 đơn vị HH",
					field: 'applyPrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định lượng theo danh mục",
					field: 'estimatePriceSum',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tỷ lệ % theo danh mục",
					field: 'estimatePricePercent',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn giá",
					field: 'applyPrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thành tiền",
					field: 'applyTotalMoney',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		vm.openDepartment=openDepartment
		function openDepartment(){
			var obj={};
// obj.page=1;
// obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data=result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
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
					columns:[ {
						title: "Mã",
						field: "code",
						width: 120
					}, {
						title: "Tên",
						field: "text",
						width: 120
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%');
			});
		}
		vm.commonSearch = {name: '', code: ''};
        vm.gridCommon = [ {
			title: "Mã xxx",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
		
		vm.onSave=onSave;
		
		function onSave(data){
			vm.shipmentSearch.createdDeptName=data.text;
			vm.shipmentSearch.createdDeptId = data.code;
		}
		vm.cancel= cancel ;
		function cancel(){
				vm.shipmentGoods={};
				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		vm.cancelDept = function()
		{
			vm.shipmentSearch.createdDeptName = undefined;
			vm.shipmentSearch.createdDeptId = undefined;
		}
		vm.cancelStatis = function()
		{
			vm.shipmentSearch.listStatus = ['03'];
		}
		vm.cancelCreatedBy = function()
		{
			vm.shipmentSearch.createName = undefined;
		}
		
		vm.edit = edit;
		function edit(dataItem){
			vm.showDetail1 = true;
			vm.showDetail2 = false;
			vm.shipment =dataItem;
			var teamplateUrl="qlk/shipmentPrice/shipmentPricePopup.html";
			var title="Định giá";
			var windowId="SHIPMENTPRICE"
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'80%','80%');
			doSearchMap();
			DocTien(dataItem);
		}
		
		vm.nextStep = nextStep;
		function nextStep(){
			vm.showDetail1 = false;
			vm.showDetail2 = true;
		}
		
		vm.prevStep = prevStep;
		function prevStep(){
			vm.showDetail2 = false;
			vm.showDetail1 = true;
		}
		vm.doSearchMap = {};
		function doSearchMap()
		{
			vm.doSearchMap.shipmentId = vm.shipment.shipmentId;
			shipmentPriceService.doSearchMap(vm.doSearchMap).then(function(d) {
				refreshGrid(d.plain(),vm.shipmentPrice1Grid);
				refreshGrid(d.plain(),vm.shipmentPrice2Grid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		
		function refreshGrid(d,grid) {
			var grid = grid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		// tien
		var ChuSo=new Array(" không "," một "," hai "," ba "," bốn "," năm "," sáu "," bảy "," tám "," chín ");
		var Tien=new Array( "", " nghìn", " triệu", " tỷ", " nghìn tỷ", " triệu tỷ");

		// 1. Hàm đọc số có ba chữ số;
		function DocSo3ChuSo(baso)
		{
		    var tram;
		    var chuc;
		    var donvi;
		    var KetQua="";
		    tram=parseInt(baso/100);
		    chuc=parseInt((baso%100)/10);
		    donvi=baso%10;
		    if(tram==0 && chuc==0 && donvi==0) return "";
		    if(tram!=0)
		    {
		        KetQua += ChuSo[tram] + " trăm ";
		        if ((chuc == 0) && (donvi != 0)) KetQua += " linh ";
		    }
		    if ((chuc != 0) && (chuc != 1))
		    {
		            KetQua += ChuSo[chuc] + " mươi";
		            if ((chuc == 0) && (donvi != 0)) KetQua = KetQua + " linh ";
		    }
		    if (chuc == 1) KetQua += " mười ";
		    switch (donvi)
		    {
		        case 1:
		            if ((chuc != 0) && (chuc != 1))
		            {
		                KetQua += " mốt ";
		            }
		            else
		            {
		                KetQua += ChuSo[donvi];
		            }
		            break;
		        case 5:
		            if (chuc == 0)
		            {
		                KetQua += ChuSo[donvi];
		            }
		            else
		            {
		                KetQua += " lăm ";
		            }
		            break;
		        default:
		            if (donvi != 0)
		            {
		                KetQua += ChuSo[donvi];
		            }
		            break;
		        }
		    return KetQua;
		}

		// 2. Hàm đọc số thành chữ (Sử dụng hàm đọc số có ba chữ số)

		function DocTienBangChu(SoTien)
		{
		    var lan=0;
		    var i=0;
		    var so=0;
		    var KetQua="";
		    var tmp="";
		    var ViTri = new Array();
		    if(SoTien<0) return "Số tiền âm !";
		    if(SoTien==0) return "Không đồng !";
		    if(SoTien>0)
		    {
		        so=SoTien;
		    }
		    else
		    {
		        so = -SoTien;
		    }
		    if (SoTien > 8999999999999999)
		    {
		        // SoTien = 0;
		        return "Số quá lớn!";
		    }
		    ViTri[5] = Math.floor(so / 1000000000000000);
		    if(isNaN(ViTri[5]))
		        ViTri[5] = "0";
		    so = so - parseFloat(ViTri[5].toString()) * 1000000000000000;
		    ViTri[4] = Math.floor(so / 1000000000000);
		     if(isNaN(ViTri[4]))
		        ViTri[4] = "0";
		    so = so - parseFloat(ViTri[4].toString()) * 1000000000000;
		    ViTri[3] = Math.floor(so / 1000000000);
		     if(isNaN(ViTri[3]))
		        ViTri[3] = "0";
		    so = so - parseFloat(ViTri[3].toString()) * 1000000000;
		    ViTri[2] = parseInt(so / 1000000);
		     if(isNaN(ViTri[2]))
		        ViTri[2] = "0";
		    ViTri[1] = parseInt((so % 1000000) / 1000);
		     if(isNaN(ViTri[1]))
		        ViTri[1] = "0";
		    ViTri[0] = parseInt(so % 1000);
		  if(isNaN(ViTri[0]))
		        ViTri[0] = "0";
		    if (ViTri[5] > 0)
		    {
		        lan = 5;
		    }
		    else if (ViTri[4] > 0)
		    {
		        lan = 4;
		    }
		    else if (ViTri[3] > 0)
		    {
		        lan = 3;
		    }
		    else if (ViTri[2] > 0)
		    {
		        lan = 2;
		    }
		    else if (ViTri[1] > 0)
		    {
		        lan = 1;
		    }
		    else
		    {
		        lan = 0;
		    }
		    for (i = lan; i >= 0; i--)
		    {
		       tmp = DocSo3ChuSo(ViTri[i]);
		       KetQua += tmp;
		       if (ViTri[i] > 0) KetQua += Tien[i];
		       if ((i > 0) && (tmp.length > 0)) KetQua += ',';// &&
																// (!string.IsNullOrEmpty(tmp))
		    }
		   if (KetQua.substring(KetQua.length - 1) == ',')
		   {
		        KetQua = KetQua.substring(0, KetQua.length - 1);
		   }
		   KetQua = KetQua.substring(1,2).toUpperCase()+ KetQua.substring(2);
		   return KetQua;// .substring(0, 1);//.toUpperCase();// +
							// KetQua.substring(1);
		}
		
		function DocTien(dataItem){
			if(dataItem.totalFee != undefined){
				vm.docso.totalFee = DocTienBangChu(dataItem.totalFee);
			}
			if(dataItem.totalOriginMoney != undefined){
				vm.docso.totalOriginMoney = DocTienBangChu(dataItem.totalOriginMoney);
			}
			if(dataItem.totalTax != undefined){
				vm.docso.totalTax = DocTienBangChu(dataItem.totalTax);
			}
			if(dataItem.totalMoney != undefined){
				vm.docso.totalMoney = DocTienBangChu(dataItem.totalMoney);
			}
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.shipmentPriceGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.shipmentPriceGrid.showColumn(column);
            } else {
            	vm.shipmentPriceGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		** Filter các cột của select 
		*/	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		// /END
	}
})();