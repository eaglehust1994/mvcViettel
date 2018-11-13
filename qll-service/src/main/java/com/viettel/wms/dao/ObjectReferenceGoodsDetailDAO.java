/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ObjectReferenceGoodsDetailBO;
import com.viettel.wms.dto.ObjectReferenceGoodsDTO;
import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;
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
@Repository("objectReferenceGoodsDetailDAO")
public class ObjectReferenceGoodsDetailDAO extends BaseFWDAOImpl<ObjectReferenceGoodsDetailBO, Long> {

    public ObjectReferenceGoodsDetailDAO() {
        this.model = new ObjectReferenceGoodsDetailBO();
    }

    public ObjectReferenceGoodsDetailDAO(Session session) {
        this.session = session;
    }
    
public List<ObjectReferenceGoodsDetailDTO> getGoodsDetail(ObjectReferenceGoodsDetailDTO obj){
    	
    	StringBuilder sql = new StringBuilder("SELECT "
				+ "orgd.OBJECT_REF_GOODS_DETAIL_ID id,"
				+ "orgd.GOODS_ID goodsId,"
				+ "orgd.GOODS_CODE goodsCode,"
				+ "orgd.GOODS_NAME goodsName,"
				+ "orgd.AMOUNT amount,"
				+ "orgd.SERIAL serial,"
				+ "orgd.PRICE price,"
				+ "orgd.MANUFACTURER manufacturer,"
				+ "orgd.PRODUCER_COUNTRY producerCountry,"
				+ "orgd.PART_NUMBER partNumber"
				+ " FROM WMS_OWNER_KTTS.OBJECT_REFERENCE_GOODS_DETAIL orgd"
				+ " JOIN WMS_OWNER_KTTS.OBJECT_REFERENCE oor ON oor.OBJECT_REFERENCE_ID = orgd.OBJECT_REFERENCE_ID "
				+ " WHERE orgd.GOODS_ID =:goodsId AND orgd.OBJECT_REFERENCE_ID = :objectReferenceId");
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("id", new LongType());
		query.addScalar("goodsId",new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("amount",new DoubleType());
		query.addScalar("serial",new StringType());
		query.addScalar("manufacturer",new StringType());
		query.addScalar("producerCountry",new StringType());
		query.addScalar("partNumber",new StringType());
		query.addScalar("price",new DoubleType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectReferenceGoodsDetailDTO.class));
		
		query.setParameter("goodsId", obj.getGoodsId());
		query.setParameter("objectReferenceId", obj.getObjectReferenceId());
    	
		return query.list();
    	
    }
}
