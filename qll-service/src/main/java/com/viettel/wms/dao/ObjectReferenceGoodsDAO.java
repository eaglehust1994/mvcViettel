/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ObjectReferenceGoodsBO;
import com.viettel.wms.dto.ObjectReferenceDetailDTO;
import com.viettel.wms.dto.ObjectReferenceGoodsDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("objectReferenceGoodsDAO")
public class ObjectReferenceGoodsDAO extends BaseFWDAOImpl<ObjectReferenceGoodsBO, Long> {

    public ObjectReferenceGoodsDAO() {
        this.model = new ObjectReferenceGoodsBO();
    }

    public ObjectReferenceGoodsDAO(Session session) {
        this.session = session;
    }
    
public List<ObjectReferenceGoodsDTO> getGoodsInfoBeforeWarrantyByCode(String code){
    	
    	StringBuilder sql = new StringBuilder("SELECT "
				+ "org.OBJECT_REFERENCE_GOODS_ID id,"
				+ "org.GOODS_ID goodsId,"
				+ "org.GOODS_CODE goodsCode,"
				+ "org.GOODS_NAME goodsName,"
				+ "org.GOODS_UNIT_ID goodsUnitId,"
				+ "org.AMOUNT amount,"
				+ "org.GOODS_UNIT_NAME goodsUnitName,"
				+ "oor.OBJECT_REFERENCE_ID objectReferenceId,"
				+ "org.GOODS_STATE_NAME goodsStateName"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE_GOODS org"
				+ " JOIN WMS_OWNER_KTTS.OBJECT_REFERENCE oor ON oor.OBJECT_REFERENCE_ID = org.OBJECT_REFERENCE_ID "
				+ " WHERE oor.OBJECT_CODE =:code AND oor.TYPE = 3 ");
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("id", new LongType());
		query.addScalar("goodsId",new LongType());
		query.addScalar("objectReferenceId",new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("amount",new DoubleType());
		query.addScalar("goodsUnitName",new StringType());
		query.addScalar("goodsStateName",new StringType());
		query.addScalar("goodsUnitId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectReferenceGoodsDTO.class));
		
		query.setParameter("code", code);
    	
		return query.list();
    	
    }
    
    public List<ObjectReferenceGoodsDTO> getGoodsInfoAfterWarrantyByCode(String code){
    	
    	StringBuilder sql = new StringBuilder("SELECT "
				+ "org.OBJECT_REFERENCE_GOODS_ID id,"
				+ "org.GOODS_ID goodsId,"
				+ "org.GOODS_CODE goodsCode,"
				+ "org.GOODS_NAME goodsName,"
				+ "org.AMOUNT amount,"
				+ "org.GOODS_UNIT_ID goodsUnitId,"
				+ "org.GOODS_UNIT_NAME goodsUnitName,"
				+ "oor.OBJECT_REFERENCE_ID objectReferenceId,"
				+ "org.GOODS_STATE_NAME goodsStateName"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE_GOODS org"
				+ " JOIN WMS_OWNER_KTTS.OBJECT_REFERENCE oor ON oor.OBJECT_REFERENCE_ID = org.OBJECT_REFERENCE_ID "
				+ " WHERE oor.OBJECT_CODE =:code AND oor.TYPE = 4 ");
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("id", new LongType());
		query.addScalar("goodsId",new LongType());
		query.addScalar("objectReferenceId",new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("amount",new DoubleType());
		query.addScalar("goodsUnitName",new StringType());
		query.addScalar("goodsStateName",new StringType());
		query.addScalar("goodsUnitId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectReferenceGoodsDTO.class));
		
		query.setParameter("code", code);
    	
		return query.list();
    	
    }
    
    public List<ObjectReferenceGoodsDTO> getGoodsInfoFromConstructionByCode(String code){
    	
    	StringBuilder sql = new StringBuilder("SELECT "
				+ "org.OBJECT_REFERENCE_GOODS_ID id,"
				+ "org.GOODS_ID goodsId,"
				+ "org.GOODS_CODE goodsCode,"
				+ "org.GOODS_NAME goodsName,"
				+ "org.AMOUNT amount,"
				+ "org.GOODS_UNIT_ID goodsUnitId,"
				+ "org.GOODS_UNIT_NAME goodsUnitName,"
				+ "oor.OBJECT_REFERENCE_ID objectReferenceId,"
				+ "org.GOODS_STATE_NAME goodsStateName"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE_GOODS org"
				+ " JOIN WMS_OWNER_KTTS.OBJECT_REFERENCE oor ON oor.OBJECT_REFERENCE_ID = org.OBJECT_REFERENCE_ID "
				+ " WHERE oor.OBJECT_CODE =:code AND oor.TYPE = 5 ");
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("id", new LongType());
		query.addScalar("goodsId",new LongType());
		query.addScalar("objectReferenceId",new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("amount",new DoubleType());
		query.addScalar("goodsUnitName",new StringType());
		query.addScalar("goodsStateName",new StringType());
		query.addScalar("goodsUnitId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectReferenceGoodsDTO.class));
		
		query.setParameter("code", code);
    	
		return query.list();
    	
    }
    
    public List<ObjectReferenceGoodsDTO> getGoodsInfoFromDepartmentByCode(String code){
    	
    	StringBuilder sql = new StringBuilder("SELECT "
				+ "org.OBJECT_REFERENCE_GOODS_ID id,"
				+ "org.GOODS_ID goodsId,"
				+ "org.GOODS_CODE goodsCode,"
				+ "org.GOODS_NAME goodsName,"
				+ "org.AMOUNT amount,"
				+ "org.GOODS_UNIT_ID goodsUnitId,"
				+ "org.GOODS_UNIT_NAME goodsUnitName,"
				+ "oor.OBJECT_REFERENCE_ID objectReferenceId,"
				+ "org.GOODS_STATE_NAME goodsStateName"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE_GOODS org"
				+ " JOIN WMS_OWNER_KTTS.OBJECT_REFERENCE oor ON oor.OBJECT_REFERENCE_ID = org.OBJECT_REFERENCE_ID "
				+ " WHERE oor.OBJECT_CODE =:code AND oor.TYPE = 6 ");
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("id", new LongType());
		query.addScalar("goodsId",new LongType());
		query.addScalar("objectReferenceId",new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("amount",new DoubleType());
		query.addScalar("goodsUnitName",new StringType());
		query.addScalar("goodsStateName",new StringType());
		query.addScalar("goodsUnitId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectReferenceGoodsDTO.class));
		
		query.setParameter("code", code);
    	
		return query.list();
    	
    }
    
    public List<ObjectReferenceGoodsDTO> getGoodsInfoFromLoanByCode(String code){
    	
    	StringBuilder sql = new StringBuilder("SELECT "
				+ "org.OBJECT_REFERENCE_GOODS_ID id,"
				+ "org.GOODS_ID goodsId,"
				+ "org.GOODS_CODE goodsCode,"
				+ "org.GOODS_NAME goodsName,"
				+ "org.AMOUNT amount,"
				+ "org.GOODS_UNIT_ID goodsUnitId,"
				+ "org.GOODS_UNIT_NAME goodsUnitName,"
				+ "oor.OBJECT_REFERENCE_ID objectReferenceId,"
				+ "org.GOODS_STATE_NAME goodsStateName"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE_GOODS org"
				+ " JOIN WMS_OWNER_KTTS.OBJECT_REFERENCE oor ON oor.OBJECT_REFERENCE_ID = org.OBJECT_REFERENCE_ID "
				+ " WHERE oor.OBJECT_CODE =:code AND oor.TYPE = 7 ");
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("id", new LongType());
		query.addScalar("goodsId",new LongType());
		query.addScalar("objectReferenceId",new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("amount",new DoubleType());
		query.addScalar("goodsUnitName",new StringType());
		query.addScalar("goodsStateName",new StringType());
		query.addScalar("goodsUnitId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectReferenceGoodsDTO.class));
		
		query.setParameter("code", code);
    	
		return query.list();
    	
    }
}
