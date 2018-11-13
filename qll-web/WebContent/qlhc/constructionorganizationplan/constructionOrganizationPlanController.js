(function() {
	'use strict';

	var controllerId = 'listController';

	angular.module('MetronicApp').controller(controllerId, listController);

	/* @ngInject */
	function listController($scope, $rootScope,
			kendoConfig, $kWindow, $q,
			ProposalEvaluation, WorkOrganizationPlan,listWorkOrganizationPlan,CommonService) {
		var vm = this;
		vm.monitoring=[];
		vm.monitoring1=[];
		// Common Information
		vm.item = {
				partnerName : '',
				contractCode : '',
				investProjectName : '',
				constrtCode : '',
				constrtName : '',
				constrType : '',
				provinceId : '',
				constrtAddress : '',
				constructId:'',
				
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
// End Common Information
					
			  vm.organization = {
					  representInvestorId : '',
					  constrOrgPlanId : '',
					  representContractorId: '',
					  createdUserId : '',
					  statusCa : '',
					  isActive : '',
					  constructId:'',
					  creatOrUpdate:''
				  };
			  
			vm.organization = listWorkOrganizationPlan.getItem();
			
			//Load combobox
			vm.resultMonitoring = {
				    constructId: '',
				    roleid :Constant.ROLE_ID["employee_roleID_CDT_DDPN"] 
				  };
			vm.resultCEO = {
				    constructId: '',
				    roleid :Constant.ROLE_ID["employee_roleID_DT_GDNT"] 
				  };
		  
			vm.resultMonitoring.constructId= vm.item.constructId;
			vm.resultCEO.constructId= vm.item.constructId;
			
			 getResultMonitoring(vm.resultMonitoring);
			 getResultMonitoring1(vm.resultCEO);
			  
			  function getResultMonitoring(object) {
				  WorkOrganizationPlan.getListEmployeeByRole(object).then(function(data) {
			    vm.monitoring = data.plain();
			  /*  if(vm.organization.creatOrUpdate == "update"){
			    	 vm.organization.representInvestorId = vm.monitoring[0].id;
					}*/
			     })
			     .catch(function(data, status) {
			      console.error('Gists error', response.status,
					 response.data);
			     })
			     .finally(function() {
			      console.log("finally finished gists");
			     });
			  }
			  
			  function getResultMonitoring1(object) {
				  WorkOrganizationPlan.getListEmployeeByRole(object).then(function(data) {
					  vm.monitoring1 = data.plain();
					 /* if(vm.organization.creatOrUpdate == "update"){
						  vm.organization.representContractorId = vm.monitoring1[0].id;
						}*/
					 
			     })
			     .catch(function(data, status) {
			       console.error('Gists error', response.status,
					 response.data);
			     })
			     .finally(function() {
			       console.log("finally finished gists");
			     });
			  }
			  
			//End Load combobox
			// creat
				vm.creatWorkOrganizationPlan = function() {
					var representInvestorId	= vm.organization.representInvestorId;
					var representContractorId = vm.organization.representContractorId ;
					if(vm.organization.creatOrUpdate == "creat"){
						vm.organization={};
						vm.organization.creatOrUpdate = "creat";
					}
					vm.organization.constructId = vm.item.constructId;
					vm.organization.representInvestorId= representInvestorId;
					vm.organization.representContractorId = representContractorId;
					WorkOrganizationPlan.addConstrOrganizationPlan(vm.organization).then(function() {
						if(vm.organization.creatOrUpdate == "creat"){
							toastr.success("Thêm mới thành công");
						}
						if(vm.organization.creatOrUpdate == "update"){
							toastr.success("Update thành công");
						}
						vm.organization ={};
						vm.organization.creatOrUpdate = "creat";
					});
				}
				
				
			  
	}
	
})();