(function () {
  'use strict';

  var controllerId = 'suggetAdPayment';

  angular.module('MetronicApp').controller(controllerId,
    suggetAdPayment);

  /* @ngInject */
  function suggetAdPayment($scope, RestEndpoint,
    $rootScope, $timeout, PopupConst, Constant,
    kendoConfig, gettextCatalog,
    $kWindow, CommonService, $q, suggetAdPaymentService, findConstruction30Service, ProposalEvaluation) {
    var vm = this;
    // vm.hideAButton = hideAButton;
    vm.validatorOptions = kendoConfig.get('validatorOptions');

    vm.save = save;
    vm.haveInDB = false;

    vm.adPayment = {
      defaultSortField: "",
      constructId: "1",
      advancePaymentProposalId: "",
      code: "",
      receiveDepartment: "",
      advancePaymentNumber: "",
      advancePaymentPercent: "",
      advancePaymentAmount: "",
      tranferToText: "",
      createdDate: "",
      createdUserId: "",
      approvalDate: "",
      statusCa: "",
      companyName: "",
      accountNo: "",
      bankName: "",
      requestDate: "",
      isActive: "",
      contractCode: '',
      investProjectName: '',
      orinCreatedDate: '',
      orinApprovalDate: '',
      orinRequestDate: ''
    }
    vm.adPaymentModify = {
      defaultSortField: "",
      constructId: "1",
      advancePaymentProposalId: "",
      code: "",
      receiveDepartment: "",
      advancePaymentNumber: "",
      advancePaymentPercent: "",
      advancePaymentAmount: "",
      tranferToText: "",
      createdDate: "",
      createdUserId: "",
      approvalDate: "",
      statusCa: "",
      companyName: "",
      accountNo: "",
      bankName: "",
      requestDate: "",
      isActive: "",
      contractCode: '',
      investProjectName: '',
      orinCreatedDate: '',
      orinApprovalDate: '',
      orinRequestDate: ''
    }


    /** hàm chuyển kiểu dữ liệu datetime từ millisecon sang ngày/tháng/năm và ngược lại */
    function chanceDateToString(stringDate, mode) { // linhnn
      if (stringDate != undefined && mode == 'M_TO_DATE') {
        var date = new Date(stringDate);
        //  var month=  
        return (date.getDate() + '/' + (date.getMonth()+1) + '/' + date.getFullYear());
      }
      if (stringDate != undefined && mode == 'DATE_TO_M') {
        var arrDate = stringDate.split("/");
        var date = new Date(arrDate[2], arrDate[1]-1, arrDate[0], 0, 0, 0, 0);
        return date.getTime();
      }
    }


    /**
     * 
     * hàm đổi dữ liệu vào form khi mở màn hình lên sử dụng CONSTRUCT_ID. đồng thời lấy dữ liệu nhận từ view tìm kiếm để hiển thị thông tin cần điền sắn
     * @return {[undefined]} hàm không trả về gì
     */

    function fillDataToForm(moreData) {
      console.log(JSON.stringify(moreData));
      suggetAdPaymentService.getAdPaymentByConstructId(moreData.constructId).then(function (data) {
        vm.adPayment = angular.copy(data);
        console.log("data nhan " + JSON.stringify(data));
        console.log(vm.adPayment.createdDate);
        vm.adPayment.createdDate = chanceDateToString(data.createdDate, 'M_TO_DATE');
        vm.adPayment.approvalDate = chanceDateToString(data.approvalDate, 'M_TO_DATE');
        vm.adPayment.requestDate = chanceDateToString(data.requestDate, 'M_TO_DATE');
        vm.adPayment.constructId = moreData.constructId;
        console.log(vm.adPayment.requestDate);
        if (data.advancePaymentProposalId !== null) {
          vm.haveInDB = true;
        } else {
          vm.haveInDB = false;
        }
        console.log(JSON.stringify(vm.adPayment));
        if (moreData) {
          console.log('moreData co du lieu' + JSON.stringify(vm.adPayment));
          vm.adPayment.contractCode = moreData.contractCode;
          vm.adPayment.investProjectName = moreData.investProjectName;
          vm.adPayment.contractName = moreData.contractName;
          vm.adPayment.contract_total_value = moreData.contract_total_value;
          vm.adPayment.signed_date = chanceDateToString(moreData.signed_date, 'M_TO_DATE');

        }
      }, function (error) {
        toastr.error("thêm mới thất bại");
      });
    }

    /**
     * hàm lưu dữ liệu từ form , nếu dữ liệu có trên DB thì update còn lai thì thêm mới  
     * @return {[type]} [description]
     */
    function save() {
      if (vm.validator.validate()) {
        if (vm.haveInDB) {
          // console.log(JSON.stringify(vm.adPayment));
          // vm.adPaymentModify=vm.adPayment;
          vm.adPaymentModify = $.extend({}, vm.adPayment);
          vm.adPaymentModify = new adPaymentConstruct(vm.adPaymentModify);
          console.log(JSON.stringify(vm.adPaymentModify));
          suggetAdPaymentService.updateAdPayment(vm.adPaymentModify).then(function (success) {
            toastr.success("Cập nhật thành công");
          }, function (error) {
            toastr.error("Cập nhật thất bại");
          });
        } else {
          console.log("run add payment");
          vm.adPaymentModify = $.extend({}, vm.adPayment);
          vm.adPaymentModify = new adPaymentConstruct(vm.adPaymentModify);
          console.log(JSON.stringify(vm.adPaymentModify));
          suggetAdPaymentService.createAdPayment(vm.adPaymentModify).then(function (success) {
            toastr.success("Lưu thành công");
          }, function (error) {
            toastr.error("Lưu thất bại");
          });
        }
      }else{
        toastr.error("Điền đầy đủ thông tin");
      }
    }



    /**
     * function hideAButton() để ấn những nút trên thành header bar bị thừa
     * @return {undefined} hàm không trả về gì cả
     */
    function hideAButton() {
      var mapHideButton = new Map();
      mapHideButton.set("Xóa nhiều", "");
      mapHideButton.set("Sửa", "");
      mapHideButton.set("Sao chép", "");
      mapHideButton.set("Xóa", "");
      mapHideButton.set("Xem bảng/Chi tiết", "");
      mapHideButton.set("Làm mới", "");
      mapHideButton.set("In", "");
      mapHideButton.set("Attachment", "");
      mapHideButton.set("Đóng băng", "");
      mapHideButton.set("Export", "");
      mapHideButton.set("Import", "");
      mapHideButton.set("Previous", "");
      mapHideButton.set("Next", "");
      mapHideButton.set("Tìm Kiếm", "");
      mapHideButton.set("Làm lại", "");
      mapHideButton.set("Thêm mới", "");
      mapHideButton.set("Tìm kiếm", "");
      $('#topBarContract').find('div').each(function () {
        var nameHideButton = $(this).attr('uib-tooltip');
        if (mapHideButton.has(nameHideButton)) {
          var hideButton = $(this).closest("button");
          hideButton.addClass("ng-hide");
          if (hideButton.hasClass("border-radius-right")) {
            hideButton.prev().addClass("border-radius-right");
          } else {
            if (hideButton.hasClass("border-radius-left")) {
              hideButton.next().addClass("border-radius-left");
            }
          }
        }
        if (nameHideButton === "Lưu") {
          var button = $(this).closest("button");
          button.addClass("margin-left-button");
          button.removeClass("border-radius-left");
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
      delete adPaymentObj.contractCode;
      delete adPaymentObj.investProjectName;
      delete adPaymentObj.contractName;
      delete adPaymentObj.signed_date;
      delete adPaymentObj.contract_price_fc;

      adPaymentObj.createdDate = chanceDateToString(adPaymentObj.createdDate, 'DATE_TO_M');
      adPaymentObj.approvalDate = chanceDateToString(adPaymentObj.approvalDate, 'DATE_TO_M');
      adPaymentObj.requestDate = chanceDateToString(adPaymentObj.requestDate, 'DATE_TO_M');
      return adPaymentObj;
    }

    /**
     * nhận data từ view tìm kiếm công trình
     * @type {undefine}
     */
    vm.item = {};
    function getDataProposalEvaluation() {
      var deferred = $q.defer();
      var item = ProposalEvaluation.getItem();
      deferred.resolve(item);
      return deferred.promise;;
    }

    getDataProposalEvaluation().then(function (item) {
      fillDataToForm(item);
    });

    $scope.$on("ProposalEvaluation.detail", function (event, item) {
      if (item === undefined) {
        console.log("cant receive data");
      } else {
        fillDataToForm(item);
      }
      console.log(JSON.stringify(vm.item));
    });


    /**
     * hàm liên quan tới call back với popup
     * @param  {[type]} dataItem [description]
     * @param  {[type]} popupId  [description]
     * @return {[]}          [tên của hợp đồng hoặc dự án]
     */
    //  function onRowChange(dataItem, popupId) {
    //   switch (popupId){
    //     case 'Partner':
    //     vm.criteria.partnerName = dataItem.partnerName;
    //     break;
    //     case 'Project':
    //     vm.criteria.investProjectName = dataItem.name;
    //     break;

    //   }
    // }
    // vm.onSave = onSave;
    // function onSave(popupId) {
    // }
    // vm.onCancel = onCancel;
    // function onCancel(popupId) {      
    // }
    // /**
    //  * hàm thay bất popup đối tác
    //  * @type {[type]}
    //  */
    //  vm.showPartnerNameForm = showPartnerNameForm;
    //  function showPartnerNameForm() {
    //   ProposalEvaluation.openPartners($scope).then(function(result){
    //     var templateUrl = 'views/popup/gridViewPartner.html';
    //     var title = 'Đối tác'; 
    //     vm.PartnerGridOptions = kendoConfig.getGridOptions({
    //       autoBind: true,
    //       dataSource: result.plain(),
    //       change: onChange,
    //       noRecords: true,
    //       messages: {
    //         noRecords: gettextCatalog.getString("Không có kết quả nào")
    //       },
    //       columns: [{
    //         title: gettextCatalog.getString("Tên đối tác"),
    //         field: "partnerName",
    //         width: 100,
    //       }, {
    //         title: gettextCatalog.getString("Mã trạm dự kiến"),
    //         field: "stationCodeExpected",
    //         width: 100
    //       }, {
    //         title: gettextCatalog.getString("Địa chỉ"),
    //         field: "address",
    //         width: 100
    //       },{
    //         title: gettextCatalog.getString("Điện thoại"),
    //         field: "phone",
    //         width: 100
    //       }, {
    //         title: gettextCatalog.getString("Fax"),
    //         field: "fax",
    //         width: 100
    //       }, {
    //         title: gettextCatalog.getString("Mã số dự kiến"),
    //         field: "taxCode",
    //         width: 100
    //       }]
    //     });

    //     CommonService.populateDataToGrid(templateUrl, title, vm.PartnerGridOptions, vm, PopupConst.ProposalEvaluation['Partner']);



    //   });
    // }

    vm.checkPercent = function(){
      if (vm.adPayment.advancePaymentPercent > 100){
        
        $("span[data-for='advancePaymentPercent2']").show();
        $("#contract_total_value").addClass("ng-invalid ng-invalid-required");
        
      }
      else{
        $("span[data-for='advancePaymentPercent2']").hide();
         $("#contract_total_value").removeClass("ng-invalid ng-invalid-required");
      }
    }
  }
})();