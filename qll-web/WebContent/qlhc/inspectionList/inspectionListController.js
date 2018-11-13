(function() {'use strict';var controllerId = 'INSP_List_CONGTRINHController';
	angular.module('MetronicApp').controller(controllerId,
			INSP_List_CONGTRINHController);

	function INSP_List_CONGTRINHController($scope, $rootScope, $timeout, Constant,kendoConfig,
			inspectionListService, gettextCatalog,  $kWindow, CommonService, PopupConst ,Restangular,ProposalEvaluation, RestEndpoint) 
	{
		var vm = this;
		// Khai báo thêm.
		vm.remove = remove;
		vm.goTo = goTo;
		vm.criteria = {};
		vm.chkSelectAll = chkSelectAll;
		
		// Thông tin chung
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
		}
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		// Đi tới trang tiếp
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
		
		// Chọn item trong bảng bằng checkbox
		function chkSelectAll(item) {
	    	console.log('chkSelectAll');
	    	var grid = vm.shipmentGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    }
		
		// chọn item bằng bản ghi onchane
		vm.onChange = onChange;
		function onChange(e) {
        	if (vm.shipmentGrid.select().length > 0) {
        		var tr = vm.shipmentGrid.select().closest("tr");
            	var dataItem = vm.shipmentGrid.dataItem(tr);
            	vm.itemRow = dataItem;
            	console.log(dataItem);
            }
        }
		
		// load bảng
		initFormData();
		function initFormData()
		{
			inspectionListService.findByConstructId(vm.item.constructId).then(function (d){
				
				fillDataTable(d.plain());
				refreshGrid(d.plain());
			},function (e){
				console.log("Erro");
			});
		}
		
		// reload bảng
		function reloadInspectionList(){
			inspectionListService.findByConstructId(vm.item.constructId).then(function (d){
				refreshGrid(d.plain());
			},function (e){
				console.log("Erro");
			});
		}
		// Lam moi lai danh sach
		function refreshGrid(d) {
			var grid = vm.shipmentGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		// xóa 1 bản ghi
		function remove(){
        	var grid = vm.shipmentGrid;
        	// Check select
        	if (grid.select() == null || grid.select().length == 0) {
        		toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
        		return;
        	}
        	var tr = grid.select().closest("tr");
        	var dataItem = grid.dataItem(tr);
        	
        	if(vm.shipmentGrid.select().length > 0 && confirm('Xác nhận xóa')){
        		inspectionListService.deleteConstructionAcceptance(dataItem.constructionAcceptanceId).then(function(result){
            		reloadInspectionList();
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
			var grid = vm.shipmentGrid;
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
					inspectionListService.deleteConstructionAcceptance(selectedRow[i]).then(function() {
			    		toastr.success("Đã xóa thành công");
			    		reloadInspectionList();
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
		
		// Bảng danh sách
		function fillDataTable(data) {
			
			vm.gridOptions = kendoConfig
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
					            width : 70
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
		
		$scope.$on("ProposalEvaluation.detail", function (event, item) {
        	if(item != undefined){
        		vm.item = item;
        		initFormData();
        	}else{
        		console.error("không nhận được dữ liệu!");
        	}
        });
	}
})();