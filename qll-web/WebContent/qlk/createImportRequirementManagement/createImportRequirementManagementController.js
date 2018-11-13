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
		
		vm.showFromProvider = true;
		vm.showBeforeWarranty = false;
		vm.showAfterWarranty = false;
		vm.showFromConstruction = false;
		vm.showFromUnit = false;
		vm.showFromBorrowedGoods = false;
		vm.showFromAlternativeStock = false;
		vm.showFromProject = false;
		vm.showFromDirect = false;
		
		vm.disableInput = false;
		vm.disableImport = false;
		
		vm.order={};
		if(impReqManaService.getData()){
			vm.order = impReqManaService.getData();
		}
		$scope.$on("importOrderDetail", function (event, item) {
        	if(item != undefined){
        		vm.order = item;
        	}else{
        		console.error("không nhận được dữ liệu!");
        	}
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
		vm.appParams.pageSize = 20;
		
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
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		
		createImpReqManaService.getForOrderCheckboxes(vm.appParams).then(function(d) {
			vm.businessTypes = d.data;
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

		});
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data,
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
						style: "text-align:center;"
					},
				},  {
					title: "Chi tiết hàng hóa",
					field:'detail',
					template: dataItem => '<a class="#=goodsId#" href="javascript:void(0);" ng-click=vm.showGoodsDetail(dataItem)>Chi tiết</a>',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Chọn",
			        template:
			        	'<div class="text-center #=id#"">'+
						'<a type="button"'+
						'class="#=id# icon_table"  uib-tooltip="Sửa" translate'+
							' ng-click=vm.update(#=id#)>'+
							'<i class="fa fa-pencil" aria-hidden="true"></i>'+
						'</a>'+
						'<a type="button"'+
						'class="#=id# icon_table"  uib-tooltip="Xóa" translate'+
							' ng-click=vm.openRemovePopup(#=id#)>'+
						'<i class="fa fa-trash" aria-hidden="true"></i>	'+
						'</a>'
						+'</div>',
			        width: 150,
			        field:"stt"
				}
				,]
			});
		}
		
		function fillDataGoodDetailTable(data) {
			vm.goodsDetailForOrderGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data,
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
			        template: dataItem => $("#goodsDetailForOrderGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
					title: "Part Number",
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
					 field: 'manufacturer',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Nước sản xuất",
					 field: 'producerCountry',
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
		
		function refreshGrid(d) {
			var grid = $("#creImReqMaGrid").data("kendoGrid");;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		vm.nextStep = function(){
			
			if(vm.showFromProvider && vm.order.shipmentCode!=null){
				createImpReqManaService.getShipmentGoodsInfoByCode(vm.order.shipmentCode).then(function(d) {
					refreshGrid(d.plain());
					vm.goods = d.plain();
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
					vm.disableInput = true;
					vm.disableImport = true;
				});
			}else if(vm.showBeforeWarranty && vm.order.maintainOrderCode!=null){
				createImpReqManaService.getGoodsInfoBeforeWarrantyByCode(vm.order.maintainOrderCode).then(function(d) {
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
					vm.disableInput = true;
					vm.disableImport = true;
				});
			}else if(vm.showAfterWarranty && vm.order.maintainReportCode!=null){
				createImpReqManaService.getGoodsInfoAfterWarrantyByCode(vm.order.maintainReportCode).then(function(d) {
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
					vm.disableInput = true;
					vm.disableImport = true;
				});
			}
			else if(vm.showFromConstruction && vm.order.consRetrieveOrderCode!=null){
				createImpReqManaService.getGoodsInfoFromConstructionByCode(vm.order.consRetrieveOrderCode).then(function(d) {
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
					vm.disableInput = true;
					vm.disableImport = true;
				});
			}
			else if(vm.showFromUnit && vm.order.deptRetrieveOrderCode!=null){
				createImpReqManaService.getGoodsInfoFromDepartmentByCode(vm.order.deptRetrieveOrderCode).then(function(d) {
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
					vm.disableInput = true;
					vm.disableImport = true;
				});
			}
			else if(vm.showFromBorrowedGoods && vm.order.loanRetrieveOrderCode!=null){
				createImpReqManaService.getGoodsInfoFromLoanByCode(vm.order.loanRetrieveOrderCode).then(function(d) {
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
					vm.disableInput = true;
					vm.disableImport = true;
				});
			}
			else if(vm.showFromAlternativeStock && vm.order.stockTransCode!=null){
				createImpReqManaService.getStockTransGoodsInfoByCode(vm.order.stockTransCode).then(function(d) {
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
					vm.disableInput = true;
					vm.disableImport = true;
				});
			}
			if(vm.showFromProject == true || vm.showFromDirect == true ){
				refreshGrid([]);
				vm.showStepOne = false;
				vm.disableInput = false;
				vm.disableImport = false;
			}
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
		
		vm.prepareDataforSaving = function(){
			vm.order.listOrderGoodsDTO = [];
			//vm.order.listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
			for(var i=0;i<vm.goods.length;i++){
				vm.order.listOrderGoodsDTO[i]={};
				vm.order.listOrderGoodsDTO[i].goodsId = vm.goods[i].goodsId;
				vm.order.listOrderGoodsDTO[i].goodsCode = vm.goods[i].goodsCode;
				vm.order.listOrderGoodsDTO[i].goodsName = vm.goods[i].goodsName;
				vm.order.listOrderGoodsDTO[i].amount = vm.goods[i].amount;

				for(var j=0;j<vm.goodsDetail.length;j++){
					if(vm.goods[i].goodsId == vm.goodsDetail[j].goodsId){
							vm.dataDetail = {};
							vm.order.listOrderGoodsDTO[i].listOrderGoodsDetailDTO = listOrderGoodsDetailDTO;
							vm.dataDetail.serial = vm.goodsDetail[j].serial;
							vm.dataDetail.manufacturerId = vm.goodsDetail[j].manufacturerId;
							vm.dataDetail.manufacturerName = vm.goodsDetail[j].manufacturerName;
							vm.dataDetail.producingCountryId = vm.goodsDetail[j].producingCountryId;
							vm.dataDetail.producingCountryName = vm.goodsDetail[j].producingCountryName;
							vm.dataDetail.partNumber = vm.goodsDetail[j].partNumber;
							vm.order.listOrderGoodsDTO[i].listOrderGoodsDetailDTO[j] = vm.dataDetail;
					}else{
					break;
					}
				}
			}
		}
		
		vm.saveImportReq = function(){
			if(vm.order.orderId == null){
				vm.prepareDataforSaving();
				vm.order.purchaseOrderDate = kendo.parseDate(vm.order.purchaseOrderDate, "yyyy-MM-dd");
				vm.order.shipDate = kendo.parseDate(vm.order.shipDate, "yyyy-MM-dd");
				vm.order.cerDate = kendo.parseDate(vm.order.shipDate, "yyyy-MM-dd");
				createImpReqManaService.saveImportReq(vm.order).then(function(){
	        		toastr.success("Thêm mới thành công!");
	        		CommonService.goTo('IM_REQ_MANAGE');
	            }, function(errResponse) {
	            	toastr.error("Thêm mới thất bại");
				});
			}else{
				createImpReqManaService.updateImportReq(vm.order).then(function(){
	        		toastr.success("Cập nhật thành công!");
	        		CommonService.goTo('IM_REQ_MANAGE');
	            }, function(errResponse) {
	            	toastr.error("Cập nhật thất bại");
				});
			}
			
		}
		
		vm.showGoodsDetail = function(dataItem){
			var teamplateUrl="qlk/createImportRequirementManagement/goodDetailPopUp.html";
			 var title="Thông tin chi tiết hàng hóa";
			 var windowId="GOODS_DETAIL";
			 vm.detail = dataItem;
			 fillDataGoodDetailTable([]);
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.order,vm,windowId,false,'85%','85%');
			 
			 if(vm.showFromAlternativeStock){
				 vm.detail.stockTransDetailId = vm.detail.id;
				 createImpReqManaService.getGoodsDetailStockTrans(vm.detail).then(function(d){
					 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
						if(grid){
							grid.dataSource.data(d.plain());
							grid.refresh();
						}
		            }, function(errResponse) {
					});
			 }else if(vm.showFromProvider){
				 vm.detail.shipmentGoodsId = vm.detail.id;
				 createImpReqManaService.getGoodsDetailShipment(vm.detail).then(function(d){
					 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
						if(grid){
							grid.dataSource.data(d.plain());
							grid.refresh();
						}
		            }, function(errResponse) {
					});
			 }else if(vm.showFromProject || vm.showFromDirect){
				 var grid = $("#goodsDetailForOrderGrid").data("kendoGrid");
					if(grid){
						grid.dataSource.data([]);
						grid.refresh();
					}
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
				vm.showFromDirect = true;
		    	vm.order.bussinessType = vm.businessTypes[8].code;
		    }
			
		}
		
		vm.cancel=cancel;
		function cancel(){
		 confirm('Xác nhận hủy bỏ thao tác này', function () {
				CommonService.goTo('IM_REQ_MANAGE');
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
		
			
	}
})();