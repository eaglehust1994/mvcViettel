/**
 * Created by hungle on 9/28/16.
 */
function initGridPeriodOption(){
    var option = kendoConfig.getGridOptions({
        filterable: false,
        sortable: false,
        noRecords: true,
        pageable: {
            pageSizes: false,
            refresh: false,
            messages: {
                display: "{0} - {1} của {2} kết quả",
                empty: "Không có kết quả nào",
                page: "trang",
                of: "của {0}",
                itemsPerPage: "kết quả/trang",
                first: "Về trang đầu",
                previous: "Về trang trước",
                next: "Về trang tiếp theo",
                last: "Về trang cuối",
                refresh: "Làm mới",
                allPages: "Tất"
            }
        },
        columns: [{
            title: "Đơn vị",
            template: function (item) {
                return item.adOrgName;
            },
            width: 160
        },{
            title: "Năm",
            template: function (item) {
                return item.year;
            },
            width: 100
        }, {
            title: "Kỳ",
            width: 90,
            template: function (item) {
                return item.periodNo;
            }
        }, {
            title: "Mã ",
            width: 130,
            template: function (item) {
                return item.periodKey;
            }
        }, {
            title: "Tên kỳ",
            template: function (item) {
                return item.name;
            },
            width: 180
        }, {
            title: "Ngày bắt đầu",
            width: 130,
            template: function (item) {
                return getDateTimeDisplay(item.startDate);
            }
        }, {
            title: "Ngày kết thúc",
            template: function (item) {
                return getDateTimeDisplay(item.endDate);
            },
            width: 130
        }]
    });
    return option;
}

function initGridYearOption(detailExpandFunc,detailCollapseFunc,url){
    var option = kendoConfig.getGridOptions({
        autoBind: true,
        dataSource: {
            pageSize: 5,
            transport: {
                read: function (options) {
                    $.ajax({
                        type: "GET",
                        url: url+"?currentPage="+options.data.page+"&pageSize="+options.data.pageSize+"&sortBy=year&sortOpt=desc",
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result) {
                            options.success(result);
                        }
                    });
                }

            },

            serverPaging: true,
            serverFiltering: true,
            serverSorting: true,
            schema: {data: "data", total: "total"}
        },
        filterable: false,
        sortable: false,
        pageable: {
            pageSizes: true,
            refresh: true,
            messages: {
                display: "{0} - {1} của {2} kết quả",
                empty: "Không có kết quả nào",
                page: "trang",
                of: "của {0}",
                itemsPerPage: "kết quả/trang",
                first: "Về trang đầu",
                previous: "Về trang trước",
                next: "Về trang tiếp theo",
                last: "Về trang cuối",
                refresh: "Làm mới",
                allPages: "Tất"
            }
        },
        noRecords: true,
        detailRow:null,
        masterRow:null,
        detailExpand: function (e) {
            this.collapseRow(this.masterRow);
            if (this.detailRow!=null) this.detailRow.remove();
            if (this.masterRow!=null) this.collapseRow(this.masterRow);
            this.detailRow= e.detailRow;
            this.masterRow= e.masterRow;
            if (detailExpandFunc!=null) detailExpandFunc.apply(this,[this.dataItem(e.masterRow)]);

        },
        detailCollapse:function(e){
            if (detailCollapseFunc!=null) detailCollapseFunc.apply(this);
        },
        // detailInit: detailInit,
        columns: [{
            title: "Năm",
            template: function (item) {
                return item.year;
            },
            width: 100
        }, {
            title: "Ngày bắt đầu",
            width: 130,
            template: function (item) {
                return getDateTimeDisplay(item.startDate);
            }
        }, {
            title: "Ngày kết thúc",
            width: 130,
            template: function (item) {
                return getDateTimeDisplay(item.endDate);
            }
        }, {
            title: "Mô Tả",
            template: function (item) {
                return item.description;
            },
            width: 250
        }, {
            title: "Hiệu lực",
            width: 130,
            template: function (item) {
                return (item.isactive==0)? "Hết hiệu lực" : "Có hiệu lực";
            }
        }, {
            title: "Đơn vị",
            template: function (item) {
                return (item.adOrg == null) ? "" : item.adOrg.name;
            },
            width: 250
        }]
    });
    return option;
}




/*---- utils -----*/
function initAutoCompleteOption(dataOptions){
    var bindOptions = {
        dataTextField: dataOptions.displayField,
        select: function(e){
            this.selectedItem = this.dataItem(e.item.index());
            if (dataOptions.selectFunc!=null) dataOptions.selectFunc(this.dataItem(e.item.index()));

        },
        dataBound:function(){
            var item =this.listView.dataSource.total();
            var tex=this._prev;
            for(var i=0;i<item;i++){
                var dataItem = this.dataItem(i);
                if (dataItem[dataOptions.displayField].toLowerCase().trim()==tex.toLowerCase().trim()){
                    if (dataOptions.selectFunc!=null) dataOptions.selectFunc(dataItem);
                    break;
                }
            }
            //var dataItem = this.listView.dataSource.data().length();
        },
        template: function (item) {
            return item[dataOptions.displayField];
        },
        minLength : 1,
        // pageSize: 5,
        dataSource: {
            serverFiltering: true,
            transport: {
                selectedItem:null,
                read: function (options) {
                    var filter=options.data.filter.filters[0].value;
                    // $scope.model.adOrgId=null,
                    //     $scope.modelValidation.errAdOrgId="";
                    var self=this;
                    var dataFilter={};
                    dataFilter[dataOptions.displayField]=filter;
                    $.ajax({
                        type: "GET",
                        url: dataOptions.url,
                        dataType: "json",
                        data: dataFilter,
                        contentType: "application/json",
                        success: function (result) {
                            options.success(result.data);
                        }
                    });
                }
            }
        }
    }
    return bindOptions;
}

function mapObject(objValue,objMap){
    for(var p in objValue){
        if (objMap!=null && objMap[p]!=null && objMap[p]!=undefined) objValue[p]=objMap[p]
        // else objMap[p]=null;

    }
}
function getDateTimeDisplay(date){
    if (date instanceof  Date) return kendo.toString(date,"dd-MM-yyyy");
    if (isNaN(Number(date))){
        return kendo.toString(kendo.parseDate( date, 'yyyy-MM-dd'), 'dd-MM-yyyy')
    }else{
        return kendo.toString(new Date(date), 'dd-MM-yyyy');
    }
}
function getDateTimeService(date){
    if (date instanceof  Date) return kendo.toString(date,"yyyy-MM-dd");
    if (isNaN(Number(date))){
        return kendo.toString(kendo.parseDate( date, 'dd-MM-yyyy'), 'yyyy-MM-dd')
    }else{
        return kendo.toString(new Date(date), 'yyyy-MM-dd');
    }
}




/*---- utils -----*/