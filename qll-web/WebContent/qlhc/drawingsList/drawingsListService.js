angular
		.module('MetronicApp')
		.factory(
				'drawingsListService',
				[
						'$http',
						'$q',
						'RestEndpoint',
						'Restangular',
						'$kWindow',
						function($http, $q, RestEndpoint, Restangular) {

							var factory = {

								openCompletionDrawingForm : openCompletionDrawingForm,
								removeCompletionDrawing : removeCompletionDrawing,
								setcompletionDrawingId : setcompletionDrawingId,
								getcompletionDrawingId : getcompletionDrawingId,
								setconstructId : setconstructId,
								getconstructId : getconstructId,
								downloadFileZip : downloadFileZip,
								createCompletionDrawing : createCompletionDrawing,
								updateCompletionDrawing : updateCompletionDrawing,
								getEmployeeNameAndIdByRole : getEmployeeNameAndIdByRole,
								getCompletionDrawingFolder : getCompletionDrawingFolder,
								setItem : setItem,
								getItem : getItem,
								setData : setData,
								getData : getData,
								getRoleId : getRoleId,
								getListEmployeeByRole : getListEmployeeByRole,
								removeUtilAttachedDocuments : removeUtilAttachedDocuments,
								removeConstrRecordMap : removeConstrRecordMap,
								updateIsActive : updateIsActive,
								deleteFile : deleteFile,
								getListEmployeeByConstruction : getListEmployeeByConstruction

							};

							return factory;

							// /////////////////////////////////////////////////////////////////////
							function setItem(item) {
								this.item = item;
							}
							function getItem() {
								return this.item;
							}
							function setData(data) {
								this.data = data;
							}
							function getData() {
								return this.data;
							}
							function setcompletionDrawingId(completionDrawingId) {
								this.completionDrawingId = completionDrawingId;
							}
							function getcompletionDrawingId() {
								return this.completionDrawingId;
							}
							function setconstructId(constructId) {
								this.constructId = constructId;
							}
							function getconstructId() {
								return this.constructId;
							}

							function openCompletionDrawingForm(item) {
								return Restangular
										.all(
												RestEndpoint.COMPLETION_DRAWINGS_SEARCH_URL)
										.post(item);
							}

							function removeCompletionDrawing(id) {
								return Restangular
										.one(
												RestEndpoint.COMPLETION_DRAWINGS_SERVICE_URL,
												id).remove();
							}

							function removeUtilAttachedDocuments(id) {
								return Restangular
										.one(
												RestEndpoint.UTIL_ATTACHED_DOCUMENTS_SERVICE_URL,
												id).remove();
							}
							function removeConstrRecordMap(id) {
								return Restangular.one(
										RestEndpoint.CONSTR_RE_MAP_SERVICE_URL,
										id).remove();
							}
							function downloadFileZip(item) {
								return Restangular
										.all(
												RestEndpoint.COMPLETION_DRAWINGS_SERVICE_URL
														+ "/downloadFile/")
										.post(item);
							}

							function updateIsActive(id) {
								return Restangular
										.all(
												RestEndpoint.COMPLETION_DRAWINGS_SERVICE_URL
														+ "/updateIsActive/")
										.post(id);
							}
							// //////////////////////////////create///////////////////////
							function setcompletionDrawingId(completionDrawingId) {
								this.completionDrawingId = completionDrawingId;
							}
							function getcompletionDrawingId() {
								return this.completionDrawingId;
							}
							function getEmployeeNameAndIdByRole(obj) {
								return Restangular.all(
										RestEndpoint.CAT_EMPLOYEE_NAME_AND_ID)
										.post(obj);
							}

							function getCompletionDrawingFolder() {
								return Restangular
										.one(
												RestEndpoint.COMPLETION_DRAWINGS_FOLDER_URL)
										.get();
							}
							function getRoleId() {
								return Restangular.all(
										RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
												+ "/getRoleId").getList();
							}
							function getListEmployeeByConstruction(item) {
								return Restangular
										.all(
												RestEndpoint.CAT_EMPLOYEE_SERVICE_URL
														+ "/getListEmployeeByConstruction")
										.post(item);
							}
							function deleteFile(path) {
								return Restangular
										.all(
												RestEndpoint.COMPLETION_DRAWINGS_SERVICE_URL
														+ "/deleteDraw").post(
												path);
							}
							// add, update
							function createCompletionDrawing(completionDrawing) {
								return Restangular
										.all(
												RestEndpoint.COMPLETION_DRAWINGS_SERVICE_URL
														+ "/addList/").post(
												completionDrawing);
							}
							function updateCompletionDrawing(completionDrawing) {
								return Restangular
										.all(
												RestEndpoint.COMPLETION_DRAWINGS_SERVICE_URL + "/put")
										.post(completionDrawing);
							}

							function getListEmployeeByRole(criteria) {
								return Restangular
										.all(
												RestEndpoint.CAT_EMPLOYEE_BY_CONSTRID_SERVICE_URL)
										.post(criteria);
							}

						} ]);