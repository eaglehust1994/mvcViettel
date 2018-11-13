(function () {
	'use strict';
	var controllerId = 'acceptanceBListController';

	angular.module('MetronicApp').controller(controllerId,
		acceptanceBListController);

	function acceptanceBListController($scope, $rootScope, $timeout, Constant,
		kendoConfig, $kWindow,WindowService, CommonService, gettextCatalog, Restangular,
		PopupConst, $q, RestEndpoint, ProposalEvaluation, acceptanceBListService, theApproval) {

		var vm = this;
		
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.disableAll = false;
		vm.addAll = addAll;
		vm.addRow = addRow;
		vm.onSave = onSave;
		vm.showPopup = showPopup; 
		vm.removeBMA = removeBMA;
		vm.removeRow = removeRow;
		vm.goTo = goTo;
		vm.goToUpdate = goToUpdate;
		vm.goToAdd = goToAdd;
		vm.show = show;
		vm.objectDTO = [];
		var checkonchange = 0;
		vm.objRemove=[];
		vm.trinhDuyet = sendBrowse;
		vm.exportFile = exportFile;
		vm.hide = hide;
		vm.update = update;
		vm.exportPDF = exportPDF;
		vm.exportDOC = exportDOC;
		vm.importBmaterial = importBmaterial;
		vm.isCreateNew = false;
		vm.showSearch = false;
		vm.hienthi = false;
		vm.appro = false;
		vm.pheduyet = pheduyet;
		vm.btnPheDuyet = false;
		vm.btnTrinhduyet = false;
		vm.btnAddAll = false;
		
		
		
		// thông tin chung
		vm.item = {
			partnerName: '',
			contractCode: '',         // Số hợp đồng
			investProjectName: '',    // Tên hợp đồng
			constrtCode: '',          // Mã công trình
			constrtName: '',			 // Tên công trình
			constrType: '',
			provinceId: '',
			constrtAddress: '',		 // Địa chỉ công trình
			constructId: '',
			bMaterialAcceptanceId: '',
			signPlace: '',
			acceptanceBase: '',
		}
		
		// lay id thong tin chung cua cong trinh
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		console.log(vm.item);
		// ////////////////////////Combobox thanh phan tham gia//////////////////////////////////
		vm.role=[];
		var totalListEmployee = [], technical = [], monitoring = [];
		vm.technical = [];
		vm.monitoring = [];
		
		angular.element(document).ready(function () {
			acceptanceBListService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
				totalListEmployee = data.plain();
//					console.log(data);
//					vm.role = data;
					for (var i = 0; i < totalListEmployee.length; i++) {
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_GSTC"]){
							monitoring.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_KTTC"]){
							technical.push(totalListEmployee[i]);
						}
					}
					vm.monitoring = monitoring;
					vm.technical = technical;
					
					if(monitoring.length > 0) {
						vm.itemID.amonitorId = monitoring[0].userId;
					}
					if(technical.length > 0) {
						vm.itemID.binChargeConstructId = technical[0].userId;
					}
					
			  })
			  
			  .catch(function() {
			    console.error('Gists error');
			});
		});
		
		// ////////////////////////END//////////////////////////////////
		// thông tin trình duyệt
		vm.theApproval = {
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
		vm.approData = {
			"employeeId": "",
			"comments": "",
//			"bMaterialAcceptanceId": "",
			"constrCompReMapId": ""
		}
		refreshGrid();
		// trinh duyet
		
		function sendBrowse() {
			if (vm.itemID.statusCa == 0) {
				$('#loading').show();
				acceptanceBListService.exportFilePDFDetail(vm.itemID).then(function (data) {
						vm.theApproval.path = data.fileName;
						vm.theApproval.nameFile = 'BM-TCNT-07.pdf';
						theApproval.setItem(vm.theApproval);
						goTo('THE_APPROVAL');
						refreshGrid();
						loadDataTable();
						vm.hienthi = false;
				}).catch( function(){
					$('#loading').hide();
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
			} else {
				toastr.warning("Trạng thái duyệt khác soạn thảo - Không thể trình duyệt!");
			}
		}
		
		//ngoccx
	      //huy trinh duyet
			vm.cancelApprovalBtn= function(){
				var grid = vm.inoutGrid;
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
					return;
				}
				vm.itemID.tableName = 'B_MATERIAL_ACCEPTANCE';
				vm.itemID.tableId = vm.itemID.bmaterialAcceptanceId;
				vm.itemID.tableIdField = 'B_MATERIAL_ACCEPTANCE_ID';
				if(vm.itemID.statusCa == 1){
					if(vm.itemID.createdUserId != Constant.user.srvUser.userId){
						toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
						return;
					}else{
						if(confirm('Xác nhận hủy trình duyệt')){
						acceptanceBListService.cancelAprroval(vm.itemID).then(function() {
							status = true;
							$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
						loadDataTable();
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
			}
		}
		//		    vm.approData.employeeId =  vm.item.catEmployeeId;
		vm.approData.employeeId = Constant.user.srvUser.catEmployeeId

		vm.itemID = {
			bmaterialAcceptanceId: '',
			aMonitorId: '',
			bInChargeConstructId: '',
			isActive: '',
			code: ''
		}
		vm.template = {
			constructId: '',
			bmaterialAcceptanceId: '',
			amonitorId: '',
			binChargeConstructId: '',
			isActive: '',
			code: '',
			contractCode: '',
			conclusion: '',
			contractQuantity: '',
			createdDate: '',
		};
		vm.template.constructId = vm.item.constructId;
		vm.template.contractId = vm.item.contractId;
			
		vm.doc = {
			contractCode: '',
			constrtCode: '',
			investProjectName: '',
			constrtName: '',
			constructorName: '',
			afullName: '',
			bfullName: '',
			listResultDTO: '',
			signDate: '',
			signPlace: '',
			acceptanceBase: '',
			constrtAddress: '',
			conclusion: '',

		}
		function nonEditor(container, options) {
			container.text(options.model[options.field]);
		}
		
		loadDataTable();
		refreshGrid();
		function pheduyet() {
			vm.isCreateNew = false;			
			loadDataTableDetail();
			refreshGridDetail();
			vm.appro = true;
			if (vm.itemID.statusCa == 1) {
				if (vm.hienthi == false) {
					var grid = vm.inoutGrid;
					// Check select
					if (grid.select() == null || grid.select().length == 0) {
						toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
						return;
					}
					vm.conclusion = [{ id: 0, value: 'Từ chối' },
					{ id: 1, value: 'Đồng ý' },];
					vm.pheduyet2 = [{ id: 2, value: 'Phê duyệt' },
					{ id: 3, value: 'Từ chối' },];

					vm.template.conclusion = vm.itemID.conclusion ? vm.itemID.conclusion : vm.conclusion[0].id;
					vm.approData.statusCa = 2;
					$('#loading').show();
					getDataRowSelect().then(function () {
						if (vm.inoutGrid.select().length > 0) {
							vm.hienthi = true;
						
							loadDataTableDetail();
						}
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi!"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
				} else {
					vm.hienthi = true;
				}
			} else {
				toastr.warning("Trạng thái trình duyệt mới được phê duyệt!");
			}
		}

		// tu dong load danh sach sau khi trinh duyet
		$scope.$on("ChangeStatusApproval", function (event, result) {
			if (result) {
				loadDataTable();
				refreshGrid();
			}
		});
		//
		$scope.$on("importBmaterial", function(event, item) {
            if (item === undefined) {
            	
            } else {
            	var dataItem = vm.inoutGridDetail.dataSource.data();
            	for(var i = 0 ; i <dataItem.length;i++){
            		item.push(dataItem[i]);         
            	}   
            	refreshGridDetail(item);        	
            	toastr.success("Import thành công");           	
            }
        });
		//btn Quay lai
		function hide() {
			vm.hienthi = false;
			vm.btnTrinhDuyet = false;
			vm.btnPheDuyet = false;
			loadDataTable();
			refreshGrid();
			refreshGridDetail();
		}
		

		//btn Xem bang/chi tiet
		function update() {
			vm.isCreateNew = false;
			vm.appro = false;
			if(vm.itemID.statusCa == 0){
				vm.disableAll = false;
			}else if(vm.itemID.statusCa == 1){
				vm.disableAll = true;
			}else if(vm.itemID.statusCa == 2){
				vm.disableAll = true;
			}else if(vm.itemID.statusCa == 3){
				vm.disableAll = false;
			}else{
				vm.disableAll = false;
			}
			
			if(vm.itemID.createdUserId != Constant.user.srvUser.userId){
				vm.disableAll = true;
			}
			
			
			if (vm.hienthi == false) {
				vm.btnPheDuyet = false;
				vm.btnTrinhDuyet = false;
				vm.btnAddAll = true;
				var grid = vm.inoutGrid;
				vm.approData.statusCa = 2;
				// Check select
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
					return;
				}
				vm.conclusion = [{ id: 0, value: 'Từ chối' },
				{ id: 1, value: 'Đồng ý' },];
				vm.template.conclusion = vm.itemID.conclusion ? vm.itemID.conclusion : vm.conclusion[0].id;
				$('#loading').show();
				getDataRowSelect().then(function () {
					if (vm.inoutGrid.select().length > 0) {
						vm.hienthi = true;
						refreshGridDetail();
						loadDataTableDetail();
					}
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi!"));
					return;
				}).finally(function(){
					$('#loading').hide();
				});
			} else {
				$(".k-invalid-msg").hide();
				vm.btnPheDuyet = false;
				vm.btnTrinhDuyet = false;
				vm.hienthi = false;
				refreshGridDetail();
				refreshGrid();
				
				
			}
		}
		//btn Them moi
		function show() {
			$(".k-invalid-msg").hide();
			console.log("vm.item.contractId"+vm.item.contractId),
			vm.appro = false;
			vm.isCreateNew = true;
			vm.btnTrinhDuyet = true;
			vm.btnPheDuyet = true;
			vm.disableAll = false;
			vm.btnAddAll = true;
			vm.conclusion = [{ id: 0, value: 'Từ chối' },
			{ id: 1, value: 'Đồng ý' },];
			vm.template.conclusion = vm.itemID.conclusion ? vm.itemID.conclusion : vm.conclusion[1].id;
			vm.itemID = {
				signPlace: '',
				signDate: '',
				acceptanceBase: '',
				bmaterialAcceptanceId: '',
				amonitorId: '',
				binChargeConstructId: '',
			}
			$( document ).ready(function() {
			acceptanceBListService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
				totalListEmployee = data.plain();
				acceptanceBListService.getRoleId().then(function(data) {
//					vm.role = data;
					for (var i = 0; i < totalListEmployee.length; i++) {
						if(totalListEmployee[i].roleid == Constrant.ROLE_ID["employee_roleID_CDT_GSTC"]){
							monitoring.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constrant.ROLE_ID["employee_roleID_DT_KTTC"]){
							technical.push(totalListEmployee[i]);
						}
					}
					
					vm.monitoring = monitoring;
					vm.technical = technical;
					
					if(monitoring.length > 0) {
						vm.itemID.amonitorId = monitoring[0].userId;
					}
					if(technical.length > 0) {
						vm.itemID.binChargeConstructId = technical[0].userId;
					}
					
				})
				.catch(function() {
					console.error('getRoleId error');
				});
			  })
			  .catch(function() {
			    console.error('Gists error');
			})
			});
			
			for (var i = 0; i < vm.monitoring.length; i++) {
				if(vm.monitoring[i].isCurrent === 1){
					vm.itemID.amonitorId = vm.monitoring[i].userId;
					break;
				} else {
					vm.itemID.amonitorId = vm.monitoring[0].userId;
					break;
				}
			}
			for (var i = 0; i < vm.technical.length; i++) {
				if(vm.technical[i].isCurrent === 1){
					vm.itemID.binChargeConstructId = vm.technical[i].userId;
					break;
				} else {
					vm.itemID.binChargeConstructId = vm.technical[0].userId;
					break;
				}
			}
			
			
			vm.hienthi = true;
			loadDataTableCreate();
		}
		
		function loadDataTableCreate() {
			acceptanceBListService.getAccpetMerList(0).then(function (d) {
				fillDataTableDetail(d.plain());
				refreshGridDetail(d.plain());
			}, function (e) {			
			});
		}

		// ////////////////Begin controller B Detail/////////////////////////////////
		// load du lieu bang con
		function loadDataTableDetail() {
			if (vm.itemID != null) {
				acceptanceBListService.getAccpetMerList(vm.itemID.bmaterialAcceptanceId).then(function (d) {
					fillDataTableDetail(d.plain());
					refreshGridDetail(d.plain());
//					initdata();
				}, function (e) {
				});
			} else if (vm.itemID === undefined) {
//				initdata();
				fillDataTableDetail([]);
				refreshGridDetail([]);
			}
		}

		function refreshGridDetail(d) {
			var grid = vm.inoutGridDetail;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		// du lieu bang con
		function fillDataTableDetail(data) {
			vm.gridOptionsDetail = kendoConfig
				.getGridOptions({
					autoBind: true,
					dataSource: data,// data.plain(),
					change: onChangeDetail,
					noRecords: true,
					messages: {
						noRecords: gettextCatalog
							.getString("Không có kết quả nào")
					},
					edit: function(e) {
				         e.container.find("input[name=materialName]").attr("maxlength", 500);
				         e.container.find("input[name=unit]").attr("maxlength", 200);
				         e.container.find("input[name=contractQuantity]").attr("maxlength", 34);
				         e.container.find("input[name=quantity]").attr("maxlength", 34);
				         e.container.find("input[name=specificationOrigin]").attr("maxlength", 500);
				         e.container.find("input[name=quality]").attr("maxlength", 200);
				     },
					columns: [
						{
							//field: "rowNumber",
							title: "<b style='margin:1px'>STT</b>",
							headerAttributes: { style: "text-align:center;" },
							editable: false,
							template: dataItem => $("#inoutGridDetail").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							attributes: { style: "text-align:center;" },
							width: 60
						},
						{
							title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
							template: "<input type='checkbox' ng-checked=\"vm.chkAll\" name='gridcheckbox' />",
							width: 25
						},
						{
							title: gettextCatalog.getString("Loại vật liệu"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "materialName",
							width: 100,
							type: "text",
						},
						{
							title: gettextCatalog.getString("Đơn vị tính"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "unit",
							width: 100,
							type: "text",
						},
						{
							title: gettextCatalog.getString("KL hợp đồng"),
							header: { style: "text-align:center;" },
							attributes: { style: "text-align:right;" },
							field: "contractQuantity",
							format: "{0:n3}",
							width: 150,
							template: function(dataItem) {
		                            if ($.isNumeric(dataItem.contractQuantity) && dataItem.contractQuantity >= 0) {
		                            	dataItem.contractQuantity = parseFloat(Number(dataItem.contractQuantity).toFixed(3));
		                                return parseFloat(Number(dataItem.contractQuantity).toFixed(3));
		                            }
		                            else{dataItem.contractQuantity = 0;
		                            return 0;}
		                        },
		                    decimals: 3,
							validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"},
							
						},
						{
							title: gettextCatalog.getString("KL nghiệm thu"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:right;" },
							field: "quantity",
							format: "{0:n3}",
							width: 150,
							 template: function(dataItem) {
		                            if ($.isNumeric(dataItem.quantity) && dataItem.quantity >= 0) {
		                            	dataItem.quantity = parseFloat(Number(dataItem.quantity).toFixed(3));
		                                return parseFloat(Number(dataItem.quantity).toFixed(3));
		                            }
		                            else {dataItem.quantity = 0;
		                            return 0;}
		                        },
		                    decimals: 3,
							validation: {  min: 0,max : 99999999, required: true , message :"Số quá lớn"}
						},
						{
							title: gettextCatalog.getString("Nguồn gốc"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "specificationOrigin",
							width: 150,
							type: "text",
						},
						{
							title: gettextCatalog.getString("Chất lượng"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "quality",
							width: 100,
							type: "text",
						},
					]
				});
		}
		
/*		$("#mainGridList").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");*/
		var listTable = [];
		// Save popup
		 function onSave(popupId, selectedRows) {
			if (PopupConst.AcceptanceBList['showPopup'] == popupId) {
				//get data into table
				var data = vm.inoutGridDetail.dataSource.data();
				var objectList = [];
				for (var i = 0; i < data.length; i++) {
					objectList.push(data[i]);
				}
				//get data into popup
				var listEstimatesDetailAnalyst = [];
				for (var i = 0; i < selectedRows.length; i++) {
					var dataPopup = selectedRows[i];
					dataPopup.materialName = selectedRows[i].costIngredientName;
					dataPopup.contractQuantity = selectedRows[i].normIndex;
					listEstimatesDetailAnalyst.push(dataPopup);
				}
				var list = Array.prototype.concat.apply(objectList, listEstimatesDetailAnalyst);
				//Check unique listEstimatesWorkItem
				function UniqueArraybyId(collection, keyname) {
					var output = [],
						keys = [];
					angular.forEach(collection, function (item) {
						var key = item[keyname];
						if (keys.indexOf(key) === -1) {
							keys.push(key);
							output.push(item);
						}
					});
					return output;
				};
				listTable = UniqueArraybyId(list, "costIngredientName");
				fillDataTableDetail(listTable);
				refreshGridDetail(listTable);
			}	
		}
		
		//  popup lay vat tu B cap
		 function showPopup() {
			acceptanceBListService
				.getAcceptanceListById(vm.item.constructId)
				.then(
				function (result) {
					var templateUrl = 'views/popup/gridViewBMaterial.html';
					var title = "Danh sách vật tư B cấp của công trình";
					vm.gridOptionsDetail = kendoConfig
						.getGridOptions({
							autoBind: true,
							dataSource: result.plain(),
							noRecords: true,
							messages: {
								noRecords: gettextCatalog
									.getString("Không có kết quả nào")
							},
							columns: [
								{
									title: gettextCatalog.getString("STT"),
									field: "STT",
									template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
									width: 100
								},
								{
									title: "<input type='checkbox' name='gridchkselectall' ng-click='chkSelectAll();' ng-model='chkAll' />",
									template: "<input type='checkbox' name='gridcheckbox' />",
									width: 35
								},
								{
									title: gettextCatalog
										.getString("Tên vật tư"),
									field: "costIngredientName",
									width: 150
								},
								{
									title: gettextCatalog
										.getString("Đơn vị tính"),
									field: "unit",
									width: 150
								},
								{
									title: gettextCatalog
										.getString("KL hợp đồng"),
									field: "normIndex",
									width: 200,
									format: "{0:n}",
									type: "number", validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"}
								},
								{
									title: gettextCatalog
										.getString("KL nghiệm thu"),
									field: "quantity",
									width: 150,
									format: "{0:n}",
									type: "number", validation: { min: 0,max : 99999999, required: true , message :"Số quá lớn"}
								},
								{
									title: gettextCatalog
										.getString("Nguồn gốc"),
									field: "specificationOrigin",
									width: 200
								},
								{
									title: gettextCatalog
										.getString("Chất lượng"),
									field: "quality",
									width: 200
								}
							]
						});
					CommonService.populateDataToGridBMaterial(templateUrl, title, vm.gridOptionsDetail, vm,
						PopupConst.AcceptanceBList['showPopup'], false, true);
				},
				function (errResponse) {
					console
						.error('Error while fetching locatorbank');
				});
		}

		//export List PDF
		function exportPDF() {
			var selectedRow = [];
			var grid = vm.inoutGrid;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.bmaterialAcceptanceId);
				}
			});
			if(selectedRow.length == 0 && checkonchange == 0 )
			{
			    toastr.warning("Bạn cần chọn bản ghi để export !");
			}else{
				// khi xem chi tiết -> export .pdf
				if(checkonchange == 1 && vm.hienthi==true){
					$('#loading').show();
			       	acceptanceBListService.exportFilePDFDetail(vm.itemID).then(function(data){
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
			    	    acceptanceBListService.exportFilePDF(selectedRow).then(function(data){
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
			    	    acceptanceBListService.exportFilePDF(selectedRow).then(function(data){
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
				else if(checkonchange == 1 && vm.hienthi == false)
				{
			       	$('#loading').show();
			       	acceptanceBListService.exportFilePDFDetail(vm.itemID).then(function(data){
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
			       	acceptanceBListService.exportFilePDFDetail(vm.itemID).then(function(data){
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

		//export List DOC
		function exportDOC() {
			var selectedRow = [];
			var grid = vm.inoutGrid;
			grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.bmaterialAcceptanceId);
				}
			});
			if(selectedRow.length == 0 && checkonchange == 0 )
			{
			    toastr.warning("Bạn cần chọn bản ghi để export !");
			}else{
				// khi xem chi tiết -> export .pdf
				if(checkonchange == 1 && vm.hienthi==true){
					$('#loading').show();
			       	acceptanceBListService.exportFileDOCDetail(vm.itemID).then(function(data){
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
			    	    acceptanceBListService.exportFileDOC(selectedRow).then(function(data){
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
			    	    acceptanceBListService.exportFileDOC(selectedRow).then(function(data){
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
				else if(checkonchange == 1 && vm.hienthi == false)
				{
			       	$('#loading').show();
			       	acceptanceBListService.exportFileDOCDetail(vm.itemID).then(function(data){
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
			       	acceptanceBListService.exportFileDOCDetail(vm.itemID).then(function(data){
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

		// Thêm 1 row mới
		function addRow() {
			var grid = $("#inoutGridDetail").data("kendoGrid");
			grid.dataSource.insert();
		}

		function removeRow() {
			// linhnn
			var selectedRow = [];
			var listRow = [];
			var grid = vm.inoutGridDetail;
			grid.table.find("tr").each(function (idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem);
					listRow.push(row);
				}
			});			
			for (var i = listRow.length - 1; i >= 0; i--) {
				grid.removeRow(listRow[i]);
			}
			if (selectedRow.length == 0) {
				toastr.warning("Phải tích chọn bản ghi !");
				return;
			}
			var objectDTO = [];
			for (var i = 0; i < selectedRow.length; i++) {
				if (selectedRow[i] != null && selectedRow[i] != "") {
					objectDTO.push(selectedRow[i]);
				} else {
					 selectedRow[i] = null;
				}
			}
//			if (objectDTO.length > 0 && confirm("Xác nhận xóa ? ")) {
				for (var i = 0; i < objectDTO.length; i++) {
					if (objectDTO[i].bmatAccMerListId != "") {
						vm.objRemove.push(objectDTO[i].bmatAccMerListId);
					} else {
						console.log("rong");
					}
				}
//			}
		}

		// chức năng thêm mới và update, phe duyet
		function addAll() {
			//phe duyet
			if (vm.appro) {
				vm.approData.bmaterialAcceptanceId = vm.itemID.bmaterialAcceptanceId;
//				delete vm.approData.bMaterialAcceptanceId;
				vm.approData.employeeId = Constant.user.srvUser.catEmployeeId;
				vm.approData.constrCompReMapId = vm.itemID.constrCompReMapId;
				acceptanceBListService.appro(vm.approData).then(function (data) {
					refreshGrid([]);
					vm.hienthi = false;
					loadDataTable();
					var x = data;
					if (x == '1') {
						toastr.warning("Chưa đến lượt duyệt");
						return;
					}
					if (x == '2') {
						toastr.warning("Đã duyệt rồi");
						return;
					}
					if (x == '4') {
						toastr.success("Từ chối duyệt thành công");
					}
					if (x == '3') {
						toastr.success("Duyệt thành công");
					}
					if (x == '0') {
						toastr.warning("Lỗi xảy ra");
						return;
					}
					if (x == '5') {
						toastr.warning("Bạn không được phép duyệt");
						return;
					}
				}, function (error) {
					toastr.error("Duyệt lỗi");
					refreshGrid([]);
					vm.hienthi = false;
					loadDataTable();
				});
				return;
			}
			// nếu đang ở trang thêm mới
			$(".k-invalid-msg").hide();
			if (vm.validator.validate()) {
				var data = vm.inoutGridDetail.dataSource.data();
				if (data.length == 0) {
					toastr.error(gettextCatalog.getString("Chưa có nội dung nghiệm thu"));
				} else {
					if (vm.isCreateNew == true) {
						//						alert("thong bao add new");
						var isCreateUser = Constant.user.srvUser.userId;
						vm.template.createdUserId = isCreateUser;
						vm.template.createdDate = new Date();
						var data = vm.inoutGridDetail.dataSource.data();
						var objectDTOB = [];
						var checkValid = false;
						for (var i = 0; i < data.length; i++) {
							if (checkValue(data[i])) {
								objectDTOB.push(data[i]);
								checkValid = true;
							} else {
								checkValid = false;
							}
						}
						if (checkValid == true) {
							vm.template.bmaterialAcceptanceId = vm.itemID.bmaterialAcceptanceId;
							vm.template.isActive = "1";
							vm.template.signPlace = vm.itemID.signPlace;
							vm.template.acceptanceBase = vm.itemID.acceptanceBase;
							vm.template.signDate = vm.itemID.signDate;
							vm.template.contractCode = vm.itemID.contractCode;
							vm.template.conclusion = vm.itemID.conclusion;
							vm.template.statusCa = "0";
							vm.template.conclusion = document.getElementById("conclusion").value;
							vm.template.contractQuantity = vm.itemID.contractQuantity;
							vm.template.bmaterialAcceptMerList = objectDTOB;
							vm.template.code = vm.itemID.code;
							vm.template.amonitorId = document.getElementById("monitoring").value;
							vm.template.binChargeConstructId = document.getElementById("technical").value;
							var isCreateUser = Constant.user.srvUser.userId;
							vm.template.createdUserId = isCreateUser;
							vm.template.createdDate = new Date();						
							acceptanceBListService.addBMaterialAcceptance(vm.template).then(function () {
								toastr.success("Thêm mới thành công ");
								refreshGrid([]);
								vm.hienthi = false;
								loadDataTable();
								vm.btnPheDuyet = false;
								vm.btnTrinhDuyet = false;
							});
						}
					}
					
					else if ((vm.isCreateNew == false && vm.itemID.statusCa == 0)) {						
						//nếu ở trang sửa Soạn Thảo
						//alert("updateeeee");
						$(".k-invalid-msg").hide();
						var data = vm.inoutGridDetail.dataSource.data();
						var objectDTO = [];
						var checkValid = false;
						for (var i = 0; i < data.length; i++) {
							if (checkValue(data[i])) {
								objectDTO.push(data[i]);
								 checkValid = true;
							}else{
								 checkValid = false;
							}
						}			
						vm.template.bmaterialAcceptMerList = objectDTO;
						if (vm.itemID != null && checkValid == true) {
							//						vm.itemID.statusCa = "1";
//							vm.template.isActive = "1";
							vm.template.statusCa = vm.itemID.statusCa;
							vm.template.bmaterialAcceptanceId = vm.itemID.bmaterialAcceptanceId;
							vm.template.isActive = vm.itemID.isActive;
							vm.template.code = vm.itemID.code;
							vm.template.amonitorId = vm.itemID.amonitorId;
							vm.template.binChargeConstructId = vm.itemID.binChargeConstructId;
							vm.template.signPlace = vm.itemID.signPlace;
							vm.template.acceptanceBase = vm.itemID.acceptanceBase;
							vm.template.signDate = vm.itemID.signDate;
							vm.template.contractCode = vm.itemID.contractCode;
							vm.template.conclusion = vm.itemID.conclusion;
							vm.template.statusCa = vm.itemID.statusCa;
							vm.template.conclusion = document.getElementById("conclusion").value;
							vm.template.contractQuantity = vm.itemID.contractQuantity;
							vm.template.createdUserId = vm.itemID.createdUserId;
							vm.template.createdDate = vm.itemID.createdDate;
						}
						if (vm.itemID.createdUserId == Constant.user.srvUser.userId){
						acceptanceBListService.update(vm.template).then(function () {
							if (vm.objRemove && vm.objRemove.length > 0) {
								acceptanceBListService.deleteResult(vm.objRemove).then(function () {
									toastr.success("Cập nhật thành công ");									
									refreshGrid([]);
									loadDataTable();
									vm.objRemove=[];
									vm.hienthi = false;
								}, function (errResponse) {
									toastr.eror("Cập nhật thất bại");
								});
							}else{
									vm.hienthi = false;
									toastr.success("Cập nhật thành công ");
									loadDataTable();
									refreshGrid();			
							}
							// vm.hienthi = false;   // update không cần quay về màn hình chính
						}, function (error) {
							toastr.eror("Cập nhật thất bại");
						});
					}else{
						toastr.error("Bạn không phải người tạo, không có quyền sửa");
					}
					}else if ((vm.isCreateNew == false && vm.itemID.statusCa == 3)) {
						$(".k-invalid-msg").hide();
						//nếu ở trang sửa Từ chối duyệt	
						var data = vm.inoutGridDetail.dataSource.data();
						var objectDTO = [];
						var checkValid = false;
						for (var i = 0; i < data.length; i++) {
							if (checkValue(data[i])) {
								objectDTO.push(data[i]);
								 checkValid = true;
							}else{
								 checkValid = false;
							}
						}
						vm.template.bmaterialAcceptMerList = objectDTO;
						if (vm.itemID != null && checkValid == true) {
							vm.template.statusCa = vm.itemID.statusCa;
							vm.template.bmaterialAcceptanceId = vm.itemID.bmaterialAcceptanceId;
							vm.template.isActive = vm.itemID.isActive;
							vm.template.code = vm.itemID.code;
							vm.template.amonitorId = vm.itemID.amonitorId;
							vm.template.binChargeConstructId = vm.itemID.binChargeConstructId;
							vm.template.signPlace = vm.itemID.signPlace;
							vm.template.acceptanceBase = vm.itemID.acceptanceBase;
							vm.template.signDate = vm.itemID.signDate;
							vm.template.contractCode = vm.itemID.contractCode;
							vm.template.conclusion = vm.itemID.conclusion;
							vm.template.statusCa = 0;
							vm.template.conclusion = document.getElementById("conclusion").value;
							vm.template.contractQuantity = vm.itemID.contractQuantity;
							vm.template.createdUserId = vm.itemID.createdUserId;
							vm.template.createdDate = vm.itemID.createdDate;
						}
						if (vm.itemID.createdUserId == Constant.user.srvUser.userId){
						acceptanceBListService.update(vm.template).then(function () {
							if (vm.objRemove && vm.objRemove.length > 0) {
								acceptanceBListService.deleteResult(vm.objRemove).then(function () {
									toastr.success("Cập nhật thành công ");									
									refreshGrid([]);
									loadDataTable();
									vm.objRemove=[];
								}, function (errResponse) {
									toastr.eror("Cập nhật thất bại");									
								});
							}else{
									vm.hienthi = false;
									toastr.success("Cập nhật thành công ");
									loadDataTable();
									refreshGrid();							
							}
							// vm.hienthi = false;   // update không cần quay về màn hình chính
						}, function (error) {
							toastr.eror("Cập nhật thất bại");
						});		
						}else{
							toastr.error("Bạn không phải người tạo, không có quyền sửa")}
					}else{
						toastr.error("Không được sửa bản ghi đã trình duyệt/đã duyệt");
					}
				}
				//
			}
		}

		//dongnv checkvalue
		function checkValue(data) {
			if (data.materialName) {
				if (data.unit) {
//					if (data.contractQuantity) {
						if (data.quantity) {
							if (data.specificationOrigin) {
								if (data.quality) {
									return true;
								} else {
									toastr.warning("Không để trống trường chất lượng");
									return false;
								}
							} else {
								toastr.warning("Không để trống trường nguồn gốc");
								return false;
							}
						} else {
							toastr.warning("Không để trống trường KL nghiệm thu");
							return false;
						}
//					} else {
//						toastr.warning("Không để trống trường KL hợp đồng");
//						return false;
//					}
				} else {
					toastr.warning("Không để trống trường đơn vị tính");
					return false;
				}
			} else {
				toastr.warning("Không để trống trường loại vật liệu!");
				return false;
			}
		}

		function exportFile() {
			vm.doc.contractCode = vm.item.contractCode;
			vm.doc.constrtCode = vm.item.constrtCode;
			vm.doc.constrtName = vm.item.constrtName;
			vm.doc.constrtAddress = vm.item.constrtAddress;
			vm.doc.afullName = vm.itemID.afullName;
			vm.doc.bfullName = vm.itemID.bfullName;
			vm.doc.signPlace = vm.itemID.signPlace;
			vm.doc.signDate = vm.itemID.signDate;
			vm.doc.acceptanceBase = vm.itemID.acceptanceBase;
			vm.doc.signDate = vm.itemID.signDate;

			var conclusions = document.getElementById("conclusion");
			vm.itemID.conclusion = conclusions.options[conclusions.selectedIndex].text;

			for (var i = 0; i < vm.monitoring.length; i++) {
				if (vm.monitoring[i].id == vm.template.amonitorId) {
					vm.doc.afullName = vm.monitoring[i].fullName;
				}
			}

			for (var i = 0; i < vm.technical.length; i++) {
				if (vm.technical[i].id == vm.template.binChargeConstructId) {
					vm.doc.bfullName = vm.technical[i].fullName;
				}
			}
			var data = vm.inoutGridDetail.dataSource.data();
			var objectDTO = [];

			for (var i = 0; i < data.length; i++) {
				if (checkValue(data[i])) {

					objectDTO.push(data[i]);
				}
			}

			vm.doc.listResultDTO = objectDTO;
			acceptanceBListService.exportFile(vm.doc).then(function () {
				toastr.success("Export thành công");
			}, function (errResponse) {
				toastr.error("Export lỗi");
			});
		}

		// ////////////////End controller B Detail/////////////////////////////////
		/* Handle action client on a menu item */
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
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
		}
		// //Remove////////////
		function detail() {

			if (vm.inoutGrid.select().length > 0) {
				vm.showDetail = true;
			} else {
				toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
			}
		}

		function onChangeDetail() {
			if (vm.inoutGridDetail.select().length > 0) {
				var tr = vm.inoutGridDetail.select().closest("tr");
				var dataItem = vm.inoutGridDetail.dataItem(tr);
				vm.itemDetail = dataItem;
			}
		}

		function onChange() {
			checkonchange = 0;
			if (vm.inoutGrid.select().length > 0) {
				var tr = vm.inoutGrid.select().closest("tr");
				var dataItem = vm.inoutGrid.dataItem(tr);

				vm.itemID = dataItem;
				console.log("minh:"+vm.itemId);
			acceptanceBListService.setItemID(vm.itemID);
				vm.theApproval.code = dataItem.code;
				vm.theApproval.constructId = dataItem.constructId;
				vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
				vm.theApproval.stringEmployee = dataItem.binChargeConstructId + ',' + dataItem.amonitorId;
				vm.theApproval.path = "/thanhcong";
				vm.theApproval.nameFile = "victory";
				vm.theApproval.isSign = 'theApproval';
				vm.theApproval.roleId = [Constant.ROLE_ID["employee_roleID_DT_KTTC"],Constant.ROLE_ID["employee_roleID_CDT_GSTC"]];
				vm.theApproval.roleName = ["Kỹ thuật nhà thầu","Giám sát thi công"];
				checkonchange = checkonchange +1;
				theApproval.setItem(vm.theApproval);
			}
		}


		function loadDataTable() {
			acceptanceBListService.findByConstructId(vm.item).then(function (d) {
				
				fillDataTable(d.plain());
				refreshGrid(d.plain());
			}, function (e) {
				if(vm.item.contractId){
					toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu !"));
				}else{
					toastr.error(gettextCatalog.getString("Lỗi, công trình này chưa có hợp đồng ! "));
					vm.disable = true;
				}
				
			});
		}
		// Lam moi lai danh sach
		function refreshGrid(d) {
		var grid = vm.inoutGrid;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}

		function getDataRowSelect() {
			var demopromise = $q.defer();
			var grid = vm.inoutGrid;
			// Check select
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
				return;
			}
			var tr = grid.select().closest("tr");// output card tr
			var dataItem = grid.dataItem(tr); // data card tr
			demopromise.resolve(dataItem);
			return demopromise.promise;
		}

		// bat su kien cho nut sua
		function goToUpdate(menuKey) {
			getDataRowSelect().then(function () {
				if (vm.inoutGrid.select().length > 0) {
					$rootScope.$broadcast("acceptanceBListController.detail");
				} else {
					return;
				}
			});

			var hasPerm = true;
			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);
				postal.publish({
					channel: "Tab",
					topic: "open",
					data: template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
		}
		// bat su kien cho nut them moi
		function goToAdd(menuKey) {
			acceptanceBListService.setItemID(null);
			$rootScope.$broadcast("acceptanceBListController.detail.null");
			var hasPerm = true;
			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);
				postal.publish({
					channel: "Tab",
					topic: "open",
					data: template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
		}


		// ---------------- xoa bang 
		function removeBMA() {
			if (vm.hienthi == true){
//				alert("xoa bang con");				
				var selectedRow = [];
				var listRow = [];
				var grid = vm.inoutGridDetail;
				grid.table.find("tr").each(function (idx, item) {
					var row = $(item);
					var checkbox = $('[name="gridcheckbox"]', row);
					if (checkbox.is(':checked')) {
						// Push id into selectedRow
						var tr = grid.select().closest("tr");
						var dataItem = grid.dataItem(item);
						selectedRow.push(dataItem);
						listRow.push(row);
					}
				});

				if (selectedRow.length > 0 && confirm("Xác nhận xóa ? ")) {
				for (var i = listRow.length - 1; i >= 0; i-- ) {
					grid.removeRow(listRow[i]);
				}
				}
				if (selectedRow.length == 0) {
					toastr.warning("Phải tích chọn bản ghi !");
					return;
				}
				var objectDTO = [];
				for (var i = 0; i < selectedRow.length; i++) {
					if (selectedRow[i] != null && selectedRow[i] != "") {
						objectDTO.push(selectedRow[i]);
					} else {
						 selectedRow[i] = null;
					}
				}
				if (objectDTO.length > 0 ) {
					for (var i = 0; i < objectDTO.length; i++) {
						if (objectDTO[i].bmatAccMerListId != "") {
							console.log("ko rong");
							vm.objRemove.push(objectDTO[i].bmatAccMerListId);
						} else {
							console.log("rong");
						}
					}
				}
			}
			else{				
//				alert("xoa bang cha");				
				var selectedRow = [];
				var checkuseroK = 0;
				var selectedRowObj = [];
				var grid = vm.inoutGrid;
				grid.table.find("tr").each(function (idx, item) {
					var row = $(item);
					var checkbox = $('[name="gridcheckbox"]', row);

					if (checkbox.is(':checked')) {
						// Push id into selectedRow
						var tr = grid.select().closest("tr");
						var dataItem = grid.dataItem(item);

						/*selectedRow.push(dataItem.bmaterialAcceptanceId);*/
						selectedRowObj.push(dataItem);
					}
				});
				if(selectedRowObj.length===0) {
					toastr.warning("Tích chọn bản ghi cần xóa");
					return;
				}
				

				//check
				
					var count =0;
					for (var i = 0; i < selectedRowObj.length; i++) {
						if (selectedRowObj[i].statusCa === 0 || selectedRowObj[i].statusCa === 3) {
							if(selectedRowObj[i].createdUserId === Constant.user.srvUser.userId)
							{
								selectedRow.push(selectedRowObj[i].bmaterialAcceptanceId);
								checkuseroK++;
								count++;
							}
							else if(checkuseroK == 0)
							{
								toastr.warning("Bạn không phải người tạo, bạn không có quyền xóa bản ghi đang chọn! !");
								checkuseroK++;
								count++;
							}
						}else {
							if(count ==0){
								toastr.warning("Không thể xóa bản ghi đang trình duyệt hoặc duyệt");
								count++;
							}						
						}
					}

						if (selectedRow.length > 0 ) {
							if (selectedRowObj.length > 0  && confirm("Xác nhận xóa ? ")){
							acceptanceBListService.deleteListEntity(selectedRow).then(function () {
								loadDataTable();
								toastr.success("Xóa thành công");

							}, function (errResponse) {
								toastr.success(gettextCatalog.getString("Có lỗi xảy ra trong quá trình xóa"));
							});
						}				 
					}
				}
		}
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistbcap").checked == true){
				document.getElementById("checkalllistbcap").checked = false;
			}
		}
		//  du lieu bang cha	
		function fillDataTable(data) {
			var deferred = $q.defer();
			vm.gridOptions = kendoConfig
				.getGridOptions({
					autoBind: true,
					editable: false,
					/*dataBound: function() {
						var grid = $("#mainGridList").data("kendoGrid");
						var selected = false;
						$.each(grid.table.find("tr"),function(){
			                 if (!selected) {
			                	 grid.select("tr:eq(0)");
			                	 selected = true;
			                 }
			             });
	                },*/
					dataSource: data,
					change: onChange,
					//						editable: false,
					noRecords: true,
					messages: {
						noRecords: gettextCatalog
							.getString("Không có kết quả nào")
					},
					columns: [
						{
							headerAttributes: { style: "text-align:center;" },
							field: "rowNumber",
							title: "STT",
							//					            editor: nonEditor,
							template: dataItem => $("#mainGridList").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							attributes: { style: "text-align:center;" },
							width: 55
						},
						{
							title: "<input type='checkbox' id='checkalllistbcap' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
							template: "<input type='checkbox' ng-checked=\"vm.chkAll\" name='gridcheckbox' ng-click='vm.handleCheck()'/>",
							width: 25
						},
						{
							title: gettextCatalog.getString("Mã phiếu"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "code",
							width: 100
						},
						{
							title: gettextCatalog.getString("Mã công trình"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "constrtCode",
							width: 100
						},
						{
							title: gettextCatalog.getString("Mã hợp đồng"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "contractCode",
							width: 150
						},
						{
							title: gettextCatalog.getString("Tên hợp đồng"),
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;" },
							field: "contractName",
							width: 150
						},
						{
							title: gettextCatalog.getString("Trạng thái"), 
							headerAttributes: { style: "text-align:center;" },
							attributes: { style: "text-align:left;", class:"statusColumn"},
							
							field: "statusCa",
							template: function ($scope) {
								if ($scope.statusCa == 0) {
									return "Soạn Thảo";
									vm.disableAll = false;
								}
								if ($scope.statusCa == 1) {
									return "Trình Duyệt";
									vm.disableAll = true;
								}
								if ($scope.statusCa == 2) {
									return "Đã Duyệt";
									vm.disableAll = true;
								}
								if ($scope.statusCa == 3) {
									return "Từ chối Duyệt";
									vm.disableAll = false;
								}else{
									return "Soạn Thảo";
									vm.disableAll = false;
								}
							},
							width: 100
						},
					]
				});
//			deferred.resolve('done');
//			return deferred.promise;
		}
		
//		$("#statuscamess").kendoTooltip({
//			filter: "td",
//            content: function (e) {
//              var target = e.target; // element for which the tooltip is shown
//              return $(target).text();
//            }
//	    }).data("kendoTooltip");
		
		
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
			if (item != undefined) {
				vm.item = item;
				vm.template.constructId = vm.item.constructId;
				vm.template.contractId = vm.item.contractId;
				loadDataTable();
			} else {
				console.error("không nhận được dữ liệu!");
			}
		});
		
		function importBmaterial(){
			WindowService.open({
                options: {
                    modal: true,
                    title: "Import vật tư B cấp",
                    visible: false,
                    width: '650',
                    height: '200',
                    actions: ["Minimize", "Maximize", "Close"],
                    open: function() {
                        this.wrapper.children('.k-window-content').addClass("fix-footer");
                    }
                },
                templateUrl: "qlhc/nghiemthuchatluongvattubenBcap/bieumauImport.html"

            });
	}
	}
})();
