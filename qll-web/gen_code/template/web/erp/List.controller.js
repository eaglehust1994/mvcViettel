(function () {
    'use strict';

    var controllerId = '${tbl.tableNameJV}ListController';

    angular.module('MetronicApp').controller(controllerId, ${tbl.tableNameJV}ListController);
    
    /* @ngInject */
    function ${tbl.tableNameJV}ListController($scope, $rootScope, $timeout, Constant, kendoConfig, WindowService, 
    		${tbl.tableNameJV}Service, $kWindow, CommonService, Restangular, $localStorage, $compile, RestEndpoint) {    	
        var vm = this;
		// vm.homeLoader = $('body').loadingIndicator({
    		// useImage: false,
    		// showOnInit: false
    	// }).data("loadingIndicator");
		vm.isShowAdvancedSearch=true;
        vm.data = {
			${tbl.tableNameVar}Id: 0, 			
			<#list  tbl.columnBOs as clm >
			<#if clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("creatorId") && !clm.columnVar?contains("creatorGroupId") && !clm.columnVar?contains("creatorDate") && !clm.columnVar?contains("updatorId") && !clm.columnVar?contains("updatorGroupId") && !clm.columnVar?contains("updatorDate") >
			${clm.columnVar}: '',
			</#if>
			</#list> 
			isActive: 1, 			
			creatorId:'',
			creatorGroupId:'',
			creatorDate:'',
			updatorId:'',
			updatorGroupId:'',
			updatorDate:'',
		};
		vm.data_temp = angular.copy(vm.data);
    	vm.criteriaSearch = {
			name: '', 
			value: '', 
			description: ''
		};
    
        vm.isCreateNew = false;
        vm.showDetail = false;//Khôn dùng
		vm.showSearch = false;// Không dùng
        vm.add = add;
        vm.edit = edit;//Không dùng
		vm.detail=detail;
        
        vm.remove = remove;
		vm.removeItem=removeItem;
		
        vm.onCheckAll = onCheckAll;
        vm.onCheck = onCheck;
        vm.checkedItems = [];
        vm.doSearch = doSearch;
        vm.canceldoSearch = canceldoSearch;
		
		vm.rowIndex = 0;
		var message = {
			noDataGrid: CommonService.translate("Không có dữ liệu trên trang hiện tại"),
			lineRequired: CommonService.translate("Bạn cần chọn một dòng trước"),
			recordRequired: CommonService.translate("Bạn cần chọn một dòng trước"),
			needShowDetail : CommonService.translate("Cần hiển thị ở chế độ Chi Tiết!"),
			positionLast: CommonService.translate("Đã ở bản ghi cuối"),
			positionFirst: CommonService.translate("Đã ở bản ghi đầu"),
			duplicateValue: CommonService.translate("Mã danh mục đã tồn tại"),
			createError: CommonService.translate("Lỗi khi tạo mới bản ghi"),
			createSuccess: CommonService.translate("Tạo mới thành công"),
			updateError: CommonService.translate("Lỗi khi cập nhật bản ghi"),
			updateSuccess: CommonService.translate("Cập nhật thành công"),
			deleteConfirm: CommonService.translate("Bạn thực sự muốn xóa bản ghi ?"),
			deleteError: CommonService.translate("Xóa không thành công"),
			deleteSuccess: CommonService.translate("Xóa thành công"),
			usedRecord: CommonService.translate("Bản ghi đã được sử dụng"),
			getDataError: CommonService.translate("Lỗi xảy ra khi lấy dữ liệu"),
			adOrgIdRequired: CommonService.translate("Đơn vị không được để trống"),
			<#list  tbl.columnBOs as clm >
		    <#if clm.isKey == false && clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("creatorId") && !clm.columnVar?contains("creatorGroupId") && !clm.columnVar?contains("creatorDate") && !clm.columnVar?contains("updatorId") && !clm.columnVar?contains("updatorGroupId") && !clm.columnVar?contains("updatorDate")>	
			${clm.columnVar}Required: CommonService.translate("${clm.columnVar} không được để trống"),			
		    </#if>
		    </#list>
        }
        
        vm.validatorOptions = kendoConfig.get('validatorOptions');
		//fetchAll();
        fillDataTable();       
        
        function getEmptyDataModel() {
        	var objReturn = {
				${tbl.tableNameVar}Id: 0, 				
				<#list  tbl.columnBOs as clm >
				<#if clm.isKey == false && clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("creatorId") && !clm.columnVar?contains("creatorGroupId") && !clm.columnVar?contains("creatorDate") && !clm.columnVar?contains("updatorId") && !clm.columnVar?contains("updatorGroupId") && !clm.columnVar?contains("updatorDate")>	
				${clm.columnVar}: '',
				</#if>
				</#list>  
								
				isActive: '', 			
				creatorId:CommonService.getUserInfo().userId,
				creatorGroupId:CommonService.getUserInfo().groupId,				
				updatorId:CommonService.getUserInfo().userId,
				updatorGroupId:CommonService.getUserInfo().groupId																																												
			};        
        	return objReturn;
		}
		
		vm.showHideColumn=function(column){
        	if (angular.isUndefined(column.hidden)) {
        		vm.${tbl.tableNameVar}Grid.hideColumn(column);
            } else if (column.hidden) {
            	vm.${tbl.tableNameVar}Grid.showColumn(column);
            } else {
            	vm.${tbl.tableNameVar}Grid.hideColumn(column);
            }
        	
        	
        }
		/*
		** Filter các cột của select 
		*/	
	
		vm.gridColumnShowHideFilter = function (item) { 
                return item.type==null||item.type !=1; 
            };
		function buildParameters (params) {
                var _dataSearch = angular.copy(vm.criteriaSearch);
                if (params.sort && params.sort.length > 0) {
                    _dataSearch.orderBy = params.sort[0].field || "";
                    _dataSearch.orderType = params.sort[0].dir || "asc";
                }
                if (params.filter && params.filter.filters && params.filter.filters.length > 0) {
                    angular.forEach(params.filter.filters, function (value, key) {
                        _dataSearch[value.field] = value.value;
                    });
                }
                _dataSearch.page = params.page ;
                _dataSearch.size = params.pageSize;
                return _dataSearch;
            };
			
        function fillDataTable(data) {
			var query = '';
			var record = 0;
    		if ($rootScope.redirectRecordId !== undefined && $rootScope.redirectRecordId !== null && $rootScope.redirectRecordId > 0) {
    			query += 'redirectRecordId=' + $rootScope.redirectRecordId;
    		}    		
    		if (query !== '') {
    			query = '?' + query;
    		}
        	vm.gridOptions = kendoConfig.getGridOptions({
                autoBind: true,
				selectable: "multiple cell",
				 toolbar: [
                          {
                              name: "actions",
                              template: $scope.getTemplateBySelector("#${tbl.tableNameJV}ListAction")
                          }
                          ],
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
					if(this.dataSource.total()<=1){
                    	this.pager.element.hide();
                    }
                },
				dataSource: {
                    transport: {                        
						read: function(e){
							var _dataSearch = buildParameters(e.data);
							${tbl.tableNameJV}Service.doSearch(_dataSearch).then(function(response){
								e.success(response.plain());
								}, function(err){
									if(err.data && err.data.status==Constant.http.BUSINESS_ERROR){
										toastr.warning(CommonService.translate(err.data.errorMessage));
									}
									e.success([]);
									console.err(err);
								}
							)
						}																		                         
                    },
					pageSize: Constant.pageSize,
                    schema: {
                        data: function(data){
							return data.rows
						},                         
						total: function (data) {
							return data.totalRow;
						},
                        model: {
                            fields: {
								<#list  tbl.columnBOs as clm >
									<#if clm.isKey == false && clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("creatorId") && !clm.columnVar?contains("creatorGroupId") && !clm.columnVar?contains("creatorDate") && !clm.columnVar?contains("updatorId") && !clm.columnVar?contains("updatorGroupId") && !clm.columnVar?contains("updatorDate")>	
										<#if clm.dataTypeJV == "Date">
										${clm.columnVar}: { type: "string" },										
										</#if>
									</#if>
								</#list>
                            	isActive: { type: "list" }
                           }
                        } 
                    },
                    serverPaging: true,
					serverSorting: true,
                    serverFiltering: true					
                },                				
				resizable: true,
				reorderable: true,
				editable: false,
				columnMenu: false,
                messages: {
                    noRecords: (message.noDataGrid)
                },
				pageable: {
					refresh: false,
					pageSizes: Constant.pageSizes,
					messages: {
						display: "{0}-{1}" +CommonService.translate("của") +"{2}"+ CommonService.translate("kết quả"),
						itemsPerPage: CommonService.translate("kết quả/trang"),
						empty: CommonService.translate("Không có kết quả hiển thị")
					}
				},
                columns: [{
                	title: "STT",
                	template: function () {
                		return ++record;
                	},
                	width: 50,
					locked:true,
					
					type:1,//không hiển thị trong bỏ ẩn hiện cột
					 headerAttributes: {
						style: "text-align:center;"
					}
                },
				 {   
			    	title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.onCheckAll($event);' ng-model='vm.chkAll' />",
			        template: "<input type='checkbox' name='gridcheckbox' ng-click='vm.onCheck($event)'/>",					
			        width: 35,
					type:1,//không hiển thị trong bỏ ẩn hiện cột
					locked:true,
					 columnMenu: false,

					 headerAttributes: {
						style: "text-align:center;"
					}
			    } ,
				{
					title: CommonService.translate("Tác vụ"),
			        template:dataItem =>
			        	'<div class="text-center">'
					+'<div class="btn-group btn-group-solid btn-action-group" >'
					+' <button type="button" title="Sửa"  ng-click="vm.edit(dataItem)" class="btn default padding-button box-shadows"> <i class="fa fa-pencil"></i></button>'	
					+' <button type="button" title="Xem"  ng-click="vm.detail(dataItem)" class="btn default padding-button box-shadow"> <i class="fa fa-eye"></i></button>'
					+' <button type="button" title="Xóa"  ng-click="vm.remove(dataItem)" class="btn default padding-button box-shadow"> <i class="fa fa-trash"></i></button>'
		            		+'</div>'                		               		                	
			        	
					+'</div>',
			        width: 100,	
			        locked:true,
			columnMenu: false,

			        type:1,//không hiển thị trong bỏ ẩn hiện cột
			        headerAttributes: {
						style: "text-align:center;"
					}
				},
				<#list  tbl.columnBOs as clm >
				<#if clm.isKey == false && clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("creatorId") && !clm.columnVar?contains("creatorGroupId") && !clm.columnVar?contains("creatorDate") && !clm.columnVar?contains("updatorId") && !clm.columnVar?contains("updatorGroupId") && !clm.columnVar?contains("updatorDate")>			
				<#if clm.dataTypeJV == "Date">
				{
                    title: CommonService.translate("${clm.columnName}"),
                    field: "${clm.columnVar}",
                    width: 100,
                    type: 'date',
                    format: "{0:dd/MM/yyyy}",
                    parseFormats: "{0:dd/MM/yyyy}",   
					columnMenu: false,					
                },	
				<#else>
				{
                    title: CommonService.translate("${clm.columnName}"),
                    field: "${clm.columnVar}",
                    width: 100,
					columnMenu: false
                },	
				</#if>
				</#if>								
				</#list>				
				]
            });			
        }
        
    	function setHeight(){
		    var h = $(window).height();
		    angular.element("#${tbl.tableNameVar}GridId").height(h-120);  
		    window.setTimeout(function() {
		    	resizeGrid();
	    	}, 1);
		}
	
		angular.element(document).ready(function () {
    		setHeight();
    	});    
		
    	angular.element(window).resize(function(){
			setHeight();
		});
                				       
		
        //Chuyener sang manf hinhf add        
        function add() {
			$(".k-invalid-msg").hide();        	           
            vm.data = getEmptyDataModel();
            $rootScope.RedirectTo${tbl.tableNameJV}FormEvent={
				data:vm.data,
				type:'add'
			}
			CommonService.goToMenu('${tbl.tableName}Form',{})
			//
        }
		
		//Chuyen sang man hinh edit
		function edit(dataItem){
			$rootScope.RedirectTo${tbl.tableNameJV}FormEvent={
				data:dataItem,
				type:'edit'
			}
			CommonService.goToMenu('${tbl.tableName}Form',{})
		}
		//Chuyen sang man hinh view
		function detail(dataItem) {
            $rootScope.RedirectTo${tbl.tableNameJV}FormEvent={
				data:dataItem,
				type:'view'
			}
			CommonService.goToMenu('${tbl.tableName}Form',{})
        }
                                        
		
		function convertDate(dateString) {
			var parts = dateString.split("/");
		    return new Date(parts[2], parts[1] - 1, parts[0]);
		}
		
		
		
		function dateValidation(e) {
			var dateformat = /^(0[1-9]|[12][0-9]|3[01])[\- \/.](?:(0[1-9]|1[012])[\- \/.](201)[2-9]{1})$/;
		    if (e.match(dateformat)) {
		        var opera1 = e.split('/');
		        var lopera1 = opera1.length;
		        if (lopera1 > 1) {
		            var pdate = e.split('/');
		        }
		        var mm = parseInt(pdate[1]);
		        var dd = parseInt(pdate[0]);
		        var yy = parseInt(pdate[2]);
		        var ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		        if (mm == 1 || mm > 2) {
		            if (dd > ListofDays[mm - 1]) {
		                return true;
		            }
		        }
		        if (mm == 2) {
		            var lyear = false;
		            if ((!(yy % 4) && yy % 100) || !(yy % 400)) {
		                lyear = true;
		            }
		            if ((lyear == false) && (dd >= 29)) {
		                return true;
		            }
		            if ((lyear == true) && (dd > 29)) {
		                return true;
		            }
		        }
		    } else {
		        return true;
		    }
		    return false;
        }
		
		
		
		
		

		function onCheckAll(event) {
			var grid = vm.${tbl.tableNameVar}Grid;
			chkSelectAllBase(vm.chkAll, grid);
			
			var grid = $(event.target).closest("[kendo-grid]").data("kendoGrid");
            var items = grid.dataSource.data();
            vm.checkedItems = [];
            items.forEach(function(item){
                if (event.target.checked) {
					<#list  tbl.columnBOs as clm >
						<#if clm.isKey == true>			
					vm.checkedItems.push(item.${clm.columnVar});						
						</#if>
					</#list>
                }                
            });
		}
		
		function onCheck(event) {
			var element = angular.element(event.currentTarget);
			var checked = element.is(':checked');
			var row = element.closest("tr");
			var gidContent = row.closest("div");
			var grid = gidContent.parent().getKendoGrid();
			var dataItem = grid.dataItem(row);
			if (checked) {	
				<#list  tbl.columnBOs as clm >
						<#if clm.isKey == true>		
				if (!contains(vm.checkedItems, dataItem.${clm.columnVar})) {
					vm.checkedItems.push(dataItem.${clm.columnVar});
				} 								
					</#if>
				</#list>
				
			} else {
				<#list  tbl.columnBOs as clm >
						<#if clm.isKey == true>		
				var index = vm.checkedItems.indexOf(dataItem.${clm.columnVar});
				if (index > -1) {
					vm.checkedItems.splice(index, 1);
				}								
					</#if>
				</#list>
				
			}			
		} 
		
		function removeItem(dataItem){
			if(confirm(CommonService.translate(message.deleteConfirm))){
				<#list  tbl.columnBOs as clm >
					<#if clm.isKey == true>
				itemId = dataItem.${clm.columnVar};
					</#if>
				</#list>
				${tbl.tableNameJV}Service.deleteObject(itemId).then(function(){
					
					toastr.success(CommonService.translate(message.deleteSuccess));
					doSearch();
				}, function(errResponse){
					
					if (errResponse.status === 302){
						toastr.error(CommonService.translate(message.usedRecord));
					} else {
						toastr.error(CommonService.translate(message.deleteError));
					}
				});
			}
		}

        function remove(){
			if (vm.checkedItems.length == 0) {
				var itemId;
				
				var grid = vm.${tbl.tableNameVar}Grid;
				if (grid.select() == null || grid.select().length === 0) {
					toastr.warning(CommonService.translate(message.recordRequired));
					return;
				}
				var tr = grid.select().closest("tr");
				var dataItem = grid.dataItem(tr);
					
				<#list  tbl.columnBOs as clm >
				<#if clm.isKey == true>
				itemId = dataItem.${clm.columnVar};
				</#if>
				</#list>
				
				
				if (vm.${tbl.tableNameVar}Grid.select().length > 0 && confirm(CommonService.translate(message.deleteConfirm))) {
					${tbl.tableNameJV}Service.deleteObject(itemId).then(function(){
						
						toastr.success(CommonService.translate(message.deleteSuccess));
						doSearch();
					}, function(errResponse){
						
						if (errResponse.status === 302){
							toastr.error(CommonService.translate(message.usedRecord));
						} else {
							toastr.error(CommonService.translate(message.deleteError));
						}
					});
				}
			} else if(confirm(CommonService.translate(message.deleteConfirm))){
        		${tbl.tableNameJV}Service.deleteList(vm.checkedItems).then(function(){					
    				toastr.success(CommonService.translate(message.deleteSuccess));    			
					doSearch();
				
                }, function(errResponse){					
					if (errResponse.status === 302){
						toastr.error(CommonService.translate(message.usedRecord));
					} else {
						toastr.error(CommonService.translate(message.deleteError));
					}
				});
        	}        	
        }        

     // Export
		vm.exportFile=function exportFile() {
	    	${tbl.tableNameJV}Service.doSearch(vm.criteriaSearch).then(
				function(d) {
					var data = d.plain();
					var title = [
							CommonService.translate("Đơn vị")
							<#list  tbl.columnBOs as clm >
							<#if clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("isDeleted") && !clm.columnVar?contains("created") && !clm.columnVar?contains("createdby") && !clm.columnVar?contains("updated") && !clm.columnVar?contains("updatedby") && !clm.columnVar?contains("adClientId") && !clm.columnVar?contains("adOrgId")>
							, CommonService.translate("${clm.columnName}")
							</#if>
							</#list>
							, CommonService.translate("Hiệu lực")
							, CommonService.translate("Người tạo")
							, CommonService.translate("Ngày cập nhật")
						];
					exportExcel(title, buildDataExport(data), "Export" + " ${tbl.tableNameVar}");
				},
				function(errResponse){
					toastr.error(CommonService.translate(message.getDataError));
				}
			);	    	
	    }
	    
	    function buildDataExport(data) {
	    	// Row content
	    	var rData = [];
	    	$.each( data, function( index, value){
	    		var item = { cells: [
	    		               { value: value.adOrgName }
	    		               <#list  tbl.columnBOs as clm >
							   <#if clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("isDeleted") && !clm.columnVar?contains("created") && !clm.columnVar?contains("createdby") && !clm.columnVar?contains("updated") && !clm.columnVar?contains("updatedby") && !clm.columnVar?contains("adClientId") && !clm.columnVar?contains("adOrgId")>
							   <#if clm.columnVar?contains("Id") && clm.isKey == false>		
								, { value: value.${clm.columnVar?replace("Id", "Name")} }					
								<#else>
								, { value: value.${clm.columnVar} }
							   </#if>
							   </#if>
							   </#list>
								
	    		          ]};
	    		rData.push(item);
	    	});
	    	
	    	return rData;
	    }
		
   
        
        function canceldoSearch(){
			vm.criteriaSearch={}
			doSearch();
		}
		function doSearch(){
			vm.${tbl.tableNameVar}Grid.dataSource.page(1);
		}
				
		
		function resizeGrid() {
            var gridElement = angular.element("#${tbl.tableNameVar}GridId"),
             dataArea = gridElement.find(".k-grid-content");
            dataArea.height($(window).height() - 220);
        }                		            																		
					   
	 
    }
})();