(function() {
	'use strict';
	var controllerId = 'phatLoiLapLaiController';

	angular.module('MetronicApp').controller(controllerId, phatLoiLapLaiController);

	function phatLoiLapLaiController($scope, $rootScope, $timeout, gettextCatalog,
			kendoConfig, $kWindow, phatLoiLapLaiService, CommonService, PopupConst,
			Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.phatLoiLapLaiSearch = {};

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
									$("#phatLoiLapLaiCount")
											.text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblLoiLapLaiServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.phatLoiLapLaiSearch.page = options.page;
									vm.phatLoiLapLaiSearch.pageSize = options.pageSize;
									// vm.oldSearch=angular.copy(vm.phatLoiLapLaiSearch);
									return JSON.stringify(vm.phatLoiLapLaiSearch);

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
									template : '<div class="text-center #=tblLoiLapLaiId#""> '
											+
											'		<button style=" border: none; " class="#=tblLoiLapLaiId# icon_table"   uib-tooltip="Cập nhật" translate>'
											+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblLoiLapLaiId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
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
									title : "Tỉnh/Tp",
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
									title : "Quận/Huyện",
									field : 'quanHuyen',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},{
									title : "Loại dịch vụ",
									field : 'loaiDv',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Account khách hàng",
									field : 'accountKh',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã nhân viên",
									field : 'maNv',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Số lần lặp",
									field : 'soLanLap',
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
									title : "Phạt",
									field : 'phat',
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

		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("datephatLoiLapLai").value;
		    var d = new Date(document.getElementById("datephatLoiLapLai").value);
		    vm.phatLoiLapLaiSearch.exThang =1 + d.getMonth();
		    vm.phatLoiLapLaiSearch.exNam = d.getFullYear();
		}
		vm.doSearch = doSearch;
		function doSearch() {
			vm.myFunction();
			vm.showDetail = false;
			var grid = vm.phatLoiLapLaiGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}

		vm.importFile= function(){
			var teamplateUrl="qll/bcThucXuatTheoKy/importPopup.html";
		    var title="Import file";
		    var windowId="IMPORT_PHAT_CDT";
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
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_LOI_LAP_LAI +"/importFile?folder="+ vm.folder,
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
       
        vm.exportExcelGrid= function(){
			vm.phatLoiLapLaiSearch.page=null;
			vm.phatLoiLapLaiSearch.pageSize=null;
			phatLoiLapLaiService.exportExcelGrid(vm.phatLoiLapLaiSearch).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
					// return;
					vm.doSearch();
				} 

			});	
			//toastr.success("hello !!!");
		}
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phatLoiLapLaiGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatLoiLapLaiGrid.showColumn(column);
			} else {
				vm.phatLoiLapLaiGrid.hideColumn(column);
			}

		}
		vm.checkNullTinh= function(){
			if(vm.phatLoiLapLaiSearch.tinh==null||vm.phatLoiLapLaiSearch.tinh==""){
			//	toastr.warning("Cần nhập tỉnh trước khi chọn huyện!!!");
				$("#huyenId").focus();
				return;
			}
		}
		var checkname=[];
		vm.patternOptions1={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập tên huyện",
                select: function(e) {
                	  var dataItem = this.dataItem(e.item.index());
                      vm.phatLoiLapLaiSearch.donVi=dataItem.maDanhMuc;
                      vm.phatLoiLapLaiSearch.huyen=dataItem.tenDanhMuc
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
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete3").post({pageSize:10, page:1, name:$("#huyenId").val().trim(),giaTri:vm.phatLoiLapLaiSearch.tinh}).then(function(response){
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
                	if(processSearch('huyenId',vm.selectedPrpject)){
   					 $('#huyenId').val("");
   					 vm.phatLoiLapLaiSearch.huyen="";
   					 vm.phatLoiLapLaiSearch.maDanhMuc="";
   					  vm.selectedPrpject=false;	
   					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
		vm.downloadImportTemplate = function(){

			phatLoiLapLaiService.downloadImportTemplate().then(function(result){
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
			vm.phatLoiLapLaiSearch.exNam = null;
			vm.phatLoiLapLaiSearch.exThang = null;
		}
		
//END CONTROLLER
	}

})();
