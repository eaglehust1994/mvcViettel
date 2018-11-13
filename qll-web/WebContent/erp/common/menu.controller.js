(function() {
	'use strict';

	var controllerId = 'MenuController';

	angular.module('MetronicApp').controller(controllerId, MenuController);

	/* @ngInject */
	function MenuController($scope, $rootScope, Constant, $http,Restangular) {
		var vm = this;
		$scope.Constant = Constant;
 // Restangular.one("authenServiceRest/loadMenu").get().then(function(casUser){
		
		// /* Get user-info */
		// /* $http.get('user-info').success(function(user){ */
		// // Restangular.one("authenServiceRest/getUserInfo").get().then(function(user){
		// $scope.$watch(function() {
	        // // return $rootScope.authenticatedUser;
	        // return $rootScope.casUser;
	    // },  function(casUser){
	    // /*
		 // * function(user) { if(user==null){ return; } var casUser =
		 // * user.casUser;
		 // */
	    	// if(casUser==null){
	    		// return ;
	    	// }
			// console.log(casUser);
			// vm.menuObjects=casUser.parentMenu;
			// // Constant.has_tscd=_.find(casUser.objectTokens, function(item)
			// // {return item.objectUrl === 'ASSET_TSCD';}) != undefined;
			// Constant.isAuthorizedForProposalEvaluation = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'ProposalEvaluation';}) != undefined;
			// Constant.isAuthorizedForListingConstruction = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'LISTING_CONSTRUCTION';}) != undefined;
			// Constant.isAuthorizedForListPartnerHR = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'LIST_PARTNER_HR';}) != undefined;
			
			// Constant.isAuthorizedForConstrCompleteRecordMap = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'CONSTR_COMPLETE_RECORD_MAP';}) != undefined;
			// Constant.isAuthorizedForHshcAuthentication = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'HSHC_AUTHENTICATION';}) != undefined;
			// Constant.isAuthorizedForListWorkOrganizationPlan = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'LIST_WORK_ORGANIZATION_PLAN';}) != undefined;
			// Constant.isAuthorizedForDs_bg_vt_tb_a_c = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'DS_BG_VT_TB_A_C';}) != undefined;
			// Constant.isAuthorizedForGroundHandover = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'GROUND_HANDOVER';}) != undefined;
			// Constant.isAuthorizedForAcceptanceBList = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'ACCEPTANCE_B_LIST';}) != undefined;
			// Constant.isAuthorizedForCreateMemoryWork = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'CREATE_MEMORY_WORK';}) != undefined;
			// Constant.isAuthorizedForConstrList = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'CONSTR_LIST';}) != undefined;
			// Constant.isAuthorizedForDrawingsList = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'DRAWINGS_LIST';}) != undefined;
			// Constant.isAuthorizedForAcceptanceOfConstructionJobs = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'ACCEPTANCE_OF_CONSTRUCTION_JOBS';}) != undefined;
			
			// Constant.isAuthorizedForSceneGenerateWork = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'SCENE_GENERATE_WORK';}) != undefined;
			// Constant.isAuthorizedForDistanceUnloadedMaterialsList = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'distanceUnloadedMaterialsList';}) != undefined;
			// Constant.isAuthorizedForListReportResult = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'List_Report_Result';}) != undefined;
			// Constant.isAuthorizedForReportRetrieveListA = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'Report_Retrieve_List_A';}) != undefined;
			// Constant.isAuthorizedForAcceptancePhaseWork = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'Acceptance_Phase_Work';}) != undefined;
			
			// Constant.isAuthorizedForDanhSachPhieuYcNghiemThu = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'DANH_SACH_PHIEU_YC_NGHIEM_THU';}) != undefined;
			// Constant.isAuthorizedForConstracceptance = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'CONSTR_ACCEPTANCE';}) != undefined;
			// Constant.isAuthorizedForConstrincomplete = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'CONSTR_INCOMPLETE';}) != undefined;
			
			
			// Constant.isAuthorizedForConstructionTitaziList = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'CONSTRUCTION_TITAZI_LIST';}) != undefined;
			// Constant.isAuthorizedForListCworkComplete = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'LIST_CWORK_COMPLETE';}) != undefined;
			// Constant.isAuthorizedForListCurrentStateHandover = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'LIST_CURRENT_STATE_HANDOVER';}) != undefined;
			// Constant.isAuthorizedForSugggetAdpayment = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'SUGGGET_ADPAYMENT';}) != undefined;
			
			
			// Constant.isAuthorizedForEstablishDetailedSettlementProposalTable = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'EstablishDetailedSettlementProposalTable';}) != undefined;
			// Constant.isAuthorizedForEstimatesAB = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'ESTIMATES_AB';}) != undefined;
			// Constant.isAuthorizedForListEstimateWork = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'LIST_ESTIMATE_WORK';}) != undefined;
			// Constant.isAuthorizedForMonitorMissionAssign = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'MONITOR_MISSION_ASSIGN';}) != undefined;
			// Constant.isAuthorizedForSceneGenerateConstruct = _.find(casUser.objectTokens, function(item) {return item.objectUrl === 'SCENE_GENERATE_CONSTRUCT';}) != undefined;
			
		// //	Constant.setUser(user);			
		// });

 // });
		vm.goTo = goTo;
		
		/*
		 * get menu text - neu vsa tra ve null thi
		 */
		vm.getMenuText=function(menuKey){
            try {
                var template = Constant.getTemplateUrl(menuKey);
                if(template==null){
        			return "N/A";
                }
                return template.title;
            }catch(err){
				console.debug(err);
                return "N/A";
            }
        }
		
		/* Handle action client on a menu item */
		function goTo(menuKey) {
			var template = Constant.getTemplateUrl(menuKey);
			// setArParams(menuKey);
			// setIdCheckbox(menuKey);
			// setApParams(menuKey);
			
			console.debug("postal", postal);
			postal.publish({
				channel : "Tab",
				topic   : "open",
				data    : template
			});
		}
		vm.activeHomePage=activeHomePage;
		function activeHomePage() {
			
			// setArParams(menuKey);
			// setIdCheckbox(menuKey);
			// setApParams(menuKey);
			
			
			postal.publish({
				channel : "Tab",
				topic   : "active"
				
			});
		}
		
	}
})();