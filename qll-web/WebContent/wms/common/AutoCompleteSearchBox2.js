angular.module('MetronicApp').directive('dateTime', function ($rootScope,RestEndpoint, CommonService, gettextCatalog, $http) {
	
    return {
	    restrict: 'AE',
	    scope: {
	        ngModel: '=',
	        caller: '=',
	        id:"@",
	      	
	    },
	
	    link: function($scope, element, attrs, ctrl) {
		
								var transDate = $('#'+id);
									transDate.kendoMaskedTextBox({
										mask : "00/00/0000"
									});

									transDate.kendoDatePicker({
										format : "dd/MM/yyyy"
									});

									transDate.closest(".k-datepicker").add(transDate)
											.removeClass("k-textbox");
								
		
		}
	  };
 });