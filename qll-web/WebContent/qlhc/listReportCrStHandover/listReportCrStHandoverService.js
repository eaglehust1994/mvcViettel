angular.module('MetronicApp').factory('listReportCrStHandoverService', ['$http', '$q', 'RestEndpoint', 'Restangular', 'Constant', '$rootScope',
    function($http, $q, RestEndpoint, Restangular, Constant, $rootScope) {

        var service = RestEndpoint.CURRENT_STATE_HANDOVER_SERVICE_Url;
        var factory = {
            goTo: goTo,
            fetchAllItemByConstructId: fetchAllItemByConstructId,
            deleteListItem: deleteListItem,
            downloadFileZip: downloadFileZip,
            exportOne: exportOne,
            theApproval: theApproval,
            exportDoc: exportDoc,
            appro: appro,
            cancelAprroval : cancelAprroval,
            downloadFileDOCZip: downloadFileDOCZip,
            getOneItemById: getOneItemById,
            getRoleId: getRoleId
        };
        return factory;
        /* Handle action client on a menu item */

        function cancelAprroval(dto){
        	return Restangular.all(RestEndpoint.APPR_SIGN_URL + "/cancelApproval").post(dto);
        }
        function goTo(menuKey) {

            var template = Constant.getTemplateUrl(menuKey);
            postal.publish({
                channel: "Tab",
                topic: "open",
                data: template
            });
        }

        function getOneItemById(id) {
            return Restangular.one(service, id).get();
        }

        function getRoleId() {
            return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
        }

        function fetchAllItemByConstructId(id) {
            return Restangular.one(service + "/listCurrentStateHandover/" + id).get();
        }
        //-----------------------------------------------------------------
        function fetchAllItem(id) {
            return Restangular.all(service + "/getAllByAdvanceRequestId/" + id).getList();
        }

        function deleteListItem(listId) {

            return Restangular.all(service + "/deleteList" + "/put").post(listId);
        }

        function exportOne(id) {
            return Restangular.all(service + "/export").post(id);
        }

        function exportDoc(id) {
            return Restangular.all(service + "/exportDoc").post(id);
        }

        function downloadFileZip(listid) {
            return Restangular.all(service + "/exportList").post(listid);
        }

        function downloadFileDOCZip(listid) {
            return Restangular.all(service + "/exportListDOC").post(listid);
        }

        function theApproval(id) {
            return Restangular.all(service + "/approval").post(id);
        }

        function appro(obj) {
            return Restangular.all(service + "/appro").post(obj);
        }

    }
]);