angular.module('MetronicApp').directive('comboBox', function (RestEndpoint, CommonService, gettextCatalog) {    
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
	        
            popupId: "=",
	        popupType: '=',
            popupSourceLink: '=',

	        comboId: "@",
	        comboSourceLink: "@",
	        comboName: "@",
	        comboValue: "@",
            comboCode: "@",
            acctmoney:"@",
            
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
				        '<div class="col-md-8">' +
                            '<input type="hidden" ng-model="modelId" />' +
				            '<input id="{{comboId}}" class="form-control width100" ng-model="modelName">' +
				        '</div>'+
				    '</div>',
	    replace: true,
	    link: function ($scope, element, attrs, ctrl) {
	        

	        function showTreePopupForm(vm, sourceLink, label, popupId) {
	            $.ajax({
	                type: "GET",
	                url: RestEndpoint.BASE_SERVICE_URL + sourceLink,
	                contentType: "application/json; charset=utf-8",
	                success: function (result) {
	                    var templateUrl = 'views/popup/treeView.html';
	                    var title = gettextCatalog.getString(label);
	                    vm.treeData = new kendo.data.HierarchicalDataSource({ data: result.plain() });
	                    CommonService.populateDataToTree(templateUrl, title, vm.treeData, vm, popupId);
	                }
	            });
	        }
	        
	        function showGridPopupForm(vm, sourceLink, label, id, value, name, popupId) {
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
	                    CommonService.populateDataToGrid(templateUrl, title, vm.gridOptions, vm, popupId);
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
	                $('#' + attrs.comboId).data('kendoComboBox').value(tempSelectName);
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
	                	if(attrs.acctmoney=='VND'){
	                		dataList=result;
	                		for (var i = 0; i < dataList.length; i++) {
	                			if(dataList[i].name == "VND" ){
	                				dataList.splice(i, 1);
	            	        	}
		                    }
	                	} 
	                	else{
	                    for (var i = 0; i < result.length; i++) {
	                        dataList.push(result[i]);
	                    }
	                	}
	                    var moreObject = new Object;
	                    moreObject[attrs.comboValue] = 0;
	                    moreObject[attrs.comboName] = "Search More";
	                    dataList.push(moreObject);

	                    $('#' + attrs.comboId).kendoComboBox({
	                        dataTextField: attrs.comboName,
	                        dataValueField: attrs.comboValue,
	                        dataSource: dataList,
	                        filter: "contains",
	                        suggest: true,
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
	                                    showGridPopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName, attrs.popupId);
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