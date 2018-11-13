/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.ConstrEstimateInfoBO;
import com.viettel.erp.bo.EstimatesItemsChildBO;
import com.viettel.erp.dto.ConstrEstimateInfoDTO;
import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("estimatesItemsChildDAO")
public class EstimatesItemsChildDAO extends BaseFWDAOImpl<EstimatesItemsChildBO, Long> {

    public EstimatesItemsChildDAO() {
        this.model = new EstimatesItemsChildBO();
    }

    public EstimatesItemsChildDAO(Session session) {
        this.session = session;
    }
    
  //haibt 	
  	@SuppressWarnings("unchecked")
  	public List<EstimatesItemsChildDTO> getAllItemsInContruct(Long constructionId ){
  		String sql = "select EIC.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, "
  				+ "EIC.ITEM_NAME itemName from ESTIMATES_ITEMS_CHILD EIC "
  				+ "join CONSTR_ESTIMATE_INFO CEI on EIC.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
  				+ "join CONSTR_CONSTRUCTIONS CC on EIC.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
  				+ "WHERE EIC.CONSTRUCTION_ID = :constructionId";
  		StringBuilder sqlBuilder = new StringBuilder(sql);
  		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
  		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
  		query.addScalar("itemName", StringType.INSTANCE);
  		
  		
  		if (constructionId!=null) {
			  query.setParameter("constructionId", constructionId);
		}
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesItemsChildDTO.class));
  		return query.list();
  	}

	public List<EstimatesItemsChildDTO> getListEstimateItemschild(EstimatesItemsChildDTO rightDTO) {
		String sql = "select EIC.ESTIMATES_ITEM_CHILD_ID itemId, "
  				+ "EIC.ITEM_NAME itemName, "
  				+ "CEI.CONSTRUCTION_ID  constructId "
  				+ "from ESTIMATES_ITEMS_CHILD EIC "
  				+ "join CONSTR_ESTIMATE_INFO CEI on EIC.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID "
  				+ "join CONSTR_CONSTRUCTIONS CC on CEI.CONSTRUCTION_ID = CC.CONSTRUCT_ID "
  				+ "WHERE CEI.CONSTRUCTION_ID = :constructionId";
  		StringBuilder sqlBuilder = new StringBuilder(sql);
  		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());  		
  		query.addScalar("itemId", LongType.INSTANCE);
  		query.addScalar("itemName", StringType.INSTANCE);
  		query.setParameter("constructionId", rightDTO.getConstructId());
  		query.setResultTransformer(Transformers.aliasToBean(EstimatesItemsChildDTO.class));
  		return query.list();
	}
	
	
	
	public List<EstimatesItemsChildBO> getAllandSearch(EstimatesItemsChildDTO obj) {

		Query q = getSession().createQuery(Joiner.on(" ").join("from estimatesitemschild a join fetch a.constrestimateinfo b join fetch b.constrconstructions c where 1=1 ",
				obj.getConstructId() !=null ? " and c.constructId = :constructId" : ""));		
		if (obj.getConstructId()!= null) {
			q.setParameter("constructId",  obj.getConstructId());
		}	
		return q.list();
	} 
	
	
	
	
    @Transactional
    public Integer DeleteEstimatesItemsChild(Long idic) throws Exception{
    	      Session session = getSession();
    	            	StringBuilder sql = new StringBuilder();
    	                sql.append("DELETE FROM ESTIMATES_ITEMS_CHILD");
    	                sql.append(" WHERE ");
    	                sql.append("ESTIMATES_ITEM_CHILD_ID = " +idic );
    	                
    	                
    	         Integer id = session.createSQLQuery(sql.toString()).executeUpdate();
    	         
    	         
    	      return id;
    }
	
	
	
}
