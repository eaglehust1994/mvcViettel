(function() {
	'use strict';
	var controllerId = 'biddingPackageController';
	
	angular.module('MetronicApp').controller(controllerId, biddingPackageController);
	
	function biddingPackageController($scope,$templateCache, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,biddingPackageService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
		vm.appParamSearch={
				status:1
		};
		vm.folder='';
		vm.appParam={};
		function getFolder() {
			Restangular.one(RestEndpoint.ORDER_GOOD_UPLOAD_FOLDER_URL+"/folder").get().then(function(data) {
				vm.folder = data.folder;
			  });
		}
		getFolder();
		
		// Khoi tao du lieu cho form
		initFormData();
		function initFormData() {
			fillDataTable([]);
		}
		     		
	
			vm.investmentOwnerType = {
					'Đối tác ngoài Viettel':1,
					'Đối tác trong Viettel':0
			}
			vm.procurementFormsId={
				'đấu thầu rộng rãi':1,
				'đấu thầu hạn chế':0,
				'mua sắm trực tiếp':2,
				'chỉ định thầu':3,
				'chào hàng cạnh tranh':4,
				'tự đấu thầu':5
			}
		
		
		console.log(Constant.userInfo);
		
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
		/*
		 * setTimeout(function(){ $("#appIds1").focus(); },15);
		 */
		 var record=0;
		 var toolbar = $templateCache.get("wms/biddingPackage/toolbar.html")
                 console.log(toolbar);
		function fillDataTable(data) {
                    vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				 scrollable: false, 
				resizable: true,
				editable: false,
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
                                  '          '+
                                  '<button class="btn btn-qlk padding-search-right addQLK"'+
                                  'ng-click="vm.import()" uib-tooltip="Nhập file excel" translate>Import</button>'+
                					'</div>'	
                  				+
                                	  '<div class="btn-group pull-right margin_top_button margin_right10">'+
                              '<i data-toggle="dropdown" uib-tooltip="Cài đặt" aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'+
                              '<i class="action-button excelQLK" uib-tooltip="Xuất file excel" ng-click="vm.exportFile()" aria-hidden="true"></i>' +
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
							url: Constant.BASE_SERVICE_URL + "biddingPackageServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
                           
							    vm.appParamSearch.page = options.page
								vm.appParamSearch.pageSize = options.pageSize

								return JSON.stringify(vm.appParamSearch)

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
					title: "Mã gói thầu",
					field: 'code',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tên gói thầu",
			        field: 'name',
			        width: '15%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Hình thức đấu thầu",
			        field: 'procurementFormsId',
			        template :  "# if(procurementFormsId == 0){ #" + 
			        "#= 'đấu thầu rộng rãi' #" + "# } " + 
			        "else if (procurementFormsId == 1) { # " + 
			        "#= 'đấu thầu hạn chế' #"+ "#} " +
			        "else if (procurementFormsId == 2) { # " +
			        "#= 'mua sắm trực tiếp' #"+ "#}  " +
			        "else if (procurementFormsId == 3) { # " +
			        "#= 'chỉ định thầu' #"+ "#}  " +
			        "else if (procurementFormsId == 4) { # " +
			        "#= ': chào hàng cạnh tranh' #"+ "#}  " +
			        "else if (procurementFormsId == 5) { # " +
			        "#= 'tự đấu thầu' #"+ "#}  " +
			        		"#",
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					}
				},  {
					title: "Loại chủ đầu tư",
					 field: 'investmentOwnerType',
			        template :  "# if(investmentOwnerType == 0){ #" + "#= 'Đối tác trong Viettel' #" + "# } " + "else if (investmentOwnerType == 1) { # " + "#= 'Đối tác ngoài Viettel' #"+ "#} #",
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Ngày kí",
			        field: 'signDate',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Giá trị",
			        field: 'price',
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					}
				},{
					title: "Nội dung gói thầu",
			        field: 'content',
			        width: '15%',
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
			        width: '10%',
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
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
				'class="#=appParamId# icon_table" ng-click="vm.remove(dataItem)" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  uib-tooltip="Xóa" translate'+
					'>'+
					'<i class="fa fa-trash" style="color: #337ab7;" ng-hide="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'+
				'<button style=" border: none; background-color: white;" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"'+
				'class="#=appParamId# icon_table"  uib-tooltip="Khóa" translate'+
					'>'+
					'<i class="fa fa-trash"  style="color:grey" ng-show="dataItem.status==0 || dataItem.createdUserId!=dataItem.sysUserId"  aria-hidden="true"></i>'+
				'</button>'
					+'</div>',
			        width: '15%',
			        field:"action"
				}
				,]
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
		},{
			field:"investmentOwnerType",
			data:{
				1:'Đối tác ngoài Viettel',
				0:'Đối tác trong Viettel'
			}
		},{
			field:"procurementFormsId",
			data:{
				1:'đấu thầu rộng rãi',
				0:'đấu thầu hạn chế',
				2:'mua sắm trực tiếp',
				3:'chỉ định thầu',
				4:'chào hàng cạnh tranh',
				5:'tự đấu thầu',
			}
		},
		]
		
		
		vm.exportFile = function exportFile() {
			vm.appParamSearch.page = null;
			vm.appParamSearch.pageSize = null;
			var data = biddingPackageService.doSearch(vm.appParamSearch);
			console.log(data);
			biddingPackageService.doSearch(vm.appParamSearch).then(function(d){
				CommonService.exportFile(vm.appParamGrid,d.data,vm.listRemove,vm.listConvert,"Danh sách gói thầu");
			});
				
		}
		
		
		function refreshGrid(d) {
			var grid = vm.appParamGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
			
			

			vm.add=add;
		  function add(){

			
			vm.appParam={};
			vm.editForm = false;
			 var teamplateUrl="ims/biddingPackage/app_paramPopup.html";
			 var title="Thêm mới gói thầu";
			 var windowId="APP_PARAM";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300',"code"); 
			  
		 }
		
		vm.edit=edit;
		function edit(dataItem){			
			vm.appParam =dataItem;
			var teamplateUrl="ims/biddingPackage/app_paramPopup.html";
			 var title="Cập nhật gói thầu";
			 var windowId="APP_PARAM";
			 $("#appParamGrid").data('kendoGrid').dataSource.read();
			 $("#appParamGrid").data('kendoGrid').refresh();
			 CommonService.populatePopupCreate(teamplateUrl,title,vm.appParam,vm,windowId,false,'1000','300',"code"); 
			 vm.editForm = true;
		}
		
                vm.import=function(){
                    vm.appParam={};
			 var teamplateUrl="ims/biddingPackage/importPackage.html";
			 var title="Import gói thầu từ file excel";
			 var windowId="APP_PARAM";
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'1000','300'); 
                }
		
		vm.save= function(data,isCreateNew){
			data=vm.appParam;
// if(checkErr()){
// if(vm.listApp.mess !==""||vm.listApp.mess1!==""||vm.listApp.mess2!==""){
// $('#parType').focus();
// return vm.listApp;
// }else{
						if(vm.errNumber!==""){
							return false;
						}
						if(isCreateNew) {
							
							biddingPackageService.createAppParam(data).then(function(result){
								if(result.error){
									$('#parType').focus();
									toastr.error(result.error);
									return;
								}
                			toastr.success("Thêm mới thành công!");
                            vm.appParam = result;
                            doSearch();
                            // CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                            vm.add();
                            
                		}, function(errResponse){
    		                if (errResponse.status === 409) {
								
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại!"));
    		                } else {
    		                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo danh mục chức vụ!"));
    		                }
        		        });
                	} else {
                		biddingPackageService.updateAppParam(data).then(function(result){
							if(result.error){
								$('#parType').focus();
								toastr.error(result.error);
								return;
							}
							$("#appParamGrid").data('kendoGrid').dataSource.read();
							$("#appParamGrid").data('kendoGrid').refresh();
                			toastr.success("Cập nhật thành công!");
                			
                			// CommonService.dismissPopup();
							$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
                		}, function(errResponse){
                			if (errResponse.status === 409) {
								$('#parType').focus();
    		                	toastr.error(gettextCatalog.getString("Mã chức vụ đã tồn tại"));
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
				biddingPackageService.remove(dataItem).then(
						function(d) {
							toastr.success("Xóa tham số thành công!");
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

			console.log(grid.dataSource.data());
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
            	biddingPackageService.exportpdf(obj);
            }
			
			
			/*
			 * var specialChars = "<>@!#$%^&*()+[]{}?:;|'\"\\,./~`-="; var
			 * check = function(string){ for(var i = 0; i <
			 * specialChars.length;i++){ if(string.indexOf(specialChars[i]) >
			 * -1){ return true; } } vm.listApp.mess=""; return false; } var
			 * check1 = function(string){ for(var i = 0; i <
			 * specialChars.length;i++){ if(string.indexOf(specialChars[i]) >
			 * -1){ return true; } } vm.listApp.mess1=""; return false; } var
			 * check2 = function(string){ for(var i = 0; i <
			 * specialChars.length;i++){ if(string.indexOf(specialChars[i]) >
			 * -1){ return true; } } vm.listApp.mess2=""; return false; }
			 * 
			 * vm.checkErr = checkErr; function checkErr() { var parType =
			 * $('#parType').val(); var code = $('#code').val(); var
			 * name=$('#name').val(); if(check(parType) === true){
			 * vm.listApp.mess = "Loại tham số chứa ký tự đặc biệt"; }
			 * if(check1(code) === true){ vm.listApp.mess1 = "Mã tham số chứa ký
			 * tự đặc biệt"; } if(check2(name) === true){ vm.listApp.mess2 =
			 * "Tên Tham số ký tự đặc biệt"; } return vm.listApp; }
			 */
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
            
            vm.getExcelTemplate = function(){
    			var fileName="FileBieuMau_GoiThau";
    			CommonService.downloadTemplate(fileName).then(function(d) {
    				data = d.plain();
    				console.log( Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName);
    				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
    			}).catch( function(){
    				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
    				return;
    			});
    		
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
//						var grid = $('#changeAreaGridted').data("kendoGrid");
//						grid.dataSource.data([]);
				     return   $.ajax({
				            url: Constant.BASE_SERVICE_URL+RestEndpoint.BIDDING_PACKAGE_SERVICE_URL+"/importBiddingPackage?folder="+ vm.folder,
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
	        //20180226_hoanm1_start
	        vm.cancelDatePopup = function(){
		    	  vm.appParam.signDate = null;
		    	  vm.appParam.signDate = null;
		      }
	        //20180226_hoanm1_end
	        
	        
	}
	
})();
