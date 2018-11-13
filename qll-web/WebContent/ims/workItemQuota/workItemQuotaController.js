(function() {
	'use strict';
	var controllerId = 'workItemQuotaController';
	
	angular.module('MetronicApp').controller(controllerId, workItemQuotaController);
	
	function workItemQuotaController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,workItemQuotaService,
			CommonService,htmlCommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        
        // true nếu đang mở form thêm mới, đóng form thêm mới, isLive = false
        vm.isLive = false;
        
        //search model
		vm.workItemQuotaSearch={
				status:1,
				quotaType:1
		};
		vm.folder='';
		
		//insert model
		vm.workItemQuota={
				quotaType:1
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
			 '<div class="text-center #=workItemQuotaId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=workItemQuotaId#"'+
				'disble="" ng-click=vm.edit(#=workItemQuotaId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=workItemQuotaId#"'+
				'ng-click=vm.send(#=workItemQuotaId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=workItemQuotaId#"'+
				'ng-click=vm.remove(#=workItemQuotaId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=workItemQuotaId#"'+
				'ng-click=vm.cancelUpgradeLta(#=workItemQuotaId#)>'+
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
                              '<label ng-repeat="column in vm.workItemQuotaGrid.columns| filter: vm.gridColumnShowHideFilter">'+
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
							url: Constant.BASE_SERVICE_URL + RestEndpoint.WORK_ITEM_QUOTA_SERVICE_URL+"/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.workItemQuotaSearch.page = options.page
								vm.workItemQuotaSearch.pageSize = options.pageSize

								return JSON.stringify(vm.workItemQuotaSearch)
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
					title: "Đơn vị",
			        field: 'sysGroupName',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Loại công trình",
			        field: 'catConstructionTypeName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Hạng mục",
			        field: 'catWorkItemTypeName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn Giá",
			        field: 'price',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Ngày công",
			        field: 'workDay',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
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
				'class="#=workItemQuotaId# icon_table" ng-click="vm.remove(dataItem)" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"'+
				'class="#=workItemQuotaId# icon_table"  uib-tooltip="Khóa" translate'+
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
			vm.workItemQuotaSearch.page = null;
			vm.workItemQuotaSearch.pageSize = null;
			var data = workItemQuotaService.doSearch(vm.workItemQuotaSearch);
			console.log(data);
			workItemQuotaService.doSearch(vm.workItemQuotaSearch).then(function(d){
				CommonService.exportFile(vm.workItemQuotaGrid,d.data,vm.listRemove,vm.listConvert,"Danh sách định mức hạng mục công ty");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.workItemQuotaGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

			vm.add=add;
		  function add(){
			vm.workItemQuota={};
			 var teamplateUrl="ims/workItemQuota/workItemQuotaPopup.html";
			 var title="Thêm mới định mức hạng mục";
			 var windowId="APP_PARAM";
			 htmlCommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','500',"code"); 
			 getTask(true);
		 }
		
		vm.edit=edit;
		function edit(dataItem){			
			vm.workItemQuota =dataItem;
			var teamplateUrl="ims/workItemQuota/workItemQuotaPopup.html";
			var title="Cập nhật định mức hạng mục";
			var windowId="APP_PARAM";
			$("#workItemQuotaGrid").data('kendoGrid').dataSource.read();
			$("#workItemQuotaGrid").data('kendoGrid').refresh();
			htmlCommonService.populatePopupCreate(teamplateUrl,title,vm.workItemQuota,vm,windowId,false,'1000','500',"code");
			var isNewTask = false
			getTask(isNewTask);
		}
		
                vm.import=function(){
                    vm.workItemQuota={};
			 var teamplateUrl="ims/workItemQuota/importWorkItemQuota.html";
			 var title="Import định mức hạng mục";
			 var windowId="APP_PARAM";
			 htmlCommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','200'); 
                }
		
		vm.save= function(data,isCreateNew){
			vm.workItemQuota.quotaType = 1;
			var task =$('#taskGrid').data("kendoGrid").dataSource.data();
			 console.log(task);
			data=vm.workItemQuota;
			if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							workItemQuotaService.createworkItemQuota(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.workItemQuota = result;
                            task.forEach(function(item, index){
                            	item.workItemQuotaId = vm.workItemQuota.workItemQuotaId;
                            })
                            workItemQuotaService.saveListTaskQuota(task);
                            doSearch();
                            htmlCommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
//                            vm.add();
                            
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Định mức đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo định mức công ty!"));
    		                }
        		        });
                	} else {
                		workItemQuotaService.updateworkItemQuota(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							 task.forEach(function(item, index){
                            	item.workItemQuotaId = vm.workItemQuota.workItemQuotaId;
                            })
							workItemQuotaService.updateListTaskQuota(task).then(function(result){
     							if(result.error){
    								$('#parType').focus();
    								toastr.error(result.error);
    								return;
    							}
                			 }) ;
							$("#workItemQuotaGrid").data('kendoGrid').dataSource.read();
							$("#workItemQuotaGrid").data('kendoGrid').refresh();
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
				workItemQuotaService.remove(dataItem).then(
					function(d) {
						toastr.success("Xóa định mức thành công!");
						var sizePage = $("#workItemQuotaGrid").data("kendoGrid").dataSource.total();
						var pageSize = $("#workItemQuotaGrid").data("kendoGrid").dataSource.pageSize();
						if(sizePage % pageSize === 1){
							var currentPage = $("#workItemQuotaGrid").data("kendoGrid").dataSource.page();
							if (currentPage > 1) {
								$("#workItemQuotaGrid").data("kendoGrid").dataSource.page(currentPage - 1);
							}
						}
						 $("#workItemQuotaGrid").data('kendoGrid').dataSource.read();
						 $("#workItemQuotaGrid").data('kendoGrid').refresh();

					}, function(errResponse) {
						toastr.error("Lỗi không xóa được!");
					});
			} )
		}
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.workItemQuotaSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			console.log(vm.workItemQuotaSearch);
			  vm.showDetail = false;
			var grid =vm.workItemQuotaGrid;	
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
        		vm.workItemQuotaGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.workItemQuotaGrid.showColumn(column);
            } else {
            	vm.workItemQuotaGrid.hideColumn(column);
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
            	workItemQuotaService.exportpdf(obj);
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
    			workItemQuotaService.getAllSysGroup(obj).then(function(result){
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
            
        	vm.openConstruction = function openConstruction(){
    			var obj={};
//    			workItemQuotaService.getAllSysGroup(obj).then(function(result){
//    				var templateUrl = 'searchPopup/template/constructTypePopUp.html';
//    				var title = gettextCatalog.getString("Tìm kiếm loại công trình");
//    				var data=result.plain();
//    				vm.gridOptions = kendoConfig.getGridOptions({
//    					autoBind: true,
//    					resizable: true,
//    					dataSource: result,
//    					noRecords: true,
//    					columnMenu:false,
//    					messages: {
//    						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
//    					},
//    					pageable: {
//    						refresh: false,
//    						pageSize:10,
//    						pageSizes: [10, 15, 20, 25],
//    						messages: {
//    			                display: "{0}-{1} của {2} kết quả",
//    			                itemsPerPage: "kết quả/trang",
//    			                empty: "Không có kết quả hiển thị"
//    			            }
//    					},
//    				});
    				var templateUrl = 'searchPopup/template/constructTypePopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm loại công trình");
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','constructTypeSearchController');
//    			});
    		}
        	
        	vm.openWorkItemType = function openWorkItemType(){
    			var obj={};
    			workItemQuotaService.getAllCatWorkItemType(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/workItemTypePopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm hạng mục công trình");
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
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','workItemTypeSearchController');
    			});
    		}
        	
            vm.getExcelTemplate = function(){
    			var fileName="FileBieuMau_DMHangMucCongTy";
    			CommonService.downloadTemplate(fileName).then(function(d) {
    				data = d.plain();
    				
//    				console.log(workItemQuotaService.getTemplateFile(data.fileName).$$state);
    				window.location = Constant.BASE_SERVICE_URL + RestEndpoint.WORK_ITEM_QUOTA_SERVICE_URL+"/downloadFile?" + data.fileName;
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
	    	                vm.workItemQuotaSearch.sysGroupName = dataItem.name;
	    	                vm.workItemQuotaSearch.sysGroupId = dataItem.sysGroupId;
	    	                console.log(vm.workItemQuotaSearch);
    	            	}
    	            	//nếu đang làm việc ở form thêm mới
    	            	else{
    	            		vm.workItemQuota.sysGroupName = dataItem.name;
	    	                vm.workItemQuota.sysGroupId = dataItem.sysGroupId;
	    	                console.log(vm.workItemQuota);
    	            	}
    	             
    	            },
    	            pageSize: 10,
    	            dataSource: {
    	                serverFiltering: true,
    	                transport: {
    	                    read: function(options) {
    	                        return Restangular.all("sysGroupServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("workItemQuotaId")).val().trim()}).then(function(response){
    	                            options.success(response);
    	                            var check = response == [] || $("#"+proccessFormId("workItemQuotaId")).val().trim() == "";
    	                            if(response == [] || $("#"+proccessFormId("workItemQuotaId")).val().trim() == ""){
    	                            	 vm.workItemQuotaSearch.sysGroupId = null;
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
    	            	if(processSearch(proccessFormId("workItemQuotaId"),vm.selectedPrpject)){
    	            		var check = processSearch(proccessFormId("workItemQuotaId"),vm.selectedPrpject);
    	            		console.log(check);
    						  vm.workItemQuotaSearch.keySearch = null;
    						  vm.workItemQuota.keySearch = null;
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
    			  vm.workItemQuotaSearch.sysGroupName = data.name;
                  vm.workItemQuotaSearch.sysGroupId = data.sysGroupId;
                  console.log(vm.workItemQuotaSearch);
    			$('#workItemQuotaId').focus();
    			}
    			else{
    				vm.workItemQuota.sysGroupName = data.name;
    				vm.workItemQuota.sysGroupId = data.sysGroupId;
    				$("#workItemQuotaId1").val(vm.workItemQuota.sysGroupName);
    				console.log(vm.workItemQuota);
    			}
    			htmlCommonService.dismissPopup();
    		}
    		
    	    vm.onSaveConstructTypeId = function onSaveConstructTypeId(data){
    	    	if(!vm.isLive){
		  			vm.workItemQuotaSearch.catConstructionTypeName = data.name;
		            vm.workItemQuotaSearch.catConstructionTypeId = data.catConstructionTypeId;
		            vm.workItemQuotaSearch.catWorkItemTypeName = null;
		            vm.workItemQuotaSearch.catWorkItemTypeId = null;
    	    	}
    	    	else{
    	    		vm.workItemQuota.catConstructionTypeName = data.name;
    	            vm.workItemQuota.catConstructionTypeId = data.catConstructionTypeId;
    	            vm.workItemQuota.catWorkItemTypeName = null;
    	            vm.workItemQuota.catWorkItemTypeId = null;
    	    	}
	            htmlCommonService.dismissPopup();
	  			$('#workItemQuotaId').focus();
  		    };
    		
  		  vm.onSaveWorkItemTypeId = function onSaveWorkItemTypeId(data){
  			if(!vm.isLive){
	  			vm.workItemQuotaSearch.catWorkItemTypeName = data.name;
	            vm.workItemQuotaSearch.catWorkItemTypeId = data.catWorkItemTypeId;
  			}
  			else{
  				vm.workItemQuota.catWorkItemTypeName = data.name;
	            vm.workItemQuota.catWorkItemTypeId = data.catWorkItemTypeId;
	            var isNewTask = true;
	            getTask(isNewTask);
  			}
	            htmlCommonService.dismissPopup();
	  			$('#workItemQuotaId').focus();
		    };
  		
    	     vm.patternConstructOptions={
     				dataTextField: "name", placeholder:"Nhập mã hoặc tên loại công trình",
     	            select: function(e) {
     	                var dataItem = this.dataItem(e.item.index());
     	               if(!vm.isLive){
	     	               vm.workItemQuotaSearch.catConstructionTypeName = dataItem.name;
	     	               vm.workItemQuotaSearch.catConstructionTypeId = dataItem.catConstructionTypeId;
	     	              vm.workItemQuotaSearch.catWorkItemTypeName = null;
	     		            vm.workItemQuotaSearch.catWorkItemTypeId = null;
     	               } else{
		     	           vm.workItemQuota.catConstructionTypeName = dataItem.name;
		       	           vm.workItemQuota.catConstructionTypeId = dataItem.catConstructionTypeId;
		       	        vm.workItemQuota.catWorkItemTypeName = null;
	    	            vm.workItemQuota.catWorkItemTypeId = null;
     	               }
     	                console.log(vm.workItemQuotaSearch);
     	            },
     	            pageSize: 10,
     	            dataSource: {
     	                serverFiltering: true,
     	                transport: {
     	                    read: function(options) {
     	                        return Restangular.all("catConstructionTypeServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("constructionType")).val().trim()}).then(function(response){
     	                            options.success(response);
     	                            var check = response == [] || $("#"+proccessFormId("constructionType")).val().trim() == "";
     	                            if(response == [] || $("#"+proccessFormId("constructionType")).val().trim() == ""){
     	                            	 vm.workItemQuotaSearch.constructTypeId = null;
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
     	            	if(processSearch(proccessFormId("constructionType"),vm.selectedPrpject)){
     	            		var check = processSearch(proccessFormId("constructionType"),vm.selectedPrpject);
     	            		console.log(check);
     						  vm.workItemQuotaSearch.keySearch = null;
     						
//     						 $('#workItemQuotaId').val(null);
     						  vm.selectedPrpject=false;	
     	            	}
     	            },
     	            close: function(e) {
     	              }
     			};
    	     
    	     vm.patternWorkItemTypeOptions={
      				dataTextField: "name", placeholder:"Nhập mã hoặc tên hạng mục",
      	            select: function(e) {
      	            	var dataItem = this.dataItem(e.item.index());
      	                if(!vm.isLive){
	      	                vm.workItemQuotaSearch.catWorkItemTypeName = dataItem.name;
	      	                vm.workItemQuotaSearch.catWorkItemTypeId = dataItem.catWorkItemTypeId;
      	                } else{
      	                	vm.workItemQuota.catWorkItemTypeName = dataItem.name;
 	      	                vm.workItemQuota.catWorkItemTypeId = dataItem.catWorkItemTypeId;
 	      	                getTask(true);
      	                }
      	            },
      	            pageSize: 10,
      	            dataSource: {
      	                serverFiltering: true,
      	                transport: {
      	                    read: function(options) {
      	                    	var catConstructionTypeId;
      	                    	if(!vm.isLive){
      	                    		if(!!vm.workItemQuotaSearch.catConstructionTypeId )
      	                    			catConstructionTypeId = vm.workItemQuotaSearch.catConstructionTypeId;         
      	      	                } else{
	      	      	                if(!!vm.workItemQuota.catConstructionTypeId )
	      	      	                	catConstructionTypeId = vm.workItemQuota.catConstructionTypeId
      	      	                }
      	                    	
      	                    	var searchObj = {
      	                    			pageSize:10, 
      	                    			page:1, 
      	                    			keySearch:$("#"+proccessFormId("workItemType")).val().trim(),
      	                    			catConstructionTypeId: catConstructionTypeId
      	                    			}
      	                        return Restangular.all("catWorkItemTypeServiceRest/getForAutoComplete").post(searchObj).then(function(response){
      	                            options.success(response);
      	                            if(response == [] || $("#"+proccessFormId("workItemType")).val().trim() == ""){
      	                            	 vm.workItemQuotaSearch.catWorkItemTypeId = null;
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
      	            	if(processSearch(proccessFormId("workItemType"),vm.selectedPrpject)){
      	            		if(vm.isLive){
      	            			 vm.workItemQuota.catWorkItemTypeId = null;
      	            		}
      	            		else{
      	            			vm.workItemQuotaSearch.catWorkItemTypeId = null;
      	            		}
      						  vm.selectedPrpject=false;	
      	            	}
      	            },
      	            close: function(e) {
      	              }
      			};
             
    	    // khai báo form thêm mới đã bị đóng 
    		vm.closeForm = function(){
    			vm.isLive=false;
    			vm.taskCount="";
    		};
    		//khai báo form thêm mới đang làm việc
    		vm.openForm = function(){
    			vm.isLive=true;
    		}
    	     
            vm.submit=submit;
	        function submit(data){
	        	console.log(data);
	        	if(!$("#filePackage")[0].files[0]){
		    		toastr.warning("Bạn chưa chọn file để import");
		    		return;
		    	}
				if($('.k-invalid-msg').is(':visible')){
					return;
				}
				if($("#filePackage")[0].files[0].name.split('.').pop() !=='xls' && $("#filePackage")[0].files[0].name.split('.').pop() !=='xlsx' ){
					toastr.warning("Sai định dạng file");
					return;
				}
	    		 var formData = new FormData();
					formData.append('multipartFile', $('#filePackage')[0].files[0]); 
			     return   $.ajax({
		            url: Constant.BASE_SERVICE_URL+RestEndpoint.WORK_ITEM_QUOTA_SERVICE_URL+"/importWorkItemQuota?folder="+ vm.folder+"&quotaType=1",
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
				$("#workday").keypress(function(event) {
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
	        
	      function proccessFormId(formId){
	    	  return vm.isLive ? (formId + "1") : formId;
	      }
	      
	      vm.cancelGroup = function(){
	    	  vm.workItemQuotaSearch.sysGroupName = null;
	    	  vm.workItemQuotaSearch.sysGroupId = null;
	      }
	      vm.cancelWorkType = function(){
	    	  vm.workItemQuotaSearch.catWorkItemTypeName = null;
	    	  vm.workItemQuotaSearch.catWorkItemTypeId = null;
	      }
	      
	      vm.cancelConstruction = function(){
	    	  vm.workItemQuotaSearch.catConstructionTypeName = null;
	    	  vm.workItemQuotaSearch.catConstructionTypeId = null;
	      }
	      var order = 0;
	       function getTask(isNewTask){
	    	  var obj = {catWorkItemTypeId:vm.workItemQuota.catWorkItemTypeId};
	    	  doGetTask(isNewTask).then(function(result){
	    		  var data = result.data;
	    		  vm.taskCount = result.size;
	    		  vm.gridTaskOptions = {};
	    		  vm.gridTaskOptions =  new kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					/*toolbar: [
	                          {
	                              name: "actions",
	                              template: 
	                            	  
	                                	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
	                              '<i data-toggle="dropdown" uib-tooltip="Cài đặt" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                              '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
	                              '<label ng-repeat="column in vm.taskGrid.columns| filter: vm.gridColumnShowHideFilter">'+
	                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
	                              '</label>'+
	                              '</div></div>'
	                            
	                          }
	                          ],*/
					dataSource: {
						data:data,
					    schema: {
							 model: {
			                    	fields: {
			                    		stt: {editable: false},
										code: {editable: false},
			                    		name:{editable: false},
			                    		price:  { type: "number", format: "{0:c}" },
			                    		workDay:{type: "number", format: "{0:c}"},
			                    		description:{type: "String", format: "{0:c}"},
			                    	}
							 }
					    }	
					},
					dataBound: function (e) {
			    	     var grid = $("#taskGrid").data("kendoGrid");
			    	    grid.tbody.find("tr").dblclick(function (e) {
			    	        var dataItem = grid.dataItem(this);
			    	        $('#'+dataItem.code).click();
			    	    });
			    	},
					noRecords: true,
					columnMenu: false,
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
					columns:[{
						title: "TT",
						field: "stt",
						width:'5%',
						template: dataItem => $("#taskGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
						headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
						}, 
				        {
						title: "Mã công việc",
						field: "code",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Tên công việc",
						field: "name",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Đơn giá",
						field: "price",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
						editable: false
					},{
						title: "Ngày công",
						field: "workDay",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Ghi chú",
						field: "description",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}
					]
				});
	    		  
			 });
	      }
	       
	       //nếu click vào màn hình edit, isNewtask là false, các trường hợp còn lại isNewTask là true
	       function doGetTask(isNewTask){
	    	   var obj = isNewTask ? {catWorkItemTypeId:vm.workItemQuota.catWorkItemTypeId, status : 1} :
	    			   {workItemQuotaId:vm.workItemQuota.workItemQuotaId, status : 1}
	    	   			;
	    	   if(!isNewTask){
	    		   return   workItemQuotaService.getTaskQuota(obj);
	    	   }else{
	    		   return workItemQuotaService.getTask(obj)
	    	   }
	       }
	       
	}
	
})();
