angular.module('MetronicApp').directive('textBox', function() {
	return {
	    restrict: 'AE',
	    scope: {
	    	modelLabel : "@",
	      	eventClick: '&',
	      	eventBlur: '&',
	      	eventChange: '&',
	      	eventCopy: '&',
	      	eventCut: '&',
	      	eventDbllick: '&',
	      	eventFocus: '&',
	      	eventKeydown: '&',
	      	eventKeypress: '&',
	      	eventKeyup: '&',
	      	eventMousedown: '&',
	      	eventMouseenter: '&',
	      	eventMouseleave: '&',
	      	eventMousemove: '&',
	      	eventMouseover: '&',
	      	eventMouseup: '&',
	      	eventPaste: '&',
			modelName: '='
	    },
	    template: '<div class="form-group col-md-6">'+
				        '<label class="col-md-4 control-label">{{modelLabel}}</label>'+
				        '<div class="col-md-8">'+
				            '<div class="input-icon right">'+
				                '<input class="form-control width100" ng-model="modelName">'+
				            '</div>'+
				        '</div>'+
				    '</div>',
	    replace: true,
	    link: function($scope, element, attr, ctrl) {
	    	$(element).children('div').click(function(e) {
	    		$scope.eventClick();
            });
	    }
	  };
 });