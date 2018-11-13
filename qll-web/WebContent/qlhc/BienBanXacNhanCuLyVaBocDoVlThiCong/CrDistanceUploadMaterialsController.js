(function() {
	'use strict';

	var controllerId = 'CrDistanceUploadMaterialsController';

	angular.module('MetronicApp').controller(controllerId,
			glAllocationController);
	/ @ngInject /
	function glAllocationController($scope, $rootScope, $timeout, Constant,
			kendoConfig, $kWindow, CommonService, gettextCatalog, Restangular,
			PopupConst, RestEndpoint,$q,crDistanceUploadMaterialsService) {
		var vm = this;
		
		/*bảng vận chuyển bằng ô tô*/
		
		function runService(){
			console.log("run here");
			crDistanceUploadMaterialsService.dichvudemo();
		}
		
		runService();
		
		vm.VanChuyenOto = kendoConfig
				.getGridOptions({
					autoBind : true,
					      //      dataSource: data,
					     //       change: onChange,
					noRecords : true,
					messages : {
						noRecords : gettextCatalog.getString("Không có kết quả nào")
					},
					columns : [
							{
								title : gettextCatalog.getString("STT"),
								field : "a1",
								width : 180,
							},
							{
								title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								template : "<input type='checkbox' name='gridcheckbox' />",
								width : 35
							},
							{
								title : gettextCatalog.getString("Loại vật tư vận chuyển"),
								field : "a3",
								width : 180,
							},
							{
								title : gettextCatalog.getString("Đơn vị tính"),
								field : "a4",
								width : 180
							},
							{
								title : gettextCatalog
										.getString("Khối lượng"),
								field : "a5",
								width : 180
							},
							{
								title : gettextCatalog.getString("Cự li vận chuyển(km)"),
								field : "a6",
								width : 180
							},
							{
								title : gettextCatalog
										.getString("Loại đường"),
								field : "a7",
								width : 180
							}, {
								title : gettextCatalog.getString("Trọng tải xe"),
								field : "a8",
								width : 180
							} ]
				});
	
		
		
		
		
		/* vận chuyển bằng xe cút kít */
		vm.VanChuyenXeCutKit = kendoConfig
		.getGridOptions({
			autoBind : true,
			//           dataSource: data,
			//            change: onChange,
			noRecords : true,
			messages : {
				noRecords : gettextCatalog
						.getString("Không có kết quả nào")
			},
			columns : [
					{
						title : gettextCatalog.getString("STT"),
						field : "adOrgName",
						width : 180,
					},
					{
						title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
						template : "<input type='checkbox' name='gridcheckbox' />",
						width : 35
					},
					{
						title : gettextCatalog.getString("Loại vật tư vận chuyển"),
						field : "sua",
						width : 180,
					},
					{
						title : gettextCatalog.getString("Đơn vị tính"),
						field : "maphieu",
						width : 180
					},
					{
						title : gettextCatalog
								.getString("Khối lượng"),
						field : "macongtrinh",
						width : 180
					},
					{
						title : gettextCatalog.getString("Cự li vận chuyển(km)"),
						field : "mahopdong",
						width : 180
					},
					{
						title : gettextCatalog
								.getString("Loại đường"),
						field : "tenhopdong",
						width : 180
					}, {
						title : gettextCatalog.getString("Độ dốc"),
						field : "tenhopdong",
						width : 180
					} ]
		});
		
		/*bảng bốc dỡ thủ công*/
		
		vm.BocDoThuCong = kendoConfig
		.getGridOptions({
			autoBind : true,
			//            dataSource: data,
			//            change: onChange,
			noRecords : true,
			messages : {
				noRecords : gettextCatalog
						.getString("Không có kết quả nào")
			},
			columns : [
					{
						title : gettextCatalog.getString("STT"),
						field : "adOrgName",
						width : 180,
					},
					{
						title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
						template : "<input type='checkbox' name='gridcheckbox' />",
						width : 35
					},
					{
						title : gettextCatalog.getString("Loại vật tư vận chuyển"),
						field : "sua",
						width : 180,
					},
					{
						title : gettextCatalog.getString("Đơn vị tính"),
						field : "maphieu",
						width : 180
					},
					{
						title : gettextCatalog
								.getString("Khối lượng"),
						field : "macongtrinh",
						width : 180
					},
					{
						title : gettextCatalog.getString("Ghi chú"),
						field : "mahopdong",
						width : 180
					} ]
		});

	}
})();