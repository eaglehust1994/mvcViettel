
(function() {
	'use strict';

	var controllerId = 'monitorMissionAssignController';

	angular.module('MetronicApp').controller(controllerId,ham);

	/* @ngInject */
	function ham($scope, $rootScope, Constant, gettextCatalog, kendoConfig, $kWindow,$q,monitorMissionAssignService,ProposalEvaluation, theApproval,RestEndpoint) {
		var vm = this;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.add = add;
		vm.view = view;
		vm.saveAll = saveAll;
		vm.remove = remove;
		vm.showDetail = false;
		vm.disable= false;
		vm.vohieuhoa=false;
		vm.isCreate=  true;
		vm.buttonCreatNew= false;
		vm.buttonPheDuyet= false;
		vm.showPheduyet= false;
		vm.hideSaveAppro = true;
		vm.hideSave = false;
		vm.onChange = onChange;
		vm.isUpdate = false;
		vm.isCreate = false;
		vm.buttonRemove = false;
		vm.disableAll = false;
		var checkonchange  = 0;
		vm.folder = '';
		vm.moniMissAss = {};
		vm.objTrinhduyet = {};
		
		vm.monitormission = {};
		// thông tin chung
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		/* console.log(vm.item); */
		Constant.user.srvUser.catEmployeeId;
		Constant.user.srvUser.userId;
		vm.item = ProposalEvaluation.getItem();
		// ////////////////////////Combobox thanh phan tham
		// gia//////////////////////////////////
		vm.role=[];
		var totalListEmployee = [], MonitorId = [], DirectorId = []
		vm.amonitorId = [];
		vm.adirectorId = [];
		function UniqueArraybyId(collection, keyname) {
            var output = [], 
                keys = [];

            angular.forEach(collection, function(item) {
                var key = item[keyname];
                if(keys.indexOf(key) === -1) {
                    keys.push(key);
                    output.push(item);
                }
            });
            return output;
        };
        var roleNguoiGiao = '';
        var roleNguoiNhan = '';
        angular.element(document).ready(function () {
        monitorMissionAssignService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
			totalListEmployee = data.plain();
			monitorMissionAssignService.getRoleId().then(function(data) {
//				vm.role = data;
				for (var i = 0; i < totalListEmployee.length; i++) {
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_GSTC"]){
						MonitorId.push(totalListEmployee[i]);
						roleNguoiNhan = totalListEmployee[i].roleid;
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){
						DirectorId.push(totalListEmployee[i]);
						
						roleNguoiGiao = totalListEmployee[i].roleid;
					}
				}
				vm.amonitorId = MonitorId;
				vm.adirectorId = DirectorId;
				if(MonitorId.length > 0) {
					vm.bean.aMonitorId = MonitorId[0].userId;
				}
				if(DirectorId.length > 0) {
					vm.bean.aDirectorId = DirectorId[0].userId;
				}
			})
			.catch(function(data, status) {
				console.error('getRoleId error', response.status, response.data);
			});
		  })
		  .catch(function(data, status) {
		    console.error('Gists error', response.status, response.data);
		})
        });
		// thông tin trình duyệt
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
		
		vm.datePickerConfig = {
		        format: "dd/MM/yyyy HH:mm:ss",
		        parseFormats: ["yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm:ss"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy HH:mm:ss')#"
		    };
		vm.datePickerConfig2 = {
		        format: "dd/MM/yyyy",
		        parseFormats: ["yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy')#"
		    };
		
		function verifyMyDate(d) {
		    var re = /^(((0[1-9]|[12]\d|3[01])[\/\.-](0[13578]|1[02])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|[12]\d|30)[\/\.-](0[13456789]|1[012])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|1\d|2[0-8])[\/\.-](02)[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((29)[\/\.-](02)[\/\.-]((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))$/g;
		    return re.test(d);
		}
		function validateDateTime(){
			if(!verifyMyDate($("#acceptFromDate").val())){
				toastr.warning(gettextCatalog.getString("Bạn nhập sai định dạng ngày giờ trường ngày bắt đầu !"));
				return false;
			}
			if(!verifyMyDate($("#acceptToDate").val())){
				toastr.warning(gettextCatalog.getString("Bạn nhập sai định dạng ngày giờ trường ngày kết thúc!"));
				return false;
			}
			return true;
		}
		// get all monitor
		monitorMissionAssignService.getMonitorMissionAssign(vm.item).then(function(d) {
			refreshGrid(d.plain());
		}, function() {
			console.error('Error while fetching object type');
		});
		function reloadAdd(){
			vm.bean ={};
			if(MonitorId.length > 0) {
				vm.bean.aMonitorId = MonitorId[0].userId;
			}
			
			if(DirectorId.length > 0) {
				vm.bean.aDirectorId = DirectorId[0].userId;
			}
			vm.moniMissAss ={};
			
		}
		function add(){
			vm.disableAll = false;
			vm.isCreate = true;
			reloadAdd();
			if(MonitorId.length > 0) {
				vm.bean.aMonitorId = MonitorId[0].userId;
			}
			
			if(DirectorId.length > 0) {
				vm.bean.aDirectorId = DirectorId[0].userId;
			}
			var data = vm.mmaGrid.dataSource.data();
				//vm.buttonRemove = true;
				vm.isCreate=  true;
				vm.buttonPheDuyet= false;
				vm.showPheduyet= false;
				vm.vohieuhoa = false;
				vm.showDetail= !vm.showDetail;
		}
		
		function view(){
			$(".k-invalid-msg").hide();
			if(!vm.showDetail && vm.mmaGrid.select().length == 0){
				toastr.warning("Bạn cần chọn một bản ghi trước ");
			}else{
			vm.isCreate = false;
			vm.hideSaveAppro = true;
			vm.hideSave = false;
			vm.showDetail= !vm.showDetail;
			vm.bean.signPlace = vm.moniMissAss.signPlace;
			vm.bean.signDate = vm.moniMissAss.signDate;
			vm.bean.aDirectorId = vm.moniMissAss.aDirectorId;
			vm.bean.aMonitorId = vm.moniMissAss.aMonitorId;
			vm.bean.assignNote = vm.moniMissAss.assignNote;
			vm.bean.missionDate = vm.moniMissAss.missionDate;
			vm.bean.monitorDocument = vm.moniMissAss.monitorDocument;
			if(vm.moniMissAss.createdUserId != Constant.user.srvUser.userId && vm.showDetail == true){
				//toastr.warning("Bạn không có quyền sửa bản ghi này !");
				vm.disableAll = true;
				return;
			}
			if(vm.moniMissAss.statusCa != 0 && vm.moniMissAss.statusCa != 3 && vm.showDetail == true){
				//toastr.warning("Bạn không có quyền sửa bản ghi đang trình duyệt hoặc đã duyệt !");
				vm.disableAll = true;
			}
			}
			
		}
		reloadData();
		vm.Approval = function(){
			monitorMissionAssignService.pheduyet(vm.dataItem).then(thongbaoThanhcong,function(errResponse) {
					toastr.error(gettextCatalog.getString("Lỗi khi cập nhật "));
					return;
			});
		}
		
		function reloadData(){
			monitorMissionAssignService.getMonitorMissionAssign(vm.item).then(function(d) {
				refreshGrid(d.plain());
				fillDataTable(d.plain());
			}, function() {
				console.error('Error while fetching object type');
			});
		};
	
		function refreshGrid(d) {
			var grid = vm.mmaGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		function onChange(){
			checkonchange = 0;
			if (vm.mmaGrid.select().length > 0){
    			var tr = vm.mmaGrid.select().closest("tr");
    			var dataItem = vm.mmaGrid.dataItem(tr);
    			vm.moniMissAss = dataItem;
    			checkonchange = checkonchange + 1;
    			vm.objTrinhduyet = dataItem;
    			vm.bean.signPlace = vm.moniMissAss.signPlace;
    			vm.bean.signDate = vm.moniMissAss.signDate;
    			vm.bean.aDirectorId = vm.moniMissAss.aDirectorId;
    			vm.bean.aMonitorId = vm.moniMissAss.aMonitorId;
    			vm.bean.assignNote = vm.moniMissAss.assignNote;
    			vm.bean.missionDate = vm.moniMissAss.missionDate;
    			vm.bean.monitorDocument = vm.moniMissAss.monitorDocument;
			}
		}
		
		vm.chkSelectAll = function(item) {
	    	var grid = vm.mmaGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    };
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistmission").checked == true){
				document.getElementById("checkalllistmission").checked = false;
			}
			var grid = vm.mmaGrid;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				
				if (checkbox.is(':checked')) {
					//vm.focusprior = false;
					
				}
			});
		}
		// các hàm hiển thị dữ liệu Bảng
		function fillDataTable(data) {
		vm.gridMissionOptions = kendoConfig.getGridOptions({
				autoBind : true,
				change : onChange,
				dataSource : data,
				editable: false,
				resizable: true,
// dataBound: function() {
// var grid = $("#stt").data("kendoGrid");
// var selected = false;
// $.each(grid.table.find("tr"),function(){
// if (!selected) {
// grid.select("tr:eq(0)");
// selected = true;
// }
// });
// },
				noRecords : true,
				/* editable: false, */
				messages : {
					noRecords : gettextCatalog.getString("Không có dữ liệu trong trang")
				},
				columns : [ {
					field: "STT",
		            title: "STT",
		            template: dataItem => $("#stt").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
		            attributes: {
			      		style: "text-align: center;"
					},
		            width : 55
				}, 
				{
			         title : "<input type='checkbox' name='gridchkselectall' id='checkalllistmission' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
			         template : "<input type='checkbox' ng-checked=\"vm.chkAll\" name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			         width : 25
			    }, 
			    {
					title : gettextCatalog.getString("Mã Phiếu"),
					field : "code",
					width : 120
				}, 
				{
					title : gettextCatalog.getString("Mã công trình"),
					field : "constrtCode",
					width : 110
				}, {
					title : gettextCatalog.getString("Mã hợp đồng"),
					field : "contractCode",
					width : 140
				},{
					title : gettextCatalog.getString("Tên hợp đồng"),
					field : "contractName",
					width : 150
				}, {
					title : gettextCatalog.getString("Trạng thái"),
					field : "statusCa",
					editor: nonEditor,
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
					width : 110
				}]
			});
		}
			// / disable edit textbox in grid
			function nonEditor(container, options) {
		        container.text(options.model[options.field]);
		    }
	/*		
			$("#stt").kendoTooltip({
				filter: "td",
	            content: function (e) {
	              var target = e.target; // element for which the tooltip is shown
	              return $(target).text();
	            }
		    }).data("kendoTooltip");*/
			 
		vm.dataItem={};
		
		// Su kien khi click vao mot hang cua danh sach
		vm.onGridChange = function(e, sel, dataItem) {
		
			vm.dataItem = dataItem;
			// monitorMissionAssignService.setDataItem(vm.dataItem);
			// Gán giá trị để trình duyệt
			vm.theApproval.isSign = 'theApproval';
			vm.theApproval.code = dataItem.code;
			vm.theApproval.constructId = dataItem.constructId;
			vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
			vm.theApproval.stringEmployee  = dataItem.binchargeConstructId + ',' +dataItem.ainchargemonitorid + ',' + dataItem.bdirectorId+ ',' +dataItem.adirectorId;
			vm.theApproval.path= "/thanhcong";
			vm.theApproval.nameFile = "victory";
			vm.theApproval.roleId = [Constant.ROLE_ID["employee_roleID_DT_PTTC"],Constant.ROLE_ID["employee_roleID_CDT_PTGST"],Constant.ROLE_ID["employee_roleID_DT_GDNT"],Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
			vm.theApproval.roleName = ["Phụ trách thi công trực tiếp","Giám sát thi công","Giám đốc nhà thầu","Thủ trưởng đơn vị"];
			theApproval.setItem(vm.theApproval);
		}
		
		// trình duyệt
		vm.trinhDuyet=function(){
			var grid = vm.mmaGrid;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}else{
				if(vm.dataItem.statusCa == 0){
					$('#loading').show();
					vm.dataItem.contractId=vm.item.contractId;
					monitorMissionAssignService.exportOnePDF(vm.dataItem).then(function(data) {
						 vm.theApproval.path= data.fileName;
				         vm.theApproval.nameFile= data.fileName;
				         theApproval.setItem(vm.theApproval);
				         goTo('THE_APPROVAL');
					}).catch( function(){
						$('#loading').hide();
						toastr.error(gettextCatalog.getString("Lỗi khi trình duyệt!"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
				 }else{
					toastr.warning("Trạng thái duyệt khác soạn thảo - Không thể trình duyệt!");
				}
			}
		};
		//ngoccx
		//huy trinh duyet
		vm.cancelApprovalBtn= function(){
			var grid = vm.mmaGrid;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}
			vm.moniMissAss.tableName = 'MONITOR_MISSION_ASSIGN';
			vm.moniMissAss.tableId = vm.moniMissAss.monitorMissionAssignId;
			vm.moniMissAss.tableIdField = 'MONITOR_MISSION_ASSIGN_ID';
			if(vm.moniMissAss.statusCa == 1){
				if(vm.moniMissAss.createdUserId != Constant.user.srvUser.userId){
					toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy trình duyệt!"));
					return;
				}else{
					if(confirm('Xác nhận hủy trình duyệt')){
					monitorMissionAssignService.cancelAprroval(vm.moniMissAss).then(function() {
						status = true;
						$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
						reloadData();
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
					channel : "Tab",
					topic   : "open",
					data    : template
				});
			}
		}
		
		
		function saveAll(){
			if(vm.showDetail == false){
				return;
			}
			vm.monitormission.signPlace = vm.bean.signPlace;
			vm.monitormission.signDate = vm.bean.signDate;
			vm.monitormission.aDirectorId = vm.bean.aDirectorId;
			vm.monitormission.aMonitorId = vm.bean.aMonitorId;
			vm.monitormission.assignNote = vm.bean.assignNote;
			vm.monitormission.missionDate = vm.bean.missionDate;
			vm.monitormission.monitorDocument = vm.bean.monitorDocument;
			vm.monitormission.constructId = vm.item.constructId;
			
			if(vm.isCreate == true){
				if(vm.validator.validate()){
				var fileName = $($('input[name="uploadfileassign"]')[0]).val().split('\\').pop();
				if(vm.showDetail == true && $($('input[name="uploadfileassign"]')[0]).val().split('\\').pop() == ""){
					toastr.warning("Chưa có file chứng chỉ văn bằng giám sát");
					return;
				}
				vm.uploadFile($(this).closest('tr').find('input[name="uploadfileassign"]').attr('id'));
				vm.monitormission.documentPath = monitorMissionAssignService.getData();
				vm.monitormission.documentName = fileName;
				console.log(monitorMissionAssignService.getData());
				vm.monitormission.createdUserId = Constant.getUser().srvUser.userId;
			monitorMissionAssignService.saveAll(vm.monitormission).then(function() {
				toastr.success("Lưu thành công ");
				$($('input[name="uploadfileassign"]')[0]).val('');
				reloadData();
	     }, function(errResponse) {			    	 
	    	 if (errResponse.status == 302){
	    		 toastr.error("Bản ghi đang được sử dụng");
	    	 }else{
	    		 toastr.error("Lỗi khi lưu");
	    	 } 			    	 
	     });
			vm.showDetail= false;
				}
			}else{
	    	 if(vm.moniMissAss.createdUserId != Constant.user.srvUser.userId){
	    	 toastr.warning("Bạn không có quyền sửa bản ghi này !");
	    	 return;
	     }else{
	    	 if(vm.moniMissAss.statusCa != 0 && vm.moniMissAss.statusCa != 3 && vm.showDetail == true){
					toastr.warning("Bạn không có quyền sửa bản ghi đang trình duyệt hoặc đã duyệt !");
					return;
				}
	    	 
	    	 if($($('input[name="uploadfileassign"]')[0]).val().split('\\').pop() == ""){
	    		 vm.monitormission.documentName = vm.moniMissAss.documentName;
	    		 vm.monitormission.documentPath = vm.moniMissAss.documentPath;
	    	 }else{
	    		 vm.uploadFile($(this).closest('tr').find('input[name="uploadfileassign"]').attr('id'));
	    		 vm.monitormission.documentPath = monitorMissionAssignService.getData();
	    		 console.log(monitorMissionAssignService.getData());
	    		 vm.monitormission.documentName = $($('input[name="uploadfileassign"]')[0]).val().split('\\').pop();
	    	 }
	    	 vm.monitormission.statusCa = 0;
	    	 vm.monitormission.monitorMissionAssignId = vm.moniMissAss.monitorMissionAssignId;
	    	 vm.monitormission.code = vm.moniMissAss.code;
	    	 vm.monitormission.createdDate = vm.moniMissAss.createdDate;
	    	 vm.monitormission.approvalDate = vm.moniMissAss.approvalDate;
	    	 vm.monitormission.createdUserId = vm.moniMissAss.createdUserId;
	    	 monitorMissionAssignService.updateMissionAssign(vm.monitormission).then(function() {
					toastr.success("Cập nhật thành công ");
					status = true;
					$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
					$($('input[name="uploadfileassign"]')[0]).val('');
					reloadData();
		     }, function(errResponse) {			    	 
		    	 if (errResponse.status == 302){
		    		 toastr.error("Bản ghi đang được sử dụng");
		    	 }else{
		    		 toastr.error("Lỗi khi lưu");
		    	 } 			    	 
		     });
	     }
			
			vm.showDetail= false;
	     }
		}
		
		/* DELETE */
		function remove(){
			var selectedRow = [];
    		var listID = [];
    		var noDel=0;
    		var noDel1= 0;
			var grid = vm.mmaGrid;
			
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				
				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem);
				}
			});
			
			if(vm.showDetail == true){
				return;
			}
			if (selectedRow.length == 0){
				toastr.warning("Bạn chưa chọn bản ghi nào để xóa");
				return;
			}
				for (var i = 0; i < selectedRow.length; i++) {
					if (selectedRow[i].statusCa === 0 || selectedRow[i].statusCa === 3) {
						if(selectedRow[i].createdUserId === Constant.user.srvUser.userId){
							listID.push(selectedRow[i].monitorMissionAssignId);
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
					if (selectedRow.length > 0 && confirm('Xác nhận xóa')) {
						monitorMissionAssignService.remove(listID).then(function() {
			    		toastr.success("Đã xóa thành công");
			    		// drawingsListService.deleteFile(selectedRow);
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
		
		// ////////////// Bangcon /////////////
		vm.bean = {};
		// initial table
			vm.pheDuyet= function(){
				isAppro = true;
				var grid = vm.mmaGrid;
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
					return;
				}else{
					vm.vohieuhoa=true;
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(tr);
					if(dataItem.statusCa == 0 || dataItem.statusCa == 2 || dataItem.statusCa == 3){
						toastr.warning(" Chỉ có thể phê duyệt bản ghi ở trạng thái trình duyệt!");
					}else if(dataItem.statusCa == 1){
						vm.hideSaveAppro = false;
						vm.hideSave = true;
						
						vm.showDetail = true;
						vm.showPheduyet= true;
						vm.isCreate =  false;
						vm.approData.statusCa = vm.approData.statusCa ? vm.approData.statusCa :2;
						
					}
				}
			}
			
			// duyet
			vm.approData = {
					"statusCa": "",
					"employeeId": Constant.user.srvUser.catEmployeeId,
					"comments": "",
					"titAziConstrAcceptId": "",
					"constrCompReMapId" : "",
				}
			
			
		
			
		// / Export PDF
			vm.exportFile = function (){
				var selectedRow = [];
				var grid = vm.mmaGrid;
				grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
					if (checkbox.is(':checked')){
						var dataItem = grid.dataItem(item);
						selectedRow.push(dataItem.monitorMissionAssignId);
					}
				});
				if(selectedRow.length == 0 && checkonchange == 0 && vm.dataItem.monitorMissionAssignId == null )
				{
				    toastr.warning("Bạn cần chọn bản ghi để export !");
				}else{
					if(checkonchange == 1 && vm.showDetail == false && selectedRow.length == 0)
					{
				       	$('#loading').show();
				       	monitorMissionAssignService.exportMonitorMissionAssign(vm.moniMissAss).then(function(data){
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
				    	    monitorMissionAssignService.exportListMonitorMissionAssign(selectedRow).then(function(data){
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
						monitorMissionAssignService.exportListMonitorMissionAssign(selectedRow).then(function(data){
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
						monitorMissionAssignService.exportMonitorMissionAssign(vm.moniMissAss).then(function(data){
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
			}
			// exportDOCX
			vm.exportFileDOC = function(){
				var selectedRow = [];
				var grid = vm.mmaGrid;
				grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
					if (checkbox.is(':checked')){
						var dataItem = grid.dataItem(item);
						selectedRow.push(dataItem.monitorMissionAssignId);
					}
				});
				if(selectedRow.length == 0 && checkonchange == 0 && vm.moniMissAss.monitorMissionAssignId == null )
				{
				    toastr.warning("Bạn cần chọn bản ghi để export !");
				}else{
					if(checkonchange == 1 && vm.showDetail == false)
					{
				       	$('#loading').show();
				       	monitorMissionAssignService.exportDocMonitorMissionAssign(vm.moniMissAss).then(function(data){
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
						monitorMissionAssignService.exportListDocMonitorMissionAssign(selectedRow).then(function(data){
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
						monitorMissionAssignService.exportListDocMonitorMissionAssign(selectedRow).then(function(data){
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
						monitorMissionAssignService.exportDocMonitorMissionAssign(vm.moniMissAss).then(function(data){
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
			}
			function getFolder() {
				monitorMissionAssignService.getMonitorAssignFolder().then(function(f) {
					vm.folder = f.folder;
				  })
				  .catch(function(data, status) {
				    console.error('Gists error', response.status, response.data);
				  })
				  .finally(function() {
				  });
			}
			
		// //////////End////////////
			// ////////////////////////END//////////////////////////////////
			// //////////////////////////////////////////Upload
			// File//////////////////////////////////////////////////
			getFolder();
			vm.uploadFile = function() {			
				var formData = new FormData();
				formData.append('tax_file', $('input[name="uploadfileassign"]')[0].files[0]); 
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
		            	monitorMissionAssignService.setData(data[0]);
		            },
		            error: function() {
		                // Handle upload error
		                // ...
		            }
		        });
		    };// function uploadFile
		    
		    vm.trinhDuyet = function(){
				
				if (vm.objTrinhduyet != null && vm.objTrinhduyet.monitorMissionAssignId != null && vm.objTrinhduyet.monitorMissionAssignId != '') {
					var dataItemOne = vm.objTrinhduyet;
					console.log(JSON.stringify(dataItemOne));
					if (dataItemOne.statusCa == 0 || dataItemOne.statusCa == 3) {
						$('#loading').show();
						monitorMissionAssignService.exportMonitorMissionAssign(vm.objTrinhduyet).then(function (data) {
							console.log(JSON.stringify(data));
							var theApprovalData = {
								code: '',
								constructId: '',
								constrCompReMapId: '',
								stringEmployee: '',
								isSign: '',
								path: '',
								nameFile: '',
								// roleId:[],
								// roleName:[]

							};
							theApprovalData.code = dataItemOne.code;
							theApprovalData.constructId = dataItemOne.constructId;
							theApprovalData.constrCompReMapId = dataItemOne.constrCompReMapId;
							theApprovalData.stringEmployee = dataItemOne.adirectorId + "," + dataItemOne.amonitorId;
							theApprovalData.isSign = "theApproval";
							theApprovalData.roleId = [roleNguoiGiao,roleNguoiNhan];
							theApprovalData.roleName = ['Người giao','Người nhận'];
							theApprovalData.path = data.fileName;
							theApprovalData.nameFile = 'BM-TCNT-05.pdf';
							
							
							theApproval.setItem(theApprovalData);
							vm.goTo('THE_APPROVAL');
						}).catch( function(){
							toastr.error(gettextCatalog.getString("Lỗi khi export!"));
							return;
						}).finally(function(){
							$('#loading').hide();
						});
						
					} else {
						toastr.warning("Bản ghi này đã trình duyệt rồi");
					}

				}

			}
		    
		    var objStatus = []
		    
		    vm.goTo = goTo;
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
		    
		    $scope.$on("ChangeStatusApproval", function (event, result) {
				if (result) {
					reloadData();
					vm.showDetail= false;
				}
			});
		    
		    vm.approDTO = {
		    		statusCa:'',
					employeeId: '',
					comments: '',
					constrWorkLogsId: '',
					constrCompReMapId: ''
				}
		    var isAppro = false;
		    vm.approData.statusCa = '1';
		    vm.saveAppro = function saveAppro(){
				if (isAppro == true) {
					vm.approDTO.statusCa = vm.approData.statusCa;
					vm.approDTO.employeeId = Constant.getUser().srvUser.catEmployeeId;
					vm.approDTO.constrWorkLogsId = vm.objTrinhduyet.monitorMissionAssignId;
					vm.approDTO.constrCompReMapId = vm.objTrinhduyet.constrCompReMapId;
					vm.approDTO.constructId = vm.item.constructId;
					monitorMissionAssignService.appro(vm.approDTO).then(function (d) {
						var x = d;
						if (x == '1') {
							toastr.warning("Bạn chưa đến lượt duyệt!");
							
						}
						if (x == '2') {
							toastr.warning("Đã duyệt rồi");
							
						}
						if (x == '4') {
							toastr.success("Từ chối duyệt thành công");
							reloadData();
							vm.showDetail = false;
							vm.isCreateNew = false;
						}
						if (x == '3') {
							toastr.success("Duyệt thành công");
							reloadData();
							vm.showDetail = false;
							vm.isCreateNew = false;
						}
						if (x == '5') {
							toastr.warning("Bạn không được phép phê duyệt");
						}

					}, function (errResponse) {
						// console.error('Error while fetching minout');
					});
					//vm.isPheduyet = false;
					return;
				}
			}
	}
	
	
})();