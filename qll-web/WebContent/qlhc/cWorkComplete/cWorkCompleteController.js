(function() {
    'use strict';

    var controllerId = 'cWorkCompleteController';

    angular.module('MetronicApp').controller(controllerId, ['$scope', 'RestEndpoint',
        '$rootScope', '$timeout', 'PopupConst', 'Constant',
        'kendoConfig', 'gettextCatalog',
        '$kWindow', 'CommonService', '$q', 'cWorkCompleteService', 'ProposalEvaluation', reportCrStHandoverFunc
    ]);

    /* @ngInject */
    function reportCrStHandoverFunc($scope, RestEndpoint,
        $rootScope, $timeout, PopupConst, Constant,
        kendoConfig, gettextCatalog,
        $kWindow, CommonService, $q, cWorkCompleteService, ProposalEvaluation) {
        var vm = this;
        vm.hideAButton = hideAButton;
        vm.onChange = onChange;
        vm.changeSite = changeSite;
        vm.demoSelect = "1";
        vm.listDataFillTable = [];
        vm.isCheckAddRow = false;
        vm.addSubTable = addSubTable;
        vm.saveSubTable = saveSubTable;
        vm.deleteSubTable = multiDelete;
        vm.save = saveAll;
        vm.exportFile = exportFile;
        vm.coutRowAdded = 0;

        vm.changeValueInput = changeValueInput;
        vm.ptgs = {
            constructId: '',
        }
        vm.ptkt = {
            constructId: '',
        }
        vm.conclusion = [{
            "id": "0",
            "content": "Không đồng ý"
        }, {
            "id": "1",
            "content": "Đồng ý"
        }]
        vm.item = {};
        vm.reportObj = {
            constructId: '',
            currentStateHandoverId: '',
            code: '',
            aInChargeMonitorId: '', // phụ trách giám sát thi công của chử đầu tư
            bInChargeConstructId: '', // phụ trách kĩ thuật nhà thầu thi thông
            handoverDate: '',
            handoverPlace: '',
            groundHandoverContent: '',
            otherDocuments: '',
            otherComments: '',
            conclusion: '',
            createdDate: '',
            createdUserId: '',
            approvalDate: '',
            statusCa: '',
            isActive: '',
            statusCaText: ''
        }
        vm.reportcvstHandover = {
            currentStateHandoverListId: "",
            handoverContent: "",
            unit: "",
            quantity: "",
            currentState: "",
            constructionRequest: "",
            notes: "",
            currentStateHandoverId: ""
        }
        vm.datePickerConfig = {
            // format: "dd/MM/yyyy HH:mm:ss",
            // parseFormats: ["yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm:ss"],
            // footer: "Currently #: kendo.toString(data,'dd-MM-yyyy HH:mm:ss')#"
            timeFormat: "HH:mm:ss",
            format: "MM/dd/yyyy HH:mm:ss",
            parseFormats: ["MM/dd/yyyy hh:mmtt", "MM/dd/yyyy HH:mm", "MM/dd/yyyy", "HH:mm"]
        }

        /** hàm chuyển kiểu dữ liệu datetime từ millisecon sang ngày/tháng/năm và ngược lại */
        function chanceDateToString(stringDate, mode) { // linhnn
            if (stringDate != undefined && mode == 'M_TO_DATE') {
                var date = new Date(stringDate);
                return (date.getDate() + '/' + date.getMonth() + '/' + date.getFullYear());
            }
            if (stringDate != undefined && mode == 'DATE_TO_M') {
                var arrDate = stringDate.split("/");
                var date = new Date(arrDate[2], arrDate[1], arrDate[0], 0, 0, 0, 0);
                return date.getTime();
            }
            if (stringDate != undefined && mode == 'DATEMONTH_TO_M') { // chuyển từ ngày tháng năm giờ phút giấy sang millisecon
                var arrDate = stringDate.split(" ");
                var DdMMYYYY = arrDate[0].split("/");
                var HHMmSs = arrDate[1].split(":");
                var date = new Date(DdMMYYYY[2], DdMMYYYY[1], DdMMYYYY[0], HHMmSs[0], HHMmSs[1], HHMmSs[2], 0);
                return date.getTime();
            }
            if (stringDate != undefined && mode == 'M_TO_DATEMONTH') {
                var date = new Date(stringDate);
                return (date.getDate() + '/' + date.getMonth() + '/' + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
            }
        }


        /**
         * hàm điền dữ liệu vào bảng . 15/11 các feild chưa có giá trị chính xác do service chưa đúng
         * @param  {JSON object} data dữ liệu của bảng
         * @return {Promise}      trả về một promise
         */


        function fillDataTable(data) {
            var deferred = $q.defer();
            if (data && data.length > 0 && !data[0].construction) { // nếu data không có trường cóntruction
                for (var i = 0; i < data.length; i++) {
                    if (data[i].constructionRequest === 0) {
                        data[i].construction = { id: "0", value: "Không thi công" }
                    } else {
                        data[i].construction = { id: "1", value: "Thi công" }
                    }
                }
            }
            var dataSource = new kendo.data.DataSource({
                pageSize: 20,
                data: data,
                autoSync: false,
                schema: {
                    model: {
                        fields: {
                            construction: { defaultValue: { id: 1, value: "Thi công" } },
                            quantity: { type: "number", validation: { min: 0, required: true } },
                            unit: { validation: { required: true } },
                            currentState: { validation: { required: true, message: "Nội dung không được để trống" } },
                            handoverContent: { validation: { required: true } },
                        }
                    }
                }
            });
            vm.options = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: dataSource,
                noRecords: true,
                onchange: onChange,
                messages: {
                    noRecords: gettextCatalog
                        .getString("Không có kết quả nào")
                },
                columns: [{
                        field: "a1",
                        title: "STT",
                        template: dataItem => $("#contextWork").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        width: 80
                    }, {
                        title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                        template: "<input type='checkbox' name='gridcheckbox' />",
                        width: 35
                    },
                    {
                        title: gettextCatalog
                            .getString("Nội dung bàn giao </br>để thực hiện"),
                        field: "handoverContent",
                        width: 150
                    },
                    {
                        title: gettextCatalog
                            .getString("Đơn vị"),
                        field: "unit",
                        width: 120
                    },
                    {
                        title: gettextCatalog
                            .getString("Số lượng"),
                        field: "quantity",
                        width: 120,
                    },
                    {
                        title: gettextCatalog
                            .getString("Tình trạng"),
                        field: "currentState",
                        width: 120
                    },
                    {
                        title: gettextCatalog.getString("Yêu cầu thi công"),
                        field: "construction",
                        attributes: { style: "padding:0" },
                        editor: categoryDropDownEditor,
                        template: "#=construction.value#",
                        width: 120
                    },
                    {
                        title: gettextCatalog
                            .getString("Ghi chú"),
                        field: "notes",
                        width: 100
                    }
                ]
            });
            deferred.resolve('done');
            return deferred.promise;
        }


        function categoryDropDownEditor(container, options) {
            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "value",
                    dataValueField: "id",
                    dataSource: [{ id: 0, value: "Không thi công" }, { id: 1, value: "Thi công" }]
                });
        }


        function changeValueInput(indexValue) {

        }

        function reloadTable(id) {
            if (id) {
                cWorkCompleteService.fetchAllItem(id).then(function(d) {
                    reloadBufferTable(d);
                }, function(error) {

                });
            } else {
                reloadBufferTable([]);
            }

        }




        function onChange() {
            // do something when click row of table
        }

        /**
         * function hideAButton() để ấn những nút trên thành header bar bị thừa
         * @return {undefined} hàm không trả về gì cả
         */
        function hideAButton() {
            var mapHideButton = new Map();
            mapHideButton.set("Xóa nhiều", "");
            mapHideButton.set("Sửa", "");
            $('#topBarContract').find('div').each(function() {
                var nameHideButton = $(this).attr('uib-tooltip');
                if (mapHideButton.has(nameHideButton)) {
                    var hideButton = $(this).closest("button");
                    hideButton.addClass("ng-hide");
                    if (hideButton.hasClass("border-radius-right")) {
                        hideButton.prev().addClass("border-radius-right");
                    } else {
                        if (hideButton.hasClass("border-radius-left")) {
                            hideButton.next().addClass("border-radius-left");
                        }
                    }
                }
            })
        }

        /**
         * hàm mở cửa sổ mới
         * @param  {string} key    key của cửa sổ mới 
         * @return {undefined}     hàm không trả về gì 
         */
        function changeSite() {
            // findConstruction30Service.goTo(key);
            // findConstruction30Service.deMo();
        }

        // get data from dodt
        function getDataFromService() {
            var deferred = $q.defer();
            vm.item = ProposalEvaluation.getItem();
            if(vm.item == null) {
    			vm.item = CommonService.getItem();
    		}
            deferred.resolve("done");
            return deferred.promise;
        }
        getDataFromService().then(function() {
            initData(vm.item);
        });

        $scope.$on("CWORK_COMPLETE.detail", function(event, item) {
            if (item === undefined) {

            } else {
                vm.item = item;
                initData(item);
            }

        });

        /** hàm cài đăt các giá trị ban đầu khi load lên màn hình */
        function initData(item) {

            if (item.currentStateHandover.currentStateHandoverId) {
                reloadTable(item.currentStateHandover.currentStateHandoverId);
            } else {
                reloadTable();
            }


            vm.ptgs.constructId = vm.item.constructId;
            vm.ptkt.constructId = vm.item.constructId;
            vm.reportObj = item.currentStateHandover;
            vm.reportObj.handoverDate = chanceDateToString(vm.reportObj.handoverDate, "M_TO_DATEMONTH");
            vm.reportObj.constructId = vm.item.constructId;

            delete vm.reportObj.constrtCode;
            delete vm.reportObj.contractCode;
            delete vm.reportObj.contractName;
            delete vm.reportObj.fwmodelId;
            delete vm.reportObj.defaultSortField;
            cWorkCompleteService.getListEmployeeByRole(vm.ptgs).then(function(data) {
                vm.showPtgt = data;
                vm.reportObj.aInChargeMonitorId = item.currentStateHandover.ainChargeMonitorId;
            });
            cWorkCompleteService.getListEmployeeByRole(vm.ptkt).then(function(data) {
                vm.showPtkt = data;
                vm.reportObj.bInChargeConstructId = item.currentStateHandover.binChargeConstructId;

            });
            if (item) { vm.item.currentStateHandover = item.currentStateHandover; }
            vm.item.signed_date = chanceDateToString(vm.item.signed_date, 'M_TO_DATE');
        }

        //---------------------------------------------------------/

        function addSubTable() {
            var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();

            var isEmpty = false;
            for (var i = 0; i < dataSource.length; i++) { // kiểm tra để cho phép thêm dòng mới
                if (checkEmptyObject(dataSource[i])) {
                    isEmpty = true;
                }
            }

            if (!isEmpty) {
                var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
                if (vm.coutRowAdded) { // neu da an them it nhat mot lan thi chay vao trong if

                    vm.demoData.splice(vm.demoData.length - 1, 1); // xóa object trằng cuối cùng trong demo data
                    vm.demoData.push(dataSource[dataSource.length - 1]); // thêm thằng cuối cùng trong datasource vào cuối demodata
                } else { // neu chua an them lan nao thi du lai cai du lieu cua bang roi moi them dong
                    vm.demoData = dataSource;
                }
                vm.isCheckAddRow = true;
                vm.coutRowAdded++;

                var emptyObj = {
                    "defaultSortField": "",
                    "currentStateHandoverListId": "",
                    "handoverContent": "",
                    "unit": "",
                    "quantity": "",
                    "currentState": "",
                    "constructionRequest": 1,
                    "notes": "",
                    "currentStateHandoverId": "",
                    "construction": { id: "1", value: "Thi công" }
                }
                vm.demoData.push(emptyObj);

                reloadBufferTable(vm.demoData);
            }
        }


        function reloadBufferTable(d) {
            fillDataTable(d).then(function() {
                refreshGrid(d);
            });
        }

        function refreshGrid(d) {
            var grid = vm.contextWork;
            grid.dataSource.data(d);
            grid.refresh();
        }

        function checkEmptyObject(obj) {
            if (
                obj.handoverContent === '' ||
                obj.currentState === '' ||
                obj.unit === '' ||
                obj.quantity === ''
            ) {
                return true;
            } else {
                return false;
            }
        }

        function saveSubTable() {

            if (vm.isCheckAddRow) {
                // data source khong doi theo nhưng thu dang trong bang
                var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
                var ObjectDTO = [];
                if (dataSource) {

                    for (var i = dataSource.length - vm.coutRowAdded; i < dataSource.length; i++) {
                        if (!checkEmptyObject(dataSource[i]))
                            ObjectDTO.push(dataSource[i]);
                    }


                    // report_result_service.createReportReultQuality(ObjectDTO);
                } else {

                };
            }
        }

        function saveAll() {
            var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
            var ObjectDTO = [];
            var bufferDataSource = angular.copy(dataSource);

            for (var i = 0; i < bufferDataSource.length; i++) {
                if (!checkEmptyObject(bufferDataSource[i])) {
                    bufferDataSource[i].constructionRequest = bufferDataSource[i].construction.id;
                    if (bufferDataSource[i].currentStateHandoverListId > 0)
                        bufferDataSource[i].currentStateHandoverId = vm.reportObj.currentStateHandoverId;
                    delete bufferDataSource[i].construction;
                    delete bufferDataSource[i].fwmodelId;
                    delete bufferDataSource[i].defaultSortField;
                    delete bufferDataSource[i].route;
                    delete bufferDataSource[i].reqParams;
                    delete bufferDataSource[i].fromServer;
                    delete bufferDataSource[i].parentResource;
                    delete bufferDataSource[i].restangularCollection;
                    ObjectDTO.push(bufferDataSource[i]);
                }
            }
            vm.reportObj.listCurrentStateHandover = ObjectDTO;
            delete vm.reportObj.statusCaText;
            var DataExport = angular.copy(vm.reportObj);
            DataExport.handoverDate = chanceDateToString(DataExport.handoverDate, "DATEMONTH_TO_M");
            if (DataExport.currentStateHandoverId > 0) {
                cWorkCompleteService.updateAllReport(DataExport).then(function(data) {
                    toastr.success("Cập nhật thành công");
                    reloadTable(vm.item.currentStateHandover.currentStateHandoverId);
                }, function(error) {
                    toastr.error("Cập nhật thất bại");
                });
            } else {
                cWorkCompleteService.createNewReport(DataExport).then(function(data) {
                    toastr.success("Thêm mới thành công");
                    reloadTable(vm.item.currentStateHandover.currentStateHandoverId);
                }, function(error) {
                    toastr.error("Thêm mới thất bại");
                });
            }

            //  
        }

        function save() {

        }

        function multiDelete() {
            var selectedRow = [];
            var grid = vm.contextWork;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);

                if (checkbox.is(':checked')) {
                    // Push id into selectedRow
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);

                    selectedRow.push(dataItem.currentStateHandoverListId);
                }
            });

            if (selectedRow.length == 0) {
                toastr.warning(message.recordRequired);
                return;
            }


            if (confirm('Xác nhận xóa')) {
                cWorkCompleteService.deleteMutiRow(selectedRow).then(function() {
                    toastr.success("Xóa bản ghi thành công");
                    reloadTable(vm.item.currentStateHandover.currentStateHandoverId);
                }, function(errResponse) {
                    if (errResponse.status == 302) {
                        toastr.error("Bản ghi đang được sử dụng");
                    } else {
                        toastr.error(message.deleteError);
                    }
                });
            }

        }

        function exportFile() {
            var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
            var ObjectDTO = [];
            var bufferDataSource = angular.copy(dataSource);
            for (var i = 0; i < bufferDataSource.length; i++) {
                if (!checkEmptyObject(bufferDataSource[i])) {
                    bufferDataSource[i].constructionRequest = bufferDataSource[i].construction.id;
                    if (bufferDataSource[i].constructionRequest === "1") {
                        bufferDataSource[i].requestYes = "Thi công";
                        bufferDataSource[i].requestNo = " ";
                    } else {
                        bufferDataSource[i].requestYes = " ";
                        bufferDataSource[i].requestNo = "Không thi công";
                    }
                    if (bufferDataSource[i].currentStateHandoverListId > 0)
                        bufferDataSource[i].currentStateHandoverId = vm.reportObj.currentStateHandoverId;
                    delete bufferDataSource[i].construction;
                    delete bufferDataSource[i].fwmodelId;
                    delete bufferDataSource[i].defaultSortField;
                    delete bufferDataSource[i].route;
                    delete bufferDataSource[i].reqParams;
                    delete bufferDataSource[i].fromServer;
                    delete bufferDataSource[i].parentResource;
                    delete bufferDataSource[i].restangularCollection;
                    ObjectDTO.push(bufferDataSource[i]);
                }
            }
            vm.reportObj.listCurrentStateHandover = ObjectDTO;
            var dataExport = angular.copy(vm.reportObj);
            dataExport.contractCode = vm.item.contractCode;
            dataExport.constrtName = vm.item.constrtName;
            dataExport.station_code = vm.item.stationCode;
            dataExport.handoverDate = chanceDateToString(dataExport.handoverDate, "DATEMONTH_TO_M");
            dataExport.chargeMonitorName = $("#showPtgt").data("kendoDropDownList").text();;
            dataExport.chargeConstructName = $("#showPtkt").data("kendoDropDownList").text();
            dataExport.conclusion = $("#conclusion").data("kendoDropDownList").text();
            var date = new Date(dataExport.handoverDate);
            dataExport.date = date.getDate();
            dataExport.month = date.getMonth();
            dataExport.year = date.getFullYear();


            cWorkCompleteService.exportNewReport(dataExport).then(function(data) {
                window.location = "/qlhc-service/fileservice/download?fileName=" + data.fileName;
            });
        }

    }
})();