
(function() {
	'use strict';
	var controllerId = 'tinhLuongDayMayController';
	
	angular.module('MetronicApp').controller(controllerId, tinhLuongDayMayController);
	
	function tinhLuongDayMayController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant,tinhLuongDayMayService,
			
			) {
		var vm = this;
		vm.showSearch = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.tinhLuongDayMaySearch = {};
		vm.typeKASearch={};
		vm.oldSearch={};
		vm.showAdvancedSearch=false;
		vm.tinhLuongDM={};
		vm.tinhLuongDM.exThang="";
		vm.tinhLuongDM.exNam="";
		$(document).ready(function() {
			// getApply();
			fillDataTable();
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		});
		 $("input").change(function(){
		       $(this).val($.trim($(this).val()));
		      });
		 
		initFormData();
		function initFormData() {
			fillDataTable([]);
			if($rootScope.stringtile){
				vm.String=$rootScope.stringtile;
				}
		}
		
		vm.advancedSearch =function(){
			vm.showAdvancedSearch = !vm.showAdvancedSearch;
		}
		
		
		// table chinh
		var record=0;
		function fillDataTable(data) {
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,	
				columnMenu: false,
				scrollable: true,
				toolbar: [
		                    {
		                    	name: "actions",
		                        template: '<div class=" pull-left">'
		      					+'</div>'
		      					+'</div>'	
		      					+
		      					 
							'<div class="btn-group pull-right margin_top_button margin_right10">'+
	                      	 '<i data-toggle="dropdown" class="tooltip1" aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Cài đặt</span><i class="fa fa-cog" aria-hidden="true"></i></i>'+
	                      	'<i class="tooltip1 action-button excelQLK" ng-click="vm.exportExcelGrid()"  aria-expanded="false"><span class="tooltipArrow"></span><span class="tooltiptext">Xuất Excel</span></i>'+
		                    '<div class="dropdown-menu hold-on-click dropdown-checkboxes" role="menu">'+
		                    '<label ng-repeat="column in vm.tinhLuongDayMayGrid.columns.slice(1,vm.tinhLuongDayMayGrid.columns.length)| filter: vm.gridColumnShowHideFilter">'+
		                    '<input type="checkbox" checked="column.hidden" ng-click="vm.showHideColumn(column)"> {{column.title}}'+
		                    '</label>'+
		                    '</div></div>'
		                    
		                    }
		                    ],
							dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				dataSource: {
					serverPaging: true,
					 schema: {
						 total: function (response) {
						 
						 $("#tinhLuongDayMayGridCount11").text(""+response.data.length);
							 
							},
							data: function (response) {
								var list=response.data;
				        		for(var x in list){
				        			for(var i in $scope.listCheck){
				        				if(list[x].orderId===$scope.listCheck[i].orderId){
				        					list[x].selected=true;
				        				}
				        			}
				        		}
				        		return list;// data is returned in
														// the "data" field of
														// the response
							},
		                },
					transport: {
						read: {
		                        // Thuc hien viec goi service
							url: Constant.BASE_SERVICE_URL + "tblLuongDayMayServiceRest/doSearch",
							contentType: "application/json; charset=utf-8",
							type: "POST"
						},					
						parameterMap: function (options, type) {
						
								vm.tinhLuongDayMaySearch.page = options.page
								vm.tinhLuongDayMaySearch.pageSize = options.pageSize                               
								vm.oldSearch = angular.copy(vm.tinhLuongDayMaySearch);
								return JSON.stringify(vm.tinhLuongDayMaySearch)
						}
					},					 
					pageSize:10
				} ,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					 pageSizes:   [ 10, 15, 20, 25 ],
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
			        template: function () {
					  return ++record;
					 },
			        width: '50px',
			        headerAttributes: {
						style: "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:center;"
					},
				},{
					title : "Thao tác",
					template : '<div class="text-center #=tblLuongDayMayId#""> '
							+
							'		<button style=" border: none; " class="#=tblLuongDayMayId# icon_table" ng-hide="dataItem.maTinh!=null"  uib-tooltip="Huỷ" translate>'
							+ '			<i class="fa fa-trash" style="margin-top: 10px;"   aria-hidden="true"></i> '
							+ '		</button>'
							
							 + '</div>',
					width : '50px',
					headerAttributes : {
						style : "text-align:center;font-weight:bold;white-space:normal;"
					}
				}
				,  {
					title: "Lương duy trì",
					columns:[
						{
							title: "Tháng",
							field: "thang",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},
						{
							title: "Tỉnh",
							field: "tinh",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Huyện",
							field: "huyen",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Mã NV",
							field: "maNv",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Họ tên",
							field: "hoTen",
					        width: '200px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Ki đơn vị",
							field: "kiDonVi",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Hệ số điều chỉnh",
							field: "heSoDieuChinh",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Số dây thuê bao quy đổi",
							field: "soDayTbQuiDoi",
					        width: '150px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Đơn giá",
							field: "donGia",
					        width: '150px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Ngày công tính lương",
							field: "ngayCongTinhLuong",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Ngày công chế độ",
							field: "ngayCongTinhLuong",
					        width: '100px',
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},
					],
			        width: '2000px',
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 	
				{
					title: "Phí bán hàng",
					field: "phiBanHang",
			        width: '150px',
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, 
				{
					title: "Triển khai mới",
			        columns:[
			        	{
			        		title: "Triển khai mới",
						     width: '500px',
						     columns:[
						    	 {
						    		 title: "0-2 ngày",
						    		 columns:[
								    	 {
								    		 title: "80,000",
										     	field: "tkm0",
										        width: '100px',
										        headerAttributes: {
										        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
												},
												attributes: {
													style: "text-align:left;"
												},
								    	 }
								    	 ],
								        width: '100px',
								        headerAttributes: {
								        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
										},
										attributes: {
											style: "text-align:left;"
										},
							    	 },
							    	 {
							    		 title: "3 ngày",
							    		 columns:[
									    	 {
									    		 title: "80,000",
											     	field: "tkm3",
											        width: '100px',
											        headerAttributes: {
											        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
													},
													attributes: {
														style: "text-align:left;"
													},
									    	 }
									    	 ],
									        width: '100px',
									        headerAttributes: {
									        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
											},
											attributes: {
												style: "text-align:left;"
											},
								    	 }, {
								    		 title: "4 ngày",
								    		 columns:[
										    	 {
										    		 title: " (200,000)",
												     	field: "tkm4",
												        width: '100px',
												        headerAttributes: {
												        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
														},
														attributes: {
															style: "text-align:left;"
														},
										    	 }
										    	 ],
										        width: '100px',
										        headerAttributes: {
										        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
												},
												attributes: {
													style: "text-align:left;"
												},
									    	 }, {
									    		 title: "≥ 5 ngày",
									    		 columns:[
											    	 {
											    		 title: "(400,000)",
													     	field: "tkm5",
													        width: '100px',
													        headerAttributes: {
													        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
															},
															attributes: {
																style: "text-align:left;"
															},
											    	 }
											    	 ],
											        width: '100px',
											        headerAttributes: {
											        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
													},
													attributes: {
														style: "text-align:left;"
													},
										    	 },
							     ],
						        headerAttributes: {
						        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
								},
								attributes: {
									style: "text-align:left;"
								},
					        },{
				        		title: "Thêm dịch vụ trên đường dây có sẵn",
							     width: '500px',
							     columns:[
							    	 {
							    		 title: "0-2 ngày",
							    		 columns:[
									    	 {
									    		 title: "20,000",
											     	field: "tdv0",
											        width: '100px',
											        headerAttributes: {
											        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
													},
													attributes: {
														style: "text-align:left;"
													},
									    	 }
									    	 ],
									        width: '100px',
									        headerAttributes: {
									        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
											},
											attributes: {
												style: "text-align:left;"
											},
								    	 },
								    	 {
								    		 title: "3 ngày",
								    		 columns:[
										    	 {
										    		 title: "20,000",
												     	field: "tdv3",
												        width: '100px',
												        headerAttributes: {
												        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
														},
														attributes: {
															style: "text-align:left;"
														},
										    	 }
										    	 ],
										        width: '100px',
										        headerAttributes: {
										        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
												},
												attributes: {
													style: "text-align:left;"
												},
									    	 }, {
									    		 title: "4 ngày",
									    		 columns:[
											    	 {
											    		 title: "(50,000)",
													     	field: "tdv4",
													        width: '100px',
													        headerAttributes: {
													        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
															},
															attributes: {
																style: "text-align:left;"
															},
											    	 }
											    	 ],
											        width: '100px',
											        headerAttributes: {
											        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
													},
													attributes: {
														style: "text-align:left;"
													},
										    	 }, {
										    		 title: "≥ 5 ngày",
										    		 columns:[
												    	 {
												    		 title: "(100,000)",
														     	field: "tdv5",
														        width: '100px',
														        headerAttributes: {
														        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
																},
																attributes: {
																	style: "text-align:left;"
																},
												    	 }
												    	 ],
												        width: '100px',
												        headerAttributes: {
												        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
														},
														attributes: {
															style: "text-align:left;"
														},
											    	 },
								     ],
							        headerAttributes: {
							        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
									},
									attributes: {
										style: "text-align:left;"
									},
						        },
					        
					       ],
			        width: '1000px',
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}, {
					title: "Phạt xử lý sự cố",
			        field: "phatXlsc",
			        width: '100px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Phạt phản ánh từ khách hàng",
			        field: "phatPakh",
			        width: '100px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Quá hạn rút port và đóng việc trên hệ thống trong vòng 10 ngày (tính từ ngày giao việc)",
			        field: "loi1",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Đóng việc hệ thống nhưng không tổ chức rút port gây thất thoát cước",
			        field: "loi2",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Rút port vật lý nhưng không đóng việc hệ thống, dẫn đến khiếu nại KH do cước phát sinh",
			        field: "loi3",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Quá hạn cắm port ngay sau khi KH đóng cước hoặc có yêu cầu trong vòng 1 ngày",
			        field: "loi4",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Quá hạn rút port và đóng việc trên hệ thống trong vòng 10 ngày (tính từ ngày giao việc)",
			        field: "loi1",
			        width: '200px',
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Đã cắm port vật lý nhưng không đóng việc trên hệ thống dẫn đến thất thoát cước",
			        field: "loi5",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Thuê bao tạm ngưng, hủy trên BCCS nhưng không tổ chức rút port",
			        field: "loi6",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: " Đóng việc cắm port trên hệ thống nhưng chưa cắm port vật lý",
			        field: "loi7",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "sự cố lặp lại từ 2 lần trở lên trong tháng",
					columns:[
						{
							title: "lỗi vi phạm đối với dịch vụ CĐBR ",
					        field: "loi8",
					        width: '200px',
					        format:"{0:n0}",
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						},{
							title: "Lỗi vi phạm đối với kênh truyền",
					        field: "loi9",
					        width: '200px',
					        format:"{0:n0}",
					        headerAttributes: {
					        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
							},
							attributes: {
								style: "text-align:left;"
							},
						}
					],
			        width: '400px',
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Phạt sử dụng đầu Fast Connector",
			        field: "loi10",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Lỗi đóng ảo sự cố, đóng sai nguyên nhân",
			        field: "loi11",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Phạt rời mạng",
			        field: "loi12",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},
				{
					title: "Lương",
			        field: "luong",
			        width: '200px',
			        format:"{0:n0}",
			        headerAttributes: {
			        	style : "text-align:center;font-weight:bold;white-space:normal;vertical-align: middle;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},]
			});
		}
		
		
		
		vm.showHideColumn = function(column) {
			if (angular.isUndefined(column.hidden)) {
				vm.tinhLuongDayMayGrid.hideColumn(column);
			} else if (column.hidden) {
				vm.tinhLuongDayMayGrid.showColumn(column);
			} else {
				vm.tinhLuongDayMayGrid.hideColumn(column);
			}

		}
		vm.doSearch =function() {
			if(vm.tinhLuongDayMaySearch.exThang==""||vm.tinhLuongDayMaySearch.exNam==""||vm.tinhLuongDayMaySearch.exThang==null||vm.tinhLuongDayMaySearch.exNam==null){
				toastr.warning("Bạn cần nhập tháng và năm!!!");
				return;
			}
			if($("#tinhLuongDayMayAutoDm113").val()==""){
				vm.tinhLuongDayMaySearch.huyen="";
			}
			
			
			if( $('#tinhLuongDayMayAutoDm112').val()==""){
				 vm.tinhLuongDayMaySearch.tinh="";
			}
			 
			if($('#pxkNhanVienAutoNv').val()==""){
				 vm.tinhLuongDayMaySearch.maNv=null;
			}
			
			vm.tinhLuongDayMaySearch.thang=vm.tinhLuongDayMaySearch.exThang+"/"+vm.tinhLuongDayMaySearch.exNam;
//			if(vm.tinhLuongDayMaySearch.ngayThaoTacFrom!=null&&vm.tinhLuongDayMaySearch.ngayThaoTacTo==null){
//				var date1 = kendo.parseDate(vm.tinhLuongDayMaySearch.ngayThaoTacFrom, "dd/MM/yyyy");
//				vm.tinhLuongDayMaySearch.ngayThaoTacFrom=kendo.toString(date1,"dd/MM/yyyy HH:mm:ss");
//			}
//			if(vm.tinhLuongDayMaySearch.ngayThaoTacFrom==null&&vm.tinhLuongDayMaySearch.ngayThaoTacTo!=null){
//				toastr.warning("Bạn cần nhập ngày thao tác: từ ngày...");
//				return;
//			}
//			
//			if(vm.tinhLuongDayMaySearch.ngayThaoTacFrom!=null&&vm.tinhLuongDayMaySearch.ngayThaoTacTo!=null){
//				var date1 = kendo.parseDate(vm.tinhLuongDayMaySearch.ngayThaoTacFrom, "dd/MM/yyyy");
//				vm.tinhLuongDayMaySearch.ngayThaoTacFrom=kendo.toString(date1,"dd/MM/yyyy HH:mm:ss");
//				
//				var date2 = kendo.parseDate(vm.tinhLuongDayMaySearch.ngayThaoTacTo, "dd/MM/yyyy");
//				vm.tinhLuongDayMaySearch.ngayThaoTacTo=kendo.toString(date2,"dd/MM/yyyy HH:mm:ss");
//			}
			var grid =vm.tinhLuongDayMayGrid;	
			if(grid){
				grid.dataSource.query({
					page: 1,
					pageSize: 10
				});
			}
	    } 
		
		
		
		
    	
		vm.luongDayMay = function() {
// vm.oldSearch.page = null;
// vm.oldSearch.pageSize = null;
			var t0 = performance.now();
			if(vm.tinhLuongDM.exThang==""||vm.tinhLuongDM.exNam==""){
				toastr.warning("Bạn cần nhập tháng, năm!!!");
				return;
			}
			
			vm.tinhLuongDM.thang=vm.tinhLuongDM.exThang+"/"+vm.tinhLuongDM.exNam;
			tinhLuongDayMayService.luongDayMay(vm.tinhLuongDM).then(
					function(result) {
						if(result.error){
		    				toastr.error(result.error);
		    				return;
		    			}
						if (result.fileName) {
							toastr.success("Tính lương nhân viên dây máy thành công!");
							window.location = Constant.BASE_SERVICE_URL
									+ "fileservice/downloadFileATTT?"
									+ data.fileName;
							// return;
						}
						var t1 = performance.now();
		            	setTimeout(function() {
		            		alert("Thời gian thực hiện " + (t1 - t0) + " milliseconds.")
		            	}, 15);

					});
		}
        
        
        vm.patternOptionsNV={
    			dataTextField: "fullName", placeholder:"Nhập mã hoặc tên nhân viên",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.tinhLuongDayMaySearch.maNv=dataItem.userCode;
                    vm.tinhLuongDayMaySearch.fullName=dataItem.fullName;
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                            return Restangular.all("tblUsersServiceRest/getForAutoCompleteNhanVien").post({pageSize:10, page:1, userCode:$("#pxkNhanVienAutoNv").val()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã nhân viên</p>' +
                '<p class="col-md-6 text-header-auto">Họ và tên</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.userCode #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.fullName #</div> </div>',
                change: function(e) {
                	if(processSearch('pxkNhanVienAutoNv',vm.selectedPrpject)){
    					 $('#pxkNhanVienAutoNv').val("");
    					 vm.tinhLuongDayMaySearch.fullName=null;
    					 vm.tinhLuongDayMaySearch.maNv=null;
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        vm.selectedPrpject=false;	
        vm.checkNullTinh= function(){
			if(vm.tinhLuongDayMaySearch.tinh==null||vm.tinhLuongDayMaySearch.tinh==""){
				toastr.warning("Cần nhập tỉnh trước khi chọn huyện!!!");
				$("#tinhLuongDayMayAutoDm112").focus();
				return;
			}
		}
        
        vm.patternOptions={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã tỉnh hoặc tên tên",
                select: function(e) {
                    var dataItem = this.dataItem(e.item.index());
                    vm.tinhLuongDayMaySearch.tinh=dataItem.maDanhMuc;
                    vm.tinhLuongDayMaySearch.tenTinh=dataItem.tenDanhMuc;
// $('#kHuyenKhoAuto').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete1").post({pageSize:10, page:1, name:$("#tinhLuongDayMayAutoDm112").val()}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã tỉnh</p>' +
                '<p class="col-md-6 text-header-auto">Tên tỉnh</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('tinhLuongDayMayAutoDm112',vm.selectedPrpject)){
    					 $('#tinhLuongDayMayAutoDm112').val("");
    					 vm.tinhLuongDayMaySearch.tinh="";
    					 vm.tinhLuongDayMaySearch.tenTinh="";
    					  vm.selectedPrpject=false;	
    					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        
        vm.patternOptions1={
    			dataTextField: "tenDanhMuc", placeholder:"Nhập mã hoặc tên huyện",
                select: function(e) {
                	  var dataItem = this.dataItem(e.item.index());
//                      vm.tinhLuongDayMaySearch.tinh=dataItem.maDanhMuc;
                      vm.tinhLuongDayMaySearch.huyen=dataItem.tenDanhMuc
// $('#dlHaTangTramAuto1').val(dataItem.tenDanhMuc);
// vm.selectedPrpject=true;
                   
                },
                pageSize: 10,
                dataSource: {
                    serverFiltering: true,
                    transport: {
                        read: function(options) {
                        	// link do search don vị thiếu do chưa có bảng đơn
							// vị
                            return Restangular.all("tblDanhMucServiceRest/getDeptForAutocomplete2").post({pageSize:10, page:1, name:$("#tinhLuongDayMayAutoDm113").val(),maDanhMuc:vm.tinhLuongDayMaySearch.tinh}).then(function(response){
                                options.success(response);
                            }).catch(function (err) {
                                console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                            });
                        }
                    }
                },
                headerTemplate: '<div class="dropdown-header row text-center k-widget k-header">' +
                '<p class="col-md-6 text-header-auto border-right-ccc">Mã huyện</p>' +
                '<p class="col-md-6 text-header-auto">Tên huyện</p>' +
                	'</div>',
                template:'<div class="row" ><div class="col-xs-5" style="padding: 0px 32px 0 0;float:left">#: data.maDanhMuc #</div><div style="padding-left:10px;width:auto;overflow: hidden"> #: data.tenDanhMuc #</div> </div>',
                change: function(e) {
                	if(processSearch('tinhLuongDayMayAutoDm113',vm.selectedPrpject)){
   					 $('#tinhLuongDayMayAutoDm113').val("");
   					 vm.tinhLuongDayMaySearch.huyen="";
//   					 vm.tinhLuongDayMaySearch.tinh="";
   					  vm.selectedPrpject=false;	
   					  }
                },
                close: function(e) {
                    // handle the event
                  }
    		};
        
		// end
		}
})();
									
				
