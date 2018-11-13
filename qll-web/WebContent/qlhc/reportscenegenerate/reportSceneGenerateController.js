(function() {
	'use strict';
	var controllerId = 'reportSceneGenerateController';
	angular.module('MetronicApp').controller(controllerId,
			reportSceneGenerateController);

	function reportSceneGenerateController($scope, $rootScope,
			$timeout, Constant, reportSceneGenerateService, theApproval,
			gettextCatalog, kendoConfig, $kWindow, CommonService,
			WindowService, PopupConst, Restangular, RestEndpoint, ProposalEvaluation
			) {
		
		// //////////////////////////initial data/////////////////////////////
		var vm = this;
		vm.importReportSceneGenerate = importReportSceneGenerate;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.viewDisable = false;
		vm.showApproval = false;
		vm.hideSave = true;
		vm.hideSaveAppro = true;
		vm.hideDel = false;
		var WorkItems = []; 
		var  checkonchange = 0;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.item = {};
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		vm.item.constructId = ProposalEvaluation.getItem().constructId;
		
		//minhpvn -- import cong trinh 
		vm.downloadTempleReportSceneGenerate = downloadTempleReportSceneGenerate;
		vm.importBieuMauCT = importBieuMauCT;
		vm.cancelImport =	function (){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		 function downloadTempleReportSceneGenerate(){
			 reportSceneGenerateService.exportDataImport(vm.item).then(function () {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;	        	
			}, function (e) {
				toastr.error(gettextCatalog.getString("Lỗi, không tải được biểu mẫu !"));
			});
		}
		function importBieuMauCT(){

		}
		//////----------end minhpvn cong trinh 
		
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
	         if(item !== undefined){
	          vm.item = item;
	          reloadReportSceneGenerate();
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
        reportSceneGenerateService.getItemNameByConstrId(vm.item).then(function(d) {
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
		reportSceneGenerateService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
			totalListEmployee = data.plain();
			reportSceneGenerateService.getRoleId().then(function(data) {
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
          	var grid = vm.reportSceneGenerateGrid;
          	if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}		
        }
		function reloadReportSceneGenerate(){
			reportSceneGenerateService.doSearchSceneGenerateWorkConstruction(vm.item).then(function(d) {
					fillDataTable(d.plain());
					refreshGrid(d.plain());
					$('[name="gridchkselectall"]').prop( "checked", false );
                },
                function(){
                    console.error('Error while fetching');
                }
            );
        }
		reloadReportSceneGenerate();
		
//		$scope.$on("importReportSceneGenerate2", function(event, item) {
//            if (item === undefined) {
//            	
//            } else {
//            	console.log(JSON.stringify(item));
//            	var dataItem = vm.reportSceneGenerateListGrid.dataSource.data();
//            	for(var i = 0 ; i <dataItem.length;i++){
//            		item.push(dataItem[i]);
//            	}
//            	refreshGridList(item);
//            	toastr.success("Import thành công");
//            	
//            }
//        });
		
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistrsg").checked == true){
				document.getElementById("checkalllistrsg").checked = false;
			}
		}
		//minhpvn import popup 
		function importReportSceneGenerate(){
			var templateUrl = 'views/popup/importPage.html';
			var title = 'Import khối lượng phát sinh công trình'			
			CommonService.populateDataToGridCT(templateUrl, title, vm.ProjectGridOptions, vm, "importReportSceneGenerate");
		
	     }
		vm.attachpopup = function(popupid) {
			
			if(popupid == "importReportSceneGenerate"){

				 reportSceneGenerateService.exportDataImport(vm.item).then(function () {
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;	        	
				}, function (e) {
					toastr.error(gettextCatalog.getString("Lỗi, không tải được biểu mẫu !"));
				});
			
			} 
		}
		vm.closepopup = function(){
			 CommonService.closePopup();
		}
		
		
		vm.savepopup = function(popupid){
			
			if(popupid == "importReportSceneGenerate"){
				var fileInput = $('#xlf');
				var input = fileInput.get(0);
				var check = false;
				var reader = new FileReader();
				if (input.files.length) {
					var textFile = input.files[0];
					reader.readAsBinaryString(textFile);
					reader.onload = function (e) {
						var data = e.target.result;
						try{
						/* Call XLSX */
						var workbook = XLSX.read(data, { type: "binary" });
						/* DO SOMETHING WITH workbook HERE */
						var first_sheet_name = workbook.SheetNames[0];
						/* Get worksheet */
						var worksheet = workbook.Sheets[first_sheet_name];
						var worklistexcell = XLSX.utils.sheet_to_json(worksheet, { raw: true });
						 }
				        catch(err){
				        	toastr.error("File Import phải là file excel (.xlsx)!");
				        }
						
//						var check = false;
				        for (var i = 0; i < worklistexcell.length; i++) {
							if(worklistexcell[i].estimatesWorkItemId){
								check = true;
							}else{
								worklistexcell.splice(i,1);
								i--;
							}
							
						}
							for (var i = 0; i < worklistexcell.length; i++) {
					        	check = true;
					        	for(var j = 0;j < WorkItems.length;j++){
					        	
					        	if(typeof worklistexcell[i].estimatesWorkItemId != "undefined")	
					        	{
					        	if(worklistexcell[i].estimatesWorkItemId == WorkItems[j].estimatesWorkItemId ){
					        		WorkItems[j].unit  = worklistexcell[i].unit;
					        		WorkItems[j].incurredQuantity = worklistexcell[i].incurredQuantity;
					        		WorkItems[j].designQuantity = worklistexcell[i].designQuantity;
					        		WorkItems[j].incurredContent = worklistexcell[i].incurredContent;
					        		WorkItems[j].note = worklistexcell[i].note;
					        		WorkItems[j].estimatesItemChildId = worklistexcell[i].estimatesItemChildId ;
					        		
					        		break; 
					        	}
					        	}
					        	}
					        }
							if(check == false){
								toastr.warning(gettextCatalog.getString("File excel không hợp lệ, hãy kiểm tra lại dữ liệu trong file excel"));
								return;
							}
							refreshGridList(WorkItems);
							CommonService.closePopup();
			            	toastr.success("Import thành công");
			            	
			            
//						worklistexcell.splice(0,1);
						
//						for (var i = 0; i < worklistexcell.length; i++) {
//							if(worklistexcell[i].unit){
//								check=true;
//							}else{
//								worklistexcell.splice(i,1);
//								i--;
//							}
//							
//						}
						
//						if(check == false){
//							toastr.warning(gettextCatalog.getString("File excel không hợp lệ, hãy kiểm tra lại dữ liệu trong file excel"));
//							return;
//						}
						
//						$rootScope.$broadcast("importReportSceneGenerate2", worklistexcell);
//						$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					};
				} else {
					toastr.warning('Hãy tải lên một tập tin trước khi tiếp tục');
				};
				
			}
			
		
		}
		
		// Hien thi danh sach
		function fillDataTable(data){
			vm.gridListOptions2 = kendoConfig
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
								template: dataItem => $("#mainReportSceneGenerateGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								width : 60,
								headerAttributes: {style:"text-align:center;"},
								attributes:{style:"text-align:center;"}
							},
							{
								title : "<input type='checkbox' id='checkalllistrsg' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
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
		$("#mainReportSceneGenerateGrid").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");
		////////////////////////////////Handle event////////////////////////////////////
		function onChange() {
			var grid = vm.reportSceneGenerateGrid;
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
												+ "," 	+ dataItem.ainChargeMonitorId 
												+ "," 	+ dataItem.bdirectorId
												+ "," 	+ dataItem.cdesignDirectionId 
												+ "," 	+ dataItem.adirectorId;
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
			var grid = vm.reportSceneGenerateGrid;
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
				reloadDetail();
			} else {
				toastr.warning("Chỉ trạng thái trình duyệt mới được phê duyệt !");
			}
		}
		// trinh duyet
		vm.sendToApproval = function(){
			if(vm.mSceneGenerateWork.statusCa === 0){
				$('#loading').show();
				reportSceneGenerateService.exportFileReportSceneAriseWeighCT(vm.mSceneGenerateWork).then(function(data){
	    			vm.theApproval.path = data.fileName;
	    			vm.theApproval.nameFile = 'BM-TCNT-18.pdf';
	    			theApproval.setItem(vm.theApproval);
	    			goTo('THE_APPROVAL');
	        	}).catch( function(){
		        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
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
				var grid = vm.reportSceneGenerateGrid;
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
						reportSceneGenerateService.cancelAprroval(vm.mSceneGenerateWork).then(function() {
							status = true;
							$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
						reloadReportSceneGenerate();
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
				reloadReportSceneGenerate();
			}
		});
        // Add
		vm.add = function(){
			////////////////////////////////////////////////////////////////
			reportSceneGenerateService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
				totalListEmployee = data.plain();
				reportSceneGenerateService.getRoleId().then(function(data) {
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
			reloadReportSceneAriseWeighList();			
		}
		////////////////////////////////////////
		// Show detail, edit
		vm.showGrid = function(){
			var grid = vm.reportSceneGenerateGrid;
			if(vm.isCreateNew === false){
				// Check select
	        	if (grid.select() === null || grid.select().length === 0 || vm.mSceneGenerateWork.sceneGenerateWorkId === null) {
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
			} else {
				vm.isCreateNew = false;
				reloadReportSceneGenerate();
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
				reloadDetail();
			} else {
				vm.showDetail = false;
				vm.showApproval = false;
				vm.hideSave = true;
				vm.hideSaveAppro = true;
				vm.hideDel = false;
				reloadReportSceneGenerate();
			}
		}
		//validate time
		function verifyMyDate(d) {
			  var re = /^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$/;
		    return re.test(d);
		}
		function validateDateTime(){
			if(!verifyMyDate($("#signDateRep1").val())){
				return false;
			}
			return true;
		}
		// Save
		vm.save = function() {
			if(vm.validator.validate()){
			var data = vm.reportSceneGenerateListGrid.dataSource.data();
			var objectList =[];
			for(var i =0; i<data.length ; i++){
				data[i].status = 0;
				objectList.push(data[i]);
			}
			if(getMultiDeleteList.length > 0){
				for (var i = 0; i < getMultiDeleteList.length; i++) {
					reportSceneGenerateService.removeReportSceneAriseWeighList(getMultiDeleteList[i]).then(function() {
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
									reportSceneGenerateService.doCRUDCT(vm.mSceneGenerateWork).then(
											function(){
												vm.isCreateNew = false;
												vm.showDetail = false;
												vm.hideSave = true;
												vm.hideSaveAppro = true;
												vm.hideDel = false;
												reloadReportSceneGenerate();
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
									vm.mSceneGenerateWork.type = 2;
									vm.mSceneGenerateWork.createdDate = new Date();
									reportSceneGenerateService.doCRUDCT(vm.mSceneGenerateWork).then(
											function(result){
												vm.mSceneGenerateWork.sceneGenerateWorkId = result.sceneGenerateWorkId;
												vm.isCreateNew = false;
												vm.showDetail = false;
												vm.hideSave = true;
												vm.hideSaveAppro = true;
												vm.hideDel = false;
												reloadReportSceneGenerate();
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
	    	var grid = vm.reportSceneGenerateGrid;
	    	var gridList = vm.reportSceneGenerateListGrid;
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
				var grid = vm.reportSceneGenerateGrid;
				// Check select
	        	if (grid.select() === null || grid.select().length === 0) {
	        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
	        		return;
	        	}
	        	var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				if(dataItem.statusCa === 0 || dataItem.statusCa === 3){
					if (grid.select().length > 0 && confirm('Xác nhận xóa')) {
						reportSceneGenerateService.updateIsActiveReportSceneAriseWeigh(dataItem.sceneGenerateWorkId).then(function(){
		            		toastr.success("Xóa thành công!");
		            		if (vm.showDetail){
		            		}else{
		            		reloadReportSceneGenerate();
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
			var listID = [];
    		var noDel=0;
    		var noDel1= 0;
			var grid = vm.reportSceneGenerateGrid;
			var checkSelect = false;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var dataItem = grid.dataItem(item);
					/*if(dataItem.statusCa === 0 || dataItem.statusCa === 3){*/
						selectedRow.push(dataItem);
					/*}
					checkSelect = true;*/
				}
			});
			/*if (checkSelect === true && selectedRow.length === 0) {
				toastr.warning("Bản ghi ở trạng thái đã duyệt hoặc trình duyệt không được xóa");
				return;
			} else if (checkSelect === false && selectedRow.length === 0) {
				toastr.warning("Bạn chưa chọn bản ghi nào để xóa");
				return;
			}*/
			if (selectedRow.length == 0){
				toastr.warning("Bạn chưa chọn bản ghi nào để xóa");
				return;
			}
			
			
				for (var i = 0; i < selectedRow.length; i++) {
					if (selectedRow[i].statusCa === 0 || selectedRow[i].statusCa === 3) {
						if(selectedRow[i].createdUserId === Constant.user.srvUser.userId){
							listID.push(selectedRow[i].sceneGenerateWorkId);
							noDel++;
							noDel1++;
						}else if(noDel == 0){
							toastr.warning("Bạn không phải người tạo, bạn không có quyền xóa bản ghi đang chọn!");
							noDel++;
							noDel1++;
						}
					}else {
						if(noDel1 ==0){
							toastr.warning("Không thể xóa bản ghi đang trình duyệt hoặc đã duyệt");
							noDel1++;
						}						
					}
				}
				if(listID.length > 0){
			if (confirm('Xác nhận xóa')) {
				reportSceneGenerateService.updateIsActiveReportSceneAriseWeigh(listID).then(function() {
		    		toastr.success("Đã xóa thành công");
		    		reloadReportSceneGenerate();
			     }, function(errResponse) {
			    	 if (errResponse.status === 302){
			    		 toastr.error("Bản ghi đang được sử dụng");
			    	 }else{
			    		 toastr.error("Có lỗi xảy ra trong quá trình xóa!");
			    	 } 
			     });
			}
				}
		} 
        ///////////////////////////////////Export File/////////////////////////////////
		vm.exportFilePDF = function(){

			var selectedRow = [];
			var grid = vm.reportSceneGenerateGrid;
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
					reportSceneGenerateService.exportFileReportSceneAriseWeighCT(vm.mSceneGenerateWork).then(function(data){
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
					reportSceneGenerateService.exportListReportSceneAriseWeighCT(selectedRow).then(function(data){
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
					reportSceneGenerateService.exportListReportSceneAriseWeighCT(selectedRow).then(function(data){
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
			       	reportSceneGenerateService.exportFileReportSceneAriseWeighCT(vm.mSceneGenerateWork).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
//			    		checkonchange = 0;
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}else
				{
					
					$('#loading').show();
					reportSceneGenerateService.exportFileReportSceneAriseWeighCT(vm.mSceneGenerateWork).then(function(data){
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
			var grid = vm.reportSceneGenerateGrid;
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
					reportSceneGenerateService.exportFileDocReportSceneAriseWeighCT(vm.mSceneGenerateWork).then(function(data){
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
					reportSceneGenerateService.exportListDocReportSceneAriseWeighCT(selectedRow).then(function(data){
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
					reportSceneGenerateService.exportListDocReportSceneAriseWeighCT(selectedRow).then(function(data){
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
			       	reportSceneGenerateService.exportFileDocReportSceneAriseWeighCT(vm.mSceneGenerateWork).then(function(data){
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
					reportSceneGenerateService.exportFileDocReportSceneAriseWeighCT(vm.mSceneGenerateWork).then(function(data){
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
        	reportSceneGenerateService.approCT(vm.mApproval).then(function(check){
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
    				reloadReportSceneGenerate();
        			toastr.success(gettextCatalog.getString("Phê duyệt thành công!"));
        		} else if(check === 4){
        			vm.showDetail = false;
    				vm.showApproval = false;
    				vm.hideSave = true;
    				vm.hideSaveAppro = true;
    				vm.hideDel = false;
    				reloadReportSceneGenerate();
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
        vm.coutRowAdded = 0;
        ///////////////////////////////////////////////
		//////////////////////////////////////////List////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//minhpvn
 function reloadBufferTable(d) {
            vm.isCheckAddRow = false;
            vm.coutRowAdded = 0;
            vm.demoData = [];
            fillDataTableDetail(d).then(function(result) {
                refreshGrid(d);
            });
        }
		function reloadReportSceneAriseWeighList(){
			reportSceneGenerateService.getWorkItemCongTrinh(vm.mSceneGenerateWork).then(function(d) {
//					fillDataTableList(d.plain());
					WorkItems = d.plain();
					refreshGridList(d.plain());
                },
                function(){
                    console.error('Error while fetching');
                }
            );
        }
		function refreshGridList(d) {
			var grid = vm.reportSceneGenerateListGrid;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}		
        }
		function reloadDetail(){
			reportSceneGenerateService.doSearchSceneGenerateWorkList(vm.mSceneGenerateWork).then(function(d) {
//					fillDataTableList(d.plain());
					refreshGridList(d.plain());
                },
                function(){
                    console.error('Error while fetching');
                }
            );
        }
		
		vm.addList = function addList(){
			var grid =vm.reportSceneGenerateListGrid;
			grid.dataSource.insert();
		}
		
		vm.multiDeleteList = function() {
			var grid = vm.reportSceneGenerateListGrid;
			var ck = false;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if(checkbox.is(':checked')){
					ck = true;
					var dataItem = grid.dataItem(item);
					if(dataItem.sceneGenWorkListId != "" && dataItem.sceneGenWorkListId != 0 && dataItem.sceneGenWorkListId != undefined && dataItem.sceneGenWorkListId != null){
						getMultiDeleteList.push(dataItem.sceneGenWorkListId);
					}
					grid.removeRow(row);
				}
			});
			if(ck === false){
				toastr.warning("Bạn chưa chọn bản ghi nào để xóa !");
        		return;
			}
	    }
        
		function checkValidateList(list){
			for (var i = 0; i < list.length; i++) {
				if(list[i].estimatesItemChildId === "" || list[i].estimatesItemChildId === undefined || list[i].estimatesItemChildId === null)
				{
					return false;
				}
				
	
				if(list[i].incurredQuantity <= 0 || list[i].incurredQuantity === null){
					return false;
				}
			}
			return true;
		}
		
//		function fillDataTableList(data) {
// bảng nhỏ
			vm.gridOptions = kendoConfig
					.getGridOptions({	
		                autoBind: true,
		                 dataSource: {
		                    type: "odata",
		                    transport: {
		                        read: data
		                    },
		                    //sort: { field: "estimatesItemChildId", dir: "asc"},
		                    group: {
		                        field: "type",
		                        
		                    },
		                     schema: {
		                           model: {
		                             fields: {
		                            	 itemName: { editable: false, nullable: true },
		                            	 incurredContent: { editable: false, nullable: true },
		                            	 unit: { editable: false, nullable: true },
		                            	 designQuantity: { editable: false, nullable: true },
		                            	 incurredQuantity: { editable: true, nullable: true },
		                            	 note: { editable: true, nullable: true },
		                            	
		                             }
		                           }
		                       },
		                    pageSize: 50
		                },
		                change: onChange,
		                noRecords: true,
		                pageable: {
					        refresh: true,
					        /*pageSizes: [50]*/
					    }, 
						dataBinding: function () {
							vm.record = 0;
							vm.record1 = 0;
						},
		                messages: {
		                    noRecords: gettextCatalog.getString("Không có kết quả nào")
		                },
		                edit: function(e) {
					         e.container.find("input[name=itemName]").attr("maxlength", 100);
					         e.container.find("input[name=incurredContent]").attr("maxlength", 200);
					         e.container.find("input[name=unit]").attr("maxlength", 200);
					         e.container.find("input[name=designQuantity]").attr("maxlength", 38);
					         e.container.find("input[name=incurredQuantity]").attr("maxlength", 38);
					         e.container.find("input[name=note]").attr("maxlength", 500);
					         
					     },
						columns : [
							{
									title : gettextCatalog.getString("STT"),
									template: function (item) {
										if(item.type == 1) {return ++vm.record}else if(item.type == 2){return ++vm.record1}
										},		
									
									headerAttributes: {style:"text-align:center;font-weight: bold;"},
								    attributes: { style: "text-align:center;" },
									width : 60
								},
								{
									title : gettextCatalog
											.getString("Hạng mục"),
									field : "itemName",
									headerAttributes: {style:"text-align:center;"},
								    attributes: { style: "text-align:left;" },
									width : 200
								},
								{
									title : gettextCatalog
											.getString("Nội dung phát sinh"),
									// template : "",
									field : "incurredContent",
									headerAttributes: {style:"text-align:center;"},
								    attributes: { style: "text-align:left;" },
									width : 250
								},
								{
									title : gettextCatalog
											.getString("Đơn vị tính"),
									field : "unit",
									headerAttributes: {style:"text-align:center;"},
								    attributes: { style: "text-align:left;" },
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Khối lượng</br> theo thiết kế"),
									field : "designQuantity",
									headerAttributes: {style:"text-align:center;"},
								    attributes: { style: "text-align:left;" },
									width : 120,
									 template: function(dataItem) {
				                            if ($.isNumeric(dataItem.designQuantity) && dataItem.designQuantity >= 0) {
				                            	dataItem.designQuantity = parseFloat(Number(dataItem.designQuantity).toFixed(3));
				                                return parseFloat(Number(dataItem.designQuantity).toFixed(3));
				                            }else{
				                            dataItem.designQuantity =0;
				                            return 0;}
				                        },
				                        decimals: 3
								},	{
									title : gettextCatalog
									.getString("Khối lượng</br> phát sinh"),
									field : "incurredQuantity",
									headerAttributes: {style:"text-align:center;"},
									attributes: { style: "text-align:left;" },
									width : 120, template: function(dataItem) {
			                            if ($.isNumeric(dataItem.incurredQuantity)  && dataItem.incurredQuantity >= 0) {
			                            	dataItem.incurredQuantity = parseFloat(Number(dataItem.incurredQuantity).toFixed(3));
			                                return parseFloat(Number(dataItem.incurredQuantity).toFixed(3));
			                            }else{
			                            dataItem.incurredQuantity =0;
			                            return 0;}
			                        },
			                        decimals: 3
								},	{
									title : gettextCatalog
									.getString("Ghi chú"),
									field : "note",
									headerAttributes: {style:"text-align:center;"},
									attributes: { style: "text-align:left;" },
									width : 120
								},
								
								{
									title : gettextCatalog.getString("Loại hợp đồng"),
									field : "type",
									hidden:true,
									groupHeaderTemplate: " #if(value == '1'){#Công việc trong hợp đồng#}else if(value == '2'){#Công việc phát sinh ngoài hợp đồng#}#",
									width : 120
								},
								],
					});
//		}
	}
})();