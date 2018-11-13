(function() {
	'use strict';
	var controllerId = 'dlHatangTramController';

	angular.module('MetronicApp').controller(controllerId,
			dlHatangTramController);

	function dlHatangTramController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, dlHatangTramService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.dlHaTangTramSearch = {};
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
									+ '<label ng-repeat="column in vm.dlHaTangTramGrid.columns.slice(1,vm.dlHaTangTramGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'
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
									$("#changeCount").text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblDlHaTangTramServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.dlHaTangTramSearch.page = options.page;
									vm.dlHaTangTramSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.dlHaTangTramSearch);
									return JSON
											.stringify(vm.dlHaTangTramSearch);

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
								// {
								// title : "<input type='checkbox'
								// id='checkalllistchange'
								// name='gridchkselectall'
								// ng-click='vm.chkSelectAll();'
								// ng-model='vm.chkAll' />",
								// template : "<input type='checkbox'
								// id='childcheck' name='gridcheckbox'
								// ng-click='vm.handleCheck(dataItem)'
								// ng-model='dataItem.selected'/>",
								// width : '3%',
								// headerAttributes : {
								// style : "text-align:center;"
								// },
								// attributes : {
								// style : "text-align:center;"
								// },
								// },
								{
									title : "TT",
									field : "stt",
									width : '8%',
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
									template : '<div class="text-center #=tblDlHaTangTramId#""> '
											+

											'		<button style=" border: none; " class="#=tblDlHaTangTramId# icon_table"   uib-tooltip="Cập nhật" translate>'
											+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
											+ '		</button>'
											+

											'		<button style=" border: none; " class="#=tblDlHaTangTramId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
											+

											'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
											+ '		</button>' + '</div>',
									width : '15%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									}
								},
								{
									title : "Mã Nhân Viên",
									field : 'maNv',
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
									width : '5%',
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
									width : '5%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Huyện",
									field : 'huyen',
									width : '8%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Mã trạm",
									field : 'maTram',
									width : '8%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Loại địa hình",
									field : 'loaiDiaHinh',
									width : '15%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								}

								,
								{
									title : "Phân loại trạm",
									field : 'phanLoaiTram',
									// template : "# if(status === '1'){ #" +
									// "#= 'Chưa được duyệt' #" + "# } " + "if
									// (status === '2') { # " + "#= 'Đã duyệt và
									// thực hiện' #"+ "#} if(status === '3'){ #"
									// + "#= 'Đã hủy' #" + "# } if(status ===
									// '4'){ #" + "#= 'Đã từ chối' #" + "# }#",
									width : '8%',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Vùng",
									field : 'vung',
									width : '8%',
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
									title : "Ngày công tính lương",
									field : 'ngayCongTinhLuong',
									width : '7%',
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
									title : "Ngày công chế dộ",
									field : 'ngayCongCheDo',
									width : '7%',
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
								}, ]
					});
		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("dateDLHaTangTram").value;
		    var d = new Date(document.getElementById("dateDLHaTangTram").value);
		    vm.dlHaTangTramSearch.exThang =1 + d.getMonth();
		    vm.dlHaTangTramSearch.exNam = d.getFullYear();
		}
		vm.doSearch = doSearch;
		function doSearch() {
			vm.myFunction();
			if($("#dlHaTangTramAuto").val()===""||$("#dlHaTangTramAuto").val()==null){
				vm.dlHaTangTramSearch.donVi=null;
                vm.dlHaTangTramSearch.tinh=null;
			}
			if($("#dlHaTangTramAuto1").val()===""||$("#dlHaTangTramAuto1").val()==null){
				vm.dlHaTangTramSearch.donVi=null;
                vm.dlHaTangTramSearch.huyen=null;
			}
			vm.showDetail = false;
			var grid = vm.dlHaTangTramGrid;

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
			var title = "Import dữ liệu hạ tầng trạm";
			var windowId = "DL_HANG_TANG";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}
		
		vm.checkNullTinh= function(){
			if(vm.dlHaTangTramSearch.donVi==null||vm.dlHaTangTramSearch.donVi==""){
				toastr.warning("Cần nhập tỉnh trước khi chọn huyện!!!");
				$("#dlHaTangTramAuto").focus();
				return;
			}
		}
		vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập tên tỉnh",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.dlHaTangTramSearch.donVi=dataItem.maDanhMuc;
                    vm.dlHaTangTramSearch.tinh=dataItem.tenDanhMuc;
                    vm.dlHaTangTramSearch.maTinh=dataItem.maDanhMuc;
//                    $('#dlHaTangTramAuto').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#dlHaTangTramAuto").val().trim()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto">Tên tỉnh</p>' +
                	'</div>',
                template:'<div class="row" ><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('dlHaTangTramAuto',vm.selectedPrpject)){
    					 $('#dlHaTangTramAuto').val("");
    					 vm.dlHaTangTramSearch.tinh="";
    					 vm.dlHaTangTramSearch.maDanhMuc="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
		
		var checkname=[];
		vm.patternOptions1={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập tên huyện",
                select: function(e) {
                	  var dataItem = this.dataItem(e.item.index());
                      vm.dlHaTangTramSearch.donVi=dataItem.maDanhMuc;
                      vm.dlHaTangTramSearch.huyen=dataItem.tenDanhMuc
//                      $('#dlHaTangTramAuto1').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete2").post({pageSize:10, page:1, name:$("#dlHaTangTramAuto1").val().trim(),maDanhMuc:vm.dlHaTangTramSearch.donVi}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã huyện</p>' +
                '<p class="col-md-6 text-header-auto">Tên huyện</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                 change: function(e) {
                	if(processSearch('dlHaTangTramAuto1',vm.selectedPrpject)){
   					 $('#dlHaTangTramAuto1').val("");
   					 vm.dlHaTangTramSearch.huyen="";
   					 vm.dlHaTangTramSearch.maDanhMuc="";
   					  vm.selectedPrpject=false;	
   					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};

		
		
		
		
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
								+ RestEndpoint.DLHTT_URL
								+ "/importDLHaTangtram?folder=" + vm.folder,
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
			dlHatangTramService.exportExcelGrid(vm.oldSearch).then(
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

			dlHatangTramService.downloadImportTemplate().then(function(result){
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
				vm.dlHaTangTramGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.dlHaTangTramGrid.showColumn(column);
			} else {
				vm.dlHaTangTramGrid.hideColumn(column);
			}

		}

	}

})();
