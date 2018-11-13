(function() {
	'use strict';
	var controllerId = 'taskGroupController';

	angular.module('MetronicApp').controller(controllerId,
			taskGroupController);

	function taskGroupController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, taskGroupService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showAdvancedSearch=false;
		vm.taskGroupSearch = {};
		vm.addTaskGroup = {};
		vm.listmess={
				errMessage:"",
				errMessage1:""
			}
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
									+ '<button class="btn btn-qlk padding-search-right addQLK margin_right10" style="width: auto;"'
									+ 'ng-click="vm.createdTaskGroup()"  aria-hidden="true" uib-tooltip="Thêm mới" translate >Thêm mới</button>'
									 +'</div>'
									+ '<div class="btn-group pull-right margin_top_button margin_right10">'
									+ '<button class="btn btn-qlk padding-search-right deletehd"  aria-hidden="true" style="margin: -5px 5px;" ng-click="vm.removeAny()" uib-tooltip="Xóa theo điều kiện" translate="">Xóa</button>'
									+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
									+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
									+ '<label ng-repeat="column in vm.taskGroupGrid.columns.slice(1,vm.taskGroupGrid.columns.length)">'
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
									$("#taskGroupCount").text(
											"" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
								 // Thuc hien viec goi service
								 url : Constant.BASE_SERVICE_URL+ "taskGroupRsServiceRest/doSearch",
								 contentType : "application/json;charset=utf-8",
								 type : "POST"
								},
								parameterMap : function(options, type) {

									vm.taskGroupSearch.page = options.page;
									vm.taskGroupSearch.pageSize = options.pageSize;
									vm.oldSearch = angular.copy(vm.taskGroupSearch);
									return JSON.stringify(vm.taskGroupSearch);

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
								title : "Thao tác",
								template : '<div class="text-center "> '
									+ '		<button style=" border: none; " class="icon_table" aria-hidden="true"  uib-tooltip="Cập nhật bản ghi" translate>'
									+ '			<i class="fa gandonvi"  ng-click="vm.UpdateTaskGroup(dataItem)"   aria-hidden="true"></i> '
									+ '		</button>'
								
//									+ '		<button style=" border: none; " class="icon_table" aria-hidden="true"   uib-tooltip="Tải file đính kèm lên" translate>'
//									+ '			<i class="fa uploadimg"  ng-click="vm.uploadFile(dataItem)"   aria-hidden="true"></i> '
//									+ '		</button>'
									
									+'		<button  class="icon_table" style=" border: none; " uib-tooltip="Xóa bản ghi" translate> '+
									'		<i class="fa deletehdCon" ng-click=vm.removeTaskGroup(dataItem) aria-hidden="true"></i>'+
									'		</button>'
									+ '</div>',
								width : '150px',
								
								headerAttributes : {
									style : "text-align:center;font-weight:bold;white-space:normal;"
								},
								attributes : {
									style : "text-align:center;"
								},
								
								
							},
							{
								title : "Tên công việc",
								field : 'taskGroupName',
								width : '150px',
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
								width : '150px',
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
							},{
								title : "Định kì",
								field : 'periodic',
								template :  "# if(periodic =='1'){ #" + "#= 'Tuần' #" + "# } " + "if (periodic =='2') { # " + "#= 'Tháng' #"+ "#}#",
								width : '60px',
								headerAttributes : {
									style : " text-align:center ;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},{
								title : "Thời gian bắt đầu",

								field : 'startTaskGroup',
								width : '90px',
								headerAttributes : {
									style : " text-align:center ;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title : "Thời gian kết thúc",

								field : 'endTaskGroup',
								width : '90px',
								headerAttributes : {
									style : " text-align:center ;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title : "Thời gian cảnh báo",

								field : 'warningTaskGroup',
								width : '90px',
								headerAttributes : {
									style : " text-align:center ;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title : "Chu kì cảnh báo",

								field : 'warningCycle',
								width : '90px',
								headerAttributes : {
									style : " text-align:center ;white-space:normal;font-weight:bold;"
								},
								attributes : {
									style : "text-align:center;"
								},
							},
							{
								title : "Email nhận cảnh báo",

								field : 'warningEmail',
								width : '200px',
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
		 
		 
		//list danh sách
		vm.doSearch = doSearch;
		function doSearch() {	
			vm.showDetail = false;
			var grid = vm.taskGroupGrid;
			if($("#taskGroupName").val()==""){
				vm.taskGroupSearch.taskGroupName=null;
			}
			if($("#departmentName").val()==""){
				vm.taskGroupSearch.department=null;
				vm.taskGroupSearch.departmentId=null;
			}
			
		
			
			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}
		vm.removeTaskGroup= function(dataItem){
        	confirm('Bạn có chắc chắn muốn xóa bản ghi?', function(){
        		taskGroupService.deleteObj(dataItem).then(function(result){
						toastr.success("Xóa bản ghi thành công!");
						vm.doSearch();
				});	
        	});
			
		}
		
		 vm.removeAny=function(){
		    	
		    	vm.oldSearch.page=null;
				vm.oldSearch.pageSize=null;
				
			
							confirm("Bạn có muốn xóa!", function() {
								taskGroupService.deleteListObj(vm.oldSearch).then(function(result){
									if(result.error){
					    				toastr.error(result.error);
					    				return;
					    			}
					    			toastr.success("Xóa công việc thành công!");
					    			  var sizePage = $("#taskGroupGrid").data("kendoGrid").dataSource.total();
					    			  var pageSize = $("#taskGroupGrid").data("kendoGrid").dataSource.pageSize();
					    					if(sizePage % pageSize === 1){
					    								var currentPage = $("#taskGroupGrid").data("kendoGrid").dataSource.page();
					    								if (currentPage > 1) {
					    									$("#taskGroupGrid").data("kendoGrid").dataSource.page(currentPage - 1);
					    								}
					    							}
					    			$("#taskGroupGrid").data('kendoGrid').dataSource.read();
					    			$("#taskGroupGrid").data('kendoGrid').refresh();
					    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
								},function(errResponse){
					                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi xóa"));
					            });
							})
					
				
			
				
		    }
		vm.UpdateTaskGroup=function(dataItem){
			vm.addTaskGroup={};
			
		  	vm.addTaskGroup=dataItem;
			
		  	vm.addTaskGroup.periodic = vm.addTaskGroup.periodic.toString();
		  
			var teamplateUrl="qll/A_PKH-taskGroup/updatedTaskGroup.html";
		    var title="Cập nhật Công việc";
		    var windowId="TD_QL_CV";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','90%');
		}
		//mo popup them moi
		
		 vm.createdTaskGroup = function(){
			  	
				var teamplateUrl="qll/A_PKH-taskGroup/createdTaskGroup.html";
			    var title="Thêm mới Công việc";
			    var windowId="CREATED_QL_CV";
			
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'75%','75%');
			}
		 //save them moi
		 vm.saveTaskGroup = function(){
			 if(vm.addTaskGroup.taskGroupId==null){
				 taskGroupService.saveTaskGroup(vm.addTaskGroup).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			
		    			toastr.success("Thêm  công việc quản lý thành công!");
		    			$("#taskGroupGrid").data('kendoGrid').dataSource.read();
		    			$("#taskGroupGrid").data('kendoGrid').refresh();
		    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		    		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi thêm mới"));
		            });
			 }else{
				 
				 taskGroupService.getDeptId(vm.addTaskGroup).then(function(result){
		    			if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
		    			vm.addTaskGroup.departmentId=result.departmentId;
		    			debugger;
		    			 taskGroupService.updateTaskGroup(vm.addTaskGroup).then(function(result){
				    			if(result.error){
				    				toastr.error(result.error);
				    				return;
				    			}
				    			
				    			toastr.success("Cập nhật thành công!");
				    			  var sizePage = $("#taskGroupGrid").data("kendoGrid").dataSource.total();
				    			  var pageSize = $("#taskGroupGrid").data("kendoGrid").dataSource.pageSize();
				    					if(sizePage % pageSize === 1){
				    								var currentPage = $("#taskGroupGrid").data("kendoGrid").dataSource.page();
				    								if (currentPage > 1) {
				    									$("#taskGroupGrid").data("kendoGrid").dataSource.page(currentPage - 1);
				    								}
				    							}
				    			$("#taskGroupGrid").data('kendoGrid').dataSource.read();
				    			$("#taskGroupGrid").data('kendoGrid').refresh();
				    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				    		}, function(errResponse){
				                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
				            }
				    		);
		    		});
				
			 }
				 
			
		    	
		 }
		 //auto phong ban view page
		 vm.OptionsAutoDept={
	    			dataTextField: "department", placeholder:"Nhập mã phòng/ban hoặc tên phòng/ban",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.taskGroupSearch.department=dataItem.department;
	                    vm.taskGroupSearch.departmentId=dataItem.departmentId;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("taskGroupRsServiceRest/getAutoCompleteDept").post({pageSize:10, page:1, autoDept:$("#departmentName").val().trim()}).then(function(response){
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
	                	if(processSearch('departmentName',vm.selectedPrpject)){
	    					 $('#departmentName').val("");
	    					 vm.taskGroupSearch.department="";
	    					 vm.taskGroupSearch.departmentId="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
	         
		 
		//auto phong ban popup
		 vm.patternOptionsTaskDept={
	    			dataTextField: "department", placeholder:"Nhập tên đơn vị: ",
	                select: function(e) {
	                    var dataItem = this.dataItem(e.item.index());
	                    vm.addTaskGroup.department=dataItem.derpartmentName;
	                    vm.addTaskGroup.departmentId=dataItem.derpartmentId;
	                },
	                pageSize: 10,
	                dataSource: {
	                    serverFiltering: true,
	                    transport: {
	                        read: function(options) {
	                        	// link do search don vị thiếu do chưa có
								// bảng đơn
								// vị
	                            return Restangular.all("service/sysGroupServiceRest/getAutoDepartment").post({pageSize:10, page:1, name:$("#taskDept").val().trim()}).then(function(response){
	                                options.success(response);
	                            }).catch(function (err) {
	                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                            });
	                        }
	                    }
	                },
	                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	                '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
	                '<p class="col-md-6 text-header-auto">Tên dơn vị</p>' +
	                	'</div>',
	                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.derpartmentId #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.derpartmentName #</div> </div>',
	                change: function(e) {
	                	if(processSearch('taskDept',vm.selectedPrpject)){
	    					 $('#taskDept').val("");
	    					 vm.addTaskGroup.department="";
	    					 vm.addTaskGroup.departmentId="";
	    					  vm.selectedPrpject=false;	
	    					  }
	                },
	                close: function(e) {
	                    // handle the event
	                  }
	    		};
		 
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.taskGroupGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.taskGroupGrid.showColumn(column);
			} else {
				vm.taskGroupGrid.hideColumn(column);
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
