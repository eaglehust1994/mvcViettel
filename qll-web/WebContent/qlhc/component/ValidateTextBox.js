angular.module('MetronicApp').directive('validateTextBox', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
        	 $(element).children('div').click(function(e) {
            modelCtrl.$parsers.push(function(inputValue) {
                var transformedInput = inputValue.toLowerCase().replace("'","~","@","#","$","%","^","&","*",";","/","\\","|",";","'","]","[",")","(", "<", ">","-","_","!","+","=","{","}","?", "\"",":");
                if (transformedInput != inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }
                return transformedInput;
            });
        });
        }
    };
});