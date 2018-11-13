(function() {'use strict';

	var controllerId = 'listControllertheApproval';

	angular.module('MetronicApp').controller(controllerId, listController);

	/* @ngInject */
	function listController($scope, $rootScope, kendoConfig, gettextCatalog,
			$kWindow, $q,Constant, theApproval) {
		var vm = this;
		var d = new Date();
		var statusApproval = false;
		var listDataEmployee = [];
		$rootScope.disabledButtom = false;
	vm.logDate = d.getDate()+"/"+(d.getMonth()+1)+"/"+d.getFullYear();
	
	vm.theApproval = {
			code:'',
			constructId:'',
			constrCompReMapId: '',
			stringEmployee:'',
			isSign:'',
			path: '',
			nameFile: '',
			userCurentId:'',
			roleId : [],
			roleName : []
		};
	
	var info= ["0","0"];
	vm.theApproval = theApproval.getItem();
	vm.fullNameEmployee = Constant.getUser().srvUser.fullName;
	info[0]=vm.theApproval.stringEmployee;
	
	loadFirst();
	function loadFirst(){
		var cellString= vm.theApproval.stringEmployee.split(",");
		for(var i = 0;i<cellString.length-1;i++){
			for(var j = i+1;j<cellString.length;j++){
				var temp = cellString[i];
				if(temp==cellString[j]){
					cellString.splice(i,1);
					vm.theApproval.roleId.splice(i,1);
					vm.theApproval.roleName.splice(i,1);
					i--;
				}
			}
		}
		vm.theApproval.stringEmployee="";
		for(var i = 0;i<cellString.length;i++){
			vm.theApproval.stringEmployee=vm.theApproval.stringEmployee + cellString[i];
			if(i<(cellString.length)-1){
				vm.theApproval.stringEmployee= vm.theApproval.stringEmployee+ ",";
			}
		}
		info[0]=vm.theApproval.stringEmployee;
		theApproval.getEmployeeByListID(info[0]).then(function(d) {
			listDataEmployee = d.plain();
			for(var i = 0;i<listDataEmployee.length;i++){
				listDataEmployee[i].positionName= vm.theApproval.roleName[i];
			}
			exportContent(listDataEmployee);
		}, function() {
			toastr.error('Error while fetching minout');
		});
	}
	
	vm.change =  change;
	function change(){
		//Change stt
		var cellString= vm.theApproval.stringEmployee.split(",");
		var swap = "";
		var swapRoleId="";
		var swapObj = {}; 
		for(var i = 0;i<cellString.length;i++){
			if(info[1]==cellString[i]){
				var j= i-1;
				if(j<0){
					j=0;
				}
				swapObj= listDataEmployee[j];
				listDataEmployee[j]= listDataEmployee[i];
				listDataEmployee[i] = swapObj;
				
				swap = cellString[j];
				cellString[j] = cellString[i];
				cellString[i]=  swap;
				
				swapRoleId = vm.theApproval.roleId[j];
				vm.theApproval.roleId[j] =vm.theApproval.roleId[i];
				vm.theApproval.roleId[i]=swapRoleId;
			}
		}
		vm.theApproval.stringEmployee="";
		for(var i = 0;i<cellString.length;i++){
			vm.theApproval.stringEmployee=vm.theApproval.stringEmployee + cellString[i];
			if(i<(cellString.length)-1){
				vm.theApproval.stringEmployee= vm.theApproval.stringEmployee+ ",";
			}
		}
		//ChangeSTT
		exportContent(listDataEmployee);
	};
	
	vm.onChange = onChange;
	function onChange(){
		if (vm.reportGrid.select().length > 0) {
			var tr = vm.reportGrid.select().closest("tr");
			var dataItem = vm.reportGrid.dataItem(tr);
			info[1]=dataItem.id;
		}
	}
	
//Click button Duyet	
	vm.clickApproval = clickApproval;
	function clickApproval() {
		vm.theApproval.userCurentId= Constant.user.srvUser.catEmployeeId;
		theApproval.updateTotalApproval(vm.theApproval).then(function() {
			toastr.success("Trình duyệt thành công");
			$rootScope.disabledButtom = true;
			statusApproval = true;
			$rootScope.$broadcast("ChangeStatusApproval", statusApproval);
			statusApproval = false;
		},function(){
				toastr.error('Có lỗi xảy ra!');
			});
	}
	
		// Load Table
		function exportContent(data1) {
				fillDataTable(data1);
				refreshGrid(data1);
		}
	
		function refreshGrid(d) {
			var grid = vm.reportGrid;
			if (grid) {
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		function fillDataTable(data) {
			var deferred = $q.defer();
			vm.gridOptions = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						change : onChange,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
						           {
						        	   title :"<b>STT duyệt</b>",
						        	   field : "as",
						        	   template: dataItem => $("#tableTheApproval").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        	   headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
						        	   width : 80
						           },
						           {
						        	   title : "<b>Đổi</b>",
						        	   field : "as",
						        	   template : '<button class="" style="margin-left: 5%; opacity: 0.7; background-color:transparent;border:0 none;" ng-click="vm.change()"><i class="fa fa-arrow-up" aria-hidden="true"></i></button>',
						        	   headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
						        	   width : 80
						           },
								{
									title : "<b>Vai trò</b>",
									field : "positionName",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:left;"},
									width : 200
								},
								{
									title : "<b>Họ và Tên</b>",
									field : "fullName",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:left;"},
									width : 200
								},
								{
									title :"<b>Email</b>",
									field : "email",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:left;"},
									width : 200
								}]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
		
		exportContentFileApproval();
		
		function refreshGridFileApproval(d) {
			var gridFileApproval = vm.listFileApproval;
			if (gridFileApproval) {
				gridFileApproval.dataSource.data(d);
				gridFileApproval.refresh();
			}
		}
		
		function exportContentFileApproval() {
			var listData = [];
			listData.push(vm.theApproval);
			 fillDataTableFileApproval(listData);
			 refreshGridFileApproval(listData);
		}
		
		function fillDataTableFileApproval(data) {
			var deferred = $q.defer();
			vm.gridOptionsFileApproval = kendoConfig
					.getGridOptions({
						autoBind : true,
						dataSource : data,
						noRecords : true,
						messages : {
							noRecords : gettextCatalog
									.getString("Không có kết quả nào")
						},
						columns : [
						           {
						        	   title : "<b>STT</b>",
						        	   field : "as",
						        	   template: dataItem => $("#tableFileApproval").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        	   headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
						        	   width : 40
						           },
						           {
						        	     title : "<b>Tên File</b>",
						        	     field : "as",
						        	     template :  function(dataItem) {
						        	    	 return "<a href='"+Constant.BASE_SERVICE_URL+"fileservice/downloadFileATTT?" + dataItem.path + "'>" + dataItem.nameFile + "</a>";},
						        	    	 headerAttributes: {style:"text-align:center;"},
										        attributes:{style:"text-align:center;"},
						        	    	 width : 200
						        	    }]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
		
		
	}
})();