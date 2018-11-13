(function() {
	'use strict';
	var controllerId = 'taskController';

	angular.module('MetronicApp').controller(controllerId,
			taskController);

	function taskController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, taskService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.taskSearch = {};
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
		vm.roleAdmin=$rootScope.RoleMenu.checkRole.checkRoLeFullQuyenAll;
		vm.roleememe=$rootScope.RoleMenu.checkRole.checkPKH;
		
		setTimeout(function() {
			$("#changeId").focus();
		}, 15);
		
		var currenRow1;
		if(currenRow1!==undefined ){
			$( function() {
				currenRow1.tooltip();
			  } );
		}
		
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
							// 
							template : '<div class=" pull-left ">'
									+ '<button class="btn btn-qlk padding-search-right DanhMuc margin_right10" style="width: auto;"'
									+ 'ng-click="vm.listTaskGroup()" ng-show="RoleMenu.checkRole.checkPKH" aria-hidden="true" uib-tooltip="Quản lý danh sách nhóm công việc" translate >Quản lý nhóm việc</button>'
									 +'</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<button class="btn btn-qlk padding-search-right deletehd"  aria-hidden="true" ng-show="RoleMenu.checkRole.checkPKH" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.taskGrid.columns.slice(1,vm.taskGrid.columns.length)">'
									+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
									+ '</label>' + '</div></div>'

						} ],
						
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
									$("#taskCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
								model : {
									fields :{
										stt : {editable : false},
										taskGroupName : {editable:false},
										department : {editable:false},
										taskGroupContent : {editable:false},
										endTime : {editable:false},
										startTime : {editable:false},
	
									}
									
								}
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "taskRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {
									
									if(vm.roleememe || vm.roleAdmin){
										vm.taskSearch.page = options.page;
										vm.taskSearch.pageSize = options.pageSize;
										vm.oldSearch = angular.copy(vm.taskSearch);
										return JSON.stringify(vm.taskSearch);
									}else{
										vm.taskSearch.page = options.page;
										vm.taskSearch.pageSize = options.pageSize;
										vm.taskSearch.employeeCode = $rootScope.casUser.employeeCode;
										vm.oldSearch = angular.copy(vm.taskSearch);
										return JSON.stringify(vm.taskSearch);
										
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
									width : '30px',
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
									title : "Tên công việc",
									field : 'taskGroupName',
									width : '120px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Đơn vị thực hiện",

									field : 'department',
									width : '120px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Nội dung công việc",
									field : 'taskGroupContent',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								
								{
									title : "Ngày giao việc",

									field : 'startTime',
									width : '90px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Ngày hoàn thành",

									field : 'endTime',
									width : '90px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Trạng thái",
									field : 'status',
									editable : true,
									template :  "# if(status ==1){ #" + "#= 'Đang giao' #" + "# } " + "if (status ==2) { # " + "#= 'Hoàn thành' #"+ "#}#",
									editor: function(container,options) {
										var selectValue = $(
												"<select ng-model='vm.updateFiels.status' ng-change='vm.updateStatus(dataItem)'>"
											+   "<option value=''>~~Chọn~~</option>"
											+	"<option value=1>Đang giao</option>"
											+   "<option value=2>Hoàn thành</option>"
											+	"</select>"
										);
										selectValue.attr("status",options.fields);
										selectValue.appendTo(container);
									},
									
									width : '90px',
									headerAttributes : {
										style : " text-align:center ;white-space:normal;font-weight:bold;"
									},
									attributes : {
										style : "text-align:center;"
									},
								}
								
								]

					});
		}
			
		
		//sua trang thai
			vm.updateStatus = function(dataItem){
				function onCancel(){
					doSearch();
					vm.updateFiels.status=null;
				}
				dataItem.status=kendo.parseInt(vm.updateFiels.status);
				dataItem.employeeCode = $rootScope.casUser.employeeCode;
				 
				 taskService.checkInfoUser(dataItem).then(function(result){
					
					 if(result==true || vm.roleememe==true){
						 confirm("Bạn có muốn cập nhật!", function() {
						 taskService.updateTaskStatus(dataItem).then(function(item){
							 if(item.error){
				    				toastr.error(item.error);
				    				return;
				    			}
				    			
				    			toastr.success("Cập nhật thành công!");
				    			$("#taskGrid").data('kendoGrid').dataSource.read();
				    			$("#taskGrid").data('kendoGrid').refresh();
						 },function(errResponse){
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
				         })
						 }, "", onCancel)
					 }else{
						 toastr.warning("bạn không có quyền cập nhật");
						 $("#taskGrid").data('kendoGrid').dataSource.read();
			    			$("#taskGrid").data('kendoGrid').refresh();
					 }
					
	    			
				 })
				
				
			}
			//auto phong ban
			vm.OptionsAutoDept={
	    			dataTextField: "department", placeholder:"Nhập mã phòng/ban hoặc tên phòng/ban",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.taskSearch.department=dataItem.department;
	                    vm.taskSearch.departmentId=dataItem.departmentId;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("taskGroupRsServiceRest/getAutoCompleteDept").post({pageSize:10, page:1, autoDept:$("#deptTask").val().trim()}).then(function(response){
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
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.departmentId #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.department #</div> </div>',
	                change: function(e) {
	                	if(processSearch('deptTask',vm.selectedPrpject)){
	    					 $('#deptTask').val("");
	    					 vm.taskSearch.department="";
	    					 vm.taskSearch.departmentId="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
			
			
		//list danh sách
		vm.doSearch = doSearch;
		function doSearch() {	
			vm.showDetail = false;
			var grid = vm.taskGrid;
			if($("#department").val()==""){
				vm.taskSearch.department=null;
				vm.taskSearch.departmentId=null;
			}
			if($("#startDateChange").val()==""){
				vm.taskSearch.startTime=null;
			}
			if($("#endDateChange").val()==""){
				vm.taskSearch.endTime=null;
				
			}

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}

		//mo tab quan ly nhom cong viec
		
		 vm.listTaskGroup = function(){
			 CommonService.goTo('TASK_GROUP');
			
		 }
		
		 
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.taskGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.taskGrid.showColumn(column);
			} else {
				vm.taskGrid.hideColumn(column);
			}

		}
		  vm.replaceVAlue=function(value){
			  if(value!=null){
				  value=value.toString();
				  if(value!=null&&value.includes(',')){
					  value= value.replaceAll(',',"");
				  } 
			  }
			  return value;
		  }

	}

})();
