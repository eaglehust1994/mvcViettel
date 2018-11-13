(function() {
	'use strict';
	var controllerId = 'shipmentController';
	
	angular.module('MetronicApp').controller(controllerId, shipmentController);
	
	function shipmentController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,shipmentService,shipmentDetailService,shipmentGoodsService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
        vm.showDetail = false;
        vm.shipmentSearch={};
        vm.shipmentCreate={};
        vm.folder = '';
        vm.goods = [];
        vm.Search={
        		businessType: ""
		};
  		vm.userTemplate='<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.employeeCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName#</div> </div>',
  		vm.userHeaderTemplate='<div class="dropdown-header row text-center k-widget k-header">' +
        '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
        '<p class="col-md-6 text-header-auto">Tên nhân viên</p>' +
        	'</div>';
        vm.commonSearch = {name: '', code: ''};
        vm.commonSearch1 = {fullName: '', employeeCode: ''};
        vm.gridCommon = [ {
			title: "Mã",
			field: "code",
			width: 120
		}, {
			title: "Tên",
			field: "name",
			width: 120
		}];
        vm.gridCommon2 = [ {
			title: "Mã",
			field: "code",
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
        
		vm.shipmentSearch =
		{
				type : 0,
				listStatus : ['1']
		};
		vm.doSearchFile=
		{
				type : 1
		}
		vm.dataReasonDelete = [];
		vm.shipment={};
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.reasonDelete = 
		{
				apply : 10,
				status : 1
		}
		initFormData();
		function initFormData() {
			fillDataTable([]);
			fillFileTable([]);
			fillUpdateTable([]);
			reasonDelete();
			loadFileData();
			if($rootScope.stringtile){
			vm.String=$rootScope.stringtile;
			}
			vm.shipmentSearch.createdBy = Constant.user.vpsUserToken.sysUserId;
			vm.shipmentSearch.fullName = Constant.user.vpsUserToken.fullName;
			vm.shipmentSearch.createdDeptName = Constant.user.VpsUserInfo.departmentName;
		}
		function reasonDelete(){
			shipmentService.getReasonDelete(vm.reasonDelete).then(function(result){
				 vm.dataReasonDelete = result;
			 }, function(errResponse){  
				toastr.error(gettextCatalog.getString("Xảy ra lỗi khi lấy Lý do"));
		     });
		}
		//tìm kiếm lô hàng
		vm.doSearch= doSearch;
		function doSearch(){
			  vm.showDetail = false;
			var grid =vm.shipmentGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
		}
		//End
		
		function refreshGrid(d) {
			var grid = vm.shipmentFileGrid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		//Chọn file tải lên trong màn hình thêm mới
		vm.onSelect = function(e) {
				 if($("#files")[0].files[0].name.split('.').pop() !='xls' && $("#files")[0].files[0].name.split('.').pop() !='xlsx' && $("#files")[0].files[0].name.split('.').pop() !='doc'&& $("#files")[0].files[0].name.split('.').pop() !='docx'&& $("#files")[0].files[0].name.split('.').pop() !='pdf' ){
        		toastr.warning("Sai định dạng file");
        		setTimeout(function() {
              	  $(".k-upload-files.k-reset").find("li").remove();
   			     $(".k-upload-files").remove();
   				 $(".k-upload-status").remove();
   				 $(".k-upload.k-header").addClass("k-upload-empty");
   				 $(".k-upload-button").removeClass("k-state-focused");
         },10);
        		return;
        	}
			 var formData = new FormData();
			jQuery.each(jQuery('#files')[0].files, function(i, file) {
					 formData.append('multipartFile'+i, file);
				});
				
				
			 
	     return   $.ajax({
	            url:Constant.BASE_SERVICE_URL+"fileservice/uploadATTT",
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success:function(data) {
				 $.map(e.files, function(file,index) {
				 var obj={};
				 obj.name=file.name;
				 obj.filePath=data[index];
				 obj.appParam ={
						 code : "choese",
						 name : "~~ Chọn ~~"
				 };
				 vm.dataFile.push(obj);
				 })
            refreshGrid(vm.dataFile);
            setTimeout(function() {
            	  $(".k-upload-files.k-reset").find("li").remove();
 			     $(".k-upload-files").remove();
 				 $(".k-upload-status").remove();
 				 $(".k-upload.k-header").addClass("k-upload-empty");
 				 $(".k-upload-button").removeClass("k-state-focused");
       },10); 
	            }
	        });
	    }
		//Danh sách lô hàng tìm kiếm
		var record=0;
		vm.count=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu: false,
				scrollable: false,
				toolbar: [
                    {
                    	name: "actions",
                        template: '<div class=" pull-left ">'+
                        '<button class="btn btn-qlk padding-search-right addQLK"'+
    					'ng-click="vm.add()" uib-tooltip="Thêm mới" translate><p class="action-button add" aria-hidden="true">Tạo mới</p></button>'+
    					'</div>'	
        				+
        				  '<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	'<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.shipmentGrid.columns.slice(1,vm.shipmentGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
                    }
                    ],
				dataBound: function (e) {
				    var grid = vm.shipmentGrid;
				    grid.tbody.find("tr").dblclick(function (e) {
				        var dataItem = grid.dataItem(this);
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
						
				    });
				},
				dataSource:{
					serverPaging: true,
					 schema: {
						 total: function (response) {
							 	vm.count = response.total;
								document.getElementById('count').innerHTML=vm.count;
								return response.total;
							},
							data: function (response) {
							for(var i in response.data)
							{
							response.data[i].sysUserId=Constant.userInfo.VpsUserInfo.sysUserId
							}
								return response.data;
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							 url: Constant.BASE_SERVICE_URL + "shipmentRsServiceRest/shipment/doSearch",
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
					template:dataItem=> '<a style="text-decoration:none;" class="#=shipmentId#" href="javascript:void(0);" ng-click=vm.showSix(dataItem)>{{dataItem.code}}</a>',
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
			        width: "12%",
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
					title: "Thao tác",
			        template:dataItem =>
			        	'	<div class="text-center #=shipmentId#"">'+
			        	'		<a  type="button"  class="icon_table #=shipmentId#" uib-tooltip="Cập nhật hàng hóa cho lô hàng" translate>'+
			        	'			<i ng-hide="dataItem.status != 1 && dataItem.status != 2|| dataItem.createdBy!=dataItem.sysUserId" class="fa fa-pencil-square-o" ng-click=vm.editDetail(dataItem) aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'		<a  type="button"  class="icon_table #=shipmentId#" uib-tooltip="Khóa" translate>'+
			        	'			<i style="color:grey" ng-show="dataItem.status != 1 && dataItem.status != 2|| dataItem.createdBy!=dataItem.sysUserId" class="fa fa-pencil-square-o" aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'		<a  type="button" class="icon_table #=shipmentId#" uib-tooltip="Cập nhật lô hàng" translate> '+
			        	'			<i ng-hide="dataItem.status != 1|| dataItem.createdBy!=dataItem.sysUserId" class="fa fa-pencil" ng-click=vm.edit(dataItem) aria-hidden="true"></i>'+
			        	'		</a> '+
			        	'		<a  type="button" class="icon_table #=shipmentId#" uib-tooltip="Khóa" translate>'+
			        	'			<i style="color:grey" ng-show="dataItem.status != 1 && dataItem.status != 2|| dataItem.createdBy!=dataItem.sysUserId" class="fa fa-pencil" aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'		<a type="button" class="icon_table #=shipmentId#" uib-tooltip="Xóa" translate >'+
			        	'			<i ng-hide="dataItem.status != 1|| dataItem.createdBy!=dataItem.sysUserId" class="fa fa-trash" ng-click=vm.Openremove(dataItem) aria-hidden="true"></i>'+
			        	'		</a>'+
			        	'       <a  type="button"  class="icon_table #=shipmentId#" uib-tooltip="Khóa" translate>'+
			        	'			<i style="color:grey" ng-show="dataItem.status != 1 && dataItem.status != 2|| dataItem.createdBy!=dataItem.sysUserId" class="fa fa-trash" aria-hidden="true"></i> '+
			        	'		</a> '+
			        	'	</div>',
			        width: "12%",
			        field:"actions"
				}
				,]
			});
		}
		//End
		//Export excel Grid
		vm.exportExcelGrid = function(){
			var ds = $("#shipmentGrid").data("kendoGrid").dataSource;
			var ds1 = ds.data();
			 if (ds1.length == 0){
				 toastr.warning(gettextCatalog.getString("Không có dữ liệu xuất"));
			 }else{
			shipmentService.doSearch(vm.shipmentSearch).then(function(d) {
				CommonService.exportFile(vm.shipmentGrid,d.data,vm.listRemove,vm.listConvert,vm.fileName);
			});
			 } 	 
		}
		//
		//End
		vm.listRemove=[{
			title: "Thao tác",
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
		//Popup Thông tin chi tiết lô hàng
		vm.showSix = function showSix(dataItem){
	    	var dataItem= dataItem;
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
		//End
		//Danh sách hàng hóa của lô hàng được Map
		var record2=0;
		function fillUpdateTable(data) {
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
                    		goodsName: { editable: false },
                    		unitTypeName:{ editable: false },
                    		amount:{type:"number"}
                    	}
                    }
                }
            });
			vm.gridUpdateOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,
				columnMenu: false,	
				dataSource:dataSource,
				noRecords: true,
				scrollable: false,
				dataBinding: function() {
					record2 = (this.dataSource.page() -1) * this.dataSource.pageSize();
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
			       template: dataItem => $("#shipmentUpdateGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
				   width: 50,
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
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng",
					field: 'goodsName',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Đơn vị tính",
					field: 'unitTypeName',
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Hãng sản xuất",
					field: 'manufacturerName',
					editor: manufacturerAutocomplete,
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border: 1px groove  ;"
					},
				},
				{
					title: "Nước sản xuất",
					field: 'producingCountryName',
					editor:producingCountryAutocomplete,
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border: 1px groove  ;"
					},
				},
				{
					title: "Part number",
					field: 'partNumber',
					editor:editorPartNumber,
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;border: 1px groove  ;"
					},
				},
				{
					title: "Serial",
					field: 'serial',
					editor:editorSerial,
			        width: 100,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						"id":"serial",
						style: "text-align:left;border: 1px groove  ;"
					},
				},
				{
					title: "Số lượng",
					field: 'amount',
			        width: 80,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:right;border: 1px groove  ;"
					},
					editor:editNumberWithoutSpinners,
					format:"{0:n2}",
				},{
					field: 'goodsId',
					hidden:true
				},
				{
					field: 'producingCountryId',
					hidden:true
				},
				{
					field: 'manufacturerId',
					hidden:true
				}
				,{
					title: "Thao tác",
			        template:dataItem =>
			        	'<div class="text-center #=shipmentId#"">'+
					'<a type="button"'+
					'class="#=shipmentId# icon_table"  uib-tooltip="Xóa" translate>'+
						'<i class="fa fa-trash" aria-hidden="true" ng-click=caller.deleteShipmentGoods(dataItem)></i>'+
					'</a>'
						+'</div>',
			        width: 100,
					field:'del'
				},
				,]
			});
		}
		//End
		//getGoodsMap
		function getGoodsMap()
		{
			
			vm.goods.shipmentId = vm.shipment.shipmentId;
			
		    shipmentDetailService.getMapGoodsDetail(vm.goods).then(function(d) {
				refreshGrid1(d.plain(),vm.shipmentUpdateGrid);
				$('#shipmentUpdateGrid').data("kendoGrid").dataSource.page(1);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		} 
		function refreshGrid1(d,grid) {
			var grid = grid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		//End
		//Editor PartNumber
		function editorPartNumber(container, options){
			$('<input id= "part" data-bind="value:' +options.field + '"/>')
			.appendTo(container)
			.kendoAutoComplete({});
			 $("#part").attr('maxlength','200');
		}
		//End
		//Editor Serial
		function editorSerial(container, options){
			$('<input id= "serial" data-bind="value:' +options.field + '"/>')
			.appendTo(container)
			.kendoAutoComplete({});
			 $("#serial").attr('maxlength','100');
		}
		//End
		//Editor Amount
		function editNumberWithoutSpinners(container, options) {
			 $('<input id= "amounts" data-bind="value:' + options.field + '"/>')
	            .appendTo(container)
	            .kendoAutoComplete({
	        });
			// Hàng có serial cho phép sửa số lượng
				if(options.model.isSerial==="1"){
								   container.text(options.model[options.field]);
				}
			//End
			 $("#amounts").attr('maxlength','10');
			$("#amounts").keypress(function(event) {
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
		        if(text.length>=10){
		        	event.preventDefault();
		        }
		        if ((text.indexOf('.') != -1) &&
		            (text.substring(text.indexOf('.')).length > 2) &&
		            (event.which != 0 && event.which != 8) &&
		            ($(this)[0].selectionStart >= text.length - 2)) {
		                event.preventDefault();
		        }      
		    });

			$("#amounts").bind("paste", function(e) {
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
		//End Editor Amount
		//Autocomplete trường hãng sản xuất 
		function manufacturerAutocomplete(container,options){
			vm.manufacturer={};
			 $('<input id= "selectedItem" data-bind="value:' + options.field + '"/>')
	            .appendTo(container)
	            .kendoAutoComplete({
	            	select: function(e) {
	  	                var dataItem = this.dataItem(e.item.index());
	  	              vm.manufacturer.manufacturerId = dataItem.manufacturerId; // thành id
	  	           vm.manufacturer.manufacturerName = dataItem.manufacturerName;//thành name
				   options.model.manufacturerId=dataItem.manufacturerId;
				    options.model.manufacturerName=dataItem.manufacturerName;
	  	            },
	            	dataSource:{ 
	            		transport:{
		            	read: function(options) {
	                    return Restangular.all("manufacturerServiceRest/manufacturer/" + 'autocomplete').post({manufacturerName:vm.manufacturer.manufacturerName}).then(function(response){
	                        options.success(response);
	                    }).catch(function (err) {
	                        console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                    });
	                }
	            		}
		            },
		            change: function(e) {
		                if (e.sender.value() === '') {
		                	vm.manufacturer.manufacturerName = null; // thành id
		                	vm.manufacturer.manufacturerId = null;
		                }
		            },
	            suggest: true,
	            filter:"contains",
	            dataTextField: "manufacturerName",
				dataTextValue: "manufacturerId",
	             minLength: 1
	        });
			 var autoComplete =$("#selectedItem").data("kendoAutoComplete");
			  autoComplete.list.width(300);
		}
		//End
		//Autocomplete trường nước sản xuất 
		function producingCountryAutocomplete(container,options){
			vm.producing={};
			 $('<input data-bind="value:' + options.field + '"/>')
	            .appendTo(container)
	            .kendoAutoComplete({
	            	select: function(e) {
	  	                var dataItem = this.dataItem(e.item.index());
	  	              vm.producing.producingCountryId = dataItem.producingCountryId; // thành id
	  	            vm.producing.producingCountryName = dataItem.producingCountryName;//thành name
					options.model.producingCountryId = dataItem.producingCountryId;
				    options.model.producingCountryName = dataItem.producingCountryName;
	  	            },
	            dataSource:{ 
	            	transport:{
	            	read: function(options) {
                    return Restangular.all("producingCountryServiceRest/producingCountry/" + 'autocomplete').post({producingCountryName:vm.producing.producingCountryName}).then(function(response){
                        options.success(response);
                    }).catch(function (err) {
                        console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                    });
                }
	            	}
	            },
	            change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.producing.producingCountryName = null; // thành id
	                	vm.producing.producingCountryId = null;
	                }
	            },
	            dataTextField: "producingCountryName",
				 dataTextValue: "producingCountryId",
	            filter: "contains",
	            minLength: 1
	        });
		}
		
		//End
		//Thông báo lỗi khi import
		function fillDataImportErrTable(data) {
			vm.importGoodResultGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data,
				noRecords: true,
				columnMenu: false,
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
	
		//Popup thêm mới lô hàng
		vm.add = function add(){
			vm.mess="";
			vm.shipmentCreate.code=null;
			vm.shipmentCreate.contractCode=null;
			vm.shipmentCreate.projectCode=null;
			vm.shipmentCreate.type=null;
			vm.shipmentCreate.shiper=null;
			vm.shipmentCreate.shiperDate=null;
			vm.shipmentCreate.shipPlace=null;
			vm.shipmentCreate.description=null;
			vm.shipmentCreate.customsProcedure=null;
			 var teamplateUrl="wms/shipment/shipmentPopup.html";
			 var title="Thêm mới lô hàng";
			 var windowId="SHIPMENT"
			 vm.dataFile = [];
			 CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,true,'80%','80%',"code123"); 
		 }
		//End
		
		//Popup Cập nhật lô  hàng
		vm.dataFile=[];
		vm.edit = function edit(dataItem){
			vm.mess="";
			$("#shipmentGrid").data("kendoGrid").dataSource.read();
			$("#shipmentGrid").data("kendoGrid").refresh();
				vm.showDetail = true;
				vm.shipmentCreate =dataItem;
				var teamplateUrl="wms/shipment/shipmentPopup.html";
				var title="Cập nhật lô hàng";
				var windowId="SHIPMENT"
				vm.doSearchFile.objectId = vm.shipmentCreate.shipmentId;
			    vm.doSearchFile.status = vm.shipmentCreate.status;
				shipmentDetailService.doSearchFile(vm.doSearchFile).then(function(result){
					CommonService.populatePopupCreate(teamplateUrl,title,vm.shipmentCreate,vm,windowId,false,'80%','80%',"code123");
					vm.dataFile=result.plain();
        		});
		}
		//End
		$scope.$watchGroup(['vm.shipmentFileGrid', 'vm.dataFile'], function(newVal, oldVal) { 
			refreshGrid(vm.dataFile);						
		});
		
		//Popup Map hàng hóa cho lô hàng
		vm.editDetail = function editDetail(dataItem){
			vm.goods.name=null;
			vm.showDetail = true;
			vm.shipment =dataItem;
			getGoodsMap();
			var teamplateUrl="wms/shipment/updateShipment.html";
			var title="Cập nhật hàng hóa cho lô hàng";
			var windowId="SHIPMENT";
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'80%','80%','orderGoods2'); 
	}
	$scope.$on('Popup.open',function(){
	getGoodsMap();
	});
		//End
		//Popup Hủy thông tin lô hàng
		vm.Openremove = function Openremove(dataItem){
			vm.showDetail = true;
			vm.shipment =dataItem;
			var d = new Date();
			var datestring = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear()
			vm.shipment.cancelDate = datestring;
			vm.shipment.cancelby = Constant.user.vpsUserToken.sysUserId;
			vm.shipment.cancelbyName = Constant.user.vpsUserToken.fullName;
			var teamplateUrl="wms/popup/Delete_Popup.html";
			var title="Hủy thông tin lô hàng";
			var windowId="SHIPMENT";
			CommonService.populatePopupCreate(teamplateUrl,title,vm.shipment,vm,windowId,false,'60%','35%'); 
	}
		//End
		
		vm.cancel= cancel ;
		function cancel(){
			CommonService.dismissPopup1();
		}
		vm.cancelMap= cancelMap ;
		function cancelMap(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		var saveFile = [];
		$scope.$on("shipment.object.file", function(event, dataItem) {
			if(dataItem === true){
				saveFileSERVICE();
			}
		});
		
		function saveFileSERVICE(){
			if(saveFile.length > 0){
			shipmentService.saveFileSERVICE(saveFile).then(function(result){
    		}, function(errResponse){
                if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Mã lô hàng đã tồn tại"));
					document.getElementById("code123").focus();
                } else {
                	toastr.error(gettextCatalog.getString("Lỗi xuất hiện khi tạo mới lô hàng"));
                }
	        });
			}
		}
		
		//Them moi, cap nhat lo hang
		vm.save= function save(isCreateNew){
			vm.checkIdObject = false;
			vm.check = 0;
			saveFile = [];
			var shipmentFileGridSave = $("#shipmentFileGrid").data().kendoGrid.dataSource.data();
			var gridFile = $("#shipmentFileGrid").data("kendoGrid");
			for(var i=0 ; i < shipmentFileGridSave.length ; i++){
					saveFile.push(shipmentFileGridSave[i]);
			}
			for(var i = 0; i< shipmentFileGridSave.length; i++){
				if(shipmentFileGridSave[i].appParam.code === "choese" || shipmentFileGridSave[i].appParam.name == "~~ Chọn ~~" )
				{
					vm.check = vm.check +1;
					gridFile.editCell(gridFile.tbody.find('tr:eq(' + i + ')').find("td").eq(2));
				}
			}
			if(vm.check === 0){
				if(!isCreateNew) {
	            	shipmentService.createShipment(vm.shipmentCreate).then(function(result){
							if(result.error){
								document.getElementById("code123").focus();
								toastr.error(result.error);
								return;
							}
	            			toastr.success("Thêm mới thành công!");
							if(saveFile.length===0){
	            				saveFile.push(vm.shipmentCreate);
	            			}
							if(saveFile.length > 0){
	                        	for(var i=0;i< saveFile.length;i++){
	                        		saveFile[i].objectId = result;
	                        		saveFile[i].type = "1";
	                        		saveFile[i].code = vm.shipment.code;
	                        		vm.checkIdObject = true;
	                        	}
	                        }
	            			vm.shipmentCreate.code=null;
	            			vm.shipmentCreate.contractCode=null;
	            			vm.shipmentCreate.contractId=null;
	            			vm.shipmentCreate.projectCode=null;
	            			vm.shipmentCreate.projInvestProjectId=null;
	            			vm.shipmentCreate.type=null;
	            			vm.shipmentCreate.shiper=null;
	            			vm.shipmentCreate.shiperDate=null;
	            			vm.shipmentCreate.shipPlace=null;
	            			vm.shipmentCreate.description=null;
	            			vm.shipmentCreate.customsProcedure=null;
	                        doSearch();
	                         $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	                        $rootScope.$broadcast("shipment.object.file", true);
	            		}, function(errResponse){
	    		        });
	            	} else {
          		shipmentService.updateShipment(vm.shipmentCreate).then(function(result){
				if(result.error===vm.shipmentCreate.code.toUpperCase()){
						document.getElementById("code123").focus();
								toastr.error("Mã lô hàng đã tồn tại!");
								return;
				}
						if(result.error){
								document.getElementById("cntCode").focus();
								toastr.error(result.error);
								return;
							}
	            			toastr.success("Cập nhật thành công!");
	            			if(saveFile.length===0){
	            				saveFile.push(vm.shipmentCreate);
	            			}
	            			if(saveFile.length > 0){
	                        	for(var i=0;i< saveFile.length;i++){
	                        		saveFile[i].objectId = vm.shipmentCreate.shipmentId;
	                        		saveFile[i].type = "1";
	                        		saveFile[i].code = vm.shipment.code;
	                        		vm.checkIdObject = true;
	                        	}
	                        }
	            			vm.shipmentCreate.code=null;
	            			vm.shipmentCreate.contractCode=null;
	            			vm.shipmentCreate.projectCode=null;
	            			vm.shipmentCreate.contractId=null;
	            			vm.shipmentCreate.projInvestProjectId=null;
	            			vm.shipmentCreate.type=null;
	            			vm.shipmentCreate.shiper=null;
	            			vm.shipmentCreate.shiperDate=null;
	            			vm.shipmentCreate.shipPlace=null;
	            			vm.shipmentCreate.description=null;
	            			vm.shipmentCreate.customsProcedure=null;
	            			$("#shipmentGrid").data("kendoGrid").dataSource.read();
	            			$("#shipmentGrid").data("kendoGrid").refresh();
	            			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	            			$rootScope.$broadcast("shipment.object.file", true);
	            		}, function(errResponse){
	    		        });
	            	}
				$("#appFile").removeClass('appFile1');
			}else{
				toastr.error("Loại file bắt buộc chọn!");
			}
		}
		//End
		
		// Ghi lai trong man hinh cap nhat hang hoa cho lo hang
		vm.goodsDataforSaving = function goodsDataforSaving(){
			vm.orderGoodsList =[];
				var grid = $("#shipmentUpdateGrid").data("kendoGrid");
				var dataGoodsGrid = $('#shipmentUpdateGrid').data("kendoGrid").dataSource.data();
				for(var i = 0; i < dataGoodsGrid.length;i++){
							if(dataGoodsGrid[i].isSerial==="1"&&dataGoodsGrid[i].serial===""||dataGoodsGrid[i].isSerial==="1"&&dataGoodsGrid[i].serial==null){
							toastr.warning("Hàng quản lý serial trường serial không được trống");
							grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(7));
								return;
							}
							if(dataGoodsGrid[i].isSerial!="1"&&dataGoodsGrid[i].serial!=""&&dataGoodsGrid[i].serial!=null){
							toastr.warning("Hàng không quản lý serial không được nhập trường serial");
							grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(7));
								return;
							}
							if(dataGoodsGrid[i].amount==null||dataGoodsGrid[i].amount<=0){
								toastr.warning("Trường Số lượng nhập không hợp lệ");
								grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(8));
								return;
							}
							else{
								if(dataGoodsGrid[i].isSerial==="1"&&dataGoodsGrid[i].amount!=1){
									toastr.warning("Hàng quản lý serial trường số lượng phải bằng 1 ");
									grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(8));
									return;
								}
							else{
								vm.orderGoodsList.push(dataGoodsGrid[i]);
							}
				}
				}
				for(var j = 0; j < vm.orderGoodsList.length;j++ ){
					vm.orderGoodsList[j].manufacturerName = dataGoodsGrid[j].manufacturerName
					vm.orderGoodsList[j].producingCountryName = dataGoodsGrid[j].producingCountryName
					vm.orderGoodsList[j].partNumber = dataGoodsGrid[j].partNumber
					vm.orderGoodsList[j].serial = dataGoodsGrid[j].serial
					vm.orderGoodsList[j].manufacturerId= dataGoodsGrid[j].manufacturerId
					vm.orderGoodsList[j].producingCountryId= dataGoodsGrid[j].producingCountryId
					vm.orderGoodsList[j].originPrice=dataGoodsGrid[j].originPrice
					vm.orderGoodsList[j].totalOriginPrice=dataGoodsGrid[j].amount*dataGoodsGrid[j].originPrice
					vm.orderGoodsList[j].goodsId=dataGoodsGrid[j].goodsId
					vm.orderGoodsList[j].goodsName=dataGoodsGrid[j].goodsName
					vm.orderGoodsList[j].goodsCode=dataGoodsGrid[j].goodsCode
					vm.orderGoodsList[j].amount=dataGoodsGrid[j].amount
					vm.orderGoodsList[j].unitTypeId=dataGoodsGrid[j].unitTypeId
					vm.orderGoodsList[j].unitTypeName=dataGoodsGrid[j].unitTypeName
					vm.orderGoodsList[j].shipmentId=vm.shipment.shipmentId
				}
			for(var j = 0; j < vm.orderGoodsList.length;j++ ){
					for(var i = 0;i  < vm.orderGoodsList.length;i++ ){
					if(vm.orderGoodsList[j].serial!==""&&vm.orderGoodsList[i].serial!==""&&vm.orderGoodsList[j].serial!=null&&vm.orderGoodsList[i].serial!=null){
					if(i!==j&&vm.orderGoodsList[j].serial.toUpperCase()===vm.orderGoodsList[i].serial.toUpperCase()&&vm.orderGoodsList[j].goodsCode===vm.orderGoodsList[i].goodsCode){
							toastr.warning("Cùng mã hàng trong lô hàng không được trùng Serial");
							grid.editCell(grid.tbody.find('tr:eq(' + i + ')').find("td").eq(7));
								return;
						}
					}
				}
				}
				shipmentService.createShipmentGoods(vm.orderGoodsList).then(function(result){
							if(result.error){
								toastr.error(result.error);
								return;
							}
					toastr.success("Map hàng hóa thành công");
					$('#shipmentUpdateGrid').data('kendoGrid').dataSource.read();
					$('#shipmentUpdateGrid').data('kendoGrid').refresh();
					$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
					doSearch();
				}, function(errResponse) {
				toastr.error("Map hàng hóa thất bại");
				});
			
		}
		
		//End
		
		//Popup don vi
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
		
		vm.onSave=onSave;
		
		function onSave(data){
			vm.shipmentSearch.createdDeptName=data.text;
			vm.shipmentSearch.createdDeptId = data.id;
			$('#shipmentDept').focus();
		}
		
		//End
		
		//Huy thong tin lo hang
		vm.remove = function remove(data){
		confirm('Bạn chắc chắn muốn xóa chứ?',function (){
			shipmentService.remove(data).then(function(result){
    			toastr.success("Hủy thông tin lô hàng thành công!");
				 var sizePage = $("#shipmentGrid").data("kendoGrid").dataSource.total();
								if(sizePage % 10 === 1||sizePage % 15 === 1||sizePage % 20 === 1||sizePage % 25 === 1){
									var currentPage = $("#shipmentGrid").data("kendoGrid").dataSource.page();
									if (currentPage > 1) {
										$("#shipmentGrid").data("kendoGrid").dataSource.page(currentPage - 1);
									}
								}
    			$("#shipmentGrid").data("kendoGrid").dataSource.read();
    			$("#shipmentGrid").data("kendoGrid").refresh();
    			$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
    		}, function(errResponse){
    			if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Lỗi không thể xóa"));
                } else {
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái"));
                }
	        });
		});
		}
		//End
		//Reset Form tìm kiếm lô hàng
		vm.cancelDept = function()
		{
			vm.shipmentSearch.createdDeptName = undefined;
			vm.shipmentSearch.createdDeptId = undefined;
			$('#shipmentDept').focus();
		}
		vm.cancelStatis = function()
		{
			vm.shipmentSearch.listStatus = null;
		$("#listStatus").data("kendoMultiSelect").focus();
		}
		vm.cancelCreatedBy = function()
		{
			vm.shipmentSearch.createName = undefined;
		}
		//End
		vm.addGroupOptions = {
	            dataTextField: "name",
	            select: function(e) {
	                var dataItem = this.dataItem(e.item.index());
	                vm.criteria.useGroupId = dataItem.groupId; // thành id
	                vm.criteria.useGroupName = dataItem.groupCode;// thành
																	// name
	            },
	            pageSize: 10,
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
	                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.criteria.useGroupName,pageSize:vm.addGroupOptions.pageSize }).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.groupAdd #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                    vm.criteria.useGroupId = null; // thành id
	                    vm.criteria.useGroupName = null;// thành name
	                }
	            },
	            ignoreCase: false
	        };
		//End
		// Danh sách các file đính kèm
		function fillFileTable(data) {
			var dataSource = new kendo.data.DataSource({
                 data: data,
                autoSync: false, 
                schema: {
                    model: {
                        id: "shipmentGoodsDetailGrid",
                    	fields: {
                    		stt: {editable: false},
                    		name: {editable: false},
                    		appParam: { defaultValue: { name: "~~Chọn~~", code : "choese"} },
                    		acctions : {editable: false},
                    	}
                    }
                }
            });
			vm.gridFileOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:dataSource,
				noRecords: true,
				columnMenu:false,
				scrollable:false,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},dataBound: function () {
					var GridDestination = $("#shipmentFileGrid").data("kendoGrid");                
			                GridDestination.pager.element.hide();
                },
				columns: [{
					title: "TT",
					field:"stt",
			        template: dataItem => $("#shipmentFileGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 20,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				, {
					title: "Tên file",
					field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
					template :  function(dataItem) {
						        	    	 return "<a href='' ng-click='caller.downloadFile(dataItem)'>" + dataItem.name + "</a>";
					}
				},  {
					title: "Loại file",
					field: 'appParam',
			        width: 150,
			        editor: categoryDropDownEditor, 
			        template: "#=appParam.name#" ,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						"id":"appFile",
						style: "text-align:left;"
					},
				}  ,{
				 title: "Xóa",
				 template: dataItem => 
					'<div class="text-center #=attactmentId#""> '+
						'<a type="button" class="#=attactmentId# icon_table" uib-tooltip="Xóa" translate> '+
							'<i class="fa fa-trash" ng-click=caller.removeRowFile(dataItem) ria-hidden="true"></i>'+
						'</a>'+
					'</div>' ,
				 width: 20,
				 field:"acctions"
				 }
				,],
			});
		}
		//End
		//Data Loại file
		vm.dataRropFile = [];
		function loadFileData(){
			shipmentService.getFileDrop().then(function(result){
				vm.dataRropFile  = result.plain();
    		}, function(errResponse){
    			if (errResponse.status === 409) {
                	toastr.error(gettextCatalog.getString("Lỗi không thể xóa"));
                } else {
                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật trạng thái"));
                }
	        });
		}
		//End
		
		//DropDownList Loại file
		function categoryDropDownEditor(container, options) {
			 $('<input required name="' + options.field + '"/>')
                .appendTo(container)
                .kendoDropDownList({
                    autoBind: false,
                    suggest: true,
                    dataTextField: "name",
                    dataValueField: "code",
                    dataSource: vm.dataRropFile,
                });
        }
		//End
		// Xóa file đính kèm
		vm.removeRowFile = removeRowFile;
		function removeRowFile(dataItem) {
			confirm('Xác nhận xóa',function (){
				$('#shipmentFileGrid').data('kendoGrid').dataSource.remove(dataItem);
				 vm.dataFile.splice(dataItem,1);
			})
		}
		//End
			//CancelUser
		vm.cancelUser = function(id)
		{
			if(id==="fullName"){
				vm.shipmentSearch.fullName=null;
			}
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.shipmentGrid.hideColumn(column);
            } else if (column.hidden) {
            	vm.shipmentGrid.showColumn(column);
            } else {
            	vm.shipmentGrid.hideColumn(column);
            }
        	
        	
        }
		// Đóng popup thông báo lỗi import
		vm.closeErrImportPopUp = function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		}
		//Hàm export file lỗi
		vm.exportExcelErr = function(){
			shipmentService.downloadErrorExcel(vm.objectErr).then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			})
		}
		//End
		//Hàm export file biểu mẫu
		vm.getExcelTemplate = function(){
			shipmentService.downloadTemplate().then(function(d) {
				data = d.plain();
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			})
		}
		//End
			//Hàm download file 
		vm.downloadFile = function(dataItem){
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.filePath;	
		}
		//End
		/*
		 * * Filter các cột của select
		 */	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
            
        // Upload file by drag and click
            $scope.fileUploadFinished = false;
            $scope.onFileSelect = function($files) {
                for (var i = 0; i < $files.length; i++) {
                    var file = $files[i];
                    $scope.upload = $upload.upload({
                        url: 'system/upload.php',
                        method: 'POST',
                        file: file
                    }).progress(function(evt) { 
                         // progress bar ...
                    }).success(function(data, status, headers, config) {
                        // file is uploaded successfully
                    });
                }
            };
            //End
            //AutoCompleteDeprt
            vm.headerTemplate4='<div class="dropdown-header row text-center k-widget k-header">' +
  	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Đơn vị</p>' +
  	      '<p class="col-md-6 text-header-auto">Tên Đơn vị</p>' +
  	      	'</div>';
			vm.selectedDept = false;
  		vm.deprtOptions = {
  	            dataTextField: "text",
  	            select: function(e) {
				vm.selectedDept = true;
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
						if(processSearch('shipmentDept',vm.selectedDept)){	
					vm.shipmentSearch.createdDeptId = null; // thành id
					 vm.shipmentSearch.createdDeptName = null;//thành name
					 vm.selectedDept = false;
					 }
  	                if (e.sender.value() === '') {
  	                	 vm.shipmentSearch.createdDeptId = null; // thành id
  	                	vm.shipmentSearch.createdDeptName = null;//thành name
  	                }
  	            },
  	            ignoreCase: false
  	        };
  		//End-AutocompleteDeprt
  		
            //AutoCompleteUser
            vm.headerTemplate1='<div class="dropdown-header row text-center k-widget k-header">' +
  	      '<p class="col-md-6 text-header-auto border-right-ccc">Mã Nhân Viên</p>' +
  	      '<p class="col-md-6 text-header-auto">Tên Nhân Viên</p>' +
  	      	'</div>';
  		vm.usersOptions = {
  	            dataTextField: "fullName",
  	            select: function(e) {
  	                var dataItem = this.dataItem(e.item.index());
  	              vm.shipmentSearch.employeeCode = dataItem.employeeCode; // thành id
  	              vm.shipmentSearch.fullName = dataItem.fullName;//thành name
  	            },
  	            pageSize: 10,
  	            dataSource: {
  	                serverFiltering: true,
  	                transport: {
  	                    read: function(options) {
  	                        return Restangular.all("sysUserServiceRest/sysUserwms/" + 'getForAutoComplete').post({fullName:vm.shipmentSearch.fullName,pageSize:vm.usersOptions.pageSize}).then(function(response){
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
  	                	vm.shipmentSearch.employeeCode = null; // thành id
  	                	vm.shipmentSearch.fullName = null;//thành name
  	                }
  	            },
  	            ignoreCase: false
  	        };
  		//End
  	  //AutoCompleteContractCode
        vm.headerTemplate2='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto">Mã hợp đồng</p>' +
	      '<p class="col-md-6 text-header-auto">Tên hợp đồng</p>'
	      	'</div>';
			vm.selectedCntCode=false;
		vm.contractOptions = {
	            dataTextField: "contractCode",
	            select: function(e) {
				vm.selectedCntCode=true;
	                var dataItem = this.dataItem(e.item.index());
	                vm.shipmentCreate.contractCode = dataItem.contractCode; // thành id
	                vm.shipmentCreate.contractId = dataItem.contractId; 
	                vm.shipmentCreate.cntTypeId = dataItem.cntTypeId;
	            },
	            pageSize: 10,
				 open: function(e) {
	                        vm.selectedCntCode = false;
	                    },
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
						vm.selectedCntCode=false;
	                        return Restangular.all("iCntContractRsServiceRest/iCntContract/" + 'getForAutoComplete').post({contractCode:vm.shipmentCreate.contractCode,contractName:vm.shipmentCreate.contractName,cntTypeId:vm.shipmentCreate.type,pageSize:vm.contractOptions.pageSize}).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.contractCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.contractName #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.shipmentCreate.contractCode = null; // thành id
	                }
	            },
	            ignoreCase: false
	        };
		
		//End
		 //AutoCompleteProjectCode
        vm.headerTemplate3='<div class="dropdown-header row text-center k-widget k-header">' +
	      '<p class="col-md-6 text-header-auto">Mã dự án</p>' +
	      '<p class="col-md-6 text-header-auto">Tên dự án</p>' 
	      	'</div>';
			vm.selectedPrpject=false;
		vm.projectOptions = {
	            dataTextField: "projectCode",
	            select: function(e) {
				vm.selectedPrpject=true;
	                var dataItem = this.dataItem(e.item.index());
	                console.log(dataItem);
	                vm.shipmentCreate.projectCode = dataItem.projectCode; // thành id
	                vm.shipmentCreate.projInvestProjectId = dataItem.projInvestProjectId;
	            },
	            pageSize: 10,
				 open: function(e) {
	                        vm.selectedPrpject = false;
	                    },
	            dataSource: {
	                serverFiltering: true,
	                transport: {
	                    read: function(options) {
						vm.selectedPrpject=false;
	                        return Restangular.all("iProjInvestProjectRsServiceRest/iProjInvestProject/" + 'getForAutoComplete').post({projectCode:vm.shipmentCreate.projectCode,pageSize:vm.contractOptions.pageSize}).then(function(response){
	                            options.success(response);
	                        }).catch(function (err) {
	                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
	                        });
	                    }
	                }
	            },
	            template:'<div class="row" ><div class="col-xs-5" style="float:left">#: data.projectCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
	            change: function(e) {
	                if (e.sender.value() === '') {
	                	vm.shipmentCreate.projectCode = null; // thành id
	                }
	            },
	            ignoreCase: false
	        };
		//Reset data textbox
		vm.cancelShipment=function(id){
			if(id==='contract'){
				vm.shipmentCreate.contractCode=null;
			}
			if(id==='project'){
				vm.shipmentCreate.projectCode=null;
			}
		}
		// /END
		
		//==================================Upload import===================================//
		vm.submit=submit;
        function submit(data){
        	if($("#fileS")[0].files[0] == null){
        		toastr.warning("Bạn chưa chọn file để import");
        		return;
        	}
        	if($("#fileS")[0].files[0].name.split('.').pop() !='xls' && $("#fileS")[0].files[0].name.split('.').pop() !='xlsx' ){
        		toastr.warning("Sai định dạng file");
        		return;
        	}
	        var formData = new FormData();
			formData.append('multipartFile', $('#fileS')[0].files[0]); 
	     return   $.ajax({
	            url:Constant.BASE_SERVICE_URL+"shipmentRsServiceRest/shipment/importGoods?folder="+ vm.folder,
	            type: "POST",
	            data: formData,
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success:function(data) {
				var dem=0;
	            	if(data[data.length - 1].lstErrorGoods!=null && data[data.length - 1].lstErrorGoods.length!=0){
	            		vm.lstErrImport = data[data.length - 1].lstErrorGoods;
	            		vm.objectErr = data[data.length - 1];
	            		var teamplateUrl="wms/shipment/importResultPopUp.html";
	       			 var title="Kết quả Import";
	       			 var windowId="ERR_IMPORT";
	       			
	       			 CommonService.populatePopupCreate(teamplateUrl,title,vm.lstErrImport,vm,windowId,false,'60%','60%');
	       			fillDataImportErrTable(vm.lstErrImport);
					return ;
	            	}
					else if(data.length===1){
					toastr.warning("File import không có dữ liệu");
					return;
					}else{
	            		$("#shipmentUpdateGrid").data("kendoGrid").dataSource.read();
						$("#shipmentUpdateGrid").data("kendoGrid").refresh();
	            		data.splice(data.length - 1, 1);
		            	var grid = $("#shipmentUpdateGrid").data("kendoGrid");
		            	for(var i = 0; i<data.length;i++){
		            		data[i].id = i+1;
		            		data[i].goodsId=data[i].goodsId;
		            		data[i].goodsCode = data[i].code;
		            		data[i].goodsName = data[i].name;
							if(data[i].manufacturerId==null){
							data[i].manufacturerId="";
							data[i].manufacturerName="";
							}
							else{
		            		data[i].manufacturerId = data[i].manufacturerId;
							data[i].manufacturerName = data[i].manufacturerName;
							}
							if( data[i].producingCountryId==null){
							 data[i].producingCountryId="";
							 data[i].producingCountryName="";
							 }
							 else{
		            		data[i].producingCountryId = data[i].producingCountryId;
		            		data[i].producingCountryName = data[i].producingCountryName;
							}
		            		data[i].partNumber = data[i].partNumber;
		            		data[i].serial = data[i].serial;
		            		data[i].isSerial = data[i].isSerial;
		            		data[i].unitTypeName = data[i].goodsUnitName;
		            		data[i].unitTypeId = data[i].unitType;
		            		data[i].originPrice = data[i].originPrice;
		            		data[i].amount=data[i].amount;
		            		if(data[i].partNumber.length>100){
		            			toastr.warning("PartNumber không được vượt quá maxlength!");
		            			return;
		            		}
		            		if(data[i].serial.length>100){
		            			toastr.warning("Serial không được vượt quá maxlength!");
		            			return;
		            		}
		            		if(data[i].amount.length>10){
		            			toastr.warning("Số lượng không vượt quá maxlength!");
								return;
		            		}
			                else{
			                	 grid.dataSource.add(data[i]);
			                 }  
		            	}
						 toastr.success("Import thành công!");
	            	}
	                
	            }
	        });
	      
        
        }
        //================================End Upload Import=====================================//
        // Hàm check xem mặt hàng đã tồn tại trong lưới chưa
        function checkDups(goodsItem){
			var isExisted = false;
			var goodsGrid = vm.shipmentUpdateGrid;
			var ds = $("#shipmentUpdateGrid").data("kendoGrid").dataSource.data();
				if(ds.length!=0){
          for(var i=0;i<ds.length;i++){
    					if(goodsItem.goodsId === ds[i].goodsId && goodsItem.isSerial !=='1'){
    						isExisted = true;
    					}
    			}
				}
            return isExisted;
		}
        //End
        //Autocomplete Bổ sung hàng hóa
        vm.goods={};
    vm.ShipKOption={
			dataTextField: "name",
			placeholder:"Nhập mã hàng, tên hàng bổ sung",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.goods.goodsId = dataItem.goodsId; 
                vm.goods.goodsCode = dataItem.code;
                vm.goods.isSerial = dataItem.isSerial;
                vm.goods.goodsName = dataItem.name;
                vm.goods.unitTypeId = dataItem.goodsUnitId;
                vm.goods.unitTypeName = dataItem.goodsUnitName;
                vm.goods.manufacturerName = dataItem.manufacturerName;
                vm.goods.manufacturerId = dataItem.manufacturerId;
                vm.goods.producingCountryName = dataItem.producingCountryName;
                vm.goods.producingCountryId = dataItem.producingCountryId;
                vm.goods.orderGoodsId = null;
                vm.goods.originPrice=dataItem.originPrice;
				if(vm.goods.isSerial==="1"){
				 var grid = $("#shipmentUpdateGrid").data("kendoGrid");
				vm.goods.amount=1;
				//grid.datasource.fields.amount.editable = false;
				}
				else{
				vm.goods.amount=null;
				}
                var grid = $("#shipmentUpdateGrid").data("kendoGrid");
				 var dt = $("#shipmentUpdateGrid").data("kendoGrid").dataSource.data();
                var check = checkDups( vm.goods);
                if(check){
                	toastr.warning("Mặt hàng đã tồn tại trong lưới!");
                }else{
                	dt.splice(0, 0, vm.goods);
                }        
                
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("goodsRsServiceRest/" + 'getGoodsForOrder').post({pageSize:20, page:1, keySearch:$("#orderGoods2").val()}).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
            '<p class="col-md-6 text-header-auto border-right-ccc">Mã hàng</p>' +
            '<p class="col-md-6 text-header-auto">Tên hàng</p>' +
            	'</div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.code #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                	vm.goods.goodsId = null;
                    vm.goods.goodsCode = null;
                    vm.goods.isSerial = null;
                    vm.goods.goodsName = null;
                    vm.goods.unitTypeId = null;
                    vm.goods.unitTypeName = null;
                    vm.goods.manufacturerName =null;
                    vm.goods.manufacturerId = null;
                    vm.goods.producingCountryName = null;
                    vm.goods.producingCountryId = null;
                    vm.goods.orderGoodsId = null;
                    vm.goods.originPrice=null;
                }
            },
            close: function(e) {
                // handle the event
            	document.getElementById("orderGoods2").value = "";
              }
		};
	//End
    
    
  //delShipmentGoodsDetail
	vm.deleteShipmentGoods=function(dataItem){
		confirm('Xác nhận xóa',function (d){
			$('#shipmentUpdateGrid').data('kendoGrid').dataSource.remove(dataItem);
			 var sizePage = $("#shipmentUpdateGrid").data("kendoGrid").dataSource.total()+1;
								if(sizePage % 10 === 1||sizePage % 15 === 1||sizePage % 20 === 1||sizePage % 25 === 1){
									var currentPage = $("#shipmentUpdateGrid").data("kendoGrid").dataSource.page();
									if (currentPage > 1) {
										$("#shipmentUpdateGrid").data("kendoGrid").dataSource.page(currentPage - 1);
										}
								}
		} )
}
	//End
	
	//setFocus
	setTimeout(function(){
		  $("#shipmt").focus();
		},15);
	//End
	//Close popup
		$(document).on("click",".k-overlay",function(){
			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
		});
	//
	//Check contractCode
		vm.mess="";
		 $scope.$watch("vm.shipmentCreate.contractCode",function(){
			$("#cntCode").on("keydown keyup",function(e){
				 if ((vm.shipmentCreate.type==null)&&e.keyCode != 46&& e.keyCode != 8) {
				vm.mess="Bạn chưa chọn loại lô hàng";
				$("#type123").data("kendoDropDownList").focus();
				e.preventDefault();
		    	 $("#cntCode").val(null);
				 }
			});
		}); 
		//
		//
		 $scope.$watch("vm.shipmentCreate.type",function(){
			if(vm.shipmentCreate.type!=null){
			vm.mess="";
			}
			if((vm.shipmentCreate.cntTypeId===1||vm.shipmentCreate.cntTypeId===null)&&vm.shipmentCreate.type==="2")
						{
						 $("#cntCode").val(null);
						}
			if((vm.shipmentCreate.cntTypeId===2||vm.shipmentCreate.cntTypeId===null)&&vm.shipmentCreate.type==="1")
						{
							 $("#cntCode").val(null);
						}
						if(vm.shipmentCreate.type==null&&vm.shipmentCreate.contractCode!=null){
						 $("#cntCode").val(null);
						}
		}); 
		// End
		//clear data
			vm.changeDataAuto=changeDataAuto
		function changeDataAuto(id){
		if(vm.mess!=""){
		document.getElementById('scode').innerHTML="";
		}
		switch(id){
			case 'proj':{
			if(processSearch(id,vm.selectedPrpject)){
			 vm.shipmentCreate.projectCode = null; // thành id
	         vm.shipmentCreate.projInvestProjectId = null;
			  vm.selectedPrpject=false;	
			 }
					break;
					}
				
			case 'cntCode':{
			if(processSearch(id,vm.selectedCntCode)){
			 vm.shipmentCreate.contractCode = null; // thành id
	                vm.shipmentCreate.contractId = null; 
	                vm.shipmentCreate.cntTypeId = null;
			}
					break;
					}
					case 'abcd':{
			 vm.shipmentSearch.employeeCode = null; // thành id
  	                	vm.shipmentSearch.fullName = null;//thành name
					break;
					}
					
						}
		
			
		}
		//End
		//Enter Search
		$("#shipmentIdSearch").on("keypress", function (e) {
            if (e.keyCode === 13) {
            	$("#findParam").click();
            }
            });
			//End
			//Enter Tao moi lo hang
		$(document).on("keypress", function (e) {
         		switch(e.keyCode) {
         			case 27:
         				$("#cancel11").click();
         				break;
         			case 13:
					if($(':focus').size()===0){
					$( "#sav" ).click(function( event ) {
							event.stopPropagation();
							});
							break;
					}
					 if (e.keyCode === 13&& !$('#sav:focus').length&& !$('#cancel11:focus').length&&!$('#confirmPopup_btnConfirm:focus').length&&!$('#confirmPopup_btnCancel:focus').length) {
         				$("#sav").click();
         				break;
						}
         		}
         	} );
			//End
			//Enter Map hang hoa cho lo hang
		$(document).on("keypress", function (e) {
         		switch(e.keyCode) {
         			case 27:
         				$("#cancel").click();
         				break;
         			case 13:
					if($(':focus').size()===0){
					$( "#saveGoods1" ).click(function( event ) {
							event.stopPropagation();
							});
							break;
					}
         				 if (e.keyCode == 13&& !$('#saveGoods1:focus').length&& !$('#cancelMap:focus').length
						 &&!$('#confirmPopup_btnConfirm:focus').length &&!$('#confirmPopup_btnCancel:focus').length&&!$('#fileS:focus').length
						 &&!$('#bntFiles:focus').length&&!$('#linkFile:focus').length) {
         				$("#saveGoods1").click();
         				break;
						}
         		}
         	} );
			//end
		}
})();
