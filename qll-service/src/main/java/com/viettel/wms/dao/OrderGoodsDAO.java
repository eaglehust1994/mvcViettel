///*
// * Copyright (C) 2011 Viettel Telecom. All rights reserved.
// * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.viettel.wms.dao;
//
//import com.viettel.wms.bo.OrderGoodsBO;
//import com.viettel.wms.dto.AppParamDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.utils.ValidateUtils;
//import com.viettel.service.base.dao.BaseFWDAOImpl;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.transform.Transformers;
//import org.hibernate.type.TimestampType;
//import org.hibernate.type.DoubleType;
//import org.hibernate.type.LongType;
//import org.hibernate.type.StringType;
//import org.springframework.stereotype.Repository;
//
///**
// * @author TruongBX3
// * @version 1.0
// * @since 08-May-15 4:07 PM
// */
//@Repository("orderGoodsDAO")
//public class OrderGoodsDAO extends BaseFWDAOImpl<OrderGoodsBO, Long> {
//
//    public OrderGoodsDAO() {
//        this.model = new OrderGoodsBO();
//    }
//
//    
//    public OrderGoodsDAO(Session session) {
//        this.session = session;
//    }
//    
//    public List<OrderGoodsDTO> doSearchGoodsForImportReq(OrderGoodsDTO obj){
//		StringBuilder sql = new StringBuilder("SELECT og.ORDER_GOODS_ID orderGoodsId,"
//				+ "og.ORDER_ID orderId,"
//				+ "og.GOODS_CODE goodsCode,"
//				+ "og.GOODS_NAME goodsName,"
//				+ "og.GOODS_UNIT_NAME goodsUnitName,"
//				+ "og.AMOUNT amount,"
//				+ "og.GOODS_STATE_NAME goodsStateName "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS og "
//				+ "INNER JOIN WMS_OWNER_KTTS.\"ORDER\" o ON o.ORDER_ID = og.ORDER_ID "
//				+ "WHERE og.ORDER_ID = :orderId");
//		
//		
//		sql.append(" ORDER BY og.ORDER_GOODS_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amount", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDTO.class));
//		query.setParameter("orderId", obj.getOrderId());
//		queryCount.setParameter("orderId", obj.getOrderId());
//		if(obj.getPage() != null && obj.getPageSize() != null){
//		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		}
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		
//		return query.list();
//	}
//    
//    public List<OrderGoodsDTO> doSearchGoods(OrderGoodsDTO obj){
//		StringBuilder sql = new StringBuilder("SELECT og.ORDER_GOODS_ID orderGoodsId,"
//				+ "og.ORDER_ID orderId,"
//				+ "og.GOODS_ID goodsId,"
//				+ "og.GOODS_CODE goodsCode,"
//				+ "og.GOODS_NAME goodsName,"
//				+ "og.GOODS_UNIT_ID goodsUnitId,"
//				+ "og.GOODS_UNIT_NAME goodsUnitName,"
//				+ "og.GOODS_TYPE goodsType,"
//				+ "og.GOODS_TYPE_NAME goodsTypeName,"
//				+ "og.AMOUNT amount,"
//				+ "og.TOTAL_PRICE totalPrice,"
//				+ "og.GOODS_IS_SERIAL goodsIsSerial,"
//				+ "og.GOODS_STATE goodsState, "
//				+ "og.GOODS_STATE_NAME goodsStateName "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS og "
//				+ "INNER JOIN WMS_OWNER_KTTS.\"ORDER\" o ON o.ORDER_ID = og.ORDER_ID "
//				+ "WHERE og.ORDER_ID = :orderId");
//		
//		
//		sql.append(" ORDER BY og.ORDER_GOODS_ID DESC");
//		
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amount", new DoubleType());
//		query.addScalar("totalPrice", new DoubleType());
//		query.addScalar("goodsIsSerial", new StringType());
//		query.addScalar("goodsState", new StringType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("goodsTypeName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDTO.class));
//		query.setParameter("orderId", obj.getOrderId());
//		return query.list();
//	}
//
//	public List<OrderGoodsDTO> doSearchGoodsForExportOrder(OrderGoodsDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT og.ORDER_GOODS_ID orderGoodsId,"
//				+ "og.ORDER_ID orderId,"
//				+ "og.GOODS_CODE goodsCode,"
//				+ "og.GOODS_TYPE goodsType,"
//				+ "og.GOODS_TYPE_NAME goodsTypeName,"
//				+ "og.GOODS_STATE goodsState,"
//				+ "og.GOODS_UNIT_ID goodsUnitId,"
//				+ "og.TOTAL_PRICE totalPrice,"
//				+ "og.GOODS_NAME goodsName,"
//				+ "og.GOODS_ID goodsId,"
//				+ "og.GOODS_UNIT_NAME goodsUnitName,"
//				+ "og.AMOUNT amount,"
//				+ "og.AMOUNT amountReal,"
//				+ "og.GOODS_IS_SERIAL goodsIsSerial,"
//				+ "og.GOODS_STATE_NAME goodsStateName, "
//				+ "STDR.ORDER_GOODS_DETAIL_ID    orderGoodsDetailId, "
//				+ "STDR.SERIAL    serial,"
//				+ "STDR.CONTRACT_CODE    contractCode,"
//				+ "STDR.PART_NUMBER    partNumber,"
//				+ "STDR.MANUFACTURER_ID    manufacturerId,"
//				+ "STDR.PRODUCING_COUNTRY_ID    producingCountryId,"
//				+ "STDR.MANUFACTURER_NAME    manufacturerName,"
//				+ "STDR.PRODUCING_COUNTRY_NAME    producingCountryName,"
//				+ "STDR.PRICE    price "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS og "
//				+ "INNER JOIN WMS_OWNER_KTTS.\"ORDER\" o ON o.ORDER_ID = og.ORDER_ID "
//				+ "LEFT JOIN ORDER_GOODS_DETAIL STDR ON og.ORDER_GOODS_ID=STDR.ORDER_GOODS_ID "
//				+ "WHERE og.ORDER_ID = :orderId");
//		
//		
//		sql.append(" ORDER BY og.ORDER_GOODS_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("goodsTypeName", new StringType());
//		query.addScalar("goodsState", new StringType());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("totalPrice", new DoubleType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amount", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsIsSerial", new StringType());
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("manufacturerId", new LongType());
//		query.addScalar("producingCountryId", new LongType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		query.addScalar("price", new DoubleType());
//
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDTO.class));
//		query.setParameter("orderId", obj.getOrderId());
//		queryCount.setParameter("orderId", obj.getOrderId());
//		if(obj.getPage() != null && obj.getPageSize() != null){
//		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		}
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		return query.list();
//	}
//	public boolean checkListOderGoods(OrderGoodsDTO obj){
//		StringBuilder sql =new StringBuilder(" SELECT COUNT(*) FROM ORDER_GOODS WHERE"
//				+ " ORDER_ID=:orderId AND GOODS_ID=:goodsId AND GOODS_STATE=:goodsState AND NVL(AMOUNT,0) >0 ");
//		SQLQuery query=getSession().createSQLQuery(sql.toString());
//		query.setParameter("orderId", obj.getOrderId());
//		query.setParameter("goodsId", obj.getGoodsId());
//		query.setParameter("goodsState", obj.getGoodsState());
//		if(((BigDecimal) query.uniqueResult()).intValue()==0){
//			return false;
//		}
//		return true;
//	}
//		
//	public List<OrderGoodsDTO> doSearchGoodsForExportOrder(Long orderId) {
//		StringBuilder sql = new StringBuilder("SELECT og.ORDER_GOODS_ID orderGoodsId,"
//				+ "og.ORDER_ID orderId,"
//				+ "og.GOODS_CODE goodsCode,"
//				+ "og.GOODS_NAME goodsName,"
//				+ "og.GOODS_UNIT_NAME goodsUnitName,"
//				+ "og.AMOUNT amount,"
//				+ "og.GOODS_STATE_NAME goodsStateName "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS og "
//				+ "INNER JOIN WMS_OWNER_KTTS.\"ORDER\" o ON o.ORDER_ID = og.ORDER_ID "
//				+ "WHERE og.ORDER_ID = :orderId");
//		
//		
//		sql.append(" ORDER BY og.ORDER_GOODS_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amount", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDTO.class));
//		query.setParameter("orderId", orderId);
//		queryCount.setParameter("orderId", orderId);
//		return query.list();
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	public List<GoodsDTO> getForAutoComplete(GoodsDTO obj) {
//		String sql = "SELECT g.GOODS_ID goodsId, "	
//			+ "g.NAME name, "		
//			+ "g.CODE code, "
//			+ "g.GOODS_TYPE goodsType, "
//			+ "g.UNIT_TYPE goodsUnitId, "
//			+ "g.UNIT_TYPE_NAME unitTypeName "
//			+ "FROM CAT_OWNER.GOODS g "
//			+ "WHERE 1=1 ";
//		
//		StringBuilder stringBuilder = new StringBuilder(sql);
//		
//		
//		if (obj.getIsSize()){
//			stringBuilder.append(" AND ROWNUM <=10 ");
//			if(StringUtils.isNotEmpty(obj.getName())){
//			stringBuilder.append(" AND (upper(g.NAME) LIKE upper(:name) escape '&' OR upper(g.CODE) LIKE upper(:name) escape '&')");
//			}
//		}
//		else{
//			if(StringUtils.isNotEmpty(obj.getName())){
//				stringBuilder.append( " AND upper(g.NAME) LIKE upper(:name) escape '&'");
//			}
//			if(StringUtils.isNotEmpty(obj.getCode())){
//				stringBuilder.append( " AND upper(g.CODE) LIKE upper(:value) escape '&'");
//			}
//		}
//		
//		stringBuilder.append(" ORDER BY g.CODE");
//		
//		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//		
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("unitTypeName", new StringType());
//	
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//
//		if (StringUtils.isNotEmpty(obj.getName())) {
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//		if (StringUtils.isNotEmpty(obj.getCode())) {
//			query.setParameter("value", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
//		}
//		return query.list();
//	}
//	
//	public Long maxId(){
//		StringBuilder sql = new StringBuilder("SELECT  MAX(ORDER_GOODS_ID) FROM WMS_OWNER_KTTS.ORDER_GOODS og ");
//		SQLQuery query=getSession().createSQLQuery(sql.toString());
//		return (Long) query.list().get(0);
//	}
//
//	public List<OrderGoodsDTO> getGoodDetail(Long orderId){
//		StringBuilder sql = new StringBuilder("SELECT og.ORDER_GOODS_ID orderGoodsId,"
//				+ "og.ORDER_ID orderId,"
//				+ "og.GOODS_CODE goodsCode,"
//				+ "og.GOODS_TYPE goodsType,"
//				+ "og.GOODS_TYPE_NAME goodsTypeName,"
//				+ "og.GOODS_STATE goodsState,"
//				+ "og.GOODS_UNIT_ID goodsUnitId,"
//				+ "og.TOTAL_PRICE totalPrice,"
//				+ "og.GOODS_NAME goodsName,"
//				+ "og.GOODS_ID goodsId,"
//				+ "og.GOODS_UNIT_NAME goodsUnitName,"
//				+ "og.AMOUNT amount,"
//				+ "og.AMOUNT amountReal,"
//				+"og.GOODS_IS_SERIAL goodsIsSerial,"
//				
//				+ "ODD.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,"
//				+ "ODD.SERIAL serial,"
//				+ "ODD.CONTRACT_CODE contractCode,"
//				+ "ODD.PART_NUMBER partNumber,"
//				+ "ODD.MANUFACTURER_ID manufacturerId,"
//				+ "ODD.PRODUCING_COUNTRY_ID producingCountryId,"
//				+ "ODD.MANUFACTURER_NAME manufacturerName,"
//				+ "ODD.PRODUCING_COUNTRY_NAME producingCountryName,"
//				+"ODD.PRICE price,"
//				
//				
//				
//				
//				+ "og.GOODS_STATE_NAME goodsStateName "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS og "
//				+ "INNER JOIN WMS_OWNER_KTTS.\"ORDER\" o ON o.ORDER_ID = og.ORDER_ID "
//				+ " JOIN ORDER_GOODS_DETAIL ODD ON ODD.ORDER_GOODS_ID=og.ORDER_GOODS_ID"
//				+ " WHERE og.ORDER_ID = :orderId");
//		
//		
//		sql.append(" ORDER BY og.ORDER_GOODS_ID,ODD.ORDER_GOODS_DETAIL_ID DESC");
//		
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("goodsTypeName", new StringType());
//		query.addScalar("goodsState", new StringType());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("totalPrice", new DoubleType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amount", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsIsSerial", new StringType());
//		
//		
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("manufacturerId", new LongType());
//		query.addScalar("producingCountryId", new LongType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		query.addScalar("price", new DoubleType());
//
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDTO.class));
//		query.setParameter("orderId", orderId);
//		return query.list();
//	}
//	
//	public OrderGoodsDTO doSearchGoodsForFileImport(Long orderId, String goodsCode, String goodsState) {
//		StringBuilder sql = new StringBuilder("SELECT og.ORDER_GOODS_ID orderGoodsId,"
//				+ "og.ORDER_ID orderId,"
//				+ "og.GOODS_CODE goodsCode,"
//				+ "og.GOODS_NAME goodsName,"
//				+ "og.GOODS_UNIT_ID goodsUnitId,"	
//				+ "og.GOODS_UNIT_NAME goodsUnitName,"
//				+ "og.AMOUNT amount,"
//				+ "og.GOODS_STATE_NAME goodsStateName "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS og "
//				+ "INNER JOIN WMS_OWNER_KTTS.\"ORDER\" o ON o.ORDER_ID = og.ORDER_ID "
//				+ "WHERE og.ORDER_ID = :orderId "
//				+ "AND upper(og.GOODS_CODE) LIKE upper(:goodsCode) escape '&' "
//				+ "AND og.GOODS_STATE = (:goodsState)");
//		
//		
//		sql.append(" ORDER BY og.ORDER_GOODS_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amount", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsUnitId", new LongType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDTO.class));
//		query.setParameter("orderId", orderId);
//		queryCount.setParameter("orderId", orderId);
//		query.setParameter("goodsCode", goodsCode);
//		queryCount.setParameter("goodsCode", goodsCode);
//		query.setParameter("goodsState", goodsState);
//		queryCount.setParameter("goodsState", goodsState);
//		
//		return (OrderGoodsDTO) query.uniqueResult();
//	}
//}
