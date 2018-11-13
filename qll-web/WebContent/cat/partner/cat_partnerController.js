(function() {
	'use strict';
	var controllerId = 'catpartnerController';
	
	angular.module('MetronicApp').controller(controllerId, catpartnerController);
	
	function catpartnerController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,catPartnerService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.catPartnerSearch={
//				status:1
		};
		vm.folder='';
		vm.catPartner={};
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
		                      '<label ng-repeat="column in vm.catPartnerGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                      '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							    $("#partnerCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + RestEndpoint.CAT_PARTNER_SERVICE_URL+ "/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                           
							    vm.catPartnerSearch.page = options.page
								vm.catPartnerSearch.pageSize = options.pageSize

								return JSON.stringify(vm.catPartnerSearch)

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
					 width: '5%',
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
					title: "Mã đối tác",
					field: 'code',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên đối tác",
			        field: 'name',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Mã số thuế",
			        field: 'taxCode',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Fax",
			        field: 'fax',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số điện thoại",
			        field: 'phone',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Địa chỉ",
			        field: 'address',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Người đại diện",
			        field: 'represent',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Loại đối tác",
			        field: 'partnerType',
			        headerAttributes: {
						style: "text-align:center;"
					},
					template :  "# if(partnerType == 0){ #" + "#= 'Đối tác trong Viettel' #" + "# } " + "else if (partnerType == 1) { # " + "#= 'Đối tác ngoài Viettel' #"+ "#} #",
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Mô tả",
			        field: 'description',
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
			field:"partnerType",
			data:{
				1:'Đối tác ngoài Viettel',
				0:'Đối tác trong Viettel'
			}
		}
		]
		
		
		vm.exportFile = function exportFile() {
			vm.catPartnerSearch.page = null;
			vm.catPartnerSearch.pageSize = null;
			var data = catPartnerService.doSearch(vm.catPartnerSearch);
			console.log(data);
			catPartnerService.doSearch(vm.catPartnerSearch).then(function(d){
				CommonService.exportFile(vm.catPartnerGrid,d.data,vm.listRemove,vm.listConvert,"Danh sách đơn vị");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.catPartnerGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

//			vm.add=add;
//		  function add(){
//
//			
//			vm.catPartner={};
//			 var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Thêm mới tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300',"code"); 
//			  
//		 }
		
//		vm.edit=edit;
//		function edit(dataItem){			
//			vm.catPartner =dataItem;
//			var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Cập nhật tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 $("#catPartnerGrid").data('kendoGrid').dataSource.read();
//			 $("#catPartnerGrid").data('kendoGrid').refresh();
//			 CommonService.populatePopupCreate(teamplateUrl,title,vm.catPartner,vm,windowId,false,'600','300',"code"); 
//		}
		
              
		vm.save= function(data,isCreateNew){
			data=vm.catPartner;
						if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							biddingPackageService.createcatPartner(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.catPartner = result;
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
                		biddingPackageService.updatecatPartner(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							$("#catPartnerGrid").data('kendoGrid').dataSource.read();
							$("#catPartnerGrid").data('kendoGrid').refresh();
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
//							var sizePage = $("#catPartnerGrid").data("kendoGrid").dataSource.total();
//							var pageSize = $("#catPartnerGrid").data("kendoGrid").dataSource.pageSize();
//							if(sizePage % pageSize === 1){
//								var currentPage = $("#catPartnerGrid").data("kendoGrid").dataSource.page();
//								if (currentPage > 1) {
//									$("#catPartnerGrid").data("kendoGrid").dataSource.page(currentPage - 1);
//								}
//							}
//							 $("#catPartnerGrid").data('kendoGrid').dataSource.read();
//							 $("#catPartnerGrid").data('kendoGrid').refresh();
//
//						}, function(errResponse) {
//							toastr.error("Lỗi không xóa được!");
//						});
//			} )
//		}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.catPartnerSearch={
//					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.catPartnerGrid;	
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
        		vm.catPartnerGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.catPartnerGrid.showColumn(column);
            } else {
            	vm.catPartnerGrid.hideColumn(column);
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
