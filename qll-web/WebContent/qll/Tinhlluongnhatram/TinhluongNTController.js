(function() {
	'use strict';
	var controllerId = 'TinhluongNTController';

	angular.module('MetronicApp').controller(controllerId, TinhluongNTController);

	function TinhluongNTController($scope, $rootScope, $timeout, gettextCatalog,
			kendoConfig, $kWindow, TinhluongNTService, CommonService, PopupConst,
			Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.TinhluongNTSearch = {};

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
						scrollable : true,
						sortable : false,
						dataBinding : function() {
							record = (this.dataSource.page() - 1)
									* this.dataSource.pageSize();
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
									$("#TinhluongNTCount")
											.text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblLuongTungTramServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.TinhluongNTSearch.page = options.page;
									vm.TinhluongNTSearch.pageSize = options.pageSize;
									// vm.oldSearch=angular.copy(vm.TinhluongNTSearch);
									return JSON.stringify(vm.TinhluongNTSearch);

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
									template : '<div class="text-center #=tblLuongTungTramId#""> '
											+
											'		<button style=" border: none; " class="#=tblLuongTungTramId# icon_table"   uib-tooltip="Cập nhật" translate>'
											+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblLuongTungTramId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
											+
											'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
											+ '		</button>' + '</div>',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;"
									}
								},

								{
									title : "Tháng",
									field : 'thang',
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
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Huyện",
									field : 'huyen',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Mã Nhân Viên",
									field : 'maNv',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Họ tên",
									field : 'hoTen',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Trạm",
									field : 'tram',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Vùng lương",
									field : 'vungLuong',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Phạt lỗi 1",
									field : 'phatLoi1',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Phạt lỗi 2",
									field : 'phatLoi2',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Phạt lỗi 3",
									field : 'phatLoi3',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Phạt lỗi 4",
									field : 'phatLoi4',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Phạt lỗi 5",
									field : 'phatLoi5',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Phạt lỗi 6",
									field : 'phatLoi6',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Địa hình",
									field : 'diaHinh',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Đơn giá",
									field : 'donGia',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Loại Trạm",
									field : 'loaiTram',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Lương duy trì từng trạm",
									field : 'luongDuyTriTungTram',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Lương",
									field : 'luong',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Ngày công chế độ",
									field : 'ngayCongCheDo',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Ngày công tính lương",
									field : 'ngayCongTinhLuong',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
									title : "Hệ số điều chỉnh",
									field : 'heSoDieuChinh',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:right;"
									},
								},
								{
								title : "KPI trạm",
								field : 'kpiTram',
								format:"{0:#,###.0}",
								width : '150px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:right;"
								},
								},
								{
								title : "KI đơn vị",
								field : 'kiDonVi',
								format:"{0:#,###.0}",
								width : '150px',
								headerAttributes : {
									style : "text-align:center;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:right;"
								},
								}]

					});
		}

		vm.doSearch = doSearch;
		function doSearch() {
			vm.myFunction();
			vm.showDetail = false;
			var grid = vm.TinhluongNTGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}

       
        vm.exportExcelGrid= function(){
			vm.TinhluongNTSearch.page=null;
			vm.TinhluongNTSearch.pageSize=null;
			TinhluongNTService.exportExcelGrid(vm.TinhluongNTSearch).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
					vm.doSearch();
					// return;
				} 

			});	
			//toastr.success("hello !!!");
		}
        vm.tinhluong= function(){
        	vm.myFunction();
			vm.TinhluongNTSearch.page=null;
			vm.TinhluongNTSearch.pageSize=null;
			TinhluongNTService.tinhluong(vm.TinhluongNTSearch).then(function(result){
				
					// return;

			});	
			vm.doSearch();
			//toastr.success("hello !!!");
		}
//        vm.tinhluong= function(){
//			vm.TinhluongNTSearch.page=null;
//			vm.TinhluongNTSearch.pageSize=null;
//			TinhluongNTService.tinhluong(vm.TinhluongNTSearch).then(function(result){
//				if (result.fileName) {
//					toastr.success("Tính lương thành công!");
//				}
//				} 
//
//			});	
//			//toastr.success("hello !!!");
//		}
        $scope.showBtns = false;
       vm.hideTL = function () {
    	   
        	if(vm.TinhluongNTSearch.tinh=="" || document.getElementById("dateTinhluongNT").value=="")
        		{
        		$scope.showBtns = false;
        		}
        	else
        		{
        		$scope.showBtns = true;
        		}
        }
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.TinhluongNTGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.TinhluongNTGrid.showColumn(column);
			} else {
				vm.TinhluongNTGrid.hideColumn(column);
			}

		}
		vm.downloadImportTemplate = function(){

			TinhluongNTService.downloadImportTemplate().then(function(result){
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
		vm.clearSearchDate = function(column) {
			vm.TinhluongNTSearch.exNam = null;
			vm.TinhluongNTSearch.exThang = null;
		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("dateTinhluongNT").value;
		    var d = new Date(document.getElementById("dateTinhluongNT").value);
		    vm.TinhluongNTSearch.exThang =1 + d.getMonth();
		    vm.TinhluongNTSearch.exNam = d.getFullYear();
		}
		vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập tên tỉnh",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.TinhluongNTSearch.donVi=dataItem.maDanhMuc;
                    vm.TinhluongNTSearch.tinh=dataItem.tenDanhMuc;
                    vm.TinhluongNTSearch.maTinh=dataItem.maDanhMuc;
//                    $('#TinhluongNTAuto').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#TinhluongNTAuto").val().trim()}).then(function(response){
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
                	if(processSearch('TinhluongNTAuto',vm.selectedPrpject)){
    					 $('#TinhluongNTAuto').val("");
    					 vm.TinhluongNTSearch.tinh="";
    					 vm.TinhluongNTSearch.maDanhMuc="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
//END CONTROLLER
	}
})();
