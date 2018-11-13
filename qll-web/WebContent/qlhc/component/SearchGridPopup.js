﻿angular.module('MetronicApp').directive('searchGridPopup', function ($rootScope,RestEndpoint, CommonService, gettextCatalog, $http) {
	var tempSelectValue = null;
    var tempSelectName = null;
	var selectionProcessed = false;
    var searchAutocompleteMinLength = 1;
    return {
	    restrict: 'AE',
	    scope: {
	        modelLabel: "@",
	        modelId: '=',
	        modelName: '=',
	        caller: '=',
	        req:"@",
	        msg:"@",
	        iddiv:"@",
	        idlable:"@",
	        disabled:'=',
	        searchtype:"=", // Budget - account - costType - salesRegion - costCenter - profitCenter - searchProject - product 
							// mwarehouse - searchLocation - searchCSiteCodeInfo - searchCSiteCodeGroup - searchStatement - searchBank
							// bankAcct - searchProductCategory - cBpartner - station - project - projectPhase - account - construction
							// constructionGroup - constructionPhase - searchDocumentType - cperiod - payroll - contract - siteCodeType
							// View more in: ModalInstanceController.js - HaiLH10
	        popupId: "=",
	        popupType: '=',
	        popupSourceLink: '=',
	        eventchange: "@",
	        change:"@",
	        comboId: "@",
	        comboSourceLink: "@",
	        comboName: "@",
	        comboValue: "@",
	        comboCode: "@",
			comboSearch: '=',
			comboGrid: '=',
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
	      	eventPaste: '&'
	    },
	    template: '<div class="form-group col-md-6" id="{{iddiv}}">'+
				        '<label id="{{idlable}}" class="col-md-4 control-label {{req}}">{{modelLabel}}</label>'+
				        '<div class="col-md-8">'+
				            '<div class="input-icon right">'+
				                '<i class="fa fa-search"></i>'+
				                '<input type="hidden" ng-model="modelId" />'+
				                '<input id="{{comboId}}" name="{{comboId}}" class="form-control width100" ng-model="modelName" data-required-msg="{{msg}}">' +
				            '</div>'+
				            '<span data-for="{{comboId}}" class="k-invalid-msg"></span>'+
				        '</div>'+
				    '</div>',
	    replace: true,
	    link: function($scope, element, attrs, ctrl) {	        
	        function showTreePopupForm(vm, sourceLink, label, popupId) {
				$scope.comboSearch.name = '';
				$scope.comboSearch.value = '';
				$scope.comboSearch.isSize = false;
	            $http({
                    url: RestEndpoint.BASE_SERVICE_URL + sourceLink,
                    dataType: 'json',
                    method: 'POST',
                    data: $scope.comboSearch,
                    headers: {
                        "Content-Type": "application/json"
                    }
                }).success(function (result) {
                    var templateUrl = 'views/popup/treeView.html';
					var title = gettextCatalog.getString(label);
					vm.treeData = new kendo.data.HierarchicalDataSource({ data: result });
					CommonService.populateDataToTree(templateUrl, title, vm.treeData, vm, popupId);
                }).error(function (error) {
                    alert("Error function: " + error);
                });
	        }

	        function showGridPopupForm(vm, sourceLink, label, id, value, name, popupId, searchtype) {
				$scope.comboSearch.name = '';
				$scope.comboSearch.value = '';
				$scope.comboSearch.isSize = false;
				$http({
                    url: RestEndpoint.BASE_SERVICE_URL + sourceLink,
                    dataType: 'json',
                    method: 'POST',
                    data: $scope.comboSearch,
                    headers: {
                        "Content-Type": "application/json"
                    }
                }).success(function (result) {
                    var templateUrl = 'views/popup/gridView.html';
					var title = gettextCatalog.getString(label);
					vm.gridData = new kendo.data.DataSource({ data: result });
					vm.gridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						dataSource: vm.gridData,
						noRecords: true,
						messages: {
							noRecords: gettextCatalog.getString("Không có dữ liệu")
						},
						columns: $scope.comboGrid
					});
					CommonService.populateDataToGrid(templateUrl, title, vm.gridOptions, vm, popupId,searchtype);
                }).error(function (error) {
                    alert("Error function: " + error);
                });
	        }
	        function showGridPopupFormPlus(vm, sourceLink, label, id, value, name,template, popupId,searchtype) {
//				$scope.comboSearch.name = '';
//				$scope.comboSearch.value = '';
//				$scope.comboSearch.isSize = false;
	        	tempSelectName = '' ;
	        	tempSelectValue = '' ;
	        	$scope.caller.disablesavepop = true;
				var vm = $scope.caller;
              
				vm.searchGrid.isSize = false;
					
                	if(template == ''){
                		templateUrl = 'views/popup/gridView.html';
                	}else{
                		templateUrl = template;
                	}
					var title = gettextCatalog.getString(label);
					vm.gridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						dataSource: {
							serverPaging: true,
			                
							transport: {
					            read: {
					               url: RestEndpoint.BASE_SERVICE_URL + sourceLink,			       
					               contentType: "application/json; charset=utf-8",
					               type: "POST"
					            },
				         parameterMap: function(options, type) {
				    	  
                           
				        	 vm.searchGrid.page = options.page
				        	 vm.searchGrid.pageSize = options.pageSize
				        	 
				    	  return  JSON.stringify(vm.searchGrid)
				    

			             }
					        },
					        schema: {
					            
					        	total: function(response) {
					        		console.log(response);
					        	      return response.total; // total is returned in the "total" field of the response
					        	},
					            data:  function(response) {
					        		console.log(response);
					        	      return response.data; // data is returned in the "data" field of the response
					        	},
					          },
						},
						noRecords: true,
						messages: {
							noRecords: gettextCatalog.getString("Không có dữ liệu")
						},
					    dataBinding: function() {
					    	vm.recordpopup = (this.dataSource.page() -1) * this.dataSource.pageSize();
					      },
						
						columns: $scope.comboGrid
					});
					CommonService.populateDataToGridplus(templateUrl, title, vm.gridOptions, vm, popupId,searchtype);
      
	        }
	        function onChangePopupForm(dataItem, popupId) {
	            if (popupId == attrs.popupId) {
	                tempSelectValue = dataItem[attrs.comboValue];
	                tempSelectName = dataItem[attrs.comboName];
	            }
	        }

	        function onRowChange(dataItem, popupId) {
	            if (popupId == attrs.popupId) {
	                tempSelectValue = dataItem[attrs.comboValue];
	                tempSelectName = dataItem[attrs.comboName];
	                $scope.caller.disablesavepop = false;
	            }
	        }
	        function searchOnGridPop(popupId) {
	        	 var vm = $scope.caller;
	        	 vm.popupGrid.dataSource.read();
	        }
	        function resetSearchPop(popupId) {
	        	 var vm = $scope.caller;
	        	 vm.searchGrid = {isSize : false};
	        	 vm.popupGrid.dataSource.read();
	        }
	        

	        function onSave(popupId) {
	            if (popupId == attrs.popupId) {
	            	selectionProcessed = true;
	                $scope.modelId = tempSelectValue;
	                $scope.modelName = tempSelectName;
	                $('#'+popupId).focus().select();
					if (typeof attrs.eventchange !== "undefined" && attrs.eventchange != null){
						$rootScope.$broadcast(attrs.eventchange, $scope.modelId);
					}
	            }
	        }

	        function onCancel() {
	            //do nothing
	        }
	        function keydownpop(popupId){
	        	if (popupId == attrs.popupId) {
				 if (event.keyCode == 13) {
					 searchOnGridPop();
				    }
	        	} 
			    }

	        setTimeout(function(){
	        	if (attrs.comboSourceLink != undefined) {	
	        		$(element).children('div').children('div').children('i').on('click', function (e) {
                        var vm = $scope.caller;
                        vm.onRowChange = onRowChange;
                        vm.onCancel = onCancel;
                        vm.onSave = onSave;
                        vm.onChangePopupForm = onChangePopupForm;
                        vm.searchOnGridPop = searchOnGridPop;
                        vm.resetSearchPop = resetSearchPop;
                        vm.keydownpop = keydownpop;
                        if (attrs.popupType == 'grid') {
                            showGridPopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName, attrs.popupId ,attrs.searchtype);
                        } else if (attrs.popupType == 'tree') {
                            showTreePopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.popupId);
                        }else if(attrs.popupType == 'gridplus'){
                        	vm.searchGrid = {};
                        	showGridPopupFormPlus(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName,attrs.template, attrs.popupId,attrs.searchtype);
                        }
                    });

                  
	                
	                function navigateTo(item) {
	                    if (item != null) {
	                    	if (item[attrs.comboValue] != 0) {
	                    		$scope.$apply(function () {
	                    			$scope.modelId = item[attrs.comboValue];
		                            $scope.modelName = item[attrs.comboName];
		        	                $('#' + attrs.comboId).data('kendoAutoComplete').value(item[attrs.comboName]);
		                        });
	        	               
								if (typeof attrs.eventchange !== "undefined" && attrs.eventchange != null){
									$rootScope.$broadcast(attrs.eventchange, $scope.modelId);
								}
	                    	} else {
	                    		var vm = $scope.caller;
	                            vm.onRowChange = onRowChange;
	                            vm.onCancel = onCancel;
	                            vm.onSave = onSave;
	                            vm.onChangePopupForm = onChangePopupForm;
	                            if (attrs.popupType == 'grid') {
	                                showGridPopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName, attrs.popupId,attrs.searchtype);
	                            } else if (attrs.popupType == 'tree') {
	                                showTreePopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.popupId);
	                            }
	                    	}                                
	                    } else {
	                    	$scope.$apply(function () {
	                    		$scope.modelId = null;
		                        $scope.modelName = null;
	                        });	                    	
	                    	$('#' + attrs.comboId).data('kendoAutoComplete').value(null);
	                    }
	                }
		        }
	        }, 10);
	         	        
	    }
	  };
 });