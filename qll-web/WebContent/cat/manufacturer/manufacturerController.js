(function() {
	'use strict';
	var controllerId = 'manufacturerController';
	
	angular.module('MetronicApp').controller(controllerId, manufacturerController);
	
	function manufacturerController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,manufacturerService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.manufacturerSearch={
//				status:1
		};
		vm.folder='';
		vm.manufacturer={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		     		
		
		console.log(Constant.userInfo);
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 setTimeout(function(){
			  $("#keySearch").focus();
			},15);
		/*
		 * setTimeout(function(){ $("#appIds1").focus(); },15);
		 */
		 var record=0;
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
                              template: '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                      '<i class="tooltip1 action-button excelQLK" ng-click="vm.exportFile()" aria-hidden="true"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                      '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                      '<label ng-repeat="column in vm.manufacturerGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                      '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							    $("#unitCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + RestEndpoint.CAT_MANUFACTURER_SERVICE_URL+ "/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                           
							    vm.manufacturerSearch.page = options.page
								vm.manufacturerSearch.pageSize = options.pageSize

								return JSON.stringify(vm.manufacturerSearch)

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
					 width: '10%',
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
					title: "Mã nhà sản xuất",
					field: 'code',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên nhà sản xuất",
			        field: 'name',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
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
			vm.manufacturerSearch.page = null;
			vm.manufacturerSearch.pageSize = null;
			var data = manufacturerService.doSearch(vm.manufacturerSearch);
			console.log(data);
			manufacturerService.doSearch(vm.manufacturerSearch).then(function(d){
				CommonService.exportFile(vm.manufacturerGrid,d.data,vm.listRemove,vm.listConvert,"Danh nhà sản xuất");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.manufacturerGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

//			vm.add=add;
//		  function add(){
//
//			
//			vm.manufacturer={};
//			 var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Thêm mới tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300',"code"); 
//			  
//		 }
		
//		vm.edit=edit;
//		function edit(dataItem){			
//			vm.manufacturer =dataItem;
//			var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Cập nhật tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 $("#manufacturerGrid").data('kendoGrid').dataSource.read();
//			 $("#manufacturerGrid").data('kendoGrid').refresh();
//			 CommonService.populatePopupCreate(teamplateUrl,title,vm.manufacturer,vm,windowId,false,'600','300',"code"); 
//		}
		
              
		vm.save= function(data,isCreateNew){
			data=vm.manufacturer;
						if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							biddingPackageService.createmanufacturer(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.manufacturer = result;
                            doSearch();
                            // CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                            vm.add();
                            
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
								
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
                		biddingPackageService.updatemanufacturer(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							$("#manufacturerGrid").data('kendoGrid').dataSource.read();
							$("#manufacturerGrid").data('kendoGrid').refresh();
                			toastr.success("Cập nhật thành công!");
                			
                			// CommonService.dismissPopup();
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

		}
		
		vm.cancel= cancel ;
		function cancel(){
		/* confirm('Bạn có muốn hủy bỏ thao tác?', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
//		vm.remove=remove;
//		function remove(dataItem){
//			confirm('Bạn thật sự muốn xóa bản ghi đã chọn?', function(){
//				biddingPackageService.remove(dataItem).then(
//						function(d) {
//							toastr.success("Xóa tham số thành công!");
//							var sizePage = $("#manufacturerGrid").data("kendoGrid").dataSource.total();
//							var pageSize = $("#manufacturerGrid").data("kendoGrid").dataSource.pageSize();
//							if(sizePage % pageSize === 1){
//								var currentPage = $("#manufacturerGrid").data("kendoGrid").dataSource.page();
//								if (currentPage > 1) {
//									$("#manufacturerGrid").data("kendoGrid").dataSource.page(currentPage - 1);
//								}
//							}
//							 $("#manufacturerGrid").data('kendoGrid').dataSource.read();
//							 $("#manufacturerGrid").data('kendoGrid').refresh();
//
//						}, function(errResponse) {
//							toastr.error("Lỗi không xóa được!");
//						});
//			} )
//		}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.manufacturerSearch={
//					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.manufacturerGrid;	
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
        		vm.manufacturerGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.manufacturerGrid.showColumn(column);
            } else {
            	vm.manufacturerGrid.hideColumn(column);
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
            	biddingPackageService.exportpdf(obj);
            }
	        
	        
	}
	
})();
