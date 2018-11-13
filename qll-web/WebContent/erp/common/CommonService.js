angular.module('MetronicApp').service('searchType', function() {
});
angular
		.module('MetronicApp')
		.factory(
				'CommonService',
				[
						'RestEndpoint',
						'Restangular',
						'$kWindow',
						'Constant',
						'$rootScope',
						'$translate',
						'searchType',
						'$filter',
						'$q',
						'$http',
						function(RestEndpoint, Restangular, $kWindow, Constant,
								$rootScope, $translate, searchType, $filter,
								$q, $http) {
							var serviceUrl = RestEndpoint.TBL_USER_ROLE;
							var serviceUrl1 = RestEndpoint.LICH_SU_DANG_NHAP;
							var serviceUrl2 = RestEndpoint.COMMON_REST;
							var factory = {
								populateDataToGrid : populateDataToGrid,
								populateDataToGridBMaterial : populateDataToGridBMaterial,
								populateDataToGridCT : populateDataToGridCT,
								populateDataToTree : populateDataToTree,
								populateDataToGridplus : populateDataToGridplus,
								populatePopup : populatePopup,
								openCustomPopup : openCustomPopup,
								closePopup : closePopup,
								dismissPopup : dismissPopup,
								closePopup1 : closePopup1,
								dismissPopup1 : dismissPopup1,
								fetchFromURL : fetchFromURL,
								fetchSingleFromURL : fetchSingleFromURL,

								initCurrencyData : initCurrencyData,
								getRoundingOrginalCurrency : getRoundingOrginalCurrency,
								getRoundingConvertedCurrency : getRoundingConvertedCurrency,
								getAdOrgDefault : getAdOrgDefault,
								getDepartmentDefault : getDepartmentDefault,
								postToUrl : postToUrl,
								openStockTransfer : openStockTransfer,
								openSplitMoney : openSplitMoney,
								getValueOrg : getValueOrg,
								getNotify : getNotify,
								openOtherForm : openOtherForm,
								setItem : setItem,
								getItem : getItem,
								goToMenu : goToMenu,
								goTo : goTo,
								getUserInfo : getUserInfo,
								translate : translate,
								populatePopupCreate : populatePopupCreate,
								populatePopupDept : populatePopupDept,
								populatePopupVofice : populatePopupVofice,
								getDepartment : getDepartment,
								validateImport : validateImport,
								exportFile : exportFile,
								exportReport : exportReport,
								genCode : genCode,
								getCharFour : getCharFour,
								getCharThree : getCharThree,
								getCharOneTimes : getCharOneTimes,
								getCharOneAmount : getCharOneAmount,
								getCharTwoWeek : getCharTwoWeek,
								getCharTwoMonth : getCharTwoMonth,
								signVoffice : signVoffice,
								getDataSign : getDataSign,
								getDetailSign : getDetailSign,
								closeTab : closeTab,
								downloadTemplate : downloadTemplate,
								getallData : getallData,
								resetData : resetData,
								getUserRoles : getUserRoles,
								getDepartment1:getDepartment1,
								checkRole:checkRole,
								insertLSDN:insertLSDN,
							};

							var modalInstance;
							var modalInstance1;
							var item;
							var checkOnePopup = false;
							var checkTowPopup = false;
							return factory;
							function translate(text) {

								try {
									return $translate.instant(text);

								} catch (err) {
									return text;
								}

							}
							function getUserInfo() {

								if (Constant.user != null
										&& Constant.user.srvUser != null) {
									srvuser = Constant.user.srvUser;
									return {
										groupId : srvuser.groupId,
										groupName : srvuser.groupName,
										groupCode : srvuser.groupCode,
										userId : srvuser.userId
									};
								}
								return {};
							}

							function setItem(item) {
								this.item = item;
							}
							function getItem() {
								return this.item;
							}
							//qll
							function getDepartment1(obj) {
								return Restangular.all("tblDanhMucServiceRest/doSearchDepartment").post(obj);
							}
							//
							function goToMenu(menuKey, option) {
								var template = Constant.getTemplateUrl(menuKey);
								postal.publish({
									channel : "Tab",
									topic : "open",
									data : template
								});
								$rootScope.$broadcast(option.event, {
									data : option.data
								});
								/*
								 * $rootScope.isCreatAsset = false;
								 * $rootScope.$broadcast("cat.detail.reload");
								 */
							}

							function openOtherForm(recordId) {
								var template = Constant
										.getTemplateUrl('Asset_CatAssetDetail');
								$rootScope.activateResultTab = true;

								postal.publish({
									channel : "Tab",
									topic : "open",
									data : template
								});
							}
							// CommonService.populateDataToGrid(templateUrl,
							// title, vm.gridOptions, vm, popupId,searchtype);
							function populateDataToGrid(templateUrl, gridTitle,
									gridOptions, caller, popupId, searchType,
									isMultiSelect) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : '800',
										height : '550',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : 'ModalInstanceCtrl',
									resolve : {
										data : function() {
											return gridOptions;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										searchType : function() {
											return searchType;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}
							// minhpvn -import bien ban phat sinh cong trinh
							function populateDataToGridCT(templateUrl,
									gridTitle, gridOptions, caller, popupId,
									searchType, isMultiSelect) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : '550',
										height : '350',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : 'ModalInstanceCtrl',
									resolve : {
										data : function() {
											return gridOptions;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										searchType : function() {
											return searchType;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}
							// minhpvn
							function populateDataToGridBMaterial(templateUrl,
									gridTitle, gridOptions, caller, popupId,
									searchType, isMultiSelect) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : '1250',
										height : '550',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : 'ModalInstanceCtrl',
									resolve : {
										data : function() {
											return gridOptions;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										searchType : function() {
											return searchType;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}

							function populateDataToGridplus(templateUrl,
									gridTitle, gridOptions, caller, popupId,
									searchType, isMultiSelect, idFocus) {
								checkTowPopup = true;
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : '1000',
										height : '550',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										},
										activate : function() {
											document.getElementById(idFocus)
													.focus();
										},
									},
									templateUrl : templateUrl,
									controller : 'ModalInstanceCtrl',
									resolve : {
										data : function() {
											return gridOptions;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										searchType : function() {
											return searchType;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}

							function populateDataToTree(templateUrl,
									tableTitle, treeData, caller, popupId,
									isMultiSelect) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : tableTitle,
										visible : false,
										width : '650',
										height : '550',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : 'ModalInstanceCtrl',
									resolve : {
										data : function() {
											return treeData;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}

							function populatePopup(templateUrl, gridOptions,
									gridTitle, data, caller, windowId,
									isCreateNew, sizeWith, sizeHeight) {
								modalInstance = $kWindow
										.open({
											options : {
												modal : true,
												title : gridTitle,
												visible : false,
												width : sizeWith,
												height : sizeHeight,
												actions : [ "Minimize",
														"Maximize", "Close" ],
												open : function() {
													this.wrapper
															.children(
																	'.k-window-content')
															.addClass(
																	"fix-footer");
												},
												close : function() {
													// modalInstance = null;
													$rootScope
															.$broadcast('Popup.CloseClick');
													isOpening = false;
												}
											},
											templateUrl : templateUrl,
											controller : 'PopupCreateNewCtrl',
											resolve : {
												gridOptions : function() {
													return gridOptions;
												},
												data : function() {
													return data;
												},
												caller : function() {
													return caller;
												},
												modalInstance : function() {
													return this;
												},
												windowId : function() {
													return windowId;
												},
												isCreateNew : function() {
													return isCreateNew;
												},
											}
										});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}

							function openCustomPopup(templateUrl, gridTitle,
									gridOptions, caller, popupId,
									controllerName, adOrgId, isMultiSelect) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : '550',
										height : '200',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : controllerName,
									resolve : {
										data : function() {
											return gridOptions;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										adOrgId : function() {
											return adOrgId;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}
							function openSplitMoney(templateUrl, gridTitle,
									gridOptions, caller, popupId,
									controllerName, ccashInBankId) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : '350',
										height : '180',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : controllerName,
									resolve : {
										data : function() {
											return gridOptions;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										ccashInBankId : function() {
											return ccashInBankId;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}
							function openStockTransfer(templateUrl, gridTitle,
									gridOptions, caller, popupId,
									controllerName, depositbrowser) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : '350',
										height : '150',
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : controllerName,
									resolve : {
										data : function() {
											return gridOptions;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										depositbrowser : function() {
											return depositbrowser;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}
							function closePopup() {
								if (checkTowPopup) {
									modalInstance.close();
									checkTowPopup = false;
								}
							}

							function closePopup1() {
								if (checkOnePopup && !checkTowPopup) {
									modalInstance1.close();
									checkOnePopup = false;
								}
							}
							function dismissPopup() {
								modalInstance.dismiss('cancel');
								checkTowPopup = false;
							}
							function dismissPopup1() {
								if (checkOnePopup && !checkTowPopup) {
									modalInstance1.dismiss('cancel');
									checkTowPopup = false;
								}
							}
							function fetchFromURL(url) {
								return Restangular.all(url).getList();
							}

							function postToUrl(url, data) {
								return Restangular.all(url).post(data);
							}

							function fetchSingleFromURL(url) {
								return Restangular.one(url).get();
							}

							function initCurrencyData() {
								/*
								 * return
								 * Restangular.all(RestEndpoint.C_CURRENCY_SERVICE_URL).getList().then(function(d){
								 * $rootScope.currencyData = d.plain(); });
								 */
							}

							function getRoundingOrginalCurrency(currencyId,
									amount) {
								var roundingConvertedScale = 0;
								var roundingOriginalCurrencyScale = 2;

								if ($rootScope.currencyData != undefined) {
									for ( var item in $rootScope.currencyData) {
										if (item.ccurrencyId == currencyId) {
											roundingConvertedScale = item.roundingConverted;
											roundingOriginalCurrencyScale = item.roundingOriginalCurrency;

											break;
										}
									}

									return amount
											.format(roundingOriginalCurrencyScale);
								} else {
									return amount;
								}
							}

							function getRoundingConvertedCurrency(currencyId,
									amount) {
								var roundingConvertedScale = 0;
								var roundingOriginalCurrencyScale = 2;

								if ($rootScope.currencyData != undefined) {
									for ( var item in $rootScope.currencyData) {
										if (item.ccurrencyId == currencyId) {
											roundingConvertedScale = item.roundingConverted;
											roundingOriginalCurrencyScale = item.roundingOriginalCurrency;

											break;
										}
									}
									console.log("roundingConvertedScale: "
											+ roundingConvertedScale);
									return amount
											.format(roundingConvertedScale);
								} else {
									return amount;
								}
							}

							function getAdOrgDefault() {
								return Restangular.one(
										RestEndpoint.AD_ORG_SERVICE_URL
												+ "/getDefault").get();
							}

							function getDepartmentDefault() {
								return Restangular.one(
										RestEndpoint.C_DEPARTMENT_SERVICE_URL
												+ "/getDefault").get();
							}
							function getValueOrg(adOrgId) {
								return Restangular.all(
										RestEndpoint.AD_ORG_SERVICE_URL
												+ "/getValue/").post(adOrgId);
							}

							function getNotify(userId) {
								return Restangular.all(
										RestEndpoint.GET_NOTIFY_SERVICE_URL)
										.post(userId);
							}

							function populatePopupCreate(templateUrl,
									gridTitle, data, caller, windowId,
									isCreateNew, sizeWith, sizeHeight, idFocus) {
								checkOnePopup = true;
								modalInstance1 = $kWindow
										.open({
											options : {
												modal : true,
												title : gridTitle,
												visible : false,
												width : sizeWith,
												height : sizeHeight,
												actions : [ "Minimize",
														"Maximize", "Close" ],
												open : function() {
													this.wrapper
															.children(
																	'.k-window-content')
															.addClass(
																	"fix-footer");
													$rootScope
															.$broadcast('Popup.open');
													if(!!caller.onOpen){
														caller.onOpen(windowId);
													}
												},
												close : function() {
													// modalInstance = null;
													$rootScope
															.$broadcast('Popup.CloseClick');
													isOpening = false;
												},
												activate : function() {
													if (document
															.getElementById(idFocus))
														document
																.getElementById(
																		idFocus)
																.focus();
												},
											},
											templateUrl : templateUrl,
											controller : 'PopupCreateNewCtrl',
											resolve : {
												data : function() {
													return data;
												},
												caller : function() {
													return caller;
												},
												modalInstance1 : function() {
													return this;
												},
												windowId : function() {
													return windowId;
												},
												isCreateNew : function() {
													return isCreateNew;
												},
											}
										});

								modalInstance1.result.then(function(result) {
									dismissPopup1();
								});
							}

							function populatePopupDept(templateUrl, gridTitle,
									gridOptions, data, caller, popupId,
									searchType, isMultiSelect, sizeWith,
									sizeHeight) {
								checkOnePopup = true;
								modalInstance1 = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : sizeWith,
										height : sizeHeight,
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : 'popupDepartmentController',
									resolve : {
										gridOptions : function() {
											return gridOptions;
										},
										dataTree : function() {
											return data;
										},
										caller : function() {
											return caller;
										},
										modalInstance1 : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										searchType : function() {
											return searchType;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance1.result.then(function(result) {
									dismissPopup1();
								});
							}

							function populatePopupVofice(templateUrl,
									gridTitle, businessType, data, caller,
									popupId, searchType, isMultiSelect,
									sizeWith, sizeHeight) {
								modalInstance = $kWindow.open({
									options : {
										modal : true,
										title : gridTitle,
										visible : false,
										width : sizeWith,
										height : sizeHeight,
										actions : [ "Minimize", "Maximize",
												"Close" ],
										open : function() {
											this.wrapper.children(
													'.k-window-content')
													.addClass("fix-footer");
										}
									},
									templateUrl : templateUrl,
									controller : 'signVofficeController',
									resolve : {
										data : function() {
											return data;
										},
										businessType : function() {
											return businessType;
										},
										caller : function() {
											return caller;
										},
										modalInstance : function() {
											return this;
										},
										popupId : function() {
											return popupId;
										},
										searchType : function() {
											return searchType;
										},
										isMultiSelect : function() {
											return isMultiSelect;
										}
									}
								});

								modalInstance.result.then(function(result) {
									dismissPopup();
								});
							}

							function getDepartment(obj) {
								return Restangular
										.all(
												"tblDanhMucServiceRest/doSearchDepartment")
										.post(obj);
							}

							function goTo(menuKey) {

								var hasPerm = true;

								if (hasPerm) {
									var template = Constant
											.getTemplateUrl(menuKey);

									postal.publish({
										channel : "Tab",
										topic : "open",
										data : template
									});
								} else {
									// toastr.error(gettextCatalog.getString("Tài
									// khoản đăng nhập
									// hiện tại không được phép truy cập vào
									// chức năng này!"));
								}

							}

							function closeTab(menuKey) {

								var template = Constant.getTemplateUrl(menuKey);

								postal.publish({
									channel : "Tab",
									topic : "closeTab",
									data : template
								});
							}

							// validate template import

							function validateImport(dataList, validateColums) {
								var objReturn = {}
								objReturn.listData = [];
								if (dataList.hederList.length < validateColums.length) {
									objReturn.msg = "Không đúng biểu mẫu import"
									return objReturn
								} else {
									var str1 = "";
									var str2 = "";
									for (var i = 0; i < validateColums.length; i++) {
										str1 = str1 + validateColums[i].colum;
									}

									for (var i = 0; i < dataList.hederList.length; i++) {
										str2 = str2 + dataList.hederList[i];
									}
									if (str1 !== str2) {
										objReturn.msg = "Không đúng biểu mẫu import"
										return objReturn
									}

								}
								return objReturn;

							}

							// Export common
							function exportFile(kendoGrid, data, listRemove,
									listConvert, FileName) {
								var selectedRow = [];
								kendoGrid.table.find("tr").each(
										function(idx, item) {
											var row = $(item);
											var checkbox = $(
													'[name="gridcheckbox"]',
													row);

											if (checkbox.is(':checked')) {
												// Push id into selectedRow
												var tr = kendoGrid.select()
														.closest("tr");
												var dataItem = kendoGrid
														.dataItem(item);
												selectedRow.push(dataItem);
											}
										});
								// var data = [];
								// if (obj != null) {
								// data.push(obj);
								// } else if (selectedRow.length > 0
								// && obj == null) {
								// data = selectedRow
								// } else {
								// data = kendoGrid.dataSource.data();
								// }
								var title = [];
								var field = [];
								for (var i = 0; i < kendoGrid.columns.length; i++) {
									var check = true;
									if (listRemove !== null) {
										for (var j = 0; j < listRemove.length; j++) {
											if (kendoGrid.columns[i].title == listRemove[j].title) {
												check = false;
											}
										}
									}

									if (check) {

										title.push(kendoGrid.columns[i].title);

										field.push(kendoGrid.columns[i])
									}
								}

								exportExcel(title, buildDataExport(data, field,
										listConvert), FileName);
							}

							function buildDataExport(data, filed, listConvert) {
								// Row content
								var rData = [];
								$
										.each(
												data,
												function(index, value) {
													var objJson = JSON
															.parse(JSON
																	.stringify(value));
													var item = {
														cells : []
													};
													for (var i = 0; i < filed.length; i++) {
														var objadd = {};
														var check = false;
														var textAlign = (filed[i].attributes.style
																.split(":")[1])
																.replace(";",
																		"");
														if (listConvert != null) {
															for (var j = 0; j < listConvert.length; j++) {

																if (filed[i].field == listConvert[j].field) {
																	objadd.value = listConvert[j].data[objJson[filed[i].field]];
																	objadd.borderBottom = {
																		color : "#000000",
																		size : 1
																	};
																	objadd.borderTop = {
																		color : "#000000",
																		size : 1
																	};
																	objadd.borderRight = {
																		color : "#000000",
																		size : 1
																	};
																	objadd.borderLeft = {
																		color : "#000000",
																		size : 1
																	};
																	objadd.textAlign = textAlign;
																	objadd.fontFamily = "Times New Roman";
																	check = true;
																}
															}
														}

														if (check) {

														} else if (filed[i].field == "stt") {
															objadd.value = index + 1;
															objadd.borderBottom = {
																color : "#000000",
																size : 1
															};
															objadd.borderTop = {
																color : "#000000",
																size : 1
															};
															objadd.borderRight = {
																color : "#000000",
																size : 1
															};
															objadd.borderLeft = {
																color : "#000000",
																size : 1
															};
															objadd.textAlign = textAlign;
															objadd.fontFamily = "Times New Roman";
														} else {
															objadd.value = objJson[filed[i].field];
															objadd.borderBottom = {
																color : "#000000",
																size : 1
															};
															objadd.borderTop = {
																color : "#000000",
																size : 1
															};
															objadd.borderRight = {
																color : "#000000",
																size : 1
															};
															objadd.borderLeft = {
																color : "#000000",
																size : 1
															};
															objadd.textAlign = textAlign;
															objadd.fontFamily = "Times New Roman";
														}
														item.cells.push(objadd);
													}

													rData.push(item);
												});
								return rData;
							}

							function exportReport(obj) {
								var deferred = $q.defer();
								$http(
										{
											url : RestEndpoint.BASE_SERVICE_URL
													+ "reportServiceRest"
													+ "/exportPdf",
											dataType : 'json',
											method : 'POST',
											data : obj,
											headers : {
												"Content-Type" : "application/json"
											},
											responseType : 'arraybuffer',// THIS
										// IS
										// IMPORTANT
										})
										.success(
												function(data, status, headers,
														config) {
													if (headers('error')) {
														var obj1 = {};
														obj1.error = headers('error');
														deferred.resolve(obj1);
													} else {
														deferred.resolve(data);
													}

												}).error(function(data) {
											deferred.reject(data);
										});
								return deferred.promise;
							}

							function genCode(obj) {
								return Restangular.all(
										"commonServiceRest/genCode").post(obj);
							}

							function getCharFour(obj) {
								return Restangular.all(
										"commonServiceRest/getCharFour").post(
										obj);
							}

							function getCharThree(obj) {
								return Restangular.all(
										"commonServiceRest/getCharThree").post(
										obj);
							}

							function getCharOneTimes(obj) {
								return Restangular.all(
										"commonServiceRest/getCharOneTimes")
										.post(obj);
							}

							function getCharOneAmount(obj) {
								return Restangular.all(
										"commonServiceRest/getCharOneAmount")
										.post(obj);
							}

							function getCharTwoWeek(obj) {
								return Restangular.all(
										"commonServiceRest/getCharTwoWeek")
										.post(obj);
							}

							function getCharTwoMonth(obj) {
								return Restangular.all(serviceUrl2 + "/getCharTwoMonth").post(obj);
							}

							function signVoffice(List) {
								return Restangular.all(
										"reportServiceRest/signVoffice").post(
										List);
							}

							function getDataSign(obj) {
								return Restangular
										.all(
												"signVofficeRsServiceRest/signVoffice/getDataSign")
										.post(obj);
							}

							function getDetailSign(obj) {
								return Restangular
										.all(
												"signVofficeRsServiceRest/signVoffice/getDetail")
										.post(obj);
							}

							function downloadTemplate(fileName) {
								return Restangular
										.all(
												"commonServiceRest/exportExcelTemplate")
										.post(fileName);
							}
							function getallData(linkApi, obj) {
								return Restangular.all(linkApi).post(obj);
							}

							function resetData(caller, comboId) {
								caller.onClear(comboId);
							}
							function getUserRoles(obj) {
						    	return Restangular.all(serviceUrl+"/getUserRoles").post(obj);
					        }
							
							function checkRole(obj) {
						    	return Restangular.all(serviceUrl+"/checkRole").post(obj);
					        }
							function insertLSDN(obj) {
						    	return Restangular.all(serviceUrl1+"/insertLSDN").post(obj);
					        }
							
							

						} ]);
