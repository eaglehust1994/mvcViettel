angular.module('MetronicApp').factory('groundhandoverservices', ['RestEndpoint', 'Restangular', function(RestEndpoint, Restangular) {
    var serviceUrl = RestEndpoint.CONSTR_GROUND_HANDOVER_SERVICE_URL;
    var item
    var factory = {
        fetchAllGroundHandover: fetchAllGroundHandover,
        saveGroundHandover: saveGroundHandover,
        deleteGroundHandover: deleteGroundHandover,
        deleteOne: deleteOne,
        setData: setData,
        getData: getData,
        exportFile: exportFile,
        appro: appro,
        cancelAprroval : cancelAprroval,
        fetchEmployeeByRole: fetchEmployeeByRole,
        getRoleId: getRoleId
    };


    return factory;
    /* Handle action client on a menu item */
    function cancelAprroval(dto){
    	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
    }
    function setData(data) {
        item = data;
    }

    function getData() {
        return item;
    }

    //approval
    function appro(dto) {
        return Restangular.all(serviceUrl + "/appro").post(dto);
    }

    function fetchEmployeeByRole(obj) {
        return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(obj);
    }

    function fetchAllGroundHandover(obj) {
        return Restangular.all(serviceUrl + "/getAllConstrGroundHandover").post(obj);
    }

    function saveGroundHandover(obj) {
        return Restangular.all(serviceUrl).post(obj);
    }

    function deleteGroundHandover(ids) {
        return Restangular.all(serviceUrl + "/deleteList" +"/put").post(ids);
    }

    function deleteOne(constrGroundHandoverId) {
        return Restangular.all(serviceUrl + "/deleteOne").post(constrGroundHandoverId);
    }

    function exportFile(obj) {
        return Restangular.all(serviceUrl + "/exportPDF").post(obj);
    }

    function getRoleId() {
        return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
    }
}]);