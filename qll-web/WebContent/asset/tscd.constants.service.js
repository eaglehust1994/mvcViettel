/*(function () {
	'use strict';*/
	
	angular.module('MetronicApp')
		.factory('tscdConstantService', [ function(){
			var config = {};
			config.lstNotAllowEditLtaStatus=[1,2];
			config.lstAllowSendErpLtaStatus=[0,3];
			
			config.isNotAllowEditLtaStatus=function(status){
				if(config.lstNotAllowEditLtaStatus.contains(status)){
					return true;
				}
				return false;
			}
			config.isAllowSendErpLtaStatus=function(status){
				if(config.lstAllowSendErpLtaStatus.contains(status)){
					return true;
				}
				return false;
			}
			config.allId=-1;
			config.lstIsSentErpSearch=[
			                           {id:-1, name: "{{'Tất cả'|translate}}"},			                           
			                           {id: 2, name: "{{'Đã duyệt'|translate}}"},
			        		           {id: 1, name: "{{'Đã gửi tài chính'|translate}}"},
			        		           {id: 3, name: "{{'Từ chối duyệt'|translate}}"},
			        		           {id: 0, name: "{{'Chưa gửi tài chính'|translate}}"}
			        		           ]
				/*
				 * Day thong tin handOverCode vao
				 */
				config.tempData={};
				config.setTempData=function(data){
					config.tempData=data;
				}
				config.getTempData=function(){
					return config.tempData;
				}
				config.rest={
					url:{
						merHandOverInfoServiceUrl:"merHandOverInfoServiceRest",
						longTermAssetServiceUrl:""
					}
				};
			return config;
			}
			
	]);
	
/*})();*/
