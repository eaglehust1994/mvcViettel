(function() {
	'use strict';

	var controllerId = 'MainTabController';

	angular.module('MetronicApp').controller(controllerId, MainTabController);

	/* @ngInject */
	function MainTabController($scope, $rootScope, $timeout, Constant, $ocLazyLoad, gettextCatalog, CommonService) {
		var vm = this;
		vm.listFileLoadedPath='';

		/**
		 * Set rootScope for version
		 */	
//		$scope.tabs = [];
//		$scope.$watch(function() {
//	        //return $rootScope.authenticatedUser;
//	        return $rootScope.authenticatedUser;
//	    },  function(casUser){
//	    	if(casUser!=null){
//		    	$scope.tabs.push(createTabData({
//					title : gettextCatalog.getString("Home"),
//					templateUrl : Constant.getTemplateUrl('DASH_BOARD').templateUrl,
//					data: {id: 0},
//					closable : false,
//					active : true
//				}));
//	    	}
//	    });
		
		$scope.tabs = [createTabData({
			title : gettextCatalog.getString("Home"),
			templateUrl : Constant.getTemplateUrl('DASH_BOARD').templateUrl,
			key: 'DASH_BOARD',
			data: {id: 0},
			closable : false,
			active : true
		})];

		vm.addTab = addTab;
		vm.removeTab = removeTab;
		vm.scrlTabsApi = {};
		
		vm.addVersionToPathList = function(fileList){
			var list=[];
			if(fileList){
				if(version){
					for(var i=0;i<fileList.length;i++){
						//=vm.listFileLoadedPath+"#%^"+fileList[i];
						var temp=vm.addVersionToPath(fileList[i]);
						if(vm.listFileLoadedPath.indexOf(temp)<0){
							vm.listFileLoadedPath=vm.listFileLoadedPath+"#%^"+temp;
							list.push(temp);	
						}
						
					}
				}
			}
			/*if(list.length==0){
				list.push("erp/common/TodoController.js");
			}*/
			return list;
		}
		vm.addVersionToPath = function(file){
			if(file){
				if(version){				
					return file+"?tsVersion="+version; 
				}
			}
			return file;
		}
		
		postal.subscribe({
			channel  : "Tab",
			  topic    : "active",
			  callback:function(){
				  $scope.tabs[0].active=true;
			  }
		})
		
		// Subscribe opening tab channel
		postal.subscribe({
            channel  : "Tab",
            topic    : "open",
            callback : function(data, envelope) {
            	var listFileNeedReload=vm
									.addVersionToPathList(data.lazyLoadFiles);
							if (listFileNeedReload.length > 0) {
								$ocLazyLoad
										.load(
												{
													name : 'MetronicApp',
													insertBefore : '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
													files : listFileNeedReload
												})
										.then(
												function() {
													vm
															.addTab(createTabData({
																title : data.title,
																//                        templateUrl: data.templateUrl,
																templateUrl : vm
																		.addVersionToPath(data.templateUrl),
																data : {
																	id : 0
																},
																isSave : false,
																closable : true
															}));
												});
							} else {
								vm
										.addTab(createTabData({
											title : data.title,
											//                        templateUrl: data.templateUrl,
											templateUrl : vm
													.addVersionToPath(data.templateUrl),
											data : {
												id : 0
											},
											isSave : false,
											closable : true
										}));
							}

							$rootScope.stringtile = data.parent + " > "
									+ data.title;
						}

					});

			postal.subscribe({
				channel : "Tab",
				topic : "closeTab",
				callback : function(data, envelope) {
					removeTabByTitle(data.title);
				}
			});

			function getTab(title, templateUrl, data) {
				for (var i = 0; i < $scope.tabs.length; i++) {
					if (data === undefined || data.id === undefined
							|| $scope.tabs[i].data.id === undefined) {
						if ($scope.tabs[i].templateUrl == templateUrl) {
							return i;
						}
					} else {
						if ($scope.tabs[i].templateUrl == templateUrl
								&& $scope.tabs[i].data.id == data.id) {
							return i;
						}
					}
				}

				return -1;
			}

			/* Add new tab */
			function addTab(tabInfo) {
				//Check duplicate tabs
				var index = getTab(tabInfo.title, tabInfo.templateUrl,
						tabInfo.data);

				if (index < 0) {
					$timeout(function() {
						$scope.tabs.push(tabInfo);
						$scope.tabs[$scope.tabs.length - 1].active = true;

					}, 0);
				} else {
					$timeout(function() {
						//close old tab
						//                		$scope.tabs.splice(index, 1);
						$scope.tabs[index].active = true;
						$scope.tabs[index].data = tabInfo.data;
						//open new tab
						//                		$scope.tabs.push(tabInfo);
						// 	                    $scope.tabs[$scope.tabs.length - 1].active = true;
					}, 0);
				}

				scrollToView();

				/*if(tabInfo.isSave){
				 settingService.saveWorkingTab(tabInfo.templateUrl, tabInfo.title, tabInfo.data);
				 }*/

				$(document).find(".font-red-sunglo").removeClass(
						"font-red-sunglo")
			}

			// Remove tab by index
			function removeTab(event, index) {
				event.preventDefault();
				event.stopPropagation();

				//Unset templateUrl out of tabNames array
				var templateUrl = $scope.tabs[index].templateUrl;
				var id = $scope.tabs[index].data.id;
				//delete $scope.tabNames[templateUrl];

				$scope.tabs.splice(index, 1);

				//settingService.closeWorkingTab(templateUrl, id);
			}
			;

			function removeTabByTemplate(templateUrl, id) {
				for (var i = 0; i < $scope.tabs.length; i++) {
					if ($scope.tabs[i].templateUrl == templateUrl) {
						$scope.tabs.splice(i, 1);
						break;
					}
				}
			}
			;

			function removeTabByTitle(title) {
				for (var i = 0; i < $scope.tabs.length; i++) {
					if ($scope.tabs[i].title == title) {
						$scope.tabs.splice(i, 1);
						break;
					}
				}
			}
			;

			function createTabData(options) {
				function TabData(options) {
					if (typeof options === 'object') {
						for ( var key in options) {
							this[key] = options[key];
						}
					}
				}

				TabData.prototype.getId = function() {
					return this.data.id;
				};

				var tabData = new TabData(options);
				return tabData;
			}
			;

			function scrollToView() {
				if (vm.scrlTabsApi.scrollTabIntoView) {
					vm.scrlTabsApi.scrollTabIntoView();
				}
			}
			;

			$scope.$on('tab-close', function(event, args) {
				//console.log(args.template.templateUrl);

				/*if (_.find($scope.tabs, function(item){return item.templateUrl = args.template.templateUrl})) {
					console.log("Closing tab..");
					removeTabByTemplate(args.template.templateUrl, 0);
				}*/
				// do what you want to do
			});

		}
})();