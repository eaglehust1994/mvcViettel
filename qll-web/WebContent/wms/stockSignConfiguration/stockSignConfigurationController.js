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
        vm.stockSearch={};
        vm.Search={
			businessType: '01'	
		};
        vm.dataStock = [];
        vm.appParams={};
		vm.appParams.parType = 'WMS_BUSS_TYPE';
		vm.appParams.page = 1;
		vm.appParams.pageSize = 10;
		vm.businessTypes =[];
		vm.flag=true;
		vm.list=[];
		vm.temp=[];
		vm.search={};
		vm.obj={};
		var unsaved = true;
		$('#checkout').attr('disabled', true);
//		Khởi tạo dữ liệu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
			
//		Sự kiện click vào từng kho
		vm.oldIndex=null;
		vm.onchange = onChange;
		function onChange(arg) {
			if(vm.change){
				confirmSave();
				return;
			}
			
			if (vm.stockListGridABC.select().length > 0 ) {
				var tr = vm.stockListGridABC.select().closest("tr");
				var dataItem = vm.stockListGridABC.dataItem(tr);
				vm.itemDetail = dataItem;
				document.getElementById('storeNameSign').innerHTML = dataItem.code;
		
				vm.search.stockId= dataItem.stockId;
				vm.search.bussTypeId = vm.Search.businessType;
				var selectedRow = vm.stockListGridABC.select();
				vm.oldIndex = selectedRow.index();
				stockSignConfigurationService.getDataByID(vm.search).then(function(result){
					vm.flag=false;
					vm.list = result.data;
					vm.temp = result.data;
				}, function(errResponse){  
					toastr.error(gettextCatalog.getString("Xảy ra lỗi khi lấy người ký"));
			     });
			}
        }

//		Su kien thay doi Loai nghiep vu trinh ky
		vm.onChangeBuss = function(){
			//var grid = $("#stockListGridABC").data("kendoGrid");    
			var grid =vm.stockListGridABC;				
				if (grid) {
					if (!vm.rowIndex) {
						grid.select("tr:eq(1)");
					} else {
						grid.select("tr:eq(" + vm.rowIndex + ")");
					}
				}
		}
		
//		Hiện nút Save khi thay đổi dữ liệu
		$scope.$watchCollection('vm.list', function(newValues, oldValues, scope) {
			if(!vm.flag){
				$('#checkout').attr('disabled', true);
				vm.flag=true;
			} else {
				$('#checkout').attr('disabled', false);
			}
		});
		
		vm.change=false;
		$scope.$on('changeData', function(event) { 
			$('#checkout').attr('disabled', false);
			vm.change=true;
		});
		
		vm.doSearchRole= doSearchRole;
		function doSearchRole(){
			var grid =vm.stockListGridABC;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
					
			}
		}
		
//		Xác nhận khi chưa lưu dữ liệu
		vm.oldValue=false;
		vm.confirmSave=confirmSave;
		function confirmSave(){
			confirm('Dữ liệu chưa lưu, bạn có muốn thoát?',function(){			
				var grid =vm.stockListGridABC;
				vm.change=false;
				vm.oldValue=true;
				vm.rowIndex=vm.oldIndex;
				//grid.tbody.find('tr:eq(' + i + ')');
				grid.select("tr:eq(" + vm.rowIndex + ")");
				$('#stockListGridABC').data('kendoGrid').dataSource.read();
				$('#stockListGridABC').data('kendoGrid').refresh();
				grid.refresh();						 
			})
		}
		
//		Lấy ra nghiệp vụ trình ký
		stockSignConfigurationService.doSearchBusinessType(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
		});
		
//		Lấy dữ liệu từ bảng Stock
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		var record = 0;
		function fillDataTable() {
			vm.stockListGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,
				resizable: false,
				columnMenu: false,
				scrollable:false,
				change: onChange,
				dataSource:{
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
							url: Constant.BASE_SERVICE_URL + "stockDailyImportExportServiceRest/configSignVoffice/doSearchStock",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockSearch.page = options.page
								vm.stockSearch.pageSize = options.pageSize  
								return JSON.stringify(vm.stockSearch)
						}
					},					 
					pageSize: 10
				},
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
				},
				dataBound: function (e) {
					//var grid = $("#stockListGridABC").data("kendoGrid"); 
					var grid =vm.stockListGridABC;					
					if (grid) {
						if (!vm.rowIndex) {
							grid.select("tr:eq(1)");
						} else {
							grid.select("tr:eq(" + vm.rowIndex + ")");
						}
					}
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
					width: '7%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Mã kho",
					field: 'code',
			        width: '20%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên kho",
			        field: 'name',
			        width: '48%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Ðơn vị quản lý",
			        field: 'departmentName',
			        width: '25%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "ID",
			        field: 'stockId',
			        hidden: true,
				}]
			});
		}
		
//		Bật popup hướng dẫn sử dụng chức năng
		vm.edit=edit;
		function edit(id){			
			var teamplateUrl="wms/stockSignConfiguration/showHint.html";
			 var title="Hướng dẫn sử dụng chức năng";
			 var windowId="Help"
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.help,vm,windowId,false,'750','370'); 
			
		}
		
//		refresh du lieu
		vm.refresh=refreshGrid;
		function refreshGrid(d) {
			var grid = vm.stockListGridABC;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
//		cancel popup huong dan
		vm.cancelSign= cancelSign ;
		function cancelSign(){
			CommonService.closePopup1();
		}

//		Tìm kiếm
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid = vm.stockListGridABC;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		
//		Luu dữ liệu người ký vào bảng CONFIG_SIGN_VOFFICE
		vm.save= save;
		function save(){
			for(var i=0; i<vm.list.length;i++){
				if((vm.list[i].configSignVofficeId == null)){
					stockSignConfigurationService.addStockSignConfiguration(vm.list[i]).then(function(result){	
					}, function(errResponse){
							toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thêm mới"));
							return;
					});
					if(i==vm.list.length-1) {toastr.success("Thêm mới thành công!");}
					vm.change = false;
				}
				else {					
					stockSignConfigurationService.updateStockSignConfiguration(vm.list[i]).then(function(result){	
					}, function(errResponse){
							toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
							return;
					});
					if(i==vm.list.length-1) {toastr.success("Cập nhật thành công!");}
					vm.change = false;						
				}
			}	
		}
		
		setTimeout(function(){
			  $("#filterText").focus();
			},15);
	}
})();
