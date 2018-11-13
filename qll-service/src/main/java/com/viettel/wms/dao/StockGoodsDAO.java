/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockGoodsBO;
import com.viettel.wms.dto.StockDailyRemainDTO;
import com.viettel.wms.dto.StockGoodsDTO;
import com.viettel.wms.dto.StockGoodsTotalDTO;
import com.viettel.wms.dto.StockTransDetailDTO;
import com.viettel.wms.dto.StockTransDetailSerialDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("stockGoodsDAO")
public class StockGoodsDAO extends BaseFWDAOImpl<StockGoodsBO, Long> {

	public StockGoodsDAO() {
		this.model = new StockGoodsBO();
	}

	public StockGoodsDAO(Session session) {
		this.session = session;
	}

	public List<StockGoodsDTO> findGoodsFromStock(StockTransDetailDTO obj){
		StringBuilder sql;
					sql = new StringBuilder("SELECT sg.STOCK_GOODS_ID stockGoodsId,"
					+ "sg.STOCK_ID stockId,"
					+ "sg.GOODS_ID goodsId,"
					+ "sg.GOODS_STATE goodsState,"
					+ "sg.GOODS_STATE_NAME goodsStateName,"
					+ "sg.GOODS_NAME goodsName,"
					+ "sg.GOODS_CODE goodsCode,"
					+ "sg.GOODS_TYPE goodsType,"
					+ "sg.GOODS_IS_SERIAL goodsIsSerial,"
					+ "sg.GOODS_UNIT_NAME goodsUnitName,"
					+ "sg.AMOUNT amount,"
					+ "sg.AMOUNT_ISSUE amountIssue,"
					+ "sg.IMPORT_DATE importDate,"
					+ "sg.STATUS status, "
					+ "sg.ORDER_ID orderId,"
					+ "sg.IMPORT_STOCK_TRANS_ID importStockTransId,"
					+ "sg.PRICE price,"
					+ "sg.CONTRACT_CODE contractCode,"
					+ "sg.SHIPMENT_CODE shipmentCode,"
					+ "sg.PROJECT_CODE projectCode,"
					+ "sg.PART_NUMBER partNumber,"
					+ "sg.MANUFACTURER_ID manufacturerId,"
					+ "sg.PRODUCING_COUNTRY_ID producingCountryId,"
					+ "sg.MANUFACTURER_NAME manufacturerName,"
					+ "sg.PRODUCING_COUNTRY_NAME producingCountryName,"
					+ "sg.GOODS_TYPE_NAME goodsTypeName,"
					+ "sg.GOODS_UNIT_ID goodsUnitId,"
					+ "sg.TOTAL_PRICE totalPrice"
					+ " FROM WMS_OWNER_KTTS.STOCK_GOODS sg " 
					+ " INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std ON std.GOODS_ID = sg.GOODS_ID "
					+ " WHERE sg.STATUS = '1' AND sg.GOODS_ID = :goodsId AND sg.STOCK_ID =:stockId AND sg.GOODS_STATE =:goodsState");	
		sql.append(" ORDER BY sg.IMPORT_DATE ");
		
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("stockGoodsId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("amountIssue", new DoubleType());
		query.addScalar("importDate", new TimestampType());
		query.addScalar("status", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsType", new LongType());
		query.addScalar("goodsIsSerial", new StringType());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("importStockTransId", new LongType());
		query.addScalar("price", new DoubleType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("shipmentCode", new StringType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("totalPrice", new DoubleType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(StockGoodsDTO.class));
		query.setParameter("goodsId", obj.getGoodsId());
		query.setParameter("stockId", obj.getStockId());
		query.setParameter("goodsState",obj.getGoodsState());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<StockGoodsDTO> detailStockGoods(StockGoodsDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT DISTINCT  SG.GOODS_ID goodsId,"
				+ "SG.STOCK_ID stockId, "
				+ "SG.MANUFACTURER_ID manufacturerId, "
				+ "SG.PRODUCING_COUNTRY_ID producingCountryId,"
				+ "SG.MANUFACTURER_NAME manufacturerName, "
				+ "SG.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ "SG.CONTRACT_CODE contractCode,"
				+ "SG.PART_NUMBER partNumber"
				
				+ " From  WMS_OWNER_KTTS.STOCK_GOODS SG "
				+ " INNER JOIN CAT_OWNER.STOCK CS On CS.STOCK_ID = SG.STOCK_ID "
				+ " INNER JOIN CAT_OWNER.GOODS CG ON CG.GOODS_ID = SG.GOODS_ID "
				+ " WHERE 1=1");
		
		
		if (obj.getStockId()!=null){
			sql.append(" AND SG.STOCK_ID = :stockId");
		}
		if (obj.getGoodsId() != null) {
			sql.append(" AND SG.GOODS_ID = :goodsId");
		}
		

		SQLQuery query= getSession().createSQLQuery(sql.toString());

		query.addScalar("goodsId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		

		query.setResultTransformer(Transformers.aliasToBean(StockGoodsDTO.class));
		
		
		if (obj.getStockId() != null) {
			query.setParameter("stockId", obj.getStockId());
		}
		if (obj.getGoodsId() != null) {
			query.setParameter("goodsId", obj.getGoodsId());
		}
		
		

		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<StockGoodsDTO> detailStockGoodsSerial(StockGoodsDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT DISTINCT  SG.GOODS_ID goodsId,"
				+ "SG.STOCK_ID stockId, "
				+ "SG.MANUFACTURER_ID manufacturerId,  "
				+ "SG.PRODUCING_COUNTRY_ID producingCountryId,"
				+ "SG.MANUFACTURER_NAME manufacturerName, "
				+ "SG.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ "SG.CONTRACT_CODE contractCode,"
				+ "SG.PART_NUMBER partNumber,"
				+ "SG.SERIAL serial"
				+ " From  WMS_OWNER_KTTS.STOCK_GOODS_SERIAL SG "
				+ " INNER JOIN CAT_OWNER.STOCK CS On CS.STOCK_ID = SG.STOCK_ID "
				+ " INNER JOIN CAT_OWNER.GOODS CG ON CG.GOODS_ID = SG.GOODS_ID "
				+ " WHERE 1=1");
		
		
		if (obj.getStockId()!=null){
			sql.append(" AND SG.STOCK_ID = :stockId");
		}
		if (obj.getGoodsId() != null) {
			sql.append(" AND SG.GOODS_ID = :goodsId");
		}
		

		SQLQuery query= getSession().createSQLQuery(sql.toString());

		query.addScalar("goodsId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("serial", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(StockGoodsDTO.class));
		
		
		if (obj.getStockId() != null) {
			query.setParameter("stockId", obj.getStockId());
		}
		if (obj.getGoodsId() != null) {
			query.setParameter("goodsId", obj.getGoodsId());
		}
		
		
		return query.list();
	}
	
	public void updateNonSerial(StockGoodsDTO obj){
		StringBuilder sql = new StringBuilder("UPDATE STOCK_GOODS SET "
				+ "AMOUNT=NVL(:amount,AMOUNT), "
				+ "CHANGE_DATE=:changeDate,"
				+ "ORDER_ID=:orderId,"
				+ "STATUS=:status "
				+ "WHERE STOCK_GOODS_ID=:stockGoodsId");
	SQLQuery query=getSession().createSQLQuery(sql.toString());
	query.setParameter("amount", obj.getAmount());
	query.setTimestamp("changeDate", obj.getChangeDate());
	query.setParameter("orderId", obj.getOrderId());
	query.setParameter("stockGoodsId", obj.getStockGoodsId());
	query.setParameter("status", obj.getStatus());
	query.executeUpdate();
	}
}
