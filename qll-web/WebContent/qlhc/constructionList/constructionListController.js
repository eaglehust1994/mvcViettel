(function() {
	'use strict';
	var controllerId = 'constructionListController';
	angular.module('MetronicApp').controller(controllerId,
			constructionListController);

	/* @ngInject */
	function constructionListController($scope, $rootScope , $timeout, Constant, gettextCatalog, kendoConfig, constructionListService,ProposalEvaluation, $kWindow, CommonService, PopupConst, Restangular, RestEndpoint,hshcAuthService) {
		var vm = this;
		$scope.object = {};
		vm.showCreate = false;
		vm.showDetail = true;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		vm.constructionListService = constructionListService;
		vm.hshcAuthService = hshcAuthService;
		// Object lưu thông tin những trường cần thực hiện thêm mới
        vm.objectsData = {};
        vm.item = {};
        vm.item = ProposalEvaluation.getItem();
        constructionListService.openEstimatesWorkItemsFormAccept(vm.item).then(function(d) {
			refreshGrid(d.plain());
		}, function() {
			console.error('Error while fetching object type');
		});
        
       
      
		///////////////////////////////////////Handle Event/////////////////////////////////////////////

		vm.initFormAccept = initFormAccept;
		vm.initFormAccept();
		function initFormAccept(){
			constructionListService.openEstimatesWorkItemsFormAccept(vm.item).then(function(d) {
				refreshGrid(d.plain());
			}, function() {
				console.error('Error while fetching object type');
			});
		};
		
		vm.exportFile = exportFile;
		function exportFile(){
			//$("#constructionListMainGrid").data("kendoGrid").saveAsExcel();
			var ds = $("#constructionListMainGrid").data("kendoGrid").dataSource;
			var rows = [{
		        cells: [
		          { value: "STT" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Mã Công trình" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Mã hiệu công việc" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Tên công việc" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Đơn vị tính" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Khối lượng theo HĐ" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Đơn giá theo HĐ" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Thành tiền" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
		          { value: "Trạng thái" , textAlign: "center", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}
		        ]
		      }];
			ds.fetch(function(){
		        var data = this.data();
		        for (var i = 0; i < data.length; i++){
		          //push single row for every record
		        	rows.push({
			            cells: [
			              { value: i+1 , textAlign: "center", borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: data[i].constrtCode , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: data[i].workItemCode , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: data[i].workItemName , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: data[i].unit , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: data[i].workAmount , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: data[i].unitPrice , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: data[i].workAmount * data[i].unitPrice , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }},
			              { value: status(data[i].status) , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 }}
			            ]
		          })
		        }
		        var workbook = new kendo.ooxml.Workbook({
		          sheets: [
		            {
		            	columns: [
		                // Column settings (width)
		                { autoWidth: true },
		                { autoWidth: true },
		                { autoWidth: true },
		                { autoWidth: true },
		                { autoWidth: true },
		                { autoWidth: true },
		                { autoWidth: true },
		                { autoWidth: true },
		                { autoWidth: true },
		              ],
		              
		              // Title of the sheet
		              title: "Danh sách công việc xây lắp",
		              // Rows of the sheet
		              rows: rows
		            }
		          ]
		        });
		        //save the file as Excel file with extension xlsx
		        kendo.saveAs({dataURI: workbook.toDataURL(), fileName: "Danh sach cong viec xay lap.xlsx"});
		      });
		};
		
		function refreshGrid(d) {
			var grid = vm.constructionListGrid;
			if(grid){
			grid.dataSource.data(d);
			grid.refresh();
			}
		}
		findPartner(vm.item);		
		function findPartner(object) {
			hshcAuthService.getConstructions(object).then(function(data) {
				vm.item.doitac = data.partnerName;						
			  });
			
		}
		 $scope.$on("ProposalEvaluation.detail", function (event, item) {
	        	if(item != undefined){
	        		vm.item = item;
	        		$scope.object = item;
	        		vm.initFormAccept();
	        		findPartner(vm.item);
	        	}else{
	        		console.error("không nhận được dữ liệu!");
	        	}
	        });
		
		// Cấu hình combobox trạng thái
        vm.statusOptions = {
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.objectsData.status = dataItem.value;
                
                if(dataItem.value == 1){
                	vm.item.checkcode = 0;
                	initFormAccept();               	
                }
                if(dataItem.value == 2){
                	vm.item.checkcode = 1;
                	initFormAccept();
                }
                if(dataItem.value == 3){
                	vm.item.checkcode = 2;
                	initFormAccept();
                }
          
            },
            dataSource: [{
                value: 1,
                name: 'Chưa nghiệm thu'
            }, {
                value: 2,
                name: 'Đã nghiệm thu'
            }, {
                value: 3,
                name: 'Tất cả'
            }],
            height: 300,
            dataTextField: "name",
            dataValueField: "value"
        };
        vm.objectsData.status = 3;
        
        function status(x){
			if(x == 1){
				return "Đã nghiệm thu";
			}else if(x == 0 )
			{return "Chưa nghiệm thu"}
			else if(x == 2){
				return "Tạm dừng";
			}else{
				return "Không xác định";
			}
		}
        
		//////////////////////////////////////////////////Grid//////////////////////////////////////////
        
        vm.gridConstructionListOptions = kendoConfig.getGridOptions({
			autoBind : true,
			dataSource : [],
			editable: false,
			filterable:false,
			sortable: true,
			excel: {
		          fileName: "Construction List.xlsx",
		          filterable:true,
		          allPages:true,
		        },
		    pageable: {
		            pageSizes: true,
		            refresh: false,
		            pageSize: 20,
		            messages: {
		                display: " {0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"

		            }
		         },
			noRecords : true,
			messages : {
				noRecords : gettextCatalog
						.getString("Không có dữ liệu trong trang")
			},
			columns : [ {
				title : gettextCatalog.getString("STT"),
				field: "rowNumber",
				template: dataItem => $("#constructionListMainGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
		        width : 70,
		        headerAttributes: {style:"text-align:center;"},
				attributes:{style:"text-align:center;"}
			}, {
				title : gettextCatalog.getString("Mã Công trình"),
				field : "constrtCode",
				width : 200
			}, {
				title : gettextCatalog.getString("Mã hiệu công việc"),
				field : "workItemCode",
				width : 150
			}, {
				title : gettextCatalog.getString("Tên công việc"),
				field : "workItemName",
				width : 150
			}, {
				title : gettextCatalog.getString("Đơn vị tính"),
				field : "unit",
				width : 150
			}, {
				title : gettextCatalog.getString("Khối lượng theo HĐ"),
				field : "workAmount",
				width : 170,
				headerAttributes: {style:"text-align:center;"},
				attributes:{style:"text-align:right;"}
			}, {
				title : gettextCatalog.getString("Đơn giá theo HĐ"),
				field : "unitPrice",
				width : 150,
				headerAttributes: {style:"text-align:center;"},
				attributes:{style:"text-align:right;"}
			}, {
				title : gettextCatalog.getString("Thành tiền"),
				field : "total",
				template : function($scope){
					return $scope.workAmount * $scope.unitPrice;
				},
				width : 150,
				headerAttributes: {style:"text-align:center;"},
				attributes:{style:"text-align:right;"}
			}, {
				title : gettextCatalog.getString("Trạng thái"),
				field : "status",
				template: function($scope){
					if($scope.status == 1){
						return "Đã nghiệm thu";
					}else if($scope.status == 0 )
					{return "Chưa nghiệm thu"}
					else if($scope.status == 2){
						return "Tạm dừng";
					}else{
						return "Không xác định";
					}
				},
				width : 150
			} ]
		});
        
        $("#constructionListMainGrid").kendoTooltip({
			filter: "td",
            content: function (e) {
              var target = e.target; // element for which the tooltip is shown
              return $(target).text();
            }
	    }).data("kendoTooltip");
        
	}
})();