
(function() {
	'use strict';
	var controllerId = 'impThucXuatTheoKyController';
	
	angular.module('MetronicApp').controller(controllerId, impThucXuatTheoKyController);
	
	function impThucXuatTheoKyController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, impThucXuatTheoKyService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.impSearch = {};
		
		$(document).ready(function() {
			// getApply();
			fillDataTable();
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		});
// function getApply(){
// kiDonViService.getApply(vm.appParamSearch).then(function(result){
// vm.dataAppParamType = result;
// vm.reasonSearch.listApply=[vm.dataAppParamType[0].code];
				 // fillDataTable();
// });
		
		initFormData();
		function initFormData() {
			fillDataTable([]);
			
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
		// table chinh
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
				
				toolbar: [
		                    {
		                    	name: "actions",
		                        template: '<div class=" pull-left">'+
		                        '<button class="btn btn-qlk padding-search-right TkQLK margin_right10"'+
		      					'ng-click="vm.importFile()" uib-tooltip="Import" translate>Import</button>'
		      					+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
								+ 'ng-click="vm.exportExcelGrid()" uib-tooltip="Xuất báo cáo tổng hợp toàn công ty" translate>Xuất BC tổng hợp</button>'+
		      					'</div>'+
		      					'</div>'	
		      					+
		      					 
							'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
// '<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"
// aria-expanded="false"><span class="tooltipArrow"></span><span
// class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.impGrid.columns.slice(1,vm.impGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
		                    dataBound: function (e) {
		    				    var grid = vm.impGrid;
		    				    grid.tbody.find("tr").dblclick(function (e) {
		    				        var dataItem = grid.dataItem(this);
		    				        vm.showDetail(dataItem)
		    				    });
		    				},
							dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
						 
						 $("#impCount").text(""+response.total);
							 	vm.count = response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].orderId===$scope.listCheck[i].orderId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list;// data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "tblQltsThucXuatTheoKyServiceRest/doSearchBctxtk",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
						// vm.impSearch.keySearch = "";
						vm.impSearch.page = options.page
						vm.impSearch.pageSize = options.pageSize                               

								return JSON.stringify(vm.impSearch)
						}
					},					 
					pageSize:10
				} ,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes:   [ 10, 15, 20, 25 ],
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
					  return ++record;
					 },
			        width: '50px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,{
					title : "Thao tác",
					template : '<div class="text-center #=tblQltsThucXuatTheoKyId#""> '
							+

							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"   uib-tooltip="Gán chi nhánh 5 KV" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"   uib-tooltip="Gán 62 TTKT" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"   uib-tooltip="Gán PXK cho nhân viên" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"   uib-tooltip="Nhân viên cập nhật số lượng" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"   uib-tooltip="Xác nhận thông tin của nhân viên" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							
							
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"   uib-tooltip="Gán PXK cho nhân viên" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							
							
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
							+

							'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
							+ '		</button>' + '</div>',
					width : '300px',
					headerAttributes : {
						style : "text-align:center;font-weight:bold;white-space:normal;"
					}
				},  {
					title: "Số phiếu xuất",
					field: "soPhieuXuat",
			        width: '200px',
					 // template: '<a class="#=tblQltsThucXuatTheoKyId#"
						// href="javascript:void(0);"
						// ng-click=vm.showDetail(dataItem)>#=tblQltsThucXuatTheoKyId#</a>',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ngày xuất",
			        field: "ngayXuat",
			        width: '100px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Nội dung",
			        field: "noiDung",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Mã kho xuất",
			        field: "maKhoXuat",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
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
				},{
					title: "Số lượng xuất",
					field: "soLuongXuat",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn giá",
					field: "donGia",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thành tiền",
					field: "thanhTien",
			        width: '200px',
			        format: "{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Mã công trình",
					field: "maCongTrinh",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng Y.Cầu",
					field: "soLuongYeuCau",
			        width: '200px',
			        format: "{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Lý do",
					field: "lyDo",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Diễn giải",
					field: "dienGiai",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên kho xuất",
					field: "tenKhoXuat",
			        width:'200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Mã kho nhận",
					field: "maKhoNhan",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Kho nhận",
					field: "khoNhan",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị nhận",
					field: "donViNhan",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Người nhận hàng",
					field: "nguoiNhanHang",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},]
			});
		}
		
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.impGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.impGrid.showColumn(column);
			} else {
				vm.impGrid.hideColumn(column);
			}

		}
		vm.doSearch =function() {
			if(!vm.validator.validate()){
				// $("#exReqManaCreateFromDate").focus();
				return;
			}
			if(vm.impSearch.ngayXuatFrom == undefined || vm.impSearch.ngayXuatFrom == ""){
				vm.impSearch.ngayXuatFrom = null;
			}
			if(vm.impSearch.ngayXuatTo == undefined || vm.impSearch.ngayXuatTo == ""){
				vm.impSearch.ngayXuatTo = null;
			}
			if(vm.impSearch.ngayXuatFrom != null && vm.impSearch.ngayXuatTo == null){
				return;
			}
			if(vm.impSearch.ngayXuatFrom == null && vm.impSearch.ngayXuatTo != null){
				return;
			}
			var grid =vm.impGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    } 
		
		vm.downloadImportTemplate = function(){
			var fileName="BAOCAOTHEOKY";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}
		
		
		vm.importFile= function(){
			var teamplateUrl="qll/quanLyVatTuACap/importThucXuatTheoKy/importPopup.html";
		    var title="Import dữ liệu thực xuất theo kỳ";
		    var windowId="IMPORT_PXK";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','25%');
		}
		
		// import file
		vm.submit=submit;
        function submit(){
    	
    	if($("#file")[0].files[0] == null){
    		toastr.warning("Bạn chưa chọn file để import");
    		return;
    	}
    	if($("#file")[0].files[0].name.split('.').pop() !='xls' && $("#file")[0].files[0].name.split('.').pop() !='xlsx' ){
    		toastr.warning("Sai định dạng file");
    		return;
    	}
        var formData = new FormData();
		formData.append('multipartFile', $('#file')[0].files[0]); 
     return   $.ajax({
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_QLTS_THUC_XUAT_THEO_KY_SERVICE_URL +"/importFile?folder="+ vm.folder,
            type: "POST",
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success:function(data) {
            	if(data.fileName){
					toastr.error("Có lỗi trong file import, kiểm tra lại file trả về");
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					// return;
				}else 
            	{
    			toastr.success("Import thành công!");
            	}
            	vm.doSearch();
            	CommonService.closePopup1();
            }
        });
      
    
    }
		// end
		}
})();
									
				
