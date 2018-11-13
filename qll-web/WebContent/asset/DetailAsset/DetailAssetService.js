
angular.module('MetronicApp').factory('detailassetService', ['$http', '$q', 'RestEndpoint', 'PopupConst', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, PopupConst, Restangular, $kWindow){
	var obj;
	var check=false;
	var serviceUrl = RestEndpoint.ESTIMATES_WORK_ITEMS_SERVICE_URL;   
	var factory = {
			fetchAllTwo : fetchAllTwo,
			fetchAllOne : fetchAllOne,
			fetchAllFour:fetchAllFour,
			  setData:setData,
			  getData:getData,
			  setFrist:setFrist,
			  getFrist:getFrist
	    };
	    return factory;
	    function setFrist(data ){
	    	check=data;
	    }
	    
	    function getFrist(){
	    	return check;
	    }
	    function setData(data){
	    	obj=data;
	    }
	    
	    function getData(){
	    	return obj;
	    }
	    
	    function fetchAllOne(id) {	    	
		 	return Restangular.all("longTermAssetServiceRest/getSerial/"+id).getList();	 	   
		}
	    
	    function fetchAllTwo(id) {	    	
		 	return Restangular.all("longTermAssetServiceRest/getNoneSerial/"+id).getList();	 	   
		}
	    
	    function fetchAllFour(id) {	    	
		 	return Restangular.all("longTermAssetServiceRest/getDistri/"+id).getList();	 	   
		}
	}]);
