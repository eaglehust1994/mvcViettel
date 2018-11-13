(function() {
	'use strict';

	var controllerId = 'listControllerOrgan';

	angular.module('MetronicApp').controller(controllerId, listController);

	/* @ngInject */
	function listController($scope, $rootScope, kendoConfig, gettextCatalog,
			$kWindow, $q, ProposalEvaluation, listWorkOrganizationPlan,Constant, theSignCA) {
		var vm = this;
		var flag = true;
		vm.folder = '';
		vm.files = [];
		var list = [];
		var list1 =[];
		vm.monitoring=[];
		vm.monitoring1=[];
		vm.aa = [];
		
		$rootScope.isDisabled = false;
		$rootScope.showList= true;
		
		$rootScope.showListFile= false;
		vm.disableAll = false;
		vm.attachTypeKey = '';
		vm.attachTypeValue = '';
		
	// Ký CA
		vm.theSign = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path: '',
			    nameFile: '',
			    roleId : [],
			    roleName:[]
			};
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
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
				getcatEmployeeId:''
			};
			vm.item = ProposalEvaluation.getItem();
			if(vm.item == null) {
				vm.item = CommonService.getItem();
			}
			$scope.$on("ProposalEvaluation.detail", function(event, item) {
				if (item != undefined) {
					vm.item = item;
				} else {
					console.error("không nhận được dữ liệu!");
				}
			});
			
			$scope.$on("ChangeStatus", function(event, result){ 
			      if(result){
			    	  refreshGridOrgan();
			      }
			     });
		// End Common Information

			vm.constrOrgPlan = {
					  representInvestorId :'',
					  constrOrgPlanId : '',
					  representContractorId: '',
					  createdUserId : '',
					  statusCa : '',
					  isActive : '',
					  constructId:'',
					  code : '',
					  creatOrUpdate: '',
					  constrCompReMapId:'',
					  listDocumentName: [],
					  documentPath:'',
					  documentName:'',
			};
			listWorkOrganizationPlan.getAttachTypeKey().then(function(data) {
				vm.attachTypeKey = data.attachTypeKey;
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
			listWorkOrganizationPlan.getAttachTypeValue().then(function(data) {
				vm.attachTypeValue = data.attachTypeValue;
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
		function refreshGridOrgan(d) {
			var grid = vm.reportGrid1;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
		vm.undo = function(){
			if($rootScope.showList && vm.reportGrid1.select().length == 0){
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			if(vm.constrOrgPlan.statusCa==1||vm.constrOrgPlan.statusCa==2){
				vm.disableAll = true;
				//toastr.warning("Không được sửa những bản ghi đang trình ký hoặc đã ký");
				
			}else{
				vm.disableAll = false;
			}
			if(vm.constrOrgPlan.createdUserId != Constant.user.srvUser.userId){
				vm.disableAll = true;
				
			}else{
				vm.disableAll = false;
			}
			
			if(!flag){
				vm.constrOrgPlan.creatOrUpdate = "update";
				$rootScope.isDisabled = true;
				$rootScope.showList= false;
				flag = true;
				exportContentFile(vm.constrOrgPlan.constrOrgPlanId);
				$rootScope.showListFile= true;
			}else{
				vm.constrOrgPlan.creatOrUpdate = "creat";
				$rootScope.isDisabled = false;
				$rootScope.showList= true;
				flag = true;
				vm.constrOrgPlan.contractId = vm.item.contractId;
				exportContentOrgan();
				$rootScope.showListFile= false;
			}
		};
		
		vm.constrOrgPlan.creatOrUpdate= "creat";
		vm.constrOrgPlan.constructId = vm.item.constructId;
		vm.constrOrgPlan.contractId = vm.item.contractId;
		// Load Table
		exportContentOrgan();
		
		function exportContentOrgan() {
			vm.constrOrgPlan.contractId = vm.item.contractId;
			listWorkOrganizationPlan.getAllConstrOrganizationPlan(vm.constrOrgPlan).then(function(d) {
				vm.aa = d.plain();
				for(var i = 0 ; i<vm.aa.length ; i++){
					if(vm.aa[i].constrOrgPlanId == vm.constrOrgPlan.constrOrgPlanId){
						vm.constrOrgPlan = vm.aa[i];
						vm.constrOrgPlan.creatOrUpdate = "update";
					}
				}
				fillDataTableOrgan(d.plain());
				refreshGridOrgan(d.plain());
			}, function() {
				fillDataTableOrgan([]);
				refreshGridOrgan([]);
			});
		}
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistpatctc").checked == true){
				document.getElementById("checkalllistpatctc").checked = false;
			}
		}
		vm.handleCheckChild = function(item){
			if(document.getElementById("checkalllistpatctccon").checked == true){
				document.getElementById("checkalllistpatctccon").checked = false;
			}
		}
		function fillDataTableOrgan(data) {
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
								{
									title : "<b>STT",
									field : "as",
									 template: dataItem => $("#IdTable1").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
									 headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
							            width : 80
								},
								{
									title :"<input type='checkbox' id='checkalllistpatctc' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
									template :"<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:center;"},
									width : 80
								},
								{
									title : "<b>Mã Phiếu</b>",
											field : "code",
											headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:left;"},
									width : 200
								},
								{
									title :"<b>Mã Công trình</b>",
									field : "as",
											template : function() {
												return vm.item.constrtCode;
											},
											headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:left;"},
									width : 200
								},
								{   
									title :"<b>Tên công trình</b>",
									field : "as",
											template : function() {
												return vm.item.constrtName;
											},
											headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:left;"},
									width : 200
								},
								{
									title : "<b>Trạng thái</b>",
							field : "statusCa",
							template : function($scope) {
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
							headerAttributes: {style:"text-align:center;"},
					        attributes:{style:"text-align:left;" , class:"statusColumn"},
							width : 120
						}]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
		
		$("#IdTable1").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");
		// End Load Table
		
		function exportContentFile(parentId) {
			listWorkOrganizationPlan.getListByParentIdAndType(parentId).then(function(d) {
				fillDataTableFile(d.plain());
				refreshGridFile(d.plain());
			}, function() {
				//toastr.error('Error while fetching minout');
			});
		}
		
		function refreshGridFile(d) {
			var grid = vm.gridViewFile;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		// Danh sách File Phụ kèm theo
		function fillDataTableFile(data) {
			var deferred = $q.defer();
			vm.gridOptionFile = kendoConfig
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
								{
									title : "<b>STT</b>",
									field : "as",
									 template: dataItem => $("#gridTable").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
									 headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
							            width : 60
								},
								{
									title :"<input type='checkbox' id='checkalllistpatctccon' name='gridchkselectall' ng-click='vm.chkAllSelect();' ng-model='vm.chkAllSe' />",
									template :"<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheckChild()'/>",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:center;"},
									width : 60
								},
								{
									title : "<b>Tên File</b>",
									 template :  function(dataItem) {
					        	    	 return "<a href='"+Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.documentPath + "'>" + dataItem.documentName + "</a>";},
											headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:left;"},
									width : 200
								},
								{
									title :"<b>Loại file</b>",
									field : "type",
									template : function($scope) {
										if ($scope.type == vm.attachTypeKey) {
											return "File Chính";
										}
										if ($scope.type == vm.attachTypeValue) {
											return "File Phụ";
										}
									},
											headerAttributes: {style:"text-align:center;"},
									        attributes:{style:"text-align:left;"},
									width : 200
								}
								]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
		$("#gridTable").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");
		vm.chkAllSelect = chkAllSelect;
		function chkAllSelect() {
	    	var grid =vm.gridViewFile;
	    	chkSelectAllBase(vm.chkAllSe, grid);
		}
		
		vm.chkSelectAll = chkSelectAll;
		function chkSelectAll() {
	    	var grid =vm.reportGrid1;
	    	chkSelectAllBase(vm.chkAll, grid);
		}
		
		
// Click to table
		vm.onChange = onChange;
		function onChange(){
			if (vm.reportGrid1.select().length > 0) {
				var tr = vm.reportGrid1.select().closest("tr");
				var dataItem = vm.reportGrid1.dataItem(tr);
				vm.constrOrgPlan = dataItem;
				vm.constrOrgPlan.creatOrUpdate = "update";
				vm.theSign.code = vm.constrOrgPlan.code;
				vm.theSign.constructId = vm.constrOrgPlan.constructId;
				vm.theSign.constrCompReMapId=vm.constrOrgPlan.constrCompReMapId;
				vm.theSign.stringEmployee = vm.constrOrgPlan.representContractorId + ',' +vm.constrOrgPlan.representInvestorId;
				vm.theSign.path = dataItem.documentPath;
				vm.theSign.nameFile  = dataItem.documentName;
				vm.theSign.roleId = [vm.resultMonitoring.roleid,vm.resultCEO.roleid];
				vm.theSign.roleName = ["Đại diện nhà thầu XL","Đại diện chủ đầu tư"];
				vm.theSign.isSign = 'theSignCA';
				
				theSignCA.setItem(vm.theSign);
			// $rootScope.$broadcast("ChangeTheSign", vm.theSign);
				flag = false;
// $rootScope.$broadcast("listWorkOrganizationPlan.item", vm.theSign);
			}
		}

		function detail() {
			if (vm.ProposalEvaluation.select().length > 0) {
				vm.showDetail = true;
			} else {
				toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
			}
		}
		
		// MulDel
		vm.multiDelete = function() {
			if($rootScope.showList== false && $rootScope.showListFile== false){
				return;
			}
			if($rootScope.isDisabled == true){
				return;
			}
			
			var selectedRow = [];
			var grid = vm.reportGrid1;
			var listCreatUserId = [];
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var dataItem = grid.dataItem(item);
					if(dataItem.statusCa == 0 || dataItem.statusCa == 3){
						listCreatUserId.push(dataItem.createdUserId);
						selectedRow.push(dataItem.constrOrgPlanId);
					}
				}
			});

			if (selectedRow.length == 0) {
				toastr.warning("Bạn chưa chọn bản ghi nào(trạng thái trình ký, đã ký không được xóa)");
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
				listWorkOrganizationPlan.deleteConstrOrganizationPlan(selectedRow).then(function() {
					toastr.success("Xóa bản ghi thành công");
					vm.criteria= {};
					exportContentOrgan(vm.criteria);
				}, function(errResponse) {
			         if (errResponse.status == 302){
			             toastr.warning("Bản ghi đang được sử dụng");
			            }else{
			             toastr.warning("Bản ghi không được xóa");
			         }
		         });
			}
		};
		
		vm.creat = function() {
			vm.constrOrgPlan ={};
			vm.theSign={};
			vm.constrOrgPlan.constructId = vm.item.constructId;
			vm.constrOrgPlan.creatOrUpdate="creat";
			if(vm.monitoring.length != 0 && vm.monitoring1.length != 0){
				vm.constrOrgPlan.representInvestorId = vm.monitoring[0].id;
				vm.constrOrgPlan.representContractorId = vm.monitoring1[0].id;
			}
			$rootScope.isDisabled = true;
			$rootScope.showList= false;
			$rootScope.showListFile= false;
			vm.constrOrgPlan.createdUserId = Constant.user.srvUser.userId;
			vm.files = [];
			$("#upload_file_create" ).val("");
			$("#multi_upload_file" ).val("");
		};
 		// ExportFile
		vm.exportFile = function() {
			if($rootScope.showList== false && $rootScope.showListFile== false){
				return;
			}
				if(vm.theSign.path==''||vm.theSign.path==undefined){
					toastr.warning("Bạn chưa chọn bản ghi nào");
					return ;
				}
	        	//window.location = "fileservice/downloadFileATTT?" + vm.theSign.path;
	        	if($rootScope.showList == true){
	        		$('#loading').show();
	        		listWorkOrganizationPlan.downloadZipParentchild(vm.constrOrgPlan.constrOrgPlanId).then(function(d) {
						data = d.plain();
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export!"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
	        	}
	        	if($rootScope.showListFile == true ){
	        		var selectedRow = [];
	        		var grid = vm.gridViewFile;
	    			
	    			grid.table.find("tr").each(function(idx, item) {
	    				var row = $(item);
	    				var checkbox = $('[name="gridcheckbox"]', row);
	    				
	    				if (checkbox.is(':checked')) {
	    					// Push id into selectedRow
	    					var dataItem = grid.dataItem(item);
	    					selectedRow.push(dataItem.documentPath);
	    				}
	    			});
	    			
	    			if (selectedRow.length == 0){
	    				toastr.warning("Bạn chưa chọn bản ghi nào ");
	    				return;
	    			}
	        		$('#loading').show();
	        		listWorkOrganizationPlan.downloadZip(selectedRow).then(function(d) {
						data = d.plain();
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export!"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
	        	}
			
		};
		$scope.$on("ChangeStatus", function(event, result){ 
			   if(result){
				   exportContentOrgan();
			   }
			  });
		// Load combobox
		vm.resultMonitoring = {
			    constructId: '',
			    contractCode  : vm.item.contractCode,
			  };
		vm.resultCEO = {
			    constructId: '',
			    contractCode  : vm.item.contractCode,
			  };
			
	            vm.resultMonitoring.roleid = Constant.ROLE_ID["employee_roleID_CDT_DDPN"];
	            vm.resultCEO.roleid = Constant.ROLE_ID["employee_roleID_DT_GDNT"];
		
		vm.resultMonitoring.constructId= vm.item.constructId;
		vm.resultCEO.constructId= vm.item.constructId;
		
		 getResultMonitoring(vm.resultMonitoring);
		 getResultMonitoring1(vm.resultCEO);
		 
//		 angular.element(document).ready(function () {
		  function getResultMonitoring(object) {
			  listWorkOrganizationPlan.getListEmployeeByRole(object).then(function(data) {
				  vm.monitoring = data.plain();
				   if(vm.monitoring.length == 0){
				    	return;
				    }
				  vm.constrOrgPlan.representInvestorId = vm.monitoring[0].id;
		     });
		  }
		  
		  function getResultMonitoring1(object) {
			  listWorkOrganizationPlan.getListEmployeeByRole(object).then(function(data) {
				  vm.monitoring1 = data.plain();
				  if(vm.monitoring1.length == 0){
				   return;
				  }
				  vm.constrOrgPlan.representContractorId = vm.monitoring1[0].id;
		     });
		  }
//		 });
		// End Load combobox
		// creat
			vm.creatWorkOrganizationPlan = function() {
				var count =0;
				list =[];
				list1 =[];
				if(!$rootScope.isDisabled){
					return;
				}
				if (!vm.validator.validate()) {return};
				if(vm.constrOrgPlan.statusCa==1||vm.constrOrgPlan.statusCa==2){
					toastr.warning("Không được sửa những bản ghi đang trình ký hoặc đã ký");
					return;
				}
				var filename = $($('input[name="uploadfilecreate"]')[0]).val().split('\\').pop();
				if(vm.constrOrgPlan.creatOrUpdate == "creat"){
					if(filename==""|| filename == undefined){
						toastr.warning("File đính kèm đang trống");
						return;
					}
					var representInvestorId	= vm.constrOrgPlan.representInvestorId;
					var representContractorId = vm.constrOrgPlan.representContractorId;
					vm.constrOrgPlan={};
					vm.constrOrgPlan.creatOrUpdate = "creat";
					vm.constrOrgPlan.createdUserId = Constant.user.srvUser.userId;
					vm.constrOrgPlan.representInvestorId= representInvestorId;
					vm.constrOrgPlan.representContractorId = representContractorId;
					
					vm.constrOrgPlan.constructId = vm.item.constructId;
	    			if($('input[name="multiuploadfile"]')[0].files.length ==0){
	    				vm.uploadFile('upload_file_create').done(function(result){
		    				if(result !="FileNameNull"){
		    					list.push(result[0]);
		    					list1.push($('input[name="uploadfilecreate"]')[0].files[0].name);
		    					count++;
		    				}
		    				runWhenDone(count,list.length);
		
	    				});
	    			}else{
	    			vm.uploadFile('upload_file_create').done(function(result){
	    				if(result !="FileNameNull"){
	    					list.push(result[0]);
	    				}
	    				vm.uploadFile('multi_upload_file').then(function(listThread){
	    					if(listThread.length == 0){
	    						vm.asss();
	    						return;
	    					} 					
	    					for(var i=0;i<listThread.length;i++){
	    						if(i==0){
	    						list1.push($('input[name="uploadfilecreate"]')[0].files[0].name);
	    						}
								list1.push($('input[name="multiuploadfile"]')[0].files[i].name);
								
	    						listThread[i].done(function(result){
	    							list.push(result[0]);
	    							count ++;							 							
	    							runWhenDone(count,listThread.length);
	    						}).fail(function(error){
	    							count ++;
	    							runWhenDone(count);
	    						});
	    					}
	    				});
	    			});
	    			}
				}
    			if(vm.constrOrgPlan.creatOrUpdate == "update"){
    				vm.constrOrgPlan.constructId = vm.item.constructId;
    				if(vm.constrOrgPlan.createdUserId != Constant.user.srvUser.userId){
        				toastr.warning(gettextCatalog.getString("Bạn không có quyền sửa bản ghi nhân viên khác tạo!"));
        				return;
        			}
    				if(filename==""|| filename == undefined){
    					list1.push(vm.constrOrgPlan.documentName);
    					list.push(vm.constrOrgPlan.documentPath);
    					vm.uploadFile('multi_upload_file').then(function(listThread){
	    					if(listThread.length == 0){
	    						vm.asss();
	    						return;
	    					} 					
	    					for(var i=0;i<listThread.length;i++){
								list1.push($('input[name="multiuploadfile"]')[0].files[i].name);
								
	    						listThread[i].done(function(result){
	    							list.push(result[0]);
	    							count ++;							 							
	    							runWhenDone(count,listThread.length);
	    						}).fail(function(error){
	    							count ++;
	    							runWhenDone(count);
	    						});
	    					}
	    				});
					}else{
						vm.uploadFile('upload_file_create').done(function(result){
		    				if(result !="FileNameNull"){
		    					list.push(result[0]);
		    				}
		    				vm.uploadFile('multi_upload_file').then(function(listThread){
		    					if(listThread.length == 0){
		    						vm.asss();
		    						return;
		    					} 					
		    					for(var i=0;i<listThread.length;i++){
		    						if(i==0){
		    						list1.push($('input[name="uploadfilecreate"]')[0].files[0].name);
		    						}
									list1.push($('input[name="multiuploadfile"]')[0].files[i].name);
									
		    						listThread[i].done(function(result){
		    							list.push(result[0]);
		    							count ++;							 							
		    							runWhenDone(count,listThread.length);
		    						}).fail(function(error){
		    							count ++;
		    							runWhenDone(count);
		    						});
		    					}
		    				});
		    			});
					}
    			}
			};
			
			function runWhenDone(count,threadLength){
				if(count==threadLength){
					vm.asss();
				}
			};
			vm.asss = function(){
				/*for(var i=0;i<list.length;i++){
					if('pdf' != list[i].split('.').pop()){
						toastr.warning("File import đính kèm phải là file PDF");
						vm.files = [];
						$("#upload_file_create" ).val("");
						$("#multi_upload_file" ).val("");
						vm.files.splice(0, vm.files.length);
						list.splice(0,list.length);
						return;
					}
				}*/
				vm.constrOrgPlan.listDocumentName = list;
				vm.constrOrgPlan.listDocumentNameEx = list1;
				listWorkOrganizationPlan.addConstrOrganizationPlan(vm.constrOrgPlan).then(function(d) {
					if(vm.constrOrgPlan.creatOrUpdate == "creat"){
						toastr.success("Thêm mới thành công");
						vm.constrOrgPlan.creatOrUpdate = "creat";
						$rootScope.isDisabled = false;
						$rootScope.showList= true;
						$rootScope.showListFile= false;
// flag = true;
						vm.constrOrgPlan ={};
						vm.constrOrgPlan.creatOrUpdate = "creat";
						vm.constrOrgPlan.constructId = vm.item.constructId;
						vm.constrOrgPlan.contractId = vm.item.contractId;
						vm.constrOrgPlan.representInvestorId = vm.monitoring[0].id;
						vm.constrOrgPlan.representContractorId = vm.monitoring1[0].id;
						vm.constrOrgPlan.constrOrgPlanId = d;
					}
					if(vm.constrOrgPlan.creatOrUpdate == "update"){
						toastr.success("Cập nhật thành công");
					}
					vm.theSign={};
					flag = false;
					vm.files.splice(0, vm.files.length);
					list.splice(0,list.length);
					$( "#upload_file_create" ).val("");
					$( "#multi_upload_file" ).val("");
					exportContentFile(vm.constrOrgPlan.constrOrgPlanId);
					exportContentOrgan();
				});
			};
			
			vm.signCA = function() {
				if(vm.constrOrgPlan.creatOrUpdate!="creat"){
					if(vm.theSign.constrCompReMapId==''){
						toastr.warning("Bạn chưa chọn bản ghi nào");
						return ;
					}
					if(vm.constrOrgPlan.statusCa != 0){
						toastr.warning("Bản ghi đã trình ký");
						return ;
					}
					goTo('THE_SIGN_CA');
				}
				
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
				} else {
					// toastr.error(gettextCatalog.getString("Tài khoản đăng));
				}
			}						
			
			vm.mulDeleteFile =mulDeleteFile;
			function mulDeleteFile(){
				if(vm.constrOrgPlan.statusCa==1||vm.constrOrgPlan.statusCa==2){
					toastr.warning("Không được xóa file đang trình ký hoặc đã ký");
					return;
				}
				var flag = false;
				var selectedRowFile = [];
				var grid = vm.gridViewFile;
				var listCreatUserId = [];
				grid.table.find("tr").each(function(idx, item) {
					var row = $(item);
					var checkbox = $('[name="gridcheckbox"]', row);
					if (checkbox.is(':checked')) {
						// Push id into selectedRow
						var dataItem = grid.dataItem(item);
						listCreatUserId.push(vm.constrOrgPlan.createdUserId);
						selectedRowFile.push(dataItem.attachId);
					}
				});
				if (selectedRowFile.length == 0) {
					toastr.warning("Bạn chưa chọn bản ghi nào");
					return;
				}
				for(var i = 0;i<listCreatUserId.length;i++){
					if(listCreatUserId[i] != Constant.user.srvUser.userId){
						selectedRowFile.splice(i,1);
						flag = true;
	    			}
				}
				if (flag && selectedRowFile.length == 0) {
					toastr.warning("Bạn không có quyền xóa bản ghi nhân viên khác tạo!");
					return;
				}
				if(vm.constrOrgPlan.createdUserId == Constant.user.srvUser.userId){
				if (confirm('Bạn có muốn xóa File không')) {	
					listWorkOrganizationPlan.deleteDocument(selectedRowFile).then(function() {
						toastr.success("Xóa File thành công");
						exportContentFile(vm.constrOrgPlan.constrOrgPlanId);
					}, function(errResponse) {
				         if (errResponse.status == 302){
				             toastr.error("Bản ghi đang được sử dụng");
				            }else{
				             toastr.error("Bản ghi không được xóa");
				         }
			         });
				}}else{
					toastr.warning("Bạn không có quyền xóa bản ghi nhân viên khác tạo!");
					return;
				}
			};
			
			function getFolder() {
				listWorkOrganizationPlan.getConstrOrganizationFolder().then(function(data) {
					vm.folder = data.folder;
				  });
			}
			getFolder();
			
			vm.uploadFile = function(id) {
				if(id=='upload_file_create'){
					var formData = new FormData();
					formData.append('tax_file', $('#'+ id)[0].files[0]); 
			     return   $.ajax({
			            url: Constant.BASE_SERVICE_URL+"fileservice/uploadATTT?folder="+ vm.folder,
			            type: "POST",
			            data: formData,
			            enctype: 'multipart/form-data',
			            processData: false,
			            contentType: false,
			            cache: false
			        });
				}
				if(id=='multi_upload_file'){
					var deferred = $q.defer();
					var listThread = [];
					for(var i = 0 ; i<vm.files.length;i++){
						var formData = new FormData();
						formData.append('tax_file', $('#'+ id)[0].files[i]); 
				     var thread =   $.ajax({
				            url: Constant.BASE_SERVICE_URL+"fileservice/uploadATTT?folder="+ vm.folder,
				            type: "POST",
				            data: formData,
				            enctype: 'multipart/form-data',
				            processData: false,
				            contentType: false,
				            cache: false
				           
				        });
				        listThread.push(thread);
					}
					deferred.resolve(listThread);
					return deferred.promise;
				}
		    }; // function uploadFile
	}
	
	angular.module('MetronicApp').directive('ngFileModel', ['$parse', function ($parse) {
	    return {
	        restrict: 'A',
	        link: function (scope, element, attrs) {
	            var model = $parse(attrs.ngFileModel);
	            var isMultiple = attrs.multiple;
	            var modelSetter = model.assign;
	            element.bind('change', function () {
	                var values = [];
	                angular.forEach(element[0].files, function (item) {
	                    var value = {
	                       // File Name
	                        name: item.name,
	                        // File Size
	                        size: item.size,
	                        // File URL to view
	                        url: URL.createObjectURL(item),
	                        // File Input Value
	                        _file: item
	                    };
	                    values.push(value);
	                });
	                scope.$apply(function () {
	                    if (isMultiple) {
	                        modelSetter(scope, values);
	                    } else {
	                        modelSetter(scope, values[0]);
	                    }
	                });
	            });
	        }
	    };
	}]);
	
	
})();