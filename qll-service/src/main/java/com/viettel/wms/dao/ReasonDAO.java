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
import org.hibernate.type.TimestampType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.bo.ReasonBO;
import com.viettel.wms.dto.ReasonDTO;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("reasonDAO")
public class ReasonDAO extends BaseFWDAOImpl<ReasonBO, Long> {

    public ReasonDAO() {
        this.model = new ReasonBO();
    }

    public ReasonDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public List<ReasonDTO> doSearch(ReasonDTO obj){
		StringBuilder sql = new StringBuilder("SELECT"
				+ " RS.CREATED_BY createdBy,"
				+ " RS.CREATED_DATE createdDate,"
				+ " RS.REASON_ID reasonId,"
				+ " RS.CODE code,"
				+ " RS.NAME name,"
				+ " RS.APPLY apply,"
				+ " RS.STATUS status, "
				+ " CA.NAME nameApply "
				+ " FROM CAT_OWNER.REASON RS "
				+ " LEFT JOIN CTCT_CAT_OWNER.APP_PARAM CA"
				+ " ON RS.APPLY = CA.CODE AND CA.PAR_TYPE='REASON_APPLY' AND CA.STATUS='1' AND CA.PAR_ORDER ='R' "
				+ " WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			sql.append(" AND (upper(RS.CODE) LIKE upper(:keySearch) OR upper(RS.NAME) LIKE upper(:keySearch))");
		}
		
		if(StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())){
			sql.append(" AND RS.STATUS = :status");
		}
		
		if(obj.getListApply().size()>0){
			sql.append(" AND RS.APPLY IN (:listApply)");
		}
		
		sql.append(" ORDER BY CA.PAR_ORDER");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("reasonId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("apply", new StringType());
		query.addScalar("status" , new StringType());
		query.addScalar("nameApply", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new TimestampType());
		
		query.setResultTransformer(Transformers.aliasToBean(ReasonDTO.class));
		
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
			queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(obj.getKeySearch())+"%");
		}
		
		if(StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())){
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		
		if(obj.getListApply().size()>0){
			query.setParameterList("listApply", obj.getListApply());
			queryCount.setParameterList("listApply", obj.getListApply());
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    public ReasonDTO getbycode(String code){
		StringBuilder sql = new StringBuilder("SELECT REASON_ID reasonId,"
				+ "CODE code,"
				+ "NAME name,"
				+ "APPLY apply,"
				+ "STATUS status "
				+ " FROM CAT_OWNER.REASON WHERE upper(CODE)=upper(:code) AND STATUS ='1'");
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		

		query.addScalar("reasonId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("apply", new StringType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ReasonDTO.class));
		
		query.setParameter("code", code);
		
		return (ReasonDTO) query.uniqueResult();
	}
    
    public ReasonDTO getbyId(Long id){
		StringBuilder sql = new StringBuilder("SELECT REASON_ID reasonId,"
				+ "CODE code,"
				+ "NAME name,"
				+ "APPLY apply,"
				+ "STATUS status "
				+ " FROM CAT_OWNER.REASON WHERE upper(REASON_ID)= (:reasonId)");
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		

		query.addScalar("reasonId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("apply", new StringType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ReasonDTO.class));
		
		query.setParameter("reasonId", id);
		
		return (ReasonDTO) query.uniqueResult();
	}
    
    @SuppressWarnings("unchecked")
	public List<ReasonDTO> getForComboBox(ReasonDTO obj){
		StringBuilder sql = new StringBuilder("SELECT COA.REASON_ID reasonId,"
				+ "COA.CODE code, "
				+ "COA.NAME name, "
				+ "COA.STATUS status "
				+ "FROM CAT_OWNER.REASON COA WHERE 1=1 ");
		if(StringUtils.isNotEmpty(obj.getStatus())){
			sql.append(" AND COA.STATUS = :status ");
		}
		
		if(obj.getApply() != null ){
			sql.append(" AND COA.APPLY = :apply");
		}
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		query.addScalar("reasonId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ReasonDTO.class));
		
		if(StringUtils.isNotEmpty(obj.getStatus())){
			query.setParameter("status", obj.getStatus());
		}
		
		if(obj.getApply() != null ){
			query.setParameter("apply", obj.getApply());
		}
		
		return query.list();
	}
    
}
