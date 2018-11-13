(function() {
	'use strict';

	var controllerId = 'estimateABController';

	angular.module('MetronicApp').controller(controllerId,
			estimateABController);

	/* @ngInject */
	function estimateABController($scope,  $q, $rootScope, $timeout, Constant,
			kendoConfig, estimatesABService, gettextCatalog, $kWindow,
			CommonService, Restangular, PopupConst, ProposalEvaluation, theSignCA) {
		var vm = this;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.disEstimate3 = false;
		vm.disableAll = false;
		vm.disableEstimate1 = false;
		vm.disableEstimate3 = false;
		vm.disableEstimate6 = false;
		vm.disableBtnSave = false;
		vm.disableBtnExpDoc = false;
		vm.disableBtnExpPdf = false;
		vm.initDataForm1 = {};
		vm.initDataForm6 = {};
		vm.checkdata6 = {};
		vm.pathFile = Constant.DOWNLOAD_SERVICE ; 
		vm.disableBtnApprpval = true;
		vm.showDetail = true;
		vm.showForm2 = false;
		vm.showForm3 = false;
		vm.showForm4 = false;
		vm.showForm5 = false;
		vm.showForm6 = false;
		vm.showFormAB1 = showFormAB1;
		vm.showFormAB2 = showFormAB2;
		vm.showFormAB3 = showFormAB3;
		vm.showFormAB4 = showFormAB4;
		vm.showFormAB5 = showFormAB5;
		vm.showFormAB6 = showFormAB6;
		vm.sendApprpval = sendApprpval;
		vm.saveForm = saveForm;
		vm.estimatesAB3 = {code: '', contractDateSign:'', constructId: '', constructName: '', constructAddress:'', stationcode:'', statusCA:''};
		vm.estimatesAB1 = {};
		vm.estimatesAB6 = {};
		vm.estimatesAB2 = {};
		vm.estimatesAB4 = {};
		vm.estimatesAB6 = {};
		vm.tmpEstimateAB1 = {};
		vm.role = [];
		vm.folder = '';
		vm.estimatesAB5 = {};
		var estimatesUserId ='';
		var estimatesCatEmployeeId ='';
		vm.abDetailPrice={};
		var flagFunctionForm = 1;
		vm.bean={};
		var statusCaSettlementEvaluate = 0;
		//thông tin chung
		vm.item = ProposalEvaluation.getItem();
	
		/*$scope.$on("ProposalEvaluation.detail", function (event, item) {
	         if(item != undefined){
	          vm.item = item;
	          initFormData();
	         }else{
	          console.error("không nhận được dữ liệu!");
	         }
	    });*/
		//minhpvn ---------------------------
		
		vm.thutruongdonviForm4=[];
		vm.resultthutruongdonviForm4 = {
				
				constructId : vm.item.constructId,
				roleid : Constant.ROLE_ID["employee_roleID_CDT_DDPN"],	//103
				contractCode:vm.item.contractCode
		};
		//
		vm.giamdocnhathauForm4=[];
		vm.resultgiamdocnhathauForm4 = {
				
				constructId : vm.item.constructId,
				roleid :Constant.ROLE_ID["employee_roleID_DT_GDNT"],	//105
				contractCode:vm.item.contractCode
			};
		vm.giamsatcdtForm4=[];
		vm.resultgiamsatcdtForm4 = {
				
				constructId : vm.item.constructId,
				roleid :Constant.ROLE_ID["employee_roleID_CDT_PTGST"],	//102
				contractCode: vm.item.contractCode
			};
		vm.thicongnhathauForm4=[];
		vm.resultthicongnhathauForm4 = {
				
				constructId : vm.item.constructId,
				roleid :Constant.ROLE_ID["employee_roleID_DT_PTTC"],	 //106
				contractCode:vm.item.contractCode
			};
		// lay ID trong file config
		estimatesABService.getRoleIdForm4().then(function(data) {
//			vm.role = data;
//			vm.resultthutruongdonviForm4.roleid = vm.role[2];
//			vm.resultgiamdocnhathauForm4.roleid = vm.role[3];
//			vm.resultgiamsatcdtForm4.roleid = vm.role[4];
//			vm.resultthicongnhathauForm4.roleid = vm.role[1];
			loadCbb1Form4();
			loadCbb2Form4();
			loadCbb3Form4();
			loadCbb4Form4();
			initFormData();
		}).catch(function() {
			toastr.error("Có lỗi xảy ra");
		});
	
		//end minhpvn-----------------------------
		
		function mapdataestimatesAB5(){
			vm.estimatesAB5.constructAddress = vm.item.constrtAddress;
			vm.estimatesAB5.stationcode = vm.item.stationCode;
			vm.estimatesAB5.statusCa = 0;
			vm.estimatesAB5.constrtCode = vm.item.constrtCode;
			vm.estimatesAB5.constructId = vm.item.constructId;
			vm.estimatesAB5.contractCode = vm.item.contractCode;
			vm.estimatesAB5.contractDateSign = vm.item.signed_date;			
			vm.estimatesAB5.constrtName = vm.item.constrtName;			
		}
		console.log(vm.item);
		
		function initFormData(){
			estimate3();
			checkSave3();	
			checkSave5();			
			//Lấy giám đốc tư vấn
			vm.findDesignConsultants ={};
			vm.findDesignConsultants.constructId = vm.estimatesAB3.constructId;
			vm.findDesignConsultants.contractCode = vm.estimatesAB3.contractCode;
			vm.findDesignConsultants.roleid = Constant.ROLE_ID["employee_roleID_CDT_DDPN"];
			estimatesABService.getlistAmonitorConstruction(vm.findDesignConsultants).then(function(d) {
				vm.listDesignConstruction = d.plain();
			}, function(errResponse) {
//				console.error('Error while fetching locatorbank');
			});
			
			
			//Giam đốc nhà thầu
			vm.findContractors ={};
			vm.findContractors.constructId = vm.estimatesAB3.constructId;
			vm.findContractors.contractCode = vm.estimatesAB3.contractCode;
			vm.findContractors.roleid = Constant.ROLE_ID["employee_roleID_CDT_DDPN"];
			estimatesABService.getlistAmonitorConstruction(vm.findContractors).then(function(d) {
				vm.listContractorsConstruction = d.plain();
				vm.estimatesAB1.bdirectorId = vm.listContractorsConstruction[0].userId;
			}, function(errResponse) {
//				console.error('Error while fetching locatorbank');
			});
			
//			vm.listThanhPhanKy=[];
			//minhpvn -- lay all nhan vien trong cat_employee
			vm.findthanhphanky = {};
			vm.findthanhphanky.constructId = vm.estimatesAB3.constructId;
			vm.findthanhphanky.contractCode = vm.estimatesAB3.contractCode;
			estimatesABService.getlistAmonitorConstructionForm6(vm.findthanhphanky).then(function(db) {
				vm.listThanhPhanKy = db.plain();
				vm.estimatesAB6.aheadConstructId = vm.listThanhPhanKy[0].userId;
				vm.estimatesAB6.aheadTechnicalId = vm.listThanhPhanKy[0].userId;
				vm.estimatesAB6.aheadFinanceId = vm.listThanhPhanKy[0].userId;
				vm.estimatesAB6.bheadConstructId = vm.listThanhPhanKy[0].userId;
				vm.estimatesAB6.bheadAccountId = vm.listThanhPhanKy[0].userId;
			}, function(errResponse) {
//				console.error('Error while fetching locatorbank');
			});
			
			
			
			
			
			//Lay thu truong
			vm.findAmonitor = {};
			vm.findAmonitor.constructId = vm.estimatesAB3.constructId;
			vm.findAmonitor.contractCode = vm.estimatesAB3.contractCode;
			vm.findAmonitor.roleid = Constant.ROLE_ID["employee_roleID_CDT_PTGST"];
			estimatesABService.getlistAmonitorConstruction(vm.findAmonitor).then(function(d) {
				vm.listAmonitorConstruction = d.plain();
				vm.estimatesAB1.adirectorId = vm.listAmonitorConstruction[0].userId;
				
			}, function(errResponse) {
//				console.error('Error while fetching locatorbank');
			});
			//init data form 1
			estimatesUserId = Constant.user.srvUser.userId;
			estimatesCatEmployeeId = Constant.getUser().srvUser.catEmployeeId;
			vm.item.statusName = "ST";
			showFormAB1();
			
			// check 
			estimatesABService.getStatusEvaluate(vm.estimatesAB3.constructId).then(
					function(data) {
						statusCaSettlementEvaluate = data.statusCaSettlementEvaluate;
					},
					function(errResponse) {
						//toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi get statusCa tham dinh !"));
						//return;
					});
		}
		
		function showFormAB6(){
			vm.showDetail = false;
			vm.showForm2 = false;
			vm.showForm3 = false;
			vm.showForm4 = false;
			vm.showForm5 = false;			
			vm.showForm6 = true;
			flagFunctionForm = 6;
			checkDataForm6();
			visibleButton();
			fillColorButton(flagFunctionForm);
		}

		function showFormAB4(){
//			loadCbb1Form4();
//			loadCbb2Form4();
//			loadCbb3Form4();
//			loadCbb4Form4();
			vm.disableBtnApprpval = true;
			vm.showDetail = false;
			vm.showForm2 = false;
			vm.showForm3 = false;	
			vm.showForm4 = true;
			vm.showForm5 = false;
			vm.showForm6 = false;
			vm.isHaveDataForm4 = false;	
			fetchAllEstimateAB4();
			loadDataTable4([]);
			flagFunctionForm = 4;
			fillColorButton(flagFunctionForm);
			showButton();
		}
		
		function showFormAB5(){
			vm.disableBtnApprpval = true;
			vm.showDetail = false;
			vm.showForm2 = false;
			vm.showForm3 = false;	
			vm.showForm4 = false;
			vm.showForm5 = true;
			vm.showForm6 = false;
			loadDataTable5([]);
			mapdataestimatesAB5();
			checkSave5();
			if(vm.check5!=null){				
				vm.estimatesAB5.bdirectorId = vm.check5.bdirectorId;
				vm.estimatesAB5.adirectorId = vm.check5.adirectorId;
				vm.estimatesAB5.code = vm.check5.code;
				vm.estimatesAB5.statusCa = vm.check5.statusCa;
				if(vm.estimatesAB5.statusCa == 1){
					vm.estimatesAB5.statusCA = 'Trình ký';	
					vm.disableBtnApprpval = true;
					vm.disableAll = true;
				}else if(vm.estimatesAB5.statusCa == 2){
					vm.disableBtnApprpval = true;
					vm.estimatesAB5.statusCA = 'Đã ký';
					vm.disableAll = true;
				}else if(vm.estimatesAB5.statusCa ==3){
					vm.estimatesAB5.statusCA = 'Từ chối ký';
					vm.disableBtnApprpval = false;
					vm.disableAll = false;
				}else if(vm.estimatesAB5.statusCa ==0){
					vm.estimatesAB5.statusCA = 'Soạn thảo';
					vm.disableBtnApprpval = false;
					vm.disableAll = false;
				}else{
					vm.estimatesAB5.statusCA = 'Soạn thảo';
				}
				vm.disableForm = false;
				vm.disableBtnExpDoc = false;
				vm.disableBtnExpPdf = false;
				
			}else{
				vm.estimatesAB5.bdirectorId = vm.listContractorsConstruction[0].userId;
				vm.estimatesAB5.adirectorId = vm.listAmonitorConstruction[0].userId;
				vm.estimatesAB5.statusCA = 'Soạn thảo';
				vm.estimatesAB5.statusCa = 0;
				vm.disableBtnApprpval = true;
				vm.disableBtnExpDoc = true;
				vm.disableBtnExpPdf = true;
			}						
			fetchAllEstimateAB5();			
			flagFunctionForm = 5;
			vm.disableBtnSave = false;
			fillColorButton(flagFunctionForm);
			showButton();
		}
		
		function showFormAB1(){
			vm.disableBtnApprpval = true;
			vm.showDetail = true;
			vm.showForm2 = false;
			vm.showForm3 = false;
			vm.showForm4 = false;
			vm.showForm5 = false;			
			vm.showForm6 = false;
			flagFunctionForm = 1;
			checkDataForm1();
			fillColorButton(flagFunctionForm);
			vm.disableBtnSave = false;
			
		}
		
		function showFormAB2(){
			vm.disableBtnApprpval = false;
			vm.showDetail = false;
			vm.showForm2 = true;
			vm.showForm3 = false;	
			vm.showForm4 = false;
			vm.showForm5 = false;
			vm.showForm6 = false;
			vm.disableBtnExpDoc = false;
			vm.disableBtnExpPdf = false;
			loadDataTable2([]);
			loadCbb1();
			loadCbb2();
			fetchAllEstimateAB2();
			flagFunctionForm = 2;
			vm.disableBtnSave = false;
			fillColorButton(flagFunctionForm);
			showButton();
		}
		
		function showFormAB3(){
			vm.disableBtnApprpval = false;
			vm.showDetail = false;
			vm.showForm2 = false;
			vm.showForm3 = true;	
			vm.showForm4 = false;
			vm.showForm5 = false;
			vm.showForm6 = false;
			vm.disableBtnSave = false;
			estimate3();
			loadDataTable3([]);	
			checkSave3();
			//Kiem tra checkSave3();
			if(vm.check3!=null){
				vm.estimatesAB3.bdirectorId = vm.check3.bdirectorId;
				vm.estimatesAB3.adirectorId = vm.check3.adirectorId;
				vm.estimatesAB3.cdesignDirectionId = vm.check3.cdesignDirectionId;
				vm.estimatesAB3.code = vm.check3.code;
				vm.estimatesAB3.statusCa = vm.check3.statusCa;
				if(vm.estimatesAB3.statusCa == 1){
					vm.estimatesAB3.statusCA = 'Trình ký';		
					vm.disableBtnApprpval = true;
					vm.disableAll = true;
				}else if(vm.estimatesAB3.statusCa == 2){
					vm.estimatesAB3.statusCA = 'Đã ký';
					vm.disableBtnApprpval = true;
					vm.disableAll = true;
				}else if(vm.estimatesAB3.statusCa ==3){
					vm.estimatesAB3.statusCA = 'Từ chối ký';
					vm.disableAll = false;
				}else if(vm.estimatesAB3.statusCa ==0){
					vm.estimatesAB3.statusCA = 'Soạn thảo';	
					vm.disableBtnApprpval = false;
					vm.disableAll = false;
				}
				vm.disableForm = false;				
				vm.disableBtnExpDoc = false;
				vm.disableBtnExpPdf = false;
			}else{
				vm.estimatesAB3.bdirectorId = vm.listContractorsConstruction.length == 0 ? null:vm.listContractorsConstruction[0].userId;
				vm.estimatesAB3.adirectorId = vm.listAmonitorConstruction.length == 0 ? null:vm.listAmonitorConstruction[0].userId;
				vm.estimatesAB3.cdesignDirectionId = vm.listDesignConstruction.length == 0 ? null:vm.listDesignConstruction[0].userId;
				vm.estimatesAB3.statusCA = 'Soạn thảo';
				vm.estimatesAB3.statusCa = 0;
				vm.disableBtnApprpval = true;
				vm.disableBtnExpDoc = true;
				vm.disableBtnExpPdf = true;
				vm.disableAll = false;
			}
			fetchAllEstimateAB3();
			flagFunctionForm = 3;
			fillColorButton(flagFunctionForm);
			showButton();
		}		
		
		function checkSave3(){
			estimatesABService.checkSave3(vm.estimatesAB3.constructId).then(function (data) {
				vm.check3 = data.plain();
			}, function(errResponse) {
				//console.error('Error while fetching object type');
			});				
		}

		function checkSave5(){
			vm.estimatesAB5.constructId = vm.item.constructId;
			estimatesABService.checkSave5(vm.estimatesAB5.constructId).then(function (data) {
				vm.check5 = data.plain();
			}, function(errResponse) {
				//console.error('Error while fetching object type');
			});				
		}		

		//Lấy dữ liệu biểu 3
		function fetchAllEstimateAB3(){
			estimatesABService.getEstimateAB3(vm.estimatesAB3.constructId).then(function(d) {
				refreshGrid(d.plain(),vm.tableAbForm3);
			}, function(errResponse) {
				console.error('Error while fetching object type');
			});
		}
		
		function refreshGrid(d, grid) {
        	grid.dataSource.data(d);
        	grid.refresh();
        }
		
		function onChange(){
			if (vm.tableAbForm3.select().length > 0) {
				var tr = vm.tableAbForm3.select().closest("tr");
				var dataItem = vm.tableAbForm3.dataItem(tr);
				vm.estimateAB3 = dataItem;
			}
		}
		
		// Bảng diễn giải khối lượng xây lắp hoàn thành
		vm.record = 0;
        vm.record1 = 0;
		function loadDataTable3(data) {			
			vm.gridOptions3 = kendoConfig.getGridOptions({
						autoBind : true,						
						dataSource: {
		                    type: "odata",
		                    transport: {
		                        read: data
		                    },
		                    
		                    group: {
		                        field: "type",
		                        
		                    }
			            },					
						change : onChange,
						noRecords : true,
						editable : false,
						dataBinding: function () {
							vm.record = 0;
							vm.record1 = 0;
						},
						messages : {
							noRecords : gettextCatalog.getString("Không có kết quả nào")
						},
						columns : [{
									title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>STT</b>"),
									template: function (data) {
										if(data.type == 1) {return ++vm.record}else{return ++vm.record1}
									},		
									field : "stt",
									width : 70,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:center;"},
								},
								{
									title : gettextCatalog.getString("Mã hiệu đơn giá"),
									field : "workItemCode",
									width : 150,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:left;"},
								},
								{
									title : gettextCatalog.getString("Nội dung công việc"),
									field : "workItemName",
									width : 180,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:left;"},
								},
								{
									title : gettextCatalog.getString("Đơn vị"),
									field : "unit",
									width : 100,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:left;"},
								},
								{
									title : gettextCatalog.getString("KL theo HĐ"),
									field : "workAmount",
									width : 130,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:right;"},
							         template: function(dataItem) {
				                            if ($.isNumeric(dataItem.workAmount) && dataItem.workAmount >= 0) {
				                            	dataItem.workAmount = parseFloat(Number(dataItem.workAmount).toFixed(3));
				                                return parseFloat(Number(dataItem.workAmount).toFixed(3));
				                            }
				                            else {dataItem.workAmount = 0;
				                            return 0;}
				                        },
				                    format: "{0:n3}",
				                    decimals: 3,
									validation: {  min: 0,max : 99999999, required: true , message :"Số quá lớn"}
								},
								{
									title : gettextCatalog.getString("KL hoàn thành QT"),
									field : "evaluateQuantity",
									width : 150,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:right;"},
							         template: function(dataItem) {
				                            if ($.isNumeric(dataItem.evaluateQuantity) && dataItem.evaluateQuantity >= 0) {
				                            	dataItem.evaluateQuantity = parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
				                                return parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
				                            }
				                            else {dataItem.evaluateQuantity = 0;
				                            return 0;}
				                        },
				                        format: "{0:n3}",
				                    decimals: 3,
									validation: {  min: 0,max : 99999999, required: true , message :"Số quá lớn"}
								},{
									title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Khối lượng chênh lệch</b>"),
									
									columns : [{
										title : gettextCatalog.getString("Tăng"),										
										field : "tang",
										width : 100,
										headerAttributes: {style:"text-align:center;"},
								         attributes:{style:"text-align:right;"},
								         template: function(dataItem) {
					                            if ($.isNumeric(dataItem.tang) && dataItem.tang >= 0) {
					                            	dataItem.tang = parseFloat(Number(dataItem.tang).toFixed(3));
					                                return parseFloat(Number(dataItem.tang).toFixed(3));
					                            }
					                            else {dataItem.tang = 0;
					                            return 0;}
					                        },
					                        format: "{0:n3}",
					                    decimals: 3,
										validation: {  min: 0,max : 99999999, required: true , message :"Số quá lớn"}
									},
									{
										title : gettextCatalog.getString("Giảm"),										
										field : "giam",
										width : 100,
										headerAttributes: {style:"text-align:center;"},
								         attributes:{style:"text-align:right;"},
								    	 template: function(dataItem) {
					                            if ($.isNumeric(dataItem.giam) && dataItem.giam >= 0) {
					                            	dataItem.giam = parseFloat(Number(dataItem.giam).toFixed(3));
					                                return parseFloat(Number(dataItem.giam).toFixed(3));
					                            }
					                            else {dataItem.giam = 0;
					                            return 0;}
					                        },
					                        format: "{0:n3}",
					                        decimals: 3,
									} ]	,	
									headerAttributes: {style:"text-align:center;"},
								},
								{
									title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Ghi chú</b>"),
									field : "ghichu",
									width : 130,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:right;"},
								},{
									title : gettextCatalog.getString("Nội dung"),
									field : "type",
									hidden:true,
									groupHeaderTemplate: " #if(value == '1'){#I. Nội dung công việc trong hợp đồng#}else{#II. Nội dung công việc ngoài hợp đồng (phụ lục hợp đồng)#}#",
									width : 120
								}
							]});			
		}
		
		// Bảng diễn giải khối lượng xây lắp hoàn thành
		function loadDataTable4(data) {			
			vm.gridOptions4 = kendoConfig.getGridOptions({
						autoBind : true,						
						dataSource: {
		                    type: "odata",
		                    transport: {
		                        read: data
		                    },		                 
			            },					
						change : onChange,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog.getString("Không có kết quả nào")
						},
						columns : [
								{
									title : gettextCatalog.getString("STT"),
									headerAttributes: {style:"text-align:center;"},
									attributes:{style:"text-align:center;"},
									field :"stt",
									template: dataItem => $("#mainGrid4").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
									width : 70
								},
								{
									title : gettextCatalog.getString("Mã hiệu"),
									headerAttributes: {style:"text-align:center;"},
									attributes:{style:"text-align:left;"},
									field : "workItemCode",
									width : 100
								},
								{
									title : gettextCatalog.getString(" ND công việc"),
									headerAttributes: {style:"text-align:center;"},
									attributes:{style:"text-align:left;"},
									field : "workItemName",
									width : 200
								},
								{
									title : gettextCatalog.getString("Đơn vị"),
									headerAttributes: {style:"text-align:center;"},
									attributes:{style:"text-align:left;"},
									field : "unit",
									width : 90
								},
								{
									title : gettextCatalog.getString("Diễn giải"),
									headerAttributes: {style:"text-align:center;"},
									attributes:{style:"text-align:left;"},
									field : "evaluateComments",
									width : 150
								},
								{
									title : gettextCatalog.getString("Khối lượng"),
									headerAttributes: {style:"text-align:center;"},
									attributes:{style:"text-align:right;"},
									field : "evaluateQuantity",
									width : 150,
									template: function(dataItem) {
			                            if ($.isNumeric(dataItem.evaluateQuantity) && dataItem.evaluateQuantity >= 0) {
			                            	dataItem.evaluateQuantity = parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
			                                return parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
			                            }
			                            else{dataItem.evaluateQuantity = 0;
			                            return 0;}
			                        },
			                        format: "{0:n3}",
			                    decimals: 3,
								validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
								},
								{
									title : gettextCatalog.getString("Bản vẽ thể hiện"),
									headerAttributes: {style:"text-align:center;"},
									attributes:{style:"text-align:left;"},
									field : "instanceDrawCode",
									width : 150
								},
							]
					});			
		}
		
//minhpvn
		function loadCbb1Form4(){
			//cbb thủ trưởng đơn vị
			estimatesABService.getListEmployeeByRole(vm.resultthutruongdonviForm4).then(function(data) {
				vm.thutruongdonviForm4 = data.plain();
				vm.firstIdThutruongdonvi = vm.thutruongdonviForm4[0].id;
			});
	  }
		function loadCbb2Form4(){
			//cbb giám đốc nhà thầu
			estimatesABService.getListEmployeeByRole(vm.resultgiamdocnhathauForm4).then(function(data) {
				vm.giamdocnhathauForm4 = data.plain();
				vm.firstIdGiamdocnhathau = vm.giamdocnhathauForm4[0].id;
			});
	  }
		function loadCbb3Form4(){
			//cbb PT Giám sát CĐT
			estimatesABService.getListEmployeeByRole(vm.resultgiamsatcdtForm4).then(function(data) {
				vm.giamsatcdtForm4 = data.plain();
				vm.firstIdGiamsatcdt = vm.giamsatcdtForm4[0].id;
			});
	  }
		function loadCbb4Form4(){
			//cbb PT Thi công nhà thầu
			estimatesABService.getListEmployeeByRole(vm.resultthicongnhathauForm4).then(function(data) {
				vm.thicongnhathauForm4 = data.plain();
				vm.firstIdThicongnhathau = vm.thicongnhathauForm4[0].id;
			});
	  }
		//minhpvn end
		vm.checkData4 ={};
		function fetchAllEstimateAB4(){
			vm.estimatesAB3.constructId = vm.item.constructId;
			estimatesABService.getEstimateAB4(vm.item.constructId).then(function(d) {	
				refreshGridForm4(d.plain());
			}, function(errResponse) {
				console.error('Error while fetching object type');
			});
			estimatesABService.getForm4ByConstrId(vm.item.constructId).then(function(data){			
					vm.checkData4 = data.plain();
					vm.bean.thutruongdonvi=data.adirectorId;
					vm.bean.giamdocnhathau=data.bdirectorId;
					vm.bean.giamsatcdt=data.ainChargeMonitorId;
					vm.bean.thicongnhathau=data.binChargeConstructId;
					vm.bean.statusCa = data.statusCa;
					vm.disableBtnSave = vm.bean.statusCa==1?true:false;
				    vm.disableBtnApprpval = vm.bean.statusCa==1?true:false;
					vm.bean.code = data.code;
					if(vm.bean.statusCa >= 0){
						vm.disableBtnExpDoc = false;
						vm.disableBtnExpPdf = false;
					}
					vm.bean.abComplementWorkDescribeId = data.abComplementWorkDescribeId;
					vm.isHaveDataForm4 = true;
					reloadStatusCA(vm.bean.statusCa , 4);
					console.log("vm.bean minh : ", vm.bean);
			},function(error){
					vm.bean.thutruongdonvi=vm.firstIdThutruongdonvi;
					vm.bean.giamdocnhathau=vm.firstIdGiamdocnhathau;
					vm.bean.giamsatcdt=vm.firstIdGiamsatcdt;
					vm.bean.thicongnhathau=vm.firstIdThicongnhathau;
					vm.disableBtnSave = vm.item.statusCa==1?true:false;
					reloadStatusCA(0 , 4);
			});
		}
		
		function estimate3(){
			vm.estimatesAB3.constructAddress = vm.item.constrtAddress;
			vm.estimatesAB3.stationcode = vm.item.stationCode;
			vm.estimatesAB3.isActive = vm.item.isActive;
			vm.estimatesAB3.statusCa = 0;
			vm.estimatesAB3.constrtCode = vm.item.constrtCode;
			vm.estimatesAB3.constructId = vm.item.constructId;
			//vm.estimatesAB3.constructName = vm.item.constrTypeName;
			vm.estimatesAB3.contractCode = vm.item.contractCode;
			vm.estimatesAB3.contractDateSign = vm.item.signed_date;			
			vm.estimatesAB3.constructName = vm.item.constrtName;
			vm.estimatesAB1.investProjectName = vm.item.investProjectName;
			vm.estimatesAB1.contractCode = vm.item.contractCode;
		}
		//ngoccx
		//toanbd
		function loadCbb1Form5(){
			//cbb thủ trưởng đơn vị
				vm.technical=[];
				vm.resultTechnical = {
						
						roleid :Constant.ROLE_ID["employee_roleID_CDT_DDPN"],
						constructId : vm.item.constructId,
						contractCode : vm.item.contractCode
				};
				estimatesABService.getListEmployeeByRole(vm.resultTechnical).then(function(data) {
					vm.technical = data.plain();
					vm.estimatesAB5.adirectorId = vm.technical[0].id;					
				});
		}
		function loadCbb2Form5(){
				//cbb giám đốc nhà thầu
				vm.giamdocnhathau=[];
				vm.resultgiamdocnhathau = {
						
						constructId : vm.item.constructId,
						roleid :Constant.ROLE_ID["employee_roleID_DT_GDNT"],
						contractCode: vm.item.contractCode
					};
				estimatesABService.getListEmployeeByRole(vm.resultgiamdocnhathau).then(function(data) {
					vm.giamdocnhathau = data.plain();
					vm.estimatesAB5.bdirectorId = vm.giamdocnhathau[0].id;
				});
		  }
		//endngoccx
		/*DONG*/
		function loadCbb1(){
			//cbb thủ trưởng đơn vị
				vm.technical=[];
				vm.resultTechnical = {
						type:'1',
						roleid :Constant.ROLE_ID["employee_roleID_CDT_DDPN"],
						constructId : vm.item.constructId,
						contractCode : vm.item.contractCode
				};
				estimatesABService.getListEmployeeByRole(vm.resultTechnical).then(function(data) {
					vm.technical = data.plain();
					vm.estimatesAB2.adirectorId = vm.technical[0].id;
				});
		}
		function loadCbb2(){
				//cbb giám đốc nhà thầu
				vm.giamdocnhathau=[];
				vm.resultgiamdocnhathau = {
						type:'1',
						constructId : vm.item.constructId,
						roleid :Constant.ROLE_ID["employee_roleID_DT_GDNT"],
						contractCode : vm.item.contractCode
					};
				estimatesABService.getListEmployeeByRole(vm.resultgiamdocnhathau).then(function(data) {
					vm.giamdocnhathau = data.plain();
					vm.estimatesAB2.bdirectorId = vm.giamdocnhathau[0].id;
				});
		  }
		
	
		// init table
		function loadDataTable2(data) {			
			vm.gridOptions2 = kendoConfig.getGridOptions({
						autoBind : true,
						dataSource: {
		                    type: "odata",
		                    transport: {
		                        read: data
		                    },
		                    sort: {
		                        field: "workItemCode",
		                        dir: "asc"
		                    },
		                    group: {
		                        field: "type",
		                        
		                    },
		                    aggregate: [
			                                { field: "thanhtien1", aggregate: "sum" },
			                                { field: "thanhtien2", aggregate: "sum" }
				                              ],
			            },					
						change : onChange2,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog.getString("Không có kết quả nào")
						},
						dataBinding: function(){
							vm.record = 0;
							vm.record1 = 0;
						},
						columns : [
								{
									title : gettextCatalog.getString("STT"),
									field : "stt",
									template: function(item){
										if(item.type == 1) {return ++vm.record}else{return ++vm.record1}
									},	
									width : 80
								},
								{
									title : gettextCatalog.getString("<center>Mã hiệu</center>"),
									field : "workItemCode",
									width : 120
								},
								{
									title : gettextCatalog.getString("<center>Tên công việc</center>"),
									field : "workItemName",
									width : 280,
									
									footerTemplate:"<p>Cộng giá trị sau thuế</p>",
								},
								{
									title : gettextCatalog.getString("<center>ĐVT</center>"),
									field : "unit",
									attributes: {
							      		style: "text-align: left;"
									},
									width : 80
								},
								{
									title : gettextCatalog.getString("<center><b style='font-weight: 600;color: #000000;'>Theo hợp đồng</b></center>"),
									columns : 
										[{
											title : gettextCatalog.getString("<center>Khối lượng</center>"),
											field : "workAmount",
											width : 150,
											template: function(dataItem) {
					                            if ($.isNumeric(dataItem.workAmount) && dataItem.workAmount >= 0) {
					                            	dataItem.workAmount = parseFloat(Number(dataItem.workAmount).toFixed(3));
					                                return parseFloat(Number(dataItem.workAmount).toFixed(3));
					                            }
					                            else{dataItem.workAmount = 0;
					                            return 0;}
					                        },
					                        format: "{0:n3}",
					                    decimals: 3,
										validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										},
										{
											title : gettextCatalog.getString("<center>Đơn giá</center>"),
											field : "unitPrice",
											template: function(dataItem) {
					                            if ($.isNumeric(dataItem.unitPrice) && dataItem.unitPrice >= 0) {
					                            	dataItem.unitPrice = parseFloat(Number(dataItem.unitPrice).toFixed(3));
					                                return parseFloat(Number(dataItem.unitPrice).toFixed(3));
					                            }
					                            else{dataItem.unitPrice = 0;
					                            return 0;}
					                        },
					                        format: "{0:n3}",
					                    decimals: 3,
											width : 150
										},
										{
											title : gettextCatalog.getString("<center><b>Thành tiền</b></center>"),
											field : "thanhtien1",
											template: function(dataItem) {
					                            if ($.isNumeric(dataItem.thanhtien1) && dataItem.thanhtien1 >= 0) {
					                            	dataItem.thanhtien1 = parseFloat(Number(dataItem.thanhtien1).toFixed(3));
					                                return parseFloat(Number(dataItem.thanhtien1).toFixed(3));
					                            }
					                            else{dataItem.thanhtien1 = 0;
					                            return 0;}
					                        },
					                        format: "{0:n3}",
					                    decimals: 3,
											width : 150,
											footerTemplate:"<p>#= kendo.toString((sum*10)/100 + sum, 'n0') # VND</p>" 
										}]
								},
								{
									title : gettextCatalog.getString("<center><b style='font-weight: 600;color: #000000;'>Theo quyết toán</b></center>"),
									columns : 
										[{
											title : gettextCatalog.getString("<center>Khối lượng</center>"),
											field : "evaluateQuantity",
											width : 150,
											template: function(dataItem) {
					                            if ($.isNumeric(dataItem.evaluateQuantity) && dataItem.evaluateQuantity >= 0) {
					                            	dataItem.evaluateQuantity = parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
					                                return parseFloat(Number(dataItem.evaluateQuantity).toFixed(3));
					                            }
					                            else{dataItem.evaluateQuantity = 0;
					                            return 0;}
					                        },
					                        validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
					                        format: "{0:n3}",
					                        decimals: 3,
										},
										{
											title : gettextCatalog.getString("<center>Đơn giá</center>"),
											field : "evaluateUnitPrice",
											/*template : function (data) {
												return kendo.toString(data.evaluateUnitPrice, 'n0');
											},*/
											width : 150,
											template: function(dataItem) {
					                            if ($.isNumeric(dataItem.evaluateUnitPrice) && dataItem.evaluateUnitPrice >= 0) {
					                            	dataItem.evaluateUnitPrice = parseFloat(Number(dataItem.evaluateUnitPrice).toFixed(3));
					                                return parseFloat(Number(dataItem.evaluateUnitPrice).toFixed(3));
					                            }
					                            else{dataItem.evaluateUnitPrice = 0;
					                            return 0;}
					                        },
					                        validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
					                        format: "{0:n3}",
					                        decimals: 3,
										},
										{
											title : gettextCatalog.getString("<center><b>Thành tiền</b></center>"),
											width : 150,
											field : "thanhtien2",
											template :function(dataItem){
												var monetized2;
													monetized2= dataItem.evaluateQuantity * dataItem.evaluateUnitPrice;	
													return kendo.toString(monetized2, 'n0');
													  if ($.isNumeric(dataItem.thanhtien2) && dataItem.thanhtien2 >= 0) {
							                            	dataItem.thanhtien2 = parseFloat(Number(dataItem.thanhtien2).toFixed(3));
							                                return parseFloat(Number(dataItem.thanhtien2).toFixed(3));
							                            }
							                            else{dataItem.thanhtien2 = 0;
							                            return 0;}

											},
											footerTemplate:"<p>#= kendo.toString((sum*10)/100 + sum, 'n0') # VND</p>" 
										}]
								},
								{
									title : gettextCatalog.getString("<center><b style='font-weight: 600;color: #000000;'>Chênh lệch</b></center>"),
									template :function(dataItem){
											var monetized1;
											var monetized2;
												monetized2= dataItem.evaluateQuantity * dataItem.evaluateUnitPrice;																				
												monetized1= dataItem.workAmount * dataItem.unitPrice;										
												return kendo.toString(monetized2 - monetized1, 'n0');
									},
									width : 150
								},{
									
									field : "type",
									groupHeaderTemplate: " #if(value == 1){# Nội dung công việc trong hợp đồng #}else{#Nội dung công việc ngoài hợp đồng (phụ lục hợp đồng) #}#",
								} ]
					});			
		}
		//Bảng phân tích chi tiết đơn giá điều chỉnh theo quyết toán
		
	/*	vm.record5 = 0;
        vm.record51 = 0;
        vm.record52 = 0;*/
		function loadDataTable5(data) {			
			vm.gridOptions5 = kendoConfig.getGridOptions({
						autoBind : true,
						dataSource: {
		                    type: "odata",
		                    transport: {
		                        read: data,
		                    },
	
		                   // group: [ { field: "workItemName" }, { field: "type2"} ] ,

			            },	
			            pageable: {
							refresh: true,
							pageSizes: [data.length,10,15,20],
							messages: {
				                display: " {0}-{1} của {2} kết quả",
				                itemsPerPage: "kết quả/trang"

				            }

						},
						
						 //selectable: "multiple",
						
						//change : onChange2,
						noRecords : true,
						/*dataBinding: function () {
							vm.record5 = 0;
					        vm.record51 = 0;
					        vm.record52 = 0;
						},*/
						messages : {
							noRecords : gettextCatalog.getString("Không có kết quả nào")
						},
						columns : [
								/*{
									title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>STT</b>"),
									template: function (data) {
										if(data.type == 1) {return ++vm.record5}else if(data.type == 2){return ++vm.record51}else{return ++vm.record52}
									},		
									width : 70,
									field : "stt",
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:center;"},
								},*/
								{
									title : gettextCatalog.getString("<center>STT</center>"),
									field : "numberic",
									width : 70,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:center;"},
								},
								{
									title : gettextCatalog.getString("<center>Mã hiệu</center>"),
									field : "workItemCode2",
									width : 120,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:left;font-weight: bold;"},
								},
								{
									title : gettextCatalog.getString("<center>Thành phần hao phí</center>"),
									field : "costIngredientName2",
									width : 200,
									headerAttributes: {style:"text-align:center;"},
							    	attributes: { "class": "# if(data.workItemCode2 !== '') { # bold # }  #" }
								},
								{
									title : gettextCatalog.getString("<center>Đơn vị</center>"),
									field : "unit2",
									width : 120,
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:left;"},
								},
								{
									title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Theo hợp đồng</b>"),
									columns : 
										[{
											title : gettextCatalog.getString("<center>Định mức</center>"),
											field : "normIndex22",
											width : 180,
											headerAttributes: {style:"text-align:center;"},
									         attributes:{style:"text-align:right;"},
									         template: function(dataItem) {
						                            if ($.isNumeric(dataItem.normIndex22) && dataItem.normIndex22 >= 0) {
						                            	dataItem.normIndex22 = parseFloat(Number(dataItem.normIndex22).toFixed(3));
						                                return parseFloat(Number(dataItem.normIndex22).toFixed(3));
						                            }
						                            else{dataItem.normIndex22 = 0;
						                            return 0;}
						                        },
						                    format: "{0:n3}",
						                    decimals: 3,
											validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										},
										{
											title : gettextCatalog.getString("<center>Đơn giá</center>"),
											field : "unitPrice22",
											width : 150,
											headerAttributes: {style:"text-align:center;"},
									         attributes:{style:"text-align:right;"},
									         template: function(dataItem) {
						                            if ($.isNumeric(dataItem.unitPrice22) && dataItem.unitPrice22 >= 0) {
						                            	dataItem.unitPrice22 = parseFloat(Number(dataItem.unitPrice22).toFixed(3));
						                                return parseFloat(Number(dataItem.unitPrice22).toFixed(3));
						                            }
						                            else{dataItem.unitPrice22 = 0;
						                            return 0;}
						                        },
						                    format: "{0:n3}",
						                    decimals: 3,
											validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										},
										{
											title : gettextCatalog.getString("<center>Hệ số </center>"),
											field : "coefficient22",
											width : 150,
											headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:right;"},
									        template: function(dataItem) {
					                            if ($.isNumeric(dataItem.coefficient22) && dataItem.coefficient22 >= 0) {
					                            	dataItem.coefficient22 = parseFloat(Number(dataItem.coefficient22).toFixed(3));
					                                return parseFloat(Number(dataItem.coefficient22).toFixed(3));
					                            }
					                            else{dataItem.coefficient22 = 0;
					                            return 0;}
					                        },
					                    format: "{0:n3}",
					                    decimals: 3,
										validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										},
										{
											title : gettextCatalog.getString("<center><b>Thành tiền</b></center>"),
											field : "totalMoney22",
											width : 150,
											headerAttributes: {style:"text-align:center;"},
									         attributes:{style:"text-align:right;"},
									         template: function(dataItem) {
						                            if ($.isNumeric(dataItem.totalMoney22) && dataItem.totalMoney22 >= 0) {
						                            	dataItem.totalMoney22 = parseFloat(Number(dataItem.totalMoney22).toFixed(3));
						                                return parseFloat(Number(dataItem.totalMoney22).toFixed(3));
						                            }
						                            else{dataItem.totalMoney22 = 0;
						                            return 0;}
						                        },
						                    format: "{0:n3}",
						                    decimals: 3,
											validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										}],
										headerAttributes: {style:"text-align:center;"},
								},
								{
									title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Quyết toán</b>"),
									columns : 
										[{
											title : gettextCatalog.getString("<center>Khối lượng quyết toán</center>"),
											field : "normIndex44",
											width : 170,
											headerAttributes: {style:"text-align:center;"},
									         attributes:{style:"text-align:right;"},
									         template: function(dataItem) {
						                            if ($.isNumeric(dataItem.normIndex44) && dataItem.normIndex44 >= 0) {
						                            	dataItem.normIndex44 = parseFloat(Number(dataItem.normIndex44).toFixed(3));
						                                return parseFloat(Number(dataItem.normIndex44).toFixed(3));
						                            }
						                            else{dataItem.normIndex44 = 0;
						                            return 0;}
						                        },
						                    format: "{0:n3}",
						                    decimals: 3,
											validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										},
										{
											title : gettextCatalog.getString("<center>Đơn giá</center>"),
											field : "unitPrice44",
											width : 150,
											headerAttributes: {style:"text-align:center;"},
									         attributes:{style:"text-align:right;"},
									         template: function(dataItem) {
						                            if ($.isNumeric(dataItem.unitPrice44) && dataItem.unitPrice44 >= 0) {
						                            	dataItem.unitPrice44 = parseFloat(Number(dataItem.unitPrice44).toFixed(3));
						                                return parseFloat(Number(dataItem.unitPrice44).toFixed(3));
						                            }
						                            else{dataItem.unitPrice44 = 0;
						                            return 0;}
						                        },
						                    format: "{0:n3}",
						                    decimals: 3,
											validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										},
										{
											title : gettextCatalog.getString("<center>Định mức</center>"),
											field : "coefficient44",
											width : 150,
											headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:right;"},
											template: function(dataItem) {
					                            if ($.isNumeric(dataItem.coefficient44) && dataItem.coefficient44 >= 0) {
					                            	dataItem.coefficient44 = parseFloat(Number(dataItem.coefficient44).toFixed(3));
					                                return parseFloat(Number(dataItem.coefficient44).toFixed(3));
					                            }
					                            else{dataItem.coefficient44 = 0;
					                            return 0;}
					                        },
					                    format: "{0:n3}",
					                    decimals: 3,
										validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										},
										{
											title : gettextCatalog.getString("<center><b>Thành tiền</b></center>"),
											width : 150,
											field : "totalMoney44",
											headerAttributes: {style:"text-align:center;"},
									         attributes:{style:"text-align:right;"},
												template: function(dataItem) {
						                            if ($.isNumeric(dataItem.totalMoney44) && dataItem.totalMoney44 >= 0) {
						                            	dataItem.totalMoney44 = parseFloat(Number(dataItem.totalMoney44).toFixed(3));
						                                return parseFloat(Number(dataItem.totalMoney44).toFixed(3));
						                            }
						                            else{dataItem.totalMoney44 = 0;
						                            return 0;}
						                        },
						                    format: "{0:n3}",
						                    decimals: 3,
											validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
										}],
										headerAttributes: {style:"text-align:center;"},
								},
								{
									title : gettextCatalog.getString("<center><b>Chênh lệch</b></center>"),
									width : 150,
									field : "deviant",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:right;"},
									template: function(dataItem) {
			                            if ($.isNumeric(dataItem.deviant) && dataItem.deviant >= 0) {
			                            	dataItem.deviant = parseFloat(Number(dataItem.deviant).toFixed(3));
			                                return parseFloat(Number(dataItem.deviant).toFixed(3));
			                            }
			                            else{dataItem.deviant = 0;
			                            return 0;}
			                        },
			                    format: "{0:n3}",
			                    decimals: 3,
								validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
								},
								{
									title : gettextCatalog.getString("<center>Ghi chú</center>"),
									width : 120,
									field : "ghichu",
									headerAttributes: {style:"text-align:center;"},
							         attributes:{style:"text-align:left;"},
								}
/*								,
								{
									title : gettextCatalog.getString("<center>workItemName</center>"),
									field : "workItemName",
									hidden:true,
									groupHeaderTemplate: "#= value # ",
									width : 120
								},
								{
									title : gettextCatalog.getString("<center>type2</center>"),
									field : "type2",
									hidden:true,
									groupHeaderTemplate: " #if(value == '1'){#Vật liệu#}else if(value == '2'){#Nhân công#} else{#Máy thi công#}#",									
									width : 120
								}*/
								]
					});			
		}
		//vm.obj={constructId:vm.item.constructId}
		function detailInit(e) {
            vm.detail = $("<div/>").appendTo(e.detailCell).kendoGrid({
                dataSource: {
                    type: "odata",
                    transport: {
                        read: data
                    }
                },
                scrollable: false,
                sortable: true,
                pageable: true,
                columns : [
							{
								title : gettextCatalog.getString("STT"),
								template: dataItem => $("#dataTable5").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								width : 60
							},
							{
								title : gettextCatalog.getString("<center>Thành phần hao phí</center>"),
								field : "costIngredientName",
								width : 200
							},
							{
								title : gettextCatalog.getString("<center><b>Theo hợp đồng</b></center>"),
								columns : 
									[{
										title : gettextCatalog.getString("<center>Đơn vị</center>"),
										field : "unit",
										width : 150
									},{
										title : gettextCatalog.getString("<center>Định mức</center>"),
										field : "workAmount",
										width : 180
									},
									{
										title : gettextCatalog.getString("<center>Đơn giá</center>"),
										field : "unitPrice",
										width : 150
									},
									{
										title : gettextCatalog.getString("<center><b>Thành tiền</b></center>"),
										field : "ttthd",
										template : function(dataItem){
											if(dataItem.workAmount > 0 && dataItem.unitPrice > 0){
												return dataItem.workAmount * dataItem.unitPrice;
											}
											return "";
										},
										width : 150
									}]
							},
							{
								title : gettextCatalog.getString("<center><b>Quyết toán</b></center>"),
								columns : 
									[{
										title : gettextCatalog.getString("<center>Khối lượng quyết toán</center>"),
										field : "evaluateQuantity",
										width : 150
									},
									{
										title : gettextCatalog.getString("<center>Đơn giá</center>"),
										field : "evaluateUnitPrice",
										width : 150
									},
									{
										title : gettextCatalog.getString("<center><b>Thành tiền</b></center>"),
										width : 150,
										template :function(dataItem){
											
												return dataItem.evaluateQuantity * dataItem.evaluateUnitPrice;
											
											return "";
										},
									}]
							},
							{
								title : gettextCatalog.getString("<center><b>Chênh lệch</b></center>"),
								template :function(dataItem){
									var monetized1;
									var monetized2;
									
										monetized2= dataItem.evaluateQuantity * dataItem.evaluateUnitPrice;																				
										monetized1= dataItem.workAmount * dataItem.unitPrice;										
										return monetized2 - monetized1
									
								},
								width : 150
							},
							{
								title : gettextCatalog.getString("<center>Ghi chú</center>"),
								width : 120
							}]
            });
        }
		
		function fetchAllEstimateAB2(){
			estimatesABService.getEstimateAB2(vm.item.constructId).then(function(d) {
				refreshGrid(d.plain(),vm.tableAbForm2);
			}, function(errResponse) {
				console.error('Error while fetching object type');
			});
			estimatesABService.getAbSettIdByConstrId(vm.item.constructId).then(function(d) {
				vm.estimatesAB2 = data.plain();
				if(vm.estimatesAB2.statusCa == 0){
					vm.estimatesAB2.statusCaText = 'Soạn thảo';
					vm.disableBtnApprpval=false;
				}else if(vm.estimatesAB2.statusCa == 1){
					vm.estimatesAB2.statusCaText = 'Trình ký';
					vm.disableBtnSave=true;
					vm.disableBtnApprpval=true;
					vm.disableCbb=true;
					vm.disableAll = true;
				}else if(vm.estimatesAB2.statusCa == 2){
					vm.estimatesAB2.statusCaText = 'Đã ký';
					vm.disableBtnSave=true;
					vm.disableBtnApprpval=true;
					vm.disableCbb=true;
					vm.disableAll = true;
				}else if(vm.estimatesAB2.statusCa == 3){
					vm.estimatesAB2.statusCaText = 'Từ chối ký';
					vm.disableBtnApprpval=true;
				}else{
					vm.estimatesAB2.statusCaText = 'Soạn thảo';
				}
			}, function(errResponse) {
				vm.disableBtnExpDoc=true;
				vm.disableBtnExpPdf=true;
				vm.disableBtnApprpval = true;
				vm.disableCbb=false;
				vm.estimatesAB2.statusCaText = 'Soạn thảo';
				console.error('Error while fetching object type');
			});
		}
		
		function fetchAllEstimateAB5(){
			//toanbd
			estimatesABService.getEstimateAB5(vm.item.constructId).then(function(d) {
				if(d.plain().length==0){
					toastr.warning("Chưa có chiết tính công việc hợp đồng");
				}
				refreshGrid(d.plain(),vm.dataTable5);
				refreshGrid(d.plain(),vm.detail);
			}, function(errResponse) {
				console.error('Error while fetching object type');
			});
		}
		
		function refreshGrid(d , grid) {
        	grid.dataSource.data(d);
        	grid.refresh();
        }
		
		function onChange2(){
			
		}

		/*DONG END*/
    
		//**************************HuyNV***************************************************
		
		vm.estimatesAB1.valueFinalizationContractors = 0;
		vm.estimatesAB1.paidValue = 0;
		vm.estimatesAB1.paidValue = 0;
		vm.estimatesAB1.recoveryMaterialValue = 0;
		function fetchAllEstimateAB1(){
			// I)get gia tri hop dong
			estimatesABService.getContractTotalValue(vm.estimatesAB3.constructId).then(function(d) {
				for (var int = 0; int < d.length; int++) {
					vm.tmpEstimateAB1 = d[int]; 
				}
				if(vm.tmpEstimateAB1.contract_total_value != null && vm.tmpEstimateAB1.contract_total_value != 0){
					vm.estimatesAB1.contractTotalValue = vm.tmpEstimateAB1.contract_total_value;
					vm.estimatesAB1.total = kendo.toString(vm.tmpEstimateAB1.contract_total_value, "n0");
				}else{
					vm.estimatesAB1.contractTotalValue = 0;
					vm.estimatesAB1.total = 0;
				}
				
				//II) get gia tri vat tu xuat kho (1)
				estimatesABService.getTotalPrice(vm.estimatesAB3.constructId).then(function(d) {
					for (var int = 0; int < d.length; int++) {
						vm.tmpEstimateAB1 = d[int]; 
					}
					if(vm.tmpEstimateAB1.totalPrice != null && vm.tmpEstimateAB1.totalPrice != 0){
						vm.estimatesAB1.exportMaterialValue = kendo.toString(vm.tmpEstimateAB1.totalPrice, "n0");
					}else{
						vm.estimatesAB1.exportMaterialValue = 0;
					}
					
					//get gia tri vat tu thi cong(2)
					estimatesABService.getValueMerchandise(vm.estimatesAB3.constructId).then(function(d) {
						for (var int = 0; int < d.length; int++) {
							vm.tmpEstimateAB1 = d[int]; 
						}
						if(vm.tmpEstimateAB1.valueConstrMerchandise != null && vm.tmpEstimateAB1.valueConstrMerchandise != 0){
							vm.estimatesAB1.acceptMaterialValue = kendo.toString(vm.tmpEstimateAB1.valueConstrMerchandise, "n0");
						}else{
							vm.estimatesAB1.acceptMaterialValue = 0;
						}
						
						//get gia tri den bu do mat mat hu hong(3)
						estimatesABService.getValueLost(vm.estimatesAB3.constructId).then(function(d) {
							for (var int = 0; int < d.length; int++) {
								vm.tmpEstimateAB1 = d[int]; 
							}
							if(vm.tmpEstimateAB1.valueLost != null && vm.tmpEstimateAB1.valueLost != ''){
								vm.estimatesAB1.lostMaterialValue = vm.tmpEstimateAB1.valueLost;
							}else{
								vm.estimatesAB1.lostMaterialValue = 0;
							}
							
							//get Gia tri vat tu A cap da thu hoi(4)
							estimatesABService.getValueSupplies(vm.estimatesAB3.constructId).then(function(d) {
								for (var int = 0; int < d.length; int++) {
									vm.tmpEstimateAB1 = d[int]; 
								}
								if(vm.tmpEstimateAB1.valueSupplies != null && vm.tmpEstimateAB1.valueSupplies != ''){
									vm.estimatesAB1.recoveryMaterialValue = vm.tmpEstimateAB1.valueSupplies;
								}else{
									vm.estimatesAB1.recoveryMaterialValue = 0;
								}
							//(5)tinh gia tri vat tu A cap chua thu hoi (1) -(2)- (3) -(4)
							vm.estimatesAB1.unrecoveryMaterialValue = Number(replaceAll(vm.estimatesAB1.exportMaterialValue, ",", "")) - replaceAll(vm.estimatesAB1.acceptMaterialValue, ",", "") 
								- replaceAll(vm.estimatesAB1.lostMaterialValue, ",", "") - replaceAll(vm.estimatesAB1.recoveryMaterialValue, ",", "");
							vm.estimatesAB1.unrecoveryMaterialValueKendo = kendo.toString(vm.estimatesAB1.unrecoveryMaterialValue, "n0");
							//III)1 la cong viec trong hop dong, 2 la ngoai hop dong
							estimatesABService.getEstimateAB1(vm.estimatesAB3.constructId).then(function(d) {
								for (var int = 0; int < d.length; int++) {
									vm.tmpEstimateAB1 = d[int]; 
									if(vm.tmpEstimateAB1.typeEstimates != null && vm.tmpEstimateAB1.typeEstimates != ''){
										if(vm.tmpEstimateAB1.typeEstimates == 1){
											//kl hoan thanh trong hop dong
											vm.estimatesAB1.valueProposed = vm.tmpEstimateAB1.valueProposed;
										}else{
											//khoi luong hoan thanh ngoai hop dong
											vm.estimatesAB1.valueOutProposed = vm.tmpEstimateAB1.valueProposed;
										}
									}else{
										vm.estimatesAB1.valueProposed = 0;
										vm.estimatesAB1.valueOutProposed = 0;
									}
								}
								vm.estimatesAB1.valueProposedKendo = kendo.toString(vm.estimatesAB1.valueProposed, "n0");
								vm.estimatesAB1.valueOutProposedKendo = kendo.toString(vm.estimatesAB1.valueOutProposed, "n0");
								//(III) Gia tri quyet toan nha thau thuc hien = 1 + 2
								vm.estimatesAB1.valueFinalizationContractors = Number(vm.estimatesAB1.valueProposed) + Number(vm.estimatesAB1.valueOutProposed);
								vm.estimatesAB1.valueFinalizationContractorsKendo = kendo.toString(vm.estimatesAB1.valueFinalizationContractors, "n0");
								//(IV)Cong gia tri quyet toan cong trinh II.2 + 3
								vm.estimatesAB1.sumSettlementConstruction =  Number(vm.estimatesAB1.valueFinalizationContractors) + Number(replaceAll(vm.estimatesAB1.acceptMaterialValue, ",", ""));
								vm.estimatesAB1.sumSettlementConstructionKendo = kendo.toString(vm.estimatesAB1.sumSettlementConstruction, "n0");
								//(VII)Gia tri con lai duoc nha thau thanh toan III - V - VI
								if(vm.estimatesAB1.paidValue == null || vm.estimatesAB1.paidValue == undefined){
									vm.estimatesAB1.paidValue = 0;
								}
								if(vm.estimatesAB1.valueDeductionSupplies == null || vm.estimatesAB1.valueDeductionSupplies == undefined){
									vm.estimatesAB1.valueDeductionSupplies = 0;
								}
								vm.estimatesAB1.valueResidual = vm.estimatesAB1.valueFinalizationContractors - vm.estimatesAB1.paidValue - vm.estimatesAB1.valueDeductionSupplies;
								vm.estimatesAB1.valueResidualKendo = kendo.toString(vm.estimatesAB1.valueResidual, "n0");
								vm.estimatesAB1.paidValue = kendo.toString(vm.estimatesAB1.paidValue, "n0");
								vm.estimatesAB1.valueDeductionSupplies = kendo.toString(vm.estimatesAB1.valueDeductionSupplies, "n0");
								vm.tmpValueConstrMerchandiseKendo = kendo.toString(vm.tmpValueConstrMerchandise, "n0");
								vm.tmpValueFinalizationContractorsKendo = kendo.toString(vm.tmpValueFinalizationContractors, "n0");
								//khoi tao data
//								vm.estimatesAB1.adirectorId = vm.listAmonitorConstruction[0].userId;
//								vm.estimatesAB1.bdirectorId = vm.listContractorsConstruction[0].userId;
							}, function(errResponse) {
								console.error('Error while fetching valueProposed');
							});
							
							}, function(errResponse) {
							});
						}, function(errResponse) {
						});
						
					}, function(errResponse) {
					});
				}, function(errResponse) {
				});
			}, function(errResponse) {
				console.error('Error while fetching object type');
			});
			
			
			
		}
		
		//******************************handle file***********************************
		function getFolder() {
			estimatesABService.getCompletionFolder().then(function(data) {
				vm.folder = data.folder;
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
			  .finally(function() {
//			    console.log("finally finished gists");
			  });
		}
		
		getFolder();
		var tmpFileName = '';
		var tmpFilePath = '';
		$scope.uploadFile = function(files) {
		    var formData = new FormData();
			formData.append("file", files[0]);
			Restangular.one(Constant.UPLOAD_RS_SERVICE+ "?folder="+ vm.folder).withHttpConfig(
					{
						transformRequest : angular.identity
					}).customPOST(formData, '', undefined, {
				'Content-Type' : 'multipart/form-data'
			}).then(function(successResponse) {
				console.log(successResponse);
				if(successResponse.length>0){
					console.log("successResponse",successResponse[0]);
					tmpFilePath = successResponse[0];
				}
			});
		    tmpFileName = files[0].name;
		};
		
		vm.theSign = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path:'',
				nameFile:'',
				roleId : [],
				roleName : [],
				contractCode:''
			};
		
		vm.SingCa3 = {
				 code: '',
			     constructId: '',
			     constrCompReMapId: '',
			     stringEmployee: '',
			     isSign: '',
			     path: '',
			     nameFile: ''
			};
		
		vm.SingCa5 = {
				 code: '',
			     constructId: '',
			     constrCompReMapId: '',
			     stringEmployee: '',
			     isSign: '',
			     path: '',
			     nameFile: ''
			};
		
		function sendApprpval() {
			switch (flagFunctionForm){
				case 1:
					//ky CA form 1
					vm.theSign.code = vm.estimatesAB1.code;
					vm.theSign.constructId = vm.estimatesAB3.constructId;
					vm.theSign.stringEmployee = vm.estimatesAB1.bdirectorId + ',' + vm.estimatesAB1.adirectorId;
					vm.theSign.isSign='theSignCA';
					vm.theSign.roleId = [Constant.ROLE_ID["employee_roleID_CDT_DDPN"],Constant.ROLE_ID["employee_roleID_DT_GDNT"]];
					vm.theSign.roleName = ["Giám đốc nhà thầu","Thủ trưởng chủ đầu tư"];
					vm.estimatesAB1.constructName = vm.estimatesAB3.constructName;
					vm.estimatesAB1.stationcode = vm.estimatesAB3.stationcode;
					vm.estimatesAB1.constructAddress = vm.estimatesAB3.constructAddress;
					if(vm.estimatesAB1.statusCa != 0){
						toastr.error("Không thể ký CA");
						return;
					}
					$('#loading').show();
					estimatesABService.exportFilePdfForm1(vm.estimatesAB1.abSettlementValueId).then(function(data) {
						vm.theSign.path = data.fileName;
						vm.theSign.nameFile = 'QT-AB-1.pdf';
						theSignCA.setItem(vm.theSign);
						goTo('THE_SIGN_CA');
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export !"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
					break;
				case 2:
					//ky CA form 2
					{
					estimatesABService.getAbSettIdByConstrId(vm.item.constructId).then(function(d) {
						vm.estimatesAB2 = data.plain();}, function(errResponse) {
						console.error('can not load constrCompReMapId');});
						vm.AB2.constructId=vm.item.constructId;
						vm.AB2.abSettlementWorkId = vm.estimatesAB2.abSettlementWorkId;
						if(vm.estimatesAB2.statusCa == 0){
							$('#loading').show();
							estimatesABService.exportPDFBieu2(vm.AB2.constructId).then(function(data) {
								vm.theSign.nameFile = 'QT-AB-bieu2.pdf';
								vm.theSign.path = data.fileName;
								vm.theSign.code = vm.estimatesAB2.code;
								vm.theSign.constrCompReMapId = vm.estimatesAB2.constrCompReMapId;
								vm.theSign.constructId = vm.item.constructId;
								vm.theSign.stringEmployee = vm.estimatesAB2.bdirectorId + ',' + vm.estimatesAB2.adirectorId;
								vm.theSign.isSign='theSignCA';
								vm.theSign.roleId=[Constant.ROLE_ID["employee_roleID_DT_GDNT"], Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
								vm.theSign.roleName=["Giám đốc nhà thầu", "Thủ trưởng chủ đầu tư"];
								theSignCA.setItem(vm.theSign);
								goTo('THE_SIGN_CA');
//								vm.disableBtnApprpval = true;
							}).catch( function(){
								toastr.error(gettextCatalog.getString("Lỗi khi export !"));
								return;
							}).finally(function(){
								$('#loading').hide();
							});
						} else {
							toastr.error("Không thể ký CA");
						}
						break;
					}
			
				case 3:
					//ky CA form 3
					if(vm.estimatesAB3.statusCa == 0){
						estimatesABService.getAmonitorsignCA3(vm.estimatesAB3.constructId).then(function (data) {
							vm.SingCa3.stringEmployee = data.bdirectorId + ',' + data.cdesignDirectionId + ',' + data.adirectorId;	
							vm.SingCa3.constrCompReMapId = data.constrCompReMapId;
							vm.SingCa3.constructId = data.constructId;
							vm.SingCa3.code = data.code;
							vm.SingCa3.roleId=[Constant.ROLE_ID["employee_roleID_DT_GDNT"],Constant.ROLE_ID["employee_roleID_TVGS_DDTVGS"], Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
							vm.SingCa3.roleName=["Giám đốc nhà thầu", "Giám đốc tư vấn giám sát", "Thủ trưởng chủ đầu tư"];
							$('#loading').show();
							estimatesABService.exportPDFBieu3(vm.estimatesAB3.constructId).then(function(data) {
									vm.SingCa3.path= data.fileName;
									vm.SingCa3.nameFile='QT-AB-3.pdf';
									
									theSignCA.setItem(vm.SingCa3);
									goTo('THE_SIGN_CA');
									
								}).catch( function(){
									toastr.error(gettextCatalog.getString("Chưa thể ký CA!"));
									return;
								}).finally(function(){
									$('#loading').hide();
								});					
							
							},function(errResponse){
								 toastr.error("Thông tin chưa lưu không thể ký CA");
							});
					}else{
						toastr.error("Trạng thái trình ký, đã ký không thể trình ký!");
					}
					
								
					break;
				case 4:
					//ky CA form 4
					{
					var dataItemOne = angular.copy(vm.bean);
					   console.log(JSON.stringify(dataItemOne));
					   $('#loading').show();
					   vm.item.constructId = vm.item.constructId;
					   vm.item.constructionId = vm.item.constructId;
					   vm.item.abComplementWorkDescribeId = vm.bean.abComplementWorkDescribeId;

					   if(dataItemOne.statusCa ===0){
						estimatesABService.signCA(vm.item).then(function (data) {
					    console.log(JSON.stringify(data));
					    var signCAData = {
					     code: '',
					     constructId: '',
					     constrCompReMapId: '',
					     stringEmployee: '',
					     isSign: '',
					     path: '',
					     nameFile: '',
					     roleId : [],
					     roleName : []
					    };
					    signCAData.code = dataItemOne.code;
					    signCAData.constructId = vm.item.constructId;
					    signCAData.constrCompReMapId = data[0];
					    
						estimatesABService.getForm4ByConstrId(vm.item.constructId).then(function(dataId){
						    signCAData.stringEmployee = dataId.binChargeConstructId + "," + dataId.ainChargeMonitorId + ","
						     + dataId.bdirectorId + "," + dataId.adirectorId;
						    signCAData.isSign = "theSignCA";
						    signCAData.roleId = [Constant.ROLE_ID["employee_roleID_DT_PTTC"],Constant.ROLE_ID["employee_roleID_CDT_PTGST"],Constant.ROLE_ID["employee_roleID_DT_GDNT"],Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
						    signCAData.roleName = ["Phụ trách thi công nhà thầu","PT Giám sát chủ đầu tư","Giám đốc nhà thầu","Thủ trưởng chủ đầu tư"];
						    signCAData.path = data[2]; // nên là đường dẫn tương đối . nhưng ở đây đã điền là tên file
						    signCAData.nameFile = 'QT-AB-4.pdf';
						    console.log(JSON.stringify(signCAData));
						    theSignCA.setItem(signCAData);
						    goTo('THE_SIGN_CA');
//						    vm.disableBtnApprpval = true;
						}).catch( function(){
							toastr.error(gettextCatalog.getString("Không thể ký CA !"));
							return;
						}).finally(function(){
							$('#loading').hide();
						});

					   });
					   }else{
					    toastr.error("Không thể ký CA");
					   }
					break;
					}
					  
				case 5:
					//ky CA form 5
					if(vm.estimatesAB5.statusCa == 0){
						estimatesABService.getAmonitorsignCA5(vm.estimatesAB5.constructId).then(function (data) {
							vm.SingCa5.stringEmployee = data.bdirectorId + ',' + data.adirectorId;	
							vm.SingCa5.constrCompReMapId = data.constrCompReMapId;
							vm.SingCa5.constructId = data.constructId;
							vm.SingCa5.code = data.code;
							vm.SingCa5.roleId=[Constant.ROLE_ID["employee_roleID_DT_GDNT"], Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
							vm.SingCa5.roleName=["Giám đốc nhà thầu", "Thủ trưởng chủ đầu tư"];
							$('#loading').show();
							estimatesABService.exportPDFBieu5(vm.estimatesAB5).then(function(data) {
									vm.SingCa5.path= data.fileName;
									vm.SingCa5.nameFile='QT-AB-5.pdf';
									
									theSignCA.setItem(vm.SingCa5);
									goTo('THE_SIGN_CA');
									
								}).catch( function(){
									toastr.error(gettextCatalog.getString("Chưa thể ký CA!"));
									return;
								}).finally(function(){
									$('#loading').hide();
								});				
							
							},function(error){
								 toastr.error("Thông tin chưa lưu không thể ký CA");
							});
					}else{
						toastr.error("Trạng thái trình ký, đã ký không thể trình ký!");
					}
					break;
				case 6:
					//ky CA form 6
					vm.theSign.code = vm.estimatesAB6.code;
					vm.theSign.constructId = vm.estimatesAB3.constructId;
					vm.theSign.stringEmployee =  vm.estimatesAB6.bheadConstructId + "," + vm.estimatesAB6.bheadAccountId  
																			 + "," + vm.estimatesAB6.aheadConstructId
																			 + "," + vm.estimatesAB6.aheadTechnicalId
																			 + "," + vm.estimatesAB6.aheadFinanceId
																			 + "," + vm.estimatesAB6.bdirectorId
																			 + "," + vm.estimatesAB6.adirectorId;
					vm.theSign.isSign='theSignCA';
					vm.theSign.roleId = ["0","0","0","0","0",Constant.ROLE_ID["employee_roleID_CDT_DDPN"],Constant.ROLE_ID["employee_roleID_DT_GDNT"]];
					vm.theSign.roleName = ["Đội thi công nhà thầu","Kế toán nhà thầu","Phòng Hạ tầng chủ đầu tư",
					                       "Phòng tổng hợp CĐT","Phòng Tài chính CĐT","Giám đốc nhà thầu","Giám đốc chủ đầu tư"];
					vm.theSign.nameFile = vm.estimatesAB6.documentName;
					vm.theSign.path = vm.estimatesAB6.documentPath;
					theSignCA.setItem(vm.theSign);
					goTo('THE_SIGN_CA');
					break;
				default:
					break;
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
			}
		}
		
		vm.settlementValue = {
				abSettlementValueId:'',
				code:'',
				createdDate:'',
				createdUserId:'',
				adirectorId:'',
				bdirectorId:'',
				statusCa:'',
				documentCaId:'',
				isActive:'',
				exportMaterialValue:'',
				acceptMaterialValue:'',
				lostMaterialValue:'',
				recoveryMaterialValue:'',
				unrecoveryMaterialValue:'',
				paidValue:'',
				constructId:''};
		
		vm.abMaterialCompare = {constructId:'', documentCaId:'',adirectorId:'', bdirectorId:'' };
		
		function saveForm(){
			/*statusCaSettlementEvaluate = 2;*/
			if(statusCaSettlementEvaluate == 2){
				switch (flagFunctionForm){
			case 1:
				// bắt validate 
				var cbb1bieu1=$("#cbbF1").data("kendoDropDownList").text();
				var cbb2bieu1=$("#cbbF2").data("kendoDropDownList").text();
				var check=0;
				if(cbb1bieu1 == ''){
					toastr.warning("không được để trống thủ trưởng đầu tư");
					check++;
					return;
				}
				if(cbb2bieu1 == '' && check == 0 ){
					toastr.warning("không được để trống giám đốc nhà thầu");
					return;
				}
				//ghi lai form 1 table = AB_SETTLEMENT_VALUE
				
				/**
				 * QuangNV
				 * Clear format Currency
				 */
			
				clearFormatAB1();
				validateDataForm1(vm.estimatesAB1);
				if(vm.isCheckLengData == "true"){
					if(vm.estimatesAB1.statusCa == null){
						vm.estimatesAB1.createdUserId = estimatesUserId;
						vm.estimatesAB1.catEmployeeId = estimatesCatEmployeeId;
						vm.estimatesAB1.statusCa = '0';
						vm.estimatesAB1.isActive = '1';
						vm.estimatesAB1.adirectorId=document.getElementById("cbbF1").value;
						vm.estimatesAB1.bdirectorId=document.getElementById("cbbF2").value;
						vm.estimatesAB1.constructId = vm.estimatesAB3.constructId;				
						vm.estimatesAB1.constrCompleteRecordsMap = {dataTableName :'', dataTableId :'',dataTableIdValue:'',catFileInvoiceId:'' }
						vm.estimatesAB1.constrCompleteRecordsMap.dataTableName ='AB_SETTLEMENT_VALUE';
						vm.estimatesAB1.constrCompleteRecordsMap.dataTableId ='AB_SETTLEMENT_VALUE_ID';
					estimatesABService.createSettlementValue(vm.estimatesAB1).then(
					function(result) {
						vm.theSign.constrCompReMapId = result;
						toastr.success("Thêm mới thành công!");
						vm.disableBtnExpDoc=false;
						vm.disableBtnExpPdf=false;
						showButton();
						checkDataForm1();
					},
					function(errResponse) {
						toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo form 1 !"));
						return;
					});
					}else{
						if(vm.checkdata1.createdUserId != Constant.user.srvUser.userId){
							toastr.warning("Phải là người tạo mới có quyền sửa");
							return;
						}
//						vm.estimatesAB1.createdUserId = estimatesUserId;
//						vm.estimatesAB1.catEmployeeId = estimatesCatEmployeeId;
						vm.estimatesAB1.statusCa = '0';
						vm.estimatesAB1.isActive = '1';
						vm.estimatesAB1.adirectorId=document.getElementById("cbbF1").value;
						vm.estimatesAB1.bdirectorId=document.getElementById("cbbF2").value;
						vm.estimatesAB1.constructId = vm.estimatesAB3.constructId;				
//						vm.estimatesAB1.constrCompleteRecordsMap = {dataTableName :'', dataTableId :'',dataTableIdValue:'',catFileInvoiceId:'' }
//						vm.estimatesAB1.constrCompleteRecordsMap.dataTableName ='AB_SETTLEMENT_VALUE';
//						vm.estimatesAB1.constrCompleteRecordsMap.dataTableId ='AB_SETTLEMENT_VALUE_ID';
						estimatesABService.createSettlementValue(vm.estimatesAB1).then(
						function(result) {
							vm.theSign.constrCompReMapId = result;
							toastr.success("Cập nhật thành công!");
							vm.disableBtnExpDoc=false;
							vm.disableBtnExpPdf=false;
							showButton();
							checkDataForm1();
						},
						function(errResponse) {
							toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo form 1 !"));
							return;
						});
						
					}
				}
				vm.disableBtnApprpval = false;
				break;
			case 2:
				{
				//ghi lai form 2
				// bắt validate 
				var cbb1bieu2=$("#cbb1").data("kendoDropDownList").text();
				var cbb2bieu2=$("#cbb3").data("kendoDropDownList").text();
				if(cbb1bieu2 == ''){
					toastr.warning("không được để trống thủ trưởng đầu tư");
					return;
				}
				else if(cbb2bieu2 == '' ){
					toastr.warning("không được để trống giám đốc nhà thầu");
					return;
				}
				
				estimatesABService.getAbSettIdByConstrId(vm.item.constructId).then(function(d) {
					vm.estimatesAB2 = d.plain();
					var cbb1s = document.getElementById("cbb1");
					vm.estimatesAB2.adirectorId = cbb1s.options[cbb1s.selectedIndex].value;
					var cbb3s = document.getElementById("cbb3");
					vm.estimatesAB2.bdirectorId = cbb3s.options[cbb3s.selectedIndex].value;
					if(vm.estimatesAB2.statusCa == 0){
						vm.estimatesAB2.statusCaText = 'Soạn thảo';
						vm.disableBtnApprpval=false;
					}else if(vm.estimatesAB2.statusCa == 1){
						vm.estimatesAB2.statusCaText = 'Trình ký';
						vm.disableBtnApprpval=true;
						vm.disableBtnSave=true;
					}else if(vm.estimatesAB2.statusCa == 2){
						vm.estimatesAB2.statusCaText = 'Đã ký';
						vm.disableBtnApprpval=true;
						vm.disableBtnSave=true;
					}else if(vm.estimatesAB2.statusCa == 3){
						vm.estimatesAB2.statusCaText = 'Từ chối ký';
						vm.disableBtnApprpval=true;
					}else{
						vm.estimatesAB2.statusCaText = 'Không xác định';
					}
					// check quyền sửa
					if(vm.estimatesAB2.createdUserId != Constant.user.srvUser.userId){
						toastr.warning("Phải là người tạo mới có quyền sửa");
						return;
					}
					// update du lieu
					estimatesABService.updatethongtinAB2(vm.estimatesAB2).then(function(result){
						toastr.success("Cập nhật thành công!");
					},function(error){
						toastr.error("Cập nhật thất bại!");
					});
				}, function(errResponse) {
					var cbb1s = document.getElementById("cbb1");
					vm.estimatesAB2.adirectorId = cbb1s.options[cbb1s.selectedIndex].value;
					var cbb3s = document.getElementById("cbb3");
					vm.estimatesAB2.bdirectorId = cbb3s.options[cbb3s.selectedIndex].value;
					vm.estimatesAB2.statusCa = 0;
					vm.estimatesAB2.isActive = 1;
					vm.estimatesAB2.constructId = vm.item.constructId;
					vm.estimatesAB2.createdDate = new Date();
					vm.estimatesAB2.createdUserId = Constant.user.srvUser.userId;
					// them moi du lieu
					estimatesABService.addthongtinAB2(vm.estimatesAB2).then(function(result){
						vm.estimatesAB2.abSettlementWorkId = result;
						toastr.success("Thêm mới thành công!");
						vm.disableBtnExpDoc=false;
						vm.disableBtnExpPdf=false;
						vm.disableBtnApprpval = false;
					},function(error){
						toastr.error("Thêm mới thất bại!");
					});
				});
				vm.disableBtnApprpval = false;
				break;
				}
			case 3:
				
				// bắt validate 
				var cbb1bieu3=$("#amonitorId3").data("kendoDropDownList").text();
				var cbb2bieu3=$("#ConstructorId3").data("kendoDropDownList").text();
				var cbb3bieu3=$("#binChargeConstructId3").data("kendoDropDownList").text();
				
				if(cbb1bieu3 == ''){
					toastr.warning("không được để trống thủ trưởng đầu tư");
					return;
				}
				else if(cbb2bieu3 == ''){
					toastr.warning("không được để trống giám đốc nhà thầu");
					return;
				}
				else if(cbb3bieu3 ==""){
					toastr.warning("không được để trống giám đốc tư vấn giám sát");
					return;
				}else{
					if(vm.check3){
						// sửa
						if(Constant.user.srvUser.userId == vm.check3.createdUserId){
							vm.estimatesAB3.createdUserId = vm.check3.createdUserId
							if(vm.estimatesAB3.statusCa == 0){
								estimatesABService.addEstimateAB3(vm.estimatesAB3).then(function(result){
				        			toastr.success("Cập nhật thành công!");   
				        			vm.disableBtnApprpval = false;
				        			vm.disableBtnExpDoc = false;
				    				vm.disableBtnExpPdf = false;
				        			checkSave3();
				        		}, function(errResponse){        			
					                console.error('Error while creating TariffContractorType');
						        });
								
							}else{
								toastr.error('Trạng thái trình ký, Đã ký không được sửa!');
							}
						}else{
							toastr.warning("Người tạo mới có quyền sửa!");
						}
						
					}else{
						// thêm mới
						vm.estimatesAB3.createdUserId = Constant.user.srvUser.userId;
						if(vm.estimatesAB3.statusCa == 0){
							estimatesABService.addEstimateAB3(vm.estimatesAB3).then(function(result){
			        			toastr.success("Lưu thành công!");   
			        			vm.disableBtnApprpval = false;
			        			vm.disableBtnExpDoc = false;
			    				vm.disableBtnExpPdf = false;
			        			checkSave3();
			        		}, function(errResponse){        			
				                console.error('Error while creating TariffContractorType');
					        });
							
						}else{
							toastr.error('Trạng thái trình ký, Đã ký không được sửa!');
						}
					}
					
				}
				vm.disableBtnApprpval = false;
				break;
			case 4:
				// bắt validate 
				var cbb1bieu4=$("#cbb1f4").data("kendoDropDownList").text();
				var cbb2bieu4=$("#cbb2f4").data("kendoDropDownList").text();
				var cbb3bieu4=$("#cbb3f4").data("kendoDropDownList").text();
				var cbb4bieu4=$("#cbb4f4").data("kendoDropDownList").text();
				if(cbb1bieu4 == ''){
					toastr.warning("không được để trống thủ trưởng đầu tư");
					return;
				}
				else if(cbb2bieu4 == ''){
					toastr.warning("không được để trống giám đốc nhà thầu");
					return;
				}
				else if(cbb3bieu4 ==""){
					toastr.warning("không được để trống PT giám sát chủ đầu tư");
					return;
				}else if(cbb4bieu4 ==""){
					toastr.warning("không được để trống Phụ trách thi công nhà thầu");
					return;
				}else{}
				if (vm.bean.statusCa !=1 ){
				var obj  = {
						 abComplementWorkDescribeId:"",
						 code:"",
						 /*createdUserId:"",*/
						 aDirectorId:"",
						 bDirectorId:"",
						 aInChargeMonitorId:"",
						 bInChargeConstructId:"",
						 statusCa:"",
						 documentCaId:"",
						 isActive:"",
						 constructId:"",
						 createdUserId:"",
						 createdDate:"",
				}
				var isCreateUser = Constant.user.srvUser.userId;
				obj.createdUserId = isCreateUser;		
				obj.createdDate = new Date();
				obj.adirectorId = vm.bean.thutruongdonvi;
				obj.bdirectorId = vm.bean.giamdocnhathau;
				obj.ainChargeMonitorId = vm.bean.giamsatcdt;
				obj.binChargeConstructId = vm.bean.thicongnhathau;
				obj.statusCa = 0;
				obj.isActive =1;
				obj.constructId  =  vm.item.constructId;	
				
				if(vm.isHaveDataForm4){	
					if(vm.checkData4.createdUserId != Constant.user.srvUser.userId){
						toastr.warning("Phải là người tạo mới có quyền sửa");
						return;
					}
					// update du lieu
					obj.abComplementWorkDescribeId =vm.bean.abComplementWorkDescribeId;
					obj.code = vm.bean.code;
					estimatesABService.udpateForm4(obj).then(function(data){
						toastr.success("Cập nhật thành công!");
						refreshGridForm4();		
						fetchAllEstimateAB4();
					},function(error){
						toastr.error("Cập nhật thất bại!");
					});
				}else{
					// them moi du lieu
					estimatesABService.recordForm4(obj).then(function(data){
						toastr.success("Thêm mới thành công!");
						vm.disableBtnExpDoc=false;
						vm.disableBtnExpPdf=false;
						refreshGridForm4();			
						fetchAllEstimateAB4();
					},function(error){
						toastr.error("Thêm mới thất bại!");
					});
				}
				vm.disableBtnApprpval = false;
			
				break;
				}
				else{
					vm.disableBtnApprpval = true;
					vm.disableBtnSave = true;
				};
				//ghi lai form 4
				
			case 5:
				if(vm.check5 == undefined){
					toastr.warning("Không có chiết tính công việc hợp đồng, không thể lưu");
					return;
				}
				// check quyền sửa
				if(vm.check5.createdUserId != Constant.user.srvUser.userId){
					toastr.warning("Phải là người tạo mới có quyền sửa");
					return;
				}
				var cbb1bieu5=$("#cbb1f5").data("kendoDropDownList").text();
				var cbb2bieu5=$("#cbb2f5").data("kendoDropDownList").text();
				if(cbb1bieu5 == ''){
					toastr.warning("không được để trống thủ trưởng đầu tư");
					return;
				}
				else if(cbb2bieu5 == ''){
					toastr.warning("không được để trống giám đốc nhà thầu");
					return;
				}else{}
				vm.estimatesAB5.createdUserId = Constant.user.srvUser.userId;
				if(vm.estimatesAB5.statusCa == 0){
					vm.estimatesAB5.isActive = 1;
					estimatesABService.addEstimateAB5(vm.estimatesAB5).then(function(result){
	        			toastr.success("Lưu thành công!"); 
	        			vm.disableBtnApprpval = false;
	        			vm.disableBtnExpDoc = false;
	    				vm.disableBtnExpPdf = false;
	        			vm.disableForm = false;
	        			checkSave5();
	        		}, function(errResponse){        			
		                console.error('Error while creating TariffContractorType');
		              
			            }
			        );
				}else{
					toastr.error('Trạng thái trình ký, Đã ký không được sửa!');
				}
				vm.disableForm = false;				
				break;
			case 6:
				//ghi lai form 6
				// check quyền sửa
				if(vm.checkdata6.createdUserId != undefined && vm.checkdata6.createdUserId != Constant.user.srvUser.userId){
					toastr.warning("Phải là người tạo mới có quyền sửa");
					return;
				}
				var cbb1bieu6=$("#amonitorId").data("kendoDropDownList").text();
				var cbb2bieu6=$("#ConstructorId").data("kendoDropDownList").text();
//				var cbb3bieu6=$("#AHEADCONSTRUCTID").data("kendoDropDownList").text();
//				var cbb4bieu6=$("#AHEADFINANCEID").data("kendoDropDownList").text();
//				var cbb5bieu6=$("#BHEADACCOUNTID").data("kendoDropDownList").text();
//				var cbb6bieu6=$("#AHEADTECHNICALID").data("kendoDropDownList").text();
//				var cbb7bieu6=$("#BHEADCONSTRUCTID").data("kendoDropDownList").text();
				if(cbb1bieu6 == ''){
					toastr.warning("không được để trống thủ trưởng đầu tư");
					return;
				}
				else if(cbb2bieu6 == ''){
					toastr.warning("không được để trống giám đốc nhà thầu");
					return;
				}else{}
				if($("#file").val() =="" && vm.checkdata6.documentPath == null){
					toastr.warning("Chưa chọn file ký CA !");
					return;
				}
				if('pdf' != $($('input[name="file"]')[0]).val().split('\\').pop().split('.').pop()){
					toastr.warning("Hãy chọn file pdf !");
					return;
				}
				vm.estimatesAB6.createdUserId = estimatesUserId;
				vm.estimatesAB6.catEmployeeId = estimatesCatEmployeeId;
				vm.estimatesAB6.constructId = vm.estimatesAB3.constructId;
				vm.estimatesAB6.utilAttachedDocuments = {};
				vm.estimatesAB6.constrCompleteRecordsMap = {}
				vm.estimatesAB6.utilAttachedDocuments.documentName = tmpFileName;
				vm.estimatesAB6.utilAttachedDocuments.documentPath = tmpFilePath;
				vm.estimatesAB6.constrCompleteRecordsMap.dataTableName ='AB_MATERIAL_COMPARE';
				vm.estimatesAB6.constrCompleteRecordsMap.dataTableId ='AB_MATERIAL_COMPARE_ID';
    			estimatesABService.createMaterial(vm.estimatesAB6).then(
						function(result) {
							vm.theSign.constrCompReMapId = result.documentCaId;
							vm.theSign.path=result.path + tmpFileName;
							toastr.success("Lưu thành công!");
							checkDataForm6();
						},
						function(errResponse) {
							toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo form 6 !"));
							return;
				});
    			vm.disableBtnApprpval = false;
				break;
			default:
				break;
			}
			refreshGridForm4();
//			*********************************
		  }else{// khong duoc save khi chua tham dinh
			  toastr.error(gettextCatalog.getString("Chưa thẩm định chưa được lưu !"));
		  }
				
		}
		
		vm.isCheckDataForm6 = '';
		vm.isCheckDataForm1 = '';
		function checkDataForm1(){
			estimatesABService.checkDataFrom1(vm.estimatesAB3.constructId).then(
			   function (data) {
				   if(data == null){
						fetchAllEstimateAB1();
						visibleButton();
				   }else{
					   vm.checkdata1 = data;
					   vm.isCheckDataForm1 = 'OK';
					   showButton();
					   vm.estimatesAB1.adirectorId = data.adirectorId;
					   vm.estimatesAB1.bdirectorId = data.bdirectorId;
					   vm.theSign.stringEmployee = vm.estimatesAB1.bdirectorId + ',' + vm.estimatesAB1.adirectorId;
					   vm.estimatesAB1.statusCa = data.statusCa;
					   vm.estimatesAB1.code = data.code;
					   vm.estimatesAB1.exportMaterialValue = kendo.toString(data.exportMaterialValue, "n0");
					   vm.estimatesAB1.acceptMaterialValue = kendo.toString(data.acceptMaterialValue, "n0");
					   vm.estimatesAB1.lostMaterialValue = kendo.toString(data.lostMaterialValue, "n0");
					   vm.estimatesAB1.recoveryMaterialValue = kendo.toString(data.recoveryMaterialValue, "n0");
					   vm.estimatesAB1.unrecoveryMaterialValue = data.exportMaterialValue - data.acceptMaterialValue - data.lostMaterialValue - data.recoveryMaterialValue;
					   vm.estimatesAB1.unrecoveryMaterialValueKendo = kendo.toString(vm.estimatesAB1.unrecoveryMaterialValue, "n0");
					   vm.estimatesAB1.paidValue = kendo.toString(data.paidValue);
					   vm.estimatesAB1.valueDeductionSupplies = kendo.toString(vm.estimatesAB1.valueDeductionSupplies, "n0");
					   vm.estimatesAB1.abSettlementValueId = kendo.toString(data.abSettlementValueId);
					  
					   
					   estimatesABService.getContractTotalValue(vm.estimatesAB3.constructId).then(function(d) {
							for (var int = 0; int < d.length; int++) {
								vm.tmpEstimateAB1 = d[int]; 
							}
							if(vm.tmpEstimateAB1.contract_total_value != null && vm.tmpEstimateAB1.contract_total_value != 0){
								vm.estimatesAB1.contractTotalValue = vm.tmpEstimateAB1.contract_total_value;
								vm.estimatesAB1.total = kendo.toString(vm.tmpEstimateAB1.contract_total_value, "n0");
							}else{
								vm.estimatesAB1.contractTotalValue = 0;
								vm.estimatesAB1.total = 0;
							}
					   }, function(errResponse) {
					   });
						estimatesABService.getEstimateAB1(vm.estimatesAB3.constructId).then(function(d) {
							if(d.length==2){
								for (var int = 0; int < d.length; int++) {
									vm.tmpEstimateAB1 = d[int]; 
									if(vm.tmpEstimateAB1.typeEstimates != null && vm.tmpEstimateAB1.typeEstimates != ''){
										if(vm.tmpEstimateAB1.typeEstimates == 1){
											//kl hoan thanh trong hop dong
											vm.estimatesAB1.valueProposed = vm.tmpEstimateAB1.valueProposed;
										}else if(vm.tmpEstimateAB1.typeEstimates == 2){
											//khoi luong hoan thanh ngoai hop dong
											vm.estimatesAB1.valueOutProposed = vm.tmpEstimateAB1.valueProposed;
										}
									}else{
										vm.estimatesAB1.valueProposed = 0;
										vm.estimatesAB1.valueOutProposed = 0;
									}
								}
							}else if(d.length==1){
								vm.tmpEstimateAB1 = d[0];
								if(vm.tmpEstimateAB1.typeEstimates != null && vm.tmpEstimateAB1.typeEstimates != ''){
									if(vm.tmpEstimateAB1.typeEstimates == 1){
										//kl hoan thanh trong hop dong
										vm.estimatesAB1.valueProposed = vm.tmpEstimateAB1.valueProposed;
										vm.estimatesAB1.valueOutProposed = 0;
									}else if(vm.tmpEstimateAB1.typeEstimates == 2){
										//khoi luong hoan thanh ngoai hop dong
										vm.estimatesAB1.valueOutProposed = vm.tmpEstimateAB1.valueProposed;
										vm.estimatesAB1.valueProposed = 0;
									}
								}else{
									vm.estimatesAB1.valueProposed = 0;
									vm.estimatesAB1.valueOutProposed = 0;
								}
							} else{
								vm.estimatesAB1.valueProposed = 0;
								vm.estimatesAB1.valueOutProposed = 0;
							}
							
							vm.estimatesAB1.valueProposedKendo = kendo.toString(vm.estimatesAB1.valueProposed, "n0");
							vm.estimatesAB1.valueOutProposedKendo = kendo.toString(vm.estimatesAB1.valueOutProposed, "n0");
							//(III) Gia tri quyet toan nha thau thuc hien = 1 + 2
							vm.estimatesAB1.valueFinalizationContractors = Number(vm.estimatesAB1.valueProposed) + Number(vm.estimatesAB1.valueOutProposed);
							vm.estimatesAB1.valueFinalizationContractorsKendo = kendo.toString(vm.estimatesAB1.valueFinalizationContractors, "n0");
							 //(IV)Cong gia tri quyet toan cong trinh II.2 + 3
						   //QuangNV add ?
							vm.estimatesAB1.sumSettlementConstruction =  Number(vm.estimatesAB1.valueFinalizationContractors) + Number(replaceAll(vm.estimatesAB1.acceptMaterialValue, ",", ""));
							vm.estimatesAB1.sumSettlementConstructionKendo = kendo.toString(vm.estimatesAB1.sumSettlementConstruction, "n0");
							//(VII)Gia tri con lai duoc nha thau thanh toan III - V - VI
							if(vm.estimatesAB1.paidValue == null || vm.estimatesAB1.paidValue == undefined){
								vm.estimatesAB1.paidValue = 0;
							}
							if(vm.estimatesAB1.valueDeductionSupplies == null || vm.estimatesAB1.valueDeductionSupplies == undefined){
								vm.estimatesAB1.valueDeductionSupplies = 0;
							}
							vm.tmpValueConstrMerchandiseKendo = kendo.toString(vm.tmpValueConstrMerchandise, "n0");
							vm.tmpValueFinalizationContractorsKendo = kendo.toString(vm.tmpValueFinalizationContractors, "n0");
							vm.estimatesAB1.valueResidual = vm.estimatesAB1.valueFinalizationContractors - vm.estimatesAB1.paidValue - vm.estimatesAB1.valueDeductionSupplies;
							vm.estimatesAB1.valueResidualKendo = kendo.toString(vm.estimatesAB1.valueResidual, "n0");
							vm.estimatesAB1.paidValue = kendo.toString(data.paidValue, "n0");
						}, function(errResponse) {
							console.error('Error while fetching valueProposed');
						});
						estimatesABService.getconstrCompReMap(vm.estimatesAB3.constructId).then(function(result) {
							vm.theSign.constrCompReMapId = result;
						}, function(errResponse) {
							console.error('Error while fetching valueProposed');
						});
						if(flagFunctionForm == 1 && vm.estimatesAB1.statusCa == 1){
							vm.disableBtnApprpval = true;
							vm.disableBtnSave = true;
						}else{
							vm.disableBtnApprpval = false;
							vm.disableBtnSave = false;
						}
				   }
				   reloadStatusCA(vm.estimatesAB1.statusCa , 1);
				},
				function(errResponse) {
					fetchAllEstimateAB1();
					reloadStatusCA(vm.estimatesAB1.statusCa , 1);
					visibleButton();
				});
		}
		
		function checkDataForm6(){
			estimatesABService.checkDataFrom6(vm.estimatesAB3.constructId).then(
			   function (data) {
				   vm.checkdata6 = data;
				   console.log("inhhhhhhhh:",data.plain());
				   vm.isCheckDataForm6 = 'OK';
				   vm.estimatesAB6.adirectorId = data.adirectorId;
				   vm.estimatesAB6.bdirectorId = data.bdirectorId;
				   vm.estimatesAB6.aheadConstructId = data.aheadConstructId;
				   vm.estimatesAB6.aheadTechnicalId = data.aheadTechnicalId;
				   vm.estimatesAB6.aheadFinanceId = data.aheadFinanceId;
				   vm.estimatesAB6.bheadConstructId = data.bheadConstructId;
				   vm.estimatesAB6.bheadAccountId = data.bheadAccountId;
				   vm.estimatesAB6.code = data.code;
				   vm.theSign.stringEmployee = vm.estimatesAB6.bdirectorId + ',' + vm.estimatesAB6.adirectorId + ',' + vm.estimatesAB6.aheadConstructId + ',' + vm.estimatesAB6.aheadTechnicalId + ',' + vm.estimatesAB6.aheadFinanceId + ',' + vm.estimatesAB6.bheadConstructId + ',' + vm.estimatesAB6.bheadAccountId;
				   vm.estimatesAB6.statusCa = data.statusCa;
				   vm.estimatesAB6.documentCaId = data.documentCaId;
				   vm.estimatesAB6.documentName = data.documentName;
				   vm.estimatesAB6.documentPath = data.documentPath;
				   if(flagFunctionForm == 6 && vm.estimatesAB6.statusCa == 1){
					   vm.disableBtnApprpval = true;
					   vm.disableBtnSave = true;
				   }else{
					   vm.disableBtnApprpval = false;
					   vm.disableBtnSave = false;
				   }
				   reloadStatusCA(vm.estimatesAB6.statusCa , 6);
				},
				function(errResponse) {
					vm.estimatesAB6.adirectorId      = vm.listAmonitorConstruction[0].userId;
					vm.estimatesAB6.bdirectorId      = vm.listContractorsConstruction[0].userId;
					vm.estimatesAB6.aheadConstructId = vm.listThanhPhanKy[0].userId;
					vm.estimatesAB6.aheadTechnicalId = vm.listThanhPhanKy[0].userId;
					vm.estimatesAB6.aheadFinanceId   = vm.listThanhPhanKy[0].userId;
					vm.estimatesAB6.bheadConstructId = vm.listThanhPhanKy[0].userId;
					vm.estimatesAB6.bheadAccountId   = vm.listThanhPhanKy[0].userId;
					if(flagFunctionForm == 6 ){
						vm.disableBtnApprpval = true;
					}
					reloadStatusCA(0 , 6);
					vm.estimatesAB6.documentName = "";
					vm.disableBtnSave = false;
				});
		}
		
		vm.AB3 = {};
		vm.AB5 = {};
		vm.AB2={constructId:''};
		
		vm.exportFileDOC = exportFileDOC;
		function exportFileDOC(){
			$('#loading').show();
			switch (flagFunctionForm){
			case 1:
				//exportFileDOC form 1
				estimatesABService.exportFileDocForm1(vm.estimatesAB1.abSettlementValueId).then(function(d) {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + d.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export !"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
				break;
			case 2:
				//exportFileDOC form 2
				vm.AB2.constructId=vm.item.constructId;
				vm.AB2.abSettlementWorkId = vm.estimatesAB2.abSettlementWorkId;
				estimatesABService.exportDOCBieu2(vm.AB2.constructId).then(function(data) {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export !"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
				break;
			case 3:
				//exportFileDOC form 3
				
					vm.AB3.constructId = vm.item.constructId;
					estimatesABService.exportDOCBieu3(vm.AB3.constructId).then(function(data) {
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export !"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
				
				break;
			case 4:
				//exportFileDOC form 4
				if(vm.bean.code != null ){
					vm.item.abComplementWorkDescribeId = vm.bean.abComplementWorkDescribeId;
					vm.item.constructionId = vm.item.constructId;
				estimatesABService.exportFileDOCDetailForm4(vm.item).then(function(data) {
			  		     window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			  		     toastr.success("Export ra file .DOCX thành công")
			  		     
			  		    }).catch( function(){
							toastr.error(gettextCatalog.getString("Lỗi khi export !"));
							return;
						}).finally(function(){
							$('#loading').hide();
						});
				}else{ 
					$('#loading').hide();
					toastr.error("Chưa có dữ liệu để export, cần ghi lại trước");
					}
//				console.log("vm-item Esimate AB :"+ vm.estimateAB3);
				break;
			case 5:
				//exportFileDOC form 5
				
				vm.AB5.constructId = vm.item.constructId;
				estimatesABService.exportDOCBieu5(vm.AB5).then(function(data) {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export !"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
				//toanbd
				break;
			case 6:
				//exportFileDOC form 6
				break;
			default:
				break;
			}
		}
		
		vm.exportFilePDF = exportFilePDF;
		function exportFilePDF(){
			$('#loading').show();
			switch (flagFunctionForm){
			case 1:
				//exportFilePDF form 1
				estimatesABService.exportFilePdfForm1(vm.estimatesAB1.abSettlementValueId).then(function(d) {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + d.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export !"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
				
				break;
			case 2:
				//exportFilePDF form 2
					vm.AB2.constructId=vm.item.constructId
					vm.AB2.abSettlementWorkId = vm.estimatesAB2.abSettlementWorkId;
					estimatesABService.exportPDFBieu2(vm.AB2.constructId).then(function(data) {
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export !"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});				
				
				
				break;
			case 3:
				//exportFilePDF form 3
				
				vm.AB3.constructId = vm.item.constructId;									
					estimatesABService.exportPDFBieu3(vm.AB3.constructId).then(function(data) {
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export !"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
				
				break;
			case 4:
				//exportFilePDF form 4
				if(vm.bean.code != null ){
					vm.item.abComplementWorkDescribeId = vm.bean.abComplementWorkDescribeId;					
					vm.item.constructionId = vm.item.constructId;
				estimatesABService.exportFilePDFDetailForm4(vm.item).then(function(data) {
		  		     window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
		  		     toastr.success("Export ra file .PDF thành công")
		  		    }).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export !"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
				}else{
					$('#loading').hide();
					 toastr.error("Chưa có dữ liệu để export, cần ghi lại trước");
					 
				}
			console.log("vm-item Esimate AB :"+ vm.estimateAB3);
				break;
			case 5:
				//exportFilePDF form 5
				vm.AB5.constructId = vm.item.constructId;									
				estimatesABService.exportPDFBieu5(vm.AB5).then(function(data) {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export !"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
				//toanbd 
				break;
			case 6:
				//exportFilePDF form 6
				break;
			default:
				break;
			}
		}
		//Bat sư kien khi da trinh ki CA done
		$scope.$on("ChangeStatus", function(event, result){ 
		      if(result){
		    	  refreshGridForm4();
		    	  fetchAllEstimateAB4();
		    	  fetchAllEstimateAB2();
		    	  if(flagFunctionForm == 1){
		    		  reloadStatusCA(1 , 1);
		    		  vm.disableBtnApprpval = true;
		    		  vm.disableBtnSave = true;
				  }
		    	  if(flagFunctionForm == 3){
		    		  reloadStatusCA(1 , 3);
		    		  checkSave3();
				  }
		    	  if(flagFunctionForm == 5){
		    		  reloadStatusCA(1 , 5);
		    		  checkSave5();
				  }
		    	 //Disabled form 6
		    	  if(flagFunctionForm == 6){
		    		  reloadStatusCA(1 , 6);
		    		  vm.disableBtnApprpval = true;
		    		  vm.disableBtnSave = true;
				  }
		      }
		});
		
		function refreshGridForm4(d) {
			var grid = vm.tableAbForm4;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		//function reload trang thai kyCA ai co ky CA co the viet chung vao day
		//bien 1 la trang thai ky cua Form, bien 2 la nameForm
		function reloadStatusCA(isCheckStatusCa, nameForm){
			if(nameForm == 1){
				if(isCheckStatusCa != undefined){
					switch (isCheckStatusCa) {
					case 1:
						vm.disableEstimate1 = true;
					    vm.estimatesAB1.statusCA = "Trình ký";
					    vm.disableAll = true;
						break;
					case 2:
						vm.disableEstimate1 = true;
					    vm.estimatesAB1.statusCA = "Đã ký";
					    vm.disableAll = true;
						break;
					case 3:
						vm.disableEstimate1 = false;
					    vm.estimatesAB1.statusCA = "Từ chối ký";
					    vm.disableAll = false;
						break;
					default:
						vm.estimatesAB1.statusCA = "Soạn thảo";
						vm.disableAll = false;
						vm.disableEstimate1 = false;
						break;
					}
				}else{
					vm.estimatesAB1.statusCA = "Soạn thảo";
					vm.disableAll = false;
					vm.disableEstimate1 = false;
				}
			}
			
			if(nameForm == 3){
				if(isCheckStatusCa != undefined){
					switch (isCheckStatusCa) {
					case 1:
						vm.disableEstimate3 = true;
					    vm.estimatesAB3.statusCA = "Trình ký";
					    vm.estimatesAB3.statusCa = 1;
					    vm.disableBtnApprpval = true;
					    vm.disableAll = true;
						break;
					case 2:
						vm.disableEstimate3 = true;
					    vm.estimatesAB3.statusCA = "Đã ký";
					    vm.estimatesAB3.statusCa = 2;
					    vm.disableBtnApprpval = true;
					    vm.disableAll = true;
						break;
					case 3:
						vm.disableEstimate3 = true;
					    vm.estimatesAB3.statusCA = "Từ chối ký";
					    vm.estimatesAB3.statusCa = 3;
						break;
					default:
						vm.estimatesAB3.statusCA = "Soạn thảo";
						break;
					}
				}else{
					vm.estimatesAB3.statusCA = "Soạn thảo";
				}
			}
			
			if(nameForm == 4){
				if(isCheckStatusCa != undefined){
					switch (isCheckStatusCa) {
					case 1:
						vm.disableEstimate1 = true;
					    vm.estimatesAB4.statusCA = "Trình ký";
					    vm.disableAll = true;
						break;
					case 2:
						vm.disableEstimate1 = true;
					    vm.estimatesAB4.statusCA = "Đã ký";
					    vm.disableAll = true;
						break;
					case 3:
						vm.disableEstimate1 = true;
					    vm.estimatesAB4.statusCA = "Từ chối ký";
						break;
					default:
						vm.estimatesAB4.statusCA = "Soạn thảo";
						vm.disableEstimate1 = false;
						vm.disableAll =false;
						break;
					}
				}else{
					vm.estimatesAB4.statusCA = "Soạn thảo";
					vm.disableEstimate1 = false;
					vm.disableAll =false;
					
				}
			
			}
			
			if(nameForm == 5){
				if(isCheckStatusCa != undefined){
					switch (isCheckStatusCa) {
					case 1:
						vm.disableEstimate5 = true;
					    vm.estimatesAB5.statusCA = "Trình ký";
					    vm.estimatesAB5.statusCa = 1;
					    vm.disableBtnApprpval = true;
					    vm.disableAll = true;
						break;
					case 2:
						vm.disableEstimate5 = true;
					    vm.estimatesAB5.statusCA = "Đã ký";
					    vm.estimatesAB5.statusCa = 2;
					    vm.disableBtnApprpval = true;
					    vm.disableAll = true;
						break;
					case 3:
						vm.disableEstimate5 = true;
					    vm.estimatesAB5.statusCA = "Từ chối ký";
					    vm.estimatesAB5.statusCa = 3;
						break;
					default:
						vm.estimatesAB5.statusCA = "Soạn thảo";
						vm.disableEstimate5 = false;
						vm.disableAll = false;
						break;
					}
				}else{
					vm.estimatesAB5.statusCA = "Soạn thảo";
					vm.disableAll = false;
					vm.disableEstimate5 = false;
				}
			}
			
			if(nameForm == 6){
				if(isCheckStatusCa != undefined){
					switch (isCheckStatusCa) {
					case 1:
					    vm.disableEstimate6 = true;
					    vm.disableAll = true;
					    vm.estimatesAB6.statusCA = "Trình ký";
						break;
					case 2:
					    vm.disableEstimate6 = true;
					    vm.disableAll = true;
					    vm.estimatesAB6.statusCA = "Đã ký";
						break;
					case 3:
					    vm.disableEstimate6 = true;
					    vm.estimatesAB6.statusCA = "Từ chối ký";
					    vm.disableAll = false;
						break;
					default:
						vm.disableEstimate6 = false;
						vm.estimatesAB6.statusCA = "Soạn thảo";
						vm.disableAll = false;
						break;
					}
				}else{
					vm.estimatesAB6.statusCA = "Soạn thảo";
					vm.disableAll = false;
					vm.disableEstimate6 = false;
				}
			}
		}
		
		vm.isCheckLengData = "false";
		function validateDataForm1(obj){
			obj.exportMaterialValue = '' + obj.exportMaterialValue;
			obj.acceptMaterialValue = '' + obj.acceptMaterialValue;
			obj.lostMaterialValue = '' + obj.lostMaterialValue;
			obj.recoveryMaterialValue = '' + obj.recoveryMaterialValue;
			obj.unrecoveryMaterialValue = '' + obj.unrecoveryMaterialValue;
			obj.paidValue = '' + obj.paidValue;
			
			if(obj.exportMaterialValue.length <= 10 && obj.acceptMaterialValue.length <= 10 && 
					obj.lostMaterialValue.length <= 10 && obj.recoveryMaterialValue.length <= 10 &&
					obj.unrecoveryMaterialValue.length <= 10 && obj.paidValue.length <=10){
				vm.isCheckLengData = "true";
			}else{
				vm.isCheckLengData = "false";
				toastr.error(gettextCatalog.getString("Lỗi data ghi vào lớn hơn độ dài cho phép!"));
			}
		}
		
		function fillColorButton(btn){
			document.getElementById("btnForm1").style.backgroundColor = "";
			document.getElementById("btnForm1").style.color = "";
			document.getElementById("btnForm2").style.backgroundColor = "";
			document.getElementById("btnForm2").style.color = "";
			document.getElementById("btnForm3").style.backgroundColor = "";
			document.getElementById("btnForm3").style.color = "";
			document.getElementById("btnForm4").style.backgroundColor = "";
			document.getElementById("btnForm4").style.color = "";
			document.getElementById("btnForm5").style.backgroundColor = "";
			document.getElementById("btnForm5").style.color = "";
			document.getElementById("btnForm6").style.backgroundColor = "";
			document.getElementById("btnForm6").style.color = "";
			switch (btn) {
			case 1:
				document.getElementById("btnForm1").style.backgroundColor = "#32c5d2";
				document.getElementById("btnForm1").style.color = "#FFFFFF";
				break;
			case 2:
				document.getElementById("btnForm2").style.backgroundColor = "#32c5d2";
				document.getElementById("btnForm2").style.color = "#FFFFFF";
				break;
			case 3:
				document.getElementById("btnForm3").style.backgroundColor = "#32c5d2";
				document.getElementById("btnForm3").style.color = "#FFFFFF";
				break;
			case 4:
				document.getElementById("btnForm4").style.backgroundColor = "#32c5d2";
				document.getElementById("btnForm4").style.color = "#FFFFFF";
				break;
			case 5:
				document.getElementById("btnForm5").style.backgroundColor = "#32c5d2";
				document.getElementById("btnForm5").style.color = "#FFFFFF";
				break;
			case 6:
				document.getElementById("btnForm6").style.backgroundColor = "#32c5d2";
				document.getElementById("btnForm6").style.color = "#FFFFFF";
				break;
			default:
				document.getElementById("btnForm1").style.backgroundColor = "#32c5d2";
				document.getElementById("btnForm1").style.color = "#FFFFFF";
				break;
			}
		}
		
		function visibleButton(){
			$("#btnExDoc").hide();
			$("#btnExPDF").hide();
		}

		function showButton(){
			$("#btnExDoc").show();
			$("#btnExPDF").show();
		}
		function replaceAll(str, find, replace) {
			str = str + '';
			if(str == '')
			{
				return '';
			}
		  return str.replace(new RegExp(find, 'g'), replace);
		}
		function clearFormatAB1(){
			vm.estimatesAB1.exportMaterialValue = replaceAll(vm.estimatesAB1.exportMaterialValue, ",","");
		   	vm.estimatesAB1.acceptMaterialValue = replaceAll(vm.estimatesAB1.acceptMaterialValue, ",","");
		   	vm.estimatesAB1.lostMaterialValue = replaceAll(vm.estimatesAB1.lostMaterialValue, ",","");
		   	vm.estimatesAB1.recoveryMaterialValue = replaceAll(vm.estimatesAB1.recoveryMaterialValue, ",","");
		   	vm.estimatesAB1.paidValue = replaceAll(vm.estimatesAB1.paidValue, ",","");
		   	vm.estimatesAB1.valueDeductionSupplies = replaceAll(vm.estimatesAB1.valueDeductionSupplies, ",","");
		}
		
		//grid popup
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
		//*******************************************END*********************************************************
	}
})();