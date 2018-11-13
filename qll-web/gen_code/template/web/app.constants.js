{
	key: '${tbl.tableName}List', //Màn hình danh sách
	title: 'Danh sách ${tbl.tableName}',
	templateUrl: 'ims/${tbl.tableNameVar}/${tbl.tableNameJV}List.view.html',
	lazyLoadFiles : [
		'ims/${tbl.tableNameVar}/${tbl.tableNameJV}List.controller.js',
		'ims/${tbl.tableNameVar}/${tbl.tableNameJV}.service.js'
	]
},
{
	key: '${tbl.tableName}Form',
	title: 'Chi tiết ${tbl.tableName}', //Màn hình form
	templateUrl: 'ims/${tbl.tableNameVar}/${tbl.tableNameJV}Form.view.html',
	lazyLoadFiles : [
		'ims/${tbl.tableNameVar}/${tbl.tableNameJV}Form.controller.js',
		'ims/${tbl.tableNameVar}/${tbl.tableNameJV}.service.js'
	]
},