(function () {
    'use strict';

    var controllerId = '${tbl.tableNameJV}FormController';

    angular.module('MetronicApp').controller(controllerId, ${tbl.tableNameJV}FormController);
    
    /* @ngInject */
    function ${tbl.tableNameJV}FormController($scope, $rootScope, $timeout, Constant, kendoConfig, WindowService, 
    		${tbl.tableNameJV}Service, $kWindow, CommonService, Restangular, $localStorage, $compile, RestEndpoint) {    	
        var vm = this;
	
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
        vm.data = {
			${tbl.tableNameVar}Id: 0, 			
			<#list  tbl.columnBOs as clm >
			<#if clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("creatorId") && !clm.columnVar?contains("creatorGroupId") && !clm.columnVar?contains("creatorDate") && !clm.columnVar?contains("updatorId") && !clm.columnVar?contains("updatorGroupId") && !clm.columnVar?contains("updatorDate") >
			${clm.columnVar}: '',
			<#else>
			<#if clm.isKey==true>
			${clm.columnVar}: 0,
			</#if>
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
        vm.save = save;        		                                   
                											      
        vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		
		//check redirect from where
        $scope.$watch($rootScope.RedirectTo${tbl.tableNameVar}FormEvent, function (newValue) {
        	if ($rootScope.RedirectTo${tbl.tableNameJV}FormEvent != null) {
				if ($rootScope.RedirectTo${tbl.tableNameJV}FormEvent.type == 'edit') {
					vm.data=$rootScope.RedirectTo${tbl.tableNameJV}FormEvent.data;
					vm.isCreateNew=false;
					vm.readonly=false;
				}else if($rootScope.RedirectTo${tbl.tableNameJV}FormEvent.type == 'view'){					
					vm.data=$rootScope.RedirectTo${tbl.tableNameJV}FormEvent.data;											
					vm.isCreateNew=false;
					vm.readonly=true;
				}else if($rootScope.RedirectTo${tbl.tableNameJV}FormEvent.type == 'add'){																			
					vm.isCreateNew=true;
					vm.readonly=false;
				}
				$rootScope.RedirectTo${tbl.tableNameJV}FormEvent = null;
			}
        });
		

        function save() {            
			if(formValidate()){				
				if(vm.isCreateNew) {//Trwowg
					${tbl.tableNameJV}Service.create(vm.data).then(function(result){
						vm.data_temp = angular.copy(vm.data);
						vm.isCreateNew = false;
						<#list  tbl.columnBOs as clm >
						<#if clm.isKey == true>
							vm.data.${clm.columnVar} = result;
						</#if>
						</#list>
						//vm.data.${tbl.tableNameVar}Id = result;							
						
						toastr.success(CommonService.translate(message.createSuccess));
					}, function(errResponse){
						
						if (errResponse.status === 409) {
							toastr.error(CommonService.translate(message.duplicateValue));
						} else {
							toastr.error(CommonService.translate(message.createError));
						}
						return;
					});
					//End trường hợp thêm mới
				} else {  // Trường hợp update              		
					${tbl.tableNameJV}Service.update(vm.data).then(function(){
							vm.data.updatorId = CommonService.getUserInfo().userId;
							vm.data.updatorGroupId = CommonService.getUserInfo().groupId
							vm.data_temp = angular.copy(vm.data);								
							
							toastr.success(CommonService.translate(message.updateSuccess));
						}, function(errResponse){
							
							if (errResponse.status === 409) {
								toastr.error(CommonService.translate(message.duplicateValue));
							} else {
								toastr.error(CommonService.translate(message.updateError));
							}
							return;
					});
					//End trường hợp update
				}
			}
            

        }	

		function formValidate() {
        	
			<#list  tbl.columnBOs as clm >
		    <#if clm.isKey == false && !clm.columnVar?contains("isActive") && !clm.columnVar?contains("creatorId") && !clm.columnVar?contains("creatorGroupId") && !clm.columnVar?contains("creatorDate") && !clm.columnVar?contains("updatorId") && !clm.columnVar?contains("updatorGroupId") && !clm.columnVar?contains("updatorDate") && !clm.nullable?contains("Y")>	
			 if (vm.data.${clm.columnVar} == null || vm.data.${clm.columnVar} == undefined || vm.data.${clm.columnVar} == '') {
        		toastr.warning(CommonService.translate(message.${clm.columnVar}Required));
        		return false;
        	}				
		    </#if>
		    </#list> 
        	return true;
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
		
	}			
				                		            															
					   
	 
    
})();