(function() {
	'use strict';

	var controllerId = 'partnerHrListController';

	angular.module('MetronicApp').controller(controllerId, partnerHrListController);

	/* @ngInject */
	function partnerHrListController($scope, $rootScope, $timeout, Constant, kendoConfig, gettextCatalog, $kWindow, $q , partnerHrService, CommonService,Restangular) {
		var vm = this;
		$scope.pattern = new RegExp('^[0-9a-zA-Z,. \u0027\u00c0\u0027\u002c\u0027\u00c1\u0027\u002c\u0027\u00c2\u0027\u002c\u0027\u00c3\u0027\u002c\u0027\u00c8\u0027\u002c\u0027\u00c9\u0027\u002c\u0027\u00ca\u0027\u002c\u0027\u00cc\u0027\u002c\u0027\u00cd\u0027\u002c\u0027\u00d2\u0027\u002c\u0027\u00d3\u0027\u002c\u0027\u00d4\u0027\u002c\u0027\u00d5\u0027\u002c\u0027\u00d9\u0027\u002c\u0027\u00da\u0027\u002c\u0027\u00dd\u0027\u002c\u0027\u00e0\u0027\u002c\u0027\u00e1\u0027\u002c\u0027\u00e2\u0027\u002c\u0027\u00e3\u0027\u002c\u0027\u00e8\u0027\u002c\u0027\u00e9\u0027\u002c\u0027\u00ea\u0027\u002c\u0027\u00ec\u0027\u002c\u0027\u00ed\u0027\u002c\u0027\u00f2\u0027\u002c\u0027\u00f3\u0027\u002c\u0027\u00f4\u0027\u002c\u0027\u00f5\u0027\u002c\u0027\u00f9\u0027\u002c\u0027\u00fa\u0027\u002c\u0027\u00fd\u0027\u002c\u0027\u0102\u0027\u002c\u0027\u0103\u0027\u002c\u0027\u0110\u0027\u002c\u0027\u0111\u0027\u002c\u0027\u0128\u0027\u002c\u0027\u0129\u0027\u002c\u0027\u0168\u0027\u002c\u0027\u0169\u0027\u002c\u0027\u01a0\u0027\u002c\u0027\u01a1\u0027\u002c\u0027\u01af\u0027\u002c\u0027\u01b0\u0027\u002c\u0027\u1ea0\u0027\u002c\u0027\u1ea1\u0027\u002c\u0027\u1ea2\u0027\u002c\u0027\u1ea3\u0027\u002c\u0027\u1ea4\u0027\u002c\u0027\u1ea5\u0027\u002c\u0027\u1ea6\u0027\u002c\u0027\u1ea7\u0027\u002c\u0027\u1ea8\u0027\u002c\u0027\u1ea9\u0027\u002c\u0027\u1eaa\u0027\u002c\u0027\u1eab\u0027\u002c\u0027\u1eac\u0027\u002c\u0027\u1ead\u0027\u002c\u0027\u1eae\u0027\u002c\u0027\u1eaf\u0027\u002c\u0027\u1eb0\u0027\u002c\u0027\u1eb1\u0027\u002c\u0027\u1eb2\u0027\u002c\u0027\u1eb3\u0027\u002c\u0027\u1eb4\u0027\u002c\u0027\u1eb5\u0027\u002c\u0027\u1eb6\u0027\u002c\u0027\u1eb7\u0027\u002c\u0027\u1eb8\u0027\u002c\u0027\u1eb9\u0027\u002c\u0027\u1eba\u0027\u002c\u0027\u1ebb\u0027\u002c\u0027\u1ebc\u0027\u002c\u0027\u1ebd\u0027\u002c\u0027\u1ebe\u0027\u002c\u0027\u1ebf\u0027\u002c\u0027\u1ec0\u0027\u002c\u0027\u1ec1\u0027\u002c\u0027\u1ec2\u0027\u002c\u0027\u1ec3\u0027\u002c\u0027\u1ec4\u0027\u002c\u0027\u1ec5\u0027\u002c\u0027\u1ec6\u0027\u002c\u0027\u1ec7\u0027\u002c\u0027\u1ec8\u0027\u002c\u0027\u1ec9\u0027\u002c\u0027\u1eca\u0027\u002c\u0027\u1ecb\u0027\u002c\u0027\u1ecc\u0027\u002c\u0027\u1ecd\u0027\u002c\u0027\u1ece\u0027\u002c\u0027\u1ecf\u0027\u002c\u0027\u1ed0\u0027\u002c\u0027\u1ed1\u0027\u002c\u0027\u1ed2\u0027\u002c\u0027\u1ed3\u0027\u002c\u0027\u1ed4\u0027\u002c\u0027\u1ed5\u0027\u002c\u0027\u1ed6\u0027\u002c\u0027\u1ed7\u0027\u002c\u0027\u1ed8\u0027\u002c\u0027\u1ed9\u0027\u002c\u0027\u1eda\u0027\u002c\u0027\u1edb\u0027\u002c\u0027\u1edc\u0027\u002c\u0027\u1edd\u0027\u002c\u0027\u1ede\u0027\u002c\u0027\u1edf\u0027\u002c\u0027\u1ee0\u0027\u002c\u0027\u1ee1\u0027\u002c\u0027\u1ee2\u0027\u002c\u0027\u1ee3\u0027\u002c\u0027\u1ee4\u0027\u002c\u0027\u1ee5\u0027\u002c\u0027\u1ee6\u0027\u002c\u0027\u1ee7\u0027\u002c\u0027\u1ee8\u0027\u002c\u0027\u1ee9\u0027\u002c\u0027\u1eea\u0027\u002c\u0027\u1eeb\u0027\u002c\u0027\u1eec\u0027\u002c\u0027\u1eed\u0027\u002c\u0027\u1eee\u0027\u002c\u0027\u1eef\u0027\u002c\u0027\u1ef0\u0027\u002c\u0027\u1ef1\u0027]*$');
		$scope.pattern2 = new RegExp('^[0-9,. \u0027\u00c0\u0027\u002c\u0027\u00c1\u0027\u002c\u0027\u00c2\u0027\u002c\u0027\u00c3\u0027\u002c\u0027\u00c8\u0027\u002c\u0027\u00c9\u0027\u002c\u0027\u00ca\u0027\u002c\u0027\u00cc\u0027\u002c\u0027\u00cd\u0027\u002c\u0027\u00d2\u0027\u002c\u0027\u00d3\u0027\u002c\u0027\u00d4\u0027\u002c\u0027\u00d5\u0027\u002c\u0027\u00d9\u0027\u002c\u0027\u00da\u0027\u002c\u0027\u00dd\u0027\u002c\u0027\u00e0\u0027\u002c\u0027\u00e1\u0027\u002c\u0027\u00e2\u0027\u002c\u0027\u00e3\u0027\u002c\u0027\u00e8\u0027\u002c\u0027\u00e9\u0027\u002c\u0027\u00ea\u0027\u002c\u0027\u00ec\u0027\u002c\u0027\u00ed\u0027\u002c\u0027\u00f2\u0027\u002c\u0027\u00f3\u0027\u002c\u0027\u00f4\u0027\u002c\u0027\u00f5\u0027\u002c\u0027\u00f9\u0027\u002c\u0027\u00fa\u0027\u002c\u0027\u00fd\u0027\u002c\u0027\u0102\u0027\u002c\u0027\u0103\u0027\u002c\u0027\u0110\u0027\u002c\u0027\u0111\u0027\u002c\u0027\u0128\u0027\u002c\u0027\u0129\u0027\u002c\u0027\u0168\u0027\u002c\u0027\u0169\u0027\u002c\u0027\u01a0\u0027\u002c\u0027\u01a1\u0027\u002c\u0027\u01af\u0027\u002c\u0027\u01b0\u0027\u002c\u0027\u1ea0\u0027\u002c\u0027\u1ea1\u0027\u002c\u0027\u1ea2\u0027\u002c\u0027\u1ea3\u0027\u002c\u0027\u1ea4\u0027\u002c\u0027\u1ea5\u0027\u002c\u0027\u1ea6\u0027\u002c\u0027\u1ea7\u0027\u002c\u0027\u1ea8\u0027\u002c\u0027\u1ea9\u0027\u002c\u0027\u1eaa\u0027\u002c\u0027\u1eab\u0027\u002c\u0027\u1eac\u0027\u002c\u0027\u1ead\u0027\u002c\u0027\u1eae\u0027\u002c\u0027\u1eaf\u0027\u002c\u0027\u1eb0\u0027\u002c\u0027\u1eb1\u0027\u002c\u0027\u1eb2\u0027\u002c\u0027\u1eb3\u0027\u002c\u0027\u1eb4\u0027\u002c\u0027\u1eb5\u0027\u002c\u0027\u1eb6\u0027\u002c\u0027\u1eb7\u0027\u002c\u0027\u1eb8\u0027\u002c\u0027\u1eb9\u0027\u002c\u0027\u1eba\u0027\u002c\u0027\u1ebb\u0027\u002c\u0027\u1ebc\u0027\u002c\u0027\u1ebd\u0027\u002c\u0027\u1ebe\u0027\u002c\u0027\u1ebf\u0027\u002c\u0027\u1ec0\u0027\u002c\u0027\u1ec1\u0027\u002c\u0027\u1ec2\u0027\u002c\u0027\u1ec3\u0027\u002c\u0027\u1ec4\u0027\u002c\u0027\u1ec5\u0027\u002c\u0027\u1ec6\u0027\u002c\u0027\u1ec7\u0027\u002c\u0027\u1ec8\u0027\u002c\u0027\u1ec9\u0027\u002c\u0027\u1eca\u0027\u002c\u0027\u1ecb\u0027\u002c\u0027\u1ecc\u0027\u002c\u0027\u1ecd\u0027\u002c\u0027\u1ece\u0027\u002c\u0027\u1ecf\u0027\u002c\u0027\u1ed0\u0027\u002c\u0027\u1ed1\u0027\u002c\u0027\u1ed2\u0027\u002c\u0027\u1ed3\u0027\u002c\u0027\u1ed4\u0027\u002c\u0027\u1ed5\u0027\u002c\u0027\u1ed6\u0027\u002c\u0027\u1ed7\u0027\u002c\u0027\u1ed8\u0027\u002c\u0027\u1ed9\u0027\u002c\u0027\u1eda\u0027\u002c\u0027\u1edb\u0027\u002c\u0027\u1edc\u0027\u002c\u0027\u1edd\u0027\u002c\u0027\u1ede\u0027\u002c\u0027\u1edf\u0027\u002c\u0027\u1ee0\u0027\u002c\u0027\u1ee1\u0027\u002c\u0027\u1ee2\u0027\u002c\u0027\u1ee3\u0027\u002c\u0027\u1ee4\u0027\u002c\u0027\u1ee5\u0027\u002c\u0027\u1ee6\u0027\u002c\u0027\u1ee7\u0027\u002c\u0027\u1ee8\u0027\u002c\u0027\u1ee9\u0027\u002c\u0027\u1eea\u0027\u002c\u0027\u1eeb\u0027\u002c\u0027\u1eec\u0027\u002c\u0027\u1eed\u0027\u002c\u0027\u1eee\u0027\u002c\u0027\u1eef\u0027\u002c\u0027\u1ef0\u0027\u002c\u0027\u1ef1\u0027]*$');
		$scope.pattern3 = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", "i");
		vm.partnerHr = {id: 0, fullName:'', identifyNumber: '', email:'', phoneNumber: '', positionId:'', 
						positionName:'',partnerOrgId:'',partnerOrgName:'',vofficeAccount:'',userId:'', userName:'', signatureFile:'', imagePath:'', isActive:'',pathFile:'' };
		vm.pathFile = Constant.DOWNLOAD_SERVICE ; 
		vm.partnerHrs = {};
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.showSearch = false;
		vm.add = add;
		vm.edit = edit;
		vm.showGrid = showGrid;
		vm.save = save;
		vm.search = search;
		vm.remove = remove;
		vm.detail = detail;
//		vm.copy = copy;
		vm.doSearch = doSearch;
		vm.canceldoSearch = canceldoSearch;
		vm.nextRow = nextRow;
		vm.previousRow = previousRow;
		vm.chkSelectAll = chkSelectAll;
		vm.criteriaSearch = {};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.folder = '';
		fillDataTable([]);
		fetchAllPartnerHr();

		function add() {
			vm.isCreateNew = true;
			vm.showDetail = true;
			getEmtyPartnerHr();
			addRequired();
			$("#fileUpload").val('');
		}
		
		function edit(){
			for (var i = 0; i < vm.partnerHrs.length; i++) {
				if (vm.partnerHrs[i].id === id) {
					vm.partnerHr = angular.copy(vm.partnerHrs[i]);
					break;
				}
			}
		}
		
		function save() {
			vm.uploadFile('fileUpload').then(function(result){
				console.log("2");
				// tmpFilePath = result;
				 if (vm.showDetail) {
						if (vm.validator.validate()) {
							vm.partnerHr.isActive = 1;
							vm.partnerHr.type = 2;
							vm.partnerHr.imagePath = $($('input[name="fileUpload"]')[0]).val().split('\\').pop();
							
							vm.partnerHr.utilAttachedDocuments ={};
							vm.partnerHr.utilAttachedDocuments.documentPath = result;
							vm.partnerHr.utilAttachedDocuments.documentName = $($('input[name="fileUpload"]')[0]).val().split('\\').pop();					
							if (vm.isCreateNew) {
								if(vm.partnerHr.identifyNumber == '' || vm.partnerHr.identifyNumber === null 
								   || vm.partnerHr.identifyNumber == undefined){
									return;
								}
								if(vm.partnerHr.phoneNumber == '' || vm.partnerHr.phoneNumber === null
										|| vm.partnerHr.phoneNumber == undefined){
									return;
								}
								partnerHrService.createPartnerHr(vm.partnerHr).then(
									function() {
										toastr.success("Lưu thành công!");
										reloadPartnerHr();
									},
									function(errResponse) {
										console.error('Error while creating');
										if (errResponse.status.toString() === "409") {
											toastr.error(gettextCatalog.getString("Mã đã tồn tại!"));
										} else {
//											toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo nhân sự đối tác !"));
											toastr.error(gettextCatalog.getString("Số chứng minh thư đã tồn tại trong hệ thống!"));
										}
										return;
									});
							} else {
								if(vm.partnerHr.identifyNumber == '' || vm.partnerHr.identifyNumber === null 
										   || vm.partnerHr.identifyNumber == undefined){
											return;
										}
										if(vm.partnerHr.phoneNumber == '' || vm.partnerHr.phoneNumber === null
												|| vm.partnerHr.phoneNumber == undefined){
											return;
										}
								partnerHrService.updatePartnerHr(vm.partnerHr).then(
									function(result) {
										toastr.success("Cập nhật thành công!");
										reloadPartnerHr();
									},
									function(errResponse) {
										if (errResponse.status.toString() === "409") {
											toastr.error(gettextCatalog.getString("Số chứng minh thư đã tồn tại trong hệ thống!"));
										} else {
											toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
										}
									});
							}
						}
					} else {

					}
			})
			
		}
		
		function remove(){
			var selectedRow = [];
			var grid = vm.partnerHrGrid;
			var flagDelete = 0;
			grid.table.find("tr").each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')) {
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(item);
					selectedRow.push(dataItem.id);
					flagDelete = 1;
				}
			});
			if(flagDelete === 1){
				var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				selectedRow.push(dataItem.id);
			}
			if (selectedRow.length === 0) {
				toastr.warning(message.recordRequired);
				return;
			}
			console.log(selectedRow);
			if (confirm('Xác nhận xóa bản ghi')) {	
				partnerHrService.deleteList(selectedRow).then(function() {
					toastr.success(message.deleteSuccess);
					vm.criteriaSearch={};
					reloadPartnerHr();
					flagDelete = 0;
				}, function(errResponse) {
			         if (errResponse.status === 302){
			             toastr.error("Bản ghi đang được sử dụng");
			            }else{
			             toastr.error(message.deleteError);
			         }
		         });
			}
		}
		
		function detail(){
			if (vm.partnerHrGrid.select().length > 0) {
				vm.showDetail = true;
			} else {
				toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
			}
		}
		
		/*function copy(){
			if (!vm.showDetail) {
	        	vm.isCreateNew = true;
	        	var tr = vm.partnerHrGrid.select().closest("tr");
				var dataItem = vm.partnerHrGrid.dataItem(tr);
	            detail();
        	} else {
        		vm.isCreateNew = true;
        	}
		}*/
		
		function doSearch(){
			if(vm.criteriaSearch != null) {
				$('#loading').show();
				partnerHrService.fetchAllPartnerHr(vm.criteriaSearch).then(function(d) {
					refreshGrid(d.plain());
				}).catch(function(data, status) {
					$('#loading').hide();
					console.error('Error while fetching PartnerHr');
				}).finally(function() {
					$('#loading').hide();
				  }); 
			}
			
		}
		
		function canceldoSearch(){
			vm.criteriaSearch = {};
			doSearch();
		}
		
		function nextRow(){
			var grid = vm.partnerHrGrid;
    		nextRowBase(grid, message);
		}
		
		function previousRow(){
			var grid = vm.partnerHrGrid;
    		previousRowBase(grid, message);
		}
		
		function showGrid(){
			if (!vm.showDetail) {
				vm.showSearch = false;
				vm.isCreateNew = false;
				if (vm.showSearch && vm.partnerHrGrid.select().length === 0){
					addRequired();
					partnerHrService.fetchAllPartnerHr().then(function(d) {
						refreshGrid(d.plain());
					}, function(errResponse) {
						console.error('Error while fetching PartnerHr');
					});
				}
				else{
					if (vm.partnerHrGrid.select().length > 0) {
						vm.showDetail = true;
						$(".k-invalid-msg").hide();
//						$('input[name="fileUpload"]').val();
					} else {
						toastr.warning("Bạn cần chọn một bản ghi trước");
					}
				}
			} else {
				//TODO
//				vm.isCreateNew = false;
				vm.showDetail = false;
				reloadPartnerHr();
			}
		}
		var fileName = "";
		function fillDataTable(data) {
			var deferred = $q.defer();
			var dataItem = 1;
			vm.gridOptions = kendoConfig
					.getGridOptions({
						autoBind : true,
						editable: false,
						dataSource : data,
						change : onChange,
						noRecords : true,
						pageable: {
							refresh: false,
							pageSize: 20,
							messages: {
				                display: "{0}-{1} của {2} kết quả",
				                itemsPerPage: "kết quả/trang",
				                empty: "Không có kết quả hiển thị"
				            }
						},
						dataBound: function(e) {
							var grid = $("#partnerHrGrid").data("kendoGrid");
							if (!vm.rowIndex) {
								grid.select("tr:eq(0)");
							} else {
								grid.select("tr:eq(" + vm.rowIndex + ")");
							}
						},
						messages : { noRecords : gettextCatalog.getString("Không có kết quả nào")
						},
						columns : [
								{
								    field: "rowNumber",
								    title: "STT",
								    headerAttributes: { style: "text-align:center; white-space: normal" },
								    attributes: { style: "text-align:center;" },
								    template: dataItem => $("#partnerHrGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								    width : 80
								},
								{   
									title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
								    template: "<input type='checkbox' name='gridcheckbox' />",
								    width: 35
								},
								{
									title : gettextCatalog.getString("Họ tên"),
									field : "fullName",
									width : 180
								},
								{
									title : gettextCatalog.getString("Chứng minh thư"),
									field : "identifyNumber",
									attributes:{ class:"text-right" },
									width : 180
								},
								{
									title : gettextCatalog.getString("Email"),
									field : "email",
									width : 180
								},
								{
									title : gettextCatalog.getString("Số điện thoại"),
									field : "phoneNumber",
									attributes:{ class:"text-right" },
									width : 180
								},
								{
									title : gettextCatalog.getString("Chức vụ"),
									field : "positionName",
									width : 180
								},
								{
									title : gettextCatalog.getString("Đối tác"),
									field : "partnerOrgName",
									width : 200
								},
								{
									title : gettextCatalog.getString("Account Voffice"),
									field : "vofficeAccount",
									width : 180
								},
								{
									title : gettextCatalog
											.getString("Account HCQT"),
									field : "userName",
									width : 200
								},
								{
									title : gettextCatalog.getString("<b>Ảnh chữ ký</b>"),
									headerAttributes: { style: "text-align:center; white-space: normal" },
									template : function(dataItem) {
										return "<a href='" + Constant.DOWNLOAD_SERVICE + dataItem.documentPath + "'>" + dataItem.imagePath + "</a>"
										
									},
									width : 300
								}]
					});
		}
		
		function getEmptyDataModel(){
			return vm.partnerHr = {};
					
		}
		
		function chkSelectAll(item) {
	    	var grid = vm.partnerHrGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
		}
		
		vm.getEmtyPartnerHr = getEmtyPartnerHr;
		function getEmtyPartnerHr(){
			vm.partnerHr = {};
		}
		
		vm.getSearchEmptyDataModel = getSearchEmptyDataModel;
		function getSearchEmptyDataModel(){
			vm.criteriaSearch = {};
		}
		
		function reloadPartnerHr(){
			partnerHrService.fetchAllPartnerHr(vm.criteriaSearch).then(function(d) {
				vm.showDetail = false;
				refreshGrid(d.plain());
			}, function(errResponse) {
				console.error('Error while fetching PartnerHr');
			});
		}
		
		vm.onGridChange = function(e, sel, dataItem) {			
	           vm.bean = dataItem;
	           vm.rowIndex = sel.selected.rowIndex;
	           onChange();
		}
		
		function onChange() {
			if (vm.partnerHrGrid.select().length > 0) {
				var tr = vm.partnerHrGrid.select().closest("tr");
				var dataItem = vm.partnerHrGrid.dataItem(tr);
				vm.partnerHr = dataItem;
			}
		}
		
		function search() {
			vm.showDetail = false;
			if (!vm.showSearch) {
				vm.showSearch = true;
				vm.criteriaSearch = getSearchEmptyDataModel();
			} else {
				vm.showSearch = false;
				reloadPartnerHr();
			}
		}
		
		
		function refreshGrid(d) {
			var grid = vm.partnerHrGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function fetchAllPartnerHr() {
			partnerHrService.fetchAllPartnerHr(vm.criteriaSearch).then(function(d) {
				refreshGrid(d.plain());
			}, function(errResponse) {
				console.error('Error while fetching PartnerHr');
			});
		}
		
		vm.commonSearch = {name: '', address: ''};
		vm.gridCommon = [ 
		  {
			title: "<b>STT</b>",
			template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			headerAttributes: { style: "text-align:center; white-space: normal" },
			attributes: { style: "text-align:center;" },
			width: 20
		},
		{
			title: "Tên đối tác",
			field: "partnerName",
			width: 120
		}, {
			title: "Địa chỉ",
			field: "address",
			width: 120
		}];
		
		vm.commonSearchAcc = {name: '', fullName: ''};
		vm.gridCustom = [ 
		 {
			title: "<b>STT</b>",
			template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			headerAttributes: { style: "text-align:center; white-space: normal" },
			attributes: { style: "text-align:center;" },
			width: 20
		},
		{
			title: "Account",
			field: "loginName",
			width: 120
		},{
			title: "FullName",
			field: "fullName",
			width: 120
		}];
		
		function addRequired(){
			document.getElementById("pa_partnerHr_txtpartnerId").required = true;
		}
		
		function resetValue(){
			$("#pa_partnerHr_txtpartnerId").data("kendoComboBox").value(null);
			$("#pa_partnerHr_txtuserId").data("kendoComboBox").value(null);
		}
		
		//******************************handle file***********************************
		function getFolder() {
			partnerHrService.getCompletionFolder().then(function(data) {
				vm.folder = data.folder;
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished gists");
			  });
		}
		
		var fileName = '';
		var tmpFilePath ='';
		getFolder();
		
		
		vm.uploadFile=function(id){
			var formData = new FormData();
			formData.append('tax_file', $('#'+ id)[0].files[0]); 
			 var deferred = $q.defer();
			Restangular.one(Constant.FILE_SERVICE+ "?folder="+ vm.folder).withHttpConfig(
					{
						transformRequest : angular.identity
					}).customPOST(formData, '', undefined, {
				'Content-Type' : 'multipart/form-data'
			}).then(function(successResponse) {
				console.log(successResponse);
				if(successResponse.length>0){
					 
					tmpFilePath = successResponse[0];
					deferred.resolve(tmpFilePath);
				}
			});
			return deferred.promise;
		}
		
		
		
		
		
		
		
		
		
		/*vm.uploadFile = function(id) {
			console.log("1");
				var formData = new FormData();
				formData.append('tax_file', $('#'+ id)[0].files[0]); 
		      return  $.ajax({
		            url: "fileservice/upload?folder="+ vm.folder,
		            type: "POST",
		            data: formData,
		            enctype: 'multipart/form-data',
		            processData: false,
		            contentType: false,
		            cache: false
//		            success: function(result) {
//		                console.log("Upload successfully!");
//		                tmpFilePath = result;
//		            },
//		            error: function() {
//		                // Handle upload error
//		                // ...
//		            }
		        });
			}*/
		
		//****************************************************************************
		var message = {
            	noDataGrid: gettextCatalog.getString("Không có dữ liệu trên trang hiện tại"),
            	lineRequired: gettextCatalog.getString("Bạn cần chọn một bản ghi trước"),
            	recordRequired: gettextCatalog.getString("Bạn cần chọn một bản ghi trước"),
            	needShowDetail : gettextCatalog.getString("Cần hiển thị ở chế độ Chi Tiết!"),
            	positionLast: gettextCatalog.getString("Đã ở bản ghi cuối"),
            	positionFirst: gettextCatalog.getString("Đã ở bản ghi đầu"),
            	deleteError: gettextCatalog.getString("Xóa không thành công"),
            	deleteSuccess: gettextCatalog.getString("Xóa  thành công"),
        }
	}
})();