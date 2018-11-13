(function() {
	'use strict';
	var controllerId = 'purchaseOrderController';
	
	angular.module('MetronicApp').controller(controllerId, purchaseOrderController);
	
	function purchaseOrderController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,purchaseOrderService,
			CommonService,htmlCommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        
        // true nếu đang mở form thêm mới, đóng form thêm mới, isLive = false
        vm.isLive = false;
        
        //search model
		vm.purchaseOrderSearch={
				status:1,
		};
		vm.folder='';
		
		//insert model
		vm.purchaseOrder={
		};
//		function getFolder() {
//			Restangular.one(RestEndpoint.ORDER_GOOD_UPLOAD_FOLDER_URL+"/folder").get().then(function(data) {
//				vm.folder = data.folder;
//			  });
//		}
//		getFolder();
//		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		     		
		
		console.log(Constant.userInfo);
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=purchaseOrderId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=purchaseOrderId#"'+
				'disble="" ng-click=vm.edit(#=purchaseOrderId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=purchaseOrderId#"'+
				'ng-click=vm.send(#=purchaseOrderId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=purchaseOrderId#"'+
				'ng-click=vm.remove(#=purchaseOrderId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=purchaseOrderId#"'+
				'ng-click=vm.cancelUpgradeLta(#=purchaseOrderId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		 
		 
		 var record=0;
		 
		function fillDataTable(data) {
                vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				scrollable: false, 
				resizable: true,
				editable: true,
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
                                  '          '+
                                  '<button class="btn btn-qlk padding-search-right addQLK"'+
                                  'ng-click="vm.import()" uib-tooltip="Nhập file excel" translate>Import</button>'+
                					'</div>'	
                  				+
                                	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                              '<i data-toggle="dropdown" uib-tooltip="Cài đặt" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                              '<i class="action-button excelQLK" uib-tooltip="Xuất file excel" ng-click="vm.exportFile()" aria-hidden="true"></i>' +
                              '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                              '<label ng-repeat="column in vm.purchaseOrderGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					batch: true,
					 schema: {
						 model : {
							 fields : {
								 workDay : { type: "number", format: "{0:c}" },
								 price : { type: "number", format: "{0:c}" },
							 }
						 },
						 total: function (response) {
							    $("#appCount").text(""+response.total);
							    vm.count=response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {				
							for(var x in response.data){
								response.data[x].sysUserId=Constant.userInfo.vpsUserToken.sysUserId
							}
							return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + RestEndpoint.PURCHASE_ORDER_SERVICE_URL+"/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.purchaseOrderSearch.page = options.page
								vm.purchaseOrderSearch.pageSize = options.pageSize

								return JSON.stringify(vm.purchaseOrderSearch)
						}
					},					 
					pageSize: 10
				},

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
			       
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  
				{
					title: "Mã đơn hàng",
			        field: 'code',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên đơn hàng",
			        field: 'name',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đối tác",
			        field: 'catPartnerName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị ký",
			        field: 'sysGroupName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ngày ký",
			        field: 'signDate',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Giá trị",
			        field: 'price',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Nguồn kinh phí",
			        field: 'expense',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Trạng thái",
			        field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực" +
	        		"' #"+ "#} #",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thao tác",
			        template: dataItem =>
					'<div class="text-center">'
					+'<button style=" border: none; background-color: white;" id="updateId" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId" ng-click="vm.edit(dataItem)" class=" icon_table "'+
					'   uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil"  ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				'</button>'+
					'<button style=" border: none; background-color: white;" class=" icon_table "'+
					'  style="color:grey" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId" uib-tooltip="Khóa" translate>'+
					'<i class="fa fa-pencil " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;" id="removeId"'+
				'class="#=purchaseOrderId# icon_table" ng-click="vm.remove(dataItem)" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"'+
				'class="#=purchaseOrderId# icon_table"  uib-tooltip="Khóa" translate'+
					'>'+
					'<i class="fa fa-trash"  style="color:grey" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'
					+'</div>',
			        width: '15%',
			        field:"action"
				}
				,],
				 editable: true
			});
		}
		
		vm.listRemove=[{
			title: "Thao tác",
		}]
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Hiệu lực',
				0:'Hết Hiệu lực'
			}
		}
		]
		
		
		vm.exportFile = function exportFile() {
			vm.purchaseOrderSearch.page = null;
			vm.purchaseOrderSearch.pageSize = null;
			var data = purchaseOrderService.doSearch(vm.purchaseOrderSearch);
			console.log(data);
			purchaseOrderService.doSearch(vm.purchaseOrderSearch).then(function(d){
				CommonService.exportFile(vm.purchaseOrderGrid,d.data,vm.listRemove,vm.listConvert,"Danh sách đơn hàng");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.purchaseOrderGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

			vm.add=add;
		  function add(){
			vm.purchaseOrder={};
			vm.editForm = false;
			 var teamplateUrl="ims/purchaseOrder/purchaseOrderPopup.html";
			 var title="Thêm mới đơn hàng";
			 var windowId="APP_PARAM";
			 htmlCommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300',"code"); 
		 }
		
		vm.edit=edit;
		function edit(dataItem){			
//			vm.editForm = true;
			vm.purchaseOrder =dataItem;
			 var windowId="APP_PARAM";
			var teamplateUrl="ims/purchaseOrder/purchaseOrderPopup.html";
			var title="Cập nhật đơn hàng";	
			$("#purchaseOrderGrid").data('kendoGrid').dataSource.read();
			$("#purchaseOrderGrid").data('kendoGrid').refresh();
			htmlCommonService.populatePopupCreate(teamplateUrl,title,vm.purchaseOrder,vm,windowId,false,'1000','300',"code");
			vm.editForm = true;
		}
		
                vm.import=function(){
                    vm.purchaseOrder={};
			 var teamplateUrl="ims/purchaseOrder/importPurchaseOrder.html";
			 var title="Import danh mục công ty file excel";
			 var windowId="APP_PARAM";
			 htmlCommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','200'); 
                }
		
		vm.save= function(data,isCreateNew){
			vm.purchaseOrder.quotaType = 1;
			
			data=vm.purchaseOrder;
			if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							purchaseOrderService.createpurchaseOrder(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.purchaseOrder = result;
                            doSearch();
                            htmlCommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
//                            vm.add();
                            
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Đơn hàng đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo đơn hàng!"));
    		                }
        		        });
                	} else {
                		purchaseOrderService.updatepurchaseOrder(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							
							$("#purchaseOrderGrid").data('kendoGrid').dataSource.read();
							$("#purchaseOrderGrid").data('kendoGrid').refresh();
                			toastr.success("Cập nhật thành công!");
                			
                			 htmlCommonService.dismissPopup()
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
						
						 
// }
// }
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
				purchaseOrderService.remove(dataItem).then(
					function(d) {
						toastr.success("Xóa đơn hàng thành công!");
						var sizePage = $("#purchaseOrderGrid").data("kendoGrid").dataSource.total();
						var pageSize = $("#purchaseOrderGrid").data("kendoGrid").dataSource.pageSize();
						if(sizePage % pageSize === 1){
							var currentPage = $("#purchaseOrderGrid").data("kendoGrid").dataSource.page();
							if (currentPage > 1) {
								$("#purchaseOrderGrid").data("kendoGrid").dataSource.page(currentPage - 1);
							}
						}
						 $("#purchaseOrderGrid").data('kendoGrid').dataSource.read();
						 $("#purchaseOrderGrid").data('kendoGrid').refresh();

					}, function(errResponse) {
						toastr.error("Lỗi không xóa được!");
					});
			} )
		}
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.purchaseOrderSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			console.log(vm.purchaseOrderSearch);
			  vm.showDetail = false;
			var grid =vm.purchaseOrderGrid;	
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
        		vm.purchaseOrderGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.purchaseOrderGrid.showColumn(column);
            } else {
            	vm.purchaseOrderGrid.hideColumn(column);
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
            	purchaseOrderService.exportpdf(obj);
            }
		
            
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
            
        	vm.openGroup = function openGroup(){
    			var obj={};
    			purchaseOrderService.getAllSysGroup(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/findSysGroupPopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm đơn vị");
    				var data=result.plain();
    				vm.gridOptions = kendoConfig.getGridOptions({
    					autoBind: true,
    					resizable: true,
    					dataSource: result,
    					noRecords: true,
    					columnMenu:false,
    					messages: {
    						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
    					},
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
    					
    				});
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','popupGroupSearchController');
    			});
    		}
            
        	vm.openPartner = function openPartner(){
    			var obj={};
    			purchaseOrderService.getAllSysGroup(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/partnerSearchPopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm đối tác");
    				var data=result.plain();
    				vm.gridOptions = kendoConfig.getGridOptions({
    					autoBind: true,
    					resizable: true,
    					dataSource: result,
    					noRecords: true,
    					columnMenu:false,
    					messages: {
    						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
    					},
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
    				});
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','partnerSearchController');
    			});
    		}
        	
        	vm.openUser = function openUser(){
    			var obj={};
    			purchaseOrderService.getSysUser(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/sysUserSearchPopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm đại diện đơn vị ký");
    				var data=result.plain();
    				vm.gridOptions = kendoConfig.getGridOptions({
    					autoBind: true,
    					resizable: true,
    					dataSource: result,
    					noRecords: true,
    					columnMenu:false,
    					messages: {
    						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
    					},
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
    				});
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','sysUserSearchController');
    			});
    		}
        	
            vm.getExcelTemplate = function(){
    			var fileName="FileBieuMau_DonHang";
    			CommonService.downloadTemplate(fileName).then(function(d) {
    				data = d.plain();
    				
//    				console.log(purchaseOrderService.getTemplateFile(data.fileName).$$state);
    				window.location = Constant.BASE_SERVICE_URL + RestEndpoint.PURCHASE_ORDER_SERVICE_URL+"/downloadFile?" + data.fileName;
//    				console.log(JSON.stringify(result));
    			}).catch( function(){
    				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
    				return;
    			});
    		
            }
            
            vm.patternGroupOptions={
    				dataTextField: "name", placeholder:"Nhập mã đơn vị hoặc tên đơn vị",
    	            select: function(e) {
    	            	var dataItem = this.dataItem(e.item.index());
    	            	//nếu đang làm việc ở màn hình tìm kiếm
    	            	if(!vm.isLive){
	    	                vm.purchaseOrderSearch.sysGroupName = dataItem.name;
	    	                vm.purchaseOrderSearch.sysGroupId = dataItem.sysGroupId;
	    	                console.log(vm.purchaseOrderSearch);
    	            	}
    	            	//nếu đang làm việc ở form thêm mới
    	            	else{
    	            		vm.purchaseOrder.sysGroupName = dataItem.name;
	    	                vm.purchaseOrder.sysGroupId = dataItem.sysGroupId;
	    	                console.log(vm.purchaseOrder);
    	            	}
    	             
    	            },
    	            pageSize: 10,
    	            dataSource: {
    	                serverFiltering: true,
    	                transport: {
    	                    read: function(options) {
    	                        return Restangular.all("sysGroupServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("sysGroup")).val().trim()}).then(function(response){
    	                            options.success(response);
    	                            var check = response == [] || $("#"+proccessFormId("sysGroup")).val().trim() == "";
    	                            if(response == [] || $("#"+proccessFormId("sysGroup")).val().trim() == ""){
    	                            	 vm.purchaseOrderSearch.sysGroupId = null;
    	                            }
    	                        }).catch(function (err) {
    	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
    	                        });
    	                    }
    	                }
    	            },
    	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
    	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
    	            '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
    	            	'</div>',
    	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
    	            change: function(e) {	
    	            	if(processSearch(proccessFormId("sysGroup"),vm.selectedPrpject)){
    	            		var check = processSearch(proccessFormId("sysGroup"),vm.selectedPrpject);
    	            		console.log(check);
    						  vm.purchaseOrderSearch.keySearch = null;
    						  vm.purchaseOrder.keySearch = null;
    						  $("#"+proccessFormId("sysGroup")).val(null);
    						  vm.selectedPrpject=false;	
    	            	}
    	            },
    	            close: function(e) {
    	                // handle the event0
    	              }
    			};
            
            vm.onSaveGroupId=onSaveGroupId;
    		function onSaveGroupId(data){
    			if(!vm.isLive){
    			  vm.purchaseOrderSearch.sysGroupName = data.name;
                  vm.purchaseOrderSearch.sysGroupId = data.sysGroupId;
                  console.log(vm.purchaseOrderSearch);
    			$('#purchaseOrderId').focus();
    			}
    			else{
    				vm.purchaseOrder.sysGroupName = data.name;
    				vm.purchaseOrder.sysGroupId = data.sysGroupId;
    				$("#purchaseOrderId1").val(vm.purchaseOrder.sysGroupName);
    				console.log(vm.purchaseOrder);
    			}
    			htmlCommonService.dismissPopup();
    		}
    		
    	    vm.onSavePartnerId = function onSavePartnerId(data){
    	    	if(!vm.isLive){
		  			vm.purchaseOrderSearch.catPartnerName = data.name;
		            vm.purchaseOrderSearch.catPartnerId = data.catPartnerId;
		            console.log(vm.purchaseOrderSearch);
    	    	}
    	    	else{
    	    		vm.purchaseOrder.catPartnerName = data.name;
    	            vm.purchaseOrder.catPartnerId = data.catPartnerId;
    	    	}
	            htmlCommonService.dismissPopup();
	            $("#"+proccessFormId("signerGroup")).focus();
  		    };
    		
  		  vm.onSaveSysUser = function onSaveSysUser(data){
  			if(!vm.isLive){
	  			vm.purchaseOrderSearch.signerGroupName = data.fullName;
	            vm.purchaseOrderSearch.signerGroupId = data.sysUserId;
	            console.log(vm.purchaseOrderSearch);
  			}
  			else{
  				vm.purchaseOrder.signerGroupName = data.fullName;
	            vm.purchaseOrder.signerGroupId = data.sysUserId;
	            
  			}
	            htmlCommonService.dismissPopup();
	            $("#"+proccessFormId("catPartnerName")).focus();
		    };
  		
    	     vm.patternPartnerOptions={
     				dataTextField: "name", placeholder:"Nhập mã hoặc tên đối tác",
     	            select: function(e) {
     	                var data = this.dataItem(e.item.index());
     	               if(!vm.isLive){
	     	               vm.purchaseOrderSearch.catPartnerName = data.name;
	  		               vm.purchaseOrderSearch.catPartnerId = data.catPartnerId;
     	               } else{
     	            	   vm.purchaseOrder.catPartnerName = data.name;
     			           vm.purchaseOrder.catPartnerId = data.catPartnerId;
     	               }
     	                console.log(vm.purchaseOrderSearch);
     	            },
     	            pageSize: 10,
     	            dataSource: {
     	                serverFiltering: true,
     	                transport: {
     	                    read: function(options) {
     	                        return Restangular.all("catPartnerServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("catPartnerName")).val().trim()}).then(function(response){
     	                            options.success(response);
     	                            var check = response == [] || $("#"+proccessFormId("catPartnerName")).val().trim() == "";
     	                            if(response == [] || $("#"+proccessFormId("catPartnerName")).val().trim() == ""){
     	                            	 vm.purchaseOrderSearch.constructTypeId = null;
     	                            }
     	                        }).catch(function (err) {
     	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
     	                        });
     	                    }
     	                }
     	            },
     	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
     	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
     	            '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
     	            	'</div>',
     	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
     	            change: function(e) {	
     	            	if(processSearch(proccessFormId("catPartnerName"),vm.selectedPrpject)){
     						  vm.purchaseOrderSearch.keySearch = null;
     						
     						 $("#"+proccessFormId("catPartnerName")).val(null);
     						  vm.selectedPrpject=false;	
     	            	}
     	            },
     	            close: function(e) {
     	              }
     			};
    	     
    	     vm.patternSignerOptions={
      				dataTextField: "name", placeholder:"Nhập mã hoặc đại diện đơn vị ký",
      	            select: function(e) {
      	            	var data = this.dataItem(e.item.index());
      	                if(!vm.isLive){
      	                	vm.purchaseOrderSearch.signerGroupName = data.fullName;
      	                	vm.purchaseOrderSearch.signerGroupId = data.sysUserId;
      	                } else{
      	                	vm.purchaseOrder.signerGroupName = data.fullName;
      	                	vm.purchaseOrder.signerGroupId = data.sysUserId;
      	                }
      	            },
      	            pageSize: 10,
      	            dataSource: {
      	                serverFiltering: true,
      	                transport: {
      	                    read: function(options) {
      	                        return Restangular.all("sysUserServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("signerGroup")).val().trim()}).then(function(response){
      	                            options.success(response);
      	                            if(response == [] || $("#"+proccessFormId("signerGroup")).val().trim() == ""){
      	                            	 vm.purchaseOrderSearch.signerGroupId = null;
      	                            	 vm.purchaseOrder.signerGroupId=null;
      	                            }
      	                        }).catch(function (err) {
      	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
      	                        });
      	                    }
      	                }
      	            },
      	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
      	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
      	            '<p class="col-md-6 text-header-auto">Họ tên</p>' +
      	            	'</div>',
      	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
      	            change: function(e) {	
      	            	if(processSearch(proccessFormId("signerGroup"),vm.selectedPrpject)){
      	            		if(vm.isLive){
      	            			vm.purchaseOrder.signerGroupId= null;
      	            		}
      	            		else{
      	            			vm.purchaseOrderSearch.signerGroupId  = null;
      	            		}
      						
      	            		$("#"+proccessFormId("signerGroup")).val(null);
      						  vm.selectedPrpject=false;	
      	            	}
      	            },
      	            close: function(e) {
      	              }
      			};
             
    	    // khai báo form thêm mới đã bị đóng 
    		vm.closeForm = function(){
    			vm.isLive=false;
    			
//    			alert("close"+vm.isLive);
    		};
    		//khai báo form thêm mới đang làm việc
    		vm.openForm = function(){
    			vm.isLive=true;
//    			alert("open"+vm.isLive);
    		}
    	     
            vm.submit=submit;
	        function submit(data){
	        	console.log(data);
	        	if($("#filePackage")[0].files[0] === null){
		    		toastr.warning("Bạn chưa chọn file để import");
		    		return;
		    	}
	        	console.log("2");
				if($('.k-invalid-msg').is(':visible')){
					return;
				}
				console.log("3");
				if($("#filePackage")[0].files[0].name.split('.').pop() !=='xls' && $("#filePackage")[0].files[0].name.split('.').pop() !=='xlsx' ){
					toastr.warning("Sai định dạng file");
					return;
				}
	    		 var formData = new FormData();
					formData.append('multipartFile', $('#filePackage')[0].files[0]); 
			     return   $.ajax({
		            url: Constant.BASE_SERVICE_URL+RestEndpoint.PURCHASE_ORDER_SERVICE_URL+"/importPurchaseOrder?folder="+ vm.folder,
		            type: "POST",
		            data: formData,
		            enctype: 'multipart/form-data',
		            processData: false,
		            contentType: false,
		            cache: false,
		            success:function(data) {
		            	if(!data.error){
							  toastr.success("Import thành công!");
							  doSearch();
		            	}else{
		            		toastr.error(data.error);
		            	}
							  
		            }
		        });     
         }
	        
	      function proccessFormId(formId){
	    	  return vm.isLive ? (formId + "1") : formId;
	      }
	      
	      vm.cancelGroup = function(){
	    	  vm.purchaseOrderSearch.sysGroupName = null;
	    	  vm.purchaseOrderSearch.sysGroupId = null;
	      }
	      vm.cancelPartner = function(){
	    	  vm.purchaseOrderSearch.catPartnerName = null;
	    	  vm.purchaseOrderSearch.catPartnerId = null;
	      }
	      
	      vm.cancelConstruction = function(){
	    	  vm.purchaseOrderSearch.catConstructionTypeName = null;
	    	  vm.purchaseOrderSearch.catConstructionTypeId = null;
	      }
	      var order = 0;
	     
	      vm.clearSearchDatefunction = function(){
	    	  vm.purchaseOrderSearch.signDateFrom = null;
	    	  vm.purchaseOrderSearch.signDateTo = null;
	      }
		  //20180214_hoanm1_start
		  vm.cancelPartnerPopup = function(){
			  vm.purchaseOrder.catPartnerName = null;
			  vm.purchaseOrder.catPartnerId= null;
	      }
		   vm.cancelGroupPopup = function(){
			   vm.purchaseOrder.sysGroupName = null;
			   vm.purchaseOrder.catPartnerId= null;
	      }
		  vm.cancelUserGroupPopup = function(){
			  vm.purchaseOrder.signerGroupName = null;
			  vm.purchaseOrder.signerGroupId = null;
	      }
		  vm.cancelSearchPopup = function(){
	    	  vm.purchaseOrder.signDate = null;
	    	  vm.purchaseOrder.signDate = null;
	      }
		  vm.checkNum = checkNum;
	        function checkNum(){
	    		$("#price").keypress(function(event) {
	    			var $this = $(this);
			        if (!htmlCommonService.validateIntKeydown(event)) {
			               event.preventDefault();
			        }
			        var text = $(this).val();
			        if(text.length>=18){
			        	event.preventDefault();
			        }
			    			
			    });
	        }
	        
	        //validate ngay
	        vm.checkErr = checkErr;
	    	function checkErr(signDateFrom,signDateTo) {
	    		var createdDateFrom = $('#signDateFrom').val();
	    		var createdDateTo = $('#signDateTo').val();
				var d = new Date();
				var curDate = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() ;
						if(createdDateTo!==""){
							$('#signDateTo').text("");
							$('#signDateFrom').text("");
							if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") > kendo.parseDate(createdDateTo,"dd/MM/yyyy")){
								if(kendo.parseDate(createdDateTo,"dd/MM/yyyy")<= d){
									vm.listMess.errMessage='';
								}else if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") <= kendo.parseDate(createdDateTo,"dd/MM/yyyy")){
									vm.listMess.errMessage='';
								}
								vm.listMess.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
								 $("#signDateTo").focus(); 
								return vm.listMess;
							}
							else if(kendo.parseDate(createdDateTo,"dd/MM/yyyy") > d){
								$('#signDateFrom').text("");
								if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") <= kendo.parseDate(createdDateTo,"dd/MM/yyyy")){
									if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy")<=d){
										vm.listMess.errMessage1='';
									}else if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy")>d){
										vm.listMess.errMessage1='Ngày tạo phải nhỏ hơn bằng ngày hiện tại'
									}
								}
							    vm.listMess.errMessage = 'Ngày đến phải nhỏ hơn bằng ngày hiện tại';
								 $("#signDateTo").focus(); 
								 return vm.listMess;
						}}else if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") > d){
								$('#signDateFrom').text("");
								$('#signDateTo').text("");
							   vm.listMess.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày hiện tại';
							    $("#signDateFrom").focus(); 
							   return vm.listMess;
						}
						if(!vm.validator.validate()){
							if($('#signDateFrom').text()!==""){
								vm.listMess.errMessage1='';
								vm.listMess.errMessage=''
								$('#signDateFrom').focus();
									return false;
							}else if($('#signDateTo').text()!==""){
								vm.listMess.errMessage1='';
								vm.listMess.errMessage='';
								$('#signDateTo').focus();
									return false;
							} 
						}
							vm.listMess.errMessage = '';
							vm.listMess.errMessage1 = '';
							return vm.listMess;
		    }
		  //20180214_hoanm1_end
	}
	
})();
