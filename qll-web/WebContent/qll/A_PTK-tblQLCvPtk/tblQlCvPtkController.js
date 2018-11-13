(function(){
	'use strict';
	var controllerId = 'tblQlCvPtkController';

	angular.module('MetronicApp').controller(controllerId,
			tblQlCvPtkController);
	function tblQlCvPtkController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, tblQlCvPtkService, GNVService,tblQlCvFileService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant){
		
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch = false;
		vm.addQlcvPopup={};
		vm.qlCvPtkSearch = {};
		vm.doSearchFile = {};
		vm.exportBC ={};
		vm.exportBCtotal ={}
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
		vm.roleememe=$rootScope.RoleMenu.checkRole.checkTPPTP;

	
		setTimeout(function() {
			$("#changeId").focus();
		}, 15);
		
		var currenRow1;
		if(currenRow1!==undefined ){
			$( function() {
				currenRow1.tooltip();
			  } );
		}
		vm.advancedSearch =function(){
			vm.showAdvancedSearch = !vm.showAdvancedSearch;
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		

		
		initFormData();
		function initFormData() {
			fillDataTable([]);
			
//			fillFileDownLoadTable([]);
//			
		
			if ($rootScope.stringtile) {
				vm.String = $rootScope.stringtile;
			}

		}
		
		var record = 0;
		vm.oldSearch = {};
		function fillDataTable(data){
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
						toolbar : [{
							name: "actions",
							template : '<div class=" pull-left ">'
								+ '<button class="btn btn-qlk padding-search-right addQLK margin_right10"'
								+ 'ng-click="vm.createCvPtk()" ng-show="RoleMenu.checkRole.checkPTK||RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Thêm mới" translate >Thêm mới</button>'
								+ '<button class="btn btn-qlk padding-search-right DanhMuc margin_right10" style="width: auto;"'
								+ 'ng-click="vm.searchGanNV()" ng-show="RoleMenu.checkRole.checkPTK||RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Quản lý nhiệm vụ" translate >Quản lý nhiệm vụ</button>'
								 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
								 + 'ng-click="vm.xuatBCQtkPop()" ng-show="RoleMenu.checkRole.checkPTK||RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Xuất báo cáo theo ngày/tháng" aria-hidden="true" translate>Báo Cáo QTK</button>'
								 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
								 + 'ng-click="vm.xuatBCTotalPop()" ng-show="RoleMenu.checkRole.checkPTK||RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Báo cáo tổng " aria-hidden="true" translate>Báo cáo tổng</button>'
								 + '<button class="btn btn-qlk padding-search-right margin_right10 bieuDo" style="width: auto;"'
								 + 'ng-click="vm.reportChart()"  aria-hidden="true" uib-tooltip="Báo cáo biểu đồ" ng-show="RoleMenu.checkRole.checkPTK||RoleMenu.checkRole.checkTPPTP" aria-hidden="true" translate>Báo cáo biểu đồ</button>'
								 +'</div>'
								+ '<div class="btn-group pull-right margin_top_button margin_right10">'
								+ '<button class="btn btn-qlk padding-search-right deletehd" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
								+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
								+'<i class="action-button excelQLK" uib-tooltip="Xuất tất cả dữ liệu" ng-click="vm.exportExcelGrid1()" aria-hidden="true"></i>'
								+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
								+ '<label ng-repeat="column in vm.qlCvPtkGrid.columns.slice(1,vm.qlCvPtkGrid.columns.length)">'
								+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
								+ '</label>' + '</div></div>'
						}],
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
									$("#qlCvPtkCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "tblQlCvPtkRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {

									vm.qlCvPtkSearch.page = options.page;
									vm.qlCvPtkSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.qlCvPtkSearch);
									return JSON.stringify(vm.qlCvPtkSearch);

								}
							},
							pageSize :10
						},
						dataBound : function(data) {
							var grid = vm.qlCvPtkGrid;
							 
					
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
//						enablePinning: true,
						
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
								template : '<div class="text-center "> '
									+ '		<button style=" border: none; " class="icon_table" aria-hidden="true"  uib-tooltip="Cập nhật bản ghi" translate>'
									+ '			<i class="fa gandonvi"  ng-click="vm.updateCvPtk(dataItem)" ng-show="RoleMenu.checkRole.checkThamDinhDN||RoleMenu.checkRole.checkTPPTP"   aria-hidden="true"></i> '
									+ '		</button>'
									
									+'		<button style=" border: none; " class="icon_table" aria-hidden="true" ng-show="RoleMenu.checkRole.checkTPPTP&&(dataItem.tinh!=null||dataItem.cnkv!=null)"  uib-tooltip="Gán nhiệm vụ cho nhân viên" translate>'
									+ '			<i class="fa phanchonhanvien" ng-click="vm.updateGNV(dataItem)"    aria-hidden="true"></i> '
									+ '		</button>'
									
									+ '		<button style=" border: none; " class="icon_table" aria-hidden="true"   uib-tooltip="Tải file đính kèm lên" translate>'
									+ '			<i class="fa uploadimg"  ng-click="vm.uploadFile(dataItem)"   aria-hidden="true"></i> '
									+ '		</button>'
									
									+'		<button  class="icon_table" style=" border: none; " uib-tooltip="Xóa" translate> '+
									'		<i class="fa deletehdCon" ng-click=vm.removeDetail(dataItem) aria-hidden="true"></i>'+
									'		</button>'
									+ '</div>',
								width : '200px',
								
								headerAttributes : {
									style : "text-align:center;font-weight:bold;white-space:normal;"
								},
								attributes : {
									style : "text-align:center;"
								},
								
								
							},{
								title : "Trạng Thái",
								field : 'trangThai',
								width : '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							
							},
							{
								title : "CNKV",

								field : 'cnkv',
								width : '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
						
							},
							{
								title : "Tinh",

								field : 'tinh',
								width : '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							
							},
							{
								title: "Mã trạm/tuyến",
								width: '600px',
							
								columns : [
									{
										title: "Mã vị trí",
										field: 'mttMaViTri',
										width: '100px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
										
									},
									{
										title: "Mã 2G",
										field: 'mttMa2g',
										width: '100px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Mã 3G",
										field: 'mttMa3g',
										width: '100px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Mã xuất kho",
										field: 'mttMaXuatKho',
										width: '100px',
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
								title: "Số hợp đồng chủ đầu tư",
								field: 'soHdCdt',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Loại công trình",
								field: 'loaiCt',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Nội dung(hạng mục)",
								field: 'noiDung',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Ngày gửi HsHC lên công ty",
								field: 'ngayGuiHshc',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Số bill",
								field: 'soBill',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Ngày trạng thái",
								field: 'ngayNhanHshc',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Ghi chú",
								field: 'ghiChu',
								width: '150px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Giá trị Theo sản lượng hạ tầng tạm tính",
								width: '900px',
								columns : [
									{
										title: "Tổng",
										field: 'gtSlHtTtTong',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Xây dựng",
										field: 'gtSlHtTtXd',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Điện",
										field: 'gtSlHtTtDien',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp dựng",
										field: 'gtSlHtTtLapDung',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp BTS",
										field: 'gtSlHtTtLapBts',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Khác",
										field: 'gtSlHtTtKhac',
										width: '150px',
										format:"{0:n2}",
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
								title: "Giá trị đề nghị QTK của CN",
								width: '1200px',
								columns :[
									{
										title: "Ngày tháng năm",
										field: 'gtDnQtkCnNgay',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Tổng",
										field: 'gtDnQtkCnTong',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Xây dựng",
										field: 'gtDnQtkCnXd',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Điện",
										field: 'gtDnQtkCnDien',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp dựng",
										field: 'gtDnQtkCnLapDung',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp BTS",
										field: 'gtDnQtkCnLapBts',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Khác",
										field: 'gtDnQtkCnKhac',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Người lập",
										field: 'gtDnQtkCnNguoiLap',
										width: '150px',
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
								title: "Giá trị QTK chốt PTK",
								width: '1500px',
								columns:[
									{
										title: "Ngày tháng năm",
										field: 'gtQtkPtkNgay',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Tổng",
										field: 'gtQtkPtkTong',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Xây dựng",
										field: 'gtQtkPtkXd',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Điện",
										field: 'gtQtkPtkDien',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp dựng",
										field: 'gtQtkPtkLapDung',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp BTS",
										field: 'gtQtkPtkLapBts',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Khác",
										field: 'gtQtkPtkKhac',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Người chốt",
										field: 'gtQtkPtkNguoiChot',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Thuộc định mức tờ trình",
										field: 'gtQtkPtkDmtt',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Tháng QTK",
										field: 'gtQtkPtkThangQtk',
										width: '150px',
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
								title: "Tình trạng chuyển chứng từ",
								field: 'tinhTrangCct1',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Ghi chú hồ sơ lỗi",
								field: 'ghiChuHsLoi',
								width: '200px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Quyết toán đối tác",
								width: '1800px',
								columns: [
									{
										title: "Ngày/tháng/năm đối tác gửi",
										field: 'qtDtNgay',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Giá trị đề nghị của đối tác",
										field: 'qtDtGtDnDt',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Số hợp đồng của đối tác",
										field: 'qtDtSoHd',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Ngày/tháng/năm Thẩm đối tác",
										field: 'qtDtNgayTdDt',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Tổng",
										field: 'qtDtTong',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Xây dựng",
										field: 'qtDtXd',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Điện",
										field: 'qtDtDien',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp dựng",
										field: 'qtDtLapDung',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Lắp BTS",
										field: 'qtDtLapBts',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Khác",
										field: 'qtDtKhac',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Thông tin đối tác",
										field: 'qtDtTtDt',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Người Thẩm",
										field: 'qtDtNguoiTd',
										width: '150px',
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
								title: "Đề nghị quyết toán CDT",
								width: '900px',
								columns: [
									{
										title: "Ngày/tháng/năm",
										field: 'dnQtCdtNgay',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Giá trị",
										field: 'dnQtCdtGt',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Người lập",
										field: 'dnQtCdtNguoiLap',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Người nhận bàn giao",
										field: 'dnQtCdtNguoiNhanBg',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Nội dung PS có trong thiết kế",
										field: 'dnQtCdtNoiDungPsCtk',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Nội dung PS không có trong thiết kế",
										field: 'dnQtCdtKtk',
										width: '150px',
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
								title: "Thẩm định quyết toán CDT",
								width: '1050px',
								columns: [
									{
										title: "Ngày/tháng/năm chốt",
										field: 'tdQtCdtNgayChot',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Giá trị",
										field: 'tdQtCdtGt',
										width: '150px',
										format:"{0:n2}",
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Người chốt mail Viettel",
										field: 'tdQtCdtNguoiChotMvt',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Người thẩm CDT mail Viettel",
										field: 'tdQtCdtNguoiTdCdtMvt',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Ngày/tháng/năm bản thẩm về",
										field: 'tdQtCdtNgayBanTd',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Ngày chuyển tài chính",
										field: 'tdQtCdtNgayCtc',
										width: '150px',
										headerAttributes : {
											style : "text-align:center;white-space:normal;font-weight:bold;"
										},
										attributes : {
											style : "text-align:center;"
										},
									},
									{
										title: "Nội dung",
										field: 'tdQtCdtNoiDung',
										width: '150px',
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
								title: "Tình trạng chuyển chứng từ",
								field: 'tinhTrangCct2',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title: "Ghi chú",
								field: 'ghiChu2',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},{
								title: "Lưu tại kho",
								field: 'luuTaiKho',
								width: '100px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
						]
						
			})	
		}
		
		//Tìm kiếm 
		
		vm.doSearch = doSearch;
		function doSearch() {	
			vm.showDetail = false;
			var grid = vm.qlCvPtkGrid;
		
			if($("#qlcvTinhAuto").val()==""){
				
				vm.qlCvPtkSearch.tinh=null;
			}
			if($("#chiNhanhCvAuto").val()==""){
			
				vm.qlCvPtkSearch.cnkv=null;
			}
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.qlCvPtkGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.qlCvPtkGrid.showColumn(column);
			} else {
				vm.qlCvPtkGrid.hideColumn(column);
			}

		}
		
		
		// thêm mới 
		
		 vm.createCvPtk = function(){
			  	vm.addQlcvPopup={};
			  	vm.addQlcvPopup.ngayTuongUngTtcut=kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
			  	vm.addQlcvPopup.nguoiDuyetPtk=$rootScope.casUser.fullName;
				var teamplateUrl="qll/A_PTK-tblQLCvPtk/addQlcvPopup.html";
			    var title="Thêm mới quản lý công việc";
			    var windowId="ADD_QL_CV";
			
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		 //ghi khi thêm mới
		 vm.save = function(){
			 if(vm.addQlcvPopup.tblQlCvPtkId==null){
				 vm.addQlcvPopup.trangThai="Mới tạo "
				 tblQlCvPtkService.saveAddCv(vm.addQlcvPopup).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			vm.addQlcvPopup={};
		    			toastr.success("Thêm  công việc quản lý thành công!");
		    			$("#qlCvPtkGrid").data('kendoGrid').dataSource.read();
		    			$("#qlCvPtkGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		    		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thêm mới"));
		            });
			 }else{
				 tblQlCvPtkService.updateCvPtk(vm.addQlcvPopup).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			debugger;
		    			vm.addQlcvPopup={};
		    			toastr.success("Cập nhật thành công!");
		    			  var sizePage = $("#qlCvPtkGrid").data("kendoGrid").dataSource.total();
		    			  var pageSize = $("#qlCvPtkGrid").data("kendoGrid").dataSource.pageSize();
		    					if(sizePage % pageSize === 1){
		    								var currentPage = $("#qlCvPtkGrid").data("kendoGrid").dataSource.page();
		    								if (currentPage > 1) {
		    									$("#qlCvPtkGrid").data("kendoGrid").dataSource.page(currentPage - 1);
		    								}
		    							}
		    			$("#qlCvPtkGrid").data('kendoGrid').dataSource.read();
		    			$("#qlCvPtkGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		    		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
		            }
		    		);
				 
			 }
		    	
		 }
		 vm.updateCvPtk = function(dataItem){
			 	vm.addQlcvPopup={};
			  	vm.addQlcvPopup=dataItem;
			
			  	
//			  	vm.addQlcvPopup.nguoiCapNhat=$rootScope.casUser.fullName;
//				vm.addQlcvPopup.nguoiPheDuyetPtk=$rootScope.casUser.fullName;
				var teamplateUrl="qll/A_PTK-tblQLCvPtk/UpdateQlCvPopup.html";
			    var title="Cập nhật quản lý Công việc";
			    var windowId="TD_QL_CV";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		 //xuất tất cả dữ liệu
		  vm.exportExcelGrid1 = function(){
				vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				tblQlCvPtkService.exportAllCV(vm.oldSearch).then(function(result){
					if (result.fileName) {
						
						toastr.success("Xuất file thành công!");
						window.location = Constant.BASE_SERVICE_URL
								+ "fileservice/downloadFileATTT?"
								+ data.fileName;
						debugger;
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
						// return;
					} 

				});	
			}
		//Mở popup xuất báo cáo QTK
		  vm.xuatBCQtkPop = function(){
			  	vm.addHdPop={};
			  	var teamplateUrl="qll/A_PTK-tblQLCvPtk/reportQtk.html";
			    var title="Xuất báo cáo QTK";
			    var windowId="EX_BC_QTK";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
			}
		 //xuất báo cáo QTK
		  vm.xuatBCQtk = function(){
			  if(vm.exportBC.ngayNhanHshcFrom==null){
				  toastr.warning(gettextCatalog.getString("Bạn cần nhập từ ngày"));
	        		return;
			  }
			  tblQlCvPtkService.reportForQTK(vm.exportBC).then(function(result){
					
//				  console.log(result);
					if (result.fileName) {
						toastr.success("Xuất file thành công!");
						window.location = Constant.BASE_SERVICE_URL
								+ "fileservice/downloadFileATTT?"
								+ data.fileName;

		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					
					} 

				});	
			  
		  }
		//Mở popup xuất báo cáo Tổng
		  vm.xuatBCTotalPop = function(){
			  	vm.addHdPop={};
			  	var teamplateUrl="qll/A_PTK-tblQLCvPtk/reportTotal.html";
			    var title="Xuất báo cáo Tổng";
			    var windowId="EX_BC_TOTAL";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
			}
		  //xuất báo cáo tổng
		  vm.xuatBCTotal = function(){
			 
			  tblQlCvPtkService.reportTotal(vm.exportBC).then(function(result){
				
//				  console.log(result);
					if (result.fileName) {
						toastr.success("Xuất file thành công!");
						window.location = Constant.BASE_SERVICE_URL
								+ "fileservice/downloadFileATTT?"
								+ data.fileName;

		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					
					} 

				});	
		  }
		  
		  
		  
		 //xoa ban ghi
		 vm.removeDetail= function(dataItem){
	        	confirm('Bạn có chắc chắn muốn xóa bản ghi?', function(){
	        		tblQlCvPtkService.deleteObj(dataItem).then(function(result){
							toastr.success("Xóa bản ghi thành công!");
							vm.doSearch();
					});	
	        	});
				
			}
		
		 //get ma chi nhanh khu vuc
		 
		 vm.patternOptionsCnkv={
				 dataTextField: "tenDanhMuc", placeholder:"Nhập mã chi nhánh hoặc tên chi nhánh",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.qlCvPtkSearch.cnkv=dataItem.maDanhMuc;
	                    vm.qlCvPtkSearch.tenDanhMuc=dataItem.tenDanhMuc;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#chiNhanhCvAuto").val().trim()}).then(function(response){
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
	                	if(processSearch('chiNhanhCvAuto',vm.selectedPrpject)){
	    					 $('#chiNhanhCvAuto').val("");
	    					 vm.qlCvPtkSearch.cnkv="";
	    					 vm.qlCvPtkSearch.maChiNhanh="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 
		 vm.patternOptionsCnkvPop={
				 dataTextField: "tenDanhMuc", placeholder:"Nhập mã chi nhánh hoặc tên chi nhánh",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.addQlcvPopup.cnkv=dataItem.maDanhMuc;
	                    vm.addQlcvPopup.tenDanhMuc=dataItem.tenDanhMuc;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1Popup").post({pageSize:10, page:1, namePopup:$("#chiNhanhCvAutoPopup").val().trim()}).then(function(response){
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
	                	if(processSearch('chiNhanhCvAutoPopup',vm.selectedPrpject)){
	    					 $('#chiNhanhCvAutoPopup').val("");
	    					 vm.addQlcvPopup.cnkv="";
	    					 vm.addQlcvPopup.maChiNhanh="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 //get ma tinh
		 vm.patternOptionsTinh={
	    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã tỉnh hoặc tên tỉnh",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                 
	                 vm.qlCvPtkSearch.tinh=dataItem.maDanhMuc;
	                 vm.qlCvPtkSearch.tenTinh=dataItem.tenDanhMuc;
	               
	                   
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#qlcvTinhAuto").val()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-6 text-header-auto border-right-ccc">Mã tỉnh</p>' +
	                '<p class="col-md-6 text-header-auto">Tên tỉnh</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
	                change: function(e) {
	                	if(processSearch('qlcvTinhAuto',vm.selectedPrpject)){
	    					 $('#qlcvTinhAuto').val("");
	    					 vm.qlCvPtkSearch.tenTinh="";
	    					 vm.qlCvPtkSearch.tinh="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 
		 
		 vm.patternOptionsTinhPop={
	    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã tỉnh hoặc tên tỉnh",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.addQlcvPopup.tinh=dataItem.maDanhMuc;
	                    vm.addQlcvPopup.tenTinh=dataItem.tenDanhMuc;
	               
	                   
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1Popup").post({pageSize:10, page:1, namePopup:$("#qlcvTinhAutoPopup").val()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-6 text-header-auto border-right-ccc">Mã tỉnh</p>' +
	                '<p class="col-md-6 text-header-auto">Tên tỉnh</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
	                change: function(e) {
	                	if(processSearch('qlcvTinhAutoPopup',vm.selectedPrpject)){
	    					 $('#qlcvTinhAutoPopup').val("");
	    					 vm.addQlcvPopup.tenTinh="";
	    					 vm.addQlcvPopup.tinh="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 
		 // importExcel
		 
		 vm.importFile = function() {
				// vm.orderChangeGoodsDetailPop={};
				var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
				var title = "Import quản lý công việc PTK";
				var windowId = "IMPORT_QL_CV";
				CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
						windowId, null, '70%', '30%', null);
			}
		 vm.importFileCV = function() {
				// vm.orderChangeGoodsDetailPop={};
				var teamplateUrl = "qll/A_PTK-tblQLCvPtk/UpdateListCVPopup.html";
				var title = "Import cập nhật công việc PTK";
				var windowId = "IMPORT_CN_CV";
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
									+ RestEndpoint.TBL_QL_CV_PTK
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
			vm.submitUpdateCV=submitUpdateCV;
			function submitUpdateCV() {
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
									+ RestEndpoint.TBL_QL_CV_PTK
									+ "/importFileCV?folder=" + vm.folder,
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
			
			vm.downloadImportTemplate = function(){
				var fileName="importQlCvPtk";
				CommonService.downloadTemplate(fileName).then(function(d) {
					data = d.plain();
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				});
			
			}
			vm.checkNv1=false;
			vm.checkNv2=false;
			vm.checkNv3=false;
			vm.checkNv4=false;
			vm.checkNv5=false;
			vm.addQlcvPopup={};
			//Mở popup gán nhiệm vụ
			 vm.updateGNV= function(dataItem){
				 vm.setIdQlCvPtk={};
				 	vm.showGanNV= true;
				 	vm.getTblQlCvPtk=dataItem;
				  	vm.setIdQlCvPtk.idQlCvPtk=dataItem.tblQlCvPtkId;
				  	
				  	
				  	 GNVService.doSearch(vm.setIdQlCvPtk).then(function(result){
				  		 vm.getTblGanNv=result.data;
				  		 angular.forEach(result.data, function(value, key){
				  			if(value.tenNhiemVu=="Đã nhận tại PTK"){
				  				vm.addQlcvPopup.maNv1= value.idUser;
				  				vm.addQlcvPopup.hoVaTen1=value.fullname;
				  				vm.addQlcvPopup.ngayGiaoNv1=value.ngayGiaoNv;
				  				vm.addQlcvPopup.ngayHt1=value.ngayHoanThanh;
				  			}
				  			if(value.tenNhiemVu=="Đã nhận QTK"){
				  				vm.addQlcvPopup.maNv2= value.idUser;
				  				vm.addQlcvPopup.hoVaTen2=value.fullname;
				  				vm.addQlcvPopup.ngayGiaoNv2=value.ngayGiaoNv;
				  				vm.addQlcvPopup.ngayHt2=value.ngayHoanThanh;
				  			}
				  			if(value.tenNhiemVu=="Đã thẩm định QTK"){
				  				vm.addQlcvPopup.maNv3= value.idUser;
				  				vm.addQlcvPopup.hoVaTen3=value.fullname;
				  				vm.addQlcvPopup.ngayGiaoNv3=value.ngayGiaoNv;
				  				vm.addQlcvPopup.ngayHt3=value.ngayHoanThanh;
				  			}
				  			if(value.tenNhiemVu=="Đã lập đề nghị"){
				  				vm.addQlcvPopup.maNv4= value.idUser;
				  				vm.addQlcvPopup.hoVaTen4=value.fullname;
				  				vm.addQlcvPopup.ngayGiaoNv4=value.ngayGiaoNv;
				  				vm.addQlcvPopup.ngayHt4=value.ngayHoanThanh;
				  			}
				  			if(value.tenNhiemVu=="Đã chốt với CDT"){
				  				vm.addQlcvPopup.maNv5= value.idUser;
				  				vm.addQlcvPopup.hoVaTen5=value.fullname;
				  				vm.addQlcvPopup.ngayGiaoNv5=value.ngayGiaoNv;
				  				vm.addQlcvPopup.ngayHt5=value.ngayHoanThanh;
				  			}
				  		 })
				  	}); 
		
					var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/ganNhiemVuPopUp.html";
				    var title="Gán nhân viên cho công việc số :" + dataItem.tblQlCvPtkId;
				    var windowId="TD_QL_CV";
				    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'75%','75%');
				}
			 
	
				
			 //Gán nhiệm vụ
			var date = kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
			 vm.saveNv = function(){
				
				 if(vm.addQlcvPopup.ngayGiaoNv1==null){
					 vm.addQlcvPopup.ngayGiaoNv1=date;
				 }
				 if(vm.addQlcvPopup.ngayGiaoNv2==null){
					 vm.addQlcvPopup.ngayGiaoNv2=date;
				 }
				 if(vm.addQlcvPopup.ngayGiaoNv3==null){
					 vm.addQlcvPopup.ngayGiaoNv3=date;
				 }
				 if(vm.addQlcvPopup.ngayGiaoNv4==null){
					 vm.addQlcvPopup.ngayGiaoNv4=date;
				 }
				 if(vm.addQlcvPopup.ngayGiaoNv5==null){
					 vm.addQlcvPopup.ngayGiaoNv5=date;
				 }
				 if(vm.addQlcvPopup.hoVaTen1==""){
					 vm.addQlcvPopup.maNv1=null;
					 vm.addQlcvPopup.hoVaTen1=null;
				 }
				 if(vm.addQlcvPopup.hoVaTen2==""){
					 vm.addQlcvPopup.maNv2=null;
					 vm.addQlcvPopup.hoVaTen2=null;
				 }
				 if(vm.addQlcvPopup.hoVaTen3==""){
					 vm.addQlcvPopup.maNv3=null;
					 vm.addQlcvPopup.hoVaTen3=null;
				 }
				 if(vm.addQlcvPopup.hoVaTen4==""){
					 vm.addQlcvPopup.maNv4=null;
					 vm.addQlcvPopup.hoVaTen4=null;
				 }
				 if(vm.addQlcvPopup.hoVaTen5==""){
					 vm.addQlcvPopup.maNv5=null;
					 vm.addQlcvPopup.hoVaTen5=null;
				 }
				 if(vm.addQlcvPopup.hoVaTen1!=null){
					 if(vm.addQlcvPopup.ngayHt1==null){
						 toastr.error("Ngày hoàn thành nhiệm vụ Đã nhận tại PTK không được để trống");
						 return;
					 }
					 
				 }
				 if(vm.addQlcvPopup.hoVaTen2!=null){
					 if(vm.addQlcvPopup.ngayHt2==null){
						 toastr.error("Ngày hoàn thành nhiệm vụ Đã nhận QTK không được để trống");
						 return;
					 }
				 }
				 if(vm.addQlcvPopup.hoVaTen3!=null){
					 if(vm.addQlcvPopup.ngayHt3==null){
						 toastr.error("Ngày hoàn thành nhiệm vụ Đã thẩm định QTK không được để trống");
						 return;
					 }
				 }
				 if(vm.addQlcvPopup.hoVaTen4!=null){
					 if(vm.addQlcvPopup.ngayHt4==null){
						 toastr.error("Ngày hoàn thành nhiệm vụ Đã lập đề nghị không được để trống");
						 return;
					 }
				 }
				 if(vm.addQlcvPopup.hoVaTen5!=null){
					 if(vm.addQlcvPopup.ngayHt5==null){
						 toastr.error("Ngày hoàn thành nhiệm vụ Đã chốt với CDT không được để trống");
						 return;
					 }
				 }
				 
				var tenCv = vm.getTblQlCvPtk.mttMaViTri+"-"+vm.getTblQlCvPtk.soHdCdt;				
				 var list = [
					 {
						 idUser : vm.addQlcvPopup.maNv1,
						 fullname: vm.addQlcvPopup.hoVaTen1,
						 idQlCvPtk: vm.getTblQlCvPtk.tblQlCvPtkId,
						 tenNhiemVu: "Đã nhận tại PTK",
						 ngayGiaoNv: vm.addQlcvPopup.ngayGiaoNv1,
						 ngayHoanThanh :vm.addQlcvPopup.ngayHt1,
						 trangThai: "Mới tạo",
						 tenCongViec: tenCv
					 },
					 {
						 idUser : vm.addQlcvPopup.maNv2,
						 fullname: vm.addQlcvPopup.hoVaTen2,
						 idQlCvPtk: vm.getTblQlCvPtk.tblQlCvPtkId,
						 tenNhiemVu: "Đã nhận QTK",
						 ngayGiaoNv: vm.addQlcvPopup.ngayGiaoNv2,
						 ngayHoanThanh :vm.addQlcvPopup.ngayHt2,
						 trangThai: "Mới tạo",
						 tenCongViec: tenCv
					 },
					 {
						 idUser : vm.addQlcvPopup.maNv3,
						 fullname: vm.addQlcvPopup.hoVaTen3,
						 idQlCvPtk: vm.getTblQlCvPtk.tblQlCvPtkId,
						 tenNhiemVu: "Đã thẩm định QTK",
						 ngayGiaoNv: vm.addQlcvPopup.ngayGiaoNv3,
						 ngayHoanThanh :vm.addQlcvPopup.ngayHt3,
						 trangThai: "Mới tạo",
						 tenCongViec: tenCv
					 },
					 {
						 idUser : vm.addQlcvPopup.maNv4,
						 fullname: vm.addQlcvPopup.hoVaTen4,
						 idQlCvPtk: vm.getTblQlCvPtk.tblQlCvPtkId,
						 tenNhiemVu: "Đã lập đề nghị",
						 ngayGiaoNv: vm.addQlcvPopup.ngayGiaoNv4,
						 ngayHoanThanh :vm.addQlcvPopup.ngayHt4,
						 trangThai: "Mới tạo",
						 tenCongViec: tenCv
					 },
					 {
						 idUser : vm.addQlcvPopup.maNv5,
						 fullname: vm.addQlcvPopup.hoVaTen5,
						 idQlCvPtk: vm.getTblQlCvPtk.tblQlCvPtkId,
						 tenNhiemVu: "Đã chốt với CDT",
						 ngayGiaoNv: vm.addQlcvPopup.ngayGiaoNv5,
						 ngayHoanThanh :vm.addQlcvPopup.ngayHt5,
						 trangThai: "Mới tạo",
						 tenCongViec: tenCv	 
					 }
				 ]
			
				
				 angular.forEach(list, function(value, key){
					
					 if(value.idUser!=null){
						
						 GNVService.saveGanNv(value).then(function(result){
							 	if(result==404){
							 		toastr.warning("Nhiệm vụ đã được gán!!!");
							 		return;
							 	}
							 if(result.error){
								 toastr.error(result.error);
								 return;
							 }
							 vm.getTblQlCvPtk.trangThai="Đã gán nhân viên";
							 tblQlCvPtkService.updateStatus(vm.getTblQlCvPtk).then(function(result){
								
								 if(result.error){
								 toastr.error(result.error);
								 return;
							 }
								
								 
							 })
							 toastr.success("Cập nhật thành công!");
								
								$("#qlCvPtkGrid").data('kendoGrid').dataSource.read();
				    			$("#qlCvPtkGrid").data('kendoGrid').refresh();
				    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click(); 
						 },function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật nhiệm vụ!!!"));
				            }
						 
						 )
	 
					 }	
					
					
				 })
				 
				 
			 }
			 
			
			//mở popup danh sách nhân viên được gán
			 vm.searchGanNV = function(){
				 CommonService.goTo('GAN_NHIEM_VU');
				
			 }
			 
			 
			//mở popup  file đính kèm
		
			  vm.objUpload={};
		        vm.uploadFile = function(dataItem){
					var teamplateUrl="qll/A_PTK-tblQLCvPtk/upLoadFile.html";
				    var title="Danh sách file đính kèm";
				    var windowId="UPLOAD_IMAGE_HD";
				    vm.objUpload=dataItem;
				    vm.dataFile = [];
				
					fillFileUpLoadTable([]);
					fillFileDownLoadTable([]);
				    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','70%');
				}
		        
			 //lựa chon file đính kèm

		        function refreshGrid(d) {
					var grid = vm.upLoadFileGrid;
					if(grid){
						grid.dataSource.data(d);
						grid.refresh();
					}
				}
		        function refreshGrid1(d){
		        	var grid = vm.downLoadFileGrid;
					if(grid){
						grid.dataSource.data(d);
						grid.refresh();
					}
		        }
		        var date = kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
				vm.onSelect = function(e) {
					
					 if($("#files")[0].files[0].name.split('.').pop() !='xls' && $("#files")[0].files[0].name.split('.').pop() !='xlsx'
						 && $("#files")[0].files[0].name.split('.').pop() !='doc'&& $("#files")[0].files[0].name.split('.').pop() !='docx'
						 && $("#files")[0].files[0].name.split('.').pop() !='pdf'&& $("#files")[0].files[0].name.split('.').pop() !='jpeg'
						 && $("#files")[0].files[0].name.split('.').pop() !='png'&& $("#files")[0].files[0].name.split('.').pop() !='jpg' ){
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
						 vm.dataFile = $("#upLoadFileGrid").data().kendoGrid.dataSource.data();
					 var obj={};
					 obj.name=file.name;
					 obj.pathFile=data[index];
					 obj.fileType = file.extension;
					 obj.idCvPtk = vm.objUpload.tblQlCvPtkId;
					 obj.createdAt = date;
					 obj.createdBy = $rootScope.casUser.fullName;
					 obj.updateAt = null;
					 obj.updateBy = null;
					 vm.dataFile.push(obj);
					
					 })
					
	            refreshGrid(vm.dataFile);
	            setTimeout(function() {
	            	  $(".k-upload-files.k-reset").find("li").remove();
	 			     $(".k-upload-files").remove();
	 				 $(".k-upload-status").remove();
	 				 $(".k-upload.k-header").addClass("k-upload-empty");
	 				 $(".k-upload-button").removeClass("k-state-focused");
	       },10); 
		            }
		        });
		    }
		    	
		    	//danh sach file dinh kem tai len
		    	
		    	function fillFileUpLoadTable(data) {
					var dataSource = new kendo.data.DataSource({
		                 data: data,
		                autoSync: false, 
		             
		            });
					vm.gridFileOptions = kendoConfig.getGridOptions({
						autoBind: true,
						resizable: true,			 
						dataSource:dataSource,
						noRecords: true,
						columnMenu:false,
						scrollable:false,
						messages: {
							noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
						},
//						dataBound: function () {
//							var GridDestination = $("#upLoadFileGrid").data("kendoGrid");                
//					                GridDestination.pager.element.hide();
//		                },
						columns: [{
							title: "TT",
							field:"stt",
					        template: dataItem => $("#upLoadFileGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					        width: 20,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
						}
						, {
							title: "Tên file",
							field: 'name',
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
							template :  function(dataItem) {
								        	    	 return "<a href='' ng-click='caller.downloadFile(dataItem)'>" + dataItem.name + "</a>";
							}
						},  {
							title: "Loại file",
							field: 'fileType',
					        width: 150,
					        headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								"id":"appFile",
								style: "text-align:left;"
							},
						}  ,{
						 title: "Xóa",
						  template: dataItem => 
							'<div class="text-center #=utilAttachDocumentId#""> '+
								'<button style=" border: none; "  class="#=utilAttachDocumentId# icon_table" uib-tooltip="Xóa" ng-click="caller.removeRowFile(dataItem)"  translate>  '+
									'<i style="color: steelblue;" class="fa fa-trash" ria-hidden="true"></i>'+
								'</button>'+
							'</div>' , 
						 width: 20,
						 field:"acctions"
						 }
						,],
					});
				}
		    	
		    	// danh sách file đính kèm tải xuống
//		    	function refreshGrid1(d,grid) {
//					var grid = grid;
//					if(grid){
//						grid.dataSource=d.data;
////						grid.refresh();
//					
//					}
//				}
		    
		    
		    	function fillFileDownLoadTable(dataSource) {
		    		vm.doSearchFile.idCvPtk = vm.objUpload.tblQlCvPtkId;
				
				    tblQlCvFileService.doSearch(vm.doSearchFile).then(function(datalist) {
						
						var data = datalist.data;
					
						vm.gridFile1Options = kendoConfig.getGridOptions({
							autoBind: true,
							sortable: true,
							resizable: true,
							columnMenu: false,			 
							dataSource: data,
							
							noRecords: true,
							messages: {
								noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
							},
							dataBound: function () {
								var GridDestination = $("#downLoadFileGrid").data("kendoGrid");                
						                GridDestination.pager.element.hide();
						               
			                },
			              
							columns: [{
								title: "TT",
								field:"stt",
								template: dataItem => $("#downLoadFileGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        width: 30,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}
						
							,    {
								title: "Tên file",
								field: 'name',
						        width: 150,
								template :  function(dataItem) {
									        	    	 return "<a href='' ng-click='caller.downloadFile1(dataItem)'>" + dataItem.name + "</a>";
								},
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							},{
								 title: "Xóa",
								  template: dataItem => 
									'<div class="text-center #=removefileId#""> '+
										'<button style=" border: none; "  class="#=removefileId# icon_table" uib-tooltip="Xóa" ng-click="caller.removeRowFileDownLoad()"  translate>  '+
											'<i style="color: steelblue;" class="fa fa-trash" ria-hidden="true"></i>'+
										'</button>'+
									'</div>' , 
								 width: 20,
								 field:"acctions"
								 }
							,]
						});
						
						refreshGrid1(data);
					},function(errResponse) {
						console.error('Error Tìm kiếm');
					} );
				   
				
				}
		    	// xóa file đính kèm tải xuống
		    	 vm.removeRowFileDownLoad= function(){
		    		var listID = data.data[0].tblQlCvPtkFileId;
		    		
		    	
			        	confirm('Bạn có chắc chắn muốn  xóa  bản ghi?', function(){
			        		tblQlCvFileService.deleteObj(listID).then(function(result){
									toastr.success("Xóa bản ghi thành công!");
									
									$("#downLoadFileGrid").data('kendoGrid').dataSource.read();
					    			$("#downLoadFileGrid").data('kendoGrid').refresh();
					    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
							});	
			        	});
						
					}
		   
		    	//xóa file đính kèm tạo mới
		    	vm.removeRowFile = removeRowFile;
				function removeRowFile(dataItem) {
					confirm('Xác nhận xóa',function (){
						$('#upLoadFileGrid').data('kendoGrid').dataSource.remove(dataItem);
						 vm.dataFile.splice(dataItem,1);
					})
				}
		    	
				
				//lưa file đính kèm
				
				vm.saveFile = function(){
					
					var list = vm.dataFile;
				
					 angular.forEach(list, function(value, key){
						 
						 tblQlCvFileService.saveFile(value).then(function(result){
								if(result.error){
				    				toastr.error(result.error);
				    				return;
				    			}
								toastr.success("Lưu file thành công!");
								
								$("#upLoadFileGrid").data('kendoGrid').dataSource.read();
				    			$("#upLoadFileGrid").data('kendoGrid').refresh();
				    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click(); 
								
							})
					 })
					 
					
					
					
					
					
				}
				
				
		        vm.removeAny=function(){
			    	
			    	vm.oldSearch.page=null;
					vm.oldSearch.pageSize=null;
					
				
								confirm("Bạn có muốn xóa!", function() {
									tblQlCvPtkService.deleteListObj(vm.oldSearch).then(function(result){
										if(result.error){
						    				toastr.error(result.error);
						    				return;
						    			}
						    			toastr.success("Xóa công việc thành công!");
						    			  var sizePage = $("#qlCvPtkGrid").data("kendoGrid").dataSource.total();
						    			  var pageSize = $("#qlCvPtkGrid").data("kendoGrid").dataSource.pageSize();
						    					if(sizePage % pageSize === 1){
						    								var currentPage = $("#qlCvPtkGrid").data("kendoGrid").dataSource.page();
						    								if (currentPage > 1) {
						    									$("#qlCvPtkGrid").data("kendoGrid").dataSource.page(currentPage - 1);
						    								}
						    							}
						    			$("#qlCvPtkGrid").data('kendoGrid').dataSource.read();
						    			$("#qlCvPtkGrid").data('kendoGrid').refresh();
						    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
									},function(errResponse){
						                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi xóa"));
						            });
								})
						
					
				
					
			    }
		        
		        //tải về file đính kèm
			 vm.downloadFile1 = function(dataItem){


					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.pathFile;	
			}
			
		        vm.downloadFile = function(dataItem){
		        	vm.test= dataItem;
		        	debugger;
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.filePath;	
			}
			 
			 //get tên nhân viên nv 1,2,3,4,5
			 //1
		        vm.patternOptionsNV1={
		    			dataTextField: "hoVaTen1", placeholder:"Nhập mã hoặc tên nhân viên",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.addQlcvPopup.maNv1=dataItem.userCode;
		                    vm.addQlcvPopup.hoVaTen1=dataItem.fullName;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có bảng
									// đơn
									// vị
		                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$("#nhanVienAuto1").val()}).then(function(response){
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
		                	if(processSearch('nhanVienAuto1',vm.selectedPrpject)){
		    					 $('#nhanVienAuto1').val("");
		    					 vm.addQlcvPopup.hoVaTen1="";
		    					 vm.addQlcvPopup.maNv1="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		        //2
		        vm.patternOptionsNV2={
		    			dataTextField: "hoVaTen", placeholder:"Nhập mã hoặc tên nhân viên",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.addQlcvPopup.maNv2=dataItem.userCode;
		                    vm.addQlcvPopup.hoVaTen2=dataItem.fullName;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có bảng
									// đơn
									// vị
		                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$("#nhanVienAuto2").val()}).then(function(response){
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
		                	if(processSearch('nhanVienAuto2',vm.selectedPrpject)){
		    					 $('#nhanVienAuto2').val("");
		    					 vm.addQlcvPopup.hoVaTen2="";
		    					 vm.addQlcvPopup.maNv2="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		        //3
		        vm.patternOptionsNV3={
		    			dataTextField: "hoVaTen", placeholder:"Nhập mã hoặc tên nhân viên",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.addQlcvPopup.maNv3=dataItem.userCode;
		                    vm.addQlcvPopup.hoVaTen3=dataItem.fullName;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có bảng
									// đơn
									// vị
		                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$("#nhanVienAuto3").val()}).then(function(response){
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
		                	if(processSearch('nhanVienAuto3',vm.selectedPrpject)){
		    					 $('#nhanVienAuto3').val("");
		    					 vm.addQlcvPopup.hoVaTen3="";
		    					 vm.addQlcvPopup.maNv3="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		        //4
		        vm.patternOptionsNV4={
		    			dataTextField: "hoVaTen", placeholder:"Nhập mã hoặc tên nhân viên",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.addQlcvPopup.maNv4=dataItem.userCode;
		                    vm.addQlcvPopup.hoVaTen4=dataItem.fullName;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có bảng
									// đơn
									// vị
		                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$("#nhanVienAuto4").val()}).then(function(response){
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
		                	if(processSearch('nhanVienAuto4',vm.selectedPrpject)){
		    					 $('#nhanVienAuto4').val("");
		    					 vm.addQlcvPopup.hoVaTen4="";
		    					 vm.addQlcvPopup.maNv4="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		        //5
		        vm.patternOptionsNV5={
		    			dataTextField: "hoVaTen", placeholder:"Nhập mã hoặc tên nhân viên",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.addQlcvPopup.maNv5=dataItem.userCode;
		                    vm.addQlcvPopup.hoVaTen5=dataItem.fullName;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có bảng
									// đơn
									// vị
		                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$("#nhanVienAuto5").val()}).then(function(response){
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
		                	if(processSearch('nhanVienAuto5',vm.selectedPrpject)){
		    					 $('#nhanVienAuto5').val("");
		    					 vm.addQlcvPopup.hoVaTen5="";
		    					 vm.addQlcvPopup.maNv5="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		        // tu dong so hop dong cdt
		        vm.patternOptionsSHDCdt={
		    			dataTextField: "soHdCdt", placeholder:"Nhập số hợp đồng",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.qlCvPtkSearch.soHdCdt=dataItem.soHdCdt;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("tblQlCvPtkRsServiceRest/getForAutoCompleteSHD").post({pageSize:10, page:1, soHdCdt:$("#tenShdCdtAuto").val().trim()}).then(function(response){
		                                options.success(response);
		                            }).catch(function (err) {
		                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                            });
		                        }
		                    }
		                },
		                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
		                '<p class="col-md-12 text-header-auto">Số hợp đồng</p>' +
		                	'</div>',
		                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.soHdCdt #</div></div>',
		                change: function(e) {
		                	if(processSearch('tenShdCdtAuto',vm.selectedPrpject)){
		    					 $('#tenShdCdtAuto').val("");
		    					 vm.qlCvPtkSearch.soHdCdt="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		        	
		        vm.patternOptionsSHDPr={
		        		dataTextField: "soHdCdt", placeholder:"Nhập số hợp đồng",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.tblQlCvPtkChart.soHdCdt=dataItem.soHdCdt;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("tblQlCvPtkRsServiceRest/getForAutoCompleteSHD").post({pageSize:10, page:1, soHdCdt:$("#soHdChart").val().trim()}).then(function(response){
		                                options.success(response);
		                            }).catch(function (err) {
		                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                            });
		                        }
		                    }
		                },
		                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
		                '<p class="col-md-12 text-header-auto">Số hợp đồng</p>' +
		                	'</div>',
		                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.soHdCdt #</div></div>',
		                change: function(e) {
		                	if(processSearch('soHdChart',vm.selectedPrpject)){
		    					 $('#soHdChart').val("");
		    					 vm.tblQlCvPtkChart.soHdCdt="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }	
		        }
		        	
			//page báo cáo biểu đồ
		        vm.reportChart = function(){
		    		
					 CommonService.goTo('BAO_CAO_BIEU_DO_PTK');

			 }
		        vm.tblQlCvPtkChart={}; 
		   	 vm.getChart = getChart;
			 function getChart(){
				 
				 if(vm.tblQlCvPtkChart.soHdCdt==""){
					 vm.tblQlCvPtkChart.soHdCdt=null;
				 }
				 if(vm.tblQlCvPtkChart.ngayNhanHshcFrom){
					 
				 }
				
				 
				 if(vm.tblQlCvPtkChart.soHdCdt){
					
					 tblQlCvPtkService.getChart(vm.tblQlCvPtkChart).then(
								
								function(data){
									
									if(vm.tblQlCvPtkChart.chartType=='SL'){
									
										Highcharts.chart('chartPTK', {
											chart: {
										        zoomType: 'xy'
										    },

										    title: {
										        text: 'Biểu đồ Sản lượng các chi nhánh theo hợp đồng : '+ vm.tblQlCvPtkChart.soHdCdt 
										    },
										   
										    xAxis: [{
										        categories: data.listCnkv
										                    }],
						                    yAxis: [
									        	 { // Secondary yAxis
												        title: {
												            text: 'Trạm',
												            style: {
												            	color: Highcharts.getOptions().colors[1]
												            }
												        },
												        min: 0,
												        labels: {
												            format: '{value} trạm',
												            style: {
												            	 color: Highcharts.getOptions().colors[1]
												            }
												        },
												        opposite: true
												    },
									        	{ // Primary yAxis
										        labels: {
										            format: '{value} đồng',
										            style: {
										                
												    color: '#ed6b75'
										            }
										        },
										        allowDecimals: false,
										        min: 0,
										        title: {
										            text: 'Giá trị thành tiền',
										            style: {
										               
										        	color: '#ed6b75'
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
											        name: 'Tổng số tramh theo Giá trị hạ tầng ',
											        type: 'column',

											      
											        data: data.listSoLuongMaTramGtSl,
											        tooltip: {
											            valueSuffix: 'trạm'
											        },
											        stack:'TR'

											    }, 
											      {
											          name: 'Tổng số trạm theo Đề nghị CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramDnQtCdt,
											        stack:'TR'
											    },
											    {
											          name: 'Tổng số trạm theo Thẩm định CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramTdQtCdt,
											        stack:'TR'
											    },
											      {
											        name: 'Giá trị sản lượng Hạ tầng',
											        type: 'spline',
											        yAxis: 1,
											        data: data.listGtSlHtTtTong,
											        tooltip: {
											            valueSuffix: 'đồng'}
											    
											        },
											        {
											        	name: 'Giá trị sản lượng Hạ tầng theo đề nghị CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listGtSlHtTtTongDn,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        },
											        {
											        	name: 'Giá trị sản lượng Hạ tầng theo thẩm định CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listGtSlHtTtTongTd,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        }
											    
											    
											    
											    ]
										})
									}
									
									if(vm.tblQlCvPtkChart.chartType=='DT'){
										Highcharts.chart('chartPTK', {
											chart: {
										        zoomType: 'xy'
										    },

										    title: {
										        text: 'Biểu đồ Doanh Thu các chi nhánh theo hợp đồng : '+ vm.tblQlCvPtkChart.soHdCdt 
										    },
										   
										    xAxis: [{
										        categories: data.listCnkv
										                    }],
						                    yAxis: [
									        	 { // Secondary yAxis
												        title: {
												            text: 'Trạm',
												            style: {
												            	color: Highcharts.getOptions().colors[1]
												            }
												        },
												        min: 0 ,
												        labels: {
												            format: '{value} trạm',
												            style: {
												            	 color: Highcharts.getOptions().colors[1]
												            }
												        },
												        opposite: true
												    },
									        	{ // Primary yAxis
										        labels: {
										            format: '{value} đồng',
										            style: {
										                
												    color: '#ed6b75'
										            }
										        },
										        allowDecimals: false,
										        min: 0,
										        title: {
										            text: 'Giá trị thành tiền',
										            style: {
										               
										        	color: '#ed6b75'
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
											        name: 'Tổng số tramh theo Giá trị hạ tầng ',
											        type: 'column',

											      
											        data: data.listSoLuongMaTramGtSl,
											        tooltip: {
											            valueSuffix: 'trạm'
											        },
											        stack:'TR'

											    }, 
											      {
											          name: 'Tổng số trạm theo Đề nghị CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramDnQtCdt,
											        stack:'TR'
											    },
											    {
											          name: 'Tổng số trạm theo Thẩm định CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramTdQtCdt,
											        stack:'TR'
											    },
											      {
											        name: 'Giá trị sản lượng Hạ tầng',
											        type: 'spline',
											        yAxis: 1,
											        data: data.listGtSlHtTtTong,
											        tooltip: {
											            valueSuffix: 'đồng'}
											    
											        },
											        {
											        	name: 'Giá trị đề nghị CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listDnQtCdtGt,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        },
											        {
											        	name: 'Giá trị thẩm định CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listTdQtCdtGt,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        }
											    
											    
											    
											    ]
										})
									}
									
									}
									
							); 
				 }else{
					 tblQlCvPtkService.getChart(vm.tblQlCvPtkChart).then(
								
								function(data){
								
									if(vm.tblQlCvPtkChart.chartType=='SL'){
									
										Highcharts.chart('chartPTK', {
											chart: {
										        zoomType: 'xy'
										    },

										    title: {
										        text: 'Biểu đồ Sản lượng các chi nhánh  ' 
										    },
										   
										    xAxis: [{
										        categories: data.listCnkv
										                    }],
						                    yAxis: [
									        	 { // Secondary yAxis
												        title: {
												            text: 'Trạm',
												            style: {
												            	color: Highcharts.getOptions().colors[1]
												            }
												        },
												        min: 0,
												        labels: {
												            format: '{value} trạm',
												            style: {
												            	 color: Highcharts.getOptions().colors[1]
												            }
												        },
												        opposite: true
												    },
									        	{ // Primary yAxis
										        labels: {
										            format: '{value} đồng',
										            style: {
										                
												    color: '#ed6b75'
										            }
										        },
										        allowDecimals: false,
										        min: 0,
										        title: {
										            text: 'Giá trị thành tiền',
										            style: {
										               
										        	color: '#ed6b75'
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
											        name: 'Tổng số tramh theo Giá trị hạ tầng ',
											        type: 'column',

											      
											        data: data.listSoLuongMaTramGtSl,
											        tooltip: {
											            valueSuffix: 'trạm'
											        },
											        stack:'TR'

											    }, 
											      {
											          name: 'Tổng số trạm theo Đề nghị CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramDnQtCdt,
											        stack:'TR'
											    },
											    {
											          name: 'Tổng số trạm theo Thẩm định CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramTdQtCdt,
											        stack:'TR'
											    },
											      {
											        name: 'Giá trị sản lượng Hạ tầng',
											        type: 'spline',
											        yAxis: 1,
											        data: data.listGtSlHtTtTong,
											        tooltip: {
											            valueSuffix: 'đồng'}
											    
											        },
											        {
											        	name: 'Giá trị sản lượng Hạ tầng theo đề nghị CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listGtSlHtTtTongDn,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        },
											        {
											        	name: 'Giá trị sản lượng Hạ tầng theo thẩm định CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listGtSlHtTtTongTd,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        }
											    
											    
											    
											    ]
										})
									}
									
									if(vm.tblQlCvPtkChart.chartType=='DT'){
										Highcharts.chart('chartPTK', {
											chart: {
										        zoomType: 'xy'
										    },

										    title: {
										        text: 'Biểu đồ Doanh Thu các chi nhánh ' 
										    },
										   
										    xAxis: [{
										        categories: data.listCnkv
										                    }],
						                    yAxis: [
									        	 { // Secondary yAxis
												        title: {
												            text: 'Trạm',
												            style: {
												            	color: Highcharts.getOptions().colors[1]
												            }
												        },
												        min: 0,
												        labels: {
												            format: '{value} trạm',
												            style: {
												            	 color: Highcharts.getOptions().colors[1]
												            }
												        },
												        opposite: true
												    },
									        	{ // Primary yAxis
										        labels: {
										            format: '{value} đồng',
										            style: {
										                
												    color: '#ed6b75'
										            }
										        },
										        allowDecimals: false,
										        min: 0,
										        title: {
										            text: 'Giá trị thành tiền',
										            style: {
										               
										        	color: '#ed6b75'
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
											        name: 'Tổng số tramh theo Giá trị hạ tầng ',
											        type: 'column',

											      
											        data: data.listSoLuongMaTramGtSl,
											        tooltip: {
											            valueSuffix: 'trạm'
											        },
											        stack:'TR'

											    }, 
											      {
											          name: 'Tổng số trạm theo Đề nghị CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramDnQtCdt,
											        stack:'TR'
											    },
											    {
											          name: 'Tổng số trạm theo Thẩm định CDT',
											        type: 'column',

											        
											       tooltip: {
											            valueSuffix: 'trạm'
											        },
											        data: data.listSoLuongMaTramTdQtCdt,
											        stack:'TR'
											    },
											      {
											        name: 'Giá trị sản lượng Hạ tầng',
											        type: 'spline',
											        yAxis: 1,
											        data: data.listGtSlHtTtTong,
											        tooltip: {
											            valueSuffix: 'đồng'}
											    
											        },
											        {
											        	name: 'Giá trị đề nghị CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listDnQtCdtGt,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        },
											        {
											        	name: 'Giá trị thẩm định CDT',
												        type: 'spline',
												        yAxis: 1,
												        data: data.listTdQtCdtGt,
												        tooltip: {
												            valueSuffix: 'đồng'}
											        }
											    
											    
											    
											    ]
										})
									}
									
									}
									
							); 
				 }
				
			
				
			 }
		 var d = new Date();
	  		var n = d.getFullYear();
	  		vm.date=n;
		 
	}
	
})();