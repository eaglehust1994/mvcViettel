
angular.module('MetronicApp').factory('constrCompleteRecordsMapService', ['$http', '$q', 'RestEndpoint', 'PopupConst', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, PopupConst, Restangular, $kWindow){

	var SERVICE_URL = RestEndpoint.CONSTR_COMPLETE_RECORDS_MAP_RS_SERVICE;

	var factory = {
				filter: filter,
				findById: findById,
				getListInvoice:getListInvoice,
				exportFileExcell:exportFileExcell,
				findByConstructionId: findByConstructionId,
				exportListMonitorAssign:exportListMonitorAssign,
				exportListWorkCompConfirm:exportListWorkCompConfirm,
				exportListScene:exportListScene,
				exportListAmaterialHanover:exportListAmaterialHanover,
				exportListAcceptanceRequest:exportListAcceptanceRequest,
				exportListRecoveryMinute:exportListRecoveryMinute,
				exportListQualityMea:exportListQualityMea,
				exportListBmaterialAcceptance:exportListBmaterialAcceptance,
				exportListAbCompleteWork:exportListAbCompleteWork,
				exportListAbMaterialCompare:exportListAbMaterialCompare,
				exportListAbSettlementValue:exportListAbSettlementValue,
				exportListAbSettlementWork:exportListAbSettlementWork,
				exportListCategoryAcceptance:exportListCategoryAcceptance,
				exportListCompleteDrawing:exportListCompleteDrawing,
				exportListGroundHanover:exportListGroundHanover,
				exportListOrganizationPlan:exportListOrganizationPlan,
				exportListWorkLog:exportListWorkLog,
				exportListWorkLogLable:exportListWorkLogLable,
				exportListStateHanover:exportListStateHanover,
				exportListDetailSettlementProposal:exportListDetailSettlementProposal,
				exportListDistanceUnload:exportListDistanceUnload,
				exportListTitAzi:exportListTitAzi,
				exportListWorkItemAcceptance:exportListWorkItemAcceptance,
				exportListAbDetailPrice:exportListAbDetailPrice,
				exportListAbCompleteWorkDescribe:exportListAbCompleteWorkDescribe,
				exportListConstructionAcceptance: exportListConstructionAcceptance,
				/*exportListAbCompleteWorkDescribe:exportListAbCompleteWorkDescribe,*/
				exportListMonitorAssignDoc:exportListMonitorAssignDoc,
				exportListWorkCompConfirmDoc:exportListWorkCompConfirmDoc,
				exportListSceneDoc:exportListSceneDoc,
				exportListAmaterialHanoverDoc:exportListAmaterialHanoverDoc,
				exportListRecoveryMinuteDoc:exportListRecoveryMinuteDoc,
				exportListQualityMeaDoc:exportListQualityMeaDoc,
				exportListBmaterialAcceptanceDoc:exportListBmaterialAcceptanceDoc,
				exportListAbCompleteWorkDoc:exportListAbCompleteWorkDoc,
				exportListAbMaterialCompareDoc:exportListAbMaterialCompareDoc,
				exportListAbSettlementValueDoc:exportListAbSettlementValueDoc,
				exportListAbSettlementWorkDoc:exportListAbSettlementWorkDoc,
				exportListCategoryAcceptanceDoc:exportListCategoryAcceptanceDoc,
				exportListCompleteDrawingDoc:exportListCompleteDrawingDoc,
				exportListGroundHanoverDoc:exportListGroundHanoverDoc,
				exportListOrganizationPlanDoc:exportListOrganizationPlanDoc,
				exportListWorkLogDoc:exportListWorkLogDoc,
				exportListWorkLogLableDoc:exportListWorkLogLableDoc,
				exportListStateHanoverDoc:exportListStateHanoverDoc,
				exportListDetailSettlementProposalDoc:exportListDetailSettlementProposalDoc,
				exportListDistanceUnloadDoc:exportListDistanceUnloadDoc,
				exportListTitAziDoc:exportListTitAziDoc,
				exportListWorkItemAcceptanceDoc:exportListWorkItemAcceptanceDoc,
				exportListAbDetailPriceDoc:exportListAbDetailPriceDoc,
				exportListAbCompleteWorkDescribeDoc:exportListAbCompleteWorkDescribeDoc,
				exportListConstructionAcceptanceDoc: exportListConstructionAcceptanceDoc,
				/*exportListAbCompleteWorkDescribeDoc:exportListAbCompleteWorkDescribeDoc,*/
	};
	return factory;
	
	function exportFileExcell(object) {
    	return Restangular.all(RestEndpoint.CONSTR_COMPLETE_RECORDS_EXPORT_EXCELL_SERVICE).post(object);
    	
    }
	function exportListAbDetailPrice(object) {
    	return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB + "/exportPDFBieu5").post(object);
    	
    }
	function exportListWorkItemAcceptance(object) { // tùng
    	return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/exportWordList").post(object);
    	
    }
	function exportListTitAzi(object) {
    	return Restangular.all(RestEndpoint.TITLE_AZIMU_SERVICE_URL+"/exportOnePDF").post(object);
    	
    }
	function exportListDistanceUnload(object) { // phong
    	return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL  + "/exportWordList").post(object);
    	
    }
	function exportListDetailSettlementProposal(object) { 
    	return Restangular.all("").post(object);
    	
    }
	
	function exportListStateHanover(object) {// bàn giao hện trạng - linh
    	return Restangular.all("currentStHandoverRest/currentStateHandover/exportList").post(object);
    	
    }
	function exportListWorkLogLable(object) {
    	return Restangular.all(RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL+"/exportFilePdf").post(object);
    	
    }
	function exportListWorkLog(object) {
    	return Restangular.all(RestEndpoint.CONSTR_WORK_LOGS_URL+"/exportFileConstrWorkLogs").post(object);
    	
    }
	function exportListOrganizationPlan(object) { // phương án tô chức thi công
    	return Restangular.all(RestEndpoint.UTIL_ATTACHED_DOCUMENTS_SERVICE_URL + "/exportFileZip").post(object);
    	
    }
	function exportListGroundHanover(object) {
    	return Restangular.all(RestEndpoint.CONSTR_GROUND_HANDOVER_SERVICE_URL+"/exportPDF").post(object);
    	
    }
	function exportListCompleteDrawing(object) {// bản vẽ thi công ngọccx
    	return Restangular.all(RestEndpoint.COMPLETION_DRAWINGS_SEARCH_BY_ID_URL).post(object);
    	
    }
	function exportListCategoryAcceptance(object) {
    	return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL  + "/exportWordListpdf").post(object);
    	
    }
	function exportListAbSettlementWork(object) {
    	return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportPDFBieu2").post(object);
    	
    }
	function exportListAbSettlementValue(object) { // huynv
    	return Restangular.one("AbSettlementValueServiceRest/abSettlementValue/exportFilePdf/"+object).get();
    	
    }
	function exportListAbMaterialCompare(object) {// nhẫ
    	return Restangular.all("").post(object);
    	
    }
	function exportListAbCompleteWork(object) {
    	return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportPDFBieu3").post(object);
    	
    }
	
	function exportListAbCompleteWorkDescribe(object) {
    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL + "/exportFilePDFDetailForm4").post(object);
    	
    }

	function filter(criteria){
		return Restangular.all(SERVICE_URL + "/filter").post(criteria);
	}

	function findByConstructionId(constructionId){
		return Restangular.all(SERVICE_URL + "/getByConstructionId/"+constructionId).getList();
	}
	function findByConstructionId(constructionId){
		return Restangular.all(SERVICE_URL + "/getByConstructionId/"+constructionId).getList();
	}
	function getListInvoice(object){
		return Restangular.all(RestEndpoint.GET_LIST_INVOICE_SERVICE_URL).post(object);
	}

	function findById(id) {
		return Restangular.one(SERVICE_URL + "/" + id).get();
	}
	
	function exportListAcceptanceRequest(object) {
    	return Restangular.all(RestEndpoint.EXPORT_PYCNT_SERVICE_URL).post(object);
    }
	
	function exportListAmaterialHanover(object) {
    	return Restangular.all(RestEndpoint.EXPORT_LIST_SERVICE_URL).post(object);
    	
    }
	
	function exportListScene(object) {
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneList").post(object);
    	
    }
	
	function exportListWorkCompConfirm(object) {
    	return Restangular.all("cWorkCompleteRest/constrWorkCompConfirm/exportList").post(object);
    	
    }
	
	function exportListRecoveryMinute(object) {
    	return Restangular.all(RestEndpoint.A_MATERIAL_RECOVERY_MINUTES_URL+"/exportList").post(object);
    	
    }
	
	function exportListQualityMea(object) {
    	return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportList").post(object);
    	
    }
	
	function exportListBmaterialAcceptance(object) {
    	return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportList").post(object);
    	
    }
	

	//////////////////////////////////////////////////////////////////////////////////////////////
	
	function exportListAbDetailPriceDoc(object) {
    	return Restangular.all(RestEndpoint.AB_DETAIL_PRICE_ESTIMATEAB + "/exportDOCBieu5").post(object);
    	
    }
	function exportListWorkItemAcceptanceDoc(object) { // tùng
    	return Restangular.all(RestEndpoint.WORK_ITEMS_ACCEPTANCE_SERVICE_URL  + "/exportWordListDoc").post(object);
    	
    }
	function exportListTitAziDoc(object) {
    	return Restangular.all(RestEndpoint.TITLE_AZIMU_SERVICE_URL+"/exportOneDoc").post(object);
    	
    }
	function exportListDistanceUnloadDoc(object) { // phong
    	return Restangular.all(RestEndpoint.DISTANCE_UNLOAD_CONSTR_MINUTE_SERVICE_URL  + "/exportDocList").post(object);
    	
    }
	function exportListDetailSettlementProposalDoc(object) { 
    	return Restangular.all("").post(object);
    	
    }
	
	function exportListStateHanoverDoc(object) {// bàn giao hện trạng - linh
    	return Restangular.all("currentStHandoverRest/currentStateHandover/exportListDOC").post(object);
    	
    }
	function exportListWorkLogLableDoc(object) {
    	return Restangular.all(RestEndpoint.CONSTR_WORK_LOGS_LABEL_URL+"/exportFileDoc").post(object);
    	
    }
	function exportListWorkLogDoc(object) {
    	return Restangular.all(RestEndpoint.CONSTR_WORK_LOGS_URL+"/exportFileDoc").post(object);
    	
    }
	function exportListOrganizationPlanDoc(object) { // phương án tô chức thi công
    	return Restangular.all(RestEndpoint.UTIL_ATTACHED_DOCUMENTS_SERVICE_URL + "/exportFileZip").post(object);
    	
    }
	function exportListGroundHanoverDoc(object) {
    	return Restangular.all(RestEndpoint.CONSTR_GROUND_HANDOVER_SERVICE_URL+"/exportPDF").post(object);
    	
    }
	function exportListCompleteDrawingDoc(object) {// bản vẽ thi công ngọccx
    	return Restangular.all(RestEndpoint.COMPLETION_DRAWINGS_SEARCH_BY_ID_URL).post(object);
    	
    }
	function exportListCategoryAcceptanceDoc(object) {
    	return Restangular.all(RestEndpoint.CATEGORY_ACCEPTANCE_URL  + "/exportWordListDoc").post(object);
    	
    }
	function exportListAbSettlementWorkDoc(object) {
    	return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportDOCBieu2").post(object);
    	
    }
	function exportListAbSettlementValueDoc(object) { // huynv
    	return Restangular.one("AbSettlementValueServiceRest/abSettlementValue/exportFileWord/"+object).get();
    	
    }
	function exportListAbMaterialCompareDoc(object) {// nhẫ
    	return Restangular.all("").post(object);
    	
    }
	function exportListAbCompleteWorkDoc(object) {
    	return Restangular.all(RestEndpoint.AB_COMPLEMENT_WORK_ESTIMATEAB + "/exportDOCBieu3").post(object);
    	
    }
	
	function exportListAbCompleteWorkDescribeDoc(object) {
    	return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL + "/exportFileDOCDetailForm4").post(object);
    	
    }
	
	function exportListAmaterialHanoverDoc(object) {
    	return Restangular.all(RestEndpoint.EXPORT_LIST_DOC_SERVICE_URL).post(object);
    	
    }
	
	function exportListSceneDoc(object) {
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneListDoc").post(object);
    	
    }
	
	function exportListWorkCompConfirmDoc(object) {
    	return Restangular.all("cWorkCompleteRest/constrWorkCompConfirm/exportListDOC").post(object);
    	
    }
	
	function exportListRecoveryMinuteDoc(object) {
    	return Restangular.all(RestEndpoint.A_MATERIAL_RECOVERY_MINUTES_URL+"/exportListDoc").post(object);
    	
    }
	
	function exportListQualityMeaDoc(object) {
    	return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportListDoc").post(object);
    	
    }
	
	function exportListBmaterialAcceptanceDoc(object) {
    	return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportListDoc").post(object);
    	
    }
	

	
	function exportListMonitorAssignDoc(object) {
    	return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/exportDocList").post(object);
    }
	
	
	
	/////////////////////////////////////////////////////////////

	function filter(criteria){
		return Restangular.all(SERVICE_URL + "/filter").post(criteria);
	}


	
	function getListInvoice(object){
		return Restangular.all(RestEndpoint.GET_LIST_INVOICE_SERVICE_URL).post(object);
	}

	function findById(id) {
		return Restangular.one(SERVICE_URL + "/" + id).get();
	}
	
	function exportListAcceptanceRequest(object) {
    	return Restangular.all(RestEndpoint.EXPORT_PYCNT_SERVICE_URL).post(object);
    }
	
	function exportListAmaterialHanover(object) {
    	return Restangular.all(RestEndpoint.EXPORT_LIST_SERVICE_URL).post(object);
    	
    }
	
	function exportListScene(object) {
    	return Restangular.all(RestEndpoint.SCENE_GENERATE_WORK_URL  + "/exportSceneList").post(object);
    	
    }
	
	function exportListWorkCompConfirm(object) {
    	return Restangular.all("cWorkCompleteRest/constrWorkCompConfirm/exportList").post(object);
    	
    }
	
	function exportListRecoveryMinute(object) {
    	return Restangular.all(RestEndpoint.A_MATERIAL_RECOVERY_MINUTES_URL+"/exportList").post(object);
    	
    }
	
	function exportListQualityMea(object) {
    	return Restangular.all(RestEndpoint.LIST_REPORT_RESULT_QUALITY_URL+"/exportList").post(object);
    	
    }
	
	function exportListBmaterialAcceptance(object) {
    	return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportList").post(object);
    	
    }
	
	function exportListConstructionAcceptance(object) {
    	return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL+"/exportWordList").post(object);
    	
    }
	function exportListConstructionAcceptanceDoc(object) {
    	return Restangular.all(RestEndpoint.CONSTRUCTION_ACCEPTANCE_SERVICE_URL+"/exportPdfList").post(object);
    	
    }
	
	function exportListMonitorAssign(object) {
    	return Restangular.all(RestEndpoint.MONITOR_MISSION_ASSIGN_URL  + "/exportWordList").post(object);
    	
    }
	
	


}]);
