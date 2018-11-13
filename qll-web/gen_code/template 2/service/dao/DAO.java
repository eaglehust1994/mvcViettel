package com.viettel.${projectCode}.dao;

import java.util.List;
import com.viettel.ktts2.dao.AbstractKttsBaseDao;
import com.viettel.${projectCode}.bo.${tbl.tableNameJV}BO;
import com.viettel.${projectCode}.dto.${tbl.tableNameJV}DTO;
import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
* Customize by hanhls1@ for ktts2
 */
@Repository("${tbl.tableNameVar}DAO")

public class ${tbl.tableNameJV}DAO extends AbstractKttsBaseDao<${tbl.tableNameJV}BO, Long> {
    public ${tbl.tableNameJV}DAO() {
        this.model = new ${tbl.tableNameJV}BO();
    }

    public ${tbl.tableNameJV}DAO(Session session) {
        this.session = session;
    }
	
	@SuppressWarnings("unchecked")
	public List<${tbl.tableNameJV}DTO> getAll() {
    	StringBuilder stringBuilder = new StringBuilder("SELECT ");
		<#list  tbl.columnBOs as clm >
		<#if clm?counter == 1>
		stringBuilder.append("T1.${clm.columnName} ${clm.columnVar} ");
		<#else>
		stringBuilder.append(",T1.${clm.columnName} ${clm.columnVar} ");
		</#if>		
		</#list>    	

    	stringBuilder.append("FROM ${tbl.tableName} T1 ");
		
		//todo: add join thêm các trường hợp ở đây
    	
    	stringBuilder.append("WHERE T1.IS_ACTIVE =1 ");
    	stringBuilder.append("ORDER BY T1.${tbl.tableName}_ID DESC ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		<#list  tbl.columnBOs as clm >
		query.addScalar("${clm.columnVar}", new ${clm.dataTypeJV?replace("java.lang.", "")?replace("java.util.", "")}Type());			
		</#list>
    	
		query.setResultTransformer(Transformers.aliasToBean(${tbl.tableNameJV}DTO.class));
    	
		return query.list();
	}
    
    @SuppressWarnings("unchecked")
	public List<${tbl.tableNameJV}DTO> doSearch(${tbl.tableNameJV}DTO criteria) {
    	StringBuilder stringBuilder = new StringBuilder("select count(*) over() totalRecord, ");
    	<#list  tbl.columnBOs as clm >
		<#if clm?counter == 1>
		stringBuilder.append("T1.${clm.columnName} ${clm.columnVar} ");
		<#else>
		stringBuilder.append(",T1.${clm.columnName} ${clm.columnVar} ");
		</#if>			
		</#list> 
    	
    	stringBuilder.append("FROM ${tbl.tableName} T1 ");
		
    	//todo: add thêm dideuf kiện where ở đây
    	
    	stringBuilder.append("WHERE T1.IS_ACTIVE = 1 ");
    			
		
		<#list  tbl.columnBOs as clm >
		<#if clm.dataTypeJV?contains("String")>
		if (StringUtils.isNotEmpty(criteria.get${clm.columnNameJV}())) {
			stringBuilder.append("AND UPPER(T1.${clm.columnName}) LIKE UPPER(:${clm.columnVar}) ");
		}
		<#else>
		if (null != criteria.get${clm.columnNameJV}()) {
			stringBuilder.append("AND T1.${clm.columnName} = :${clm.columnVar} ");
		}
		<#if clm.dataTypeJV?contains("java.util.Date")>
		if (null != criteria.get${clm.columnNameJV}From()) {
			stringBuilder.append("AND T1.${clm.columnName} >= :${clm.columnVar}From ");
		}
		if (null != criteria.get${clm.columnNameJV}To()) {
			stringBuilder.append("AND T1.${clm.columnName} <= :${clm.columnVar}To ");
		}
		</#if>
		</#if>
		</#list>		
		
    	if (StringUtils.isNotEmpty(criteria.getOrderBy())){
			stringBuilder.append("ORDER BY " + criteria.getOrderBy() + " " + criteria.getOrderType() + " ");
		} else {
			<#list  tbl.columnBOs as clm >
			<#if clm.isKey ==true>
			stringBuilder.append("ORDER BY T1.${clm.columnName}, T1.IS_ACTIVE ");
			</#if>
			</#list>
		}
    	    	
		
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
	
		
	
		query.addScalar("totalRecord", new IntegerType());
		<#list  tbl.columnBOs as clm >
		query.addScalar("${clm.columnVar}", new ${clm.dataTypeJV?replace("java.lang.", "")?replace("java.util.", "")}Type());		
		</#list>
		
    	
		<#list  tbl.columnBOs as clm >
		<#if clm.dataTypeJV?contains("String")>
		if (StringUtils.isNotEmpty(criteria.get${clm.columnNameJV}())) {
			query.setParameter("${clm.columnVar}", "%" + criteria.get${clm.columnNameJV}().toUpperCase() + "%");
		}
		<#else>
		if (null != criteria.get${clm.columnNameJV}()) {
			query.setParameter("${clm.columnVar}", criteria.get${clm.columnNameJV}());
		}
		<#if clm.dataTypeJV?contains("java.util.Date")>
		if (null != criteria.get${clm.columnNameJV}From()) {
			query.setTimestamp("${clm.columnVar}From", criteria.get${clm.columnNameJV}From());
		}
		if (null != criteria.get${clm.columnNameJV}To()) {
			query.setTimestamp("${clm.columnVar}To", criteria.get${clm.columnNameJV}To());
		}
		</#if>
		</#if>
		</#list>
    	if (criteria.getSize() != 0 ) {
			query.setFirstResult((criteria.getPage()-1)*criteria.getSize());
			query.setMaxResults(criteria.getSize());
		}
		query.setResultTransformer(Transformers.aliasToBean(${tbl.tableNameJV}DTO.class));    	
		List ls = query.list();
		
		return ls;
	}  
	
	public ${tbl.tableNameJV}DTO findByValue(String value) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
    	<#list  tbl.columnBOs as clm >
		<#if clm?counter == 1>
		stringBuilder.append("T1.${clm.columnName} ${clm.columnVar} ");
		<#else>
		stringBuilder.append(",T1.${clm.columnName} ${clm.columnVar} ");
		</#if>
		</#list> 
    	
    	stringBuilder.append("FROM ${tbl.tableName} T1 ");    
		//TODO: chỉnh sửa cột dữ liệ
    	//stringBuilder.append("WHERE T1.IS_ACTIVE=1 AND upper(T1.VALUE) = upper(:value)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
    	<#list  tbl.columnBOs as clm >
		query.addScalar("${clm.columnVar}", new ${clm.dataTypeJV?replace("java.lang.", "")?replace("java.util.", "")}Type());
		</#list>
    	
		//todo: chình sửa kiểu dữ liệu
		//query.setParameter("value", value);    	
		query.setResultTransformer(Transformers.aliasToBean(${tbl.tableNameJV}DTO.class));    	

		return (${tbl.tableNameJV}DTO) query.uniqueResult();
	}

	public List<${tbl.tableNameJV}BO> getForAutoComplete(${tbl.tableNameJV}DTO obj) {
		<#list  tbl.columnBOs as clm >
		<#if clm.isKey>
		String sql = "SELECT t.*"	
			
			+" FROM ${tbl.tableName} t"
			+" WHERE IS_ACTIVE=1";			
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(" AND ROWNUM <=:page" );
		//todo Add thêm điều kiện tìm kiệm
		if(StringUtils.isNotEmpty(obj.getKeySearch())){	
			stringBuilder.append("upper(${clm.columnName}) like :keySearch");
		}
		
		
		stringBuilder.append(" ORDER BY ${clm.columnName} desc");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addEntity(${tbl.tableNameJV}BO.class);		
		
		</#if>
		</#list>
	
		

		
		query.setParameter("page",obj.getSize()>0?obj.getSize():10);
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch().toUpperCase() + "%");
		}
		

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public ${tbl.tableNameJV}DTO getById(Long id) {
    	StringBuilder stringBuilder = new StringBuilder("SELECT ");
		<#list  tbl.columnBOs as clm >
		<#if clm?counter == 1>
		stringBuilder.append("T1.${clm.columnName} ${clm.columnVar} ");
		<#else>
		stringBuilder.append(",T1.${clm.columnName} ${clm.columnVar} ");
		</#if>
		<#if clm.columnVar?contains("Id") && clm.isKey == false>
		//todo: change Table name
		stringBuilder.append(",CASE WHEN xxx.VALUE IS NULL THEN xxx.NAME ELSE (xxx.VALUE || ' - ' || xxx.NAME) END ${clm.columnVar?replace('Id', 'Name')} ");
		</#if>
		<#if clm.columnVar?contains("atedby") && clm.isKey == false>
		//todo: change Table name
		stringBuilder.append(",(xxx.USERNAME || ' - ' || xxx.FULLNAME) ${clm.columnVar}Name ");
		</#if>
		</#list>    	

    	stringBuilder.append("FROM ${tbl.tableName} T1 ");
		
		<#list  tbl.columnBOs as clm >
		<#if (clm.columnVar?contains("Id") || clm.columnVar?contains("atedby")) && clm.isKey == false>
		//todo: change Table name
		stringBuilder.append("LEFT JOIN xxxTableName Txxx ON T1.${clm.columnName} = Txxx.${clm.columnName} ");
		</#if>
		</#list>
    	
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.${tbl.tableName}_ID = :${tbl.tableNameVar}Id ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		<#list  tbl.columnBOs as clm >
		query.addScalar("${clm.columnVar}", new ${clm.dataTypeJV?replace("java.lang.", "")?replace("java.util.", "")}Type());
		<#if clm.columnVar?contains("Id") && clm.isKey == false>
		query.addScalar("${clm.columnVar?replace('Id', 'Name')}", new StringType());
		</#if>
		<#if clm.columnVar?contains("atedby") && clm.isKey == false>
		query.addScalar("${clm.columnVar}Name", new StringType());
		</#if>
		</#list>
    	
		query.setParameter("${tbl.tableNameVar}Id", id);
		query.setResultTransformer(Transformers.aliasToBean(${tbl.tableNameJV}DTO.class));
    	
		return (${tbl.tableNameJV}DTO) query.uniqueResult();
	}
}
