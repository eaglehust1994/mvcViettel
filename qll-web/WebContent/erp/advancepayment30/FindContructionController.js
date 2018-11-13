(function () {
      'use strict';

      var controllerId = 'findConstruction30Controller';

      angular.module('MetronicApp').controller(controllerId,
            findConstruction30Func);

      /* @ngInject */
      function findConstruction30Func($scope, $rootScope, $timeout, Constant, gettextCatalog, kendoConfig, ProposalEvaluation, catProvincesService, catConstrTypeService, $kWindow, CommonService, PopupConst, Restangular, RestEndpoint, findConstruction30Service) {
            var vm = this;
            vm.validatorOptions = kendoConfig.get('validatorOptions');
            vm.showSearch = false;
            vm.changeSite = changeSite;
            vm.provinces = [];
            vm.constrTypes = [];

            // set model empty -----------------------------------------------------------------
            function getEmptyDataModel() {
                  var objReturn = {

                  }


                  $(".k-invalid-msg").hide();
                  return objReturn;
            }
            vm.item = {
                  partnerName: '',
                  contractCode: '',
                  investProjectName: '',
                  constrtCode: '',
                  constrtName: '',
                  constrType: '',
                  provinceId: '',
                  constructId: '',
                  constrtAddress: ''
            }

            vm.criteria = {
                  partnerName: '',
                  contractCode: '',
                  investProjectName: '',
                  constrtCode: '',
                  constrtName: '',
                  constrType: '',
                  provinceId: '',
                  acceptStartDate: ''
            };

            // set search model empty -----------------------------------------------------------------
            function getSearchEmptyDataModel() {
                  return {

                  };
            }
            function changeSite() {
                  console.log("aaa");
                  findConstruction30Service.goTo();
            }
            vm.creatmemorywork = function () {
                  vm.item;
                  if (vm.item.constructId == '') {
                        toastr.warning("Bạn chưa chọn bản ghi nào");
                        return;
                  }
                  goTo('CREATE_MEMORY_WORK');
            }
            vm.listconstructionorganizationplan = function () {
                  vm.item;
                  if (vm.item.constructId == '') {
                        toastr.warning("Bạn chưa chọn bản ghi nào");
                        return;
                  }
                  goTo('LIST_WORK_ORGANIZATION_PLAN');
            }

            vm.acceptance = function () {
                  vm.item;
                  if (vm.item.constructId == '') {
                        toastr.warning("Bạn chưa chọn bản ghi nào");
                        return;
                  }
                  goTo('ACCEPTANCE_OF_CONSTRUCTION_JOBS');
            }

            vm.listReportResult = function () {
                  vm.item;
                  if (vm.item.constructId == '') {
                        toastr.warning("Bạn chưa chọn bản ghi nào");
                        return;
                  }
                  goTo('List_Report_Result');
            }


            vm.goTo = goTo;

            /* Handle action client on a menu item */
            function goTo(menuKey) {

                  var hasPerm = true;

                  if (hasPerm) {
                        var template = Constant.getTemplateUrl(menuKey);

                        postal.publish({
                              channel: "Tab",
                              topic: "open",
                              data: template
                        });
                  } else {
                        //toastr.error(gettextCatalog.getString("Tài khoản đăng nhập hiện tại không được phép truy cập vào chức năng này!"));
                  }

            }

            initFormData();

            function initFormData() {

                  catConstrTypeService.getAll().then(function (d) {
                        vm.constrTypes = d;
                  }, function (errResponse) {
                        console.error('Error while fetching provinces');
                  });

                  catProvincesService.getAll().then(function (d) {
                        vm.provinces = d;
                  }, function (errResponse) {
                        console.error('Error while fetching provinces');
                  });
            }

            // Su kien cho nut tim kiem
            vm.search = search;
            function search() {
                  vm.showDetail = false;
                  if (!vm.showSearch) {
                        vm.showSearch = true;
                        vm.criteria = getSearchEmptyDataModel();
                  } else {
                        vm.showSearch = false;
                  }
            }
            vm.resetSearch = function () {
                  vm.criteria = getSearchEmptyDataModel();
                  ProposalEvaluation.getAllandSearch(vm.criteria).then(
                        function (d) {
                              refreshGrid(d.plain());
                        },
                        function (errResponse) {
                              console.error('Error while fetching minout');
                        }
                  );
            }
            vm.doSearch = function () {
                  console.log("aaa");
                  ProposalEvaluation.getAllandSearch(vm.criteria).then(
                        function (d) {
                              fillDataTable(d);
                              refreshGrid(d.plain());
                        },
                        function (errResponse) {
                              console.error('Error while fetching minout');
                        }
                  );
            }


            function chkSelectAll(item) {
                  var grid = vm.findConstruction30Service;
                  chkSelectAllBase(vm.chkAll, grid);
            }

            function detail() {
                  if (vm.findConstruction30Service.select().length > 0) {
                        vm.showDetail = true;
                  } else {
                        toastr.warning(gettextCatalog.getString("Bạn cần chọn một bản ghi trước"));
                  }
            }



            // Xóa nhiều
            function multiDelete() {
                  var selectedRow = [];
                  var grid = vm.findConstruction30Service;
                  grid.table.find("tr").each(function (idx, item) {
                        var row = $(item);
                        var checkbox = $('[name="gridcheckbox"]', row);

                        if (checkbox.is(':checked')) {
                              // Push id into selectedRow
                              var tr = grid.select().closest("tr");
                              var dataItem = grid.dataItem(item);
                              console.log('dataItem ----');
                              console.log(dataItem.minoutId);
                              selectedRow.push(dataItem.minoutId);
                        }
                  });

                  if (selectedRow.length == 0) {
                        toastr.warning(message.recordRequired);
                        return;
                  }

                  console.log(selectedRow);
                  if (confirm('Xác nhận xóa')) {
                        ProposalEvaluation.deleteList(selectedRow).then(function () {
                              toastr.success(message.deleteSuccess);
                              initFormData();
                        }, function (errResponse) {
                              if (errResponse.status == 302) {
                                    toastr.error("Bản ghi đang được sử dụng");
                              } else {
                                    toastr.error(message.deleteError);
                              }
                        });
                  }
            }




            // Xoa thong tin 
            vm.remove = remove;
            function remove() {

            }

            // Sư kien cho nut luu thong tin chung
            vm.save = function save() {

            }


            // Su kien cho nut lam moi
            vm.refresh = refresh;
            function refresh() {
                  if (vm.showDetail) {
                        vm.shipmentDetail = getEmptyDataModel();
                  }
                  else {
                        ProposalEvaluation.doSearch(vm.criteria).then(
                              function (d) {
                                    refreshGrid(d.plain());
                              },
                              function (errResponse) {
                                    console.error('Error while fetching minout');
                              }
                        );
                  }
            }

            // Hien thi danh sach 
            function fillDataTable(data) {
                  vm.gridOptions = kendoConfig.getGridOptions({
                        autoBind: true,
                        dataSource: data,
                        change: onChange,
                        detailExpand: function (e) {

                        },
                        noRecords: true,
                        messages: {
                              noRecords: gettextCatalog.getString("There is no data on current page")
                        },
                        columns: [{
                              title: "<input type='checkbox' name='gridchkselectall' ng-click='vm.chkSelectAll();' ng-model='vm.chkAll' />",
                              template: "<input type='checkbox' name='gridcheckbox' />",
                              width: 35
                        }, {
                              title: gettextCatalog.getString("Tạm ứng 30%"),
                              template: '<button class="btn btn-primary" style="margin-left: 35%;"  ng-click="vm.changeSite(\'SUGGGET_ADPAYMENT\')"><span class="glyphicon glyphicon-forward"></span></button>',
                              width: 150
                        },
                        {
                              title: gettextCatalog.getString("Tỉnh thành"),
                              field: "provinceName",
                              width: 200
                        },
                        {
                              title: gettextCatalog.getString("Dự án"),
                              field: "investProjectName",
                              width: 200
                        },
                        {
                              title: gettextCatalog.getString("Số hợp đồng"),
                              field: "contractCode",
                              width: 150
                        },
                        {
                              title: gettextCatalog.getString("Mã công trình"),
                              field: "constrtCode",
                              width: 150
                        },
                        {
                              title: gettextCatalog.getString("Tên công trình"),
                              field: "constrtName",
                              width: 150
                        },
                        {
                              title: gettextCatalog.getString("Loại công trình"),
                              field: "constrTypeName",
                              width: 150
                        },
                        {
                              title: gettextCatalog.getString("Trạng thái công trình"),
                              field: "statusName",
                              width: 170
                        }
                        ]
                  });
            }

            // Su kien khi click vao mot hang cua danh sach 
            vm.onChange = onChange;
            function onChange() {
                  if (vm.findConstruction30Service.select().length > 0) {
                        var tr = vm.findConstruction30Service.select().closest("tr");
                        var dataItem = vm.findConstruction30Service.dataItem(tr);
                        vm.item = dataItem;
                        findConstruction30Service.setItem(vm.item);
                        $rootScope.$broadcast("ConstructionData30.detail", vm.item);

                  }
            }

            // Lam moi lai danh sach 
            function refreshGrid(d) {
                  var grid = vm.findConstruction30Service;
                  if (grid) {
                        grid.dataSource.data(d);
                        grid.refresh();
                  }
            }

            vm.onRowChange = onRowChange;
            function onRowChange(dataItem, popupId) {
                  switch (popupId) {
                        case 'Partner':
                              vm.criteria.partnerName = dataItem.partnerName;
                              break;
                        case 'Project':
                              vm.criteria.investProjectName = dataItem.name;
                              break;

                  }
            }
            vm.onSave = onSave;
            function onSave(popupId) {
            }
            vm.onCancel = onCancel;
            function onCancel(popupId) {
            }




            vm.showPartnerNameForm = showPartnerNameForm;
            function showPartnerNameForm() {
                  ProposalEvaluation.openPartners($scope).then(function (result) {
                        var templateUrl = 'views/popup/gridViewPartner.html';
                        var title = 'Đối tác';
                        vm.PartnerGridOptions = kendoConfig.getGridOptions({
                              autoBind: true,
                              dataSource: result.plain(),
                              change: onChange,
                              noRecords: true,
                              messages: {
                                    noRecords: gettextCatalog.getString("There is no data on current page")
                              },
                              columns: [{
                                    title: gettextCatalog.getString("Tên đối tác"),
                                    field: "partnerName",
                                    width: 100,
                              }, {
                                    title: gettextCatalog.getString("Mã trạm dự kiến"),
                                    field: "stationCodeExpected",
                                    width: 100
                              }, {
                                    title: gettextCatalog.getString("Địa chỉ"),
                                    field: "address",
                                    width: 100
                              }, {
                                    title: gettextCatalog.getString("Điện thoại"),
                                    field: "phone",
                                    width: 100
                              }, {
                                    title: gettextCatalog.getString("Fax"),
                                    field: "fax",
                                    width: 100
                              }, {
                                    title: gettextCatalog.getString("Mã số dự kiến"),
                                    field: "taxCode",
                                    width: 100
                              }]
                        });

                        CommonService.populateDataToGrid(templateUrl, title, vm.PartnerGridOptions, vm, PopupConst.ProposalEvaluation['Partner']);



                  });
            }

            vm.showProjectNameForm = showProjectNameForm;
            function showProjectNameForm() {
                  ProposalEvaluation.openProject($scope).then(function (result) {
                        var templateUrl = 'views/popup/gridViewProject.html';
                        var title = 'Dự án';
                        vm.ProjectGridOptions = kendoConfig.getGridOptions({
                              autoBind: true,
                              dataSource: result.plain(),
                              change: onChange,
                              noRecords: true,
                              messages: {
                                    noRecords: gettextCatalog.getString("There is no data on current page")
                              },
                              columns: [{
                                    title: gettextCatalog.getString("Mã dự án"),
                                    field: "code",
                                    width: 100,
                              }, {
                                    title: gettextCatalog.getString("Tên dự án"),
                                    field: "name",
                                    width: 100
                              }, {
                                    title: gettextCatalog.getString("Tình trạng dự án"),
                                    field: "statusCode",
                                    width: 100
                              }]
                        });

                        CommonService.populateDataToGrid(templateUrl, title, vm.ProjectGridOptions, vm, PopupConst.ProposalEvaluation['Project']);
                  });
            }




      }

})();