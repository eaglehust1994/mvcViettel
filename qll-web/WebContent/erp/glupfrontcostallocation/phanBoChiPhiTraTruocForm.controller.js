(function() {
    'use strict';

    var controllerId = 'PhanBoChiPhiTraTruocFormController';

    angular.module('MetronicApp').controller(controllerId, PhanBoChiPhiTraTruocFormController);

    /* @ngInject */
    function PhanBoChiPhiTraTruocFormController($scope, $rootScope, $timeout, Constant, gettextCatalog, WindowService, kendoConfig) {
        var vm = this;

        vm.showDetail = false;
        vm.showDetailDonVi = false;
        vm.add = add;
        vm.edit = edit;
        vm.showGrid = showGrid;
        vm.save = save;
        vm.remove = remove;
        vm.detail = detail;
        vm.showTaiKhoanForm = showTaiKhoanForm;

        $scope.showDonViForm = function(title){
            WindowService.openSearchEditor({}, "", title);
        }

        function showTaiKhoanForm() {
            WindowService.openSearchTaiKhoanEditor({}, "", "Tìm tài khoản");
        }

        function add() {
            vm.showDetail = true;
        }

        function detail() {
            if (vm.phieuKeToanGrid.select().length > 0) {
                vm.showDetail = true;
            } else {
                toastr.warning("Bạn cần chọn một bản ghi trước");
            }
        }

        function edit() {
            vm.showDetail = true;
        }

        function showGrid() {
            vm.showDetail = false;
        }

        function save() {
            if(vm.showDetail){
                if(vm.validator.validate()){
                    toastr.success("Lưu thành công!");
                }
            }else {
                toastr.success("Lưu thay đổi thành công!");
            }

        }

        function remove() {
            if (vm.phieuKeToanGrid.select().length > 0) {
                //var dateItem = vm.phieuKeToanGrid.dataItem(vm.phieuKeToanGrid.select());
                //vm.phieuKeToanGrid.dataSource.remove(dataItem);
                toastr.success("Xóa thành công!");
            } else {
                toastr.warning("Bạn cần chọn một bản ghi trước");
            }
        }
    }
})();