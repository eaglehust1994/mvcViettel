(function() {
	'use strict';

	angular.module('MetronicApp').constant('Constant', Config());

	/* @ngInject */
	function Config() {
		var config = {};
		config.authen={
				LOGIN_URL:"auth/kttsAuthenRsService/login",
				LOGOUT_URL:"auth/kttsAuthenRsService/logout",
				GET_USER_INFO:'auth/kttsAuthenRsService/getUserInfo'
				
			}	
		/***********************************************************************
		 * HTTP STATUS
		 **********************************************************************/
		config.http = {
			SUCCESS : 0,
			ERROR : 1,
			BUSINESS_ERROR : 400
		};
				config.pageSize = 20,
				config.pageSizes = [ 10, 15, 20, 25 ],

				/**
				 * Thêm cấu hình các white list url không cần add version
				 */
				config.LIST_WHITE_LIST_VERSION_URL = [
						"template/tabs/tab.html", "template/tabs/tabset.html",
						"template/tooltip/tooltip-html-popup.html" ],
				config.inWhiteListAddVersion = function(url) {
					if (url.startsWith(config.BASE_SERVICE_URL)) {
						return true;
					}
					if (url.startsWith('template/')) {
						return true;
					}
					if (url.indexOf('?tsVersion=')) {
						return true;
					}
					for ( var str in config.LIST_WHITE_LIST_VERSION_URL) {
						if (url
								.indexOf(config.LIST_WHITE_LIST_VERSION_URL[str]) >= 0) {
							return true;
						}
					}
				}

		config.ROLE_ID = {
			employee_roleID_CDT_PTGST : 102,// 4 - RoleID 102
			employee_roleID_CDT_DDPN : 103,// 2 - RoleID 103
			employee_roleID_DT_KTTC : 104,// 5 - RoleID 104
			employee_roleID_DT_GDNT : 105,// 3 - RoleID 105
			employee_roleID_DT_PTTC : 106,// 1 - RoleID 106
			employee_roleID_TVTK_DDTV : 107,// 6 - RoleID 107
			employee_roleID_TVTK_CNTK : 108,// 7 - RoleID 108
			employee_roleID_TVGS_GSTC : 109, // 0 - RoleID 109
			employee_roleID_TVGS_PTGST : 110,// 8 - RoleID 110
			employee_roleID_TVGS_DDTVGS : 111,// 9 - RoleID 111
			employee_roleID_CDT_GSTC : 101,// 10 - RoleID 101
			employee_roleID_CDT_DDDVSDCT : 112
		// 11 - RoleID 112
		}

		config.BASE_SERVICE_URL = 'http://localhost:8084/qll-service/';
		/**
		 * qll
		 */
	//	config.BASE_SERVICE_URL = 'http://10.61.19.199:8086/qll-service/';
		/**
		 * qlts
		 */
		
//		config.BASE_SERVICE_URL = 'http://10.61.19.199:8085/qll-service/';
//		config.BASE_SERVICE_URL = 'http://10.58.71.134:8787/qll-service/';
//		config.BASE_SERVICE_URL = 'http://10.58.71.134:8352/qll-service/';
//		 config.BASE_SERVICE_URL = 'http://10.58.71.134:8282/ktts-service/';
		// config.FILE_SERVICE = 'fileservice/uploadATTT';
		// config.DOWNLOAD_SERVICE =
		// '/ktts-service/fileservice/downloadFileATTT?';
		config.FILE_SERVICE_TEMP = 'fileservice/uploadTemp';
		// config.UPLOAD_RS_SERVICE='fileUpload/uploadATTT';
		config.UPLOAD_RS_SERVICE = 'fileservice/uploadATTT';
		config.FILE_SERVICE = 'fileservice/uploadATTT';
		config.DOWNLOAD_SERVICE = API_URL + 'fileservice/downloadFileATTT?';
		config.DOWNLOAD_SERVICE2 = API_URL + 'fileservice/downloadFileATTT2?';
		config.contextPath = "wms-web";
		config.prefixLanguage = 'js/languages/',
				config.loginUrl = 'authenServiceRest/login';
		config.getUser = function() {
			return this.user;
		}
		
		
		
		config.setUser = function(user) {
			this.user = user;
			config.userInfo=this.user;
		}

		
		
		config.URL_POPUP = {
			DELETE_POPUP : 'wms/popup/Delete_Popup.html',
			VOFICE_POPUP : 'wms/popup/SignVofficePopup.html',
		}

		config.COLUMS_VALIDATE={
				goods:[
				       {
				    	   colum:'Mã hàng',
				    	   field:'code',
				    	   dataType:"Text"
				       },
				       {
				    	   colum:'Tên hàng',
				    	   field:'name',
				    	   dataType:"Text"
				       },
				       {
				    	   colum:'Đơn vị tính',
				    	   field:'unit',
				    	   dataType:"Text"
				       },
				       {
				    	   colum:'Số lượng',
				    	   field:'qty',
				    	   dataType:"Number"
				       }
				       ]
		};
		config.TEMPLATE_URL = [
				{
					key : 'HCQT_CATEGORY',
					title : 'Danh mục'

				},
				{
					key : 'DASH_BOARD',
					title : 'Home',
					templateUrl : 'views/dashboard.html',
				},
				{
					key : 'Test-Control',
					title : 'Test các chức năng',
					templateUrl : 'control/multipleCheckBox/multiCheckBoxSample.html',
					lazyLoadFiles : [
							'control/multipleCheckBox/multipleCheckboxController.js',
							'asset/tscd.constants.service.js' ]

				},
				{
					key : 'APP_PARAM',
					title : 'Danh mục tham số ',
					parent:'Quản lý danh mục',
					templateUrl : 'wms/app_param/app_paramList.html',
					lazyLoadFiles : [ 'wms/app_param/app_paramController.js',
							'wms/app_param/app_paramService.js', ]
				},
				
				{
					key : 'DASH_BOARD',
					title : 'Trang chủ',
					templateUrl : 'wms/dashbroad/dashbroad.html',
					lazyLoadFiles : [
							'wms/dashbroad/dashbroadController.js',
							 ]
				},
				
				// báo cáo end

				// quản lý xuất kho
				{
					key : 'DELIVERY_ORDER',
					title : 'Viết phiếu xuất kho',
					parent:'Quản lý xuất kho',
					templateUrl : 'wms/createExportNote/createExportNoteList.html',
					lazyLoadFiles : [ 'js/jszip.min.js',
							'wms/createExportNote/createExportNoteController.js',
							'wms/createExportNote/createExportNoteService.js',
							'wms/exportRequestManage/createExportRequestManageService.js',
							'wms/exportRequestManage/exportRequestManageService.js',
							]
				},

				{
					key : 'EXPORT_STATEMENT_MAN',
					title : 'Quản lý phiếu xuất kho',
					parent:'Quản lý xuất kho',
					templateUrl : 'wms/exportStatementManagement/exportStatementMan.html',
					lazyLoadFiles : [
							'js/jszip.min.js',
							'wms/exportStatementManagement/exportStatementController.js',
							'wms/exportStatementManagement/exportService.js',
							'wms/exportRequestManage/createExportRequestManageService.js',
							]
				},

				
				{
					key : 'CREATE_EX_REQ_MANA',
					title : 'Tạo mới yêu cầu xuất kho',
					parent:'Quản lý xuất kho',
					templateUrl : 'wms/exportRequestManage/createExportRequestManage.html',
					lazyLoadFiles : [
							'wms/exportRequestManage/createExportRequestManageController.js',
							'wms/exportRequestManage/createExportRequestManageService.js',
							'wms/importRequirementManagement/impRequirementManagementService.js',
							'wms/exportRequestManage/exportRequestManageService.js'


					]
				},
				{
					key : 'UPDATE_EX_REQ_MANA',
					title : 'Cập nhật yêu cầu xuất kho',
					parent:'Quản lý xuất kho',
					templateUrl : 'wms/exportRequestManage/createExportRequestManage.html',
					lazyLoadFiles : [
							'wms/exportRequestManage/createExportRequestManageController.js',
							'wms/exportRequestManage/createExportRequestManageService.js',
							'wms/exportRequestManage/exportRequestManageService.js',
							'wms/importRequirementManagement/impRequirementManagementService.js',
					]
				},
			
			    // nhantv quan ly danh muc				
				{
					key : 'APP_PARAM',
					title : 'Danh mục tham số ',
					parent : 'Quản lý danh mục',
					templateUrl : 'wms/app_param/app_paramList.html',
					lazyLoadFiles : [ 'wms/app_param/app_paramController.js',
							'wms/app_param/app_paramService.js', ]
				},

				//Quan ly luong - ctct - qll
				{
					key : 'EXPORT_STATEMENT_MANAGE',
					title : 'Quản lý yêu cầu xuất kho',
					parent:'Quản lý xuất kho',
					templateUrl : 'qll/exportRequestManage/exportRequestManage.html',
					lazyLoadFiles : [
							'qll/exportRequestManage/exportRequestManageController.js',
							'qll/exportRequestManage/exportRequestManageService.js', 
							'qll/exportRequestManage/createExportRequestManageService.js'
				]
				},
				,
				{
					key : 'DL_HA_TANG_TRAM',
					title : 'Dữ liệu hạ tần trạm',
					parent:'Nhân viên trạm',
					templateUrl : 'qll/dlHaTangTram/DLHaTangTram.html',
					lazyLoadFiles : [
							'qll/dlHaTangTram/DLHaTangTramController.js',
							'qll/dlHaTangTram/DlHaTangTramService.js'
				]
				},
				{
					key : 'KI_DON_VI',
					title : 'Ki Đơn Vị',
					parent:'Nhân viên dây máy',
					templateUrl : 'qll/kiDonVi/kiDonVi.html',
					lazyLoadFiles : [
							'qll/kiDonVi/kiDonViController.js',
							'qll/kiDonVi/kiDonViService.js'
				]
				},
				{
					key : 'BC_THUC_XUAT_THEO_KY',
					title : 'Báo cáo thực xuất theo kỳ',
					parent:'Báo cáo',
					templateUrl : 'qll/bcThucXuatTheoKy/bcThucXuatTheoKy.html',
					lazyLoadFiles : [
							'qll/bcThucXuatTheoKy/bcThucXuatTheoKyController.js',
							'qll/bcThucXuatTheoKy/bcThucXuatTheoKyService.js'
				]
				},{
					key : 'BC_CHI_TIET_CONG_NO',
					title : 'Import dữ liêu đầu vào thực xuất',
					parent:'Báo cáo',
					templateUrl : 'qll/baoCaoChiTietCongNo/bcChiTietCongNo.html',
					lazyLoadFiles : [
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoController.js',
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoService.js'
				]
				},{
					key : 'BC_CHI_TIET_CONG_NO1',
					title : 'Xuất báo cáo chi tiết',
					parent:'Báo cáo',
					templateUrl : 'qll/baoCaoChiTietCongNo/bcChiTietCongNo2.html',
					lazyLoadFiles : [
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoController2.js',
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoService.js'
				]
				}
				,{
					key : 'BC_CHI_TIET_CONG_NO2',
					title : 'Xuất báo cáo tổng hợp',
					parent:'Báo cáo',
					templateUrl : 'qll/baoCaoChiTietCongNo/bcChiTietCongNo3.html',
					lazyLoadFiles : [
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoController3.js',
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoService.js'
				]
				}
				,{
					key : 'BC_CHI_TIET_CONG_NO3',
					title : 'Import dữ liêu đầu vào PXK',
					parent:'Báo cáo',
					templateUrl : 'qll/baoCaoChiTietCongNo/bcChiTietCongNo4.html',
					lazyLoadFiles : [
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoController4.js',
							'qll/baoCaoChiTietCongNo/bcChiTietCongNoService.js'
				]
				}
				,{
					key : 'PHAT_FC',
					title : 'Phạt FC',
					parent:'Dây máy',
					templateUrl : 'qll/phatFc/phatFc.html',
					lazyLoadFiles : [
							'qll/phatFc/phatFcController.js',
							'qll/phatFc/phatFcService.js'
				]
				},
				{
					key : 'DU_LIEU_DUY_TRI',
					title : 'Dữ liệu duy trì',
					parent:'Dây máy',
					templateUrl : 'qll/Dulieuduytri/DlDauVaoDayMay.html',
					lazyLoadFiles : [
							'qll/Dulieuduytri/DlDauVaoDayMayController.js',
							'qll/Dulieuduytri/DlDauVaoDayMayService.js'
				]
				},
				{//loi dong ao
					key : 'PHAT_LOI_DONG_AO',
					title : 'Quản lý lỗi đóng ảo',
					parent:'Dây máy',
					templateUrl : 'qll/phatLoiDongAo/phatLoiDongAo.html',
					lazyLoadFiles : [
							'qll/phatLoiDongAo/phatLoiDongAoController.js',
							'qll/phatLoiDongAo/phatLoiDongAoService.js'
				]
				},{//loi lap lai
					key : 'Don_Gia_Thue_Bao',
					title : 'Đơn giá thuê bao',
					parent:'Dây máy',
					templateUrl : 'qll/donGiaThueBao/donGiaThueBao.html',
					lazyLoadFiles : [
							'qll/donGiaThueBao/donGiaThueBaoController.js',
							'qll/donGiaThueBao/donGiaThueBaoService.js'
				]
				},{//loi lap lai
					key : 'PHAT_LOI_LAP_LAI',
					title : 'Quản lý lỗi lặp lại',
					parent:'Dây máy',
					templateUrl : 'qll/phatLoiLapLai/phatLoiLapLai.html',
					lazyLoadFiles : [
							'qll/phatLoiLapLai/phatLoiLapLaiController.js',
							'qll/phatLoiLapLai/phatLoiLapLaiService.js'
				]
				},{//phat pakh
					key : 'PHAT_PAKH',
					title : 'Quản lý phạt phản ánh khách hàng',
					parent:'Dây máy',
					templateUrl : 'qll/phatPakh/phatPakh.html',
					lazyLoadFiles : [
							'qll/phatPakh/phatPakhController.js',
							'qll/phatPakh/phatPakhService.js'
				]
				},{
					key : 'PHAT_LOI_CDT',
					title : 'Phạt của chủ đầu tư phân bổ về đơn vị',
					parent:'Nhân viên dây máy',
					templateUrl : 'qll/phaiLoiCDT/phatLoiCDT.html',
					lazyLoadFiles : [
							'qll/phaiLoiCDT/phatLoiCDTController.js',
							'qll/phaiLoiCDT/phatLoiCDTService.js'
				]
				},{
					key : 'PHAT_KHAC_DAY_MAY',
					title : 'Danh sách chi tiết các lỗi phạt',
					parent:'Nhân viên dây máy',
					templateUrl : 'qll/phatKhacDayMay/phatKhacDayMay.html',
					lazyLoadFiles : [
							'qll/phatKhacDayMay/phatKhacDayMayController.js',
							'qll/phatKhacDayMay/phatKhacDayMayService.js'
				]
				},{
					key : 'PHAT_NGAY_CONG',
					title : 'Phạt Ngày Công',
					parent:'Nhân viên dây máy',
					templateUrl : 'qll/NgayCong/phatNgayCong.html',
					lazyLoadFiles : [
							'qll/NgayCong/phatNgayCongController.js',
							'qll/NgayCong/phatNgayCongService.js'
				]
				},
				{
					key : 'PHAT_ROI_MANG',
					title : 'Phạt Rời Mạng',
					parent:'Nhân viên dây máy',
					templateUrl : 'qll/phatRoiMang/phatRoiMang.html',
					lazyLoadFiles : [
							'qll/phatRoiMang/phatRoiMangController.js',
							'qll/phatRoiMang/phatRoiMangService.js'
				]
				},
				{
					key : 'DUT_CAP',
					title : 'Đứt cáp do lỗi chủ quan',
					parent:'Nhân viên trạm',
					templateUrl : 'qll/phatDutCapDoLoiCQ/phatDutCapDoLoiCQ.html',
					lazyLoadFiles : [
							'qll/phatDutCapDoLoiCQ/phatDutCapDoLoiCQController.js',
							'qll/phatDutCapDoLoiCQ/phatDutCapDoLoiCQService.js'
				]
				},
				{
					key : 'DL_DON_GIA_TRAM',
					title : 'Dữ liệu đơn giá trạm',
					parent:'Nhân viên trạm',
					templateUrl : 'qll/donGiaTram/donGiaTram.html',
					lazyLoadFiles : [
							'qll/donGiaTram/donGiaTramController.js',
							'qll/donGiaTram/donGiaTramService.js'
				]
				},
				{
					key : 'PHAT_KHAC',
					title : 'Phạt khác',
					parent:'Nhân viên trạm',
					templateUrl : 'qll/phatKhacNhaTram/phatKhacNhaTram.html',
					lazyLoadFiles : [
							'qll/phatKhacNhaTram/phatKhacController.js',
							'qll/phatKhacNhaTram/phatKhacService.js'
				]
				},
				{
					key : 'PHAT_KPI',
					title : 'Phạt KPI',
					parent:'Nhân viên trạm',
					templateUrl : 'qll/phatKPI/phatKPI.html',
					lazyLoadFiles : [
							'qll/phatKPI/phatKPIController.js',
							'qll/phatKPI/phatKPIService.js'
				]
				},
				{
					key : 'TLNT',
					title : 'Tính lương nhà Trạm',
					parent:'Nhân viên trạm',
					templateUrl : 'qll/Tinhlluongnhatram/TinhluongNT.html',
					lazyLoadFiles : [
							'qll/Tinhlluongnhatram/TinhluongNTController.js',
							'qll/Tinhlluongnhatram/TinhluongNTService.js'
				]
				},
				{
					key : 'PHAT_XLSC',
					title : 'Phạt xử lý sự cố',
					parent:'Nhân viên dây máy',
					templateUrl : 'qll/phatXLSC/phatXLSC.html',
					lazyLoadFiles : [
							'qll/phatXLSC/phatXLSCController.js',
							'qll/phatXLSC/phatXLSCService.js'
				]
				},
				{
					key : 'QTTL',
					title : 'Quá trình tính lương',
					parent:'Nhân viên nhà trạm',
					templateUrl : 'qll/quaTrinhTinhLuong/qtTinhLuong.html',
					lazyLoadFiles : [
							'qll/quaTrinhTinhLuong/qtTinhLuongController.js',
							'qll/quaTrinhTinhLuong/qtTinhLuongService.js'
				]
				},{
					key : 'KDC_LUONG_CD',
					title : 'KDC lương cơ động',
					parent:'Danh mục dùng chung',
					templateUrl : 'qll/kiDieuChinh/kDCLuongCD.html',
					lazyLoadFiles : [
							'qll/kiDieuChinh/kDCLuongCDController.js',
							'qll/kiDieuChinh/kDCLuongCDService.js'
				]
				},
				{
					key : 'KDC_QUY_LUONG',
					title : 'KDC quỹ lương',
					parent:'Danh mục dùng chung',
					templateUrl : 'qll/kiDieuChinh/KDCQuyLuong.html',
					lazyLoadFiles : [
							'qll/kiDieuChinh/KDCQuyLuongController.js',
							'qll/kiDieuChinh/KDCQuyLuongService.js'
				]
				},{
					key : 'K_HUYEN_KHO',
					title : 'K huyện khó',
					parent:'Danh mục dùng chung',
					templateUrl : 'qll/kiDieuChinh/kHuyenKho.html',
					lazyLoadFiles : [
							'qll/kiDieuChinh/kHuyenKhoController.js',
							'qll/kiDieuChinh/kHuyenKhoService.js'
				]
				},{
					key : 'KI_CA_NHAN',
					title : 'Ki cá nhân',
					parent:'Danh mục dùng chung',
					templateUrl : 'qll/kiCaNhan/kiCaNhan.html',
					lazyLoadFiles : [
							'qll/kiCaNhan/kiCaNhanController.js',
							'qll/kiCaNhan/kiCaNhanService.js'
				]
				},{
					key : 'PHI_BAN_HANG',
					title : 'Phí bán hàng',
					parent:'Danh mục dùng chung',
					templateUrl : 'qll/phiBanHang/phiBanHang.html',
					lazyLoadFiles : [
							'qll/phiBanHang/phiBanHangController.js',
							'qll/phiBanHang/phiBanHangService.js'
				]
				},{
					key : 'QL_NHAN_VIEN',
					title : 'Quản lý nhân viên',
					parent:'Danh mục dùng chung',
					templateUrl : 'qll/qlNhanVien/qlNhanVien.html',
					lazyLoadFiles : [
							'qll/qlNhanVien/qlNhanVienController.js',
							'qll/qlNhanVien/qlNhanVienService.js'
				]
				},
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//Báo cáo vật tư A cấp
				{
					key : 'IMP_PXK_A_CAP',
					title : 'Import dữ liệu đầu vào PXK',
					parent:'Quản lý vật tư A cấp',
					templateUrl : 'qll/quanLyVatTuACap/importDvPXK/importPXK.html',
					lazyLoadFiles : [
							'qll/quanLyVatTuACap/importDvPXK/importPXKController.js',
							'qll/quanLyVatTuACap/importDvPXK/importPXKService.js'
				]
				},{
					key : 'IMP_TX_PXK_A_CAP',
					title : 'Import dữ liệu đầu thực xuất theo kỳ',
					parent:'Quản lý vật tư A cấp',
					templateUrl : 'qll/quanLyVatTuACap/importThucXuatTheoKy/impThucXuatTheoKy.html',
					lazyLoadFiles : [
							'qll/quanLyVatTuACap/importThucXuatTheoKy/impThucXuatTheoKyController.js',
							'qll/quanLyVatTuACap/importThucXuatTheoKy/impThucXuatTheoKyService.js'
				]
				},
				{
					key : 'EXP_TX_PXK_A_CAP',
					title : 'Export dữ liệu đầu thực xuất theo kỳ',
					parent:'Quản lý vật tư A cấp',
					templateUrl : 'qll/quanLyVatTuACap/ExportPXK/exportPXK.html',
					lazyLoadFiles : [
							'qll/quanLyVatTuACap/ExportPXK/exportPXKController.js',
							'qll/quanLyVatTuACap/ExportPXK/exportPXKService.js'
				]
				},{
					key : 'EXP_TH_A_CAP',
					title : 'Export tổng hợp A cấp',
					parent:'Quản lý vật tư A cấp',
					templateUrl : 'qll/quanLyVatTuACap/exportTH/bcThucXuatTheoKy.html',
					lazyLoadFiles : [
							'qll/quanLyVatTuACap/exportTH/bcThucXuatTheoKyController.js',
							'qll/quanLyVatTuACap/exportTH/bcThucXuatTheoKyService.js'
				]
				},
				{
					key : 'PHAN_QUYEN_MENU',
					title : 'Phân quyền ',
					parent:'Phân quyền chức năng',
					templateUrl : 'qll/phanQuyenHeThong/phanQuyenHT.html',
					lazyLoadFiles : [
							'qll/phanQuyenHeThong/phanQuyenHTController.js',
							'qll/phanQuyenHeThong/phanQuyenHTService.js'
				]
				},
				{
					key : 'VIEW_HISTORY_ACTION',
					title : 'Lịch sử thao tác',
					parent:'Lịch sử ',
					templateUrl : 'qll/viewHistory/viewHistory.html',
					lazyLoadFiles : [
							'qll/viewHistory/viewHistoryController.js',
							'qll/viewHistory/viewHistoryService.js'
				]
				},
				{
					key : 'VIEW_HISTORY_DN_ACTION',
					title : 'Lịch sử đăng nhập',
					parent:'Lịch sử ',
					templateUrl : 'qll/viewHistory2/viewHistoryDN.html',
					lazyLoadFiles : [
							'qll/viewHistory2/viewHistoryDNController.js',
							'qll/viewHistory2/viewHistoryDNService.js'
				]
				},
				{
					key : 'TINH_LUONG_DAY_MAY',
					title : 'Tính lương dây máy',
					parent:'Tính lương ',
					templateUrl : 'qll/tinhLuongDayMay/tinhLuongDayMay.html',
					lazyLoadFiles : [
							'qll/tinhLuongDayMay/tinhLuongDayMayController.js',
							'qll/tinhLuongDayMay/tinhLuongDayMayService.js'
				]
				},
				
				//tool phong thanh khoan
				{
					key : 'QLHDTK',
					title : 'Cập nhật kết quả hợp đồng PTK',
					parent:'Báo cáo PTK',
					templateUrl : 'qll/A_PTK-qLHDTK/QLHDTK.html',
					lazyLoadFiles : [
							'qll/A_PTK-qLHDTK/qLHDTKController.js',
							'qll/A_PTK-qLHDTK/qLHDTKService.js'
				]
				},{
					key : 'TBLQLCVPTK',
					title: 'Quản lý công việc PTK',
					parent:'Báo cáo PTK',
					templateUrl : 'qll/A_PTK-tblQLCvPtk/tblQlCvPtk.html',
					lazyLoadFiles : [
							'qll/A_PTK-tblQLCvPtk/tblQlCvPtkController.js',
							'qll/A_PTK-tblQLCvPtk/tblQlCvPtkService.js',
							'qll/A_PTK-tblqlcvfile/tblQlCvFileService.js',
							'qll/A_PTK-ganNhiemVu-baoCaoCongViec/GNVService.js'
				]
				},{
					key : 'BAO_CAO_BIEU_DO_PTK',
					title: 'Biểu đồ báo cáo công việc phòng thanh khoản',
					parent:'Báo cáo PTK',
					templateUrl : 'qll/A_PTK-tblQLCvPtk/reportChart.html',
					lazyLoadFiles : [
						'qll/A_PTK-tblQLCvPtk/tblQlCvPtkController.js',
						'qll/A_PTK-tblQLCvPtk/tblQlCvPtkService.js'
							
				]
				},
				{
					key : 'GAN_NHIEM_VU',
					title: 'Quản lý nhiệm vụ',
					parent:'Báo cáo PTK',
					templateUrl : 'qll/A_PTK-ganNhiemVu-baoCaoCongViec/ListGanNvPopup.html',
					lazyLoadFiles : [
							'qll/A_PTK-ganNhiemVu-baoCaoCongViec/GNVController.js',
							'qll/A_PTK-ganNhiemVu-baoCaoCongViec/GNVService.js',
							'qll/A_PTK-tblQLCvPtk/tblQlCvPtkService.js'
				]
				},
				
				//tool phong to chuc lao dong
				{
					key : 'TBL_TIME_WORK',
					title: 'Lịch sử ra/vào đơn vị',
					parent:'TK Time Ra vao',
					templateUrl : 'qll/A_PTCLD-tblTimeWork/tblTimeWork.html',
					lazyLoadFiles : [
							'qll/A_PTCLD-tblTimeWork/tbLTimeWorkController.js',
							'qll/A_PTCLD-tblTimeWork/tblTimeWorkService.js'
							
				]
				},
				
				//tool phong ke hoach
				{
					key : 'TBL_KH_QLVC',
					title: 'Thống kê Kết quả nhiệm vụ',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-tblKhQlVc/tblKhQlVc.html',
					lazyLoadFiles : [
							'qll/A_PKH-tblKhQlVc/tbLKhQlVcController.js',
							'qll/A_PKH-tblKhQlVc/tblKhQlVcService.js'
							
				]
				},
				{
					key : 'BAO_CAO_BIEU_DO_QLCV',
					title: 'Biểu đồ thống kê Kết quả nhiệm vụ',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-tblKhQlVc/reportPKH.html',
					lazyLoadFiles : [
							'qll/A_PKH-tblKhQlVc/tbLKhQlVcController.js',
							'qll/A_PKH-tblKhQlVc/tblKhQlVcService.js'
							
				]
				},{
					key : 'TBL_KH_BCKQCV',
					title: 'Thống kê kết quả Kinh doanh',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-tblKhBcKqCv/tblKhBcKqCv.html',
					lazyLoadFiles : [
							'qll/A_PKH-tblKhBcKqCv/tblKhBcKqCvController.js',
							'qll/A_PKH-tblKhBcKqCv/tblKhBcKqCvService.js'
							
				]
				},{
					key : 'BAO_CAO_BIEU_DO_KQCV',
					title: 'Biểu đồ thống kê kết quả Kinh doanh',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-tblKhBcKqCv/reportPKH.html',
					lazyLoadFiles : [
//							'qll/tblKhQlVc/tbLKhQlVcController.js',
//							'qll/tblKhQlVc/tblKhQlVcService.js'
							
				]
				},{
					key : 'GENERAL_KPI_SCORE',
					title: 'Tổng hợp điểm Các Phòng ban',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-tblKpiScore/generalKpiScore.html',
					lazyLoadFiles : [
							'qll/A_PKH-tblKpiScore/tblKpiScoreController.js',
							'qll/A_PKH-tblKpiScore/tblKpiScoreService.js'
					]
				},
				{
					key : 'DETAIL_KPI_SCORE',
					title: 'Chi tiết điểm Các Phòng ban',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-tblKpiScore/detailKpiScore.html',
					lazyLoadFiles : [
							'qll/A_PKH-tblKpiScore/tblKpiScoreController.js',
							'qll/A_PKH-tblKpiScore/tblKpiScoreService.js'
					]
				},{
					key : 'TASK',
					title: 'Quản lý công việc nề nếp',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-task/task.html',
					lazyLoadFiles : [
							'qll/A_PKH-task/taskController.js',
							'qll/A_PKH-task/taskService.js'
					]
				},{
					key : 'TASK_GROUP',
					title: 'Quản lý nhóm công việc',
					parent:'TK phòng kế hoạch',
					templateUrl : 'qll/A_PKH-taskGroup/taskGroup.html',
					lazyLoadFiles : [
							'qll/A_PKH-taskGroup/taskGroupController.js',
							'qll/A_PKH-taskGroup/taskGroupService.js'
					]
				}
				
				
		];

		config.getTemplateUrl = function(key) {
			for ( var i in config.TEMPLATE_URL) {
				if (config.TEMPLATE_URL[i].key == key) {
					return config.TEMPLATE_URL[i];
				}
			}

			return null;
		}

		return config;
	}
	angular.module('MetronicApp').constant('PopupConst', {
		
	});

	angular.module('MetronicApp').constant('AppConst', {
		AR_INVOICE : {
			Invoice_Table_ID : 1000059,
			Tax_Account_ID : 1000027
		},
		AR_DEPOSITE_BROWSER : {
			document_type_id : 'D00001'
		},
		AR_REVALUATION : {
			Document_Type_Id : 17,
			Status : 'DR'
		},
		C_CONTIGENCY_SALE : {
			Status : 'DR',
			CurrencyName : '1000046'
		}
	});

})();
