(function() {
	'use strict';
	var controllerId = 'stockGoodController';
	
	angular.module('MetronicApp').controller(controllerId, stockGoodController);
	
	function stockGoodController($scope, $rootScope, $timeout, gettextCatalog, 
			kendoConfig, $kWindow,stockGoodService,
			CommonService, PopupConst, Restangular, RestEndpoint,Constant) {
		var vm = this;
		vm.stockGoodsDetail={};
		
		
		
		initFormData();
		function initFormData() {
			fillStockGoods([]);
			
		}
		//begin
		
		$(document).ready(function(){
			$("#closeDetail").click(function(){
		         // call 'close' method on nearest kendoWindow
		         $(this).closest("[data-role=window]").data("kendoWindow").close();
		         // the above is equivalent to:
		         //$(this).closest(".k-window-content").data("kendoWindow").close();
		       });	
		})
vm.cancelDetail= cancelDetail ;
		function cancelDetail(){
		/* confirm('Xác nhận huỷ bỏ', function(){ */
				$("div.k-window-actions > a.k-window-action > span.k-i-close").click();
				/* }); */
		}
		var record=0;
		function fillStockGoods(data) {
			vm.stockGoodDetailGridOptions = kendoConfig.getGridOptions({
				autoBind: true,
				sortable: true,
				resizable: true,
				columnMenu: false,			 
				dataSource:data,
				noRecords: true,
				scrollable: false,
				messages: {
					noRecords : gettextCatalog.getString("Không có kết quả hiển thị")
				},
				pageable: {
					refresh: false,
					pageSize:10,
					 pageSizes: [10, 15, 20, 25],
					messages: {
		                display: "{0}-{1} của {2} kết quả",
		                itemsPerPage: "kết quả/trang",
		                empty: "Không có kết quả hiển thị"
		            }
				},
				dataBinding: function() {
                    record = (this.dataSource.page() -1) * this.dataSource.pageSize();
                },
				columns: [{
					title: "TT",
					field:"stt",
					template: function () {
						  return ++record;
						 },
			        width: 70,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:center;"
					},
				}
				, {
					title: "Serial",
					field: 'serial',
			        width: 90,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				},  {
					title: "Mã hợp đồng",
					field: 'contractCode',
			        width: 200,
			        headerAttributes: {
						style: "text-align:center;"
					},
					attributes: {
						style: "text-align:left;"
					},
				}
				,{
					title: "Part number",
					field: 'partNumber',
			        width: 110,
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
					title: "Vị trí",
					field: 'vitri',
			        width: 120,
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
		vm.exportExcelGridStock = exportExcelGridStock;
		function exportExcelGridStock(){
		//data("kendoGrid").dataSource
		//data().kendoGrid.dataSource.view()
			var ds = $("#viewGoodDetailGrid").data("kendoGrid").dataSource;
			var pagesize = ds.total();
			ds.pageSize(parseInt(pagesize));
			var ds1 = ds.data();
			 if (ds1.length == 0){
				 toastr.error(gettextCatalog.getString("Không có dữ liệu xuất "));
			 }else {
		    	var rows = [{
			        cells: [
			          { value: "TT", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "center",fontFamily:"Times New Roman" },
			          { value: "Serial", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "center",fontFamily:"Times New Roman" },
			          { value: "Mã hợp đồng", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "center",fontFamily:"Times New Roman" },
			          { value: "Part number", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "center",fontFamily:"Times New Roman"},
			          { value: "Hãng sản xuất", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "center",fontFamily:"Times New Roman" },
			          { value: "Nước sản xuất", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "center",fontFamily:"Times New Roman" },
			          { value: "Vị trí", background: "#D8E4BC", bold: true, borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 } }	      
			          	      
			          ]
			      }];
		    	ds.fetch(function(){
		    		var data = this.data();
		    		for (var i = 0; i < data.length; i++){
				          // push single row for every record
				        	rows.push({
					            cells: [
					              { value: i+1 , borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "center",fontFamily:"Times New Roman"},
					              { value: data[i].serial,borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "left",fontFamily:"Times New Roman" },
					              { value: data[i].contractCode,borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "left",fontFamily:"Times New Roman" },
					              { value: data[i].partNumber,borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "left",fontFamily:"Times New Roman" },
					              { value: data[i].manufacturerName,borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "left",fontFamily:"Times New Roman" },
					              { value: data[i].producingCountryName,borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "left",fontFamily:"Times New Roman" },
								  { borderBottom: { color: "#000000", size: 1 }, borderTop: { color: "#000000", size: 1 }, borderRight: { color: "#000000", size: 1 },borderLeft: { color: "#000000", size: 1 },textAlign: "left",fontFamily:"Times New Roman"},
]
				          })
				        }
		    		var workbook = new kendo.ooxml.Workbook({
				          sheets: [
				            {
				            	columns: [
				                // Column settings (width)
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },
				                { autoWidth: true },

				              ],
				              
				              // Title of the sheet
				              title: "Thông tin chi tiết hàng hóa",
				              // Rows of the sheet
				              rows: rows
				            }
				          ]
				        });
		    		kendo.saveAs({dataURI: workbook.toDataURL(), fileName: "Thông tin chi tiết hàng hóa.xlsx"});
		    		ds.pageSize(parseInt(10));
		    	});
			 }
		}
		
		
		vm.stockGet={};
		vm.stockDetail={}
		function getStockGoodsDetail()
		{
			
			vm.stockGoodsDetail.stockId = vm.stockDetail.stockId;
			vm.stockGoodsDetail.goodsId = vm.stockDetail.goodsId;
			
			stockGoodService.getstockGoodsDetail(vm.stockGoodsDetail).then(function(d) {
				refreshGrid(d.plain(),vm.stockGoodDetailGrid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		} 
		
		function getstockGoodsDetailSerial()
		{
			
			vm.stockGoodsDetail.stockId = vm.stockDetail.stockId;
			vm.stockGoodsDetail.goodsId = vm.stockDetail.goodsId;
			
			stockGoodService.getstockGoodsDetailSerial(vm.stockGoodsDetail).then(function(d) {
				refreshGrid(d.plain(),vm.stockGoodDetailGrid);
			}, function(errResponse) {
				console.error('Error Tìm kiếm');
			});
		}
		$scope.$on("stockintrade.object.data", function(event, dataItem) {
			vm.stockGet = dataItem;
			vm.stockDetail = vm.stockGet;
			if(vm.stockDetail.goodsIsSerial == 0){
				getStockGoodsDetail();
			}else{
				getstockGoodsDetailSerial();
			}
		});

		
		function refreshGrid(d,grid) {
			var grid = grid;
			if(grid){
				grid.dataSource.data(d);
				grid.refresh();
			}
		}
		
		
	}
	
	
})();