///*
// * Copyright (C) 2011 Viettel Telecom. All rights reserved.
// * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.viettel.wms.dao;
//
//import com.viettel.wms.bo.StockTransDetailBO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.ObjectReferenceDetailDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.StockGoodsSerialDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.wms.dto.StockTransDetailSerialDTO;
//import com.viettel.service.base.dao.BaseFWDAOImpl;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
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
//@Repository("stockTransDetailDAO")
//public class StockTransDetailDAO extends BaseFWDAOImpl<StockTransDetailBO, Long> {
//
//	public StockTransDetailDAO() {
//		this.model = new StockTransDetailBO();
//	}
//
//	public StockTransDetailDAO(Session session) {
//		this.session = session;
//	}
//
//	// 1.for import
//	public List<StockTransDetailDTO> doSearchGoodsForImportNote(StockTransDetailDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT std.STOCK_TRANS_DETAIL_ID stockTransDetailId,"
//				+ "std.ORDER_ID orderId,"
//				+ "std.GOODS_CODE goodsCode," 
//				+ "std.GOODS_NAME goodsName,"
//				+ "std.GOODS_UNIT_NAME goodsUnitName," 
//				+ "std.AMOUNT_ORDER amountOrder," 
//				+ "std.AMOUNT_REAL amountReal,"
//				+ "std.TOTAL_PRICE totalPrice,"
//				+ "std.GOODS_STATE_NAME goodsStateName " 
//				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std "
//				+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
//				+ "WHERE std.STOCK_TRANS_ID = :stockTransId");
//
//		sql.append(" ORDER BY std.STOCK_TRANS_DETAIL_ID DESC");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amountOrder", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("totalPrice", new DoubleType());
//		
//
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("stockTransId", obj.getStockTransId());
//		queryCount.setParameter("stockTransId", obj.getStockTransId());
//		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//
//		return query.list();
//	}
//
//	public List<StockTransDetailDTO> doSearchByStockTransId(Long id) {
//		StringBuilder sql = new StringBuilder(
//				"SELECT std.STOCK_TRANS_DETAIL_ID stockTransDetailId," 
//						+ "std.ORDER_ID orderId,"
//						+ "std.GOODS_ID goodsId," 
//						+ "std.GOODS_CODE goodsCode," 
//						+ "std.GOODS_NAME goodsName,"
//						+ "std.GOODS_UNIT_NAME goodsUnitName," 
//						+ "std.GOODS_IS_SERIAL goodsIsSerial,"
//						+ "std.AMOUNT_ORDER amountOrder," 
//						+ "std.AMOUNT_REAL amountReal," 
//						+ "std.GOODS_STATE goodsState, " 
//						+ "std.GOODS_TYPE goodsType, "
//						+ "std.TOTAL_PRICE totalPrice, " 
//						+ "std.GOODS_TYPE_NAME goodsTypeName, " 
//						+ "std.GOODS_UNIT_ID goodsUnitId,"
//						+ "std.GOODS_STATE_NAME goodsStateName " 
//						+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std "
//						+ "JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
//						+ "WHERE std.STOCK_TRANS_ID = :stockTransId");
//
//		sql.append(" ORDER BY std.STOCK_TRANS_DETAIL_ID DESC");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		 query.addScalar("amountOrder", new DoubleType());
//		 query.addScalar("amountReal", new DoubleType());
//		 query.addScalar("goodsState", new StringType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsIsSerial", new StringType());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("goodsTypeName", new StringType());
//		query.addScalar("totalPrice", new DoubleType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("stockTransId", id);
//
//		return query.list();
//	}
//
//	public List<StockTransDetailDTO> getGoodsInfoFromAlternativeStockByCode(String code) {
//
//		StringBuilder sql = new StringBuilder("SELECT " + "std.STOCK_TRANS_DETAIL_ID id,"
//				+ "st.STOCK_TRANS_ID stockTransId," + "std.GOODS_ID goodsId," + "std.GOODS_CODE goodsCode,"
//				+ "std.GOODS_NAME goodsName," + "std.AMOUNT_REAL amount," + "std.GOODS_UNIT_NAME goodsUnitName,"
//				+ "std.GOODS_STATE_NAME goodsStateName" + " FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std"
//				+ " JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
//				+ " WHERE st.CODE =:code AND st.TYPE = 2 AND st.STATUS = 3 ");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//
//		query.addScalar("id", new LongType());
//		query.addScalar("stockTransId", new LongType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("amount", new DoubleType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("goodsStateName", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("code", code);
//
//		return query.list();
//
//	}
//
//	// 2.for export
//	// me
//	public StockTransDetailDTO getStockTransDetail(Long id) {
//		StringBuilder sql = new StringBuilder("SELECT std.STOCK_TRANS_DETAIL_ID stockTransDetailId,"
//				+ "std.ORDER_ID orderId," + "std.GOODS_CODE goodsCode," + "std.GOODS_NAME goodsName,"
//				+ "std.GOODS_UNIT_NAME goodsUnitName," + "std.AMOUNT_ORDER amountOrder," + "std.AMOUNT_REAL amountReal,"
//				+ "std.GOODS_STATE_NAME goodsStateName " + "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std "
//				+ "WHERE std.STOCK_TRANS_DETAIL_ID = :stockTransDetailId");
//
//		sql.append(" ORDER BY std.STOCK_TRANS_DETAIL_ID DESC");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amountOrder", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("stockTransDetailId", id);
//
//		return (StockTransDetailDTO) query.uniqueResult();
//	}
//
//	// me
//	public List<StockTransDetailDTO> doSearchGoodsForIExportNote(StockTransDetailDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT std.STOCK_TRANS_DETAIL_ID stockTransDetailId,"
//				+ "std.ORDER_ID orderId," + "std.GOODS_CODE goodsCode," + "std.GOODS_NAME goodsName,"
//				+ "std.GOODS_STATE goodsState,"
//				+ "std.GOODS_UNIT_NAME goodsUnitName," + "std.AMOUNT_ORDER amountOrder," + "std.AMOUNT_REAL amountReal,"
//				+ "std.GOODS_STATE_NAME goodsStateName " + "FROM STOCK_TRANS_DETAIL std "
//				+ "INNER JOIN STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
//				+ "WHERE std.STOCK_TRANS_ID = :stockTransId");
//
//		if(StringUtils.isNotEmpty(obj.getGoodsIsSerial())){
//			sql.append(" AND std.GOODS_IS_SERIAL=:goodsIsSerial ");
//		}
//		
//		sql.append(" ORDER BY std.STOCK_TRANS_DETAIL_ID DESC");
//
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amountOrder", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsState", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("stockTransId", obj.getStockTransId());
//		if(StringUtils.isNotEmpty(obj.getGoodsIsSerial())){
//			query.setParameter("goodsIsSerial", obj.getGoodsIsSerial());
//		}
//		return query.list();
//	}
//
//	// test
//	public List<StockTransDetailDTO> doSearchGoodsForIExportNote(StockTransDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT std.STOCK_TRANS_DETAIL_ID stockTransDetailId, "
//				+ "st.STOCK_ID stockId,"
//				+ "std.ORDER_ID orderId," 
//				+ "std.GOODS_CODE goodsCode," 
//				+ "std.GOODS_ID goodsId,"
//				+ "std.GOODS_NAME goodsName," 
//				+ "std.GOODS_UNIT_NAME goodsUnitName," 
//				+ "std.AMOUNT_ORDER amountOrder,"
//				+ "std.AMOUNT_REAL amountReal," 
//				+ "std.GOODS_IS_SERIAL goodsIsSerial," 
//				+ "std.GOODS_STATE goodsState," 
//				+ "std.GOODS_STATE_NAME goodsStateName "
//				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std "
//				+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
//				+ "WHERE std.STOCK_TRANS_ID = :stockTransId");
//
//		sql.append(" ORDER BY std.STOCK_TRANS_DETAIL_ID DESC");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amountOrder", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsIsSerial", new StringType());
//		query.addScalar("goodsState", new StringType());
//		query.addScalar("stockId", new LongType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("stockTransId", obj.getStockTransId());
//		queryCount.setParameter("stockTransId", obj.getStockTransId());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//
//		return query.list();
//	}
//
//	public List<StockTransDetailDTO> doSearchGoodsForExportNote(StockTransDetailDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT std.STOCK_TRANS_DETAIL_ID stockTransDetailId,"
//				+ "std.ORDER_ID orderId," + "std.GOODS_CODE goodsCode," + "std.GOODS_NAME goodsName,"
//				+ "std.GOODS_UNIT_NAME goodsUnitName," + "std.AMOUNT_ORDER amountOrder," + "std.AMOUNT_REAL amountReal,"
//				+ "std.GOODS_STATE_NAME goodsStateName, " + "std.GOODS_STATE goodsState " + "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std "
//				+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
//				+ "WHERE std.STOCK_TRANS_ID = :stockTransId");
//
//		sql.append(" ORDER BY std.STOCK_TRANS_DETAIL_ID DESC");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amountOrder", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsState", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("stockTransId", obj.getStockTransId());
//		queryCount.setParameter("stockTransId", obj.getStockTransId());
//		if(obj.getPage() != null && obj.getPageSize() != null){
//			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//			query.setMaxResults(obj.getPageSize().intValue());
//		}
//		
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//
//		return query.list();
//	}
//
//	// kiem tra serial co ton tai
//	public List<BigDecimal> checkGoods(String goodsCode,String goodsState,Long stockTransId) {
//		StringBuilder sql = new StringBuilder("SELECT std.AMOUNT_REAL FROM  Stock_Trans_Detail std  "
//				+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
//				+ "where st.STATUS = 1  AND std.STOCK_TRANS_ID=:stockTransId AND std.GOODS_CODE=:goodsCode AND std.GOODS_STATE=:goodsState");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		query.setParameter("goodsCode",goodsCode);
//		query.setParameter("goodsState",goodsState);
//		query.setParameter("stockTransId",stockTransId);
//		return query.list();
//
//	}
//
//	public List<StockTransDetailSerialDTO> getListStockGoodSerialBySerial(String serial){
//		StringBuilder sql = new StringBuilder("SELECT GOODS_ID goodsId"
//				+ ",GOODS_CODE goodsCode"
//				+ ",GOODS_NAME goodsName"
//				+ ",SERIAL serial"
//				+ ",TO_CHAR(GOODS_STATE) goodState"
//				+ ",GOODS_STATE_NAME goodsStateName"
//				+ ",CONTRACT_CODE contractCode"
//				+ ",PART_NUMBER partNumber"
//				+ ",MANUFACTURER_ID manufacturerId"
//				+ ",PRODUCING_COUNTRY_ID producingCountryId"
//				+ ",MANUFACTURER_NAME manufacturerName"
//				+ ",PRODUCING_COUNTRY_NAME producingCountryName"
//				+ ",PRICE price"
//				+ " From WMS_OWNER_KTTS.STOCK_GOODS_SERIAL where STATUS =1 AND SERIAL=:serial");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("serial", new StringType());
//		
//		query.addScalar("manufacturerId", new LongType());
//		query.addScalar("producingCountryId", new LongType());
//		
//		
//		query.addScalar("price", new DoubleType());
//		
//		
//		query.addScalar("goodState", new StringType());
////		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailSerialDTO.class));
//		query.setParameter("serial",serial);
//		
//		return query.list();
//	}
//	
//	// kiem tra serial co ton tai
//	public Long checkSearialGood(StockGoodsSerialDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT Count(*) From WMS_OWNER_KTTS.STOCK_GOODS_SERIAL where STATUS =1 AND SERIAL=:serial");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		query.setParameter("serial",obj.getSerial());
//		return ((BigDecimal) query.uniqueResult()).longValue();
//	}
//		// kiem tra code cos ton tai trong kho xuat
//	public boolean checkStockCode(StockTransDetailDTO obj,Long orderId) {
//		StringBuilder sql = new StringBuilder("SELECT  COUNT(*) FROM ORDER_GOODS WHERE  ORDER_ID=:orderId AND GOODS_CODE=upper(:goodsCode) AND NVL(AMOUNT,0)>0 ");
//		if(StringUtils.isNotEmpty(obj.getGoodsState())){
//			sql.append(" AND GOODS_STATE=:goodsState");
//		}
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		query.setParameter("orderId", orderId);
//		query.setParameter("goodsCode", obj.getGoodsCode());
//		if(StringUtils.isNotEmpty(obj.getGoodsState())){
//			query.setParameter("goodsState", obj.getGoodsState());
//		}
//		
//		if(((BigDecimal) query.uniqueResult()).intValue()>0){
//			return true;
//		}
//		return false;
//
//	}
//	
//	public boolean serialOfGoodsCode(String code,Long orderId) {
//		StringBuilder sql = new StringBuilder("SELECT COUNT(*)  FROM ORDER_GOODS WHERE GOODS_CODE =upper(:code) AND ORDER_ID=:orderId AND GOODS_IS_SERIAL='1'");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		
//		query.setParameter("orderId", orderId);
//		query.setParameter("code", code);
//
//		if(((BigDecimal) query.uniqueResult()).intValue()>0){
//			return true;
//		}
//		return false;
//	}
//	
//	
//	public List<StockTransDetailDTO> getListStockTransDetail(Long id){
//		StringBuilder sql =new StringBuilder("SELECT "
//				+ "ST.STOCK_ID   stockId,"
//				+ "S.NAME stockName,"
//				+ "S.CODE stockCode,"
//				+ "STD.STOCK_TRANS_DETAIL_ID   stockTransDetailId,"
//				+ "STD.ORDER_ID    orderId,"
//				+ "STD.GOODS_TYPE    goodsType,"
//				+ "STD.GOODS_TYPE_NAME    goodsTypeName,"
//				+ "STD.GOODS_ID    goodsId,"
//				+ "STD.GOODS_CODE    goodsCode,"
//				+ "STD.GOODS_NAME    goodsName,"
//				+ "STD.GOODS_IS_SERIAL    isSerial,"
//				+ "STD.GOODS_STATE    goodsState,"
//				+ "STD.GOODS_STATE_NAME    goodsStateName,"
//				+ "STD.GOODS_UNIT_NAME    goodsUnitName,"
//				+ "STD.GOODS_UNIT_ID    goodsUnitId,"
//				+ "STD.AMOUNT_ORDER    amountOrder,"
//				+ "STD.AMOUNT_REAL    amountReal,"
//				+ "STD.TOTAL_PRICE    totalPrice,"
//				+ "STD.STOCK_TRANS_ID    stockTransId,"
//				+ "STDR.STOCK_TRANS_DETAIL_SERIAL_ID    stockTransDetailSerialId,"
//				+ "STDR.SERIAL    serial,"
//				+ "STDR.CONTRACT_CODE    contractCode,"
//				+ "STDR.PART_NUMBER    partNumber,"
//				+ "STDR.MANUFACTURER_ID    manufacturerId,"
//				+ "STDR.PRODUCING_COUNTRY_ID    producingCountryId,"
//				+ "STDR.MANUFACTURER_NAME    manufacturerName,"
//				+ "STDR.PRODUCING_COUNTRY_NAME    producingCountryName,"
//				+ "STDR.PRICE    price,"
//				+ "STDR.CELL_CODE    cellCode"
//				+ " FROM STOCK_TRANS_DETAIL STD LEFT JOIN STOCK_TRANS_DETAIL_SERIAL STDR ON STD.STOCK_TRANS_DETAIL_ID=STDR.STOCK_TRANS_DETAIL_ID AND "
//				+ " (STD.GOODS_IS_SERIAL='0' OR (STD.GOODS_IS_SERIAL='1' AND STDR.SERIAL IS NOT NULL))"
//				+ " JOIN STOCK_TRANS ST ON ST.STOCK_TRANS_ID=STD.STOCK_TRANS_ID "
//				+ " JOIN STOCK S ON S.STOCK_ID=ST.STOCK_ID "
//				+ " WHERE STD.STOCK_TRANS_ID=:id ");
//		
//		SQLQuery query=getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("stockId", new LongType());
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("goodsTypeName", new StringType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("isSerial", new StringType());
//		query.addScalar("goodsState", new StringType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("amountOrder", new DoubleType());
//		query.addScalar("amountReal", new DoubleType());
//		query.addScalar("totalPrice", new DoubleType());
//		query.addScalar("stockTransId", new LongType());
//		query.addScalar("stockTransDetailSerialId", new LongType());
//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("manufacturerId", new LongType());
//		query.addScalar("producingCountryId", new LongType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());
//		query.addScalar("price", new DoubleType());
//		query.addScalar("cellCode", new StringType());
//		query.addScalar("stockName", new StringType());
//		query.addScalar("stockCode", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		
//		query.setParameter("id", id);
//		
//		return query.list();
//
//	}
//	
//	//tim thong tin hang hoa khi cap nhat phieu xuat
//	public StockTransDetailDTO doSearchGoodsForFileImport(Long orderId, String goodsCode, String goodsState) {
//		StringBuilder sql = new StringBuilder("SELECT st.STOCK_TRANS_DETAIL_ID stockTransDetailId,"
//				+ "st.ORDER_ID orderId,"
//				+ "st.GOODS_CODE goodsCode,"
//				+ "st.GOODS_NAME goodsName,"
//				+ "st.GOODS_UNIT_ID goodsUnitId,"	
//				+ "st.GOODS_UNIT_NAME goodsUnitName,"
//				+ "st.AMOUNT_ORDER amountOrder,"
//				+ "st.GOODS_STATE_NAME goodsStateName "
//				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL st "
//				+ "WHERE st.ORDER_ID = :orderId "
//				+ "AND upper(st.GOODS_CODE) LIKE upper(:goodsCode) escape '&' "
//				+ "AND st.GOODS_STATE = (:goodsState)");
//		
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("stockTransDetailId", new LongType());
//		query.addScalar("orderId", new LongType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("amountOrder", new DoubleType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("goodsUnitId", new LongType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
//		query.setParameter("orderId", orderId);
//		queryCount.setParameter("orderId", orderId);
//		query.setParameter("goodsCode", goodsCode);
//		queryCount.setParameter("goodsCode", goodsCode);
//		query.setParameter("goodsState", goodsState);
//		queryCount.setParameter("goodsState", goodsState);
//		
//		return (StockTransDetailDTO) query.uniqueResult();
//	}
//
//}
