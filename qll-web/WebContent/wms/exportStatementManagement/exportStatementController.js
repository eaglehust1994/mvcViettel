(function() {
	'use strict';
	var controllerId = 'export';
	
	angular.module('MetronicApp').controller(controllerId,  exportManageController);

	function exportManageController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,exStaManaService,createExportRequestManageService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant)  {
		/** Content* */
		// 0.attributes
		// 1. multiple select (dropdown)
		// 2. tables for popup (Nguoi tao va kho nhap)
				// 2.1.danh sách kho nhập (pop-up) // 2.2.danh sách kho nhập
				// (pop-up)
				// 2.3. danh sach don vi(pop-up)
		// 3. initdata
			// 3.1 data search //3.2 Lập phiếu trình ký //3.3 Chi tiết hàng hóa
			// //3.4 Đơn vị quản lý //3.5 Danh sách hàng hóa
		// 4. complex popups - including tables and inputs
				// 4.1 tabs //4.2 complex popups
		// 5.validator
		// 6.show/hide column fields
		// 7.cancel input data
		// 8. Show detail from result row
		// 9.crud data
		// 10.dateTime
		// 11.show/hide panels
		// 12.autocomplete
		// 13.close/cancel popup
		// 14.searching
		// 15.treeView
		// 16.delete result
		// 17.refresh grid
		// 18.export
		// sign
		// 19.rEx
		// showpopup (only with data)
		
		/** Real content* */
		// 0.attributes
		var vm = this;
		vm.errorDate=false;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.folder = '';
		vm.showTabOneNoteDetail = true;
		vm.showTabTwoNoteDetail = false;
		vm.showTabThreeNoteDetail = false;
		vm.stockTrans={};
		vm.stockTransSearch={};
		vm.stockTransSearch.listStockId = [];
		vm.stockTransUpdateSearch={};
		vm.stockTransDetailSearch ={};
		vm.stockTransDetailSerialSearch = {};
		vm.stockTransSerialSearch = {};
		vm.orderGoodsSearch = {};
		//vm.stockTransSearch.createdBy=Constant.user.vpsUserToken.sysUserId; 
		//vm.stockTransSearch.createdByName=Constant.user.vpsUserToken.fullName;
		vm.disableImport = false;
		
		// su kien an nut enter tim kiem	
		$(document).on("keydown", function (e) {
        if (e.keyCode === 13) {
        	$("#findExtState").click();
        }
        });
		
		vm.gotoTabOnePopUp = function(){
			vm.showTabOne = true;
			vm.bt1 = false; vm.bt2 = true; vm.bt3 = true; vm.bt4 = false;
			
		}
		vm.gotoTabTwoPopUp = function(){
			vm.showTabOne = false;
			vm.bt1 = true; vm.bt2 = false; vm.bt3 = false; vm.bt4 = true;
		}
		vm.stockTransSearch={
				listStatus:['1'],
				listSignState : ["1"],
				createdByName : Constant.user.vpsUserToken.fullName,
				createdBy : Constant.user.vpsUserToken.sysUserId,
				listStockId : [],
				createdDeptId:Constant.user.VpsUserInfo.departmentId,
				createdDeptName:Constant.user.VpsUserInfo.departmentName
		};
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
    		},{
			    field: "bussinessType",
			    data:{
			    	
			    	1:'Xuất cho đơn vị sử dụng',
					2:'Xuất ra công trình',
					3:'Xuất đi BHSC',
					4:'Xuất luân chuyển kho',
					
					5:'Xuất tặng đối tác',
					6:'Xuất cho đối tác mượn',
					7:'Xuất trả đối tác',
					8:' Xuất thanh lý',
					
					9:'Xuất cho đề tài/dự án',
					10:'Xuất bán'
			    }
			}];
		vm.stockTransSearch.createdDateFrom=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy")
		
		
		vm.clearSearchCreatedDeptName = function(){
			$("#expStateCreDept").val("");
			vm.stockTransSearch.createdDeptName = null;
			vm.stockTransSearch.createdDeptId = null;
		}
		vm.clearKeySearch=function(){
			vm.stockTransSearch.keySearch=null;
		}
		vm.clearOrderCode=function(){
			vm.stockTransSearch.orderCode=null;
		}
		vm.clearSearchStatus=function(){
			vm.stockTransSearch.listStatus=[];
		}
		vm.clearSearchDate=function(){
			vm.stockTransSearch.createdDateTo=null;
		
			vm.stockTransSearch.createdDateFrom=null;
		}		
		vm.clearListSignState=function(){
			vm.stockTransSearch.listSignState=[];
		}
		
		setTimeout(function(){
			  $("#stockTransCode").focus();
			},15);
			
		vm.appParams={};
		vm.appParams.parType = 'EXPORT_ORDER_TYPE';
		vm.appParams.page = 1;
		vm.appParams.pageSize = 10;
		exStaManaService.getForExtOrderCheckboxes(vm.appParams).then(function(d) {
        	vm.businessTypes = d.data;
        
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
		// 1. multiple select (dropdown)
		$("#ns").kendoMultiSelect().data("kendoMultiSelect");
		$("#cast").kendoMultiSelect().data("kendoMultiSelect");
		

		// 2. tables for popup (Nguoi tao & Kho nhap)
					// 2.1.danh sách kho nhập (pop-up)
					   vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
						vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
				      '<p class="col-md-6 text-header-auto border-right-ccc">Mã kho</p>' +
				      '<p class="col-md-6 text-header-auto">Tên kho</p>' +
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
		
					// 2.2.danh sach nguoi tao (pop-up)
						// Users
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
					
					// 2.3
					vm.openDepartment=function(){
						var obj={};
//						obj.page=1;
//						obj.pageSize=20;
						CommonService.getDepartment(obj).then(function(result){
							var templateUrl = 'wms/popup/findDepartmentPopUp.html';
							var title = gettextCatalog.getString("Đơn vị");
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
			// SignVoffice
					vm.rowIndex=null;
					vm.sendToSign = function(){
						var teamplateUrl="wms/popup/SignVofficePopup.html";
						 var title="Trình ký phiếu xuất kho";
						 var windowId="ORDER";
						 vm.nameToSign="Danh sách phiếu trình ký"+"("+$scope.listCheck.length+")";
						 var selectedRow = [];
						 var err="";
							for(var i =0;i< $scope.listCheck.length;i++){
								if(Constant.userInfo.VpsUserInfo.sysUserId !== $scope.listCheck[i].createdBy){
										if(err===""){
											err="Bạn không có quyền trình ký văn bản có mã :" +$scope.listCheck[i].code;
										} else {
										err=err+", "+$scope.listCheck[i].code;
										}
								} else {
									selectedRow.push($scope.listCheck[i].stockTransId);
								}
								
							}
							if(err!==""){
								toastr.error(err);
									return;
							}
							if ($scope.listCheck.length === 0) {
								//if (grid.select() == null || grid.select().length === 0) {
									toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
									return;
								//}
							}
							var obj={};
							obj.listId=selectedRow;
							obj.type="04";
							obj.reportName="PhieuXuatKho_KhongSerial";
							CommonService.getDataSign(obj).then(function(data){
								if(data.error){
								toastr.error(data.error);
								return;
								}
							var dataList=data.plain();
							CommonService.populatePopupVofice(teamplateUrl,title,'01',dataList,vm,windowId,false,'85%','85%');
					
							
					});
					}
		
		// 3.initdata
		initFormData();
		function initFormData() {
			fillDataTable([]);
			//fillDataTableGoodsDetail([]);
			erroTable([]);
			stockTranUpdateGrid([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
					// 3.1 data search
					var record =0;
					vm.oldSearch={};
					function fillDataTable(data) {
						vm.gridOptions = kendoConfig.getGridOptions({
							autoBind: true,
							resizable: true,	
							toolbar: [
			                    {
			                        name: "actions",
			                        template:
			        				'<div class=" pull-left ">'+
			        				'<button class="btn btn-qlk padding-search-right TkQLK"'+
			      					'ng-click="vm.sendToSign()" uib-tooltip="Trình ký" translate>Trình ký</button>'+
			      					
			        				'<button type="button" id="xbk"	class="margin-left10 btn btn-qlk padding-search-right excelExportQLK" '+
			      					'ng-click="vm.exportstockTranEX()" uib-tooltip="Xuất bảng kê" translate>Xuất bảng kê</button>'+
			      					
			        				'<button type="button" id="xbbbbg" class="margin-left10 btn btn-qlk padding-search-right mapQLK"'+
			      					'ng-click="vm.exportOrderWithSerial()" uib-tooltip="Xuất BBBG" translate>Xuất BBBG</button>'+
			      					'</div>'	
			      					+
			                      	  '<div class="btn-group pull-right margin_top_button margin10">'+
					                    '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
					                    '<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
					                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
					                    '<label ng-repeat="column in vm.exGrid.columns.slice(1,vm.exGrid.columns.length) | filter: vm.gridColumnShowHideFilter">'+
							            '<input type="checkbox" checked="column.hidden"  ng-click="vm.showHideColumn(column)"> {{column.title}}'+
					                    '</label>'+                    
					                    '</div></div>'
			                    }
			                    ],
			                    dataBound: function (e) {
			    				    var grid = vm.exGrid;
			    				    grid.tbody.find("tr").dblclick(function (e) {
			    				        var dataItem = grid.dataItem(this);
			    				        vm.showNoteDetail(dataItem)
			    				    });
			    				},
			                
							dataSource: {
								serverPaging: true,
								 schema: {
									 total: function (response) {
									 $("#extStateCount").text(""+response.total);
											return response.total; // total is
																	// returned
																	// in the
																	// "total"
																	// field of
																	// the
																	// response
										},
										data: function (response) {
											var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].stockStransId===$scope.listCheck[i].stockStransId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list; // data is
																	// returned
																	// in the
																	// "data"
																	// field of
																	// the
																	// response
										},
					                },
								transport: {
									read: {
					              		url: Constant.BASE_SERVICE_URL + "stockTransServiceRest/stockTrans/doSearchExportStatement",
										contentType: "application/json; charset=utf-8",
										type: "POST"
									},					
									parameterMap: function (options, type) {
										    vm.stockTransSearch.page = options.page
											vm.stockTransSearch.pageSize = options.pageSize
											vm.oldSearch=angular.copy(vm.stockTransSearch);
											return JSON.stringify(vm.stockTransSearch)
									}
								},					 
								pageSize: 10
							} ,
							columnMenu: false,
							scrollable: false,
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
							dataBinding: function() {
								record = (this.dataSource.page() -1) * this.dataSource.pageSize();
							},
							columns: [
								{title : "<input type='checkbox' id='chkSelectAll' name='chkSelectAll' ng-click='vm.chkSelectAll()' ng-model='vm.chkAll' />",
									 headerAttributes: {style:"text-align:center;"},
									 template : "<input type='checkbox' name='gridcheckbox' ng-click='vm.handleCheck(dataItem)' ng-mode='dataItem.selected'/>",
									 attributes:{style:"text-align:center;"},
									 width : "5%" },
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
										, 
								{
									title: "Mã yêu cầu",
									field: 'orderCode',
									template: '<a class="#=orderId#" href="javascript:void(0);" ng-click=vm.showDetailOrder(dataItem)>#=orderCode#</a>',
							        width: "10%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Mã phiếu",
									field: 'code',
									template: '<a class="#=stockTransId#" href="javascript:void(0);" ng-click=vm.showNoteDetail(dataItem)>#=code#</a>',
							        width: "10%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Loại yêu cầu",
							        field: 'bussinessTypeName',
							        width: "10%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Kho xuất",
							        field: 'stockName',
							        width: "15%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Người tạo",
									 field: 'createdByName',
							        width: "15%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Trạng thái",
									 field: 'status',
							        width: "7%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
									template: function($scope){
										if($scope.status == 1){
											return "Chưa xuất";
										}else if($scope.status == 2){
											return "Đã xuất";
										}else if($scope.status == 3){
											return "Đã hủy";
										}
									},
									
								},  {
									title: "Tình trạng ký CA",
									 field: 'signState',
							        width: "10%",
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
									 template:
								        	'<div class="text-center #=stockTransId#""> '	+
								        	'		<button style=" border: none; background-color: white;" ng-hide="dataItem.status == 1 " class="#=stockTransId# icon_table" uib-tooltip="Khóa" translate>'+
								        	'			<i  style="color:grey" ng-hide="dataItem.status == 1 " class="fa fa-upload" aria-hidden="true"></i> '+
								        	'		</button> '+
										'		<button style=" border: none; background-color: white;" ng-click=vm.rEx(dataItem) ng-hide="dataItem.status != 1 " class="#=stockTransId# icon_table" uib-tooltip="Thực xuất" translate>'+
							        	'			<i  ng-hide="dataItem.status != 1 " style="color:steelblue;" class="fa fa-upload"  aria-hidden="true"></i> '+
							        	'		</button> '+
							        	'		<button style=" border: none; background-color: white;" ng-hide="dataItem.status == 1 " class="#=stockTransId# icon_table" uib-tooltip="Khóa" translate> '+
							        	'			<i style="color:grey"  ng-hide="dataItem.status == 1 " class="fa fa-pencil" aria-hidden="true"></i>'+
							        	'		</button> '+
							        	'		<button  style=" border: none; background-color: white;" ng-click=vm.edit(dataItem) ng-hide="dataItem.status != 1 " class="#=stockTransId# icon_table" uib-tooltip="Cập nhật" translate>'+
							        	'			<i ng-hide="dataItem.status != 1"   class="fa fa-pencil" aria-hidden="true"></i> '+
							        	'		</button>' +
							        	'		<button style=" border: none; background-color: white;" ng-hide="dataItem.status == 1 "  class="#=stockTransId# icon_table" uib-tooltip="Khóa" translate> '+
							        	'			<i style="color:grey"  ng-hide="dataItem.status == 1 " class="fa fa-trash" aria-hidden="true"></i>'+
							        	'		</button> '+
							        	'		<button style=" border: none; background-color: white;" ng-click=vm.openRemove(#=stockTransId#) ng-hide="dataItem.status != 1" class="#=stockTransId# icon_table" uib-tooltip="Hủy phiếu" translate>'+
							        	'			<i ng-hide="dataItem.status != 1" style="color:steelblue;" class="fa fa-trash" aria-hidden="true"></i> '+
							        	'		</button>'
											+'</div>',  
							        width: "13%",
							        field:"stt",
									headerAttributes: {
										style: "text-align:center;"
										},
									attributes: {
										style: "text-align:left;"
										}
								}]	
							
						});
					}
					
					// 3.3 Chi tiết hàng hóa
					function fillDataTableGoodsDetail(data){
						 var dataSource = new kendo.data.DataSource({
						pageSize: 10,
						data: data,
						autoSync: false, 
					});
						vm.goodsDetailForNoteGrid3Options = kendoConfig.getGridOptions({
							autobind: false,
							sortable: true,
			                 //filterable: true,
			                 columnMenu: false,
							 dataSource: dataSource,
								noRecords: true,
								messages: {
									noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
								},
								pageable: {
									refresh: false,
									 pageSizes: [10, 15, 20, 25],
									 pageSize: 10,
									messages: {
						                display: "{0}-{1} của {2} kết quả",
						                itemsPerPage: "kết quả/trang",
						                empty: "Không có kết quả hiển thị"
						            }
								},
								pageSize : 10,
								columns: [
								{
									title: "TT",
									field:"stt",
							        template: dataItem => $("#goodsDetailForNoteGrid3").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
							        width: "10%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:center;"
									},
								}
								, {
									title: "Serial",
									field: 'serial',
							        width: "20%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Mã hợp đồng",
							        field: 'contractCode',
							        width: "20%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}, {
									title: "Part number",
							        field: 'partNumber',
							        width:  "10%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Hãng sản xuất",
									 field: 'manufacturerName',
							        width: "15%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								},  {
									title: "Nước sản xuất",
									 field: 'producingCountryName',
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
							        width: "10%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:left;"
									},
								}]
						});
					}	
					// 3.4 Danh sách hàng hóa xem chi tiet
					 
					 function fillDataTableGoodsListForDetail(data) {

						 var dataSource = new kendo.data.DataSource({
						pageSize: 10,
						data: data,
						autoSync: false, 
						schema: {
							model: {
								//id: "stockTranUpdateGrid",
								fields: {
									stt: {editable: false},
									goodsCode: {editable: false},
									goodsName: {editable: false},
									goodsUnitName: {editable: false},
									goodsStateName : {editable: false},
								}
							}
						}
					});
					
					
						vm.goodsListForDetailGridOptions = kendoConfig.getGridOptions({
						    change: function() {
						          var gridDetails = $("#goodsListForDetailGrid").data("kendoGrid");
						          var dataItem = this.dataItem(this.select());  
			          // document.getElementById('goodName').innerHTML = dataItem.goodsName;
					  	 // document.getElementById('goodsStateName').innerHTML = dataItem.goodsStateName;
						 
						          vm.stockTransDetail=dataItem;
						          fillDataTableGoodsDetail(dataItem.listDetailSerial);
						          //var gridDetailsSerial = $("#goodsDetailForNoteGrid3").data("kendoGrid");
						         // gridDetailsSerial.dataSource.data(dataItem.listDetailSerial);
						         // gridDetailsSerial.refresh();
						    },

						    	dataBound: function(e) {
							    							   var tr = $("#goodsListForDetailGrid").data("kendoGrid").select("tr:eq(0)");
						    	},
							autoBind: true,
							resizable: true,
							selectable: true,
							columnMenu: false,
							dataSource: dataSource,
							noRecords: true,
							pageSize : 10,
							messages: {
								noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
							},
							pageable: {
								refresh: false,
								 pageSizes: [10, 15, 20, 25],
								 pageSize : 10,
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
						        template: dataItem => $("#goodsListForDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        width: "10%",
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
						        width: "25%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}, {
								title: "Tên hàng",
						        field: 'goodsName',
						        width: "25%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}, {
								title: "Đơn vị tính",
						        field: 'goodsUnitName',
						        width: "15%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							},  {
								title: "Số lượng",
								 field: 'amountReal',
						        width: "10%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:right;"
								},
							},  {
								title: "Tình trạng",
								field: 'goodsStateName',
								width: "15%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								}
								}]
						});
					}
					 
					// 3.5 Danh sách hàng hóa
					function fillDataTableGoodsListForNote(data) {

						 var dataSource = new kendo.data.DataSource({
						pageSize: 10,
						data: data,
						autoSync: false, 
						schema: {
							model: {
								id: "stockTranUpdateGrid",
								fields: {
									stt: {editable: false},
									goodsCode: {editable: false},
									goodsName: {editable: false},
									goodsUnitName: {editable: false},
									
									
									goodsStateName : {editable: false},
								}
							}
						}
					});
					
					
						vm.goodsListForNoteGridOptions = kendoConfig.getGridOptions({
						    change: function() {
						          var gridDetails = $("#goodsListForNoteGrid").data("kendoGrid");
						          var dataItem = this.dataItem(this.select());  
			          // document.getElementById('goodName').innerHTML = dataItem.goodsName;
					  	 // document.getElementById('goodsStateName').innerHTML = dataItem.goodsStateName;
						 
						          vm.stockTransDetail=dataItem;
						          fillDataTableGoodsDetail(dataItem.listDetailSerial);
						          //var gridDetailsSerial = $("#goodsDetailForNoteGrid3").data("kendoGrid");
						         // gridDetailsSerial.dataSource.data(dataItem.listDetailSerial);
						         // gridDetailsSerial.refresh();
						    },

						    	dataBound: function(e) {
							    							   var tr = $("#goodsListForNoteGrid").data("kendoGrid").select("tr:eq(0)");
						    	},
							autoBind: true,
							resizable: true,
							selectable: true,
							columnMenu: false,
							scrollable:false,
							dataSource: dataSource,
							noRecords: true,
							pageSize : 10,
							messages: {
								noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
							},
							pageable: {
								refresh: false,
								 pageSizes: [10, 15, 20, 25],
								 pageSize : 10,
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
						        width: "10%",
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
						        width: "25%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}, {
								title: "Tên hàng",
						        field: 'goodsName',
						        width: "25%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							}, {
								title: "Đơn vị tính",
						        field: 'goodsUnitName',
						        width: "10%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								},
							},  {
								title: "Số lượng",
								 field: 'amountReal',
						        width: "10%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:right;"
								},
							},  {
								title: "Tình trạng",
								field: 'goodsStateName',
								width: "10%",
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:left;"
								}},
								{
									title: "Chi tiết hàng hóa",
									field: 'stt',
							        width: "10%",
							        headerAttributes: {
										style: "text-align:center;"
									},
									attributes: {
										style: "text-align:center;"
									},
									template: "# if(isSerial == 1){ #" + "<a  ng-click=caller.showSerialDetailNote(dataItem)>#= 'Chi tiết' #</a>" + "# } " + "else if (isSerial == 0) { # " + "#= '' #"+ "#} #",
//dataItem => '<a href="javascript:void(0);" ng-click=caller.showSerialDetailNote(dataItem)>Xem chi tiết</a>',
								  
							}]
						});
					}
					 
					 
				
				// 3.6 SERIAL
				function fillDataTableGoodsSerialList(data){
				var dataSource = new kendo.data.DataSource({
						pageSize: 10,
						data: data,
						autoSync: false, 
					});
					vm.goodsDetailForNoteGrid4Options = kendoConfig.getGridOptions({
						autobind:true,
						sortable: true,
		                 filterable: true,
		                 columnMenu: true,
						 dataSource: dataSource,
							noRecords: true,
							messages: {
								noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
							},
							pageSize:10,
							pageable: {
								refresh: false,
								pageSize:10,
								 pageSizes: [10, 15, 20, 25],
								 messages:{
								 display: "{0}-{1} của {2} kết quả",
						                itemsPerPage: "kết quả/trang",
						                empty: "Không có kết quả hiển thị"
							}
							},
							columns: [
							{
								title: "TT",
								field:"stt",
						        template: dataItem => $("#goodsDetailForNoteGrid4").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        width: 90,
						        headerAttributes: {
									style: "text-align:center;"
								},
								attributes: {
									style: "text-align:center;"
								},
							}
							, {
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

				vm.stockTransDetail = {};
				vm.showSerialDetail = showSerialDetail;
				function showSerialDetail (dataItem) {
					var teamplateUrl="wms/exportStatementManagement/serialsPopup.html";
					 var title="Chi tiết hàng hóa";
					 var windowId="STOCK_TRANS_2";
					 vm.stockTransDetail=dataItem;
					 fillDataTableGoodsSerialList(dataItem.listDetailSerial);
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTransDetail,vm,windowId,false,'95%','85%');
				}
				
				vm.showSerialDetailNote=function(dataItem){
					var teamplateUrl="wms/exportStatementManagement/serialsPopup.html";
					 var title="Chi tiết hàng hóa";
					 var windowId="STOCK_TRANS_2";
					 vm.stockTransDetail=dataItem;
					 fillDataTableGoodsSerialList(dataItem.listDetailSerial);
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTransDetail,vm,windowId,false,'95%','85%');
				}
		
		// 4. complex popups - including tables and inputs
				// 4.1 tabs
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
				obj.type="04";
				CommonService.getDetailSign(obj).then(function(d){
					vm.list=d.plain();
				});
					vm.showTabOneNoteDetail = false;
					vm.showTabTwoNoteDetail = false;
					vm.showTabThreeNoteDetail = true;
					
					
				}
		
		// 5.validator
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		// 6.show/hide column fields
		vm.showHideColumn=function(column){
	    	if (angular.isUndefined(column.hidden)) {
	    		vm.exGrid.hideColumn(column);
	        } else if (column.hidden) {
	        	vm.exGrid.showColumn(column);
	        } else {
	        	vm.exGrid.hideColumn(column);
	        }   	
	    }
		/*
		 * * Filter các cột của select
		 */		vm.gridColumnShowHideFilter = function (item) { 
	            return item.type==null||item.type !=1; 
	        };
	        
	        
		
		 // 7.cancel input data
        vm.cancelest = function(id){
				if(id==="stock")
					{
					vm.orderSearch.stock = undefined;
					}
				if(id==="creater")
					{
						vm.exSk.creater = undefined;
					}
				if(id==="cast")
					{
						 $('#cast').data("kendoMultiSelect").value([]);
					}
				if(id==="ns")
					{
						 $('#ns').data("kendoMultiSelect").value([]);
					}
				if(id==="dates"){
					vm.stockTrans.endDate = undefined;
				}
				
				if(id==="createdDeptedName")
				{
					vm.stockTrans.createdDeptedName = undefined;
				}
				// in sendExSignPopup
				/*
				 * if(id==="exkeware") { vm.exSk.exkeware = undefined; }
				 * if(id==="rec") { vm.exSk.rec = undefined; }
				 * if(id==="finCharge") { vm.exSk.finCharge = undefined;
				 * }if(id==="finMan") { vm.exSk.finMan = undefined; }
				 */
        }
	
        // 8. Show detail from result row
     
		// 9.CRUD data

			vm.edit = function(dataItem){
				var teamplateUrl="wms/exportStatementManagement/editNotePopUp.html";
				 var title="Cập nhật phiếu xuất kho";
				 var windowId="EDIT_STOCK_TRANS";
				 vm.stockTrans =dataItem;
				 exStaManaService.getListDeatil(vm.stockTrans.stockTransId).then(
							function(d) {
							vm.disableImport = false;
								var grid=$("#stockTranUpdateGrid").data("kendoGrid")
								var data = d.plain();
								for(var i = 0; i<data.length;i++){
								data[i].isSerial = parseInt(data[i].isSerial);
								if(data[i].isSerial === 1){
									vm.disableImport = true;
								}
								}
								if(grid){
				            		grid.dataSource.data(data);
				            		grid.refresh();
									} else{
										stockTranUpdateGrid(data);
									}
								 vm.stockTrans.listDetail=data;
								 if(vm.stockTrans.countSerial>0){
								 		vm.disbledSerial=true;
								 		} else {
								 			vm.disbledSerial=false;
								 		}
								 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%', "stockTransEditReceiver");
								$('#exGrid').data('kendoGrid').dataSource.read();
								$('#exGrid').data('kendoGrid').refresh();
							}, function(errResponse) {
								toastr.error("Lỗi không xuất thực được!");
							});
			}
		
		vm.update=function(){
				vm.stockTrans.updateDate=new Date();
	    		vm.prepareDataforSaving();
	    		exStaManaService.updateStockTran(vm.stockTrans).then(function(result){
				if(result.error){
											toastr.error(result.error);
											return;
										}
	    			toastr.success("Cập nhật thành công!");
	    			doSearch();
	    			CommonService.closePopup1();
	    		}, function(errResponse){
	    			if (errResponse.status === 409) {
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Lỗi không xác định!!!"));
	                }
		        });
		}
		
		vm.prepareDataforSaving = function(){
			vm.stockTrans.listStockTransDetailDTO = [];
			var dataGoodFromGrid = $('#stockTranUpdateGrid').data("kendoGrid").dataSource.data();
			for(var i = 0; i<dataGoodFromGrid.length;i++){
				vm.stockTrans.listStockTransDetailDTO.push(dataGoodFromGrid[i]);
			}
		}

		
		vm.openDepartment=function(){
			var obj={};
			obj.page=1;
			obj.pageSize=20;
			CommonService.getDepartment(obj).then(function(result){
				var templateUrl = 'wms/popup/findDepartmentPopUp.html';
				var title = gettextCatalog.getString("Đơn vị");
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
						title: "Mã phòng ban",
						field: "code",
						width: 120
					}, {
						title: "Tên phòng ban",
						field: "text",
						width: 120
					}]
				});
				CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'create', 'ggfd', false, '85%','85%');
			});
		}
		
		vm.openRemove=openRemove;
		vm.dataReasonDelete ={};
		function openRemove(id){
			var teamplateUrl="wms/exportStatementManagement/noteCancelPopup.html";
			var grid=vm.exGrid;
			var item=grid.table.find("tr div." +id);
			var uid=$(item).parent().parent().attr("data-uid");
			var data =	grid.dataSource.getByUid(uid);
			data.cancelByName = Constant.user.vpsUserToken.fullName;
			data.cancelBy = Constant.user.vpsUserToken.sysUserId;
			data.cancelDate=kendo.toString(new Date(),"dd/MM/yyyy");
			if(data.signState==="2"){
				toastr.error("Phiếu đã được ký!");
				return;
			}
			 var title="Hủy phiếu xuất kho";
			 var windowId="CANCEL_NOTE";
				var reason = {
						status : '1'
						
				}
				reason.apply='5';
				exStaManaService.getReasonForComboBox(reason).then(function(d){
					vm.dataReasonDelete =  d.plain();
					//data.cancelReasonName=vm.dataReasonDelete[0].name;
				});
				
				 CommonService.populatePopupCreate(teamplateUrl,title,data,vm,windowId,false,'55%','35%');
				}
		
		vm.remove=remove;
		function remove(data){
			confirm('Xác nhận xóa',function (){
				exStaManaService.remove(data).then(
						function(d) {
							toastr.success("Xóa thành công!");
							/* var grid = $("#exGrid").data("kendoGrid");	
				if(grid){
					grid.dataSource.query({
						page:grid.dataSource.page(),
						pageSize: 10
					});
				}      
							CommonService.closePopup1(); */
							
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
        		var sizePage = $("#exGrid").data("kendoGrid").dataSource.total();
				var pageSize = $("#exGrid").data("kendoGrid").dataSource.pageSize();
				if(sizePage % pageSize == 1){
					var currentPage = $("#exGrid").data("kendoGrid").dataSource.page();
			        if (currentPage > 1) {
			            $("#exGrid").data("kendoGrid").dataSource.page(currentPage - 1);
			        }
				}
				$('#exGrid').data('kendoGrid').dataSource.read();
				$('#exGrid').data('kendoGrid').refresh();
						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} 
			)
		}
//autocomplete tim kiem don vi tao
		vm.selectedDept = false;
		vm.deprtOptions={
			dataTextField: "text",
			dataValueField:"id",
            select: function(e) {
			vm.selectedDept = true;
                var dataItem = this.dataItem(e.item.index());
              // vm.stockTransSearch.createdDeptName = dataItem.name;
	 			//vm.stockTransSearch.createdDeptId = dataItem.departmentId;
				vm.stockTransSearch.createdDeptName = dataItem.text;
	 			vm.stockTransSearch.createdDeptId = dataItem.id;
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
                        return Restangular.all("departmentServiceRest/department/" + 'getForAutocompleteDept').post({name:vm.stockTransSearch.createdDeptName,pageSize:vm.deprtOptions.pageSize}).then(function(response){    
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            // headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            // '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn vị</p>' +
            // '<p class="col-md-6 text-header-auto">Tên đơn vị</p>' +
            	// '</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.text #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                vm.stockTransSearch.createdDeptName = null;
	 			vm.stockTransSearch.createdDeptId = null;
                }
            },
  	            ignoreCase: false

		};
	
	
		// 10.dateTime
		var d = new Date();
		var datestring = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
		vm.stockTrans.startDate = datestring;
		vm.stockTrans.endDate = null;
		
		// 10a
		if(vm.stockTrans.endDate == ""){
			vm.stockTrans.endDate = null;
		}
		
		  // 11.show/hide panels
		   vm.myFunc = function() {
		        vm.showSearch = !vm.showSearch;
		    }
		   
		   	
		 // 13.close/cancel popup
			vm.cancel = cancel;
			function cancel(){
				/* confirm('Bạn có chắc chắn muốn hủy bỏ thao tác ?', function(){
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				var grid = $("#exGrid").data("kendoGrid");	
				if(grid){
					grid.dataSource.query({
						page: grid.dataSource.page(),
						pageSize: 10
					});
				}    
				
			} ); */
			CommonService.closePopup1();
			}
			
			vm.close = function(){
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			}
			
			
			
			// 14.searching
			vm.doSearch= doSearch;
			function doSearch(){
			
			if(!vm.validator.validate()){
				$("#createFromExState").focus();
				return;
			}
			trimSpace();
			vm.stockTransSearch.listStatus = $("#exportNoteState").data("kendoMultiSelect").value();
			vm.stockTransSearch.listSignState = $("#signStateCAExNote").data("kendoMultiSelect").value();
				var grid = $("#exGrid").data("kendoGrid");	
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10
					});
				}              
			}

			 vm.formatAction=function(dataItem){
				 var template=
				 '<div class="text-center #=appParamId#"">'				 
					 template+='<button type="button"'+
					'class="btn btn-default padding-button box-shadow  #=appParamId#"'+
					'disble="" ng-click=vm.edit(#=appParamId#)>'+
					'<div class="action-button edit" uib-tooltip="Sửa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=appParamId#"'+
					'ng-click=vm.send(#=appParamId#)>'+
					'<div class="action-button export" uib-tooltip="Gửi tài chính" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'+
				'<button type="button"'+
				'class="btn btn-default padding-button box-shadow #=appParamId#"'+
					'ng-click=vm.remove(#=appParamId#)>'+
					'<div class="action-button del" uib-tooltip="Xóa" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>'
					+
					'<button type="button" class="btn btn-default padding-button box-shadow #=appParamId#"'+
					'ng-click=vm.cancelUpgradeLta(#=appParamId#)>'+
					'<div class="action-button cancelUpgrade" uib-tooltip="Hủy nâng cấp" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
				'</button>';
					template+='</div>';
				 return dataItem.groupId;
			 }
			
			//clear data if wrong input for autocomplete
			vm.changeDataAuto=changeDataAuto
		function changeDataAuto(id){
		switch(id){
		case 'expStateCreDept':{
			if(processSearch(id,vm.selectedDept)){
				vm.stockTransSearch.createdDeptName = null;
				vm.stockTransSearch.createdDeptId = null;
			  vm.selectedDept=false;	
			 }
					break;
					}
		
		}
		}
			// 17.refresh grid
			function refreshGrid(d) {
				var grid = vm.exGrid;
				if(grid){
					grid.dataSource.data(d);
					grid.refresh();
				}
			}
			
			// 18
			// a
			vm.listRemove=[
		{
			title : "<input type='checkbox' id='chkSelectAll' name='chkSelectAll' ng-click='vm.chkSelectAll()' ng-model='vm.chkAll' />",
		}];
			vm.exportExcelGrid = function(){
			
			vm.oldSearch.page = null;
			vm.oldSearch.pageSize = null;
				exStaManaService.getForExportGrid(vm.oldSearch).then(function(d) {
					CommonService.exportFile(vm.exGrid,d.data,vm.listRemove,vm.listConvert,"Quản lý phiếu xuất kho");
				});
			}
			// b
			vm.exportExcelGridDetail = function(){
				exStaManaService.getForExportDetailGrid(vm.stockTransSerialSearch).then(function(d) {
					CommonService.exportFile(vm.goodsDetailForNoteGrid4,d.data,null,null);
				});
			}
			
			vm.listRemove=[{
				title: "Thao tác",
			},
			{
				title : "<input type='checkbox' id='chkSelectAll' name='chkSelectAll' ng-click='vm.chkSelectAll()' ng-model='vm.chkAll' />"
			}];
			vm.listConvert=[{
				field:"status",
				data:{
					1:'Chưa tạo phiếu',
					2:'Đã tạo phiếu',
					3:'Đã nhập/xuất',
					4:'Đã hủy',
					5:'Đã từ chối'
				}
			},{
				field:"signState",
				data:{
					1:'Chưa trình ký',
					2:'Đã trình ký',
					3:'Đã ký',
					4:'Đã từ chối'
				}
			}];
			
		
			// 19.
			
			vm.checkBoxAutoLoad = function checkBoxAutoLoad(item){
						vm.showExtForDepartment = false;
						vm.showExtForConstruct = false;
						vm.showExtForBHSC = false;
						vm.showExtAlternativeStock = false;
						vm.showExtForGift = false;
						vm.showExtForBorrow = false;
						vm.showExtForPay = false;
						vm.showExtForSale = false;
						vm.showExtForSell = false;
						vm.showExtForProject = false;
						
						if (item.bussinessType === vm.businessTypes[0].code ) {
					    	vm.showExtForDepartment =  true;vm.value=0;
					    	$("#extForDepartment").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[1].code) {
					  
							vm.showExtForConstruct = true;vm.value=1;
					    	$("#extForConstruct").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[2].code ) {
					  
							vm.showExtForBHSC = true;vm.value=2;
					    	$("#extForBHSC").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[3].code ) {
					
							vm.showExtAlternativeStock = true;vm.value=3;
					    	$("#extAlternativeStock").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[4].code ) {
						     
							vm.showExtForGift  = true;vm.value=4;
						    $("#extForGift").prop("checked", true);
						}
						if (item.bussinessType === vm.businessTypes[5].code ) {
					    
					    	vm.showFromBorrowedGoods = true;vm.value=5;
					    	$("#extForBorrow").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[6].code ) {
					     
							vm.showExtForPay = true;vm.value=6;
					    	$("#extForPay").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[7].code ) {
							vm.showExtForSale = true;vm.value=7;
					    	$("#extForSale").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[8].code ) {
					        vm.showExtForProject = true;vm.value=8;
							$("#extForProject").prop("checked", true);
					    }
						if (item.bussinessType === vm.businessTypes[9].code ) {
							vm.showExtForSell = true;vm.value=9;
							$("#extForSell").prop("checked", true);
					    }
		}
			
			vm.showNoteDetail=function(dataItem){
				var teamplateUrl="wms/exportStatementManagement/detailCPopup.html";
				 var title="Thông tin phiếu xuất kho";
				 var windowId="STOCK_TRANS_2";
				 vm.stockTrans=dataItem;
				 
				 if(vm.stockTrans.status === 1){
					 vm.stockTrans.statusName = 'Chưa xuất';
				 }else if(dataItem.status === 2){
					 vm.stockTrans.statusName = 'Đã xuất';
				 }else{
					 vm.stockTrans.statusName = 'Đã hủy';
				 }
				 
				 if(vm.stockTrans.signState === 1){
					 vm.stockTrans.signStateName = 'Chưa trình ký';
				 }else if(dataItem.signState === 2){
					 vm.stockTrans.signStateName = 'Đã trình ký';
				 }else if(dataItem.signState === 3){
					 vm.stockTrans.signStateName = 'Đã ký';
				 }else if(dataItem.signState === 4){
					 vm.stockTrans.signStateName = 'Đã từ chối';
				 }
				 
				 exStaManaService.getListDeatil(vm.stockTrans.stockTransId).then(
							function(d) {
								 fillDataTableGoodsListForDetail(d.plain());
								 vm.stockTrans.listDetail=d.plain();
								 vm.gotoTabOneNoteDetailPopUp();
								 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','86%');
							}, function(errResponse) {
								toastr.error("Lỗi không xuất thực được!");
							});
			}
			
			vm.showDetailOrder=function(dataItem){
				var teamplateUrl="wms/exportStatementManagement/detailPopup.html";
					 var windowId="DETAIL_POPUP";
					 vm.stockTrans = {};
					 //vm.orderGoodsSearch.orderId = vm.stockTrans.orderId;
					 var title="Chi tiết yêu cầu xuất kho " + dataItem.orderCode;
					vm.gotoTabOnePopUp();
				exStaManaService.getOrderDetailById(dataItem.orderId).then(function(d) {
				 vm.stockTrans = d.plain();
				 vm.orderGoodsSearch.orderId = vm.stockTrans.orderId;
				 vm.checkBoxAutoLoad(vm.stockTrans);
				 if(vm.stockTrans.constrCode != null){
						 createExportRequestManageService.getConstructionByCode(vm.stockTrans.constrCode).then(function(d){
							 	vm.consTrc = d.plain();	
							 	vm.stockTrans.constructionName = vm.consTrc.name;
						 });
						 
					 }
					 
					 if(vm.stockTrans.reasonId != null){
						 //var reasonToExport = {reasonId: vm.stockTrans.reasonId};
						 createExportRequestManageService.getReasonName(vm.stockTrans.reasonId).then(function(d){
							 	vm.rs = d.plain();	
							 	vm.stockTrans.reasonToExport = vm.rs.name;
						 });
					 }
					 
					 //show partner name
					 if(vm.stockTrans.partnerId != null){
						 createExportRequestManageService.getPartnerName(vm.stockTrans.partnerId).then(function(d){
							 	vm.ptn = d.plain();	
							 	vm.stockTrans.partnerName = vm.ptn.name;
						 });
					 }

					 
					 // show partner name
					 if(vm.stockTrans.status === '4' || vm.stockTrans.status === '5'){
						 vm.terminatedOrder = true;
					 }else{
						 vm.terminatedOrder = false;
					 }
				  fillDataTableGoodsListPopup([]);
					CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%'); 
				}, function() {
					console.error('Error');
				});
			}
			
			function fillDataTableGoodsListPopup(data) {
				vm.goodsListPopupGridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,	
					  change: onChangeGoodList,
				    	dataBound: function(e) {		
						var grid = $("#goodsListPopupGrid").data("kendoGrid");
                         grid.select("tr:eq(1)");
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
								url: Constant.BASE_SERVICE_URL + "orderGoodsServiceRest/orderGoods/doSearchGoodsForExportOrder",
								contentType: "application/json; charset=utf-8",
								type: "POST"
							},					
							parameterMap: function (options, type) {
								    vm.orderGoodsSearch.page = options.page
									vm.orderGoodsSearch.pageSize = options.pageSize
									
									return JSON.stringify(vm.orderGoodsSearch)
							}
						},					 
						pageSize: 10
					},
					noRecords: true,
					columnMenu: false,
					scrollable:false,
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
				        template: dataItem => $("#goodsListPopupGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
				        width: "10%",
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
				        width: "25%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Tên hàng",
				        field: 'goodsName',
				        width: "25%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Đơn vị tính",
				        field: 'goodsUnitName',
				        width: "15%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Số lượng",
						 field: 'amount',
				        width: "10%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:right;"
						},
					},  {
						title: "Tình trạng",
						 field: 'goodsStateName',
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
			
			function fillDataTableGoodsDetailPopUp(data) {
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
					scrollable:false,
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
				        width: "10%",
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
				        width:"20%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Mã hợp đồng",
				        field: 'contractCode',
				        width: "20%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Part number",
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
						 field: 'manufacturerName',
				        width: "20%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Nước sản xuất",
						 field: 'producingCountryName',
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
			 //change list good detai when select good
		function onChangeGoodList(){
			
			if ($("#goodsListPopupGrid").data("kendoGrid").select().length > 0){
				var tr = $("#goodsListPopupGrid").data("kendoGrid").select().closest("tr");
    			var dataItem = $("#goodsListPopupGrid").data("kendoGrid").dataItem(tr);
    			
    			vm.orderGoodsDetailSearch = dataItem;
    			$("#goodsCodeAndNameExtState").text(vm.orderGoodsDetailSearch.goodsCode+" "+vm.orderGoodsDetailSearch.goodsName);
				
    			var grid = $("#goodsDetailGrid").data("kendoGrid");	
    			if(grid){
    				grid.dataSource.query({
    					page: 1,
    					pageSize: 10
    				});
    			} else {
    			fillDataTableGoodsDetailPopUp([]);
    			}
    			
    			console.log(vm.orderGoodsDetailSearch.goodsName);
			}
			
		}
			/* vm.sign=function(){
				var teamplateUrl="wms/exportStatementManagement/signPopup.html";
				 var title="Thông tin phiếu trình kí";
				 var windowId="SIGNALL_NOTE";
				// vm.stockTrans={};
				 
				 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','80%');		
			} */
			vm.disbledSerial=false;
			vm.rEx=rEx;
			function rEx(dataItem){
				var teamplateUrl="wms/exportStatementManagement/RealExInfoPopup.html";
				 var title="Thực xuất phiếu xuất kho";
				 var windowId="REALEX_NOTE";
				 vm.stockTrans = dataItem;
				 
				 exStaManaService.getListDeatil(vm.stockTrans.stockTransId).then(
							function(d) {
								var grid=$("#goodsListForNoteGrid").data("kendoGrid")
								var data = d.plain();
		   			            		for(var i = 0; i<data.length;i++){
								data[i].isSerial = parseInt(data[i].isSerial);
}
								if(grid){
			            		grid.dataSource.data(data);
			            		grid.refresh();
								} else{
								 fillDataTableGoodsListForNote(data);
								}
								 vm.stockTrans.listDetail=d.plain();
							}, function(errResponse) {
								toastr.error("Lỗi không xuất thực được!");
							});
						 
				 	if(vm.stockTrans.countSerial>0 || vm.stockTrans.countSerialDetail==0){
				 		vm.disbledSerial=true;
				 		} else {
				 			vm.disbledSerial=false;
				 		}
						 
						 CommonService.populatePopupCreate(teamplateUrl,title,vm.stockTrans,vm,windowId,false,'85%','85%');
				}
			
			
			vm.realExport = function(){
								
						//confirm('Xác nhận thực xuất',function (){
							var grid=$("#goodsListForNoteGrid").data("kendoGrid")
		            		var gridData = grid.dataSource.data();
							vm.stockTrans.listStockTransDetailDTO=gridData;
							exStaManaService.realExport(vm.stockTrans).then(
									function(d) {
										if(d.error){
											toastr.error(d.error);
											return;
										}
										toastr.success("Thực xuất thành công!");
										vm.doSearch();
										$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
										//CommonService.closePopup1();
									}, function(errResponse) {
										toastr.error("Lỗi không xuất thực được!");
									});
						/* } 
						); */
					}
	
			/* vm.handleCheck = function(item){
				if(document.getElementById("chkSelectAll").checked == true){
					document.getElementById("chkSelectAll").checked = false;
				}
			}
			
			vm.chkSelectAll = function(item) {
		    	var grid = vm.exGrid;
		    	chkSelectAllBase(vm.chkAll, grid);
		    } */
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
			var grid = vm.exGrid;
	    		chkSelectAllBase(vm.chkAll, grid);
			if(vm.chkAll){
				if( $scope.checkSearch && $scope.dataSearch.length >0){
				$scope.listCheck=$scope.dataSearch;
				} else {
				
					CommonService.getallData("stockTransServiceRest/stockTrans/getAllExportManagement",vm.stockTransSearch).then(function(data){
						$scope.listCheck=data.plain();
						})
					}
				} else {
					$scope.listCheck=[];
				}
		};
			
			// xuất biên bản bàn giao - bbbg
			vm.exportOrderWithSerial = function(){
				var obj={};
				var selectedRow = [];
				var grid = vm.exGrid;
				var rowList = grid.lockedTable == undefined ? grid.table.find("tr")
						: grid.lockedTable.find("tr");
				rowList.each(function(idx, item) {
					var row = $(item);
					var checkbox = $('[name="gridcheckbox"]', row);

					if (checkbox.is(':checked')) {
						// Push id into selectedRow
						var tr = grid.select().closest("tr");
						var dataItem = grid.dataItem(item);
						selectedRow.push(dataItem.stockTransId);
					}
				});
				if (selectedRow.length === 0) {
					if (grid.select() == null || grid.select().length === 0) {
						toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
						return;
					}
				}
				obj.listStockTransId=selectedRow;
	         	obj.reportType="DOC";
	         	obj.reportName="BienBanBanGiao";
	         		CommonService.exportReport(obj).then(
	         				function(data) {
							if("error1" === data.error){
					toastr.error("Chỉ xuất được biên bản bàn giao với những phiếu cùng đơn vị nhận");
					return;
					}else if("error2" === data.error){
					toastr.error("Chỉ xuất được biên bản bàn giao với những phiếu có đơn vị nhận");
					return;
					}
	         					var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
	         					kendo.saveAs({dataURI: binarydata, fileName: "BienBanBanGiao"+ '.docx'});
	         				}, function(errResponse) {
	         					toastr.error("Lỗi không export DOC được!");
	         				});
			}
			
			// xuất bảng kê vật tư
			vm.exportstockTranEX = function(){
				var obj={};
				var selectedRow = [];
				var grid = vm.exGrid;
				var rowList = grid.lockedTable == undefined ? grid.table.find("tr")
						: grid.lockedTable.find("tr");
				rowList.each(function(idx, item) {
					var row = $(item);
					var checkbox = $('[name="gridcheckbox"]', row);

					if (checkbox.is(':checked')) {
						// Push id into selectedRow
						var tr = grid.select().closest("tr");
						var dataItem = grid.dataItem(item);
						selectedRow.push(dataItem.stockTransId);
					}
				});
				if (selectedRow.length === 0) {
					if (grid.select() == null || grid.select().length === 0) {
						toastr.warning("Cần chọn bản ghi trước khi thực hiện!");
						return;
					}
				}
			exStaManaService.exportExcelBKVT(selectedRow).then(function(result){
						if(result.error){
						toastr.error(result.error);
						return;
						}
			
    			toastr.success("Export thành công!");
    			 window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + result.fileName;
    			
    		}, function(errResponse){
                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi xuất thẻ"));
	        });
			}
			
			
			
			
			
			// --xử lý import serial từ excel
			 vm.submit=submit;
	         function submit(data){
		     	if($("#fileExport")[0].files[0] == null){
		    		toastr.warning("Bạn chưa chọn file để import");
		    		return;
		    	}
		     	if($("#fileExport")[0].files[0].name.split('.').pop() !='xls' && $("#fileExport")[0].files[0].name.split('.').pop() !='xlsx' ){
	        		toastr.warning("Sai định dạng file");
	        		return;
	        	}
		     	if($("#fileExport")[0].files[0].size > 104857600){
		     		toastr.warning("Dung lượng file import không được vượt quá 100MB");
	        		return;
		     	}
	          var formData = new FormData();
	          formData.append('multipartFile', $('#fileExport')[0].files[0]); 
	          return   $.ajax({
	              url: Constant.BASE_SERVICE_URL+RestEndpoint.STOCK_TRANS_DETAIL_SERVICE_URL+"/exportStockTrans?folder="+ vm.folder+"&stockTransId="+vm.stockTrans.stockTransId,
	              type: "POST",
	              data: formData,
	              enctype: 'multipart/form-data',
	              processData: false,
	              contentType: false,
	              cache: false,
	              success:function(data) {
					if((data[data.length - 1].lstErrorOrderGoods).length >0){
						 vm.lstErrImport = data[data.length - 1].lstErrorOrderGoods;
						 vm.objectErr = data[data.length - 1];
						vm.objectErr.fileName="XK_ThucXuat_Error";
						 var teamplateUrl="wms/stock_in_trade/errList.html";
						 var title="Kết quả Import";
						 var windowId="ERR_EXPORT";
						 CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'50%','50%');
						 erroTable(vm.lstErrImport);
					}else{
		            		toastr.success("Import thành công!");
		            		data.splice(data.length - 1, 1);
		            		var listExport={};
		            		var grid=$("#goodsListForNoteGrid").data("kendoGrid")
		            		var gridData = grid.dataSource.data();
		            	for(var x=0;x<data.length;x++){
			            	for(var i = 0; i<gridData.length;i++){
                                    if( data[x].goodsCode === gridData[i].goodsCode && data[x].goodState === gridData[i].goodsState){
                                    	gridData[i].listDetailSerial.push(data[x]);
                                    }
			            	}
		            	}
		            	grid.dataSource.data(gridData);
		            	grid.refresh();
					}
	              }
	          });
	          
	        
	         }
	         // --kết thúc xử lý import
	         
	       // --xử lý import hàng hóa từ excel
			 vm.submitUpdate=submitUpdate;
	         function submitUpdate(data){
		     	if($("#fileUpdateImport")[0].files[0] == null){
		    		toastr.warning("Bạn chưa chọn file để import");
		    		return;
		    	}
		     	
	          var formData = new FormData();
	          formData.append('multipartFile', $('#fileUpdateImport')[0].files[0]); 
			  // formData.append('orderId',vm.stockTrans.orderId)
	          return   $.ajax({
	              url: Constant.BASE_SERVICE_URL+"stockTransDetailServiceRest/stockTransDetail/importUpdateStockTrans?folderUpdate="+ vm.folder+"&orderId="+vm.stockTrans.orderId,
	              type: "POST",
	              data: formData,
	              enctype: 'multipart/form-data',
	              processData: false,
	              contentType: false,
	              cache: false,
	              success:function(data) {
					 if((data[data.length - 1].lstErrorOrderGoods).length >0){
					 vm.lstErrImport = data[data.length - 1].lstErrorOrderGoods;
					 vm.objectErr = data[data.length - 1];
						vm.objectErr.fileName="XK_TaoPhieuXuat_Error";
					 var teamplateUrl="wms/stock_in_trade/errList.html";
					 var title="Kết quả Import";
					 var windowId="ERR_STOCK_TRANS_EXPORT";
					 CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'50%','50%');
					 erroTable(vm.lstErrImport);
					 }else{
		            		
		            		data.splice(data.length - 1, 1);
			            	var grid = $("#stockTranUpdateGrid").data("kendoGrid");
		            		var gridData = grid.dataSource.data();
							
		            	for(var x=0;x<data.length;x++){
								var flag=false;
			            	for(var i = 0; i<gridData.length;i++){
                                    if( data[x].goodsCode === gridData[i].goodsCode && data[x].goodsState === gridData[i].goodsState){
                                    	gridData[i].amountReal=data[x].amountReal;
										flag=true;
                                    } 
			            	}
							if(!flag)   {
										gridData.push(data[x])
									}
		            	}
						toastr.success("Import thành công!");
		            	grid.dataSource.data(gridData);
		            	grid.refresh();
		            	}
	              }
	          });
	          
	        
	         }
	         
	         
	         // Bang hien loi import excel
	         function erroTable(data) {
	 			vm.errGridOptions = kendoConfig.getGridOptions({
	 				autoBind: true,
	 				resizable: true,			 
	 				dataSource:data,
	 				noRecords: true,
					columnMenu: false,
	 				messages: {
	 					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
	 				},
	 				pageable: {
	 					refresh: false,
	 					 pageSizes: [10, 15, 20, 25],
						 pageSize : 10,
	 					messages: {
	 		                display: "{0}-{1} của {2} kết quả",
	 		                itemsPerPage: "kết quả/trang",
	 		                empty: "Không có kết quả hiển thị"
	 		            }
	 				},
	 				columns: [{
	 					title: "TT",
	 					field:"stt",
	 			        template: dataItem => $("#detailErrGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
	 			        width: "15%",
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
	 			        width: "15%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:right;"
	 					},
	 				},  {
	 					title: "Cột lỗi ",
	 					field: 'columnError',
	 			        width: "15%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:right;"
	 					},
	 				},{
	 					title: "Nội dung lỗi ",
	 					field: 'detailError',
	 			        width: "55%",
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
			
	         // xuat file loi import excel
	         vm.exportExcelGridDetail = function(){
	        	 exStaManaService.loadDetail(data).then(function(d) {
	 				CommonService.exportFile(vm.goodsDetailForNoteGrid4,d.data,0,0);
	 			});
	 			
	 		}
	         
	         // grid hien thi thong tin tu import update excel
	         function stockTranUpdateGrid(data) {
					 var dataSource = new kendo.data.DataSource({
						pageSize: 10,
						data: data,
						autoSync: false, 
						schema: {
							model: {
								id: "stockTranUpdateGrid",
								fields: {
									stt: {editable: false},
									goodsCode: {editable: false},
									goodsName: {editable: false},
									goodsUnitName: {editable: false},
									amountOrder: {editable: false},
									
									goodsStateName : {editable: false},
								}
							}
						}
					});
	 			vm.stockTranUpdateGridOptions = kendoConfig.getGridOptions({
	 				autoBind: true,
	 				resizable: true,			 
	 				dataSource:dataSource,
					scrollable:false,
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
					edit: function(e) {
				    if(vm.disableImport){
				      $('#stockTranUpdateGrid').data("kendoGrid").closeCell();
				    }},
	 				columns: [{
	 					title: "TT",
	 					field:"stt",
	 			        template: dataItem => $("#stockTranUpdateGrid").data("kendoGrid").dataSource.indexOf(dataItem) +  1,
	 			        width: "10%",
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
	 			        width: "25%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:left;"
	 					},
	 				},  {
	 					title: "Tên hàng",
	 					field: 'goodsName',
	 			        width: "25%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:left;"
	 					},
	 				},{
	 					title: "Đơn vị tính",
	 					field: 'goodsUnitName',
	 			        width: "10%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:left;"
	 					},
	 				},{
	 					title: "Số lượng yêu cầu",
	 					field: 'amountOrder',
	 			        width: "10%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:right;"
	 					},
	 				},{
	 					title: "Số lượng xuất",
	 					field: 'amountReal',
	 			        width: "10%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:right;"
	 					},
	 				},{
	 					title: "Tình trạng",
	 					 field: 'goodsState',
	 			        template :  "# if(goodsState == 1){ #" + "#= 'Bình thường' #" + "# } " + "else if (goodsState == 2) { # " + "#= 'Hỏng' #"+ "#} #",
	 			        width: "10%",
	 			        headerAttributes: {
	 						style: "text-align:center;"
	 					},
	 					attributes: {
	 						style: "text-align:left;"
	 					}
	 				},
	 				{
						title: "Chi tiết",
						field: 'stt',
				        width: "10%",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
						template:"# if(isSerial == 1){ #" + "<a  ng-click=caller.showSerialDetail(dataItem)>#= 'Xem chi tiết' #</a>" + "# } " + "else if (isSerial == 0) { # " + "#= '' #"+ "#} #",
// dataItem => '<a href="javascript:void(0);" ng-click=caller.showSerialDetail(dataItem)>Xem chi tiết</a>',
				}
	 				]
	 			});
	 		}
	         
	     	vm.openDepartmentCreate=openDepartmentCreate
			function openDepartmentCreate(){
				vm.obj={};
				CommonService.getDepartment(vm.obj).then(function(result){
					var templateUrl = 'wms/popup/findDepartmentPopUp.html';
					var title = gettextCatalog.getString("Tìm kiếm đơn vị");
					var data=result.plain();
					vm.gridOptions = kendoConfig.getGridOptions({
						autoBind: true,
						resizable: true,
						dataSource: result,
						noRecords: true,
						columnMenu: false,
						messages: {
							noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
						},
						pageSize: 10,
						pageable: {
							refresh: false,
							 pageSizes: [10, 15, 20, 25],
							messages: {
				                display: "{0}-{1} của {2} kết quả",
				                itemsPerPage: "kết quả/trang",
				                empty: "Không có kết quả hiển thị"
				            }
						},
						columns:[{
							title: "TT",
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
					
					CommonService.populatePopupDept(templateUrl, title, vm.gridOptions,data, vm, 'nothing' , 'string', false, '91%','89%');
				});
			}
			
	         vm.onSave=onSave;
	 		function onSave(data){
	 				vm.stockTransSearch.createdDeptName = data.text;
	 				vm.stockTransSearch.createdDeptId = data.id;
	 		}
	         
	         // xuất file lỗi
	         vm.exportErrExcelGrid = function(){
			 vm.objectErr.lstErrorGoods = vm.objectErr.lstErrorOrderGoods;
	        	 exStaManaService.downloadStockTransErrorExcel(vm.objectErr).then(function(d) {
		 				data = d.plain();
		 				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
		 			}).catch( function(){
		 				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
		 				return;
		 			})
	 		}
	         
	      // validate ngay thang
			
    	    	vm.validateDate = validateDate;
			
//			function validateDate(id){
//					if(id == 'checkErr1'){
//					var startDate = $('#createFromExState').val();  
//					var endDate = $('#createToExState').val();
//					vm.errMessage = '';
//					vm.errMessage1 = '';
//					var curDate = new Date();
//					if(kendo.parseDate(startDate,"dd/MM/yyyy") > curDate){
//				           vm.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày hiện tại';
//				           $("#createFromExState").focus();
//				           return vm.errMessage1;
//				        }
//			        if(endDate !== ""){
//					if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
//			          vm.errMessage1 = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
//			          $("#createFromExState").focus();
//			          return vm.errMessage1;
//			        }
//					}
//			        
//				        else if(startDate <= curDate){
//					           vm.errMessage1 = '';
//							   vm.errMessage = '';
//					           return vm.errMessage1;
//					        }
//				}
//				else if(id == 'checkErr'){
//					var startDate = $('#createFromExState').val();  
//					var endDate = $('#createToExState').val();
//					var curDate = new Date();
//					if(endDate == undefined){
//					endDate = null;
//					}
//					if(startDate == undefined){
//					startDate = null;
//					}
//					vm.errMessage = '';
//					vm.errMessage1 = '';
//					// var d = new Date();
//					// var curDate = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
//			        if(endDate !== ""){
//			        if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
//			          vm.errMessage = 'Ngày tạo phải nhỏ hơn bằng ngày đến';
//			          $("#createToExState").focus();
//			          return vm.errMessage;
//			        }if(kendo.parseDate(endDate,"dd/MM/yyyy") > curDate){
//				           vm.errMessage = 'Ngày đến phải nhỏ hơn ngày hiện tại';
//				           $("#createToExState").focus();
//				           return vm.errMessage;
//				        }
//}
//			        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
//			          vm.errMessage = '';
//					  vm.errMessage1 = '';
//			          return vm.errMessage;
//			        }
//				} else{
//					var recDate = $('#receiveDate').val();
//					if(recDate.length != 0){
//						var curDate = new Date();
//				        
//				        if(kendo.parseDate(recDate,"dd/MM/yyyy") == null){
//				        	
//				        	vm.errMessage2 = 'Ngày nhận hàng dự kiến không hợp lệ';
//				        	$('#receiveDate').focus();
//				        	return vm.errMessage2;
//				        }
//				        	
//				        else {
//				        	if(kendo.parseDate(recDate,"dd/MM/yyyy") > (curDate - 1)){	
//				        		vm.errMessage2 = '';
//				        		$('#receiveDate').focus();return vm.errMessage2;}
//				        	else{
//				        		vm.errMessage2 = 'Ngày nhận hàng dự kiến phải lấy từ ngày hiện tại trở đi';
//					        	$('#receiveDate').focus();
//					        	return vm.errMessage2;
//				        	}
//				        }
//					}else{
//						vm.errMessage2 = '';
//						return vm.errMessage2;
//					}
//				}
//			}
    	    	
    	    	vm.objMsg={};
   	    	 function validateDate() {
   	    		var obj={};
					  obj.errMessage1="Ngày tạo từ"
					  obj.errMessage="Ngày tạo đến"
					 vm.objMsg=validateDateBase(obj,vm.stockTransSearch.createdDateFrom,vm.stockTransSearch.createdDateTo,vm.validator)
   	    		
				
   	         }
    	    	
			
	         
	         vm.exportOrderWithoutSerial=function(){
					var obj={};
					var totalMoney = 0;
					for(var i=0;i<vm.stockTrans.listDetail.length;i++){
					totalMoney += vm.stockTrans.listDetail[i].totalPrice;
					}
					obj.stringMoney=	DocTienBangChu(kendo.parseFloat(totalMoney));
					obj.stockTransId=vm.stockTrans.stockTransId;
		         	obj.reportType="DOC";
		         	obj.reportName="PhieuXuatKho_CoSerial";
		         	CommonService.exportReport(obj).then(
							function(data) {
							var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
					        kendo.saveAs({dataURI: binarydata, fileName: "PhieuCoSerial" + '.docx'});
						}, function(errResponse) {
							toastr.error("Lỗi không export DOC được!");
						});
				}
				
				vm.exportOrderWithoutNonSerial=function(){
					var obj={};
					var totalMoney = 0;
					for(var i=0;i<vm.stockTrans.listDetail.length;i++){
					totalMoney += vm.stockTrans.listDetail[i].totalPrice;
					}
					obj.stringMoney=	DocTienBangChu(kendo.parseFloat(totalMoney));
		         	obj.stockTransId=vm.stockTrans.stockTransId;
		         	obj.reportType="DOC";
		         	obj.reportName="PhieuXuatKho_KhongSerial";
		         	CommonService.exportReport(obj).then(
							function(data) {
							var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
					        kendo.saveAs({dataURI: binarydata, fileName: "PhieuKhongSerial" + '.docx'});
						}, function(errResponse) {
							toastr.error("Lỗi không export DOC được!");
						});
				}
				
				
			vm.exportOrderSerial=function(){
				var obj={};
	         	obj.orderId=vm.stockTrans.orderId;
	         	obj.reportType="DOC";
	         	obj.reportName="ThongTinYeuCauXuat_CoSerial";
	         	CommonService.exportReport(obj).then(
						function(data) {
						var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
				        kendo.saveAs({dataURI: binarydata, fileName: "YeuCauCoSerial" + '.docx'});
					}, function(errResponse) {
						toastr.error("Lỗi không export DOC được!");
					});
			}
			
			vm.exportOrderNonSerial=function(){
				var obj={};
	         	obj.orderId=vm.stockTrans.orderId;
	         	obj.reportType="DOC";
	         	obj.reportName="ThongTinYeuCauXuat_KhongSerial";
	         	CommonService.exportReport(obj).then(
						function(data) {
						var binarydata= new Blob([data],{type: "text/plain;charset=utf-8"});
				        kendo.saveAs({dataURI: binarydata, fileName: "YeuCauKhongSerial" + '.docx'});
					}, function(errResponse) {
						toastr.error("Lỗi không export DOC được!");
					});
			}
		vm.getExcelTemplate = function(fileName){
				if(!vm.disableImport){
				exStaManaService.downloadTemplate(vm.stockTrans).then(function(d) {
					data = d.plain();
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				});
			}
		}
		
		vm.downloadTemplate = function(fileName){
				
				CommonService.downloadTemplate(fileName).then(function(d) {
					data = d.plain();
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
				}).catch( function(){
					toastr.error(gettextCatalog.getString("Lỗi khi export!"));
					return;
				});
			
		}
	    
		// $scope.$watch("vm.validator.validate()",function(){
		    // focusElement(vm.validator._errors);
		// })
		
	}// end whole main het
})();
