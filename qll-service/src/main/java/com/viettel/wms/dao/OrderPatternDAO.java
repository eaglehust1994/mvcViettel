/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.OrderPatternBO;
import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.wms.dto.OrderPatternGoodsDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

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

/**
 * @author TuanNB
 * @version 1.0
 * @since 09-Sep-17 09:09 PM
 */
@Repository("orderPatternDAO")
public class OrderPatternDAO extends BaseFWDAOImpl<OrderPatternBO, Long> {

    public OrderPatternDAO() {
        this.model = new OrderPatternBO();
    }

    public OrderPatternDAO(Session session) {
        this.session = session;
    }

//  Tìm kiếm dữ liệu trong bảng ORDER_PATTERN -- TUANNB
	@SuppressWarnings("unchecked")
	public List<OrderPatternDTO> doSearch(OrderPatternDTO obj) {
		StringBuilder sql =new StringBuilder
				("SELECT "
						+ "NAME name ,"
						+ "OP.CREATED_USER_ID createdUserId ,"
						+ "OP.CREATED_USER_NAME createdUserName ,"
						+ "OP.DESCRIPTION description ,"
						+ "OP.ORDER_PATTERN_ID orderPatternId "
						+ "FROM WMS_OWNER_KTTS.ORDER_PATTERN OP "
						+ "LEFT JOIN VPS_OWNER.SYS_USER sys ON sys.SYS_USER_ID= OP.CREATED_USER_ID "
						+ "WHERE 1=1 ");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(OP.NAME) LIKE upper(:keySearch) escape '&')");
		}
		
		if (StringUtils.isNotEmpty(obj.getCreatedUserName())) {
			sql.append(" AND sys.FULL_NAME = :createdUserName");
		}
		
		sql.append(" ORDER BY OP.ORDER_PATTERN_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());	
		
		query.addScalar("name",new StringType());
		query.addScalar("createdUserName",new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("orderPatternId", new LongType());
		query.addScalar("createdUserId", new LongType());
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(""+obj.getKeySearch())+"%");
			queryCount.setParameter("keySearch", "%"+ ValidateUtils.validateKeySearch(""+obj.getKeySearch())+"%");
			}
		if (StringUtils.isNotEmpty(obj.getCreatedUserName())) {
			query.setParameter("createdUserName", obj.getCreatedUserName());
			queryCount.setParameter("createdUserName", obj.getCreatedUserName());
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		query.setResultTransformer(Transformers.aliasToBean(OrderPatternDTO.class));	
		
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		
		return query.list();
	}

//	Lấy dữ liệu bảng ORDER_PATTERN theo tên -- TUANNB
	public OrderPatternDTO getbyname(String name) {
		StringBuilder sql= new StringBuilder("SELECT ORDER_PATTERN_ID orderPatternId, NAME name, "
				+ "STATUS status, DESCRIPTION description, CREATED_DATE createdDate, "
				+ "CREATED_USER_ID createdUserId, CREATED_USER_NAME createdUserName "
				+ "FROM WMS_OWNER_KTTS.ORDER_PATTERN WHERE upper(NAME)=upper(:name)");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("orderPatternId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdUserName", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(OrderPatternDTO.class));

		query.setParameter("name", name);

		return (OrderPatternDTO) query.uniqueResult();
	}
	
//	Xoá dữ liệu bảng ORDER_PATTERN_GOODS và ORDER_PATTERN -- TUANNB
	public Long deleteObj(OrderPatternDTO obj){
		try{
		String sql="delete from WMS_OWNER_KTTS.\"ORDER_PATTERN\" where ORDER_PATTERN_ID = :orderPatternId";
		SQLQuery query=getSession().createSQLQuery(sql);
		query.setParameter("orderPatternId", obj.getOrderPatternId());
		query.executeUpdate();
		
		String sql2="delete from WMS_OWNER_KTTS.\"ORDER_PATTERN_GOODS\" where ORDER_PATTERN_ID = :orderPatternId";
		SQLQuery query2=getSession().createSQLQuery(sql2);
		query2.setParameter("orderPatternId", obj.getOrderPatternId());
		query2.executeUpdate();
		
		return 1L;
		}catch(Exception e){
			e.getMessage();
			return 0L;
		}
	}
	
//	Xoá dữ liệu bảng ORDER_PATTERN_GOODS -- TUANNB
	public Long deleteDetail(OrderPatternGoodsDTO obj){
		try{
			String sql="delete from WMS_OWNER_KTTS.\"ORDER_PATTERN_GOODS\" where ORDER_PATTERN_GOODS_ID = :orderPatternGoodsId";
			SQLQuery query=getSession().createSQLQuery(sql);
			query.setParameter("orderPatternGoodsId", obj.getOrderPatternGoodsId());
			query.executeUpdate();
			
			return 1L;
		}
		catch(Exception e){
			e.getMessage();
			return 0L;
		}
	}

//	Xem chi tiết hàng hoá -- TUANNB
	@SuppressWarnings("unchecked")
	public List<OrderPatternGoodsDTO> viewDetail(OrderPatternDTO obj) {
		StringBuilder sql=new StringBuilder("SELECT OPG.GOODS_CODE goodsCode,"
				+ "OPG.GOODS_NAME goodsName,"
				+ "OPG.GOODS_UNIT_NAME goodsUnitName,"
				+ "OPG.GOODS_TYPE goodsType,"
				+ "OPG.GOODS_TYPE_NAME goodsTypeName,"
				+ "OPG.GOODS_ID goodsId,"
				+ "OPG.GOODS_IS_SERIAL goodsIsSerial,"
				+ "OPG.GOODS_STATE goodsState,"
				+ "OPG.GOODS_STATE_NAME goodsStateName,"
				+ "OPG.GOODS_UNIT_ID goodsUnitId,"
				+ "OPG.AMOUNT amount, "
				+ "op.CREATED_USER_ID createdUserId,"
				+ "OPG.ORDER_PATTERN_GOODS_ID orderPatternGoodsId "
				+ "FROM WMS_OWNER_KTTS.ORDER_PATTERN_GOODS OPG "
				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_PATTERN op "
				+ "ON OPG.ORDER_PATTERN_ID= op.ORDER_PATTERN_ID "
				+ "WHERE OPG.ORDER_PATTERN_ID = :orderPatternId");
		
		sql.append(" ORDER BY OPG.ORDER_PATTERN_GOODS_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("goodsType", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsIsSerial", new StringType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("createdUserId", new LongType());
		
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("orderPatternGoodsId", new LongType());
		
		query.setParameter("orderPatternId", obj.getOrderPatternId());
		queryCount.setParameter("orderPatternId", obj.getOrderPatternId());
		
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		query.setResultTransformer(Transformers.aliasToBean(OrderPatternGoodsDTO.class));	
		
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}
}
