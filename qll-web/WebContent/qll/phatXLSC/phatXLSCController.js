(function() {
	'use strict';
	var controllerId = 'phatXLSCController';

	angular.module('MetronicApp').controller(controllerId, phatXLSCController);

	function phatXLSCController($scope, $rootScope, $timeout, gettextCatalog,
			kendoConfig, $kWindow, phatXLSCService, CommonService, PopupConst,
			Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.phatXLSCSearch = {};

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
						toolbar : [ {
							name : "actions",
							template :
									'<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.phatXLSCGrid.columns.slice(1,vm.phatXLSCGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'
									+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
									+ '</label>' + '</div></div>'

						} ],
						dataBound : function(e) {
							// var grid = vm.orderChangeGoodskGrid;
							// grid.tbody.find("tr").dblclick(function (e) {
							// var dataItem = grid.dataItem(this);
							// vm.seeDetail(dataItem)
							// });
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
									$("#phatXLSCCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblPhatXuLySuCoServiceRest/doSearch",
									contentType : "application/json;charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.phatXLSCSearch.page = options.page;
									vm.phatXLSCSearch.pageSize = options.pageSize;
									// vm.oldSearch=angular.copy(vm.orderChangeGoodsSearch);
									return JSON.stringify(vm.phatXLSCSearch);

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
								/*
								 * { title : "<input type='checkbox'
								 * id='checkalllistchange'
								 * name='gridchkselectall'
								 * ng-click='vm.chkSelectAll();'
								 * ng-model='vm.chkAll' />", template : "<input
								 * type='checkbox' id='childcheck'
								 * name='gridcheckbox'
								 * ng-click='vm.handleCheck(dataItem)'
								 * ng-model='dataItem.selected'/>", width :
								 * '3%', headerAttributes : { style :
								 * "text-align:center;" }, attributes : { style :
								 * "text-align:center;" }, },
								 */
								{
									title : "TT",
									field : "stt",
									width : '50px',
									columnMenu : false,
									template : function() {
										return ++record;
									},
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Thao tác",
									template : '<div class="text-center #=tblPhatXuLySuCoId#""> '
											+

											'		<button style=" border: none; " class="#=tblPhatXuLySuCoId# icon_table"   uib-tooltip="Cập nhật" translate>'
											+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
											+ '		</button>'
											+

											'		<button style=" border: none; " class="#=tblPhatXuLySuCoId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
											+

											'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
											+ '		</button>' + '</div>',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;vertical-align: middle;"
									}
								},

								{
									title : "Tháng",
									field : 'thang',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
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
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Huyện",
									field : 'huyen',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Account",
									field : 'account',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Mã nhân viên",
									field : 'maNv',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Tổng sự cố",
									field : 'tong',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Phạt TGXL",
									columns : [
											{
												title : "Số lượng",
												columns : [
														{
															title : "3h",
															field : "phatTgxlSl3h",
															width : '100px',
															headerAttributes : {
																style : "text-align:center;font-weight: bold;"
															},
															attributes : {
																style : "text-align:right;"
															},
														},
														{
															title : "24h",
															field : "phatTgxlSl24h",
															width : '100px',
															headerAttributes : {
																style : "text-align:center;font-weight: bold;"
															},
															attributes : {
																style : "text-align:right;"
															},
														},

												],
												width : '200px',
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Tỷ lệ (%)",
												columns : [
														{
															title : "3h",
															field : "phatTgxlTl3h",
															width : '100px',
															headerAttributes : {
																style : "text-align:center;font-weight: bold;"
															},
															attributes : {
																style : "text-align:right;"
															},
														},
														{
															title : "24h",
															field : "phatTgxlTl24h",
															width : '100px',
															headerAttributes : {
																style : "text-align:center;font-weight: bold;"
															},
															attributes : {
																style : "text-align:right;"
															},
														},

												],
												width : '200px',
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Số lượng phạt",
												field : "phatTgxlSlp",
												width : '200px',
												headerAttributes : {
													style : "text-align:center;font-weight: bold;vertical-align: middle;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Tổng tiền phạt",
												field : "phatTgxlTp",
												width : '200px',
												headerAttributes : {
													style : "text-align:center;font-weight: bold;vertical-align: middle;"
												},
												attributes : {
													style : "text-align:right;"
												},
											}, ],
									width : '800px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								} ]

					});
		}
		vm.myFunction = myFunction;
		function myFunction() {
		    var x = document.getElementById("datephatXLSC").value;
		    var d = new Date(document.getElementById("datephatXLSC").value);
		    vm.phatXLSCSearch.exThang =1 + d.getMonth();
		    vm.phatXLSCSearch.exNam = d.getFullYear();
		}
		vm.doSearch = doSearch;
		function doSearch() {
			vm.myFunction();
			vm.showDetail = false;
			var grid = vm.phatXLSCGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}

		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phatXLSCGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phatXLSCGrid.showColumn(column);
			} else {
				vm.phatXLSCGrid.hideColumn(column);
			}

		}
		vm.importFile= function(){
			var teamplateUrl="qll/bcThucXuatTheoKy/importPopup.html";
		    var title="Import file";
		    var windowId="IMPORT_PHAT_PAKH";
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
            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_PHAT_XLSC +"/importFile?folder="+ vm.folder,
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
			vm.phatXLSCSearch.page=null;
			vm.phatXLSCSearch.pageSize=null;
			phatXLSCService.exportExcelGrid(vm.phatXLSCSearch).then(function(result){
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
    	vm.downloadImportTemplate = function(){

    		phatXLSCService.downloadImportTemplate().then(function(result){
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
		vm.checkNullTinh= function(){
			if(vm.phatXLSCSearch.tinh==null||vm.phatXLSCSearch.tinh==""){
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
                      vm.phatXLSCSearch.donVi=dataItem.maDanhMuc;
                      vm.phatXLSCSearch.huyen=dataItem.tenDanhMuc
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
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete3").post({pageSize:10, page:1, name:$("#huyenId").val().trim(),giaTri:vm.phatXLSCSearch.tinh}).then(function(response){
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
   					 vm.phatXLSCSearch.huyen="";
   					 vm.phatXLSCSearch.maDanhMuc="";
   					  vm.selectedPrpject=false;	
   					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
	}

})();
