(function() {
	'use strict';

	var controllerId = 'expertiseController';

	angular.module('MetronicApp').controller(controllerId,
			expertiseController);

	/* @ngInject */
	function expertiseController(
			$scope, $rootScope, $timeout, Constant,
			kendoConfig, estimatesTableDetailService, ProposalEvaluation, gettextCatalog, $kWindow,
			CommonService, Restangular, PopupConst) {
		var vm = this;
		vm.expertise={};
		vm.expertise.estimatesWorkItemId = estimatesTableDetailService.getEstimatesWorkItemId();
	    vm.save = save;
	    vm.cancel = cancel;
	    vm.chkSelectAll = chkSelectAll;
	    vm.tempale = {
	    		evaluateQuantity:'',
	    		evaluateComments:''
	    }
	        
		fillDataTable([]);
		fetchAll();
		
		 function fillDataTable(data){
			   vm.validatorOptions = kendoConfig.get('validatorOptions');
			   var dataSource = new kendo.data.DataSource({
	                pageSize: 20,
	                data: data,
	                //autoSync: true,
	                schema: {
	                    model: {
	                        id: "reportGrid",
	                     fields: {
	                      objectChecking: { validation: { required: true } },
	                      length: { type: "number", validation: { min: 0, required: true } },
	                      attenuationLength: { type: "number", validation: { min: 0, required: true } },
	                      attenuationDegree: { type: "number", validation: { min: 0, required: true } },
	                      attenuationSum: { type: "number", validation: { min: 0, required: true } },
	                      attenuationAverage: { type: "number", validation: { min: 0, required: true } },
	                            note: {}
	                        }
	                    }
	                }
	            });
			   vm.gridOptions = kendoConfig.getGridOptions({
			    autoBind : true,
			    dataSource : data,
			    // change : onChange,
			    noRecords : true,
			    messages : {
			     noRecords : gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
			    },
			    columns : [
				     {
				      title: gettextCatalog.getString("STT"),
				      field: "stt",
				      template: dataItem => $("#gridExpertise").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
				      width: 80,
				      editor: nonEditor,
				      headerAttributes: { style: "text-align:center;" },
					  attributes: { style: "text-align:center;" },
				     },
				     {
						title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
						template : "<input type='checkbox' name='gridcheckbox' />",
						width : 35,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:left;" },
					},
					{
						title : gettextCatalog
								.getString("Mã hiệu đơn giá"),
						field : "workItemCode",
						width : 180,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:right;" },
					},
					{
						title : gettextCatalog
								.getString("Nội dung công việc"),
						field : "workItemName",
						width : 180,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:left;" },
					},
					{
						title : gettextCatalog
								.getString("Đơn vị tính"),
						field : "unit",
						width : 180,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:left;" },
					},
					{
						title : gettextCatalog
								.getString("Diễn dải"),
						field : "explain",
						width : 180,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:left;" },
					},
					{
						title : gettextCatalog
								.getString("Khối lượng"),
						field : "executeQuantity",
						width : 180,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:right;" },
					},
					{
						title : gettextCatalog
								.getString("Bản vẽ thể hiện"),
						field : "instanceDrawCode",
						width : 180,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:left;" },
					},
					{
						title : gettextCatalog
								.getString("Ghi chú"),
						field : "comments",
						width : 180,
						editor: nonEditor,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:left;" },
					},
					// DEFAULT Khối lượng thẩm định = Khối lượng, executeQuantity = evaluateQuantity
					{
						title : gettextCatalog
								.getString("<div style='margin: 00px 0px 4px 35px !important; color: #2196F3 !important; font-weight: 600; font-size: 12px;'>Khối lượng thẩm định</div>"),
						field : "executeQuantity",
						width : 250,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:right;" },
					},
					{
						title : gettextCatalog
						.getString("<div style='margin: 00px 0px 4px 35px !important; color: #2196F3 !important; font-weight: 600; font-size: 12px;'>Diễn dải thẩm định</div>"),
						field : "evaluateComments",
						width : 250,
						headerAttributes: { style: "text-align:center;" },
					    attributes: { style: "text-align:left;" },
					},
					 ]
			   });
			  }
		
		function fetchAll() {
			estimatesTableDetailService.getWorkItem(vm.expertise).then(
					function(d) {
						refreshGrid(d.plain());
						vm.expertise.evaluateComments = '';
						console.log(vm.expertise.evaluateComments);
					}, function(errResponse) {
						console.error('Error while fetching InvDetailInOut');
					});
		}
		
		function refreshGrid(d) {
			var grid = vm.gridExpertise;
			if(grid){
//				d[0].evaluateComments = '';
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function save(){
			vm.constrAcceptWorkList = {
					constrAcceptWorkListId: '',
					explain:'',
					executeQuantity:'',
					evaluateQuantity:'',
					evaluateComments:'',
					instanceDrawCode:'',
					comments:'',
					constructionAcceptanceId:'',
					estimatesWorkItemId:'',
					settleUnitPrice:'',
					evaluateUnitPrice:''
					
			};
//			executeQuantity = evaluateQuantity
			var data = vm.gridExpertise.dataSource.data();
//			vm.tempale = data;
			vm.constrAcceptWorkList.constrAcceptWorkListId = data[0].constrAcceptWorkListId;
			vm.constrAcceptWorkList.explain = data[0].explain;
			vm.constrAcceptWorkList.executeQuantity = data[0].executeQuantity;
			vm.constrAcceptWorkList.evaluateQuantity = data[0].evaluateQuantity;
			vm.constrAcceptWorkList.instanceDrawCode = data[0].instanceDrawCode;
			vm.constrAcceptWorkList.comments = data[0].comments;
			vm.constrAcceptWorkList.constructionAcceptanceId = data[0].constructionAcceptanceId;
			vm.constrAcceptWorkList.estimatesWorkItemId = data[0].estimatesWorkItemId;
			vm.constrAcceptWorkList.settleUnitPrice = data[0].settleUnitPrice;
			vm.constrAcceptWorkList.evaluateUnitPrice = data[0].evaluateUnitPrice;
			vm.constrAcceptWorkList.evaluateComments = data[0].evaluateComments;
	       	estimatesTableDetailService.updateConstrAcceptWorkList(vm.constrAcceptWorkList).then(function(){
				toastr.success("Lưu thành công");
				fetchAll();
				vm.showDetail = false;
			}, function(e){
				toastr.error(gettextCatalog.getString("Lỗi khi thêm mới!"));
				return;
			});
//				toastr.warning("ĐINH CÔNG MẠNH!");
	        }
	
		function cancel(){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
		function chkSelectAll(item) {
	    	console.log('chkSelectAll');
	    	var grid = vm.gridExpertise;
	    	chkSelectAllBase(vm.chkAll, grid);
		}
		
		function nonEditor(container, options) {
		    container.text(options.model[options.field]);
		}	
		
	}

})();