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

import com.viettel.erp.bo.ConstrWorkCompConfListBO;
import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrWorkCompConfListDAO")
public class ConstrWorkCompConfListDAO extends BaseFWDAOImpl<ConstrWorkCompConfListBO, Long> {

	public ConstrWorkCompConfListDAO() {
		this.model = new ConstrWorkCompConfListBO();
	}

	public ConstrWorkCompConfListDAO(Session session) {
		this.session = session;
	}

	public List<ConstrWorkCompConfListLessDTO> getListConstrWorkByConstrId(Long id) {

		String sql = "SELECT ewi.WORK_ITEM_NAME workItemName,ewi.UNIT unit,ewi.WORK_AMOUNT workAmount,ewi.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId "
				+ "FROM ESTIMATES_WORK_ITEMS ewi " + "JOIN CONSTR_ESTIMATE_INFO cei "
				+ "ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID " + "JOIN CONSTR_CONSTRUCTIONS cc "
				+ "ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID  "
				+ "WHERE ewi.PROGRESS_TYPE = 3 AND ewi.STATUS = 1 AND cc.CONSTRUCT_ID = :id";

		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("workAmount", LongType.INSTANCE);
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(ConstrWorkCompConfListLessDTO.class));

		query.setParameter("id", id);

		return query.list();
	}

	public List<ConstrWorkCompConfListLessDTO> getListConstrWorkExistByConstrId(List<Long> Listid) {

		String sql = "SELECT ewi.WORK_ITEM_NAME workItemName,ewi.UNIT unit,ewi.WORK_AMOUNT workAmount,ewi.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId,cwl.EXECUTE_QUANTITY executeQuantity,cwl.COMMENTS comments,cwl.CONSTR_WORK_COMP_LIST_ID constrWorkCompListId "
				+ " FROM ESTIMATES_WORK_ITEMS ewi " + " JOIN CONSTR_ESTIMATE_INFO cei "
				+ " ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID " + " JOIN CONSTR_CONSTRUCTIONS cc "
				+ " ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID " + " JOIN CONSTR_WORK_COMP_CONF_LIST cwl "
				+ " ON ewi.ESTIMATES_WORK_ITEM_ID = cwl.ESTIMATES_WORK_ITEM_ID "
				+ " WHERE ewi.PROGRESS_TYPE = 3 AND ewi.STATUS = 1 AND cc.CONSTRUCT_ID = :constrId AND cwl.CONSTR_WORK_COMP_CONFIRM_ID = :cWccid ";

		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("constrWorkCompListId", LongType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("workAmount", DoubleType.INSTANCE);
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("executeQuantity", DoubleType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConstrWorkCompConfListLessDTO.class));

		query.setParameter("constrId", Listid.get(0));
		query.setParameter("cWccid", Listid.get(1));
		

		return query.list();
	}
}
