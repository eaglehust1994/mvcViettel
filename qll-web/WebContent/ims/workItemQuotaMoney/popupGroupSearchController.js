/* Modal Controller */
MetronicApp.controller('popupGroupSearchController', [
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
		'workItemQuotaService',
		function($scope, dataTree, caller, modalInstance1,gridOptions, popupId, isMultiSelect,
				CommonService, htmlCommonService, SearchService, PopupConst, RestEndpoint,
				$localStorage, $rootScope, workItemQuotaService) {
          
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
						width:'10%',
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
					}, 
					         {
						title: "Mã đơn vị",
						field: "code",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Tên đơn vị",
						field: "name",
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
				workItemQuotaService.getAllSysGroup(obj).then(function(result){
					var items = result.data;
					var data=genData(items,null);
					setupTree(data);
					fillTable(result.plain());
				 });
				 }
			});
			
			function genData(items, parent) {
	            var itemArr = [];
	            for (var i = 0; i < items.length; i++) {
	                if (items[i].parentId === parent) {
	                	var row = items[i];
	                	row.id = items[i].sysGroupId;
	                	row.text = items[i].name;
	                    row.children = genData(items, items[i].sysGroupId);
	                    itemArr.push(row);
	                }
	            }
	            return itemArr;
	        }
			
				
			function onChangeTree(dataItem){
				 var obj={};
				obj.sysGroupId=dataItem.id
				 $scope.parent=dataItem;
				workItemQuotaService.getAllSysGroup(obj).then(function(result){
					 var grid =	$scope.gridView;
					 grid.dataSource.data(result.plain().data);
					 if(result.plain().length>0){
						 grid.dataSource.page(1);
						 }
					 grid.refresh();
				 });
			 }
			
			  function setupTree(dataSource) {
		    		$('#treeviewUnits').jstree({
				        "plugins" : [ "search", "dnd","sort"],
				        'core' : {
				        	'multiple' : false,
				        	'data' : dataSource,
		        		    'check_callback' : true
		        		}
		    		});
					    
				    var to = false;
				    $('#filterText').keyup(function () {
				    	if(to) { clearTimeout(to); }
				    	to = setTimeout(function () {
				    		var v = $('#filterText').val();
					        $('#treeviewUnits').jstree(true).search(v);
				      	}, 250);
				    });
					    
				    $('#treeviewUnits').on("select_node.jstree", function (e, data) {
				    	
				    		var dataItem = data.node.original;
					    	onChangeTree(dataItem);
				    	
				    });
				   
				 setTimeout(function(){
					 document.getElementById("code").focus();
				},1000);
					
	        }
			
			$scope.onRowChange=onRowChange;
			
			function onRowChange(dataItem){
				$scope.dataItem=dataItem;
			}
			
			function cancel() {
				CommonService.dismissPopup1();
				// caller.cancel();
			}

			function save(dataItem) {
				if(dataItem){
					caller.onSaveGroupId(dataItem);
					htmlCommonService.dismissPopup();
				} else{
					if ($scope.dataItem) {
					caller.onSave($scope.dataItem, $scope.popupId);
					CommonService.dismissPopup1();
					} else {
						if($scope.parent){
							caller.onSave($scope.parent);
							CommonService.dismissPopup1();
						} else{
							if(confirm('Chưa chọn bản ghi nào!')){
								CommonService.dismissPopup1();
							}
						}
					}
				}
				
			}

			$scope.filterTree=filterTree
			function filterTree(keyEvent){
				filter($scope.treeView.dataSource, keyEvent.target.value.toLowerCase());
			}
			
			function filter(dataSource, query) {
                var hasVisibleChildren = false;
                var data = dataSource instanceof kendo.data.HierarchicalDataSource && dataSource.data();

                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    var text = item.name.toLowerCase();
                    var itemVisible =
                        query === true // parent already matches
                        || query === "" // query is empty
                        || text.indexOf(query) >= 0; // item text matches
														// query

                    var anyVisibleChildren = filter(item.children, itemVisible || query); // pass
																							// true
																							// if
																							// parent
																							// matches

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
				workItemQuotaService.getAllSysGroup(obj).then(function(result){
					var grid =	$scope.gridView;
					grid.dataSource.data(result.plain().data);
					fillTable(result.plain());
					grid.refresh();
				});
			}
			
			
			
		} ]);