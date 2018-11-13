(function() {
	'use strict';

	var controllerId = 'PycntController';

	angular.module('MetronicApp').controller(controllerId, PycntController);

	/* @ngInject */
	function PycntController($scope, $rootScope, $timeout, Constant,
			gettextCatalog, kendoConfig, WindowService, pycntService, ProposalEvaluation, 
			CommonService, $q, RestEndpoint) {
		var vm = this;
		vm.fillData = fillData;
		vm.save = save;
		vm.exportFile = exportFile;
		vm.vm.pycntObj = {};
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
	
		vm.datePickerConfig = {
		        format: "dd/MM/yyyy HH:mm:ss",
		        parseFormats: ["yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm:ss"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy HH:mm:ss')#"
		    };
		
		vm.constrObj = {
				constructId: '',
				shd : '',
				ngaykyhd : '',
				mact : '',
				tenct : '',
				diachict : '',
				tai : ''
			};
		
		vm.item = {
				code:'',
				constrtCode:'',
				contractCode:'',
				handoverFromDate:'',
				handoverToDate:'',
				statusName:'',
				aMaterialHandoverId:''
		};
		
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			alert("Chưa chọn bản ghi nào.");
			return;
		}
		
		
		vm.thutruong = [];
		vm.nguoigiao = [];
		vm.partner = [];
		vm.nguoinhan = [];
		
		vm.constrObject = {
				constructId : vm.item.constructId
				
			};
		
		fillData(vm.constrObject);
		
		function fillData(object) {
			pycntService.getConstructions(object).then(function(data) {
				vm.constrObj.constructId = data.constructId;
				vm.constrObj.shd = data.contractCode;
				vm.constrObj.ngaykyhd = data.signDate;
				vm.constrObj.mact = data.constrtCode;
				vm.constrObj.tenct = data.constrtName;
				vm.constrObj.diachict = data.constrtAddress;
				vm.constrObj.stationCode = data.stationCode;
			  })
			  .catch(function(data, status) {
			    console.error('getConstructions error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished getConstructions");
			  });
		}
		
		// lay danh sach thu truong start
		vm.roleThutruong = {
				constructId : vm.item.constructId,
				role : '2',
				type : '1'
			};
		getThuTruong(vm.roleThutruong);
		
		function getThuTruong(object) {
			pycntService.getEmployeeByRole(object).then(function(data) {
				vm.thutruong = data.plain();
				if(vm.thutruong.length > 0) {
					vm.thutruong.id = vm.thutruong[0].id;
				}
				
			  })
			  .catch(function(data, status) {
			    console.error('getEmployeeByRole error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished getEmployeeByRole");
			  });
		}
		// lay danh sach thu truong end
		
		// lay danh sach nguoi giao start
		vm.roleNguoigiao = {
				constructId : vm.item.constructId,
				role : '2',
				type : '1'
			};
		getNguoigiao(vm.roleNguoigiao);
		
		function getNguoigiao(object) {
			pycntService.getEmployeeByRole(object).then(function(data) {
				vm.nguoigiao = data.plain();
				if(vm.nguoigiao.length > 0) {
					vm.nguoigiao.id = vm.nguoigiao[0].id;
				}
				
			  })
			  .catch(function(data, status) {
			    console.error('getEmployeeByRole error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished getEmployeeByRole");
			  });
		}
		// lay danh sach nguoi giao end
		
		// lay danh sach doi tac
		vm.objPartner = {
				constructId : vm.item.constructId,
				role : '1',
				type : '2'
			};
		getPartner(vm.objPartner);
		
		function getPartner(object) {
			pycntService.getPartner(object).then(function(data) {
				vm.partner = data.plain();
				if(vm.partner.length > 0) {
					vm.partner.partnerId = vm.partner[0].partnerId;
				}
				
			  })
			  .catch(function(data, status) {
			    console.error('getPartner error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished getPartner");
			  });
		}
		// lay danh sach doi tac end
		
		
		
		// add material start
		 function save(){
			 vm.validator._inputSelector = $rootScope.formLevel2.INPUTSELECTOR;
			 if (!vm.validator.validate()) return;
			 var today = new Date();
			 var fromDate = vm.AmaterialObj.handoverFromDate;
			 if(fromDate < today) {
				 toastr.warning(gettextCatalog.getString("Thời gian nghiệm thu không hợp lệ!"));
					return;
			 }
			 
			 pycntService.addConstrAcceptanceRequest(vm.pycntObj).then(function(data) {
					if(data == "CREATED"){
						alert("Phiếu đã tạo");
					}else if(data[1] == "TRUNG") {
						alert("Đã tồn tại!");
					}
				  })
				  .catch(function(data, status) {
				    console.error('addConstrAcceptanceRequest error', response.status, response.data);
				  })
				  .finally(function() {
				    console.log("finally finished addConstrAcceptanceRequest");
				  });
				
		 }
		// add material end
		
		function onChange(){
		  	// do something when click row of table
		  	}
		
		$scope.$on("pycntService.detail", function(event, item) {
			if (item != undefined) {
				vm.item = item;
			} else {
				console.error("không nhận được dữ liệu!");
			}
		});
		
		
		function exportFile(){
			 vm.validator._inputSelector = $rootScope.formLevel2.INPUTSELECTOR;
			 if (!vm.validator.validate()) return;
			 var today = new Date();
			 var fromDate = vm.AmaterialObj.handoverFromDate;
			 if(fromDate < today) {
				 toastr.warning(gettextCatalog.getString("Thời gian nghiệm thu không hợp lệ!"));
					return;
			 }
			 
			var nguoigiao = document.getElementById("nguoigiao");
			var aperson1 = nguoigiao.options[nguoigiao.selectedIndex].text;
			var fromDate = document.getElementById("fromDate").value;
			var hstart = fromDate.substring(11, 13)+fromDate.substring(13, 16);
			var dstart = fromDate.substring(0, 2);
			var mstart = fromDate.substring(3, 5);
			var ystart = fromDate.substring(6, 10);
			
			var obj = {
					constrName: document.getElementById("tenct").value,
					code: vm.constrObj.stationCode,
					address: document.getElementById("dcct").value ,
					contractCode: document.getElementById("shd").value,
					aperson1: aperson1,
					hstart: hstart,
					dstart:dstart ,
					mstart:mstart ,
					ystart: ystart,
					place: document.getElementById("tai").value,
			};
			
			pycntService.exportFile(obj).then(function(data) {
				window.location = "/qlhc-service/fileservice/download/" + data.fileName;
			  })
			  .catch(function(data, status) {
			    console.error('exportFile error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished exportFile");
			  });
		}
		
			
	}
	
})();