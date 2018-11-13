(function() {
    'use strict';

    angular.module('MetronicApp').service('EXRDService', function() {
        var service = this;
        // Service đánh giá chênh lẹch tỷ giá
        service.urlService = "http://42.112.29.41:8080/erp-service/glRevaluationRsService/glRevaluation";

        // Service danh mục đơn vị
        service.urlAdOrg = "http://42.112.29.41:8080/erp-service/adOrgServiceRest/adOrg";

        // Service danh mục phòng ban
        service.urlcDepartment = "http://42.112.29.41:8080/erp-service/cDepartmentRsServiceRest/cDepartment";

        // Service danh mục loại chứng từ
        service.urlcDocumentType = "http://42.112.29.41:8080/erp-service/cDocumentTypeRsService/cDocumentType";

        // Service danh mục nhóm chứng từ
        service.urlglRevaluationCategory = "http://42.112.29.41:8080/erp-service/glRevaluationCategoryRsService/glRevaluationCategory";

        // Service danh mục tiền tệ
        service.urlcCurrency = "http://42.112.29.41:8080/erp-service/cCurrencyRsService/cCurrency";

        // Những param phục vụ cho việc search dữ liệu
        service.SERVICE_SEARCH_PARAM = [];

        // Function thực hiện việc nối chuỗi string phục vụ tìm kiếm dữ liệu
        service.filterSearchParam = function(object) {
            var param = "";
            if (service.SERVICE_SEARCH_PARAM.length <= 0) return;
            service.SERVICE_SEARCH_PARAM.filter(function(key) {
                if (object[key] !== undefined && object[key] !== '') {
                    param += (param === "") ? key + "=" + object[key] : "&" + key + "=" + object[key];
                }
            });
            return (param !== "") ? "?" + param : "";
        };

        // Function trả về định dạng format datetime cần lấy
        service.formatDateTime = function(value, format) {
            if (value !== undefined && value !== '')
                return kendo.toString(kendo.parseDate(value, 'dd-MM-yyyy'), format);
            else
                return null;
        };
    });

    angular.module('MetronicApp').service('EXRDSearch', function() {
        var services = this;

        // Cấu hình control date ranger ngày chứng từ
        services.configTransDate = function(scope) {
            scope.maxTransDate = new Date();
            scope.minTransDate = new Date();
            // Set min cho control đến ngày khi từ ngày thay đổi
            scope.dateChangedTransDateFrom = function() {
                scope.minTransDate = kendo.parseDate(scope.searchParam.transDateFrom, 'dd-MM-yyyy');
            };
            // Set max cho control từ ngày khi đến ngày thay đổi
            scope.dateChangedTransDateTo = function() {
                scope.maxTransDate = kendo.parseDate(scope.searchParam.transDateTo, 'dd-MM-yyyy');
            };
        };

        // Cấu hình control date ranger ngày hạch toán
        services.configdateAcct = function(scope) {
            scope.maxdateAcct = new Date();
            scope.mindateAcct = new Date();
            // Set min cho control đến ngày khi từ ngày thay đổi
            scope.dateChangeddateAcctFrom = function() {
                scope.mindateAcct = kendo.parseDate(scope.searchParam.dateAcctFrom, 'dd-MM-yyyy');
            };
            // Set max cho control từ ngày khi đến ngày thay đổi
            scope.dateChangeddateAcctTo = function() {
                scope.maxdateAcct = kendo.parseDate(scope.searchParam.dateAcctTo, 'dd-MM-yyyy');
            };
        };

        return services;
    });

    angular.module('MetronicApp').directive('maskedDatePicker', [function() {
        return {
            link: function(scope, elem, attrs) {
                $(elem).kendoMaskedTextBox({
                    mask: "00-00-0000"
                });
                $(elem).removeClass("k-textbox");
            }
        };
    }]);

    angular.module('MetronicApp').controller('EvaluateExchangeRateDifferenceCtr',
        function($scope, $http, EXRDService, EXRDSearch) {
            var ctrl = this;
            // Object lưu thông tin những trường cần thực hiện thêm mới
            ctrl.objectsData = {};

            // Varriable xác định việc hiển thị form thêm mới
            ctrl.showCreate = false;

            // Varriable xác định việc hiển thị form tìm kiếm
            ctrl.showSearch = true;

            // Object chứa những tham số cần tìm kiếm
            ctrl.searchParam = {};

            // Function khởi chạy khi DOM load xong
            ctrl.init = function() {
                // Cấu hình DateTime cho các control Datetime
                ctrl.datePickerOptions = {
                    format: "dd-MM-yyyy",
                    parseFormats: ["yyyy-MM-dd", "dd-MM-yyyy"]
                };

                // Cấu hình control date ngày chứng từ
                EXRDSearch.configTransDate(ctrl);

                // Cấu hình control date ngày hạch toán
                EXRDSearch.configdateAcct(ctrl);

                // Cấu hình control nhập liệu kiểu số tiền
                ctrl.maskPrices = {
                    mask: "0000000000",
                    promptChar: " ",
                    unmaskOnPost: true
                };

                // Cấu hình autocomplete đơn vị
                ctrl.adOrgIdOptions = {
                    dataTextField: "name",
                    select: function(e) {
                        var dataItem = this.dataItem(e.item.index());
                        ctrl.objectsData.adOrgId = dataItem.adOrgId;
                        ctrl.objectsData.adOrgName = dataItem.name;
                    },
                    pageSize: 10,
                    dataSource: {
                        serverFiltering: true,
                        transport: {
                            read: function(options) {
                                return $http.get(EXRDService.urlAdOrg + '/find?name=' + options.data.filter.filters[0].value).success(
                                    function(response) {
                                        options.success(response.data);
                                    }
                                );
                            }
                        }
                    },
                    change: function(e) {
                        if (e.sender.value() === '') {
                            ctrl.objectsData.adOrgId = null;
                            ctrl.objectsData.adOrgName = null;
                        }
                    },
                    ignoreCase: false
                };

                // Cấu hình autocomplete phòng ban
                ctrl.cDepartmentIdOptions = {
                    dataTextField: "name",
                    select: function(e) {
                        var dataItem = this.dataItem(e.item.index());
                        ctrl.objectsData.cDepartmentId = dataItem.cDepartmentId;
                    },
                    pageSize: 10,
                    dataSource: {
                        serverFiltering: true,
                        transport: {
                            read: function(options) {
                                return $http.get(EXRDService.urlcDepartment + '/find?name=' + options.data.filter.filters[0].value).success(
                                    function(response) {
                                        options.success(response.data);
                                    }
                                );
                            }
                        }
                    }
                };

                // Cấu hình combobox loại chứng từ
                ctrl.cDocumentTypeIdOptions = {
                    select: function(e) {
                        var dataItem = this.dataItem(e.item.index());
                        ctrl.objectsData.glRevaluationCategoryId = dataItem.glRevaluationCategoryId;
                    },
                    dataSource: {
                        transport: {
                            read: function(options) {
                                return $http.get(EXRDService.urlcDocumentType).success(
                                    function(response) {
                                        options.success(response.data);
                                    }
                                );
                            }
                        }
                    },
                    height: 300,
                    dataTextField: "name",
                    dataValueField: "value"
                };

                // Cấu hình autocomplete nhóm
                ctrl.glRevaluationCategoryIdOptions = {
                    dataTextField: "name",
                    select: function(e) {
                        var dataItem = this.dataItem(e.item.index());
                        ctrl.objectsData.glRevaluationCategoryId = dataItem.glRevaluationCategoryId;
                    },
                    pageSize: 10,
                    dataSource: {
                        serverFiltering: true,
                        transport: {
                            read: function(options) {
                                return $http.get(EXRDService.urlglRevaluationCategory + '/find?name=' + options.data.filter.filters[0].value).success(
                                    function(response) {
                                        options.success(response.data);
                                    }
                                );
                            }
                        }
                    }
                };

                // Cấu hình combobox tiền tệ
                ctrl.cCurrencyIdOptions = {
                    select: function(e) {
                        var dataItem = this.dataItem(e.item.index());
                        ctrl.objectsData.cCurrencyId = dataItem.cCurrencyId;
                    },
                    dataSource: {
                        transport: {
                            read: function(options) {
                                return $http.get(EXRDService.urlcCurrency).success(
                                    function(response) {
                                        options.success(response.data);
                                    }
                                );
                            }
                        }
                    },
                    height: 300,
                    dataTextField: "name",
                    dataValueField: "value"
                };

                // Cấu hình combobox trạng thái
                ctrl.statusOptions = {
                    select: function(e) {
                        var dataItem = this.dataItem(e.item.index());
                        ctrl.objectsData.status = dataItem.value;
                    },
                    dataSource: [{
                        value: '1',
                        name: 'Nháp'
                    }, {
                        value: '2',
                        name: 'Đã hạch toán'
                    }],
                    height: 300,
                    dataTextField: "name",
                    dataValueField: "value"
                };
            };

            // Cấu hình hiển thị danh sách trên grid kendoui
            ctrl.gridOptions = {
                selectable: true,
                dataSource: {
                    serverPaging: true,
                    schema: {
                        data: "data",
                        total: "total"
                    },
                    pageSize: 10,
                    transport: {
                        read: function(options) {
                            EXRDService.SERVICE_SEARCH_PARAM = [
                                'documentNo', 'voucherNo', 'transDateFrom', 'transDateTo', 'dateAcctFrom',
                                'dateAcctTo', 'batchNo', 'batchName', 'groupBatchNo', 'groupBatchName'
                            ];
                            // Ngày chứng từ từ
                            if (ctrl.searchParam.transDateFrom !== null && ctrl.searchParam.transDateFrom !== undefined)
                                ctrl.searchParam.transDateFrom = EXRDService.formatDateTime(ctrl.searchParam.transDateFrom, 'yyyy-MM-dd');
                            // Ngày chứng từ đến
                            if (ctrl.searchParam.transDateTo !== null && ctrl.searchParam.transDateTo !== undefined)
                                ctrl.searchParam.transDateTo = EXRDService.formatDateTime(ctrl.searchParam.transDateTo, 'yyyy-MM-dd');
                            // Ngày hạch toán từ
                            if (ctrl.searchParam.dateAcctFrom !== null && ctrl.searchParam.dateAcctFrom !== undefined)
                                ctrl.searchParam.dateAcctFrom = EXRDService.formatDateTime(ctrl.searchParam.dateAcctFrom, 'yyyy-MM-dd');
                            // Ngày hạch toán đến
                            if (ctrl.searchParam.dateAcctTo !== null && ctrl.searchParam.dateAcctTo !== undefined)
                                ctrl.searchParam.dateAcctTo = EXRDService.formatDateTime(ctrl.searchParam.dateAcctTo, 'yyyy-MM-dd');
                            // Gọi Service lấy dữ liệu
                            var url = EXRDService.urlService;
                            if (EXRDService.filterSearchParam(ctrl.searchParam) === '') {
                                url += '/getPaging';
                                url += '?currentPage=' + options.data.page + '&pageSize=' + options.data.pageSize;
                            } else {
                                url += '/find';
                                url += EXRDService.filterSearchParam(ctrl.searchParam) + '&currentPage=' + options.data.page + '&pageSize=' + options.data.pageSize;
                            }
                            return $http.get(url).success(
                                function(response) {
                                    options.success(response);
                                }
                            );
                        }
                    }
                },
                pageable: {
                    pageSizes: true,
                    refresh: true,
                    messages: {
                        display: "{0} - {1} của {2} kết quả",
                        empty: "Không có kết quả nào",
                        page: "trang",
                        of: "của {0}",
                        itemsPerPage: "kết quả/trang",
                        first: "Về trang đầu",
                        previous: "Về trang trước",
                        next: "Về trang tiếp theo",
                        last: "Về trang cuối",
                        refresh: "Làm mới",
                        allPages: "Tất"
                    }
                },
                columns: [{
                    title: "Đơn vị",
                    width: 180,
                    template: function(dataItem) {
                        return dataItem.adOrg === null ? '' : dataItem.adOrg.name;
                    }
                }, {
                    title: "Phòng ban",
                    width: 200,
                    template: function(dataItem) {
                        return dataItem.cDepartment === null ? '' : dataItem.cDepartment.name;
                    }
                }, {
                    title: "Loại chứng từ",
                    field: "cDocumentTypeId",
                    width: 150
                }, {
                    title: "Số chứng từ",
                    field: "documentNo",
                    width: 120
                }, {
                    title: "Ngày chứng từ",
                    width: 120,
                    template: function(dataItem) {
                        return dataItem.transDate === null ? '' : kendo.toString(new Date(dataItem.transDate), 'dd-MM-yyyy');
                    }
                }, {
                    title: "Ngày hạch toán",
                    width: 120,
                    template: function(dataItem) {
                        return dataItem.dateAcct === null ? '' : kendo.toString(new Date(dataItem.dateAcct), 'dd-MM-yyyy');
                    }
                }]
            };

            // Event click show form tìm kiếm
            ctrl.btnSearch = function() {
                ctrl.showCreate = false;
                ctrl.showSearch = true;
                ctrl.eventSearch();
            };

            // Event click show form thêm mới
            ctrl.btnAdd = function() {
                ctrl.objectsData = {};
                ctrl.showCreate = true;
                ctrl.showSearch = false;
            };

            // Event click lưu
            ctrl.btnSave = function() {
                if (ctrl.showCreate) {
                    if (!ctrl.validator.validate()) return;
                    // Clone ra  object mới để thực hiện loại bot những trường dữ liệu không cần thiết (Nếu loại bỏ luôn ở objectsData thì sẽ mất giá trị hiển thị trên control)
                    var data = $.extend(true, {}, ctrl.objectsData);
                    delete data.adOrgName;
                    delete data.cDepartmentName;
                    delete data.glRevaluationCategoryName;
                    delete data.cDocumentTypeName;
                    delete data.cCurrencyName;
                    delete data.statusName;
                    // Đổi định dạng ngày chứng từ
                    data.transDate = EXRDService.formatDateTime(data.transDate, 'yyyy-MM-dd');
                    // Đổi định dạng ngày hạch toán
                    data.dateAcct = EXRDService.formatDateTime(data.dateAcct, 'yyyy-MM-dd');
                    data.isdelete = 'N';
                    // Call service
                    $http.post(EXRDService.urlService, JSON.stringify(data)).success(function(response) {
                        // ctrl.eventSearch();
                        // ctrl.btnSearch();
                        ctrl.objectsData.glRevaluationId = response.data;
                        toastr.success("Lưu đánh giá chênh lệch tỷ giá thành công!");
                    });
                }
            };

            // Event Click xóa chứng từ
            ctrl.btnRemove = function() {
                if (ctrl.grid.select().length > 0) {
                    var dataItem = ctrl.grid.dataItem(ctrl.grid.select());
                    if (dataItem.status !== null && dataItem.status !== "1") {
                        toastr.warning("Chứng từ đã hạch toán không thể thực hiện xóa");
                        return;
                    }
                    if (dataItem.glRevaluationId === undefined) return;
                    ctrl.grid.dataSource.remove(dataItem);
                    $http.delete(EXRDService.urlService + '/' + dataItem.glRevaluationId);
                    toastr.success("Xóa chứng từ đánh giá chênh lệch tỷ giá thành công!");
                } else {
                    toastr.warning("Bạn cần chọn một bản ghi trước");
                }
            };

            // event click hạch toán
            ctrl.btnHoachToan = function() {
                if (ctrl.grid.select().length > 0) {
                    var dataItem = ctrl.grid.dataItem(ctrl.grid.select());
                    if (dataItem.glRevaluationId === undefined) return;
                    $http.get(EXRDService.urlService + '/accounting/' + dataItem.glRevaluationId);
                    toastr.success("Hạch toán đánh giá chênh lệch tỷ giá thành công!");
                } else {
                    toastr.warning("Bạn cần chọn một bản ghi trước");
                }
            };

            // event click hủy hạch toán
            ctrl.btnHuyHoachToan = function() {
                if (ctrl.grid.select().length > 0) {
                    var dataItem = ctrl.grid.dataItem(ctrl.grid.select());
                    if (dataItem.glRevaluationId === undefined) return;
                    $http.get(EXRDService.urlService + '/cancelAccounting/' + dataItem.glRevaluationId);
                    toastr.success("Hủy hạch toán đánh giá chênh lệch tỷ giá thành công!");
                } else {
                    toastr.warning("Bạn cần chọn một bản ghi trước");
                }
            };

            // Event Tìm kiếm danh sách đánh giá chênh lệch tỷ giá
            ctrl.eventSearch = function() {
                ctrl.grid.dataSource.page(1);
                ctrl.grid.dataSource.read();
                ctrl.grid.refresh();
            };

            ctrl.btnExchangeRateDifference = function() {
                if (ctrl.objectsData.glRevaluationId === undefined || ctrl.objectsData.glRevaluationId === null) return;
                $http.get(EXRDService.urlService + '/calculateRevaluation/' + ctrl.objectsData.glRevaluationId);
            };
        }
    );
})();
