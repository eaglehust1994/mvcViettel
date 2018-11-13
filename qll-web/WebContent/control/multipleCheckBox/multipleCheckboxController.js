/**
 * Created by hanhls1 on 3/10/2017.
 */
(function() {

    'use strict';
    var app = angular.module('MetronicApp');
    app.controller('multipleCheckBoxController',	function($scope,$rootScope, gettextCatalog,Restangular,Constant,
                                                    kendoConfig,CommonService,tscdConstantService){
        var vm=this;
        vm.test="123";
        vm.showSearch = true;
        vm.searchForm={};
        
        vm.doSearch = function(){
            vm.grid.dataSource.page(1);
        }
        vm.doInit=function(){
        	 var curdate=new Date();        	 
        	 vm.searchForm.toHandoverDate=new Date();
        	 curdate.setMonth(curdate.getMonth()-1);
        	 vm.searchForm.fromHandoverDate=curdate;
        	// vm.doSearch();
        }
        
        vm.organizations=[];
        
        vm.temp={value:7000473,text:'Tập đoàn viễn thông quân đội',hasChildren:true, childrens:[],showSub:false};
        vm.organizations.push(vm.temp);
        vm.getOrganizationsByParent=function(organize){
        	if(!organize.hasChildren){
        		return ;
        	}
        	if(organize.showSub || organize.showSub){
        		organize.showSub=!organize.showSub;
        		return;
        	}
        	organize.showSub=!organize.showSub;
        	if(organize.childrens && organize.childrens.length>0){
        		return;
        	}        	
        	 Restangular.all("common/commonSysGroupServiceRest/" + 'getSysGroupAjax').get(organize.value).then(function (response) {
                 
                 organize.childrens=response.plain();
                 
             }).catch(function (err) {
                 if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                     toastr.warning(CommonService.translate(err.data.errorMessage));
                 }
                 e.success([]);
                 console.log('Không thể kết nối để lấy dữ liệu: ' + err);
             });
        }
       

        vm.cancelDoSearch= function (){
            vm.searchForm={};
        };
        //Chuyển sang chức năng tạo biên bản bàn giao
        vm.createLta= function(id,type){
            if(1== type) { // Hình thành qua xây lắp
                console.debug("chuyen sang hinh thanh qua xay lap");
                //Asset_CatAssetDetail
                $rootScope.Asset_CatAssetDetail_init={
                    type:1,
                    value:id
                };
                CommonService.goToMenu("Asset_CatAssetDetail_update",{});
            }
            else if (2==type){ // Hình thành không qua xây lắp
                console.debug("chuyen sang hinh thanh khong qua xay lap");
                $rootScope.Asset_LongTermAsset_Update_init={
                        type:1,
                        value:id
                    };
               
                CommonService.goToMenu("Asset_LongTermAsset_Update",{});
            }
        };
        //Chuyển sang mang hình upgrade
        vm.upgrade=function(id,type){
        	var data={
            		type:1,
            		handOverId:id
            	}
        	tscdConstantService.setTempData(data);
        	 CommonService.goToMenu("Asset_NangCap_TS_KhongQuaXayLap",{});
        };
        vm.viewHandOverDetail=function(code){
        	var data={
        		type:1,
        		handOverCode:code
        	}
        	tscdConstantService.setTempData(data);
        	CommonService.goToMenu("Asset_View_BienbanbanGiao_KoQuaXayLap",{});
        };

        vm.columns=[
            //startCloumnHere
            {
                title: "STT",
                //template: rowItem => vm.grid.dataSource.indexOf(rowItem) + 1,
                template: rowItem => (vm.grid.dataSource.page()-1)*vm.grid.dataSource.pageSize()+ vm.grid.dataSource.indexOf(rowItem) + 1,
                width: 15,
                headerAttributes: {
                    style: "text-align:center;"
                },
                attributes: {
                    style: "text-align:center;"
                }
            }, {
                title: "Thao tác",
                template: '<div class="text-center">'+
                
                '</button><button type="button"'+
                'class="btn btn-default padding-button box-shadow  #=id#"'+
                'ng-click=vm.upgrade(#=id#,#=type#)>'+
                '<div class="action-button add" uib-tooltip="Tạo tài sản" translate>&nbsp;&nbsp;&nbsp;&nbsp;</div>'+
                '</button></div>',
                headerAttributes: {
                    style: "text-align:center;"
                },
                width:30

            },
            {
                title: "Mã biên bản bàn giao",
                field: "handoverCode",
                template:'<div class="text-left"><a link="javascript;;" ng-click=vm.viewHandOverDetail("#=handoverCode#")>#=handoverCode#</a></div>',
                headerAttributes: {
                    style: "text-align:center;"
                },
                attributes: {style: "text-align:left;"},
                type: "string",
                sortable: false,
                filterable: false,
                columnMenu: false,
                width: 50
            },
            {
                title: "Ngày bàn giao",
                field: "handoverDate",
                headerAttributes: {
                    style: "text-align:center;"
                },
                attributes: {style: "text-align:center;"},
                type: "string",
                sortable: false,
               
                filterable: false,
                columnMenu: false,
                width: 30
            },            
            {
                title: 'Người nhận',
                field: "receiverName",
                headerAttributes: {
                    style: "text-align:center;"
                },
                attributes: {style: "text-align:left;"},
                type: "string",
                sortable: false,
                filterable: false,
                columnMenu: false,
                width: 40
            },
            {
                title: 'Đơn vị nhận',
                field: "deliveryGroupName",
                headerAttributes: {
                    style: "text-align:center;"
                },
                attributes: {style: "text-align:left;"},
                type: "string",
                sortable: false,
                filterable: false,
                columnMenu: false,
                width: 65
            }
        ];
        
        vm.selectedIds=[{groupId:12002646,name:'An Giang'},{groupId:12522315,name:'Nhóm 2'}];
        
        vm.selectOptions = {
                placeholder: "Chọn đơn vị...",
                dataTextField: "name",
                dataValueField: "groupId",
                valuePrimitive: false,
                autoBind: false,
                pageSize:10,
                dataSource: {
                    serverFiltering: true,
                    schema: {
                        data: function(response) {                        	
                        	var result=[];
                        	for(var i=response.length-1;i>=0;i--){   
                        		for(var j=0;j<vm.selectedIds.length;j++){
	                        		if(vm.selectedIds[j].groupId==(response[i].groupId)){	                        			
	                        			response.splice(i, 1);
	                        			break;
	                        		}
                        		}
                        	}    
                            return response; 
                          }
                        },
                    transport: {
                     read: {                    	 
                            url: Constant.BASE_SERVICE_URL+"assetReportServiceRest/filterSysGroup",
                            contentType: "application/json; charset=utf-8",
							type: "POST"
                        },                        
				       
						parameterMap: function (options, type) {
				              //  vm.criteria.employeeId = Constant.user.srvUser.catEmployeeId;
							
								vm.search={};
								vm.search.pageSize= vm.selectOptions.pageSize;
								//vm.search.ids=vm.selectedIds;
								if(options.filter){
									if(options.filter.filters.length>0){
										//vm.search.filter=options.filter.filters[0];
										vm.search.keySearch=options.filter.filters[0].value;
									}
								}
				
								return JSON.stringify(vm.search)
				
						}
                    }
                }
            };
        //Multiple Checkbox
        vm.selectedIdMultipleFromClient=[1];
        vm.multiSelectBindFromClient={
        		 placeholder: "Chọn giá trị...",
                 dataTextField: "name",
                 dataValueField: "groupId",
                 valuePrimitive: true,
                 change:function(e,pramts,atest){
                     var filter_ = { logic: "or", filters: [ ] };
                     var values = this.value();
                     for(var i=0;i<values.length;i++){
                         filter_.filters.push({ field: "groupId", operator: "neq", value: values[i] });
                     }
                     this.dataSource.query({ filter: filter_ });
                 	console.log(e);
                 },
                 pageSize:10,
                 dataSource: {
                     serverFiltering: false,
                     schema: {
                         data: function(response) {                        	
                         	var result=[];
                         	for(var i=response.length-1;i>=0;i--){   
                         		for(var j=0;j<vm.selectedIds.length;j++){
 	                        		if(vm.selectedIds[j].groupId==(response[i].groupId)){	                        			
 	                        			response.splice(i, 1);
 	                        			break;
 	                        		}
                         		}
                         	}    
                             return response; 
                           }
                         },
                     transport: {
                      read: {                    	 
                             url: Constant.BASE_SERVICE_URL+"assetReportServiceRest/filterSysGroup",
                             contentType: "application/json; charset=utf-8",
 							type: "POST"
                         },                        
 				       
 						parameterMap: function (options, type) {
 				              //  vm.criteria.employeeId = Constant.user.srvUser.catEmployeeId;
 							
 								vm.search={};
 								vm.search.pageSize= 100;
 								//vm.search.ids=vm.selectedIds;
 								if(options.filter){
 									if(options.filter.filters.length>0){
 										//vm.search.filter=options.filter.filters[0];
 										vm.search.keySearch=options.filter.filters[0].value;
 									}
 								}
 				
 								return JSON.stringify(vm.search)
 				
 						}
                     }
                 }
                 
        };
        vm.multiSelectBindFromServerFilterFromClient={
       		 placeholder: "Chọn giá trị...",
                dataTextField: "name",
                dataValueField: "value",
                valuePrimitive: true,                 
                pageSize:10,
                dataSource:{
                	data:[
                	      { name: "Item1", value: "1" },
                	      { name: "Item2", value: "2" }
                	      ],
                	
                },
                change:function(e,pramts,atest){
                    var filter_ = { logic: "or", filters: [ ] };
                    var values = this.value();
                    for(var i=0;i<values.length;i++){
                        filter_.filters.push({ field: "value", operator: "neq", value: values[i] });
                    }
                    this.dataSource.query({ filter: filter_ });
                	console.log(e);
                },

                
       };
        


        vm.gridOptions = kendoConfig.getGridOptions({
            autoBind: true,
            dataSource: {
                serverPaging: true,
                schema: {
                    data: function (data) {
                        return data.rows;
                    },
                    total: function (data) {
                        return data.totalRow;
                    }
                },
                transport:{
                    read:function (e) {
                        var pagingParam={
                            size: e.data.pageSize,
                            page:e.data.page
                        };
                        var postParam=angular.copy(vm.searchForm);
                        console.log(postParam);
                        postParam.size=pagingParam.size;
                        postParam.page=pagingParam.page;
                        postParam.fromHandoverDate= kendo.toString(vm.searchForm.fromHandoverDate,'yyyy-MM-dd');
                        postParam.toHandoverDate=kendo.toString(vm.searchForm.toHandoverDate,'yyyy-MM-dd');
                        postParam.handoverCode=vm.searchForm.handoverCode;
                        //Thuc hien viec goi service
                        Restangular.all("longTermAssetServiceRest/" + 'searchHandOverUpgradeConstr').post(postParam,pagingParam).then(function (response) {
                            console.log(response);
                            e.success(response);
                        }).catch(function (err) {
                            if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                                toastr.warning(CommonService.translate(err.data.errorMessage));
                            }
                            e.success([]);
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err);
                        });;



                    },
                    parameterMap: function (options, type) {
                        vm.searchForm.page = options.page
                        vm.searchForm.pageSize = options.pageSize
                        return JSON.stringify(vm.searchForm)

                    }
                },
                pageSize: 20

            },
            noRecords: true,
            messages: {
                noRecords: CommonService.translate("Không có bản ghi nào trong danh sách")
            },
            pageable: {
                refresh: true,
                pageSizes: [10, 15, 20, 25],
                messages: {
                    display: " {0}-{1} của {2} kết quả",
                    itemsPerPage: "kết quả/trang",
                    	empty: CommonService.translate("Không có bản ghi")
                }
            },
            columns: vm.columns
        });

        vm.doInit();
        /**
         * Upload
         */
        vm.onSelect = function(e) {
            var message = $.map(e.files, function(file) { return file.name; }).join(", ");
            console.log("event :: select (" + message + ")");
        }
        //vm.listFiles=[{"name":"test.jpg"}];
        vm.listFileTemplate=$scope.getTemplateBySelector("#fileTemplate");
        vm.onSucess=function(e){
        	   for (var i = 0; i < e.files.length; i++) {
        		   $scope.$apply(function(){        			   
	        		   	vm.listFiles.push(e.files[i]);
	        		   }
        		   );
        	   }
        	 //  $scope.setValueToModel();
        }
        vm.test="hello";
        vm.onUpload=function(e) {

            var xhr = e.XMLHttpRequest; 
            xhr.addEventListener("readystatechange", function(e) {
                if (xhr.readyState == 1 /* OPENED */) {
                    xhr.setRequestHeader('X-CSRF-TOKEN', readCookie('XSRF-TOKEN'));
                }
            });
            console.log("Upload :: " );
            //console.log("Upload :: " + getFileInfo(e));
        }

    });
})();