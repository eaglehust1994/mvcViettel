(function() {
	'use strict';
	var controllerId = 'goodsController';
	
	angular.module('MetronicApp').controller(controllerId, goodsController);
	
	function goodsController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,goodsService , htmlCommonService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.goodsSearch={
				status:1
		};
		vm.folder='';
		vm.goods={};
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
		                    '<label ng-repeat="column in vm.goodsGrid.columns| filter: vm.gridColumnShowHideFilter">'+
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
							url: Constant.BASE_SERVICE_URL + RestEndpoint.CAT_GOOD_SERVICE_URL+ "/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                           
							    vm.goodsSearch.page = options.page
								vm.goodsSearch.pageSize = options.pageSize

								return JSON.stringify(vm.goodsSearch)

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
					 width: '4%',
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
					title: "Mã hàng",
					field: 'code',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên hàng",
			        field: 'name',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị tính",
			        field: 'unitTypeName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Loại hàng",
			        field: 'goodsType',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Có serial",
			        field: 'isSerial',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Giá gốc",
			        field: 'originPrice',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Kích thước ban đầu",
			        field: 'originSize',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Cân nặng",
			        field: 'weight',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thể tích ban đầu",
			        field: 'volumeOrigin',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thể tích Thực tế",
			        field: 'volumeReal',
			        headerAttributes: {
						style: "text-align:center;"
					},
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
				},{
					title: "Nhà sản xuất",
			        field: 'manufacturerName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Nước sản xuất",
			        field: 'producingCountryName',
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
			vm.goodsSearch.page = null;
			vm.goodsSearch.pageSize = null;
			var data = goodsService.doSearch(vm.goodsSearch);
			console.log(data);
			goodsService.doSearch(vm.goodsSearch).then(function(d){
				CommonService.exportFile(vm.goodsGrid,d.data,vm.listRemove,vm.listConvert,"Danh sách hàng hóa");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.goodsGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

//			vm.add=add;
//		  function add(){
//
//			
//			vm.goods={};
//			 var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Thêm mới tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300',"code"); 
//			  
//		 }
		
//		vm.edit=edit;
//		function edit(dataItem){			
//			vm.goods =dataItem;
//			var teamplateUrl="wms/hohoho/app_paramPopup.html";
//			 var title="Cập nhật tham số ứng dụng";
//			 var windowId="APP_PARAM";
//			 $("#goodsGrid").data('kendoGrid').dataSource.read();
//			 $("#goodsGrid").data('kendoGrid').refresh();
//			 CommonService.populatePopupCreate(teamplateUrl,title,vm.goods,vm,windowId,false,'600','300',"code"); 
//		}
		
              
		vm.save= function(data,isCreateNew){
			data=vm.goods;
						if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							biddingPackageService.creategoods(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.goods = result;
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
                		biddingPackageService.updategoods(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							$("#goodsGrid").data('kendoGrid').dataSource.read();
							$("#goodsGrid").data('kendoGrid').refresh();
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
//							var sizePage = $("#goodsGrid").data("kendoGrid").dataSource.total();
//							var pageSize = $("#goodsGrid").data("kendoGrid").dataSource.pageSize();
//							if(sizePage % pageSize === 1){
//								var currentPage = $("#goodsGrid").data("kendoGrid").dataSource.page();
//								if (currentPage > 1) {
//									$("#goodsGrid").data("kendoGrid").dataSource.page(currentPage - 1);
//								}
//							}
//							 $("#goodsGrid").data('kendoGrid').dataSource.read();
//							 $("#goodsGrid").data('kendoGrid').refresh();
//
//						}, function(errResponse) {
//							toastr.error("Lỗi không xóa được!");
//						});
//			} )
//		}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.goodsSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.goodsGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}

			console.log(grid.dataSource.data());
		}
		vm.selectedPrpject= false;
		
		
		vm.patternOptions={
				dataTextField: "name", placeholder:"Nhập mã đơn vị hoặc tên đơn vị tính",
	            select: function(e) {
	            	
	                var dataItem = this.dataItem(e.item.index());
	                vm.goodsSearch.unitTypeName = dataItem.name;
	                vm.goodsSearch.unitType = dataItem.goodsId;

	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("goodsServiceRest/getForAutoComplete").post({pageSize:10, page:1, name:$("#unitType").val().trim()}).then(function(response){
	                            options.success(response);
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
	            	console.log(  console.log(vm.goodsSearch.unitTypeName));
	            	if(processSearch('unitType',vm.selectedPrpject)){
						  vm.goodsSearch.keySearch2 = null;
						 $('#unitType').val(null);
						  vm.selectedPrpject=false;	
						  }
	            },
	            close: function(e) {
	                // handle the event0
	              }
			};
		
		
		vm.openUnit = function openUnit(){
			var obj={};
			// obj.page=1;
			// obj.pageSize=20;
			goodsService.getAllUnit(obj).then(function(result){
				var templateUrl = 'cat/goods/findUnitPopUp.html';
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
					columns:[{
						title: "TT",
						field: "stt",
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width: '5%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
						},{
						title: "Mã phòng ban",
						field: "code",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					},

					]
				});
				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','popupUnitSearchController');
//				CommonService.populatePopupUnit(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%');
			});
		}
		
		vm.onSave=onSave;
		function onSave(data){
//			vm.stock.departmentId=data.id;
//			vm.stock.departmentName=data.text;
			/* vm.stockSearch.departmentId=data.id; */
			  vm.goodsSearch.unitTypeName = data.name;
              vm.goodsSearch.unitType = data.goodsId;
			$('#unitType').focus();
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.goodsGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.goodsGrid.showColumn(column);
            } else {
            	vm.goodsGrid.hideColumn(column);
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
