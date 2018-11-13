angular.module('MetronicApp').directive('fileInput',function($window,$rootScope,RestEndpoint, CommonService,$timeout) {
							return {
								restrict : 'AE',
//								require : 'ngModel',
								scope : {
									listFileType : "@",
									size : '=',
									model : '=',
									caller : '=',
									dataFile : '=',
									req : "@",
									msg : "@",
									iddiv : "@",
									idlable : "@",
									disabled : '=',
									inputId: "@",
									modelLabel: "@",
								},
								template : '<div class="form-group col-md-6" id="{{iddiv}}">'
										+ '<label id="{{idlable}}" class="col-md-4 control-label {{req}}">{{modelLabel}}</label>'
										+ '<div class="col-md-8">'
										+ '<input type="file" id="{{inputId}}" name="{{inputId}}"  ng-model="model" data-required-msg="{{msg}}">'
										+ '<span data-for="{{inputId}}" class="k-invalid-msg"></span>'
										+ '<span id="type" class="k-invalid-msg" style="color: red;" >File không đúng định dạng</span>'
										+ '<span id="size"   class="k-invalid-msg" style="color: red;">File tải vượt quá dung lượng cho phép</span>'
										+ '<span id="size0"  class="k-invalid-msg" style="color: red;">File tải lên không có dữ liệu</span>'
										+ '</div>' + '</div>',
								link : function($scope, el, attr, ctrl) {
									var reader = new $window.FileReader();
									$("#type").hide();
									$("#size0").hide();
									$("#size").hide();
									function checkSizeFile(File) {
										if (File.size === 0) {
											$("#size0").show();
										} else if ($scope.size <= File.size) {
											$("#size").show();
										} else {
											readerFile(File);
										}
									}

									function checkTypeFile(File) {
										var fileType = attr.listFileType
												.split(',');
										;
										var fileName = File.name;
										var type = fileName.substr(fileName
												.lastIndexOf('.') + 1);
										var type = fileName.substr(fileName
												.lastIndexOf('.') + 1);
										if (fileType.indexOf(type) !== -1) {
											checkSizeFile(File);
										} else {
											$("#type").show();
										}
									}
									
									function readerFile(File){
										reader.readAsBinaryString(File);
										reader.onload = function (e) {
											var data = e.target.result;
											/* Call XLSX */
											var workbook = XLSX.read(data, { type: "binary" });
											/* DO SOMETHING WITH workbook HERE */
											var first_sheet_name = workbook.SheetNames[0];
											/* Get worksheet */
											var worksheet = workbook.Sheets[first_sheet_name];
											var array = XLSX.utils.sheet_to_json(worksheet, { raw: true });
											var headerNames = XLSX.utils.sheet_to_json(worksheet, { header: 1 })[0];
												if(array.length !==0){
													 $timeout(function() {
														 $scope.model.data=array;
														 $scope.model.hederList=headerNames;
														 $scope.model.file=File;
											              });
													
												} else {
													$("#size0").show();
												}
										};
									}
									
									
									el.bind('change', function(e) {
										$("#type").hide();
										$("#size0").hide();
										$("#size").hide();
										var File = e.target.files[0];
										 checkTypeFile(File);
									});
								}
							};
						});

// angular.module('MetronicApp').directive('validFile', function () {
// return {
// require: 'ngModel',
// link: function (scope, elem, attrs, ngModel) {
// var validFormats = ['jpg','jpeg','png'];
// elem.bind('change', function () {
// validImage(false);
// scope.$apply(function () {
// ngModel.$render();
// });
// });
// ngModel.$render = function () {
// ngModel.$setViewValue(elem.val());
// };
// function validImage(bool) {
// ngModel.$setValidity('extension', bool);
// }
// ngModel.$parsers.push(function(value) {
// var ext = value.substr(value.lastIndexOf('.')+1);
// if(ext=='') return;
// if(validFormats.indexOf(ext) == -1){
// return value;
// }
// validImage(true);
// return value;
// });
// }
// };
// });
