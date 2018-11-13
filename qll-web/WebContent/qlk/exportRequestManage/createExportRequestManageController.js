(function() {
	'use strict';
	var controllerId = 'createExportRequestManageController';
	angular.module('MetronicApp').controller(controllerId,
			createExportRequestManageController);
	function createExportRequestManageController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, CommonService, PopupConst, createExportRequestManageService,
			Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showDetail = false;
		vm.showStepOne = true;
		vm.businessTypes ={};
		vm.order = {};
		vm.showExtForDepartment = true;
		vm.showExtForConstruct = false;
		vm.showExtForBHSC = false;
		vm.showExtAlternativeStock = false;
		vm.showExtForGift = false;
		vm.showExtForBorrow = false;
		vm.showExtForPay = false;
		vm.showExtForSale = false;
		vm.showExtForProject = false;
		vm.showExtForSell = false;
		vm.show = '1';
		vm.creExt = {
			extType : 1
		};
		vm.value = '1';
		
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
		
		vm.reason = {
				/*status : '1',
				apply : '5'*/
		};
		
		vm.people ={};
		
		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
      '<span>Mã</span>' +
      '<span>Tên</span>' +
      	'</div>';
		//autocomplete
		/**1**/
		vm.reasonOptions = {
	            dataTextField: "name", // 
	          /*  select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.roleSearch.code = dataItem.code; // thành id
	                vm.roleSearch.name = dataItem.name;//thành name
	               // vm.detailRole.userRoleId=dataItem.userRoleId;
	            },*/
	            pageSize: 10,
	            headerTemplate: '<div class="dropdown-header k-widget k-header">' +
                '<span>ID</span>' +
                '<span>Name</span>' +
                '</div>',    
            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                    	createExportRequestManageService.getReasons(vm.reason).then(function(response){ 
	                    			options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    },
						/*parameterMap: function (options, type) {
							vm.findRole.userRoleId = vm.detailRole.userRoleId;
								return JSON.stringify(vm.findRole)
						}*/
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-6" style="float:left">#: data.reasonId #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	           /* change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.roleSearch.code = null; // thành id
	                	vm.roleSearch.name = null;//thành name
	                }
	            },*/
	            ignoreCase: false
	        };
		
		vm.userOptions = {
	            dataTextField: "fullName", // 
	          /*  select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.roleSearch.code = dataItem.code; // thành id
	                vm.roleSearch.name = dataItem.name;//thành name
	               // vm.detailRole.userRoleId=dataItem.userRoleId;
	            },*/
	            pageSize: 10,
	            headerTemplate: '<div class="dropdown-header k-widget k-header">' +
                '<span>ID</span>' +
                '<span>Name</span>' +
                '</div>',    
            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                    	createExportRequestManageService.getForUsers(vm.people).then(function(response){ 
	                    		options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    },
						/*parameterMap: function (options, type) {
							vm.findRole.userRoleId = vm.detailRole.userRoleId;
								return JSON.stringify(vm.findRole)
						}*/
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-6" style="float:left">#: data.fullName #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.phoneNumber #</div> </div>',
	           /* change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.roleSearch.code = null; // thành id
	                	vm.roleSearch.name = null;//thành name
	                }
	            },*/
	            ignoreCase: false
	        };
		
	
		//EndAutoComplete
		

		vm.addExtOrder = {};
//		vm.showInfo = showInfo;
//		function showInfo() {
//			vm.show = vm.creExt.extType
//		}
		vm.nextStep = function() {
			vm.showStepOne = false;
		}
		vm.prevStep = function() {
			vm.showStepOne = true;
		}
		vm.cancel = function() {
			vm.goTo('EXPORT_REQUEST_MANAGE');
		}
		var par = {
				parType : "EXPORT_ORDER_TYPE"
		}
		createExportRequestManageService.getForExtOrderCheckboxes(par).then(function(d) {
			vm.businessTypes = d.plain();
		});
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data ,
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
			        template: dataItem => $("#creExReqMaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'apply',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'status',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tình trạng",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Chi tiết hàng hóa",
					 field: 'status',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Chọn",
			        template:
			        	'<div class="text-center #=reasonId#""><button type="button"'+
					' class="btn btn-default padding-button box-shadow  #=reasonId#"'+
					' ng-click=vm.edit(#=reasonId#)>'+
					'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=reasonId#"'+
					'ng-click=vm.remove(#=reasonId#)>'+
					'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'
					+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		
		
		vm.checkbox = function(){
			vm.showExtForDepartment = false;
			vm.showExtForConstruct = false;
			vm.showExtForBHSC = false;
			vm.showExtAlternativeStock = false;
			vm.showExtForGift = false;
			vm.showExtForBorrow = false;
			vm.showExtForPay = false;
			vm.showExtForSale = false;
			vm.showExtForProject = false;
			vm.showExtForSell = false;

			if (document.getElementById("extForDepartment").checked == true) {
		        //Checkbox has been checked
		    	vm.showExtForDepartment = true;
		    	vm.order.bussinessType = vm.businessTypes[0].code;
		    }
			if (document.getElementById("extForConstruct").checked == true) {
		        //Checkbox has been checked
		    	vm.showExtForConstruct = true;
		    	vm.order.bussinessType = vm.businessTypes[1].code;
		    }
			if (document.getElementById("extForBHSC").checked == true) {
		        //Checkbox has been checked
		    	vm.showExtForBHSC = true;
		    	vm.order.bussinessType = vm.businessTypes[2].code;
		    }
			if (document.getElementById("extAlternativeStock").checked == true) {
		        //Checkbox has been checked
		    	vm.showExtAlternativeStock = true;
		    	vm.order.bussinessType = vm.businessTypes[3].code;
		    }
			if (document.getElementById("extForGift").checked == true) {
			        //Checkbox has been checked
			    vm.showExtForGift = true;
			    vm.order.bussinessType = vm.businessTypes[4].code;
			}
			if (document.getElementById("extForBorrow").checked == true) {
		        //Checkbox has been checked
		    	vm.showExtForBorrow = true;
		    	vm.order.bussinessType = vm.businessTypes[5].code;
		    }
			if (document.getElementById("extForPay").checked == true) {
		        //Checkbox has been checked
		    	vm.showExtForPay = true;
		    	vm.order.bussinessType = vm.businessTypes[6].code;
		    }
			if (document.getElementById("extForSale").checked == true) {
		        //Checkbox has been checked
		    	vm.showExtForSale = true;
		    	vm.order.bussinessType = vm.businessTypes[7].code;
		    }
			if (document.getElementById("extForProject").checked == true) {
		    	vm.showExtForProject = true;
		    	vm.order.bussinessType = vm.businessTypes[8].code;
		    }
			if (document.getElementById("extForSell").checked == true) {
		    	vm.showExtForSell = true;
		    	vm.order.bussinessType = vm.businessTypes[9].code;
		    }
			
		}
		
		
		
		vm.nextStep = function() {
			vm.showStepOne = false;
//			if(vm.showFromProvider && vm.order.fromProviderCode!=null){
//				createImpReqManaService.getShipmentGoodsInfoByCode(vm.order.fromProviderCode).then(function(d) {
//					refreshGrid(d.plain());
//					vm.showStepOne = false;
//				});
//			}
		}
		vm.prevStep = function() {
			vm.showStepOne = true;
		}
		vm.cancel = function() {
			vm.goTo('EXPORT_REQUEST_MANAGE');
		}
		
		function refreshGrid(d) {
			var grid = $("#creExReqMaGrid").data("kendoGrid");;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.openDepartment=openDepartment
		function openDepartment(){
			var obj={};
//			obj.page=1;
//			obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data =result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
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
					columns:[ {
						title: "Mã",
						field: "code",
						width: 120
					}, {
						title: "Tên",
						field: "text",
						width: 120
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		vm.onSave=onSave;
		
		function onSave(data, popupID){
			if(popupID == 'create'){
			vm.addExtOrder.extForDepartmentName = data.text;
			vm.addExtOrder.extForDepartmentId = data.code;
			}
		}
		
		vm.goTo = function(menuKey) {

			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel : "Tab",
					topic : "open",
					data : template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}

		}
	}
})();