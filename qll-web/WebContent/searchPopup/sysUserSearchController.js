/* Modal Controller */
MetronicApp.controller('sysUserSearchController', [
		'$scope',
		'dataTree',
		'caller',
		'modalInstance1',
		'gridOptions',
		'popupId',
		'isMultiSelect',
		'CommonService',
		'htmlCommonService',
		'SearchService',
		'PopupConst',
		'RestEndpoint',
		'$localStorage',
		'$rootScope',
		'popupSearchService',
		function($scope, dataTree, caller, modalInstance1,gridOptions, popupId, isMultiSelect,
				CommonService,htmlCommonService, SearchService, PopupConst, RestEndpoint,
				$localStorage, $rootScope, popupSearchService) {
          
			$rootScope.flag=false;
			
			$scope.modalInstance = modalInstance1;
			$scope.popupId = popupId;
			$scope.caller = caller;
			$scope.cancel = cancel;
			$scope.save = save;
			$scope.isMultiSelect = isMultiSelect;
			function fillTable(result){
			$scope.gridOptions =  kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
					noRecords: true,
					columnMenu:false,
					scrollable:false,
					messages: {
						noRecords : "Không có kết quả hiển thị"
					},
					pageSize:10,
					pageable: {
						refresh: false,
						pageSize:10,
						 pageSizes: [10, 15, 20, 25],
						messages: {
			                display: "{0}-{1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang",
			                empty: "Không có kết quả hiển thị"
			            }
					},
					dataBound: function (e) {
					    	     var grid = $("#gridView").data("kendoGrid");
					    	    grid.tbody.find("tr").dblclick(function (e) {
					    	        var dataItem = grid.dataItem(this);
					    	        $('#'+dataItem.code).click();
					    	    });
					    	},
					columns:[{
						title: "TT",
						field: "#",
						width:'6%',
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
					}, 
					         {
						title: "Tên đăng nhập",
						field: "loginName",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Mã nhân viên",
						field: "employeeCode",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Họ tên",
						field: "fullName",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Email",
						field: "email",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "SĐT",
						field: "phoneNumber",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Chọn",
						 template: 
					        	'<div class="text-center "> '	+
				        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
				        	'			<i id="#=code#" ng-click=save(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
				        	'		</a>'
								+'</div>',  
				        width: "15%",
				        field:"stt"
					}
					]
				});

				
				}

		$( document ).ready(function() {
			var obj={};
			if($('#treeviewUnits')){
				popupSearchService.getSysUser(obj).then(function(result){
					var data=genData(result.plain(),null);
					fillTable(result.plain());
				 });
				 }
			});
			
			function genData(items, parent) {
	            var itemArr = [];
	            for (var i = 0; i < items.length; i++) {
	                if (items[i].parentId === parent) {
	                	var row = items[i];
	                	row.id = items[i].id;
	                	row.text = items[i].text;
	                    row.children = genData(items, items[i].id);
	                    itemArr.push(row);
	                }
	            }
	            return itemArr;
	        }
			
			$scope.onRowChange=onRowChange;
			
			function onRowChange(dataItem){
				$scope.dataItem=dataItem;
			}
			
			function cancel() {
				htmlCommonService.dismissPopup();
			}

			function save(dataItem) {
			if(dataItem){
			caller.onSaveSysUser(dataItem);
			htmlCommonService.dismissPopup();
			} else{
				if ($scope.dataItem) {
				caller.onSave($scope.dataItem, $scope.popupId);
				htmlCommonService.dismissPopup();
				} else {
					if($scope.parent){
						caller.onSave($scope.parent);
						htmlCommonService.dismissPopup();
					} else{
						if(confirm('Chưa chọn bản ghi nào!')){
							htmlCommonService.dismissPopup();
						}
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
				// $("#deptCode").val($("#deptCode").val().trim());
				// $("#deptName").val($("#deptName").val().trim());
				trimSpace();
				if($scope.parent){
				$scope.searchGrid.id=$scope.parent.id;
				}
				var obj = {};
				if(!! $scope.searchGrid)
					 obj = {
							keySearch : $scope.searchGrid.code,
						}
				popupSearchService.getSysUser(obj).then(function(result){
					var grid =	$scope.gridView;
					grid.dataSource.data(result.plain().data);
					fillTable(result.plain());
					grid.refresh();
				});
			}
			
			
			
		} ]);