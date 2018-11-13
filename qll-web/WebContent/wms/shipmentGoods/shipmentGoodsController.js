(function() {
	'use strict';
	var controllerId = 'shipmentGoodsController';
	
	angular.module('MetronicApp').controller(controllerId, shipmentGoodsController);
	
	function shipmentGoodsController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,shipmentGoodsService,shipmentDetailService,
			CommonService, PopupConst, Restangular, shipmentService,RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.showDetail = true;
		vm.folder = '';
		vm.shipment={};
		vm.dataItemUpdate={};
		vm.shipmentSearch =
		{
				type : 0,
				listStatus : ['2'],
				lstCreatedDeptId :[]
		};
		vm.userTemplate='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
		vm.userHeaderTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
      '<p class="col-md-6 text-header-auto">Tên nhân viên</p>' +
      	'</div>';
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
			fillDataDetailTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
				vm.shipmentSearch.createdBy = Constant.user.vpsUserToken.sysUserId;
			vm.shipmentSearch.fullName = Constant.user.vpsUserToken.fullName;
		}
		var record=0;
		function fillDataTable(data) {
			vm.gridGoodsOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu: false,
				scrollable: false,
				toolbar: [
                    {
                        name: "actions",
                        template: 
                        	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
 	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
 	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
 		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
 		                    '<label ng-repeat="column in vm.shipmentGoodsGrid.columns.slice(1,vm.shipmentGoodsGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
 		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
 		                    '</label>'+
 		                    '</div></div>'
                    
                    }
                    ],
				dataBound: function (e) {
				    var grid = vm.shipmentGoodsGrid;
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
						vm.shipment =dataItem;
						vm.downloadObj = dataItem;
						 if(dataItem.status==2||dataItem.status==3){
						$('#shipmentGoodsGrid').data('kendoGrid').dataSource.read();
						$("#shipmentGoodsGrid").data("kendoGrid").refresh();
						dataItem.totalOriginMoneyDisplay = kendo.toString(kendo.parseFloat(dataItem.totalOriginMoney), "n2");
						$scope.$on("Popup.open", function () {
						loadDataDetail(dataItem);
						});
						DocTien(dataItem);
				        var teamplateUrl="wms/shipmentGoods/shipmentGoodsPopup.html";
						var title="Định lượng tỷ trọng kỹ thuật";
						var windowId="SHIPMENTGOODS";
						CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'97%','90%','totalOriginMoney1');
				        }
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
						 document.getElementById('count1').innerHTML=response.total;
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
							
							  url: Constant.BASE_SERVICE_URL + "shipmentRsServiceRest/shipment/doSearchQuantity",
							  contentType: "application/json; charset=utf-8",
							  type: "POST"
							 
						},					
						parameterMap: function (options, type) {
							   
								  vm.shipmentSearch.page = options.page
								  vm.shipmentSearch.pageSize = options.pageSize
								  
								  return JSON.stringify(vm.shipmentSearch)
						}
					},					 
					pageSize: 10
				},
				dataBinding: function() {
					record = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					template: dataItem => '<a style="text-decoration: none;" class="#=shipmentId#" href="javascript:void(0);" ng-click=vm.showSix(dataItem)> {{dataItem.code}} </a>',
			        width: "15%",
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
			        width: "12%",
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
			        width: "14%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, 
				{
					title: "Người tạo",
			        field: 'fullName',
			        width: "13%",
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
			        width: "30%",
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
					        "{ # " + "#= 'Đã cập nhật hàng hóa' #"+ "#} " +
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
			        width: "12%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định lượng",
			        template:
					'<div class="text-center #=shipmentId#"">'+
					'<a ng-hide="dataItem.status != 2 && dataItem.status != 3 ||dataItem.createdBy!=dataItem.sysUserId" class="#=shipmentId# icon_table" uib-tooltip="Định lượng" ng-click=vm.quantyfi(#=shipmentId#) translate>'+
					'<button class="fa btn mapQLKGIRD"  aria-hidden="true"></button> '+
					'</a>'+
					'<a class=" icon_table #=shipmentId#" uib-tooltip="Khóa" translate>'+
					'<i  style="color:grey" ng-show="dataItem.status != 3 && dataItem.status != 2||dataItem.createdBy!=dataItem.sysUserId" class="fa fa-trash" aria-hidden="true"></i>'+
					'</a>'
					+'</div>',
			        width: "10%",
			        field:"stt"
				}
				,]
			});
		}
		
		vm.showSix = function showSix(dataItem){
			var dataItem = dataItem;
	        var teamplateUrl="wms/shipmentDetail/shipmentDetailList.html";
	        var title="Thông tin chi tiết lô hàng";
			$kWindow.open({
				 options: {
					 modal: true,
					 title: title,
					 visible: false,
					 width: '88%',
					 height: '88%',
					 actions: ["Minimize", "Maximize", "Close"],
					 open: function () {
						 this.wrapper.children('.k-window-content').addClass("fix-footer");
						 $rootScope.$broadcast("shipment.object.data", dataItem);
					 }
				 },                
				 templateUrl: teamplateUrl
			});
		}
		//Danh sách hàng hóa của lô hàng
		var record1=0;
		function fillDataDetailTable(data) {
			var dataSource = new kendo.data.DataSource({
                pageSize: 10,
                data: data,
                autoSync: true,        
                schema: {
                    model: {
                        id: "goodsCode",
                    	fields: {
							del: {editable: false},
                    		stt: {editable: false},
                    		goodsCode: {editable: false},
                    		goodsName: {editable: false},
                    		unitTypeName: {editable: false},
                    		estimatePriceSum:{editable:false},
                    		estimatePricePercent:{editable:false},
                    		totalOriginPrice:{editable:false},
                    		originPrice:{editable:false},
                    		amount: {editable:false},
                    		estimatePrice:{type:"number"}
                    	}
                    }
                }
            });
			vm.gridGoodsDetailOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu: false,
				scrollable:false,
				dataSource: dataSource,
				dataBinding: function() {
					record1 = (this.dataSource.page() -1) * this.dataSource.pageSize();
					var grid = $("#shipmentGoodsDetailGrid").data("kendoGrid");
                    var gridData =  grid.dataSource.data();
                    
                    if(gridData.length> 0){
                    	vm.totalEstimatePriceSum = 0;
						vm.totalTotalOriginPrice=0;
                    	for (var i = 0; i < gridData.length; i++) {
							gridData[i].estimatePriceSum=gridData[i].estimatePrice*gridData[i].amount
						if(kendo.parseFloat(vm.shipment.totalOriginMoneyDisplay)>0){
		            		gridData[i].estimatePricePercent=(gridData[i].estimatePriceSum/(kendo.parseFloat(vm.shipment.totalOriginMoneyDisplay)))*100;
									}
									else{
									gridData[i].estimatePricePercent=null;
									}
							vm.totalEstimatePriceSum +=gridData[i].estimatePriceSum;
							vm.totalTotalOriginPrice +=gridData[i].totalOriginPrice;
	                     }
						}
				},
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
					template:dataItem => $("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					width: '10%',
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
			        width: '20%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				 {
					title: "Tên hàng",
					field: 'goodsName',
			        width: '40%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Đơn vị <br> tính",
			        field: 'unitTypeName',
			        width: '20%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Số lượng",
			        field: 'amount',
			        width: '20%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
					format:"{0:n2}",
				},
				{
					title: "Định lượng cho 1 <br> đơn vị HH",
			        field: 'estimatePrice',
			        width: '30%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;border: 1px groove  ;"
					},
					format:"{0:n2}",
					editor:editNumberWithoutSpinners
				},
				{
					title: "Định lượng theo <br> danh mục",
			        field: 'estimatePriceSum',
			        width: '30%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
					format:"{0:n2}"
				},
				{
					title: "Tỷ lệ % theo <br> danh mục",
			        field: 'estimatePricePercent',
			        format:"{0:n2}",
			        width: '30%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				},
				{
					title: "Đơn giá theo <br> danh mục",
			        field: 'originPrice',
			        width: '30%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;background-color:Turquoise"
					},
					format:"{0:n2}"
				},
				{
					title: "Thành tiền theo <br> danh mục",
			        field: 'totalOriginPrice',
			        width: '30%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;background-color:LightSkyBlue"
					},
					format:"{0:n2}"
				}]
			});
		}
		//End
		//Editor Estimate
		function editNumberWithoutSpinners(container, options) {
			 $('<input id= "estimate" data-bind="value:' + options.field + '"/>')
	            .appendTo(container)
	            .kendoAutoComplete({
	        });
			 $("#estimate").attr('maxlength','15');
			$("#estimate").keypress(function(event) {
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

		        if ((text.indexOf('.') != -1) &&
		            (text.substring(text.indexOf('.')).length > 2) &&
		            (event.which != 0 && event.which != 8) &&
		            ($(this)[0].selectionStart >= text.length - 2)) {
		                event.preventDefault();
		        }      
		    });

			$("#estimate").bind("paste", function(e) {
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
		//End Editor Estimate
		
		//Thông báo lỗi khi import định lượng fail
		function fillDataImportErrTable(data) {
			vm.importGoodResultGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data,
				columnMenu: false,
				noRecords: true,
				scrollable: false,
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
			        width: 10,
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
			        width: 20,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Cột lỗi",
			        field: 'columnError',
			        width: 20,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}, {
					title: "Nội dung lỗi",
			        field: 'detailError',
			        width: 70,
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
		//End
		// Xóa hàng hóa của lô hàng
		vm.removeRowFile = removeRowFile
		function removeRowFile(dataItem){
			confirm('Xác nhận xóa',function(){
			$("#shipmentGoodsDetailGrid").data('kendoGrid').dataSource.remove(dataItem);
			 var sizePage = $("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.total()+1;
								if(sizePage % 10 === 1||sizePage % 15 === 1||sizePage % 20 === 1||sizePage % 25 === 1){
									var currentPage = $("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.page();
									if (currentPage > 1) {
										$("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.page(currentPage - 1);
									}
								}
			})
		}
		//End
		// Xuất file excel theo Grid
	vm.exportExcelGrid = function(){
			var ds = $("#shipmentGoodsGrid").data("kendoGrid").dataSource;
			var ds1 = ds.data();
			 if (ds1.length == 0){
				 toastr.warning(gettextCatalog.getString("Không có dữ liệu xuất"));
				 return;
			 }else{
			shipmentService.doSearchQuantity(vm.shipmentSearch).then(function(d) {
				CommonService.exportFile(vm.shipmentGoodsGrid,d.data,vm.listRemove,vm.listConvert,vm.fileName);
			});
			 } 	 
		}
		//End
		//
		vm.listRemove=[{
			title: "Định lượng",
		},
		{
			title : "<input type='checkbox' id='checkalllistdraw' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />"
		}]
	
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Mới tạo',
				2:'Đã cập nhật hàng hóa',
				3:'Đã định lượng ',
				4:'Đã định giá',
				5:'Đã tạo YCKT',
				6:'Đã tạo BBKT',
				7:'Đã nhập kho',
				8:'Đã hủy',
			}
		},{
			field:"type",
			data:{
				1:'Lô hàng nội',
				2:'Lô hàng ngoại',
			}
		}];
		//End
		vm.fileName="Danh sách hàng hóa của lô hàng";
		function statusOfShipment(x){
			 if(x === 1)
			 { return "Mới tạo";}
			 else if (x === 2)
			 { return "Đã cập nhật hàng hóa";}
		     else if (x === 3)
		     { return "Đã định lượng";}
		     else if (x === 4){ return "Đã định giá";}
		     else if (x === 5){ return "Đã tạo YCKT";}
		     else if (x === 6){ return "Đã tạo BBKT";}
		     else if (x === 7){ return "Đã nhập kho";}
		     else if (x === 8){ return "Đã hủy";}
		}
		
		function TypeOfShipment(x){
			if(type === 1){ 
				return "Lô hàng nội";
			}else if (type === 2) {
				return "Lô hàng ngoại";
			}
			
		}
		vm.cancel= cancel;
		function cancel(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		//Hàm tìm kiếm lô hàng
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			  
				  var grid =vm.shipmentGoodsGrid;	
					if(grid){
						grid.dataSource.query({
							page: 1,
							pageSize: 10
						});
					}
			  
			
		}
		//End
		//Popup màn hình định lượng
		vm.quantyfi = function quantyfi(id)
		{ 
			$('#shipmentGoodsGrid').data('kendoGrid').dataSource.read();
			$("#shipmentGoodsGrid").data("kendoGrid").refresh();
			var grid=vm.shipmentGoodsGrid;
			var item=grid.table.find("tr div." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			var dataItem=grid.dataSource.getByUid(uid);
			vm.shipment =dataItem;
			vm.downloadObj = dataItem;
			dataItem.totalOriginMoneyDisplay = kendo.toString(kendo.parseFloat(dataItem.totalOriginMoney), "n2");
			vm.importId = id;
				$scope.$on("Popup.open", function () {
			loadDataDetail(dataItem);
			});
		DocTien(dataItem);
			var teamplateUrl="wms/shipmentGoods/shipmentGoodsPopup.html";
			var title="Định lượng tỷ trọng kỹ thuật";
			var windowId="SHIPMENTGOODS";
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'97%','90%','totalOriginMoney1');
		}
		//End
		function refreshGrid(d) {
			var grid = vm.shipmentGoodsDetailGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		// Load dữ liệu hàng hóa của lô hàng
		function loadDataDetail(dataItem)
		{
			vm.dataItemUpdate=dataItem
			shipmentGoodsService.doSearchMap(dataItem).then(function(result){
				refreshGrid(result.plain());
				$('#shipmentGoodsDetailGrid').data("kendoGrid").dataSource.page(1);
			}, function(errResponse){
				if (errResponse.status === 409) {
					toastr.error(gettextCatalog.getString("Error!"));
				} else {
					toastr.error(gettextCatalog.getString("NoAccess!"));
				}
			});
		}
		//End
		//Export file biểu mẫu
		vm.getExcelTemplate = function(){
			shipmentGoodsService.downloadTemplate(vm.downloadObj).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		}
		//End
		//Export file lỗi
		vm.exportExcelErr = function(){
			shipmentGoodsService.downloadErrorExcel(vm.objectErr).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			});
		}
		//End
		//Cập nhật định lượng hàng hóa cho lô hàng
		vm.saveShipment= function saveShipment(){
			vm.shipmentUpdate={};
			var lstShipmentGoods=[];
			vm.shipmentUpdate.lstShipmentGoods=[];
			vm.shipmentUpdate.shipmentId = vm.dataItemUpdate.shipmentId;
			vm.shipmentUpdate.contractId = vm.dataItemUpdate.contractId;
			vm.shipmentUpdate.amount = vm.dataItemUpdate.amount;
			vm.shipmentUpdate.projInvestProjectId = vm.dataItemUpdate.projInvestProjectId;
			vm.shipmentUpdate.contractCode = vm.dataItemUpdate.contractCode;
			vm.shipmentUpdate.projectCode = vm.dataItemUpdate.projectCode;
			vm.shipmentUpdate.type = vm.dataItemUpdate.type;
			vm.shipmentUpdate.shiperDate = vm.dataItemUpdate.shiperDate;
			vm.shipmentUpdate.createdBy = vm.dataItemUpdate.createdBy;
			vm.shipmentUpdate.createdDate = vm.dataItemUpdate.createdDate;
			vm.shipmentUpdate.createdDeptId = vm.dataItemUpdate.createdDeptId;
			vm.shipmentUpdate.status =3;
			vm.shipmentUpdate.code = vm.dataItemUpdate.code;
			vm.shipmentUpdate.totalOriginMoney = kendo.parseFloat(vm.shipment.totalOriginMoneyDisplay);
			var dataShipmentGoodsGrid=$('#shipmentGoodsDetailGrid').data("kendoGrid").dataSource.data();
				if(vm.shipmentUpdate.totalOriginMoney==null||vm.shipmentUpdate.totalOriginMoney===0){
					document.getElementById('totalOriginMoney1').focus();
					 toastr.warning("Trường tổng nguyên giá lô hàng không được trống");
					return;
				}
				else{
				for(var i=0;i<dataShipmentGoodsGrid.length;i++){
					dataShipmentGoodsGrid[i].estimatePriceSum=dataShipmentGoodsGrid[i].estimatePrice*dataShipmentGoodsGrid[i].amount;
					dataShipmentGoodsGrid[i].estimatePricePercent=(dataShipmentGoodsGrid[i].estimatePriceSum/(vm.shipmentUpdate.totalOriginMoney))*100;
					if(dataShipmentGoodsGrid[i].amount==null||dataShipmentGoodsGrid[i].amount<=0){
						toastr.warning("Trường Số lượng nhập không hợp lệ");
						return;
					}
					if(dataShipmentGoodsGrid[i].estimatePrice==null||dataShipmentGoodsGrid[i].estimatePrice<=0){
						toastr.warning("Trường Định lượng cho 1 đơn vị hàng hóa nhập không hợp lệ");
						var grid = $("#shipmentGoodsDetailGrid").data("kendoGrid");
                        grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(5));
						return;
					}
					vm.shipmentUpdate.lstShipmentGoods.push(dataShipmentGoodsGrid[i]);
				}
					shipmentService.updateShipmentGoods(vm.shipmentUpdate).then(function(result){
							if(result.error){
								toastr.error(result.error);
								document.getElementById("dept").focus();
								return;
							}
            			toastr.success("Định lượng thành công!");
            			doSearch();
            			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
            			$("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.data(vm.goodsDataInGrid);
            			$("#shipmentGoodsDetailGrid").data("kendoGrid").refresh();
            		}, function(errResponse){
            			if (errResponse.status === 409) {
		                	toastr.error(gettextCatalog.getString("Mã lô hàng đã tồn tại"));
		                	$rootScope.$broadcast("shipment.object.file", false);
		                } else {
		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
		                	$rootScope.$broadcast("shipment.object.file", false);
		                }
    		        });
				}
			
	            	}
		//End
		//popup đơn vị
		vm.openDepartment=openDepartment;
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
		//End
		vm.commonSearch = {name: '', code: ''};
		vm.commonSearch1 = {fullName: '', employeeCode: ''};
        vm.gridCommon = [ {
			title: "Mã xxx",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
		vm.onSave=onSave;
		
		function onSave(data){
			vm.shipmentSearch.createdDeptName=data.text;
			vm.shipmentSearch.createdDeptId = data.id;
			$("#shipmentDept1").focus();
		}
		//Reset dữ liệu trên form nhập tìm kiếm
		vm.cancelDept = function()
		{
			vm.shipmentSearch.createdDeptName = undefined;
			vm.shipmentSearch.createdDeptId = undefined;
			$("#shipmentDept1").focus();
		}
		vm.cancelStatis = function()
		{
			vm.shipmentSearch.listStatus = null;
	$("#listStatus1").data("kendoMultiSelect").focus();
		}
		vm.cancelCreatedBy = function()
		{
			vm.shipmentSearch.createName = undefined;
		}
		vm.cancelUser=function(id){
			if(id==="fullName"){
				vm.shipmentSearch.fullName=null;
			}
		}
		//End
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.shipmentGoodsGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.shipmentGoodsGrid.showColumn(column);
            } else {
            	vm.shipmentGoodsGrid.hideColumn(column);
            }
        	
        	
        }
	
		// * * Filter các cột của select
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
            
            //AutoCompleteDeprt
            vm.headerTemplate4='<div class="dropdown-header row text-center k-widget k-header">' +
  	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Đơn vị</p>' +
  	      '<p class="col-md-6 text-header-auto">Tên Đơn vị</p>' +
  	      	'</div>';
			vm.selectedDept = false;
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
						if(processSearch("shipmentDept1",vm.selectedDept)){	
					vm.shipmentSearch.createdDeptId = null; // thành id
					 vm.shipmentSearch.createdDeptName = null;//thành name
					 vm.selectedDept = false;
				}
  	                if (e.sender.value() === '') {
  	                	 vm.shipmentSearch.createdDeptId = dataItem.id; // thành id
  	                	vm.shipmentSearch.createdDeptName = null;//thành name
  	                }
  	            },
  	            ignoreCase: false
  	        };
  		//End-AutocompleteDeprt
          
  		// Tải lên file export
  		vm.submit=submit;
        function submit(data){
        	if($("#file")[0].files[0] == null){
        		toastr.warning("Bạn chưa chọn file để import");
        		return;
        	}
        	if($("#file")[0].files[0].name.split('.').pop() !='xls' && $("#file")[0].files[0].name.split('.').pop() !='xlsx' ){
        		toastr.warning("Sai định dạng file");
        		return;
        	}
        	
        	if(vm.dataItemUpdate.totalOriginMoney==null||vm.dataItemUpdate.totalOriginMoney===0){
        		toastr.warning("Trường tổng nguyên giá lô hàng không được trống");
        		document.getElementById('totalOriginMoney1').focus();
        		return;
        	}
        	
	        var formData = new FormData();
			formData.append('multipartFile', $('#file')[0].files[0]);
	     return   $.ajax({
	            url: Constant.BASE_SERVICE_URL+"shipmentGoodsRsServiceRest/shipmentGoods/importQuantitative/"+ vm.importId +"?folder="+ vm.folder ,
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success:function(data) {
	            	if(data[data.length - 1].lstErrorGoods!=null && data[data.length - 1].lstErrorGoods.length!=0){
	            		vm.lstErrImport = data[data.length - 1].lstErrorGoods;
	            		vm.objectErr = data[data.length - 1];
	            		var teamplateUrl="wms/shipmentGoods/importResultPopUp.html";
	       			 	var title="Kết quả Import";
	       			 	var windowId="ERR_IMPORT";
	       			
	       			 	CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'60%','60%');
	       			 	fillDataImportErrTable(vm.lstErrImport);
	            	}else if(data.length===1){
					toastr.warning("File import không có dữ liệu");
					return;
					}else{
	            		toastr.success("Import thành công!");
	            		vm.dataFinal = [];
		            	data.splice(data.length - 1, 1);
		            	console.log(data);
		            	vm.goodsDataInGrid = $("#shipmentGoodsDetailGrid").data().kendoGrid.dataSource.data();
		            		for(var i=0;i<data.length;i++){
		            			for(var j=0;j<vm.goodsDataInGrid.length;j++){
		            				if(data[i].goodsCode === vm.goodsDataInGrid[j].goodsCode && data[i].goodsName === vm.goodsDataInGrid[j].goodsName){
		            					vm.goodsDataInGrid[j].estimatePrice = data[i].estimatePrice;
		            					vm.goodsDataInGrid[j].amount = data[i].amount;
		            					vm.goodsDataInGrid[j].estimatePriceSum=vm.goodsDataInGrid[j].estimatePrice*data[i].amount
		            					vm.goodsDataInGrid[j].estimatePricePercent=(vm.goodsDataInGrid[j].estimatePriceSum/(kendo.parseFloat(vm.dataItemUpdate.totalOriginMoney)))*100;
		            				}
		            			}
		            		}
		            	
		            		$("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.data(vm.goodsDataInGrid);
	            			$("#shipmentGoodsDetailGrid").data("kendoGrid").refresh();
		            	
	            	}
	            	
	            }
	        });
        }
        //End
    	setTimeout(function(){
  		  $("#shipmtg").focus();
  		},15);
    	//Close popup importERR
    	vm.closeErrImportPopUp=function(){
    		 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    	}
    	//End
    	//Đọc chữ số
    	vm.dso=function(totalOriginMoney){
		vm.docso.totalOriginMoney=	DocTienBangChu(kendo.parseFloat(totalOriginMoney));
				var grid = $("#shipmentGoodsDetailGrid").data("kendoGrid");
                    var gridData =  grid.dataSource.data();
                    if(gridData.length> 0){
                    	for (var i = 0; i < gridData.length; i++) {
							if(kendo.parseFloat(vm.shipment.totalOriginMoneyDisplay)>0){
		            		gridData[i].estimatePricePercent=(gridData[i].estimatePriceSum/(kendo.parseFloat(vm.shipment.totalOriginMoneyDisplay)))*100;
								grid.refresh();
									}	
									else{
									gridData[i].estimatePricePercent=null;
									grid.refresh();
									}
							 }
						}
		
		}
		// end
		vm.docso={};
		vm.DocTien = DocTien(vm.docso);
		// begin
		// doc tien
		function DocTien(dataItem){
			if(dataItem.totalOriginMoney != undefined&&dataItem.totalOriginMoney!=null){
				vm.docso.totalOriginMoney = DocTienBangChu(dataItem.totalOriginMoney);
			}
			if(dataItem.totalOriginMoney==null){
			vm.docso.totalOriginMoney = DocTienBangChu(dataItem.totalOriginMoney);
			}
			
		}
		// end
		//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		})
		//
			$("#shipmentGoodsIdSearch").on("keypress", function (e) {
            if (e.keyCode === 13) {
			$("#findParam1").click();
            }
            });
			//End
			//Enter
			$(document).on("keypress", function (e) {
         		switch(e.keyCode) {
         			case 27:
         				$("#cancel").click();
         				break;
         			case 13:
					if($(':focus').size()===0){
					$( "#savGoods" ).click(function( event ) {
							event.stopPropagation();
							});
							break;
					}
         				 if (e.keyCode == 13&& !$('#savGoods:focus').length&& !$('#cancel1:focus').length
						 &&!$('#confirmPopup_btnConfirm:focus').length &&!$('#confirmPopup_btnCancel:focus').length
						 &&!$('#file:focus').length&&!$('#btnFileGoods:focus').length&&!$('#linkFileGoods:focus').length) {
         				$("#savGoods").click();
         				break;
						}
         		}
         	} );

			//End
	}

})();
