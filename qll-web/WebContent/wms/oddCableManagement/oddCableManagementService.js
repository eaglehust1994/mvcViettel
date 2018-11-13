angular.module('MetronicApp').factory('oddCableManagementService', ['$http', '$q', 'RestEndpoint', 'Restangular', '$kWindow', function($http, $q, RestEndpoint, Restangular, $kWindow){
 	var serviceUrl = RestEndpoint.ODD_CABLE;
 	var factory = {
    		 remove:remove,
    		 addCreatNew:addCreatNew,
    		 doSearch : doSearch,
    		 update:update
    };
 
     return factory;
     
//   Hàm xoá cáp lẻ
     function remove(obj) {
        return Restangular.all(serviceUrl+"/remove").post(obj); 	 
     }
     
//  Thêm mới cáp lẻ
    function addCreatNew(obj) {
        return Restangular.all(serviceUrl+"/add").post(obj); 	 
    }
    
//  Sửa cáp lẻ
    function update(obj) {
        return Restangular.all(serviceUrl+"/update").post(obj); 	 
    }
    
//  Tìm kiếm cáp lẻ
    function doSearch(obj) {
        return Restangular.all(serviceUrl+"/doSearch").post(obj); 	 
    }
}]);