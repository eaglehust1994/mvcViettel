/**
 * Created by hanhls-pc on 12/13/2016.
 */
(function() {

    'use strict';
    var app = angular.module('MetronicApp');
    app.controller('TscdDashboardController',	function($scope, gettextCatalog,Restangular,Constant,CommonService){
        var vm = this;
        vm.countConstructionAcceptainStatusNotCreatedAsset="";
        vm.countMerHandOverStatusNotCreatedAsset="";

        
        vm.goToListHandover=function(){
        	CommonService.goToMenu("Asset_DS_BienbanbanGiao",{});
        	
        }

      
        vm.goToListHandoverByConstruction=function(){
        	CommonService.goToMenu("Asset_DS_BienbanbanGiao_QuaXayLap",{});
        	
        }
        vm.goToListHandoverNotByConstruction=function(){
        	CommonService.goToMenu("Asset_DS_BienbanbanGiao_KoQuaXayLap",{});
        	
        } 
        vm.ratioArray=[["Biên bản",50],["Danh sách",30]];

        vm.getNotifyMerNotCreateAsset =function(){

            Restangular.all("assetReportServiceRest/" + 'notifyMerNotCreateAsset').post().then(function (response) {                
                vm.countConstructionAcceptainStatusNotCreatedAsset=response.data[0];
                vm.countMerHandOverStatusNotCreatedAsset=response.data[1];
//                vm.ratioArray=[["Biên bản",vm.countConstructionAcceptainStatusNotCreatedAsset*1000],["Danh sách",vm.countMerHandOverStatusNotCreatedAsset]];
            }).catch(function (err) {
                if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                    toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                }
                console.log('Không thể kết nối để lấy dữ liệu: ' + err);
            });

        };
        vm.getNotifyMerNotCreateAsset();

    });

})();
