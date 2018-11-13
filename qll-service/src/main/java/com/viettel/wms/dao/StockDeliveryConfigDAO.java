/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockDeliveryConfigBO;
import com.viettel.wms.dto.StockCellDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.StockDeliveryConfigDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("stockDeliveryConfigDAO")
public class StockDeliveryConfigDAO extends BaseFWDAOImpl<StockDeliveryConfigBO, Long> {

	public StockDeliveryConfigDAO() {
		this.model = new StockDeliveryConfigBO();
	}

	public StockDeliveryConfigDAO(Session session) {
		this.session = session;
	}

	public List<StockDeliveryConfigDTO> doSearch() {
		StringBuilder sql = new StringBuilder(
				"SELECT STC.DELIVERY_ID deliveryId, STC.DELIVERY_ONE_NAME deliveryOneName, STC.DELIVERY_ONE_ADDRESS deliveryOneAddress, STC.DELIVERY_ONE_REPRESENT deliveryOneRepresent, STC.DELIVERY_TWO_NAME deliveryTwoName, STC.DELIVERY_TWO_ADDRESS deliveryTwoAddress, STC.DELIVERY_TWO_REPRESENT deliveryTwoRepresent, STC.TRANFER_NAME tranferName, STC.TRANFER_ADDRESS tranferAddress, STC.TRANFER_REPRESENT tranferRepresent, STC.STOCK_ID stockId"
						+ " FROM CAT_OWNER.STOCK_DELIVERY_CONFIG STC INNER JOIN CAT_OWNER.STOCK ST ON (ST.STOCK_ID = STC.STOCK_ID) WHERE 1=1 ");

		sql.append(" ORDER BY STC.DELIVERY_ID");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("deliveryId", new LongType());
		
		query.addScalar("deliveryOneName", new StringType());
		query.addScalar("deliveryOneAddress", new StringType());
		query.addScalar("deliveryOneRepresent", new StringType());
		query.addScalar("deliveryTwoName", new StringType());
		query.addScalar("deliveryTwoAddress", new StringType());
		query.addScalar("deliveryTwoRepresent", new StringType());
		query.addScalar("tranferName", new StringType());
		query.addScalar("tranferAddress", new StringType());
		query.addScalar("tranferRepresent", new StringType());
		
		query.addScalar("stockId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(StockDeliveryConfigDTO.class));
		
		return query.list();
	}
	
	public StockDeliveryConfigDTO getStockDeliveryConfig(Long idStock) {
		StringBuilder sql = new StringBuilder(
				"SELECT STC.DELIVERY_ID deliveryId, STC.DELIVERY_ONE_NAME deliveryOneName, STC.DELIVERY_ONE_ADDRESS deliveryOneAddress, STC.DELIVERY_ONE_REPRESENT deliveryOneRepresent, STC.DELIVERY_TWO_NAME deliveryTwoName, STC.DELIVERY_TWO_ADDRESS deliveryTwoAddress, STC.DELIVERY_TWO_REPRESENT deliveryTwoRepresent, STC.TRANFER_NAME tranferName, STC.TRANFER_ADDRESS tranferAddress, STC.TRANFER_REPRESENT tranferRepresent, STC.STOCK_ID stockId"
						+ " FROM CAT_OWNER.STOCK_DELIVERY_CONFIG STC INNER JOIN CAT_OWNER.STOCK ST ON (ST.STOCK_ID = STC.STOCK_ID) WHERE STC.STOCK_ID =:stockId ");


		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("deliveryId", new LongType());
		
		query.addScalar("deliveryOneName", new StringType());
		query.addScalar("deliveryOneAddress", new StringType());
		query.addScalar("deliveryOneRepresent", new StringType());
		query.addScalar("deliveryTwoName", new StringType());
		query.addScalar("deliveryTwoAddress", new StringType());
		query.addScalar("deliveryTwoRepresent", new StringType());
		query.addScalar("tranferName", new StringType());
		query.addScalar("tranferAddress", new StringType());
		query.addScalar("tranferRepresent", new StringType());
		
		query.addScalar("stockId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(StockDeliveryConfigDTO.class));
		query.setParameter("stockId", idStock);
		return (StockDeliveryConfigDTO) query.uniqueResult();
	}
}
