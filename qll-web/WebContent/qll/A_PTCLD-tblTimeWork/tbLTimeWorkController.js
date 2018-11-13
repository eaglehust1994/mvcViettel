(function() {
	'use strict';
	var controllerId = 'tbLTimeWorkController';

	angular.module('MetronicApp').controller(controllerId,
			tbLTimeWorkController);

	function tbLTimeWorkController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, tblTimeWorkService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.tblTimeWorkSearch = {};
		vm.KhQlVcPop ={};
		vm.tblKhQlVcReport ={}
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
		vm.roleememe=$rootScope.RoleMenu.checkRole.checkPTCLD;
		
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
									+ '<button class="btn btn-qlk padding-search-right addQLK margin_right10" style="width: auto;"'
									+ 'ng-click="vm.importFile()"  aria-hidden="true"  translate >Import Lịch sử</button>'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.exportTimeLate()"  aria-hidden="true" uib-tooltip="Báo cáo thời gian Nhân viên " aria-hidden="true" translate>Báo cáo </button>'
									 +'</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<button class="btn btn-qlk padding-search-right deletehd"  aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.tblTimeWorkGrid.columns.slice(1,vm.tblTimeWorkGrid.columns.length)">'
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
									$("#tblTimeWorkCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "tblTimeWorkRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {

									vm.tblTimeWorkSearch.page = options.page;
									vm.tblTimeWorkSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.tblTimeWorkSearch);
									return JSON.stringify(vm.tblTimeWorkSearch);

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
									title : "Tên/mã nhân viên",
									field : "name",
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								{
									title : "Giờ vào",
									field : 'timeIn',
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Trạng thái vào",

									field : 'statusIn',
									width : '120px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Giờ ra",

									field : 'timeOut',
									width : '120px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Trạng thái ra",

									field : 'statusOut',
									width : '120px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
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
			var grid = vm.tblTimeWorkGrid;
			if($("#name").val()==""){
				vm.tblTimeWorkSearch.name=null;
			}
			if($("#statusIn").val()==""){
				vm.tblTimeWorkSearch.statusIn=null;
			}
			if($("#statusOut").val()==""){
				vm.tblTimeWorkSearch.statusOut=null;
				
			}
			
		
			
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

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
			var title = "Import bản excel lịch sử ra/vào đơn vị";
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
								+ RestEndpoint.TBL_TIME_WORK
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
		
		vm.exportTimeLate = function(){
			vm.oldSearch.page=null;
			vm.oldSearch.pageSize=null;
			tblTimeWorkService.exportTimeLate(vm.oldSearch).then(function(result){
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
				vm.tblTimeWorkGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.tblTimeWorkGrid.showColumn(column);
			} else {
				vm.tblTimeWorkGrid.hideColumn(column);
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
