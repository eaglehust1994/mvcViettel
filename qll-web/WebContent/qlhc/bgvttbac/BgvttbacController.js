(function() {
	'use strict';

	var controllerId = 'BgvttbacController';

	angular.module('MetronicApp').controller(controllerId, BgvttbacController);

	/* @ngInject */
	function BgvttbacController($scope, $rootScope, $timeout, Constant,
			gettextCatalog, kendoConfig, WindowService, bgvttbacService,dsbgvtService, 
			CommonService, $q, RestEndpoint) {
		var vm = this;
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		vm.fillData = fillData;
		vm.chkSelectAll = chkSelectAll;
		vm.onChange = onChange;
		vm.save = save;
		vm.exportFile = exportFile;
	
		vm.datePickerConfig = {
		        format: "dd/MM/yyyy HH:mm:ss",
		        parseFormats: ["yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm:ss"],
		        footer: "Currently #: kendo.toString(data,'dd-MM-yyyy HH:mm:ss')#"
		    };
		
		vm.constrObj = {
				constructId: '',
				shd : '',
				ngaykyhd : '',
				mact : '',
				tenct : '',
				diachict : '',
				tai : '',
				stationCode:''
			};
		
		vm.materialObj = {
				code:'',
				constrtCode:'',
				contractCode:'',
				handoverFromDate:'',
				handoverToDate:'',
				statusName:'',
				amaterialHandoverId:''
		};
		
		vm.materialObj = dsbgvtService.getItem1();
		
		
		vm.thutruong = [];
		vm.nguoigiao = [];
		vm.giamdoc = [];
		vm.nguoinhan = [];
		
		vm.item = dsbgvtService.getItem();
		
		vm.constrObject = {
				constructId : vm.item
				
			};
		
		fillData(vm.constrObject);
		
		function fillData(object) {
			bgvttbacService.getConstructions(object).then(function(data) {
				vm.constrObj.constructId = data.constructId;
				vm.constrObj.shd = data.contractCode;
				vm.constrObj.ngaykyhd = data.signDate;
				vm.constrObj.mact = data.constrtCode;
				vm.constrObj.tenct = data.constrtName;
				vm.constrObj.diachict = data.constrtAddress;
				vm.constrObj.stationCode = data.stationCode;
			  })
			  .catch(function(data, status) {
			    console.error('getConstructions error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished getConstructions");
			  });
		}
		
		// lay danh sach thu truong start
		vm.roleThutruong = {
				constructId : vm.item,
				role : '2',
				type : '1'
			};
		getThuTruong(vm.roleThutruong);
		
		function getThuTruong(object) {
			bgvttbacService.getEmployeeByRole(object).then(function(data) {
				vm.thutruong = data.plain();
				if(vm.thutruong.length > 0) {
					vm.thutruong.id = vm.thutruong[0].id;
				}
				
			  })
			  .catch(function(data, status) {
			    console.error('getEmployeeByRole error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished getEmployeeByRole");
			  });
		}
		// lay danh sach thu truong end
		
		// lay danh sach nguoi giao start
		vm.roleNguoigiao = {
				constructId : vm.item,
				role : '2',
				type : '1'
			};
		getNguoigiao(vm.roleNguoigiao);
		
		function getNguoigiao(object) {
			bgvttbacService.getEmployeeByRole(object).then(function(data) {
				vm.nguoigiao = data.plain();
				if(vm.nguoigiao.length > 0) {
					vm.nguoigiao.id = vm.nguoigiao[0].id;
				}
				
			  })
			  .catch(function(data, status) {
			    console.error('getEmployeeByRole error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished getEmployeeByRole");
			  });
		}
		// lay danh sach nguoi giao end
		
		// lay danh sach giam doc start
		vm.roleGiamdoc = {
				constructId : vm.item,
				role : '1',
				type : '2'
			};
		getGiamdoc(vm.roleGiamdoc);
		
		function getGiamdoc(object) {
			bgvttbacService.getEmployeeByRole(object).then(function(data) {
				vm.giamdoc = data.plain();
				if(vm.giamdoc.length > 0) {
					vm.giamdoc.id = vm.giamdoc[0].id;
				}
				
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished gists");
			  });
		}
		// lay danh sach ngiam doc end
		
		// lay danh sach nguoi nhan start
		vm.roleNguoinhan = {
				constructId : vm.item,
				role : '2',
				type : '2'
			};
		getNguoinhan(vm.roleNguoinhan);
		
		function getNguoinhan(object) {
			bgvttbacService.getEmployeeByRole(object).then(function(data) {
				vm.nguoinhan = data.plain();
				if(vm.nguoinhan.length > 0) {
					vm.nguoinhan.id = vm.nguoinhan[0].id;
				}
				
			  })
			  .catch(function(data, status) {
			    console.error('Gists error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished gists");
			  });
		}
		// lay danh sach nguoi nhan end
		
		// load danh sach phieu xuat kho da xuat ra cong trinh start
		vm.code = '';
		vm.listVTTB = [];
		vm.listExpCode = [];
		vm.constrObj.constructId = dsbgvtService.getItem();
		loadExpNote(vm.constrObj);
		function loadExpNote(object) {
			
			bgvttbacService.getwareExpCmdByConstrt(object).then(function(data) {
				fillDataTable(data.plain()) ;
				// load danh sach vat tu thiet bi them moi
				if(vm.materialObj == null || vm.materialObj.amaterialHandoverId == null ) {
					$.each( data, function( index, item ) {
						vm.listExpCode.push(item.expNoteCode);
					});
					
					bgvttbacService.getListAMaterial(vm.listExpCode).then(function(dataVTTB){
						fillDataTableVTTB(dataVTTB.plain()) ;
					});
				}
				
			  });
			
			if(vm.materialObj != undefined && vm.materialObj.amaterialHandoverId != null ) {
				// load danh sach vat tu thiet bi edit
				vm.materialObj = {
						amaterialHandoverId : vm.materialObj.amaterialHandoverId,
				};
				bgvttbacService.getThoiGianBanGiao(vm.materialObj).then(function(data){
					if(data.length > 0) {
						vm.materialObj.handoverFromDate = new Date(data[0].handoverFromDate);
						vm.materialObj.handoverToDate = new Date(data[0].handoverToDate);
						vm.materialObj.handoverPlace = data[0].handoverPlace;
						vm.materialObj.otherComments = data[0].otherComments;
					}
					
				});
			
				bgvttbacService.getListAMaterialHandOverMerList(vm.materialObj).then(function(dataVTTB){
					fillDataTableVTTB(dataVTTB.plain()) ;
				});
				
			}
		}
		
		
		function fillDataTable(data) {
		   	var deferred = $q.defer();
		   	vm.options1 = kendoConfig.getGridOptions({
		   		autoBind : true,
		   		dataSource : data,
		   		change : onChange,
		   		noRecords : true,
		   		messages : {
		   			noRecords : gettextCatalog.getString("Không có dữ liệu.")
		   		},
		   		columns : [
		   		{
		   			title : gettextCatalog.getString("STT"),
					field : "index",
					template: dataItem => $("#pxkGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					width : 80
		   		},
		   		{
		   			title : "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
		   			template : "<input type='checkbox' name='gridcheckbox' />",
					width : 35
		   		},
		   		{
		   			title : gettextCatalog.getString("Mã phiếu xuất kho"),
		   			field : "expNoteCode",
		   			width : 120
		   		},
		   		{
		   			title : gettextCatalog.getString("Ngày tạo"),
		   			field : "createdDate",
		   			width : 120
		   		},
		   		{
		   			title : gettextCatalog.getString("Ngày thực xuất"),
		   			field : "actualExpDate",
		   			width : 120
		   		},
		   		{
		   			title : gettextCatalog.getString("Trạng thái bàn giao"),
		   			field : "status",
					template : function(data) {
						if(data.status == '1') {
							return "Chưa thực xuất";
						}else if (data.status == '2') {
							return "Đã xuất";
						}else if (data.status == '3') {
							return "Đã hủy";
						}else if (data.status == '4') {
							return "Đã tạo lệnh nhập";
						}else {
							return data.status;
						}
					},
		   			width : 100
		   		}]
		   	});
		   	deferred.resolve('done');
		   	return deferred.promise;
		}
		
		function chkSelectAll(item) {
			console.log('chkSelectAll');
	    	var grid = vm.contractGrid;
	    	chkSelectAllBase(vm.chkAll, grid);
		}
		// load danh sach phieu xuat kho da xuat ra cong trinh end
		
		
		// load danh sach thiet bi vat tu start
		
		function fillDataTableVTTB(data) {
		   	var deferred = $q.defer();
		   	vm.options = kendoConfig.getGridOptions({
		   		autoBind : true,
		   		dataSource : data,
		   		change : onChange,
		   		noRecords : true,
		   		messages : {
		   			noRecords : gettextCatalog.getString("Không có dữ liệu.")
		   		},
		   		columns : [
		   		{
		   			title : gettextCatalog.getString("STT"),
					field : "index",editable: false,
					template: dataItem => $("#vttbGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
					width : 50
		   		},
		   		{
		   			title : gettextCatalog.getString("Mã phiếu xuất kho"),
		   			field : "expNoteCode",editable: false,
		   			width : 120
		   		},
		   		{
		   			title : gettextCatalog.getString("Tên vật tư, TB"),
		   			field : "merName",editable: false,
		   			width : 120
		   		},
		   		{
		   			title : gettextCatalog.getString("Serial Number"),
		   			field : "serialNumber",editable: false,
		   			width : 110
		   		},
		   		{
		   			title : gettextCatalog.getString("Đơn vị tính"),
		   			field : "unitId",editable: false,
		   			width : 100
		   		},
		   		{
		   			title : gettextCatalog.getString("Số lượng bàn giao"),
		   			field : "handoverQuantity",
		   			width : 120
		   		},{
		   			title : gettextCatalog.getString("Số lượng thực nhận"),
		   			field : "actualReceiveQuantity",
		   			width : 130
		   		},{
		   			title : gettextCatalog.getString("Hiện trạng"),
		   			field : "qualityStatus",editable: false,
		   			template : function(data) {
						if(data.qualityStatus == '1') {
							return "Tốt";
						}else if (data.qualityStatus == '0') {
							return "Hỏng";
						}else {
							return 'không xác định';
						}
		   			},
		   			width : 100
		   		},{
		   			title : gettextCatalog.getString("Ghi chú"),
		   			field : "comments",editable: false,
		   			width : 150
		   		}]
		   	});
		   	deferred.resolve('done');
		   	return deferred.promise;
		}
		
		// add material start
		 function save(){
			 vm.validator._inputSelector = $rootScope.formLevel2.INPUTSELECTOR;
			 if (!vm.validator.validate()) return;
			 var todate = vm.materialObj.handoverToDate;
			 var fromDate = vm.materialObj.handoverFromDate;
			 if(fromDate > todate) {
				 toastr.warning(gettextCatalog.getString("Thời gian nghiệm thu không hợp lệ!"));
					return;
			 }
			 
			 
			 // get list object from table
			 var data = vm.vttbGrid.dataSource.data();
			 
			 for(var i = 0; i < data.length; i++) {
					var VTTB = {
							amaterialHandoverId : '',
							expNoteCode : data[i].expNoteCode,
							handoverQuantity : data[i].handoverQuantity,
							merName : data[i].merName,
							unitId  : data[i].unitId,
							qualityStatus  : data[i].qualityStatus,
							serialNumber  : data[i].serialNumber,
							comments  : data[i].comments,
							merEntityId  : data[i].merEntityId,
							actualReceiveQuantity  : data[i].actualReceiveQuantity,
							isDevice  : data[i].isDevice,
							merId  : data[i].merId
					}
					vm.listVTTB.push(VTTB);
			}
			 
			
			 var aDirectorId = document.getElementById("thutruong").value ;
			 var aHandoverPersonId = document.getElementById("nguoigiao").value ;
			 var bReceivePersonId = document.getElementById("nguoinhan").value ;
			 var bDirectorId = document.getElementById("giamdoc").value ;
			 var handoverFromDate = vm.materialObj.handoverFromDate;
			 
	    	 var handoverToDate = vm.materialObj.handoverToDate;
	    	 
	    	 var handoverPlace = document.getElementById("tai").value;
	    	 var otherComments = document.getElementById("otherComment").value;
	    	 var statusCa = 1;
	    	 var documentCaId = 1;
	    	 var approvalDate = null;
	    	 var constrtCode = vm.constrObj.shd;
	    	 var constructId = vm.constrObj.constructId;
	    	 var isActive = null;
	    	 
	    	 
	    	 
	    	 vm.materialObj = {
	    			 code: '',
	    			 aDirectorId:aDirectorId,
	    			 aHandoverPersonId:aHandoverPersonId,
	    			 bReceivePersonId:bReceivePersonId,
	    			 bDirectorId:bDirectorId,
	    			 handoverFromDate:handoverFromDate,
	    			 handoverToDate:handoverToDate,
	    			 handoverPlace:handoverPlace,
	    			 otherComments:otherComments,
	    			 statusCa:statusCa,
	    			 documentCaId:documentCaId,
	    			 approvalDate:approvalDate,
	    			 isActive:isActive,
	    			 constrtCode:constrtCode,
	    			 constructId:constructId,
	    			 listAmaterialMerlist:vm.listVTTB,
	    			 
	    	 }
		    	
	    	 bgvttbacService.addAmaterial(vm.materialObj).then(function(data) {
	    		 if(data){
						alert("Đã thêm thành công");
					}
				  }).catch(function(data, status) {
				    console.error('addAmaterial error', response.status, response.data);
				  })
				  .finally(function() {
				    console.log("finally finished addAmaterial");
				  });
		 }
		// add material end
		
		function onChange(){
		  	// do something when click row of table
		  	}
		
		$scope.$on("bgvttbacService.detail", function(event, item) {
			if (item != undefined) {
				vm.materialObj = item;
			} else {
				console.error("không nhận được dữ liệu!");
			}
		});
		
		function exportFile(){
			var data = vm.vttbGrid.dataSource.data();
			vm.listVTTB = [];
			
			for(var i = 0; i < data.length; i++) {
				var VTTB = {
						amaterialHandoverId : '',
						expNoteCode : data[i].expNoteCode,
						handoverQuantity : data[i].handoverQuantity,
						merName : data[i].merName,
						unitID  : data[i].unitId,
						quanlityStatus  : data[i].qualityStatus,
						serialNumber  : data[i].serialNumber,
						comments  : data[i].comments,
						merEntityId  : data[i].merEntityId,
						actualReceiveQuantity  : data[i].actualReceiveQuantity
				}
				vm.listVTTB.push(VTTB);
		}
			
			var thutruong = document.getElementById("thutruong");
			var aperson1 = thutruong.options[thutruong.selectedIndex].text;
			
			var nguoigiao = document.getElementById("nguoigiao");
			var aperson2 = nguoigiao.options[nguoigiao.selectedIndex].text;
			
			var giamdoc = document.getElementById("giamdoc");
			var bperson1 = giamdoc.options[giamdoc.selectedIndex].text;
			
			var nguoinhan = document.getElementById("nguoinhan");
			var bperson2 = nguoinhan.options[nguoinhan.selectedIndex].text;
			
			var fromDate = document.getElementById("fromDate").value;
			var hstart = fromDate.substring(11, 13)+fromDate.substring(13, 16);
			var dstart = fromDate.substring(0, 2);
			var mstart = fromDate.substring(3, 5);
			var ystart = fromDate.substring(6, 10);
			var toDate = document.getElementById("toDate").value;
			var hend = toDate.substring(11, 13)+toDate.substring(13, 16);
			var dend = toDate.substring(0, 2);
			var mend = toDate.substring(3, 5);
			var yend = toDate.substring(6, 10);
			
			var obj = {
					constrName: document.getElementById("tenct").value,
					hangMuc: '',
					code: vm.constrObj.stationCode,
					address: document.getElementById("dcct").value ,
					contractCode: document.getElementById("shd").value,
					aperson1: aperson1,
					aperson1Orange: 'Thủ trưởng',
					aperson2: aperson2,
					aperson2Orange: 'Người giao',
					bperson1: bperson1,
					bperson1Orange:'Giám đốc' ,
					bperson2: bperson2,
					bperson2Orange:'Người nhận' ,
					hstart: hstart,
					dstart:dstart ,
					mstart:mstart ,
					ystart: ystart,
					hend: hend,
					dend: dend,
					mend: mend,
					yend: yend,
					place: document.getElementById("tai").value,
					vttbList: vm.listVTTB
			};
			
			bgvttbacService.exportFile(obj).then(function(data) {
				window.location = "/qlhc-service/fileservice/download/" + data.fileName;
			  })
			  .catch(function(data, status) {
			    console.error('exportFile error', response.status, response.data);
			  })
			  .finally(function() {
			    console.log("finally finished exportFile");
			  });
		}
		
		
	}
	
})();