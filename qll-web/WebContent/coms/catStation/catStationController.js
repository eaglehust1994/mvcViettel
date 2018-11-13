(function() {
	'use strict';
	var controllerId = 'catStationController';
	
	angular.module('MetronicApp').controller(controllerId, catStationController);
	
	function catStationController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,catStationService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.catStationSearch={
				status:"1"
		};
		vm.menuTree = [
		 		      { label : "Quản lý danh mục", id : "screen",clickAction :"backToList"},
		 		      { label : "Danh mục công trình", id : "screen",clickAction :"backToList"},
		 		      { label : "Trạm", id : "screen",clickAction :"backToList"}
		 		];
		 		vm.addNode = addNode;
		 		vm.removeNode = removeNode;
		 		function addNode(actionName) {
		 		      vm.menuTree.push({ label : actionName, id : "action",clickAction :"", classNode:"selected"});
		 		}
		 		function removeNode() {
		 		      vm.menuTree.pop();
		 		} 
		vm.catStation={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
//			if($rootScope.stringtile){
//				vm.String=$rootScope.stringtile;
//				}
		}
		vm.String = "Quản lý danh mục > Danh mục công trình > Trạm";
		function fillDataTable(data) {
	
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				 scrollable: true, 
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
//                            	  '<div class=" pull-left ">'+
//                                  '<button class="btn btn-qlk padding-search-right addQLK"'+
//                                  'ng-click="vm.add()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
//                					'</div>'	
//                  				+
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
							url: Constant.BASE_SERVICE_URL + "catStationRsService/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.catStationSearch.page = options.page
								vm.catStationSearch.pageSize = options.pageSize

								return JSON.stringify(vm.catStationSearch)

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
			        width: 40,
			        columnMenu: false,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã trạm",
			        field: 'code',
			        width: 50,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				}	
				,  {
					title: "Mã tỉnh thành",
					field: 'provinceCode',
					width: 50,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				 {
					title: "Trạm gốc",
					width: 50,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
					template :  "# if(parentId == null){ #" + "#= 'Không phải trạm gốc' #" + "# } " + "else if (parentId == 1) { # " + "#= 'Trạm gốc' #"+ "#} #",
				},
				 {
					title: "Loại trạm",
					width: 100,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
					template:'#=catStationTypeName#',
				},
				{
					title: "Địa chỉ",
					width: 200,
					field: 'address',
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Mã nhà trạm",
					width: 50,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
					template:'#=catStationHouseCode#',
				},
				{
					title: "Trạng thái trạm đồng bộ",
					width: 50,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Cr Num",
					width: 50,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Trạng thái phê duyệt nhà trạm",
					width: 50,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Ngày phê duyệt TKDĐ",
					width: 50,
					headerAttributes: {
						"class": "columns-title" 
					},
					attributes: {
						style: "text-align:center;"
					},
				},
				 {
					title: "Trạng thái",
					field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: 50,
			        headerAttributes: {
			        	"class": "columns-title" 
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
					+'<button style=" border: none; background-color: white;"ng-hide="dataItem.status==0 ||dataItem.IsSynonim == 1"  ng-click="vm.edit(dataItem)" id="updateId"  class=" icon_table "'+
					'   uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil" ng-hide="dataItem.status==0 ||dataItem.IsSynonim == 1"    aria-hidden="true"></i>'+
					'</button>'+
				'<button style=" border: none; background-color: white;" id="removeId"'+
				'class="#=catStationId# icon_table"  ng-click="vm.remove(dataItem)" uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0||dataItem.IsSynonim == 1"  aria-hidden="true"></i>'+
				'</button>'
					+'</div>',
			        width: 50,
				}
				,]
			});
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=catStationId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=catStationId#"'+
				'disble="" ng-click=vm.edit(#=catStationId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=catStationId#"'+
				'ng-click=vm.remove(#=catStationId#)>'+
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
			
			  vm.catStation={};
			 var teamplateUrl="coms/catStation/catStationPopup.html";
			 var title="Thêm mới trạm";
			 var windowId="CAT_STATION";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','250',"code"); 
			 
		 }
		
		vm.edit=edit;
		function edit(dataItem){
//			vm.listApp= { mess:"",
//    		         mess1:"",
//					 mess2:""
//    		         };
			
			vm.catStation =dataItem;
			vm.catStation.parentId==1?vm.catStation.isParent=true:vm.catStation.isParent=false;
			var teamplateUrl="coms/catStation/catStationPopup.html";
			 var title="Chỉnh sửa trạm";
			 var windowId="CAT_STATION";
			 $("#appParamGrid").data('kendoGrid').dataSource.read();
			 $("#appParamGrid").data('kendoGrid').refresh();
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.catStation,vm,windowId,false,'800','250',"code"); 
		}
		
		
		vm.save= function(data,isCreateNew){
			vm.catStation.isParent==true?vm.catStation.parentId=1:vm.catStation.parentId=null;
			data=vm.catStation;
			
						if(isCreateNew) {
							
							catStationService.createCatStation(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.catStation = result;
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
                		catStationService.updateCatStation(data).then(function(result){
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
				catStationService.remove(dataItem).then(
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
		
		
		 //AutoCompleteStationTypeOptions
        vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto">Tên loại trạm</p>' +
	      	'</div>';
        //AutoCompleteStationHouseOptions
        vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
        '<p class="col-md-6 text-header-auto">Mã nhà trạm</p>' +
        '</div>';
        
     // AutoCompleteStationTypeOptions
		vm.stationTypeOptions = {
	       		dataTextField:"name",
	        		
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.catStation.catStationTypeId = dataItem.catStationTypeId
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                            return Restangular.all("catStationRsService/" + 'getCatStationTypeForAutoComplete').post(
	                                {keySearch: vm.catStation.catStationTypeName,
	                                    pageSize: 10,
	                                    page: 1
	                                    }).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.name #</div> </div>',
	                change: function(e) {
	                    if (e.sender.value() === '') {
	                    	vm.catStation.catStationTypeId = null;
	                    }
	                },
	                ignoreCase: false
	            };
		// AutoCompleteStationHouseOptions
		vm.stationHouseOptions = {
				dataTextField:"code",
				
				select: function(e) {
					var dataItem = this.dataItem(e.item.index());
					vm.catStation.catStationHouseId = dataItem.catStationHouseId
				},
				pageSize: 10,
				dataSource: {
					serverFiltering: true,
					transport: {
						read: function(options) {
							return Restangular.all("catStationRsService/" + 'getCatStationHouseForAutoComplete').post(
									{keySearch: vm.catStation.catStationHouseCode,
										pageSize: 10,
										page: 1
									}).then(function(response){
										options.success(response);
									}).catch(function (err) {
										console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
									});
						}
					}
				},
				template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.code #</div> </div>',
				change: function(e) {
					if (e.sender.value() === '') {
						vm.catStation.catStationHouseId = null;
					}
				},
				ignoreCase: false
		};
        
        
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.catStationSearch={
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
            	catStationService.exportpdf(obj);
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
