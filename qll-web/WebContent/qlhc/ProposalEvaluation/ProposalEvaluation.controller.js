(function () {
	'use strict';
	var controllerId = 'ProposalEvaluation';
	angular.module('MetronicApp').controller(controllerId, ProposalEvaluation)

	function ProposalEvaluation($scope, $rootScope, $timeout, Constant, gettextCatalog, kendoConfig, ProposalEvaluation, catProvincesService, catConstrTypeService, $kWindow, WindowService,CommonService, PopupConst, Restangular, RestEndpoint) {
		var vm = this;
		$scope.Constant = Constant;
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.showSearch = false;

		vm.provinces = [];
		vm.constrTypes = [];

		// set model empty -----------------------------------------------------------------
		function getEmptyDataModel() {
			var objReturn = {}

			$(".k-invalid-msg").hide();
			return objReturn;
		}
		vm.item = {};
		vm.workLogs={};
		vm.searchGrid = {};
		vm.checktype = {
			checktype: ''
		};
		////////////// get Employee id ///////////////////
		var getcatEmployeeId = Constant.user.srvUser.catEmployeeId;
		var getcatEmployeeName = Constant.user.srvUser.fullName;
		///////////////////////////////////

		vm.criteria = {
			partnerName: '',
			partnerId: '',
			contractCode: '',
			//dự án
			investProjectName: '',
			projectId: '',
			//
			constrtCode: '',
			constrtName: '',
			constrType: '',
			provinceId: '',
			constructorId: '',
			acceptStartDate: ''
		};

		// set search model empty -----------------------------------------------------------------
		function getSearchEmptyDataModel() {
			return {
				partnerName: '',
				partnerId: '',
				contractCode: '',
				//dự án
				investProjectName: '',
				projectId: '',
				//
				constrtCode: '',
				constrtName: '',
				constrType: '',
				provinceId: '',
				constructorId: '',
				acceptStartDate: ''
				
				
			};
		}

		vm.goToEVA=function(){
			ProposalEvaluation.setCheckGoTo(false);
			$rootScope.$broadcast("Goto.propo.evalua");
			goTo('LIST_ESTIMATE_WORK');
		}
		
		vm.creatmemorywork = function () {
			vm.workLogs.checkBia = 1;
			ProposalEvaluation.checkBia(vm.item.constructId).then(function(res){
				if(res.data=="OK"){
					vm.workLogs.checkBia = 0;
					ProposalEvaluation.setWorkLogs(vm.workLogs);
					goTo('CREATE_MEMORY_WORK');
				}else if(res.data=="NULL"){
					ProposalEvaluation.setWorkLogs(vm.workLogs);
	        		goTo('CREATE_MEMORY_WORK');
				}
        	},function(err){
        		goTo('CREATE_MEMORY_WORK');
        	});
		}
		vm.listconstructionorganizationplan = function () {
			vm.item;
			if (vm.item.constructId == '') {
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			goTo('LIST_WORK_ORGANIZATION_PLAN');
		}

		vm.acceptance = function () {
			vm.item;
			if (vm.item.constructId == '') {
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			goTo('ACCEPTANCE_OF_CONSTRUCTION_JOBS');
		}

		vm.listReportResult = function () {
			vm.item;
			if (vm.item.constructId == '') {
				toastr.warning("Bạn chưa chọn bản ghi nào");
				return;
			}
			goTo('List_Report_Result');
		}

		vm.goTo = goTo;

		/* Handle action client on a menu item */
		function goTo(menuKey) {

			var template = Constant.getTemplateUrl(menuKey);

			postal.publish({
				channel: "Tab",
				topic: "open",
				data: template
			});
			
			if (menuKey == 'DANH_SACH_PHIEU_YC_NGHIEM_THU') {
				$rootScope.$broadcast('tab-close', { menuKey: menuKey, template: template });
			}
		}

		initFormData();

		function role(roleid,array){
			for(var i = 0 ; i <array.length;i++){
				if(roleid == array[i]){					
					return true;
					break;
					
				}
			}
			return false;
		}
		
		function initFormData() {
			
			
			var roleid = Constant.ROLE_ID["employee_roleID_TVTK_CNTK"];
			
			
			var array = {
					CONSTRUCTION_ACCEPTANCE :[Constant.ROLE_ID["employee_roleID_CDT_PTGST"],Constant.ROLE_ID["employee_roleID_CDT_DDPN"],Constant.ROLE_ID["employee_roleID_DT_GDNT"],Constant.ROLE_ID["employee_roleID_DT_PTTC"],Constant.ROLE_ID["employee_roleID_TVTK_DDTV"],Constant.ROLE_ID["employee_roleID_TVTK_CNTK"]]
			}
			
			if (!role(roleid,array.CONSTRUCTION_ACCEPTANCE)){
				$("#construction_acceptance").hide();
			}
			
			
			catConstrTypeService.getAll().then(function (d) {
				vm.constrTypes = d;
				vm.constrTypes.unshift({
					constrTypeId: '',
					constrTypeName: 'Tất cả'
				});
				//vm.criteria.constrType = '';
			}, function (errResponse) {
				console.error('Error while fetching provinces');
			});

			catProvincesService.getAll().then(function (d) {
				vm.provinces = d;
				vm.provinces.unshift({
					provinceId: '',
					provinceName: 'Tất cả'
				});
				//vm.criteria.provinceId = '';
			}, function (errResponse) {
				console.error('Error while fetching provinces');
			});

			fillDataTable([]);

		}

		// Su kien cho nut tim kiem
		vm.search = search;
		function search() {
			vm.showDetail = false;
			if (!vm.showSearch) {
				vm.showSearch = true;
				vm.criteria = getSearchEmptyDataModel();
			} else {
				vm.showSearch = false;
			}
		}
		vm.resetSearch = function () {
			vm.criteria = getSearchEmptyDataModel();
			vm.ProposalEvaluation.dataSource.query({
				page: 1,
				pageSize: 20
			});
			/*			ProposalEvaluation.getAllandSearch(vm.criteria).then(
			function (d) {
			refreshGrid(d.plain());
			},
			function (errResponse) {
			console.error('Error while fetching minout');
			}
			);*/
		}
		vm.doSearch = function () {

			//fillDataTable([]);
			//vm.ProposalEvaluation.dataSource.read();
			vm.ProposalEvaluation.dataSource.query({
				page: 1,
				pageSize: 20
			});
			/*			ProposalEvaluation.getAllandSearch(vm.criteria).then(
			function (d) {
			fillDataTable(d);
			refreshGrid(d.plain());
			},
			function (errResponse) {
			console.error('Error while fetching minout');
			}
			);*/
		}

		function chkSelectAll(item) {
			var grid = vm.ProposalEvaluation;
			chkSelectAllBase(vm.chkAll, grid);
		}

		function detail() {
			if (vm.ProposalEvaluation.select().length > 0) {
				vm.showDetail = true;
			} else {
				toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
			}
		}

		// Xóa nhiều
		function multiDelete() {
			var selectedRow = [];
			var grid = vm.ProposalEvaluation;
			grid.table.find("tr").each(function (idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);

				if (checkbox.is(':checked')) {
					// Push id into selectedRow
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(item);
					console.log('dataItem ----');
					console.log(dataItem.minoutId);
					selectedRow.push(dataItem.minoutId);
				}
			});

			if (selectedRow.length == 0) {
				toastr.warning(message.recordRequired);
				return;
			}

			console.log(selectedRow);
			if (confirm('Xác nhận xóa')) {
				ProposalEvaluation.deleteList(selectedRow).then(function () {
					toastr.success(message.deleteSuccess);
					initFormData();
				}, function (errResponse) {
					if (errResponse.status == 302) {
						toastr.error("Bản ghi đang được sử dụng");
					} else {
						toastr.error(message.deleteError);
					}
				});
			}
		}

		// Xoa thong tin
		vm.remove = remove;
		function remove() {}

		// Sư kien cho nut luu thong tin chung
		vm.save = function save() {
			vm.doSearch();
			
		}

		// Su kien cho nut lam moi
		vm.refresh = refresh;
		function refresh() {
			if (vm.showDetail) {
				vm.shipmentDetail = getEmptyDataModel();
			} else {
				ProposalEvaluation.doSearch(vm.criteria).then(
					function (d) {
					refreshGrid(d.plain());
				},
					function (errResponse) {
					console.error('Error while fetching minout');
				});
			}
		}

		// Hien thi danh sach
		var record = 0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					editable: false,
					resizable:true,
	                reorderable: true,
					dataSource: {

						serverPaging: true,

						transport: {
							read: {
								url: Constant.BASE_SERVICE_URL + "VConstructionHcqtRsService/vConstructionHcqt/getAllandSearch",
								contentType: "application/json; charset=utf-8",
								type: "POST"
							},
							parameterMap: function (options, type) {
                                  //  vm.criteria.employeeId = Constant.user.srvUser.catEmployeeId;
								    vm.criteria.page = options.page
									vm.criteria.pageSize = options.pageSize
									vm.criteria.checktype = vm.checktype.checktype

									return JSON.stringify(vm.criteria)

							}
						},
						schema: {

							total: function (response) {
								console.log(response);
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								console.log(response);
								return response.data; // data is returned in the "data" field of the response
							},
						},

						pageSize: 20

					},
					pageable: {
						refresh: true,
						pageSizes: [10, 15, 20, 25],
						messages: {
			                display: " {0}-{1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang"

			            }
					},

					dataBinding: function () {
						record = (this.dataSource.page() - 1) * this.dataSource.pageSize();
					},
					
					dataBound: function(e) {
						var grid = $("#proposalEvaluationGrid").data("kendoGrid");
						var selected = false;
						$.each(grid.table.find("tr"),function(){
			                 var model = grid.dataItem(this);
			                 if (!selected) {
			                	 grid.select("tr:eq(0)");
			                	 selected = true;
			                 }
			             });
	                },
	                
					change: onChange,
					noRecords: true,
					messages: {
						noRecords: gettextCatalog.getString("Không có kết quả nào")
					},
					columns: [{
							title: "STT",
							template: function (item) {
								return ++record;
							},
							width: 50,
							headerAttributes: {
								style: "text-align:center;font-weight: bold;"
							},
							attributes: {
								style: "text-align:center;"
							}
						}, {
							title: gettextCatalog.getString("Tỉnh thành"),
							field: "provinceName",
							width: 130,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}, {
							title: gettextCatalog.getString("Dự án"),
							field: "investProjectName",
							width: 200,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}, {
							title: gettextCatalog.getString("Đối tác"),
							field: "constructorName",
							width: 200,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}, {
							title: gettextCatalog.getString("Số hợp đồng"),
							field: "contractCode",
							width: 180,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}, {
							title: gettextCatalog.getString("Mã công trình"),
							field: "constrtCode",
							width: 170,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}, {
							title: gettextCatalog.getString("Tên công trình"),
							field: "constrtName",
							width: 200,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}, {
							title: gettextCatalog.getString("Loại công trình"),
							field: "constrTypeName",
							width: 150,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}, {
							title: gettextCatalog.getString("Trạng thái công trình"),
							field: "statusName",
							width: 165,
							headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							}
						}
					]
				});
		}
		$("#proposalEvaluationGrid").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");

		// Su kien khi click vao mot hang cua danh sach
		vm.onChange = onChange;
		function onChange() {
			if (vm.ProposalEvaluation.select().length > 0) {
				var tr = vm.ProposalEvaluation.select().closest("tr");
				var dataItem = vm.ProposalEvaluation.dataItem(tr);
				vm.item = dataItem;
				vm.item.getcatEmployeeId = getcatEmployeeId;
				vm.item.getcatEmployeeName = getcatEmployeeName;
				ProposalEvaluation.setItem(vm.item);
				CommonService.setItem(vm.item);
				$rootScope.$broadcast("ProposalEvaluation.detail", vm.item);

			}
		}

		// Lam moi lai danh sach
		function refreshGrid(d) {
			var grid = vm.ProposalEvaluation;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}

		vm.onRowChange = onRowChange;
		function onRowChange(dataItem, popupId) {
			switch (popupId) {
			case 'Partner':
				vm.criteria.partnerName = dataItem.partnerName;
				break;
			case 'Project':
				vm.criteria.investProjectName = dataItem.name;
				break;

			}
		}
		vm.onSave = onSave;
		function onSave(popupId) {}
		vm.onCancel = onCancel;
		function onCancel(popupId) {}

		vm.showPartnerNameForm = showPartnerNameForm;
		function showPartnerNameForm() {
			ProposalEvaluation.openPartners($scope).then(function (result) {
				var templateUrl = 'views/popup/gridViewPartner.html';
				var title = 'Đối tác';
				vm.PartnerGridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						dataSource: result.plain(),
						change: onChange,
						noRecords: true,
						messages: {
							noRecords: gettextCatalog.getString("Không có kết quả nào")
						},
						columns: [{
								title: gettextCatalog.getString("Tên đối tác"),
								field: "partnerName",
								width: 100,
							}, /* {
							title: gettextCatalog.getString("Mã trạm dự kiến"),
							field: "stationCodeExpected",
							width: 100
							},*/
							{
								title: gettextCatalog.getString("Địa chỉ"),
								field: "address",
								width: 100
							}, {
								title: gettextCatalog.getString("Điện thoại"),
								field: "phone",
								width: 100
							}, {
								title: gettextCatalog.getString("Fax"),
								field: "fax",
								width: 100
							}, {
								title: gettextCatalog.getString("Mã số dự kiến"),
								field: "taxCode",
								width: 100
							}
						]
					});

				CommonService.openCustomPopup(templateUrl, title, vm.PartnerGridOptions, vm, PopupConst.ProposalEvaluation['Partner']);

			});
		}

		vm.showProjectNameForm = showProjectNameForm;
		function showProjectNameForm() {
			ProposalEvaluation.openProject($scope).then(function (result) {
				var templateUrl = 'views/popup/gridViewProject.html';
				var title = 'Dự án';
				vm.ProjectGridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						dataSource: result.plain(),
						change: onChange,
						noRecords: true,
						messages: {
							noRecords: gettextCatalog.getString("Không có kết quả nào")
						},
						columns: [{
								title: gettextCatalog.getString("Mã dự án"),
								field: "code",
								width: 100,
							}, {
								title: gettextCatalog.getString("Tên dự án"),
								field: "name",
								width: 100
							}, {
								title: gettextCatalog.getString("Tình trạng dự án"),
								field: "statusCode",
								width: 100
							}
						]
					});

				CommonService.openCustomPopup(templateUrl, title, vm.ProjectGridOptions, vm, PopupConst.ProposalEvaluation['Project']);
			});
		}

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
			}, {
				title: "Tên đối tác",
				field: "partnerName",
				width: 120
			},
			/*		{
			title: "Mã trạm dự kiến",
			field: "stationCodeExpected",
			width: 120
			}, */
			{
				title: "Địa chỉ",
				field: "address",
				width: 120
			}, {
				title: "Điện thoại",
				field: "phone",
				width: 120
			}, {
				title: "Fax",
				field: "fax",
				width: 120
			}
			/*		, {
			title: "Mã số dự kiến",
			field: "taxCode",
			width: 120
			}*/
		];

		vm.gridCustomProject = [{
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

			}, {
				title: "Mã dự án",
				field: "code",
				width: 120
			}, {
				title: "Tên dự án",
				field: "name",
				width: 120
			}
		, {
				title: "Tình trạng dự án",
				field: "statusCode",
				template: function ($scope) {
					if ($scope.statusCode == 0) {
						return "Chờ thẩm định";
					} else if ($scope.statusCode == 1) {
						return "Chờ phê duyệt";
					} else if ($scope.statusCode == 2) {
						return "Được phê duyệt";
					} else if ($scope.statusCode == 3) {
						return "Không được phê duyệt";
					} else {
						return "Chưa xác định";
					}
				},
				width: 120
			}
		];

		//Nghiem thu hoan thanh cong trinh
		vm.constracceptance = function fetchWorkItem() {
			vm.workitem = {
				checkstatus: true
			};
			vm.workitem.constructionId = vm.item.constructId;
			ProposalEvaluation.getWorkItem(vm.workitem).then(function (d) {

				if (d.plain().length > 0) {
					vm.goTo('INSP_INCOMPLETE');
				} else {
					vm.goTo('INSP_CONGTRINH');
				}

			}, function (errResponse) {
				console.error('Error while fetching crevaluation');
			})
		}
		//check tham dinh
		vm.proposalCheck = function proposalCheck() {
			vm.checkProposal = {};
			vm.checkProposal.constructId = vm.item.constructId;
			ProposalEvaluation.getcheckEvaluate(vm.checkProposal).then(function (d) {

				if (d.plain().length > 0 && d.plain()[0].statusCa == 2) {
					vm.goTo('ESTIMATES_AB');
				} else {
					toastr.warning("Chưa thẩm định không được quyết toán !");
				}

			}, function (errResponse) {
				console.error('Error while fetching crevaluation');
			})
			//vm.goTo('ESTIMATES_AB');
			
		}
		
		//Xoa textbox doi tac
		vm.removeProject = function (){
			vm.criteria.projectId =  '';
			vm.criteria.investProjectName =  '';
		}
		
		vm.removePartner = function (){
			vm.criteria.partnerName =  '';
			vm.criteria.partnerId =  '';
		}

		//
		vm.importCTHD= function(){
			if (vm.ProposalEvaluation.select().length > 0) {
				WindowService.open({
	                options: {
	                    modal: true,
	                    title: "Import chiết tính hợp đồng",
	                    visible: false,
	                    width: '650',
	                    height: '200',
	                    actions: ["Minimize", "Maximize", "Close"],
	                    open: function() {
	                        this.wrapper.children('.k-window-content').addClass("fix-footer");
	                    }
	                },
	                templateUrl: "qlhc/ProposalEvaluation/chietTinhCVHD.html"

	            });
			} else{
				toastr.warning("Bạn cần chọn một công trình !");
			}
		}
		
		vm.importCVHD= function(){
			if (vm.ProposalEvaluation.select().length > 0) {
				WindowService.open({
	                options: {
	                    modal: true,
	                    title: "Import công việc hợp đồng",
	                    visible: false,
	                    width: '650',
	                    height: '200',
	                    actions: ["Minimize", "Maximize", "Close"],
	                    open: function() {
	                        this.wrapper.children('.k-window-content').addClass("fix-footer");
	                    }
	                },
	                templateUrl: "qlhc/ProposalEvaluation/CVHĐ.html"

	            });
			} else{
				toastr.warning("Bạn cần chọn một công trình !");
			}
		}
		
		
		
		
		 vm.keydown = function($event){
			 if (event.keyCode == 13) {
				 vm.doSearch();
			    }
		        
		    }
	}
})();
