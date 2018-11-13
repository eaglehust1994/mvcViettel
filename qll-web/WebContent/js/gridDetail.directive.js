/***
 GLobal Directives
 ***/

(function() {
    'use strict';

    var MetronicApp = angular.module('MetronicApp');

// Handle global LINK click
    MetronicApp.directive('gridDetail', ['$window', function($window) {
        return {
            restrict: 'EA',
            link: function(scope, elem, attrs) {
                elem.width(elem.closest('.k-grid-content').width() - 20);

                angular.element($window).bind('resize', function(){

                    elem.width(elem.closest('.k-grid-content').width() - 20);

                    // manuall $digest required as resize event
                    // is outside of angular
                    //scope.$digest();
                });

            }
        };
    }]);

})()