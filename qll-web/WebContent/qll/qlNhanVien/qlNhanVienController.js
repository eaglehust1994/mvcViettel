
(function() {
	'use strict';
	var controllerId = 'qlNhanVienController';
	
	angular.module('MetronicApp').controller(controllerId, qlNhanVienController);
	
	function qlNhanVienController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, qlNhanVienService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.qlNhanVienSearch = {};
		
		$(document).ready(function() {
			// getApply();
			fillDataphiBanHangTable();
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		});
// function getApply(){
// phiBanHangService.getApply(vm.appParamSearch).then(function(result){
// vm.dataAppParamType = result;
// vm.reasonSearch.listApply=[vm.dataAppParamType[0].code];
				 // fillDataTable();
// });
		
		initFormData();
		function initFormData() {
			fillDataphiBanHangTable([]);
			
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
		vm.oldSearch={};
		// table chinh
		var record=0;
		function fillDataphiBanHangTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: false,
				toolbar: [
		                    {
		                    	name: "actions",
		                    	template : '<div class=" pull-left ">'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 addQLK"'
									+ 'ng-click="vm.add()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'
									+ '<button class="btn btn-qlk padding-search-right TkQLK"'
									+ 'ng-click="vm.Import()" uib-tooltip="Import" translate>Import</button>'
									+ '</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<i class="action-button excelQLK" uib-tooltip="Xuất file excel" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.qlNhanVienGrid.columns.slice(1,vm.qlNhanVienGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'
									+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
									+ '</label>' + '</div></div>'
		                    	
		                       /*
								 * template: '<div class=" pull-left ">'+ '<button
								 * class="btn btn-qlk padding-search-right
								 * margin_right10 addQLK"'+ 'ng-click="vm.add()"
								 * uib-tooltip="Tạo mới" translate>Tạo mới</button>'+ + '<button
								 * class="btn btn-qlk padding-search-right
								 * margin_right10 excelQLK"' +
								 * 'ng-click="vm.exportExcelGrid()"
								 * uib-tooltip="Xuất Excell" translate>Xuất
								 * Excell</button>' + '</div>' + '<div
								 * class="btn-group pull-right margin_top_button
								 * margin_right10">' + '<i
								 * data-toggle="dropdown" uib-tooltip="Cài đặt"
								 * aria-expanded="false"><i class="fa fa-cog"
								 * aria-hidden="true"></i></i>' + '<i
								 * class="action-button excelQLK"
								 * uib-tooltip="Xuất file excel"
								 * ng-click="vm.exportExcelGrid()"
								 * aria-hidden="true"></i>' + '<div
								 * class="dropdown-menu hold-on-click
								 * dropdown-checkboxes" role="menu">' + '<label
								 * ng-repeat="column in
								 * vm.qlNhanVienGrid.columns.slice(1,vm.qlNhanVienGrid.columns.length)|
								 * filter: vm.gridColumnShowHideFilter">' + '<input
								 * type="checkbox" checked="column.hidden"
								 * ng-click="vm.showHideColumn(column)">
								 * {{column.title}}' + '</label>' + '</div></div>'
								 */
			                    
		                    }
		                    ],
		                    dataBound: function (e) {
		    				    var grid = vm.qlNhanVienGrid;
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
						 
						 $("#extRegCount11").text(""+response.total);
							 	vm.count = response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								var list=response.data;
				        		/*
								 * for(var x in list){ for(var i in
								 * $scope.listCheck){
								 * if(list[x].orderId===$scope.listCheck[i].orderId){
								 * list[x].selected=true; } } }
								 */
				        		return list;// data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "tblNhanVienServiceRest/doSearchNhanVien",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
// vm.qlNhanVienSearch.keySearch = "01/2018";
						vm.qlNhanVienSearch.page = options.page;
						vm.qlNhanVienSearch.pageSize = options.pageSize;
						vm.oldSearch=angular.copy(vm.qlNhanVienSearch);
						return JSON.stringify(vm.qlNhanVienSearch)
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
			        width: "5%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},
				{
					title : "Thao tác",
					template : '<div class="text-center #=tblNhanVienId#""> '
							+

							'		<button style=" border: none; " class="#=tblNhanVienId# icon_table"   uib-tooltip="Cập nhật" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							+

							'		<button style=" border: none; " class="#=tblNhanVienId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
							+

							'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
							+ '		</button>' + '</div>',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;font-weight:bold;white-space:normal;"
					}
				},
				{
					title : "Mã NV",
					field : 'maNv',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Họ tên",
					field : 'hoVaTen',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Đơn vị",
					field : 'donVi',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Loại nhân viên",
					field : 'chucDanh',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Trạng Thái",
					field : 'hoatDong',
					template :  "# if(hoatDong === 0){ #" + "#= 'Không hoạt động' #" + "# } " + "if (hoatDong === 1) { # " + "#= 'Hoạt động' #"+ "#}  #",
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},]
			});
		}
		
		vm.doSearch = doSearch;
		function doSearch() {
			
			 if( $('#qlNhanVienAuto').val()===""||$('#qlNhanVienAuto').val()==null){
				 vm.qlNhanVienSearch.tenDanhMuc=null;
				 vm.qlNhanVienSearch.donVi=null;
			 }
			 
			
			vm.showDetail = false;
			var grid = vm.qlNhanVienGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		
		vm.Import = function Import() {
			// vm.orderChangeGoodsDetailPop={};
			var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
			var title = "Import nhân viên";
			var windowId = "NHAN_VIEN";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, null, '70%', '30%', null);
		}
		
		// import file
		vm.submit=submit;
        function submit(){
        	
			    	if($("#fileChange")[0].files[0] == null){
			    		toastr.warning("Bạn chưa chọn file để import");
			    		return;
			    	}
			    	if($("#fileChange")[0].files[0].name.split('.').pop() !='xls' && $("#fileChange")[0].files[0].name.split('.').pop() !='xlsx' ){
			    		toastr.warning("Sai định dạng file");
			    		return;
			    	}
			    	
			    	var t0 = performance.now();
			    	$("#upfile").css("display","none");
					$("#modalLoading").css("display","block");
					var loading = $("#modalLoading");
			         kendo.ui.progress(loading, true);
			        var formData = new FormData();
					formData.append('multipartFile', $('#fileChange')[0].files[0]); 
			     return   $.ajax({
			            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_NHAN_VIEN_URL +"/importNhanVien?folder="+ vm.folder,
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
			vm.oldSearch.page=null;
			vm.oldSearch.pageSize=null;
			qlNhanVienService.exportExcelGrid(vm.oldSearch).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
					vm.doSearch();
					// return;
				} 

			});	
			// toastr.success("hello !!!");
		}
        
        
        vm.openDepartment = function openDepartment(){
			var obj={};
			// obj.page=1;
			// obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				if(result.error){
    				toastr.error(result.error);
    				return;
    			}
				var templateUrl = 'qll/Common/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Tìm kiếm đơn vị");
				var data=result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
					noRecords: true,
					columnMenu:false,
					messages: {
						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
					},
					pageable: {
						refresh: false,
						pageSize:10,
						pageSizes: [10, 15, 20, 25],
						messages: {
			                display: "{0}-{1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang",
			                empty: "Không có kết quả hiển thị"
			            }
					},
					columns:[{
						title: "TT",
						field: "stt",
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width: '5%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
						},{
						title: "Mã phòng ban",
						field: "code",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					}, {
						title: "Tên phòng ban",
						field: "text",
						width: '23%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					}, {
						title: "Đơn vị cha",
						field: "parentName",
						width: '20%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					},{
						title: "Ngày hiệu lực",
						field: "effectDate",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
					},{
						title: "Ngày hết hiệu lực",
						field: "endDate",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
					},{
								title: "Chọn",
								 template:
							        	'<div class="text-center "> '	+
						        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
						        	'			<i id="#=departmentId#" ng-click=onSave(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
						        	'		</a>'
										+'</div>',  
						        width: '7%',
						        field:"stt"
					}
					]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '40%','80%');
			});
		}
        
        vm.selectedPrpject=false;
		vm.changeDataAuto=changeDataAuto;
		function changeDataAuto(id){
		switch(id){
			case 'qlNhanVienAuto':{
			if(processSearch(id,vm.selectedPrpject)){
				vm.qlNhanVienSearch.donVi = null;
				vm.qlNhanVienSearch.tenDanhMuc =null;
				vm.selectedPrpject=false;	
			 }
				break;
			}
		 }
		
		}
        
        vm.onSave=onSave;
		function onSave(data){
			vm.qlNhanVienSearch.donVi=data.maDanhMuc;
			vm.qlNhanVienSearch.tenDanhMuc=data.tenDanhMuc;
			
// $('#qlNhanVienAuto').focus();
		}
        
        vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã đơn vị hoặc tên đơn vị",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.qlNhanVienSearch.donVi=dataItem.maDanhMuc;
                    vm.qlNhanVienSearch.tenDanhMuc=dataItem.tenDanhMuc;
//                    $('#qlNhanVienAuto').val(dataItem.tenDanhMuc);
//                    alert( $('#qlNhanVienAuto').val());
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete").post({pageSize:10, page:1, name:$("#qlNhanVienAuto").val().trim()}).then(function(response){
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
                	if(processSearch('qlNhanVienAuto',vm.selectedPrpject)){
    					 $('#qlNhanVienAuto').val("");
    					 vm.qlNhanVienSearch.tenDanhMuc="";
    					 vm.qlNhanVienSearch.donVi="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        
        vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.qlNhanVienGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.qlNhanVienGrid.showColumn(column);
			} else {
				vm.qlNhanVienGrid.hideColumn(column);
			}

		}
		

		
		// end
		}
})();
									
				
