/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.ConstrEstimateInfoBO;
import com.viettel.erp.bo.EstimatesItemsChildBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.dto.ConstrEstimateInfoDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrEstimateInfoDAO")
public class ConstrEstimateInfoDAO extends BaseFWDAOImpl<ConstrEstimateInfoBO, Long> {

	public ConstrEstimateInfoDAO() {
		this.model = new ConstrEstimateInfoBO();
	}

	public ConstrEstimateInfoDAO(Session session) {
		this.session = session;
	}

	public List<ConstrEstimateInfoBO> getAllandSearch(ConstrEstimateInfoDTO obj) {

		Query q = getSession().createQuery(
				Joiner.on(" ").join("from constrestimateinfo a Join fetch a.constrconstructions b  where 1=1 ",
						obj.getConstructionId() != null ? " and b.constructId = :constructionId" : ""));
		if (obj.getConstructionId() != null) {
			q.setParameter("constructionId", obj.getConstructionId());
		}
		return q.list();
	}

	@Transactional
	public Integer DeleteEstimatesItemsChild(String code) throws Exception {
		Session session = getSession();
		String sql = "DELETE FROM ESTIMATES_ITEMS_CHILD WHERE ITEMS_CODE = :code";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("code", code);
		Integer id = query.executeUpdate();
		return id;
	}
}
