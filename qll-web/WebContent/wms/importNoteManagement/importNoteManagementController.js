(function() {
	'use strict';
	var controllerId = 'impNoteManaController';
	
	angular.module('MetronicApp').controller(controllerId, impNoteManaController);
	
	function impNoteManaController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,impNoteManaService, impReqManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTabOne = true;
		vm.showTabOneNoteDetail = true;
		vm.showTabOneTwoDetail = false;
		vm.showTabThreeNoteDetail = false;
		
		vm.disableCheckboxes = true;
		vm.disableInput = true;
		
		vm.order={};
		vm.orderSearch ={};
		vm.orderGoodsSearch ={};
		vm.orderGoodsDetailSearch ={};
		vm.stockTransSearch ={};
		vm.stockTrans={};
		vm.stockTransDetail={};
		vm.stockTransDetailSearch ={};
		vm.stockTransDetailSerialSearch ={};
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
		
		vm.stockTransSearch.listStatus = ["1"];
		vm.stockTransSearch.listSignState = ["1"];
		vm.stockTransSearch.listStockId = [];
		
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
		vm.listRemove=[{
			title: "Thao tác",
		},
		{
			title: "<input type='checkbox' id='checkalllistImpNote' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
		}];
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
	        	$("#findImpNote").click();
	        }
	        });
	
		setTimeout(function(){
			  $("#impNoteCode").focus();
			},15);
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		$("#impNoteManaCreDept").kendoAutoComplete({
			dataTextField: "text",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.stockTransSearch.createdDeptName = dataItem.text;
                vm.stockTransSearch.createdDeptId = dataItem.id;
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("departmentServiceRest/" + 'department/getall').post({pageSize:10, page:1, name:$("#impNoteManaCreDept").val().trim()}).then(function(response){
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
		
		$("#impNoteManaCreDept").focusout(function(){
			if($("#impNoteManaCreDept").data("kendoAutoComplete").dataSource._data.length == 0){
				$("#impNoteManaCreDept").val(null);
			}
		});
/*		vm.chkSelectAll = function(item) {
	    	var grid = vm.impNoteManaGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
	    };
	    vm.handleCheck = function(item){
			if(document.getElementById("checkalllistImpNote").checked == true){
				document.getElementById("checkalllistImpNote").checked = false;
			}
		}*/
		$scope.listCheck=[];
		vm.handleCheck=handleCheck;
    	function handleCheck(dataItem){
    		if(dataItem.selected){
    		$scope.listCheck.push(dataItem);
    		} else {
    			for(var i=0;i<$scope.listCheck.length;i++){
    				if($scope.listCheck[i].stockTransId===dataItem.stockTransId){
    				$scope.listCheck.splice(i,1);
    				}
    			}
				$('[name="gridchkselectall"]').prop('checked', false);
    		}
    		}
    	vm.chkSelectAll=chkSelectAll;
		$scope.checkSearch=false;
		function chkSelectAll(){
			var grid = vm.impNoteManaGrid;
	    		chkSelectAllBase(vm.chkAll, grid);
			if(vm.chkAll){
				if( $scope.checkSearch && $scope.dataSearch.length >0){
				$scope.listCheck=$scope.dataSearch;
				} else {
				
					CommonService.getallData("stockTransServiceRest/stockTrans/getAllImportNote",vm.stockTransSearch).then(function(data){
						$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}
		};
		initFormData();
		function initFormData() {
			vm.stockTransSearch.createdDateFrom=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy")
			vm.stockTransSearch.createdDateTo = null;
			vm.stockTransSearch.createdBy = Constant.user.vpsUserToken.sysUserId;
			vm.stockTransSearch.createdByName = Constant.user.vpsUserToken.fullName;
			
			fillDataTableImpNoteMana([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		impNoteManaService.getForOrderCheckboxes(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
		});
		vm.onSave=onSave;
		function onSave(data){
				$("#impNoteManaCreDept").val(data.text);
				vm.stockTransSearch.createdDeptName = data.text;
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
    			}else{
    				fillDataTableGoodsDetail([]);
    			}
    			
			}
			
		}
		
		function onChangeGoodListForNote(){
			if ($("#goodsListForNoteGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListForNoteGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListForNoteGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.stockTransDetailSerialSearch = dataItem;
    			$("#goodsCodeAndNameForNote").text(vm.stockTransDetailSerialSearch.goodsCode+" "+vm.stockTransDetailSerialSearch.goodsName);
    			var grid = $("#goodsDetailForNoteGrid").data("kendoGrid");	
    			if(grid){
    				grid.dataSource.query({
    					page: 1,
    					pageSize: 10
    				});
    			}else{
    				fillDataTableGoodsDetailForNote([]);
    			}
			}
		}
		
		vm.exportExcelGrid = function(){
			vm.stockTransSearch.page = null;
			vm.stockTransSearch.pageSize = null;
			impNoteManaService.getForExportGrid(vm.stockTransSearch).then(function(d) {
				CommonService.exportFile(vm.impNoteManaGrid,d.data,vm.listRemove,vm.listConvert,"QuanLyPhieuNhapKho");
			});
		}
		
		var record=0;
		function fillDataTableImpNoteMana(data) {
			vm.impNoteManaGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataBound: function (e) {
				    var grid = $("#impNoteManaGrid").data("kendoGrid");
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
				        vm.showNoteDetail(dataItem.stockTransId);
				    });
				},
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				toolbar: [
		                    {
		                        name: "actions",
		                        template: '<div class=" pull-left ">'+
		                        '<button class="btn btn-qlk padding-search-right TkQLK"'+
		      					'ng-click="vm.sendToSign()" uib-tooltip="Trình ký" translate>Trình ký</button>'+
		      					'</div>'	
		        				+
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
		                    '<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.impNoteManaGrid.columns.slice(1,vm.impNoteManaGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
							 $("#impNoteCount").text(""+response.total);
							 vm.count = response.total;
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].stockTransId===$scope.listCheck[i].stockTransId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "stockTransServiceRest/stockTrans/doSearchImportNote",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                             //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.stockTransSearch.page = options.page
								vm.stockTransSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockTransSearch)
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
					pageSize: 10,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				columns: [{
					title : "<input type='checkbox' id='checkalllistImpNote' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck(dataItem)' ng-model='dataItem.selected'/>",
			        width: 35,
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
				},  {
					title: "Mã yêu cầu",
					field: "orderCode",
					template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(#=orderId#)>#=orderCode#</a>',
			        width: "16%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Mã phiếu",
					field: "code",
					template: '<a class="#=stockTransId#" href="javascript:void(0);" ng-click=vm.showNoteDetail(#=stockTransId#)>#=code#</a>',
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
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
					title: "Trạng thái",
					 field: 'status',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					template: function($scope){
						if($scope.status == 1){
							return "Chưa nhập";
						}else if($scope.status == 2){
							return "Đã nhập";
						}else if($scope.status == 3){
							return "Đã hủy";
						}
					},
					
				},  {
					title: "Tình trạng ký CA",
					 field: 'signState',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
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

				},{
					title: "Thao tác",
			        template:dataItem =>
			        	'<div class="text-center #=stockTransId#""><button  style=" border: none; background-color: white;" '+
						' class=" icon_table #=stockTransId#"'+
						' uib-tooltip="Thực nhập" translate'+
						' ng-click=vm.realImport(dataItem.stockTransId) ng-hide="vm.showHideButton(dataItem)">'+'<i style="color:steelblue;"  class="fa fa-download" aria-hidden="true"></i>'+
					'</button>'+
					'<button  style=" border: none; background-color: white;"'+
					'class="#=stockTransId# icon_table"  uib-tooltip="Sửa" translate'+
						' ng-click=vm.edit(dataItem.stockTransId) ng-hide="vm.showHideButton(dataItem)">'+
						'<i  class="fa fa-pencil" aria-hidden="true"></i>'+
					'</button>'+
					'<button  style=" border: none; background-color: white;"'+
					'class="#=stockTransId# icon_table"  uib-tooltip="Xóa" translate'+
						' ng-click=vm.openRemovePopup(dataItem) ng-hide="vm.showHideButton(dataItem)">'+
					'<i style="color:steelblue;"  class="fa fa-trash" aria-hidden="true"></i>	'+
					'</button>'
					+'<a '+
					' class=" icon_table #=stockTransId#"'+
					' uib-tooltip="Khóa" translate'+
					' >'+'<i style="color:grey" ng-show="vm.showHideButton(dataItem)" class="fa fa-download" aria-hidden="true"></i>'+
				'</a>'+
				'<a type="button"'+
				'class="#=stockTransId# icon_table"  uib-tooltip="Khóa" translate'+
					'>'+
					'<i style="color:grey" ng-show="vm.showHideButton(dataItem)" class="fa fa-pencil" aria-hidden="true"></i>'+
				'</a>'+
				'<a type="button"'+
				'class="#=stockTransId# icon_table"  uib-tooltip="Khóa" translate'+
					'>'+
				'<i style="color:grey" ng-show="vm.showHideButton(dataItem)" class="fa fa-trash" aria-hidden="true"></i>	'+
				'</a>'+'</div>',
			        width: "15%",
			        field:"stt"
				}
				,]
			});
		}
		$scope.$watch('vm.stockTransDetailSearch.stockTransId', function() {
			var grid = $("#goodsListForNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
		function fillDataTableGoodsListForNote(data) {
			vm.goodsListForNoteGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				change : onChangeGoodListForNote,
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
							vm.stockTransDetail=response.data;
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "stockTransDetailServiceRest/stockTransDetail/doSearchGoodsForImportNote",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stockTransDetailSearch.page = options.page
								vm.stockTransDetailSearch.pageSize = options.pageSize
								vm.stockTransDetailSearch.stockTransId = vm.stockTrans.stockTransId
								return JSON.stringify(vm.stockTransDetailSearch)
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
					pageSize: 10,
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
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Số lượng",
					 field: 'amountOrder',
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
		$scope.$watch('vm.stockTransDetailSerialSearch', function() {
			var grid = $("#goodsDetailForNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
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
							url: Constant.BASE_SERVICE_URL + "stockTransDetailSerialServiceRest/stockTransDetailSerial/doSearchGoodsDetailForImportNote",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                           //  vm.appParamSearch.employeeId = Constant.user.srvUser.catEmployeeId;
							    vm.stockTransDetailSerialSearch.page = options.page
								vm.stockTransDetailSerialSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stockTransDetailSerialSearch)
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
					pageSize: 10,
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
				change : onChangeGoodList,
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
					pageSize: 10,
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
		
		$scope.$watch('vm.stockTransDetailSearch', function() {
			var grid = $("#goodsListForNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
		$scope.$watch('vm.stockTransDetailSerialSearch.goodsName', function() {
			var grid = $("#goodsDetailForNoteGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    });
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
		
		function refreshGrid(d) {
			var grid = vm.imReqMaGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.doSearch = function(){
			if(vm.stockTransSearch.createdDateFrom == ""){
				vm.stockTransSearch.createdDateFrom = null;
			}
			if(vm.stockTransSearch.createdDateTo == ""){
				vm.stockTransSearch.createdDateTo = null;
			}
			vm.stockTransSearch.createdDeptName = $("#impNoteManaCreDept").val();
			if(vm.stockTransSearch.createdDeptName == ""){
				vm.stockTransSearch.createdDeptName = null;
			}
			trimSpace();
			if(!vm.validator.validate()){
				if(vm.validator._errors.impNoteCreateFrom){
					$("#impNoteCreateFrom").focus();
				}
				if(vm.validator._errors.impNoteCreateTo){
					$("#impNoteCreateTo").focus();
				}
				return;
			}
			vm.stockTransSearch.listStatus =  $("#importNoteState").data("kendoMultiSelect").value();
			vm.stockTransSearch.listSignState =  $("#signStateCAImportNoteMana").data("kendoMultiSelect").value();
			var grid = $("#impNoteManaGrid").data("kendoGrid");	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		vm.rowIndex=null;
		vm.sendToSign = function(){
			var teamplateUrl="wms/popup/SignVofficePopup.html";
			 var title="Trình ký phiếu nhập kho";
			 var windowId="ORDER";
			 vm.nameToSign="Danh sách phiếu trình ký"+"("+$scope.listCheck.length+")";
			 var selectedRow = [];
				for(var i in $scope.listCheck){
					selectedRow.push($scope.listCheck[i].stockTransId);
				}
				if (selectedRow.length === 0) {
						toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
						return;
				}
				var obj={};
				obj.listId=selectedRow;
				obj.type="03";
				obj.reportName="PhieuNhapKho_KhongSerial";
				CommonService.getDataSign(obj).then(function(data){
					if(data.error){
								toastr.error(data.error);
								return;
								}
				var dataList=data.plain();
				
			 
				CommonService.populatePopupVofice(teamplateUrl,title,'01',dataList,vm,windowId,false,'85%','85%');
		
				
		});
		}
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
		}
		//show detail note
		vm.list=[];
		vm.gotoTabOneNoteDetailPopUp = function(){
			vm.showTabOneNoteDetail = true;
			vm.showTabTwoNoteDetail = false;
			vm.showTabThreeNoteDetail = false;
		}
		vm.gotoTabTwoNoteDetailPopUp = function(){
			vm.showTabOneNoteDetail = false;
			vm.showTabTwoNoteDetail = true;
			vm.showTabThreeNoteDetail = false;
		}
		vm.gotoTabThreeNoteDetailPopUp = function(){
			var obj={};
				obj.objectId=vm.stockTrans.stockTransId;
				obj.type="03";
				CommonService.getDetailSign(obj).then(function(d){
					vm.list=d.plain();
				});
			vm.showTabOneNoteDetail = false;
			vm.showTabTwoNoteDetail = false;
			vm.showTabThreeNoteDetail = true;
		}
		
		vm.exportNoteWithSerial = function(){
			var obj={};
			var totalMoney = 0;
					for(var i=0;i<vm.stockTransDetail.length;i++){
					totalMoney += vm.stockTransDetail[i].totalPrice;
					}
			obj.stringMoney=	DocTienBangChu(kendo.parseFloat(totalMoney));
         	obj.stockTransId=vm.stockTrans.stockTransId;
         	obj.reportType="DOC";
         	obj.reportName="PhieuNhapKho_CoSerial";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
			        kendo.saveAs({dataURI: binarydata, fileName: "PhieuYeuCauCoSerial" + '.docx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export DOC được!");
				});
		}
		
		vm.exportNoteWithoutSerial = function(){
			var obj={};
			var totalMoney = 0;
					for(var i=0;i<vm.stockTransDetail.length;i++){
					totalMoney += vm.stockTransDetail[i].totalPrice;
					}
			obj.stringMoney=	DocTienBangChu(kendo.parseFloat(totalMoney));
         	obj.stockTransId=vm.stockTrans.stockTransId;
         	obj.reportType="DOC";
         	obj.reportName="PhieuNhapKho_KhongSerial";
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
			        kendo.saveAs({dataURI: binarydata, fileName: "PhieuYeuCauKhongSerial" + '.docx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export DOC được!");
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
		
		vm.showDetail=function(id){
			vm.showTabOne = true;
			var teamplateUrl="wms/importNoteManagement/detailPopup.html";
			 var title="Chi tiết yêu cầu nhập kho";
			 var windowId="ORDER";
			 vm.order={};
			 impReqManaService.getOrderDetailById(id).then(function(d) {
				 vm.order = d.plain();
				 vm.checkBoxAutoLoad();
				 vm.orderGoodsSearch.orderId = vm.order.orderId;
				 fillDataTableGoodsList([]);
				 title="Chi tiết yêu cầu nhập kho: "+ vm.order.code;
					CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
			 
		}
		
		vm.showNoteDetail=function(id){
			vm.showTabOneNoteDetail = true;
			vm.showTabTwoNoteDetail = false;
			vm.showTabThreeNoteDetail = false;
			var teamplateUrl="wms/importNoteManagement/noteDetailPopUp.html";
			 var title="Thông tin phiếu nhập kho";
			 var windowId="STOCK_TRANS";
			 vm.stockTrans={};
			 
			 impNoteManaService.getStockTransDetailById(id).then(function(d) {
				 vm.stockTrans = d.plain();
				 fillDataTableGoodsListForNote([]);
				 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
				}, function() {
					console.error('Error');
				});
			 
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
			noteStatusName();
			noteSignStateName();
        });
		
		$scope.$watch('vm.order.bussinessType', function() {
			vm.checkBoxAutoLoad();
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
		
		vm.edit = function(id){
			var teamplateUrl="wms/importNoteManagement/editNotePopUp.html";
			 var title="Cập nhật phiếu nhập kho";
			 var windowId="EDIT_STOCK_TRANS";
			 vm.stockTrans={};
			 
			 impNoteManaService.getStockTransDetailById(id).then(function(d) {
				 vm.stockTrans = d.plain();
				 fillDataTableGoodsListForNote([]);
				 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
				}, function() {
					console.error('Error');
				});
		}
		
		vm.save = function(){
			if(!vm.validator.validate()){
				if($("#editNoteShipper").val() == ""){
					$("#editNoteShipper").focus();
				}
				return;
			}
			impNoteManaService.updateImportNote(vm.stockTrans).then(function(d) {
				if(d.error){
					toastr.error(d.error);
				}
				toastr.success("Cập nhật thành công!");
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				$("#impNoteManaGrid").data("kendoGrid").dataSource.read();
        		$("#impNoteManaGrid").data("kendoGrid").refresh();
				}, function() {
					console.error('Error');
				});
		}
		vm.close = function(){
			//confirm('Xác nhận hủy bỏ thao tác này', function () {
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			//});
		}
		vm.closeDetail = function(){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
			var esc = $.Event("keydown",{keycode : 27});
		$(document).trigger(esc);
		
		vm.realImport = function(id){
			var teamplateUrl="wms/importNoteManagement/realImportPopUp.html";
			 var title="Thực nhập phiếu nhập kho";
			 var windowId="REAL_IMPORT_STOCK_TRANS";
			 vm.stockTrans={};

			 impNoteManaService.getStockTransDetailById(id).then(function(d) {
				 vm.stockTrans = d.plain();
				 fillDataTableGoodsListForNote([]);
				 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
				}, function() {
					console.error('Error');
				});
		}
		
		vm.openRemovePopup=openRemovePopup;
		function openRemovePopup(dataItem){
			var data =	dataItem;
			vm.stockTransDelete = data;
			vm.reason ={};
			vm.reason.apply = "4";
			vm.reason.status = 1;
			data.cancelByName = Constant.user.vpsUserToken.fullName;
			impReqManaService.getReasonForCombobox(vm.reason).then(function(d) {
				vm.dataReasonDelete = d.plain();
				}, function() {
					console.error('Error');
				});
			var teamplateUrl = "wms/importNoteManagement/import_note_delete_popup.html";
			var title="Hủy phiếu nhập kho";
			var windowId="APP_PARAM"
			data.cancelDate=kendo.toString(new Date(),"dd/MM/yyyy");
			CommonService.populatePopupCreate(teamplateUrl,title,data,vm,windowId,false,'50%','50%'); 
		}
		
		vm.remove = function(){
			if(!vm.validator.validate()){
				$("#cancelReasonCode").data("kendoDropDownList").focus();
				return;
			}
			vm.stockTransDelete.cancelBy = Constant.user.vpsUserToken.sysUserId;
			vm.stockTransDelete.cancelByName = Constant.user.vpsUserToken.fullName;
			impNoteManaService.removeStockTrans(vm.stockTransDelete).then(function(mess){
				if(mess.error){
					toastr.error(mess.error);
				}
        		toastr.success("Xóa thành công!");
        		$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
        		var sizePage = $("#impNoteManaGrid").data("kendoGrid").dataSource.total();
				var pageSize = $("#impNoteManaGrid").data("kendoGrid").dataSource.pageSize();
				if(sizePage % pageSize == 1){
					var currentPage = $("#impNoteManaGrid").data("kendoGrid").dataSource.page();
			        if (currentPage > 1) {
			            $("#impNoteManaGrid").data("kendoGrid").dataSource.page(currentPage - 1);
			        }
				}
				$('#impNoteManaGrid').data('kendoGrid').dataSource.read();
				$('#impNoteManaGrid').data('kendoGrid').refresh();
            }, function(errResponse) {
			});
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.impNoteManaGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.impNoteManaGrid.showColumn(column);
            } else {
            	vm.impNoteManaGrid.hideColumn(column);
            }
        	
        	
        }
		
		vm.showHideButton = function(dataItem){
			if(dataItem.status !=1){
				return true;
			}
			return false;
		}
		
		vm.saveRealImport = function(){
		/* if(vm.stockTrans.signState != '3'){
								toastr.error("Yêu cầu chưa được ký duyệt !");
								return;
							} */
			impNoteManaService.realImportNote(vm.stockTrans).then(function(d){
				if(d.error){
					toastr.error(d.error);
					return;
				}
        		toastr.success("Thực nhập thành công!");
        		$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
        		vm.doSearch();
            }, function(errResponse) {
            	if (errResponse.status == 406) {
                    toastr.error("Serial đã tồn trong kho");
                } else {
               	 toastr.error("Lỗi khi lưu");
                }
			});
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
		
		function noteStatusName(){
			if(vm.stockTrans.status==1){
				vm.stockTrans.statusName = "Chưa nhập/xuất";
			}else if(vm.stockTrans.status==2){
				vm.stockTrans.statusName = "Đã nhập/xuất";
			}else if(vm.stockTrans.status==3){
				vm.stockTrans.statusName = "Đã hủy";
			}
		}
		
		function noteSignStateName(){
			if(vm.stockTrans.signState==1){
				vm.stockTrans.signStateName = "Chưa trình ký";
			}else if(vm.stockTrans.signState==2){
				vm.stockTrans.signStateName = "Đã trình ký";
			}else if(vm.stockTrans.signState==3){
				vm.stockTrans.signStateName = "Đã ký";
			}else if(vm.stockTrans.signState==4){
				vm.stockTrans.signStateName = "Đã từ chối";
			}
		}
		// =================================Clear
		// Textbox================================//
		vm.clearSearchDate = function(){
			vm.stockTransSearch.createdDateFrom = null;
			vm.stockTransSearch.createdDateTo = null;
		}
		vm.clearOrderCode = function(){
			vm.stockTransSearch.orderCode = null;
		}
		vm.clearSearchStatus = function(){
			vm.stockTransSearch.listStatus = [];
		}
		vm.clearSearchCreatedBy = function(){
			vm.stockTransSearch.createdByName = null;
		}
		vm.clearSearchSignState = function(){
			vm.stockTransSearch.listSignState = [];
		}
		vm.clearSearchCreatedDeptName = function(){
			$("#impNoteManaCreDept").val("");
			vm.stockTransSearch.createdDeptName = null;
		}
		
		// =================================End Clear
		// Textbox===============================//
		//validate ngay thang
		vm.checkErr = checkErr;
		function checkErr() {
			if(!vm.validator.validate()){
				$("#impNoteCreateTo").focus();
				return;
			}
			var startDate = $('#impNoteCreateFrom').val();
			var endDate = $('#impNoteCreateTo').val();
			vm.errMessage = '';
			var d = new Date();
			var curDate = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
	        
	        if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
	          $("#impNoteCreateFrom").focus();
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
				$("#impNoteCreateFrom").focus();
				return;
			}
			var startDate = $('#impNoteCreateFrom').val();  
			var endDate = $('#impNoteCreateTo').val();
			vm.errMessage1 = '';
			var curDate = new Date();
	        
	        if(kendo.parseDate(startDate,"dd/MM/yyyy") > curDate){
		           vm.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày hiện tại';
		           $("#impNoteCreateFrom").focus();
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
