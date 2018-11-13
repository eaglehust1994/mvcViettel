
angular.module('MetronicApp').factory('tblQlCvFileService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.TBL_QL_CV_PTK_FILE;
//	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	    	
	    		doSearch:doSearch,
	        saveFile:saveFile,
	        deleteObj:deleteObj
	    };
	   
	     return factory;
	 
	   
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	    

	    function saveFile(obj) {
	    
	    	
            return Restangular.all(serviceUrl+"/saveFile").post(obj); 	 
            
        }
	    
	    function deleteObj(obj) {
            return Restangular.all(serviceUrl+"/deleteObj").post(obj); 	 
        }
	    function setCode(code) {
			this.code = code;
		}
		function getCode() {
			return this.code;
		}
		function setData(data) {
			this.data = data;
		}
		function getData() {
			return this.data;
		}
	    

	}]);
