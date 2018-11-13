/* Modal Controller */
MetronicApp.controller('ModalInstanceCtrl', ['$scope', 'data', 'caller', 'modalInstance', 'popupId','searchType', 'isMultiSelect',
						  'CommonService', 'SearchService', 'PopupConst', 
 function($scope, data, caller, modalInstance, popupId , searchType, isMultiSelect, CommonService, SearchService, PopupConst) {
	
	$scope.data = data;
	$scope.caller = caller;
	$scope.modalInstance = modalInstance;
	$scope.popupId = popupId;
	$scope.searchType = searchType;
	$scope.isMultiSelect = isMultiSelect;
	$scope.cancel = cancel;
	$scope.save = save;
	$scope.submit=submit;
	$scope.saveBpopup = saveBpopup;
	$scope.searchAdOrg = searchAdOrg;
	$scope.searchOnGrid = searchOnGrid;
	$scope.searchAccount = searchAccount;
	$scope.searchBpartner = searchBpartner;
	$scope.searchPayroll = searchPayroll;
	$scope.searchBudget = searchBudget;
	$scope.searchCostType = searchCostType;
	$scope.searchProject = searchProject;
	$scope.searchLocation = searchLocation;
	$scope.searchMwarehouce = searchMwarehouce;
	$scope.searchProduct = searchProduct;
	$scope.searchProductCategory = searchProductCategory;
	$scope.searchCSiteCodeInfo = searchCSiteCodeInfo;
	$scope.searchStatement = searchStatement;
	$scope.searchBank = searchBank;
	$scope.onChangeCheckbox = onChangeCheckbox;


	$scope.searchGrids=[];
	$scope.searchGrid={
		typeEstimateWork: '2'	
	}
	//$scope.searchGrid={}
	$scope.searchDocumentType = searchDocumentType;
	$scope.searchDocumentType1 = searchDocumentType1;
	$scope.searchPeriod = searchPeriod;
	$scope.chkSelectAll = chkSelectAll;
	$scope.chkAll;
	$scope.handleCheck=handleCheck;
	(function initController() {
		$scope.$watch(function(){
			return $("#treeview").data("kendoTreeView");
		}, function(){
			/*if($("#treeview").data("kendoTreeView")) {
				var treeview = $("#treeview").data("kendoTreeView");
				treeview.bind("expand", treeExpand);
				treeview.bind("collapse", treeCollapse);
				
				
				for(var i = 0 ; i<treeview.root[0].childNodes.length ; i++){
					var item = treeview.root[0].childNodes[i];
					
					if(i != treeview.root[0].childNodes.length - 1 && i != 0 ) {
						var itemNode = item.childNodes[0];
						itemNode.className  =itemNode.className +" borderCentere";
						var itemNodeOne = item.childNodes[0].getElementsByClassName("k-icon");
						itemNodeOne[0].className =itemNodeOne[0].className +" plusOpen";
					}
					else{
						var itemNode = item.childNodes[0];
						itemNode.className  =itemNode.className +" endBorderOut";
						var itemNodeOne = item.childNodes[0].getElementsByClassName("k-icon");
						itemNodeOne[0].className =itemNodeOne[0].className +" plusOpen";
					}
				}
				var firstItemParent=treeview.root[0].childNodes[0].getElementsByClassName("k-top");
				firstItemParent[0].className = "" + "k-top ng-scope borderPlus";
				var firstItemChild=treeview.root[0].childNodes[0].getElementsByClassName("k-icon");
				firstItemChild[0].className = "" + "k-icon k-plus plusOpen";
				var lastItemParent =treeview.root[0].childNodes[treeview.root[0].childNodes.length - 1].getElementsByClassName("k-bot");
				lastItemParent[0].className = "" + "k-bot ng-scope endBorderOut";
				var lastItemChild =treeview.root[0].childNodes[treeview.root[0].childNodes.length - 1].getElementsByClassName("k-icon");
				lastItemChild[0].className="" + "k-icon k-plus plusOpen";*/
				
//			}
		});
		
		if (caller.bean != null && caller.bean != undefined) {
			$scope.chkAll = caller.bean.chkAll;
		}
	})();
	
	function treeExpand(e) {
		var node = e.node;
		
		$scope.$watch(function(){
			return node.childElementCount;
		}, function(){
			var treeview = $("#treeview").data("kendoTreeView");
			if(node.childElementCount > 1) {
				var parentNode = node.childNodes[1];
				for(var i=0 ; i < parentNode.childElementCount ; i++){
					var spanNode = parentNode.childNodes[i].getElementsByClassName("k-icon");
					var spanNodeBorder = parentNode.childNodes[i].getElementsByClassName("k-in");
					if(spanNode && spanNode.length > 0 ) {
						spanNode[0].className = spanNode[0].className + " k-circle";
						spanNodeBorder[0].className = spanNodeBorder[0].className + " borderCircle";
						spanNode[0].className  = spanNode[0].className.replace("k-icon", ""); 
					}
					if(i == parentNode.childElementCount - 1) {
						spanNodeBorder[0].className = spanNodeBorder[0].className + " endBorder";
						spanNodeBorder[0].className  ="k-in endBorder"; 
					}
				}
				
				parentNode.className = parentNode.className +" childSum";
				var parentDivKTop = node.childNodes[0];
				parentDivKTop.className = parentDivKTop.className + " borderPlus";
				delKIcon = node.childNodes[0].getElementsByClassName("k-icon");
				delKIcon[0].className  =delKIcon[0].className +" plusOpen";
				if(treeview.root[0].childNodes.length == 2){
					var spanNodeEnd = treeview.root[0].childNodes[1].getElementsByClassName("k-bot");
					spanNodeEnd[0].className="" + "k-bot ng-scope font-weight-tree";
				}
				
			}else{
				if(node.childElementCount == 0 || node.childElementCount){
					var spanNodeFirst = node.getElementsByClassName("k-top");
					var spanNodeMid = node.getElementsByClassName("k-mid")
					var spanNodeBot = node.getElementsByClassName("k-bot")
					var removeSpan = node.getElementsByClassName("k-in");
					
					var cloneNode = removeSpan[0].cloneNode();
					cloneNode.innerHTML = removeSpan[0].innerHTML;
					
					var spanChild = document.createElement('span');
					spanChild.className="k-icon k-minus plusOpen";
					
					if(spanNodeFirst.length > 0){
						spanNodeFirst[0].removeChild(removeSpan[0]);
						spanNodeFirst[0].appendChild(spanChild + removeSpan);
					}else if(spanNodeMid.length > 0){
						spanNodeMid[0].removeChild(removeSpan[0]);
						spanNodeMid[0].appendChild(spanChild);
						spanNodeMid[0].appendChild(cloneNode);
					}else if(spanNodeBot.length > 0){
						spanNodeBot[0].removeChild(removeSpan[0]);
					}
							
					
				}
			}
			
		});
	}
	
	function treeCollapse(e) {
		
		var treeview = $("#treeview").data("kendoTreeView");
		for(var i = 0 ; i<treeview.root.length ; i++){
			var node = treeview.root[i];
			var itemNode = node.childNodes[0].getElementsByClassName("k-top");
			var itemNodeOne = node.childNodes[0].getElementsByClassName("k-icon");
			if(i == treeview.root.length - 1) {
				itemNode[0].className  ="" + "k-top borderPlus"; 
				itemNodeOne[0].className ="" + "k-icon k-plus plusOpen";
			}
		}
		if(treeview.root[0].childNodes.length == 2){
			var spanNodeClose= treeview.root[0].childNodes[1].getElementsByClassName("k-bot");
			spanNodeClose[0].className="" + "k-bot ng-scope endBorderOut";
		}
		
	}

	function chkSelectAll() {
		
    	var grid = $("#gridView").data("kendoGrid");
    	grid.table.find("tr").each(function(idx, item) {
    		var row = $(item);
    		var checkbox = $('[name="gridcheckbox"]', row);
    		if($('[name="gridchkselectall"]').is(':checked')) {
    			var temp = checkbox[0];
    			checkbox.prop('checked', true);
				
    		} else {
    			checkbox.prop('checked', false);
				
    		}


    		
        });

			if($('[name="gridchkselectall"]').is(':checked')) {
    			
				caller.chkSelectAll1(true);
    		} else {
    			
				caller.chkSelectAll1(false);
    		}

	
    }
    
    function onChangeCheckbox() {
		var grid = $("#gridView").data("kendoGrid");
    	
    	var checkAll = true;
    	grid.table.find("tr").each(function(idx, item) {
    		var row = $(item);
    		var checkbox = $('[name="gridcheckbox"]', row);
    		if(!checkbox.is(':checked')) {
    			checkAll = false;
    		}
    		
        });

    	// Set checkall
    	$('[name="gridchkselectall"]').prop('checked', checkAll);
	}
	
	$scope.chkSelect = function(e) {
    	var grid = $("#gridView").data("kendoGrid");    	
    	var checkAll = true;
    	grid.table.find("tr").each(function(idx, item) {
    		var row = $(item);
    		var checkbox = $('[name="gridcheckbox"]', row);
    		if(!checkbox.is(':checked')) {
    			checkAll = false;
    		}
    		
        });

    	// Set checkall
    	$('[name="gridchkselectall"]').prop('checked', checkAll);
	}
	
	function searchAdOrg() {
		SearchService.searchAdOrg($scope.value, $scope.name).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			$("#treeview").data("kendoTreeView").dataSource.read();
		}, function(error) {
		});
	}
	
	function searchOnGrid(popupId, searchType) {
		if (searchType) {
			switch (searchType) {
			   case 'Partner':
				   	searchBudget();
				   	break;	
			   case 'Budget':
					searchBudget();
			        break;
				case  'account' :
					searchAccount();
			        break;
				case 'costType':
				    searchCostType();
				    break;   
				case 'salesRegion':
					searchRevenueType();
				    break; 
				case 'costCenter':
					searchCostCenter();
				    break;   
				case 'profitCenter':
					searchProfitCenter();
				    break; 
				case 'searchProject': 
					searchProject();
					 break;
				case 'product': 
					searchProduct();
					 break;
				case 'mwarehouse': 
					searchMwarehouce();
					 break;
				case 'searchLocation': 
					searchLocation();
					 break; 
				case 'searchCSiteCodeInfo': 
					searchCSiteCodeInfo();
					 break; 	
				case 'searchStatement': 
					searchStatement();
					 break; 
				case 'searchBank': 
					searchBank();
					 break; 
				case 'searchProductCategory': 
					searchProductCategory();
					 break; 
				case 'cBpartner': 
					searchPartner();
					 break; 
				case 'station': 
					searchStation();
					 break; 
				case 'project': 
					searchProject();
					 break; 
				case 'projectPhase': 
					searchProjectPhase();
					 break; 
				case 'construction': 
					searchConstruction();
					 break;
				case 'constructionGroup': 
					searchConstructionGroup();
					 break;
				case 'constructionPhase': 
					searchConstructionPhase();
					 break; 
				case 'searchDocumentType': 
					 searchDocumentType1();
				        break;
				case 'cperiod': 
					searchCPeriod();
					 break;
				case 'payroll':
					searchPayroll();
					break;
				case 'contract':
			    	searchContract();
			        break;
				case 'siteCodeType':
					searchSiteCodeType();
					break;
				case 'PersonalEvaluation':
					searchCatEmployee();
					break;
				case 'showNoiDungPS':
					searchSceneGenerateWork();
					break;
				case 'searchPartnerHr':
					searchPartnerHr();
					break;
				case 'searchICntCntract':
					searchICntCntract();
					break;
				case 'searchIProInvesPro':
					searchIProInvesPro();
					break;
			}
		}
		
		switch (popupId) {
			//ArRevenueType
		
	       case PopupConst.ProposalEvaluation['Partner']:
	    	   searchParner();
	        break;
	       case PopupConst.ProposalEvaluation['Project']:
	    	   searchProject();
	        break;
		    default:
				break;		  
		}
		switch (popupId) {
       case PopupConst.AcceptanceOfConstructionJobs['showPopupWorkForm']:
    	   searchEstimatesWorkItems();
        break;
	    default:
			break;		  
		}
		switch (popupId) {
	       case PopupConst.distanceUnloadedMaterialsList['showNoiDungPS']:
	    	   searchSceneGenerateWork();
	        break;
		    default:
				break;		  
			}
		switch (popupId) {
	       case PopupConst.BMaterial['showPopup']:
	    	   searchEstimatesDetailAnalyst();
	        break;
		    default:
				break;		  
			}
	}
	
	function searchPartnerHr() {
		SearchService.searchPartnerHr($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchCatEmployee() {
		SearchService.searchCatEmployee($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchParner() {
		SearchService.searchParner($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	function searchParner() {
		SearchService.searchProject($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	//tungpv
	function searchEstimatesWorkItems() {
		var item = CommonService.getItem();
		$scope.searchGrid.constructionId = item.constructId;
		SearchService.searchEstimatesWorkItems($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	//end tungpv
	//minhpvn
	function searchEstimatesDetailAnalyst() {
		var item = CommonService.getItem();
		$scope.searchGrid.constructId = item.constructId;
		SearchService.searchEstimatesDetailAnalyst($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	//end minhpvn
	//phong
	function searchSceneGenerateWork() {
		var item = CommonService.getItem();
		$scope.searchGrid.constructionId = item.constructId;
		SearchService.searchSceneGenerateWork($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchICntCntract(){
		SearchService.searchICntCntract($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchIProInvesPro(){
		SearchService.searchIProInvesPro($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	//end phong
	function searchSiteCodeType() {
		SearchService.searchSiteCodeType($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	function searchMwarehouce() {
		SearchService.searchMwarehouce($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchAccount() {
		SearchService.searchAccount($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchBpartner() {
		SearchService.searchBpartner($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchPayroll() {
		SearchService.searchPayroll($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
			console.log("error!!!!!!!!!!");
		});
	}
	
	function searchBudget() {
		SearchService.searchBudget($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchCostType() {
		SearchService.searchCostType($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchDocumentType() {
		$scope.code = $scope.code == undefined ? '' : $scope.code;
		$scope.name = $scope.name == undefined ? '' : $scope.name;
		SearchService.searchDocumentType($scope.code, $scope.name).then(function(result){
				$scope.data = new kendo.data.DataSource({ data: result.plain()});
				$("#gridView").data("kendoGrid").setDataSource($scope.data);
				$("#gridView").data("kendoGrid").dataSource.read();
				$("#gridView").data("kendoGrid").refresh();
			}, function(error) {
		});
	}
	
	
	//searchDocument
	function searchDocumentType1() {
		SearchService.searchDocumentType1($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchPeriod() {
		$scope.code = $scope.code == undefined ? '' : $scope.code;
		$scope.name = $scope.name == undefined ? '' : $scope.name;
		SearchService.searchPeriod($scope.code, $scope.name).then(function(result){
				$scope.data = new kendo.data.DataSource({ data: result.plain()});
				$("#gridView").data("kendoGrid").setDataSource($scope.data);
				$("#gridView").data("kendoGrid").dataSource.read();
				$("#gridView").data("kendoGrid").refresh();
			}, function(error) {
		});
	}
	
	function searchCPeriod() {
		SearchService.searchCPeriod($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchProfitCenter() {
		SearchService.searchProfitCenter($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchCostCenter() {
		SearchService.searchCostCenter($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchRevenueType() {
		SearchService.searchRevenueType($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchProduct() {
		SearchService.searchProduct($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	
	function searchContract() {
		SearchService.searchContract($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
//	function searchInOutcomeTypeMap() {
//		SearchService.searchInOutcomeTypeMap($scope.searchGrid).then(function(result){
//			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
//			var grid = $("#gridView").data("kendoGrid");
//			grid.dataSource = $scope.data;
//			$scope.data.read();
//			grid.refresh();
//		}, function(error) {
//		});
//	}
	function searchPeriod() {
		SearchService.searchPeriod($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	function searchStatement() {
		SearchService.searchStatement($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	function searchDepartment() {
		SearchService.searchDepartment($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	function searchPartner() {
		SearchService.searchPartner($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}	
	function searchProject() {
		SearchService.searchProject($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}	
		function searchProjectPhase() {
		SearchService.searchProjectPhase($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
		function searchConstruction() {
		SearchService.searchConstruction($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}
	    function searchConstructionGroup() {
			SearchService.searchConstructionGroup($scope.searchGrid).then(function(result){
				$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
				var grid = $("#gridView").data("kendoGrid");
				grid.dataSource = $scope.data;
				$scope.data.read();
				grid.refresh();
			}, function(error) {
			});
		}
	function searchLocation() {
		SearchService.searchLocation($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}	
		function searchConstructionPhase() {
		SearchService.searchConstructionPhase($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}	
	function searchProductCategory() {
		SearchService.searchProductCategory($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}	
	
	function searchCSiteCodeInfo() {
		SearchService.searchCSiteCodeInfo($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}	
	
	function searchBank() {
		SearchService.searchBank($scope.searchGrid).then(function(result){
			$scope.data = new kendo.data.HierarchicalDataSource({ data: result.plain()});
			var grid = $("#gridView").data("kendoGrid");
			grid.dataSource = $scope.data;
			$scope.data.read();
			grid.refresh();
		}, function(error) {
		});
	}	
	function cancel() {
		CommonService.dismissPopup();
		caller.onCancel($scope.popupId);
	}
	
	function getSelectedRow(grid) {
		var selectedRow = [];
		grid.table.find("tr").each(function(idx, item) {
			var row = $(item);
			var checkbox = $('[name="gridcheckbox"]', row);
			
			if(checkbox.is(':checked')) {
				var tr = grid.select().closest("tr");
	        	var dataItem = grid.dataItem(item);
	        	selectedRow.push(dataItem);
			}
	    });
		
		return selectedRow;
	}
	
	function save(dataItem) {
		var grid = $("#gridView").data("kendoGrid");
		if(dataItem){
			caller.onPopupSave($scope.popupId,dataItem);
		} else{
		if ($scope.isMultiSelect == true) {
			var selectedRows = getSelectedRow(grid);
			caller.onPopupSave($scope.popupId, selectedRows,true);
		} else {
			
			caller.onPopupSave($scope.popupId);
			}
		}
		// close popup
		CommonService.closePopup();
	}

	
	function submit() {
		caller.submit($scope.searchGrid.comment);
		// close popup
		CommonService.closePopup();
	}
	
	function saveBpopup() {
		var grid = $("#gridView").data("kendoGrid");
		var selectedRows = getSelectedRow(grid);
		if (selectedRows.length>0) {
			caller.onSave($scope.popupId, selectedRows);
		} else {
			caller.onSave($scope.popupId);
		}
		
		
		// close popup
		CommonService.closePopup();
	}
	$scope.saveMultipopup=saveMultipopup;
	function saveMultipopup() {
		var grid = $("#gridView").data("kendoGrid");
		var selectedRows = getSelectedRow(grid);
	
			caller.onSaveMulti(selectedRows);
		
		
		
		// close popup
		CommonService.closePopup();
	}
	
	$scope.saveGrid= function(dataItem){
		caller.saveGrid(dataItem);
		
		CommonService.closePopup();
	}
	
	$scope.keydown = function($event){
		 if (event.keyCode == 13) {
			 searchOnGrid($scope.popupId, $scope.searchType);
		    }
	        
	    }

	$scope.listCheck=[];
	function handleCheck(dataItem){
			console.log(dataItem);
		if(dataItem.selected){
		
		} else {
			$('[name="gridchkselectall"]').prop('checked', false);
		}
		caller.handleCheck(dataItem);

		
	}
	
	$(document).on("click",".k-overlay",function(){
			 // $("div.k-window-actions > a.k-window-action > span.k-i-close").click();
			 CommonService.closePopup();
		
			});
	
}]);