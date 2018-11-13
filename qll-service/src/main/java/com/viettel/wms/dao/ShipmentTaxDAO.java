/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ShipmentTaxBO;
import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
import com.viettel.wms.dto.ShipmentTaxDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
@Repository("shipmentTaxDAO")
public class ShipmentTaxDAO extends BaseFWDAOImpl<ShipmentTaxBO, Long> {

    public ShipmentTaxDAO() {
        this.model = new ShipmentTaxBO();
    }

    public ShipmentTaxDAO(Session session) {
        this.session = session;
    }

	@SuppressWarnings("unchecked")
	public List<ShipmentTaxDTO> doSearchTAX(ShipmentTaxDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT WOST.VALUE valueshipmenttax,"
				+ "COT.NAME name,"
				+ "COT.VALUE valuetax,"
				+ "COT.TYPE type,"
				+ "COT.IGNORE ignore,"
				+ "COT.STATUS status,"
				+ "COT.APPLY apply "
				+ " FROM WMS_OWNER_KTTS.SHIPMENT_TAX WOST "
				+ " LEFT JOIN CAT_OWNER.TAX COT ON WOST.TAX_ID = COT.TAX_ID "
				+ " WHERE 1=1");

		if (obj.getShipmentId() != null) {
			sql.append(" AND WOST.SHIPMENT_ID = :shipmentId");
		}
		
		sql.append(" ORDER BY COT.NAME");

		SQLQuery query = getSession().createSQLQuery(sql.toString());


		query.addScalar("valueshipmenttax",new LongType());
		query.addScalar("name",new StringType());
		query.addScalar("valuetax",new DoubleType());
		query.addScalar("type",new StringType());
		query.addScalar("ignore",new StringType());
		query.addScalar("apply",new StringType());
		query.addScalar("status",new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ShipmentTaxDTO.class));

		if (obj.getShipmentId() != null) {
			query.setParameter("shipmentId", obj.getShipmentId());
		}
		

		return query.list();
	}
	
	
}
