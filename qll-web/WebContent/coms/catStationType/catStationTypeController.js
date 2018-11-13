(function() {
	'use strict';
	var controllerId = 'catStationTypeController';
	
	angular.module('MetronicApp').controller(controllerId, catStationTypeController);
	
	function catStationTypeController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,catStationTypeService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.catStationTypeSearch={
				status:"1"
		};
		vm.menuTree = [
		 		      { label : "Loại trạm", id : "screen",clickAction :"backToList"}
		 		];
		vm.catStationType={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
//			if($rootScope.stringtile){
//				vm.String=$rootScope.stringtile;
//				}
		}
		vm.String = "Quản lý danh mục > Danh mục công trình > Loại trạm";
		function fillDataTable(data) {
	
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				 scrollable: false, 
				resizable: true,
				editable: false,
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
                reorderable: true,
				toolbar: [
                          {
                              name: "actions",
                              template: 
                            	  '<div class=" pull-left ">'+
                                  '<button class="btn btn-qlk padding-search-right addQLK"'+
                                  'ng-click="vm.add()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
                					'</div>'	
                  				+
                                	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                              '<i data-toggle="dropdown" uib-tooltip="Cài đặt" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                              '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                              '<label ng-repeat="column in vm.appParamGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							    $("#appCount").text(""+response.total);
							    vm.count=response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {				
							return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "catStationTypeRsService/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.catStationTypeSearch.page = options.page
								vm.catStationTypeSearch.pageSize = options.pageSize

								return JSON.stringify(vm.catStationTypeSearch)

						}
					},					 
					pageSize: 10
				},
// dataSource: data,
				noRecords: true,
				columnMenu: false,
				messages: {
					noRecords : gettextCatalog.getString("<div style='margin:5px'>Không có kết quả hiển thị</div>")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "<div style='margin:5px'>Không có kết quả hiển thị</div>"
		            }
				},
				columns: [{
					title: "TT",
					field:"stt",
			        template: function () {
					  return ++record;
					 },
			        width: '5%',
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Loại trạm",
			        field: 'name',
			        width: '25%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thứ tự",
					field: 'catOrder',
//					template:'#=catOrder#',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: '40%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Trạng thái",
					field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
					 headerAttributes: {
						 "class": "columns-title" 
						},
			        template: dataItem =>
					'<div class="text-center">'
					+'<button style=" border: none; background-color: white;"ng-hide="dataItem.status==0"  ng-click="vm.edit(dataItem)" id="updateId"  class=" icon_table "'+
					'   uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil" ng-hide="dataItem.status==0"    aria-hidden="true"></i>'+
					'</button>'+
				'<button style=" border: none; background-color: white;" id="removeId"'+
				'class="#=catStationTypeId# icon_table"  ng-click="vm.remove(dataItem)" uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0"  aria-hidden="true"></i>'+
				'</button>'
					+'</div>',
			        width: '15%',
				}
				,]
			});
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=catStationTypeId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=catStationTypeId#"'+
				'disble="" ng-click=vm.edit(#=catStationTypeId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=catStationTypeId#"'+
				'ng-click=vm.remove(#=catStationTypeId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		 setTimeout(function(){
			  $("#keySearch").focus();
			},15);
		/* setTimeout(function(){
			  $("#appIds1").focus();
			},15);*/
		 var record=0;
		vm.listRemove=[{
			title: "Thao tác",
		}]
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Hiệu lực',
				0:'Hết Hiệu lực'
			}
		}]
		
		
		vm.exportFile = function exportFile() {
			var data = vm.appParamGrid.dataSource.data();
				CommonService.exportFile(vm.appParamGrid,data,vm.listRemove,vm.listConvert);
		}
		
		
		function refreshGrid(d) {
			var grid = vm.appParamGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			vm.add=add;
		  function add(){
			
			  vm.catStationType={};
			 var teamplateUrl="coms/catStationType/catStationTypePopup.html";
			 var title="Thêm mới loại trạm";
			 var windowId="CAT_STATION_TYPE";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'600','200',"parType"); 
			 
		 }
		
		vm.edit=edit;
		function edit(dataItem){
//			vm.listApp= { mess:"",
//    		         mess1:"",
//					 mess2:""
//    		         };
			
			vm.catStationType =dataItem;
			var teamplateUrl="coms/catStationType/catStationTypePopup.html";
			 var title="Chỉnh sửa loại trạm";
			 var windowId="CAT_STATION_TYPE";
			 $("#appParamGrid").data('kendoGrid').dataSource.read();
			 $("#appParamGrid").data('kendoGrid').refresh();
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.catStationType,vm,windowId,false,'600','200',"parType"); 
		}
		
		
		vm.save= function(data,isCreateNew){
			data=vm.catStationType;
						if(isCreateNew) {
							
							catStationTypeService.createCatStationType(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.catStationType = result;
                            doSearch();
                            //CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                            vm.add();
                            
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
								
    		                	toastr.error(gettextCatalog.getString(""));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Có lỗi xảy ra."));
    		                }
        		        });
                	} else {
                		catStationTypeService.updateCatStationType(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							$("#appParamGrid").data('kendoGrid').dataSource.read();
							$("#appParamGrid").data('kendoGrid').refresh();
                			toastr.success("Cập nhật thành công!");
                			
                			//CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
								$('#parType').focus();
    		                	
    		                } else {
    		                	
    		                }
        		        });
                	}
		}
		
		vm.cancel= cancel ;
		function cancel(){
		/* confirm('Bạn có muốn hủy bỏ thao tác?', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
		vm.remove=remove;
		function remove(dataItem){
			confirm('Bạn thật sự muốn xóa bản ghi đã chọn?', function(){
				catStationTypeService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa tham số thành công!");
							var sizePage = $("#appParamGrid").data("kendoGrid").dataSource.total();
							var pageSize = $("#appParamGrid").data("kendoGrid").dataSource.pageSize();
							if(sizePage % pageSize === 1){
								var currentPage = $("#appParamGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#appParamGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
							 $("#appParamGrid").data('kendoGrid').dataSource.read();
							 $("#appParamGrid").data('kendoGrid').refresh();

						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} )
	}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.catStationTypeSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.appParamGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}

		}
		
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.appParamGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.appParamGrid.showColumn(column);
            } else {
            	vm.appParamGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !==1; 
            };
		
            
            vm.exportpdf= function(){
            	var obj={};
            	catStationTypeService.exportpdf(obj);
            }
			
			
			/*var specialChars = "<>@!#$%^&*()+[]{}?:;|'\"\\,./~`-=";
    			var check = function(string){
    			    for(var i = 0; i < specialChars.length;i++){
    			        if(string.indexOf(specialChars[i]) > -1){
    			            return true;
    			        }
    			    }
    			    vm.listApp.mess="";
    			    return false;
    			}
    			var check1 = function(string){
    			    for(var i = 0; i < specialChars.length;i++){
    			        if(string.indexOf(specialChars[i]) > -1){
    			            return true;
    			        }
    			    }
    			    vm.listApp.mess1="";
    			    return false;
    			}
				var check2 = function(string){
    			    for(var i = 0; i < specialChars.length;i++){
    			        if(string.indexOf(specialChars[i]) > -1){
    			            return true;
    			        }
    			    }
    			    vm.listApp.mess2="";
    			    return false;
    			}
			
			vm.checkErr = checkErr;
    	    	function checkErr() {
    	    		var parType = $('#parType').val();
    	    		var code = $('#code').val();
    	    		var name=$('#name').val();
    	    		if(check(parType) === true){
    	    			vm.listApp.mess = "Loại tham số chứa ký tự đặc biệt";
    	    		}
    	    		if(check1(code) === true){
    	    			vm.listApp.mess1 = "Mã tham số chứa ký tự đặc biệt";
    	    		}
					if(check2(name) === true){
    	    			vm.listApp.mess2 = "Tên Tham số ký tự đặc biệt";
    	    		}
					return vm.listApp;
    	    	}*/
			vm.errNumber="";
            vm.checkNumber=checkNumber;
            function checkNumber(){
            	var val=$('#order').val();
				if(val===0){
					if(val===0){
						if(val===""){
							vm.errNumber="";
						}else{
							vm.errNumber="Phải nhập kiểu số nguyên từ 1-99";
						return false;
						}
						
					}
				}else{
					var isNaN = function(val) {
    	    			if(Number.isNaN(Number(val))){
							return false;
    	    			}
						return true;
					}
					if(isNaN(val)===false){
						vm.errNumber="Phải nhập kiểu số nguyên từ 1-99";
					}else{
						vm.errNumber="";
					}
					
				}
				
				
            }
            
		     
            //close popup wwhen click outside
            /* $(document).on("click", ".k-overlay", function () {
            	$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
            }); */
	
}
	})();
