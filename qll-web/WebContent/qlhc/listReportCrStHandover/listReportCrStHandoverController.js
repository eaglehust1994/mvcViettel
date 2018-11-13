(function() {
    'use strict';

    var controllerId = 'listReportCrStHandoverController';

    angular.module('MetronicApp').controller(controllerId,
        findConstruction30Func);

    /* @ngInject */
    function findConstruction30Func($scope, RestEndpoint,
        $rootScope, $timeout, PopupConst, Constant,
        kendoConfig, gettextCatalog,
        $kWindow, CommonService, $q, reportCrStHandoverService, listReportCrStHandoverService, ProposalEvaluation, theApproval) {
        var vm = this;
        vm.hideAButton = hideAButton;
        vm.onChange = onChange;
        vm.changeSite = changeSite;
        vm.doSearch = doSearch;
        vm.chkSelectAll = chkSelectAll;
        vm.chkSelectAllSubTable = chkSelectAllSubTable;
        vm.showGrid = showGrid;
        vm.remove = onRemove;
        vm.save = saveAll;
        vm.signCA = signCA;
        vm.exportFile = exportFile;
        vm.deleteSubTable = multiDelete;
        vm.addSubTable = addSubTable;
        vm.validatorOptions = kendoConfig.get('validatorOptions');
        vm.onApproval = onApproval;
        vm.item = {};
        vm.item2 = {};
        vm.deleteRowSubTable = [];
        vm.add = add;
        vm.showDetail = false;
        vm.showTrinhDuyet = true;
        vm.showPheduyet = false;
        vm.disableAll = false;
        vm.reportObj = {
            constructId: '',
            currentStateHandoverId: '',
            code: '',
            "ainChargeMonitorId": '',
            "binChargeConstructId": '',
            handoverDate: '',
            handoverPlace: '',
            groundHandoverContent: '',
            otherDocuments: '',
            otherComments: '',
            "conclusion": 1,
            createdDate: '',
            createdUserId: '',
            approvalDate: '',
            statusCa: '',
            isActive: '',
            statusCaText: ''
        }

        function reportObjContructer(obj) {
            switch (Number(obj.statusCa)) {
                case 0:
                    { obj.statusCaText = 'Soạn thảo'; break; }
                case 1:
                    { obj.statusCaText = 'Trình duyệt'; break; }
                case 2:
                    { obj.statusCaText = 'Đã duyệt'; break; }
                case 3:
                    { obj.statusCaText = 'Từ chối duyệt'; }
            }
            obj.constrtCode = vm.item.constrtCode;
            obj.contractCode = vm.item.contractCode;
            obj.contractName = vm.item.contractName;
            return obj;
        }


        /**
         * hàm điền dữ liệu vào bảng . 15/11 các feild chưa có giá trị chính xác do service chưa đúng
         * @param  {JSON object} data dữ liệu của bảng
         * @return {Promise}      trả về một promise
         */
        function fillDataTable(data) {

            var deferred = $q.defer();
            var tranerData = [];
            for (var el in data) {
                var tranferObj = new reportObjContructer(data[el]);
                tranerData.push(tranferObj);
            }
            vm.options = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: tranerData, //data.plain(),
                change: onChange,
                noRecords: true,
                editable: false,
                messages: {

                    noRecords: gettextCatalog.getString("Hiện tại chưa có biên bản")
                },
                columns: [{
                        title: gettextCatalog.getString("STT"),
                        field: "stt",
                        template: dataItem => $("#reportCrStHandoverGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:center;" },
                        width: 70,
                    },
                    {
                        title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                        template: "<input  type='checkbox' name='gridcheckbox'/>",
                        attributes: { style: "text-align:center;" },
                        headerAttributes: { style: "text-align:center;" },
                        width: 50
                    },
                    {
                        title: gettextCatalog.getString("Mã biên bản"),
                        field: "code",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                        width: 150
                    },
                    {
                        title: gettextCatalog.getString("Mã công trình"),
                        field: "constrtCode",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                        width: 150
                    },
                    {
                        title: gettextCatalog.getString("Mã hợp đồng"),
                        field: "contractCode",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                        width: 150
                    },
                    {
                        title: gettextCatalog.getString("Tên hợp đồng"),
                        field: "contractName",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                        width: 150
                    },
                    {
                        title: gettextCatalog.getString("Trạng thái"),
                        field: "statusCaText",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;", class:"statusColumn" },
                        width: 150
                    }
                ]
            });
            deferred.resolve('done');
            return deferred.promise;
        }

        function reloadTable() {
            if (vm.item.constructId) {
                vm.ptgs.constructId = vm.item.constructId;
                vm.ptkt.constructId = vm.item.constructId;
                vm.ptkt.contractCode = vm.item.contractCode;
                vm.ptkt.contractCode = vm.item.contractCode;
                reportCrStHandoverService.getListEmployeeByRole(vm.ptgs).then(function(data) {
                    vm.showPtgt = data;

                    vm.firstIdOfPtgs = data.length > 0 ? data[0].id : undefined;
                });
                reportCrStHandoverService.getListEmployeeByRole(vm.ptkt).then(function(data) {
                    vm.showPtkt = data;
                    vm.firstIdOfPtkt = data.length > 0 ? data[0].id : undefined;
                });

                listReportCrStHandoverService.fetchAllItemByConstructId(vm.item.constructId).then(function(data) {
                    if (data.data.length <= 0) {
                        vm.disabledButtomAdd = false;
                    } else { vm.disabledButtomAdd = true; }
                    fillDataTable(data.data).then(function(d) {
                        try {
                            var grit = vm.reportCrStHandoverGrid;
                            if (grit) {

                                grit.dataSource.data(data.data);
                                grit.refresh();

                            }
                        } catch (e) {

                        }
                    });
                }, function(error) {});
            } else {

            }

        }

        reloadTable();

        /** function thuc hien trinh ki */
        function onApproval() {
            var mapIndex = new Map();
            if ($('.k-grid-content').find('td').hasClass('k-dirty-cell')) {
                $('.k-grid-content').find('tr').each(function() {
                    if ($(this).find('td').hasClass('k-dirty-cell')) {
                        mapIndex.set($(this).index(), "1");
                    }
                });
                // data source khong doi theo nhưng thu dang trong bang				
                var dataSource = $("#contractGrid").data().kendoGrid.dataSource.view();
                if (mapIndex.size > 0 && dataSource) {
                    var count = 0;
                    var ObjectDTO = [];
                    for (var el in dataSource) {
                        if (mapIndex.has(count)) {
                            ObjectDTO.push(dataSource[el]);
                        }
                        count++;
                    }
                }
            }
        }
        //ngoccx
        //huy trinh duyet
        vm.cancelApprovalBtn = function() {
                var grid = vm.reportCrStHandoverGrid;
                if (grid.select() == null || grid.select().length == 0) {
                    toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
                    return;
                }
                vm.item2.tableName = 'CURRENT_STATE_HANDOVER';
                vm.item2.tableId = vm.item2.currentStateHandoverId;
                vm.item2.tableIdField = 'CURRENT_STATE_HANDOVER_ID';
                if (vm.item2.statusCa == 1) {
                    if (vm.item2.createdUserId != Constant.user.srvUser.userId) {
                        toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
                        return;
                    } else {
                        if (confirm('Xác nhận hủy trình duyệt')) {
                            listReportCrStHandoverService.cancelAprroval(vm.item2).then(function() {
                                status = true;
                                $rootScope.$broadcast("ChangeStatusHuyDuyet", status);
                                reloadTable();
                                toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
                            }, function() {
                                toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
                                return;
                            });
                        }
                    }
                } else {
                    toastr.warning("Chỉ trình duyệt mới được hủy trình duyệt");
                }
            }
            /** hàm thêm mới một bản ghi vào bảng */
        function add() {
            vm.item.currentStateHandover = {};
            vm.showDetail = true;
            vm.disableForm = false;
            vm.item.currentStateHandover.conclusion = 1;
            vm.deleteRowSubTable = [];
            initData(vm.item);
        }

        function onChange() {
            // do something when click row of table
            var tr = vm.reportCrStHandoverGrid.select().closest("tr");
            var dataItem = vm.reportCrStHandoverGrid.dataItem(tr);
            vm.item.theApproval = $.extend({}, dataItem);
            vm.item2 = dataItem;

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

        function chkSelectAll(item) {
            var grid = vm.reportCrStHandoverGrid;
            chkSelectAllBase(vm.chkAll, grid);
        }

        function chkSelectAllSubTable(item) {
            var grid = vm.contextWork;
            chkSelectAllBase(vm.chkAllDetail, grid);
        }

        /**
         * hàm mở cửa sổ mới
         * @param  {string} key    key của cửa sổ mới 
         * @return {undefined}     hàm không trả về gì 
         */
        function changeSite(key) {
            listReportCrStHandoverService.goTo(key);
        }

        function showGrid() {
            if (vm.reportCrStHandoverGrid.select().length > 0 || vm.showDetail) {
                var tr = vm.reportCrStHandoverGrid.select().closest("tr");
                var dataItem = vm.reportCrStHandoverGrid.dataItem(tr);
                if (dataItem)
                    if (dataItem.statusCa === 1 || dataItem.statusCa === 2) {
                        vm.disabledButtomSave = true;
                        vm.disableAll = true;
                        
                    } else {
                        vm.disabledButtomSave = false;
                        vm.disableAll = false;
                    }
                if(dataItem.createdUserId != Constant.user.srvUser.userId){
					vm.disableAll = true;
				}else{
					vm.disableAll = false;
				}
                vm.item.currentStateHandover = $.extend({}, dataItem);

                initData(vm.item);
                vm.disableForm = false;
                vm.isCheckAddRow = false;
                vm.coutRowAdded = 0;
                vm.demoData = [];
                vm.deleteRowSubTable = [];
                vm.showDetail = !vm.showDetail;
                if (vm.showDetail && vm.item.currentStateHandover.statusCa === 1) {
                    vm.showTrinhDuyet = false;
                } else {
                    vm.showTrinhDuyet = true;
                }
                vm.showPheduyet = false;
            } else {
                toastr.warning("Chọn biên bản trước");
            }

        }

        function doSearch() {
            if (vm.validator.validate()) {}
        }


        /**	code lấy dữ liệu dodt */
        function getDataFromService() {
            var deferred = $q.defer();
            vm.item = ProposalEvaluation.getItem();

            deferred.resolve("done");
            return deferred.promise;
        }
        getDataFromService().then(function() {
            reloadTable();
        });
        $scope.$on("ProposalEvaluation.detail", function(event, item) {
            if (item === undefined) {

            } else {
                vm.item = item;
                reloadTable();
                // fillDataToForm(item);
            }
        });
        //------------------------------------------------------------
        /**gởi dữ liệu khi change site */
        //-------------------- code lấy từ report detail---------------------------------------------
        //============================================================================================



        vm.demoSelect = "1";
        vm.listDataFillTable = [];
        vm.isCheckAddRow = false;
        vm.addSubTable = addSubTable;
        // vm.saveSubTable = saveSubTable;
        // vm.deleteSubTable = multiDelete;
        // vm.save = saveAll;
        // vm.exportFile = exportFile;
        vm.coutRowAdded = 0;
        // vm.changeValueInput = changeValueInput;
        vm.ptgs = { //Phụ trách giám sát thi công
            constructId: '',
            contractCode: vm.item.contractCode
        }
        vm.ptkt = { //Phụ trách thi công
            constructId: '',
            contractCode: vm.item.contractCode
        }
        vm.conclusion = [{
                "id": "0",
                "content": "Không đồng ý"
            }, {
                "id": "1",
                "content": "Đồng ý"
            }]
            // vm.item = {};
        listReportCrStHandoverService.getRoleId().then(function(data) {
            //vm.role = data;
            vm.ptgs.roleid = Constant.ROLE_ID["employee_roleID_CDT_PTGST"];//4
            vm.ptkt.roleid = Constant.ROLE_ID["employee_roleID_DT_PTTC"];//1

        }).catch(function(data, status) {

        });

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

        function chanceDateToString(stringDate, mode) { // linhnn
            if (stringDate != undefined && mode == 'M_TO_DATE') {
                var date = new Date(stringDate);
                return (date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear());
            }
            if (stringDate != undefined && mode == 'DATE_TO_M') {
                var arrDate = stringDate.split("/");
                var date = new Date(arrDate[2], arrDate[1] - 1, arrDate[0], 0, 0, 0, 0);
                return date.getTime();
            }
            if (stringDate != undefined && mode == 'DATEMONTH_TO_M') { // chuyển từ ngày tháng năm giờ phút giấy sang millisecon
                var arrDate = stringDate.split(" ");
                var DdMMYYYY = arrDate[0].split("/");
                var HHMmSs = arrDate[1].split(":");
                var date = new Date(DdMMYYYY[2], DdMMYYYY[1] - 1, DdMMYYYY[0], HHMmSs[0], HHMmSs[1], HHMmSs[2], 0);
                return date.getTime();
            }
            if (stringDate != undefined && mode == 'M_TO_DATEMONTH') {
                var date = new Date(stringDate);
                return (date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
            }
        }
        /** hàm cài đăt các giá trị ban đầu khi load lên màn hình */
        function initData(item) {


            if (item.currentStateHandover.currentStateHandoverId) {
                reloadTableDetail(item.currentStateHandover.currentStateHandoverId);
            } else {
                reloadTableDetail();
            }
            vm.reportObj = item.currentStateHandover;
            $(document).ready(function() {
                vm.reportObj.ainChargeMonitorId = item.currentStateHandover.ainChargeMonitorId == undefined ? vm.firstIdOfPtgs : item.currentStateHandover.ainChargeMonitorId;
                vm.reportObj.binChargeConstructId = item.currentStateHandover.binChargeConstructId == undefined ? vm.firstIdOfPtkt : item.currentStateHandover.binChargeConstructId;
            });
            // vm.reportObj.handoverDate = chanceDateToString(vm.reportObj.handoverDate, "M_TO_DATEMONTH");
            vm.reportObj.handoverDate = vm.reportObj.handoverDate;
            vm.reportObj.constructId = vm.item.constructId;
            delete vm.reportObj.constrtCode;
            delete vm.reportObj.contractCode;
            delete vm.reportObj.contractName;
            delete vm.reportObj.fwmodelId;
            delete vm.reportObj.defaultSortField;

            // if (item) { vm.item.currentStateHandover = item.currentStateHandover; }
            // vm.item.signed_date = chanceDateToString(vm.item.signed_date, 'M_TO_DATE');
        }

        function reloadBufferTable(d) {
            vm.isCheckAddRow = false;
            vm.coutRowAdded = 0;
            vm.demoData = [];
            fillDataTableDetail(d).then(function(result) {
                refreshGrid(d);
            });
        }

        function refreshGrid(d) {
            var grid = vm.contextWork;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        function reloadTableDetail(id) {
            if (id) {
                reportCrStHandoverService.fetchAllItem(id).then(function(d) {
                    reloadBufferTable(d.plain());
                }, function(error) {

                });
            } else {
                reloadBufferTable([]);
            }

        }

        function fillDataTableDetail(data) {
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
                            quantity: {
                                validation: {
                                    min: 0,
                                    required: { message: "Nội dung không được để trống" },
                                    messages: {
                                        custom: "Nội dung không được để trống"
                                    }
                                }
                            },
                            unit: {
                                validation: {
                                    required: { message: "Nội dung không được để trống" },
                                    messages: {
                                        custom: "Nội dung không được để trống"
                                    }
                                }
                            },
                            currentState: {
                                validation: {
                                    required: { message: "Nội dung không được để trống" },
                                    messages: {
                                        custom: "Nội dung không được để trống"
                                    }
                                }
                            },
                            handoverContent: {
                                validation: {
                                    required: { message: "Nội dung không được để trống" },
                                    messages: {
                                        custom: "Nội dung không được để trống"
                                    }
                                }
                            },
                            notes: {
                                validation: {
                                    required: { message: "Nội dung không được để trống" },
                                    messages: {
                                        custom: "Nội dung không được để trống"
                                    }
                                }
                            },
                        }
                    }
                }
            });
            vm.optionsDetail = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: dataSource,
                noRecords: true,
                onchange: onChangeDetail,
                messages: {
                    noRecords: gettextCatalog
                        .getString("Hiện tại chưa có nội dung")
                },
            	edit: function(e) {
			         e.container.find("input[name=handoverContent]").attr("maxlength", 1000);
			         e.container.find("input[name=unit]").attr("maxlength", 200);
			         e.container.find("input[name=quantity]").attr("maxlength", 34);
			         e.container.find("input[name=currentState]").attr("maxlength", 1000);
			         e.container.find("input[name=construction]").attr("maxlength", 200);
			         e.container.find("input[name=notes]").attr("maxlength", 500);	         
            	},
                columns: [{
                        field: "a1",
                        title: "STT",
                        template: dataItem => $("#contextWork").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        headerAttributes: { style: "text-align:center;padding-bottom: 10px;" },
                        attributes: { style: "text-align:center;" },
                        width: 70
                    }, {
                        title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAllSubTable();' ng-model='vm.chkAllDetail' />",
                        template: "<input type='checkbox' name='gridcheckbox' />",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:center;" },
                        width: 35
                    },
                    {
                        title: gettextCatalog
                            .getString("Nội dung bàn giao </br>để thực hiện"),
                        field: "handoverContent",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                        width: 150
                    },
                    {
                        title: gettextCatalog
                            .getString("Đơn vị"),
                        field: "unit",
                        headerAttributes: { style: "text-align:center; padding-bottom: 10px;" },
                        attributes: { style: "text-align:left;" },
                        width: 120
                    },
                    {
                        title: gettextCatalog
                            .getString("Số lượng"),
                        headerAttributes: { style: "text-align:center;padding-bottom: 10px;" },
                        attributes: { style: "text-align:right;" },
                        field: "quantity",
						template: function(dataItem) {
                            if ($.isNumeric(dataItem.quantity) && dataItem.quantity >= 0) {
                            	dataItem.quantity = parseFloat(Number(dataItem.quantity).toFixed(3));
                                return parseFloat(Number(dataItem.quantity).toFixed(3));
                            }
                            else{dataItem.quantity = 0;
                            return 0;}
                        },
                    decimals: 3,
					validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
                        width: 120,
                    },
                    {
                        title: gettextCatalog
                            .getString("Tình trạng"),
                        headerAttributes: { style: "text-align:center;padding-bottom: 10px;" },
                        attributes: { style: "text-align:left;" },
                        field: "currentState",
                        width: 120
                    },
                    {
                        title: gettextCatalog.getString("Yêu cầu thi công"),
                        field: "construction",
                        attributes: { style: "padding:0" },
                        editor: categoryDropDownEditor,
                        headerAttributes: { style: "text-align:center;padding-bottom: 10px;" },
                        template: "#=construction.value#",
                        width: 140
                    },
                    {
                        title: gettextCatalog
                            .getString("Ghi chú"),
                        headerAttributes: { style: "text-align:center;padding-bottom: 10px;" },
                        attributes: { style: "text-align:left;" },
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
                    dataSource: [{ id: 0, value: "Không thi công" },
                        { id: 1, value: "Thi công" }
                    ]
                });
        }

        function onChangeDetail() {
            // do some thing when subtable click
        }

        function addSubTable() {
            var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
            var grid = $("#contextWork").data("kendoGrid");
            
            var isEmpty = false;
            for (var i = 0; i < dataSource.length; i++) { // kiểm tra để cho phép thêm bản ghi mới
                if (checkEmptyObject(dataSource[i])) {
                    isEmpty = true;
                }
            }

            if (!isEmpty) {
                var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
                if (vm.coutRowAdded) { // neu da an them it nhat mot lan thi chay vao trong if
                	grid.dataSource.insert();
                   // vm.demoData.splice(vm.demoData.length - 1, 1); // xóa object trằng cuối cùng trong demo data
                   // vm.demoData.push(dataSource[dataSource.length - 1]); // thêm thằng cuối cùng trong datasource vào cuối demodata
                } else { // neu chua an them lan nao thi du lai cai du lieu cua bang roi moi them dong
                    
                    grid.dataSource.insert();
                    vm.demoData = dataSource;
                    return;
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


        function checkEmptyObject(obj) {
            if (
                obj.handoverContent === '' ||
                obj.currentState === '' ||
                obj.unit === '' ||
                obj.quantity === '' ||
                obj.notes === ''
            ) {
                toastr.warning("Phải điền đầy đủ các trường trong bảng");
                return true;

            } else {
                return false;
            }
        }

        vm.appro = {
            "statusCa": 3,
            "employeeId": 3513,
            "comments": "",
            "currentStateHandoverId": 343
        }

        function saveAll() {
            // author : linhnn +truongtx
            // code tìm bản ghi thêm
            // var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
            // var ObjectDTO = [];
            // if (dataSource) {
            //   for (var i = dataSource.length - vm.coutRowAdded; i < dataSource.length; i++) {
            //     if (!checkEmptyObject(dataSource[dataSource.length - 1])) {
            //       ObjectDTO.push(dataSource[i]);
            //     } else {
            //       // xu li bản ghi thiếu trường ở đấy
            //     }
            //   }
            //   // report_result_service.createReportReultQuality(ObjectDTO);
            // } else {
            //   
            // }

            // // // code tìm bản ghi bị sửa
            // var mapIndex = new Map();
            // 
            // if ($('.k-grid-content').find('td').hasClass('k-dirty-cell')) {
            //   $('.k-grid-content').find('tr').each(function () {
            //     if ($(this).find('td').hasClass('k-dirty-cell')) {
            //       mapIndex.set($(this).index(), "1");
            //     }
            //   });
            //   // data source khong doi theo nhưng thu dang trong bang
            //   var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
            //   
            //   if (mapIndex.size > 0 && dataSource) {
            //     var count = 0;
            //     for (var i = 0; i < dataSource.length - vm.coutRowAdded; i++) {
            //       if (mapIndex.has(count)) {
            //         // phải check số âm trong mỗi bảng ở đây 
            //         ObjectDTO.push(dataSource[i]);
            //       }
            //       count++;
            //     }
            //   }
            // }

            // vm.reportObj.listReportCrStHandover = ObjectDTO;
            // delete vm.reportObj.statusCaText;
            //  
            // reportCrStHandoverService.updateAllReport(cm.reportObj).then(function(data){
            //   
            // })

            if (vm.showPheduyet) {
                vm.appro.currentStateHandoverId = vm.item.currentStateHandover.currentStateHandoverId;
                vm.appro.employeeId = Constant.user.srvUser.catEmployeeId;
                listReportCrStHandoverService.appro(vm.appro).then(function(data) {
                    var x = data;
                    if (x == '1') {
                        toastr.warning("Chưa đến lượt duyệt");
                        return;
                    }
                    if (x == '2') {
                        toastr.warning("Đã duyệt rồi");
                        vm.showDetail = false;
                        reloadTable()
                        return;
                    }
                    if (x == '4') {
                        toastr.success("Từ chối duyệt thành công");
                        vm.showDetail = false;
                        reloadTable()
                    }
                    if (x == '3') {
                        toastr.success("Duyệt thành công");
                        vm.showDetail = false;
                        reloadTable()
                    }
                    if (x == '0') {
                        toastr.warning("Lỗi xảy ra");
                        return;
                    }
                    if (x == '5') {
                        toastr.warning("Bạn không được phép duyệt");
                        return;
                    }
                }, function(error) {
                    toastr.error("Duyệt lỗi");
                });
                return;
            }
            if (vm.validator.validate()) {
                var dataSource = $("#contextWork").data().kendoGrid.dataSource.data();
                var ObjectDTO = [];
                var bufferDataSource = angular.copy(dataSource);
                if (bufferDataSource.length === 0) {
                    toastr.warning("Bảng nội dung không được trống");
                    return;
                }

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
                    } else {
                        return false;
                    }
                }
                vm.reportObj.listCurrentStateHandover = ObjectDTO;
                //truongtx
                if(ObjectDTO[0].quantity < 0){
                	toastr.error("Số lượng Không được âm");
                	return;
                }
                vm.reportObj.isActive = "1";
                vm.reportObj.statusCa = "0";
                delete vm.reportObj.statusCaText;
                var DataExport = angular.copy(vm.reportObj);
                // DataExport.handoverDate = chanceDateToString(DataExport.handoverDate, "DATEMONTH_TO_M");

                if (DataExport.currentStateHandoverId > 0) {
                    //minhpvn check quyen 
                    if (vm.item.currentStateHandover.createdUserId == Constant.user.srvUser.userId) {
                        if (DataExport.statusCa == "3") {
                            DataExport.statusCa = "0";
                        }
                        reportCrStHandoverService.updateAllReport(DataExport).then(function(data) {
                            toastr.success("Cập nhật thành công");
                            vm.showDetail = false;
                            reloadTableDetail(vm.item.currentStateHandover.currentStateHandoverId);
                            reloadTable();
                            vm.coutRowAdded = 0;
                            vm.item.theApproval = angular.copy(DataExport);
                        }, function(error) {
                            toastr.error("Cập nhật thất bại");
                        });
                    } else {
                        toastr.error("Bạn không có quyền sửa");
                    }
                    //end minhpvn check quyen 
                } else {
                    var idCreater = Constant.getUser().srvUser.userId;
                    DataExport.createdUserId = idCreater;
                    reportCrStHandoverService.createNewReport(DataExport).then(function(data) {
                        toastr.success("Thêm mới thành công");
                        reloadTableDetail(vm.item.currentStateHandover.currentStateHandoverId);
                        showGrid();
                        reloadTable();
                    }, function(error) {
                        toastr.error("Thêm mới thất bại");
                    });
                }
                if (vm.deleteRowSubTable.length > 0) {
                    reportCrStHandoverService.deleteMutiRow(vm.deleteRowSubTable).then(function() {
                        reloadTableDetail(vm.item.currentStateHandover.currentStateHandoverId);
                        vm.deleteRowSubTable = [];
                    }, function(errResponse) {

                    });
                }

            } else {
                toastr.warning("Điền đầy đủ các trường");
            }
        }

        function onRemove() {
            var selectedRow = [];
            var isShowtoart = false;
            var grid = vm.reportCrStHandoverGrid;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);
                if (checkbox.is(':checked')) {
                    // Push id into selectedRow
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);

                    if (dataItem.statusCa === 2 || dataItem.statusCa === 1) {
                        if (!isShowtoart) {
                            toastr.warning("Không được xóa bản ghi đã trình duyệt/phê duyệt");
                            isShowtoart = true;
                        }
                    } else {
                        if (dataItem.createdUserId === Constant.user.srvUser.userId) {
                            selectedRow.push(dataItem.currentStateHandoverId);
                        } else {
                            toastr.warning("Bạn không có quyền xóa bản ghi này !");
                        }

                    }
                }
            });

            if (selectedRow.length > 0 && confirm("Xác nhận xóa ? ")) {
                listReportCrStHandoverService.deleteListItem(selectedRow).then(function(success) {
                    reloadTable();
                    toastr.success("Xóa thành công");
                }, function(error) {
                    toastr.error("Xóa thất bại");
                });
            } else if (selectedRow.length > 0) {
                if (!isShowtoart)
                    toastr.warning("Chọn bản ghi trước khi xóa");
            }

        }



        function multiDelete() {
            var dataSource = $("#contextWork").data().kendoGrid.dataSource.view();
            var grid = vm.contextWork;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);

                if (checkbox.is(':checked')) {
                    // Push id into selectedRow
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    if (dataItem.currentStateHandoverListId) {
                        vm.deleteRowSubTable.push(dataItem.currentStateHandoverListId);
                    }
                    var index = dataSource.indexOf(dataItem);
                    if (index > -1) { dataSource.splice(index, 1); }

                }
            });
            reloadBufferTable(dataSource);
            // if (selectedRow.length == 0) {
            // 	toastr.warning("Chọn bản ghi để xóa trước");
            // 	return;
            // }

            // 
            // if (confirm('Xác nhận xóa')) {
            // reportCrStHandoverService.deleteMutiRow(selectedRow).then(function () {
            // 	toastr.success("Xóa bản ghi thành công");
            // 	reloadTableDetail(vm.item.currentStateHandover.currentStateHandoverId);
            // }, function (errResponse) {
            // 	if (errResponse.status == 302) {
            // 		toastr.error("Bản ghi đang được sử dụng");
            // 	} else {
            // 		toastr.error("Xóa thất bại");
            // 	}
            // });
            // }

        }

        function exportFile() {

            if (vm.showDetail) {
                $('#loading').show();
                listReportCrStHandoverService.exportOne(vm.item.currentStateHandover.currentStateHandoverId).then(function(data) {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            } else {
                var list = [];
                var grid = vm.reportCrStHandoverGrid;
                grid.table.find("tr").each(function(idx, item) {
                    var row = $(item);
                    var checkbox = $('[name="gridcheckbox"]', row);
                    if (checkbox.is(':checked')) {
                        var tr = grid.select().closest("tr");
                        var dataItem = grid.dataItem(item);
                        list.push(dataItem.currentStateHandoverId);

                    }
                });
                if (list.length > 0) {
                    $('#loading').show();
                    listReportCrStHandoverService.downloadFileZip(list).then(function() {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });
                } else {
                    if (vm.reportCrStHandoverGrid.select().length > 0) {
                        var tr = vm.reportCrStHandoverGrid.select().closest("tr");
                        var dataItem = vm.reportCrStHandoverGrid.dataItem(tr);
                        $('#loading').show();
                        listReportCrStHandoverService.exportOne(dataItem.currentStateHandoverId).then(function(data) {
                            window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                        }).catch(function() {
                            toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                            return;
                        }).finally(function() {
                            $('#loading').hide();
                        });
                    } else {
                        toastr.warning("Chọn biên bản trước");
                    }
                }
            }

        }
        vm.exportListFileDOC = exportListFileDOC;

        function exportListFileDOC() {
            var list = [];
            var grid = vm.reportCrStHandoverGrid;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);
                if (checkbox.is(':checked')) {
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    list.push(dataItem.currentStateHandoverId);

                }
            });
            if (list.length > 0) {
                $('#loading').show();
                listReportCrStHandoverService.downloadFileDOCZip(list).then(function() {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            } else {
                if (vm.reportCrStHandoverGrid.select().length > 0) {
                    var tr = vm.reportCrStHandoverGrid.select().closest("tr");
                    var dataItem = vm.reportCrStHandoverGrid.dataItem(tr);
                    $('#loading').show();
                    listReportCrStHandoverService.exportDoc(dataItem.currentStateHandoverId).then(function(data) {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });
                } else {
                    toastr.warning("Chọn biên bản trước");
                }
            }
        }

        function signCA() { // trinh duyet
            if (vm.reportCrStHandoverGrid.select().length > 0 || vm.showDetail) {

                var dataItemOne = angular.copy(vm.item.theApproval);
                if (vm.item.theApproval.currentStateHandoverId === undefined) {
                    toastr.warning("Bản ghi chưa được tạo");
                    return;
                }

                if (dataItemOne.statusCa === 0) {
                    $('#loading').show();
                    listReportCrStHandoverService.theApproval(dataItemOne.currentStateHandoverId).then(function(data) {

                        var theApprovalData = {
                            code: '',
                            constructId: '',
                            constrCompReMapId: '',
                            stringEmployee: '',
                            roleId: [vm.ptkt.roleid, vm.ptgs.roleid],
                            roleName: ["Phụ trách kỹ thuật nhà thầu thi công", "Phụ trách giám sát thi công chủ đầu tư"],
                            isSign: '',
                            path: '',
                            nameFile: ''
                        };
                        theApprovalData.code = dataItemOne.code;
                        theApprovalData.constructId = dataItemOne.constructId;
                        theApprovalData.constrCompReMapId = data[0];
                        theApprovalData.stringEmployee = dataItemOne.binChargeConstructId + "," + dataItemOne.ainChargeMonitorId;
                        theApprovalData.isSign = "theApproval";
                        theApprovalData.path = data[2]; // nên là đường dẫn tương đối . nhưng ở đây đã điền là tên file
                        theApprovalData.nameFile = 'BM-TCNT-21.pdf';
                        theApproval.setItem(theApprovalData);
                        vm.goTo('THE_APPROVAL');
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });
                } else {
                    toastr.warning("Bản ghi này đã trình ký");
                }
            } else {
                toastr.warning("Bạn chưa chọn biên bản");
            }


        }
        $scope.$on("ChangeStatusApproval", function(event, result) {
            if (result) {
                reloadTable();
            }
        });

        vm.goTo = goTo;

        function goTo(menuKey) {

            var hasPerm = true;

            if (hasPerm) {
                var template = Constant.getTemplateUrl(menuKey);
                postal.publish({
                    channel: "Tab",
                    topic: "open",
                    data: template
                });
            } else {
                //toastr.error(gettextCatalog.getString("Tài khoản đăng nhập hiện tại không được phép truy cập vào chức năng này!"));
            }

        }

        vm.exportDoc = exportDoc;

        function exportDoc() {

            if (vm.showDetail) {
                $('#loading').show();
                listReportCrStHandoverService.exportDoc(vm.item.currentStateHandover.currentStateHandoverId).then(function(data) {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            }
        }
        vm.pheDuyet = pheDuyet;
        vm.appro = {
            "statusCa": 2,
            "employeeId": 3496,
            "comments": "",
            "currentStateHandoverId": 343
        }
        vm.disableForm = false;

        function pheDuyet() {
            if (!vm.showDetail) {
                if (vm.reportCrStHandoverGrid.select().length > 0) {
                    var tr = vm.reportCrStHandoverGrid.select().closest("tr");
                    var dataItem = vm.reportCrStHandoverGrid.dataItem(tr);
                    vm.item.currentStateHandover = $.extend({}, dataItem);
                    if (vm.item.currentStateHandover.statusCa == 2) {
                        toastr.warning("Bản ghi này đã duyệt");
                        return;
                    }
                    if (vm.item.currentStateHandover.statusCa == 1) {
                        initData(vm.item);
                        vm.disableForm = true;
                        vm.isCheckAddRow = false;
                        vm.coutRowAdded = 0;
                        vm.demoData = [];
                        vm.showDetail = true;
                        vm.showPheduyet = true;
                        vm.disabledButtomSave = false;
                        if (vm.item.currentStateHandover.ainChargeMonitorId != Constant.user.srvUser.catEmployeeId && vm.item2.binChargeConstructId != Constant.user.srvUser.catEmployeeId) {
                            toastr.warning(gettextCatalog.getString("Bạn không được quyền phê duyệt !"));
                        }
                    } else {
                        toastr.warning("Phải trình duyệt trước phê duyệt");
                    }
                } else {
                    toastr.warning("Chọn biên bản để duyệt trước");
                }
            } else {
                if (vm.item.currentStateHandover.currentStateHandoverId == undefined) {
                    toastr.warning("Bản ghi chưa được tạo");
                    return;
                }
                listReportCrStHandoverService.getOneItemById(vm.item.currentStateHandover.currentStateHandoverId).then(function(data) {
                    vm.item.currentStateHandover = $.extend({}, data);
                    if (vm.item.currentStateHandover.statusCa == 2) {
                        toastr.warning("Bản ghi này đã duyệt");
                        return;
                    }
                    if (vm.item.currentStateHandover.statusCa == 1) {
                        initData(vm.item);
                        vm.disableForm = true;
                        vm.isCheckAddRow = false;
                        vm.coutRowAdded = 0;
                        vm.demoData = [];
                        vm.showDetail = true;
                        vm.showPheduyet = true;
                        vm.disabledButtomSave = false;
                    } else {
                        toastr.warning("Phải trình duyệt trước phê duyệt");
                    }
                }, function(error) {

                });
            }

        }
    }
})();