/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.AbComplementWorkBO;

import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;

import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;

import com.viettel.service.base.dao.BaseFWDAOImpl;


import javax.transaction.Transactional;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;


import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("abComplementWorkDAO")
public class AbComplementWorkDAO extends BaseFWDAOImpl<AbComplementWorkBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

    public AbComplementWorkDAO() {
        this.model = new AbComplementWorkBO();
    }

    public AbComplementWorkDAO(Session session) {
        this.session = session;
    }

    @Transactional
    public Long saveTable( AbComplementWorkDTO  completionDrawing) throws Exception{
           Session session = getSession();
           Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
           completionDrawing.getConstrCompleteRecordsMap().setDataTableIdValue(completionDrawingId);     
           session.save(completionDrawing.getConstrCompleteRecordsMap().toModel());
           return completionDrawingId;
     }    
  

    
    public AbComplementWorkDTO getThongtinchung(Long id) {
    	StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "V_CONSTRUCTION_HCQT.CONSTRT_NAME constructName, "
 				+ "V_CONSTRUCTION_HCQT.STATION_CODE stationcode, "
 				+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constructAddress, "
 				+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode,"
 				+ " AB.A_DIRECTOR_ID adirectorId, "
 				+ " AB.B_DIRECTOR_ID bdirectorId, "
 				+ " AB.STATUS_CA statusCa "
 				+ "FROM V_CONSTRUCTION_HCQT "
 				+ "INNER JOIN AB_COMPLEMENT_WORK AB ON V_CONSTRUCTION_HCQT.CONSTRUCT_ID = AB.CONSTRUCT_ID "
 				+ "WHERE V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :id AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
 		query.addScalar("constructName", StringType.INSTANCE);
 		query.addScalar("stationcode", StringType.INSTANCE);
 		query.addScalar("constructAddress", StringType.INSTANCE);
 		query.addScalar("contractCode", StringType.INSTANCE);
 		query.addScalar("adirectorId", LongType.INSTANCE);
 		query.addScalar("bdirectorId", LongType.INSTANCE);
 		query.addScalar("statusCa", LongType.INSTANCE);
 		
 		query.setParameter("id", id);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbComplementWorkDTO.class));

 		return (AbComplementWorkDTO) query.uniqueResult();
 	}
    
    public AbComplementWorkDTO getThongtinchungBieu2(Long id) {
    	StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "V_CONSTRUCTION_HCQT.CONSTRT_NAME constructName, "
 				+ "V_CONSTRUCTION_HCQT.STATION_CODE stationcode, "
 				+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constructAddress, "
 				+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode,"
 				+ " AB.A_DIRECTOR_ID adirectorId, "
 				+ " AB.B_DIRECTOR_ID bdirectorId, "
 				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT A_DIRECTOR_ID FROM AB_SETTLEMENT_WORK WHERE AB_SETTLEMENT_WORK_ID = "+id+")) adirectorPath,"
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT B_DIRECTOR_ID FROM AB_SETTLEMENT_WORK WHERE AB_SETTLEMENT_WORK_ID = "+id+")) bdirectorPath,"
 				+ " AB.STATUS_CA statusCa "
 				+ "FROM V_CONSTRUCTION_HCQT "
 				+ "INNER JOIN AB_SETTLEMENT_WORK AB ON V_CONSTRUCTION_HCQT.CONSTRUCT_ID = AB.CONSTRUCT_ID "
 				+ "WHERE V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :id AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
 		query.addScalar("constructName", StringType.INSTANCE);
 		query.addScalar("stationcode", StringType.INSTANCE);
 		query.addScalar("constructAddress", StringType.INSTANCE);
 		query.addScalar("contractCode", StringType.INSTANCE);
 		query.addScalar("adirectorId", LongType.INSTANCE);
 		query.addScalar("bdirectorId", LongType.INSTANCE);
 		query.addScalar("statusCa", LongType.INSTANCE);
 		query.addScalar("adirectorPath", StringType.INSTANCE);
 		query.addScalar("bdirectorPath", StringType.INSTANCE);
 		
 		query.setParameter("id", id);
 		query.setParameter("type2", partnerAttachType);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbComplementWorkDTO.class));

 		return (AbComplementWorkDTO) query.uniqueResult();
 	}

	public AbComplementWorkDTO getByIdCC(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
				+ "ABC.CREATED_USER_ID createdUserId, "
 				+ "ABC.CONSTRUCT_ID constructId, "
 				+ "ABC.CODE code, "
 				+ "ABC.AB_COMPLEMENT_WORK_ID abComplementWorkId " 				
 				+ "FROM AB_COMPLEMENT_WORK ABC " 				
 				+ "WHERE ABC.CONSTRUCT_ID = :constructId AND ROWNUM = 1" );
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());
 		query.addScalar("code", StringType.INSTANCE);
 		query.addScalar("createdUserId", LongType.INSTANCE);
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("abComplementWorkId", LongType.INSTANCE);
 		
 		query.setParameter("constructId", constructId);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbComplementWorkDTO.class));

 		return (AbComplementWorkDTO) query.uniqueResult();
	}

	public AbComplementWorkDTO getAmonitorSingCa(Long id) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
				+ "ABC.CODE code, "	
				+ "ABC.CONSTRUCT_ID constructId, "
				+ "ABC.AB_COMPLEMENT_WORK_ID abComplementWorkId, "
				+ "JCC.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
 				+ "ABC.A_DIRECTOR_ID adirectorId, "
 				+ "ABC.B_DIRECTOR_ID bdirectorId, "
 				+ "ABC.C_DESIGN_DIRECTION_ID cdesignDirectionId " 				
 				+ "FROM AB_COMPLEMENT_WORK ABC "
 				+ "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP JCC ON ABC.AB_COMPLEMENT_WORK_ID = JCC.DATA_TABLE_ID_VALUE AND JCC.DATA_TABLE_NAME = 'AB_COMPLEMENT_WORK' " 				
 				+ "WHERE ABC.CONSTRUCT_ID = :id AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("adirectorId", LongType.INSTANCE);
 		query.addScalar("bdirectorId", LongType.INSTANCE);
 		query.addScalar("cdesignDirectionId", LongType.INSTANCE);
 		query.addScalar("constrCompReMapId", LongType.INSTANCE);
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("code", StringType.INSTANCE);
 		
 		query.setParameter("id", id);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbComplementWorkDTO.class));

 		return (AbComplementWorkDTO) query.uniqueResult();
	}
	//check
	public AbComplementWorkDTO CheckEstimate3(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
				//+ "ABC.CODE code, "		
				+ "ABC.CREATED_USER_ID createdUserId, "
 				+ "ABC.A_DIRECTOR_ID adirectorId, "
 				+ "ABC.B_DIRECTOR_ID bdirectorId, "
 				+ "ABC.C_DESIGN_DIRECTION_ID cdesignDirectionId, "
 				+ "ABC.STATUS_CA statusCa, "
 				+ "ABC.CODE code "				
 				+ "FROM AB_COMPLEMENT_WORK ABC " 						
 				+ "WHERE ABC.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
 		query.addScalar("createdUserId", LongType.INSTANCE);
 		query.addScalar("adirectorId", LongType.INSTANCE);
 		query.addScalar("bdirectorId", LongType.INSTANCE);
 		query.addScalar("cdesignDirectionId", LongType.INSTANCE);
 		query.addScalar("statusCa", LongType.INSTANCE); 		
 		query.addScalar("code", StringType.INSTANCE); 
 		
 		query.setParameter("constructId", constructId);
 		
 		query.setResultTransformer(Transformers.aliasToBean(AbComplementWorkDTO.class));

 		return (AbComplementWorkDTO) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
    public String autoGenCode() {
     StringBuffer sql = new StringBuffer("select get_next_code('AB_COMPLEMENT_WORK', 'CODE','QT_AB_3',6) code from dual");
     SQLQuery query = getSession().createSQLQuery(sql.toString());
     query.addScalar("code", StandardBasicTypes.STRING);
     return (String) query.uniqueResult();
    }

}
