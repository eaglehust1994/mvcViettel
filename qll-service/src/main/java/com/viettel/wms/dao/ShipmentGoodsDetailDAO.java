/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ShipmentGoodsDetailBO;
import com.viettel.wms.dto.AttachmentDTO;
import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;
import com.viettel.wms.dto.ShipmentGoodsDTO;
import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
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
@Repository("shipmentGoodsDetailDAO")
public class ShipmentGoodsDetailDAO extends BaseFWDAOImpl<ShipmentGoodsDetailBO, Long> {

	public ShipmentGoodsDetailDAO() {
		this.model = new ShipmentGoodsDetailBO();
	}

	public ShipmentGoodsDetailDAO(Session session) {
		this.session = session;
	}
//Hàm hiển thị danh sách hàng hóa trong màn hình Cập nhật hàng hóa cho lô hàng
	@SuppressWarnings("unchecked")
	public List<ShipmentGoodsDetailDTO> getDoMapDetail(ShipmentGoodsDetailDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT " + "WOSGD.SHIPMENT_GOODS_DETAIL_ID shipmentGoodsDetailId,"
				+ "WOSGD.GOODS_ID goodsId," + "WOSGD.GOODS_CODE goodsCode," + "WOSGD.GOODS_NAME goodsName,"
				+ "WOSGD.AMOUNT amount," + "WOSGD.SERIAL serial," + "WOSGD.UNIT_TYPE_ID unitTypeId,"
				+ "WOSGD.UNIT_TYPE_NAME unitTypeName," + "WOSGD.MANUFACTURER_ID manufacturerId,"
				+ "WOSGD.PRODUCING_COUNTRY_ID producingCountryId," + "WOSGD.MANUFACTURER_NAME manufacturerName,"
				+ "WOSGD.PRODUCING_COUNTRY_NAME producingCountryName," + "WOSGD.APPLY_PRICE applyPrice,"
				+ "WOSGD.PART_NUMBER partNumber," + "WOSGD.PRICE price," + "WOSGD.SHIPMENT_ID shipmentId,"
				+ "WOSGD.SHIPMENT_GOODS_ID shipmentGoodsId" + " FROM WMS_OWNER_KTTS.SHIPMENT_GOODS_DETAIL WOSGD"
				+ " WHERE 1=1 ");

		if (obj.getShipmentId() != null) {
			sql.append(" AND WOSGD.SHIPMENT_ID = :shipmentId");
			/*sql.append(
					" AND WOSGD.SHIPMENT_GOODS_ID IN (SELECT WOSG.SHIPMENT_GOODS_ID FROM WMS_OWNER_KTTS.SHIPMENT_GOODS WOSG WHERE WOSG.SHIPMENT_ID = :shipmentId )");*/
		}
		sql.append(" ORDER BY WOSGD.SHIPMENT_GOODS_DETAIL_ID ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("shipmentGoodsDetailId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("serial", new StringType());
		query.addScalar("unitTypeId", new LongType());
		query.addScalar("unitTypeName", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("applyPrice", new DoubleType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("price", new DoubleType());
		query.addScalar("shipmentId", new LongType());
		query.addScalar("shipmentGoodsId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDetailDTO.class));

		if (obj.getShipmentId() != null) {
			query.setParameter("shipmentId", obj.getShipmentId());
		}

		return query.list();
	}
//End
	public List<ShipmentGoodsDetailDTO> getGoodsInfoByCode(String code) {
		StringBuilder sql = new StringBuilder("SELECT " + "sgd.SHIPMENT_GOODS_DETAIL_ID id," + "sgd.GOODS_ID goodsId,"
				+ "sgd.GOODS_CODE goodsCode," + "sgd.GOODS_NAME goodsName," + "sgd.AMOUNT amount,"
				+ "sgd.SERIAL serial," + "sgd.UNIT_TYPE_ID unitTypeId," + "sgd.UNIT_TYPE_NAME unitTypeName,"
				+ "sgd.MANUFACTURER_ID manufacturerId," + "sgd.PRODUCING_COUNTRY_ID producingCountryId,"
				+ "sgd.MANUFACTURER_NAME manufacturerName," + "sgd.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ "sgd.APPLY_PRICE applyPrice," + "sgd.PART_NUMBER partNumber," + "sgd.PRICE price,"
				+ "sgd.SHIPMENT_ID shipmentId," + "sgd.SHIPMENT_GOODS_ID shipmentGoodsId"
				+ " FROM WMS_OWNER_KTTS.SHIPMENT_GOODS_DETAIL sgd"
				+ " JOIN WMS_OWNER_KTTS.SHIPMENT s ON s.SHIPMENT_ID = sgd.SHIPMENT_ID " + " WHERE s.CODE =:code ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("serial", new StringType());
		query.addScalar("unitTypeId", new LongType());
		query.addScalar("unitTypeName", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("applyPrice", new DoubleType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("price", new DoubleType());
		query.addScalar("shipmentId", new LongType());
		query.addScalar("shipmentGoodsId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDetailDTO.class));

		query.setParameter("code", code);

		return query.list();
	}

	public List<ShipmentGoodsDetailDTO> getGoodsDetail(ShipmentGoodsDetailDTO obj) {

		StringBuilder sql = new StringBuilder(
				"SELECT " + "sgd.SHIPMENT_GOODS_DETAIL_ID id," + "sgd.GOODS_ID goodsId," + "sgd.GOODS_CODE goodsCode,"
						+ "sgd.GOODS_NAME goodsName," + "sgd.AMOUNT amount," + "sgd.SERIAL serial," 
						+ "sgd.MANUFACTURER_ID manufacturerId," + "sgd.PRODUCING_COUNTRY_ID producingCountryId," + "sgd.PRICE price,"
						+ "sgd.MANUFACTURER_NAME manufacturer," + "sgd.PRODUCING_COUNTRY_NAME producerCountry,"
						+ "sgd.PART_NUMBER partNumber" + " FROM WMS_OWNER_KTTS.SHIPMENT_GOODS_DETAIL sgd"
						+ " JOIN WMS_OWNER_KTTS.SHIPMENT_GOODS sg ON sg.SHIPMENT_GOODS_ID = sgd.SHIPMENT_GOODS_ID "
						+ " WHERE sgd.GOODS_ID =:goodsId AND sgd.SHIPMENT_GOODS_ID = :shipmentGoodsId");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("serial", new StringType());
		query.addScalar("manufacturer", new StringType());
		query.addScalar("producerCountry", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("price", new DoubleType());

		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDetailDTO.class));

		query.setParameter("goodsId", obj.getGoodsId());
		query.setParameter("shipmentGoodsId", obj.getShipmentGoodsId());

		return query.list();

	}

	public List<AttachmentDTO> getGoodsFile(AttachmentDTO obj) {

		StringBuilder sql = new StringBuilder(
						  "SELECT " 
						+ "a.NAME name," 
					    + "a.APP_PARAM_CODE appParamCode"
						+ " From WMS_OWNER_KTTS.ATTACHMENT a where a.TYPE=1 ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("name", new StringType());
		query.addScalar("appParamCode", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(AttachmentDTO.class));

		return query.list();

	}
	public ShipmentGoodsDetailDTO checkSerial(ShipmentGoodsDetailDTO obj){
		StringBuilder sql=new StringBuilder(
				"SELECT sgd.GOODS_ID goodsId,sgd.SHIPMENT_ID shipmentId,sgd.SERIAL serial from WMS_OWNER_KTTS.SHIPMENT_GOODS_DETAIL sgd"
				+" where sgd.GOODS_ID=:goodsId and upper(sgd.SERIAL)=upper(:serial) and sgd.SHIPMENT_ID=:shipmentId "
				);
		SQLQuery query=getSession().createSQLQuery(sql.toString());
		query.addScalar("goodsId", new LongType());
		query.addScalar("serial", new StringType());
		query.addScalar("shipmentId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDetailDTO.class));
		if(obj.getGoodsId()!=null){
		query.setLong("goodsId",obj.getGoodsId());
		}
		if(obj.getSerial()!=null){
		query.setParameter("serial", obj.getSerial());
		}
		if(obj.getShipmentId()!=null){
			query.setLong("shipmentId", obj.getShipmentId());
			}
		return (ShipmentGoodsDetailDTO)query.uniqueResult();
	}
	 //Xóa hàng hóa của lô hàng//
    public void deleteGoods(Long shipmentGoodsDetailId){
    	StringBuilder sql=new StringBuilder("Delete from WMS_OWNER_KTTS.SHIPMENT_GOODS_DETAIL WOSG where WOSG.SHIPMENT_GOODS_DETAIL_ID=:shipmentGoodsDetailId ");
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	query.setResultTransformer(Transformers.aliasToBean(ShipmentGoodsDetailDTO.class));
    	query.setLong("shipmentGoodsDetailId",shipmentGoodsDetailId);
    	int result=query.executeUpdate();
    }
    //End
    
  
}
