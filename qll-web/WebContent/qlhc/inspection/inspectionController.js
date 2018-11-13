(function() {
    'use strict';
    var controllerId = 'ConstructionAcceptanceController';
    angular.module('MetronicApp').controller(controllerId,
        ConstructionAcceptanceController);

    function ConstructionAcceptanceController($scope, $rootScope, $timeout, Constant,
        gettextCatalog, kendoConfig, $kWindow, WindowService,
        CommonService, PopupConst, inspectionService, ProposalEvaluation, theSignCA, Restangular,
        RestEndpoint) {
        var vm = this;
        fillDataTableWorkItem([]);
        fillDataTable2([]);
        fillDataTable3([]);
        fillDataTable4([]);
        vm.hideFileCalculate = false;
        vm.hideFileCalculateGrid = false;
        vm.goToDsNtht = goToDsNtht;
        var list1 =[];
        vm.item1={};
        function goToDsNtht() {
            inspectionService.goTo_DSNTHT();
        }

       
        var ArrEmployee = [];
        var WorkItems = [];
        var MerLists = [];
        vm.item = {
            constructId: 69164445,
            amonitorId: 0,
            amonitorName: '',
            ainChargeMonitorId: 0,
            ainChargeMonitorName: '',
            bdirectorId: 0,
            bdirectorName: '',
            binChargeConstructId: 0,
            binChargeConstructName: '',
            cdesignDirectionId: 0,
            cdesignDirectionName: '',
            cdesignManagerId: 0,
            cdesignManagerName: '',
            acceptFromDate: '',
            acceptToDate: '',
            acceptPlace: '',
            applyBenchmark: '',
            otherDocuments: '',
            constructionQuality: '',
            otherComments: '',
            conclusion: 0,
            completeRequest: '',
            statusCa: '0',
            type: '',
            incompleteReason: '',
            constracceptworklist: [],
            constracceptmerlist: [],
            signPlace: '  '

        };

        vm.employee = {
        }
        vm.theSign = {}

        vm.buttonInactive = {
                all: false
            }
            // Hien thi popup tim kiem
        vm.popupTenCT = popupTenCT;
        vm.folder = '';

        function popupTenCT() {
            var dataConfig = {
                selected: function(data) {
                    $scope.object.adOrgId = data.id;
                    $scope.objectview.adOrgIdTextView = data.text;
                }
            };
            WindowService.openUnitsForm(dataConfig);
        }
        var message = {
            noDataGrid: gettextCatalog.getString("Không có dữ liệu trên trang hiện tại"),
            lineRequired: gettextCatalog.getString("Bạn cần chọn một bản ghi trước"),
            recordRequired: gettextCatalog.getString("Bạn cần chọn một bản ghi trước"),
            deleteSuccess: gettextCatalog.getString("Xóa thành công!"),
            deleteError: gettextCatalog.getString("Có lỗi xảy ra trong quá trình xóa!"),
            saveSuccess: gettextCatalog.getString("Lưu thành công!"),
            duplicateCode: gettextCatalog.getString("Mã của công trình đã tồn tại!"),
            createError: gettextCatalog.getString("Lỗi xuất hiện khi tạo  công trình!"),
            updateError: gettextCatalog.getString("Lỗi xuất hiện khi cập nhật  công trình!"),
            needShowDetail: gettextCatalog.getString("Cần hiển thị ở chế độ Chi Tiết!"),
            positionLast: gettextCatalog.getString("Đã ở bản ghi cuối"),
            positionFirst: gettextCatalog.getString("Đã ở bản ghi đầu"),
        };
        // trong hop dong
        var IContract = [];
        // ngoai hop dong
        var OContract = [];


        vm.isCreateNew = false;
        vm.showDetail = false;
        vm.showSearch = false;
        vm.add = add;
        // vm.edit = edit;
        vm.showGrid = showGrid;
        // vm.search = search;
        vm.save = save;
        vm.remove = remove;
        vm.detail = detail;
        // vm.onSave = onSave;
        // vm.doSearch = doSearch;
        // vm.copy=copy;
        vm.validatorOptions = kendoConfig.get('validatorOptions');
        // vm.chkSelectAll = chkSelectAll;
        // vm.multiDelete = multiDelete;
        // vm.candoSearch = candoSearch;









        function getFolder() {
        	inspectionService.getConstructionAcceptanceFolder().then(function(data) {
				vm.folder = data.folder;
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
			  .finally(function() {
			  });
		}



        // Khoi tao du lieu cho form
        initFormData();

        function initFormData() {
            try {
                vm.item.userid = Constant.user.srvUser.userId;
            } catch (err) {
                toastr.error("Có gì đó không đúng xảy ra!");
            };
            if (ProposalEvaluation.getItem() != null) {

                vm.item.constructId = ProposalEvaluation.getItem().constructId;
                vm.item.constrtCode = ProposalEvaluation.getItem().constrtCode;
                vm.item.constrtName = ProposalEvaluation.getItem().constrtName;
                vm.item.contractCode = ProposalEvaluation.getItem().contractCode;
                vm.item.contractName = ProposalEvaluation.getItem().contractName;
                vm.item.constrtAddress = ProposalEvaluation.getItem().constrtAddress;
                vm.item.stationCode = ProposalEvaluation.getItem().stationCode;

                vm.item.contractId = ProposalEvaluation.getItem().contractId;

            }
            getListEmployee(vm.item.constructId);





            //inspectionService.getCompletionDrawing(vm.item).then(function (d){ 
            //	  vm.typeId = d.plain();
            // }, function(errResponse){ 
            //       console.error('Error  while fetching locatorbank'); });

            fetchAmaterial();


            /*
             * getListEmployee(vm.employee);
             * 
             * inspectionService.getCompletionDrawing({}).then(function (d){ vm.typeId =
             * d.plain(); fetchWorkItem(); }, function(errResponse){ console.error('Error
             * while fetching locatorbank'); });
             */
        }
        // /1




        function add() {
        	$($('input[name="uploadfilecalculate"]')[0]).val('');
        	vm.hideFileCalculate = false;
            var check = false;



            inspectionService.CheckCA(vm.item).then(function(result) {

                if (result) {
                    vm.showDetail = true;
                    vm.showSearch = false;
                    vm.isCreateNew = true;



                    //
                    vm.item.acceptFromDate = '';
                    vm.item.acceptToDate = '',
                        vm.item.acceptPlace = '';
                    vm.item.applyBenchmark = '';
                    vm.item.otherDocuments = '';
                    vm.item.constructionQuality = '';
                    vm.item.otherComments = '';
                    vm.item.conclusion = 1;
                    vm.item.completeRequest = '';
                    vm.item.signDate = '';
                    vm.item.signPlace = '';
                    vm.item.statusCa = 0;
                    vm.item.isActive = 1;
                    fetchWorkItem();
                    fetchAmaterial();
                    vm.buttonInactive.all = false;
                }

            }, function(errResponse) {
                if (errResponse.status == 409) {
                    toastr.error("Một bản ghi đã tồn tại!");
                    check = false;
                } else if (errResponse.status == 406) {
                    toastr.error("Phiếu yêu cầu nghiệm thu chưa tạo ");
                    check = false;
                    return;
                } else if (errResponse.status == 403) {
                    toastr.error("Phiếu yêu cầu nghiệm thu chưa ký");
                    check = false;
                    return;
                }

            })








        }
        fillDataTableFileCalculate([]);
        getCalculateFile();
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

        function getListEmployee(param1) {


            var Monitor = [],
                InChargeMonitor = [],
                Director = [],
                InChargeContruction = [],
                DesignDirection = [],
                DesignManager = [];

            inspectionService.getListEmployeeByConstruction(param1).then(function(d) {
                ArrEmployee = d.plain();


                inspectionService.getRoleId().then(function(data) {
//                        vm.role = data;
                        for (var i = 0; i < d.plain().length; i++) {

                            if (d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]) {//2
                                Monitor.push(d.plain()[i])
                                if (d.plain()[i].isCurrent == 1) {
                                    vm.item.amonitorId = d.plain()[i].userId;
                                } else if (vm.item.amonitorId == 0) {
                                    vm.item.amonitorId = d.plain()[i].userId;
                                }

                            }
                            if (d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_PTGST"]) {//4
                                InChargeMonitor.push(d.plain()[i])
                                if (d.plain()[i].isCurrent == 1) {
                                    vm.item.ainChargeMonitorId = d.plain()[i].userId;
                                } else if (vm.item.ainChargeMonitorId == 0) {
                                    vm.item.ainChargeMonitorId = d.plain()[i].userId;
                                }
                            }
                            if (d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]) {//3
                                Director.push(d.plain()[i])
                                if (d.plain()[i].isCurrent == 1) {
                                    vm.item.bdirectorId = d.plain()[i].userId;
                                } else if (vm.item.bdirectorId == 0) {
                                    vm.item.bdirectorId = d.plain()[i].userId;
                                }
                            }
                            if (d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_DT_PTTC"]) {//1
                                InChargeContruction.push(d.plain()[i])
                                if (d.plain()[i].isCurrent == 1) {
                                    vm.item.binChargeConstructId = d.plain()[i].userId;
                                } else if (vm.item.binChargeConstructId == 0) {
                                    vm.item.binChargeConstructId = d.plain()[i].userId;
                                }
                            }
                            if (d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_DDTV"]) {//6
                                DesignDirection.push(d.plain()[i])
                                if (d.plain()[i].isCurrent == 1) {
                                    vm.item.cdesignDirectionId = d.plain()[i].userId;
                                } else if (vm.item.cdesignDirectionId == 0) {
                                    vm.item.cdesignDirectionId = d.plain()[i].userId;
                                }
                            }
                            if (d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_CNTK"]) {//7
                                DesignManager.push(d.plain()[i])
                                if (d.plain()[i].isCurrent == 1) {
                                    vm.item.cdesignManagerId = d.plain()[i].userId;
                                } else if (vm.item.cdesignManagerId == 0) {
                                    vm.item.cdesignManagerId = d.plain()[i].userId;
                                }
                            }
                        }






                        vm.Monitor = Monitor;
                        vm.InChargeMonitor = InChargeMonitor;
                        vm.Director = Director;
                        vm.InChargeContruction = InChargeContruction;
                        vm.DesignDirection = DesignDirection;
                        vm.DesignManager = DesignManager;



                        /*				if(vm.Monitor.length > 0) {
                        					vm.item.amonitorId = Monitor[0].userId;
                        				}
                        				if(vm.InChargeMonitor.length > 0) {
                        					vm.item.ainChargeMonitorId = InChargeMonitor[0].userId;
                        				}
                        				if(vm.Director.length > 0) {
                        					vm.item.bdirectorId = Director[0].userId;
                        				}
                        				if(vm.InChargeContruction.length > 0) {
                        					vm.item.binChargeConstructId = InChargeContruction[0].userId;
                        				}
                        				if(vm.DesignDirection.length > 0) {
                        					vm.item.cdesignDirectionId = DesignDirection[0].userId;
                        				}
                        				if(vm.DesignManager.length > 0) {
                        					vm.item.cdesignManagerId = DesignManager[0].userId;
                        				}
                        				*/

                    })
                    .catch(function(data, status) {
                        console.error('getRoleId error', response.status, response.data);
                    });


                inspectionService.getConstructionacceptance(vm.item).then(function(d) {

                    fillDataTableMain(d.plain());
                    vm.item1 = d.plain();
                    // refreshGrid(d.plain(),vm.MainGrid);
                }, function(e) {
                    console.log("Erro");
                });


            }, function(errResponse) {
                console.error('Error while fetching locatorbank');
            });

            console.log(vm.item.aMonitorId);
        }


        function fetchWorkItem() {


            vm.item.status = 1;
            vm.item.constructionId = vm.item.constructId;
            inspectionService.getWorkItem(vm.item).then(function(d) {
                //fillDataTable(d.plain());
                /*				for(var i =0;i<d.plain().length;i++){
                					if(d.plain()[i].type == 1 || d.plain()[i].type == 2){
                						WorkItems.push(d.plain()[i])
                					}
                				}*/

                WorkItems = d.plain();
                refreshGrid(WorkItems, vm.WorkItemGrid);
                /*				for(var i = 0;i <d.plain().length;i++){
                					
                					if(d.plain()[i].type == 1){
                						IContract.push(d.plain()[i]);
                					}else if(d.plain()[i].type == 2){
                						OContract.push(d.plain()[i]);
                					}
                				}
                				fillDataTable3(IContract);
                				fillDataTable4(OContract);*/

            }, function(errResponse) {
                console.error('Error while fetching crevaluation');
            })
        }

        function fetchAmaterial() {
            //Khoi luong vat tu do A cap
            inspectionService.getAmaterialhandoverforcontruction(vm.item.constructId).then(function(d) {
                fillDataTable2(d.plain());
                MerLists = d.plain();
                refreshGrid(d.plain(), vm.AmaterialGrid);

            }, function(errResponse) {
                console.error('Error while fetching crevaluation');
            });
        }


        vm.save = save;

        function save() {
        	 vm.item.listDocumentPath = [];
         	vm.item.listDocumentName = [];
            // console.log(vm.item.aMonitorId);

            vm.item.userid = Constant.user.srvUser.userId;

            /*
             * if(vm.item.amonitorId == undefined && vm.Monitor.length >0 ){
             * vm.item.amonitorId = vm.Monitor[0].userId; } if(vm.item.ainChargeMonitorId ==
             * undefined && vm.InChargeMonitor.length >0 ){ vm.item.ainChargeMonitorId =
             * vm.InChargeMonitor[0].userId; } if(vm.item.bdirectorId == undefined &&
             * vm.Director.length >0 ){ vm.item.bdirectorId = vm.Director[0].userId; }
             * if(vm.item.binChargeConstructId == undefined && vm.InChargeContruction.length
             * >0 ){ vm.item.binChargeConstructId = vm.InChargeContruction[0].userId; }
             * if(vm.item.cdesignDirectionId == undefined && vm.DesignDirection.length >0 ){
             * vm.item.cdesignDirectionId = vm.DesignDirection[0].userId; }
             * if(vm.item.cdesignManagerId == undefined && vm.DesignManager.length >0 ){
             * vm.item.cdesignManagerId = vm.DesignManager[0].userId; }
             */


            var executeQuantity = 0;
            //  var checkdataWork = false;
            var dataWork = vm.WorkItemGrid.dataSource.data();
            var dataMer = vm.AmaterialGrid.dataSource.data();


            /*for(var i = 0;i<dataMer.length;i++){
            	dataMer[i].defaultSortField = '';
            	dataMer[i].uid = '';
            	dataMer[i].handoverQuantity = dataMer[i].actualReceiveQuantity;

            }*/
            vm.item.constracceptmerlist = dataMer;
            vm.item.constracceptworklist = dataWork;


            //delete vm.item.constrcompleterecordsmap;



            if (vm.showDetail) {
                if (vm.validator.validate()) {
                    for (var i = 0; i < dataWork.length; i++) {
                        //dataWork[i].defaultSortField = '';
                        //dataWork[i].uid = '';

                        if (dataWork[i].executeQuantity == null) {
                            toastr.error(dataWork[i].workItemName + " chưa điền khối lượng thực tế");
                            return;
                        }
                        /*else if(dataWork[i].instanceDrawCode == " "){
                        	toastr.error(dataWork[i].workItemName +" chưa điền bản vẽ thi công");
                        	return;
                        }*/
                    }

                    if (vm.isCreateNew) {
                    	if($('input[name="uploadfilecalculate"]')[0].files.length > 0){
                    		for(var i=0;i<$('input[name="uploadfilecalculate"]')[0].files.length;i++){
                        		vm.uploadFile(i);
                            	var fileName = $('input[name="uploadfilecalculate"]')[0].files[i].name;
                            	vm.item.listDocumentName.push(fileName);
                        	}
                        	
                        	vm.item.listDocumentPath = list1;
                        	if(vm.item.listDocumentName.length != vm.item.listDocumentPath.length){
                        		toastr.error("Lỗi khi upload file");
                        		return;
                        	}
                        	inspectionService.addFileCal(vm.item).then(function(){
                        		list1 =[];
                        	});
                    	}
                        inspectionService.saveConstructionacceptance(vm.item).then(function(result) {

                            toastr.success(message.saveSuccess);
                            vm.item.constructionAcceptanceId = result;
                            vm.showDetail = false;
                            vm.showCreate = false;
                            //refreshGrid(dataWork,vm.WorkItemGrid);
                            reloadData();

                        }, function(errResponse) {
                            if (errResponse.status == 409) {
                                toastr.error("Một bản ghi đã tồn tại!");
                            } else if (errResponse.status == 406) {
                                toastr.error("Phiếu yêu cầu nghiệm thu chưa tạo ");
                            } else if (errResponse.status == 403) {
                                toastr.error("Phiếu yêu cầu nghiệm thu chưa ký");
                            } else if (errResponse.status == 400) {
                                toastr.error("Ngày kết thúc phải lớn hơn ngày bắt đầu");
                            } else {
                                toastr.error(gettextCatalog.getString("Lỗi thêm mới"));
                            }

                        });

                    } else {
                    	if($('input[name="uploadfilecalculate"]')[0].files.length > 0){
                    		for(var i=0;i<$('input[name="uploadfilecalculate"]')[0].files.length;i++){
                        		vm.uploadFile(i);
                            	var fileName = $('input[name="uploadfilecalculate"]')[0].files[i].name;
                            	vm.item.listDocumentName.push(fileName);
                        	}
                        	
                        	vm.item.listDocumentPath = list1;
                        	if(vm.item.listDocumentName.length != vm.item.listDocumentPath.length){
                        		toastr.error("Lỗi khi upload file");
                        		return;
                        	}
                        	inspectionService.addFileCal(vm.item).then(function(){
                        		list1 =[];
                        	});
                    	}
                        if (vm.item.createdUserId != Constant.user.srvUser.userId) {
                            toastr.warning("Bạn không phải người tạo, không có quyền sửa bản ghi này");
                            return;
                        }

                        inspectionService.updateConstructionacceptance(vm.item).then(function(result) {
                            toastr.success(message.saveSuccess);

                            vm.showDetail = false;
                            vm.showCreate = false;
                            //refreshGrid(dataWork,vm.WorkItemGrid);
                            reloadData()
                        }, function(errResponse) {
                            if (errResponse.status == 406) {
                                toastr.error("Bản ghi đã trình ký không thể sửa!");
                            } else if (errResponse.status == 400) {
                                toastr.error("Ngày kết thúc phải lớn hơn ngày bắt đầu");
                            } else {
                                toastr.error(gettextCatalog.getString("Lỗi cập nhật"));
                            }


                        });

                        // refreshGrid(dataWork,vm.WorkItemGrid);
                        // refreshGrid(dataMer,vm.AmaterialGrid);
                    }
                }
            }

        }



        function refreshGrid(d, grid) {
            grid.dataSource.data(d);
            grid.refresh();
        }
        vm.record = 0;
        vm.record1 = 0;
        // Bảng diễn giải khối lượng xây lắp hoàn thành
        function fillDataTableWorkItem(data) {


            vm.WorkItemGridOptions = kendoConfig
                .getGridOptions({



                    autoBind: true,
                    dataSource: {
                        type: "odata",
                        transport: {
                            read: data
                        },
                        sort: { field: "estimatesWorkItemId", dir: "asc" },
                        group: {
                            field: "type",

                        },
                        schema: {
                            model: {
                                fields: {
                                    workItemCode: { editable: false, nullable: true },
                                    workItemName: { editable: false, nullable: true },
                                    unit: { editable: false, nullable: true },
                                    evaluateQuantity: { type: "number", editable: false, nullable: true },
                                    executeQuantity: {
                                        validation: {
                                            min: 0,
                                            required: { message: "Khối lượng thi công thực tế phải lớn hơn 0" },


                                        }
                                    }
                                }
                            }
                        },
                        pageSize: 50
                    },
                    change: onChange,
                    noRecords: true,
                    pageable: {
                        refresh: true,
                        pageSizes: [50]
                    },
                    dataBinding: function() {
                        vm.record = 0;
                        vm.record1 = 0;
                    },
                    messages: {
                        noRecords: gettextCatalog.getString("Không có kết quả nào")
                    },
                	edit: function(e) {
   			         e.container.find("input[name=workItemCode]").attr("maxlength", 100);
   			         e.container.find("input[name=workItemName]").attr("maxlength", 1000);
   			         e.container.find("input[name=unit]").attr("maxlength", 10);
   			         e.container.find("input[name=explain]").attr("maxlength", 1000);
   			         e.container.find("input[name=instanceDrawCode]").attr("maxlength", 200);
			         e.container.find("input[name=evaluateQuantity]").attr("maxlength", 34);
			         e.container.find("input[name=comments]").attr("maxlength", 1000);
			         e.container.find("input[name=executeQuantity]").attr("maxlength", 34);
   			      
   			      
   			   
   			   
   			},
                    columns: [{
                            title: gettextCatalog.getString("STT"),
                            template: function(item) {
                                if (item.type == 1) { return ++vm.record } else if (item.type == 2) { return ++vm.record1 }
                            },

                            headerAttributes: { style: "text-align:center;font-weight: bold;" },
                            attributes: { style: "text-align:center;" },
                            width: 60
                        },
                        {
                            title: gettextCatalog
                                .getString("Mã hiệu đơn giá"),
                            field: "workItemCode",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 200
                        },
                        {
                            title: gettextCatalog
                                .getString("Nội dung công việc"),
                            // template : "",
                            field: "workItemName",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 250
                        },
                        {
                            title: gettextCatalog
                                .getString("Đơn vị tính"),
                            field: "unit",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Diễn giải"),
                            field: "explain",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng theo TK"),
                            field: "evaluateQuantity",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;" },
                            width: 170,
							template: function(dataItem) {
	                            if ($.isNumeric(dataItem.evaluateQuantity) && dataItem.evaluateQuantity >= 0) {
	                            	dataItem.evaluateQuantity = parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
	                                return parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
	                            }
	                            else{dataItem.evaluateQuantity = 0;
	                            return 0;}
	                        },
	                    decimals: 3,
						validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng thực tế"),
                            field: "executeQuantity",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;" },
							template: function(dataItem) {
	                            if ($.isNumeric(dataItem.executeQuantity) && dataItem.executeQuantity >= 0) {
	                            	dataItem.executeQuantity = parseFloat(Number(dataItem.executeQuantity).toFixed(3));
	                                return parseFloat(Number(dataItem.executeQuantity).toFixed(3));
	                            }
	                            else{dataItem.executeQuantity = null;
	                            return "";}
	                        },
	                    decimals: 3,
						validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},


                            width: 170
                        },
                        {
                            title: gettextCatalog.getString("<b>Khối lượng chênh lệch</b>"),
                            headerAttributes: { style: "text-align:center;" },
                            columns: [{
                                    title: gettextCatalog.getString("Tăng"),

                                    template: function(dataItem) {
                                        if (dataItem.executeQuantity >= dataItem.evaluateQuantity) {
                                            return parseFloat(Number(dataItem.executeQuantity - dataItem.evaluateQuantity).toFixed(3));

                                        }
                                        return "";
                                    },

                                    headerAttributes: { style: "text-align:center;font-weight: bold;" },
                                    attributes: { style: "text-align:center;" },
                                    format: "{0:n3}",
                                    decimals: 3,
                                    width: 150
                                },
                                {
                                    title: gettextCatalog.getString("Giảm"),
                                    template: function(dataItem) {
                                        if (dataItem.evaluateQuantity >= dataItem.executeQuantity) {
                                            return parseFloat(Number(dataItem.evaluateQuantity - dataItem.executeQuantity).toFixed(3));

                                        }
                                        return "";
                                    },
                                    headerAttributes: { style: "text-align:center;font-weight: bold;" },
                                    attributes: { style: "text-align:center;" },
                                    format: "{0:n3}",
                                    decimals: 3,
                                    width: 150
                                }
                            ]
                        },
                        {


                            field: "instanceDrawCode",
                            title: "Bản vẽ",

                            width: "180px",
                            //editor: categoryDropDownEditor, 
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            // template: "#=instanceDrawCode.drawName#"


                        },


                        {
                            title: gettextCatalog.getString("Ghi chú"),
                            field: "comments",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120
                        },



                        {
                            title: gettextCatalog.getString("Loại hợp đồng"),
                            field: "type",
                            hidden: true,
                            groupHeaderTemplate: " #if(value == '1'){#Nội dung công việc trong hợp đồng#}else if(value == '2'){#Nội dung công việc ngoài hợp đồng(phụ lục hợp đồng)#}#",
                            width: 120
                        },

                        {
                            title: gettextCatalog.getString("estimatesWorkItemId"),
                            field: "estimatesWorkItemId",
                            hidden: true,
                            width: 120
                        }
                    ],
                    /*						    save: function(e) {     
                    							        var dataSource = this.dataSource;
                    							        e.model.one("change", function() {
                    							            dataSource.one("change", function() {
                    							               console.log("aaaaaaaaaaa")
                    							            });
                    							          dataSource.fetch();
                    							        });
                    							    }*/
                });
        }





        /*        function categoryDropDownEditor(container, options) {
                    $('<input required name="' + options.field + '"/>')
                        .appendTo(container)
                        .kendoDropDownList({
                            autoBind: false,
                            dataTextField: "drawCode",
                            dataValueField: "drawCode",
                            dataSource: vm.typeId

                        });
                }*/
        function categoryDropDownEditor(container, options) {
            $("<select multiple='multiple' " +
                "data-bind='value : drawCode'/>").appendTo(container).kendoMultiSelect({
                dataSource: vm.typeId
            });
        }




        // Khối lượng VTTB do A cấp
        var recordAmaterial = 0;

        function fillDataTable2(data) {
            vm.gridOptions2 = kendoConfig
                .getGridOptions({
                    autoBind: true,
                    dataSource: {
                        type: "odata",
                        transport: {
                            read: data
                        },
                        sort: { field: "merChandiseId", dir: "asc" },

                        schema: {
                            model: {
                                fields: {
                                    merName: { editable: false, nullable: true },
                                    unitName: { editable: false, nullable: true },
                                    /*handoverQuantity: {},*/
                                    handoverQuantity: { type: "number", editable: false, nullable: true, validation: { min: 0, required: { message: "Khối lượng thi công thiết k phải lớn hơn 0" } } },
                                    executeQuantity: {
                                        validation: {
                                            min: 0,
                                            required: { message: "Khối lượng thi công thực tế phải lớn hơn 0" },


                                        }
                                    }
                                }
                            }
                        },

                    },
                    // change : onChange,

                    dataBinding: function() {
                        recordAmaterial = (this.dataSource.page() - 1) * this.dataSource.pageSize();

                    },
                    noRecords: true,
                    messages: {
                        noRecords: gettextCatalog
                            .getString("Không có kết quả nào")
                    },
                    columns: [{
                            title: "STT",
                            headerAttributes: { style: "text-align:center;font-weight: bold;" },
                            attributes: { style: "text-align:center;" },
                            template: function(item) {
                                return ++recordAmaterial;
                            },
                            width: 40
                        },
                        {
                            title: gettextCatalog
                                .getString("Nội dung"),
                            field: "merName",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Đơn vị tính"),
                            field: "unitName",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng theo TK"),
                            field: "handoverQuantity",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120,
							template: function(dataItem) {
	                            if ($.isNumeric(dataItem.handoverQuantity) && dataItem.handoverQuantity >= 0) {
	                            	dataItem.handoverQuantity = parseFloat(Number(dataItem.handoverQuantity).toFixed(3));
	                                return parseFloat(Number(dataItem.handoverQuantity).toFixed(3));
	                            }
	                            else{dataItem.handoverQuantity = 0;
	                            return 0;}
	                        },
	                    decimals: 3,
						validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng thực tế"),
                            field: "executeQuantity",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120,
							template: function(dataItem) {
	                            if ($.isNumeric(dataItem.executeQuantity) && dataItem.executeQuantity >= 0) {
	                            	dataItem.executeQuantity = parseFloat(Number(dataItem.executeQuantity).toFixed(3));
	                                return parseFloat(Number(dataItem.executeQuantity).toFixed(3));
	                            }
	                            else{dataItem.executeQuantity = 0;
	                            return 0;}
	                        },
	                    decimals: 3,
						validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
                        }

                        , {
                            title: gettextCatalog.getString("<b>Khối lượng chênh lệch</b>"),
                            headerAttributes: { style: "text-align:center;" },
                            columns: [{
                                    title: gettextCatalog.getString("Tăng"),

                                    template: function(dataItem) {
                                        if (dataItem.executeQuantity >= dataItem.handoverQuantity) {
                                            return parseFloat(Number(dataItem.executeQuantity - dataItem.handoverQuantity).toFixed(3));

                                        }
                                        return "";
                                    },
                                    format: "{0:n3}",
                                    decimals: 3,
                                    headerAttributes: { style: "text-align:center;font-weight: bold;" },
                                    attributes: { style: "text-align:center;" },
                                    width: 150
                                },
                                {
                                    title: gettextCatalog.getString("Giảm"),
                                    template: function(dataItem) {
                                        if (dataItem.handoverQuantity >= dataItem.executeQuantity) {
                                            return parseFloat(Number(dataItem.handoverQuantity - dataItem.executeQuantity).toFixed(3));
                                        }
                                        return "";
                                    },
                                    headerAttributes: { style: "text-align:center;font-weight: bold;" },
                                    attributes: { style: "text-align:center;" },
                                    width: 150,
                                    format: "{0:n3}",
                                    decimals: 3
                                }
                            ]

                        },
                        {
                            title: gettextCatalog.getString("Ghi chú"),
                            field: "comments",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120
                        },
                        {
                            title: gettextCatalog.getString("merEntityId"),
                            field: "merEntityId",
                            hidden: true,
                            width: 120
                        },
                        {
                            title: gettextCatalog.getString("merChandiseId"),
                            field: "merChandiseId",
                            hidden: true,
                            width: 120
                        },
                        {
                            title: gettextCatalog.getString("serialNumber"),
                            field: "serialNumber",
                            hidden: true,
                            width: 120
                        }

                        ,
                        {
                            title: gettextCatalog.getString("constrAcceptMerListId"),
                            field: "constrAcceptMerListId",
                            hidden: true,
                            width: 120
                        }
                        /*,
								{
									title : gettextCatalog.getString("expNoteCode"),
									field : "expNoteCode",
									hidden:true,
                                    width : 120
								}
								*/
                    ]
                });
        }
        // Khối lượng xây lắp do nhà thầu thi công
        function fillDataTable3(data) {
            vm.gridOptions3 = kendoConfig
                .getGridOptions({
                    autoBind: true,
                    dataSource: data,
                    noRecords: true,
                    messages: {
                        noRecords: gettextCatalog
                            .getString("Không có kết quả nào")
                    },
                    columns: [
                        /*							{
                        								title : gettextCatalog.getString("STT"),
                        								field : "",
                        								width : 60
                        							},*/
                        {
                            title: gettextCatalog
                                .getString("Nội dung"),
                            field: "workItemName",
                            width: 120,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" }
                        },
                        {
                            title: gettextCatalog
                                .getString("Đơn vị tính"),
                            field: "unit",
                            width: 120,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" }

                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng theo TK"),
                            field: "workAmount",
                            width: 120,
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;" }
                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng thực tế"),
                            field: "executeQuantity",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;" },
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Chênh lệch"),
                            field: "deviation",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:right;" },
                            width: 120

                        },
                        {
                            title: gettextCatalog.getString("Ghi chú"),
                            field: "comments",
                            headerAttributes: { style: "text-align:center;" },
                            attributes: { style: "text-align:left;" },
                            width: 120
                        }
                    ]
                });
        }
        // Các công việc phát sinh ngoài hợp đồng
        function fillDataTable4(data) {
            vm.gridOptions4 = kendoConfig
                .getGridOptions({
                    autoBind: true,
                    dataSource: data,
                    noRecords: true,
                    messages: {
                        noRecords: gettextCatalog
                            .getString("Không có kết quả nào")
                    },
                    columns: [{
                            title: gettextCatalog.getString("STT"),
                            field: "",
                            width: 60
                        },
                        {
                            title: gettextCatalog
                                .getString("Nội dung"),
                            field: "workItemName",
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Đơn vị tính"),
                            field: "unit",
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng theo TK"),
                            field: "workAmount",
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Khối lượng thực tế"),
                            field: "executeQuantity",
                            width: 120
                        },
                        {
                            title: gettextCatalog
                                .getString("Chênh lệch"),
                            field: "deviation",
                            width: 120
                        },
                        {
                            title: gettextCatalog.getString("Ghi chú"),
                            field: "comments",
                            width: 120
                        }
                    ]
                });
        }





        vm.exportFiledoc = function() {

            $('#loading').show();
            inspectionService.exportFiledoc(vm.item.constructionAcceptanceId).then(function(data) {
                window.location = Constant.DOWNLOAD_SERVICE + data.fileName;
            }).catch(function() {
                $('#loading').hide();
                toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                return;
            }).finally(function() {
                $('#loading').hide();
            });
        }

        vm.exportFilepdf = function() {

            $('#loading').show();
            inspectionService.exportFile(vm.item.constructionAcceptanceId).then(function(data) {
                window.location = Constant.DOWNLOAD_SERVICE + data.fileName;
            }).catch(function() {
                $('#loading').hide();
                toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                return;
            }).finally(function() {
                $('#loading').hide();
            });
        }








        // Bảng danh sách nghiệm thu hoàn thành công trình
        var record = 0;

        function fillDataTableMain(data) {

            vm.MainGridOptions = kendoConfig
                .getGridOptions({
                    autoBind: true,
                    dataSource: data,
                    editable: false,









                    change: onChange,
                    noRecords: true,
                    dataBound: function(e) {
                        setTimeout(function() {
                            var grid = vm.MainGrid;
                            if (grid != undefined) {
                                grid.select("tr:eq(0)");
                            }
                        }, 50)
                    },
                    dataBinding: function() {
                        record = (this.dataSource.page() - 1) * this.dataSource.pageSize();
                    },
                    messages: {
                        noRecords: gettextCatalog
                            .getString("Không có kết quả nào")
                    },
                    columns: [{
                            title: "STT",
                            template: function(item) {
                                return ++record;
                            },
                            width: 50,
                            headerAttributes: {
                                style: "text-align:center;font-weight: bold;"
                            },
                            attributes: {
                                style: "text-align:center;"
                            }
                        },
                        /*								{
                        									title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                        									template : "<input type='checkbox' name='gridcheckbox' />",
                        									width : 20
                        								},*/
                        {
                            title: gettextCatalog.getString("Mã Biên bản"),
                            field: "code",
                            width: 100
                        },
                        {
                            title: gettextCatalog.getString("Mã công trình"),
                            field: "constrtCode",
                            width: 100
                        },
                        {
                            title: gettextCatalog.getString("Mã hợp đồng"),
                            field: "contractCode",
                            width: 150
                        },
                        {
                            title: gettextCatalog.getString("Tên hợp đồng"),
                            field: "contractName",
                            width: 150
                        },
                        {
                            title: gettextCatalog.getString("Trạng thái ký CA"),
                            field: "statusCa",
                            attributes: { style: "text-align:left;", class:"statusColumn" },
                            template: function(data) {
                                if (data.statusCa == 0)
                                    return "Soạn thảo";
                                if (data.statusCa == 1)
                                    return "Trình ký";
                                if (data.statusCa == 2)
                                    return "Đã ký";
                                if (data.statusCa == 3)
                                    return "Từ chối ký";
                                return '';
                            },
                            width: 100
                        }
                    ]
                });
        }




        function showGrid() {
        	$($('input[name="uploadfilecalculate"]')[0]).val('');
        	reloadDataCal();
        	if(vm.item1.statusCa == 0 || vm.item1.statusCa == 3){
        		vm.hideFileCalculate = false;
        	}else{
        		vm.hideFileCalculate = true;
        	}
        	

            $(".k-invalid-msg").hide();

            if (vm.showDetail == false) {
                vm.isCreateNew = false;
                //vm.Contract = true;
                var grid = vm.MainGrid;
                // Check select
                if (vm.showSearch && grid.select() == null || grid.select().length == 0) {
                    reloadData();
                    vm.showSearch = false;
                    return;
                }
                detail();
            } else {
                vm.showDetail = false;
                vm.showSearch = false;
                reloadData();
            }

        }


        function detail() {
            if (vm.MainGrid.select().length > 0) {
                vm.showDetail = true;
            } else {
                toastr.warning(message.lineRequired);
            }
        }

        function onChange(e) {
            if (vm.MainGrid.select().length > 0) {
                var tr = vm.MainGrid.select().closest("tr");
                var dataItem = vm.MainGrid.dataItem(tr);
                vm.item = dataItem;
                WorkItems = dataItem.constracceptworklist;
                MerLists = dataItem.constracceptmerlist;
                //ky CA
                vm.theSign.code = vm.item.code;
                vm.theSign.constructId = vm.item.constructId;
                vm.theSign.constrCompReMapId = vm.item.constrcompleterecordsmap.constrCompReMapId;
                vm.theSign.stringEmployee = vm.item.binChargeConstructId + ',' + vm.item.ainChargeMonitorId + ',' + vm.item.bdirectorId + ',' + vm.item.amonitorId + ',' + vm.item.cdesignManagerId + ',' + vm.item.cdesignDirectionId;
                vm.theSign.isSign = 'theSignCA';

                vm.theSign.roleName = [ "Phụ trách thi công trực tiếp","Phụ trách giám sát thi công ", "Giám đốc nhà thầu ", "Thủ trưởng đơn vị", "Chủ nhiệm thiết kế ", "Giám đốc tư vấn thiết kế"];
                //1-4-3-1-6-7
                vm.theSign.roleId = [ Constant.ROLE_ID["employee_roleID_DT_PTTC"],Constant.ROLE_ID["employee_roleID_CDT_PTGST"],
                                      Constant.ROLE_ID["employee_roleID_DT_GDNT"], Constant.ROLE_ID["employee_roleID_DT_PTTC"], 
                                      Constant.ROLE_ID["employee_roleID_TVTK_DDTV"], Constant.ROLE_ID["employee_roleID_TVTK_CNTK"]]
                theSignCA.setItem(vm.theSign);
                //end

                $rootScope.$broadcast("listWorkOrganizationPlan.item", vm.theSign);


                if (vm.showDetail == false) {
                    var arrayWorkItem = dataItem.constracceptworklist;
                    refreshGrid(arrayWorkItem, vm.WorkItemGrid);

                    var arrayMerList = dataItem.constracceptmerlist;
                    refreshGrid(arrayMerList, vm.AmaterialGrid);


                }
                if (vm.item.statusCa == 2 || vm.item.statusCa == 1) {

                    vm.buttonInactive.all = true

                } else {
                    vm.buttonInactive.all = false;
                }
                if(vm.item.createdUserId != Constant.user.srvUser.userId){
                	 vm.buttonInactive.all = true;
    			}else{
    				 vm.buttonInactive.all = false;
    			}

                /*if(vm.AmaterialGrid.select().length == 0){   
                var arrayAmaterial = dataItem.constracceptmerlist;	            		
                	refreshGrid(arrayAmaterial, vm.AmaterialGrid);
                }*/

            }
        }


        function reloadData() {
            inspectionService.getConstructionacceptance(vm.item).then(function(d) {
                refreshGrid(d.plain(), vm.MainGrid);
            }, function(e) {
                console.log("Erro");
            });

        }



        vm.myFunc = function() {
            /*//vm.completeRequest = false ;
            $rootScope.completeRequest = false;
            if(vm.item.conclusion == 0){
            	$rootScope.completeRequest =  true;
            }else{
            	$rootScope.completeRequest = false ;
            }
            console.log(vm.completeRequest);*/
        }

        $scope.$on("ProposalEvaluation.detail", function(event, item) {
            if (item != undefined) {

                vm.item.constructId = item.constructId;
                vm.item.constrtCode = item.constrtCode;
                vm.item.constrtName = item.constrtName;
                vm.item.contractCode = item.contractCode;
                vm.item.contractName = item.contractName;
                vm.item.constrtAddress = item.constrtAddress;
                vm.item.stationCode = item.stationCode;
                vm.item.contractId = item.contractId;

                reloadData();
            } else {
                console.error("không nhận được dữ liệu!");
            }
        });
        vm.signCA = function() {
            if (vm.theSign.constrCompReMapId == '') {
                toastr.warning("Bạn chưa chọn bản ghi nào");
                return;
            }
            if (vm.item.statusCa != 0) {
                toastr.warning("Bản ghi đã trình ký");
                return;
            }
            $('#loading').show();
            inspectionService.exportFile(vm.item.constructionAcceptanceId).then(function(data) {
                vm.theSign.path = data.fileName;
                
                if(vm.item.type == 1){
                	vm.theSign.nameFile = "BM-TCNT-15.pdf";
                }else{
                	vm.theSign.nameFile = "BM-TCNT-16.pdf";
                }
                
                theSignCA.setItem(vm.theSign);
                goTo('THE_SIGN_CA');
            }).catch(function() {
                $('#loading').hide();
                toastr.error(gettextCatalog.getString("Lỗi khi export!"));
                return;
            }).finally(function() {
                $('#loading').hide();
            });

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
                // toastr.error(gettextCatalog.getString("Tài khoản đăng
                // nhập hiện tại không được phép truy cập vào chức năng
                // này!"));
            }
        }


        function remove() {
            var grid = vm.MainGrid;
            // Check select
            if (grid.select() == null || grid.select().length == 0) {
                toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
                return;
            }



            var tr = grid.select().closest("tr");
            var dataItem = grid.dataItem(tr);

            if (dataItem.statusCa == 1 || dataItem.statusCa == 2) {

                toastr.error(gettextCatalog.getString("Không thể xóa bản ghi đã kí CA"));
                return;
            }



            if (vm.MainGrid.select().length > 0 && confirm('Xác nhận xóa')) {
                if (dataItem.createdUserId != Constant.user.srvUser.userId) {
                    toastr.warning(gettextCatalog.getString("Bạn không có quyền xóa bản ghi này !"));
                    return;
                }
                inspectionService.updateIsActive(dataItem.constructionAcceptanceId).then(function(result) {
                    vm.showDetail = false;
                    vm.showCreate = false;
                    reloadData()
                    toastr.success(gettextCatalog.getString("Xóa thành công!"));
                }, function(errResponse) {
                    if (errResponse.status == 406) {
                        toastr.error(gettextCatalog.getString("Không thể xóa bản ghi đã kí CA"));
                    } else {
                        console.error('Error while deleting item');
                    }
                });
            }
        }

        vm.multiDelete = multiDelete;
        //delete multiple record
        function multiDelete() {
            var selectedRow = [];
            var grid = vm.MainGrid;
            grid.table.find("tr").each(function(idx, item) {
                var row = $(item);
                var checkbox = $('[name="gridcheckbox"]', row);

                if (checkbox.is(':checked')) {
                    // Push id into selectedRow
                    var tr = grid.select().closest("tr");
                    var dataItem = grid.dataItem(item);
                    console.log('dataItem ----');
                    console.log(dataItem.constructionAcceptanceId);
                    selectedRow.push(dataItem.constructionAcceptanceId);
                }
            });

            if (selectedRow.length == 0) {
                toastr.warning("Bạn cần chọn một bản ghi trước");
                return;
            }

            console.log(selectedRow);
            if (confirm('Xác nhận xóa')) {
                for (var i = 0; i < selectedRow.length; i++) {
                    inspectionService.deleteConstructionAcceptance(selectedRow[i]).then(function() {
                        toastr.success("Đã xóa thành công");
                        reloadData();
                    }, function(errResponse) {
                        if (errResponse.status == 302) {
                            toastr.error("Bản ghi đang được sử dụng");
                        } else {
                            toastr.error("Có lỗi xảy ra trong quá trình xóa!");
                        }


                    });
                }
            }
        }

        function chkSelectAll(item) {
            console.log('chkSelectAll');
            var grid = vm.MainGrid;
            chkSelectAllBase(vm.chkAll, grid);
        }
        vm.closepopup = function() {
            CommonService.closePopup();
        }
        vm.savepopup = function(popupid) {

            if (popupid == "WorkItemsID") {




                var fileInput = $('#xlf');
                var input = fileInput.get(0);
                var textFile = input.files[0];

//                if (textFile.name.indexOf(vm.item.constructId) != 0) {
//                    toastr.error("Biểu mẫu sai! Import đúng biểu mẫu " + vm.item.constructId + "-WorkItem.xls");
//                    return;
//                }
//69164445-WorkItem.xls
                var reader = new FileReader();
                if (input.files.length) {
                    var textFile = input.files[0];
                    reader.readAsBinaryString(textFile);
                    reader.onload = function(e) {
                        var data = e.target.result;
                        try {
                            //  Call XLSX 
                            var workbook = XLSX.read(data, { type: "binary" });
                            //   DO SOMETHING WITH workbook HERE 
                            var first_sheet_name = workbook.SheetNames[0];
                            //   Get worksheet 
                            var worksheet = workbook.Sheets[first_sheet_name];
                            var worklistexcell = XLSX.utils.sheet_to_json(worksheet, { raw: true });
                            /*		        var worklistexcell = [];
                            		        
                            		        for(var i = 0;i<array.length;i++){
                            		        	if(array[i].type == 1 || array[i].type == 2){
                            		        		worklistexcell.push(array[i]);
                            		        	}
                            		        }*/
                        } catch (err) {
                            toastr.error("File không đúng định dạng!");
                        }
                        var worklist = [];
                        var check = false;
                		for (var i = 0; i < worklistexcell.length; i++) {
                			worklist.push(worklistexcell[i]);
                			}
					
                		worklist.splice(0,1);
                		
                		for (var i = 0; i < worklist.length; i++) {
    						if(worklist[i].evaluateQuantity){
    							check=true;
    						}else{
    							worklist.splice(i,1);
    							i--;
    						}
    					}
                        for (var i = 0; i < worklistexcell.length; i++) {
                            for (var j = 0; j < WorkItems.length; j++) {

                                if (typeof worklistexcell[i].estimatesWorkItemId != "undefined") {
                                    if (worklistexcell[i].estimatesWorkItemId == WorkItems[j].estimatesWorkItemId) {
                                        WorkItems[j].workItemCode = worklistexcell[i].workItemCode
                                        //WorkItems[j].workItemName = worklistexcell[i].workItemName
                                        //WorkItems[j].unit  = worklistexcell[i].unit
                                        WorkItems[j].explain = worklistexcell[i].explain
                                        if (typeof worklistexcell[i].executeQuantity == "number") {
                                            WorkItems[j].executeQuantity = worklistexcell[i].executeQuantity;
                                        } else {
                                            WorkItems[j].executeQuantity = '';
                                        }

                                        //	WorkItems[j].executeQuantity = worklistexcell[i].executeQuantity
                                        //	WorkItems[j].evaluateQuantity = worklistexcell[i].evaluateQuantity
                                        if (typeof worklistexcell[i].instanceDrawCode == "undefined") {
                                            WorkItems[j].instanceDrawCode = " "
                                        } else {
                                            WorkItems[j].instanceDrawCode = worklistexcell[i].instanceDrawCode
                                        }

                                        WorkItems[j].comments = worklistexcell[i].comments
                                            //WorkItems[j].type = worklistexcell[i].type
                                    }
                                }
                            }
                        }
                        if(check == false){
    						toastr.warning(gettextCatalog.getString("File excel không hợp lệ, hãy kiểm tra lại dữ liệu trong file excel"));
    						return;
    					}
                        refreshGrid(WorkItems, vm.WorkItemGrid);
                        CommonService.closePopup();
                        toastr.success("Import thành công");
                    };
                } else {
                    alert('Please upload a file before continuing')
                };







            } else if (popupid == "MerListID") {



                var fileInput = $('#xlf');
                var input = fileInput.get(0);
                var textFile = input.files[0];

//                if (textFile.name.indexOf(vm.item.constructId+"-merlist") != 0) {
//                    toastr.error("Biểu mẫu sai! Import đúng biểu mẫu " + vm.item.constructId + "-merlist.xls");
//                    return;
//                }

                var reader = new FileReader();
                if (input.files.length) {
                    var textFile = input.files[0];
                    reader.readAsBinaryString(textFile);
                    reader.onload = function(e) {
                        var data = e.target.result;
                        try {
                            //  Call XLSX 
                            var workbook = XLSX.read(data, { type: "binary" });
                            //   DO SOMETHING WITH workbook HERE 
                            var first_sheet_name = workbook.SheetNames[0];
                            //   Get worksheet 
                            var worksheet = workbook.Sheets[first_sheet_name];
                            var MerListexcell = XLSX.utils.sheet_to_json(worksheet, { raw: true });
                            /*		        var worklistexcell = [];
                            		        
                            		        for(var i = 0;i<array.length;i++){
                            		        	if(array[i].type == 1 || array[i].type == 2){
                            		        		worklistexcell.push(array[i]);
                            		        	}
                            		        }*/
                        } catch (err) {
                            toastr.error("File không đúng định dạng!");
                        }
                        var worklist = [];
                        var check = false;
                		for (var i = 0; i < MerListexcell.length; i++) {
                			worklist.push(MerListexcell[i]);
                			}
					
                		worklist.splice(0,1);
                		
                		for (var i = 0; i < worklist.length; i++) {
    						if(worklist[i].handoverQuantity){
    							check=true;
    						}else{
    							worklist.splice(i,1);
    							i--;
    						}
    					}                        
                        for (var i = 0; i < MerListexcell.length; i++) {





                            for (var j = 0; j < MerLists.length; j++) {

                                if (typeof MerListexcell[i].merChandiseId != "undefined") {

                                    if (MerListexcell[i].merChandiseId == MerLists[j].merChandiseId && MerListexcell[i].merEntityId == MerLists[j].merEntityId) {

                                        MerLists[j].comments = MerListexcell[i].comments
                                        if (typeof MerListexcell[i].handoverQuantity == "number") {
                                            MerLists[j].handoverQuantity = MerListexcell[i].handoverQuantity;
                                        } else {
                                            MerLists[j].handoverQuantity = '';
                                        }

                                        if (typeof MerListexcell[i].executeQuantity == "number") {
                                            MerLists[j].executeQuantity = MerListexcell[i].executeQuantity;
                                        } else {
                                            MerLists[j].executeQuantity = '';
                                        }

                                        break;
                                    }
                                }
                            }
                        }
                        if(check == false){
    						toastr.warning(gettextCatalog.getString("File excel không hợp lệ, hãy kiểm tra lại dữ liệu trong file excel"));
    						return;
    					}
                        refreshGrid(MerLists, vm.AmaterialGrid);
                        CommonService.closePopup();
                        toastr.success("Import thành công");
                    };
                } else {
                    alert('Please upload a file before continuing')
                };



            }


        }
        vm.Import = function() {
            var templateUrl = 'views/popup/importPage.html';
            var title = 'Popup'
            CommonService.populateDataToGridCT(templateUrl, title, vm.ProjectGridOptions, vm, "WorkItemsID");
        }

        vm.ImportMerList = function() {
            var templateUrl = 'views/popup/importPage.html';
            var title = 'Khối lượng vật tư, thiết bị'
            CommonService.populateDataToGridCT(templateUrl, title, vm.ProjectGridOptions, vm, "MerListID");
        }

        vm.attachpopup = function(popupid) {

            if (popupid == "WorkItemsID") {
                vm.item.constructionId = vm.item.constructId;
                
                vm.workitem = {constructionId:vm.item.constructId ,status: 1 };
                inspectionService.exportWorkItem(vm.workitem).then(function(data) {
                    window.location = Constant.DOWNLOAD_SERVICE + data.fileName;
                });
            } else if (popupid == "MerListID") {
                vm.item.constructionId = vm.item.constructId;
                inspectionService.exportMerList(vm.item).then(function(data) {
                    window.location = Constant.DOWNLOAD_SERVICE + data.fileName;
                });
            }
        }
        function reloadDataCal(){
        	inspectionService.getFileCal(vm.item.constructId).then(function(d) {
        		
				refreshGridCal(d.plain());
			}, function() {
				refreshGridCal([]);
				console.error('Error while fetching object type');
			});
		};
	
		function refreshGridCal(d) {
			var grid = vm.calculateFileGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
        vm.multiDeleteCal = function(){
        	var selectedRow = [];
        	var grid = vm.calculateFileGrid;
			
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.attachId);
				}
			});
			
			if (selectedRow.length == 0){
				return;
			}
			
			if (selectedRow.length > 0 && confirm('Xác nhận xóa')) {
				inspectionService.deleteFileCal(selectedRow).then(function() {
	    		toastr.success("Đã xóa thành công");
	    		reloadDataCal();
			}, function(errResponse) {
				if (errResponse.status == 302){
					toastr.error("Bản ghi đang được sử dụng");
				}else{
					toastr.error("Có lỗi xảy ra trong quá trình xóa!");
				} 			    	 
			});
		}
        }
        
        getFolder();
		vm.uploadFile = function(i) {			
			var formData = new FormData();
			formData.append('tax_file', $('input[name="uploadfilecalculate"]')[0].files[i]); 
	        $.ajax({
	            url: Constant.BASE_SERVICE_URL+"fileservice/uploadATTT?folder="+ vm.folder,
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            async: false,
	            cache: false,
	            success: function(data) {
	            	list1.push(data[0]);
	            	//inspectionService.setData(data[0]);
	            	console.log(list1);
	            },
	            error: function() {
	                // Handle upload error
	                // ...
	            }
	        });
	    };// function uploadFile
	    vm.handleCheck = function(item){
			if(document.getElementById("checkalllistcalculate").checked == true){
				document.getElementById("checkalllistcalculate").checked = false;
			}
			var grid = vm.calculateFileGrid;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				
				if (checkbox.is(':checked')) {
					//vm.focusprior = false;
					
				}
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
								         title : "<input type='checkbox' name='gridchkselectall' id='checkalllistcalculate' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								         template : "<input type='checkbox' ng-checked=\"vm.chkAll\" name='gridcheckbox' ng-click='vm.handleCheck()'/>",
								         width : 25,
								         headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:center;"},
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


        $scope.$on("ChangeStatus", function(event, result) {
            if (result) {
                reloadData();
            }
        });
    }
})();