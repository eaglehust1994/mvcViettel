(function() {
	'use strict';
	var controllerId = 'export';
	
	angular.module('MetronicApp').controller(controllerId,  exportManageController);
	
	
	function exportManageController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,exStaManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant)  {
		/**Content**/
		//0.attributes
		//1. multiple select (dropdown)
		//2. tables for popup (Nguoi tao va kho nhap)
				// 2.1.danh sách kho nhập (pop-up)		// 2.2.danh sách kho nhập (pop-up)
				//2.3. danh sach don vi(pop-up)
		//3. initdata
			//3.1 data search	//3.2 Lập phiếu trình ký		//3.3 Chi tiết hàng hóa		//3.4 Đơn vị quản lý		//3.5 Danh sách hàng hóa
		//4. complex popups - including tables and inputs
				//4.1 tabs		//4.2 complex popups
		//5.validator
		//6.show/hide column fields
		//7.cancel input data
		//8. Show detail from result row
		//9.crud data
		//10.dateTime
		//11.show/hide panels
		//12.autocomplete
		//13.close/cancel popup
		//14.searching
		//15.treeView
		//16.delete result
		//17.refresh grid
		//18.export
		//sign
		//19.rEx
		//showpopup (only with data)
		
		/**Real content**/
		//0.attributes
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
		
		vm.showTabOneNoteDetail = true;
		vm.showTabTwoNoteDetail = false;
		vm.showTabThreeNoteDetail = false;
		vm.stockTrans={};
		vm.stockTransDetailSearch ={};
		vm.stockTransDetailSerialSearch = {};
	/*	vm.appParamSearch={
				status:1
		};
		vm.appParam={};*/
		
		//1. multiple select (dropdown)
		$("#ns").kendoMultiSelect().data("kendoMultiSelect");
		$("#cast").kendoMultiSelect().data("kendoMultiSelect");
		

		//2. tables for popup (Nguoi tao & Kho nhap)
					// 2.1.danh sách kho nhập (pop-up)
					vm.gridStockCommon = [ 
					{
						title: "STT",
						field: "stockId",	
						width: 30
					}, {
						title: "Mã của kho",
						field: "code",
						width: 48
					}, {
						title: "Tên kho",
						field: "name",
						width: 50
					}, {
						title: "Đơn vị quản lý",
						field: "departmentName",						
						width: 70
					}/*, {
						title: "Chọn lựa",
						field: "stt",		
						width: 30
					}*/];
		
					//2.2.danh sach nguoi tao (pop-up)
					/*vm.gridCreaterCommon = [ {*/
						/*		title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
							    width: 25,
						        headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},	
						{
							title: "STT",
							field: "stockId",	
							width: 30
						}, {
							title: "Mã kho",
							field: "code",
							width: 48
						}, {
							title: "Tên kho",
							field: "name",
							width: 50
						}, {
							title: "Đơn vị quản lý",
							field: "departmentName",						
							width: 70
						}, {
							title: "Chọn",
							field: "name",
							width: 30
						}];*/
		
		//3.initdata
		initFormData();
		function initFormData() {
			fillDataTable([]);
	    	//fillSubNoteTable([]);
			fillDataTableGoodsDetail();
			//fillDataTableGoodsList([]);
			//fillDataTableUnit([]);
			//fillDataTableUsers([]);
		}
					//3.1 data search
					function fillDataTable(data) {
						vm.gridOptions = kendoConfig.getGridOptions({
							autoBind: true,
							resizable: true,	
							toolbar: [
			                    {
			                        name: "actions",
			                        template:
			                      	  '<div class="btn-group pull-right margin_top_button margin10">'+
					                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
					                    '<i class="action-button excelQLK" aria-hidden="true"></i>'+
					                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
					                    '<label ng-repeat="column in vm.exGrid.columns| filter: vm.gridColumnShowHideFilter">'+
					                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
					                    '</label>'+                    
					                    '</div></div>'
			                    }
			                    ],
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
					              		url: Constant.BASE_SERVICE_URL + "stockTransServiceRest/stockTrans/doSearchExportStatement",
										contentType: "application/json; charset=utf-8",
										type: "POST"
									},					
									parameterMap: function (options, type) {
										if(vm.stockTrans.endDate == ""){
											vm.stockTrans.endDate = null;
										}
			                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
										    vm.stockTrans.page = options.page
											vm.stockTrans.pageSize = options.pageSize
											return JSON.stringify(vm.stockTrans)
									}
								},					 
								pageSize: 20
							} ,
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
								{title : "<input type='checkbox' id='chkSelectAll' name='chkSelectAll' ng-click=vm.chkSelectAll();' ng-model='vm.chkAll' />",
									 headerAttributes: {style:"text-align:center;"},
									 template : "<input type='checkbox' name='gridcheckbox' ng-click=vm.handleCheck();' />",
									 attributes:{style:"text-align:center;"},
									 width : 40 },
								{
									title: "STT",
									field:"stockTransId",
							        width: 75,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:center;"
									},
								}, 
								{
									title: "Mã yêu cầu",
									field: 'orderCode',
									template: '<a class="#=orderId#" href="javascript:void(0);">#=orderCode#</a>',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Mã phiếu",
									field: 'code',
									template: '<a href="javascript:void(0);" ng-click=vm.showNoteDetail(#=stockTransId#)>#=code#</a>',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Loại yêu cầu",
							        field: 'type',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									}, template :  "# if(type == 1){ #" + "#= 'Xuất cho đơn vị sử dụng' #" + "# } " +
							        "else if (type == 2) { # " + "#= ' Xuất ra công trình' #"+ "#} " +
							        "else if (type == 3) { # " + "#= ' Xuất đi BHSC' #"+ "#} " +
							        "else if (type == 4) { # " + "#= 'Xuất luân chuyển kho' #"+ "#} " +
							        "else if (type == 5) { # " + "#= 'Xuất tặng đối tác' #"+ "#} " +
							        "else if (type == 6) { # " + "#= 'Xuất cho đối tác mượn' #"+ "#} " +
							        "else if (type == 7) { # " + "#= 'Xuất trả đối tác' #"+ "#} " +
							        "else if (type == 8) { # " + "#= ' Xuất thanh lý' #"+ "#} " +
							        "else if (type == 9) { # " + "#= ' Xuất cho đề tài/dự án' #"+ "#} " +
							        "else { # " + "#= 'Xuất bán' #"+ "#} " +
							        		"#",
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Kho nhập",
							        field: 'stockName',
							        width: 200,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Người tạo",
									 field: 'createdByName',
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
									 template :  "# if(status == 1){ #" + "#= 'Chưa xuất/nhập' #" + "# } " +
								        "else if (status == 2) { # " + "#= ' Đã xuất/nhập' #"+ "#} " +
								        "else { # " + "#= ' Đã hủy' #"+ "#} " +
								  		"#",
									 width: 200,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Tình trạng ký CA",
									 field: 'signState',
							        width: 200,
							        template :  "# if(signState == 1){ #" + "#= 'Chưa trình kí' #" + "# } " +
							        "else if (status == 2) { # " + "#= ' Đã trình kí' #"+ "#} " +
							        "else if (status == 3){ # " + "#= ' Đã kí' #"+ "#} " +
							        "else { # " + "#= ' Đã hủy' #"+ "#} " +
							  		"#",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},{
									title: "Thao tác",
							        template:
							        	'<div class="text-center #=stockTransId#""><a '+
										' class=" icon_table #=stockTransId#"'+
										' uib-tooltip="Thực Xuất Phiếu" translate'+
										' ng-click=vm.rEx(#=stockTransId#)>'+'<i class="fa fa-upload" aria-hidden="true" ></i>'+
									'</a>'+
									'<a type="button"'+
									'class="#=stockTransId# icon_table"  uib-tooltip="Sửa" translate'+
										' ng-click=vm.edit(#=stockTransId#)>'+
										'<i class="fa fa-pencil" aria-hidden="true"></i>'+
									'</a>'+
									'<a type="button"'+
									'class="#=stockTransId# icon_table"  uib-tooltip="Hủy phiếu xuất" translate'+
										' ng-click=vm.remove(#=stockTransId#)>'+
									'<i class="fa fa-trash" aria-hidden="true"></i>	'+
									'</a>'
									+'</div>',
							        width: 150,
							        field:"stt"
								}
								]	
							
						});
					}
					
					//3.2 Lập phiếu trình ký
					function fillSubNoteTable(data) {
						vm.SubNoteGridOptions = kendoConfig.getGridOptions({
							autoBind: true,
							resizable: true,			 
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
										url: Constant.SERVICE_URL + "appParamServiceRest/doSearch",
										contentType: "application/json; charset=utf-8",
										type: "POST"
									},					
									parameterMap: function (options, type) {
				                          //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
										    vm.appParamSearch.page = options.page
											vm.appParamSearch.pageSize = options.pageSize

											return JSON.stringify(vm.appParamSearch)

									}
								},					 
								pageSize: 20
							},
//							dataSource: data,
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
						        template: dataItem => $("#appParamGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        width: 30,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}
							,  {
								title: "Xóa",
								field: 'code',
						        width: 30,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}, {
								title: "Mã phiếu",
						        field: 'name',
						        width: 60,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}]
						});
					}
		
					
					//3.3 Chi tiết hàng hóa		
					function fillDataTableGoodsDetail(){
						vm.goodsDetailForNoteGrid3Options = kendoConfig.getGridOptions({
							 sortable: true,
			                 filterable: true,
			                 columnMenu: true,
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
											url: Constant.BASE_SERVICE_URL + "stockTransDetailSerialServiceRest/stockTransDetailSerial/doSearchGoodsDetailForExportNote",
											contentType: "application/json; charset=utf-8",
											type: "POST"
										}
									},					 
									pageSize: 10
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
							        template: dataItem => $("#goodsDetailForNoteGrid3").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							        width: 70,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:center;"
									},
								}
								, {
									title: "Mã ",
									field: 'stockTransDetailId',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Serial",
									field: 'serial',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Mã hợp đồng",
							        field: 'contractCode',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Part number",
							        field: 'partNumber',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Hãng sản xuất",
									 field: 'manufacturerName',
							        width: 200,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Nước sản xuất",
									 field: 'producingCountryName',
							        width: 200,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Vị trí",
									 field: 'status',
							       template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else { # " + "#= 'Hiệu lực' #"+ "#} #",
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
					//3.4 Đơn vị quản lý
					function fillDataTableUnit(data) {
						vm.unitGridOptions = kendoConfig.getGridOptions({
							autoBind: true,
							resizable: true,			 
							dataSource: data ,
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
							        template: dataItem => $("#imReqMaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							        width: 40,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:center;"
									},
								}
								,  {
									title: "Mã phòng ban",
									field: 'code',
							        width: 140,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Tên phòng ban",
							        field: 'name',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Đơn vị cha",
							        field: 'apply',
							        width: 150,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Ngày hiệu lực",
									 field: 'status',
							        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
							        width: 140,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Ngày hết hiệu lực",
									 field: 'status',
							        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
							        width: 140,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Chọn",
									 field: 'status',
							        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
							        width: 80,
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}]
						});
					}
		
					//3.5 Danh sách hàng hóa
				function fillDataTableGoodsList(data) {
					vm.goodsListForNoteGridOptions = kendoConfig.getGridOptions({
					    change: function() {
					          var gridDetails = $("#goodsDetailForNoteGrid3").data("kendoGrid");
					          var dataItem = this.dataItem(this.select());
					          gridDetails.dataSource.filter({ field: "stockTransDetailId", value: dataItem.stockTransDetailId, operator: "eq" });
					        },

					    	dataBound: function(e) {
					    							  var grid = $("#goodsDetailForNoteGrid3").data("kendoGrid");
					    							   var tr = $("#goodsListForNoteGrid").data("kendoGrid").select("tr:eq(0)");
					    							   var dataItem = $("#goodsListForNoteGrid").data("kendoGrid").dataItem(tr);
					    					          grid.dataSource.filter({ field: "stockTransDetailId", value: dataItem.stockTransDetailId, operator: "eq" });
					    	},
						autoBind: true,
						resizable: true,
						selectable: true,
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
									url: Constant.BASE_SERVICE_URL + "stockTransDetailServiceRest/stockTransDetail/doSearchGoodsForExportStatement",
									contentType: "application/json; charset=utf-8",
									type: "POST"
								},				
								parameterMap: function (options, type) {
									    vm.stockTransDetailSearch.page = options.page
										vm.stockTransDetailSearch.pageSize = options.pageSize
										vm.stockTransDetailSearch.stockTransId = vm.stockTrans.stockTransId
										return JSON.stringify(vm.stockTransDetailSearch)
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
				
						columns: [
						{
							title: "STT??????????",
							field:"stt",
					        template: dataItem => $("#goodsListForNoteGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
						}, {
							title: "Đơn vị tính",
					        field: 'goodsUnitName',
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},  {
							title: "Số lượng",
							 field: 'amountReal',
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
					        width: 200,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}},
							{
								title: "Chi tiết hàng hóa",
								field: 'stt',
						        width: 200,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
								template: '<a href="javascript:void(0);" ng-click=caller.showSerialDetail(#=stockTransDetailId#)>Xem chi tiết</a>',
							  
						}]
					});
				}

				vm.serials = {};
				vm.showSerialDetail = showSerialDetail;
				function showSerialDetail (itemId) {
					//get serial by clicking each product
					exStaManaService.getStockTransDetailSerialById(itemId).then(function(d){
						vm.serials = d.plain();
						 fillDataTableGoodsSerialList([]);
						 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'95%','85%');
						}, function() {
							console.error('Error');
						});
				}
				
				
				
				//3.6 Danh sách USERS
				function fillDataTableUsers(data) {
					vm.exUserGridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						resizable: true,	
						toolbar: [
	                          {
	                              name: "actions",
	                              template: 
	                             	  '<div class="btn-group pull-right margin10">'
				                      + '<i data-toggle="dropdown" aria-expanded="false"><i class="glyphicon glyphicon-cog" style="color:navy;font-size:15px;" aria-hidden="true"></i></i>'+
				                          '<a class="action-button excelQLK"></a>' + '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
				                          '<label ng-repeat="column in vm.exUserTable.columns| filter: vm.gridColumnShowHideFilter">'+
				                          '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
				                          '</label>'+
				                         '</div>'
	                          }
	                          ],
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
									url: Constant.BASE_SERVICE_URL + "sysUserServiceRest/user/sysAllUsersQLK",
									contentType: "application/json; charset=utf-8",
									type: "POST"
								},					
								parameterMap: function (options, type) {
			                          //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
									    vm.appParamSearch.page = options.page
										vm.appParamSearch.pageSize = options.pageSize
		
										return JSON.stringify(vm.appParamSearch)
		
								}
							},					 
							pageSize: 10
						},noRecords: true,
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
					        template: dataItem => $("#exUserTable").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					        width: 70,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
						}
						,  {
							title: "Tên đăng nhập",
							field: "loginName",
					        width: 120,
					   	  /*  template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(#=orderId#)>#=code#</a>',
							*/  headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
							title: "Họ tên",
					        field: "fullName",
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
							title: "Mã",
					        field: "employeeCode",
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},  {
							title: "Email",
							 field: "email",
					        width: 200,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},  {
							title: "SĐT",
							 field: "phoneNumber",
					        width: 200,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Thao tác",
					        template:
					        	'<div class="text-center #=stockTransId#""><a '+
								' class=" icon_table #=stockTransId#"'+
								' uib-tooltip="Thực xuất" translate'+
								' ng-click=vm.edit(#=stockTransId#)>'+'<i class="fa fa-upload" aria-hidden="true"></i>'+
							'</a>'+
						'<a type="button"'+
						'class="#=orderId# icon_table"  uib-tooltip="Xóa" translate'+
							'ng-click=vm.openRemovePopup(#=orderId#)>'+
							'<i class="fa fa-pencil" aria-hidden="true"></i>'+
						'</a>'+
						'<a type="button"'+
						'class="#=reasonId# icon_table"  uib-tooltip="Xóa" translate'+
							'ng-click=vm.openRemovePopup(#=reasonId#)>'+
						'<i class="fa fa-trash" aria-hidden="true"></i>	'+
						'</a>'
						+'</div>',
					        width: 150,
					        field:"stt"
						}
						]
					
						
					});
				}

		//4. complex popups - including tables and inputs
				//4.1 tabs
				vm.gotoTabOneNoteDetailPopUp = function(){
					vm.showTabOneNoteDetail = true;
					vm.showTabTwoNoteDetail = false;
					vm.showTabThreeNoteDetail = false;
				}
				vm.gotoTabTwoNoteDetailPopUp = function(){
					vm.showTabOneNoteDetail = false;
					vm.showTabTwoNoteDetail = true;
					vm.showTabThreeNoteDetail = false;
				}
				vm.gotoTabThreeNoteDetailPopUp = function(){
					vm.showTabOneNoteDetail = false;
					vm.showTabTwoNoteDetail = false;
					vm.showTabThreeNoteDetail = true;
				}
				
				//4.2 complex popups
		
		//5.validator
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		//6.show/hide column fields
		vm.showHideColumn=function(column){
	    	if (angular.isUndefined(column.hidden)) {
	    		vm.exGrid.hideColumn(column);
	        } else if (column.hidden) {
	        	vm.exGrid.showColumn(column);
	        } else {
	        	vm.exGrid.hideColumn(column);
	        }   	
	    }
		/*
		** Filter các cột của select 
		*/		vm.gridColumnShowHideFilter = function (item) { 
	            return item.type==null||item.type !=1; 
	        };
		
		 //7.cancel input data
        vm.cancelest = function(id){
				if(id==="stock")
					{
					vm.orderSearch.stock = undefined;
					}
				if(id==="creater")
					{
						vm.exSk.creater = undefined;
					}
				if(id==="cast")
					{
						 $('#cast').data("kendoMultiSelect").value([]);
					}
				if(id==="ns")
					{
						 $('#ns').data("kendoMultiSelect").value([]);
					}
				if(id==="dates"){
					vm.stockTrans.endDate = undefined;
				}
				
				if(id==="createdDeptedName")
				{
					vm.stockTrans.createdDeptedName = undefined;
				}
				//in sendExSignPopup
				/*if(id==="exkeware")
				{
					vm.exSk.exkeware = undefined;
				}
				if(id==="rec")
				{
					vm.exSk.rec = undefined;
				}
				if(id==="finCharge")
				{
					vm.exSk.finCharge = undefined;
				}if(id==="finMan")
				{
					vm.exSk.finMan = undefined;
				}*/
        }
	
        //8. Show detail from result row
     
		//9.CRUD data
		 vm.add = function(){ 
			 vm.isCreateNew = true;
	         vm.showDetail = true;
	         vm.appParam={};
	         vm.title="Thêm mới tham số ứng dụng"
		 }

			vm.edit = function(id){
				var teamplateUrl="qlk/exportStatementManagement/editNotePopUp.html";
				 var title="Cập nhật phiếu xuat kho";
				 var windowId="EDIT_STOCK_TRANS";
				 vm.stockTrans={};
				 
				 exStaManaService.getStockTransDetailById(id).then(function(d) {
					 vm.stockTrans = d.plain();
					 fillDataTableGoodsList([]);
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
					}, function() {
						console.error('Error');
					});
			}
		
		vm.save= function(){
                if(vm.validator.validate()){
                	if(vm.isCreateNew) {
                		appParamService.createAppParam(vm.appParam).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.appParam.appParamId = result;
                            doSearch();
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
                		appParamService.updateAppParam(vm.appParam).then(function(result){
                			toastr.success("Cập nhật thành công!");
                			doSearch();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
		}
		}
		
		vm.openDepartment=function(){
			var obj={};
//			obj.page=1;
//			obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data1=result.plain();
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
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		
		vm.remove=remove;
		function remove(id){
			var teamplateUrl="qlk/exportStatementManagement/noteCancelPopup.html";
			var grid=vm.exGrid;
			var item=grid.table.find("tr div." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			var data =	grid.dataSource.getByUid(uid);
			data.date=kendo.toString(new Date(),"dd/MM/yyyy");
			 var title="Hủy phiếu xuất kho";
			 var windowId="CANCEL_NOTE";
					 CommonService.populatePopupCreate(teamplateUrl,title,data,vm,windowId,false,'74%','40%');
				}
		
	
	
		//10.dateTime
		var d = new Date();
		var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
		vm.stockTrans.startDate = datestring;
		vm.stockTrans.endDate = null;
		
		//10a
		if(vm.stockTrans.endDate == ""){
			vm.stockTrans.endDate = null;
		}
		
		  //11.show/hide panels
		   vm.myFunc = function() {
		        vm.showSearch = !vm.showSearch;
		    }
		   
		   	
		 //13.close/cancel popup
			vm.cancel = cancel;
			function cancel(){
				vm.goTo('EXPORT_STATEMENT_MAN');
			}
			
			vm.goTo = function(menuKey) {

				var hasPerm = true;

				if (hasPerm) {
					var template = Constant.getTemplateUrl(menuKey);
					alert(template);
					postal.publish({
						channel : "Tab",
						topic : "open",
						data : template
					});
				} else {
					// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
					// hiện tại không được phép truy cập vào chức năng này!"));
				}
			}
			
			function goTo(menuKey) {
				var template = Constant.getTemplateUrl(menuKey);
				postal.publish({
					channel: "Tab",
					topic: "open",
					data: template
				});
				$rootScope.isCreatAsset = false;
				$rootScope.$broadcast("cat.detail.reload");
			}
			
			
			//14.searching
			//14.1 dosearching
			vm.doSearch= doSearch;
			function doSearch(){
				 // vm.showDetail = false;
				var grid =vm.exGrid;	
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 20
					});
				}              
			}
			//14.2 cancelsearching
			vm.canceldoSearch= function (){
				 vm.showDetail = false;
				vm.appParamSearch={
						status:"1",
				};
				doSearch();
			}
			
			
		  
		        
	/*	vm.exportExcel=function(){
			detailCatAssetUpdateService.exportExcel(vm.appParamSearch).then(
					function() {
						window.location = "fileservice/download?fileName=" + data.fileName;
						toastr.success("Export thành công");
					}, function(errResponse) {
						toastr.error("Lỗi~~~~~~~~");
					});
		}*/

			 vm.formatAction=function(dataItem){
				 var template=
				 '<div class="text-center #=appParamId#"">'				 
					 template+='<button type="button"'+
					'class="btn btn-default padding-button box-shadow  #=appParamId#"'+
					'disble="" ng-click=vm.edit(#=appParamId#)>'+
					'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=appParamId#"'+
					'ng-click=vm.send(#=appParamId#)>'+
					'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=appParamId#"'+
					'ng-click=vm.remove(#=appParamId#)>'+
					'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'
					+
					'<button type="button" class="btn btn-default padding-button box-shadow #=appParamId#"'+
					'ng-click=vm.cancelUpgradeLta(#=appParamId#)>'+
					'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>';
					template+='</div>';
				 return dataItem.groupId;
			 }
			
			//17.refresh grid
			function refreshGrid(d) {
				var grid = vm.appParamGrid;
				if(grid){
					grid.dataSource.data(d);
					grid.refresh();
				}
			}
	
			
			//ShowPopup
			vm.showPopup = showPopup;
			function showPopup(popupId){
				var templateUrl = 'qlk/exportStatementManagement/exportManGridPopup.html';
				if (popupId === 'importStockEntity'){
					
					var title = "Danh sách kho hàng";
					vm.merEntityGridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						dataSource: vm.dataMerEntity,
						noRecords: true,
	                    resizable: true,                    
						messages: {
							noRecords: gettextCatalog.getString("There is no data on current page")
						},
						columns: [
							{
								title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
						        width: 35,
						        headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},	
						{
							title: gettextCatalog.getString("STT"),
							field: 'lineNo',
							width: 29,
						}, {
							title: gettextCatalog.getString("Mã kho"),
							field: "code",
							width: 48
						}, {
							title: gettextCatalog.getString("Tên kho"),
							field: "name",
							width: 50
						}, {
							title: gettextCatalog.getString("Đơn vị quản lý"),
							field: "unitName",						
							width: 60
						}, {
							title: gettextCatalog.getString("Chọn"),
							field: "total",
							attributes: {
								style: "text-align:right;"
							},
							width: 30
						}]
					});
					CommonService.populateDataToGrid(templateUrl, title, vm.merEntityGridOptions, vm, 'merEntity');
				}
				else{
					var title = "Danh sách người dùng";
					vm.exUserGridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						messages: {
							noRecords: gettextCatalog.getString("There is no data on current page")
						},
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
									url: Constant.BASE_SERVICE_URL + "sysUserServiceRest/user/sysAllUsersQLK",
									//url: Constant.BASE_SERVICE_URL + "orderServiceRest/order/doSearchExportRequirement",
									contentType: "application/json; charset=utf-8",
									type: "POST"
								},					
								parameterMap: function (options, type) {
			                          //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
									    vm.appParamSearch.page = options.page
										vm.appParamSearch.pageSize = options.pageSize
		
										return JSON.stringify(vm.appParamSearch)
		
								}
							},					 
							pageSize: 10
						},
		//				dataSource: data,
						noRecords: true,
						columnMenu: false,
						messages: {
							noRecords : gettextCatalog.getString("Không có kết quả hiển thị rui")
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
							title: "STT 21",
							field:"stt",
					        template: dataItem => $("#exUserGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					        width: 70,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
						}
						,  {
							title: "Ten dang nhap",
							field: "loginName",
					        width: 120,
					  	  headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
							title: "Ho ten",
					        field: "fullName",
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
							title: "Ma nhan vien",
					        field: "employeeCode",
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},  {
							title: "Email",
							 field: "email",
					        width: 200,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},  {
							title: "Phone",
							 field: "phoneNumber",
					        width: 200,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Thao tác",
					        template:
					        	'<div class="text-center #=stockTransId#""><a '+
								' class=" icon_table #=stockTransId#"'+
								' uib-tooltip="Thực xuất" translate'+
								' ng-click=vm.edit(#=stockTransId#)>'+'<i class="fa fa-upload" aria-hidden="true"></i>'+
							'</a>'+
						'<a type="button"'+
						'class="#=orderId# icon_table"  uib-tooltip="Xóa" translate'+
							'ng-click=vm.openRemovePopup(#=orderId#)>'+
							'<i class="fa fa-pencil" aria-hidden="true"></i>'+
						'</a>'+
						'<a type="button"'+
						'class="#=stockTransId# icon_table"  uib-tooltip="Xóa" translate'+
							' ng-click=vm.remove(#=stockTransId#)>'+
						'<i class="fa fa-trash" aria-hidden="true"></i>	'+
						'</a>'
						+'</div>',
					        width: 150,
					        field:"stt"
						}
						]
					/*	columns: [{
							title: gettextCatalog.getString("TT"),
							field: "lineNo",
							width: 25,
						}, {
							title: gettextCatalog.getString("Tên đăng nhập"),
							field: "code",
							width: 65
						}, {
							title: gettextCatalog.getString("Mã nhân viên"),
							field: "name",
							width: 50
						}, {
							title: gettextCatalog.getString("Họ tên"),
							field: "merWeight",
							width: 70
						},  {
							title: gettextCatalog.getString("Email"),
							field: "name",
							width: 60
						}, {
							title: gettextCatalog.getString("SĐT"),
							field: "merWeight",
							width: 50
						},{
							title: gettextCatalog.getString("Chọn"),
							field: "vndUnitPrice",
							width: 30
						}]*/
					});
					CommonService.populateDataToGrid(templateUrl, title, vm.sysUserGridOptions, vm, 'constructionAcceptance');
				}
			}
			
			//19.
			
			vm.showNoteDetail=function(id){
				var teamplateUrl="qlk/exportStatementManagement/detailCPopup.html";
				 var title="Thông tin phiếu xuất kho";
				 var windowId="STOCK_TRANS_2";
				 vm.stockTrans={};
				 
	       		exStaManaService.getStockTransDetailById(id).then(function(d) {
					 vm.stockTrans = d.plain();
					 fillDataTableGoodsList([]);
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'95%','85%');
					}, function() {
						console.error('Error');
					});
			}
			
			vm.sign=function(){
				var teamplateUrl="qlk/exportStatementManagement/signPopup.html";
				 var title="Thông tin phiếu trình kí";
				 var windowId="SIGNALL_NOTE";
				//vm.stockTrans={};
				 
				 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','80%');		
			}
			
			vm.rEx=rEx;
			function rEx(id){
				var teamplateUrl="qlk/exportStatementManagement/RealExInfoPopup.html";
				 var title="Thực xuất phiếu xuất";
				 var windowId="REALEX_NOTE";
				
				 vm.stockTrans={};
				 
		       		exStaManaService.getStockTransDetailById(id).then(function(d) {
						 vm.stockTrans = d.plain();
						 fillDataTableGoodsList([]);
						 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'95%','85%');
						}, function() {
							console.error('Error');
						});
				}
		

/* $("#goodsDetailForNoteGrid2").kendoGrid({ 
				 sortable: true,
                 filterable: true,
                 columnMenu: true,
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
								url: Constant.BASE_SERVICE_URL + "stockTransDetailSerialServiceRest/stockTransDetailSerial/doSearchGoodsDetailForExportNote",
								contentType: "application/json; charset=utf-8",
								type: "POST"
							}
						},					 
						pageSize: 10
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
				        template: dataItem => $("#goodsDetailForNoteGrid2").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
				        width: 70,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					}
					, {
						title: "Mã ",
						field: 'stockTransDetailId',
				        width: 150,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Serial",
						field: 'serial',
				        width: 150,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Mã hợp đồng",
				        field: 'contractCode',
				        width: 150,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Part number",
				        field: 'partNumber',
				        width: 150,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Hãng sản xuất",
						 field: 'manufacturerName',
				        width: 200,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Nước sản xuất",
						 field: 'producingCountryName',
				        width: 200,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Vị trí",
						 field: 'status',
				       template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else { # " + "#= 'Hiệu lực' #"+ "#} #",
				        width: 200,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}]
				 
				 
				 
			 });*/

			 
	/* $("#orderGoods").kendoGrid({
			dataBound: function(e) {
				  var grid = $("#goodsDetailForNoteGrid2").data("kendoGrid");
				  var dataItem = this.dataItem(this.select("tr:eq(0)"));
		          grid.dataSource.filter({ field: "stockTransDetailId", value: dataItem.stockTransDetailId, operator: "eq" });
			},
		 selectable: true,
	        change: function() {
	          var gridDetails = $("#goodsDetailForNoteGrid2").data("kendoGrid");
	          var dataItem = this.dataItem(this.select());
	          gridDetails.dataSource.filter({ field: "stockTransDetailId", value: dataItem.stockTransDetailId, operator: "eq" });
	        },
	        
				 sortable: true,
                 filterable: true,
                 columnMenu: true,
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
								
								url: Constant.BASE_SERVICE_URL + "stockTransDetailServiceRest/stockTransDetail/doSearchGoodsForExportStatement2",
								contentType: "application/json; charset=utf-8",
								type: "POST"
							},			
						},					 
						pageSize: 10
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
					        template: dataItem => $("#orderGoods").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
						}, {
							title: "Mã ",
							field: 'stockTransDetailId',
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Tên hàng",
					        field: 'goodsName',
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}, {
							title: "Đơn vị tính",
					        field: 'goodsUnitName',
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},  {
							title: "Số lượng",
							 field: 'amountReal',
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
					        width: 200,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}]
				 
			 });*/
		
			
			
			vm.handleCheck = function(item){
				if(document.getElementById("chkSelectAll").checked == true){
					document.getElementById("chkSelectAll").checked = false;
				}
			}
			
			vm.chkSelectAll = function(item) {
		    	var grid = vm.exGrid;
		    	chkSelectAllBase(vm.chkAll, grid);
		    }

			
	}//end whole main
})();