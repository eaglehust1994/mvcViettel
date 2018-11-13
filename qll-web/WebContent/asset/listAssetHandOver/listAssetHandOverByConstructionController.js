/**
 * Created by hanhls1 on 3/10/2017.
 */
(function() {

    'use strict';
    var app = angular.module('MetronicApp');
    app.controller('listAssetHandOverByConstruction',	function($scope,$rootScope, gettextCatalog,Restangular,Constant,
                                                    kendoConfig,CommonService){
        var vm=this;
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

        vm.columns=[
            //startCloumnHere
            {
                title: "STT",
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
                template: '<div class="text-center"><button   type="button"'+
                'class="btn btn-default padding-button box-shadow  #=id#"'+
                'ng-click=vm.createLta(#=id#,#=type#)>'+
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
                title: "Mã trạm",
                field: "stationCode",
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
                title: "Mã công trình",
                field: "constrCode",
                headerAttributes: {
                    style: "text-align:center;"
                },
                attributes: {style: "text-align:left;"},
                type: "string",
                sortable: false,
                filterable: false,
                columnMenu: false,
                width: 40
            }
        ];

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
                        postParam.size=pagingParam.size;
                        postParam.page=pagingParam.page;
                        
                        //Thuc hien viec goi service
                        Restangular.all("longTermAssetServiceRest/" + 'searchHandOverByConstruction').post(postParam,pagingParam).then(function (response) {
                            e.success(response);
                        }).catch(function (err) {
                            if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                                toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                            }
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
                noRecords: gettextCatalog.getString("Không tìm thấy bản ghi nào")
            },
            pageable: {
                refresh: true,
                pageSizes: [10, 15, 20, 25],
                messages: {
                    display: " {0}-{1} của {2} kết quả",
                    itemsPerPage: "kết quả/trang"

                }
            },
            columns: vm.columns
        });

        vm.doInit();


    });
})();