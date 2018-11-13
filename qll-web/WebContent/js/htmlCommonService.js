
angular.module('MetronicApp').factory('htmlCommonService',
		['$http', '$q','$templateCache', '$rootScope', 'RestEndpoint', 'Restangular', '$kWindow', 
		 function($http, $q, $templateCache, $rootScope, RestEndpoint, Restangular, $kWindow){
	    var factory = {
	    		getTemplateHtml:getTemplateHtml,
	    		populatePopup:populatePopup,
	    		populatePopupCreate:populatePopupCreate,
	    		dismissPopup:dismissPopup,
	    		isNumber:isNumber,
	    		isNegative:isNegative,
	    		validateIntKeydown:validateIntKeydown
	    };
	    function getTemplateHtml(url){
	    	return $templateCache.get(url);
	    }
	    
	    function populatePopup(templateUrl, gridTitle,
				gridOptions, data, caller, popupId,
				searchType, isMultiSelect, sizeWith,
				sizeHeight, controller) {
			checkOnePopup = true;
			modalInstance1 = $kWindow.open({
				options : {
					modal : true,
					title : gridTitle,
					visible : false,
					width : sizeWith,
					height : sizeHeight,
					actions : [ "Minimize", "Maximize",
							"Close" ],
					open : function() {
						this.wrapper.children(
								'.k-window-content')
								.addClass("fix-footer");
					}
				},
				templateUrl : templateUrl,
				controller : controller,
				resolve : {
					gridOptions : function() {
						return gridOptions;
					},
					dataTree : function() {
						return data;
					},
					caller : function() {
						return caller;
					},
					modalInstance1 : function() {
						return this;
					},
					popupId : function() {
						return popupId;
					},
					searchType : function() {
						return searchType;
					},
					isMultiSelect : function() {
						return isMultiSelect;
					}
				}
			});

			modalInstance1.result.then(function(result) {
				dismissPopup1();
			});
		}
	    
	    
	    function populatePopupCreate(templateUrl,
				gridTitle, data, caller, windowId,
				isCreateNew, sizeWith, sizeHeight, idFocus) {
			checkOnePopup = true;
			modalInstance1 = $kWindow
					.open({
						options : {
							modal : true,
							title : gridTitle,
							visible : false,
							width : sizeWith,
							height : sizeHeight,
							actions : [ "Minimize",
									"Maximize", "Close" ],
							open : function() {
								caller.openForm();
								this.wrapper
										.children(
												'.k-window-content')
										.addClass(
												"fix-footer");
								$rootScope
										.$broadcast('Popup.open');

							},
							close : function() {
								// modalInstance = null;
								caller.closeForm();
								$rootScope
										.$broadcast('Popup.CloseClick');
								isOpening = false;
							},
							activate : function() {
								if (document
										.getElementById(idFocus))
									document
											.getElementById(
													idFocus)
											.focus();
							},
						},
						templateUrl : templateUrl,
						controller : 'PopupCreateNewCtrl',
						resolve : {
							data : function() {
								return data;
							},
							caller : function() {
								return caller;
							},
							modalInstance1 : function() {
								return this;
							},
							windowId : function() {
								return windowId;
							},
							isCreateNew : function() {
								return isCreateNew;
							},
						}
					});

			modalInstance1.result.then(function(result) {
				dismissPopup1();
			});
		}
	    
	    
	    function dismissPopup() {
				modalInstance1.dismiss('cancel');
				checkTowPopup = false;
		}
	    
	    function isNumber(input){
	    	if(input.length == 0){
	    		return true;
	    	}
	    	else{
	    		return (!isNaN(input) && !!parseInt(input) );
	    	}
	    }
	    
	    function isNegative(input){
	    	var num = parseInt(input);
	    	return num < 0;
	    }
	    
	    /**
	     * @overview: check if key pressed is number
	     */
	    function validateIntKeydown(event){
	    	var isValid = event.which != 46 && 
	    	((event.which < 48 || event.which > 57) &&
	    			(event.which != 0 && event.which != 8));
	    	return !isValid;

	    }
	     return factory;
	 
	     

	}]);
