package com.viettel.ims.dao;

import java.util.List;

import java.math.BigDecimal;
import com.viettel.ims.bo.SysGroupBO;

import com.viettel.ims.dto.SysGroupDTO;


import java.lang.reflect.Field;

import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("sysGroupDAO")
public class SysGroupDAO extends BaseFWDAOImpl<SysGroupBO, Long> {

    public SysGroupDAO() {
        this.model = new SysGroupBO();
    }

    public SysGroupDAO(Session session) {
        this.session = session;
    }	
    
	@SuppressWarnings("unchecked")
	public List<SysGroupDTO> doSearch(SysGroupDTO criteria) {
		StringBuilder stringBuilder = getSelectAllQuery();
		stringBuilder.append("WHERE STATUS=1 ");

		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		
		if(criteria.getSysGroupId() != null){
			stringBuilder
			.append(" AND (PARENT_ID = :sysGroupId OR SYS_GROUP_ID = :sysGroupId)");
		}

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("sysGroupId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("parentId", new LongType());
		query.addScalar("parentName", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("path", new StringType());
		query.addScalar("effectDate", new DateType());
		query.addScalar("endDate", new DateType());

		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			query.setParameter("key", "%" + criteria.getKeySearch() + "%");
			queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
		}
		if (criteria.getSysGroupId() != null) {
			query.setParameter("sysGroupId", criteria.getSysGroupId());
			queryCount.setParameter("sysGroupId",criteria.getSysGroupId());
		}

		query.setResultTransformer(Transformers.aliasToBean(SysGroupDTO.class));
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return ls;
	}
	
	public SysGroupDTO findByCode(String value) {
		StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE upper(T1.CODE) = upper(:code)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("parentId", new LongType());
		query.addScalar("parentName", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("path", new StringType());
		query.addScalar("effectDate", new DateType());
		query.addScalar("endDate", new DateType());
    	
		query.setParameter("code", value);    	
		query.setResultTransformer(Transformers.aliasToBean(SysGroupDTO.class));    	

		return (SysGroupDTO) query.uniqueResult();
	}

	public List<SysGroupDTO> getForAutoComplete(SysGroupDTO obj) {
				
		
		StringBuilder stringBuilder = getSelectAllQuery();
		stringBuilder.append("Where STATUS = 1");
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		stringBuilder.append(" ORDER BY NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("sysGroupId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("parentId", new LongType());
		query.addScalar("parentName", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("path", new StringType());
		query.addScalar("effectDate", new DateType());
		query.addScalar("endDate", new DateType());
	
		query.setResultTransformer(Transformers.aliasToBean(SysGroupDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("key", "%" + obj.getKeySearch() + "%");
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysGroupDTO> getForComboBox(SysGroupDTO obj){
		String sql = "SELECT SYS_GROUP_ID sysGroupId "
				+ " ,NAME name" + " ,CODE code"
				+ " FROM CTCT_CAT_OWNER.SYS_GROUP"
				+ " WHERE STATUS = 1";

		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		stringBuilder.append(" ORDER BY CODE");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(SysGroupDTO.class));

		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public SysGroupDTO getById(Long id) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE T1.SYS_GROUP_ID = :sysGroupId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("parentId", new LongType());
		query.addScalar("parentName", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("path", new StringType());
		query.addScalar("effectDate", new DateType());
		query.addScalar("endDate", new DateType());
    	
		query.setParameter("sysGroupId", id);
		query.setResultTransformer(Transformers.aliasToBean(SysGroupDTO.class));
    	
		return (SysGroupDTO) query.uniqueResult();
	}
	
	StringBuilder getSelectAllQuery(){
    	StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append("T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.PARENT_ID parentId ");
		stringBuilder.append(",(SELECT T2.NAME FROM CTCT_CAT_OWNER.SYS_GROUP T2 WHERE T2.SYS_GROUP_ID = T1.PARENT_ID) parentName ");   
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.PATH path ");
		stringBuilder.append(",T1.EFFECT_DATE effectDate ");
		stringBuilder.append(",T1.END_DATE endDate ");
    	stringBuilder.append("FROM CTCT_CAT_OWNER.SYS_GROUP T1 ");   
    	
    	return stringBuilder;
	}
}
