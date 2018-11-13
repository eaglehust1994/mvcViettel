angular.module('MetronicApp').directive('searchBoxTiny', function ($rootScope,RestEndpoint, CommonService, gettextCatalog) {
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
	        
	        searchtype:"=", // Budget - account - costType - salesRegion - costCenter - profitCenter - searchProject - product 
							// mwarehouse - searchLocation - searchCSiteCodeInfo - searchCSiteCodeGroup - searchStatement - searchBank
							// bankAcct - searchProductCategory - cBpartner - station - project - projectPhase - account - construction
							// constructionGroup - constructionPhase - searchDocumentType - cperiod - payroll - contract - siteCodeType
							// View more in: ModalInstanceController.js - HaiLH10
	        
	        popupId: "=",
	        popupType: '=',
	        popupSourceLink: '=',
	        
	        change:"@",
	        comboId: "@",
	        comboSourceLink: "@",
	        comboName: "@",
	        comboValue: "@",
	        comboCode: "@",

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
	    template: '<div class="col-md-5 control-inline">'+
				        '<div class="input-icon right">'+
					        '<i class="fa fa-search"></i>'+
					        '<input type="hidden" ng-model="modelId" />'+
					        '<input id="{{comboId}}" kendo-auto-complete class="form-control width100" ng-model="modelName">' +
				        '</div>'+
					'</div>',
	    replace: true,
	    link: function($scope, element, attrs, ctrl) {	        
	        function showTreePopupForm(vm, sourceLink, label, popupId) {
	            $.ajax({
	                type: "GET",
	                url: RestEndpoint.BASE_SERVICE_URL + sourceLink,
	                contentType: "application/json; charset=utf-8",
	                success: function (result) {
	                    var templateUrl = 'views/popup/treeView.html';
	                    var title = gettextCatalog.getString(label);
	                    vm.treeData = new kendo.data.HierarchicalDataSource({ data: result });
	                    CommonService.populateDataToTree(templateUrl, title, vm.treeData, vm, popupId);
	                }
	            });
	        }

	        function showGridPopupForm(vm, sourceLink, label, id, value, name, popupId,searchtype) {
	            $.ajax({
	                type: "GET",
	                url: RestEndpoint.BASE_SERVICE_URL + sourceLink,
	                contentType: "application/json; charset=utf-8",
	                success: function (result) {
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
	                        columns: [{
	                            field: id,
	                            hidden: true
	                        }, {
	                            title: "Mã",
	                            field: value,
	                            width: 120
	                        }, {
	                            title: "Tên",
	                            field: name,
	                            width: 120
	                        }]
	                    });
	                    CommonService.populateDataToGrid(templateUrl, title, vm.gridOptions, vm, popupId,searchtype);
	                }
	            });
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

	        function onSave(popupId) {
	            if (popupId == attrs.popupId) {
	            	selectionProcessed = true;
	                $scope.modelId = tempSelectValue;
	                $scope.modelName = tempSelectName;
	                if(attrs.change!=undefined && attrs.change!=null){
                    	$rootScope.$broadcast(attrs.change); // thay the ng-change
                    }
	            }
	        }

	        function onCancel() {
	            //do nothing
	        }

	        setTimeout(function(){
	        	if (attrs.comboSourceLink != undefined) {	
	        		
	        		$(element).children('div').children('div').children('i').on('click', function (e) {
                        var vm = $scope.caller;
                        vm.onRowChange = onRowChange;
                        vm.onCancel = onCancel;
                        vm.onSave = onSave;
                        vm.onChangePopupForm = onChangePopupForm;
                        if (attrs.popupType == 'grid') {
                            showGridPopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName, attrs.popupId ,attrs.searchtype);
                        } else if (attrs.popupType == 'tree') {
                            showTreePopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.popupId);
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
					                url: RestEndpoint.BASE_SERVICE_URL + attrs.comboSourceLink
					            },
					            parameterMap: function (data, action) {
					            	if(action === "read") {
					            		if (data.filter.filters.length > 0) {
					            			return {
	    				                    	term: data.filter.filters[0].value
	    				                    };
					            		}	    				                    
					                } else {
					                    return data;
					                }
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
		        	                $('#' + attrs.comboId).data('kendoAutoComplete').value(item[attrs.comboName]);
		                        });		                    		
	        	                if(attrs.change!=undefined && attrs.change!=null){
                                	$rootScope.$broadcast(attrs.change); // thay the ng-change
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