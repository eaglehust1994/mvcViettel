(function() {
	'use strict';
	var controllerId = 'phanQuyenHTController';

	angular.module('MetronicApp').controller(controllerId,
			phanQuyenHTController);

	function phanQuyenHTController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, phanQuyenHTService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.phanQuyenHTSearch = {};
		vm.infoRoles=[];
		vm.infoxxx={};
		// vm.user=Constant.vpsUserToken.fullName;
		initFormData();
		function initFormData() {
			fillDataTable([]);

			if ($rootScope.stringtile) {
				vm.String = $rootScope.stringtile;
			}

		}
		 $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
		vm.validatorOptions = kendoConfig.get('validatorOptions');

		var record = 0;
		vm.oldSearch = {};
		function fillDataTable(data) {
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
							template : '<div class=" pull-left ">'
									+ '<button class="btn btn-qlk padding-search-right margin_right10 addQLK" style="padding-left:25px;width:auto;"'
									+ 'ng-click="vm.openPhanQuyen()" uib-tooltip="Phân quyền"  translate>Phân quyền</button>'
									+ '</div>'
									
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.phanQuyenHTGrid.columns.slice(1,vm.phanQuyenHTGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'
									+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
									+ '</label>' + '</div></div>'

						} ],
						dataBound : function(e) {
							 var grid = vm.phanQuyenHTGrid;
							 grid.tbody.find("tr").dblclick(function (e) {
							 var dataItem = grid.dataItem(this);
							 vm.seeDetail(dataItem)
							 });
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
									$("#phanQuyenHTCount").text("" + response.total);
									vm.count = response.total;
									var list=response.data;
					        		for(var x in list){
					        			for(var i in $scope.listCheck){
					        				if(list[x].tblUsersId===$scope.listCheck[i].tblUsersId){
					        					list[x].selected=true;
					        				}
					        			}
					        		}
									return list;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "tblUsersServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.phanQuyenHTSearch.page = options.page;
									vm.phanQuyenHTSearch.pageSize = options.pageSize;
// vm.phanQuyenHTSearch.type="1";
									vm.oldSearch = angular
											.copy(vm.phanQuyenHTSearch);
									return JSON.stringify(vm.phanQuyenHTSearch);

								}
							},
							pageSize :10
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
						columns : [{
								title : "<input type='checkbox' id='checkalllistchange' name='gridchkselectall' />",
								template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck(dataItem)' ng-model='dataItem.selected'/>",
						        width: '3%',
						        headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"},
							},{
									title : "TT",
									field : "stt",
									width : '60px',
									columnMenu : false,
									template : function() {
										return ++record;
									},
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight: bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Họ tên nhân sự",
									field : 'fullName',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight: bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã nhân sự",
									field : 'userCode',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight: bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Đơn vị",
									field : 'donVi',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight: bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Chức danh",
									field : 'chucDanh',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight: bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Phòng ban",
									field : 'phongBan',
									width : '150px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight: bold;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},]
					});
		}

		/*
		 * Thực hiện submit file import excel
		 */

		vm.doSearch = doSearch;
		function doSearch() {
			if (!vm.validator.validate()) {
				return false;
			}
			vm.showDetail = false;
			var grid = vm.phanQuyenHTGrid;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		
		vm.seeDetail=function(dataItem){
			var teamplateUrl="qll/phanQuyenHeThong/dsQuyen.html";
		    var title="Thông tin quyền";
		    var windowId="INFO_ROLES_USER";
		      phanQuyenHTService.getRolesInfo(dataItem).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			vm.infoRoles=result;
	    			vm.infoxxx.fullName=dataItem.fullName;
	    			CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'40%','30%');
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi lấy thông tin"));
	            });
		   
		}

		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.phanQuyenHTGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.phanQuyenHTGrid.showColumn(column);
			} else {
				vm.phanQuyenHTGrid.hideColumn(column);
			}

		}
		
		 vm.patternOptionsNV={
	    			dataTextField: "fullName", placeholder:"Nhập mã hoặc tên nhân viên",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.phanQuyenHTSearch.userCode=dataItem.userCode;
	                    vm.phanQuyenHTSearch.hoVaTen=dataItem.fullName;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có bảng
								// đơn
								// vị
	                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVien").post({pageSize:10, page:1, userCode:$("#phanQuyenHTNhanVienAuto").val().trim()}).then(function(response){
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
	                	if(processSearch('phanQuyenHTNhanVienAuto',vm.selectedPrpject)){
	    					 $('#phanQuyenHTNhanVienAuto').val("");
	    					 vm.phanQuyenHTSearch.hoVaTen="";
	    					 vm.phanQuyenHTSearch.userCode="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 
		 vm.listAdd=[];
		   vm.openPhanQuyen= function(){
			   if($scope.listCheck.length===0){
				   toastr.warning("Danh sách phân quyền trống!!!");
				   return;
			   }
			   	vm.rolesUser.tblRolesId=null;
			   	vm.getRoles();
			    vm.listAdd=$scope.listCheck;
				var teamplateUrl="qll/phanQuyenHeThong/popupPhanQuyen.html";
			    var title="Phân quyền hệ thống ";
			    var windowId="PQ_HE_THONG";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'50%','30%');
			}
		   
// insertRoles
		   vm.rolesUser={};
		   vm.listRoles=[];
		   vm.getRoles= function(){
			   phanQuyenHTService.getRoles().then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			vm.listRoles=result;
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		   
		   vm.insertRoles= function(){
			   if(vm.rolesUser.tblRolesId==null||vm.rolesUser.tblRolesId==""){
				   toastr.warning("Bạn cần chọn quyền!!!");
				   return;
			   }
			   vm.rolesUser.lstRolesUser=$scope.listCheck;
			   phanQuyenHTService.insertRoles(vm.rolesUser).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			vm.rolesUser.tblRolesId=null;
	    			toastr.success("Gán quyền thành công thành công!");
	    			$("#phanQuyenHTGrid").data('kendoGrid').dataSource.read();
	    			$("#phanQuyenHTGrid").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		   
		   vm.insertRoles8= function(){
			   phanQuyenHTService.insertRoles8().then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Gán quyền thành công thành công!");
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		   
		   
		   
		 
		 $scope.listCheck=[];
			vm.handleCheck=handleCheck;
	    	function handleCheck(dataItem){
	    		if(dataItem.selected){
	    		$scope.listCheck.push(dataItem);
	    		} else {
	    			for(var i=0;i<$scope.listCheck.length;i++){
	    				if($scope.listCheck[i].tblUsersId===dataItem.tblUsersId){
	    				$scope.listCheck.splice(i,1);
	    				}
	    			}
					$('[name="gridchkselectall"]').prop('checked', false);
	    		}
	    }
	}

})();
