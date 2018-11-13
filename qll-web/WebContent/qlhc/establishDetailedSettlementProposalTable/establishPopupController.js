(function() {
	'use strict';

	var controllerId = 'establishPopupController';

	angular.module('MetronicApp').controller(controllerId, establishPopupController);

	/* @ngInject */
	function establishPopupController($scope, $rootScope, $timeout, Constant,
			kendoConfig, establishDetailedSettlementProposalService, ProposalEvaluation, gettextCatalog, $kWindow,
			CommonService, Restangular, PopupConst) {
		var vm = this;
		vm.save = save;
		vm.showPopupPersonalEvaluation = showPopupPersonalEvaluation;
		vm.onRowChange = onRowChange;
		vm.onSave = onSave;
		vm.cancel = cancel;
		vm.itemPopup = {};
		vm.popupTemp = {};
	    mapdata();
	    function mapdata(){
        	vm.itemPopup = establishDetailedSettlementProposalService.getCode();
        	vm.itemPopup.evaluateComments = "";
        }
	    
	    establishDetailedSettlementProposalService
		function showPopupPersonalEvaluation(){
			establishDetailedSettlementProposalService.showPopupForm().then(function(result){
						var templateUrl = 'qlhc/establishDetailedSettlementProposalTable/PersonalEvaluation.html';
		    			var title = 'Cá nhân thẩm định';
		    			vm.popupGridOptions = kendoConfig.getGridOptions({
		    	    		autoBind: true,
		    	    		editable: false,
		    	    		dataSource: result.plain(),
		    	    		noRecords: true,
		    	    		messages: {
		    	    			noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
		    	    		},
		    	    		columns: [{
		    	    			title: gettextCatalog.getString("Mã"),
		    	    			field: "code",
		    	    			width: 100,
		    	    		}, {
		    	    			title: gettextCatalog.getString("Tên"),
		    	    			field: "fullName",
		    	    			width: 100
		    	    		}, {
		    	    			title: gettextCatalog.getString("Email"),
		    	    			field: "email",
		    	    			width: 100
		    	    		}]
		    	    	});
		    			CommonService.populateDataToGrid(templateUrl, title, vm.popupGridOptions, vm, PopupConst.CatEmployee['PersonalEvaluation'], 'PersonalEvaluation');
					});
		}
		
	    vm.recordpopup = 0;
	    vm.gridCustom = [{
			title: "STT",
			template: function (item) {
				return ++vm.recordpopup;
			},
			width: 25,
			headerAttributes: {
				style: "text-align:center;font-weight: bold;"
			},
			attributes: {
				style: "text-align:center"
			}
		},{
			title: gettextCatalog.getString("Mã"),
			field: "code",
			width: 100,
		}, {
			title: gettextCatalog.getString("Tên"),
			field: "fullName",
			width: 100
		}, {
			title: gettextCatalog.getString("Email"),
			field: "email",
			width: 100
		}
	];
	    
	    
	    
	    
	    
	    
	    
		function onRowChange(dataItem, popupId) {
			switch (popupId){
				case 'PersonalEvaluation':
					vm.popupTemp.code = dataItem.id;
	            	vm.popupTemp.fullName = dataItem.fullName;
					break;
			}
		}
		
		function onSave(popupId) {
			switch (popupId){
				case 'PersonalEvaluation':
					vm.itemPopup.codePerSon = vm.popupTemp.code;
		        	vm.itemPopup.fullName = vm.popupTemp.fullName;
		        	break;
			}
		}
		
		function save(){
			vm.temp={};
			vm.temp.detailSettlementProposalId = vm.itemPopup.detailSettlementProposalId
			vm.temp.code = vm.itemPopup.code;
			vm.temp.sendPersonId = vm.itemPopup.sendPersonId;
			vm.temp.brepresentativeId = vm.itemPopup.brepresentativeId;
			vm.temp.createdDate = vm.itemPopup.createdDate;
			vm.temp.createdUserId = vm.itemPopup.createdUserId;
			vm.temp.approvalDate = vm.itemPopup.approvalDate;
			vm.temp.statusCa = vm.itemPopup.statusCa;
			vm.temp.evaluatePersonId = vm.itemPopup.codePerSon;
			vm.temp.evaluateComments = vm.itemPopup.evaluateComments;
			vm.temp.evaluateFinishDate = vm.itemPopup.evaluateFinishDate;
			vm.temp.evaluateStatus = vm.itemPopup.evaluateStatus;
			vm.temp.isActive = vm.itemPopup.isActive;
			vm.temp.constructId = vm.itemPopup.constructId;
			
			if(vm.temp.statusCa == 3){
					vm.dataItem.statusCa = 0;
			}
			
			establishDetailedSettlementProposalService.addAppraisalAssignment(vm.temp).then(function(result){
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				toastr.success("Lưu thành công!");
				var status = true;
				$rootScope.$broadcast("establishPopupController.status", status);
	            		}, function(errResponse){
	            			console.log(errResponse);
			                toastr.error("Xảy ra lỗi!");
			                return;
	            		}
	    		);
		 }
		
		function cancel(){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
	}
})();