(function() {
	'use strict';
	var controllerId = 'bcChiTietCongNoController';

	angular.module('MetronicApp').controller(controllerId,
			bcChiTietCongNoController);

	function bcChiTietCongNoController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, bcChiTietCongNoService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.CongNoSearch = {};
		// vm.user=Constant.vpsUserToken.fullName;
		initFormData();
		function initFormData() {
			fillDataTable([]);

			if ($rootScope.stringtile) {
				vm.String = $rootScope.stringtile;
			}

		}
		 $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		// $("#grid .k-grid-content").css({
		// "overflow-y" : "scroll"
		// });

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
							template : '<div class=" pull-left ">'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 TkQLK"'
									+ 'ng-click="vm.importFile()" uib-tooltip="Import file" ng-show="RoleMenu.checkRole.checkRoleImport" aria-hidden="true" translate>Import file</button>'
									+ '</div>'
									
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.bcCongNoGrid.columns.slice(1,vm.bcCongNoGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'
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
									$("#congNoCount").text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "TblQltsCongNoVatTuServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.CongNoSearch.page = options.page;
									vm.CongNoSearch.pageSize = options.pageSize;
//									vm.CongNoSearch.type="1";
									vm.oldSearch = angular
											.copy(vm.CongNoSearch);
									return JSON.stringify(vm.CongNoSearch);

								}
							},
							pageSize :10
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
									width : '60px',
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
									title : "Đối tượng nhận nợ",
									field : 'doiTuongNhanNo',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
//								{
//									title : "Mã NV",
//									field : 'maNv',
//									width : '100px',
//									headerAttributes : {
//										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
//									},
//									attributes : {
//										style : "text-align:left;"
//									},
//								},
								{
									title : "Đơn vị",
									field : 'donVi',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Số PXK",

									field : 'soPxk',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Ngày chứng từ",
									field : 'ngayChungTu',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tên vật tư",
									field : 'tenVatTu',
									width : '250px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã vật tư",
									field : 'maVatTu',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã trạm",
									field : 'maTram',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								}

								,
								{
									title : "Hạng mục",
									field : 'hangMuc',
									// template : "# if(status === '1'){ #" +
									// "#=
									// 'Chưa được duyệt' #" + "# } " + "if
									// (status
									// === '2') { # " + "#= 'Đã duyệt và thực
									// hiện'
									// #"+ "#} if(status === '3'){ #" + "#= 'Đã
									// hủy'
									// #" + "# } if(status === '4'){ #" + "#=
									// 'Đã từ
									// chối' #" + "# }#",
									width : '250px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "ĐVT",
									field : 'dvt',
									width : '150px',
									// template : "# if(signState ==='1'){ #" +
									// "#=
									// 'Chưa trình ký' #" + "# } " + "if
									// (signState
									// ==='2') { # " + "#= 'Đã trình ký' #"+ "#}
									// if(signState === '3'){ #" + "#= 'Đã ký'
									// #" +
									// "# } if(signState === '4'){ #" + "#= 'Đã
									// từ
									// chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Giá",
									field : 'gia',
									width : '150px',
									format : '{0:n0}{1}',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Khối lượng xuất kho",
									// field : 'ngayCongCheDo',
									width : '250px',
									columns : [
											{
												title : "Số lượng",
												field : "klxkSoLuong",
												width : '100px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Thành tiền",
												field : "klxkThanhTien",
												width : '150px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											} ],
									// template : "# if(signState ==='1'){ #" +
									// "#=
									// 'Chưa trình ký' #" + "# } " + "if
									// (signState
									// ==='2') { # " + "#= 'Đã trình ký' #"+ "#}
									// if(signState === '3'){ #" + "#= 'Đã ký'
									// #" +
									// "# } if(signState === '4'){ #" + "#= 'Đã
									// từ
									// chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Số nghiệm thu",
									// field : 'ngayCongCheDo',
									width : '250px',
									columns : [
											{
												title : "Số lượng",
												field : "sntSoLuong",
												width : '100px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Thành tiền",
												field : "sntThanhTien",
												width : '150px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											} ],
									// template : "# if(signState ==='1'){ #" +
									// "#=
									// 'Chưa trình ký' #" + "# } " + "if
									// (signState
									// ==='2') { # " + "#= 'Đã trình ký' #"+ "#}
									// if(signState === '3'){ #" + "#= 'Đã ký'
									// #" +
									// "# } if(signState === '4'){ #" + "#= 'Đã
									// từ
									// chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Số hao hụt theo định mức",
									// field : 'ngayCongCheDo',
									width : '250px',
									columns : [
											{
												title : "Số lượng",
												field : "shhtdmSoLuong",
												width : '100px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Thành tiền",
												field : "shhtdmThanhTien",
												width : '150px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											} ],
									// template : "# if(signState ==='1'){ #" +
									// "#=
									// 'Chưa trình ký' #" + "# } " + "if
									// (signState
									// ==='2') { # " + "#= 'Đã trình ký' #"+ "#}
									// if(signState === '3'){ #" + "#= 'Đã ký'
									// #" +
									// "# } if(signState === '4'){ #" + "#= 'Đã
									// từ
									// chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Số đã thu thu hồi",
									// field : 'ngayCongCheDo',
									width : '250px',
									columns : [
											{
												title : "Số lượng",
												field : "sdtthSoLuong",
												width : '100px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Thành tiền",
												field : "sdtthThanhTien",
												width : '150px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											} ],
									// template : "# if(signState ==='1'){ #" +
									// "#=
									// 'Chưa trình ký' #" + "# } " + "if
									// (signState
									// ==='2') { # " + "#= 'Đã trình ký' #"+ "#}
									// if(signState === '3'){ #" + "#= 'Đã ký'
									// #" +
									// "# } if(signState === '4'){ #" + "#= 'Đã
									// từ
									// chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Số mất mát phải bồi thường cho Công ty Công trình",
									// field : 'ngayCongCheDo',
									width : '250px',
									columns : [
											{
												title : "Số lượng",
												field : "smmpbtcctSoLuong",
												width : '100px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Thành tiền",
												field : "smmpbtcctThanhTien",
												width : '150px',
												// aggregates : [ "sum" ],
												format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											} ],

									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Ghi chú",
									field : 'ghiChu',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								} ]
					});
		}
		// import
		// function getFolder() {
		// Restangular.one(RestEndpoint.QLCN_URL+"/folder").get().then(function(data)
		// {
		// vm.folder = data.folder;
		// });
		// }
		// getFolder();

		/*
		 * Thực hiện submit file import excel
		 */
		vm.submit = submit;
		function submit() {
			if ($("#file")[0].files[0] == null) {
				toastr.warning("Bạn chưa chọn file để import");
				return;
			}
			if ($("#file")[0].files[0].name.split('.').pop() != 'xls'
					&& $("#file")[0].files[0].name.split('.').pop() != 'xlsx') {
				toastr.warning("Sai định dạng file");
				return;
			}
			var t0 = performance.now();
			$("#upfile").css("display","none");
			$("#modalLoading").css("display","block");
			var loading = $("#modalLoading");
	         kendo.ui.progress(loading, true);
			var formData = new FormData();
			formData.append('multipartFile', $('#file')[0].files[0]);
			return $
					.ajax({
						url : RestEndpoint.BASE_SERVICE_URL
								+ RestEndpoint.QLCN_URL
								+ "/importCongNo?folder=" + vm.folder,
						type : "POST",
						data : formData,
						enctype : 'multipart/form-data',
						processData : false,
						contentType : false,
						cache : false,
						success : function(data) {
							if (data.fileName) {
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

		// all
		vm.importFile = function() {
			var teamplateUrl = "qll/baoCaoChiTietCongNo/importPopup.html";
			var title = "Import file";
			var windowId = "IMPORT_CONG_NO";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, false, '90%', '25%');
		}

		vm.doSearch = doSearch;
		function doSearch() {
			if (!vm.validator.validate()) {
				return false;
			}
			vm.showDetail = false;
			var grid = vm.bcCongNoGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}

		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.bcCongNoGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.bcCongNoGrid.showColumn(column);
			} else {
				vm.bcCongNoGrid.hideColumn(column);
			}

		}

		// vm.listRemove=[{
		// title: "Thao tác",
		// }

		vm.exportExcelGrid = function() {
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			bcChiTietCongNoService.exportExcelGrid(vm.oldSearch).then(
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
			var fileName="BAOCAOCONGNO";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}
		
		vm.exportTongHop = function() {
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			bcChiTietCongNoService.exportTongHop(vm.oldSearch).then(
					function(result) {
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ data.fileName;
							// return;
						}

					});
			// toastr.success("hello !!!");
		}

	}

})();
