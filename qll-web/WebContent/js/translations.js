angular.module('gettext').run(['gettextCatalog', function (gettextCatalog) {
/* jshint -W100 */
    gettextCatalog.setStrings('en', {"Danh mục":"Category","Khoản thu":"Receivable","Thanh toán":"Payable","Trang chủ":"Dashboard"});
    gettextCatalog.setStrings('vi', {"Danh mục":"Danh mục","Khoản thu":"Khoản thu","Thanh toán":"Thanh toán","Trang chủ":"Trang chủ"});
/* jshint +W100 */
}]);