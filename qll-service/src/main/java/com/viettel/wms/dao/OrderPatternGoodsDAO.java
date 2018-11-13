/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.OrderPatternGoodsBO;
import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.wms.dto.OrderPatternGoodsDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TuanNB
 * @version 1.0
 * @since 09-Sep-17 09:09 PM
 */
@Repository("orderPatternGoodsDAO")
public class OrderPatternGoodsDAO extends BaseFWDAOImpl<OrderPatternGoodsBO, Long> {

	
    public OrderPatternGoodsDAO() {
        this.model = new OrderPatternGoodsBO();
    }

    public OrderPatternGoodsDAO(Session session) {
        this.session = session;
    }
    
//  Tìm kiếm dữ liệu trong bảng ORDER_PATTERN
	@SuppressWarnings("unchecked")
	public List<OrderPatternGoodsDTO> doSearch(OrderPatternGoodsDTO obj) {
		StringBuilder sql =new StringBuilder
				("SELECT NAME name ,"
						+ "OP.CREATED_USER_NAME createdUserName ,"
						+ "OP.DESCRIPTION description ,"
						+ "OP.ORDER_PATTERN_ID orderPatternId "
						+ "FROM WMS_OWNER_KTTS.ORDER_PATTERN OP WHERE 1=1 ");
		
		sql.append(" ORDER BY NAME");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());		
		query.addScalar("name",new StringType());
		query.addScalar("createdUserName",new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("orderPatternId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(OrderPatternDTO.class));	
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
//		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderPatternGoodsDTO> getPatternGoodsByOrderPatternId(OrderPatternGoodsDTO obj) {
		StringBuilder sql =new StringBuilder
				("SELECT NAME name ,"
						+ "OP.CREATED_USER_NAME createdUserName, "
						+ "OP.DESCRIPTION description, "
						+ "OP.ORDER_PATTERN_ID orderPatternId, "
						+ " OPD.GOODS_ID goodsId, "
						+ "OPD.GOODS_CODE goodsCode, "
						+ "OPD.GOODS_NAME goodsName, "
						+ "OPD.GOODS_TYPE goodsType, "
						+ "OPD.GOODS_TYPE_NAME goodsTypeName, "
						+ "OPD.GOODS_IS_SERIAL goodsIsSerial, "
						+ "OPD.GOODS_STATE goodsState, "
						+ "OPD.GOODS_STATE_NAME goodsStateName, "
						+ "OPD.GOODS_UNIT_NAME goodsUnitName, "
						+ "OPD.GOODS_UNIT_ID goodsUnitId, "
						+ "OPD.AMOUNT amount "
						+ "FROM WMS_OWNER_KTTS.ORDER_PATTERN OP "
						+ "INNER JOIN WMS_OWNER_KTTS.ORDER_PATTERN_GOODS OPD ON  OPD.ORDER_PATTERN_ID = OP.ORDER_PATTERN_ID ");
		
		if (obj.getOrderPatternId() != null) {
			sql.append(" AND OP.ORDER_PATTERN_ID = :orderPatternId");
		}		
		sql.append(" ORDER BY NAME");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());		
		//query.addScalar("name",new StringType());
		//query.addScalar("createdUserName",new StringType());
		//query.addScalar("description", new StringType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsType", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsIsSerial", new StringType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("amount", new DoubleType());
		
		query.setResultTransformer(Transformers.aliasToBean(OrderPatternGoodsDTO.class));	
		if (obj.getOrderPatternId() != null) {
			query.setLong("orderPatternId", obj.getOrderPatternId());
			queryCount.setLong("orderPatternId", obj.getOrderPatternId());
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
//		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}
}
