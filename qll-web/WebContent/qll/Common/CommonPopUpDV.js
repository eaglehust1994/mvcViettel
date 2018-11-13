/* Modal Controller */
MetronicApp.controller('popupDepartmentControllerQll', [
		'$scope',
		'dataTree',
		'caller',
		'modalInstance1',
		'gridOptions',
		'popupId',
		'isMultiSelect',
		'CommonService',
		'SearchService',
		'PopupConst',
		'RestEndpoint',
		'$localStorage',
		'$rootScope',
		function($scope, dataTree, caller, modalInstance1,gridOptions, popupId, isMultiSelect,
				CommonService, SearchService, PopupConst, RestEndpoint,
				$localStorage, $rootScope) {
          
			$rootScope.flag=false;
			
			$scope.modalInstance = modalInstance1;
			$scope.popupId = popupId;
			$scope.caller = caller;
			$scope.cancel = cancel;
			$scope.save = save;
			$scope.isMultiSelect = isMultiSelect;
//			function fillTable(result){
//			$scope.gridOptions =  kendoConfig.getGridOptions({
//					autoBind: true,
//					resizable: true,
//					dataSource: result,
//					noRecords: true,
//					columnMenu:false,
//					scrollable:false,
//					messages: {
//						noRecords : "Không có kết quả hiển thị"
//					},
//					pageSize:10,
//					pageable: {
//						refresh: false,
//						pageSize:10,
//						 pageSizes: [10, 15, 20, 25],
//						messages: {
//			                display: "{0}-{1} của {2} kết quả",
//			                itemsPerPage: "kết quả/trang",
//			                empty: "Không có kết quả hiển thị"
//			            }
//					},
//					dataBound: function (e) {
//					    	     var grid = $("#gridView").data("kendoGrid");
//					    	    grid.tbody.find("tr").dblclick(function (e) {
//					    	        var dataItem = grid.dataItem(this);
//					    	        $('#'+dataItem.code).click();
//					    	    });
//					    	},
//					columns:[{
//						title: "TT",
//						field: "#",
//						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
//						width: "12%",
//						 headerAttributes: {
//								style: "text-align:center;"
//							},
//							attributes: {
//								style: "text-align:center;"
//							},
//					}, 
//					         {
//						title: "Mã phòng<br> ban",
//						field: "code",
//						width: "18%",
//						headerAttributes: {
//							style: "text-align:center;"
//						},
//						attributes: {
//							style: "text-align:left;"
//						},
//					}, {
//						title: "Tên phòng ban",
//						field: "text",
//						width: "30%",
//						 headerAttributes: {
//								style: "text-align:center;"
//							},
//							attributes: {
//								style: "text-align:left;"
//							},
//					}, {
//						title: "Đơn vị cha",
//						field: "parentName",
//						width: "30%",
//						 headerAttributes: {
//								style: "text-align:center;"
//							},
//							attributes: {
//								style: "text-align:left;"
//							},
//					},{
//						title: "Ngày hiệu lực",
//						field: "effectDate",
//						width: "20%",
//						 headerAttributes: {
//								style: "text-align:center;"
//							},
//							attributes: {
//								style: "text-align:center;"
//							},
//					},{
//						title: "Ngày hết hiệu lực",
//						field: "endDate",
//						width: "25%",
//						 headerAttributes: {
//								style: "text-align:center;"
//							},
//							attributes: {
//								style: "text-align:center;"
//							},
//					},{
//						title: "Chọn",
//						 template: 
//					        	'<div class="text-center "> '	+
//				        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
//				        	'			<i id="#=code#" ng-click=save(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
//				        	'		</a>'
//								+'</div>',  
//				        width: "15%",
//				        field:"stt"
//					}]
//				});
//
//				
//				}

		$( document ).ready(function() {
			var obj={};
			if($('#treeviewUnits')){
				CommonService.getDepartment(obj).then(function(result){
					if(result.error){
        				toastr.error(result.error);
        				return;
        			}
					var data=genData(result.plain(),null);
					setupTree(data);
//					fillTable(result.plain());
					
					
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
			 document.getElementById("deptCode").focus();
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
			caller.onSave(dataItem);
			CommonService.dismissPopup1();
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