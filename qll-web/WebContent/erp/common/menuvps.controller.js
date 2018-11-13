(function() {
	'use strict';

	var controllerId = 'MenuController';

	angular.module('MetronicApp').controller(controllerId, MenuController);

	/* @ngInject */
	function MenuController($scope, $rootScope, Constant, $http, Restangular,
			CommonService) {
		var vm = this;
		$scope.Constant = Constant;
		
		$scope.$watch(function() {
			return $rootScope.casUser;
		}, function(casUser) {
			if (casUser == null) {
				return;
			}
			var checkRole;
			if (casUser != null) {
				// CommonService.getUserRoles(casUser.employeeCode).then
				
			}
			
			
//			CommonService.getUserRoles(casUser.employeeCode).then(function(result) {
//				$rootScope.RoleUser=result.roleCode;
//			},
//			function(errResponse) {
//				toastr.error(gettextCatalog
//						.getString("Lỗi không xác định!"));
//				return false;
//			});
			
			/**
			 * Check user roles
			 */
			$rootScope.checkValue="";
			$rootScope.checkValue2="";
//			$rootScope.checkValue=$rootScope.checkValue2;
			
			CommonService.checkRole(casUser.employeeCode).then(function(result) {
				$rootScope.RoleMenu=result;
				$rootScope.checkUser=true;
				//check reload pages or logIn pages
				if (window.performance) {
					  console.info("window.performance works fine on this browser");
					}
					  if (performance.navigation.type == 1) {
					    console.info( "This page is reloaded" );
					  } else {
					    vm.dangNhapObj={};
						vm.dangNhapObj.ipDangNhap=result.ipAdrr;
						vm.dangNhapObj.userCode=casUser.employeeCode;
						vm.dangNhapObj.gioDangNhap=kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy HH:mm:ss");
						vm.dangNhapObj.trangThai="Đăng nhập thành công";
						CommonService.insertLSDN(vm.dangNhapObj).then(function(result) {
									toastr.success("Save successful login session!!!");
								},
								function(errResponse) {
									alert('Lỗi không xác định!!!');
									return;
								});
					  }
						
					toastr.success("Get menu success!!!");
					},
			function(errResponse) {
				$rootScope.checkUser=false;
				
				alert('Tài khoản không tồn tại trong hệ thống của công ty công trình');
				return;
			});
			
			console.log(casUser);
//			debugger;
//			for (var i = 0; i < casUser.parentMenu.length; i++) {
//				switch (casUser.parentMenu[i].code) {
//				case "WMS_CAT": {
//					casUser.parentMenu[i].classIcon = "dmgl";
//					break;
//				}
//				case "WMS_SHIPMENT": {
//					casUser.parentMenu[i].classIcon = "qlLohang";
//					break;
//				}
//				case "WMS_INPUT": {
//					casUser.parentMenu[i].classIcon = "qlNhapKho";
//					break;
//				}
//				case "WMS_OUTPUT": {
//					casUser.parentMenu[i].classIcon = "qlXuatkho";
//					break;
//				}
//				case "WMS_INVENTORY": {
//					casUser.parentMenu[i].classIcon = "qlTonkho";
//					break;
//				}
//				case "WMS_TOOL": {
//					casUser.parentMenu[i].classIcon = "qlTienIch";
//					break;
//				}
//				case "WMS_REPORT": {
//					casUser.parentMenu[i].classIcon = "qlBaoCao";
//					break;
//				}
//				}
//			}
			
			vm.menuObjects = casUser.parentMenu;

		})

		vm.goTo = goTo;

		/*
		 * get menu text - neu vsa tra ve null thi
		 */
		vm.getMenuText = function(menuObject) {
			try {
				if (menuObject.url == null) {
					return CommonService.translate(menuObject.name);
					/* return menuObject.name; */
				}
				var template = Constant.getTemplateUrl(menuObject.url);
				if (template == null) {
					if (menuObject.name != '') {
						return CommonService.translate(menuObject.code);

					}
					return "N/A";
				}
				return template.title;
			} catch (err) {
				console.debug(err);
				return "N/A";
			}
		}

		/* Handle action client on a menu item */
		function goTo(menuKey) {
			var template = Constant.getTemplateUrl(menuKey);
			console.debug("postal", postal);
			postal.publish({
				channel : "Tab",
				topic : "open",
				data : template
			});
		}
		vm.activeHomePage = activeHomePage;
		function activeHomePage() {
			postal.publish({
				channel : "Tab",
				topic : "active"

			});
		}

	}
})();