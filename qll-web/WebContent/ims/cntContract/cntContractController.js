(function() {
	'use strict';
	var controllerId = 'cntContractController';
	
	angular.module('MetronicApp').controller(controllerId, cntContractController);
	
	function cntContractController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,cntContractService,
			CommonService,htmlCommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        vm.purchaseOrderLst = [];
        // true nếu đang mở form thêm mới, đóng form thêm mới, isLive = false
        vm.isLive = false;
        
        // search model
		vm.cntContractSearch={
				status:1,
				quotaType:1
		};
		vm.folder='';
		
		// insert model
		vm.cntContract={
				contractType:0
		};
		vm.cntContractMap={
				
		};
// function getFolder() {
// Restangular.one(RestEndpoint.ORDER_GOOD_UPLOAD_FOLDER_URL+"/folder").get().then(function(data)
// {
// vm.folder = data.folder;
// });
// }
// getFolder();
//		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		     	
		vm.cotractStatus = {
				'Đang thực hiện':1,
				'Đã nghiệm thu':2,
				'Đã thanh toán':3,
				'Đã thanh lý':4,
				'Đã hủy':0
		}
		
		console.log(Constant.userInfo);
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		 
		 
		var record=0;
		 
		function fillDataTable(data) {
                vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				scrollable: false, 
				resizable: true,
				editable: true,
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
                                  '<button class="btn btn-qlk padding-search-right addQLK"'+
                                  'ng-click="vm.import()" uib-tooltip="Nhập file excel" translate>Map HĐ</button>'+
                					'</div>'	
                  				+
                                	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                              '<i data-toggle="dropdown" uib-tooltip="Cài đặt" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                              '<i class="action-button excelQLK" uib-tooltip="Xuất file excel" ng-click="vm.exportFile()" aria-hidden="true"></i>' +
                              '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
                              '<label ng-repeat="column in vm.cntContractGrid.columns| filter: vm.gridColumnShowHideFilter">'+
                              '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
                              '</label>'+
                              '</div></div>'
                          }
                          ],
				dataSource:{
					serverPaging: true,
					batch: true,
					 schema: {
						 model : {
							 fields : {
								 workDay : { type: "number", format: "{0:c}" },
								 price : { type: "number", format: "{0:c}" },
							 }
						 },
						 total: function (response) {
							    $("#appCount").text(""+response.total);
							    vm.count=response.total;
								return response.total; // total is returned in
														// the "total" field of
														// the response
							},
							data: function (response) {				
							for(var x in response.data){
								response.data[x].sysUserId=Constant.userInfo.vpsUserToken.sysUserId
							}
							return response.data; // data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + RestEndpoint.CNT_CONTRACT_SERVICE_URL+"/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
							    vm.cntContractSearch.page = options.page
								vm.cntContractSearch.pageSize = options.pageSize

								return JSON.stringify(vm.cntContractSearch)
						}
					},					 
					pageSize: 10
				},

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
			       
			        columnMenu: false,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  
				{
					title: "Số hợp đồng",
			        field: 'code',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên hợp đồng",
			        field: 'name',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đối tác",
			        field: 'catPartnerName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn vị ký",
			        field: 'sysGroupName',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Ngày ký",
			        field: 'signDate',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Giá trị",
			        field: 'price',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Trạng thái",
					 field: 'status',
			        template :  "# if(status == 0){ #" + "#= 'Hết hiệu lực' #" + "# } " + "else if (status == 1) { # " + "#= 'Hiệu lực" +
			        		"' #"+ "#} #",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Thao tác",
			        template: dataItem =>
					'<div class="text-center">'
					+'<button style=" border: none; background-color: white;" id="updateId" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId" ng-click="vm.edit(dataItem)" class=" icon_table "'+
					'   uib-tooltip="Sửa" translate>'+
					'<i class="fa fa-pencil"  ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				'</button>'+
					'<button style=" border: none; background-color: white;" class=" icon_table "'+
					'  style="color:grey" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId" uib-tooltip="Khóa" translate>'+
					'<i class="fa fa-pencil " style="color:grey" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"   aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;" id="removeId"'+
				'class="#=cntContractId# icon_table" ng-click="vm.remove(dataItem)" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"'+
				'class="#=cntContractId# icon_table"  uib-tooltip="Khóa" translate'+
					'>'+
					'<i class="fa fa-trash"  style="color:grey" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'
					+'</div>',
			        width: '15%',
			        field:"action"
				}
				,],
				 editable: true
			});
		}
		
		vm.listRemove=[{
			title: "Thao tác",
		}]
		vm.listConvert=[{
			field:"status",
			data:{
				1:'Hiệu lực',
				0:'Hết Hiệu lực'
			}
		}
		]
		
		
		vm.exportFile = function exportFile() {
			vm.cntContractSearch.page = null;
			vm.cntContractSearch.pageSize = null;
			var data = cntContractService.doSearch(vm.cntContractSearch);
			console.log(data);
			cntContractService.doSearch(vm.cntContractSearch).then(function(d){
				CommonService.exportFile(vm.cntContractGrid,d.data,vm.listRemove,vm.listConvert,"Danh sách định mức hạng mục công ty");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.cntContractGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

			vm.add=add;
		  function add(){
			vm.cntContract={};
			 var teamplateUrl="ims/cntContract/cntContractPopup.html";
			 var title="Thêm mới hợp đồng xây lắp đầu ra";
			 var windowId="APP_PARAM";
			 htmlCommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','600',"code"); 
		 }
		
		vm.edit=edit;
		function edit(dataItem){			
			vm.cntContract =dataItem;
			var teamplateUrl="ims/cntContract/cntContractPopup.html";
			var title="Cập nhật hợp đồng xây lắp đầu ra";
			var windowId="APP_PARAM";
			$("#cntContractGrid").data('kendoGrid').dataSource.read();
			$("#cntContractGrid").data('kendoGrid').refresh();
	        !dataItem.purchaseOrderLst ? vm.purchaseOrderLst = [] : vm.purchaseOrderLst = dataItem.purchaseOrderLst;
			htmlCommonService.populatePopupCreate(teamplateUrl,title,vm.cntContract,vm,windowId,false,'1000','600',"code");
		}
		
          /*      vm.import=function(){
                    vm.cntContract={};
			 var teamplateUrl="ims/cntContract/importCntContractQuota.html";
			 var title="Import hợp đồng xây lắp đầu ra từ file excel";
			 var windowId="APP_PARAM";
			 htmlCommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','600'); 
                }*/

		
		vm.save= function(data,isCreateNew){
// vm.cntContract.purchaseOrderLst = vm.purchaseOrderLst;
			var purchaseOrderLst =$('#purchaseOrderGrid').data("kendoGrid").dataSource.data();
			vm.cntContract.purchaseOrderLst = purchaseOrderLst;
			data=vm.cntContract;
			if(vm.errNumber!==""){
				return false;
			}
			if(isCreateNew) {
				cntContractService.createCntContract(data).then(function(result){
					if(result.error){
						toastr.error(result.error);
						return;
					}
	    			toastr.success("Thêm mới thành công!");
	                vm.cntContract = result;
	                doSearch();
	                htmlCommonService.dismissPopup();
					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
// vm.add();
                
	    		}, function(errResponse){
	                if (errResponse.status === 409) {
	                	toastr.error(gettextCatalog.getString("Hợp đồng đã tồn tại!"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo hợp đồng xây lắp đầu ra!"));
	                }
		        });
	    	} else {
	    		cntContractService.updateCntContract(data).then(function(result){
					if(result.error){
						$('#parType').focus();
						toastr.error(result.error);
						return;
					}
					
					$("#cntContractGrid").data('kendoGrid').dataSource.read();
					$("#cntContractGrid").data('kendoGrid').refresh();
	    			toastr.success("Cập nhật thành công!");
	    			
	    			 htmlCommonService.dismissPopup()
					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    		}, function(errResponse){
	    			if (errResponse.status === 409) {
						$('#parType').focus();
	                	toastr.error(gettextCatalog.getString("Mã hợp đồng"));
	                } else {
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	                }
		        });
	    	}
						
						 
// }
// }
		}
		
		vm.cancel= cancel ;
		function cancel(){
		/* confirm('Bạn có muốn hủy bỏ thao tác?', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
		vm.remove=remove;
		function remove(dataItem){
			confirm('Bạn thật sự muốn xóa bản ghi đã chọn?', function(){
				cntContractService.remove(dataItem).then(
					function(d) {
						toastr.success("Xóa tham số thành công!");
						var sizePage = $("#cntContractGrid").data("kendoGrid").dataSource.total();
						var pageSize = $("#cntContractGrid").data("kendoGrid").dataSource.pageSize();
						if(sizePage % pageSize === 1){
							var currentPage = $("#cntContractGrid").data("kendoGrid").dataSource.page();
							if (currentPage > 1) {
								$("#cntContractGrid").data("kendoGrid").dataSource.page(currentPage - 1);
							}
						}
						 $("#cntContractGrid").data('kendoGrid').dataSource.read();
						 $("#cntContractGrid").data('kendoGrid').refresh();

					}, function(errResponse) {
						toastr.error("Lỗi không xóa được!");
					});
			} )
		}
		
		
		vm.canceldoSearch= function (){
			 vm.showDetail = false;
			vm.cntContractSearch={
					status:"1",
			};
			doSearch();
		}
		
		vm.doSearch= doSearch;
		function doSearch(){
			console.log(vm.cntContractSearch);
			  vm.showDetail = false;
			var grid =vm.cntContractGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}

			console.log(grid.dataSource.data());
		}
		
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.cntContractGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.cntContractGrid.showColumn(column);
            } else {
            	vm.cntContractGrid.hideColumn(column);
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
            	cntContractService.exportpdf(obj);
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
            
        	vm.openGroup = function openGroup(){
    			var obj={};
    			cntContractService.getAllSysGroup(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/findSysGroupPopUp.html';
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
    						pageSize:10,
    						pageSizes: [10, 15, 20, 25],
    						messages: {
    			                display: "{0}-{1} của {2} kết quả",
    			                itemsPerPage: "kết quả/trang",
    			                empty: "Không có kết quả hiển thị"
    			            }
    					},
    					
    				});
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','popupGroupSearchController');
    			});
    		}
            
        	vm.openPartner = function openPartner(){
    			var obj={};
    			cntContractService.getAllSysGroup(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/partnerSearchPopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm loại công trình");
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
    						pageSize:10,
    						pageSizes: [10, 15, 20, 25],
    						messages: {
    			                display: "{0}-{1} của {2} kết quả",
    			                itemsPerPage: "kết quả/trang",
    			                empty: "Không có kết quả hiển thị"
    			            }
    					},
    				});
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','partnerSearchController');
    			});
    		}
        	
        	vm.openUser = function openUser(){
    			var obj={};
    			cntContractService.getSysUser(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/sysUserSearchPopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm hạng mục công trình");
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
    						pageSize:10,
    						pageSizes: [10, 15, 20, 25],
    						messages: {
    			                display: "{0}-{1} của {2} kết quả",
    			                itemsPerPage: "kết quả/trang",
    			                empty: "Không có kết quả hiển thị"
    			            }
    					},
    				});
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','sysUserSearchController');
    			});
    		}
        	
            vm.getExcelTemplate = function(){
    			var fileName="FileBieuMau_DMHangMucCongTy";
    			CommonService.downloadTemplate(fileName).then(function(d) {
    				data = d.plain();
    				
// console.log(cntContractService.getTemplateFile(data.fileName).$$state);
    				window.location = Constant.BASE_SERVICE_URL + RestEndpoint.WORK_ITEM_QUOTA_SERVICE_URL+"/downloadFile?" + data.fileName;
// console.log(JSON.stringify(result));
    			}).catch( function(){
    				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
    				return;
    			});
    		
            }
            
            vm.patternGroupOptions={
    				dataTextField: "name", placeholder:"Nhập mã đơn vị hoặc tên đơn vị",
    	            select: function(e) {
    	            	var dataItem = this.dataItem(e.item.index());
    	            	// nếu đang làm việc ở màn hình tìm kiếm
    	            	if(!vm.isLive){
	    	                vm.cntContractSearch.sysGroupName = dataItem.name;
	    	                vm.cntContractSearch.sysGroupId = dataItem.sysGroupId;
	    	                console.log(vm.cntContractSearch);
    	            	}
    	            	// nếu đang làm việc ở form thêm mới
    	            	else{
    	            		vm.cntContract.sysGroupName = dataItem.name;
	    	                vm.cntContract.sysGroupId = dataItem.sysGroupId;
	    	                console.log(vm.cntContract);
    	            	}
    	             
    	            },
    	            pageSize: 10,
    	            dataSource: {
    	                serverFiltering: true,
    	                transport: {
    	                    read: function(options) {
    	                        return Restangular.all("sysGroupServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("sysGroup")).val().trim()}).then(function(response){
    	                            options.success(response);
    	                            var check = response == [] || $("#"+proccessFormId("sysGroup")).val().trim() == "";
    	                            if(response == [] || $("#"+proccessFormId("sysGroup")).val().trim() == ""){
    	                            	 vm.cntContractSearch.sysGroupId = null;
    	                            	 vm.cntContract.sysGroupId =null;
    	                            }
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
    	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
    	            change: function(e) {	
    	            	if(processSearch(proccessFormId("cntContractId"),vm.selectedPrpject)){
    						  vm.cntContractSearch.sysGroupName = null;
    						  vm.cntContract.sysGroupName = null;
    						  vm.selectedPrpject=false;	
    	            	}
    	            },
    	            close: function(e) {
    	                // handle the event0
    	              }
    			};
            
            vm.onSaveGroupId=onSaveGroupId;
    		function onSaveGroupId(data){
    			if(!vm.isLive){
    			  vm.cntContractSearch.sysGroupName = data.name;
                  vm.cntContractSearch.sysGroupId = data.sysGroupId;
                  console.log(vm.cntContractSearch);
                  $("#"+proccessFormId("sysGroup")).focus();
    			}
    			else{
    				vm.cntContract.sysGroupName = data.name;
    				vm.cntContract.sysGroupId = data.sysGroupId;
    				$("#"+proccessFormId("sysGroup")).val(vm.cntContract.sysGroupName);
    				console.log(vm.cntContract);
    			}
    			htmlCommonService.dismissPopup();
    		}
    		
    		vm.onSavePartnerId = function onSavePartnerId(data){
    	    	if(!vm.isLive){
		  			vm.cntContractSearch.catPartnerName = data.name;
		            vm.cntContractSearch.catPartnerId = data.catPartnerId;
		            console.log(vm.cntContractSearch);
    	    	}
    	    	else{
    	    		vm.cntContract.catPartnerName = data.name;
    	            vm.cntContract.catPartnerId = data.catPartnerId;
    	    	}
	            htmlCommonService.dismissPopup();
	            $("#"+proccessFormId("signerGroup")).focus();
  		    };
    		
  		  vm.onSaveSysUser = function onSaveSysUser(data){
  			if(!vm.isLive){
	  			vm.cntContractSearch.signerGroupName = data.fullName;
	            vm.cntContractSearch.signerGroup = data.sysUserId;
	            console.log(vm.cntContractSearch);
  			}
  			else{
  				vm.cntContract.signerGroupName = data.fullName;
	            vm.cntContract.signerGroup = data.sysUserId;
	            
  			}
	            htmlCommonService.dismissPopup();
	            $("#"+proccessFormId("catPartnerName")).focus();
		    };
		    
		    vm.onSaveBidPackage = function onSaveBidPackage(data){
	  			if(!vm.isLive){
		  			vm.cntContractSearch.biddingPackageName = data.name;
		            vm.cntContractSearch.biddingPackageId = data.biddingPackageId;
		           
	  			}
	  			else{
	  				vm.cntContract.biddingPackageName = data.name;
		            vm.cntContract.biddingPackageId = data.biddingPackageId;
		            
	  			}
		            htmlCommonService.dismissPopup();
		            $("#"+proccessFormId("biddingPackageName")).focus();
			    };
  		
		    vm.patternPartnerOptions={
     				dataTextField: "name", placeholder:"Nhập mã hoặc tên đối tác",
     	            select: function(e) {
     	                var data = this.dataItem(e.item.index());
     	               if(!vm.isLive){
	     	               vm.cntContractSearch.catPartnerName = data.name;
	  		               vm.cntContractSearch.catPartnerId = data.catPartnerId;
     	               } else{
     	            	   vm.cntContract.catPartnerName = data.name;
     			           vm.cntContract.catPartnerId = data.catPartnerId;
     	               }
     	                console.log(vm.cntContractSearch);
     	            },
     	            pageSize: 10,
     	            dataSource: {
     	                serverFiltering: true,
     	                transport: {
     	                    read: function(options) {
     	                        return Restangular.all("catPartnerServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("catPartnerName")).val().trim()}).then(function(response){
     	                            options.success(response);
     	                            var check = response == [] || $("#"+proccessFormId("catPartnerName")).val().trim() == "";
     	                            if(response == [] || $("#"+proccessFormId("catPartnerName")).val().trim() == ""){
     	                            	 vm.cntContractSearch.catPartnerId = null;
     	                            	 vm.cntContract.catPartnerId = null;
     	                            	 
     	                            }
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
     	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
     	            change: function(e) {	
     	            	if(processSearch(proccessFormId("catPartnerName"),vm.selectedPrpject)){
     	            		var check = processSearch(proccessFormId("catPartnerName"),vm.selectedPrpject);
     	            		console.log(check);
     						vm.cntContractSearch.catPartnerId = null;
     						vm.cntContract.catPartnerId = null;
     						
// $('#purchaseOrderId').val(null);
     						  vm.selectedPrpject=false;	
     	            	}
     	            },
     	            close: function(e) {
     	              }
     			};
    	     
    	     vm.patternSignerOptions={
      				dataTextField: "signerGroupName", placeholder:"Nhập mã hoặc đại diện đơn vị ký",
      	            select: function(e) {
      	            	var data = this.dataItem(e.item.index());
      	                if(!vm.isLive){
      	                	vm.cntContractSearch.signerGroupName = data.fullName;
      	                	vm.cntContractSearch.signerGroup = data.sysUserId;
      	                } else{
      	                	vm.cntContract.signerGroupName = data.fullName;
      	                	vm.cntContract.signerGroup = data.sysUserId;
      	                }
      	            },
      	            pageSize: 10,
      	            dataSource: {
      	                serverFiltering: true,
      	                transport: {
      	                    read: function(options) {
      	                        return Restangular.all("sysUserServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("signerGroup")).val().trim()}).then(function(response){
      	                            options.success(response);
      	                            if(response == [] || $("#"+proccessFormId("signerGroup")).val().trim() == ""){
      	                            	 vm.cntContractSearch.signerGroupId = null;
      	                            	 vm.cntContractr.signerGroupId=null;
      	                            }
      	                        }).catch(function (err) {
      	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
      	                        });
      	                    }
      	                }
      	            },
      	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
      	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
      	            '<p class="col-md-6 text-header-auto">Họ tên</p>' +
      	            	'</div>',
      	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
      	            change: function(e) {	
      	            	if(processSearch(proccessFormId("signerGroup"),vm.selectedPrpject)){
      	            		if(vm.isLive){
      	            			vm.cntContract.signerGroupId= null;
      	            		}
      	            		else{
      	            			vm.cntContractSearch.signerGroupId  = null;
      	            		}
      						
// $('#purchaseOrderId').val(null);
      						  vm.selectedPrpject=false;	
      	            	}
      	            },
      	            close: function(e) {
      	              }
      			};
    	     
    	 
     		
   		    vm.patternBidPackOptions={
        				dataTextField: "name", placeholder:"Nhập mã hoặc gói thầu",
        	            select: function(e) {
        	                var data = this.dataItem(e.item.index());
        	               if(!vm.isLive){
   	     	               vm.cntContractSearch.biddingPackageName = data.name;
   	  		               vm.cntContractSearch.biddingPackageId = data.biddingPackageId;
        	               } else{
        	            	   vm.cntContract.biddingPackageName = data.name;
        			           vm.cntContract.biddingPackageId = data.biddingPackageId;
        	               }
        	            },
        	            pageSize: 10,
        	            dataSource: {
        	                serverFiltering: true,
        	                transport: {
        	                    read: function(options) {
        	                        return Restangular.all(RestEndpoint.BIDDING_PACKAGE_SERVICE_URL+"/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("biddingPackageName")).val().trim()}).then(function(response){
        	                            options.success(response);
        	                            var check = response == [] || $("#"+proccessFormId("biddingPackageName")).val().trim() == "";
        	                            if(response == [] || $("#"+proccessFormId("biddingPackageName")).val().trim() == ""){
        	                            	 vm.cntContractSearch.biddingPackageId = null;
        	                            	 vm.cntContract.biddingPackageId = null;
        	                            	 
        	                            }
        	                        }).catch(function (err) {
        	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
        	                        });
        	                    }
        	                }
        	            },
        	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
        	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã gói thầu</p>' +
        	            '<p class="col-md-6 text-header-auto">Tên gói thầu</p>' +
        	            	'</div>',
        	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
        	            change: function(e) {	
        	            	if(processSearch(proccessFormId("biddingPackageName"),vm.selectedPrpject)){
        	            		var check = processSearch(proccessFormId("biddingPackageName"),vm.selectedPrpject);
        	            		console.log(check);
        						vm.cntContractSearch.biddingPackageId = null;
        						vm.cntContract.biddingPackageId = null;
        						
// $('#purchaseOrderId').val(null);
        						  vm.selectedPrpject=false;	
        	            	}
        	            },
        	            close: function(e) {
        	              }
        			};
             
   			vm.openBiddingpackage = function openBiddingpackage(){
    			var obj={};
    			cntContractService.getAllSysGroup(obj).then(function(result){
    				var templateUrl = 'searchPopup/template/biddingPackageSearchPopUp.html';
    				var title = gettextCatalog.getString("Tìm kiếm gói thầu");
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
    						pageSize:10,
    						pageSizes: [10, 15, 20, 25],
    						messages: {
    			                display: "{0}-{1} của {2} kết quả",
    			                itemsPerPage: "kết quả/trang",
    			                empty: "Không có kết quả hiển thị"
    			            }
    					},
    				});
    				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','biddingPackageSearchController');
    			});
    		}
   			
   		 vm.patternOrderOptions={
 				dataTextField: "purchaseOrder", placeholder:"Nhập mã/tên đơn hàng",
 	            select: function(e) {
 	            	var dataItem = this.dataItem(e.item.index());
 	            	if(!checkDups(dataItem)){
 	            		vm.purchaseOrderLst.push(dataItem);
 	            	}
 	            },
 	            pageSize: 10,
 	            dataSource: {
 	                serverFiltering: true,
 	                transport: {
 	                    read: function(options) {
 	                        return Restangular.all(RestEndpoint.PURCHASE_ORDER_SERVICE_URL+"/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#purchaseOrder").val().trim()}).then(function(response){
 	                            options.success(response);
 	                            var check = response == [] || $("#purchaseOrder").val().trim() == "";
 	                            if(response == [] || $("#purchaseOrder").val().trim() == ""){
 	                            	$("#purchaseOrder").val(null);
 	                            }
 	                        }).catch(function (err) {
 	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
 	                        });
 	                    }
 	                }
 	            },
 	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
 	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã đơn hàng</p>' +
 	            '<p class="col-md-6 text-header-auto">Tên đơn hàng</p>' +
 	            	'</div>',
 	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
 	            change: function(e) {	
 	            	if(processSearch("purchaseOrder",vm.selectedPrpject)){
 						  $("#purchaseOrder").val(null);
 	            	}
 	            },
 	            close: function(e) {
 	                // handle the event0
 	              }
 			};
   			
    	    // khai báo form thêm mới đã bị đóng
    		vm.closeForm = function(){
    			vm.purchaseOrderLst = [];
    			vm.isLive=false;
    			vm.taskCount="";
    		};
    		// khai báo form thêm mới đang làm việc
    		vm.openForm = function(){
    			  vm.purchaseOrderOptions = new kendoConfig.getGridOptions({
    					autoBind: true,
    					resizable: true,
    					dataSource: {
    						data: getPurchaseOrderLst()
    					},
    					dataBound: function (e) {
    			    	     var grid = $("#purchaseOrderGrid").data("kendoGrid");
    			    	    grid.tbody.find("tr").dblclick(function (e) {
    			    	        var dataItem = grid.dataItem(this);
    			    	        $('#'+dataItem.code).click();
    			    	    });
    			    	},
    					noRecords: true,
    					scrollable:false,
    					messages: {
    						noRecords : "Không có kết quả hiển thị"
    					},
    					pageSize:10,
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
    					columns:[{
    						title: "TT",
    						field: "stt",
    						width:'5%',
    						template: dataItem => $("#purchaseOrderGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1 ,
    						headerAttributes: {
    								style: "text-align:center;"
    							},
    							attributes: {
    								style: "text-align:center;"
    							},
    						}, 
    				        {
    						title: "Mã đơn hàng",
    						field: "code",
    						headerAttributes: {
    							style: "text-align:center;"
    						},
    						attributes: {
    							style: "text-align:left;"
    						},
    					}, {
    						title: "Tên đơn hàng",
    						field: "name",
    						headerAttributes: {
    							style: "text-align:center;"
    						},
    						attributes: {
    							style: "text-align:left;"
    						},
    					},{
    						title: "Giá trị",
    						field: "price",
    						headerAttributes: {
    							style: "text-align:center;"
    						},
    						attributes: {
    							style: "text-align:left;"
    						},
    						editable: false
    					},{
    						title: "Ngày tạo",
    						field: "signDate",
    						headerAttributes: {
    							style: "text-align:center;"
    						},
    						attributes: {
    							style: "text-align:left;"
    						},
    					},{
    						title: "Nguồn kinh phí",
    						field: "expense",
    						headerAttributes: {
    							style: "text-align:center;"
    						},
    						attributes: {
    							style: "text-align:left;"
    						},
    					}, {
    						title: "Thao tác",
    				        template: dataItem =>
    					'<div class="text-center #=orderCode#"">'+
							'<a type="button" class="#=orderCode# icon_table" uib-tooltip="Xóa" translate ng-click="caller.removeOrder(dataItem)">'+
								'<i class="fa fa-trash" aria-hidden="true"></i>'+
							'</a>'+
						'</div>',
    				        width: '15%',
    				        field:"action"
    					}
    					]
    				});
    			
    			vm.isLive=true;
    		}
    	     
            vm.submit=submit;
	        function submit(data){
	        	console.log(data);
	        	if($("#filePackage")[0].files[0] === null){
		    		toastr.warning("Bạn chưa chọn file để import");
		    		return;
		    	}
	        	console.log("2");
				if($('.k-invalid-msg').is(':visible')){
					return;
				}
				console.log("3");
				if($("#filePackage")[0].files[0].name.split('.').pop() !=='xls' && $("#filePackage")[0].files[0].name.split('.').pop() !=='xlsx' ){
					toastr.warning("Sai định dạng file");
					return;
				}
	    		 var formData = new FormData();
					formData.append('multipartFile', $('#filePackage')[0].files[0]); 
			     return   $.ajax({
		            url: Constant.BASE_SERVICE_URL+RestEndpoint.WORK_ITEM_QUOTA_SERVICE_URL+"/importCntContract?folder="+ vm.folder+"&quotaType=1",
		            type: "POST",
		            data: formData,
		            enctype: 'multipart/form-data',
		            processData: false,
		            contentType: false,
		            cache: false,
		            success:function(data) {
		            	if(!data.error){
							  toastr.success("Import thành công!");
							  doSearch();
		            	}else{
		            		toastr.error(data.error);
		            	}
							  
		            }
		        });     
         }
	        
	      function proccessFormId(formId){
	    	  return vm.isLive ? (formId + "1") : formId;
	      }
	      
	      vm.cancelGroup = function(){
	    	  vm.cntContractSearch.sysGroupName = null;
	    	  vm.cntContractSearch.sysGroupId = null;
	      }
	      vm.cancelWorkType = function(){
	    	  vm.cntContractSearch.catWorkItemTypeName = null;
	    	  vm.cntContractSearch.catWorkItemTypeId = null;
	      }
	      
	      vm.cancelConstruction = function(){
	    	  vm.cntContractSearch.catConstructionTypeName = null;
	    	  vm.cntContractSearch.catConstructionTypeId = null;
	      }
	      
	      function getPurchaseOrderLst(){
	  		return vm.purchaseOrderLst;
	  	}
	      
	      vm.removeOrder = function(dataItem){
	    	  for(var i=0;i<vm.purchaseOrderLst.length;i++)
		    	 {
		    	   if(vm.purchaseOrderLst[i].code==dataItem.code)
		    		   {
		    		   vm.purchaseOrderLst.splice(i,1);
		    		   toastr.success("Xóa thành công!");
						return;
		    		   }
		    	 }
	      }
	      
	     // hoanm1_20170227_start
	         vm.import=function(){
	             vm.cntContract={};
				 var teamplateUrl="ims/cntContract/cntContractMap.html";
				 var title="Thông tin Map hợp đồng";
				 var windowId="APP_PARAM";
				 htmlCommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'900','200'); 
	           }

	      vm.openContractGroup = function openGroup(){
  			var obj={};
  			cntContractService.doSearch(obj).then(function(result){
  				var templateUrl = 'searchPopup/template/contractSearchPopUp.html';
  				var title = gettextCatalog.getString("Tìm kiếm hợp đồng");
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
  						pageSize:10,
  						pageSizes: [10, 15, 20, 25],
  						messages: {
  			                display: "{0}-{1} của {2} kết quả",
  			                itemsPerPage: "kết quả/trang",
  			                empty: "Không có kết quả hiển thị"
  			            }
  					},
  					
  				});
  				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','contractSearchController');
  			});
  		}
	      vm.openContractMapGroup = function openGroup(){
	  			var obj={};
	  			cntContractService.getAllSysGroup(obj).then(function(result){
	  				var templateUrl = 'searchPopup/template/findSysGroupPopUp.html';
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
	  						pageSize:10,
	  						pageSizes: [10, 15, 20, 25],
	  						messages: {
	  			                display: "{0}-{1} của {2} kết quả",
	  			                itemsPerPage: "kết quả/trang",
	  			                empty: "Không có kết quả hiển thị"
	  			            }
	  					},
	  					
	  				});
	  				htmlCommonService.populatePopup(templateUrl, title, vm.gridOptions,data, vm, 'fff', 'ggfd', false, '85%','85%','popupGroupSearchController');
	  			});
	  		}
	      vm.onSaveContract = function onSaveContract(data){
	  			if(!vm.isLive){		           
	  			}
	  			else{
	  				vm.cntContractMap.name = data.name;
		            vm.cntContractMap.cntContractId = data.cntContractId;
		            
	  			}
		            htmlCommonService.dismissPopup();
		            $("#"+proccessFormId("name")).focus();
			    };
	      
	      vm.patternContractOptions={
  				dataTextField: "name", placeholder:"Nhập mã hợp đồng hoặc tên hợp đồng",
  	            select: function(e) {
  	            	var dataItem = this.dataItem(e.item.index());
  	            	// nếu đang làm việc ở màn hình tìm kiếm
  	            	if(!vm.isLive){
	    	                vm.cntContractSearch.sysGroupName = dataItem.name;
	    	                vm.cntContractSearch.sysGroupId = dataItem.sysGroupId;
	    	                console.log(vm.cntContractSearch);
  	            	}
  	            	// nếu đang làm việc ở form thêm mới
  	            	else{
  	            		
  	            			vm.cntContractMap.name = dataItem.name;
	    	                vm.cntContractMap.cntContractId = dataItem.cntContractId;
	    	                console.log(vm.cntContractMap);
  	            	}
  	             
  	            },
  	            pageSize: 10,
  	            dataSource: {
  	                serverFiltering: true,
  	                transport: {
  	                    read: function(options) {
  	                        return Restangular.all("sysGroupServiceRest/getForAutoComplete").post({pageSize:10, page:1, keySearch:$("#"+proccessFormId("name")).val().trim()}).then(function(response){
  	                            options.success(response);
  	                            var check = response == [] || $("#"+proccessFormId("name")).val().trim() == "";
  	                            if(response == [] || $("#"+proccessFormId("name")).val().trim() == ""){
  	                            	 vm.cntContractMap.cntContractId =null;
  	                            }
  	                        }).catch(function (err) {
  	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
  	                        });
  	                    }
  	                }
  	            },
  	            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
  	            '<p class="col-md-6 text-header-auto border-right-ccc">Mã hợp đồng</p>' +
  	            '<p class="col-md-6 text-header-auto">Tên hợp đồng</p>' +
  	            	'</div>',
  	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
  	            change: function(e) {	
  	            	if(processSearch(proccessFormId("name"),vm.selectedPrpject)){
  						  vm.cntContractMap.name = null;
  						  vm.selectedPrpject=false;	
  	            	}
  	            },
  	            close: function(e) {
  	                // handle the event0
  	              }
  			};
	      //hoanm1_20170227_end
		
	       
	}
	
	function checkDups(purchaseOrder){
		var isExisted = false;
		var orderGrid = $('#purchaseOrderGrid').data("kendoGrid");
		var dataGridLength = orderGrid.dataSource.data().length;
		
		if(dataGridLength!=0){
			var dataItem = orderGrid.dataSource._data;
			for(var i=0;i<dataGridLength;i++){
				if(purchaseOrder.code == dataItem[i].code){
							return isExisted = true;
				}
			}
		}
        return isExisted;
	}
	
	
})();
