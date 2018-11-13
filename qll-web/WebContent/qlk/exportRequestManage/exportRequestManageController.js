(function() {
	'use strict';
	var controllerId = 'exportRequestManageController';
	
	angular.module('MetronicApp').controller(controllerId, exportRequestManageController);
	
	function exportRequestManageController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, exReqManaService, impReqManaService) {
		var vm = this;
		 vm.order={};
		 vm.cancelOrder = {};
		vm.orderSearch ={};
//		vm.orderSearch.listStatus = {};
		vm.orderGoodsSearch ={};
		vm.orderGoodsDetailSearch ={};
		vm.showSearch = true;
//		vm.showDetail = false;
		vm.showTabOne = true;
		vm.showAdvancedSearch = false;
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

		vm.orderSearch.listStatus = ['1'];
		
//		$("#requestType").kendoMultiSelect().data("kendoMultiSelect");
//		$("#requestStatus").kendoMultiSelect();
//		$("#signCA").kendoMultiSelect().data("kendoMultiSelect");

//		vm.orderSearch.listStatus = $("#requestStatus").data("kendoMultiSelect").value();

		initFormData();
		function initFormData() {
			fillDataTableExt([]);
		}
		vm.doSearch= doSearch;
		function doSearch(){
//			  vm.showDetail = false;
			var grid =vm.exRegManaGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}              
		}
		function onChangeGoodList(){
			if ($("#goodsListGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.orderGoodsDetailSearch = dataItem;
    			fillDataTableGoodsDetail([]);
			}
			
		}
		function fillDataTableExt(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				toolbar: [
		                    {
		                    	name: "actions",
		                        template: '<div class=" pull-left button-radio">'+
		                        '<button class="btn btn-qlk padding-search-right addQLK"'+
		      					'ng-click="vm.createNew()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
		      					'</div>'	
		        				+
		        				'<div class=" pull-left ">'+
		        				'<button class="btn btn-qlk padding-search-right TkQLK"'+
		      					'ng-click="vm.sendToSign()" uib-tooltip="Trình ký" translate>Trình ký</button>'+
		      					'</div>'	
		      					+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.exRegManaGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
							 	vm.count = response.total;
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
							url: Constant.BASE_SERVICE_URL + "orderServiceRest/order/doSearchExportRequirement",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.orderSearch.page = options.page
								vm.orderSearch.pageSize = options.pageSize

								return JSON.stringify(vm.orderSearch)
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
				columns: [{
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			        width: 35,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				},
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#exRegManaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã yêu cầu",
					field: "code",
			        width: 150,
					 template:   '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(#=orderId#)>#=code#</a>',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại yêu cầu",
			        field: "bussinessType",
			        template :  "# if(bussinessType == 1){ #" + "#= 'Xuất cho đơn vị sử dụng' #" + "# } " +
			        "else if (bussinessType == 2) { # " + "#= ' Xuất ra công trình' #"+ "#} " +
			        "else if (bussinessType == 3) { # " + "#= ' Xuất đi BHSC' #"+ "#} " +
			        "else if (bussinessType == 4) { # " + "#= 'Xuất luân chuyển kho' #"+ "#} " +
			        "else if (bussinessType == 5) { # " + "#= 'Xuất tặng đối tác' #"+ "#} " +
			        "else if (bussinessType == 6) { # " + "#= 'Xuất cho đối tác mượn' #"+ "#} " +
			        "else if (bussinessType == 7) { # " + "#= 'Xuất trả đối tác' #"+ "#} " +
			        "else if (bussinessType == 8) { # " + "#= ' Xuất thanh lý' #"+ "#} " +
			        "else if (bussinessType == 9) { # " + "#= ' Xuất cho đề tài/dự án' #"+ "#} " +
			        "else if (bussinessType == 10) { # " + "#= 'Xuất bán' #"+ "#} " +
			        		"#",
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Kho xuất",
			        field: "stockName",
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Người tạo",
					 field: "createdByName",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Đơn vị tạo",
					 field: "createdDeptedName",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Ký CA",
					 field: "signState",
			        template :   "# if(signState == 1){ #" + "#= 'Chưa trình ký' #" + "# } " +
			        "else if (signState == 2) { # " + "#= 'Đã trình ký' #"+ "#} " +
			        "else if (signState == 3) { # " + "#= 'Đã ký' #"+ "#} " +
			        "else if (signState == 4) { # " + "#= 'Đã từ chối' #"+ "#} " +

			        		"#",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Trạng thái",
					 field: "status",
				        template :  "# if(status == 1){ #" + "#= 'Chưa tạo phiếu' #" + "# } " +
				        "else if (status == 2) { # " + "#= 'Đã tạo phiếu' #"+ "#} " +
				        "else if (status == 3) { # " + "#= 'Đã nhập/xuất' #"+ "#} " +
				        "else if (status == 4) { # " + "#= 'Đã hủy' #"+ "#} " +
				        "else if (status == 5) { # " + "#= 'Đã từ chối' #"+ "#} " +
				        		"#",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template:  dataItem =>
					'<div class="text-center #=orderId#""><a '+
					' class=" icon_table #=orderId#"'+
					'  uib-tooltip="Sao chép yêu cầu" '+
					' ng-click=vm.copy(dataItem)>'+'<i class="fa fa-files-o" aria-hidden="true"></i>'+
				'</a>'+
				'<a type="button"'+
				'class="#=orderId# icon_table"  uib-tooltip="Cập nhật yêu cầu" '+
					'>'+
					'<i class="fa fa-pencil" aria-hidden="true"></i>'+
				'</a>'+
				'<a  type="button"'+
				'class="#=orderId# icon_table"  uib-tooltip="Hủy bỏ yêu cầu" '+
					'ng-click=vm.openRemovePopup(dataItem)>'+
				'<i class="fa fa-trash" aria-hidden="true"></i>	'+
				'</a>'
				+'</div>' + ' #" + "# }', 
			        width: 150,
			        field:"stt"
				}]
			});
		}
		function fillDataTableGoodsList(data) {
			vm.goodsListGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				change : onChangeGoodList,
				dataBound: function(e) {
                    var grid = $("#goodsListGrid").data("kendoGrid");
                        grid.select("tr:eq(0)");
               },
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.orderGoodsSearch.page = options.page
								vm.orderGoodsSearch.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsSearch)
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
					title: "STT",
					field:"stt",
			        template: dataItem => $("#goodsListGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
					 field: 'amount',
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
			});
		}
		
		function fillDataTableGoodsDetail(data) {
			vm.goodsDetailGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsDetailServiceRest/orderGoodsDetail/doSearchGoodsDetailForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						    vm.orderGoodsDetailSearch.page = options.page
							vm.orderGoodsDetailSearch.pageSize = options.pageSize
							
							return JSON.stringify(vm.orderGoodsDetailSearch)
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
					title: "STT",
					field:"stt",
			        template: dataItem => $("#goodsDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
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
				}]
			});
		}
		vm.sign = function(){

			//var teamplateUrl = Constant.URL_POPUP['VOFICE_POPUP'];
			var teamplateUrl = "qlk/exportRequestManage/signPopup.html";
			 var title="Trình ký yêu cầu xuất kho";
			 var windowId="EX_SIGN"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'70%','48%'); 
			 
				
			 		 }
		vm.cancel= cancel ;
		function cancel(){
				vm.appParam={};
//				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		function refreshGrid(d) {
			var grid = vm.exReqManaGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.showDetail=function(id){
			var teamplateUrl="qlk/exportRequestManage/detailPopup.html";
			 var title="Chi tiết yêu cầu xuất kho";
			 var windowId="DETAIL_POPUP";
			 vm.order={};
			 exReqManaService.getOrder(id).then(function(d) {
				 vm.order = d.plain();
				// vm.orderGoodsSearch.orderId = vm.order.orderId;*/
				// fillDataTableGoodsList([]);
					CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});	
			 }
		vm.createNew = function(){
			vm.goTo('CREATE_EX_REQ_MANA');
		}
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
		}
		vm.advancedSearch =function(){
			vm.showAdvancedSearch = !vm.showAdvancedSearch;
		}
		vm.dataReasonDelete = [];

		vm.openRemovePopup=openRemovePopup;
		function openRemovePopup(dataItem){
			var reason = {
					status : '1',
					apply : '5'
			}
			exReqManaService.getReasonForComboBox(reason).then(function(d){
				vm.dataReasonDelete =  d.plain();
				var teamplateUrl = Constant.URL_POPUP['DELETE_POPUP'];
				var title="Hủy yêu cầu xuất kho";
				var windowId="EXT"
				dataItem.cancelDate=kendo.toString(new Date(),"yyyy-MM-dd");
				 CommonService.populatePopupCreate(teamplateUrl,title,dataItem,vm,windowId,false,'50%','50%'); 
			});
		}
		
		vm.remove=remove;
		function remove(data){
			confirm('Xác nhận xóa',function (){
				data.cancelReasonApply = "5";
				exReqManaService.remove(data).then(
						function(d) {
							toastr.success("Xóa thành công!");
							vm.doSearch();
							CommonService.dismissPopup();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} 
			)
		}
		vm.openDepartmentCreate=openDepartmentCreate
		function openDepartmentCreate(){
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
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data1, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		
		vm.openDepartmentTo=openDepartmentTo
		function openDepartmentTo(){
			var obj={};
//			obj.page=1;
//			obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data1=result.plain();
				vm.gridOptions1 = kendoConfig.getGridOptions({
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
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions1,data1, vm, 'to', 'ggfd', false, '85%','85%');
			});
		}
		vm.onSave=onSave;
		
		function onSave(data1, popupID){
			if(popupID == 'create'){
				vm.orderSearch.createdDeptedName = data1.text;
				vm.orderSearch.createdDeptedId = data1.code;
			}
			if(popupID == 'to'){
				vm.orderSearch.deptReceiveName = data1.text;
				vm.orderSearch.deptReceiveId = data1.code;
			}
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
        		vm.exRegManaGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.exRegManaGrid.showColumn(column);
            } else {
            	vm.exRegManaGrid.hideColumn(column);
            }
        }
		
		vm.gridColumnShowHideFilter = function (item) { 
            return item.type==null||item.type !=1; 
        };

		
		vm.cancelTime = function()
		{
			vm.orderSearch.createdDateFrom = undefined;
			vm.orderSearch.createdDateTo = undefined;
		}
		vm.cancelListStatus = function()
		{
			vm.orderSearch.listStatus = ['1'];
		}
		vm.cancelListBussinessType = function()
		{
			vm.orderSearch.listBussinessType = [];
		}
		vm.cancelListSignState = function()
		{
			vm.orderSearch.listSignState = [];
		}
		vm.cancelCreptedDepted = function()
		{
			vm.orderSearch.createdDeptedName = undefined;
			vm.orderSearch.createdDeptedId = undefined;

		}
		vm.cancelDeptReceive = function()
		{
			vm.orderSearch.deptReceiveName = undefined;
			vm.orderSearch.deptReceiveId = undefined;
		}
		vm.cancelCreatedByName = function()
		{
			vm.orderSearch.createdByName= undefined;
		}
		
		
		//duplicate
		vm.copy = function(dataItem){
			exReqManaService.dulplicate(dataItem).then(
					function(d) {
						toastr.success("Sao chép yêu cầu thành công!");
						doSearch();
					}, function(errResponse) {
						toastr.error("Lỗi không sao chép được yêu cầu!");
					});
			
		}
	}
})();