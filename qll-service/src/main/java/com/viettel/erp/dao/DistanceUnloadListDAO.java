/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.DistanceUnloadListBO;
import com.viettel.erp.dto.DistanceUnloadListDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("distanceUnloadListDAO")
public class DistanceUnloadListDAO extends BaseFWDAOImpl<DistanceUnloadListBO, Long> {

    public DistanceUnloadListDAO() {
        this.model = new DistanceUnloadListBO();
    }

    public DistanceUnloadListDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<DistanceUnloadListDTO> doSearchByDisUnloadConsMinId(Long disUnloadConsMinId) {
		String sql = "SELECT "
				+ "DUL.MATERIAL_TYPE materialType, "
				+ "DUL.UNIT unit, "
				+ "DUL.QUANTITY quantity, "
				+ "DUL.TRANSPORT_DISTANCE transportDistance, "
				+ "DUL.ROAD_TYPE roadType, "
				+ "DUL.ROAD_TYPE_STR roadTypeStr, "
				+ "DUL.SLOPE_DEGREE slopeDegree, "
				+ "DUL.TRUCK_LOAD truckLoad, "
				+ "DUL.NOTE note, "
				+ "DUL.TRANSPORT_TYPE transportType, "
				+ "DUL.DIS_UNLOAD_CONS_MIN_ID disUnloadConsMinId "
				+ "FROM DISTANCE_UNLOAD_LIST DUL JOIN DISTANCE_UNLOAD_CONSTR_MINUTES DUCM "
				+ "ON DUCM.DIS_UNLOAD_CONS_MIN_ID = DUL.DIS_UNLOAD_CONS_MIN_ID "
				;
				

		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (disUnloadConsMinId != null) {
			sqlBuilder.append(" and DUL.DIS_UNLOAD_CONS_MIN_ID "
					+ "in (select DIS_UNLOAD_CONS_MIN_ID from DISTANCE_UNLOAD_LIST "
					+ "where DUL.DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId)");
		}
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("materialType", new StringType());
		query.addScalar("unit", new StringType());
		query.addScalar("quantity", new DoubleType());
		query.addScalar("transportDistance", new DoubleType());
		query.addScalar("roadType", new LongType());
		query.addScalar("slopeDegree", new StringType());
		query.addScalar("truckLoad", new DoubleType());
		query.addScalar("note", new StringType());
		query.addScalar("transportType", new LongType());
		query.addScalar("roadTypeStr", new StringType());
		query.addScalar("disUnloadConsMinId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(DistanceUnloadListDTO.class));
		if (disUnloadConsMinId != null) {
			query.setParameter("disUnloadConsMinId", disUnloadConsMinId);
		}
		return query.list();
	}
}
