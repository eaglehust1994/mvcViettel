/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.AbDetailPriceBO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.AbDetailPriceDTO;
import com.viettel.erp.dto.AbDetailPriceNewDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
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
@Repository("abDetailPriceDAO")
public class AbDetailPriceDAO extends BaseFWDAOImpl<AbDetailPriceBO, Long> {

    public AbDetailPriceDAO() {
        this.model = new AbDetailPriceBO();
    }

    public AbDetailPriceDAO(Session session) {
        this.session = session;
    }

	public AbDetailPriceDTO getById(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "ABC.CONSTRUCT_ID constructId,"
 				+ "ABC.AB_DETAIL_PRICE_ID abDetailPriceId, "
 				+ "ABC.CODE code, "
 				+ "ABC.CREATED_USER_ID createdUserId "
 				+ "FROM AB_DETAIL_PRICE ABC " 				
 				+ "WHERE ABC.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 
 		query.addScalar("code", StringType.INSTANCE);
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("abDetailPriceId", LongType.INSTANCE);
 		query.addScalar("createdUserId", LongType.INSTANCE);
 		query.setParameter("constructId", constructId);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbDetailPriceDTO.class));

 		return (AbDetailPriceDTO) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
    public String autoGenCode() {
     StringBuffer sql = new StringBuffer("select get_next_code('AB_DETAIL_PRICE', 'CODE','QT_AB_5',6) code from dual");
     SQLQuery query = getSession().createSQLQuery(sql.toString());
     query.addScalar("code", StandardBasicTypes.STRING);
     return (String) query.uniqueResult();
    }
	
	@Transactional
    public Long saveTable( AbDetailPriceDTO  completionDrawing) throws Exception{
           Session session = getSession();
           Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
           completionDrawing.getConstrCompleteRecordsMap().setDataTableIdValue(completionDrawingId);     
           session.save(completionDrawing.getConstrCompleteRecordsMap().toModel());
           return completionDrawingId;
     }

	public AbDetailPriceDTO getThongtinchung(AbDetailPriceDTO dto) {
	    	StringBuilder sqlbuilder = new StringBuilder("SELECT "
	 				+ "V_CONSTRUCTION_HCQT.CONSTRT_NAME constructName, "
	 				+ "V_CONSTRUCTION_HCQT.STATION_CODE stationcode, "
	 				+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constructAddress, "
	 				+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode,"
	 				+ " AB.A_DIRECTOR_ID adirectorId, "
	 				+ " AB.B_DIRECTOR_ID bdirectorId, "
	 				+ " AB.CODE code,"
	 				+ " AB.STATUS_CA statusCa "
	 				+ "FROM V_CONSTRUCTION_HCQT "
	 				+ "INNER JOIN AB_DETAIL_PRICE AB ON V_CONSTRUCTION_HCQT.CONSTRUCT_ID = AB.CONSTRUCT_ID "
	 				+ "WHERE V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
	 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
	 		query.addScalar("constructName", StringType.INSTANCE);
	 		query.addScalar("stationcode", StringType.INSTANCE);
	 		query.addScalar("constructAddress", StringType.INSTANCE);
	 		query.addScalar("contractCode", StringType.INSTANCE);
	 		query.addScalar("code", StringType.INSTANCE);
	 		query.addScalar("adirectorId", LongType.INSTANCE);
	 		query.addScalar("bdirectorId", LongType.INSTANCE);
	 		query.addScalar("statusCa", LongType.INSTANCE);
	 		
	 		query.setParameter("constructId",  dto.getConstructId());
	 		
	 		query.setResultTransformer(Transformers.aliasToBean(AbDetailPriceDTO.class));

	 		return (AbDetailPriceDTO) query.uniqueResult();
	}

	public AbDetailPriceDTO CheckEstimate5(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
				//+ "ABC.CODE code, "				
 				+ "ABC.A_DIRECTOR_ID adirectorId, "
 				+ "ABC.B_DIRECTOR_ID bdirectorId, " 				
 				+ "ABC.STATUS_CA statusCa, "
 				+ "ABC.CODE code "				
 				+ "FROM AB_DETAIL_PRICE ABC " 						
 				+ "WHERE ABC.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("adirectorId", new LongType());
 		query.addScalar("bdirectorId", new LongType()); 		
 		query.addScalar("statusCa", new LongType()); 		
 		query.addScalar("code", new StringType()); 
 		
 		query.setParameter("constructId", constructId);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbDetailPriceDTO.class));

 		return (AbDetailPriceDTO) query.uniqueResult();
	}

	public AbDetailPriceDTO getAmonitorSingCa(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
				+ "ABC.CODE code, "	
				+ "ABC.CONSTRUCT_ID constructId, "
				+ "ABC.AB_DETAIL_PRICE_ID abDetailPriceId, "
				+ "JCC.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
 				+ "ABC.A_DIRECTOR_ID adirectorId, "
 				+ "ABC.B_DIRECTOR_ID bdirectorId " 						
 				+ "FROM AB_DETAIL_PRICE ABC "
 				+ "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP JCC ON ABC.AB_DETAIL_PRICE_ID = JCC.DATA_TABLE_ID_VALUE AND JCC.DATA_TABLE_NAME = 'AB_DETAIL_PRICE' " 				
 				+ "WHERE ABC.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("adirectorId", LongType.INSTANCE);
 		query.addScalar("bdirectorId", LongType.INSTANCE); 		
 		query.addScalar("constrCompReMapId", LongType.INSTANCE);
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("code", StringType.INSTANCE);
 		
 		query.setParameter("constructId", constructId);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbDetailPriceDTO.class));

 		return (AbDetailPriceDTO) query.uniqueResult();
	}

	public List<AbDetailPriceNewDTO> getExportBieu5(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("select x.*, y.* from (select a.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.WORK_ITEM_NAME workItemName2, "
  				+ "b.WORK_ITEM_CODE workItemCode2, " 
  				+ "a.COST_INGREDIENT_NAME costIngredientName2, " 
  				+ "a.UNIT unit2, "
  				+ "a.TYPE type2, "
  				+ "a.NORM_INDEX normIndex2, "
  				+ "a.UNIT_PRICE unitPrice2 , "
  				+ "a.COEFFICIENT coefficient2 , "
  				+ "a.TOTAL_MONEY totalMoney2, "
  				+ "a.COST_INGREDIENT_CODE "
  				+ "from ESTIMATES_DETAIL_ANALYST a JOIN ESTIMATES_WORK_ITEMS b ON a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID AND CEI.CONSTRUCTION_ID = :constructIdA"
  				
  				+ " where a.PROGRESS_TYPE = 2 order by a.EST_DETAIL_ANALYST_ID) x "
  				+ "left join (select a.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
  				+ "b.WORK_ITEM_NAME workItemName4, "
  				+ "b.WORK_ITEM_CODE workItemCode4, " 
  				+ "a.COST_INGREDIENT_NAME costIngredientName4, " 
  				+ "a.UNIT unit4, "
  				+ "a.TYPE type4, "
  				+ "a.NORM_INDEX normIndex4, "
  				+ "a.UNIT_PRICE unitPrice4 , "
  				+ "a.COEFFICIENT coefficient4 , "
  				+ "a.TOTAL_MONEY totalMoney4, "
  				+ "a.COST_INGREDIENT_CODE "
  				+ "from ESTIMATES_DETAIL_ANALYST a JOIN ESTIMATES_WORK_ITEMS b ON a.ESTIMATES_WORK_ITEM_ID = b.ESTIMATES_WORK_ITEM_ID "
  				+ "JOIN CONSTR_ESTIMATE_INFO CEI  ON b.CONSTR_ESTIMATE_INFO_ID = CEI.CONSTR_ESTIMATE_INFO_ID AND CEI.CONSTRUCTION_ID = :constructIdB"
  				+ " where a.PROGRESS_TYPE = 4 order by a.EST_DETAIL_ANALYST_ID) "
  				+ "y on (x.estimatesWorkItemId = y.estimatesWorkItemId AND x.TYPE2 = y.TYPE4 AND x.COST_INGREDIENT_CODE = y.COST_INGREDIENT_CODE) ");
  		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
  		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
  		query.addScalar("workItemCode2", StringType.INSTANCE);
  		query.addScalar("workItemName2", StringType.INSTANCE);
  		query.addScalar("unit2", StringType.INSTANCE);
  		query.addScalar("type2", LongType.INSTANCE); 
  		query.addScalar("unitPrice2", DoubleType.INSTANCE);
  		query.addScalar("normIndex2", DoubleType.INSTANCE);
  		query.addScalar("coefficient2", DoubleType.INSTANCE);
  		query.addScalar("totalMoney2", DoubleType.INSTANCE); 
  		query.addScalar("costIngredientName2", StringType.INSTANCE);
  		//Quyết toán
  		query.addScalar("workItemCode4", StringType.INSTANCE);
  		query.addScalar("workItemName4", StringType.INSTANCE);
  		query.addScalar("unit4", StringType.INSTANCE);  
  		query.addScalar("type4", LongType.INSTANCE);
  		query.addScalar("unitPrice4", DoubleType.INSTANCE);
  		query.addScalar("normIndex4", DoubleType.INSTANCE);
  		query.addScalar("coefficient4", DoubleType.INSTANCE);
  		query.addScalar("totalMoney4", DoubleType.INSTANCE);  		
  		query.addScalar("costIngredientName4", StringType.INSTANCE);
  		
  		query.setParameter("constructIdA", constructId);
  		query.setParameter("constructIdB", constructId);
  		
  		query.setResultTransformer(Transformers.aliasToBean(AbDetailPriceNewDTO.class));
  		
  		
  		List<AbDetailPriceNewDTO> a = query.list();
  		
  		return a;
	}

}
