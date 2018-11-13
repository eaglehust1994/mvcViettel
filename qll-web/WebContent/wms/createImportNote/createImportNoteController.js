(function() {
	'use strict';
	var controllerId = 'creImpNoteController';
	
	angular.module('MetronicApp').controller(controllerId, creImpNoteController);
	
	function creImpNoteController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,creImpNoteService, impReqManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTabOne = true;
		vm.showAdvancedSearch = false;
		vm.disableCheckboxes = true;
		vm.folder = '',
		vm.orderSearch ={};
		vm.orderGoodsSearch = {};
		vm.orderGoodsDetailSearch ={};
		vm.orderGoodsSearchForNote = {};
		vm.appParams={};
		vm.appParams.parType = 'IMPORT_ORDER_TYPE';
		vm.appParams.page = 1;
		vm.appParams.pageSize = 10;
		vm.showFromProvider = false;
		vm.showBeforeWarranty = false;
		vm.showAfterWarranty = false;
		vm.showFromConstruction = false;
		vm.showFromUnit = false;
		vm.showFromBorrowedGoods = false;
		vm.showFromAlternativeStock = false;
		vm.showFromProject = false;
		
		vm.goodsDataInGrid =[];
		vm.goodsDetailInGrid = [];
		vm.goodsForImport = [];
		
		vm.dataList={
				hederList:[],
				data:[]
		};
		vm.validateColums=Constant.COLUMS_VALIDATE.goods;
		
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
			title: "Mã kho",
			field: "code",
			width: 120
		}, {
			title: "Tên kho",
			field: "name",
			width: 120
		}];
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
		vm.orderSearch.listStatus = ['1'];
		vm.orderSearch.listSignState = ['1'];
		vm.orderSearch.listStockId = [];
		
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Chưa tạo phiếu',
				2:'Đã tạo phiếu',
				3:'Đã nhập/xuất',
				4:'Đã hủy',
				5:'Đã từ chối',
			}
		},{
			field:"signState",
			data:{
				1:'Chưa trình ký',
				2:'Đã trình ký',
				3:'Đã ký',
				4:'Đã từ chối',
			}
		}];
		$(document).on("keydown", function (e) {
	        if (e.keyCode == 13) {
	        	$("#findCreImpNote").click();
	        }
	        });
		setTimeout(function(){
			  $("#creImpNoteOrderCode").focus();
			},15);
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		creImpNoteService.getForOrderCheckboxes(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
			fillDataTableCreImpNote([]);
			var obj={};
			obj.data={};
			obj.data[d.data[0].code]=d.data[0].name;
			obj.data[d.data[1].code]=d.data[1].name;
			obj.data[d.data[2].code]=d.data[2].name;
			obj.data[d.data[3].code]=d.data[3].name;
			obj.data[d.data[4].code]=d.data[4].name;
			obj.data[d.data[5].code]=d.data[5].name;
			obj.data[d.data[6].code]=d.data[6].name;
			obj.data[d.data[7].code]=d.data[7].name;
			obj.data[d.data[8].code]=d.data[8].name;
			obj.field="bussinessType";
			vm.listConvert.push(obj);
		});
		
		$("#creImpNoteCreDept").kendoAutoComplete({
			dataTextField: "text",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.orderSearch.createdDeptedName = dataItem.text;
                vm.orderSearch.createdDeptedId = dataItem.id;
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("departmentServiceRest/" + 'department/getall').post({pageSize:10, page:1, name:$("#creImpNoteCreDept").val().trim()}).then(function(response){
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
                if (e.sender.value() === '') {
                	
                }
            },
            close: function(e) {
                // handle the event
              }
		});
		
		$("#creImpNoteCreDept").focusout(function(){
			if($("#creImpNoteCreDept").data("kendoAutoComplete").dataSource._data.length == 0){
				$("#creImpNoteCreDept").val(null);
			}
		});
		
		initData();
		function initData(){
			vm.orderSearch.createdDateFrom=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy");
			vm.orderSearch.createdDateTo = null;
			vm.orderSearch.createdBy = Constant.user.vpsUserToken.sysUserId;
			vm.orderSearch.createdByName = Constant.user.vpsUserToken.fullName;
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
		vm.doSearch = doSearch;
		function doSearch(){
			
			if(vm.orderSearch.createdDateFrom == ""){
				vm.orderSearch.createdDateFrom = null;
			}
			if(vm.orderSearch.createdDateTo == ""){
				vm.orderSearch.createdDateTo = null;
			}
			vm.orderSearch.createdDeptedName = $("#createdDeptImpReqMa").val();
			if(vm.orderSearch.createdDeptedName == ""){
				vm.orderSearch.createdDeptedName = null;
			}
			trimSpace();
			if(!vm.validator.validate()){
				if(vm.validator._errors.creImpNoteFromDate){
					$("#creImpNoteFromDate").focus();
				}
				if(vm.validator._errors.creImpNoteToDate){
					$("#creImpNoteToDate").focus();
				}
				return;
			}
			vm.orderSearch.listStatus = $("#requirementStatusImportNote").data("kendoMultiSelect").value();
			vm.orderSearch.listBussinessType = $("#requirementTypeImportNote").data("kendoMultiSelect").value();
			vm.orderSearch.listSignState = $("#signStateCAImportNote").data("kendoMultiSelect").value();
			if(vm.orderSearch.listBussinessType && vm.orderSearch.listBussinessType.length>0){
				for(var i = 0;i<vm.orderSearch.listBussinessType.length;i++){
					if(vm.orderSearch.listBussinessType[i]=="0"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[0].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="1"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[1].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="2"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[2].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="3"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[3].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="4"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[4].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="5"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[5].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="6"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[6].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="7"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[7].code;
					}
					if(vm.orderSearch.listBussinessType[i]=="8"){
						vm.orderSearch.listBussinessType[i] = vm.businessTypes[8].code;
					}
				}
			}
			
			var grid = $("#creImpNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		vm.onChangeGoodList = function(){
			if ($("#goodsListGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.orderGoodsDetailSearch = dataItem;
    			fillDataTableGoodsDetail([]);
			}
			
		}
		vm.onChangeGoodListForNote = function(){
			if ($("#goodsListForNoteGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListForNoteGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListForNoteGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.orderGoodsDetailSearchForNote = dataItem;
    			fillDataTableGoodsDetailForNote([]);
    			/*$("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.data();
    			$("#goodsDetailForNoteGrid").data("kendoGrid").refresh();*/
    			
    			/*vm.goodsDataInGrid = $("#goodsListForNoteGrid").data().kendoGrid.dataSource.view();
        		vm.goodsDetailInGrid = $("#goodsDetailForNoteGrid").data().kendoGrid.dataSource.view();*/
        		
        		var goodsDetailList = [];
        		if(vm.goodsForImport.length == 0){
        			creImpNoteService.getGoodsDetailByOrderId(vm.orderForNote.orderId).then(function(d) {
    					vm.goodsForImport = d.plain();
    				});
        		}
        		for(var i = 0; i<vm.goodsForImport.length;i++){
        			if(vm.goodsForImport[i].orderGoodsId == dataItem.orderGoodsId){
        				goodsDetailList.push(vm.goodsForImport[i]);
        				$("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.data(goodsDetailList);
            			$("#goodsDetailForNoteGrid").data("kendoGrid").refresh();
        			}
        		}
        		
    			/*var list=[];
            	for(var i = 0; i<vm.goodsForImport.length;i++){
            		for(var j = 0; j<vm.goodsDataInGrid.length;j++){
            			if(vm.goodsDetailInGrid.length > 0){
    		            		if(vm.goodsForImport[i].goodsCode == vm.goodsDataInGrid[j].goodsCode){
    		            			if(vm.goodsForImport[i].goodsCode == dataItem.goodsCode){
    		            				list.push(vm.goodsForImport[i]);
    		            			}
    		            			vm.goodsDetailInGrid = list;
    		            			$("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.data(vm.goodsDetailInGrid);
    		            			$("#goodsDetailForNoteGrid").data("kendoGrid").refresh();
    		            		}
            			}
                	}
            	}*/
        		
			}
		}
		$scope.$on("Popup.open", function () {
			fillDataTableGoodsListForNote([]);
			var goodDetailForNoteGrid = $("#goodsListForNoteGrid").data("kendoGrid");
			//$("#goodsDetailForNoteGrid").data("kendoGrid").refresh();
			if(goodDetailForNoteGrid){
				creImpNoteService.getGoodsDetailByOrderId(vm.orderForNote.orderId).then(function(d) {
					vm.goodsForImport = d.plain();
					var goodsDetailList = [];
	        		for(var i = 0; i<vm.goodsForImport.length;i++){
	        			if(vm.goodsForImport[i].orderGoodsId == vm.orderGoodsDetailSearchForNote.orderGoodsId){
	        				goodsDetailList.push(vm.goodsForImport[i]);
	        				$("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.data(goodsDetailList);
	            			$("#goodsDetailForNoteGrid").data("kendoGrid").refresh();
	        			}
	        		}
				});
			}
			
        });
		/*$scope.$watch('vm.orderGoodsSearchForNote.goodsName', function() {
			var grid = $("#goodsListForNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
		$scope.$watch('vm.orderGoodsDetailSearchForNote', function() {
			var grid = $("#goodsDetailForNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });*/
		$scope.$watch('vm.orderGoodsSearch', function() {
			var grid = $("#goodsListGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
		$scope.$watch('vm.orderGoodsDetailSearch.goodsName', function() {
			var grid = $("#goodsDetailGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
		vm.listRemove=[{
			title: "Viết phiếu",
		}]
		vm.exportExcelGrid = function(){
			vm.orderSearch.page = null;
			vm.orderSearch.pageSize = null;
			creImpNoteService.getForExportGrid(vm.orderSearch).then(function(d) {
				CommonService.exportFile(vm.creImpNoteGrid,d.data,vm.listRemove,vm.listConvert,"VietPhieuNhapKho");
			});
		}
		var record=0;
		function fillDataTableCreImpNote(data) {
			vm.creImpNoteGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				excel: {
			        allPages: true,
			        fileName: "Viết phiếu nhập kho.xlsx",
			        filterable:true
			    },
				dataBound: function (e) {
				    var grid = $("#creImpNoteGrid").data("kendoGrid");
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
				        if(dataItem.status == 1){
				        	vm.createImportNote(dataItem.orderId);
				        }
				    });
				},
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				toolbar: [
		                    {
		                        name: "actions",
		                        template: '<div class=" ">'+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                    '<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.creImpNoteGrid.columns| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
				dataSource: {
					serverPaging: true,
					 schema: {
						 errors: function (response) {
								if(response.error){
									toastr.error(response.error);
								}
								return response.error;
							},
						 total: function (response) {
							 $("#creImpNoteCount").text(""+response.total);
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
							url: Constant.BASE_SERVICE_URL + "orderServiceRest/order/doSearchForCreateImportNote",
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
					pageSize: 10
				},
				noRecords: true,
				columnMenu: false,
				scrollable: false,
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
						  return ++record;
						 },
			        width: "5%",
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
					template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(#=orderId#)>#=code#</a>',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại yêu cầu",
			        field: 'bussinessType',
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					template: function($scope){
						if($scope.bussinessType == vm.businessTypes[0].code){
							return vm.businessTypes[0].name;
						}else if($scope.bussinessType == vm.businessTypes[1].code){
							return vm.businessTypes[1].name;
						}else if($scope.bussinessType == vm.businessTypes[2].code){
							return vm.businessTypes[2].name;
						}else if($scope.bussinessType == vm.businessTypes[3].code){
							return vm.businessTypes[3].name;
						}else if($scope.bussinessType == vm.businessTypes[4].code){
							return vm.businessTypes[4].name;
						}else if($scope.bussinessType == vm.businessTypes[5].code){
							return vm.businessTypes[5].name;
						}else if($scope.bussinessType == vm.businessTypes[6].code){
							return vm.businessTypes[6].name;
						}else if($scope.bussinessType == vm.businessTypes[7].code){
							return vm.businessTypes[7].name;
						}else if($scope.bussinessType == vm.businessTypes[8].code){
							return vm.businessTypes[8].name;
						}
					},
				}, {
					title: "Kho nhập",
			        field: 'stockName',
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Ngày tạo",
					 field: 'createdDate',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					}
				},  {
					title: "Ký CA",
					 field: 'signState',
					 template: function($scope){
							if($scope.signState == 1){
								return "Chưa trình ký";
							}else if($scope.signState == 2){
								return "Đã trình ký";
							}else if($scope.signState == 3){
								return "Đã ký";
							}else if($scope.signState == 4){
								return "Đã từ chối";
							}
						},
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Trạng thái",
					 field: 'status',
			        width:"10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					template: function($scope){
						if($scope.status == 1){
							return "Chưa tạo phiếu";
						}else if($scope.status == 2){
							return "Đã tạo phiếu";
						}else if($scope.status == 3){
							return "Đã nhập";
						}else if($scope.status == 4){
							return "Đã hủy";
						}else if($scope.status == 5){
							return "Đã từ chối";
						}
					},
				},{
					title: "Viết phiếu",
			        template: dataItem =>
			        	'<div class="text-center #=orderId#""><button style=" border: none; background-color: white;" '+
						' class=" icon_table #=orderId#"'+
						' ng-click=vm.createImportNote(dataItem.orderId) ng-hide="vm.showHideButtonCreateNote(dataItem)" uib-tooltip="Viết phiếu" translate>'+
						'<i  class="fa fa-file-text" aria-hidden="true" style="color:steelblue;"></i>'+
					'</button>'+
					'<button style=" border: none; background-color: white;"'+
					'class="#=orderId# icon_table"  uib-tooltip="Từ chối yêu cầu nhập kho" translate'+
						' ng-click=vm.openRejectPopup(dataItem) ng-hide="vm.showHideButtonReject(dataItem)">'+
					'<i  class="fa btn stopQLK" aria-hidden="true"></i>	'+
					'</button>'+'<a '+
					' class=" icon_table #=orderId#"'+
					'   uib-tooltip="Khóa" translate>'+
					'<i style="color:grey" ng-show="vm.showHideButtonCreateNote(dataItem)" class="fa fa-file-text" aria-hidden="true"></i>'+
				'</a>'+
				'<a type="button"'+
				'class="#=orderId# icon_table"  uib-tooltip="Khóa" translate'+
					'>'+
				'<i style="color:grey" ng-show="vm.showHideButtonReject(dataItem)" class="fa btn stopQLK" aria-hidden="true"></i>	'+
				'</a>'+'</div>',
			        width:"10%",
			        field:"stt"
				}
				,]
			});
		}
		
		function fillDataTableGoodsListForNote(data) {
			vm.goodsListForNoteGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataBound: function(e) {
                     var grid = $("#goodsListForNoteGrid").data("kendoGrid");
                         grid.select("tr:eq(0)");
                },
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.orderGoodsSearchForNote.page = options.page
								vm.orderGoodsSearchForNote.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsSearchForNote)
						}
					},					 
					pageSize: 10
				},
				noRecords: true,
				columnMenu: false,
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
			        template: dataItem => $("#goodsListForNoteGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'amount',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},  {
					title: "Tình trạng",
					 field: 'goodsStateName',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}]
			});
		}
		
		function fillDataTableGoodsDetailForNote(data) {
			vm.goodsDetailForNoteGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderGoodsDetailServiceRest/orderGoodsDetail/doSearchGoodsDetailForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.orderGoodsDetailSearchForNote.page = options.page
								vm.orderGoodsDetailSearchForNote.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsDetailSearchForNote)
						}
					},					 
					pageSize: 10
				},
				noRecords: true,
				columnMenu: false,
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
			        template: dataItem => $("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
				},  {
					title: "Vị trí",
					 field: 'cellCode',
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
		
		function fillDataTableGoodsList(data) {
			vm.goodsListGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataBound: function(e) {
                     var grid = $("#goodsListGrid").data("kendoGrid");
                         grid.select("tr:eq(0)");
                },
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForImportReq",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.orderGoodsSearch.page = options.page
								vm.orderGoodsSearch.pageSize = options.pageSize
								
								return JSON.stringify(vm.orderGoodsSearch)
						}
					},					 
					pageSize: 10
				},
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
				},
				columns: [
				{
					title: "TT",
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
						style: "text-align:right;"
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
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
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
					pageSize: 10
				},
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
				},
				columns: [
				{
					title: "TT",
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
		function fillDataImportErrTable(data) {
			vm.importGoodResultGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data,
				noRecords: true,
				columnMenu: false,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSize:10,
				pageable: {
					pageSize:10,
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
			        template: dataItem => $("#importGoodResultGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
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
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Cột lỗi",
			        field: 'columnError',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Nội dung lỗi",
			        field: 'detailError',
			        width: 150,
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
		function refreshGrid(d) {
			var grid = vm.imReqMaGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
		}
		vm.advancedSearch = function(){
			vm.showAdvancedSearch = !vm.showAdvancedSearch;
		}
		
		vm.showDetail=function(id){
			vm.showTabOne = true;
			var teamplateUrl="wms/createImportNote/detailPopup.html";
			 var title="Chi tiết yêu cầu nhập kho";
			 var windowId="ORDER";
			 vm.order={};
			 creImpNoteService.getOrderDetailById(id).then(function(d) {
				 vm.order = d.plain();
				 vm.checkBoxAutoLoad();
				 vm.orderGoodsSearch.orderId = vm.order.orderId;
				 fillDataTableGoodsList([]);
				 title="Chi tiết yêu cầu nhập kho: " + vm.order.code;
					CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.exportOrderWithSerial = function(){
			var obj={};
         	obj.orderId=vm.order.orderId;
         	obj.reportType="DOC";
         	obj.reportName="YeuCauNhapKho_CoSerial";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
			        kendo.saveAs({dataURI: binarydata, fileName: "YeuCauCoSerial" + '.docx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export DOC được!");
				});
		}
		
		vm.exportOrderWithoutSerial = function(){
			var obj={};
         	obj.orderId=vm.order.orderId;
         	obj.reportType="DOC";
         	obj.reportName="YeuCauNhapKho_KhongSerial";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
			        kendo.saveAs({dataURI: binarydata, fileName: "YeuCauKhongSerial" + '.docx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export DOC được!");
				});
		}
		
		$scope.$watch('vm.orderForNote.stockId', function() {
			var obj={};
			obj.value="PNK";
			obj.orgValue="AAA";
			obj.stockId="";
			obj.stockId= vm.orderForNote.stockId;
				CommonService.genCode(obj).then(
						function(d) {
							vm.orderForNote.code = d;
						});
	    });
		
		
		vm.createImportNote=function(id){
			var teamplateUrl="wms/createImportNote/createImportNotePopUp.html";
			 var title="Tạo phiếu nhập kho";
			 var windowId="CREATEIMPORTNOTE";
			 vm.orderForNote={};
				
			 creImpNoteService.getOrderDetailById(id).then(function(d) {
				 vm.orderForNote = d.plain();
				 vm.orderForNote.orderCode = d.plain().code; 
				 vm.orderForNote.description = vm.description;
				 vm.orderGoodsSearchForNote.orderId = vm.orderForNote.orderId;
				 
				 
					CommonService.populatePopupCreate(teamplateUrl,title,vm.orderForNote,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.openRejectPopup = openRejectPopup;
		function openRejectPopup(dataItem){
			var data =	dataItem;
			vm.orderReject = data;
			vm.reason ={};
			vm.reason.apply = "6";
			vm.reason.status = 1;
			data.cancelByName = Constant.user.vpsUserToken.fullName;
			impReqManaService.getReasonForCombobox(vm.reason).then(function(d) {
				vm.dataReasonDelete = d.plain();
				}, function() {
					console.error('Error');
				});
			var teamplateUrl = "wms/createImportNote/cancelOrderPopup.html";
			var title="Từ chối yêu cầu nhập kho";
			var windowId="APP_PARAM"
			data.cancelDate=kendo.toString(new Date(),"dd/MM/yyyy");
			CommonService.populatePopupCreate(teamplateUrl,title,data,vm,windowId,false,'50%','50%');
		}
		
		vm.closeErrImportPopUp = function(){
			CommonService.closePopup1();
		}
		vm.exportExcelErr = function(){
			creImpNoteService.downloadErrorExcel(vm.objectErr).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		}
		vm.closeDetail = function(){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
		vm.cancel = function(){
			//confirm('Xác nhận hủy bỏ thao tác này', function () {
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		//});
			
		}
		
		vm.reject = function(){
			if(!vm.validator.validate()){
				return;
			}
			vm.orderReject.cancelBy = Constant.user.vpsUserToken.sysUserId;
			vm.orderReject.cancelByName = Constant.user.vpsUserToken.fullName;
			creImpNoteService.rejectOrder(vm.orderReject).then(function(mess){
				if(mess.error){
					toastr.error(mess.error);
				}
        		toastr.success("Từ chối thành công!");
        		$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
        		var sizePage = $("#creImpNoteGrid").data("kendoGrid").dataSource.total();
				var pageSize = $("#creImpNoteGrid").data("kendoGrid").dataSource.pageSize();
				if(sizePage % pageSize == 1){
					var currentPage = $("#creImpNoteGrid").data("kendoGrid").dataSource.page();
			        if (currentPage > 1) {
			            $("#creImpNoteGrid").data("kendoGrid").dataSource.page(currentPage - 1);
			        }
				}
				$('#creImpNoteGrid').data('kendoGrid').dataSource.read();
				$('#creImpNoteGrid').data('kendoGrid').refresh();
            }, function(errResponse) {
            	if (errResponse.status == 406) {
                    toastr.error("Người dùng hiện tại không có quyền từ chối bản ghi này!");
                } else {
               	 toastr.error("Lỗi khi từ chối");
                }
			});
		}
		
		vm.checkboxDetailPopup = function(){
			vm.showFromProvider = false;
			vm.showBeforeWarranty = false;
			vm.showAfterWarranty = false;
			vm.showFromConstruction = false;
			vm.showFromUnit = false;
			vm.showFromBorrowedGoods = false;
			vm.showFromAlternativeStock = false;
			vm.showFromProject = false;
			if (document.getElementById("fromProvider").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromProvider = true;
		    	vm.order.bussinessType = vm.businessTypes[0].code;
		    }
			if (document.getElementById("beforeWarranty").checked == true) {
		        //Checkbox has been checked
		    	vm.showBeforeWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[1].code;
		    }
			if (document.getElementById("afterWarranty").checked == true) {
		        //Checkbox has been checked
		    	vm.showAfterWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[2].code;
		    }
			if (document.getElementById("fromConstruction").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromConstruction = true;
		    	vm.order.bussinessType = vm.businessTypes[3].code;
		    }
			if (document.getElementById("fromUnit").checked == true) {
			        //Checkbox has been checked
			    vm.showFromUnit = true;
			    vm.order.bussinessType = vm.businessTypes[4].code;
			}
			if (document.getElementById("fromBorrowedGoods").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromBorrowedGoods = true;
		    	vm.order.bussinessType = vm.businessTypes[5].code;
		    }
			if (document.getElementById("fromAlternativeStock").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromAlternativeStock = true;
		    	vm.order.bussinessType = vm.businessTypes[6].code;
		    }
			if (document.getElementById("fromProject").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromProject = true;
		    	vm.order.bussinessType = vm.businessTypes[7].code;
		    }
			if (document.getElementById("direct").checked == true) {
		        //Checkbox has been checked
		    	vm.order.bussinessType = vm.businessTypes[8].code;
		    }
		}
		
		vm.checkBoxAutoLoad = function(){
			vm.showFromProvider = false;
			vm.showBeforeWarranty = false;
			vm.showAfterWarranty = false;
			vm.showFromConstruction = false;
			vm.showFromUnit = false;
			vm.showFromBorrowedGoods = false;
			vm.showFromAlternativeStock = false;
			vm.showFromProject = false;
			if (vm.order.bussinessType == vm.businessTypes[0].code ) {
		        //Checkbox has been checked
		    	vm.showFromProvider = true;
		    	$("#fromProvider").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[1].code) {
		        //Checkbox has been checked
		    	vm.showBeforeWarranty = true;
		    	$("#beforeWarranty").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[2].code ) {
		        //Checkbox has been checked
		    	vm.showAfterWarranty = true;
		    	$("#afterWarranty").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[3].code ) {
		        //Checkbox has been checked
		    	vm.showFromConstruction = true;
		    	$("#fromConstruction").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[4].code ) {
			        //Checkbox has been checked
			    vm.showFromUnit = true;
			    $("#fromUnit").prop("checked", true);
			}
			if (vm.order.bussinessType == vm.businessTypes[5].code ) {
		        //Checkbox has been checked
		    	vm.showFromBorrowedGoods = true;
		    	$("#fromBorrowedGoods").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[6].code ) {
		        //Checkbox has been checked
		    	vm.showFromAlternativeStock = true;
		    	$("#fromAlternativeStock").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[7].code ) {
		        //Checkbox has been checked
		    	vm.showFromProject = true;
		    	$("#fromProject").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[8].code ) {
		        //Checkbox has been checked
				$("#direct").prop("checked", true);
		    }
		}
		
		$scope.$on("Popup.open", function () {
			vm.checkBoxAutoLoad();
			statusName();
			signStateName();
        });
		
		vm.create = function(){
			if(!vm.validator.validate()){
				return;
			}
			if(vm.orderForNote.signState != '3'){
				toastr.error("Yêu cầu chưa được ký duyệt !");
						return;
				}
			var orderGoodsDTO={};
			var listOrderGoodsDetailDTO = vm.goodsForImport;
			 orderGoodsDTO.orderId = vm.orderForNote.orderId;
			vm.orderForNote.orderGoodsDTO=orderGoodsDTO;
			vm.orderForNote.listOrderGoodsDetailDTO=listOrderGoodsDetailDTO;
			creImpNoteService.createImportNote(vm.orderForNote).then(function(result) {
			if(result.error){
				 toastr.error(result.error);
				 return;
			}
				toastr.success("Tạo phiếu thành công ");
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				var sizePage = $("#creImpNoteGrid").data("kendoGrid").dataSource.total();
				if(sizePage % 10 == 1){
					var currentPage = $("#creImpNoteGrid").data("kendoGrid").dataSource.page();
			        if (currentPage > 1) {
			            $("#creImpNoteGrid").data("kendoGrid").dataSource.page(currentPage - 1);
			        }
				}
				$('#creImpNoteGrid').data('kendoGrid').dataSource.read();
				$('#creImpNoteGrid').data('kendoGrid').refresh();
	     }, function(errResponse) {			    	 
	    		 toastr.error("Lỗi khi lưu");
	     });
		}
		vm.createAndRealImport= function(){
			if(!vm.validator.validate()){
				return;
			}
			if(vm.orderForNote.signState != '3'){
				toastr.error("Yêu cầu chưa được ký duyệt !");
						return;
				}
			var orderGoodsDTO={};
			var listOrderGoodsDetailDTO = vm.goodsForImport;
			 orderGoodsDTO.orderId = vm.orderForNote.orderId;
			vm.orderForNote.orderGoodsDTO=orderGoodsDTO;
			vm.orderForNote.listOrderGoodsDetailDTO=listOrderGoodsDetailDTO;
			creImpNoteService.createAndRealImportNote(vm.orderForNote).then(function(d) {
				if(d.error){
					toastr.error(d.error);
					return;
				}
				toastr.success("Tạo phiếu và thực nhập thành công");
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				var sizePage = $("#creImpNoteGrid").data("kendoGrid").dataSource.total();
				if(sizePage % 10 == 1){
					var currentPage = $("#creImpNoteGrid").data("kendoGrid").dataSource.page();
			        if (currentPage > 1) {
			            $("#creImpNoteGrid").data("kendoGrid").dataSource.page(currentPage - 1);
			        }
				}
				$('#creImpNoteGrid').data('kendoGrid').dataSource.read();
				$('#creImpNoteGrid').data('kendoGrid').refresh();
	     }, function(errResponse) {			    	 
	    	 if (errResponse.status == 406) {
                 toastr.error("Serial đã tồn trong kho");
             } else {
            	 toastr.error("Lỗi khi lưu");
             }
	     });
		}
		
		vm.goTo = function(menuKey) {
			
			var hasPerm = true;

			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);

				postal.publish({
					channel : "Tab",
					topic   : "open",
					data    : template
				});
			} else {
				// toastr.error(gettextCatalog.getString("Tài khoản đăng nhập
				// hiện tại không được phép truy cập vào chức năng này!"));
			}
			
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.creImpNoteGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.creImpNoteGrid.showColumn(column);
            } else {
            	vm.creImpNoteGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.showHideButtonCreateNote = function(dataItem){
			if(dataItem.status != 1){
				return true;
			}
			return false;
		}
		
		vm.showHideButtonReject = function(dataItem){
			if(dataItem.status != 1){
				return true;
			}
			return false;
		}
		
		vm.getExcelTemplate = function(){
			creImpNoteService.downloadTemplate().then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		}
		
		vm.onSave=onSave;
		function onSave(data){
			$("#creImpNoteCreDept").val(data.text);
			vm.orderSearch.createdDeptedName = data.text;
		}
		
		vm.openDepartment=function(){
			var obj={};
//			obj.page=1;
//			obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'wms/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Tìm kiếm đơn vị");
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
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		
		function statusName(){
			if(vm.order.status==1){
				vm.order.statusName = "Chưa tạo phiếu";
			}else if(vm.order.status==2){
				vm.order.statusName = "Đã tạo phiếu";
			}else if(vm.order.status==3){
				vm.order.statusName = "Đã nhập/xuất";
			}else if(vm.order.status==4){
				vm.order.statusName = "Đã hủy";
			}else if(vm.order.status==5){
				vm.order.statusName = "Đã từ chối";
			}
		}
		
		function signStateName(){
			if(vm.order.signState==1){
				vm.order.signStateName = "Chưa trình ký";
			}else if(vm.order.signState==2){
				vm.order.signStateName = "Đã trình ký";
			}else if(vm.order.signState==3){
				vm.order.signStateName = "Đã ký";
			}else if(vm.order.signState==4){
				vm.order.signStateName = "Đã từ chối";
			}
		}
		
		//=================================Clear Textbox================================//
		vm.clearSearchDate = function(){
			vm.orderSearch.createdDateFrom = null;
			vm.orderSearch.createdDateTo = null;
		}
		vm.clearSearchStatus = function(){
			vm.orderSearch.listStatus = [];
		}
		vm.clearSearchCreatedBy = function(){
			vm.orderSearch.createdByName = null;
		}
		vm.clearSearchSignState = function(){
			vm.orderSearch.listSignState = [];
		}
		
		vm.clearSearchBusinessType = function(){
			$('#requirementTypeImportNote').data("kendoMultiSelect").value([]);
		}
		
		vm.clearSearchCreatedDeptName = function(){
			$("#creImpNoteCreDept").val("");
			vm.orderSearch.createdDeptedName = null;
		}
		//=================================End Clear Textbox===============================//
		 vm.submit=submit;
	        function submit(){
	        	if($("#fileCreImpNote")[0].files[0] == null){
	        		toastr.warning("Bạn chưa chọn file để import");
	        		return;
	        	}
	        	if($("#fileCreImpNote")[0].files[0].name.split('.').pop() !='xls' && $("#fileCreImpNote")[0].files[0].name.split('.').pop() !='xlsx' ){
	        		toastr.warning("Sai định dạng file");
	        		return;
	        	}
		        var formData = new FormData();
				formData.append('multipartFile', $('#fileCreImpNote')[0].files[0]);
		     return   $.ajax({
		            url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/importCells/"+ vm.orderForNote.orderId +"?folder="+ vm.folder ,
		            type: "POST",
		            data: formData,
		            enctype: 'multipart/form-data',
		            processData: false,
		            contentType: false,
		            cache: false,
		            success:function(data) {
		            	if(data.error){
		            		toastr.error(data.error);
		            		return;
		            	}
		            	if(data.length == 1 && data[data.length - 1].lstErrorOrderGoods.length>0){
		            		vm.lstErrImport = data[data.length - 1].lstErrorOrderGoods;
		            		vm.objectErr = data[data.length - 1];
		            		var teamplateUrl="wms/createImportRequirementManagement/importResultPopUp.html";
		       			 	var title="Kết quả Import";
		       			 	var windowId="ERR_IMPORT";
		       			
		       			 	CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'80%','80%');
		       			 	fillDataImportErrTable(vm.lstErrImport);
		            	}else if(data.length == 1 && data[data.length - 1].lstErrorOrderGoods.length == 0){
		            		toastr.error("Không có dữ liệu phù hợp để import");
		            		return;
		            	}else if(data.length > 1 && data[data.length - 1].lstErrorOrderGoods.length != 0){
		            		vm.lstErrImport = data[data.length - 1].lstErrorOrderGoods;
		            		vm.objectErr = data[data.length - 1];
		            		var teamplateUrl="wms/createImportRequirementManagement/importResultPopUp.html";
		       			 	var title="Kết quả Import";
		       			 	var windowId="ERR_IMPORT";
		       			
		       			 	CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'80%','80%');
		       			 	fillDataImportErrTable(vm.lstErrImport);
		            	}else{
		            		toastr.success("Import thành công!");
		            		vm.dataFinal = [];
			            	data.splice(data.length - 1, 1);
			            	console.log(data);
			            	vm.goodsDataInGrid = $("#goodsListForNoteGrid").data().kendoGrid.dataSource.view();
			        		vm.goodsDetailInGrid = $("#goodsDetailForNoteGrid").data().kendoGrid.dataSource.view();
			            	vm.goodsForImport = data;
			            	var tr = $("#goodsListForNoteGrid").data("kendoGrid").select().closest("tr");
			    			var dataItem = $("#goodsListForNoteGrid").data("kendoGrid").dataItem(tr);
			    			
			    			var goodsDetailList = [];
			        		for(var i = 0; i<vm.goodsForImport.length;i++){
			        			if(vm.goodsForImport[i].orderGoodsId == dataItem.orderGoodsId){
			        				goodsDetailList.push(vm.goodsForImport[i]);
			        				$("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.data(goodsDetailList);
			            			$("#goodsDetailForNoteGrid").data("kendoGrid").refresh();
			        			}
			        		}
			            	/*var list=[];
			            	for(var i = 0; i<vm.goodsForImport.length;i++){
			            		if(vm.goodsForImport[i].cellCode == null){
			            			continue;
			            		}
			            		for(var j = 0; j<vm.goodsDataInGrid.length;j++){
			            			if(vm.goodsDetailInGrid.length > 0){
						            		if(vm.goodsForImport[i].goodsCode == vm.goodsDataInGrid[j].goodsCode){
						            			if(vm.goodsForImport[i].goodsCode == dataItem.goodsCode){
						            				list.push(vm.goodsForImport[i]);
						            			}
						            			vm.goodsDetailInGrid = list;
						            			$("#goodsDetailForNoteGrid").data("kendoGrid").dataSource.data(vm.goodsDetailInGrid);
						            			$("#goodsDetailForNoteGrid").data("kendoGrid").refresh();
						            		}
						            	
			            			}else{
			            				toastr.error("Không có dữ liệu phù hợp để import");
			            			}
			            			
				            	}
			            	}*/
			    			
		            	}
		            	
		            }
		        });
		      
	        
            }
	        
	      //validate ngay thang
			vm.checkErr = checkErr;
			function checkErr() {
				if(!vm.validator.validate()){
					$("#creImpNoteToDate").focus();
					return;
				}
				var startDate = $('#creImpNoteFromDate').val();
				var endDate = $('#creImpNoteToDate').val();
				vm.errMessage = '';
				var d = new Date();
				var curDate = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
		        
		        if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
		          vm.errMessage = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
		          $("#creImpNoteFromDate").focus();
		          return vm.errMessage;
		        }
		        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
		          vm.errMessage = '';
		          return vm.errMessage;
		        }
		    }
			
			//validate ngay thang
			vm.checkErr1 = checkErr1;
			function checkErr1() {
				if(!vm.validator.validate()){
					$("#creImpNoteFromDate").focus();
					return;
				}
				var startDate = $('#creImpNoteFromDate').val();  
				var endDate = $('#creImpNoteToDate').val();
				vm.errMessage1 = '';
				var curDate = new Date();
		        
		        if(kendo.parseDate(startDate,"dd/MM/yyyy") > curDate){
			           vm.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày hiện tại';
			           $("#creImpNoteFromDate").focus();
			           return vm.errMessage1;
			        }
			        else if(startDate <= curDate){
				           vm.errMessage1 = '';
				           return vm.errMessage1;
				        }
		        
		    }
			
			//Close popup
			// $(document).on("click",".k-overlay",function(){
				 // $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			// });
		//
			
	}
})();
