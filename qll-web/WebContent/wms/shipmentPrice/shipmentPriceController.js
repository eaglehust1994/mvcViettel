(function() {
	'use strict';
	var controllerId = 'shipmentPriceController';
	
	angular.module('MetronicApp').controller(controllerId, shipmentPriceController);
	
	function shipmentPriceController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,shipmentPriceService,shipmentDetailService,
			CommonService, PopupConst, Restangular,shipmentService,RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showTabOne=true;
		vm.shipmentDetail = {};
		vm.shipmentGet = {};
		vm.formValidate={};
		initFormData();
	
		vm.taxSearch={
				status:'1',
				type: '1',
				apply: '1',
				ignore: '0'
		};

		vm.cancel= cancel ;
		vm.shipmentSearch =
		{
				type : 0,
				listStatus : ['3'],
				lstCreatedDeptId :[]
		};
vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.cancelUser=function(id){
			if(id==="loginName"){
				vm.shipmentSearch.loginName=null;
			}
		}
		vm.commonSearchNT = {loginName: '', employeeCode: ''};
		vm.userTemplateNT='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.loginName #</div> </div>',
		vm.userHeaderTemplateNT='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto border-right-ccc">MÃ NHÂN VIÊN</p>' +
	      '<p class="col-md-6 text-header-auto">TÊN ĐĂNG NHẬP</p>' +
	      	'</div>';
		// AutoCompleteDeprt
        vm.headerTemplateDT='<div class="dropdown-header row text-center k-widget k-header">' +
  	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Đơn vị</p>' +
  	      '<p class="col-md-6 text-header-auto">Tên Đơn vị</p>' +
  	      	'</div>';
        vm.deprtOptions = {
  	            dataTextField: "text",
  	            select: function(e) {
  	                var dataItem = this.dataItem(e.item.index());
  	              vm.shipmentSearch.createdDeptId = dataItem.id; // thành id
  	              vm.shipmentSearch.createdDeptName = dataItem.text;//thành name
  	            },
  	            pageSize: 10,
				open: function(e) {
	                        vm.selectedDept = false;
	                    },
  	            dataSource: {
  	                serverFiltering: true,
  	                transport: {
  	                    read: function(options) {
						vm.selectedDept = false;
  	                        return Restangular.all("departmentServiceRest/department/" + 'getForAutocompleteDept').post({name:vm.shipmentSearch.createdDeptName,pageSize:vm.deprtOptions.pageSize}).then(function(response){
  	                            options.success(response);
  	                        }).catch(function (err) {
  	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
  	                        });
  	                    }
  	                }
  	            },
  	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.code #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.text #</div> </div>',
  	            change: function(e) {
  	                if (e.sender.value() === '') {
  	                	 vm.shipmentSearch.createdDeptId = dataItem.id; // thành id
  	                	vm.shipmentSearch.createdDeptName = null;//thành name
  	                }
  	            },
  	            ignoreCase: false
  	        };
setTimeout(function(){
  		  $("#focusname").focus();
  		},15);
		function initFormData() {
			fillDataTable([]);
			
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
			
		}
		vm.shipmentSearch.createdBy = Constant.user.vpsUserToken.sysUserId;
			vm.shipmentSearch.fullName = Constant.user.vpsUserToken.fullName; 
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.shipmentPriceGrid;
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		
		}
		
		// begin
		// grid định giá
		var record=0;
		vm.oldSearch={};
		function fillDataTable(data) {
			vm.gridPriceOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu: false,
				scrollable: false,
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				toolbar: [
                    {
                        name: "actions",
                        template: 
					'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.shipmentPriceGrid.columns.slice(1,vm.shipmentPriceGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
                    
                    }
                    ],
				dataBound: function (e) {
				
				var grid = vm.shipmentPriceGrid;
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
						edit(dataItem);
						vm.valueTax1.totalOriginMoney = dataItem.totalOriginMoney;
						
						
						if(dataItem.totalFee==null){
							vm.checkTotalFree=false;
						}else{
							vm.checkTotalFree=true;
						}
						
				        if(dataItem.status === "3" ||dataItem.status === "4"){

						dataItem.totalFeeDisplay = kendo.toString(kendo.parseFloat(dataItem.totalFee), "n2");
				        var teamplateUrl="wms/shipmentPrice/shipmentPricePopup.html";
						var title="Định giá tài chính cho lô hàng: " + dataItem.code;
						var windowId="SHIPMENTPRICE";
						sum();

						
						CommonService.populatePopupCreate(teamplateUrl,title,dataItem,vm,windowId,false,'90%','85%','totalFee');
				        }
						$("#shipmentPriceGrid").data("kendoGrid").dataSource.read();
						$("#shipmentPriceGrid").data("kendoGrid").dataSource.refresh();  
				    });
					
				},
				dataSource:{
					serverPaging: true,
					 schema: { errors: function (response) {
							if(response.error){
								toastr.error(response.error);
							}
							return response.error;
						},
					 total: function (response) {
					 document.getElementById('countPrice').innerHTML=response.total;
							return response.total;
						},
						data: function (response) {
						for(var i in response.data){
						response.data[i].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId;
						}
							return response.data;
						},
	                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							
							  url: Constant.BASE_SERVICE_URL + "shipmentRsServiceRest/shipment/doSearchPrice",
							  contentType: "application/json; charset=utf-8",
							  type: "POST"
							 
						},					
						parameterMap: function (options, type) {
							   
								  vm.shipmentSearch.page = options.page
								  vm.shipmentSearch.pageSize = options.pageSize
								  vm.oldSearch=angular.copy(vm.shipmentSearch);
								  return JSON.stringify(vm.shipmentSearch)
						}
					},					 
					pageSize: 10
				},
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {

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
			        width: "5%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã lô hàng",
					field: 'code',
					template: dataItem => '<a  class="#=shipmentId#" href="javascript:void(0);" ng-click=vm.showSix(dataItem)> {{dataItem.code}} </a>',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Mã hợp đồng",
					field: 'contractCode',
			        width: "15%",
			        
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Loại lô hàng",
			        field: 'type',
			        width: "10%",
			        template :  
			        "# if(type == 1){ #" + "#= 'Lô hàng nội' #" + "# } " +
			        "else if (type == 2) { # " + "#= 'Lô hàng ngoại' #"+ "#} " +
			        "#",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					
				},
				{
					title: "Ngày giao hàng",
			        field: 'shiperDate',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, 
				{
					title: "Nguời tạo",
			        field: 'fullName',
			        width: "10%",
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
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'status',
					 template :  
					        "# if(status == 1)" +
					        "{ #" + "#= 'Mới tạo' #" + "# } " +
					        "else if (status == 2) " +
					        "{ # " + "#= 'Đã cập nhập hàng hóa' #"+ "#} " +
					        "else if (status == 3) " +
					        "{ # " + "#= 'Đã định lượng' #"+ "#} " +
					        "else if (status == 4) " +
					        "{ # " + "#= 'Đã định giá' #"+ "#} " +
					        "else if (status == 5) " +
					        "{ # " + "#= 'Đã tạo YCKT' #"+ "#} " +
					        "else if (status == 6) " +
					        "{ # " + "#= 'Đã tạo BBKT' #"+ "#} " +
					        "else if (status == 7) " +
					        "{ # " + "#= 'Đã nhập kho' #"+ "#} " +
					        "else if (status == 8) " +
					        "{ # " + "#= 'Đã hủy' #"+ "#} " +
					        "#",
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định giá",
			        template: dataItem =>			
						'<div class="text-center #=shipmentId#"">'+
						'<a class=" icon_table #=shipmentId#" uib-tooltip="Định giá" translate>'+
						'<i ng-hide="dataItem.status != 3 && dataItem.status != 4 ||dataItem.createdBy!=dataItem.sysUserId" class="fa fa-usd" aria-hidden="true" ng-click=vm.edit(dataItem)></i>'+
						'</a>'+
						'<a class=" icon_table #=shipmentId#" uib-tooltip="Khóa" translate>'+
						'<i style="color:grey" ng-hide="dataItem.status == 3 || dataItem.status == 4 ||dataItem.createdBy!=dataItem.sysUserId" class="fa fa-trash" aria-hidden="true"></i>'+
						'</a>'
						+'</div>',
				width: "10%",
			    field:"stt",
			    headerAttributes: {
					style: "text-align:center;"
				}
				}
				,]
			});
		}// end
		
		vm.changeDataAuto=changeDataAuto
		function changeDataAuto(){
			if(processSearch("shipmentPriceClear",vm.selectedDept)){	
			vm.shipmentSearch.createdDeptCode = null; // thành id
  	         vm.shipmentSearch.createdDeptName = null;//thành name
		}}
		
		// begin
		// chi tiết lô hàng
		vm.showSix = function showSix(dataItem){
	    	var dataItem=dataItem;
	    	vm.shipmentDetail = dataItem;
	        var teamplateUrl="wms/shipmentDetail/shipmentDetailList.html";
	        var title="Thông tin chi tiết lô hàng";
			$kWindow.open({
				 options: {
					 modal: true,
					 title: title,
					 visible: false,
					 width: '88%',
					 height: '95%',
					 actions: ["Minimize", "Maximize", "Close"],
					 open: function () {
						 this.wrapper.children('.k-window-content').addClass("fix-footer");
						 $rootScope.$broadcast("shipment.object.data", dataItem);
					 }
				 },                
				 templateUrl: teamplateUrl
			});
		}// end
		
		vm.listRemove=[{
			title: "Định giá",
		},];

		vm.listConvert=[{
			field:"status",
			data:{
				1:'Mới tạo',
				2:'Đã cập nhập hàng hóa',
				3:'Đã định lượng',
				4:'Đã định giá',
				5:'Đã tạo YCKT',
				6:'Đã tạo BBKT',
				7:'Đã nhập kho',
				8:'Đã hủy'

		}
		},{
			field:"type",
			data:{
				1:'Lô hàng nội',
				2:'Lô hàng ngoại'
			}
		}];
		// begin
		// xuat excel grid
		vm.exportExcelGrid = function(){
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			var ds = $("#shipmentPriceGrid").data("kendoGrid").dataSource;
			var ds1 = ds.data();
			if (ds1.length == 0){
				 toastr.warning(gettextCatalog.getString("Không có dữ liệu"));
			}else{
			
				shipmentPriceService.getForExportGrid(vm.oldSearch).then(function(d) {
					CommonService.exportFile(vm.shipmentPriceGrid,d.data,vm.listRemove,vm.listConvert,"Danh sách lô hàng");
				});
		}
			}// end
		
		
		// begin
		// grid thong tin thue
		vm.valueTax1={};
		vm.rowIndex=null;
		var record=0;
		function fillTaxTable(data) {
			
			vm.gridPrice1Options = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: true,
				resizable: true,
				columnMenu: false,
				dataSource:data,
				//scrollable: false,
				/* save: function () {
                    var grid = this;
                    setTimeout(function () {
                      grid.refresh();
                      if (grid) {
							if (!vm.rowIndex) {
								grid.select("tr:eq(0)");
							} else {
								grid.select("tr:eq(" + vm.rowIndex + ")");
							}
						
                  }
                    })
                  }, */
				noRecords: true,
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
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
					var grid = $("#shipmentPrice1Grid").data("kendoGrid");
                    var gridData =  grid.dataSource.view();
                    
                    if(gridData.length> 0){
                    	vm.totalTax = 0;
                    	for (var i = 0; i < gridData.length; i++) {
	                        vm.totalTax += kendo.parseFloat(gridData[i].value * vm.valueTax1.totalOriginMoney)/100;
	                     }
                    	
                    	
                    }
                },
				columns: [{
					title: "TT",
					field:"stt",
			        template: function () {
						  return ++record;
						 },
			        width: '5%',
			
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				, {
					title: "Tên thuế",
			        field: 'name',
			        width: '25%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thuế xuất <br/>(%)",
			        field: 'value',
			        width: '12%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Loại thuế",
					field: 'type',
					template : "# if(type === '1'){ #" + "#= 'Đầu vào' #" + "# } " + "else if (type === '2') { # " + "#= 'Đầu ra' #"+ "#} #",
				
			        width: '11%',
			        format:"{0:n}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Phân bổ",
			        field: 'apply',
			        template :  "# if(apply === '1'){ #" + "#= 'Có' #" + "# } " + "else if (apply === '0') { # " + "#= 'Không' #"+ "#} #",
			        width: '10%',
			        
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Miễn thuế",
			        field: 'ignore',
			        template :  "# if(ignore === '1'){ #" + "#= 'Có' #" + "# } " + "else if (ignore === '0') { # " + "#= 'Không' #"+ "#} #",
			        width: '12%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tiền thuế (VNĐ)",
					field: 'valueshipmenttax',
					template :function($scope){
						var thue = ($scope.value * vm.valueTax1.totalOriginMoney)/100;
						return kendo.toString(thue,'n2');
					},
			        width: '20%',
			        format:"{0:n}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},],
			});
		}// end
		// begin
		// grid dinh gia phan bo hang hoa
		function fillData2Table(data) {
			vm.gridPriceGoodsOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: true,
				resizable: true,
				columnMenu: false,			 
				dataSource: data,
				scrollable: false,
				noRecords: true,
				
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
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				columns: [{
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
					title: "Mã hàng",
					field: 'goodsCode',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng",
					field: 'goodsName',
			        width: "20%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Đơn vị tính",
					field: 'unitTypeName',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
					field: 'amount',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "Tỷ lệ % theo danh mục",				
					field: 'estimatePricePercent',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "Đơn giá phân bổ",
					field: 'applyPrice',
			        width: "15%",
			      format:"{0:n}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},{
					title: "Thành tiền",
					field: 'applyTotalMoney',
			        width: "15%",
					format:"{0:n}",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {

						style: "text-align:right;"
					},
				}
				,],
				dataBound: function(e) {
					var grid = $("#shipmentGoods2Grid").data("kendoGrid");
                    var gridData =  grid.dataSource.view();
					if(gridData.length> 0){
                    	vm.applyTotalMoney = 0;
                    	for (var i = 0; i < gridData.length; i++) {
	                        vm.applyTotalMoney += gridData[i].applyTotalMoney ;
	                     }
                    	
                 
                    }
	                },
			});
			
			
		}// end
		// begin
		//popup đơn vị
		vm.openDepfartment=openDepartment;
		function openDepartment(){
			var obj={};
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
						pageSizes:10,
						pageSizes: [10, 15, 20, 25],
						messages: {
			                display: "{0}-{1} của {2} kết quả",
			                itemsPerPage: "kết quả/trang",
			                empty: "Không có kết quả hiển thị"
			            }
					},
					columns:[{
						title: "STT",
						field: "#",
						template: dataItem => $("#gridView").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
						width: "12%",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:right;"
							},
					}, 
					         {
						title: "Mã phòng<br> ban",
						field: "code",
						width: "18%",
						headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Tên phòng ban",
						field: "text",
						width: "30%",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
					}, {
						title: "Đơn vị cha",
						field: "parentName",
						width: "30%",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
					},{
						title: "Ngày hiệu lực",
						field: "effectDate",
						width: "20%",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
					},{
						title: "Ngày hết hiệu lực",
						field: "endDate",
						width: "25%",
						 headerAttributes: {
								style: "text-align:center;"
							},
							attributes: {
								style: "text-align:left;"
							},
					},{
						title: "Chọn",
						 template: 
					        	'<div class="text-center "> '	+
				        	'		<a  type="button" class=" icon_table" uib-tooltip="Chọn" translate>'+
				        	'			<i id="#=departmentId#" ng-click=save(dataItem) class="fa fa-check color-green" aria-hidden="true"></i> '+
				        	'		</a>'
								+'</div>',  
				        width: "15%",
				        field:"stt"
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%');
			});
		}
		// end
		vm.commonSearch = {name: '', code: ''};
 
        
		vm.onSave =onSave;
		function onSave(data){
			vm.shipmentSearch.createdDeptName=data.text;
			vm.shipmentSearch.createdDeptId = data.id;
		}	
		// begin
		// đóng popup
		function cancel(){		
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			/* $("#shipmentPriceGrid").data("kendoGrid").dataSource.read();
			$("#shipmentPriceGrid").data("kendoGrid").dataSource.refresh();  */  
				
		}
		// end

		vm.cancelDept = function()
		{
			vm.shipmentSearch.createdDeptName = undefined;
			vm.shipmentSearch.createdDeptId = undefined;
		}
		vm.cancelStatis = function()
		{
			vm.shipmentSearch.listStatus = null;
		}
		vm.cancelCreatedBy = function()
		{
			vm.shipmentSearch.createName = undefined;
		}// end
		
		// begin
		// tinh tong
		vm.sum = sum;
		function sum(totalFee){
			//if(vm.shipment.status==3){
				if(totalFee !== undefined){
					vm.totalFee = totalFee;
				}else if(totalFee === undefined){
					vm.totalFee = totalFee;
				}
			vm.totalMoney = kendo.parseFloat(vm.valueTax1.totalOriginMoney || 0)  + kendo.parseFloat(vm.totalFee || 0) + kendo.parseFloat(vm.totalTax || 0);
	
		}
		$scope.$watch('vm.totalTax', function() {
		        sum();
		    });// end
		 vm.checkTotalFree =true;
		vm.edit = edit;
		// begin
		// popup Dinh gia
		function edit(dataItem){
			vm.showTabOne = true;
			loadShipmentPrice();
			if(dataItem.totalFee!=null){
				vm.checkTotalFree=true;
			}
			vm.shipment =dataItem;
			vm.shipmentGet = dataItem;
			vm.shipmentGet.shipmentId =dataItem.shipmentId;
			vm.valueTax1.totalOriginMoney = dataItem.totalOriginMoney;
			vm.valueTax1.totalMoney = dataItem.totalMoney;
			vm.valueTax1.totalFee = dataItem.totalFee;
			vm.shipment.status = dataItem.status; 
			
			var teamplateUrl="wms/shipmentPrice/shipmentPricePopup.html";
			var title="Định giá tài chính cho lô hàng: "+dataItem.code;
			var windowId="SHIPMENTPRICE";
		
			sum(vm.valueTax1.totalFee);
			
			dataItem.totalFeeDisplay = kendo.toString(kendo.parseFloat(dataItem.totalFee), "n2");
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'95%','85%','totalFee');

			$scope.$on("Popup.open", function () {
				loadShipmentPrice();
				DocTien(dataItem);
				if(vm.shipment.status === "3"){
			vm.docso.totalOriginMoney=DocTienBangChu(vm.valueTax1.totalOriginMoney);
			$("#shipmentPricePopup2").addClass('declineQLK postion-icon');
			$("#shipmentPricePopup4").addClass('declineQLK postion-icon');
			}
			if(vm.shipment.status === "4"){
			vm.docso.totalOriginMoney=DocTienBangChu(vm.valueTax1.totalOriginMoney);
			$("#shipmentPricePopup2").addClass('acceptQLK postion-icon');
			$("#shipmentPricePopup4").addClass('acceptQLK postion-icon');
			}
			});  
		    $("#shipmentPriceGrid").data("kendoGrid").dataSource.read();
			$("#shipmentPriceGrid").data("kendoGrid").dataSource.refresh();  
			/* doSearchMap(); */
			 
		}// end
		
		//begin
		//thuế
		function loadShipmentPrice(){
			
				shipmentPriceService.getTax(vm.taxSearch).then(function(d) {
					fillTaxTable(d.plain()); 
					vm.docso.totalTax=DocTienBangChu(vm.totalTax);
					vm.docso.totalMoney=DocTienBangChu(vm.totalMoney);
					vm.docso.totalOriginMoney=DocTienBangChu(vm.valueTax1.totalOriginMoney);
				}, function(errResponse) {
					console.error('Error Tìm kiếm');
				});
		}
		//end
		$scope.$on("Popup.open", function () {
			
			});  
		// begin
		// tach ban ghi
		vm.tachbanghi=tachbanghi;
		function tachbanghi(){
			vm.obj = [];
			vm.objReturn=[];
			
			vm.shipmentGoods1={};
			shipmentPriceService.getShipmentGoodsPrice(vm.shipmentGet).then(function(d) {
				vm.obj = d.plain();
				
				for(var i=0;i<vm.obj.length;i++){
					vm.newItem ={};
					vm.newItem.estimatePrice = vm.obj[i].estimatePrice;
					vm.newItem.estimatePricePercent = vm.obj[i].estimatePricePercent;
					vm.newItem.estimatePriceSum = vm.obj[i].estimatePriceSum;
					vm.newItem.goodsId = vm.obj[i].goodsId;
					vm.newItem.shipmentId = vm.obj[i].shipmentId;
					vm.newItem.goodsCode = vm.obj[i].goodsCode;
					vm.newItem.goodsName = vm.obj[i].goodsName;
					vm.newItem.goodsUnitName = vm.obj[i].goodsUnitName;
					vm.newItem.totalOriginPrice = vm.obj[i].totalOriginPrice;
					vm.newItem.unitTypeId = vm.obj[i].unitTypeId;
					vm.newItem.unitTypeName = vm.obj[i].unitTypeName;
					vm.newItem.manufacturerId = vm.obj[i].manufacturerId;
					vm.newItem.producingCountryId = vm.obj[i].producingCountryId;
					vm.newItem.manufacturerName = vm.obj[i].manufacturerName;
					vm.newItem.producingCountryName = vm.obj[i].producingCountryName;
					vm.newItem.createdDate = vm.obj[i].createdDate;
					vm.newItem.description = vm.obj[i].description;
					vm.newItem.originPrice = vm.obj[i].originPrice;
					vm.newItem.partNumber = vm.obj[i].partNumber;
					 vm.newItem.serial = vm.obj[i].serial;
					var n = kendo.parseFloat((vm.totalMoney * (vm.obj[i].estimatePricePercent/100))/vm.obj[i].amount).toFixed(2);
					var b = ((vm.totalMoney * (vm.obj[i].estimatePricePercent/100))/vm.obj[i].amount).toFixed(3);
					if(Number(n) === Number(b)){
						vm.obj[i].applyPrice = kendo.parseFloat(n,'0,000.00');
						
						vm.obj[i].applyTotalMoney = parseFloat(vm.obj[i].applyPrice * vm.obj[i].amount);
					}else{
						if(vm.obj[i].amount > 1){
								vm.obj[i].applyPrice = parseFloat(n);
								vm.obj[i].amount = vm.obj[i].amount - 1;
								vm.obj[i].applyTotalMoney = parseFloat(vm.obj[i].applyPrice * vm.obj[i].amount);
								vm.newItem.amount = 1;
								var price = parseFloat((vm.totalMoney * (vm.obj[i].estimatePricePercent/100)) - (vm.obj[i].applyPrice*vm.obj[i].amount));
								vm.newItem.applyPrice = kendo.parseFloat(price, '0,000.00');
								vm.newItem.applyTotalMoney = parseFloat(vm.newItem.applyPrice * vm.newItem.amount);
								
								vm.objReturn.push(vm.newItem);
								// break;
						}
						else{
							vm.obj[i].applyPrice = kendo.parseFloat(n, '0,000.00');
							
							vm.obj[i].applyTotalMoney = parseFloat(vm.obj[i].applyPrice * vm.obj[i].amount);
						}
					}
				}
				for(var i=0;i<vm.objReturn.length;i++){
					vm.obj.push(vm.objReturn[i]);
				}
				refreshGrid(vm.obj,vm.shipmentGoods2Grid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		// end
		vm.nextPopup = nextPopup;
		
		// begin
		// sang popup thu 2
		function nextPopup(){
			if(vm.totalFee == 0){
			document.getElementById('totalFee').focus();
			toastr.warning("Tổng chi phí phải lớn hơn 0"); 
			
				return;
			}


			if(!vm.validator.validate()){
				$("#totalFee").focus();
				
				return;
			}else{

				vm.showTabOne = false;
				tachbanghi();
			}
			fillData2Table([]);
		}
		// end
		// begin
		// luu thong tin vao 2 bang SHIPMENT_GOODS va SHIPMENT_GOODS_DETAILS
		vm.saveDetailShipment=saveDetailShipment;
		function saveDetailShipment(){
			vm.updateShipmentTotal={};
			vm.lstShipmentTax = [];
			vm.lstShipmentGoods=[];
			vm.lstShipmentDetail=[];
			vm.lst=[];
			vm.updateShipmentTotal.lstShipmentTax=[];
			vm.updateShipmentTotal.lstShipmentGoods=[];
			vm.updateShipmentTotal.lstShipmentDetail=[];
			vm.updateShipmentTotal.shipmentId = vm.shipment.shipmentId;
			vm.updateShipmentTotal.contractId = vm.shipment.contractId;
			vm.updateShipmentTotal.projInvestProjectId = vm.shipment.projInvestProjectId;
			vm.updateShipmentTotal.contractCode = vm.shipment.contractCode;
			vm.updateShipmentTotal.projectCode = vm.shipment.projectCode;
			vm.updateShipmentTotal.type = vm.shipment.type;
			vm.updateShipmentTotal.shiperDate = vm.shipment.shiperDate;
			vm.updateShipmentTotal.createdBy = vm.shipment.createdBy;
			vm.updateShipmentTotal.createdDeptId = vm.shipment.createdDeptId;
			vm.updateShipmentTotal.shipPlace = vm.shipment.shipPlace;
			vm.updateShipmentTotal.createdDate = vm.shipment.createdDate;
			vm.updateShipmentTotal.code = vm.shipment.code;
			vm.updateShipmentTotal.totalOriginMoney = vm.shipment.totalOriginMoney;
			vm.updateShipmentTotal.totalTax = vm.totalTax;
			vm.updateShipmentTotal.totalMoney = vm.totalMoney;
			vm.updateShipmentTotal.description = vm.shipment.description;
			vm.updateShipmentTotal.customsProcedure = vm.shipment.customsProcedure;
			vm.updateShipmentTotal.shiper = vm.shipment.shiper;
			vm.updateShipmentTotal.status = vm.shipment.status;
			vm.updateShipmentTotal.totalFee = kendo.parseFloat(vm.shipment.totalFee);
			  if(vm.updateShipmentTotal.status == 3){
				vm.updateShipmentTotal.status=4;
			} 
			 
			var x = $("#shipmentPrice1Grid").data("kendoGrid").dataSource.view();
			for(var i=0;i<x.length;i++){
				vm.lst.shipmentId = vm.shipment.shipmentId;
				vm.lst.taxId = x[i].taxId;
				x[i].valueshipmenttax=(vm.updateShipmentTotal.totalOriginMoney*x[i].value)/100;	
				vm.lst.valueshipmenttax= x[i].valueshipmenttax;
				vm.updateShipmentTotal.lstShipmentTax.push(x[i]);
			}
			
			var shipmentPrice = $('#shipmentGoods2Grid').data("kendoGrid").dataSource.data();
			for(var i=0;i<shipmentPrice.length;i++){
				vm.updateShipmentTotal.lstShipmentGoods.push(shipmentPrice[i]);
				vm.updateShipmentTotal.partNumber = shipmentPrice[i].partNumber;
				vm.updateShipmentTotal.serial = shipmentPrice[i].serial;
				vm.updateShipmentTotal.lstShipmentDetail.push(shipmentPrice[i]);
			}
				shipmentService.saveDetailShipment(vm.updateShipmentTotal).then(function(result){
					if(result.error){
        				toastr.error(result.error);
        				return;
        			}
					
					toastr.success("Định giá tài chính thành công!");
					
					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					$("#shipmentPriceGrid").data("kendoGrid").dataSource.read();
					$("#shipmentPriceGrid").data("kendoGrid").dataSource.refresh();
				}, function(errResponse) {
					toastr.error("Định giá tài chính thất bại!");
				});
			 
		}// end
		
	
		// begin
		// quay lai popup dau tien
		vm.prevStep = prevStep;
		function prevStep(){
			vm.showTabOne = true;
		}
		// end
		

		
		// begin
		// refresh 
		function refreshGrid(d,grid) {
			var grid = grid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		// end
	
	//begin
	//đọc tiền
		vm.dmso=function(totalFee){
			  vm.checkTotalFree=true;
			vm.docso.totalFee=	DocTienBangChu(kendo.parseFloat(totalFee));
			if(totalFee != undefined){
					vm.totalFee = totalFee;
				}else if(totalFee == undefined){
					vm.totalFee = totalFee;
				}
			vm.totalMoney = kendo.parseFloat(vm.valueTax1.totalOriginMoney || 0)  + kendo.parseFloat(vm.totalFee || 0) + kendo.parseFloat(vm.totalTax || 0);
			vm.docso.totalMoney=DocTienBangChu(vm.totalMoney);
		
}//end


	vm.docso={};
		vm.DocTien = DocTien(vm.docso);
		// begin
		// doc tien
		function DocTien(dataItem){
		
				if(dataItem.totalFee !== undefined || dataItem.totalOriginMoney !== undefined || dataItem.totalTax !== undefined || dataItem.totalMoney !== undefined ){
					//dataItem.totalMoney=vm.totalMoney;
					dataItem.totalTax=vm.totalTax;
					vm.docso.totalFee = DocTienBangChu(dataItem.totalFee);
					vm.docso.totalOriginMoney = DocTienBangChu(dataItem.totalOriginMoney);
					vm.docso.totalMoney = DocTienBangChu(dataItem.totalMoney);
					vm.docso.totalTax = DocTienBangChu(dataItem.totalTax);
				}
				if(dataItem.totalFee === undefined || dataItem.totalOriginMoney === undefined || dataItem.totalTax === undefined || dataItem.totalMoney === undefined ){
					//dataItem.totalMoney=vm.totalMoney;
					dataItem.totalTax=vm.totalTax;
					vm.docso.totalFee = DocTienBangChu(dataItem.totalFee);
					vm.docso.totalOriginMoney = DocTienBangChu(dataItem.totalOriginMoney);
					vm.docso.totalMoney = DocTienBangChu(dataItem.totalMoney);
					vm.docso.totalTax = DocTienBangChu(dataItem.totalTax);
				}
			
			
		}// end 

		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.shipmentPriceGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.shipmentPriceGrid.showColumn(column);
            } else {
            	vm.shipmentPriceGrid.hideColumn(column);
            }
        	
        	
        }
		//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		})
		//
		/* vm.clickFree=clickFree;
		function clickFree(){
			if(vm.totalFee === null){
				$("#totalFee").focus();
				return;
			}
			
		} */
		
		/*
		 * * Filter cÃ¡c c?t c?a select
		 */	
			$(document).on("keypress", function (e) {
         		switch(e.keyCode) {
         			case 27:
         				$("#cancelp").click();
         				break;
         			case 13:
					if($(':focus').size()===0){
					$( "#nextPopup1" ).click(function( event ) {
							event.stopPropagation();
							});
							break;
					}
					 if (e.keyCode === 13&& !$('#nextPopup1:focus').length&& !$('#cancelp:focus').length) {
         				$("#nextPopup1").click();
         				break;
						}
         		}
         	} );
		// OnkeyDown
    	 $(document).on("keydown", function (e) {
            if (e.keyCode === 13) {
					if($('.k-widget.k-window.fix-footer').css('display')!=='block'){
							
							$("#findParamprice").click();
						}
            	
            }

            }); 
    	// End
    	
   
    	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		// /END
	}
	
	
})

();