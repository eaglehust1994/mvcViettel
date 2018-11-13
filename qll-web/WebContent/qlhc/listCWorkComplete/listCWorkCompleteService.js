angular.module('MetronicApp').factory('listCWorkCompleteService', ['$http', '$q', 'RestEndpoint', 'Restangular', 'Constant', '$rootScope',
    function($http, $q, RestEndpoint, Restangular, Constant, $rootScope) {

        var service = RestEndpoint.CWORK_COMPLETE_SERVICE_Url;
        var serviceChil = RestEndpoint.CONSTR_WORK_COMP_CONF_LIST_SERVICE_Url;
        var factory = {
            goTo: goTo,
            fetchAllItemByConstructId: fetchAllItemByConstructId,
            // fetchAllItem : fetchAllItem,
            deleteListItem: deleteListItem,
            getListChilById: getListChilById,
            getListChilExistById: getListChilExistById,
            getListEmployeeByRole: getListEmployeeByRole,
            updateAllReport: updateAllReport,
            createNewReport: createNewReport,
            downloadFileZip: downloadFileZip,
            exportOne: exportOne,
            signCA: signCA,
            exportDoc: exportDoc,
            downloadFileDOCZip: downloadFileDOCZip,
            getRoleId: getRoleId,
            getListEmployeeByConstruction : getListEmployeeByConstruction
        };
        return factory;
        /* Handle action client on a menu item */


        function goTo(menuKey) {

            var template = Constant.getTemplateUrl(menuKey);
            postal.publish({
                channel: "Tab",
                topic: "open",
                data: template
            });
        }

        function fetchAllItemByConstructId(id) {
            return Restangular.one(service + "/constructId/" + id).get();
        }

        function downloadFileZip(listObj) {
            return Restangular.all(service + "/exportList/").post(listObj);
        }

        function downloadFileDOCZip(listObj) {
            return Restangular.all(service + "/exportListDOC/").post(listObj);
        }

        function exportOne(obj) {
            return Restangular.all(service + "/exportOne/").post(obj);
        }

        function getListEmployeeByConstruction(item) {
			return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getListEmployeeByConstruction").post(item);	
		}
        function exportDoc(obj) {
            return Restangular.all(service + "/exportDoc/").post(obj);
        }
        //-----------------------------------------------------------------
        // function fetchAllItem(id) {	    	
        //     	return Restangular.all(service + "/constructId/" +id).getList();	 	   
        // }
        function getListChilById(id) {
            return Restangular.one(serviceChil + "/byConstrId/" + id).get();
        }

        function getListChilExistById(listId) {

            return Restangular.all(serviceChil + "/cWorkExistByConstrId/").post(listId);
        }

        function deleteListItem(listId) {
            return Restangular.all(service + "/deleteList" +"/put").post(listId);
        }

        function getListEmployeeByRole(object) {
            return Restangular.all(RestEndpoint.REPORT_RESULT_QUALITY_MONITORING_URL).post(object);
        }

        function signCA(object) {
            return Restangular.all(service + "/signCA").post(object);
        }

        function updateAllReport(obj) {
            return Restangular.all(service + "/put").post(obj);
        }

        function createNewReport(obj) {
            return Restangular.all(service).post(obj);
        }

        function getRoleId() {
            return Restangular.all(RestEndpoint.CAT_EMPLOYEE_SERVICE_URL + "/getRoleId").getList();
        }

    }
]);