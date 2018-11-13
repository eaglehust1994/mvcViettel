(function() {
	'use strict';
	var controllerId = 'authorizedManageController';
	
	angular.module('MetronicApp').controller(controllerId, authorizedManageController);
	
	function authorizedManageController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, authManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showDetail = false;
		vm.showTabOne = true;
		vm.checkData = false;
		vm.roleSearch = {};
		vm.role = {};
		vm.findRole = {};
		vm.detailRole = {};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
      '<span>Mã</span>' +
      '<span>Tên</span>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		vm.gridCommon = [ {
			title: "Mã",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
		initFormData();
		function initFormData() {
			//fillDataTable([]);
		}
		//autoComplete
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
	      '<span>Mã</span>' +
	      '<span>Tên</span>' +
	      	'</div>';
		vm.stockOptions = {
	            dataTextField: "name",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.roleSearch.code = dataItem.code; // thành id
	                vm.roleSearch.name = dataItem.name;//thành name
	                vm.roleSearch.domainDataId = dataItem.domainDataId;
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("userRoleServiceRest/userRole/" + 'getForAutoCompleteUserRoleData').post({name:vm.roleSearch.name,pageSize:vm.stockOptions.pageSize,domainDataId:vm.roleSearch.domainDataId }).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    },
						parameterMap: function (options, type) {
							//vm.roleSearch.userRoleId = 54;
								return JSON.stringify(vm.findRole)
						}
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.code #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.roleSearch.code = null; // thành id
	                	vm.roleSearch.name = null;//thành name
	                	vm.roleSearch.domainDataId = null;
	                }
	            },
	            ignoreCase: false
	        };
		
		//AddItem
		vm.addItemm=function(){
			var grid =vm.actionPopupGrid;
			if(vm.roleSearch.code!=null&&vm.roleSearch.name!=""){
			grid.dataSource.data().push({dataCode:vm.roleSearch.code,dataName:vm.roleSearch.name});
			vm.roleSearch.code=null;
			}
		};
		//
		vm.doSearch= doSearch;
		function doSearch(){
			
			if (vm.validator.validate()) {
				vm.checkData = true;
				fillDataTable([]);
				 vm.showDetail = false;
					var grid =vm.authorManaGrid;	
					if(grid){
						grid.dataSource.query({
							page: 1,
							pageSize: 20
						});
					}
			}

		}
		//handleCheck
		vm.handleCheck = function(item){
			if(document.getElementById("chkSelectAll").checked == true){
				document.getElementById("chkSelectAll").checked = false;
			}
		}
		vm.chkSelectAll = function(item) {
	    	var grid = vm.addPopupGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    }
		
		function onChangeAddPopup(){
			if ($("#addPopupGrid").data("kendoGrid").select().length > 0){
				var tr = $("#addPopupGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#addPopupGrid").data("kendoGrid").dataItem(tr);
    			fillDataTableAddPopup([]);
			}
			if ($("#actionPopupGrid").data("kendoGrid").select().length > 0){
				var tr = $("#actionPopupGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#actionPopupGrid").data("kendoGrid").dataItem(tr);
    			fillDataTableActionPopup([]);
			}
		}
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				reorderable: true,
				toolbar: [
		                    {
		                        name: "actions",
		                        template: '<div class=" pull-left ">'+
		                        '<button class="btn btn-qlk padding-search-right addQLK1"'+
		      					'ng-click="vm.createNew()" uib-tooltip="Bổ sung vai trò" translate><p class="action-button add" aria-hidden="true">Bổ sung vai trò</p></a>'+
		      					'</div>'	
		        				+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.authorManaGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								vm.role = response.data[0];
								if(vm.role != null){
									vm.detailRole.loginName = vm.role.loginName;
									vm.detailRole.sysUserId = vm.role.sysUserId;
									vm.detailRole.userRoleId = vm.role.userRoleId;
								}else{
									vm.detailRole = {};
								}
								
								return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "userRoleServiceRest/userRole/doSearchUserRole",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.roleSearch.page = options.page
								vm.roleSearch.pageSize = options.pageSize
								return JSON.stringify(vm.roleSearch)
						}
					},					 
					pageSize: 20
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
					title: "STT",
					field:"stt",
			        template: dataItem => $("#authorManaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã vai trò",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên vai trò",
			        field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template: dataItem =>
				'<div class="text-center""><a '+
				' class="#=userRoleId# icon_table "'+
				' ng-click=vm.actionPopup(dataItem)  uib-tooltip="Sửa" translate>'+
				'<i class="fa fa-pencil" aria-hidden="true"></i>'+
			'</a>'+
			'<a type="button"'+	
				'class="#=userRoleId# icon_table"  uib-tooltip="Xóa" translate>'+
				'<i class="fa fa-trash" aria-hidden="true" ng-click=vm.remove(dataItem)></i>'+
			'</a>'
				+'</div>',
		        width: 150,
		        field:"stt"
			}
			,]
		});
		}
		
		function fillDataTableAddPopup(data) {
			vm.addPopupGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "userRoleServiceRest/userRole/doSearchRole",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							vm.findRole.keySearch=vm.roleSearch.keySearch;
							vm.findRole.sysUserId = vm.detailRole.sysUserId;
							 vm.findRole.page = vm.roleSearch.page
							 vm.findRole.pageSize = vm.roleSearch.pageSize
								
								return JSON.stringify(vm.findRole)
						}
					},					 
					pageSize: 20
				},
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					 pageSize: 20,
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#addPopupGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã vai trò",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên vai trò",
			        field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{title : "<input type='checkbox' id='chkSelectAll' name='chkSelectAll' ng-click='caller.chkSelectAll();' ng-model='caller.chkAll' />",
					 headerAttributes: {style:"text-align:center;"},
					 template : "<input type='checkbox' name='gridcheckbox' ng-click='caller.handleCheck();' />",
					 attributes:{style:"text-align:center;"},
					 width : 20 }
				,]
			});
		}
		vm.doSearchRole= doSearchRole;
		function doSearchRole(){
					var grid =vm.addPopupGrid;	
					if(grid){
						grid.dataSource.query({
							page: 1,
							pageSize: 20
						});
					
			}
		}
		
		
		function fillDataTableActionPopup(data) {
			vm.actionPopupGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "userRoleServiceRest/userRole/doSearchUserRoleData",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							//vm.findRole.keySearch=vm.roleSearch.keySearch;
							vm.findRole.keySearchAction=vm.roleSearch.keySearchAction;
							vm.findRole.sysUserId = vm.detailRole.sysUserId;
							vm.findRole.userRoleId = vm.detailRole.userRoleId;
							vm.findRole.domainDataId = vm.detailRole.domainDataId;
							 vm.findRole.page = vm.roleSearch.page
							 vm.findRole.pageSize = vm.roleSearch.pageSize
								
								return JSON.stringify(vm.findRole)
						}
					},					 
					pageSize: 20
				},
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
					title: "STT",
					field:"stt",
			        template: dataItem => $("#actionPopupGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã kho",
					field: 'dataCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên kho",
			        field: 'dataName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị",
			        field: 'dataName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Xóa",
			        template: dataItem =>
					'<div class="text-center"">'+
				'<a type="button"'+	
					'class="#=userRoleId# icon_table" style="color:red;"  uib-tooltip="Xóa" translate>'+
					'<i class="fa fa-times" aria-hidden="true" ng-click=caller.deleteUserRoleData(dataItem)></i>'+
				'</a>'
					+'</div>',
			        width: 50,
			        field:"stt",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		//doSearchAction
		vm.doSearchUserRoleData= doSearchUserRoleData;
		function doSearchUserRoleData(){
					var grid =vm.actionPopupGrid;	
					if(grid){
						grid.dataSource.query({
							page: 1,
							pageSize: 20
						});
					
			}
		}
		//
		vm.createNew = createNew;
			function createNew(){
				if(vm.detailRole.sysUserId != null){
					var teamplateUrl="qlk/authorizedManage/authorizedManagePopup.html";
					 var title="Bổ sung vai trò người dùng";
					 var windowId="AUTHOR_POPUP";
							fillDataTableAddPopup([]);						 
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.role,vm,windowId,true,'80%','80%'); 
				}
				else{
					toastr.warning(gettextCatalog.getString("Đã có lỗi xảy ra khi !"));
				}
			 
		 }	
			$scope.filterTree=filterTree
//			 $("#filterText").keyup(function (e) {
			function filterTree(keyEvent){
				filter($scope.treeView.dataSource, keyEvent.target.value.toLowerCase());
			}
					vm.cancel= cancel ;
		function cancel(){
				vm.appParam={};
				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		
		vm.refreshGrid= refreshGrid ;
		function refreshGrid(d) {
			var grid = vm.addPopupGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.actionPopup=actionPopup;
		function actionPopup(dataItem){
			var teamplateUrl="qlk/authorizedManage/actionPopup.html";
			 var title="Kho thao tác của vai trò";
			 var windowId="ACTION_POPUP"
				 fillDataTableActionPopup([]);						 
				 vm.detailRole.name = dataItem.name;
				 vm.detailRole.userRoleId=dataItem.userRoleId;
				 vm.detailRole.domainDataId=dataItem.domainDataId;
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.detailRole,vm,windowId,false,'85%','85%'); 
		}
		
		vm.remove=remove;
		function remove(dataItem){
			confirm('Xác nhận xóa',function (){
				authManaService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							vm.doSearch();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} 
			)
	}
		//deleteUserRoleData
		vm.deleteUserRoleData=function(dataItem){
			confirm('Xác nhận xóa',function (d){
				authManaService.deleteUserRoleData(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							$('#actionPopupGrid').data('kendoGrid').dataSource.read();
							$('#actionPopupGrid').data('kendoGrid').refresh();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} 
			)
			vm.doSearch();
	}
		
		
		//addRole
		vm.addRole = function() {
			var selectedRow = [];
			var grid = vm.addPopupGrid;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					var dataItem = grid.dataItem(item);
					dataItem.sysUserId = vm.detailRole.sysUserId;
					dataItem.isActive = 1;
					selectedRow.push(dataItem);
				}
			});
			if(selectedRow.length == 0 )
			{
			    toastr.warning("Bạn chưa chọn bản ghi !");
			}else{
				authManaService.addRole(selectedRow).then(
						function(d) {
							toastr.success("Bổ sung thành công!");
							vm.doSearch();
							CommonService.dismissPopup();
						}, function(errResponse) {
							toastr.error("Lỗi thêm mới!");
						});		
				}
		}
		
		//insertUserRoleData
		vm.insertUserRoleData = function() {
			var selectedRow = [];
			var grid = vm.actionPopupGrid;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
					var dataItem = grid.dataItem(item);
					dataItem.userRoleId = vm.detailRole.userRoleId;
					dataItem.domainDataId =vm.roleSearch.domainDataId;
					selectedRow.push(dataItem);
			});
				authManaService.insertUserRoleData(selectedRow).then(
						function(d) {
							toastr.success("Bổ sung thành công!");
							vm.doSearch();
							CommonService.dismissPopup();
						}, function(errResponse) {
							toastr.error("Lỗi thêm mới!");
						});		
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.authorManaGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.authorManaGrid.showColumn(column);
            } else {
            	vm.authorManaGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		** Filter các cột của select 
		*/	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		vm.goTo = function(menuKey) {
			
			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel : "Tab",
					topic   : "open",
					data    : template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
			
		}
		
	}
})();