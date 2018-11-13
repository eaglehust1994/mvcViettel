
(function() {
	'use strict';
	var controllerId = 'phiBanHangController';
	
	angular.module('MetronicApp').controller(controllerId, phiBanHangController);
	
	function phiBanHangController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant, phiBanHangService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.phiBanHangSearch = {};
		
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
		                        template: '<div class=" pull-left ">'
//									+ '<button class="btn btn-qlk padding-search-right TkQLK"'
//									+ 'ng-click="vm.Import()" uib-tooltip="Import" translate>Import</button>'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK"'
									+ 'ng-click="vm.exportExcelGrid()" uib-tooltip="Xuất Excell" translate>Xuất Excell</button>'
									+ '</div>'	
		      					+'<div class="btn-group pull-right margin_top_button margin_right10">'+
		                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
			                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
			                    '<label ng-repeat="column in vm.phiBanHangGrid.columns.slice(1,vm.phiBanHangGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
			                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
			                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
		                    dataBound: function (e) {
		    				    var grid = vm.phiBanHangGrid;
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
						 
						 $("#extRegCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + "tblPhiBanHangServiceRest/doSearchPhiBanHang",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
// vm.phiBanHangSearch.keySearch = "01/2018";
						vm.phiBanHangSearch.page = options.page;
						vm.phiBanHangSearch.pageSize = options.pageSize;
						vm.oldSearch=angular.copy(vm.phiBanHangSearch);
						return JSON.stringify(vm.phiBanHangSearch)
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
					template : '<div class="text-center #=tblPhiBanHangId#""> '
							+

							'		<button style=" border: none; " class="#=tblPhiBanHangId# icon_table"   uib-tooltip="Cập nhật" translate>'
							+ '			<i class="fa fa-pencil"    aria-hidden="true"></i> '
							+ '		</button>'
							+

							'		<button style=" border: none; " class="#=tblPhiBanHangId# icon_table"  uib-tooltip="Hủy bỏ" translate>'
							+

							'			<i class="fa fa-trash" style="color: steelblue;"   aria-hidden="true"></i> '
							+ '		</button>' + '</div>',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;font-weight:bold;white-space:normal;"
					}
				},
				{
					title : "Tháng",
					field : 'thang',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "User bán hàng",
					field : 'userBanHang',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Mã tỉnh",
					field : 'maTinh',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Tổng thuê bao",
					field : 'tongThueBao',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Phí bán hàng trước thuế",
					field : 'phiTruocThue',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
					title : "Phí bán hàng sau thuế",
					field : 'phiSauThue',
					width : '15%',
					headerAttributes : {
						style : "text-align:center;white-space:normal;font-weight:bold;"
					},
					attributes : {
						style : "text-align:left;"
					},
				},{
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
					title : "Tên CTV",
					field : 'tenCtv',
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
			 if( $('#phiBanHangAuto').val()===""||$('#phiBanHangAuto').val()==null){
				 vm.phiBanHangSearch.tenDanhMuc=null;
				 vm.phiBanHangSearch.maDanhMuc=null;
			 }
			
			vm.showDetail = false;
			var grid = vm.phiBanHangGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		
		vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập tên tỉnh",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.phiBanHangSearch.donVi=dataItem.maDanhMuc;
                    vm.phiBanHangSearch.tenDanhMuc=dataItem.tenDanhMuc;
//                    $('#phiBanHangAuto').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#phiBanHangAuto").val().trim()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã tỉnh</p>' +
                '<p class="col-md-6 text-header-auto">Tên tỉnh</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('phiBanHangAuto',vm.selectedPrpject)){
    					 $('#phiBanHangAuto').val("");
    					 vm.phiBanHangSearch.tenDanhMuc="";
    					 vm.phiBanHangSearch.donVi="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
		
		vm.Import = function Import() {
			// vm.orderChangeGoodsDetailPop={};
			var teamplateUrl = "qll/dlHaTangTram/addProductPopup.html";
			var title = "Import phí bán hàng";
			var windowId = "PHI_BAN_HANG";
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
			            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.TBL_PHI_BAN_HANG +"/importPhiBanHang?folder="+ vm.folder,
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
			phiBanHangService.exportExcelGrid(vm.oldSearch).then(function(result){
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
        
        vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phiBanHangGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phiBanHangGrid.showColumn(column);
			} else {
				vm.phiBanHangGrid.hideColumn(column);
			}

		}
		
// vm.doSearch =function() {
// phiBanHangService.test().then(function(d) {
// data = d.plain();
// toastr.success(gettextCatalog.getString("Lỗi!"));
// }).catch( function(){
// toastr.error(gettextCatalog.getString("Lỗi!"));
// return;
// });
// }
//		
		
		// end
		}
})();
									
				
