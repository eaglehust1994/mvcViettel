(function() {
    'use strict';

    var controllerId = 'estimateTableDetailController';

    angular.module('MetronicApp').controller(controllerId,
        estimateTableDetailController);

    /* @ngInject */
    function estimateTableDetailController($scope, $rootScope, $timeout, Constant,
        kendoConfig, estimatesTableDetailService, ProposalEvaluation, gettextCatalog, $kWindow,WindowService,
        CommonService, Restangular, PopupConst, theApproval,inspectionService) {
        var vm = this;
        vm.showReCord = false;

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
        vm.user = {};
        vm.user = Constant.getUser();
        vm.approDTO = {
            statusCa: '',
            employeeId: '',
            comments: '',
            detailSettlementEvaluateId: '',
            constrCompReMapId: ''
        }

        $scope.datact = [];
        vm.doAppro = true;
        vm.cancelAppro = true;
        vm.validatorOptions = kendoConfig.get('validatorOptions');
        vm.appro = true;
        vm.hide = true;
        vm.hideSave = false;
        vm.hideRemove = false;
        vm.showDetail = false;
        vm.isCreateNew = false;
        vm.disableForm = false;
        vm.hideAll = false;
        vm.checkCTTD = true;
        vm.add = add;
        vm.edit = edit;
        vm.showGrid = showGrid;
        vm.save = save;
        vm.remove = remove;
        vm.detail = detail;
        vm.imPortKLTDPopup = imPortKLTDPopup;
        vm.imPortCTTDPopup = imPortCTTDPopup;
        //		vm.fail = fail;
        vm.doAppovar = doAppovar;
        vm.item = {};
        vm.worksheets = [];
        vm.constr = {};
        vm.constr.constructId = ProposalEvaluation.getItem().constructId;
        vm.constr.contractCode = ProposalEvaluation.getItem().contractCode;
        vm.dataItem = {};
        vm.dataItem.constructionId = ProposalEvaluation.getItem().constructId;
        //		vm.dataItem.checkUpdate = false;
        vm.getroledirectorb = {};
        vm.getroledirectorb.constructId = ProposalEvaluation.getItem().constructId;
        vm.getroledirectorb.contractCode = ProposalEvaluation.getItem().contractCode;
        
        vm.employee = {};
        vm.employee.constructId = ProposalEvaluation.getItem().constructId;
        checkPoFound();
        vm.gridCommon = [{
            title: "Mã",
            field: "code",
            width: 120
        }, {
            title: "Tên",
            field: "fullName",
            width: 120
        }];

        fillDataTable([]);
        fillDataTableInsideContract([]);
        fillDataTableOutsideContract([]);
        fillDataTableSumContract([]);
        fillDataTableFileCalculate([]);
        //		fetchAll();
        //		fetchAllInsideContract();
        //		fetchAllOutsideContract();
        //		fetchAllSumContract();


        // THủ trưởng
        vm.getroledirectora = {};
        vm.getroledirectora.roleid = Constant.ROLE_ID["employee_roleID_CDT_DDPN"];
        vm.getroledirectora.constructId = ProposalEvaluation.getItem().constructId;
        vm.getroledirectora.contractCode = ProposalEvaluation.getItem().contractCode;
        // Giám sát
        vm.getrolemonitor = {};
        vm.getrolemonitor.roleid = Constant.ROLE_ID["employee_roleID_CDT_GSTC"];
        vm.getrolemonitor.constructId = ProposalEvaluation.getItem().constructId;
        vm.getrolemonitor.contractCode = ProposalEvaluation.getItem().contractCode;
        // Phụ trách giám sát
        vm.getroleinChargeMonitor = {};
        vm.getroleinChargeMonitor.roleid = Constant.ROLE_ID["employee_roleID_CDT_PTGST"];
        vm.getroleinChargeMonitor.constructId = ProposalEvaluation.getItem().constructId;
        vm.getroleinChargeMonitor.contractCode = ProposalEvaluation.getItem().contractCode;
        // Giám đốc
        vm.getroledirectorb = {};
        vm.getroledirectorb.roleid = Constant.ROLE_ID["employee_roleID_DT_GDNT"];
        vm.getroledirectorb.constructId = ProposalEvaluation.getItem().constructId;
        vm.getroledirectorb.contractCode = ProposalEvaluation.getItem().contractCode;
        // Phụ trách thi công
        vm.getrolebinChargeConstruct = {};
        vm.getrolebinChargeConstruct.roleid = Constant.ROLE_ID["employee_roleID_DT_PTTC"];
        vm.getrolebinChargeConstruct.constructId = ProposalEvaluation.getItem().constructId;
        vm.getrolebinChargeConstruct.contractCode = ProposalEvaluation.getItem().contractCode;

        vm.estimatesWorkItemIdList = [];
        vm.userId=0;
        function getemployeeRandom() {
            estimatesTableDetailService.getemployeeRandom(vm.constr).then(
                function(d) {
//                    vm.item.createdEvaluatePerId = d.plain()[0].id;
//                    vm.item.createdEvaluatePerName = d.plain()[0].fullName;
                    vm.item.checkEvaluatePerId = d.plain()[1].id;
                    vm.item.checkEvaluatePerName = d.plain()[1].fullName;
//                    vm.item.sendPersonId = d.plain()[2].id;
//                    vm.item.sendPersonName = d.plain()[2].fullName;
                },
                function(errResponse) {
                    toastr.error('Error while fetching InvDetailInOut');
                });
        }
        
        function getcreatedEvaluatePerId() {
            estimatesTableDetailService.checkPoFound(vm.constr).then(
                function(d) {
                    vm.item.createdEvaluatePerId = d.plain()[0].evaluatePersonId;
                    vm.item.createdEvaluatePerName = d.plain()[0].fullNameEmployee;
                },
                function(errResponse) {
                    toastr.error('Error while fetching InvDetailInOut');
                });
        }
        function getdefaulDirector() {
            estimatesTableDetailService.getemployeeRandom(vm.getroledirectorb).then(
                function(d) {
                    vm.item.brepresentativeId = d.plain()[0].id;
                    vm.item.brepresentativeName = d.plain()[0].fullName;
                },
                function(errResponse) {
                    toastr.error('Error while fetching InvDetailInOut');
                });
        }
        
        function getdefaulDirectorA() {
            estimatesTableDetailService.getemployeeRandom(vm.getroledirectora).then(
                function(d) {
                    vm.item.aDirectorId = d.plain()[0].id;
                    vm.item.aDirectorName = d.plain()[0].fullName;
                },
                function(errResponse) {
                    toastr.error('Error while fetching InvDetailInOut');
                });
        }
        
        function getsendPersonName(){
        	estimatesTableDetailService.getsendPersonName(vm.userId).then(
                    function(d) {
                    	vm.item.sendPersonId=d.sendPersonId;
                    	vm.item.sendPersonName=d.sendPersonName;
                        });
        	
        }
        
        function checkPoFound() {
            estimatesTableDetailService.checkPoFound(vm.constr).then(
                function(d) {
                    if (d.length === 0) {
                        vm.hide = true;
                        vm.appro = true;
                        toastr.warning('Chưa có đề nghị nào được tạo!');
                    } else {
                    	 vm.userId=d.plain()[0].createdUserId
                        if (d.plain()[0].evaluateStatus === 1) {
                            if (vm.user.srvUser.catEmployeeId == d.plain()[0].evaluatePersonId) {
                                vm.hide = false;

                                if (ProposalEvaluation.getCheckGoTo()) {
                                    vm.showDetail = true;
                                    vm.isCreateNew = true;
                                    vm.locationGroup = {};
                                    vm.dataItem.checkUpdate = true;
                                    fetchAllInsideContract();
                                    fetchAllOutsideContract();
                                    fetchAllSumContract();
                                    getCalculateFile();
                                    vm.item = {};
                                    mapdata();
                                    getemployeeRandom();
                                    getdefaulDirector();
                                    getsendPersonName();
                                    getdefaulDirectorA();
                                    getcreatedEvaluatePerId();
                                    fetchAll();
                                } else {
                                    vm.showDetail = false;
                                    fetchAll();
                                }
                            } else {
                                vm.hide = true;
                                vm.appro = true;
                                fetchAll();
                                toastr.warning('Bạn không phải là người được giao việc thẩm định!');
                            }

                        } else if (d.plain()[0].evaluateStatus === 2) {
                            fetchAll();
                        } else {
                            vm.hide = true;
                            vm.appro = true;
                            toastr.warning('Đề nghị chưa được giao thẩm định');
                        }
                    }
                },
                function(errResponse) {
                    toastr.error('Error while fetching InvDetailInOut');
                });
        }
        mapdata();

        function add() {
            $(".k-invalid-msg").hide();
            vm.showDetail = true;
            vm.isCreateNew = true;
            vm.checkCTTD = true;
            vm.locationGroup = {};
            addRequired();
            vm.dataItem.checkUpdate = true;
            fetchAllInsideContract();
            fetchAllOutsideContract();
            fetchAllSumContract();
            getCalculateFile();
            vm.item = {};
            mapdata();
            getemployeeRandom();
            getdefaulDirector();
            getsendPersonName();
            getdefaulDirectorA();
            getcreatedEvaluatePerId();
        }

        function detail() {
            if (vm.estimateWorkTables.select().length > 0) {
                vm.showDetail = true;
                vm.dataItem.checkUpdate = false;
            } else {
                toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
            }
        }

        function edit(id) {
            console.log('id to be edited', id);
            for (var i = 0; i < vm.locationGroup.length; i++) {
                if (vm.locationGroup[i].id === id) {
                    vm.locationGroup = angular.copy(vm.locationGroup[i]);
                    break;
                }
            }
        }

        function showGrid() {
        	if (vm.showDetail == false) {
        	  estimatesTableDetailService.checkPoFound(vm.constr).then(
                      function(d) {
                                  if (vm.user.srvUser.catEmployeeId != d.plain()[0].evaluatePersonId) {
                                      vm.disableForm = true;
                                      vm.checkCTTD = false;
                                      detail();
                                      fetchAllInsideContract();
                                      fetchAllOutsideContract();
                                      fetchAllSumContract();
                                      getCalculateFile();
                                      if(d.plain()[0].statusCa !=2){
                                      toastr.warning('Người được giao việc thẩm định mới được sửa!');
                                      }
                                  }else{
                                		
                                      $(".k-invalid-msg").hide();
                                      if (vm.showDetail == false) {
                                      	addRequired();
                                          if (vm.item.statusCa == 1 || vm.item.statusCa == 2) {
                                              vm.disableForm = true;
                                              vm.checkCTTD = false;
                                              detail();
                                              fetchAllInsideContract();
                                              fetchAllOutsideContract();
                                              fetchAllSumContract();
                                              getCalculateFile();
                                          } else {
                                              vm.disableForm = false;
                                              vm.checkCTTD = true;
                                              detail();
                                              fetchAllInsideContract();
                                              fetchAllOutsideContract();
                                              fetchAllSumContract();
                                              getCalculateFile();
                                          }
                                      } else {
                                          vm.showDetail = false;
                                          vm.checkCTTD = true;
                                          // reloadCLocationGroups();
                                      }
                                  }
                      },
                      function(errResponse) {
                          toastr.error('Error while fetching InvDetailInOut');
                      });
        	}else {
                vm.showDetail = false;
                vm.checkCTTD = true;
                // reloadCLocationGroups();
            }
        }
        vm.array = [];

        function save() {
        	vm.validator._inputSelector = $rootScope.formLevel2.INPUTSELECTOR;
			 if (!vm.validator.validate()) return;	
            if (vm.disabledPheDuyet) {
                dopheDuyet();
                vm.disabledPheDuyet = false;
            } else {
                for (var i = 0; i < vm.contentWorkInsideContract.dataSource.data().length; i++) {
                    vm.array.push(vm.contentWorkInsideContract.dataSource.data()[i]);
                }
                for (var i = 0; i < vm.contentWorkOutsideContract.dataSource.data().length; i++) {
                    vm.array.push(vm.contentWorkOutsideContract.dataSource.data()[i]);
                }

                if (vm.array.length > 0) {
                    vm.item.listAcc = vm.array;
                    vm.item.listEstDetail = vm.worksheets;
                    //					vm.item.listEstimatesWorkItemId = vm.estimatesWorkItemIdList;
//                    for (var i = 0; i < vm.item.listEstDetail.length; i++) {
//                        console.log(vm.item.listEstDetail[i].estimatesWorkItemId);
//                    }
                    vm.item.createdUserId = Constant.user.srvUser.userId;
                    
                    if(vm.item.createdEvaluatePerId == '' && vm.item.createdEvaluatePerId === null){
                    	toastr.warning("Không để trống ");
                    	return;
                    }else{
                        estimatesTableDetailService.addAll(vm.item).then(function(d) {
                            if (d.error) {
                                toastr.error(gettextCatalog.getString(d.error));
                                return;
                            }
                            
//                            vm.item.createdEvaluatePerId = d.plain()[0].id;
//                            vm.item.createdEvaluatePerName = d.plain()[0].fullName;
//                            vm.item.checkEvaluatePerId = d.plain()[1].id;
//                            vm.item.checkEvaluatePerName = d.plain()[1].fullName;
//                            vm.item.sendPersonId = d.plain()[2].id;
//                            vm.item.sendPersonName = d.plain()[2].fullName;
                            
                            
                            toastr.success("Lưu thành công!");
                            vm.item.listEstDetail=[];
                            vm.worksheets = [];
                            vm.estimatesWorkItemIdList = [];
                            vm.array = [];
                            vm.item.listAcc=[];
                            fetchAll();
                            vm.showDetail = false;
                            vm.appro = false;
                            var statusSave = true;
                            $rootScope.$broadcast("estimateTableDetailController.statusSave", statusSave);
                        }, function(e) {
                            toastr.error(gettextCatalog.getString("Lỗi khi thêm mới!"));
                            return;
                        });
                    }
                    
                
                } else {
                    toastr.warning("Không thể thêm mới khi không có công việc nào được nghiệm thu!");
                }
            }
        }

        function remove() {
        	
        	  estimatesTableDetailService.checkPoFound(vm.constr).then(
                      function(d) {
                                	  if (vm.item.statusCa == 1 || vm.item.statusCa == 2) {
                                          toastr.error("Không thể xóa bản ghi trình duyệt hoặc đã duyệt");
                                      } else {
                                    	  if (vm.user.srvUser.catEmployeeId != d.plain()[0].evaluatePersonId) {
                                              toastr.warning('Người được giao việc thẩm định mới được xóa!');
                                              return;
                                          }
                                          if (vm.estimateWorkTables.select().length > 0 && confirm("Xác nhận xóa ? ")) {
                                              estimatesTableDetailService.updateIsActive(vm.item.detailSettlementEvaluateId).then(function() {
                                                  toastr.success("Xóa thành công");
                                                  fetchAll("remove");
                                                  var statusDel = true;
                                                  $rootScope.$broadcast("estimateTableDetailController.statusDel", statusDel);
                                                  vm.hide = false;
                                                  vm.showDetail = false;
                                              }, function(e) {
                                                  if (e.status == 302) {
                                                	  toastr.warning("Bản ghi trình duyệt hoặc đã duyệt không được xóa!");
                                                  } else {
                                                      toastr.error("Lỗi~~~~~~~~~~~~~~~~~~~");
                                                  }
                                              });
                                          }
                                      }	
                      },
                      function(errResponse) {
                          toastr.error('Error while fetching InvDetailInOut');
                      });
        }


        vm.exportdata = {};


        vm.imPortKLTD = imPortKLTD;

        function imPortKLTD(formData) {
        	
        	
        	Restangular.one(Constant.FILE_SERVICE_TEMP + "?folder=kltd").withHttpConfig(
					{
						transformRequest : angular.identity
					}).customPOST(formData, '', undefined, {
				'Content-Type' : 'multipart/form-data'
			}).then(function(successResponse) {
				console.log(successResponse);
				if(successResponse.length>0){
					
					var item = {pathFile:successResponse[0]}
					
					 estimatesTableDetailService.importKL(item).then(function(d) {
	                   	 if (d.error) {
	                         toastr.error(gettextCatalog.getString(d.error));
	                         $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                         return;
	                     }
	                   	 
	                     var temp3 = d.plain();
	                   	
	                     var count=0;
	                        // Grid Nội dung công việc trong hợp đồng
	                        var gridData = vm.contentWorkInsideContract.dataSource.data();
	                        for (var gridItem in gridData) {
	                            for (var x in temp3) {

	                                // update total money
	                                if (/*gridData[gridItem].workItemCode === temp3[x].workItemCode &&*/ gridData[gridItem].workItemName === temp3[x].workItemName) {
	                                    gridData[gridItem].evaluateQuantity = temp3[x].evaluateQuantity;
	                                    gridData[gridItem].evaluateComments = temp3[x].evaluateComments;
	                                    count++;
	                                }
	                            }
	                            //Thành tiền hợp đồng
	                            if (null != gridData[gridItem].workAmount && null != gridData[gridItem].unitPrice) {
	                                gridData[gridItem].allotmentAmount = gridData[gridItem].unitPrice * gridData[gridItem].workAmount;
	                            }
	                            //Thành tiền đề nghị quyết toán
	                            if (null != gridData[gridItem].executeQuantity && null != gridData[gridItem].settleUnitPrice) {
	                                gridData[gridItem].advanceAmount = gridData[gridItem].executeQuantity * gridData[gridItem].settleUnitPrice;
	                            }
	                            //Thành tiền phê duyệt quyết toán
	                            if (null != gridData[gridItem].evaluateQuantity && null != gridData[gridItem].evaluateUnitPrice) {
	                                gridData[gridItem].approvalAmount = gridData[gridItem].evaluateQuantity * gridData[gridItem].evaluateUnitPrice;
	                            }
	                            //Chênh lệch giữa phê duyệt và đề nghị
	                            if (null != gridData[gridItem].approvalAmount && null != gridData[gridItem].advanceAmount) {
	                                gridData[gridItem].revaluationAmount = gridData[gridItem].approvalAmount - gridData[gridItem].advanceAmount;
	                            }
	                        }
	                        vm.contentWorkInsideContract.dataSource.data(gridData);
	                        vm.contentWorkInsideContract.refresh();

	                        // Grid Nội dung công việc phát sinh ngoài hợp đồng
	                        gridData = vm.contentWorkOutsideContract.dataSource.data();
	                        for (var gridItem in gridData) {
	                            for (var x in temp3) {

	                                // update total money
	                                if (/*gridData[gridItem].workItemCode === temp3[x].workItemCode &&*/ gridData[gridItem].workItemName === temp3[x].workItemName) {
	                                    gridData[gridItem].evaluateQuantity = temp3[x].evaluateQuantity;
	                                    gridData[gridItem].evaluateComments = temp3[x].evaluateComments;
	                                    count++;
	                                }
	                            }
	                            //Thành tiền hợp đồng
	                            if (null != gridData[gridItem].workAmount && null != gridData[gridItem].unitPrice) {
	                                gridData[gridItem].allotmentAmount = gridData[gridItem].unitPrice * gridData[gridItem].workAmount;
	                            }
	                            //Thành tiền đề nghị quyết toán
	                            if (null != gridData[gridItem].executeQuantity && null != gridData[gridItem].settleUnitPrice) {
	                                gridData[gridItem].advanceAmount = gridData[gridItem].executeQuantity * gridData[gridItem].settleUnitPrice;
	                            }
	                            //Thành tiền phê duyệt quyết toán
	                            if (null != gridData[gridItem].evaluateQuantity && null != gridData[gridItem].evaluateUnitPrice) {
	                                gridData[gridItem].approvalAmount = gridData[gridItem].evaluateQuantity * gridData[gridItem].evaluateUnitPrice;
	                            }
	                            //Chênh lệch giữa phê duyệt và đề nghị
	                            if (null != gridData[gridItem].approvalAmount && null != gridData[gridItem].advanceAmount) {
	                                gridData[gridItem].revaluationAmount = gridData[gridItem].approvalAmount - gridData[gridItem].advanceAmount;
	                            }
	                        }
	                        vm.contentWorkOutsideContract.dataSource.data(gridData);
	                        vm.contentWorkOutsideContract.refresh();
	                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                        if(count===0){
	                        	 toastr.error("Không có công việc phù hợp!");
	                        } else {
	                        toastr.success("Import khối lượng thẩm định thành công");
	                        }
	                }, function() {
	                        toastr.error(gettextCatalog.getString("Có lỗi xảy ra!"));
	                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                    })

				}
			});
        	


        }


        vm.imPortCTTD = imPortCTTD

        function imPortCTTD(formData) {
        	
        	
        	Restangular.one(Constant.FILE_SERVICE_TEMP + "?folder=chiettinh").withHttpConfig(
					{
						transformRequest : angular.identity
					}).customPOST(formData, '', undefined, {
				'Content-Type' : 'multipart/form-data'
			}).then(function(successResponse) {
				console.log(successResponse);
				if(successResponse.length>0){
					
					var item = {pathFile:successResponse[0],constructionId:vm.item.constructionId}
					
					 estimatesTableDetailService.importCT(item).then(function(d) {
	                   	 if (d.error) {
	                         toastr.error(gettextCatalog.getString(d.error));
	                         $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                         return;
	                     }
	                        // data from server
	                   	var size=vm.contentWorkInsideContract.dataSource.data().length+vm.contentWorkOutsideContract.dataSource.data().length
	                        var temp3 = d.plain();
	                        if(size>temp3.length){
		                   		if(!confirm("Bạn đang import không đẩy đủ chiết tính của các công việc. Bạn muốn tiếp tục? ")){
		                   			return;
		                   		}
		                   	 }
	                        var count=0;
	                        vm.worksheets = [];
	                        vm.estimatesWorkItemIdList=[];
	                        // Grid Nội dung công việc trong hợp đồng
	                        var gridData = vm.contentWorkInsideContract.dataSource.data();
	                        for (var x in temp3) {
	                        for (var gridItem=0; gridItem < gridData.length;gridItem++) {
	                                // update total money
	                                if (/*gridData[gridItem].workItemCode === temp3[x].workItemCode &&*/ gridData[gridItem].workItemName === temp3[x].workItemName) {
	                                    gridData[gridItem].evaluateUnitPrice = typeof parseFloat(_.find(temp3[x].estimatesDetailAnalysts, function(item) {
	                                        return item.costIngredientCode == 'Gxd' && item.progressType == 4
	                                    }).totalMoney) !== "undefined" ?  parseFloat(_.find(temp3[x].estimatesDetailAnalysts, function(item) {
	                                        return item.costIngredientCode == 'Gxd' && item.progressType == 4
	                                    }).totalMoney) : null;

	                                    vm.estimatesWorkItemIdList.push(gridData[gridItem].estimatesWorkItemId);
	                                    for (var i = 0; i < temp3[x].estimatesDetailAnalysts.length; i++) {
	                                        var tranfer = {};
	                                        tranfer.costIngredientCode = temp3[x].estimatesDetailAnalysts[i].costIngredientCode;
	                                        tranfer.costIngredientName = temp3[x].estimatesDetailAnalysts[i].costIngredientName;
	                                        tranfer.totalMoney = temp3[x].estimatesDetailAnalysts[i].totalMoney;
	                                        tranfer.totalMoneyFormula = temp3[x].estimatesDetailAnalysts[i].totalMoneyFormula;
	                                        tranfer.type = temp3[x].estimatesDetailAnalysts[i].type;
	                                        tranfer.progressType = temp3[x].estimatesDetailAnalysts[i].progressType;
	                                        tranfer.unit = temp3[x].estimatesDetailAnalysts[i].unit;
	                                        tranfer.unitPrice = temp3[x].estimatesDetailAnalysts[i].unitPrice;
	                                        tranfer.normIndex = temp3[x].estimatesDetailAnalysts[i].normIndex;
	                                        tranfer.coefficient = temp3[x].estimatesDetailAnalysts[i].coefficient;
	                                        tranfer.estimatesWorkItemId = gridData[gridItem].estimatesWorkItemId;
	                                        vm.worksheets.push(tranfer);
	                                    }
	                                    
	                                    count++;
	                                }

	                                //Thành tiền hợp đồng
		                            if (null != gridData[gridItem].workAmount && null != gridData[gridItem].unitPrice) {
		                                gridData[gridItem].allotmentAmount = gridData[gridItem].unitPrice * gridData[gridItem].workAmount;
		                            }
		                            //Thành tiền đề nghị quyết toán
		                            if (null != gridData[gridItem].executeQuantity && null != gridData[gridItem].settleUnitPrice) {
		                                gridData[gridItem].advanceAmount = gridData[gridItem].executeQuantity * gridData[gridItem].settleUnitPrice;
		                            }
		                            //Thành tiền phê duyệt quyết toán
		                            if (null != gridData[gridItem].evaluateQuantity && null != gridData[gridItem].evaluateUnitPrice) {
		                                gridData[gridItem].approvalAmount = gridData[gridItem].evaluateQuantity * gridData[gridItem].evaluateUnitPrice;
		                            }
		                            //Chênh lệch giữa phê duyệt và đề nghị
		                            if (null != gridData[gridItem].approvalAmount && null != gridData[gridItem].advanceAmount) {
		                                gridData[gridItem].revaluationAmount = gridData[gridItem].approvalAmount - gridData[gridItem].advanceAmount;
		                            }
	                            }

	                           
	                        }
	                        vm.contentWorkInsideContract.dataSource.data(gridData);
	                        vm.contentWorkInsideContract.refresh();

	                        // Grid Nội dung công việc phát sinh ngoài hợp đồng
	                        gridData = vm.contentWorkOutsideContract.dataSource.data();
	                        for (var x in temp3) {

	                        for (var gridItem=0; gridItem < gridData.length;gridItem++) {
	                            
	                                // update total money
	                                if (/*gridData[gridItem].workItemCode === temp3[x].workItemCode &&*/ gridData[gridItem].workItemName === temp3[x].workItemName) {
	                                	gridData[gridItem].evaluateUnitPrice = typeof parseFloat(_.find(temp3[x].estimatesDetailAnalysts, function(item) {
	                                        return item.costIngredientCode == 'Gxd' && item.progressType == 4
	                                    }).totalMoney) !== "undefined" ?  parseFloat(_.find(temp3[x].estimatesDetailAnalysts, function(item) {
	                                        return item.costIngredientCode == 'Gxd' && item.progressType == 4
	                                    }).totalMoney) : null;
	                                    vm.estimatesWorkItemIdList.push(gridData[gridItem].estimatesWorkItemId);
	                                    for (var i = 0; i < temp3[x].estimatesDetailAnalysts.length; i++) {
	                                        var tranfer = {};
	                                        tranfer.costIngredientCode = temp3[x].estimatesDetailAnalysts[i].costIngredientCode;
	                                        tranfer.costIngredientName = temp3[x].estimatesDetailAnalysts[i].costIngredientName;
	                                        tranfer.totalMoney = temp3[x].estimatesDetailAnalysts[i].totalMoney;
	                                        tranfer.totalMoneyFormula = temp3[x].estimatesDetailAnalysts[i].totalMoneyFormula;
	                                        tranfer.type = temp3[x].estimatesDetailAnalysts[i].type;
	                                        tranfer.progressType = temp3[x].estimatesDetailAnalysts[i].progressType;
	                                        tranfer.unit = temp3[x].estimatesDetailAnalysts[i].unit;
	                                        tranfer.unitPrice = temp3[x].estimatesDetailAnalysts[i].unitPrice;
	                                        tranfer.normIndex = temp3[x].estimatesDetailAnalysts[i].normIndex;
	                                        tranfer.coefficient = temp3[x].estimatesDetailAnalysts[i].coefficient;
	                                        tranfer.estimatesWorkItemId = gridData[gridItem].estimatesWorkItemId;
	                                        vm.worksheets.push(tranfer);
	                                    }
	                                    count++;
	                                }
	                                //Thành tiền hợp đồng
		                            if (null != gridData[gridItem].workAmount && null != gridData[gridItem].unitPrice) {
		                                gridData[gridItem].allotmentAmount = gridData[gridItem].unitPrice * gridData[gridItem].workAmount;
		                            }
		                            //Thành tiền đề nghị quyết toán
		                            if (null != gridData[gridItem].executeQuantity && null != gridData[gridItem].settleUnitPrice) {
		                                gridData[gridItem].advanceAmount = gridData[gridItem].executeQuantity * gridData[gridItem].settleUnitPrice;
		                            }
		                            //Thành tiền phê duyệt quyết toán
		                            if (null != gridData[gridItem].evaluateQuantity && null != gridData[gridItem].evaluateUnitPrice) {
		                                gridData[gridItem].approvalAmount = gridData[gridItem].evaluateQuantity * gridData[gridItem].evaluateUnitPrice;
		                            }
		                            //Chênh lệch giữa phê duyệt và đề nghị
		                            if (null != gridData[gridItem].approvalAmount && null != gridData[gridItem].advanceAmount) {
		                                gridData[gridItem].revaluationAmount = gridData[gridItem].approvalAmount - gridData[gridItem].advanceAmount;
		                            }
	                            }

	                        }
	                        vm.contentWorkOutsideContract.dataSource.data(gridData);
	                        vm.contentWorkOutsideContract.refresh();
	                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                        if(count===0){
	                        	 toastr.error("Không có công việc phù hợp!");
	                        } else {
	                        toastr.success("Import chiết tính thành công");
	                        }
	                }, function() {
	                        toastr.error(gettextCatalog.getString("Có lỗi xảy ra!"));
	                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                    })

				}
			});
        	


        }

        function doAppovar() {
            // TODO
        }
        // dữ liệu Tìm kiếm gửi

        function mapdata() {
            vm.item.constructId = ProposalEvaluation.getItem().constructId;
            vm.item.constrtName = ProposalEvaluation.getItem().constrtName;
            vm.item.contractCode = ProposalEvaluation.getItem().contractCode;
            vm.item.contractName = ProposalEvaluation.getItem().contractName;
            vm.item.constrtCode = ProposalEvaluation.getItem().constrtCode;
            vm.item.signed_date = ProposalEvaluation.getItem().signed_date;
            vm.item.stationAddress = ProposalEvaluation.getItem().stationAddress;
            vm.item.constrtAddress = ProposalEvaluation.getItem().constrtAddress;
            // vm.item.evaluatePersonId = establishDetailedSettlementProposalService.getEvaluatePersonId().evaluatePersonId;
            //            if(vm.item.evaluatePersonId === Constant.getUser().srvUser.catEmployeeId){
            //            	vm.hideRemove = false;
            //                vm.hideSave = false;
            //                vm.hideAll = false;
            //            }else{
            //            	vm.hideAll = true;
            //                vm.hideRemove = true;
            //                vm.hideSave = true;
            //            }
        }
        // END dữ liệu Tìm kiếm gửi



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
                        { field: "allotmentAmount", aggregate: "sum" },
                        { field: "advanceAmount", aggregate: "sum" },
                        { field: "approvalAmount", aggregate: "sum" },
                        { field: "deviantApproval", aggregate: "sum" }
                    ],
                },
                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                columns: [{
                        title: gettextCatalog.getString("STT"),
                        field: "stt",
                        template: dataItem => $("#sumEvaluateContract").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        width: 60,
                        editor: nonEditor,
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
                        width: 200,
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:left;" },
                        footerTemplate: "<center>TỔNG GIÁ TRỊ</center>",
                    },
                    {
                        title: gettextCatalog.getString("Thành tiền theo hợp đồng (sau VAT)"),
                        field: "allotmentAmount",
                        width: 150,
                        editor: nonEditor,
//                        template: "<div>#= kendo.toString(allotmentAmount, 'n3') #</div>",
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                        footerAttributes: {
                            style: "text-align: right;"
                        },
//                        footerTemplate: "<div>#= kendo.toString(sum, 'n3') #</div>",
                        template :  "# if(allotmentAmount != null){ #" + "#= kendo.toString(allotmentAmount, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                        footerTemplate: "# if(sum != null){ #" + "#= kendo.toString(sum, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                    },
                    {
                        title: gettextCatalog.getString("Thành tiền đề nghị (sau VAT)"),
                        field: "advanceAmount",
                        width: 150,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: { style: "text-align:right;" },
                        footerAttributes: {
                            style: "text-align: right;"
                        },
                        /*width: 150,*/
//                        template: "<div>#= kendo.toString(advanceAmount, 'n3') #</div>",
//                        footerTemplate: "<div>#= kendo.toString(sum, 'n3') #</div>",
                        template :  "# if(advanceAmount != null){ #" + "#= kendo.toString(advanceAmount, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                        footerTemplate: "# if(sum != null){ #" + "#= kendo.toString(sum, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                    },
                    {
                        title: gettextCatalog.getString("Thành tiền thẩm định (sau VAT)"),
                        field: "approvalAmount",
                        width: 150,
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center;  white-space: normal" },
                        attributes: { style: "text-align:right;" },
                        footerAttributes: {
                            style: "text-align: right;"
                        },
//                        template: "<div>#= kendo.toString(approvalAmount, 'n3') #</div>",
//                        footerTemplate: "<div>#= kendo.toString(sum, 'n3') #</div>",
                        template :  "# if(approvalAmount != null){ #" + "#= kendo.toString(approvalAmount, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                        footerTemplate: "# if(sum != null){ #" + "#= kendo.toString(sum, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                    },
                    {
                        title: gettextCatalog.getString("Chênh lệch thành tiền thẩm định và đề nghị"),
                        field: "deviantApproval",
                        width: 200,
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center;  white-space: normal" },
                        attributes: { style: "text-align:right;" },
                        footerAttributes: {
                            style: "text-align: right;"
                        },
//                        template: "<div>#= kendo.toString(deviantApproval, 'n3') #</div>",
//                        footerTemplate: "<div>#= kendo.toString(sum, 'n3') #</div>",
                        template :  "# if(deviantApproval != null){ #" + "#= kendo.toString(deviantApproval, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                        footerTemplate: "# if(sum != null){ #" + "#= kendo.toString(sum, 'n3') #" + "# } " + "else { # " + "#= kendo.toString(0, 'n3') #"+ "#} #",
                    }
                ]
            });
        }

        function fetchAllSumContract() {
            estimatesTableDetailService.getAllEstimatesWorkContract(vm.dataItem).then(
                function(d) {
                    refreshGridSumContract(d.plain());
                },
                function(errResponse) {
                    console.error('Error while fetching InsideContract');
                });
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

        function fillDataTable(data) {
            vm.validatorOptions = kendoConfig.get('validatorOptions');
            vm.gridOptions = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: data,

                editable: false,
                change: onChange,
                noRecords: true,
                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                dataBound: function(e) {
                    var grid = $("#estimateWorkTablesEva").data("kendoGrid");
                    if (!vm.rowIndex) {
                        grid.select("tr:eq(0)");
                    } else {
                        grid.select("tr:eq(" + vm.rowIndex + ")");
                    }
                },
                columns: [{
                    title: gettextCatalog.getString("STT"),
                    field: "stt",
                    template: dataItem => $("#estimateWorkTablesEva").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                    width: 70,
                    editor: nonEditor,
                    headerAttributes: {
                        style: "text-align:center;"
                    },
                    attributes: {
                        style: "text-align:center;"
                    },
                }, {
                    title: gettextCatalog
                        .getString("Mã thẩm định"),
                    field: "code",
                    width: 180,
                    editor: nonEditor,
                    headerAttributes: {
                        style: "text-align:center;"
                    },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog
                        .getString("Mã công trình"),
                    field: "constrtCode",
                    width: 180,
                    editor: nonEditor,
                    headerAttributes: {
                        style: "text-align:center;"
                    },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog
                        .getString("Mã hợp đồng"),
                    field: "contractCode",
                    width: 180,
                    editor: nonEditor,
                    headerAttributes: {
                        style: "text-align:center;"
                    },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog
                        .getString("Tên hợp đồng"),
                    field: "contractName",
                    width: 180,
                    editor: nonEditor,
                    headerAttributes: {
                        style: "text-align:center;"
                    },
                    attributes: {
                        style: "text-align:left;"
                    },
                }, {
                    title: gettextCatalog
                        .getString("Trạng thái"),
                    field: "statusCa",
                    template: "# if(statusCa == 0){ #" +
                        "#= 'Soạn thảo' #" +
                        "# } else if (statusCa==1) { # " +
                        "#= 'Trình duyệt' #" +
                        "# } else if (statusCa==2) { # " +
                        "#= 'Đã duyệt' #" +
                        "# } else if (statusCa==3) {#" +
                        "#=  'Từ chối duyệt' #" +
                        "#} #",
                    width: 120,
                    editor: nonEditor,
                    headerAttributes: {
                        style: "text-align:center;"
                    },
                    attributes: {
                        style: "text-align:left;", class:"statusColumn"
                    },
                }]
            });
        }

        function onChange(e) {
            if (vm.estimateWorkTables.select().length > 0) {
                var tr = vm.estimateWorkTables.select().closest("tr");
                var dataItem = vm.estimateWorkTables.dataItem(tr);
                vm.item = dataItem;
                mapdata();
                estimatesTableDetailService.setItem(vm.item);
                // Gán giá trị để trình duyệt
                vm.theApproval.code = dataItem.code;
                vm.theApproval.constructId = dataItem.constructId;
                vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
                vm.theApproval.stringEmployee = dataItem.createdEvaluatePerId + ',' + dataItem.checkEvaluatePerId + ',' + dataItem.sendPersonId + ',' + dataItem.brepresentativeId+','+dataItem.aDirectorId;
                vm.theApproval.roleName = ["Người lập của chủ đầu tư", "Người kiểm tra của chủ đầu tư", "Người đề nghị quyết toán", "Giám đốc","Đại diện chủ đầu tư"];
                vm.theApproval.roleId = ["0", vm.getrolemonitor.roleid, "0", vm.getroledirectorb.roleid,vm.getroledirectora.roleid];
                vm.theApproval.isSign = 'theApproval';
                theApproval.setItem(vm.theApproval);

                vm.approDTO.employeeId = Constant.getUser().srvUser.catEmployeeId;
                vm.approDTO.detailSettlementEvaluateId = dataItem.detailSettlementEvaluateId;
                vm.approDTO.constrCompReMapId = dataItem.constrCompReMapId;
            }
        }

        vm.onGridTableEstimateListDetail = function(e, sel, dataItem) {
            vm.bean = dataItem;
            vm.rowIndex = sel.selected.rowIndex;
            onChange();
        }

        function fillDataTableInsideContract(data) {
            var dataSource = new kendo.data.DataSource({
                pageSize: 20,
                data: data,
                //autoSync: true,
                schema: {
                    model: {
                        fields: {
                            evaluateQuantity: {
                                //type: "number",
                                validation: {
                                    required: true,
                                    recoveryValidation: function(input) {
                                        if (input.val() < 0) {
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
            });

            vm.gridOptionsInsideContract = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: dataSource,
                change: onChangeInsideContract,
                noRecords: true,

                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                edit: function(e) {
                    var input = e.container.find(".k-input");
                    var value = input.val();
                    input.keyup(function() {
                        value = input.val();
                    });
                    $("[name='evaluateQuantity']", e.container).blur(function() {
                        var tr = vm.contentWorkInsideContract.select().closest("tr");
                        var approvalAmount = vm.contentWorkInsideContract.dataItem(tr).approvalAmount;
                        vm.contentWorkInsideContract.dataItem(tr).approvalAmount = vm.contentWorkInsideContract.dataItem(tr).evaluateQuantity * vm.contentWorkInsideContract.dataItem(tr).evaluateUnitPrice;
                        if (approvalAmount != vm.contentWorkInsideContract.dataItem(tr).approvalAmount) {
                            vm.contentWorkInsideContract.refresh();
                        }
                    });
                },
                columns: [{
                    title: gettextCatalog.getString("STT"),
                    field: "stt",
                    template: dataItem => $("#contentWorkOutsideContractEva").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                    width: 80,
                    editor: nonEditor,
                    headerAttributes: {
                        style: "text-align:center;"
                    },
                    attributes: {
                        style: "text-align:center;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Nội dung công việc</div>"),
                    field: "workItemName",
                    width: 180,
                    editor: nonEditor,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:left;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Đơn vị tính</div>"),
                    field: "unit",
                    width: 150,
                    editor: nonEditor,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:left;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Khối lượng hợp đồng</div>"),
//                    field: "workAmount",
                    width: 180,
//                    format: "{0:n3}",
//                    decimals: 3,
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.workAmount)) {
                        	dataItem.workAmount = parseFloat(Number(dataItem.workAmount).toFixed(3));
                            return kendo.toString(dataItem.workAmount, 'n3');
                        }else
                        {
                        	dataItem.workAmount = 0;
                        return kendo.toString(dataItem.workAmount, 'n3');}
                    },
//                    template:"<div>#= kendo.toString(workAmount, 'n3') #</div>",
                    editor: nonEditor,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Khối lượng đề nghị quyết toán</div>"),
//                    field: "executeQuantity",
                    width: 180,
//                    decimals: 3,
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.executeQuantity)) {
                        	dataItem.executeQuantity = parseFloat(Number(dataItem.executeQuantity).toFixed(3));
                            return kendo.toString(dataItem.executeQuantity,'n3');
                        }else{
                        	dataItem.executeQuantity = 0;
                        	kendo.toString(dataItem.executeQuantity,'n3');
                        	}
                    },
//                    format: "{0:n3}",
//                    template:"<div>#= kendo.toString(executeQuantity, 'n3') #</div>",
                    editor: nonEditor,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Khối lượng phê duyệt quyết toán</div>"),
                    field: 'evaluateQuantity',
                    width: 180,
                    format: "{0:n3}",
                    decimals: 3,
                    template: function(dataItem) {
                        if ($.isNumeric(dataItem.evaluateQuantity)) {
                        	dataItem.evaluateQuantity = parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
                            return parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
                        }
                        else {dataItem.evaluateQuantity = 0 ;
                        return 0;}
                    },
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right; border: 1px groove  ;"
                    },
                },
                {
                    title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Diễn dải thẩm định</div>"),
                    field: 'evaluateComments',
                    width: 180,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right; border: 1px groove  ;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Đơn giá hợp đồng</div>"),
//                    field: "unitPrice",
                    template:"<div>#= kendo.toString(unitPrice, 'n3') #</div>",
                    width: 180,
                    editor: nonEditor,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Đơn giá đề nghị quyết toán</div>"),
//                    field: "settleUnitPrice",
                    width: 180,
                    editor: nonEditor,
                    template:"<div>#= kendo.toString(settleUnitPrice, 'n3') #</div>",
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Đơn giá phê duyệt QT</div>"),
//                    field: "evaluateUnitPrice",
                    //							template: "<a><span style='display: block;' ng-click='vm.worksheet()'>#=evaluateUnitPrice#</span></a>",
                    width: 180,
                    template:"<div>#= kendo.toString(evaluateUnitPrice, 'n3') #</div>",
                    editor: nonEditor,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Thành tiền hợp đồng</div>"),
//                    field: "allotmentAmount",
                    template: function(dataItem) {
                    	
                    	 if ($.isNumeric(dataItem.workAmount * dataItem.unitPrice)) {
                                return kendo.toString(dataItem.workAmount * dataItem.unitPrice,'n3');
                            }
                            return kendo.toString(0,'n3');
                    	
                       
                    },
//                    template:"<div>#= kendo.toString(unitPrice, 'n3') #</div>",
                    width: 180,
                    editor: nonEditor,
//                    format: "{0:n3}",
//                    decimals: 3,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Thành tiền đề nghị quyết toán</div>"),
//                    field: "advanceAmount",

                    
                    template: function(dataItem) {
                    	
                   	 if ($.isNumeric(dataItem.executeQuantity * dataItem.settleUnitPrice)) {
                                return kendo.toString(dataItem.executeQuantity * dataItem.settleUnitPrice,'n3');
                            }
                            return  kendo.toString(0,'n3');
                   	
                      
                   },
                    width: 180,
                    editor: nonEditor,
//                    format: "{0:n3}",
//                    decimals: 3,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Thành tiền phê duyệt quyết toán</div>"),
//                    field: "approvalAmount",
                    template: function(dataItem) {
                    	
                   	 if ($.isNumeric(dataItem.evaluateQuantity * dataItem.evaluateUnitPrice)) {
                                return kendo.toString(dataItem.evaluateQuantity * dataItem.evaluateUnitPrice,'n3');
                            }
                     return  kendo.toString(0,'n3');
                   },
                    width: 180,
                    editor: nonEditor,
//                    format: "{0:n3}",
//                    decimals: 3,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }, {
                    title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Chênh lệch giữa phê duyệt và đề nghị</div>"),
//                    field: "revaluationAmount",

                    
                    template: function(dataItem) {
                    	
                      	 if ($.isNumeric((dataItem.evaluateQuantity * dataItem.evaluateUnitPrice) - (dataItem.executeQuantity * dataItem.settleUnitPrice))) {
	                                return kendo.toString((dataItem.evaluateQuantity * dataItem.evaluateUnitPrice) - (dataItem.executeQuantity * dataItem.settleUnitPrice),'n3');
	                            }
                      	  return  kendo.toString(0,'n3');
                      },
                    width: 180,
                    editor: nonEditor,
//                    format: "{0:n3}",
//                    decimals: 3,
                    headerAttributes: { style: "text-align:center; white-space: normal" },
                    attributes: {
                        style: "text-align:right;"
                    },
                }
            ]
            });
        }

        function onChangeInsideContract() {
            // TODO
            vm.temp = {};
            if (vm.contentWorkInsideContract.select().length > 0) {
                var tr = vm.contentWorkInsideContract.select().closest("tr");
                var dataItem = vm.contentWorkInsideContract.dataItem(tr);
                vm.temp = dataItem;
                estimatesTableDetailService.setEstimatesWorkItemId(vm.temp.estimatesWorkItemId);
            }
        }

        function fillDataTableOutsideContract(data) {
            var dataSource = new kendo.data.DataSource({
                pageSize: 20,
                data: data,
                //autoSync: true,
                schema: {
                    model: {
                        fields: {
                            evaluateQuantity: {
                                //type: "number",
                                validation: {
                                    required: true,
                                    recoveryValidation: function(input) {
                                        if (input.val() < 0) {
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
            });

            vm.gridOptionsOutsideContract = kendoConfig.getGridOptions({
                autoBind: true,
                dataSource: dataSource,
                change: onChangeOutsideContract,
                noRecords: true,
                messages: {
                    noRecords: gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
                },
                edit: function(e) {
                    var input = e.container.find(".k-input");
                    var value = input.val();
                    input.keyup(function() {
                        value = input.val();
                    });
                    $("[name='evaluateQuantity']", e.container).blur(function() {
                        var tr = vm.contentWorkOutsideContract.select().closest("tr");
                        var approvalAmount = vm.contentWorkOutsideContract.dataItem(tr).approvalAmount;
                        vm.contentWorkOutsideContract.dataItem(tr).approvalAmount = vm.contentWorkOutsideContract.dataItem(tr).evaluateQuantity * vm.contentWorkOutsideContract.dataItem(tr).evaluateUnitPrice;
                        if (approvalAmount != vm.contentWorkOutsideContract.dataItem(tr).approvalAmount) {
                            vm.contentWorkOutsideContract.refresh();
                        }
                    });
                    $("[name='executeQuantity']", e.container).attr("readonly", true);
                    
                },
                columns: [{
                        title: gettextCatalog.getString("STT"),
                        field: "stt",
                        template: dataItem => $("#contentWorkOutsideContractEva").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
                        width: 80,
                        editor: nonEditor,
                        headerAttributes: {
                            style: "text-align:center;"
                        },
                        attributes: {
                            style: "text-align:center;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Nội dung công việc</div>"),
                        field: "workItemName",
                        width: 180,
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:left;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Đơn vị tính</div>"),
                        field: "unit",
                        width: 150,
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:left;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Khối lượng hợp đồng</div>"),
//                        field: "workAmount",
                        width: 180,
//                        format: "{0:n3}",
//                        decimals: 3,
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.workAmount)) {
                            	dataItem.workAmount = parseFloat(Number(dataItem.workAmount).toFixed(3));
                                return kendo.toString(dataItem.workAmount, 'n3');
                            }else
                            {
                            	dataItem.workAmount = 0;
                            return kendo.toString(dataItem.workAmount, 'n3');}
                        },
//                        template:"<div>#= kendo.toString(workAmount, 'n3') #</div>",
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Khối lượng đề nghị quyết toán</div>"),
//                        field: "executeQuantity",
                        width: 180,
//                        decimals: 3,
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.executeQuantity)) {
                            	dataItem.executeQuantity = parseFloat(Number(dataItem.executeQuantity).toFixed(3));
                                return kendo.toString(dataItem.executeQuantity,'n3');
                            }else{
                            	dataItem.executeQuantity = 0;
                            	kendo.toString(dataItem.executeQuantity,'n3');
                            	}
                        },
//                        format: "{0:n3}",
//                        template:"<div>#= kendo.toString(executeQuantity, 'n3') #</div>",
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Khối lượng phê duyệt quyết toán</div>"),
                        field: 'evaluateQuantity',
                        width: 180,
                        format: "{0:n3}",
                        decimals: 3,
                        template: function(dataItem) {
                            if ($.isNumeric(dataItem.evaluateQuantity)) {
                            	dataItem.evaluateQuantity = parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
                                return parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
                            }
                            else {dataItem.evaluateQuantity = 0 ;
                            return 0;}
                        },
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right; border: 1px groove  ;"
                        },
                    },
                    {
                        title: gettextCatalog.getString("<div style='margin: 00px 6px 4px 25px !important; color: #000000 !important; font-weight: 600; font-size: 13px;'>Diễn dải thẩm định</div>"),
                        field: 'evaluateComments',
                        width: 180,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right; border: 1px groove  ;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Đơn giá hợp đồng</div>"),
//                        field: "unitPrice",
                        template:"<div>#= kendo.toString(unitPrice, 'n3') #</div>",
                        width: 180,
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Đơn giá đề nghị quyết toán</div>"),
//                        field: "settleUnitPrice",
                        width: 180,
                        editor: nonEditor,
                        template:"<div>#= kendo.toString(settleUnitPrice, 'n3') #</div>",
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Đơn giá phê duyệt QT</div>"),
//                        field: "evaluateUnitPrice",
                        //							template: "<a><span style='display: block;' ng-click='vm.worksheet()'>#=evaluateUnitPrice#</span></a>",
                        width: 180,
                        template:"<div>#= kendo.toString(evaluateUnitPrice, 'n3') #</div>",
                        editor: nonEditor,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Thành tiền hợp đồng</div>"),
//                        field: "allotmentAmount",
                        template: function(dataItem) {
                        	
                        	 if ($.isNumeric(dataItem.workAmount * dataItem.unitPrice)) {
	                                return kendo.toString(dataItem.workAmount * dataItem.unitPrice,'n3');
	                            }
	                            return kendo.toString(0,'n3');
                        	
                           
                        },
//                        template:"<div>#= kendo.toString(unitPrice, 'n3') #</div>",
                        width: 180,
                        editor: nonEditor,
//                        format: "{0:n3}",
//                        decimals: 3,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Thành tiền đề nghị quyết toán</div>"),
//                        field: "advanceAmount",

                        
                        template: function(dataItem) {
                        	
                       	 if ($.isNumeric(dataItem.executeQuantity * dataItem.settleUnitPrice)) {
	                                return kendo.toString(dataItem.executeQuantity * dataItem.settleUnitPrice,'n3');
	                            }
	                            return  kendo.toString(0,'n3');
                       	
                          
                       },
                        width: 180,
                        editor: nonEditor,
//                        format: "{0:n3}",
//                        decimals: 3,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Thành tiền phê duyệt quyết toán</div>"),
//                        field: "approvalAmount",
                        template: function(dataItem) {
                        	
                       	 if ($.isNumeric(dataItem.evaluateQuantity * dataItem.evaluateUnitPrice)) {
	                                return kendo.toString(dataItem.evaluateQuantity * dataItem.evaluateUnitPrice,'n3');
	                            }
                         return  kendo.toString(0,'n3');
                       },
                        width: 180,
                        editor: nonEditor,
//                        format: "{0:n3}",
//                        decimals: 3,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }, {
                        title: gettextCatalog.getString("<div style='margin: 00px 20px 4px 20px !important; color: #000000 !important; font-weight: 600; font-size: 13px;display: block;min-height: 18px; line-height: 18px; padding: .5em .6em .4em .6em; overflow: hidden; text-overflow: ellipsis;'>Chênh lệch giữa phê duyệt và đề nghị</div>"),
//                        field: "revaluationAmount",
 
                        
                        template: function(dataItem) {
                        	
                          	 if ($.isNumeric((dataItem.evaluateQuantity * dataItem.evaluateUnitPrice) - (dataItem.executeQuantity * dataItem.settleUnitPrice))) {
   	                                return kendo.toString((dataItem.evaluateQuantity * dataItem.evaluateUnitPrice) - (dataItem.executeQuantity * dataItem.settleUnitPrice),'n3');
   	                            }
                          	  return  kendo.toString(0,'n3');
                          },
                        width: 180,
                        editor: nonEditor,
//                        format: "{0:n3}",
//                        decimals: 3,
                        headerAttributes: { style: "text-align:center; white-space: normal" },
                        attributes: {
                            style: "text-align:right;"
                        },
                    }
                ]

            });
        }

        function onChangeOutsideContract() {
            // TODO
            if (vm.contentWorkOutsideContract.select().length > 0) {
                var tr = vm.contentWorkOutsideContract.select().closest("tr");
                var dataItem = vm.contentWorkOutsideContract.dataItem(tr);
                vm.temp = dataItem;
                estimatesTableDetailService.setEstimatesWorkItemId(vm.temp.estimatesWorkItemId);
            }
        }

        // InsideContract
        function fetchAllInsideContract() {
            estimatesTableDetailService.fetchAllInsideContract(vm.dataItem).then(
                function(d) {
                    refreshGridInsideContract(d.plain());
                },
                function(errResponse) {
                    console.error('Error while fetching InvDetailInOut');
                });
        }

        function refreshGridInsideContract(d) {
            var grid = vm.contentWorkInsideContract;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }
        
      //ngoccx
        //huy trinh duyet
  		vm.cancelApprovalBtn= function(){
  			var grid = vm.estimateWorkTables;
  			if (grid.select() == null || grid.select().length == 0) {
  				toastr.warning("Cần chọn dòng trước khi thực hiện thao tác này!");
  				return;
  			}
  			vm.item.tableName = 'DETAIL_SETTLEMENT_EVALUATE';
  			vm.item.tableId = vm.item.detailSettlementEvaluateId;
  			vm.item.tableIdField = 'DETAIL_SETTLEMENT_EVALUATE_ID';
  			if(vm.item.statusCa == 1){
  				if(vm.item.createdUserId != Constant.user.srvUser.userId){
  					toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
  					return;
  				}else{
  					if(confirm('Xác nhận hủy trình duyệt')){
  						estimatesTableDetailService.cancelAprroval(vm.item).then(function() {
  						status = true;
  						$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
  						fetchAll();
  				toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
  			}, function(){
  			toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
  			return;
  		});}
  					}
  			}else{
  				toastr.warning("Chỉ trình duyệt mới được hủy trình duyệt");
  			}
  		}

        // OutsideContract
        function fetchAllOutsideContract() {
            estimatesTableDetailService.fetchAllOutsideContract(vm.dataItem).then(
                function(d) {
                    refreshGridOutsideContract(d.plain());
                },
                function(errResponse) {
                    console.error('Error while fetching InvDetailInOut');
                });
        }

        function refreshGridOutsideContract(d) {
            var grid = vm.contentWorkOutsideContract;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        //
        function fetchAll(mode) {
            estimatesTableDetailService.fetchAll(vm.constr).then(
                function(d) {
                    if (d.length === 0 && mode === "remove") {
                        refreshGrid(d.plain());
                        return true;
                    }
                    if (d.length === 0) {
                        refreshGrid(d.plain());
                        toastr.success('Bạn có thể tạo mới bản ghi để đồng ý thẩm định!');
                        vm.hide = false;
                    } else {
                        if (d.plain()[0].statusCa === 1) {
                            vm.appro = true;
                            vm.doAppro = false;
                            vm.cancelAppro = false;
                        }
                        if (d.plain()[0].statusCa === 2) {
                            vm.appro = true;
                        }
                        if (d.plain()[0].statusCa === 0) {
                            vm.appro = false
                        }
                        if (d.plain()[0].statusCa === 3) {
                            vm.appro = true;
                        }
                        if ((d.plain()[0].brepresentativeId === vm.user.srvUser.catEmployeeId) ||
                            (d.plain()[0].checkEvaluatePerId === vm.user.srvUser.catEmployeeId) ||
                            (d.plain()[0].createdEvaluatePerId === vm.user.srvUser.catEmployeeId) ||
                            (d.plain()[0].sendPersonId === vm.user.srvUser.catEmployeeId) || 
                            (d.plain()[0].aDirectorId === vm.user.srvUser.catEmployeeId) ) {
                            vm.doAppro = false;
                            vm.cancelAppro = false;
                        } else {
                            vm.doAppro = true;
                            vm.cancelAppro = true;
                        }
                        refreshGrid(d.plain());
                        vm.hide = true;
                        return;
                    }
                },
                function(errResponse) {
                    console.error('Error while fetching InvDetailInOut');
                });
        }

        function refreshGrid(d) {
            var grid = vm.estimateWorkTables;
            if (grid) {
                grid.dataSource.data(d);
                grid.refresh();
            }
        }

        vm.expertise = expertise;
        vm.worksheet = worksheet;

        function expertise() {
            WindowService.open({
                options: {
                    modal: true,
                    title: "Bảng diễn dải khối lượng xây lắp",
                    visible: false,
                    width: '1000',
                    height: '550',
                    actions: ["Minimize", "Maximize", "Close"],
                    open: function() {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: "qlhc/estimateTableDetail/expertisePopup.html"

            });

        }

        function worksheet() {
        	WindowService.open({
                options: {
                    modal: true,
                    title: "Chiết tính đề nghị quyết toán",
                    visible: false,
                    width: '650',
                    height: '200',
                    actions: ["Minimize", "Maximize", "Close"],
                    open: function() {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: "qlhc/estimateTableDetail/worksheetPopup.html"

            });
        }

        function imPortKLTDPopup() {
        	WindowService.open({
                options: {
                    modal: true,
                    title: "Import khối lượng thẩm định",
                    visible: false,
                    width: '650',
                    height: '200',
                    actions: ["Minimize", "Maximize", "Close"],
                    open: function() {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: "qlhc/estimateTableDetail/khoiLuongThamDinhPopup.html"

            });
        }

        function imPortCTTDPopup() {
        	WindowService.open({
                options: {
                    modal: true,
                    title: "Import chiết tính thẩm định",
                    visible: false,
                    width: '650',
                    height: '200',
                    actions: ["Minimize", "Maximize", "Close"],
                    open: function() {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: "qlhc/estimateTableDetail/chietTinhThamDinhPopup.html"

            });
        }

        vm.cancelImport = cancelImport;

        function cancelImport() {
            $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
        }

        vm.exportListContact = function() {
            vm.item.constructionId = vm.item.constructId;
            // delete vm.item.constructId;
            estimatesTableDetailService.exportEstimateTable(vm.item).then(function(data) {
                window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
            });
        }

        vm.pheDuyet = function pheDuyet() {
            if (vm.estimateWorkTables.select().length > 0) {
                if (vm.item.statusCa == 2) {
                    toastr.warning("Bản ghi đã được phê duyệt!");
                    return;
                }
                if (vm.item.statusCa == 1) {
                    vm.disableForm = true;
                    vm.showDetail = true;
                    fetchAllInsideContract();
                    fetchAllOutsideContract();
                    fetchAllSumContract();
                    getCalculateFile();
                    vm.disabledPheDuyet = true;
                    vm.approDTO.statusCa = 2;
                } else {
                    toastr.warning(gettextCatalog.getString("Bản ghi chưa được trình duyệt!"));
                }
            } else {
                toastr.warning("Bạn cần chọn một bản ghi trước");
            }
        }

        vm.dopheDuyet = dopheDuyet;

        function dopheDuyet() {
            estimatesTableDetailService.appro(vm.approDTO).then(function(d) {
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
                    toastr.error("Không có quyền duyệt");
                }
                fetchAll();
                vm.showDetail = false;
            }, function(e) {
                toastr.error(gettextCatalog.getString("Lỗi khi phê duyệt!"));
                return;
            });
        }
        vm.sendBrowse = sendBrowse;

        function sendBrowse() {
            if (vm.item.statusCa === 0) {
            	vm.search.constructionId = ProposalEvaluation.getItem().constructId;
                vm.search.contractCode= ProposalEvaluation.getItem().contractCode;
                estimatesTableDetailService.exPortfull(vm.search).then(function(data) {
                	vm.theApproval.path = data.fileName;
	    			vm.theApproval.nameFile = 'BM-QT-02.pdf';
	    			goTo('THE_APPROVAL');
                }, function(e) {
                    toastr.error(gettextCatalog.getString("Lỗi khi trình duyệt!"));
                });
                
            } else {
                toastr.warning("Nếu trạng thái là soạn thảo thì đc trình duyệt!");
            }

        }

        $scope.$on("ChangeStatusApproval", function(event, result) {
            if (result) {
                //hàm refresh gridview
                fetchAll();
                vm.showDetail = true;
                vm.appro = true;
                vm.doAppro = false;
            }
        });

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
                // toastr.error(gettextCatalog.getString("Tài khoản đăng
                // nhập
                // hiện tại không được phép truy cập vào chức năng này!"));
            }
        }

        function nonEditor(container, options) {
            container.text(options.model[options.field]);
        }

        vm.search = {};
        vm.exPortCTTDPopup = function() {
            vm.search.constructionId = ProposalEvaluation.getItem().constructId;
            vm.search.contractCode= ProposalEvaluation.getItem().contractCode;
            estimatesTableDetailService.exPortfull(vm.search).then(function(d) {
            	if(d.error){
           		 toastr.error(gettextCatalog.getString(d.error));
           		 return;
            	}
                window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
                toastr.success("Export thành công");
            }, function(e) {
                toastr.error(gettextCatalog.getString("Chưa có dữ liệu export!"));
            });
        }
        //minhpvn
    	function addRequired(){			   
       	 document.getElementById("estimates_detail_txtCreatedEvaluatePerId").required = true;
    	 document.getElementById("estimates_detail_txtCheckEvaluatePerId").required = true;
    	 document.getElementById("estimates_detail_txtSendPersonId").required = true;
    	 document.getElementById("estimates_detail_txtBrepresentativeId").required = true;
		}
        $scope.$on("establishDetailedSettlementProposalService.evaluatePersonId", function(event, result) {
            if (result) {
                vm.item.evaluatePersonId = result;
                if (vm.item.evaluatePersonId === Constant.getUser().srvUser.catEmployeeId) {
                    vm.hideRemove = false;
                    vm.hideSave = false;
                } else {
                    vm.hideRemove = true;
                    vm.hideSave = true;
                }
            }
        });

        $scope.$on("importCT", function(event, formData) {
            imPortCTTD(formData);
        });

        $scope.$on("importKLTD", function(event, formData) {
            imPortKLTD(formData);
        });

//        $scope.$on("Goto.propo.evalua", function(event) {
//            vm.constr.constructId = ProposalEvaluation.getItem().constructId;
//            vm.showDetail = data;
//            checkPoFound();
//            //				fetchAll();
//        });
        
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
    }

})();