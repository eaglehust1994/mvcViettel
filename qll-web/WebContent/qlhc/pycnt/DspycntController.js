(function () {
	'use strict';

	var controllerId = 'DspycntController';

	angular.module('MetronicApp').controller(controllerId, DspycntController);

	/* @ngInject */
	function DspycntController($scope, $rootScope, $timeout, Constant,
		gettextCatalog, kendoConfig, WindowService, dspycntService, pycntService,
		CommonService, $q, RestEndpoint, ProposalEvaluation, theApproval) {
		var vm = this;
		vm.hideAButton = hideAButton;
		/*vm.onChange = onChange;*/
		vm.changeSite = changeSite;
		vm.getDspycnt = getDspycnt;
		vm.chkSelectAll = chkSelectAll;
		vm.closeModalListWork = closeModalListWork;
		vm.multiDelete = remove;
		vm.showDetail = false;
		vm.showWorkItem = false;
		vm.isCreateNew = false;
		vm.hideSaveAppro = true;
		vm.save = save;
		vm.exportFile = exportFile;
		vm.objExport = {};
		vm.theSign = {};
		vm.dataophieu = true;
		vm.disableAll = false;
		vm.thutruong = [];
		vm.nguoigiao = [];
		vm.approDTOs = [
		                {statusCa:1,value:'Phê duyệt'},
		                {statusCa:2,value:'Từ chối duyệt'},
		                ];
		vm.pycntItem = {
			constructId: '',
			shd: '',
			tenhd: '',
			mact: '',
			tenct: '',
			diachict: ''
		}

		vm.validatorOptions = kendoConfig.get('validatorOptions');

/*		vm.datePickerConfig = {

				
				format : "dd/MM/yyyy HH:mm:ss",
				parseFormats : [ "yyyy-MM-dd HH:mm:ss",
						"dd/MM/yyyy HH:mm:ss",
						"yyyy/MM/dd HH:mm:ss" ],
				footer : "Currently #: kendo.toString(data,'dd-MM-yyyy HH:mm:ss')#",	

		};*/
		
		vm.datePickerConfig2 = {
		        format: "dd/MM/yyyy",
		        parseFormats: ["yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy')#"
		    };

		vm.item = {
			partnerName: '',
			contractCode: '',
			investProjectName: '',
			constrtCode: '',
			constrtName: '',
			constrType: '',
			provinceId: '',
			constructId: ''
		};

		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		
		var count;
		
		var NguoiGiao = [], NguoiNhan = [];
		vm.sender = [];
		vm.receiver = [];
		function getEmployee(object) {
			var deferred = $q.defer();
			pycntService.getEmployeeByRole(object).then(function (data) {
				vm.Employee = data.plain();
				dspycntService.getRoleId().then(function(data) {
					//vm.role = data;
					for (var i = 0; i < vm.Employee.length; i++) {
						if(vm.Employee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]){//3
							NguoiGiao.push(vm.Employee[i]);
						}
						if(vm.Employee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){//2
							NguoiNhan.push(vm.Employee[i]);
						}
					}
					/*if (vm.Employee.length > 0) {
						if(vm.Employee[i].roleid == vm.role[3]){
						
						vm.thutruong.id = vm.Employee[0].id;}
						if(vm.Employee[i].roleid == vm.role[2]){
						vm.nguoigiao.id = vm.Employee[0].id;}
					}*/
					vm.sender = NguoiGiao;
					vm.receiver = NguoiNhan;
					
					if(NguoiGiao.length>0){
						vm.thutruong.id =  NguoiGiao[0].id ;
					}
					if(NguoiNhan.length>0){
						vm.nguoigiao.id = NguoiNhan[0].id;
					}
					
					 deferred.resolve('Done');
					count = vm.Employee.length;
				});
				

			});
			return deferred.promise;
		}
		
		vm.constrObject = {
				constructId: vm.item.constructId
			}
		getEmployee({constructId: vm.item.constructId}).then(function(successResult) {
	        getDspycnt(vm.constrObject);
	      });
		

		
		
		
		
		if (vm.item == null) {
			alert("Chưa chọn bản ghi nào.");
			return;
		}

		vm.materialObj = {
			code: '',
			constrtCode: '',
			contractCode: '',
			createdDate: '',
			handoverToDate: '',
			statusName: ''
		};

		vm.dspxk = [];

		vm.pycntObj = {
			constructId: '',
			shd: '',
			tenhd: '',
			mact: '',
			tenct: '',
			diachict: ''
				
				
				
		}

		fillData(vm.item);

		function fillData(object) {
			dspycntService.getConTruction(object).then(function (data) {
				vm.pycntObj.constructId = data.constructId;
				dspycntService.setItem(data.constructId);
				vm.pycntObj.contractCode = data.contractCode;
				vm.pycntObj.contractName = data.contractName;
				vm.pycntObj.constrtCode = data.constrtCode;
				vm.pycntObj.constrtName = data.constrtName;
				vm.pycntObj.constrtAddress = data.constrtAddress;
			})
			.catch (function (data, status) {
				console.error('getConTruction error', response.status, response.data);
			})
			.finally (function () {
				console.log("finally finished getConTruction");
			});
		}



		function getDspycnt(object) {
			dspycntService.getDspycnt(object).then(function (data) {
				vm.dspxk = data.plain();
				if (data.length < 1) {
					vm.dataophieu = false;
				} else {
					vm.dataophieu = true;
					if (vm.dspxk[0].statusCa == 1) {
						vm.isPheduyet = false;
					}
				}
				
				fillDataTable(vm.dspxk);
				
			});
		}

		function toDate(dateStr) {
			var parts = dateStr.split("/");
			return new Date(parts[2], parts[1] - 1, parts[0]);
		}

		function chkSelectAll(item) {
			console.log('chkSelectAll');
			var grid = vm.acceptanceGrid;
			chkSelectAllBase(vm.chkAll, grid);
		}

		function fillDataTable(data) {
			var deferred = $q.defer();
			vm.options = kendoConfig.getGridOptions({
					autoBind: true,
					dataSource: data, // data.plain(),
					change: onChangeAcceptanceGrid,
					noRecords: true,
					editable:false,
					dataBound: function(e) {
	                    setTimeout(function () {                     
	                        var grid = vm.acceptanceGrid;
	                        if(grid != undefined ){
	                        grid.select("tr:eq(0)");
	                        }
	                      }, 50)
	                     },
					messages: {
						noRecords: gettextCatalog.getString("Không có kết quả nào")
					},
					columns: [{
							title: gettextCatalog.getString("STT"),
							field: "index",
							template: dataItem => $("#acceptanceGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							attributes: {
								style: "text-align:center;"
							},
							width: 60
						}, 
						
/*						{
							title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
							template: "<input type='checkbox' name='gridcheckbox' />",
							headerAttributes: {
								"class": "color-black",
								style: "text-align: center"
							},
							attributes: {
								style: "text-align:center;"
							},
							width: 35
						}, */
						
						{
							title: gettextCatalog.getString("Mã Phiếu"),
							field: "code",
							//editor: nonEditor,
							width: 100
						}, {
							title: gettextCatalog.getString("Mã công trình"),
							field: "constrtCode",
							//editor: nonEditor,
							width: 120
						}, {
							title: gettextCatalog.getString("Mã hợp đồng"),
							field: "contractCode",
							//editor: nonEditor,
							width: 100
						}, {
							title: gettextCatalog.getString("Tên hợp đồng"),
							field: "contractName",
							//editor: nonEditor,
							width: 120
						}, {
							title: gettextCatalog.getString("Trạng thái duyệt"),
							field: "statusCa",
							template: function (data) {
								if (data.statusCa == 0) {
									return "Soạn thảo";
								} else if (data.statusCa == 1) {
									return "Trình duyệt";
								} else if (data.statusCa == 2) {
									return "Đã duyệt";
								} else if (data.statusCa == 3) {
									return "Từ chối duyệt";
								} else {
									return data.statusCa;
								}
							},
							attributes: { style: "text-align:left;", class:"statusColumn"},
							//editable:false,
							width: 120
						}
					]
				});
			deferred.resolve('done');
			return deferred.promise;
		}

		function fillDataTableWorkItem(data) {
			var deferred = $q.defer();
			vm.optionsworkItem = kendoConfig.getGridOptions({
					autoBind: true,
					dataSource: data, // data.plain(),
					//change: onChangeWorkItemGrid,
					noRecords: true,
					messages: {
						noRecords: gettextCatalog.getString("Không có kết quả nào")
					},
					columns: [{
							title: gettextCatalog.getString("STT"),
							field: "index",
							template: dataItem => $("#workItemGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							width: 80
						}, {
							title: gettextCatalog.getString("Mã công việc"),
							field: "workItemCode",
							editor: nonEditor,
							width: 100
						}, {
							title: gettextCatalog.getString("Tên công việc"),
							field: "workItemName",
							editor: nonEditor,
							width: 120
						}, {
							title: gettextCatalog.getString("Đơn vị"),
							field: "unit",
							editor: nonEditor,
							width: 100
						}, {
							title: gettextCatalog.getString("Số lượng"),
							field: "workAmount",
							editor: nonEditor,
							width: 120
						}
					]
				});
			deferred.resolve('done');
			return deferred.promise;
		}
/*		function onChangeWorkItemGrid() {
			var gridworkItemGrid = vm.workItemGrid;
			if (gridworkItemGrid.select().length > 0) {
				var tr = gridworkItemGrid.select().closest("tr");
				var dataItem = gridworkItemGrid.dataItem(tr);
				vm.pycntObj = dataItem;
				vm.pycntObj.constructId = vm.item.constructId;
				vm.thutruong.id = vm.pycntObj.sendPersonId + "";
				vm.nguoigiao.id = vm.pycntObj.receivePersonId + "";
				vm.objExport.constAcceptanceRequestId = vm.pycntObj.constAcceptanceRequestId;
				var ThuTruongused = false;
				var NguoiGiao  = false;
				for(var i = 0;i<count;i++){
					if(vm.thutruong.id == vm.sender[i].id && !ThuTruongused){
						vm.thutruong.roleid = vm.sender[i].roleid;
						ThuTruongused = true;
					}
					if(vm.nguoigiao.id == vm.receiver[i].id && !NguoiGiao){
						vm.nguoigiao.roleid = vm.receiver[i].roleid;
						NguoiGiao = true;
					}
				}

				vm.theSign.code = vm.pycntObj.code;
				vm.theSign.constructId = vm.item.constructId;
				vm.theSign.constrCompReMapId = vm.pycntObj.constAcceptanceRequestId;
				vm.theSign.stringEmployee = vm.pycntObj.sendPersonId + "," + vm.pycntObj.receivePersonId;
				vm.theSign.isSign = 'theSignCA';

			}
		}*/
		function onChangeAcceptanceGrid() {
			var grid = vm.acceptanceGrid;
			if (grid.select().length > 0) {
				var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				vm.pycntObj = dataItem;
				vm.pycntObj.constructId = vm.item.constructId;
				vm.thutruong.id = vm.pycntObj.sendPersonId ;
				vm.nguoigiao.id = vm.pycntObj.receivePersonId;
				vm.objExport.constAcceptanceRequestId = vm.pycntObj.constAcceptanceRequestId;
				var ThuTruongused = false;
				var NguoiGiao  = false;
				for(var i = 0;i<vm.sender.length;i++){
					if(vm.thutruong.id == vm.sender[i].id && !ThuTruongused){
						vm.thutruong.roleid = vm.sender[i].roleid;
						ThuTruongused = true;
						break;
					}
				}
				for(var i = 0;i<vm.receiver.length;i++){
					if(vm.nguoigiao.id == vm.receiver[i].id && !NguoiGiao){
						vm.nguoigiao.roleid = vm.receiver[i].roleid;
						NguoiGiao = true;
						break;
					}
				}
				
					
				
				
/*				vm.thutruong.roleid = vm.pycntObj.sendPersonId + "";
				vm.nguoigiao.roleid = vm.pycntObj.receivePersonId + "";*/

				
				vm.theSign.code = vm.pycntObj.code;
				vm.theSign.constructId = vm.item.constructId;
				vm.theSign.constrCompReMapId = vm.pycntObj.constAcceptanceRequestId;
				vm.theSign.stringEmployee = vm.pycntObj.sendPersonId + "," + vm.pycntObj.receivePersonId;
				vm.theSign.isSign = 'theSignCA';
				
			}
			
		}

		function changeSite(key) {
			findConstruction30Service.goTo(key);
		}

		function hideAButton() {
			var mapHideButton = new Map();
			mapHideButton.set("Xóa nhiều", "");
			mapHideButton.set("Sửa", "");
			$('#topBarContract').find('div').each(function () {
				var nameHideButton = $(this).attr('uib-tooltip');
				if (mapHideButton.has(nameHideButton)) {
					var hideButton = $(this).closest("button");
					hideButton.addClass("ng-hide");
					if (hideButton.hasClass("border-radius-right")) {
						hideButton.prev().addClass("border-radius-right");
					} else {
						if (hideButton.hasClass("border-radius-left")) {
							hideButton.next().addClass("border-radius-left");
						}
					}
				}
			})
		}

		function multiDelete() {
			if(vm.pycntObj.createUserId != Constant.user.srvUser.userId) {
				 toastr.warning(gettextCatalog.getString("Bạn không có quyền sửa phiếu này"));
	 				return;
			 }
			var selectedRow = [];
			var grid = vm.acceptanceGrid;
			vm.acceptanceGrid.select("tr:eq(1)");
			grid.table.find("tr").each(function (idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);

				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(item);

					if (dataItem.statusCa == 1 || dataItem.statusCa == 2) {
						return;
					}

					selectedRow.push(dataItem.code);

				}
			});

			if (selectedRow.length == 0) {
				toastr.warning(gettextCatalog.getString("Chưa chọn bản ghi nào để xóa, hoặc Phiếu đã được phê duyệt"));
				return;
			}
			
			

			if (confirm('Xác nhận xóa')) {
				dspycntService.deleteConstrAcceptanceReq(selectedRow).then(function () {
					toastr.success(gettextCatalog.getString("Xóa  thành công!"));
					// reload danh sach
					refreshGrid(vm.item);
					//reloadcrevaluation();
				}, function (errResponse) {
					if (errResponse.status == 302) {
						toastr.error("Bản ghi đang được sử dụng");
					} else {
						toastr.error(message.deleteError);
					}
				});
			}
		}
		
		
		function remove(){
        	var grid = vm.acceptanceGrid;
        	// Check select
        	if (grid.select() == null || grid.select().length == 0) {
        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
        		return;
        	}
        	
        	
        
        	var tr = grid.select().closest("tr");
        	var dataItem = grid.dataItem(tr);
        	
        	if(dataItem.statusCa == 1 || dataItem.statusCa == 2)
        	{      		
        		toastr.warning(gettextCatalog.getString("Không thể xóa bản ghi trình duyệt hoặc đã duyệt"));
        		return;
        	}else{ 
        		if(dataItem.createdUserId != Constant.user.srvUser.userId){
            		toastr.warning("Bạn không phải người tạo, bạn không có quyền xóa bản ghi đang chọn!");
            		return;
        		}
        		if(vm.acceptanceGrid.select().length > 0 && confirm('Xác nhận xóa')){
                	
        			dspycntService.removeConstrAcceptanceReq(dataItem).then(function () {
        				toastr.success(gettextCatalog.getString("Xóa  thành công!"));
        				// reload danh sach
        				refreshGrid(vm.item);
        				//reloadcrevaluation();
        			}, function (errResponse) {
        				if (errResponse.status == 302) {
        					toastr.error("Bản ghi đang được sử dụng");
        				} else {
        					toastr.error(message.deleteError);
        				}
        			});
                	}
        	}
        	
        	
        }
		function refreshGrid(object) {
			vm.pycntObj.statusCa = null;
			dspycntService.getDspycnt(vm.constrObject).then(function (data) {
				vm.dspxk = data.plain();
				if (data.length < 1) {
					vm.dataophieu = false;
				} else {
					vm.dataophieu = true;
					if (vm.dspxk[0].statusCa == 1) {
						vm.isPheduyet = false;
					}
				}
				var grid = vm.acceptanceGrid;
				if (grid) {
					
					grid.dataSource.data(data.plain());
					grid.refresh();
				}
			});

		}

		// //////////// thông tin chung////////////////
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
			if (item != undefined) {
				vm.item = item;

			} else {
				console.error("không nhận được dữ liệu!");
			}
		});

		// add new
		vm.add = function add() {
			getEmployee({constructId: vm.item.constructId});
			if (!vm.dataophieu) {
				vm.isCreateNew = true;
				vm.showDetail = true;
				vm.pycntObj.constructId = vm.item.constructId;
				vm.pycntObj.acceptanceDate = '';
				vm.pycntObj.acceptancePlace = '';
				
				vm.pycntObj.signPlace = '';
				vm.pycntObj.dearGroup = '';
				
				vm.pycntObj.acceptanceDate = '';
				vm.pycntObj.signDate = '';
				
				vm.partner.partnerId = '';
				vm.pycntObj.code = null;
			} else {
				toastr.warning(gettextCatalog.getString("Phiếu yêu cầu nghiệm thu đã tạo!"));
				return;
			}

		}

		// edit
		vm.edit = function edit() {
			
			$(".k-invalid-msg").hide();
			if (vm.pycntObj.statusCa == 1) {
				//vm.isPheduyet = false;
			}

			if (vm.acceptanceGrid.select().length == 0 && vm.showDetail == false) {
				toastr.warning(gettextCatalog.getString("Chưa chọn bản ghi nào"));
				return;
			} else {
				vm.isCreateNew = false;
				vm.showPheDuyet = false;
				if(!vm.showDetail && (vm.pycntObj.statusCa ==1 || vm.pycntObj.statusCa ==2)){
					//toastr.warning(gettextCatalog.getString("Trạng thái trình duyệt hoặc đã duyệt không được sửa"));
					vm.disableAll = true;
					}else{
						vm.disableAll = false;
					}
				if(!vm.showDetail && (vm.pycntObj.createdUserId != Constant.user.srvUser.userId)){
					//toastr.warning(gettextCatalog.getString("Trạng thái trình duyệt hoặc đã duyệt không được sửa"));
					vm.disableAll = true;
					}else{
						vm.disableAll = false;
					}
				//
				vm.hideSaveAppro = true;
				vm.hideSave = false;
				//
				vm.showDetail = !vm.showDetail;
				$rootScope.$broadcast("pycntService.load", vm.pycntObj);
/*				vm.pycntObj.createdDate = vm.pycntObj.createdDate;
				vm.pycntObj.acceptancePlace = vm.pycntObj.acceptancePlace;
				vm.partner.partnerId = vm.pycntObj.partnerId;
				vm.pycntObj.code = vm.pycntObj.code;
				vm.pycntObj.signDate = vm.pycntObj.signDate;
				vm.pycntObj.signPlace = vm.pycntObj.signPlace;
				vm.pycntObj.toGroup = vm.pycntObj.toGroup;*/
			}

		}

		// undo
		vm.undo = function undo() {
			vm.showDetail = false;
			vm.isCreateNew = false;
		}

		vm.goToAdd = goToAdd;

		/ Handle action client on a menu item /
		function goToAdd(menuKey) {

			dspycntService.setItem1(null);

			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel: "Tab",
					topic: "open",
					data: template
				});
			}

		}

		vm.goTo = goTo;
		function goTo(menuKey) {

			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);
				postal.publish({
					channel: "Tab",
					topic: "open",
					data: template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng
				// nhập hiện tại không được phép truy cập vào chức năng
				// này!"));
			}

		}

		vm.goToUpdate = goToUpdate;

		/ Handle action client on a menu item /
		function goToUpdate(menuKey) {
			getDataRowSelect().then(function () {
				if (vm.acceptanceGrid.select().length > 0) {
					$rootScope.$broadcast("pycntService.detail");
				} else {
					return;
				}
			});

			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel: "Tab",
					topic: "open",
					data: template
				});
			}

		}

		function getDataRowSelect() {
			var demopromise = $q.defer();
			var grid = vm.acceptanceGrid;
			// Check select
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
				return;
			}
			var tr = grid.select().closest("tr"); // output card tr
			var dataItem = grid.dataItem(tr); // data card tr
			demopromise.resolve(dataItem);
			return demopromise.promise;
		}

		vm.showPheDuyet = false;
		vm.pheDuyet = function pheDuyet() {
			if(vm.pycntObj.statusCa == 1){
				vm.showDetail = true;
				vm.showPheDuyet = true;
				isAppro = true;
				vm.hideSaveAppro = false;
				vm.hideSave = true;
				vm.approDTO.statusCa = 1;
			} else{
				toastr.warning(gettextCatalog.getString("Trạng thái trình duyệt mới được phê duyệt"));
				return;
			}
			if (vm.pycntObj.statusCa == 1) {
				vm.isPheduyet = false;
			}

			if (vm.pycntObj.acceptanceDate == null && vm.showDetail == false) {
				toastr.warning(gettextCatalog.getString("Chưa chọn bản ghi nào"));
				return;
			} else {
				if(!vm.showDetail) {
					vm.isCreateNew = false;
					vm.showDetail = !vm.showDetail;
					$rootScope.$broadcast("pycntService.load", vm.pycntObj);

				}
				
			}
		}
		vm.approDTO = {
			statusCa: '',
			employeeId: '',
			comments: '',
			constrWorkLogsId: '',
			constrCompReMapId: ''
		}
		vm.saveAppro = function saveAppro(){
			if (isAppro == true) {
				//vm.approDTO.statusCa = vm.approDTOs.statusCa;
				vm.approDTO.employeeId = Constant.getUser().srvUser.catEmployeeId;
				vm.approDTO.constrWorkLogsId = vm.pycntObj.constAcceptanceRequestId;
				vm.approDTO.constrCompReMapId = vm.pycntObj.constrCompReMapId;
				vm.approDTO.constructId = vm.item.constructId;
				//vm.approDTO.statusCa = vm.pycntObj.statusCa;
				dspycntService.appro(vm.approDTO).then(function (d) {
					var x = d;
					if (x == '1') {
						toastr.warning("Bạn chưa đến lượt duyệt!");
						
					}
					if (x == '2') {
						toastr.warning("Đã duyệt rồi");
						
					}
					if (x == '4') {
						toastr.success("Từ chối duyệt thành công");
						refreshGrid(vm.item);
						vm.showDetail = false;
						vm.isCreateNew = false;
					}
					if (x == '3') {
						toastr.success("Duyệt thành công");
						refreshGrid(vm.item);
						vm.showDetail = false;
						vm.isCreateNew = false;
					}
					if (x == '5') {
						toastr.warning("Bạn không được phép phê duyệt");
					}

				}, function (errResponse) {
					// console.error('Error while fetching minout');
				});
				//vm.isPheduyet = false;
				return;
			}
		}
		 //ngoccx
	      //huy trinh duyet
			vm.cancelApprovalBtn= function(){
				var grid = vm.acceptanceGrid;
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
					return;
				}
				vm.pycntObj.tableName = 'CONSTR_ACCEPTANCE_REQUEST';
				vm.pycntObj.tableId = vm.pycntObj.constAcceptanceRequestId;
				vm.pycntObj.tableIdField = 'CONST_ACCEPTANCE_REQUEST_ID';
				if(vm.pycntObj.statusCa == 1){
					if(vm.pycntObj.createUserId != Constant.user.srvUser.userId){
						toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
						return;
					}else{
						if(confirm('Xác nhận hủy trình duyệt')){
							status = true;
							$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
						dspycntService.cancelAprroval(vm.pycntObj).then(function() {
						refreshGrid(vm.item);
					toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
				}, function(){
				toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
				return;
			});}}
				}else{
					toastr.warning("Chỉ trình duyệt mới được hủy trình duyệt");
				}
			}
		// add acceptance_request start
		var isAppro = false;
		function save() {
			if(!vm.isCreateNew && vm.pycntObj.createUserId != Constant.user.srvUser.userId) {
				 toastr.warning(gettextCatalog.getString("Bạn không có quyền sửa phiếu này"));
	 				return;
			 }
			if(vm.showDetail == true){
				vm.validator._inputSelector = $rootScope.formLevel2.INPUTSELECTOR;
				if (!vm.validator.validate())
					return;
//				var today = new Date();
//				var fromDate = kendo.parseDate(vm.pycntObj.acceptanceDate, "dd/MM/yyyy HH:mm:ss");
//				if (fromDate < today) {
//					toastr.warning(gettextCatalog.getString("Thời gian nghiệm thu không hợp lệ!"));
//					return;
//				}
	
				// check cong viec da hoan thanh het chua
				var object = {
					constructionId: vm.item.constructId
				};
				/*if (chanceDateToString(vm.pycntObj.acceptanceDate, "DATEMONTH_TO_M") <= chanceDateToString(vm.pycntObj.signDate, "DATEMONTH_TO_M")) {
                      toastr.warning("Ngày ký phải trước ngày nghiệm thu!");
                      return;
                    }*/
				if(vm.pycntObj.statusCa !=1 && vm.pycntObj.statusCa !=2){
				if(!compareDateAndDatetime(vm.pycntObj.signDate,vm.pycntObj.acceptanceDate)){
					 toastr.warning("Ngày ký phải trước ngày nghiệm thu!");
					 return;
				}}
					
				dspycntService.getWorkItemNotDone(object).then(function (data) {
					if (data.plain().length > 0) {
						// show model ds cong viec chua hoan thanh
						fillDataTableWorkItem(data.plain());
						vm.showWorkItem = true;
						$("#myModalPycnt").modal();
	
					} else {
						// neu ko con cong viec chua hoan thanh thi save
						vm.obj = {
							constAcceptanceRequestId: vm.pycntObj.constAcceptanceRequestId,
							constructId: vm.item.constructId,
							code: vm.pycntObj.code,
							sendPersonId: document.getElementById("thutruong").value,
							receivePersonId: document.getElementById("nguoigiao").value,
							acceptanceDate: vm.pycntObj.acceptanceDate,
							acceptancePlace: document.getElementById("tai").value,
							createdUserId: Constant.getUser().srvUser.userId,
							approvalDate: null,
							statusCa: 0,
							isActive: 1,
							/*dearGroup: null,*/
							signDate : vm.pycntObj.signDate,
							signPlace : vm.pycntObj.signPlace,
							dearGroup : vm.pycntObj.dearGroup,
							createdDate:new Date(),
						}

						if(vm.pycntObj.statusCa == null ){
							pycntService.addConstrAcceptanceRequest(vm.obj).then(function (data) {
								toastr.success(gettextCatalog.getString("Thêm mới thành công!"));
								// reload danh sach
								refreshGrid(vm.item);
								vm.showDetail = false;
								//vm.isPheduyet = false;
								vm.isCreateNew = false;
		
							});
							
						}else if( vm.pycntObj.statusCa == 0 || vm.pycntObj.statusCa == 3 ){
							pycntService.addConstrAcceptanceRequest(vm.obj).then(function (data) {
								toastr.success(gettextCatalog.getString("Cập nhật thành công!"));
								// reload danh sach
								refreshGrid(vm.item);
								vm.showDetail = false;
								//vm.isPheduyet = false;
								vm.isCreateNew = false;
		
							});
						}else{
							toastr.warning(gettextCatalog.getString("Trạng thái trình duyệt hoặc đã duyệt không được sửa"));
						}
						
	
						vm.objExport = {
							constAcceptanceRequestId: vm.obj.constAcceptanceRequestId,
						}
					}
	
				});
			}
		}
		// add acceptance_request end
		
		// export start
		function exportFile(fileType) {
			
			if ('pdf' === fileType) {
				vm.objExport.constAcceptanceRequestId = vm.pycntObj.constAcceptanceRequestId;
				vm.objExport.fileType = 'pdf';
				vm.objExport.contractId = vm.item.contractId;
				$('#loading').show();
				pycntService.exportFile(vm.objExport).then(function (data) {
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
				
			} else {
				vm.objExport.constAcceptanceRequestId = vm.pycntObj.constAcceptanceRequestId;
				vm.objExport.fileType = 'doc';
				vm.objExport.contractId = vm.item.contractId;
				$('#loading').show();
				pycntService.exportFile(vm.objExport).then(function (data) {
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
				
			}
			
			/*var selectedRow = [];
			var grid = vm.acceptanceGrid;
			vm.acceptanceGrid.select("tr:eq(1)");
			grid.table.find("tr").each(function (idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);

				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(item);

					selectedRow.push(dataItem.constAcceptanceRequestId);

				}
			});

			if (selectedRow.length > 0) {
				pycntService.exportList(selectedRow).then(function (data) {
					window.location = "/ktts-service/fileservice/downloadFileATTT?" + data.fileName;
				});
			} else {
				if (vm.objExport.constAcceptanceRequestId == null) {
					toastr.warning(gettextCatalog.getString("Chưa chọn bản ghi nào!"));
					return;
				} else {
					
				}

			}*/
		}
		// export end

		// lay danh sach thu truong start
		/*vm.roleThutruong = {
			constructId: vm.item.constructId,
			role: '2',
			type: '1'
		};
		getThuTruong(vm.roleThutruong);

		function getThuTruong(object) {
			pycntService.getEmployeeByRole(object).then(function (data) {
				

				

				
				vm.thutruong_ = _.uniq(data.plain(), 'id');
				
				if (vm.thutruong_.length > 0) {
					vm.thutruong.id = vm.thutruong_[0].id;
					vm.nguoigiao.id = vm.nguoigiao_[0].id;
				}

			});
		}
		// lay danh sach thu truong end

		// lay danh sach nguoi giao start
		vm.roleNguoigiao = {
			constructId: vm.item.constructId,
			role: '2',
			type: '1'
		};
		getNguoigiao(vm.roleNguoigiao);

		function getNguoigiao(object) {
			pycntService.getEmployeeByRole(object).then(function (data) {
				vm.nguoigiao_ = _.uniq(data.plain(), 'id');
				if (vm.nguoigiao_.length > 0) {
					vm.nguoigiao.id = vm.nguoigiao_[0].id;
					vm.nguoigiao.roleid = vm.nguoigiao_[0].roleid;
				}

			});
		}
		
		*/
		
		
		
		
		
		
		
		// lay danh sach nguoi giao end

		// lay danh sach doi tac
		vm.objPartner = {
			constructId: vm.item.constructId,
			role: '1',
			type: '2'
		};
		getPartner(vm.objPartner);

		function getPartner(object) {
			pycntService.getPartner(object).then(function (data) {
				vm.partner = data.plain();
				if (vm.partner.length > 0) {
					vm.partnerId = vm.partner[0].partnerId;					
					vm.partnerName = vm.partner[0].partnerName
				}

			});
		}
		// lay danh sach doi tac end

		// dừng các công việc chưa nghiệm thu
		vm.pauseWorkItem = function pauseWorkItem() {
			vm.listCode = [];
			var data = vm.workItemGrid.dataSource.data();
			for (var i = 0; i < data.length; i++) {
				vm.listCode.push(data[i].estimatesWorkItemId);
			}
			
			dspycntService.pauseWorkItem(vm.listCode).then(function (data) {
				vm.obj = {
					constAcceptanceRequestId: vm.pycntObj.constAcceptanceRequestId,
					constructId: vm.item.constructId,
					code: vm.pycntObj.code,
					sendPersonId: document.getElementById("thutruong").value,
					receivePersonId: document.getElementById("nguoigiao").value,
					acceptanceDate: vm.pycntObj.acceptanceDate,
					acceptancePlace: document.getElementById("tai").value,
					createdUserId: Constant.getUser().srvUser.userId,
					approvalDate: null,
					statusCa: 0,
					isActive: 1,
					/*dearGroup: null,*/
					signPlace: vm.pycntObj.signPlace,
					signDate: vm.pycntObj.signDate,
					dearGroup: vm.pycntObj.dearGroup
				}
				if(vm.pycntObj.statusCa == null || vm.pycntObj.statusCa == 0 || vm.pycntObj.statusCa == 3){
				pycntService.addConstrAcceptanceRequest(vm.obj).then(function (data) {
					toastr.success(gettextCatalog.getString("Xử lý thành công!"));
					// reload danh sach
					refreshGrid(vm.item);
					closeModalListWork();
					vm.isCreateNew = false;
					vm.showDetail = false;
					
				});}
				else{
					toastr.warning(gettextCatalog.getString("Trạng thái trình duyệt hoặc đã duyệt không được sửa"));
				}
				vm.objExport = {
					constAcceptanceRequestId: vm.obj.constAcceptanceRequestId,
				}

			});
		}

		// khong cho edit trong grid
		function nonEditor(container, options) {
			container.text(options.model[options.field]);
		}

		function closeModalListWork() {
			$('#myModalPycnt').modal('toggle');
		}
		vm.signCA = function(){
			
			if (vm.objExport != null && vm.objExport.constAcceptanceRequestId != null && vm.objExport.constAcceptanceRequestId != '') {
				var dataItemOne = vm.pycntObj;
				console.log(JSON.stringify(dataItemOne));
				if (dataItemOne.statusCa == 0) {
					$('#loading').show();
					pycntService.exportFile(vm.objExport).then(function (data) {
						console.log(JSON.stringify(data));
						var theApprovalData = {
							code: '',
							constructId: '',
							constrCompReMapId: '',
							stringEmployee: '',
							isSign: '',
							path: '',
							nameFile: '',
							// roleId:[],
							// roleName:[]

						};
						theApprovalData.code = dataItemOne.code;
						theApprovalData.constructId = dataItemOne.constructId;
						theApprovalData.constrCompReMapId = dataItemOne.constrCompReMapId;
						theApprovalData.stringEmployee = dataItemOne.sendPersonId + "," + dataItemOne.receivePersonId;
						theApprovalData.isSign = "theApproval";
						theApprovalData.roleId = [vm.thutruong.roleid,vm.nguoigiao.roleid];
						theApprovalData.roleName = ['Người gửi','Người nhận'];
						theApprovalData.path = data.fileName;
						theApprovalData.nameFile = 'BM-TCNT-09.pdf';
						
						
						theApproval.setItem(theApprovalData);
						vm.goTo('THE_APPROVAL');
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export!"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
					
				} else {
					toastr.warning("Bản ghi này đã trình duyệt rồi");
				}

			}

		}

		$scope.$on("ChangeStatusApproval", function (event, result) {
			if (result) {
				refreshGrid(vm.item);
			}
		});

		function goTo(menuKey) {
			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel: "Tab",
					topic: "open",
					data: template
				});
			}
		}
		vm.constrObject = {
				constructId: vm.item.constructId
			}
		
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
        	if(item != undefined){
        		vm.constrObject.constructId = item.constructId;
        		refreshGrid({});
				vm.isCreateNew = false;
				vm.showDetail = false;
        	}else{
        		console.error("không nhận được dữ liệu!");
        	}
        });

 /* function convert from string vietnamese data to mililsecon */
        function chanceDateToString(stringDate, mode) {
            if (stringDate != undefined && mode == 'M_TO_DATE') {
                var date = new Date(stringDate);
                return (date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear());
            }
            if (stringDate != undefined && mode == 'DATE_TO_M') {
                var arrDate = stringDate.split("/");
                var date = new Date(arrDate[2], arrDate[1] - 1, arrDate[0], 0, 0, 0, 0);
                return date.getTime();
            }
            if (stringDate != undefined && mode == 'DATEMONTH_TO_M') { // chuyển từ ngày tháng năm giờ phút giấy sang millisecon
                var arrDate = stringDate.split(" ");
                var DdMMYYYY = arrDate[0].split("/");
                var HHMmSs = arrDate[1].split(":");
                var date = new Date(DdMMYYYY[2], DdMMYYYY[1] - 1, DdMMYYYY[0], HHMmSs[0], HHMmSs[1], HHMmSs[2], 0);
                return date.getTime();
            }
            if (stringDate != undefined && mode == 'M_TO_DATEMONTH') {
                var date = new Date(stringDate);
                return (date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
            }
        }
        
        function compareDateAndDatetime(ngay,ngaygio){
        	/*var ngay1 = ngay.split(" ");
        	var ngay1cat = ngay1[0];
        	var arrDate1Split = ngay1cat.split("/");*/
        	var date = new Date();
        	date = kendo.parseDate(ngay,"dd/MM/yyyy");
        	/*date.setFullYear(arrDate1Split[2]);
        	date.setMonth(arrDate1Split[1]);
        	date.setDate(arrDate1Split[0]);*/
        	
        	/*var ngaygio1 = ngaygio.split(" "); 
         	var ngaygiocat = ngaygio1[0];
         	var arrDateSplit2 = ngaygiocat.split("/");*/
         
         	var dateTime = new Date();
        	dateTime = kendo.parseDate(ngaygio,"dd/MM/yyyy hh:mm:ss");
         	/*dateTime.setFullYear(arrDateSplit2[2]);
         	dateTime.setMonth(arrDateSplit2[1]);
         	dateTime.setDate(arrDateSplit2[0]);*/
     		if(date < dateTime)
     		{ return true;
     			}
     		else{return false;
     			}
        }

	}

})();
