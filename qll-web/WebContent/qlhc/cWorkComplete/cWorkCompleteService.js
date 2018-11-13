angular.module('MetronicApp').factory('cWorkCompleteService', ['$http', '$q', 'RestEndpoint', 'Restangular', 'Constant', '$rootScope',
    function($http, $q, RestEndpoint, Restangular, Constant, $rootScope) {

        var service = RestEndpoint.CURRENT_STATE_HANDOVER_LIST_SERVICE_Url;
        var serviceUrl_GetMonitor = RestEndpoint.REPORT_RESULT_QUALITY_URL;
        var servicePe = RestEndpoint.CURRENT_STATE_HANDOVER_SERVICE_Url;
        var factory = {
            goTo: goTo,
            fetchAllItem: fetchAllItem,
            getListEmployeeByRole: getListEmployeeByRole,
            updateAllReport: updateAllReport,
            createNewReport: createNewReport,
            deleteMutiRow: deleteMutiRow,
            exportNewReport: exportNewReport
        };

        //-------------------------------------------------------
        return factory;
        /* Handle action client on a menu item */

        //-----------------------------------------------------------------
        function fetchAllItem(id) {
            return Restangular.all(service + "/listCurrentHandoverByCrStHID/" + id).getList();
        }

        function getListEmployeeByRole(object) {
            return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);
        }

        function updateAllReport(obj) {

            return Restangular.all(servicePe +"/put").post(obj);
        }

        function createNewReport(obj) {
            return Restangular.all(servicePe).post(obj);
        }

        function deleteMutiRow(obj) {
            return Restangular.all(service + "/removeMutiRecord" + "/put").post(obj);
        }

        function exportNewReport(obj) {
            return Restangular.all(servicePe + "/export").post(obj);
        }

    }
]);