(function() {
    'use strict';

    var controllerId = 'MenuController';

    angular.module('MetronicApp').controller(controllerId, MenuController);

    /* @ngInject */
    function MenuController($scope, $rootScope, Constant, $http,Restangular) {
        var vm = this;

        /*Get user-info*/
        //$http.get('user-info').success(function(casUser){
        //    /*additional info from service*/
        //    $http.get(Constant.BASE_SERVICE_URL + 'user-info/user/' + casUser.userName).success(function(data) {
        //        var user = {};
        //        user.casUser = casUser;
        //        user.srvUser = data;
        //
        //        Constant.setUser(user);
        //
        //    });
        //});
        $http.get(url).success(function(user){
            console.log(user);
        });

        Restangular.one("authenServiceRest/loadMenu").get().then(function(response){
            $rootScope.isAuthenticated=true;
            vm.menuObjects=response.parentMenu;
        }).catch(function (err) {
            //console.log(err);
            //if(err.status == 489){
            //    window.location =err.data.data;
            //}

            console.log('Không kết nối dữ liệu ' + err.message);
        });


        vm.goTo = goTo;

        vm.getMenuText=function(menuKey){
            try {
                var template = Constant.getTemplateUrl(menuKey);
                return template.title;
            }catch(err){
                return "N/A";
            }
        }

        /* Handle action client on a menu item */
        function goTo(menuKey) {

            var hasPerm = true;

            if (hasPerm) {
                var template = Constant.getTemplateUrl(menuKey);
                setArParams(menuKey);
                setIdCheckbox(menuKey);
                setApParams(menuKey);
                postal.publish({
                    channel : "Tab",
                    topic   : "open",
                    data    : template
                });

                if (menuKey === "Asset_CatAssetDetail"){
                    $rootScope.isCreatAsset = true;
                }
            } else {
                //toastr.error(gettextCatalog.getString("Tài khoản đăng nhập hiện tại không được phép truy cập vào chức năng này!"));
            }

        }
        function setIdCheckbox(key){
            switch(key) {
                case "AR_INVOICE_HEADER":
                    $rootScope.nameCheckbox = "isSyncRecord" ;
                    $rootScope.nameCheckbox1 = "isDetailSync";
                    $rootScope.nameCheckbox2 = "isEstimate" ;
                    break;
                case "AR_DTTKDS":
                    $rootScope.nameCheckbox = "isSyncRecord1" ;
                    $rootScope.nameCheckbox1 = "isDetailSync1";
                    $rootScope.nameCheckbox2 = "isEstimate1" ;
                    break;
                case "AR_POSTPAID":
                    $rootScope.nameCheckbox = "isSyncRecord2" ;
                    $rootScope.nameCheckbox1 = "isDetailSync2";
                    $rootScope.nameCheckbox2 = "isEstimate2" ;
                    break;
            }
        }

        function setArParams(key) {
            switch(key) {
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
            switch(key) {
                case "AP_BANK_PAYMENT_BILL":
                    $rootScope.cdocumentTypeId = '4';
                    break;
                case "AP_CASH_PAYMENT_BILL":
                    $rootScope.cdocumentTypeId = '5';
                    break;
                default:
            }
        }
    }
})();