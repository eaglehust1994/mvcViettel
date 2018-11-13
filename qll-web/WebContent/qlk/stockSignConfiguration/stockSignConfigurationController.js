(function() {
	'use strict';
	var controllerId = 'stockSignConfigurationController';
	
	angular.module('MetronicApp').controller(controllerId, stockSignConfigurationController);
	
	function stockSignConfigurationController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,stockSignConfigurationService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
                    
        vm.SignData={
        		UnitApproval : "UnitApproval",
        		UnitRequest : "UnitRequest",
        		Creater : "Creater",
        		Shipper : "Shipper",
        		Storekeepers : "storekeepers",
        		ReceiverUnit : "ReceiverUnit",
        		Receiver : "Receiver",
        }
        vm.SignDataCombobox={
        		UnitApproval : "",
        		UnitRequest : "",
        		Creater : "",
        		Shipper : "",
        		Storekeepers : "",
        		ReceiverUnit : "",
        		Receiver : "",
        }
        
        
              
        vm.appParamSearch = {
				status:1,
				parType : 'WMS_BUSS_TYPE'
		};
        
        vm.stockSearch={};
        vm.Search={
        		businessType: 1
		};
        
        vm.selectedStore={
        		storeName: ""
		};
        vm.stockSignList=[];
        vm.templateStock='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
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
		vm.dataStockSignConfigurationType = [];
		initFormData();
		function initFormData() {
			fillDataTable([]);
			getApply();
			/*
			 * stockSignConfigurationService.doSearchBusinessType(vm.stockSignSearch).then(function(result){
			 * vm.stockSignList = result.data; }, function(errResponse){ });
			 */
		}
		
		function getApply(){
			stockSignConfigurationService.getApply(vm.appParamSearch).then(function(result){
				vm.dataStockSignConfigurationType = result.plain();						 
			 }, function(errResponse){  
				toastr.error(gettextCatalog.getString("Xảy ra lỗi khi lấy tham số"));
		     });
		}
		
		function onChange(arg) {	
			 
	        vm.StockGrid = $("#stockListGridABC").data("kendoGrid");     
	        var tr = StockGrid.select().closest("tr");
	        var dataItem = StockGrid.dataItem(tr);
				vm.selectedStore.storeName = "aaa"; 
				var b =	vm.StockRowIndex;
				var a = vm.StockDataRows 
        }
		
		vm.openStock=openStock
		function openStock(){
			var obj={};
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findStockPopup.html';
				var title = gettextCatalog.getString("Danh sách kho");
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
		
		vm.cancelDept = function()
		{
			vm.stockSearch.createdDeptName = undefined;
			vm.stockSearch.createdDeptId = undefined;
		}
		
		vm.doSearchRole= doSearchRole;
		function doSearchRole(){
					var grid =vm.stockListGridABC;	
					if(grid){
						grid.dataSource.query({
							page: 1,
							pageSize: 20
						});
					
			}
		}
		
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
	      '<span>Mã</span>' +
	      '<span>Tên</span>' +
	      	'</div>';
		vm.stockOptions = {
	            dataTextField: "name",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.stockSearch.stockCode = dataItem.code; // thành id
	                vm.stockSearch.stockName = dataItem.name;// thành name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("stockRsServiceRest/" + 'getForAutoCompleteStock').post({name:vm.stockSearch.name,pageSize:vm.stockOptions.pageSize }).then(function(response){
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
	                	vm.stockSearch.stockCode = null; // thành id
	                	vm.stockSearch.stockName = null;// thành name
	                }
	            },
	            ignoreCase: false
	        };
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		function fillDataTable() {
			vm.stockListGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,
				resizable: true,
				change: onChange,
				columnMenu: false,
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
							url: Constant.BASE_SERVICE_URL + "stockRsServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.taxSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.stockSearch.page = options.page
								vm.stockSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockSearch)

						}
					},					 
					pageSize: 20
				},
				/* dataSource: data , */
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
					title: "TT",
					field:"stt",
			        template: dataItem => $("#stockListGridABC").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã kho",
					field: 'code',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên kho",
			        field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị",
			        field: 'departmentName',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		vm.edit=edit;
		function edit(id){			
			var teamplateUrl="qlk/stockSignConfiguration/showHint.html";
			 var title="Hướng dẫn sử dụng chức năng";
			 var windowId="Help"
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.help,vm,windowId,false,'750','370'); 
			
		}
		
		function refreshGrid(d) {
			var grid = vm.taxGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
/* doSearch(); */
		}

		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.taxGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		vm.save= function save(data){
            
            	data.status = '1';
            		reasonService.createReason(data).then(function(result){
            			toastr.success("Cập nhật thành công!");
                        vm.reason.reasonId = result;
                        doSearch();
                        CommonService.dismissPopup();
                        vm.add();
            		}, function(errResponse){
		                if (errResponse.status === 409) {
		                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại!"));
		                } else {
		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi cập nhật danh sách người kí!"));
		                }
    		        });
            	} 
				/*else {
            		reasonService.updateReason(data).then(function(result){
            			toastr.success("Cập nhật thành công!");
            			doSearch();
            			CommonService.dismissPopup();
            		}, function(errResponse){
            			if (errResponse.status === 409) {
		                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại"));
		                } else {
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
		                }
    		        });
            	}*/

	    vm.onStockCategoryChange = function () {

	        $window.alert("Selected Value:");

	    };
		
	}
})();