(function() {
	'use strict';
	var controllerId = 'createExportRequestManageController';
	angular.module('MetronicApp').controller(controllerId,
			createExportRequestManageController);
	function createExportRequestManageController($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, CommonService, PopupConst, createExportRequestManageService,impReqManaService,
			Restangular, RestEndpoint, Constant,exReqManaService) {
		var vm = this;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.showSearch = true;
		vm.showDetail = false;
		vm.showStepOne = true;
		vm.orderGoodsSearch = {};
		vm.businessTypes ={};
		vm.orderGoodsSearch.departmentpopUp ={};
		vm.ordergood = {};
		vm.ordergood.stockName = [];			
		vm.ordergoodId = [];
		vm.project ={};
		vm.contract = {};
		vm.exportOrder = {};
		vm.construction = {};
		vm.userSearch = {};
		vm.good= {};
		vm.getlistGood = [];
		vm.lstGood = [];
	    vm.disableOrderType = false;
		vm.changeStock = false;

		vm.OrderGoods={
				status:'1'
		};
		vm.orderPattern={
				status:'1'
		};
		vm.orderPatternGood={};
			

		
		var par = {
				parType : "EXPORT_ORDER_TYPE"
		}
		
		vm.errMessage2 = '';
		vm.validateDate = validateDate;
		function validateDate(){
			var recDate = $('#receiveDate').val();
			if(recDate.length != 0){
				var curDate = new Date();
		        
		        if(kendo.parseDate(recDate,"dd/MM/yyyy") == null){
		        	vm.errMessage2 = 'Ngày nhận hàng dự kiến không hợp lệ';
		        	$('#receiveDate').focus();
		        	return vm.errMessage2;
		        }
		        	
		        else {
		        	if(kendo.parseDate(recDate,"dd/MM/yyyy") > curDate ){	
		        		vm.errMessage2 = '';
		        		$('#receiveDate').focus();return vm.errMessage2;}
		        	else{
		        		vm.errMessage2 = 'Ngày nhận hàng dự kiến phải lấy từ ngày hiện tại trở đi';
			        	$('#receiveDate').focus();
			        	return vm.errMessage2;
		        	}
		        }
			}else{
				vm.errMessage2 = '';
				return vm.errMessage2;
			}
		}
		
		
createExportRequestManageService.getForExtOrderCheckboxes(par).then(function(d) {
			vm.businessTypes = d.plain();
			if(vm.exportOrder.orderId == null){
				vm.exportOrder.bussinessType = vm.businessTypes[0].code;
				/* vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.partnerName = "filled";
				vm.exportOrder.projectCode = "filled"; */
				vm.disOpt  = false;
			}
			if (vm.exportOrder.bussinessType == vm.businessTypes[0].code ) {
		    	vm.showExtForDepartment =  true;
		    	$("#extForDepartment").prop("checked", true);
		    	
		    }
			 if (vm.exportOrder.bussinessType == vm.businessTypes[1].code) {
		  
				vm.showExtForConstruct = true;
		    	$("#extForConstruct").prop("checked", true);
		    	
		    	
		    }
			 if (vm.exportOrder.bussinessType == vm.businessTypes[2].code ) {
		  
				vm.showExtForBHSC = true;
		    	$("#extForBHSC").prop("checked", true);
		    	
		    }
			 if (vm.exportOrder.bussinessType == vm.businessTypes[3].code ) {
		
				vm.showExtAlternativeStock = true;
		    	$("#extAlternativeStock").prop("checked", true);
		    	
			}
			 if (vm.exportOrder.bussinessType == vm.businessTypes[4].code ) {
			     
				vm.showExtForGift  = true;
			    $("#extForGift").prop("checked", true);
			   
			}
			 if (vm.exportOrder.bussinessType == vm.businessTypes[5].code ) {
		    
		    	vm.showExtForBorrow = true;
		    	$("#extForBorrow").prop("checked", true);
		    	
		    }
			 if (vm.exportOrder.bussinessType == vm.businessTypes[6].code ) {
		     
				vm.showExtForPay = true;
		    	$("#extForPay").prop("checked", true);
		    	
		    }
			 if (vm.exportOrder.bussinessType == vm.businessTypes[7].code ) {
				vm.showExtForSale = true;
		    	$("#extForSale").prop("checked", true);
		    	
		    }
			 if (vm.exportOrder.bussinessType == vm.businessTypes[8].code ) {
		        vm.showExtForProject = true;
				$("#extForProject").prop("checked", true);
				
		    }
			 if (vm.exportOrder.bussinessType == vm.businessTypes[9].code ) {
				vm.showExtForSell = true;
				$("#extForSell").prop("checked", true);
				
		    }
			if(vm.exportOrder.orderId != null){
				checkBoxAutoLoad();
			}
			
		});
		
		//if(vm.exportOrder.orderId == null){
			$scope.$watch('vm.exportOrder.stockId', function() {
				if(vm.changeStock){

				var obj={};
				obj.value="YCXK";
				obj.orgValue="VTM";
				obj.stockId=vm.exportOrder.stockId;
				CommonService.genCode(obj).then(
					function(d) {
						vm.exportOrder.code = d;
						//vm.exportOrder.title = "Tạo mới yêu cầu xuất kho";
					});
					}
		    });
			
			$scope.$watch('vm.tmpStockId', function() {
				vm.changeStock = true;
			});
		//}
		vm.folder = '';
		
		vm.orderGoodDetail={
				status:'1'
		};
		
		vm.show = '1';
		vm.creExt = {
			extType : 1
		};
		vm.value = '1';
		
		if(exReqManaService.getData()){
			vm.exportOrder = exReqManaService.getData();
			if(vm.exportOrder.bussinessType != undefined || vm.exportOrder.bussinessType != null){
				vm.disOpt = true;
				$("#creExtReqBCIconOne").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconOne").addClass("acceptQLK postion-icon");
				$("#creExtReqBCIconThree").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconThree").addClass("acceptQLK postion-icon");
				$("#creExtReqBCIconTwo").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconTwo").addClass("acceptQLK postion-icon");
				$("#creExtReqBCIconFour").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconFour").addClass("acceptQLK postion-icon");
			}
		}
		
		$scope.$on("ezOrderDetail", function (event, item) {
        	if(item != undefined){
        		vm.exportOrder = item;	
				vm.tmpStockId = vm.exportOrder.stockId;
        		vm.disOpt = true;
        		$("#creExtReqBCIconOne").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconOne").addClass("acceptQLK postion-icon");
				$("#creExtReqBCIconThree").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconThree").addClass("acceptQLK postion-icon");
				$("#creExtReqBCIconTwo").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconTwo").addClass("acceptQLK postion-icon");
				$("#creExtReqBCIconFour").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconFour").addClass("acceptQLK postion-icon");
				fillDataTableGoodsList(vm.exportOrder.listOrderGoodsDetailDTO);
        	}else{
			    vm.changeStock = true;
        		console.error("không nhận được dữ liệu!");
        	}
        });
		 
		if(exReqManaService.getData()){
			vm.exportOrder= exReqManaService.getData();
		}
		
		
		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
      '<span>Mã</span>' +
      '<span>Tên</span>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		vm.gridCreator = [  {
			title: "Tên đăng nhập",
			field: "loginName",
			width: 120
		},{
			title: "Mã nhân viên",
			field: "employeeCode",
			width: 120
		}, {
			title: "Họ tên",
			field: "fullName",
			width: 120
		}, {
			title: "Email",
			field: "email",
			width: 120
		}, {
			title: "SĐT",
			field: "phoneNumber",
			width: 120
		}];
		
		vm.gridCommon = [ {
			title: "Mã kho",
			field: "code",
			width: 120
		}, {
			title: "Tên kho",
			field: "name",
			width: 120
		},{title: "Tên đơn vị tạo",
			field: "code",
			width: 120
		}, {
			title: "Tên kho",
			field: "name",
			width: 120
		}];
		
		//Templates for search boxes
		//Stocks
		vm.stockTemplate='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.stockHeaderTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã kho</p>' +
      '<p class="col-md-6 text-header-auto">Tên kho</p>' +
      	'</div>';
		
		//Users
		vm.userTemplate='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
		vm.userHeaderTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
      '<p class="col-md-6 text-header-auto">Tên nhân viên</p>' +
      	'</div>';
		
		
		  
			vm.onSave=onSave;
			function onSave(data1, popupID){
				/*if(popupID == 'depOpenCreateCP'){
					vm.exportOrder.createdDeptedName = data1.text;
					vm.exportOrder.createdDeptedId = data1.code;
				}
				else*/ if(popupID == 'depOpenToCP'){
					vm.exportOrder.deptReceiveName = data1.text;
					vm.exportOrder.deptReceiveId = data1.code;
				}else if(popupID == 'depOpenCreate'){
					vm.orderSearch.createdDeptedName = data1.text;
					vm.orderSearch.createdDeptedId = data1.code;
				}else if(popupID == 'depOpenTo'){
					vm.orderSearch.deptReceiveName = data1.text;
					vm.orderSearch.deptReceiveId = data1.code;
				}else{
					
				}
			}  
		
		vm.openDepartmentCreate=openDepartmentCreate
		function openDepartmentCreate(popUpId){
			vm.obj={};
			vm.orderGoodsSearch.departmentpopUp = popUpId;
			CommonService.getDepartment(vm.obj).then(function(result){
				var templateUrl = 'wms/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Tìm kiếm đơn vị");
				var data1=result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
					noRecords: true,
					messages: {
						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
					},
					pageSize: 10,
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
						title: "TT",
						field: "stt",
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width: 70
						},{
						title: "Mã phòng ban",
						field: "code",
						width: 150
					}, {
						title: "Tên phòng ban",
						field: "text",
						width: 210
					}, {
						title: "Đơn vị cha",
						field: "parentName",
						width: 210
					},{
						title: "Ngày hiệu lực",
						field: "effectDate",
						
						width: 140
					},{
						title: "Ngày hết hiệu lực",
						field: "endDate",
						width: 150
					}]
				});
				
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data1, vm, popUpId , 'string', false, '91%','89%');
			});
		}
		
		
		vm.openDepartmentTo=openDepartmentTo
		function openDepartmentTo(popUpId){
			vm.obj={};
			vm.orderGoodsSearch.departmentpopUp = popUpId;
			CommonService.getDepartment(vm.obj).then(function(result){
				var templateUrl = 'wms/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Tìm kiếm đơn vị");
				var data1=result.plain();
				vm.gridOptions1 = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
					noRecords: true,
					pageSize: 10,
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
						title: "TT",
						field: "stt",
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width: 70
						},{
						title: "Mã phòng ban",
						field: "code",
						width: 150
					}, {
						title: "Tên phòng ban",
						field: "text",
						width: 210
					}, {
						title: "Đơn vị cha",
						field: "parentName",
						width: 210
					},{
						title: "Ngày hiệu lực",
						field: "effectDate",
						
						width: 140
					},{
						title: "Ngày hết hiệu lực",
						field: "endDate",
						width: 150
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions1,data1, vm, popUpId, 'string', false, '92%','89%');
			});
		}
			
		vm.onSave=onSave;
		function onSave(data){
			if(vm.orderGoodsSearch.departmentpopUp == 'depOpenCreateCP'){
			vm.exportOrder.createdDeptedName = data.text;
			vm.exportOrder.createdDeptedId = data.id;
			}
			else
				if(vm.orderGoodsSearch.departmentpopUp == 'depOpenToCP'){
				vm.exportOrder.deptReceiveName = data.text;
				vm.exportOrder.deptReceiveId = data.id;
			}
		}
		//autocomplete
		/** 1. Import notes* */
		vm.importNotecheck=false;
		vm.ImportNoteOptions ={
				   dataTextField: "code", 
			            pageSize: 10,
			            select: function(e) {
			                var dataItem = this.dataItem(e.item.index());
			                vm.exportOrder.ieStockTransId = dataItem.stockTransId;
			                vm.exportOrder.stockTransCode = dataItem.code;
							vm.importNotecheck=true;
			            },
						open: function(e) {
	                        vm.importNotecheck = false;
	                    }, 
						dataSource: {
			                serverFiltering: true,
			                transport: {
			                    read: function(options) {
								vm.importNotecheck=false;
			                    	createExportRequestManageService.doSearchImportNote({code: vm.exportOrder.stockTransCode}).then(function(response){ 
			                    			options.success(response.data);
			                        }).catch(function (err) {
			                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
			                        });
			                    },
			                }
			            },
			            template:'<div class="row" ><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.code #</div> </div>',
			        change: function(e) {
                    if (e.sender.value() === '') {
                        vvm.exportOrder.ieStockTransId = null;
                        vm.exportOrder.stockTransCode = null;
                    }
                },
			            ignoreCase: false
				
		};
		
		/**2.Users **/
		 //AutoCpmpleteUser
        vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Nhân Viên</p>' +
	      '<p class="col-md-6 text-header-auto">Tên Đăng Nhập</p>' +
	      	'</div>';
		
		
		/**3.Contracts**/
		vm.selectedContract=false;
		vm.contractOptions = {
				 select: function(e) {
		                var dataItem = this.dataItem(e.item.index());
		                vm.exportOrder.contractCode = dataItem.contractCode;
						vm.selectedContract=true;
		            },
				dataTextField: "contractCode", 
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
							vm.selectedContract=false;
	                    		createExportRequestManageService.getContracts({contractCode:vm.exportOrder.contractCode}).then(function(response){ 
		                    		options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		/**4. Construction**/
		vm.selectedconstrCode=false;
	    vm.extForConstructCodeOptions = {
			            dataTextField: "code", 
			            select: function(e) {
			                var dataItem = this.dataItem(e.item.index());
			                vm.exportOrder.constrCode = dataItem.code;
							vm.selectedconstrCode=true;
			            },
			            pageSize: 10,
			       
		            dataSource: {
			                serverFiltering: true,
			                transport: {
			                    read: function(options) {
								vm.selectedconstrCode=false;
			                    	createExportRequestManageService.getConstructionForAutoComplete({code:vm.exportOrder.constrCode}).then(function(response){ 
			                    		options.success(response);
			                        }).catch(function (err) {
			                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
			                        });
			                    },
			                }
			            },
			            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
			            '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
			            '<p class="col-md-6 text-header-auto">Tên</p>' +
			            	'</div>',
			            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
			            change: function(e) {
		  	                if (e.sender.value() === '') {
		  	                  vm.exportOrder.constrCode = null;
		  	                }
		  	            },
			            ignoreCase: false
		}
		
		/**5. Partners**/
	    vm.partner = {};
		 vm.selectedpartnerName=false;
		vm.extForPartnerCodeOptions = {
			            dataTextField: "code", // 
			            select: function(e) {
			                var dataItem = this.dataItem(e.item.index());
			                vm.exportOrder.partnerId = dataItem.partnerId;
			                vm.exportOrder.partnerName = dataItem.name;
							 vm.selectedpartnerName=true;
			                 },
			            pageSize: 10,
		            dataSource: {
			                serverFiltering: true,
			                transport: {
			                    read: function(options) {
								 vm.selectedpartnerName=false;
			                    	createExportRequestManageService.getPartnerForAutoComplete({code:vm.exportOrder.partnerName}).then(function(response){ 
			                    		options.success(response);
			                        }).catch(function (err) {
			                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
			                        });
			                    },
			                }
			            },
			            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
			            '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
			            '<p class="col-md-6 text-header-auto">Tên</p>' +
			            	'</div>',
			            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
			            change: function(e) {
		  	                if (e.sender.value() === '') {
		  	                  vm.exportOrder.partnerName =null;
		  	                }
		  	            },
			            ignoreCase: false
		}
	    
	    /**6. Projects**/
		vm.selectedPrpject=false;
	    vm.extForProjCodeOptions = {
	            dataTextField: "projectCode", 
	            pageSize: 10,
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.exportOrder.projectCode = dataItem.projectCode;
					vm.selectedPrpject=true;
	               },
            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
						vm.selectedPrpject=false;
	                    	createExportRequestManageService.getProjects({projectCode:vm.exportOrder.projectCode}).then(function(response){ 
	                    		options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    },
	                }
	            },
	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
	            '<p class="col-md-6 text-header-auto">Tên</p>' +
	            	'</div>',
	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.projectCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
  	                if (e.sender.value() === '') {
  	                	 vm.exportOrder.projectCode =null;
  	                }
  	            },
	            ignoreCase: false
			}
	    
		 /**7. Departments**/
	  
	    vm.selectedDept1=false;
	    vm.deprtOptions1 = {
  	            dataTextField: "text",
				dataValueField:"id",
  	            select: function(e) {
				vm.selectedDept1=true;
  	            var dataItem = this.dataItem(e.item.index());
  	            vm.exportOrder.deptReceiveName = dataItem.text;//thành name
				vm.exportOrder.deptReceiveId = dataItem.id;
  	            },
  	            pageSize: 10,
				open: function(e) {
	                        vm.selectedDept1 = false;
	                    }, 
  	            dataSource: {
  	                serverFiltering: true,
  	                transport: {
  	                    read: function(options) {
						vm.selectedDept1=false;
  	                        return Restangular.all("departmentServiceRest/department/" + 'getForAutocompleteDept').post({name:vm.exportOrder.deptReceiveName,pageSize:vm.deprtOptions1.pageSize}).then(function(response){
  	                            options.success(response);
  	                        }).catch(function (err) {
  	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
  	                        });
  	                    }
  	                }
  	            },
  	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.code #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.text #</div> </div>',
  	            change: function(e) {
  	                if (e.sender.value() === '') {
  	                	vm.exportOrder.deptReceiveName = null;
  	                	vm.exportOrder.deptReceiveId = null;
  	                }
  	            },
  	            ignoreCase: false
  	        };
		
		 //clear data
			vm.changeDataAuto=changeDataAuto
		function changeDataAuto(id){
		
		switch(id){
			case 'extForProject':{
			if(processSearch(id,vm.selectedPrpject)){
				 vm.exportOrder.projectCode =null;
			  vm.selectedPrpject=false;	
			 }
					break;
					}
				
			case 'exportContract':{
			if(processSearch(id,vm.selectedContract)){
		
	               vm.exportOrder.contractCode = null;
				   vm.selectedContract=false;
				}
					break;
					}
				
				
			case 'exportCopyDept':{
			if(processSearch(id,vm.selectedDept1)){	
					vm.exportOrder.deptReceiveName = null;//thành name
					vm.exportOrder.deptReceiveId = null;
					 vm.selectedDept1=false;
			 }
					break;
					}
					
			
			
			case 'extForConstructCreate':{
			if(processSearch(id,vm.selectedconstrCode)){	
					vm.exportOrder.constrCode = null;
					 vm.selectedconstrCode=false;
			 }
					break;
					}
			case 'extForGiftpartner':{
			if(processSearch(id,vm.selectedpartnerName)){	
					vm.exportOrder.partnerName = null;
					 vm.selectedpartnerName=false;
			 }
					break;
					}
			case 'importNote':{
			if(processSearch(id,vm.importNotecheck)){	
					vm.exportOrder.ieStockTransId = null;
			        vm.exportOrder.stockTransCode = null;
					vm.importNotecheck=false;
			 }
					break;
					}			
					
						}
		
			
		}
	    
/* 	    var reason = {};
		vm.dataReasonDelete = [];
	    createExportRequestManageService.getReasons(reason).then(function(d){
			vm.dataReasonDelete =  d.plain();
			if(vm.exportOrder.orderId == null){
				vm.exportOrder.reasonId = vm.dataReasonDelete[0].reasonId;
			}
		}); */
	    
		//EndAutoComplete
		
		//remove input data value
	    vm.cancelCreatedByName = function()
		{
			vm.userSearch.loginName= undefined;
		}
		vm.cancelInput = function(param){
				if(param == 'impNote'){// consider ng-model
					vm.exportOrder.stockTransCode  = null;
				}
				if(param == 'contract'){
					vm.exportOrder.contractCode = undefined;
				}
				if(param == 'date'){
					vm.exportOrder.expectedRecievedDate = null;
				}
				if(param == 'person'){	
					vm.exportOrder.recieverName = undefined;
				}
				if(param == 'cons'){
					vm.exportOrder.constrCode = undefined;
				}
				if(param == 'proj'){
					vm.exportOrder.projectCode = undefined;
				}
				if(param == 'partner'){
					vm.exportOrder.partnerName = undefined;
				}
				
		}
		
		vm.cancelDeptReceive = function()
		{
				vm.exportOrder.deptReceiveName = undefined;
				vm.exportOrder.deptReceiveId = undefined;
		}
		
		vm.isValidDate =isValidDate;
		function isValidDate()
		{
			var recDate = $('#receiveDate').val();
			if(recDate.length != 0){
				var curDate = new Date();
		        
		        if(kendo.parseDate(recDate,"dd/MM/yyyy") == null)
		        	return -1;
		        else {
		        	if(kendo.parseDate(recDate,"dd/MM/yyyy") > curDate)
	        			return 0;
		        	else
		        		return -2;
		        }
			}else{
				return 0;
			}
		}
		
		
		vm.nextStep = function() {
		vm.validator3.validate();
			vm.isValidInfo = false;
			//trimSpace();
					if(!vm.validator3.validate()){
					$("#creExtReqBCIconOne").removeClass("acceptQLK postion-icon");
					$("#creExtReqBCIconOne").addClass("declineQLK postion-icon");
					$("#creExtReqBCIconTwo").removeClass("acceptQLK postion-icon");
					$("#creExtReqBCIconTwo").addClass("declineQLK postion-icon");
					$("#creExtReqBCIconThree").removeClass("acceptQLK postion-icon");
					$("#creExtReqBCIconThree").addClass("declineQLK postion-icon");
					if(vm.exportOrder.stockId == null || vm.exportOrder.stockId == undefined){
											$("#orderStock2").focus();
						}else if(vm.exportOrder.recieverId == null || vm.exportOrder.recieverId == undefined){
											$("#recCU").focus();
						}else if(vm.showExtForDepartment){
							if(vm.exportOrder.deptReceiveName == "" || vm.exportOrder.deptReceiveName == null)
							$("#exportCopyDeptExtForDepartment").focus();
						}else if(vm.showExtForConstruct){
							if(vm.exportOrder.constrCode == "" || vm.exportOrder.constrCode == null)
							$("#extForConstructCreateExtForConstruct").focus();
							else if(vm.exportOrder.reasonId == "" || vm.exportOrder.deptReceiveName == null)
							//$("#cancelReasonName").focus();
							$("#cancelReasonName").data("kendoDropDownList").focus();
							else if(vm.exportOrder.deptReceiveName == "" || vm.exportOrder.deptReceiveName == null)
							$("#exportCopyDeptExtForConstruct").focus();

						}else if(vm.showExtForBHSC){
							if(vm.exportOrder.deptReceiveName == "" || vm.exportOrder.deptReceiveName == null)
							$("#exportCopyDeptExtForBHSC").focus();
						}else if(vm.showExtAlternativeStock){
							if(vm.exportOrder.ieStockName == "" || vm.exportOrder.ieStockName == null)
							$("#orderExpStockExtAlternativeStock").focus();
						}else if(vm.showExtForGift){
							if(vm.exportOrder.partnerName == "" || vm.exportOrder.partnerName == null)
							$("#extForGiftpartnerExtForGift").focus();
						}else if(vm.showExtForBorrow){
							if(vm.exportOrder.partnerName == "" || vm.exportOrder.partnerName == null)
							$("#extForGiftpartnerExtForBorrow").focus();
						}else if(vm.showExtForPay){
							if(vm.exportOrder.partnerName == "" || vm.exportOrder.partnerName == null)
							$("#extForGiftpartnerExtForPay").focus();
						}else if(vm.showExtForSale){
							if(vm.exportOrder.deptReceiveName == "" || vm.exportOrder.deptReceiveName == null)
							$("#exportCopyDeptExtForSale").focus();
						}else if(vm.showExtForProject){
							if(vm.exportOrder.projectCode == "" || vm.exportOrder.projectCode == null)
							$("#extForProjectExtForProject").focus();
						}else if(vm.showExtForSell){
							if(vm.exportOrder.deptReceiveName == "" || vm.exportOrder.deptReceiveName == null)
							$("#exportCopyDeptExtForSell").focus();
						}
					return;
				}
				$("#creExtReqBCIconOne").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconOne").addClass("acceptQLK postion-icon");
				$("#creExtReqBCIconThree").removeClass("declineQLK postion-icon");
				$("#creExtReqBCIconThree").addClass("acceptQLK postion-icon");
			if(vm.exportOrder.stockName.length != 0 && vm.exportOrder.recieverName.length != 0 && vm.errMessage2 == ''){
				if(vm.exportOrder.bussinessType == vm.businessTypes[0].code ||
						vm.exportOrder.bussinessType == vm.businessTypes[2].code ||
						vm.exportOrder.bussinessType == vm.businessTypes[7].code ||
						vm.exportOrder.bussinessType == vm.businessTypes[9].code ){
					
					if(vm.exportOrder.deptReceiveName.length != 0)
						{
							vm.isValidInfo = true;
						}
					
				}else if(vm.exportOrder.bussinessType == vm.businessTypes[4].code ||
						vm.exportOrder.bussinessType == vm.businessTypes[5].code ||
						vm.exportOrder.bussinessType == vm.businessTypes[6].code){
					
					if(vm.exportOrder.partnerName.length != 0)
					{
							vm.isValidInfo = true;
					}
					
				}else if(vm.exportOrder.bussinessType == vm.businessTypes[3].code){
					if(vm.exportOrder.ieStockName.length != 0)
					{	
						vm.isValidInfo = true;
					}
				}else if(vm.exportOrder.bussinessType == vm.businessTypes[8].code){
					if(vm.exportOrder.projectCode.length != 0)
					{	
						vm.isValidInfo = true;
					}
				}else{
					if((vm.exportOrder.constrCode.length != 0) 
							&& (vm.exportOrder.reasonId.length != 0) && 
							(vm.exportOrder.deptReceiveName.length != 0))
					{
						vm.isValidInfo = true;
					}
				}
			}
				
			if(vm.isValidInfo == false )	{
				vm.showStepOne = true;
			}
			else{
				vm.showStepOne = false;
				if(vm.exportOrder.orderId!= null){
					vm.fixedTableShow = true;
					vm.editTableShow = false;
					vm.orderGoodsSearch.orderId = vm.exportOrder.orderId;
					//fillDataTableGoodsList(data);
					//vm.disableImport = true;
					vm.disableOptions = false;
					//document.getElementById("goods").readOnly = true;
				}else{
					vm.fixedTableShow = false;
					vm.editTableShow = true;
					//vm.disableImport = false;
					vm.disableOptions = true;
				}
			}
			vm.ordergood.stockName = vm.exportOrder.stockName;
			
		}
		
		vm.prevStep = function() {
			vm.showStepOne = true;
		}
		vm.cancel = cancel;
		function cancel() {
			confirm('Bạn có chắc muốn hủy bỏ thao tác này', function(){
				CommonService.closeTab('UPDATE_EX_REQ_MANA');			
				CommonService.closeTab('CREATE_EX_REQ_MANA');
				vm.updateSearch = {};
				vm.updateSearch.page = vm.exportOrder.page;
				vm.updateSearch.pageSize = vm.exportOrder.pageSize;
				$rootScope.$broadcast("backFromCancelUpdate", vm.updateSearch);
				CommonService.goTo('EXPORT_STATEMENT_MANAGE');

			});
		}
		
		 function clearFilled(){
			// if (vm.exportOrder.bussinessType == vm.businessTypes[0].code ) {
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.partnerName = "";
				// vm.exportOrder.projectCode = "";
		    // }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[1].code) {
		  
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.partnerName = "";
				// vm.exportOrder.projectCode = "";
		    // }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[2].code ) {
		  
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.partnerName = "";
				// vm.exportOrder.projectCode = "";
		    // }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[3].code ) {
		
		    	// vm.exportOrder.deptReceiveName = "";
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.partnerName = "";
				// vm.exportOrder.projectCode = "";
		    // }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[4].code ) {
			     
			    // vm.exportOrder.deptReceiveName = "";
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.projectCode = "";
			// }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[5].code ) {
		    
		    	// vm.exportOrder.deptReceiveName = "";
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.projectCode = "";
		    // }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[6].code ) {
		     
		    	// vm.exportOrder.deptReceiveName = "";
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.projectCode = "";
		    // }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[7].code ) {
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.partnerName = "";
				// vm.exportOrder.projectCode = "";
		    // }
			// else if (vm.exportOrder.bussinessType == vm.businessTypes[8].code ) {
				// vm.exportOrder.deptReceiveName = "";
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.partnerName = "";
		    // }
			// else {
				// vm.exportOrder.constrCode = "";
				// vm.exportOrder.reasonId = null;
				// vm.exportOrder.ieStockName = "";
				// vm.exportOrder.partnerName = "";
				// vm.exportOrder.projectCode = "";
		    // }
		}
		
		
		function checkBoxAutoLoad(){
			vm.showExtForDepartment = false;
			vm.showExtForConstruct = false;
			vm.showExtForBHSC = false;
			vm.showExtAlternativeStock = false;
			vm.showExtForGift = false;
			vm.showExtForBorrow = false;
			vm.showExtForPay = false;
			vm.showExtForSale = false;
			vm.showExtForSell = false;
			vm.showExtForProject = false;

			
			if (vm.exportOrder.bussinessType == vm.businessTypes[0].code ) {
		    	vm.showExtForDepartment =  true;
		    	$("#extForDepartment").prop("checked", true);

				/* vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.partnerName = "filled";
				vm.exportOrder.projectCode = "filled"; */
		    }
			if (vm.exportOrder.bussinessType == vm.businessTypes[1].code) {
				vm.showExtForConstruct = true;
		    	$("#extForConstruct").prop("checked", true);
				/* vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.partnerName = "filled";
				vm.exportOrder.projectCode = "filled"; */
		    	
		    }
			if (vm.exportOrder.bussinessType == vm.businessTypes[2].code ) {
		  
				vm.showExtForBHSC = true;
		    	$("#extForBHSC").prop("checked", true);
				/* vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.partnerName = "filled";
				vm.exportOrder.projectCode = "filled"; */
		    }
			if (vm.exportOrder.bussinessType == vm.businessTypes[3].code ) {
		
				vm.showExtAlternativeStock = true;
		    	$("#extAlternativeStock").prop("checked", true);
		    	/* vm.exportOrder.deptReceiveName = "filled";
				vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.partnerName = "filled";
				vm.exportOrder.projectCode = "filled"; */
			}
			if (vm.exportOrder.bussinessType == vm.businessTypes[4].code ) {
			     
				vm.showExtForGift  = true;
			    $("#extForGift").prop("checked", true);
			    /* vm.exportOrder.deptReceiveName = "filled";
				vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.projectCode = "filled"; */
			}
			if (vm.exportOrder.bussinessType == vm.businessTypes[5].code ) {
		    
		    	vm.showExtForBorrow = true;
		    	$("#extForBorrow").prop("checked", true);
		    	/* vm.exportOrder.deptReceiveName = "filled";
				vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.projectCode = "filled"; */
		    }
			if (vm.exportOrder.bussinessType == vm.businessTypes[6].code ) {
		     
				vm.showExtForPay = true;
		    	$("#extForPay").prop("checked", true);
		    	/* vm.exportOrder.deptReceiveName = "filled";
				vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.projectCode = "filled"; */
		    }
			if (vm.exportOrder.bussinessType == vm.businessTypes[7].code ) {
				vm.showExtForSale = true;
		    	$("#extForSale").prop("checked", true);
				/* vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.partnerName = "filled";
				vm.exportOrder.projectCode = "filled"; */
		    }
			if (vm.exportOrder.bussinessType == vm.businessTypes[8].code ) {
		        vm.showExtForProject = true;
				$("#extForProject").prop("checked", true);
				/* vm.exportOrder.deptReceiveName = "filled";
				vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.partnerName = "filled"; */
		    }
			if (vm.exportOrder.bussinessType == vm.businessTypes[9].code ) {
				vm.showExtForSell = true;
				$("#extForSell").prop("checked", true);
				/* vm.exportOrder.constrCode = "filled";
				vm.exportOrder.reasonId = vm.exportOrder.dataReasonDelete[0].reasonId;
				vm.exportOrder.ieStockName = "filled";
				vm.exportOrder.partnerName = "filled";
				vm.exportOrder.projectCode = "filled"; */
		    }
		}	


		function checkDups(goodsItem){
			var isExisted = false;
			if(vm.fixedTableShow){
					var goodsGrid = $('#goodsListGrid3').data("kendoGrid");
					}else{
					var goodsGrid = $('#creExReqMaGrid').data("kendoGrid");
					}
					var dataGrid = goodsGrid.dataSource.data().length;
					if(dataGrid!=0){
			//var goodsGrid = $("#creExReqMaGrid").data("kendoGrid");;
            goodsGrid.table.find("tr").each(function(idx, item) {
    					var row = $(item);
    					var dataItem = goodsGrid.dataItem(item);
    					if(goodsItem.goodsCode == dataItem.goodsCode && goodsItem.goodsState == dataItem.goodsState ){
    						isExisted = true;
    					}
    			});
				}
            return isExisted;
		}
		
		function checkExistGoodPattern(goodsItem){
			var isExisted = false;
			var goodsGrid = vm.creExReqMaGrid;
            goodsGrid.table.find("tr").each(function(idx, item) {
    					var row = $(item);
    					var dataItem = goodsGrid.dataItem(item);
    						if(goodsItem.orderPatternGoodsId == dataItem.orderPatternGoodsId ){
        						isExisted = true;
    					}
    			});
            return isExisted;
		}
		
		$("#goods").kendoAutoComplete({
			dataTextField: "name",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.good.goodsId = dataItem.goodsId; 
                vm.good.goodsCode = dataItem.code;
                vm.good.goodsName = dataItem.name;
                vm.good.goodsState = 1;
                vm.good.goodsUnitName = dataItem.goodsUnitName;
                vm.good.goodsStateName = dataItem.goodsStateName;
                vm.good.orderGoodsId = null;
                vm.good.manufacturerName = dataItem.manufacturerName;
                vm.good.manufacturerId = dataItem.manufacturerId;
                vm.good.producingCountryName= dataItem.producingCountryName;
                vm.good.producingCountryId = dataItem.producingCountryId;
                vm.good.isSerial = parseInt(dataItem.isSerial);

				if(vm.editTableShow){
                var grid = $("#creExReqMaGrid").data("kendoGrid");
                var check = checkDups(vm.good);
                if(check){
                	toastr.warning("Mặt hàng đã tồn tại trong lưới!");
                }else{
				vm.lstGood = [];
                	vm.lstGood.push(vm.good)
                	grid.dataSource.insert(vm.lstGood[0]);
                }        
				}else if(vm.fixedTableShow){
                var grid = $("#goodsListGrid3").data("kendoGrid");
                var check = checkDups( vm.good);
                if(check){
                	toastr.warning("Mặt hàng đã tồn tại trong lưới!");
                }else{
				vm.lstGood = [];
                	vm.lstGood.push(vm.good)
                	grid.dataSource.insert(vm.lstGood[0]);
                }
				}
                
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("goodsRsServiceRest/" + 'getGoodsForOrder').post({pageSize:20, page:1, keySearch:$("#goods").val()}).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã hàng</p>' +
            '<p class="col-md-6 text-header-auto">Tên hàng</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                	vm.good.goodsId = null; // thành id
	                vm.good.code = null;
	                vm.good.name = null;
                }
            },
            close: function(e) {
                // handle the event
            	document.getElementById("goods").value = "";
              }
		});
		
		
		initFormData();
		function initFormData() {
			fillDataTable([]);
			erroTable([]);
			//fillDetailTable([]);
			fillListGoods([]);
			fillListPatternGoods([]);
			getListGood();
			fillDataTableGoodsList(data);
		}
		function getListGood()
		{
			Restangular.all("goodsRsServiceRest/" + 'goods').customGET().then(function(response){
				var list = response.data;
            	for(var i =0;i<list.length;i++)
            		{
            		 if(list[i].status==1)
            			 {
            			 vm.getlistGood.push(list[i]);
            			 }
            		}
            }).catch(function (err) {
                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
            });
		}
		
		function fillListGoods(data)
		{
			vm.listGoodGridOptions = kendoConfig.getGridOptions({
				sortable: true,
                columnMenu: false,		 
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
						for(var i = 0; i<response.data.length;i++){
							response.data[i].isSerial = parseInt(response.data[i].goodsIsSerial);
		            	}
								return response.data;
							},
		                },
					transport: {
						read: {
		                  // Thuc hien viec goi service
						  url: Constant.BASE_SERVICE_URL +"orderPatternGoodServiceRest/getPatternGoodsByOrderPatternId", 
						  contentType:"application/json; charset=utf-8",
						  type: "POST"
						 
						},					
						parameterMap: function (options, type) {
							
							    vm.orderPatternGood.page = options.page;
								vm.orderPatternGood.pageSize = options.pageSize;
								return JSON.stringify(vm.orderPatternGood);

						}
					},					 
					pageSize: 10
				},
				// dataSource: data,
				scrollable:false,
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
						title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='caller.chkSelectAll();' ng-model='caller.chkAll' />",
						template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.onCheck($event)'/>",
				        width: 35,
				        headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"}
					}
					,{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#listGoods").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
					field: 'goodsCode',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 300,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Số lượng",
			        field: 'amount',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},]
			});
		}
		vm.orderPatternUser = {};
		function fillListPatternGoods(data)
		{
			vm.patternGoodGridOptions = kendoConfig.getGridOptions({
				
				change: function() {
			        //var gridDetails = $("#listGoods").data("kendoGrid");
			        var dataItem = this.dataItem(this.select());
					vm.orderPatternGood.orderPatternId = dataItem.orderPatternId;
					var grid = $("#listGoods").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}   
			        //gridDetails.dataSource.filter({ field: "orderPatternId", value: dataItem.orderPatternId, operator: "eq" });
				  		 // document.getElementById('patterName').value = dataItem.name;
				  		// document.getElementById('personCreate').value = dataItem.createdUserName;
				  		//document.getElementById('notes').innerHTML = dataItem.description;
vm.orderPatternUser.name = dataItem.name;
vm.orderPatternUser.createdUserName = dataItem.createdUserName;
vm.orderPatternUser.description = dataItem.description;
			    },
			    	dataBound: function(e) {
							    					
			    								   var grid = $("#listGoods").data("kendoGrid");
				    							   var tr = $("#listDetailGoods").data("kendoGrid").select("tr:eq(0)");
				    							   var dataItem = $("#listDetailGoods").data("kendoGrid").dataItem(tr);
				    							    /* grid.dataSource.filter({ field: "orderPatternId", value: dataItem.orderPatternId, operator: "eq" });  
				    							    document.getElementById('patterName').value = dataItem.name;
				    						  		 document.getElementById('personCreate').value = dataItem.createdUserName;
				    						  		document.getElementById('notes').innerHTML = dataItem.description; */
			    	},
				autoBind: true,
				resizable: true,			 
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								vm.totalSample = response.total;
								return response.total;
							},
							data: function (response) {
								return response.data;
							},
		                },
					transport: {
						read: {
						  url: Constant.BASE_SERVICE_URL +"orderPatternServiceRest/doSearch", 
						  contentType:"application/json; charset=utf-8",
						  type: "POST"
						 
						},					
						parameterMap: function (options, type) {
							
							    vm.orderPattern.page = options.page;
								vm.orderPattern.pageSize = options.pageSize;
								return JSON.stringify(vm.orderPattern);
						}
					},					 
					pageSize: 10
				},
				columnMenu: false,
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
				columns: [{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#listDetailGoods").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Tên mẫu",
					field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		vm.showPatternGoods = showPatternGoods;
		function showPatternGoods()
		{
			 var teamplateUrl="wms/exportRequestManage/listPatternGood.html";
			    var title="Danh sách mẫu hàng hóa trong yêu cầu xuất kho";
			    var windowId="PATTERN";
				vm.chkAll = false;
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'90%','75%');
		}
		
		function fillDetailTable(data){
					vm.detailGridOptions = kendoConfig.getGridOptions({
						autobind:true,
						sortable: true,
		                 columnMenu: false,
						 dataSource: data,
							noRecords: true,
							messages: {
								noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
							},
							pageable: {
								refresh: false,
								 pageSizes: [10, 15, 20, 25],
								 pageSize:10,
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
						        template: dataItem => $("#viewDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        width: "10%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}
							, {
								title: "Serial",
								field: 'serial',
						        width: "20%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}, {
								title: "Mã hợp đồng",
						        field: 'contractCode',
						        width: "20%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}, {
								title: "Part number",
						        field: 'partNumber',
						        width: "10%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							},  {
								title: "Hãng sản xuất",
								 field: 'manufacturerName',
						        width: "15%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							},  {
								title: "Nước sản xuất",
								 field: 'producingCountryName',
						        width: "15%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							},  {
								title: "Vị trí",
								 field: 'cellCode',
						        width: "10%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}]
					});
				}	
		function erroTable(data) {
			vm.errGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
				noRecords: true,
				columnMenu: false,
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
				columns: [{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#detailErrGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Dòng lỗi",
					field: 'lineError',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},  {
					title: "Cột lỗi ",
					field: 'columnError',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "Nội dung lỗi ",
					field: 'detailError',
			        width: "55%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				
				]
			});
		}
		
		function fillDataTable(data) {
			
			var dataSource = new kendo.data.DataSource({
                pageSize: 10,
                data: data,
                autoSync: true,    
                schema: {
                    model: {
                       // id: "goodsCode",
                    	fields: {
                    		stt: {editable: false},
                    		goodsCode: {editable: false},
                    		goodsName: { editable: false },
                    		amount:  { type: "number", format: "{0:c}" },
                    		goodsUnitName: {editable: false},
                    		detail: {editable: false},
                    		action: {editable: false},
                    	}
                    }
                }
            });
			
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,		
				dataSource:dataSource,
			//	dataSource: data,
				scrollable:false,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					 pageSize : 10,
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columnMenu: false,
				columns: [
					{
						title: "TT",
						field:"stt",
				        template: dataItem => $("#creExReqMaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
				        width: "9%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
						//template:  "# disable #",

					}
					,  {
						title: "Mã hàng",
						field: 'goodsCode',
				        width: "20%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Tên hàng",
				        field: 'goodsName',
				        width: "20%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Đơn vị tính",
				        field: 'goodsUnitName',
				        width: "10%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Số lượng",
						 field: 'amount',
				        width: "10%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:right;"
						},
					},  {
						title: "Tình trạng",
						 field: 'goodsState',
						 editor: categoryDropDownEditor,
				        template :  "# if(goodsState == 1){ #" + "#= 'Bình thường' #" + "# } " + "else if (goodsState == 2) { # " + "#= 'Hỏng' #"+ "#} #",
				        width: "10%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Chi tiết hàng hóa",
						 field: 'detail',
						   template: "# if(isSerial == 1){ #" + "<a  ng-click=vm.seeDetail(dataItem)>#= 'Chi tiết' #</a>" + "# } " + "else if (isSerial == 0) { # " + "#= '' #"+ "#} #",
							//'<a class="#=id#" href="javascript:void(0);" ng-click=vm.seeDetail(dataItem)>Chi tiết</a>',	
						 width: "12%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					},{
						title: "Thao tác",
				        template: 
						'<div class="text-center #=goodsCode#"">'+
							'<a type="button" class="#=goodsCode# icon_table" ng-hide="vm.hideDeleteRow" uib-tooltip="Xóa" translate ng-click="vm.remove(dataItem)">'+
								'<i class="fa fa-trash" aria-hidden="true"></i>'+
							'</a>'+
						'</div>',
				        width: "9%",
				        field:"action"
					}
					,]
					
			});
		}
		vm.status = [
			 {id: 1, name: 'Bình thường'}
			,{id: 2, name: 'Hỏng'}
		]
		function categoryDropDownEditor(container, options) {
            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: true,
                    dataTextField: "name",
                    dataValueField: "id",
                    dataSource: vm.status
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
		        // Checkbox has been checked
		    	vm.showExtForDepartment = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[0].code;
		    	vm.exportOrder.deptReceiveName = "";
		    }
			if (document.getElementById("extForConstruct").checked == true) {
		        // Checkbox has been checked
		    	vm.showExtForConstruct = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[1].code;
		    	vm.exportOrder.deptReceiveName = "";
		    	vm.exportOrder.reasonId = "";
		    	vm.exportOrder.constrCode = "";
		    }
			if (document.getElementById("extForBHSC").checked == true) {
		        // Checkbox has been checked
		    	vm.showExtForBHSC = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[2].code;
		    	vm.exportOrder.deptReceiveName = "";
		    }
			if (document.getElementById("extAlternativeStock").checked == true) {
		        // Checkbox has been checked
		    	vm.showExtAlternativeStock = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[3].code;
		    	vm.exportOrder.ieStockName = "";
		    }
			if (document.getElementById("extForGift").checked == true) {
			        // Checkbox has been checked
			    vm.showExtForGift = true;
			    vm.exportOrder.bussinessType = vm.businessTypes[4].code;
			    vm.exportOrder.partnerName ="";
			}
			if (document.getElementById("extForBorrow").checked == true) {
		        // Checkbox has been checked
		    	vm.showExtForBorrow = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[5].code;
		    	vm.exportOrder.partnerName ="";
		    }
			if (document.getElementById("extForPay").checked == true) {
		        // Checkbox has been checked
		    	vm.showExtForPay = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[6].code;
		    	vm.exportOrder.partnerName ="";
		    }
			if (document.getElementById("extForSale").checked == true) {
		        // Checkbox has been checked
		    	vm.showExtForSale = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[7].code;
		    	vm.exportOrder.deptReceiveName ="";
		    }
			if (document.getElementById("extForProject").checked == true) {
		    	vm.showExtForProject = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[8].code;
		    	vm.exportOrder.projectCode = "";
		    }
			if (document.getElementById("extForSell").checked == true) {
		    	vm.showExtForSell = true;
		    	vm.exportOrder.bussinessType = vm.businessTypes[9].code;
		    	vm.exportOrder.deptReceiveName = "";
		    }
			
			// if(vm.exportOrder.orderId != null){
				// checkBoxAutoLoad(vm.exportOrder.orderId);
				 
		  	
			// }
			
		}
		
		function refreshGrid(d) {
			var grid = $("#creExReqMaGrid").data("kendoGrid");
			var gridDetail = vm.viewDetailGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			} 
			if(gridDetail){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.remove = remove;
		function remove(dataItem)
		{
				if(vm.fixedTableShow){
					var grid = $('#goodsListGrid3').data("kendoGrid").dataSource.data();
					}else{
					var grid = $('#creExReqMaGrid').data("kendoGrid").dataSource.data();
				}
			 //var grid = $('#creExReqMaGrid').data("kendoGrid").dataSource.data();
			//confirm('Xác nhận xóa', function(){
				if(dataItem.orderGoodsId==null)
					{
					     for(var i=0;i<grid.length;i++)
					    	 {
					    	   if(grid[i].goodsCode==dataItem.goodsCode)
					    		   {
					    		   grid.splice(i,1);
					    		   toastr.success("Xóa thành công!");
									return;
					    		   }
					    	 }
					}
				else
					{
					createExportRequestManageService.remove(dataItem.orderGoodsId).then(
							function(d) {
								toastr.success("Xóa thành công!");
								doSearch();
							}, function(errResponse) {
								toastr.error("Lỗi không xóa được!");
							});
				} 
					//})
				
		}
		
		vm.selectPatternGood = selectPatternGood;
		function selectPatternGood()
		{
			var selectedItem = [];
			var lstGood = [];
			var dataItem=[];
			if(vm.fixedTableShow){
					var gridgoods = $('#goodsListGrid3').data("kendoGrid");
					}else{
					var gridgoods = $('#creExReqMaGrid').data("kendoGrid");
					}
			// var gridgoods = $("#creExReqMaGrid").data("kendoGrid");
			var grid = vm.listGoodsGrid;
			var rowList = grid.lockedTable == undefined ? grid.table.find("tr")
					: grid.lockedTable.find("tr");
			rowList.each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					 dataItem = grid.dataItem(item);
					 dataItem.orderGoodsId  = null;
					 dataItem.goodsState = '1';
					 lstGood.push(dataItem);
					 
				}
			});
			if(lstGood.length==0)
				{
				toastr.warning("Chưa có bản ghi nào được chọn");
				}
			else
				{
				var checkInsert = false;
				for(var i=0;i<lstGood.length;i++)
					{
					// var check = checkExistGoodPattern(lstGood[i]);
					var check = checkDups(lstGood[i]);
		                if(!check){
		                	checkInsert = true;
		                	gridgoods.dataSource.insert(lstGood[i]);
		                }  
					}
				if(checkInsert)
					{
					toastr.success("Thao tác thành công!");
					CommonService.closePopup1();
					}
				else
					{
					toastr.warning("Hàng hóa đã tồn tại trên lưới");
					}
				
				}
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			var grid =vm.creExReqMaGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		vm.onCheck = function(item){
			if(document.getElementById("chkSelectAll").checked == true){
				document.getElementById("chkSelectAll").checked = false;
			}
		}
		
		vm.chkSelectAll = function(item) {
	    	var grid = vm.listGoodsGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    }
		
		//vm.ordergood = {};
		vm.seeDetail = function(dataItem)
		{
			    var teamplateUrl="wms/exportRequestManage/viewDetailOrderGood.html";
			    var title="Thông tin chi tiết hàng hóa";
			    var windowId="DETAIL";
				vm.detail = dataItem;
				vm.detail.contractCode = vm.exportOrder.contractCode;
			  //  vm.ordergood = dataItem;
				//vm.ordergood.stockName = vm.exportOrder.stockName;
				vm.ordergood.stockCode = vm.exportOrder.stockId;
				vm.ordergood.goodsCode = vm.detail.goodsCode;
				vm.ordergood.goodsName = vm.detail.goodsName;
			    vm.ordergoodId = dataItem.goodsId;
				vm.listData = [];
				vm.listData.push(vm.detail);
				fillDetailTable(vm.listData);
			    CommonService.populatePopupCreate(teamplateUrl,title,vm.exportOrder,vm,windowId,false,'60%','40%');
		}
	
	/*vm.seeDetail = function(dataItem){
	var teamplateUrl="wms/exportRequestManage/viewDetailOrderGood.html";
			    var title="Thông tin chi tiết hàng hóa";
			    var windowId="DETAIL";
				vm.detail = dataItem;
				vm.ordergood.goodsCode = vm.detail.goodsCode;
				vm.ordergood.goodsName = vm.detail.goodsName;
			    vm.orderGoodsSearch.id = dataItem.goodsId;
				vm.goodDetail = [];
				createExportRequestManageService.getGoodsById(vm.orderGoodsSearch).then(function(d) {
				 vm.goodDetail = d.plain();
				 vm.goodDetail.contractCode = vm.detail.contractCode;
				 fillDetailTable(vm.goodDetail);
			    CommonService.populatePopupCreate(teamplateUrl,title,vm.goodDetail,vm,windowId,false,'60%','40%');	
				}, function() {
					console.error('Error');
				});
	}*/
		vm.exportExcelErr =function() {
	        //$("#detailErrGrid").getKendoGrid().saveAsExcel();
				createExportRequestManageService.downloadErrorExcel(vm.objectErr).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
	    }
		
		function getFolder() {
			Restangular.one(RestEndpoint.ORDER_GOOD_UPLOAD_FOLDER_URL+"/folder").get().then(function(data) {
				vm.folder = data.folder;
			  });
		}
		getFolder();
		
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
	        var formData = new FormData();
			formData.append('multipartFile', $('#file')[0].files[0]); 
	     return   $.ajax({
	            url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.ORDER_GOOD_SERVICE_URL +"/importGoods?folder="+ vm.folder,
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success:function(data) {
	            	if(data[data.length - 1].lstErrorGoods.length > 0){
	            		vm.lstErrImport = data[data.length - 1].lstErrorGoods;
	            		vm.objectErr = data[data.length - 1];
	            		var teamplateUrl="wms/exportRequestManage/errList.html";
	       			 var title="Kết quả Import";
	       			 var windowId="ERR_IMPORT";
	       			
	       			 CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'50%','50%');
	       			erroTable(vm.lstErrImport);
	            	}else 
	            	{
					if(vm.fixedTableShow){
					vm.dataRemain = $("#goodsListGrid3").data().kendoGrid.dataSource.view();
					var grid = $('#goodsListGrid3').data("kendoGrid");
					}else{
					vm.dataRemain = $("#creExReqMaGrid").data().kendoGrid.dataSource.view();
					var grid = $('#creExReqMaGrid').data("kendoGrid");
					}
	            		data.splice(data.length - 1, 1);
	            		//vm.dataRemain = $("#creExReqMaGrid").data().kendoGrid.dataSource.view();
	            		vm.countRemain = vm.dataRemain.length;
		            	//var grid = $("#creExReqMaGrid").data("kendoGrid");
		            	grid.dataSource.data([]);
		            	for(var i = 0; i<data.length;i++){
							data[i].isSerial = parseInt(data[i].goodsIsSerial);
		            		data[i].id = i+1;
		            		
		            		data[i].goodsCode = data[i].goodsCode;
		            		data[i].goodsName = data[i].goodsName;
		            		if(data[i].goodsState == "1"){
		            			data[i].goodsStateName = "Bình thường";
		            		}else if(data[i].goodsState == "2"){
		            			data[i].goodsStateName = "Hỏng";
		            		}
		            		
		            			grid.dataSource.add(data[i]);
		            		
		            	}
		            	for(var k = 0; k<data.length;k++){
        					if(data[k].used== 0){
        						grid.dataSource.add(data[k]);
        					}
        				}
        					            		toastr.success("Import thành công!");

	            	}
	                
	            }
	        });
	      
        
        }
	        
	       function fillDataTableGoodsList(data) {
					var dataSource = new kendo.data.DataSource({
                pageSize: 10,
                data: data,
                autoSync: true,    
                schema: {
						 model: {
		                       // id: "goodsCode",
		                    	fields: {
		                    		stt: {editable: false},goodsCode: {editable: false},
		                    		goodsUnitName:{editable: false},
		                    		amount:  { type: "number", format: "{0:c}" },
		                    		goodsName:{editable: false},
									detail:{editable: false},
		                    		action:{editable: false}
		                    	}
						 }
                }
            });
			vm.goodsListGrid3Options = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu: false,
				scrollable:false,
				dataSource : dataSource,
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
					field:"stt",
			        template: dataItem => $("#goodsListGrid3").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: "9%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'amount',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},  {
					title: "Tình trạng",
					 field: 'goodsState',
					 editor: categoryDropDownEditor,
				     template :  "# if(goodsState == 1){ #" + "#= 'Bình thường' #" + "# } " + "else if (goodsState == 2) { # " + "#= 'Hỏng' #"+ "#} #", 
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Chi tiết hàng hóa",
					field: 'detail',
					template: "# if(isSerial == 1){ #" + "<a  ng-click=vm.seeDetail(dataItem)>#= 'Chi tiết' #</a>" + "# } " + "else if (isSerial == 0) { # " + "#= '' #"+ "#} #",
							   
							//'<a class="#=id#" href="javascript:void(0);" ng-click=caller.seeDetail(dataItem)>Chi tiết</a>',	
			        width: "12%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title: "Thao tác",
					field:"action",
			        template: 
						'<div class="text-center #=goodsCode#"">'+
							'<a type="button" class="#=goodsCode# icon_table" ng-hide="vm.hideDeleteRow" uib-tooltip="Xóa" translate ng-click="vm.remove(dataItem)">'+
								'<i class="fa fa-trash" aria-hidden="true"></i>'+
							'</a>'+
						'</div>',
			        width: "9%",
			        field:"action"
				}]
			});
		}
	       /* function fillDataTableGoodsList(data) { 
				vm.goodsListGrid3Options = kendoConfig.getGridOptions({
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
								url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForExportOrder",
								contentType: "application/json; charset=utf-8",
								type: "POST"
							},					
							parameterMap: function (options, type) {
	                             // vm.appParamSearch.employeeId =
									// Constant.user.srvUser.catEmployeeId;
								    vm.orderGoodsSearch.page = options.page
									vm.orderGoodsSearch.pageSize = options.pageSize
									
									return JSON.stringify(vm.orderGoodsSearch)
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
						title: "TT",
						field:"stt",
				        template: dataItem => $("#goodsListGrid3").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
						field: 'goodsCode',
				        width: 150,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Tên hàng",
				        field: 'goodsName',
				        width: 150,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Đơn vị tính",
				        field: 'goodsUnitName',
				        width: 150,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Số lượng",
						 field: 'amount',
				        width: 200,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Tình trạng",
						 field: 'goodsStateName',
				        width: 200,
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}]
				});
			} */
            /*
			 * --Begin-- Lưu thông tin trên lưới dữ liệu vào các bảng
			 * WMS_OWNER.ORDER, WMS_OWNER.ORDER_GOODS và
			 * WMS_OWNER.ORDER_GOODS_DETAIL
			 */     
	        vm.saveExportReg = saveExportReg;
	        function saveExportReg()
	        {
	        	
	        	if(vm.exportOrder.orderId == null){
	        		var isOK = false;
	        		//var dataGoodsGrid = $('#creExReqMaGrid').data("kendoGrid").dataSource.data();
					if(vm.fixedTableShow){
					var dataGoodsGrid = $('#goodsListGrid3').data("kendoGrid").dataSource.data();
					var grid = $("#goodsListGrid3").data("kendoGrid");
				}else{
					var dataGoodsGrid = $('#creExReqMaGrid').data("kendoGrid").dataSource.data();
					var grid = $("#creExReqMaGrid").data("kendoGrid");
					}
								if(dataGoodsGrid.length != 0){
					for(var i = 0; i < dataGoodsGrid.length;i++){
							if(dataGoodsGrid[i].goodsCode == null || dataGoodsGrid[i].goodsName == null 
									 || dataGoodsGrid[i].amount == null || dataGoodsGrid[i].goodsState == null )
								{
									if(dataGoodsGrid[i].goodsCode == null){
										toastr.warning("Chứa hàng hóa không có mã hàng");
										break;
									}else if(dataGoodsGrid[i].amount == null){
										toastr.warning("Trường số lượng không được để trống");
										grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4));
										break;
									}else{
										toastr.warning("Chứa hàng hóa không có tình trạng");
										break;
									} 
								}
								
								else{
									if((dataGoodsGrid[i].amount - kendo.parseInt(dataGoodsGrid[i].amount)) != 0){
										toastr.warning("Chứa hàng hóa có số lượng yêu cầu không hợp lệ");
										grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4));
										break;
									}else if(dataGoodsGrid[i].amount <= 0){
											toastr.warning("Trường số lượng phải là số nguyên dương");
											grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4));
											break;
										}else{
										if(i == dataGoodsGrid.length - 1)
												isOK = true;
									}
										
								}
									
					}
	    			if(isOK == false){
	    				if(dataGoodsGrid.length != 0) 	return;
	    			}else {
	    				vm.prepareDataforSaving();/*
						var createdDateOrder = kendo.parseDate(new Date(), "dd/MM/yyyy");
						vm.exportOrder.createdDate = createdDateOrder;*/
						//clearFilled();
						createExportRequestManageService.saveImportReq(vm.exportOrder).then(function(d){
						if(d.error){
						toastr.error(d.error);
						return;
					}
			        		toastr.success("Thêm mới thành công!");
							//vm.exportOrder = {};
							$rootScope.$broadcast("backFromCreate", vm.exportOrder);	
							//exReqManaService.setData(vm.exportOrder);							
			        		CommonService.goTo('EXPORT_STATEMENT_MANAGE');
							CommonService.closeTab('CREATE_EX_REQ_MANA');
			            }, function(errResponse) {
			            	toastr.error("Thêm mới thất bại");
						});
	    			 
	        	}
				}	else{
						            	 toastr.error("Không có hàng hóa để tạo yêu cầu");

			}
				}
				else{
				var isOK = false;
				var dataGoodsGrid = $('#goodsListGrid3').data("kendoGrid").dataSource.data();
				var grid = $("#goodsListGrid3").data("kendoGrid");
				for(var i = 0; i < dataGoodsGrid.length;i++){
	    			if(dataGoodsGrid[i].amount==null){
	    				toastr.warning("Trường số lượng không được để trống");
						isOK == false;
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4)); 
	    				break;
	    			}else if(dataGoodsGrid[i].amount<=0){
	    				toastr.warning("Trường số lượng phải là số nguyên dương");
						isOK == false;
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4)); 
	    				break;
	    			}
					else if((dataGoodsGrid[i].amount - kendo.parseInt(dataGoodsGrid[i].amount)) != 0){
	    						toastr.warning("Chứa hàng hóa có số lượng yêu cầu không hợp lệ");
								isOK == false;
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4)); 
	    						break;
	    					}else{
					isOK = true;
					}
				}
				if(isOK == false){
	    				if(dataGoodsGrid.length != 0) 	return;
	    			}else{
					//clearFilled();
					vm.prepareDataforSaving();
					vm.dataUpdate = $("#goodsListGrid3").data().kendoGrid.dataSource.view();
					vm.exportOrder.listOrderGoodsDTO = vm.dataUpdate;
					
					createExportRequestManageService.updateImportReq(vm.exportOrder).then(function(mess){
				if(mess.error){
					toastr.error(mess.error);
					return;
				}
		        		toastr.success("Cập nhật thành công!");
						vm.updateSearch = {};
						vm.updateSearch.page = vm.exportOrder.page;
						vm.updateSearch.pageSize = vm.exportOrder.pageSize;
						$rootScope.$broadcast("backUpDate", vm.updateSearch);
		        		CommonService.goTo('EXPORT_STATEMENT_MANAGE');
						CommonService.closeTab('UPDATE_EX_REQ_MANA');

		            }, function(errResponse) {
		            	toastr.error("Cập nhật thất bại");
					});
				}
				}
	        	
	        }
	      
	        
	        vm.prepareDataforSaving = function(){
				vm.exportOrder.listOrderGoodsDTO = [];
				if(vm.fixedTableShow){
					var dataGoodFromGrid = $('#goodsListGrid3').data("kendoGrid").dataSource.data();
				}else{
					var dataGoodFromGrid = $('#creExReqMaGrid').data("kendoGrid").dataSource.data();
					}
					for(var i = 0; i<dataGoodFromGrid.length;i++){
						 vm.orderGood = {};
						 vm.orderGood.listOrderGoodsDetailDTO = [];
						 var orderGoodDetail = {};
						dataGoodFromGrid[i].id=null;
						if(dataGoodFromGrid[i].goodsState==1)
							{
							dataGoodFromGrid[i].goodsStateName="Bình thường";
							}
						else
							{
								dataGoodFromGrid[i].goodsState=2;
							dataGoodFromGrid[i].goodsStateName="Hỏng";
							}
						orderGoodDetail.manufacturerName = dataGoodFromGrid[i].manufacturerName;
						orderGoodDetail.manufacturerId = dataGoodFromGrid[i].manufacturerId;
						orderGoodDetail.producingCountryName = dataGoodFromGrid[i].producingCountryName;
						orderGoodDetail.producingCountryId = dataGoodFromGrid[i].producingCountryId;
						orderGoodDetail.contractCode  = vm.exportOrder.contractCode;
						orderGoodDetail.serial = dataGoodFromGrid[i].serial;
						vm.orderGood = dataGoodFromGrid[i];
						
						vm.orderGood.listOrderGoodsDetailDTO = orderGoodDetail;
						
						vm.exportOrder.listOrderGoodsDTO.push(vm.orderGood);
					}
					
	        }
	        
	        vm.downloadImportTemplate = function(){
	        	CommonService.downloadTemplate('XK_Import_HangHoa').then(function(d) {
					data = d.plain();
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				});;
	        }

vm.close = function(){
				CommonService.closePopup1();
			}

vm.onClear = function(comboId){
		switch(comboId){
		case 'orderStock2':{
				vm.exportOrder.stockId=null;
				vm.exportOrder.stockName=null;
				 $('#' + comboId).val("");
				break;
				}
			
		case 'recCU':{
				vm.exportOrder.recieverId=null;
				vm.exportOrder.recieverName=null;
				 $('#' + comboId).val("");
				break;
				}
			}
		
		}
	}
})();
