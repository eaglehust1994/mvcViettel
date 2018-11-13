
(function() {
	'use strict';

	var controllerId = 'titAziConstrAcceptCtroller';

	angular.module('MetronicApp').controller(controllerId,ham);

	/* @ngInject */
	function ham($scope, $rootScope, Constant, gettextCatalog, kendoConfig, $kWindow,WindowService,$q,titAziConstrAcceptService,ProposalEvaluation, theApproval) {
		var vm = this;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.add = add;
		vm.view = view;
		vm.remove = remove;
		vm.showDetail = false;
		vm.disable= false;
		vm.vohieuhoa=false;
		vm.isCreate=  true;
		vm.buttonCreatNew= false;
		vm.buttonPheDuyet= false;
		vm.showPheduyet= false;
		vm.buttonRemove = false;
		//thông tin chung
		vm.item = ProposalEvaluation.getItem();
		if(vm.item == null) {
			vm.item = CommonService.getItem();
		}
		/*console.log(vm.item);*/
		Constant.user.srvUser.catEmployeeId;
		Constant.user.srvUser.userId;
		
		// thông tin trình duyệt
		vm.theApproval = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path: '',
			    nameFile: '',
			    roleId : [],
			    roleName : []
			};
		
		vm.datePickerConfig = {
		        format: "dd/MM/yyyy HH:mm:ss",
		        parseFormats: ["yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm:ss"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy HH:mm:ss')#"
		    };
		vm.datePickerConfig2 = {
		        format: "dd/MM/yyyy",
		        parseFormats: ["yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy')#"
		    };
		
		function verifyMyDate(d) {
		    var re = /^(((0[1-9]|[12]\d|3[01])[\/\.-](0[13578]|1[02])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|[12]\d|30)[\/\.-](0[13456789]|1[012])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|1\d|2[0-8])[\/\.-](02)[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((29)[\/\.-](02)[\/\.-]((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))$/g;
		    return re.test(d);
		}
		function validateDateTime(){
			if(!verifyMyDate($("#acceptFromDate").val())){
				toastr.warning(gettextCatalog.getString("Bạn nhập sai định dạng ngày giờ trường ngày bắt đầu !"));
				return false;
			}
			if(!verifyMyDate($("#acceptToDate").val())){
				toastr.warning(gettextCatalog.getString("Bạn nhập sai định dạng ngày giờ trường ngày kết thúc!"));
				return false;
			}
			return true;
		}
		
		function add(){
			var data = vm.bang.dataSource.data();
			if(data.length >= 1){
				toastr.warning("Đã tồn tại 1 bản ghi, không thể thực hiện thao tác thêm mới.");
			}else{
				vm.buttonRemove = true;
				vm.showDetail= true;
				vm.disable= true;
				vm.isCreate=  true;
				vm.buttonPheDuyet= false;
				vm.showPheduyet= false;
				vm.dataItem ={
						adirectorId:'',
						ainchargemonitorid:'',
						bdirectorId:'',
						binchargeConstructId:'',
						acceptFromDate:'',
						acceptToDate:'',
						acceptPlace:'',
						applyBenchmark:'',
						constructionQuality:'',
						otherDocuments:'',
						otherComments:'',
						conclusion:'',
						completeRequest:'',
						createdDate:'',
						createdUserId:Constant.user.srvUser.userId,
						approvalDate:'',
						constructId:'',
						constrtName: vm.item.constrtName,
						constrtCode: vm.item.constrtCode,
						constrtAddress:vm.item.constrtAddres,
						contractCode: vm.item.contractCode
				};
				
				titAziConstrAcceptService.displayTitAziConstrAcceptList(0).then(function(d) {
					refreshBangcon(d.plain());
				}, function() {
					toastr.warning(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu !"));
				});
				loadCbb();
				/*console.log("data khởi tạo khi thêm",vm.dataItem);*/
			}
			
			
		}
		
		function view(){
			if(vm.dataItem.createdUserId != Constant.user.srvUser.userId && vm.showDetail== false){
					toastr.warning(gettextCatalog.getString("Bạn không có quyền sửa bản ghi này !"));
				}
			vm.objRemove = [];
			vm.showPheduyet= false;
			$(".k-invalid-msg").hide();
			if(vm.showDetail == false){
				
				// Check select
				var grid = vm.bang;
				var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
					return;
				}else{
					if(dataItem.statusCa == 1 || dataItem.statusCa == 2){
						vm.vohieuhoa=true;
					}
					if(dataItem.statusCa == 0 || dataItem.statusCa == 3){
						vm.vohieuhoa=false;
					}
					vm.buttonRemove = true;
					vm.buttonPheDuyet= true;
					vm.showDetail= true;
					vm.isCreate= false;
					titAziConstrAcceptService.displayTitAziConstrAcceptList(vm.dataItem.titAziConstrAcceptId).then(function(d) {
						refreshBangcon(d.plain());
					}, function() {
						toastr.warning(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu !"));
					});
					loadCbb();
					/*console.log("data khởi tạo khi sửa",vm.dataItem);*/
				}
			}else{
				vm.buttonRemove = false;
				vm.showDetail= false;
				vm.disable= false;
				vm.buttonPheDuyet= true;
				vm.vohieuhoa=false;
				hienthiBangto();
			}
			
		}
		
		vm.Approval = function(){
			titAziConstrAcceptService.pheduyet(vm.dataItem).then(thongbaoThanhcong,function(errResponse) {
					toastr.error(gettextCatalog.getString("Lỗi khi cập nhập "));
					return;
			});
		}
		
		hienthiBangto();
		
		function hienthiBangto() {
			titAziConstrAcceptService.display(vm.item).then(function(d) {
				fillDataTable(d.plain());
				refreshGrid(d.plain());
		}, function() {
			if(vm.item.contractId){
				toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình load dữ liệu !"));
			}else{
				toastr.error(gettextCatalog.getString("Lỗi, công trình này chưa có hợp đồng ! "));
				vm.disable = true;
			}
			
		});
			 
		}	
	
		function refreshGrid(d) {
			var grid = vm.bang;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
	
		// các hàm hiển thị dữ liệu Bảng to 
		function fillDataTable(data){
			vm.gridOptions = kendoConfig.getGridOptions({
				autoBind : true,
				dataSource: data,
				dataBound: function() {
					var grid = $("#stt").data("kendoGrid");
					var selected = false;
					$.each(grid.table.find("tr"),function(){
		                 if (!selected) {
		                	 grid.select("tr:eq(0)");
		                	 selected = true;
		                 }
		             });
                },
				noRecords : true,
				editable: false,
				messages : {
					noRecords : gettextCatalog.getString("Không có dữ liệu trong trang")
				},
				columns : [ {
					field: "STT",
		            title: "STT",
		            template: dataItem => $("#stt").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
		            attributes: {
			      		style: "text-align: center;"
					},
		            width : 55
				}, 
				{
			         title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
			         template : "<input type='checkbox' ng-checked=\"vm.chkAll\" name='gridcheckbox' />",
			         width : 25
			    }, 
			    {
					title : gettextCatalog.getString("Mã biên bản"),
					field : "code",
					width : 120
				}, 
				{
					title : gettextCatalog.getString("Mã công trình"),
					field : "constrtCode",
					width : 110
				}, {
					title : gettextCatalog.getString("Mã hợp đồng"),
					field : "contractCode",
					width : 140
				},{
					title : gettextCatalog.getString("Tên hợp đồng"),
					field : "contractName",
					width : 150
				}, {
					title : gettextCatalog.getString("Trạng thái duyệt"),
					field : "statusCa",
					editor: nonEditor,
					attributes: { style: "text-align:left;", class:"statusColumn"},
					template: function($scope){
					      if($scope.statusCa == 0)
					      {
					       return "Soạn Thảo";
					      }
					      if($scope.statusCa == 1)
					      {
					    	  return "Trình Duyệt";
					      }
					      if($scope.statusCa == 2)
					      {
					    	  return "Đã Duyệt";
					      }
					      if($scope.statusCa == 3)
					      {
					    	  return "Từ chối Duyệt";
					      }
					      },
					width : 110
				}]
			})
	};
			
			/// disable edit textbox in grid
			function nonEditor(container, options) {
		        container.text(options.model[options.field]);
		    }
			 $scope.$on("ProposalEvaluation.detail", function(event, item) {
		            if (item === undefined) {
		            	
		            } else {
		                vm.item = item;
		                hienthiBangto();
		            }
		        });
			$scope.$on("importKLXL", function(event, item) {
		            if (item === undefined) {
		            	
		            } else {
		            	var dataItem = vm.bangcon.dataSource.data();
		            	for(var i = 0 ; i <dataItem.length;i++){
		            		item.push(dataItem[i]);
		            	}
		            	refreshBangcon(item);
		            	toastr.success("Import thành công!");
		            }
		        });
			
			$scope.$on("ChangeStatusApproval", function(event, result){ 
			      if(result){
			    	  hienthiBangto();
			      }
			     });
		
		vm.dataItem={};
		
		// Su kien khi click vao mot hang cua danh sach 				
		vm.onGridChange = function(e, sel, dataItem) {
		
			vm.dataItem = dataItem;
			titAziConstrAcceptService.setDataItem(vm.dataItem);
			//Gán giá trị để trình duyệt
			vm.theApproval.isSign = 'theApproval';
			vm.theApproval.code = dataItem.code;
			vm.theApproval.constructId = dataItem.constructId;
			vm.theApproval.constrCompReMapId = dataItem.constrCompReMapId;
			vm.theApproval.stringEmployee  = dataItem.binchargeConstructId + ',' +dataItem.ainchargemonitorid + ',' + dataItem.bdirectorId+ ',' +dataItem.adirectorId;
			vm.theApproval.path= "/thanhcong";
			vm.theApproval.nameFile = "victory";
			//1-4-3-2
			vm.theApproval.roleId = [Constant.ROLE_ID["employee_roleID_DT_PTTC"],Constant.ROLE_ID["employee_roleID_CDT_PTGST"],
			                         Constant.ROLE_ID["employee_roleID_DT_GDNT"],Constant.ROLE_ID["employee_roleID_CDT_DDPN"]];
			vm.theApproval.roleName = ["Phụ trách thi công trực tiếp","Giám sát thi công","Giám đốc nhà thầu","Thủ trưởng đơn vị"];
			theApproval.setItem(vm.theApproval);
		}
		
		// trình duyệt
		vm.trinhDuyet=function(){
			var grid = vm.bang;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}else{
				if(vm.dataItem.statusCa == 0){
					$('#loading').show();
					vm.dataItem.contractId=vm.item.contractId;
					titAziConstrAcceptService.exportOnePDF(vm.dataItem).then(function(data) {
						 vm.theApproval.path= data.fileName;
				         vm.theApproval.nameFile= 'BM-TCNT-23.pdf';
				         theApproval.setItem(vm.theApproval);
				         goTo('THE_APPROVAL');
					}).catch( function(){
						$('#loading').hide();
						toastr.error(gettextCatalog.getString("Lỗi khi trình duyệt!"));
						return;
					}).finally(function(){
						$('#loading').hide();
					});
				 }else{
					toastr.warning("Trạng thái soạn thảo mới được trình duyệt!");
				}
			}
		};
		
		function goTo(menuKey) {
			var hasPerm = true;
			if (hasPerm) {
				var template = Constant.getTemplateUrl(menuKey);
				postal.publish({
					channel : "Tab",
					topic   : "open",
					data    : template
				});
			}
		}
		
		
		/*DELETE*/
		function remove(){
			var grid = vm.bang;
			// Check select
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}
			var tr = grid.select().closest("tr");
			var dataItem = grid.dataItem(tr);
			if(dataItem.createdUserId != Constant.user.srvUser.userId){
				toastr.warning(gettextCatalog.getString("Bạn không có quyền xóa bản ghi này !"));
				return;
			}else{
				if(dataItem.statusCa == 1 || dataItem.statusCa == 2){
				toastr.warning("Không thể xóa bản ghi đang trình duyệt hoặc đã duyệt!");
				}else if(dataItem.statusCa == 0 || dataItem.statusCa == 3){
					if (vm.bang.select().length > 0 && confirm('Xác nhận xóa bản ghi này ?')) {
						titAziConstrAcceptService.updateIsActive(dataItem.titAziConstrAcceptId).then(function() {
							if(dataItem.statusCa == 0 || dataItem.statusCa == 3){
								toastr.success("Xóa thành công!");
								//titAziConstrAcceptService.removeConstrRecordMap(dataItem.constrCompReMapId);
							}else{
								toastr.success("Xóa thất bại!");
							}
							hienthiBangto();
							
						},function(errResponse) {
							toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình xóa!"));
						});
					}
				}
			}
			
		}
		
		//////////////// Bangcon /////////////			
		vm.bean = {};
		//initial table
		
		var dataSource = new kendo.data.DataSource({
            pageSize: 20,
           // autoSync: true,
            schema: {
                model: {
                    id: "aaa",
                	fields: {
                		content: {type : "string", validation: {
                            validateTitle: function (input) {
                                if (input.is("[name='content']") && input.val().length > 200) {
                                   input.attr("data-validateTitle-msg", "Không nhập được ký tự 201");
                                   return false;
                                }
                                return true;
                            }
                        }
                        },
                		titleCornerBAdjust: {type : "string", validation: {
                            validateTitle: function (input) {
                                if (input.is("[name='titleCornerBAdjust']") && input.val().length > 200) {
                                   input.attr("data-validateTitle-msg", "Không nhập được ký tự 201");
                                   return false;
                                }
                                return true;
                            }
                        }
                        },
                		azimuthDirectBAdjust:{type : "string", validation: {
                            validateTitle: function (input) {
                                if (input.is("[name='azimuthDirectBAdjust']") && input.val().length > 200) {
                                   input.attr("data-validateTitle-msg", "Không nhập được ký tự 201");
                                   return false;
                                }    
                                return true;
                            }
                        }
                        },
                		titleCornerAAdjust: {type : "string", validation: {
                            validateTitle: function (input) {
                                if (input.is("[name='titleCornerAAdjust']") && input.val().length > 200) {
                                   input.attr("data-validateTitle-msg", "Không nhập được ký tự 201");
                                   return false;
                                }    
                                return true;
                            }
                        }
                        },
                		azimuthDirectAAdjust: {type : "string", validation: {
                            validateTitle: function (input) {
                                if (input.is("[name='azimuthDirectAAdjust']") && input.val().length > 200) {
                                   input.attr("data-validateTitle-msg", "Không nhập được ký tự 201");
                                   return false;
                                }    
                                return true;
                            }
                        }
                        },
                        note: {type : "string", validation: {
                            validateTitle: function (input) {
                                if (input.is("[name='note']") && input.val().length > 500) {
                                   input.attr("data-validateTitle-msg", "Không nhập được ký tự 501");
                                   return false;
                                }    
                                return true;
                            }
                        }
                        },
                    }
                }
            }
        });
		
		vm.gridOptions2 = kendoConfig
				.getGridOptions({
					autoBind : true,
					dataSource : dataSource,
					/*navigatable: true,*/
					noRecords : true,
					/*nullable: true,*/
					messages : {
						noRecords : gettextCatalog
								.getString("Không có dữ liệu trong trang")
					},
					columns : [ {
								field: "STT",
					            title: "STT",
					            headerAttributes: {
					            	style: "text-align: center"
					            	},
					            attributes: {
								      style: "text-align: center"
								    },
					            template: dataItem => $("#aaa").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					            width : 70
							},
							{
								title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.checkAll' />",
								template : "<input type='checkbox' ng-checked=\"vm.checkAll\" name='gridcheckbox' />",
								width : 30,
								attributes: {
								      style: "text-align: center"
								    },
								headerAttributes: {
						            style: "text-align: center"
						            },    
							},
							{
								title : gettextCatalog
										.getString("Nội dung"),
								field : "content",
								headerAttributes: {
						            style: "text-align: center"
						            },
								width : 180
							},
							{
								title : gettextCatalog.getString("Đơn vị tính"),
								field : "unit",
								width : 120
							},
							{
								title : gettextCatalog.getString("Góc Tilt trước điều chỉnh"),
								field : "titleCornerBAdjust",
								width : 220
							},
							{
								title : gettextCatalog.getString("Hướng Azimuth trước điều chỉnh"),
								field : "azimuthDirectBAdjust",
								width : 250
							},
							{
								title : gettextCatalog.getString("Góc Tilt sau điều chỉnh"),
								field : "titleCornerAAdjust",
								width : 200
							},
							{
								title : gettextCatalog.getString("Hướng Azimuth sau điều chỉnh"),
								field : "azimuthDirectAAdjust",
								width : 300
							},
							{
								title : gettextCatalog.getString("Ghi chú"),
								field : "note",
								attributes: {
								      style: "text-align: center",
								      "class": "hahaa",
								    },
								width : 100,
								
							} ]
				});
		
		function refreshBangcon(d) {
			var grid = vm.bangcon;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		} 
		
		// lay ID trong file config
		angular.element(document).ready(function () {
		titAziConstrAcceptService.getRoleId().then(function(data) {
			//vm.role = data;
			
			//cbb thủ trưởng đơn vị
			vm.resultTechnical = {
					constructId : vm.item.constructId,
					contractCode : vm.item.contractCode,
					roleid :Constant.ROLE_ID["employee_roleID_CDT_DDPN"], /*103*///2
			};
			
			//cbb giám sát thi công
			vm.resultgiamsatthicong = {
					constructId : vm.item.constructId,
					contractCode : vm.item.contractCode,
					roleid :Constant.ROLE_ID["employee_roleID_CDT_PTGST"],	/*102*///4
			};
			//cbb giám đốc nhà thầu
			vm.resultgiamdocnhathau = {
					constructId : vm.item.constructId,
					contractCode : vm.item.contractCode,
					roleid :Constant.ROLE_ID["employee_roleID_DT_GDNT"],	/*105*///3
			};
			//cbb phụ trách thi công trực tiếp
			vm.resultphutracthicong = {
					constructId : vm.item.constructId,
					contractCode : vm.item.contractCode,
					roleid :Constant.ROLE_ID["employee_roleID_DT_PTTC"]	/*106*///1
			};
			
		}).catch(function() {
			toastr.warning(gettextCatalog.getString("có lỗi xảy ra khi load phân quyền!"));
		});
		});
		
		
		  function loadCbb(){
			//cbb thủ trưởng đơn vị
				vm.technical=[];
				titAziConstrAcceptService.getListEmployeeByRole(vm.resultTechnical).then(function(data) {
					vm.technical = data.plain();
					if(vm.technical.length > 0){
						vm.bean.adirectorId = vm.dataItem.adirectorId ? vm.dataItem.adirectorId  : vm.technical[0].id;
					}else{
						toastr.warning(gettextCatalog.getString("Thủ trưởng đơn vị chưa được phân quyền!"));
					}
				});
				
				//cbb giám sát thi công
				vm.giamsatthicong=[];
				titAziConstrAcceptService.getListEmployeeByRole(vm.resultgiamsatthicong).then(function(data) {
					vm.giamsatthicong = data.plain();
					if(vm.giamsatthicong.length > 0){
						vm.bean.ainchargemonitorid = vm.dataItem.ainchargemonitorid ? vm.dataItem.ainchargemonitorid : vm.giamsatthicong[0].id;
					}else{
						toastr.warning(gettextCatalog.getString("Phụ trách giám sát thi công chưa được phân quyền!"));
					}
				});
				
				//cbb giám đốc nhà thầu
				vm.giamdocnhathau=[];
				titAziConstrAcceptService.getListEmployeeByRole(vm.resultgiamdocnhathau).then(function(data) {
					vm.giamdocnhathau = data.plain();
					if(vm.giamdocnhathau.length > 0){
						vm.bean.bdirectorId = vm.dataItem.bdirectorId ? vm.dataItem.bdirectorId : vm.giamdocnhathau[0].id;
					}else{
						toastr.warning(gettextCatalog.getString("Giám đốc nhà thầu chưa được phân quyền!"));
					}
				});
				
				//cbb phụ trách thi công trực tiếp
				vm.phutracthicong=[];
				titAziConstrAcceptService.getListEmployeeByRole(vm.resultphutracthicong).then(function(data) {
					vm.phutracthicong = data.plain();
					if(vm.phutracthicong.length > 0){
						vm.bean.binchargeConstructId = vm.dataItem.binchargeConstructId > 0 ? vm.dataItem.binchargeConstructId : vm.phutracthicong[0].id;
					}else{
						toastr.warning(gettextCatalog.getString("Phụ trách thi công trực tiếp chưa được phân quyền!"));
					}
					
				});
				
				//cbb kết luận
				vm.conclusion=[
				               {id:1, value:'Chấp nhận nghiệm thu và đồng ý cho triển khai các công việc tiếp theo'},
				               {id:0, value:'Không chấp nhận nghiệm thu'},];
					vm.bean.conclusion = vm.dataItem.conclusion ? vm.dataItem.conclusion : vm.conclusion[0].id ;
		  }
		
		  // Thêm dòng
			 vm.themdong=function(){
				var grid = $(".gridAzimu").data("kendoGrid");
				var data = vm.bangcon.dataSource.data();
				if(data.length == 0){
					grid.dataSource.insert();
					return;
				}
					for(var i =0; i<data.length ; i++){
							if(checkValue(data[i])){
								grid.dataSource.insert();
								return;
							}else{
								return;
							}	
					}
			}
			 
			function checkValue(data){
				if(data.content ){
					if(data.unit){
						if(data.titleCornerBAdjust ){
							if(data.azimuthDirectBAdjust ){
								if(data.titleCornerAAdjust ){
									if(data.azimuthDirectAAdjust){
										return true;
									}else{
										toastr.warning("Không để trống trường Hướng Azimuth sau điều chỉnh trong bảng diễn giải!");
										return false;
									}
								}else{
									toastr.warning("Không để trống trường Góc Tilt sau điều chỉnh trong bảng diễn giải!");
									return false;
								}
							}else{
								toastr.warning("Không để trống trường Hướng Azimuth trước điều chỉnh trong bảng diễn giải!");
								return false;
							}
						}else{
							toastr.warning("Không để trống trường Góc Tilt trước điều chỉnh trong bảng diễn giải!");
							return false;
						}
					}else{
						toastr.warning("Không để trống trường Đơn vị trong bảng diễn giải!");
						return false;
					}
				}else{
					toastr.warning("Không để trống trường Nội dung trong bảng diễn giải!");
					return false;
				}
			}
			
			
			vm.thongbaoThanhcong = thongbaoThanhcong;
			function thongbaoThanhcong() {
				toastr.success(gettextCatalog.getString("Lưu thành công!"));
				hienthiBangto();
				vm.showDetail = false;
				vm.disable= false;
				vm.buttonRemove = false;
			}
			
			vm.pheDuyet= function(){
				var grid = vm.bang;
				if (grid.select() == null || grid.select().length == 0) {
					toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
					return;
				}else{
					vm.vohieuhoa=true;
					var tr = grid.select().closest("tr");
					var dataItem = grid.dataItem(tr);
					if(dataItem.statusCa == 0 || dataItem.statusCa == 2 || dataItem.statusCa == 3){
						toastr.warning(" Chỉ có thể phê duyệt bản ghi ở trạng thái Soạn thảo!");
					}else if(dataItem.statusCa == 1){
						loadCbb();
						vm.showDetail = true;
						vm.showPheduyet= true;
						vm.isCreate =  false;
						vm.approData.statusCa = vm.approData.statusCa ? vm.approData.statusCa :2;
						titAziConstrAcceptService.displayTitAziConstrAcceptList(vm.dataItem.titAziConstrAcceptId).then(function(d) {
							refreshBangcon(d.plain());
						}, function() {
							toastr.warning(gettextCatalog.getString("Co loi xay ra trong qua trinh xay ra qua trinh load du lieu!"));
						});
					}
				}
			}
			
			// duyet
			vm.approData = {
					"statusCa": "",
					"employeeId": Constant.user.srvUser.catEmployeeId,
					"comments": "",
					"titAziConstrAcceptId": "",
					"constrCompReMapId" : "",
				}
			
			// Lưu , update
			vm.saveAll = function(){
				$(".k-invalid-msg").hide();
             		if(vm.validator.validate()){
             			if(!validateDateTime()){
             				return;
             			}
             			var arrValidFrom = vm.dataItem.acceptFromDate.split(" ");
        				var dateFrom = arrValidFrom[0];
        				var arrDateFrom = dateFrom.split("/");
                     	var timeFrom= arrValidFrom[1];
                     	var validTimeFrom = timeFrom.split(":");
                     	var validFrom = new Date();
                     		validFrom.setFullYear(arrDateFrom[2]);
        			   		validFrom.setMonth((arrDateFrom[1]) - 1);
        			   		validFrom.setDate(arrDateFrom[0]);
                     		validFrom.setHours(validTimeFrom[0]);
                     		validFrom.setMinutes(validTimeFrom[1]);
                     		
                     	var arrValidTo = vm.dataItem.acceptToDate.split(" "); 
                     	var dateTo = arrValidTo[0];
                     	var arrDateTo = dateTo.split("/");
                     	var timeTo= arrValidTo[1];
                     	var validTimeTo = timeTo.split(":");
                     	var validTo = new Date();
        	             	validTo.setFullYear(arrDateTo[2]);
        	             	validTo.setMonth((arrDateTo[1]) - 1);
        	             	validTo.setDate(arrDateTo[0]);
                     		validTo.setHours(validTimeTo[0]);
                     		validTo.setMinutes(validTimeTo[1]);
                     		/*var d = new Date;
                     		d.setHours(23, 59, 59);
        	             	if(validTo > d){
        	             		toastr.warning(gettextCatalog.getString("Ngày kết thúc không được quá ngày hiện tại !"));
        	             		return;
        	             	}*/
             	if(validFrom <= validTo){ // check time 
     	               
     				if(vm.showDetail === false){
     					toastr.warning(gettextCatalog.getString("Hãy chọn THÊM hoặc SỬA !"));
     					return;
     				}else{
     				// thêm
     					if(vm.isCreate ==  true){
     						var data = vm.bangcon.dataSource.data();
     						var objectDTO =[];
     						if(data.length == 0){
 								toastr.warning(gettextCatalog.getString("Không để trống <b>bảng diễn giải khối lượng xây lắp hoàn thành</b> ! "));
 							}else{
 								for(var i =0; i<data.length ; i++){
 	     							if(checkValue(data[i])){
 	     								objectDTO.push(data[i]);
 	     							}else{
 	     								return;
 	     							}
 	     						}
	     	     				vm.dataItem.titAziConstrAcceptList = objectDTO;
	     	     				setCbbValue();
	     	     				/*console.log(JSON.stringify(vm.dataItem));*/
	     	     				titAziConstrAcceptService.addAll(vm.dataItem).then(thongbaoThanhcong,function(errResponse) {
	     	     				toastr.error(gettextCatalog.getString("Lỗi khi thêm mới "));
	     	     				return;});
 							}
     					}
     					// sửa
     					else if (vm.isCreate ==  false){
     						var grid = vm.bang;
     						var tr = grid.select().closest("tr");
     						var dataItem = grid.dataItem(tr);
     						
     						// phe Duyet
     						if (vm.showPheduyet) {
     							vm.approData.titAziConstrAcceptId = vm.dataItem.titAziConstrAcceptId;
     							vm.approData.constrCompReMapId = vm.dataItem.constrCompReMapId,
     							/*console.log(JSON.stringify(vm.approData))*/
     							titAziConstrAcceptService.appro(vm.approData).then(function (data) {
     								var x = data;
     							     if(x == '1'){
     							      toastr.warning("Chưa tới lượt duyệt");
     							     }
     							     if(x == '2'){
     							      toastr.warning("Đã duyệt rồi");
     							     }
     							     if(x == '4'){
     							      toastr.success("Từ chối duyệt thành công");
     							      vm.showDetail = false;
     							      hienthiBangto();
     							     }
     							     if(x == '3'){
     							      toastr.success("Duyệt thành công");
     							      vm.showDetail = false;
     							      hienthiBangto();
     							     }
     							     if(x == '0'){
     							      toastr.warning("Lỗi xảy ra");
     							     }if(x == '5'){
     							    	toastr.error("Duyệt lỗi, người dùng chưa được phân quyền !");
     							     }
     							});
     							vm.showDetail = false;
     							hienthiBangto();
     							return;
     						}
     						
     						var data = vm.bangcon.dataSource.data();
	     					var objectDTO =[];
	     					
     						if(data.length == 0){
 								toastr.warning(gettextCatalog.getString("Không để trống <b>bảng diễn giải khối lượng xây lắp hoàn thành</b> !"));
 							}else{
 								if(vm.dataItem.createdUserId != Constant.user.srvUser.userId){
 									toastr.warning(gettextCatalog.getString("Bạn không có quyền sửa bản ghi này !"));
 									return;
 								}
 								for(var i =0; i<data.length ; i++){
 	     							if(checkValue(data[i])){
 	     								objectDTO.push(data[i]);
 	     							}else{
 	     								return;
 	     							}
 	     						}
 	  	     						vm.dataItem.titAziConstrAcceptList = objectDTO;
 	  	     						setCbbValue();
 	  	     						/*console.log(JSON.stringify(vm.dataItem));*/
 	  	     						for (var i = 0; i < vm.dataItem.titAziConstrAcceptList.length; i++) {
 	  	     							vm.dataItem.titAziConstrAcceptList[i].titAziConstrAcceptId = vm.dataItem.titAziConstrAcceptId
									}
 	  	     						if(vm.dataItem.statusCa == 3){
 	  	     							vm.dataItem.statusCa = 0;
 	  	     						}
 	  	     						titAziConstrAcceptService.update(vm.dataItem).then(function(){
 	  	     							thongbaoThanhcong();
	 	  	     						titAziConstrAcceptService.deleteAziList(vm.objRemove);
 	  	     						},function(errResponse) {
 	  	     							toastr.error(gettextCatalog.getString("Lỗi khi cập nhật "));
 	  	     							return;});
 	  	     						
 							}
     					}
     				}
             	}else{
                 	   toastr.warning(gettextCatalog.getString("Ngày kết thúc phải bằng hoặc sau ngày bắt đầu"));
                }
     		}
		}
			
			function setCbbValue(){
				vm.dataItem.adirectorName= $("#cbb1").data("kendoDropDownList").text();
				vm.dataItem.ainchargemonitorName= $("#cbb2").data("kendoDropDownList").text();
				vm.dataItem.bdirectorName= $("#cbb3").data("kendoDropDownList").text();
				vm.dataItem.binchargeConstructName= $("#cbb4").data("kendoDropDownList").text();
				
				vm.dataItem.adirectorId = document.getElementById("cbb1").value;
				vm.dataItem.ainchargemonitorid = document.getElementById("cbb2").value;
				vm.dataItem.bdirectorId = document.getElementById("cbb3").value;
				vm.dataItem.binchargeConstructId = document.getElementById("cbb4").value;
				vm.dataItem.constructId = vm.item.constructId;
				vm.dataItem.conclusion=document.getElementById("conclusion").value;
				
			}
			
			// xoa row
			vm.removeRow=function(){
				var selectedRow = [];
				var listRow = [];
		  	    var grid = vm.bangcon;
		  	    grid.table.find("tr").each(function(idx, item) {
		  	    	var row = $(item);
		  	    	var checkbox = $('[name="gridcheckbox"]', row);
		  	    	if (checkbox.is(':checked')) {
		  	    		// Push id into selectedRow
		  	    		var tr = grid.select().closest("tr");
		  	    		var dataItem = grid.dataItem(item);
		  	    		/*console.log(JSON.stringify(dataItem));*/
		  	    		selectedRow.push(dataItem);
		  	    		listRow.push(row);
		  	    	}
		  	   });
		  	   
		  	  for(var i=listRow.length-1;i>=0;i--){
		  		 grid.removeRow(listRow[i]);
		  	  }
		  	   if (selectedRow.length == 0) {
		  	    toastr.warning("Phải tích chọn bản ghi !");
		  	    	return;
		  	   }
		  	
		  	   var objectDTO = [];
		  	   for(var i = 0 ; i < selectedRow.length; i++){
		  		   if(selectedRow[i] != null && selectedRow[i] != "" ){
		  			   objectDTO.push(selectedRow[i]);
		  		   }else{
		  			   /*delete selectedRow[i];*/
		  		   }
		  	   }

		  	   if(objectDTO.length > 0){
		  			console.log(JSON.stringify(objectDTO));
		  			for(var i = 0 ; i < objectDTO.length; i++){
				  		   if(objectDTO[i].titAziConAcceptListId !=""){
				  			 console.log("không rỗng");
				  			vm.objRemove.push(objectDTO[i].titAziConAcceptListId);
				  		   }else{
				  			 console.log("rỗng");
				  		   }
				  	   }
		  	   }
			};
			
			vm.closeApproval = function(){
				vm.showPheduyet= false;
			}
		/* **********************EXPORT **********************	*/
		vm.exportOnePDF = function(){
			vm.dataItem.contractId=vm.item.contractId;
			var grid = vm.bang;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}
			
			$('#loading').show();
			titAziConstrAcceptService.exportOnePDF(vm.dataItem).then(function(data) {
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				$('#loading').hide();
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			}).finally(function(){
				$('#loading').hide();
			});
		};
		
		vm.ExportDOC = function() {
				var selectedRow = [];
		  	    var grid = vm.bang;
		  	    
		  	    grid.table.find("tr").each(function(idx,item) {
		  	    	var row = $(item);
		  	    	var checkbox = $('[name="gridcheckbox"]', row);

		  	    	if (checkbox.is(':checked')) {
		  	    		// Push id into selectedRow
		  	    		var tr = grid.select().closest("tr");
		  	    		var dataItem = grid.dataItem(item);
		  	    		selectedRow.push(dataItem.titAziConstrAcceptId);
		  	    		
		  	    	}
		  	   });
		  	   
		  	   if (selectedRow.length == 0) {
		  	    toastr.warning("Phải tích chọn bản ghi!");
		  	    	return;
		  	   }else if(selectedRow.length > 0){
		  		 toastr.warning("Xin vui lòng đợi quá trình export hoàn tất");
		  		titAziConstrAcceptService.exportFileDOC(selectedRow).then(function(data) {
					window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
					toastr.success("export danh sach thành công");
				}, function(){
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
				});
				}
		};
		
		vm.exportOneDoc = function(){
			$('#loading').show();
			vm.dataItem.contractId=vm.item.contractId;
			var grid = vm.bang;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}
			console.log("vm.dataItem",vm.dataItem);
			titAziConstrAcceptService.exportOneDoc(vm.dataItem).then(function(data) {
				window.location = Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + data.fileName;
			}).catch( function(){
				$('#loading').hide();
				toastr.error(gettextCatalog.getString("Lỗi khi export!"));
				return;
			}).finally(function(){
				$('#loading').hide();
			});
		};
		
		vm.huyPheDuyet= function(){
			var grid = vm.bang;
			if (grid.select() == null || grid.select().length == 0) {
				toastr.warning("Cần chọn bản ghi trước khi thực hiện thao tác này!");
				return;
			}
			vm.dataItem.tableName = 'TIT_AZI_CONSTR_ACCEPT';
			vm.dataItem.tableId = vm.dataItem.titAziConstrAcceptId;
			vm.dataItem.tableIdField = 'TIT_AZI_CONSTR_ACCEPT_ID';
			if(vm.dataItem.createdUserId != Constant.user.srvUser.userId){
				toastr.warning(gettextCatalog.getString("Người tạo mới có quyền hủy duyệt!"));
				return;
			}else{
				if(vm.dataItem.statusCa == 1 && confirm('Bạn chắc chắn muốn hủy duyệt ?')){
					titAziConstrAcceptService.cancelAprroval(vm.dataItem).then(function() {
						status = true;
						$rootScope.$broadcast("ChangeStatusHuyDuyet", status);
						toastr.success(gettextCatalog.getString("Đã hủy duyệt !"));
						hienthiBangto();
						}, function(){
							toastr.error(gettextCatalog.getString("Có lỗi xảy ra trong quá trình thực hiện thao tác này!"));
							return;
					});
				}else if(vm.dataItem.statusCa != 1){
					toastr.warning("Chỉ bản ghi đang trình duyệt mới có thể hủy duyệt!");
				}
			}
		}
		
		vm.importKLXLHT= function(){
			WindowService.open({
	                options: {
	                    modal: true,
	                    title: "Import khối lượng xây lắp hoàn thành",
	                    visible: false,
	                    width: '650',
	                    height: '200',
	                    actions: ["Minimize", "Maximize", "Close"],
	                    open: function() {
	                        this.wrapper.children('.k-window-content').addClass("fix-footer");
	                    }
	                },
	                templateUrl: "qlhc/constructionTitAzi/KLXLHT.html"

	            });
			
		}
		////////////End////////////
		
	}
})();