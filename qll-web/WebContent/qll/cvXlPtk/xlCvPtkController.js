(function() {
	'use strict';
	var controllerId = 'xlCvPtkController';

	angular.module('MetronicApp').controller(controllerId,
			xlCvPtkController);

	function xlCvPtkController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, xlCvPtkService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.xlCvPtkSearch = {};
		vm.addQlcvPopup={};
		vm.exportHD={};
		vm.exportHD1={};
		vm.xuatBCDMV={};
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
				vm.addQlcvPopup.ngayGuiPtkHshc=null;
			}else{
				vm.addQlcvPopup.thangHshcQuyLuong=null;
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
									+ 'ng-click="vm.addCvHdPopSubmit()" ng-show="RoleMenu.checkRole.checkNhapLieuDN||RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Thêm mới" translate >Thêm mới</button>'
									 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.xuatBCTHCV()" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Xuất báo tổng hợp" aria-hidden="true" translate>Báo cáo tổng hợp</button>'
									 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
									 + 'ng-click="vm.xuatBCTH()" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Xuất báo cáo theo ngày/tháng" aria-hidden="true" translate>BC tổng hợp ngày</button>'
									 +'</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<button class="btn btn-qlk padding-search-right deletehd" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+'<i class="action-button excelQLK" uib-tooltip="Xuất tất cả dữ liệu" ng-click="vm.exportExcelGrid1()" aria-hidden="true"></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.xlCvPtkGrid.columns.slice(1,vm.xlCvPtkGrid.columns.length)">'
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
									$("#xlCvPtkCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "qlCvPtkRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {

									vm.xlCvPtkSearch.page = options.page;
									vm.xlCvPtkSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.xlCvPtkSearch);
									return JSON.stringify(vm.xlCvPtkSearch);

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
									title : "Thao tác",
									template : '<div class="text-center #=qlCvPtkId#""> '
											+ '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true"  uib-tooltip="Cập nhật thẩm định" translate>'
											+ '			<i class="fa gandonvi"  ng-click="vm.updateHDTd(dataItem)" ng-show="RoleMenu.checkRole.checkThamDinhDN||RoleMenu.checkRole.checkTPPTP"   aria-hidden="true"></i> '
											+ '		</button>'
											
								
											+'		<button  class="#=qlCvPtkId# icon_table" style=" border: none; " uib-tooltip="Xóa" translate> '+
											'		<i class="fa deletehdCon" ng-click=vm.removeDetail(dataItem) ria-hidden="true"></i>'+
											'		</button>'
											+ '</div>',
									width : '120px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;"
									}
								},
								{
									title : "File QTK",
									template: '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true" ng-show="dataItem.fileQtk!=null"  uib-tooltip="Tải về file Scan đính kèm" translate>'
											+ '			<i class="fa downloadimg"  ng-click="vm.downloadFileQtk(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+ '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true"  uib-tooltip="Tải file Scan đính kèm lên" translate>'
											+ '			<i class="fa uploadimg"  ng-click="vm.uploadFileAll1(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>',
									field : 'fileQtk',
									width : '90px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "File QT Đối Tác",
									template: '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true" ng-show="dataItem.fileQtDt!=null"  uib-tooltip="Tải về file Scan đính kèm" translate>'
											+ '			<i class="fa downloadimg"  ng-click="vm.downloadFileQtDt(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+ '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true"  uib-tooltip="Tải file Scan đính kèm lên" translate>'
											+ '			<i class="fa uploadimg"  ng-click="vm.uploadFileAll2(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>',
									field : 'fileQtDt',
									width : '90px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Bản thẩm định chủ đầu tư file scan",
									template: '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true" ng-show="dataItem.banThamDinh1!=null"  uib-tooltip="Tải về file Scan đính kèm" translate>'
											+ '			<i class="fa downloadimg"  ng-click="vm.downloadFile(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+ '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true"  uib-tooltip="Tải file Scan đính kèm lên" translate>'
											+ '			<i class="fa uploadimg"  ng-click="vm.uploadFile(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>',
									field : 'banThamDinh1',
									width : '90px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Bản thẩm định chủ đầu tư file excel",
									template: '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true" ng-show="dataItem.banThamDinh2!=null"  uib-tooltip="Tải về file Excel đính kèm" translate>'
											+ '			<i class="fa exdown"  ng-click="vm.downloadFile1(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+ '		<button style=" border: none; " class="#=qlCvPtkId# icon_table" aria-hidden="true"  uib-tooltip="Tải file Excel đính kèm lên" translate>'
											+ '			<i class="fa uploadEx"  ng-click="vm.uploadFileExcel(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>',
									field : 'banThamDinh2',
									width : '90px',
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
										style : "text-align:left;"
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
										style : "text-align:left;"
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
									title : "Thuộc định mức hoặc tờ trình nào",
									field : 'thuocDmTt',
									width : '150px',
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
									width : '100px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
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
									title : "Ngày gửi HSHC lên công ty",
									field : 'ngayGuiHshc',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Số bill",
									field : 'soBill',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
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
								},{
									title : "Giá trị Theo sản lượng hạ tầng tạm tính",
									field : 'gtSlHtTinh',
									width : '200px',
									format:"{0:n0}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},

								},
								{
									title : "Giá trị đề nghị QTK của CN",
									field : 'gtDnQtkCn',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Ngày trị đề nghị QTK",
									field : 'ngayDnQtk',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},{
									title : "Ngày nhận gửi PTK gửi HSHC",
									field : 'ngayNhanGuiPtkHshc',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tình trạng chuyển Chứng từ",
									field : 'tinhTrangChuyenUngTu',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								{
									title : "Ghi chú (đối với trường hợp hồ sơ lỗi vướng hướng giải quyết)",
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
									title : "Ngày tương ứng theo tình trạng chứng từ",
									field : 'ngayTuongUngTtcut',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Giá trị PTK thẩm định xong QTK",
									field : 'gtPtkTdXongQtk',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày PTK thẩm định xong QTK",
									field : 'ngayPtkTdXongQtk',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Giá trị Đề nghị QT với CĐT",
									field : 'gtDnQtCdt',
									width : '150px',
									format:"{0:n0}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Giá trị Chốt QT với CĐT",
									field : 'gtChotQtCdt',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày giá trị chốt QT với CĐT",
									field : 'ngayGtChotQtCdt',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ghi chú",
									field : 'ghiChuDn',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tháng QTK",
									field : 'thangQtk',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tháng QT với CDT",
									field : 'thangQtkCdt',
									width : '150px',
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
									title : "Hồ sơ tồn quá 35 ngày chưa xử lý",
									field : 'hsTonQua35n',
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
									field : 'gtQhChuaXl',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Đang lưu dữ tại thùng khu kho",
									field : 'luuTaiKho',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Người duyệt CDT",
									field : 'nguoiDuyetCdt',
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
									field : 'nguoiDuyetPtk',
									width : '100px',
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

			vm.objUploadFile1={};
			vm.uploadFileAll1 = function(dataItem){
				var teamplateUrl="qll/cvXlPtk/upLoadFile1.html";
			    var title="Tải file đính kèm";
			    var windowId="UPLOAD_File";
			    vm.objUploadFile1=dataItem;
			    vm.dataFile = [];
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','40%');
			}
			vm.objUploadFile2={};
			vm.uploadFileAll2 = function(dataItem){
				var teamplateUrl="qll/cvXlPtk/upLoadFile2.html";
			    var title="Tải file đính kèm";
			    var windowId="UPLOAD_File";
			    vm.objUploadFile2=dataItem;
			    vm.dataFile = [];
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','40%');
			}
		   vm.objUpload={};
	        vm.uploadFile = function(dataItem){
				var teamplateUrl="qll/quanLyVatTuACap/importDvPXK/upLoad.html";
			    var title="Tải file đính kèm";
			    var windowId="UPLOAD_IMAGE_HD";
			    vm.objUpload=dataItem;
			    vm.dataFile = [];
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','40%');
			}
	        vm.objUpload1={};
	        vm.uploadFileExcel = function(dataItem){
				var teamplateUrl=" qll/cvXlPtk/upLoad.html";
			    var title="Tải file excel đính kèm";
			    var windowId="UPLOAD_EXCEL_HD";
			    vm.objUpload1=dataItem;
			    vm.dataFile = [];
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','40%');
			}
	       
	        vm.onSelectFile1 = function(e) {
				 if( $("#files")[0].files[0].name.split('.').pop() !='png' && $("#files")[0].files[0].name.split('.').pop() !='jpg'&& $("#files")[0].files[0].name.split('.').pop() !='pdf'&& $("#files")[0].files[0].name.split('.').pop() !='xlsx'&& $("#files")[0].files[0].name.split('.').pop() !='xls'){
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
				 var obj={};
				 obj.name=file.name;
				 obj.filePath=data[index];
				 vm.objUploadFile1.fileQtk=obj.filePath;
				 vm.dataFile.push(obj);
				 xlCvPtkService.updatePathFileQtk(vm.objUploadFile1).then(
						function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Tải ảnh lên thành công!");
			    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
			    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
			            });

				 })
	            }
	        });
	        }
	        vm.onSelectFile2 = function(e) {
				 if( $("#files")[0].files[0].name.split('.').pop() !='png' && $("#files")[0].files[0].name.split('.').pop() !='jpg'&& $("#files")[0].files[0].name.split('.').pop() !='pdf'&& $("#files")[0].files[0].name.split('.').pop() !='xlsx'&& $("#files")[0].files[0].name.split('.').pop() !='xls'){
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
				 var obj={};
				 obj.name=file.name;
				 obj.filePath=data[index];
				 vm.objUploadFile2.fileQtDt=obj.filePath;
				 vm.dataFile.push(obj);
				 xlCvPtkService.updatePathFileQtDt(vm.objUploadFile2).then(
							function(result) {
									if(result.error){
				    				toastr.error(result.error);
				    				return;
				    			}
				    			toastr.success("Tải ảnh lên thành công!");
				    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
				    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
				    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				    		}, function(errResponse){
				                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
				            });
				 })
	            }
	        });
	        }
	        vm.onSelect = function(e) {
				 if( $("#files")[0].files[0].name.split('.').pop() !='png' && $("#files")[0].files[0].name.split('.').pop() !='jpg'&& $("#files")[0].files[0].name.split('.').pop() !='pdf'){
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
				 var obj={};
				 obj.name=file.name;
				 obj.filePath=data[index];
				 vm.objUpload.banThamDinh1=obj.filePath;
				 vm.dataFile.push(obj);
				 xlCvPtkService.updatePathFileScan(vm.objUpload).then(
						function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Tải ảnh lên thành công!");
			    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
			    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
			            });
				 
				 })
	            }
	        });
	    }
	      
	        vm.onSelect1 = function(e) {
				 if( $("#files1")[0].files[0].name.split('.').pop() !='xlsx'&& $("#files1")[0].files[0].name.split('.').pop() !='xls'){
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
				 jQuery.each(jQuery('#files1')[0].files, function(i, file) {
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
				 var obj={};
				 obj.name=file.name;
				 obj.filePath=data[index];
				 vm.objUpload1.banThamDinh2=obj.filePath;
				 vm.dataFile.push(obj);
				 xlCvPtkService.updatePathFileExcel(vm.objUpload1).then(
						function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Tải ảnh lên thành công!");
			    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
			    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
			            });
				 
				 })
	            }
	        });
	    }
	        
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
	                            return Restangular.all("qlCvPtkRsServiceRest/getForAutoCompleteSHD").post({pageSize:10, page:1, soHd:$("#tenShdAuto").val().trim()}).then(function(response){
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
	                	if(processSearch('tenShdAuto',vm.selectedPrpject)){
	    					 $('#tenShdAuto').val("");
	    					 vm.exportHD.soHd="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
	        
	        
	        vm.downloadFileQtk = function(dataItem){
	        		
	        	 if(dataItem.fileQtk==null){
	        	 toastr.error("Phiếu xuất này không có file  đính kèm!!!");
	        	 return;
	        	 }
	        				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.fileQtk;
	        				
	        	    	}
	        
	        vm.downloadFileQtDt = function(dataItem){
        		
        	 if(dataItem.fileQtDt==null){
        	 toastr.error("Phiếu xuất này không có file  đính kèm!!!");
        	 return;
        	 }
        				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.fileQtDt;
        				
        	    	}
	        vm.downloadFile = function(dataItem){
	        
	        	 if(dataItem.banThamDinh1==null){
	        	 toastr.error("Phiếu xuất này không có file scan đính kèm!!!");
	        	 return;
	        	 }
	        				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.banThamDinh1;	
	        	    	}
	        
	        vm.downloadFile1 = function(dataItem){
	        	
	        	 if(dataItem.banThamDinh2==null){
	        	 toastr.error("Phiếu xuất này không có file excel đính kèm!!!");
	        	 return;
	        	 }
	        				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.banThamDinh2;	
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
			case 'xlCvPtkAuto':{
			if(processSearch(id,vm.selectedPrpject)){
				vm.xlCvPtkSearch.donVi = null;
				vm.xlCvPtkSearch.tenDanhMuc =null;
				vm.selectedPrpject=false;	
			 }
				break;
			}
		 }
		
		}
        
        vm.onSave=onSave;
		function onSave(data){
			vm.xlCvPtkSearch.donVi=data.maDanhMuc;
			vm.xlCvPtkSearch.tenDanhMuc=data.tenDanhMuc;
			
// $('#xlCvPtkAuto').focus();
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
			var grid = vm.xlCvPtkGrid;
			if($("#maLenhXuatId").val()==""){
				vm.xlCvPtkSearch.maTramTuyen=null;
			}
			if($("#tenShdAuto").val()==""){
				vm.xlCvPtkSearch.soHd=null;
			}
			if($("#qlcvTinhAuto11").val()==""){
				vm.xlCvPtkSearch.tenTinh=null;
			}
			if($("#chiNhanhPtkAuto").val()==""){
				vm.xlCvPtkSearch.tenDanhMuc=null;
			}
			if($("#soBillId").val()==""){
				vm.xlCvPtkSearch.soBill=null;
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
			var title = "Import quản lý công việc PTK";
			var windowId = "IMPORT_QL_CV";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}
		vm.save12 = function(){
			var formData = new FormData();
			formData.append('multipartFile', $('#files')[0].files[0]);
			return   $.ajax({
	            url:Constant.BASE_SERVICE_URL+"fileservice/uploadATTT",
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success:function(data) {
				
				 vm.objUploadFile.fileQtk=data[0];
				 	
				 xlCvPtkService.updatePathFileQtk(vm.objUploadFile).then(
						function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Tải ảnh lên thành công!");
			    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
			    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi tải ảnh lên!!!"));
			            });
				 
				
	            }
	        });
		}
		// importfile excel
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
								+ RestEndpoint.QL_CV_PTK
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

//		vm.downloadFile = function(dataItem){
//			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.pathImg;	
//    	}
		 //xóa list theo điều kiện tìm kiếm 
		
		 vm.removeAny=function(){
		    	vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				xlCvPtkService.deleteListObj(vm.oldSearch).then(function(result){
					if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Xóa hợp đồng thành công!");
	    			  var sizePage = $("#xlCvPtkGrid").data("kendoGrid").dataSource.total();
	    			  var pageSize = $("#xlCvPtkGrid").data("kendoGrid").dataSource.pageSize();
	    					if(sizePage % pageSize === 1){
	    								var currentPage = $("#xlCvPtkGrid").data("kendoGrid").dataSource.page();
	    								if (currentPage > 1) {
	    									$("#xlCvPtkGrid").data("kendoGrid").dataSource.page(currentPage - 1);
	    								}
	    							}
	    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
	    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
	    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thẩm định"));
	            });
		    }
		 vm.checkTyLe=function(){
			 var xx1 ="0";
			 var xx2 = "0";
			  if(vm.addQlcvPopup.gtChotQtCdt===undefined||vm.addQlcvPopup.gtChotQtCdt===""||vm.addQlcvPopup.gtChotQtCdt==null){
				 vm.addQlcvPopup.gtChotQtCdt=0;
			  }
			  else{
				  xx1= vm.replaceVAlue(vm.addQlcvPopup.gtChotQtCdt);
			  }
			  if(vm.addQlcvPopup.gtDnQtCdt===undefined||vm.addQlcvPopup.gtDnQtCdt===""||vm.addQlcvPopup.gtDnQtCdt==null){
				  vm.addQlcvPopup.gtDnQtCdt=0;
			  } else{
				  xx2= vm.replaceVAlue(vm.addQlcvPopup.gtDnQtCdt);
			  }
			  if(parseFloat(xx2)!=0){
				  vm.addQlcvPopup.tyLe= (parseFloat(xx1)/parseFloat(xx2))*100;
				  vm.addQlcvPopup.tyLe= kendo.toString(vm.addQlcvPopup.tyLe, "#,###.00");
			  }
			 
		  }
		 vm.checkChange=function(){
			  if(vm.addQlcvPopup.ngayNhanGuiPtkHshc!=null&&vm.addQlcvPopup.ngayGtChotQtCdt!=null){
				  var date1 = kendo.parseDate(vm.addQlcvPopup.ngayNhanGuiPtkHshc, "dd/MM/yyyy");
				  	var date2 = kendo.parseDate(vm.addQlcvPopup.ngayGtChotQtCdt, "dd/MM/yyyy");
				    var xxx=Math.abs((date2.getTime()-date1.getTime())/(35*24*60*60*1000));
				    if(xxx>1){
				    	  vm.addQlcvPopup.hsTonQua35n="Quá hạn";
				    }else{
				    	 vm.addQlcvPopup.hsTonQua35n="Chưa quá hạn";
				    }
			  }
		  }

		 //thêm mới
		 
		  vm.addCvHdPopSubmit = function(){
			  	vm.addQlcvPopup={};
			  	vm.addQlcvPopup.ngayTuongUngTtcut=kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
			  	vm.addQlcvPopup.nguoiDuyetPtk=$rootScope.casUser.fullName;
				var teamplateUrl="qll/cvXlPtk/addQlcvPopup.html";
			    var title="Thêm mới quản lý công việc";
			    var windowId="ADD_QL_CV";
			
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		
		  vm.xuatBCTH = function(){
			  	vm.xuatBCDMV={};
			  	var teamplateUrl="qll/cvXlPtk/xuatBaoCaoTheoTTChungTuPop.html";
			    var title="Xuất báo cáo theo đầu mục việc";
			    var windowId="EX_BC_DMV";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','35%');
			}
		
		  		  
		  vm.updateHDTd = function(dataItem){
			  	vm.addQlcvPopup=dataItem;
			  	vm.addQlcvPopup.nguoiCapNhat=$rootScope.casUser.fullName;
				vm.addQlcvPopup.nguoiPheDuyetPtk=$rootScope.casUser.fullName;
				var teamplateUrl="qll/cvXlPtk/addUpdateHdTdPopup.html";
			    var title="Cập nhật quản lý Công việc";
			    var windowId="TD_QL_CV";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		  
		  vm.updateHDLuong = function(dataItem){
			  	vm.addQlcvPopup=dataItem;
			  	vm.addQlcvPopup.nguoiPheDuyetPtk=$rootScope.casUser.fullName;
			  	vm.addQlcvPopup.tyLe=(vm.addQlcvPopup.gtTdPtkChuaVat/vm.addQlcvPopup.gtQtCdtChuaVat)*100;
			  	 vm.addQlcvPopup.luongThucNhan=vm.addQlcvPopup.cpLuongTd-vm.addQlcvPopup.tamUngLuong;
			  	var date1 = kendo.parseDate(vm.addQlcvPopup.ngayGuiPtkHshc, "dd/MM/yyyy");
			  	var date2 = kendo.parseDate(vm.addQlcvPopup.ngayPtkTdXong, "dd/MM/yyyy");
			    var xxx=(date2.getTime()-date1.getTime())/(5*24*60*60*1000);
			    if(xxx>1){
			    	  vm.addQlcvPopup.hsTonQua5n="Quá hạn";
			    }else{
			    	 vm.addQlcvPopup.hsTonQua5n="Chưa quá hạn";
			    }
			  
				var teamplateUrl="qll/xlCvPtk/addUpdateHdTTKPopup.html";
			    var title="Thêm mới thông tin hợp đồng";
			    var windowId="TM_TT_KQ_HD";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		  vm.listmess1="";
		  vm.listmess2="";
		  vm.updateHD = function(dataItem){
			  	vm.addQlcvPopup=dataItem;
				var teamplateUrl="qll/xlCvPtk/addQlcvPopupup.html";
			    var title="Thêm mới hợp đồng";
			    var windowId="ADD_KQ_HD";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
			}
		  vm.sumLuongThucNhan=function(){
			  vm.addQlcvPopup.luongThucNhan=vm.addQlcvPopup.cpLuongTd-vm.addQlcvPopup.tamUngLuong;
		  }
		  
		  vm.totalDN=function(){
			  if(vm.addQlcvPopup.cpNhanCongDn===undefined||vm.addQlcvPopup.cpNhanCongDn===""){
				  vm.addQlcvPopup.cpNhanCongDn=0;
			  }
			  if(vm.addQlcvPopup.cpVatLieuDn===undefined||vm.addQlcvPopup.cpVatLieuDn===""){
				  vm.addQlcvPopup.cpVatLieuDn=0;
			  }
			  if(vm.addQlcvPopup.cpHshcDn===undefined||vm.addQlcvPopup.cpHshcDn===""){
				  vm.addQlcvPopup.cpHshcDn=0;
			  }
			  if(vm.addQlcvPopup.cpVanChuyenDn===undefined||vm.addQlcvPopup.cpVanChuyenDn===""){
				  vm.addQlcvPopup.cpVanChuyenDn=0;
			  }
			  if(vm.addQlcvPopup.chiPhiKhacDn===undefined||vm.addQlcvPopup.chiPhiKhacDn===""){
				  vm.addQlcvPopup.chiPhiKhacDn=0;
			  }
			  if(vm.addQlcvPopup.chiPhiLuongDn===undefined||vm.addQlcvPopup.chiPhiLuongDn===""){
				  vm.addQlcvPopup.chiPhiLuongDn=0;
			  }
			  if(vm.addQlcvPopup.vatDn===undefined||vm.addQlcvPopup.vatDn===""){
				  vm.addQlcvPopup.vatDn=0;
			  }
			  vm.addQlcvPopup.tongDn=vm.addQlcvPopup.cpNhanCongDn+vm.addQlcvPopup.cpVatLieuDn
					  				+ vm.addQlcvPopup.cpHshcDn+vm.addQlcvPopup.cpVanChuyenDn
					  				+vm.addQlcvPopup.chiPhiKhacDn+vm.addQlcvPopup.chiPhiLuongDn+vm.addQlcvPopup.vatDn;
			  vm.addQlcvPopup.gtQtCdtChuaVat=vm.addQlcvPopup.tongDn-vm.addQlcvPopup.vatDn;
			  vm.addQlcvPopup.gtQtCdtCoVat=vm.addQlcvPopup.tongDn;
// vm.addQlcvPopup.tongDn=vm.addQlcvPopup.tongDn+(vm.addQlcvPopup.tongDn*(vm.addQlcvPopup.vatDn/100));
		  }
		  	var d = new Date();
	  		var n = d.getFullYear();
	  		vm.date=n;
		  vm.totalTd=function(){
			  if(vm.addQlcvPopup.cpNhanCongTd===undefined||vm.addQlcvPopup.cpNhanCongTd===""){
				  vm.addQlcvPopup.cpNhanCongTd=0;
			  }
			  if(vm.addQlcvPopup.cpVatLieuTd===undefined||vm.addQlcvPopup.cpVatLieuTd===""){
				  vm.addQlcvPopup.cpVatLieuTd=0;
			  }
			  if(vm.addQlcvPopup.cpHshcTd===undefined||vm.addQlcvPopup.cpHshcTd===""){
				  vm.addQlcvPopup.cpHshcTd=0;
			  }
			  if(vm.addQlcvPopup.cpVanChuyenTd===undefined||vm.addQlcvPopup.cpVanChuyenTd===""){
				  vm.addQlcvPopup.cpVanChuyenTd=0;
			  }
			  if(vm.addQlcvPopup.chiPhiKhacTd===undefined||vm.addQlcvPopup.chiPhiKhacTd===""){
				  vm.addQlcvPopup.chiPhiKhacTd=0;
			  }
			  if(vm.addQlcvPopup.cpLuongTd===undefined||vm.addQlcvPopup.cpLuongTd===""){
				  vm.addQlcvPopup.cpLuongTd=0;
			  }
			  if(vm.addQlcvPopup.vatTd===undefined||vm.addQlcvPopup.vatTd===""){
				  vm.addQlcvPopup.vatTd=0;
			  }
			  vm.addQlcvPopup.tongTd=vm.addQlcvPopup.cpNhanCongTd+vm.addQlcvPopup.cpVatLieuTd
					  				+ vm.addQlcvPopup.cpHshcTd+vm.addQlcvPopup.cpVanChuyenTd
					  				+vm.addQlcvPopup.chiPhiKhacTd+vm.addQlcvPopup.cpLuongTd+vm.addQlcvPopup.vatTd;
			  vm.addQlcvPopup.gtTdPtkCoVat=vm.addQlcvPopup.tongTd;
			  vm.addQlcvPopup.gtTdPtkChuaVat=vm.addQlcvPopup.tongTd-vm.addQlcvPopup.vatTd;
		  }
		    
		    vm.save1 = function(){
		    	if(vm.addQlcvPopup.kqHdTkId==null){
// if(vm.addQlcvPopup.vatDn===""||vm.addQlcvPopup.vatDn===undefined){
// vm.messVAT=""
// }
		    		
		    		xlCvPtkService.saveAddCv(vm.addQlcvPopup).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			vm.addQlcvPopup={};
		    			toastr.success("Thêm hợp công việc quản lý thành công!");
		    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
		    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		    		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
		            });
		    	}else{
		    		
		    		if(vm.addQlcvPopup.ngayGuiPtkHshc==null||vm.addQlcvPopup.ngayGuiPtkHshc===""){
		    			vm.listmess.errMessage="Ngày nhận gửi PTK gửi HSHC không được để trống";
		    			if(vm.addQlcvPopup.ngayPtkTdXong==null||vm.addQlcvPopup.ngayPtkTdXong===""){
			    			vm.listmess.errMessage1="Ngày nhận gửi PTK gửi HSHC không được để trống";
			    		}else{
			    			vm.listmess.errMessage1="";
			    		}
		    			return vm.listmess;
		    		}else{
		    			vm.listmess.errMessage="";
		    		}
		    		if(vm.addQlcvPopup.ngayPtkTdXong==null||vm.addQlcvPopup.ngayGuiPtkHshc===""){
		    			vm.listmess.errMessage1="Ngày nhận gửi PTK gửi HSHC không được để trống";
		    			if(vm.addQlcvPopup.ngayGuiPtkHshc==null||vm.addQlcvPopup.ngayGuiPtkHshc===""){
		    				vm.listmess.errMessage="Ngày nhận gửi PTK gửi HSHC không được để trống";
			    		}else{
			    			vm.listmess.errMessage="";
			    		}
		    			if(vm.listmess.errMessage1!=""||vm.listmess.errMessage!=""){
		    				if(vm.listmess.errMessage1!=""){
		    					$("#ngayPtkTdXong").focus();
		    				}else{
		    					$("#ngayGuiPtkHshc").focus();
		    				}
		    				
		    			}
		    			
		    			return vm.listmess;
		    		}else{
		    			vm.listmess.errMessage1="";
		    		}
		    		
		    		vm.addQlcvPopup.checkTrinhKy=1;
		    		xlCvPtkService.updateHdTd(vm.addQlcvPopup).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			vm.addQlcvPopup={};
		    			toastr.success("Thẩm định hợp đồng thành công!");
		    			  var sizePage = $("#xlCvPtkGrid").data("kendoGrid").dataSource.total();
		    			  var pageSize = $("#xlCvPtkGrid").data("kendoGrid").dataSource.pageSize();
		    					if(sizePage % pageSize === 1){
		    								var currentPage = $("#xlCvPtkGrid").data("kendoGrid").dataSource.page();
		    								if (currentPage > 1) {
		    									$("#xlCvPtkGrid").data("kendoGrid").dataSource.page(currentPage - 1);
		    								}
		    							}
		    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
		    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		    		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thẩm định"));
		            });
		    	}
		    	
			}
		    vm.coppyVl=function(){
		    	vm.addQlcvPopup.cpNhanCongTd=vm.addQlcvPopup.cpNhanCongDn;
		    	vm.addQlcvPopup.cpVatLieuTd=vm.addQlcvPopup.cpVatLieuDn;
		    	vm.addQlcvPopup.cpHshcTd=vm.addQlcvPopup.cpHshcDn;
		    	vm.addQlcvPopup.cpVanChuyenTd=vm.addQlcvPopup.cpVanChuyenDn;
		    	vm.addQlcvPopup.cpKhacTd=vm.addQlcvPopup.chiPhiKhacDn;
		    	vm.addQlcvPopup.cpLuongTd=vm.addQlcvPopup.chiPhiLuongDn;
		    	vm.addQlcvPopup.vatTd=vm.addQlcvPopup.vatDn;
		    	vm.addQlcvPopup.tongTd=vm.addQlcvPopup.tongDn;
		    	vm.addQlcvPopup.gtTdPtkChuaVat=vm.addQlcvPopup.gtQtCdtChuaVat;
		    	vm.addQlcvPopup.gtTdPtkCoVat=vm.addQlcvPopup.gtQtCdtCoVat;
		    }
		    
		    vm.deny=function(){
		    	if(vm.addQlcvPopup.lyDoTc==null||vm.addQlcvPopup.lyDoTc===""){
					$("#descriptxx").focus();
					toastr.error("Bạn cần nhập lý do từ chối!!!");
					return
				}	
		    	vm.addQlcvPopup.checkTrinhKy=0;
	    		xlCvPtkService.updateHdTd(vm.addQlcvPopup).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			vm.addQlcvPopup={};
	    			toastr.success("Từ chối hợp đồng thành công!");
	    			  var sizePage = $("#xlCvPtkGrid").data("kendoGrid").dataSource.total();
	    			  var pageSize = $("#xlCvPtkGrid").data("kendoGrid").dataSource.pageSize();
	    					if(sizePage % pageSize === 1){
	    								var currentPage = $("#xlCvPtkGrid").data("kendoGrid").dataSource.page();
	    								if (currentPage > 1) {
	    									$("#xlCvPtkGrid").data("kendoGrid").dataSource.page(currentPage - 1);
	    								}
	    							}
	    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
	    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
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
		    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã tỉnh hoặc tên tỉnh",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.xlCvPtkSearch.tinh=dataItem.maDanhMuc;
		                    vm.xlCvPtkSearch.tenTinh=dataItem.tenDanhMuc;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#qlcvTinhAuto11").val().trim()}).then(function(response){
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
		                	if(processSearch('qlcvTinhAuto11',vm.selectedPrpject)){
		    					 $('#qlcvTinhAuto11').val("");
		    					 vm.xlCvPtkSearch.tenTinh="";
		    					 vm.xlCvPtkSearch.tinh="";
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
		                    vm.addQlcvPopup.tinh=dataItem.maDanhMuc;
		                    vm.addQlcvPopup.tenTinh=dataItem.tenDanhMuc;
// $('#qlcvTinhAuto11').val(dataItem.tenDanhMuc);
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
		                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#tinhQlCvPop").val()}).then(function(response){
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
		                	if(processSearch('tinhQlCvPop',vm.selectedPrpject)){
		    					 $('#tinhQlCvPop').val("");
		    					 vm.addQlcvPopup.tenTinh="";
		    					 vm.addQlcvPopup.tinh="";
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
		                    vm.xlCvPtkSearch.soHd=dataItem.soHd;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng đơn
									// vị
		                            return Restangular.all("kqHdTkRsServiceRest/getForAutoCompleteHD").post({pageSize:10, page:1, soHd:$("#tenShdAuto").val()}).then(function(response){
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
		                	if(processSearch('tenShdAuto',vm.selectedPrpject)){
		    					 $('#tenShdAuto').val("");
		    					 vm.xlCvPtkSearch.soHd="";
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
		                    vm.xlCvPtkSearch.soKhTc=dataItem.soKhTc;
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
		    					 vm.xlCvPtkSearch.soKhTc="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};
		       
			 
		        vm.exportExcelGrid= function(){
		        	if(vm.exportHD.soHd==null||vm.exportHD.soHd===""){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập số hợp đồng"));
		        		return;
		        	}
					vm.exportHD.page=null;
					vm.exportHD.pageSize=null;
					xlCvPtkService.exportExcelGrid(vm.exportHD).then(function(result){
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
// xlCvPtkService.exportExcelGrid(vm.exportHD).then(function(result){
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
					var teamplateUrl="qll/xlCvPtk/addUpdateHdTdPopup3.html";
				    var title="Xuất báo cáo lương theo tháng";
				    var windowId="EX_SLY";
				    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','40%');
				}
		        
		        vm.xuatBCLuong= function(){
		        	if(vm.exportLuongHD.thangGhiNhanQuyLuongTqt==""){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập tháng xuất quỹ lương"));
		        		return;
		        	}
					xlCvPtkService.xuatBCLuong(vm.exportLuongHD).then(function(result){
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
				}
		        
		        vm.removeDetail= function(dataItem){
		        	confirm('Bạn có chắc chắn muốn xóa bản ghi?', function(){
		        		xlCvPtkService.deleteObj(dataItem).then(function(result){
								toastr.success("Xóa bản ghi thành công!");
								vm.doSearch();
						});	
		        	});
					
				}
		        
		        vm.xuatBCtheoDM= function(){
		        	if(vm.xuatBCDMV.listTinhTrangChuyenUngTu==""||vm.xuatBCDMV.listTinhTrangChuyenUngTu==null){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập tháng xuất quỹ lương"));
		        		return;
		        	}
					xlCvPtkService.xuatBCTheoDauViec(vm.xuatBCDMV).then(function(result){
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
		        
		        vm.exportExcelGrid1 = function(){
					vm.oldSearch.page=null;
					vm.oldSearch.pageSize=null;
					xlCvPtkService.exportRowAll(vm.oldSearch).then(function(result){
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
		        vm.xuatBCTHCV= function(){
					xlCvPtkService.xuatBCTHCV().then(function(result){
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
		        
		        vm.xuatBCTHCon= function(){
		        	if(vm.exportHD.ngayGuiPtkHshc==null&&vm.exportHD.thangHshcQuyLuong==null){
		        		toastr.error(gettextCatalog.getString("Bạn cần nhập ngày hoặc tháng"));
		        		return;
		        	}
					xlCvPtkService.xuatBCTH(vm.exportHD).then(function(result){
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
// $("#xlCvPtkGrid").data('kendoGrid').refresh();
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
	        	xlCvPtkService.updateSLByNV(vm.updateSLNV113).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Từ chối thành công!");
	    			$("#xlCvPtkGrid").data('kendoGrid').dataSource.read();
	    			$("#xlCvPtkGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		    
		    
	        // autocomplite CNKV
			  vm.patternOptions11={
		    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã chi nhánh hoặc tên chi nhánh",
		                select: function(e) {
		                    var dataItem = this.dataItem(e.item.index());
		                    vm.xlCvPtkSearch.cnkv=dataItem.maDanhMuc;
		                    vm.xlCvPtkSearch.tenDanhMuc=dataItem.tenDanhMuc;
		                },
		                pageSize: 10,
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: function(options) {
		                        	// link do search don vị thiếu do chưa có
									// bảng
									// đơn
									// vị
		                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#chiNhanhPtkAuto").val().trim()}).then(function(response){
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
		                	if(processSearch('chiNhanhPtkAuto',vm.selectedPrpject)){
		    					 $('#chiNhanhPtkAuto').val("");
		    					 vm.xlCvPtkSearch.cnkv="";
		    					 vm.xlCvPtkSearch.maChiNhanh="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};	
			  
			  vm.patternOptionsCnkv={
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
		    					 vm.addQlcvPopup.cnkv="";
		    					 vm.addQlcvPopup.maChiNhanh="";
		    					  vm.selectedPrpject=false;	
		    					  }
		                },
		                close: function(e) {
		                    // handle the event
		                  }
		    		};		
		
		
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
				vm.xlCvPtkGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.xlCvPtkGrid.showColumn(column);
			} else {
				vm.xlCvPtkGrid.hideColumn(column);
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
