angular.module('MetronicApp').factory('estimatesABService', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular){
	
	    var factory = {
	    		getlistAmonitorConstructionForm6:getlistAmonitorConstructionForm6, 
	    		getlistAmonitorConstruction:getlistAmonitorConstruction,
	    		getEstimateAB3:getEstimateAB3,
	    		getListEmployeeByRole:getListEmployeeByRole,
	    		getEstimateAB1:getEstimateAB1,
	    		getEstimateAB2:getEstimateAB2,
	    		getEstimateAB5:getEstimateAB5,
	    		getCompletionFolder : getCompletionFolder,
	    		getValueMerchandise : getValueMerchandise,
	    		getValueSupplies : getValueSupplies,
	    		getValueLost : getValueLost,
	    		getContractTotalValue : getContractTotalValue,
	    		addEstimateAB3: addEstimateAB3,
	    		getTotalPrice : getTotalPrice,	    		
	    		createMaterial : createMaterial,
	    		createSettlementValue : createSettlementValue,
	    		exportFilePdfForm1 : exportFilePdfForm1,
	    		exportFileDocForm1 : exportFileDocForm1,
	    		getEstimateAB4:getEstimateAB4,
	    		exportPDFBieu2:exportPDFBieu2,
	    		exportDOCBieu2:exportDOCBieu2,
	    		exportFileDOCDetailForm4 : exportFileDOCDetailForm4,
	    		exportFilePDFDetailForm4 : exportFilePDFDetailForm4,
	    		recordForm4  :recordForm4 ,
	    		getForm4ByConstrId : getForm4ByConstrId,
	    		udpateForm4:udpateForm4,
	    		signCA:signCA,
	    		addEstimateAB2,
	    		updateEstimateAB2,
	    		exportDOCBieu3: exportDOCBieu3,
	    		exportPDFBieu3: exportPDFBieu3,
	    		getAbSettIdByConstrId: getAbSettIdByConstrId,
	    		addthongtinAB2:addthongtinAB2,
	    		updatethongtinAB2:updatethongtinAB2,
	    		getAmonitorsignCA3: getAmonitorsignCA3,
	    		checkSave3:checkSave3,
	    		addEstimateAB5:addEstimateAB5,
	    		exportPDFBieu5:exportPDFBieu5,
	    		exportDOCBieu5:exportDOCBieu5,
	    		checkSave5:checkSave5,
	    		checkDataFrom6 : checkDataFrom6,
	    		checkDataFrom1 : checkDataFrom1,
	    		getconstrCompReMap : getconstrCompReMap,
	    		getAmonitorsignCA5 : getAmonitorsignCA5,
	    		getStatusEvaluate : getStatusEvaluate,
	    		getRoleIdForm4 : getRoleIdForm4,
	    		getEmployeeByRoleForm6 : getEmployeeByRoleForm6
	    };
	 
	    return factory;
	    
	    function getAbSettIdByConstrId(id) {
			return Restangular.all(RestEndpoint.AB_SETTLEMENT_WORK_SERVICE_URL+ "/getAbSettIdByConstrId").post(id);			
		}
	    function addthongtinAB2(objectDTO) {
			return Restangular.all(RestEndpoint.AB_SETTLEMENT_WORK_SERVICE_URL).post(objectDTO);			
		}
	    function updatethongtinAB2(objectDTO) {
			return Restangular.all(RestEndpoint.AB_SETTLEMENT_WORK_SERVICE_URL + "/put").post(objectDTO);			
		}
	    function exportPDFBieu2(criteria) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportPDFBieu2").post(criteria);
		}
	    
	    function exportDOCBieu2(criteria) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportDOCBieu2").post(criteria);
		}
	    
	    function exportPDFBieu3(criteria) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportPDFBieu3").post(criteria);
		}
	    
	    function exportDOCBieu3(criteria) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportDOCBieu3").post(criteria);
		}
	    function addEstimateAB2(obj) {
			return Restangular.all(RestEndpoint.AB_SETTLEMENT_WORK_SERVICE_URL).post(obj);
		}
	    function updateEstimateAB2(obj) {
			return Restangular.all(RestEndpoint.AB_SETTLEMENT_WORK_SERVICE_URL + "/put").post(obj);
		}
	    
	    function addEstimateAB3(obj) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB).post(obj);
		}
	    function getlistAmonitorConstruction(objectDTO) {
			return Restangular.all(RestEndpoint.SETTLEMENT_RIGHT_URL).post(objectDTO);			
		}
	    //minhpvn
	    function getlistAmonitorConstructionForm6(objectDTO) {
			return Restangular.all(RestEndpoint.SETTLEMENT_RIGHT_URL_ESTIMATE_AB_FORM_6_URL).post(objectDTO);			
		}
	    //end minhpvn
	    function getEstimateAB3(obj) {
			return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL +"/getByConstruction").post(obj);
		}
	    
	    function getListEmployeeByRole(object) {
			return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);			
		}
	    
	    function getEstimateAB2(obj) {
			return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL +"/getBieu2").post(obj);
		}

	    
	    function getEstimateAB5(obj) {
			return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB +"/getBieu5").post(obj);
		}
	    
	    function getEstimateAB4(obj) {
			return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL +"/getBieu4").post(obj);
		}
	   
	    function getCompletionFolder(scope){
	    	return Restangular.one(RestEndpoint.COMPLETION_PARTNER_FOLDER_URL + "/uploadFileEstimate").get();
	    }
	    
	    function getEstimateAB1(id) {
			return Restangular.all(RestEndpoint.CONSTR_ACCEPT_WORK_LIST_SERVICE_URL +"/getProposedSettlement/" + id).post();
		}
	    
	    function getValueMerchandise(id) {
			return Restangular.all(RestEndpoint.CONSTR_MERCHANDISE_SERVICE_URL +"/getValueConstrMerchandise/" + id).post();
		}
	    
	    function getValueSupplies(id) {
			return Restangular.all(RestEndpoint.ASSET_MANAGER_REQ_SERVICE_URL +"/getValueSupplies/" + id).post();
		}
	    
	    function getValueLost(id) {
			return Restangular.all(RestEndpoint.CONSTR_ACCEPT_LOST_NOTE_SERVICE_URL +"/getValueLoss/" + id).post();
		}
	    
	    function getContractTotalValue(id) {
			return Restangular.all(RestEndpoint.CONSTR_CONSTRUCTIONS_SERVICE_URL +"/getContractTotalValue/" + id).post();
		}
	    
	    function getTotalPrice(id) {
			return Restangular.all(RestEndpoint.ASSET_MANAGER_REQ_SERVICE_URL +"/getTotalPrice/" + id).post();
		}
	    
	    function exportFilePdfForm1(id) {
			return Restangular.one(RestEndpoint.AB_SETTLEMENT_VALUE_SERVICE_URL +"/exportFilePdf/" + id).get();
		}
	    
	    function exportFileDocForm1(id) {
			return Restangular.one(RestEndpoint.AB_SETTLEMENT_VALUE_SERVICE_URL +"/exportFileWord/" + id).get();
		}
	    
	    function createMaterial(obj) {
	    	return Restangular.all(RestEndpoint.AB_MATERIAL_COMPARE_SERVICE_URL).post(obj);
	    }
	    
	    function createSettlementValue(obj) {
	    	return Restangular.all(RestEndpoint.AB_SETTLEMENT_VALUE_SERVICE_URL).post(obj);
	    }
	    
		function exportFileDOCDetailForm4(criteria) {
			   return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/exportFileDOCDetailForm4").post(criteria);
			  }
		
		function exportFilePDFDetailForm4(criteria) {
			   return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+"/exportFilePDFDetailForm4").post(criteria);
			  }
	    function recordForm4(obj) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_DESCRIBE_SERVICE_URL+"/addForm4" ).post(obj);
		}
	    function udpateForm4(obj) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_DESCRIBE_SERVICE_URL + "/put").post(obj);
		}
	    function getForm4ByConstrId(id){
	    	return Restangular.one(RestEndpoint.AB_COMPLEMENT_WORK_DESCRIBE_SERVICE_URL + "/constructId/"+id).get();
	    }
	    
		function signCA(obj){
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_DESCRIBE_SERVICE_URL + "/signCA").post(obj); 
		}
		
		function getAmonitorsignCA3(constructId) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/constructIdsignCA/"+constructId).post(constructId);
		}
		
		function getAmonitorsignCA5(constructId) {
			return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB + "/constructIdsignCA/"+constructId).post(constructId);
		}
		 
		function checkSave3(constructId) {
			return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/checkSave3/"+constructId).post(constructId);
		}
		
		function checkSave5(constructId) {
			return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB + "/checkSave5/"+constructId).post(constructId);
		}
		function addEstimateAB5(obj) {
			return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB).post(obj);
		}
		function exportPDFBieu5(criteria) {
			return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB + "/exportPDFBieu5").post(criteria);
		}
		    
		function exportDOCBieu5(criteria) {
			return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB + "/exportDOCBieu5").post(criteria);
		}

		function checkDataFrom6(constructId) {
			return Restangular.all(RestEndpoint.AB_MATERIAL_COMPARE_SERVICE_URL + "/checkDataForm6/"+constructId).post(constructId);
		}
		
		function checkDataFrom1(constructId) {
			return Restangular.all(RestEndpoint.AB_SETTLEMENT_VALUE_SERVICE_URL + "/checkDataForm1/"+constructId).post(constructId);
		}
		
		function getconstrCompReMap(constructId) {
			return Restangular.all(RestEndpoint.AB_SETTLEMENT_VALUE_SERVICE_URL + "/getconstrCompReMapId/"+constructId).post(constructId);
		}
		
		function getStatusEvaluate(constructId) {
			return Restangular.one(RestEndpoint.AB_SETTLEMENT_VALUE_SERVICE_URL + "/getStatusEvaluate/"+constructId).get();
		}
		function getRoleIdForm4() {
		    return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
		}
		function getEmployeeByRoleForm6(object) {
		    return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getEmployeeNameAndIdByRole").post(object); 	 
		}

	}]);
