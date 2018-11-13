(function() {
    'use strict';
    var controllerId = 'listController';
    angular.module('MetronicApp').controller(controllerId, listController);
    /* @ngInject */
    function listController($scope, $rootScope, $timeout, Constant,
        kendoConfig, gettextCatalog, $kWindow, $q, list_report_A_services, ProposalEvaluation, theSignCA) {
        var vm = this;
        vm.validatorOptions = kendoConfig.get('validatorOptions');
        vm.changeSite = changeSite;
        vm.goToAdd = goToAdd;
        vm.showGrid = showGrid;
        vm.isCreatNew = false;
        vm.checkCA = false;
        vm.disabledButtom = false;
        vm.checkDisable = false;
        vm.disabledButtomEx = false;
        vm.save = save;
        vm.exportFile = exportFile;
        vm.exportFileDoc = exportFileDoc;
        vm.chkSelectAll = chkSelectAll;
        vm.showDetail = false;
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
            contractName: ''
        }
        vm.load={};
        vm.item = ProposalEvaluation.getItem();
    	if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
//        initdata();
        vm.objectDTO = [];
        vm.monitoringreport = [];
        vm.technicalreport = [];
        vm.reportmonitoringA = [];
        vm.reporttechnicalA = [];
        vm.resultMonitoring = {
            constructId: vm.item.constructId,
            contractCode: vm.item.contractCode
        };
        vm.resultMonitoringA = {
            constructId: vm.item.constructId,
            contractCode: vm.item.contractCode
        };
        vm.allObject = {
            constructId: vm.item.constructId,
            contractCode: vm.item.contractCode
        }
        vm.itemID = {
            amaterialRecoveryMinutesId: '',
            adirectorId: '',
            ahandoverPersonId: '',
            bdirectorId: '',
            breceivePersonId: '',
            isActive: '',
            statusCa: '',
            code: '',
            createdDate: '',
            signPlace: '',
            amaterialRecoveryList: '',
            constructId: '',
            createdUserId: '',
        }
        vm.template = {
            amaterialRecoveryMinutesId: '',
            adirectorId: '',
            ahandoverPersonId: '',
            bdirectorId: '',
            breceivePersonId: '',
            isActive: '',
            createdUserId: '',
            statusCa: '',
            code: '',
            createdDate: '',
            constructId: '',
            signPlace: '',
            amaterialRecoveryList: ''
        }

        vm.doc = {
            amaterialRecoveryMinutesId: ''
        }
        loadDataTable();
        vm.role=[];
		var totalListEmployee = [], MonitorId = [], InChargeConstructId = [] ,reportmonitoringAID = [] , 
		reporttechnicalAID = [];
		vm.monitoringreport = [];
        vm.technicalreport = [];
        vm.reportmonitoringA = [];
        vm.reporttechnicalA = [];
        angular.element(document).ready(function () {
		list_report_A_services.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
			totalListEmployee = data.plain();
			InChargeConstructId = totalListEmployee;
			reporttechnicalAID = totalListEmployee;
			list_report_A_services.getRoleId().then(function(data) {
//				vm.role = data;
				
				for (var i = 0; i < totalListEmployee.length; i++) {
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){//2
						MonitorId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]){//3
						reportmonitoringAID.push(totalListEmployee[i]);
					}
				}
				vm.monitoringreport = MonitorId;
				vm.technicalreport = InChargeConstructId;
				vm.reportmonitoringA = reportmonitoringAID;
		        vm.reporttechnicalA = reporttechnicalAID;
				if(MonitorId.length > 0) {
					vm.item.adirectorId = MonitorId[0].userId;
				}
				if(InChargeConstructId.length > 0) {
					vm.item.bdirectorId = InChargeConstructId[0].userId;
				}
				if(reportmonitoringAID.length > 0) {
					vm.item.ahandoverPersonId = reportmonitoringAID[0].userId;
				}
				if(reporttechnicalAID.length > 0) {
					vm.item.breceivePersonId = reporttechnicalAID[0].userId;
				}
				
			})
			.catch(function() {
				console.error('getRoleId error');
			});
		  })
		  .catch(function() {
		    console.error('Gists error');
		});
        });
//        list_report_A_services.getRoleId().then(function(data) {
//            vm.role = data;
//            vm.resultMonitoring.roleid = vm.role[2];
//            vm.resultMonitoringA.roleid = vm.role[3];
//            initdata();
//        }).catch(function(data, status) {
//            console.error('getRoleId error', response.status, response.data);
//        });
//
//        function initdata() {
//            list_report_A_services.getListEmployeeByRole(vm.resultMonitoring).then(function(data) {
//                if (data.plain().length > 0) {
//                    vm.monitoringreport = data.plain();
//                } else {
//                    vm.monitoringreport = [];
//                }
//            });
//
//            list_report_A_services.getListEmployeeByRole(vm.resultMonitoringA).then(function(data) {
//                if (data.plain().length > 0) {
//                    vm.reportmonitoringA = data.plain();
//                } else {
//                    vm.reportmonitoringA = [];
//                }
//            });
//
//            list_report_A_services.getAllListEmployeeByRole(vm.allObject).then(function(data) {
//                if (data.plain().length > 0) {
//                    vm.technicalreport = data.plain();
//                } else {
//                    vm.technicalreport = [];
//                }
//            });
//
//            list_report_A_services.getAllListEmployeeByRole(vm.allObject).then(function(data) {
//                if (data.plain().length > 0) {
//                    vm.reporttechnicalA = data.plain();
//                } else {
//                    vm.reporttechnicalA = [];
//                }
//            });
////            vm.item.adirectorId = vm.monitoringreport[0].id;
////            vm.item.bdirectorId = vm.technicalreport[0].id;
////            vm.item.ahandoverPersonId = vm.reportmonitoringA[0].id;
////            vm.item.breceivePersonId = vm.reporttechnicalA[0].id;
//        }
        vm.handleCheck = function(item) {
            if (document.getElementById("checkalllist2").checked == true) {
                document.getElementById("checkalllist2").checked = false;
            }
        }

        function fillDataTable(data) {
            var deferred = $q.defer();
            vm.gridOptions = kendoConfig
                .getGridOptions({
                    autoBind: true,
                    dataSource: data,
                    change: onChange,
                    noRecords: true,
                    editable: false,
                    messages: {
                        noRecords: gettextCatalog.getString("Không có kết quả nào")
                    },
                    columns: [{
                            field: "STT",
                            title: "STT",
                            template: dataItem => $("#reportGridVT").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:center;" },
                            width: 80
                        },
                        {
                            title: "<input type='checkbox' id='checkalllist2' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                            template: "<input type='checkbox' ng-click='vm.handleCheck();' name='gridcheckbox' />",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:center;" },
                            width: 50
                        },
                        {
                            title: gettextCatalog.getString("Mã biên bản"),
                            field: "code",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 200
                        },
                        {
                            title: gettextCatalog
                                .getString("Mã công trình"),
                            field: "constrtCode",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 200
                        },
                        {
                            title: gettextCatalog
                                .getString("Mã hợp đồng"),
                            field: "contractCode",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 200
                        },
                        {
                            title: gettextCatalog
                                .getString("Tên hợp đồng"),
                            field: "contractName",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 200
                        },
                        {
                            title: gettextCatalog
                                .getString("Trạng thái"),
                            field: "statusCa",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" , class:"statusColumn"},
                            template: function($scope) {
                                if ($scope.statusCa == 0) {
                                    return "Soạn Thảo";
                                }
                                if ($scope.statusCa == 1) {
                                    return "Trình Ký";
                                }
                                if ($scope.statusCa == 2) {
                                    return "Đã Ký";
                                }
                                if ($scope.statusCa == 3) {
                                    return "Từ chối Ký";
                                }
                            },
                            width: 120
                        },
                    ]
                });
            deferred.resolve('done');
            return deferred.promise;
        }

        function loadDataTable() {
        	vm.load.constructId = vm.item.constructId;
        	vm.load.contractId = vm.item.contractId;
            list_report_A_services.findByConstructId(vm.load).then(function(d) {
                if (d.plain().length > 0) {
                    fillDataTable(d.plain());
                    refreshGrid(d.plain());
                } else {
                    fillDataTable(d.plain());
                    refreshGrid(d.plain());
                    // toastr.warning(gettextCatalog.getString("Dữ liệu trống!"));
                }
            }, function() {
                toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu!"));
            });
        }

        function refreshGrid(d) {
            var grid = vm.reportGrid;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        function chkSelectAll(item) {
            var grid = vm.reportGrid;
            chkSelectAllBase(vm.chkAll, grid);
        }
        // ///////////// thông tin chung////////////////
        $scope.$on("ProposalEvaluation.detail", function(event, item) {
            if (item != undefined) {
                vm.item = item;
                vm.template.constructId = vm.item.constructId;
                loadDataTable();
            } else {
                toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
            }
        });


        function changeSite() {
            list_report_A_services.goTo();
        }

        vm.theSign = {
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

        // //////Remove///////////
        function onChange() {
            if (vm.reportGrid.select().length > 0) {
                var tr = vm.reportGrid.select().closest("tr");
                var dataItem = vm.reportGrid.dataItem(tr);
                vm.itemID = dataItem;
                vm.itemID.amaterialRecoveryMinutesId = dataItem.amaterialRecoveryMinutesId;

                vm.theSign.code = vm.itemID.code;
                vm.theSign.constructId = vm.item.constructId;
                vm.theSign.constrCompReMapId = vm.itemID.constrCompReMapId;
                vm.theSign.stringEmployee = dataItem.breceivePersonId + "," + dataItem.bdirectorId + "," + dataItem.ahandoverPersonId + "," + dataItem.adirectorId;
                vm.theSign.roleId = ["0", "0", vm.resultMonitoringA.roleid, vm.resultMonitoring.roleid];
                vm.theSign.roleName = ["Người giao", "người nhận", "GĐ. Thi công", "GĐ. Chủ đầu tư"];
                vm.theSign.isSign = 'theSignCA';
            }
        }

        function getDataRowSelect() {
            var demopromise = $q.defer();
            var grid = vm.reportGrid;
            // Check select
            if (grid.select() == null || grid.select().length == 0) {
                toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
                return;
            }
            var tr = grid.select().closest("tr"); // output card tr
            var dataItem = grid.dataItem(tr); // data card tr
            demopromise.resolve(dataItem);
            return demopromise.promise;
        }

        vm.multiDelete = function() {
            var selectedRow = [];
            var grid = vm.reportGrid;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);
                if (checkbox.is(':checked')) {
                    // Push id into selectedRow
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    if ((dataItem.statusCa == 0 || dataItem.statusCa == 3) &&
                        dataItem.createdUserId == Constant.user.srvUser.userId) {
                        selectedRow.push(dataItem.amaterialRecoveryMinutesId);
                    }
                }
            });

            if (selectedRow.length == 0) {
                toastr.warning("Phải tích chọn bản ghi!");
                return;
            }
            if (confirm('Xác nhận xóa')) {
                list_report_A_services.deleteAMaterialMinutes(selectedRow).then(function() {
                    toastr.success("Xóa bản ghi thành công");
                    loadDataTable();
                }, function(errResponse) {
                    if (errResponse.status == 302) {
                        toastr.error("Bản ghi đang được sử dụng");
                    } else {
                        toastr.error(message.deleteError);
                    }
                });
            }
        }

        var originRecoveryQuantity;
        var checkQuantity;
        var check1;
        var check2;
        function fillDataTableA(data) {
            var deferred = $q.defer();
            if (data && data.length > 0 && !data[0].qualityStatusNew) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].qualityStatus == 0) {
                        data[i].qualityStatusNew = { id: "0", value: "Tốt" }
                    } else {
                        data[i].qualityStatusNew = { id: "1", value: "Hỏng" }
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
                            qualityStatusNew: { defaultValue: { id: 0, value: "Tốt" } },
                            recoveryQuantity: {
                                validation: {
                                    required: true,
                                    recoveryValidation: function(input) {
                                        if (vm.isCreatNew == true) {
                                            originRecoveryQuantity = input.val();
                                            var row = input.closest("tr");
                                            var grid = row.closest("[data-role=grid]").data("kendoGrid");
                                            var dataItem = grid.dataItem(row);
                                            if (input.val() > dataItem.originRecoveryQuantity) {
                                            	check2 = dataItem.originRecoveryQuantity;
                                                input.attr("data-recoveryValidation-msg", "Giá trị không được lớn hơn " + dataItem.originRecoveryQuantity + " " + dataItem.unitName);
                                                return false;
                                            } else if (input.val() < 0) {
                                                input.attr("data-recoveryValidation-msg", "Giá trị không được nhỏ hơn 0");
                                                return false;
                                            }
                                            return true;
                                        } else {
                                            checkQuantity = input.val();
                                            var row = input.closest("tr");
                                            var grid = row.closest("[data-role=grid]").data("kendoGrid");
                                            var dataItem = grid.dataItem(row);

                                            if (input.val() > dataItem.checkQuantity) {
                                            	check1 = dataItem.checkQuantity;
                                                input.attr("data-recoveryValidation-msg", "Giá trị không được lớn hơn " + dataItem.checkQuantity + " " + dataItem.unitName);
                                                return false;
                                            } else if (input.val() < 0) {
                                                input.attr("data-recoveryValidation-msg", "Giá trị không được nhỏ hơn 0");
                                                return false;
                                            }
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
            
            vm.gridOptionsA = kendoConfig
                .getGridOptions({
                    autoBind: true,
                    dataSource: dataSource,
                    change: onChangeA,
                    noRecords: true,
                    messages: {
                        noRecords: gettextCatalog
                            .getString("Không có kết quả nào")
                    },
                	edit: function(e) {
   			         e.container.find("input[name=merName]").attr("maxlength", 500);
   			         e.container.find("input[name=serialNumber]").attr("maxlength", 200);
   			         e.container.find("input[name=unitName]").attr("maxlength", 100);
   			         e.container.find("input[name=handoverQuantity]").attr("maxlength", 34);
   			         e.container.find("input[name=acceptQuantity]").attr("maxlength", 34);
			         e.container.find("input[name=recoveryQuantity]").attr("maxlength", 34);
			         e.container.find("input[name=originRecoveryQuantity]").attr("maxlength", 100);			    	        
			         e.container.find("input[name=comments]").attr("maxlength", 500);
                	},
                    columns: [{
                            field: "STT",
                            title: "STT",
                            template: dataItem => $("#reportGridA").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                            editor: nonEditor,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:center; background-color : Gainsboro;" },
                            width: 80
                        },
                        {
                            title: gettextCatalog.getString("Tên VT TB"),
                            field: "merName",
                            editor: nonEditor,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left; background-color : Gainsboro;" },
                            width: 200
                        },
                        {
                            title: gettextCatalog.getString("Serial number"),
                            field: "serialNumber",
                            editor: nonEditor,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left; background-color : Gainsboro;" },
                            width: 150
                        },
                        {
                            title: gettextCatalog.getString("Đơn vị tính"),
                            field: "unitName",
                            editor: nonEditor,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;background-color : Gainsboro;" },
                            width: 120
                        },
                        {
                            title: gettextCatalog.getString("Số lượng bàn giao"),
                            field: "handoverQuantity",
                            editor: nonEditor,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;background-color : Gainsboro;" },
                            width: 160
                        },
                        {
                            title: gettextCatalog.getString("Số lượng nghiệm thu"),
                            field: "acceptQuantity",
                            editor: nonEditor,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;background-color : Gainsboro;" },
                            width: 170
                        },
                        {
                            title: gettextCatalog.getString("Số lượng thu hồi"),
                            field: "recoveryQuantity",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;" },
                            template: function(dataItem) {
                                if ($.isNumeric(dataItem.recoveryQuantity)) {
                                    dataItem.recoveryQuantity = parseFloat(Number(dataItem.recoveryQuantity).toFixed(3));
                                    return parseFloat(Number(dataItem.recoveryQuantity).toFixed(3));
                                } else {
                                    dataItem.recoveryQuantity = 0;
                                    return 0;
                                }
                            },
                            format: "{0:n3}",
                            decimals: 3,
                            width: 150
                        },
                        {
                            title: gettextCatalog.getString("Số lượng thu hồi"),
                            field: "originRecoveryQuantity",
                            template: function(dataItem) {
                                if ($.isNumeric(dataItem.originRecoveryQuantity)) {
                                    dataItem.originRecoveryQuantity = parseFloat(Number(dataItem.originRecoveryQuantity).toFixed(3));
                                    return parseFloat(Number(dataItem.originRecoveryQuantity).toFixed(3));
                                } else {
                                    dataItem.originRecoveryQuantity
                                    return 0;
                                }
                            },
                            format: "{0:n3}",
                            decimals: 3,
                            hidden: true,
                            width: 200
                        },
                        {
                            title: gettextCatalog.getString("check Số lượng thu hồi"),
                            field: "checkQuantity",
                            template: function(dataItem) {
                                if ($.isNumeric(dataItem.checkQuantity)) {
                                    dataItem.checkQuantity = parseFloat(Number(dataItem.checkQuantity).toFixed(3));
                                    return parseFloat(Number(dataItem.checkQuantity).toFixed(3));
                                } else {
                                    dataItem.checkQuantity = 0;
                                    return 0;
                                }
                            },
                            format: "{0:n3}",
                            decimals: 3,
                            hidden: true,
                            width: 200
                        },
                        {
                            title: gettextCatalog.getString("Hiện Trạng TB"),
                            field: "qualityStatusNew",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            editor: categoryDropDownEditor,
                            template: "#=qualityStatusNew.value#",
                            width: 150
                        },
                        {
                            title: gettextCatalog.getString("Ghi chú"),
                            field: "comments",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 150
                        }
                    ]
                });
            deferred.resolve(data);
            return deferred.promise;
        }

        function nonEditor(container, options) {
            container.text(options.model[options.field]);
        }

        function categoryDropDownEditor(container, options) {
            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "value",
                    dataValueField: "id",
                    dataSource: [{ id: 1, value: "Hỏng" }, { id: 0, value: "Tốt" }]
                });
        }

        function loadDataTableA() {
            if (vm.isCreatNew == true) {
                list_report_A_services.getTwoList(vm.item.constructId).then(function(d) {
                    var hihi = [];
                    hihi = d.plain();
                    if (hihi.length == 0 && vm.showDetail == true) {
                        toastr.warning(gettextCatalog.getString("Dữ liệu vật tư thiết bị trống!"));
                        fillDataTableA([])
                        refreshGridA([]);
                        return;
                    }
                    fillDataTableA(d.plain()).then(function(s) {
                        refreshGridA(s);
                    });

                }, function() {
                    toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu!"));
                });
            } else {
                list_report_A_services.updateRecoveryList(vm.itemID.amaterialRecoveryMinutesId).then(function(d) {
                    fillDataTableA(d.plain()).then(function(s) {

                        refreshGridA(s);
                    });
                }, function() {
                    toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu!"));
                });
            }

        }

        function refreshGridA(d) {
            var grid = vm.reportGridA;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        function getDataRowSelectA() {
            var demopromise = $q.defer();
            var grid = vm.reportGridA;
            // Check select
            if (grid.select() == null || grid.select().length == 0) {
                toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
                return;
            }
            var tr = grid.select().closest("tr"); // output card tr
            var dataItem = grid.dataItem(tr); // data card tr
            demopromise.resolve(dataItem);
            return demopromise.promise;
        }

        function onChangeA() {
            if (vm.reportGridA.select().length > 0) {
                var tr = vm.reportGridA.select().closest("tr");
                var dataItem = vm.reportGridA.dataItem(tr);
                vm.itemIDA = dataItem;
            }
        }

        function goToAdd() {
            vm.isCreatNew = true;
            vm.showDetail = true;
            vm.checkCA = true;
            vm.disabledButtomEx = true;
            vm.itemID.constructId = vm.item.constructId;
            list_report_A_services.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
    			totalListEmployee = data.plain();
    			InChargeConstructId.push(totalListEmployee);
    			reporttechnicalAID.push(totalListEmployee);
    			list_report_A_services.getRoleId().then(function(data) {
    				//vm.role = data;
    				for (var i = 0; i < totalListEmployee.length; i++) {
    					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){//2
    						MonitorId.push(totalListEmployee[i]);
    					}
    					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]){//3
    						reportmonitoringAID.push(totalListEmployee[i]);
    					}
    				}
    				vm.monitoringreport = MonitorId;
    				vm.technicalreport = InChargeConstructId;
    				vm.reportmonitoringA = reportmonitoringAID;
    		        vm.reporttechnicalA = reporttechnicalAID;
    				if(MonitorId.length > 0) {
    					vm.item.adirectorId = MonitorId[0].userId;
    				}
    				if(InChargeConstructId.length > 0) {
    					vm.item.bdirectorId = InChargeConstructId[0].userId;
    				}
    				if(reportmonitoringAID.length > 0) {
    					vm.item.ahandoverPersonId = reportmonitoringAID[0].userId;
    				}
    				if(reporttechnicalAID.length > 0) {
    					vm.item.breceivePersonId = reporttechnicalAID[0].userId;
    				}
    				
    			})
    			.catch(function() {
    				console.error('getRoleId error');
    			});
    		  })
    		  .catch(function() {
    		    console.error('Gists error');
    		});
            for (var i = 0; i < vm.monitoringreport.length; i++) {
				if(vm.monitoringreport[i].isCurrent === 1){
					vm.item.adirectorId = vm.monitoringreport[i].userId;
					break;
				} else {
					vm.item.adirectorId = vm.monitoringreport[0].userId;
					break;
				}
			}
			for (var j = 0; j < vm.technicalreport.length; j++) {
				if(vm.technicalreport[j].isCurrent === 1){
					vm.item.bdirectorId = vm.technicalreport[j].userId;
					break;
				} else {
					vm.item.bdirectorId = vm.technicalreport[0].userId;
					break;
				}
			}
			for (var i = 0; i < vm.reportmonitoringA.length; i++) {
				if(vm.monitoringreport[i].isCurrent === 1){
					vm.item.ahandoverPersonId = vm.reportmonitoringA[i].userId;
					break;
				} else {
					vm.item.ahandoverPersonId = vm.reportmonitoringA[0].userId;
					break;
				}
			}
			for (var j = 0; j < vm.reporttechnicalA.length; j++) {
				if(vm.reporttechnicalA[j].isCurrent === 1){
					vm.item.breceivePersonId = vm.reporttechnicalA[j].userId;
					break;
				} else {
					vm.item.breceivePersonId = vm.reporttechnicalA[0].userId;
					break;
				}
			}
//            if (vm.monitoringreport.length > 0) {
//                vm.item.adirectorId = vm.monitoringreport[0].id;
//            } else {
//            	initdata();
//            	if (vm.monitoringreport.length > 0) {
//                    vm.item.adirectorId = vm.monitoringreport[0].id;
//                } else {
//                    toastr.error("Dữ liệu giám sát chủ đầu tư trống");
//                }
//            }
//            if (vm.technicalreport.length > 0) {
//                vm.item.bdirectorId = vm.technicalreport[0].id;
//            } else {
//            	initdata();
//            	if (vm.technicalreport.length > 0) {
//                    vm.item.bdirectorId = vm.technicalreport[0].id;
//                } else {
//                    toastr.error("Dữ liệu người nhận trống");
//                }
//            }
//            if (vm.reportmonitoringA.length > 0) {
//                vm.item.ahandoverPersonId = vm.reportmonitoringA[0].id;
//            } else {
//            	initdata();
//            	if (vm.reportmonitoringA.length > 0) {
//                    vm.item.ahandoverPersonId = vm.reportmonitoringA[0].id;
//                } else {
//                    toastr.error("Dữ liệu giám đốc đơn vị thi công trống");
//                }
//            }
//            if (vm.reporttechnicalA.length > 0) {
//                vm.item.breceivePersonId = vm.reporttechnicalA[0].id;
//            } else {
//            	initdata();
//            	if (vm.reporttechnicalA.length > 0) {
//                    vm.item.breceivePersonId = vm.reporttechnicalA[0].id;
//                } else {
//                    toastr.error("Dữ liệu người giao trống");
//                }
//            }
            vm.item.signPlace = '';
            loadDataTableA();
        }

        function showGrid() {
            vm.isCreatNew = false;
            vm.disabledButtomEx = false;
            if (vm.showDetail == false) {
                if (vm.reportGrid.select().length > 0) {
                    if ((vm.itemID.statusCa == 0 || vm.itemID.statusCa == 3) &&
                        Constant.user.srvUser.userId == vm.itemID.createdUserId) {
                        vm.showDetail = true;
                        vm.checkCA = false;
                        vm.item.adirectorId = vm.itemID.adirectorId;
                        vm.item.bdirectorId = vm.itemID.bdirectorId;
                        vm.item.ahandoverPersonId = vm.itemID.ahandoverPersonId;
                        vm.item.breceivePersonId = vm.itemID.breceivePersonId;
                        vm.item.signPlace = vm.itemID.signPlace;
                        loadDataTableA();
                    } else {
                        vm.disabledButtom = true;
                        vm.showDetail = true;
                        vm.checkCA = false;
                        vm.checkDisable = true;
                        toastr.warning(gettextCatalog.getString("Bạn không được quyền sửa!"));
                        vm.item.adirectorId = vm.itemID.adirectorId;
                        vm.item.bdirectorId = vm.itemID.bdirectorId;
                        vm.item.ahandoverPersonId = vm.itemID.ahandoverPersonId;
                        vm.item.breceivePersonId = vm.itemID.breceivePersonId;
                        vm.item.signPlace = vm.itemID.signPlace;
                        loadDataTableA();
                    }

                } else {
                    toastr.error(gettextCatalog.getString("Cần chọn bản ghi trước khi thực hiện!"));
                    return;
                }
            } else {
                vm.showDetail = false;
                vm.checkCA = false;
                vm.checkDisable = false;
                vm.disabledButtom = false;
            }
        }

        function save() {
            vm.template.constructId = vm.item.constructId;
            vm.template.adirectorId = vm.item.adirectorId;
            vm.template.bdirectorId = vm.item.bdirectorId;
            vm.template.ahandoverPersonId = vm.item.ahandoverPersonId;
            vm.template.breceivePersonId = vm.item.breceivePersonId;
            if (vm.showDetail == false) {
                toastr.error(gettextCatalog.getString("Không có bản ghi!"));
                return;
            } else {
                if (vm.isCreatNew == true) {
                    var data = vm.reportGridA.dataSource.data();
                    if (data.length < 1) {
                        toastr.error(gettextCatalog.getString("Không có bản ghi!"));
                        return;
                    } else {
                        if(originRecoveryQuantity < 0){
                        	return;
                        }
                        if(originRecoveryQuantity > check2){
                        	return;
                        }
                        var objectDTO = [];
                        objectDTO = data;
                        vm.template.amaterialRecoveryList = objectDTO;
                        vm.template.signPlace = vm.item.signPlace;
                        vm.template.createdUserId = Constant.user.srvUser.userId;
                        for (var i = 0; i < objectDTO.length; i++) {
                            if (objectDTO[i].qualityStatusNew.id == 0) {
                                vm.template.amaterialRecoveryList[i].qualityStatus = 0;
                            } else if (objectDTO[i].qualityStatusNew.id == 1) {
                                vm.template.amaterialRecoveryList[i].qualityStatus = 1;
                            }
                        }
                        if (data != null) {
                            if (vm.item.signPlace == "" || vm.item.signPlace == null) {
                                toastr.error(gettextCatalog.getString("Chưa có địa chỉ của biên bản!"));
                                return;
                            } else {
                                list_report_A_services.addAMaterialRecoveryMinutes(vm.template).then(function(d) {
                                    toastr.success("Thêm mới thành công");
                                    vm.item.adirectorId = vm.monitoringreport[0].id;
                                    vm.item.bdirectorId = vm.technicalreport[0].id;
                                    vm.item.ahandoverPersonId = vm.reportmonitoringA[0].id;
                                    vm.item.breceivePersonId = vm.reporttechnicalA[0].id;
                                    loadDataTableA();
                                    loadDataTable();
                                    vm.showDetail = false;
                                    vm.checkCA = false;
                                    vm.disabledButtomEx = false;
                                }, function() {
                                    toastr.error(gettextCatalog.getString("Vật tư thiết bị công trình đã được thu hồi!"));
                                    return;
                                });
                            }
                        } else {
                            toastr.error(gettextCatalog.getString("Không có dữ liệu thu hồi!"));
                        }
                    }

                } else {
                    if (vm.item.signPlace == "" || vm.item.signPlace == null) {
                        toastr.error(gettextCatalog.getString("Chưa có địa chỉ của biên bản!"));
                        return;
                    }
                    if(checkQuantity < 0){
                    	return;
                    }
                    
                    if(checkQuantity > check1){
                    	return;
                    }
                    vm.template.amaterialRecoveryMinutesId = vm.itemID.amaterialRecoveryMinutesId;
                    vm.template.code = vm.itemID.code;
                    vm.template.isActive = vm.itemID.isActive;
                    vm.template.signPlace = vm.item.signPlace;
                    vm.template.statusCa = vm.itemID.statusCa;
                    vm.template.createdDate = vm.itemID.createdDate;
                    vm.template.createdUserId = vm.itemID.createdUserId;
                    var data = vm.reportGridA.dataSource.data();
                    var objectDTO = [];
                    objectDTO = data;
                    vm.template.amaterialRecoveryList = objectDTO;
                    for (var i = 0; i < objectDTO.length; i++) {
                        if (objectDTO[i].qualityStatusNew.id == 0) {
                            vm.template.amaterialRecoveryList[i].qualityStatus = 0;
                        } else if (objectDTO[i].qualityStatusNew.id == 1) {
                            vm.template.amaterialRecoveryList[i].qualityStatus = 1;
                        }
                        delete objectDTO[i].defaultSortField;
                        delete objectDTO[i].fwmodelId;
                        delete objectDTO[i].qualityStatusNew;
                    }
                    if (data != null) {
                        list_report_A_services.updateAMaterialRecoveryMinutes(vm.template).then(function() {
                            toastr.success("Sửa thành công");
                            vm.itemID.signPlace;
                            loadDataTableA();
                            vm.showDetail = false;
                            loadDataTable();
                        }, function() {
                            toastr.error(gettextCatalog.getString("Lỗi khi sửa!"));
                            return;
                        });
                    } else {
                        toastr.error(gettextCatalog.getString("Không có dữ liệu thu hồi!"));
                        return;
                    }
                }
            }
        }
        // export file PDF
        function exportFile() {
            if (vm.showDetail == false) {
                var selectedRow = [];
                var grid = vm.reportGrid;
                //vm.reportGrid.select("tr:eq(1)");
                grid.table.find("tr").each(function(idx, item) {
                    var row = $(item);
                    var checkbox = $('[name="gridcheckbox"]', row);

                    if (checkbox.is(':checked')) {
                        // Push id into selectedRow
                        var tr = grid.select().closest("tr");
                        var dataItem = grid.dataItem(item);
                        selectedRow.push(dataItem.amaterialRecoveryMinutesId);
                    }
                });
                if (selectedRow.length == 0 && vm.itemID.amaterialRecoveryMinutesId != null) {
                    $('#loading').show();
                    vm.doc.amaterialRecoveryMinutesId = vm.itemID.amaterialRecoveryMinutesId;
                    vm.doc.contractId = vm.item.contractId;
                    list_report_A_services.exportFileM(vm.doc).then(function(data) {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                        toastr.success("Export thành công");
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });

                } else if (selectedRow.length > 0) {
                    $('#loading').show();
                    list_report_A_services.exportList(selectedRow).then(function(data) {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                        toastr.success("Export danh sách thành công");
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });

                } else {
                    toastr.error(gettextCatalog.getString("Bạn cần chọn bản ghi để export!"));
                    return;
                }
            } else if (vm.isCreatNew == true && vm.showDetail == true) {
                toastr.error(gettextCatalog.getString("Không có bản ghi export!"));
                return;
            } else if (vm.showDetail == true && vm.itemID != null) {
                vm.doc.amaterialRecoveryMinutesId = vm.itemID.amaterialRecoveryMinutesId;
                $('#loading').show();
                list_report_A_services.exportFileM(vm.doc).then(function(data) {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    toastr.success("Export thành công");
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });

            }
        }
        // / export doc///
        function exportFileDoc() {
            if (vm.showDetail == false) {
                var selectedRow = [];
                var grid = vm.reportGrid;
                //vm.reportGrid.select("tr:eq(1)");
                grid.table.find("tr").each(function(idx, item) {
                    var row = $(item);
                    var checkbox = $('[name="gridcheckbox"]', row);
                    if (checkbox.is(':checked')) {
                        // Push id into selectedRow
                        var tr = grid.select().closest("tr");
                        var dataItem = grid.dataItem(item);
                        selectedRow.push(dataItem.amaterialRecoveryMinutesId);
                    }
                });
                if (selectedRow.length == 0 && vm.itemID.amaterialRecoveryMinutesId != null) {
                    $('#loading').show();
                    vm.doc.amaterialRecoveryMinutesId = vm.itemID.amaterialRecoveryMinutesId;
                    vm.doc.contractId = vm.item.contractId;
                    list_report_A_services.exportFileMDoc(vm.doc).then(function(data) {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                        toastr.success("Export thành công");
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });
                } else if (selectedRow.length > 0) {
                    $('#loading').show();
                    list_report_A_services.exportListDoc(selectedRow).then(function(data) {
                        window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                        toastr.success("export danh sach thành công");
                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });

                } else {
                    toastr.error(gettextCatalog.getString("Bạn cần chọn bản ghi để export!"));
                    return;
                }
            } else if (vm.isCreatNew == true && vm.showDetail == true) {
                toastr.error(gettextCatalog.getString("không có bản ghi export!"));
                return;
            } else if (vm.showDetail == true && vm.itemID != null) {
                $('#loading').show();
                vm.doc.amaterialRecoveryMinutesId = vm.itemID.amaterialRecoveryMinutesId;
                list_report_A_services.exportFileMDoc(vm.doc).then(function(data) {
                    window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                    toastr.success("Export thành công");
                }).catch(function() {
                    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                    return;
                }).finally(function() {
                    $('#loading').hide();
                });
            }
        }
        // /CA///
        vm.signCA = function signCA() {
            vm.doc.amaterialRecoveryMinutesId = vm.itemID.amaterialRecoveryMinutesId;
            if (vm.doc != null && vm.doc.amaterialRecoveryMinutesId != null && vm.doc.amaterialRecoveryMinutesId != '') {
                if (vm.itemID.statusCa == 0) {
                    $('#loading').show();
                    list_report_A_services.exportFileM(vm.doc).then(function(data) {
                        vm.theSign.path = data.fileName;
                        vm.theSign.nameFile = 'BM-TCNT-13.pdf';

                        theSignCA.setItem(vm.theSign);
                        goTo('THE_SIGN_CA');

                    }).catch(function() {
                        toastr.error(gettextCatalog.getString("Lỗi export"));
                        return;
                    }).finally(function() {
                        $('#loading').hide();
                    });

                } else {
                    toastr.warning(gettextCatalog.getString("Trạng thái soạn thảo mới được ký CA "));
                    return;
                }

            } else {
                toastr.warning(gettextCatalog.getString("Chưa chọn bản ghi nào!"));
                return;
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
            }
        }

        $scope.$on("ChangeStatus", function(event, result) {
            if (result) {
                loadDataTable();
            }
        });
    }
})();