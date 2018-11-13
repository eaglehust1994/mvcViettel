
(function() {
	'use strict';
	var controllerId = 'phatFcController';
	
	angular.module('MetronicApp').controller(controllerId, phatFcController);
	
	function phatFcController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, phatFcService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.phatFcSearch = {};
		
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
			vm.phatFcGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
//		        dataBound: function (e) {
//		    				    var grid = vm.phatFcGrid;
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
						 
						 $("#phatFcCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + "tblPhatFcServiceRest/doSearchPhatFc",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
						// vm.phatFcSearch.keySearch = "";
						vm.phatFcSearch.page = options.page
						vm.phatFcSearch.pageSize = options.pageSize                               

								return JSON.stringify(vm.phatFcSearch)
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
					field: "thang",
			        width: '100px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;overflow-y: hidden;"
					},
				}, {
					title: "Mã nhân viên",
			        field: "maNv",
			        width: '150px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				}, {
					title: "Họ và tên",
			        field: "hoVaTen",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Quận/Huyện",
			        field: "quanHuyen",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},
				{
					title: "Tỉnh",
			        field: "tinh",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				}, 
				{
					title: "Mã tỉnh",
					field: "maTinh",
			        width: '100px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Tổng số đường dây quản lý",
					field: "tsddql",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Tổng Số FC cho phép lỗi tháng n-1",
					field: "tsfccplt",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Thuê bao Gline phát triển tháng n-1",
					field: "tbgptt",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Số lượng FC đã sử dụng thực tế cho phát triển mới tháng n-1",
					field: "slfcdsdttcptmt",
			        width: '200px',
			        format: "{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Số lượng FC phát triển mới vượt hạn mức tháng n-1",
					field: "slfcptmvhmt",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Số lượng FC thực tế xử lý sự cố tháng n-1",
					field: "slfcttxlsct",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Tổng số lượng FC lỗi tháng n-1",
					field: "tslfclt",
			        width: '200px',
			        format: "{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Số lượng FC giảm trừ do được bảo hành",
					field: "slfcgtddbh",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Giảm trừ từ văn bản được phê duyệt",
					field: "gttvbdpd",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Số FC phạt tháng n-1",
					field: "sfcpt",
			        width:'200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				},{
					title: "Số tiền phạt tính theo Accout gline (VNĐ)",
					field: "stpttag",
			        width: '200px',
			        format:"{0:#,###.0}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;overflow-y: hidden;"
					},
				}]
			});
		}
		
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phatFcGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatFcGrid.showColumn(column);
			} else {
				vm.phatFcGrid.hideColumn(column);
			}

		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("datephatFc").value;
		    var d = new Date(document.getElementById("datephatFc").value);
		    vm.phatFcSearch.exThang =1 + d.getMonth();
		    vm.phatFcSearch.exNam = d.getFullYear();
		}
		vm.doSearch =function() {
			/*if(!vm.validator.validate()){
				//$("#exReqManaCreateFromDate").focus();
				return;
			}*/
//			if(vm.phatFcSearch.ngayXuatFrom == undefined || vm.phatFcSearch.ngayXuatFrom == ""){
//				vm.phatFcSearch.ngayXuatFrom = null;
//			}
//			if(vm.phatFcSearch.ngayXuatTo == undefined || vm.phatFcSearch.ngayXuatTo == ""){
//				vm.phatFcSearch.ngayXuatTo = null;
//			}
//			if(vm.phatFcSearch.ngayXuatFrom != null && vm.phatFcSearch.ngayXuatTo == null){
//				return;
//			}
//			if(vm.phatFcSearch.ngayXuatFrom == null && vm.phatFcSearch.ngayXuatTo != null){
//				return;
//			}
			vm.myFunction();
			var grid =$("#phatFcGrid").data("kendoGrid");
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
		    var windowId="IMPORT_PHAT_FC";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','25%');
		}
		
		vm.downloadImportTemplate = function(){

			phatFcService.downloadImportTemplate().then(function(result){
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
		vm.exportPhatFc = function(){
			vm.phatFcSearch.page=null;
			vm.phatFcSearch.pageSize=null;
			phatFcService.exportPhatFc(vm.phatFcSearch).then(function(result){
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
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_PHAT_FC +"/importFile?folder="+ vm.folder,
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
                    vm.phatFcSearch.donVi=dataItem.maDanhMuc;
                    vm.phatFcSearch.maTinh=dataItem.tenDanhMuc;
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
    					 vm.phatFcSearch.maTinh="";
    					 vm.phatFcSearch.maDanhMuc="";
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
                      vm.phatFcSearch.donVi2=dataItem.maDanhMuc;
                      vm.phatFcSearch.quanHuyen=dataItem.tenDanhMuc
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
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete3").post({pageSize:10, page:1, name:$("#quanHuyenFcAuto1").val().trim(),giaTri:vm.phatFcSearch.maTinh}).then(function(response){
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
                	if(processSearch('quanHuyenFcAuto1',vm.selectedPrpject)){
   					 $('#quanHuyenFcAuto1').val("");
   					 vm.phatFcSearch.quanHuyen="";
   					 vm.phatFcSearch.maDanhMuc="";
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
			vm.phatFcSearch.exNam = null;
			vm.phatFcSearch.exThang = null;
		}
		// end controller
		}
})();
									
				
