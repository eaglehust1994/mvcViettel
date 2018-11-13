(function() {
	'use strict';
	var controllerId = 'tblKhBcKqCvController';

	angular.module('MetronicApp').controller(controllerId,
			tblKhBcKqCvController);
	function tblKhBcKqCvController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, tblKhBcKqCvService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant){
		
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.khBcKqKdSearch = {};
		vm.BcKqKdPop ={};
		
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
		vm.doSearch = doSearch;
		function doSearch() {	
			vm.showDetail = false;
			var grid = vm.tblKhBcKqKdGrid;
			
			if($("#pkhMaDonVi").val()==""){
				vm.khBcKqKdSearch.tenDonVi=null;
				vm.khBcKqKdSearch.maDonVi=null;
			}
			
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		var record = 0;
		vm.oldSearch = {};
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
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
							+ 'ng-click="vm.importFile()" ng-show="RoleMenu.checkRole.checkPKH" aria-hidden="true" uib-tooltip="Thêm mới kết quả kinh doanh" translate >Thêm mới</button>'
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
							$("#tblKhBcKqVcCount").text(
									"" + response.total);
							vm.count = response.total;
							return response.data;
						},
					},
					transport : {
						read : {
						 // Thuc hien viec goi service
						 url : Constant.BASE_SERVICE_URL+ "tblKhBckqcvRsServiceRest/doSearch",
						 contentType : "application/json;charset=utf-8",
						 type : "POST"
						},
						parameterMap : function(options, type) {

							vm.khBcKqKdSearch.page = options.page;
							vm.khBcKqKdSearch.pageSize = options.pageSize;
							vm.oldSearch = angular.copy(vm.khBcKqKdSearch);
							return JSON.stringify(vm.khBcKqKdSearch);

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
						template : '<div class="text-center #=khBckqcvId#""> '
								+ '		<button style=" border: none; " class="#=khBckqcvId# icon_table" aria-hidden="true"  uib-tooltip="Cập nhật bản ghi " translate>'
								+ '			<i class="fa gandonvi"  ng-click="vm.updateKD(dataItem)" ng-show="RoleMenu.checkRole.checkPKH"   aria-hidden="true"></i> '
								+ '		</button>'
								
					
								+'		<button  class="#=khBckqcvId# icon_table" style=" border: none; " uib-tooltip="Xóa" translate> '+
								'		<i class="fa deletehdCon" ng-click=vm.removeDetail(dataItem) ng-show="RoleMenu.checkRole.checkPKH" aria-hidden="true"></i>'+
								'		</button>'
								+ '</div>',
						width : '120px',
						headerAttributes : {
							style : "text-align:center;font-weight:bold;white-space:normal;"
						}
					},
					{
						title : "Sản lượng",
						field : 'sanLuong',
						width : '120px',
						headerAttributes : {
							style : "text-align:center;white-space:normal;font-weight:bold;"
						},
						attributes : {
							style : "text-align:center;"
						},
					},
					{
						title : "Doanh thu",
						field : 'doanhThu',
						width : '120px',
						headerAttributes : {
							style : "text-align:center;white-space:normal;font-weight:bold;"
						},
						attributes : {
							style : "text-align:center;"
						},
					},
					{
						title : "Hoàn công quyết toán",
						field : 'hcqt',
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
						title : "Thời gian",
						field : 'thoiGian',
						width : '100px',
						headerAttributes : {
							style : "text-align:center;white-space:normal;font-weight:bold;"
						},
						attributes : {
							style : "text-align:center;"
						},
					},
					{
						title : "Loại",
						field : 'loai',
						template: "# if(loai ==='TT'){ #" + "#= 'Thực tế' #" + "# } " + "if (loai ==='KH') { # " + "#= 'Kế hoạch' #"+ "#}#",
						width : '100px',
						headerAttributes : {
							style : "text-align:center;white-space:normal;font-weight:bold;"
						},
						attributes : {
							style : "text-align:center;"
						},
					}			
					]
					
			})
			
			
		}
		//xoa list ban ghi
		 vm.removeAny=function(){
		    	
		    	vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				
			if(vm.khBcKqKdSearch.thoiGian || vm.khBcKqKdSearch.tenDonVi ){
							confirm("Bạn có muốn xóa!", function() {
								tblKhBcKqCvService.deleteListObj(vm.oldSearch).then(function(result){
									if(result.error){
					    				toastr.error(result.error);
					    				return;
					    			}
					    			toastr.success("Xóa hợp đồng thành công!");
					    			  var sizePage = $("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.total();
					    			  var pageSize = $("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.pageSize();
					    					if(sizePage % pageSize === 1){
					    								var currentPage = $("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.page();
					    								if (currentPage > 1) {
					    									$("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.page(currentPage - 1);
					    								}
					    							}
					    			$("#tblKhBcKqKdGrid").data('kendoGrid').dataSource.read();
					    			$("#tblKhBcKqKdGrid").data('kendoGrid').refresh();
					    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
								},function(errResponse){
					                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thẩm định"));
					            });
							})
					
				
			}else{
				toastr.warning(gettextCatalog.getString("Chưa tìm kiếm bản ghi cần xóa"));
			}
				
		    }
	
		// xoa 1 ban ghi
		  vm.removeDetail= function(dataItem){
	        	confirm('Bạn có chắc chắn muốn xóa bản ghi?', function(){
	        		tblKhBcKqCvService.deleteObj(dataItem).then(function(result){
							toastr.success("Xóa bản ghi thành công!");
							vm.doSearch();
					});	
	        	});
				
			}
		  
		  //update ban ghi
			vm.updateKD = function(dataItem){
			  	vm.BcKqKdPop={};
			  	vm.BcKqKdPop=dataItem;
				var teamplateUrl="qll/A_PKH-tblKhBcKqCv/addKhKd.html";
			    var title="Thêm mới Thống kê nhiệm vụ";
			    var windowId="ADD_KH_QLVC";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','80%');
			}
			//ghi lại
			vm.saveKhKd = function(){
				if(!vm.BcKqKdPop.sanLuong){
					vm.BcKqKdPop.sanLuong = 0;
				}
			
				if(!vm.BcKqKdPop.doanhThu){
					vm.BcKqKdPop.doanhThu = 0;
				}
				
				if(!vm.BcKqKdPop.hcqt){
					vm.BcKqKdPop.hcqt = 0;
				}
				if(vm.BcKqKdPop.tenDonVi==null){
					toastr.error("Tên đơn vị không được để trống");
					return;
				}
				tblKhBcKqCvService.updateKhKd(vm.BcKqKdPop).then(function(result){
					if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
					toastr.success("Cập nhật bản ghi thành công!");
					var sizePage = $("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.total();
	  			  var pageSize = $("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.pageSize();
	  					if(sizePage % pageSize === 1){
	  								var currentPage = $("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.page();
	  								if (currentPage > 1) {
	  									$("#tblKhBcKqKdGrid").data("kendoGrid").dataSource.page(currentPage - 1);
	  								}
	  							}
	  			$("#tblKhBcKqKdGrid").data('kendoGrid').dataSource.read();
	  			$("#tblKhBcKqKdGrid").data('kendoGrid').refresh();
	  			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				},function(errResponse){
				                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
				            })
		
			}
		
			
			
		// mở popup tải file excel
		vm.importFile = function() {
			// vm.orderChangeGoodsDetailPop={};
			var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
			var title = "Import bản excel thống kê nhiệm vụ";
			var windowId = "IMPORT_DL_KHTk";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}
		
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
								+ RestEndpoint.TBL_KH_BCKQCV
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
		
		// download biểu mẫu 
		vm.downloadImportTemplate = function(){
			var fileName="KhBcKqKd";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}
		//tự động tháng
		 vm.patternOptionsMonth={
	    			dataTextField: "thang", placeholder:"Nhập Tháng",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.khBcKqKdSearch.thang=dataItem.thang;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhBckqcvRsServiceRest/getForAutoCompleteMonth").post({pageSize:10, page:1, thang:$("#pkhThang").val().trim()}).then(function(response){
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
	    					 vm.khBcKqKdSearch.thang="";
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
	                    vm.khBcKqKdSearch.maDonVi=dataItem.maDonVi;
	                    vm.khBcKqKdSearch.tenDonVi=dataItem.tenDonVi;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhBckqcvRsServiceRest/getForAutoCompleteMaDv").post({pageSize:10, page:1, department:$("#pkhMaDonVi").val().trim()}).then(function(response){
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
	    					 vm.khBcKqKdSearch.maDonVi="";
	    					 vm.khBcKqKdSearch.tenDonVi="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 vm.patternOptionsMaDvPop={
	    			dataTextField: "tenDonVi", placeholder:"Nhập tên hoặc mã đơn vị",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.BcKqKdPop.maDonVi=dataItem.maDonVi;
	                    vm.BcKqKdPop.tenDonVi=dataItem.tenDonVi;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblKhBckqcvRsServiceRest/getForAutoCompleteMaDv").post({pageSize:10, page:1, department:$("#donViPop").val().trim()}).then(function(response){
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
	                	if(processSearch('donViPop',vm.selectedPrpject)){
	    					 $('#donViPop').val("");
	    					 vm.BcKqKdPop.maDonVi="";
	    					 vm.BcKqKdPop.tenDonVi="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 vm.reportChart = function(){
				
			 CommonService.goTo('BAO_CAO_BIEU_DO_KQCV');

	 }
		 
		 vm.getChartDeptDay = getChartDeptDay;
		 function getChartDeptDay(){
			
			if(!vm.khBcKqKdSearch.tenDonVi){
				toastr.warning("Bạn chưa chọn đơn vị");
				return;
			}
			if(!vm.khBcKqKdSearch.thang){
				vm.khBcKqKdSearch.thang=kendo.toString(new Date((new Date()).getTime()),"MM");
			}
			
			
			tblKhBcKqCvService.getChartDeptDay(vm.khBcKqKdSearch).then(
					
					function(data){
						console.log(data);
						Highcharts.chart('chartKD',{
							
							chart: {
						        type: 'spline'
						    },
						    
						    title: {
						        text: 'Biểu đồ kế hoạch kinh doanh các ngày trong tháng : ' + vm.khBcKqKdSearch.thang + ' của đơn vị :' +vm.khBcKqKdSearch.maDonVi ,
						    },
						    xAxis: [{
						        categories: data.listThoiGian,
						       
						    }],
						    yAxis: {
						        title: {
						            text: 'thông số'
						        }
						    },
						   

						    plotOptions: {
						    	
						        spline: {
						          
						        dataLabels: {
					                enabled: true
					            },
					            enableMouseTracking: false
						        }
						    },

						    series: [{
						        name: 'Sản lượng thực tế',
						        data: data.listSanLuong
						    }, {
						        name: 'Doanh thu thực tế',
						        data: data.listDoanhThu
						    }, {
						        name: 'Hoàn công quyết toán thực tế',
						        data: data.listHcqt
						    }, ]
							
							})
						}
						
				);
		
			
		 }
		
		 
		 vm.getChartMonth = getChartMonth;
		 function getChartMonth(){
			
			if(!vm.khBcKqKdSearch.thang){
				toastr.warning("Bạn chưa chọn tháng");
				return;
			}
			if(!vm.khBcKqKdSearch.chartType){
				toastr.warning("Bạn chưa chọn biểu đồ");
				return;
			}
			tblKhBcKqCvService.getChartMonth(vm.khBcKqKdSearch).then(
					
					function(data){
						if(vm.khBcKqKdSearch.chartType=='SL'){
							Highcharts.chart('chartKD', {
								
								chart: {
							        zoomType: 'xy'
							    },
							    title: {
							        text: 'Biểu đồ Sản lượng  các đơn vị của tháng : ' + vm.khBcKqKdSearch.thang,
							    },
							  
							    xAxis: [{
							        categories: data.listDonVi,
							       
							    }],
							    yAxis: [{ // Primary yAxis
							        labels: {
							            format: '{value} tỉ đồng',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        allowDecimals: false,
							        min: 0,
							        title: {
							            text: 'Thông số',
							            style: {
							                color: '#ed6b75'
							            }
							        }
							    }, { // Secondary yAxis
							        title: {
							            text: 'Tỉ Lệ',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        min:0,
							        labels: {
							            format: '{value} %',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        opposite: true
							    }],
							    tooltip: {
							    	shared: true,
							        crosshair: true,
						   
						        headerFormat: '<b>{point.x}</b><br/>',
						        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							    },
							  
							    plotOptions: {
							       
							    	column: {
										   
								        dataLabels: {
							                enabled: true,
							                color: '#ed6b75'
							            },
							            enableMouseTracking: false
							        },
							        spline: {
							          
							        dataLabels: {
						                enabled: true,
						                color: Highcharts.getOptions().colors[0]
						            },
						            enableMouseTracking: false
							        }
							    
							    },
							    series: [{
							        name: 'Sản lượng thực tế',
							        type: 'column',
							       
							        data: data.listSanLuong,
							  
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    }, 
							    {
							        name: 'Sản lượng Kế hoạch',
							        type: 'column',
							       
							        data: data.listSanLuongKh,
							
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    },{
							        name: 'Tỉ lệ hoàn thành Sản lượng',
							        type: 'spline',
							        data: data.listTileSl,
							        color: "rgb(253,236,109)",
							        yAxis: 1,
							        tooltip: {
							            valueSuffix: ' %'
							        }
							    }
							    
							    
							    ]

							   
							});
						}
						if(vm.khBcKqKdSearch.chartType=='DT'){
							Highcharts.chart('chartKD', {
								
								chart: {
							        zoomType: 'xy'
							    },
							    title: {
							        text: 'Biểu đồ Doanh thu các đơn vị của tháng : ' + vm.khBcKqKdSearch.thang,
							    },
							  
							    xAxis: [{
							        categories: data.listDonVi,
							       
							    }],
							    yAxis: [{ // Primary yAxis
							        labels: {
							            format: '{value} tỉ đồng',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        allowDecimals: false,
							        min: 0,
							        title: {
							            text: 'Thông số',
							            style: {
							                color: '#ed6b75'
							            }
							        }
							    }, { // Secondary yAxis
							        title: {
							            text: 'Tỉ Lệ',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							       min:0,
							        labels: {
							            format: '{value} %',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        opposite: true
							    }],
							    tooltip: {
							    	shared: true,
							        crosshair: true,
						   
						        headerFormat: '<b>{point.x}</b><br/>',
						        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							    },
							  
							    plotOptions: {
							       
							    	column: {
										   
								        dataLabels: {
							                enabled: true,
							                color: '#ed6b75'
							            },
							            enableMouseTracking: false
							        },
							        spline: {
							          
							        dataLabels: {
						                enabled: true,
						                color: Highcharts.getOptions().colors[0]
						            },
						            enableMouseTracking: false
							        }
							    
							    },
							    series: [{
							        name: 'Doanh Thu thực tế',
							        type: 'column',
							       
							        data: data.listDoanhThu,
							  
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    }, 
							    {
							        name: 'Doanh Thu Kế hoạch',
							        type: 'column',
							       
							        data: data.listDoanhThuKh,
							
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    },{
							        name: 'Tỉ lệ hoàn thành Doanh thu',
							        type: 'spline',
							        data: data.listTileDt,
							        color: "rgb(253,236,109)",
							        yAxis: 1,
							        tooltip: {
							            valueSuffix: ' %'
							        }
							    }
							    
							    
							    ]

							   
							});
						}
						if(vm.khBcKqKdSearch.chartType=='HCQT'){
								Highcharts.chart('chartKD', {
								
								chart: {
							        zoomType: 'xy'
							    },
							    title: {
							        text: 'Biểu đồ Hoàn công quyết toán các đơn vị của tháng : ' + vm.khBcKqKdSearch.thang,
							    },
							  
							    xAxis: [{
							        categories: data.listDonVi,
							       
							    }],
							    yAxis: [{ // Primary yAxis
							        labels: {
							            format: '{value} tỉ đồng',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        allowDecimals: false,
							        min: 0,
							        title: {
							            text: 'Thông số',
							            style: {
							                color: '#ed6b75'
							            }
							        }
							    }, { // Secondary yAxis
							        title: {
							            text: 'Tỉ Lệ',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							       min:0,
							        labels: {
							            format: '{value} %',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        opposite: true
							    }],
							    tooltip: {
							    	shared: true,
							        crosshair: true,
						   
						        headerFormat: '<b>{point.x}</b><br/>',
						        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							    },
							  
							    plotOptions: {
							       
							    	column: {
							   
								        dataLabels: {
							                enabled: true,
							                color: '#ed6b75'
							            },
							            enableMouseTracking: false
							        },
							        spline: {
							          
							        dataLabels: {
						                enabled: true,
						                color: Highcharts.getOptions().colors[0]
						            },
						            enableMouseTracking: false
							        }
							    
							    },
							    series: [{
							        name: 'Hoàn công quyết toán thực tế',
							        type: 'column',
							       
							        data: data.listHcqt,
							  
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    }, 
							    {
							        name: 'Hoàn công quyết toán Kế hoạch',
							        type: 'column',
							       
							        data: data.listHcqtKh,
							
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    },{
							        name: 'Tỉ lệ hoàn thành HCQT',
							        type: 'spline',
							        data: data.listTileHcqt,
							        color: "rgb(253,236,109)",
							        yAxis: 1,
							        tooltip: {
							            valueSuffix: ' %'
							        }
							    }
							    
							    
							    ]

							   
							});
						}
					}
						
				);
		
			
		 }
		
		 vm.getChartDeptMonth = getChartDeptMonth;
		 function getChartDeptMonth(){
			
			if(!vm.khBcKqKdSearch.tenDonVi){
				toastr.warning("Bạn chưa chọn đơn vị");
				return;
			}
			if(!vm.khBcKqKdSearch.chartType){
				toastr.warning("Bạn chưa chọn biểu đồ");
				return;
			}
			
			tblKhBcKqCvService.getChartDeptMonth(vm.khBcKqKdSearch).then(
					
					function(data){
						if(vm.khBcKqKdSearch.chartType=='SL'){
							Highcharts.chart('chartKD', {
								
								chart: {
							        zoomType: 'xy'
							    },
							    title: {
							        text: 'Biểu đồ Sản lượng  các đơn vị của tháng : ' + vm.khBcKqKdSearch.tenDonVi,
							    },
							  
							    xAxis: [{
							        categories: data.listThang,
							       
							    }],
							    yAxis: [{ // Primary yAxis
							        labels: {
							            format: '{value} tỉ đồng',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        allowDecimals: false,
							        min: 0,
							        title: {
							            text: 'Thông số',
							            style: {
							                color: '#ed6b75'
							            }
							        }
							    }, { // Secondary yAxis
							        title: {
							            text: 'Tỉ Lệ',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        min:0,
							        labels: {
							            format: '{value} %',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        opposite: true
							    }],
							    tooltip: {
							    	shared: true,
							        crosshair: true,
						   
						        headerFormat: '<b>{point.x}</b><br/>',
						        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							    },
							  
							    plotOptions: {
							       
							    	column: {
							          
								        dataLabels: {
							                enabled: true,
							                color: '#ed6b75'
							            },
							            enableMouseTracking: false
							        },
							        spline: {
							          
							        dataLabels: {
						                enabled: true,
						                color: Highcharts.getOptions().colors[0]
						            },
						            enableMouseTracking: false
							        }
							    
							    },
							    series: [{
							        name: 'Sản lượng thực tế',
							        type: 'column',
							       
							        data: data.listSanLuong,
							  
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    }, 
							    {
							        name: 'Sản lượng Kế hoạch',
							        type: 'column',
							       
							        data: data.listSanLuongKh,
							
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    },{
							        name: 'Tỉ lệ hoàn thành Sản lượng',
							        type: 'spline',
							        data: data.listTileSl,
							        color: "#ed6b75",
							        yAxis: 1,
							        tooltip: {
							            valueSuffix: ' %'
							        }
							    }
							    
							    
							    ]

							   
							});
						}
						if(vm.khBcKqKdSearch.chartType=='DT'){
							Highcharts.chart('chartKD', {
								
								chart: {
							        zoomType: 'xy'
							    },
							    title: {
							        text: 'Biểu đồ Doanh thu các đơn vị của tháng : ' + vm.khBcKqKdSearch.tenDonVi,
							    },
							  
							    xAxis: [{
							        categories: data.listThang,
							       
							    }],
							    yAxis: [{ // Primary yAxis
							        labels: {
							            format: '{value} tỉ đồng',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        allowDecimals: false,
							        min: 0,
							        title: {
							            text: 'Thông số',
							            style: {
							                color: '#ed6b75'
							            }
							        }
							    }, { // Secondary yAxis
							        title: {
							            text: 'Tỉ Lệ',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							       min:0,
							        labels: {
							            format: '{value} %',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        opposite: true
							    }],
							    tooltip: {
							    	shared: true,
							        crosshair: true,
						   
						        headerFormat: '<b>{point.x}</b><br/>',
						        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							    },
							  
							    plotOptions: {
							       
							    	column: {

								        dataLabels: {
							                enabled: true,
							                color: '#ed6b75'
							            },
							            enableMouseTracking: false
							        },
							        spline: {
							          
							        dataLabels: {
						                enabled: true,
						                color: Highcharts.getOptions().colors[0]
						            },
						            enableMouseTracking: false
							        }
							    
							    },
							    series: [{
							        name: 'Doanh Thu thực tế',
							        type: 'column',
							       
							        data: data.listDoanhThu,
							  
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    }, 
							    {
							        name: 'Doanh Thu Kế hoạch',
							        type: 'column',
							       
							        data: data.listDoanhThuKh,
							
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    },{
							        name: 'Tỉ lệ hoàn thành Doanh thu',
							        type: 'spline',
							        data: data.listTileDt,
							        color: "#ed6b75",
							        yAxis: 1,
							        tooltip: {
							            valueSuffix: ' %'
							        }
							    }
							    
							    
							    ]

							   
							});
						}
						if(vm.khBcKqKdSearch.chartType=='HCQT'){
								Highcharts.chart('chartKD', {
								
								chart: {
							        zoomType: 'xy'
							    },
							    title: {
							        text: 'Biểu đồ Hoàn công quyết toán các đơn vị của tháng : ' + vm.khBcKqKdSearch.tenDonVi,
							    },
							  
							    xAxis: [{
							        categories: data.listThang,
							       
							    }],
							    yAxis: [{ // Primary yAxis
							        labels: {
							            format: '{value} tỉ đồng',
							            style: {
							                color: '#ed6b75'
							            }
							        },
							        allowDecimals: false,
							        min: 0,
							        title: {
							            text: 'Thông số',
							            style: {
							                color: '#ed6b75'
							            }
							        }
							    }, { // Secondary yAxis
							        title: {
							            text: 'Tỉ Lệ',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							       min:0,
							        labels: {
							            format: '{value} %',
							            style: {
							                color: Highcharts.getOptions().colors[0]
							            }
							        },
							        opposite: true
							    }],
							    tooltip: {
							    	shared: true,
							        crosshair: true,
						   
						        headerFormat: '<b>{point.x}</b><br/>',
						        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
							    },
							  
							    plotOptions: {
							       
							    	column: {
							            
								        dataLabels: {
							                enabled: true,
							                color: '#ed6b75'
							            },
							            enableMouseTracking: false
							        },
							        spline: {
							          
							        dataLabels: {
						                enabled: true,
						                color: Highcharts.getOptions().colors[0]
						            },
						            enableMouseTracking: false
							        }
							    
							    },
							    series: [{
							        name: 'Hoàn công quyết toán thực tế',
							        type: 'column',
							       
							        data: data.listHcqt,
							  
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    }, 
							    {
							        name: 'Hoàn công quyết toán Kế hoạch',
							        type: 'column',
							       
							        data: data.listHcqtKh,
							
							        tooltip: {
							            valueSuffix: ' tỉ đồng'
							        },
							    	
							    },{
							        name: 'Tỉ lệ hoàn thành HCQT',
							        type: 'spline',
							        data: data.listTileHcqt,
							        color: "#ed6b75",
							        yAxis: 1,
							        tooltip: {
							            valueSuffix: ' %'
							        }
							    }
							    
							    
							    ]

							   
							});
						}
					
						   
					}
						
				);
		
			
		 }
		 
		 
	
	}
	
})();