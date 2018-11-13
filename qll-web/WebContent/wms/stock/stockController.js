(function() {
	'use strict';
	var controllerId = 'stockController';
	
	angular.module('MetronicApp').controller(controllerId, stockController);
	
	function stockController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, stockService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.folder = '';
		vm.lstStockCell = [];
		vm.showSearch = true;
		vm.stockConfig = {};
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.stockSearch={
				status:'1'
		};
		vm.stock={};
		
		vm.rowIndex = 0;
		vm.stockDeliveryConfig=[];
		vm.stockDeliveryConfigTempt =null;
		vm.dataAppParam=[];
		vm.appParamSearch= {
				status:'1',
				parType:'STOCK_TYPE'
		};
		
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã kho</p>' +
      '<p class="col-md-6 text-header-auto">Tên kho</p>' +
      	'</div>';
		vm.stockid={};
		vm.appParam={};
		vm.stockCellSearch={};
		vm.headerTemplate='<div class="dropdown-header k-widget k-header">' +
	      '<span>Mã</span>' +
	      '<span>Tên</span>' +
	      '<span>Đơn vị quản lý</span>'
	      	'</div>';
			vm.commonSearch = {name: '', code: '',departmentName:''};
			vm.gridCommon = [ {
				title: "Mã",
				field: "code",
				width: 120
			}, {
				title: "Tên",
				field: "name",
				width: 120
			},{
				title: "Đơn vị quản lý",
				field: "departmentName",
				width: 120
			}];
		vm.selectedPrpject=false;
		vm.changeDataAuto=changeDataAuto;
		function changeDataAuto(id){
		switch(id){
			case 'createdDeptImpReqMaStock':{
			if(processSearch(id,vm.selectedPrpject)){
			 vm.stock.departmentName = null;
              vm.stock.departmentId =null;
			  vm.selectedPrpject=false;	
			 }
				break;
			}
		 }
		
		}
		/*vm.changeDataAuto1=changeDataAuto1;
		function changeDataAuto1(id){
			if(processSearch(id,vm.selectedPrpject)){
				vm.stockSearch.keySearch2 = null;
				 vm.selectedPrpject=false;	
		 }
		}*/
		
		var check = false;
		 vm.dataAppParamType = [];
		 vm.dataAppParamLevel = [];
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			/* addTablePopup([]); */
			vm.appParamSearch.parType = "STOCK_TYPE";
			stockService.getForComboBox1(vm.appParamSearch).then(function(result){
				 vm.dataAppParamType = result;						 
     		 }, function(errResponse){             
		     });
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
				
		}
		
		 setTimeout(function(){
			  $("#stockIDS").focus();
			},15);
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 vm.formatAction=function(dataItem){
			 var template=
			 '<div class="text-center #=stockId#"">'				 
				 template+='<button type="button"'+
				'class="btn btn-default padding-button box-shadow  #=stockId#"'+
				'disble="" ng-click=vm.edit(#=stockId#)>'+
				'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=stockId#"'+
				'ng-click=vm.send(#=stockId#)>'+
				'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'+
			'<button type="button"'+
			'class="btn btn-default padding-button box-shadow #=stockId#"'+
				'ng-click=vm.remove(#=stockId#)>'+
				'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>'
				+
				'<button type="button" class="btn btn-default padding-button box-shadow #=stockId#"'+
				'ng-click=vm.cancelUpgradeLta(#=stockId#)>'+
				'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
			'</button>';
				template+='</div>';
			 return dataItem.groupId;
		 }
		 var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				 scrollable: false,				
				columnMenu: false,
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				toolbar: [
                    {
                        name: "actions",
                        template: 
                        	 '<div class=" pull-left ">'/*
														 * + '<a class="btn
														 * green btn-outline
														 * padding-search-right"'+
														 * 'ng-click="vm.add()"
														 * uib-tooltip="Thêm
														 * mới" translate><p class="action-button add" aria-hidden="true">Thêm
														 * mới</p></a>'+ '</div>'
														 */	+
           				  '<button class="btn btn-qlk padding-search-right addQLK"'+
                          'ng-click="vm.add()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
        					'</div>'	
             				+
                           	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                         '<i data-toggle="dropdown" uib-tooltip="Cài đặt" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                         '<i class="action-button excelQLK" uib-tooltip="Xuất file excel" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>' +
                         '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                         '<label ng-repeat="column in vm.stockGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                         '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                         '</label>'+
                         '</div></div>'
                    }
                    ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							$("#stockCount").text(""+response.total);
								vm.count=response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
							for(var x in response.data){
								response.data[x].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId
							}
								return response.data;
							},
		                },
					transport: {
						read: {
		                    // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "stockRsServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockSearch.page = options.page
								vm.stockSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockSearch)

						}
					},					 
					pageSize: 10
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
					title: "TT",
					field:"stt",
			        template: function () {
					  return ++record;
					 },
			        width: '5%',
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã kho",
					field: 'code',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên kho",
			        field: 'name',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị quản lý",
			        field: 'departmentName',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mức phân cấp",
			        field: 'levelStock',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Loại kho",
			        field: 'nameStock',
			        width: '15%',
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
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template: 
					'<div class="text-center #=stockId#">'+
						'<button style=" border: none; background-color: white;" ng-click="vm.deliveryConfig(dataItem)" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" class="icon_table #=stockId# " uib-tooltip="Cấu hình" translate>'+
							'<i class="fa fa-cog"   ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true"></i>'+
						'</button>'+
						'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" class="icon_table #=stockId# "  uib-tooltip="Khóa" translate >'+
							'<i class="fa fa-cog " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true"></i>'+
						'</button>'
						
						+'<button style=" border: none; background-color: white;" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" class="icon_table #=stockId#"  ng-click="vm.changeAreaStock(dataItem)" uib-tooltip="Thay đổi vị trí kho" translate>'
							+'<i class="fa fa-table" style="color:steelblue;" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i>'+
						'</button>'
						+'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" class="icon_table #=stockId#"  uib-tooltip="Khóa" translate>'
							+'<i class="fa fa-table " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true"></i>'+
						'</button>'+
						
						'<button style=" border: none; background-color: white;" ng-click="vm.edit(dataItem)" class="icon_table #=stockId#" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  uib-tooltip="Sửa" translate>'
							+'<i class="fa fa-pencil"   ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true"></i>'+
					    '</button>'+
						'<button style=" border: none; background-color: white;" class="icon_table #=stockId#" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  uib-tooltip="Khóa" translate>'
							+'<i class="fa fa-pencil " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true"></i>'+
					    '</button>'+
						
						'<button style=" border: none; background-color: white;" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" ng-click="vm.remove(dataItem)" class="#=stockId# icon_table" uib-tooltip="Xóa" translate >'+
							'<i class="fa fa-trash " style="color:steelblue;" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true" ></i>'+
						'</button>'+
						'<button style=" border: none; background-color: white;" class="#=stockId# icon_table" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" uib-tooltip="Khóa" translate >'+
							'<i class="fa fa-trash " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" aria-hidden="true"></i>'+
						'</button>'+
					'</div>',
			        width: '15%',
			        field:"stt"
				}
				,]
			});
		}
		
		function addTablePopup(data) {
			vm.popupTable = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },				
				
				dataSource: data,
				noRecords: true,
				columnMenu: false,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					 pageSize: 10, 
					refresh: false,
					pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columns: [{
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='caller.chkSelectAll();' ng-model='caller.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.onCheck($event)'/>",
			        width: '5%',
			        columnMenu: false,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				}
					 ,{ title: "TT", 
					 field:"stt",
					template: function () {
						  return ++record;
						 },
					  width: '10%', 
					  headerAttributes: { style:"text-align:center;" }, 
					  attributes: { style:"text-align:center;" }, }
					 
				,  {
					title: "Mã vị trí",
					field: 'code',
			        width: '25%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: '60%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},]
			});
		}
			
		function refreshGrid(d) {
			var grid = vm.stockGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.listRemove=[{
			title: "Thao tác",
		}]
		vm.listConvert=[{
			field:"status",
			data:{
			    0:'Hết hiệu lực',
			    1:'Hiệu lực'
			   }
		}]
		vm.exportExcelGrid = function(){
			vm.stockSearch.page=null;
			vm.stockSearch.pageSize=null;
			var grid = $("#stockGrid").data("kendoGrid").dataSource;
			if(grid.data().length===0){
				toastr.error(gettextCatalog.getString("Không có dữ liệu xuất"));
			}else{
				stockService.doSearch(vm.stockSearch).then(function(d) {
					CommonService.exportFile(vm.stockGrid,d.data,vm.listRemove,vm.listConvert,'Danhsachkho');
			});
			
		}
		}
		 vm.add = add;
		 function add(){
//		  vm.list= { mess:"",
//    		         mess1:""
//    		         };
			
			
			vm.stock.departmentName=null;
			 var teamplateUrl="wms/stock/stockPopup.html";
			 var title="Thêm mới kho";
			 var windowId="STOCK";	 
			 vm.appParamSearch.parType = "STOCK_LEVEL";
			  stockService.getForComboBox2(vm.appParamSearch).then(function(result){
			  vm.dataAppParamLevel = result; }, function(errResponse){
			  });
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','300',"code");
		 }
		 
		 var levelST1;
		vm.edit=edit;
		function edit(dataItem){
		/*
		 * vm.showDetail = true; var grid = vm.stockGrid; var item =
		 * grid.table.find("tr div." +id); var uid =
		 * $(item).parent().parent().attr("data-uid"); var dataItem =
		 * grid.dataSource.getByUid(uid);
		 */
//		  vm.list= { mess:"",
//    		         mess1:""
//    		         };

			;
			vm.stock = dataItem;
			levelST1=dataItem.levelST;
			var teamplateUrl = "wms/stock/stockPopup.html";
			var title = "Sao chép kho";
			var windowId = "EDIT_STOCK";
			 $("#stockGrid").data('kendoGrid').dataSource.read();
			 $("#stockGrid").data('kendoGrid').refresh();
			 vm.appParamSearch.parType = "STOCK_LEVEL";
			  stockService.getForComboBox2(vm.appParamSearch).then(function(result){
			  vm.dataAppParamLevel = result; }, function(errResponse){
			  });
			CommonService.populatePopupCreate(teamplateUrl,title,vm.stock,vm,windowId,false,'800','300',"code"); 
		}
		
//		 vm.list1={
//				 messonename:"",
//				 messtwoname:"",
//				 tranfername:"",
//				 onerepresent:"",
//				 tworepresent:"",
//				 tranferrepresent:""
//				 
//		 }
		vm.saveConfig = saveConfig;
		function saveConfig()
		{
//		if(checkErr1()){
//				if(vm.list1.messonename!==""||vm.list1.messtwoname!==""||vm.list1.onerepresent!==""||
//				vm.list1.tworepresent!==""||vm.list1.tranferrepresent!==""){
//					$('#messonename').focus();
//					return vm.list1;
//				}else{
				 if(objStockConfig==null)
					 {
					 vm.dataconfig.stockId = vm.stockid;
					 stockService.createStockConfig(vm.dataconfig).then(function(result){
//					 vm.list1.messonename="";
//					 vm.list1.messtwoname="";
//					 vm.list1.tranfername="";
						$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
						toastr.success("Thêm mới thành công!");
					}, function(errResponse){
								toastr.error(gettextCatalog.getString("Lỗi thêm mới!"));
							
					});
					 }
				 else
					 {
					 stockService.updateStockConfig(vm.dataconfig).then(function(result){
//							  vm.list1.messonename="";
//							 vm.list1.messtwoname="";
//							  vm.list1.tranfername="";
							   $("#stockGrid").data('kendoGrid').dataSource.read();
								$("#stockGrid").data('kendoGrid').refresh();
								$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
							toastr.success("Cập nhật thành công!");
						}, function(errResponse){
									toastr.error(gettextCatalog.getString("Lỗi cập nhật!"));
								
						});
					 }
//			}
//		}
		
		}

		vm.saveStockCell = saveStockCell;
		function saveStockCell()
		{
		 
		  vm.stockSearch.lstStockCell = [];
		  vm.stockSearch.stockId=stockIds;
			var dataStockCellFromGrid = $('#changeAreaGridted').data("kendoGrid").dataSource.data();
			for(var i = 0; i<dataStockCellFromGrid.length;i++){
				vm.stockSearch.lstStockCell.push(dataStockCellFromGrid[i]);
			}
			stockService.saveListStockCell(vm.stockSearch).then(function(result){
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				toastr.success("Thao tác thành công!");
			}, function(errResponse){
				toastr.error(gettextCatalog.getString("Lỗi cập nhật!"));				
			});
			
			
		}
		// ///////////
		vm.deliveryConfig=deliveryConfig;
		vm.dataconfig=null;
		var objStockConfig =null;
		function deliveryConfig(dataItem){
//			vm.list1={
//				 messonename:"",
//				 messtwoname:"",
//				 tranfername:"",
//				 onerepresent:"",
//				 tworepresent:"",
//				 tranferrepresent:""
//				 
//			}
			var teamplateUrl = "wms/stock/stockDeliveryConfig.html";
			var title = "Cấu hình thông tin trong biên bản bàn giao của kho: VT_VTN_SX";
			var windowId = "CONFIG_STOCK";
			vm.stockid = dataItem.stockId;

			vm.stockConfig = stockService.getStockConfig(vm.stockid);
			vm.stockConfig.then(
					function(result) {
								  vm.dataconfig = result;
								  objStockConfig = vm.dataconfig;
								  
					},function(errResponse){
						toastr.error(gettextCatalog.getString("Lỗi lấy dữ liệu"));
 		        });
			/*
			 * vm.stockDeliveryConfig.then( function(result) { var lst = result;
			 * for(var i=0;i<lst.data.length;i++) {
			 * if(lst.data[i].stockId===vm.stockid) { vm.dataconfig =
			 * lst.data[i]; objStockConfig = vm.dataconfig; return; } }
			 * vm.dataconfig=null; objStockConfig = vm.dataconfig; });
			 */
			CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','550','onename'); 
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
		
		vm.save = save;
		function save(data,isCreateNew){
			data.departmentId=vm.stock.departmentId;
			data.departmentName=vm.stock.departmentName;
/* 			data.parentId=data.stockId; */
			data.status="1";
//				if(checkErr()){
//					if(vm.list.mess !==""||vm.list.mess1!==""){
//						$('#code').focus();
//						return vm.list;
//				}else{
					if(isCreateNew) {
                		
                		stockService.createStock(data).then(function(result){
							if(result.error){
									$('#code').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.stock.stockId = result;
                            doSearch();
                            $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                            vm.stock = {};
                            reset(data);
                             vm.add();
//                            check=false;
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
								$('#code').focus();
    		                	toastr.error(gettextCatalog.getString("Mã kho đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
						if(data.levelST===undefined){
							data.levelST=levelST1;
						}
                		/* data.levelST='4'; */
                		stockService.updateStock(data).then(function(result){
							if(result.error){
									$('#code').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Cập nhật thành công!");
                			 $("#stockGrid").data('kendoGrid').dataSource.read();
							 $("#stockGrid").data('kendoGrid').refresh();
                			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                			 check=false;
                		}, function(errResponse){
                			if (errResponse.status === 409) {
								$('#code').focus();
    		                	toastr.error(gettextCatalog.getString("Mã kho đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
    		                }
        		        });
                	}
//				}
//			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			/* confirm('Xác nhận huỷ bỏ', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			/* 	}); */
		}
		
		vm.remove=remove;
		function remove(dataItem){
			/*
			 * var grid = vm.stockGrid; var item = grid.table.find("tr div."
			 * +id); var uid = $(item).parent().parent().attr("data-uid"); var
			 * dataItem = grid.dataSource.getByUid(uid); dataItem.status = '0';
			 */
			confirm('Xác nhận xóa', function(){
				stockService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa thành công!");
							var sizePage = $("#stockGrid").data("kendoGrid").dataSource.total();
							var sizePage = $("#stockGrid").data("kendoGrid").dataSource.pageSize();
							
							if(sizePage % pageSize === 1){
								var currentPage = $("#stockGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#stockGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
							 $("#stockGrid").data('kendoGrid').dataSource.read();
							 $("#stockGrid").data('kendoGrid').refresh();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} )
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
			if($('#createdDeptImpReqMaStockC').val()===""||$('#createdDeptImpReqMaStockC').val()==null){
				 vm.stockSearch.departmentId=null;
			}else if(departmentIdQ!=null){
				vm.stockSearch.departmentId=departmentIdQ;
			}
			
			  vm.showDetail = false;
			var grid =vm.stockGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		
		vm.findDepartment = function(){
			vm.detailWindow = $kWindow.open({
                options: {
                    modal: true,
                    title: "Chi tiết yêu cầu nhập kho",
                    visible: false,
                    resizable: true,
                    width: '1000',
                    height: '600',
                    actions: ["Minimize", "Maximize", "Close"],
			
                },
                templateUrl: "wms/popup/findDepartmentPopUp.html"

            });
		}
		
		vm.openDepartment = function openDepartment(){
			var obj={};
			// obj.page=1;
			// obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'wms/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Tìm kiếm đơn vị");
				var data=result.plain();
				vm.gridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,
					dataSource: result,
					noRecords: true,
					columnMenu:false,
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
					columns:[{
						title: "TT",
						field: "stt",
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						width: '5%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
						},{
						title: "Mã phòng ban",
						field: "code",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					}, {
						title: "Tên phòng ban",
						field: "text",
						width: '23%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					}, {
						title: "Đơn vị cha",
						field: "parentName",
						width: '20%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:left;"},
					},{
						title: "Ngày hiệu lực",
						field: "effectDate",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
					},{
						title: "Ngày hết hiệu lực",
						field: "endDate",
						width: '15%',
						headerAttributes: {style:"text-align:center;"},
						attributes:{style:"text-align:center;"},
					},{
								title: "Chọn",
								 template:
							        	'<div class="text-center "> '	+
						        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
						        	'			<i id="#=departmentId#" ng-click=onSave(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
						        	'		</a>'
										+'</div>',  
						        width: '7%',
						        field:"stt"
					}
					]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%');
			});
		}
		

		vm.patternOptions1={
			dataTextField: "text",
            select: function(e) {
            	departmentIdQ=null;
                var dataItem = this.dataItem(e.item.index());
                vm.stock.departmentName = dataItem.text;
                vm.stock.departmentId = dataItem.id;
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("departmentServiceRest/" + 'department/getall').post({pageSize:10, page:1, name:$("#createdDeptImpReqMaStock").val().trim()}).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
            '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.text #</div> </div>',
            change: function(e) {
            	if(processSearch('createdDeptImpReqMaStock',vm.selectedPrpject)){
					 vm.stock.departmentId = null;
					 $('#createdDeptImpReqMaStock').val(null);
					  vm.selectedPrpject=false;	
					  }
            },
            close: function(e) {
                // handle the event
              }
		};
		
		vm.patternOptions={
			dataTextField: "text", placeholder:"Nhập mã đơn vị hoặc tên đơn vị",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.stockSearch.departmentName = dataItem.text;
                vm.stockSearch.departmentId = dataItem.id;
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("departmentServiceRest/department/getForAutocompleteDept").post({pageSize:10, page:1, name:$("#createdDeptImpReqMaStockC").val().trim()}).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
            '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.text #</div> </div>',
            change: function(e) {
            	if(processSearch('createdDeptImpReqMaStockC',vm.selectedPrpject)){
					  vm.stockSearch.keySearch2 = null;
					 $('#createdDeptImpReqMaStockC').val(null);
					  vm.selectedPrpject=false;	
					  }
            },
            close: function(e) {
                // handle the event
              }
		};
		
		/* $("#createdDeptImpReqMaStockC").kendoAutoComplete({
			dataTextField: "text",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.stockSearch.keySearch2 = dataItem.text;
                vm.stockSearch.createdDeptedId = dataItem.id;
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("departmentServiceRest/department/getForAutocompleteDept").post({pageSize:10, page:1, name:$("#createdDeptImpReqMaStockC").val().trim()}).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
            '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.text #</div> </div>',
            change: function(e) {

				 if(processSearch('createdDeptImpReqMaStockC',vm.selectedPrpject)){
				 vm.stockSearch.keySearch2 = null;
				 $('#createdDeptImpReqMaStockC').val(null);
				  vm.selectedPrpject=false;	
				  }

            },
            close: function(e) {
                // handle the event
              }
		}); */
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.stockGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.stockGrid.showColumn(column);
            } else {
            	vm.stockGrid.hideColumn(column);
            }
        	
        	
        }

		vm.gridColumnShowHideFilter = function (item) { 
                return item.type===null||item.type !==1; 
        };
		var stockIds;
		vm.changeAreaStock = function(dataItem){
			vm.deleteListStockCell =[];
			vm.lstStockCell =[];
			stockIds=dataItem.stockId;
			 var teamplateUrl="wms/stock/changeAreaStock.html";
			 var title="Thay đổi/bổ sung vị trí trong kho";
			 var windowId="STOCK";
//			 vm.stockid = dataItem.stockId;
			 stockService.getChangeAreaByStock(dataItem).then(function(result){
			    addTablePopup(result.plain()); 
			    
				CommonService.populatePopupCreate(teamplateUrl,title,dataItem,vm,windowId,false,'800','400'); 
				vm.chkAll=false;				 
			   }, function(errResponse){
			  });
			
			
		}
		var departmentIdQ;
		vm.onSave=onSave;
		function onSave(data){
			vm.stock.departmentId=data.id;
			vm.stock.departmentName=data.text;
			/* vm.stockSearch.departmentId=data.id; */
			vm.stockSearch.departmentName=data.text;
			departmentIdQ=data.id;
			vm.stockSearch.departmentId=data.id;
			$('#createdDeptImpReqMaStockC').focus();
		}
		
		vm.cancelDept = function(){
			vm.stockSearch.departmentId = null;
			vm.stock.departmentName = null;
			$('#createdDeptImpReqMaStockC').val(null);
			$('#createdDeptImpReqMaStock').val(null);
			departmentIdQ=null;
		}
		vm.deleteListStockCell =[];
		vm.removeStockCell = function() {
			var selectedRow = [];
			var selectedItem = [];
			var dataItem=[];
			var grid = vm.changeAreaGrid;
			var rowList = grid.lockedTable === undefined ? grid.table.find("tr")
					: grid.lockedTable.find("tr");
			rowList.each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					 dataItem = grid.dataItem(item);
					 if(dataItem.stockCellId!=null)
						 {
							selectedRow.push(dataItem.stockCellId);
						 }
					 else
						 {
						   selectedItem.push(dataItem.code);
						 }
					
				}
			});
			if(selectedRow.length === 0 & selectedItem.length===0)
			{
			    toastr.warning("Bạn chưa chọn bản ghi !");
			}else{
				confirm('Xác nhận xóa', function(){
					 var dataItem = $('#changeAreaGridted')
						.data("kendoGrid").dataSource
						.data();
					 
				for (var i = 0; i < dataItem.length; i++) {
					for (var j = 0; j < selectedRow.length; j++) {
						if (selectedRow[j] === dataItem[i].stockCellId) {
							vm.deleteListStockCell.push(selectedRow[j]);
							dataItem.splice(i, 1);
							i=-1;
							break;
						}
					}
				}
				for (var i = 0; i < dataItem.length; i++) {
					for (var j = 0; j < selectedItem.length; j++) {
						if(dataItem[i].stockCellId===null)
							{
							if (selectedItem[j] === dataItem[i].code) {
								dataItem.splice(i, 1);
								i=-1;
								break;
							}
							}
					}
				}
				  vm.doSearch();
				  toastr.success("Xóa thành công!");
				  for (var i = 0; i < vm.lstStockCell.length; i++) {
						for (var j = 0; j < selectedItem.length; j++) {
								if (selectedItem[j] === vm.lstStockCell[i].code) {
									vm.lstStockCell.splice(i, 1);
									i=-1;
									break;
								}
							
						}
					}
				});
			}
		}
		
		vm.onCheck = function(item){
			if(document.getElementById("chkSelectAll").checked === true){
				document.getElementById("chkSelectAll").checked = false;
			}
		}
		
		vm.chkSelectAll = function(item) {
	    	var grid = vm.changeAreaGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    }
		
		function reset(data)
		{
			data.code = '';
			data.name ='';
			data.type ='';
			data.parentId='';
			data.levelST ='';
			data.description='';
		}
		function getFolder() {
			Restangular.one(RestEndpoint.ORDER_GOOD_UPLOAD_FOLDER_URL+"/folder").get().then(function(data) {
				vm.folder = data.folder;
			  });
		}
		getFolder();
		
		 vm.submit=submit;
	        function submit(data){
	        	if($("#fileStock")[0].files[0] === null){
		    		toastr.warning("Bạn chưa chọn file để import");
		    		return;
		    	}
				if($('.k-invalid-msg').is(':visible')){
					return;
				}
				if($("#fileStock")[0].files[0].name.split('.').pop() !=='xls' && $("#fileStock")[0].files[0].name.split('.').pop() !=='xlsx' ){
					toastr.warning("Sai định dạng file");
					return;
				}
		    		 var formData = new FormData();
						formData.append('multipartFile', $('#fileStock')[0].files[0]); 
						var grid = $('#changeAreaGridted').data("kendoGrid");
						grid.dataSource.data([]);
				     return   $.ajax({
				            url: Constant.BASE_SERVICE_URL+RestEndpoint.STOCK_CELL_URL+"/importStockCell?folder="+ vm.folder,
				            type: "POST",
				            data: formData,
				            enctype: 'multipart/form-data',
				            processData: false,
				            contentType: false,
				            cache: false,
				            success:function(data) {
									  toastr.success("Import thành công!");
//									  data.splice(data.length - 1, 1);
									for(var i = 0; i<data.length;i++){
										data[i].id = i+1;
										grid.dataSource.add(data[i]);
									}  
				                
				            }
				        });
		       
         }
		
		function setHeight() {
			var h = $(window).height();
			window.setTimeout(function() {
				resizeGrid();
			}, 1);
		}

		$(document).ready(function() {
			setHeight();
		});
		$(window).resize(function() {
			setHeight();
		});
		function resizeGrid() {
			var gridElement = $("#changeAreaGridted"), dataArea = gridElement
					.find(".k-grid-content");
			dataArea.height($(window).height() - 250);
		}
		
//		// validate special Characters
//		var specialChars = "<>@!#$%^&*()_+[]{}?:;|'\"\\,./~`-=";
//    			var check = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list.mess="";
//    			    return false;
//    			}
//    			var check1 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list.mess1="";
//    			    return false;
//    			}
//				var check3 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list1.messonename="";
//    			    return false;
//    			}
//				var check4 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list1.messtwoname="";
//    			    return false;
//    			}
//				
//				var check5 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list1.tranfername="";
//    			    return false;
//    			}
//				
//				var check6 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list1.onerepresent="";
//    			    return false;
//    			}
//				
//				var check7 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list1.tworepresent="";
//    			    return false;
//    			}
//				
//				var check8 = function(string){
//    			    for(var i = 0; i < specialChars.length;i++){
//    			        if(string.indexOf(specialChars[i]) > -1){
//    			            return true;
//    			        }
//    			    }
//    			    vm.list1.tranferrepresent="";
//    			    return false;
//    			}
//				
//					vm.list1={
//				 messonename:"",
//				 messtwoname:"",
//				 tranfername:"",
//				 onerepresent:"",
//				 tworepresent:"",
//				 tranferrepresent:""
//				 
//		 }
//				
//				vm.checkErr1 = checkErr1;
//    	    	function checkErr1(name1,name2) {
//    	    		var name1 = $('#onename').val();
//    	    		var name2 = $('#twoname').val();
//    	    		var tranfername=$('#tranfername').val();
//					var onerepresent=$('#onerepresent').val();
//					var tworepresent=$('#tworepresent').val();
//					var tranferrepresent=$('#tranferrepresent').val();
//					
//    	    		if(check3(name1) === true){
//    	    			vm.list1.messonename = "Tên Bên giao chứa ký tự đặc biệt";
//    	    		}
//    	    		if(check4(name2) === true){
//    	    			vm.list1.messtwoname = "Tên Bên giao(2) chứa ký tự đặc biệt";
//    	    		}
//					if(check5(tranfername) === true){
//    	    			vm.list1.tranfername = "Tên Bên vận tải chứa ký tự đặc biệt";
//    	    		}
//					if(check6(onerepresent) === true){
//    	    			vm.list1.onerepresent = "Thông tin đại diện chứa ký tự đặc biệt";
//    	    		}
//					if(check7(tworepresent) === true){
//    	    			vm.list1.tworepresent = "Thông tin đại diện chứa ký tự đặc biệt";
//    	    		}
//					if(check8(tranferrepresent) === true){
//    	    			vm.list1.tranferrepresent = "Thông tin đại diện chứa ký tự đặc biệt";
//    	    		}
//					
//					return vm.list1;
//    	    	}
//				
//			
//				
//				vm.checkErr = checkErr;
//    	    	function checkErr(code,name) {
//    	    		var code = $('#code').val();
//    	    		var name = $('#name').val();
//    	    		
//    	    		if(check(code) === true){
//    	    			vm.list.mess = "Mã kho chứa ký tự đặc biệt";
//    	    		}
//    	    		if(check1(name) === true){
//    	    			vm.list.mess1 = "Tên kho chứa ký tự đặc biệt";
//    	    		}
//					return vm.list;
//    	    	}
		
		vm.getExcelTemplate = function(){
			var fileName="FileBieuMau_Kho";
			CommonService.downloadTemplate(fileName).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		
	}
    	    	//close popup wwhen click outside
    	    	/* $(document).on("click", ".k-overlay", function () {
    	    		 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    	    		}); */
	}
})();
