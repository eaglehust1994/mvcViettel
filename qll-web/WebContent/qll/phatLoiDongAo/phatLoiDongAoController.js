(function() {
	'use strict';
	var controllerId = 'phatLoiDongAoController';

	angular.module('MetronicApp').controller(controllerId, phatLoiDongAoController);

	function phatLoiDongAoController($scope, $rootScope, $timeout, gettextCatalog,
			kendoConfig, $kWindow, phatLoiDongAoService, CommonService, PopupConst,
			Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.phatLoiDongAoSearch = {};

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
									$("#phatLoiDongAoCount")
											.text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblLoiDongAoServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.phatLoiDongAoSearch.page = options.page;
									vm.phatLoiDongAoSearch.pageSize = options.pageSize;
									// vm.oldSearch=angular.copy(vm.phatLoiDongAoSearch);
									return JSON.stringify(vm.phatLoiDongAoSearch);

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
									template : '<div class="text-center #=tblLoiDongAoId#""> '
											+
											'		<button style=" border: none; " class="#=tblLoiDongAoId# icon_table"   uib-tooltip="Cập nhật" translate>'
											+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblLoiDongAoId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
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
									title : "Tổng lỗi",
									field : 'tongLoi',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},{
									title : "Đơn giá",
									field : 'donGia',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Thành tiền",
									field : 'thanhTien',
									format:"{0:#,###.0}",
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								}]

					});
		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("datephatLoiDongAo").value;
		    var d = new Date(document.getElementById("datephatLoiDongAo").value);
		    vm.phatLoiDongAoSearch.exThang =1 + d.getMonth();
		    vm.phatLoiDongAoSearch.exNam = d.getFullYear();
		}
		vm.doSearch = doSearch;
		function doSearch() {
			vm.myFunction();
			vm.showDetail = false;
			var grid = vm.phatLoiDongAoGrid;

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
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_LOI_DONG_AO +"/importFile?folder="+ vm.folder,
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
			vm.phatLoiDongAoSearch.page=null;
			vm.phatLoiDongAoSearch.pageSize=null;
			phatLoiDongAoService.exportExcelGrid(vm.phatLoiDongAoSearch).then(function(result){
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
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phatLoiDongAoGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatLoiDongAoGrid.showColumn(column);
			} else {
				vm.phatLoiDongAoGrid.hideColumn(column);
			}

		}
		vm.downloadImportTemplate = function(){

			phatLoiDongAoService.downloadImportTemplate().then(function(result){
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
			vm.phatLoiDongAoSearch.exNam = null;
			vm.phatLoiDongAoSearch.exThang = null;
		}
		
//END CONTROLLER
	}

})();
