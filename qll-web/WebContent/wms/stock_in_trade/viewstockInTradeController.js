(function() {
	'use strict';
	var controllerId = 'viewstockInTradeController';
	
	angular.module('MetronicApp').controller(controllerId, viewstockInTradeController);
	
	function viewstockInTradeController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow, viewstockInTradeService,stockGoodService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        vm.doSearch= doSearch;
		vm.exportST = exportST;
      vm.exportCard=exportCard;
	  vm.cancel= cancel ;
	  vm.stockGoodsDetails={};
	  vm.refreshGrid=refreshGrid;
		
		vm.stockTradeSearch={
				 goodsState:0, 
		};
		vm.stockTrade={};
		
	
		vm.detailTradeSearch = {};

		vm.stockTradeSearchType = [{
			
			
		}];
		vm.stockTradeSearch.startDate=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy")
		vm.stockTradeSearch.listStockId=[];
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
      '<p class="col-md-6 text-header-auto">Tên</p>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};

		  vm.validatorOptions = kendoConfig.get('validatorOptions');
		$scope.IsHidden = true;
		// begin
		// click vào nút tìm kiếm
		function doSearch(){
		
			trimSpace();
			 if(!vm.validator.validate()){
				if($('div.k-widget.k-window.fix-footer').css('display')!=='block'){
					$("#listStockGoodsId").focus();
				}
				
				return;
			}
			if(vm.stockTradeSearch.listStockId){
				$scope.IsHidden =   false;
				fillDataTable([]);
				vm.showDetail = false;
				var grid = vm.stockGoodsGrid;	
			//
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10
					});
				}	
			}
	
		}
		// end
		
		// begin
		// Khoi tao du lieu cho form
		fillDataTable([]);
		if($rootScope.stringtile){
			vm.String=$rootScope.stringtile;
		}
		// end
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		$scope.listCheck=[];
		vm.handleCheck=handleCheck;
    	function handleCheck(dataItem){
    		if(dataItem.selected){
    		$scope.listCheck.push(dataItem);
    		} else {
    			for(var i=0;i<$scope.listCheck.length;i++){
    				if($scope.listCheck[i].stockGoodsTotalId===dataItem.stockGoodsTotalId){
    				$scope.listCheck.splice(i,1);
    				}
    			}
				$('[name="gridchkselectall"]').prop('checked', false);
    		}
    		}
    	vm.chkSelectAll=chkSelectAll;
		$scope.checkSearch=false;
		function chkSelectAll(){
			var grid = vm.stockGoodsGrid;
	    		chkSelectAllBase(vm.chkAll, grid);
			if(vm.chkAll){
				if( $scope.checkSearch && $scope.dataSearch.length >0){
				$scope.listCheck=$scope.dataSearch;
				} else {
				
					CommonService.getallData("stockGoodsTotalServiceRest/getCheckStockGood",vm.stockTradeSearch).then(function(data){
						$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}
		};
		
		vm.oldSearch={};
		vm.exportExcelGrid = function(){
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
			var ds = $("#stockGoodsGrid").data("kendoGrid").dataSource;
			var ds1 = ds.data();
			if (ds1.length == 0){
				 toastr.warning(gettextCatalog.getString("Không có dữ liệu"));
			}else{
			
				viewstockInTradeService.doSearchStockGood(vm.oldSearch).then(function(d) {
					CommonService.exportFile(vm.stockGoodsGrid,d.data,vm.listRemove,vm.listConvert,"Xem thông tin hàng tồn kho");
				});
			}
			
		}
		
	vm.listRemove=[{
			title: "Chi tiết hàng hóa",
		},
		{
			title : "<input type='checkbox' id='checkallliststockintrade' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />"
		}];
		vm.listConvert=[{
			field:"goodsState",
			data:{
				1:'Bình thường',
				2:'Hỏng'

		}
		}];

		// begin
		// Grid xem thông tin tồn kho
		var record=0;
		function fillDataTable() {
			vm.gridStocksGoodsOptions = kendoConfig.getGridOptions({
				autoBind: false,
				resizable: true,
				columnMenu: false,
				scrollable: false,
				noRecords: true,

				toolbar: [
                    {
                        name: "actions",
                        template: 
                        	 '<div class=" pull-left ">'+
                        	 '<button class="btn btn-qlk padding-search-right  noteQLK width125"'+
 	      					'ng-click="vm.exportST()" uib-tooltip="Xuất thẻ kho" translate>Xuất thẻ kho</button>'+
           					'</div>'
 	      					+
							
							'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.stockGoodsGrid.columns.slice(1,vm.stockGoodsGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'						
             			
                    }
                    ],
                    dataBound: function (e) {
    				    var grid = vm.stockGoodsGrid;
    				    grid.tbody.find("tr").dblclick(function (e) {
    				        var dataItem = grid.dataItem(this);
    				        vm.seeDetail(dataItem)
    				    });
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
							 	vm.count =response.total
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {
								var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].stockGoodsTotalId===$scope.listCheck[i].stockGoodsTotalId){
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
							url: Constant.BASE_SERVICE_URL + "stockGoodsTotalServiceRest/doSearchStockGood",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {

							    vm.stockTradeSearch.page = options.page;
								vm.stockTradeSearch.pageSize = options.pageSize;
								vm.oldSearch=angular.copy(vm.stockTradeSearch);
								return JSON.stringify(vm.stockTradeSearch);

						}
					},					 
					pageSize: 10
				},
				// dataSource: data,
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
					title : "<input type='checkbox' id='checkallliststockintrade' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
					template: "<input type='checkbox' id='childcheck' name='gridcheckbox' ng-click='vm.handleCheck(dataItem)' ng-model='dataItem.selected'/>",
			        width: "5%",
			        headerAttributes: {style:"text-align:center;"},
					attributes:{style:"text-align:center;"}
				},{
					title: "TT",
					field:"stt",
			        width: "5%",
			        template: function () {
						  return ++record;
						 },
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Kho",
					field: 'stockName',
			        width: "15%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Loại hàng hóa",
			        field: 'goodsTypeName',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã hàng",
			        field: 'goodsCode',
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Tên hàng",
			        field: 'goodsName',
			        width: "20%",
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
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;"
					},
				}
				, {
					title: "Tình trạng",
					field: 'goodsState',
			        template :  "# if(goodsState == 1){ #" + "#= 'Bình thường' #" + "# } " + "else if (goodsState == 2) { # " + "#= 'Hỏng' #"+ "#} #",
			        width: "10%",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					
				},{
					title:  'Chi tiết hàng hóa',
					field: 'gtt',
			        template :  '<a  href="javascript:void(0);" ng-click=vm.seeDetail(dataItem)  style="text-decoration: underline;">Chi tiết</a>',
			        width: "15%",
			        headerAttributes: {
						style: "overflow: hidden; text-overflow: ellipsis;"


					},
					attributes: {
						style: "text-align:center;"
					}
				}]
			});
		}
		// end
		
		function refreshGrid(d) {
			var grid = vm.stockGoodsGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.listMess={
				errMessage:"",
				errMessage1:""
			}
		/// validate ngay thang
		vm.validateDate = validateDate;
		function validateDate(startDate,endDate){
			var startDate = $('#startDateFromExportCard').val();
    		var endDate = $('#endDatefrom').val();
			var d = new Date();
			var curDate = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() ;
					if(endDate!=""){
						$('#ex2').text("");
						$('#ex1').text("");
						if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
							if(kendo.parseDate(endDate,"dd/MM/yyyy")<= d){
								vm.listMess.errMessage='';
							}else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
								vm.listMess.errMessage='';
							}
							vm.listMess.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
							$('#startDateFromExportCard').focus();
							return vm.listMess;
						}else if(kendo.parseDate(endDate,"dd/MM/yyyy") > d){
							$('#ex1').text("");
							if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
								if(kendo.parseDate(startDate,"dd/MM/yyyy")<=d){
									vm.listMess.errMessage1='';
								}else if(kendo.parseDate(startDate,"dd/MM/yyyy")>d){
									vm.listMess.errMessage1='Ngày tạo phải nhỏ hơn bằng ngày hiện tại'
								}
							}
						    vm.listMess.errMessage = 'Ngày đến phải nhỏ hơn bằng ngày hiện tại';
						    $('#endDatefrom').focus();
							 return vm.listMess;
					}}else if(kendo.parseDate(startDate,"dd/MM/yyyy") > d){
						$('#ex1').text("");
						$('#ex2').text("");
						   vm.listMess.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày hiện tại';
						   return vm.listMess;
					}
					if(!vm.validator.validate()){
						if($('#ex1').text()!=""){
							vm.listMess.errMessage1='';
							vm.listMess.errMessage=''
							$('#startDateFromExportCard').focus();
								return false;
						}else if($('#ex2').text()!=""){
							vm.listMess.errMessage1='';
							vm.listMess.errMessage='';
							$('#endDatefrom').focus();
								return false;
						} 
					}
						vm.listMess.errMessage = '';
						vm.listMess.errMessage1 = '';
						return vm.listMess;
		}
		// begin
		// popup xuất thẻ kho
		function exportST(){
			 var grid = $("#stockGoodsGrid").data("kendoGrid");
             var gridData =  grid.dataSource.view();
			 if(gridData.length == 0){
				toastr.warning("Không có dữ liệu xuất thẻ kho!");
				$("#listStockGoodsId").focus();
					return;
			 }
			vm.listMess.errMessage1="";
			vm.listMess.errMessage="";
			 vm.stockTradeSearch.startDate=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy") 
			 var teamplateUrl="wms/stock_in_trade/exportCardStockPopup.html";
			 var title="Xuất thông tin thẻ kho";
			 var windowId="VIEW_STOCK_IN_TRADE"
			 if ($scope.listCheck.length == 0) {
				//if (grid.select() == null || grid.select().length === 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
					return;
				//}
			}
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'50%','30%'); 
		} 
		// end
		
		//begin
		//xuất thẻ
		function exportCard(){
			if(vm.stockTradeSearch.startDate==null||vm.stockTradeSearch.endDate==""){
				if(!vm.validator.validate()){
						vm.listMess.errMessage = '';
						vm.listMess.errMessage1 = '';
					
						$("#startDateFromExportCard").focus();
				}
				return false;
			}
			if(!vm.validator.validate()){
				if($('#ex1').text()!=""){
					vm.listMess.errMessage1='';
					vm.listMess.errMessage=''
					$('#startDateFromExportCard').focus();
						return false;
				}else if($('#ex2').text()!=""){
					vm.listMess.errMessage1='';
					vm.listMess.errMessage='';
					$('#endDatefrom').focus();
						return false;
				} 
				
			}
			if(vm.listMess.errMessage1!=""){
				$("#startDateFromExportCard").focus();
				return false;
			}else if(vm.listMess.errMessage!=""){
				$('#endDatefrom').focus();
					return false;
			}
			
			var obj={};
			obj.listStockDailyRemain=$scope.listCheck;
			obj.startDate=vm.stockTradeSearch.startDate;
			obj.endDate=vm.stockTradeSearch.endDate;
			viewstockInTradeService.exportCard(obj).then(function(result){
				if(result.error){
				toastr.error(result.error);
				return;
				}
			
    			toastr.success("Export thành công!");
    			 window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + result.fileName;
    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi xuất thẻ"));
	        });
			

		}
		//end
		
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
		
		
	
		function cancel(){
			vm.stockTradeSearch.startDate=null;
			vm.stockTradeSearch.endDate=null;
			 
		}
		//begin
		//huy bo popup xuat thong tin the kho
		vm.remove = remove;
			function remove(){
			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				
			}	
		//end
		

		//begin
		//xem thông tin chi tiết
		vm.seeDetail = function seeDetail(dataItem){
	    	var dataItem=dataItem;
	    	vm.stockGoodsDetails = dataItem;
	        var teamplateUrl="wms/stockGood/viewDetailTrade.html";
	        var title="Thông tin chi tiết hàng hóa";
			$kWindow.open({
				 options: {
					 modal: true,
					 title: title,
					 visible: false,
					 width: '80%',
					 height: '40%',
					 actions: ["Minimize", "Maximize", "Close"],
					 open: function () {
						 this.wrapper.children('.k-window-content').addClass("fix-footer");
						 $rootScope.$broadcast("stockintrade.object.data", dataItem);
					 }
				 },                
				 templateUrl: teamplateUrl
			});
		}
		// end
	
		
		// 1.show/hide column field
		// start
		vm.showHideColumn=function(column){
		
        	if (angular.isUndefined(column.hidden)) {
        		vm.stockGoodsGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.stockGoodsGrid.showColumn(column);
            } else {
            	vm.stockGoodsGrid.hideColumn(column);
            }
        }
		
		vm.gridColumnShowHideFilter = function (item) { 
            return item.type==null||item.type !=1; 
        };
		// end
		// begin
		// xóa loại hàng hóa
		vm.cancelStock = function(id) {
			if(id==="deleteStockGoods"){
				 $('#listStockGoodsType1').data("kendoMultiSelect").value([]);
				 vm.stockTradeSearch.listGoodsType=null;
			}
			if(id==="clearGoods"){
				 vm.stockTradeSearch.goodsName="";
			}
		}// end
		
		// AutocompleteGoods
		// begin
		// hi?n th? danh sách hàng hóa
        vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
        '<p class="col-md-6 text-header-auto border-right-ccc">Mã Hàng Hóa</p>' +
        '<p class="col-md-6 text-header-auto">Tên Hàng Hóa</p>' +
        	'</div>';
		vm.goodsOptions = {
	            dataTextField: "name",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	              vm.stockTradeSearch.goodsCode = dataItem.code; // thành id
	              vm.stockTradeSearch.goodsName = dataItem.name;// thành name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("orderGoodsServiceRest/orderGoods/" + 'getForAutoComplete').post({name:vm.stockTradeSearch.goodsName,pageSize:vm.goodsOptions.pageSize}).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.code #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.stockTradeSearch.goodsCode = null; // thành id
	                	vm.stockTradeSearch.goodsName = null;// thành name
	                }
	            },
	            ignoreCase: false
	        };
			// end
		
		// multiselect
		// begin
		// danh sách loại hàng hóa
		$("#listStockGoodsType1").kendoMultiSelect({
			
			dataSource:{
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
						url: Constant.BASE_SERVICE_URL + "goodsTypeRsServiceRest/getList",
						contentType: "application/json; charset=utf-8",
						type: "POST"
					},					
					parameterMap: function (options, type) {
					    vm.stockTradeSearch.page = options.page
						vm.stockTradeSearch.pageSize = options.pageSize

						return JSON.stringify(vm.stockTradeSearch)
					}
				},					 
				pageSize: 20,
				
			},
			dataTextField: "name",
			 dataValueField: "goodsTypeId",
			 optionLabel: {
				 goodsTypeId: ""
			    },
		});
		
		
		 //Enter
		$(document).on("keydown", function (e) {
				if (e.keyCode === 13) {
if($('div.k-widget.k-window.fix-footer').css('display')!=="block"){
					$("#findStockGood").click();
				}
}
				});
				
				$("#findStockGood").on("keypress", function (e) {
			if (e.keyCode === 13) {
				$("#findStockGood").focus();
				$("#findStockGood").click();
				}
		});
			//End  
    	//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		});
	}
})();
