(function() {
	'use strict';
	var controllerId = 'shipmentController';
	
	angular.module('MetronicApp').controller(controllerId, shipmentController);
	
	function shipmentController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,shipmentService,shipmentDetailService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        
        vm.Search={
        		businessType: ""
		};
        vm.commonSearch = {name: '', code: ''};
        vm.gridCommon = [ {
			title: "Mã",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
        vm.gridCommon2 = [ {
			title: "Mã",
			field: "code",
			width: 120
		}];
        
		vm.shipmentSearch =
		{
				type : 0,
				listStatus : ['1']
		};
		vm.doSearchFile=
		{
				type : 1
		}
		vm.dataReasonDelete = [];
		vm.shipment={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.reasonDelete = 
		{
				apply : 10,
				status : 1
		}
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillFileTable([]);
			fillUpdateTable([]);
			reasonDelete();
			loadFileData();
		}
		function reasonDelete(){
			shipmentService.getReasonDelete(vm.reasonDelete).then(function(result){
				 vm.dataReasonDelete = result;						 
			 }, function(errResponse){  
				toastr.error(gettextCatalog.getString("Xảy ra lỗi khi lấy Lý do"));
		     });
		}
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.shipmentGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		function refreshGrid(d) {
			var grid = vm.shipmentFileGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.onSelect = function(e) {
			var obj={};
			 $.map(e.files, function(file) {
				 obj.name= file.name;
				 obj.appParam ={
						 code : "choese",
						 name : "~~ Chọn ~~"
				 };
            	}
            )
            vm.dataFile.push(obj);
            refreshGrid(vm.dataFile);
        }
		
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,
				resizable: true,
				columnMenu: false,
				toolbar: [
                    {
                    	name: "actions",
                        template: '<div class=" pull-left ">'+
                        '<a class="btn btn-qlk padding-search-right addQLK"'+
    					'ng-click="vm.add()" uib-tooltip="Thêm mới" translate><p class="action-button add" aria-hidden="true">Tạo mới</p></a>'+
    					'</div>'	
        				+
                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in vm.shipmentGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                    '</label>'+
                    '</div></div>'
                    
                    }
                    ],
				dataBound: function (e) {
				    var grid = vm.shipmentGrid;
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
				        var teamplateUrl="qlk/shipmentDetail/shipmentDetailList.html";
				        var title="Thông tin chi tiết lô hàng";
						$kWindow.open({
							 options: {
								 modal: true,
								 title: title,
								 visible: false,
								 width: '88%',
								 height: '88%',
								 actions: ["Minimize", "Maximize", "Close"],
								 open: function () {
									 this.wrapper.children('.k-window-content').addClass("fix-footer");
									 $rootScope.$broadcast("shipment.object.data", dataItem);
								 }
							 },                
							 templateUrl: teamplateUrl
				});
						
				    });
				},
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total;
							},
							data: function (response) {
								return response.data;
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							 url: Constant.BASE_SERVICE_URL + "shipmentRsServiceRest/shipment/doSearch",
							 contentType: "application/json; charset=utf-8",
							 type: "POST"
						},					
						parameterMap: function (options, type) {
								  vm.shipmentSearch.page = options.page
								  vm.shipmentSearch.pageSize = options.pageSize
								  return JSON.stringify(vm.shipmentSearch)
						}
					},					 
					pageSize: 20
				},
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				
				columns: [{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#shipmentGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã lô hàng",
					field: 'code',
					template:dataItem=> '<a class="#=shipmentId#" href="javascript:void(0);" ng-click=vm.showSix(dataItem)><u> {{dataItem.code}}</u></a>',
			        width: 120,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Mã hợp đồng",
					field: 'contractCode',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Loại lô hàng",
			        field: 'type',
			        width: 90,
			        template :  
			        "# if(type == 1){ #" + "#= 'Lô hàng nội' #" + "# } " +
			        "else if (type == 2) { # " + "#= 'Lô hàng ngoại' #"+ "#} " +
			        "#",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					
				},
				{
					title: "Ngày giao <br> hàng",
			        field: 'shiperDate',
			        width: 95,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Người tạo",
			        field: 'createdBy',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				 
				{
					title: "Đơn vị",
			        field: 'projInvestProjectId',
			        width: 250,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'status',
					 template :  
					        "# if(status == 1)" +
					        "{ #" + "#= 'Mới tạo' #" + "# } " +
					        "else if (status == 2) " +
					        "{ # " + "#= 'Đã cập nhật hàng hóa' #"+ "#} " +
					        "else if (status == 3) " +
					        "{ # " + "#= 'Đã định lượng' #"+ "#} " +
					        "else if (status == 4) " +
					        "{ # " + "#= 'Đã định giá' #"+ "#} " +
					        "else if (status == 5) " +
					        "{ # " + "#= 'Đã tạo YCKT' #"+ "#} " +
					        "else if (status == 6) " +
					        "{ # " + "#= 'Đã tạo BBKT' #"+ "#} " +
					        "else if (status == 7) " +
					        "{ # " + "#= 'Đã nhập kho' #"+ "#} " +
					        "else if (status == 8) " +
					        "{ # " + "#= 'Đã hủy' #"+ "#} " +
					        "#",
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template:dataItem =>
			        	'	<div class="text-center #=shipmentId#"">'+
			        	'		<a  type="button"  class="#=shipmentId# icon_table" uib-tooltip="Cập nhật hàng hóa cho lô hàng" translate>'+
			        	'			<i ng-hide="dataItem.status == 1 || dataItem.status == 2" class="fa fa-pencil-square-o" ng-click=vm.editDetail(dataItem) aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'		<a  type="button"  class="#=shipmentId# icon_table" uib-tooltip="Cập nhật hàng hóa cho lô hàng" translate>'+
			        	'			<i ng-hide="dataItem.status != 1 && dataItem.status != 2" class="fa fa-trash" aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'		<a  type="button" class="#=shipmentId# icon_table" uib-tooltip="Cập nhật lô hàng" translate> '+
			        	'			<i ng-hide="dataItem.status == 1 " class="fa fa-pencil" ng-click=vm.edit(dataItem) aria-hidden="true"></i>'+
			        	'		</a> '+
			        	'		<a  type="button" class="#=shipmentId# icon_table" uib-tooltip="Cập nhật lô hàng" translate>'+
			        	'			<i ng-hide="dataItem.status != 1" class="fa fa-pencil" aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'		<a type="button" class="#=shipmentId# icon_table" uib-tooltip="Xóa" translate >'+
			        	'			<i ng-hide="dataItem.status == 1" class="fa fa-trash" ng-click=vm.Openremove(dataItem) aria-hidden="true"></i>'+
			        	'		</a>'+
			        	'       <a  type="button"  class="#=shipmentId# icon_table" uib-tooltip="Xóa" translate>'+
			        	'			<i ng-hide="dataItem.status != 1" class="fa fa-trash" aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'	</div>',
			        width: 90,
			        field:"actionss"
				}
				,]
			});
		}
		
		vm.listRemove=[{
			title: "Thao tác",
		},
		{
			title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />"
		}]
	
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Mới tạo',
				2:'Đã cập nhật hàng hóa',
				3:'Đã định lượng ',
				4:'Đã định giá',
				5:'Đã tạo YCKT',
				6:'Đã tạo BBKT',
				7:'Đã nhập kho',
				8:'Đã hủy',
			}
		}];
		
		vm.exportExcelGrid = function(){
			impReqManaService.getForExportGrid(vm.shipmentSearch).then(function(d) {
				CommonService.exportFile(vm.shipmentGrid,d.data,vm.listRemove,vm.listConvert);
			});
			
		}
		
		function statusOfShipment(x){
			 if(x == 1)
			 { return "Mới tạo";}
			 else if (x == 2)
			 { return "Đã cập nhật hàng hóa";}
		     else if (x == 3)
		     { return "Đã định lượng";}
		     else if (x == 4){ return "Đã định giá";}
		     else if (x == 5){ return "Đã tạo YCKT";}
		     else if (x == 6){ return "Đã tạo BBKT";}
		     else if (x == 7){ return "Đã nhập kho";}
		     else if (x == 8){ return "Đã hủy";}
		}
		
		function TypeOfShipment(x){
			if(type == 1){ 
				return "Lô hàng nội";
			}else if (type == 2) {
				return "Lô hàng ngoại";
			}
			
		}
		
		vm.showSix = function showSix(dataItem){
	    	var dataItem= dataItem;
	        var teamplateUrl="qlk/shipmentDetail/shipmentDetailList.html";
	        var title="Thông tin chi tiết lô hàng";
			$kWindow.open({
				 options: {
					 modal: true,
					 title: title,
					 visible: false,
					 width: '88%',
					 height: '88%',
					 actions: ["Minimize", "Maximize", "Close"],
					 open: function () {
						 this.wrapper.children('.k-window-content').addClass("fix-footer");
						 $rootScope.$broadcast("shipment.object.data", dataItem);
					 }
				 },                
				 templateUrl: teamplateUrl
			});
		}

		function fillUpdateTable(data) {
			vm.gridUpdateOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data/*
								 * { serverPaging: true, schema: { total:
								 * function (response) { return response.total; },
								 * data: function (response) { return
								 * response.data; }, }, transport: { read: { //
								 * Thuc hien viec goi service url:
								 * Constant.SERVICE_URL +
								 * "reasonRsServiceRest/reason/doSearch",
								 * contentType: "application/json;
								 * charset=utf-8", type: "POST" }, parameterMap:
								 * function (options, type) {
								 * 
								 * vm.reasonSearch.page = options.page
								 * vm.reasonSearch.pageSize = options.pageSize
								 * 
								 * return JSON.stringify(vm.reasonSearch) } },
								 * pageSize: 20 }
								 */,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#shipmentUpdateGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Hãng sản xuất",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Nước sản xuất",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Partnumber",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Serial",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Số lượng",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Thao tác",
			        template:dataItem =>
			        	'<div class="text-center #=shipmentId#""><a '+
						' class=" icon_table #=shipmentId#"'+
						' ng-click=vm.edit(#=shipmentId#)  uib-tooltip="Sửa" translate>'+
						'<i class="fa fa-pencil" aria-hidden="true"></i>'+
					'</a>'+
					'<a type="button"'+
					'class="#=shipmentId# icon_table"  uib-tooltip="Xóa" translate'+
						'ng-click=vm.OpenremoveFile(#=shipmentId#)>'+
						'<i class="fa fa-trash" aria-hidden="true"></i>'+
					'</a>'
						+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		
		vm.add = function add(){

			 var teamplateUrl="qlk/shipment/shipmentPopup.html";
			 var title="Thêm mới lô hàng";
			 var windowId="SHIPMENT"
			 vm.dataFile = [];
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'80%','80%'); 
			 
		 }
		vm.dataFile=[];
		vm.edit = function edit(dataItem){
				vm.showDetail = true;
				vm.shipment =dataItem;
				var teamplateUrl="qlk/shipment/shipmentPopup.html";
				var title="Cập nhật lô hàng";
				var windowId="SHIPMENT"
				vm.doSearchFile.objectId = vm.shipment.shipmentId;
			    vm.doSearchFile.status = vm.shipment.status;
				shipmentDetailService.doSearchFile(vm.doSearchFile).then(function(result){
					CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'80%','80%');
					vm.dataFile=result.plain();
        		});
		}
		
		$scope.$watchGroup(['vm.shipmentFileGrid', 'vm.dataFile'], function(newVal, oldVal) { 
			refreshGrid(vm.dataFile);						
		});
		
		vm.editDetail = function editDetail(dataItem){
			vm.showDetail = true;
// var grid=vm.shipmentGrid;
// var item=grid.table.find("tr div." +id);
// var uid=$(item).parent().parent().attr("data-uid");
// var dataItem=grid.dataSource.getByUid(uid);
			vm.shipment =dataItem;
			var teamplateUrl="qlk/shipment/updateShipment.html";
			var title="Cập nhật hàng hóa cho lô hàng";
			var windowId="SHIPMENT"
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'80%','80%'); 
	}
		
		vm.Openremove = function Openremove(dataItem){
			vm.showDetail = true;
			vm.shipment =dataItem;
			var d = new Date();
			var datestring = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear()
			vm.shipment.cancelDate = datestring;
			var teamplateUrl="qlk/popup/Delete_Popup.html";
			var title="Hủy thông tin lô hàng";
			var windowId="SHIPMENT"
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'80%','80%'); 
	}
		
		vm.cancel= cancel ;
		function cancel(){
				vm.reason={};
				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		var saveFile = [];
		var updateFile = [];
		
		$scope.$on("shipment.object.file", function(event, dataItem) {
			if(dataItem == true){
				saveFileSERVICE();
				deleteFileSERVICE();
				updateFileSERVICE();
			}
		});
		
		function saveFileSERVICE(){
			if(saveFile.length > 0){
			shipmentService.saveFileSERVICE(saveFile).then(function(result){
    		}, function(errResponse){
                if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại!"));
                } else {
                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới lô hàng!"));
                }
	        });
			}
		}
		function updateFileSERVICE(){
			if(updateFile.length > 0){
			shipmentService.updateFileSERVICE(updateFile).then(function(result){
    		}, function(errResponse){
                if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại!"));
                } else {
                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới lô hàng!"));
                }
	        });
			}
		}
		function deleteFileSERVICE(){
			if(removeFile.length > 0){
			shipmentService.deleteFileSERVICE(removeFile).then(function(result){
    		}, function(errResponse){
                if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại!"));
                } else {
                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới lô hàng!"));
                }
	        });
			}
		}
		
		
		vm.save= function save(data,isCreateNew){
			vm.checkIdObject = false;
			vm.check = 0;
			saveFile = [];
		    updateFile = [];
			var shipmentFileGridSave = $("#shipmentFileGrid").data().kendoGrid.dataSource.view();
			for(var i=0 ; i < shipmentFileGridSave.length ; i++){
				if(shipmentFileGridSave[i].attactmentId != undefined ){
					updateFile.push(shipmentFileGridSave[i]);
				}else{
					saveFile.push(shipmentFileGridSave[i]);
				}
			}
			for(var i = 0; i< shipmentFileGridSave.length; i++){
				if(shipmentFileGridSave[i].appParam.code === "choese" || shipmentFileGridSave[i].appParam.name == "~~ Chọn ~~" )
				{
					vm.check = vm.check +1;
				}
			}
			if(vm.check == 0){
				if(isCreateNew) {
	            	shipmentService.createShipment(data).then(function(result){
	            			toastr.success("Thêm mới thành công!");
	                        vm.shipment.shipmentId = result;
	                        doSearch();
	                        if(saveFile.length > 0){
	                        	for(var i=0;i< saveFile.length;i++){
	                        		saveFile[i].objectId = result;
	                        		saveFile[i].type = "1";
	                        		saveFile[i].code = vm.shipment.code;
	                        		vm.checkIdObject = true;
	                        	}
	                        }
	                        CommonService.dismissPopup();
	                        $rootScope.$broadcast("shipment.object.file", true);
	            		}, function(errResponse){
			                if (errResponse.status === 409) {
			                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại!"));
			                	$rootScope.$broadcast("shipment.object.file", false);
			                } else {
			                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới lô hàng!"));
			                	$rootScope.$broadcast("shipment.object.file", false);
			                }
	    		        });
	            	} else {
	            		shipmentService.updateShipment(data).then(function(result){
	            			toastr.success("Cập nhật thành công!");
	            			doSearch();
	            			if(saveFile.length > 0){
	                        	for(var i=0;i< saveFile.length;i++){
	                        		saveFile[i].objectId = result;
	                        		saveFile[i].type = "1";
	                        		saveFile[i].code = vm.shipment.code;
	                        		vm.checkIdObject = true;
	                        	}
	                        }
	            			CommonService.dismissPopup();
	            			$rootScope.$broadcast("shipment.object.file", true);
	            		}, function(errResponse){
	            			if (errResponse.status === 409) {
			                	toastr.error(gettextCatalog.getString("Mã lý do đã tồn tại"));
			                	$rootScope.$broadcast("shipment.object.file", false);
			                } else {
			                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
			                	$rootScope.$broadcast("shipment.object.file", false);
			                }
	    		        });
	            	}
			}else{
				toastr.error("Loại file chưa chọn đúng!");
			}
		}
		
		vm.openDepartment=openDepartment
		function openDepartment(){
			var obj={};
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data=result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
					noRecords: true,
					messages: {
						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
					},
					pageable: {
						refresh: false,
						 pageSizes: [10, 15, 20, 25],
						messages: {
			                display: "{0}-{1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang",
			                empty: "Không có kết quả hiển thị"
			            }
					},
					columns:[ {
						title: "Mã",
						field: "code",
						width: 120
					}, {
						title: "Tên",
						field: "text",
						width: 120
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%');
			});
		}
		
		vm.onSave=onSave;
		
		function onSave(data){
			vm.shipmentSearch.createdDeptName=data.text;
			vm.shipmentSearch.createdDeptId = data.code;
		}
		vm.remove = function remove(data)
		{
			shipmentService.remove(data).then(function(result){
    			toastr.success("Hủy thông tin lô hàng thành công!");
    			doSearch();
    			CommonService.dismissPopup();
    		}, function(errResponse){
    			if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Lỗi không thể xóa"));
                } else {
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái"));
                }
	        });
		}
		vm.cancelDept = function()
		{
			vm.shipmentSearch.createdDeptName = undefined;
			vm.shipmentSearch.createdDeptId = undefined;
		}
		vm.cancelStatis = function()
		{
			vm.shipmentSearch.listStatus = ['1'];
		}
		vm.cancelCreatedBy = function()
		{
			vm.shipmentSearch.createName = undefined;
		}
		
		vm.addGroupOptions = {
	            dataTextField: "name",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.criteria.useGroupId = dataItem.groupId; // thành id
	                vm.criteria.useGroupName = dataItem.groupCode;// thành
																	// name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.criteria.useGroupName,pageSize:vm.addGroupOptions.pageSize }).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.groupAdd #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                    vm.criteria.useGroupId = null; // thành id
	                    vm.criteria.useGroupName = null;// thành name
	                }
	            },
	            ignoreCase: false
	        };
		
		function fillFileTable(data) {
			var dataSource = new kendo.data.DataSource({
                pageSize: 20,
                data: data,
                autoSync: false,        
                schema: {
                    model: {
                        id: "shipmentGoodsDetailGrid",
                    	fields: {
                    		stt: {editable: false},
                    		name: {editable: false},
                    		appParam: { defaultValue: { name: "~~Chọn~~", code : "choese"} },
                    		acctions : {editable: false},
                    	}
                    }
                }
            });
			vm.gridFileOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:dataSource,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSize : 20,
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#shipmentFileGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Tên file",
					field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Loại file",
					field: 'appParam',
			        width: 150,
			        editor: categoryDropDownEditor, 
			        template: "#=appParam.name#" ,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				} ,{
				 title: "Xóa",
				 template: dataItem => 
					'<div class="text-center #=attactmentId#""> '+
						'<a type="button" class="#=attactmentId# icon_table" uib-tooltip="Xóa" translate> '+
							'<i class="fa fa-trash" ng-click=caller.removeRowFile(dataItem) ria-hidden="true"></i>'+
						'</a>'+
					'</div>' ,
				 width: 150,
				 field:"acctions"
				 }
				,]
			});
		}
		
		vm.dataRropFile = [];
		function loadFileData(){
			shipmentService.getFileDrop().then(function(result){
				vm.dataRropFile  = result.plain();
    		}, function(errResponse){
    			if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Lỗi không thể xóa"));
                } else {
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái"));
                }
	        });
		}
		
		function categoryDropDownEditor(container, options) {
			 $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    suggest: true,
                    dataTextField: "name",
                    dataValueField: "code",
                    dataSource: vm.dataRropFile,
                });
        }
		
		var removeFile =[];
		vm.removeRowFile = removeRowFile;
		function removeRowFile(dataItem) {
			removeFile =[];
			var grid = vm.shipmentFileGrid;
			var row = grid.tbody.find("tr[data-uid='" + dataItem.uid + "']");
			if(dataItem.attactmentId != undefined){
				removeFile.push(dataItem);
				grid.removeRow(row);
			}else{
				grid.removeRow(row);
			}
		}
						
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.shipmentGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.shipmentGrid.showColumn(column);
            } else {
            	vm.shipmentGrid.hideColumn(column);
            }
        	
        	
        }
			
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
            
        // Upload file by drag and click
            
            $scope.fileUploadFinished = false;
            $scope.onFileSelect = function($files) {
                for (var i = 0; i < $files.length; i++) {
                    var file = $files[i];
                    $scope.upload = $upload.upload({
                        url: 'system/upload.php',
                        method: 'POST',
                        file: file
                    }).progress(function(evt) { 
                         // progress bar ...
                    }).success(function(data, status, headers, config) {
                        // file is uploaded successfully
                    });
                }
            };
		// /END
	}
})();