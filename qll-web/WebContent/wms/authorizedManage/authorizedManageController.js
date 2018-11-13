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
			fillDataTableActionPopup([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		//autoCompleteStock
		vm.headerTemplateStock='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Kho</p>' +
	      '<p class="col-md-6 text-header-auto">Tên Kho</p>' +
	      	'</div>';
			vm.roleSearch={};
			 vm.selectedStock = false;
		vm.stockOptions = {
	            dataTextField: "keySearch",
	            placeholder:"Nhập nhanh kho,tên kho để thêm mới",
	            select: function(e) {
				 vm.selectedStock = true;
			      var dataItem = this.dataItem(e.item.index());
	                vm.roleSearch.dataCode = dataItem.code; // thành id
	                vm.roleSearch.dataName = dataItem.name;//thành name
	                vm.roleSearch.domainDataId = dataItem.domainDataId;
					 var grid = $("#actionPopupGrid").data("kendoGrid");
					 var dt=$("#actionPopupGrid").data("kendoGrid").dataSource.data();
                var check = checkDups(vm.roleSearch);
                if(check){
                	toastr.warning("Mặt hàng đã tồn tại trong lưới!");
                }else{
					  dt.splice(0, 0, vm.roleSearch);
                }  
					
	            },
	            pageSize: 10,
				 open: function(e) {
	                        vm.selectedStock = false;
	                    },
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
						 vm.selectedStock = false;
						     return Restangular.all("userRoleServiceRest/userRole/" + 'getForAutoCompleteUserRoleData').post({keySearch:$("#stock").val().trim(),pageSize:vm.stockOptions.pageSize,domainDataId:vm.roleSearch.domainDataId }).then(function(response){
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
				if(processSearch('stock',vm.selectedStock)){	
					vm.roleSearch.dataCode = null; // thành id
	                	vm.roleSearch.dataName = null;//thành name
	                	vm.roleSearch.domainDataId = null;
						vm.selectedStock = false;
					 }
	                if (e.sender.value() === '') {
	                	vm.roleSearch.dataCode = null; // thành id
	                	vm.roleSearch.dataName = null;//thành name
	                	vm.roleSearch.domainDataId = null;
					 }
	            },
	            ignoreCase: false
	        };
		//Check du lieu tren luoi
		 function checkDups(stockItem){
				var isExisted = false;
				var stockGrid = vm.actionPopupGrid;
				var ds = $("#actionPopupGrid").data("kendoGrid").dataSource.data();
				if(ds.length!=0){
				for(var i=0;i<ds.length;i++){
	    					if(stockItem.domainDataId === ds[i].domainDataId){
	    						isExisted = true;
	    					}
						}
					}
	            return isExisted;
			}
		 //End
		 //Hamf tim kiem nguoi dung
	vm.doSearch=function doSearch(){
			trimSpace();
			if (!vm.validator.validate()) {
			document.getElementById("user").focus();
			return;
			}
			else{
			$scope.$watch("vm.roleSearch.loginName",function(){
				if(vm.roleSearch.loginName==null||vm.roleSearch.loginName===""){
				document.getElementById("user").focus();
				return;
				}
		});
				vm.checkData = true;
				fillDataTable([]);
					var grid =vm.authorManaGrid;	
					if(grid){
						grid.dataSource.query({
							page: 1,
							pageSize: 10
						});
					}
			}

		}
		//End
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
		//Hàm lấy danh sách vai trò đã được gán cho người dùng
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu:false,
				scrollable: false,
				toolbar: [
		                    {
		                        name: "actions",
		                        template: '<div class=" pull-left ">'+
		                        '<button class="btn btn-qlk padding-search-right addQLK1"'+
		      					'ng-click="vm.createNew()" uib-tooltip="Bổ sung vai trò" translate><p class="action-button add" aria-hidden="true">Bổ sung vai trò</p></a>'+
		      					'</div>'	
		        				+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
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
						 document.getElementById('count2').innerHTML=response.total;
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
					pageSize: 10
				} ,
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
				}
				,  {
					title: "Mã vai trò",
					field: 'code',
			        width: "30%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên vai trò",
			        field: 'name',
			        width: "35%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: "40%",
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
				'<button  class="fa btn departmentQLK"   aria-hidden="true"></button> '+
			'</a>'+
			'<button style=" border: none; background-color: white;" type="button"'+	
							'class="#=userRoleId# icon_table"  uib-tooltip="Xóa" ng-click=vm.remove(dataItem) translate>'+
							'<i class="fa fa-trash" style="color:steelblue;"  aria-hidden="true" ></i>'+
						'</button>'
				+'</div>',
		        width: "10%",
		        field:"stt"
			}
			,]
		});
		}
		//End
		//Danh sách vai trò chưa được gán cho người dùng
		var record1=0;
		function fillDataTableAddPopup(data) {
			vm.addPopupGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu:false,
				scrollable: false,
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
							//1.0
								var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].sysRoleId===$scope.listCheck[i].sysRoleId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list;
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
							vm.findRole.sysUserId = vm.roleSearch.sysUserId;
							 vm.findRole.page = options.page
							 vm.findRole.pageSize = options.pageSize
								
								return JSON.stringify(vm.findRole)
						}
					},					 
					pageSize: 10
				},
				dataBinding: function() {
					record1 = (this.dataSource.page() -1) * this.dataSource.pageSize();
				},
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					 pageSize: 10,
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columns: [
				{
					title: "TT",
					field:"#",
					template: function () {
						  return ++record1;
						 },
			        width: 20,
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
			        width: 170,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: 130,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{title : "<input type='checkbox' id='checkalllistImpReq1' name='gridchkselectall' ng-click='caller.chkSelectAll();' ng-model='caller.chkAll' />",
					 headerAttributes: {style:"text-align:center;"},
					 template : "<input type='checkbox' id='childcheck1' name='gridcheckbox' ng-click='caller.handleCheck(dataItem)' ng-model='dataItem.selected'/>",
					 attributes:{style:"text-align:center;"},
					 width : 20 }
				,]
			});
		}
		//End
		//1.1
		$scope.listCheck=[];
		vm.handleCheck=handleCheck;
    	function handleCheck(dataItem){
    		if(dataItem.selected){
    		$scope.listCheck.push(dataItem);
    		} else {
    			for(var i=0;i<$scope.listCheck.length;i++){
    				if($scope.listCheck[i].sysRoleId===dataItem.sysRoleId){
    				$scope.listCheck.splice(i,1);
    				}
    			}
				$('[name="gridchkselectall"]').prop('checked', false);
    		}
    		}
    	vm.chkSelectAll=chkSelectAll;
		$scope.checkSearch=false;
		function chkSelectAll(){
			var grid = vm.addPopupGrid;
	    		chkSelectAllBase(vm.chkAll, grid);
			if(vm.chkAll){
				if( $scope.checkSearch && $scope.dataSearch.length >0){
				$scope.listCheck=$scope.dataSearch;
				} else {
				
					CommonService.getallData("userRoleServiceRest/userRole/getAllUserRole",vm.findRole).then(function(data){
						$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}
		};
		//
		vm.doSearchRole= doSearchRole;
		function doSearchRole(){
			$scope.checkSearch=true;
					var grid =vm.addPopupGrid;	
					if(grid){
						grid.dataSource.query({
							page: 1,
							pageSize: 10
						});
					
			}
		CommonService.getallData("userRoleServiceRest/userRole/getAllUserRole",vm.findRole).then(function(data){
						$scope.dataSearch=data.plain();
				})	
		}
		//Load dữ liệu kho vai trò người dung
		vm.roleSearch={};
		function loadDataDetail(dataItem)
		{
		vm.roleSearch.userRoleId=dataItem.userRoleId;
			authManaService.doSearchUserRoleData(vm.roleSearch).then(function(result){
						refreshGrid1(result.plain());
						$('#actionPopupGrid').data("kendoGrid").dataSource.page(1);
				}, function(errResponse){
				if (errResponse.status === 409) {
					toastr.error(gettextCatalog.getString("Error!"));
				} else {
					toastr.error(gettextCatalog.getString("NoAccess!"));
				}
			});
		}
		
		function refreshGrid1(d) {
			var grid = $("#actionPopupGrid").data("kendoGrid");
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		//End
		//Danh sách kho vai trò
		var recordStock=0;
		function fillDataTableActionPopup(data) {
		var dataSource = new kendo.data.DataSource({
                pageSize: 10,
                data: data,
                autoSync: true,        
                schema: {
                    model: {
                        id: "dataCode",
                    	fields: {
							dataCode: {editable: false},
                    		stt: {editable: false},
                    		dataName: {editable: false},
                    		del: {editable: false}
                    	}
                    }
                }
            });
			vm.actionPopupGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu:false,
				scrollable: false,
				dataSource: dataSource,
				dataBinding: function() {
					recordStock = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					title: "TT",
					field:"stt",
			       template: function () {
						  return ++recordStock;
						 }, width: 20,
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
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên kho",
			        field: 'dataName',
			        width: 170,
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
			        width: 30,
			        field:"del",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		//Popup bổ sung vai trò người dùng
		vm.createNew = createNew;
			function createNew(){
				vm.roleSearch.keySearch=null;
					var teamplateUrl="wms/authorizedManage/authorizedManagePopup.html";
					 var title="Bổ sung vai trò người dùng";
					 var windowId="AUTHOR_POPUP";
							fillDataTableAddPopup([]);
							$scope.listCheck=[];
							vm.chkAll=false;
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.role,vm,windowId,true,'80%','80%','filterText'); 
		 }	
			//End
			$scope.filterTree=filterTree;
//			 $("#filterText").keyup(function (e) {
			function filterTree(keyEvent){
				filter($scope.treeView.dataSource, keyEvent.target.value.toLowerCase());
			}
					vm.cancel= cancel ;
		function cancel(){
			confirm('Xác nhận hủy',function (){
				vm.appParam={};
				vm.showDetail = false;
				vm.isCreateNew = false;
			});
		}
		
		vm.refreshGrid= refreshGrid ;
		function refreshGrid(d) {
			var grid = vm.addPopupGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		//Popup Kho thao tác của vai trò
		vm.actionPopup=actionPopup;
		function actionPopup(dataItem){
			vm.roleSearch.keySearch=null;
			vm.detailRole=dataItem;	
			 $scope.$on("Popup.open",function(){
			 loadDataDetail(dataItem);	
			 });
			var teamplateUrl="wms/authorizedManage/actionPopup.html";
			 var title="Kho thao tác của vai trò";
			 var windowId="ACTION_POPUP";		 
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.detailRole,vm,windowId,false,'85%','85%','stock'); 
			 
		}
		//End
		
		//Xóa vai trò của người dùng
		vm.remove=remove;
		function remove(dataItem){
			confirm('Xác nhận xóa',function (){
				authManaService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							 var sizePage = $("#authorManaGrid").data("kendoGrid").dataSource.total();
								if(sizePage % 10 === 1||sizePage % 15 === 1||sizePage % 20 === 1||sizePage % 25 === 1){
									var currentPage = $("#authorManaGrid").data("kendoGrid").dataSource.page();
									if (currentPage > 1) {
										$("#authorManaGrid").data("kendoGrid").dataSource.page(currentPage - 1);
									}
								}
							$("#authorManaGrid").data("kendoGrid").dataSource.read();
	            			$("#authorManaGrid").data("kendoGrid").refresh();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} 
			)
	}
		//End
		//deleteUserRoleData
		vm.deleteUserRoleData=function(dataItem){
			confirm('Xác nhận xóa',function (d){
				$('#actionPopupGrid').data('kendoGrid').dataSource.remove(dataItem);
				 var sizePage = $("#actionPopupGrid").data("kendoGrid").dataSource.total()+1;
								if(sizePage % 10 === 1||sizePage % 15 === 1||sizePage % 20 === 1||sizePage % 25 === 1){
									var currentPage = $("#actionPopupGrid").data("kendoGrid").dataSource.page();
									if (currentPage > 1) {
										$("#actionPopupGrid").data("kendoGrid").dataSource.page(currentPage - 1);
									}
								}
			} 
			);
			vm.doSearch();
	}
	//End	
		
		//addRole- Bổ sung vai trò
		vm.addRole = function() {
			trimSpace();
			for(var i in $scope.listCheck){
			$scope.listCheck[i].sysUserId=vm.roleSearch.sysUserId;
			}
			if($scope.listCheck.length === 0)
			{
			    toastr.warning("Bạn chưa chọn bản ghi !");
			}else{
			
			
				authManaService.addRole($scope.listCheck).then(
						function(d) {
							toastr.success("Bổ sung thành công!");
							vm.doSearch();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
						}, function(errResponse) {
							toastr.error("Lỗi thêm mới!");
						});		
			}
		}
		//End
		//insertUserRoleData-Bổ sung kho vai trò
		vm.insertUserRoleData = function() {
			var selectedRow = [];
			var grid = vm.actionPopupGrid;
		var dataGoodsGrid = $('#actionPopupGrid').data("kendoGrid").dataSource.data();
				for(var i = 0; i < dataGoodsGrid.length;i++){
					if(dataGoodsGrid.length!=0){
					dataGoodsGrid[i].userRoleId = vm.detailRole.userRoleId;
					dataGoodsGrid[i].isDefault = 1;
					if(dataGoodsGrid[i].domainDataId==null){
						dataGoodsGrid[i].domainDataId =vm.roleSearch.domainDataId;
						}
					selectedRow.push(dataGoodsGrid[i]);
					}
			}
			if(selectedRow.length===0){
				selectedRow.push(vm.detailRole);
			}
			authManaService.insertUserRoleData(selectedRow).then(
					function(d) {
						toastr.success("Bổ sung thành công!");
						$("#authorManaGrid").data("kendoGrid").dataSource.read();
            			$("#authorManaGrid").data("kendoGrid").refresh();
						$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					}, function(errResponse) {
						toastr.error("Lỗi thêm mới!");
					});		
		}
		//End
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
		 //AutocompleteUser
		vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Nhân Viên</p>' +
	      '<p class="col-md-6 text-header-auto">Tên Đăng Nhập</p>' +
	      	'</div>';
			vm.selectedUser=false;
		vm.goodsOptions = {
	            dataTextField: "loginName",
	            select: function(e) {
				vm.selectedUser=true;
	                var dataItem = this.dataItem(e.item.index());
	              vm.roleSearch.employeeCode = dataItem.employeeCode; // thành id
	              vm.roleSearch.loginName = dataItem.loginName;//thành name
	              vm.roleSearch.domainDataId = dataItem.domainDataId;
	              vm.roleSearch.sysUserId = dataItem.sysUserId;
	            },
	            pageSize: 10,
				 open: function(e) {
	                        vm.selectedUser = false;
	                    },
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
						vm.selectedUser=false;
	                        return Restangular.all("userRoleServiceRest/userRole/" + 'getForAutoComplete').post({loginName:vm.roleSearch.loginName,pageSize:vm.goodsOptions.pageSize}).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.employeeCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.loginName #</div> </div>',
	            change: function(e) {
				if(processSearch('user',vm.selectedUser)){	
						vm.roleSearch.employeeCode = null; // thành id
	                	vm.roleSearch.loginName = null;//thành name
						vm.selectedUser=false;
					 }
	                if (e.sender.value() === '') {
	                	vm.roleSearch.employeeCode = null; // thành id
	                	vm.roleSearch.loginName = null;//thành name
	                }
	            },
	            ignoreCase: false
	        }; 
		//SetFocus
		setTimeout(function(){
			  $("#user").focus();
			},15);
		//
		vm.changeDataAuto=changeDataAuto;
		function changeDataAuto(id){
		switch(id){
			case 'user':{	
			if(processSearch(id,vm.selectedUser)){
							vm.roleSearch.employeeCode = null; // thành id
							vm.roleSearch.loginName = null;//thành name
			}
			if(!vm.selectedUser){
				vm.roleSearch.loginName = null;
			}
		break;
					}
					case 'stock':{	
			if(processSearch(id,vm.selectedStock)){
							vm.roleSearch.keySearch = null; 
							vm.roleSearch.dataCode = null; // thành id
							vm.roleSearch.dataName = null;//thành name
							vm.roleSearch.domainDataId = nul;
				}
		break;
					}
		}
		}
		//End
		//Enter
	$(document).on("keypress", function (e) {
         		switch(e.keyCode) {
         			case 27:
         				$("#cancel11").click();
         				break;
         			case 13:
					if($(':focus').size()===0){
					$( "#savData" ).click(function( event ) {
							event.stopPropagation();
							});
							break;
					}
					 if (e.keyCode == 13&& !$('#savData:focus').length&& !$('#cancel:focus').length&&!$('#confirmPopup_btnConfirm:focus').length&&!$('#confirmPopup_btnCancel:focus').length) {
         				$("#savData").click();
         				break;
						}
         		}
			} );
		//End
	}
})();
