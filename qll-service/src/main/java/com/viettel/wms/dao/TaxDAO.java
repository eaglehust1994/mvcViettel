/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.wms.bo.TaxBO;
import com.viettel.wms.dto.TaxDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("taxDAO")
public class TaxDAO extends BaseFWDAOImpl<TaxBO, Long> {

    public TaxDAO() {
        this.model = new TaxBO();
    }

    public TaxDAO(Session session) {
        this.session = session;
    }
    
    public List<TaxDTO> doSearch(TaxDTO obj){
		StringBuilder sql = new StringBuilder("Select CT.TAX_ID taxId,"
				+ "CT.CODE code,"
				+ "CT.NAME name,"
				+ "CT.VALUE value,"
				+ "CT.TYPE type,"
				+ "CT.IGNORE ignore,"
				+ "CT.APPLY apply,"
				+ "CT.STATUS status"
				+ " FROM CAT_OWNER.TAX CT  "
				+ " WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getStatus()) && !"0".equals(obj.getStatus())){
			sql.append(" AND CT.STATUS = :status");
		}
		if(StringUtils.isNotEmpty(obj.getType()) && !"2".equals(obj.getType())){
			sql.append(" AND CT.TYPE = :type");
		}
		
		if(StringUtils.isNotEmpty(obj.getIgnore()) && !"1".equals(obj.getIgnore())){
			sql.append(" AND CT.IGNORE  = :ignore");
		}
		
		if(StringUtils.isNotEmpty(obj.getApply()) && !"0".equals(obj.getApply())){
			sql.append(" AND CT.APPLY   = :apply");
		}
		
		sql.append(" ORDER BY CT.TAX_ID DESC ");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("taxId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("value", new DoubleType());
		query.addScalar("type", new StringType());
		query.addScalar("ignore", new StringType());
		query.addScalar("apply", new StringType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TaxDTO.class));
		
		if(StringUtils.isNotEmpty(obj.getStatus()) && !"0".equals(obj.getStatus())){
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		if(StringUtils.isNotEmpty(obj.getType()) && !"2".equals(obj.getType())){
			query.setParameter("type", obj.getType());
			queryCount.setParameter("type", obj.getType());
		}
		if(StringUtils.isNotEmpty(obj.getIgnore()) && !"1".equals(obj.getIgnore())){
			query.setParameter("ignore", obj.getIgnore());
			queryCount.setParameter("ignore", obj.getIgnore());
		}
		if(StringUtils.isNotEmpty(obj.getApply()) && !"0".equals(obj.getApply())){
			query.setParameter("apply", obj.getApply());
			queryCount.setParameter("apply", obj.getApply());
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    public List<TaxDTO> doSearchBytax(TaxDTO obj){
		StringBuilder sql = new StringBuilder("SELECT CT.TAX_ID taxId,CT.CODE code,CT.NAME name,CT.VALUE value,CT.TYPE type,"
				+ "CT.IGNORE ignore,CT.APPLY apply,CT.STATUS status,"
				+ "CT.CREATED_BY createdBy,"
				+ "CT.CREATED_DATE createdDate,"
				+ "CT.UPDATED_BY updatedBy,"
				+ "CT.UPDATED_DATE updatedDate "
				+ "FROM CAT_OWNER.TAX CT "
				+ "WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			sql.append(" AND (upper(CT.CODE ) LIKE upper(:keySearch) OR upper(CT.NAME) LIKE upper(:keySearch))");
		}
		
		if(StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())){
			sql.append(" AND CT.STATUS = :status");
		}
		if(StringUtils.isNotEmpty(obj.getType()) && !"3".equals(obj.getType())){
			sql.append(" AND CT.TYPE = :type");
		}
		
		if(StringUtils.isNotEmpty(obj.getIgnore()) && !"1".equals(obj.getIgnore())){
			sql.append(" AND CT.IGNORE  = :ignore");
		}
		
		if(StringUtils.isNotEmpty(obj.getApply()) && !"0".equals(obj.getApply())){
			sql.append(" AND CT.APPLY   = :apply");
		}
		
		sql.append(" ORDER BY CT.TAX_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("taxId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("value", new DoubleType());
		query.addScalar("type", new StringType());
		query.addScalar("ignore", new StringType());
		query.addScalar("apply", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("updatedBy",new LongType());
		query.addScalar("createdBy",new LongType());
		query.addScalar("createdDate",new DateType());
		query.addScalar("updatedDate",new DateType());
		
		query.setResultTransformer(Transformers.aliasToBean(TaxDTO.class));
		
		if (obj.getShipmentId() != null) {
			query.setParameter("shipmentId", obj.getShipmentId());
			queryCount.setParameter("shipmentId", obj.getShipmentId());
		}
		
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
		query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		}
		if(StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())){
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		if(StringUtils.isNotEmpty(obj.getType()) && !"3".equals(obj.getType())){
			query.setParameter("type", obj.getType());
			queryCount.setParameter("type", obj.getType());
		}
		if(StringUtils.isNotEmpty(obj.getIgnore()) && !"1".equals(obj.getIgnore())){
			query.setParameter("ignore", obj.getIgnore());
			queryCount.setParameter("ignore", obj.getIgnore());
		}
		if(StringUtils.isNotEmpty(obj.getApply()) && !"0".equals(obj.getApply())){
			query.setParameter("apply", obj.getApply());
			queryCount.setParameter("apply", obj.getApply());
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
	
	
	public TaxDTO getbycode(String code){
		StringBuilder sql = new StringBuilder("SELECT TAX_ID taxId,"
				+ "CODE code,"
				+ "NAME name,"
				+ "VALUE value,"
				+ "TYPE type,"
				+ "IGNORE ignore,"
				+ "APPLY apply,"
				+ "STATUS status "
				+ " FROM CAT_OWNER.TAX WHERE upper(CODE)=upper(:code) and STATUS='1'");
		SQLQuery query= getSession().createSQLQuery(sql.toString());

		query.addScalar("taxId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("value", new DoubleType());
		query.addScalar("type", new StringType());
		query.addScalar("ignore", new StringType());
		query.addScalar("apply", new StringType());
		query.addScalar("status", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(TaxDTO.class));
		
		query.setParameter("code", code);
		
		return (TaxDTO) query.uniqueResult();
	}
}
