
angular.module('MetronicApp').factory('findConstruction30Service', ['$http', '$q', 'RestEndpoint', 'Restangular', 'Constant', '$rootScope',
	function ($http, $q, RestEndpoint, Restangular, Constant, $rootScope) {

		//    var service = RestEndpoint.C_APPROVAL_ADVANCE_REQUEST_URL;
		var factory = {
			goTo: goTo,
			findByConstructionId: findByConstructionId,
			getAllandSearch: getAllandSearch,
			openPartners: openPartners,
			openProject: openProject,
			setItem: setItem,
			getItem: getItem
		};
		var SUGGGET_ADPAYMENT = {
			key: 'SUGGGET_ADPAYMENT',
			title: 'Đề nghị tạm ứng',
			templateUrl: 'erp/suggetadpayment/suggetAdPayment.html',
			lazyLoadFiles: [
				'erp/suggetadpayment/suggetAdPaymentController.js',
				'erp/suggetadpayment/suggetAdPaymentService.js',
			]
		};
		return factory;
		/* Handle action client on a menu item */
		function goTo(menuKey) {
			var template = SUGGGET_ADPAYMENT;
			setArParams(menuKey);
			setIdCheckbox(menuKey);
			setApParams(menuKey);
			postal.publish({
				channel: "Tab",
				topic: "open",
				data: template
			});
		}
		function setIdCheckbox(key) {
			switch (key) {
				case "AR_INVOICE_HEADER":
					$rootScope.nameCheckbox = "isSyncRecord";
					$rootScope.nameCheckbox1 = "isDetailSync";
					$rootScope.nameCheckbox2 = "isEstimate";
					break;
				case "AR_DTTKDS":
					$rootScope.nameCheckbox = "isSyncRecord1";
					$rootScope.nameCheckbox1 = "isDetailSync1";
					$rootScope.nameCheckbox2 = "isEstimate1";
					break;
				case "AR_POSTPAID":
					$rootScope.nameCheckbox = "isSyncRecord2";
					$rootScope.nameCheckbox1 = "isDetailSync2";
					$rootScope.nameCheckbox2 = "isEstimate2";
					break;
			}
		}

		function setArParams(key) {
			switch (key) {
				case "AR_INVOICE_HEADER":
					$rootScope.invoiceDocTypeId = 8;
					break;
				case "AR_DTTKDS":
					$rootScope.invoiceDocTypeId = 18;
					break;
				case "AR_POSTPAID":
					$rootScope.invoiceDocTypeId = 19;
					break;
				default:
			}
		}

		function setApParams(key) {
			switch (key) {
				case "AP_BANK_PAYMENT_BILL":
					$rootScope.cdocumentTypeId = '4';
					break;
				case "AP_CASH_PAYMENT_BILL":
					$rootScope.cdocumentTypeId = '5';
					break;
				default:
			}
		}
		function setItem(item) {
			this.item = item;
			console.log("run  setItem");
		}
		function getItem() {
			var deferred = $q.defer();
			deferred.resolve(this.item);
			return deferred.promise;
		}


		function getAllandSearch(criteria) {
			return Restangular.all(RestEndpoint.CONSTR_CONSTRUCTIONS_SERVICE_URL + "/getAllandSearch").post(criteria);
		}
		function openPartners() {
			return Restangular.all(RestEndpoint.CAT_PARTNERS_SERVICE_URL).getList();
		}
		function openProject() {
			return Restangular.all(RestEndpoint.PROJ_INVEST_PROJECT_SERVICE_URL).getList();
		}

		function findByConstructionId(constructionId) {
			return Restangular.one(RestEndpoint.CONSTR_CONSTRUCTIONS_SERVICE_URL + "/" + constructionId).get();
		}

	}]);
