﻿angular.module('MetronicApp').directive('multiSelect', function ($rootScope,RestEndpoint, CommonService, gettextCatalog, $http) {
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
	        caller: '=',
	        req:"@",
	        msg:"@",
	        iddiv:"@",
	        idlable:"@",
			focusE:"=",
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
	      	eventPaste: '&',
			
	    },
	    template: '<div class="form-group col-md-6" id="{{iddiv}}">'+
				        '<label id="{{idlable}}" class="col-md-4 control-label {{req}}">{{modelLabel}}</label>'+
				        '<div class="col-md-8">'+
				            '<div class="input-icon right">'+
				                '<i class="fa fa-search"></i>'+
			                '<input id="{{comboId}}id" type="hidden" ng-model="modelId" />'+
				                '<input  id="{{comboId}}" name="{{comboId}}" class="form-control width100" ng-model="modelName" data-required-msg="Trường không được để trống">' +
				            '</div>'+
				            '<span data-for="{{comboId}}" class="k-invalid-msg"></span>'+
				        '</div>'+
				        '<div class="One_icon">'+
						'<i class="fa fa-times" aria-hidden="true"></i>'+
					'</div>'+
				    '</div>',
	    replace: true,
	    link: function($scope, element, attrs, ctrl) {	        
	        

	        
	        function showGridPopupFormPlus(vm, sourceLink, label, id, value, name,template, popupId,searchtype) {

	        	tempSelectName = '' ;
	        	tempSelectValue = '' ;
	        	$scope.caller.disablesavepop = true;
				var vm = $scope.caller;
				vm.commonSearch={};
				vm.commonSearch.page = attrs.page;
	        	vm.commonSearch.pageSize = attrs.pageSize;
				vm.commonSearch.isSize = false;
					
                
                		templateUrl = 'wms/popup/gridViewMulti.html';
                	
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
				    	  
                           
				        	 vm.commonSearch.page = options.page
				        	 vm.commonSearch.pageSize = options.pageSize
				        	 
				    	  return  JSON.stringify(vm.commonSearch)
				    

			             }
					        },
					        schema: {
					            
					        	total: function(response) {
					        		console.log(response);
					        	      return response.total; // total is returned in the "total" field of the response
					        	},
					            data:  function(response) {
					        		console.log(response);
					        		var list=response.data;
					        		for(var x in list){
					        			for(i in $scope.listCheck){
					        				if(list[x].fwmodelId===$scope.listCheck[i].fwmodelId){
					        					list[x].selected=true;
					        				}
					        			}
					        		}
					        		
					        	      return  list;// data is returned in the "data" field of the response
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
					      columns: [
							{
							title : "<input type='checkbox' id='checkalllistImpReq' name='gridchkselectall' ng-click='chkSelectAll();' ng-model='chkAll' />",
							template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='handleCheck(dataItem)' ng-model='dataItem.selected'/>",
							width: "5%",
							headerAttributes: {style:"text-align:center;"},
							attributes:{style:"text-align:center;"}
							},
							{
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
					CommonService.populateDataToGridplus(templateUrl, title, vm.gridOptions, vm, popupId,searchtype,true,"filterText");
      
	        }
	        
	        $scope.listCheck=[];
	    	function handleCheck(dataItem){
	    		if(dataItem.selected){
	    		$scope.listCheck.push(dataItem);
	    		} else {
	    			for(var i=0;i<$scope.listCheck.length;i++){
	    				if($scope.listCheck[i].fwmodelId===dataItem.fwmodelId){
	    				$scope.listCheck.splice(i,1);
	    				}
	    			}
	    		}
	    		}
	        
			function chkSelectAll(chkAll){
				if(chkAll ){
				if( $scope.checkSearch && $scope.dataSearch.length >0){
				$scope.listCheck=$scope.dataSearch;
				} else {
				var obj={};
				var linkApi="stockRsServiceRest/getListByNameOrCode";
				if(attrs.domain){
				var linkApi="stockRsServiceRest/getForAutoCompleteStockDomain";
				}
					CommonService.getallData(linkApi,obj).then(function(data){
						$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}

			};
	      
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
	        $scope.checkSearch=false;
			$scope.dataSearch=[];
	        function searchOnGridPop() {
				var vm = $scope.caller;
				$scope.checkSearch=true;
				
	        	var grid = $("#gridView").data("kendoGrid");
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10,
					
				});
				var linkApi="stockRsServiceRest/getListByNameOrCode";
				if(attrs.domain){
				var linkApi="stockRsServiceRest/getForAutoCompleteStockDomain";
				}
				CommonService.getallData(linkApi,vm.commonSearch).then(function(data){
						$scope.dataSearch=data.plain();
				})
				// grid.refresh();
			}
	        }
	        
	      
	        function resetSearchPop(popupId) {
	        	 var vm = $scope.caller;
	        	 vm.searchGrid = {isSize : false};
	        	 vm.popupGrid.dataSource.read();
	        }
	        

	        function onPopupSave(popupId,dataItem,flag) {
	            if (popupId == attrs.popupId) {
	            	selectionProcessed = true;
						
	            	if(!flag){
	            		$scope.modelId=[];
	            		$scope.modelId.push(dataItem[attrs.comboValue]);
		                $scope.modelName = dataItem[attrs.comboName];
	            	} else {
		               $scope.modelName=null;
					   $scope.modelId=[];
						for(var i=0;i<$scope.listCheck.length;i++){
						$scope.modelId.push($scope.listCheck[i][attrs.comboValue])
						if($scope.modelName){
						$scope.modelName=$scope.modelName+"; "+$scope.listCheck[i][attrs.comboName];
						} else{
						$scope.modelName=$scope.listCheck[i][attrs.comboName];
						}
					}
	            	}
					
	            }
	        }

			
	        function onCancel() {
	            
	        }
	        
			
			

	        setTimeout(function(){
			
			
	        	if (typeof attrs.req !== "undefined" && attrs.req === "req"){
	                $("input[name=" + attrs.comboId + "]").attr("required", true);
	            }
	        	/*if ($scope.focusE){
	                $('#' + attrs.comboId).focus();
	            }*/
	        	$(element).find($(".fa.fa-times")).on('click', function (e) {
	        		$scope.$apply(function () {
                		$scope.modelId = [];
                        $scope.modelName = null;
                        $scope.modelCode = null;
                    });
        			$('#' + attrs.comboId).val("");
        		});
	        	
	        	if (attrs.comboSourceLink != undefined) {
				
				element.bind('change', function(e) {
						if(selectionProcessed)
						processSearch();
					});
					$(element).children('div').children('div').children('i').on('click', function (e) {
                        var vm = $scope.caller;
                        vm.onRowChange = onRowChange;
                        vm.onCancel = onCancel;
                        vm.onPopupSave = onPopupSave;
                        vm.onChangePopupForm = onChangePopupForm;
                        vm.searchOnGridPop = searchOnGridPop;
                        vm.handleCheck=handleCheck;
						vm.chkSelectAll1=chkSelectAll;
                         $scope.listCheck=[];
						$scope.checkSearch=false;
						$scope.dataSearch=[];
						var popupSourceLink="stockRsServiceRest/doSearchStockInPopUp";
						if(attrs.domain){
						popupSourceLink="stockRsServiceRest/doSearchStockInPopUpDomain";
						}						
                        showGridPopupFormPlus(vm,popupSourceLink, attrs.modelLabel, attrs.comboValue, attrs.comboCode, attrs.comboName,attrs.templateUrl, attrs.popupId,attrs.searchtype);
                       
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
										$scope.modelId=[];
										$scope.modelName=null;
										selectionProcessed = false;
									}
									var comboSearch={}
									comboSearch.name = $scope.modelName;
									// $scope.comboSearch.pageSize = $scope.pageSize;
									return JSON.stringify(comboSearch);
								}
					        }
					    },
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
	                    } ,     
	                    ignoreCase: false
                    });
                    
                    if ($scope.focusE){
    	                $('#' + attrs.comboId).focus();
    						} 
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
	                    if (autocomplete.value() != "") {
	                        if (autocomplete.value().length >= searchAutocompleteMinLength) {
	                            autocomplete.search(autocomplete.value());
	                            if (autocomplete.dataItem(0) != undefined) {
	                                searchDataItem = autocomplete.dataItem(0);
									return;
	                            }
	                        }
	                    }
	                    navigateTo(searchDataItem);                   
	                }
	                
	                function navigateTo(item) {
	                    if (item != null) {
	                    	if (item[attrs.comboValue] != 0) {
	                    		$scope.$apply(function () {
	                    			
									$scope.modelId.length=0;
									$scope.modelId.push(item[attrs.comboValue]);
									$scope.modelName = item[attrs.comboName];
		                        });
	        	               
								if (typeof attrs.eventchange !== "undefined" && attrs.eventchange != null){
									$rootScope.$broadcast(attrs.eventchange, $scope.modelId);
								}
	                    	}                             
	                    } else {
	                    	$scope.$apply(function () {
	                    		$scope.modelId = [];
		                        $scope.modelName = null;
		                        $scope.modelCode = null;
	                        });	                    	
	                    	$('#' + attrs.comboId).data('kendoAutoComplete').value(null);
	                    }
	                }
	        }, 10);
	         	        
	    }
	  };
 });
