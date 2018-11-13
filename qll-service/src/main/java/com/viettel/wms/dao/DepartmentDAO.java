/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.DepartmentBO;
import com.viettel.wms.dto.DepartmentDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import javassist.convert.Transformer;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("departmentDAO")
public class DepartmentDAO extends BaseFWDAOImpl<DepartmentBO, Long> {

    public DepartmentDAO() {
        this.model = new DepartmentBO();
    }

    public DepartmentDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<DepartmentDTO> getall(DepartmentDTO obj){
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT DP.SYS_GROUP_ID id,"
    			+ "DP.CODE code,(DP.CODE ||'-' || DP.NAME) text, DP.PARENT_ID parentId, DP.STATUS status, DP.PATH path,"
    			+ "DP.EFFECT_DATE effectDate, DP.END_DATE endDate, DP1.NAME parentName"
    			+ " FROM VPS_OWNER.SYS_GROUP DP "
    			+ "LEFT Join VPS_OWNER.SYS_GROUP DP1 "
    			+ "On DP1.SYS_GROUP_ID=DP.PARENT_ID "
    			+ "WHERE DP.STATUS=1");
    	if(StringUtils.isNotEmpty(obj.getCode())){
    		sql.append(" AND upper(DP.CODE) like upper(:code)");
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getName())){
    		sql.append(" AND upper(DP.NAME) like upper(:name)");
    	}
    	
    	if(obj.getId()!=null){
    		sql.append(" AND (DP.PARENT_ID = :id OR DP.SYS_GROUP_ID=:id )");
    	}
    	
    	
    	
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	query.addScalar("id", new LongType());
    	query.addScalar("code", new StringType());
    	query.addScalar("text", new StringType());
    	query.addScalar("parentId", new LongType());
    	query.addScalar("status", new StringType());
    	query.addScalar("path", new StringType());
    	query.addScalar("parentName", new StringType());
    	query.addScalar("effectDate", new DateType());
    	query.addScalar("endDate", new DateType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
  		sqlCount.append(sql.toString());
  		sqlCount.append(")");
  		
  		if(obj.getPage()!=null && obj.getPageSize()!=null){
  			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
	  		query.setMaxResults(obj.getPageSize().intValue());
  		}
  		
	
    	if(StringUtils.isNotEmpty(obj.getCode())){
    		query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getName())){
    		query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
    	}
    	
    	if(obj.getId()!=null){
    		query.setParameter("id", obj.getId());
    	}
    	return query.list();
    }
    //AutocompleteDept
    @SuppressWarnings("unchecked")
	public List<DepartmentDTO> getForAutoCompleteDept(DepartmentDTO obj) {
		String sql = "SELECT "
			+ " ST.SYS_GROUP_ID id"
			+ ",(ST.CODE ||'-' || ST.NAME) text"	
			+ " ,ST.NAME name"		
			+ " ,ST.CODE code"
			+ " FROM VPS_OWNER.SYS_GROUP ST"
			+ " WHERE ST.STATUS=1 ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		
			stringBuilder.append(" AND ROWNUM <=10 ");
			if(StringUtils.isNotEmpty(obj.getName())){
			stringBuilder.append(" AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
			}
		
		
		stringBuilder.append(" ORDER BY ST.CODE");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("id", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("text", new StringType());
		query.addScalar("code", new StringType());
	
		query.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("value", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		return query.list();
	}
	
    //End-AutocompleteDept
    
    public DepartmentDTO getOne(Long id){
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT DP.SYS_GROUP_ID id,"
    			+ "DP.CODE code,(DP.CODE ||'-' || DP.NAME) text, DP.PARENT_ID parentId, DP.STATUS status, DP.PATH path,"
    			+ "DP.EFFECT_DATE effectDate, DP.END_DATE endDate, DP1.NAME parentName"
    			+ " FROM VPS_OWNER.SYS_GROUP DP "
    			+ " LEFT Join VPS_OWNER.SYS_GROUP DP1 "
    			+ " On DP1.SYS_GROUP_ID=DP.PARENT_ID "
    			+ " WHERE DP.STATUS=1 AND DP.SYS_GROUP_ID=:id ");
    	
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	query.addScalar("id", new LongType());
    	query.addScalar("code", new StringType());
    	query.addScalar("text", new StringType());
    	query.addScalar("parentId", new LongType());
    	query.addScalar("status", new StringType());
    	query.addScalar("path", new StringType());
    	query.addScalar("parentName", new StringType());
    	query.addScalar("effectDate", new DateType());
    	query.addScalar("endDate", new DateType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(DepartmentDTO.class));
    	
    	query.setParameter("id", id);
    	
    	return (DepartmentDTO) query.uniqueResult();
    }
}
