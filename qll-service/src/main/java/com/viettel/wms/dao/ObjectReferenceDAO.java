/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ObjectReferenceBO;
//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.ObjectReferenceDTO;
import com.viettel.wms.dto.ShipmentDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.wms.bo.ObjectReferenceBO;
import com.viettel.wms.dto.ObjectReferenceDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("objectReferenceDAO")
public class ObjectReferenceDAO extends BaseFWDAOImpl<ObjectReferenceBO, Long> {

	public ObjectReferenceDAO() {
		this.model = new ObjectReferenceBO();
	}

	public ObjectReferenceDAO(Session session) {
		this.session = session;
	}

	public ObjectReferenceDTO getDataKCS(ObjectReferenceDTO obj) {
		StringBuilder sql = new StringBuilder( "SELECT "
				+ " WOOR.OBJECT_REFERENCE_ID objectReferenceId,"
				+ " WOOR.PARENT_ID  parentId,"
				+ " WOOR.OBJECT_ID objectId,"
				+ " WOOR.TYPE type,"
				+ " WOOR.OBJECT_CODE objectCode,"
				+ " WOOR.OBJECT_NAME objectName,"
				+ " WOOR.OBJECT_DESCRIPTION objectDescription,"
				+ " WOOR.OBJECT_STATUS objectStatus,"
				+ " WOOR.OBJECT_CREATED_DATE objectCreatedDate,"
				+ " WOOR.OBJECT_CREATOR objectCreator "
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE WOOR "
				+ " WHERE 1=1");
		
		if(obj.getObjectId() != null)
		{
			sql.append(" AND WOOR.OBJECT_ID = :objectId ");
		}
		
		if(obj.getType() != null)
		{
			sql.append(" AND WOOR.TYPE = :type ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("objectReferenceId",new LongType());
		query.addScalar("parentId",new LongType());
		query.addScalar("objectId",new LongType());
		query.addScalar("type",new StringType());
		query.addScalar("objectCode",new StringType());
		query.addScalar("objectName",new StringType());
		query.addScalar("objectDescription",new StringType());
		query.addScalar("objectStatus",new StringType());
		query.addScalar("objectCreatedDate",new StringType());
		query.addScalar("objectCreator",new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ObjectReferenceDTO.class));
		if(obj.getObjectId() != null)
		{
			query.setParameter("objectId",obj.getObjectId());
		}
		if(obj.getType() != null)
		{
			query.setParameter("type",obj.getType());
		}

		return (ObjectReferenceDTO) query.uniqueResult();
	}
	
	//tim ma 
	public List<String> getGoodsInfoBeforeWarrantyCode(String code){
		StringBuilder sql = new StringBuilder("SELECT oor.OBJECT_CODE code"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE oor "
				+ "WHERE oor.TYPE = 3");
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
			sql.append(" AND upper(OBJECT_CODE) LIKE upper(:code)");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", new StringType());
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
		query.setParameter("code", "%"+code+"%");
		}
		return query.list();
	}
	
	public List<String> getGoodsInfoAfterWarrantyCode(String code){
		StringBuilder sql = new StringBuilder("SELECT oor.OBJECT_CODE code"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE oor "
				+ "WHERE oor.TYPE = 4");
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
			sql.append(" AND upper(OBJECT_CODE) LIKE upper(:code)");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", new StringType());
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
		query.setParameter("code", "%"+code+"%");
		}
		return query.list();
	}
	
	public List<String> getGoodsInfoFromConstructionCode(String code){
		StringBuilder sql = new StringBuilder("SELECT oor.OBJECT_CODE code"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE oor "
				+ "WHERE oor.TYPE = 5");
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
			sql.append(" AND upper(OBJECT_CODE) LIKE upper(:code)");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", new StringType());
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
		query.setParameter("code", "%"+code+"%");
		}
		return query.list();
	}
	public List<String> getGoodsInfoFromDepartmentCode(String code){
		StringBuilder sql = new StringBuilder("SELECT oor.OBJECT_CODE code"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE oor "
				+ "WHERE oor.TYPE = 6");
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
			sql.append(" AND upper(OBJECT_CODE) LIKE upper(:code)");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", new StringType());
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
		query.setParameter("code", "%"+code+"%");
		}
		return query.list();
	}
	public List<String> getGoodsInfoFromLoanCode(String code){
		StringBuilder sql = new StringBuilder("SELECT oor.OBJECT_CODE code"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE oor "
				+ "WHERE oor.TYPE = 7");
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
			sql.append(" AND upper(OBJECT_CODE) LIKE upper(:code)");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", new StringType());
		if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
		query.setParameter("code", "%"+code+"%");
		}
		return query.list();
	}
	
}
