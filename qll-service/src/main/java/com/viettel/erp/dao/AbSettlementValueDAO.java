/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.AbSettlementValueBO;
import com.viettel.erp.dto.AbMaterialCompareDTO;
import com.viettel.erp.dto.AbSettlementValueDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("abSettlementValueDAO")
public class AbSettlementValueDAO extends BaseFWDAOImpl<AbSettlementValueBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	@Autowired
	CatFileInvoiceDAO catFileInvoiceDAO;
	
    public AbSettlementValueDAO() {
        this.model = new AbSettlementValueBO();
    }

    public AbSettlementValueDAO(Session session) {
        this.session = session;
    }
    
    @Transactional
    public Long saveTable( AbSettlementValueDTO  abSettlementValue) throws Exception{
	      Session session = getSession();
	      abSettlementValue.setCreatedDate(new Date());
	      String code = autoGenCode();
	      abSettlementValue.setCode(code);
	      System.out.println("Code ==========================" + code);
	      abSettlementValue.setCreatedUserId(abSettlementValue.getCreatedUserId());
	      abSettlementValue.setStatusCa(abSettlementValue.getStatusCa());
	      abSettlementValue.setIsActive(abSettlementValue.getIsActive());
	      Long abSettlementValueId = (Long) session.save(abSettlementValue.toModel());
	      abSettlementValue.getConstrCompleteRecordsMap().setDataTableIdValue(abSettlementValueId);
	      System.out.println("getDataTableIdValue " +  abSettlementValue.getConstrCompleteRecordsMap().getDataTableIdValue());
	      CatFileInvoiceDTO dto ;
	      dto = catFileInvoiceDAO.onlyFindByTableName("AB_SETTLEMENT_VALUE");
	      abSettlementValue.getConstrCompleteRecordsMap().setCatFileInvoiceId(dto.getCatFileInvoiceId());
	      abSettlementValue.getConstrCompleteRecordsMap().setDataTableName("AB_SETTLEMENT_VALUE");
	      abSettlementValue.getConstrCompleteRecordsMap().setDataTableId("AB_SETTLEMENT_VALUE_ID");
	      abSettlementValue.getConstrCompleteRecordsMap().setLevelOrder(1l);
	      abSettlementValue.getConstrCompleteRecordsMap().setConstructionId(abSettlementValue.getConstructId());
	      abSettlementValue.getConstrCompleteRecordsMap().setCreatedDate(new Date());
	      abSettlementValue.getConstrCompleteRecordsMap().setCreatedUserId(abSettlementValue.getCreatedUserId());
	      abSettlementValue.getConstrCompleteRecordsMap().setCode(abSettlementValue.getCode());
	      abSettlementValue.getConstrCompleteRecordsMap().setStatus(0L);
	      Long constrCompReMapId = (Long) session.save(abSettlementValue.getConstrCompleteRecordsMap().toModel());
	      return constrCompReMapId;
    }
    
    public AbSettlementValueDTO getById(Long constructId) {
		StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "a.CONSTRUCT_ID constructId,"
 				+ "a.AB_SETTLEMENT_VALUE_ID abSettlementValueId " 				
 				+ "FROM AB_SETTLEMENT_VALUE a " 				
 				+ "WHERE a.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("abSettlementValueId", LongType.INSTANCE);
 		query.setParameter("constructId", constructId);
 		query.setResultTransformer(Transformers.aliasToBean(AbSettlementValueDTO.class));
 		return (AbSettlementValueDTO) query.uniqueResult();
	}
    
    public Long getConstrComReMapId(Long idChild){
    	Long id;
    	StringBuilder sqlbuilder = new StringBuilder("select CONSTR_COMP_RE_MAP_ID id "
    			+ " from CONSTR_COMPLETE_RECORDS_MAP "
    			+ " where DATA_TABLE_NAME = 'AB_SETTLEMENT_VALUE' "
    			+ " and DATA_TABLE_ID_VALUE = :idChild");
    	SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	
    	query.addScalar("id", LongType.INSTANCE);
    	query.setParameter("idChild", idChild);
    	id = (Long) query.uniqueResult();
    	return id;
    }
    
    public AbSettlementValueDTO checkEstimateAB1(Long constructId){
    	StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "a.CONSTRUCT_ID constructId, "
 				+ "a.Code code, "
 				+ "a.A_DIRECTOR_ID aDirectorId, "
 				+ "a.B_DIRECTOR_ID bDirectorId, "
 				+ "a.STATUS_CA statusCa, "
 				+ "a.DOCUMENT_CA_ID documentCaId, "
 				+ "a.AB_SETTLEMENT_VALUE_ID abSettlementValueId, "
				+ "a.EXPORT_MATERIAL_VALUE exportMaterialValue, "
				+ "a.ACCEPT_MATERIAL_VALUE acceptMaterialValue, "
				+ "a.LOST_MATERIAL_VALUE lostMaterialValue, "
				+ "a.RECOVERY_MATERIAL_VALUE recoveryMaterialValue, "
				+ "a.UNRECOVERY_MATERIAL_VALUE unrecoveryMaterialValue, "
				+ "a.PAID_VALUE paidValue, "
				+ "a.CREATED_USER_ID createdUserId "
 				+ "FROM AB_SETTLEMENT_VALUE a " 				
 				+ "WHERE "
 				+ " a.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("code", StringType.INSTANCE);
 		query.addScalar("aDirectorId", LongType.INSTANCE);
 		query.addScalar("bDirectorId", LongType.INSTANCE);
 		query.addScalar("statusCa", LongType.INSTANCE);
 		query.addScalar("documentCaId", LongType.INSTANCE);
 		query.addScalar("abSettlementValueId", LongType.INSTANCE);
 		query.addScalar("exportMaterialValue", LongType.INSTANCE);
 		query.addScalar("acceptMaterialValue", LongType.INSTANCE);
 		query.addScalar("lostMaterialValue", LongType.INSTANCE);
 		query.addScalar("recoveryMaterialValue", LongType.INSTANCE);
 		query.addScalar("unrecoveryMaterialValue", LongType.INSTANCE);
 		query.addScalar("paidValue", LongType.INSTANCE);
 		query.addScalar("createdUserId", LongType.INSTANCE);
 		query.setParameter("constructId", constructId);
 		query.setResultTransformer(Transformers.aliasToBean(AbSettlementValueDTO.class));

 		return (AbSettlementValueDTO) query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('AB_SETTLEMENT_VALUE', 'CODE','QT_AB1',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}
    
    public AbSettlementValueDTO getByABSettlementId(Long id) {
    	StringBuilder sqlbuilder = new StringBuilder("SELECT "
 				+ "a.CONSTRUCT_ID constructId, "
 				+ "a.Code code, "
 				+ "a.A_DIRECTOR_ID aDirectorId, "
 				+ "a.B_DIRECTOR_ID bDirectorId, "
 				+ "a.STATUS_CA statusCa, "
 				+ "a.DOCUMENT_CA_ID documentCaId, "
 				+ "a.AB_SETTLEMENT_VALUE_ID abSettlementValueId, "
				+ "a.EXPORT_MATERIAL_VALUE exportMaterialValue, "
				+ "a.ACCEPT_MATERIAL_VALUE acceptMaterialValue, "
				+ "a.LOST_MATERIAL_VALUE lostMaterialValue, "
				+ "a.RECOVERY_MATERIAL_VALUE recoveryMaterialValue, "
				+ "a.UNRECOVERY_MATERIAL_VALUE unrecoveryMaterialValue, "
				+ "a.PAID_VALUE paidValue, "
				+ "v.CONTRACT_NAME constrtName, "
				+ "v.CONSTRT_ADDRESS constructAddress, "
				+ "v.CONTRACT_CODE contractCode, "
				+ "v.STATION_CODE stationcode, "
				+ "v.CONSTRT_NAME constructName, "
				+ "v.INVEST_PROJECT_NAME investProjectName, "
				
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
    			+ "(SELECT A_DIRECTOR_ID FROM AB_SETTLEMENT_VALUE WHERE AB_SETTLEMENT_VALUE_ID = "+ id + ")) aDirectorIdPath, "
    			+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
    			+ "(SELECT B_DIRECTOR_ID FROM AB_SETTLEMENT_VALUE WHERE AB_SETTLEMENT_VALUE_ID = "+ id + ")) bDirectorIdPath "
    			
 				+ "FROM AB_SETTLEMENT_VALUE a "
 				+ "inner join V_CONSTRUCTION_HCQT v on v.CONSTRUCT_ID = a.CONSTRUCT_ID " 				
 				+ "WHERE "
 				+ " a.AB_SETTLEMENT_VALUE_ID = :id AND ROWNUM = 1");
 		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("code", StringType.INSTANCE);
 		query.addScalar("aDirectorId", LongType.INSTANCE);
 		query.addScalar("bDirectorId", LongType.INSTANCE);
 		query.addScalar("statusCa", LongType.INSTANCE);
 		query.addScalar("documentCaId", LongType.INSTANCE);
 		query.addScalar("abSettlementValueId", LongType.INSTANCE);
 		query.addScalar("exportMaterialValue", LongType.INSTANCE);
 		query.addScalar("acceptMaterialValue", LongType.INSTANCE);
 		query.addScalar("lostMaterialValue", LongType.INSTANCE);
 		query.addScalar("recoveryMaterialValue", LongType.INSTANCE);
 		query.addScalar("unrecoveryMaterialValue", LongType.INSTANCE);
 		query.addScalar("paidValue", LongType.INSTANCE);
 		query.addScalar("constrtName", StringType.INSTANCE);
 		query.addScalar("constructAddress", StringType.INSTANCE);
 		query.addScalar("contractCode", StringType.INSTANCE);
 		query.addScalar("stationcode", StringType.INSTANCE);
 		query.addScalar("constructName", StringType.INSTANCE);
 		query.addScalar("investProjectName", StringType.INSTANCE);
 		
 		query.addScalar("aDirectorIdPath", StringType.INSTANCE);
 		query.addScalar("bDirectorIdPath", StringType.INSTANCE);
 		query.setParameter("id", id);
 		query.setParameter("type2", partnerAttachType);
 		query.setResultTransformer(Transformers.aliasToBean(AbSettlementValueDTO.class));

 		return (AbSettlementValueDTO) query.uniqueResult();
	}
    
    @Transactional
    public AbSettlementValueDTO getStatusEvaluate(Long constrId){
    	StringBuilder sqlbuilder = new StringBuilder(
    			  "select STATUS_CA statusCaSettlementEvaluate from DETAIL_SETTLEMENT_EVALUATE where IS_ACTIVE = 1 AND CONSTRUCT_ID = :constrId AND ROWNUM = 1");
    	
    	SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());	 		
 		query.addScalar("statusCaSettlementEvaluate", LongType.INSTANCE);
 		query.setParameter("constrId", constrId);
    	query.setResultTransformer(Transformers.aliasToBean(AbSettlementValueDTO.class));
    	return (AbSettlementValueDTO) query.uniqueResult();
    }
    
}
