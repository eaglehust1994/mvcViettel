(function () {
    'use strict';

    var controllerId = 'PhanBoChiPhiTraTruocController';

    angular.module('MetronicApp').controller(controllerId, PhanBoChiPhiTraTruocController);

    /* @ngInject */
    function PhanBoChiPhiTraTruocController($scope, $rootScope, $timeout, Constant, gettextCatalog, kendoConfig, WindowService) {
        var vm = this;

        $scope.displayState = {
            showGrid: 0x01,
            showSearch: 0x02,
            showDetail: 0x04
        };

        $scope.state = $scope.displayState.showGrid;

        $scope.isShow = function (key) {
            return ($scope.state & $scope.displayState[key]) != 0;
        }

        vm.add = add;
        vm.edit = edit;
        vm.showGrid = showGrid;
        vm.save = save;
        vm.remove = remove;
        vm.detail = detail;
        vm.search = search;
        vm.popup = popup;

        function popup() {
            WindowService.openEditor({}, 'app/phanBoChiPhiTraTruoc/phanBoChiPhiTraTruocForm.view.html', "Phân bổ chi phí trả trước");
        }

        vm.validatorOptions = kendoConfig.get('validatorOptions');

        vm.dataSource = new kendo.data.DataSource({
            pageSize: 20,
            transport: {
                type: "json",
                read:  {
                    url: "app/phanBoChiPhiTraTruoc/phanBoChiPhiTraTruoc.json",
                    dataType: "json"
                }
            }
        });

        vm.gridOptions = kendoConfig.getGridOptions({
            autoBind: true,
            dataSource: vm.dataSource,
            //height: 450,
            columns: [{
                title: "<input type='checkbox' />",
                template: '<input type="checkbox">',
                width: 30
            },{
                title: "Đơn vị",
                field: "donVi",
                width: 180,
            }, {
                title: "Phòng ban",
                field: "phongBan",
                width: 100
            }, {
                title: "Loại chứng từ",
                field: "loaiChungTu",
                width: 150
            }, {
                title: "Số chứng từ",
                field: "soCT",
                width: 150
            }, {
                title: "Ngày chứng từ",
                field: "ngayChungTu",
                width: 150
            }, {
                title: "Kỳ phân bổ",
                field: "kyPhanBo",
                width: 150
            }, {
                title: "Kỳ hạch toán",
                field: "kyHachToan",
                width: 150
            }, {
                title: "Ngày hạch toán",
                field: "ngayHachToan",
                width: 150
            }, {
                title: "Số CTGS",
                field: "soCTGS",
                width: 150
            }, {
                title: "Số THTT",
                field: "soTHTT",
                width: 150
            }, {
                title: "Nội dung THTT",
                field: "noiDungTHTT",
                width: 150
            }, {
                title: "Nội dung chứng từ",
                field: "soCTGS",
                width: 150
            }, {
                title: "Tiền tệ",
                field: "tienTe",
                width: 150
            }, {
                title: "Tài khoản",
                field: "taiKhoan",
                width: 150
            }, {
                title: "Nguồn kinh phí",
                field: "nguonKinhPhi",
                width: 150
            }, {
                title: "Khoản mục",
                field: "khoanMuc",
                width: 150
            }, {
                title: "Vụ việc",
                field: "vuViec",
                width: 150
            }, {
                title: "Tổng tiền nguyên tệ",
                field: "tongTienNguyenTe",
                width: 150
            }, {
                title: "Trạng thái",
                field: "trangThai",
                width: 150,
                template: function (item) {
                    if (item.trangThai == 1) {
                        return "Đã phân bổ";
                    } else {
                        return "Chưa phân bổ"
                    }
                }
            }]
        });

        function add() {
            $scope.state = $scope.displayState.showDetail;
        }

        function detail() {
            if (vm.nhomDonViGrid.select().length > 0) {
                $scope.state = $scope.displayState.showDetail;
            } else {
                toastr.warning("Bạn cần chọn một bản ghi trước");
            }
        }

        function edit() {
            $scope.state = $scope.displayState.showDetail;
        }

        function showGrid() {
            if ($scope.state == $scope.displayState.showDetail) {
                $scope.state = $scope.displayState.showGrid;
            }
            else if ($scope.state == $scope.displayState.showGrid) {
                $scope.state = $scope.displayState.showDetail;
            }
        }

        function save() {
            if(vm.showDetail){
                if(vm.validator.validate()){
                    toastr.success("Lưu thành công!");
                }
            }else {
                toastr.success("Lưu thành công!");
            }s
        }

        function remove() {
            if (vm.nhomDonViGrid.select().length > 0) {
                toastr.success("Xóa thành công!");
            } else {
                toastr.warning("Bạn cần chọn một bản ghi trước");
            }
        }

        function search() {
            $scope.state = $scope.displayState.showSearch;
        }
    }
})();