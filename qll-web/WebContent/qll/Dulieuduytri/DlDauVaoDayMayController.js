
(function() {
	'use strict';
	var controllerId = 'DlDauVaoDayMayController';
	
	angular.module('MetronicApp').controller(controllerId, DlDauVaoDayMayController);
	
	function DlDauVaoDayMayController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, DlDauVaoDayMayService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.phatDLduytriSearch = {};
		
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
			vm.phatDLduytriGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
//		        dataBound: function (e) {
//		    				    var grid = vm.phatDLduytriGrid;
//		    				    grid.tbody.find("tr").dblclick(function (e) {
//		    				        var dataItem = grid.dataItem(this);
//		    				        vm.showDetail(dataItem)
//		    				    });
//		    				},
							dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
						 
						 $("#phatDLduytriCount").text(""+response.total);
							 	vm.count = response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {

				        		return response.data;// data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "tblDlDauVaoDayMayServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
						// vm.phatDLduytriSearch.keySearch = "";
						vm.phatDLduytriSearch.page = options.page
						vm.phatDLduytriSearch.pageSize = options.pageSize                               

								return JSON.stringify(vm.phatDLduytriSearch)
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
					 pageSizes: [10, 15, 20, 25],
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
				,  {
					title: "Tháng",
					field: "kyLuong",
			        width: '100px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã nhân viên",
			        field: "maNv",
			        width: '150px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Họ và tên",
			        field: "hoVaTen",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị",
			        field: "donVi",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Mã Tỉnh",
			        field: "maTinh",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Tên Huyện",
					field: "tenHuyen",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Ghép",
					field: "ghep",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đối tương/Cấp bậc",
					field: "doiTuong",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Dây thuê bao kênh truyền",
					field: "ddtbqlDayTbkt",
			        width: '200px',
			        format: "{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Dây thuê bao AON(đã quy đổi)",
					field: "ddtbqlDayTbAon",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Dây thuê bao các dự án",
					field: "ddtbqlDaToaNha",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Dây thuê bao GPON, ADSL, PSTN, EoC (đã quy đổi)",
					field: "ddtbqlGponAdslPstnEoc",
			        width: '200px',
			        format: "{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tổng dây thuê bao quy đổi",
					field: "ddtbqlTongQuyDoi",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Được hưởng lương duy trì hay không?",
					field: "ddtbqlHuongLuongDuyTri",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "0-2 ngày",
					field: "tbmtbkt02",
			        width:'200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "3 ngày",
					field: "tbmtbkt3",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "4 ngày",
					field: "tbmtbkt4",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "5 ngày",
					field: "tbmtbkt5",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "6 ngày",
					field: "tbmtbkt6",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: ">6 ngày",
					field: "tbmtbkt7",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "0-2 ngày",
					field: "tbmtbt02",
			        width:'200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "3 ngày",
					field: "tbmtbt3",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "4 ngày",
					field: "tbmtbt4",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "5 ngày",
					field: "tbmtbt5",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "6 ngày",
					field: "tbmtbt6",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: ">6 ngày",
					field: "tbmtbt7",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "0-2 ngày",
					field: "tbmtbdv02",
			        width:'200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "3 ngày",
					field: "tbmtbdv3",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "4 ngày",
					field: "tbmtbdv4",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "5 ngày",
					field: "tbmtbdv5",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "6 ngày",
					field: "tbmtbdv6",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: ">6 ngày",
					field: "tbmtbdv7",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "0-2 ngày",
					field: "tbmtbcs02",
			        width:'200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "3 ngày",
					field: "tbmtbcs3",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "4 ngày",
					field: "tbmtbcs4",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "5 ngày",
					field: "tbmtbcs5",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: ">5 ngày",
					field: "tbmtbcs6",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "0-2 ngày",
					field: "tbmtbwf02",
			        width:'200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "3 ngày",
					field: "tbmtbwf3",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "4 ngày",
					field: "tbmtbwf4",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "5 ngày",
					field: "tbmtbwf5",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: ">5 ngày",
					field: "tbmtbwf6",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Nhóm trưởng(1 hoặc 0)",
					field: "nhomTruong",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "ghi chú",
					field: "ghiChu",
			        width: '200px',
			        format:"{0:#,###.0}",
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
				vm.phatDLduytriGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatDLduytriGrid.showColumn(column);
			} else {
				vm.phatDLduytriGrid.hideColumn(column);
			}

		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("dateDLduytri").value;
		    var d = new Date(document.getElementById("dateDLduytri").value);
		    vm.phatDLduytriSearch.exThang =1 + d.getMonth();
		    vm.phatDLduytriSearch.exNam = d.getFullYear();
		}
		vm.doSearch =function() {
			/*if(!vm.validator.validate()){
				//$("#exReqManaCreateFromDate").focus();
				return;
			}*/
//			if(vm.phatDLduytriSearch.ngayXuatFrom == undefined || vm.phatDLduytriSearch.ngayXuatFrom == ""){
//				vm.phatDLduytriSearch.ngayXuatFrom = null;
//			}
//			if(vm.phatDLduytriSearch.ngayXuatTo == undefined || vm.phatDLduytriSearch.ngayXuatTo == ""){
//				vm.phatDLduytriSearch.ngayXuatTo = null;
//			}
//			if(vm.phatDLduytriSearch.ngayXuatFrom != null && vm.phatDLduytriSearch.ngayXuatTo == null){
//				return;
//			}
//			if(vm.phatDLduytriSearch.ngayXuatFrom == null && vm.phatDLduytriSearch.ngayXuatTo != null){
//				return;
//			}
			vm.myFunction();
			var grid =$("#phatDLduytriGrid").data("kendoGrid");
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    } 
		
		
		vm.importFile= function(){
			var teamplateUrl="qll/bcThucXuatTheoKy/importPopup.html";
		    var title="Import file";
		    var windowId="IMPORT_PHAT_DL_duy_tri";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','25%');
		}
		
		vm.downloadImportTemplate = function(){

			DlDauVaoDayMayService.downloadImportTemplate().then(function(result){
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
		vm.exportPhatDLduytri = function(){
			vm.phatDLduytriSearch.page=null;
			vm.phatDLduytriSearch.pageSize=null;
			DlDauVaoDayMayService.exportPhatDLduytri(vm.phatDLduytriSearch).then(function(result){
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
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_DU_LIEU_DUY_TRI +"/importFile?folder="+ vm.folder,
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
					//return;
				}else if(data.error){
					toastr.error("File import lỗi");
				} 
				else{
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
        vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập tên tỉnh",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.phatDLduytriSearch.donVi=dataItem.maDanhMuc;
                    vm.phatDLduytriSearch.maTinh=dataItem.tenDanhMuc;
//                    $('#dlHaTangTramAuto').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#maTinhauto").val().trim()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
               
                	'</div>',
                template:'<div class="row" ><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('maTinhauto',vm.selectedPrpject)){
    					 $('#maTinhauto').val("");
    					 vm.phatDLduytriSearch.maTinh="";
    					 vm.phatDLduytriSearch.donVi="";
    					  vm.selectedPrpject=false;	
    					  }
                
                },
                close: function(e) {
                    // handle the event
                  }
    		};
		vm.patternOptions1={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập tên huyện",
                select: function(e) {
                	  var dataItem = this.dataItem(e.item.index());
                      vm.phatDLduytriSearch.donVi2=dataItem.maDanhMuc;
                      vm.phatDLduytriSearch.tenHuyen=dataItem.tenDanhMuc
//                      $('#dlHaTangTramAuto1').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete3").post({pageSize:10, page:1, name:$("#quanHuyenDLduytriAuto1").val().trim(),giaTri:vm.phatDLduytriSearch.donVi}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto">Tên huyện</p>' +
                	'</div>',
                template:'<div class="row" ><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('quanHuyenDLduytriAuto1',vm.selectedPrpject)){
   					 $('#quanHuyenDLduytriAuto1').val("");
   					 vm.phatDLduytriSearch.tenHuyen="";
   					 vm.phatDLduytriSearch.maDanhMuc="";
   					  vm.selectedPrpject=false;	
   					
   					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
		vm.checkNullTinh= function(){
			if(vm.dlHaTangTramSearch.donVi==null||vm.dlHaTangTramSearch.donVi==""){
			//	toastr.warning("Cần nhập tỉnh trước khi chọn huyện!!!");
				
				$("#maTinhauto").focus();
				return;
			}
		}
        vm.clearSearchDate = function(column) {
			vm.phatDLduytriSearch.exNam = null;
			vm.phatDLduytriSearch.exThang = null;
		}
		// end controller
		}
})();
									
				
