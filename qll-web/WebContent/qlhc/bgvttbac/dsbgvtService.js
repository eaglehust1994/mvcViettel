angular.module('MetronicApp').factory('dsbgvtService', ['$http', '$q', 'Constant', 'RestEndpoint', 'Restangular', function ($http, $q, Constant, RestEndpoint, Restangular) {

    var SERVICE_URI = Constant.SERVICE_URL + 'cProfitCenterRsService/cProfitCenter';
    var SERVICE_GET_ALL = '';
    var factory = {
    	        getDsbgvt: getDsbgvt,
    	        getConTruction: getConTruction,
    	        deleteMaterial: deleteMaterial,
    	        setItem:setItem,
    			getItem:getItem,
    			setItem1:setItem1,
    			getRoleId:getRoleId,
    			getItem1:getItem1
    			
    };

    return factory;
    
    function setItem(item){
		this.item = item;
	}
	function getItem(){
		return this.item;
	}
	
	function setItem1(item1){
		this.item1 = item1;
	}
	function getItem1(){
		return this.item1;
	}
	
	function getRoleId() {
        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList(); 	 
    }
    
    function getConTruction(object) {
    	return Restangular.all(RestEndpoint.INTERGRATED_CONTRACT_NAME_SERVICE_URL).post(object);
    	
    }
    
    function getDsbgvt(object) {
    	return Restangular.all(RestEndpoint.LIST_MATERIALT_SERVICE_URL).post(object);
    	
    }
    
    function deleteMaterial(object) {
    	return Restangular.all(RestEndpoint.DELETE_A_MATERIAL_SERVICE_URL).post(object);
    	
    }
    
}]);
