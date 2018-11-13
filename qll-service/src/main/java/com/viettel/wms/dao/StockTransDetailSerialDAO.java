/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockTransDetailSerialBO;
import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;
import com.viettel.wms.dto.OrderGoodsDetailDTO;
import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
import com.viettel.wms.dto.StockTransDetailSerialDTO;
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
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("stockTransDetailSerialDAO")
public class StockTransDetailSerialDAO extends
		BaseFWDAOImpl<StockTransDetailSerialBO, Long> {

	public StockTransDetailSerialDAO() {
		this.model = new StockTransDetailSerialBO();
	}

	public StockTransDetailSerialDAO(Session session) {
		this.session = session;
	}

	public List<StockTransDetailSerialDTO> doSearchGoodsDetailForImportNote(
			StockTransDetailSerialDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT stds.STOCK_TRANS_DETAIL_SERIAL_ID stockTransDetailSerialId,"
						+ "stds.SERIAL serial,"
						+ "stds.CONTRACT_CODE contractCode,"
						+ "stds.PART_NUMBER partNumber,"
						+ "stds.MANUFACTURER_NAME manufacturerName,"
						+ "stds.CELL_CODE cellCode,"
						+ "stds.PRODUCING_COUNTRY_NAME producingCountryName "
						+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL stds "
						+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std ON std.STOCK_TRANS_DETAIL_ID = stds.STOCK_TRANS_DETAIL_ID "
						+ "WHERE stds.STOCK_TRANS_DETAIL_ID = :stockTransDetailId");

		sql.append(" ORDER BY stds.STOCK_TRANS_DETAIL_SERIAL_ID DESC");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockTransDetailSerialId", new LongType());
		query.addScalar("cellCode", new StringType());
		query.addScalar("serial", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockTransDetailSerialDTO.class));
		query.setParameter("stockTransDetailId", obj.getStockTransDetailId());
		queryCount.setParameter("stockTransDetailId",
				obj.getStockTransDetailId());
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
			}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public List<StockTransDetailSerialDTO> doSearchByStockTransDetailId(Long id) {
		StringBuilder sql = new StringBuilder(
				"SELECT stds.STOCK_TRANS_DETAIL_SERIAL_ID stockTransDetailSerialId,"
						+ "stds.SERIAL serial,"
						+ "stds.CONTRACT_CODE contractCode,"
						+ "stds.PART_NUMBER partNumber,"
						+ "stds.MANUFACTURER_ID manufacturerId,"
						+ "stds.PRODUCING_COUNTRY_ID producingCountryId, "
						+ "stds.MANUFACTURER_NAME manufacturerName,"
						+ "stds.PRODUCING_COUNTRY_NAME producingCountryName "
						+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL stds "
						+ "JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std ON std.STOCK_TRANS_DETAIL_ID = stds.STOCK_TRANS_DETAIL_ID "
						+ "WHERE stds.STOCK_TRANS_DETAIL_ID = :stockTransDetaiId");

		sql.append(" ORDER BY stds.STOCK_TRANS_DETAIL_SERIAL_ID DESC");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("stockTransDetailSerialId", new LongType());
		query.addScalar("serial", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockTransDetailSerialDTO.class));
		query.setParameter("stockTransDetaiId", id);

		return query.list();
	}

	public List<StockTransDetailSerialDTO> getGoodsInfoByCode(String code) {
		StringBuilder sql = new StringBuilder(
				"SELECT "
						+ "stdr.STOCK_TRANS_DETAIL_SERIAL_ID id,"
						+ "stdr.GOODS_ID goodsId,"
						+ "std.GOODS_CODE goodsCode,"
						+ "std.GOODS_NAME goodsName,"
						+ "std.AMOUNT_REAL amount,"
						+ "stdr.SERIAL serial,"
						+ "std.GOODS_UNIT_NAME unitTypeName,"
						+ "stdr.MANUFACTURER_ID manufacturerId,"
						+ "stdr.PRODUCING_COUNTRY_ID producingCountryId,"
						+ "stdr.MANUFACTURER_NAME manufacturerName,"
						+ "stdr.PRODUCING_COUNTRY_NAME producingCountryName,"
						+ "stdr.PART_NUMBER partNumber,"
						+ "stdr.PRICE price"
						+ " FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL stdr"
						+ " JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std ON stdr.STOCK_TRANS_DETAIL_ID = std.STOCK_TRANS_DETAIL_ID "
						+ " JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON st.STOCK_TRANS_ID = std.STOCK_TRANS_ID "
						+ " WHERE st.CODE =:code ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("serial", new StringType());
		query.addScalar("unitTypeName", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("price", new DoubleType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockTransDetailSerialDTO.class));

		query.setParameter("code", code);

		return query.list();
	}

	public List<StockTransDetailSerialDTO> doSearchGoodsDetailForExportNote() {
		StringBuilder sql = new StringBuilder(
				"SELECT stds.STOCK_TRANS_DETAIL_SERIAL_ID stockTransDetailSerialId,"
						+ "stds.STOCK_TRANS_DETAIL_ID stockTransDetailId, "
						+ "stds.SERIAL serial,"
						+ "stds.CONTRACT_CODE contractCode,"
						+ "stds.PART_NUMBER partNumber,"
						+ "stds.MANUFACTURER_NAME manufacturerName,"
						+ "stds.PRODUCING_COUNTRY_NAME producingCountryName "
						+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL stds ");

		sql.append(" ORDER BY stds.STOCK_TRANS_DETAIL_SERIAL_ID DESC");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("stockTransDetailId", new LongType());
		query.addScalar("stockTransDetailSerialId", new LongType());
		query.addScalar("serial", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockTransDetailSerialDTO.class));
		return query.list();

	}

	public List<StockTransDetailSerialDTO> getGoodsDetail(
			StockTransDetailSerialDTO obj) {

		StringBuilder sql = new StringBuilder(
				"SELECT "
						+ "stds.STOCK_TRANS_DETAIL_SERIAL_ID id,"
						+ "stds.GOODS_ID goodsId,"
						+ "stds.SERIAL serial,"
						+ "stds.CONTRACT_CODE contractCode,"
						+ "stds.MANUFACTURER_NAME manufacturer,"
						+ "stds.PRODUCING_COUNTRY_NAME producerCountry,"
						+ "stds.CELL_CODE cellCode,"
						+ "stds.PART_NUMBER partNumber"
						+ " FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL stds"
						+ " JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std ON std.STOCK_TRANS_DETAIL_ID = stds.STOCK_TRANS_DETAIL_ID "
						+ " WHERE stds.GOODS_ID =:goodsId AND stds.STOCK_TRANS_DETAIL_ID = :stockTransDetailId");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("serial", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("manufacturer", new StringType());
		query.addScalar("producerCountry", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("cellCode", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockTransDetailSerialDTO.class));

		query.setParameter("goodsId", obj.getGoodsId());
		query.setParameter("stockTransDetailId", obj.getStockTransDetailId());

		return query.list();

	}

	/**
	 * Danh sach thong tin serial
	 * 
	 * @param obj
	 * @return
	 */
	public List<StockTransDetailSerialDTO> doSearchGoodsDetailForExportNote(
			StockTransDetailSerialDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT stds.STOCK_TRANS_DETAIL_SERIAL_ID stockTransDetailSerialId,"
						+ "stds.SERIAL serial,"
						+ "stds.CONTRACT_CODE contractCode,"
						+ "stds.PART_NUMBER partNumber,"
						+ "stds.MANUFACTURER_NAME manufacturerName,"
						+ "stds.CELL_CODE cellCode,"
						+ "stds.PRODUCING_COUNTRY_NAME producingCountryName "
						+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL stds "
						+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std ON std.STOCK_TRANS_DETAIL_ID = stds.STOCK_TRANS_DETAIL_ID "
						+ "WHERE stds.STOCK_TRANS_DETAIL_ID = :stockTransDetailId");

		sql.append(" ORDER BY stds.STOCK_TRANS_DETAIL_SERIAL_ID DESC");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockTransDetailSerialId", new LongType());
		query.addScalar("cellCode", new StringType());
		query.addScalar("serial", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockTransDetailSerialDTO.class));
		query.setParameter("stockTransDetailId", obj.getStockTransDetailId());
		queryCount.setParameter("stockTransDetailId",
				obj.getStockTransDetailId());
		query.setFirstResult((obj.getPage().intValue() - 1)
				* obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();

	}

	/**
	 * Chi tiet Serial
	 * 
	 * @param obj
	 * @return
	 */
	public List<StockTransDetailSerialDTO> doSearchGoodsDetailSerial(
			StockTransDetailSerialDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT stds.STOCK_TRANS_DETAIL_SERIAL_ID stockTransDetailSerialId,"
						+ "stds.SERIAL serial,"
						+ "stds.CONTRACT_CODE contractCode,"
						+ "stds.PART_NUMBER partNumber,"
						+ "stds.MANUFACTURER_NAME manufacturerName,"
						+ "stds.CELL_CODE cellCode,"
						+ "stds.PRODUCING_COUNTRY_NAME producingCountryName, "
						+ "std.GOODS_UNIT_NAME goodsUnitName,"
						+ "std.AMOUNT_ORDER amount,"
						+ "std.AMOUNT_REAL amountReal,"
						+ "std.GOODS_STATE goodState,"
						+ "std.GOODS_NAME goodsName,"
						+ "std.GOODS_CODE goodsCode,"
						+ "std.STOCK_TRANS_DETAIL_ID stockTransDetailId "
						+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL stds "
						+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL std ON std.STOCK_TRANS_DETAIL_ID = stds.STOCK_TRANS_DETAIL_ID "
						+ "WHERE 1=1");

		if (obj.getStockTransDetailId() != null) {
			sql.append("And stds.STOCK_TRANS_DETAIL_ID = :stockTransDetailId");
		}
		sql.append(" ORDER BY stds.STOCK_TRANS_DETAIL_SERIAL_ID DESC");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockTransDetailSerialId", new LongType());
		query.addScalar("cellCode", new StringType());
		query.addScalar("serial", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("amountReal", new LongType());
		query.addScalar("goodState", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("stockTransDetailId", new LongType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockTransDetailSerialDTO.class));
		if (obj.getStockTransDetailId() != null) {
			query.setParameter("stockTransDetailId",
					obj.getStockTransDetailId());
			queryCount.setParameter("stockTransDetailId",
					obj.getStockTransDetailId());
		}
		/*
		 * query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().
		 * intValue()); query.setMaxResults(obj.getPageSize().intValue());
		 * obj.setTotalRecord(((BigDecimal)
		 * queryCount.uniqueResult()).intValue());
		 */

		return query.list();
	}

}
