(function() {
	'use strict';
	var controllerId = 'deliveryorderController';
	
	angular.module('MetronicApp').controller(controllerId, deliveryorderController);
	
	
	
	//1.show/hide column field
	
	//2.choose file create order
	function deliveryorderController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,deliveryOrderService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant,impReqManaService) {
		//impReqManaService,creImpNoteService
		var vm = this;
		 vm.order={};
		vm.orderSearch ={
				listStatus : ['1'],
			
		};
		vm.orderGoodsSearch ={};
		vm.orderGoodsDetailSearch ={};
		vm.showcode=false;
		vm.showSearch = true;
		vm.showDetail = false;
		vm.showTabOne = true;
		vm.showAdvancedSearch = false;
		vm.dataList={
				hederList:[],
				data:[]
		};
		vm.validateColums=Constant.COLUMS_VALIDATE.goods;
		vm.validatorOptions = kendoConfig.get('validatorOptions');

		vm.template='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
        '<span class="k-state-default"><p>#: data.name #</p></span>',
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
      '<span>Mã</span>' +
      '<span>Tên</span>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		vm.gridCommon = [ {
			title: "Mã kho",
			field: "code",
			width: 120
		}, {
			title: "Tên kho",
			field: "name",
			width: 120
		}];
		
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDataTableInforDeliveryOrder([]);
			
		}
		
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				toolbar: [
                    {
                        name: "actions",
                        template:
                      	  '<div class="btn-group pull-right margin-left10 margin_right10">'+
                    '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<i class="action-button excelQLK" aria-hidden="true"></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in vm.imReqMaGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                    '</label>'+                    
                    '</div></div>'
                    }
                    ],
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
							 	vm.count = response.total;
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderServiceRest/order/doSearchDeliveryOrder",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.orderSearch.page = options.page
								vm.orderSearch.pageSize = options.pageSize

								return JSON.stringify(vm.orderSearch)
						}
					},					 
					pageSize: 20
				} ,
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#imReqMaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 75,
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
			        template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showcode(#=orderId#)>#=code#</a>',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại yêu cầu",
					 field: "bussinessType",
				        template :  "# if(bussinessType == 1){ #" + "#= 'Xuất cho đơn vị sử dụng' #" + "# } " +
				        "else if (bussinessType == 2) { # " + "#= ' Xuất ra công trình' #"+ "#} " +
				        "else if (bussinessType == 3) { # " + "#= ' Xuất đi BHSC' #"+ "#} " +
				        "else if (bussinessType == 4) { # " + "#= 'Xuất luân chuyển kho' #"+ "#} " +
				        "else if (bussinessType == 5) { # " + "#= 'Xuất tặng đối tác' #"+ "#} " +
				        "else if (bussinessType == 6) { # " + "#= 'Xuất cho đối tác mượn' #"+ "#} " +
				        "else if (bussinessType == 7) { # " + "#= 'Xuất trả đối tác' #"+ "#} " +
				        "else if (bussinessType == 8) { # " + "#= ' Xuất thanh lý' #"+ "#} " +
				        "else if (bussinessType == 9) { # " + "#= ' Xuất cho đề tài/dự án' #"+ "#} " +
				        "else if (bussinessType == 10) { # " + "#= 'Xuất bán' #"+ "#} " +
				        		"#",
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Kho xuất",
			        field: 'stockName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Người tạo",
					 field: 'createdByName',
			       
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Đơn vị tạo",
					 field: 'createdDeptedName',
			       
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Ký CA",
					 field: 'signState',
			      
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Trạng thái",
					 field: 'status',
			      
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Viết phiếu",
					field: 'writeballot',
			        width: 150,
			        template:
			        	'<div class="text-center #=orderId#""><a '+
						' class=" icon_table #=orderId#"'+
						' ng-click=vm.createNew(#=orderId#)  uib-tooltip="Viết phiếu" translate>'+
						'<i class="fa fa-file-text" aria-hidden="true"></i>'+
					'</a>'+'</div>',
			        	
//			        	'<a class="#=reasonId#" href="javascript:void(0);" ng-click=vm.createNew(#=reasonId#)>#=code#</a>',
			        headerAttributes: {
			        style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				
				,]
			});
		}
		
		
		
		
		function fillDataTableInforDeliveryOrder(data) {
			vm.addInforDeliveryOrderOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				dataSource: data ,
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#addInforDeliveryOrder").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
				}, {
					title: "Tên hàng",
			        field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'note',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Số lượng yêu cầu",
			        field: 'note',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				, {
					title: "Tình trạng",
			        field: 'note',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Chi tiết hàng hóa",
			        field: 'note',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		
		function fillDataTableGoodsList(data) {
			vm.goodsListGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				change : onChangeGoodList,
				dataBound: function(e) {
                    var grid = $("#goodsListGrid").data("kendoGrid");
                        grid.select("tr:eq(0)");
               },
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.orderGoodsSearch.page = options.page
								vm.orderGoodsSearch.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsSearch)
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#goodsListGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
					field: 'goodsCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'amount',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tình trạng",
					 field: 'goodsStateName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		function fillDataTableGoodsDetail(data) {
			vm.goodsDetailGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: {
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
							url: Constant.BASE_SERVICE_URL + "orderGoodsDetailServiceRest/orderGoodsDetail/doSearchGoodsDetailForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						    vm.orderGoodsDetailSearch.page = options.page
							vm.orderGoodsDetailSearch.pageSize = options.pageSize
							
							return JSON.stringify(vm.orderGoodsDetailSearch)
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
				columns: [
				{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#goodsDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Serial",
					field: 'serial',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hợp đồng",
			        field: 'contractCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Part number",
			        field: 'partNumber',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Hãng sản xuất",
					 field: 'manufacturerName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Nước sản xuất",
					 field: 'producingCountryName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		//start đơn vị tạo
		vm.openSearchDepartmentCreate=openSearchDepartmentCreate
		function openSearchDepartmentCreate(){
			var obj={};
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
				var data1=result.plain();
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
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data1, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		
		vm.onSave=onSave;
		
		function onSave(data1, popupID){
			if(popupID == 'create'){
				vm.orderSearch.createdDeptedName = data1.text;
				vm.orderSearch.createdDeptedId = data1.code;
			}
			if(popupID == 'to'){
				vm.orderSearch.dvnName = data1.text;
				vm.orderSearch.dvnId = data1.code;
			}
		}
		// end 
		
		vm.createNew = function(id){
			 var teamplateUrl="qlk/deliveryorder/infordeliveryorder.html";
			 var title="Viết phiếu xuất kho";
			 var windowId="Infor_Deliveryorder"
				 vm.orderForNote={};
			 creImpNoteService.getOrderDetailById(id).then(function(d) {
				 vm.orderForNote = d.plain();
//				 vm.orderGoodsSearchForNote.orderId = vm.orderForNote.orderId;
//				 fillDataTableGoodsListForNote([]);
					CommonService.populatePopupCreate(teamplateUrl,title,vm.orderForNote,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
		 }
		
		
		
		function refreshGrid(d) {
			var grid = vm.appParamGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			

		 vm.add = function(){ 
			 vm.isCreateNew = true;
	         vm.showDetail = true;
	         vm.appParam={};
	         vm.title="Thêm mới tham số ứng dụng"
		 }

		 vm.showcode=function(id){
				var teamplateUrl="qlk/deliveryorder/detailImportPopup.html";
				 var title="Chi tiết yêu cầu nhập kho";
				 var windowId="DETAIL_POPUP"
					 vm.order={};
				 impReqManaService.getOrderDetailById(id).then(function(d) {
					 vm.order = d.plain();
					 vm.orderGoodsSearch.orderId = vm.order.orderId;
					 fillDataTableGoodsList([]);
						CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%'); 
					}, function() {
						console.error('Error');
					});	
			  } 
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.imReqMaGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
				});
			}
		}
		
		
		function onChangeGoodList(){
			if ($("#goodsListGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.orderGoodsDetailSearch = dataItem;
//    			fillDataTableGoodsDetail([]);
			}
			
		}
		
		
		
		//mutiselect op
		//  VALIDATE 
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');

		vm.cancelListStatus = function()
		{
			vm.orderSearch.listStatus = [];
		}
		vm.cancellistSignState = function()
		{
			vm.orderSearch.listSignState = [];
		}
		vm.cancelListBussinessType = function()
		{
			vm.orderSearch.listBussinessType = [];
		}
		
		vm.cancelTime = function()
		{
			vm.orderSearch.createdDateFrom = undefined;
			vm.orderSearch.createdDateTo = undefined;
		}

		//1.show/hide column field
		//start
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.imReqMaGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.imReqMaGrid.showColumn(column);
            } else {
            	vm.imReqMaGrid.hideColumn(column);
            }
        }
		
		vm.gridColumnShowHideFilter = function (item) { 
            return item.type==null||item.type !=1; 
        };
		// end
		
        
      //2.choose file create order
         vm.submit=submit;
        function submit(){
            	var grid=vm.addInforDeliveryOrder;
            	if(grid){
            	var dataGrid=grid.dataSource.data();
            	 
            var obj=CommonService.validateImport(vm.dataList,vm.validateColums);
            	
            	if(obj.msg){
            		toastr.error(obj.msg);
            		return;
            	}
            	
            	
            	var formData = new FormData();
    			formData.append('filename', vm.dataList.file);
    			
            	Restangular.one(Constant.FILE_SERVICE_TEMP + "?folder=kltd").withHttpConfig(
    					{
    						transformRequest : angular.identity
    					}).customPOST(formData, '', undefined, {
    				'Content-Type' : 'multipart/form-data'
    			}).then(function(successResponse) {
    				console.log(successResponse);
    				if(successResponse.length>0){
    					
    					var item = {pathFile:successResponse[0]}
    					
    					deliveryorder.importGoods(item).then(function(d) {
    						 
    					 }, function() {
    						 
    					 })

    				}
    			});
            	
            	grid.dataSource.data(dataGrid);
                grid.refresh();
            }
            }
        //end
        
        //
        var d = new Date();
		var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
		vm.orderSearch.createdDateFrom = datestring;
		vm.orderSearch.createdDateTo = null;   
	}
})();