(function() {
	'use strict';
	var controllerId = 'taxController';
	
	angular.module('MetronicApp').controller(controllerId, taxController);
	
	function taxController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,taxService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.taxSearch={
				status:1,
				type: 3
		
		};
		vm.tax={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=taxId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=taxId#"'+
				'disble="" ng-click=vm.edit(#=taxId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=taxId#"'+
				'ng-click=vm.send(#=taxId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=taxId#"'+
				'ng-click=vm.remove(#=taxId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=taxId#"'+
				'ng-click=vm.cancelUpgradeLta(#=taxId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		 setTimeout(function(){
			  $("#TaxID").focus();
			},15);
		 
		 var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				 scrollable: false, 
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				toolbar: [
                    {
                        name: "actions",
                        template: '<div class=" pull-left ">'+
                        '<button class="btn btn-qlk padding-search-right addQLK"'+
      					'ng-click="vm.add()" uib-tooltip="Thêm mới" translate><p class="action-button add" aria-hidden="true">Tạo mới</p></button>'+
      					'</div>'	
        				+
                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                    '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in vm.taxGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                    '</label>'+
                    '</div></div>'
                    }
                    ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							$("#taxCount").text(""+response.total);
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
							for(var x in response.data){
								response.data[x].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId
							}
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "taxRsServiceRest/tax/doSearchBytax",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              //  vm.taxSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.taxSearch.page = options.page
								vm.taxSearch.pageSize = options.pageSize

								return JSON.stringify(vm.taxSearch)

						}
					},					 
					pageSize: 10
				},
//				dataSource: data,
				noRecords: true,
				columnMenu: false,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
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
					title: "Mã thuế",
					field: 'code',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên thuế",
			        field: 'name',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thuế xuất <br> (%)",
			        field: 'value',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Loại thuế",
					template :  "# if(type == 2){ #" + "#= 'Đầu ra' #" + "# } " + "else if (type == 1) { # " + "#= 'Đầu vào' #"+ "#} #",
			        field: 'type',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Phân bổ",
					template :  "# if(apply == 0){ #" + "#= 'Không' #" + "# } " + "else if (apply == 1) { # " + "#= 'Có' #"+ "#} #",
			        field: 'apply',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Miễn thuế",
					template :  "# if(ignore == 0){ #" + "#= 'Không' #" + "# } " + "else if (ignore == 1) { # " + "#= 'Có' #"+ "#} #",
			        field: 'ignore',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Trạng thái",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
					template: dataItem =>
					'<div class="text-center""><button style=" border: none; background-color: white;"  ng-click="vm.edit(dataItem)" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  uib-tooltip="Sửa" '+
					' class=" icon_table ">'+
						'<i class="fa fa-pencil"  ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'+
					'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  class=" icon_table "'+
					'   uib-tooltip="Khóa" translate>'+
					'<i class="fa fa-pencil " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;"'+
				'class="#=appParamId# icon_table" ng-click="vm.remove(dataItem)" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"'+
				'class="#=appParamId# icon_table"  uib-tooltip="Khóa" translate'+
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
			var grid = vm.taxGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
		/* 	confirm('Xác nhận huỷ bỏ', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			/* 	}); */
		}
		
		vm.edit=edit;
		function edit(dataItem){
			
			 vm.list= { 
    		         mess2:""
    		         };
			vm.tax =dataItem;
			var teamplateUrl="wms/tax/taxPopup.html";
			 var title="Cập nhật loại thuế";
			 var windowId="TAX";
			 $("#taxGrid").data('kendoGrid').dataSource.read();
			 $("#taxGrid").data('kendoGrid').refresh();
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.tax,vm,windowId,false,'600','400',"code"); 
			
		}
		
		
		vm.save= function save(data,isCreateNew){
				
				if(!validateType()){
					if(document.getElementById('msg').innerHTML!==""||document.getElementById('msg1').innerHTML!==""||document.getElementById('msg2').innerHTML!==""){
						return false;
					}	
				}
	/*			if(vm.list.mess2!==""){
					$('#value').focus();
					return vm.list;
				}else{*/
					if(isCreateNew) {
                		data.status = '1';
                		taxService.createTax(data).then(function(result){
							if(result.error){
									$('#code').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.tax.taxId = result;         
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();							
                            doSearch();
                            vm.add();
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	$('#code').focus();
    		                	toastr.error(gettextCatalog.getString("Mã thuế đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục loại thuế!"));
    		                }
        		        });
                	} else {
                		taxService.updateTax(data).then(function(result){
								if(result.error){
									$('#code').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Cập nhật thành công!");
//                			doSearch();
                			 $("#taxGrid").data('kendoGrid').dataSource.read();
                			 $("#taxGrid").data('kendoGrid').refresh();
                			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
                				$('#code').focus();
    		                	toastr.error(gettextCatalog.getString("Mã thuế đã tồn tại"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
				
                	
		}
		
		
		 vm.add = function add(){
			 var teamplateUrl="wms/tax/taxPopup.html";
			 var title="Thêm mới loại thuế";
			 var windowId="TAX"
				 vm.Tax={};	 
			 vm.list= { 
    		         mess2:""
    		         };
				
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.Tax,vm,windowId,true,'600','400',"code"); 
			 
		 }
		
		vm.remove=remove;
		function remove(dataItem){
		confirm('Xác nhận xóa',function(){
				taxService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							var sizePage = $("#taxGrid").data("kendoGrid").dataSource.total();
							var pageSize = $("#taxGrid").data("kendoGrid").dataSource.pageSize();
							if(sizePage % pageSize === 1){
								var currentPage = $("#taxGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#taxGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
							 $("#taxGrid").data('kendoGrid').dataSource.read();
							 $("#taxGrid").data('kendoGrid').refresh();
							 CommonService.dismissPopup();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
		})
	}
		
		
		
		vm.canceldoSearch= function canceldoSearch(){
			 vm.showDetail = false;
			vm.taxSearch={
					status:"1",
					type:"3",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.taxGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.taxGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.taxGrid.showColumn(column);
            } else {
            	vm.taxGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		** Filter các cột của select 
		*/	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type== null||item.type !==1; 
            };
            
         
    			
            	vm.list={
            			mess2:"",
            	}
            	vm.checkErr=checkErr;
    	    	function checkErr() {
    	    		var tax = $('#value').val();
    	    		var isNaN = function(tax) {
    	    			if(Number.isNaN(Number(tax))){
    	    				return false;
    	    			}else{
    	    				 vm.list.mess2="";
    	    			}
    	    		}
    	    		
    	    		
					if(isNaN(tax)===false){
						vm.list.mess2 ="Thuế xuất phải đúng định dạng 0.01";
					}
					return vm.list;
    	    	}
				
            	
                       
    	    	//close popup wwhen click outside
    	    	/* $(document).on("click", ".k-overlay", function () {
    	    		 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    	    		}); */
			vm.validateType=validateType;
			function validateType(){
			var typeAdds = document.getElementsByName("typeAdd");
			var applyAdds = document.getElementsByName("applyAdd");
			var ignoreAdds = document.getElementsByName("ignoreAdd"); 
            if (typeAdds[0].checked === false && typeAdds[1].checked === false) {
                var msg = '<br /><br /><span >Loại thuế chưa được chọn</span><br /><br />';
                document.getElementById('msg').innerHTML = msg;
            }
            var ignoreAdds = document.getElementsByName("ignoreAdd");            
            if (ignoreAdds[0].checked === false && ignoreAdds[1].checked === false) {
                var msg = '<br /><br /><span >Miễn thuế chưa được chọn</span><br /><br />';
                document.getElementById('msg1').innerHTML = msg;
            }
            var applyAdds = document.getElementsByName("applyAdd");        
			if (applyAdds[0].checked === false && applyAdds[1].checked === false) {
                var msg = '<br /><br /><span >Phân bổ chưa được chọn</span><br /><br />';
                document.getElementById('msg2').innerHTML = msg;
			} 
           
		} 
			vm.reset_msg=reset_msg;
		function reset_msg(){
			document.getElementById('msg').innerHTML = '';
		}
		vm.reset_msg1=reset_msg1;
		function reset_msg1(){
			document.getElementById('msg1').innerHTML = '';
		}
		vm.reset_msg2=reset_msg2;
		function reset_msg2(){
			document.getElementById('msg2').innerHTML = '';
		}	
//		$(document).on("keydown", function (e) {
//				if (e.keyCode === 13) {
//					$("#savepopup").click();
//				}
//				});
 /*  $('#taxPopupID').keypress(function(){
		    	  document.onkeydown= keyDown;
                  function keyDown(e){
             		switch(e.keyCode) {
             			case 27:
             				$("#cancel").click();
             				break;
             			case 13:
             				if(e.keyCode===13&& !$('#cancel:focus').length){
             					$("#savepopup").click();
             				break;
             				}
             		}
             	} 
		      }) */		
				
	}
})();
