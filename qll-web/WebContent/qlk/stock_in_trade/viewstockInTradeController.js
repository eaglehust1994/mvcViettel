(function() {
	'use strict';
	var controllerId = 'viewstockInTradeController';
	
	angular.module('MetronicApp').controller(controllerId, viewstockInTradeController);
	
	function viewstockInTradeController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, viewstockInTradeService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        
        var now = new Date();
		var day = now.getDate();
		var month = now.getUTCMonth();
		var year = now.getFullYear();
		
		$('#createFromStockTrade').kendoDatePicker({
			value : day +"/"+ month +"/"+ year,
			format: "dd/MM/yyyy",
			animation : {
				close : {
					effects : "zoom:out",
					duration : 300
				}
			}
		}); 
		
		vm.stockTradeSearch={
				goodsState:'0',
		};
		vm.stockTrade={};
		
		vm.detailTradeSearch = {};
		
		vm.shipmentGoodsSearch = {
				
		}
		
		
		vm.stockTradeSearchType = [{
			
		}];
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
	      '<span>Mã</span>' +
	      '<span>Tên</span>' +
	      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		  vm.templateGoods='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
	        '<span class="k-state-default"><p>#: data.name #</p></span>',
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
		
		vm.doSearch= doSearch;
		function doSearch(){
			if(formValidate()){
				fillDataTable([]);
				vm.showDetail = false;
				var grid = vm.stockTradeGrid;	
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 20
					});
				}
			}else{
				fillDataTable([]);
				vm.showDetail = false;
				var grid = vm.stockTradeGrid;	
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 20
					});
				}
			}
			
		}
		var message = {
				stockName: gettextCatalog.getString("Chọn kho trước khi tìm kiếm")	
			}
			function formValidate(){
				if(vm.stockTradeSearch.stockName==null || vm.stockTradeSearch.stockName ==''){
					toastr.warning(gettextCatalog.getString(message.stockName));
					return false;
				}else{
					return true;
				}
			}
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDetailTable([]);
			viewstockInTradeService.doSearchTotal(vm.stockTradeSearch).then(function(result){
				 vm.stockTradeSearchType = result.data;						 
    		 }, function(errResponse){             
		     });
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
	
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				toolbar: [
                    {
                        name: "actions",
                        template: 
                        	 '<div class=" pull-left ">'+
                        	 '<button class="btn btn-qlk padding-search-right noteQLK width125"'+
 	      					'ng-click="vm.exportST()" uib-tooltip="Xuất thẻ kho" translate>Xuất thẻ kho</button>'+
           					'</div>'	
             				+
                           	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                         '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                         '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                         '<label ng-repeat="column in vm.stockTradeGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                         '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                         '</label>'+
                         '</div></div>'
                    }
                    ],
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsTotalServiceRest/doSearchGoodTotal",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockTradeSearch.page = options.page;
								vm.stockTradeSearch.pageSize = options.pageSize;

								return JSON.stringify(vm.stockTradeSearch);

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
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			        width: "5%",
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				},{
					title: "STT",
					field:"5%",
			        template: dataItem => $("#stockTradeGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
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
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại hàng hóa",
			        field: 'goodsTypeName',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hàng",
			        field: 'goodsCode',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Số lượng",
			        field: 'amount',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				, {
					title: "Tình trạng hàng hóa",
					field: 'goodsState',
			        template :  "# if(goodsState == 1){ #" + "#= 'Bình thường' #" + "# } " + "else if (goodsState == 2) { # " + "#= 'Hỏng' #"+ "#} #",
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					
				},{
					title: "Chi tiết hàng hóa",
					 field: 'chitiet',
			        template :  '<a  href="javascript:void(0);" ng-click=vm.seeDetail(dataItem)>Chi tiết</a>',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
			
		function fillDetailTable(data) {
			vm.detailGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
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
			        template: dataItem => $("#viewDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
				}, {
					title: "Hãng sản xuất",
			        field: 'manufacturerName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Nước sản xuất",
			        field: 'producingCountryName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				, {
					title: "Vị trí",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Bình thường' #" + "# } " + "else if (status == 1) { # " + "#= 'Hỏng' #"+ "#} #",
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
		
		function refreshGrid(d) {
			var grid = vm.stockGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
	
		 
		vm.exportST = exportST;
		function exportST(){
			 var teamplateUrl="qlk/stock_in_trade/exportCardStockPopup.html";
			 var title="Xuất thông tin thẻ kho";
			 var windowId="VIEW_STOCK_IN_TRADE"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'600','150'); 
		} 
		
		vm.edit=edit;
		function edit(id){
			vm.showDetail = true;
			var grid = vm.stockGrid;
			var item = grid.table.find("tr div." +id);
			var uid = $(item).parent().parent().attr("data-uid");
			var dataItem = grid.dataSource.getByUid(uid);
			vm.stock = dataItem;
			var teamplateUrl = "erp/stock/stockPopup.html";
			 var title = "Chỉnh sửa tham số";
			 var windowId = "STOCK"
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.stock,vm,windowId,true,'800','400'); 
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
		
		vm.save = save;
		function save(data,isCreateNew){
                	if(isCreateNew) {
                		stockService.createStock(data).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.stock.stockId = result;
                            doSearch();
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
                		stockService.updateStock(data).then(function(result){
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
		
		vm.cancel= cancel ;
		function cancel(){
			
		}
				
		vm.canceldoSearch= function (){
			
			 vm.showDetail = false;
			vm.stockTradeSearch={
					status:"1",
			};
			doSearch();
		}
			
		vm.seeDetail=seeDetail;
		function seeDetail(dataItem){
			   var teamplateUrl="qlk/stock_in_trade/viewDetailTrade.html";
			    var title="Thông tin chi tiết hàng hóa";
			    var windowId="DETAIL";
			    vm.detailTradeSearch = dataItem;
			    vm.detailTradeSearch.goodsCode = dataItem.goodsCode;
			    fillDetailTable([vm.detailTradeSearch]);
			    CommonService.populatePopupCreate(teamplateUrl,title,vm.detailTradeSearch,vm,windowId,false,'60%','40%');
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.stockGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.stockGrid.showColumn(column);
            } else {
            	vm.stockGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.gridColumnShowHideFilter = function (item) { 
            return item.type==null||item.type !=1; 
		};
	}
})();