(function() {'use strict';

	var controllerId = 'listControllerCreat';

	angular.module('MetronicApp').controller(controllerId, listController);

	/* @ngInject */
	function listController($scope, $rootScope, kendoConfig, gettextCatalog,
			$kWindow, $q, ProposalEvaluation, createMemoryWork,CommonService,PopupConst,Constant,theApproval,RestEndpoint,Restangular) {
		var vm = this;
		var flag = true;
		var isAppro = false;
		
	
		
		vm.disableAll = false;
		$rootScope.isDisabled = false;
		$rootScope.showList =true;
		$rootScope.showBia=false;
		var checkonchange = 0;
		$scope.workContent={}
		vm.aMonitorId=[];
		vm.bConstructId=[];
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.theApproval = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path: '',
			    nameFile: '',
			    roleId : [],
				roleName : []
			};
		vm.approDTO= {
				statusCa: '',
				employeeId:'',
				comments:'',
				constrWorkLogsId:'',
				constrCompReMapId:''
		};
		
		vm.constrWorkLogsLabelDTO = {
				constrWoLogsLabId:'',
				constructId:'',
				createdUserId:'',
				amonitorId:'',
				bconstructId:'',
				adirectorId:'',
				bdirectorId:'',
				statusCa:''
		};
		// Common Information
		vm.item = {
			partnerName : '',
			contractCode : '',
			investProjectName : '',
			constrtCode : '',
			constrtName : '',
			constrType : '',
			provinceId : '',
			constrtAddress : '',
			constructId:'',
		};
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		  $scope.$on("ProposalEvaluation.detail", function(event, item) { 
			  if(item != undefined) { 
				  vm.item = item; 
				  } 
		  else { 
			  console.error("không nhận được dữ liệu!"); 
		  } 
		  });
		  
		  	vm.aMonitorIdList=[];
			vm.bConstructIdList=[];
			vm.aDirectorIdList=[];
			vm.bDirectorIdList=[];
			
			vm.cancelApprove={};
			
			vm.aMonitorIdObj = {
				    constructId: '',
				    contractCode  : vm.item.contractCode,
				  };
			// ky thuat nha thau thi cong
			vm.aDirectorIdObj = {
				    constructId: '',
				    contractCode  : vm.item.contractCode,
				  };
// // GIAM DOC NHA THAU THI CONG
			vm.bDirectorIdObj = {
				    constructId: '',
				    contractCode  : vm.item.contractCode,
				  };
			
			createMemoryWork.getRoleId().then(function(data) {
//		            vm.role = data;
		            vm.aMonitorIdObj.roleid = Constant.ROLE_ID["employee_roleID_CDT_GSTC"]; //10
		            vm.aDirectorIdObj.roleid = Constant.ROLE_ID["employee_roleID_CDT_DDPN"]; //2
		            vm.bDirectorIdObj.roleid = Constant.ROLE_ID["employee_roleID_DT_GDNT"]; //3
		            vm.resultAMonitorId.roleid = Constant.ROLE_ID["employee_roleID_DT_KTTC"]; //5
		            vm.resultCEO.roleid = Constant.ROLE_ID["employee_roleID_CDT_GSTC"]; //10
		        }).catch(function(data, status) {
		        });
			
			vm.aMonitorIdObj.constructId= vm.item.constructId;
			vm.aDirectorIdObj.constructId= vm.item.constructId;
			vm.bDirectorIdObj.constructId= vm.item.constructId;
		  
		  
		  vm.workLogs={};
		  vm.workLogs=ProposalEvaluation.getWorkLogs();
		  if(vm.workLogs.checkBia == 0){
			  $rootScope.showBia=true;
			  exportContentBia(vm.item.constructId);
		  }
		  

		  
		  
		  // khoi tao lai du lieu 'bia cong trinh' khi mo lai chuc nang
		  delete createMemoryWork.deleteConstrWorkLogsLabelDTO();
		  //end
		  
		  function exportContentBia(data) {
				createMemoryWork.getAllBia(data).then(function(d) {
					refreshGridBia(d.plain());
					fillDataTableBia(d.plain());
					console.log("==============================");
					vm.constrWorkLogsLabelDTO.amonitorId = d.plain()[0].amonitorId;
					vm.constrWorkLogsLabelDTO.bconstructId =d.plain()[0].bconstructId;
					vm.constrWorkLogsLabelDTO.adirectorId=d.plain()[0].adirectorId;
					vm.constrWorkLogsLabelDTO.bdirectorId=d.plain()[0].bdirectorId;
					vm.constrWorkLogsLabelDTO.constrCompReMapId = d.plain()[0].constrCompReMapId;
					vm.constrWorkLogsLabelDTO.constrWoLogsLabId = d.plain()[0].constrWoLogsLabId;
					vm.constrWorkLogsLabelDTO.constructId = d.plain()[0].constructId;
					vm.constrWorkLogsLabelDTO.statusCa = d.plain()[0].statusCa;
					vm.constrWorkLogsLabelDTO.createdUserId = d.plain()[0].createdUserId;
					createMemoryWork.setConstrWorkLogsLabelDTO(vm.constrWorkLogsLabelDTO);
					vm.isDis = true;
				}, function() {
					 console.error('Có lỗi exportContentBia');
				});
			}
		// End Common Information
		
		// Search
		vm.exportContent =  function() {
			vm.criteria.constructId = vm.item.constructId ;
			exportContent(vm.criteria);
		};

		vm.criteria = {
			contractorComments : '',
			constrWorkLogsId:'',
			additionChangeArise : '',
			workContent : '',
			logDate : '',
			monitorComments : '',
			estimatesWorkItemId : '',
			workItemName:'',
			constructId : '',
			creatOrUpdate : '',
			statusCa:'',
			contractCode:'',
			constrtCode: '',
			constrtName:'',
			constrtAddress:'',
			aMonitorId:'',
			bConstructId:'',
			createdUserId:''
		};

		vm.criteria.creatOrUpdate = "creat";
		vm.criteria.constructId = vm.item.constructId ;
		
		vm.resultAMonitorId = {
			    constructId: '',
			    contractCode  : vm.item.contractCode,
			  };
		vm.resultCEO = {
			    constructId: '',
			    contractCode  : vm.item.contractCode,
			  };
		
		
		getResultCEO(vm.resultCEO);
		  

		  
		  function getResultCEO(object) {
			  createMemoryWork.getListEmployeeByRole(object).then(function(data) {
				  vm.bConstructId = data.plain();
 if(vm.bConstructId.length == 0){
// toastr.warning("Không có giám sát thi công nào");
 return;
 }
				  vm.criteria.bConstructId = vm.bConstructId[0].id;
		     })
		  }
		  
		  $scope.$on("ChangeStatusApproval", function(event, result){ 
			   if(result){
				   vm.criteria={};
				   vm.criteria.creatOrUpdate = "creat";
				   vm.criteria.constructId = vm.item.constructId ;
				   exportContent(vm.criteria);
				   $rootScope.showList =true;
				   if(vm.workLogs.checkBia != 0){
						  $rootScope.showBia=false;
					}else{
						  $rootScope.showBia=true;
					}
				   if($rootScope.showBia){
					   exportContentBia(vm.item.constructId);
				   }
				   $rootScope.isDisabled = false;
			   }
			  });
		  
		  $scope.$on("ChangeBia", function(event, result){ 
			   if(result){
				   $rootScope.showBia=true;
				   vm.workLogs.checkBia=0;
				   exportContentBia(vm.item.constructId);
			   }
			  });
		  
		  createMemoryWork.setIsAppro(false);
		// Load Table
		exportContent(vm.criteria);

		function refreshGrid(d) {
			var grid = vm.reportGridMemory;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.resultAMonitorId.constructId= vm.item.constructId;
		vm.resultCEO.constructId= vm.item.constructId;
	
		// Click Duyet
		vm.Appro = function(){
			if($rootScope.showList && vm.criteria.constrWorkLogsId==undefined){
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			
			if(vm.criteria.statusCa ==2||vm.criteria.statusCa ==3){
				toastr.warning("Bản ghi đã hoàn thành");
				return ;
			}
			vm.approDTO.statusCa = 2;
			vm.approDTO.comments = "";
			$rootScope.isDisabled = true;
			isAppro = true;
			vm.criteria.creatOrUpdate = "update";
			$rootScope.showList =false;
			flag = true;
		};
		
		// refresh page
		vm.undo= undo();
			function undo(){
			vm.criteria={};
			vm.criteria.constructId = vm.item.constructId ;
			exportContent(vm.criteria);
		}
		
		vm.creat = function(){
			vm.criteria={};
			vm.criteria.creatOrUpdate = "creat";
			vm.criteria.createdUserId = Constant.user.srvUser.userId;
			vm.criteria.constructId = vm.item.constructId;
			if(vm.aMonitorId.length !=0 && vm.bConstructId.length != 0){
				vm.criteria.aMonitorId = vm.aMonitorId[0].id;
				vm.criteria.bConstructId = vm.bConstructId[0].id;
			}
			
			$rootScope.showList =false;
			// $rootScope.isDisabled1 = true;
			$rootScope.isDisabled = false;	
			isAppro = false;
			$rootScope.showBia=false;
		};
		
		vm.showGrid = function(){
			$(".k-invalid-msg").hide();
			if($rootScope.showList && vm.criteria.constrWorkLogsId==undefined){
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			if(vm.criteria.statusCa==1||vm.criteria.statusCa==2){
				vm.disableAll = true;
			}else{
				vm.disableAll = false;
			}
			
			if(vm.criteria.createdUserId != Constant.user.srvUser.userId){
				vm.disableAll = true;
			}else{
				vm.disableAll = false;
			}
			if(!flag){
				vm.criteria.creatOrUpdate = "update";
				$rootScope.showList =false;
				flag = true;

				$rootScope.isDisabled = false;
				$rootScope.showBia  = false;
			}else{
				vm.criteria={};
				vm.criteria.creatOrUpdate = "creat";
				vm.criteria.constructId = vm.item.constructId ;
				exportContent(vm.criteria);
				$rootScope.showList =true;
				// $rootScope.isDisabled1 = true;
				$rootScope.isDisabled = false;
				if(vm.workLogs.checkBia != 0){
					  $rootScope.showBia=false;
				  }else{
					  $rootScope.showBia=true;
				  }
				
			}
			isAppro =false;
			
		};
		
		// creat
		vm.creatConstrWorkLogs = function() {
			 if (!vm.validator.validate()){ return};
			if(vm.criteria.estimatesWorkItemId==''){
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			if(isAppro){
				createMemoryWork.appro(vm.approDTO).then(function(d) {
					var x = d;
					if(x == '1'){
						toastr.warning("Chưa tới lượt duyệt");
						return;
					}
					if(x == '2'){
						toastr.warning("Đã duyệt rồi");
						return;
					}
					if(x == '4'){
						toastr.success("Từ chối duyệt thành công");
					}
					if(x == '3'){
						toastr.success("Duyệt thành công");
					}
					if(x == '5'){
						toastr.warning("Bạn không được phép phê duyệt");
					}
					isAppro =false;
					vm.approDTO.statusCa = 2;
					vm.approDTO.comments = "";
					vm.criteria={};
					vm.criteria.constructId = vm.item.constructId ;
					exportContent(vm.criteria);
					$rootScope.showList =true;
					$rootScope.isDisabled = false;	
					// $rootScope.isDisabled1 = true;
					flag = false;
					vm.criteria.creatOrUpdate = "creat";
				}, function() {
					 console.error('Error while fetching minout');
				});
				return;
			}
			if(vm.criteria.statusCa==1||vm.criteria.statusCa==2 ){
				toastr.warning("Không được sửa những bản ghi đang trình duyệt hoặc duyệt");
				return;
			}
			if(vm.criteria.creatOrUpdate == "update" && vm.criteria.createdUserId != Constant.user.srvUser.userId){
				toastr.warning(gettextCatalog.getString("Bạn không có quyền sửa bản ghi này !"));
				return;
			}
			if(vm.criteria.creatOrUpdate =="update"){
				vm.criteria.statusCa = 0;
				};
			vm.criteria.constructId = vm.item.constructId ;
			if(vm.criteria.workContent === undefined){
			}else{
				createMemoryWork.creatConstrWorkLogs(vm.criteria).then(function(d) {
					if(d==-1){
						toastr.warning("Công trình đã kết thúc, không được tạo nhật ký công trình nữa");
						return;
					}
					if(vm.criteria.creatOrUpdate == "creat"){
						toastr.success("Thêm mới thành công");
						vm.criteria={};
						vm.criteria.creatOrUpdate = "creat";
						vm.criteria.constructId = vm.item.constructId ;
						exportContent(vm.criteria);
						$rootScope.showList =true;
						$rootScope.isDisabled = false;	
						// $rootScope.isDisabled1 = true;
						flag = false;
					}
					if(vm.criteria.creatOrUpdate == "update"){
						toastr.success("Cập nhật thành công");					
						$rootScope.showList =true;
						refreshGridBia();
						fillDataTableBia();
						vm.criteria={};
						vm.criteria.creatOrUpdate = "creat";
						vm.criteria.constructId = vm.item.constructId ;
						exportContent(vm.criteria);
					}
				});
			}
		};
		
		function exportContent(data) {
			createMemoryWork.getAllConstrWorkLogs(data).then(function(d) {
				fillDataTable(d.plain());
				refreshGrid(d.plain());
				isAppro = false;
			}, function() {
				 console.error('Error while fetching minout');
			});
		}
		
		// ONCHANGE of gridView listWorkLog
		vm.onChange = onChange;
		function onChange(){
			checkonchange = 0;
			if (vm.reportGridMemory.select().length > 0) {
				var tr = vm.reportGridMemory.select().closest("tr");
				var dataItem = vm.reportGridMemory.dataItem(tr);
				vm.cancelApprove = dataItem;
				vm.criteria.additionChangeArise= dataItem.additionChangeArise;
				vm.criteria.contractorComments= dataItem.contractorComments;
				vm.criteria.workContent= dataItem.workContent;
				vm.criteria.logDate= dataItem.logDate;
				vm.criteria.monitorComments= dataItem.monitorComments;
				vm.criteria.workContent= dataItem.workContent;
				vm.criteria.workItemName= dataItem.workItemName;
				vm.criteria.estimatesWorkItemId=dataItem.estimatesWorkItemId;
				vm.criteria.constrWorkLogsId=dataItem.constrWorkLogsId;
				vm.criteria.statusCa = dataItem.statusCa;
				vm.criteria.aMonitorId = dataItem.aMonitorId;
				vm.criteria.bConstructId= dataItem.bConstructId;
				vm.criteria.creatOrUpdate = "update";
				vm.criteria.createdUserId=dataItem.createdUserId;
				flag = false;
				checkonchange = checkonchange +1;
				vm.theApproval.code = dataItem.code;
				vm.theApproval.constructId = dataItem.constructId;
				vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
				vm.theApproval.stringEmployee  = dataItem.aMonitorId + ',' +dataItem.bConstructId ;
				vm.theApproval.isSign ='theApproval';
				vm.theApproval.roleId = [vm.resultAMonitorId.roleid,vm.resultCEO.roleid];
				vm.theApproval.roleName = ["Kỹ thuật nhà thầu thi công","Giám sát thi công"];
				theApproval.setItem(vm.theApproval);
				
				vm.approDTO.employeeId = Constant.getUser().srvUser.catEmployeeId;
				vm.approDTO.constrWorkLogsId= vm.criteria.constrWorkLogsId;
				vm.approDTO.constrCompReMapId = dataItem.constrCompReMapId;
				
			}
		}
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistcreatemem").checked == true){
				document.getElementById("checkalllistcreatemem").checked = false;
			}
		}
		
		function fillDataTable(data) {
			var deferred = $q.defer();
			vm.gridOptions = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						change : onChange,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
								{	title : "<b>STT</b>",
									field : "ass",
									template: dataItem => $("#IdTable").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
									 headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
							            width : 60
								},
								{
									title :"<input type='checkbox' id='checkalllistcreatemem' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
									template :"<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:center;"},
									width : 50
								},
								{
									title : "<b>Mã nhật ký</b>",
									field : "code",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:center;"},
									width : 120
								},
								{
									title : "<b>Ngày nhật ký</b>",
									field : "logDate",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:center;"},
									width : 150
								},
								{
									title : "<b>Nội dung công việc</b>",
									field : "workContent",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:left;"},
									width : 300
								},
								{
									title : "<b>Trạng thái</b>",
									field : "statusCa",
									template : function($scope) {
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
											return "Từ chối Duyệt";
										}
									},
									headerAttributes: {style:"text-align:center;"},
									attributes: { style: "text-align:left;", class:"statusColumn"},
									width : 120
								}]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
/*		$("#IdTable").kendoTooltip({
			filter: "table",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");*/
/*		$("#IdTableBia").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");*/
		vm.chkSelectAll = function chkSelectAll(item) {
	    	var grid = vm.reportGridMemory;
	    	chkSelectAllBase(vm.chkAll, grid);
	    };
		
		// Show Popup
		vm.showEstimatesWorkForm = showEstimatesWorkForm;
    	function showEstimatesWorkForm() {
    		
    		createMemoryWork.getEstimatesWork(vm.item.constructId).then(function(result){
    			var templateUrl = 'views/popup/gridViewWorkName.html';
    			var title = 'Tên công trình'; 
    			vm.ProjectGridOptions = kendoConfig.getGridOptions({
    	    		autoBind: true,
    	    		dataSource: result.plain(),
    	    		change : onChange,
    	    		noRecords: true,
    	    		messages: {
    	    			noRecords: gettextCatalog.getString("Không có kết quả nào")
    	    		},
    	    		columns: [
    	    		          {	title : "<b>STT</b>",
    	    		        	  field : "as", 
    	    		        	  template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
									 headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
							            width : 80
								},
								{
    	    			title: gettextCatalog.getString("Mã Công trình"),
    	    			field: "workItemCode",
    	    			width: 100,
    	    		}, {
    	    			title: gettextCatalog.getString("Tên công trình"),
    	    			field: "workItemName",
    	    			width: 100
    	    		}]
    	    	});
    	
    			CommonService.populateDataToGrid(templateUrl, title, vm.ProjectGridOptions, vm, PopupConst.ProposalEvaluation['Project']);
    		});
    	}
    	
    	// End Show Popup
		// Delete Multi with checkbox
		vm.multiDelete = function() {
			
			var selectedRow = [];
			var flag = false;
			var listCreatUserId = [];
			var grid = vm.reportGridMemory;
			grid.table.find("tr").each(function(idx, item) {
				
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					var dataItem = grid.dataItem(item);
					listCreatUserId.push(dataItem.createdUserId);
					selectedRow.push(dataItem.constrWorkLogsId);
				}
			});
			
			if (selectedRow.length == 0) {
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			
			for(var i = 0;i<listCreatUserId.length;i++){
				if(listCreatUserId[i] != Constant.user.srvUser.userId){
					selectedRow.splice(i,1);
					flag = true;
    			}
			}
			
			if (flag && selectedRow.length == 0) {
				toastr.warning("Bạn không có quyền xóa bản ghi nhân viên khác tạo!");
				return;
			}
			
			if (confirm('Xác nhận xóa')) {	
				createMemoryWork.deleteConstrWorkLogs(selectedRow).then(function() {
					toastr.success("Xóa bản ghi thành công");
					vm.criteria= {};
					vm.criteria.constructId = vm.item.constructId ;
					exportContent(vm.criteria);
				}, function(errResponse) {
			         if (errResponse.status == 302){
			             toastr.warning("Bản ghi đang được sử dụng");
			            }else{
			             toastr.warning("Bản ghi không được xóa");
			         }
		         });
			}
			isAppro =false;
		};
		
		// ExportFile
		vm.exportFile = function() {
			var selectedRow = [];
			selectedRow.push(vm.item.contractCode);
			var grid = vm.reportGridMemory;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.constrWorkLogsId);
				}
			});
			if(selectedRow.length == 0 && checkonchange == 0 && vm.criteria.constrWorkLogsId == null )
			{
			    toastr.warning("Bạn cần chọn bản ghi để export !");
			}else{
				if(checkonchange == 1 && vm.showList == true)
				{
			       	$('#loading').show();
			       	
			       	createMemoryWork.exportFileConstrWorkLogs(vm.criteria).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
				else if(selectedRow.length > 0)
				{
					$('#loading').show();
						if(selectedRow.length < 2 && vm.criteria.constrWorkLogsId != null) {
				       		selectedRow.push(vm.criteria.constrWorkLogsId);
				       	}
			    	    createMemoryWork.exportList(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
			    	}).catch( function(){
			    	    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			    	    return;
			    	}).finally(function(){
			    	    $('#loading').hide();
			    	});
				}
				else if(checkonchange == 1 && selectedRow.length > 0)
				{
					$('#loading').show();
			    	    createMemoryWork.exportList(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
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
			       	createMemoryWork.exportFileConstrWorkLogs(vm.criteria).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .PDF thành công!"));
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
			}
		};
		
		vm.ExportDOC = function() {
			var selectedRow = [];
			selectedRow.push(vm.item.contractCode);
			var grid = vm.reportGridMemory;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.constrWorkLogsId);
				}
			});
			if(selectedRow.length == 0 && checkonchange == 0 && vm.criteria.constrWorkLogsId == null )
			{
			    toastr.warning("Bạn cần chọn bản ghi để export !");
			}else{
				if(checkonchange == 1 && vm.showList == true)
				{
			       	$('#loading').show();
			       	createMemoryWork.exportFileDoc(vm.criteria).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .DOCX thành công!"));
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
				else if(selectedRow.length > 0)
				{
					$('#loading').show();
						if(selectedRow.length < 2 && vm.criteria.constrWorkLogsId != null) {
				       		selectedRow.push(vm.criteria.constrWorkLogsId);
				       	}
			    	    createMemoryWork.exportListDoc(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .DOCX thành công!"));
			    	}).catch( function(){
			    	    toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			    	    return;
			    	}).finally(function(){
			    	    $('#loading').hide();
			    	});
				}
				else if(checkonchange == 1 && selectedRow.length > 0)
				{
					$('#loading').show();
			    	    createMemoryWork.exportListDoc(selectedRow).then(function(data){
			    	    window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    	     toastr.success(gettextCatalog.getString("Export file .DOCX thành công!"));
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
			       	createMemoryWork.exportFileDoc(vm.criteria).then(function(data){
			       		window.location = RestEndpoint.BASE_SERVICE_URL + "fileservice/downloadFileATTT?" + data.fileName;
			    		toastr.success(gettextCatalog.getString("Export file .DOCX thành công!"));
			        }).catch( function(){
			        	toastr.error(gettextCatalog.getString("Lỗi khi export!"));
			        	return;
			        }).finally(function(){
						$('#loading').hide();
					});
				}
			}
		};
		
		function refreshGridBia(d) {
			var grid =vm.reportGridMemoryBia;
			
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function fillDataTableBia(data) {
			var deferred = $q.defer();
			vm.gridOptionsBia = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
						           {
						        	   title : "<b>STT</b>",
						        	   field : "afs",
						        	   template: dataItem => $("#IdTableBia").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        	   headerAttributes: {style:"text-align:center;"},
								       attributes:{style:"text-align:center;"},
						        	   width : 80
						           },
						           {
										title :"<b>Mã Bìa</b>",
										field : "code",
										headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:left;"},
										width : 160
									},
									 {
										title :"<b>Thủ trưởng chủ đầu tư</b>",
										field : "aDirectorName",
										headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:left;"},
										width : 180
									},
									 {
										title :"<b>Giám sát thi công</b>",
										field : "aMonitorName",
										headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:left;"},
										width : 160
									},
									 {
										title :"<b>Kỹ thuật thi công</b>",
										field : "bConstructName",
										headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:left;"},
										width : 160
									},
									{
										title :"<b>Giám đốc nhà thầu thi công</b>",
										field : "bDirectorName",
										headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:left;"},
										width : 220
									},
						           {
										title : "<b>Trạng thái</b>",
										field : "statusCa",
										template : function($scope) {
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
												return "Từ chối Duyệt";
											}
										},
										headerAttributes: {style:"text-align:center;"},
								        aattributes: { style: "text-align:left;", class:"statusColumnBia"},
										width : 120
									}]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
		
		// Trình duyệt
		vm.signCA = function() {
			if(vm.criteria.statusCa == undefined){
				toastr.warning("Bản chưa chọn bản ghi nào");
				return ;
			}
			if(vm.criteria.statusCa != 0){
				$('#loading').hide();
				toastr.warning("Bản ghi đã trình duyệt");
				return ;
			}
			$('#loading').show();
			createMemoryWork.exportFileConstrWorkLogs(vm.criteria).then(function(data){
				vm.theApproval.path= data.fileName;
				vm.theApproval.nameFile = 'BM-TCNT-04.pdf';
				theApproval.setItem(vm.theApproval);
				goTo('THE_APPROVAL');
				isAppro =false;
        	}).catch( function(){
        		toastr.error(gettextCatalog.getString("Lỗi khi export!"));
        		return;
        	}).finally(function(){
        		$('#loading').hide();
        	});
        	
		};
		//ngoccx
	      //huy trinh duyet
			vm.cancelApprovalBtn= function(){
				var grid = vm.reportGridMemory;
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
					return;
				}
				vm.cancelApprove.tableName = 'CONSTR_WORK_LOGS';
				vm.cancelApprove.tableId = vm.cancelApprove.constrWorkLogsId;
				vm.cancelApprove.tableIdField = 'CONSTR_WORK_LOGS_ID';
				if(vm.cancelApprove.statusCa == 1){
					if(vm.cancelApprove.createdUserId != Constant.user.srvUser.userId){
						toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
						return;
					}else{
						if(confirm('Xác nhận hủy trình duyệt')){
							createMemoryWork.cancelAprroval(vm.cancelApprove).then(function() {
								status = true;
								$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
								vm.criteria= {};
								vm.criteria.constructId = vm.item.constructId ;
								exportContent(vm.criteria);
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

			  function getResultMonitoring(object) {
				  var deferred = $q.defer();
				  createMemoryWork.getListEmployeeByRole(object).then(function(data) {
			    vm.aMonitorId = data.plain();
			    vm.bConstructIdList = data.plain();
			    createMemoryWork.setbConstructIdList(vm.bConstructIdList);
			    deferred.resolve('thread1');
	 if(vm.aMonitorId.length == 0 ){
	// toastr.warning("Không có kỹ thuật nhà thầu thi công nào");
	 return;
	 }
			    
			    
			    vm.criteria.aMonitorId = vm.aMonitorId[0].id;
			    
			     })
			     return deferred.promise;
			  }
		function getResultAMonitor(object) {
			var deferred = $q.defer();
			 createMemoryWork.getListEmployeeByRole(object).then(function(data) {
				  vm.aMonitorIdList = data.plain();
				  createMemoryWork.setaMonitorIdList(vm.aMonitorIdList);
				  deferred.resolve('thread2');
		     });
			 return deferred.promise;
		  }
		
		
		function getResultADirector(object) {
			var deferred = $q.defer();
			 createMemoryWork.getListEmployeeByRole(object).then(function(data) {
				 vm.aDirectorIdList = data.plain();
				 createMemoryWork.setaDirectorIdList( vm.aDirectorIdList);
				  deferred.resolve('thread3');
				 
		     });
			 return deferred.promise;
		  }
		
		
		function getResultBDirector(object) {
			var deferred = $q.defer();
			 createMemoryWork.getListEmployeeByRole(object).then(function(data) {
				 vm.bDirectorIdList = data.plain();
				 createMemoryWork.setbDirectorIdList(vm.bDirectorIdList);
		         deferred.resolve('thread4');
		     });
			 return deferred.promise;
		  }
		
		
		 $q.all([getResultBDirector(vm.bDirectorIdObj),getResultADirector(vm.aDirectorIdObj),getResultAMonitor(vm.aMonitorIdObj),getResultMonitoring(vm.resultAMonitorId)]).then(
				 
			      function(successResult) { 
			    	  vm.isDisableSignConstrWorkLogsLabel = false;
			    	  vm.isDis = false;
			        }, function(failureReason) { 
			          vm.isDisableSignConstrWorkLogsLabel = true;
			          vm.isDis = true;
			        }
				
		 
		 );
		 
		 vm.downloadInstruction = function (){
			 createMemoryWork.downloadInstruction().then(function () {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadImport?" + data.fileName;
				}, function (e) {
					toastr.error(gettextCatalog.getString("Lỗi, không tải được biểu mẫu !"));
				});
			}
		

		// Trình duyệt bìa nhật ký công trình
		vm.signConstrWorkLogs = function(){
			
			if(vm.workLogs.checkBia == 0){
				toastr.warning("Bìa chỉ được tạo 1 lần");
				return;
			}
			
			if (confirm('Chỉ trình duyệt bìa khi công trình đã kết thúc và không được tạo nhật ký công trình nữa')) {	
				goTo('CONSTR_WORK_LOGS_THE_APPROVAL');
			}
		};
		
		vm.signConstrWorkLogsLabel = function(){
			createMemoryWork.setIsAppro(false);
			goTo('CONSTR_WORK_LOGS_THE_APPROVAL');
		};
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
		
		

	}
})();