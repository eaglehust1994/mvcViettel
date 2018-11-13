(function() {
	'use strict';
	var controllerId = 'catWorkItemTypeController';
	
	angular.module('MetronicApp').controller(controllerId, catWorkItemTypeController);
	
	function catWorkItemTypeController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,catWorkItemTypeService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.catWorkItemTypeSearch={
				status:1
		};
		vm.catWorkItemType={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
//			if($rootScope.stringtile){
//				vm.String=$rootScope.stringtile;
//				}
		}
		 
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
                              '<label ng-repeat="column in vm.catWorkItemTypeGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							    $("#workItemCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + "catWorkItemTypeRsService/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.catWorkItemTypeSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.catWorkItemTypeSearch.page = options.page
								vm.catWorkItemTypeSearch.pageSize = options.pageSize

								return JSON.stringify(vm.catWorkItemTypeSearch)

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
					title: "Tên loại hạng mục công trình",
			        field: 'name',
			        width: '20%',
			        headerAttributes: {
						style: "text-align:left;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã loại hạng mục công trình",
					field: 'code',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Loại công trình",
					field: 'catConstructionType',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					
					field: 'catConstructionTypeId',
					hidden: true,
					
				},
				{
					title: "Mô tả",
			        field: 'description',
			        width: '15%',
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
			        template: dataItem =>
					'<div class="text-center">'
					+'<button style=" border: none; background-color: white;" id="updateId" ng-hide="dataItem.status==0"  ng-click="vm.edit(dataItem)" class="btn-mofify icon_table "uib-tooltip="Sửa"'+
					' translate>'+
					'<i class="fa fa-pencil"  ng-hide="dataItem.status==0 "   aria-hidden="true"></i>'+
					'</button>'+
				'<button style=" border: none; background-color: white;" id="removeId"'+
				'class="#=catWorkItemTypeId# icon_table" ng-click="vm.remove(dataItem)" ng-hide="dataItem.status==0"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0"  aria-hidden="true"></i>'+
				'</button>'
					+'</div>',
			        width: '15%',
			        field:"action"
				}
				,]
			});
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=catWorkItemTypeId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=catWorkItemTypeId#"'+
				'disble="" ng-click=vm.edit(#=catWorkItemTypeId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=catWorkItemTypeId#"'+
				'ng-click=vm.send(#=catWorkItemTypeId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=catWorkItemTypeId#"'+
				'ng-click=vm.remove(#=catWorkItemTypeId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=catWorkItemTypeId#"'+
				'ng-click=vm.cancelUpgradeLta(#=catWorkItemTypeId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
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
		
		vm.constructionTypeSearchOptions= {
        		dataSource: [
		             {id:1,name:'Công trình A&P'},
		             {id:2,name:'Công trình BTS'},
		             {id:3,name:'Công trình ngầm hóa'},
		             {id:4,name:'Công trình truyền dẫn'}
		             ],
	             dataTextField: "name",
	             dataValueField: "id",
	             optionLabel: "---Chọn---",        		          
	             valuePrimitive: true
        };
		
		vm.exportFile = function exportFile() {
			var data = vm.catWorkItemTypeGrid.dataSource.data();
				CommonService.exportFile(vm.catWorkItemTypeGrid,data,vm.listRemove,vm.listConvert);
		}
		
		
		function refreshGrid(d) {
			var grid = vm.catWorkItemTypeGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			
// vm.add = function(){
// vm.isCreateNew = true;
// vm.showDetail = true;
// vm.catWorkItemType={};
// vm.title="Thêm mới tham số ứng dụng"
// }
			vm.add=add;
		  function add(){
//			vm.listApp= { mess:"",
//    		         mess1:"",
//					 mess2:""
//    		         };
			
			  vm.catWorkItemType={};
			 var teamplateUrl="coms/catWorkItemType/catWorkItemTypePopup.html";
			 var title="Thêm mới loại trạm";
			 var windowId="CAT_WORK_ITEM_TYPE";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'570','250',"parType"); 
			 
		 }
		
		vm.edit=edit;
		function edit(dataItem){
//			vm.listApp= { mess:"",
//    		         mess1:"",
//					 mess2:""
//    		         };
			
			vm.catWorkItemType =dataItem;
			var teamplateUrl="coms/catWorkItemType/catWorkItemTypePopup.html";
			 var title="Cập nhật loại hạng mục";
			 var windowId="CAT_WORK_ITEM_TYPE";
			 $("#catWorkItemTypeGrid").data('kendoGrid').dataSource.read();
			 $("#catWorkItemTypeGrid").data('kendoGrid').refresh();
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.catWorkItemType,vm,windowId,false,'600','300',"parType"); 
		}
		
		vm.cancelChoose=function(id){
			
			if(id==='catConstructionType'){
				vm.catConstructionTypeName=null;
				vm.catWorkItemType.catConstructionTypeId=null;
			}
		}
		 //AutoCompleteconstructionTypeOptions
        vm.headerTemplate3='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto">Tên loại công trình</p>' +
	      	'</div>';
		// from db
		vm.constructionTypeOptions = {
	       		dataTextField:"catConstructionTypeName",
	        		
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.catWorkItemType.catConstructionTypeId = dataItem.catConstructionTypeId
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                            return Restangular.all("catConstructionDeployRsService/" + 'fillterAllActiveCatConstructionType').post(
	                                {keySearch: vm.catWorkItemType.catConstructionType,
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
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.catConstructionTypeName #</div> </div>',
	                change: function(e) {
	                    if (e.sender.value() === '') {
	                    	vm.catWorkItemType.catConstructionTypeId = null;
	                    }
	                },
	                ignoreCase: false
	            };
		
		
		vm.save= function(data,isCreateNew){
			data=vm.catWorkItemType;
//				if(checkErr()){
//					if(vm.listApp.mess !==""||vm.listApp.mess1!==""||vm.listApp.mess2!==""){
//						$('#parType').focus();
//						return vm.listApp;
//					}else{
						if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							catWorkItemTypeService.createCatWorkItemType(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.catWorkItemType = result;
                            doSearch();
                            //CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
//                            vm.add();
                            
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
								
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
                		catWorkItemTypeService.updateCatWorkItemType(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							$("#catWorkItemTypeGrid").data('kendoGrid').dataSource.read();
							$("#catWorkItemTypeGrid").data('kendoGrid').refresh();
                			toastr.success("Cập nhật thành công!");
                			
                			//CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
								$('#parType').focus();
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
//				}
//			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
		/* confirm('Bạn có muốn hủy bỏ thao tác?', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
		vm.remove=remove;
		function remove(dataItem){
// var grid=vm.catWorkItemTypeGrid;
// var item=grid.table.find("tr div." +id);
// var uid=$(item).parent().parent().attr("data-uid");
// var dataItem = grid.dataSource.getByUid(uid);
			confirm('Bạn thật sự muốn xóa bản ghi đã chọn?', function(){
				catWorkItemTypeService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa tham số thành công!");
							var sizePage = $("#catWorkItemTypeGrid").data("kendoGrid").dataSource.total();
							var pageSize = $("#catWorkItemTypeGrid").data("kendoGrid").dataSource.pageSize();
							if(sizePage % pageSize === 1){
								var currentPage = $("#catWorkItemTypeGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#catWorkItemTypeGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
							 $("#catWorkItemTypeGrid").data('kendoGrid').dataSource.read();
							 $("#catWorkItemTypeGrid").data('kendoGrid').refresh();

						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} )
	}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.catWorkItemTypeSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.catWorkItemTypeGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}

			console.log(grid.dataSource.data());
		}
		
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.catWorkItemTypeGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.catWorkItemTypeGrid.showColumn(column);
            } else {
            	vm.catWorkItemTypeGrid.hideColumn(column);
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
            	catWorkItemTypeService.exportpdf(obj);
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
            	var val=$('#parOder').val();
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
