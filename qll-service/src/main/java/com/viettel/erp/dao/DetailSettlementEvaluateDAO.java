/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.ConstrAcceptWorkListBO;
import com.viettel.erp.bo.DetailSettlementEvaluateBO;
import com.viettel.erp.bo.DetailSettlementProposalBO;
import com.viettel.erp.bo.EstimatesDetailAnalystBO;
import com.viettel.erp.bo.EstimatesItemsChildBO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("detailSettlementEvaluateDAO")
public class DetailSettlementEvaluateDAO extends BaseFWDAOImpl<DetailSettlementEvaluateBO, Long> {

	public DetailSettlementEvaluateDAO() {
		this.model = new DetailSettlementEvaluateBO();
	}

	public DetailSettlementEvaluateDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<DetailSettlementEvaluateDTO> getAllbyConstructId(DetailSettlementEvaluateDTO dto) {
		StringBuffer sql = new StringBuffer("SELECT " 
				+ "CG.DETAIL_SETTLEMENT_EVALUATE_ID detailSettlementEvaluateId,"
				+ "CG.CODE code,"
				+ "CG.CREATED_EVALUATE_PER_ID createdEvaluatePerId,"
				+ "CG.CHECK_EVALUATE_PER_ID checkEvaluatePerId,"
				+ "CG.SEND_PERSON_ID sendPersonId,"
				+ "CG.B_REPRESENTATIVE_ID bRepresentativeId,"
				+ " CG.A_DIRECTOR_ID aDirectorId,"
				+ "CG.CREATED_DATE createdDate,"
				+ "CG.CREATED_USER_ID createdUserId,"
				+ "CG.APPROVAL_DATE approvalDate,"
				+ "CG.STATUS_CA statusCa,"
				+ "CG.CONSTRUCT_ID constructId,"
				+ "CE1.FULL_NAME createdEvaluatePerName,"
				+ "CE2.FULL_NAME checkEvaluatePerName,"
				+ "CE3.FULL_NAME sendPersonName,"
				+ "CE4.FULL_NAME brepresentativeName,"
				+ "CE5.FULL_NAME aDirectorName,"
				+ "VCH.CONSTRT_ADDRESS constrtAddress,"
				+ "VCH.CONSTRT_CODE constrtCode,"
				+ "VCH.CONSTRT_NAME constrtName,"
				+ "VCH.CONTRACT_NAME contractName,"
				+ "VCH.CONTRACT_CODE contractCode,"
				+ "VCH.PROVINCE_NAME provinceName,"
				+ "CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
				+ "ASM.COMMENTS comments "
				+ " FROM DETAIL_SETTLEMENT_EVALUATE CG "
				+ " INNER JOIN CAT_EMPLOYEE CE1 ON TO_NUMBER(CE1.ID) = CG.CREATED_EVALUATE_PER_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE2 ON TO_NUMBER(CE2.ID) = CG.CHECK_EVALUATE_PER_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE3 ON TO_NUMBER(CE3.ID) = CG.SEND_PERSON_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE4 ON TO_NUMBER(CE4.ID) = CG.B_REPRESENTATIVE_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE5 ON TO_NUMBER(CE5.ID) = CG.A_DIRECTOR_ID"
				+ " INNER JOIN V_CONSTRUCTION_HCQT VCH ON VCH.CONSTRUCT_ID = CG.CONSTRUCT_ID"
				+ " INNER JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON CCRM.DATA_TABLE_ID_VALUE = CG.DETAIL_SETTLEMENT_EVALUATE_ID AND CCRM.DATA_TABLE_NAME = 'DETAIL_SETTLEMENT_EVALUATE' "
				+ " LEFT JOIN APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2)"
				+ " WHERE 1=1 AND CG.IS_ACTIVE = 1 " + "AND   CG.CONSTRUCT_ID = :contructId");

		if(null != dto.getDetailSettlementEvaluateId()){
			sql.append(" AND CG.DETAIL_SETTLEMENT_EVALUATE_ID = :detailSettlementEvaluateId ");
		}
		
		if(dto.getContractCode()!= null){
			sql.append( " AND VCH.CONTRACT_CODE = :contractCode");
 		}
 		

 		
	
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		
		
		query.addScalar("detailSettlementEvaluateId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("createdEvaluatePerId", LongType.INSTANCE);
		query.addScalar("checkEvaluatePerId", LongType.INSTANCE);
		query.addScalar("sendPersonId", LongType.INSTANCE);
		query.addScalar("bRepresentativeId", LongType.INSTANCE);
		query.addScalar("aDirectorId", LongType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("createdEvaluatePerName", StringType.INSTANCE);
		query.addScalar("checkEvaluatePerName", StringType.INSTANCE);
		query.addScalar("sendPersonName", StringType.INSTANCE);
		query.addScalar("brepresentativeName", StringType.INSTANCE);
		query.addScalar("aDirectorName", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("provinceName", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(DetailSettlementEvaluateDTO.class));
		
		if(null != dto.getDetailSettlementEvaluateId()){
			query.setParameter("detailSettlementEvaluateId", dto.getDetailSettlementEvaluateId());
		}
		if ( dto.getContractCode() != null) {
			query.setParameter("contractCode", dto.getContractCode());
		}
		query.setParameter("contructId", dto.getConstructId());
		List<DetailSettlementEvaluateDTO> list = query.list();
		return list;
	}

	@Transactional
	public Long addManyTable(DetailSettlementEvaluateBO Evaluate, List<EstimatesWorkItemsDTO> listAcc,
			List<EstimatesDetailAnalystBO> listAna, List<Long> listEstimatesWorkItemId) throws Exception {
		Long id = 0L;
		Session session = getSession();
		if (listAna.size() > 0) {

			for (EstimatesDetailAnalystBO Ana : listAna) {
				StringBuilder sqlDel = new StringBuilder();
				sqlDel.append("DELETE FROM  ");
				sqlDel.append(" ESTIMATES_DETAIL_ANALYST ");
				sqlDel.append(" WHERE ");
				sqlDel.append(" PROGRESS_TYPE = 4 ");
				sqlDel.append("  AND ESTIMATES_WORK_ITEM_ID =:estimateWorkItemId");
				session.createSQLQuery(sqlDel.toString())
						.setParameter("estimateWorkItemId", Ana.getEstimatesWorkItems().getEstimatesWorkItemId())
						.executeUpdate();
				Ana.setProgressType(4L);
				session.save(Ana);
			}
		}
		if (null == Evaluate.getDetailSettlementEvaluateId()) {
			Evaluate.setCreatedDate(new Date());
			id = (Long) session.save(Evaluate);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE " + DetailSettlementProposalBO.class.getName());
			sql.append(" SET ");
			sql.append(" EVALUATE_STATUS = 2");
			sql.append(" WHERE ");
			sql.append(" CONSTRUCT_ID =:constrId ");
			Query query =session.createQuery(sql.toString());
			query.setParameter("constrId", Evaluate.getConstructId());
			query.executeUpdate();
			if (listAna.size() > 0) {
				for (EstimatesDetailAnalystBO Ana : listAna) {
					Ana.setProgressType(4L);
					session.save(Ana);
				}
			}
		} else {
			session.update(Evaluate);

		}

		for (EstimatesWorkItemsDTO Acc : listAcc) {

			StringBuilder sql1 = new StringBuilder();
			sql1.append("UPDATE " + ConstrAcceptWorkListBO.class.getName());
			sql1.append(" SET ");
			sql1.append(" EVALUATE_UNIT_PRICE   = :unitPrice");
			sql1.append(" ,EVALUATE_QUANTITY  = :quantity");
			if (!StringUtils.isEmpty(Acc.getEvaluateComments())) {
				sql1.append(" ,EVALUATE_COMMENTS  = :comment");
			}
			sql1.append(" WHERE ");
			sql1.append("ESTIMATES_WORK_ITEM_ID = :estimateWorkItemId");
			Query query = session.createQuery(sql1.toString());
			if (!StringUtils.isEmpty(Acc.getEvaluateComments())) {
				query.setParameter("comment", Acc.getEvaluateComments());
			}
			query.setParameter("quantity", Acc.getEvaluateQuantity());
			query.setParameter("unitPrice", Acc.getEvaluateUnitPrice());
			query.setParameter("estimateWorkItemId", Acc.getEstimatesWorkItemId());

			query.executeUpdate();
		}

		return id;
	}

	@Transactional
	public void fail(DetailSettlementEvaluateDTO obj) throws Exception {
		Session session = getSession();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE " + DetailSettlementProposalBO.class.getName());
		sql.append(" SET ");
		sql.append("EVALUATE_STATUS = 3 ");
		if(StringUtils.isNotEmpty(obj.getEvaluateComments())){
		sql.append(" ,EVALUATE_COMMENTS =:comment ");
		}
		sql.append(" WHERE ");
		sql.append("CONSTRUCT_ID =:constrId");
		Query	query= session.createQuery(sql.toString());
		if(StringUtils.isNotEmpty(obj.getEvaluateComments())){
		query.setParameter("comment", obj.getEvaluateComments());
		}
		query.setParameter("constrId", obj.getConstructId());
		query.executeUpdate();
	}

	@Transactional
	public void deleteDetailSettlementEvaluate(Long id, List<EstimatesWorkItemsDTO> listItem) throws Exception {
		Session session = getSession();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE " + DetailSettlementProposalBO.class.getName());
		sql.append(" SET ");
		sql.append("EVALUATE_STATUS = 1");
		sql.append(" WHERE ");
		sql.append("CONSTRUCT_ID = :id");
		session.createQuery(sql.toString()).setParameter("id", id).executeUpdate();

		String sq = "UPDATE DETAIL_SETTLEMENT_EVALUATE" + " SET " + " IS_ACTIVE   = 0" + " WHERE " + "CONSTRUCT_ID ="
				+ id;
		session.createSQLQuery(sq).executeUpdate();

		for (EstimatesWorkItemsDTO Acc : listItem) {
			String sql1 = "UPDATE CONSTR_ACCEPT_WORK_LIST SET EVALUATE_UNIT_PRICE = null" 
					+ " ,EVALUATE_QUANTITY  = null ,EVALUATE_COMMENTS  = null WHERE "
					+ "ESTIMATES_WORK_ITEM_ID = :estimateWorkItemId" ;
			session.createSQLQuery(sql1).setParameter("estimateWorkItemId", Acc.getEstimatesWorkItemId()).executeUpdate();

			StringBuilder sqlDel = new StringBuilder();
			sqlDel.append("DELETE FROM  ");
			sqlDel.append(" ESTIMATES_DETAIL_ANALYST ");
			sqlDel.append(" WHERE ");
			sqlDel.append(" PROGRESS_TYPE = 4 ");
			sqlDel.append("  AND ESTIMATES_WORK_ITEM_ID = :estimateWorkItemId");
			session.createSQLQuery(sqlDel.toString()).setParameter("estimateWorkItemId", Acc.getEstimatesWorkItemId()).executeUpdate();
		}
	}

	public boolean deleteFromRecomap(long id) {
		Session session = getSession();
		session.createSQLQuery(
				"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'DETAIL_SETTLEMENT_EVALUATE' AND recod.DATA_TABLE_ID_VALUE = :id")
				.setParameter("id", id).executeUpdate();
		return true;
	}

	public boolean checkStatusDatabase(Long detailSettlementEvaluateId) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT SC.STATUS_CA status" + " FROM DETAIL_SETTLEMENT_EVALUATE SC "
				+ " WHERE SC.DETAIL_SETTLEMENT_EVALUATE_ID = :detailSettlementEvaluateId");

		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("status", LongType.INSTANCE);
		query.setParameter("detailSettlementEvaluateId", detailSettlementEvaluateId);

		Long status = (Long) query.uniqueResult();
		if (status == 0L || status == 3L) {
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

	@Transactional
	public Long appro(approDTO obj) {
		approDTO appCheck = getCheckData(obj);
		if (appCheck == null) {
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

					////////////// Thay Ä‘á»•i thÃ nh tÃªn báº£ng vÃ  id cá»§a
					////////////// báº£ng mÃ¬nh
					session.createSQLQuery(
							"update DETAIL_SETTLEMENT_EVALUATE com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 2 "
									+ " where  com.DETAIL_SETTLEMENT_EVALUATE_ID = :id")
							.setParameter("appDate", new Date()).setParameter("id", obj.getDetailSettlementEvaluateId())
							.executeUpdate();
					///////////////////////////////////////////////////
				} else {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.LEVEL_ORDER = :lvod "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id")
							.setParameter("lvod", appCheck.getLevelOrder() + 1)
							.setParameter("id", obj.getConstrCompReMapId()).executeUpdate();
				}
				session.createSQLQuery(
						"update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 1, asm.APPROVAL_DATE=:appDate"
								+ " , asm.COMMENTS = :cmm where asm.EMPLOYEE_ID = :employeeId  AND asm.CONSTR_COMP_RE_MAP_ID  = :ccrmId ")
						.setParameter("employeeId", obj.getEmployeeId()).setParameter("cmm", obj.getComments())
						.setParameter("appDate", new Date()).setParameter("ccrmId", obj.getConstrCompReMapId())
						.executeUpdate();
				resp = 3l;
			} else {
				session.createSQLQuery(
						"update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 2, asm.APPROVAL_DATE=:appDate "
								+ " , asm.COMMENTS = :cmm where asm.EMPLOYEE_ID = :employeeId  AND asm.CONSTR_COMP_RE_MAP_ID  = :ccrmId ")
						.setParameter("employeeId", obj.getEmployeeId()).setParameter("cmm", obj.getComments())
						.setParameter("appDate", new Date()).setParameter("ccrmId", obj.getConstrCompReMapId())
						.executeUpdate();
				session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 3 "
						+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
						.executeUpdate();

				////////////// Thay Ä‘á»•i thÃ nh tÃªn báº£ng vÃ  id cá»§a
				////////////// báº£ng mÃ¬nh
				session.createSQLQuery(
						"update DETAIL_SETTLEMENT_EVALUATE com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 3 "
								+ " where  com.DETAIL_SETTLEMENT_EVALUATE_ID = :id")
						.setParameter("appDate", new Date()).setParameter("id", obj.getDetailSettlementEvaluateId())
						.executeUpdate();
				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}

	public DetailSettlementEvaluateDTO getsendPerson(Long id){
		String sql="SELECT b.EMPLOYEE_ID sendPersonId,CAT.FULL_NAME sendPersonName FROM SYS_USER a"
				+" JOIN USER_EMPLOYEE b ON a.USER_ID=b.USER_ID"
				+" JOIN CAT_EMPLOYEE CAT ON TO_NUMBER(CAT.ID)=b.EMPLOYEE_ID"
				+" WHERE a.USER_ID=:id";
		SQLQuery query = getSession().createSQLQuery(sql);
		
		query.addScalar("sendPersonId", LongType.INSTANCE);
		query.addScalar("sendPersonName", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(DetailSettlementEvaluateDTO.class));
		query.setParameter("id", id);
		
		return (DetailSettlementEvaluateDTO) query.uniqueResult();
	}
	public boolean updateIsActive(Long detailSettlementEvaluateId){
    		Session session = getSession();
			StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE DETAIL_SETTLEMENT_EVALUATE SET DETAIL_SETTLEMENT_EVALUATE.IS_ACTIVE = '0' ");
    		sql.append("WHERE DETAIL_SETTLEMENT_EVALUATE.DETAIL_SETTLEMENT_EVALUATE_ID = " + detailSettlementEvaluateId);
    		session.createSQLQuery(sql.toString()).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'DETAIL_SETTLEMENT_EVALUATE_ID' "
    				+ "AND DATA_TABLE_ID_VALUE = " + detailSettlementEvaluateId).executeUpdate();
			return true;
    }
	
}
