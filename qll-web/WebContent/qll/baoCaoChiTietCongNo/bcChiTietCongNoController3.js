(function() {
	'use strict';
	var controllerId = 'bcChiTietCongNoController2';

	angular.module('MetronicApp').controller(controllerId,
			bcChiTietCongNoController2);

	function bcChiTietCongNoController2($scope, $rootScope, $timeout,
			gettextCatalog, kendoConfig, $kWindow, bcChiTietCongNoService,
			CommonService, PopupConst, Restangular, RestEndpoint, Constant) {
		var vm = this;
		vm.showSearch = true;
		vm.isCreateNew = false;
		vm.showDetail = false;
		vm.CongNoSearch2 = {};
		vm.updateSLNV111={};
		vm.updateSLNV113={};
//		vm.user=Constant.userInfo.vpsUserToken.fullName;
		initFormData();
		function initFormData() {
			fillDataTable([]);

			if ($rootScope.stringtile) {
				vm.String = $rootScope.stringtile;
			}

		}
		 $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		// $("#grid .k-grid-content").css({
		// "overflow-y" : "scroll"
		// });

		var record = 0;
		vm.oldSearch = {};
		function fillDataTable(data) {
			// if(data.status !==1){
			// $('#icon').prop("disabled",true);
			// }
			vm.gridOptions = kendoConfig
					.getGridOptions({
						autoBind : true,
						resizable : true,
						scrollable : true,
						sortable : false,
						dataBinding : function() {
							record = (this.dataSource.page() - 1)
									* this.dataSource.pageSize();
						},
						toolbar : [ {
							name : "actions",
							template : '<div class=" pull-left ">'
								+ '<button class="btn btn-qlk padding-search-right margin_right10 TkQLK"'
								+ 'ng-click="vm.importFile()" uib-tooltip="Import file" ng-show="RoleMenu.checkRole.checkRoleImport" aria-hidden="true" translate>Import file</button>'
								+ '<button class="btn btn-qlk padding-search-right margin_right10 excelQLK btn pull-right ng-scope"'
		      					+ 'ng-click="vm.exportExcelGrid()" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC" aria-hidden="true" uib-tooltip="Xuất tổng hợp" translate>Xuất file</button>'
								+ '</div>'
								+ '<div class="btn-group pull-right margin_top_button margin_right10">'
								+ '<i data-toggle="dropdown" uib-tooltip="Cài đặt"  aria-expanded="false"><i class="fa fa-cog" aria-hidden="true"></i></i>'
//								+ '<i class="action-button excelQLK" uib-tooltip="Xuất file excel" ng-click="vm.exportExcelGrid()" aria-hidden="true"></i>'
								+ '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'
								+ '<label ng-repeat="column in vm.bcCongNoGrid13.columns.slice(1,vm.bcCongNoGrid13.columns.length)| filter: vm.gridColumnShowHideFilter">'
								+ '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'
								+ '</label>' + '</div></div>'

						} ],
						dataBound : function(e) {
							// var grid = vm.orderChangeGoodskGrid;
							// grid.tbody.find("tr").dblclick(function (e) {
							// var dataItem = grid.dataItem(this);
							// vm.seeDetail(dataItem)
							// });
						},
						dataSource : {

							serverPaging : true,
							schema : {
								total : function(response) {

									return response.total; // total is returned
									// in
									// the "total" field of
									// the response
								},
								data : function(response) {
									$("#congNoCount12").text("" + response.total);
									vm.count = response.total;
									return response.data;
								},
							},
							transport : {
								read : {
									// Thuc hien viec goi service
									url : Constant.BASE_SERVICE_URL
											+ "TblQltsCongNoVatTuServiceRest/doSearch",
									contentType : "application/json; charset=utf-8",
									type : "POST"
								},
								parameterMap : function(options, type) {

									vm.CongNoSearch2.page = options.page;
									vm.CongNoSearch2.pageSize = options.pageSize;
//									vm.CongNoSearch2.type="0";
									vm.oldSearch=angular.copy(vm.CongNoSearch2);
									return JSON.stringify(vm.CongNoSearch2);

								}
							},
							pageSize : 10
						},
						// dataSource: response,
						noRecords : true,
						columnMenu : false,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả hiển thị")
						},
						pageable : {
							refresh : false,
							pageSizes : [ 10, 15, 20, 25 ],
							messages : {
								display : "{0}-{1} của {2} kết quả",
								itemsPerPage : "kết quả/trang",
								empty : "Không có kết quả hiển thị"
							}
						},
						columns : [

								{
									title : "TT",
									field : "stt",
									width : '60px',
									columnMenu : false,
									template : function() {
										return ++record;
									},
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Thao tác",
									template : '<div class="text-center #=tblQltsCongNoVatTuId#""> '
											+
											'		<button style=" border: none; " class="#=tblQltsCongNoVatTuId# icon_table" aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleNhapLieu&&dataItem.checkNl==null"  uib-tooltip="Cập nhật số lượng" translate>'
											+ '			<i class="fa capnhatsolieu"  ng-click="vm.updateSLNV(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+//
											'		<button style=" border: none; " class="#=tblQltsCongNoVatTuId# icon_table" aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleNhapLieu&&dataItem.checkNl!=null"  uib-tooltip="Nhân viên đã cập nhật số lượng" translate>'
											+ '			<i class="fa dacapnhatsolieu"  ng-click="vm.updateSLNV(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblQltsCongNoVatTuId# icon_table"  aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC&&(dataItem.checkNl!=1||dataItem.checkNl==null)"  uib-tooltip="Xác nhận thông tin của nhân viên" translate>'
											+ '			<i class="fa xacnhansolieu"  ng-click="vm.updateSLNV1(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+
											'		<button style=" border: none; " class="#=tblQltsCongNoVatTuId# icon_table"  aria-hidden="true" ng-show="RoleMenu.checkRole.checkRoleXacNhanThongTinftXuatBC&&(dataItem.checkNl==1||dataItem.checkNl!=null)"  uib-tooltip="Đã xác nhận thông tin của nhân viên" translate>'
											+ '			<i class="fa daxacnhansolieu"  ng-click="vm.updateSLNV1(dataItem)"  aria-hidden="true"></i> '
											+ '		</button>'
											+ '</div>',
									width : '300px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									}
								},
								{
									title : "Đối tượng nhận nợ",
									field : 'doiTuongNhanNo',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
//								{
//									title : "Mã NV",
//									field : 'maNv',
//									width : '100px',
//									headerAttributes : {
//										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
//									},
//									attributes : {
//										style : "text-align:left;"
//									},
//								},
								{
									title : "Đơn vị",
									field : 'donVi',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								
								{
									title : "Số PXK",

									field : 'soPxk',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Ngày chứng từ",
									field : 'ngayChungTu',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:center;"
									},
								},
								{
									title : "Tên vật tư",
									field : 'tenVatTu',
									width : '250px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã vật tư",
									field : 'maVatTu',
									width : '100px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Mã trạm",
									field : 'maTram',
									width : '250px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								}

								,
								{
									title : "Hạng mục",
									field : 'hangMuc',
									// template : "# if(status === '1'){ #" +
									// "#=
									// 'Chưa được duyệt' #" + "# } " + "if
									// (status
									// === '2') { # " + "#= 'Đã duyệt và thực
									// hiện'
									// #"+ "#} if(status === '3'){ #" + "#= 'Đã
									// hủy'
									// #" + "# } if(status === '4'){ #" + "#=
									// 'Đã từ
									// chối' #" + "# }#",
									width : '200px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "ĐVT",
									field : 'dvt',
									width : '150px',
									// template : "# if(signState ==='1'){ #" +
									// "#=
									// 'Chưa trình ký' #" + "# } " + "if
									// (signState
									// ==='2') { # " + "#= 'Đã trình ký' #"+ "#}
									// if(signState === '3'){ #" + "#= 'Đã ký'
									// #" +
									// "# } if(signState === '4'){ #" + "#= 'Đã
									// từ
									// chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Giá",
									field : 'gia',
									width : '150px',
									format : '{0:n0}{1}',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},

								{
									title : "Khối lượng xuất kho",
									// field : 'ngayCongCheDo',
									width : '250px',
									columns : [
											{
												title : "Số lượng",
												field : "klxkSoLuong",
												width : '100px',
												// aggregates : [ "sum" ],
												 format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											},
											{
												title : "Thành tiền",
												field : "klxkThanhTien",
												width : '150px',
												// aggregates : [ "sum" ],
												 format : '{0:n0}{1}',
												// footerTemplate : "<div
												// style='float:
												// right'>#=sum.toLocaleString()#
												// </label>",
												// groupFooterTemplate : "<div
												// style='float: right'>
												// #=sum.toLocaleString()#
												// </label>",
												headerAttributes : {
													style : "text-align:center;font-weight: bold;"
												},
												attributes : {
													style : "text-align:right;"
												},
											} ],
									// template : "# if(signState ==='1'){ #" +
									// "#=
									// 'Chưa trình ký' #" + "# } " + "if
									// (signState
									// ==='2') { # " + "#= 'Đã trình ký' #"+ "#}
									// if(signState === '3'){ #" + "#= 'Đã ký'
									// #" +
									// "# } if(signState === '4'){ #" + "#= 'Đã
									// từ
									// chối' #" + "# }#",
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								},
								{
									title : "Ghi chú",
									field : 'ghiChu',
									width : '200px',
									headerAttributes : {
										style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes : {
										style : "text-align:left;"
									},
								} ]
					});
		}
		// import
		// function getFolder() {
		// Restangular.one(RestEndpoint.QLCN_URL+"/folder").get().then(function(data)
		// {
		// vm.folder = data.folder;
		// });
		// }
		// getFolder();
		
		
		
		
		 vm.updateSLNV = function(dataItem){
        	 var list=[];
        	 vm.checkkk=false;
        	 vm.updateSLNV111={};
        	 vm.updateSLNV113={};
        	 vm.updateSLNV111=dataItem;
        	list.push(dataItem);
        	updateSL(list);
			var teamplateUrl="qll/baoCaoChiTietCongNo/updateTT.html";
		    var title="Cập nhật số lượng PXK B cấp";
		    var windowId="CAP_NHAT_SL_PXK_B_CAP";
		    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','50%');
		}
		 
		 vm.nvUpdateSL1= function(){
	        	vm.prepareDataforSaving();
	        	for(var i=0;i<vm.updateSLNV113.lstCongNo.length;i++){
	        		vm.updateSLNV113.lstCongNo[i].shhtdmSoLuong=null;
		        	vm.updateSLNV113.lstCongNo[i].shhtdmThanhTien=null;
		        	vm.updateSLNV113.lstCongNo[i].smmpbtcctSoLuong=null;
		        	vm.updateSLNV113.lstCongNo[i].smmpbtcctThanhTien=null;
		        	vm.updateSLNV113.lstCongNo[i].sdthThanhTien=null;
		        	vm.updateSLNV113.lstCongNo[i].sdthSoLuong=null;
		        	vm.updateSLNV113.lstCongNo[i].sntSoLuong=null;
		        	vm.updateSLNV113.lstCongNo[i].sntThanhTien=null;
			    	vm.updateSLNV113.lstCongNo[i].checkNl = null;
	        	}
	        	
	        	bcChiTietCongNoService.updateCongNo(vm.updateSLNV113).then(function(result){
	    			if(result.error){
	    				toastr.error(result.error);
	    				return;
	    			}
	    			toastr.success("Hủy xác nhận thành công!");
	    			$("#bcCongNoGrid12").data('kendoGrid').dataSource.read();
	    			$("#bcCongNoGrid12").data('kendoGrid').refresh();
	    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
	    			
	    		}, function(errResponse){
	                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
	            });
	        }
		    
		 
		 vm.checkkk=false;
	        vm.updateSLNV1 = function(dataItem){
		       	var list=[];
	        	 vm.checkkk=true;
	        	 vm.updateSLNV113={};
	        	 vm.updateSLNV1131={};
	        	 vm.updateSLNV113=dataItem;
	        	 list.push(dataItem);
	         	 updateSL(list);
		    	var teamplateUrl="qll/baoCaoChiTietCongNo/updateTT.html";
			    var title="Xác nhận thông tin cập nhật số lượng";
			    var windowId="NV_CAP_NHAT_SL_TYPEB";
			    CommonService.populatePopupCreate(teamplateUrl,title,null,vm,windowId,false,'80%','90%');
    			
			}
		 
		 
		 function updateSL(data) {
			 var dataSource = new kendo.data.DataSource({
	                pageSize: 10,
	                data: data,
	                autoSync: true,    
	                schema: {
							 model: {
				                    id: "stt",
				                	fields: {
				                		stt: {type: "number",editable: false},
				                		doiTuongNhanNo: {editable: false},
				                		tenVatTu : {editable: false},
				                		gia: {editable: false},
				                		klxkSoLuong :{type: "number",editable: false},
				                		klxkThanhTien : {type: "number",editable: false},
				                		shhtdmSoLuong:  { type: "number"},
				                		shhtdmThanhTien: { type: "number"},
				                		smmpbtcctSoLuong: {type: "number"},
				                		smmpbtcctThanhTien: {type: "number"},
				                		sdthSoLuong: {type: "number"},
				                		sdthThanhTien: {type: "number"},
				                		sntSoLuong: {type: "number"},
				                		sntThanhTien: {type: "number"},
				                		   
					                	}
								}
	                }
	            });
			 
				vm.grid1Options = kendoConfig.getGridOptions({
					autoBind: true,
					resizable: true,	
					columnMenu: false,
					scrollable: true,
					dataSource: dataSource ,
					save: function(data) {
						if(data.values.shhtdmSoLuong===null){
							data.values.shhtdmSoLuong=0;
							data.values.shhtdmThanhTien=0;
						}
						if(data.values.smmpbtcctSoLuong===null){
							data.values.smmpbtcctSoLuong=0;
							data.values.smmpbtcctThanhTien=0;
						}
						if(data.values.sdthSoLuong===null){
							data.values.sdthSoLuong=0;
							data.values.sdthThanhTien=0;
						}
						if(data.values.sntSoLuong===null){
							data.values.sntSoLuong=0;
							data.values.sntThanhTien=0;
						}
						
				         if (data.values.sntSoLuong!=undefined) {
				            var test = data.model.set("sntThanhTien", data.values.sntSoLuong * data.model.gia);
				        }
				        else if(data.values.sdthSoLuong!=undefined){
				            var test = data.model.set("sdthThanhTien", data.values.sdthSoLuong * data.model.gia);
				        }else if(data.values.smmpbtcctSoLuong!=undefined){
				            var test = data.model.set("smmpbtcctThanhTien", data.values.smmpbtcctSoLuong * data.model.gia);
				        }else if(data.values.shhtdmSoLuong!=undefined){
				            var test = data.model.set("shhtdmThanhTien", data.values.shhtdmSoLuong * data.model.gia);
				        }
				    },
					noRecords: true,
					messages: {
						noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
					},
					pageable: false,
					columns: [
					{
						title: "TT",
						field:"stt",
				        template: function () {
						  return 1;
						 },
				        width: '50px',
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:center;"
						},
					}, {
						title: "Đối tượng nhận nợ",
						field: "doiTuongNhanNo",
				        width: '200px',
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},  {
						title: "Tên vật tư",
						field: "tenVatTu",
				        width: '200px',
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},{
						title: "Đơn giá",
						field: "gia",
				        width: '100px',
				        format:"{0:n0}",
				        headerAttributes: {
							style: "text-align:center;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}, {
						title: "Khối lượng kho suất",
						columns:[{
								title: "Số lượng",
								field:"klxkSoLuong",
								 width: '100px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							},{
								title: "Thành tiền",
								field:"klxkThanhTien",
								format:"{0:n0}",
								 width: '150px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							}
							
						],
				        width: '250px',
				        headerAttributes: {
							style: "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title: "Số nghiệm thu",
						columns:[{
								title: "Số lượng",
								field:"sntSoLuong",
								 width: '100px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							},{
								title: "Thành tiền",
								field:"sntThanhTien",
								format:"{0:n0}",
								editor: function(cont, options) {
		         		            $("<span>" + options.model.sntThanhTien + "</span>").appendTo(cont);},
								 width: '150px',
								 
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							}
							
						],
				        width: '250px',
				        headerAttributes: {
							style: "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title: "Số hao hụt theo định mức",
						columns:[{
								title: "Số lượng",
								field:"shhtdmSoLuong",
								 width: '100px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							},{
								title: "Thành tiền",
								field:"shhtdmThanhTien",
								format:"{0:n0}",
								editor: function(cont, options) {
		         		            $("<span>" + options.model.shhtdmThanhTien + "</span>").appendTo(cont);},
								 width: '150px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							}
							
						],
				        width: '250px',
				        headerAttributes: {
							style: "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title: "Số đã thu hôì",
						columns:[{
								title: "Số lượng",
								field:"sdthSoLuong",
								 width: '100px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							},{
								title: "Thành tiền",
								field:"sdthThanhTien",
								format:"{0:n0}",
								editor: function(cont, options) {
		         		            $("<span>" + options.model.sdthThanhTien + "</span>").appendTo(cont);},
								 width: '150px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							}
							
						],
				        width: '250px',
				        headerAttributes: {
							style: "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
						},
						attributes: {
							style: "text-align:left;"
						},
					},
					{
						title: "Số mất mát phải bồi thường cho Công ty Công trình",
						columns:[{
								title: "Số lượng",
								field:"smmpbtcctSoLuong",
								 width: '100px',
								
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							},{
								title: "Thành tiền",
								field:"smmpbtcctThanhTien",
								 format:"{0:n0}",
								editor: function(cont, options) {
		         		            $("<span>" + options.model.smmpbtcctThanhTien + "</span>").appendTo(cont);},
								 width: '150px',
							      headerAttributes: {
										style: "text-align:center;"
								},
									attributes: {
										style: "text-align:left;"
								},
							}
						],
				        width: '250px',
				        headerAttributes: {
							style: "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
						},
						attributes: {
							style: "text-align:left;"
						},
					}]
				});
			}
		

		/*
		 * Thực hiện submit file import excel
		 */
		
		vm.exportExcelGrid = function(){
			vm.oldSearch.page=null;
			vm.oldSearch.pageSize=null;
			bcChiTietCongNoService.exportExcelGrid1(vm.oldSearch).then(function(result){
				if (result.fileName) {
					toastr.success("Xuất file thành công!");
					window.location = Constant.BASE_SERVICE_URL
							+ "fileservice/downloadFileATTT?"
							+ data.fileName;
					// return;
				} 

			});	
		}
		vm.submit = submit;
		function submit() {
			
			if ($("#file")[0].files[0] == null) {
				toastr.warning("Bạn chưa chọn file để import");
				return;
			}
			if ($("#file")[0].files[0].name.split('.').pop() != 'xls'
					&& $("#file")[0].files[0].name.split('.').pop() != 'xlsx') {
				toastr.warning("Sai định dạng file");
				return;
			}
			
			var t0 = performance.now();
			$("#upfile").css("display","none");
			$("#modalLoading").css("display","block");
			var loading = $("#modalLoading");
	         kendo.ui.progress(loading, true);
	        
			
			var formData = new FormData();
			formData.append('multipartFile', $('#file')[0].files[0]);
			return $.ajax({
				url : RestEndpoint.BASE_SERVICE_URL + RestEndpoint.QLCN_URL
						+ "/importCongNo1?folder=" + vm.folder,
				type : "POST",
				data : formData,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				cache : false,
				success : function(data) {
					if (data.fileName) {
						toastr.error("Có lỗi trong file import, kiểm tra lại file trả về");
						window.location = Constant.BASE_SERVICE_URL
								+ "fileservice/downloadFileATTT?"
								+ data.fileName;
						// return;
					} else {
						toastr.success("Import thành công!");
						
					}
					kendo.ui.progress(loading, false);
					var t1 = performance.now();
					setTimeout(function() {
						alert("Thời gian thực hiện " + (t1 - t0) + " milliseconds.")
					}, 15);
					vm.doSearch();
					CommonService.closePopup1();
				}
			});

		}

		// all
		vm.importFile = function() {
			var teamplateUrl = "qll/bcThucXuatTheoKy/importPopup.html";
			var title = "Import file";
			var windowId = "IMPORT_BCTXTK";
			CommonService.populatePopupCreate(teamplateUrl, title, null, vm,
					windowId, false, '90%', '25%');
		}

		vm.doSearch = doSearch;
		function doSearch() {
			if(!vm.validator.validate()){
				return false;
			}
			vm.showDetail = false;
			var grid = vm.bcCongNoGrid12;

			if (grid) {
				grid.dataSource.query({
					page : 1,
					pageSize : 10
				});
			}

		}

		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.bcCongNoGrid12.hideColumn(column);
			} else if (column.hidden) {
				vm.bcCongNoGrid12.showColumn(column);
			} else {
				vm.bcCongNoGrid12.hideColumn(column);
			}

		}
		
		
			vm.nvUpdateSL= function(){
				
					    	var listRow=[];
					    	var listRow1=[];
					    	var grid = $("#updateTTGrid").data("kendoGrid");
							var dataGoodFromGrid = $('#updateTTGrid').data("kendoGrid").dataSource.data();
							for(var j = 0; j<dataGoodFromGrid.length;j++){
//								if(dataGoodFromGrid[j].shhtdmSoLuong===null){
//									dataGoodFromGrid[j].shhtdmSoLuong=0;
//									dataGoodFromGrid[j].shhtdmThanhTien=0;
//								}
//								
//								
//								if(dataGoodFromGrid[j].smmpbtcctSoLuong===null){
//									dataGoodFromGrid[j].smmpbtcctSoLuong=0;
//									dataGoodFromGrid[j].smmpbtcctThanhTien=0;
//								}
//								
//								if(dataGoodFromGrid[j].sdthSoLuong===null){
//									dataGoodFromGrid[j].sdthSoLuong=0;
//									dataGoodFromGrid[j].sdthThanhTien=0;
//								}
//								if(dataGoodFromGrid[j].sntSoLuong===null){
//									dataGoodFromGrid[j].sntSoLuong=0;
//									dataGoodFromGrid[j].sntThanhTien=0;
//								}
								
								var currentUid = dataGoodFromGrid[j].uid;
							    var currenRow = grid.table.find("tr[data-uid='" + currentUid + "']");
							    var sum=dataGoodFromGrid[j].shhtdmSoLuong+dataGoodFromGrid[j].smmpbtcctSoLuong
							    		+dataGoodFromGrid[j].sdthSoLuong+dataGoodFromGrid[j].sntSoLuong;
							    if(sum!=0){
									if(dataGoodFromGrid[j].klxkSoLuong!=(sum)){
									    var curren=$(currenRow).addClass("red"+j);
									    listRow.push(curren);
									}else{
										var curren1=$(currenRow).addClass("red"+j);
										listRow1.push(curren1);
									}
							    }else{
							    	if(dataGoodFromGrid[j].klxkSoLuong!=(sum)){
									    var curren=$(currenRow).addClass("red"+j);
									    listRow.push(curren);
									}else{
										var curren1=$(currenRow).addClass("red"+j);
										listRow1.push(curren1);
									}
//							    	var curren=$(currenRow).addClass("red"+j);
//									listRow.push(curren1);
							    }
							    
							}
							if(listRow.length>0){
								for(var i=0;i<listRow1.length;i++){
									$(listRow1[i]).css({"background":"white","color":"black"});
								}
								for(var i=0;i<listRow.length;i++){
									$(listRow[i]).css({"background":"chocolate","color":"lightgoldenrodyellow"});
								}
								toastr.error("∑dữ liệu thực xuất luôn = số lượng xuất");
							    return;
							}
					    	vm.prepareDataforSaving();
					    	if(vm.checkkk){
					    		vm.updateSLNV113.lstCongNo[0].checkNl = 1;
					    	}else{
					    		vm.updateSLNV113.lstCongNo[0].checkNl = 0;
					    	}
					    	bcChiTietCongNoService.updateCongNo(vm.updateSLNV113).then(function(result){
				    			if(result.error){
				    				toastr.error(result.error);
				    				return;
				    			}
				    			toastr.success("Cập nhật thành công!");
				    			$("#bcCongNoGrid12").data('kendoGrid').dataSource.read();
				    			$("#bcCongNoGrid12").data('kendoGrid').refresh();
				    			 $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				    			
				    		}, function(errResponse){
				                	toastr.error(gettextCatalog.getString("Xảy ra lỗi khi cập nhật"));
				            });
				        }
				        

		vm.prepareDataforSaving = function(){
			vm.updateSLNV113.lstCongNo = [];
			var dataGoodFromGrid = $('#updateTTGrid').data("kendoGrid").dataSource.data();
		
			for(var i = 0; i<dataGoodFromGrid.length;i++){
				vm.updateSLNV113.lstCongNo.push(dataGoodFromGrid[i]);
			}
		}
		
		
// vm.listRemove=[{
// title: "Thao tác",
// }

		

	}

})();
