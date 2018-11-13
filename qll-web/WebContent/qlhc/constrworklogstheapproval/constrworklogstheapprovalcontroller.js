(function() {'use strict';
	var controllerId = 'constrworklogstheapprovalcontroller';

	angular.module('MetronicApp').controller(controllerId, listController);

	/* @ngInject */
	function listController($scope, $rootScope, kendoConfig, gettextCatalog,
			$kWindow, $q, constrWorkLogsTheApproval, ProposalEvaluation,
			Constant, theApproval, createMemoryWork) {
		var vm = this;
		var statusBia = false;
		$rootScope.isDisabled = false;
		vm.disableAll = false;
		var creatOrUpdate;
		vm.role=[];
		vm.approDTO = {
			statusCa : '',
			employeeId : '',
			comments : '',
			constrWoLogsLabId : '',
			constrCompReMapId : ''
		};
		vm.approDTO.statusCa = 2;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
   
		
		//3/3/2017 dodt edit: xu li bat dong bo 
		function getCreateMemoryWork(){
			var deferred = $q.defer();
			$rootScope.isAppro = createMemoryWork.getIsAppro();
			vm.aMonitorIdList = createMemoryWork.getaMonitorIdList();
			vm.bConstructIdList = createMemoryWork.getbConstructIdList();
			vm.aDirectorIdList = createMemoryWork.getaDirectorIdList();
			vm.bDirectorIdList = createMemoryWork.getbDirectorIdList();
			
            deferred.resolve('done');
            return deferred.promise;
		};
		
		
		getCreateMemoryWork().then(function(result) {

			vm.constrWorkLogsLabelDTO = createMemoryWork.getConstrWorkLogsLabelDTO();
			
			if(vm.constrWorkLogsLabelDTO != undefined){
				if(vm.constrWorkLogsLabelDTO.statusCa==1||vm.constrWorkLogsLabelDTO.statusCa==2){
					vm.disableAll = true;
				}else{
					vm.disableAll = false;
				}
				
				if(vm.constrWorkLogsLabelDTO.createdUserId != Constant.user.srvUser.userId){
					vm.disableAll = true;
				}else{
					vm.disableAll = false;
				}
			}
			if (vm.constrWorkLogsLabelDTO == undefined) {
				

				
				if(vm.aMonitorIdList.length != 0 && vm.bConstructIdList.length != 0 && vm.aDirectorIdList.length != 0  && vm.bDirectorIdList.length != 0){
					vm.constrWorkLogsLabelDTO = {};
					creatOrUpdate = "creat";
					vm.constrWorkLogsLabelDTO.amonitorId = vm.aMonitorIdList[0].id;
					vm.constrWorkLogsLabelDTO.bconstructId = vm.bConstructIdList[0].id;
					vm.constrWorkLogsLabelDTO.adirectorId = vm.aDirectorIdList[0].id;
					vm.constrWorkLogsLabelDTO.bdirectorId = vm.bDirectorIdList[0].id;
				}else{
					toastr.warning("Thiếu dữ liệu đầu vào");
				}
				
			}
        });
		
		//end 3/3/2017
		
		// Common
		vm.item = {
			partnerName : '',
			contractCode : '',
			investProjectName : '',
			constrtCode : '',
			constrtName : '',
			constrType : '',
			provinceId : '',
			constrtAddress : '',
			constructId : '',
			signed_date : '',
			provinceName : '',
			supervisorName : '',
			constructorName : ''
		}
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		$scope.$on("ProposalEvaluation.detail", function(event, item) {
			if (item != undefined) {
				vm.item = item;
			} else {
				console.error("không nhận được dữ liệu!");
			}
		});

		vm.theApproval = {
			code : '',
			constructId : '',
			constrCompReMapId : '',
			stringEmployee : '',
			isSign : '',
			path : '',
			nameFile : '',
			roleId : [],
			roleName : []
		};
		constrWorkLogsTheApproval.getRoleId().then(function(data) {
			vm.role = data;
		})
		.catch(function(data, status) {
			console.error('getRoleId error', response.status, response.data);
		});
		vm.signCACreat = signCACreat;
		function signCACreat() {
			if (vm.constrWorkLogsLabelDTO == undefined || vm.constrWorkLogsLabelDTO.statusCa == undefined) {
				toastr.warning("Bìa chưa được tạo");
				return;
			}
			if (vm.constrWorkLogsLabelDTO.statusCa != 0) {
				toastr.warning("Bìa đã trình duyệt");
				return;
			}
			$('#loading').show();
			constrWorkLogsTheApproval
					.exportFilePdf(vm.constrWorkLogsLabelDTO)
					.then(
							function(data) {
								vm.theApproval.constrCompReMapId = vm.constrWorkLogsLabelDTO.constrCompReMapId;
								vm.theApproval.path = data.fileName;
								vm.theApproval.nameFile = 'BM-TCNT-04-BIA.pdf';
								vm.theApproval.constructId = vm.item.constructId;
								vm.theApproval.stringEmployee = vm.constrWorkLogsLabelDTO.bconstructId
										+ ","
										+vm.constrWorkLogsLabelDTO.amonitorId
										+ ","
										+ vm.constrWorkLogsLabelDTO.bdirectorId
										+ ","
										+ vm.constrWorkLogsLabelDTO.adirectorId;										
								vm.theApproval.isSign = 'theApproval';
								vm.theApproval.roleId = [Constant.ROLE_ID["employee_roleID_DT_KTTC"], Constant.ROLE_ID["employee_roleID_CDT_GSTC"], Constant.ROLE_ID["employee_roleID_DT_GDNT"], Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
								vm.theApproval.roleName = [
								        "Kỹ thuật thi công",										
										"Giám sát thi công",
										"Giám đốc nhà thầu thi công",
										"Thủ trưởng chủ đầu tư"
										 ];
								vm.theApproval.code = 'sdfsdfsdf';
								theApproval.setItem(vm.theApproval);
								goTo('THE_APPROVAL');
							}).catch( function(){
				        		toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				        		return;
				        	}).finally(function(){
				        		$('#loading').hide();
				        	});
		}

		vm.ChangeData = ChangeData;
		function ChangeData() {
			if (vm.constrWorkLogsLabelDTO.constrCompReMapId != null
					|| vm.constrWorkLogsLabelDTO.constrCompReMapId != undefined) {
				creatOrUpdate = "update";
			}
		}
		
		vm.save = save;
		function save() {
			if (!vm.validator.validate()){
				return;
			}
			if ($rootScope.isAppro) {
				vm.approDTO.employeeId = Constant.getUser().srvUser.catEmployeeId;
				vm.approDTO.constrWoLogsLabId = vm.constrWorkLogsLabelDTO.constrWoLogsLabId;
				vm.approDTO.constrCompReMapId = vm.constrWorkLogsLabelDTO.constrCompReMapId;
				constrWorkLogsTheApproval.appro(vm.approDTO).then(function(d) {
					var x = d;
					if (x == '1') {
						toastr.warning("Chưa đến lượt duyệt");
						return;
					}
					if (x == '2') {
						toastr.warning("Đã duyệt rồi");
						return;
					}
					if (x == '4') {
						toastr.success("Từ chối duyệt thành công");
					}
					if (x == '3') {
						toastr.success("Duyệt thành công");
					}
					if (x == '5') {
						toastr.warning("Bạn không được phép phê duyệt");
					}
					statusBia = true;
					$rootScope.$broadcast("ChangeBia", statusBia);
					statusBia = false;
				}, function() {
					toastr.error("Có sự cố xảy ra!");
				});
				return;
			} else if (creatOrUpdate == "creat") {
				vm.constrWorkLogsLabelDTO.constructId = vm.item.constructId;
				vm.constrWorkLogsLabelDTO.createdUserId = Constant.getUser().srvUser.userId;
				constrWorkLogsTheApproval.creat(vm.constrWorkLogsLabelDTO)
						.then(function(d) {
							if (d == -1) {
								toastr.warning("Bìa đã được tạo!");
								return;
							}
							vm.theApproval.constrCompReMapId = d;
							vm.constrWorkLogsLabelDTO.constrCompReMapId = d;
							vm.constrWorkLogsLabelDTO.statusCa = 0;
							statusBia = true;
							toastr.success("Tạo bìa thành công");
							$rootScope.$broadcast("ChangeBia", statusBia);
							statusBia = false;
						}, function(errResponse) {
							toastr.error("Có sự cố xảy ra!");
						});
			} else if (creatOrUpdate == "update") {
				if (vm.constrWorkLogsLabelDTO.statusCa != 0) {
					toastr
							.warning("Chỉ được sửa khi đang ở trạng thái Soạn Thảo");
					return;
				}
				constrWorkLogsTheApproval
						.updateLabel(vm.constrWorkLogsLabelDTO)
						.then(function(d) {
							if (d == -1) {
								toastr.warning("Không được phép sửa Bìa");
								return;
							}
							statusBia = true;
							toastr.success("Update thành công");
							vm.constrWorkLogsLabelDTO.statusCa = 0;
							$rootScope.$broadcast("ChangeBia", statusBia);
							statusBia = false;
						}, function() {
							toastr.error("Có sự cố xảy ra!");
						});

			}

		}

		vm.exportFilePdf = exportFilePdf;
		function exportFilePdf() {
			if (vm.constrWorkLogsLabelDTO ==  undefined) {
			toastr.warning("Bìa chưa được tạo");
			return;
		}
			$('#loading').show();
			vm.constrWorkLogsLabelDTO.constructId = vm.item.constructId;
			vm.constrWorkLogsLabelDTO.contractCode=vm.item.contractCode;
			constrWorkLogsTheApproval
					.exportFilePdf(vm.constrWorkLogsLabelDTO)
					.then(
							function(data) {
								window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?"
										+ data.fileName;
							}).catch( function(){
				        		toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				        		return;
				        	}).finally(function(){
				        		$('#loading').hide();
				        	});

		}

		vm.ExportDoc = exportDoc;
		function exportDoc() {
			if (vm.constrWorkLogsLabelDTO == undefined) {
			toastr.warning("Bìa chưa được tạo");
			return;
		}
			$('#loading').show();
			vm.constrWorkLogsLabelDTO.constructId = vm.item.constructId;
			vm.constrWorkLogsLabelDTO.contractCode=vm.item.contractCode;
			constrWorkLogsTheApproval
					.exportFileDoc(vm.constrWorkLogsLabelDTO)
					.then(
							function(data) {
								window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?"
										+ data.fileName;
							}).catch( function(){
				        		toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				        		return;
				        	}).finally(function(){
				        		$('#loading').hide();
				        	});

		}

		vm.ApproBia = approBia;
		function approBia() {
			if (vm.constrWorkLogsLabelDTO == undefined) {
				toastr.warning("Bìa chưa được tạo");
				return;
			}
			if(vm.constrWorkLogsLabelDTO.statusCa != 1) {
				toastr.warning("Chỉ trạng thái trình duyệt mới được phê duyệt");
				return;
			}
			$rootScope.isDisabled = true;
			$rootScope.isAppro = true;
		}
		//ngoccx
	      //huy trinh duyet
			vm.cancelApprovalBtn= function(){
				vm.constrWorkLogsLabelDTO.tableName = 'CONSTR_WORK_LOGS_LABEL';
				vm.constrWorkLogsLabelDTO.tableId = vm.constrWorkLogsLabelDTO.constrWoLogsLabId;
				vm.constrWorkLogsLabelDTO.tableIdField = 'CONSTR_WO_LOGS_LAB_ID';
				if(vm.constrWorkLogsLabelDTO.statusCa == 1){
					if(vm.constrWorkLogsLabelDTO.createdUserId != Constant.user.srvUser.userId){
						toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
						return;
					}else{
						if(confirm('Xác nhận hủy trình duyệt')){
							createMemoryWork.cancelAprroval(vm.constrWorkLogsLabelDTO).then(function() {
								status = true;
								$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
								statusBia = true;
								$rootScope.$broadcast("ChangeBia", statusBia);
								statusBia = false;
					toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
				}, function(){
				toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
				return;
			});}
						}
				}else{
					toastr.warning("Chỉ trình duyệt mới được hủy trình duyệt");
				}
			}
		function goTo(menuKey) {
			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel : "Tab",
					topic : "open",
					data : template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng
				// nhập hiện tại không được phép truy cập vào chức năng
				// này!"));
			}
		}

	}
})();