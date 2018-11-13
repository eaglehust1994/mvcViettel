(function() {
	'use strict';
	var controllerId = 'oddCableManagementController';
	
	angular.module('MetronicApp').controller(controllerId, oddCableManagementController);
	
	function oddCableManagementController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,oddCableManagementService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.oddCableManagementSearch={
				status:1
		};
		vm.oddCable = {};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.oddCableManagement={};
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.commonSearch = {name: '', code: ''};
		vm.SearchOddCable2 = {}
		
		vm.listRemove=[{
			title: "Thao tác",
		}]
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Hiệu lực',
				0:'Hết Hiệu lực'
			}
		}]
		
//		Khởi tạo dữ liệu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
//		1. Lấy dữ liệu màn hình khởi tạo
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: false,				
				columnMenu: false,
				editable: false,
				resizable: false,
				scrollable: false,
				scrollable:false,
				toolbar: [
                    {
                        name: "actions",
                        template: 
                        	'<div class=" pull-left ">'+'<button class="btn btn-qlk padding-search-right addQLK"'+
                            'ng-click="vm.add()" translate>Tạo mới</button>'+'</div>'
            				+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.oddCableManagementGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
							
                    }
                    ],
                    dataBound: function (e) {
    				    var grid = vm.oddCableManagementGrid;
    				    grid.tbody.find("tr").dblclick(function (e) {
    				        var dataItem = grid.dataItem(this);
//    				        vm.showDetail(dataItem.orderId);
    				    });
    				},
				
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								$("#oddCableCount").text(""+response.total);
							 	vm.count = response.total;
								return response.total; 
							},
							data: function (response) {
								return response.data; 
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "oddCableServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.oddCableManagementSearch.page = options.page
								vm.oddCableManagementSearch.pageSize = options.pageSize
								return JSON.stringify(vm.oddCableManagementSearch)
						}
					},					 
					pageSize: 10
				},
				// dataSource: data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				dataBound: function () {
					/*var GridDestination = $("#oddCableManagementGrid").data("kendoGrid");                
					 if (GridDestination.dataSource.total() < 10) {
			                GridDestination.pager.element.hide();
			            }
			            else {
			                GridDestination.pager.element.show();
			            }*/
                },
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					width: '7%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: '20%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: '23%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: '11%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},{
					title: "Chiều dài tối thiểu",
			        field: 'amountMinimum',
			        width: '15%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
				},{
					title: "Trạng thái",
			        field: 'status',
			        width: '11%',
			        template : "# if(status == 1)" +"{ #" + "#= 'Hiệu lực' #" + "# } " +
				        		"else if (status == 0) " +"{ # " + "#= 'Hết hiệu lực' #"+ "#} " + "#",
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},{
					title: "Thao tác",
			        template: dataItem =>
					'<div class="text-center"">'+
						'<button style=" border: none; background-color: white;" class=" icon_table " uib-tooltip="Sửa" translate ng-click="vm.edit(dataItem)" ng-hide="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId">'+
							'<i class="fa fa-pencil" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  aria-hidden="true"></i>'+
						'</button>'+
						'<button style=" border: none; background-color: white;" class=" icon_table " ng-show="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId" uib-tooltip="Khóa" translate>'+
							'<i class="fa fa-pencil " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"   aria-hidden="true"></i>'+
						'</button>'+
						'<button style=" border: none; background-color: white;" type="button" class="#=appParamId# icon_table"  uib-tooltip="Xóa" translate ng-click="vm.remove(dataItem)" aria-hidden="true" ng-hide="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId">'+
							'<i class="fa fa-trash" style="color:steelblue;" ng-hide="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId" ></i>'+
						'</button>'+
						'<button style=" border: none; background-color: white;" type="button" class="#=appParamId# icon_table" ng-show="dataItem.status!=1 || dataItem.createdBy!=dataItem.sysUserId"  uib-tooltip="Khóa" translate>'+
							'<i class="fa fa-trash" style="color:grey" ng-show="dataItem.status==0 || dataItem.createdBy!=dataItem.sysUserId"  aria-hidden="true"></i>'+
						'</button>'
					+'</div>',
					width: '12%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
			        field:"stt"
				},]
			});
		}
		
//		Xuất file Excel
		vm.exportExcelGrid = function(){
			var ds = $("#oddCableManagementGrid").data("kendoGrid").dataSource;
			var totalCount = ds.total();
			var pageSizeCount = ds.pageSize();
			ds.pageSize(parseInt(totalCount));
			var ds1 = ds.data();
			 if (ds1.length == 0){
				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
			 }else {
				 
		    	var rows = [{
			        cells: [
			          { value: "STT", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Mã hàng", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Tên hàng", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Đơn vị tính", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Chiều dài tối thiểu", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
			          { value: "Trạng thái", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},		      
			          { value: "Thao tác", textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}		      
			          ]
			      }];
		    	ds.fetch(function(){
		    		var data = this.data();
		    		for (var i = 0; i < data.length; i++){
				          // push single row for every record
				        	rows.push({
					            cells: [
									{ value: i+1 , textAlign: "center", background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ value: data[i].goodsCode, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ value: data[i].goodsName, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
									{ value: data[i].goodsUnitName, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
									{ value: data[i].amountMinimum, background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
									{ value: statusSerial(data[i].status), background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
									{ background: "#D8E4BC", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}
								]
				          })
				        }
		    		var workbook = new kendo.ooxml.Workbook({
				          sheets: [
				            {
				            	columns: [
				                // Column settings (width)
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				              ],
				              
				              // Title of the sheet
				              title: "Danh sách cáp lẻ",
				              // Rows of the sheet
				              rows: rows
				            }
				          ]
				        });
		    		kendo.saveAs({dataURI: workbook.toDataURL(), fileName: "Danh sách cáp lẻ.xlsx"});
		    		ds.pageSize(parseInt(pageSizeCount));
		    	}); 
			 }
	}
	
	function statusSerial(x){
		if(x == 0){ return "Hết hiệu lực";} 
		else if (x == 1) { return "Hiệu lực";}
	}
		
//		Hiện/Ẩn cột
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.oddCableManagementGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.oddCableManagementGrid.showColumn(column);
            } else {
            	vm.oddCableManagementGrid.hideColumn(column);
            }
        }
		
//		Hiện popup thêm mới cáp lẻ
		vm.add = function add(){
			vm.oddCableManagementSearch.goodsId = null;
			vm.oddCableManagementSearch.goodsCode = null; 
			vm.oddCableManagementSearch.goodsName = null;
			vm.oddCableManagementSearch.name = null;
			vm.oddCableManagementSearch.goodsType = null;
			vm.oddCableManagementSearch.goodsUnitId = null;
			vm.oddCableManagementSearch.goodsUnitName = null;
			var teamplateUrl="wms/oddCableManagement/creatNewOddCable.html";
			var title="Thêm mới cáp lẻ";
			var windowId="addNewOddCableManagement";
			CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','180',"goodsName"); 
			vm.mess = '';
		 }
			
		function refreshGrid(d) {
			var grid = vm.oddCableManagementGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
			// confirm('Bạn có muốn hủy thao tác?',function (){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			// });
		}
		
//		Cập nhật cáp lẻ
		vm.edit=edit;
		function edit(dataItem){
			vm.oddCableManagementSearch = dataItem;
			vm.oddCableManagementSearch.goodsId = dataItem.goodsId;
			vm.oddCableManagementSearch.name = dataItem.goodsName;
			vm.oddCable.name = dataItem.goodsName;
			vm.oddCable.code = dataItem.goodsCode;
			var teamplateUrl="wms/oddCableManagement/edit_oddPopup.html";
			var title="Cập nhật cáp lẻ";
			var windowId="editOddCableManagement"
			CommonService.populatePopupCreate(teamplateUrl,title,vm.oddCableManagementSearch,vm,windowId,false,'1000','180',"goodsNameEdit");
			$('#oddCableManagementGrid').data('kendoGrid').dataSource.read();
			$('#oddCableManagementGrid').data('kendoGrid').refresh();
			vm.mess = '';
		}

//		Validate special character
		vm.mess = '';
		var specialChars = "<>@!#$%^&*()_+[]{}?:;|'\"\\,./~`-=";
			var check = function(string){
			    for(var i = 0; i < specialChars.length;i++){
			        if(string.indexOf(specialChars[i]) > -1){
			        	vm.mess = 'Tên hàng hóa chứa ký tự đặc biệt';
			            return true;
			        }
			    }
			    vm.mess = '';
			    return false;
			}
		
		vm.checkErr = checkErr;
    	function checkErr(goodsName) {
    		var goodsName = $('#goodsName').val();
    		if(check($('#goodsName').val()) == true){
    			vm.mess = 'Tên hàng hóa chứa ký tự đặc biệt';
    			return vm.mess;
    		}else{
    			vm.mess = '';
    			return vm.mess;
    		}
    	}		
//		Thêm mới cáp lẻ
		vm.save= function save(data,isCreateNew){			
        	if(isCreateNew) {
				if((vm.oddCableManagementSearch.goodsId == null)||(vm.oddCableManagementSearch.goodsId == undefined)){
					toastr.error("Hàng hóa "+vm.oddCableManagementSearch.name+" không tồn tại trong kho");
					$('#goodsName').focus();
				}
				else{
					data.status = '1';            		
					data.goodsId = vm.oddCableManagementSearch.goodsId;
					data.goodsCode = vm.oddCableManagementSearch.goodsCode;
					data.goodsName = vm.oddCableManagementSearch.goodsName;
					data.goodsType = vm.oddCableManagementSearch.goodsType;
					data.goodsUnitId = vm.oddCableManagementSearch.goodsUnitId;
					data.goodsUnitName = vm.oddCableManagementSearch.goodsUnitName;
					oddCableManagementService.addCreatNew(data).then(function(result){
						toastr.success("Thêm mới thành công!");
						doSearch();
						CommonService.closePopup1();
					}, function(errResponse){
						if (errResponse.status === 409) {
							toastr.error(gettextCatalog.getString("Mã hàng "+vm.oddCableManagementSearch.goodsCode+" đã tồn tại!"));
							$('#goodsName').focus();
						} else {
							toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới cáp lẻ!"));
						}
					});
				}	
        	} else {
				if((vm.oddCable.code == vm.oddCableManagementSearch.goodsCode)&&(vm.oddCable.name != vm.oddCableManagementSearch.goodsName)){
					toastr.error("Hàng hóa "+data.goodsName+" không tồn tại trong kho");
					$('#goodsNameEdit').focus();
					return;
				}
					oddCableManagementService.update(data).then(function(result){
						toastr.success("Cập nhật thành công!");
						$('#oddCableManagementGrid').data('kendoGrid').dataSource.read();
						$('#oddCableManagementGrid').data('kendoGrid').refresh();
						CommonService.closePopup1();
					}, function(errResponse){
						if (errResponse.status === 409) {
							toastr.error(gettextCatalog.getString("Mã hàng "+vm.oddCableManagementSearch.goodsCode+" đã tồn tại!"));
							$('#goodsNameEdit').focus();
						} else {
							toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật!"));
						}
					});
				$('#oddCableManagementGrid').data('kendoGrid').dataSource.read();
				$('#oddCableManagementGrid').data('kendoGrid').refresh();
        	}
		}
		
//		Xoá cáp lẻ
		vm.remove=remove;
		function remove(dataItem){
			confirm('Xác nhận xóa',function(){
				oddCableManagementService.remove(dataItem).then(
					function(d) {
						toastr.success("Xoá thành công!");
						var sizePage = $("#oddCableManagementGrid").data("kendoGrid").dataSource.total();
						var pageSizeCable = $("#oddCableManagementGrid").data("kendoGrid").dataSource.pageSize();
						if(sizePage % pageSizeCable == 1){
							var currentPage = $("#oddCableManagementGrid").data("kendoGrid").dataSource.page();
					        if (currentPage > 1) {
					            $("#oddCableManagementGrid").data("kendoGrid").dataSource.page(currentPage - 1);
					        }
						}
						$('#oddCableManagementGrid').data('kendoGrid').dataSource.read();
						$('#oddCableManagementGrid').data('kendoGrid').refresh();
					}, function(errResponse) {
						toastr.error("Lỗi không xoá được!");
					});
			})
		}
		
		vm.canceldoSearch= function canceldoSearch(){
			 vm.showDetail = false;
			vm.oddCableManagementSearch={
					status:"1",
			};
			doSearch();
		}

//		Tìm kiếm dữ liệu
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.oddCableManagementGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}	

		//		Hiện autoComplete hàng hoá
        vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
        '<p class="col-md-6 text-header-auto border-right-ccc">Mã Hàng Hóa</p>' +
        '<p class="col-md-6 text-header-auto">Tên Hàng Hóa</p>' +
        	'</div>';
		vm.goodsOptions = {
            dataTextField: "name",
            select: function(e) {
              var dataItem = this.dataItem(e.item.index());
			  vm.oddCableManagementSearch.goodsId = dataItem.goodsId;
              vm.oddCableManagementSearch.goodsCode = dataItem.code; 
              vm.oddCableManagementSearch.goodsName = dataItem.name;
			  vm.oddCableManagementSearch.goodsType = dataItem.goodsType;
			  vm.oddCableManagementSearch.goodsUnitId = dataItem.goodsUnitId;
              vm.oddCableManagementSearch.goodsUnitName = dataItem.unitTypeName;
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("orderGoodsServiceRest/orderGoods/" + 'getForAutoComplete').post({name:vm.oddCableManagementSearch.name,pageSize:vm.goodsOptions.pageSize}).then(function(response){
                            options.success(response);
                            if(response.length==0){
								$("#goodsNameEdit").val("");
								$("#goodsName").val("");
							}
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            template:'<div class="row" ><div class="col-xs-6" style="float:left">#: data.code #</div><div  style="padding-left: 6px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
            change: function(e) {
                if (e.sender.value() == '') {
					vm.oddCableManagementSearch.goodsId = null;
                	vm.oddCableManagementSearch.goodsCode = null; 
                	vm.oddCableManagementSearch.goodsName = null;
					vm.oddCableManagementSearch.goodsType = null;
					vm.oddCableManagementSearch.goodsUnitId = null;
                	vm.oddCableManagementSearch.goodsUnitName = null; 
					vm.oddCableManagementSearch.name = null; 
                }else
				 if (vm.oddCableManagementSearch.name != vm.oddCableManagementSearch.goodsName) {
					vm.oddCableManagementSearch.goodsId = null;
                	vm.oddCableManagementSearch.goodsCode = null; 
                	vm.oddCableManagementSearch.goodsName = null;
					vm.oddCableManagementSearch.goodsType = null;
					vm.oddCableManagementSearch.goodsUnitId = null;
                	vm.oddCableManagementSearch.goodsUnitName = null; 
					vm.oddCableManagementSearch.name = null; 
                }
            },
            ignoreCase: false
        };
		
		//Enter
		$("#oddCableManagementId").on("keypress", function (e) {
            if (e.keyCode === 13) {
				$("#findOddCable").focus();
            	$("#findOddCable").click();
				}
            });
			//End
	}
})();