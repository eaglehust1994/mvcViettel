
(function() {
	'use strict';
	var controllerId = 'viewHistoryDNController';
	
	angular.module('MetronicApp').controller(controllerId, viewHistoryDNController);
	
	function viewHistoryDNController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant,viewHistoryDNService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.viewHistoryDNSearch = {};
		vm.typeKASearch={};
		vm.oldSearch={};
		
		$(document).ready(function() {
			// getApply();
			fillDataTable();
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		});
		 $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
		 
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
		// table chinh
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
				toolbar: [
		                    {
		                    	name: "actions",
		                        template: '<div class=" pull-left">'
//		      					+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC"'  
//								+ 'ng-click="vm.exportExcelGridForNV()" uib-tooltip="Xuất báo cáo theo nhân viên"  aria-hidden="true" translate>Xuất báo cáo TH nhân viên</button>'
//								+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC"'  
//								+ 'ng-click="vm.exportExcelGridForDV()" uib-tooltip="Xuất báo cáo theo đơn vị"  aria-hidden="true" translate>Xuất báo cáo TH đơn vị</button>'
//								+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC"'  
//								+ 'ng-click="vm.exportTHDoiSoatVatTu()" uib-tooltip="Xuất báo cáo thi tiết"  aria-hidden="true" translate>Xuất báo cáo TH theo PXK</button>'
//								+'<div class=" pull-right">'
//									+ '<input  k-options="vm.patternOptionsHDXL" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC" kendo-auto-complete style="height:30px;outline:auto;margin-right:10px;" type="text" ng-model="vm.typeKASearch.maHdxl" id="maHdxl" aria-hidden="true" />'
//									+ '<input  ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC" k-options="vm.patternOptionsTram" kendo-auto-complete style="height:30px;outline:auto;" type="text" ng-model="vm.typeKASearch.maTramNhan" id="maTramNhan" aria-hidden="true" />'
//								+'</div>'
//								+ '<button  style="width: auto;text-align:center;" ng-hide="checkExportPXK"'  
//								+ 'ng-click="vm.checkExistHD()" uib-tooltip="Kiểm tra tồn tại HĐXL"  aria-hidden="true" translate>Kiểm tra</button>'
		      					+'</div>'
		      					+'</div>'	
		      					+
		      					 
							'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.viewHistoryDNGrid.columns.slice(1,vm.viewHistoryDNGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
							dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
						 
						 $("#viewHistoryDNGridCount11").text(""+response.total);
							 	vm.count = response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].orderId===$scope.listCheck[i].orderId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list;// data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "lichSuDangNhapServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
								vm.viewHistoryDNSearch.page = options.page
								vm.viewHistoryDNSearch.pageSize = options.pageSize                               
								vm.oldSearch = angular.copy(vm.viewHistoryDNSearch);
								return JSON.stringify(vm.viewHistoryDNSearch)
						}
					},					 
					pageSize:10
				} ,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes:   [ 10, 15, 20, 25 ],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columns: [
				{
					title: "TT",
					field:"stt",
			        template: function () {
					  return ++record;
					 },
			        width: '50px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title : "Thao tác",
					template : '<div class="text-center #=id#""> '
							+
							'		<button style=" border: none; " class="#=id# icon_table" ng-hide="dataItem.maTinh!=null"  uib-tooltip="Huỷ" translate>'
							+ '			<i class="fa fa-trash" style="margin-top: 10px;"   aria-hidden="true"></i> '
							+ '		</button>'
							
							 + '</div>',
					width : '50px',
					headerAttributes : {
						style : "text-align:center;font-weight:bold;white-space:normal;"
					}
				}
				,  {
					title: "Tên đăng nhập",
					field: "userCode",
			        width: '150px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Thời gian đăng nhập",
					field: "gioDangNhap",
			        width: '150px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "IP đăng nhập",
			        field: "ipDangNhap",
			        width: '150px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Trạng thái",
			        field: "trangThai",
			        width: '300px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		
		
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.viewHistoryDNGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.viewHistoryDNGrid.showColumn(column);
			} else {
				vm.viewHistoryDNGrid.hideColumn(column);
			}

		}
		vm.doSearch =function() {
			if($("#pxkNhanVienAuto1").val()==""){
				 vm.viewHistoryDNSearch.userCode=null;
			}
			
			if(vm.viewHistoryDNSearch.gioDangNhapTo===""){
				vm.viewHistoryDNSearch.gioDangNhapTo=null;
			}
			
			if(vm.viewHistoryDNSearch.gioDangNhapFrom===""){
				vm.viewHistoryDNSearch.gioDangNhapFrom=null;
			}
			
			if(vm.viewHistoryDNSearch.gioDangNhapFrom!=null&&vm.viewHistoryDNSearch.gioDangNhapTo==null){
				var date1 = kendo.parseDate(vm.viewHistoryDNSearch.gioDangNhapFrom, "dd/MM/yyyy");
				vm.viewHistoryDNSearch.gioDangNhapFrom=kendo.toString(date1,"dd/MM/yyyy HH:mm:ss");
//				vm.viewHistoryDNSearch.gioDangNhapFrom=kendo.toString(date1,"dd/MM/yyyy HH:mm:ss"));
//				vm.viewHistoryDNSearch.gioDangNhapFrom = vm.viewHistoryDNSearch.gioDangNhapFrom.getTime();
			}
			if(vm.viewHistoryDNSearch.gioDangNhapFrom==null&&vm.viewHistoryDNSearch.gioDangNhapTo!=null){
				toastr.warning("Bạn cần nhập ngày đăng nhập: từ ngày...");
				return;
			}
			
			if(vm.viewHistoryDNSearch.gioDangNhapFrom!=null&&vm.viewHistoryDNSearch.gioDangNhapTo!=null){
				var date1 = kendo.parseDate(vm.viewHistoryDNSearch.gioDangNhapFrom, "dd/MM/yyyy");
				vm.viewHistoryDNSearch.gioDangNhapFrom=kendo.toString(date1,"dd/MM/yyyy HH:mm:ss");
				
				var date2 = kendo.parseDate(vm.viewHistoryDNSearch.gioDangNhapTo, "dd/MM/yyyy");
//				date2=kendo.toString(new Date(date2.getTime()+24*3600*1000),"dd/MM/yyyy");
//				var date3 = kendo.parseDate(date2, "dd/MM/yyyy");
				vm.viewHistoryDNSearch.gioDangNhapTo=kendo.toString(date2,"dd/MM/yyyy HH:mm:ss");
			}
			var grid =vm.viewHistoryDNGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    } 
		
    	
    	

        
        
//        vm.patternOptionsNV={
//    			dataTextField: "fullName", placeholder:"Nhập mã hoặc tên nhân viên",
//                select: function(e) {
//                    var dataItem = this.dataItem(e.item.index());
//                    vm.viewHistoryDNSearch.userCode=dataItem.userCode;
//                    vm.viewHistoryDNSearch.fullName=dataItem.fullName;
//                },
//                pageSize: 10,
//                dataSource: {
//                    serverFiltering: true,
//                    transport: {
//                        read: function(options) {
//                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVien").post({pageSize:10, page:1, userCode:$("#pxkNhanVienAuto1").val().trim()}).then(function(response){
//                                options.success(response);
//                            }).catch(function (err) {
//                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
//                            });
//                        }
//                    }
//                },
//                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
//                '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
//                '<p class="col-md-6 text-header-auto">Họ và tên</p>' +
//                	'</div>',
//                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.userCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
//                change: function(e) {
//                	if(processSearch('pxkNhanVienAuto1',vm.selectedPrpject)){
//    					 $('#pxkNhanVienAuto1').val("");
//    					 vm.viewHistoryDNSearch.fullName=null;
//    					 vm.viewHistoryDNSearch.userCode=null;
//    					  vm.selectedPrpject=false;	
//    					  }
//                },
//                close: function(e) {
//                    // handle the event
//                  }
//    		};
//	   
        
        
        
		// end
		}
})();
									
				
