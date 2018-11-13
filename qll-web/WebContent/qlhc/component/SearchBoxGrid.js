angular.module('MetronicApp').directive('searchBoxGrid', function ($rootScope,RestEndpoint, CommonService, gettextCatalog, $http) {
	var tempSelectValue = null;
    var tempSelectName = null;
	var selectionProcessed = false;
    var searchAutocompleteMinLength = 1;
    return {
	    restrict: 'AE',
	    scope: {
	        modelLabel: "@",
	        modelId: '=',
	        modelCode: '=',
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
	    template: '<div class="margin-bottom-asset col-md-6" id="{{iddiv}}">'+
				        '<label id="{{idlable}}" class="col-md-4 control-label {{req}}">{{modelLabel}}</label>'+
				        '<div class="col-md-8">'+
				            /*'<div class="input-icon right">'+
				                '<i class="fa fa-search"></i>'+*/
				                '<input type="hidden" ng-model="modelId" />'+
				                '<input type="hidden" ng-model="modelCode" />'+
				                '<input id="{{comboId}}" name="{{comboId}}" class="form-control width100" ng-model="modelName" data-required-msg="{{msg}}">' +
				            /*'</div>'+*/
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
						columns: $scope.comboGrid
					});
					CommonService.populateDataToGrid(templateUrl, title, vm.gridOptions, vm, popupId,searchtype);
      
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
	            }
	        }
	        function searchOnGrid(popupId) {
	        	 var vm = $scope.caller;
	        	 vm.popupGrid.dataSource.read();
	        }
	        
	        function onSave(popupId) {
	            if (popupId == attrs.popupId) {
	            	selectionProcessed = true;
	                $scope.modelId = tempSelectValue;
	                $scope.modelName = tempSelectName;
	               
					if (typeof attrs.eventchange !== "undefined" && attrs.eventchange != null){
						$rootScope.$broadcast(attrs.eventchange, $scope.modelId);
					}
	            }
	        }

	        function onCancel() {
	            //do nothing
	        }
	        

	        setTimeout(function(){
	        	if (attrs.comboSourceLink != undefined) {
	        		if (typeof attrs.req !== "undefined" && attrs.req === "req"){
	        			document.getElementById(attrs.comboId).required = true;
	        		}
	        		$(element).children('div').children('div').children('i').on('click', function (e) {
                        var vm = $scope.caller;
                        vm.onRowChange = onRowChange;
                        vm.onCancel = onCancel;
                        vm.onSave = onSave;
                        vm.onChangePopupForm = onChangePopupForm;
                         vm.searchOnGrid = searchOnGrid;
                        
                        if (attrs.popupType == 'grid') {
                            showGridPopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName, attrs.popupId ,attrs.searchtype);
                        } else if (attrs.popupType == 'tree') {
                            showTreePopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.popupId);
                        }else if(attrs.popupType == 'gridplus'){
                        	showGridPopupFormPlus(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName,attrs.template, attrs.popupId,attrs.searchtype);
                        }
                    });

                    $('#' + attrs.comboId).kendoAutoComplete({                        
                        dataTextField: attrs.comboName,
                        dataValueField: attrs.comboValue,
                        dataSource: {
					        serverFiltering: true,
					        type: "json",
					        transport: {
					            read: {
									type: "POST",
									url: RestEndpoint.BASE_SERVICE_URL + attrs.comboSourceLink,
									contentType: "application/json; charset=utf-8",
									dataType: "json"
					            },
								parameterMap: function(options, operation) {
									if($scope.modelName==null ||  $scope.modelName===""){
										$scope.modelId=null;
									}
									
									$scope.comboSearch.value = $scope.comboGrid;
//									$scope.comboSearch.code = $scope.modelName;								
									$scope.comboSearch.keySearch =$scope.modelName;
									/*$scope.comboSearch.name = $scope.modelName;
									$scope.comboSearch.isSize = true;*/
									return JSON.stringify($scope.comboSearch);
								}
					        }
					    },
					    minLength: searchAutocompleteMinLength,
			            suggest: true,	
                        filter: "contains",
                        select: function (e) {
	                        var dataItem = this.dataItem(e.item.index());
	                        selectionProcessed = true;
			            	navigateTo(dataItem);
			            	if (dataItem != null && dataItem[attrs.comboValue] == 0) {
			            		e.preventDefault();
			            	}
	                    },
			            open: function(e) {
	                        selectionProcessed = false;
	                    },
	                    change: function (e) {
	                        if (!selectionProcessed) {
	                            selectionProcessed = true;
	                            processSearch();
	                        } else {
	                            selectionProcessed = false;
	                        }
	                    }                        
                    });
	                
	                $('#' + attrs.comboId).keypress(function(event) {
			        	if (event.which == kendo.keys.ENTER) {
	                        if (!selectionProcessed) {
	                            selectionProcessed = true;
	                            processSearch();
	                        } else {
	                            selectionProcessed = false;
	                        }
	                    }
			        });
	                
	                function processSearch() {
	                    var autocomplete = $('#' + attrs.comboId).data('kendoAutoComplete');
	                    var searchDataItem = null;
	                    if (autocomplete.value() != "") {
	                        if (autocomplete.value().length >= searchAutocompleteMinLength) {
	                            autocomplete.search(autocomplete.value());
	                            if (autocomplete.dataItem(0) != undefined) {
	                                searchDataItem = autocomplete.dataItem(0);
	                            }
	                        }
	                    }
	                    navigateTo(searchDataItem);                   
	                }
	                
	                function navigateTo(item) {
	                    if (item != null) {
	                    	if (item[attrs.comboValue] != 0) {
	                    		$scope.$apply(function () {
	                    			$scope.modelId = item[attrs.comboValue];
		                            $scope.modelName = item[attrs.comboName];
		                            $scope.modelCode = item[attrs.comboCode];
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