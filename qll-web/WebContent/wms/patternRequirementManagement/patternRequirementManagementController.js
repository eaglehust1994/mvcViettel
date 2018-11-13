(function() {
	'use strict';
	var controllerId = 'patternRequirementManagementController';
	
	angular.module('MetronicApp').controller(controllerId, patternRequirementManagementController);
	
		function patternRequirementManagementController($scope, $rootScope, $timeout, gettextCatalog, 
				kendoConfig, $kWindow,patternRequirementManagementService,
				CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
			var vm = this;
			vm.orderPattern={};
			vm.detailGoods={};
			vm.showSearch = true;
			vm.isCreateNew = false;
	//		vm.showDetail = false;
			vm.oldSearch={};
			vm.patternRequirementManagementSearch={
			
			};
			vm.patternRequirementManagementSearch.createdUserName = Constant.user.vpsUserToken.fullName;
			vm.stockSearch={};
			vm.orderPattern={};
			vm.templateGoods='<span class="k-state-default"><h3>#: data.code #</h3></span>' +
	        '<span class="k-state-default"><p>#: data.name #</p></span>',
	        vm.headerTemplate1='<div class="dropdown-header k-widget k-header">' +
	        '<span>Mã</span>' +
	        '<span>Tên</span>' +
	        	'</div>';
	        vm.commonSearch = {stt:'', name: '', code: '',goodsUnitName: ''};
	        vm.validatorOptions = kendoConfig.get('validatorOptions');
			
//		Khởi tạo dữ liệu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDatadetailGrid([]);
			fillDataTablePattern([]);
			fillDataEditGrid([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
//		1. Hiển thị thông tin tìm kiếm khởi tạo màn hình
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: false,	
				sortable: false,				
				columnMenu: false,
				scrollable: false,
				toolbar: [
                    {
                        name: "actions",
                        template:
                        	'<div class=" pull-left ">'+
                            '<button class="btn btn-qlk padding-search-right addQLK"'+
                            'ng-click="vm.addCreatNew()" translate>Tạo mới</button>'+
          					'</div>'
							+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.patternRequirementManagementGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
                    }
                    ],dataBound: function (e) {
						var grid = vm.patternRequirementManagementGrid;
						grid.tbody.find("tr").dblclick(function (e) {
							var dataItem = grid.dataItem(this);
							vm.showNoteDetail(dataItem)
						});
					},
               
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
								$("#patternCount").text(""+response.total);
							 	vm.count = response.total;
								return response.total; 
							},
							data: function (response) {
								for(var i in response.data)
								{
									response.data[i].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId
								}
									return response.data;
								},
							},
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderPatternServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.patternRequirementManagementSearch.page = options.page
								vm.patternRequirementManagementSearch.pageSize = options.pageSize
								vm.oldSearch=angular.copy(vm.patternRequirementManagementSearch);
								return JSON.stringify(vm.patternRequirementManagementSearch)
						}
					},					 
					pageSize: 10
				},
				
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
				},
					
				// dataSource: data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				dataBound: function () {
					/*var GridDestination = $("#patternRequirementManagementGrid").data("kendoGrid");                
					 if (GridDestination.dataSource.total() < 10 ){
			                GridDestination.pager.element.hide();
			            }
			            else {
			                GridDestination.pager.element.show();
			            }*/
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
			        //template: dataItem => $("#patternRequirementManagementGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: '12%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Tên mẫu",
					field: 'name',
					 template: '<a class="#=name#" href="javascript:void(0);" ng-click=vm.showDetail(dataItem)>#=name#</a>',
			        width: '25%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Người tạo",
					field: 'createdUserName',
			        width: '25%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Ghi chú",
					field: 'description',
					width: '25%',
					headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Thao tác",
			        template:
			        	'<div class="text-center"">'+
						'<button style=" border: none; background-color: white;" class=" icon_table "'+
						' ng-click=vm.edit(dataItem)  uib-tooltip="Sửa" translate>'+
						'<i class="fa fa-pencil" ng-hide="dataItem.status==0 || dataItem.createdBy!=Constant.userInfo.vpsUserToken.sysUserId" aria-hidden="true"></i>'
						+'</button>'+
						'<button style=" border: none; background-color: white;" type="button"'+	
							'class="#=orderPatternId# icon_table"  uib-tooltip="Xóa" ng-click=vm.remove(dataItem) translate>'+
							'<i class="fa fa-trash" style="color:steelblue;" ng-hide="dataItem.status==0 || dataItem.createdBy!=Constant.userInfo.vpsUserToken.sysUserId" aria-hidden="true" ></i>'+
						'</button>'+'</div>',
			        width: '12%',
			        field:"stt",
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
				}
				,]
			});
		}
		
		var record2=0;
//		2. Hiển thị dữ liệu danh sách hàng hoá khi thêm mới
		function fillDataTablePattern(data) {
			var dataSource = new kendo.data.DataSource({
                pageSize: 10,
                data: data,
                autoSync: false,        
                schema: {
                    model: {
                        id: "patternGrid",
                    	fields: {
                    		stt: {editable: false},
                    		goodsCode: {editable: false},
                    		goodsName: {editable: false},
                    		goodsUnitName: {editable: false},
                    		amount:  { type: "number"},
                    		choose: {editable: false},
                    	}
                    }
                }
            });
			vm.gridPatternOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: false,		
				sortable: false,				
				columnMenu: false,
				scrollable: false,
				pageable: false,
				dataSource: dataSource,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				/* dataBound: function () {
					 var GridDestination = $("#patternGrid").data("kendoGrid");                
					 if (GridDestination.dataSource.total() < 10) {
			                GridDestination.pager.element.hide();
			            }
			            else {
			                GridDestination.pager.element.show();
			            } 
                },
				 */
				dataBinding: function() {
					record2 = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					title: "TT",
					field:"stt",
			        template: function () {
					  return ++record2;
					 }, 
					//template: dataItem => $("#patternGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: '8%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: '22%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: '28%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: '20%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},  {
					title: "Số lượng",
					field: 'amount',
			        width: '14%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
					editor:editNumber,
					format:"{0:n0}",
				}, {
					title: "Xóa",
					field:'choose',
			        template: dataItem =>
			        	'<div class="text-center #=id#"">'+'<a type="button"'+
						'class="#=id# icon_table" translate'+'>'+
						'<i ng-hide="vm.hideDeleteRow" ng-click=caller.deleteUserRoleData(dataItem) class="fa fa-trash" aria-hidden="true"></i>	'+
						'</a>'+'</div>',
			        width: '8%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,]
			});
		}
		//Editor amount
		
		
		//Editor Amount
		function editNumber(container, options) {
			 $('<input id= "amounts" data-bind="value:' + options.field + '"/>')
	            .appendTo(container)
	            .kendoAutoComplete({
	        });
			 $("#amounts").attr('maxlength','10');
			$("#amounts").keypress(function(event) {
		        var $this = $(this);
		        if ((event.which != 46 || $this.val().indexOf('.') != -1) &&
		           ((event.which < 48 || event.which > 57) &&
		           (event.which != 0 && event.which != 8))) {
		               event.preventDefault();
		        }

		        var text = $(this).val();
		        if ((event.which === 46) && (text.indexOf('.') === -1)) {
		            setTimeout(function() {
		                if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                    $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		                }
		            }, 1);
		        }
		        if(text.length>=10){
		        	event.preventDefault();
		        }
		        if ((text.indexOf('.') != -1) &&
		            (text.substring(text.indexOf('.')).length > 0) &&
		            (event.which != 0 && event.which != 8) &&
		            ($(this)[0].selectionStart >= text.length)) {
		                event.preventDefault();
		        }      
		    });

			$("#amounts").bind("paste", function(e) {
		    var text = e.originalEvent.clipboardData.getData('Text');
		    if ($.isNumeric(text)) {
		        if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		            e.preventDefault();
		            $(this).val(text.substring(0, text.indexOf('.') + 3));
		       }
		    }
		    else {
		            e.preventDefault();
		         }
		    });
		    
		}
		//End Editor Amount
		
		
		//End Editor amount
		
//		3. Hiển thị danh sách hàng hoá của mẫu trong chi tiết hàng hoá
		var record3=0;
		function fillDatadetailGrid(data) {
			vm.detailGrid = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: false,
				sortable: false,				
				columnMenu: false,
				scrollable: false,
				dataSource: {
					pageSize: 10,
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
							url: Constant.BASE_SERVICE_URL + "orderPatternServiceRest/viewDetail",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.detailGoods.page = options.page
								vm.detailGoods.pageSize = options.pageSize 
								return JSON.stringify(vm.detailGoods)
						}
					},			
				} ,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable:false,
				/* 
				dataBound: function () {
					/*var GridDestination = $("#detailGrid").data("kendoGrid");                
					 if (GridDestination.dataSource.total() < 10) {
			                GridDestination.pager.element.hide();
			            }
			            else {
			                GridDestination.pager.element.show();
			            }
                },
				*/
				dataBinding: function() {
					record3 = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					title: "TT",
					field:"stt",
					template: function () {
					  return ++record3;
					 }, 
					//template: dataItem => $("#detailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 40,
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: 100,
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: 200,
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: 80,
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},{
					title: "Số lượng",
			        field: 'amount',
			        width: 50,
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
				},{
					title: "ID",
			        field: 'orderPatternId',
			        hidden: true,
				}
				]
			});
		}
		
		// 3. Hiển thị danh sách hàng hoá của mẫu khi chỉnh sửa mẫu y/c
		var record4=0;
		function fillDataEditGrid(data) {
			vm.detailEditGrid = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: false,		
				sortable: false,				
				columnMenu: false,
				scrollable: false,
				dataSource: {
					pageSize: 10, 
					 schema: {
						model: {
							id: "detailEditGrid",
							fields: {
								stt: {editable: false},
								goodsCode: {editable: false},
								goodsName: {editable: false},
								goodsUnitName: {editable: false},
								amount:  { type: "number"},
								choose: {editable: false},
							}
						},		
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
							url: Constant.BASE_SERVICE_URL + "orderPatternServiceRest/viewDetail",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    /* vm.tax.page = options.page
								vm.tax.pageSize = options.pageSize */
								return JSON.stringify(vm.tax)
						}
					},			
				} ,
				dataBinding: function() {
					record4 = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					title: "TT",
					field:"stt",
			        template: function () {
					  return ++record4;
					 },
 				    //template: dataItem => $("#detailEditGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: '5%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: '25%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: '40%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				}, {
					title: "Đơn vị tính",
			        field: 'goodsUnitName',
			        width: '15%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:left;"},
				},{
					title: "Số lượng",
			        field: 'amount',
			        width: '10%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:right;"},
					editor:editNumber,
					format:"{0:n0}",
				},{
					title: "ID",
			        field: 'orderPatternId',
			        hidden: true,
				},{
					title: "Xóa",
					field:'choose',
			        template: dataItem =>
			        	'<div class="text-center #=id#"">'+'<a type="button"'+
						'class="#=id# icon_table" translate'+'>'+
						'<i ng-click=caller.deleteData(dataItem) class="fa fa-trash" aria-hidden="true"></i>'+
						'</a>'+'</div>',
			        width: '5%',
			        headerAttributes: {style: "text-align:center;font-weight: bold;"},
					attributes: {style: "text-align:center;"},
				}]
			});
		}
		
//		Hiển thị Popup Chi tiết hàng hoá
		vm.showDetail=showDetail;
		function showDetail(dataItem){			
			vm.detailGoods=dataItem;
			var teamplateUrl="wms/patternRequirementManagement/viewDetail.html";
		    var title="Chi tiết hàng hóa";
		    var windowId="detailGrid";
		    CommonService.populatePopupCreate(teamplateUrl,title,vm.detailGoods,vm,windowId,false,'85%','85%');
		    fillDatadetailGrid([]);
			fillDataTable([]);
		}	

		var flag = false;
//		Hiển thị Popup thêm mới yêu cầu xuất kho
		vm.addCreatNew = function add(){
			 patternGoodsListDTO=[];
			 vm.pattern.patternGoodsListDTO = [];
			 var teamplateUrl="wms/patternRequirementManagement/creatNewSample.html";
			 var title="Tạo mới mẫu yêu cầu xuất kho";
			 var windowId="patternRequirementManagement";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'75%','50%',"sampleNameNew");
			fillDataTablePattern([]);
			fillDataTable([]);
			setTimeout(function(){ 
				 $("#patternGrid").data('kendoGrid').dataSource.data(patternGoodsListDTO);
				 document.getElementById("orderGoods2").value = "";
			 }, 500);
			 vm.mess = '';
			 
		 }
		
//		Hiển thị Popup chỉnh sửa mẫu yêu cầu xuất kho
		vm.edit=edit;
		function edit(dataItem){
			vm.tax =dataItem;
			var teamplateUrl="wms/patternRequirementManagement/edit_popup.html";
			var title="Chỉnh sửa mẫu yêu cầu xuất kho";
			var windowId="patternRequirementManagementEditPopup"
			CommonService.populatePopupCreate(teamplateUrl,title,vm.tax,vm,windowId,false,'75%','50%',"sampleName");
			fillDataEditGrid([]);
			fillDataTable([]);
			vm.mess = '';
			setTimeout(function(){ 
				 document.getElementById("orderGoods").value = "";
			 }, 500);
			$('#patternRequirementManagementGrid').data('kendoGrid').dataSource.read();
			$('#patternRequirementManagementGrid').data('kendoGrid').refresh();
		}
		
//		Xuất file Excel
		vm.exportExcelGrid = function(){
			var ds = $("#patternRequirementManagementGrid").data("kendoGrid").dataSource;
			var ds1 = ds.data();
			 if (ds1.length == 0){
				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
			 }else {
					vm.oldSearch.page = null;
					vm.oldSearch.pageSize = null;
					patternRequirementManagementService.doSearch(vm.oldSearch).then(function(d) {
						CommonService.exportFile(vm.patternRequirementManagementGrid,d.data,vm.listRemove,vm.listConvert,"Quản lý mẫu yêu cầu");
					});
			}	 
		}
		
		vm.listRemove=[{
			title: "Thao tác",
		}];
		vm.listConvert=[];
		
//		Tìm kiếm dữ liệu
		vm.doSearch= doSearch;
		function doSearch(){
			trimSpace();
			var grid =vm.patternRequirementManagementGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		
//		Hiện/Ẩn các cột
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.patternRequirementManagementGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.patternRequirementManagementGrid.showColumn(column);
            } else {
            	vm.patternRequirementManagementGrid.hideColumn(column);
            }
        	
        	
        }
		
		function refreshGrid(d) {
			var grid = vm.patternRequirementManagementGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
//		Validate special character
		vm.mess = '';
		var specialChars = "<>@!#$%^&*()_+[]{}?:;|'\"\\,./~`-=";
			var check = function(string){
			    for(var i = 0; i < specialChars.length;i++){
			        if(string.indexOf(specialChars[i]) > -1){
			        	vm.mess = 'Tên mẫu chứa ký tự đặc biệt';
			            return true;
			        }
			    }
			    vm.mess = '';
			    return false;
			}
		
		vm.checkErr = checkErr;
    	function checkErr(goodsName) {
    		var goodsName = $('#sampleName').val();
    		if(check(goodsName) == true){
    			vm.mess = 'Tên mẫu chứa ký tự đặc biệt';
    			return vm.mess;
    		}else{
    			vm.mess = '';
    			return vm.mess;
    		}
    	}		
		
//		Thêm mới yêu cầu xuất kho
		vm.pattern={};
		var patternGoodsListDTO=[];
		vm.pattern.patternGoodsListDTO=[];
		vm.save= function save(data, isCreateNew){		
			if(isCreateNew) {
				var dataGoodsGrid = $('#patternGrid').data("kendoGrid").dataSource.data();
				if((dataGoodsGrid.length == 0)){
					toastr.warning("Mẫu yêu cầu chưa import hàng hóa");
	    			return;
				}
				trimSpace();
	    		vm.pattern.status = '1';
	    		vm.pattern.name = data.name;
	    		vm.pattern.description = data.description;
	    		vm.pattern.createdUserName = vm.patternRequirementManagementSearch.createdUserName;
				vm.pattern.createdDate = vm.patternRequirementManagementSearch.createdDate;
				
	    		for(var i = 0; i < dataGoodsGrid.length;i++){
	    			/* if(dataGoodsGrid[i].amount==0){
	    				toastr.warning("Số lượng phải lớn hơn 0");
						var grid = $("#patternGrid").data("kendoGrid");
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4)); 
	    				return;
	    			} */
	    			if(dataGoodsGrid[i].amount==null){
	    				toastr.warning("Bạn chưa nhập số lượng");
						var grid = $("#patternGrid").data("kendoGrid");
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4)); 
	    				return;
	    			}
	    			else {
	    				vm.pattern.patternGoodsListDTO.push(dataGoodsGrid[i]);
	    			}
				}
	    		
	    		patternRequirementManagementService.addCreatNew(vm.pattern).then(function(result){
					if(result.error){
						$('#sampleName').focus();
						toastr.error(result.error);
						vm.pattern.patternGoodsListDTO=[];
						return;
					}
	    			toastr.success("Thêm mới thành công!");
	                doSearch();
	                CommonService.closePopup1();
	    		}, function(errResponse){
	                if (errResponse.status === 409) {
	                	//toastr.error(gettextCatalog.getString("Mẫu yêu cầu đã tồn tại!"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới yêu cầu xuất kho!"));
	                }
		        });
	    	} else {
				data.patternGoodsListDTO=[];
				data.status='1';
				var dataGoodsGrid = $('#detailEditGrid').data("kendoGrid").dataSource.data();
				if(dataGoodsGrid.length == 0){
					toastr.warning("Mẫu yêu cầu chưa import hàng hóa");
	    			return;
				}
				for(var i = 0; i < dataGoodsGrid.length;i++){
	    			/* if(dataGoodsGrid[i].amount==0){
	    				toastr.warning("Số lượng phải lớn hơn 0");
						var grid = $("#detailEditGrid").data("kendoGrid");
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4)); 
	    				return;
	    			}*/ 
	    			if(dataGoodsGrid[i].amount==null){
	    				toastr.warning("Bạn chưa nhập số lượng");
						var grid = $("#detailEditGrid").data("kendoGrid");
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4)); 
	    				return;
	    			}
	    			else {
	    				data.patternGoodsListDTO.push(dataGoodsGrid[i]);
	    			}
				}
				
				for (var i=0; i<data.length; i++){
					data[i].goodsCode = dataGoodsGrid[i].goodsCode;
					data[i].goodsId = dataGoodsGrid[i].goodsId; 
					data[i].goodsName = dataGoodsGrid[i].name;
					data[i].goodsUnitName = dataGoodsGrid[i].goodsUnitName;
					data[i].goodsType = dataGoodsGrid[i].goodsType;
					data[i].goodsIsSerial = dataGoodsGrid[i].isSerial;
					data[i].goodsUnitId = dataGoodsGrid[i].goodsUnitId;
					data[i].amount = dataGoodsGrid[i].amount;
					data[i].goodsState = 1;
					data[i].goodsStateName = "Bình thường";
				}
	    		patternRequirementManagementService.update(data).then(function(result){
					if(result.error){
						$('#sampleName').focus();
						toastr.error(result.error);
						return;
					}
	    			toastr.success("Cập nhật thành công!");
					$('#patternRequirementManagementGrid').data('kendoGrid').dataSource.read();
					$('#patternRequirementManagementGrid').data('kendoGrid').refresh();
	    			CommonService.closePopup1();
	    		}, function(errResponse){
	    			if (errResponse.status === 409) {
	                	//toastr.error(gettextCatalog.getString("Mẫu yêu cầu đã tồn tại"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	                }
		        });
	    	}
		}
		
		// Hàm check xem mặt hàng đã tồn tại trong lưới chưa
        function checkDups(goodsItem){
			var isExisted = false;
			var goodsGrid = vm.patternGrid;
			var ds = $("#patternGrid").data("kendoGrid").dataSource.data();
				if(ds.length!=0){
					goodsGrid.table.find("tr").each(function(idx, item) {
    					var row = $(item);
    					var dataItem = goodsGrid.dataItem(item);
    					if(goodsItem.goodsCode == dataItem.goodsCode && goodsItem.goodsName == dataItem.goodsName){
    						isExisted = true;
    					}
					});
				}
            return isExisted;
		}
        //End
		
		// Hàm check xem mặt hàng đã tồn tại trong lưới chưa
        function checkDups2(goodsItem){
			var isExisted = false;
			var goodsGrid = vm.detailEditGrid;
            goodsGrid.table.find("tr").each(function(idx, item) {
    					var row = $(item);
    					var dataItem = goodsGrid.dataItem(item);
    					if(goodsItem.goodsCode == dataItem.goodsCode && goodsItem.goodsName == dataItem.goodsName){
    						isExisted = true;
    					}
    			});
            return isExisted;
		}
        //End
		
//		Hiện autoComplete hàng hoá
		vm.goods={};
		vm.patternOptions1={
			dataTextField: "name",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.goods.goodsId = dataItem.goodsId; // thành id
                vm.goods.goodsCode = dataItem.code;
                vm.goods.goodsName = dataItem.name;
				vm.goods.goodsUnitName = dataItem.goodsUnitName;
				vm.goods.goodsType = dataItem.goodsType;
				vm.goods.goodsIsSerial = dataItem.isSerial;
				vm.goods.goodsUnitId = dataItem.goodsUnitId;
				vm.goods.goodsState = 1;
				vm.goods.goodsStateName = "Bình thường";
				 var grid = $("#patternGrid").data("kendoGrid");
				 var dt = $("#patternGrid").data("kendoGrid").dataSource.data();
				 var check = checkDups( vm.goods);
				
				if(check){
					toastr.warning("Mặt hàng đã tồn tại trong lưới!");
				}else { 
					 //grid.dataSource.add(vm.goods);
					 dt.splice(0, 0, vm.goods);
				 }                 
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("goodsRsServiceRest/" + 'getGoodsForOrder').post({pageSize:10, page:1, keySearch:$("#orderGoods2").val()}).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            placeholder:"Nhập mã hàng, tên hàng và ấn enter để bổ sung",
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã hàng</p>' +
            '<p class="col-md-6 text-header-auto">Tên hàng</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                	vm.goods.goodsId = null; 
	                vm.goods.code = null;
	                vm.goods.name = null;
					vm.goods.goodsUnitName = null;
					vm.goods.goodsType = null;
					vm.goods.goodsIsSerial = null;
					vm.goods.goodsUnitId = null;
					vm.goods.goodsState = null;
					vm.goods.goodsStateName = null;
                }
            },
            close: function(e) {
                // handle the event
            	document.getElementById("orderGoods2").value = "";
              }
		};
		
		//		Hiện autoComplete hàng hoá
		vm.goodsEdit={};
		vm.patternOptions={
			dataTextField: "name",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.goodsEdit.goodsId = dataItem.goodsId; // thành id
                vm.goodsEdit.goodsCode = dataItem.code;
                vm.goodsEdit.goodsName = dataItem.name;
				vm.goodsEdit.goodsUnitName = dataItem.goodsUnitName;
				vm.goodsEdit.goodsType = dataItem.goodsType;
				vm.goodsEdit.goodsIsSerial = dataItem.isSerial;
				vm.goodsEdit.goodsUnitId = dataItem.goodsUnitId;
				vm.goodsEdit.goodsState = 1;
				vm.goodsEdit.goodsStateName = "Bình thường";
				var grid = $("#detailEditGrid").data("kendoGrid");
				 var dt = $("#detailEditGrid").data("kendoGrid").dataSource.data();
				var check = checkDups2(vm.goodsEdit);
				
				if(check){
					toastr.warning("Mặt hàng đã tồn tại trong lưới!");
				}else{ 
					 //grid.dataSource.add(vm.goodsEdit);
					 dt.splice(0, 0, vm.goodsEdit);
				}                 
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("goodsRsServiceRest/" + 'getGoodsForOrder').post({pageSize:10, page:1, keySearch:$("#orderGoods").val()}).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            placeholder:"Nhập mã hàng, tên hàng và ấn enter để bổ sung",
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã hàng</p>' +
            '<p class="col-md-6 text-header-auto">Tên hàng</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
					vm.goodsEdit.goodsId = null; 
	                vm.goodsEdit.code = null;
	                vm.goodsEdit.name = null;
					vm.goodsEdit.goodsUnitName = null;
					vm.goodsEdit.goodsType = null;
					vm.goodsEdit.goodsIsSerial = null;
					vm.goodsEdit.goodsUnitId = null;
					vm.goodsEdit.goodsState = null;
					vm.goodsEdit.goodsStateName = null;
                }
            },
            close: function(e) {
                // handle the event
            	document.getElementById("orderGoods").value = "";
              }
		};
		
		//deleteItem khi thêm mới
		vm.deleteUserRoleData=function(dataItem){
				$('#patternGrid').data('kendoGrid').dataSource.remove(dataItem);
				var totalP = $("#patternGrid").data("kendoGrid").dataSource.total();
				var pageSizePattern = $("#patternGrid").data("kendoGrid").dataSource.pageSize();
				var currentPage = $("#patternGrid").data("kendoGrid").dataSource.page();
				if(((totalP+1) % pageSizePattern == 1)&&(currentPage > 1)){
					$("#patternGrid").data("kendoGrid").dataSource.page(currentPage - 1);
					currentPage = currentPage - 1;
				}
		}
		
		//deleteItem khi chỉnh sửa
		vm.deleteData=function(dataItem){
				$('#detailEditGrid').data('kendoGrid').dataSource.remove(dataItem);
				var totalP1 = $("#detailEditGrid").data("kendoGrid").dataSource.total();
				var pageSizePattern1 = $("#detailEditGrid").data("kendoGrid").dataSource.pageSize();
				var currentPage1 = $("#detailEditGrid").data("kendoGrid").dataSource.page();
				if(((totalP1+1) % pageSizePattern1 == 1)&&(currentPage1 > 1)){
					$("#detailEditGrid").data("kendoGrid").dataSource.page(currentPage1 - 1);
					currentPage1 = currentPage1 - 1;
				}
		}
		
//		Xoá yêu cầu xuất kho
		vm.remove=remove;
		function remove(dataItem){
			confirm('Xác nhận xóa?',function(){
				patternRequirementManagementService.remove(dataItem).then(function(result) {
					if(result.error){
						toastr.error(result.error);
						return;
					}
					
					toastr.success("Xóa thành công!");
					var totalP = $("#patternRequirementManagementGrid").data("kendoGrid").dataSource.total();
					var pageSizePattern = $("#patternRequirementManagementGrid").data("kendoGrid").dataSource.pageSize();
					if(totalP % pageSizePattern == 1){
						var currentPage = $("#patternRequirementManagementGrid").data("kendoGrid").dataSource.page();
						if (currentPage > 1) {
							$("#patternRequirementManagementGrid").data("kendoGrid").dataSource.page(currentPage - 1);
						}
					}
					$('#patternRequirementManagementGrid').data('kendoGrid').dataSource.read();
					$('#patternRequirementManagementGrid').data('kendoGrid').refresh();
					
				}, function(errResponse){
	    			if (errResponse.status === 200) {
	                	toastr.error(gettextCatalog.getString("Người dùng hiện tại không có quyền xóa bản ghi này !"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Lỗi không xóa được"));
	                }
		        });
				$('#patternRequirementManagementGrid').data('kendoGrid').dataSource.read();
				$('#patternRequirementManagementGrid').data('kendoGrid').refresh();
			});	
		}
		
		vm.cancel= cancel ;
		function cancel(){
			//confirm('Bạn có muốn hủy thao tác?',function (){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			//});
		}
		
		vm.canceldoSearch= function canceldoSearch(){
			vm.patternRequirementManagementSearch={
			};
			doSearch();
		}
		
		vm.cancelPattern = cancelPattern;
		function cancelPattern(){
			CommonService.closePopup1();
		}
		
		/* vm.viewDetail= viewDetail;
		function viewDetail(){
			var grid =vm.detailGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		} */
		
		setTimeout(function(){
			  $("#patternName").focus();
			},15);
			
		$(document).on("keypress", function (e) {
		switch(e.keyCode) {
			case 27:
				$("#cancelEditId").click();
				break;
			case 13:
				if($(':focus').size()===0){
					$( "#saveEditId" ).click(function( event ) {
						event.stopPropagation();
						});
						break;
				}
				if(e.keyCode===13&&!$('#descriptionEdit:focus').length&&!$('#saveEditId:focus').length&&!$('#cancelEditId:focus').length){
					$("#saveEditId").click();
					break;
				}
		}
	} );
	}
})();