(function () {
    'use strict';

    angular.module('MetronicApp')
            .factory('WindowService', WindowService);

    /* @ngInject */
    function WindowService($q, $kWindow, $rootScope) {
        var service = {};

        service.openEditor = openEditor;
        service.openDonViForm = openDonViForm;
        service.openCongThucForm = openCongThucForm;
        service.openBaoCaoForm = openBaoCaoForm;
        service.openDongKyNhieuDonViForm = openDongKyNhieuDonViForm;
        service.openMoGioiHanForm = openMoGioiHanForm;
        service.openKhoanMucKhoan = openKhoanMucKhoan;
        service.openKhoanMucPhi = openKhoanMucPhi;
        service.openNguonKinhPhi = openNguonKinhPhi;
        service.openSmallEditor = openSmallEditor;
        service.openSearchEditor = openSearchEditor;
        service.openBangMapKhoanMucForm = openBangMapKhoanMucForm;
        service.openSearchTaiKhoanEditor = openSearchTaiKhoanEditor;
        service.openUnitsForm = openUnitsForm;
        service.openModal = openModal;
        service.importExcel = importExcel;
        service.exportExcel = exportExcel;
        service.open=open;

        return service;
        
        /**
         * open Window service
         */
        function open(data){
        	return $kWindow.open({
                options: data.options,
                templateUrl: addVersionToPath(data.templateUrl)

            });
        }

        function openEditor(data, templateUrl, title) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: title,
                    visible: false,
                    width: '1000',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/editorPopup.view.html',
                controller: 'EditorPopupController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    },
                    templateUrl: function () {
                        return templateUrl;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openDonViForm(data) {
            var deferred = $q.defer();
            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Đơn vị",
                    visible: false,
                    width: '650',
                    height: '550',
                    actions: ["Pin","Minimize", "Maximize", "Close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'views/popup/donViForm.view.html',
                controller: 'arunitController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openCongThucForm(data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "I. Tiền và các khoản tương đương tiền",
                    visible: false,
                    width: '800',
                    height: '390',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'views/popup/congThucForm.view.html',
                controller: 'CongThucFormController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openBaoCaoForm (data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Xem báo cáo - Bảng cân đối kế toán",
                    visible: false,
                    width: '400',
                    height: '280',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'views/popup/baoCaoForm.view.html',
                controller: 'BaoCaoFormController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openDongKyNhieuDonViForm (data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Đóng kỳ nhiều đơn vị",
                    visible: false,
                    width: '90%',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/dongMoKy/dongKyNhieuDonVi.view.html',
                controller: 'DongKyNhieuDonViController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openMoGioiHanForm (data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Mở giới hạn nhiều đơn vị",
                    visible: false,
                    width: '90%',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/dongMoKy/moGioiHanNhieuDonVi.view.html',
                controller: 'MoGioiHanNhieuDonViController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openKhoanMucKhoan(data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Mở khoản mục khoán",
                    visible: false,
                    width: '90%',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/danhMuc/khoanMucKhoan/khoanMucKhoan.view.html',
                controller: 'KhoanMucKhoanController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openKhoanMucPhi(data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Mở khoản mục phí",
                    visible: false,
                    width: '90%',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/danhMuc/khoanMucPhi/khoanMucPhi.view.html',
                controller: 'KhoanMucPhiController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openNguonKinhPhi(data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Mở nguồn kinh phí",
                    visible: false,
                    width: '90%',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/nguonKinhPhi/nguonKinhPhi.view.html',
                controller: 'NguonKinhPhiController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openSmallEditor(width, height, data, templateUrl, title) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: title,
                    visible: false,
                    width: width,
                    height: height,
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/editorPopup.view.html',
                controller: 'EditorPopupController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    },
                    templateUrl: function () {
                        return templateUrl;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openSearchEditor(data, templateUrl, title) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: title,
                    visible: false,
                    width: '900',
                    height: '350',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/timKiemPopup.view.html',
                controller: 'TimKiemPopupController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    },
                    templateUrl: function () {
                        return templateUrl;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openSearchTaiKhoanEditor(data, templateUrl, title) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: title,
                    visible: false,
                    width: '900',
                    height: '350',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/timKiemTaiKhoanPopup.view.html',
                controller: 'TimKiemTaiKhoanPopupController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    },
                    templateUrl: function () {
                        return templateUrl;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openBangMapKhoanMucForm(data) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: "Bảng map khoản mục",
                    visible: false,
                    width: '650',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/danhMuc/bangMapKhoanMuc/bangMapKhoanMucForm.view.html',
                controller: 'BangMapKhoanMucController1',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openUnitsForm(data, title) {
            var deferred = $q.defer();

            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: (title != undefined && title != "") ? title : "Đơn vị",
                    visible: false,
                    width: '650',
                    height: '550',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: (data.showCheckbox != undefined && data.showCheckbox == true) ? 'erp/popup/units_checkbox_view.html' : 'erp/popup/units_view.html',
                controller: 'UnitsController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function openModal(data, templateUrl, title) {
            var deferred = $q.defer();
            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: title,
                    visible: false,
                    width: '850',
                    height: '380',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/grid_view.html',
                controller: 'ModalController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    },
                    templateUrl: function () {
                        return templateUrl;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }
        
        function importExcel(data, templateUrl, title) {
            var deferred = $q.defer();
            var modalInstance = $kWindow.open({
                options: {
                    modal: true,
                    title: title,
                    visible: false,
                    width: '550',
                    height: '250',
                    actions: ["Minimize", "Maximize", "close"],
                    open: function () {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'erp/popup/upload_view.html',
                controller: 'UploadController',
                controllerAs: 'vm',
                resolve: {
                    data: function () {
                        return data;
                    },
                    templateUrl: function () {
                        return templateUrl;
                    }
                }
            });

            modalInstance.result.then(function (result) {
                deferred.resolve(result);
            });

            return deferred.promise;
        }

        function exportExcel(rows, fileName) {
            var workbook = new kendo.ooxml.Workbook({
                sheets: [
                    {
                        columns: [
                            { autoWidth: true },
                            { autoWidth: true }
                        ],
                        title: "Orders",
                        rows: rows
                    }
                ]
            });
            if (fileName != undefined && fileName == "") {
                fileName = "Export data";
            }
            fileName += " " + getDateTime() + ".xlsx";
            kendo.saveAs({dataURI: workbook.toDataURL(), fileName: fileName});
        }

        function getDateTime() {
            var now     = new Date();
            var year    = now.getFullYear();
            var month   = now.getMonth()+1;
            var day     = now.getDate();
            var hour    = now.getHours();
            var minute  = now.getMinutes();
            var second  = now.getSeconds();
            if(month.toString().length == 1) {
                var month = '0'+month;
            }
            if(day.toString().length == 1) {
                var day = '0'+day;
            }
            if(hour.toString().length == 1) {
                var hour = '0'+hour;
            }
            if(minute.toString().length == 1) {
                var minute = '0'+minute;
            }
            if(second.toString().length == 1) {
                var second = '0'+second;
            }
            var dateTime = day+'-'+month+'-'+year+' '+hour+'-'+minute+'-'+second;
            return dateTime;
        }
    }
})();
