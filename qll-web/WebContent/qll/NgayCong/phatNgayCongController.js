
(function() {
	'use strict';
	var controllerId = 'phatNgayCongController';
	
	angular.module('MetronicApp').controller(controllerId, phatNgayCongController);
	
	function phatNgayCongController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, phatNgayCongService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.phatNgayCongSearch = {};
		
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
			vm.phatNgayCongGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
//		        dataBound: function (e) {
//		    				    var grid = vm.phatNgayCongGrid;
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
						 
						 $("#phatNgayCongCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + "tblNgaycongServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
						// vm.phatNgayCongSearch.keySearch = "";
						vm.phatNgayCongSearch.page = options.page
						vm.phatNgayCongSearch.pageSize = options.pageSize                               

								return JSON.stringify(vm.phatNgayCongSearch)
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
					title: "Ngày Công tính lương",
			        field: "ngayCongTinhLuong",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Ngày Công chế dộ",
			        field: "ngayCongCheDo",
			        width: '200px',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				]
			});
		}
		
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phatNgayCongGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatNgayCongGrid.showColumn(column);
			} else {
				vm.phatNgayCongGrid.hideColumn(column);
			}

		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("datephatNgayCong").value;
		    var d = new Date(document.getElementById("datephatNgayCong").value);
		    vm.phatNgayCongSearch.exThang =1 + d.getMonth();
		    vm.phatNgayCongSearch.exNam = d.getFullYear();
		}
		vm.doSearch =function() {
			/*if(!vm.validator.validate()){
				//$("#exReqManaCreateFromDate").focus();
				return;
			}*/
//			if(vm.phatNgayCongSearch.ngayXuatFrom == undefined || vm.phatNgayCongSearch.ngayXuatFrom == ""){
//				vm.phatNgayCongSearch.ngayXuatFrom = null;
//			}
//			if(vm.phatNgayCongSearch.ngayXuatTo == undefined || vm.phatNgayCongSearch.ngayXuatTo == ""){
//				vm.phatNgayCongSearch.ngayXuatTo = null;
//			}
//			if(vm.phatNgayCongSearch.ngayXuatFrom != null && vm.phatNgayCongSearch.ngayXuatTo == null){
//				return;
//			}
//			if(vm.phatNgayCongSearch.ngayXuatFrom == null && vm.phatNgayCongSearch.ngayXuatTo != null){
//				return;
//			}
			vm.myFunction();
			var grid =$("#phatNgayCongGrid").data("kendoGrid");
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
		    var windowId="IMPORT_PHAT_NgayCong";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','25%');
		}
		
		vm.downloadImportTemplate = function(){

			phatNgayCongService.downloadImportTemplate().then(function(result){
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
		vm.exportphatNgayCong = function(){
			vm.phatNgayCongSearch.page=null;
			vm.phatNgayCongSearch.pageSize=null;
			phatNgayCongService.exportphatNgayCong(vm.phatNgayCongSearch).then(function(result){
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
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_NGAY_CONG +"/importFile?folder="+ vm.folder,
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

        vm.clearSearchDate = function(column) {
			vm.phatNgayCongSearch.exNam = null;
			vm.phatNgayCongSearch.exThang = null;
		}
		// end controller
		}
})();
									
				
