angular.module('MetronicApp').factory('longTermAssetUpgradeService', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular){
		serviceUrl = "merHandOverInfoServiceRest"		
		var id;    
	    var factory = {
				getMerHandOverInfobyId : getMerHandOverInfobyId,
				getMerHandOverEntitybyId : getMerHandOverEntitybyId,
				getCaacName : getCaacName,
				setId:setId,
	    		getId:getId,
	    		updateLongTermAssetEntity : updateLongTermAssetEntity,
	    		//addLongTermAsset : addLongTermAsset,
	    		getDataMerHandOverId : getDataMerHandOverId,
	    		updateLongTermAsset : updateLongTermAsset,
	    		getMerHandOverEntityUpdate : getMerHandOverEntityUpdate,
	    		getCaacCodeFull :getCaacCodeFull,
				getCatAssetFolder: getCatAssetFolder,
				getMerHandOverInfoByLtaId:getMerHandOverInfoByLtaId,
				getMerHandOverInfoDetailByHandOverId :getMerHandOverInfoDetailByHandOverId ,
				//updateLTA:updateLTA,
				
				loadMerHandOverNotByConstrForUpgrade:loadMerHandOverNotByConstrForUpgrade,
				upgradeLta:upgradeLta
	    };
	 
	    return factory;
		
		function getCatAssetFolder(scope){
			return Restangular.one(RestEndpoint.LONG_TERM_ASSET_URL + "/getFolder").get();
		}
	    
	    function getMerHandOverInfobyId(termassetentity) {	    	
	    	return Restangular.all(serviceUrl + "/getbyId").post(termassetentity);
	    }
	    function getMerHandOverEntitybyId(termassetentity){
	    	return Restangular.all(serviceUrl + "/getMerHandOverEntity").post(termassetentity);
	    }
	    
	    function getCaacName(){
	    	return Restangular.all(serviceUrl + "/getCaacname").getList();
	    }
	    function updateLongTermAssetEntity(longtermassetentity){
	    	return Restangular.all(serviceUrl + "/updateMHI").post(longtermassetentity);
	    }
	    function addLongTermAsset(longtermassetentity){
	    	return Restangular.all(serviceUrl + "/insertLTA").post(longtermassetentity);
	    }
	    function updateLTA(longtermassetentity){
	    	return Restangular.all(serviceUrl + "/updateLTA").post(longtermassetentity);
	    }	   
	    function getDataMerHandOverId(updateasset){
	    	 return Restangular.all(serviceUrl + "/getupdateMer").post(updateasset); 
	     }
	    function updateLongTermAsset(updateasset){
	    	 return Restangular.all(serviceUrl + "/update").post(updateasset);
	    }
	    function autocomplateCaac(caacname){
	    	return Restangular.all(serviceUrl + "/getCaacname").post(caacname); 
	    }
	    function getMerHandOverEntityUpdate(updateasset){
	    	return Restangular.all(serviceUrl + "/getMerUpdate").post(updateasset); 
	    }
	    
	    /*
	     * Hanhls1 cho viec sua tai san khong qua xay lap
	     */
	    function getMerHandOverInfoByLtaId(longTermAssetId){
	    		return Restangular.all(serviceUrl + "/getMerHandOverInfoByLtaId").post(longTermAssetId); 
	    }
	    function getMerHandOverInfoDetailByHandOverId(handOverId){
    		return Restangular.all(serviceUrl + "/getMerHandOverInfoDetailByHandOverId").post(handOverId); 
    }
	    
	    
	    function getCaacCodeFull(catassetcodeid){
	    	return Restangular.all(serviceUrl + "/getcodefull").post(catassetcodeid); 
	    }
	    
	    function setId(data){
	    	id=data;
	    }
	    function getId(){
	    	return id;
	    }
	    function upgradeLta(ltaNewObj){
		    	return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/upgradeLta").post(ltaNewObj);
	    }
	    function loadMerHandOverNotByConstrForUpgrade(merHandOverId){
			return Restangular.all(RestEndpoint.LONG_TERM_ASSET_URL + "/loadMerHandOverNotByConstrForUpgrade").post(merHandOverId);
		}
	    
	}]);
