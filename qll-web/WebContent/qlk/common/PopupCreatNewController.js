/* Modal Controller */
MetronicApp.controller('PopupCreateNewCtrl', [
		'$scope',
		'data',
		'caller',
		'modalInstance',
		'windowId',
		'isCreateNew',
		'CommonService',
		'SearchService',
		'PopupConst',
		'RestEndpoint',
		'$localStorage',
		'$rootScope',
		function($scope, data, caller, modalInstance, windowId, isCreateNew,
				CommonService, SearchService, PopupConst, RestEndpoint,
				$localStorage, $rootScope) {
          
			$rootScope.flag=false;
			$scope.data = data;
			$scope.modalInstance = modalInstance;
			$scope.windowId = windowId;
			$scope.caller = caller;
			$scope.cancel = cancel;
			$scope.save = save;
			$scope.remove = remove;
			$scope.isCreateNew = isCreateNew;
			$scope.saveConfig = saveConfig;
			$scope.validatorOptions = kendoConfig.get('validatorOptions');

			function cancel() {
				CommonService.dismissPopup();
				// caller.cancel();
			}

			function save() {
				if ($scope.validator.validate()) {
				caller.save($scope.data, $scope.isCreateNew);
				}
			}
			
			function saveConfig() {
				if ($scope.validator.validate()) {
				caller.saveConfig();
				}
			}
			
			function remove(){
				if ($scope.validator.validate()) {
					caller.remove($scope.data);
					}
			}

		} ]);