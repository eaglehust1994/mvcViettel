﻿﻿angular.module('MetronicApp').directive('autoSearch', function ($rootScope,RestEndpoint, CommonService, gettextCatalog, $http) {
	var tempSelectValue = null;
    var tempSelectName = null;
	var selectionProcessed = false;
    var searchAutocompleteMinLength = 1;
	var recordpopup = 0;
    return {
	    restrict: 'AE',
	    scope: {
	        modelLabel: "@",
	        modelId: '=',
	        readonly:'=',
	        modelName: '=',
	        modelCode: '=',
	        caller: '=',
	        req:"@",
	        msg:"@",
	        iddiv:"@",
	        idlable:"@",
	        disabled:'=',
	        searchtype:"=", 
			focusE:"=",
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
	      	eventPaste: '&',
			
	    },
	    template: '<div class="form-group col-md-6" id="{{iddiv}}">'+
				        '<label id="{{idlable}}" class="col-md-4 control-label {{req}}">{{modelLabel}}</label>'+
				        '<div class="col-md-8">'+
				            '<div class="input-icon right">'+
				                '<i class="fa fa-search"></i>'+
				                '<input type="hidden" ng-model="modelId" />'+
				                '<input id="{{comboId}}" name="{{comboId}}" class="form-control width100" ng-model="modelName" data-required-msg="Trường không được để trống">' +
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
                    var templateUrl = 'wms/popup/gridView.html';
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
					
                
                		templateUrl = 'wms/popup/gridView.html';
                	
					var title = gettextCatalog.getString(label);
					var title = "Danh sách kho hàng";
					vm.gridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						sortable: true,
		                resizable: false,
		                columnMenu: false,
						scrollable: false,
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
                        	pageSize: 10,
                        	pageSizes: [10, 20, 30],
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
						columnMenu: false,
						messages: {
							noRecords: gettextCatalog.getString("Không có dữ liệu")
						},
					    dataBinding: function() {
					    	recordpopup = (this.dataSource.page() -1) * this.dataSource.pageSize();
					      },
					      dataBound: function (e) {
					    	   var grid = $("#gridView").data("kendoGrid");
					    	    grid.tbody.find("tr").dblclick(function (e) {
					    	        var dataItem = grid.dataItem(this);
					    	        document.getElementById(dataItem.code).click();
					    	    });
					    	},
//					      columns: $scope.comboGrid
					      columns: [ {
								title: "TT",
								field: "#",
						        template: function () {
									return ++recordpopup;
								},
								width: 40,
						        filterable: false,
						        headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:center;"},
							},{
								title: "Mã kho",
								field: "code",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 100
							}, {
								title: "Tên kho",
								field: "name",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 150
							},{
								title: "Đơn vị quản lý",
								field: "departmentName",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 100
							},{
								title: "Chọn",
								 template: 
							        	'<div class="text-center "> '	+
						        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
						        	'			<i id="#=code#" ng-click=save(dataItem) class="fa fa-check color-green #=code#" aria-hidden="true"></i> '+
						        	'		</a>'
										+'</div>',  
						        width: 50,
						        field:"stt"
							}
							]
					});
					CommonService.populateDataToGridplus(templateUrl, title, vm.gridOptions, vm, popupId,searchtype,true,"filterTextStock");
      
	        }
	        var record=0;
	        function showGridPopupUser(vm, sourceLink, label, id, value, name,template, popupId,searchtype) {
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
					
                	
                		templateUrl = 'wms/popup/gridViewCreateUser.html';
                	
                	
//					var title = gettextCatalog.getString(label);
					var title = "Danh sách người dùng";
					vm.gridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						sortable: true,
		                resizable: false,
		                columnMenu: false,
						scrollable: false,
						dataSource: {
							serverPaging: true,
			                
							transport: {
					            read: {
					               url: RestEndpoint.BASE_SERVICE_URL + "sysUserServiceRest/sysUserwms/doSearchUserInPopup",			       
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
						dataBinding: function() {
							record = (this.dataSource.page() -1) * this.dataSource.pageSize();
						},
						pageable: {
                        	pageSize: 10,
                        	pageSizes: [10, 20, 30],
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
						columnMenu: false,
						messages: {
							noRecords: gettextCatalog.getString("Không có dữ liệu")
						},
					    dataBinding: function() {
					    	record = (this.dataSource.page() -1) * this.dataSource.pageSize();
					      },
					      dataBound: function (e) {
					    	   var grid = $("#gridView").data("kendoGrid");
					    	    grid.tbody.find("tr").dblclick(function (e) {
					    	        var dataItem = grid.dataItem(this);
					    	      
									 document.getElementById(dataItem.sysUserId).click();
					    	    });
					    	},
//					      columns: $scope.comboGrid
					      columns: [ {
								title: "TT",
								field: "#",
								template: function () {
									  return ++record;
									 },
								width: 40,
						        filterable: false,
						        headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:center;"},
							},{
								title: "Tên đăng nhập",
								field: "loginName",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 100
							},{
								title: "Mã nhân viên",
								field: "employeeCode",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:leftr;"},
								width: 100
							}, {
								title: "Họ tên",
								field: "fullName",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 150
							}, {
								title: "Email",
								field: "email",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 100
							}, {
								title: "SĐT",
								field: "phoneNumber",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 100
							},{
								title: "Chọn",
								 template:
							        	'<div class="text-center "> '	+
						        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
						        	'			<i id="#=sysUserId#" ng-click=save(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
						        	'		</a>'
										+'</div>',  
						        width: 50,
						        field:"stt"
							}]
					});
					CommonService.populateDataToGridplus(templateUrl, title, vm.gridOptions, vm, popupId,searchtype,true,"filterTextUser");
      
	        }
	        
	        function showGridPopupGoods(vm, sourceLink, label, id, value, name,template, popupId,searchtype) {
//				$scope.comboSearch.name = '';
//				$scope.comboSearch.value = '';
//				$scope.comboSearch.isSize = false;
	        	tempSelectName = '' ;
	        	tempSelectValue = '' ;
	        	$scope.caller.disablesavepop = true;
				var vm = $scope.caller;
				vm.searchGridGoods.page = attrs.page;
				vm.searchGridGoods.pageSize = attrs.pageSize;
				vm.searchGridGoods.isSize = false;
					
                	
                		templateUrl = 'wms/popup/gridViewGoodsPopup.html';
                	
//					var title = gettextCatalog.getString(label);
					var title = "Danh sách hàng hóa";
					vm.gridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						sortable: true,
		                resizable: false,
		                columnMenu: false,
						scrollable: false,
						dataSource: {
							serverPaging: true,
			                
							transport: {
					            read: {
					               url: RestEndpoint.BASE_SERVICE_URL + "stockRsServiceRest/doSearchGoods",			       
					               contentType: "application/json; charset=utf-8",
					               type: "POST"
					            },
					            
				         
					           parameterMap: function(options, type) {
				    	  
                           
					        	 vm.searchGridGoods.page = options.page
				        	 vm.searchGridGoods.pageSize = options.pageSize
				        	 
				    	  return  JSON.stringify(vm.searchGridGoods)
				    

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
                        	pageSize: 10,
                        	pageSizes: [10, 20, 30],
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
					    	recordpopup = (this.dataSource.page() -1) * this.dataSource.pageSize();
					      },
						dataBound: function (e) {
					    	   var grid = $("#gridView").data("kendoGrid");
					    	    grid.tbody.find("tr").dblclick(function (e) {
					    	        var dataItem = grid.dataItem(this);
					    	         document.getElementById(dataItem.code).click();
					    	    });
					    	},
//					      columns: $scope.comboGrid
					      columns: [ {
								title: "TT",
								field: "#",
						        template: function () {
									return ++recordpopup;
								},
								width: 40,
						        filterable: false,
						        headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:center;"},
							},{
								title: "Mã hàng hóa",
								field: "code",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 80
							}, {
								title: "Tên hàng hóa",
								field: "name",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 150
							},{
								title: "Đơn vị tính",
								field: "unitTypeName",
								headerAttributes: {style: "text-align:center;"},
								attributes: {style: "text-align:left;"},
								width: 80
							},{
								title: "Chọn",
								 template: 
							        	'<div class="text-center "> '	+
						        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
						        	'			<i id="#=code#" ng-click=save(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
						        	'		</a>'
										+'</div>',  
						        width: 50,
						        field:"stt"
							}
							]
					});
					CommonService.populateDataToGridplus(templateUrl, title, vm.gridOptions, vm, popupId,searchtype,true,"filterTextGoods");
      
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
	        
	        function searchOnGridPop() {
			trimSpace();
	        	var grid = $("#gridView").data("kendoGrid");
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10,
					
				});
				// grid.refresh();
			}
	        }
	        
	        function searchOnGoodsPopup() {
			trimSpace();
	        	var grid = $("#gridView").data("kendoGrid");
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10,
					
				});
				// grid.refresh();
			}
	        }
	        
	        function searchOnUserPopup() {
			trimSpace();
			var grid = $("#gridView").data("kendoGrid");
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10,
					
				});
				// grid.refresh();
			}
	        	 
	        }
	        function resetSearchPop(popupId) {
	        	 var vm = $scope.caller;
	        	 vm.searchGrid = {isSize : false};
	        	 vm.popupGrid.dataSource.read();
	        }
	        

	        function onPopupSave(popupId,dataItem) {
	            if (popupId == attrs.popupId) {
	            	selectionProcessed = true;
	            	if(dataItem){
	            		$scope.modelId = dataItem[attrs.comboValue];
		                $scope.modelName = dataItem[attrs.comboName];
		                $scope.modelCode = dataItem[attrs.comboCode];
	            	} else {
	                $scope.modelId = tempSelectValue;
	                $scope.modelName = tempSelectName;
	            	}
					if (typeof attrs.eventchange !== "undefined" && attrs.eventchange != null){
						$rootScope.$broadcast(attrs.eventchange, $scope.modelId);
					}
					$("input[name=" + attrs.comboId + "]").focus();
				}
	        }

	       function saveGrid(dataItem) {
	            	selectionProcessed = true;
	                $scope.modelId = dataItem[attrs.comboValue];
	                $scope.modelName = dataItem[attrs.comboName];
	               
	        }
	        
	        function onCancel() {
	            //do nothing
	        }
	        

			
			
	        setTimeout(function(){
				
				
				
	        	if (typeof attrs.req !== "undefined" && attrs.req === "req"){
	                $("input[name=" + attrs.comboId + "]").attr("required", true);
	            }
	        	if ($scope.readonly){
	                $("input[name=" + attrs.comboId + "]").attr("readonly", true);
	            }
				
	        	$(element).find($(".fa.fa-times")).on('click', function (e) {
	        		$scope.$apply(function () {
                		$scope.modelId = null;
                        $scope.modelName = null;
                        $scope.modelCode = null;
				$("input[name=" + attrs.comboId + "]").focus();
                    });
        			$('#' + attrs.comboId).val("");
        		});
	        	
	        	if (attrs.comboSourceLink != undefined) {


					element.bind('change', function(e) {
						if(!selectionProcessed)
						processSearch();
					});
					
					
	        		$(element).children('div').children('div').children('i').on('click', function (e) {
                        var vm = $scope.caller;
                        vm.onRowChange = onRowChange;
                        vm.onCancel = onCancel;
                        vm.onPopupSave = onPopupSave;
                        vm.onChangePopupForm = onChangePopupForm;
                        vm.searchOnGridPop = searchOnGridPop;
                        vm.searchOnGoodsPopup = searchOnGoodsPopup;
                        vm.searchOnUserPopup = searchOnUserPopup;
                        vm.resetSearchPop = resetSearchPop;
                        
                        if (attrs.popupType == 'grid') {
                            showGridPopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName, attrs.popupId ,attrs.searchtype);
                        } else if (attrs.popupType == 'tree') {
                            showTreePopupForm(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.popupId);
                        }else if(attrs.popupType == 'gridplus'){
                        	vm.searchGrid = {};
							var popupSourceLink= "stockRsServiceRest/doSearchStockInPopUp";
							if(attrs.domain){
								popupSourceLink= "stockRsServiceRest/doSearchStockInPopUpDomain";
							}
                        	showGridPopupFormPlus(vm, popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName,attrs.templateUrl, attrs.popupId,attrs.searchtype);
                        }else if(attrs.popupType == 'gridplusGoods'){
                        	vm.searchGridGoods = {};
                        	showGridPopupGoods(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName,attrs.templateUrl, attrs.popupId,attrs.searchtype);
                        }else if(attrs.popupType == 'gridplusUser'){
                        	vm.searchGrid = {};
                        	showGridPopupUser(vm, attrs.popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName,attrs.templateUrl, attrs.popupId,attrs.searchtype);
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
	                
					if ($scope.focusE){
	                $('#' + attrs.comboId).focus();
						} 

	                // $('#' + attrs.comboId).keypress(function(event) {
			        	// if (event.which == kendo.keys.ENTER) {
	                        // if (!selectionProcessed) {
	                            // selectionProcessed = true;
	                            // processSearch();
	                        // } else {
	                            // selectionProcessed = false;
	                        // }
	                    // }
			        // });
	                
					
					
	                function processSearch() {
	                    var autocomplete = $('#' + attrs.comboId).data('kendoAutoComplete');
	                    var searchDataItem = null;
						if(autocomplete){
						
							if (autocomplete.value() != "" ) {
								if (autocomplete.value().length >= searchAutocompleteMinLength) {
									autocomplete.search(autocomplete.value());
									if (autocomplete.dataItem(0) != undefined) {
										searchDataItem = autocomplete.dataItem(0);
									}
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
	                            vm.onPopupSave = onPopupSave;
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
		                        $scope.modelCode = null;
	                        });	                    	
	                    	$('#' + attrs.comboId).data('kendoAutoComplete').value(null);
	                    }
	                }
		        }
	        }, 10);
	         	        
	    }
	  };
 });
