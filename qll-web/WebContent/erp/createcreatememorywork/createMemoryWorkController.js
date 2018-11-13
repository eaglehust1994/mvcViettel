(function() {
	'use strict';

	var controllerId = 'suggetAdPayment';

	angular.module('MetronicApp').controller(controllerId,
		suggetAdPayment);

	/* @ngInject */
	function suggetAdPayment($scope, RestEndpoint,
		$rootScope, $timeout, PopupConst, Constant,
		kendoConfig, gettextCatalog,
		$kWindow, CommonService,$q,suggetAdPaymentService) {
		var vm = this;
		vm.hideAButton = hideAButton;
        vm.save = save;
        vm.haveInDB = false;
        vm.adPayment={
        defaultSortField:"",
           constructId:"1",
           advancePaymentProposalId:"",
           code:"",
           receiveDepartment:"",
           advancePaymentNumber:"",
           advancePaymentPercent:"",
           advancePaymentAmount:"",
           tranferToText:"",
           createdDate:"",
           createdUserId:"",
           approvalDate:"",
           statusCa:"",
           companyName:"",
           accountNo:"",
           bankName:"",
           requestDate:"",
           isActive:"",
       }

    /**
     * hàm đổi dữ liệu vào form khi mở màn hình lên sử dụng CONSTRUCT_ID
     * @return {[undefined]} hàm không trả về gì
     */
     function fillDataToForm() {
       suggetAdPaymentService.getAdPaymentByConstructId(1).then(function(data){
        vm.adPayment =data;
        if(data.advancePaymentProposalId == null){
            vm.haveInDB = false;
        }else{
            vm.haveInDB = true;
        }
    },function(error){
        toastr.error("thêm mới thất bại");
    });
   }

   fillDataToForm();

/**
 * hàm lưu dữ liệu từ form , nếu dữ liệu có trên DB thì update còn lai thì thêm mới  
 * @return {[type]} [description]
 */
   function save() {
    if(vm.haveInDB){
        // console.log(JSON.stringify(vm.adPayment));
        vm.adPayment = new adPaymentConstruct(vm.adPayment);
      suggetAdPaymentService.updateAdPayment(vm.adPayment).then(function(success){
      toastr.success("Cập nhật thành công");
      },function(error){
          toastr.error("Cập nhật thất bại");
      });
    }else{
        // console.log(vm.adPayment);
         vm.adPayment = new adPaymentConstruct(vm.adPayment);
         suggetAdPaymentService.createAdPayment(vm.adPayment).then(function(success){
         toastr.success("Lưu thành công");
      },function(error){
          toastr.error("Lưu thất bại");
      });
    }
   }


/**
 * function hideAButton() để ấn những nút trên thành header bar bị thừa
 * @return {undefined} hàm không trả về gì cả
 */
 function hideAButton(){
 	var mapHideButton = new Map();
 	mapHideButton.set("Xóa nhiều","");
 	mapHideButton.set("Sửa","");
 	$('#topBarContract').find('div').each(function(){
 		var nameHideButton =$(this).attr('uib-tooltip');
 		if(mapHideButton.has(nameHideButton)){
 			var hideButton = $(this).closest("button");
 			hideButton.addClass("ng-hide");
 			if(hideButton.hasClass("border-radius-right")){
 				hideButton.prev().addClass("border-radius-right");
 			}else{
 				if(hideButton.hasClass("border-radius-left")){
 					hideButton.next().addClass("border-radius-left");
 				}
 			}
 		}
 	})
 }

/**
 * hàm khởi tạo cho đối tượng adpayment
 * @param  {adPayment} adPaymentObj đối tượng adpayment của server trả về có thừa một số trường
 * @return {adPayment}              trả về đối tượng adPayment chuẩn đã được delete mộ số trường bị thừa
 */
  function adPaymentConstruct(adPaymentObj) {
      delete adPaymentObj.fwmodelId;
      return adPaymentObj;
  }

}

})();