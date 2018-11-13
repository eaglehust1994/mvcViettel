//
//(function() {
//	'use strict';
//	var controllerId = 'kiDonViController';
//	
//	angular.module('MetronicApp').controller(controllerId, kiDonViController);
//	
//	function kiDonViController($scope, $rootScope, $timeout, gettextCatalog, 
//			kendoConfig, $kWindow,
//			CommonService, PopupConst, Restangular, RestEndpoint,Constant, kiDonViService,
//			
//			) {
//		var vm = this;
//		vm.showSearch = true;
//		vm.validatorOptions = kendoConfig.get('validatorOptions');
//		vm.kiDvSearch = {};
//		
//		$(document).ready(function() {
//			//getApply();
//			fillDataKiDonViTable();
//			if($rootScope.stringtile){
//				vm.String=$rootScope.stringtile;
//				}
//		});
////		function getApply(){
////			kiDonViService.getApply(vm.appParamSearch).then(function(result){
////				 vm.dataAppParamType = result;	
////				 vm.reasonSearch.listApply=[vm.dataAppParamType[0].code];
//				 //fillDataTable();
////			 });
//		
//		initFormData();
//		function initFormData() {
//			fillDataKiDonViTable([]);
//			
//			if($rootScope.stringtile){
//				vm.String=$rootScope.stringtile;
//				}
//		}
//		
//		//table chinh
//		var record=0;
//		function fillDataKiDonViTable(data) {
//			vm.gridOptions = kendoConfig.getGridOptions({
//				autoBind: true,
//				resizable: true,	
//				columnMenu: false,
//				scrollable: false,
//				toolbar: [
//		                    {
//		                    	name: "actions",
//		                        template: '<div class=" pull-left">'+
//		                        '<button class="btn btn-qlk padding-search-right addQLK margin_right10"'+
//		      					'ng-click="vm.createNew()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
//		      					'</div>'	+
//		        				'<button class="btn btn-qlk padding-search-right TkQLK"'+
//		      					'ng-click="vm.sendToSign()" uib-tooltip="Import" translate>Trình ký</button>'+
//		      					'</div>'	
//		      					+
//		      					 
//							'<div class="btn-group pull-right margin_top_button margin_right10">'+
//	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
//	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
//		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
//		                    '<label ng-repeat="column in vm.exRegManaGrid.columns.slice(1,vm.exRegManaGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
//		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
//		                    '</label>'+
//		                    '</div></div>'
//		                    
//		                    }
//		                    ],
//		                    dataBound: function (e) {
//		    				    var grid = vm.kiDonViGrid;
//		    				    grid.tbody.find("tr").dblclick(function (e) {
//		    				        var dataItem = grid.dataItem(this);
//		    				        vm.showDetail(dataItem)
//		    				    });
//		    				},
//							dataBinding: function() {
//                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
//                },
//				dataSource: {
//					serverPaging: true,
//					 schema: {
//						 total: function (response) {
//						 
//						 $("#extRegCount").text(""+response.total);
//							 	vm.count = response.total;
//								return response.total; // total is returned in
//														// the "total" field of
//														// the response
//							},
//							data: function (response) {
//								var list=response.data;
//				        		for(var x in list){
//				        			for(var i in $scope.listCheck){
//				        				if(list[x].orderId===$scope.listCheck[i].orderId){
//				        					list[x].selected=true;
//				        				}
//				        			}
//				        		}
//				        		return list;// data is returned in
//														// the "data" field of
//														// the response
//							},
//		                },
//					transport: {
//						read: {
//		                        // Thuc hien viec goi service
//							url: Constant.BASE_SERVICE_URL + "tblDmKiDonViServiceRest/doSearchKiDonVi",
//							contentType: "application/json; charset=utf-8",
//							type: "POST"
//						},					
//						parameterMap: function (options, type) {
//						
//						vm.kiDvSearch.keySearch = "01/2018";
//						vm.kiDvSearch.page = options.page
//						vm.kiDvSearch.pageSize = options.pageSize                               
//
//								return JSON.stringify(vm.kiDvSearch)
//						}
//					},					 
//					pageSize: 10
//				} ,
//				noRecords: true,
//				messages: {
//					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
//				},
//				pageable: {
//					refresh: false,
//					 pageSizes: [10, 15, 20, 25],
//					messages: {
//		                display: "{0}-{1} của {2} kết quả",
//		                itemsPerPage: "kết quả/trang",
//		                empty: "Không có kết quả hiển thị"
//		            }
//				},
//				columns: [{
//					title : "<input type='checkbox' id='checkalllistEx' name='gridchkselectall' ng-click='vm.chkSelectAllForExq();' ng-model='vm.chkAllForExReq' />",
//					template: "<input type='checkbox' id='childcheckInExReq' name='gridcheckbox' ng-click='vm.handleCheckForExq(dataItem)' ng-model='dataItem.selected'/>",
//			        width: "5%",
//			        headerAttributes: {style:"text-align:center;"},
//					attributes:{style:"text-align:center;"}
//				},
//				{
//					title: "TT",
//					field:"stt",
//			        template: function () {
//					  return ++record;
//					 },
//			        width: "5%",
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:center;"
//					},
//				}
//				,  {
//					title: "Mã yêu cầu",
//					field: "code",
//			        width: "15%",
//					 template:  '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetail(dataItem)>#=code#</a>',
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:left;"
//					},
//				}, {
//					title: "Loại yêu cầu",
//			        field: "bussinessType",
//			        template: function($scope){
//						if($scope.bussinessType == vm.businessTypes[0].code){
//							return vm.businessTypes[0].name;
//						}else if($scope.bussinessType == vm.businessTypes[1].code){
//							return vm.businessTypes[1].name;
//						}else if($scope.bussinessType == vm.businessTypes[2].code){
//							return vm.businessTypes[2].name;
//						}else if($scope.bussinessType == vm.businessTypes[3].code){
//							return vm.businessTypes[3].name;
//						}else if($scope.bussinessType == vm.businessTypes[4].code){
//							return vm.businessTypes[4].name;
//						}else if($scope.bussinessType == vm.businessTypes[5].code){
//							return vm.businessTypes[5].name;
//						}else if($scope.bussinessType == vm.businessTypes[6].code){
//							return vm.businessTypes[6].name;
//						}else if($scope.bussinessType == vm.businessTypes[7].code){
//							return vm.businessTypes[7].name;
//						}else if($scope.bussinessType == vm.businessTypes[8].code){
//							return vm.businessTypes[8].name;
//						}else{
//							return vm.businessTypes[9].name;
//						}
//					},
//			        width: "10%",
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:left;"
//					},
//				}, {
//					title: "Kho xuất",
//			        field: "stockName",
//			        width: "15%",
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:left;"
//					},
//				},{
//					title: "Người tạo",
//			        field: "createdByName",
//			        width: "15%",
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:left;"
//					},
//				},
//				/*{
//					title: "Đơn vị tạo",
//			        field: "createdDeptedName",
//			        width: 150,
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:left;"
//					},
//				},*/  
//				{
//					title: "Ký CA",
//					 field: "signState",
//			        template :   "# if(signState == 1){ #" + "#= 'Chưa trình ký' #" + "# } " +
//			        "else if (signState == 2) { # " + "#= 'Đã trình ký' #"+ "#} " +
//			        "else if (signState == 3) { # " + "#= 'Đã ký' #"+ "#} " +
//			        "else if (signState == 4) { # " + "#= 'Đã từ chối' #"+ "#} " +
//
//			        		"#",
//			        width: "10%",
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:left;"
//					},
//				},  {
//					title: "Trạng thái",
//					 field: "status",
//				        template :  "# if(status == 1){ #" + "#= 'Chưa tạo phiếu' #" + "# } " +
//				        "else if (status == 2) { # " + "#= 'Đã tạo phiếu' #"+ "#} " +
//				        "else if (status == 3) { # " + "#= 'Đã nhập/xuất' #"+ "#} " +
//				        "else if (status == 4) { # " + "#= 'Đã hủy' #"+ "#} " +
//				        "else if (status == 5) { # " + "#= 'Đã từ chối' #"+ "#} " +
//				        		"#",
//			        width: "10%",
//			        headerAttributes: {
//						style: "text-align:center;"
//					},
//					attributes: {
//						style: "text-align:left;"
//					},
//				},{
//					title: "Thao tác",
//			        template: 
//					'<div class="text-center #=orderId#""> '	+
//		        	// '		<a   type="button"  class="#=orderId# icon_table" uib-tooltip="Khóa" translate>'+
//		        	// '			<i  style="color:grey" ng-hide="dataItem.status == 1 " class="fa fa-files-o" aria-hidden="true"></i> '+
//		        	// '		</a> '+
//				'		<button  style=" border: none; background-color: white;" ng-click=vm.copyzzz(dataItem)   class="icon_table" uib-tooltip="Sao chép" translate>'+
//	        	'			<i style="color:steelblue;" class="fa fa-files-o" aria-hidden="true"></i> '+
//	        	'		</button> '+
//	        	'		<button  style=" border: none; background-color: white;" ng-show="dataItem.status != 1 || dataItem.signState != 1" class="#=orderId# icon_table" uib-tooltip="Khóa" translate> '+
//	        	'			<i style="color:grey"  ng-show="dataItem.status != 1 || dataItem.signState != 1" class="fa fa-pencil"  aria-hidden="true"></i>'+
//	        	'		</button> '+
//	        	'		<button  style=" border: none; background-color: white;" ng-click=vm.update(dataItem) ng-show="dataItem.status == 1 && dataItem.signState == 1"  class="#=orderId# icon_table" uib-tooltip="Cập nhật" translate>'+
//	        	'			<i ng-show="dataItem.status == 1 && dataItem.signState == 1" class="fa fa-pencil" aria-hidden="true"></i> '+
//	        	'		</button>' +
//	        	'		<button  style=" border: none; background-color: white;" ng-show="dataItem.status != 1 || dataItem.signState != 1" class="#=orderId# icon_table" uib-tooltip="Khóa" translate> '+
//	        	'			<i style="color:grey"  ng-show="dataItem.status != 1 || dataItem.signState != 1" class="fa fa-trash" aria-hidden="true"></i>'+
//	        	'		</button> '+
//	        	'		<button  style=" border: none; background-color: white;" ng-click=vm.openRemovePopup(dataItem) ng-show="dataItem.status == 1 && dataItem.signState == 1" class="#=orderId# icon_table" uib-tooltip="Hủy bỏ" translate>'+
//	        	'			<i ng-show="dataItem.status == 1 && dataItem.signState == 1"  style="color:steelblue;" class="fa fa-trash" aria-hidden="true"></i> '+
//	        	'		</button>'
//					+'</div>', 
//			        width: "15%",
//			        field:"stt"
//				}]
//			});
//		}
//		
//		vm.doSearch =function() {
//			kiDonViService.doSearchKiDv().then(function(d) {
//				data = d.plain();
//				toastr.success(gettextCatalog.getString("Lỗi!"));
//			}).catch( function(){
//				toastr.error(gettextCatalog.getString("Lỗi!"));
//				return;
//			});
//	    } 
//		
//		
//		//end
//		}
//})();
//									
//				
