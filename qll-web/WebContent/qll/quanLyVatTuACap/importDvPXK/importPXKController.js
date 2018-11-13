(function() {
	'use strict';
	var controllerId = 'importPXKController';

	angular.module('MetronicApp').controller(controllerId,
			importPXKController);

	function importPXKController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, importPXKService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.importPXKSearch = {};
		vm.chiNhanhKV={};
		vm.u62TTKT={};
		vm.pxkNhanVien={};
		vm.updateSLNV111={};
		vm.updateSLNV1112={};
		vm.updateSLNV113={};
		vm.updateSLNV1131={};
		vm.roleememe=$rootScope.RoleMenu.roleCode;
		setTimeout(function() {
			$("#changeId").focus();
		}, 15);
// if(RoleUser.RoleUser==="CN1"){
// checkExportPXK=true;
// break;
// }
		//hiện title bản ghi quá thời gian trên grid
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
									+ '<button class="btn btn-qlk padding-search-right TkQLK margin_right10"'
									+ 'ng-click="vm.Import()" uib-tooltip="Import" translate ng-show="RoleMenu.checkRole.checkRoleImport||RoleMenu.checkRole.checkRoleFullQuyenQLTS" aria-hidden="true">Import</button>'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'  
									+ 'ng-click="vm.exportExcelGrid()" uib-tooltip="Xuất dữ liệu đã import" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC||RoleMenu.checkRole.checkRoleFullQuyenQLTS" aria-hidden="true" translate>Xuất dữ liệu đã import</button>'
									+ '</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.importPXKGrid.columns.slice(1,vm.importPXKGrid.columns.length)">'
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
									$("#importPXKCount").text(
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

									vm.importPXKSearch.page = options.page;
									vm.importPXKSearch.pageSize = options.pageSize;
//									if($rootScope.RoleMenu.checkRole.checkRoleFullQuyenQLTS&&($rootScope.RoleMenu.checkRole.checkRoleNhapLieu||$rootScope.RoleMenu.checkRole.checkRoleGanPXKChoNV)){
//										vm.importPXKSearch.donVi=$rootScope.RoleMenu.lstUser[0].donVi;
//										vm.importPXKSearch.maNv=$rootScope.casUser.employeeCode;
//									}
									vm.oldSearch = angular
											.copy(vm.importPXKSearch);
									return JSON.stringify(vm.importPXKSearch);

								}
							},
							pageSize :10
						},
						dataBound : function(data) {
							 var grid = vm.importPXKGrid;
							 
							 var grid = $("#importPXKGrid").data("kendoGrid");
//							 	grid.hideColumn("tinhTrangKyCa");
//							 	grid.hideColumn("maHdxl");
//								grid.hideColumn("tinhTrang");
//								grid.hideColumn("maTramNhan");
//								grid.hideColumn("congTrinhNhan");
//								grid.hideColumn("donViNhan");
							 	
								var dataGoodFromGrid = $('#importPXKGrid').data("kendoGrid").dataSource.data();
								for(var j = 0; j<dataGoodFromGrid.length;j++){
									if(data._data[j].checkGt==2){
										 currenRow1 = grid.table.find("tr[data-uid='" + data._data[j].uid + "']").addClass("currenRow"+j);
										currenRow1.css({"background":"#d6ff9f"});
										currenRow1.attr("title","3 ngày chưa nhập liệu sau khi gán đơn vị");
									}else if(data._data[j].checkGt==1){
										 currenRow1 = grid.table.find("tr[data-uid='" + data._data[j].uid + "']").addClass("currenRow"+j);
										currenRow1.css({"background":"#f4bffb"});
										currenRow1.attr("title","3 ngày chưa xử lý dữ liệu sau khi gán dữ liệu");
									}
									
									
								}
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
									template : '<div class="text-center #=tblTypeAPxkId#""> '
											+ '		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="(dataItem.ktKvttkt===0||dataItem.ktKvttkt==null)&&RoleMenu.checkRole.checkRoleGanPXK&&dataItem.maChiNhanh==null"  uib-tooltip="Gán chi nhánh 5 KV" translate>'
											+ '			<i class="fa gankhuvuc" style="margin-top: 10px;" ng-click="vm.openChiNhanh(dataItem)"   aria-hidden="true"></i> '
											+ '		</button>'
											+ '		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="(dataItem.ktKvttkt===0||dataItem.ktKvttkt==null)&&RoleMenu.checkRole.checkRoleGanPXK&&dataItem.maChiNhanh!=null"  uib-tooltip="Đã gán chi nhánh 5 KV" translate>'
											+ '			<i class="fa dagankhuvuc" style="margin-top: 10px;" ng-click="vm.openChiNhanh(dataItem)"   aria-hidden="true"></i> '
											+ '		</button>'
											+//
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="(dataItem.ktKvttkt===1||dataItem.ktKvttkt==null)&&RoleMenu.checkRole.checkRoleGanPXK&&dataItem.maTinh==null"  uib-tooltip="Đã gán 62 TTKT" translate>'
											+ '			<i class="fa gandonvi" ng-click=" vm.open62KTTK(dataItem)"    aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="(dataItem.ktKvttkt===1||dataItem.ktKvttkt==null)&&RoleMenu.checkRole.checkRoleGanPXK&&dataItem.maTinh!=null"  uib-tooltip="Đã gán 62 TTKT" translate>'
											+ '			<i class="fa dagandonvi" ng-click=" vm.open62KTTK(dataItem)"    aria-hidden="true"></i> '
											+ '		</button>'
											+//
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleGanPXKChoNV&&(dataItem.maTinh!=null||dataItem.maChiNhanh!=null)&&dataItem.maNv==null"  uib-tooltip="Gán PXK cho nhân viên" translate>'
											+ '			<i class="fa phanchonhanvien" ng-click="vm.updatePXKNV(dataItem)"    aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleGanPXKChoNV&&(dataItem.maTinh!=null||dataItem.maChiNhanh!=null)&&dataItem.maNv!=null"  uib-tooltip="Đã gán PXK cho nhân viên" translate>'
											+ '			<i class="fa daphanchonhanvien" ng-click="vm.updatePXKNV(dataItem)"    aria-hidden="true"></i> '
											+ '		</button>'
											+//
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleNhapLieu&&dataItem.maNv!=null&&dataItem.checkNhapLieu==null"  uib-tooltip="Nhân viên cập nhật số lượng" translate>'
											+ '			<i class="fa capnhatsolieu"  ng-click="vm.updateSLNV(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+//
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleNhapLieu&&dataItem.maNv!=null&&dataItem.checkNhapLieu!=null"  uib-tooltip="Nhân viên đã cập nhật số lượng" translate>'
											+ '			<i class="fa dacapnhatsolieu"  ng-click="vm.updateSLNV(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table"  aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC&&dataItem.maNv!=null&&(dataItem.checkNhapLieu==null||dataItem.checkNhapLieu!=1)"  uib-tooltip="Xác nhận thông tin của nhân viên" translate>'
											+ '			<i class="fa xacnhansolieu"  ng-click="vm.updateSLNV1(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table"  aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC&&dataItem.maNv!=null&&dataItem.checkNhapLieu!=null&&(dataItem.checkNhapLieu==1)"  uib-tooltip="Đã xác nhận thông tin của nhân viên" translate>'
											+ '			<i class="fa daxacnhansolieu"  ng-click="vm.updateSLNV1(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											
											+
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="((dataItem.maNv==casUser.employeeCode)||RoleMenu.checkRole.checkRoleFullQuyenQLTS)&&dataItem.pathImg==null"  uib-tooltip="Tải lên ảnh phiếu xuất" translate>'
											+ '			<i class="fa uploadimg"  ng-click="vm.uploadFile(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="((dataItem.maNv==casUser.employeeCode)||RoleMenu.checkRole.checkRoleFullQuyenQLTS)&&dataItem.pathImg!=null"  uib-tooltip="Đã tải lên ảnh phiếu xuất" translate>'
											+ '			<i class="fa dauploadimg"  ng-click="vm.uploadFile(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+//
											'		<button style=" border: none; " class="#=tblTypeAPxkId# icon_table" aria-hidden="true" ng-show="dataItem.pathImg!=null"  uib-tooltip="Tải về ảnh của phiếu xuất" translate>'
											+ '			<i class="fa downloadimg"  ng-click="vm.downloadFile(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+ '</div>',
									width : '300px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;"
									}
								},
								{
									title : "Mã tỉnh",
									field : 'maTinh',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
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
								},{
									title : "Mã HĐXL",
									field : 'maHdxl',
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
								},{
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
									title : "Tình trạng ký CA",
									field : 'tinhTrangKyCa',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								}]

					});
		}
		vm.testListDataPXK=[];
		var record1;
		function updateSL(data) {
			var dataSource = new kendo.data.DataSource({
                pageSize: 10,
                data: data,
                autoSync: true,    
                schema: {
						 model: {
			                    id: "stt",
			                	fields: {
			                		stt: {type: "number",editable: false},
			                		soPhieuXuat: {editable: false},
			                		ngayXuat : {editable: false},
			                		tenVatTu: {editable: false},
			                		maVatTu : {editable: false},
			                		donViTinh : {editable: false},
			                		maTramNhan:{editable: false},
			                		congTrinhNhan:{editable: false},
			                		soLuongXuat:{editable: false},
			                		maHdxl:{editable: false},
			                		donGia: {editable: false},
			                		slThucTeTC :{type: "number"},
			                		ttThucTeTC : {type: "number"},
			                		slThuHoi:  { type: "number"},
			                		ttThuHoi: { type: "number"},
			                		slTienDen: {type: "number"},
			                		ttTienDen: {type: "number"},
				                	}
							}
                }
            });
			vm.grid1Options = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
				dataSource:dataSource,
				dataBinding: function() {
                    record1 = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				editable: true,
				save: function(data) {
					if(data.values.slThucTeTC===null){
						data.values.slThucTeTC=0;
					}
					if(data.values.slThuHoi===null){
						data.values.slThuHoi=0;
					}
					if(data.values.slTienDen===null){
						data.values.slTienDen=0;
					}
			         if (data.values.slThucTeTC!=undefined) {
			            var test = data.model.set("ttThucTeTC", data.values.slThucTeTC * data.model.donGia);
			        }
			        else if(data.values.slThuHoi!=undefined){
			            var test = data.model.set("ttThuHoi", data.values.slThuHoi * data.model.donGia);
			        }else if(data.values.slTienDen!=undefined){
			            var test = data.model.set("ttTienDen", data.values.slTienDen * data.model.donGia);
			        }
			    },
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					 pageSize : 10,
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
					  return ++record1;
					 },
			        width: '50px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},  
				{
					title: "Tên vật tư",
			        field: "tenVatTu",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Mã vật tư",
					field: "maVatTu",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị tính",
					field: "donViTinh",
			        width: '100px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn giá",
					field: "donGia",
			        width: '100px',
			        format:"{0:n0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng xuất",
					field: "soLuongXuat",
			        width: '100px',
// format:"{0:n2}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Số thực tế thi công",
					columns:[{
						title: "Số lượng",
						field: "slThucTeTC",
						 width: '100px',
						headerAttributes: {
								style: "text-align:center;"
							},
						attributes: {
								style: "text-align:left;"
							},
						},{
						title: "Thành tiền",
						field: "ttThucTeTC",
						width: '100px',
						format:"{0:n0}",
						editor: function(cont, options) {
         		            $("<span>" + options.model.ttThucTeTC + "</span>").appendTo(cont);},
						headerAttributes: {
									style: "text-align:center;"
								},
						attributes: {
									style: "text-align:left;"
								},
							},
						],
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Số đã thu hồi",
					columns:[{
						title: "Số lượng",
						field: "slThuHoi",
						width: '100px',
						headerAttributes: {
								style: "text-align:center;"
							},
						attributes: {
								style: "text-align:left;"
							},
						},{
						title: "Thành tiền",
						field: "ttThuHoi",
						width: '100px',
						format:"{0:n0}",
						editor: function(cont, options) {
         		            $("<span>" + options.model.ttThuHoi + "</span>").appendTo(cont);},
						headerAttributes: {
									style: "text-align:center;"
								},
						attributes: {
									style: "text-align:left;"
								},
							},
						],
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Số lượng đền tiền",
					columns:[{
						title: "Số lượng",
						field: "slTienDen",
						 width: '100px',
						headerAttributes: {
								style: "text-align:center;"
							},
						attributes: {
								style: "text-align:left;"
							},
						},{
						title: "Thành tiền",
						field: "ttTienDen",
						width: '100px',
						format:"{0:n0}",
						editor: function(cont, options) {
         		            $("<span>" + options.model.ttTienDen + "</span>").appendTo(cont);},
						headerAttributes: {
									style: "text-align:center;"
								},
						attributes: {
									style: "text-align:left;"
								},
							},
						],
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				]
			});
			
		}
		
		  vm.openChiNhanh= function(dataItem){
	        	vm.chiNhanhKV=dataItem;
				var teamplateUrl="qll/bcThucXuatTheoKy/popupGanChiNhanh5KV.html";
			    var title="Gán chi nhánh 5 KV";
			    var windowId="GAN_CHI_NHANH";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
			}
		  
		  
		  vm.updateChiNhanh= function(){
			  if(vm.chiNhanhKV.maChiNhanh==null){
				  toastr.error("Bạn cần nhập khu vực");
				  return;
			  }
			  importPXKService.updateChiNhanh(vm.chiNhanhKV).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Cập nhật thành công!");
	    			$("#importPXKGrid").data('kendoGrid').dataSource.read();
	    			$("#importPXKGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		
		  
		  vm.patternOptions11={
	    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã chi nhánh hoặc tên chi nhánh",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.chiNhanhKV.maChiNhanh=dataItem.maDanhMuc;
	                    vm.chiNhanhKV.tenDanhMuc=dataItem.tenDanhMuc;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có bảng
								// đơn
								// vị
	                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#chiNhanhAuto").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-6 text-header-auto border-right-ccc">Mã KV</p>' +
	                '<p class="col-md-6 text-header-auto">Tên KV</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
	                change: function(e) {
	                	if(processSearch('chiNhanhAuto',vm.selectedPrpject)){
	    					 $('#chiNhanhAuto').val("");
	    					 vm.chiNhanhKV.tenDanhMuc="";
	    					 vm.chiNhanhKV.maChiNhanh="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		
		  vm.patternOptions1={
	    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã hoặc tên 62 TTKT",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.u62TTKT.maTinh=dataItem.maDanhMuc;
	                    vm.u62TTKT.tenDanhMuc=dataItem.tenDanhMuc;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có bảng
								// đơn
								// vị
	                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#t62Auto").val().trim()}).then(function(response){
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
	                	if(processSearch('t62Auto',vm.selectedPrpject)){
	    					 $('#t62Auto').val("");
	    					 vm.u62TTKT.tenDanhMuc="";
	    					 vm.u62TTKT.maTinh="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		  
		  
		  
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
			case 'importPXKAuto':{
			if(processSearch(id,vm.selectedPrpject)){
				vm.importPXKSearch.donVi = null;
				vm.importPXKSearch.tenDanhMuc =null;
				vm.selectedPrpject=false;	
			 }
				break;
			}
		 }
		
		}
        
        vm.onSave=onSave;
		function onSave(data){
			vm.importPXKSearch.donVi=data.maDanhMuc;
			vm.importPXKSearch.tenDanhMuc=data.tenDanhMuc;
			
// $('#importPXKAuto').focus();
		}
        
        vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã đơn vị hoặc tên đơn vị",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.importPXKSearch.donVi=dataItem.maDanhMuc;
                    vm.importPXKSearch.tenDanhMuc=dataItem.tenDanhMuc;
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
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete").post({pageSize:10, page:1, name:$("#importPXKAuto").val().trim()}).then(function(response){
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
                	if(processSearch('importPXKAuto',vm.selectedPrpject)){
    					 $('#importPXKAuto').val("");
    					 vm.importPXKSearch.tenDanhMuc="";
    					 vm.importPXKSearch.donVi="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        
        $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
        
        vm.showAdvancedSearch1=false;
        vm.advancedSearch1 =function(){
			vm.showAdvancedSearch1 = !vm.showAdvancedSearch1;
		}
        
        vm.patternOptionsDV={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã đơn vị hoặc tên đơn vị",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                
                    vm.importPXKSearch.maTinh=dataItem.maDanhMuc;
                    vm.importPXKSearch.tenTinh=dataItem.tenDanhMuc;
// $('#kDCLuongCDAuto').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng
							// đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete").post({pageSize:10, page:1, name:$("#donViAuto").val()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã tỉnh</p>' +
                '<p class="col-md-6 text-header-auto">Tên Trạm</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('donViAuto',vm.selectedPrpject)){
    					 $('#donViAuto').val("");
    					 vm.importPXKSearch.tenTinh="";
    					 vm.importPXKSearch.maTinh="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        
        vm.patternOptionsNV={
    			dataTextField: "hoVaTen", placeholder:"Nhập mã hoặc tên nhân viên",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.pxkNhanVien.maNv=dataItem.userCode;
                    vm.pxkNhanVien.hoVaTen=dataItem.fullName;
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng
							// đơn
							// vị
                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVien").post({pageSize:10, page:1, userCode:$("#pxkNhanVienAuto").val(),donVi:donViNhan}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
                '<p class="col-md-6 text-header-auto">Họ và tên</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.userCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
                change: function(e) {
                	if(processSearch('pxkNhanVienAuto',vm.selectedPrpject)){
    					 $('#pxkNhanVienAuto').val("");
    					 vm.pxkNhanVien.hoVaTen="";
    					 vm.pxkNhanVien.maNv="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
	   

		vm.doSearch = doSearch;
		function doSearch() {
			if($('#importPXKAuto').val()===""||$('#importPXKAuto').val()==null){
				 vm.importPXKSearch.tenDanhMuc=null;
				 vm.importPXKSearch.maDanhMuc=null;
	        }
			if($('#donViAuto').val()==null){
				 vm.importPXKSearch.maTinh=null;
	        }
			if(vm.importPXKSearch.ngayTaoFrom===""){
				vm.importPXKSearch.ngayTaoFrom=null;
			}
			if(vm.importPXKSearch.ngayTaoTo===""){
				vm.importPXKSearch.ngayTaoTo=null;
			}
			
			
			
			vm.showDetail = false;
			var grid = vm.importPXKGrid;

			
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
			$("#upfile").css("display","none");
			$("#modalLoading").css("display","block");
			var loading = $("#modalLoading");
	         kendo.ui.progress(loading, true);
			var t0 = performance.now();
			if ($("#fileChange")[0].files[0] == null) {
				toastr.warning("Bạn chưa chọn file để import");
				return;
			}
			if ($("#fileChange")[0].files[0].name.split('.').pop() != 'xls'
					&& $("#fileChange")[0].files[0].name.split('.').pop() != 'xlsx') {
				toastr.warning("Sai định dạng file");
				return;
			}
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
// $(function() {
// $("#loading").kendoWindow({
// title: "Đang xử lý...",
// content: {
// template: "Đang xử lý..."
// },
// width: 300,
// height: 160
// });
// });

		vm.downloadFile = function(dataItem){
// if(dataItem.pathImg==null){
// toastr.error("Phiếu xuất này chưa tải ảnh lên!!!");
// return;
// }
			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.pathImg;	
    	}
		
		 vm.open62KTTK= function(dataItem){
	        	vm.u62TTKT=dataItem;
				var teamplateUrl="qll/bcThucXuatTheoKy/popupGan62TTKT.html";
			    var title="Gán 62 TTKT";
			    var windowId="GAN_62_TTKT";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
			}
		 
		 
		  vm.update62TTKT= function(){
			  if(vm.u62TTKT.maTinh==null||vm.u62TTKT.maTinh==""){
				  toastr.error("Bạn cần nhập Tỉnh");
				  return;
			  }
			  importPXKService.updateChiNhanh(vm.u62TTKT).then(function(result){
				  $rootScope.checkValue="UPDATE_62TTKT";
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Cập nhật thành công!");
	    			$("#importPXKGrid").data('kendoGrid').dataSource.read();
	    			$("#importPXKGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		  var donViNhan;
		
		  vm.updatePXKNV = function(dataItem){
	        	vm.pxkNhanVien=dataItem;
	        	if(vm.pxkNhanVien.maChiNhanh!=null){
	        		donViNhan=vm.pxkNhanVien.maChiNhanh;
	        	}else{
	        		donViNhan=vm.pxkNhanVien.maTinh;
	        	}
	        	
				var teamplateUrl=" qll/bcThucXuatTheoKy/popupPXKChoNV.html";
			    var title="Gán PXK cho nhân viên";
			    var windowId="GAN_PXK_NV";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
			}
		  
		    vm.updatePXKNhanVien= function(){
		    	 if(vm.pxkNhanVien.maNv==null||vm.pxkNhanVien.maNv==""){
					  toastr.error("Bạn cần nhập nhân viên");
					  return;
				  }
		    	importPXKService.updateNhanVien(vm.pxkNhanVien).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Cập nhật thành công!");
	    			$("#importPXKGrid").data('kendoGrid').dataSource.read();
	    			$("#importPXKGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		    
		    vm.updateSLNV = function(dataItem){
	        	 var list=[];
	        	 vm.checkkk=false;
	        	 vm.updateSLNV113={};
	        	 vm.updateSLNV1131={};
	        	
	        	 vm.updateSLNV113=dataItem;
	        	vm.updateSLNV1131.soPhieuXuat=dataItem.maPhieuXuat;
	        	 vm.updateSLNV1131.checkNhapLieu = 0;
		    	importPXKService.doSearchByPXK1(vm.updateSLNV1131).then(function(result){
		    	
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			
	    			updateSL(result.data);
	    			var teamplateUrl="qll/bcThucXuatTheoKy/nhanVienUpdateSl.html";
				    var title="Nhân viên cập nhật số lượng";
				    var windowId="NV_CAP_NHAT_SL";
				    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
// $("#orderChangeGoodskGrid").data('kendoGrid').dataSource.read();
// $("#importPXKGrid").data('kendoGrid').refresh();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
// list.push(testListDataPXK);
	        	
				
			}
		    
			vm.prepareDataforSaving = function(){
				vm.updateSLNV113.lstThucXuat = [];
				var dataGoodFromGrid = $('#updateSlGrid').data("kendoGrid").dataSource.data();
			
				for(var i = 0; i<dataGoodFromGrid.length;i++){
					vm.updateSLNV113.lstThucXuat.push(dataGoodFromGrid[i]);
				}
			}
	        
	        
			 vm.checkkk=false;
	        vm.updateSLNV1 = function(dataItem){
		       	var list=[];
	        	 vm.checkkk=true;
	        	 vm.updateSLNV113={};
	        	 vm.updateSLNV1131={};
	        	 vm.updateSLNV113=dataItem;
	        	vm.updateSLNV1131.soPhieuXuat=dataItem.maPhieuXuat;
	        	 vm.updateSLNV1131.tblTypeAPxkId=dataItem.tblTypeAPxkId;
		    	importPXKService.doSearchByPXK1(vm.updateSLNV1131).then(function(result){
		    	
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			
	    			updateSL(result.data);
	    			var teamplateUrl="qll/bcThucXuatTheoKy/nhanVienUpdateSl.html";
				    var title="Xác nhận thông tin cập nhật số lượng";
				    var windowId="NV_CAP_NHAT_SL";
				    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','90%');
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
			
			}
	        
	        vm.objUpload={};
	        vm.uploadFile = function(dataItem){
				var teamplateUrl="qll/quanLyVatTuACap/importDvPXK/upLoad.html";
			    var title="Tải lên ảnh đại diện";
			    var windowId="UPLOAD_IMAGE";
			    vm.objUpload=dataItem;
			  
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','40%');
			}
		    
		    
		    
		    vm.cancel11=function(){
// $("#importPXKGrid").data('kendoGrid').refresh();
	        	$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	        }
	        
		    vm.nvUpdateSL= function(){
// if(vm.updateSLNV111.ttThucTeTC==null||vm.updateSLNV111.slThucTeTC==null||vm.updateSLNV111.ttTienDen==null
// ||vm.updateSLNV111.slTienDen==null||vm.updateSLNV111.ttThuHoi==null||vm.updateSLNV111.slThuHoi==null){
// toastr.error("Cần nhập đầy đủ thông tin nhân viên cho số lượng!!!");
// return;
// }
		    	
		    	var listRow=[];
		    	var listRow1=[];
		    	var grid = $("#updateSlGrid").data("kendoGrid");
				var dataGoodFromGrid = $('#updateSlGrid').data("kendoGrid").dataSource.data();
				for(var j = 0; j<dataGoodFromGrid.length;j++){
					if(dataGoodFromGrid[j].slThuHoi==null){
						dataGoodFromGrid[j].slThuHoi=0;
						dataGoodFromGrid[j].ttThuHoi=0;
					}
					
					
					if(dataGoodFromGrid[j].slThucTeTC==null){
						dataGoodFromGrid[j].slThucTeTC=0;
						dataGoodFromGrid[j].ttThucTeTC=0;
					}
					
					if(dataGoodFromGrid[j].slTienDen==null){
						dataGoodFromGrid[j].slTienDen=0;
						dataGoodFromGrid[j].ttTienDen=0;
					}
					var currentUid = dataGoodFromGrid[j].uid;
				    var currenRow = grid.table.find("tr[data-uid='" + currentUid + "']");
				    var sum=dataGoodFromGrid[j].slThuHoi+dataGoodFromGrid[j].slThucTeTC+dataGoodFromGrid[j].slTienDen;
				    if(sum!=0){
						if(dataGoodFromGrid[j].soLuongXuat!=(sum)){
						    var curren=$(currenRow).addClass("red"+j);
						    listRow.push(curren);
						}else{
							var curren1=$(currenRow).addClass("red"+j);
							listRow1.push(curren1);
						}
				    }else{
				    	if(dataGoodFromGrid[j].soLuongXuat!=(sum)){
						    var curren=$(currenRow).addClass("red"+j);
						    listRow.push(curren);
						}else{
							var curren1=$(currenRow).addClass("red"+j);
							listRow1.push(curren1);
						}
// var curren=$(currenRow).addClass("red"+j);
// listRow.push(curren1);
				    }
				    
				}
				if(listRow.length>0){
					for(var i=0;i<listRow1.length;i++){
						$(listRow1[i]).css({"background":"white","color":"black"});
					}
					for(var i=0;i<listRow.length;i++){
						$(listRow[i]).css({"background":"chocolate","color":"lightgoldenrodyellow"});
					}
					toastr.error("∑dữ liệu thực xuất luôn = số lượng xuất");
				    return;
				}
		    	vm.prepareDataforSaving();
		    	if(vm.checkkk){
		    		vm.updateSLNV113.checkNhapLieu = 1;
		    	}else{
		    		vm.updateSLNV113.checkNhapLieu = 0;
		    	}
		    	
		    	importPXKService.updateSLByNV(vm.updateSLNV113).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Cập nhật thành công!");
	    			$("#importPXKGrid").data('kendoGrid').dataSource.read();
	    			$("#importPXKGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
	        
	        vm.nvUpdateSL1= function(){
	        	vm.prepareDataforSaving();
	        	for(var i=0;i<vm.updateSLNV113.lstThucXuat.length;i++){
	        		vm.updateSLNV113.lstThucXuat[i].slThucTeTC=null;
		        	vm.updateSLNV113.lstThucXuat[i].ttThucTeTC=null;
		        	vm.updateSLNV113.lstThucXuat[i].slThuHoi=null;
		        	vm.updateSLNV113.lstThucXuat[i].ttThuHoi=null;
		        	vm.updateSLNV113.lstThucXuat[i].slTienDen=null;
		        	vm.updateSLNV113.lstThucXuat[i].ttTienDen=null;
		        	vm.updateSLNV113.lstThucXuat[i].checkNhapLieu=null;
	        	}
	        	importPXKService.updateSLByNV(vm.updateSLNV113).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Từ chối thành công!");
	    			$("#importPXKGrid").data('kendoGrid').dataSource.read();
	    			$("#importPXKGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		    
		    
		    
		    
	        vm.patternOptionsTram={
	    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã trạm nhận",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.importPXKSearch.maTramNhan=dataItem.maTramNhan;
// vm.importPXKSearch.tenDanhMuc=dataItem.tenDanhMuc;
// $('#qlNhanVienAuto').val(dataItem.tenDanhMuc);
// alert( $('#qlNhanVienAuto').val());
	// vm.selectedPrpject=true;
	                   
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                            return Restangular.all("tblTypeAPxkServiceRest/getDeptForAutocomplete").post({pageSize:10, page:1, maTramNhan:$("#maTramNhanAuto").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-12 text-header-auto border-right-ccc">Mã trạm nhận</p>' +
// '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;text-align:center;">#: data.maTramNhan #</div></div>',
	                change: function(e) {
	                	if(processSearch('maTramNhanAuto',vm.selectedPrpject)){
	    					 $('#maTramNhanAuto').val("");
	    					 vm.importPXKSearch.maTramNhan="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		    
		    
		
		// exportExcelGrid

		vm.exportExcelGrid = function() {
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			importPXKService.exportExcelGrid(vm.oldSearch).then(
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
		
		
		
		// Chọn file tải lên trong màn hình thêm mới
		vm.onSelect = function(e) {
				 if($("#files")[0].files[0].name.split('.').pop() !='png' && $("#files")[0].files[0].name.split('.').pop() !='jpg'){
        		toastr.warning("Sai định dạng file");
        		setTimeout(function() {
		              	 $(".k-upload-files.k-reset").find("li").remove();
		   			     $(".k-upload-files").remove();
		   				 $(".k-upload-status").remove();
		   				 $(".k-upload.k-header").addClass("k-upload-empty");
		   				 $(".k-upload-button").removeClass("k-state-focused");
        			},10);
        			return;
				 }
				 var formData = new FormData();
				 jQuery.each(jQuery('#files')[0].files, function(i, file) {
						 formData.append('multipartFile'+i, file);
					});
				
				
			 
	     return   $.ajax({
	            url:Constant.BASE_SERVICE_URL+"fileservice/uploadATTT",
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success:function(data) {
				 $.map(e.files, function(file,index) {
			
				 vm.objUpload.pathImg=data[index];
				
				 vm.upLoadFile=vm.objUpload;

				 
				 })

	            }
	        });
	    }
		
		vm.saveFileUpLoad = function(){
			
			 importPXKService.updatePathImg(vm.upLoadFile).then(
			function(result) {
					if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Tải ảnh lên thành công!");
    			$("#importPXKGrid").data('kendoGrid').dataSource.read();
    			$("#importPXKGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
            });
		}
		
		
		vm.downloadImportTemplate = function(){
			var fileName="template_TypeA_PXK";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}

		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.importPXKGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.importPXKGrid.showColumn(column);
			} else {
				vm.importPXKGrid.hideColumn(column);
			}

		}

	}

})();
