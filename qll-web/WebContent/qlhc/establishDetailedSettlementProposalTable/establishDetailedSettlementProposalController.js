(function() {
    'use strict';

    var controllerId = 'establishDetailedSettlementProposalController';

    angular.module('MetronicApp').controller(controllerId,
        establishDetailedSettlementProposalController);

    /* @ngInject */
    function establishDetailedSettlementProposalController($scope, $rootScope, $timeout, Constant,
        kendoConfig, establishDetailedSettlementProposalService, ProposalEvaluation, gettextCatalog, $kWindow,WindowService,
        CommonService, Restangular, PopupConst, theApproval,inspectionService) {
        var vm = this;
        vm.item = {};
        vm.itemPopup = {};
        vm.theApproval = {
            code: '',
            constructId: '',
            constrCompReMapId: '',
            stringEmployee: '',
            isSign: '',
            path: '',
            nameFile: '',
            roleName: []
        }

        vm.approDTO = {
            statusCa: '',
            employeeId: '',
            comments: '',
            detailSettlementProposalId: '',
            constrCompReMapId: ''
        }
        vm.detailSettlement = [];
        // dữ liệu Tìm kiếm gửi
        mapdata();

        function mapdata() {
            vm.item.constructId = ProposalEvaluation.getItem().constructId;
            vm.item.constrtName = ProposalEvaluation.getItem().constrtName;
            vm.item.contractCode = ProposalEvaluation.getItem().contractCode;
            vm.item.contractName = ProposalEvaluation.getItem().contractName;
            vm.item.constrtCode = ProposalEvaluation.getItem().constrtCode;
            vm.item.signed_date = ProposalEvaluation.getItem().signed_date;
            vm.item.stationAddress = ProposalEvaluation.getItem().stationAddress;
            vm.item.constrtAddress = ProposalEvaluation.getItem().constrtAddress;
            // vm.item.getcatEmployeeId = ProposalEvaluation.getItem().getcatEmployeeId;
        }
        // END dữ liệu Tìm kiếm gửi


        vm.showGrid = showGrid;
        vm.showDetail = true;
        vm.isCreateNew = false;
        vm.hideSave = false;
        vm.hideAdd = true;
        vm.disabledPheDuyet = false;
        vm.dopheDuyet = dopheDuyet;
        //		vm.hideAppraisalAssignment = true;
        //		vm.hideUnAssignment = true;
        //		vm.hideAssignment = true;
        //		vm.hidePheDuyet = true;
        //		vm.hideBrowser = true;
        vm.hideEdit = false;
        vm.hideRemove = false;
        vm.disableForm = false;
        vm.add = add;
        vm.undo = undo;
        vm.goTo = goTo;
        vm.fail = fail;
        vm.chkSelectAll = chkSelectAll;
        vm.showPopupAppraisalAssignment = showPopupAppraisalAssignment;
        vm.showPopupImportDiscountedSettlementProposal = showPopupImportDiscountedSettlementProposal;
        vm.onClickCellInsideContract = onClickCellInsideContract;
        vm.save = save;
        vm.remove = remove;

        // bảng chính
        vm.detailSettlementProposal = {
            approvalDate: '',
            brepresentativeId: '',
            code: '',
            constrtCode: '',
            constructId: '',
            contractCode: '',
            contractName: '',
            createdDate: '',
            createdUserId: '',
            defaultSortField: '',
            detailSettlementProposalId: '',
            evaluateComments: '',
            evaluateFinishDate: '',
            evaluatePersonId: '',
            evaluateStatus: '',
            isActive: '',
            sendPersonId: '',
            statusCa: ''
        };
        vm.detailSettlementProposal.constructId = vm.item.constructId;
        vm.detailSettlementProposal.contractCode = vm.item.contractCode;
        // bangcongviechopdong
        vm.estimateContrat = {};
        vm.estimateContrat.constructionId = vm.item.constructId;

        // dropdown nguoiLap
        vm.resultCreator = {
            constructId: '',
//            roleid: '106',
            contractCode: vm.item.contractCode
            
        };
        vm.resultCreator.constructId = vm.item.constructId;
        // dropdown daidienchudautu
        vm.resultRepresentingContractors = {
            constructId: '',
            contractCode: vm.item.contractCode
        };
        vm.resultRepresentingContractors.roleid = Constant.ROLE_ID["employee_roleID_DT_GDNT"];
        vm.resultRepresentingContractors.constructId = vm.item.constructId;

//        establishDetailedSettlementProposalService.getRoleId().then(function(data) {
//            vm.role = data;
////            vm.resultCreator.roleid = vm.role[1];
//            vm.resultRepresentingContractors.roleid = vm.role[3];
//        }).catch(function(data, status) {
//            console.error('getRoleId error', response.status, response.data);
//        });
        //		function getCreatorRoleId(){
        //			establishDetailedSettlementProposalService.getListEmployeeByRole(Constant.getUser().srvUser.catEmployeeId).then(
        //					function(d) {
        //						vm.role = d.plain();
        //					}, function(errResponse) {
        //						console.error('Error while fetching locatorbank');
        //					});
        //		}
        //		
        //		angular.element(document.body).injector().get('Constant');
        // dropdown nguoiLap
        vm.recordpopup = 0;
	    vm.gridCustom = [{
			title: "STT",
			template: function (item) {
				return ++vm.recordpopup;
			},
			width: 25,
			headerAttributes: {
				style: "text-align:center;font-weight: bold;"
			},
			attributes: {
				style: "text-align:center"
			}
		},{
			title: gettextCatalog.getString("Mã"),
			field: "code",
			width: 100,
		}, {
			title: gettextCatalog.getString("Tên"),
			field: "fullName",
			width: 100
		}, {
			title: gettextCatalog.getString("Email"),
			field: "email",
			width: 100
		}
	];
        vm.rowIndex = 0;
        fillDataTableEstimateListDetail([]);
        fetchAllTableEstimateListDetail();

        fillDataTableInsideContract([]);
        fillDataTableOutsideContract([]);
        fillDataTableSumContract([]);
        fillDataTableFileCalculate([]);
        function fillDataTableSumContract(data) {
            vm.validatorOptions = kendoConfig.get('validatorOptions');
            vm.gridOptionsSumContract = kendoConfig.getGridOptions({
                autoBind: true,
                /*dataSource: data,*/

                editable: false,
                noRecords: true,
                dataSource: {
                	type: "odata",
                    transport: {
                        read: data
                    },
                	aggregate: [
                        { field: "allotmentAmount", aggregate: "sum", },
                        { field: "advanceAmount", aggregate: "sum", },
                        { field: "deviantAdvance", aggregate: "sum" }
                    ],
                },
                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                columns: [{
                        title: gettextCatalog.getString("STT"),
                        field: "stt",
                        template: dataItem => $("#sumContract").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        width: 60,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:center;" },
                    },
                    {
                        title: gettextCatalog.getString("Nội dung"),
                        field: "code",
                        template: function($scope) {
                            if ($scope.type == 1) {
                                return "Nội dung công việc trong HĐ";
                            }
                            if ($scope.type == 2) {
                                return "Nội dung công việc phát sinh ngoài HĐ";
                            }
                        },
                        width: 150,
                        headerAttributes: { style: "text-align:center;  white-space: normal;" },
                        attributes: { style: "text-align:left;" },
                        footerTemplate: "<center>TỔNG GIÁ TRỊ</center>",
                    },
                    {
                        title: gettextCatalog.getString("Thành tiền theo hợp đồng (sau VAT)"),
                        field: "allotmentAmount",
                        /*width: 120,*/
                        headerAttributes: { style: "text-align:center;  white-space: normal" },
                        attributes: { style: "text-align:right;" },
                        footerAttributes: {
                            style: "text-align: right;"
                        },
                        width: 150,
//                        template: "<div>#= kendo.toString(allotmentAmount, 'n3') #</div>",
                        template :  "# if(allotmentAmount != null){ #" + "#= kendo.toString(allotmentAmount, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                        footerTemplate: "# if(sum != null){ #" + "#= kendo.toString(sum, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                    },
                    {
                        title: gettextCatalog.getString("Thành tiền đề nghị (sau VAT)"),
                        field: "advanceAmount",
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                        footerAttributes: {
                            style: "text-align: right;"
                        },
                        width: 150,
//                        template: "<div>#= kendo.toString(advanceAmount, 'n3') #</div>",
//                        footerTemplate: "<div>#= kendo.toString(sum, 'n3') #</div>",
                        template :  "# if(advanceAmount != null){ #" + "#= kendo.toString(advanceAmount, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                        footerTemplate: "# if(sum != null){ #" + "#= kendo.toString(sum, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                    }, {
                        title: gettextCatalog.getString("Chênh lệch giữa thành tiền hợp đồng và đề nghị"),
                        field: "deviantAdvance",
                        width: 180,
                        headerAttributes: { style: "text-align:center;  white-space: normal" },
                        attributes: { style: "text-align:right;" },
                        footerAttributes: {
                            style: "text-align: right;"
                        },
//                        template: "<div>#= kendo.toString(deviantAdvance, 'n3') #</div>",
//                        footerTemplate: "<div>#= kendo.toString(sum, 'n3') #</div>",
                        template :  "# if(deviantAdvance != null){ #" + "#= kendo.toString(deviantAdvance, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                        footerTemplate: "# if(sum != null){ #" + "#= kendo.toString(sum, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                    }
                ]
            });
        }

        // InsideContract
        function fetchAllSumContract(obj) {
            if (vm.item.constructId != undefined && vm.item.constructId != null) {
                establishDetailedSettlementProposalService.getAllEstimatesWorkContract(obj).then(
                    function(d) {
                        refreshGridSumContract(d.plain());
                    },
                    function(errResponse) {
                        console.error('Error while fetching InsideContract');
                    });
            }
        }

        function refreshGridSumContract(d) {
            var grid = vm.gridSumContract;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        vm.onGridSumContract = function(e, sel, dataItem) {
            vm.bean = dataItem;
            vm.rowIndex = sel.selected.rowIndex;
            //            onChange();
        }

        function fillDataTableEstimateListDetail(data) {
            vm.validatorOptions = kendoConfig.get('validatorOptions');
            vm.gridOptions = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: data,
                editable: false,
                change: onChangeTableEstimateListDetail,
                noRecords: true,
                dataBound: function(e) {
                    var grid = $("#estimateWorkTables").data("kendoGrid");
                    if (!vm.rowIndex) {
                        grid.select("tr:eq(0)");
                    } else {
                        grid.select("tr:eq(" + vm.rowIndex + ")");
                    }
                },
                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                columns: [{
                        title: gettextCatalog.getString("STT"),
                        field: "stt",
                        template: dataItem => $("#estimateWorkTables").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        width: 80,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:center;" },
                    },
                    {
                        title: gettextCatalog.getString("Mã đề nghị"),
                        field: "code",
                        width: 180,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Mã công trình"),
                        field: "constrtCode",
                        width: 150,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Mã hợp đồng"),
                        field: "contractCode",
                        width: 150,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Tên hợp đồng"),
                        field: "contractName",
                        width: 150,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Trạng thái duyệt"),
                        field: "statusCa",
                        template: function($scope) {
                            if ($scope.statusCa == 0) {
                                return "Soạn Thảo";
                            }
                            if ($scope.statusCa == 1) {
                                return "Trình Duyệt";
                            }
                            if ($scope.statusCa == 2) {
                                return "Đã Duyệt";
                            }
                            if ($scope.statusCa == 3) {
                                return "Từ Chối Duyệt";
                            }
                        },
                        width: 150,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;", class:"statusColumn" },
                    },
                    {
                        title: gettextCatalog.getString("Trạng thái thẩm định"),
                        field: "evaluateStatus",
                        template: function($scope) {
                            if ($scope.evaluateStatus == 0) {
                                return "Chưa Giao Thẩm Định";
                            }
                            if ($scope.evaluateStatus == 1) {
                                return "Đã Giao Thẩm Định";
                            }
                            if ($scope.evaluateStatus == 2) {
                                return "Đồng Ý Thẩm Định";
                            }
                            if ($scope.evaluateStatus == 3) {
                                return "Từ Chối Thẩm Định";
                            }
                        },
                        width: 170,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Người được giao thẩm định"),
                        field: "fullNameEmployee",
                        width: 220,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Lý do từ chối thẩm định"),
                        field: "evaluateComments",
                        width: 220,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:left;" },
                    }
                ]
            });
        }

        function onChangeTableEstimateListDetail() {
            if (vm.tableEstimateListDetail.select().length > 0) {
                var tr = vm.tableEstimateListDetail.select().closest("tr");
                var dataItem = vm.tableEstimateListDetail.dataItem(tr);
                vm.item = dataItem;
                establishDetailedSettlementProposalService.setCode(vm.item);
                establishDetailedSettlementProposalService.setEvaluatePersonId(vm.item);

                $rootScope.$broadcast("establishDetailedSettlementProposalService.evaluatePersonId", vm.item.evaluatePersonId);
                mapdata();
                // Gán giá trị để trình duyệt
                vm.theApproval.code = dataItem.code;
                vm.theApproval.constructId = dataItem.constructId;
                vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
                vm.theApproval.stringEmployee = dataItem.sendPersonId + ',' + dataItem.brepresentativeId;
                vm.theApproval.roleName = ["Người lập", "Đại diện nhà thầu"];
                vm.theApproval.roleId = [0, vm.resultRepresentingContractors.roleid];

                // vm.theApproval.nameFile = "victory";
                vm.theApproval.isSign = 'theApproval';
                theApproval.setItem(vm.theApproval);

                // Gán giá trị để phê duyệt
                // vm.approDTO.employeeId = "3506";
                vm.approDTO.employeeId = Constant.getUser().srvUser.catEmployeeId;
                vm.approDTO.detailSettlementProposalId = dataItem.detailSettlementProposalId
                vm.approDTO.constrCompReMapId = dataItem.constrCompReMapId;
            }
        }

        vm.onGridTableEstimateListDetail = function(e, sel, dataItem) {
            vm.bean = dataItem;
            vm.rowIndex = sel.selected.rowIndex;
            onChangeTableEstimateListDetail();
        }

        $scope.$on("establishPopupController.status", function(event, result) {
            if (result) {
                fetchAllTableEstimateListDetail();
            }
        });

        $scope.$on("ChangeStatusApproval", function(event, result) {
            if (result) {
                fetchAllTableEstimateListDetail();
            }
        });

        $scope.$on("estimateTableDetailController.statusDel", function(event, result) {
            if (result) {
                fetchAllTableEstimateListDetail();
            }
        });

        $scope.$on("estimateTableDetailController.statusSave", function(event, result) {
            if (result) {
                fetchAllTableEstimateListDetail();
            }
        });

        // TableEstimateListDetail
        function fetchAllTableEstimateListDetail() {
            establishDetailedSettlementProposalService.getAllDetailSettlementProposal(vm.detailSettlementProposal).then(
                function(d) {
                    if (d.length != 0) {
                        vm.hideAdd = true;
                        vm.isCreateNew = false;
                        vm.detailSettlement = d.plain();

                        if (vm.detailSettlement[0].statusCa == 0 || vm.detailSettlement[0].statusCa == 3) {
                            vm.hideEdit = false;
                            vm.hideRemove = false;
                            vm.hideSave = false;
                        }

                        if (vm.detailSettlement[0].statusCa == 0) {
                            vm.hideBrowser = false;
                            vm.hideEdit = false;
                            vm.hideSave = false;
                        }

                        if (vm.detailSettlement[0].statusCa == 2) {
                            vm.hideEdit = false;
                            vm.hideSave = true;
                            vm.hideRemove = true;
                        }

                        if (vm.detailSettlement[0].statusCa == 1) {
                            vm.hideEdit = false;
                            vm.hideRemove = true;
                            vm.hideSave = false;
                        }

                        if (vm.detailSettlement[0].evaluateStatus == 1) {
                            vm.hideEdit = false;
                            vm.hideSave = true;
                        }

                        if (vm.detailSettlement[0].evaluateStatus == 2) {
                            vm.hideEdit = false;
                            vm.hideSave = true;
                        }
                    } else {
                        vm.hideAdd = false;
                    }
                    refreshGridTableEstimateListDetail(d.plain());
                },
                function(errResponse) {
                    console.error('Error while fetching TableEstimateListDetail');
                });
        }

        function refreshGridTableEstimateListDetail(d) {
            var grid = vm.tableEstimateListDetail;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        function fillDataTableInsideContract(data) {
            vm.validatorOptions = kendoConfig.get('validatorOptions');
            vm.gridOptionsInsideContract = kendoConfig.getGridOptions({
                autoBind: true,
                editable: false,
                dataSource: data,
                change: onClickCellInsideContract,
                noRecords: true,
                dataBound: function(e) {
                    var grid = $("#contentWorkInsideContract").data("kendoGrid");
                    if (!vm.rowIndex) {
                        grid.select("tr:eq(0)");
                    } else {
                        grid.select("tr:eq(" + vm.rowIndex + ")");
                    }
                },
                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                columns: [{
                    title: gettextCatalog.getString("STT"),
                    field: "stt",
                    template: dataItem => $("#contentWorkInsideContract").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                    width: 80,
                    headerAttributes: { style: "text-align:center;" },
                    attributes: { style: "text-align:center;" },
                },
                {
                    title: gettextCatalog.getString("Nội dung công việc"),
                    field: "workItemName",
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:left;" },
                },
                {
                    title: gettextCatalog.getString("Đơn vị tính"),
                    field: "unit",
                    width: 150,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:left;" },
                },
                {
                    title: gettextCatalog.getString("Khối lượng theo hợp đồng"),
                    field: "workAmount",
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.workAmount)) {
                            return kendo.toString(dataItem.workAmount,'n3');
                        }
                        return kendo.toString(0,'n3');
                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:right;" },
                },
                {
                    title: gettextCatalog.getString("Khối lượng đề nghị quyết toán"),
                    field: "executeQuantity",
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.executeQuantity)) {
                            return kendo.toString(dataItem.executeQuantity,'n3');
                        }
                        return kendo.toString(0,'n3');
                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:right;" },
                },
                {
                    title: gettextCatalog.getString("Đơn giá hợp đồng"),
                    field: "unitPrice",
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.unitPrice)) {
                            return kendo.toString(dataItem.unitPrice,'n3');
                        }
                        return kendo.toString(0,'n3');
                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:right;" },
                },
                {
                    title: gettextCatalog.getString("Đơn giá đề nghị quyết toán"),
                    field: "settleUnitPrice",
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.settleUnitPrice)) {
                        	 return kendo.toString(dataItem.settleUnitPrice,'n3');
                        }
                        return kendo.toString(0,'n3');
                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:right;" },

                },
                {
                    title: gettextCatalog.getString("<b>Thành tiền hợp đồng</b>"),
                    field: "tienGiaoKhoan",
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.workAmount * dataItem.unitPrice)) {
                            return kendo.toString(dataItem.workAmount * dataItem.unitPrice,'n3');
                        }
                        return kendo.toString(0,'n3');
                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:right;" },
                },
                {
                    title: gettextCatalog.getString("<b>Thành tiền đề nghị quyết toán</b>"),
                    field: "tienDeNghiQuyetToan",
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.executeQuantity * dataItem.settleUnitPrice)) {
                            return kendo.toString(dataItem.executeQuantity * dataItem.settleUnitPrice,'n3');
                        }
                        return kendo.toString(0,'n3');
                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:right;" },
                },
                {
                    title: gettextCatalog.getString("<b>Chênh lệch giữa đề nghị và hợp đồng</b>"),
                    field: "chenhLech",
                    template: function(dataItem) {
                        if ($.isNumeric((dataItem.executeQuantity * dataItem.settleUnitPrice) - (dataItem.workAmount * dataItem.unitPrice))) {
                            return kendo.toString((dataItem.executeQuantity * dataItem.settleUnitPrice) - (dataItem.workAmount * dataItem.unitPrice),'n3');
                        }
                        return kendo.toString(0,'n3');
                    },
                    format: "{0:n3}",
                    decimals: 3,
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: { style: "text-align:right;" },
                }
            ]
            });
        }

        function onClickCellInsideContract() {
            if (vm.contentWorkInsideContract.select().length > 0) {
                var tr = vm.contentWorkInsideContract.select().closest("tr");
                var dataItem = vm.contentWorkInsideContract.dataItem(tr);
                vm.item = dataItem;
            }
        }

        vm.onGridContentWorkInsideContract = function(e, sel, dataItem) {
            vm.bean = dataItem;
            vm.rowIndex = sel.selected.rowIndex;
            // ();
        }

        // InsideContract
        function fetchAllInsideContract(obj) {
            if (vm.item.constructId != undefined && vm.item.constructId != null) {
                establishDetailedSettlementProposalService.getAllEstimatesWorkInsideContract(obj).then(
                    function(d) {
                        refreshGridInsideContract(d.plain());
                    },
                    function(errResponse) {
                        console.error('Error while fetching InsideContract');
                    });
            }
        }

        function refreshGridInsideContract(d) {
            var grid = vm.contentWorkInsideContract;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        function fillDataTableOutsideContract(data) {
            vm.validatorOptions = kendoConfig.get('validatorOptions');
            vm.gridOptionsOutsideContract = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: data,
                editable: false,
                // change : onChange,
                noRecords: true,
                dataBound: function(e) {
                    var grid = $("#contentWorkOutsideContract").data("kendoGrid");
                    if (!vm.rowIndex) {
                        grid.select("tr:eq(0)");
                    } else {
                        grid.select("tr:eq(" + vm.rowIndex + ")");
                    }
                },
                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                columns: [{
                        title: gettextCatalog.getString("STT"),
                        field: "stt",
                        template: dataItem => $("#contentWorkOutsideContract").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        width: 80,
                        headerAttributes: { style: "text-align:center;" },
                        attributes: { style: "text-align:center;" },
                    },
                    {
                        title: gettextCatalog.getString("Nội dung công việc"),
                        field: "workItemName",
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Đơn vị tính"),
                        field: "unit",
                        width: 150,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:left;" },
                    },
                    {
                        title: gettextCatalog.getString("Khối lượng theo hợp đồng"),
                        field: "workAmount",
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.workAmount)) {
                                return kendo.toString(dataItem.workAmount,'n3');
                            }
                            return kendo.toString(0,'n3');
                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                    },
                    {
                        title: gettextCatalog.getString("Khối lượng đề nghị quyết toán"),
                        field: "executeQuantity",
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.executeQuantity)) {
                                return kendo.toString(dataItem.executeQuantity,'n3');
                            }
                            return kendo.toString(0,'n3');
                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                    },
                    {
                        title: gettextCatalog.getString("Đơn giá hợp đồng"),
                        field: "unitPrice",
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.unitPrice)) {
                                return kendo.toString(dataItem.unitPrice,'n3');
                            }
                            return kendo.toString(0,'n3');
                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                    },
                    {
                        title: gettextCatalog.getString("Đơn giá đề nghị quyết toán"),
                        field: "settleUnitPrice",
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.settleUnitPrice)) {
                            	 return kendo.toString(dataItem.settleUnitPrice,'n3');
                            }
                            return kendo.toString(0,'n3');
                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },

                    },
                    {
                        title: gettextCatalog.getString("<b>Thành tiền hợp đồng</b>"),
                        field: "tienGiaoKhoan",
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.workAmount * dataItem.unitPrice)) {
                                return kendo.toString(dataItem.workAmount * dataItem.unitPrice,'n3');
                            }
                            return kendo.toString(0,'n3');
                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                    },
                    {
                        title: gettextCatalog.getString("<b>Thành tiền đề nghị quyết toán</b>"),
                        field: "tienDeNghiQuyetToan",
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.executeQuantity * dataItem.settleUnitPrice)) {
                                return kendo.toString(dataItem.executeQuantity * dataItem.settleUnitPrice,'n3');
                            }
                            return kendo.toString(0,'n3');
                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                    },
                    {
                        title: gettextCatalog.getString("<b>Chênh lệch giữa đề nghị và hợp đồng</b>"),
                        field: "chenhLech",
                        template: function(dataItem) {
                            if ($.isNumeric((dataItem.executeQuantity * dataItem.settleUnitPrice) - (dataItem.workAmount * dataItem.unitPrice))) {
                                return kendo.toString((dataItem.executeQuantity * dataItem.settleUnitPrice) - (dataItem.workAmount * dataItem.unitPrice),'n3');
                            }
                            return kendo.toString(0,'n3');
                        },
                        format: "{0:n3}",
                        decimals: 3,
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                    }
                ]
            });
        }

        vm.onGridContentWorkOutsideContract = function(e, sel, dataItem) {
            vm.bean = dataItem;
            vm.rowIndex = sel.selected.rowIndex;
        }

        function fetchAllOutsideContract(obj) {
            establishDetailedSettlementProposalService.getAllEstimatesWorkOutsideContract(obj).then(
                function(d) {
                    refreshGridOutsideContract(d.plain());
                },
                function(errResponse) {
                    console.error('Error while fetching OutsideContract');
                });
        }

        function refreshGridOutsideContract(d) {
            var grid = vm.contentWorkOutsideContract;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        function showGrid() {

            if (vm.showDetail) {
                if (vm.item.statusCa == 1 || vm.item.statusCa == 2) {
                    vm.disableForm = true;
                    vm.showDetail = false;
                    vm.disabledPheDuyet = false;
                    fetchAllInsideContract(vm.estimateContrat);
                    fetchAllOutsideContract(vm.estimateContrat);
                    fetchAllSumContract(vm.estimateContrat);
                    getCalculateFile();
                    return;
                }
                if (vm.item.statusCa == 2) {
                    vm.disableForm = true;
                    vm.showDetail = false;
                    vm.disabledPheDuyet = true;
                    fetchAllInsideContract(vm.estimateContrat);
                    fetchAllOutsideContract(vm.estimateContrat);
                    fetchAllSumContract(vm.estimateContrat);
                    getCalculateFile();
                    return;
                }

                if (vm.tableEstimateListDetail.select().length > 0) {
                	if(Constant.user.srvUser.userId == vm.item.createdUserId){
                		 vm.showDetail = false;
                         vm.disabledPheDuyet = false;
                         if (vm.item.statusCa == 3) {
                             vm.disableForm = false;
                         }
                         fetchAllInsideContract(vm.estimateContrat);
                         fetchAllOutsideContract(vm.estimateContrat);
                         fetchAllSumContract(vm.estimateContrat);
                         getCalculateFile();
                	}else{
                		vm.disableForm = true;
                        vm.showDetail = false;
                        vm.disabledPheDuyet = false;
                        fetchAllInsideContract(vm.estimateContrat);
                        fetchAllOutsideContract(vm.estimateContrat);
                        fetchAllSumContract(vm.estimateContrat);
                        getCalculateFile();
                        toastr.warning("Bạn không được quyền sửa");
                	}
                	
                   
                } else {
                    toastr.warning("Bạn cần chọn một bản ghi trước");
                }
            } else {
                vm.showDetail = true;
                vm.isCreateNew = false;
            }
        }

        function showPopupAppraisalAssignment() {
            if (vm.tableEstimateListDetail.select().length > 0) {
                if (vm.item.statusCa == 2) {
                    if (vm.item.evaluateStatus == 1) {
                        toastr.warning(gettextCatalog.getString("Bản ghi đã được giao việc thẩm định!"));
                        return;
                    }

                    if (vm.item.evaluateStatus == 2) {
                        toastr.warning(gettextCatalog.getString("Bản ghi đã được đồng ý thẩm định!"));
                        return;
                    }
                    establishDetailedSettlementProposalService.getAllDetailSettlementProposal(vm.detailSettlementProposal).then(
                        function(result) {
                        	WindowService.open({
                                templateUrl: 'qlhc/establishDetailedSettlementProposalTable/AppraisalAssignment.html',
                                options: {
                                    modal: true,
                                    title: 'Giao việc thẩm định',
                                    visible: false,
                                    dataSource: result.plain(),
                                    width: '650',
                                    height: '280',
                                    actions: ["Minimize", "Maximize", "Close"],
                                    open: function() {
                                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                                    }
                                },
                            });
                        });
                } else {
                    toastr.warning(gettextCatalog.getString("Bản ghi chưa được phê duyệt!"));
                }
            } else {
                toastr.warning(gettextCatalog.getString("Chưa có bản ghi nào được chọn!"));
            }

        }

        function showPopupImportDiscountedSettlementProposal() {
        	WindowService.open({
                options: {
                    modal: true,
                    title: 'Import chiết tính đề nghị quyết toán',
                    visible: false,
                    width: '650',
                    height: '180',
                    actions: ["Minimize", "Maximize", "Close"],
                    open: function() {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: 'views/popup/establishDetailedSettlementProposal/importDiscountedSettlementProposal.html'

            });
        }

        vm.temp = {};
       

        function save() {
        	//
        	console.log(vm.item);
            vm.temp.approvalDate = vm.item.approvalDate;
            vm.temp.brepresentativeId = vm.item.brepresentativeId;
            vm.temp.code = vm.item.code;
            vm.temp.constrtAddress = vm.item.constrtAddress;
            vm.temp.constrtCode = vm.item.constrtCode;
            vm.temp.constrtName = vm.item.constrtName;
            vm.temp.constructId = vm.item.constructId;
            vm.temp.contractCode = vm.item.contractCode;
            vm.temp.contractName = vm.item.contractName;
            vm.temp.createdDate = vm.item.createdDate;
            vm.temp.createdUserId = Constant.user.srvUser.userId;
            vm.temp.detailSettlementProposalId = vm.item.detailSettlementProposalId;
            vm.temp.evaluateComments = vm.item.evaluateComments;
            vm.temp.evaluateFinishDate = vm.item.evaluateFinishDate;
            vm.temp.evaluatePersonId = vm.item.evaluatePersonId;
            vm.temp.evaluateStatus = vm.item.evaluateStatus;
            vm.temp.sendPersonId = vm.item.sendPersonId;
            vm.temp.signed_date = vm.item.signed_date;
            vm.temp.stationAddress = vm.item.stationAddress;
            vm.temp.statusCa = vm.item.statusCa;
            vm.array = [];
            if (vm.disabledPheDuyet) {
                dopheDuyet();
                vm.disabledPheDuyet = false;
            } else {
                if (!vm.showDetail) {
                    if (vm.item.statusCa == 1 || vm.item.statusCa == 2) {
                        toastr.warning("Không thể sửa bản ghi khi đang trình duyệt");
                        return;
                    }
                    if(vm.item.sendPersonId == '' || vm.item.sendPersonId === null){
                    	toastr.warning(gettextCatalog.getString("Chưa có dữ liệu người lập!"));
                    	return;
                    }
                    if(vm.item.brepresentativeId == '' || vm.item.brepresentativeId === null){
                    	toastr.warning(gettextCatalog.getString("Chưa có dữ liệu nhà thầu!"));
                    	return;
                    }
                    if (vm.isCreateNew) {
                        for (var i = 0; i < vm.contentWorkInsideContract.dataSource.data().length; i++) {
                            vm.array.push(vm.contentWorkInsideContract.dataSource.data()[i]);
                        }
                        for (var i = 0; i < vm.contentWorkOutsideContract.dataSource.data().length; i++) {
                            vm.array.push(vm.contentWorkOutsideContract.dataSource.data()[i]);
                        }
                        

                        if (vm.array.length > 0) {
                            vm.temp.listAcc = vm.array
                            establishDetailedSettlementProposalService.addAll(vm.temp).then(function() {

                                toastr.success("Thêm mới thành công");
                                fetchAllTableEstimateListDetail();
                                vm.showDetail = true;
                            }, function(e) {
                                toastr.error(gettextCatalog.getString("Lỗi khi thêm mới"));
                                return;
                            });
                        } else {
                            toastr.warning("Không thể thêm mới khi không có công việc nào được nghiệm thu!");
                        }
                    } else {
                        for (var i = 0; i < vm.contentWorkInsideContract.dataSource.data().length; i++) {
                            vm.array.push(vm.contentWorkInsideContract.dataSource.data()[i]);
                        }
                        for (var i = 0; i < vm.contentWorkOutsideContract.dataSource.data().length; i++) {
                            vm.array.push(vm.contentWorkOutsideContract.dataSource.data()[i]);
                        }
                        if(Constant.user.srvUser.userId == vm.item.createdUserId){
                        if (vm.array.length > 0) {
                            vm.temp.listAcc = vm.array
                            establishDetailedSettlementProposalService.updateAll(vm.temp).then(function() {

                                toastr.success("Cập nhật thành công");
                                fetchAllTableEstimateListDetail();
                                vm.showDetail = true;
                            }, function(e) {
                                toastr.error(gettextCatalog.getString("Lỗi khi cập nhật!"));
                                return;
                            });
                        }}else{
                    		toastr.warning("Bạn không được quyền sửa");
                    		return;
                    	}
                    }
                } else {

                }

            }
        	/**/
        }

        function remove() {
            // TODO
        	if((Constant.user.srvUser.userId != vm.item.createdUserId) && vm.tableEstimateListDetail.select().length > 0){
        		toastr.warning("Bạn không được quyền xóa!");
        	}else if (vm.tableEstimateListDetail.select().length > 0 && (Constant.user.srvUser.userId == vm.item.createdUserId)) {
                if (vm.tableEstimateListDetail.select().length > 0 && confirm('Xác nhận xóa')) {
                    establishDetailedSettlementProposalService.updateIsActive(vm.item.detailSettlementProposalId).then(function() {
                        toastr.success("Xóa thành công");
                        fetchAllTableEstimateListDetail();
                        vm.showDetail = true;
                    }, function(e) {
                        if (errResponse.status == 302) {
                        	toastr.warning("Bản ghi trình duyệt hoặc đã duyệt không được xóa!");
                        } else {
                            toastr.error("Lỗi~~~~~~~~~~~~~~~~~~~");
                        }
                    });
                }
            } else {
                toastr.warning("Không có bản ghi nào được chọn!");
            }

        }

        function chkSelectAll(item) {
            console.log('chkSelectAll');
            var grid = vm.tableEstimateListDetail;
            chkSelectAllBase(vm.chkAll, grid);
        }

        function add() {
            if (vm.showDetail) {
                vm.showDetail = false;
                vm.isCreateNew = true;
                vm.disableForm = false;

                establishDetailedSettlementProposalService.getListEmployeeByRole(vm.resultCreator).then(
                    function(d) {
                        vm.item.sendPersonName = d.plain()[0].fullName;
                        vm.item.sendPersonId = d.plain()[0].id;
                        //								vm.creators.id = (d.length > 0 ? d[0].id : "");
                    },
                    function(errResponse) {
                        console.error('Error while fetching locatorbank');
                    }
                );

                establishDetailedSettlementProposalService.getListEmployeeByRole(vm.resultRepresentingContractors).then(
                    function(d) {
                        vm.item.brepresentativeId = d.plain()[0].id;
                        vm.item.brepresentaiveName = d.plain()[0].fullName;
                    },
                    function(errResponse) {
                        console.error('Error while fetching locatorbank');
                    }
                );

                fetchAllInsideContract(vm.estimateContrat);
                fetchAllOutsideContract(vm.estimateContrat);
                fetchAllSumContract(vm.estimateContrat);
                getCalculateFile();
                vm.item = {};
                mapdata();
            } else {
                vm.showDetail = true;
            }
        }

        function undo() {
            if (!vm.showDetail) {
                vm.showDetail = true;
            }
        }

        vm.pheDuyet = function pheDuyet() {
            if (vm.tableEstimateListDetail.select().length > 0) {
                if (vm.item.statusCa == 2) {
                    toastr.warning("Bản ghi đã được phê duyệt!");
                    return;
                }
                if (vm.item.statusCa == 1) {
                    vm.disableForm = true;
                    vm.showDetail = false;
                    fetchAllInsideContract(vm.estimateContrat);
                    fetchAllOutsideContract(vm.estimateContrat);
                    fetchAllSumContract(vm.estimateContrat);
                    getCalculateFile();
                    vm.disabledPheDuyet = true;
                    vm.approDTO.statusCa = 2;
                } else {
                    toastr.error(gettextCatalog.getString("Bản ghi chưa được trình duyệt!"));
                }
            } else {
                toastr.warning("Bạn cần chọn một bản ghi trước");
            }

        }

        function dopheDuyet() {
            establishDetailedSettlementProposalService.appro(vm.approDTO).then(function(d) {
                var x = d;
                if (x == '1') {
                    toastr.warning("Chưa đến lượt duyệt");
                    return;
                }
                if (x == '2') {
                    toastr.warning("Đã duyệt rồi");
                    return;
                }
                if (x == '3') {
                    toastr.success("Duyệt thành công");
                }
                if (x == '4') {
                    toastr.success("Từ chối duyệt thành công");
                }
                if (x == '5') {
                    toastr.error("Bạn không có quyền duyệt bản ghi");
                }
                fetchAllTableEstimateListDetail();
                vm.showDetail = true;
            }, function(e) {
                toastr.error(gettextCatalog.getString("Lỗi khi phê duyệt!"));
                return;
            });
        }
        //ngoccx
      //huy trinh duyet
		vm.cancelApprovalBtn= function(){
			var grid = vm.tableEstimateListDetail;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}
			vm.item.tableName = 'DETAIL_SETTLEMENT_PROPOSAL';
			vm.item.tableId = vm.item.detailSettlementProposalId;
			vm.item.tableIdField = 'DETAIL_SETTLEMENT_PROPOSAL_ID';
			if(vm.item.statusCa == 1){
				if(vm.item.createdUserId != Constant.user.srvUser.userId){
					toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
					return;
				}else{
					if(confirm('Xác nhận hủy trình duyệt')){
				establishDetailedSettlementProposalService.cancelAprroval(vm.item).then(function() {
				fetchAllTableEstimateListDetail();
				toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
			}, function(){
			toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
			return;
		});}}
			}else{
				toastr.warning("Chỉ trình duyệt mới được hủy trình duyệt");
			}
		}
        vm.sendBrowse = sendBrowse;

        function sendBrowse() {
            if (vm.item.statusCa == 0) {
            	vm.search.constructionId = ProposalEvaluation.getItem().constructId;
                vm.search.contractCode = ProposalEvaluation.getItem().contractCode;
            	establishDetailedSettlementProposalService.exPortfull(vm.search).then(function(data) {
            		vm.theApproval.path = data.fileName;
	    			vm.theApproval.nameFile = 'BM-QT-01.pdf';
	    			goTo('THE_APPROVAL');
                }, function(e) {
                    toastr.error(gettextCatalog.getString("Lỗi !"));
                    return;
                });
                
            } else {
            	toastr.warning("Trạng thái soạn thảo mới được trình duyệt!");
            }

        }

        // Đi tới trang tiếp
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
        vm.commonSearch = { name: '', value: '' };
        vm.gridCommon = [{
            title: "Mã",
            field: "id",
            width: 120
        }, {
            title: "Tên",
            field: "fullName",
            width: 120
        }];
        vm.objTypeSearch = { id: '', fullName: '' };

        function addRequired() {
            // TODO
        }

        function setValue() {
            // TODO
        }

        function resetValue() {
            // TODO
        }

        vm.goToTD = function() {
            ProposalEvaluation.setCheckGoTo(true);
            $rootScope.$broadcast("Goto.propo.evalua");
            goTo('LIST_ESTIMATE_WORK');
        }

        $scope.$on("ProposalEvaluation.detail", function(event, item) {
            if (item != undefined) {
                vm.detailSettlementProposal.constructId = ProposalEvaluation.getItem().constructId;
                vm.detailSettlementProposal.contractCode = ProposalEvaluation.getItem().contractCode;
                fetchAllTableEstimateListDetail();
            } else {
                console.error("không nhận được dữ liệu!");
                toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
            }
        });

        function fail() {
        	var templateUrl='views/popup/popupCancelApp.html';
        	var title="Từ chối thẩm định"
        CommonService.openCustomPopup(templateUrl, title,null,vm,null,'ModalInstanceCtrl',null);
//            if (vm.item.evaluateStatus == 2) {
//                toastr.warning(gettextCatalog.getString("Bản ghi đã được đồng ý thẩm định! Không thể từ chối!"));
//                return;
//            }
//            if (vm.item.evaluateStatus == 1) {
//                if (vm.detailSettlement[0].evaluateStatus === 1 && Constant.getUser().srvUser.catEmployeeId == vm.detailSettlement[0].evaluatePersonId) {
//                    establishDetailedSettlementProposalService.fail(ProposalEvaluation.getItem().constructId).then(function() {
//                        toastr.success("Từ chối thẩm định thành công");
//                        fetchAllTableEstimateListDetail();
//                    }, function(e) {
//                        toastr.error(gettextCatalog.getString("Lỗi khi từ chối thẩm định!"));
//                    });
//                } else {
//                    toastr.warning('Bạn không phải là người được giao việc thẩm định!');
//                }
//            } else {
//                toastr.warning(gettextCatalog.getString("Chưa có bản ghi nào giao việc thẩm định!"));
//            }

        }
        vm.submit= submit;
        	function submit(data){
        		 if (vm.item.evaluateStatus == 2) {
                   toastr.warning(gettextCatalog.getString("Bản ghi đã được đồng ý thẩm định! Không thể từ chối!"));
                   return;
               }
        		 var obj={};
        		 obj.constructId=ProposalEvaluation.getItem().constructId;
        		 obj.contractCode = ProposalEvaluation.getItem().contractCode;
        		 obj.evaluateComments=data;
               if (vm.item.evaluateStatus == 1) {
                   if (vm.detailSettlement[0].evaluateStatus === 1 && Constant.getUser().srvUser.catEmployeeId == vm.detailSettlement[0].evaluatePersonId) {
                       establishDetailedSettlementProposalService.fail(obj).then(function() {
                           toastr.success("Từ chối thẩm định thành công");
                           fetchAllTableEstimateListDetail();
                       }, function(e) {
                           toastr.error(gettextCatalog.getString("Lỗi khi từ chối thẩm định!"));
                       });
                   } else {
                       toastr.warning('Bạn không phải là người được giao việc thẩm định!');
                   }
               } else {
                   toastr.warning(gettextCatalog.getString("Chưa có bản ghi nào giao việc thẩm định!"));
               }
        }
        vm.search = {};
        vm.exPortCTTDPopup = function() {
            vm.search.constructionId = ProposalEvaluation.getItem().constructId;
            vm.search.contractCode = ProposalEvaluation.getItem().contractCode;
            establishDetailedSettlementProposalService.exPortfull(vm.search).then(function(d) {
            	if(d.error){
            		 toastr.error(gettextCatalog.getString(d.error));
            		 return;
            	}
                window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                toastr.success("Export thành công");
            }, function(e) {
                toastr.error(gettextCatalog.getString("Lỗi !"));
            });
        }
        
        function fillDataTableFileCalculate(data) {
	    	vm.gridCalculateOptions = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						noRecords : true,
						editable: false,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
						           {
						        	   title : "<b>STT</b>",
						        	   field : "as",
						        	   template: dataItem => $("#calculateFileGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        	   headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
						        	   width : 40
						           },
						           {
						        	     title : "<b>Tên File</b>",
						        	     field : "as",
						        	     template :  function(dataItem) {
						        	    	 return "<a href='"+ Constant.DOWNLOAD_SERVICE + dataItem.documentPath + "'>" + dataItem.documentName + "</a>";},
						        	    	 
						        	    	 headerAttributes: {style:"text-align:center;"},
										        attributes:{style:"text-align:center;"},
						        	    	 width : 200
						        	    }]
					});
		}
        function refreshGridCal(d) {
			var grid = vm.calculateFileGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
        function getCalculateFile(){
        	inspectionService.getFileCal(vm.item.constructId).then(function(d) {
        		vm.fileCal = d.plain();
        		if(vm.fileCal!=undefined){
        			refreshGridCal(d.plain());}
        		else{
        			refreshGridCal([]);
        		}
        		
        	}, function() {
        		vm.hideFileCalculateGrid = true;
				refreshGridCal([]);
				console.error('Error while fetching object type');
			});
        }
    }
})();