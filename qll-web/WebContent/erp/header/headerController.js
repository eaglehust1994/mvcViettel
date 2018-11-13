(function () {
    'use strict';

    var controllerId = 'HeaderController';

    angular.module('MetronicApp').controller(controllerId, headerController);
    
    /* @ngInject */
    function headerController($scope, $rootScope, gettextCatalog, Constant) {
        var vm = this;
        vm.user={}

		$scope.$watch(function() {
	        
	         return $rootScope.casUser;
	     },  function(casUser){
	    
	    	 if(casUser==null){
	    		 return ;
	    	 }
			 vm.user=casUser;
			});


        /*vm.changeLanguage = changeLanguage;
        vm.activeLangCode = 'VN';
        vm.activeFlagIcon = 'vn';
        
        function changeLanguage(langCode) {
        	vm.activeLangCode = langCode;
        	if ('EN' == langCode) {
        		vm.activeFlagIcon = 'us';
        		gettextCatalog.currentLanguage= 'en';
        		$scope.currLan = gettextCatalog.currentLanguage;
        		
        	} else if ('VI' == langCode) {
        		vm.activeFlagIcon = 'vn';
        		gettextCatalog.currentLanguage= 'vi';
        		$scope.currLan = gettextCatalog.currentLanguage;
        	}
        }*/
        
    }
})();