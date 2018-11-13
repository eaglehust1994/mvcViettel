
angular.module('MetronicApp').factory('bcChiTietCongNoService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
	 	var serviceUrl = RestEndpoint.QLCN_URL;
	 	
	 	var serviceDetailURL=RestEndpoint.ORDER_CHANGE_GOODS_DETAIL_URL;
	    var factory = {
	        remove:remove,
	        createStock:createStock,
	        getall:getall,
	        doSearch:doSearch,
	        importCongNo:importCongNo,
	        importCongNo1:importCongNo1,
	        exportExcelGrid:exportExcelGrid,
	        exportTongHop:exportTongHop,
	        exportExcelGrid1:exportExcelGrid1,
	        exportExcelGrid2:exportExcelGrid2,
	        updateCongNo:updateCongNo,
	    };
	   
	     return factory;
	 
	    function remove(obj) {
	    	return Restangular.all(serviceUrl+"/orderChangeGoods/remove").post(obj);
        }
	    
	    
	    function doSearch(obj) {
            return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
        }
	  
	    function createStock(obj) {
            return Restangular.all(serviceUrl+"/orderChangeGoods/add").post(obj); 	 
        }
	    function updateCongNo(obj) {
            return Restangular.all(serviceUrl+"/updateCongNo").post(obj); 	 
        }
	    
	    function importCongNo(item) {
			return Restangular.all(serviceUrl + "/importCongNo").post(item);
		}
	    
	    function importCongNo1(item) {
			return Restangular.all(serviceUrl + "/importCongNo1").post(item);
		}
	    
	    function getall() {
            return Restangular.all(serviceUrl+"/getAllDLHaTang").post(); 	 
        }
	    
	    function exportExcelGrid(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid").post(obj); 	 
        }
	    function exportExcelGrid1(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid1").post(obj); 	 
        }
	    function exportExcelGrid2(obj) {
            return Restangular.all(serviceUrl+"/exportExcelGrid2").post(obj); 	 
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
		function exportTongHop(obj) {
            return Restangular.all(serviceUrl+"/exportExcelTongHop").post(obj); 	 
        }
	    

	}]);
