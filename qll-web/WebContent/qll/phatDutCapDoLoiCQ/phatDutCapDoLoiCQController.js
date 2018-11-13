(function() {
	'use strict';
	var controllerId = 'phatDutCapSearchController';

	angular.module('MetronicApp').controller(controllerId,
			phatDutCapSearchController);

	function phatDutCapSearchController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, dutCapService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.PhatDutCapSearch = {};

		setTimeout(function() {
			$("#changeId").focus();
		}, 15);

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
						scrollable : false,
						sortable : false,
						dataBinding : function() {
							record = (this.dataSource.page() - 1)
									* this.dataSource.pageSize();
						},
						toolbar : [ {
							name : "actions",
							template : '<div class=" pull-left ">'
									+ '<button class="btn btn-qlk padding-search-right TkQLK"'
									+ 'ng-click="vm.Import()" uib-tooltip="Import" translate>Import</button>'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK"'
									+ 'ng-click="vm.exportExcelGrid()" uib-tooltip="Xuất Excell" translate>Xuất Excell</button>'
									+ '</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.phatDutCapGrid.columns.slice(1,vm.phatDutCapGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'
									+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
									+ '</label>' + '</div></div>'

						} ],
						dataBound : function(e) {
							// var grid = vm.orderChangeGoodskGrid;
							// grid.tbody.find("tr").dblclick(function (e) {
							// var dataItem = grid.dataItem(this);
							// vm.seeDetail(dataItem)
							// });
						},
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
									$("#dutCapCount").text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblDutCapLoiCqServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.PhatDutCapSearch.page = options.page;
									vm.PhatDutCapSearch.pageSize = options.pageSize;
									vm.oldSearch = angular
											.copy(vm.PhatDutCapSearch);
									return JSON.stringify(vm.PhatDutCapSearch);

								}
							},
							pageSize : 10
						},
						// dataSource: response,
						noRecords : true,
						columnMenu : false,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả hiển thị")
						},
						pageable : {
							refresh : false,
							pageSizes : [ 10, 15, 20, 25 ],
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
									width : '10%',
									columnMenu : false,
									template : function() {
										return ++record;
									},
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Thao tác",
									template : '<div class="text-center #=tblDutCapLoiCqId#""> '
											+

											'		<button style=" border: none; " class="#=tblDutCapLoiCqId# icon_table"   uib-tooltip="Cập nhật" translate>'
											+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
											+ '		</button>'
											+

											'		<button style=" border: none; " class="#=tblDutCapLoiCqId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
											+

											'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
											+ '		</button>' + '</div>',
									width : '15%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									}
								},

								{
									title : "Tháng",
									field : 'thang',
									width : '10%',
									// template : "# if(signState ==='1'){ #" +
									// "#= 'Chưa trình ký' #" + "# } " + "if
									// (signState ==='2') { # " + "#= 'Đã trình
									// ký' #"+ "#} if(signState === '3'){ #" +
									// "#= 'Đã ký' #" + "# } if(signState ===
									// '4'){ #" + "#= 'Đã từ chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Khu vực",
									field : 'khuVuc',
									width : '10%',
									// template: '<a
									// class="#=tblDlHaTangTramId#"
									// href="javascript:void(0);"
									// ng-click=vm.seeDetail(dataItem)>#=code#</a>',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã tỉnh",

									field : 'maTinh',
									width : '10%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Nhân viên giao khoán",
									field : 'nvNhanGk',
									width : '10%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Số lần đứt",
									field : 'soLanDut',
									width : '10%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Tiền phạt đứt cáp",
									field : 'tienPhat',
									// template : "# if(status === '1'){ #" +
									// "#= 'Chưa được duyệt' #" + "# } " + "if
									// (status === '2') { # " + "#= 'Đã duyệt và
									// thực hiện' #"+ "#} if(status === '3'){ #"
									// + "#= 'Đã hủy' #" + "# } if(status ===
									// '4'){ #" + "#= 'Đã từ chối' #" + "# }#",
									width : '5%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								}, ]

					});
		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("datephatDCCQ").value;
		    var d = new Date(document.getElementById("datephatDCCQ").value);
		    vm.PhatDutCapSearch.exThang =1 + d.getMonth();
		    vm.PhatDutCapSearch.exNam = d.getFullYear();
		}
		vm.doSearch = doSearch;
		function doSearch() {
			vm.myFunction();
			vm.showDetail = false;
			var grid = vm.phatDutCapGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}

		vm.Import = function Import() {
			// vm.orderChangeGoodsDetailPop={};
			var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
			var title = "Import lỗi đứt cáp";
			var windowId = "DUT_CAP_POPUP";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}

		vm.submit = submit;
		function submit() {
			
			if ($("#fileChange")[0].files[0] == null) {
				toastr.warning("Bạn chưa chọn file để import");
				return;
			}
			if ($("#fileChange")[0].files[0].name.split('.').pop() != 'xls'
					&& $("#fileChange")[0].files[0].name.split('.').pop() != 'xlsx') {
				toastr.warning("Sai định dạng file");
				return;
			}
			
			var t0 = performance.now();
			$("#upfile").css("display","none");
			$("#modalLoading").css("display","block");
			var loading = $("#modalLoading");
	         kendo.ui.progress(loading, true);
			
			var formData = new FormData();
			formData.append('multipartFile', $('#fileChange')[0].files[0]);
			return $
					.ajax({
						url : RestEndpoint.BASE_SERVICE_URL
								+ RestEndpoint.TBL_DUT_CAP
								+ "/importDutCapCQ?folder=" + vm.folder,
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
								toastr
										.error("Có lỗi trong file import, kiểm tra lại file trả về");
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

		vm.exportExcelGrid = function() {
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			dutCapService.exportExcelGrid(vm.oldSearch).then(
					function(result) {
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ data.fileName;
							// return;
						}

					});
		}
		vm.downloadImportTemplate = function(){

			dutCapService.downloadImportTemplate().then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
					// return;
				} 
			});	
			//toastr.success("hello !!!");
		}
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phatDutCapGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatDutCapGrid.showColumn(column);
			} else {
				vm.phatDutCapGrid.hideColumn(column);
			}

		}

	}

})();
