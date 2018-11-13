/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

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

import com.viettel.wms.bo.ShipmentGoodsBO;
import com.viettel.wms.dto.ShipmentGoodsDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("shipmentGoodsDAO")
public class ShipmentGoodsDAO extends BaseFWDAOImpl<ShipmentGoodsBO, Long> {

	public ShipmentGoodsDAO() {
		this.model = new ShipmentGoodsBO();
	}

	public ShipmentGoodsDAO(Session session) {
		this.session = session;
	}
// Hiển thị danh sách hàng hóa màn định lượng
	@SuppressWarnings("unchecked")
	public List<ShipmentGoodsDTO> doSearchMap(ShipmentGoodsDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT WOSG.SHIPMENT_GOODS_ID shipmentGoodsId,"
				+ " WOSG.GOODS_ID goodsId," 
				+ " WOSG.GOODS_CODE goodsCode," 
				+ " WOSG.GOODS_NAME goodsName,"
				+ " WOSG.AMOUNT amount," 
				+ " WOSG.UNIT_TYPE_ID unitTypeId," 
				+ " WOSG.UNIT_TYPE_NAME unitTypeName,"
				+ " WOSG.ORIGIN_PRICE originPrice," 
				+ " WOSG.TOTAL_ORIGIN_PRICE totalOriginPrice,"
				+ " WOSG.ESTIMATE_PRICE estimatePrice," 
				+ " WOSG.ESTIMATE_PRICE_SUM estimatePriceSum,"
				+ " WOSG.ESTIMATE_PRICE_PERCENT estimatePricePercent," 
				+ " WOSG.APPLY_PRICE applyPrice,"
				+ " WOSG.APPLY_TOTAL_MONEY applyTotalMoney," 
				+ " WOSG.SHIPMENT_ID shipmentId,"
				+ " CG.MANUFACTURER_ID manufacturerId,"
				+ " CG.PRODUCING_COUNTRY_ID producingCountryId,"
				+ " CG.MANUFACTURER_NAME manufacturerName,"
				+ " CG.PRODUCING_COUNTRY_NAME producingCountryName"
				+ " FROM WMS_OWNER_KTTS.SHIPMENT_GOODS WOSG "
				+ " INNER JOIN WMS_OWNER_KTTS.SHIPMENT ST ON WOSG.SHIPMENT_ID = ST.SHIPMENT_ID "
				+ " INNER JOIN CAT_OWNER.GOODS CG on CG.GOODS_ID = WOSG.GOODS_ID"
				+ " WHERE 1=1 ");

		if (obj.getShipmentId() != null) {
			sql.append(" AND WOSG.SHIPMENT_ID = :shipmentId");
		}
		sql.append(" ORDER BY WOSG.GOODS_CODE");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("shipmentGoodsId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("unitTypeId", new LongType());
		query.addScalar("unitTypeName", new StringType());
		query.addScalar("originPrice", new DoubleType());
		query.addScalar("totalOriginPrice", new DoubleType());
		query.addScalar("estimatePrice", new DoubleType());
		query.addScalar("estimatePriceSum", new DoubleType());
		query.addScalar("estimatePricePercent", new DoubleType());
		query.addScalar("applyPrice", new DoubleType());
		query.addScalar("applyTotalMoney", new DoubleType());
		query.addScalar("shipmentId", new LongType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDTO.class));

		if (obj.getShipmentId() != null) {
			query.setParameter("shipmentId", obj.getShipmentId());
		}

		return query.list();
	}
	//End
	//getForAutoComplete
	@SuppressWarnings("unchecked")
	public List<ShipmentGoodsDTO> getForAutoComplete(ShipmentGoodsDTO obj) {
		String sql = "SELECT SMG.SHIPMENT_GOODS_ID shipmentGoodsId"	
			+ " ,SMG.GOODS_ID goodsId"		
			+ " ,SMG.GOODS_CODE goodsCode"
			+ " ,SMG.GOODS_NAME goodsName"
			+ " ,SMG.UNIT_TYPE_ID unitTypeId"
			+ " ,SMG.UNIT_TYPE_NAME unitTypeName"
			+ " FROM WMS_OWNER_KTTS.SHIPMENT_GOODS SMG"
			+ " WHERE 1=1 ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10 " : "");
		if (obj.getIsSize()){
			stringBuilder.append(StringUtils.isNotEmpty(obj.getGoodsName()) ? " AND (upper(SMG.GOODS_NAME) LIKE upper(:goodsName) escape '&' OR upper(SMG.GOODS_CODE) LIKE upper(:goodsName) escape '&')" : "");
		}
		else{
			stringBuilder.append(StringUtils.isNotEmpty(obj.getGoodsName()) ? " AND upper(SMG.GOODS_NAME) LIKE upper(:goodsName) escape '&'" + (StringUtils.isNotEmpty(obj.getGoodsCode()) ? " AND upper(SMG.CODE) LIKE upper(:value) escape '&'" : "") : (StringUtils.isNotEmpty(obj.getGoodsCode()) ? " AND upper(sMG.CODE) LIKE upper(:value) escape '&'" : ""));
		}
		
		stringBuilder.append(" ORDER BY SMG.GOODS_CODE");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("shipmentGoodsId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("unitTypeId", new LongType());
		query.addScalar("unitTypeName", new StringType());
	
		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDTO.class));

		if (StringUtils.isNotEmpty(obj.getGoodsName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getGoodsName()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getGoodsName())) {
			query.setParameter("value", "%" + ValidateUtils.validateKeySearch(obj.getGoodsName()) + "%");
		}

		return query.list();
	}
	//End
	//Hàm lấy thông tin hàng hóa trong bảng ShipmentGoods theo mã 
	public List<ShipmentGoodsDTO> getGoodsInfoByCode(String code){
		StringBuilder sql = new StringBuilder("SELECT "
				+ "sg.SHIPMENT_GOODS_ID id,"
				+ "sg.GOODS_ID goodsId,"
				+ "sg.GOODS_CODE goodsCode,"
				+ "sg.GOODS_NAME goodsName,"
				+ "sg.AMOUNT amount,"
				+ "sg.UNIT_TYPE_ID goodsUnitId,"
				+ "sg.UNIT_TYPE_NAME goodsUnitName"
				+ " FROM WMS_OWNER_KTTS.SHIPMENT_GOODS sg"
				+ " JOIN WMS_OWNER_KTTS.SHIPMENT s ON s.SHIPMENT_ID = sg.SHIPMENT_ID "
				+ " WHERE s.CODE =:code");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("id", new LongType());
		query.addScalar("goodsId",new LongType());
		query.addScalar("goodsCode",new StringType());
		query.addScalar("goodsName",new StringType());
		query.addScalar("amount",new DoubleType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("goodsUnitName",new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDTO.class));
		
		query.setParameter("code", code);
		
		return query.list();
	}
	//End
	//
	public List<ShipmentGoodsDTO> getShipmentGoodsPrice(ShipmentGoodsDTO obj){
		StringBuilder sql = new StringBuilder("SELECT WOSG.SHIPMENT_GOODS_ID shipmentGoodsId,"
				+ " WOSG.GOODS_ID goodsId," 
				+ " WOSG.GOODS_CODE goodsCode," 
				+ " WOSG.GOODS_NAME goodsName,"
				+ " WOSG.AMOUNT amount," 
				+ " WOSG.UNIT_TYPE_ID unitTypeId," 
				+ " WOSG.UNIT_TYPE_NAME unitTypeName,"
				+ " WOSG.ORIGIN_PRICE originPrice," 
				+ " WOSG.TOTAL_ORIGIN_PRICE totalOriginPrice,"
				+ " WOSG.ESTIMATE_PRICE estimatePrice," 
				+ " WOSG.ESTIMATE_PRICE_SUM estimatePriceSum,"
				+ " WOSG.ESTIMATE_PRICE_PERCENT estimatePricePercent," 
				+ " WOSG.APPLY_PRICE applyPrice,"
				+ " WOSG.APPLY_TOTAL_MONEY applyTotalMoney,"
				+ " SGD.SERIAL serial,"
				+ " SGD.PART_NUMBER partNumber,"
				+ " SGD.MANUFACTURER_ID manufacturerId,"
				+ " SGD.MANUFACTURER_NAME manufacturerName,"
				+ " SGD.PRODUCING_COUNTRY_ID producingCountryId,"
				+ " SGD.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ " WOSG.SHIPMENT_ID shipmentId"
				+ " FROM WMS_OWNER_KTTS.SHIPMENT_GOODS WOSG "
				+ " INNER JOIN WMS_OWNER_KTTS.SHIPMENT ST ON WOSG.SHIPMENT_ID = ST.SHIPMENT_ID "
				+ " INNER JOIN SHIPMENT_GOODS_DETAIL  SGD ON WOSG.SHIPMENT_GOODS_ID = SGD.SHIPMENT_GOODS_ID"
				+ " WHERE 1=1 ");

		if (obj.getShipmentId() != null) {
			sql.append(" AND WOSG.SHIPMENT_ID = :shipmentId");
		}
		
		sql.append(" ORDER BY WOSG.GOODS_CODE ASC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("shipmentGoodsId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("unitTypeId", new LongType());
		query.addScalar("unitTypeName", new StringType());
		query.addScalar("originPrice", new DoubleType());
		query.addScalar("totalOriginPrice", new DoubleType());
		query.addScalar("estimatePrice", new DoubleType());
		query.addScalar("estimatePriceSum", new DoubleType());
		query.addScalar("estimatePricePercent", new DoubleType());
		query.addScalar("applyPrice", new DoubleType());
		query.addScalar("applyTotalMoney", new DoubleType());
		query.addScalar("shipmentId", new LongType());
		query.addScalar("serial", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("manufacturerName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDTO.class));

		if (obj.getShipmentId() != null) {
			query.setParameter("shipmentId", obj.getShipmentId());
			queryCount.setParameter("shipmentId", obj.getShipmentId());
		}
		
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}
	//End
	 //Xóa hàng hóa của lô hàng//
    public void deleteGoods(Long shipmentGoodsId){
    	StringBuilder sql=new StringBuilder("Delete from WMS_OWNER_KTTS.SHIPMENT_GOODS WOSG where WOSG.SHIPMENT_GOODS_ID=:shipmentGoodsId ");
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDTO.class));
    	query.setLong("shipmentGoodsId",shipmentGoodsId);
    	int result=query.executeUpdate();
    }
    //End
}
