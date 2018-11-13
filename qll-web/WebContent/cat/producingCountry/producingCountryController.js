(function() {
	'use strict';
	var controllerId = 'producingCountryController';
	
	angular.module('MetronicApp').controller(controllerId, producingCountryController);
	
	function producingCountryController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,producingCountryService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.catProvinceSearch={
//				status:1
		};
		vm.folder='';
		vm.catUnit={};
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
                              template: 
                              
                              '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                    '<i class="tooltip1 action-button excelQLK" ng-click="vm.exportFile()" aria-hidden="true"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.catProvinceGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							    $("#provinceCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + RestEndpoint.PRODUCING_COUNTRY_SERVICE_URL+ "/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                           
							    vm.catProvinceSearch.page = options.page
								vm.catProvinceSearch.pageSize = options.pageSize

								return JSON.stringify(vm.catProvinceSearch)

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
					title: "Mã quốc gia",
					field: 'code',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên quốc gia",
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
			vm.catProvinceSearch.page = null;
			vm.catProvinceSearch.pageSize = null;
			var data = producingCountryService.doSearch(vm.catProvinceSearch);
			console.log(data);
			producingCountryService.doSearch(vm.catProvinceSearch).then(function(d){
				CommonService.exportFile(vm.catProvinceGrid,d.data,vm.listRemove,vm.listConvert,"Danh nước sản xuất");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.catProvinceGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

//			vm.add=add;
//		  function add(){
//
//			
//			vm.catUnit={};
//			 var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Thêm mới tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300',"code"); 
//			  
//		 }
		
//		vm.edit=edit;
//		function edit(dataItem){			
//			vm.catUnit =dataItem;
//			var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Cập nhật tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 $("#catProvinceGrid").data('kendoGrid').dataSource.read();
//			 $("#catProvinceGrid").data('kendoGrid').refresh();
//			 CommonService.populatePopupCreate(teamplateUrl,title,vm.catUnit,vm,windowId,false,'600','300',"code"); 
//		}
		
              
		vm.save= function(data,isCreateNew){
			data=vm.catUnit;
						if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							biddingPackageService.createcatUnit(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.catUnit = result;
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
                		biddingPackageService.updatecatUnit(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							$("#catProvinceGrid").data('kendoGrid').dataSource.read();
							$("#catProvinceGrid").data('kendoGrid').refresh();
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
//							var sizePage = $("#catProvinceGrid").data("kendoGrid").dataSource.total();
//							var pageSize = $("#catProvinceGrid").data("kendoGrid").dataSource.pageSize();
//							if(sizePage % pageSize === 1){
//								var currentPage = $("#catProvinceGrid").data("kendoGrid").dataSource.page();
//								if (currentPage > 1) {
//									$("#catProvinceGrid").data("kendoGrid").dataSource.page(currentPage - 1);
//								}
//							}
//							 $("#catProvinceGrid").data('kendoGrid').dataSource.read();
//							 $("#catProvinceGrid").data('kendoGrid').refresh();
//
//						}, function(errResponse) {
//							toastr.error("Lỗi không xóa được!");
//						});
//			} )
//		}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.catProvinceSearch={
//					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.catProvinceGrid;	
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
        		vm.catProvinceGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.catProvinceGrid.showColumn(column);
            } else {
            	vm.catProvinceGrid.hideColumn(column);
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
