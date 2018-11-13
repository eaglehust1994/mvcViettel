(function() {
	'use strict';
	var controllerId = 'tblKhQlVcController';

	angular.module('MetronicApp').controller(controllerId,
			tblKhQlVcController);

	function tblKhQlVcController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, tblKhQlVcService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.tblKhQlVcSearch = {};
		vm.KhQlVcPop ={};
		vm.tblKhQlVcReport ={}
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
		vm.roleememe=$rootScope.RoleMenu.checkRole.checkPKH;
		
		setTimeout(function() {
			$("#changeId").focus();
		}, 15);
		
		var currenRow1;
		if(currenRow1!==undefined ){
			$( function() {
				currenRow1.tooltip();
			  } );
		}
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');

		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if ($rootScope.stringtile) {
				vm.String = $rootScope.stringtile;
			}

		}

		
		
		var record = 0;
		vm.oldSearch = {};
		function fillDataTable(data) {
			// if(data.status !==1){
			// $('#icon').prop("disabled",true);
			// }
			vm.gridOptions = kendoConfig
					.getGridOptions({
						autoBind : true,
						resizable : true,
						scrollable : true,
						sortable : false,
						dataBinding : function() {
							record = (this.dataSource.page() - 1)
									* this.dataSource.pageSize();
						},
						toolbar : [ {
							name : "actions",
							// 
							template : '<div class=" pull-left ">'
									+ '<button class="btn btn-qlk padding-search-right addQLK margin_right10"'
									+ 'ng-click="vm.importFile()" ng-show="RoleMenu.checkRole.checkPKH" aria-hidden="true" uib-tooltip="Thêm mới danh sách bản ghi" translate >Thêm mới</button>'
									 + '<button class="btn btn-qlk padding-search-right margin_right10 bieuDo" style="width: auto;"'
									 + 'ng-click="vm.reportChart()"  aria-hidden="true" uib-tooltip="Báo cáo biểu đồ" aria-hidden="true" translate>Báo cáo biểu đồ</button>'
									 +'</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<button class="btn btn-qlk padding-search-right deletehd" ng-show="RoleMenu.checkRole.checkPKH" aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.tblKhQlVcGrid.columns.slice(1,vm.tblKhQlVcGrid.columns.length)">'
									+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
									+ '</label>' + '</div></div>'

						} ],
						
						dataSource : {

							serverPaging : true,
							schema : {
								total : function(response) {

									return response.total; // total is returned
									// in
									// the "total" field of
									// the response
								},
								data : function(response) {
									$("#tblKhQlVcCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "tblKhQlvcRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {

									vm.tblKhQlVcSearch.page = options.page;
									vm.tblKhQlVcSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.tblKhQlVcSearch);
									return JSON.stringify(vm.tblKhQlVcSearch);

								}
							},
							pageSize :10
						},
						dataBound : function(data) {
						},
						noRecords : true,
						columnMenu : false,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả hiển thị")
						},
						pageable : {
							refresh : false,
							pageSizes :  [ 10, 15, 20, 25 ],
							messages : {
								display : "{0}-{1} của {2} kết quả",
								itemsPerPage : "kết quả/trang",
								empty : "Không có kết quả hiển thị"
							}
						},
						columns : [
								{
									title : "TT",
									field : "stt",
									width : '50px',
									columnMenu : false,
									template : function() {
										return ++record;
									},
									headerAttributes : {
										style : "text-align:center;white-space:normal;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Thao tác",
									template : '<div class="text-center #=khQlvcId#""> '
											+ '		<button style=" border: none; " class="#=khQlvcId# icon_table" aria-hidden="true"  uib-tooltip="Cập nhật bản ghi " translate>'
											+ '			<i class="fa gandonvi"  ng-click="vm.updateNv(dataItem)" ng-show="RoleMenu.checkRole.checkPKH"   aria-hidden="true"></i> '
											+ '		</button>'
											
								
											+'		<button  class="#=khQlvcId# icon_table" style=" border: none; " uib-tooltip="Xóa" translate> '+
											'		<i class="fa deletehdCon" ng-click=vm.removeDetail(dataItem) ng-show="RoleMenu.checkRole.checkPKH" aria-hidden="true"></i>'+
											'		</button>'
											+ '</div>',
									width : '120px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;"
									}
								},
								{
									title : "Tổng số nhiệm vụ",
									field : 'tongNv',
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								{
									title : "Tổng số nhiệm vụ chậm",
									field : 'tongCham',
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : " Mã đơn vị",

									field : 'maDonVi',
									width : '120px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Đơn vị",

									field : 'tenDonVi',
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tháng",
									field : 'thang',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Năm",
									field : 'nam',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Nhân viên giao việc",
									field : 'tenNvGiaoViec',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Mã nhân viên",
									field : 'maNvGiaoViec',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								}
								
								
								]

					});
		}

		
		//list danh sách
		vm.doSearch = doSearch;
		function doSearch() {	
			vm.showDetail = false;
			var grid = vm.tblKhQlVcGrid;
			if($("#pkhThang").val()==""){
				vm.tblKhQlVcSearch.thang=null;
			}
			if($("#pkhNam").val()==""){
				vm.tblKhQlVcSearch.nam=null;
			}
			if($("#pkhMaDonVi").val()==""){
				vm.tblKhQlVcSearch.tenDonVi=null;
				vm.tblKhQlVcSearch.maDonVi=null;
			}
			if($("#pkhMaNv").val()==""){
				vm.tblKhQlVcSearch.tenNvGiaoViec=null;
				vm.tblKhQlVcSearch.maNvGiaoViec=null;
			}
		
			
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		// xoa 1 ban ghi
		  vm.removeDetail= function(dataItem){
	        	confirm('Bạn có chắc chắn muốn xóa bản ghi?', function(){
	        		tblKhQlVcService.deleteObj(dataItem).then(function(result){
							toastr.success("Xóa bản ghi thành công!");
							vm.doSearch();
					});	
	        	});
				
			}
		//xoa list ban ghi
		 vm.removeAny=function(){
		    	
		    	vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				
			if(vm.tblKhQlVcSearch.thang || vm.tblKhQlVcSearch.nam || vm.tblKhQlVcSearch.tenDonVi || vm.tblKhQlVcSearch.tenNvGiaoViec){
							confirm("Bạn có muốn xóa!", function() {
								tblKhQlVcService.deleteListObj(vm.oldSearch).then(function(result){
									if(result.error){
					    				toastr.error(result.error);
					    				return;
					    			}
					    			toastr.success("Xóa hợp đồng thành công!");
					    			  var sizePage = $("#tblKhQlVcGrid").data("kendoGrid").dataSource.total();
					    			  var pageSize = $("#tblKhQlVcGrid").data("kendoGrid").dataSource.pageSize();
					    					if(sizePage % pageSize === 1){
					    								var currentPage = $("#tblKhQlVcGrid").data("kendoGrid").dataSource.page();
					    								if (currentPage > 1) {
					    									$("#tblKhQlVcGrid").data("kendoGrid").dataSource.page(currentPage - 1);
					    								}
					    							}
					    			$("#tblKhQlVcGrid").data('kendoGrid').dataSource.read();
					    			$("#tblKhQlVcGrid").data('kendoGrid').refresh();
					    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
								},function(errResponse){
					                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thẩm định"));
					            });
							})
					
				
			}else{
				toastr.warning(gettextCatalog.getString("Chưa tìm kiếm bản ghi cần xóa"));
			}
				
		    }
		
			// mở popup thêm mới bản ghi
		
		vm.updateNv = function(dataItem){
		  	vm.KhQlVcPop={};
		  	vm.KhQlVcPop=dataItem;
			var teamplateUrl="qll/A_PKH-tblKhQlVc/addKhQlVc.html";
		    var title="Thêm mới Thống kê nhiệm vụ";
		    var windowId="ADD_KH_QLVC";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','80%');
		}
		//ghi lại
		vm.saveKhNv = function(){
			vm.KhQlVcPop;
			
			if(vm.KhQlVcPop.tongNv!=null){
				vm.KhQlVcPop.tongNv = vm.replaceVAlue(vm.KhQlVcPop.tongNv);
			}
		
			if(vm.KhQlVcPop.tongCham!=null){
				vm.KhQlVcPop.tongCham = vm.replaceVAlue(vm.KhQlVcPop.tongCham);
			}
			
			if(!vm.KhQlVcPop.tenNvGiaoViec){
				toastr.error("Tên nhân viên giao việc không được để trống");
				return;
			}
			if(!vm.KhQlVcPop.tenDonVi){
				toastr.error("Tên đơn vị không được để trống");
				return;
			}
			tblKhQlVcService.updateKhNv(vm.KhQlVcPop).then(function(result){
				if(result.error){
    				toastr.error(result.error);
    				return;
    			}
				toastr.success("Cập nhật nhiệm vụ thành công!");
				var sizePage = $("#tblKhQlVcGrid").data("kendoGrid").dataSource.total();
  			  var pageSize = $("#tblKhQlVcGrid").data("kendoGrid").dataSource.pageSize();
  					if(sizePage % pageSize === 1){
  								var currentPage = $("#tblKhQlVcGrid").data("kendoGrid").dataSource.page();
  								if (currentPage > 1) {
  									$("#tblKhQlVcGrid").data("kendoGrid").dataSource.page(currentPage - 1);
  								}
  							}
  			$("#tblKhQlVcGrid").data('kendoGrid').dataSource.read();
  			$("#tblKhQlVcGrid").data('kendoGrid').refresh();
  			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			},function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
			            })
	
		}
	
		// download biểu mẫu 
		vm.downloadImportTemplate = function(){
			var fileName="KhTkNhiem_vu";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}
		
		// mở popup tải file excel thêm mới bản ghi
		vm.importFile = function() {
			// vm.orderChangeGoodsDetailPop={};
			var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
			var title = "Import bản excel thống kê nhiệm vụ";
			var windowId = "IMPORT_DL_KHTk";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}
		//tải file excel
		
		vm.submit = submit;
		function submit() {
			$("#upfile").css("display","none");
			$("#modalLoading").css("display","block");
			var loading = $("#modalLoading");
	         kendo.ui.progress(loading, true);
			var t0 = performance.now();
			var checkfile = 100;
			if ($("#fileChange")[0].files[0] == null) {
				toastr.warning("Bạn chưa chọn file để import");
				return;
			}
			if ($("#fileChange")[0].files[0].name.split('.').pop() != 'xls'
					&& $("#fileChange")[0].files[0].name.split('.').pop() != 'xlsx') {
				toastr.warning("Sai định dạng file");
				return;
			}
			
			if ((($("#fileChange")[0].files[0].size)/1024)/1024 > checkfile) {
			toastr.warning("dung luong file vuot qua" + checkfile + "Mb");
			return;
			}
			var formData = new FormData();
			formData.append('multipartFile', $('#fileChange')[0].files[0]);
			return $
					.ajax({
						url : RestEndpoint.BASE_SERVICE_URL
								+ RestEndpoint.TBL_KH_QLVC
								+ "/importFile?folder=" + vm.folder,
						type : "POST",
						data : formData,
						enctype : 'multipart/form-data',
						processData : false,
						contentType : false,
						cache : false,
						success : function(data) {
							if (data.fileName) {
								if (data.error) {
									toastr.error(data.error);
									return;
								}
								toastr.error("Có lỗi trong file import, kiểm tra lại file trả về");
								window.location = Constant.BASE_SERVICE_URL
										+ "fileservice/downloadFileATTT?"
										+ data.fileName;
								// return;
								
							} else {
								toastr.success("Import thành công!");
								
							}
							kendo.ui.progress(loading, false);
							var t1 = performance.now();
							setTimeout(function() {
								alert("Thời gian thực hiện " + (t1 - t0) + " milliseconds.")
							}, 15);
							vm.doSearch();
							CommonService.closePopup1();
						}
					});

		}
		
		//tự dộng tháng
		 vm.patternOptionsMonth={
	    			dataTextField: "thang", placeholder:"Nhập Tháng",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.tblKhQlVcSearch.thang=dataItem.thang;
	                   
	                },
	                
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMonth").post({pageSize:10, page:1, thang:$("#pkhThang").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-12 text-header-auto">Tháng</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.thang #</div></div>',
	                change: function(e) {
	                	if(processSearch('pkhThang',vm.selectedPrpject)){
	    					 $('#pkhThang').val("");
	    					 vm.tblKhQlVcSearch.thang="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 
		 vm.patternOptionsMonthPop={
	    			dataTextField: "thang", placeholder:"Nhập Tháng",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.tblKhQlVcReport.thang=dataItem.thang;
	                   
	                },
	                
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMonth").post({pageSize:10, page:1, thang:$("#thangReport").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-12 text-header-auto">Tháng</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.thang #</div></div>',
	                change: function(e) {
	                	if(processSearch('thangReport',vm.selectedPrpject)){
	    					 $('#thangReport').val("");
	    					 vm.tblKhQlVcReport.thang="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 //tự động năm
		 vm.patternOptionsYear={
	    			dataTextField: "nam", placeholder:"Nhập Năm",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.tblKhQlVcSearch.nam=dataItem.nam;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteYear").post({pageSize:10, page:1, nam:$("#pkhNam").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-12 text-header-auto">Năm</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.nam #</div></div>',
	                change: function(e) {
	                	if(processSearch('pkhNam',vm.selectedPrpject)){
	    					 $('#pkhNam').val("");
	    					 vm.tblKhQlVcSearch.nam="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 //tự động đơn vị
		 vm.patternOptionsMaDv={
	    			dataTextField: "tenDonVi", placeholder:"Nhập tên hoặc mã đơn vị",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.tblKhQlVcSearch.maDonVi=dataItem.maDonVi;
	                    vm.tblKhQlVcSearch.tenDonVi=dataItem.tenDonVi;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMaDv").post({pageSize:10, page:1, department:$("#pkhMaDonVi").val().trim()}).then(function(response){
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
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDonVi #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDonVi #</div> </div>',
	                change: function(e) {
	                	if(processSearch('pkhMaDonVi',vm.selectedPrpject)){
	    					 $('#pkhMaDonVi').val("");
	    					 vm.tblKhQlVcSearch.maDonVi="";
	    					 vm.tblKhQlVcSearch.tenDonVi="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 vm.patternOptionsMaDvPop={
	    			dataTextField: "tenDonVi", 
	    			placeholder:"Nhập tên hoặc mã đơn vị",
	    			
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.tblKhQlVcReport.maDonVi=dataItem.maDonVi;
	                    vm.tblKhQlVcReport.tenDonVi=dataItem.tenDonVi;
	                },
	            
               
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                   
	                    transport: {
	                        read: function(options) {	
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMaDv").post({pageSize:10, page:1, department:$("#maDonViReport").val().trim()}).then(function(response){
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
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDonVi #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDonVi #</div> </div>',
	                change: function(e) {
	                	if(processSearch('maDonViReport',vm.selectedPrpject)){
	    					 $('#maDonViReport').val("");
	    					 vm.tblKhQlVcReport.maDonVi="";
	    					 vm.tblKhQlVcReport.tenDonVi="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 //tự động nhân viên
		 vm.patternOptionsMaNv={
	    			dataTextField: "tenNvGiaoViec", placeholder:"Nhập tên hoặc mã nhân viên",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.tblKhQlVcSearch.maNvGiaoViec=dataItem.maNvGiaoViec;
	                    vm.tblKhQlVcSearch.tenNvGiaoViec=dataItem.tenNvGiaoViec;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMaNv").post({pageSize:10, page:1, name:$("#pkhMaNv").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
	                '<p class="col-md-6 text-header-auto">Tên nhân viên</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maNvGiaoViec #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenNvGiaoViec #</div> </div>',
	                change: function(e) {
	                	if(processSearch('pkhMaNv',vm.selectedPrpject)){
	    					 $('#pkhMaNv').val("");
	    					 vm.tblKhQlVcSearch.maNvGiaoViec="";
	    					 vm.tblKhQlVcSearch.tenNvGiaoViec="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 
		 vm.patternOptionsMaNvPop={
	    			dataTextField: "tenNvGiaoViec", placeholder:"Nhập tên hoặc mã nhân viên",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.tblKhQlVcReport.maNvGiaoViec=dataItem.maNvGiaoViec;
	                    vm.tblKhQlVcReport.tenNvGiaoViec=dataItem.tenNvGiaoViec;
	                },
	                pageSize: 10,
	                
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMaNv").post({pageSize:10, page:1, name:$("#tenNvReport").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
	                '<p class="col-md-6 text-header-auto">Tên nhân viên</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maNvGiaoViec #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenNvGiaoViec #</div> </div>',
	                change: function(e) {
	                	if(processSearch('tenNvReport',vm.selectedPrpject)){
	    					 $('#tenNvReport').val("");
	    					 vm.tblKhQlVcReport.maNvGiaoViec="";
	    					 vm.tblKhQlVcReport.tenNvGiaoViec="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		
		 
		 $(document).ready(function() {
            $("#maDonViReport").kendoMultiSelect({
                placeholder: "Nhập tên hoặc mã đơn vị",
                itemTemplate: '<span class="order-id">#= tenDonVi #</span> ',
                dataTextField: "tenDonVi",
                dataValueField: "maDonVi",
                
                virtual: {
                    itemHeight: 26,
                    valueMapper: function(options) {
                        $.ajax({
                            url: RestEndpoint.BASE_SERVICE_URL
							+ RestEndpoint.TBL_KH_QLVC+"tblKhQlvcRsServiceRest/getForAutoCompleteMaDv",
                            type: "POST",
                            dataType: "jsonp",
                            data: convertValues(options.value),
                            success: function (data) {
                                options.success(data);
                            }
                        })
                    }
                },
                dataSource: {
                    type: "data",
                    transport: {
                    	read: function(options) {	
                        
                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMaDv").post({pageSize:10, page:1, department:$("#maDonViReport").val()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    },
                    schema: {
                        model: {
                            fields: {
                            	maDonVi: { type: "string" },
                            	tenDonVi: { type: "string" }
                                
                            }
                        }
                    },
                    pageSize: 10,
                    serverPaging: true,
                    serverFiltering: true
                },
               
            });
        });
		 $(document).ready(function() {
	            $("#thangReport").kendoMultiSelect({
	                placeholder: "Nhập tháng" ,
	                		
	                itemTemplate: '<span class="order-id">#= thang #</span> ',
	                dataTextField: "thang",
	                dataValueField: "thang",
	                dataSource: {
	                    type: "data",
	                    transport: {
	                    	read: function(options) {	
	                        
	                            return Restangular.all("tblKhQlvcRsServiceRest/getForAutoCompleteMonth").post({pageSize:10, page:1}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    },
	                    schema: {
	                        model: {
	                            fields: {
	                            	thang: { type: "string" }
	                            	
	                                
	                            }
	                        }
	                    },
	                    pageSize: 10,
	                    serverPaging: true,
	                    serverFiltering: true
	                },
	               
	            });
	        });
        function convertValues(value) {
            var data = {};

            value = $.isArray(value) ? value : [value];

            for (var idx = 0; idx < value.length; idx++) {
                data["values[" + idx + "]"] = value[idx];
            }

            return data;
        }
		 
       
		 vm.reportChart = function(){
		
				 CommonService.goTo('BAO_CAO_BIEU_DO_QLCV');

		 }
		 // biểu đồ tháng  theo đơn vị
		 
		 vm.getChartDept = getChartDept;
		 function getChartDept(){
			 vm.tblKhQlVcReport;

			 vm.tblKhQlVcReport.listThang=$("#thangReport").data("kendoMultiSelect").value();
			 vm.tblKhQlVcReport.listMaDonVi=$("#maDonViReport").data("kendoMultiSelect").value();
			 
		if(!vm.tblKhQlVcReport.nam){
			vm.tblKhQlVcReport.nam=kendo.toString(new Date((new Date()).getTime()),"yyyy");
		}
		if(vm.tblKhQlVcReport.listMaDonVi.length==0){
			toastr.warning("Bạn chưa chọn đơn vị");
			return;
		}
		
		if(vm.tblKhQlVcReport.listThang.length==0){
			toastr.warning("Bạn chưa chọn tháng");
			return;
		}	
		if(vm.tblKhQlVcReport.tenNvGiaoViec){
			tblKhQlVcService.getChartDept(vm.tblKhQlVcReport).then(
					
					function(data){
						console.log(data);
						Highcharts.chart('chartCV', {
					
					    chart: {
					        zoomType: 'xy'
					    },
					   

				    	title: {
					        text: 'Biểu đồ thống kê nhiệm vụ các đơn vị trong tháng : '+ vm.tblKhQlVcReport.listThang + 'theo người chủ trì :' + vm.tblKhQlVcReport.tenNvGiaoViec
					    },

					   
					    xAxis: [{
					        categories: data.listMaDonVi,
					    }],
				        yAxis: [
				        	 { // Secondary yAxis
							        title: {
							            text: 'Tổng số',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        labels: {
							            format: '{value} nhiệm vụ',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        opposite: true
							    },
				        	{ // Primary yAxis
					        labels: {
					            format: '{value} %',
					            style: {
					                color: Highcharts.getOptions().colors[1]
					            }
					        },
					        allowDecimals: false,
					        min: 0,
					        title: {
					            text: ' Ti lệ',
					            style: {
					                color: Highcharts.getOptions().colors[1]
					            }
					        }
					    }],
					    tooltip: {
					    	 shared: true,
						        crosshair: true,
					   
					        headerFormat: '<b>{point.x}</b><br/>',
					        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
					    },
					  
					    plotOptions: {
					        column: {
					            stacking: 'normal',
						        dataLabels: {
					                enabled: true,
					                color: '#ed6b75'
					            },
					            enableMouseTracking: false
					        },
					        spline: {
					          
					        dataLabels: {
				                enabled: true
				            },
				            enableMouseTracking: false
					        }
					    
					    },
					    series: [
					        {
					        name: 'Tổng nhiệm vụ hoàn thành',
					        type: 'column',

					      
					        data: data.listTongNvHoanThanh,
					        tooltip: {
					            valueSuffix: ' nhiệm vụ'
					        },
					        stack:'NV'

					    }, 
					      {
					          name: 'Tổng nhiệm vụ chậm',
					        type: 'column',

					        
					       tooltip: {
					            valueSuffix: ' nhiệm vụ'
					        },
					        data: data.listTongCham,
					        stack:'NV'
					    }, 
					      {
					        name: 'tỉ lệ chậm',
					        type: 'spline',
					        yAxis: 1,
					        data: data.listTile,
					        tooltip: {
					            valueSuffix: '%'
					        }
					    }]
						})	
					},	
						
				)
		}else{
			tblKhQlVcService.getChartDept(vm.tblKhQlVcReport).then(
					
					function(data){
						console.log(data);
						Highcharts.chart('chartCV', {
					
					    chart: {
					        zoomType: 'xy'
					    },
					    
					    title: {
						        text: 'Biểu đồ thống kê tổng hợp nhiệm vụ các đơn vị trong tháng : '+ vm.tblKhQlVcReport.listThang 
						    },
						    
					    xAxis: [{
					        categories: data.listMaDonVi,}],
				        yAxis: [
				        	 { // Secondary yAxis
							        title: {
							            text: 'Tổng số',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        labels: {
							            format: '{value} nhiệm vụ',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        opposite: true
							    },
				        	{ // Primary yAxis
					        labels: {
					            format: '{value} %',
					            style: {
					                color: Highcharts.getOptions().colors[1]
					            }
					        },
					        allowDecimals: false,
					        min: 0,
					        title: {
					            text: ' Ti lệ',
					            style: {
					                color: Highcharts.getOptions().colors[1]
					            }
					        }
					    }],
					    tooltip: {
					    	 shared: true,
						        crosshair: true,
					   
					        headerFormat: '<b>{point.x}</b><br/>',
					        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
					    },
					  
					    plotOptions: {
					        column: {
					            stacking: 'normal',
						        dataLabels: {
					                enabled: true,
					                color: '#ed6b75'
					            },
					            enableMouseTracking: false
					        },
					        spline: {
					          
					        dataLabels: {
				                enabled: true
				            },
				            enableMouseTracking: false
					        }
					    
					    },
					    series: [
					        {
					        name: 'Tổng nhiệm vụ hoàn thành',
					        type: 'column',

					      
					        data: data.listTongNvHoanThanh,
					        tooltip: {
					            valueSuffix: ' nhiệm vụ'
					        },
					        stack:'NV'

					    }, 
					      {
					          name: 'Tổng nhiệm vụ chậm',
					        type: 'column',

					        
					       tooltip: {
					            valueSuffix: ' nhiệm vụ'
					        },
					        data: data.listTongCham,
					        stack:'NV'
					    }, 
					      {
					        name: 'tỉ lệ chậm',
					        type: 'spline',
					        yAxis: 1,
					        data: data.listTile,
					        tooltip: {
					            valueSuffix: '%'
					        }
					    }]
						})	
					},	
						
				);
		}
		
		 }
		 
		 
		 //biểu đồ đơn vị theo tháng, năm
		 vm.getChartMonth = getChartMonth;
		 function getChartMonth(){
			 
//		if(!vm.tblKhQlVcSearch.tenDonvi&&!vm.tblKhQlVcSearch.tenNvGiaoViec){
//			if(!vm.tblKhQlVcSearch.nam){
//				vm.tblKhQlVcSearch.nam=kendo.toString(new Date((new Date()).getTime()),"yyyy");
//			}
			 
			 vm.tblKhQlVcReport.listMaDonVi=$("#maDonViReport").data("kendoMultiSelect").value();
			 vm.tblKhQlVcReport.listThang=$("#thangReport").data("kendoMultiSelect").value();
			
			 if(vm.tblKhQlVcReport.listMaDonVi.length==0){
					toastr.warning("Bạn chưa chọn đơn vị");
					return;
				}
				
				if(vm.tblKhQlVcReport.listThang.length==0){
					toastr.warning("Bạn chưa chọn tháng");
					return;
				}
				 
				if(vm.tblKhQlVcReport.tenNvGiaoViec==""){
					vm.tblKhQlVcReport.tenNvGiaoViec=null;
					vm.tblKhQlVcReport.maNvGiaoViec=null;
				}
			if(vm.tblKhQlVcReport.tenNvGiaoViec){
				tblKhQlVcService.getChartMonth(vm.tblKhQlVcReport).then(
						
						function(data){
							Highcharts.chart('chartCV', {
								chart: {
							        zoomType: 'xy'
							    },

							    title: {
							        text: 'Biểu đồ nhiệm vụ tháng theo đơn vị : '+ vm.tblKhQlVcReport.listMaDonVi + ' (Người giao viêc : ' + vm.tblKhQlVcReport.tenNvGiaoViec + ')'
							    },
							   
							    xAxis: [{
							        categories: data.listThang
							                    }],
			                    yAxis: [
						        	 { // Secondary yAxis
									        title: {
									            text: 'Tổng số',
									            style: {
									                color: '#ed6b75'
									            }
									        },
									        labels: {
									            format: '{value} nhiệm vụ',
									            style: {
									                color: '#ed6b75'
									            }
									        },
									        opposite: true
									    },
						        	{ // Primary yAxis
							        labels: {
							            format: '{value} %',
							            style: {
							                color: Highcharts.getOptions().colors[1]
							            }
							        },
							        allowDecimals: false,
							        min: 0,
							        title: {
							            text: ' Ti lệ',
							            style: {
							                color: Highcharts.getOptions().colors[1]
							            }
							        }
							    }],
							    tooltip: {
							    	 shared: true,
								        crosshair: true,
							   
							        headerFormat: '<b>{point.x}</b><br/>',
							        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							    },
							  
							    plotOptions: {
							    	column: {
							            stacking: 'normal',
								        dataLabels: {
							                enabled: true,
							                color: '#ed6b75'
							            },
							            enableMouseTracking: false
							        },
							        spline: {
							          
							        dataLabels: {
						                enabled: true
						            },
						            enableMouseTracking: false
							        }
							    
							    },
							    series: [
							    	{
								        name: 'Tổng nhiệm vụ hoàn thành',
								        type: 'column',

								      
								        data: data.listTongNvHoanThanh,
								        tooltip: {
								            valueSuffix: ' nhiệm vụ'
								        },
								        stack:'NV'

								    }, 
								      {
								          name: 'Tổng nhiệm vụ chậm',
								        type: 'column',

								        
								       tooltip: {
								            valueSuffix: ' nhiệm vụ'
								        },
								        data: data.listTongCham,
								        stack:'NV'
								    }, 
								      {
								        name: 'tỉ lệ chậm',
								        type: 'spline',
								        yAxis: 1,
								        data: data.listTile,
								        tooltip: {
								            valueSuffix: '%'}
								    
								        }]
							})
						}
				
				)
			}else{
				tblKhQlVcService.getChartMonth(vm.tblKhQlVcReport).then(
						
						function(data){
							
							Highcharts.chart('chartCV', {
							chart: {
						        zoomType: 'xy'
						    },

						    title: {
						        text: 'Biểu đồ nhiệm vụ tháng theo đơn vị :  '+vm.tblKhQlVcReport.listMaDonVi
						    },
						   
						    xAxis: [{
						        categories: data.listThang
						                    }],
		                    yAxis: [
					        	 { // Secondary yAxis
								        title: {
								            text: 'Tổng số',
								            style: {
								                color: '#ed6b75'
								            }
								        },
								        labels: {
								            format: '{value} nhiệm vụ',
								            style: {
								                color: '#ed6b75'
								            }
								        },
								        opposite: true
								    },
					        	{ // Primary yAxis
						        labels: {
						            format: '{value} %',
						            style: {
						                color: Highcharts.getOptions().colors[1]
						            }
						        },
						        allowDecimals: false,
						        min: 0,
						        title: {
						            text: ' Ti lệ',
						            style: {
						                color: Highcharts.getOptions().colors[1]
						            }
						        }
						    }],
						    tooltip: {
						    	 shared: true,
							        crosshair: true,
						   
						        headerFormat: '<b>{point.x}</b><br/>',
						        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
						    },
						  
						    plotOptions: {
						    	column: {
						            stacking: 'normal',
							        dataLabels: {
						                enabled: true,
						                color: '#ed6b75'
						            },
						            enableMouseTracking: false
						        },
						        spline: {
						          
						        dataLabels: {
					                enabled: true
					            },
					            enableMouseTracking: false
						        }
						    
						    },
						    series: [
						    	{
							        name: 'Tổng nhiệm vụ hoàn thành',
							        type: 'column',

							      
							        data: data.listTongNvHoanThanh,
							        tooltip: {
							            valueSuffix: ' nhiệm vụ'
							        },
							        stack:'NV'

							    }, 
							      {
							          name: 'Tổng nhiệm vụ chậm',
							        type: 'column',

							        
							       tooltip: {
							            valueSuffix: ' nhiệm vụ'
							        },
							        data: data.listTongCham,
							        stack:'NV'
							    }, 
							      {
							        name: 'tỉ lệ chậm',
							        type: 'spline',
							        yAxis: 1,
							        data: data.listTile,
							        tooltip: {
							            valueSuffix: '%'}
							    
							        }]
							})
						},
							
					);
			}
			
			
		
			
		 }
		 
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.tblKhQlVcGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.tblKhQlVcGrid.showColumn(column);
			} else {
				vm.tblKhQlVcGrid.hideColumn(column);
			}

		}
		  vm.replaceVAlue=function(value){
			  if(value!=null){
				  value=value.toString();
				  if(value!=null&&value.includes(',')){
					  value= value.replaceAll(',',"");
				  } 
			  }
			  return value;
		  }

	}

})();
