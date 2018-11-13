(function() {
	'use strict';
	var controllerId = 'phatRoiMangController';

	angular.module('MetronicApp').controller(controllerId, phatRoiMangController);

	function phatRoiMangController($scope, $rootScope, $timeout, gettextCatalog,
			kendoConfig, $kWindow, phatRoiMangService, CommonService, PopupConst,
			Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.phatRoiMangSearch = {};

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
									$("#phatRoiMangCount")
											.text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblPhatRoiMangServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.phatRoiMangSearch.page = options.page;
									vm.phatRoiMangSearch.pageSize = options.pageSize;
									// vm.oldSearch=angular.copy(vm.phatRoiMangSearch);
									return JSON.stringify(vm.phatRoiMangSearch);

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
									template : '<div class="text-center #=tblPhatRoiMangId#""> '
											+
											'		<button style=" border: none; " class="#=tblPhatRoiMangId# icon_table"   uib-tooltip="Cập nhật" translate>'
											+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblPhatRoiMangId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
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
									title : "User bán hàng",
									field : 'userBh',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã Tỉnh",
									field : 'maTinh',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},{
									title : "Tổng thuê bao",
									field : 'tongThueBao',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Phạt rời mạng trước thuế",
									field : 'phatTruocThue',
									width : '150px',
									format:"{0:#,###.0}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Phạt rời mạng(bao gồm thuế)",
									field : 'phatSauThue',
									width : '150px',
									format:"{0:#,###.0}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã TTNS",
									field : 'maTtns',
									width : '150px',
									format:"{0:#,###.0}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Tên CTV",
									field : 'tenCtv',
									width : '150px',
									format:"{0:#,###.0}",
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								}]

					});
		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("datephatRoiMang").value;
		    var d = new Date(document.getElementById("datephatRoiMang").value);
		    vm.phatRoiMangSearch.exThang =1 + d.getMonth();
		    vm.phatRoiMangSearch.exNam = d.getFullYear();
		}
		vm.doSearch = doSearch;
		function doSearch() {
			vm.myFunction();
			vm.showDetail = false;
			var grid = vm.phatRoiMangGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		vm.downloadImportTemplate = function(){

			phatRoiMangService.downloadImportTemplate().then(function(result){
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
		vm.importFile= function(){
			var teamplateUrl="qll/bcThucXuatTheoKy/importPopup.html";
		    var title="Import file";
		    var windowId="IMPORT_PHAT_ROI_MANG";
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
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_PHAT_ROI_MANG +"/importFile?folder="+ vm.folder,
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
			vm.phatRoiMangSearch.page=null;
			vm.phatRoiMangSearch.pageSize=null;
			phatRoiMangService.exportExcelGrid(vm.phatRoiMangSearch).then(function(result){
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
				vm.phatRoiMangGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatRoiMangGrid.showColumn(column);
			} else {
				vm.phatRoiMangGrid.hideColumn(column);
			}

		}
		
		vm.clearSearchDate = function(column) {
			vm.phatRoiMangSearch.exNam = null;
			vm.phatRoiMangSearch.exThang = null;
		}
	
//END CONTROLLER
	}

})();
