/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.ConstrAcceptWorkListBO;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrAcceptWorkListDAO")
public class ConstrAcceptWorkListDAO extends BaseFWDAOImpl<ConstrAcceptWorkListBO, Long> {

    public ConstrAcceptWorkListDAO() {
        this.model = new ConstrAcceptWorkListBO();
    }

    public ConstrAcceptWorkListDAO(Session session) {
        this.session = session;
    }
    
    public List<ConstrAcceptWorkListDTO> getProposedSettlement(Long contractId){
    	StringBuilder sql = new StringBuilder("SELECT  C.TYPE typeEstimates"
    			+" ,SUM(NVL(A.EVALUATE_QUANTITY,0) *NVL(A.EVALUATE_UNIT_PRICE,0)) valueProposed"	
				+" FROM CONSTR_ACCEPT_WORK_LIST A"
				+" JOIN CONSTRUCTION_ACCEPTANCE B ON A.CONSTRUCTION_ACCEPTANCE_ID = B.CONSTRUCTION_ACCEPTANCE_ID "
				+" JOIN ESTIMATES_WORK_ITEMS C ON A.ESTIMATES_WORK_ITEM_ID = C.ESTIMATES_WORK_ITEM_ID"
				+" WHERE B.STATUS_CA = 2" );
		/*SELECT C.TYPE, SUM(A.EVALUATE_QUANTITY * A.EVALUATE_UNIT_PRICE) AS GIATRIQUYETTOAN FROM CONSTR_ACCEPT_WORK_LIST A JOIN CONSTRUCTION_ACCEPTANCE B ON (A.CONSTRUCTION_ACCEPTANCE_ID = B.CONSTRUCTION_ACCEPTANCE_ID)
    	JOIN ESTIMATES_WORK_ITEMS C ON (A.ESTIMATES_WORK_ITEM_ID = C.ESTIMATES_WORK_ITEM_ID)
    	WHERE B.CONSTRUCT_ID = 69164445 AND B.STATUS_CA = 2
    	GROUP BY C.TYPE*/
    	
    	if (contractId != null) {
    		sql.append(" AND B.CONSTRUCT_ID = :contractId");
		}
    	sql.append(" GROUP BY C.TYPE ");
    	
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("valueProposed", LongType.INSTANCE);
//		query.addScalar("constrAcceptWorkListId", LongType.INSTANCE);
		query.addScalar("typeEstimates", LongType.INSTANCE);
		if (contractId != null) {
    		query.setParameter("contractId", contractId);
		}
		query.setResultTransformer(Transformers.aliasToBean(ConstrAcceptWorkListDTO.class));
		
		return query.list();
    }
    
    public List<ConstrAcceptWorkListDTO> getAllbyConstructId(Long constructId) {
		StringBuffer sql = new StringBuffer("SELECT " 
				+" CG.CONSTR_ACCEPT_WORK_LIST_ID constrAcceptWorkListId,"
				+" CG.EXPLAIN explain,"
				+" CG.EXECUTE_QUANTITY executeQuantity, "
				+" CG.EVALUATE_QUANTITY evaluateQuantity,"
				+" CG.EVALUATE_COMMENTS evaluateComments,"
				+" CG.INSTANCE_DRAW_CODE instanceDrawCode, "
				+" CG.COMMENTS comments,"
				+" CG.CONSTRUCTION_ACCEPTANCE_ID constructionAcceptanceId,"
				+" CG.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId,"
				+" CG.SETTLE_UNIT_PRICE settleUnitPrice,"
				+" CG.EVALUATE_UNIT_PRICE evaluateUnitPrice"
				+ " FROM CONSTR_ACCEPT_WORK_LIST CG "
				+ " INNER JOIN CONSTRUCTION_ACCEPTANCE CA ON CA.CONSTRUCTION_ACCEPTANCE_ID=CG.CONSTRUCTION_ACCEPTANCE_ID"
				+ " WHERE  CA.CONSTRUCT_ID =:constructId");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("constrAcceptWorkListId", LongType.INSTANCE);
		query.addScalar("explain", StringType.INSTANCE);
		query.addScalar("executeQuantity", new DoubleType());
		query.addScalar("evaluateQuantity", new DoubleType());
		query.addScalar("evaluateComments", StringType.INSTANCE);
		query.addScalar("instanceDrawCode", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("constructionAcceptanceId", LongType.INSTANCE);
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("settleUnitPrice", new DoubleType());
		query.addScalar("evaluateUnitPrice", new DoubleType());
		query.setParameter("constructId", constructId);
		query.setResultTransformer(Transformers.aliasToBean(ConstrAcceptWorkListDTO.class));
		
		List<ConstrAcceptWorkListDTO> list = query.list();
		return list;
	}
    
}
