(function() {
	'use strict';
	var controllerId = 'acceptanceOfConstructionJobsController';
	angular.module('MetronicApp').controller(controllerId,
			acceptanceOfConstructionJobsController);

	function acceptanceOfConstructionJobsController($scope, $rootScope,
			$timeout, Constant, acceptanceOfConstructionJobsService,
			gettextCatalog, kendoConfig, $kWindow, CommonService, theApproval,
			WindowService, PopupConst, Restangular, RestEndpoint, ProposalEvaluation) {
		
		// //////////////////////////initial data/////////////////////////////
		var vm = this;
		vm.showDetail = false;
		vm.viewDisable = false;
		vm.hideSave = true;
		vm.hideSaveAppro = true;
		var checkonchange = 0;
		vm.hideDel = false;
		vm.showCreate = false;
		vm.showApproval = false;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.dateTimePickerConfig1 = {
		        format: "dd/MM/yyyy",
		        parseFormats: ["yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy')#"
		    };
		vm.item = {};
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
	         if(item !== undefined){
	          vm.item = item;
	          reloadWorkItemsAcceptance();
	         }else{
	          console.error("không nhận được dữ liệu!");
	         }
	    });
		vm.mWorkItemsAcceptance = {};
		//get user login
		vm.mWorkItemsAcceptance.constructId = vm.item.constructId;
		vm.mWorkItemsAcceptance.contractId = vm.item.contractId;
		var getcatEmployeeId = Constant.user.srvUser.catEmployeeId;
		var listTable = [];
		var listDelete = [];
		var putArrayDelete = [];
		
		vm.theApproval = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path:'',
				nameFile:'',
				roleId : [],
				roleName : []
			}
		vm.mApproval = {};
		fillDataTable([]);
		fillDataTableList([]);
		vm.onChange = onChange;
		// ////////////////////////Combobox thanh phan tham gia//////////////////////////////////
		vm.role=[];
		var totalListEmployee = [], MonitorId = [], InChargeConstructId = [];
		vm.monitorId = [];
		vm.inChargeConstructId = [];
		angular.element(document).ready(function () {
		acceptanceOfConstructionJobsService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
			totalListEmployee = data.plain();
			acceptanceOfConstructionJobsService.getRoleId().then(function(data) {
//				vm.role = data;
				for (var i = 0; i < totalListEmployee.length; i++) {
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_GSTC"]){ //10
						MonitorId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_KTTC"]){ //5
						InChargeConstructId.push(totalListEmployee[i]);
					}
				}
				vm.monitorId = MonitorId;
				vm.inChargeConstructId = InChargeConstructId;
				
				if(MonitorId.length > 0) {
					vm.mWorkItemsAcceptance.monitorId = MonitorId[0].userId;
				}
				if(InChargeConstructId.length > 0) {
					vm.mWorkItemsAcceptance.inChargeConstructId = InChargeConstructId[0].userId;
				}
				
			})
			.catch(function() {
				console.error('getRoleId error');
			});
		  })
		  .catch(function() {
		    console.error('Gists error');
		});
		});
		// ////////////////////////END//////////////////////////////////
		
		
		////////////////////////////////Grid Area///////////////////////////////
		function refreshGrid(d) {
          	var grid = vm.acceptanceOfConstructionJobsGrid;
          	grid.dataSource.data(d);
          	grid.refresh();
        }
		function reloadWorkItemsAcceptance(){
			acceptanceOfConstructionJobsService.doSearchWorkItemsAcceptance(vm.item).then(function(d) {
					fillDataTable(d.plain());
					refreshGrid(d.plain());
					$('[name="gridchkselectall"]').prop( "checked", false );
                },
                function(){
                    console.error('Lỗi khi tải dữ liệu!!!');
                }
            );
        }
		reloadWorkItemsAcceptance();
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistaoc").checked == true){
				document.getElementById("checkalllistaoc").checked = false;
			}
		}
		vm.handleCheck2 = function(item){
			if(document.getElementById("checkalllistaoc1").checked == true){
				document.getElementById("checkalllistaoc1").checked = false;
			}
		}
		
		// Hien thi danh sach ra bang
		function fillDataTable(data){
			vm.gridListsOptions = kendoConfig
				.getGridOptions({
					autoBind : true,
					dataSource : data,
					change: onChange,
					noRecords : true,
					pageable: {
			            refresh: false,
			            pageSizes: [10, 15, 20, 25],
			            pageSize: 20,
			            messages: {
			                display: " {0} - {1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang",
			                empty: "Không có kết quả hiển thị"
			            }
			         },
					editable: false,
					messages : {
						noRecords : gettextCatalog
								.getString("Không có kết quả trong trang")
					},
					columns : [
							{
								title : gettextCatalog
										.getString("STT"),
								field : "STT",
								template: dataItem => $("#mainAcceptanceOfConstructionJobsGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								width : 60,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},
							{
								title : "<input type='checkbox' id='checkalllistaoc' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								template : "<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
								width : 35,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Mã phiếu"),
								field : "code",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Mã công trình"),
								field : "constrtCode",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Mã hợp đồng"),
								field : "contractCode",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Tên hợp đồng"),
								field : "contractName",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Trạng thái"),
								attributes: { style: "text-align:left;", class:"statusColumn"},
								field : "statusCa",
								template : function (data) {
									if(data.statusCa === 0)
										return "Soạn thảo";
									if(data.statusCa === 1)
										return "Trình duyệt";
									if(data.statusCa === 2)
										return "Đã duyệt";
									if(data.statusCa === 3)
										return "Từ chối duyệt";
									return '';
								},
								width : 100,
								headerAttributes: {style:"text-align:center;"}
							} ]
							
				});
		}
		// //////////////////////////////Handle event////////////////////////////////////
		function onChange() {
			var grid = vm.acceptanceOfConstructionJobsGrid;
			checkonchange = 0;
			if (grid.select().length > 0) {
				var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				vm.mWorkItemsAcceptance = dataItem;
				///////////////////////////////////////Trinh duyet//////////////////////////////////
				vm.theApproval.code= dataItem.code ;
				vm.theApproval.constructId = dataItem.constructId;
				vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
				vm.theApproval.stringEmployee = dataItem.inChargeConstructId +","+ dataItem.monitorId;
				vm.theApproval.roleName = ["Kỹ thuật nhà thầu","Giám sát thi công"];
				vm.theApproval.roleId = [Constant.ROLE_ID["employee_roleID_DT_KTTC"],Constant.ROLE_ID["employee_roleID_CDT_GSTC"]];  //5-10
				vm.theApproval.isSign = 'theApproval';
				checkonchange = checkonchange+1;
				theApproval.setItem(vm.theApproval);
				/////////////////////////////////////////////////////////////////////////////////
			}
		}
		// phe duyet
		vm.approval = function(){
			var grid = vm.acceptanceOfConstructionJobsGrid;
			// Check select
        	if (grid.select() === null || grid.select().length === 0 || vm.mWorkItemsAcceptance.workItemsAcceptanceId === null) {
        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
        		return;
        	}
			if(vm.mWorkItemsAcceptance.statusCa === 1){
				vm.showDetail = true;
				vm.showApproval = true;
				vm.viewDisable = true;
				vm.hideSaveAppro = false;
				vm.hideSave = true;
				vm.hideDel = true;
				vm.mApproval.statusCa = 1;
				fetchAllEstimatesWorkItemsList();
			} else {
				toastr.warning("Trạng thái trình duyệt mới được phê duyệt !");
			}
		}
		// trinh duyet
		vm.sendToApproval = function(){
			if(vm.mWorkItemsAcceptance.statusCa === 0){
				$('#loading').show();
				acceptanceOfConstructionJobsService.exportFileWorkItemsAcceptance(vm.mWorkItemsAcceptance).then(function(data){
	    			vm.theApproval.path = data.fileName;
	    			vm.theApproval.nameFile = 'BM-TCNT-10.pdf';
	    			theApproval.setItem(vm.theApproval);
	    			goTo('THE_APPROVAL');
	        	}).catch(function(){
                	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export, không thể trình duyệt !"));
                	return;
	        	}).finally(function(){
	        		$('#loading').hide();
	        	});
	        	
			} else{
				toastr.warning("Trạng thái soạn thảo mới được trình duyệt !");
			}
		}
		//huy trinh duyet
		vm.cancelApprovalBtn= function(){
			var grid = vm.acceptanceOfConstructionJobsGrid;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Bạn chưa chọn bản ghi để thực hiện thao tác này!");
				return;
			}
			vm.mWorkItemsAcceptance.tableName = 'WORK_ITEMS_ACCEPTANCE';
			vm.mWorkItemsAcceptance.tableId = vm.mWorkItemsAcceptance.workItemsAcceptanceId;
			vm.mWorkItemsAcceptance.tableIdField = 'WORK_ITEMS_ACCEPTANCE_ID';
			if(vm.mWorkItemsAcceptance.statusCa == 1){
				if(vm.mWorkItemsAcceptance.createdUserId != Constant.user.srvUser.userId){
					toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
					return;
				}else{
					if(confirm('Xác nhận hủy trình duyệt')){
			acceptanceOfConstructionJobsService.cancelAprroval(vm.mWorkItemsAcceptance).then(function() {
				status = true;
				$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
				reloadWorkItemsAcceptance();
				toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
			}, function(){
			toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
			return;
		});}}
			}else{
				toastr.warning("Trạng thái trình duyệt mới được hủy trình duyệt");
			}
		}
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
				toastr.error(gettextCatalog.getString("Tài khoản đăng nhập hiện tại không được phép truy cập vào chức năng này!"));
			}
		}
		
		$scope.$on("ChangeStatusApproval", function(event, statusApproval) {
			if(statusApproval){
				vm.showDetail = false;
				reloadWorkItemsAcceptance();
			}
		});
        // Add
		vm.add = function(){
			acceptanceOfConstructionJobsService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
				totalListEmployee = data.plain();
				acceptanceOfConstructionJobsService.getRoleId().then(function(data) {
					//vm.role = data;
					for (var i = 0; i < totalListEmployee.length; i++) {
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_GSTC"]){ //10
							MonitorId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_KTTC"]){ //5
							InChargeConstructId.push(totalListEmployee[i]);
						}
					}
					vm.monitorId = MonitorId;
					vm.inChargeConstructId = InChargeConstructId;
					
					if(MonitorId.length > 0) {
						vm.mWorkItemsAcceptance.monitorId = MonitorId[0].userId;
					}
					if(InChargeConstructId.length > 0) {
						vm.mWorkItemsAcceptance.inChargeConstructId = InChargeConstructId[0].userId;
					}
					
				})
				.catch(function() {
					console.error('getRoleId error');
				});
			  })
			  .catch(function() {
			    console.error('Gists error');
			});
			for (var i = 0; i < vm.monitorId.length; i++) {
				if(vm.monitorId[i].isCurrent === 1){
					vm.mWorkItemsAcceptance.monitorId = vm.monitorId[i].userId;
					break;
				} else {
					vm.mWorkItemsAcceptance.monitorId = vm.monitorId[0].userId;
					break;
				}
			}
			for (var j = 0; j < vm.inChargeConstructId.length; j++) {
				if(vm.inChargeConstructId[j].isCurrent === 1){
					vm.mWorkItemsAcceptance.inChargeConstructId = vm.inChargeConstructId[j].userId;
					break;
				} else {
					vm.mWorkItemsAcceptance.inChargeConstructId = vm.inChargeConstructId[0].userId;
					break;
				}
			}
			
			vm.showDetail = true;
			vm.viewDisable = false;
			vm.showCreate = true;
			vm.showApproval = false;
			vm.hideSave = false;
			vm.hideSaveAppro = true;
			vm.hideDel = true;
			var validatorFormAccept = $("#formIdFormAccept").kendoValidator().data("kendoValidator");
			validatorFormAccept.hideMessages();
			var validatorListAccept = $("#formIdListAccept").kendoValidator().data("kendoValidator");
			validatorListAccept.hideMessages();
			vm.mWorkItemsAcceptance = {};
			vm.mWorkItemsAcceptance.constructId = vm.item.constructId;
			vm.mWorkItemsAcceptance.conclusion = 1;
			fillDataTableList([]);
			refreshGridList([]);
		}
		// Show detail, edit
		vm.showGrid = function(){
			var grid = vm.acceptanceOfConstructionJobsGrid;
			if(vm.showCreate === false){
				// Check select
	        	if (grid.select() === null || grid.select().length === 0 || vm.mWorkItemsAcceptance.workItemsAcceptanceId === null) {
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
			} else {
				reloadWorkItemsAcceptance();
				vm.showCreate = false;
			}
			
			if (vm.showDetail === false) {
				if(vm.mWorkItemsAcceptance.statusCa === 1 || vm.mWorkItemsAcceptance.statusCa === 2){
					vm.viewDisable = true;
				} else {
					if(vm.mWorkItemsAcceptance.createdUserId === Constant.user.srvUser.userId){
						vm.viewDisable = false;
					} else{
						vm.viewDisable = true;
						//toastr.warning("Bạn không được quyền sửa bản ghi này!");
					}
				}
	        	vm.showDetail = true;
	        	vm.hideSave = false;
	        	vm.hideDel = true;
	        	var validatorFormAccept = $("#formIdFormAccept").kendoValidator().data("kendoValidator");
				validatorFormAccept.hideMessages();
				var validatorListAccept = $("#formIdListAccept").kendoValidator().data("kendoValidator");
				validatorListAccept.hideMessages();
				fetchAllEstimatesWorkItemsList();
				
			} else {
				vm.showDetail = false;
				vm.showApproval = false;
				vm.hideSave = true;
				vm.hideSaveAppro = true;
				vm.hideDel = false;
				reloadWorkItemsAcceptance();
			}
		}
		//validate time
		function verifyMyDateTime(d) {
		    var re = /^(((0[1-9]|[12]\d|3[01])[\/\.-](0[13578]|1[02])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|[12]\d|30)[\/\.-](0[13456789]|1[012])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|1\d|2[0-8])[\/\.-](02)[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((29)[\/\.-](02)[\/\.-]((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))$/g;
		    return re.test(d);
		}
		function verifyMyDate(sText) {
	        var reDate = /^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$/;
	        return reDate.test(sText);
	    }
		function validateDateTime(){
			if(!verifyMyDate($("#signDateAcc").val())){
				return false;
			}
			if(!verifyMyDateTime($("#acceptFromDateAcc").val())){
				return false;
			}
			if(!verifyMyDateTime($("#acceptToDateAcc").val())){
				return false;
			}
			return true;
		}
		// Save
		vm.save = function() {
			if(vm.validator.validate()){
			if (vm.showDetail && vm.mWorkItemsAcceptance.statusCa !== 1 && vm.mWorkItemsAcceptance.statusCa !== 2){
				
				var checkList = vm.mWorkItemsAcceptance.cvntList;
				
				vm.mWorkItemsAcceptance.cvntList = [];
				var monitorIds = document.getElementById("monitorId");
				vm.mWorkItemsAcceptance.monitorId = monitorIds.options[monitorIds.selectedIndex].value;
				var inChargeConstructIds = document.getElementById("inChargeConstructId");
				vm.mWorkItemsAcceptance.inChargeConstructId = inChargeConstructIds.options[inChargeConstructIds.selectedIndex].value;
				var data = vm.shareEstimatesWorkItemsGrid.dataSource.data();
    			var objectList =[];
    			for(var i =0; i<data.length ; i++){
    				objectList.push(data[i]);
    			}
				if(objectList.length > 0){
					if (vm.validator.validate()) {
						if(validateDateTime()){
							var arrValidFrom = vm.mWorkItemsAcceptance.acceptFromDate.split(" ");
	        				var dateFrom = arrValidFrom[0];
	        				var arrDateFrom = dateFrom.split("/");
	                     	var timeFrom= arrValidFrom[1];
	                     	var validTimeFrom = timeFrom.split(":");
	                     	var validFrom = new Date();
	                     		validFrom.setFullYear(arrDateFrom[2]);
	        			   		validFrom.setMonth((arrDateFrom[1]) - 1);
	        			   		validFrom.setDate(arrDateFrom[0]);
	                     		validFrom.setHours(validTimeFrom[0]);
	                     		validFrom.setMinutes(validTimeFrom[1]);
	                     		
	                     	var arrValidTo = vm.mWorkItemsAcceptance.acceptToDate.split(" "); 
	                     	var dateTo = arrValidTo[0];
	                     	var arrDateTo = dateTo.split("/");
	                     	var timeTo= arrValidTo[1];
	                     	var validTimeTo = timeTo.split(":");
	                     	var validTo = new Date();
	        	             	validTo.setFullYear(arrDateTo[2]);
	        	             	validTo.setMonth((arrDateTo[1]) - 1);
	        	             	validTo.setDate(arrDateTo[0]);
	                     		validTo.setHours(validTimeTo[0]);
	                     		validTo.setMinutes(validTimeTo[1]);
							if(validFrom < validTo){
								if (vm.mWorkItemsAcceptance.workItemsAcceptanceId !== undefined && vm.mWorkItemsAcceptance.workItemsAcceptanceId !== 0){
									if(vm.mWorkItemsAcceptance.createdUserId === Constant.user.srvUser.userId){
									} else{
										toastr.warning("Bạn không được quyền sửa bản ghi này!");
										return;
									}
									vm.mWorkItemsAcceptance.isActive = 1;
									//vm.mWorkItemsAcceptance.createdUserId = Constant.user.srvUser.userId;
									if(vm.mWorkItemsAcceptance.statusCa === 3){
										vm.mWorkItemsAcceptance.statusCa = 0;
									}
									acceptanceOfConstructionJobsService.updateWorkItemsAcceptance(vm.mWorkItemsAcceptance).then(
											function(){
												///////////// list///////////////////
												if(putArrayDelete.length > 0){
													var deleteID = [];
													for (var i = 0; i < putArrayDelete.length; i++) {
														deleteID.push(putArrayDelete[i].estimatesWorkItemId);
													}
													acceptanceOfConstructionJobsService.deleteWorkItemAcceptList(deleteID).then(function(){
														
											            }, function(){
											            	toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình tạo danh sách công việc!"));
										                }
													);
												}
												if(checkList.length > 0){
													var onlyInA = listTable.filter(function(current){
													    return checkList.filter(function(current_b){
													        return current_b.estimatesWorkItemId === current.estimatesWorkItemId
													    }).length === 0
													});
													vm.mWorkItemsAcceptance.cvntList = onlyInA;
												} else {
													vm.mWorkItemsAcceptance.cvntList = listTable;
												}
												acceptanceOfConstructionJobsService.addWorkItemAcceptList(vm.mWorkItemsAcceptance).then(function(){
								            		
										            }, function(){
										            	console.error('updateWorkItemsAcceptance error');
									                }
												);
												vm.showDetail = false;
												vm.showCreate = false;
												vm.hideSave = true;
												vm.hideSaveAppro = true;
												vm.hideDel = false;
												reloadWorkItemsAcceptance();
												//////////////////////////////////////
												toastr.success(gettextCatalog.getString("Lưu thay đổi thành công!"));
											}, function(errResponse) {
												if (errResponse.status === 409) {
					    		                	toastr.error(gettextCatalog.getString("Mã tồn tại"));
					    		                } else {
					    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo công việc nghiệm thu !"));
					    		                }
					                			return;
											}
									)
								} else {
									vm.mWorkItemsAcceptance.statusCa = 0;
									vm.mWorkItemsAcceptance.isActive = 1;
									vm.mWorkItemsAcceptance.createdUserId = Constant.user.srvUser.userId;
									vm.mWorkItemsAcceptance.createdDate = new Date();
									acceptanceOfConstructionJobsService.createWorkItemsAcceptance(vm.mWorkItemsAcceptance).then(
											function(result){
												//////////////////List////////////////////////////
												vm.mWorkItemsAcceptance.cvntList = listTable;
												acceptanceOfConstructionJobsService.addWorkItemAcceptList(vm.mWorkItemsAcceptance).then(function(){
								            		
										            }, function(){
										            	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi tạo danh sách công việc!"));
									                }
												);
												/////////////////////////////////////////////////////
												vm.mWorkItemsAcceptance.workItemsAcceptanceId = result;
												vm.showDetail = false;
												vm.showCreate = false;
												vm.hideSave = true;
												vm.hideSaveAppro = true;
												vm.hideDel = false;
												reloadWorkItemsAcceptance();
												toastr.success(gettextCatalog.getString("Lưu thành công!"));
											}, function() {
					    		                toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo công việc nghiệm thu !"));
					                			return;
											}
									)
								}
							} else {
								toastr.error(gettextCatalog.getString("Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc !"));
							}
						} else {
							toastr.error(gettextCatalog.getString("Bạn nhập sai định dạng ngày giờ !"));
						}
					}
				} else {
					toastr.error(gettextCatalog.getString("Bạn phải chọn ít nhất một công việc nghiệm thu!"));
				}
				
			} else {
				toastr.warning("Chỉ được lưu ở trạng thái soạn thảo hoặc từ chối duyệt !");
			}
			}
		}
		// Check box
		vm.chkSelectAll = function() {
	    	var grid = vm.acceptanceOfConstructionJobsGrid;
	    	var gridList = vm.shareEstimatesWorkItemsGrid;
	    	if(vm.showDetail === false){
	    		chkSelectAllBase(vm.chkAll, grid);
	    	} else {
	    		chkSelectAllBase(vm.chkAll, gridList);
	    	}
	    }
		// Delete
		vm.remove = function() {
			if(vm.mWorkItemsAcceptance.createdUserId === Constant.user.srvUser.userId){
				if(vm.showDetail){
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
				var grid = vm.acceptanceOfConstructionJobsGrid;
				// Check select
	        	if (grid.select() === null || grid.select().length === 0) {
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
				var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				if(dataItem.statusCa === 0 || dataItem.statusCa === 3){
					if (grid.select().length > 0 && confirm('Xác nhận xóa')) {
						acceptanceOfConstructionJobsService.updateIsActiveWorkItemsAcceptance(dataItem.workItemsAcceptanceId).then(function(){
		            		toastr.success("Xóa thành công!");
		            		if (vm.showDetail){
		            			
		            		}else{
		            		reloadWorkItemsAcceptance();
		            		}
			            }, function(errResponse) {
			            	if (errResponse.status === 302){
			            		toastr.error(gettextCatalog.getString("Bản ghi đang được sử dụng!"));
			            	}else{
			            		console.error("Có lỗi xảy ra trong quá trình xóa!");
			            	}
						});
					}
				} else {
					toastr.warning(gettextCatalog.getString("Chỉ được xóa khi ở trạng thái soạn thảo hoặc từ chối duyệt !"));
				}
			} else{
				toastr.warning("Bạn không được quyền xóa bản ghi này!");
			}
		}
		
		// delete multiple record
		vm.multiDelete = function() {
			var selectedRow = [];
			var listID = [];
			var noDel = 0;
			var noDel1 = 0;
			var grid = vm.acceptanceOfConstructionJobsGrid;
			var isShowToart = false;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem);
				}
			});
		       
            
            	for (var i = 0; i < selectedRow.length; i++) {
					if (selectedRow[i].statusCa === 0 || selectedRow[i].statusCa === 3) {
						if(selectedRow[i].createdUserId === Constant.user.srvUser.userId){
							listID.push(selectedRow[i].workItemsAcceptanceId);
							noDel++;
							noDel1++;
						}else if(noDel == 0){
							toastr.warning("Bạn không phải người tạo, bạn không có quyền xóa bản ghi đang chọn!");
							noDel++;
							noDel1++;
						}
					}else {
						if(noDel1 ==0){
							toastr.warning("Không thể xóa bản ghi đang trình duyệt hoặc duyệt");
							noDel1++;
						}						
					}
				}
            	
            	if(listID.length > 0){
            		if (selectedRow.length > 0 && confirm("Xác nhận xóa ? ")) {
            		acceptanceOfConstructionJobsService.updateIsActiveWorkItemsAcceptance(listID).then(function() {
			    		toastr.success("Đã xóa thành công");
			    		reloadWorkItemsAcceptance();
					}, function(errResponse) {
						if (errResponse.status == 302){
							toastr.error("Bản ghi đang được sử dụng");
						}else{
							toastr.error("Có lỗi xảy ra trong quá trình xóa!");
						} 			    	 
					});
				}

            } else if(selectedRow.length === 0){
                if (!isShowToart)
                    toastr.warning("Chọn bản ghi trước khi xóa");
            }
		}
		
		
		
		
		
		// luu phe duyet
        vm.approvalSave = function(){
        	vm.mApproval.employeeId = getcatEmployeeId;
        	vm.mApproval.constrCompReMapId = vm.theApproval.constrCompReMapId;
        	vm.mApproval.workItemsAcceptanceId = vm.mWorkItemsAcceptance.workItemsAcceptanceId;
        	vm.mApproval.constructId = vm.item.constructId;
        	acceptanceOfConstructionJobsService.approWorkItemsAcceptance(vm.mApproval).then(function(check){
        		if(check === 1){
        			toastr.warning("Bạn chưa đến lượt duyệt !");
        		} else if(check === 2){
        			toastr.warning("Bạn đã duyệt rồi, không cần duyệt nữa !");
        		} else if(check === 3){
        			vm.showDetail = false;
    				vm.showApproval = false;
    				vm.hideSave = true;
    				vm.hideSaveAppro = true;
    				vm.hideDel = false;
    				reloadWorkItemsAcceptance();
        			toastr.success(gettextCatalog.getString("Phê duyệt thành công !"));
        		} else if(check === 4){
        			vm.showDetail = false;
    				vm.showApproval = false;
    				vm.hideSave = true;
    				vm.hideSaveAppro = true;
    				vm.hideDel = false;
    				reloadWorkItemsAcceptance();
        			toastr.success(gettextCatalog.getString("Từ chối duyệt thành công !"));
        		} else if(check === 5){
        			toastr.warning(gettextCatalog.getString("Bạn không được phép duyệt !"));
        		}
            }, function(){
            	toastr.error(gettextCatalog.getString("Có lỗi xảy ra phê duyệt !"));
            	return;
            }
        	);
        }
        //////////////////////////////////////Export Area/////////////////////////////////////////
        vm.exportFilePDF = function(){
        	var selectedRow = [];
			var grid = vm.acceptanceOfConstructionJobsGrid;
			var dataItem = '';
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					dataItem = grid.dataItem(item);
					var dataEx = {};
					dataEx.workItemsAcceptanceId = dataItem.workItemsAcceptanceId;
					dataEx.constructId = vm.item.constructId;
					dataEx.contractId = vm.item.contractId;
					selectedRow.push(dataEx);
				}
			});
        	if(vm.showDetail === true){
        		$('#loading').show();
        		acceptanceOfConstructionJobsService.exportFileWorkItemsAcceptance(vm.mWorkItemsAcceptance).then(function(data){
        			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
            	}).catch(function(){
                	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                	return;
            	}).finally(function(){
            		$('#loading').hide();
            	});
            	
        	} else {
        		if(selectedRow.length > 1){
        			$('#loading').show();
    				acceptanceOfConstructionJobsService.exportListWorkItemsAcceptance(selectedRow).then(function(data){
            			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                	}).catch(function(){
                    	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                    	return;
                	}).finally(function(){
                		$('#loading').hide();
                	});
                	
        		} else  if(selectedRow.length === 1){
        			if(selectedRow.length === 0){
        				$('#loading').hide();
        				toastr.warning("Bạn cần chọn bản ghi để export !");
        				return;
        			}
        			$('#loading').show();
        			acceptanceOfConstructionJobsService.exportFileWorkItemsAcceptance(dataItem).then(function(data){
            			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                	}).catch(function(){
                    	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                    	return;
                	}).finally(function(){
                		$('#loading').hide();
                	});
                	
        		} else if(selectedRow.length === 0 && grid.select().length > 0){
        			$('#loading').show();
        			var tr = grid.select().closest("tr");
    				var dataItem = grid.dataItem(tr);
    				acceptanceOfConstructionJobsService.exportFileWorkItemsAcceptance(dataItem).then(function(data){
            			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                	}).catch(function(){
                    	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                    	return;
                	}).finally(function(){
                		$('#loading').hide();
                	});
        		}
        	}
        }
        vm.exportFileDOC = function(){
        	var selectedRow = [];
			var grid = vm.acceptanceOfConstructionJobsGrid;
			var dataItem = '';
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					dataItem = grid.dataItem(item);
					var dataEx = {};
					dataEx.workItemsAcceptanceId = dataItem.workItemsAcceptanceId;
					dataEx.constructId = vm.item.constructId;
					dataEx.contractId = vm.item.contractId;
					selectedRow.push(dataEx);
				}
			});
        	if(vm.showDetail === true){
        		$('#loading').show();
        		acceptanceOfConstructionJobsService.exportFileDocWorkItemsAcceptance(vm.mWorkItemsAcceptance).then(function(data){
        			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
            	}).catch(function(){
                	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                	return;
                }).finally(function(){
                	$('#loading').hide();
                });
                
        	} else {
        		$('#loading').show();
        		if(selectedRow.length > 1){
    				acceptanceOfConstructionJobsService.exportListDocWorkItemsAcceptance(selectedRow).then(function(data){
            			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                	}).catch(function(){
                    	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                    	return;
                    }).finally(function(){
                    	$('#loading').hide();
                    });
        		} else if(selectedRow.length === 1){
        			if(selectedRow.length === 0){
        				$('#loading').hide();
        				toastr.warning("Bạn cần chọn bản ghi để export !");
        				return;
        			}
        			
        			acceptanceOfConstructionJobsService.exportFileDocWorkItemsAcceptance(dataItem).then(function(data){
            			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                	}).catch(function(){
                    	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                    	return;
                    }).finally(function(){
                    	$('#loading').hide();
                    });
                    
        		} else if(selectedRow.length === 0 && grid.select().length > 0){
        			var tr = grid.select().closest("tr");
    				var dataItem = grid.dataItem(tr);
        			acceptanceOfConstructionJobsService.exportFileDocWorkItemsAcceptance(dataItem).then(function(data){
            			window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                	}).catch(function(){
                    	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                    	return;
                    }).finally(function(){
                    	$('#loading').hide();
                    });
        		}
        	}
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////List////////////////////////////////////////////////
     	
        
        function refreshGridList(d) {
        	var grid = vm.shareEstimatesWorkItemsGrid;
        	grid.dataSource.data(d);
        	grid.refresh();
        }
        
        function fetchAllEstimatesWorkItemsList(){
        	acceptanceOfConstructionJobsService.doSearchByWorkItemsAcceptance(vm.mWorkItemsAcceptance).then(function(d) {
        		vm.mWorkItemsAcceptance.cvntList = d.plain();
        		fillDataTableList(d.plain());
				refreshGridList(d.plain());
	    	}, function() {
	    		console.error('Error while fetching');
	        });
        }
        // Hien thi danh sach cong viec nghiem thu
		function fillDataTableList(data) {
		vm.gridOptions = kendoConfig
			.getGridOptions({
				autoBind : true,
				dataSource: data,
				noRecords : true,
				pageable: {
		            refresh: false,
		            pageSizes: [10, 15, 20, 25],
		            pageSize: 20,
		            messages: {
		                display: " {0} - {1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
		         },
				editable: false,
				messages : {
					noRecords : gettextCatalog
							.getString("Không có kết quả trong trang")
				},
				columns : [
						{
							title : gettextCatalog
									.getString("STT"),
							field : "rowNumber",
							template: dataItem => $("#shareEstimatesWorkItemsGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							width : 50,
							headerAttributes: {style:"text-align:center;"},
							attributes:{style:"text-align:center;"}
						},
						{
							title : "<input type='checkbox' id='checkalllistaoc1' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
							template : "<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheck2()'/>",
							width : 35,
							headerAttributes: {style:"text-align:center;"},
							attributes:{style:"text-align:center;"}
						},
						{
							title : gettextCatalog
									.getString("Mã hạng mục"),
							field : "itemsCode",
							width : 150,
							headerAttributes: {style:"text-align:center;"}
						},
						{
							title : gettextCatalog
									.getString("Tên hạng mục"),
							field : "itemName",
							width : 150,
							headerAttributes: {style:"text-align:center;"}
						},
						{
							title : gettextCatalog
									.getString("Mã đầu việc"),
							field : "workItemCode",
							width : 150,
							headerAttributes: {style:"text-align:center;"}
						},
						{
							title : gettextCatalog
									.getString("Tên đầu việc"),
							field : "workItemName",
							width : 200,
							headerAttributes: {style:"text-align:center;"}
						} ]
			});
		}
		function nonEditor(container, options) {
			container.text(options.model[options.field]);
		}
        // Hien thi tim kiem cong viec
		vm.showPopupWorkForm = function() {
			acceptanceOfConstructionJobsService.setItem(vm.item.constructId);
			acceptanceOfConstructionJobsService
			.showPopupWorkForm(vm.item.constructId)
			.then(
				function(result) {
				var templateUrl = 'views/popup/gridViewSearchWork.html';
				var title = "Danh sách công việc xây lắp công trình";
				vm.constructionGroupGridOptions = kendoConfig
						.getGridOptions({
							autoBind : true,
							dataSource : result.plain(),
							noRecords : true,
							messages : {
								noRecords : gettextCatalog
										.getString("Không có kết quả trong trang")
							},
							columns : [
									{
										title : gettextCatalog.getString("STT"),
										field : "STT",
										template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
										width : 100,
										editor: nonEditor,
										headerAttributes: {style:"text-align:center;"},
										attributes:{style:"text-align:center;"}
									},
									{
				    	    			title : "<input type='checkbox' name='gridchkselectall' ng-click='chkSelectAll();' ng-model='chkAll' />",
				    	                template : "<input type='checkbox' name='gridcheckbox' />",
										width : 35,
										editor: nonEditor,
										headerAttributes: {style:"text-align:center;"},
										attributes:{style:"text-align:center;"}
									},
									{
										title : gettextCatalog
												.getString("Mã hạng mục"),
										field : "itemsCode",
										width : 150,
										editor: nonEditor,
										headerAttributes: {style:"text-align:center;"}
									},
									{
										title : gettextCatalog
												.getString("Tên hạng mục"),
										field : "itemName",
										width : 150,
										editor: nonEditor,
										headerAttributes: {style:"text-align:center;"}
									},
									{
										title : gettextCatalog
												.getString("Mã đầu việc"),
										field : "workItemCode",
										width : 150,
										editor: nonEditor,
										headerAttributes: {style:"text-align:center;"}
									},
									{
										title : gettextCatalog
												.getString("Tên đầu việc"),
										field : "workItemName",
										width : 200,
										editor: nonEditor,
										headerAttributes: {style:"text-align:center;"}
									},
									{
										title : gettextCatalog
												.getString("Loại công việc"),
										field : "type",
										template : function (data) {
											if(data.type === 1)
												return "Công việc trong hợp đồng";
											if(data.type === 2)
												return "Công việc phát sinh ngoài hợp đồng";
										},
										width : 200,
										editor: nonEditor,
										headerAttributes: {style:"text-align:center;"}
									} ]
						});

					CommonService.populateDataToGrid(templateUrl,
							title, vm.constructionGroupGridOptions,
							vm,
							PopupConst.AcceptanceOfConstructionJobs['showPopupWorkForm'],false,true);
					},
					function() {
						console.error('Error while fetching locatorbank');
					});

		}
		//////////////////////////////////////////Function//////////////////////////////
		vm.multiDeleteList = multiDeleteList;
		function multiDeleteList() {
			var grid = vm.shareEstimatesWorkItemsGrid;
			var ck = false;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if(checkbox.is(':checked')){
					ck = true;
					var dataItem = grid.dataItem(item);
					listDelete.push(dataItem);
					grid.removeRow(row);
				}
			});
			//get intersection : vm.mWorkItemsAcceptance.cvntList and listDelete
	        for (var i = 0, len1 = vm.mWorkItemsAcceptance.cvntList.length; i < len1; i++) {
	        	for (var j = 0, len2 = listDelete.length; j < len2; j++) {
	        		if (vm.mWorkItemsAcceptance.cvntList[i].estimatesWorkItemId === listDelete[j].estimatesWorkItemId) {
	        			putArrayDelete.push(vm.mWorkItemsAcceptance.cvntList[i]);
	        			break;
	        		}
	        	}
	        }
	        //get unique array by id
	        function UniqueArraybyId(collection, keyname) {
                var output = [], 
                    keys = [];

                angular.forEach(collection, function(item) {
                    var key = item[keyname];
                    if(keys.indexOf(key) === -1) {
                        keys.push(key);
                        output.push(item);
                    }
                });
	            return output;
	        };
	        
	        putArrayDelete = UniqueArraybyId(putArrayDelete ,"estimatesWorkItemId");
			if(ck === false){
				toastr.warning("Bạn chưa chọn bản ghi nào để xóa !");
        		return;
			}
	    }
		// Save popup
        vm.onSave = function(popupId,selectedRows) {
        	if (PopupConst.AcceptanceOfConstructionJobs['showPopupWorkForm'] === popupId) {
        		//get data into table
        		var data = vm.shareEstimatesWorkItemsGrid.dataSource.data();
    			var objectList =[];
    			for(var i =0; i<data.length ; i++){
    				objectList.push(data[i]);
    			}
    			//get data into popup
    			var listEstimatesWorkItem = [];
        		for (var i = 0; i < selectedRows.length; i++) {
					var data = selectedRows[i];
					listEstimatesWorkItem.push(data);
				}
        		var list = Array.prototype.concat.apply(objectList, listEstimatesWorkItem);
        		//Check unique listEstimatesWorkItem
        		function UniqueArraybyId(collection, keyname) {
                    var output = [], 
                        keys = [];

                    angular.forEach(collection, function(item) {
                        var key = item[keyname];
                        if(keys.indexOf(key) === -1) {
                            keys.push(key);
                            output.push(item);
                        }
                    });
		            return output;
		        };
		        
		        listTable = UniqueArraybyId(list ,"estimatesWorkItemId");
		        
        		fillDataTableList(listTable);
				refreshGridList(listTable);
        		
        	}
        }
	}
})();