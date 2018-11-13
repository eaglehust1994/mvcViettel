(function() {
	'use strict';

	var controllerId = 'constrCompleteRecordsMapController';

	angular.module('MetronicApp').controller(controllerId, constrCompleteRecordsMapController);

	/* @ngInject */
	function constrCompleteRecordsMapController($scope, $rootScope, $timeout, Constant,
										kendoConfig,
										ProposalEvaluation,
										constrCompleteRecordsMapService,
										catFileInvoiceService,
									 	hshcAuthService,
										gettextCatalog, $kWindow,
										CommonService, Restangular, PopupConst, RestEndpoint) {
		var vm = this;
		var constrService = ProposalEvaluation;
		var mapService = constrCompleteRecordsMapService;
		var invoiceService = catFileInvoiceService;
		vm.exportFileExcell = exportFileExcell;
		vm.chkSelectAll = chkSelectAll;

		vm.showSearch = true;
		vm.showDetail = false;
		vm.mapDatatableLength = false;

		vm.isShowNotExist = false;

		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.invoiceData = [];
		vm.mapDatatable = [];

		vm.constr = {
			"defaultSortField": '',
			"catProjectId": '',
			"catApProjectName": '',
			"constructId": '',
			"constrtCode": '',
			"constrType": '',
			"constrTypeName": '',
			"constrTypeCode": '',
			"constrtName": '',
			"constrtAddress": '',
			"constructorId": '',
			"supervisorId": '',
			"startingDate": '',
			"completeDate": '',
			"sysUserId": '',
			"catApProjectId": '',
			"sysUserName": '',
			"sysGroupId": '',
			"sysGroupName": '',
			"sysCreatedDate": '',
			"status": '',
			"expectedCompleteDate": '',
			"startComment": '',
			"isActive": '',
			"provinceId": '',
			"districtId": '',
			"investProjectId": '',
			"investProjectName": '',
			"investProjectCode": '',
			"stationId": '',
			"serviceProvideDate": '',
			"districtCode": '',
			"districtName": '',
			"handoverDate": '',
			"stationCode": '',
			"stationAddress": '',
			"provinceCode": '',
			"constructorName": '',
			"supervisorName": '',
			"provinceName": '',
			"superForm": '',
			"constrtForm": '',
			"isObstructed": '',
			"statusId": '',
			"statusName": '',
			"statusCode": '',
			"isAccepted": '',
			"preStartingDate": '',
			"acceptStartDate": '',
			"acceptFinishDate": '',
			"lineMode": '',
			"projectType": '',
			"constructorPerson": '',
			"constructorPhone": '',
			"supervisorPerson": '',
			"supervisorPhone": '',
			"cableTypeId": '',
			"lineLength": '',
			"lineType": '',
			"executer": '',
			"executeGroup": '',
			"executePhone": '',
			"finishTest": '',
			"percentFinish": '',
			"preparedPremises": '',
			"handoverMaterials": '',
			"progressCommitment": '',
			"contractCode": '',
			"contractId": '',
			"isAcceptedContract": '',
			"isOff": '',
			"fwmodelId": ''
		};

		vm.criteria = {
			constructionId:'',
			createDateFrom:'',
			createDateTo:'',
			signEmployee:'',
			status:'',
			typeConstruction:'0'
		};
		
		vm.form = {};

		vm.status = [];

		vm.item = {
			partnerName:'',
			contractCode:'',
			investProjectName:'',
			constrtCode:'',
			constrtName:'',
			constrType:'',
			provinceId:'',
			constrtAddress:'',
			constructId:''
		};
		
		vm.statuss = [
		              {status:4,statusName:'Tất cả'},
		              {status:0,statusName:'Khởi tạo'},
		              {status:1,statusName:'Trình duyệt/ký'},
		              {status:2,statusName:'Đã duyệt/ký'},
		              {status:3,statusName:'Từ chối'}
		              ];
		vm.statuss.status = 4;

		vm.item = constrService.getItem();
		initFormData();
		function initFormData() {
			detail();
		}
		
		// lay danh sach loại ho so start
		
		
		
		function getListInvoice() {
			mapService.getListInvoice().then(function(data) {
				vm.invoice = data.plain();
				if(vm.invoice.length > 0) {
					vm.invoice.catFileInvoiceId = 0;
				}
				
			  });
		}
		// lay danh sach loại ho so end
		$('#loading').show();
		hshcAuthService.getConstructions(ProposalEvaluation.getItem()).then(function(data) {
			vm.constrObj = {};
			vm.constrObj.constructId = data.constructId;
			vm.constrObj.contractCode = data.contractCode;
			vm.constrObj.doitac = data.partnerName;
			vm.constrObj.mact = data.constrtCode;
			vm.constrObj.tenct = data.constrtName;
			vm.constrObj.tinhthanh = data.provinceName;
			vm.constrObj.loaict = data.constrTypeName;
			getListInvoice();
		}).catch(function(data, status) {
			toastr.warning(gettextCatalog.getString("không export được danh sách!"));
			$('#loading').hide();
		}).finally(function(){
			$('#loading').hide();
		});

		function detail() {
			console.log(vm.item);
			if(!vm.item || vm.item.constructId == 0 ) {
				alert("Please select one row!");
				return;
			}

			vm.showDetail = true;
			vm.showSearch = false;
			

/*			constrService.findByConstructionId(vm.item.constructId).then(function (data) {
				vm.constr = data;

			}, function(errResponse) {
				console.error('Error while fetching construction data.');
			});*/

			vm.datasource = [];
			mapService.findByConstructionId(vm.item.constructId).then(function (data) {
				for(var i = 0; i < data.length; i++) {
					data[i].stt = (i+1)+"";
					if(data[i].status == 0) {
						data[i].statusName = "Khởi tạo";
					}
					if(data[i].status == 1) {
						data[i].statusName = "Trình duyệt/ký";
					}
					if(data[i].status == 2) {
						data[i].statusName = "Đã duyệt/ký";
					}
					if(data[i].status == 3) {
						data[i].statusName = "Từ chối";
					}
				}
				vm.mapDatatable = data;
				if(vm.mapDatatable.length == 0) {
					vm.mapDatatableLength = true;
				}else{
					vm.mapDatatableLength = false;
				}
				
			}, function(errResponse) {
				console.error('Error while fetching record map data.');
			});
			

			vm.criteria = {};
			vm.criteria.typeConstruction = 0;
			 invoiceService.findByExistProfile(vm.item.constructId).then(function (data) {
			 vm.invoiceData = data;
			 fillInvoiceGrid(data.plain());
			 var grid = vm.invoiceGrid;
			 if(grid){
			 grid.dataSource.data(data);
			 grid.refresh();
			 }
							
			 vm.criteria = {
			 constructionId:'',
			 createDateFrom:'',
			 createDateTo:'',
			 signEmployee:'',
			 status:'',
			 typeConstruction:'0'
			 };
			 }, function(errResponse) {
			 console.error('Error while fetching record map data.');
			 });
		}
		

		function fillInvoiceGrid(data) {

			vm.invoiceGridOptions = kendoConfig.getGridOptions({
				autoBind : true,
		   		scrollable:true,
		   		dataSource : data,
		   		noRecords : true,
		   		editable: false,
				messages : {
					noRecords : gettextCatalog.getString("Không có kết quả nào")
				},
				columns : [
				    {
					    title : gettextCatalog.getString("STT"),
						field : "index",
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
						template: dataItem => $("#invoiceGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width : 50
					},
					{
						title: gettextCatalog.getString("Tên hồ sơ"),
						field : "fileInvoiceName",
						//editor: nonEditor,
						headerAttributes: {style:"text-align:center;"},
						width : 150
					},
					{
						title: gettextCatalog.getString("Mã hồ sơ"),
						field : "fileInvoiceCode",
						//editor: nonEditor,
						headerAttributes: {style:"text-align:center;"},
						width : 150
					},
					{
						title: gettextCatalog.getString("Tình trạng tạo"),
						width : 100,
						field : "isExistProfile",
						//editor: nonEditor,
						headerAttributes: {style:"text-align:center;"},
						attributes: { style: "text-align:center" },
						template: "#= isExistProfile == 1 ? 'Đã tạo' : 'Chưa tạo' #"
					}]
			});

		}

		vm.onChange = function(){
			var typeConstruction = vm.criteria.typeConstruction;
			
			var datasource = [];
			var item;
			var i;
			if(vm.isShowNotExist)
			{
				for(i = 0; i < vm.invoiceData.length; i++)
				{
					item = vm.invoiceData[i];
					if(typeConstruction == "0") {
						if(item.isExistProfile == "0")
						{
							datasource.push(item);
						}
					}else {
						if(item.typeConstruction+"" == typeConstruction && item.isExistProfile == "0")
						{
							datasource.push(item);	
						}
					}
					
				}
			}else {
				for(i = 0; i < vm.invoiceData.length; i++)
				{
					item = vm.invoiceData[i];
					if(typeConstruction == "0") {
						datasource.push(item);
					}else {
						if(item.typeConstruction+"" == typeConstruction && item.isExistProfile == "1")
						{
							datasource.push(item);
						}
					}
					
				}
			}
			var grid = vm.invoiceGrid;
			if(grid){
				grid.dataSource.data(datasource);
				grid.refresh();
			}
		};
		
		vm.filter = function(){
			
			if(vm.statuss.status != null ) {
				vm.criteria.status = vm.statuss.status;
			}

			var validFrom = 0;
			var validTo = 0;
			if(vm.form.createDateFromfd != null && vm.form.createDateFromfd.length > 9) {
				var arrDateFrom = vm.form.createDateFromfd.split("/");
	         	validFrom = new Date();
	         		validFrom.setFullYear(arrDateFrom[2]);
			   		validFrom.setMonth((arrDateFrom[1]) - 1);
			   		validFrom.setDate(arrDateFrom[0]);
			}
			
			if(vm.form.createDateTotd != null && vm.form.createDateTotd.length > 9) {
	         	var arrDateTo = vm.form.createDateTotd.split("/");
	         	validTo = new Date();
	             	validTo.setFullYear(arrDateTo[2]);
	             	validTo.setMonth((arrDateTo[1]) - 1);
	             	validTo.setDate(arrDateTo[0]);
			}
			
			if(validFrom <= validTo || validFrom == 0 || validTo == 0){
			 }else{
				toastr.warning(gettextCatalog.getString("Ngày tạo từ ngày phải sau Ngày tạo đến ngày"));
				return;
			 }
 			 
			if(vm.form.createDateFromfd != null && vm.form.createDateFromfd.length > 9) {
				vm.criteria.createDateFrom = vm.form.createDateFromfd.substring(6, 10) +"-"+ vm.form.createDateFromfd.substring(3, 5) +"-"+ vm.form.createDateFromfd.substring(0, 2);
			}else {
				vm.criteria.createDateFrom = null;
			}
			
			if(vm.form.createDateTotd != null && vm.form.createDateTotd.length > 9) {
				vm.criteria.createDateTo = vm.form.createDateTotd.substring(6, 10) +"-"+ vm.form.createDateTotd.substring(3, 5) +"-"+ vm.form.createDateTotd.substring(0, 2);
			}else {
				vm.criteria.createDateTo = null;
			}
			
			vm.criteria.constructionId = vm.item.constructId;
			if($scope.employee != null && $scope.employee.fullName != null && $scope.employee.fullName.length > 0) {
				vm.criteria.signEmployee = $scope.employee.fullName[0].id;
			}else {
				vm.criteria.signEmployee = null;
			}
			
			if(vm.invoice.catFileInvoiceId != null) {
				vm.criteria.catFileInvoiceId = vm.invoice.catFileInvoiceId;
			}
			
			$('#loading').show();
			mapService.filter(vm.criteria).then(function (data) {
				
				for(var i = 0; i < data.length; i++) {
					data[i].stt = (i+1)+"";
					if(data[i].status == 0) {
						data[i].statusName = "Khởi tạo";
					}
					if(data[i].status == 1) {
						data[i].statusName = "Trình duyệt/ký";
					}
					if(data[i].status == 2) {
						data[i].statusName = "Đã duyệt/ký";
					}
					if(data[i].status == 3) {
						data[i].statusName = "Từ chối";
					}
				}
				vm.mapDatatable = data;
				if(vm.mapDatatable.length == 0) {
					vm.mapDatatableLength = true;
				}else {
					vm.mapDatatableLength = false;
				}
				
				
			}).catch( function(){
				console.error('Error while fetching record map data.');
				return;
			}).finally(function(){
				$('#loading').hide();
			});
			
		};

		$scope.employee = {};
		vm.onSuggest = function(){
			var searchTerm = {
					fullName: $("#inputXemhshc").val(),
			};
			if(searchTerm.fullName.length > 1)
			{
				hshcAuthService.getAutoData(searchTerm).then(function(data) {

					$scope.employees = new kendo.data.DataSource({
						data: data
					});
					$scope.employees.read();
					

				});
			}
		}

		$scope.$on("ProposalEvaluation.detail", function (event, item) {
			if(item != undefined){
				vm.item = item;
				initFormData();
			}else{
				console.error("không nhận được dữ liệu!");
			}
		});
		
		
		
		vm.exportFile = function exportFile(){
			if(vm.checkHaveDone <= 0){
				toastr.warning(gettextCatalog.getString("Bạn chưa chọn bản ghi nào để Export !"));
				return;
			}
			var ListAcceptanceRequest = [];
			var ListAmaterialHanover = [];
			var ListScene = [];
			var ListWorkCompConfirm = [];

			var ListRecoveryMinute = [];
			var ListQualityMea = [];
			var ListBmaterialAcceptance = [];
			var ListConstructionAcceptance = [];
			
			var ListAbCompleteWork = [];
			var ListAbMaterialCompare = [];
			var ListAbSettlementValue = [];
			var ListAbSettlementWork = [];
			var ListCategoryAcceptance = [];
			var ListCompleteDrawing = [];
			var ListGroundHanover = [];
			var ListOrganizationPlan = [];
			var ListWorkLog = [];
			var ListWorkLogLable = [];
			var ListStateHanover = [];
			var ListDetailSettlement = [];
			var ListDetailSettlementProposal = [];
			var ListDistanceUnload = [];
			var ListTitAzi = [];
			var ListWorkItemAcceptance = [];
			var ListAbCompleteWorkDescribe = [];
			var ListAbDetailPrice = [];
			var ListComplementWorkEstimateAB = [];
			var ListMonitorAssign = [];
			
			var value = $("[name='checkItem']");
			for(var i = 0; i < value.length; i++) {
				var dataItem = value[i];
				if(dataItem.checked) {
					switch(dataItem.value) {
				    case 'CONSTR_ACCEPTANCE_REQUEST':
				    	ListAcceptanceRequest.push(dataItem.id);
				        break;
				    case 'A_MATERIAL_HANDOVER':
				    	ListAmaterialHanover.push(dataItem.id);
				        break;
				    case 'SCENE_GENERATE_WORK':
				    	ListScene.push(dataItem.id);
				        break;
				    case 'CONSTR_WORK_COMP_CONFIRM':
				    	ListWorkCompConfirm.push(dataItem.id);
				        break;
				    case 'A_MATERIAL_RECOVERY_MINUTES':
				    	ListRecoveryMinute.push(dataItem.id);
				        break;
				    case 'QUALITY_CABLE_MEA_REPORT':
				    	ListQualityMea.push(dataItem.id);
				        break;
				    case 'B_MATERIAL_ACCEPTANCE':
				    	ListBmaterialAcceptance.push(dataItem.id);
				        break;
				    case 'CONSTRUCTION_ACCEPTANCE':
				    	ListConstructionAcceptance.push(dataItem.id);
				        break;
				    case 'AB_COMPLEMENT_WORK_DESCRIBE':
				    	ListAbCompleteWorkDescribe.push(dataItem.id);
				        break;
				    case 'AB_COMPLEMENT_WORK':
				    	ListAbCompleteWork.push(dataItem.id);
				        break;
				    case 'AB_MATERIAL_COMPARE':
				    	ListAbMaterialCompare.push(dataItem.id);
				        break;
				    case 'AB_SETTLEMENT_VALUE':
				    	ListAbSettlementValue.push(dataItem.id);
				        break;
				    case 'AB_SETTLEMENT_WORK':
				    	ListAbSettlementWork.push(dataItem.id);
				        break;
				    case 'CATEGORY_ACCEPTANCE':
				    	ListCategoryAcceptance.push(dataItem.id);
				        break;
				    case 'COMPLETION_DRAWING':
				    	ListCompleteDrawing.push(dataItem.id);
				        break;
				    case 'CONSTR_GROUND_HANDOVER':
				    	ListGroundHanover.push(dataItem.id);
				        break;
				    case 'CONSTR_ORGANIZATION_PLAN':
				    	ListOrganizationPlan.push(dataItem.id);
				        break;
				    case 'CONSTR_WORK_LOGS':
				    	ListWorkLog.push(dataItem.id);
				        break;
				    case 'CONSTR_WORK_LOGS_LABEL':
				    	ListWorkLogLable.push(dataItem.id);
				        break;
				    case 'CURRENT_STATE_HANDOVER':
				    	ListStateHanover.push(dataItem.id);
				        break;
				    case 'DETAIL_SETTLEMENT_EVALUATE':
				    	ListDetailSettlement.push(dataItem.id);
				        break;
				    case 'DETAIL_SETTLEMENT_PROPOSAL':
				    	ListDetailSettlementProposal.push(dataItem.id);
				        break;
				    case 'DISTANCE_UNLOAD_CONSTR_MINUTES':
				    	ListDistanceUnload.push(dataItem.id);
				        break;
				    case 'TIT_AZI_CONSTR_ACCEPT':
				    	ListTitAzi.push(dataItem.id);
				    	vm.dataItem ={};
				    	vm.dataItem.contractId=vm.item.contractId;
				    	vm.dataItem.titAziConstrAcceptId=dataItem.id;
				        break;
				    case 'WORK_ITEMS_ACCEPTANCE':
				    	var listWorkItemAcceptance = {};
				    	listWorkItemAcceptance.constructId = vm.item.constructId;
				    	listWorkItemAcceptance.workItemsAcceptanceId=dataItem.id;
				    	ListWorkItemAcceptance.push(listWorkItemAcceptance);
				        break;
				    case 'AB_DETAIL_PRICE':
				    	ListAbDetailPrice.push(dataItem.id);
				        break;
				    case 'MONITOR_MISSION_ASSIGN':
				    	ListMonitorAssign.push(dataItem.id);
				        break;
				    default:
				        break;
					}
				}
			}
			
			
			if (ListConstructionAcceptance.length > 0) {
				mapService.exportListConstructionAcceptance(ListConstructionAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được Biên bản nghiệm thu công trình đưa vào sử dụng!"));
				});
			}
			
			
			if (ListAcceptanceRequest.length > 0) {
				var objExport = {};
				objExport.fileType = 'pdf';
				objExport.constAcceptanceRequestId = ListAcceptanceRequest[0],
				
				mapService.exportListAcceptanceRequest(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được phiếu yêu cầu nghiệm thu!"));
				});
			}
			
			if (ListAmaterialHanover.length > 0) {
				mapService.exportListAmaterialHanover(ListAmaterialHanover).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được bàn giao thiết bị!"));
				});
			}
			
			if (ListScene.length > 0) {
				mapService.exportListScene(ListScene).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListScene!"));
				});
			}
			
			if (ListWorkCompConfirm.length > 0) {
				mapService.exportListWorkCompConfirm(ListWorkCompConfirm).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkCompConfirm!"));
				});
			}

			
			if (ListRecoveryMinute.length > 0) {
				mapService.exportListRecoveryMinute(ListRecoveryMinute).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListRecoveryMinute!"));
				});
			}
			
			if (ListQualityMea.length > 0) {
				mapService.exportListQualityMea(ListQualityMea).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListQualityMea!"));
				});
			}
			
			if (ListBmaterialAcceptance.length > 0) {
				mapService.exportListBmaterialAcceptance(ListBmaterialAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListBmaterialAcceptance!"));
				});
			}
			////////////////////////////////////////////////////
			
			if (ListAbCompleteWork.length > 0) {
				mapService.exportListAbCompleteWork(vm.item.constructId).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbCompleteWork!"));
				});
			}
			
			if (ListAbMaterialCompare.length > 0) {
				mapService.exportListAbMaterialCompare(ListAbMaterialCompare).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbMaterialCompare!"));
				});
			}
			
			if (ListAbSettlementValue.length > 0) {
				// của Huy
				mapService.exportListAbSettlementValue(ListAbSettlementValue[0]).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbSettlementValue!"));
				});
			}
			
			if (ListAbSettlementWork.length > 0) {
				mapService.exportListAbSettlementWork(vm.item.constructId).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbSettlementWork!"));
				});
			}
			
			if (ListCategoryAcceptance.length > 0) {
				mapService.exportListCategoryAcceptance(ListCategoryAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListCategoryAcceptance!"));
				});
			}
			
			if (ListCompleteDrawing.length > 0) {
				mapService.exportListCompleteDrawing(ListCompleteDrawing).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListCompleteDrawing!"));
				});
			}
			
			if (ListGroundHanover.length > 0) {
				var objExport = {
						constructId:vm.item.constructId,
						docOrPdf:false,
						constrGroundHandoverId:ListGroundHanover[0],
				}
				mapService.exportListGroundHanover(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListGroundHanover!"));
				});
			}
			
			if (ListOrganizationPlan.length > 0) {
				mapService.exportListOrganizationPlan(ListOrganizationPlan).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListOrganizationPlan!"));
				});
			}
			
			if (ListWorkLog.length > 0) {
				var objExport = {
						constrWorkLogsId:ListWorkLog[0],
					
				};
				mapService.exportListWorkLog(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkLog!"));
				});
			}
			
			if (ListWorkLogLable.length > 0) {
				var objExport = {
						constructId:vm.item.constructId,
				};
				mapService.exportListWorkLogLable(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkLogLable!"));
				});
			}
			
			if (ListStateHanover.length > 0) {
				mapService.exportListStateHanover(ListStateHanover).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListStateHanover!"));
				});
			}
			
			if (ListDetailSettlement.length > 0) {
				mapService.exportListDetailSettlement(ListDetailSettlement).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListDetailSettlement!"));
				});
			}
			
			if (ListDetailSettlementProposal.length > 0) {
				mapService.exportListDetailSettlementProposal(ListDetailSettlementProposal).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListDetailSettlementProposal!"));
				});
			}
			
			if (ListDistanceUnload.length > 0) {
				mapService.exportListDistanceUnload(ListDistanceUnload).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListDistanceUnload!"));
				});
			}
			
			if (ListTitAzi.length > 0) {
				
				mapService.exportListTitAzi(vm.dataItem).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListTitAzi!"));
				});
			}
			
			if (ListWorkItemAcceptance.length > 0) {
				mapService.exportListWorkItemAcceptance(ListWorkItemAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkItemAcceptance!"));
				});
			}
			
			if (ListAbCompleteWorkDescribe.length > 0) {
				var objExport = {
						abComplementWorkDescribeId:ListAbCompleteWorkDescribe[0],
						constructId:vm.item.constructId,
						constructionId : vm.item.constructId,
					
				};
				mapService.exportListAbCompleteWorkDescribe(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbCompleteWorkDescribe!"));
				});
			}
			
			if (ListAbDetailPrice.length > 0) {
				var objExport = {
						constructId:vm.item.constructId,
					
				};
				mapService.exportListAbDetailPrice(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbDetailPrice!"));
				});
			}
			
			if (ListMonitorAssign.length > 0) {
				mapService.exportListMonitorAssign(ListMonitorAssign).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListMonitorAssign!"));
				});
			}
		}
		
		
		vm.exportFileDoc = function exportFileDoc(){
			if(vm.checkHaveDone <= 0){
				toastr.warning(gettextCatalog.getString("Bạn chưa chọn bản ghi nào để Export !"));
				return;
			}
			var ListAcceptanceRequest = [];
			var ListAmaterialHanover = [];
			var ListScene = [];
			var ListWorkCompConfirm = [];

			var ListRecoveryMinute = [];
			var ListQualityMea = [];
			var ListBmaterialAcceptance = [];
			var ListConstructionAcceptance = [];
			
			var ListAbCompleteWork = [];
			var ListAbMaterialCompare = [];
			var ListAbSettlementValue = [];
			var ListAbSettlementWork = [];
			var ListCategoryAcceptance = [];
			var ListCompleteDrawing = [];
			var ListGroundHanover = [];
			var ListOrganizationPlan = [];
			var ListWorkLog = [];
			var ListWorkLogLable = [];
			var ListStateHanover = [];
			var ListDetailSettlement = [];
			var ListDetailSettlementProposal = [];
			var ListDistanceUnload = [];
			var ListTitAzi = [];
			var ListWorkItemAcceptance = [];
			var ListAbCompleteWorkDescribe = [];
			var ListAbDetailPrice = [];
			var ListComplementWorkEstimateAB = [];
			var ListMonitorAssign = [];
			var value = $("[name='checkItem']");
			for(var i = 0; i < value.length; i++) {
				var dataItem = value[i];
				if(dataItem.checked) {
					switch(dataItem.value) {
				    case 'CONSTR_ACCEPTANCE_REQUEST':
				    	ListAcceptanceRequest.push(dataItem.id);
				        break;
				    case 'A_MATERIAL_HANDOVER':
				    	ListAmaterialHanover.push(dataItem.id);
				        break;
				    case 'SCENE_GENERATE_WORK':
				    	ListScene.push(dataItem.id);
				        break;
				    case 'CONSTR_WORK_COMP_CONFIRM':
				    	ListWorkCompConfirm.push(dataItem.id);
				        break;
				    case 'A_MATERIAL_RECOVERY_MINUTES':
				    	ListRecoveryMinute.push(dataItem.id);
				        break;
				    case 'QUALITY_CABLE_MEA_REPORT':
				    	ListQualityMea.push(dataItem.id);
				        break;
				    case 'B_MATERIAL_ACCEPTANCE':
				    	ListBmaterialAcceptance.push(dataItem.id);
				        break;
				    case 'CONSTRUCTION_ACCEPTANCE':
				    	ListConstructionAcceptance.push(dataItem.id);
				        break;
				    case 'AB_COMPLEMENT_WORK_DESCRIBE':
				    	ListAbCompleteWorkDescribe.push(dataItem.id);
				        break;
				    case 'AB_COMPLEMENT_WORK':
				    	ListAbCompleteWork.push(dataItem.id);
				        break;
				    case 'AB_MATERIAL_COMPARE':
				    	ListAbMaterialCompare.push(dataItem.id);
				        break;
				    case 'AB_SETTLEMENT_VALUE':
				    	ListAbSettlementValue.push(dataItem.id);
				        break;
				    case 'AB_SETTLEMENT_WORK':
				    	ListAbSettlementWork.push(dataItem.id);
				        break;
				    case 'CATEGORY_ACCEPTANCE':
				    	ListCategoryAcceptance.push(dataItem.id);
				        break;
				    case 'COMPLETION_DRAWING':
				    	ListCompleteDrawing.push(dataItem.id);
				        break;
				    case 'CONSTR_GROUND_HANDOVER':
				    	ListGroundHanover.push(dataItem.id);
				        break;
				    case 'CONSTR_ORGANIZATION_PLAN':
				    	ListOrganizationPlan.push(dataItem.id);
				        break;
				    case 'CONSTR_WORK_LOGS':
				    	ListWorkLog.push(dataItem.id);
				        break;
				    case 'CONSTR_WORK_LOGS_LABEL':
				    	ListWorkLogLable.push(dataItem.id);
				        break;
				    case 'CURRENT_STATE_HANDOVER':
				    	ListStateHanover.push(dataItem.id);
				        break;
				    case 'DETAIL_SETTLEMENT_EVALUATE':
				    	ListDetailSettlement.push(dataItem.id);
				        break;
				    case 'DETAIL_SETTLEMENT_PROPOSAL':
				    	ListDetailSettlementProposal.push(dataItem.id);
				        break;
				    case 'DISTANCE_UNLOAD_CONSTR_MINUTES':
				    	ListDistanceUnload.push(dataItem.id);
				        break;
				    case 'TIT_AZI_CONSTR_ACCEPT':
				    	ListTitAzi.push(dataItem.id);
				    	vm.dataItem ={};
				    	vm.dataItem.contractId=vm.item.contractId;
				    	vm.dataItem.titAziConstrAcceptId=dataItem.id;
				        break;
				    case 'WORK_ITEMS_ACCEPTANCE':
				    	var listWorkItemAcceptance = {};
				    	listWorkItemAcceptance.constructId = vm.item.constructId;
				    	listWorkItemAcceptance.workItemsAcceptanceId=dataItem.id;
				    	ListWorkItemAcceptance.push(listWorkItemAcceptance);
				        break;
				    case 'AB_DETAIL_PRICE':
				    	ListAbDetailPrice.push(dataItem.id);
				        break;
				    case 'MONITOR_MISSION_ASSIGN':
				    	ListMonitorAssign.push(dataItem.id);
				        break;
				        
				    default:
				        break;
					}
				}
			}
			
			
			if (ListConstructionAcceptance.length > 0) {
				mapService.exportListConstructionAcceptanceDoc(ListConstructionAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được Biên bản nghiệm thu công trình đưa vào sử dụng!"));
				});
			}
			
			
			if (ListAcceptanceRequest.length > 0) {
				var objExport = {};
				objExport.fileType = 'doc';
				objExport.constAcceptanceRequestId = ListAcceptanceRequest[0],
				
				mapService.exportListAcceptanceRequest(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được phiếu yêu cầu nghiệm thu!"));
				});
			}
			
			if (ListAmaterialHanover.length > 0) {
				mapService.exportListAmaterialHanoverDoc(ListAmaterialHanover).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được bàn giao thiết bị!"));
				});
			}
			
			if (ListScene.length > 0) {
				mapService.exportListSceneDoc(ListScene).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListScene!"));
				});
			}
			
			if (ListWorkCompConfirm.length > 0) {
				mapService.exportListWorkCompConfirmDoc(ListWorkCompConfirm).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkCompConfirm!"));
				});
			}

			
			if (ListRecoveryMinute.length > 0) {
				mapService.exportListRecoveryMinuteDoc(ListRecoveryMinute).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListRecoveryMinute!"));
				});
			}
			
			if (ListQualityMea.length > 0) {
				mapService.exportListQualityMeaDoc(ListQualityMea).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListQualityMea!"));
				});
			}
			
			if (ListBmaterialAcceptance.length > 0) {
				mapService.exportListBmaterialAcceptanceDoc(ListBmaterialAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListBmaterialAcceptance!"));
				});
			}
			////////////////////////////////////////////////////
			
			if (ListAbCompleteWork.length > 0) {
				mapService.exportListAbCompleteWorkDoc(vm.item.constructId).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbCompleteWork!"));
				});
			}
			
			if (ListAbMaterialCompare.length > 0) {
				mapService.exportListAbMaterialCompareDoc(ListAbMaterialCompare).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbMaterialCompare!"));
				});
			}
			
			if (ListAbSettlementValue.length > 0) {
				// của Huy
				mapService.exportListAbSettlementValueDoc(ListAbSettlementValue[0]).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbSettlementValue!"));
				});
			}
			
			if (ListAbSettlementWork.length > 0) {
				mapService.exportListAbSettlementWorkDoc(vm.item.constructId).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbSettlementWork!"));
				});
			}
			
			if (ListCategoryAcceptance.length > 0) {
				mapService.exportListCategoryAcceptanceDoc(ListCategoryAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListCategoryAcceptance!"));
				});
			}
			
			if (ListCompleteDrawing.length > 0) {
				toastr.warning(gettextCatalog.getString("Bản vẽ hoàn công chỉ export được file pdf !"));
				/*mapService.exportListCompleteDrawingDoc(ListCompleteDrawing).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListCompleteDrawing!"));
				});*/
			}
			
			if (ListGroundHanover.length > 0) {
				var objExport = {
						constructId:vm.item.constructId,
						docOrPdf:true,
						constrGroundHandoverId:ListGroundHanover[0],
				}
				mapService.exportListGroundHanoverDoc(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListGroundHanover!"));
				});
			}
			
			if (ListOrganizationPlan.length > 0) {
				toastr.warning(gettextCatalog.getString("Phương án tổ chức thi công chỉ export được file pdf !"));
				/*mapService.exportListOrganizationPlanDoc(ListOrganizationPlan).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("Chỉ export được file pdf ListOrganizationPlan!"));
				});*/
			}
			
			if (ListWorkLog.length > 0) {
				var objExport = {
						constrWorkLogsId:ListWorkLog[0],
					
				};
				mapService.exportListWorkLogDoc(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkLog!"));
				});
			}
			
			if (ListWorkLogLable.length > 0) {
				var objExport = {
						constructId:vm.item.constructId,
				};
				mapService.exportListWorkLogLableDoc(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkLogLable!"));
				});
			}
			
			if (ListStateHanover.length > 0) {
				mapService.exportListStateHanoverDoc(ListStateHanover).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListStateHanover!"));
				});
			}
			
			if (ListDetailSettlementProposal.length > 0) {
				mapService.exportListDetailSettlementProposalDoc(ListDetailSettlementProposal).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListDetailSettlementProposal!"));
				});
			}
			
			if (ListDistanceUnload.length > 0) {
				mapService.exportListDistanceUnloadDoc(ListDistanceUnload).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListDistanceUnload!"));
				});
			}
			
			if (ListTitAzi.length > 0) {
				mapService.exportListTitAziDoc(vm.dataItem).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListTitAzi!"));
				});
			}
			
			if (ListWorkItemAcceptance.length > 0) {
				mapService.exportListWorkItemAcceptanceDoc(ListWorkItemAcceptance).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListWorkItemAcceptance!"));
				});
			}
			
			if (ListAbCompleteWorkDescribe.length > 0) {
				var objExport = {
						abComplementWorkDescribeId:ListAbCompleteWorkDescribe[0],
						constructId:vm.item.constructId,
						constructionId : vm.item.constructId,
					
				};
				mapService.exportListAbCompleteWorkDescribeDoc(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbCompleteWorkDescribe!"));
				});
			}
			
			if (ListAbDetailPrice.length > 0) {
				var objExport = {
						constructId:vm.item.constructionId,
					
				};
				mapService.exportListAbDetailPriceDoc(objExport).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListAbDetailPrice!"));
				});
			}
			
			
			if (ListMonitorAssign.length > 0) {
				mapService.exportListMonitorAssignDoc(ListMonitorAssign).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được ListMonitorAssign!"));
				});
			}
		}
		
		function chkSelectAll(item) {
			var checkBoxAllHS = document.getElementById("checkBoxAllHS").checked;
			
			if(checkBoxAllHS) {
				$("[name='checkItem']").prop( "checked", true );
			}else {
				$("[name='checkItem']").prop( "checked", false );
			}
			vm.countChecked();

		}
			vm.checkHaveDone=0;
			vm.countChecked = function() {
				vm.checkHaveDone = $( "[name='checkItem']:checked" ).length;
			};
				
		
		function exportFileExcell() {
			if(vm.mapDatatable.length > 0) {
				$('#loading').show();
				mapService.exportFileExcell(vm.mapDatatable).then(function(data) {
					if(data.fileName != null)
					window.location = Constant.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
				}).catch(function(data, status) {
					toastr.warning(gettextCatalog.getString("không export được danh sách!"));
					$('#loading').hide();
				}).finally(function(){
					$('#loading').hide();
				});
			}
		}
		
		$scope.$on("ChangeStatusApproval", function(event, result){ 
			   if(result){
				   detail();
				   
			   }
			  });
		$scope.$on("ChangeStatus", function(event, result){ 
			   if(result){
				   detail();
			   }
			  });
		$scope.$on("ChangeStatusHuyDuyet", function(event, result){ 
			   if(result){
				   detail();
			   }
			  });

	}


})();