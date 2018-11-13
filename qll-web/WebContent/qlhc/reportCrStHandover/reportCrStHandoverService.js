angular.module('MetronicApp').factory('reportCrStHandoverService', ['$http', '$q', 'RestEndpoint', 'Restangular', 'Constant', '$rootScope',
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
        function goTo(menuKey) {
            var template = Constant.getTemplateUrl;
            setArParams(menuKey);
            setIdCheckbox(menuKey);
            setApParams(menuKey);
            postal.publish({
                channel: "Tab",
                topic: "open",
                data: template
            });
        }

        function setIdCheckbox(key) {
            switch (key) {
                case "AR_INVOICE_HEADER":
                    $rootScope.nameCheckbox = "isSyncRecord";
                    $rootScope.nameCheckbox1 = "isDetailSync";
                    $rootScope.nameCheckbox2 = "isEstimate";
                    break;
                case "AR_DTTKDS":
                    $rootScope.nameCheckbox = "isSyncRecord1";
                    $rootScope.nameCheckbox1 = "isDetailSync1";
                    $rootScope.nameCheckbox2 = "isEstimate1";
                    break;
                case "AR_POSTPAID":
                    $rootScope.nameCheckbox = "isSyncRecord2";
                    $rootScope.nameCheckbox1 = "isDetailSync2";
                    $rootScope.nameCheckbox2 = "isEstimate2";
                    break;
            }
        }

        function setArParams(key) {
            switch (key) {
                case "AR_INVOICE_HEADER":
                    $rootScope.invoiceDocTypeId = 8;
                    break;
                case "AR_DTTKDS":
                    $rootScope.invoiceDocTypeId = 18;
                    break;
                case "AR_POSTPAID":
                    $rootScope.invoiceDocTypeId = 19;
                    break;
                default:
            }
        }

        function setApParams(key) {
            switch (key) {
                case "AP_BANK_PAYMENT_BILL":
                    $rootScope.cdocumentTypeId = '4';
                    break;
                case "AP_CASH_PAYMENT_BILL":
                    $rootScope.cdocumentTypeId = '5';
                    break;
                default:
            }
        }
        //-----------------------------------------------------------------
        function fetchAllItem(id) {
            return Restangular.all(service + "/listCurrentHandoverByCrStHID/" + id).getList();
        	//return Restangular.all(service + "/listCurrentHandoverByCrStHID/").post(id);
        }

        function getListEmployeeByRole(object) {
            return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);
        }

        function updateAllReport(obj) {
            console.log(JSON.stringify(obj));
            return Restangular.all(servicePe + "/put").post(obj);
        }

        function createNewReport(obj) {
            return Restangular.all(servicePe).post(obj);
        }

        function deleteMutiRow(obj) {
            return Restangular.all(service + "/removeMutiRecord" +"/put").post(obj);
        }

        function exportNewReport(obj) {
            return Restangular.all(servicePe + "/export").post(obj);
        }
    }
]);