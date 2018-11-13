(function() {
    'use strict';

    var controllerId = 'PhanBoChiPhiTraTruocSearchController';

    angular.module('MetronicApp').controller(controllerId, PhanBoChiPhiTraTruocSearchController);

    /* @ngInject */
    function PhanBoChiPhiTraTruocSearchController($scope, $rootScope, $timeout, Constant, gettextCatalog, WindowService, kendoConfig) {
        var vm = this;

        vm.showDetail = false;
        vm.showDetailDonVi = false;
        vm.add = add;
        vm.edit = edit;
        vm.showGrid = showGrid;
        vm.save = save;
        vm.remove = remove;
        vm.detail = detail;

        $scope.showDonViForm = function(title){
            WindowService.openSearchEditor({}, "", title);
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
                toastr.success("Xóa thành công!");
            } else {
                toastr.warning("Bạn cần chọn một bản ghi trước");
            }
        }
    }
})();