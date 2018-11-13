(function () {
    'use strict';

    var controllerId = 'GlYearAndPeriodController';

    angular.module('MetronicApp').controller(controllerId, GlYearAndPeriodController);

    /* @ngInject */
    function GlYearAndPeriodController($scope, $rootScope, $timeout, Constant,
                                       gettextCatalog, kendoConfig, WindowService, GlYearAndPeriodService) {
        $scope.objectview={};
        var vm = this;

        vm.openUnitsForm = openUnitsForm;

        /*------------- switch display state ------------*/

        $scope.displayState = {
            showGrid: 0x01,
            showAdd: 0x02
        };

        // $scope.state = $scope.displayState.showGrid;

        $scope.isShow = function (key) {
            if ($scope.state == $scope.displayState[key]) return true
            else return false;
        }

        $scope.isShowDetail = function () {
            return $scope.isYearDetailExpand;
        }
        $scope.isDisableYearAndStartDate = function () {
            if ($scope.state == $scope.displayState.showGrid) return true;
            if ($scope.gridYear != null && $scope.gridYear.dataSource.data().length > 0) return true;
            return false;
        }

        /*------------- switch display state ------------*/


        /*------------- init Model , Options -----------*/

        /**
         * init new model
         */
        function initModel() {
            //find max year and grid and endDate of its
            var year = new Date().getFullYear();
            var startDate = new Date(new Date().getFullYear(), 0, 1);
            var endDate = new Date(new Date().getFullYear(), 11, 31);
            if ($scope.gridYear != null) {
                //var dataItem=$scope.gridYear.dataSource.dataItem();
                var datas = $scope.gridYear.dataSource.data();
                if (datas.length > 0) {
                    year = datas[0].year + 1;
                    startDate = datas[0].endDate + (24 * 60 * 60 * 1000);
                    endDate = new Date(year, 11, 31);
                }
            }

            $scope.model = {
                cYearId: null,
                adOrgId: -1,
                adOrgName: "",
                name: "",
                year: year,
                description: "",
                startDate: getDateTimeDisplay(startDate),
                endDate: getDateTimeDisplay(endDate),
                isactive: "true"
            }
            $scope.modelValidation = {
                errEndDate: "",
                errStartDate: "",
                errAdOrgId: ""
            };

            //   bindPeriodGrid(null);

        }


        $scope.init = function () {
            initModel();

            $scope.datePickerOptions = {
                format: "dd-MM-yyyy",
                parseFormats: ["yyyy-MM-dd", "dd-MM-yyyy"]
            };
            $scope.datePickerOptions = {
                format: "dd-MM-yyyy",
                parseFormats: ["yyyy-MM-dd", "dd-MM-yyyy"]
            };
            $scope.validatorOptions = kendoConfig.get('validatorOptions');

            // initAutoCompleteAdOrg();
            $scope.adOrgOptions = initAutoCompleteOption({
                displayField: "name",
                url: GlYearAndPeriodService.urlBase() + "/adOrgServiceRest/adOrg/find",
                selectFunc: function (data) {
                    $scope.model.adOrgId = data.adOrgId;
                }
            })

            $scope.gridYearOptions = initGridYearOption(bindYearDetailExpand, bindYeardetailCollapse, GlYearAndPeriodService.uri() + "/getPaging");

            /// $scope.gridPeriodOptions=initGridPeriodOption();

            $scope.isYearDetailExpand = false;

            showGrid();


        }

        /*-------------End init Model , Options -----------*/


        /*-------------validator-------------*/
        /**
         * validate all form
         * @returns {boolean}
         */
        function validateForm() {

            //validate require
            if (!vm.validator.validate()) return false;

            if ($scope.modelValidation.errStartDate != "") return false;
            if ($scope.modelValidation.errEndDate != "") return false;

            if ($scope.model.adOrgId == -1) {
                $scope.modelValidation.errAdOrgId = gettextCatalog.getString("Không tìm thấy đơn vị !");
                return;
            }

            return true;
        }

        /**
         * validate endDate
         */
        $scope.endDateChange = function () {
            var endDate = kendo.parseDate($scope.model.endDate, 'dd-MM-yyyy');
            if (endDate == null) {
                $scope.modelValidation.errEndDate = gettextCatalog.getString("Ngày kết thúc không đúng !");
            } else {
                var startDate = kendo.parseDate($scope.model.startDate, 'dd-MM-yyyy');
                if (startDate != null) {
                    if (startDate >= endDate) $scope.modelValidation.errEndDate = gettextCatalog.getString("Ngày kết thúc phải lớn hơn ngày bắt đầu!")
                    else {
                        $scope.modelValidation.errEndDate = "";
                        $scope.modelValidation.errStartDate = "";
                    }
                } else {
                    $scope.modelValidation.errEndDate = ""
                    $scope.modelValidation.errStartDate = "";
                }
            }
        }

        /**
         * validate startDate
         */
        $scope.startDateChange = function () {
            var startDate = kendo.parseDate($scope.model.startDate, 'dd-MM-yyyy');
            if (startDate == null) {
                $scope.modelValidation.errStartDate = gettextCatalog.getString("Ngày bắt đầu không đúng !");
            } else {
                var endDate = kendo.parseDate($scope.model.endDate, 'dd-MM-yyyy');
                if (endDate != null) {
                    if (startDate >= endDate) $scope.modelValidation.errStartDate = gettextCatalog.getString("Ngày kết thúc phải lớn hơn ngày bắt đầu!")
                    else {
                        $scope.modelValidation.errEndDate = "";
                        $scope.modelValidation.errStartDate = "";
                    }
                } else {
                    $scope.modelValidation.errEndDate = "";
                    $scope.modelValidation.errStartDate = "";
                }

            }
        }

        /*-------------end validator -------------*/


        /*----------- bindData -------------*/

        /**
         *
         * @param dataItem : yearDataItem
         *
         */
        function bindYearDetailExpand(yearDataItem) {
            // console.log("bind detail "+JSON.stringify(yearDataItem));
            var data = JSON.parse(JSON.stringify(yearDataItem))
            data.adOrgId = data.adOrg.adOrgId;
            data.adOrgName = data.adOrg.name;
            data.startDate = getDateTimeDisplay(data.startDate);
            data.endDate = getDateTimeDisplay(data.endDate);
            data.isactive = (data.isactive == 1) ? "true" : "false";

            initModel();
            mapObject($scope.model, data);
            $("#gridPeriod").kendoGrid(initGridPeriodOption());
            getPeriodByYear(data, bindPeriodGrid);
            $scope.isYearDetailExpand = true;
            // console.log("bind detail model "+JSON.stringify($scope.model));
        }

        function bindYeardetailCollapse() {
            console.log("year collapse");
            $scope.isYearDetailExpand = false;
        }

        /**
         *
         * @param dataSource
         *
         */
        function bindYearGrid(page) {
            if (page == null) page = 1;
            // console.log("bind grid");
            if ($scope.gridYear != null && $scope.gridYear != undefined) {
                $scope.gridYear.dataSource.page(page);
                $scope.gridYear.dataSource.read();
                $scope.gridYear.refresh();
                $scope.isYearDetailExpand = false;
            }

        }

        /**
         *
         * @param data : json get from server
         */
        function bindPeriodGrid(periodData) {
            if ($("#gridPeriod") != null) {
                var ds = new kendo.data.DataSource({data: periodData});
                ds.page(1);
                ds.total(periodData.length);
                $("#gridPeriod").data("kendoGrid").setDataSource(ds);
            }
        }

        /*-----------end bindData -------------*/


        /*------------- action menu ------------*/

        var vm = this;
        vm.showAdd = showAdd;
        vm.showGrid = showGrid;
        vm.save = save;
        vm.remove = remove;

        function showAdd() {
            if ($scope.state == $scope.displayState.showAdd) return;
            $scope.state = $scope.displayState.showAdd;
            initModel();
        }

        function showGrid() {
            if ($scope.state == $scope.displayState.showGrid) return;
            $scope.state = $scope.displayState.showGrid;
            bindYearGrid();
        }


        function save() {
            if (validateForm()) {
                $scope.model.startDate = getDateTimeService($scope.model.startDate);
                $scope.model.endDate = getDateTimeService($scope.model.endDate);
                $scope.model.isactive = ($scope.model.isactive == "true") ? 1 : 0;
                if ($scope.state == $scope.displayState.showAdd) {
                    GlYearAndPeriodService.create(JSON.stringify($scope.model)).then(
                        function (d) {
                            toastr.success(gettextCatalog.getString("Lưu năm tài chính thành công"));
                            createPeriod($scope.model.year);
                            showGrid();
                        },
                        function (err) {
                            toastr.success(gettextCatalog.getString("Có lỗi xảy ra khi lưu năm tài chính"));
                        }
                    )
                    return;
                }

                if ($scope.isYearDetailExpand) {
                    GlYearAndPeriodService.update(JSON.stringify($scope.model)).then(
                        function (d) {
                            toastr.success(gettextCatalog.getString("Lưu năm tài chính thành công"));
                            bindYearGrid($scope.gridYear.dataSource.page());
                        },
                        function (err) {
                            toastr.success(gettextCatalog.getString("Có lỗi xảy ra khi lưu năm tài chính"));
                        }
                    )
                    return;
                }
            } else {
                console.log("form not validate");
            }
        }

        function remove() {
            if ($scope.state == $scope.displayState.showGrid) {
                if ($scope.gridYear.select().length == 0) {
                    toastr.warning("Bạn cần chọn một bản ghi trước");
                    return;
                }
                var dataRows = $scope.gridYear.items();
                var rowIndex = dataRows.index($scope.gridYear.select());
                if (rowIndex != 0) {
                    toastr.warning("Bạn chỉ có thể xoá năm tài chính theo thứ tự !");
                    return;
                }
                if (false) {
                    // check da su dung o hach toan hoac chung tu thi ko xoa
                    return;
                }

                var dataItem = $scope.gridYear.dataItem($scope.gridYear.select());
                GlYearAndPeriodService.remove(dataItem.cYearId).then(
                    function (d) {
                        bindYearGrid();
                        getPeriodByYear(dataItem, removePeriodByYear);
                        toastr.success("Xóa năm tài  thành công!");
                    }
                    , function (errResponse) {
                        console.error('Lỗi xoá năm tài chính');
                    });

                return;
            }

            if ($scope.state == $scope.displayState.showAdd) {
                initModel(); // reset form
                return;
            }

        }

        /**
         * auto create period for year
         * after create year success
         * @param year
         */
        function createPeriod(year) {
            //first find cYearId
            $.ajax({
                type: "GET",
                url: GlYearAndPeriodService.uri() + '/find?year=' + year,
                dataType: "json",
                contentType: "application/json",
                success: function (result) {
                    //options.success(result.data);
                    //console.log("find year "+JSON.stringify(result));
                    if (result.data.length == 0) return;
                    var data = result.data[0];
                    var year = data.year;
                    var currMonth = new Date(data.startDate).getMonth();
                    var endMonth = new Date(data.endDate).getMonth();
                    var periodIndex = 1;
                    console.log("period month " + currMonth + ":" + endMonth);
                    for (var i = currMonth; i < endMonth + 1; i++) {
                        var startDate = new Date(year, i, 1);
                        var endDate = new Date(year, i + 1, 0);
                        if (periodIndex == 1) {
                            startDate = new Date(year, 0, 1);
                        }
                        if (i == endMonth) {
                            endDate = new Date(data.endDate);
                        }
                        var periodKey = (periodIndex < 10) ? "0" + periodIndex : periodIndex;
                        var periodObj = {
                            adOrgId: data.adOrgId,
                            CYearId: data.cYearId,
                            periodNo: periodIndex,
                            periodKey: year + "_" + periodKey,
                            name: "Năm " + year + " kỳ " + periodIndex,
                            startDate: getDateTimeService(startDate),
                            endDate: getDateTimeService(endDate),
                            isactive: 1,
                            description: ""

                        }
                        GlYearAndPeriodService.createPeriod(periodObj);
                        periodIndex++;
                    }
                },
                error: function (e) {
                    toastr.warning("Có lỗi xảy ra khi tạo kỳ tài chính , không tìm thấy năm tài chính !");
                }
            });

        }


        function getPeriodByYear(yearItem, callFunc) {
            if (yearItem == null || yearItem.cYearId == undefined) return;
            $.ajax({
                type: "GET",
                url: GlYearAndPeriodService.urlBase() + "/cPeriodRsService/cPeriod",
                //  url: GlYearAndPeriodService.urlBase()+"/cPeriodRsService/cPeriod",
                dataType: "json",
                contentType: "application/json",
                success: function (result) {
                    //options.success(result);Y
                    var arrData = [];
                    for (var i = 0; i < result.data.length; i++) {
                        result.data[i].adOrgName = yearItem.adOrg.name;
                        result.data[i].year = yearItem.year;
                        var periodYear = parseInt(result.data[i].periodKey.split("_")[0]);
                        // only get current year , for filter find service not correct
                        if (periodYear == yearItem.year) arrData.push(result.data[i]);
                    }
                    //sort by periodKey
                    for (var i = 0; i < arrData.length; i++)
                        for (var j = i; j < arrData.length; j++) {
                            if (arrData[i].periodKey > arrData[j].periodKey) {
                                var tmp = arrData[i];
                                arrData[i] = arrData[j];
                                arrData[j] = tmp;
                            }
                        }

                    console.log("get period data " + JSON.stringify(result.data));

                    callFunc.apply(this, [arrData]);
                },
                error: function (e) {
                    console.log("Có lỗi khi lấy dữ liệu kỳ tài chính")
                }
            });
        }

        /**
         * call after remove year
         * @param arrPeriod
         */
        function removePeriodByYear(arrPeriod) {
            for (var i = 0; i < arrPeriod.length; i++) {
                $.ajax({
                    type: "DELETE",
                    url: GlYearAndPeriodService.urlBase() + "/cPeriodRsService/cPeriod/" + arrPeriod[i].cPeriodId,
                    dataType: "json",
                    contentType: "application/json",
                    success: function (result) {
                        console.log("xoa ky tai chinh " + arrPeriod[i].name);
                    },
                    error: function (e) {
                        console.log("Có lỗi khi xóa kỳ tài chính");
                    }
                });
            }
        }

        //Loading modal
        function openUnitsForm() {
            var dataConfig = {
                selected: function (data) {
                    $scope.model.adOrgId = data.id;
                    $scope.objectview.adOrgIdTextView = data.text;
                }
            };
            WindowService.openUnitsForm(dataConfig);
        }

        /*------------- end action menu ------------*/
    }
})();

