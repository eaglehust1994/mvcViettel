(function() {
	'use strict';
	var controllerId = 'shipmentDetailController';
	
	angular.module('MetronicApp').controller(controllerId, shipmentDetailController);
	
	function shipmentDetailController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,shipmentDetailService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.showGoods = true;
		vm.showGoods1 = false;
		vm.shipmentGet = {};
		vm.shipmentDetail = {};
		vm.docso = {};
		vm.kcsSearch = {};
		vm.kcsSearch2 = {};
		vm.kcs = {};
		vm.kcs2 = {};
		vm.orderSreach ={};
		vm.order = {};
		vm.stockSearch={};
		vm.stock={};
		
		vm.doSearchFile=
		{
				type : 1
		}
		vm.doSearchMap = {};
		
		vm.goods1 = function goods1()
		{
				vm.showGoods1 = true;
				vm.showGoods = false;
		}
		vm.goods = function goods()
		{
				vm.showGoods = true;
				vm.showGoods1 = false;
		}
		
		initFormData();
		function initFormData() {
			fillFileTable([]);
			fillMapTable([]);
			fillGoodsTable([]);
			fillGoodsDetailTable([]);
			fillGoodsDetail1Table([]);
		}
		function fillFileTable(dataSource) {
			vm.gridFileOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: dataSource,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSizes: [10, 15, 20, 25],
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#shipmentFileGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Tên file",
					field: 'name',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Loại file",
					field: 'appParamCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		
		function fillMapTable(data) {
			vm.gridMapOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSizes: [10, 15, 20, 25],
				columns: [{
					title: "STT",
					field:"stt",
					template: dataItem => $("#shipmentMapGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng",
					field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Đơn vị tính",
					field: 'unitTypeName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Hãng sản xuất",
					field: 'manufacturerName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Nước sản xuất",
					field: 'producingCountryName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Partnumber",
					field: 'partNumber',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Serial",
					field: 'serial',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
					field: 'amount',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		
		function fillGoodsTable(data) {
			vm.gridGoodsOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSizes: [10, 15, 20, 25],
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#shipmentGoodsGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng",
					field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Đơn vị tính",
					field: 'unitTypeName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
					field: 'amount',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định lượng cho 1 đơn vị HH",
					field: 'applyPrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Định lượng theo danh mục",
					field: 'estimatePriceSum',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tỷ lệ % theo danh mục",
					field: 'estimatePricePercent',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn giá",
					field: 'applyPrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thành tiền",
					field: 'applyTotalMoney',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		
		function fillGoodsDetail1Table(data) {
			vm.gridGoodsDetail1Options = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource:data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSizes: [10, 15, 20, 25],
				columns: [{
					title: "STT",
					field:"stt",
					template: dataItem =>  $("#shipmentGoodsDetail1Grid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Mã hàng",
					field: 'goodsCode',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Tên hàng",
					field: 'goodsName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Đơn vị tính",
					field: 'unitTypeName',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Số lượng",
					field: 'amount',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tỷ lệ % theo danh mục",
					field: 'estimatePricePercent',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Đơn giá phân bổ",
					field: 'applyPrice',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Thành tiền",
					field: 'applyTotalMoney',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		
		function fillGoodsDetailTable(data) {
			vm.gridGoodsDetailOptions = kendoConfig.getGridOptions({
				autoBind: true,
				resizable: true,			 
				dataSource: data,
				noRecords: true,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageSizes: [10, 15, 20, 25],
				columns: [{
					title: "STT",
					field:"stt",
			        template: dataItem => $("#shipmentGoodsDetailGrid").data("kendoGrid").dataSource.indexOf(dataItem) + 1,
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				,  {
					title: "Tên thuế",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Thuế xuất (%)",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Loại thuế",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Phân bổ",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Miễn thuế",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},{
					title: "Tiền thuế (VNĐ)",
					field: 'code',
			        width: 150,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,]
			});
		}
		
		
		$scope.$on("shipment.object.data", function(event, dataItem) {
			vm.shipmentGet = dataItem;
			vm.shipmentDetail = vm.shipmentGet;
			doSearchFile();
			doSearchMap();
			doSearchGoodDetail();
			doSearchTAX();
			getDataKCS1();
			getDataKCS2();
			getOrderByShipment();
			getStockByOrder();
			
		});
		
		function getOrderByShipment()
		{
			vm.orderSreach.shipmentId = vm.shipmentDetail.shipmentId;
		    shipmentDetailService.getOrderByShipment(vm.orderSreach).then(function(d) {
				vm.order=d.plain();
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		
		function getStockByOrder()
		{
			vm.stockSearch.shipmentId = vm.shipmentDetail.shipmentId;
		    shipmentDetailService.getStockByOrder(vm.stockSearch).then(function(d) {
				vm.stock=d.plain();
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		
		function getDataKCS1()
		{
			vm.kcsSearch.objectId = vm.shipmentDetail.shipmentId;
		    vm.kcsSearch.type = 1;
		    shipmentDetailService.getDataKCS(vm.kcsSearch).then(function(d) {
				vm.kcs=d.plain();
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		function getDataKCS2()
		{
			vm.kcsSearch2.objectId = vm.shipmentDetail.shipmentId;
		    vm.kcsSearch2.type = 2;
		    shipmentDetailService.getDataKCS(vm.kcsSearch2).then(function(d) {
				vm.kcs2=d.plain();
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		
		function doSearchFile()
		{
			vm.doSearchFile.objectId = vm.shipmentDetail.shipmentId;
		    vm.doSearchFile.status = vm.shipmentDetail.status;
		    shipmentDetailService.doSearchFile(vm.doSearchFile).then(function(d) {
				refreshGrid(d.plain(),vm.shipmentFileGrid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		function doSearchMap()
		{
			vm.doSearchMap.shipmentId = vm.shipmentDetail.shipmentId;
		    shipmentDetailService.doSearchMap(vm.doSearchMap).then(function(d) {
				refreshGrid(d.plain(),vm.shipmentGoodsGrid);
				refreshGrid(d.plain(),vm.shipmentGoodsDetail1Grid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		
		function doSearchGoodDetail()
		{
			vm.doSearchMap.shipmentId = vm.shipmentDetail.shipmentId;
			shipmentDetailService.getDoMapDetail(vm.doSearchMap).then(function(d) {
				refreshGrid(d.plain(),vm.shipmentMapGrid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		} 
		
		function doSearchTAX()
		{
			vm.doSearchMap.shipmentId = vm.shipmentDetail.shipmentId;
			shipmentDetailService.doSearchTAX(vm.doSearchMap).then(function(d) {
				refreshGrid(d.plain(),vm.shipmentGoodsDetailGrid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		} 
		
		function refreshGrid(d,grid) {
			var grid = grid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		//tien
		var ChuSo=new Array(" không "," một "," hai "," ba "," bốn "," năm "," sáu "," bảy "," tám "," chín ");
		var Tien=new Array( "", " nghìn", " triệu", " tỷ", " nghìn tỷ", " triệu tỷ");

		//1. Hàm đọc số có ba chữ số;
		function DocSo3ChuSo(baso)
		{
		    var tram;
		    var chuc;
		    var donvi;
		    var KetQua="";
		    tram=parseInt(baso/100);
		    chuc=parseInt((baso%100)/10);
		    donvi=baso%10;
		    if(tram==0 && chuc==0 && donvi==0) return "";
		    if(tram!=0)
		    {
		        KetQua += ChuSo[tram] + " trăm ";
		        if ((chuc == 0) && (donvi != 0)) KetQua += " linh ";
		    }
		    if ((chuc != 0) && (chuc != 1))
		    {
		            KetQua += ChuSo[chuc] + " mươi";
		            if ((chuc == 0) && (donvi != 0)) KetQua = KetQua + " linh ";
		    }
		    if (chuc == 1) KetQua += " mười ";
		    switch (donvi)
		    {
		        case 1:
		            if ((chuc != 0) && (chuc != 1))
		            {
		                KetQua += " mốt ";
		            }
		            else
		            {
		                KetQua += ChuSo[donvi];
		            }
		            break;
		        case 5:
		            if (chuc == 0)
		            {
		                KetQua += ChuSo[donvi];
		            }
		            else
		            {
		                KetQua += " lăm ";
		            }
		            break;
		        default:
		            if (donvi != 0)
		            {
		                KetQua += ChuSo[donvi];
		            }
		            break;
		        }
		    return KetQua;
		}
		
		vm.cancel= cancel ;
		function cancel(){
				vm.shipmentGoods={};
				vm.showDetail = false;
				vm.isCreateNew = false;
		}
		
		//2. Hàm đọc số thành chữ (Sử dụng hàm đọc số có ba chữ số)

		function DocTienBangChu(SoTien)
		{
		    var lan=0;
		    var i=0;
		    var so=0;
		    var KetQua="";
		    var tmp="";
		    var ViTri = new Array();
		    if(SoTien<0) return "Số tiền âm !";
		    if(SoTien==0) return "Không đồng !";
		    if(SoTien>0)
		    {
		        so=SoTien;
		    }
		    else
		    {
		        so = -SoTien;
		    }
		    if (SoTien > 8999999999999999)
		    {
		        //SoTien = 0;
		        return "Số quá lớn!";
		    }
		    ViTri[5] = Math.floor(so / 1000000000000000);
		    if(isNaN(ViTri[5]))
		        ViTri[5] = "0";
		    so = so - parseFloat(ViTri[5].toString()) * 1000000000000000;
		    ViTri[4] = Math.floor(so / 1000000000000);
		     if(isNaN(ViTri[4]))
		        ViTri[4] = "0";
		    so = so - parseFloat(ViTri[4].toString()) * 1000000000000;
		    ViTri[3] = Math.floor(so / 1000000000);
		     if(isNaN(ViTri[3]))
		        ViTri[3] = "0";
		    so = so - parseFloat(ViTri[3].toString()) * 1000000000;
		    ViTri[2] = parseInt(so / 1000000);
		     if(isNaN(ViTri[2]))
		        ViTri[2] = "0";
		    ViTri[1] = parseInt((so % 1000000) / 1000);
		     if(isNaN(ViTri[1]))
		        ViTri[1] = "0";
		    ViTri[0] = parseInt(so % 1000);
		  if(isNaN(ViTri[0]))
		        ViTri[0] = "0";
		    if (ViTri[5] > 0)
		    {
		        lan = 5;
		    }
		    else if (ViTri[4] > 0)
		    {
		        lan = 4;
		    }
		    else if (ViTri[3] > 0)
		    {
		        lan = 3;
		    }
		    else if (ViTri[2] > 0)
		    {
		        lan = 2;
		    }
		    else if (ViTri[1] > 0)
		    {
		        lan = 1;
		    }
		    else
		    {
		        lan = 0;
		    }
		    for (i = lan; i >= 0; i--)
		    {
		       tmp = DocSo3ChuSo(ViTri[i]);
		       KetQua += tmp;
		       if (ViTri[i] > 0) KetQua += Tien[i];
		       if ((i > 0) && (tmp.length > 0)) KetQua += ',';//&& (!string.IsNullOrEmpty(tmp))
		    }
		   if (KetQua.substring(KetQua.length - 1) == ',')
		   {
		        KetQua = KetQua.substring(0, KetQua.length - 1);
		   }
		   KetQua = KetQua.substring(1,2).toUpperCase()+ KetQua.substring(2);
		   return KetQua;//.substring(0, 1);//.toUpperCase();// + KetQua.substring(1);
		}
		
		if(vm.shipmentDetail.totalFee != undefined){
			vm.docso.totalFee = DocTienBangChu(vm.shipmentDetail.totalFee);
		}
		if(vm.shipmentDetail.totalOriginMoney != undefined){
			vm.docso.totalOriginMoney = DocTienBangChu(vm.shipmentDetail.totalOriginMoney);
		}
		if(vm.shipmentDetail.totalTax != undefined){
			vm.docso.totalTax = DocTienBangChu(vm.shipmentDetail.totalTax);
		}
		if(vm.shipmentDetail.totalMoney != undefined){
			vm.docso.totalFee = DocTienBangChu(vm.shipmentDetail.totalMoney);
		}
		
		
		// /END
	}
	
	
})();