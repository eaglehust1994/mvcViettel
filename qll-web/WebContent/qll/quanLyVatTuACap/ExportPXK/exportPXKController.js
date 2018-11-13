(function() {
	'use strict';
	var controllerId = 'exportPXKController';

	angular.module('MetronicApp').controller(controllerId,
			exportPXKController);

	function exportPXKController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, exportPXKService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.exportPXKSearch = {};

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
		 $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
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
									+ '<button class="btn btn-qlk padding-search-right TkQLK margin_right10"'
									+ 'ng-click="vm.Import()" uib-tooltip="Import" ng-show="RoleMenu.checkRole.checkRoleImport" aria-hidden="true" translate>Import</button>'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC"'  
									+ 'ng-click="vm.exportExcelGrid()" uib-tooltip="Xuất báo cáo thi tiết"  aria-hidden="true" translate>Xuất báo cáo thi tiết</button>'
									+ '</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.exportPXKGrid.columns.slice(1,vm.exportPXKGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'
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
									$("#exportPXKCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "tblTypeAPxkServiceRest/doSearchPXK",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {

									vm.exportPXKSearch.page = options.page;
									vm.exportPXKSearch.pageSize = options.pageSize;
									vm.oldSearch = angular
											.copy(vm.exportPXKSearch);
									return JSON.stringify(vm.exportPXKSearch);

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
							pageSizes :   [ 10, 15, 20, 25 ],
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
									template : '<div class="text-center #=tblTypeAPxkId#""> '
// +
//
// ' <button style=" border: none; " class="#=tblQuaTrinhTlId# icon_table"
// uib-tooltip="Cập nhật" translate>'
// + ' <i class="fa fa-pencil" aria-hidden="true"></i> '
// + ' </button>'
											+

											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
											+

											'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
											+ '		</button>' + '</div>',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;"
									}
								},
								{
									title : "Mã phiếu xuất",

									field : 'maPhieuXuat',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã lệnh xuất",
									field : 'maLenhXuat',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},

								},
								{
									title : "Mã yêu cầu xuất",
									field : 'maYcXuat',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Người tạo yêu cầu",
									field : 'nguoiTaoYc',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Người phê duyệt",
									field : 'nguoiPheDuyet',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Ngày tạo",
									field : 'ngayTao',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Ngày thực xuất",
									field : 'ngayThucXuat',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Kho xuất",
									field : 'khoXuat',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Lý do xuất",
									field : 'lyDoXuat',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Kho nhận",
									field : 'khoNhan',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Đơn vị nhận",
									field : 'donViNhan',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Công trình nhận",
									field : 'congTrinhNhan',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Mã trạm nhận",
									field : 'maTramNhan',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tình trạng",
									field : 'tinhTrang',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Lý do từ chối",
									field : 'lyDoTuChoi',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Mã HĐXL",
									field : 'maHdxl',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Tình trạng ký CA",
									field : 'tinhTrangKyCa',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},]

					});
		}
		
		
		
		
		
		vm.openDepartment = function openDepartment(){
			var obj={};
			// obj.page=1;
			// obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				if(result.error){
    				toastr.error(result.error);
    				return;
    			}
				var templateUrl = 'qll/Common/findDepartmentPopUp.html';
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
					}, {
						title: "Tên phòng ban",
						field: "text",
						width: '23%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					}, {
						title: "Đơn vị cha",
						field: "parentName",
						width: '20%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					},{
						title: "Ngày hiệu lực",
						field: "effectDate",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
					},{
						title: "Ngày hết hiệu lực",
						field: "endDate",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
					},{
								title: "Chọn",
								 template:
							        	'<div class="text-center "> '	+
						        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
						        	'			<i id="#=departmentId#" ng-click=onSave(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
						        	'		</a>'
										+'</div>',  
						        width: '7%',
						        field:"stt"
					}
					]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '40%','80%');
			});
		}
        
        
        
        vm.selectedPrpject=false;
		vm.changeDataAuto=changeDataAuto;
		function changeDataAuto(id){
		switch(id){
			case 'exportPXKAuto':{
			if(processSearch(id,vm.selectedPrpject)){
				vm.exportPXKSearch.donVi = null;
				vm.exportPXKSearch.tenDanhMuc =null;
				vm.selectedPrpject=false;	
			 }
				break;
			}
		 }
		
		}
        
        vm.onSave=onSave;
		function onSave(data){
			vm.exportPXKSearch.donVi=data.maDanhMuc;
			vm.exportPXKSearch.tenDanhMuc=data.tenDanhMuc;
			
// $('#importPXKAuto').focus();
		}
        
        vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã đơn vị hoặc tên đơn vị",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.exportPXKSearch.donVi=dataItem.maDanhMuc;
                    vm.exportPXKSearch.tenDanhMuc=dataItem.tenDanhMuc;
// $('#importPXKAuto').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete").post({pageSize:10, page:1, name:$("#exportPXKAuto").val().trim()}).then(function(response){
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
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('exportPXKAuto',vm.selectedPrpject)){
    					 $('#exportPXKAuto').val("");
    					 vm.exportPXKSearch.tenDanhMuc="";
    					 vm.exportPXKSearch.donVi="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};

		vm.doSearch = doSearch;
		function doSearch() {
			if($('#exportPXKAuto').val()===""||$('#exportPXKAuto').val()==null){
				 vm.exportPXKSearch.tenDanhMuc=null;
				 vm.exportPXKSearch.maDanhMuc=null;
	        }
			vm.showDetail = false;
			var grid = vm.exportPXKGrid;

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
			var title = "Import dữ liệu PXK";
			var windowId = "IMPORT_DL_PXK";
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
			
			$("#upfile").css("display","none");
			$("#modalLoading").css("display","block");
			var loading = $("#modalLoading");
	         kendo.ui.progress(loading, true);
	         var t0 = performance.now();
			
			var formData = new FormData();
			formData.append('multipartFile', $('#fileChange')[0].files[0]);
			return $
					.ajax({
						url : RestEndpoint.BASE_SERVICE_URL
								+ RestEndpoint.TBL_TYPE_PXK
								+ "/importTypePXK?folder=" + vm.folder,
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

		// exportExcelGrid

		vm.exportExcelGrid = function() {
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			exportPXKService.exportExcelGrid(vm.oldSearch).then(
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

		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.exportPXKGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.exportPXKGrid.showColumn(column);
			} else {
				vm.exportPXKGrid.hideColumn(column);
			}

		}

	}

})();
