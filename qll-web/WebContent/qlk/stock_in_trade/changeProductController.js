(function() {
	'use strict';
	var controllerId = 'changeProductController';
	
	angular.module('MetronicApp').controller(controllerId, changeProductController);
	
	function changeProductController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, changeProductService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        
        vm.dataReasonDelete = [];
        
    	vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
	        '<span class="k-state-default"><p>#: data.name #</p></span>',
			vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
	      '<span>Mã</span>' +
	      '<span>Tên</span>' +
	      	'</div>';
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
		
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Chưa được duyệt',
				2:'Đã duyệt và thực hiện',
				3:'Đã hủy ',
				4:'Đã từ chối',
			}
		}];
		
        var now = new Date();
		var day = now.getDate();
		var month = now.getUTCMonth();
		var year = now.getFullYear();
		
		vm.orderChangeGoodsSearch={
				listStatus:['1'],
				createdDateFrom:  day +"/"+ month +"/"+ year,
		};
		vm.reasonDelete = 
		{
				apply : 10,
				status : 1
		}
		
		/*
		 * $('#createToChangeProduct').kendoDatePicker({ value : day +"/"+ month
		 * +"/"+ year, format: "dd/MM/yyyy", animation : { close : { effects :
		 * "zoom:out", duration : 300 } } });
		 */ 
												
		$('#createFromChangeProduct').kendoDatePicker({
			value : day +"/"+ month +"/"+ year,
			format: "dd/MM/yyyy",
			animation : {
				close : {
					effects : "zoom:out",
					duration : 300
				}
			}
		});
		
		vm.showTabOne = false; 
		vm.showTabTwo = false;
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
			vm.showTabTwo = false;
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
			vm.showTabTwo = true;
		}
		function reasonDelete(){
			changeProductService.getReasonDelete(vm.reasonDelete).then(function(result){
				 vm.dataReasonDelete = result;						 
			 }, function(errResponse){  
				toastr.error(gettextCatalog.getString("Xảy ra lỗi khi lấy Lý do"));
		     });
		}
		
		vm.orderChangeGoods={};
// vm.orderChangeGoodsDetail={goodsCode:'',orderChangeGoodsId:'',goodsStateName:''};
		
		$("#stockTradeStatus").kendoMultiSelect().data("kendoMultiSelect");
		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillAddTable([]);
			fillDetailTable([]);
			reasonDelete();
			signBoardTable([]);
			
			
		}
		
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=orderChangeGoodsId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=orderChangeGoodsId#"'+
				'disble="" ng-click=vm.edit(#=orderChangeGoodsId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=orderChangeGoodsId#"'+
				'ng-click=vm.send(#=orderChangeGoodsId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=orderChangeGoodsId#"'+
				'ng-click=vm.remove(#=orderChangeGoodsId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=orderChangeGoodsId#"'+
				'ng-click=vm.cancelUpgradeLta(#=orderChangeGoodsId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		 
		function fillDataTable(data) {
			
				if(data.status !=1){
					$('#icon').prop("disabled",true);
				}
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				toolbar: [
                    {
                        name: "actions",
                        template: '<div class=" pull-left ">'+
                        '<button class="btn btn-qlk padding-search-right margin_right10 addQLK"'+
      					'ng-click="vm.add()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
      					'<button class="btn btn-qlk padding-search-right TkQLK"'+
      					'ng-click="vm.sendToSign()" uib-tooltip="Trình ký" translate>Trình ký</button>'+
      					'</div>'	
        				+
                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in vm.orderChangeGoodskGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                    '</label>'+
                    '</div></div>'
                    
                    }
                    ],dataBound: function (e) {
    				    var grid = vm.orderChangeGoodskGrid;
    				    grid.tbody.find("tr").dblclick(function (e) {
    				        var dataItem = grid.dataItem(this);
    				        vm.showDetail(dataItem.orderChangeGoodsId)
    				    });
    				},
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							 vm.count =response.total
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderChangeGoodsRsServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
           
							    vm.orderChangeGoodsSearch.page = options.page
								vm.orderChangeGoodsSearch.pageSize = options.pageSize

								return JSON.stringify(vm.orderChangeGoodsSearch)

						}
					},					 
					pageSize: 20
				},
				// dataSource: data,
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
				}
				,
				columns: [
					{
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck()'/>",
			        width: 35,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"},
				},
					{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã yêu cầu",
					field: 'code',
			        width: 150,
			        template: '<a class="#=orderChangeGoodsId#" href="javascript:void(0);" ng-click=vm.seeDetail(dataItem)>#=code#</a>',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Kho thay đổi",
			        field: 'stockName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ngày tạo",
			        field: 'createdDate',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Người tạo",
			        field: 'level',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Đơn vị tạo",
			        field: 'departmentName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				, {
					title: "Trạng thái",
					 field: 'status',
			        template :  "# if(status == 1){ #" + "#= 'Chưa được duyệt' #" + "# } " + "if (status == 2) { # " + "#= 'Đã duyệt và thực hiện' #"+ "#}  if(status == 3){ #" + "#= 'Đã hủy' #" + "# }  if(status == 4){ #" + "#= 'Đã từ chối' #" + "# }#",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template:
			        '<div class="text-center #=orderChangeGoodsId#"" id="icon">' +
			        '<a type="button" class="icon_table #=orderChangeGoodsId#" ng-click="vm.edit(dataItem)" id="sua" uib-tooltip="Sửa" translate>' +
			        '<i class="fa fa-pencil" aria-hidden="true"></i></a>' +
			        '<a type="button" class="#=orderChangeGoodsId# icon_table" uib-tooltip="Xóa" id="xoa" translate ng-click="vm.removeDetail(dataItem)">' +
			        '<i class="fa fa-trash" aria-hidden="true"></i></a>' +
			        '</div>'
						,
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;font-weight:bold;"
					}
				}
				,]
			});
		}
			
		
		function fillAddTable(data) {
			vm.gridAddOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderChangeGoodsRsServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.orderChangeGoodsSearch.page = options.page
								vm.orderChangeGoodsSearch.pageSize = options.pageSize

								return JSON.stringify(vm.orderChangeGoodsSearch)

						}
					},					 
					pageSize: 20
				},
				// dataSource: data,
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
			        template: dataItem => $("#reasonGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã yêu cầu",
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
					title: "Kho thay đổi",
					field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Ngày tạo",
					field: 'createdDate',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị tạo",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Đã được duyệt' #" + "# } " + "else if (status == 1) { # " + "#= 'Chưa được duyệt' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Thao tác",
			        template:
			        	'<div class="text-center #=reasonId#""><button type="button"'+
					' class="btn btn-default padding-button box-shadow  #=reasonId#"'+
					' ng-click=vm.edit(#=reasonId#)>'+
					'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=reasonId#"'+
					'ng-click=vm.remove(#=reasonId#)>'+
					'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
					"<script>" +
					"" +
					"</script>"
					+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
			
		function fillDetailTable(data) {
			vm.detailGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
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
			        template: dataItem => $("#detailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng trước thay đổi",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng trước thay đổi",
					field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng thay đổi",
					field: 'createdDate',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị tính",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Serial thay đổi",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Đã được duyệt' #" + "# } " + "else if (status == 1) { # " + "#= 'Chưa được duyệt' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Mã hàng sau thay đổi",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,
				{
					title: "Serial sau thay đổi",
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
					title: "Xóa",
					template:
							'<div class="text-center #=orderChangeGoodsId#"" id="icon">'+
								'<a type="button" class="#=orderChangeGoodsId# icon_table" uib-tooltip="Xóa" id="xoa" translate ng-click="vm.removeOld(dataItem)">'+
									'<i class="fa fa-trash" aria-hidden="true"></i>'+
								'</a>'+
							'</div>'
					,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;font-weight:bold;"
					},
			        attributes: {
						style: "text-align:left;"
					}
			        
				}]
			});
		}
		
		function signBoardTable(data) {
			vm.signBoardGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
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
			        template: dataItem => $("#signBoard").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Xóa",
					field: 'code',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Mã yêu cầu",
					field: 'goodsName',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		function refreshGrid(d) {
			var grid = vm.orderChangeGoodskGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
		vm.exportExcelGrid = function(){
			changeProductService.doSearch(vm.orderChangeGoodsSearch).then(function(d) {
				CommonService.exportFile(vm.orderChangeGoodskGrid,d.data,vm.listRemove,vm.listConvert);
			});
			
		}
		 
		 vm.add = function add(){
			 var teamplateUrl="qlk/stock_in_trade/addProductPopup.html";
			 var title="Thêm mới yêu cầu thay đổi hàng hóa";
			 var windowId="CHANGE_STOCK_IN_TRADE"
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'90%','90%'); 
			 
		 }
		 
		vm.signBoard = signBoard;
		function signBoard(){
			 var teamplateUrl="qlk/stock_in_trade/signBoardPopup.html";
			 var title="Trình ký thay đổi yêu cầu hàng khóa trong kho";
			 var windowId="CHANGE_STOCK_IN_TRADE ";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','400'); 
			 
		}
		
		vm.edit=edit;
		function edit(dataItem){
			vm.showDetail = true;
			vm.orderChangeGoods = dataItem;
			var teamplateUrl = "qlk/stock_in_trade/addProductPopup.html";
			 var title = "Thêm mới yêu cầu thay đổi hàng hóa";
			 var windowId = "CHANGE_STOCK_IN_TRADE";
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.orderChangeGoods,vm,windowId,true,'80%','80%'); 
		}
		
		function goTo(menuKey) {
			var template = Constant.getTemplateUrl(menuKey);
			postal.publish({
				channel: "Tab",
				topic: "open",
				data: template
			});
			$rootScope.isCreatAsset = false;
			$rootScope.$broadcast("cat.detail.reload");
		}
		
// function addDetail(){
// changeProductService.createDetail(data).then(function(result){
// toastr.success("Thêm chi tiết thành công!");
// });
// }
		
		vm.save = save;
		function save(data,isCreateNew){
			var formattedNumber = ("000" + data.orderChangeGoodsId).slice(-6);
			var rand=Math.floor(Math.random()*900000) + 99999;
			if(data.orderChangeGoodsId == null){
				if(isCreateNew) {
                		data.createdDate=new Date();
                		data.status="1";
                		data.code= "YCTD_"+data.stockId+"/"+year.toString().substr(-2)+"/"+rand;
                		changeProductService.createStock(data).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.orderChangeGoods.orderChangeGoodsId = result;
                            doSearch();
                            CommonService.dismissPopup();
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	}} else {
                		
                		data.code= "YCTD_"+data.stockId+"/"+year.toString().substr(-2)+"/"+formattedNumber;
                		data.updateDate=new Date();
                		changeProductService.updateStock(data).then(function(result){
                			toastr.success("Cập nhật thành công!");
                			doSearch();
                			CommonService.dismissPopup();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			
		}
		
		vm.removeDetail=removeDetail;
		function removeDetail(dataItem){
			vm.showDetail = true;
			vm.orderChangeGoods =dataItem;
			var d = new Date();
			var datestring = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear();
			vm.orderChangeGoods.cancelDate = datestring;
			var teamplateUrl = 'qlk/stock_in_trade/Delete_Popup.html';
			var title="Hủy yêu cầu thay đổi";
			var windowId="CHANGE_STOCK_IN_TRADE";
			CommonService.populatePopupCreate(teamplateUrl,title,vm.orderChangeGoods,vm,windowId,false,'50%','50%');
			
		}
		
		vm.remove = function remove(data){
			  confirm('Xác nhận hủy',function(){
			changeProductService.remove(data).then(function(result) {
			  toastr.success("Hủy thành công!");
			  doSearch(); 
			  CommonService.dismissPopup();
			  },function(errResponse) { 
				  if (errResponse.status === 409) {
	                	toastr.error(gettextCatalog.getString("Lỗi không thể hủy"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái"));
	                }
			}); 
		  }) 
		 }
		 
		
		vm.canceldoSearch= function (){
			vm.showDetail = false;
			vm.appParamSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			vm.showDetail = false;
			var grid = vm.orderChangeGoodskGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.orderChangeGoodskGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.orderChangeGoodskGrid.showColumn(column);
            } else {
            	vm.orderChangeGoodskGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.listRemove=[{
			title: "Thao tác",
		},
		{
			title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />"
		}]
		
		vm.seeDetail= seeDetail;
		function seeDetail(dataItem){
			 vm.showTabOne = true;
			 vm.showTabTwo= false;
			 vm.orderChangeGoods = dataItem;
			 var teamplateUrl="qlk/stock_in_trade/detailStockTradePopup.html";
			 var title="Thông tin yêu cầu thay đổi hàng hóa";
			 var windowId="CHANGE_STOCK_IN_TRADE";		 
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.orderChangeGoods,vm,windowId,true,'800','400'); 
		}
	}
})();