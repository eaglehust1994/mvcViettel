angular.module('MetronicApp').factory('SearchService', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular){
	 
	    var factory = {	      
	        searchAdOrg:searchAdOrg,
	        searchAccount: searchAccount,
	        searchDocumentType: searchDocumentType,
	        searchPeriod: searchPeriod,
	        /*searchAccount: searchAccount,*/
	        searchBpartner: searchBpartner,
	        searchPayroll: searchPayroll,
	        searchBudget: searchBudget,
	        searchCostType: searchCostType,
	        searchProfitCenter:searchProfitCenter,
	        searchCostCenter:searchCostCenter,
	        searchRevenueType:searchRevenueType,
	        searchDepartment:searchDepartment,
	        searchPartner:searchPartner,
	        searchProject:searchProject,
	        searchProjectPhase:searchProjectPhase,
	        searchLocation:searchLocation,
	        searchProduct:searchProduct,
	        searchMwarehouce:searchMwarehouce,
	        searchBank:searchBank,
	        searchCSiteCodeInfo:searchCSiteCodeInfo,
	        searchStatement:searchStatement,
	         searchConstruction:searchConstruction,
	        searchConstructionPhase:searchConstructionPhase,
	        searchConstructionGroup:searchConstructionGroup,
	        searchContract:searchContract,
	        searchDocumentType1:searchDocumentType1,
	        searchCPeriod:searchCPeriod,
	        searchSiteCodeType: searchSiteCodeType,
	        searchProductCategory: searchProductCategory,
	        
	        searchParner:searchParner,
	        searchEstimatesWorkItems : searchEstimatesWorkItems,
	        searchEstimatesDetailAnalyst : searchEstimatesDetailAnalyst,
	        searchSceneGenerateWork : searchSceneGenerateWork,
	        searchCatEmployee: searchCatEmployee,
	        searchPartnerHr: searchPartnerHr,
	        
	        searchICntCntract : searchICntCntract,
	        searchIProInvesPro : searchIProInvesPro,
	    };
	 
	    return factory;
	    function searchAdOrg(code, name) {
	    	return Restangular.all(RestEndpoint.AD_ORG_SERVICE_URL, code).all(name).getList();
	    	//return Restangular.one(RestEndpoint.AD_ORG_SERVICE_URL, code).customGET(name).getList();
        }
	    
	    function searchCatEmployee(searchCriteria) {
	    	return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_Url+"/doSearchEmployeeViettel").post(searchCriteria);
        }
	    
	    function searchAccount(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_ACCOUNT_SERVICE_URL+"/search").post(searchCriteria);
        }
	    
	    function searchBpartner(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_BPARTNER_SERVICE_URL+"/search").post(searchCriteria);
        }
	    
	    function searchPayroll(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_PAYROLL_SERVICE_URL+"/search").post(searchCriteria);
        }
     
	    function searchBudget(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_BUDGET_SERVICE_URL+"/doSearchBudget").post(searchCriteria);
        }
     
	    function searchCostType(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_COST_TYPE_SERVICE_URL+"/doSearchCostType").post(searchCriteria);
        }
	    
	    function searchDocumentType(code, name) {
	    	return Restangular.one(RestEndpoint.C_DOCUMENT_TYPE_SEARCH_URL, code).all(name).getList();
        }
	    
	    function searchDocumentType1(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_DOCUMENT_TYPE_URL+"/doSearchPopup").post(searchCriteria);
        }
	    function searchPeriod(code, name) {
	    	return Restangular.one(RestEndpoint.C_PERIOD_SEARCH_URL, code).all(name).getList();
        }
     
	    function searchProfitCenter(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_PROFIT_CENTER_SERVICE_URL+"/doSearchPopup").post(searchCriteria);
        }
	    
	    function searchCostCenter(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_COST_CENTER_SERVICE_URL+"/doSearchPopup").post(searchCriteria);
        }
	    
	    function searchRevenueType(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_SALES_REGION_URL+"/doSearchPopup").post(searchCriteria);
        }
	    
	    function searchDepartment(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_DEPARTMENT_SERVICE_URL+"/search").post(searchCriteria);
        }
	    
	    function searchPartner(searchCriteria) {
	    	return Restangular.all(RestEndpoint.C_BPARTNER_SERVICE_URL+"/search").post(searchCriteria);
        }
	    
	    function searchProject(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_PROJECT_SERVICE_URL+"/doSearchPopup").post(searchCriteria);
	    }	
	    
	      function searchProjectPhase(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_PROJECT_PHASE_SERVICE_URL+"/doSearchPopup").post(searchCriteria);
	    }
	    function searchLocation(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_LOCATION_SERVICE_URL+"/search").post(searchCriteria);
	    }	
	    
	    function searchProduct(searchCriteria){
	    	return Restangular.all(RestEndpoint.M_PRODUCT_SERVICE_URL+"/search").post(searchCriteria);
	    }
	    function searchMwarehouce(searchCriteria){
	    	return Restangular.all(RestEndpoint.M_WAREHOUSE_SERVICE_URL+"/search").post(searchCriteria);
	    }
	    
	    function searchBank(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_BANK_SERVICE_URL+"/search").post(searchCriteria);
	    }
	    
	    function searchProductCategory(searchCriteria){
	    	return Restangular.all(RestEndpoint.M_PRODUCT_CATEGORY_SERVICE_URL+"/search").post(searchCriteria);
	    }
	    
	    function searchCSiteCodeInfo(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_SITE_CODE_INFO_SERVICE_URL+"/doSearchPopup").post(searchCriteria);
	    }
	    
	    function searchStatement(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_STATEMENT_SERVICE_URL+"/search").post(searchCriteria);
	    }
	     function searchConstruction(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_CONSTRUCTION_SERVICE_URL+"/search").post(searchCriteria);
	    }
	     function searchConstructionGroup(searchCriteria){
		    	return Restangular.all(RestEndpoint.C_CONSTRUCTION_GROUP_SERVICE_URL+"/search").post(searchCriteria);
		    }
	    function searchConstructionPhase(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_CONSTRUCTION_PHASE_SERVICE_URL+"/search").post(searchCriteria);
	    }
	    
	    function searchContract(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_CONTRACT_SERVICE_URL+"/search").post(searchCriteria);
	    }
	    
	    function searchCPeriod(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_PERIOD_URL+"/getSearch").post(searchCriteria);
	    }
	    
	    function searchSiteCodeType(searchCriteria){
	    	return Restangular.all(RestEndpoint.C_SITE_CODE_TYPE_SERVICE_URL+"/search").post(searchCriteria);
	    }
	    
	    
	    function searchParner(searchCriteria){
	    	return Restangular.all(RestEndpoint.CAT_PARTNERS_SERVICE_URL+"/doSearch").post(searchCriteria);
	    }
/*	    function searchProject(searchCriteria){
	    	return Restangular.all(RestEndpoint.PROJ_INVEST_PROJECT_SERVICE_URL+"/doSearch").post(searchCriteria);
	    }*/
	    function searchEstimatesWorkItems(searchCriteria){
	    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL + "/search").post(searchCriteria);
	    }
	    function searchEstimatesDetailAnalyst(searchCriteria){
	    	return Restangular.all(RestEndpoint.ESTIMATES_DETAIL_ANALYST_SERVICE_URL + "/doSearchEstimatesDetailAnalyst").post(searchCriteria);
	    }
	    function searchSceneGenerateWork(searchCriteria){
	    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_LIST_URL + "/search").post(searchCriteria);
	    }
	    
	    function searchPartnerHr(searchCriteria){
	    	return Restangular.all(RestEndpoint.CAT_PARTNERS_SERVICE_URL + "/getForAutoComplete").post(searchCriteria);
	    }
	    
	    function searchICntCntract(searchCriteria){
	    	return Restangular.all(RestEndpoint.I_CNT_CONTRACT + "/getForAutoComplete").post(searchCriteria);
	    }
	    
	    function searchIProInvesPro(searchCriteria){
	    	return Restangular.all(RestEndpoint.I_PROJ_INVEST_PROJECT_URL + "/getForAutoComplete").post(searchCriteria);
	    }
	    
}]);