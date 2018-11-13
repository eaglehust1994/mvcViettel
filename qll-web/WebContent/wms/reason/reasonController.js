(function() {
	'use strict';
	var controllerId = 'reasonController';
	
	angular.module('MetronicApp').controller(controllerId, reasonController);
	
	function reasonController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,reasonService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.reasonSearch={
				status: 1,
				listApply:1,
		};
		vm.appParamSearch = {
				status:1,
				parType : 'REASON_APPLY'
		};
		vm.reason={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.dataAppParamType = [""];
		$(document).ready(function() {
			getApply();
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		});
		function getApply(){
			reasonService.getApply(vm.appParamSearch).then(function(result){
				 vm.dataAppParamType = result;	
				 vm.reasonSearch.listApply=[vm.dataAppParamType[0].code];
				 fillDataTable();
			 });
		}
		setTimeout(function(){
			  $("#reasonIds").focus();
			},15);
		var flag=1;
		var record=0;
		function fillDataTable() {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				scrollable: false,
				//sortable: false,	
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },				
				columnMenu: false,
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
                              '<label ng-repeat="column in vm.reasonGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
						 
						 $("#reasonCount").text(""+response.total);
								return response.total;
							},
							data: function (response) {
							for(var x in response.data){
								response.data[x].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId
							}
								return response.data;
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "reasonRsServiceRest/reason/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.reasonSearch.page = options.page
								vm.reasonSearch.pageSize = options.pageSize
								if(flag===1){
									vm.reasonSearch.listApply=[vm.dataAppParamType[0].code];
								}
								return JSON.stringify(vm.reasonSearch)

						}
					},					 
					pageSize: 10
				},
				noRecords: true,
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
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã lý do",
					field: 'code',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên lý do",
			        field: 'name',
			        width: '30%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tình huống sử dụng",
			        field: 'nameApply',
			        width: '20%',
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
					'<div class="text-center""><button style=" border: none; background-color: white;" '+
					' class=" icon_table "'+
					'   uib-tooltip="Sửa" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" ng-click="vm.edit(dataItem)" translate>'+
					'<i class="fa fa-pencil"  ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				'</button>'+
					'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" class=" icon_table "'+
					'   uib-tooltip="Khóa" translate>'+
					'<i class="fa fa-pencil " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;"'+
				'class="#=appParamId# icon_table"  ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  ng-click="vm.remove(dataItem)"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;"'+
				'class="#=appParamId# icon_table" style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  uib-tooltip="Khóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'
					+'</div>',
			        width: '15%',
			        field:"stt"
				}
				,]
			});
		}
		
		function refreshGrid(d) {
			var grid = vm.reasonGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
		/* confirm('Xác nhận huỷ bỏ', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
		
		vm.save= function save(data,isCreateNew){
//			if(checkErr()){
//				if(vm.listReason.mess!==""||vm.listReason.mess1!==""){
//					$('#code').focus();
//					return vm.list1;
//				}else{
			
					if(isCreateNew) {
	            	data.status = '1';
	            		reasonService.createReason(data).then(function(result){
	            			if(result.error){
								$('#code').focus();
								toastr.error(result.error);
								return;
							}
	            			toastr.success("Thêm mới thành công!");
	                        vm.reason.reasonId = result;
	                        doSearch();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
							vm.add();
	            		}, function(errResponse){
			                if (errResponse.status === 409) {
								$('#code').focus();
			                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại!"));
			                } else {
			                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục lý do!"));
			                }
	    		        });
	            	} else {
	            		reasonService.updateReason(data).then(function(result){
	            			if(result.error){
								$('#code').focus();
								toastr.error(result.error);
								return;
							}
							$("#reasonGrid").data('kendoGrid').dataSource.read();
							$("#reasonGrid").data('kendoGrid').refresh();
	            			toastr.success("Cập nhật thành công!");
	            			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	            		}, function(errResponse){
	            			if (errResponse.status === 409) {
								$('#code').focus();
			                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại"));
			                } else {
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
			                }
	    		        });
	            	}
//				}
//			}
			}
		
		vm.doSearch= doSearch;
		function doSearch(){
			if($("#listApply").val()===""||$("#listApply").val()==null){
				vm.reasonSearch.listApply=[];
				flag=0;
				
			}
			if(vm.reasonSearch.listApply.length>1){
				flag =2;
			}else{
				flag=0;
			}
			  vm.showDetail = false;
			var grid =vm.reasonGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		vm.edit=edit;
		function edit(dataItem){
//			vm.listReason= { mess:"",
//    		         mess1:""
//    		         };
			
				vm.reason =dataItem;
				var teamplateUrl="wms/reason/reasonPopup.html";
				var title="Cập nhật lý do";
				var windowId="REASON";
				/* $("#reasonGrid").data('kendoGrid').dataSource.read();
				$("#reasonGrid").data('kendoGrid').refresh();*/
				CommonService.populatePopupCreate(teamplateUrl,title,vm.reason,vm,windowId,false,'500','250',"code"); 
		}
		
		vm.add = function add(){
//		 vm.listReason= { mess:"",
//    		         mess1:""
//    		         };
			
			var teamplateUrl="wms/reason/reasonPopup.html";
			var title="Thêm mới lý do";
			var windowId="REASON";
			CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'500','250',"code"); 
			 
		 }
		vm.remove=remove;
		function remove(dataItem){
			confirm('Xác nhận xóa',function(){
				reasonService.remove(dataItem).then(
						function(d) {
							/*if(d.error){
								toastr.error(d.error);
								return;
							}*/
							var sizePage = $("#reasonGrid").data("kendoGrid").dataSource.total();
							var pageSize = $("#reasonGrid").data("kendoGrid").dataSource.pageSize();
							
							if(sizePage % pageSize === 1){
								var currentPage = $("#reasonGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#reasonGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
							 $("#reasonGrid").data('kendoGrid').dataSource.read();
							 $("#reasonGrid").data('kendoGrid').refresh();
							 toastr.success("Xóa thành công!");
							
							
							
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
				})
		}
		vm.canceldoSearch= function canceldoSearch(){
			 vm.showDetail = false;
			vm.reasonSearch={
					status:"1",
			};
			doSearch();
		}
		vm.cancelapply = function cancelapply()
		{
			vm.reasonSearch.listApply = [];
			flag =0;
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.reasonGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.reasonGrid.showColumn(column);
            } else {
            	vm.reasonGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		 * * Filter các cột của select
		 */	
	
		// vm.gridColumnShowHideFilter = function (item) { 
                // return item.type==null||item.type !==1; 
            // };
			
//			var specialChars = "<>@!#$%^&*()_+[]{}?:;|'\"\\,./~`-=";
//    			var check = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.listReason.mess="";
//    			    return false;
//    			}
//				
//				var check1 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.listReason.mess1="";
//    			    return false;
//    			}
//				
//			vm.checkErr = checkErr;
//    	    	function checkErr(code,name) {
//    	    		var code = $('#code').val();
//    	    		var name = $('#name').val();
//    	    		
//    	    		if(check(code) === true){
//    	    			vm.listReason.mess = "Mã kho chứa ký tự đặc biệt";
//    	    		}
//    	    		if(check1(name) === true){
//    	    			vm.listReason.mess1 = "Tên kho chứa ký tự đặc biệt";
//    	    		}
//					return vm.listReason;
//    	    	}
    	    	//close popup wwhen click outside
    	    	/* $(document).on("click", ".k-overlay", function () {
    	    		 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    	    		}); */
//				$(document).on("keydown", function (e) {
//					if (e.keyCode === 13) {
//						$("#findReason").click();
//					}
//				});
				
					
	}
})();