(function() {
    'use strict';

    var controllerId = 'listCWorkCompleteController';

    angular.module('MetronicApp').controller(controllerId,
        findConstruction30Func);

    /* @ngInject */
    function findConstruction30Func($scope, RestEndpoint,
        $rootScope, $timeout, PopupConst, Constant,
        kendoConfig, gettextCatalog,
        $kWindow,WindowService, CommonService, $q, listCWorkCompleteService, ProposalEvaluation, bgvttbacService, theSignCA) {
        var vm = this;
        vm.hideAButton = hideAButton;
        vm.onChange = onChange;
        vm.changeSite = changeSite;
        vm.doSearch = doSearch;
        vm.chkSelectAll = chkSelectAll;
        vm.showGrid = showGrid;
        vm.remove = onRemove;
        vm.signCA = signCA;
        vm.save = saveAll;
        vm.validatorOptions = kendoConfig.get('validatorOptions');
        vm.onApproval = onApproval;
        vm.item = {};
        vm.exportFile = exportFile;
        vm.showDetail = false;
        vm.disableAll = false;
        vm.add = add;
        vm.reportObj = {
            "constructId": "",
            "constrWorkCompConfirmId": "",
            "code": "",
            "aDirectorId": "",
            "aInChargeMonitorId": "",
            "bDirectorId": "",
            "bInChargeConstructId": "",
            "confirmFromDate": "",
            "confirmToDate": "",
            "confirmPlace": "",
            "conclusion": "",
            "createdDate": "",
            "ohterComments": "",
            "createdUserId": "",
            "approvalDate": "",
            "statusCa": "",
            "isActive": "",
            "listConstrWorkCompConfDTO": "",
            "signPlace": "",
            "signDate": ""
        }

        vm.conclusion = [{
            "id": "0",
            "content": "Không đồng ý"
        }, {
            "id": "1",
            "content": "Đồng ý"
        }];
        vm.datePickerConfig = {
            timeFormat: "HH:mm:ss",
            format: "MM/dd/yyyy HH:mm:ss",
            parseFormats: ["MM/dd/yyyy hh:mmtt", "MM/dd/yyyy HH:mm", "MM/dd/yyyy", "HH:mm"]
        }

        if (vm.item.constructId) {
            listCWorkCompleteService.fetchAllItemByConstructId(vm.item.constructId).then(function(data) {
                fillDataTable(data.data).then(function(d) {
                    try {
                        var grit = vm.cWorkCompleteTable;
                        if (grit) {
                            grit.dataSource.data(data.data);
                            grit.refresh();
                        }
                    } catch (e) {}
                });
            }, function(error) {});

        }

        /**	code lấy dữ liệu dodt */
        function getDataFromService() {
            var deferred = $q.defer();
            var item = ProposalEvaluation.getItem();
            deferred.resolve(item);
            return deferred.promise;
        }

        $scope.$on("ProposalEvaluation.detail", function(event, item) {
            if (item === undefined) {

            } else {
                vm.item = item;
                // initData(item);
                goibonong();
                reloadTable();
                // fillDataToForm(item);
            }
        });

        // ////////////////////////Combobox thanh phan tham gia//////////////////////////////////
        vm.role = [];
        var totalListEmployee = [],
            showTtdv = [],
            showPtgs = [],
            showGd = [],
            showPttc = [];
        vm.showTtdv = [];
        vm.showPtgs = [];
        vm.showGd = [];
        vm.showPttc = [];

        function goibonong() {
            listCWorkCompleteService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
                    totalListEmployee = data.plain();
                    listCWorkCompleteService.getRoleId().then(function(data) {
//                            vm.role = data;
                            for (var i = 0; i < totalListEmployee.length; i++) {
                                //vm.role = data;
                                if (totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]) {//2
                                    showTtdv.push(totalListEmployee[i]);
                                }
                                if (totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_PTGST"]) {//4
                                    showPtgs.push(totalListEmployee[i]);
                                }
                                if (totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]) {//3
                                    showGd.push(totalListEmployee[i]);
                                }
                                if (totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_PTTC"]) {//1
                                    showPttc.push(totalListEmployee[i]);
                                }
                            }
                            vm.showTtdv = showTtdv;
                            vm.showPtgs = showPtgs;
                            vm.showGd = showGd;
                            vm.showPttc = showPttc;

                            if (showTtdv.length > 0) {
                                vm.reportObj.adirectorId = showTtdv[0].userId;
                            }
                            if (showPtgs.length > 0) {
                                vm.reportObj.ainChargeMonitorId = showPtgs[0].userId;
                            }
                            if (showGd.length > 0) {
                                vm.reportObj.bdirectorId = showGd[0].userId;
                            }
                            if (showPttc.length > 0) {
                                vm.reportObj.binChargeConstructId = showPttc[0].userId;
                            }

                        })
                        .catch(function() {
                            console.error('getRoleId error');
                        });
                })
                .catch(function() {
                    console.error('Gists error');
                });
        };
        // ////////////////////////END//////////////////////////////////
        function onRemove() {
            var selectedRow = [];
            var listID = [];
            var noDel = 0;
            var noDel1 = 0;
            var isShowToart = false;
            var grid = vm.cWorkCompleteTable;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);

                if (checkbox.is(':checked')) {
                    // Push id into selectedRow
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    selectedRow.push(dataItem);

                    /* if (dataItem.statusCa === 2 || dataItem.statusCa === 1) {
                         if (!isShowToart && selectedRow[i].createdUserId != Constant.user.srvUser.userId) {
                             toastr.warning("Không được xóa văn bản đã ký");
                             isShowToart = true;
                         }
                     } else {
                         selectedRow.push(dataItem.constrWorkCompConfirmId);
                     }*/
                }
            });



            for (var i = 0; i < selectedRow.length; i++) {
                if (selectedRow[i].statusCa === 0 || selectedRow[i].statusCa === 3) {
                    if (selectedRow[i].createdUserId === Constant.user.srvUser.userId) {
                        listID.push(selectedRow[i].constrWorkCompConfirmId);
                        noDel++;
                        noDel1++;
                    } else if (noDel == 0) {
                        toastr.warning("Bạn không phải người tạo, bạn không có quyền xóa bản ghi đang chọn!");
                        noDel++;
                        noDel1++;
                    }
                } else {
                    if (noDel1 == 0) {
                        toastr.warning("Không thể xóa bản ghi đang trình duyệt hoặc duyệt");
                        noDel1++;
                    }
                }
            }

            if (listID.length > 0) {
                if (selectedRow.length > 0 && confirm("Xác nhận xóa ? ")) {
                    listCWorkCompleteService.deleteListItem(listID).then(function() {
                        toastr.success("Đã xóa thành công");
                        reloadTable();
                    }, function(errResponse) {
                        if (errResponse.status == 302) {
                            toastr.error("Bản ghi đang được sử dụng");
                        } else {
                            toastr.error("Có lỗi xảy ra trong quá trình xóa!");
                        }
                    });
                }

                /*listCWorkCompleteService.deleteListItem(selectedRow).then(function() {
                    reloadTable();
                    toastr.success("Xóa thành công");
                }, function() {
                    toastr.error("Xóa thất bại");
                });*/
            }
            if (!selectedRow.length > 0) {
                toastr.warning("Chọn bản ghi trước khi xóa");
                return;
            }
        }
        
        function reportObjContructer(obj) {
            switch (Number(obj.statusCa)) {
                case 0:
                    { obj.statusCaText = 'Soạn thảo'; break; }
                case 1:
                    { obj.statusCaText = 'Trình ký'; break; }
                case 2:
                    { obj.statusCaText = 'Đã duyệt'; break; }
                case 3:
                    { obj.statusCaText = 'Từ chối duyệt'; }
                default:
                    {
                        obj.statusCaText = 'Soạn thảo';
                    }
            }
            obj.constrtCode = vm.item.constrtCode;
            obj.contractCode = vm.item.contractCode;
            obj.contractName = vm.item.contractName;
            return obj;
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
                    noRecords: gettextCatalog.getString("Chưa có biên bản")
                },
                columns: [{
                        title: gettextCatalog.getString("STT"),
                        field: "stt",
                        headerAttributes: { style: "text-align:center;padding-bottom: 3px" },
                        attributes: { style: "text-align:center;" },
                        template: dataItem => $("#cWorkCompleteTable").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        width: 70,
                    },
                    {
                        title: "<input type='checkbox' id='checkallklhtct' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                        template: "<input  type='checkbox' ng-click='vm.handleCheck();' name='gridcheckbox'/>",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:center;" },
                        width: 30
                    },
                    {
                        title: gettextCatalog.getString("Mã biên bản"),
                        field: "code",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                        width: 180
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
                        width: 100
                    }
                ]
            });
            deferred.resolve('done');
            return deferred.promise;
        }

        vm.handleCheck = function(item) {
            if (document.getElementById("checkallklhtct").checked == true) {
                document.getElementById("checkallklhtct").checked = false;
            }
        }

        function reloadTable() {

            if (vm.item.constructId) {
                listCWorkCompleteService.fetchAllItemByConstructId(vm.item.constructId).then(function(data) {
                    fillDataTable(data.data).then(function(d) {
                        try {
                            var grit = vm.cWorkCompleteTable;
                            if (grit) {
                                grit.dataSource.data(data.data);
                                grit.refresh();
                            }
                        } catch (e) {}
                    });
                }, function(error) {});
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

        function onChange() {
            // do something when click row of table
            var tr = vm.cWorkCompleteTable.select().closest("tr");
            var dataItem = vm.cWorkCompleteTable.dataItem(tr);
            vm.item.signCA = $.extend({}, dataItem);
        }

        function downloadFile() {
            var list = [];
            var grid = vm.cWorkCompleteTable;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);

                if (checkbox.is(':checked')) {
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    list.push(dataItem.constrWorkCompConfirmId);

                }
            });
            if (list.length > 0) {
                $('#loading').show();
                listCWorkCompleteService.downloadFileZip(list).then(function() {
                    window.location =Constant.BASE_SERVICE_URL+ "fileservice/downloadFileATTT?" + data.fileName;
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            } else {
                if (vm.cWorkCompleteTable.select().length > 0) {
                    var tr = vm.cWorkCompleteTable.select().closest("tr");
                    var dataItem = vm.cWorkCompleteTable.dataItem(tr);
                    $('#loading').show();
                    var dataItemOne = angular.copy(dataItem);
                    listCWorkCompleteService.exportOne(dataItemOne.constrWorkCompConfirmId).then(function() {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });
                } else {
                    toastr.warning("Chọn bản ghi trước");
                }
            }
        }

        vm.exportListDOC = exportListDOC;

        function exportListDOC() {
            var list = [];
            var grid = vm.cWorkCompleteTable;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);

                if (checkbox.is(':checked')) {
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    list.push(dataItem.constrWorkCompConfirmId);

                }
            });
            if (list.length > 0) {
                $('#loading').show();
                listCWorkCompleteService.downloadFileDOCZip(list).then(function() {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            } else {
                if (vm.cWorkCompleteTable.select().length > 0) {
                    var tr = vm.cWorkCompleteTable.select().closest("tr");
                    var dataItem = vm.cWorkCompleteTable.dataItem(tr);
                    $('#loading').show();
                    var dataItemOne = angular.copy(dataItem);
                    listCWorkCompleteService.exportDoc(dataItemOne.constrWorkCompConfirmId).then(function() {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });
                } else {
                    toastr.warning("Chọn bản ghi trước");
                }
            }
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
            var grid = vm.cWorkCompleteTable;
            chkSelectAllBase(vm.chkAll, grid);
        }

        /**
         * hàm mở cửa sổ mới
         * @param  {string} key    key của cửa sổ mới 
         * @return {undefined}     hàm không trả về gì 
         */
        function changeSite(key) {
            listCWorkCompleteService.goTo(key);
        }

        function showGrid() {
        	
            if (vm.cWorkCompleteTable.select().length > 0) {
            	$(".k-invalid-msg").hide();
                var tr = vm.cWorkCompleteTable.select().closest("tr");
                var dataItem = vm.cWorkCompleteTable.dataItem(tr);
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
                vm.item.constrWorkCompConf = $.extend({}, dataItem);

                vm.showDetail = !vm.showDetail;
                $("#xlf ").val('');
                vm.showImportValue = false;
                initData(vm.item);
                if (!vm.showDetail) { reloadTable(); }
            } else {
                if (vm.showDetail) { vm.showDetail = false; } else { toastr.warning("Chọn biên bản xác nhận trước"); }
            }
            //vm.validator.validate();
        }

        function add() {
        	$(".k-invalid-msg").hide();
            listCWorkCompleteService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
                    totalListEmployee = data.plain();
                    listCWorkCompleteService.getRoleId().then(function(data) {
                            //vm.role = data;
                            for (var i = 0; i < totalListEmployee.length; i++) {
                                //vm.role = data;
                                if (totalListEmployee[i].roleid == "employee_roleID_CDT_DDPN") {//2
                                    showTtdv.push(totalListEmployee[i]);
                                }
                                if (totalListEmployee[i].roleid == "employee_roleID_CDT_PTGST") {//4
                                    showPtgs.push(totalListEmployee[i]);
                                }
                                if (totalListEmployee[i].roleid == "employee_roleID_DT_GDNT") {//3
                                    showGd.push(totalListEmployee[i]);
                                }
                                if (totalListEmployee[i].roleid == "employee_roleID_DT_PTTC") {//1
                                    showPttc.push(totalListEmployee[i]);
                                }
                            }
                            vm.showTtdv = showTtdv;
                            vm.showPtgs = showPtgs;
                            vm.showGd = showGd;
                            vm.showPttc = showPttc;

                            if (showTtdv.length > 0) {
                                vm.reportObj.adirectorId = showTtdv[0].userId;
                            }
                            if (showPtgs.length > 0) {
                                vm.reportObj.ainChargeMonitorId = showPtgs[0].userId;
                            }
                            if (showGd.length > 0) {
                                vm.reportObj.bdirectorId = showGd[0].userId;
                            }
                            if (showPttc.length > 0) {
                                vm.reportObj.binChargeConstructId = showPttc[0].userId;
                            }

                        })
                        .catch(function() {
                            console.error('getRoleId error');
                        });
                })
                .catch(function() {
                    console.error('Gists error');
                });
            for (var i = 0; i < vm.showTtdv.length; i++) {
                if (vm.showTtdv[i].isCurrent === 1) {
                    vm.reportObj.adirectorId = vm.showTtdv[i].userId;
                    break;
                } else {
                    vm.reportObj.adirectorId = vm.showTtdv[0].userId;
                    break;
                }
            }
            for (var i = 0; i < vm.showPtgs.length; i++) {
                if (vm.showPtgs[i].isCurrent === 1) {
                    vm.reportObj.ainChargeMonitorId = vm.showPtgs[i].userId;
                    break;
                } else {
                    vm.reportObj.ainChargeMonitorId = vm.showPtgs[0].userId;
                    break;
                }
            }
            for (var i = 0; i < vm.showGd.length; i++) {
                if (vm.showGd[i].isCurrent === 1) {
                    vm.reportObj.bdirectorId = vm.showGd[i].userId;
                    break;
                } else {
                    vm.reportObj.bdirectorId = vm.showGd[0].userId;
                    break;
                }
            }
            for (var i = 0; i < vm.showPttc.length; i++) {
                if (vm.showPttc[i].isCurrent === 1) {
                    vm.reportObj.binChargeConstructId = vm.showPttc[i].userId;
                    break;
                } else {
                    vm.reportObj.binChargeConstructId = vm.showPttc[0].userId;
                    break;
                }
            }
            vm.item.constrWorkCompConf = {};
            vm.item.constrWorkCompConf.conclusion = 1;
            initData(vm.item);
            vm.showImportValue = false;
            $("#xlf ").val('');
            // reloadTableDetail(vm.item.constructId);
            vm.showDetail = true;
            vm.disabledButtomSave = false
        }

        function doSearch() {
            if (vm.validator.validate()) {}
        }


        getDataFromService().then(function(item) {
            vm.item = item;
            // initData(item);
            reloadTable();
            goibonong();
        });

        function initData(item) {


            if (item.constrWorkCompConf.constrWorkCompConfirmId) {
                vm.reportObj = angular.copy(item.constrWorkCompConf);
                // vm.reportObj.confirmFromDate = chanceDateToString(item.constrWorkCompConf.confirmFromDate, "M_TO_DATEMONTH");
                vm.reportObj.confirmFromDate = item.constrWorkCompConf.confirmFromDate;
                // vm.reportObj.confirmToDate = chanceDateToString(item.constrWorkCompConf.confirmToDate, "M_TO_DATEMONTH");
                vm.reportObj.confirmToDate = item.constrWorkCompConf.confirmToDate;
                // vm.reportObj.signDate = chanceDateToString(item.constrWorkCompConf.signDate, "M_TO_DATE");
                vm.reportObj.signDate = item.constrWorkCompConf.signDate;


            } else {
                vm.reportObj = {};
                vm.reportObj.conclusion = 1;
                vm.reportObj.adirectorId = vm.firstIdOfTtdv;
                vm.reportObj.ainChargeMonitorId = vm.firstIdOfPtgs;
                vm.reportObj.bdirectorId = vm.firstIdOfGd;
                vm.reportObj.binChargeConstructId = vm.firstIdOfPttc;
            }

            var dataFullData = [];
            if (typeof item.constrWorkCompConf.constrWorkCompConfirmId != undefined &&
                item.constrWorkCompConf.constrWorkCompConfirmId != null) {
                var arrayId = [item.constructId, item.constrWorkCompConf.constrWorkCompConfirmId];
                listCWorkCompleteService.getListChilExistById(arrayId).then(function(success) {
                    dataFullData = success.data; // dữ liệu đã có ghi chú và số lượng thực hiện
                    reloadBufferTable(dataFullData);
                }, function(error) {
                    console.log(error)
                });
            } else {
                var arrayId = [item.constructId, 0];
                listCWorkCompleteService.getListChilById(item.constructId).then(function(data2) {
                    var dataEmptyData = data2.data; // dữ liệu chứa toàn bộ các child
                    for (var i = 0; i < dataEmptyData.length; i++) {
                        dataEmptyData[i].executeQuantity = dataEmptyData[i].workAmount;
                    }

                    reloadBufferTable(dataEmptyData);
                })
            }

            // listCWorkCompleteService.getListChilExistById(arrayId).then(function (success) {
            // 	dataFullData = success.data; // dữ liệu đã có ghi chú và số lượng thực hiện
            // 	var mapId = new Map();
            // 	for (var i = 0; i < dataFullData.length; i++) {
            // 		mapId.set(dataFullData[i].estimatesWorkItemId, "");
            // 	}
            // 	listCWorkCompleteService.getListChilById(item.constructId).then(function (data2) {
            // 		var dataEmptyData = data2.data;// dữ liệu chứa toàn bộ các child
            // 		
            // 		for (var i = 0; i < dataEmptyData.length; i++) {
            // 			if (!mapId.has(dataEmptyData[i].estimatesWorkItemId)) {
            // 				dataEmptyData[i].executeQuantity = dataEmptyData[i].workAmount;
            // 				dataFullData.push(dataEmptyData[i]);
            // 			}
            // 		}
            // 		if (typeof item.constrWorkCompConf.constrWorkCompConfirmId != undefined && item.constrWorkCompConf.constrWorkCompConfirmId != null) {
            // 			reloadBufferTable(dataFullData);
            // 		} else {
            // 			reloadBufferTable(dataEmptyData);
            // 		}

            // 		// if (typeof item.constrWorkCompConf.constrWorkCompConfirmId != undefined && item.constrWorkCompConf.constrWorkCompConfirmId !== null) {
            // 		// 	reloadBufferTable(dataFullData);
            // 		// } else {
            // 		// 	reloadBufferTable([]);
            // 		// }
            // 	})
            // }, function (error) {
            // 	console.log(error)
            // });


        }
        //------------------------------------------------------------


        function fillDataTableDetail(data) {
            var deferred = $q.defer();
            var dataSource = new kendo.data.DataSource({
                pageSize: 20,
                data: data,
                autoSync: false,
                schema: {
                    model: {
                        fields: {
                            executeQuantity: { validation: { min: 0, required: { message: "Khối lượng thi công thực tế phải lớn hơn 0" } } },
                            workAmount:{ editable: false },
                            workItemName: { editable: false },
                            unit: { editable: false },
                        }
                    }
                }
            });

            vm.optionsDetail = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: dataSource,
                noRecords: true,
                onchange: onChange,
                excel: {
                    fileName: "Construction List.xlsx",
                    filterable: true,
                    allPages: true,
                },
                messages: {
                    noRecords: gettextCatalog
                        .getString("Chưa có nội dung")
                },
            	edit: function(e) {
			         e.container.find("input[name=workItemName]").attr("maxlength", 1000);
			         e.container.find("input[name=unit]").attr("maxlength", 10);
			         e.container.find("input[name=workAmount]").attr("maxlength", 34);
			         e.container.find("input[name=executeQuantity]").attr("maxlength", 34);
			         e.container.find("input[name=comments]").attr("maxlength", 1000);			         
            	},
                columns: [{
                        field: "a1",
                        title: "STT",
                        template: dataItem => $("#cWorkComplete").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        attributes: { style: "text-align:center;" },
                        headerAttributes: { style: "text-align:center;padding-bottom: 15px;" },
                        width: 80
                    },
                    // {
                    // 	title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                    // 	template: "<input type='checkbox' name='gridcheckbox' />",
                    // 	width: 35
                    // },
                    {
                        field: "constrWorkCompListId",
                        title: "id",
                        width: 0,
                        hidden: true
                    },
                    {
                        title: gettextCatalog
                            .getString("Nội dung"),
                        field: "workItemName",
                        headerAttributes: { style: "text-align:center;padding-bottom: 15px;" },
                        attributes: { style: "text-align:left;" },
                        width: 150
                    },
                    {
                        title: gettextCatalog
                            .getString("Đơn vị tính"),
                        field: "unit",
                        headerAttributes: { style: "text-align:center;padding-bottom: 15px;" },
                        attributes: { style: "text-align:left;" },
                        width: 120
                    },
                    {
                        title: gettextCatalog
                        .getString("Khối lượng theo <br/> hợp đồng"),
                    field: "workAmount",
                    headerAttributes: { style: "text-align:center;" },
                    attributes: { style: "text-align:right;" },
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.workAmount) && dataItem.workAmount >= 0) {
                            dataItem.workAmount = parseFloat(Number(dataItem.workAmount).toFixed(3));
                            return parseFloat(Number(dataItem.workAmount).toFixed(3));
                        } else {
                            dataItem.workAmount = 0;
                            return 0;
                        }

                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 150
                },
                    {
                        title: gettextCatalog
                            .getString("Khối lượng thi công <br/> thực tế"),
                        field: "executeQuantity",
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:right;" },
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.executeQuantity) && dataItem.executeQuantity >= 0) {
                                dataItem.executeQuantity = parseFloat(Number(dataItem.executeQuantity).toFixed(3));
                                return parseFloat(Number(dataItem.executeQuantity).toFixed(3));
                            } else {
                                dataItem.executeQuantity = 0;
                                return 0;
                            }

                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 150
                    },
                    {
                        title: gettextCatalog
                            .getString("Ghi chú"),
                        field: "comments",
                        headerAttributes: { style: "text-align:center;padding-bottom: 15px;" },
                        attributes: { style: "text-align:left;" },
                        width: 100
                    }
                ]
            });
            deferred.resolve('done');
            return deferred.promise;
        }


        function checkEmptyObject(obj) {
            if (
                obj.executeQuantity === ''
            ) {
                return true;
            } else {
                return false;
            }
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
            var grid = vm.cWorkComplete;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        function reloadTableDetail(id) {
            if (id) {
                if ($.isArray(id)) {
                    listCWorkCompleteService.getListChilExistById(id).then(function(d) {
                        reloadBufferTable(d.data);
                    }, function(error) {

                    });
                } else {

                    listCWorkCompleteService.getListChilById(id).then(function(d) {

                        reloadBufferTable(d.data);
                    }, function(error) {

                    });
                }
            } else {
                reloadBufferTable([]);
            }

        }

        function saveAll() {

            if (vm.validator.validate()) {
                var dataSource = $("#cWorkComplete").data().kendoGrid.dataSource.data();
                var ObjectDTO = [];
                var bufferDataSource = angular.copy(dataSource);

                if (bufferDataSource.length === 0) {
                    toastr.warning("Bảng nội dung không được trống");
                    return;
                }
                for (var i = 0; i < bufferDataSource.length; i++) {
                    if (!checkEmptyObject(bufferDataSource[i])) {
                        delete bufferDataSource[i].fwmodelId;
                        bufferDataSource[i].constrWorkCompConfirmId = vm.reportObj.constrWorkCompConfirmId;
                        ObjectDTO.push(bufferDataSource[i]);
                    } else {
                        toastr.warning("Khối lượng thi công thực tế không được trống");
                        return;
                    }

                }

                vm.reportObj.listConstrWorkCompConfDTO = ObjectDTO;
                vm.reportObj.isActive = "1";
                var DataExport = angular.copy(vm.reportObj);
                // DataExport.confirmFromDate = chanceDateToString(vm.reportObj.confirmFromDate, "DATEMONTH_TO_M");
                // DataExport.confirmToDate = chanceDateToString(vm.reportObj.confirmToDate, "DATEMONTH_TO_M");
                var confirmFromDateMili = chanceDateToString(vm.reportObj.confirmFromDate, "DATEMONTH_TO_M");
                var confirmToDateMili = chanceDateToString(vm.reportObj.confirmToDate, "DATEMONTH_TO_M");
                if (confirmFromDateMili >= confirmToDateMili) {
                    toastr.warning("Ngày kết thúc phải bằng hoặc sau ngày ngày bắt đầu");
                    return;
                }
                // DataExport.signDate = chanceDateToString(vm.reportObj.signDate, "DATE_TO_M");

                if (DataExport.constrWorkCompConfirmId > 0) {
                    if (vm.reportObj.createdUserId == Constant.user.srvUser.userId) {
                        listCWorkCompleteService.updateAllReport(DataExport).then(function(data) {
                            toastr.success("Cập nhật thành công");
                            vm.showDetail = false;
                            reloadTableDetail([vm.item.constructId, vm.item.constrWorkCompConf.constrWorkCompConfirmId]);
                            vm.item.signCA = angular.copy(DataExport);
                            reloadTable();
                        }, function(error) {
                            toastr.error("Cập nhật thất bại");
                        });
                    } else {
                        toastr.error("Bạn không phải là người tạo, không có quyền sửa");
                    }
                } else {
                    DataExport.statusCa = 0;
                    DataExport.constructId = vm.item.constructId;
                    var idCreater = Constant.getUser().srvUser.userId;
                    DataExport.createdUserId = idCreater;
                    listCWorkCompleteService.createNewReport(DataExport).then(function(data) {

                        toastr.success("Thêm mới thành công");
                        vm.showDetail = !vm.showDetail;
                        reloadTable();

                    }, function(error) {
                        toastr.error("Thêm mới thất bại");
                    });
                }


            } else {
                toastr.warning("Điền đầy đủ các trường");
            }

        }

        /** hàm export file */
        function exportFile() {
            if (vm.showDetail) {
                // export one
                $('#loading').show();
                var dataItemOne = angular.copy(vm.item.constrWorkCompConf);
                listCWorkCompleteService.exportOne(dataItemOne.constrWorkCompConfirmId).then(function() {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            } else {
                // export many
                downloadFile();
            }
        }

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

        function signCA() {
            var dataItemOne = angular.copy(vm.item.signCA);

            if (dataItemOne.statusCa === 0) {
                $('#loading').show();
                listCWorkCompleteService.signCA(dataItemOne.constrWorkCompConfirmId).then(function(data) {

                    var signCAData = {
                        code: '',
                        constructId: '',
                        constrCompReMapId: '',
                        stringEmployee: '',
                        //1-4-3-2
                        roleId: [Constant.ROLE_ID["employee_roleID_DT_PTTC"], Constant.ROLE_ID["employee_roleID_CDT_PTGST"], 
                                 Constant.ROLE_ID["employee_roleID_DT_GDNT"], Constant.ROLE_ID["employee_roleID_CDT_DDPN"]],
                        roleName: ["Phụ trách thi công", "Phụ trách giám sát", "Giám đốc", "Thủ trưởng đơn vị"],
                        isSign: '',
                        path: '',
                        nameFile: ''
                    };
                    signCAData.code = dataItemOne.code;
                    signCAData.constructId = dataItemOne.constructId;
                    signCAData.constrCompReMapId = data[0];
                    signCAData.stringEmployee = dataItemOne.binChargeConstructId + "," + dataItemOne.ainChargeMonitorId + "," +
                        dataItemOne.bdirectorId + "," + dataItemOne.adirectorId;
                    signCAData.isSign = "theSignCA";

                    signCAData.path = data[2]; // nên là đường dẫn tương đối . nhưng ở đây đã điền là tên file
                    signCAData.nameFile = 'BM-TCNT-24.pdf';
                    theSignCA.setItem(signCAData);
                    vm.goTo('THE_SIGN_CA');
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            } else {
                toastr.warning("Biên bản đã trình ký");
            }


        }
        $scope.$on("ChangeStatus", function(event, result) {
            if (result) {
                reloadTable();
            }
        });

        vm.exportDoc = exportDoc;

        function exportDoc() {
            if (vm.showDetail) {
                // export one

                var dataItemOne = angular.copy(vm.item.constrWorkCompConf);
                $('#loading').show();
                listCWorkCompleteService.exportDoc(dataItemOne.constrWorkCompConfirmId).then(function() {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            }
        }

        vm.importExcel = importExcel;

        function importExcel() {
            var dataSource = $("#cWorkComplete").data().kendoGrid.dataSource.data();
            var ds = new kendo.data.DataSource({
                type: "odata",
                dataSource: dataSource,
                schema: {
                    model: {
                        fields: {
                            constrWorkCompListId: { type: "number", editable: false },
                            workItemName: { type: "string" },
                            unit: { type: "string" },
                            workAmount: { type: "number" },
                            executeQuantity: { type: "number" },
                            comments: { type: "string" }
                        }
                    }
                }
            });

            var rows = [{
                cells: [
                    // First cell
                    { value: "STT", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },

                    { value: "ID", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },
                    // Second cell
                    { value: "WORK_ITEM_ID", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },

                    { value: "NỘI DUNG", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },
                    // Third cell
                    { value: "ĐƠN VỊ TÍNH", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },
                    // Fourth cell
                    { value: "KHỐI LƯỢNG THEO HỢP ĐỒNG", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },
                    // Fifth cell
                    { value: "KHÔI LƯỢNG THI CÔNG THỰC TẾ", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },

                    { value: "GHI CHÚ", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 }, borderLeft: { color: "#000000", size: 1 } },
                ]
            }];

            //using fetch, so we can process the data when the request is successfully completed
            ds.fetch(function() {
                var data = dataSource;
                for (var i = 0; i < data.length; i++) {
                    //push single row for every record

                    rows.push({
                        cells: [
                            { value: (i + 1) },
                            { value: data[i].constrWorkCompListId },
                            { value: data[i].estimatesWorkItemId },
                            { value: data[i].workItemName },
                            { value: data[i].unit },
                            { value: data[i].workAmount },
                            { value: data[i].executeQuantity },
                            { value: data[i].comments }
                        ]
                    })
                }
                var workbook = new kendo.ooxml.Workbook({
                    sheets: [{
                        columns: [
                            // Column settings (width)
                            { width: 50 },
                            { width: 1 },
                            { width: 1 },
                            { width: 150 },
                            { width: 95 },
                            { width: 225 },
                            { width: 250 },
                            { width: 250 }
                        ],
                        // Title of the sheet
                        title: "Orders",
                        // Rows of the sheet
                        rows: rows
                    }]
                });
                //save the file as Excel file with extension xlsx
                kendo.saveAs({ dataURI: workbook.toDataURL(), fileName: "Export.xlsx" });
            });

        }

        vm.Import = function() {
            var dataSource = $("#cWorkComplete").data().kendoGrid.dataSource.data();
            var fileInput = $('#xlf');
            var filePart = fileInput.val();
            var extraNameFile = filePart.split("/(\\|\/)/g").pop().split(".").pop();
            if (extraNameFile !== "xlsx" && extraNameFile !== "xls") {
                toastr.warning("Định dạng file import không hợp lệ");
                return;
            }
            var listIdWrongExcel = [];
            var input = fileInput.get(0);
            var textFile = input.files[0];
            var reader = new FileReader();
            if (input.files.length) {
                var textFile = input.files[0];
                reader.readAsBinaryString(textFile);
                reader.onload = function(e) {
                    var data = e.target.result;
                    /* Call XLSX */
                    var workbook = XLSX.read(data, { type: "binary" });
                    /* DO SOMETHING WITH workbook HERE */
                    var first_sheet_name = workbook.SheetNames[0];
                    /* Get worksheet */
                    var worksheet = workbook.Sheets[first_sheet_name];
                    var array = XLSX.utils.sheet_to_json(worksheet, { raw: true });

                    var worklistexcell = [];
                    var tranferObj = {}
                    for (var i = 0; i < array.length; i++) {
                        tranferObj.constrWorkCompListId = array[i]["ID"];
                        tranferObj.workItemName = array[i]["NỘI DUNG"];
                        tranferObj.unit = array[i]["ĐƠN VỊ TÍNH"];
                        tranferObj.estimatesWorkItemId = array[i]["WORK_ITEM_ID"];
                        tranferObj.workAmount = array[i]["KHỐI LƯỢNG THEO HỢP ĐỒNG"];
                        tranferObj.executeQuantity = array[i]["KHÔI LƯỢNG THI CÔNG THỰC TẾ"];
                        tranferObj.comments = array[i]["GHI CHÚ"];
                        if (!tranferObj.estimatesWorkItemId) {
                            toastr.error("Biểu mẫu không hợp lệ");
                            return;
                        }
                        if (vm.reportObj.constrWorkCompConfirmId) {
                            // nếu là màn hình update
                            if (tranferObj.constrWorkCompListId !== undefined) {
                                var dataCopy = angular.copy(tranferObj);
                                // if (dataCopy.workItemName === undefined || dataCopy.unit === undefined || dataCopy.workAmount === undefined || dataCopy.estimatesWorkItemId === undefined || dataCopy.executeQuantity === undefined) {
                                //     toastr.error("Dữ liệu không hợp lệ");
                                //     return undefined;
                                // }
                                if (checkDataImport(dataCopy, "update", i + 2)) {
                                    worklistexcell.push(dataCopy);
                                } else { return; }
                            } else {
                                listIdWrongExcel.push(tranferObj.constrWorkCompListId);
                            }
                        } else {
                            // nếu là màn hình thêm mới
                            var dataCopy = angular.copy(tranferObj);
                            // if (dataCopy.workItemName === undefined || dataCopy.unit === undefined || dataCopy.workAmount === undefined || dataCopy.estimatesWorkItemId === undefined || dataCopy.executeQuantity === undefined) {
                            //     toastr.error("Dữ liệu không hợp lệ");
                            //     return undefined;
                            // }
                            if (checkDataImport(dataCopy, "addnew", i + 2)) {
                                worklistexcell.push(dataCopy);
                            } else { return; }
                        }
                    }
                    if (worklistexcell.length !== dataSource.length) {
                        // khi thêm mới mà dữ liệu thêm nhiều hơn dữ liệu trong bảng
                        toastr.error("Dữ liệu không hợp lệ");
                        return;
                    } else {
                        refreshGrid(worklistexcell);
                        toastr.success("Import thành công");
                        return;
                    }
                    // var subTableData = mergeArray(dataSource, worklistexcell);
                    // if (subTableData) {
                    //     refreshGrid(worklistexcell);
                    // } else {
                    //     toastr.error("Dữ liệu không hợp lệ ");
                    // }
                };
            } else {
                toastr.warning('Upload file để tiếp tục')
            };
        }

        function checkDataImport(dataCopy, mode, index) {
            if (mode === "update") {
                if (dataCopy.constrWorkCompListId === undefined) {
                    toastr.error("Ô B" + index + " không được trống");
                    return undefined;
                } else {
                    if (!$.isNumeric(dataCopy.constrWorkCompListId)) {
                        toastr.error("Ô B" + index + " phải là số");
                        return undefined;
                    }
                }
                if (dataCopy.workItemName === undefined) {
                    toastr.error("Ô D" + index + " không được trống");
                    return undefined;
                }
                if (dataCopy.unit === undefined) {
                    toastr.error("Ô E" + index + " không được trống");
                    return undefined;
                }
                if (dataCopy.workAmount === undefined) {
                    toastr.error("Ô F" + index + " không được trống");
                    return undefined;
                } else {
                    if (!$.isNumeric(dataCopy.workAmount)) {
                        toastr.error("Ô F" + index + " phải là số");
                        return undefined;
                    }
                }
                if (dataCopy.estimatesWorkItemId === undefined) {
                    toastr.error("Ô C" + index + " không được trống");
                    return undefined;
                } else {
                    if (!$.isNumeric(dataCopy.estimatesWorkItemId)) {
                        toastr.error("Ô C" + index + " phải là số");
                        return undefined;
                    }
                }
                if (dataCopy.executeQuantity === undefined) {
                    toastr.error("Ô G" + index + " không được trống");
                    return undefined;
                } else {
                    if (!$.isNumeric(dataCopy.executeQuantity)) {
                        toastr.error("Ô G" + index + " phải là số");
                        return undefined;
                    }
                }
                return 1;

            } else {
                if (dataCopy.workItemName === undefined) {
                    toastr.error("Ô D" + index + " không được trống");
                    return undefined;
                }
                if (dataCopy.unit === undefined) {
                    toastr.error("Ô E" + index + " không được trống");
                    return undefined;
                }
                if (dataCopy.workAmount === undefined) {
                    toastr.error("Ô F" + index + " không được trống");
                    return undefined;
                } else {
                    if (!$.isNumeric(dataCopy.workAmount)) {
                        toastr.error("Ô F" + index + " phải là số");
                        return undefined;
                    }
                }
                if (dataCopy.estimatesWorkItemId === undefined) {
                    toastr.error("Ô C" + index + " không được trống");
                    return undefined;
                } else {
                    if (!$.isNumeric(dataCopy.estimatesWorkItemId)) {
                        toastr.error("Ô C" + index + " phải là số");
                        return undefined;
                    }
                }
                if (dataCopy.executeQuantity === undefined) {
                    toastr.error("Ô G" + index + " không được trống");
                    return undefined;
                } else {
                    if (!$.isNumeric(dataCopy.executeQuantity)) {
                        toastr.error("Ô G" + index + " phải là số");
                        return undefined;
                    }
                }
                return 1;
            }
        }


        function mergeArray(rootArray, advantArray) {
            var targetArray = [];
            var advantMap = new Map();
            for (var i = 0; i < advantArray.length; i++) {
                advantMap.set(advantArray[i].constrWorkCompListId, advantArray[i]);
            }
            for (var i = 0; i < rootArray.length; i++) {
                if (advantMap.has(rootArray[i].constrWorkCompListId)) {
                    var obj = advantMap.get(rootArray[i].constrWorkCompListId);
                    if (obj.workItemName === undefined || obj.unit === undefined || obj.workAmount === undefined || obj.estimatesWorkItemId === undefined) {
                        return undefined;
                    }
                    targetArray.push(advantMap.get(rootArray[i].constrWorkCompListId));
                } else {
                    targetArray.push(rootArray[i]);
                }
            }
            return targetArray;
        }

        vm.showImport = showImport;
        vm.showImportValue = false;
        vm.cancelImport =	function (){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
        function showImport() {
//            vm.showImportValue = !vm.showImportValue;
        	WindowService.open({
                options: {
                    modal: true,
                    title: "Import",
                    visible: false,
                    width: '650',
                    height: '200',
                    actions: ["Minimize", "Maximize", "Close"],
                    open: function() {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: "qlhc/listCWorkComplete/listCWorkCompleteImportTemplate.html"

            });
        }

    }
})();