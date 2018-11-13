(function() {
	'use strict';
	var controllerId = 'catLineController';
	
	angular.module('MetronicApp').controller(controllerId, catLineController);
	
	function catLineController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,catLineService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.catLineSearch={
				status:1
		};
		vm.catLine={};
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				editable: false,
				scrollable: true, 
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
                reorderable: true,
				toolbar: [
                          {
                              name: "actions",
                              template: 
                            	  '<div class=" pull-left ">'+
                                  '<button class="btn btn-qlk padding-search-right addQLK"'+
                                  'ng-click="vm.add()" uib-tooltip="Tạo mới" translate>Tạo mới</button>'+
                					'</div>'	
                  				+
                                	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                              '<i data-toggle="dropdown" uib-tooltip="Cài đặt" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                              '<i class="action-button excelQLK" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'+
                              '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                              '<label ng-repeat="column in vm.appParamGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							    $("#appCount").text(""+response.total);
							    vm.count=response.total;
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
							url: Constant.BASE_SERVICE_URL + "catLineRsService/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                              // vm.appParamSearch.employeeId =
								// Constant.user.srvUser.catEmployeeId;
							    vm.catLineSearch.page = options.page
								vm.catLineSearch.pageSize = options.pageSize

								return JSON.stringify(vm.catLineSearch)

						}
					},					 
					pageSize: 10
				},
// dataSource: data,
				noRecords: true,
				columnMenu: false,
				messages: {
					noRecords : gettextCatalog.getString("<div style='margin:5px'>Không có kết quả hiển thị</div>")
				},
				pageable: {
					refresh: false,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "<div style='margin:5px'>Không có kết quả hiển thị</div>"
		            }
				},
				columns: [{
					title: "TT",
					field:"stt",
			        template: function () {
					  return ++record;
					 },
			        width: 20,
			        columnMenu: false,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã tuyến",
			        field: 'code',
			        width: 30,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã tỉnh thành",
					field: 'provinceCode',
			        width: 30,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Mã điểm đầu",
			        field: 'startPointCode',
			        width: 30,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},  
				 {
					title: "Địa chỉ điểm đầu",
			        field: 'startPointAddress',
			        width: 70,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				 {
					title: "Mã điểm cuối",
			        field: 'endPointCode',
			        width: 30,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},  
				 {
					title: "Địa chỉ điểm cuối",
			        field: 'endPointAddress',
			        width: 70,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				 {
					title: "Khối lượng (KM)",
			        field: 'distanceOdd',
			        width: 30,
			        headerAttributes: {
			        	"class": "columns-title" 
					},
					attributes: {
						style: "text-align:right;"
					},
				},
				{
					title: "Trạng thái",
					field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực' #"+ "#} #",
			        width: 20,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thao tác",
					 headerAttributes: {
				        	"class": "columns-title"
						},
			        template: dataItem =>
					'<div class="text-center">'
					+'<button style=" border: none; background-color: white;" id="updateId" ng-hide="dataItem.status==0||dataItem.IsSynonim == 1"  ng-click="vm.edit(dataItem)" class=" icon_table "'+
					'   uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil"  ng-hide="dataItem.status==0||dataItem.IsSynonim == 1 "   aria-hidden="true"></i>'+
					'</button>'+
				'<button style=" border: none; background-color: white;" id="removeId"'+
				'class="#=appParamId# icon_table" ng-click="vm.remove(dataItem)" ng-hide="dataItem.status==0"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0||dataItem.IsSynonim == 1"  aria-hidden="true"></i>'+
				'</button>'+
					+'</div>',
			        width: 20,
				}
				,]
			});
		}
		vm.validatorOptions = kendoConfig.get('validatorOptions');
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
		 setTimeout(function(){
			  $("#keySearch").focus();
			},15);
		/* setTimeout(function(){
			  $("#appIds1").focus();
			},15);*/
		 var record=0;
		vm.listRemove=[{
			title: "Thao tác",
		}]
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Hiệu lực',
				0:'Hết Hiệu lực'
			}
		}]
		
		
		vm.exportFile = function exportFile() {
			var data = vm.appParamGrid.dataSource.data();
				CommonService.exportFile(vm.appParamGrid,data,vm.listRemove,vm.listConvert);
		}
		
		
		function refreshGrid(d) {
			var grid = vm.appParamGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			
// vm.add = function(){
// vm.isCreateNew = true;
// vm.showDetail = true;
// vm.appParam={};
// vm.title="Thêm mới tham số ứng dụng"
// }
			vm.add=add;
		  function add(){
//			vm.listApp= { mess:"",
//    		         mess1:"",
//					 mess2:""
//    		         };
			
			  vm.catLine={startPointType:1};
			 var teamplateUrl="coms/catLine/catLinePopup.html";
			 var title="Thêm mới tuyến";
			 var windowId="CAT_LINE";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','auto',"code"); 
			 
		 }
		
		vm.edit=edit;
		function edit(dataItem){
//			vm.listApp= { mess:"",
//    		         mess1:"",
//					 mess2:""
//    		         };
			
			vm.catLine =dataItem;
			vm.catLine.startPointType==1?vm.isMangXong = false: vm.isMangXong = true
			var teamplateUrl="coms/catLine/catLinePopup.html";
			 var title="Chỉnh sửa tuyến";
			 var windowId="CAT_STATION";
			 $("#appParamGrid").data('kendoGrid').dataSource.read();
			 $("#appParamGrid").data('kendoGrid').refresh();
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.catLine,vm,windowId,false,'1000','auto',"code"); 
		}
		
		
		vm.save= function(data,isCreateNew){
			data=vm.catLine;
//				if(checkErr()){
//					if(vm.listApp.mess !==""||vm.listApp.mess1!==""||vm.listApp.mess2!==""){
//						$('#parType').focus();
//						return vm.listApp;
//					}else{
						if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							catLineService.createCatLine(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.catLine = result;
                            doSearch();
                            //CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                            vm.add();
                            
	                		}, function(errResponse){
	    		                if (errResponse.status === 409) {
									
	    		                } else {
	    		                	toastr.error(gettextCatalog.getString("Có lỗi xảy ra"));
	    		                }
	        		        });
	                	} else {
	                		catLineService.updateCatLine(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
								$("#appParamGrid").data('kendoGrid').dataSource.read();
								$("#appParamGrid").data('kendoGrid').refresh();
	                			toastr.success("Cập nhật thành công!");
	                			
	                			//CommonService.dismissPopup();
								$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                		}, function(errResponse){
	                			if (errResponse.status === 409) {
									$('#parType').focus();
	    		                } else {
	    		                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	    		                }
	        		        });
	                	}
//				}
//			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
		/* confirm('Bạn có muốn hủy bỏ thao tác?', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
		vm.remove=remove;
		function remove(dataItem){
// var grid=vm.appParamGrid;
// var item=grid.table.find("tr div." +id);
// var uid=$(item).parent().parent().attr("data-uid");
// var dataItem = grid.dataSource.getByUid(uid);
			confirm('Bạn thật sự muốn xóa bản ghi đã chọn?', function(){
				appParamService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa bản ghi thành công!");
							var sizePage = $("#appParamGrid").data("kendoGrid").dataSource.total();
							var pageSize = $("#appParamGrid").data("kendoGrid").dataSource.pageSize();
							if(sizePage % pageSize === 1){
								var currentPage = $("#appParamGrid").data("kendoGrid").dataSource.page();
								if (currentPage > 1) {
									$("#appParamGrid").data("kendoGrid").dataSource.page(currentPage - 1);
								}
							}
							 $("#appParamGrid").data('kendoGrid').dataSource.read();
							 $("#appParamGrid").data('kendoGrid').refresh();

						}, function(errResponse) {
							toastr.error("Lỗi không xóa được!");
						});
			} )
	}
		
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.appParamSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.appParamGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		
		vm.exportExcelGrid = function(){
			var ds = $("#appParamGrid").data("kendoGrid").dataSource;
	    	var rows = [{
		        cells: [
		          { value: "STT", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã tuyến", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã tỉnh thành", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã điểm đầu", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Địa chỉ điểm cuối", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Mã điểm cuối", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Địa chỉ điểm cuối", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } },
		          { value: "Khối lượng(KM) ", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Trạng thái", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}		      
		          ]
		      }];
	    	ds.fetch(function(){
	    		var data = this.data();
	    		for (var i = 0; i < data.length; i++){
			          // push single row for every record
			        	rows.push({
				            cells: [
				              { value: i+1 , textAlign: "center"},
				              { value: data[i].code},
				              { value: data[i].provinceCode},
				              { value: data[i].startPointCode },
				              { value: data[i].startPointAddress },
				              { value: data[i].endPointCode },
				              { value: data[i].endPointAddress},
				              { value: goodsStateNameSerial(data[i].distanceOdd)},
				              { value: statusSerial(data[i].status)},
				            ]
			          })
			        }
	    		var workbook = new kendo.ooxml.Workbook({
			          sheets: [
			            {
			            	columns: [
			                // Column settings (width)
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			                { autoWidth: true },
			              ],
			              
			              // Title of the sheet
			              title: "Danh sách tra cứu serial",
			              // Rows of the sheet
			              rows: rows
			            }
			          ]
			        });
	    		kendo.saveAs({dataURI: workbook.toDataURL(), fileName: "Danh sách tra cứu serial.xlsx"});
	    	});
		}
	
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.appParamGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.appParamGrid.showColumn(column);
            } else {
            	vm.appParamGrid.hideColumn(column);
            }
        	
        	
        }
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !==1; 
            };
		
            
            vm.exportpdf= function(){
            	var obj={};
            	appParamService.exportpdf(obj);
            }
			
		// areaLocation multiselect
            vm.areaLocation=[
                             {id:1,name:'Khu vực 1'},
                             {id:2,name:'Khu vực 2'},
                             {id:3,name:'Khu vực 3'}
                             ];
            
            vm.areaLocationSelectOptions = {
            		dataSource:vm.areaLocation,
            		dataTextField: "name",
            		dataValueField: "id",
            		placeholder: "---Tất cả---",
            		optionLabel:"---Tất cả---",
            		valuePrimitive: true
            };
            // areaLocation multiselect
            vm.scope=[
                             {id:1,name:'Nội tỉnh'},
                             {id:2,name:'Nội tỉnh nội huyện'},
                             {id:3,name:'Nội tỉnh liên huyện'},
                             {id:4,name:'Liên tỉnh nội khu vực'},
                             {id:5,name:'Liên tỉnh liên khu vực'},
                             {id:6,name:'Trục'},
                             {id:7,name:'Quốc tế'},
                             ];
            
            vm.scopeSelectOptions = {
            		dataSource:vm.scope,
            		dataTextField: "name",
            		dataValueField: "id",
            		placeholder: "---Tất cả---",
            		optionLabel:"---Tất cả---",
            		valuePrimitive: true
            };
         // loại chi phí dropdown list
            vm.checkCatLineType1 =checkCatLineType1;
            vm.checkCatLineType2 =checkCatLineType2;
            function checkCatLineType1(){
            	vm.isMangXong = false;
            	vm.catLine.lineType= undefined;
            	vm.catLine.endPointId = undefined;
            }
            function checkCatLineType2(){
            	vm.isMangXong = true;
            	vm.catLine.endPointId = undefined;
            }
            
			vm.errNumber="";
            vm.checkNumber=checkNumber;
            function checkNumber(){
            	var val=$('#parOder').val();
				if(val===0){
					if(val===0){
						if(val===""){
							vm.errNumber="";
						}else{
							vm.errNumber="Phải nhập kiểu số nguyên từ 1-99";
						return false;
						}
						
					}
				}else{
					var isNaN = function(val) {
    	    			if(Number.isNaN(Number(val))){
							return false;
    	    			}
						return true;
					}
					if(isNaN(val)===false){
						vm.errNumber="Phải nhập kiểu số nguyên từ 1-99";
					}else{
						vm.errNumber="";
					}
					
				}
				
				
            }
            
	
	
	vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
    '<p class="col-md-6 text-header-auto">Phạm vi</p>' +
    	'</div>';
	// from db
	vm.scopeOptions = {
     		dataTextField:"scopeName",
      		
              select: function(e) {
                  var dataItem = this.dataItem(e.item.index());
                  vm.catLine.scope = dataItem.code
              },
              pageSize: 10,
              dataSource: {
                  serverFiltering: true,
                  transport: {
                      read: function(options) {
                          return Restangular.all("catLineRsService/" + 'getScopeForAutoComplete').post(
                              {keySearch: vm.catLine.scopeName,
                                  pageSize: 10,
                                  page: 1
                                  }).then(function(response){
                              options.success(response);
                          }).catch(function (err) {
                              console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                          });
                      }
                  }
              },
              template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.scopeName #</div> </div>',
              change: function(e) {
                  if (e.sender.value() === '') {
                	  vm.catLine.scope = null;
                  }
              },
              ignoreCase: false
          };
	vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
    '<p class="col-md-6 text-header-auto">Mã điểm đầu</p>' +
    	'</div>';
	// from db
	vm.startPointOptions = {
     		dataTextField:"code",
      		
              select: function(e) {
                  var dataItem = this.dataItem(e.item.index());
                  vm.catLine.startPointId = dataItem.catStationId;
              },
              pageSize: 10,
              dataSource: {
                  serverFiltering: true,
                  transport: {
                      read: function(options) {
                          return Restangular.all("catLineRsService/" + 'getCatStationOrLineForAutoComplete').post(
                              {keySearch: vm.catLine.startPointCode,
                            	  catType:1,
                                  pageSize: 10,
                                  page: 1
                                  }).then(function(response){
                              options.success(response);
                          }).catch(function (err) {
                              console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                          });
                      }
                  }
              },
              template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.code #</div> </div>',
              change: function(e) {
                  if (e.sender.value() === '') {
                	  vm.catLine.startPointId = null;
                  }
              },
              ignoreCase: false
          };
	vm.headerTemplate2='<div class="dropdown-header row text-center k-widget k-header">' +
    '<p class="col-md-6 text-header-auto">Mã điểm cuối</p>' +
    	'</div>';
	// from db
	vm.endPointOptions = {
     		dataTextField:"code",
      		
              select: function(e) {
                  var dataItem = this.dataItem(e.item.index());
                  vm.catLine.endPointId = dataItem.catStationId
              },
              pageSize: 10,
              dataSource: {
                  serverFiltering: true,
                  transport: {
                      read: function(options) {
                          return Restangular.all("catLineRsService/" + 'getCatStationOrLineForAutoComplete').post(
                              {keySearch: vm.catLine.endPointCode,
                            	  catType:1,
                                  pageSize: 10,
                                  page: 1
                                  }).then(function(response){
                              options.success(response);
                          }).catch(function (err) {
                              console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                          });
                      }
                  }
              },
              template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.code #</div> </div>',
              change: function(e) {
                  if (e.sender.value() === '') {
                	  vm.catLine.endPointId = null;
                  }
              },
              ignoreCase: false
          };
	vm.headerTemplate3='<div class="dropdown-header row text-center k-widget k-header">' +
    '<p class="col-md-6 text-header-auto">Thuộc tuyến</p>' +
    	'</div>';
	// from db
	vm.belongsPointOptions = {
     		dataTextField:"code",
      		
              select: function(e) {
                  var dataItem = this.dataItem(e.item.index());
                  vm.catLine.endPointId = dataItem.catStationId
              },
              pageSize: 10,
              dataSource: {
                  serverFiltering: true,
                  transport: {
                      read: function(options) {
                          return Restangular.all("catLineRsService/" + 'getCatStationOrLineForAutoComplete').post(
                              {keySearch: vm.catLine.endPointCode,
                            	  catType:2,
                                  pageSize: 10,
                                  page: 1
                                  }).then(function(response){
                              options.success(response);
                          }).catch(function (err) {
                              console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                          });
                      }
                  }
              },
              template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.code #</div> </div>',
              change: function(e) {
                  if (e.sender.value() === '') {
                	  vm.catLine.endPointId = null;
                  }
              },
              ignoreCase: false
          };
	}
})();
