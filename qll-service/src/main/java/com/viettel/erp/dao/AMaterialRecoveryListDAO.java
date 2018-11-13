/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.AMaterialRecoveryListBO;
import com.viettel.erp.bo.AMaterialRecoveryMinutesBO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

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
@Repository("aMaterialRecoveryListDAO")
public class AMaterialRecoveryListDAO extends BaseFWDAOImpl<AMaterialRecoveryListBO, Long> {

	public AMaterialRecoveryListDAO() {
		this.model = new AMaterialRecoveryListBO();
	}

	public AMaterialRecoveryListDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<AMaterialRecoveryListModelDTO> findByConstructId(Long constructId) {
		StringBuffer sql = new StringBuffer(
				"select d.mer_entity_id merEntityId,c.CONSTRUCT_ID constructId, e.NAME as merName, d.SERIAL_NUMBER serialNumber, f.NAME as unitId,"
						+ "a.ACTUAL_RECEIVE_QUANTITY as actualReceiveQuantity,g.QUANTITY as quantity "
						+ "from A_MATERIAL_HANDOVER_MER_LIST a join A_MATERIAL_HANDOVER b "
						+ "on (a.A_MATERIAL_HANDOVER_ID = b.A_MATERIAL_HANDOVER_ID) "
						+ "join CONSTR_CONSTRUCTIONS c on (b.CONSTRUCT_ID = c.CONSTRUCT_ID) "
						+ "join mer_entity d on (a.mer_entity_id = d.mer_entity_id) "
						+ "join cat_merchandise e on (d.mer_id = e.merchandise_id) "
						+ "join cat_unit f on (e.unit_id = f.unit_id) "
						+ "left join constr_merchandise g on (c.CONSTRUCT_ID = g.CONSTRUCT_ID and a.mer_entity_id = g.mer_entity_id) "
						+ "where c.CONSTRUCT_ID = :constructId AND (a.ACTUAL_RECEIVE_QUANTITY is not null and a.ACTUAL_RECEIVE_QUANTITY <>0) "
						+ "and(a.ACTUAL_RECEIVE_QUANTITY > g.QUANTITY or g.QUANTITY is null)");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.addScalar("merName", StringType.INSTANCE);
		query.addScalar("serialNumber", StringType.INSTANCE);
		query.addScalar("unitId", StringType.INSTANCE);
		query.addScalar("actualReceiveQuantity", LongType.INSTANCE);
		query.addScalar("quantity", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);

		query.setParameter("constructId", constructId);
		query.setResultTransformer(Transformers.aliasToBean(AMaterialRecoveryListModelDTO.class));

		List<AMaterialRecoveryListModelDTO> list = query.list();
		return list;

	}

}
