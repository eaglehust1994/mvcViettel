(function() {
    'use strict';

    var controllerId = 'GlUnitsGroupController';

    angular.module('MetronicApp').controller(controllerId, GlUnitsGroupController);

    /* @ngInject */
    function GlUnitsGroupController($scope, $rootScope, $timeout, Constant, gettextCatalog, kendoConfig, WindowService, GlUnitsGroupService) {
        var vm = this;

        $scope.object = {};
        $scope.search = {};

        vm.showCreate = false;
        vm.showDetail = false;
        vm.showSearch = false;
        vm.showTable = true;

        vm.add = add;
        vm.save = save;
        vm.remove = remove;
        vm.search = search;
        vm.showGrid = showGrid;
        vm.find = find;
		vm.popup = popup;

        vm.validatorOptions = kendoConfig.get('validatorOptions');

        //Init message
        var noRecordMsg = gettextCatalog.getString("Chưa có bản ghi nào");
        var saveSuccessMsg = gettextCatalog.getString("Lưu nhóm đơn vị thành công");
        var delSuccessMsg = gettextCatalog.getString("Xóa nhóm đơn vị thành công");
        var saveWarningMsg = gettextCatalog.getString("Mã đã tồn tại");
		var errorExistId = gettextCatalog.getString("Mã đã tồn tại");


        //Init table fields
        var tableFields = [{
            title: gettextCatalog.getString("Mã"),
            field: "value",
            width: 100
        }, {
            title: gettextCatalog.getString("Tên"),
            field: "name",
            width: 180
        }, {
            title: gettextCatalog.getString("Mô tả"),
            field: "description",
            width: 180
        }, {
            title: gettextCatalog.getString("Hiệu lực"),
            field: "isactive",
            width: 180,
            template: function(item) {
                return (item.isactive == "1") ? "Có" : "Không";
            }
        }];

        //Running
        fetchAllData();

        //Set object model
        function setModelData(model) {
            $scope.object = {
                adOrgGroupId: model.adOrgGroupId,
                value: model.value,
                name: model.name,
                description: model.description,
                isactive: (model.isactive == "1") ? true : false
            };
        }

        function add() {
            $scope.object = {};
            $scope.object.isactive = true;
            $scope.$apply;
            vm.showDetail = false;
            vm.showSearch = false;
            vm.showTable = false;
            vm.showCreate = true;
        }

        function search() {
            $scope.search = {};
            $scope.$apply;
            vm.showDetail = false;
            vm.showCreate = false;
            vm.showSearch = true;
            vm.showTable = true;
        }

        function showGrid() {
            if (!vm.showTable || vm.showSearch) {
                $scope.search = {};
                reloadData();
                vm.showDetail = false;
                vm.showSearch = false;
                vm.showCreate = false;
                vm.showTable = true;
                return;
            }

            if (vm.dataGrid.select().length > 0) {
                var model = vm.dataGrid.dataItem(vm.dataGrid.select());
                setModelData(model);
                vm.showSearch = false;
                vm.showTable = false;
                vm.showDetail = true;
                vm.showCreate = true;
            }
        }

        function find() {
			$scope.search.isDeleted = 'N';
            GlUnitsGroupService.findObject($scope.search).then(function(d) {
                fillDataTable(d.data);
                refreshGrid(d.data);
            }, errorHandle);
        }

        function save() {
            if ((!vm.showCreate && !vm.showTable) || (!vm.validator.validate())) {
                return;
            }
            $scope.object.isDeleted = 'N';
            $scope.object.isactive = ($scope.object.isactive) ? 1 : 0;
            if (vm.showCreate && !vm.showDetail) {
                GlUnitsGroupService.createObject(JSON.stringify($scope.object)).then(
                    function(d) {
						if (d.status != undefined && d.status == "VALUE_EXIST") {
							$scope.object.isactive = ($scope.object.isactive == 1) ? true : false;
                            $scope.$apply;
                            toastr.error(errorExistId);
                        }
						else {
                            reloadObject();
                            toastr.success(saveSuccessMsg);
                        }
                    }, errorHandle);
            } else {
                GlUnitsGroupService.updateObject(JSON.stringify($scope.object)).then(
					function (d) {
                        if (d.status != undefined && d.status == "VALUE_EXIST") {
                            toastr.error(errorExistId);
                        } else {
                            reloadObject();
                            toastr.success(saveSuccessMsg);
                        }
                    }, errorHandle);
            }
        }

        function remove() {
            if (vm.dataGrid.select().length > 0) {
				var dataItem = vm.dataGrid.dataItem(vm.dataGrid.select());
				vm.dataGrid.dataSource.remove(dataItem);
				GlUnitsGroupService.deleteObject(dataItem.adOrgGroupId).then(function(response) {
					if (response.status === "REQUIRED") {
						toastr.error("Dữ liệu đã có rằng buộc bạn không thể thực hiện xóa");
					} else {
						$scope.object = {};
						showGrid();
						reloadData();
						toastr.success(delSuccessMsg);
					}
				}, errorHandle);
				return;
			}
			if ($scope.object.adOrgGroupId > 0) {
				GlUnitsGroupService.deleteObject($scope.object.adOrgGroupId).then(function(response) {
					if (response.status === "REQUIRED") {
						toastr.error("Dữ liệu đã có rằng buộc bạn không thể thực hiện xóa");
					} else {
						$scope.object = {};
						showGrid();
						reloadData();
						toastr.success(delSuccessMsg);
					}
				}, errorHandle);
				return;
			}
            else{
				toastr.warning("Bạn cần chọn một bản ghi trước");
			}
        }

        //Init table
        function fillDataTable(data) {
            vm.gridOptions = kendoConfig.getGridOptions({
                selectable: true,
                autoBind: true,
                dataSource: data,
                noRecords: true,
                editable: false,
                // pageable: {refresh: false, info: false},
                detailExpand: detailExpand,
                messages: {
                    noRecords: noRecordMsg
                },
                columns: tableFields,
                pageable: {
                    pageSizes: 20,
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
            });
        }

        function detailExpand(e) {
            setModelData(vm.dataGrid.dataItem(e.masterRow));
            this.collapseRow(this.tbody.find(' > tr.k-master-row').not(e.masterRow));
        }

        //Get data from service
        function fetchAllData() {
            GlUnitsGroupService.fetchAllObject().then(function(d) {
                fillDataTable(d.data);
            }, errorHandle);
        }

        function reloadObject() {
            $scope.object = {};
            showGrid();
            reloadData();
        }

        function reloadData() {
            GlUnitsGroupService.fetchAllObject().then(function(d) {
                fillDataTable(d.data);
                refreshGrid(d.data);
            }, errorHandle);
        }

        function refreshGrid(d) {
            vm.dataGrid.dataSource.data(d);
            vm.dataGrid.refresh();
            vm.showDetail = false;
            vm.showCreate = false;
            vm.showTable = true;
        }

        //Error handler
        function errorHandle(errResponse) {
            toastr.warning(saveWarningMsg);
            console.error('Error while creating object: ' + errResponse);
        }
		
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
		
		function popup() {
            WindowService.openSmallEditor("70%", "60%", {}, 'erp/glunitsgroup/gl_units_group_form.html', "Nhóm đơn vị");
        }
        
        vm.nextRow = function() {
			var grid = vm.dataGrid;
			nextRowBase(grid, message);
		}
		
		vm.previousRow = function() {
			var grid = vm.dataGrid;
			previousRowBase(grid, message);
		}
    }
})();
