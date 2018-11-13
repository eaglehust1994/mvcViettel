/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.AttachmentBO;
import com.viettel.wms.dto.AttachmentDTO;
import com.viettel.wms.dto.UserRoleDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("attachmentDAO")
public class AttachmentDAO extends BaseFWDAOImpl<AttachmentBO, Long> {

	public AttachmentDAO() {
		this.model = new AttachmentBO();
	}

	public AttachmentDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<AttachmentDTO> doSearchFile(AttachmentDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT" 
	+ " WA.ATTACTMENT_ID attactmentId," 
				+ " WA.OBJECT_ID objectId,"
				+ " WA.FILEPATH filePath,"
				+ " WA.TYPE type," 
				+ " WA.APP_PARAM_CODE appParamCode," 
				+ " WA.CODE code," + " WA.NAME name,"
				+ " WA.ENCRYT_NAME encrytName," 
				+ " WA.DESCRIPTION description," 
				+ " WA.STATUS status "
				+ " FROM WMS_OWNER_KTTS.ATTACHMENT WA WHERE 1=1");

		if (obj.getObjectId() != null) {
			sql.append(" AND WA.OBJECT_ID =:objectId ");
		}

		if (obj.getType() != null) {
			sql.append(" AND WA.TYPE = :type");
		}

		if (StringUtils.isNotEmpty(obj.getStatus())) {
			sql.append(" AND WA.STATUS = :status");
		}
		sql.append(" ORDER BY WA.CODE");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("attactmentId", new LongType());
		query.addScalar("objectId", new LongType());
		query.addScalar("type", new StringType());
		query.addScalar("appParamCode", new StringType());
		query.addScalar("filePath", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("encrytName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(AttachmentDTO.class));

		if (obj.getObjectId() != null) {
			query.setParameter("objectId", obj.getObjectId());
			queryCount.setParameter("objectId", obj.getObjectId());
		}

		if (obj.getType() != null) {
			sql.append(" AND WA.TYPE = :type");
			query.setParameter("type", obj.getType());
			queryCount.setParameter("type", obj.getType());
		}

		if (StringUtils.isNotEmpty(obj.getStatus())) {
			query.setParameter("status", ValidateUtils.validateKeySearch(obj.getStatus()));
			queryCount.setParameter("status", ValidateUtils.validateKeySearch(obj.getStatus()));
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttachmentDTO> doSearch(AttachmentDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT" 
				+ " WA.ATTACTMENT_ID attactmentId," 
				+ " WA.OBJECT_ID objectId,"
				+ " WA.FILEPATH filePath,"
				+ " WA.TYPE type," 
				+ " WA.APP_PARAM_CODE appParamCode," 
				+ " WA.CODE code," 
				+ " WA.NAME name,"
				+ " WA.ENCRYT_NAME encrytName," 
				+ " WA.DESCRIPTION description," 
				+ " WA.STATUS status, "
				+ " APP.NAME appParamName "
				+ " FROM WMS_OWNER_KTTS.ATTACHMENT WA"
				+ " JOIN CTCT_CAT_OWNER.APP_PARAM APP ON APP.CODE=WA.APP_PARAM_CODE"
				+ " WHERE 1=1 ");

		if (obj.getObjectId() != null) {
			sql.append(" AND WA.OBJECT_ID =:objectId ");
		}
		if (obj.getType() != null) {
			sql.append(" AND WA.TYPE = :type ");
		}
		if (StringUtils.isNotEmpty(obj.getStatus())) {
			sql.append(" AND WA.STATUS = :status ");
		}
		sql.append(" ORDER BY WA.CODE");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("attactmentId", new LongType());
		query.addScalar("objectId", new LongType());
		query.addScalar("type", new StringType());
		query.addScalar("filePath", new StringType());
		query.addScalar("appParamCode", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("encrytName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("appParamName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(AttachmentDTO.class));

		if (obj.getObjectId() != null) {
			query.setParameter("objectId", obj.getObjectId());
		}

		if (obj.getType() != null) {
			sql.append(" AND WA.TYPE = :type");
			query.setParameter("type", obj.getType());
		}

		if (StringUtils.isNotEmpty(obj.getStatus())) {
			query.setParameter("status", ValidateUtils.validateKeySearch(obj.getStatus()));
		}
		return query.list();
		
	}
	
	public List<AttachmentDTO> getById(AttachmentDTO obj){
		StringBuilder sql = new StringBuilder("SELECT" 
				+ " WA.ATTACTMENT_ID attactmentId FROM WMS_OWNER_KTTS.ATTACHMENT WA where WA.OBJECT_ID=:objectId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("attactmentId",new LongType());
		query.setResultTransformer(Transformers.aliasToBean(AttachmentDTO.class));
		if(obj.getObjectId()!=null){
			query.setLong("objectId",obj.getObjectId());
		}
		return query.list();
	}
	public void delete(AttachmentDTO obj){
		StringBuilder sql=new StringBuilder("Delete from  WMS_OWNER_KTTS.ATTACHMENT WA where WA.ATTACTMENT_ID= :attactmentId");
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	query.setResultTransformer(Transformers.aliasToBean(AttachmentDTO.class));
    	query.setLong("attactmentId",obj.getAttactmentId());
    	int result=query.executeUpdate();
	}
}
