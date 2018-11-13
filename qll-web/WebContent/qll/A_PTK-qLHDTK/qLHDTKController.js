(function() {
	'use strict';
	var controllerId = 'qLHDTKController';

	angular.module('MetronicApp').controller(controllerId,
			qLHDTKController);

	function qLHDTKController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, qLHDTKService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.qLHDTKSearch = {};
		vm.addHdPop={};
		vm.exportHD={};
		vm.exportHD1={};
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
		//tất cả quyền PTK
		vm.roleememe=$rootScope.RoleMenu.checkRole.checkTPPTP;
		
	
		//quyền thẩm định
		vm.roleememeTD=$rootScope.RoleMenu.checkRole.checkThamDinhDN;
	
		
		setTimeout(function() {
			$("#changeId").focus();
		}, 15);
// if(RoleUser.RoleUser==="CN1"){
// checkExportPXK=true;
// break;
// }
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

		vm.advancedSearch =function(){
			vm.showAdvancedSearch = !vm.showAdvancedSearch;
			if(!vm.showAdvancedSearch){
				vm.addHdPop.ngayGuiPtkHshc=null;
			}else{
				vm.addHdPop.thangHshcQuyLuong=null;
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
									+ 'ng-click="vm.addHdPopSubmit()" ng-show="RoleMenu.checkRole.checkNhapLieuDN||RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Thêm mới" translate >Thêm mới</button>'
									 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.xuatBCLuongPop()" ng-show="RoleMenu.checkRole.checkTPPTP||RoleMenu.checkRole.checkThamDinhDN" aria-hidden="true" uib-tooltip="Xuất báo cáo lương" aria-hidden="true" translate>BC lương</button>'
									 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.xuatBCTH()" ng-show="RoleMenu.checkRole.checkTPPTP||RoleMenu.checkRole.checkThamDinhDN" aria-hidden="true" uib-tooltip="Xuất báo cáo theo ngày/tháng" aria-hidden="true" translate>BC tổng hợp </button>'
									 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.exportExcelGrid()" ng-show="RoleMenu.checkRole.checkTPPTP||RoleMenu.checkRole.checkThamDinhDN" aria-hidden="true" uib-tooltip="Phệ duyệt quyết toán" aria-hidden="true" translate>Phê duyệt quyết toán</button>'
									 + '<input  k-options="vm.patternOptionsSHD" ng-show="RoleMenu.checkRole.checkTPPTP||RoleMenu.checkRole.checkThamDinhDN" aria-hidden="true"  kendo-auto-complete style="height:30px;outline:auto;margin-right:10px;width:300px;" type="text" ng-model="vm.exportHD1.soHd" id="soHdxx" />'
									 + '</div>'
									
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+'<button class="btn btn-qlk padding-search-right deletehd" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+'<i class="action-button excelQLK" uib-tooltip="Xuất tất cả dữ liệu" ng-click="vm.exportExcelGrid1()" aria-hidden="true"></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.qLHDTKGrid.columns.slice(1,vm.qLHDTKGrid.columns.length)">'
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
									$("#qLHDTKCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "kqHdTkRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {

									vm.qLHDTKSearch.page = options.page;
									vm.qLHDTKSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.qLHDTKSearch);
									return JSON.stringify(vm.qLHDTKSearch);

								}
							},
							pageSize :10
						},
						dataBound : function(data) {
							
							var grid = vm.qLHDTKGrid;
							 
							 var grid = $("#qLHDTKGrid").data("kendoGrid");

							 var date2 = kendo.parseDate(d, "dd/MM/yyyy");
								var dataGoodFromGrid = $('#qLHDTKGrid').data("kendoGrid").dataSource.data();
								
								for(var j = 0; j<dataGoodFromGrid.length;j++){
									
									var date1 = kendo.parseDate(dataGoodFromGrid[j].ngayCapNhat,"dd/MM/yyyy");
									
									if(dataGoodFromGrid[j].ngayCapNhat!=null&&dataGoodFromGrid[j].trangThai=="Mới tạo"){
								  	 	if((date2.getTime()-date1.getTime())>24*60*60*1000){
								  	 		currenRow1 = grid.table.find("tr[data-uid='" + data._data[j].uid + "']").addClass("currenRow"+j);
											currenRow1.css({"background":"#FFFF66"});
											currenRow1.attr("title","Hợp đồng quá hạn 1 ngày chưa duyệt, thẩm định");
									  	}
								  	}
								}

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
									template : '<div class="text-center #=kqHdTkId#""> '
										
										
											+ '		<button style=" border: none; " class="#=kqHdTkId# icon_table" aria-hidden="true"  uib-tooltip="Cập nhật đề nghị" translate>'
											+ '			<i class="fa gandonvi"  ng-click="vm.updateHdPopSubmit(dataItem)" ng-show="RoleMenu.checkRole.checkNhapLieuDN"   aria-hidden="true"></i> '
											+ '		</button>'
											+ '		<button style=" border: none; " class="#=kqHdTkId# icon_table" aria-hidden="true"  uib-tooltip="Cập nhật thẩm định" translate>'
											+ '			<i class="fa gankhuvuc"  ng-click="vm.updateHDTd(dataItem)" ng-show="RoleMenu.checkRole.checkThamDinhDN||RoleMenu.checkRole.checkTPPTP"   aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=kqHdTkId# icon_table" aria-hidden="true"   uib-tooltip="Tải file đính kèm lên" translate>'
											+ '			<i class="fa uploadimg"  ng-click="vm.uploadFile(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+//
											'		<button style=" border: none; " class="#=kqHdTkId# icon_table" aria-hidden="true" ng-show="dataItem.pathFile!=null"  uib-tooltip="Tải về file đính kèm" translate>'
											+ '			<i class="fa downloadimg"  ng-click="vm.downloadFile(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+'		<button  class="#=kqHdTkId#  icon_table" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" style="border:none;" uib-tooltip="Xóa" translate> '+
											'		<i class="fa deletehdCon"  ng-click=vm.removeObj(dataItem) ria-hidden="true"></i>'+
											'		</button>'
											+ '</div>',
									width : '300px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;"
									}
								},{
									title : "Trạng thái",

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
									title : "Tỉnh",

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
									title : "Mã trạm/tuyến",
									field : 'maTramTuyen',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Nguồn cấp ứng",
									field : 'nguonCapUng',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Số hợp đồng",
									field : 'soHd',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Số kế hoạch thi công",
									field : 'soKhTc',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày ký kh",
									field : 'ngayKyKh',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Thuộc danh mục tờ trình",
									field : 'thuocDmToTrinh',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Loại công trình",
									field : 'loaiCt',
									template :  "# if(loaiCt ==='1'){ #" + "#= 'Nhà trạm' #" + "# } " + "if (loaiCt ==='2') { # " + "#= 'Truyền dẫn' #"+ "#}  if(loaiCt === '3'){ #" + "#= 'Cơ điện' #" + "# }  if(loaiCt === '4'){ #" + "#= 'GPON' #" + "# }#",
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Nội dung",
									field : 'noiDung',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày gửi HSHC",
									field : 'ngayGuiHshc',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Số bill",
									field : 'soBill',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
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
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày thi công xong",
									field : 'ngayThiCong',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Giá trị quyết toán CĐT chưa VAT",
									field : 'gtQtCdtChuaVat',
									width : '200px',
									format:"{0:n2}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Giá trị quyết toán CĐT có VAT",
									field : 'gtQtCdtCoVat',
									width : '200px',
									format:"{0:n2}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "TTKT đề nghị",
									width : '2000px',
									columns:[
										{
											title : "Chi phí nhân công ",
											field : 'cpNhanCongDn',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí vật liệu ",
											field : 'cpVatLieuDn',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí HSHC ",
											field : 'cpHshcDn',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí vận chuyển ",
											field : 'cpVanChuyenDn',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí khác ",
											field : 'chiPhiKhacDn',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí lương ",
											field : 'chiPhiLuongDn',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "VAT ",
											field : 'vatDn',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Tổng ",
											field : 'tongDn',
											width : '150px',
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
									title : "Người cập nhật",
									field : 'nguoiCapNhat',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày gửi PTK,HSHC",
									field : 'ngayGuiPtkHshc',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tình trạng chứng từ",
									field : 'tinhTrangChungTu',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày t/ứ theo tt chứng từ",
									field : 'ngayTutttt',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ghi chú hồ sơ lỗi",
									field : 'ghiChuHsLoi',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày PTK thẩm định xong",
									field : 'ngayPtkTdXong',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								{
									title : "Giá trị thẩm định PTK",
									width : '2000px',
									columns:[{
											title : "Chi phí nhân công",
											field : 'cpNhanCongTd',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí vật liệu",
											field : 'cpVatLieuTd',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí HSHC ",
											field : 'cpHshcTd',
											format:"{0:n2}",
											width : '150px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí vận chuyển ",
											field : 'cpVanChuyenTd',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí khác ",
											field : 'cpKhacTd',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Chi phí lương ",
											field : 'cpLuongTd',
											width : '150px',
											format:"{0:n2}",
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "VAT ",
											field : 'vatTd',
											format:"{0:n2}",
											width : '150px',
											headerAttributes : {
												style : "text-align:center;white-space:normal;font-weight:bold;"
											},
											attributes : {
												style : "text-align:center;"
											},
										},
										{
											title : "Tổng",
											field : 'tongTd',
											width : '150px',
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
									title : "Giá trị thẩm định PTK chưa VAT",
									field : 'gtTdPtkChuaVat',
									format:"{0:n2}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Giá trị thẩm định PTK có VAT",
									field : 'gtTdPtkCoVat',
									width : '150px',
									format:"{0:n2}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tháng HSHC về quỹ lương theo Quyết toán",
									field : 'thangHshcQuyLuong',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tháng ghi nhận quỹ lương theo Quyết toán",
									field : 'thangGhiNhanQuyLuongTqt',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tạm ứng lương",
									field : 'tamUngLuong',
									format:"{0:n2}",
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Lương thực nhận",
									field : 'luongThucNhan',
									width : '200px',
									format:"{0:n2}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tỷ lệ",
									field : 'tyLe',
									format:"{0:n2}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Hồ sơ tồn quá 5 ngày chưa xử lý",
									field : 'hsTonQua5n',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Giải trình về quá hạn chưa sử lý chứng từ",
									field : 'gtQhXlCt',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Người duyệt PTK",
									field : 'nguoiPheDuyetPtk',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								}]

					});
		}
		
		  vm.removeObj= function(dataItem){
	        	confirm('Bạn có chắc chắn muốn xóa bản ghi?', function(){
	        		qLHDTKService.deleteObj(dataItem).then(function(result){
							toastr.success("Xóa bản ghi thành công!");
							vm.doSearch();
					});	
	        	});
				
			}
		
		   vm.objUpload={};
	        vm.uploadFile = function(dataItem){
				var teamplateUrl="qll/quanLyVatTuACap/importDvPXK/upLoad.html";
			    var title="Tải file đính kèm";
			    var windowId="UPLOAD_IMAGE_HD";
			    vm.objUpload=dataItem;
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','40%');
			}
	        vm.onSelect = function(e) {
				 if($("#files")[0].files[0].name.split('.').pop() !='png' && $("#files")[0].files[0].name.split('.').pop() !='jpg'&& $("#files")[0].files[0].name.split('.').pop() !='xlsx'&& $("#files")[0].files[0].name.split('.').pop() !='xls'&& $("#files")[0].files[0].name.split('.').pop() !='pdf'&& $("#files")[0].files[0].name.split('.').pop() !='docx'&& $("#files")[0].files[0].name.split('.').pop() !='doc'){
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
			
				 vm.objUpload.pathFile=data[index];
				
				 vm.uploadFile = vm.objUpload;
				 
				 })
	            }
	        });
	    }
	        vm.saveFileUpLoad = function(){
	        	qLHDTKService.updatePath(vm.uploadFile).then(
						function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Tải file lên thành công!");
			    			$("#qLHDTKGrid").data('kendoGrid').dataSource.read();
			    			$("#qLHDTKGrid").data('kendoGrid').refresh();
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
			            });
	        }
	        vm.downloadFile = function(dataItem){
	        				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.pathFile;	
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
			case 'qLHDTKAuto':{
			if(processSearch(id,vm.selectedPrpject)){
				vm.qLHDTKSearch.donVi = null;
				vm.qLHDTKSearch.tenDanhMuc =null;
				vm.selectedPrpject=false;	
			 }
				break;
			}
		 }
		
		}
        
        vm.onSave=onSave;
		function onSave(data){
			vm.qLHDTKSearch.donVi=data.maDanhMuc;
			vm.qLHDTKSearch.tenDanhMuc=data.tenDanhMuc;
			
// $('#qLHDTKAuto').focus();
		}
        
        
        $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
        
        vm.showAdvancedSearch1=false;
        vm.advancedSearch1 =function(){
			vm.showAdvancedSearch1 = !vm.showAdvancedSearch1;
		}
        
	   

		vm.doSearch = doSearch;
		function doSearch() {	
			vm.showDetail = false;
			var grid = vm.qLHDTKGrid;
			
			if($("#soKhTc11").val()==""){
				vm.qLHDTKSearch.soKhTc=null;
			}
			if($("#soHd11").val()==""){
				vm.qLHDTKSearch.soHd=null;
			}
			if($("#kHuyenKhoAuto11").val()==""){
				vm.qLHDTKSearch.tinh=null;
			}
			if($("#maLenhXuatId").val()==""){
				vm.qLHDTKSearch.maTramTuyen=null;
			}
			if($("#loaiHD").val()=="0"){
				vm.qLHDTKSearch.ngayPtkTdXong=null;
			}
			
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		vm.importFile = function() {
			// vm.orderChangeGoodsDetailPop={};
			var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
			var title = "Import dữ liệu hợp đồng";
			var windowId = "IMPORT_DL_HD";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}
		
		vm.importFileTD = function(){
			var teamplateUrl = "qll/A_PTK-qLHDTK/addHDTdPopup.html";
			var title = "Import dữ liệu hợp đồng đã thẩm định";
			var windowId = "IMPORT_DL_HD_TD";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}
		//tải file excel lên
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
								+ RestEndpoint.KQ_HD_TK
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
		// tải file excel hợp đồng đã thẩm định
		vm.submitTD = submitTD;
		function submitTD() {
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
								+ RestEndpoint.KQ_HD_TK
								+ "/importFileTD?folder=" + vm.folder,
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
//		vm.downloadFile = function(dataItem){
//			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.pathImg;	
//    	}
		 
		 
		  vm.addHdPopSubmit = function(){
			  	vm.addHdPop={};
			  	vm.addHdPop.nguoiTao=$rootScope.casUser.fullName;
				var teamplateUrl="qll/A_PTK-qLHDTK/addHDPopup.html";
			    var title="Thêm mới hợp đồng";
			    var windowId="ADD_KQ_HD";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		  
		  vm.updateHdPopSubmit = function(dataItem){
			
			  	vm.addHdPop={};
			 	if(dataItem.checkTrinhKy==1){
			 		toastr.error("Bản ghi đã được thẩm định không thể sửa");
			 		return;
			 	}
			 	vm.addHdPop=dataItem;
			 	if(vm.addHdPop.gtQtCdtChuaVat!=null){
			  		vm.addHdPop.gtQtCdtChuaVat=kendo.toString(vm.addHdPop.gtQtCdtChuaVat, "#,###.00");
			  	}
			  	if(vm.addHdPop.gtQtCdtCoVat!=null){
				   vm.addHdPop.gtQtCdtCoVat=kendo.toString(vm.addHdPop.gtQtCdtCoVat, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpNhanCongDn!=null){
			  		 vm.addHdPop.cpNhanCongDn=kendo.toString(vm.addHdPop.cpNhanCongDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpVatLieuDn!=null){
			  		 vm.addHdPop.cpVatLieuDn=kendo.toString(vm.addHdPop.cpVatLieuDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpHshcDn!=null){
			  		 vm.addHdPop.cpHshcDn=kendo.toString(vm.addHdPop.cpHshcDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpVanChuyenDn!=null){
			  		 vm.addHdPop.cpVanChuyenDn=kendo.toString(vm.addHdPop.cpVanChuyenDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.chiPhiKhacDn!=null){
			  		 vm.addHdPop.chiPhiKhacDn=kendo.toString(vm.addHdPop.chiPhiKhacDn, "#,###.00");
			  	}
			  	
			  	if(vm.addHdPop.chiPhiLuongDn!=null){
					   vm.addHdPop.chiPhiLuongDn=kendo.toString(vm.addHdPop.chiPhiLuongDn, "#,###.00");
				  	}
				  	if(vm.addHdPop.vatDn!=null){
				  		 vm.addHdPop.vatDn=kendo.toString(vm.addHdPop.vatDn,"#,###.00");
				  	}
				  	if(vm.addHdPop.tongDn!=null){
				  		 vm.addHdPop.tongDn=kendo.toString(vm.addHdPop.tongDn, "#,###.00");
				  	}
				  	if(vm.addHdPop.tamUngLuong!=null){
				  		 vm.addHdPop.tamUngLuong=kendo.toString(vm.addHdPop.tamUngLuong, "#,###.00");
				  	}
				  	if(vm.addHdPop.cpVanChuyenDn!=null){
				  		 vm.addHdPop.cpVanChuyenDn=kendo.toString(vm.addHdPop.cpVanChuyenDn, "#,###.00");
				  	}
				var teamplateUrl="qll/A_PTK-qLHDTK/addHDPopup.html";
			    var title="Cập nhật hợp đồng";
			    var windowId="UPDATE_KQ_HD";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		  
		  vm.listRemove=[{
				title: "Thao tác",
			}]
		  //xuất tất cả dữ liệu hợp đồng ptk
		  vm.exportExcelGrid1 = function(){
				vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				
				qLHDTKService.exportAllHD(vm.oldSearch).then(function(result){
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
		  
		  vm.xuatBCTH = function(){
			  	vm.addHdPop={};
			  	var teamplateUrl="qll/A_PTK-qLHDTK/addUpdateHdTdPopup2.html";
			    var title="Xuất báo cáo tổng hợp";
			    var windowId="EX_BC_TH";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
			}
		  vm.checkChange=function(){
			  if(vm.addHdPop.ngayGuiPtkHshc!=null&&vm.addHdPop.ngayPtkTdXong!=null){
				  var date1 = kendo.parseDate(vm.addHdPop.ngayGuiPtkHshc, "dd/MM/yyyy");
				  	var date2 = kendo.parseDate(vm.addHdPop.ngayPtkTdXong, "dd/MM/yyyy");
				    var xxx=(date2.getTime()-date1.getTime())/(5*24*60*60*1000);
				    if(xxx>1){
				    	  vm.addHdPop.hsTonQua5n="Quá hạn";
				    }else{
				    	 vm.addHdPop.hsTonQua5n="Chưa quá hạn";
				    }
			  }
		  }
		  vm.checkTyLe=function(){
			  if(vm.addHdPop.gtTdPtkChuaVat!=null&&vm.addHdPop.gtQtCdtChuaVat!=null){
				  vm.addHdPop.tyLe=(vm.addHdPop.gtTdPtkChuaVat/vm.addHdPop.gtQtCdtChuaVat)*100; 
			  }
		  }
		  vm.check24H=false;
		  vm.updateHDTd = function(dataItem){
			  	if(!vm.roleememe&&dataItem.trangThai==="Đã duyệt PTK"){
			  		toastr.error("Hợp đồng đã được duyệt PTK");
			  		return;
			  	}
			  	vm.addHdPop={};
//			  	vm.addHdPopupdate={};
			  	
			  	vm.addHdPop=angular.copy(dataItem);
				vm.addHdPop.ngayTutttt=kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
				if(vm.addHdPop.nguoiCapNhat==null){
					vm.addHdPop.nguoiCapNhat=$rootScope.casUser.fullName;
				}
			 	var date2 = kendo.parseDate(d, "dd/MM/yyyy");
			  	var date1 = kendo.parseDate(vm.addHdPop.ngayCapNhat, "dd/MM/yyyy");
			  	if(vm.addHdPop.gtQtCdtChuaVat!=null){
			  		vm.addHdPop.gtQtCdtChuaVat=kendo.toString(vm.addHdPop.gtQtCdtChuaVat, "#,###.00");
			  	}
			  	if(vm.addHdPop.gtQtCdtCoVat!=null){
				   vm.addHdPop.gtQtCdtCoVat=kendo.toString(vm.addHdPop.gtQtCdtCoVat, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpNhanCongDn!=null){
			  		 vm.addHdPop.cpNhanCongDn=kendo.toString(vm.addHdPop.cpNhanCongDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpVatLieuDn!=null){
			  		 vm.addHdPop.cpVatLieuDn=kendo.toString(vm.addHdPop.cpVatLieuDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpHshcDn!=null){
			  		 vm.addHdPop.cpHshcDn=kendo.toString(vm.addHdPop.cpHshcDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.cpVanChuyenDn!=null){
			  		 vm.addHdPop.cpVanChuyenDn=kendo.toString(vm.addHdPop.cpVanChuyenDn, "#,###.00");
			  	}
			  	if(vm.addHdPop.chiPhiKhacDn!=null){
			  		 vm.addHdPop.chiPhiKhacDn=kendo.toString(vm.addHdPop.chiPhiKhacDn, "#,###.00");
			  	}
			  	
			  	if(vm.addHdPop.chiPhiLuongDn!=null){
					   vm.addHdPop.chiPhiLuongDn=kendo.toString(vm.addHdPop.chiPhiLuongDn, "#,###.00");
				  	}
				  	if(vm.addHdPop.vatDn!=null){
				  		 vm.addHdPop.vatDn=kendo.toString(vm.addHdPop.vatDn, "#,###.00");
				  	}
				  	if(vm.addHdPop.tongDn!=null){
				  		 vm.addHdPop.tongDn=kendo.toString(vm.addHdPop.tongDn, "#,###.00");
				  	}
				  	if(vm.addHdPop.tamUngLuong!=null){
				  		 vm.addHdPop.tamUngLuong=kendo.toString(vm.addHdPop.tamUngLuong, "#,###.00");
				  	}
				  	if(vm.addHdPop.cpVanChuyenDn!=null){
				  		 vm.addHdPop.cpVanChuyenDn=kendo.toString(vm.addHdPop.cpVanChuyenDn, "#,###.00");
				  	}
				  	if(vm.addHdPop.cpNhanCongTd!=null){
				  		 vm.addHdPop.cpNhanCongTd=kendo.toString(vm.addHdPop.cpNhanCongTd, "#,###.00");
				  	}
				  	
				  	///
					  	if(vm.addHdPop.cpVatLieuTd!=null){
					  		 vm.addHdPop.cpVatLieuTd=kendo.toString(vm.addHdPop.cpVatLieuTd, "#,###.00");
					  	}
					  	if(vm.addHdPop.cpHshcTd!=null){
					  		 vm.addHdPop.cpHshcTd=kendo.toString(vm.addHdPop.cpHshcTd, "#,###.00");
					  	}
					  	if(vm.addHdPop.cpVanChuyenTd!=null){
					  		 vm.addHdPop.cpVanChuyenTd=kendo.toString(vm.addHdPop.cpVanChuyenTd, "#,###.00");
					  	}
					  	if(vm.addHdPop.cpKhacTd!=null){
					  		 vm.addHdPop.cpKhacTd=kendo.toString(vm.addHdPop.cpKhacTd, "#,###.00");
					  	}
					  	if(vm.addHdPop.cpLuongTd!=null){
					  		 vm.addHdPop.cpLuongTd=kendo.toString(vm.addHdPop.cpLuongTd, "#,###.00");
					  	}
					  	//
						if(vm.addHdPop.vatTd!=null){
					  		 vm.addHdPop.vatTd=kendo.toString(vm.addHdPop.vatTd, "#,###.00");
					  	}
					  	if(vm.addHdPop.gtTdPtkChuaVat!=null){
					  		 vm.addHdPop.gtTdPtkChuaVat=kendo.toString(vm.addHdPop.gtTdPtkChuaVat, "#,###.00");
					  	}
					  	if(vm.addHdPop.gtTdPtkCoVat!=null){
					  		 vm.addHdPop.gtTdPtkCoVat=kendo.toString(vm.addHdPop.gtTdPtkCoVat, "#,###.00");
					  	}
					  	if(vm.addHdPop.tongTd!=null){
					  		 vm.addHdPop.tongTd=kendo.toString(vm.addHdPop.tongTd, "#,###.00");
					  	}
					  	if(vm.addHdPop.luongThucNhan!=null){
					  		 vm.addHdPop.luongThucNhan=kendo.toString(vm.addHdPop.luongThucNhan, "#,###.00");
					  	}
						if(vm.addHdPop.tyLe!=null){
					  		 vm.addHdPop.tyLe=kendo.toString(vm.addHdPop.tyLe, "#,###.00");
					  	}
						var tyLe1= vm.replaceVAlue(vm.addHdPop.gtTdPtkChuaVat);
		        		var tyLe2= vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
		        		if(vm.addHdPop.gtTdPtkChuaVat!=null&&vm.addHdPop.gtQtCdtChuaVat!=null){
							  vm.addHdPop.tyLe=(parseFloat(tyLe1)/parseFloat(tyLe2))*100; 
						  }
						
			
			 
				var teamplateUrl="qll/A_PTK-qLHDTK/addUpdateHdTdPopup.html";
			    var title="Thẩm định hợp đồng";
			    var windowId="TD_KQ_HD";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		  
		  vm.updateHDLuong = function(dataItem){
			  	vm.addHdPop=dataItem;
// if(vm.addHdPop.ngayGuiPtkHshc==null||vm.addHdPop.ngayPtkTdXong==null){
// toastr.error(gettextCatalog.getString("Bạn cần thẩm định trước khi tiếp
// tục!!!"));
// return;
// }
			  	vm.addHdPop.nguoiPheDuyetPtk=$rootScope.casUser.fullName;
			  	vm.addHdPop.tyLe=(vm.addHdPop.gtTdPtkChuaVat/vm.addHdPop.gtQtCdtChuaVat)*100;
			  	 vm.addHdPop.luongThucNhan=vm.addHdPop.cpLuongTd-vm.addHdPop.tamUngLuong;
			  	var date1 = kendo.parseDate(vm.addHdPop.ngayGuiPtkHshc, "dd/MM/yyyy");
			  	var date2 = kendo.parseDate(vm.addHdPop.ngayPtkTdXong, "dd/MM/yyyy");
			    var xxx=(date2.getTime()-date1.getTime())/(5*24*60*60*1000);
			    if(xxx>1){
			    	  vm.addHdPop.hsTonQua5n="Quá hạn";
			    }else{
			    	 vm.addHdPop.hsTonQua5n="Chưa quá hạn";
			    }
			  
				var teamplateUrl="qll/A_PTK-qLHDTK/addUpdateHdTTKPopup.html";
			    var title="Thêm mới thông tin hợp đồng";
			    var windowId="TM_TT_KQ_HD";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		  vm.listmess1="";
		  vm.listmess2="";
		  vm.updateHD = function(dataItem){
			  	vm.addHdPop=dataItem;
				var teamplateUrl="qll/A_PTK-qLHDTK/addHDPopup.html";
			    var title="Thêm mới hợp đồng";
			    var windowId="ADD_KQ_HD";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
//		  vm.sumLuongThucNhan=function(){
//			  if(vm.addHdPop.tamUngLuong!=null&&vm.addHdPop.cpLuongTd!=null){
//				  var x12= vm.replaceVAlue(vm.addHdPop.cpLuongTd);
//				  var x13= vm.replaceVAlue(vm.addHdPop.tamUngLuong);
//				  vm.addHdPop.luongThucNhan=parseFloat(x12)-parseFloat(x13);
//				  vm.addHdPop.luongThucNhan=kendo.toString(vm.addHdPop.luongThucNhan, "#,###.00");
//			  }
//			
//		  }
		  vm.replaceVAlue=function(value){
			  if(value!=null){
				  value=value.toString();
				  if(value!=null&&value.includes(',')){
					  value= value.replaceAll(',',"");
				  } 
			  }
			  return value;
		  }
		  
		  vm.totalDN=function(){
			  var x1="0";
			  var x2="0";
			  var x3="0";
			  var x4="0";
			  var x5="0";
			  var x6="0";
			  var x7="0";
			  if(vm.addHdPop.cpNhanCongDn===undefined||vm.addHdPop.cpNhanCongDn===""||vm.addHdPop.cpNhanCongDn==null){
				  vm.addHdPop.cpNhanCongDn="0";
				   x1="0";
			  }else{
				  x1= vm.replaceVAlue(vm.addHdPop.cpNhanCongDn);
			  }
			  if(vm.addHdPop.cpVatLieuDn===undefined||vm.addHdPop.cpVatLieuDn===""||vm.addHdPop.cpVatLieuDn==null){
				  vm.addHdPop.cpVatLieuDn="0";
				   x2="0";
			  }else{
				  x2= vm.replaceVAlue(vm.addHdPop.cpVatLieuDn);
			  }
			  if(vm.addHdPop.cpHshcDn===undefined||vm.addHdPop.cpHshcDn===""||vm.addHdPop.cpHshcDn==null){
				  vm.addHdPop.cpHshcDn="0";
				   x3="0";
			  }else{
				  x3= vm.replaceVAlue(vm.addHdPop.cpHshcDn);
			  }
			  if(vm.addHdPop.cpVanChuyenDn===undefined||vm.addHdPop.cpVanChuyenDn===""||vm.addHdPop.cpVanChuyenDn==null){
				  vm.addHdPop.cpVanChuyenDn="0";
				   x4="0";
			  }else{
				  x4= vm.replaceVAlue(vm.addHdPop.cpVanChuyenDn);
			  }
			  if(vm.addHdPop.chiPhiKhacDn===undefined||vm.addHdPop.chiPhiKhacDn===""||vm.addHdPop.chiPhiKhacDn==null){
				  vm.addHdPop.chiPhiKhacDn="0";
				   x5="0";
			  }else{
				  x5= vm.replaceVAlue(vm.addHdPop.chiPhiKhacDn);
			  }
			  if(vm.addHdPop.chiPhiLuongDn===undefined||vm.addHdPop.chiPhiLuongDn===""||vm.addHdPop.chiPhiLuongDn==null){
				  vm.addHdPop.chiPhiLuongDn="0";
				   x6="0";
			  }else{
				  x6= vm.replaceVAlue(vm.addHdPop.chiPhiLuongDn);
			  }
			  if(vm.addHdPop.vatDn===undefined||vm.addHdPop.vatDn===""||vm.addHdPop.vatDn==null){
				  vm.addHdPop.vatDn="0";
				   x7="0"
			  }else{
				  x7= vm.replaceVAlue(vm.addHdPop.vatDn);
			  }
			  vm.addHdPop.tongDn=parseFloat(x1)+parseFloat(x2)
					  				+ parseFloat(x3)+parseFloat(x4)
					  				+ parseFloat(x5)+parseFloat(x6)+parseFloat(x7);
			  vm.addHdPop.tongDn=kendo.toString(vm.addHdPop.tongDn, "#,###.00");
		  }
		  	var d = new Date();
	  		var n = d.getFullYear();
	  		vm.date=n;
	  		//tính VAT
	  		vm.changeVAT=function(){
	  			var xx11= vm.replaceVAlue(vm.addHdPop.gtTdPtkChuaVat);
	  			var xx12 = vm.replaceVAlue(vm.addHdPop.vatTd);
	  			var xx13= vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
        		vm.addHdPop.gtTdPtkCoVat=parseFloat(xx11)+parseFloat(xx12);
        		vm.addHdPop.gtTdPtkCoVat=kendo.toString(vm.addHdPop.gtTdPtkCoVat, "#,###.00");
        		vm.addHdPop.tongTd=kendo.toString(vm.addHdPop.gtTdPtkCoVat, "#,###.00");
        		if(vm.addHdPop.gtTdPtkChuaVat!=null||vm.addHdPop.gtQtCdtChuaVat!=null){
        			vm.addHdPop.tyLe=(parseFloat(xx11)/parseFloat(xx13))*100;
            		vm.addHdPop.tyLe=kendo.toString(vm.addHdPop.tyLe, "#,###.00");
        		}
        		
	  		}
	  		//tính giá trị thẩm định PTK chưa VAT
		  vm.totalTd=function(){
			  var x11="0";
			  var x12="0";
			  var x13="0";
			  var x14="0";
			  var x15="0";
			  var x16="0";
			  var x17="0";
			  var x18="0";
			  var x19="0";
		
			  if(vm.addHdPop.cpNhanCongTd===undefined||vm.addHdPop.cpNhanCongTd===""||vm.addHdPop.cpNhanCongTd==null){
				  vm.addHdPop.cpNhanCongTd=0;
			  }
			  else{
				  x11= vm.replaceVAlue(vm.addHdPop.cpNhanCongTd);
			  }
			  if(vm.addHdPop.cpVatLieuTd===undefined||vm.addHdPop.cpVatLieuTd===""||vm.addHdPop.cpVatLieuTd==null){
				  vm.addHdPop.cpVatLieuTd=0;
			  } else{
				  x12= vm.replaceVAlue(vm.addHdPop.cpVatLieuTd);
			  }
			  if(vm.addHdPop.cpHshcTd===undefined||vm.addHdPop.cpHshcTd===""||vm.addHdPop.cpHshcTd==null){
				  vm.addHdPop.cpHshcTd=0;
			  } else{
				  x13= vm.replaceVAlue(vm.addHdPop.cpHshcTd);
			  }
			  if(vm.addHdPop.cpVanChuyenTd===undefined||vm.addHdPop.cpVanChuyenTd===""||vm.addHdPop.cpVanChuyenTd==null){
				  vm.addHdPop.cpVanChuyenTd=0;
			  }else{
				  x14= vm.replaceVAlue(vm.addHdPop.cpVanChuyenTd);
			  }
			  if(vm.addHdPop.cpKhacTd===undefined||vm.addHdPop.cpKhacTd===""||vm.addHdPop.cpKhacTd==null){
				  vm.addHdPop.cpKhacTd=0;
			  }else{
				  x15= vm.replaceVAlue(vm.addHdPop.cpKhacTd);
			  }
			  if(vm.addHdPop.cpLuongTd===undefined||vm.addHdPop.cpLuongTd===""||vm.addHdPop.cpLuongTd==null){
				  vm.addHdPop.cpLuongTd=0;
			  }else{
				  x16= vm.replaceVAlue(vm.addHdPop.cpLuongTd);
			  }
			  if(vm.addHdPop.tamUngLuong===undefined||vm.addHdPop.tamUngLuong===""||vm.addHdPop.tamUngLuong==null){
				  vm.addHdPop.tamUngLuong=0;
			  }else{
				  x17= vm.replaceVAlue(vm.addHdPop.tamUngLuong);
			  }
			  if(vm.addHdPop.vatTd===undefined||vm.addHdPop.vatTd===""||vm.addHdPop.vatTd==null){
				  vm.addHdPop.vatTd=0;
			  }else{
				  x18= vm.replaceVAlue(vm.addHdPop.vatTd);
			  }
			  if(vm.addHdPop.gtQtCdtChuaVat===undefined||vm.addHdPop.gtQtCdtChuaVat===""||vm.addHdPop.gtQtCdtChuaVat==null){
				  vm.addHdPop.gtQtCdtChuaVat=0;
			  }else{
				  x19= vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
			  }

			  
			  if(vm.addHdPop.tamUngLuong!=null&&vm.addHdPop.cpLuongTd!=null){
				  var x16= vm.replaceVAlue(vm.addHdPop.cpLuongTd);
				  var x17= vm.replaceVAlue(vm.addHdPop.tamUngLuong);
				  vm.addHdPop.luongThucNhan=parseFloat(x16)-parseFloat(x17);
				  vm.addHdPop.luongThucNhan=kendo.toString(vm.addHdPop.luongThucNhan, "#,###.00");
			  }
			  
			  vm.addHdPop.gtTdPtkChuaVat = parseFloat(x11)+ parseFloat(x12) + parseFloat(x13) + parseFloat(x14) + parseFloat(x15) + parseFloat(x16);
			  vm.addHdPop.gtTdPtkChuaVat=kendo.toString(vm.addHdPop.gtTdPtkChuaVat, "#,###.00");
			  vm.addHdPop.gtTdPtkCoVat=parseFloat(x11)+parseFloat(x12)+parseFloat(x13)+parseFloat(x14)+parseFloat(x15)+parseFloat(x16)+parseFloat(x18);				
			  vm.addHdPop.gtTdPtkCoVat = kendo.toString(vm.addHdPop.gtTdPtkCoVat, "#,###.00");
			  vm.addHdPop.tongTd = kendo.toString(vm.addHdPop.gtTdPtkCoVat, "#,###.00");
			  vm.addHdPop.tyLe = ((parseFloat(x11)+ parseFloat(x12) + parseFloat(x13) + parseFloat(x14) + parseFloat(x15) + parseFloat(x16))/parseFloat(x19))*100;
			  vm.addHdPop.tyLe = kendo.toString(vm.addHdPop.tyLe, "#,###.00");
			 
		  }
		    
		    vm.save1 = function(){
		    	
		    	if(vm.addHdPop.gtQtCdtChuaVat!=null){
			  		vm.addHdPop.gtQtCdtChuaVat=vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
			  	}
			  	if(vm.addHdPop.gtQtCdtCoVat!=null){
				   vm.addHdPop.gtQtCdtCoVat=vm.replaceVAlue(vm.addHdPop.gtQtCdtCoVat);
			  	}
			  	if(vm.addHdPop.cpNhanCongDn!=null){
			  		 vm.addHdPop.cpNhanCongDn=vm.replaceVAlue(vm.addHdPop.cpNhanCongDn);
			  	}
			  	if(vm.addHdPop.cpVatLieuDn!=null){
			  		 vm.addHdPop.cpVatLieuDn=vm.replaceVAlue(vm.addHdPop.cpVatLieuDn);
			  	}
			  	if(vm.addHdPop.cpHshcDn!=null){
			  		 vm.addHdPop.cpHshcDn=vm.replaceVAlue(vm.addHdPop.cpHshcDn);
			  	}
			  	if(vm.addHdPop.cpVanChuyenDn!=null){
			  		 vm.addHdPop.cpVanChuyenDn=vm.replaceVAlue(vm.addHdPop.cpVanChuyenDn);
			  	}
			  	if(vm.addHdPop.chiPhiKhacDn!=null){
			  		 vm.addHdPop.chiPhiKhacDn=vm.replaceVAlue(vm.addHdPop.chiPhiKhacDn);
			  	}
			  	
			  	if(vm.addHdPop.chiPhiLuongDn!=null){
					   vm.addHdPop.chiPhiLuongDn=vm.replaceVAlue(vm.addHdPop.chiPhiLuongDn);
				  	}
				  	if(vm.addHdPop.vatDn!=null){
				  		 vm.addHdPop.vatDn=vm.replaceVAlue(vm.addHdPop.vatDn);
				  	}
				  	if(vm.addHdPop.tongDn!=null){
				  		 vm.addHdPop.tongDn=vm.replaceVAlue(vm.addHdPop.tongDn);
				  	}
				  	if(vm.addHdPop.tamUngLuong!=null){
				  		 vm.addHdPop.tamUngLuong=vm.replaceVAlue(vm.addHdPop.tamUngLuong);
				  	}
				  	if(vm.addHdPop.cpVanChuyenDn!=null){
				  		 vm.addHdPop.cpVanChuyenDn=vm.replaceVAlue(vm.addHdPop.cpVanChuyenDn);
				  	}
				  	if(vm.addHdPop.cpNhanCongTd!=null){
				  		 vm.addHdPop.cpNhanCongTd=vm.replaceVAlue(vm.addHdPop.cpNhanCongTd);
				  	}
				  	
				  	///
					if(vm.addHdPop.chiPhiLuongDn!=null){
						   vm.addHdPop.chiPhiLuongDn=vm.replaceVAlue(vm.addHdPop.chiPhiLuongDn);
					  	}
					  	if(vm.addHdPop.cpVatLieuTd!=null){
					  		 vm.addHdPop.cpVatLieuTd=vm.replaceVAlue(vm.addHdPop.cpVatLieuTd);
					  	}
					  	if(vm.addHdPop.cpHshcTd!=null){
					  		 vm.addHdPop.cpHshcTd=vm.replaceVAlue(vm.addHdPop.cpHshcTd);
					  	}
					  	if(vm.addHdPop.cpVanChuyenTd!=null){
					  		 vm.addHdPop.cpVanChuyenTd=vm.replaceVAlue(vm.addHdPop.cpVanChuyenTd);
					  	}
					  	if(vm.addHdPop.cpKhacTd!=null){
					  		 vm.addHdPop.cpKhacTd=vm.replaceVAlue(vm.addHdPop.cpKhacTd);
					  	}
					  	if(vm.addHdPop.cpLuongTd!=null){
					  		 vm.addHdPop.cpLuongTd=vm.replaceVAlue(vm.addHdPop.cpLuongTd);
					  	}
					  	//
						if(vm.addHdPop.vatTd!=null){
					  		 vm.addHdPop.vatTd=vm.replaceVAlue(vm.addHdPop.vatTd);
					  	}
					  	if(vm.addHdPop.gtTdPtkChuaVat!=null){
					  		 vm.addHdPop.gtTdPtkChuaVat=vm.replaceVAlue(vm.addHdPop.gtTdPtkChuaVat);
					  	}
					  	if(vm.addHdPop.gtTdPtkCoVat!=null){
					  		 vm.addHdPop.gtTdPtkCoVat=vm.replaceVAlue(vm.addHdPop.gtTdPtkCoVat);
					  	}
					  	if(vm.addHdPop.tongTd!=null){
					  		 vm.addHdPop.tongTd=vm.replaceVAlue(vm.addHdPop.tongTd);
					  	}
					  	if(vm.addHdPop.luongThucNhan!=null){
					  		 vm.addHdPop.luongThucNhan=vm.replaceVAlue(vm.addHdPop.luongThucNhan);
					  	}
						if(vm.addHdPop.tyLe!=null){
					  		 vm.addHdPop.tyLe=vm.replaceVAlue(vm.addHdPop.tyLe);
					  	}
		    	
		    	
		    	if(vm.addHdPop.kqHdTkId==null){
		    		vm.addHdPop.nguoiTao=$rootScope.casUser.employeeCode;
		    		vm.addHdPop.trangThai="Mới tạo";
		    		if(vm.addHdPop.ngayThiCong==null){
	    				toastr.error("Ngày thi công xong không được để trống");
	    				return;
	    			};
		    		qLHDTKService.insertHd(vm.addHdPop).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			
		    			vm.addHdPop={};
		    			
		    			toastr.success("Thêm hợp đồng thành công!");
		    			$("#qLHDTKGrid").data('kendoGrid').dataSource.read();
		    			$("#qLHDTKGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		    		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
		            });
		    	}else{
		    		if(vm.roleememe||vm.roleememeTD){
		    			if(vm.roleememe){
			    			vm.addHdPop.trangThai="Đã duyệt PTK";
			    			vm.addHdPop.nguoiPheDuyetPtk=$rootScope.casUser.fullName;
			    		}else{
			    			vm.addHdPop.trangThai="Đã thẩm định";
			    			vm.addHdPop.nguoiCapNhat=$rootScope.casUser.fullName;
			    		}
			    		
			    		if(vm.addHdPop.ngayGuiPtkHshc==null){
			    			toastr.error("Ngày nhận Ptk gửi HSHC không được để trống ");
		    				return;
			    		}
			    		vm.addHdPop.checkTrinhKy=1;
			    		qLHDTKService.updateHdTd(vm.addHdPop).then(function(result){
			    			if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			
			    			if(vm.addHdPop.ngayPtkTdXong==null){
			    				var date1= kendo.parseDate(vm.addHdPop.ngayGuiPtkHshc,"dd/MM/yyyy");
			    				
			    				var date2 = kendo.parseDate(d, "dd/MM/yyyy");
			    				var xxx=(date2.getTime() - date1.getTime())/(5*24*60*60*1000);
							    if(xxx>1){
							    	  vm.addHdPop.hsTonQua5n="Quá hạn";
							    }else{
							    	 vm.addHdPop.hsTonQua5n="Chưa quá hạn";
							    }
			    			}
			    			if(vm.roleememe){
			    				toastr.success("Duyệt hợp đồng thành công!");
			    			}else{
			    				toastr.success("Thẩm định hợp đồng thành công!");
			    			}
			    			
			    			  var sizePage = $("#qLHDTKGrid").data("kendoGrid").dataSource.total();
			    			  var pageSize = $("#qLHDTKGrid").data("kendoGrid").dataSource.pageSize();
			    					if(sizePage % pageSize === 1){
			    								var currentPage = $("#qLHDTKGrid").data("kendoGrid").dataSource.page();
			    								if (currentPage > 1) {
			    									$("#qLHDTKGrid").data("kendoGrid").dataSource.page(currentPage - 1);
			    								}
			    							}
			    			$("#qLHDTKGrid").data('kendoGrid').dataSource.read();
			    			$("#qLHDTKGrid").data('kendoGrid').refresh();
			    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thẩm định"));
			            });
		    			
		    		}else{
		    		
		    		
		    		qLHDTKService.updateHdPopSubmit(vm.addHdPop).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			vm.addHdPop={};
		    			
		    			toastr.success("Cập nhật hợp đồng thành công!");
		    			  var sizePage = $("#qLHDTKGrid").data("kendoGrid").dataSource.total();
		    			  var pageSize = $("#qLHDTKGrid").data("kendoGrid").dataSource.pageSize();
		    					if(sizePage % pageSize === 1){
		    								var currentPage = $("#qLHDTKGrid").data("kendoGrid").dataSource.page();
		    								if (currentPage > 1) {
		    									$("#qLHDTKGrid").data("kendoGrid").dataSource.page(currentPage - 1);
		    								}
		    							}
		    			$("#qLHDTKGrid").data('kendoGrid').dataSource.read();
		    			$("#qLHDTKGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		    		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
		            });
		    		};
		    	
		    		
		    		
		    		
		    	}
		    	
			}
		  
		
		    vm.removeAny=function(){
		    	
		    	vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				
			if(vm.qLHDTKSearch.soHd || vm.qLHDTKSearch.tinh || vm.qLHDTKSearch.soKhTc || vm.qLHDTKSearch.maTramTuyen){
							confirm("Bạn có muốn xóa!", function() {
								qLHDTKService.deleteListObj(vm.oldSearch).then(function(result){
									if(result.error){
					    				toastr.error(result.error);
					    				return;
					    			}
					    			toastr.success("Xóa hợp đồng thành công!");
					    			  var sizePage = $("#qLHDTKGrid").data("kendoGrid").dataSource.total();
					    			  var pageSize = $("#qLHDTKGrid").data("kendoGrid").dataSource.pageSize();
					    					if(sizePage % pageSize === 1){
					    								var currentPage = $("#qLHDTKGrid").data("kendoGrid").dataSource.page();
					    								if (currentPage > 1) {
					    									$("#qLHDTKGrid").data("kendoGrid").dataSource.page(currentPage - 1);
					    								}
					    							}
					    			$("#qLHDTKGrid").data('kendoGrid').dataSource.read();
					    			$("#qLHDTKGrid").data('kendoGrid').refresh();
					    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
								},function(errResponse){
					                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thẩm định"));
					            });
							})
					
				
			}else{
				toastr.error(gettextCatalog.getString("Chưa tìm kiếm bản ghi cần xóa"));
			}
				
		    }
		    
		    vm.coppyVl=function(){
		    	vm.addHdPop.cpNhanCongTd=vm.addHdPop.cpNhanCongDn;
		    	vm.addHdPop.cpVatLieuTd=vm.addHdPop.cpVatLieuDn;
		    	vm.addHdPop.cpHshcTd=vm.addHdPop.cpHshcDn;
		    	vm.addHdPop.cpVanChuyenTd=vm.addHdPop.cpVanChuyenDn;
		    	vm.addHdPop.cpKhacTd=vm.addHdPop.chiPhiKhacDn;
		    	vm.addHdPop.cpLuongTd=vm.addHdPop.chiPhiLuongDn;
		    	vm.addHdPop.vatTd=vm.addHdPop.vatDn;
		    	vm.addHdPop.tongTd=vm.addHdPop.gtQtCdtCoVat;
		    	vm.addHdPop.gtTdPtkChuaVat=vm.addHdPop.gtQtCdtChuaVat;
		    	vm.addHdPop.gtTdPtkCoVat=vm.addHdPop.gtQtCdtCoVat;
		    	var tyLe1= vm.replaceVAlue(vm.addHdPop.gtTdPtkChuaVat);
        		var tyLe2= vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
		    	if(vm.addHdPop.gtTdPtkChuaVat!=null&&vm.addHdPop.gtQtCdtChuaVat!=null){
					  vm.addHdPop.tyLe=(parseFloat(tyLe1)/parseFloat(tyLe2))*100; 
		    		}
		    }
		    
		    vm.deny=function(){
		    	
		    	
		    	if(vm.addHdPop.lyDoTc==null||vm.addHdPop.lyDoTc===""){
					$("#descriptxx").focus();
					toastr.error("Bạn cần nhập lý do từ chối!!!");
					return;
				}	
		    	if(vm.addHdPop.gtQtCdtChuaVat!=null){
			  		vm.addHdPop.gtQtCdtChuaVat=vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
			  	}
			  	if(vm.addHdPop.gtQtCdtCoVat!=null){
				   vm.addHdPop.gtQtCdtCoVat=vm.replaceVAlue(vm.addHdPop.gtQtCdtCoVat);
			  	}
			  	if(vm.addHdPop.cpNhanCongDn!=null){
			  		 vm.addHdPop.cpNhanCongDn=vm.replaceVAlue(vm.addHdPop.cpNhanCongDn);
			  	}
			  	if(vm.addHdPop.cpVatLieuDn!=null){
			  		 vm.addHdPop.cpVatLieuDn=vm.replaceVAlue(vm.addHdPop.cpVatLieuDn);
			  	}
			  	if(vm.addHdPop.cpHshcDn!=null){
			  		 vm.addHdPop.cpHshcDn=vm.replaceVAlue(vm.addHdPop.cpHshcDn);
			  	}
			  	if(vm.addHdPop.cpVanChuyenDn!=null){
			  		 vm.addHdPop.cpVanChuyenDn=vm.replaceVAlue(vm.addHdPop.cpVanChuyenDn);
			  	}
			  	if(vm.addHdPop.chiPhiKhacDn!=null){
			  		 vm.addHdPop.chiPhiKhacDn=vm.replaceVAlue(vm.addHdPop.chiPhiKhacDn);
			  	}
			  	
			  	if(vm.addHdPop.chiPhiLuongDn!=null){
					   vm.addHdPop.chiPhiLuongDn=vm.replaceVAlue(vm.addHdPop.chiPhiLuongDn);
				  	}
				  	if(vm.addHdPop.vatDn!=null){
				  		 vm.addHdPop.vatDn=vm.replaceVAlue(vm.addHdPop.vatDn);
				  	}
				  	if(vm.addHdPop.tongDn!=null){
				  		 vm.addHdPop.tongDn=vm.replaceVAlue(vm.addHdPop.tongDn);
				  	}
				  	if(vm.addHdPop.tamUngLuong!=null){
				  		 vm.addHdPop.tamUngLuong=vm.replaceVAlue(vm.addHdPop.tamUngLuong);
				  	}
				  	if(vm.addHdPop.cpVanChuyenDn!=null){
				  		 vm.addHdPop.cpVanChuyenDn=vm.replaceVAlue(vm.addHdPop.cpVanChuyenDn);
				  	}
				  	if(vm.addHdPop.cpNhanCongTd!=null){
				  		 vm.addHdPop.cpNhanCongTd=vm.replaceVAlue(vm.addHdPop.cpNhanCongTd);
				  	}
				  	
				  	///
					if(vm.addHdPop.chiPhiLuongDn!=null){
						   vm.addHdPop.chiPhiLuongDn=vm.replaceVAlue(vm.addHdPop.chiPhiLuongDn);
					  	}
					  	if(vm.addHdPop.cpVatLieuTd!=null){
					  		 vm.addHdPop.cpVatLieuTd=vm.replaceVAlue(vm.addHdPop.cpVatLieuTd);
					  	}
					  	if(vm.addHdPop.cpHshcTd!=null){
					  		 vm.addHdPop.cpHshcTd=vm.replaceVAlue(vm.addHdPop.cpHshcTd);
					  	}
					  	if(vm.addHdPop.cpVanChuyenTd!=null){
					  		 vm.addHdPop.cpVanChuyenTd=vm.replaceVAlue(vm.addHdPop.cpVanChuyenTd);
					  	}
					  	if(vm.addHdPop.cpKhacTd!=null){
					  		 vm.addHdPop.cpKhacTd=vm.replaceVAlue(vm.addHdPop.cpKhacTd);
					  	}
					  	if(vm.addHdPop.cpLuongTd!=null){
					  		 vm.addHdPop.cpLuongTd=vm.replaceVAlue(vm.addHdPop.cpLuongTd);
					  	}
					  	//
						if(vm.addHdPop.vatTd!=null){
					  		 vm.addHdPop.vatTd=vm.replaceVAlue(vm.addHdPop.vatTd);
					  	}
					  	if(vm.addHdPop.gtTdPtkChuaVat!=null){
					  		 vm.addHdPop.gtTdPtkChuaVat=vm.replaceVAlue(vm.addHdPop.gtTdPtkChuaVat);
					  	}
					  	if(vm.addHdPop.gtTdPtkCoVat!=null){
					  		 vm.addHdPop.gtTdPtkCoVat=vm.replaceVAlue(vm.addHdPop.gtTdPtkCoVat);
					  	}
					  	if(vm.addHdPop.tongTd!=null){
					  		 vm.addHdPop.tongTd=vm.replaceVAlue(vm.addHdPop.tongTd);
					  	}
					  	if(vm.addHdPop.luongThucNhan!=null){
					  		 vm.addHdPop.luongThucNhan=vm.replaceVAlue(vm.addHdPop.luongThucNhan);
					  	}
						if(vm.addHdPop.tyLe!=null){
					  		 vm.addHdPop.tyLe=vm.replaceVAlue(vm.addHdPop.tyLe);
					  	}
		    	vm.addHdPop.checkTrinhKy=0;
		    	 vm.addHdPop.trangThai="Đã từ chối";
	    		qLHDTKService.updateHdTd(vm.addHdPop).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			vm.addHdPop={};
	    			toastr.success("Từ chối hợp đồng thành công!");
	    			  var sizePage = $("#qLHDTKGrid").data("kendoGrid").dataSource.total();
	    			  var pageSize = $("#qLHDTKGrid").data("kendoGrid").dataSource.pageSize();
	    					if(sizePage % pageSize === 1){
	    								var currentPage = $("#qLHDTKGrid").data("kendoGrid").dataSource.page();
	    								if (currentPage > 1) {
	    									$("#qLHDTKGrid").data("kendoGrid").dataSource.page(currentPage - 1);
	    								}
	    							}
	    			$("#qLHDTKGrid").data('kendoGrid').dataSource.read();
	    			$("#qLHDTKGrid").data('kendoGrid').refresh();
	    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thẩm định"));
	            });
		    }
		    
		    
			vm.prepareDataforSaving = function(){
				vm.updateSLNV113.lstThucXuat = [];
				var dataGoodFromGrid = $('#updateSlGrid').data("kendoGrid").dataSource.data();
			
				for(var i = 0; i<dataGoodFromGrid.length;i++){
					vm.updateSLNV113.lstThucXuat.push(dataGoodFromGrid[i]);
				}
			}
			
			 vm.patternOptionsTinh={
		    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã tỉnh hoặc tên tên",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.qLHDTKSearch.tinh=dataItem.maDanhMuc;
		                    vm.qLHDTKSearch.tenTinh=dataItem.tenDanhMuc;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#kHuyenKhoAuto11").val().trim()}).then(function(response){
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
		                	if(processSearch('kHuyenKhoAuto11',vm.selectedPrpject)){
		    					 $('#kHuyenKhoAuto11').val("");
		    					 vm.qLHDTKSearch.tenTinh="";
		    					 vm.qLHDTKSearch.tinh="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
			 
			 vm.patternOptionsTinhPop={
		    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã tỉnh hoặc tên tên",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.addHdPop.tinh=dataItem.maDanhMuc;
		                    vm.addHdPop.tenTinh=dataItem.tenDanhMuc;
// $('#kHuyenKhoAuto11').val(dataItem.tenDanhMuc);
		// vm.selectedPrpject=true;
		                   
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#kHuyenKhoAuto11Pop").val().trim()}).then(function(response){
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
		                	if(processSearch('kHuyenKhoAuto11Pop',vm.selectedPrpject)){
		    					 $('#kHuyenKhoAuto11Pop').val("");
		    					 vm.addHdPop.tenTinh="";
		    					 vm.addHdPop.tinh="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
			 
			 vm.patternOptionsSHD={
		    			dataTextField: "soHd", placeholder:"Nhập số hợp đồng",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.exportHD.soHd=dataItem.soHd;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("kqHdTkRsServiceRest/getForAutoCompleteHD").post({pageSize:10, page:1, soHd:$("#soHdxx").val().trim()}).then(function(response){
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
		                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.soHd #</div></div>',
		                change: function(e) {
		                	if(processSearch('soHdxx',vm.selectedPrpject)){
		    					 $('#soHdxx').val("");
		    					 vm.exportHD.soHd="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
			
			 vm.patternOptionsSHDPr={
		    			dataTextField: "soHd", placeholder:"Nhập số hợp đồng",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.qLHDTKSearch.soHd=dataItem.soHd;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("kqHdTkRsServiceRest/getForAutoCompleteHD").post({pageSize:10, page:1, soHd:$("#soHd11").val().trim()}).then(function(response){
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
		                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.soHd #</div></div>',
		                change: function(e) {
		                	if(processSearch('soHd11',vm.selectedPrpject)){
		    					 $('#soHd11').val("");
		    					 vm.qLHDTKSearch.soHd="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
			 
			 vm.patternOptionsSKHTC={
		    			dataTextField: "soKhTc", placeholder:"Nhập số kế hoạc thi công",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.qLHDTKSearch.soKhTc=dataItem.soKhTc;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("kqHdTkRsServiceRest/getForAutoCompleteKHTC").post({pageSize:10, page:1, soKhTc:$("#soKhTc11").val().trim()}).then(function(response){
		                                options.success(response);
		                            }).catch(function (err) {
		                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                            });
		                        }
		                    }
		                },
		                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
		                '<p class="col-md-12 text-header-auto">Số kế hoạch</p>' +
		                	'</div>',
		                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.soKhTc #</div></div>',
		                change: function(e) {
		                	if(processSearch('soKhTc11',vm.selectedPrpject)){
		    					 $('#soKhTc11').val("");
		    					 vm.qLHDTKSearch.soKhTc="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		       
			 
		        vm.exportExcelGrid= function(){
		        	if(vm.exportHD1.soHd==null||vm.exportHD1.soHd===""){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập số hợp đồng"));
		        		return;
		        	}
					vm.exportHD1.page=null;
					vm.exportHD1.pageSize=null;
					qLHDTKService.exportExcelGrid(vm.exportHD1).then(function(result){
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ data.fileName;
							vm.doSearch();
							// return;
						} 

					});	
					// toastr.success("hello !!!");
				}
		        
		        
// vm.exportTH= function(){
// if(vm.exportHD.ngayGuiPtkHshc==null&&vm.exportHD.thangHshcQuyLuong==null){
// toastr.error(gettextCatalog.getString("Bạn cần nhập ngày hoặc tháng"));
// return;
// }
// vm.exportHD.page=null;
// vm.exportHD.pageSize=null;
// qLHDTKService.exportExcelGrid(vm.exportHD).then(function(result){
// if (result.fileName) {
// toastr.success("Xuất file thành công!");
// window.location = Constant.BASE_SERVICE_URL
// + "fileservice/downloadFileATTT?"
// + data.fileName;
// vm.doSearch();
// // return;
// }
//
// });
// // toastr.success("hello !!!");
// }
		        vm.xuatBCLuongPop = function(dataItem){
					var teamplateUrl="qll/A_PTK-qLHDTK/addUpdateHdTdPopup3.html";
				    var title="Xuất báo cáo lương theo tháng";
				    var windowId="EX_SLY";
				    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','40%');
				}
		        
		        vm.xuatBCLuong= function(){
		        	if(vm.exportLuongHD.thangGhiNhanQuyLuongTqt==""){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập tháng xuất quỹ lương"));
		        		return;
		        	}
					qLHDTKService.xuatBCLuong(vm.exportLuongHD).then(function(result){
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ data.fileName;
							vm.doSearch();
							// return;
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
						} 

					});	
					// toastr.success("hello !!!");
				}
		        
		        vm.xuatBCTHCon= function(){
		        	if(vm.exportHD.ngayGuiPtkHshcTo==null&&vm.exportHD.ngayGuiPtkHshcFrom==null&&vm.exportHD.thangGhiNhanQuyLuongTqt==null){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập ngày hoặc tháng"));
		        		return;
		        	}else if(vm.exportHD.ngayGuiPtkHshcTo!=null&&vm.exportHD.ngayGuiPtkHshcFrom==null){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập ngày từ"));
		        		return;
		        	}
		        	
					qLHDTKService.xuatBCTH(vm.exportHD).then(function(result){
					
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ data.fileName;
// vm.doSearch();
			    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
							// return;
						} 

					});	
					// toastr.success("hello !!!");
				}
		        
	        vm.changeCVat=function(){
	        		var xx= vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
	        		vm.addHdPop.gtQtCdtCoVat=parseFloat(xx)*1.1;
	        		vm.addHdPop.gtQtCdtCoVat=kendo.toString(vm.addHdPop.gtQtCdtCoVat, "#,###.00");
	        		var tyLe1= vm.replaceVAlue(vm.addHdPop.gtTdPtkChuaVat);
	        		var tyLe2= vm.replaceVAlue(vm.addHdPop.gtQtCdtChuaVat);
	        		if(vm.addHdPop.gtTdPtkChuaVat!=null&&vm.addHdPop.gtQtCdtChuaVat!=null){
						  vm.addHdPop.tyLe=(parseFloat(tyLe1)/parseFloat(tyLe2))*100; 
					  }
	        }
	        vm.changeKVat=function(){
	        		var xx= vm.replaceVAlue(vm.addHdPop.gtQtCdtCoVat);
	        		vm.addHdPop.gtQtCdtChuaVat= parseFloat(xx)/1.1;
	        		vm.addHdPop.gtQtCdtChuaVat=kendo.toString(vm.addHdPop.gtQtCdtChuaVat, "#,###.00");
	        }
	        
	        vm.changeKVatTd=function(){
	        	var xxgtTdPtkCoVat= vm.replaceVAlue(vm.addHdPop.gtTdPtkCoVat);
	        	var xxvatTd = vm.replaceVAlue(vm.addHdPop.vatTd);
        		vm.addHdPop.gtTdPtkChuaVat=parseFloat(xxgtTdPtkCoVat)- parseFloat(xxvatTd);
        		vm.addHdPop.gtTdPtkChuaVat=kendo.toString(vm.addHdPop.gtTdPtkChuaVat, "#,###.00");
        		vm.addHdPop.tongTd=parseFloat(xxgtTdPtkCoVat);
        		vm.addHdPop.tongTd=kendo.toString(vm.addHdPop.tongTd, "#,###.00");
	        }
	        vm.changetongtdgtTd=function(){
	        	var xxtongTd = vm.replaceVAlue(vm.addHdPop.tongTd);
	        	var xxvatTd = vm.replaceVAlue(vm.addHdPop.vatTd);
	        	vm.addHdPop.gtTdPtkChuaVat=parseFloat(xxtongTd)- parseFloat(xxvatTd);
	        	vm.addHdPop.gtTdPtkChuaVat=kendo.toString(vm.addHdPop.gtTdPtkChuaVat, "#,###.00");
	        	vm.addHdPop.gtTdPtkCoVat=parseFloat(xxtongTd);
	        	vm.addHdPop.gtTdPtkCoVat=kendo.toString(vm.addHdPop.gtTdPtkCoVat, "#,###.00");
	        }
	        
	        vm.fomatNumber=function(data){
	        	vm.addHdPop.cpNhanCongDn=kendo.toString(data, "#,###.00");
	        }
	        
	        
	        vm.objUpload={};
	        vm.uploadFile = function(dataItem){
				var teamplateUrl="qll/quanLyVatTuACap/importDvPXK/upLoad.html";
			    var title="Tải lên ảnh đại diện";
			    var windowId="UPLOAD_IMAGE";
			    vm.objUpload=dataItem;
			    vm.dataFile = [];
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','40%');
			}
		    
		    
		    
		    vm.cancel11=function(){
// $("#qLHDTKGrid").data('kendoGrid').refresh();
	        	$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
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
	        	qLHDTKService.updateSLByNV(vm.updateSLNV113).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Từ chối thành công!");
	    			$("#qLHDTKGrid").data('kendoGrid').dataSource.read();
	    			$("#qLHDTKGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		    
		    
		    
		    
		
		
// // Chọn file tải lên trong màn hình thêm mới
// vm.onSelect = function(e) {
// if($("#files")[0].files[0].name.split('.').pop() !='png' &&
// $("#files")[0].files[0].name.split('.').pop() !='jpg'){
// toastr.warning("Sai định dạng file");
// setTimeout(function() {
// $(".k-upload-files.k-reset").find("li").remove();
// $(".k-upload-files").remove();
// $(".k-upload-status").remove();
// $(".k-upload.k-header").addClass("k-upload-empty");
// $(".k-upload-button").removeClass("k-state-focused");
// },10);
// return;
// }
// var formData = new FormData();
// jQuery.each(jQuery('#files')[0].files, function(i, file) {
// formData.append('multipartFile'+i, file);
// });
//				
//				
//			 
// return $.ajax({
// url:Constant.BASE_SERVICE_URL+"fileservice/uploadATTT",
// type: "POST",
// data: formData,
// enctype: 'multipart/form-data',
// processData: false,
// contentType: false,
// cache: false,
// success:function(data) {
// $.map(e.files, function(file,index) {
// var obj={};
// obj.name=file.name;
// obj.filePath=data[index];
// vm.objUpload.pathImg=obj.filePath;
// vm.dataFile.push(obj);
// qLHDTKService.updatePathImg(vm.objUpload).then(
// function(result) {
// if(result.error){
// toastr.error(result.error);
// return;
// }
// toastr.success("Tải ảnh lên thành công!");
// $("#qLHDTKGrid").data('kendoGrid').dataSource.read();
// $("#qLHDTKGrid").data('kendoGrid').refresh();
// $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
// }, function(errResponse){
// toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
// });
//				 
// })
// }
// });
// }
//		
		
		
		vm.downloadImportTemplate = function(){
			var fileName="import_Hd_Dn";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}
		
		vm.downloadImportTemplateTd = function(){
			var fileName="import_Hd_Dn_TD";
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
				vm.qLHDTKGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.qLHDTKGrid.showColumn(column);
			} else {
				vm.qLHDTKGrid.hideColumn(column);
			}

		}

	}

})();
