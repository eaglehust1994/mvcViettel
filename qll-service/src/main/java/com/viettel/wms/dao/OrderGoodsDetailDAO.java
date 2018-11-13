///*
// * Copyright (C) 2011 Viettel Telecom. All rights reserved.
// * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.viettel.wms.dao;
//
//import com.viettel.wms.bo.OrderGoodsDetailBO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDetailDTO;
//import com.viettel.wms.dto.StockCellDTO;
//import com.viettel.service.base.dao.BaseFWDAOImpl;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.transform.Transformers;
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
//@Repository("orderGoodsDetailDAO")
//public class OrderGoodsDetailDAO extends BaseFWDAOImpl<OrderGoodsDetailBO, Long> {
//
//    public OrderGoodsDetailDAO() {
//        this.model = new OrderGoodsDetailBO();
//    }
//
//    public OrderGoodsDetailDAO(Session session) {
//        this.session = session;
//    }
//    
//    public List<OrderGoodsDetailDTO> doSearchGoodsDetailForImportReq(OrderGoodsDetailDTO obj){
//		StringBuilder sql = new StringBuilder("SELECT ogd.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,"
//				+ "ogd.SERIAL serial,"
//				+ "ogd.CONTRACT_CODE contractCode,"
//				+ "ogd.PART_NUMBER partNumber,"
//				+ "ogd.MANUFACTURER_NAME manufacturerName,"
//				+ "ogd.PRICE price,"
//				+ "ogd.PRODUCING_COUNTRY_NAME producingCountryName "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd "
//				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_GOODS og ON og.ORDER_GOODS_ID = ogd.ORDER_GOODS_ID "
//				+ "WHERE ogd.ORDER_GOODS_ID = :orderGoodsId");
//		
//		sql.append(" ORDER BY ogd.ORDER_GOODS_DETAIL_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("price", new DoubleType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDetailDTO.class));
//		query.setParameter("orderGoodsId", obj.getOrderGoodsId());
//		queryCount.setParameter("orderGoodsId", obj.getOrderGoodsId());
//		if(obj.getPage() != null && obj.getPageSize() != null){
//		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		}
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		
//		return query.list();
//	}
//    
//    //get serial by orderGoodsId
//    public List<OrderGoodsDetailDTO> doSearchSerialByOrderGoodsId(long orderGoodsId){
//		StringBuilder sql = new StringBuilder("SELECT ogd.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,"
//				+ "ogd.SERIAL serial "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd "
//				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_GOODS og ON og.ORDER_GOODS_ID = ogd.ORDER_GOODS_ID "
//				+ "WHERE ogd.ORDER_GOODS_ID = :orderGoodsId");
//		
//		sql.append(" ORDER BY ogd.ORDER_GOODS_DETAIL_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDetailDTO.class));
//		query.setParameter("orderGoodsId", orderGoodsId);
//		queryCount.setParameter("orderGoodsId", orderGoodsId);
//		
//		return query.list();
//	}
//    public List<OrderGoodsDetailDTO> doSearchGoodsDetail(Long id){
//		StringBuilder sql = new StringBuilder("SELECT ogd.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,"
//				+ "ogd.ORDER_GOODS_ID orderGoodsId,"
//				+ "ogd.ORDER_ID orderId,"
//				+ "ogd.SERIAL serial,"
//				+ "ogd.CONTRACT_CODE contractCode,"
//				+ "ogd.PART_NUMBER partNumber,"
//				+ "ogd.MANUFACTURER_NAME manufacturerName,"
//				+ "ogd.MANUFACTURER_ID manufacturerId,"
//				+ "ogd.PRICE price,"
//				+ "og.GOODS_IS_SERIAL goodsIsSerial,"
//				+ "ogd.PRODUCING_COUNTRY_ID producingCountryId, "
//				+ "ogd.PRODUCING_COUNTRY_NAME producingCountryName "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd "
//				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_GOODS og ON og.ORDER_GOODS_ID = ogd.ORDER_GOODS_ID "
//				+ "WHERE ogd.ORDER_GOODS_ID = :orderGoodsId");
//		
//		sql.append(" ORDER BY ogd.ORDER_GOODS_DETAIL_ID DESC");
//		
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		query.addScalar("goodsIsSerial", new StringType());
//		query.addScalar("manufacturerId", new LongType());
//		query.addScalar("producingCountryId", new LongType());
//		query.addScalar("price", new DoubleType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDetailDTO.class));
//		query.setParameter("orderGoodsId", id);
//		
//		return query.list();
//	}
//    
//    public List<OrderGoodsDetailDTO> getGoodsByCodeAndSerial(String goodCode, String serial, Long id){
//    	StringBuilder sql = new StringBuilder();
//    	
//    	sql.append("Select ogd.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,");
//    	sql.append("ogd.SERIAL serial,");
//    	sql.append("ogd.CONTRACT_CODE contractCode,");
//    	sql.append("ogd.PART_NUMBER partNumber,");
//    	sql.append("og.GOODS_CODE goodsCode,");
//    	sql.append("og.GOODS_NAME goodsName,");
//    	sql.append("ogd.PRODUCING_COUNTRY_NAME producingCountryName ");
//    	sql.append("FROM WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd ");
//    	sql.append("JOIN WMS_OWNER_KTTS.ORDER_GOODS og ON ogd.ORDER_GOODS_ID = og.ORDER_GOODS_ID ");
//    	sql.append("where og.GOODS_CODE =:goodCode AND og.ORDER_ID =:orderId");
//    	
//    	if(!serial.isEmpty()){
//    		sql.append(" AND ogd.SERIAL =:serialNo");
//    	}
//    	
//    	SQLQuery query= getSession().createSQLQuery(sql.toString());
//    	query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDetailDTO.class));
//		
//		if(!serial.isEmpty()){
//			query.setParameter("serialNo", serial);
//		}
//		
//		query.setParameter("goodCode", goodCode);
//		query.setParameter("orderId", id);
//		
//		return query.list();
//		
//    }
//    
//	public List<OrderGoodsDetailDTO> doSearchGoodsDetailForExportReq() {
//		StringBuilder sql = new StringBuilder("SELECT ogd.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,"
//				+ "ogd.SERIAL serial,"
//				+ "ogd.CONTRACT_CODE contractCode,"
//				+ "ogd.PART_NUMBER partNumber,"
//				+ "ogd.MANUFACTURER_NAME manufacturerName,"
//				+ "ogd.PRICE price,"
//				+ "ogd.PRODUCING_COUNTRY_NAME producingCountryName, "
//				+ "ogd.ORDER_GOODS_ID orderGoodsId "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd "
//				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_GOODS og ON og.ORDER_GOODS_ID = ogd.ORDER_GOODS_ID ");
//		
//		sql.append(" ORDER BY ogd.ORDER_GOODS_DETAIL_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("price", new DoubleType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDetailDTO.class));
//		
//		
//		return query.list();
//	}
//	
//	public List<OrderGoodsDetailDTO> doSearchGoodsDetailForExportReq(OrderGoodsDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT ogd.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,"
//				+ "ogd.SERIAL serial,"
//				+ "ogd.CONTRACT_CODE contractCode,"
//				+ "ogd.PART_NUMBER partNumber,"
//				+ "ogd.MANUFACTURER_NAME manufacturerName,"
//				+ "ogd.PRICE price,"
//				+ "ogd.PRODUCING_COUNTRY_NAME producingCountryName, "
//				+ "ogd.ORDER_GOODS_ID orderGoodsId "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd "
//				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_GOODS og ON og.ORDER_GOODS_ID = ogd.ORDER_GOODS_ID "
//				+ "WHERE og.ORDER_GOODS_ID = :oGId");
//		
//		sql.append(" ORDER BY ogd.ORDER_GOODS_DETAIL_ID DESC");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("price", new DoubleType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDetailDTO.class));
//		query.setParameter("oGId", obj.getOrderGoodsId());
//		
//		return query.list();
//	}
//	
//	public List<OrderGoodsDetailDTO> getGoodsDetailByOrderGoodId(Long OrderGoodId) {
//		StringBuilder sql = new StringBuilder("SELECT ogd.ORDER_GOODS_DETAIL_ID orderGoodsDetailId,"
//				+ "ogd.SERIAL serial,"
//				+ "ogd.CONTRACT_CODE contractCode,"
//				+ "ogd.PART_NUMBER partNumber,"
//				+ "ogd.MANUFACTURER_NAME manufacturerName,"
//				+ "ogd.PRICE price,"
//				+ "ogd.PRODUCING_COUNTRY_NAME producingCountryName, "
//				+ "ogd.ORDER_GOODS_ID orderGoodsId "
//				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd "
//				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_GOODS og ON og.ORDER_GOODS_ID = ogd.ORDER_GOODS_ID "
//				+ "WHERE ogd.ORDER_GOODS_ID = :OrderGoodId");
//		
//		
//		sql.append(" ORDER BY ogd.ORDER_GOODS_DETAIL_ID DESC");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("orderGoodsId", new LongType());
//		query.addScalar("orderGoodsDetailId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("price", new DoubleType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderGoodsDetailDTO.class));
//		
//		query.setParameter("OrderGoodId", OrderGoodId);
//		
//		//return (OrderGoodsDetailDTO) query.uniqueResult();
//		return query.list();
//	}
//	
//}
