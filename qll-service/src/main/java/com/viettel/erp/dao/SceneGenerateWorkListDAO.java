/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.SceneGenerateWorkBO;
import com.viettel.erp.bo.SceneGenerateWorkListBO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("sceneGenerateWorkListDAO")
public class SceneGenerateWorkListDAO extends BaseFWDAOImpl<SceneGenerateWorkListBO, Long> {

	public SceneGenerateWorkListDAO() {
		this.model = new SceneGenerateWorkListBO();
	}

	public SceneGenerateWorkListDAO(Session session) {
		this.session = session;
	}

	// begin phongpv
	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> doSearchSceneGenerateWork(EstimatesWorkItemsDTO criteria) {
		String sql = "SELECT " 
				+ "EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, " 
				+ "EWI.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " 
				+ "EWI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfoId, " 
				+ "EWI.PROGRESS_TYPE progressType, " 
				+ "EIC.ITEMS_CODE itemsCode, "
				+ "EIC.ITEM_NAME itemName, " 
				+ "EWI.WORK_ITEM_CODE workItemCode, " 
				+ "EWI.WORK_ITEM_NAME workItemName, "
				+ "EWI.TYPE type, "
				+ "EWI.STATUS status, "
				+ "cc.CONSTRUCT_ID constructionId " 
				+ "from ESTIMATES_WORK_ITEMS ewi JOIN CONSTR_ESTIMATE_INFO cei "
				+ "ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
				+ "LEFT JOIN CONSTR_CONSTRUCTIONS cc "
				+ "ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
				+ "LEFT JOIN ESTIMATES_ITEMS_CHILD eic ON eic.ESTIMATES_ITEM_CHILD_ID = ewi.ESTIMATES_ITEM_CHILD_ID "
				+ "WHERE ((ewi.PROGRESS_TYPE = 3 AND ewi.TYPE != 2) OR (ewi.TYPE = 2 AND ewi.WORK_TYPE = 2)) AND ewi.STATUS NOT IN (1,2) AND cc.CONSTRUCT_ID = :constructId" ;

		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (criteria.getEstimatesWorkItemId() != null) {
			sqlBuilder.append(" AND EWI.ESTIMATES_WORK_ITEM_ID = :estimatesWorkItemId ");
		}
		if (StringUtils.isNotEmpty(criteria.getItemsCode())) {
    		sqlBuilder.append(" AND LOWER(EIC.ITEMS_CODE) LIKE '%"+ criteria.getItemsCode().toLowerCase() +"%' ");
    	}
		if (StringUtils.isNotEmpty(criteria.getItemName())) {
    		sqlBuilder.append(" AND LOWER(EIC.ITEM_NAME) LIKE '%"+ criteria.getItemName().toLowerCase() +"%' ");
    	}
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
		query.addScalar("progressType", LongType.INSTANCE);
		query.addScalar("itemsCode", StringType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("status", LongType.INSTANCE);
		query.addScalar("constructionId", LongType.INSTANCE);
		query.setParameter("constructId", criteria.getConstructionId());
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		if (criteria.getEstimatesWorkItemId() != null) {
			query.setParameter("estimatesWorkItemId", criteria.getEstimatesWorkItemId());
		}
		return query.list();
	}

	
	
	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> getAllSceneGenerateWork(Long constructId) {
		String sql = "SELECT " 
				+ "EWI.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, " 
				+ "EWI.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " 
				+ "EWI.CONSTR_ESTIMATE_INFO_ID constrEstimateInfoId, " 
				+ "EWI.PROGRESS_TYPE progressType, " 
				+ "EIC.ITEMS_CODE itemsCode, "
				+ "EIC.ITEM_NAME itemName, "
				+ "EWI.WORK_ITEM_CODE workItemCode, "
				+ "EWI.WORK_ITEM_NAME workItemName, "
				+ "EWI.TYPE type, "
				+ "EWI.STATUS status, "
				+ "cc.CONSTRUCT_ID constructionId " 
				+ "from ESTIMATES_WORK_ITEMS ewi "
				+ "JOIN CONSTR_ESTIMATE_INFO cei "
				+ "ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
				+ "JOIN CONSTR_CONSTRUCTIONS cc "
				+ "ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
				+ "LEFT JOIN ESTIMATES_ITEMS_CHILD eic ON eic.ESTIMATES_ITEM_CHILD_ID = ewi.ESTIMATES_ITEM_CHILD_ID "
				+ "WHERE ((ewi.PROGRESS_TYPE = 3 AND ewi.TYPE != 2) OR (ewi.TYPE = 2 AND ewi.WORK_TYPE = 2)) AND ewi.STATUS NOT IN (1,2) AND cc.CONSTRUCT_ID = :id" ;
		StringBuilder sqlBuilder = new StringBuilder(sql);
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constrEstimateInfoId", LongType.INSTANCE);
		query.addScalar("progressType", LongType.INSTANCE);
		query.addScalar("itemsCode", StringType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("status", LongType.INSTANCE);
		query.addScalar("constructionId", LongType.INSTANCE);
		query.setParameter("id", constructId);
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		return query.list();
	}

	// end phongpv
	// tungpv
	@SuppressWarnings("unchecked")
	public List<SceneGenerateWorkListDTO> doSearchSceneGenerateWorkList(SceneGenerateWorkDTO criteria) {
		String sql = "SELECT " + " SGWL.SCENE_GEN_WORK_LIST_ID sceneGenWorkListId, "
				+ " SGWL.INCURRED_CONTENT incurredContent, " + " SGWL.UNIT unit, "
				+ " SGWL.TYPE type, "
				+ " SGWL.INCURRED_QUANTITY incurredQuantity, " + " SGWL.DESIGN_QUANTITY designQuantity, "
				+ " SGWL.NOTE note, " + " SGWL.WORK_ITEM_TYPE workItemType, "
				+ " SGWL.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " + " SGWL.STATUS status, "
				+ " ESTIMATES_ITEMS_CHILD.ITEM_NAME itemName"
				+ " FROM SCENE_GENERATE_WORK_LIST SGWL "
				+ " JOIN ESTIMATES_ITEMS_CHILD ON SGWL.ESTIMATES_ITEM_CHILD_ID = ESTIMATES_ITEMS_CHILD.ESTIMATES_ITEM_CHILD_ID ";

		StringBuilder sqlBuilder = new StringBuilder(sql);

		if (criteria.getSceneGenerateWorkId() != null) {
			sqlBuilder.append(" WHERE SGWL.SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId ");
		}
		sqlBuilder.append(" ORDER BY ESTIMATES_ITEMS_CHILD.ESTIMATES_ITEM_CHILD_ID DESC ");
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("sceneGenWorkListId", LongType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("incurredContent", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("incurredQuantity", DoubleType.INSTANCE);
		query.addScalar("designQuantity", DoubleType.INSTANCE);
		query.addScalar("note", StringType.INSTANCE);
		query.addScalar("workItemType", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("status", LongType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(SceneGenerateWorkListDTO.class));

		if (criteria.getSceneGenerateWorkId() != null) {
			query.setParameter("sceneGenerateWorkId", criteria.getSceneGenerateWorkId());
		}
		// query.setMaxResults(100);

		return query.list();
	}
	// end tungpv

	public SceneGenerateWorkDTO doCRUD(SceneGenerateWorkDTO dto) {
		Session session = getSession();
		SceneGenerateWorkBO bo = dto.toModel();
		session.saveOrUpdate(bo);
		return bo.toDTO();
	}

}
