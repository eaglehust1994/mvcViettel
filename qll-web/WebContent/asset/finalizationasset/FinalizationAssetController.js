(function() {
	'use strict';
	var controllerId = 'finalizationassetcontroller';

	angular.module('MetronicApp').controller(controllerId, finalizationassetcontroller);

	function finalizationassetcontroller($scope, $rootScope, $timeout, $compile, Constant, kendoConfig, finalizationassetService, gettextCatalog, $kWindow, CommonService, Restangular, PopupConst, RestEndpoint) {
		var vm = this;
		vm.validatorOptions = kendoConfig.get('validatorOptions');
		
		initFormData();
		function initFormData() {
			vm.item = {
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
			finalizationassetService.getCatAssetFolder().then(function(data) {
				vm.folder = data.folder;
			}, function(errResponse){
				console.error('Error while getCatAssetFolder');
			});
			
			vm.notChange = true;
			vm.countRow = 1;
			vm.itemTemp = {};
			vm.tempStr = "";
		}
		
		vm.cancel = cancel;
		function cancel(){
			if (vm.item.longTermAssetId !== 0){
				vm.item = vm.itemTemp;
			}
			else{
				vm.totalDis = 0;
				vm.item = {
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
			$("#tblFinalizationDis tbody").html(compiledeHTML)
			var compiledeTempHTML = $compile(vm.tempStr)($scope);
			$("#tblFinalizationDis tbody tr:last").before(compiledeTempHTML);
			caculateCost();
		}
		
		vm.save = save;
		function save(){
			if (vm.item.longTermAssetId !== 0){
				if (vm.validator.validate()) {
					$("#finalizationForm #btnSave").css("pointer-events", "none");
					
					// Thong tin cho bang LONG_TERM_ASSET_ENTITIES 
					vm.item.listAssetCost = [];
					vm.item.listAssetEntities = vm.dataMerEntity;
					
					// Thong tin cho bang LONG_TERM_ASSET_COSTS va LONG_TERM_ASSET_VOUCHERS 
					// Them chi phi vat tu thiet bi vao
					var objMerEntity = new Object();
					objMerEntity.loacType = 1;
					objMerEntity.loacValue = vm.merEntityValue.toString().replaceAll(".", "");
					objMerEntity.voucherCode = '';
					objMerEntity.voucherDate = '';
					vm.item.listAssetCost.push(objMerEntity);
					
					// Them chi phi xay lap vao
					var objConstructionAcceptance = new Object();
					objConstructionAcceptance.loacType = 2;
					objConstructionAcceptance.loacValue = vm.constructionAcceptanceValue.toString().replaceAll(".", "");
					objConstructionAcceptance.voucherCode = '';
					objConstructionAcceptance.voucherDate = '';
					vm.item.listAssetCost.push(objConstructionAcceptance);
					
					// Them thong tin nguoi dung nhap tu bang phan bo vao
					var disItem = $("#tblFinalizationDis tbody tr");
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
							objAssetCost.attachName = $(disItem[i]).find(".att").val();
							
							if (typeof $(disItem[i]).find(".file")[0].files[0] !== "undefined" && $(disItem[i]).find(".file")[0].files[0].name != null && $(disItem[i]).find(".file")[0].files[0].name !== ""){
								var fileData = new FormData();
								var fileUpload = $(disItem[i]).find(".file")[0].files[0];
								fileData.append('fileUpload', fileUpload);
								$.ajax({
									url: RestEndpoint.BASE_SERVICE_URL + RestEndpoint.LONG_TERM_ASSET_URL + "/upload?folder=" + vm.folder, //"/qlhc-service/fileservice/upload?folder="+ vm.folder,
									type: "POST",
									data: fileData,
									enctype: 'multipart/form-data',
									processData: false,
									contentType: false,
									cache: false,
									success: function() {
										console.log("Upload successfully!");
									},
									error: function() {
										
									}
								});
								
								objAssetCost.attachName = fileUpload.name;
								objAssetCost.hasUpload = 'Y';
							}
							vm.item.listAssetCost.push(objAssetCost);
						}
					}
					
					vm.item.originalPrice = vm.item.originalPrice.toString().replaceAll(".", "");
					vm.item.lotaStatus = 1;
					finalizationassetService.updateCatAsset(vm.item).then(function(result){
						toastr.success(gettextCatalog.getString("Lưu thay đổi thành công!"));
						$("#finalizationForm #btnSave").removeAttr('style');
						vm.item.originalPrice = convertStringToCurrency(vm.item.originalPrice, false, false);
					}, function(errResponse) {
						$("#finalizationForm #btnSave").removeAttr('style');
						vm.item.originalPrice = convertStringToCurrency(vm.item.originalPrice, false, false);
						toastr.error(gettextCatalog.getString("Lỗi xuất hiện!"));
					});
				}
				else{
					$("#finalizationForm #btnSave").removeAttr('style');
				}
			}
		}
		
		$scope.changeDisFile = function(element){
			if (typeof $(element)[0].files[0] !== "undefined")
				$(element).next().val($(element)[0].files[0].name);
		}
		
		$scope.choseDisFile = function(element){
			$(element).parent().prev().prev().click();
		}
		
		$scope.txtChoseDisFile = function(element){
			$(element).prev().click();
		}
		
		$scope.removeDisRow = function(element){
			var disItem = $("#tblFinalizationDis tbody tr");
			if (disItem.length > 1){
				$(element).parent().parent().parent().remove();
				caculateCost();
			}
		}
		
		$scope.addDisRow = function(element){
			if ($(element).parent().parent().parent().find(".value").val() !== "" && $(element).parent().parent().parent().find(".att").val() !== ""){
				var count = $("#tblFinalizationDis tbody tr").length + 1;
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
				$("#tblFinalizationDis tbody").append(compiledeHTML);
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
						width: 50,
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
			var disItem = $("#tblFinalizationDis tbody tr");
			for (var i = 0; i < disItem.length; i++) {
				if ($(disItem[i]).find(".value").val() != "" && $(disItem[i]).find(".value").val() != "0"){
					vm.totalDis += parseFloat($(disItem[i]).find(".value").val().replaceAll(".", ""));
				}
			}
			vm.item.originalPrice = vm.totalDis + parseFloat(vm.totalCost.toString().replaceAll(".", ""));
			
			vm.totalDis = convertStringToCurrency(vm.totalDis, false, false);
			vm.item.originalPrice = convertStringToCurrency(vm.item.originalPrice, false, false);
		}
		
		$scope.$watch('vm.item.constructId',function(id){
			if (typeof id !== "undefined" && id != null && id.toString() != ""){
				$("div.loading-effect").show();
				finalizationassetService.getMerEntity({constructionId: id}).then(function(d) {
					vm.dataMerEntity = d.plain();
					for(var i = 0; i < vm.dataMerEntity.length; i++){
						var obj = vm.dataMerEntity[i];
						vm.merEntityValue += (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
						vm.dataMerEntity[i].total = (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
						vm.dataMerEntity[i].lineNo = i + 1;
					}
					vm.merEntityValue = convertStringToCurrency(vm.merEntityValue.toString(), false, false);
					finalizationassetService.getConstructionAcceptance({constructionId: id}).then(function(d) {
						vm.dataConstructionAcceptance = d.plain();
						for(var j = 0; j < vm.dataConstructionAcceptance.length; j++){
							var obj = vm.dataConstructionAcceptance[j];
							vm.constructionAcceptanceValue += (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
							vm.dataConstructionAcceptance.total = (obj.merWeight != null && obj.vndUnitPrice != null ? obj.merWeight * obj.vndUnitPrice : 0);
							vm.dataConstructionAcceptance[j].lineNo = j + 1;
						}
						vm.constructionAcceptanceValue = convertStringToCurrency(vm.constructionAcceptanceValue.toString(), false, false);
						vm.totalCost = convertStringToCurrency(parseFloat(vm.merEntityValue.toString().replaceAll(".", "")) + parseFloat(vm.constructionAcceptanceValue.toString().replaceAll(".", "")), false, false);
						
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
		
		$scope.$watch('vm.item.longTermAssetId',function(id){
			if (typeof id != "undefined" && id != null && id != ""){
				vm.objSearch = {longTermAssetId: id};
				finalizationassetService.getDetail(vm.objSearch).then(function(d) {
					vm.item = d;
					vm.itemTemp = angular.copy(vm.item, vm.itemTemp);
					
					finalizationassetService.searchAssetCost({longTermAssetId: vm.item.longTermAssetId}).then(function(result) {
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
										"var inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + " = $(\"input[name='inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + "']\");" +
										"inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + ".kendoMaskedTextBox({" +
											"mask : \"00/00/0000\"" +
										"});" +
										"inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + ".kendoDatePicker({" +
											"format : \"dd/MM/yyyy\"" +
										"});" +
										"inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + ".closest(\".k-datepicker\").add(inputDate" + (result.plain().length > 0 ? result.plain().length - 1 : 1) + ").removeClass(\"k-textbox\");" +
									"});" +
								"</script>" +
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
						$("#tblFinalizationDis tbody").html(compiledeFirstHTML);
						
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
												"var inputDate" + count + " = $(\"input[name='inputDate" + count + "']\");" +
												"inputDate" + count + ".kendoMaskedTextBox({" +
													"mask : \"00/00/0000\"" +
												"});" +
												"inputDate" + count + ".kendoDatePicker({" +
													"format : \"dd/MM/yyyy\"" +
												"});" +
												"inputDate" + count + ".closest(\".k-datepicker\").add(inputDate" + count + ").removeClass(\"k-textbox\");" +
											"});" +
										"</script>" +
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
						$("#tblFinalizationDis tbody tr:last").before(compiledeHTML);
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
	}
})();

function addDisRow(element){
	var scope = angular.element(document.getElementById("finalizationFormDetail")).scope();
	scope.$apply(function () {
		scope.addDisRow(element);
    });
}
function removeDisRow(element){
	var scope = angular.element(document.getElementById("finalizationFormDetail")).scope();
	scope.$apply(function () {
		scope.removeDisRow(element);
    });
}
function changeCurrency(element){
	var scope = angular.element(document.getElementById("finalizationFormDetail")).scope();
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