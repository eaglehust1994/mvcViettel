(function() {
    'use strict';

    angular.module('MetronicApp').factory('GroupExpenseItemsService', function() {
        return {
            urlService: 'http://42.112.29.41:8080/erp-service/cCostTypeGroupRsService/cCostTypeGroup',
            urlAdOrgService: 'http://42.112.29.41:8080/erp-service/adOrgServiceRest/adOrg',
            SERVICE_SEARCH_PARAM: ['value', 'name'],
            filterSearchParam: function(object) {
                var param = "";
                SERVICE_SEARCH_PARAM.filter(function(key) {
                    if (object[key] !== undefined && object[key] !== '') {
                        param += (param === "") ? key + "=" + object[key] : "&" + key + "=" + object[key];
                    }
                });
                return (param !== "") ? "?" + param : "";
            }
        };
    });

    angular.module('MetronicApp').controller('GroupExpenseItemsCtr',
        function($scope, $http, WindowService, gettextCatalog, GroupExpenseItemsService) {
            var vm = this;
            $scope.objects = {};
            $scope.search = {};
            $scope.showCreate = false;
            $scope.showDetail = false;
            $scope.showSearch = false;
            $scope.showTable = true;

            $scope.openUnitsForm = openUnitsForm;
			
			var errorExistId = gettextCatalog.getString("Mã đã tồn tại");

            function openUnitsForm() {
                var dataConfig = {
                    selected: function(data) {
                        $scope.objects.adOrgId = data.id;
                        $scope.objects.adOrgName = data.text;
                    }
                };
                WindowService.openUnitsForm(dataConfig);
            }

            // Hàm khởi chạy khi DOM được load xong
            $scope.init = function() {

                // Cấu hình load autocomplete đơn vị
                $scope.adOrgIdOptions = {
                    dataTextField: "name",
                    select: function(e) {
                        var dataItem = this.dataItem(e.item.index());
                        $scope.objects.adOrgId = dataItem.adOrgId;
                        $scope.objects.adOrgName = dataItem.name;
                    },
                    pageSize: 10,
                    dataSource: {
                        serverFiltering: true,
                        transport: {
                            read: function(options) {
                                return $http.get(GroupExpenseItemsService.urlAdOrgService + '/find?name=' + options.data.filter.filters[0].value).success(
                                    function(response) {
                                        options.success(response.data);
                                    }
                                );
                            }
                        }
                    },
                    change: function(e) {
                        if (e.sender.value() === '') {
                            $scope.objects.adOrgId = null;
                            $scope.objects.adOrgName = null;
                        }
                    },
                    ignoreCase: false
                };

                // Cấu hình control datetimepicker
                $scope.datePickerOptions = {
                    format: "dd-MM-yyyy",
                    parseFormats: ["yyyy-MM-dd", "dd-MM-yyyy"]
                };

                $scope.maxValidFrom = new Date();
                $scope.minValidTo = new Date();
                // Set min cho control đến ngày khi từ ngày thay đổi
                $scope.changeValidFrom = function() {
                    $scope.minValidTo = kendo.parseDate($scope.objects.validFrom, 'dd-MM-yyyy');
                };
                // Set max cho control từ ngày khi đến ngày thay đổi
                $scope.changeValidTo = function() {
                    $scope.maxValidFrom = kendo.parseDate($scope.objects.validTo, 'dd-MM-yyyy');
                };
            };

            // Cấu hình hiển thị danh sách trên grid kendoui
            $scope.gridOptions = {
                selectable: true,
                detailExpand: function(e) {
                    this.collapseRow(this.tbody.find(' > tr.k-master-row').not(e.masterRow));
                    var model = $scope.gridList.dataItem(e.masterRow);
                    $scope.objects = {
                        cCostTypeGroupId: model.cCostTypeGroupId,
                        adOrgName: model.adOrg !== null ? model.adOrg.name : '',
                        adOrgId: model.adOrgId,
                        value: model.value,
                        name: model.name,
                        description: model.description,
                        isactive: model.isactive == "1" ? true : false,
                        validFrom: model.validFrom === null ? null : kendo.toString(
                            new Date(model.validFrom), 'dd-MM-yyyy'),
                        validTo: model.validTo === null ? null : kendo.toString(
                            new Date(model.validTo), 'dd-MM-yyyy')
                    };
                    $scope.$apply();
                },
                dataSource: {
                    pageSize: 20,
                    transport: {
                        read: function(options) {
                            var url = GroupExpenseItemsService.urlService;
                            if ($scope.showSearch === true) {
                                url += '/find?';
                                GroupExpenseItemsService.SERVICE_SEARCH_PARAM.filter(function(values) {
                                    if ($scope.search[values] !== '' && $scope.search[values] !== undefined && $scope.search[values] !== null)
                                        url += values + '=' + $scope.search[values] + '&';
                                });
                                url = url.substring(0, url.length - 1);
                            }
                            return $http.get(url).success(
                                function(response) {
                                    options.success(response.data);
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
                    field: 'adOrg',
                    width: 180,
                    template: function(dataItem) {
                        return dataItem.adOrg === null ? '' : dataItem.adOrg.name;
                    }
                }, {
                    title: "Mã",
                    field: "value",
                    width: 100
                }, {
                    title: "Tên",
                    field: "name",
                    width: 180
                }, {
                    title: "Mô tả",
                    field: "description",
                    width: 150
                }, {
                    title: "Hiệu lực",
                    field: "isactive",
                    template: function(dataItem) {
                        if (dataItem.isactive == "1") {
                            return "Có";
                        } else {
                            return "Không";
                        }
                    },
                    width: 100
                }, {
                    title: "Hiệu lực từ",
                    field: "validFrom",
                    width: 100,
                    template: function(dataItem) {
                        return dataItem.validFrom === null ? '' : kendo
                            .toString(new Date(dataItem.validFrom),
                                'dd-MM-yyyy');
                    }
                }, {
                    title: "Hiệu lực đến",
                    field: "validTo",
                    width: 100,
                    template: function(dataItem) {
                        return dataItem.validTo === null ? '' : kendo
                            .toString(new Date(dataItem.validTo),
                                'dd-MM-yyyy');
                    }
                }]
            };

            // Event click toolbar thêm mới
            $scope.btnAdd = function() {
				$scope.objects = {};
				$scope.objects.isactive = true;
                $scope.showDetail = false;
                $scope.showSearch = false;
                $scope.showTable = false;
                $scope.showCreate = true;
            };

            // Event click toolbar xóa
            $scope.btnRemove = function() {
                if ($scope.gridList.select().length > 0) {
                    var dataItem = $scope.gridList.dataItem($scope.gridList.select());
                    if (dataItem.cCostTypeGroupId === undefined) return;
                    $scope.gridList.dataSource.remove(dataItem);
					$scope.objects = {};
                    $http.delete(GroupExpenseItemsService.urlService + '/' + dataItem.cCostTypeGroupId);
                    toastr.success("Xóa nhóm khoản mục phí thành công!");
					$scope.btnShowGrid();
					return;
                }
				if ($scope.objects.cCostTypeGroupId > 0) {
                    $http.delete(GroupExpenseItemsService.urlService + '/' + $scope.objects.cCostTypeGroupId);
					$scope.objects = {};
                    toastr.success("Xóa nhóm khoản mục phí thành công!");
					$scope.searchData();
					$scope.gridList.dataSource.read();
                    $scope.gridList.refresh();
                    $scope.btnShowGrid();
					return;
                }
				else {
                    toastr.warning("Bạn cần chọn một bản ghi trước");
                }
            };

            // Event click toolbar Lưu
            $scope.btnSave = function() {
                if (!$scope.showCreate && !$scope.showTable) return;
                if (!vm.validator.validate()) return;
                // Binding lại giá trị trường hiệu lực
                $scope.objects.isactive = $scope.objects.isactive ? 1 : 0;
                // Loại bỏ trường tên đơn vị không cần thiết
                delete $scope.objects.adOrgName;
                // Định dạng lại ngày hiệu lực
                if ($scope.objects.validFrom !== undefined)
                    $scope.objects.validFrom = kendo.toString(kendo.parseDate($scope.objects.validFrom, 'dd-MM-yyyy'), 'yyyy-MM-dd');
                if ($scope.objects.validTo !== undefined)
                    $scope.objects.validTo = kendo.toString(kendo.parseDate($scope.objects.validTo, 'dd-MM-yyyy'), 'yyyy-MM-dd');

                // Lưu
                var fnSuccess = function() {
                    $scope.gridList.dataSource.read();
                    $scope.gridList.refresh();
                    $scope.btnShowGrid();
                    toastr.success("Lưu nhóm khoản mục phí thành công!");
                };
                if ($scope.showCreate) {
                    $http.post(GroupExpenseItemsService.urlService, JSON.stringify($scope.objects)).success(
                        function(response) {
                            if (response.status != undefined && response.status == "VALUE_EXIST") {
								toastr.error(errorExistId);
							} else {
								$scope.objects.cCostTypeGroupId = response.data;
								fnSuccess();
							}
                        }
                    );
					$scope.objects.isactive = ($scope.objects.isactive == 1) ? true : false;
					$scope.$apply;
                } else {
                    $http.put(GroupExpenseItemsService.urlService, JSON.stringify($scope.objects)).success(
                        function(response) {
                            if (response.status != undefined && response.status == "VALUE_EXIST") {
								toastr.error(errorExistId);
							} else {
								fnSuccess();
							}
                        }
                    );
                }
            };

            // Event click toolbar View
            $scope.btnShowGrid = function() {
                if (!$scope.showTable) {
                    $scope.showDetail = false;
                    $scope.showSearch = false;
                    $scope.showCreate = false;
                    $scope.showTable = true;
                    $scope.search = {};
                    $scope.gridList.dataSource.read();
                    $scope.gridList.refresh();
                } else {
                    if ($scope.gridList.select().length > 0) {
                        var model = $scope.gridList.dataItem($scope.gridList.select());
                        // $scope.isCreated = false;
                        $scope.objects = {
                            cCostTypeGroupId: model.cCostTypeGroupId,
                            adOrgName: model.adOrg == null ? "" : model.adOrg.name,
                            adOrgId: model.adOrgId,
                            value: model.value,
                            name: model.name,
                            description: model.description,
                            isactive: model.isactive == "1" ? true : false,
                            validFrom: model.validFrom === null ? null : kendo.toString(
                                new Date(model.validFrom), 'dd-MM-yyyy'),
                            validTo: model.validTo === null ? null : kendo.toString(
                                new Date(model.validTo), 'dd-MM-yyyy')
                        };
                        $scope.showSearch = false;
                        $scope.showTable = false;
                        $scope.showDetail = true;
                        $scope.showCreate = true;
                    }
                }
            };

            // Event click toolbar tìm kiếm
            $scope.btnSearch = function() {
                $scope.showDetail = false;
                $scope.showCreate = false;
                $scope.showSearch = true;
                $scope.showTable = true;
            };

            // Event click button tìm kiếm
            $scope.searchData = function() {
                $scope.gridList.dataSource.read();
                $scope.gridList.refresh();
            };
			
			var message = {
				noDataGrid: gettextCatalog.getString("Không có dữ liệu trên trang hiện tại"),
				lineRequired: gettextCatalog.getString("Bạn cần chọn một bản ghi trước"),
				recordRequired: gettextCatalog.getString("Bạn cần chọn một bản ghi trước"),
				needShowDetail : gettextCatalog.getString("Cần hiển thị ở chế độ Chi Tiết!"),
				positionLast: gettextCatalog.getString("Đã ở bản ghi cuối"),
				positionFirst: gettextCatalog.getString("Đã ở bản ghi đầu"),
				deleteError: gettextCatalog.getString("Xóa không thành công"),
				deleteSuccess: gettextCatalog.getString("Xóa  thành công"),
			}
			
			$scope.popup = function() {
				WindowService.openSmallEditor("70%", "60%", {}, 'erp/groupExpenseItems/groupExpenseItemsForm.html', "Nhóm khoản mục chi phí");
			}
			
			$scope.nextRow = function() {
				var grid = gridList;
				nextRowBase(grid, message);
			}
			
			$scope.previousRow = function() {
				var grid = gridList;
				previousRowBase(grid, message);
			}
        });
})();
