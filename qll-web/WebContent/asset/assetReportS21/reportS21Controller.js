/**
 * Created by hanhls-pc on 12/13/2016.
 */
(function() {

    'use strict';
    var app = angular.module('MetronicApp');
    app.controller('reportS21Controller',	function($scope, gettextCatalog,Restangular,Constant,CommonService,$filter){
        var vm = this;
        vm.search={};
        vm.initForm=function(){
            var date=new Date();
            vm.search.toDate=new Date();
            date.setDate(1);
            date.setMonth(date.getMonth()-2);
            vm.search.fromDate=date;
            if(vm.search.fromDate.getYear()!=vm.search.toDate.getYear()){
                //Nếu khác ngày thì thực hiện câp nhật ngày
                date.setDate(1);
                date.setMonth(0);
                date.setYear(vm.search.toDate.getUTCFullYear());
                vm.search.fromDate= date;
            }
            //Ngay thang bao cao tong hop tai san co dinh theo don vi
            date = new Date();
            date.setDate(1);
            date.setMonth(date.getMonth()-1);
            vm.search.fromDateReportTSCD=date;
        }
        vm.initForm();
        //auto complete phong ban
        vm.sysGroupOptions = {
            dataTextField: "name",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.search.groupId = dataItem.groupId; // thành id
                vm.search.groupCode = dataItem.groupCode;//thành name
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.search.groupCode,pageSize:vm.sysGroupOptions.pageSize }).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            //template:'<div class="row" ><div class="col-xs-3" style="padding: 0px">#: data.groupCode #</div><div class="col-xs-9" style="padding: 0px"> #: data.name #</div></div>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.groupCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                    vm.search.groupId = null; // thành id
                    vm.search.groupName = null;//thành name
                }
            },
            ignoreCase: false
        };

        //auto complete loai tai san
        vm.catAssetCodeOptions = {
            dataTextField: "caacFullCode",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.search.catAssetCodeId = dataItem.catAssetCodeId; // thành id
                vm.search.caacFulllCode = dataItem.caacFulllCode;//thành name
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("catAssetCodeServiceRest;/" + 'filterCatAssetCodeType').post({keySearch:vm.search.caacCode,pageSize:vm.catAssetCodeOptions.pageSize }).then(function(response){
                            console.log(response)
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            //template:'<div class="clearfix"><div class="row" ><div class="col-xs-3" style="padding: 0px">#: data.caacFullCode #</div><div class="col-xs-9" style="padding: 0px"> #: data.caacName #</div> </div></di>',
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.caacFullCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.caacName #</div> </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                    vm.search.groupId = null; // thành id
                    vm.search.groupName = null;//thành name
                }
            },
            ignoreCase: false
        };
        //auto complete tai san
        vm.longTermAssetOptions = {
            dataTextField: "lotaCode",
            select: function(e) {
                var dataItem = this.dataItem(e.item.index());
                vm.search.longTermAssetId = dataItem.longTermAssetId; // thành id
                vm.search.lotaCode = dataItem.lotaCode;//thành name
            },
            pageSize: 10,
            dataSource: {
                serverFiltering: true,
                transport: {
                    read: function(options) {
                        return Restangular.all("assetReportServiceRest/" + 'filterLongTermAsset').post({keySearch:vm.search.lotaCode,pageSize:vm.longTermAssetOptions.pageSize }).then(function(response){
                            options.success(response);
                        }).catch(function (err) {
                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
                        });
                    }
                }
            },
            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.lotaCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.caacName #</div> </div>',
            //template:'<div style="height: auto;overflow:hidden;" ><div class="" style="float:left;width:140px;overflow: hidden">#: data.lotaCode #</div><div class="" style="padding-left:2px;width:auto;overflow: hidden"> #: data.caacName #</div>  </div>',
            change: function(e) {
                if (e.sender.value() === '') {
                    vm.search.groupId = null; // thành id
                    vm.search.groupName = null;//thành name
                }
            },
            ignoreCase: false
        };
        vm.validate=function(){
            if(vm.search.groupId==null|| vm.search.groupId==0){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn đơn vị'));
                return false;
            }
            if(vm.search.catAssetCodeId==null|| vm.search.catAssetCodeId==0){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn loại tài sản'));
                return false;
            }
            if(vm.search.fromDate==null|| vm.search.fromDate==''){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn ngày từ'));
                return false;
            }
           // var fromDate=stringToDateKendo(vm.search.fromDate);
            var fromDate=(vm.search.fromDate);
            if(vm.search.toDate==null|| vm.search.toDate==''){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn ngày đến'));
                return false;
            }
            //var toDate=stringToDateKendo(vm.search.toDate);
            var toDate=(vm.search.toDate);
            if(fromDate>toDate){
                toastr.warning(gettextCatalog.getString('Từ ngày không được lớn hơn đến ngày'));
                return false;
            }
         /*   if(vm.search.toDate.substr(6)!=vm.search.toDate.substr(6)){
                toastr.warning(gettextCatalog.getString('Chỉ xuất được báo cáo trong 1 năm'));
                return false;
            }*/
                if(vm.search.fromDate.getYear()!=vm.search.toDate.getYear()){
                toastr.warning(gettextCatalog.getString('Chỉ xuất được báo cáo trong 1 năm'));
                return false;
            }

            return true;
        }


        vm.doExportS21 =function(){
            if(vm.validate()) {
                //vm.search.fromDate= kendo.toString(vm.search.fromDate,'dd/MM/yyyy');
                //vm.search.toDate=kendo.toString(vm.search.toDate,'dd/MM/yyyy');
                var postParam={};//vm.cloneObject(vm.search);
                angular.copy(vm.search,postParam);
                postParam.fromDate= kendo.toString(vm.search.fromDate,'dd/MM/yyyy');
                postParam.toDate=kendo.toString(vm.search.toDate,'dd/MM/yyyy');
                Restangular.all("assetReportServiceRest/" + 'exportS21').post(postParam).then(function (response) {
                    var link = document.createElement('a');
                    link.download = 'exportS21.docx';
                    link.href = 'data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,' + response.data;
                    document.body.appendChild(link);
                    link.click();
                }).catch(function (err) {
                    if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                        toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                    }
                    console.log('Không thể kết nối để lấy dữ liệu: ' + err);
                });
            }
        };
        vm.doExportS22 =function(){
            if(vm.validate()){
                var postParam={};//vm.cloneObject(vm.search);
                angular.copy(vm.search,postParam);
                postParam.fromDate= kendo.toString(vm.search.fromDate,'dd/MM/yyyy');
                postParam.toDate=kendo.toString(vm.search.toDate,'dd/MM/yyyy');
                Restangular.all("assetReportServiceRest/" + 'exportS22').post(postParam).then(function(response){
                    var link = document.createElement('a');
                    link.download = 'exportS22.docx';
                    link.href = 'data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,' + response.data;
                    document.body.appendChild(link);
                    link.click();
                }).catch(function(err){
                    if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                        toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                    }
                    console.log('Không thể kết nối để lấy dữ liệu: ' + err);
                });
            }
        };
        vm.validateS23Report=function(){

            if(vm.search.groupId==null|| vm.search.groupId==0){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn đơn vị'));
                return false;
            }
            if(vm.search.longTermAssetId==null|| vm.search.longTermAssetId==0){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn  tài sản'));
                return false;
            }
            if(vm.search.fromDate==null|| vm.search.fromDate==''){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn ngày từ'));
                return false;
            }
            // var fromDate=stringToDateKendo(vm.search.fromDate);
            var fromDate=(vm.search.fromDate);
            if(vm.search.toDate==null|| vm.search.toDate==''){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn ngày đến'));
                return false;
            }
            //var toDate=stringToDateKendo(vm.search.toDate);
            var toDate=(vm.search.toDate);
            if(fromDate>toDate){
                toastr.warning(gettextCatalog.getString('Từ ngày không được lớn hơn đến ngày'));
                return false;
            }
            /*   if(vm.search.toDate.substr(6)!=vm.search.toDate.substr(6)){
             toastr.warning(gettextCatalog.getString('Chỉ xuất được báo cáo trong 1 năm'));
             return false;
             }*/
            if(vm.search.fromDate.getYear()!=vm.search.toDate.getYear()){
                toastr.warning(gettextCatalog.getString('Chỉ xuất được báo cáo trong 1 năm'));
                return false;
            }

            return true;
        }
        vm.doExportS23 =function(){
            if(vm.validateS23Report()){
                var postParam={};//vm.cloneObject(vm.search);
                angular.copy(vm.search,postParam);
                postParam.fromDate= kendo.toString(vm.search.fromDate,'dd/MM/yyyy');
                postParam.toDate=kendo.toString(vm.search.toDate,'dd/MM/yyyy');
                Restangular.all("assetReportServiceRest/" + 'exportS23').post(postParam).then(function(response){
                	if(response.status==200){
	                    var link = document.createElement('a');
	                    link.download = 'exportS23.docx';
	                    link.href = 'data:application/vnd.ms-excel;base64,' + response.data;
	                    document.body.appendChild(link);
	                    link.click();
	                }else if(response.status==400){//loi nghiep vu	                	
	                	toastr.warning(CommonService.translate(response.errorMessage));
	                }
                }).catch(function(err){
                    if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                        toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                    }
                    console.log('Không thể kết nối để lấy dữ liệu: ' + err);
                });
            }
        };
        vm.doExportBaoCaoTSCoDinhTheoDonvi=function(){
            if(vm.validateBaoCaoTSCoDinhTheoDonvi()){
                var postParam={};//vm.cloneObject(vm.search);
                angular.copy(vm.search,postParam);
                postParam.fromDate= kendo.toString(vm.search.fromDateReportTSCD,'dd/MM/yyyy');
                Restangular.all("assetReportServiceRest/" + 'exportAssetBaseDonVi').post(postParam).then(function(response){
                    var link = document.createElement('a');
                    link.download = 'rp_tang_giamTs_trong_ky.xlsx';
                    link.href = 'data:application/vnd.ms-excel;base64,' + response.data;
                    document.body.appendChild(link);
                    link.click();
                }).catch(function(err){
                    if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                        toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                    }
                    console.log('Không thể kết nối để lấy dữ liệu: ' + err);
                });
            }
        };
        vm.validateBaoCaoTSCoDinhTheoDonvi=function(){
            if(vm.search.groupId==null|| vm.search.groupId==0){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn đơn vị'));
                return false;
            }

            if(vm.search.fromDateReportTSCD==null|| vm.search.fromDateReportTSCD==''){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn kỳ báo cáo'));
                return false;
            }
            // var fromDate=stringToDateKendo(vm.search.fromDate);
            var fromDate=(vm.search.fromDateReportTSCD);
            if(fromDate.getUTCFullYear()<=2016 ){
                toastr.warning(gettextCatalog.getString('Kỳ báo cáo phải lớn hơn năm 2016'));
                return false;
            }


            return true;
        }

        vm.doExportBaoCaoTSCoDinhAllDonvi=function(){
            if(vm.validateBaoCaoTSCoDinhAllDonvi()){
                var postParam={};//vm.cloneObject(vm.search);
                angular.copy(vm.search,postParam);
                postParam.fromDate= kendo.toString(vm.search.fromDateReportTSCD,'dd/MM/yyyy');
                Restangular.all("assetReportServiceRest/" + 'exportAssetBaseAllDonVi').post(postParam).then(function(response){
                    var link = document.createElement('a');
                    link.download = 'rp_tang_giamTs_theo_donvi.xlsx';
                    link.href = 'data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,' + response.data;
                    document.body.appendChild(link);
                    link.click();
                }).catch(function(err){
                    if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
                        toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                    }
                    console.log('Không thể kết nối để lấy dữ liệu: ' + err);
                });
            }
        };
        vm.validateBaoCaoTSCoDinhAllDonvi=function(){

            if(vm.search.fromDateReportTSCD==null|| vm.search.fromDateReportTSCD==''){
                toastr.warning(gettextCatalog.getString('Bạn phải chọn kỳ báo cáo'));
                return false;
            }
            // var fromDate=stringToDateKendo(vm.search.fromDate);
            var fromDate=(vm.search.fromDateReportTSCD);
            if(fromDate.getUTCFullYear()<=2016 ){
                toastr.warning(gettextCatalog.getString('Kỳ báo cáo phải lớn hơn năm 2016'));
                return false;
            }


            return true;
        }

    });

})();
