(function() {
	'use strict';
	var controllerId = 'stockController';
	
	angular.module('MetronicApp').controller(controllerId, stockController);
	
	function stockController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, stockService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
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
		
		var check = false;
		 vm.dataAppParamType = [];
		 vm.dataAppParamLevel = [];
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			addTablePopup([]);
			vm.appParamSearch.parType = "STOCK_TYPE";
			stockService.getForComboBox1(vm.appParamSearch).then(function(result){
				 vm.dataAppParamType = result;						 
     		 }, function(errResponse){             
		     });
				
		}
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
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,		
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
                         '<i data-toggle="dropdown" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                         '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>' +
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
			        template: dataItem => $("#stockGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
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
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên kho",
			        field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị quản lý",
			        field: 'departmentName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mức phân cấp",
			        field: 'levelStock',
			        width: 200,
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
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
			        template: dataItem =>
					'<div class="text-center #=stockId#"">'+
						'<a type="button" class="#=stockId# icon_table" ng-click="vm.deliveryConfig(dataItem)" uib-tooltip="Cấu hình" translate ng-click="">'+
							'<i class="fa fa-cog" aria-hidden="true"></i>'+
						'</a>'
						+'<a type="button" class="icon_table #=stockId#" ng-click="vm.changeAreaStock(dataItem)" uib-tooltip="Thay đổi vị trí kho" translate>'
							+'<i class="fa fa-table" aria-hidden="true"></i>'+
						'</a>'+
						'<a type="button" class="icon_table #=stockId#" ng-click="vm.edit(dataItem)" uib-tooltip="Sửa" translate>'
							+'<i class="fa fa-pencil" aria-hidden="true"></i>'+
					    '</a>'+
						'<a type="button" class="#=stockId# icon_table" uib-tooltip="Xóa" translate ng-click="vm.remove(dataItem)">'+
							'<i class="fa fa-trash" aria-hidden="true"></i>'+
						'</a>'+
					'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		
		function addTablePopup(data) {
			vm.popupTable = kendoConfig.getGridOptions({
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
								/*
								 * vm.stockCell = response.data[0];
								 * if(vm.stockCell != null){
								 * vm.stockCellSearch.stockCellId =
								 * vm.stockCell.stockCellId; }else{
								 * vm.stockCellSearch = {} }
								 */
								vm.stockCellSearch.stockCellId = response.data[0].stockCellId;
								var lstStockCell=[];
								for(var i=0;i<response.data.length;i++)
									{
									   if(response.data[i].stockId==vm.stockid)
										   {
										   lstStockCell.push(response.data[i]);
										   }
									}
								return lstStockCell; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                  // Thuc hien viec goi service
						  url: Constant.BASE_SERVICE_URL +"stockCellRsServiceRest/doSearch", 
						  contentType:"application/json; charset=utf-8",
						  type: "POST"
						 
						},					
						parameterMap: function (options, type) {
								
							    vm.stockCellSearch.page = options.page;
								vm.stockCellSearch.pageSize = options.pageSize;

								return JSON.stringify(vm.stockCellSearch);

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
					title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='caller.chkSelectAll();' ng-model='caller.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.onCheck($event)'/>",
			        width: 35,
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				}
					 ,{ title: "TT", field:"stt", template: dataItem =>
					  $("#changeAreaGridted").data("kendoGrid").dataSource.indexOf(dataItem) +
					  1, width: 70, headerAttributes: { style:
					  "text-align:center;" }, attributes: { style:
					  "text-align:center;" }, }
					 
				,  {
					title: "Mã vị trí",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Ghi chú",
			        field: 'description',
			        width: 150,
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
			stockService.doSearch(vm.stockSearch).then(function(d) {
				CommonService.exportFile(vm.stockGrid,d.data,vm.listRemove,vm.listConvert);
			});
			
		}
		
		 vm.add = add;
		 function add(){
			 var teamplateUrl="qlk/stock/stockPopup.html";
			 var title="Thêm mới kho";
			 var windowId="STOCK";	 
			 vm.appParamSearch.parType = "STOCK_LEVEL";
			  stockService.getForComboBox2(vm.appParamSearch).then(function(result){
			  vm.dataAppParamLevel = result; }, function(errResponse){
			  });
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','200');
		 }
		 
		 
		vm.edit=edit;
		function edit(dataItem){
		/*
		 * vm.showDetail = true; var grid = vm.stockGrid; var item =
		 * grid.table.find("tr div." +id); var uid =
		 * $(item).parent().parent().attr("data-uid"); var dataItem =
		 * grid.dataSource.getByUid(uid);
		 */
			vm.stock = dataItem;
			var teamplateUrl = "qlk/stock/stockPopup.html";
			var title = "Sao chép kho";
			var windowId = "EDIT_STOCK";
			 vm.appParamSearch.parType = "STOCK_LEVEL";
			  stockService.getForComboBox2(vm.appParamSearch).then(function(result){
			  vm.dataAppParamLevel = result; }, function(errResponse){
			  });
			CommonService.populatePopupCreate(teamplateUrl,title,vm.stock,vm,windowId,false,'800','200'); 
		}
		
		vm.saveConfig = saveConfig;
		function saveConfig()
		{
			 if(objStockConfig==null)
				 {
				 vm.dataconfig.stockId = vm.stockid;
				 stockService.createStockConfig(vm.dataconfig).then(function(result){
         			toastr.success("Thêm mới thành công!");
         		}, function(errResponse){
		                	toastr.error(gettextCatalog.getString("Lỗi thêm mới!"));
		                
 		        });
				 }
			 else
				 {
				 stockService.updateStockConfig(vm.dataconfig).then(function(result){
	         			toastr.success("Cập nhật thành công!");
	         		}, function(errResponse){
			                	toastr.error(gettextCatalog.getString("Lỗi cập nhật!"));
			                
	 		        });
				 }
		}
		vm.saveStockCell = saveStockCell;
		function saveStockCell()
		{
			 var dataItem = $('#changeAreaGridted')
				.data("kendoGrid").dataSource
				.data();
			 
		}
		// ///////////
		vm.deliveryConfig=deliveryConfig;
		vm.dataconfig=null;
		var objStockConfig =null;
		function deliveryConfig(dataItem){
			
			var teamplateUrl = "qlk/stock/stockDeliveryConfig.html";
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
			 * if(lst.data[i].stockId==vm.stockid) { vm.dataconfig =
			 * lst.data[i]; objStockConfig = vm.dataconfig; return; } }
			 * vm.dataconfig=null; objStockConfig = vm.dataconfig; });
			 */
			CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'800','550'); 
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
                	if(isCreateNew) {
                		data.status="1";
                		stockService.createStock(data).then(function(result){
                			toastr.success("Thêm mới thành công!");
                            vm.stock.stockId = result;
                            doSearch();
                            CommonService.dismissPopup();
                            vm.stock = {};
                            reset(data);
                            // vm.add();
                            check=false;
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
    		                	toastr.error(gettextCatalog.getString("Mã kho đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
                		stockService.updateStock(data).then(function(result){
                			toastr.success("Cập nhật thành công!");
                			doSearch();
                			 CommonService.dismissPopup();
                			 check=false;
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
			  CommonService.dismissPopup();
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
							doSearch();
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
			  vm.showDetail = false;
			var grid =vm.stockGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 20
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
                templateUrl: "qlk/popup/findDepartmentPopUp.html"

            });
		}
		
		vm.openDepartment = function openDepartment(){
			var obj={};
			// obj.page=1;
			// obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'qlk/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Tìm kiếm đơn vị");
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
                return item.type==null||item.type !=1; 
        };
		
		vm.changeAreaStock = function(dataItem){
			 var teamplateUrl="qlk/stock/changeAreaStock.html";
			 var title="Thay đổi/bổ sung vị trí trong kho";
			 var windowId="STOCK";
			 vm.stockid = dataItem.stockId;
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'800','400'); 
			 vm.chkAll=false;
		}
		
		vm.onSave=onSave;
		function onSave(data){
			vm.stock.departmentId=data.id;
			vm.stock.departmentName=data.text;
		
			vm.stockSearch.keySearch2 = data.text;
		}
		
		vm.cancelDept = function(){
			vm.stockSearch.keySearch2 = undefined;
			vm.stock.departmentName = undefined;
			
		}
		
		vm.removeStockCell = function() {
			var selectedRow = [];
			var dataItem=[];
			var grid = vm.changeAreaGrid;
			var rowList = grid.lockedTable == undefined ? grid.table.find("tr")
					: grid.lockedTable.find("tr");
			rowList.each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
				if (checkbox.is(':checked')){
					 dataItem = grid.dataItem(item);
					dataItem.isActive = 1;
					selectedRow.push(dataItem.stockCellId);
				}
			});
			if(selectedRow.length == 0 )
			{
			    toastr.warning("Bạn chưa chọn bản ghi !");
			}else{
					 var dataItem = $('#changeAreaGridted')
						.data("kendoGrid").dataSource
						.data();
				for (var i = 0; i < dataItem.length; i++) {
					for (var j = 0; j < selectedRow.length; j++) {
						if (selectedRow[j] == dataItem[i].stockCellId) {
							dataItem.splice(i, 1);
							i=-1;
							break;
						}
					}
				}
				  vm.doSearch();
				  toastr.success("Xóa thành công!");
				}
		}
		
		vm.onCheck = function(item){
			if(document.getElementById("chkSelectAll").checked == true){
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
	}
})();