(function() {'use strict';

	var controllerId = 'listControllertheSign';

	angular.module('MetronicApp').controller(controllerId, listController);

	/* @ngInject */
	function listController($scope, $rootScope, kendoConfig, gettextCatalog,
			$kWindow, $q, theSignCA, Constant) {
		var vm = this;
		var d = new Date();
		var status = true;
		$rootScope.disabledButtom= false;
		var listDataEmployee = [];
		
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		vm.theSign = {
				code:'',
				constructId:'',
				constrCompReMapId: '',
				stringEmployee:'',
				isSign:'',
				path: '',
				nameFile: '',
				user: '',
				password:'',
				userId : '',
				roleId : [],
				roleName : []
		};
		vm.object1 ={};
		var info= ["0","0"];
		vm.theSign = theSignCA.getItem();
		
		vm.theSign.userId =Constant.user.srvUser.userId;
		info[0]=vm.theSign.stringEmployee;
		vm.logDate = d.getDate()+"/"+(d.getMonth()+1)+"/"+d.getFullYear();
		
		vm.clickSignCA = clickSignCA;
		function clickSignCA() {
			 if (!vm.validator.validate()) {return};
			 $rootScope.disabledButtom= true;
			theSignCA.updateTotal(vm.theSign).then(function() {
				toastr.success("Trình ký thành công");
				
				status = true;
				$rootScope.$broadcast("ChangeStatus", status);
				status = false;
				vm.saveCookies();
				
			},function(){
				toastr.error('Có lỗi xảy ra!');
				$rootScope.disabledButtom= false;
			});
		}
		loadCookie();
		function loadCookie(){
			var decodedCookie = decodeURIComponent(document.cookie);
			var ca = decodedCookie.split(';');
			for(var i = 0; i <ca.length; i++) {
		        var c = ca[i];
		        if(c.includes('user')){
		        	$('#user').val(c);
		        }
		        if(c.includes('password')){
		        	$('#password').val(c);
		        }
		    }
			}
		
		vm.saveCookies = function(){
			if ($('#saveAccountVoffice').is(":checked")) {
				document.cookie = "user=" +$('#user').val()+";";
				document.cookie = "password="+$('#password').val()+";";
				} else {
					document.cookie = user+ "=" +""+";";
					document.cookie = password+"="+""+";";
				}
		}
		
		$scope.$on("ChangeTheSign", function(event, item) {
			if (item != undefined) {
				vm.theSign = item;
				exportContentFileSign();
				info[0]=vm.theSign.stringEmployee;
				exportContent(info[0]);
			} 
		});
		
		loadFirst();
		function loadFirst(){
			var cellString= vm.theSign.stringEmployee.split(",");
			for(var i = 0;i<cellString.length-1;i++){
				for(var j = i+1;j<cellString.length;j++){
					var temp = cellString[i];
					if(temp==cellString[j]){
						cellString.splice(i,1);
						vm.theSign.roleId.splice(i,1);
						vm.theSign.roleName.splice(i,1);
						i--;
					}
				}
			}
			vm.theSign.stringEmployee="";
			for(var i = 0;i<cellString.length;i++){
				vm.theSign.stringEmployee=vm.theSign.stringEmployee + cellString[i];
				if(i<(cellString.length)-1){
					vm.theSign.stringEmployee= vm.theSign.stringEmployee+ ",";
				}
			}
			if($('#user').val()=="" && $('#password').val()==""){
				$('#saveAccountVoffice').prop("checked", false);
			}else{
				$('#saveAccountVoffice').prop("checked", true);
			}
			info[0]=vm.theSign.stringEmployee;
			theSignCA.getEmployeeByListID(info[0]).then(function(d) {
				listDataEmployee = d.plain();
				for(var i = 0;i<listDataEmployee.length;i++){
					listDataEmployee[i].positionName= vm.theSign.roleName[i];
				}
				exportContent(listDataEmployee);
			}, function() {
				toastr.error('Error while fetching minout');
			});
		}
		
		//exportContent(info[0]);
		vm.change = change;
			function change(){
			// Change stt
			var cellString= vm.theSign.stringEmployee.split(",");
			var swap;
			var swapObj; 
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
				}
			}
			vm.theSign.stringEmployee="";
			for(var i = 0;i<cellString.length;i++){
				vm.theSign.stringEmployee=vm.theSign.stringEmployee + cellString[i];
				if(i<(cellString.length)-1){
					vm.theSign.stringEmployee= vm.theSign.stringEmployee+ ",";
				}
			}
			// ChangeSTT
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
						        	   title : "<b>STT</b>",
						        	   field : "as",
						        	   template: dataItem => $("#tableTheSign").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
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
									title : "<b>Email</b>",
									field : "email",
									headerAttributes: {style:"text-align:center;"},
							        attributes:{style:"text-align:left;"},
									width : 200
								}]
					});
			deferred.resolve('done');
			return deferred.promise;
		}
		
		exportContentFileSign();
		
		function refreshGridFileSign(d) {
			var gridFileSign = vm.listFileSign;
			if (gridFileSign) {
				gridFileSign.dataSource.data(d);
				gridFileSign.refresh();
			}
		}
		
		function exportContentFileSign() {
			var listData = [];
			listData.push(vm.theSign);
			if(vm.theSign.code.substr(0, 6) == "PATCTC"){
				theSignCA.getConOrgChild(vm.theSign).then(function(d) {
					vm.object = d.plain();
					for(var i = 0;i<vm.object.length;i++){
					vm.object[i].nameFile = vm.object[i].documentName;
					vm.object[i].path = vm.object[i].documentPath;
					listData.push(vm.object[i]);
					}
					refreshGridFileSign(listData);
				}, function() {
					console.error('Error while fetching object type');
				});
			}
			fillDataTableFileSign(listData);
			refreshGridFileSign(listData);
		}
		
		function fillDataTableFileSign(data) {
			var deferred = $q.defer();
			vm.gridOptionsFileSign = kendoConfig
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
						        	   template: dataItem => $("#tableFileSign").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
						        	   headerAttributes: {style:"text-align:center;"},
								        attributes:{style:"text-align:center;"},
						        	   width : 40
						           },
						           {
						        	     title : "<b>Tên File</b>",
						        	     field : "as",
						        	     template :  function(dataItem) {
						        	    	 return "<a href='"+ Constant.DOWNLOAD_SERVICE + dataItem.path + "'>" + dataItem.nameFile + "</a>";},
						        	    	 
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