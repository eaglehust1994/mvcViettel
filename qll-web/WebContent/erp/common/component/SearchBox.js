angular.module('MetronicApp').directive('searchBox', function (RestEndpoint, CommonService, gettextCatalog) {
    var tempSelectValue;
    var tempSelectName;
    return {
	    restrict: 'AE',
	    scope: {
	        modelLabel: "@",
	        modelId: '=',
	        modelName: '=',
	        caller: '=',
	        req:"@",
	        
	        searchtype:"=",
	        popupId: "=",
	        popupType: '=',
	        popupSourceLink: '=',

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
	    template: '<div class="form-group col-md-6">'+
				        '<label class="col-md-4 control-label {{req}}">{{modelLabel}}</label>'+
				        '<div class="col-md-8">'+
				            '<div class="input-icon right">'+
				                '<i class="fa fa-search"></i>'+
				                '<input type="hidden" ng-model="modelId" />'+
				                '<input id="{{comboId}}" kendo-auto-complete class="form-control width100" ng-model="modelName">' +
				            '</div>'+
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
	                $scope.modelId = tempSelectValue;
	                $scope.modelName = tempSelectName;
	            }
	        }

	        function onCancel() {
	            //do nothing
	        }

	        if (attrs.comboSourceLink != undefined) {
	            var dataList = [];
	            $.ajax({
	                type: "GET",
	                url: RestEndpoint.BASE_SERVICE_URL + attrs.comboSourceLink,
	                contentType: "application/json; charset=utf-8",
	                success: function (result) {
	                    for (var i = 0; i < result.length; i++) {
	                        dataList.push(result[i]);
	                    }

	                    var moreObject = new Object;
	                    moreObject[attrs.comboValue] = 0;
	                    moreObject[attrs.comboName] = "Search More";
	                    dataList.push(moreObject);

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
	                        dataSource: dataList,
	                        dataTextField: attrs.comboName,
	                        dataValueField: attrs.comboValue,
	                        filter: "contains",
	                        select: function (e) {
	                            var dataItem = this.dataItem(e.item.index());
	                            if (dataItem[attrs.comboValue] != 0) {
	                                $scope.modelId = dataItem[attrs.comboValue];
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
	                                e.preventDefault();
	                            }
	                        }
	                    });
	                }
	            });
	        }
	    }
	  };
 });