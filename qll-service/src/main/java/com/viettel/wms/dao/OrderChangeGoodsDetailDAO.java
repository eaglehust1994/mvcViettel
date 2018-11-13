///*
// * Copyright (C) 2011 Viettel Telecom. All rights reserved.
// * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.viettel.wms.dao;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.transform.Transformers;
//import org.hibernate.type.DateType;
//import org.hibernate.type.DoubleType;
//import org.hibernate.type.LongType;
//import org.hibernate.type.StringType;
//import org.springframework.stereotype.Repository;
//
//import com.viettel.service.base.dao.BaseFWDAOImpl;
//import com.viettel.wms.bo.OrderChangeGoodsDetailBO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.StockGoodsSerialDTO;
//import com.viettel.wms.utils.ValidateUtils;
//
///**
// * @author TruongBX3
// * @version 1.0
// * @since 08-May-15 4:07 PM
// */
//@Repository("orderChangeGoodsDetailDAO")
//public class OrderChangeGoodsDetailDAO extends BaseFWDAOImpl<OrderChangeGoodsDetailBO, Long> {
//
//	public OrderChangeGoodsDetailDAO() {
//		this.model = new OrderChangeGoodsDetailBO();
//	}
//
//	public OrderChangeGoodsDetailDAO(Session session) {
//		this.session = session;
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<OrderChangeGoodsDetailDTO> doSearch(OrderChangeGoodsDetailDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT OCG.ORDER_CHANGE_GOODS_ID orderChangeGoodsId," + "OCG.CODE code,"
//				+ "ORCGD.GOODS_CODE goodsCode," + "ORCGD.AMOUNT_CHANGE amountChange," + "ORCGD.SERIAL serial,"
//				+ "ORCGD.GOODS_UNIT_NAME goodsUnitName," + "OCG.DESCRIPTION description,"
//				+ "ORCGD.GOODS_NAME goodsName," + "ORCGD.ORDER_CHANGE_GOODS_DETAIL_ID orderChangeGoodsDetailId"
//				+ " FROM WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL ORCGD LEFT JOIN  WMS_OWNER_KTTS.ORDER_CHANGE_GOODS OCG ON (OCG.ORDER_CHANGE_GOODS_ID = ORCGD.ORDER_CHANGE_GOODS_ID)"
//				+ " WHERE 1=1");
//
//		sql.append(" ORDER BY CODE");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("orderChangeGoodsId", new LongType());
//		query.addScalar("orderChangeGoodsDetailId", new LongType());
//		query.addScalar("code", new StringType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("description", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("amountChange", new LongType());
//		query.addScalar("serial", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(OrderChangeGoodsDetailDTO.class));
//
//		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//
//		return query.list();
//	}
//
//	
//
//	
//	
//	/**
//	 * lấy tất cả serial để valiaddte khi import từ excel
//	 * @param obj
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<OrderChangeGoodsDetailDTO> checkSerial(OrderChangeGoodsDetailDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT OCGD.SERIAL serial  FROM WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL OCGD");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		query.addScalar("serial", new StringType());
//		query.setResultTransformer(Transformers.aliasToBean(OrderChangeGoodsDetailDTO.class));
//		return query.list();
//
//	}
//	
//	public boolean checkDuplicateSerial(String code,String newSerial){
//		StringBuilder sql = new StringBuilder("SELECT STGS.SERIAL serial "
//				+ " FROM WMS_OWNER_KTTS.STOCK_GOODS_SERIAL STGS"
//				+ " where STGS.GOODS_CODE =:goodsCode and STGS.SERIAL=:serial");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		query.addScalar("serial", new StringType());
//		query.setResultTransformer(Transformers.aliasToBean(StockGoodsSerialDTO.class));
//		query.setParameter("goodsCode", code);
//		query.setParameter("serial", newSerial);
//		List<StockGoodsSerialDTO> lst=query.list();
//		if(lst.size()>0){
//			return true;
//		}
//		return false;
//		
//	}
//	
//	
//	
//		
//	/**
//	 * check mã hàng có quản lý serial
//	 * @param code
//	 * @return
//	 */
//	public GoodsDTO checkIsSerial(String code) {
//		StringBuilder sql = new StringBuilder("SELECT G.IS_SERIAL isSerial FROM CAT_OWNER.GOODS G WHERE G.CODE =:code ");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("isSerial", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//
//		query.setParameter("code", code);
//
//		return (GoodsDTO) query.uniqueResult();
//	}
//	
//	/**
//	 * Check tên hàng có tồn tại
//	 * @param code
//	 * @return
//	 */
//	public GoodsDTO getNameByCode(String code){
//		StringBuilder sql = new StringBuilder("SELECT G.GOODS_ID goodsId,"	
//				+ " G.UNIT_TYPE goodsUnitId,"
//				+ " G.UNIT_TYPE_NAME goodsUnitName,"
//				+ " G.NAME name,"
//				+" G.ORIGIN_PRICE originPrice,"
//				+ " G.IS_SERIAL isSerial,"
//				+ " G.GOODS_TYPE goodsType,"
//				+ " GT.NAME goodsTypeName,"
//				+ " G.STATUS status,"
//				+ " G.MANUFACTURER_NAME manufacturerName,"
//				+ " G.MANUFACTURER_ID manufacturerId,"
//				+ " G.PRODUCING_COUNTRY_NAME producingCountryName,"
//				+ " G.PRODUCING_COUNTRY_ID producingCountryId,"
//				+ " G.CODE code,"
//				+ " G.CREATED_DATE createdDate"
//				+ " FROM CAT_OWNER.GOODS G "
//				+ " JOIN CAT_OWNER.GOODS_TYPE GT ON GT.GOODS_TYPE_ID = G.GOODS_TYPE "
//				+ " WHERE G.STATUS=1 ");
//		
//		
//			sql.append(" AND G.CODE=:code");
//		
//
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("code", new StringType());
//		query.addScalar("isSerial", new StringType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("name", new StringType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("status", new StringType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("manufacturerId", new LongType());
//		query.addScalar("producingCountryName", new StringType());
//		query.addScalar("goodsTypeName", new StringType());
//		query.addScalar("producingCountryId", new LongType());
//		query.addScalar("originPrice", new DoubleType());
//		query.addScalar("createdDate", new DateType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//		
//
//		query.setParameter("code", code);
//
//		return (GoodsDTO) query.uniqueResult();
//	}
//	
//	//check tên hàng có phù hượp vs mã hàng
//	public GoodsDTO checkNameByCode(String code,String name){
//		StringBuilder sql = new StringBuilder("SELECT G.NAME name FROM CAT_OWNER.GOODS G WHERE G.CODE =:code and  G.NAME=:name");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("name", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//
//		query.setParameter("code", code);
//		query.setParameter("name", name);
//
//		return (GoodsDTO) query.uniqueResult();
//	}
//
//	public GoodsDTO selectNameByCode(String code){
//		StringBuilder sql = new StringBuilder("SELECT CODE code FROM CAT_OWNER.GOODS where CODE=:code ");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("code", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//
//		query.setParameter("code", code);
//
//		return (GoodsDTO) query.uniqueResult();
//	}
//	
//}
