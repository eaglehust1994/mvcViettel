(function() {
	'use strict';

	var controllerId = 'listController';

	angular.module('MetronicApp').controller(controllerId, listController);

	/* @ngInject */
	function listController($scope, $rootScope, $timeout, Constant,
			kendoConfig, gettextCatalog, $kWindow, $q, list_report_services) {
		var vm = this;
		vm.changeSite = changeSite;
		dataForListPersonSign();

		function fillDataListPersonSign(data) {
			var deferred = $q.defer();
			vm.gridOptionsSign = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						// data.plain(),
						//change : onChange,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
								{
									title : gettextCatalog.getString("Thứ tự ký"),
									field : "signOrder",
									width : 30
								},
								{
									template : '<button class="btn btn-primary" style="margin-left: 35%;" ng-click="vm.updateCategoryAcceptance()"><span>Up</span></button>',
									width : 20
								},{
									title : gettextCatalog.getString("Vai trò"),
									field: "role",
									width : 70
								},{
									title : gettextCatalog.getString("Họ tên"),
									field: "fullName",
									width : 70
								},{
									title : gettextCatalog.getString("Email"),
									field: "email",
									width : 70
								}]
					});
			deferred.resolve('done');
			return deferred.promise;
		}

		function dataForListPersonSign() {
			var d = [ {
				"signOrder" : "1",
				"role" : "Phụ trách kỹ thuật",
				"fullName" : "Dương Tiến Nam",
				"email" : "namdt@itsolt.vn"
			},{
				"signOrder" : "2",
				"role" : "Phụ trách thi công",
				"fullName" : "Lê Tiến Cương",
				"email" : "cuonglt@itsol.vn"
			} ];
			fillDataListPersonSign(d).then(function(result) {
				try {
					var grit = vm.listPersonSign;
					if (grit) {
						grit.dataSource.data(d.plain());
						vm.listPersonSign.refresh();
					}
				} catch (e) {
					console.log(e);
				}
			});
		}
		
		function fillDataListFileSign(data) {
			var deferred = $q.defer();
			vm.gridOptionsFileSign = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						// data.plain(),
						//change : onChange,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
								{
									title : gettextCatalog.getString("STT"),
									field : "order",
									width : 30
								},
								{
									title : gettextCatalog.getString("Tên file"),									
									field : 'fileName',
									template : function(dataItem) {
										return '<a href='+dataItem.fileName+'>'+dataItem.fileName+'</a>';
									},
									width : 200
								}]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
		
		dataForListFileSign();
		function dataForListFileSign() {
			var d = [ {
				"order" : "1",
				"fileName" : "BTS_HNI_00001/NTBGCT_0001.pdf"			
			},{
				"order" : "2",
				"fileName" : "BTS_HNI_00001/NTBGCT_0002.pdf"
			} ];
			fillDataListFileSign(d).then(function(result) {
				try {
					var grit = vm.listFileSign;
					if (grit) {
						grit.dataSource.data(d.plain());
						vm.listPersonSign.refresh();
					}
				} catch (e) {
					console.log(e);
				}
			});
		}
		

		function changeSite() {
			list_report_services.goTo();
		}
	}
})();