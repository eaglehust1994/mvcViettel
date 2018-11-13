
(function() {
	'use strict';
	var controllerId = 'exportThucXuatTheoKyController';
	
	angular.module('MetronicApp').controller(controllerId, exportThucXuatTheoKyController);
	
	function exportThucXuatTheoKyController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant,exportThucXuatTheoKyService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.exporttxtkSearch = {};
		vm.typeKASearch={};
		vm.oldSearch={};
		
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
		 $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
		 
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
		                        template: '<div class=" pull-left">'
		      					+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC"'  
								+ 'ng-click="vm.exportExcelGridForNV()" uib-tooltip="Xuất báo cáo theo nhân viên"  aria-hidden="true" translate>Xuất báo cáo TH nhân viên</button>'
								+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC"'  
								+ 'ng-click="vm.exportExcelGridForDV()" uib-tooltip="Xuất báo cáo theo đơn vị"  aria-hidden="true" translate>Xuất báo cáo TH đơn vị</button>'
								+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC"'  
								+ 'ng-click="vm.exportTHDoiSoatVatTu()" uib-tooltip="Xuất báo cáo thi tiết"  aria-hidden="true" translate>Xuất báo cáo TH theo PXK</button>'
								+'<div class=" pull-right">'
									+ '<input  k-options="vm.patternOptionsHDXL" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC" kendo-auto-complete style="height:30px;outline:auto;margin-right:10px;" type="text" ng-model="vm.typeKASearch.maHdxl" id="maHdxl" aria-hidden="true" />'
									+ '<input  ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC" k-options="vm.patternOptionsTram" kendo-auto-complete style="height:30px;outline:auto;" type="text" ng-model="vm.typeKASearch.maTramNhan" id="maTramNhan" aria-hidden="true" />'
								+'</div>'
//								+ '<button  style="width: auto;text-align:center;" ng-hide="checkExportPXK"'  
//								+ 'ng-click="vm.checkExistHD()" uib-tooltip="Kiểm tra tồn tại HĐXL"  aria-hidden="true" translate>Kiểm tra</button>'
		      					+'</div>'+
		      					'</div>'	
		      					+
		      					 
							'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.exporttxtkGrid.columns.slice(1,vm.exporttxtkGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
		                    dataBound: function (e) {
// var grid = vm.exporttxtkGrid;
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
						 
						 $("#bctxtkGridCount11").text(""+response.total);
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
						
								// vm.exporttxtkSearch.keySearch = "";
								vm.exporttxtkSearch.page = options.page
								vm.exporttxtkSearch.pageSize = options.pageSize                               
								vm.oldSearch = angular.copy(vm.exporttxtkSearch);
								return JSON.stringify(vm.exporttxtkSearch)
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
				},{
					title : "Thao tác",
					template : '<div class="text-center #=tblQltsThucXuatTheoKyId#""> '
							+
							'		<button style=" border: none; " class="#=tblQltsThucXuatTheoKyId# icon_table" ng-hide="dataItem.maTinh!=null"  uib-tooltip="Huỷ" translate>'
							+ '			<i class="fa fa-trash" style="margin-top: 10px;"   aria-hidden="true"></i> '
							+ '		</button>'
							
							 + '</div>',
					width : '50px',
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
			        format:"{0:#,###}",
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
			        format: "{0:#,###}",
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
				vm.exporttxtkGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.exporttxtkGrid.showColumn(column);
			} else {
				vm.exporttxtkGrid.hideColumn(column);
			}

		}
		vm.doSearch =function() {
			if(!vm.validator.validate()){
				// $("#exReqManaCreateFromDate").focus();
				return;
			}
			if(vm.exporttxtkSearch.ngayXuatFrom == undefined || vm.exporttxtkSearch.ngayXuatFrom == ""){
				vm.exporttxtkSearch.ngayXuatFrom = null;
			}
			if(vm.exporttxtkSearch.ngayXuatTo == undefined || vm.exporttxtkSearch.ngayXuatTo == ""){
				vm.exporttxtkSearch.ngayXuatTo = null;
			}
			if(vm.exporttxtkSearch.ngayXuatFrom != null && vm.exporttxtkSearch.ngayXuatTo == null){
				return;
			}
			if(vm.exporttxtkSearch.ngayXuatFrom == null && vm.exporttxtkSearch.ngayXuatTo != null){
				return;
			}
			var grid =vm.exporttxtkGrid;	
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
	    	$("#upfile").css("display","none");
			$("#modalLoading").css("display","block");
			var loading = $("#modalLoading");
	         kendo.ui.progress(loading, true);
	         var t0 = performance.now();
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
        	exportThucXuatTheoKyService.updateChiNhanh(vm.chiNhanhKV).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#exporttxtkGrid").data('kendoGrid').dataSource.read();
    			$("#exporttxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        
        vm.update62TTKT= function(){
        	exportThucXuatTheoKyService.updateChiNhanh(vm.u62TTKT).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#exporttxtkGrid").data('kendoGrid').dataSource.read();
    			$("#exporttxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        
        vm.updatePXKNhanVien= function(){
        	exportThucXuatTheoKyService.updateChiNhanh(vm.pxkNhanVien).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#exporttxtkGrid").data('kendoGrid').dataSource.read();
    			$("#exporttxtkGrid").data('kendoGrid').refresh();
    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
            });
        }
        var donGia111=0;
        var donGia1112=0;
        
        vm.nvUpdateSL= function(){
        	exportThucXuatTheoKyService.updateChiNhanh(vm.updateSLNV111).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Cập nhật thành công!");
    			$("#exporttxtkGrid").data('kendoGrid').dataSource.read();
    			$("#exporttxtkGrid").data('kendoGrid').refresh();
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
        	exportThucXuatTheoKyService.updateChiNhanh(vm.updateSLNV1112).then(function(result){
    			if(result.error){
    				toastr.error(result.error);
    				return;
    			}
    			toastr.success("Từ chối thành công!");
    			$("#exporttxtkGrid").data('kendoGrid').dataSource.read();
    			$("#exporttxtkGrid").data('kendoGrid').refresh();
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
        
        
        
    	vm.exportExcelGridForNV = function() {
    		
    		if((vm.oldSearch.ngayXuatFrom==null&&vm.oldSearch.ngayXuatTo!=null)||(vm.oldSearch.ngayXuatFrom!=null&&vm.oldSearch.ngayXuatTo==null)){
				toastr.error("Bạn phải nhập đủ ngày từ và ngày đến");
				return;
			}
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			exportThucXuatTheoKyService.exportExcelGrid(vm.oldSearch).then(
					function(result) {
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ result.fileName;
							// return;
						}

					});
		}
    	
    	vm.exportExcelGridForDV = function() {
    		if((vm.oldSearch.ngayXuatFrom==null&&vm.oldSearch.ngayXuatTo!=null)||(vm.oldSearch.ngayXuatFrom!=null&&vm.oldSearch.ngayXuatTo==null)){
				toastr.error("Bạn phải nhập đủ ngày từ và ngày đến");
				return;
			}
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			exportThucXuatTheoKyService.exportExcelGrid1(vm.oldSearch).then(
					function(result) {
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ result.fileName;
							// return;
						}

					});
		}
    	
    	vm.checkExistHD=function(){
    		if(vm.typeKASearch.maHdxl ==null||vm.typeKASearch.maHdxl==""){
    			toastr.error("Bạn cần nhập mã HĐXL");
    			return;
    		}
    			exportThucXuatTheoKyService.checkMaHdxl(vm.typeKASearch).then(
					function(result) {
						if (result.length==0) {
							toastr.error("Mã HĐXL không tồn tại trong báo cáo thực xuất theo kỳ");
							return;
						}else{
							toastr.success("Mã HĐXL tồn tại trong báo cáo thực xuất theo kỳ");
							return;
						}
					});
    	}
    	
    	vm.exportTHDoiSoatVatTu = function() {
    		
    		if(vm.typeKASearch.maHdxl ==null||vm.typeKASearch.maHdxl==""){
    			toastr.error("Bạn cần nhập mã HĐXL");
    			return;
    		}
    		exportThucXuatTheoKyService.checkMaHdxl(vm.typeKASearch).then(
					function(result) {
						if (result.size==0) {
							toastr.error("Mã HĐXL không tồn tại trong báo cáo thực xuất theo kỳ");
							return;
						}
					});
    		if((vm.oldSearch.ngayXuatFrom==null&&vm.oldSearch.ngayXuatTo!=null)||(vm.oldSearch.ngayXuatFrom!=null&&vm.oldSearch.ngayXuatTo==null)){
				toastr.error("Bạn phải nhập đủ ngày từ và ngày đến");
				return;
			}else{
				vm.typeKASearch.ngayThucXuatFrom=vm.oldSearch.ngayXuatFrom;
	    		vm.typeKASearch.ngayThucXuatTo=vm.oldSearch.ngayXuatTo;
			}
			exportThucXuatTheoKyService.exportTHDoiSoatVatTu(vm.typeKASearch).then(
					function(result) {
						if (result.fileName) {
							toastr.success("Xuất file thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ result.fileName;
							// return;
						}

					});
		}
    	
    	
    	

        
        
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
                    vm.chiNhanhKV.maTinh=dataItem.maDanhMuc;
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
    					 vm.chiNhanhKV.tenDanhMuc="";
    					 vm.chiNhanhKV.maTinh="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        
        
        
        
        vm.patternOptionsHDXL={
    			dataTextField: "maHdxl", placeholder:"Nhập HĐXL",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.typeKASearch.maHdxl=dataItem.maHdxl;
//                    vm.importPXKSearch.tenDanhMuc=dataItem.tenDanhMuc;
//                    $('#qlNhanVienAuto').val(dataItem.tenDanhMuc);
//                    alert( $('#qlNhanVienAuto').val());
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                            return Restangular.all("tblTypeAPxkServiceRest/getForAutoCompleteHDXL").post({pageSize:10, page:1, maHdxl:$("#maHdxl").val().trim()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-12 text-header-auto border-right-ccc">Mã hđxl</p>' +
//                '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;text-align:center;">#: data.maHdxl #</div></div>',
                change: function(e) {
                	if(processSearch('maHdxl',vm.selectedPrpject)){
    					 $('#maHdxl').val("");
    					 vm.typeKASearch.maHdxl="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        
        vm.patternOptionsTram={
    			dataTextField: "maTramNhan", placeholder:"Nhập mã trạm nhận",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.typeKASearch.maTramNhan=dataItem.maTramNhan;
//                    vm.importPXKSearch.tenDanhMuc=dataItem.tenDanhMuc;
//                    $('#qlNhanVienAuto').val(dataItem.tenDanhMuc);
//                    alert( $('#qlNhanVienAuto').val());
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                            return Restangular.all("tblTypeAPxkServiceRest/getDeptForAutocomplete").post({pageSize:10, page:1, maTramNhan:$("#maTramNhan").val().trim()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-12 text-header-auto border-right-ccc">Mã trạm nhận</p>' +
//                '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;text-align:center;">#: data.maTramNhan #</div></div>',
                change: function(e) {
                	if(processSearch('maTramNhan',vm.selectedPrpject)){
    					 $('#maTramNhan').val("");
    					 vm.typeKASearch.maTramNhan="";
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
									
				
