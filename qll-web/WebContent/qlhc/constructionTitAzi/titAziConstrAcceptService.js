angular.module('MetronicApp').factory('titAziConstrAcceptService', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular){
		var serviceUrl = RestEndpoint.TITLE_AZIMU_SERVICE_URL;
		var serviceUrl2 = RestEndpoint.TITLE_AZIMU_LIST_SERVICE_URL;
	    var factory = {
	    	
	    	addAll: addAll,
	    	display: display,
	    	deleteRecords: deleteRecords,
	    	removeOneRecord:removeOneRecord,
	    	removeMultiple: removeMultiple,
			setDataItem:setDataItem,
			getDataItem:getDataItem,
			update: update,
			exportFileDOC:exportFileDOC,
			exportOneDoc:exportOneDoc,
			exportFilePDF: exportFilePDF,
			exportOnePDF:exportOnePDF,
			pheduyet:pheduyet,
			appro:appro,
			downloadTempleKLXLHT:downloadTempleKLXLHT,
			cancelAprroval : cancelAprroval,
			// bang con
			getListEmployeeByRole: getListEmployeeByRole,
			displayTitAziConstrAcceptList: displayTitAziConstrAcceptList,
			deleteAziList:deleteAziList,
			removeConstrRecordMap : removeConstrRecordMap,
			getRoleId:getRoleId,
			updateIsActive:updateIsActive
			
	    };
	    
	    return factory;
	    
	    function cancelAprroval(dto){
	    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
	    }
	    // bang con
	    function displayTitAziConstrAcceptList(id) {	    	
	    	return Restangular.all(serviceUrl2+"/getListByTitAziConstrAcceptId/"+id).getList();
	    }
	    
	    function appro(obj){
			return Restangular.all(serviceUrl + "/appro").post(obj); 
		}
	    
	    function deleteAziList(listID){
			return Restangular.all(serviceUrl2+"/deleteList").post(listID);
		}
	    
	    // bang cha
	    function removeMultiple(listID){
			return Restangular.all(serviceUrl+"/removeMultiple").post(listID);
		}
	    
	    function removeConstrRecordMap(id){
	    	return Restangular.one(RestEndpoint.CONSTR_RE_MAP_SERVICE_URL, id).remove();
	    }
	    
	    function pheduyet(obj) {
			return Restangular.all(serviceUrl+"/pheduyet"+"/put").post(obj);			
		}
	    
	    function exportFilePDF(criteria) {
			return Restangular.all(serviceUrl+"/exportList").post(criteria);
		}
	    
	    function exportOnePDF(criteria) {
			return Restangular.all(serviceUrl+"/exportOnePDF").post(criteria);
		}
	    
	    function exportFileDOC(criteria) {
			return Restangular.all(serviceUrl+"/exportListDoc").post(criteria);
		}
	    
	    function exportOneDoc(criteria) {
			return Restangular.all(serviceUrl+"/exportOneDoc").post(criteria);
		}
	    
	    function getListEmployeeByRole(object) {
			return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);			
		}
	    
	    function addAll(obj){
	    	return Restangular.all(serviceUrl).post(obj);
	    }
	 
	    function setDataItem(data){
	    	this.dataItem=data;
	    }
	    
	    function getDataItem(){
	    	return this.dataItem;
	    }
	    
	    function update(object) {
			return Restangular.all(serviceUrl+"/put").post(object);			
		}
	    
		function removeOneRecord(id){
			return Restangular.one(serviceUrl, id).remove();
		}
		
	    function display(data) {	    	
	    	return Restangular.all(serviceUrl+"/findByConstructId").post(data);
	    }
	    
	    function deleteRecords(id) {	    	
	    	return Restangular.all(serviceUrl+"/findByConstructId/"+id).getList();
	    }
	    
	    function getRoleId() {
	        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
	    }
	    
	    function downloadTempleKLXLHT(obj){
	    	return Restangular.all(serviceUrl+"/downloadTempleKLXLHT").post(obj)
	    }
	    function updateIsActive(id) {
			return Restangular
					.all(
							serviceUrl
									+ "/updateIsActive")
					.post(id);
		}
	   
	}]);
