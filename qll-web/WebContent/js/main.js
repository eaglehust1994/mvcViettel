/***
Metronic AngularJS App Main Script
***/
//var API_URL="http://localhost:8084/ktts-service/";
var API_URL="/ktts-service/";
$.ajaxPrefilter(function(options, originalOptions, jqXHR){
    if (options.type.toLowerCase() === "post"||options.type.toLowerCase()==="put"||
    		options.type.toLowerCase()==="delete"||options.type.toLowerCase()==="options") {
        // add _token entry
        jqXHR.setRequestHeader("X-CSRF-TOKEN",readCookie('XSRF-TOKEN'))
    }
});

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}
/* Metronic App */
var MetronicApp = angular.module("MetronicApp", [
    "ui.router", 
    "ui.bootstrap", 
    "oc.lazyLoad",
    "ngCookies",
    "ngSanitize",
    'ngResource',
    'ngStorage',
    'pascalprecht.translate',
    'kendo.directives',
    'kendo.window',
    'gettext',
    'ui.tab.scroll',
    'restangular',
    'ngIdle',
    'disableAll'    
]); 

MetronicApp.config(['$translateProvider', 'Constant', function ($translateProvider, Constant) {
    $translateProvider
            .useStaticFilesLoader({
                prefix: Constant.prefixLanguage,
                suffix: '.json'
            })
            .preferredLanguage('vi_VN');
}]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
MetronicApp.config(['$ocLazyLoadProvider', 'scrollableTabsetConfigProvider','RestangularProvider','$uibTooltipProvider', function($ocLazyLoadProvider, 
		scrollableTabsetConfigProvider, RestangularProvider,$uibTooltipProvider) {
    $ocLazyLoadProvider.config({
        // global configs go here
    });
    
    scrollableTabsetConfigProvider.setShowTooltips(true);
    scrollableTabsetConfigProvider.setAutoRecalculate(true);
	scrollableTabsetConfigProvider.setTooltipLeftPlacement('bottom');
	scrollableTabsetConfigProvider.setTooltipRightPlacement('left');
	$uibTooltipProvider.options({
		  appendToBody: true
		});
	
	//RestangularProvider.setBaseUrl('/ktts-service/');
    RestangularProvider.setBaseUrl(API_URL);
    //RestangularProvider.setBaseUrl('http://localhost:8084/ktts-service/');
    //RestangularProvider.setBaseUrl('http://10.58.71.138:8386/ktts-service/');
/*	RestangularProvider.setDefaultHeaders({
		'Content-Type': 'application/json',
		'Authorization': 'Basic YWRtaW46YWRtaW4='
	});*/
    RestangularProvider.setDefaultHttpFields({withCredentials: true});
  //  RestangularProvider.setDefaultHeaders({'X-XSRF-TOKEN': CSRF_TOKEN});


}]);
/* Init global settings and run the app */
MetronicApp.run(["$rootScope", "settings", "$state", "gettextCatalog", "Idle","Restangular","Constant", function($rootScope, settings, $state, gettextCatalog, Idle,Restangular,Constant) {
	// start watching when the app runs. also starts the Keepalive service by default.
   // Idle.watch();
   // $rootScope.$state = $state; // state to be accessed from view
   // $rootScope.$settings = settings; // state to be accessed from view

    Restangular.setErrorInterceptor(function(response, deferred, responseHandler) {
        if(response.status === 489) {      
        	//if(response.config&&response.config.url.indexOf(Constant.loginUrl)){
        	if(response.config&&response.config.url.indexOf(Constant.authen.LOGIN_URL)){
        		window.location =response.data.data;
        	}
            return false; // error handled
        }
        else if (response.status === 499) {
        	$rootScope.authenticatedUser =null;
            window.location =response.data.data;
            return false; // error handled
        }else if (response.status === 492) {
        	$rootScope.authenticatedUser =null;
        	$('body').addClass('page-on-load')
            alert("Bạn chưa được phân quyền vào ứng dụng");            
            return false; // error handled
        }
        return true; // error not handled
    });
    
    
    if($rootScope.authenticatedUser == 'undefined' ||$rootScope.authenticatedUser==null){
    	 Restangular.one(Constant.authen.LOGIN_URL).get().then(function(user){
    		 $rootScope.casUser=user.casUser;
    		 if($rootScope.casUser!=null){
	    		 Restangular.one(Constant.authen.GET_USER_INFO).get().then(function(user){    	    	
	     	    	$rootScope.authenticatedUser=user
	     	    	Constant.setUser(user);;    	 
	     	    });
    		 }
    	 });
    	
    }
   


    $rootScope.formLevel1 = {
    	INPUTSELECTOR : ":input:not(:button,[type=submit],[type=reset],[disabled],[readonly], [level2] input, [level2] select)",
    	CHECKBOXSELECTOR : ":checkbox:not([disabled],[readonly],[level2] checkbox)",
    	NUMBERINPUTSELECTOR : "[type=number]:not([level2] [type=number]),[type=range]:not([level2] [type=range])"
    }
    
    $rootScope.formLevel2 = {
		INPUTSELECTOR : ":input:not(:button,[type=submit],[type=reset],[disabled],[readonly], [level3] input, [level3] select)",
		CHECKBOXSELECTOR : ":checkbox:not([disabled],[readonly], [level3] checkbox)",
		NUMBERINPUTSELECTOR : "[type=number]:not([level3] [type=number]),[type=range]:not([level3] [type=range])"
    }
    
    $rootScope.formLevel3 = {
		INPUTSELECTOR : ":input:not(:button,[type=submit],[type=reset],[disabled],[readonly], [level4] input, [level4] select)",
		CHECKBOXSELECTOR : ":checkbox:not([disabled],[readonly], [level4] checkbox)",
		NUMBERINPUTSELECTOR : "[type=number]:not([level4] [type=number]),[type=range]:not([level4] [type=range])"
    }
    
    gettextCatalog.currentLanguage= 'vi';
    gettextCatalog.debug = false;
}]);




/********************************************
 BEGIN: BREAKING CHANGE in AngularJS v1.3.x:
*********************************************/
/**
`$controller` will no longer look for controllers on `window`.
The old behavior of looking on `window` for controllers was originally intended
for use in examples, demos, and toy apps. We found that allowing global controller
functions encouraged poor practices, so we resolved to disable this behavior by
default.

To migrate, register your controllers with modules rather than exposing them
as globals:

Before:

```javascript
function MyController() {
  // ...
}
```

After:

```javascript
angular.module('myApp', []).controller('MyController', [function() {
  // ...
}]);

Although it's not recommended, you can re-enable the old behavior like this:

```javascript
angular.module('myModule').config(['$controllerProvider', function($controllerProvider) {
  // this option might be handy for migrating old apps, but please don't use it
  // in new ones!
  $controllerProvider.allowGlobals();
}]);
**/
//AngularJS v1.3.x workaround for old style controller declarition in HTML
MetronicApp.config(['$controllerProvider', function($controllerProvider) {
  // this option might be handy for migrating old apps, but please don't use it
  // in new ones!
  $controllerProvider.allowGlobals();
}]);

MetronicApp.config(['IdleProvider', function(IdleProvider) {
	// configure Idle settings
    IdleProvider.idle(1800); // in seconds
    IdleProvider.timeout(6000); // in seconds
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
*********************************************/

/* Setup global settings */
MetronicApp.factory('settings', ['$rootScope', function($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        assetsPath: 'assets',
        globalPath: 'assets/global',
        layoutPath: 'assets/layouts/layout',
        kttsLayoutPath:'style/layouts'
    };

    $rootScope.settings = settings;

    return settings;
}]);
	
/* Setup App Main Controller */
MetronicApp.controller('AppController', ['$scope', '$rootScope', 'gettextCatalog', '$window','$translate', function($scope, $rootScope, gettextCatalog, $window,$translate) {
    $scope.$on('$viewContentLoaded', function() {    	
        //App.initComponents(); // init core components
        //Layout.init(); 
    	//  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive
    });


    $scope.getTemplateBySelector = function (selector) {
        return angular.element(selector).html();
    };
    
    $scope.changeLanguage = changeLanguage;
    $scope.activeLangCode = 'VN';
    $scope.activeFlagIcon = 'vn';
    
    function changeLanguage(langCode) {
    	$scope.activeLangCode = langCode;
    
    	if ('EN' == langCode) {
    		$scope.activeFlagIcon = 'us';
    		gettextCatalog.setCurrentLanguage('en');
    		$translate.use('en_US');
    		
    	} else if ('VI' == langCode) {
    		$scope.activeFlagIcon = 'vn';
    		gettextCatalog.setCurrentLanguage('vi');
    		$translate.use('vi_VN');
    	}
    }
    
    $scope.$on('IdleStart', function() {
        // the user appears to have gone idle
    });

    $scope.$on('IdleWarn', function(e, countdown) {
        // follows after the IdleStart event, but includes a countdown until the user is considered timed out
        // the countdown arg is the number of seconds remaining until then.
        // you can change the title or display a warning dialog from here.
        // you can let them resume their session by calling Idle.watch()
    });

    $scope.$on('IdleTimeout', function() {
        // the user has timed out (meaning idleDuration + timeout has passed without any activity)
        // this is where you'd log them
    	$window.location.href = 'logout';
    });

    $scope.$on('IdleEnd', function() {
        // the user has come back from AFK and is doing stuff. if you are warning them, you can use this to hide the dialog
    });

}]);

/***
Layout Partials.
By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial 
initialization can be disabled and Layout.init() should be called on page load complete as explained above.
***/



/* Setup Layout Part - Header */
MetronicApp.controller('HeaderController', ['$scope','$rootScope', 'Constant','Restangular', function($scope,$rootScope, Constant,Restangular) {	
	$scope.$watch(function() {
        return $rootScope.casUser;
    }, function(casUser) {
    	if (casUser==null) {
				return;
    		}
    			$scope.fullName = casUser.fullName;
    		
    	
    }, true)
	
    $scope.$on('$includeContentLoaded', function() {
    	Layout.initHeader(); // init header
    });

    $scope.logout=function(){
        $rootScope.isAuthenticated=false;
        Restangular.one("authenServiceRest/logout").get().then(function(response){

        }).catch(function (err) {
            console.log('Không kết nối dữ liệu ' + err.message);
        });

    };
}]);

/* Setup Layout Part - Sidebar */
MetronicApp.controller('SidebarController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initSidebar(); // init sidebar
    });
}]);

/* Setup Layout Part - Quick Sidebar */
MetronicApp.controller('QuickSidebarController', ['$scope', function($scope) {    
    $scope.$on('$includeContentLoaded', function() {
       setTimeout(function(){
            QuickSidebar.init(); // init quick sidebar        
        }, 2000)
    });
}]);

/* Setup Layout Part - Theme Panel */
MetronicApp.controller('ThemePanelController', ['$scope', function($scope) {    
    $scope.$on('$includeContentLoaded', function() {
        Demo.init(); // init theme panel
    });
}]);

/* Setup Layout Part - Footer */
MetronicApp.controller('FooterController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);
MetronicApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.withCredentials = true;

    $httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN';
    $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
   
}]);

/* Setup Rounting For All Pages */
MetronicApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/dashboard.html");  

    $stateProvider
        .state('erp', {
            url: "",
            templateUrl: "tpl/mainTab.view.html",
            controller: "DashboardController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                        files: [
                            'assets/global/plugins/jquery.sparkline.min.js',
                            'assets/pages/scripts/dashboard.js',
                            'erp/common/DashboardController.js',
                        ] 
                    });
                }]
            }
        })	
}]);



//add authenticate cho cookie
MetronicApp.factory('myHttpResponseInterceptor', ['$q', '$cookies','Constant', function ($q, $cookies,Constant) {
    return {
        // optional method
        'request': function (config) {
            // do something on success
            config.withCredentials = true;
            if(version){
            	/*if(config.url.indexOf('tab.html')>=0||config.url.indexOf('tooltip-html-popup')>=0||config.url.indexOf('tabset.html')>=0){
            		console.log(config.url);
            		return config;
            	}*/
            	if(!Constant.inWhiteListAddVersion(config.url))
	       		 	config.url = config.url+"?tsVersion="+version;    
            	else{
            		/*console.log(config.url);
            		console.log(config);*/
            	}
	       	 }
            
            return config;
        },

        // optional method
        'requestError': function (rejection) {
            // do something on error
            return $q.reject(rejection);
        },

        // optional method
        'response': function (response) {
            // do something on success
            return response;
        }
        //,
    };
}]);
MetronicApp.config(function ($httpProvider) {
    $httpProvider.interceptors.push('myHttpResponseInterceptor');
});