/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.OddCableBO;
import com.viettel.wms.dto.OddCableDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;


@Repository("oddCableDAO")
public class OddCableDAO extends BaseFWDAOImpl<OddCableBO, Long> {

    public OddCableDAO() {
        this.model = new OddCableBO();
    }

    public OddCableDAO(Session session) {
        this.session = session;
    }

//    Tìm kiếm trong bảng OddCable  -- TUANNB
	public List<OddCableDTO> doSearch(OddCableDTO obj) {
		// TODO Auto-generated method stub
		StringBuilder sql =new StringBuilder
				("SELECT GOODS_CODE goodsCode, "
						+ "GOODS_ID goodsId, "
						+ "GOODS_TYPE goodsType, "
						+ "GOODS_NAME goodsName ,"
						+ "GOODS_UNIT_ID goodsUnitId, "
						+ "GOODS_UNIT_NAME goodsUnitName, "
						+ "AMOUNT_MINIMUM amountMinimum, "
						+ "ODD_CABLE_ID oddCableId, "
						+ "STATUS status "
						+ "FROM WMS_OWNER_KTTS.ODD_CABLE OC WHERE 1=1");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(GOODS_NAME) LIKE upper(:keySearch) escape '&' OR upper(GOODS_CODE) LIKE upper(:keySearch) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())) {
			sql.append(" AND OC.STATUS = :status");
		}

		sql.append(" ORDER BY GOODS_CODE");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsType", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("amountMinimum", new DoubleType());
		query.addScalar("oddCableId", new LongType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(OddCableDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(""+obj.getKeySearch())+"%");
			queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(""+obj.getKeySearch())+"%");
		}
		if (StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())) {
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		
		query.setResultTransformer(Transformers.aliasToBean(OddCableDTO.class));	
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
	
	
	public OddCableDTO getbycode(String goodsCode, String goodsName){
		StringBuilder sql = new StringBuilder("SELECT ODD_CABLE_ID oddCableId,"
				+ "GOODS_TYPE goodsType,"
				+ "GOODS_TYPE_NAME goodsTypeName,"
				+ "GOODS_ID goodsId,"
				+ "GOODS_CODE goodsCode, "
				+ "GOODS_NAME goodsName, "
				+ "GOODS_UNIT_NAME goodsUnitName, "
				+ "GOODS_UNIT_ID goodsUnitId, "
				+ "AMOUNT_MINIMUM amountMinimum, "
				+ "STATUS status "
				+ " FROM WMS_OWNER_KTTS.ODD_CABLE WHERE upper(GOODS_CODE)=upper(:goodsCode) AND upper(GOODS_NAME)=upper(:goodsName)");
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		

		query.addScalar("oddCableId", new LongType());
		query.addScalar("goodsType", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("amountMinimum", new DoubleType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(OddCableDTO.class));
		query.setParameter("goodsCode", goodsCode);
		query.setParameter("goodsName", goodsName);
		return (OddCableDTO) query.uniqueResult();
	}
}
