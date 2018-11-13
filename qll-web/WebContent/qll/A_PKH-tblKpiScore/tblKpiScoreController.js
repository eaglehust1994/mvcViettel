(function() {
	'use strict';
	var controllerId = 'tblKpiScoreController';

	angular.module('MetronicApp').controller(controllerId,
			tblKpiScoreController);

	function tblKpiScoreController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, tblKpiScoreService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.genKpiScoreSearch = {};
		vm.detKpiScoreSearch = {};
	
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
		vm.roleememe=$rootScope.RoleMenu.checkRole.checkKpi;
		
		
		setTimeout(function() {
			$("#changeId").focus();
		}, 15);
		
		var currenRow1;
		if(currenRow1!==undefined ){
			$( function() {
				currenRow1.tooltip();
			  } );
		}
		init();
		function init(){
			if(vm.genKpiScoreSearch.thang==null){
				vm.genKpiScoreSearch.thang = new Date();
			}
//			if(vm.detKpiScoreSearch.thang==null){
//				vm.detKpiScoreSearch.thang = new Date();
//			}
		    $scope.monthSelectorOptions = {
		        start: "year",
		        depth: "year"
		    };

		}


		vm.validatorOptions = kendoConfig.get('validatorOptions');

		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			
			
			if ($rootScope.stringtile) {
				vm.String = $rootScope.stringtile;
			}
			fillDataTable([]);
			fillDataDetailTable([]);
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
//									+ '<button class="btn btn-qlk padding-search-right excelQLK margin_right10" style="width: auto;"'
//									+ 'ng-click="vm.importFile()"  aria-hidden="true"  translate >Nhập FileExcel</button>'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.genExportFile()"  aria-hidden="true"   translate>Xuất FileExcel </button>'
									 + '<button class="btn btn-qlk padding-search-right DanhMuc margin_right10" style="width: auto;"'
									+ 'ng-click="vm.DetailKpiScore()" aria-hidden="true" uib-tooltip="Danh sách điểm chi tiết" translate >Chi tiết điểm</button>'
									 +'</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<button class="btn btn-qlk padding-search-right deletehd"  aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.genKpiScoreGrid.columns.slice(1,vm.genKpiScoreGrid.columns.length)">'
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
									$("#genKpiScoreCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.listData;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "service/tblKpiScoreServiceRest/ListAllDepartment",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {
									
									vm.genKpiScoreSearch.createddateFrom= "01/"+kendo.toString(vm.genKpiScoreSearch.thang,'MM/yyyy')+' 00:00:00';
								
									vm.genKpiScoreSearch.page = options.page;
									vm.genKpiScoreSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.genKpiScoreSearch);
									return JSON.stringify(vm.genKpiScoreSearch);

								}
							},
							pageSize :10
						},
						dataBound : function(data) {
							var grid = vm.genKpiScoreGrid;
							
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
									title : "Phòng/Ban",
									field : "departmentname",
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								{
									title : "Điểm cộng",
									width : '180px',
									columns : [
										{
											title: "Điểm đề xuất",
											field: 'scorebonus',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
										{
											title: "Điểm chốt",
											field: 'scorebonusConfirm',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
									],
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Điểm trừ",
									width : '180px',
									columns : [
										{
											title: "Điểm đề xuất",
											field: 'scorepenalty',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
										{
											title: "Điểm chốt",
											field: 'scorepenaltyConfirm',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
									],
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tổng điểm",
									width : '180px',
									columns : [
										{
											title: "Điểm đề xuất",
											field: 'sumScore',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
										{
											title: "Điểm chốt",
											field: 'sumScoreConfirm',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
									],
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								]

					});
		}
		
		
		 vm.DetailKpiScore = function(){
			 console.log('a');
			 CommonService.goTo('DETAIL_KPI_SCORE');
			
		 }
		
		function fillDataDetailTable(data) {
			// if(data.status !==1){
			// $('#icon').prop("disabled",true);
			// }
			
			vm.gridDetailOptions = kendoConfig
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
									+ '<button class="btn btn-qlk padding-search-right addQLK margin_right10" style="width: auto;"'
									+ 'ng-click="vm.decImportFile()"  aria-hidden="true"  translate > Nhập FileExcel </button>'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.decExportFile()"  aria-hidden="true"  translate> Xuất FileExcel </button>'
									 +'</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<button class="btn btn-qlk padding-search-right deletehd"  aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.detKpiScoreGrid.columns.slice(1,vm.detKpiScoreGrid.columns.length)">'
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
									$("#detKpiScoreCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.listData;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "service/tblKpiScoreServiceRest/InfoDetailByDoSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {
									
//									vm.detKpiScoreSearch.createddateFrom= "01/"+kendo.toString(vm.detKpiScoreSearch.thang,'MM/yyyy')+' 00:00:00';
									if(vm.detKpiScoreSearch.checkScore==""){
										vm.detKpiScoreSearch.checkScore=null;
									}
									vm.detKpiScoreSearch.page = options.page;
									vm.detKpiScoreSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.detKpiScoreSearch);
									return JSON.stringify(vm.detKpiScoreSearch);

								}
							},
							pageSize :10
						},
						dataBound : function(data) {
							var grid = vm.detKpiScoreGrid;
							
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
									title : "Phòng/Ban",
									field : "departmentname",
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								
								{
									title : "Điểm ",
									width : '180px',
									columns : [
										{
											title: "Điểm đề xuất",
											field: 'viewScore',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
										{
											title: "Điểm chốt",
											field: 'viewScoreConfirm',
											width: '90px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
											
										},
									],
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Lý do dề xuất",
									field: 'reason',
									width: '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Người đề xuất",
									field: 'fullnamecreated',
									width: '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Phòng/ban đề xuất",
									field: 'departmentnamecreated',
									width: '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Thời gian đề xuất",
									field: 'created_date',
									width: '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Lý do chốt",
									field: 'reasonConfirm',
									width: '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Người chốt",
									field: 'fullnameModified',
									width: '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Phòng/ban chốt",
									field: 'departmentnameModified',
									width: '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title: "Thời gian chốt",
									field: 'modified_date',
									width: '150px',
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
		vm.listAllDepartment = listAllDepartment;
		function listAllDepartment() {	
			vm.showDetail = false;
			var grid = vm.genKpiScoreGrid;
			if($("#generalDept").val()==""){
				vm.genKpiScoreSearch.departmentname=null;
				vm.genKpiScoreSearch.departmentid=null;
			}
			
		
			
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		vm.infoDetailByDoSearch = infoDetailByDoSearch;
		function infoDetailByDoSearch() {	
			vm.showDetail = false;
			var grid = vm.detKpiScoreGrid;
			if($("#detailDept").val()==""){
				vm.detKpiScoreSearch.departmentname=null;
				vm.detKpiScoreSearch.departmentid=null;
			}
			if(vm.detKpiScoreSearch.thang!=null){
				vm.detKpiScoreSearch.createddateFrom= "01/"+kendo.toString(vm.detKpiScoreSearch.thang,'MM/yyyy')+' 00:00:00';
			}
			
			
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
 
		// auto phong ban 
		vm.patternOptionsDetailDept={
    			dataTextField: "departmentname", placeholder:"Nhập tên phòng/ban: ",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.detKpiScoreSearch.departmentname=dataItem.departmentname;
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có
							// bảng đơn
							// vị
                            return Restangular.all("service/tblKpiScoreServiceRest/getAutoDepartmentKPI").post({pageSize:10, page:1, departmentname:$("#detailDept").val().trim()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-12 text-header-auto">Phòng/ban</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.departmentname #</div></div>',
                change: function(e) {
                	if(processSearch('detailDept',vm.selectedPrpject)){
    					 $('#detailDept').val("");
    					 vm.detKpiScoreSearch.departmentname="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
		vm.patternOptionsDetailDeptCreated={
    			dataTextField: "departmentnamecreated", placeholder:"Nhập tên phòng/ban đề xuất: ",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.detKpiScoreSearch.departmentnamecreated=dataItem.departmentnamecreated;
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có
							// bảng đơn
							// vị
                            return Restangular.all("service/tblKpiScoreServiceRest/getAutoDepartmentCreatedKPI").post({pageSize:10, page:1, departmentnamecreated:$("#detailDeptCreated").val().trim()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-12 text-header-auto">Phòng/ban</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.departmentnamecreated #</div></div>',
                change: function(e) {
                	if(processSearch('detailDeptCreated',vm.selectedPrpject)){
    					 $('#detailDeptCreated').val("");
    					 vm.detKpiScoreSearch.departmentnamecreated="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
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
		vm.decImportFile = function() {
			// vm.orderChangeGoodsDetailPop={};
			var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
			var title = "Import bản excel chốt điểm";
			var windowId = "IMPORT_BAN_EXCEL";
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
								+ RestEndpoint.TBL_KPI_SCORE
								+ "/decidedImportFile?folder=" + vm.folder,
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
		
		vm.decExportFile = function(){
			vm.oldSearch.page=null;
			vm.oldSearch.pageSize=null;
			if(vm.detKpiScoreSearch.checkScore){
				vm.oldSearch.checkScore = vm.detKpiScoreSearch.checkScore;
			}
			if(vm.detKpiScoreSearch.departmentname){
				vm.oldSearch.departmentname = vm.detKpiScoreSearch.departmentname;
			}
			if(vm.detKpiScoreSearch.departmentnamecreated){
				vm.oldSearch.departmentnamecreated = vm.detKpiScoreSearch.departmentnamecreated;
			}
			if(vm.detKpiScoreSearch.thang){
				vm.oldSearch.createddateFrom= "01/"+kendo.toString(vm.detKpiScoreSearch.thang,'MM/yyyy')+' 00:00:00';
			}
			
			tblKpiScoreService.decExportFile(vm.oldSearch).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
				
					vm.infoDetailByDoSearch();	
	    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					// return;
				} 

			});	
		}
		vm.genExportFile = function(){
			vm.oldSearch.page=null;
			vm.oldSearch.pageSize=null;
			tblKpiScoreService.genExportFile(vm.oldSearch).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
//vm.doSearch();
	    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					// return;
				} 

			});	
		}
		
		 
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.genKpiScoreGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.genKpiScoreGrid.showColumn(column);
			} else {
				vm.genKpiScoreGrid.hideColumn(column);
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
