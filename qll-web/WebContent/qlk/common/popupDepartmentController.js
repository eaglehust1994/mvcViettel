/* Modal Controller */
MetronicApp.controller('popupDepartmentController', [
		'$scope',
		'dataTree',
		'caller',
		'modalInstance',
		'gridOptions',
		'popupId',
		'isMultiSelect',
		'CommonService',
		'SearchService',
		'PopupConst',
		'RestEndpoint',
		'$localStorage',
		'$rootScope',
		function($scope, dataTree, caller, modalInstance,gridOptions, popupId, isMultiSelect,
				CommonService, SearchService, PopupConst, RestEndpoint,
				$localStorage, $rootScope) {
          
			$rootScope.flag=false;
			
			$scope.modalInstance = modalInstance;
			$scope.popupId = popupId;
			$scope.caller = caller;
			$scope.cancel = cancel;
			$scope.save = save;
			$scope.isMultiSelect = isMultiSelect;
			$scope.gridOptions = gridOptions;

			function genData(items, parent) {
	            var itemArr = [];
	            for (var i = 0; i < items.length; i++) {
	                if (items[i].parentId === parent) {
	                	var row = items[i];
	                	row.id = items[i].id;
	                	row.text = items[i].text;
	                    row.items = genData(items, items[i].id);
	                    itemArr.push(row);
	                }
	            }
	            return itemArr;
	        }
			$scope.data = new kendo.data.HierarchicalDataSource({ data: genData(dataTree,null) });

			 $scope.treeViewOptions = {
		                dataSource: $scope.data,
		                dataTextField: "text",
		                loadOnDemand: false,
		                expandAll: true, 
		                dataBound: function (e) {
		                    e.sender.expand(e.node);
		                },
		            }
			
			 
			 
			$scope.onChangeTree=onChangeTree;
			function onChangeTree(dataItem){
				var obj={};
				obj.id=dataItem.id
				$scope.parent=dataItem;
				CommonService.getDepartment(obj).then(function(result){
					var grid =	$scope.gridView;
					grid.dataSource.data(result.plain());
					if(result.plain().length>0){
						grid.dataSource.page(1);
						}
					grid.refresh();
				});
			}
			
			$scope.onRowChange=onRowChange;
			
			function onRowChange(dataItem){
				$scope.dataItem=dataItem;
			}
			
			function cancel() {
				CommonService.dismissPopup();
				// caller.cancel();
			}

			function save() {
				if ($scope.dataItem) {
				caller.onSave($scope.dataItem, $scope.popupId);
				CommonService.dismissPopup();
				} else {
					if($scope.parent){
						caller.onSave($scope.parent);
						CommonService.dismissPopup();
					} else{
						if(confirm('Chưa chọn bản ghi nào!')){
							CommonService.dismissPopup();
						}
					}
				}
				
				
			}

			$scope.filterTree=filterTree
//			 $("#filterText").keyup(function (e) {
			function filterTree(keyEvent){
				filter($scope.treeView.dataSource, keyEvent.target.value.toLowerCase());
			}
			
			function filter(dataSource, query) {
                var hasVisibleChildren = false;
                var data = dataSource instanceof kendo.data.HierarchicalDataSource && dataSource.data();

                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    var text = item.text.toLowerCase();
                    var itemVisible =
                        query === true // parent already matches
                        || query === "" // query is empty
                        || text.indexOf(query) >= 0; // item text matches query

                    var anyVisibleChildren = filter(item.children, itemVisible || query); // pass true if parent matches

                    hasVisibleChildren = hasVisibleChildren || anyVisibleChildren || itemVisible;

                    item.hidden = !itemVisible && !anyVisibleChildren;
                }

                if (data) {
                    // re-apply filter on children
                    dataSource.filter({ field: "hidden", operator: "neq", value: true });
                }

                return hasVisibleChildren;
            }

			$scope.doSearch= function(){
				if($scope.parent){
				$scope.searchGrid.id=$scope.parent.id;
				}
				CommonService.getDepartment($scope.searchGrid).then(function(result){
					var grid =	$scope.gridView;
					grid.dataSource.data(result.plain());
					if(result.plain().length>0){
						grid.dataSource.page(1);
						}
					grid.refresh();
				});
			}
			
		} ]);