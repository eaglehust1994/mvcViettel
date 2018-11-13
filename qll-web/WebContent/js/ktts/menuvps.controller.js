(function() {
	'use strict';

	var controllerId = 'MenuController';

	angular.module('MetronicApp').controller(controllerId, MenuController);

	/* @ngInject */
	function MenuController($scope, $rootScope, Constant, $http,Restangular,CommonService) {
		var vm = this;
		$scope.Constant = Constant;

		$scope.$watch(function() {
	        return $rootScope.casUser;
	    },  function(casUser){
	    	if(casUser==null){
	    		return ;
	    	}
			console.log(casUser);
			vm.menuObjects=casUser.parentMenu;
			
		})


		vm.goTo = goTo;
		
		/*
		 * get menu text - neu vsa tra ve null thi
		 */
		vm.getMenuText=function(menuObject){
            try {
            	if(menuObject.url==null){
            		return CommonService.translate(menuObject.name);
            		/*return menuObject.name;*/
            	}
                var template = Constant.getTemplateUrl(menuObject.url);
                if(template==null){
                	if(menuObject.name!=''){
                		return CommonService.translate(menuObject.code);
                		
                	}
        			return "N/A";
                }
                return template.title;
            }catch(err){
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
				topic   : "open",
				data    : template
			});
		}
		vm.activeHomePage=activeHomePage;
		function activeHomePage() {
			postal.publish({
				channel : "Tab",
				topic   : "active"
				
			});
		}
		
	}
})();