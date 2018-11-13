
(function () {
  'use strict';
  var module = angular.module('vt.directive.isNumber', []);
  module.directive('isNumber', ['$filter', function ($filter) {
	  return {
	        require: '?ngModel',
	        link: function (scope, elem, attrs, ctrl) {
	            if (!ctrl) return;


	            ctrl.$formatters.unshift(function () {
	                return $filter('number')(ctrl.$modelValue,0)
	            });


	            ctrl.$parsers.unshift(function (viewValue) {
	                var plainNumber = viewValue.replace(/[^0-9]/g, '');
	                ctrl.$setViewValue(plainNumber);
	                ctrl.$render();
	                elem.val($filter('number')(plainNumber,0));
	                return plainNumber;
	            });
	        }
	    };
	}]);
  
  module.directive('isFloat', ['$filter', function ($filter) {
	  return {
	        require: '?ngModel',
	        link: function (scope, elem, attrs, ctrl) {
	            if (!ctrl) return;


	            ctrl.$formatters.unshift(function () {
	                return $filter('number')(ctrl.$modelValue)
	            });

	            ctrl.$parsers.unshift(function (viewValue) {
	                var plainNumber = viewValue.replace(/[^0-9]/g,'');
	                ctrl.$setViewValue(plainNumber);
	                ctrl.$render();
	                elem.val($filter('number')(plainNumber));
	                return plainNumber;
	            });
	        }
	    };
	}]);
  
  module.directive("autoGrow", ['$window', function($window){
	  return {
	      restrict: 'A',
	      link: function postLink(scope, element, attrs) {
	          var update = function(){
	        	  element.css({
	                  height: 'auto',
	                  overflow: 'hidden'
	                });
	              var height = element[0].scrollHeight;
	              if(height > 0){
	            	  element.css("height", height + "px");
	        	  }
	          };
	          scope.$watch(attrs.ngModel, function(){
	        		  update();
	          });

	          attrs.$set("ngTrim", "false");
	      }
	    };
	}]);
  
  module.directive('formatDate', ['$filter', function ($filter) {
      return {
          require: '?ngModel',
          link: function (scope, element, attrs, ctrl) {
        	  element.mask("99/99/9999");
        	  
        	  
          }
      };
  }]);
  
  
  
})();

