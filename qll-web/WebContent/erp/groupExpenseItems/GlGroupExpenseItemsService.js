angular.module('MetronicApp').factory('GlGroupExpenseItemsService', ['$http', '$q', 'Constant', function ($http, $q, Constant) {

    var SERVICE_URI = Constant.SERVICE_URL + 'cCostTypeGroupRsService/cCostTypeGroup';
    var SERVICE_GET_ALL = '';
    var SERVICE_GET_BY_ID = '/';
    var SERVICE_FIND = '/find';
    var SERVICE_ADD = '';
    var SERVICE_UPDATE = '';
    var SERVICE_DELETE = '/';
    var SERVICE_SEARCH_PARAM = ['value', 'name'];

    var factory = {
        fetchAllObject: fetchAllObject,
        findObject: findObject,
        createObject: createObject,
        updateObject: updateObject,
        deleteObject: deleteObject
    };

    return factory;

    function fetchAllObject() {
        var deferred = $q.defer();
        $http.get(SERVICE_URI + SERVICE_GET_ALL)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching object');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function findObject(object) {
        var deferred = $q.defer();
        var url = SERVICE_URI + SERVICE_FIND + filterSearchParam(object);
        $http({
            method: 'GET',
            url: url,
            headers: { 'Content-Type': 'Accept: application/json' }
        }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching object');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createObject(object) {
        var deferred = $q.defer();
        $http.post(SERVICE_URI + SERVICE_ADD, object)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while creating object');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function updateObject(object) {
        var deferred = $q.defer();
        $http.put(SERVICE_URI + SERVICE_UPDATE, object)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while updating object');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteObject(object) {
        var deferred = $q.defer();
        $http.delete(SERVICE_URI + SERVICE_DELETE + object)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while deleting object');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function filterSearchParam(object) {
        var param = "";
        SERVICE_SEARCH_PARAM.filter(function (key) {
            if (object[key] != undefined && object[key] != '') {
                param += (param == "") ? key + "=" + object[key] : "&" + key + "=" + object[key];
            }
        });
        return (param != "") ? "?" + param : "";
    }

}]);
