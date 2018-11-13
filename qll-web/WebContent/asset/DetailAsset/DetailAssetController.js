(function() {
	'use strict';

	var controllerId = 'detailassetcontroller';

	angular.module('MetronicApp').controller(controllerId,
			detailassetcontroller);

	/* @ngInject */
	function detailassetcontroller($scope, $rootScope, $timeout, Constant,
			kendoConfig, detailassetService, gettextCatalog, $kWindow,
			CommonService, Restangular, PopupConst, RestEndpoint,longTermAssetEntityService,detailCatAssetUpdateService,tscdConstantService) {
		var vm = this;
		
	     vm.edit = edit;
	     

	        function edit(id){
	            for(var i = 0; i < vm.locationGroup.length; i++){
	                if(vm.locationGroup[i].id === id) {
	                    vm.locationGroup = angular.copy(vm.locationGroup[i]);
	                    break;
	                }
	            }
	        }
	        
	     
	        
	        function mapdata(){
	        	
	        }
	    detailassetService.setFrist(true);
		// dữ liệu Tìm kiếm gửi
	    vm.item={};
		vm.item = detailassetService.getData();
		$scope.$on('load.data.detail', function(event, args) {
			vm.item = detailassetService.getData();
			fetchAllOne();
			fetchAllTwo();
			fetchAllFour();
		});
		
//		vm.item = ProposalEvaluation.getItem();
		// END dữ liệu Tìm kiếm gửi
		
		
		fillDataTableOne([]);
		fillDataTableTwo([]);
//		fillDataTableThree([]);
		fillDataTableFour([]);
		fetchAllOne();
		fetchAllTwo();
		fetchAllFour();

		
		
		 function fillDataTableOne(data){
			   vm.validatorOptions = kendoConfig.get('validatorOptions');
			   vm.gridOptionsOne = kendoConfig.getGridOptions({
			    autoBind : true,
			    dataSource : {data,
			   
			    aggregate: [ { field: "total", aggregate: "sum" }
                            ]
			    },
			    // change : onChange,
			    noRecords : true,
			    messages : {
			     noRecords : gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
			    },
			    columns : [
			     {
			      title: gettextCatalog.getString("STT"),
			      field: "stt",
			      template: dataItem => $("#gridOne").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			      width: 70,
			      headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
			     	},
					{
						title : gettextCatalog
								.getString("Mã hàng"),
						field : "code",
						width : 150,
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title : gettextCatalog
								.getString("Tên hàng"),
						field : "name",
						width : 150,
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
						footerTemplate: "<div style='text-align: center;font-size:17px;'>TỔNG</div>"
					},
					{
						title : gettextCatalog
								.getString("Serial number"),
						field : "serialNumber",
						width : 150,
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title : gettextCatalog
								.getString("Đơn vị tính"),
						field : "unit",
						width : 150,
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title : gettextCatalog
								.getString("Số lượng"),
						field : "quantity",
						width : 150,
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:right;"
						},
					},
					{
						title : gettextCatalog
								.getString("Đơn giá"),
						field : "originalPrice",
						template: "# if(originalPrice == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(originalPrice, 'n') #"+ "#} #",
						width : 150,
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:right;"
						},
					},
					{
						title : gettextCatalog
								.getString("Thành tiền"),
						field : "total",
						template: "# if(total == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(total, 'n') #"+ "#} #",
						width : 150,
						aggregates:["sum"],
//						footerTemplate: "<div style='text-align: right;font-size:17px;'>#= sum #</div>",
						footerTemplate: "<div style='text-align: right;font-size:17px;'># if(sum == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(sum, 'n') #"+ "#} #</div>",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:right;"
						},
					}
					 ]
			   });
			  }
		
		function fillDataTableTwo(data){
			vm.validatorOptions = kendoConfig.get('validatorOptions');
			vm.gridOptionsTwo = kendoConfig.getGridOptions({
				autoBind : true,
				dataSource :{ data,
					   aggregate: [ { field: "total", aggregate: "sum" }]
				},
				change : onChangeInsideContract,
				noRecords : true,
				messages : {
					noRecords : gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
				},
				columns : [
					{
						title: gettextCatalog.getString("STT"),
						field: "stt",
						template: dataItem => $("#gridTwo").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width: 70,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
					},
					
					{
						title : gettextCatalog
								.getString("Mã hàng"),
						field : "code",
						width : 180,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
					},
					{
						title : gettextCatalog
								.getString("Tên hàng"),
						field : "name",
						width : 150,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
							footerTemplate: "<div style='text-align: center;font-size:17px;'>TỔNG</div>"
					},
					{
						title : gettextCatalog
								.getString("Đơn vị tính"),
						field : "unit",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						width : 150,
					},
					{
						title : gettextCatalog
								.getString("Số lượng"),
						field : "quantity",
//						template: "# if(quantity == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(quantity, 'n') #"+ "#} #",
						width : 150,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:right;"
							},
					},
					{
						title : gettextCatalog
								.getString("Đơn giá"),
						field : "originalPrice",
						template: "# if(originalPrice == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(originalPrice, 'n') #"+ "#} #",
						width : 150,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:right;"
							},
					},
					{
						title : gettextCatalog
								.getString("Thành tiền"),
						field : "total",
						template: "# if(total == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(total, 'n') #"+ "#} #",
						width : 150,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:right;"
							},
						aggregates:["sum"],
//						footerTemplate: "<div style='text-align: right;font-size:17px;'>#= sum #</div>",
						footerTemplate: "<div style='text-align: right;font-size:17px;'># if(sum == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(sum, 'n') #"+ "#} #</div>",
					}
				]
			});
		}
		
		function onChangeInsideContract(){
			//TODO
		}
		function fillDataTableThree(data){
			vm.validatorOptions = kendoConfig.get('validatorOptions');
			vm.gridOptionsThree = kendoConfig.getGridOptions({
				autoBind : true,
				dataSource : data,
				change : onChangeOutsideContract,
				noRecords : true,
				messages : {
					noRecords : gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
				},
				columns : [
				           {
					        	title: gettextCatalog.getString("STT"),
								field: "stt",
								template: dataItem => $("#contentWorkOutsideContract").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
								width: 65,
				           },
				           {
								title : gettextCatalog
										.getString("Mã"),
								field : "code",
								width : 180
							},
							{
								title : gettextCatalog
										.getString("Tên"),
								field : "name",
								width : 180,
								footerTemplate: "Tổng"
							},
							{
								title : gettextCatalog
										.getString("Đơn vị tính"),
								field : "unit",
								width : 180,
							},
							{
								title : gettextCatalog
										.getString("Số lương"),
								field : "qty",
								width : 180,
							},
							{
								title : gettextCatalog
										.getString("Đơn giá"),
								field : "price",
								width : 180,
							},
							{
								title : gettextCatalog
										.getString("Thành tiên"),
								field : "amount",
								width : 180,
								footerTemplate: "Tổng:1000"
							}
				           ]
			});
		}
		
		function onChangeOutsideContract(){
			//TODO
		}
		
		
		function fillDataTableFour(data){
			vm.validatorOptions = kendoConfig.get('validatorOptions');
			vm.gridOptionsFour = kendoConfig.getGridOptions({
				autoBind : true,
				dataSource : { data,
					   aggregate: [ { field: "voucherValue", aggregate: "sum" }]
				},
				// change : onChange,
				noRecords : true,
				messages : {
					noRecords : gettextCatalog.getString("Không có dữ liệu cho trang hiện tại")
				},
				columns : [
					{
						title: gettextCatalog.getString("STT"),
						field : "abc",
						template: dataItem => $("#gridFour").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width : 60,
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:center;"
							},
					},
					{
						title: gettextCatalog.getString("Hạng mục chi phí"),
						field : "abc",
						   template : "#if(loacType == 1){#" + "#= 'Chi phí vật tư thiết bị'#" + "# }else if(loacType == 2){#" 
						  	  + "#= 'Chi phí xây lắp'#" + "# }else if(loacType == 3){#" 
							  + "#= 'Chi phí tư vấn, dầu tư xây dựng'#" + "# }else if(loacType == 3){#" 
							  + "#= 'Chi phí QLDA'#" + "# }else if(loacType == 3){#" 
							  + "#= 'Chi phí bồi thường, hỗ trợ, tái định cư'#" + "# }else if(loacType == 4){#" 
							  + "#= 'Chi phí khác'#" + "# }#" ,
						width : 150,
						footerTemplate: "<div style='text-align: center;font-size:17px;'>TỔNG</div>",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
					},
					{
						title: gettextCatalog.getString("Giá trị(VNĐ)"),
						field : "voucherValue",
						 template: "# if(voucherValue == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(voucherValue, 'n') #"+ "#} #",
						width : 150,
						 headerAttributes: {
								style: "text-align:center;"
							},							
							attributes: {
								style: "text-align:right;"
							},
						aggregates:["sum"],
//						footerTemplate: "<div style='text-align: right;font-size:17px;'>#= sum #</div>",	
						footerTemplate: "<div style='text-align: right;font-size:17px;'># if(sum == null){ #" + "#= '' #" + "# } " + "else { # " + "#= kendo.toString(sum, 'n') #"+ "#} #</div>",
								
					},
					{
						title: gettextCatalog.getString("Số chứng từ"),
						field : "voucherCode",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						width : 150
					},
					{
						title: gettextCatalog.getString("Ngày chứng từ"),
						field : "voucherDate",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						width : 150
					},
					{
						title: gettextCatalog.getString("File đính kèm"),
						field : "abc",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
						width : 150
					},
				]
			});
		}
		
		function fetchAllOne(){
			detailassetService.fetchAllOne(vm.item.longTermAssetId).then(
					function(d) {
						refreshGridOne(d.plain());
					}, function(errResponse) {
						toastr.error("Lỗi~~~~~~~~");
					});
		}
		
		function refreshGridOne(d) {
			var grid = vm.gridOne;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function fetchAllTwo(){
			detailassetService.fetchAllTwo(vm.item.longTermAssetId).then(
					function(d) {
						refreshGridTwo(d.plain());
					}, function(errResponse) {
						toastr.error("Lỗi~~~~~~~~");
					});
		}
		
		function refreshGridTwo(d) {
			var grid = vm.gridTwo;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function refreshGridThree(d) {
			var grid = vm.gridThree;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function fetchAllFour(){
			detailassetService.fetchAllFour(vm.item.longTermAssetId).then(
					function(d) {
						refreshGridFour(d.plain());
					}, function(errResponse) {
						toastr.error("Lỗi~~~~~~~~");
					});
		}
		
		function refreshGridFour(d) {
			var grid = vm.gridFour;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function goTo(menuKey) {
			var template = Constant.getTemplateUrl(menuKey);
			postal.publish({
				channel: "Tab",
				topic: "open",
				data: template
			});
		}
		
		vm.doEdit=doEdit;
		vm.showEditBtn=function(){
			return !tscdConstantService.isNotAllowEditLtaStatus(vm.item.isSentErp);
		}
		
		function doEdit(){
			if(!tscdConstantService.isNotAllowEditLtaStatus(vm.item.isSentErp)){
			/*if(vm.item.isSentErp===0){*/
//				if(vm.item.voucherType===2){
//					longTermAssetEntityService.setId(vm.item.longTermAssetId);
//					goTo("LONG_TERM_ASSET_ENTITY_UPDATE");
//				} else if(vm.item.voucherType===1){
//					detailCatAssetService.setId(vm.item.longTermAssetId);
//					goTo("Asset_CatAssetDetail");
//				}
				if(vm.item.voucherType==2){//Loai tai san hinh thanh khong qua xay lap
					/*	longTermAssetEntityService.setId(id);
						goTo("LONG_TERM_ASSET_ENTITY_UPDATE");*/
						 console.debug("chuyen sang hinh thanh khong qua xay lap tu man hinh danh sach tai san");
		                $rootScope.Asset_LongTermAsset_Update_init={
		                        type:2,
		                        value:vm.item.longTermAssetId
	                    };
		                CommonService.goToMenu("Asset_LongTermAsset_Update",{});
					} else if(vm.item.voucherType==1){ // tai san hinh thanh qua xay lap
						/*detailCatAssetUpdateService.setId(id);
						goTo("Asset_CatAssetDetail");*/
						console.debug("chuyen sang hinh thanh qua xay lap");	            
		                $rootScope.Asset_CatAssetDetail_init={
		                    type:2,
		                    value:vm.item.longTermAssetId
		                };
		                CommonService.goToMenu("Asset_CatAssetDetail_update",{});
					}
			} else{
				toastr.error("Bản ghi đã được gửi tài chính!");
			}
		}
	}

})();