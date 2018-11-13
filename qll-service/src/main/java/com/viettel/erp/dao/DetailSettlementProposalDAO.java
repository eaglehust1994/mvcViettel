/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.DetailSettlementProposalBO;
import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("detailSettlementProposalDAO")
public class DetailSettlementProposalDAO extends BaseFWDAOImpl<DetailSettlementProposalBO, Long> {

    public DetailSettlementProposalDAO() {
        this.model = new DetailSettlementProposalBO();
    }

    public DetailSettlementProposalDAO(Session session) {
        this.session = session;
    }
    
	public  String getCode( String tableName, String value) {
		SQLQuery query = getSession().createSQLQuery(Joiner.on("").join("select get_next_code('", tableName, "', 'CODE','", value, "',6) code from dual"));
		query.addScalar("code", StandardBasicTypes.STRING);

		return (String) query.uniqueResult();
	}
    
    
	public List<DetailSettlementProposalDTO> getAllDetailSettlementProposal(DetailSettlementProposalDTO  detailSettlementProposalDTO) {
 		StringBuffer sqlBuffer = new StringBuffer("SELECT"
 				+ " DSP.DETAIL_SETTLEMENT_PROPOSAL_ID detailSettlementProposalId,"
				+ " DSP.CODE code,"
				+ " DSP.SEND_PERSON_ID sendPersonId," //nguoi lap
				+ " DSP.B_REPRESENTATIVE_ID bRepresentativeId," // dai dien chu dau tu
				+ " DSP.CREATED_DATE createdDate,"
				+ " DSP.CREATED_USER_ID createdUserId," // accout nguoi tao
				+ " DSP.APPROVAL_DATE approvalDate,"
				+ " DSP.STATUS_CA statusCa,"
				+ " DSP.EVALUATE_PERSON_ID evaluatePersonId," // nguoi giao tham dinh
				+ " DSP.EVALUATE_COMMENTS evaluateComments,"
				+ " DSP.EVALUATE_FINISH_DATE evaluateFinishDate,"
				+ " DSP.EVALUATE_STATUS evaluateStatus,"
				+ " DSP.IS_ACTIVE isActive,"
				+ " DSP.CONSTRUCT_ID constructId, "
				+ "VC.CONSTRT_ADDRESS constrtAddress,"
				+ "VC.CONSTRT_CODE constrtCode,"
				+ "VC.CONSTRT_NAME constrtName,"
				+ "VC.CONTRACT_NAME contractName,"
				+ "VC.CONTRACT_CODE contractCode,"
				+ "VC.PROVINCE_NAME provinceName,"
				+ " CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId,"
				+ "ASM.COMMENTS comments, "
				+ " CE1.FULL_NAME sendPersonName,"
				+ " CE2.FULL_NAME brepresentaiveName,"
				+ " CE3.FULL_NAME fullNameEmployee"
//				+ " CE4.FULL_NAME createdUserName"
				+ " FROM DETAIL_SETTLEMENT_PROPOSAL DSP"
				+ " INNER JOIN CAT_EMPLOYEE CE1 ON TO_NUMBER(CE1.ID) = DSP.SEND_PERSON_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE2 ON TO_NUMBER(CE2.ID) = DSP.B_REPRESENTATIVE_ID"
				+ " LEFT JOIN CAT_EMPLOYEE CE3 ON TO_NUMBER(CE3.ID) = DSP.EVALUATE_PERSON_ID"
//				+ " LEFT JOIN CAT_EMPLOYEE CE4 ON TO_NUMBER(CE4.ID) = DSP.createdUserId"
				+ " INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = DSP.CONSTRUCT_ID "
				+ " JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON CCRM.DATA_TABLE_ID_VALUE = DSP.DETAIL_SETTLEMENT_PROPOSAL_ID AND CCRM.DATA_TABLE_NAME = 'DETAIL_SETTLEMENT_PROPOSAL' "
				+ " LEFT JOIN APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2)"
				+ " WHERE DSP.IS_ACTIVE = 1 AND DSP.CONSTRUCT_ID = :constructId" );
		
 		if(StringUtils.isNotEmpty(detailSettlementProposalDTO.getContractCode())){
 			sqlBuffer.append( " AND VC.CONTRACT_CODE = :contractCode");
 		}
 		
 		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());

 				
 		query.addScalar("detailSettlementProposalId", LongType.INSTANCE);
 		query.addScalar("code", StringType.INSTANCE);
 		query.addScalar("sendPersonId", LongType.INSTANCE);
 		query.addScalar("bRepresentativeId", LongType.INSTANCE);
 		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
 		query.addScalar("createdUserId", LongType.INSTANCE);
 		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
 		query.addScalar("statusCa", LongType.INSTANCE);
 		query.addScalar("evaluatePersonId", LongType.INSTANCE);
 		query.addScalar("evaluateComments", StringType.INSTANCE);
 		query.addScalar("evaluateFinishDate", StandardBasicTypes.TIMESTAMP);
 		query.addScalar("evaluateStatus", LongType.INSTANCE);
 		query.addScalar("isActive", LongType.INSTANCE);
 		query.addScalar("constructId", LongType.INSTANCE);
 		query.addScalar("constrCompReMapId", LongType.INSTANCE);
 		
 		query.addScalar("constrtCode", StringType.INSTANCE);
 		query.addScalar("contractCode", StringType.INSTANCE);
 		query.addScalar("contractName", StringType.INSTANCE);
 		
 		query.addScalar("constrtAddress", StringType.INSTANCE);
 		query.addScalar("constrtName", StringType.INSTANCE);
 		query.addScalar("fullNameEmployee", StringType.INSTANCE);
 		query.addScalar("sendPersonName", StringType.INSTANCE);
 		query.addScalar("brepresentaiveName", StringType.INSTANCE);
// 		query.addScalar("createdUserName", StringType.INSTANCE);
 		query.addScalar("provinceName", StringType.INSTANCE);
 		
 		query.addScalar("comments", StringType.INSTANCE);
 		
 			query.setParameter("constructId", detailSettlementProposalDTO.getConstructId());
 		
		if (StringUtils.isNotEmpty(detailSettlementProposalDTO.getContractCode())) {
			query.setParameter("contractCode", detailSettlementProposalDTO.getContractCode());
		}
 		
 		/*if (detailSettlementProposalDTO.getConstructId()!= null) {
 			query.setParameter("constructId",  detailSettlementProposalDTO.getConstructId());
 		}*/
 		query.setResultTransformer(Transformers.aliasToBean(DetailSettlementProposalDTO.class));
 		return query.list();
 	}
	
	
	@Transactional
    public  Long addManyTable( DetailSettlementProposalBO Proposal,List<EstimatesWorkItemsDTO> listAcc) throws Exception{
    	      Session session = getSession();
    	      Proposal.setEvaluateStatus(0L);
    	      Long id = (long)  session.save(Proposal);
    	      /*if(null!= Proposal.getDetailSettlementProposalId()){
    	       id = (long)  session.save(Proposal);
    	      } else {
    	    	    session.update(Proposal);
    	      }*/
    	                
    	             for(EstimatesWorkItemsDTO Acc:listAcc){
    	            	String sql1 = "UPDATE CONSTR_ACCEPT_WORK_LIST"
    	                +" SET "
    	                +"SETTLE_UNIT_PRICE     = :settleUnitPrice " 
    	                +" WHERE "
    	                +" ESTIMATES_WORK_ITEM_ID = :estimatesWorkItemId " ;
    	                session.createSQLQuery(sql1).setParameter("settleUnitPrice",Acc.getSettleUnitPrice())
    	                							.setParameter("estimatesWorkItemId",Acc.getEstimatesWorkItemId()).executeUpdate();
    	            }
    	      return id;
    	}
	
	@Transactional
    public  String updateManyTable( DetailSettlementProposalBO Proposal,List<EstimatesWorkItemsDTO> listAcc) throws Exception{
    	      Session session = getSession();
    	      Proposal.setEvaluateStatus(0L);
    	      session.update(Proposal);
//    	      Long id = (long)  session.save(Proposal);
    	      /*if(null!= Proposal.getDetailSettlementProposalId()){
    	       id = (long)  session.save(Proposal);
    	      } else {
    	    	    session.update(Proposal);
    	      }*/
    	                
    	             for(EstimatesWorkItemsDTO Acc:listAcc){
    	            	String sql1 = "UPDATE CONSTR_ACCEPT_WORK_LIST"
    	                +" SET "
    	                +"SETTLE_UNIT_PRICE     = :settleUnitPrice " 
    	                +" WHERE "
    	                +" ESTIMATES_WORK_ITEM_ID = :estimatesWorkItemId";
    	                session.createSQLQuery(sql1).setParameter("settleUnitPrice",Acc.getSettleUnitPrice())
    	                							.setParameter("estimatesWorkItemId",Acc.getEstimatesWorkItemId()).executeUpdate();
    	            }
    	      return null;
    	}
	
    @Transactional
    public  String deleteDetailSettlementProposal( Long id, List<EstimatesWorkItemsDTO> listItem) throws Exception{
    	      Session session = getSession();
    	            	
    	                for(EstimatesWorkItemsDTO Acc:listItem){
        	            	String sql1 = "UPDATE CONSTR_ACCEPT_WORK_LIST"
        	                +" SET "
        	                +" SETTLE_UNIT_PRICE   = " + null
        	                +" WHERE "
        	                +"ESTIMATES_WORK_ITEM_ID = :estimatesWorkItemId" ;
        	                session.createSQLQuery(sql1).setParameter("estimatesWorkItemId",Acc.getEstimatesWorkItemId()).executeUpdate();
        	            }
    	      return null;
    	   }
	 public boolean checkStatusDatabase(Long detailSettlementProposalId) {
			StringBuffer sqlBuffer = new StringBuffer("SELECT SC.STATUS_CA status"
					+ " FROM DETAIL_SETTLEMENT_PROPOSAL SC "
					+ " WHERE SC.DETAIL_SETTLEMENT_PROPOSAL_ID = :detailSettlementProposalId");
					
			SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
			query.addScalar("status", LongType.INSTANCE);
			query.setParameter("detailSettlementProposalId", detailSettlementProposalId);
			
			Long status = (Long) query.uniqueResult();
			if(status==0L || status==3L) {
				return false;
			} else {
				return true;
			}
		}
	 
	 public approDTO getCheckData(approDTO obj) {
			StringBuffer sql = new StringBuffer(
					"SELECT cr.LEVEL_ORDER levelOrder, ca.APPROVAL_ORDER approvalOrder, ca.IS_LAST isLast "
							+ "from APPROVAL_SIGN_MANAGEMENT ca " + "inner join CONSTR_COMPLETE_RECORDS_MAP cr "
							+ "on ca.CONSTR_COMP_RE_MAP_ID = cr.CONSTR_COMP_RE_MAP_ID "
							+ "where ca.CONSTR_COMP_RE_MAP_ID = :ccrmID and ca.EMPLOYEE_ID = :eID AND ROWNUM = 1 ");
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("levelOrder", LongType.INSTANCE);
			query.addScalar("approvalOrder", LongType.INSTANCE);
			query.addScalar("isLast", LongType.INSTANCE);
			query.setResultTransformer(Transformers.aliasToBean(approDTO.class));
			query.setParameter("ccrmID", obj.getConstrCompReMapId());
			query.setParameter("eID", obj.getEmployeeId());
			return (approDTO) query.uniqueResult();
		}

	    public boolean deleteFromRecomap(DetailSettlementProposalDTO obj){
	    	Session session = getSession();
	    	session.createSQLQuery(
					"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'DETAIL_SETTLEMENT_PROPOSAL' AND recod.DATA_TABLE_ID_VALUE = :id")
					.setParameter("id", obj.getDetailSettlementProposalId()).executeUpdate();
	    	return true;
	    }
	 
		@Transactional
		public Long appro(approDTO obj) {
			approDTO appCheck = getCheckData(obj);
			if(appCheck == null) {
				return 5l;
			}
			Long resp = 0l;
			
			Session session = getSession();
			if (appCheck.getLevelOrder() < appCheck.getApprovalOrder()) {
				System.out.println("Chua den luot duyet");
				resp = 1l;
			} else if (appCheck.getLevelOrder() > appCheck.getApprovalOrder()) {
				System.out.println("Da duoc duyet roi");
				resp = 2l;
			} else if (appCheck.getLevelOrder() == appCheck.getApprovalOrder()) {
				if (obj.getStatusCa() == 2) {
					if (appCheck.getIsLast() == 1) {
						session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
						
								+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
								.executeUpdate();
						
						//////////////Thay đổi thành tên bảng và id của bảng mình
						session.createSQLQuery("update DETAIL_SETTLEMENT_PROPOSAL com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 2 "
								+ " where  com.DETAIL_SETTLEMENT_PROPOSAL_ID = :id")
								.setParameter("appDate", new Date())
								.setParameter("id", obj.getDetailSettlementProposalId()).executeUpdate();
						///////////////////////////////////////////////////
					} else {
						session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.LEVEL_ORDER = :lvod "
								+ " where  com.CONSTR_COMP_RE_MAP_ID = :id")
								.setParameter("lvod", appCheck.getLevelOrder() + 1)
								.setParameter("id", obj.getConstrCompReMapId()).executeUpdate();
					}
					session.createSQLQuery("update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 1, asm.APPROVAL_DATE=:appDate "
							+ " , asm.COMMENTS = :cmm where asm.EMPLOYEE_ID = :employeeId  AND asm.CONSTR_COMP_RE_MAP_ID  = :ccrmId ")
							.setParameter("employeeId", obj.getEmployeeId()).setParameter("cmm", obj.getComments()).setParameter("appDate", new Date())
							.setParameter("ccrmId", obj.getConstrCompReMapId()).executeUpdate();
					resp = 3l;
				} else {
					session.createSQLQuery("update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 2, asm.APPROVAL_DATE=:appDate "
							+ " , asm.COMMENTS = :cmm where asm.EMPLOYEE_ID = :employeeId  AND asm.CONSTR_COMP_RE_MAP_ID  = :ccrmId ")
							.setParameter("employeeId", obj.getEmployeeId()).setParameter("cmm", obj.getComments()).setParameter("appDate", new Date())
							.setParameter("ccrmId", obj.getConstrCompReMapId()).executeUpdate();
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 3 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();
					
					//////////////Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery("update DETAIL_SETTLEMENT_PROPOSAL com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 3 "
							+ " where  com.DETAIL_SETTLEMENT_PROPOSAL_ID = :id")
							.setParameter("appDate", new Date())
							.setParameter("id", obj.getDetailSettlementProposalId()).executeUpdate();
					///////////////////////////////////////////////////
					resp = 4l;
				}
			}
			return resp;
		}
		
		public boolean updateIsActive(Long detailSettlementProposalId){
	    		Session session = getSession();
				StringBuilder sql = new StringBuilder();
	    		sql.append("UPDATE DETAIL_SETTLEMENT_PROPOSAL SET DETAIL_SETTLEMENT_PROPOSAL.IS_ACTIVE = '0' ");
	    		sql.append("WHERE DETAIL_SETTLEMENT_PROPOSAL.DETAIL_SETTLEMENT_PROPOSAL_ID = " + detailSettlementProposalId);
	    		session.createSQLQuery(sql.toString()).executeUpdate();
	    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'DETAIL_SETTLEMENT_PROPOSAL_ID' "
	    				+ "AND DATA_TABLE_ID_VALUE = " + detailSettlementProposalId).executeUpdate();
				return true;
	    }
	
		
}
