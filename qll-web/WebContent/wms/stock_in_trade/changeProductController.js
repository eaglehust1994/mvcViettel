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
        vm.folder = '';
        vm.lstStockCell=[];
        vm.orderChangeGoodsDetailPop = [];
        vm.dataReasonDelete = [];
        vm.showFromProject = false;
        vm.showFromDirect = false;
        vm.orderChangeGoodsDetailPop={};
		vm.orderChangeGoods={};
		vm.changGoods=[];
		vm.orderChangeGoodsSearch={};
		vm.user=Constant.userInfo.VpsUserInfo.fullName;
		vm.orderChangeGoodsSearch.createdBy=Constant.userInfo.VpsUserInfo.sysUserId; 
		vm.orderChangeGoodsSearch.fullName=vm.user;
       /*  alert('abc '+Constant.user.casUser.fullName); */
      var  listorderChangeGoodsDetailDTO=[];
      	vm.orderChangeGoodsDetail=[]
        vm.gridCreator = [  {
			title: "Tên đăng nhập",
			field: "loginName",
			width: 120
		},{
			title: "Mã nhân viên",
			field: "employeeCode",
			width: 120
		}, {
			title: "Họ tên",
			field: "fullName",
			width: 120
		}, {
			title: "Email",
			field: "email",
			width: 120
		}, {
			title: "SĐT",
			field: "phoneNumber",
			width: 120
		}];
        
    	vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã kho</p>' +
      '<p class="col-md-6 text-header-auto">Tên kho</p>' +
      	'</div>';
    	
    	vm.userTemplate='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
		vm.userHeaderTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
      '<p class="col-md-6 text-header-auto">Tên nhân viên</p>' +
      	'</div>';
    	
		vm.commonSearch = {name: '', code: ''};
		vm.gridCommon = [ {
			title: "STT",
			field: "#",
	        template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
			width: 40,
	        filterable: false,
	        headerAttributes: {style: "text-align:center;"},
			attributes: {style: "text-align:center;"},
		},{
			title: "Mã kho",
			field: "code",
			headerAttributes: {style: "text-align:center;"},
			attributes: {style: "text-align:left;"},
			width: 80
		}, {
			title: "Tên kho",
			field: "name",
			headerAttributes: {style: "text-align:center;"},
			attributes: {style: "text-align:left;"},
			width: 150
		},{
			title: "Đơn vị quản lý",
			field: "departmentName",
			headerAttributes: {style: "text-align:center;"},
			attributes: {style: "text-align:left;"},
			width: 100
		}
		];
		
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Chưa được duyệt',
				2:'Đã duyệt và thực hiện',
				3:'Đã hủy ',
				4:'Đã từ chối',
			}},
			{field:"signState",
			data:{
				1:'Chưa trình ký',
				2:'Đã trình ký',
				3:'Đã ký ',
				4:'Đã từ chối',
			}
		}];
		
		
		
		vm.orderChangeGoodsSearch.createdDateFrom=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy");
		vm.orderChangeGoodsSearch.listStatus=['1'];
		vm.orderChangeGoodsSearch.listStockId=[];

		vm.reasonDelete = 
		{
				apply : 11,
				status : 1
		}
		
		setTimeout(function(){
			  $("#changeId").focus();
			},15);
												
		// Set ngày mặc định lùi 1 tháng
//		var d = new Date();
//		var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
//		vm.orderChangeGoodsSearch.createdDateFrom = datestring;
		vm.orderChangeGoodsSearch.createdDateTo = null;

		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
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
		     })
		}
		
		
		
		$("#stockTradeStatus").kendoMultiSelect().data("kendoMultiSelect");
		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDetailTable([]);
			fillDetailListTable([]);
			reasonDelete();
			erroTable([]);
			signBoardTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
				
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
		 
		 var record=0;
		 vm.oldSearch={};
		function fillDataTable(data) {
				if(data.status !==1){
					$('#icon').prop("disabled",true);
				}
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				scrollable:false,
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
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
                    '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                    '<i class="action-button excelQLK" uib-tooltip="Xuất file excel" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                    '<label ng-repeat="column in vm.orderChangeGoodskGrid.columns.slice(1,vm.orderChangeGoodskGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                    '</label>'+
                    '</div></div>'
					
                    
                    }
                    ],dataBound: function (e) {
    				    var grid = vm.orderChangeGoodskGrid;
    				    grid.tbody.find("tr").dblclick(function (e) {
    				        var dataItem = grid.dataItem(this);
    				        vm.seeDetail(dataItem)
    				    });
    				},
				dataSource:{
					
					serverPaging: true,
					 schema: {
						 total: function (response) {
							
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
							    $("#changeCount").text(""+response.total);
								 vm.count =response.total;
								var list=response.data;
				        		for(var x in list){
									list[x].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId;
				        			for(var i in $scope.listCheck){
				        				if(list[x].orderChangeGoodsId===$scope.listCheck[i].orderChangeGoodsId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list;
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
								
							    vm.orderChangeGoodsSearch.page = options.page;
								vm.orderChangeGoodsSearch.pageSize = options.pageSize;
								vm.oldSearch=angular.copy(vm.orderChangeGoodsSearch);
								return JSON.stringify(vm.orderChangeGoodsSearch)

						}
					},					 
					pageSize: 10
				},
				// dataSource: data,
				noRecords: true,
				columnMenu: false,
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
					title : "<input type='checkbox' id='checkalllistchange' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck(dataItem)' ng-model='dataItem.selected'/>",
			        width: '3%',
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"},
				},
					{
					title: "TT",
					field:"stt",  
			        width: '3%',
			        columnMenu: false,
			        template: function () {
						  return ++record;
						 },
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
			        width: '13%',
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
			        width: '13%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ngày tạo",
			        field: 'createdDate',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, {
					title: "Người tạo",
			        field: 'fullName',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Đơn vị tạo",
			        field: 'createdDeptName',
			        width: '13%',
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
			        template :  "# if(status === '1'){ #" + "#= 'Chưa được duyệt' #" + "# } " + "if (status === '2') { # " + "#= 'Đã duyệt và thực hiện' #"+ "#}  if(status === '3'){ #" + "#= 'Đã hủy' #" + "# }  if(status === '4'){ #" + "#= 'Đã từ chối' #" + "# }#",
			        width: '13%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				, 
				 {
					title: "Tình trạng ký CA",
			        field: 'signState',
			        width: '10%',
					template :  "# if(signState ==='1'){ #" + "#= 'Chưa trình ký' #" + "# } " + "if (signState ==='2') { # " + "#= 'Đã trình ký' #"+ "#}  if(signState === '3'){ #" + "#= 'Đã ký' #" + "# }  if(signState === '4'){ #" + "#= 'Đã từ chối' #" + "# }#",
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
			        '<div class="text-center #=orderChangeGoodsId#""> '	+
	        	'		<button style=" border: none;" class="#=orderChangeGoodsId# icon_table" ng-show="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId" uib-tooltip="Khóa" translate> '+
	        	'			<i class="fa fa-pencil " style="color:grey" ng-show="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				
	        	'		</button> '+
	        	'		<button style=" border: none; " class="#=orderChangeGoodsId# icon_table"  ng-click="vm.edit(dataItem)" ng-hide="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId" uib-tooltip="Cập nhật" translate>'+
	        	'			<i class="fa fa-pencil"  ng-hide="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId"  aria-hidden="true"></i> '+
	        	'		</button>' +
	        	'		<button style=" border: none; "  type="button" class="#=orderChangeGoodsId# icon_table" ng-show="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId" uib-tooltip="Khóa" translate> '+
	        	'			<i class="fa fa-trash" style="color:grey" ng-show="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i>'+
	        	'		</button> '+
	        	'		<button style=" border: none; " class="#=orderChangeGoodsId# icon_table" ng-click="vm.removeDetail(dataItem)" ng-hide="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId" uib-tooltip="Hủy bỏ" translate>'+
							
	        	'			<i class="fa fa-trash" style="color: steelblue;" ng-hide="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i> '+
	        	'		</button>'
				+'</div>'
						,
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;font-weight:bold;"
					}
				}
				,]
			});
		}
		
		
		vm.bomaykyCA=function(item){
			Restangular.all("signVofficeRsServiceRest/signVoffice/test").post(item.orderChangeGoodsId).then(function(){
			toastr.success("Đúng CMMR!");
			},function(){
			toastr.error("Sai CMMR!");
			});
		}
		
		$scope.listCheck=[];
		vm.handleCheck=handleCheck;
    	function handleCheck(dataItem){
    		if(dataItem.selected){
    		$scope.listCheck.push(dataItem);
    		} else {
    			for(var i=0;i<$scope.listCheck.length;i++){
    				if($scope.listCheck[i].orderChangeGoodsId===dataItem.orderChangeGoodsId){
    				$scope.listCheck.splice(i,1);
    				}
    			}
				$('[name="gridchkselectall"]').prop('checked', false);
    		}
    		}
    	vm.chkSelectAll=chkSelectAll;
		$scope.checkSearch=false;
		function chkSelectAll(){
			var grid = vm.orderChangeGoodskGrid;
	    		chkSelectAllBase(vm.chkAll, grid);
			if(vm.chkAll){
				if( $scope.checkSearch && $scope.dataSearch.length >0){
				$scope.listCheck=$scope.dataSearch;
				} else {
						CommonService.getallData("orderChangeGoodsRsServiceRest/doSearchForCheckAll",vm.orderChangeGoodsSearch).then(function(data){
						$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}
		};
			
		/*
		 * Xóa bản ghi trên Grid
		 */
		vm.deleteImportExcel=function(dataItem){
			confirm('Xác nhận xóa',function (d){
				$('#detailGrid').data('kendoGrid').dataSource.remove(dataItem);
			} );
		}
		
		/*
		 * Grid fill dữ liệu và nhập dữ liệu trong Popup Add và update
		 */
		 /* $(document).on("click", ".k-overlay", function () {
			  $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			}); */
		
		function fillDetailTable(data) {
			data=vm.orderChangeGoodsDetailPop;
			if(vm.orderChangeGoodsDetailPop.orderChangeGoodsId!=null){
			vm.detailGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				sortable  : false,
				batch: true,
				scrollable:false,
				/* dataBound: function() {
					var rows = this.tbody.children();
					var dataItems = this.dataSource.view();
					for (var i = 0; i < dataItems.length; i++)  {
					  kendo.bind(rows[i], dataItems[i]);
					}
				  }	, */			
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                    },
				dataSource: {
					serverPaging: true,
					 schema: {
						 model: {
			                    id: "detailGrid",
			                	fields: {
			                		stt: {editable: false},
			                		goodsCode: {editable: false},
			                		goodsName: {editable: false},
			                		amountChange : {editable: false},
			                		goodsUnitName: {editable: false},
			                		serial :{type: "string",nullable: true},
			                		goodsStateName : {editable: false},
			                		newGoodsCode:  { type: "string",nullable: false},
			                		newSerial: { type: "string",nullable: true},
			                		choose: {editable: false},
				                	}
							},
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
							url: Constant.BASE_SERVICE_URL + "orderChangeGoodsRsServiceRest/orderChangeGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.orderChangeGoodsDetailPop.page = options.page
								vm.orderChangeGoodsDetailPop.pageSize = options.pageSize
								return JSON.stringify(vm.orderChangeGoodsDetailPop)
						}
					},				 
					pageSize: 10
				},
				columnMenu: false,
				noRecords: true,
//				edit: function(e) {
//				    if(e.container.find("[name]").first().attr("name") === "newSerial"){
//				      if (e.model.serial === ""||e.model.serial==null) {
//				        e.sender.closeCell();
//				      }  
//				    }},
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				
				pageable: {
					refresh: false,
					pageSize:10,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				}, 
				columns: [{
					title: "<b>TT</b>",
					field:"stt",
			         template: function () {
						  return ++record;
						 },
			        width: 50,
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "<b>Mã hàng trước thay đổi</b>",
					 field: 'goodsCode', 
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "<b>Tên hàng trước thay đổi</b>",
					 field: 'goodsName', 
					
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<b>Đơn vị tính</b>",
					 field: 'goodsUnitName', 
					
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<b>Serial thay đổi</b>",
					field: 'serial', 
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border:1px solid gray;"
						
					},
				},{
					title: "<b>Mã hàng sau thay đổi</b>",
					field: 'newGoodsCode', 
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border:1px solid gray;"
					},
				}
				,
				{
					title: "<b>Serial sau thay đổi</b>",
					field: 'newSerial', 
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border:1px solid gray;"
					},
				}
				,{
					title: "Xóa",
					field:'choose',
					template: dataItem => 
					'<div class="text-center #=orderChangeGoodsDetailId#""> '+
					'<a type="button" class="#=orderChangeGoodsDetailId# icon_table" uib-tooltip="Xóa" translate> '+
						'<i class="fa fa-trash" ng-click=caller.removeRowFile(dataItem) ria-hidden="true"></i>'+
					'</a>'+
					'</div>'
					,
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;font-weight:bold;"
					},
			        attributes: {
						style: "text-align:left;"
					}
			        
				}]
			});
			}else{
				vm.detailGridOptions = kendoConfig.getGridOptions({
					resizable: true,	
					refresh:true,
					modal: true,
					sortable  : false,
					scrollable:false,
					dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                    },
					dataSource: {
						serverPaging: true,
						 schema: {
							 model: {
				                    id: "detailGrid",
				                	fields: {
				                		stt: {editable: false},
				                		goodsCode: {editable: false},
				                		goodsName: {editable: false},
				                		amountChange : {editable: false},
				                		goodsUnitName: {editable: false},
				                		serial :{type: "string",nullable: true},
				                		goodsStateName : {editable: false},
				                		newGoodsCode:  { type: "string",nullable: false},
				                		newSerial: { type: "string",nullable: true},
				                		choose: {editable: false},
					                	}
								},
			                },
						pageSize: 10
					},
					autoBind: false,
					columnMenu: false,
					noRecords: true,/* 
					edit: function(e) {
					    if(e.container.find("[name]").first().attr("name") === "newSerial"){
					      if (e.model.serial === ""||e.model.serial==null) {
					        e.sender.closeCell();
					      }  
					    }}, */
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
					title: "<b>TT</b>",
					field:"stt",
			         template: function () {
						  return ++record;
						 },
			        width: 50,
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "<b>Mã hàng trước thay đổi</b>",
					 field: 'goodsCode', 
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "<b>Tên hàng trước thay đổi</b>",
					 field: 'goodsName', 
					
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<b>Đơn vị tính</b>",
					 field: 'goodsUnitName', 
					
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "<b>Serial thay đổi</b>",
					 field: 'serial', 
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border:1px solid gray;"
						
					},
				},
				{
					title: "<b>Mã hàng sau thay đổi</b>",
					field: 'newGoodsCode',  
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border:1px solid gray;"
					},
				}
				,
				{
					title: "<b>Serial sau thay đổi</b>",
					 field: 'newSerial', 
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border:1px solid gray;"
					},
				}
				,{
					title: "Xóa",
					field:'choose',
					template: dataItem => 
					'<div class="text-center #=orderChangeGoodsDetailId#""> '+
					'<a type="button" class="#=orderChangeGoodsDetailId# icon_table" uib-tooltip="Xóa" translate> '+
						'<i class="fa fa-trash" ng-click=caller.removeRowFile(dataItem) ria-hidden="true"></i>'+
					'</a>'+
					'</div>'
					,
			        width: 50,
			        headerAttributes: {
						style: "text-align:center;font-weight:bold;"
					},
			        attributes: {
						style: "text-align:left;"
					}
			        
				}]
				});
				
			}
		}
		
		/*
		 * Fill list dữ liệu vào grid chi tiết 1 bản ghi
		 */
		function fillDetailListTable(data) {
			vm.detailListGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				scrollable: false, 
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
							url: Constant.BASE_SERVICE_URL + "orderChangeGoodsRsServiceRest/orderChangeGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.orderChangeGoodsSearch.page = options.page
								vm.orderChangeGoodsSearch.pageSize = options.pageSize
								vm.orderChangeGoodsSearch.orderChangeGoodsId=vm.orderChangeGoods.orderChangeGoodsId;
								return JSON.stringify(vm.orderChangeGoodsSearch)
						}
					},					 
					pageSize: 10
				},
				columnMenu: false,
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
			        template: dataItem => $("#detailListGrid").data("kendoGrid").dataSource.indexOf(dataItem) +  1,
			        width: 50,
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng trước thay đổi",
					field: 'goodsCode',
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
					field: 'amountChange',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị tính",
					field: 'goodsUnitName',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Serial thay đổi",
					field: 'serial',
			        width: 100,
			        name:'serial',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tình trạng",
					 field: 'goodsStateName',
			        template :  "# if(goodsStateName === 1){ #" + "#= 'Bình thường' #" + "# } " + "else if (goodsStateName === 2) { # " + "#= 'Hỏng' #"+ "#} #",
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Mã hàng sau thay đổi",
					field: 'newGoodsCode',
					id:'newGoodsCode',
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
					field: 'newSerial',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				]
			});
		}
		
		
		
//		$scope.$watch('vm.orderChangeGoodsSearch.orderChangeGoodsId', function() {
//			var grid = $("#detailListGrid").data("kendoGrid");	
//			if(grid){
//				grid.dataSource.query({
//					page: 1,
//					pageSize: 10
//				});
//			}
//	    });
//		
//		function onChangeGoodListForNote(){
//			if ($("#detailListGrid").data("kendoGrid").select().length > 0){
//				var tr = $("#detailListGrid").data("kendoGrid").select().closest("tr");
//    			var dataItem = $("#detailListGrid").data("kendoGrid").dataItem(tr);
//    			
//    			vm.orderChangeGoodsSearch = dataItem;
//			}
//		}
		
		
		function signBoardTable(data) {
			vm.signBoardGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
				columnMenu: false,
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
			        width: 50,
			        columnMenu: false,
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
		
		/*
		 * Xuất file lỗi khi import excel
		 */
		vm.exportErrExcelGrid =function() {
			changeProductService.expOrderChangeErrorExcel(vm.objectErr).then(function(d) {
	 				data = d.plain();
	 				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
	 				CommonService.closePopup1();
	 			}).catch( function(){
	 				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
	 				return;
	 			})
	    }
		
		/*
		 * Grid xuất file lỗi import excel
		 */
		 
		function erroTable(data) {
			vm.errGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
				columnMenu: false,
				scrollable: false, 
				pageSize: 10,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					pageSize: 10,
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
			        template: dataItem => $("#detailErrGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 50,
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Dòng lỗi",
					field: 'lineError',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},  {
					title: "Cột lỗi ",
					field: 'columnError',
			        width: 110,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "Nội dung lỗi ",
					field: 'detailError',
			        width: 250,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				
				]
			});
		}
		
		
		/*
		 * refreshGrid
		 */
		function refreshGrid(d) {
			var grid = vm.orderChangeGoodskGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
		/*
		 * Xuất danh sách tồn kho
		 */
		vm.exportExcelGrid = function(){
		
			vm.oldSearch.page=null;
			vm.oldSearch.pageSize=null;
			var ds = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource;
			var ds1 = ds.data();
			 if (ds1.length === 0){
				 toastr.warning(gettextCatalog.getString("Không có dữ liệu xuất "));
			 }else {
				
					changeProductService.doSearch(vm.oldSearch).then(function(d) {
					CommonService.exportFile(vm.orderChangeGoodskGrid,d.data,vm.listRemove,vm.listConvert,"ThayDoiHangHoa");
				});
			 }
			 
			
			
		}
		
			 $scope.$watch('vm.orderChangeGoodsDetailPop.stockId', function(){
				 if(vm.orderChangeGoodsDetailPop.orderChangeGoodsId == null){
					var obj={};
					obj.value="YCTD";
					obj.stockValue=Constant.userInfo.VpsUserInfo.departmentId;
					/* obj.stockId=vm.orderChangeGoodsDetailPop.stockId; */
					CommonService.genCode(obj).then(
							function(d) {
								changeProductService.setCode(d);
								vm.orderChangeGoodsDetailPop.code=d;
						});
				 	}
				});
			 
			 /*
			  * Hiện Popup thêm mới yêu cầu thay đổi
			  */
		 vm.add = function add(){
			 vm.orderChangeGoodsDetailPop={};
			 var teamplateUrl="wms/stock_in_trade/addProductPopup.html";
			 var title="Thêm mới yêu cầu thay đổi hàng hóa";
			 var windowId="CHANGE_STOCK_IN_TRADE_CREATE";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,null,'90%','90%','changepopupStock'); 
		 }
		 
		vm.signBoard = signBoard;
		function signBoard(){
			 var teamplateUrl="wms/stock_in_trade/signBoardPopup.html";
			 var title="Trình ký thay đổi yêu cầu hàng khóa trong kho";
			 var windowId="CHANGE_STOCK_IN_TRADE_CREATE";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','400','changepopupStock'); 
			 
		}
		
		 //hard code
		vm.exportpdf=exportpdf;
		function exportpdf(){
						var obj3={};
						obj3.objectCode=vm.orderChangeGoods.code;
			         	obj3.reportType="PDF";
			         	obj3.reportName="YeuCauThayDoi";
			         	CommonService.exportReport(obj3).then(
								function(data) {
								var binarydata= new Blob([data],{ type:'application/pdf'});
						        kendo.saveAs({dataURI: binarydata, fileName: "YeuCauThayDoi" + '.pdf'});
							}, function(errResponse) {
								toastr.error("Lỗi không export PDF được!");
							});
			         }
		 /*
		  * Hiện Popup thêm mới yêu cầu sửa đổi
		  */
		vm.edit=edit;
		function edit(dataItem){
				vm.orderChangeGoodsDetailPop={};
				vm.orderChangeGoodsDetailPop.orderChangeGoodsId="";
				vm.showDetail = true;
				var teamplateUrl = "wms/stock_in_trade/addProductPopup.html";
				 var title = "Cập nhật yêu cầu thay đổi hàng hóa";
				 var windowId = "CHANGE_STOCK_IN_TRADE_EDIT";
				 vm.orderChangeGoodsDetailPop=dataItem;
				 changeProductService.getOrderChangeById(dataItem.orderChangeGoodsId).then(function(d) {
					 vm.orderChangeGoodsDetailPop.listorderChangeGoodsDetailDTO = d.plain();
					 fillDetailTable(d.plain());
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.orderChangeGoodsDetailPop,vm,windowId,false,'95%','90%','changepopupStock');
					}, function() {
						console.error('Error');
					});
		}
		
		
		/*
		 * chuyển tab
		 */
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
		
			// reset data sau khi thêm mới
			vm.reset=function(){
				vm.orderChangeGoodsDetailPop.orderChangeGoodsId=null;
				vm.orderChangeGoodsDetailPop.stockName=null;
				vm.orderChangeGoodsDetailPop.stockId=null;
				vm.orderChangeGoodsDetailPop.cancelDescription=null;
				vm.orderChangeGoodsDetailPop.code=null;
				$("#orderChange").value="";
			}
			
			/*
			 * Thực hiện lưu yêu cầu thay đổi
			 */
		vm.save1 = save1;
		function save1(){
			
			if(vm.orderChangeGoodsDetailPop.stockId ==null||vm.orderChangeGoodsDetailPop.stockId ===""||vm.orderChangeGoodsDetailPop.stockId ==='undefined'){
					 //$("#changepopupStock").select();
					  $("#changepopupStock").focus();
					toastr.error(gettextCatalog.getString("Kho không được để trống!"));
                	return false;
                }else{
				 if(vm.orderChangeGoodsDetailPop.orderChangeGoodsId == null){
                	vm.prepareDataforSaving();
					vm.orderChangeGoodsDetailPop.createdDate=new Date();
					vm.orderChangeGoodsDetailPop.status="1";
            		vm.orderChangeGoodsDetailPop.code=changeProductService.getCode();
            		changeProductService.createStock(vm.orderChangeGoodsDetailPop).then(function(result){
            			if(result.error){
            				toastr.error(result.error);
            				return Response.ok()
            						.entity(Collections.singletonMap("error", e.getMessage()))
            						.build();
            			}
            				return;
            			}
                		toastr.success("Thêm mới thành công!");
                       /* var sizePage = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.total();
							if(sizePage % 10 === 1){
								var currentPage = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#orderChangeGoodskGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
						$("#orderChangeGoodskGrid").data('kendoGrid').dataSource.read();
						$("#orderChangeGoodskGrid").data('kendoGrid').refresh(); */
						doSearch();
                        $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
            		}, function(errResponse){
		                if (errResponse.status === 400) {
		                	toastr.error(gettextCatalog.getString("Mã hàng sau thay đổi không tồn tại"));
		                	return false;
		                }else {
		                	toastr.error(gettextCatalog.getString("Lỗi không xác định!"));
		                	return false;
				                }
		    		        });
                	} else {
                		vm.orderChangeGoodsDetailPop.updateDate=new Date();
                		vm.prepareDataforSaving();
                		changeProductService.updateStock(vm.orderChangeGoodsDetailPop).then(function(result){
                			if(result.error){
                				toastr.error(result.error);
                				return;
                			}
                			toastr.success("Cập nhật thành công!");
                			/* var sizePage = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.total();
							if(sizePage % 10 === 1){
								var currentPage = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#orderChangeGoodskGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							 }*/
							$("#orderChangeGoodskGrid").data('kendoGrid').dataSource.read();
							$("#orderChangeGoodskGrid").data('kendoGrid').refresh();
                			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                			
                		}, function(errResponse){
                			if (errResponse.status === 400) {
    		                	toastr.error(gettextCatalog.getString("Mã thay đổi không tồn tại"));
    		                }else if(vm.orderChangeGoodsDetailPop.stockId ==null){
    		                	toastr.error(gettextCatalog.getString("Kho không được để trống!"));
    		                	return false;
    		                }else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
				}
		}
		
		/*
		 * đóng Popup
		 */
		vm.cancel1= cancel1 ;
		function cancel1(){
		/* confirm('Xác nhận huỷ bỏ', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
		vm.cancel= cancel ;
		function cancel(){
			CommonService.closePopup1();
		}
		
		/*
		 * Hiển thị Popup hủy bỏ thay đổi yêu cầu
		 */
		vm.removeDetail=removeDetail;
		function removeDetail(dataItem){
			vm.showDetail = true;
			vm.orderChangeGoods =dataItem;
			var d = new Date();
			var datestring = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear();
			vm.orderChangeGoods.cancelDate = datestring;
			vm.orderChangeGoods.createdBy=vm.orderChangeGoods.createdBy;
			vm.reason={};
			vm.orderChangeGoods.cancelReasonName=null;
			changeProductService.getReasonForCombobox(vm.reason).then(function(d) {
				vm.dataReasonDelete = d.plain();
// dataItem.cancelReasonName = d.plain()[0].name;
				}, function() {
					console.error('Error');
				});
			var teamplateUrl = 'wms/stock_in_trade/Delete_Popup.html';
			var title="Hủy yêu cầu thay đổi";
			var windowId="CHANGE_STOCK_IN_TRADE_CREATE";
			CommonService.populatePopupCreate(teamplateUrl,title,vm.orderChangeGoods,vm,windowId,false,'60%','37%');
			
		}
		
		/*
		 * Thực hiện hủy bỏ yêu 
		 */
		vm.remove = function remove(data){
		if(!vm.validator.validate()){
			$("select:first").focus();
		}
		  confirm('Bạn chắc chắn muốn hủy yêu cầu thay đổi?', function(){
			changeProductService.remove(data).then(function(result) {
			  toastr.success("Hủy thành công!");
			  var sizePage = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.total();
			  var pageSize = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.pageSize();
			  
							if(sizePage % pageSize === 1){
								var currentPage = $("#orderChangeGoodskGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#orderChangeGoodskGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
				$("#orderChangeGoodskGrid").data('kendoGrid').dataSource.read();
				$("#orderChangeGoodskGrid").data('kendoGrid').refresh();
			  $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			  },function(errResponse) { 
				  if (errResponse.status === 409) {
	                	toastr.error(gettextCatalog.getString("Lỗi không thể hủy"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái"));
	                }
			}); 
			});
		 }
		 /*
		  * Dừng tìm kiếm
		  */
		vm.canceldoSearch= function (){
			
			vm.showDetail = false;
			vm.appParamSearch={
					status:"1",
			};
			doSearch();
		}
		
		/*
		 * Tìm kiếm yêu cầu thay đổi
		 */
		vm.doSearch= doSearch;
		function doSearch(){
			
			var x=$('#listStatus').val();
			if(x===0){
				vm.orderChangeGoodsSearch.listStatus=null;
			}
			var timestamp=Date.parse();

			var date=new Date;
			if(vm.orderChangeGoodsSearch.createdDateFrom==null||vm.orderChangeGoodsSearch.createdDateFrom===""){
				if(!vm.validator.validate()){
						vm.listMess.errMessage = '';
						vm.listMess.errMessage1 = '';
						$("#createdDateFromChange").focus();
				}
				return false;
			}
			if(!vm.validator.validate()){
				if($('#err1').text()!==""){
					vm.listMess.errMessage1='';
					vm.listMess.errMessage=''
					$('#createdDateFromChange').focus();
						return false;
				}else if($('#err2').text()!==""){
					vm.listMess.errMessage1='';
					vm.listMess.errMessage='';
					$('#createdDateToChange').focus();
						return false;
				} 
				
			}
			if(vm.listMess.errMessage1!==""){
					$("#createdDateFromChange").focus();
					return false;
				}else if(vm.listMess.errMessage!==""){
					$('#createdDateToChange').focus();
						return false;
				}
			
			
			vm.showDetail = false;
			var grid = vm.orderChangeGoodskGrid;	
			
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
			
		}
		
		/*
		 * clear trạng thái, ngày bắt đầu ,ngày kết thúc
		 */
		vm.clearStatus=function(){
			vm.orderChangeGoodsSearch.listStatus=null;
			$('#listStatus').data("kendoMultiSelect").focus();
			
		}
		vm.clearSearchDate=function(){
			vm.orderChangeGoodsSearch.createdDateTo=null;
			vm.orderChangeGoodsSearch.createdDateFrom=null;
			$('#createdDateFromChange').focus();
		}
		
		/*
		 * Ẩn cột trong grid hiển thị yêu cầu thay đổi trên màn hình chính
		 */
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.orderChangeGoodskGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.orderChangeGoodskGrid.showColumn(column);
            } else {
            	vm.orderChangeGoodskGrid.hideColumn(column);
            }
        	
        	
        }
		
		/*
		 * Đẩy dữ liệu được thêm mới từ file excel hoặc thêm nhanh 1 bản ghi vào list
		 */
		vm.prepareDataforSaving = function(){
			vm.orderChangeGoodsDetailPop.listorderChangeGoodsDetailDTO = [];
			var dataGoodFromGrid = $('#detailGrid').data("kendoGrid").dataSource.data();
			for(var i = 0; i<dataGoodFromGrid.length;i++){
				vm.orderChangeGoodsDetailPop.listorderChangeGoodsDetailDTO.push(dataGoodFromGrid[i]);
			}
		}
		/*
		 * Xóa bản ghi trên grid
		 */
		var removeFile =[];
		vm.removeRowFile = removeRowFile;
		function removeRowFile(dataItem) {
					$('#detailGrid').data('kendoGrid').dataSource.remove(dataItem);
					 $("#detailGrid").data("kendoGrid").refresh();
		}
		
		
		vm.listRemove=[{
			title: "Thao tác",
		},
		{
			title : "<input type='checkbox' id='checkalllistchange' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />"
		}]
		

		function getFolder() {
			Restangular.one(RestEndpoint.ORDER_GOOD_UPLOAD_FOLDER_URL+"/folder").get().then(function(data) {
				vm.folder = data.folder;
			  });
		}
		getFolder();
		
		/*
		 * Thực hiện submit file import excel
		 */
		 vm.submit=submit;
         function submit(data){
	     	if($("#fileChange")[0].files[0] == null){
	    		toastr.warning("Bạn chưa chọn file để import");
	    		return;
	    	}
	     	if($("#fileChange")[0].files[0].name.split('.').pop() !=='xls' && $("#fileChange")[0].files[0].name.split('.').pop() !=='xlsx' ){
        		toastr.warning("Sai định dạng file");
        		return;
        	}
	    		var grid = $("#detailGrid").data("kendoGrid");
            	grid.dataSource.data([]);
	    		var formData = new FormData();
	            formData.append('multipartFile', $('#fileChange')[0].files[0]); 
	            return   $.ajax({
	                url: Constant.BASE_SERVICE_URL+"orderChangeGoodsRsServiceRest/orderChangeGoods/importGoods?folder="+ vm.folder,
	                type: "POST",
	                data: formData,
	                enctype: 'multipart/form-data',
	                processData: false,
	                contentType: false,
	                cache: false,
	                success:function(data) {
	  					 if((data[data.length - 1].lstErrorOrderGoods).length >0){
						 toastr.error(gettextCatalog.getString("Xảy ra lỗi khi import"));
	  					 vm.lstErrImport = data[data.length - 1].lstErrorOrderGoods;
	  					 vm.objectErr = data[data.length - 1];
	  					 var teamplateUrl="wms/stock_in_trade/errList.html";
	  					 var title="Kết quả Import";
	  					 var windowId="ERR_IMPORT";
	  					 CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'50%','50%');
	  					 erroTable(vm.lstErrImport);
	  					 }else {
	              		 	  toastr.success("Import thành công!");
	              		 	  data.splice(data.length - 1, 1);
// var grid = $("#detailGrid").data("kendoGrid");
	              		 	for(var i = 0; i<data.length;i++){
	              		 		data[i].id = i+1;
	              		 		grid.dataSource.add(data[i]);
	              		 	}
	              		 }
	              	}
	            });
          
         }
		
         /*
          * Xem chi tiết 1 yêu cầu thay đổi
          */
		vm.seeDetail= seeDetail;
		function seeDetail(dataItem){
			vm.list={};
			 vm.showTabOne = true;
			 vm.showTabTwo= false;
			 vm.orderChangeGoods = dataItem;
			 if(dataItem.status == 1){
				 vm.list='Chưa được duyệt';
			 }else if(dataItem.status ==2){
				 vm.list="Đã duyệt và thực hiện";
			 }else if(dataItem.status == 3){
				 vm.list="Đã hủy";
			 }else if(dataItem.status == 4){
				 vm.list="Đã từ chối";
			 }
			 
			 var teamplateUrl="wms/stock_in_trade/detailStockTradePopup.html";
			 var title="Thông tin yêu cầu thay đổi hàng hóa";
			 var windowId="CHANGE_STOCK_IN_TRADE_CREATE";		 
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.orderChangeGoods,vm,windowId,true,'90%','90%'); 
		}
		/*
		 * Thêm nhanh 1 mặt hàng thay đổi
		 *//* 
		function checkDplc(goodsItem){
			var isExisted = false;
			var detailGrid = $('#detailGrid').data("kendoGrid");
			  detailGrid.table.find("tr").each(function(idx, item) {
				var row = $(item);
						if(idx==0){
							return;
						}
    					var dataItem = detailGrid.dataItem(item);
    					if(goodsItem.goodsCode == dataItem.goodsCode ){
    						isExisted = true;
    					}
			  })
			   return isExisted;
		} */
		 var list=[];
		vm.patternOptions1={
				
				dataTextField: "goodsName", placeholder:"Nhập nhanh mã hàng, tên hàng để bổ sung",
	            select: function(e) {
	            	var grid = $("#detailGrid").data("kendoGrid");
	            	var dataList=grid.dataSource.data();
	                var dataItem = this.dataItem(e.item.index());
					var check=true;
	             for(var i=0;i<dataList.length;i++){
				 if (dataList[i].goodsId===dataItem.goodsId && dataItem.isSerial!=="1"){
					check=false;
				 }
				  
				 }  
				 if(check){
				vm.orderChangeGoodsDetailPop.goodsId = dataItem.goodsId; // thành id
                vm.orderChangeGoodsDetailPop.goodsCode = dataItem.code;
                vm.orderChangeGoodsDetailPop.goodsName = dataItem.name;
                vm.orderChangeGoodsDetailPop.goodsStateName = "Bình thường";
                vm.orderChangeGoodsDetailPop.goodsUnitName = dataItem.goodsUnitName;
                vm.orderChangeGoodsDetailPop.goodsUnitId = dataItem.goodsUnitId;
                vm.orderChangeGoodsDetailPop.goodsType=dataItem.goodsType;
			    vm.orderChangeGoodsDetailPop.goodsState='1';
			    vm.orderChangeGoodsDetailPop.goodsTypeName=dataItem.goodsTypeName;
			    vm.orderChangeGoodsDetailPop.goodsIsSerial=dataItem.isSerial;
				/* var checkDups = checkDplc(vm.orderChangeGoodsDetailPop);				 
					if(checkDups){
						toastr.warning("Mặt hàng đã tồn tại trong lưới!");
						return;
					} */
				grid.dataSource.add(vm.orderChangeGoodsDetailPop);
				}
				
			
			   
			   
	               
	                
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
						if(vm.orderChangeGoodsDetailPop.stockId){
	                        return Restangular.all("goodsRsServiceRest/" + 'getGoodsForOrder').post({pageSize:10, page:1, keySearch:$("#orderChange").val(),stockId:vm.orderChangeGoodsDetailPop.stockId}).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    } else {
					
						toastr.warning("Bạn chưa chọn kho thay đổi!")
							 options.success([]);
						}
						}
	                }
	            },
	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã hàng</p>' +
	            '<p class="col-md-6 text-header-auto">Tên hàng</p>' +
	            	'</div>',
	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
		                vm.orderChangeGoodsDetailPop.goodsCode ='';
		                vm.orderChangeGoodsDetailPop.goodsName ='';
		                vm.orderChangeGoodsDetailPop.goodsUnitName='';
	                }
	            },
	            close: function(e) {
	                // handle the event
	            	document.getElementById("orderChange").value = "";
	              }
			};
		vm.listMess={
			errMessage:"",
			errMessage1:""
		}
		// validate ngay thang
    	vm.checkErr = checkErr;
    	function checkErr(createdDateFromChange,createdDateToChange) {
    		var createdDateFrom = $('#createdDateFromChange').val();
    		var createdDateTo = $('#createdDateToChange').val();
			/* vm.errMessage = ''; */
			var d = new Date();
			var curDate = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() ;
					if(createdDateTo!==""){
						$('#err2').text("");
						$('#err1').text("");
						if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") > kendo.parseDate(createdDateTo,"dd/MM/yyyy")){
							if(kendo.parseDate(createdDateTo,"dd/MM/yyyy")<= d){
								vm.listMess.errMessage='';
							}else if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") <= kendo.parseDate(createdDateTo,"dd/MM/yyyy")){
								vm.listMess.errMessage='';
							}
							vm.listMess.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
							 $("#createdDateToChange").focus(); 
							return vm.listMess;
						}
						else if(kendo.parseDate(createdDateTo,"dd/MM/yyyy") > d){
							$('#err1').text("");
							if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") <= kendo.parseDate(createdDateTo,"dd/MM/yyyy")){
								if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy")<=d){
									vm.listMess.errMessage1='';
								}else if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy")>d){
									vm.listMess.errMessage1='Ngày tạo phải nhỏ hơn bằng ngày hiện tại'
								}
							}
						    vm.listMess.errMessage = 'Ngày đến phải nhỏ hơn bằng ngày hiện tại';
							 $("#createdDateToChange").focus(); 
							 return vm.listMess;
					}}else if(kendo.parseDate(createdDateFrom,"dd/MM/yyyy") > d){
							$('#err1').text("");
							$('#err2').text("");
						   vm.listMess.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày hiện tại';
						    $("#createdDateFromChange").focus(); 
						   return vm.listMess;
					}
					if(!vm.validator.validate()){
						if($('#err1').text()!==""){
							vm.listMess.errMessage1='';
							vm.listMess.errMessage=''
							$('#createdDateFromChange').focus();
								return false;
						}else if($('#err2').text()!==""){
							vm.listMess.errMessage1='';
							vm.listMess.errMessage='';
							$('#createdDateToChange').focus();
								return false;
						} 
					}
						vm.listMess.errMessage = '';
						vm.listMess.errMessage1 = '';
						return vm.listMess;
				 
//				}
	    }
    	    	
    	$scope.$on("Popup.open", function () {
    		fillDetailTable([]);
        });
    	
    	/* // check all
    	vm.handleCheck = function(item){
			if(document.getElementById("checkalllistchange").checked === true){
				document.getElementById("checkalllistchange").checked = false;
			}
		} */
    	
    	/*vm.chkSelectAll = function(item) {
	    	var grid = vm.orderChangeGoodskGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    }*/
    	
    	 function searchUserOnGridPop() {
        	 CommonService.doSearchForPopup($scope.caller.searchGrid).then(function(result){
        		 var grid = $("#gridViewUser").data("kendoGrid");
        		 grid.dataSource.data(result.plain());
        		 if(result.plain().length > 10){
        			 if(grid){
        					grid.dataSource.query({
        						page: 1,
        						pageSize: 10
        					});
        				}
				}
			});
        }
		// SignVoffice
					vm.rowIndex=null;
					vm.sendToSign = function(){
						var teamplateUrl="wms/popup/SignVofficePopup.html";
						 var title="Trình ký yêu cầu thay đổi trong kho";
						 var windowId="STOCKTRADE";
						 vm.nameToSign="Danh sách yêu cầu trình ký"+"("+$scope.listCheck.length+")";
						 var selectedRow = [];
						 var err="";
						for(var i =0;i< $scope.listCheck.length;i++){
							if(Constant.userInfo.VpsUserInfo.sysUserId !== $scope.listCheck[i].createdBy){
								if(err===""){
									err="Bạn không có quyền trình ký văn bản có mã :" +$scope.listCheck[i].code;
								} else {
								err=err+", "+$scope.listCheck[i].code;
								}
							} else {
								 selectedRow.push($scope.listCheck[i].orderChangeGoodsId);
							}
							 }
							if(err!==""){
								toastr.error(err);
									return;
							}
							if ($scope.listCheck.length === 0) {
								toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
								return;
						}
							var obj={};
							obj.listId=selectedRow;
							obj.type="05";
							obj.reportName="YeuCauThayDoi";
							CommonService.getDataSign(obj).then(function(data){
								if(data.error){
								toastr.error(data.error);
								return;
								}
							var dataList=data.plain();
							
						 
							CommonService.populatePopupVofice(teamplateUrl,title,'01',dataList,vm,windowId,false,'85%','85%');
					
							
					});
					}
					
					vm.getExcelTemplate = function(){
						var fileName="ThayDoiHangHoa";
						CommonService.downloadTemplate(fileName).then(function(d) {
							data = d.plain();
							window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
						}).catch( function(){
							toastr.error(gettextCatalog.getString("Lỗi khi export!"));
							return;
						});
					
				}
					
					vm.onClear = function(comboId){
						switch(comboId){
						case 'changeStockId':{
							vm.orderChangeGoodsSearch.listStockId=[];
								vm.orderChangeGoodsSearch.stockName=null;
								 $('#' + comboId).val("");
								break;
								}
							
						case 'changeCreator':{
							vm.orderChangeGoodsSearch.createdBy=null;
							vm.orderChangeGoodsSearch.fullName=null;
								 $('#' + comboId).val("");
								break;
								}
							}
						
						}
					
					
					
	}
	
		
	
})();
