(function() {
	'use strict';
	var controllerId = 'stock_transController';
	
	angular.module('MetronicApp').controller(controllerId, stock_transController);
	
	function stock_transController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,stockTransServiceRest,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        vm.commonSearchUser = {loginName: '', employeeCode: ''};
		vm.stock_transSearch={
				status:1
		};
		vm.stock_transSearch.listStockId=[];
		if($rootScope.stringtile){
			vm.String=$rootScope.stringtile;
			}
		vm.stock_trans={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		var data=[{reasonId:1,code:'fix',name:'test'}]
		vm.template='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		vm.headerTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
      '<p class="col-md-6 text-header-auto border-right-ccc">Mã</p>' +
      '<p class="col-md-6 text-header-auto">Tên</p>' +
      	'</div>';
		vm.commonSearch = {name: '', code: ''};
		
		fillDataTable([]);
		function initFormData() {
			fillDataTable([]);
			
		}
		
		//		Set ngày mặc định lùi 1 tháng
		vm.stock_transSearch.startDate=kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy")

		//Danh sách
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: false,
				resizable: true,
				sortable: false,
				columnMenu:false,
				scrollable:false,
				dataSource: {
					serverPaging: true,
					 schema: {
					  errors: function (response) {
								if(response.error){
									toastr.error(response.error);
								}
								return response.error;
							},
						 total: function (response) {
								return response.total; // total is returned in the "total" field of the response
							},
							data: function (response) {
								for(var i in response.data)
								{
									response.data[i].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId
								}
								return response.data; // data is returned in the "data" field of the response
							},
		                },
					transport: {
						read: {
		                        //Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "stockTransServiceRest/stockTrans/doSearchExport",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.stock_transSearch.page = options.page
								vm.stock_transSearch.pageSize = options.pageSize

								return JSON.stringify(vm.stock_transSearch)
						}
					},	
					pageSize: 10,
					group:[{
						field: "text",
						  aggregates: [ { field: "text"}],
					}],
					
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
				columns: [
				{
					title: "STT",
					field:"stt",
					//template: dataItem => $("#stock_transGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
			        template: function () {
						return ++record;
					},
					width: '6%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã yêu cầu xuất",
					field: 'orderCode',
			        width: '17%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}, {
					title: "Mã phiếu xuất",
			        field: 'code',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Người xuất",
			        field: 'realIeUserName',
			        width: '12%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Ngày xuất",
					 field: 'realIeTransDate',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Đơn vị nhận",
					 field: 'deptReceiveName',
			        width: '20%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Kho nhận",
					 field: 'stockReceiveCode',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;font-weight: bold;"
					},
					attributes: {
						style: "text-align:left;"
					}
				},{hidden:true,
					 field:'text',
					 groupHeaderTemplate:"#=value#"
				}]
			});
		}
		//End
		function refreshGrid(d) {
			var grid = vm.stock_transGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		vm.cancel= cancel ;
		function cancel(){
				vm.stock_trans={};
				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		//DoSearch 
		vm.flag = true;
		vm.flag1 = true;
		vm.doSearch = doSearch;
		function doSearch(){
			if((!vm.validator.validate())&&(!vm.flag1)){
				$("#startDate11").focus();
				return;
			}
			else if((vm.stock_transSearch.startDate != "")&&(!vm.flag1)){
				$("#startDate11").focus();
				return;
			}
			else if((vm.stock_transSearch.startDate != "")&&(!vm.flag)){
				$("#endDate11").focus();
				return;
			}
			else{
				vm.showDetail = false;
				var grid =vm.stock_transGrid;	
				if(grid){
					grid.dataSource.query({
						page: 1,
						pageSize: 10,
						group:[ 
							{ field: "text",
								  aggregates: [ { field: "text", aggregate: "count" }]}
						],
						sort: { field: "text", dir: "asc" },
					});
				}
			}
		}
		//End
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.stock_transGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.stock_transGrid.showColumn(column);
            } else {
            	vm.stock_transGrid.hideColumn(column);
            }
        }
		/*
		** Filter các cột của select 
		*/	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			doSearch();
		}
		//Reset DateTime
		vm.cancelstocktrans = function(id)
		{
			if(id==="date"){
				vm.stock_transSearch.startDate="";
				vm.stock_transSearch.endDate=null;
				vm.errMessage1 = "";
				vm.errMessage = "";
				vm.flag = true;
				vm.flag1 = false;
				$('#startDate11').focus;
			}
			if(id==="loginName"){
				vm.stock_transSearch.loginName=null;
				$('#loginName').focus;
			}
		}
		//End
		
   
		//Xuất PDF
		vm.exportpdf= function(){
         	var obj={};
         	obj.listStockId=vm.stock_transSearch.listStockId;
         	obj.startDate=vm.stock_transSearch.startDate;
         	obj.endDate=vm.stock_transSearch.endDate;
         	obj.loginName=vm.stock_transSearch.loginName;
         	obj.reportType="PDF";
         	obj.reportName="BaoCaoPhieuXuatKhoDangDiDuong";
         	 var ds1=$("#stock_transGrid").data("kendoGrid").dataSource.data();
         	 if (ds1.length === 0&&obj.listStockId.length===0&&obj.endDate==undefined&&obj.loginName==undefined&&obj.startDate===kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy")){
				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
				 return ;
			 } 
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/pdf'});
			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo phiếu xuất kho đang đi đường" + '.pdf'});
				}, function(errResponse) {
					toastr.error("Lỗi không export PDF được!");
				});
         }
		
		vm.exportexcel= function(){
         	var obj={};
         	obj.listStockId=vm.stock_transSearch.listStockId;
         	obj.startDate=vm.stock_transSearch.startDate;
         	obj.endDate=vm.stock_transSearch.endDate;
         	obj.loginName=vm.stock_transSearch.loginName;
         	obj.reportType="EXCEL";
         	obj.reportName="BaoCaoPhieuXuatKhoDangDiDuong";
         	 var ds1=$("#stock_transGrid").data("kendoGrid").dataSource.data();
        	 if (ds1.length === 0&&obj.listStockId.length===0&&obj.endDate==undefined&&obj.loginName==undefined&&obj.startDate===kendo.toString(new Date((new Date()).getTime()-30*24*3600*1000),"dd/MM/yyyy")){
				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
				 return ;
			 } 
         	CommonService.exportReport(obj).then(
					function(data) {
					var binarydata= new Blob([data],{ type:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
			        kendo.saveAs({dataURI: binarydata, fileName: "Báo cáo phiếu xuất kho đang đi đường" + '.xlsx'});
				}, function(errResponse) {
					toastr.error("Lỗi không export Excel được!");
				});
         }
		
		 //AutocompleteGoods
		var data=[{sysUserId:1,employeeCode:'fix',loginName:'test'}]
		vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Nhân Viên</p>' +
	      '<p class="col-md-6 text-header-auto">Tên Nhân Viên</p>' +
	      	'</div>';
		vm.goodsOptions = {
	            dataTextField: "fullName",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	              vm.stock_transSearch.employeeCode = dataItem.employeeCode; // thành id
	              vm.stock_transSearch.loginName = dataItem.fullName;//thành name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("userRoleServiceRest/userRole/" + 'getForAutoCompleteSysUser').post({fullName:vm.stock_transSearch.loginName,pageSize:vm.goodsOptions.pageSize}).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.employeeCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.stock_transSearch.employeeCode = null; // thành id
	                	vm.stock_transSearch.loginName = null;//thành name
	                }
	            },
	            ignoreCase: false
	        };
		//End
		
    	//validate ngay thang
    	vm.checkErr = checkErr;
    	function checkErr() {
    		var startDate = $('#startDate11').val();
    		var endDate = $('#endDate11').val();

    		if ($('#endDate11').val()===""){
    			endDate = "31/12/9999";
    		}
			vm.errMessage = '';
			var d = new Date();
			var curDate = d.getDate()  + "/" + d.getMonth() + "/" + d.getFullYear() ;
	        
			if(!vm.validator.validate()){
				$("#endDate11").focus();
				vm.flag = false;
				return;
			}
			
	        if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage = 'Ngày đến phải lớn hơn hoặc bằng ngày tạo';
	          $("#endDate11").focus();
			  vm.flag = false;
	          return vm.errMessage;
	        }
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage = '';
			  vm.errMessage1 = '';
			  vm.flag = true;
			  vm.flag1 = true;
	          return vm.errMessage;
	        }
	    }
    	
    	//validate ngay thang
    	vm.checkErr1 = checkErr1;
    	function checkErr1() {
    		var startDate = $('#startDate11').val();  
    		var endDate = $('#endDate11').val();
			vm.errMessage1 = '';
			var curDate = new Date();
			if ($('#endDate11').val()===""){
    			endDate = "31/12/9999";
    		}
	        
			if(!vm.validator.validate()){
				$("#startDate11").focus();
				vm.flag1 = false;
				return;
			}
			
	        if(kendo.parseDate(startDate,"dd/MM/yyyy") > curDate){
			   vm.errMessage1 = 'Ngày tạo phải nhỏ hơn hoặc bằng ngày hiện tại';
			   $("#startDate11").focus();
			   vm.flag1 = false;
			   return vm.errMessage1;
			}
			else if(startDate <= curDate){
				   vm.errMessage1 = '';
				   vm.flag1 = true;
				   return vm.errMessage1;
				}
			if(kendo.parseDate(startDate,"dd/MM/yyyy") > kendo.parseDate(endDate,"dd/MM/yyyy")){
	          vm.errMessage1 = 'Ngày tạo phải nhỏ hơn hoặc bằng ngày đến';
	          $("#startDate11").focus();
			  vm.flag1 = false;
	          return vm.errMessage1;
	        }
	        else if(kendo.parseDate(startDate,"dd/MM/yyyy") <= kendo.parseDate(endDate,"dd/MM/yyyy")){
			  vm.errMessage1 = '';
			  vm.errMessage = '';
			  vm.flag1 = true;
			  vm.flag = true;
	          return vm.errMessage1;
	        }
	    }
		
		//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		});
		//
		
		//OnkeyDown 
    	$(document).on("keydown", function (e) {
            if (e.keyCode === 13) {
            	$("#doSearchTrans").click();
            }
            });
    	//End
		
		//Enter
		$("#stock_transId").on("keypress", function (e) {
			if (e.keyCode === 13) {
				$("#doSearchTrans").focus();
				$("#doSearchTrans").click();
				}
		});
		//End
	}
})();