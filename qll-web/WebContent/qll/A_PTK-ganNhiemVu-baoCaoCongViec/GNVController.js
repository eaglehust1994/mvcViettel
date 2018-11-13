(function(){
	'use strict';
	var controllerId = 'GNVController';
	
	angular.module('MetronicApp').controller(controllerId,
			GNVController);
	
	function GNVController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, GNVService,tblQlCvPtkService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant){
		
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.addGnvPopup={};
		vm.addCvPopup={};
		vm.ganNvSearch = {};
		vm.listTaskSearch = {};
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
		vm.roleememe=$rootScope.RoleMenu.checkRole.checkTPPTP;
		$rootScope.casUser.employeeCode;
		
		setTimeout(function() {
			$("#changeId").focus();
		}, 15);
		
		var d = new Date();
  		var n = d.getFullYear();
  		vm.date=n;
vm.validatorOptions = kendoConfig.get('validatorOptions');
		

		
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if ($rootScope.stringtile) {
				vm.String = $rootScope.stringtile;
			}
		}
		var record = 0;
		vm.oldSearch = {};
		function fillDataTable(data){
			
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
						toolbar : [{
							name: "actions",
							template : '<div class=" pull-left ">'
								 + '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK" style="width: auto;"'
								 + 'ng-click="vm.DetailNV()" ng-show="RoleMenu.checkRole.checkTPPTP||RoleMenu.checkRole.checkThamDinhDN"  uib-tooltip="Thống kê danh sách nhiệm vụ nhân viên" aria-hidden="true" translate>Thống kê nhiệm vụ</button>'
								 + '<button class="btn btn-qlk padding-search-right DanhMuc margin_right10" style="width: auto;"'
								+ 'ng-click="vm.listTasks()" ng-show="RoleMenu.checkRole.checkPTK||RoleMenu.checkRole.checkTPPTP" aria-hidden="true"   >Giao việc theo danh sách</button>'
								 +'</div>'
								+ '<div class="btn-group pull-right margin_top_button margin_right10">'
								+ '<button class="btn btn-qlk padding-search-right deletehd" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
								+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
								+'<i class="action-button excelQLK" uib-tooltip="Xuất dữ liệu theo điều kiện" ng-click="vm.DetailInfo()" aria-hidden="true"></i>'
								+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
								+ '<label ng-repeat="column in vm.ganNvGrid.columns.slice(1,vm.ganNvGrid.columns.length)">'
								+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
								+ '</label>' + '</div></div>'
						}],
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
									$("#ganNvCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {

								 url : Constant.BASE_SERVICE_URL+ "tblGanNhiemVuRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {
									if(vm.roleememe){
										vm.ganNvSearch.page = options.page;
										vm.ganNvSearch.pageSize = options.pageSize;
										vm.oldSearch = angular.copy(vm.ganNvSearch);
										return JSON.stringify(vm.ganNvSearch);
									}else{
										vm.ganNvSearch.page = options.page;
										vm.ganNvSearch.pageSize = options.pageSize;
										vm.ganNvSearch.idUser=$rootScope.casUser.employeeCode;
										vm.oldSearch = angular.copy(vm.ganNvSearch);
										return JSON.stringify(vm.ganNvSearch);
									}	
									

								}
							},
							pageSize :10
						},
						dataBound : function(data) {
						},
						noRecords : true,
						columnMenu : false,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả hiển thị")
						},
						pageable : {
							refresh : false,
							pageSizes :  [ 10, 15, 20, 25 ],
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
								template : '<div class="text-center #=ganNvId#""> '
										+ '		<button style=" border: none; " class="#=ganNvId# icon_table" aria-hidden="true"  uib-tooltip="Cập nhật công việc" translate>'
										+ '			<i class="fa gandonvi"  ng-click="vm.updateCV(dataItem)"    aria-hidden="true"></i> '
										+ '		</button>'
										
									
										
										+'		<button  class="#=ganNvId# icon_table" style=" border: none; " uib-tooltip="Xóa" translate> '+
										'		<i class="fa deletehdCon" ng-click=vm.removeDetail(dataItem) ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true"></i>'+
										'		</button>'
										
										+ '		<button style=" border: none; " class="#=ganNvId# icon_table" aria-hidden="true"  uib-tooltip="Cập nhật bản ghi" translate>'
										+ '			<i class="fa dagandonvi"  ng-click="vm.updateGanNV(dataItem)" ng-show="RoleMenu.checkRole.checkTPPTP"  aria-hidden="true"></i> '
										+ '		</button>'
										
										+ '</div>',
								width : '200px',
								headerAttributes : {
									style : "text-align:center;font-weight:bold;white-space:normal;"
								}
							},
							{
								title: "Tình trạng",
								 field: 'trangThai',
						        width: 150,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							},
							{
								title: "Tên nhân viên",
								field: 'fullname',
						        width: 150,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}, {
								title: "Tên nhiệm vụ",
						        field: 'tenNhiemVu',
						        width: 150,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}, 
							 {
								title: "Thuộc công việc",
								 field: 'tenCongViec',
						        width: 150,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}, {
								title: "Ngày giao nhiệm vụ",
								 field: 'ngayGiaoNv',
						        width: 150,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}, 
							{
								title: "Ngày hoàn thành",
								 field: 'ngayHoanThanh',
						        width: 150,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}
						]
						
			})	
		}
		
		
		vm.doSearch = doSearch;
		function doSearch() {	
			vm.showDetail = false;
			var grid = vm.ganNvGrid;
		
			if($("#nhanVienAuto").val()==""){
				vm.ganNvSearch.fullname=null;
				vm.ganNvSearch.idUser=null;
			}
			if($("#tenCvAuto").val()==""){
				vm.ganNvSearch.tenCongViec=null;
			}
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		
		vm.DetailNV=function(){
			vm.exportTkNv={};
			GNVService.DetailNV(vm.exportTkNv).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					// return;
					
				} 

			})
		}
		
		
		vm.DetailInfo=function(){
			vm.oldSearch.page=null;
			vm.oldSearch.pageSize=null;
	
			GNVService.getDetailInfo(vm.oldSearch).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					// return;
					
				} 

			})
		}
		 //xoa ban ghi
		 vm.removeDetail= function(dataItem){
	        	confirm('Bạn có chắc chắn muốn xóa bản ghi?', function(){
	        		GNVService.deleteObj(dataItem).then(function(result){
							toastr.success("Xóa bản ghi thành công!");
							vm.doSearch();
					});	
	        	});
				
			}
		 //popup update nhân viên
		 vm.updateGanNV =function(dataItem){
			 vm.updateGnvPopup=dataItem;
			 	var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/UpdateGanNV.html";
			    var title="Cập nhật bản ghi :";
			    var windowId="GAN_NV_CV";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','50%');
		 }
		 vm.saveGanNV = function(){
			if(!vm.updateGnvPopup.ngayHoanThanh){
				toastr.warning("Ngày hoàn thành không được để trống!!!");
				return
			}
			 GNVService.updateGNV(vm.updateGnvPopup).then(
					 function(item){
						 if(item.error){
			    				toastr.error(item.error);
			    				return;
			    			}	
							toastr.success("Cập nhật thành công");
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					 },function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật nhiệm vụ!!!"));
					 }
					 )
		 }
		 //popup update Công việc
		 vm.updateCV = function(dataItem){
			 	vm.addGnvPopup={};
			  	vm.addGnvPopup={tblQlCvPtkId: dataItem.idQlCvPtk,tenNhiemVu: dataItem.tenNhiemVu};
			  	tblQlCvPtkService.doSearch(vm.addGnvPopup).then(function(result){
		 			vm.addCvPopup=result.data[0];
		 			vm.checkItem = dataItem;
		 			vm.checkItem.gtSlHtTtTong=result.data[0].gtSlHtTtTong;
		 			vm.checkItem.gtDnQtkCnTong=result.data[0].gtDnQtkCnTong;
		 			vm.checkItem.gtQtkPtkTong=result.data[0].gtQtkPtkTong;
		 			vm.checkItem.dnQtCdtGt=result.data[0].dnQtCdtGt;
		 			vm.checkItem.tdQtCdtGt=result.data[0].tdQtCdtGt

		 		 	if(dataItem.tenNhiemVu=='Đã nhận tại PTK'){

				 		var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/UpdateCV1.html";
					    var title="Cập nhật nhiệm vụ Đã nhận tại PTK của công việc :";
					    var windowId="TD_QL_CV";
					    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','50%');
				 	}
				 	if(dataItem.tenNhiemVu=="Đã nhận QTK"){
				 		vm.addCvPopup.gtDnQtkCnNguoiLap=$rootScope.casUser.fullName;
				 		debugger;
				 		var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/UpdateCV2.html";
					    var title="Cập nhật nhiệm vụ Đã nhận QTK của công việc :";
					    var windowId="TD_QL_CV";
					    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','50%');
				 	}
				 	if(dataItem.tenNhiemVu=="Đã thẩm định QTK"){
				 		vm.addCvPopup.gtQtkPtkNguoiChot=$rootScope.casUser.fullName;
				 		var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/UpdateCV3.html";
					    var title="Cập nhật nhiệm vụ Đã thẩm định QTK của công việc :";
					    var windowId="TD_QL_CV";
					    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','50%');
				 	}
				 	if(dataItem.tenNhiemVu=="Đã lập đề nghị"){
				 		vm.addCvPopup.dnQtCdtNguoiLap=$rootScope.casUser.fullName;
				 		var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/UpdateCV4.html";
					    var title="Cập nhật nhiệm vụ Đã lập đề nghị của công việc :";
					    var windowId="TD_QL_CV";
					    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','50%');
				 	}
				 	if(dataItem.tenNhiemVu=="Đã chốt với CDT"){
				 	
				 		var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/UpdateCV5.html";
					    var title="Cập nhật nhiệm vụ Đã chốt với CDT của công việc :";
					    var windowId="TD_QL_CV";
					    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','50%');
				 	}
		 		});
			  
			  
		
				
			}
		
		 // save nhan vien duoc update
		 vm.saveCV = function(){
			 
			 var d = new Date();
			 
			 var ngayHt = kendo.parseDate(vm.checkItem.ngayHoanThanh,"dd/MM/yyyy");
			 ngayHt.setDate(ngayHt.getDate()+1);
			 if(vm.addCvPopup.gtSlHtTtTong!=0 && vm.addCvPopup.gtDnQtkCnTong!=0 &&
				vm.addCvPopup.gtQtkPtkTong!=0 && vm.addCvPopup.dnQtCdtGt!=0 &&	vm.addCvPopup.tdQtCdtGt!=0 ){
				 
			 }
			 
			 if(vm.addGnvPopup.tenNhiemVu=="Đã nhận tại PTK"){
		
				 if(vm.addCvPopup.gtSlHtTtTong!=vm.checkItem.gtSlHtTtTong){
					 if(ngayHt.getTime()<d.getTime()){
						 vm.checkItem.trangThai="Quá hạn";
					 }else{
						 vm.checkItem.trangThai="Hoàn Thành";
					 }					 
				 }else{
					 vm.checkItem.trangThai="Mới tạo";
				 }
				 GNVService.updateGNV(vm.checkItem).then(
						 function(item){
							 if(item.error){
				    				toastr.error(item.error);
				    				return;
				    			}		
						 },function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật nhiệm vụ!!!"));
						 }
						 )
						 
				 tblQlCvPtkService.updateCV1(vm.addCvPopup).then(
						 	function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Cập nhật thành công");
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật công việc!!!"));
			            }
						 )
				if(vm.addCvPopup.gtSlHtTtTong!=0 && vm.addCvPopup.gtDnQtkCnTong!=0 &&
					vm.addCvPopup.gtQtkPtkTong!=0 && vm.addCvPopup.dnQtCdtGt!=0 &&	vm.addCvPopup.tdQtCdtGt!=0 ){
					vm.addCvPopup.trangThai="Hoàn thành";
					tblQlCvPtkService.updateStatus(vm.addCvPopup).then(
						 	function(objItem) {
								if(objItem.error){
			    				toastr.error(objItem.error);
			    				return;
			    			}
			    			
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái công việc!!!"));
			            }
						 )			 
					}		 
			 }
			
			 if(vm.addGnvPopup.tenNhiemVu=="Đã nhận QTK"){
				 
				 if(vm.addCvPopup.gtDnQtkCnTong!=vm.checkItem.gtDnQtkCnTong){
					 if(ngayHt.getTime()<d.getTime()){
						 vm.checkItem.trangThai="Quá hạn";
					 }else{
						 vm.checkItem.trangThai="Hoàn Thành";
					 }					 
				 }else{
					 vm.checkItem.trangThai="Mới tạo";
				 }
				 GNVService.updateGNV(vm.checkItem).then(
						 function(item){
							 if(item.error){
				    				toastr.error(item.error);
				    				return;
				    			}		
						 },
						 function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật nhiệm vụ!!!"));
						 }
						 )
				 tblQlCvPtkService.updateCV2(vm.addCvPopup).then(
						 	function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Cập nhật thành công");
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật công việc!!!"));
			            }
						 )
					 if(vm.addCvPopup.gtSlHtTtTong!=0 && vm.addCvPopup.gtDnQtkCnTong!=0 &&
						vm.addCvPopup.gtQtkPtkTong!=0 && vm.addCvPopup.dnQtCdtGt!=0 && vm.addCvPopup.tdQtCdtGt!=0 ){
								vm.addCvPopup.trangThai="Hoàn thành";
								tblQlCvPtkService.updateStatus(vm.addCvPopup).then(
									 	function(objItem) {
											if(objItem.error){
						    				toastr.error(objItem.error);
						    				return;
						    			}
						    			
						    		}, 
						    		function(errResponse){
						                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái công việc!!!"));
						            }
									 )			 
								}		 
			 }
			 
			 if(vm.addGnvPopup.tenNhiemVu=="Đã thẩm định QTK"){
				 
					
				 if(vm.addCvPopup.gtQtkPtkTong!=vm.checkItem.gtQtkPtkTong){
					 if(ngayHt.getTime()<d.getTime()){
						 vm.checkItem.trangThai="Quá hạn";
					 }else{
						 vm.checkItem.trangThai="Hoàn Thành";
					 }					 
				 }else{
					 vm.checkItem.trangThai="Mới tạo";
				 }
				 GNVService.updateGNV(vm.checkItem).then(
						 function(item){
							 if(item.error){
				    				toastr.error(item.error);
				    				return;
				    			}		
						 },function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật nhiệm vụ!!!"));
						 }
						 )
				 tblQlCvPtkService.updateCV3(vm.addCvPopup).then(
						 	function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Cập nhật thành công");
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật công việc!!!"));
			            }
						 )
				 if(vm.addCvPopup.gtSlHtTtTong!=0 && vm.addCvPopup.gtDnQtkCnTong!=0 &&
					vm.addCvPopup.gtQtkPtkTong!=0 && vm.addCvPopup.dnQtCdtGt!=0 && vm.addCvPopup.tdQtCdtGt!=0 ){
									vm.addCvPopup.trangThai="Hoàn thành";
									tblQlCvPtkService.updateStatus(vm.addCvPopup).then(
										 	function(objItem) {
												if(objItem.error){
							    				toastr.error(objItem.error);
							    				return;
							    			}
							    			
							    		}, 
							    		function(errResponse){
							                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái công việc!!!"));
							            }
										 )			 
									}			 
			 }
			 if(vm.addGnvPopup.tenNhiemVu=="Đã lập đề nghị"){				 
					
				 if(vm.addCvPopup.dnQtCdtGt!=vm.checkItem.dnQtCdtGt){
					 if(ngayHt.getTime()<d.getTime()){
						 vm.checkItem.trangThai="Quá hạn";
					 }else{
						 vm.checkItem.trangThai="Hoàn Thành";
					 }					 
				 }else{
					 vm.checkItem.trangThai="Mới tạo";
				 }
				 GNVService.updateGNV(vm.checkItem).then(
						 function(item){
							 if(item.error){
				    				toastr.error(item.error);
				    				return;
				    			}		
						 },function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật nhiệm vụ!!!"));
						 }
						 )
				 tblQlCvPtkService.updateCV4(vm.addCvPopup).then(
						 	function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Cập nhật thành công");
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật công việc!!!"));
			            }
						 )
				 if(vm.addCvPopup.gtSlHtTtTong!=0 && vm.addCvPopup.gtDnQtkCnTong!=0 &&
					vm.addCvPopup.gtQtkPtkTong!=0 && vm.addCvPopup.dnQtCdtGt!=0 && vm.addCvPopup.tdQtCdtGt!=0 ){
									vm.addCvPopup.trangThai="Hoàn thành";
									tblQlCvPtkService.updateStatus(vm.addCvPopup).then(
										 	function(objItem) {
												if(objItem.error){
							    				toastr.error(objItem.error);
							    				return;
							    			}
							    			
							    		}, 
							    		function(errResponse){
							                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái công việc!!!"));
							            }
										 )			 
									}	
			 }
			 if(vm.addGnvPopup.tenNhiemVu=="Đã chốt với CDT"){				 
					
				 if(vm.addCvPopup.tdQtCdtGt!=vm.checkItem.tdQtCdtGt){
					 if(ngayHt.getTime()<d.getTime()){
						 vm.checkItem.trangThai="Quá hạn";
					 }else{
						 vm.checkItem.trangThai="Hoàn Thành";
					 }					 
				 }else{
					 vm.checkItem.trangThai="Mới tạo";
				 }
				 GNVService.updateGNV(vm.checkItem).then(
						 function(item){
							 if(item.error){
				    				toastr.error(item.error);
				    				return;
				    			}		
						 },function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật nhiệm vụ!!!"));
						 }
						 )
				 tblQlCvPtkService.updateCV5(vm.addCvPopup).then(
						 	function(result) {
								if(result.error){
			    				toastr.error(result.error);
			    				return;
			    			}
			    			toastr.success("Cập nhật thành công");
			    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			    		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật công việc!!!"));
			            }
						 )
				 if(vm.addCvPopup.gtSlHtTtTong!=0 && vm.addCvPopup.gtDnQtkCnTong!=0 &&
					vm.addCvPopup.gtQtkPtkTong!=0 && vm.addCvPopup.dnQtCdtGt!=0 && vm.addCvPopup.tdQtCdtGt!=0 ){
									vm.addCvPopup.trangThai="Hoàn thành";
									tblQlCvPtkService.updateStatus(vm.addCvPopup).then(
										 	function(objItem) {
												if(objItem.error){
							    				toastr.error(objItem.error);
							    				return;
							    			}
							    			
							    		}, 
							    		function(errResponse){
							                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái công việc!!!"));
							            }
										 )			 
									}			 
			 }
			 
			 
			 
		 }
		//xoa list nhan vien

	        vm.removeAny=function(){
		    	
		    	vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				
			
							confirm("Bạn có muốn xóa!", function() {
								GNVService.deleteListObj(vm.oldSearch).then(function(result){
									if(result.error){
					    				toastr.error(result.error);
					    				return;
					    			}
					    			toastr.success("Xóa công việc thành công!");
					    			  var sizePage = $("#ganNvGrid").data("kendoGrid").dataSource.total();
					    			  var pageSize = $("#ganNvGrid").data("kendoGrid").dataSource.pageSize();
					    					if(sizePage % pageSize === 1){
					    								var currentPage = $("#ganNvGrid").data("kendoGrid").dataSource.page();
					    								if (currentPage > 1) {
					    									$("#ganNvGrid").data("kendoGrid").dataSource.page(currentPage - 1);
					    								}
					    							}
					    			$("#ganNvGrid").data('kendoGrid').dataSource.read();
					    			$("#ganNvGrid").data('kendoGrid').refresh();
					    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
								},function(errResponse){
					                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi xóa"));
					            });
							})
					
				
			
				
		    }
	        
	        
	     
	        
	        vm.patternOptionsGanNVPop={
	    			dataTextField: "fullname", placeholder:"Nhập mã hoặc tên nhân viên",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.updateGnvPopup.idUser=dataItem.userCode;
	                    vm.updateGnvPopup.fullname=dataItem.fullName;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có bảng
								// đơn
								// vị
	                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$('#nhanVienAuto1').val()}).then(function(response){
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
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.userCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
	                change: function(e) {
	                	if(processSearch('nhanVienAuto1',vm.selectedPrpject)){
	    					 $('#nhanVienAuto1').val("");
	    					 vm.updateGnvPopup.fullname="";
	    					 vm.updateGnvPopup.idUser="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
	        vm.assignTaskSearch={};
	        vm.patternOptionsGetNvPtk={
	    			dataTextField: "fullname", placeholder:"Nhập mã hoặc tên nhân viên",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.assignTaskSearch.idUser=dataItem.userCode;
	                    vm.assignTaskSearch.fullname=dataItem.fullName;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có bảng
								// đơn
								// vị
	                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$('#nhanVienPtk').val()}).then(function(response){
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
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.userCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
	                change: function(e) {
	                	if(processSearch('nhanVienPtk',vm.selectedPrpject)){
	    					 $('#nhanVienPtk').val("");
	    					 vm.assignTaskSearch.fullname="";
	    					 vm.assignTaskSearch.idUser="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
	        vm.patternOptionsGetNv={
	    			dataTextField: "fullname", placeholder:"Nhập mã hoặc tên nhân viên",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.ganNvSearch.idUser=dataItem.userCode;
	                    vm.ganNvSearch.fullname=dataItem.fullName;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có bảng
								// đơn
								// vị
	                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVienPtk").post({pageSize:10, page:1, userCode:$('#nhanVienAuto').val()}).then(function(response){
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
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.userCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
	                change: function(e) {
	                	if(processSearch('nhanVienAuto',vm.selectedPrpject)){
	    					 $('#nhanVienAuto').val("");
	    					 vm.ganNvSearch.fullname="";
	    					 vm.ganNvSearch.idUser="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
	        vm.patternOptionsAutoTenCv={
	    			dataTextField: "tenCongViec", placeholder:"Nhập tên công việc",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.ganNvSearch.tenCongViec=dataItem.tenCongViec;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	
	                            return Restangular.all("tblGanNhiemVuRsServiceRest/getForAutoCompleteTenCv").post({pageSize:10, page:1, tenCongViec:$("#tenCvAuto").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-12 text-header-auto">Số hợp đồng</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.tenCongViec #</div></div>',
	                change: function(e) {
	                	if(processSearch('tenCvAuto',vm.selectedPrpject)){
	    					 $('#tenCvAuto').val("");
	    					 vm.ganNvSearch.tenCongViec="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                   
	                  }
	    		};
	        
	        
	        
	        vm.patternOptionsSoHdCdt={
	    			dataTextField: "soHdCdt", placeholder:"Nhập số hợp đồng",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.listTaskSearch.soHdCdt=dataItem.soHdCdt;
	                    
	                    Restangular.all("tblGanNhiemVuRsServiceRest/getAutoMaViTriForShd").post({pageSize:10, page:1,soHdCdt:vm.listTaskSearch.soHdCdt}).then(function(response){
                           vm.dataList = response;
                           $("#maViTriPopup10").data("kendoMultiSelect").dataSource.data(vm.dataList);
                           $("#maViTriPopup10").data("kendoMultiSelect").refresh();
                        })
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: false,
	                    filter:true,
	                    transport: {
	                        read: function(options) {
	                        	
	                            return Restangular.all("tblQlCvPtkRsServiceRest/getForAutoCompleteSHD").post({pageSize:10, page:1, soHdCdt:$("#SoHdCdtPopUp").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-12 text-header-auto">Số hợp đồng</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-12" style="padding: 0px 32px 0 0;float:center;">#: data.soHdCdt #</div></div>',
	                change: function(e) {
	                	if(processSearch('SoHdCdtPopUp',vm.selectedPrpject)){
	    					 $('#SoHdCdtPopUp').val("");
	    					 vm.listTaskSearch.soHdCdt="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    
	                  }
	    		};
		//quản lý nhiệm vụ 
	        
	        vm.getListTask = getListTask;
	        function getListTask(){
	        	vm.listTaskSearch.listMaViTri=$("#maViTriPopup10").data("kendoMultiSelect").value();
	        	 
	        	 var grid = vm.listTaskGrid;
	        	 if($("#SoHdCdtPopUp").val()==""){
	        		 vm.listTaskSearch.soHdCdt=null;
	 		
	 			}
	        	 if($("#maViTriPopup10").val()==""){
	        		 vm.listTaskSearch.listMaViTri=null;
	 		
	 			}
	        	 if($("#createdDateFromChange").val()==""){
	        		 vm.listTaskSearch.ngayNhanHshcFrom=null;
	        	 }
	        	 if($("#createdDateToChange").val()==""){
	        		 vm.listTaskSearch.ngayNhanHshcTo=null;
	        	 }
	        	 if(grid){
	 				grid.dataSource.query({
	 					page: 1,
	 					pageSize: 10
	 				});
	 			}  
	        }
	        
	        vm.onOpen = function(windowId){
	        	if(windowId == "TD_QL_CV"){
					 $("#maViTriPopup10").kendoMultiSelect({
			              placeholder: "Nhập mã vị trí" ,
			              itemTemplate: '<span class="order-id">#= mttMaViTri #</span> ',
			              dataTextField: "mttMaViTri",
			              dataValueField: "mttMaViTri",
			              dataSource: {
			                  type: "data",
			                  transport: {
			                  	read: function(options) {	
			                          return Restangular.all("tblGanNhiemVuRsServiceRest/getAutoMaViTriForShd").post({pageSize:10, page:1,soHdCdt:vm.listTaskSearch.soHdCdt}).then(function(response){
			                              options.success(response);
			                          }).catch(function (err) {
			                              console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
			                          });
			                      }
			                  },
			                  schema: {
			                      model: {
			                          fields: {
			                          	mttMaViTri: { type: "string" }
			                          }
			                      }
			                  },
			                  pageSize: 10,
			                  serverPaging: true,
			                  serverFiltering: true
			              },
			          });
	        	}
	        	
	        	
//	        	 $("#maViTriPopup10").on('click', function(){
//	 	        	var multiSelect = $("#maViTriPopup10").data("kendoMultiSelect")
//	 	        	multiSelect.search({pageSize:10, page:1,soHdCdt:vm.listTaskSearch.soHdCdt});
//	 	        })
	        }
       	 	
	       
	        
	        vm.listTasks = function(){
	        	var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/listTask.html";
			    var title="Giao việc theo danh sách";
			    var windowId="TD_QL_CV";
			    fillDataTableTask([]);
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'95%','95%');
	        }
		
		
	        var record=0;
			function fillDataTableTask(data) {
				
				vm.listTaskGridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,	
					columnMenu: false,
					scrollable: true,
					toolbar: [
	                    {
	                    	
	                    	 template: 
	                    		 '<div class=" pull-left ">'
									+ '<button class="btn btn-qlk padding-search-right addQLK margin_right10" style="width: auto;"'
									+ 'ng-click="vm.assignTasks()" ng-show="RoleMenu.checkRole.checkTPPTP" aria-hidden="true" uib-tooltip="Gán công việc" translate >Gán công việc</button>'
									
									 + '</div>'+
	                    		 
							'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.listTaskGrid.columns.slice(1,vm.listTaskGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
	                    }
	                    ],
	                dataBound: function (e) {
					    var grid = vm.listTaskGrid;
					    
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
								 $("#listTaskCount").text(""+response.total);
								 	vm.count = response.total;
									return response.total; 
								},
								data: function (response) {
									var list=response.data;
					        		for(var x in list){
					        			for(var i in $scope.listCheck){
					        				if(list[x].tblQlCvPtkId===$scope.listCheck[i].tblQlCvPtkId){
					        					list[x].selected=true;
					        				}
					        			}
					        		}
					        		return list;
								},
			                },
						transport: {
							read: {
								url: Constant.BASE_SERVICE_URL + "tblQlCvPtkRsServiceRest/listTasks",
								contentType: "application/json; charset=utf-8",
								type: "POST"
							},					
							parameterMap: function (options, type) {
//								if(!vm.orderSearch.listBussinessType){
//									vm.orderSearch.listBussinessType = [];
//								}
								vm.listTaskSearch.page = options.page
								vm.listTaskSearch.pageSize = options.pageSize
								
								return JSON.stringify(vm.listTaskSearch)
							}
						},					 
						pageSize: 10
					} ,
					dataBinding: function () {
						record = (this.dataSource.page() - 1) * this.dataSource.pageSize();
					},
					noRecords: true,
					messages: {
						noRecords : CommonService.translate("Không có kết quả hiển thị")
					},
					pageable: {
						refresh: false,
						 pageSizes: [10, 15, 20, 25],
						messages: {
							display: CommonService.translate("{0}-{1} của {2} kết quả"),
			                itemsPerPage: CommonService.translate("kết quả/trang"),
			                empty: CommonService.translate("Không có kết quả hiển thị")
			            }
					},
					columns: [{
						title : "<input type='checkbox' id='checkAllListTask' name='gridChkSelectAll' ng-click='vm.chkSelectAllForTask();' ng-model='vm.chkAllForTask' />",
						template: "<input type='checkbox'  id='childCheckInTask' name='gridcheckbox' ng-click='vm.handleCheckForTask(dataItem)' ng-model='dataItem.selected'/>",
				        width: "20px",
				        headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"}
					},{
						title: "TT",
						field:"stt",
				        template: function () {
						  return ++record;
						 },
				        width: "30px",
				        headerAttributes: {
							style: "text-align:center; font-weight: bold",
							translate:""
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"CNKV",
						field:"cnkv",
						width: "50px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
		
					},{
						title:"Mã vị trí",
						field:"mttMaViTri",
						width: "60px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Số hợp đồng",
						field:"soHdCdt",
						width: "200px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Ngày nhận HSHC",
						field:"ngayNhanHshc",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Giá trị SLHTTT tổng",
						field:"gtSlHtTtTong",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Nhân viên đã nhận tại PTK",
						field:"nhanVien1",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Giá trị DNQTKCN tổng",
						field:"gtDnQtkCnTong",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Nhân viên đã nhận QTK",
						field:"nhanVien2",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Giá trị QTKPTK tổng",
						field:"gtQtkPtkTong",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Nhân viên đã thẩm định QTK",
						field:"nhanVien3",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Giá trị đề nghị QTCDT",
						field:"dnQtCdtGt",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Nhân viên đã lập đề nghị",
						field:"nhanVien4",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Giá trị thẩm định QTCDT",
						field:"tdQtCdtGt",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title:"Nhân viên đã chốt với CDT",
						field:"nhanVien5",
						width: "90px",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					}]
				});
			}
		
			$scope.listCheck=[];
			vm.handleCheckForTask=handleCheckForTask;
	    	function handleCheckForTask(dataItem){
	    		if(dataItem.selected){
	    		$scope.listCheck.push(dataItem);
	    		} else {
	    			for(var i=0;i<$scope.listCheck.length;i++){
	    				if($scope.listCheck[i].tblQlCvPtkId===dataItem.tblQlCvPtkId){
	    				$scope.listCheck.splice(i,1);
	    				}
	    			}
					$('[name="gridChkSelectAll"]').prop('checked', false);
	    		}
			}
			
			
			vm.chkSelectAllForTask=chkSelectAllForTask;
		
			$scope.checkSearch=false;
			function chkSelectAllForTask(){
				var grid = vm.listTaskGrid;
				
		    		chkSelectAllBase(vm.chkAllForTask, grid);
				if(vm.chkAllForTask){
					if( $scope.checkSearch && $scope.dataSearch.length >0){
						$scope.listCheck=$scope.dataSearch;
					} else {
						CommonService.getallData("tblQlCvPtkRsServiceRest/listTasks",vm.listTaskSearch).then(function(data){
							$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}
			};
//			vm.afterSignHandle = function(){
//				vm.chkAllForTask = false;
//				chkSelectAllForTask() ;
//			}
			
			 vm.assignTasks = function(){
		        	var teamplateUrl="qll/A_PTK-ganNhiemVu-baoCaoCongViec/assignTask.html";
				    var title="Giao việc";
				    var windowId="GIAO_VIEC";
				   
				    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'70%','30%');
		        }
			
			 vm.saveTask = function(){
				 if($scope.listCheck.length==0){
					 toastr.warning(gettextCatalog.getString("Bạn chưa chọn công việc!!!"));
		        		return;
				 }
				 if(vm.assignTaskSearch.ngayHoanThanh==null){
					 toastr.warning(gettextCatalog.getString("Bạn chưa chọn ngày hoàn thành!!!"));
		        		return;
				 }
				 if(vm.assignTaskSearch.tenNhiemVu==""){
					 toastr.warning(gettextCatalog.getString("Bạn chưa chọn nhiệm vụ!!!"));
		        		return;
				 }
				 if(vm.assignTaskSearch.ngayGiaoNv==null){
					 vm.assignTaskSearch.ngayGiaoNv= kendo.toString(new Date((new Date()).getTime()),"dd/MM/yyyy");
				 }
				 var listIdQlCvPtk = [];
				 var listTenCongViec = [];
				 for(var j=0;j<$scope.listCheck.length;j++){
					 vm.assignTaskSearch.trangThai="Mới tạo";
					
					 listIdQlCvPtk.push($scope.listCheck[j].tblQlCvPtkId);
					 listTenCongViec.push($scope.listCheck[j].mttMaViTri+"-"+$scope.listCheck[j].soHdCdt);
				 }
				 vm.assignTaskSearch.listIdQlCvPtk=listIdQlCvPtk;
				 vm.assignTaskSearch.listTenCongViec=listTenCongViec;
				 
				 GNVService.saveAssignListTask(vm.assignTaskSearch).then(function(result){
					 	
					 if(result.error){
						 toastr.error(result.error);
						 return;
					 }
					
					 toastr.success("thêm mới thành công!");
					
						$("#ganNvGrid").data('kendoGrid').dataSource.read();
		    			$("#ganNvGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click(); 
				 },function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thêm mới nhiệm vụ!!!"));
		            }
				 
				 )

			 }
			 
	}
	
	
	
	
})();