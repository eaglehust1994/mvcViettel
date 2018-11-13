﻿angular.module('MetronicApp').directive('autoSearch', function ($rootScope,RestEndpoint, CommonService, gettextCatalog, $http) {
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
	        searchtype:"=", 
	        popupId: "=",
	        popupType: '=',
	        popupSourceLink: '=',
	        headerTemplate:'=',
	        templateAuto:'=',
	        page:'=',
	        pageSize:'=',
	        templateUrl:'=',
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
				        '<div class="One_icon">'+
						'<i class="fa fa-times" aria-hidden="true"></i>'+
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
				vm.searchGrid.page = attrs.page;
	        	vm.searchGrid.pageSize = attrs.pageSize;
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
						pageable: {
                        	pageSize: 15,
                        	pageSizes: [15, 30, 50],
                        	messages: {
                                display: "{0} - {1} trong {2} kết quả", 
                                empty: "Không có dữ liệu",
                                page: "Trang",
                                allPages: "All",
                                of: "trong {0}",
                                itemsPerPage: "kết quả/trang",
                                first: "Về trang đầu",
                                previous: "Về trang trước",
                                next: "Về trang tiếp theo",
                                last: "Về trang cuối",
                                refresh: "Làm mới"
                        	}
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
					CommonService.populateDataToGridplus(templateUrl, title, vm.gridOptions, vm, popupId,searchtype,true);
      
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
	               
					if (typeof attrs.eventchange !== "undefined" && attrs.eventchange != null){
						$rootScope.$broadcast(attrs.eventchange, $scope.modelId);
					}
	            }
	        }

	        function onCancel() {
	            //do nothing
	        }
	        

	        setTimeout(function(){
	        	$(element).find($(".fa.fa-times")).on('click', function (e) {
        			$scope.modelId = null;
                    $scope.modelName = null;
                    $('#' + attrs.comboId).val("");
        		});
	        	
	        	if (attrs.comboSourceLink != undefined) {	
	        		$(element).children('div').children('div').children('i').on('click', function (e) {
                        var vm = $scope.caller;
                        vm.onRowChange = onRowChange;
                        vm.onCancel = onCancel;
                        vm.onSave = onSave;
                        vm.onChangePopupForm = onChangePopupForm;
                        vm.searchOnGridPop = searchOnGridPop;
                        vm.resetSearchPop = resetSearchPop;
                        
                        if (attrs.popupType == 'grid') {
                            showGridPopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName, attrs.popupId ,attrs.searchtype);
                        } else if (attrs.popupType == 'tree') {
                            showTreePopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.popupId);
                        }else if(attrs.popupType == 'gridplus'){
                        	vm.searchGrid = {};
                        	showGridPopupFormPlus(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName,attrs.templateUrl, attrs.popupId,attrs.searchtype);
                        }
                    });

	        		
                    $('#' + attrs.comboId).kendoAutoComplete({                        
                        dataTextField: attrs.comboName,
                        dataValueField: attrs.comboValue,
                        headerTemplate : $scope.headerTemplate,
                        footerTemplate: 'Total #: instance.dataSource.total() # items found',
                        template : $scope.templateAuto,
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
									$scope.comboSearch.name = $scope.modelName;
									$scope.comboSearch.isSize = true;
									return JSON.stringify($scope.comboSearch);
								}
					        }
					    },
					    minLength: 1,
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