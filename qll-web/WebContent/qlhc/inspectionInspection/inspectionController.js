(function() {
	'use strict';
	var controllerId = 'ConstructionAcceptanceController';
	angular.module('MetronicApp').controller(controllerId,
			ConstructionAcceptanceController);

	function ConstructionAcceptanceController($scope, $rootScope, $timeout, Constant,
			gettextCatalog, kendoConfig, $kWindow, WindowService,
			CommonService, PopupConst, inspectionService,ProposalEvaluation, Restangular,
			RestEndpoint) {
		var vm = this;
		fillDataTableWorkItem([]);
		fillDataTable2([]);
		fillDataTable3([]);
		fillDataTable4([]);

		vm.goToDsNtht = goToDsNtht;

		function goToDsNtht() {
			inspectionService.goTo_DSNTHT();
		}
		
		
		var ArrEmployee = [];
		
		vm.item = {
		 amonitorId : 0,
		 amonitorName :'',
	     ainChargeMonitorId : 0,
	     ainChargeMonitorName :'',
	     bdirectorId : 0,
	     bdirectorName : '',
	     binChargeConstructId : 0,
	     binChargeConstructName : '',
	     cdesignDirectionId : 0,
	     cdesignDirectionName : '',
	     cdesignManagerId : 0,
	     cdesignManagerName : '',
	     acceptFromDate :'',
	     acceptToDate :'',
	     acceptPlace :'',
	     applyBenchmark :'',
	     otherDocuments :'',
	     constructionQuality :'',
	     otherComments :'',
	     conclusion :0,
	     completeRequest :'',
	     statusCa :'0',
	     constracceptworklist: []	
			
		};

		vm.employee = {
            constructId: 69164445,
			roleid :Constant.ROLE_ID["employee_roleID_DT_KTTC"]
		} 
		

       var workitemarr = [];
		// Hien thi popup tim kiem
		vm.popupTenCT = popupTenCT;
		function popupTenCT() {
			var dataConfig = {
				selected : function(data) {
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
	        	needShowDetail : gettextCatalog.getString("Cần hiển thị ở chế độ Chi Tiết!"),
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
			 vm.chkSelectAll = chkSelectAll;
			 vm.multiDelete = multiDelete;
			// vm.candoSearch = candoSearch;
		
		
		
		
		
		
		
		
		
		
		
		
		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {						
				
			vm.item.constructId = ProposalEvaluation.getItem().constructId;
			vm.item.constrtCode = ProposalEvaluation.getItem().constrtCode;
			vm.item.constrtName = ProposalEvaluation.getItem().constrtName;
			vm.item.contractCode = ProposalEvaluation.getItem().contractCode;
			vm.item.contractName = ProposalEvaluation.getItem().contractName;
			vm.item.constrtAddress = ProposalEvaluation.getItem().constrtAddress;
			vm.item.constrtAddress = ProposalEvaluation.getItem().stationCode;
			
			
			
			inspectionService.getConstructionacceptance(vm.item.constructId).then(function (d){
				
				fillDataTableMain(d.plain());
				// refreshGrid(d.plain(),vm.MainGrid);
			},function (e){
				console.log("Erro");
			});
			
			
			getListEmployee(vm.item.constructId);
			
			
			inspectionService.getCompletionDrawing(vm.item.constructId).then(function (d){ 
				  vm.typeId = d.plain();
		    }, function(errResponse){ 
		          console.error('Error  while fetching locatorbank'); });
			
			
			//Khoi luong vat tu do A cap
			inspectionService.getAmaterialhandoverforcontruction(vm.item.constructId).then(function(d) {
				fillDataTable2(d.plain());
				refreshGrid(d.plain(),vm.AmaterialGrid);
				
			}, function(errResponse) {
				console.error('Error while fetching crevaluation');
			});			
		           
				 
			
			
			
			
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
			vm.showDetail = true;
			vm.showSearch = false;
			vm.isCreateNew = true;
			
			
			
			//
		     vm.item.acceptFromDate ='06/12/2016 00:00:00';
		     vm.item.acceptToDate ='06/12/2016 00:00:00',
		     vm.item.acceptPlace ='';
		     vm.item.applyBenchmark ='';
		     vm.item.otherDocuments ='';
		     vm.item.constructionQuality ='';
		     vm.item.otherComments ='';
		     vm.item.conclusion =0;
		     vm.item.completeRequest ='';
		     fetchWorkItem();
			
		     vm.Contract = false;

		}
		
		
		
		function getListEmployee(param1){
			
			
			var Monitor = [],
			InChargeMonitor = [],
			Director = [],
			InChargeContruction = [],
			DesignDirection = [],
			DesignManager = [];
			
			inspectionService.getListEmployeeByConstruction(param1).then(function (d){
				ArrEmployee = d.plain();
				for (var i = 0;i < d.plain().length;i++){
					
					if(d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){
						Monitor.push(d.plain()[i])
					}
					if(d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_PTGST"]){
						InChargeMonitor.push(d.plain()[i])
					}
					if(d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]){
						Director.push(d.plain()[i])
					}
					if(d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_DT_PTTC"]){
						InChargeContruction.push(d.plain()[i])
					}
					if(d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_DDTV"]){
						DesignDirection.push(d.plain()[i])
					}
					if(d.plain()[i].roleid == Constant.ROLE_ID["employee_roleID_TVTK_CNTK"]){
						DesignManager.push(d.plain()[i])
					}
				}
				
		

				
				
				
				vm.Monitor = Monitor;
				vm.InChargeMonitor = InChargeMonitor;			
				vm.Director = Director;						
				vm.InChargeContruction = InChargeContruction;			
				vm.DesignDirection = DesignDirection;			
				vm.DesignManager = DesignManager;
				
				
				
				if(vm.Monitor.length > 0) {
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
				
				
				
				
				
				
				
            }, function(errResponse){
				console.error('Error while fetching locatorbank');
            });	
			
			console.log(vm.item.aMonitorId);
		}
		
		
		function fetchWorkItem() {
			inspectionService.getWorkItem(vm.item.constructId).then(function(d) {			
				//fillDataTable(d.plain());
				refreshGrid(d.plain(), vm.WorkItemGrid);
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
		
		
		vm.save = save;

		function save() {
			// console.log(vm.item.aMonitorId);

			
			
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
			
			

			

			 
			 
			 
			    vm.item.statusCa = 0;
			 
	        	if(vm.showDetail){
	                if(vm.validator.validate()){

	                	if(vm.isCreateNew) {
	                	
	            			var data = vm.WorkItemGrid.dataSource.data();
	            			
	            			for(var i = 0;i<data.length;i++){
	            				data[i].defaultSortField = '';
	            				data[i].uid = '';
	            			}
	            		    vm.Contract = true;
	            			vm.item.constracceptworklist = data;
	            	        inspectionService.saveConstructionacceptance(vm.item).then(function(result){
	            	        	toastr.success(message.saveSuccess);
	                		}, function(errResponse){
	            				
	            					toastr.error(gettextCatalog.getString("Lỗi thêm mới"));
	            				
	            	        });
	            			

	            			 refreshGrid(data,vm.WorkItemGrid);
	                	} else {
	                		
	            			var data = vm.WorkItemGrid.dataSource.data();
	            			
	            			for(var i = 0;i<data.length;i++){
	            				data[i].defaultSortField = '';
	            				data[i].uid = '';
	            			}
	            			
	            			vm.item.constracceptworklist = data;
	            	        inspectionService.updateConstructionacceptance(vm.item).then(function(result){
	            	        	toastr.success(message.saveSuccess);
	                		}, function(errResponse){
	            				
	            					toastr.error(gettextCatalog.getString("Lỗi cập nhật"));
	            				
	            	        });
	            			

	            			 refreshGrid(data,vm.WorkItemGrid);
	                	}
	                }
	            }
			 

		}
		
		function remove(){
        	var grid = vm.MainGrid;
        	// Check select
        	if (grid.select() == null || grid.select().length == 0) {
        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
        		return;
        	}
        	var tr = grid.select().closest("tr");
        	var dataItem = grid.dataItem(tr);
        	
        	if(vm.MainGrid.select().length > 0 && confirm('Xác nhận xóa')){
        		inspectionService.deleteConstructionAcceptance(dataItem.constructionAcceptanceId).then(function(result){
        			reloadData();
            		toastr.success(gettextCatalog.getString("Xóa thành công!"));
            	}, function(errResponse){
            		if (errResponse.status == 409) {
	                	toastr.error(gettextCatalog.getString("Không thể xóa hàng hóa đang sử dụng"));
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
			    	 if (errResponse.status == 302){
			    		 toastr.error("Bản ghi đang được sử dụng");
			    	 }else{
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
        function refreshGrid(d , grid) {
        	grid.dataSource.data(d);
        	grid.refresh();
        }
        
      	// Bảng diễn giải khối lượng xây lắp hoàn thành
		function fillDataTableWorkItem(data) {
			

			vm.WorkItemGridOptions = kendoConfig
					.getGridOptions({
						
						
					
		                autoBind: true,
		                dataSource: data,
		                change: onChange,
		                noRecords: true,
		                showHeader: false,
		                detailInit: detailInit,
		                messages: {
		                    noRecords: gettextCatalog.getString("Không có kết quả nào")
		                },
						columns : [
								{
									field: "rowNumber",
						            title: "STT",
						            template: dataItem => $("#WorkItemGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						            width : 30
								},
								{
									title : gettextCatalog
											.getString("Mã hiệu đơn giá"),
									field : "workItemCode",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Nội dung công việc"),
									// template : "",
									field : "workItemName",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Đơn vị tính"),
									field : "unit",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Diễn giải"),
									field : "explain",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Khối lượng"),
									field : "executeQuantity",
									width : 120
								},
								{
					
									
									field: "instanceDrawCode", title: "Bản vẽ",
									width: "180px", 
									editor: categoryDropDownEditor, 
									// template: "#=instanceDrawCode.drawName#"
									
									
								},
								
								
								{
									title : gettextCatalog.getString("Ghi chú"),
									field : "comments",
									width : 120
								} ]
					});
		}

		
		function detailInit(e) {
			childgrid = $("<div/>").appendTo(e.detailCell).kendoGrid({
			    dataSource: workitemarr,

			    scrollable: false,
			    sortable: false,
			    selectable: true,
			    pageable: true,
				columns : [
							{
								field: "rowNumber",
					            title: "STT",
					            template: dataItem => $("#WorkItemGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					            width : 30
							},
							{
								title : gettextCatalog
										.getString("Mã hiệu đơn giá"),
								field : "workItemCode",
								width : 120
							},
							{
								title : gettextCatalog
										.getString("Nội dung công việc"),
								// template : "",
								field : "workItemName",
								width : 120
							},
							{
								title : gettextCatalog
										.getString("Đơn vị tính"),
								field : "unit",
								width : 120
							},
							{
								title : gettextCatalog
										.getString("Diễn giải"),
								field : "explain",
								width : 120
							},
							{
								title : gettextCatalog
										.getString("Khối lượng"),
								field : "executeQuantity",
								width : 120
							},
							{
				
								
								field: "instanceDrawCode", title: "Bản vẽ",
								width: "180px", 
								editor: categoryDropDownEditor, 
								// template: "#=instanceDrawCode.drawName#"
								
								
							},
							
							
							{
								title : gettextCatalog.getString("Ghi chú"),
								field : "comments",
								width : 120
							} ]
			}).data("kendoGrid");
			}
		
		
        function categoryDropDownEditor(container, options) {
            $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    dataTextField: "drawCode",
                    dataValueField: "drawCode",
                    dataSource: vm.typeId

                });
        }
		

		
		
		
		// Khối lượng VTTB do A cấp
		function fillDataTable2(data) {
			vm.gridOptions2 = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						// change : onChange,
						detailExpand : function(e) {
							var tr = e.masterRow;
							var data = vm.shipmentGrid.dataItem(tr);
							vm.shipmentDetail = data;
							ShipmentDetailService
									.setShipmentDetail(vm.shipmentDetail);
							$rootScope.$broadcast("inv.shipment.detail.reload",
									vm.shipmentDetail.minoutId);
						},
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
								{
									   field: "rowNumber",
							            title: "STT",
							            template: dataItem => $("#AmaterialGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							            width : 30
								},
								{
									title : gettextCatalog
											.getString("Nội dung"),
									field : "contentname",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Đơn vị tính"),
									field : "unitname",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Khối lượng theo TK"),
									field : "quantity",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Khối lượng thực tế"),
									field : "actualReceiveQuantity",
									width : 120
								}]
					});
		}
		// Khối lượng xây lắp do nhà thầu thi công
		function fillDataTable3(data) {
			vm.gridOptions3 = kendoConfig
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
									 field: "rowNumber",
							            title: "STT",
							            template: dataItem => $("#IContractGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							            width : 30
								},
								{
									title : gettextCatalog
											.getString("Nội dung"),
									field : "workItemName",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Đơn vị tính"),
									field : "unit",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Khối lượng theo TK"),
									field : "workAmount",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Khối lượng thực tế"),
									field : "executeQuantity",
									width : 120
								},
								{
									title : gettextCatalog
											.getString("Chênh lệch"),
									field : "deviation",
									width : 120
								},
								{
									title : gettextCatalog.getString("Ghi chú"),
									field : "comments",
									width : 120
								} ]
					});
		}
		// Các công việc phát sinh ngoài hợp đồng
		function fillDataTable4(data) {
			vm.gridOptions4 = kendoConfig
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
										 field: "rowNumber",
								            title: "STT",
								            template: dataItem => $("#OContractGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								            width : 30
									},
									{
										title : gettextCatalog
												.getString("Nội dung"),
										field : "workItemName",
										width : 120
									},
									{
										title : gettextCatalog
												.getString("Đơn vị tính"),
										field : "unit",
										width : 120
									},
									{
										title : gettextCatalog
												.getString("Khối lượng theo TK"),
										field : "workAmount",
										width : 120
									},
									{
										title : gettextCatalog
												.getString("Khối lượng thực tế"),
										field : "executeQuantity",
										width : 120
									},
									{
										title : gettextCatalog
												.getString("Chênh lệch"),
										field : "deviation",
										width : 120
									},
									{
										title : gettextCatalog.getString("Ghi chú"),
										field : "comments",
										width : 120
									} ]
					});
		}
		
		
		
		
		
		vm.exportFile = function() {
			/*
			 * createMemoryWork.exportFileConstrWorkLogs(vm.criteria).then(function() {
			 * toastr.success("Export File thành công"); },
			 * function(errResponse) { // console.error('Error while fetching
			 * minout'); });
			 */

			for(var i = 0;i<ArrEmployee.length;i++){
				if(vm.item.amonitorId == ArrEmployee[i].userId){
					vm.item.amonitorName = ArrEmployee[i].fullName
				}if(vm.item.ainChargeMonitorId  == ArrEmployee[i].userId){
					vm.item.ainChargeMonitorName = ArrEmployee[i].fullName
				} if(vm.item.bdirectorId  == ArrEmployee[i].userId){
					vm.item.bdirectorName = ArrEmployee[i].fullName
				} if(vm.item.binChargeConstructId  == ArrEmployee[i].userId){
					vm.item.binChargeConstructName = ArrEmployee[i].fullName
				} if(vm.item.cdesignDirectionId  == ArrEmployee[i].userId){
					vm.item.cdesignDirectionName = ArrEmployee[i].fullName
				} if(vm.item.cdesignManagerId  == ArrEmployee[i].userId){
					vm.item.cdesignManagerName = ArrEmployee[i].fullName
				}
			}
			
			vm.item.conclusionnotaccept = '';
			vm.item.conclusionaccept = '';
			
			inspectionService.exportFile(vm.item).then(function(data){
        	 window.location = "/qlhc-service/fileservice/download?fileName=" + data.fileName;
        	});
		}
		
		

		
		
		
		
		
		// Bảng danh sách nghiệm thu hoàn thành công trình
		function fillDataTableMain(data) {
			
			vm.MainGridOptions = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						change: onChange,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
								{
					            field: "rowNumber",
					            title: "STT",
					            template: dataItem => $("#mainGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					            width : 20
						        },
								{
									title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
									template : "<input type='checkbox' name='gridcheckbox' />",
									width : 20
								},
								{
									title : gettextCatalog.getString("Mã Biên bản"),
									field : "code",
									width : 100
								},
								{
									title : gettextCatalog.getString("Mã công trình"),
									field :"constrtCode",
									width : 100
								},
								{
									title : gettextCatalog.getString("Mã hợp đồng"),
									field : "contractCode",
									width : 150
								},
								{
									title : gettextCatalog.getString("Tên hợp đồng"),
									field : "contractName",
									width : 150
								},
								{
									title : gettextCatalog.getString("Trạng thái ký CA"),
									field : "statusCa",
									 attributes: { style: "text-align:left;", class:"statusColumn" },
									template: function($scope){
									      if($scope.statusCa == 0)
									      {
									       return "Soạn Thảo";
									      }
									      if($scope.statusCa == 1)
									      {
									    	  return "Trình Duyệt";
									      }
									      if($scope.statusCa == 2)
									      {
									    	  return "Đã Duyệt";
									      }
									      if($scope.statusCa == 3)
									      {
									    	  return "Từ chối Duyệt";
									      }
									      },
									width : 100
								}
								]
					});
		}
		
		
		
		
        function showGrid() {

        	
        	$(".k-invalid-msg").hide();

			if (vm.showDetail == false) {
				vm.isCreateNew = false;
				vm.Contract = true;
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

            	if(vm.WorkItemGrid.select().length == 0){
   
            	workitemarr = dataItem.constracceptworklist;	
            		
            	var array  = [{workItemCode : "Nội dung công việc ngoài hợp đồng",workItemName : "", unit :"" ,explain : "" , executeQuantity : "" , instanceDrawCode :"" , comments: ""},{workItemCode : "Nội dung công việc ngoài hợp đồng",workItemName : "", unit :"" ,explain : "" , executeQuantity : "" , instanceDrawCode :"" , comments: ""}]
            	
            	
            	refreshGrid(array, vm.WorkItemGrid);
            	IContract = [];
            	OContract = [];
                for(var i = 0;i <array.length;i++){
					
					if(array[i].type == 1){
						IContract.push(array[i]);
					}else if(array[i].type == 2){
						OContract.push(array[i]);
					}
				}
            	
				refreshGrid(IContract, vm.IContractGrid);
				refreshGrid(OContract, vm.OContractGrid);
            	
            	
            	}
            }
        }
		
		
        function reloadData(){
	         inspectionService.getConstructionacceptance(vm.item.constructId).then(function (d){		
				refreshGrid(d.plain(),vm.MainGrid);
			},function (e){
				console.log("Erro");
			});
			
        }
		
		
		
		
		
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
        	if(item != undefined){
        		
    			vm.item.constructId = item.constructId;
    			vm.item.constrtCode = item.constrtCode;
    			vm.item.constrtName = item.constrtName;
    			vm.item.contractCode = item.contractCode;
    			vm.item.contractName = item.contractName;
    			vm.item.constrtAddress = item.constrtAddress;   			
    			vm.item.stationCode = item.stationCode;
    			
    			reloadData();
        	}else{
        		console.error("không nhận được dữ liệu!");
        	}
        });
		
	}
})();