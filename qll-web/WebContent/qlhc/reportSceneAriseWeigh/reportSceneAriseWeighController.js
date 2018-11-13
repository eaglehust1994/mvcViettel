(function() {
	'use strict';
	var controllerId = 'reportSceneAriseWeighController';
	angular.module('MetronicApp').controller(controllerId,
			reportSceneAriseWeighController);

	function reportSceneAriseWeighController($scope, $rootScope,
			$timeout, Constant, reportSceneAriseWeighService, theApproval,
			gettextCatalog, kendoConfig, $kWindow,WindowService, CommonService,
			PopupConst, Restangular, RestEndpoint, ProposalEvaluation
			) {
		
		// //////////////////////////initial data/////////////////////////////
		var vm = this;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.viewDisable = false;
		vm.showApproval = false;
		vm.hideSave = true;
		vm.hideSaveAppro = true;
		vm.hideDel = false;
		vm.importBieuMau = importBieuMau;
		var checkonchange = 0;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.item = {};
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
	         if(item !== undefined){
	          vm.item = item;
	          reloadReportSceneAriseWeigh();
	         }else{
	          console.error("không nhận được dữ liệu!");
	         }
	    });
		vm.theApproval = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path:'',
				nameFile:'',
				roleId : [],
				roleName : []
			}
		vm.dateTimePickerConfig1 = {
		        format: "dd/MM/yyyy",
		        parseFormats: ["yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy')#"
		    };
		vm.mApproval = {};
		var getcatEmployeeId = Constant.user.srvUser.catEmployeeId;
		vm.mSceneGenerateWork = {};
		fillDataTable([]);
		vm.onChange = onChange;
		var itemNameData = [];
        var estimatesItemChildIdData = [];
        reportSceneAriseWeighService.getItemNameByConstrId(vm.item).then(function(d) {
    		if(d.plain().length > 0){
    			for( var i in d.plain() ) {
        		    if (d.plain().hasOwnProperty(i)){
        		    	var putItemName = {};
        		    	putItemName.estimatesItemChildId = d.plain()[i].estimatesItemChildId;
        		    	putItemName.itemName = d.plain()[i].itemName;
        		    	itemNameData.push(putItemName);
        		    }
        		}
        		for (var j = 0; j < itemNameData.length; j++) {
        			var putData = {};
        			putData.value = itemNameData[j].estimatesItemChildId;
        			putData.text = itemNameData[j].itemName;
        			estimatesItemChildIdData.push(putData);
    			}
    		} else{
    			itemNameData.push({estimatesItemChildId:0, itemName:"Không có hạng mục"});
        		estimatesItemChildIdData.push({value:0,text:"Không có hạng mục"});
    		}
        },
        function(){
            console.error('Error while fetching');
        });
        var getMultiDeleteList = [];
		//////////////////////////Combobox thanh phan tham gia//////////////////////////////////
		vm.role=[];
		var totalListEmployee = [], AdirectorId = [], AinChargeMonitorId = [], BdirectorId = [],
		BinChargeConstructId = [], CdesignDirectionId = [], CdesignManagerId = [];
		
		vm.adirectorId = [];
		vm.ainChargeMonitorId = [];
		vm.bdirectorId = [];
		vm.binChargeConstructId = [];
		vm.cdesignDirectionId = [];
		vm.cdesignManagerId = [];
		
		angular.element(document).ready(function () {
		reportSceneAriseWeighService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
			totalListEmployee = data.plain();
			reportSceneAriseWeighService.getRoleId().then(function(data) {
				//vm.role = data;
				for (var i = 0; i < totalListEmployee.length; i++) {
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){//2
						AdirectorId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_PTGST"]){//4
						AinChargeMonitorId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]){//3
						BdirectorId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_PTTC"]){//1
						BinChargeConstructId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_DDTV"]){//6
						CdesignDirectionId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_CNTK"]){//7
						CdesignManagerId.push(totalListEmployee[i]);
					}
				}
				vm.adirectorId = AdirectorId;
				vm.ainChargeMonitorId = AinChargeMonitorId;
				vm.bdirectorId = BdirectorId;
				vm.binChargeConstructId = BinChargeConstructId;
				vm.cdesignDirectionId = CdesignDirectionId;
				vm.cdesignManagerId = CdesignManagerId;
				
				if(AdirectorId.length > 0) {
					vm.mSceneGenerateWork.adirectorId = AdirectorId[0].userId;
				}
				if(AinChargeMonitorId.length > 0) {
					vm.mSceneGenerateWork.ainChargeMonitorId = AinChargeMonitorId[0].userId;
				}
				if(BdirectorId.length > 0) {
					vm.mSceneGenerateWork.bdirectorId = BdirectorId[0].userId;
				}
				if(BinChargeConstructId.length > 0) {
					vm.mSceneGenerateWork.binChargeConstructId = BinChargeConstructId[0].userId;
				}
				if(CdesignDirectionId.length > 0) {
					vm.mSceneGenerateWork.cdesignDirectionId = CdesignDirectionId[0].userId;
				}
				if(CdesignManagerId.length > 0) {
					vm.mSceneGenerateWork.cdesignManagerId = CdesignManagerId[0].userId;
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
		//////////////////////////END//////////////////////////////////
		////////////////////////////////Grid Area///////////////////////////////
		function refreshGrid(d) {
          	var grid = vm.reportSceneAriseWeighGrid;
         	if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}	
        }
		function reloadReportSceneAriseWeigh(){
			reportSceneAriseWeighService.doSearchSceneGenerateWork(vm.item).then(function(d) {
					fillDataTable(d.plain());
					refreshGrid(d.plain());
					$('[name="gridchkselectall"]').prop( "checked", false );
                },
                function(){
                    console.error('Error while fetching');
                }
            );
        }
		reloadReportSceneAriseWeigh();
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistrsaw").checked == true){
				document.getElementById("checkalllistrsaw").checked = false;
			}
		}
		vm.handleCheck1 = function(item){
			if(document.getElementById("checkalllistrsaw1").checked == true){
				document.getElementById("checkalllistrsaw1").checked = false;
			}
		}
		// Hien thi danh sach
		function fillDataTable(data){
			vm.gridListOptions = kendoConfig
				.getGridOptions({
					autoBind : true,
					dataSource : data,
					change: onChange,
					noRecords : true,
					pageable: {
			            refresh: false,
			            pageSizes: [10, 15, 20, 25],
			            pageSize: 20,
			            messages: {
			                display: " {0} - {1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang",
			                empty: "Không có kết quả hiển thị"
			            }
			         },
					editable: false,
					messages : {
						noRecords : gettextCatalog
								.getString("Không có kết quả trong trang")
					},
					columns : [
							{
								title : gettextCatalog
										.getString("STT"),
								field : "STT",
								template: dataItem => $("#mainReportSceneAriseWeighGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								width : 60,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},
							{
								title : "<input type='checkbox' id='checkalllistrsaw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								template : "<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
								width : 35,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Mã biên bản"),
								field : "code",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Mã công trình"),
								field : "constrtCode",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Mã hợp đồng"),
								field : "contractCode",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Tên hợp đồng"),
								field : "contractName",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Trạng thái"),
								field : "statusCa",
								template : function (data) {
									if(data.statusCa === 0)
										return "Soạn thảo";
									if(data.statusCa === 1)
										return "Trình duyệt";
									if(data.statusCa === 2)
										return "Đã duyệt";
									if(data.statusCa === 3)
										return "Từ chối duyệt";
									return '';
								},
								width : 100,
								headerAttributes: {style:"text-align:center;"},
								attributes: { style: "text-align:left;" , class:"statusColumn"},
							}]
				});
		}
		$("#mainReportSceneAriseWeighGrid").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");
		////////////////////////////////Handle event////////////////////////////////////
		function onChange() {
			var grid = vm.reportSceneAriseWeighGrid;
			checkonchange = 0;
			if (grid.select().length > 0) {
				var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				vm.mSceneGenerateWork = dataItem;
				///////////////////////////////////////Trinh duyet//////////////////////////////////
				vm.theApproval.code= dataItem.code ;
				vm.theApproval.constructId = dataItem.constructId;
				vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
				vm.theApproval.stringEmployee = dataItem.binChargeConstructId 
												+ ","  + dataItem.cdesignManagerId 
												+ "," + dataItem.ainChargeMonitorId 
												+ "," + dataItem.bdirectorId
												+ "," + dataItem.cdesignDirectionId 
												+ "," + dataItem.adirectorId;
				vm.theApproval.roleName = ["Phụ trách thi công trực tiếp (Nhà thầu thi công)",
				                           "Chủ nhiệm thiết kế (Đơn vị thiết kế)",
				                           "Phụ trách giám sát thi công (Chủ đầu tư)",
				                           "Giám đốc (Nhà thầu thi công)",
				                           "Giám đốc (Đơn vị thiết kế)",
				                           "Thủ trưởng đơn vị (Chủ đầu tư)"];
				//1-7-4-3-6-2
				vm.theApproval.roleId = [Constant.ROLE_ID["employee_roleID_DT_PTTC"],Constant.ROLE_ID["employee_roleID_TVTK_CNTK"],
				                         Constant.ROLE_ID["employee_roleID_CDT_PTGST"],Constant.ROLE_ID["employee_roleID_DT_GDNT"],
				                         Constant.ROLE_ID["employee_roleID_TVTK_DDTV"],Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
				vm.theApproval.isSign = 'theApproval';
				checkonchange = checkonchange +1;
				theApproval.setItem(vm.theApproval);
			}
		}
		// phe duyet
		vm.approval = function(){			
			var grid = vm.reportSceneAriseWeighGrid;
			// Check select
        	if (grid.select() === null || grid.select().length === 0 || vm.mSceneGenerateWork.sceneGenerateWorkId === null) {
        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
        		return;
        	}
        	
			if(vm.mSceneGenerateWork.statusCa === 1){
				vm.showDetail = true;
				vm.showApproval = true;
				vm.viewDisable = true;
				vm.hideSaveAppro = false;
				vm.hideSave = true;
				vm.hideDel = true;
				vm.mApproval.statusCa = 1;
				reloadReportSceneAriseWeighList([]);
			} else {
				toastr.warning("Chỉ trạng thái trình duyệt mới được phê duyệt !");
			}
		}
		// trinh duyet
		vm.sendToApproval = function(){
			
			if(vm.mSceneGenerateWork.statusCa === 0){
				$('#loading').show();
				reportSceneAriseWeighService.exportFileReportSceneAriseWeigh(vm.mSceneGenerateWork).then(function(data){
	    			vm.theApproval.path = data.fileName;
	    			vm.theApproval.nameFile = 'BM-TCNT-11.pdf';
	    			theApproval.setItem(vm.theApproval);
	    			goTo('THE_APPROVAL');
	        	}).catch(function(){
	        		$('#loading').hide();
                	toastr.error(gettextCatalog.getString("Có lỗi xảy ra khi export !"));
                	return;
	        	}).finally(function(){
	        		$('#loading').hide();
	        	});
			} else {
				toastr.warning("Chỉ trạng thái soạn thảo mới được trình duyệt !");
			}
		}
		//ngoccx
	      //huy trinh duyet
			vm.cancelApprovalBtn= function(){
				var grid = vm.reportSceneAriseWeighGrid;
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
					return;
				}
				vm.mSceneGenerateWork.tableName = 'SCENE_GENERATE_WORK';
				vm.mSceneGenerateWork.tableId = vm.mSceneGenerateWork.sceneGenerateWorkId;
				vm.mSceneGenerateWork.tableIdField = 'SCENE_GENERATE_WORK_ID';
				if(vm.mSceneGenerateWork.statusCa == 1){
					if(vm.mSceneGenerateWork.createdUserId != Constant.user.srvUser.userId){
						toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
						return;
					}else{
						if(confirm('Xác nhận hủy trình duyệt')){
						reportSceneAriseWeighService.cancelAprroval(vm.mSceneGenerateWork).then(function() {
							status = true;
							$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
						reloadReportSceneAriseWeigh();
					toastr.success(gettextCatalog.getString("Đã hủy trình duyệt !"));
				}, function(){
				toastr.error(gettextCatalog.getString("Lỗi khi hủy trình duyệt!"));
				return;
			});}}
				}else{
					toastr.warning("Chỉ trình duyệt mới được hủy trình duyệt");
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
				toastr.error(gettextCatalog.getString("Tài khoản đăng nhập hiện tại không được phép truy cập vào chức năng này!"));
			}
		}
		
		$scope.$on("ChangeStatusApproval", function(event, statusApproval) {
			if(statusApproval){
				vm.showDetail = false;
				reloadReportSceneAriseWeigh();
			}
		});
        // Add
		vm.add = function(){
			////////////////////////////////////////////////////////////////
			reportSceneAriseWeighService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
				totalListEmployee = data.plain();
				reportSceneAriseWeighService.getRoleId().then(function(data) {
					//vm.role = data;
					for (var i = 0; i < totalListEmployee.length; i++) {
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){//2
							AdirectorId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_PTGST"]){//4
							AinChargeMonitorId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]){//3
							BdirectorId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_PTTC"]){//1
							BinChargeConstructId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_DDTV"]){//6
							CdesignDirectionId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_CNTK"]){//7
							CdesignManagerId.push(totalListEmployee[i]);
						}
					}
					vm.adirectorId = AdirectorId;
					vm.ainChargeMonitorId = AinChargeMonitorId;
					vm.bdirectorId = BdirectorId;
					vm.binChargeConstructId = BinChargeConstructId;
					vm.cdesignDirectionId = CdesignDirectionId;
					vm.cdesignManagerId = CdesignManagerId;
					
					if(AdirectorId.length > 0) {
						vm.mSceneGenerateWork.adirectorId = AdirectorId[0].userId;
					}
					if(AinChargeMonitorId.length > 0) {
						vm.mSceneGenerateWork.ainChargeMonitorId = AinChargeMonitorId[0].userId;
					}
					if(BdirectorId.length > 0) {
						vm.mSceneGenerateWork.bdirectorId = BdirectorId[0].userId;
					}
					if(BinChargeConstructId.length > 0) {
						vm.mSceneGenerateWork.binChargeConstructId = BinChargeConstructId[0].userId;
					}
					if(CdesignDirectionId.length > 0) {
						vm.mSceneGenerateWork.cdesignDirectionId = CdesignDirectionId[0].userId;
					}
					if(CdesignManagerId.length > 0) {
						vm.mSceneGenerateWork.cdesignManagerId = CdesignManagerId[0].userId;
					}
					
				})
				.catch(function() {
					console.error('getRoleId error');
				});
			  })
			  .catch(function() {
			    console.error('Gists error');
			});
			for (var i = 0; i < vm.adirectorId.length; i++) {
				if(vm.adirectorId[i].isCurrent === 1){
					vm.mSceneGenerateWork.amonitorId = vm.adirectorId[i].userId;
					break;
				} else {
					vm.mSceneGenerateWork.amonitorId = vm.adirectorId[0].userId;
					break;
				}
			}
			for (var i = 0; i < vm.ainChargeMonitorId.length; i++) {
				if(vm.ainChargeMonitorId[i].isCurrent === 1){
					vm.mSceneGenerateWork.ainChargeMonitorId = vm.ainChargeMonitorId[i].userId;
					break;
				} else {
					vm.mSceneGenerateWork.ainChargeMonitorId = vm.ainChargeMonitorId[0].userId;
					break;
				}
			}
			for (var i = 0; i < vm.bdirectorId.length; i++) {
				if(vm.bdirectorId[i].isCurrent === 1){
					vm.mSceneGenerateWork.bdirectorId = vm.bdirectorId[i].userId;
					break;
				} else {
					vm.mSceneGenerateWork.bdirectorId = vm.bdirectorId[0].userId;
					break;
				}
			}
			for (var i = 0; i < vm.binChargeConstructId.length; i++) {
				if(vm.binChargeConstructId[i].isCurrent === 1){
					vm.mSceneGenerateWork.binChargeConstructId = vm.binChargeConstructId[i].userId;
					break;
				} else {
					vm.mSceneGenerateWork.binChargeConstructId = vm.binChargeConstructId[0].userId;
					break;
				}
			}
			for (var i = 0; i < vm.cdesignDirectionId.length; i++) {
				if(vm.cdesignDirectionId[i].isCurrent === 1){
					vm.mSceneGenerateWork.cdesignDirectionId = vm.cdesignDirectionId[i].userId;
					break;
				} else {
					vm.mSceneGenerateWork.cdesignDirectionId = vm.cdesignDirectionId[0].userId;
					break;
				}
			}
			for (var i = 0; i < vm.cdesignManagerId.length; i++) {
				if(vm.cdesignManagerId[i].isCurrent === 1){
					vm.mSceneGenerateWork.cdesignManagerId = vm.cdesignManagerId[i].userId;
					break;
				} else {
					vm.mSceneGenerateWork.cdesignManagerId = vm.cdesignManagerId[0].userId;
					break;
				}
			}
			///////////////////////////////////////////////////////////////
			vm.viewDisable = false;
			vm.isCreateNew = true;
			vm.showDetail = true;
			vm.showApproval = false;
			vm.hideSave = false;
			vm.hideDel = true;
			vm.hideSaveAppro = true;
			var validatorForm = $("#formIDForm").kendoValidator().data("kendoValidator");
			validatorForm.hideMessages();
			var validatorList = $("#formIDList").kendoValidator().data("kendoValidator");
			validatorList.hideMessages();
			vm.mSceneGenerateWork = {};
			vm.mSceneGenerateWork.constructId = vm.item.constructId;
			fillDataTableList([]);
			refreshGridList([]);
		}
		////////////////////////////////////////
		// Show detail, edit
		vm.showGrid = function(){
			var grid = vm.reportSceneAriseWeighGrid;
			if(vm.isCreateNew === false){
				// Check select
	        	if (grid.select() === null || grid.select().length === 0 || vm.mSceneGenerateWork.sceneGenerateWorkId === null) {
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
			} else {
				vm.isCreateNew = false;
				reloadReportSceneAriseWeigh();
			}
			
			if (vm.showDetail === false) {
				if(vm.mSceneGenerateWork.statusCa === 1 || vm.mSceneGenerateWork.statusCa === 2){
					vm.viewDisable = true;
				} else {
					if(vm.mSceneGenerateWork.createdUserId === Constant.user.srvUser.userId){
						vm.viewDisable = false;
					} else{
						vm.viewDisable = true;
						toastr.warning("Bạn không được quyền sửa bản ghi này!");
					}
				}
				vm.showDetail = true;
				vm.hideSave = false;
				vm.hideDel = true;
	        	var validatorForm = $("#formIDForm").kendoValidator().data("kendoValidator");
				validatorForm.hideMessages();
				var validatorList = $("#formIDList").kendoValidator().data("kendoValidator");
				validatorList.hideMessages();
				reloadReportSceneAriseWeighList();
			} else {
				vm.showDetail = false;
				vm.showApproval = false;
				vm.hideSave = true;
				vm.hideSaveAppro = true;
				vm.hideDel = false;
				reloadReportSceneAriseWeigh();
			}
		}
		//validate time
		function verifyMyDate(d) {
		    var re = /^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$/;
		    return re.test(d);
		}
		function validateDateTime(){
			if(!verifyMyDate($("#signDateRep").val())){
				return false;
			}
			return true;
		}
		// Save
		vm.save = function() {
			if(vm.validator.validate()){
			var data = vm.reportSceneAriseWeighListGrid.dataSource.data();
			var objectList =[];
			for(var i =0; i<data.length ; i++){
				data[i].status = 0;
				data[i].type = 1;
				objectList.push(data[i]);
			}
			if(getMultiDeleteList.length > 0){
				for (var i = 0; i < getMultiDeleteList.length; i++) {
					reportSceneAriseWeighService.removeReportSceneAriseWeighList(getMultiDeleteList[i]).then(function() {
			    		//toastr.success("Đã xóa thành công");
				    }, function() {
				    	toastr.error("Có lỗi xảy ra trong quá trình xóa!");
				    });
				}
				getMultiDeleteList = [];
			}
			vm.mSceneGenerateWork.bbhtpsklList = objectList;
			if (vm.showDetail && vm.mSceneGenerateWork.statusCa !== 1 && vm.mSceneGenerateWork.statusCa !== 2){
				var adirectorIds = document.getElementById("adirectorId");
				vm.mSceneGenerateWork.adirectorId = adirectorIds.options[adirectorIds.selectedIndex].value;
				var ainChargeMonitorIds = document.getElementById("ainChargeMonitorId");
				vm.mSceneGenerateWork.ainChargeMonitorId = ainChargeMonitorIds.options[ainChargeMonitorIds.selectedIndex].value;
				var bdirectorIds = document.getElementById("bdirectorId");
				vm.mSceneGenerateWork.bdirectorId = bdirectorIds.options[bdirectorIds.selectedIndex].value;
				var binChargeConstructIds = document.getElementById("binChargeConstructId");
				vm.mSceneGenerateWork.binChargeConstructId = binChargeConstructIds.options[binChargeConstructIds.selectedIndex].value;
				var cdesignDirectionIds = document.getElementById("cdesignDirectionId");
				vm.mSceneGenerateWork.cdesignDirectionId = cdesignDirectionIds.options[cdesignDirectionIds.selectedIndex].value;
				var cdesignManagerIds = document.getElementById("cdesignManagerId");
				vm.mSceneGenerateWork.cdesignManagerId = cdesignManagerIds.options[cdesignManagerIds.selectedIndex].value;
				if(vm.mSceneGenerateWork.bbhtpsklList.length > 0){
					if(checkValidateList(vm.mSceneGenerateWork.bbhtpsklList)){
						if (vm.validator.validate()) {
							if(validateDateTime()){
								if (vm.mSceneGenerateWork.sceneGenerateWorkId !== undefined && vm.mSceneGenerateWork.sceneGenerateWorkId !== 0){
									if(vm.mSceneGenerateWork.createdUserId === Constant.user.srvUser.userId){
									} else{
										toastr.warning("Bạn không được quyền sửa bản ghi này!");
										return;
									}
									vm.mSceneGenerateWork.isActive = 1;
									
									//vm.mSceneGenerateWork.createdUserId = Constant.user.srvUser.userId;
									if(vm.mSceneGenerateWork.statusCa === 3){
										vm.mSceneGenerateWork.statusCa = 0;
									}
									reportSceneAriseWeighService.doCRUD(vm.mSceneGenerateWork).then(
											function(){
												vm.isCreateNew = false;
												vm.showDetail = false;
												vm.hideSave = true;
												vm.hideSaveAppro = true;
												vm.hideDel = false;
												reloadReportSceneAriseWeigh();
												toastr.success(gettextCatalog.getString("Lưu thay đổi thành công!"));
											}, function(errResponse) {
												if (errResponse.status === 409) {
					    		                	toastr.error(gettextCatalog.getString("Mã tồn tại"));
					    		                } else {
					    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo biên bản hiện trường !"));
					    		                }
					                			return;
											}
									)
									
								} else {
									vm.mSceneGenerateWork.createdUserId = Constant.user.srvUser.userId;
									vm.mSceneGenerateWork.statusCa = 0;
									vm.mSceneGenerateWork.isActive = 1;
									vm.mSceneGenerateWork.type = 1;
									vm.mSceneGenerateWork.createdDate = new Date();
									reportSceneAriseWeighService.doCRUD(vm.mSceneGenerateWork).then(
											function(result){
												vm.mSceneGenerateWork.sceneGenerateWorkId = result.sceneGenerateWorkId;
												vm.isCreateNew = false;
												vm.showDetail = false;
												vm.hideSave = true;
												vm.hideSaveAppro = true;
												vm.hideDel = false;
												reloadReportSceneAriseWeigh();
												toastr.success(gettextCatalog.getString("Lưu thành công!"));
											}, function() {
					    		                toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo biên bản hiện trường !"));
					                			return;
											}
									)
								}
							} else {
								toastr.error(gettextCatalog.getString("Bạn nhập sai định dạng ngày giờ !"));
							}
						}
					} else {
						toastr.error(gettextCatalog.getString("Danh sách khối lượng phát sinh nhập thiếu trường bắt buộc, vui lòng kiểm tra lại !"));
					}
				} else {
					toastr.error(gettextCatalog.getString("Bạn phải tạo ít nhất một khối lượng phát sinh !"));
					return;
				}
			} else {
				toastr.warning("Chỉ được lưu ở trạng thái soạn thảo hoặc từ chối duyệt !");
			}
			}
		}
		// Check box
		vm.chkSelectAll = function() {
	    	var grid = vm.reportSceneAriseWeighGrid;
	    	var gridList = vm.reportSceneAriseWeighListGrid;
	    	if(vm.showDetail === false){
	    		chkSelectAllBase(vm.chkAll, grid);
	    	} else {
	    		chkSelectAllBase(vm.chkAll, gridList);
	    	}
	    	
	    }
		// Delete
		vm.remove = function(){
			if(vm.mSceneGenerateWork.createdUserId === Constant.user.srvUser.userId){
				if(vm.showDetail){
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
				var grid = vm.reportSceneAriseWeighGrid;
				// Check select
	        	if (grid.select() === null || grid.select().length === 0) {
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
	        	var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				if(dataItem.statusCa === 0 || dataItem.statusCa === 3){
					if (grid.select().length > 0 && confirm('Xác nhận xóa')) {
						reportSceneAriseWeighService.updateIsActiveReportSceneAriseWeigh(dataItem.sceneGenerateWorkId).then(function(){
		            		toastr.success("Xóa thành công!");
		            		if (vm.showDetail){
		            		}else{
		            		reloadReportSceneAriseWeigh();
		            		}
			            }, function(errResponse) {
			            	if (errResponse.status === 302){
			            		toastr.error(gettextCatalog.getString("Bản ghi đang được sử dụng!"));
			            	}else{
			            		console.error("Có lỗi xảy ra trong quá trình xóa!");
			            	}
						});
					}
				} else {
					toastr.error(gettextCatalog.getString("Chỉ được xóa khi ở trạng thái soạn thảo hoặc từ chối duyệt !"));
				}
			} else{
				toastr.warning("Bạn không được quyền xóa bản ghi này!");
			}
		}
		
		// delete multiple record
		vm.multiDelete = function() {

			   var selectedRow = [];
			   var checkuser = [];
			   var selectedRowDele = 0;
			   var selectedRowDeOk = 0;
			   var grid = vm.reportSceneAriseWeighGrid;
			   grid.table.find("tr").each(function(idx, item) {
			    var row = $(item);
			    var checkbox = $('[name="gridcheckbox"]', row);
			    if (checkbox.is(':checked')) {
			     var dataItem = grid.dataItem(item);
			     if(dataItem.statusCa == 0 || dataItem.statusCa == 3)
			     {
			      selectedRow.push(dataItem);
			      checkuser.push(dataItem.createdUserId);
			      selectedRowDeOk = selectedRowDeOk + 1;
			     }else
			     {
			      selectedRowDele = selectedRowDele + 1;
			     }
			    }
			   });
			   if (selectedRowDele > 0 && selectedRowDeOk == 0) {
			    toastr.warning(gettextCatalog.getString("Trạng thái Trình Duyệt và Đã Duyệt không được xóa"));
			   }
			   else if (selectedRow.length > 0) {
			    vm.xoa = false;
			    vm.xoaFalse = false;
			    vm.xoaNotUser = false;
			    for (var i = 0; i < selectedRow.length; i++) {
			       if(checkuser[i] == Constant.user.srvUser.userId){
			        if(confirm('Xác nhận xóa')){
			        //distanceUnloadedMaterialsList.updateStatusDelete(selectedRow[i].sceneGenWorkListId);
			        reportSceneAriseWeighService.updateIsActiveReportSceneAriseWeigh(selectedRow[i].sceneGenerateWorkId).then(function() {
			         if(vm.xoa == false)
			         {
			          toastr.success("Đã xóa thành công");
			          vm.xoa = true;
			         }
			         if(selectedRowDele > 0 && vm.xoaFalse == false)
			            {
			             toastr.warning("Trạng thái trình duyệt và đã duyệt không được xóa.");
			             vm.xoaFalse = true;
			            }
			        reloadReportSceneAriseWeigh();
			         }, function(errResponse) {
			         if (errResponse.status == 302){
			             toastr.error(gettextCatalog.getString("Bản ghi đang được sử dụng"));
			         }else{
			             toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình xóa!"));
			         } 
			           });
			          }
			          }else if(!vm.xoaNotUser){
			           toastr.warning(gettextCatalog.getString("Bạn không phải người tạo không được xóa !"));
			           vm.xoaNotUser = true;
			      }
			    }
			   }else{toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));}
		} 
        ///////////////////////////////////Export File/////////////////////////////////
		vm.exportFilePDF = function(){

			var selectedRow = [];
			var grid = vm.reportSceneAriseWeighGrid;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.sceneGenerateWorkId);
				}
			});
			if(selectedRow.length == 0 && checkonchange == 0 )
			{
			    toastr.warning("Bạn cần chọn bản ghi để export !");
			}else{
				// khi xem chi tiết -> export .pdf
				if(checkonchange == 1 && vm.showDetail==true){
					$('#loading').show();
					reportSceneAriseWeighService.exportFileReportSceneAriseWeigh(vm.mSceneGenerateWork).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
//			    		checkonchange = 0;
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
				// chọn 1 bản ghi và tích nhiều bản ghi -> export zip.
				else if(checkonchange == 1 && selectedRow.length > 0)
				{
					$('#loading').show();
					reportSceneAriseWeighService.exportListReportSceneAriseWeigh(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
//			    	     checkonchange = 0;
			    	}).catch( function(){
			    	    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			    	    return;
			    	}).finally(function(){
			    	    $('#loading').hide();
			    	});
				}
				// tích chọn 1 bản ghi trở lên -> export zip.
				else if(selectedRow.length > 0)
				{
					$('#loading').show();
					reportSceneAriseWeighService.exportListReportSceneAriseWeigh(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
			    	}).catch( function(){
			    	    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			    	    return;
			    	}).finally(function(){
			    	    $('#loading').hide();
			    	});
				}
				// chọn 1 bản ghi -> export .pdf 
				else if(checkonchange == 1 && vm.showDetail == false)
				{
			       	$('#loading').show();
			       	reportSceneAriseWeighService.exportFileReportSceneAriseWeigh(vm.mSceneGenerateWork).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
//			    		checkonchange = 0;
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
				
				
				else
				{
					
					$('#loading').show();
					reportSceneGenerateService.exportFileReportSceneAriseWeigh(vm.mSceneGenerateWork).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
//			    		checkonchange = 0;
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
			}
		
		}
		vm.exportFileDOC = function(){

			var selectedRow = [];
			var grid = vm.reportSceneAriseWeighGrid;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.sceneGenerateWorkId);
				}
			});
			if(selectedRow.length == 0 && checkonchange == 0 )
			{
			    toastr.warning("Bạn cần chọn bản ghi để export !");
			}else{
				// khi xem chi tiết -> export .pdf
				if(checkonchange == 1 && vm.showDetail==true){
					$('#loading').show();
					reportSceneAriseWeighService.exportFileDocReportSceneAriseWeigh(vm.mSceneGenerateWork).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .DOC thành công!"));
//			    		checkonchange = 0;
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
				// chọn 1 bản ghi và tích nhiều bản ghi -> export zip.
				else if(checkonchange == 1 && selectedRow.length > 0)
				{
					$('#loading').show();
					reportSceneAriseWeighService.exportListDocReportSceneAriseWeigh(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .DOC thành công!"));
//			    	     checkonchange = 0;
			    	}).catch( function(){
			    	    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			    	    return;
			    	}).finally(function(){
			    	    $('#loading').hide();
			    	});
				}
				// tích chọn 1 bản ghi trở lên -> export zip.
				else if(selectedRow.length > 0)
				{
					$('#loading').show();
					reportSceneAriseWeighService.exportListDocReportSceneAriseWeigh(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .DOC thành công!"));
			    	}).catch( function(){
			    	    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			    	    return;
			    	}).finally(function(){
			    	    $('#loading').hide();
			    	});
				}
				// chọn 1 bản ghi -> export .pdf 
				else if(checkonchange == 1 && vm.showDetail == false)
				{
			       	$('#loading').show();
			       	reportSceneAriseWeighService.exportFileDocReportSceneAriseWeigh(vm.mSceneGenerateWork).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .DOC thành công!"));
//			    		checkonchange = 0;
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
				
				
				else
				{
					$('#loading').show();
					reportSceneAriseWeighService.exportFileDocReportSceneAriseWeigh(vm.mSceneGenerateWork).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .DOC thành công!"));
//			    		checkonchange = 0;
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
			}
		
		}
		/////////////////////////////////////////////
		//luu phe duyet
        vm.approvalSave = function(){
        	vm.mApproval.employeeId = getcatEmployeeId;
        	vm.mApproval.constrCompReMapId = vm.theApproval.constrCompReMapId;
        	vm.mApproval.sceneGenerateWorkId = vm.mSceneGenerateWork.sceneGenerateWorkId;
        	vm.mApproval.constructId = vm.item.constructId;
        	reportSceneAriseWeighService.approReportSceneAriseWeigh(vm.mApproval).then(function(check){
        		if(check === 1){
        			toastr.warning("Bạn chưa đến lượt duyệt !");
        		} else if(check === 2){
        			toastr.warning("Bạn đã duyệt rồi, không cần duyệt nữa !");
        		} else if(check === 3){
        			vm.showDetail = false;
    				vm.showApproval = false;
    				vm.hideSave = true;
    				vm.hideSaveAppro = true;
    				vm.hideDel = false;
    				reloadReportSceneAriseWeigh();
        			toastr.success(gettextCatalog.getString("Phê duyệt thành công!"));
        		} else if(check === 4){
        			vm.showDetail = false;
    				vm.showApproval = false;
    				vm.hideSave = true;
    				vm.hideSaveAppro = true;
    				vm.hideDel = false;
    				reloadReportSceneAriseWeigh();
        			toastr.success(gettextCatalog.getString("Từ chối duyệt thành công!"));
        		} else if(check === 5){
        			toastr.warning(gettextCatalog.getString("Bạn không được quyền duyệt !"));
        		}
            }, function(){
            	toastr.error(gettextCatalog.getString("Có lỗi xảy ra phê duyệt !"));
            	return;
            }
		);
        }
        ///////////////////////////////////////////////
		//////////////////////////////////////////List////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//minhpvn
		function importBieuMau(){
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
                templateUrl: "qlhc/reportSceneAriseWeigh/bieumauImport.html"

            });
		
	}
        //minhpvn
		$scope.$on("importBieuMau", function(event, item) {
            if (item === undefined) {
            	
            } else {
            	
            	var dataItem = vm.reportSceneAriseWeighListGrid.dataSource.data();
            	for(var i = 0 ; i <dataItem.length;i++){
            		item.push(dataItem[i]);
            	}
            	refreshGridList(item);
            	toastr.success("Import thành công");
            }
        });
		function refreshGridList(d) {
          	var grid = vm.reportSceneAriseWeighListGrid;
         	if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}	
        }
		function reloadReportSceneAriseWeighList(){
			reportSceneAriseWeighService.doSearchSceneGenerateWorkList(vm.mSceneGenerateWork).then(function(d) {
					fillDataTableList(d.plain());
					refreshGridList(d.plain());
                },
                function(){
                    console.error('Error while fetching');
                }
            );
        }
		
		vm.addList = function addList(){
			var grid = vm.reportSceneAriseWeighListGrid;
			grid.dataSource.insert();
		}
		
		vm.multiDeleteList = function() {
			var grid = vm.reportSceneAriseWeighListGrid;
			var ck = false;
			var rowArr = [];
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if(checkbox.is(':checked')){
					ck = true;
					var dataItem = grid.dataItem(item);
					if(dataItem.sceneGenWorkListId != "" && dataItem.sceneGenWorkListId != 0 && dataItem.sceneGenWorkListId != undefined && dataItem.sceneGenWorkListId != null){
						getMultiDeleteList.push(dataItem.sceneGenWorkListId);
					}
					rowArr.push(row);
				}
			});
			for (var i = 0; i < rowArr.length; i++) {
				grid.removeRow(rowArr[i]);
			}
			if(ck === false){
				toastr.warning("Bạn chưa chọn bản ghi nào để xóa !");
        		return;
			}
	    }
        
		function checkValidateList(list){
			for (var i = 0; i < list.length; i++) {
//				if(list[i].estimatesItemChildId === "" || list[i].estimatesItemChildId === undefined || list[i].estimatesItemChildId === null)
//				{
//					return false;
//				}
				if(list[i].incurredContent === null || list[i].incurredContent === ""){
					return false;
				}
				if(list[i].unit === null || list[i].unit === ""){
					return false;
				}
				if(list[i].incurredQuantity <= 0 || list[i].incurredQuantity === null){
					return false;
				}
				if(list[i].workItemType === 0 || list[i].workItemType === "" || list[i].workItemType === undefined || list[i].workItemType === null){
					return false;
				}
			}
			return true;
		}
		
		function fillDataTableList(data) {
			var workItemTypeData = [{ value: 1, text: "Công việc vận chuyển thủ công" },
			                        { value: 2, text: "Công việc vận chuyển cơ giới" },
			                        { value: 3, text: "Công việc xây lắp" }
			                        ];
			var dataSource = new kendo.data.DataSource({
                pageSize: 20,
                data: data,
                autoSync: false,
                schema: {
                    model: {
                        id: "sceneGenWorkListId",
                    	fields: {
                    		STT: {editable: false},
                    		estimatesItemChildId: {},                    		
                            incurredContent: { validation: {  required: { message: "Nội dung phát sinh không được để trống" } } },
                            unit: { validation: {  required: { message: "Đơn vị tính không được để trống" } } },
                            designQuantity: { validation: { min: 0,editable :false,  required: { message: "Khối lượng thiết kế phải lớn hơn 1" } } },
                            incurredQuantity: {  validation: { min: 1,  required: { message: "Khối lượng phát sinh phải lớn hơn 1" } } },
                            note: {},
                            workItemType: { validation: {  required: { message: "Loại công việc không được để trống" } } }
                        }
                    }
                }
            });
			vm.gridOptions = kendoConfig
				.getGridOptions({
					autoBind : true,
					dataSource: dataSource,
					noRecords : true,
					pageable: {
			            refresh: false,
			            pageSizes: [10, 15, 20, 25],
			            pageSize: 20,
			            messages: {
			                display: " {0} - {1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang",
			                empty: "Không có kết quả hiển thị"
			            }
			         },
					messages : {
						noRecords : gettextCatalog
								.getString("Không có kết quả trong trang")
					},
					edit: function(e) {
				         e.container.find("input[name=itemName]").attr("maxlength", 100);
				         e.container.find("input[name=unit]").attr("maxlength", 200);
				         e.container.find("input[name=note]").attr("maxlength", 500);
				         e.container.find("input[name=designQuantity]").attr("maxlength", 38);
				         e.container.find("input[name=incurredQuantity]").attr("maxlength", 38);
					},
					columns : [
							{
								title : gettextCatalog
										.getString("STT"),
								field : "STT",
								template: dataItem => $("#reportSceneAriseWeighListGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								width : 100,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},
							{
								title : "<input type='checkbox' id='checkalllistrsaw1' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								template : "<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheck1()'/>",
								width : 35,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Hạng mục"),
								field : "estimatesItemChildId",
								editor: itemNameDropDownAutoCompleteEditor,
								values: estimatesItemChildIdData,
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Nội dung phát sinh"),
								field : "incurredContent",
								width : 200,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Đơn vị tính"),
								field : "unit",
								width : 150,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Khối lượng thiết kế"),
								field : "designQuantity",
								edit : false,
								format: "{0:n3}",
								 template: function(dataItem) {
			                            if ($.isNumeric(dataItem.designQuantity) && dataItem.designQuantity >= 0) {
			                            	dataItem.designQuantity = parseFloat(Number(dataItem.designQuantity).toFixed(3));
			                                return parseFloat(Number(dataItem.designQuantity).toFixed(3));
			                            }else{
			                            dataItem.designQuantity =0;
			                            return 0;}
			                        },
			                        decimals: 3,
								width : 200,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:right;"}
							},
							{
								title : gettextCatalog
										.getString("Khối lượng phát sinh"),
								field : "incurredQuantity",
								width : 200,
								headerAttributes: {style:"text-align:center;"},
								 template: function(dataItem) {
			                            if ($.isNumeric(dataItem.incurredQuantity)&& dataItem.incurredQuantity >= 0) {
			                            	dataItem.incurredQuantity = parseFloat(Number(dataItem.incurredQuantity).toFixed(3));
			                                return parseFloat(Number(dataItem.incurredQuantity).toFixed(3));
			                            }else{
			                            dataItem.incurredQuantity = 0;
			                            return 0;}
			                        },
			                        decimals: 3,
			                   
								format: "{0:n3}",
								attributes:{style:"text-align:right;"}
							},
							{
								title : gettextCatalog
										.getString("Ghi chú"),
								field : "note",
								width : 200,
								headerAttributes: {style:"text-align:center;"}
							},
							{
								title : gettextCatalog
										.getString("Loại công việc"),
								field : "workItemType",
								/*attributes: { style: "padding:0" },*/
								editor: workItemTypeDropDownEditor,
								values: workItemTypeData,
								width : 200,
								attributes:{style:"text-align:left;"},
								headerAttributes: {style:"text-align:center;"}
							} ]
				});
				function workItemTypeDropDownEditor(container, options) {
	                $('<input name="workItemType" required data-text-field="name" data-value-field="workItemType" data-bind="value:' + options.field + '"/>')
	                    .appendTo(container)
	                    .kendoDropDownList({
	                        autoBind: false,
	                        dataSource: [{
	                        	workItemType: 1,
	                        	name: "Công việc vận chuyển thủ công"
		                    }, {
		                    	workItemType: 2,
		                    	name: "Công việc vận chuyển cơ giới"
		                    }, {
		                    	workItemType: 3,
		                    	name: "Công việc xây lắp"
		                    }]
	                    });
	            }
				function itemNameDropDownAutoCompleteEditor(container, options) {
					$('<input name="estimatesItemChildId"  data-text-field="itemName" data-value-field="estimatesItemChildId"  data-bind="value:' + options.field + '"/>')
			            .appendTo(container)
			            .kendoDropDownList({
			            suggest: true,
			            placeholder: "Chọn hạng mục",
			            dataSource: itemNameData,
			            filter: "contains",
			            minLength: 1
		            });
				}
			}
	}
})();