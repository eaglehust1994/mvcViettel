(function() {
	'use strict';
	var controllerId = 'createImpReqManaController';
	
	angular.module('MetronicApp').controller(controllerId, createImpReqManaController);
	
	function createImpReqManaController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,createImpReqManaService, impReqManaService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.showStepOne = true;
		
		vm.folder = '';
		vm.showFromProvider = true;
		vm.showBeforeWarranty = false;
		vm.showAfterWarranty = false;
		vm.showFromConstruction = false;
		vm.showFromUnit = false;
		vm.showFromBorrowedGoods = false;
		vm.showFromAlternativeStock = false;
		vm.showFromProject = false;
		vm.showFromDirect = false;
		vm.hideDeleteRow = false;
		vm.disableOrderType = false;
		
		vm.disableInput = false;
		vm.disableImport = false;
		
		vm.changeStock = false;
		
		
		vm.order={};
		if(impReqManaService.getData()){
			vm.order = impReqManaService.getData();
			if(vm.order.bussinessType != undefined || vm.order.bussinessType != null){
				vm.disableOrderType = true;
				$("#creImpReqBCIconOne").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconOne").addClass("acceptQLK postion-icon");
				$("#creImpReqBCIconThree").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconThree").addClass("acceptQLK postion-icon");
				$("#creImpReqBCIconTwo").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconTwo").addClass("acceptQLK postion-icon");
				$("#creImpReqBCIconFour").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconFour").addClass("acceptQLK postion-icon");
			}
		}
		$scope.$on("importOrderDetail", function (event, item) {
        	if(item != undefined){
        		vm.order = item;
        		vm.tmpStockId = vm.order.stockId;
        		vm.disableOrderType = true;
        		$("#creImpReqBCIconOne").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconOne").addClass("acceptQLK postion-icon");
				$("#creImpReqBCIconThree").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconThree").addClass("acceptQLK postion-icon");
				$("#creImpReqBCIconTwo").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconTwo").addClass("acceptQLK postion-icon");
				$("#creImpReqBCIconFour").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconFour").addClass("acceptQLK postion-icon");
        	}else{
        		vm.changeStock = true;
        		console.error("không nhận được dữ liệu!");
        	}
        });
		
			$scope.$watch('vm.order.stockId', function() {
				if(vm.changeStock){
					var obj={};
					obj.value="YCNK";
					obj.orgValue="AAA";
					obj.stockId=vm.order.stockId;
					CommonService.genCode(obj).then(
						function(d) {
							vm.order.code = d;
						});
				}
		    });
			$scope.$watch('vm.tmpStockId', function() {
				vm.changeStock = true;
			});	
		
		vm.businessTypes =[];
		vm.dataSave = {};
		vm.goods = [];
		vm.goodsDetail = [];
		var listOrderGoodsDTO = [];
		var listOrderGoodsDetailDTO = [];
		
		vm.appParams={};
		vm.appParams.parType = 'IMPORT_ORDER_TYPE';
		vm.appParams.page = 1;
		vm.appParams.pageSize = 10;
		
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
		
		vm.closeDetailGoodPopup = function(){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		
		$("#orderGoods").kendoAutoComplete({
			dataTextField: "name",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.goods.id = dataItem.goodsId; // thành id
                vm.goods.goodsCode = dataItem.code;
                vm.goods.goodsName = dataItem.name;
                vm.goods.goodsStateName = "Bình thường";
                vm.goods.goodsUnitName = dataItem.goodsUnitName;
                vm.goods.goodsUnitId = dataItem.goodsUnitId;
                vm.goods.manufacturerId = dataItem.manufacturerId;
				vm.goods.manufacturerName = dataItem.manufacturerName;
				vm.goods.producingCountryId = dataItem.producingCountryId;
				vm.goods.producingCountryName = dataItem.producingCountryName;
				vm.goods.partNumber = dataItem.partNumber;
				vm.goods.price = dataItem.price;
				vm.goods.serial = dataItem.serial;
				vm.goods.amount = 1;
                
                var grid = $("#creImReqMaGrid").data("kendoGrid");
                
                vm.dataRemaining = $("#creImReqMaGrid").data().kendoGrid.dataSource.view();
                vm.numbOfData = vm.dataRemaining.length;
        		if(vm.dataRemaining.length == 0){
        			grid.dataSource.insert(vm.goods);
        		}else{
        			var countSame = 0;
        			vm.newItem = null;
        			for(var j = 0; j<vm.numbOfData;j++){
            			if(vm.dataRemaining[j].goodsName == vm.goods.goodsName && vm.dataRemaining[j].goodsCode == vm.goods.goodsCode && vm.dataRemaining[j].goodsStateName == vm.goods.goodsStateName){
            				countSame +=1;
            			}else{
            					vm.newItem = vm.goods;
            			}
            		}
        			
        			if(countSame == 1){
        				toastr.warning("Hàng hóa "+vm.goods.goodsCode+" đã tồn tại trên lưới dữ liệu");
        			}else{
        				grid.dataSource.insert(vm.newItem);
        			}
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
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã hàng</p>' +
            '<p class="col-md-6 text-header-auto">Tên hàng</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                	vm.goods.goodsId = null; // thành id
	                vm.goods.code = null;
	                vm.goods.name = null;
                }
            },
            close: function(e) {
                // handle the event
            	document.getElementById("orderGoods").value = "";
              }
		});
		
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillDataImportErrTable([]);
			
		}
		
		createImpReqManaService.getForOrderCheckboxes(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
			if(vm.order.orderId == null){
				vm.order.bussinessType = vm.businessTypes[0].code;
				vm.order.shipmentCode = "";
				vm.order.maintainOrderCode = "filled";
				vm.order.maintainReportCode ="filled";
				vm.order.consRetrieveOrderCode ="filled";
				vm.order.deptRetrieveOrderCode = "filled";
				vm.order.loanRetrieveOrderCode = "filled";
				vm.order.stockTransCode ="filled";
				vm.order.projectCode ="filled";
			}
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
			
			if(vm.order.orderId != null){
				checkBoxAutoLoad();
			}

		});
		function fillDataTable(data) {
			var dataSource = new kendo.data.DataSource({
                pageSize: 10,
                data: data,
                autoSync: false,        
                schema: {
                    model: {
                    	fields: {
                    		stt: {editable: false},
                    		goodsCode: {editable: false},
                    		goodsName: {editable: false},
                    		goodsUnitName: {editable: false},
							amount:  { type: "number", format: "{0:c}" },
                    		goodsStateName: { defaultValue: { goodsStateName: "~~Chọn~~", status : "choese"} },
                    		serial: {editable: false},
                    		choose: {editable: false},
                    	}
                    }
                }
            });
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: dataSource,
				noRecords: true,
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
				columnMenu: false,
				columns: [
				{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#creImReqMaGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
			        width: 80,
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
					editor: statusDropDownEditor,
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},  {
					title: "Chi tiết hàng hóa",
					field:'serial',
					template: "# if(serial != null){ #" + "<a  ng-click=vm.showGoodsDetail(dataItem)>#= 'Chi tiết' #</a>" + "# } " + "else if (serial == null) { # " + "#= '' #"+ "#} #",
					//dataItem => '<a class="#=goodsId#" href="javascript:void(0);" ng-click=vm.showGoodsDetail(dataItem)>Chi tiết</a>',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title: "Chọn",
					field:'choose',
			        template: dataItem =>
			        	'<div class="text-center #=id#"">'+
						'<button  style=" border: none; background-color: white;"'+
						'class="#=id# icon_table"  uib-tooltip="Xóa" translate'+
							' ng-hide="vm.hideDeleteRow" ng-click=vm.removeRow(dataItem.uid)>'+
						'<i style="color:steelblue;"  class="fa fa-trash" aria-hidden="true"></i>	'+
						'</button>'
						+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		
		function fillDataGoodDetailTable(data) {
			data.manufacturer = data.manufacturerName;
			data.producerCountry = data.producingCountryName;
			vm.goodsDetailForOrderGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				dataSource: data,
				scrollable: false,
				toolbar: [
		                    {
		                        name: "actions",
		                        template: 
		                      	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
		                    '<i class="action-button excelQLK" ng-click="caller.exportExcelGrid()" aria-hidden="true"></i>'+
		                    '</div>'
		                    }
		                    ],
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
				columnMenu: false,
				columns: [
				{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#goodsDetailForOrderGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: "5%",
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
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hợp đồng",
			        field: 'contractCode',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Part Number",
			        field: 'partNumber',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Hãng sản xuất",
					 field: 'manufacturer',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Nước sản xuất",
					 field: 'producerCountry',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Vị trí",
					 field: 'cellCode',
			        width: "15%",
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
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					 pageSize:10,
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
			var grid = $("#creImReqMaGrid").data("kendoGrid");;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.getExcelTemplate = function(){
			if(vm.showFromProject == true || vm.showFromDirect == true){
				createImpReqManaService.downloadTemplate().then(function(d) {
					data = d.plain();
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				});
			}
		}
		vm.mouseUpEvent = function(){
			vm.validator.validate();
			if($("#orderStock").val()==""){
				$("#orderStock").focus();
				return;
			}
			if(vm.showFromProvider && (vm.order.shipmentCode==""||vm.order.shipmentCode==undefined)){
					$("#fromProviderCode").focus();
					return;
			}else if(vm.showBeforeWarranty && (vm.order.maintainOrderCode=="" ||vm.order.maintainOrderCode==undefined)){
					$("#beforeWarrantyCode").focus();
					return;
			}else if(vm.showAfterWarranty && (vm.order.maintainReportCode=="" ||vm.order.maintainReportCode==undefined)){
					$("#afterWarrantyCode").focus();
					return;
			}
			else if(vm.showFromConstruction && (vm.order.consRetrieveOrderCode==""||vm.order.consRetrieveOrderCode==undefined)){
					$("#fromConstructionCode").focus();
					return;
			}
			else if(vm.showFromUnit && (vm.order.deptRetrieveOrderCode==""||vm.order.deptRetrieveOrderCode==undefined)){
					$("#fromUnitCode").focus();
					return;
			}
			else if(vm.showFromBorrowedGoods && (vm.order.loanRetrieveOrderCode==""||vm.order.loanRetrieveOrderCode==undefined)){
					$("#fromBorrowedCode").focus();
					return;
			}
			else if(vm.showFromAlternativeStock && (vm.order.stockTransCode==""||vm.order.stockTransCode==undefined)){
					$("#fromAlternativeStockCode").focus();
					return;
			}
			else if(vm.showFromProject && (vm.order.projectCode==""||vm.order.projectCode==undefined)){
					$("#fromProjectCode").focus();
					return;
			}
			
			if($("#shipper").val()==""){
				$("#shipper").focus();
				return;
			}
		}
		vm.nextStep = function(){
			vm.validator.validate();
			if($("#orderStock").val()==""){
				$("#orderStock").focus();
				return;
			}
			if(vm.showFromProvider && (vm.order.shipmentCode==""||vm.order.shipmentCode==undefined)){
					$("#fromProviderCode").focus();
					return;
			}else if(vm.showBeforeWarranty && (vm.order.maintainOrderCode=="" ||vm.order.maintainOrderCode==undefined)){
					$("#beforeWarrantyCode").focus();
					return;
			}else if(vm.showAfterWarranty && (vm.order.maintainReportCode=="" ||vm.order.maintainReportCode==undefined)){
					$("#afterWarrantyCode").focus();
					return;
			}
			else if(vm.showFromConstruction && (vm.order.consRetrieveOrderCode==""||vm.order.consRetrieveOrderCode==undefined)){
					$("#fromConstructionCode").focus();
					return;
			}
			else if(vm.showFromUnit && (vm.order.deptRetrieveOrderCode==""||vm.order.deptRetrieveOrderCode==undefined)){
					$("#fromUnitCode").focus();
					return;
			}
			else if(vm.showFromBorrowedGoods && (vm.order.loanRetrieveOrderCode==""||vm.order.loanRetrieveOrderCode==undefined)){
					$("#fromBorrowedCode").focus();
					return;
			}
			else if(vm.showFromAlternativeStock && (vm.order.stockTransCode==""||vm.order.stockTransCode==undefined)){
					$("#fromAlternativeStockCode").focus();
					return;
			}
			else if(vm.showFromProject && (vm.order.projectCode==""||vm.order.projectCode==undefined)){
					$("#fromProjectCode").focus();
					return;
			}
			
			if($("#shipper").val()==""){
				$("#shipper").focus();
				return;
			}
			
			trimSpace();
				if(!vm.validator.validate()){
					$("#creImpReqBCIconOne").removeClass("acceptQLK postion-icon");
					$("#creImpReqBCIconOne").addClass("declineQLK postion-icon");
					$("#creImpReqBCIconThree").removeClass("acceptQLK postion-icon");
					$("#creImpReqBCIconThree").addClass("declineQLK postion-icon");
					return;
				}
				$("#creImpReqBCIconOne").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconOne").addClass("acceptQLK postion-icon");
				$("#creImpReqBCIconThree").removeClass("declineQLK postion-icon");
				$("#creImpReqBCIconThree").addClass("acceptQLK postion-icon");
			if(vm.showFromProvider && vm.order.shipmentCode!=null){
				if($("#fromProviderCode").val()==""){
					$("#fromProviderCode").focus();
				}
				createImpReqManaService.getShipmentGoodsInfoByCode(vm.order.shipmentCode).then(function(d) {
				vm.finallyGood = [];
				vm.dataInsert = d.plain();
				//vm.dataInsert.goodsStateName = "Bình thường";
				if(vm.dataInsert.length==0){
						toastr.warning("Không có hàng hóa với mã lô hàng này");
						$("#fromProviderCode").focus();
						return;
					}
				for(var i=0;i<vm.dataInsert.length;i++){
						vm.dataInsert[i].goodsStateName = "Bình thường";
						if(vm.finallyGood.length === 0){
						vm.finallyGood.push(vm.dataInsert[i]);
						}else{
						for(var j=0;j<vm.finallyGood.length;j++){
						if(vm.dataInsert[i].goodsCode == vm.finallyGood[j].goodsCode){
						vm.finallyGood[j].amount = vm.dataInsert[i].amount + vm.finallyGood[j].amount;
						break;
						}else {
						vm.finallyGood.push(vm.dataInsert[i]);
						break;
						}
						}
						}
					}
					
					refreshGrid(vm.finallyGood);
					vm.goods = vm.finallyGood;
					for(var i=0;i<vm.goods.length;i++){
						vm.goods[i].shipmentGoodsId = vm.goods[i].id;

						createImpReqManaService.getGoodsDetailShipment(vm.goods[i]).then(function(data){
							for(var j=0;j<data.plain().length;j++){
								vm.goodsDetail.push(data.plain()[j]);
							}
				            }, function(errResponse) {
							});
					}
					vm.showStepOne = false;
					vm.disableImport = true;
					document.getElementById("orderGoods").readOnly = true;
					$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: false});
					vm.hideDeleteRow = true;
				});
			}else if(vm.showBeforeWarranty && vm.order.maintainOrderCode!=null){
				if($("#beforeWarrantyCode").val()==""){
					$("#beforeWarrantyCode").focus();
				}
				createImpReqManaService.getGoodsInfoBeforeWarrantyByCode(vm.order.maintainOrderCode).then(function(d) {
					if(d.plain().length==0){
						toastr.warning("Không có hàng hóa với mã yêu cầu BHSC này");
						$("#beforeWarrantyCode").focus();
						return;
					}
					refreshGrid(d.plain());
					vm.goods = d.plain();
					for(var i=0;i<vm.goods.length;i++){
						createImpReqManaService.getGoodsDetail(vm.goods[i]).then(function(data){
							for(var j=0;j<data.plain().length;j++){
								vm.goodsDetail.push(data.plain()[j]);
							}
				            }, function(errResponse) {
							});
					}
					vm.showStepOne = false;
					vm.disableImport = true;
					document.getElementById("orderGoods").readOnly = true;
					$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: false});
					vm.hideDeleteRow = true;
				});
			}else if(vm.showAfterWarranty && vm.order.maintainReportCode!=null){
				if($("#afterWarrantyCode").val()==""){
					$("#afterWarrantyCode").focus();
				}
				createImpReqManaService.getGoodsInfoAfterWarrantyByCode(vm.order.maintainReportCode).then(function(d) {
					if(d.plain().length==0){
						toastr.warning("Không có hàng hóa với mã BBBG BHSC này");
						$("#afterWarrantyCode").focus();
						return;
					}
					refreshGrid(d.plain());
					vm.goods = d.plain();
					for(var i=0;i<vm.goods.length;i++){
						createImpReqManaService.getGoodsDetail(vm.goods[i]).then(function(data){
							for(var j=0;j<data.plain().length;j++){
								vm.goodsDetail.push(data.plain()[j]);
							}
				            }, function(errResponse) {
							});
					}
					vm.showStepOne = false;
					vm.disableImport = true;
					document.getElementById("orderGoods").readOnly = true;
					$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: false});
					vm.hideDeleteRow = true;
				});
			}
			else if(vm.showFromConstruction && vm.order.consRetrieveOrderCode!=null){
				if($("#fromConstructionCode").val()==""){
					$("#fromConstructionCode").focus();
				}
				createImpReqManaService.getGoodsInfoFromConstructionByCode(vm.order.consRetrieveOrderCode).then(function(d) {
					if(d.plain().length==0){
						toastr.warning("Không có hàng hóa với mã yêu cầu thu hồi này");
						$("#fromConstructionCode").focus();
						return;
					}
					refreshGrid(d.plain());
					vm.goods = d.plain();
					for(var i=0;i<vm.goods.length;i++){
						createImpReqManaService.getGoodsDetail(vm.goods[i]).then(function(data){
							for(var j=0;j<data.plain().length;j++){
								vm.goodsDetail.push(data.plain()[j]);
							}
				            }, function(errResponse) {
							});
					}
					vm.showStepOne = false;
					vm.disableImport = true;
					document.getElementById("orderGoods").readOnly = true;
					$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: false});
					vm.hideDeleteRow = true;
				});
			}
			else if(vm.showFromUnit && vm.order.deptRetrieveOrderCode!=null){
				if($("#fromUnitCode").val()==""){
					$("#fromUnitCode").focus();
				}
				createImpReqManaService.getGoodsInfoFromDepartmentByCode(vm.order.deptRetrieveOrderCode).then(function(d) {
					if(d.plain().length==0){
						toastr.warning("Không có hàng hóa với mã yêu cầu thu hồi này");
						$("#fromUnitCode").focus();
						return;
					}
					refreshGrid(d.plain());
					vm.goods = d.plain();
					for(var i=0;i<vm.goods.length;i++){
						createImpReqManaService.getGoodsDetail(vm.goods[i]).then(function(data){
							for(var j=0;j<data.plain().length;j++){
								vm.goodsDetail.push(data.plain()[j]);
							}
				            }, function(errResponse) {
							});
					}
					vm.showStepOne = false;
					vm.disableImport = true;
					document.getElementById("orderGoods").readOnly = true;
					$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: false});
					vm.hideDeleteRow = true;
				});
			}
			else if(vm.showFromBorrowedGoods && vm.order.loanRetrieveOrderCode!=null){
				if($("#fromBorrowedCode").val()==""){
					$("#fromBorrowedCode").focus();
				}
				createImpReqManaService.getGoodsInfoFromLoanByCode(vm.order.loanRetrieveOrderCode).then(function(d) {
					if(d.plain().length==0){
						toastr.warning("Không có hàng hóa với mã yêu cầu thu hồi này");
						$("#fromBorrowedCode").focus();
						return;
					}
					refreshGrid(d.plain());
					vm.goods = d.plain();
					for(var i=0;i<vm.goods.length;i++){
						createImpReqManaService.getGoodsDetail(vm.goods[i]).then(function(data){
							for(var j=0;j<data.plain().length;j++){
								vm.goodsDetail.push(data.plain()[j]);
							}
				            }, function(errResponse) {
							});
					}
					vm.showStepOne = false;
					vm.disableImport = true;
					document.getElementById("orderGoods").readOnly = true;
					$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: false});
					vm.hideDeleteRow = true;
				});
			}
			else if(vm.showFromAlternativeStock && vm.order.stockTransCode!=null){
				if($("#fromAlternativeStockCode").val()==""){
					$("#fromAlternativeStockCode").focus();
				}
				createImpReqManaService.getStockTransGoodsInfoByCode(vm.order.stockTransCode).then(function(d) {
					if(d.plain().length==0){
						toastr.warning("Không có hàng hóa với mã phiếu xuất này");
						$("#fromAlternativeStockCode").focus();
						return;
					}
					refreshGrid(d.plain());
					vm.goods = d.plain();
					for(var i=0;i<vm.goods.length;i++){
						vm.goods[i].stockTransDetailId = vm.goods[i].id;
						createImpReqManaService.getGoodsDetailStockTrans(vm.goods[i]).then(function(data){
							for(var j=0;j<data.plain().length;j++){
								vm.goodsDetail.push(data.plain()[j]);
							}
				            }, function(errResponse) {
							});
					}
					vm.showStepOne = false;
					vm.disableImport = true;
					document.getElementById("orderGoods").readOnly = true;
					$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: false});
					vm.hideDeleteRow = true;
				});
			}
			if(vm.showFromProject == true || vm.showFromDirect == true ){
				refreshGrid([]);
				vm.showStepOne = false;
				vm.disableImport = false;
				document.getElementById("orderGoods").readOnly = false;
				$("#creImReqMaGrid").data("kendoGrid").setOptions({editable: true});
				vm.hideDeleteRow = false;
				
				if(vm.order.orderId != null){
					createImpReqManaService.doSearchGoodsForImportReq(vm.order).then(function(d) {
						refreshGrid(d.data);
					});
				}
			}
			
			
			/*if(vm.order.orderId != null){
				createImpReqManaService.doSearchGoodsForImportReq(vm.order).then(function(d) {
					refreshGrid(d.data);
				});
			}*/
			
		}
		
		
		
		vm.prevStep = function(){
			vm.showStepOne = true;
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
		
		vm.exportExcelGrid = function(){
			var dataForExport = [];
			var dataFromTable = [];
			dataFromTable = $("#goodsDetailForOrderGrid").data("kendoGrid").dataSource.view();
			for(var i=0;i<dataFromTable.length;i++){
				dataForExport.push(dataFromTable[i]);
			}
			CommonService.exportFile(vm.goodsDetailForOrderGrid,dataForExport,[],[],"ChiTietHangHoa");
		}
		
		vm.prepareDataforSaving = function(){
			
			if(vm.showFromProject == true || vm.showFromDirect == true ){
				vm.order.listOrderGoodsDTO = [];
				var dataGoodFromGrid = $('#creImReqMaGrid').data("kendoGrid").dataSource.data();
				for(var i = 0; i<dataGoodFromGrid.length;i++){
					dataGoodFromGrid[i].goodsIsSerial = "0";
					if(dataGoodFromGrid[i].goodsStateName.toUpperCase() == "Bình thường".toUpperCase()){
						dataGoodFromGrid[i].goodsState == "1";
					}else{
						dataGoodFromGrid[i].goodsState == "0";
					}
					vm.dataDetail = {};
					listOrderGoodsDetailDTO = [];
					dataGoodFromGrid[i].listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
					vm.dataDetail.manufacturerId = dataGoodFromGrid[i].manufacturerId;
					vm.dataDetail.manufacturerName = dataGoodFromGrid[i].manufacturerName;
					vm.dataDetail.producingCountryId = dataGoodFromGrid[i].producingCountryId;
					vm.dataDetail.producingCountryName = dataGoodFromGrid[i].producingCountryName;
					vm.dataDetail.partNumber = dataGoodFromGrid[i].partNumber;
					vm.dataDetail.price = dataGoodFromGrid[i].price;
					vm.dataDetail.serial = dataGoodFromGrid[i].serial;
					dataGoodFromGrid[i].listOrderGoodsDetailDTO.push(vm.dataDetail);
					vm.order.listOrderGoodsDTO.push(dataGoodFromGrid[i]);
					
				}
				/*for(var i = 0; i<vm.order.listOrderGoodsDTO.length;i++){
					vm.dataDetail = {};
					listOrderGoodsDetailDTO = [];
					vm.order.listOrderGoodsDTO[i]={};
					vm.order.listOrderGoodsDTO[i].listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
					vm.dataDetail.manufacturerId = dataGoodFromGrid[i].manufacturerId;
					vm.dataDetail.manufacturerName = dataGoodFromGrid[i].manufacturerName;
					vm.dataDetail.producingCountryId = dataGoodFromGrid[i].producingCountryId;
					vm.dataDetail.producingCountryName = dataGoodFromGrid[i].producingCountryName;
					vm.dataDetail.partNumber = dataGoodFromGrid[i].partNumber;
					vm.dataDetail.price = dataGoodFromGrid[i].price;
					vm.order.listOrderGoodsDTO[i].listOrderGoodsDetailDTO.push(vm.dataDetail);
				}*/
			}else{
				vm.order.listOrderGoodsDTO = [];
				//vm.order.listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
				for(var i=0;i<vm.goods.length;i++){
					vm.order.listOrderGoodsDTO[i]={};
					vm.order.listOrderGoodsDTO[i].goodsId = vm.goods[i].goodsId;
					vm.order.listOrderGoodsDTO[i].goodsCode = vm.goods[i].goodsCode;
					vm.order.listOrderGoodsDTO[i].goodsName = vm.goods[i].goodsName;
					vm.order.listOrderGoodsDTO[i].amount = vm.goods[i].amount;
					vm.order.listOrderGoodsDTO[i].goodsUnitId = vm.goods[i].goodsUnitId;
					vm.order.listOrderGoodsDTO[i].goodsUnitName = vm.goods[i].goodsUnitName;
					vm.order.listOrderGoodsDTO[i].goodsStateName = vm.goods[i].goodsStateName;
					if(!vm.goods[i].goodsStateName){
						vm.order.listOrderGoodsDTO[i].goodsStateName="Bình thường";
					}
					if(vm.order.listOrderGoodsDTO[i].goodsStateName.toUpperCase() == "Bình thường".toUpperCase()){
						vm.order.listOrderGoodsDTO[i].goodsState == "1";
					}else{
						vm.order.listOrderGoodsDTO[i].goodsState == "0";
					}
						

					for(var j=0;j<vm.goodsDetail.length;j++){
						if(vm.goods[i].goodsId == vm.goodsDetail[j].goodsId){
								vm.dataDetail = {};
								listOrderGoodsDetailDTO = [];
								vm.order.listOrderGoodsDTO[i].listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
								vm.dataDetail.serial = vm.goodsDetail[j].serial;
								vm.dataDetail.manufacturerId = vm.goodsDetail[j].manufacturerId;
								vm.dataDetail.manufacturerName = vm.goodsDetail[j].manufacturer;
								vm.dataDetail.producingCountryId = vm.goodsDetail[j].producingCountryId;
								vm.dataDetail.producingCountryName = vm.goodsDetail[j].producerCountry;
								vm.dataDetail.partNumber = vm.goodsDetail[j].partNumber;
								vm.dataDetail.price = vm.goodsDetail[j].price;
								vm.order.listOrderGoodsDTO[i].listOrderGoodsDetailDTO.push(vm.dataDetail);
								if(vm.dataDetail.serial!=null){
									vm.order.listOrderGoodsDTO[i].goodsIsSerial = "1";
								}else{
									vm.order.listOrderGoodsDTO[i].goodsIsSerial = "0";
								}
						}
					}
					
				}
				for(var i=0;i<vm.order.listOrderGoodsDTO.length;i++){
					for(var k=i+1;k<vm.order.listOrderGoodsDTO.length;k++){
						if(vm.order.listOrderGoodsDTO[i].goodsId == vm.order.listOrderGoodsDTO[k].goodsId){
							vm.order.listOrderGoodsDTO[i].amount = vm.order.listOrderGoodsDTO[i].amount + vm.order.listOrderGoodsDTO[k].amount;
							vm.order.listOrderGoodsDTO.splice(k, 1);
						}
					}
				}
			}
			
		}
		
		vm.saveImportReq = function(){
			var dataGoodCheckFromGrid = $('#creImReqMaGrid').data("kendoGrid").dataSource.data();
			var grid = $("#creImReqMaGrid").data("kendoGrid");
			for(var i=0;i<dataGoodCheckFromGrid.length;i++){
				if(dataGoodCheckFromGrid[i].amount == null || dataGoodCheckFromGrid[i].amount == 0){
					toastr.warning("Hàng hóa "+dataGoodCheckFromGrid[i].goodsCode+" chưa có số lượng");
					grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(4));
					return;
					break;
				}
			}
			if(dataGoodCheckFromGrid.length==0){
				toastr.warning("Chưa có hàng hóa");
				return;
			}
			vm.prepareDataforSaving();
			clearFilled();
			if(vm.order.orderId == null){
				//vm.prepareDataforSaving();
				var purchaseOrderDate = kendo.parseDate(vm.order.purchaseOrderDate, "dd/MM/yyyy");
				var shipDate = kendo.parseDate(vm.order.shipDate, "dd/MM/yyyy");
				var cerDate = kendo.parseDate(vm.order.cerDate, "dd/MM/yyyy");
				vm.order.purchaseOrderDate = purchaseOrderDate;
				vm.order.shipDate = shipDate;
				vm.order.cerDate = cerDate;
				
				createImpReqManaService.saveImportReq(vm.order).then(function(d){
					if(d.error){
						toastr.error(d.error);
						return;
					}
	        		toastr.success("Thêm mới thành công!");
	        		vm.order = {};
	        		$rootScope.$broadcast("backToImport", vm.order);
	   			 impReqManaService.setData(vm.order);
	        		CommonService.goTo('IM_REQ_MANAGE');
	        		CommonService.closeTab('CREATE_IM_REQ_MANAGE');
	            }, function(errResponse) {
	            	toastr.error("Thêm mới thất bại");
				});
			}else{
				if(vm.showFromProject == true || vm.showFromDirect == true){
					vm.dataUpdate = $("#creImReqMaGrid").data().kendoGrid.dataSource.view();
					vm.order.listOrderGoodsDTO = vm.dataUpdate;
				}
				createImpReqManaService.updateImportReq(vm.order).then(function(mess){
					if(mess.error){
						toastr.error(mess.error);
					}
	        		toastr.success("Cập nhật thành công!");
	        		vm.flag = true;
	   			 	$rootScope.$broadcast("backToImport", vm.order);
	   			 	CommonService.goTo('IM_REQ_MANAGE');
	        		CommonService.closeTab('UPDATE_IM_REQ_MANAGE');
	            }, function(errResponse) {
	            	if (errResponse.status == 406) {
	                    toastr.error("Người dùng hiện tại không có quyền sửa bản ghi này!");
	                } else {
	            	toastr.error("Cập nhật thất bại");
	                }
				});
			}
			
		}
		
		vm.showGoodsDetail = function(dataItem){
			var teamplateUrl="wms/createImportRequirementManagement/goodDetailPopUp.html";
			 var title="Thông tin chi tiết hàng hóa";
			 var windowId="GOODS_DETAIL";
			 vm.detail = dataItem;
			 fillDataGoodDetailTable([]);
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%');
		}
		
		$scope.$on("Popup.open", function () {
			if(vm.showFromAlternativeStock){
				 vm.detail.stockTransDetailId = vm.detail.id;
				 if(vm.detail.stockTransDetailId !=null){
					 createImpReqManaService.getGoodsDetailStockTrans(vm.detail).then(function(d){
						 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
							if(grid){
								grid.dataSource.data(d.plain());
								grid.refresh();
							}
			            }, function(errResponse) {
						});
				 }else{
					 createImpReqManaService.doSearchGoodsDetailForImportReq(vm.detail).then(function(d){
						 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
							if(grid){
								grid.dataSource.data(d.data);
								grid.refresh();
							}
			            }, function(errResponse) {
						});
				 }
				 
			 }else if(vm.showFromProvider){
				 vm.detail.shipmentGoodsId = vm.detail.id;
				 if(vm.detail.shipmentGoodsId !=null){
					 createImpReqManaService.getGoodsDetailShipment(vm.detail).then(function(d){
						 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
							if(grid){
								grid.dataSource.data(d.plain());
								grid.refresh();
							}
			            }, function(errResponse) {
						}); 
				 }else{
					 createImpReqManaService.doSearchGoodsDetailForImportReq(vm.detail).then(function(d){
						 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
							if(grid){
								grid.dataSource.data(d.data);
								grid.refresh();
							}
			            }, function(errResponse) {
						});
				 }
				 
			 }else if(vm.showFromProject || vm.showFromDirect){
				 if(vm.detail.orderId != null){
					 createImpReqManaService.doSearchGoodsDetailForImportReq(vm.detail).then(function(d){
						 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
							if(grid){
								for(var i =0;i<d.data.length;i++){
									d.data[i].manufacturer = d.data[i].manufacturerName;
									d.data[i].producerCountry = d.data[i].producingCountryName;
								}
								grid.dataSource.data(d.data);
								grid.refresh();
							}
			            }, function(errResponse) {
						});
					 
				 }else{
					 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
						if(grid){
						vm.detailList = [];
							vm.detail.manufacturer = vm.detail.manufacturerName;
							vm.detail.producerCountry = vm.detail.producingCountryName;
							vm.detailList.push(vm.detail);
							grid.dataSource.data(vm.detailList);
							grid.refresh();
						} 
				 }
				 
			 }else{
				 if(vm.detail.orderId != null){
					 createImpReqManaService.doSearchGoodsDetailForImportReq(vm.detail).then(function(d){
						 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
							if(grid){
								for(var i =0;i<d.data.length;i++){
									d.data[i].manufacturer = d.data[i].manufacturerName;
									d.data[i].producerCountry = d.data[i].producingCountryName;
								}
								grid.dataSource.data(d.data);
								grid.refresh();
							}
			            }, function(errResponse) {
						});
					 
				 }else{
					 createImpReqManaService.getGoodsDetail(vm.detail).then(function(d){
						 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
							if(grid){
								grid.dataSource.data(d.plain());
								grid.refresh();
							}
			            }, function(errResponse) {
						});
				 }
			 }
        });
		
		vm.closeErrImportPopUp = function(){
			CommonService.closePopup1();
		}
		vm.exportExcelErr = function(){
			createImpReqManaService.downloadErrorExcel(vm.objectErr).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		}
		
		
		
			vm.checkbox = checkbox;
			function checkbox(){
			vm.showFromProvider = false;
			vm.showBeforeWarranty = false;
			vm.showAfterWarranty = false;
			vm.showFromConstruction = false;
			vm.showFromUnit = false;
			vm.showFromBorrowedGoods = false;
			vm.showFromAlternativeStock = false;
			vm.showFromProject = false;
			vm.showFromDirect = false;
			
			vm.order.shipmentCode = "filled";
			vm.order.maintainOrderCode = "filled";
			vm.order.maintainReportCode ="filled";
			vm.order.consRetrieveOrderCode ="filled";
			vm.order.deptRetrieveOrderCode = "filled";
			vm.order.loanRetrieveOrderCode = "filled";
			vm.order.stockTransCode ="filled";
			vm.order.projectCode ="filled";
			if (document.getElementById("fromProvider").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromProvider = true;
		    	vm.order.bussinessType = vm.businessTypes[0].code;
		    	vm.order.shipmentCode = "";
		    }
			if (document.getElementById("beforeWarranty").checked == true) {
		        //Checkbox has been checked
		    	vm.showBeforeWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[1].code;
		    	vm.order.maintainOrderCode = "";
		    }
			if (document.getElementById("afterWarranty").checked == true) {
		        //Checkbox has been checked
		    	vm.showAfterWarranty = true;
		    	vm.order.bussinessType = vm.businessTypes[2].code;
		    	vm.order.maintainReportCode ="";
		    }
			if (document.getElementById("fromConstruction").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromConstruction = true;
		    	vm.order.bussinessType = vm.businessTypes[3].code;
		    	vm.order.consRetrieveOrderCode ="";
		    }
			if (document.getElementById("fromUnit").checked == true) {
			        //Checkbox has been checked
			    vm.showFromUnit = true;
			    vm.order.bussinessType = vm.businessTypes[4].code;
			    vm.order.deptRetrieveOrderCode = "";
			}
			if (document.getElementById("fromBorrowedGoods").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromBorrowedGoods = true;
		    	vm.order.bussinessType = vm.businessTypes[5].code;
		    	vm.order.loanRetrieveOrderCode = "";
		    }
			if (document.getElementById("fromAlternativeStock").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromAlternativeStock = true;
		    	vm.order.bussinessType = vm.businessTypes[6].code;
		    	vm.order.stockTransCode ="";
		    }
			if (document.getElementById("fromProject").checked == true) {
		        //Checkbox has been checked
		    	vm.showFromProject = true;
		    	vm.order.bussinessType = vm.businessTypes[7].code;
		    	vm.order.projectCode ="";
		    }
			if (document.getElementById("direct").checked == true) {
		        //Checkbox has been checked
				vm.showFromDirect = true;
		    	vm.order.bussinessType = vm.businessTypes[8].code;
		    }
			
		}
			function clearFilled(){
				if (vm.order.bussinessType == vm.businessTypes[0].code ) {
			        //Checkbox has been checked
					vm.order.maintainOrderCode = "";
					vm.order.maintainReportCode ="";
					vm.order.consRetrieveOrderCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
					vm.order.projectCode ="";
			    }
				if (vm.order.bussinessType == vm.businessTypes[1].code) {
			        //Checkbox has been checked
			    	vm.order.shipmentCode = "";
					vm.order.maintainReportCode ="";
					vm.order.consRetrieveOrderCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
					vm.order.projectCode ="";
			    }
				if (vm.order.bussinessType == vm.businessTypes[2].code ) {
			        //Checkbox has been checked
			    	vm.order.shipmentCode = "";
					vm.order.maintainOrderCode = "";
					vm.order.consRetrieveOrderCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
					vm.order.projectCode ="";
			    }
				if (vm.order.bussinessType == vm.businessTypes[3].code ) {
			        //Checkbox has been checked
			    	vm.order.shipmentCode = "";
					vm.order.maintainOrderCode = "";
					vm.order.maintainReportCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
					vm.order.projectCode ="";
			    }
				if (vm.order.bussinessType == vm.businessTypes[4].code ) {
				        //Checkbox has been checked
				    vm.order.shipmentCode = "";
					vm.order.maintainOrderCode = "";
					vm.order.maintainReportCode ="";
					vm.order.consRetrieveOrderCode ="";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
					vm.order.projectCode ="";
				}
				if (vm.order.bussinessType == vm.businessTypes[5].code ) {
			        //Checkbox has been checked
			    	vm.order.shipmentCode = "";
					vm.order.maintainOrderCode = "";
					vm.order.maintainReportCode ="";
					vm.order.consRetrieveOrderCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
					vm.order.projectCode ="";
			    }
				if (vm.order.bussinessType == vm.businessTypes[6].code ) {
			        //Checkbox has been checked
			    	vm.order.shipmentCode = "";
					vm.order.maintainOrderCode = "";
					vm.order.maintainReportCode ="";
					vm.order.consRetrieveOrderCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.projectCode ="";
			    }
				if (vm.order.bussinessType == vm.businessTypes[7].code ) {
			        //Checkbox has been checked
			    	vm.order.shipmentCode = "";
					vm.order.maintainOrderCode = "";
					vm.order.maintainReportCode ="";
					vm.order.consRetrieveOrderCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
			    }
				if (vm.order.bussinessType == vm.businessTypes[8].code ) {
			        //Checkbox has been checked
					vm.order.shipmentCode = "";
					vm.order.maintainOrderCode = "";
					vm.order.maintainReportCode ="";
					vm.order.consRetrieveOrderCode ="";
					vm.order.deptRetrieveOrderCode = "";
					vm.order.loanRetrieveOrderCode = "";
					vm.order.stockTransCode ="";
					vm.order.projectCode ="";
			    }
			}
			
			function checkBoxAutoLoad(){
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
					vm.order.maintainOrderCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
					vm.order.projectCode ="filled";
			    }
				if (vm.order.bussinessType == vm.businessTypes[1].code) {
			        //Checkbox has been checked
			    	vm.showBeforeWarranty = true;
			    	$("#beforeWarranty").prop("checked", true);
			    	vm.order.shipmentCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
					vm.order.projectCode ="filled";
			    }
				if (vm.order.bussinessType == vm.businessTypes[2].code ) {
			        //Checkbox has been checked
			    	vm.showAfterWarranty = true;
			    	$("#afterWarranty").prop("checked", true);
			    	vm.order.shipmentCode = "filled";
					vm.order.maintainOrderCode = "filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
					vm.order.projectCode ="filled";
			    }
				if (vm.order.bussinessType == vm.businessTypes[3].code ) {
			        //Checkbox has been checked
			    	vm.showFromConstruction = true;
			    	$("#fromConstruction").prop("checked", true);
			    	vm.order.shipmentCode = "filled";
					vm.order.maintainOrderCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
					vm.order.projectCode ="filled";
			    }
				if (vm.order.bussinessType == vm.businessTypes[4].code ) {
				        //Checkbox has been checked
				    vm.showFromUnit = true;
				    $("#fromUnit").prop("checked", true);
				    vm.order.shipmentCode = "filled";
					vm.order.maintainOrderCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
					vm.order.projectCode ="filled";
				}
				if (vm.order.bussinessType == vm.businessTypes[5].code ) {
			        //Checkbox has been checked
			    	vm.showFromBorrowedGoods = true;
			    	$("#fromBorrowedGoods").prop("checked", true);
			    	vm.order.shipmentCode = "filled";
					vm.order.maintainOrderCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
					vm.order.projectCode ="filled";
			    }
				if (vm.order.bussinessType == vm.businessTypes[6].code ) {
			        //Checkbox has been checked
			    	vm.showFromAlternativeStock = true;
			    	$("#fromAlternativeStock").prop("checked", true);
			    	vm.order.shipmentCode = "filled";
					vm.order.maintainOrderCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.projectCode ="filled";
			    }
				if (vm.order.bussinessType == vm.businessTypes[7].code ) {
			        //Checkbox has been checked
			    	vm.showFromProject = true;
			    	$("#fromProject").prop("checked", true);
			    	vm.order.shipmentCode = "filled";
					vm.order.maintainOrderCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
			    }
				if (vm.order.bussinessType == vm.businessTypes[8].code ) {
			        //Checkbox has been checked
					vm.showFromDirect = true;
					$("#direct").prop("checked", true);
					vm.order.shipmentCode = "filled";
					vm.order.maintainOrderCode = "filled";
					vm.order.maintainReportCode ="filled";
					vm.order.consRetrieveOrderCode ="filled";
					vm.order.deptRetrieveOrderCode = "filled";
					vm.order.loanRetrieveOrderCode = "filled";
					vm.order.stockTransCode ="filled";
					vm.order.projectCode ="filled";
			    }
			}
		
		vm.cancel=cancel;
		function cancel(){
		 confirm('Xác nhận hủy bỏ thao tác này', function () {
			 $rootScope.$broadcast("backToImport", vm.order);
			 impReqManaService.setData(vm.order);
				if(vm.order.orderId == null){
					CommonService.goTo('IM_REQ_MANAGE');
					CommonService.closeTab('CREATE_IM_REQ_MANAGE');
				}else{
					CommonService.goTo('IM_REQ_MANAGE');
					CommonService.closeTab('UPDATE_IM_REQ_MANAGE');
				}
			});
		}
		
		vm.removeRow = function(id){
			var dataRow = $('#creImReqMaGrid').data("kendoGrid").dataSource.getByUid(id);
            $('#creImReqMaGrid').data("kendoGrid").dataSource.remove(dataRow);
		}
		
		function statusDropDownEditor(container, options) {
			 $('<input required name="' + options.field + '"/>')
               .appendTo(container)
               .kendoDropDownList({
                   autoBind: false,
                   suggest: true,
                   dataSource: ["Bình thường","Hỏng"],
               });
       }
		//---------------------------------Code Pickers----------------------------------------//
		vm.fromProviderCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.SHIPMENT_URL+ "/searchListShipmentCode").post(vm.order.shipmentCode).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		vm.beforeWarrantyCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.OBJECT_REFERENCE_URL+ "/getGoodsInfoBeforeWarrantyCode").post(vm.order.maintainOrderCode).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		vm.afterWarrantyCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.OBJECT_REFERENCE_URL+ "/getGoodsInfoAfterWarrantyCode").post(vm.order.maintainReportCode).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		vm.fromConstructionCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.OBJECT_REFERENCE_URL+ "/getGoodsInfoFromConstructionCode").post(vm.order.consRetrieveOrderCode).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		vm.fromUnitCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.OBJECT_REFERENCE_URL+ "/getGoodsInfoFromDepartmentCode").post(vm.order.deptRetrieveOrderCode).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		vm.fromBorrowedCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.OBJECT_REFERENCE_URL+ "/getGoodsInfoFromLoanCode").post(vm.order.loanRetrieveOrderCode).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		vm.fromAlternativeStockCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.STOCK_TRANS_SERVICE_URL+ "/getGoodsInfoFromAlternativeStockCode").post(vm.order.stockTransCode).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	        };
		
		vm.fromProjectCodeOptions = {
	            dataSource: {
	                serverFiltering: true,
	                	transport: {
	                    	read: function(options) {
		                        return Restangular
		    					.all(RestEndpoint.I_PROJ_INVEST_PROJECT_URL+ "/getFromProjectCode").post(vm.order.projectCode).then(function(response){
		                            options.success(response);
		                            if(response.length==0){
		                            	$("#fromProjectCode").val("");
		                            }
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
	                    }
	            }
	       };
		
		//---------------------------------End Code Pickers----------------------------------------//
		
		// =================================Clear Textbox================================//
		vm.clearPurchaseOrderDate = function(){
			vm.order.purchaseOrderDate = "";
		}
		vm.clearCerDate = function(){
			vm.order.cerDate = "";
		}
		vm.clearShipperName = function(){
			vm.order.shipperName = "";
		}
		vm.clearShipDate = function(){
			vm.order.shipDate = "";
		}
		// =================================End Clear Textbox===============================//
		
		//==================================Upload import===================================//
		vm.submit=submit;
        function submit(){
        	
        	if($("#file")[0].files[0] == null){
        		toastr.warning("Bạn chưa chọn file để import");
        		return;
        	}
        	if($("#file")[0].files[0].name.split('.').pop() !='xls' && $("#file")[0].files[0].name.split('.').pop() !='xlsx' ){
        		toastr.warning("Sai định dạng file");
        		return;
        	}
	        var formData = new FormData();
			formData.append('multipartFile', $('#file')[0].files[0]); 
	     return   $.ajax({
	            url: Constant.BASE_SERVICE_URL + "orderServiceRest/order/importGoods?folder="+ vm.folder,
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success:function(data) {
	            	if(data.length == 1 && data[data.length - 1].lstErrorGoods.length != 0){
	            		vm.lstErrImport = data[data.length - 1].lstErrorGoods;
	            		vm.objectErr = data[data.length - 1];
	            		var teamplateUrl="wms/createImportRequirementManagement/importResultPopUp.html";
	       			 var title="Kết quả Import";
	       			 var windowId="ERR_IMPORT";
	       			
	       			 CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'80%','80%');
	       			fillDataImportErrTable(vm.lstErrImport);
	            	}else if(data.length == 1 && data[data.length - 1].lstErrorGoods.length == 0){
	            		toastr.error("Không có dữ liệu để import");
	            		return;
	            	}else if(data.length > 1 && data[data.length - 1].lstErrorGoods.length != 0){
	            		vm.lstErrImport = data[data.length - 1].lstErrorGoods;
	            		vm.objectErr = data[data.length - 1];
	            		var teamplateUrl="wms/createImportRequirementManagement/importResultPopUp.html";
	       			 var title="Kết quả Import";
	       			 var windowId="ERR_IMPORT";
	       			
	       			 CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'80%','80%');
	       			fillDataImportErrTable(vm.lstErrImport); 
	            	}else
	            	{
	            		toastr.success("Import thành công!");
	            		data.splice(data.length - 1, 1);
	            		vm.dataRemain = $("#creImReqMaGrid").data().kendoGrid.dataSource.view();
	            		vm.countRemain = vm.dataRemain.length;
		            	var grid = $("#creImReqMaGrid").data("kendoGrid");
		            	for(var i = 0; i<data.length;i++){
		            		data[i].id = i+1;
		            		data[i].goodsCode = data[i].code;
		            		data[i].goodsName = data[i].name;
		            		if(data[i].status == "1"){
		            			data[i].goodsStateName = "Bình thường";
		            		}else if(data[i].status == "2"){
		            			data[i].goodsStateName = "Hỏng";
		            		}
		            		
		            		/*if(vm.dataRemain.length == 0){
		            			grid.dataSource.add(data[i]);
		            		}else{
		            			for(var j = 0; j<vm.countRemain;j++){
			            			if(vm.dataRemain[j].goodsName == data[i].goodsName && vm.dataRemain[j].goodsCode == data[i].goodsCode && vm.dataRemain[j].goodsStateName == data[i].goodsStateName){
			            				vm.dataRemain[j].amount = Number(vm.dataRemain[j].amount) + Number(data[i].amount);
			            				$("#creImReqMaGrid").data("kendoGrid").dataSource.data(vm.dataRemain);
				            			$("#creImReqMaGrid").data("kendoGrid").refresh();
				            			data[i].used = 1;
					            		break;
			            			}else{
			            				data[i].used = 0;
			            			}
			            		}
		            			
		            		}*/
		            	}
		            	$("#creImReqMaGrid").data("kendoGrid").dataSource.data(data);
            			$("#creImReqMaGrid").data("kendoGrid").refresh();
		            	/*for(var k = 0; k<data.length;k++){
        					if(data[k].used== 0){
        						grid.dataSource.add(data[k]);
        					}
        				}*/
	            	}
	                
	            }
	        });
	      
        
        }
        
        //================================End Upload Import=====================================//
        
      //Close popup
    	$(document).on("click",".k-overlay",function(){
    		 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    	});
    //
        //
    	$(document).keyup(function (e) {
    	    var code = (e.keyCode ? e.keyCode : e.which);
    	    if (code == 9 && $('#cancelImprt:focus').length) {
    	    	 $('#cancelImprt').addClass('focusbutton');
    	    }
    	    else{
    	    	 $('#cancelImprt').removeClass('focusbutton');
    	    }
    	   if (code == 9 && $('#nextStep:focus').length) {
    	    	 $('#nextStep').addClass('focusbutton');
    	    }
    	    else{
    	    	$('#nextStep').removeClass('focusbutton');
    	    }
    	   if (code == 9 && $('#prevStep:focus').length) {
    	    	 $('#prevStep').addClass('focusbutton');
    	    }
    	    else{
    	    	$('#prevStep').removeClass('focusbutton');
    	    }
    	    if (code == 9 && $('#saveImprt:focus').length) {
    	    	 $('#saveImprt').addClass('focusbutton');
    	    }
    	    else{
    	    	$('#saveImprt').removeClass('focusbutton');
    	    }
    	    if (code == 9 && $('#cancelImprt1:focus').length) {
   	    	 $('#cancelImprt1').addClass('focusbutton');
   	    }
   	    else{
   	    	$('#cancelImprt1').removeClass('focusbutton');
   	    }
    	    
    	});
    	//
        
	}
})();
