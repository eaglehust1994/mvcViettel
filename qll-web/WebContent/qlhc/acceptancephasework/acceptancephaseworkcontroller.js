(function() {
    'use strict';

    var controllerId = 'acceptancephaseworkController';

    angular.module('MetronicApp').controller(controllerId,
        acceptancephaseworkController);

    /* @ngInject */
    // function acceptancephaseworkController($scope, $rootScope, $timeout,
    // Constant, gettextCatalog, kendoConfig, $q, RestEndpoint, Restangular,
    // $kWindow,acceptanceService, settlementService) {
    function acceptancephaseworkController($scope, $rootScope, $timeout,
        Constant, gettextCatalog, kendoConfig, $kWindow, $q,
        acceptanceService, ProposalEvaluation, theApproval, CommonService) {
        var vm = this;
        vm.validatorOptions = kendoConfig.get('validatorOptions');
        vm.disApproval = false;
        vm.disableCreat = false;
        vm.showCreat = false;
        vm.showDetail = true;
        vm.showApproval = false;
        vm.isCreateNew = true;
        vm.update = '';
        vm.add = add;
        vm.detail = detail;
        vm.save = save;
        //vm.exports = exports;
        //loadDataTable([]);

        vm.multiDelete = multiDelete;
        vm.showGrid = showGrid;
        //vm.ListshowGrid = ListshowGrid;
        vm.theApproval = {
            code: '',
            constructId: '',
            constrCompReMapId: '',
            stringEmployee: '',
            isSign: '',
            path: '',
            nameFile: '',
            roleId: [],
            roleName: []
        }
        vm.AB = {};
        vm.role = [];

        vm.listacceptance = {

            statusCa: '0',
            isActive: '1',
            constructId: '',
            contractName: '',
            code: '',
            contractDateSign: '',
            constructName: '',
            constructAddress: '',
            estimatesItemChildId: '',
            // estimatesItemChildName : '',
            amonitorId: '',
            binChargeConstructId: '',
            acceptFromDate: '',
            acceptToDate: '',
            acceptPlace: '',
            applyBenchmark: '',
            otherDocuments: '',
            constructionQuality: '',
            otherComments: '',
            conclusion: '0',
            completeRequest: '',
            amonitorName: '',
            binChargeConstructName: '',
            signPlace: '',
            signDate: '',
            contractId: ''
        };

        vm.listcategory = {
            contractCode: '',
            contractName: '',
            constructId: '',
            constructName: '',
            constructAddress: '',
            categoryAcceptanceId: ''
        };

        vm.item = {
            partnerName: '',
            contractCode: '',
            investProjectName: '',
            constrtCode: '',
            constrtName: '',
            constrType: '',
            provinceId: '',
            constrtAddress: '',
            constructId: '',

        }
        vm.dateTimePickerConfig1 = {
		        format: "dd/MM/yyyy",
		        parseFormats: ["yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy')#"
		    };
        // lay HD, Cong trinh theo tim kiem cong trinh
        vm.item = ProposalEvaluation.getItem();
        if (vm.item == null) {
            vm.item = CommonService.getItem();
        }

        $scope.$on("ProposalEvaluation.detail", function(event, item) {
            if (item != undefined) {
                vm.item = item;
            } else {
                console.error("không nhận được dữ liệu!");
            }
        });

        if (vm.item != undefined) {
            vm.listacceptance.contractCode = vm.item.contractCode;
            vm.listacceptance.constrtCode = vm.item.constrtCode;
            vm.listacceptance.contractDateSign = vm.item.signed_date;
            vm.listacceptance.constructId = vm.item.constructId;
            vm.listacceptance.constructName = vm.item.constrtName;
            vm.listacceptance.constructAddress = vm.item.constrtAddress;

            vm.listcategory.contractCode = vm.item.contractCode;
            vm.listcategory.contractName = vm.item.contractName;
            vm.listcategory.constrtCode = vm.item.constrtCode;
            vm.listcategory.constructId = vm.item.constructId;
            vm.listcategory.constructName = vm.item.constrtName;
            vm.listcategory.constructAddress = vm.item.constrtAddress;

        }

//        acceptanceService.getRoleId().then(function(data) {
//			//vm.role = data;
//		})
//		.catch(function(data, status) {
//			console.error('getRoleId error', response.status, response.data);
//		});

        // Hang muc nghiem thu
        vm.findestimates = {};
        vm.findestimates.constructId = vm.listacceptance.constructId;
        
        // Giam sat thi cong
        vm.findAmonitorId = {};
        vm.findAmonitorId.constructId = vm.listacceptance.constructId;
        
        // Phu trach ki thuat
        vm.findBinChargeConstructId = {};
        vm.findBinChargeConstructId.constructId = vm.listacceptance.constructId;
        

        initFormData();
        reloadCategoryAcceptance();
        acceptanceService.getRoleId().then(function(data) {
//			vm.role = data;
			vm.findAmonitorId.roleid = Constant.ROLE_ID["employee_roleID_CDT_GSTC"];//10
			vm.findBinChargeConstructId.roleid = Constant.ROLE_ID["employee_roleID_DT_KTTC"];//5
		})
		.catch(function(data, status) {
			console.error('getRoleId error', response.status, response.data);
		});

        function initFormData() {

            acceptanceService.getAllCategoryAcceptance(vm.listcategory.constructId, vm.item.contractId).then(function(d) {
                loadDataTable(d.plain());
                refreshGrid(d.plain(), vm.categoryAcceptanceGrid);
            }, function(errResponse) {
                console.error('Error while fetching object type');
            });
            getABC();

        }

        function getABC() {
            acceptanceService.getAllAMonitorOrBInChargeByConstructId(vm.findAmonitorId).then(function(d) {
                vm.listAmonitorConstruction = d.plain();

                vm.listacceptance.amonitorName = vm.listAmonitorConstruction[0].fullName;
                vm.listacceptance.amonitorId = vm.listAmonitorConstruction[0].id;

            }, function(errResponse) {

            });

            acceptanceService.getAllAMonitorOrBInChargeByConstructId(vm.findBinChargeConstructId).then(function(d) {
                vm.listBinChargeConstruction = d.plain();

                vm.listacceptance.binChargeConstructName = vm.listBinChargeConstruction[0].fullName;
                vm.listacceptance.binChargeConstructId = vm.listBinChargeConstruction[0].id;

            }, function(errResponse) {

            });
        }

        vm.gridCommon = [{
            title: "Mã",
            field: "code",
            width: 120
        }, {
            title: "Tên",
            field: "fullName",
            width: 120
        }];

        vm.getEmptyDataModel = getEmptyDataModel;

        function getEmptyDataModel() {
            return vm.listacceptance = { estimatesItemChildId: '', amonitorId: '', binChargeConstructId: '', acceptFromDate: '', acceptToDate: '', acceptPlace: '', applyBenchmark: '', otherDocuments: '', constructionQuality: '', otherComments: '', conclusion: '', completeRequest: '', statusCa: '0', isActive: '1', };
        }

        vm.chkSelectAll = chkSelectAll;

        function chkSelectAll(item) {
            var grid = vm.categoryAcceptanceGrid;
            chkSelectAllBase(vm.chkAll, grid);
        }
        vm.handleCheck = function(item) {
            if (document.getElementById("checkalllist1").checked == true) {
                document.getElementById("checkalllist1").checked = false;
            }
        }

        function loadDataTable(data) {
            var deferred = $q.defer();
            vm.gridOptions = kendoConfig
                .getGridOptions({
                    autoBind: true,
                    dataSource: data,
                    change: onChange,
                    noRecords: true,
                    editable: false,
                    messages: {
                        noRecords: gettextCatalog
                            .getString("Không có kết quả nào")
                    },
                    columns: [{
                            title: gettextCatalog.getString("<b>STT</b>"),
                            field: "as",
                            template: dataItem => $("#mainGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                            width: 70,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:center;" },
                        },
                        {
                            title: "<input type='checkbox' id='checkalllist1' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                            template: "<input type='checkbox' ng-click='vm.handleCheck();' name='gridcheckbox' />",
                            width: 60,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:center;" },
                        },
                        {
                            title: gettextCatalog.getString("Mã biên bản"),
                            field: "code",
                            width: 180,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                        },
                        {
                            title: gettextCatalog
                                .getString("Mã hạng mục"),
                            field: "itemCode",
                            width: 150,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                        },
                        {
                            title: gettextCatalog
                                .getString("Tên hạng mục"),
                            field: "itemName",
                            width: 150,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                        },
                        {
                            title: gettextCatalog
                                .getString("Mã công trình"),
                            field: "constrtCode",
                            width: 150,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                        },
                        {
                            title: gettextCatalog
                                .getString("Mã hợp đồng"),
                            field: "contractCode",
                            width: 150,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                        },
                        {
                            title: gettextCatalog
                                .getString("Tên hợp đồng"),
                            field: "contractName",
                            width: 180,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                        },
                        {
                            title: gettextCatalog.getString("<b>Trạng thái</b>"),
                            attributes: { style: "text-align:left;", class:"statusColumn"},
                            template: "# if(statusCa == 0){ #" + "#= 'Soạn thảo' #" + "# } " +
                                "else if (statusCa==1) { # " + "#= 'Trình duyệt' #" + "# } " +
                                "else if (statusCa==2) { # " + "#= 'Đã duyệt' #" + "# } " +
                                "else if (statusCa==3) {#" + "#=  'Từ chối duyệt' #" + "#} #",
                            width: 150,
                            field: "ass",
                            headerAttributes: { style: "text-align:center;" },
                        }
                    ]
                });
            deferred.resolve('done');
            return deferred.promise;
        }

        function showGrid() {
            if (vm.showDetail) {
                if (vm.categoryAcceptanceGrid.select().length > 0) {
                	if(vm.listacceptance.createdUserId != Constant.user.srvUser.userId){
						vm.disableCreat = true;
					}else{
						vm.disableCreat = false;
					}
                    if (vm.listacceptance.statusCa == 0 || vm.listacceptance.statusCa == 3) {
                        vm.listacceptance.categoryAcceptanceId;
                        if (vm.listacceptance.categoryAcceptanceId != null) {
                            acceptanceService.getCategoryAcceptanceById(vm.listacceptance.categoryAcceptanceId).then(function(d) {
                                vm.listacceptance = d.plain();
                            }, function(e) {
                                toastr.success(gettextCatalog.getString("Lỗi tải dữ liệu nên!"));
                            });
                        }
                        $(".k-invalid-msg").hide();
                        vm.showCreat = true;
                        vm.showDetail = false;
                        vm.isCreateNew = false;
                        vm.disableCreat = false;
                    } else {
                        vm.listacceptance.categoryAcceptanceId;
                        acceptanceService.getCategoryAcceptanceById(vm.listacceptance.categoryAcceptanceId).then(function(d) {
                            vm.listacceptance = d.plain();
                        }, function(e) {
                            toastr.success(gettextCatalog.getString("Lỗi tải dữ liệu nên!"));
                        });

                        $(".k-invalid-msg").hide();
                        vm.showCreat = true;
                        vm.isCreateNew = false;
                        vm.showDetail = false;
                        vm.disableCreat = true;
                    }
                } else {
                    toastr.warning("Bạn cần chọn một bản ghi trước");
                }
            } else {
                vm.showDetail = true;
                vm.showCreat = false;
                vm.showApproval = false;
                vm.listcategory.contractCode = vm.item.contractCode;
                vm.listcategory.contractName = vm.item.contractName;
                vm.listcategory.constrtCode = vm.item.constrtCode;
                vm.listcategory.constructId = vm.item.constructId;
                vm.listcategory.constructName = vm.item.constrtName;
                vm.listcategory.constructAddress = vm.item.constrtAddress;
                acceptanceService.getAllCategoryAcceptance(vm.listcategory.constructId, vm.item.contractId).then(function(d) {
                    // loadDataTable(d.plain());
                    refreshGrid(d.plain(), vm.categoryAcceptanceGrid);
                }, function(errResponse) {
                    console.error('Error while fetching object type');
                });
            }


        }

        function reloadCategoryAcceptance() {
            acceptanceService.getAllCategoryAcceptance(vm.listcategory.constructId).then(function(d) {
                refreshGrid(d.plain(), vm.categoryAcceptanceGrid);
                loadDataTable(d.plain());
                vm.showCreat = false;
            }, function(errResponse) {
                console.error('Error while fetching object type');
            });
        }

        function multiDelete() {
            var selectedRow = [];
            var listID = [];
            var noDel = 0;
            var noDel1 = 0;
            var grid = vm.categoryAcceptanceGrid;
            var isShowToart = false;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);

                if (checkbox.is(':checked')) {
                    grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    selectedRow.push(dataItem);
                }
            });

            for (var i = 0; i < selectedRow.length; i++) {
                if (selectedRow[i].statusCa === 0 || selectedRow[i].statusCa === 3) {
                    if (selectedRow[i].createdUserId === Constant.user.srvUser.userId) {
                        listID.push(selectedRow[i].categoryAcceptanceId);
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
                if (selectedRow.length > 0 && confirm("Xác nhận xóa ?")) {
                    acceptanceService.deleteCategoryAcceptanceList(listID).then(function() {
                        toastr.success("Đã xóa thành công");
                        initFormData();
                    }, function(errResponse) {
                        if (errResponse.status == 302) {
                            toastr.error("Bản ghi đang được sử dụng");
                        } else {
                            toastr.error("Có lỗi xảy ra trong quá trình xóa!");
                        }
                    });
                }



            } else if (selectedRow.length === 0) {
                if (!isShowToart)
                    toastr.warning("Chọn bản ghi trước khi xóa");
            }
        }




        vm.showGridItem = showGridItem;

        function showGridItem() {
            vm.showCreat = true;
            $(".k-invalid-msg").hide();
        }

        function add() {
            vm.showDetail = false;
            vm.showCreat = true;
            vm.isCreateNew = true;
            vm.showApproval = false;
            vm.disableCreat = false;
            getEmptyDataModel();
            getABC();
            //initFormData();
            vm.listacceptance.conclusion = '1';
            vm.listacceptance.contractCode = vm.item.contractCode;
            vm.listacceptance.constrtCode = vm.item.constrtCode;
            vm.listacceptance.contractDateSign = vm.item.signed_date;
            vm.listacceptance.constructId = vm.item.constructId;
            vm.listacceptance.constructName = vm.item.constrtName;
            vm.listacceptance.constructAddress = vm.item.constrtAddress;
            addRequired();
            $(".k-invalid-msg").hide();
        }
        vm.exapprovall = {};

        function onChange() {
            if (vm.categoryAcceptanceGrid.select().length > 0) {
                var tr = vm.categoryAcceptanceGrid.select().closest("tr");
                var dataItem = vm.categoryAcceptanceGrid.dataItem(tr);
                vm.listacceptance = dataItem;

                vm.AB.categoryAcceptanceId = vm.listacceptance.categoryAcceptanceId;

                // Gán giá trị để trình duyệt
                vm.exapprovall.categoryAcceptanceId = dataItem.categoryAcceptanceId;
                vm.listacceptance.statusCa = dataItem.statusCa;
                vm.theApproval.code = dataItem.code;
                vm.theApproval.constructId = dataItem.constructId;
                vm.theApproval.constrCompReMapId = dataItem.constrcompreMapId;
                vm.theApproval.stringEmployee = dataItem.binChargeConstructId + ',' + dataItem.amonitorId;
                vm.theApproval.isSign = 'theApproval';
                theApproval.setItem(vm.theApproval);
                vm.appro.constrCompReMapId = dataItem.constrcompreMapId;
            }
        }

        function refreshGrid(d) {
            var grid = vm.categoryAcceptanceGrid;
            if (grid) {
                vm.categoryAcceptanceGrid.dataSource.data(d);
                vm.categoryAcceptanceGrid.refresh();
            }
        }

        function detail() {
            if (vm.categoryAcceptanceGrid.select().length > 0) {
                vm.showDetail = true;
            } else {
                toastr.warning(gettextCatalog.getString("Chưa chọn bản ghi nào ?"));
            }
        }


        function save() {
            if (vm.showCreat) {
                if (vm.validator.validate()) {
                    var arrValidFrom = vm.listacceptance.acceptFromDate.split(" ");
                    var dateFrom = arrValidFrom[0];
                    var arrDateFrom = dateFrom.split("/");
                    var timeFrom = arrValidFrom[1];
                    var validTimeFrom = timeFrom.split(":");
                    var validFrom = new Date();
                    validFrom.setFullYear(arrDateFrom[2]);
                    validFrom.setMonth((arrDateFrom[1]) - 1);
                    validFrom.setDate(arrDateFrom[0]);
                    validFrom.setHours(validTimeFrom[0]);
                    validFrom.setMinutes(validTimeFrom[1]);

                    var arrValidTo = vm.listacceptance.acceptToDate.split(" ");
                    var dateTo = arrValidTo[0];
                    var arrDateTo = dateTo.split("/");
                    var timeTo = arrValidTo[1];
                    var validTimeTo = timeTo.split(":");
                    var validTo = new Date();
                    validTo.setFullYear(arrDateTo[2]);
                    validTo.setMonth((arrDateTo[1]) - 1);
                    validTo.setDate(arrDateTo[0]);
                    validTo.setHours(validTimeTo[0]);
                    validTo.setMinutes(validTimeTo[1]);
                    if (validFrom <= validTo) {
                        if (vm.listacceptance.estimatesItemChildId == null) {
                            toastr.error(gettextCatalog.getString("Chọn hạng mục có sẵn."));
                            return;
                        }
                        if (vm.isCreateNew) {
                            vm.listacceptance.statusCa = '0';
                            vm.listacceptance.isActive = '1';
                            //vm.listacceptance.createdDate = new Date();
                            vm.listacceptance.createdUserId = Constant.user.srvUser.userId;
                            vm.listacceptance.contractId = vm.item.contractId;
                            acceptanceService.addCategoryAcceptance(vm.listacceptance).then(function(result) {
                                toastr.success("Lưu thành công!");
                                showGrid();
                            }, function(errResponse) {
                                console.error('Error while creating TariffContractorType');
                                if (errResponse.status == 406) {
                                    toastr.error(gettextCatalog.getString("Hạng mục nghiêm thu đã tồn tại!"));
                                } else {
                                    toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo nghiệm thu giai đoạn CT !"));
                                }
                                return;
                            });
                        } else {
                            vm.listacceptanced = {};
                            vm.listacceptanced.code = vm.listacceptance.code;
                            vm.listacceptanced.categoryAcceptanceId = vm.listacceptance.categoryAcceptanceId;
                            vm.listacceptanced.constructId = vm.listacceptance.constructId;
                            vm.listacceptanced.estimatesItemChildId = vm.listacceptance.estimatesItemChildId;
                            vm.listacceptanced.amonitorId = vm.listacceptance.amonitorId;
                            vm.listacceptanced.binChargeConstructId = vm.listacceptance.binChargeConstructId;
                            vm.listacceptanced.acceptFromDate = vm.listacceptance.acceptFromDate;
                            vm.listacceptanced.acceptToDate = vm.listacceptance.acceptToDate;
                            vm.listacceptanced.applyBenchmark = vm.listacceptance.applyBenchmark;
                            vm.listacceptanced.isActive = '1';
                            vm.listacceptanced.statusCa = '0';
                            vm.listacceptanced.completeRequest = vm.listacceptance.completeRequest;
                            vm.listacceptanced.approvalDate = vm.listacceptance.approvalDate;
                            vm.listacceptanced.otherComments = vm.listacceptance.otherComments;
                            vm.listacceptanced.otherDocuments = vm.listacceptance.otherDocuments;
                            vm.listacceptanced.conclusion = vm.listacceptance.conclusion;
                            vm.listacceptanced.constructionQuality = vm.listacceptance.constructionQuality;
                            vm.listacceptanced.acceptPlace = vm.listacceptance.acceptPlace;
                            vm.listacceptanced.signPlace = vm.listacceptance.signPlace;
                            vm.listacceptanced.signDate = vm.listacceptance.signDate;

                            if (vm.listacceptance.createdUserId == Constant.user.srvUser.userId) {
                                acceptanceService.updateCategoryAcceptance(vm.listacceptanced).then(function(result) {
                                    toastr.success("Cập nhật thành công!");
                                    //ListshowGrid();
                                    showGrid();
                                }, function(errResponse) {
                                    toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
                                });
                            } else {
                                toastr.warning("Bạn không phải người tạo, không có quyền sửa");
                            }
                        }
                    } else {
                        toastr.error(gettextCatalog.getString("Ngày kết thúc phải bằng hoặc sau ngày bắt đầu"));
                    }

                }
            } else if (vm.showApproval) {
                toshowApproval();
            }
        }


        var dataToApproval = {
            documentCode: '',
            listAMonitorSign: {},
            listBInChargeSign: {},
            listFileSign: {},
        }

        // Phê duyệt
        vm.appro = {
            statusCa: '',
            employeeId: Constant.getUser().srvUser.catEmployeeId,
            comments: "",
            constrCompReMapId: '',
            categoryAcceptanceId: ''
        }

        function toshowApproval() {

            vm.appro.statusCa = vm.approval.statusCa;
            vm.appro.categoryAcceptanceId = vm.listapproval.categoryAcceptanceId;

            acceptanceService.Approval(vm.appro).then(function(x) {
                if (x == '1') {
                    toastr.warning("Chưa đến lượt duyệt!");
                    vm.showDetail = true;
                    vm.showCreat = false;
                    vm.showApproval = false;
                }
                if (x == '2') {
                    toastr.warning("Đã duyệt rồi");
                    vm.showDetail = true;
                    vm.showCreat = false;
                    vm.showApproval = false;
                }
                if (x == '4') {
                    toastr.success("Từ chối duyệt thành công");
                    vm.showDetail = true;
                    vm.showCreat = false;
                    vm.showApproval = false;
                    initFormData();
                }
                if (x == '3') {
                    toastr.success("Duyệt thành công");
                    vm.showDetail = true;
                    vm.showCreat = false;
                    vm.showApproval = false;
                    initFormData();
                }
                if (x == '5') {
                    toastr.warning("Bạn không được phép duyệt");
                    vm.showDetail = true;
                    vm.showCreat = false;
                    vm.showApproval = false;
                }
            }, function(errResponse) {
                toastr.error(gettextCatalog.getString("Xảy ra lỗi khi duyệt!"));
            });
        }

        vm.approval = {};
        vm.sendToApproval = sendToApproval;

        function sendToApproval() {
            var grid = vm.categoryAcceptanceGrid;
            if (grid.select() == null || grid.select().length == 0) {
                toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
                return;
            }
            var tr = grid.select().closest("tr");
            var dataItem = grid.dataItem(tr);
            if (vm.categoryAcceptanceGrid.select().length > 0) {
                if (vm.listacceptance.statusCa == 1) {
                    vm.showCreat = false;
                    vm.showDetail = false;
                    vm.showApproval = true;
                    vm.disApproval = true;
                    vm.approval.statusCa = 2;
                    vm.listacceptance.categoryAcceptanceId;
                    if (vm.listacceptance.categoryAcceptanceId != null) {
                        acceptanceService.getCategoryAcceptanceById(vm.listacceptance.categoryAcceptanceId).then(function(d) {
                            vm.listapproval = d.plain();

                        }, function(e) {
                            toastr.success(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu!"));
                        });
                    }



                } else {
                    toastr.warning("Trạng thái trình duyệt mới được phê duyệt!");
                }
            } else {
                toastr.warning("Xảy ra lỗi trong quá trình duyệt!");
            }

        }

        // Trình duyệt
        vm.sendBrowse = sendBrowse;

        function sendBrowse() {
            if (vm.listacceptance.statusCa == 0) {

                $('#loading').show();
                acceptanceService.exportPDF(vm.exapprovall).then(function(data) {
                    vm.theApproval.path = data.fileName;
                    vm.theApproval.nameFile = 'BM-TCNT-14.pdf';
                    	//5-10
                    vm.theApproval.roleId = [Constant.ROLE_ID["employee_roleID_DT_KTTC"], Constant.ROLE_ID["employee_roleID_CDT_GSTC"]];
                    vm.theApproval.roleName = ["Kỹ thuật thi công", "Giám sát thi công của chủ đầu tư"];
                    theApproval.setItem(vm.theApproval);
                    goTo('THE_APPROVAL');
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi export , không thể trình duyệt!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });

            } else {
                toastr.warning("Trạng thái soạn thảo mới được trình duyệt!");
            }

        }
        //ngoccx
        //huy trinh duyet
        vm.cancelApprovalBtn = function() {
            var grid = vm.categoryAcceptanceGrid;
            if (grid.select() == null || grid.select().length == 0) {
                toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
                return;
            }
            vm.listacceptance.tableName = 'CATEGORY_ACCEPTANCE';
            vm.listacceptance.tableId = vm.listacceptance.categoryAcceptanceId;
            vm.listacceptance.tableIdField = 'CATEGORY_ACCEPTANCE_ID';
            vm.listacceptance.constrCompReMapId = vm.listacceptance.constrcompreMapId;
            if (vm.listacceptance.statusCa == 1) {
                if (vm.listacceptance.createdUserId != Constant.user.srvUser.userId) {
                    toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
                    return;
                } else {
                    if (confirm('Xác nhận hủy trình duyệt')) {
                        acceptanceService.cancelAprroval(vm.listacceptance).then(function() {
                        	status = true;
							$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
							initFormData();
                            toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
                        }, function() {
                            toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
                            return;
                        });
                    }
                }
            } else {
                toastr.warning("Trạng thái trình duyệt mới được hủy trình duyệt");
            }
        }

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
                // toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
                // hiện tại không được phép truy cập vào chức năng này!"));
            }

        }

        vm.exportFilePDF = exportFilePDF;

        function exportFilePDF() {
            if (vm.showDetail) {
                var selectedRow = [];
                var grid = vm.categoryAcceptanceGrid;
                grid.table.find("tr").each(function(idx, item) {
                    var row = $(item);
                    var checkbox = $('[name="gridcheckbox"]', row);
                    if (checkbox.is(':checked')) {
                        // Push id into selectedRow
                        var tr = grid.select().closest("tr");
                        var dataItem = grid.dataItem(item);
                        selectedRow.push(dataItem.categoryAcceptanceId);

                    }
                });
                if (selectedRow.length == 0) {
                    if (vm.categoryAcceptanceGrid.select().length > 0) {
                        var tr = vm.categoryAcceptanceGrid.select().closest("tr");
                        var dataItem = vm.categoryAcceptanceGrid.dataItem(tr);
                        vm.AB.categoryAcceptanceId = dataItem.categoryAcceptanceId;
                        $('#loading').show();
                        acceptanceService.exportPDF(vm.AB).then(function(data) {
                            window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                            toastr.success(gettextCatalog.getString("Export thành công !"));
                        }).catch(function() {
                            toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                            return;
                        }).finally(function() {
                            $('#loading').hide();
                        });
                    } else {
                        toastr.warning("Bạn cần chọn bản ghi để export !");
                    }
                } else {
                    if (selectedRow.length > 0) {
                        $('#loading').show();
                        acceptanceService.exportListPDFCategoryAcceptence(selectedRow).then(function(data) {
                            window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                            toastr.success(gettextCatalog.getString("Export thành công !"));
                        }).catch(function() {
                            toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                            return;
                        }).finally(function() {
                            $('#loading').hide();
                        });

                    }
                }

            } else if (vm.showCreat) {
                $('#loading').show();
                vm.AB.categoryAcceptanceId = vm.listacceptance.categoryAcceptanceId;
                acceptanceService.exportPDF(vm.AB).then(function(data) {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    toastr.success(gettextCatalog.getString("Export thành công !"));
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });

            }

        }
        vm.exportFileDOC = exportFileDOC;

        function exportFileDOC() {
            if (vm.showDetail) {
                var selectedRow = [];
                var grid = vm.categoryAcceptanceGrid;
                grid.table.find("tr").each(function(idx, item) {
                    var row = $(item);
                    var checkbox = $('[name="gridcheckbox"]', row);
                    if (checkbox.is(':checked')) {
                        // Push id into selectedRow
                        var tr = grid.select().closest("tr");
                        var dataItem = grid.dataItem(item);

                        selectedRow.push(dataItem.categoryAcceptanceId);

                    }
                });
                if (selectedRow.length == 0) {
                    if (vm.categoryAcceptanceGrid.select().length > 0) {
                        var tr = vm.categoryAcceptanceGrid.select().closest("tr");
                        var dataItem = vm.categoryAcceptanceGrid.dataItem(tr);
                        vm.AB.categoryAcceptanceId = dataItem.categoryAcceptanceId;
                        $('#loading').show();
                        acceptanceService.exportDOC(vm.AB).then(function(data) {
                            window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                            toastr.success(gettextCatalog.getString("Export thành công !"));
                        }).catch(function() {
                            toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                            return;
                        }).finally(function() {
                            $('#loading').hide();
                        });
                    } else {
                        toastr.warning("Bạn cần chọn bản ghi để export !");
                    }
                } else {
                    if (selectedRow.length > 0) {
                        $('#loading').show();
                        acceptanceService.exportListDocCategoryAcceptence(selectedRow).then(function(data) {
                            window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                            toastr.success(gettextCatalog.getString("Export thành công !"));
                        }).catch(function() {
                            toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                            return;
                        }).finally(function() {
                            $('#loading').hide();
                        });

                    }
                }

            } else if (vm.showCreat) {
                $('#loading').show();
                vm.AB.categoryAcceptanceId = vm.listacceptance.categoryAcceptanceId;
                acceptanceService.exportDOC(vm.AB).then(function(data) {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    toastr.success(gettextCatalog.getString("Export thành công !"));
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });

            }
        }
        
        function addRequired() {
            //TODO
            document.getElementById("acceptance_Phaseworck_txtEstimatesItemChildIdXXX").required = true;
            document.getElementById("acceptance_Phaseworck_txtAmonitorId").required = true;
            document.getElementById("ground_handover_txtBinChargeConstructId").required = true;
        }
        
        $scope.$on("ChangeStatusApproval", function(event, result) {
            if (result) {
            	initFormData();
            }
        });
    }
})();