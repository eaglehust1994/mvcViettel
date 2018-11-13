(function() {
	'use strict';
	var controllerId = 'stock_dailyController';
	
	angular.module('MetronicApp').controller(controllerId, stock_dailyController);
	
	function stock_dailyController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTable = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stock_dailySearch={
				status:2,
				status1:2
		};
		vm.stock_daily={};
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
		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDataTablePopup([]);
			fillDataTablePopup2([]);
		}

		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
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
							url: Constant.SERVICE_URL + "stock_dailyRsServiceRest/stock_daily/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              //  vm.stock_dailySearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.stock_dailySearch.page = options.page
								vm.stock_dailySearch.pageSize = options.pageSize

								return JSON.stringify(vm.stock_dailySearch)

						}
					},					 
					pageSize: 20
				},
				
				//dataSource: data,
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
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			        width: 20,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				},
				{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#stock_dailyGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Kho",
					field: 'code',
			        width: 60,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hàng",
			        field: 'name',
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'managementDetail',
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'level',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Tình trạng",
			        field: 'type',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Số lượng tồn kho",
			        field: 'type',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Số lượng yêu cầu",
			        field: 'type',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Còn dư",
			        field: 'type',
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
		
		//fill data popup
		function fillDataTablePopup(data) {
			vm.merEntityGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				dataSource: vm.dataMerEntity,
				noRecords: true,
                resizable: true,                    
				messages: {
					noRecords: gettextCatalog.getString("There is no data on current page")
				},
				
				columns: [{
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			        width: 35,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				},
				{
					title: gettextCatalog.getString("TT"),
					field: 'lineNo',
					width: 68,
				}, {
					title: gettextCatalog.getString("Mã kho"),
					field: "code",
					width: 68
				}, {
					title: gettextCatalog.getString("Tên kho"),
					field: "name",
					width: 120
				}, {
					title: gettextCatalog.getString("Đơn vị quản lý"),
					field: "unitName",						
					width: 100
				}, {
					title: gettextCatalog.getString("Chọn"),
					field: "merWeight",
					width: 50
				}, ]
			});
		}
		
		function fillDataTablePopup2(data) {
			vm.constructionAcceptance = kendoConfig.getGridOptions({
				autoBind: true,
				dataSource: vm.dataConstructionAcceptance,
				noRecords: true,
				messages: {
					noRecords: gettextCatalog.getString("There is no data on current page")
				},
				columns: [{
					title: gettextCatalog.getString("TT"),
					field: "lineNo",
					width: 50,
				}, {
					title: gettextCatalog.getString("Mã hàng"),
					field: "code",
					width: 60
				}, {
					title: gettextCatalog.getString("Tên hàng"),
					field: "name",
					width: 120
				}, {
					title: gettextCatalog.getString("Đơn vị tính"),
					field: "merWeight",
					width: 60
				}, {
					title: gettextCatalog.getString("Chọn"),
					field: "vndUnit",
					width: 50
				}]
			});
		}
		
		//ShowPopup
		vm.showPopup = function(popupId){
			if (popupId === 'merEntity'){
				var templateUrl = 'qlk/stock_daily/stock_dailyPopup.html';
				var title = "Danh sách kho hàng" + "<button id='checkPopup' class='btn btn-link' ng-click=vm.showPopup1('checkPopup')> Đã chọn</button>";
				CommonService.populateDataToGrid(templateUrl, title, vm.merEntityGridOptions, vm, 'merEntity');
			}
			else{
				var templateUrl = 'qlk/stock_daily/stock_dailyPopup.html';
				var title = "Danh sách hàng hoá" + "<button id='checkPopup' class='btn btn-link' ng-click=vm.showPopup1('checkPopup')> Đã chọn</button>";
				CommonService.populateDataToGrid(templateUrl, title, vm.constructionAcceptance, vm, 'merEntity1');
			}
		}
		
		function refreshGrid(d) {
			var grid = vm.stock_dailyGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			vm.stock_daily={};
			vm.showDetail = false;
		}
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.stock_dailySearch={
					goods_state_name:"2",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.stock_dailyGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
	/*	vm.exportExcel=function(){
			detailCatAssetUpdateService.exportExcel(vm.stock_dailySearch).then(
					function() {
						window.location = "fileservice/download?fileName=" + data.fileName;
						toastr.success("Export thành công");
					}, function(errResponse) {
						toastr.error("Lỗi~~~~~~~~");
					});
		}*/

		$("#type1").kendoMultiSelect().data("kendoMultiSelect");
		$("#goodsType").kendoMultiSelect().data("kendoMultiSelect");
		
		//clear form
		vm.cancelStock = function(id) {
			if(id==="clearStock"){
				$('#name').val('');
			}
			else if (id==="clearGoods"){
				$('#goods_name').val('');
			}
			else if (id==="clearType"){
				$('#type1').data("kendoMultiSelect").value([]);
			}
			else if(id==="clearGoodsType"){
				$('#goodsType').data("kendoMultiSelect").value([]);
			}
		}
		
		// autoComplete
		var data = [
			"Albania",
            "Belarus",
            "Bulgaria",
            "Croatia",
            "Cyprus",
            "Denmark",
            "Estonia",
            "Finland",
            "France",
            "Georgia",
            "Germany",
            "Hungary",
            "Iceland",
            "Italy",
            "Kosovo",
            "Latvia",
            "Luxembourg",
            "Montenegro",
            "Netherlands",
            "Poland",
            "Romania",
            "Spain",
            "Turkey",
            "United Kingdom",
            "Viet Nam"
        ];

		$("#stock_name").kendoAutoComplete({
			animation: {
				   close: {effects: "fadeOut zoom:out",duration: 300},
				   open: {effects: "fadeIn zoom:in",duration: 300}
			},
            dataSource: data,
        });
		$("#goods_name").kendoAutoComplete({
			animation: {
				   close: {effects: "fadeOut zoom:out",duration: 300},
				   open: {effects: "fadeIn zoom:in",duration: 300}
			},
            dataSource: data,
        });
	}
})();

