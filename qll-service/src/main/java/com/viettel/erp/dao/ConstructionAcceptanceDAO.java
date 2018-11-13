/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapBO;
import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.erp.bo.DetailSettlementEvaluateBO;
import com.viettel.erp.bo.DetailSettlementProposalBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.bo.VSysUserBO;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;
import com.viettel.erp.dto.VSysUserDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constructionAcceptanceDAO")
public class ConstructionAcceptanceDAO extends BaseFWDAOImpl<ConstructionAcceptanceBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

    public ConstructionAcceptanceDAO() {
        this.model = new ConstructionAcceptanceBO();
    }

    public ConstructionAcceptanceDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<ConstructionAcceptanceDTO> findByConstructId(Long constructId) {
    	StringBuffer sql = new StringBuffer("SELECT "+
		 "VC.CONSTRUCT_ID constructId,"
		 + "CA.CONSTRUCTION_ACCEPTANCE_ID constructionAcceptanceId ,"
		 + "VC.CONSTRT_CODE constrtCode,"
		 + "VC.CONTRACT_CODE contractCode,CA.STATUS_CA statusCa,CA.CODE code,CNA.CONTRACT_NAME contractName "      
		+"FROM  CONSTRUCTION_ACCEPTANCE CA "
		+"INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = CA.CONSTRUCT_ID "     
		+"INNER JOIN CNT_CONTRACT CNA ON CNA.CONTRACT_ID = VC.CONTRACT_ID "
		+ "WHERE CA.IS_ACTIVE = 1 AND VC.CONSTRUCT_ID = :constructId" );
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("constructId", LongType.INSTANCE);
    	query.addScalar("constrtCode", StringType.INSTANCE);
    	query.addScalar("contractCode", StringType.INSTANCE);
    	query.addScalar("statusCa", LongType.INSTANCE);
    	query.addScalar("code", StringType.INSTANCE);
    	query.addScalar("contractName", StringType.INSTANCE);
    	query.addScalar("constructionAcceptanceId", LongType.INSTANCE);
    	
    	query.setParameter("constructId", constructId);
		query.setResultTransformer(Transformers.aliasToBean(ConstructionAcceptanceDTO.class));
    	
		List<ConstructionAcceptanceDTO> list =  query.list();
		return list;
	}
    
    
  //dodt
    //Bảng diễn giải khối lượng xây lắp hoàn thành
    public List<ConstructionAcceptanceDTO> getAllC(ConstructionAcceptanceDTO obj) {

    	// Tạm comment hql
/*		Query q = getSession().createQuery(Joiner.on(" ").join("select a,b from constructionacceptance a, constrcompleterecordsmap b join fetch a.vconstructionHcqt c    where 1=1 and a.constructionAcceptanceId = b.dataTableIdValue and b.dataTableName = 'CONSTRUCTION_ACCEPTANCE' ",
				                            obj.getConstructId() !=null ? " and c.constructId = :constructId" : "",
				                            obj.getContractId() !=null ? " and c.contractId = :contractId" : "",
				                            obj.getType() !=null ? " and a.type = :type" : ""));
		

		
		
		
		
		if ( obj.getConstructId()!= null) {
			q.setParameter("constructId",   obj.getConstructId());
		}
		
		if ( obj.getContractId()!= null) {
			q.setParameter("contractId",   obj.getContractId());
		}
		
		if (obj.getType()!= null) {
			q.setParameter("type",  obj.getType());
		}
		
		
		List<ConstructionAcceptanceDTO> list = Lists.newArrayList(); 
		
		  for (Object result : q.list()) {
			  Object[] o = (Object[]) result;

			  
			  ConstrCompleteRecordsMapBO  constrCompleteRecordsMapBO = (ConstrCompleteRecordsMapBO) o[1];
			  ConstructionAcceptanceBO constructionAcceptanceBO = (ConstructionAcceptanceBO) o[0];
			  
			  ConstructionAcceptanceDTO constructionAcceptanceDTO = constructionAcceptanceBO.toDTO();
			  constructionAcceptanceDTO.setConstrcompleterecordsmap(constrCompleteRecordsMapBO.toDTO());
			  
			  list.add(constructionAcceptanceDTO);
		  }
		

		
		return list;*/
    	
    	//SQL native
    	
    	//37

		StringBuffer sql = new StringBuffer(
				"SELECT CA.CONSTRUCTION_ACCEPTANCE_ID constructionAcceptanceId,CA.CODE code ,CA.A_MONITOR_ID aMonitorId ,CA.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId,CA.B_DIRECTOR_ID bDirectorId ,"
				
				+"CA.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId ,CA.C_DESIGN_DIRECTION_ID cDesignDirectionId,CA.C_DESIGN_MANAGER_ID cDesignManagerId,CA.ACCEPT_FROM_DATE acceptFromDate,"
				
			    +"CA.ACCEPT_TO_DATE acceptToDate, CA.ACCEPT_PLACE acceptPlace,CA.APPLY_BENCHMARK applyBenchmark,CA.CONSTRUCTION_QUALITY constructionQuality,CA.OTHER_DOCUMENTS otherDocuments,"
			    
				+"CA.OTHER_COMMENTS otherComments,CA.CONCLUSION conclusion,CA.COMPLETE_REQUEST completeRequest,CA.CREATED_DATE createdDate ,CA.CREATED_USER_ID createdUserId,"
				
			    +"CA.APPROVAL_DATE approvalDate ,CA.STATUS_CA statusCa,CA.DOCUMENT_CA_ID documentCaId,CA.TYPE type ,CA.INCOMPLETE_REASON incompleteReason,"
			    
				+"CA.IS_ACTIVE isActive,CA.SIGN_DATE signDate,CA.SIGN_PLACE signPlace,"
				+"VCH.CONSTRUCT_ID constructId,VCH.CONTRACT_ID contractId,"
				+"CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
				+ " DOCUMENT_CA.COMMENT_CA comments, "
				+" VCH.CONSTRT_CODE constrtCode,VCH.CONTRACT_CODE contractCode, VCH.CONTRACT_NAME contractName , VCH.CONSTRT_NAME constrtName ,VCH.CONSTRT_ADDRESS constrtAddress,VCH.STATION_CODE stationCode "
				+" FROM CONSTRUCTION_ACCEPTANCE CA inner join V_CONSTRUCTION_HCQT VCH  on CA.CONSTRUCT_ID=VCH.CONSTRUCT_ID and (VCH.IS_ACTIVE = '1') "
				+" INNER JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON CA.CONSTRUCTION_ACCEPTANCE_ID=CCRM.DATA_TABLE_ID_VALUE AND CCRM.DATA_TABLE_NAME='CONSTRUCTION_ACCEPTANCE'  "
				+ " LEFT JOIN DOCUMENT_CA ON CA.DOCUMENT_CA_ID = DOCUMENT_CA.DOCUMENT_CA_ID "
				+" where CA.IS_ACTIVE = '1'   and 1=1 and VCH.CONSTRUCT_ID= :constructId   ");
        
		if (obj.getType() != null) {
			sql.append(" and CA.TYPE = :type ");
		}
		
		if (obj.getContractId() != null) {
			sql.append(" and VCH.CONTRACT_ID = :contractId ");
		}

		sql.append("order by CA.CONSTRUCTION_ACCEPTANCE_ID DESC");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);
		
		
		query.addScalar("constructionAcceptanceId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		query.addScalar("bDirectorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("cDesignDirectionId", LongType.INSTANCE);
		query.addScalar("cDesignManagerId", LongType.INSTANCE);
		query.addScalar("acceptFromDate", StandardBasicTypes.TIMESTAMP);

		query.addScalar("acceptToDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptPlace", StringType.INSTANCE);
		query.addScalar("applyBenchmark", StringType.INSTANCE);
		
		query.addScalar("constructionQuality", StringType.INSTANCE);
		query.addScalar("otherDocuments", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);
		query.addScalar("completeRequest", StringType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);

		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("documentCaId", LongType.INSTANCE);

		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("incompleteReason", StringType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);

		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		
		if (obj.getType()!= null) {
			query.setParameter("type",  obj.getType());
		}
		
		if ( obj.getConstructId()!= null) {
			query.setParameter("constructId",   obj.getConstructId());
		}
		
		if ( obj.getContractId()!= null) {
			query.setParameter("contractId",   obj.getContractId());
		}
		
		query.setResultTransformer(Transformers.aliasToBean(ConstructionAcceptanceDTO.class));

		List<ConstructionAcceptanceDTO> list = query.list();
		return list;
	
    		
    	
    	
	}
    
    
    
    public String getDocumentPath (Long EmployeeId) {

    	StringBuffer sql = new StringBuffer("SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = " + EmployeeId );
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.addScalar("DOCUMENT_PATH", StandardBasicTypes.STRING);
    	query.setParameter("type2", partnerAttachType);
    			return (String)query.setMaxResults(1).uniqueResult();
	}
    
    
    public ConstructionAcceptanceDTO getByOneId(Long id) {


    	Object[] result = (Object[]) getSession().createQuery(Joiner.on(" ").join(" select a,b from constructionacceptance a, constrcompleterecordsmap b  join fetch a.vconstructionHcqt c  where 1=1 and a.constructionAcceptanceId = b.dataTableIdValue and b.dataTableName = 'CONSTRUCTION_ACCEPTANCE' ",				                          
        		id !=null ? " and a.constructionAcceptanceId = :constructionAcceptanceId" : "")).setParameter("constructionAcceptanceId",  id).setMaxResults(1).uniqueResult();

		
    	ConstructionAcceptanceDTO dto = null;
		if (result != null) {
			 ConstrCompleteRecordsMapBO  constrCompleteRecordsMapBO = (ConstrCompleteRecordsMapBO) result[1];
			  ConstructionAcceptanceBO constructionAcceptanceBO = (ConstructionAcceptanceBO) result[0];
			
			dto = constructionAcceptanceBO.toDTO();
			dto.setConstrcompleterecordsmap(constrCompleteRecordsMapBO.toDTO());
		}
		return dto;
	}
    
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer("select get_next_code('CONSTRUCTION_ACCEPTANCE', 'CODE','TCNT',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

    
    @Transactional
    public Long saveTable( ConstructionAcceptanceDTO ConstrAcc) throws Exception{
    	      Session session = getSession();
    	      Long constructionAcceptanceId = (Long) session.save(ConstrAcc.toModel());
    	      ConstrAcc.getConstrcompleterecordsmap().setDataTableIdValue(constructionAcceptanceId); 	      
    	      session.save(ConstrAcc.getConstrcompleterecordsmap().toModel());
    	      
    	      StringBuilder sql = new StringBuilder();
              sql.append("UPDATE "+ConstrConstructionsBO.class.getName());
              sql.append(" SET ");
              sql.append("IS_ACCEPTED_HANDOVER_USE  = 1");
              sql.append(" WHERE ");
              sql.append("CONSTRUCT_ID =" +ConstrAcc.getConstructId());
              session.createQuery(sql.toString()).executeUpdate();
    	      
    	      
    	      return constructionAcceptanceId;
     }
    
    
    @Transactional
    public Long deleteTable( ConstructionAcceptanceDTO ConstrAcc) throws Exception{
    	      Session session = getSession();
    	      session.delete(ConstrAcc.toModel());     
    	      session.delete(ConstrAcc.getConstrcompleterecordsmap().toModel());
    	      StringBuilder sql = new StringBuilder();
              sql.append("UPDATE "+ConstrConstructionsBO.class.getName());
              sql.append(" SET ");
              sql.append("IS_ACCEPTED_HANDOVER_USE  = 0");
              sql.append(" WHERE ");
              sql.append("CONSTRUCT_ID =" +ConstrAcc.getConstructId());
              session.createQuery(sql.toString()).executeUpdate();
    	      
    	      
    	      return 0L;
     }
    
    public boolean updateIsActive(Long constrAcceptanceId){
    	try {
    		Session session = getSession();
			StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE CONSTRUCTION_ACCEPTANCE SET CONSTRUCTION_ACCEPTANCE.IS_ACTIVE = '0' ");
    		sql.append("WHERE CONSTRUCTION_ACCEPTANCE.CONSTRUCTION_ACCEPTANCE_ID = " + constrAcceptanceId);
    		session.createSQLQuery(sql.toString()).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'CONSTRUCTION_ACCEPTANCE_ID' "
    				+ "AND DATA_TABLE_ID_VALUE = " + constrAcceptanceId).executeUpdate();
			return true;
    	} catch (HibernateException he) {
			log.error(he.getMessage(), he);
			return false;
		}
    }
    

}
