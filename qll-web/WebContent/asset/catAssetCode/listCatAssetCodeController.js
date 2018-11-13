(function() {
	'use strict';

	String.prototype.format = function (arr) {
		var formatted = this;
		for (var i = 0; i < arr.length; i++) {
			var regexp = new RegExp('\\{' + i + '\\}', 'gi');
			formatted = formatted.replace(regexp, arr[i]);
		}
		return formatted;
	};

	function CatAssetCodeModel(parent, level, catAssetCodeService, gettextCatalog,Constant) {
		var caac = this;
		caac.parent = parent;
		caac.level = level;
		caac.catAssetCodeService = catAssetCodeService;
		caac.gettextCatalog = gettextCatalog;
		caac.displayList = [];
		caac.setCurrentLevel = function() {
			if (!caac.isLastElement()) {
				caac.parent.level = caac.level;
			}
		};
		caac.isThirdType = function() {
			return caac.level == 2;
		};
		caac.isSecondType = function() {
			return caac.level == 1;
		};

		caac.focusOnAdd = function() {
			$('#level_' + caac.level).focus();
		};
		caac.chooseItem = function(item) {
			if (item !== caac._currentItem) {
				caac.clearNext();
				item.reload().then(function() {
					caac._currentItem = item;
					if (!caac.isLastElement()) {
						caac.nextElement().refreshList();
					}
					caac.setCurrentLevel();
				});
			}
		};
		caac.isSelectedItem = function(item) {
			return caac._currentItem == item;
		};
		caac.getSelectedItem = function() {
			return caac._currentItem;
		};
		caac._add = false;
		caac.preAdd = function() {
			var parent = null;
			if (!caac.isFirstElement()) {
				parent = caac.previousElement().getSelectedItem();
				if (parent === null) {
					toastr.warning(gettextCatalog.getString('Bạn phải chọn {0}').format([caac.previousElement().name]));
					return;
				}
			}
			caac._add = true;
			caac.addingItem = {
				caacName: '',
				parent: parent,
				depreciationTime: null,
				isFixedAsset: false
			};
			setTimeout(caac.focusOnAdd, 100);
		};
		caac.isAdd = function() {
			return caac._add;
		};
		caac.cancelAdd = function() {
			caac._add = false;
		};
		caac.add = function() {
			catAssetCodeService.post('insert', {
				caacName: caac.addingItem.caacName,
				caacParentId: caac.addingItem.parent == null ? null : caac.addingItem.parent.originalItem.catAssetCodeId,
				depreciationTime: caac.addingItem.depreciationTime,
				useDuration:caac.addingItem.useDuration,
				isFixedAsset: caac.addingItem.isFixedAsset ? 1 : 0
			}).then(function(response) {
				toastr.success(gettextCatalog.getString('Thêm mới thành công'));
				caac.cancelAdd();
				caac.displayList.unshift(new CatAssetCodeItemModel(response, caac));
				if (caac.addingItem.parent !== null) {
					caac.addingItem.parent.originalItem.isUsing = true;
				}
			},function(err){
				 if(err.data.status==Constant.http.BUSINESS_ERROR){//Lỗi báo cáo
					 toastr.error(item.parent.gettextCatalog.getString('Lỗi khi gửi sang tài chính'));
                     //toastr.warning(gettextCatalog.getString(err.data.errorMessage));
                 }
				
			});
		};
		caac.isLastElement = function() {
			return caac.level + 1 >= caac.parent.list.length;
		};
		caac.isFirstElement = function() {
			return caac.level === 0;
		};
		caac.nextElement = function() {
			return caac.parent.list[caac.level + 1];
		};
		caac.previousElement = function() {
			return caac.parent.list[caac.level - 1];
		};
		caac.clear = function() {
			caac._currentItem = null;
			caac.displayList = [];
			caac.cancelAdd();
			if (!caac.isLastElement()) {
				caac.nextElement().clear();
			}
		};
		caac.clearNext = function() {
			if (!caac.isLastElement()) {
				caac.nextElement().clear();
			}
		};

		caac.refreshList = function() {
			caac.clear();
			var parent = null;
			if (!caac.isFirstElement()) {
				parent = caac.previousElement()._currentItem;
				if (parent === null || parent === undefined) {
					return;
				}
			}
			catAssetCodeService.post('searchPaginage',
				{
					caacLevel: caac.level + 1,
					caacParentId: parent ? parent.originalItem.catAssetCodeId : null,
					quickSearchQuery: caac.query,
					page: 0,
					size: 20,
					orderInfo: {
						attribute: 'caacCode',
						type: 'desc'
					}
				}
			).then(function(response) {
					console.log(response);
					caac.displayList = [];

					var cloneArray = [];
					angular.forEach(response.rows, function(item) {
						cloneArray.push(new CatAssetCodeItemModel(item, caac));
					});
					caac.displayList = cloneArray;
				});
		};
	}

	function CatAssetCodeItemModel(originalItem, parent) {
		var item = this;
		item.originalItem = originalItem;
		item.parent = parent;
		item._edit = false;
		item.reload = function() {
			return item.parent.catAssetCodeService.post('getDetail', {
				catAssetCodeId: item.originalItem.catAssetCodeId
			}).then(function(response) {
				item.originalItem = response;
			});
		};
		item.preEdit = function() {
			item._edit = true;

			item.editingItem = {
				caacName: item.originalItem.caacName,
				depreciationTime: item.originalItem.depreciationTime,
				useDuration: item.originalItem.useDuration,
				isFixedAsset: item.originalItem.isFixedAsset === 1 ? true : false
			};

			setTimeout(item.focusOnEdit, 100);
		};
		item.focusOnEdit = function() {
			$('#id_' + item.originalItem.catAssetCodeId).focus();
		};
		item.edit = function() {
			item.parent.catAssetCodeService.post('update', {
				caacName: item.editingItem.caacName,
				catAssetCodeId: item.originalItem.catAssetCodeId,
				depreciationTime: item.editingItem.depreciationTime,
				useDuration: item.editingItem.useDuration,
				isFixedAsset: item.editingItem.isFixedAsset ? 1 : 0
			}).then(function(response) {
				toastr.success(item.parent.gettextCatalog.getString('Cập nhật thành công'));
				item.originalItem.caacName = item.editingItem.caacName;
				item.originalItem.depreciationTime = item.editingItem.depreciationTime;
				item.originalItem.isFixedAsset = item.editingItem.isFixedAsset ? 1 : 0;
				item.originalItem.useDuration=item.editingItem.useDuration;
				item.cancelEdit();
			},function(err){
				toastr.error(item.parent.gettextCatalog.getString('Lỗi khi gửi sang tài chính'));
			});
		};
		item.lock = function() {
			item.parent.catAssetCodeService.post('inactive', {
				catAssetCodeId: item.originalItem.catAssetCodeId
			}).then(function(response) {
				toastr.success(item.parent.gettextCatalog.getString('Khóa thành công'));
				response.isUsing = item.originalItem.isUsing;
				item.originalItem = response;
			});
		};
		item.unlock = function() {
			item.parent.catAssetCodeService.post('active', {
				catAssetCodeId: item.originalItem.catAssetCodeId
			}).then(function(response) {
				toastr.success(item.parent.gettextCatalog.getString('Mở khóa thành công'));
				response.isUsing = item.originalItem.isUsing;
				item.originalItem = response;
			});
		};
		item.cancelEdit = function() {
			item._edit = false;
		};
		item.isEdit = function() {
			return item._edit;
		};
		item.isActive = function() {
			return item.originalItem.isActive === 1;
		};
		item.isUsing = function() {
			return (item.originalItem.isUsing);
		};
		item.canShowEditIcon = function() {
			return !item.isEdit() &&  item.parent.isSelectedItem(item) && item.isActive();
		};
		item.canShowDeleteIcon = function() {
			return !item.isEdit() &&  item.parent.isSelectedItem(item) && !item.isUsing();
		};
		item.canShowLockIcon = function() {
			return !item.isEdit() &&  item.parent.isSelectedItem(item) && item.isUsing() && item.isActive();
		};
		item.canShowUnlockIcon = function() {
			return !item.isEdit() &&  item.parent.isSelectedItem(item) && !item.isActive();
		};
		item.delete = function() {
			item.parent.catAssetCodeService.post('delete', {
				catAssetCodeId: item.originalItem.catAssetCodeId
			}).then(function(response) {
				toastr.success(item.parent.gettextCatalog.getString('Xóa thành công'));
				var index = item.parent.displayList.indexOf(item);
				if (index > -1) {
					item.parent.displayList.splice(index, 1);
				}
				item.parent.clearNext();
				if (!item.parent.isFirstElement()) {
					var curItem = item.parent.previousElement().getSelectedItem();
					if (curItem !== null && curItem !== undefined) {
						curItem.reload();
					}
				}
			});
		};
	}

	var app = angular.module('MetronicApp');

	app.factory('catAssetCodeService', function (Restangular) {
		return {
			post: function(action, params) {
				//var api = 'http://localhost:8080/qlhc-service/' + 'catAssetCodeServiceRest/' + action + '/';
				//var promise = $http.post(api, params);
				//return promise;
				return Restangular.all("catAssetCodeServiceRest/" + action).post(params);
			}
		};
	});

	app.controller('listCatAssetCodeController',
		function($scope, gettextCatalog, catAssetCodeService,Constant) {

			$scope.model = {
				list: [],
				level: 0,
				twoColumn: true,
				enableToolTip: true,
				toogleNumberOfList: function() {
					this.twoColumn = !this.twoColumn;
				},
				toogleListStyle: function() {
					this.enableToolTip = !this.enableToolTip;
				},
				isShowList: function(level) {
					return !this.twoColumn || level === this.level || level === this.level + 1;
				},
				nextLevel: function() {
					if (!this.isLastLevel()) {
						this.level += 1;
					}
				},

				previousLevel: function() {
					if (!this.isFirstLevel()) {
						this.level -= 1;
					}
				},
				changeLevel:function(level2){
					if(level2!=this.level){
						this.level=level2;
					}
				},
				isFirstLevel: function() {
					return this.level === 0;
				},
				isLastLevel: function() {
					return this.level === this.list.length - 2;
				},
				isActiveLevel:function(currentLevel){
					if(this.level==currentLevel){
						return true;
					}
				},
				isActiveDot:function(currentLevel){
					if(this.level==currentLevel||this.level+1==currentLevel){
						return true;
					}
				}
			};

			for (var i=0; i<4; i++) {
				$scope.model.list.push(new CatAssetCodeModel($scope.model, i, catAssetCodeService, gettextCatalog,Constant));
			}

			$scope.model.list[0].name = gettextCatalog.getString('Nhóm tài sản');
			$scope.model.list[1].name = gettextCatalog.getString('Loại tài sản');
			$scope.model.list[2].name = gettextCatalog.getString('Dòng tài sản');
			$scope.model.list[3].name = gettextCatalog.getString('Tên tài sản');

			$scope.model.list[0].refreshList();

		});


})();