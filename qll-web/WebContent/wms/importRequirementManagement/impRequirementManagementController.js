(function() {
	'use strict';
	var controllerId = 'impReqManaController';
	
	angular.module('MetronicApp').controller(controllerId, impReqManaController);
	
	function impReqManaController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,impReqManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTabOne = true;
		vm.showAdvancedSearch = false;
		vm.disableCheckboxes = true;
		
		var listBusinessTypes = [];
		vm.order={};
		vm.orderSearch ={};
		vm.orderGoodsSearch ={};
		vm.orderGoodsDetailSearch ={};
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
		
		vm.reload = false;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã kho</p>' +
      '<p class="col-md-6 text-header-auto">Tên kho</p>' +
      	'</div>';
		vm.userTemplate='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.email #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.phoneNumber #</div> </div>',
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
		vm.gridCreator = [{
				title: "TT",
				field:"stt",
		        template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
		        width: 40,
		        headerAttributes: {
					style: "text-align:center;"
				}, 
				attributes: {
					style: "text-align:center;"
				}
		},  {
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
		
		$(document).on("keydown", function (e) {
	        if (e.keyCode == 13) {
	        	$("#findImpReq").click();
	        }
	        });
		
		setTimeout(function(){
			  $("#importOrderCode").focus();
			},15);
		
		vm.orderSearch.listStatus = ['1'];
		vm.orderSearch.listStockId = [];
		$scope.$on("backToImport", function(event, result){ 
		      if(result){
		    	  vm.orderSearch.page = result.page;
					vm.orderSearch.pageSize = result.pageSize;
					var grid = $("#imReqMaGrid").data("kendoGrid");	
					if(grid){
						if(result.page && result.pageSize){
							grid.dataSource.query({
								page: result.page,
								pageSize: result.pageSize
							});
						}else{
							grid.dataSource.query({
								page: 1,
								pageSize: 10
							});
						}
						
					}
		      }
		     });
		if(impReqManaService.getData()){
			vm.result = impReqManaService.getData();
			var grid = $("#imReqMaGrid").data("kendoGrid");	
			if(grid){
				if(result.page && result.pageSize){
					grid.dataSource.query({
						page: result.page,
						pageSize: result.pageSize
					});
				}else{
					grid.dataSource.query({
						page: 1,
						pageSize: 10
					});
				}
			}
		}
		initData();
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
		function initData(){
			vm.orderSearch.createdDateFrom=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy");
			vm.orderSearch.createdDateTo = null;
			vm.orderSearch.createdBy = Constant.user.vpsUserToken.sysUserId;
			vm.orderSearch.createdByName = Constant.user.vpsUserToken.fullName;
		}
		if($rootScope.stringtile){
			vm.String=$rootScope.stringtile;
			}
		impReqManaService.getForOrderCheckboxes(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
			
			fillDataTableImReqMa([]);
			
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
		
		$("#createdDeptImpReqMa").kendoAutoComplete({
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
                        return Restangular.all("departmentServiceRest/" + 'department/getall').post({pageSize:10, page:1, name:$("#createdDeptImpReqMa").val().trim()}).then(function(response){
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
            	
            },
            close: function(e) {
                // handle the event
              }
		});
		
		$("#createdDeptImpReqMa").focusout(function(){
			if($("#createdDeptImpReqMa").data("kendoAutoComplete").dataSource._data.length == 0){
				$("#createdDeptImpReqMa").val(null);
			}
		});
		
		
		/*vm.chkSelectAll = function(item) {
	    	var grid = vm.imReqMaGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    };
	    vm.handleCheck = function(item){
			if(document.getElementById("checkalllistImpReq").checked == true){
				document.getElementById("checkalllistImpReq").checked = false;
			}
		}*/
		$scope.listCheck=[];
		vm.handleCheck=handleCheck;
    	function handleCheck(dataItem){
    		if(dataItem.selected){
    		$scope.listCheck.push(dataItem);
    		} else {
    			for(var i=0;i<$scope.listCheck.length;i++){
    				if($scope.listCheck[i].orderId===dataItem.orderId){
    				$scope.listCheck.splice(i,1);
    				}
    			}
				$('[name="gridchkselectall"]').prop('checked', false);
    		}
    		}
    	vm.chkSelectAll=chkSelectAll;
		$scope.checkSearch=false;
		function chkSelectAll(){
			var grid = vm.imReqMaGrid;
	    		chkSelectAllBase(vm.chkAll, grid);
			if(vm.chkAll){
				if( $scope.checkSearch && $scope.dataSearch.length >0){
				$scope.listCheck=$scope.dataSearch;
				} else {
				
					CommonService.getallData("orderServiceRest/order/getAllImportRequirement",vm.orderSearch).then(function(data){
						$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}
		};
		
		vm.doSearch = doSearch;
		function doSearch(){
			if(!vm.validator.validate()){
				if(vm.validator._errors.impReqManaCreateFromDate){
					$("#impReqManaCreateFromDate").focus();
				}
				if(vm.validator._errors.impReqManaCreateToDate){
					$("#impReqManaCreateToDate").focus();
				}
				return;
			}
			trimSpace();
			vm.orderSearch.listStatus = $("#requirementStatus").data("kendoMultiSelect").value();
			vm.orderSearch.listBussinessType = $("#requirementType").data("kendoMultiSelect").value();
			vm.orderSearch.listSignState = $("#signStateCA").data("kendoMultiSelect").value();
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
				var grid = $("#imReqMaGrid").data("kendoGrid");	
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10
					});
				}
		}
		
		function onChangeGoodList(){
			
			if ($("#goodsListGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.orderGoodsDetailSearch = dataItem;
    			$("#goodsCodeAndName").text(vm.orderGoodsDetailSearch.goodsCode+" "+vm.orderGoodsDetailSearch.goodsName);
    			var grid = $("#goodsDetailGrid").data("kendoGrid");	
    			if(grid){
    				grid.dataSource.query({
    					page: 1,
    					pageSize: 10
    				});
    			} else {
    			fillDataTableGoodsDetail([]);
    			}
    			
    			console.log(vm.orderGoodsDetailSearch.goodsName);
			}
			
		}
		
		vm.listRemove=[{
			title: "Thao tác",
		},
		{
			title : "<input type='checkbox' id='checkalllistImpReq' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
		}]
		
		vm.exportExcelGrid = function(){
			vm.orderSearch.page = null;
			vm.orderSearch.pageSize = null;
			impReqManaService.getForExportGrid(vm.orderSearch).then(function(d) {
				CommonService.exportFile(vm.imReqMaGrid,d.data,vm.listRemove,vm.listConvert,"QuanLyYeuCauNhapKho");
			});
			
		}
	
		var record=0;
		function fillDataTableImReqMa(data) {
			vm.imReqMaGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				toolbar: [
		                    {
		                        name: "actions",
		                        template: '<div class=" pull-left ">'+
		                        '<button class="btn btn-qlk padding-search-right addQLK margin_right10"'+
		      					'ng-click="vm.createNew()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
		      					 '<button class="btn btn-qlk padding-search-right TkQLK"'+
			      					'ng-click="vm.sendToSign()" uib-tooltip="Trình ký" translate>Trình ký</button>'+
		      					'</div>'	
		        				+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.imReqMaGrid.columns.slice(1,vm.imReqMaGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
		    				dataBound: function (e) {
		    				    var grid = vm.imReqMaGrid;
		    				    grid.tbody.find("tr").dblclick(function (e) {
		    				        var dataItem = grid.dataItem(this);
		    				        vm.showDetail(dataItem.orderId)
		    				    });
		    				},dataBinding: function() {
		                        record = (this.dataSource.page() -1) * this.dataSource.pageSize();
		                    },
				dataSource:{
					serverPaging: true,
					 schema: {
						 errors: function (response) {
								if(response.error){
									toastr.error(response.error);
								}
								return response.error;
							},
						 total: function (response) {
							 	$("#importRegCount").text(""+response.total);
							 	vm.count = response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].orderId===$scope.listCheck[i].orderId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "orderServiceRest/order/doSearchImportRequirement",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
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
				columns: [{
					title : "<input type='checkbox' id='checkalllistImpReq' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck(dataItem)' ng-model='dataItem.selected'/>",
			        width: "5%",
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				},
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
					field: "code",
					template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(#=orderId#)>#=code#</a>',
			        width: "16%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại yêu cầu",
			        field: "bussinessType",
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
					 field: "createdDate",
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					}
				},  {
					title: "Ký CA",
					 field: "signState",
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
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Trạng thái",
					 field: "status",
			        width: "15%",
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
					title: "Thao tác",
			        template: dataItem =>
			        	'<div class="text-center #=orderId#"">'+
					'<button  style=" border: none; background-color: white;"'+
					'class="#=orderId# icon_table"  uib-tooltip="Sửa" translate'+
						'  ng-click=vm.update(dataItem.orderId) ng-hide="dataItem.status!=1">'+
						'<i   class=" fa fa-pencil" aria-hidden="true"></i>'+
					'</button>'+
					'<button  style=" border: none; background-color: white;"'+
					'class="#=orderId# icon_table"  uib-tooltip="Xóa" translate'+
						'  ng-click=vm.openRemovePopup(dataItem) ng-hide="dataItem.status!=1">'+
					'<i   style="color:steelblue;" class="fa fa-trash" aria-hidden="true"></i>	'+
					'</button>'
					+'<a type="button"'+
					'class="#=orderId# icon_table"  uib-tooltip="Khóa" translate'+
					'  >'+
					'<i style="color:grey" ng-show="dataItem.status!=1"  class="fa fa-pencil" aria-hidden="true"></i>'+
					'</a>'+
					'<a type="button"'+
					'class="#=orderId# icon_table"  uib-tooltip="Khóa" translate'+
					'  >'+
					'<i style="color:grey" ng-show="dataItem.status!=1"  class="fa fa-trash" aria-hidden="true"></i>	'+
					'</a>'
					+'</div>',
			        width: "20%",
			        field:"stt"
				}
				,]
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
		
		$scope.$watch('vm.orderGoodsSearch', function() {
			var grid = $("#goodsListGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
//		$scope.$watch('vm.orderGoodsDetailSearch.goodsName', function() {
//			var grid = $("#goodsDetailGrid").data("kendoGrid");	
//			if(grid){
//				grid.dataSource.query({
//					page: 1,
//					pageSize: 10
//				});
//			}
//	    });

		
		function refreshGrid(d) {
			var grid = vm.imReqMaGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.createNew = function(){
			vm.order ={};
			vm.order.title = "Tạo mới yêu cầu nhập kho";
			impReqManaService.setData(vm.order);
			$rootScope.$broadcast("importOrderDetail", vm.order);
			vm.goTo('CREATE_IM_REQ_MANAGE');
		}
		vm.closePopup = function(){
			
		}
		vm.closeDetail = function(){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
		}
		vm.advancedSearch =function(){
			vm.showAdvancedSearch = !vm.showAdvancedSearch;
		}
		vm.rowIndex=null;
		vm.sendToSign = function(){
			var teamplateUrl="wms/popup/SignVofficePopup.html";
			 var title="Trình ký yêu cầu nhập kho";
			 var windowId="ORDER";
			 vm.nameToSign="Danh sách yêu cầu trình ký"+"("+$scope.listCheck.length+")";
			 var selectedRow = [];
			 for(var i in $scope.listCheck){
				 selectedRow.push($scope.listCheck[i].orderId);
			 }
				if ($scope.listCheck.length === 0) {
						toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
						return;
				}
				var obj=vm.orderSearch;
				obj.listId=selectedRow;
				obj.type="01";
				obj.reportName="YeuCauNhapKho_KhongSerial";
				CommonService.getDataSign(obj).then(function(data){
					if(data.error){
								toastr.error(data.error);
								return;
								}
				var dataList=data.plain();
				
			 
				CommonService.populatePopupVofice(teamplateUrl,title,'01',dataList,vm,windowId,false,'85%','85%');
		
				
		});
		}
		vm.showDetail=function(id){
			vm.showTabOne = true;
			var teamplateUrl="wms/importRequirementManagement/detailPopup.html";
			 var title="Chi tiết yêu cầu nhập kho: " + vm.order.code;
			 var windowId="ORDER";
			 vm.order={};
			
			 impReqManaService.getOrderDetailById(id).then(function(d) {
				 vm.order = d.plain();
				 vm.orderGoodsSearch.orderId = vm.order.orderId;
				 vm.checkBoxAutoLoad();
				 fillDataTableGoodsList([]);
				 title="Chi tiết yêu cầu nhập kho: " + vm.order.code;
				CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%');
	
				
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.openDepartment=function(){
			var obj={};
// obj.page=1;
// obj.pageSize=20;
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
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data1, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		vm.onSave=onSave;
		function onSave(data){
			/*vm.orderSearch.createdDeptedName = data.text;
			vm.orderSearch.createdDeptedId = data.id;*/
			$("#createdDeptImpReqMa").val(data.text);
			vm.orderSearch.createdDeptedName = data.text;
		}
		
		vm.exportSerial = function(){
			impReqManaService.exportSerial(vm.order).then(function(d) {
				toastr.success("Export thành công!");
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
		
		vm.openRemovePopup=openRemovePopup;
		function openRemovePopup(dataItem){
			var data =	dataItem;
			vm.orderDelete = data;
			vm.reason ={};
			vm.reason.apply = "4";
			vm.reason.status = 1;
			data.cancelByName = Constant.user.vpsUserToken.fullName;
			impReqManaService.getReasonForCombobox(vm.reason).then(function(d) {
				vm.dataReasonDelete = d.plain();
				}, function() {
					console.error('Error');
				});
			var teamplateUrl = "wms/importRequirementManagement/import_order_delete_popup.html";
			var title="Hủy yêu cầu nhập kho";
			var windowId="APP_PARAM"
			data.cancelDate=kendo.toString(new Date(),"dd/MM/yyyy");
			CommonService.populatePopupCreate(teamplateUrl,title,data,vm,windowId,false,'50%','40%'); 
		}
		
		vm.remove = function(){
			if(!vm.validator.validate()){
				$("#cancelReasonCode").data("kendoDropDownList").focus();
				return;
			}
			vm.orderDelete.cancelBy = Constant.user.vpsUserToken.sysUserId;
			vm.orderDelete.cancelByName = Constant.user.vpsUserToken.fullName;
			impReqManaService.remove(vm.orderDelete).then(function(mess){
				if(mess.error){
					toastr.error(mess.error);
					return;
				}
        		toastr.success("Xóa thành công!");
        		
        		$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
        		var sizePage = $("#imReqMaGrid").data("kendoGrid").dataSource.total();
				var pageSize = $("#imReqMaGrid").data("kendoGrid").dataSource.pageSize();
				if(sizePage % pageSize == 1){
					var currentPage = $("#imReqMaGrid").data("kendoGrid").dataSource.page();
			        if (currentPage > 1) {
			            $("#imReqMaGrid").data("kendoGrid").dataSource.page(currentPage - 1);
			        }
				}
				$('#imReqMaGrid').data('kendoGrid').dataSource.read();
				$('#imReqMaGrid').data('kendoGrid').refresh();
            }, function(errResponse) {
            	if (errResponse.status == 406) {
                    toastr.error("Người dùng hiện tại không có quyền xóa bản ghi này!");
                } else {
               	 toastr.error("Lỗi khi lưu");
                }
			});
		}
		vm.cancel = function(){
			//confirm('Xác nhận hủy bỏ thao tác này', function () {
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			//});
		}
		
		vm.close = function(){
				CommonService.closePopup1();
			}
		vm.update = function(id){
			impReqManaService.getOrderDetailById(id).then(function(d) {
				 vm.order = d.plain();
				 vm.order.title = "Cập nhật yêu cầu nhập kho";
				 vm.order.page = vm.orderSearch.page;
				 vm.order.pageSize = vm.orderSearch.pageSize;
				 impReqManaService.setData(vm.order);
				 $rootScope.$broadcast("importOrderDetail", vm.order);
				}, function() {
					console.error('Error');
				});
			CommonService.goTo('UPDATE_IM_REQ_MANAGE');
		}
		vm.checkbox = function(){
			vm.showFromProvider = false;
			vm.showBeforeWarranty = false;
			vm.showAfterWarranty = false;
			vm.showFromConstruction = false;
			vm.showFromUnit = false;
			vm.showFromBorrowedGoods = false;
			vm.showFromAlternativeStock = false;
			vm.showFromProject = false;
			if (document.getElementById("fromProvider").checked == true) {
		        // Checkbox has been checked
		    	vm.showFromProvider = true;
		    	vm.order.bussinessType = vm.businessTypes[0].code;
		    }
			if (document.getElementById("beforeWarranty").checked == true) {
		        // Checkbox has been checked
		    	vm.showBeforeWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[1].code;
		    }
			if (document.getElementById("afterWarranty").checked == true) {
		        // Checkbox has been checked
		    	vm.showAfterWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[2].code;
		    }
			if (document.getElementById("fromConstruction").checked == true) {
		        // Checkbox has been checked
		    	vm.showFromConstruction = true;
		    	vm.order.bussinessType = vm.businessTypes[3].code;
		    }
			if (document.getElementById("fromUnit").checked == true) {
			        // Checkbox has been checked
			    vm.showFromUnit = true;
			    vm.order.bussinessType = vm.businessTypes[4].code;
			}
			if (document.getElementById("fromBorrowedGoods").checked == true) {
		        // Checkbox has been checked
		    	vm.showFromBorrowedGoods = true;
		    	vm.order.bussinessType = vm.businessTypes[5].code;
		    }
			if (document.getElementById("fromAlternativeStock").checked == true) {
		        // Checkbox has been checked
		    	vm.showFromAlternativeStock = true;
		    	vm.order.bussinessType = vm.businessTypes[6].code;
		    }
			if (document.getElementById("fromProject").checked == true) {
		        // Checkbox has been checked
		    	vm.showFromProject = true;
		    	vm.order.bussinessType = vm.businessTypes[7].code;
		    }
			if (document.getElementById("direct").checked == true) {
		        // Checkbox has been checked
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
		        // Checkbox has been checked
		    	vm.showFromProvider = true;
		    	$("#fromProvider").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[1].code) {
		        // Checkbox has been checked
		    	vm.showBeforeWarranty = true;
		    	$("#beforeWarranty").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[2].code ) {
		        // Checkbox has been checked
		    	vm.showAfterWarranty = true;
		    	$("#afterWarranty").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[3].code ) {
		        // Checkbox has been checked
		    	vm.showFromConstruction = true;
		    	$("#fromConstruction").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[4].code ) {
			        // Checkbox has been checked
			    vm.showFromUnit = true;
			    $("#fromUnit").prop("checked", true);
			}
			if (vm.order.bussinessType == vm.businessTypes[5].code ) {
		        // Checkbox has been checked
		    	vm.showFromBorrowedGoods = true;
		    	$("#fromBorrowedGoods").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[6].code ) {
		        // Checkbox has been checked
		    	vm.showFromAlternativeStock = true;
		    	$("#fromAlternativeStock").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[7].code ) {
		        // Checkbox has been checked
		    	vm.showFromProject = true;
		    	$("#fromProject").prop("checked", true);
		    }
			if (vm.order.bussinessType == vm.businessTypes[8].code ) {
		        // Checkbox has been checked
				$("#direct").prop("checked", true);
		    }
		}
		$scope.$on("Popup.open", function () {
			vm.checkBoxAutoLoad();
			statusName();
			signStateName();
			
        });
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
        		vm.imReqMaGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.imReqMaGrid.showColumn(column);
            } else {
            	vm.imReqMaGrid.hideColumn(column);
            }
        	
        	
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
	// =================================Clear
	// Textbox================================//
	vm.clearSearchDate = function(){
		vm.orderSearch.createdDateFrom = null;
		vm.orderSearch.createdDateTo = 	null;
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
		$('#requirementType').data("kendoMultiSelect").value([]);
	}
	vm.clearSearchCreatedDeptName = function(){
		$("#createdDeptImpReqMa").val("");
		vm.orderSearch.createdDeptedName = null;
	}
	// =================================End Clear
	// Textbox===============================//
	
	//validate ngay thang
	vm.checkErr = checkErr;
	function checkErr() {
		if(!vm.validator.validate()){
			$("#impReqManaCreateToDate").focus();
			return;
		}
		var startDate = $('#impReqManaCreateFromDate').val();
		var endDate = $('#impReqManaCreateToDate').val();
		vm.errMessage = '';
		var d = new Date();
		var curDate = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
        
        if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
          vm.errMessage = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
          $("#impReqManaCreateFromDate").focus();
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
			$("#impReqManaCreateFromDate").focus();
			return;
		}
		var startDate = $('#impReqManaCreateFromDate').val();  
		var endDate = $('#impReqManaCreateToDate').val();
		vm.errMessage1 = '';
		var curDate = new Date();
        
        if(kendo.parseDate(startDate,"dd/MM/yyyy") > curDate){
	           vm.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày hiện tại';
	           $("#impReqManaCreateFromDate").focus();
	           return vm.errMessage1;
	        }
	        else if(startDate <= curDate){
		           vm.errMessage1 = '';
		           return vm.errMessage1;
		        }
        
    }
	
	//Close popup
	$(document).on("click",".k-overlay",function(){
		 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	});
	
	
//
	}
})();
