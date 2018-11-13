
(function() {
	'use strict';
	var controllerId = 'bcThucXuatTheoKyController';
	
	angular.module('MetronicApp').controller(controllerId, bcThucXuatTheoKyController);
	
	function bcThucXuatTheoKyController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, bcThucXuatTheoKyService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.bctxtkSearch = {};
		vm.chiNhanhKV={};
		vm.u62TTKT={};
		vm.pxkNhanVien={};
		vm.updateSLNV111={};
		vm.updateSLNV1112={};
		
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
			updateSL([]);
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
		      					'ng-click="vm.importFile()" uib-tooltip="Import" ng-show="RoleMenu.checkRole.checkRoleImport"  aria-hidden="true" translate>Import</button>'+
		      					'</div>'+
		      					'</div>'	
		      					+
		      					 
							'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.bctxtkGrid.columns.slice(1,vm.bctxtkGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
		                    dataBound: function (e) {
// var grid = vm.bctxtkGrid;
// grid.tbody.find("tr").dblclick(function (e) {
// var dataItem = grid.dataItem(this);
// vm.showDetail(dataItem)
// });
		    				},
							dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
						 
						 $("#bctxtkGridCount").text(""+response.total);
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
						
						// vm.bctxtkSearch.keySearch = "";
						vm.bctxtkSearch.page = options.page
						vm.bctxtkSearch.pageSize = options.pageSize     
						if($rootScope.RoleMenu.roleCode!=6){
							vm.bctxtkSearch.donViNhan=$rootScope.RoleMenu.donVi
							}
						return JSON.stringify(vm.bctxtkSearch)
						}
					},					 
					pageSize: 10
				} ,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes:  [ 10, 15, 20, 25 ],
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
				},{
					title : "Thao tác",
					template : '<div class="text-center #=tblQltsThucXuatTheoKyId#""> '
// +
//
// ' <button style=" border: none; " class="#=tblQltsThucXuatTheoKyId#
// icon_table" aria-hidden="true"
// ng-show="dataItem.maTinh==null&&RoleMenu.checkRoleFt1A" uib-tooltip="Gán chi
// nhánh 5 KV" translate>'
// + ' <i class="fa gankhuvuc" style="margin-top: 10px;"
// ng-click="vm.openChiNhanh(dataItem)" aria-hidden="true"></i> '
// + ' </button>'
//							
// +
// ' <button style=" border: none; " class="#=tblQltsThucXuatTheoKyId#
// icon_table" aria-hidden="true"
// ng-show="dataItem.maChiNhanh==null&&RoleMenu.checkRoleFt2A" uib-tooltip="Gán
// 62 TTKT" translate>'
// + ' <i class="fa gandonvi" ng-click=" vm.open62KTTK(dataItem)"
// aria-hidden="true"></i> '
// + ' </button>'
//							
// +
// ' <button style=" border: none; " class="#=tblQltsThucXuatTheoKyId#
// icon_table" aria-hidden="true" ng-show="RoleMenu.checkRoleFt3A"
// uib-tooltip="Gán PXK cho nhân viên" translate>'
// + ' <i class="fa phanchonhanvien" ng-click="vm.updatePXKNV(dataItem)"
// aria-hidden="true"></i> '
// + ' </button>'
//							
// +
// ' <button style=" border: none; " class="#=tblQltsThucXuatTheoKyId#
// icon_table" aria-hidden="true" ng-show="RoleMenu.checkRoleFt4A"
// uib-tooltip="Nhân viên cập nhật số lượng" translate>'
// + ' <i class="fa capnhatsolieu" ng-click="vm.updateSLNV(dataItem)"
// aria-hidden="true"></i> '
// + ' </button>'
//							
// +
// ' <button style=" border: none; " class="#=tblQltsThucXuatTheoKyId#
// icon_table" aria-hidden="true" ng-show="RoleMenu.checkRoleFt5A"
// uib-tooltip="Xác nhận thông tin của nhân viên" translate>'
// + ' <i class="fa xacnhansolieu" ng-click="vm.updateSLNV1(dataItem)"
// aria-hidden="true"></i> '
// + ' </button>'
							
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
							+

							'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
							+ '		</button>' 
							+ '</div>',
					width : '300px',
					headerAttributes : {
						style : "text-align:center;font-weight:bold;white-space:normal;"
					}
				}
				,  {
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
				},  {
					title: "Số phiếu xuất bên Logistic",
					field: "soPhieuXuatBenLogistic",
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
					title: "Mã kho nhận",
					field: "maKhoNhan",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên kho nhận",
					field: "khoNhan",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị nhận",
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
				}]
			});
		}
		
		
		
		function updateSL(data) {
			vm.grid1Options = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
				dataSource: data ,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: false,
				columns: [
				{
					title: "TT",
					field:"stt",
			        template: function () {
					  return 1;
					 },
			        width: '50px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, {
					title: "Mã phiếu xuất",
					field: "maPhieuXuat",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số phiếu xuất",
					field: "soPhieuXuat",
			        width: '200px',
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
				},]
			});
		}
		
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.bctxtkGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.bctxtkGrid.showColumn(column);
			} else {
				vm.bctxtkGrid.hideColumn(column);
			}

		}
		vm.doSearch =function() {
			if(!vm.validator.validate()){
				// $("#exReqManaCreateFromDate").focus();
				return;
			}
			if(vm.bctxtkSearch.ngayXuatFrom == undefined || vm.bctxtkSearch.ngayXuatFrom == ""){
				vm.bctxtkSearch.ngayXuatFrom = null;
			}
			if(vm.bctxtkSearch.ngayXuatTo == undefined || vm.bctxtkSearch.ngayXuatTo == ""){
				vm.bctxtkSearch.ngayXuatTo = null;
			}
			if(vm.bctxtkSearch.ngayXuatFrom != null && vm.bctxtkSearch.ngayXuatTo == null){
				return;
			}
			if(vm.bctxtkSearch.ngayXuatFrom == null && vm.bctxtkSearch.ngayXuatTo != null){
				return;
			}
			var grid =vm.bctxtkGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    } 
		
		vm.downloadImportTemplate = function(){
			var fileName="TemplateThucXuatTypeA";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}
		
		
		vm.importFile= function(){
			var teamplateUrl="qll/bcThucXuatTheoKy/importPopup.html";
		    var title="Import file";
		    var windowId="IMPORT_BCTXTK";
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
    	
    	var t0 = performance.now();
    	$("#upfile").css("display","none");
		$("#modalLoading").css("display","block");
		var loading = $("#modalLoading");
         kendo.ui.progress(loading, true);
    	
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
        
        vm.updateChiNhanh= function(){
        	bcThucXuatTheoKyService.updateChiNhanh(vm.chiNhanhKV).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#bctxtkGrid").data('kendoGrid').dataSource.read();
    			$("#bctxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        
        vm.update62TTKT= function(){
        	bcThucXuatTheoKyService.updateChiNhanh(vm.u62TTKT).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#bctxtkGrid").data('kendoGrid').dataSource.read();
    			$("#bctxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        
        vm.updatePXKNhanVien= function(){
        	bcThucXuatTheoKyService.updateChiNhanh(vm.pxkNhanVien).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#bctxtkGrid").data('kendoGrid').dataSource.read();
    			$("#bctxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        var donGia111=0;
        var donGia1112=0;
        
        vm.nvUpdateSL= function(){
        	bcThucXuatTheoKyService.updateChiNhanh(vm.updateSLNV111).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#bctxtkGrid").data('kendoGrid').dataSource.read();
    			$("#bctxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        
        vm.nvUpdateSL1= function(){
        	vm.updateSLNV1112.slThucTeTC=null;
        	vm.updateSLNV1112.ttThucTeTC=null;
        	vm.updateSLNV1112.slThuHoi=null;
        	vm.updateSLNV1112.ttThuHoi=null;
        	vm.updateSLNV1112.slTienDen=null;
        	vm.updateSLNV1112.ttTienDen=null;
        	bcThucXuatTheoKyService.updateChiNhanh(vm.updateSLNV1112).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Từ chối thành công!");
    			$("#bctxtkGrid").data('kendoGrid').dataSource.read();
    			$("#bctxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        
        vm.cancel11=function(){
        	toastr.success("Xác nhận thành công!");
        	$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
        }
        
        vm.tinhTien=function(){
        	vm.updateSLNV111.ttThucTeTC=vm.updateSLNV111.slThucTeTC*donGia111;
        }
        vm.tinhTien1=function(){
        	vm.updateSLNV111.ttThuHoi=vm.updateSLNV111.slThuHoi*donGia111;
        }
        vm.tinhTien2=function(){
        	vm.updateSLNV111.ttTienDen=vm.updateSLNV111.slTienDen*donGia111;
        }
        
        vm.tinhTien3=function(){
        	vm.updateSLNV1112.ttThucTeTC=vm.updateSLNV1112.slThucTeTC*donGia1112;
        }
        vm.tinhTien4=function(){
        	vm.updateSLNV1112.ttThuHoi=vm.updateSLNV1112.slThuHoi*donGia1112;
        }
        vm.tinhTien5=function(){
        	vm.updateSLNV1112.ttTienDen=vm.updateSLNV1112.slTienDen*donGia1112;
        }
        
        vm.openChiNhanh= function(dataItem){
        	vm.chiNhanhKV=dataItem;
			var teamplateUrl="qll/bcThucXuatTheoKy/popupGanChiNhanh5KV.html";
		    var title="Gán chi nhánh 5 KV";
		    var windowId="GAN_CHI_NHANH";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
		}
        
        vm.open62KTTK= function(dataItem){
        	vm.u62TTKT=dataItem;
			var teamplateUrl="qll/bcThucXuatTheoKy/popupGan62TTKT.html";
		    var title="Gán 62 TTKT";
		    var windowId="GAN_62_TTKT";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
		}
        
        vm.updatePXKNV = function(dataItem){
        	vm.pxkNhanVien=dataItem;
        	
			var teamplateUrl=" qll/bcThucXuatTheoKy/popupPXKChoNV.html";
		    var title="Gán PXK cho nhân viên";
		    var windowId="GAN_PXK_NV";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
		}
       
        vm.updateSLNV = function(dataItem){
        	 var list=[];
        	vm.updateSLNV111=dataItem;
        	list.push(dataItem);
        	updateSL(list);
        	donGia111=dataItem.donGia;
			var teamplateUrl="qll/bcThucXuatTheoKy/nhanVienUpdateSl.html";
		    var title="Nhân viên cập nhật số lượng";
		    var windowId="NV_CAP_NHAT_SL";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','50%');
		}
        
        
        
        vm.updateSLNV1 = function(dataItem){
        	if(dataItem.ttThucTeTC==null||dataItem.slThucTeTC==null||dataItem.ttTienDen==null
        			||dataItem.slTienDen==null||dataItem.ttThuHoi==null||dataItem.slThuHoi==null){
        		toastr.error("Cần nhập đầy đủ thông tin nhân viên cho số lượng!!!");
        		return;
        	}
	       	var list=[];
	       	vm.updateSLNV1112=dataItem;
	       	list.push(dataItem);
	       	updateSL(list);
	       	donGia1112=dataItem.donGia;
			var teamplateUrl="qll/bcThucXuatTheoKy/xacNhanTTNhanVienUpdateSl2.html";
		    var title="Xác nhận thông tin cập nhật số lượng";
		    var windowId="NV_CAP_NHAT_SL";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','50%');
		}
        
        
       
// vm.downloadImportTemplate = function(){
// var fileName="TempBaoCaoThucXuatTheoKy";
// CommonService.downloadTemplate(fileName).then(function(d) {
// data = d.plain();
// window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" +
// data.fileName;
// }).catch( function(){
// toastr.error(gettextCatalog.getString("Lỗi khi export!"));
// return;
// });
//		
// }
        
        vm.patternOptions={
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
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
                '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
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
        
        
        
        
        
        vm.patternOptionsNV={
    			dataTextField: "hoVaTen", placeholder:"Nhập mã hoặc tên nhân viên",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.pxkNhanVien.maNv=dataItem.maNv;
                    vm.pxkNhanVien.hoVaTen=dataItem.hoVaTen;
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng
							// đơn
							// vị
                            return Restangular.all("tblNhanVienServiceRest/getForAutoCompleteNV").post({pageSize:10, page:1, name:$("#pxkNhanVienAuto").val().trim()}).then(function(response){
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
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maNv #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.hoVaTen #</div> </div>',
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
	   
        
        
        
		// end
		}
})();
									
				
