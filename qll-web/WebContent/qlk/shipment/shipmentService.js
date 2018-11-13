angular.module('MetronicApp').factory(
		'shipmentService',
		[
				'$http',
				'$q',
				'RestEndpoint',
				'Restangular',
				'$kWindow',
				function($http, $q, RestEndpoint, Restangular, $kWindow) {
					var serviceUrl = RestEndpoint.SHIPMENT_URL;
					var factory = {
						createShipment : createShipment,
						remove : remove,
						updateShipment : updateShipment,
						doSearch : doSearch,
						setItem : setItem,
						getItem : getItem,
						getReasonDelete : getReasonDelete,
						doSearchFile : doSearchFile,
						getFileDrop : getFileDrop,
						saveFileSERVICE : saveFileSERVICE,
						updateFileSERVICE : updateFileSERVICE,
						deleteFileSERVICE : deleteFileSERVICE,
						getForExportGrid : getForExportGrid
					};

					return factory;

					function setItem(data) {
						item = data;
					}

					function getItem() {
						return item;
					}

					function createShipment(obj) {
						return Restangular.all(serviceUrl + "/add").post(obj);
					}

					function remove(obj) {
						return Restangular.all(serviceUrl + "/remove").post(obj);
					}

					function updateShipment(obj) {
						return Restangular.all(serviceUrl + "/update").post(obj);
					}

					function doSearch(obj) {
						return Restangular.all(serviceUrl + "/doSearch").post(obj);
					}
					
					function getReasonDelete(obj) {
						return Restangular.all(RestEndpoint.REASON_URL + "/getForComboBox").post(obj);
					}
					
					function doSearchFile(obj) {
						return Restangular.all(RestEndpoint.ATTCHMENT_URL + "/doSearch").post(obj);
					}
					
					function getFileDrop(){
						return Restangular.all(RestEndpoint.APP_PARAM_SERVICE_URL +"/getFileDrop").getList();
					}
					
					function saveFileSERVICE(obj) {
						return Restangular.all(RestEndpoint.ATTCHMENT_URL + "/add").post(obj);
					}
					
					function updateFileSERVICE(obj) {
						return Restangular.all(RestEndpoint.ATTCHMENT_URL + "/update").post(obj);
					}
					
					function deleteFileSERVICE(obj) {
						return Restangular.all(RestEndpoint.ATTCHMENT_URL + "/delete").post(obj);
					}
					
					function getForExportGrid(obj) {
						return Restangular.all(RestEndpoint.ORDER_SERVICE_URL + "/doSearchImportRequirementForExportGrid").post(obj);
					}
				} ]);
