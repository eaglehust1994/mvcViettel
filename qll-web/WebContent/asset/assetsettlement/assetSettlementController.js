(function() {
	'use strict';

	var controllerId = 'assetSettlementController';

	angular.module('MetronicApp').controller(controllerId,
			assetSettlementController);

	/* @ngInject */
	// function assetSettlementController($scope, $rootScope, $timeout,
	// Constant, gettextCatalog, kendoConfig, $q, RestEndpoint, Restangular,
	// $kWindow,longTermAssetEntityService, settlementService) {
	function assetSettlementController($scope, $rootScope, $timeout,
			Constant, gettextCatalog, kendoConfig, $kWindow) {
		var vm = this;
		vm.detail = detail;
		vm.save = save;


		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
						autoBind : true,
						dataSource : data,						
						change : onChange,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("There is no data on current page")
						},
						columns : [
								{									 
									title: gettextCatalog.getString("STT"),
									field : "stt",
									width :50
								},
								{
									title : gettextCatalog.getString("Hạng mục chi phí"),
									field : "name",
									width : 180
								},
								{
									title : gettextCatalog.getString("Số chứng từ"),
									field : "code",
									width : 200
								},
								{
									title : gettextCatalog.getString("Ngày chứng từ"),
									field : "code",
									width : 200
								},
								{
									title : gettextCatalog.getString("File đính kèm"),
									field : "itemCode",
									width : 200
								}]
					});
		}	

	
		fetchAllData();
		function fetchAllData() {
			var d= [];
			fillDataTable(d);
//			clearingapService.fetchAllClearingAps().then(function(d) {
//				fillDataTable(d.plain());
//				
//				setTimeout(function () {               	     
//           	 	var grid = vm.clearingapGrid;
//				grid.select("tr:eq(0)");   
//					}, 50);
//			}, function(errResponse) {
//				console.error('Error while fetching clearingaps');
//			});
		}
//		function reloadCategorylongTermAssetEntity(){
//			longTermAssetEntityService.getAllCategorylongTermAssetEntity(vm.listcategory.constructId).then(function(d) {
//				//loadDataTable(d.plain());
//				refreshGrid(d.plain());
//				vm.showCreat = false;
//			}, function(errResponse) {
//				console.error('Error while fetching object type');
//			});
//		}
		
		
		vm.remove = remove;
		function remove(){
//			var grid = vm.tabledata;
//			if (grid.select() == null || grid.select().length == 0) {
//        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
//        		return;
//        	}
//			var tr = grid.select().closest("tr");
//			var dataItem = grid.dataItem(tr);
//			if (vm.tabledata.select().length > 0 && confirm('Xác nhận xóa')) {
//            	longTermAssetEntityService.deleteCategorylongTermAssetEntityId(dataItem.categorylongTermAssetEntityId).then(function(){
//	            		if (vm.showCreat){
//	            			toastr.success("Xóa thành công!");
//	            			nextRow();
//	            		}else{	            			
//		            		reloadCategorylongTermAssetEntity();
//	            		}
//		            }, function(errResponse){
//		            	if (errResponse.status == 302){
//		            		toastr.error(gettextCatalog.getString("Bản ghi đang được sử dụng!"));
//		            	}else{
//		            		toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình xóa!"));
//		            	}
//	                }
//            	);
//            }else{
//        		toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình xóa!"));
//        	}
//			
		}
	

		function onChange(){
			if (vm.tabledata.select().length > 0) {
				var tr = vm.tabledata.select().closest("tr");
				var dataItem = vm.tabledata.dataItem(tr);
				vm.listlongTermAssetEntity = dataItem;
			}
		}

		function refreshGrid(d) {
			var grid = vm.tabledata;
		if(grid){
			vm.tabledata.dataSource.data(d);
			vm.tabledata.refresh();}
		}
		
		function detail() {
			if (vm.tabledata.select().length > 0) {
				vm.showDetail = true;
			} else {
				toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
			}
		}

		function save(){}		

	}
})();