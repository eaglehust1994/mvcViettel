/**
 * Created by hungle on 9/26/16.
 */
angular.module('MetronicApp').factory('GlYearAndPeriodService', ['$http', '$q', function ($http, $q) {

    var SERVICE_BASE = "http://42.112.29.41:8080/erp-service"
    var SERVICE_URI = SERVICE_BASE+'/cYearRsService/cYear';
    var SERVICE_GET_ALL = '';
    var SERVICE_GET_BY_ID = '';
    var SERVICE_DELETE = '/';
    var SERVICE_UPDATE = '';
    var SERVICE_ADD = '';
    var SERVICE_FIND = '/find';
    var SERVICE_SEARCH_PARAM = ['value', 'name'];

    var factory = {
        urlBase:getServiceBase,
        uri:getServiceURI,
        getAll:getAll,
        create: create,
        update: update,
        remove: remove,
        search: search,
        createPeriod:createPeriod
    };

    return factory;

    function getAll() {
        var deferred = $q.defer();
        $http.get(SERVICE_URI + SERVICE_GET_ALL)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while fetching '+SERVICE_URI);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    /*
     * $http({ method : 'POST', url :
     * 'http://42.112.29.41:8080/erp-service/adOrgGroupServiceRest/adOrgGroup',
     * data : JSON.stringify($scope.units) }).then( function (response) {
     * deferred.resolve(response.data); }, function(errResponse){
     * console.error('Error while creating planprice');
     * deferred.reject(errResponse); } );
     */

    function create(object) {
        var deferred = $q.defer();
        $http.post(SERVICE_URI + SERVICE_ADD, object)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while creating :'+SERVICE_URI);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function update(object) {
        var deferred = $q.defer();
        $http.put(SERVICE_URI + SERVICE_UPDATE, object)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while updating :'+SERVICE_URI);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function remove(object){
        var deferred = $q.defer();
        $http.delete(SERVICE_URI + SERVICE_DELETE + object).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while deleting '+SERVICE_URI);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function search(object) {
        var deferred = $q.defer();
        var url = SERVICE_URI + SERVICE_FIND;
        url += filterSearchParam(object);
        $http.get(url)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while finding '+SERVICE_URI);
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

    function createPeriod(object){
        var deferred = $q.defer();
        console.log("create period "+JSON.stringify(object));
        $http.post(SERVICE_BASE + "/cPeriodRsService/cPeriod", object)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (errResponse) {
                console.error('Error while creating :'+SERVICE_URI);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }




    function getServiceBase(){
        return SERVICE_BASE;
    }
    function getServiceURI(){
        return SERVICE_URI;
    }



}]);