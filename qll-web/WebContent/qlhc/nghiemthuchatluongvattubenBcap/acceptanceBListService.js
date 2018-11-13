angular
		.module('MetronicApp')
		.factory(
				'acceptanceBListService',
				[
						'$http',
						'$q',
						'RestEndpoint',
						'Restangular',
						'$kWindow',
						function($http, $q, RestEndpoint, Restangular, $kWindow) {
							var item;
							var factory = {
								fetchAll : fetchAll,
								findByConstructId : findByConstructId,
								setItemID : setItemID,
								getItemID : getItemID,
								removeBMaterial : removeBMaterial,
								
								// b detail service//
								getAcceptanceList : getAcceptanceList,
								getAcceptanceListById : getAcceptanceListById,
								getAccpetMerList : getAccpetMerList,
								updateBMaterialAcceptMerList : updateBMaterialAcceptMerList,
								createBMerList : createBMerList,
								deleteResult : deleteResult,
								update : update,
								exportFile : exportFile,
								getListEmployeeByRole : getListEmployeeByRole,
								showPopupWorkForm :showPopupWorkForm,
								addBMaterialAcceptance :addBMaterialAcceptance,
								exportFilePDF : exportFilePDF,
								exportFileDOC:exportFileDOC,
								exportFileDOCDetail : exportFileDOCDetail,
								exportFilePDFDetail : exportFilePDFDetail,
								appro:appro,
								cancelAprroval : cancelAprroval,
								deleteListEntity:deleteListEntity,
								getRoleId:getRoleId,
								downloadTempleBmaterial:downloadTempleBmaterial,
								getListEmployeeByConstruction : getListEmployeeByConstruction
							// end b detail service//
							};

							function setItemID(item) {
								this.item = item;
							}
							function getItemID() {
								var deferred = $q.defer();
								// console.log(JSON.stringify(this.item));
								deferred.resolve(this.item);
								return deferred.promise;
							}
							return factory;
							
							function cancelAprroval(dto){
						    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
						    }
							function fetchAll() {
								return Restangular
										.one(
												RestEndpoint.ACCEPTANE_B_LIST_SERVCICE_URL)
										.get();
							}
							function findByConstructId(constructId) {
								return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+ "/findByConstructId").post(constructId);
							}
							// /Remove Quality
							function removeBMaterial(bMaterialAcceptanceId) {
								console.log(bMaterialAcceptanceId)
								return Restangular
										.one(
												RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL,
												bMaterialAcceptanceId).remove();
							}
							////////B detail service  ///
							function getListEmployeeByConstruction(item) {
								return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
					 
							}
						     // lấy ra list dữ liệu join từ 4 bảng
						     function getAcceptanceList(){
						    	 return Restangular.all(RestEndpoint.ESTIMATES_DETAIL_ANALYST_SERVICE_URL+"/estimatesDetailAnalystList").getList();
						     }
						 	function getAcceptanceListById(constructId) {
								return Restangular.all(RestEndpoint.ESTIMATES_DETAIL_ANALYST_SERVICE_URL+ "/getAcceptanceListById/" + constructId).getList();
							}
						    function getAccpetMerList(bMaterialAcceptanceId){
						    	return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPT_MER_LIST_SERVICE_URL+ "/getAccpetMerList/" + bMaterialAcceptanceId).getList();
						    }
						    
						   ////////////// 
						    function updateBMaterialAcceptMerList(objectReportResult){
//						    	console.log(JSON.stringify(bMaterialAcceptanceId));
						    	return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPT_MER_LIST_SERVICE_URL +"/put").post(objectReportResult);
						    }
						    //////////////
							function getListEmployeeByRole(object) {
								return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);			
							}
						    function createBMerList(bMaterialAcceptanceId){
						    	console.log(JSON.stringify(bMaterialAcceptanceId));
						    	return Restangular.one(RestEndpoint.B_MATERIAL_ACCEPT_MER_LIST_SERVICE_URL).post(bMaterialAcceptanceId);
						    }
						    function deleteResult(listIDResult){
								return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/deleteResult").post(listIDResult);
							}
						    function deleteListEntity(listIDResult){
								return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/deleteListEntity").post(listIDResult);
							}
						    
						    function update(objectBM){
						    	return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL +"/put").post(objectBM);
						    }
						    
							function exportFile(objectReport) {
								return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportFile").post(objectReport);
							}
							 
						    function showPopupWorkForm(scope) {
						        return Restangular.all(RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL+ "/getGrid").getList(); 	 
						    }
							function addBMaterialAcceptance(object){
								return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL).post(object);
							}
							
							function exportFilePDF(criteria) {
								   return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportList").post(criteria);
								  }
						
							function exportFileDOC(criteria) {
								   return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportListDoc").post(criteria);
								  }
							function exportFileDOCDetail(criteria) {
								   return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportFileDOCDetail").post(criteria);
								  }
							function exportFilePDFDetail(criteria) {
								   return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/exportFilePDFDetail").post(criteria);
								  }
							function appro(obj){
								return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL + "/appro").post(obj); 
							}
							 function getRoleId() {
							        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
							    }
							 function downloadTempleBmaterial(obj){
							    	return Restangular.all(RestEndpoint.B_MATERIAL_ACCEPTANCE_SERVICE_URL+"/downloadTempleBmaterial").post(obj);
							    }
							 
							 
						} ]);
