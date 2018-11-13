(function() {
	'use strict';

	var controllerId = 'CrLookingWorkTranstationController';

	angular.module('MetronicApp').controller(controllerId,
			glAllocationController);
	/ @ngInject /
	function glAllocationController($scope, $rootScope, $timeout, Constant,
			kendoConfig, $kWindow, CommonService, gettextCatalog, Restangular,
			PopupConst, RestEndpoint,$q) {
		var vm = this;
		loadDataTable1();
		
		function fillDataTable1(data) {
			var deferred = $q.defer();
		vm.gridOptions = kendoConfig
				.getGridOptions({
					autoBind : true,
					          dataSource: data,
					//            change: onChange,
					noRecords : true,
					messages : {
						noRecords : gettextCatalog
								.getString("Không có kết quả nào")
					},
					columns : [
							{
								title : gettextCatalog.getString("STT"),
								field : "a1",
								width : 180,
							},
							/*{
								title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								template : "<input type='checkbox' name='gridcheckbox' />",
								width : 35
							},*/
							{
								title : gettextCatalog.getString("Ngiệm thu"),
								field : "a3",
								width : 120,
							},
							{
								title : gettextCatalog.getString("Hạng mục"),
								field : "a4",
								width : 180
							},
							{
								title : gettextCatalog.getString("Nội dung phát sinh"),
								field : "a5",
								width : 180
							},
							{
								title : gettextCatalog.getString("Đơn vị tính"),
								field : "a6",
								width : 180
							},
							{
								title : gettextCatalog.getString("Khối lượng phát sinh"),
								field : "a7",
								width : 180
							}, {
								title : gettextCatalog.getString("Ghi chú"),
								field : "a8",
								width : 180
							} ]
				});
		deferred.resolve('done');
		return deferred.promise;
	}
		function loadDataTable1() {
			var d = [ {
				"a1" : "1",
				"a2" : "4",
				"a3" : "5",
				"a4" : "6",
				"a5" : "7",
				"a6" : "8",
				"a7" : "8",
				"a8" : "8"
			} ];
			fillDataTable1(d).then(function(result) {
				try {
					var grit = vm.reportGrid;
					if (grit) {
						grit.dataSource.data(d.plain());
						vm.contractGrid.refresh();
					}
				} catch (e) {
					console.log(e);
				}
			});
		}
	}
})();