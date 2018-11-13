(function() {
	'use strict';
	var controllerId = 'catAssetDetailController';
	
	angular.module('MetronicApp').controller(controllerId, catAssetDetailController);
	
	function catAssetDetailController($scope, $rootScope, $timeout, $compile, gettextCatalog, kendoConfig, $kWindow, CommonService, PopupConst, Restangular, RestEndpoint, detailCatAssetService,Constant) {
		var vm = this;
		var callFromOtherForm=false;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		initFormData();
		$rootScope.$on('Asset_CatAssetDetail_init', function(event, args) {
			if(data.type==1){
				//Init tu man hinh bien ban ban giao
				console.debug("id bien ban la:"+args.data.value);
				//TODO: thuc hien bien ban
				vm.loadConstructionAcceptainInfo(args.data.value);
			}
			if(data.type==2){
				console.debug("id tai san la:"+args.value);
				//init tu man hinh xem, sua bien ban
			}
			// do what you want to do
		});
		if(!callFromOtherForm){
			// Thuc hien viec khoi tao form thong thuong
		}
		vm.loadConstructionAcceptainInfo =function(constructionAcceptainId){
			Restangular.one("longTermAssetServiceRest/getConstructionAcceptainById").get(constructionAcceptainId).then(function(successResponse){

			}, function(errorResponse){

			});
		}

		function initFormData() {
			vm.detail = {
				stationId: '',
				stationName: '',
				constructId: '',
				constructName: '',
				groupId: '',
				groupName: '',
				useGroupId: '',
				useGroupName: '',
				assetGroupId: '',
				assetGroupName: '',
				assetGroupCode: '',
				assetTypeId: '',
				assetTypeName: '',
				assetTypeCode: '',
				assetSourceId: '',
				assetSourceName: '',
				assetSourceCode: '',
				catAssetCodeId: '',
				assetNameName: '',
				assetNameCode: '',
				lotaCode: '',
				originalPrice: 0,
				handoverCode: '',
				voucherDate: '',
				createdSource: 1,
				lotaStatus: 0,
				depreciationStartDate: '',
				isSentErp: 0,
				
				lotaIndex: '',
				listAssetEntities: [],
				listAssetCost: [],
				longTermAssetId: 0,
				isActive: 1,
				lotaType: '',
				voucherType: 1,
				
				attachName: '',
				hasUpload: ''
			}
			
			vm.merEntityValue = 0;
			vm.dataMerEntity = [];
			vm.constructionAcceptanceValue = 0;
			vm.dataConstructionAcceptance = [];
			vm.totalCost = 0;
			vm.totalDis = 0;
			vm.lotaType = [{id: 1, name: 'TSCĐ'}, {id: 2, name: 'CCDC'}];
			vm.voucherType = [
				{id: 1, name: 'Xây dựng cơ bản'},
				{id: 2, name: 'Mua sắm'}
			];
			vm.createdSource = [
				{id: 1, name: 'Đầu tư xây dựng'},
				{id: 2, name: 'Khoa học công nghệ'},
				{id: 3, name: 'Chi phí sản xuất kinh doanh'},
				{id: 4, name: 'Quỹ phúc lợi'},
				{id: 5, name: 'Khác'}
			];
			
			vm.commonSearch = {
				keySearch: '',
				pageSize: '',
				level: '',
				parentId: ''
			};
			vm.searchConstruct = {
				keySearch: '',
				pageSize: '',
				level: '',
				parentId: ''
			};
			vm.useGroupSearch = {
				keySearch: '',
				pageSize: '',
				level: '',
				parentId: ''
			};
			
			vm.assetGroupSearch = {caacName: '', caacCode: '', caacLevel: 1};
			vm.assetTypeSearch = {caacName: '', caacCode: '', caacLevel: 2};
			vm.catAssetSourceSearch = {caacName: '', caacCode: '', caacLevel: 3};
			vm.catAssetCodeSearch = {caacName: '', caacCode: '', caacLevel: 4};
			
			vm.gridCommon = [{
				title: "Mã", 
				field: "constrtCode", 
				width: 120
			}, {
				title: "Tên", 
				field: "constrtName", 
				width: 120
			}];
			
			vm.files = [];
			var list = [];
			vm.folder = '';
			detailCatAssetService.getCatAssetFolder().then(function(data) {
				vm.folder = data.folder;
			}, function(errResponse){
				console.error('Error while getCatAssetFolder');
			});
			
			vm.notChange = true;
			vm.countRow = 1;
			vm.itemTemp = {};
			vm.tempStr = "";
		}
		
		 vm.sysGroupOptions = {
		            dataTextField: "name",
		            select: function(e) {
		                var dataItem = this.dataItem(e.item.index());
		                vm.detail.groupId = dataItem.groupId; // thành id
		                vm.detail.groupName = dataItem.groupCode;//thành name
		            },
		            pageSize: 10,
		            dataSource: {
		                serverFiltering: true,
		                transport: {
		                    read: function(options) {
		                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.detail.groupName,pageSize:vm.sysGroupOptions.pageSize }).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
		                }
		            },
		            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.groupCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		            change: function(e) {
		                if (e.sender.value() === '') {
		                    vm.detail.groupId = null; // thành id
		                    vm.detail.groupName = null;//thành name
		                }
		            },
		            ignoreCase: false
		        };
		 vm.sysUserGroupOptions = {
		            dataTextField: "name",
		            select: function(e) {
		                var dataItem = this.dataItem(e.item.index());
		                vm.detail.useGroupId = dataItem.groupId; // thành id
		                vm.detail.useGroupName = dataItem.groupCode;//thành name
		            },
		            pageSize: 10,
		            dataSource: {
		                serverFiltering: true,
		                transport: {
		                    read: function(options) {
		                        return Restangular.all("assetReportServiceRest/" + 'filterSysGroup').post({keySearch:vm.detail.useGroupName,pageSize:vm.sysUserGroupOptions.pageSize }).then(function(response){
		                            options.success(response);
		                        }).catch(function (err) {
		                            console.log('Không thể kết nối để lấy dữ liệu: ' + err.message);
		                        });
		                    }
		                }
		            },
		            template:'<div class="row" ><div class="col-xs-5" style="padding: 0px;float:left">#: data.groupCode #</div><div  style="padding-left: 5px;width:auto;overflow: hidden"> #: data.name #</div> </div>',
		            change: function(e) {
		                if (e.sender.value() === '') {
		                    vm.detail.useGroupId = null; // thành id
		                    vm.detail.useGroupName = null;//thành name
		                }
		            },
		            ignoreCase: false
		        };
		vm.cancel = cancel;
		function cancel(){
			if (vm.detail.longTermAssetId !== 0){
				vm.detail = vm.itemTemp;
			}
			else{
				vm.totalDis = 0;
				vm.detail = {
					stationId: '',
					stationName: '',
					constructId: '',
					constructName: '',
					groupId: '',
					groupName: '',
					useGroupId: '',
					useGroupName: '',
					assetGroupId: '',
					assetGroupName: '',
					assetGroupCode: '',
					assetTypeId: '',
					assetTypeName: '',
					assetTypeCode: '',
					assetSourceId: '',
					assetSourceName: '',
					assetSourceCode: '',
					catAssetCodeId: '',
					assetNameName: '',
					assetNameCode: '',
					lotaCode: '',
					originalPrice: 0,
					handoverCode: '',
					voucherDate: '',
					createdSource: '',
					lotaStatus: '',
					depreciationStartDate: '',
					
					lotaIndex: '',
					listAssetEntities: [],
					listAssetCost: [],
					longTermAssetId: 0,
					isActive: 1,
					lotaType: '',
					
					attachName: '',
					hasUpload: ''
				};
			}
			var str = "<tr>" +
				"<td class=\"text-center\">" + vm.countRow + "<input type=\"hidden\" class=\"id\" /></td>" +
				"<td>" +
					"<select class=\"type\" style=\"padding: 2.6px 5px; border: 1px solid #c2cad8; with: 100%;\">"+
						"<option value=\"3\">Chi phí tư vấn đầu tư xây dựng</option>" +
						"<option value=\"4\">Chi phí QLDA</option>" +
						"<option value=\"5\">Chi phí bồi thường, hỗ trợ, tái định cư</option>" +
						"<option value=\"6\">Chi phí khác</option>" +
					"</select>" +
				"</td>" +
				"<td>" +
					"<input type=\"text\" class=\"form-control value text-right\" onkeypress=\"return event.charCode >= 48 && event.charCode <= 57\" onchange=\"changeCurrency(this)\" />" +
				"</td>" +
				"<td>" +
					"<input type=\"text\" class=\"form-control code\" maxlength=\"50\" />" +
				"</td>" +
				"<td>" +
					"<input class=\"form-control date\" kendo-date-picker data-date-msg=\"Ngày không đúng định dạng!\" name=\"inputDate" + vm.countRow + "\" k-format=\"'dd/MM/yyyy'\" />" +
					"<span data-for=\"inputDate" + vm.countRow + "\" class=\"k-invalid-msg\"></span>" +
					"<script>" +
						"$(function() {" +
							"var inputDate = $(\"input[name='inputDate" + vm.countRow + "']\");" +
							"inputDate.kendoMaskedTextBox({" +
								"mask : \"00/00/0000\"" +
							"});" +
							"inputDate.kendoDatePicker({" +
								"format : \"dd/MM/yyyy\"" +
							"});" +
							"inputDate.closest(\".k-datepicker\").add(inputDate).removeClass(\"k-textbox\");" +
						"});" +
					"</script>" +
				"</td>" +
				"<td>" +
					"<div class=\"input-group\">" +
						"<input type=\"file\" accept=\".doc, .xls, .pdf\" class=\"file\" style=\"display: none;\" onchange=\"angular.element(this).scope().changeDisFile(this)\" />" +
						"<input type=\"text\" class=\"form-control\" placeholder=\"Chọn file...\" readonly onclick=\"angular.element(this).scope().txtChoseDisFile(this)\" />" +
						"<span class=\"input-group-btn\">" +
							"<button class=\"btn btn-default btn-sm\" type=\"button\" style=\"padding-bottom: 3px; padding-top: 2px;\" onclick=\"angular.element(this).scope().choseDisFile(this)\">Browser</button>" +
						"</span>" +
					"</div>" +
				"</td>" +
				"<td class=\"text-center\" style=\"width: 80px;\">" +
					"<div class=\"btn-group\">" +
						"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"addDisRow(this);\">" +
							"<span class=\"glyphicon glyphicon-plus\"></span>" +
						"</button>" +
						"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"removeDisRow(this);\">" +
							"<span class=\"glyphicon glyphicon-remove\"></span>" +
						"</button>" +
					"</div>" +
				"</td>" +
			"</tr>";
			var compiledeHTML = $compile(str)($scope);
			$("#tblDistribution tbody").html(compiledeHTML)
			var compiledeTempHTML = $compile(vm.tempStr)($scope);
			$("#tblDistribution tbody tr:last").before(compiledeTempHTML);
			caculateCost();
		}
		
		vm.save = save;
		function save(){
			if (!vm.validator.validate()) {
				if ($("#filePath").val() != ""){
					$("#detailAssetForm #btnSave").css("pointer-events", "none");
					
					// Thong tin cho bang LONG_TERM_ASSET_ENTITIES 
					vm.detail.listAssetCost = [];
					vm.detail.listAssetEntities = vm.dataMerEntity;
					
					// Thong tin cho bang LONG_TERM_ASSET_COSTS va LONG_TERM_ASSET_VOUCHERS 
					// Them chi phi vat tu thiet bi vao
					var objMerEntity = new Object();
					objMerEntity.loacType = 1;
					objMerEntity.loacValue = vm.merEntityValue.toString().replaceAll(".", "");
					objMerEntity.voucherCode = '';
					objMerEntity.voucherDate = '';
					vm.detail.listAssetCost.push(objMerEntity);
					
					// Them chi phi xay lap vao
					var objConstructionAcceptance = new Object();
					objConstructionAcceptance.loacType = 2;
					objConstructionAcceptance.loacValue = vm.constructionAcceptanceValue.toString().replaceAll(".", "");
					objConstructionAcceptance.voucherCode = '';
					objConstructionAcceptance.voucherDate = '';
					vm.detail.listAssetCost.push(objConstructionAcceptance);
					
					// Them thong tin nguoi dung nhap tu bang phan bo vao
					var disItem = $("#tblDistribution tbody tr");
					for (var i = 0; i < disItem.length; i++) {
						if ($(disItem[i]).find(".value").val() != "" && $(disItem[i]).find(".value").val() != "0"){
							if (typeof $(disItem[i]).find(".file")[0].files[0] !== "undefined" && $(disItem[i]).find(".file")[0].files[0].name != null && $(disItem[i]).find(".file")[0].files[0].name !== ""){
								var fileUpload = $(disItem[i]).find(".file")[0].files[0];
								var filesize = ((fileUpload.size/1024)/1024).toFixed(4);
								
								if (filesize > 20){
									toastr.error(gettextCatalog.getString("File " + fileUpload.name + " có dung lượng quá lớn!"));
									return;
								}
							}
							else{
								if ($(disItem[i]).find(".att").val() == ""){
									toastr.error(gettextCatalog.getString("Không được để trống 'File đính kèm' tại Danh sách chi phí phân bổ!"));
									return;
								}
							}
						}
					}
					for (var i = 0; i < disItem.length; i++) {
						if ($(disItem[i]).find(".value").val() != "" && $(disItem[i]).find(".value").val() != "0"){
							var objAssetCost = new Object();
							objAssetCost.longTermAssetCostId = $(disItem[i]).find(".id").val();
							objAssetCost.loacType = $(disItem[i]).find(".type").val();
							objAssetCost.loacValue = $(disItem[i]).find(".value").val().replaceAll(".", "");
							objAssetCost.voucherCode = $(disItem[i]).find(".code").val();
							
							var datepicker = $(disItem[i]).find("input.date").data("kendoDatePicker");
							
							objAssetCost.voucherDate = kendo.toString(datepicker.value(), "dd/MM/yyyy");
							//objAssetCost.attachName = $(disItem[i]).find(".att").val();
							objAssetCost.attachName =$(disItem[i]).find(".att").attr("fileEncryptedPath");
							if(objAssetCost.attachName!=null && !""==objAssetCost.attachName.trim()){
								objAssetCost.hasUpload = 'Y';
							}
							
//							if (typeof $(disItem[i]).find(".file")[0].files[0] !== "undefined" && $(disItem[i]).find(".file")[0].files[0].name != null && $(disItem[i]).find(".file")[0].files[0].name !== ""){
//								var fileData = new FormData();
//								var fileUpload = $(disItem[i]).find(".file")[0].files[0];
//								fileData.append('fileUpload', fileUpload);
//								$.ajax({
//									url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.LONG_TERM_ASSET_URL + "/upload?folder=" + vm.folder, //"/qlhc-service/fileservice/upload?folder="+ vm.folder,
//									type: "POST",
//									data: fileData,
//									enctype: 'multipart/form-data',
//									processData: false,
//									contentType: false,
//									cache: false,
//									success: function() {
//										console.log("Upload successfully!");
//									},
//									error: function() {
//										
//									}
//								});
//								
//								objAssetCost.attachName = fileUpload.name;
//								objAssetCost.hasUpload = 'Y';
//							}
							vm.detail.listAssetCost.push(objAssetCost);
						}
					}
					// Thong tin cho bang LONG_TERM_ASSET
					// Lay thong tin file upload
					//var fileName = $($('input[name="fileBBBG"]')[0]).val().split('\\').pop();
//					if (typeof fileName != undefined && fileName != null && fileName !== ""){
//						
//						var assetUpload = $("#fileBBBG")[0].files[0];
//						var formData = new FormData();
//						formData.append('assetUpload', assetUpload);
//						
//						$.ajax({
//							url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.LONG_TERM_ASSET_URL + "/upload?folder=" + vm.folder, //"/qlhc-service/fileservice/upload?folder="+ vm.folder,
//							type: "POST",
//							data: formData,
//							enctype: 'multipart/form-data',
//							processData: false,
//							contentType: false,
//							cache: false,
//							success: function() {
//								console.log("Upload successfully!");
//							},
//							error: function() {
//								
//							}
//						});
//						
//						vm.detail.attachName = assetUpload.name;
//						vm.detail.hasUpload = 'Y';
//					}
					vm.detail.lotaStatus = $("#detailAssetForm input[name='lotaStatus']:checked").val();
					if (parseFloat(vm.detail.originalPrice.toString().replaceAll(".", "")) > 30000000){
						vm.detail.lotaType = 1;
					}
					else{
						vm.detail.lotaType = 2;
					}
					//vm.detail.depreciationStartDate = chanceDateToString($("#depreciationStartDate").val(), 'DATE_TO_DATE2');
					vm.detail.originalPrice = vm.detail.originalPrice.toString().replaceAll(".", "");
				
					if (!$rootScope.isCreatAsset){
						// Sua thong tin
						detailCatAssetService.updateCatAsset(vm.detail).then(function(result){
							toastr.success(gettextCatalog.getString("Lưu thay đổi thành công!"));
							$("#detailAssetForm #btnSave").removeAttr('style');
							vm.detail.originalPrice = convertStringToCurrency(vm.detail.originalPrice, false, false);
						}, function(errResponse) {
							$("#detailAssetForm #btnSave").removeAttr('style');
							toastr.error(gettextCatalog.getString("Lỗi xuất hiện!"));
						});
					}
					else{
						// Them moi thong tin
						detailCatAssetService.addCatAsset(vm.detail).then(function(result){
							$rootScope.isCreatAsset = false;
							toastr.success(gettextCatalog.getString("Lưu thay đổi thành công!"));
							vm.detail.longTermAssetId = result.longTermAssetId;
							$("#detailAssetForm #btnSave").removeAttr('style');
							vm.detail.originalPrice = convertStringToCurrency(vm.detail.originalPrice, false, false);
						}, function(errResponse) {
							$("#detailAssetForm #btnSave").removeAttr('style');
							toastr.error(gettextCatalog.getString("Lỗi xuất hiện!"));
						});
					}
					
				}
				else{
					toastr.error(gettextCatalog.getString("File đình kèm BBBG không được để trống"));
				}
			}
			else{
				$("#detailAssetForm #btnSave").removeAttr('style');
			}
		}
		
		vm.choseFile = choseFile;
		function choseFile(){
			$("#detailAssetForm #fileBBBG").click();
		}
		
		$scope.changeFile = function(){
			if (typeof $("#detailAssetForm #fileBBBG")[0].files[0] !== "undefined"){
				$("#detailAssetForm #filePath").val($("#detailAssetForm #fileBBBG")[0].files[0].name);
				
				
				var formData = new FormData();
				var assetUpload = $("#fileBBBG")[0].files[0];
				formData.append('assetUpload', assetUpload);
				Restangular.one(Constant.UPLOAD_RS_SERVICE).withHttpConfig(
						{
							transformRequest : angular.identity
						}).customPOST(formData, '', undefined, {
					'Content-Type' : 'multipart/form-data'
				}).then(function(successResponse) {
					console.log(successResponse);
					if(successResponse.length>0){
						//vm.termassetentity.attachName = $("#changeBBBG")[0].files[0].name;
						vm.detail.attachName = $("#fileBBBG")[0].files[0].name;
						vm.detail.attachName=successResponse[0];
						//vm.detail.attachEncryptedPath =successResponse[0];
						vm.detail.hasUpload = 'Y';
					}
				});
			}
		}
		
		$scope.changeDisFile = function(element){
			if (typeof $(element)[0].files[0] !== "undefined"){
				var fileName=$(element)[0].files[0].name;				
				
				//begin upload
				var formData = new FormData();
				var assetUpload = $(element)[0].files[0];
				formData.append('assetUpload', assetUpload);
				Restangular.one(Constant.UPLOAD_RS_SERVICE).withHttpConfig(
						{
							transformRequest : angular.identity
						}).customPOST(formData, '', undefined, {
					'Content-Type' : 'multipart/form-data'
				}).then(function(successResponse) {					
					if(successResponse.length>0){
						//vm.termassetentity.attachName = $("#changeBBBG")[0].files[0].name;
					//	$(element).val(successResponse[0]);
						$(element).next().val(fileName);
						//đẩy file đã mã hóa
						$(element).next().attr('fileEncryptedPath',successResponse[0]);						
					}
				});
			}
		}
		
		$scope.choseDisFile = function(element){
			$(element).parent().prev().prev().click();
		}
		
		$scope.txtChoseDisFile = function(element){
			$(element).prev().click();
		}
		
		$scope.removeDisRow = function(element){
			var disItem = $("#tblDistribution tbody tr");
			if (disItem.length > 1){
				$(element).parent().parent().parent().remove();
				caculateCost();
			}
		}
		
		$scope.addDisRow = function(element){
			if ($(element).parent().parent().parent().find(".value").val() !== "" && $(element).parent().parent().parent().find(".att").val() !== ""){
				var count = $("#tblDistribution tbody tr").length + 1;
				var str = "<tr>" +
					"<td class=\"text-center\">" + count + "<input type=\"hidden\" class=\"id\" /></td>" +
					"<td>" +
						"<select class=\"type\" style=\"padding: 2.6px 5px; border: 1px solid #c2cad8; with: 100%;\">" +
							"<option value=\"3\">Chi phí tư vấn đầu tư xây dựng</option>" +
							"<option value=\"4\">Chi phí QLDA</option>" +
							"<option value=\"5\">Chi phí bồi thường, hỗ trợ, tái định cư</option>" +
							"<option value=\"6\">Chi phí khác</option>" +
						"</select>" +
					"</td>" +
					"<td>" +
						"<input type=\"text\" class=\"form-control value text-right\" onkeypress=\"return event.charCode >= 48 && event.charCode <= 57\" onchange=\"changeCurrency(this)\" />" +
					"</td>" +
					"<td>" +
						"<input type=\"text\" class=\"form-control code\" maxlength=\"50\" />" +
					"</td>" +
					"<td>" +
						"<input class=\"form-control date\" kendo-date-picker data-date-msg=\"Ngày không đúng định dạng!\" name=\"inputDate" + count + "\" k-format=\"'dd/MM/yyyy'\" />" +
						"<span data-for=\"inputDate" + count + "\" class=\"k-invalid-msg\"></span>" +
						"<script>" +
							"$(function() {" +
								"var inputDate = $(\"input[name='inputDate" + count + "']\");" +
								"inputDate.kendoMaskedTextBox({" +
									"mask : \"00/00/0000\"" +
								"});" +
								"inputDate.kendoDatePicker({" +
									"format : \"dd/MM/yyyy\"" +
								"});" +
								"inputDate.closest(\".k-datepicker\").add(inputDate).removeClass(\"k-textbox\");" +
							"});" +
						"</script>" +
						//"<input type=\"date\" class=\"form-control date\" />" +
					"</td>" +
					"<td>" +
						"<div class=\"input-group\">" +
							"<input type=\"file\" accept=\".doc, .xls, .pdf\" class=\"file\" style=\"display: none;\" onchange=\"angular.element(this).scope().changeDisFile(this)\" />" +
							"<input type=\"text\" class=\"form-control\" placeholder=\"Chọn file...\" readonly onclick=\"angular.element(this).scope().txtChoseDisFile(this)\" />" +
							"<span class=\"input-group-btn\">" +
								"<button class=\"btn btn-default btn-sm\" type=\"button\" style=\"padding-bottom: 3px; padding-top: 2px;\" onclick=\"angular.element(this).scope().choseDisFile(this)\">Browser</button>" +
							"</span>" +
						"</div>" +
					"</td>" +
					"<td class=\"text-center\" style=\"width: 80px;\">" +
						"<div class=\"btn-group\">" +
							"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"addDisRow(this);\">" +
								"<span class=\"glyphicon glyphicon-plus\"></span>" +
							"</button>" +
							"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"removeDisRow(this);\">" +
								"<span class=\"glyphicon glyphicon-remove\"></span>" +
							"</button>" +
						"</div>" +
					"</td>" +
				"</tr>";
				var compiledeHTML = $compile(str)($scope);
				$("#tblDistribution tbody").append(compiledeHTML);
			}
			else{
				toastr.warning(gettextCatalog.getString("Không được bỏ trống 'Giá trị' và 'File đính kèm'!"));
			}
		}
		
		vm.showPopup = showPopup;
		function showPopup(popupId){
			if (popupId === 'merEntity'){
				var templateUrl = 'asset/catAssetList/popup.html';
				var title = "Danh sách vật tư thiết bị";
				vm.merEntityGridOptions = kendoConfig.getGridOptions({
					autoBind: true,
					dataSource: vm.dataMerEntity,
					noRecords: true,
					messages: {
						noRecords: gettextCatalog.getString("There is no data on current page")
					},
					columns: [{
						title: gettextCatalog.getString("STT"),
						field: 'lineNo',
						width: 100,
					}, {
						title: gettextCatalog.getString("Mã"),
						field: "code",
						width: 100
					}, {
						title: gettextCatalog.getString("Tên"),
						field: "name",
						width: 150
					}, {
						title: gettextCatalog.getString("Serial Number"),
						field: "serialNumber",
						width: 150
					}, {
						title: gettextCatalog.getString("Đơn vị tính"),
						field: "unitId",
						width: 150
					}, {
						title: gettextCatalog.getString("Số lượng"),
						field: "merWeight",
						width: 150
					}, {
						title: gettextCatalog.getString("Đơn giá"),
						field: "vndUnitPrice",
						width: 150
					}, {
						title: gettextCatalog.getString("Thành tiền (VNĐ)"),
						field: "total",
						width: 150
					}]
				});
				CommonService.populateDataToGrid(templateUrl, title, vm.merEntityGridOptions, vm, 'merEntity');
			}
			else{
				var templateUrl = 'asset/catAssetList/popup.html';
				var title = "Danh sách chi phí xây lắp";
				vm.constructionAcceptance = kendoConfig.getGridOptions({
					autoBind: true,
					dataSource: vm.dataConstructionAcceptance,
					noRecords: true,
					messages: {
						noRecords: gettextCatalog.getString("There is no data on current page")
					},
					columns: [{
						title: gettextCatalog.getString("STT"),
						field: "lineNo",
						width: 50,
					}, {
						title: gettextCatalog.getString("Mã đầu việc"),
						field: "code",
						width: 100
					}, {
						title: gettextCatalog.getString("Tên đầu việc"),
						field: "name",
						width: 150
					}, {
						title: gettextCatalog.getString("Khối lượng"),
						field: "merWeight",
						width: 150
					}, {
						title: gettextCatalog.getString("Đơn giá"),
						field: "vndUnitPrice",
						width: 150
					}, {
						title: gettextCatalog.getString("Thành tiền (VNĐ)"),
						field: "total",
						width: 150
					}]
				});
				CommonService.populateDataToGrid(templateUrl, title, vm.constructionAcceptance, vm, 'constructionAcceptance');
			}
		}
		vm.onCancel = onCancel;
		function onCancel(){
			
		}
		
		$scope.$watch('vm.detail.assetGroupId',function(id){
			if (vm.detail.longTermAssetId !== 0){
				// Sua thong tin
				vm.assetTypeSearch.superParentId = id;
				
				if (!vm.notChange){
					vm.detail.assetTypeId = '';
					vm.detail.assetTypeName = '';
					vm.detail.assetSourceId = '';
					vm.detail.assetSourceName = '';
					vm.detail.catAssetCodeId = '';
					vm.detail.assetNameName = '';
					vm.detail.lotaIndex = '';
					vm.detail.lotaCode = '';
				}
				//vm.notChange = false;
			}
			else{
				vm.detail.assetTypeId = '';
				vm.detail.assetTypeName = '';
				vm.detail.assetSourceId = '';
				vm.detail.assetSourceName = '';
				vm.detail.catAssetCodeId = '';
				vm.detail.assetNameName = '';
				vm.detail.lotaIndex = '';
				vm.detail.lotaCode = '';
					
				vm.assetTypeSearch.superParentId = id;
			}
		});
		
		$scope.$watch('vm.detail.assetTypeId',function(id){
			if (vm.detail.longTermAssetId !== 0){
				// Sua thong tin
				vm.catAssetSourceSearch.superParentId = id;
				
				if (!vm.notChange){
					vm.detail.assetSourceId = '';
					vm.detail.assetSourceName = '';
					vm.detail.catAssetCodeId = '';
					vm.detail.assetNameName = '';
					vm.detail.lotaIndex = '';
					vm.detail.lotaCode = '';
				}
				//vm.notChange = false;
			}
			else{
				// Them moi
				vm.detail.assetSourceId = '';
				vm.detail.assetSourceName = '';
				vm.detail.catAssetCodeId = '';
				vm.detail.assetNameName = '';
				vm.detail.lotaIndex = '';
				vm.detail.lotaCode = '';
				
				vm.catAssetSourceSearch.superParentId = id;
			}
		});
		
		$scope.$watch('vm.detail.assetSourceId',function(id){
			if (vm.detail.longTermAssetId !== 0){
				// Sua thong tin
				vm.catAssetCodeSearch.superParentId = id;
				
				if (!vm.notChange){
					vm.detail.catAssetCodeId = '';
					vm.detail.assetNameName = '';
					vm.detail.lotaIndex = '';
					vm.detail.lotaCode = '';
				}
				//vm.notChange = false;
			}
			else{
				// Them moi
				vm.detail.catAssetCodeId = '';
				vm.detail.assetNameName = '';
				vm.detail.lotaIndex = '';
				vm.detail.lotaCode = '';
				
				vm.catAssetCodeSearch.superParentId = id;
			}
		});
		
		$scope.$watch('vm.detail.catAssetCodeId', function(id){
			if (vm.detail.longTermAssetId !== 0){
				// Sua thong tin
				if (!vm.notChange){
					if (typeof id !== "undefined" && id != null && id.toString() != ""){
						detailCatAssetService.genAssetCode({catAssetCodeId: id}).then(function(d) {
							vm.detail.lotaIndex = (d.plain().lotaIndex != null ? d.plain().lotaIndex : 30001);
							vm.detail.lotaCode = vm.detail.assetGroupCode + vm.detail.assetTypeCode + vm.detail.assetSourceCode + vm.detail.assetNameCode + (d.plain().lotaIndex != null ? d.plain().lotaIndex : 30001).toString();
							//vm.detail.lotaCode = vm.detail.assetGroupCode + "." + vm.detail.assetTypeCode + "." + vm.detail.assetSourceCode + "." + vm.detail.assetNameCode + "." + (d.plain().lotaIndex != null ? d.plain().lotaIndex : 30001).toString();
						}, function(errResponse){
							console.error('Error while genAssetCode');
						});
					}
					else{
						vm.detail.lotaIndex = '';
						vm.detail.lotaCode = '';
					}
				}
				//vm.notChange = false;
			}
			else{
				if (typeof id !== "undefined" && id != null && id.toString() != ""){
					detailCatAssetService.genAssetCode({catAssetCodeId: id}).then(function(d) {
						vm.detail.lotaIndex = (d.plain().lotaIndex != null ? d.plain().lotaIndex + 1 : 30001);
						vm.detail.lotaCode = vm.detail.assetGroupCode + vm.detail.assetTypeCode + vm.detail.assetSourceCode + vm.detail.assetNameCode + (d.plain().lotaIndex != null ? d.plain().lotaIndex + 1 : 30001).toString();
						//vm.detail.lotaCode = vm.detail.assetGroupCode + "." + vm.detail.assetTypeCode + "." + vm.detail.assetSourceCode + "." + vm.detail.assetNameCode + "." + (d.plain().lotaIndex != null ? d.plain().lotaIndex + 1 : 30001).toString();
					}, function(errResponse){
						console.error('Error while genAssetCode');
					});
				}
				else{
					vm.detail.lotaIndex = '';
					vm.detail.lotaCode = '';
				}
			}
		});
		
		$scope.$watch('vm.detail.constructId',function(id){
			if (typeof id !== "undefined" && id != null && id.toString() != ""){
				$("div.loading-effect").show();
				detailCatAssetService.getMerEntity({constructionId: id}).then(function(d) {
					vm.dataMerEntity = d.plain();
					for(var i = 0; i < vm.dataMerEntity.length; i++){
						var obj = vm.dataMerEntity[i];
						vm.merEntityValue += (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
						vm.dataMerEntity[i].total = (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
						vm.dataMerEntity[i].lineNo = i + 1;
					}
					vm.merEntityValue = convertStringToCurrency(vm.merEntityValue.toString(), false, false);
					detailCatAssetService.getConstructionAcceptance({constructionId: id}).then(function(d) {
						vm.dataConstructionAcceptance = d.plain();
						for(var j = 0; j < vm.dataConstructionAcceptance.length; j++){
							var obj = vm.dataConstructionAcceptance[j];
							vm.constructionAcceptanceValue += (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
							vm.dataConstructionAcceptance.total = (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
							vm.dataConstructionAcceptance[j].lineNo = j + 1;
						}
						vm.constructionAcceptanceValue = convertStringToCurrency(vm.constructionAcceptanceValue.toString(), false, false);
						vm.totalCost = convertStringToCurrency(parseFloat(vm.merEntityValue.toString().replaceAll(".", "")) + parseFloat(vm.constructionAcceptanceValue.toString().replaceAll(".", "")), false, false);
						
						vm.detail.originalPrice = convertStringToCurrency(parseFloat(vm.totalCost.toString().replaceAll(".", "")) + parseFloat(vm.totalDis.toString().replaceAll(".", "")), false, false);
						$("div.loading-effect").hide();
					}, function(errResponse){
						console.error('Error while getConstructionAcceptance');
						$("div.loading-effect").hide();
					});
				}, function(errResponse){
					console.error('Error while getMerEntity');
					$("div.loading-effect").hide();
				});
			}
			else{
				vm.merEntityValue = 0;
				vm.dataMerEntity = [];
				vm.constructionAcceptanceValue = 0;
				vm.dataConstructionAcceptance = [];
				vm.totalCost = 0;
			}
		});
		
		$scope.$watch('vm.detail.stationId',function(id){
			if (vm.detail.longTermAssetId !== 0){
				// Sua thong tin
				vm.searchConstruct.parentId = id;
				
				if (!vm.notChange){
					vm.detail.constructId = '';
					vm.detail.constructName = '';
				}
			}
			else{
				// Them moi
				vm.searchConstruct.parentId = id;
				vm.detail.constructId = '';
				vm.detail.constructName = '';
			}
		});
		
		function chanceDateToString(stringDate, mode) {
			// String to dd/MM/yyyy
			if (stringDate != undefined && mode == 'M_TO_DATE') {
				var date = new Date(stringDate);
				return (date.getDate() + '/' + (date.getMonth() === 0 ? 12 : date.getMonth()) + '/' + date.getFullYear());
			}
			// dd/MM/yyyy to yyyy-MM-dd
			if (stringDate != undefined && mode == 'DATE_TO_DATE') {
				var arrDate = stringDate.split("/");
				
				return arrDate[2] + "-" + arrDate[1] + "-" + arrDate[0];
			}
			// String to yyyy-MM-dd
			if (stringDate != undefined && mode == 'M2_TO_DATE') {
				var date = new Date(stringDate);
				return (date.getFullYear() + '-' + (date.getMonth() === 0 ? "01" : date.getMonth() + 1) + '-' + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()));
			}
			// dd/MM/yyyy to string
			if (stringDate != undefined && mode == 'DATE_TO_M') {
				var arrDate = stringDate.split("/");
				var date = new Date(arrDate[2], arrDate[1], arrDate[0], 0, 0, 0, 0);
				return date.getTime();
			}
			// yyyy-MM-dd to string
			if (stringDate != undefined && mode == 'DATE2_TO_M') {
				var arrDate = stringDate.split("-");
				var date = new Date(arrDate[0], arrDate[1], arrDate[2], 0, 0, 0, 0);
				return date.getTime();
			}
			// yyyy-MM-dd to dd/MM/yyyy
			if (stringDate != undefined && mode == 'DATE_TO_DATE2') {
				var arrDate = stringDate.split("-");
				return arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
			}
			if (stringDate != undefined && mode == 'DATEMONTH_TO_M') {  // chuyển từ ngày tháng năm giờ phút giấy sang millisecon
				var arrDate = stringDate.split(" ");
				var DdMMYYYY = arrDate[0].split("/");
				var HHMmSs = arrDate[1].split(":");
				var date = new Date(DdMMYYYY[2], DdMMYYYY[1], DdMMYYYY[0], HHMmSs[0], HHMmSs[1], HHMmSs[2], 0);
				return date.getTime();
			}
			
			if (stringDate != undefined && mode == 'M_TO_DATEMONTH') {
				var date = new Date(stringDate);
				return (date.getDate() + '/' + (date.getMonth() === 0 ? 12 : date.getMonth()) + '/' + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
			}
		}
		
		$scope.$watch($rootScope.isCreatAsset, function(){
			if (!$rootScope.isCreatAsset){
				var strFirst = "<tr>" +
					"<td class=\"text-center\">" + vm.countRow + "<input type=\"hidden\" class=\"id\" /></td>" +
					"<td>" +
						"<select class=\"type\" style=\"padding: 2.6px 5px; border: 1px solid #c2cad8; with: 100%;\">"+
							"<option value=\"3\">Chi phí tư vấn đầu tư xây dựng</option>" +
							"<option value=\"4\">Chi phí QLDA</option>" +
							"<option value=\"5\">Chi phí bồi thường, hỗ trợ, tái định cư</option>" +
							"<option value=\"6\">Chi phí khác</option>" +
						"</select>" +
					"</td>" +
					"<td>" +
						"<input type=\"text\" class=\"form-control value text-right\" onkeypress=\"return event.charCode >= 48 && event.charCode <= 57\" onchange=\"changeCurrency(this)\" />" +
					"</td>" +
					"<td>" +
						"<input type=\"text\" class=\"form-control code\" maxlength=\"50\" />" +
					"</td>" +
					"<td>" +
						"<input class=\"form-control date\" kendo-date-picker data-date-msg=\"Ngày không đúng định dạng!\" name=\"inputDate" + vm.countRow + "\" k-format=\"'dd/MM/yyyy'\" />" +
						"<span data-for=\"inputDate" + vm.countRow + "\" class=\"k-invalid-msg\"></span>" +
						"<script>" +
							"$(function() {" +
								"var inputDate = $(\"input[name='inputDate" + vm.countRow + "']\");" +
								"inputDate.kendoMaskedTextBox({" +
									"mask : \"00/00/0000\"" +
								"});" +
								"inputDate.kendoDatePicker({" +
									"format : \"dd/MM/yyyy\"" +
								"});" +
								"inputDate.closest(\".k-datepicker\").add(inputDate).removeClass(\"k-textbox\");" +
							"});" +
						"</script>" +
						//"<input type=\"date\" class=\"form-control date\" />" +
					"</td>" +
					"<td>" +
						"<div class=\"input-group\">" +
							"<input type=\"file\" accept=\".doc, .xls, .pdf\" class=\"file\" style=\"display: none;\" onchange=\"angular.element(this).scope().changeDisFile(this)\" />" +
							"<input type=\"text\" class=\"form-control att\" placeholder=\"Chọn file...\" readonly onclick=\"angular.element(this).scope().txtChoseDisFile(this)\" />" +
							"<span class=\"input-group-btn\">" +
								"<button class=\"btn btn-default btn-sm\" type=\"button\" style=\"padding-bottom: 3px; padding-top: 2px;\" onclick=\"angular.element(this).scope().choseDisFile(this)\">Browser</button>" +
							"</span>" +
						"</div>" +
					"</td>" +
					"<td class=\"text-center\" style=\"width: 80px;\">" +
						"<div class=\"btn-group\">" +
							"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"addDisRow(this);\">" +
								"<span class=\"glyphicon glyphicon-plus\"></span>" +
							"</button>" +
							"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"removeDisRow(this);\">" +
								"<span class=\"glyphicon glyphicon-remove\"></span>" +
							"</button>" +
						"</div>" +
					"</td>" +
				"</tr>";
				var compiledeFirstHTML = $compile(strFirst)($scope);
				$("#tblDistribution tbody").html(compiledeFirstHTML);
				
				vm.objSearch = {longTermAssetId:detailCatAssetService.getId()};
				detailCatAssetService.getDetail(vm.objSearch).then(function(d) {
					vm.detail = d;
					vm.detail.originalPrice = convertStringToCurrency(vm.detail.originalPrice, false, false);
					vm.itemTemp = angular.copy(vm.detail, vm.itemTemp);
					/*if (vm.detail.depreciationStartDate != null){
						$("#depreciationStartDate").val(chanceDateToString(vm.detail.depreciationStartDate, 'DATE_TO_DATE'));
					}*/
					detailCatAssetService.searchAssetCost({longTermAssetId: vm.detail.longTermAssetId}).then(function(result) {
						var count = 1;
						var str = "";
						var currencyString = 0;
						for(var i = 0; i < result.plain().length; i++){
							var item = result.plain()[i];
							if (item.loacType > 2){
								currencyString += item.voucherValue;
								str += "<tr>" +
									"<td class=\"text-center\">" + count + "<input type=\"hidden\" class=\"id\" value=\"" + item.longTermAssetCostId + "\" /></td>" +
									"<td>" +
										"<select class=\"type\" style=\"padding: 2.6px 5px; border: 1px solid #c2cad8; with: 100%;\">" +
											"<option value=\"3\"" + (item.loacType === 3 ? " selected" : "") + ">Chi phí tư vấn đầu tư xây dựng</option>" +
											"<option value=\"4\"" + (item.loacType === 4 ? " selected" : "") + ">Chi phí QLDA</option>" +
											"<option value=\"5\"" + (item.loacType === 5 ? " selected" : "") + ">Chi phí bồi thường, hỗ trợ, tái định cư</option>" +
											"<option value=\"6\"" + (item.loacType === 6 ? " selected" : "") + ">Chi phí khác</option>" +
										"</select>" +
									"</td>" +
									"<td>" +
										"<input type=\"text\" value=\"" + convertStringToCurrency(item.voucherValue.toString(), false, false) + "\" class=\"form-control value text-right\" onkeypress=\"return event.charCode >= 48 && event.charCode <= 57\" onchange=\"changeCurrency(this)\" />" +
									"</td>" +
									"<td>" +
										"<input type=\"text\" value=\"" + item.voucherCode + "\" class=\"form-control code\" maxlength=\"50\" />" +
									"</td>" +
									"<td>" +
										"<input class=\"form-control date\" kendo-date-picker data-date-msg=\"Ngày không đúng định dạng!\" name=\"inputDate" + count + "\" k-format=\"'dd/MM/yyyy'\" value=\"" + item.voucherDate + "\" />" +
										"<span data-for=\"inputDate" + count + "\" class=\"k-invalid-msg\"></span>" +
										"<script>" +
											"$(function() {" +
												"var inputDate = $(\"input[name='inputDate" + count + "']\");" +
												"inputDate.kendoMaskedTextBox({" +
													"mask : \"00/00/0000\"" +
												"});" +
												"inputDate.kendoDatePicker({" +
													"format : \"dd/MM/yyyy\"" +
												"});" +
												"inputDate.closest(\".k-datepicker\").add(inputDate).removeClass(\"k-textbox\");" +
											"});" +
										"</script>" +
										//"<input type=\"date\" value=\"" + item.voucherDate + "\" class=\"form-control date\" />" +
									"</td>" +
									"<td>" +
										"<div class=\"input-group\">" +
											"<input type=\"file\" accept=\".doc, .xls, .pdf\" class=\"file\" style=\"display: none;\" onchange=\"angular.element(this).scope().changeDisFile(this)\" />" +
											"<input type=\"text\" class=\"form-control att\" value=\"" + (item.attachName != null ? item.attachName : "") + "\" placeholder=\"Chọn file...\" readonly onclick=\"angular.element(this).scope().txtChoseDisFile(this)\" />" +
											"<span class=\"input-group-btn\">" +
												"<button class=\"btn btn-default btn-sm\" type=\"button\" style=\"padding-bottom: 3px; padding-top: 2px;\" onclick=\"angular.element(this).scope().choseDisFile(this)\">Browser</button>" +
											"</span>" +
										"</div>" +
									"</td>" +
									"<td class=\"text-center\" style=\"width: 80px;\">" +
										"<div class=\"btn-group\">" +
											"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"addDisRow(this);\">" +
												"<span class=\"glyphicon glyphicon-plus\"></span>" +
											"</button>" +
											"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"removeDisRow(this);\">" +
												"<span class=\"glyphicon glyphicon-remove\"></span>" +
											"</button>" +
										"</div>" +
									"</td>" +
								"</tr>";
								count++;
								vm.countRow++;
							}
						}
						var compiledeHTML = $compile(str)($scope);
						$("#tblDistribution tbody tr:last").before(compiledeHTML);
						vm.totalDis = convertStringToCurrency(currencyString, false, false);
						vm.tempStr = str;
					}, function(errResponse){
						console.error('Error while get list AssetCost');
					});
				}, function(errResponse){
					console.error('Error while getDetail');
				});
			}
		});
		
		$scope.$watch('vm.detail',function(obj){
			if (obj.longTermAssetId != 0){
				vm.notChange = false;
			}
		});
		
		$scope.$on("cat.detail.reload", function(event){
			vm.objSearch = {longTermAssetId:detailCatAssetService.getId()};
			detailCatAssetService.getDetail(vm.objSearch).then(function(d) {
				vm.detail = d;
				vm.detail.originalPrice = convertStringToCurrency(vm.detail.originalPrice.toString(), false, false);
				vm.itemTemp = angular.copy(vm.detail, vm.itemTemp);
				/*if (vm.detail.depreciationStartDate != null){
					$("#depreciationStartDate").val(chanceDateToString(vm.detail.depreciationStartDate, 'DATE_TO_DATE'));
				}*/
				detailCatAssetService.searchAssetCost({longTermAssetId: vm.detail.longTermAssetId}).then(function(result) {
					var strFirst = "<tr>" +
						"<td class=\"text-center\">" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + "<input type=\"hidden\" class=\"id\" /></td>" +
						"<td>" +
							"<select class=\"type\" style=\"padding: 2.6px 5px; border: 1px solid #c2cad8; with: 100%;\">"+
								"<option value=\"3\">Chi phí tư vấn đầu tư xây dựng</option>" +
								"<option value=\"4\">Chi phí QLDA</option>" +
								"<option value=\"5\">Chi phí bồi thường, hỗ trợ, tái định cư</option>" +
								"<option value=\"6\">Chi phí khác</option>" +
							"</select>" +
						"</td>" +
						"<td>" +
							"<input type=\"text\" class=\"form-control value text-right\" onkeypress=\"return event.charCode >= 48 && event.charCode <= 57\" onchange=\"changeCurrency(this)\" />" +
						"</td>" +
						"<td>" +
							"<input type=\"text\" class=\"form-control code\" maxlength=\"50\" />" +
						"</td>" +
						"<td>" +
							"<input class=\"form-control date\" kendo-date-picker data-date-msg=\"Ngày không đúng định dạng!\" name=\"inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + "\" k-format=\"'dd/MM/yyyy'\" />" +
							"<span data-for=\"inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + "\" class=\"k-invalid-msg\"></span>" +
							"<script>" +
								"$(function() {" +
									"var inputDate = $(\"input[name='inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + "']\");" +
									"inputDate.kendoMaskedTextBox({" +
										"mask : \"00/00/0000\"" +
									"});" +
									"inputDate.kendoDatePicker({" +
										"format : \"dd/MM/yyyy\"" +
									"});" +
									"inputDate.closest(\".k-datepicker\").add(inputDate).removeClass(\"k-textbox\");" +
								"});" +
							"</script>" +
							//"<input type=\"date\" class=\"form-control date\" />" +
						"</td>" +
						"<td>" +
							"<div class=\"input-group\">" +
								"<input type=\"file\" accept=\".doc, .xls, .pdf\" class=\"file\" style=\"display: none;\" onchange=\"angular.element(this).scope().changeDisFile(this)\" />" +
								"<input type=\"text\" class=\"form-control att\" placeholder=\"Chọn file...\" readonly onclick=\"angular.element(this).scope().txtChoseDisFile(this)\" />" +
								"<span class=\"input-group-btn\">" +
									"<button class=\"btn btn-default btn-sm\" type=\"button\" style=\"padding-bottom: 3px; padding-top: 2px;\" onclick=\"angular.element(this).scope().choseDisFile(this)\">Browser</button>" +
								"</span>" +
							"</div>" +
						"</td>" +
						"<td class=\"text-center\" style=\"width: 80px;\">" +
							"<div class=\"btn-group\">" +
								"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"addDisRow(this);\">" +
									"<span class=\"glyphicon glyphicon-plus\"></span>" +
								"</button>" +
								"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"removeDisRow(this);\">" +
									"<span class=\"glyphicon glyphicon-remove\"></span>" +
								"</button>" +
							"</div>" +
						"</td>" +
					"</tr>";
					var compiledeFirstHTML = $compile(strFirst)($scope);
					$("#tblDistribution tbody").html(compiledeFirstHTML);
					
					var count = 1;
					var str = "";
					var currencyString = 0;
					for(var i = 0; i < result.plain().length; i++){
						var item = result.plain()[i];
						if (item.loacType > 2){
							currencyString += item.voucherValue;
							str += "<tr>" +
								"<td class=\"text-center\">" + count + "<input type=\"hidden\" class=\"id\" value=\"" + item.longTermAssetCostId + "\" /></td>" +
								"<td>" +
									"<select class=\"type\" style=\"padding: 2.6px 5px; border: 1px solid #c2cad8; with: 100%;\">" +
										"<option value=\"3\"" + (item.loacType === 3 ? " selected" : "") + ">Chi phí tư vấn đầu tư xây dựng</option>" +
										"<option value=\"4\"" + (item.loacType === 4 ? " selected" : "") + ">Chi phí QLDA</option>" +
										"<option value=\"5\"" + (item.loacType === 5 ? " selected" : "") + ">Chi phí bồi thường, hỗ trợ, tái định cư</option>" +
										"<option value=\"6\"" + (item.loacType === 6 ? " selected" : "") + ">Chi phí khác</option>" +
									"</select>" +
								"</td>" +
								"<td>" +
									"<input type=\"text\" value=\"" + convertStringToCurrency(item.voucherValue, false, false) + "\" class=\"form-control value text-right\" onkeypress=\"return event.charCode >= 48 && event.charCode <= 57\" onchange=\"changeCurrency(this)\" />" +
								"</td>" +
								"<td>" +
									"<input type=\"text\" value=\"" + item.voucherCode + "\" class=\"form-control code\" maxlength=\"50\" />" +
								"</td>" +
								"<td>" +
									"<input class=\"form-control date\" kendo-date-picker data-date-msg=\"Ngày không đúng định dạng!\" name=\"inputDate" + count + "\" k-format=\"'dd/MM/yyyy'\" value=\"" + item.voucherDate + "\" />" +
									"<span data-for=\"inputDate" + count + "\" class=\"k-invalid-msg\"></span>" +
									"<script>" +
										"$(function() {" +
											"var inputDate = $(\"input[name='inputDate" + count + "']\");" +
											"inputDate.kendoMaskedTextBox({" +
												"mask : \"00/00/0000\"" +
											"});" +
											"inputDate.kendoDatePicker({" +
												"format : \"dd/MM/yyyy\"" +
											"});" +
											"inputDate.closest(\".k-datepicker\").add(inputDate).removeClass(\"k-textbox\");" +
										"});" +
									"</script>" +
									//"<input type=\"date\" value=\"" + item.voucherDate + "\" class=\"form-control date\" />" +
								"</td>" +
								"<td>" +
									"<div class=\"input-group\">" +
										"<input type=\"file\" accept=\".doc, .xls, .pdf\" class=\"file\" style=\"display: none;\" onchange=\"angular.element(this).scope().changeDisFile(this)\" />" +
										"<input type=\"text\" class=\"form-control att\" value=\"" + (item.attachName != null ? item.attachName : "") + "\" placeholder=\"Chọn file...\" readonly onclick=\"angular.element(this).scope().txtChoseDisFile(this)\" />" +
										"<span class=\"input-group-btn\">" +
											"<button class=\"btn btn-default btn-sm\" type=\"button\" style=\"padding-bottom: 3px; padding-top: 2px;\" onclick=\"angular.element(this).scope().choseDisFile(this)\">Browser</button>" +
										"</span>" +
									"</div>" +
								"</td>" +
								"<td class=\"text-center\" style=\"width: 80px;\">" +
									"<div class=\"btn-group\">" +
										"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"addDisRow(this);\">" +
											"<span class=\"glyphicon glyphicon-plus\"></span>" +
										"</button>" +
										"<button type=\"button\" class=\"btn btn-link btn-xs\" onclick=\"removeDisRow(this);\">" +
											"<span class=\"glyphicon glyphicon-remove\"></span>" +
										"</button>" +
									"</div>" +
								"</td>" +
							"</tr>";
							
							count++;
							vm.countRow++;
						}
					}
					var compiledeHTML = $compile(str)($scope);
					$("#tblDistribution tbody tr:last").before(compiledeHTML);
					vm.totalDis = convertStringToCurrency(currencyString, false, false);
					vm.tempStr = str;
				}, function(errResponse){
					console.error('Error while get list AssetCost');
				});
			}, function(errResponse){
				console.error('Error while getDetail');
			});
		});
		
		$scope.changeCurrency = function(element){
			if ($(element).val() != ""){
				var currency = convertStringToCurrency($(element).val(), false, false);
				$(element).val(currency);
				caculateCost();
			}
		}
		
		vm.caculateCost = caculateCost;
		function caculateCost(){
			vm.totalDis = 0;
			var disItem = $("#tblDistribution tbody tr");
			for (var i = 0; i < disItem.length; i++) {
				if ($(disItem[i]).find(".value").val() != "" && $(disItem[i]).find(".value").val() != "0"){
					vm.totalDis += parseFloat($(disItem[i]).find(".value").val().replaceAll(".", ""));
				}
			}
			vm.detail.originalPrice = vm.totalDis + parseFloat(vm.totalCost.toString().replaceAll(".", ""));
			
			vm.totalDis = convertStringToCurrency(vm.totalDis, false, false);
			vm.detail.originalPrice = convertStringToCurrency(vm.detail.originalPrice, false, false);
		}
		
		/*$scope.$watch('vm.detail.groupId',function(id){
			vm.useGroupSearch.parentId = id;
		});*/
	}
})();
function addDisRow(element){
	var scope = angular.element(document.getElementById("formDetail")).scope();
	scope.$apply(function () {
		scope.addDisRow(element);
    });
}
function removeDisRow(element){
	var scope = angular.element(document.getElementById("formDetail")).scope();
	scope.$apply(function () {
		scope.removeDisRow(element);
    });
}
function changeCurrency(element){
	var scope = angular.element(document.getElementById("formDetail")).scope();
	scope.$apply(function () {
		scope.changeCurrency(element);
    });
}
function convertStringToCurrency(strConvert, allowZero, allowNegative) {
    var str = strConvert.toString().replaceAll(".", "");
	// Khong cho nhap hai dau "."
	var isDot = false;
	for(var i = 0; i< str.length; i++){
		if (str[i] === ","){
			if (isDot){
				str = str.replaceAt(i, "");
			}
			isDot = true;
		}
	}
	// Cho phep nhan so am
	if (typeof allowNegative === "undefined" || allowNegative){
		// Khong cho nhap hai dau "-"
		var isNegative = false;
		for(var i = 0; i< str.length; i++){
			if (str[i] === "-"){
				if (i === 0)
					isNegative = true;
				else{
					if (isNegative){
						str = str.replaceAt(i, "");
					}
				}
			}
		}
	}
	else{
		str = str.replaceAll("-", "");
	}
	
	// Khong cho nhap hai so 0 dau tien
	var isZero = false;
	for(var i = 0; i< str.length; i++){
		if (str[i] === "0" && i === 0){
			isZero = true;
		}
		if (isZero && i == 1 && str[i] !== ","){
			if (typeof allowZero === "undefined" || !allowZero){
				if (str[i] === "0")
					str = str.replaceAt(i, "");
				str = str.replaceAt(0, "");
			}
			else{
				if (str[i] !== "0")
					str = str.replaceAt(0, "");
				else
					str = str.replaceAt(i, "");
			}
			break;
		}
	}
	var indexDot = str.indexOf(",");
	var beforeDot = (indexDot >= 0 ? str.substring(0, indexDot) : str).replace("-", "");
	var affterDot = indexDot >= 0 ? str.substring(indexDot, str.length) : "";
    var strReturn = "";
	// Chuyen phan so truoc dau "." sang dinh dang tien
    if (beforeDot.length > 3) {
        var loop = beforeDot.length - 1;
        while (loop >= 0) {
            if (strReturn.replaceAll(".", "").length % 3 == 0 && strReturn != "")
            {
                strReturn = beforeDot[loop] + "." + strReturn;
            }
            else
                strReturn = beforeDot[loop] + strReturn;
            loop--;
        }
    }
    else
        strReturn = beforeDot;
	
    return isNegative ? "-" + (indexDot === 0 ? "0" : "") + strReturn + affterDot : (indexDot === 0 ? "0" : "") + strReturn + affterDot;
}
String.prototype.replaceAll = function (strTarget, strSubString) {
	var strText = this;
	strText = strText.toLowerCase();
	var intIndexOfMatch = strText.indexOf(strTarget);
	while (intIndexOfMatch != -1) {
		strText = strText.replace(strTarget, strSubString)
		intIndexOfMatch = strText.indexOf(strTarget);
	}
	return strText.replace('"', '');
}
String.prototype.replaceAt = function(index, character) {
    return this.substring(0, index) + character + this.substring((index + 1) + character.length);
}