/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.EstimatesDetailAnalystBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("estimatesDetailAnalystDAO")
public class EstimatesDetailAnalystDAO extends BaseFWDAOImpl<EstimatesDetailAnalystBO, Long> {

    public EstimatesDetailAnalystDAO() {
        this.model = new EstimatesDetailAnalystBO();
    }

    public EstimatesDetailAnalystDAO(Session session) {
        this.session = session;
    }
    // start here
    @SuppressWarnings("unchecked")
    public List<EstimatesDetailAnalystDTO> getAcceptanceList() {
    	StringBuffer sql =new StringBuffer(
    			"SELECT "
    			+"eda.COST_INGREDIENT_CODE costIngredientCode,"
    			+"eda.COST_INGREDIENT_NAME costIngredientName,"
    			+"eda.UNIT unit,"
    			+"sum(eda.NORM_INDEX) normIndex "
    			+"FROM " 
    			+"ESTIMATES_DETAIL_ANALYST eda "
    			+"JOIN ESTIMATES_WORK_ITEMS ewi " 
    			+"ON eda.ESTIMATES_WORK_ITEM_ID = ewi.ESTIMATES_WORK_ITEM_ID "
    			+"JOIN CONSTR_ESTIMATE_INFO cei " 
    			+"ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID " 
    			+"JOIN CONSTR_CONSTRUCTIONS cc "
    			+"ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
    			+"WHERE "
    			+"eda.TYPE = 1 AND "
    			+"eda.COST_INGREDIENT_CODE != 'VL' AND "
    			+"eda.PROVIDED_GROUP = 2 AND "
    			+"eda.PROGRESS_TYPE = 2 AND "
    			+"ewi.PROGRESS_TYPE = 3  "
    			+"group by "
    			+"eda.COST_INGREDIENT_CODE, "
    			+"eda.COST_INGREDIENT_NAME, "
    			+"eda.UNIT" 
    	);
        SQLQuery query = getSession().createSQLQuery(sql.toString());
             
        query.addScalar("costIngredientCode", StringType.INSTANCE);
        query.addScalar("costIngredientName", StringType.INSTANCE);
        query.addScalar("unit", StringType.INSTANCE);
        query.addScalar("normIndex", LongType.INSTANCE);       
        
     query.setResultTransformer(Transformers.aliasToBean(EstimatesDetailAnalystDTO.class));
        
     List<EstimatesDetailAnalystDTO> list =  query.list();
     return list;
    }
    
   
    
    public List<EstimatesDetailAnalystDTO> getAcceptanceListById(Long constructId) {
    	System.out.println("=========");
    	StringBuffer sql =new StringBuffer(
    	"SELECT "	
    	+"eda.COST_INGREDIENT_CODE costIngredientCode,"
    	+"eda.COST_INGREDIENT_NAME costIngredientName,"
    	+"eda.UNIT unit,"
    	+"sum(eda.NORM_INDEX) normIndex "
    	+"FROM "
    	+"ESTIMATES_DETAIL_ANALYST eda "
    	+"JOIN ESTIMATES_WORK_ITEMS ewi "
    	+"ON eda.ESTIMATES_WORK_ITEM_ID = ewi.ESTIMATES_WORK_ITEM_ID "
    	+"JOIN CONSTR_ESTIMATE_INFO cei "
    	+"ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
    	+"JOIN CONSTR_CONSTRUCTIONS cc "
    	+"ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
    	+"WHERE "
    	+"eda.TYPE = 1 AND "
    	+"eda.COST_INGREDIENT_CODE != 'VL' AND "
    	+"eda.PROVIDED_GROUP = 2 AND "
    	+"eda.PROGRESS_TYPE = 2 AND "
    	+"ewi.PROGRESS_TYPE = 3 AND "
    	+"cc.CONSTRUCT_ID = :constructId "
    	+"group by eda.COST_INGREDIENT_CODE, "
    	+"eda.COST_INGREDIENT_NAME, "
    	+"eda.UNIT"
    	);
        SQLQuery query = getSession().createSQLQuery(sql.toString());
//        query.addScalar("constructId", LongType.INSTANCE);

        query.addScalar("costIngredientCode", StringType.INSTANCE);
        query.addScalar("costIngredientName", StringType.INSTANCE);
        query.addScalar("unit", StringType.INSTANCE);
        query.addScalar("normIndex", new DoubleType());    
        query.setParameter("constructId", constructId);
        
     query.setResultTransformer(Transformers.aliasToBean(EstimatesDetailAnalystDTO.class));
        
     List<EstimatesDetailAnalystDTO> list =  query.list();
     return list;
    }
    
    //----popup tim kiem  vat tu b cap trong HD - phan nay dang viet sai---
	@SuppressWarnings("unchecked")
	public List<EstimatesDetailAnalystDTO> doSearchEstimatesDetailAnalyst(EstimatesDetailAnalystDTO criteria) {
		String sql = "SELECT " 
				+ "eda.COST_INGREDIENT_CODE costIngredientCode, " 
				+ "eda.COST_INGREDIENT_NAME costIngredientName, " 
				+ "eda.UNIT unit, " 
				+ "sum(eda.NORM_INDEX) normIndex " 
				+ "FROM "
				+ "ESTIMATES_DETAIL_ANALYST eda  " 
				+ "JOIN ESTIMATES_WORK_ITEMS ewi  " 
				+ "ON eda.ESTIMATES_WORK_ITEM_ID = ewi.ESTIMATES_WORK_ITEM_ID "
				+ "JOIN CONSTR_ESTIMATE_INFO cei  "
				+ "ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID  "
				+ "JOIN CONSTR_CONSTRUCTIONS cc " 
				+ "ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
				+ "WHERE "
				+ "eda.TYPE = 1 AND eda.COST_INGREDIENT_CODE != 'VL' "
				+ "AND eda.PROVIDED_GROUP = 2 AND	eda.PROGRESS_TYPE = 2 "
				+ "AND ewi.PROGRESS_TYPE = 3 AND cc.CONSTRUCT_ID = :constructId ";

		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (StringUtils.isNotEmpty(criteria.getCostIngredientName())) {
    		sqlBuilder.append(" AND LOWER(eda.COST_INGREDIENT_NAME) LIKE :costIngredientName");
    	}
		sqlBuilder.append(" group by eda.COST_INGREDIENT_CODE, eda.COST_INGREDIENT_NAME, eda.UNIT");
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("costIngredientCode", new StringType());
		query.addScalar("costIngredientName", new StringType());
		query.addScalar("unit", new StringType());
		query.addScalar("normIndex", new DoubleType());
		query.setParameter("constructId", criteria.getConstructId());
		if (StringUtils.isNotEmpty(criteria.getCostIngredientName())) {
			query.setParameter("", "%"+criteria.getCostIngredientName().toLowerCase()+"%");
    	}
		query.setResultTransformer(Transformers.aliasToBean(EstimatesDetailAnalystDTO.class));
		return query.list();
	}
    
    
    @SuppressWarnings("unchecked")
    public String autoGenCode(String tableName, String value) {
     SQLQuery query = getSession().createSQLQuery(
       Joiner.on("").join("select get_next_code('", tableName, "', 'CODE','", value, "',6) code from dual"));
     query.addScalar("code", StandardBasicTypes.STRING);

     return (String) query.uniqueResult();
    }
    
    
    public List<EstimatesDetailAnalystBO> getEstimatesDetailAnalystLst(EstimatesDetailAnalystDTO obj) {

		Query q = getSession().createQuery(Joiner.on(" ").join("from estimatesDetailAnalysts e where 1=1  ",
				obj.getEstimatesWorkItemId() !=null ? " and e.estimatesWorkItems.estimatesWorkItemId = :estimatesWorkItemId" : ""));

		
		if (obj.getEstimatesWorkItemId()!= null) {
			q.setParameter("estimatesWorkItemId",  obj.getEstimatesWorkItemId());
		}
		return q.list();
	}
    
    
}
