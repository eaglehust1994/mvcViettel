(function() {
	'use strict';
	var controllerId = 'drawingsListController';
	angular.module('MetronicApp').controller(controllerId,
			drawingsListController).directive('fdInput', [function() {
		        return {
		            link: function(scope, element) {
		                element.on('change', function(evt) {
		                    var files = evt.target.files;
		                    var item = scope.vm.createDrawingGrid.dataItem($(evt.target).closest("tr"));
		                    item.fileUpload = files[0].name;
		                    item.file = files[0];
		                });
		            }
		        };
		    }]);

	/* @ngInject */
	function drawingsListController($scope, $rootScope, $timeout,
			Constant, gettextCatalog, kendoConfig, $kWindow, CommonService,
			PopupConst, Restangular, RestEndpoint, ProposalEvaluation, drawingsListService,theSignCA) {
		var vm = this;
		$rootScope.showCreate = false;
		$rootScope.drawItem = {};
		vm.onChange = onChange;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.drawingsListService = drawingsListService;
		vm.theSignCA = theSignCA;
		vm.folder = '';
		vm.focusprior = false;
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		drawingsListService.openCompletionDrawingForm(vm.item).then(function(d) {
			refreshGrid(d.plain());
		}, function() {
			console.error('Error while fetching object type');
		});
		       
		vm.mCompletionDrawing ={
		};
		
		vm.bean = {};
		// ////////////////////////Combobox thanh phan tham
		// gia//////////////////////////////////
		vm.role=[];
		var totalListEmployee = [], MonitorId = [], DirectorId = [], CreatorId = [];
		vm.amonitorId = [];
		vm.bdirectorId = [];
		vm.creator = [];
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
		// ////////////////////////END//////////////////////////////////
		// //////////////////////////////////////////Upload
		// File//////////////////////////////////////////////////
		getFolder();
		vm.uploadFile = function(file) {	
			
			var formData = new FormData();
			formData.append('tax_file', file); 
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
	            	console.log(data);
	                drawingsListService.setData(data[0]);
	            },
	            error: function() {
	                // Handle upload error
	                // ...
	            }
	        });
	        
	    }; // function uploadFile
		// /////////////////////////////////////Handle
		// Event/////////////////////////////////////////////
		vm.initFormComplete = initFormComplete;
		vm.initFormComplete();
		function initFormComplete(){
			drawingsListService.openCompletionDrawingForm(vm.item).then(function(d) {
				refreshGrid(d.plain());
				vm.object = d.plain();
			}, function() {
				console.error('Error while fetching object type');
			});
		};
		$( document ).ready(function() {
		drawingsListService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
			totalListEmployee = data.plain();
			drawingsListService.getRoleId().then(function(data) {
//				vm.role = data;
				for (var i = 0; i < totalListEmployee.length; i++) {
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_GSTC"]){ //10
						MonitorId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_DDPN"]){ //3
						DirectorId.push(totalListEmployee[i]);
					}
					if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_KTTC"]){ //5
						CreatorId.push(totalListEmployee[i]);
					}
				}
				vm.amonitorId = MonitorId;
				vm.bdirectorId = DirectorId;
				vm.creator = CreatorId;
				if(MonitorId.length > 0) {
					vm.bean.amonitorId = MonitorId[0].userId;
				}
				if(DirectorId.length > 0) {
					vm.bean.bdirectorId = DirectorId[0].userId;
				}
				if(CreatorId.length > 0) {
				vm.bean.creatorId = CreatorId[0].userId;
				}
			})
			.catch(function(data, status) {
				console.error('getRoleId error', response.status, response.data);
			});
		  })
		  .catch(function(data, status) {
		    console.error('Gists error', response.status, response.data);
		});
		});
		function getFolder() {
			drawingsListService.getCompletionDrawingFolder().then(function(data) {
				vm.folder = data.folder;
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
			  .finally(function() {
			  });
		}

		/* Handle action client on a menu item */
		vm.goTo = goTo;
	    function goTo(menuKey) {
			
			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel : "Tab",
					topic   : "open",
					data    : template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
			
		}
		vm.goBack = function(){
			$rootScope.showCreate = false;
		};
		
		$rootScope.reload = reloadData;
		function reloadData(){
			drawingsListService.openCompletionDrawingForm(vm.item).then(function(d) {
				refreshGrid(d.plain());
			}, function() {
				console.error('Error while fetching object type');
			});
		};
		
		function reloadDataCreate(){
			var data = [{drawCode : "", drawName : "" }];
			refreshCreateGrid(data);
		};
		$scope.$on("ChangeStatus", function(event, result){ 
		      if(result){
		    	  reloadData();
		      }
		     });
		 $scope.$on("ProposalEvaluation.detail", function (event, item) {
	        	if(item != undefined){
	        		vm.item = item;
	        		drawingsListService.openCompletionDrawingForm(vm.item).then(function(d) {
	    				refreshGrid(d.plain());
	    				vm.object = d.plain();
	    				
	    			}, function() {
	    				console.error('Error while fetching object type');
	    			});
	        	}else{
	        		console.error("không nhận được dữ liệu!");
	        	}
	        });     
		// Ky CA
		vm.theSign = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path: '',
				nameFile: ''
			};
		
		function onChange(){
			if (vm.drawingListGrid.select().length > 0){
				var tr = vm.drawingListGrid.select().closest("tr");
    			var dataItem = vm.drawingListGrid.dataItem(tr);
    			
    			vm.completiondrawing = dataItem;
    			vm.completiondrawing.contractId = vm.item.contractId;
    			drawingsListService.setItem(vm.completiondrawing);
    			drawingsListService.setcompletionDrawingId(vm.completiondrawing.completionDrawingId);
    			drawingsListService.setconstructId(vm.completiondrawing.constructId);
    			
    			vm.theSign.code = vm.completiondrawing.drawCode;
				vm.theSign.constructId = vm.completiondrawing.constructId;
				vm.theSign.constrCompReMapId = vm.completiondrawing.constrCompReMapId;
				//role 5-10-3
				vm.theSign.roleId = [Constant.ROLE_ID["employee_roleID_DT_KTTC"],Constant.ROLE_ID["employee_roleID_CDT_GSTC"],Constant.ROLE_ID["employee_roleID_DT_GDNT"]];   
				vm.theSign.roleName = ["Người lập","Giám sát thi công","Đại diện pháp luật của nhà thầu thi công"];
				vm.theSign.stringEmployee =vm.completiondrawing.creatorId+ ','+vm.completiondrawing.amonitorId+','+ vm.completiondrawing.bdirectorId;
				vm.theSign.isSign='theSignCA';
				
				vm.theSign.path = dataItem.documentPath;
				vm.theSign.nameFile = dataItem.documentName;
				
				theSignCA.setItem(vm.theSign);
				$rootScope.$broadcast("drawingList", vm.theSign);
				vm.focusprior = true;
    			
			}
		}
		vm.signCA = function() {
		    if(vm.theSign.constrCompReMapId==''){
		     toastr.warning("Bạn chưa chọn bản ghi nào");
		     return ;
		    }
		    if(vm.completiondrawing.statusCa != 0){
		     toastr.warning("Bản ghi đã trình ký");
		     return ;
		    }
		    goTo('THE_SIGN_CA');
		   };
		function onChangeCreate(){
		}
		
		
		function refreshGrid(d) {
			var grid = vm.drawingListGrid;
			if(grid){
			grid.dataSource.data(d);
			grid.refresh();
			}
		};
		
		function refreshCreateGrid(d) {
			var grid = vm.createDrawingGrid;
			if(grid){
			grid.dataSource.data(d);
			grid.refresh();
			}
		};
		
		function reloadDropDown(){
			drawingsListService.getListEmployeeByConstruction(vm.item.constructId).then(function(data) {
				totalListEmployee = data.plain();
				drawingsListService.getRoleId().then(function(data) {
//					vm.role = data;
					for (var i = 0; i < totalListEmployee.length; i++) {
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_CDT_GSTC"]){ //10
							MonitorId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_GDNT"]){ //3
							DirectorId.push(totalListEmployee[i]);
						}
						if(totalListEmployee[i].roleid == Constant.ROLE_ID["employee_roleID_DT_KTTC"]){ //5
							CreatorId.push(totalListEmployee[i]);
						}
					}
					vm.amonitorId = MonitorId;
					vm.bdirectorId = DirectorId;
					vm.creator = CreatorId;
					if(MonitorId.length > 0) {
						vm.bean.amonitorId = MonitorId[0].userId;
					}
					if(DirectorId.length > 0) {
						vm.bean.bdirectorId = DirectorId[0].userId;
					}
					if(CreatorId.length > 0) {
					vm.bean.creatorId = CreatorId[0].userId;
					}
				})
				.catch(function(data, status) {
					console.error('getRoleId error', response.status, response.data);
				});
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			});
		}
		vm.addDraw = addDraw;
		function addDraw(){
			
			$rootScope.showCreate = true;
			$rootScope.showEdit = true;
			reloadDataCreate();
			reloadDropDown();
		}
		vm.editDraw = editDraw;
		function editDraw(){
			var grid = vm.drawingListGrid;
			var tr = grid.select().closest("tr");
			var dataItem = vm.drawingListGrid.dataItem(tr);
			vm.editdraw = dataItem;
			console.log(vm.editdraw);
			if(vm.editdraw == null){
				toastr.warning("Bạn cần chọn một bản ghi trước");
				$rootScope.hideSaveDraw = false;
				$rootScope.hideSignCA = false;
				return;
			}else{
			if(vm.completiondrawing.statusCa != 0 && vm.completiondrawing.statusCa != 3){
				/*toastr.warning("Không được sửa những bản ghi đang trình ký hoặc đã ký");*/
			     $rootScope.$broadcast("CompletionDrawingEdit", vm.editdraw);
					$rootScope.showCreate = true;
					$rootScope.showEdit = false;
					$rootScope.hideSaveDraw = true;
					$rootScope.hideSignCA = true;
					if(vm.completiondrawing.createdUserId != Constant.user.srvUser.userId){
						toastr.warning("Bạn không có quyền sửa bản ghi này !");
					}
					return;
			    }else{
			if(vm.editdraw != null){
			
			$rootScope.$broadcast("CompletionDrawingEdit", vm.editdraw);
			
			$rootScope.showCreate = true;
			$rootScope.showEdit = false;
			$rootScope.hideSaveDraw = false;
			$rootScope.hideSignCA = false;
			if(vm.completiondrawing.createdUserId != Constant.user.srvUser.userId){
				toastr.warning("Bạn không có quyền sửa bản ghi này !");
			}}
			else{
				toastr.warning("Bạn cần chọn một bản ghi trước");
				return;
			}
			    }}		
		}
		vm.deleteOne = deleteOne;
		
		function deleteOne() {
			if(vm.isCreateNew && vm.showDetail){
        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
        		return;
        	}
			var grid = vm.drawingListGrid;
			
			// Check select
        	if (grid.select() == null || grid.select().length == 0) {
        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
        		return;
        	}
        	var grid = vm.drawingListGrid;
			var tr = grid.select().closest("tr");
			var dataItem = grid.dataItem(tr);
			if(vm.completiondrawing.statusCa != 0){
			     toastr.warning("Không thể xóa bản ghi đã trình ký");
			     return ;
			    }else{
			if (vm.item.constrtCode != '' && grid.select().length > 0 && confirm('Xác nhận xóa')) {
				drawingsListService.removeConstrRecordMap(dataItem.constrCompReMapId);
				drawingsListService.updateIsActive(dataItem.completionDrawingId).then(function(){
            		toastr.success("Xóa thành công!");
            		reloadData();
	            }, function(errResponse) {
	            	if (errResponse.status == 302){
	            		toastr.error(gettextCatalog.getString("Bản ghi đang được sử dụng!"));
	            	}else{
	            		console.error("Có lỗi xảy ra trong quá trình xóa!");
	            	}
				});
			}}
		}
		vm.multiDelete = multiDelete;
		// delete multiple record
        function multiDelete() {
        		var selectedRow = [];
        		var listID = [];
        		var noDel=0;
        		var noDel1= 0;
    			var grid = vm.drawingListGrid;
    			
    			grid.table.find("tr").each(function(idx, item) {
    				var row = $(item);
    				var checkbox = $('[name="gridcheckbox"]', row);
    				
    				if (checkbox.is(':checked')) {
    					// Push id into selectedRow
    					var dataItem = grid.dataItem(item);
    					selectedRow.push(dataItem);
    				}
    			});
    			
    			if (selectedRow.length == 0){
    				toastr.warning("Bạn chưa chọn bản ghi nào để xóa");
    				return;
    			}
    			
    			
    				for (var i = 0; i < selectedRow.length; i++) {
						if (selectedRow[i].statusCa === 0 || selectedRow[i].statusCa === 3) {
							if(selectedRow[i].createdUserId === Constant.user.srvUser.userId){
								listID.push(selectedRow[i].completionDrawingId);
								noDel++;
								noDel1++;
							}else if(noDel == 0){
								toastr.warning("Bạn không phải người tạo, bạn không có quyền xóa bản ghi đang chọn!");
								noDel++;
								noDel1++;
							}
						}else {
							if(noDel1 ==0){
								toastr.warning("Không thể xóa bản ghi đang trình ký hoặc đã ký");
								noDel1++;
							}						
						}
					}
    				
    				if(listID.length > 0){
    					if (selectedRow.length > 0 && confirm('Xác nhận xóa')) {
    					drawingsListService.updateIsActive(listID).then(function() {
    			    		toastr.success("Đã xóa thành công");
    			    		//drawingsListService.deleteFile(selectedRow);
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
        
     // Check box
		vm.chkSelectAll = function(item) {
	    	var grid = vm.drawingListGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    };
		
		vm.chkSelectAllCreate = function(item) {
	    	var grid = vm.createDrawingGrid;
	    	chkSelectAllBase(vm.chkAllCreate, grid);
	    };
        
        vm.add = add;
		function add(){
			var grid = $("#createDrawingMainGrid").data("kendoGrid");		
			grid.dataSource.insert();
		}
		
		vm.remove = remove;
		function remove(){
			var grid = $("#createDrawingMainGrid").data("kendoGrid");
			$("#createDrawingMainGrid").find("tr:gt(0)").find("input:checked").each(function(){
	            grid.removeRow($(this).closest("tr"));
	          });
	          
			}
		
		vm.removeOne = removeOne;
		function removeOne(){
			var grid = $("#createDrawingMainGrid").data("kendoGrid");
			var tr = grid.select().closest("tr");
			grid.removeRow(tr);
			
			}
		
		vm.checkex = function(list){
			for(var i=0;i<list.length;i++){
				if('pdf' != list[i].split('.').pop()){
					toastr.warning("File import đính kèm phải là file PDF");
					return;
				}
		}
		}
		vm.saveAll = saveAll;
		function saveAll(){
			if(vm.validator.validate()){
			if($rootScope.showCreate == true && $rootScope.showEdit == true){
			vm.flag = true;
			var count = 0;
			var selectedRow = [];
			
			var grid = vm.createDrawingGrid;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);

					// Push id into selectedRow
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(item);
					vm.mCompletionDrawing = dataItem;			
					if(dataItem.file && dataItem.file.name.split('.').pop() == 'pdf'){
						vm.uploadFile(dataItem.file);};
					vm.mCompletionDrawing.documentPath = '';
					vm.mCompletionDrawing.documentPath = drawingsListService.getData();
					vm.mCompletionDrawing.amonitorId = vm.bean.amonitorId;	
					vm.mCompletionDrawing.bdirectorId = vm.bean.bdirectorId;
					vm.mCompletionDrawing.creatorId = vm.bean.creatorId;
					vm.mCompletionDrawing.constructId = vm.item.constructId;
					vm.mCompletionDrawing.createdUserId = Constant.getUser().srvUser.userId;
					vm.mCompletionDrawing.isActive = 1;
					selectedRow.push(vm.mCompletionDrawing);
					
							
			});

			if (selectedRow.length == 0) {
				toastr.warning("Bạn cần chọn một bản ghi trước");
				return;
			}
			for (var i = 0; i < selectedRow.length; i++){
				if($("#createDrawingMainGrid").data("kendoGrid").dataSource.view()[i].fileUpload == null){
					toastr.warning("Chưa đầy đủ thông tin");
					return;
				}
				else if('pdf' == $("#createDrawingMainGrid").data("kendoGrid").dataSource.view()[i].fileUpload.split('.').pop()){
				selectedRow[i].utilAttachedDocuments = [];
    			selectedRow[i].utilAttachedDocuments.push({documentName : $("#createDrawingMainGrid").data("kendoGrid").dataSource.view()[i].fileUpload});
				if(checkData(selectedRow[i])){
					count += 1;
				}}else{
					toastr.warning("File import đính kèm phải là file PDF");
					return;
				}
			}
			if(vm.mCompletionDrawing.amonitorId != null && vm.mCompletionDrawing.bdirectorId != null && vm.mCompletionDrawing.creatorId != null){
				for (var i = 0; i < selectedRow.length; i++) {
					if(count != selectedRow.length){
						vm.flag = false;
						/* toastr.warning("Phải điền đầy đủ thông tin"); */
						break;
					}
					selectedRow[i].utilAttachedDocuments = [];
	    			selectedRow[i].utilAttachedDocuments.push({documentName : $("#createDrawingMainGrid").data("kendoGrid").dataSource.view()[i].fileUpload});
				}	
				if(count == selectedRow.length){
					drawingsListService.createCompletionDrawing(selectedRow).then(function() {
						toastr.success("Lưu thành công ");	
			    		reloadData();
			     }, function(errResponse) {			    	 
			    	 if (errResponse.status == 302){
			    		 toastr.error("Bản ghi đang được sử dụng");
			    	 }else{
			    		 toastr.error("Lỗi khi lưu");
			    	 } 			    	 
			     });}	    					    				
			}else{
				toastr.error(gettextCatalog.getString("Chưa hoàn thành thành phần ký"));
				vm.flag = false;
			}
			if(vm.flag){				
			$rootScope.showCreate = false;}
			}
			}
		}
		
		
		
		
		function checkData(data){
			if(data.drawName){
				if(data.drawCode){
					if(data.utilAttachedDocuments[0].documentName){
						return true;
					}else{
						toastr.warning("Chưa có file upload");
						return false;
					}
				}else{
					toastr.warning("Chưa có mã bản vẽ");
					return false;
				}
			}else{
				toastr.warning("Chưa có tên bản vẽ");
				return false;
			}
		}
		
		function fileUploadEditor(container, options) {
            $('<input type="file" fd-input accept=".pdf"/>')
                .appendTo(container);
        }
		vm.handleCheck = function(item){
			if(document.getElementById("checkalllistdraw").checked == true){
				document.getElementById("checkalllistdraw").checked = false;
			}
			var grid = vm.drawingListGrid;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				
				if (checkbox.is(':checked')) {
					vm.focusprior = false;
					
				}
			});
		}
		// ////////////////////////////////////////////////Grid
		// Area//////////////////////////////////////////

			vm.gridDrawingListOptions = kendoConfig.getGridOptions({
				autoBind : true,
				dataSource : [],
				change : onChange,
				editable: false,
				noRecords : true,
				navigatable: true,
				resizable: true,
				pageable: {
		            pageSizes: true,
		            refresh: false,
		            pageSize: 20,
		            messages: {
		                display: " {0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
		         },
				messages : {
					noRecords : gettextCatalog
							.getString("Không có dữ liệu trong trang")
				},
				columns : [ {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>STT</b>"),
					field : "rowNumber",
					template: dataItem => $("#drawingListMainGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					width : 90,
					headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				}, {
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			        width: 35,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Mã bản vẽ</b>"),
					field: "drawCode",
					width : 150
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Tên bản vẽ</b>"),
					field : "drawName",
					width : 150
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Mã công trình</b>"),
					field : "constrtCode",
					width : 150
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Mã hợp đồng</b>"),
					field : "contractCode",
					width : 150
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Tên hợp đồng</b>"),
					field : "contractName",
					width : 150
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Trạng thái kí CA</b>"),
					field : "statusCa",
					attributes: { style: "text-align:left;", class:"statusColumn"},
					template: function($scope){
						if($scope.statusCa == 0){
							return "Soạn thảo";
						}else if($scope.statusCa == 1){
							return "Trình ký";
						}else if($scope.statusCa == 2){
							return "Đã ký";
						}else if($scope.statusCa == 3){
							return "Từ chối ký";
						}else{return "Không xác định";}
					},
					width : 150
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>File mềm</b>"),
					field : "documentPath",
					template : function(dataItem) {
						return "<a href='"+Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.documentPath + "'>" + dataItem.documentName + "</a>";
					},
					width : 150
				}]
			});
		// };
			/*$("#drawingListMainGrid").kendoTooltip({
				filter: "td",
	            content: function (e) {
	              var target = e.target; // element for which the tooltip is shown
	              return $(target).text();
	            }
		    }).data("kendoTooltip");*/
		
		/* function fillDataCreateTable(data) { */
		var data = [{drawCode : "", drawName : "" }];
		vm.dataSource = new kendo.data.DataSource({            
            schema: {
                model: {
                	fields: {
                    	stt: {editable: false},
                		drawCode: { type: "string" },
                    	drawName: { type: "string" },
                    	fileUpload: { field: "fileUpload" },
                    }
                }
            },
            // serverPaging: true,
            pageSize: 20,
            
        });
		
			vm.gridCreateDrawingOptions = kendoConfig.getGridOptions({
				autoBind: true,
				dataSource: vm.dataSource,
				noRecords : true,
				sortable: false,
				navigatable: true,
				edit: function(e) {
			         e.container.find("input[name=drawCode]").attr("maxlength", 200);
			         e.container.find("input[name=drawName]").attr("maxlength", 500);
			     },
				pageable: {
		            pageSizes: true,
		            refresh: false,
		            pageSize: 20,
		            messages: {
		                display: " {0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"

		            }
		         },
				messages : {
					noRecords : gettextCatalog
							.getString("Không có dữ liệu trong trang")
				},
				
				columns : [ {
					title : "<b style='font-weight: 600;color: #000000;'>STT</b>",
					template: dataItem => $("#createDrawingMainGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					width : 70,
					field:"stt",
					editable: false,
					headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
					
				}, {
					title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAllCreate();' ng-model='vm.chkAllCreate' checked='checked'/>",
					template: "<input type='checkbox' name='gridcheckbox' />",
					// field: "check",
					width : 35,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Mã bản vẽ</b>"),
					field: "drawCode",
					width : 150,
					headerAttributes: {style:"text-align:center;"},
				}, {
					title : gettextCatalog.getString("<b style='font-weight: 600;color: #000000;'>Tên bản vẽ</b>"),
					field: "drawName",
					width : 150,
					nullable: false,
					headerAttributes: {style:"text-align:center;"},
				}, {
					field: "fileUpload",
	                editor: fileUploadEditor,
	                title: "<b style='font-weight: 600;color: #000000;'>File mềm</b>",
					width : 200,
					headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:right;"},
				}],
				
	            
			});
		/* }; */
		
		// //////////////////////////////////////////Download
		// File//////////////////////////////////////////////
		vm.downloadFile = function(){
			
			var list = [];
			var list1 = [];
			 var grid = vm.drawingListGrid;
				grid.table.find("tr").each(function(idx, item) {
					var row = $(item);
					var checkbox = $('[name="gridcheckbox"]', row);
					
					if (checkbox.is(':checked') && vm.focusprior == false) {
						var tr = grid.select().closest("tr");
						var dataItem = grid.dataItem(item);
						
						list.push(dataItem.completionDrawingId);
						list1.push(dataItem);
					}
				});
				if (list.length == 0 && vm.focusprior == false) {
					toastr.warning("Bạn cần tích chọn hoặc chọn một bản ghi trước");
					return;
				};	
					$('#loading').show();
					if(vm.focusprior == true){
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + vm.completiondrawing.documentPath;
						$('#loading').hide();
					}
					if(vm.focusprior == false && list.length >1){
					drawingsListService.downloadFileZip(list).then(function(d) {
						data = d.plain();
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					}).catch( function(){
						toastr.error(gettextCatalog.getString("Lỗi khi export!"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
					}
					if(vm.focusprior == false && list1.length ==1){
						window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + list1[0].documentPath;
						$('#loading').hide();
					}
		 }
		
	}
})();