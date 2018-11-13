(function() {
	'use strict';
	var controllerId = 'stockGoodsController';
	
	angular.module('MetronicApp').controller(controllerId, stockGoodsController);
	
	function stockGoodsController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, 
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stockGoodsSearch={
				goodsStateName:2,
				goodsIsSerial:0
		};
		vm.stockGoods={};
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
		}
		
		function fillDataTable(data) {
			vm.stockGoodsGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:{
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsKpiServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.stockGoodsSearch.page = options.page
								vm.stockGoodsSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockGoodsSearch)

						}
					},					 
					pageSize: 20
				},
				// dataSource: data,
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
			        template: dataItem => $("#stockGoodsGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title: "Kho",
					field: 'stockName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hàng",
			        field: 'goodsCode',
			        width: 100,
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
					}, {
					title: "Hợp đồng",
					field: 'contractCode',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Dự án",
					field: 'projectCode',
			        width: 100,
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
					title: "Tình trạng hàng hoá",
					 field: 'goodsStateName',
					 width: 200,
					 template :  
					        "# if(goodsStateName == 0){ #" + "#= 'Hỏng' #" + "# } " +
					        "else if (goodsStateName == 1) { # " + "#= 'Bình thường' #"+ "#} " +
					        "#",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Serial",
						field: 'serial',
				        width: 100,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title: "Xuất kèm Serial",
						 field: 'goodsIsSerial',
						 width: 150,
						 template :  
						        "# if(goodsIsSerial == 0){ #" + "#= 'Không' #" + "# } " +
						        "else if (goodsIsSerial == 1) { # " + "#= 'Có' #"+ "#} " +
						        "#",
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},
				]
			});
		}
		
		function refreshGrid(d) {
			var grid = vm.stockGoodsGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			vm.stockGoods={};
			vm.showDetail = false;
		}
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.stockGoodsSearch={
					status:"2",
					serial:"0"
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.stockGoodsGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		$("#goodsType").kendoMultiSelect().data("kendoMultiSelect");
		
		// clear form
		vm.cancelStock = function(id) {
			if(id==="clearStock"){
				$('#stocks_name').val('');
			}
			else if (id==="clearGood"){
				$('#good_name').val('');
			}
			else if(id==="deselect-all"){
				vm.stockGoodsSearch.listGoodsType=[];
			}
		}
	}
})();