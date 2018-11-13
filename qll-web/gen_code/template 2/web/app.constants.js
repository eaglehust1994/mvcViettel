{
	key: '${tbl.tableName}List', //Màn hình danh sách
	title: 'Danh sách ${tbl.tableName}',
	templateUrl: '${projectCode}/${tbl.tableNameVar}/${tbl.tableNameJV}List.view.html',
	lazyLoadFiles : [
		'${projectCode}/${tbl.tableNameVar}/${tbl.tableNameJV}List.controller.js',
		'${projectCode}/${tbl.tableNameVar}/${tbl.tableNameJV}.service.js'
	]
},
{
	key: '${tbl.tableName}Form',
	title: 'Chi tiết ${tbl.tableName}', //Màn hình form
	templateUrl: '${projectCode}/${tbl.tableNameVar}/${tbl.tableNameJV}Form.view.html',
	lazyLoadFiles : [
		'${projectCode}/${tbl.tableNameVar}/${tbl.tableNameJV}Form.controller.js',
		'${projectCode}/${tbl.tableNameVar}/${tbl.tableNameJV}.service.js'
	]
},