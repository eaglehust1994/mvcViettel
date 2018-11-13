/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.ConstrGroundHandoverBO;
import com.viettel.erp.dto.ConstrGroundHandoverDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrGroundHandoverDAO")
public class ConstrGroundHandoverDAO extends BaseFWDAOImpl<ConstrGroundHandoverBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public ConstrGroundHandoverDAO() {
		this.model = new ConstrGroundHandoverBO();
	}

	public ConstrGroundHandoverDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<ConstrGroundHandoverDTO> getAllConstrGroundHandover(ConstrGroundHandoverDTO dto) {
		StringBuffer sql = new StringBuffer("SELECT " 
				+ "CG.CONSTR_GROUND_HANDOVER_ID constrGroundHandoverId, "
				+ "CG.CODE code," 
				+ "CG.A_DIRECTOR_ID aDirectorId," 
				+ "CG.A_MONITOR_ID aMonitorId,"
				+ "CG.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId," 
				+ "CG.B_DIRECTOR_ID bDirectorId,"
				+ "CG.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId," 
				+ "CG.STATUS_CA statusCa,"
				+ "CG.HANDOVER_DATE handoverDate," 
				+ "CG.GROUND_CURRENT_STATUS groundCurrentStatus,"
				+ "CG.BENCHMARK benchmark," 
				+ "CG.CREATED_DATE createdDate," 
				+ "CG.CREATED_USER_ID createdUserId,"
				+ "CG.APPROVAL_DATE approvalDate," 
				+ "CG.SIGN_DATE signDate," 
				+ "CG.SIGN_PLACE signPlace,"
				+ "CG.CONSTRUCT_ID constructId," 
				+ "CE1.FULL_NAME adirectorName," 
				+ "CE2.FULL_NAME amonitorName,"
				+ "CE3.FULL_NAME ainChargeMonitorName," 
				+ "CE4.FULL_NAME bdirectorName,"
				+ "CE5.FULL_NAME binChargeConstructName,"
				+ "VCH.CONSTRT_ADDRESS constrtAddress,"
				+ "VCH.CONSTRT_CODE constrtCode,"
				+ "VCH.CONSTRT_NAME constrtName,"
				+ "VCH.CONTRACT_NAME contractName,"
				+ "VCH.CONTRACT_CODE contractCode,"
				+ "VCH.STATION_CODE stationCode,"
				+ " CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId,"
				+ " ASM.COMMENTS comments "
				
				+ " FROM CONSTR_GROUND_HANDOVER CG "
				+ " INNER JOIN CAT_EMPLOYEE CE1 ON TO_NUMBER(CE1.ID) = CG.A_DIRECTOR_ID "
				+ " INNER JOIN CAT_EMPLOYEE CE2 ON TO_NUMBER(CE2.ID) = CG.A_MONITOR_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE3 ON TO_NUMBER(CE3.ID) = CG.A_IN_CHARGE_MONITOR_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE4 ON TO_NUMBER(CE4.ID) = CG.B_DIRECTOR_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE5 ON TO_NUMBER(CE5.ID) = CG.B_IN_CHARGE_CONSTRUCT_ID"
				+ " INNER JOIN V_CONSTRUCTION_HCQT VCH ON VCH.CONSTRUCT_ID = CG.CONSTRUCT_ID"
				+ " INNER JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON CCRM.DATA_TABLE_ID_VALUE = CG.CONSTR_GROUND_HANDOVER_ID AND CCRM.DATA_TABLE_NAME = 'CONSTR_GROUND_HANDOVER' "
				+ " LEFT JOIN APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2) "
				+ " WHERE 1=1 AND CG.IS_ACTIVE = 1 " + "AND CG.CONSTRUCT_ID =:constructId" 
				);

		if(null != dto.getConstrGroundHandoverId()){
			sql.append(" AND CG.CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId ");
		}
		if(null != dto.getContractId()){
			sql.append(" AND VCH.CONTRACT_ID = :contractId");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrGroundHandoverId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("handoverDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("aDirectorId", LongType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		
		query.addScalar("bDirectorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("groundCurrentStatus", StringType.INSTANCE);
		query.addScalar("benchmark", StringType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("adirectorName", StringType.INSTANCE);
		query.addScalar("amonitorName", StringType.INSTANCE);
		query.addScalar("ainChargeMonitorName", StringType.INSTANCE);
		query.addScalar("bdirectorName", StringType.INSTANCE);
		query.addScalar("binChargeConstructName", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.setParameter("constructId", dto.getConstructId());
		//query.setParameter("contractCode", dto.getContractCode());

		query.setResultTransformer(Transformers.aliasToBean(ConstrGroundHandoverDTO.class));
		
		if(null != dto.getConstrGroundHandoverId()){
			query.setParameter("constrGroundHandoverId", dto.getConstrGroundHandoverId());
		}
		if(null != dto.getContractId()){
			query.setParameter("contractId", dto.getContractId());
		}
		List<ConstrGroundHandoverDTO> list = query.list();
		return list;
	}

	
	
	
	@SuppressWarnings("unchecked")
	public ConstrGroundHandoverDTO getAllConstrGroundHandoverById(ConstrGroundHandoverDTO dto) {
		StringBuffer sql = new StringBuffer("SELECT " 
				+ "CG.CONSTR_GROUND_HANDOVER_ID constrGroundHandoverId, "
				+ "CG.CODE code," 
				+ "CG.A_DIRECTOR_ID aDirectorId," 
				+ "CG.A_MONITOR_ID aMonitorId,"
				+ "CG.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId," 
				+ "CG.B_DIRECTOR_ID bDirectorId,"
				+ "CG.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId," 
				+ "CG.STATUS_CA statusCa,"
				+ "CG.HANDOVER_DATE handoverDate," 
				+ "CG.GROUND_CURRENT_STATUS groundCurrentStatus,"
				+ "CG.BENCHMARK benchmark," 
				+ "CG.CREATED_DATE createdDate," 
				+ "CG.CREATED_USER_ID createdUserId,"
				+ "CG.APPROVAL_DATE approvalDate," 
				+ "CG.SIGN_DATE signDate," 
				+ "CG.SIGN_PLACE signPlace,"
				+ "CG.CONSTRUCT_ID constructId,"
				
/*				+ "CE1.FULL_NAME adirectorName,"
				+ "CE2.FULL_NAME amonitorName,"*/
				
				+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_DIRECTOR_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) adirectorName, " 
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_DIRECTOR_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) adirectorNamePath, " 
				+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_MONITOR_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) amonitorName, "
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_MONITOR_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) amonitorNamePath, "
				
				+ "CE3.FULL_NAME ainChargeMonitorName," 
				
				/*+ "CE4.FULL_NAME bdirectorName,"
				+ "CE5.FULL_NAME binChargeConstructName,"*/
				
				+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_DIRECTOR_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) bdirectorName, " 
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_DIRECTOR_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) bdirectorNamePath, " 
				+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) binChargeConstructName, "
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM CONSTR_GROUND_HANDOVER WHERE CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId)) binChargeConstructNamePath, "
				
				
				+ "VCH.CONSTRT_ADDRESS constrtAddress,"
				+ "VCH.CONSTRT_CODE constrtCode,"
				+ "VCH.CONSTRT_NAME constrtName,"
				+ "VCH.CONTRACT_NAME contractName,"
				+ "VCH.CONTRACT_CODE contractCode,"
				+ "VCH.STATION_CODE stationCode"
				+ " FROM CONSTR_GROUND_HANDOVER CG "
				
				/*+ " INNER JOIN CAT_EMPLOYEE CE1 ON TO_NUMBER(CE1.ID) = CG.A_DIRECTOR_ID "
				+ " INNER JOIN CAT_EMPLOYEE CE2 ON TO_NUMBER(CE2.ID) = CG.A_MONITOR_ID"*/
				
				+ " INNER JOIN CAT_EMPLOYEE CE3 ON TO_NUMBER(CE3.ID) = CG.A_IN_CHARGE_MONITOR_ID"
				
				/*+ " INNER JOIN CAT_EMPLOYEE CE4 ON TO_NUMBER(CE4.ID) = CG.B_DIRECTOR_ID"
				+ " INNER JOIN CAT_EMPLOYEE CE5 ON TO_NUMBER(CE5.ID) = CG.B_IN_CHARGE_CONSTRUCT_ID"*/
				
				+ " INNER JOIN V_CONSTRUCTION_HCQT VCH ON VCH.CONSTRUCT_ID = CG.CONSTRUCT_ID"
				+ " WHERE ROWNUM=1 AND CG.IS_ACTIVE = 1 " + "AND   CG.CONSTRUCT_ID =:constructId AND ROWNUM = 1 " );
		if(null != dto.getContractCode()){
			sql.append(" AND VCH.CONTRACT_CODE = :contractCode ");
		}

		if(null != dto.getConstrGroundHandoverId()){
			sql.append(" AND CG.CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId ");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		
		
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrGroundHandoverId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("handoverDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("aDirectorId", LongType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		query.addScalar("bDirectorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("groundCurrentStatus", StringType.INSTANCE);
		query.addScalar("benchmark", StringType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("adirectorName", StringType.INSTANCE);
		query.addScalar("adirectorNamePath", StringType.INSTANCE);
		query.addScalar("amonitorName", StringType.INSTANCE);
		query.addScalar("amonitorNamePath", StringType.INSTANCE);
		query.addScalar("ainChargeMonitorName", StringType.INSTANCE);
		
		query.addScalar("bdirectorName", StringType.INSTANCE);
		query.addScalar("bdirectorNamePath", StringType.INSTANCE);
		query.addScalar("binChargeConstructName", StringType.INSTANCE);
		query.addScalar("binChargeConstructNamePath", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);
		
		query.setParameter("constructId", dto.getConstructId());
		query.setParameter("type2", partnerAttachType);
		if(null != dto.getContractCode()){
			query.setParameter("contractCode", dto.getContractCode());
		}
		

		query.setResultTransformer(Transformers.aliasToBean(ConstrGroundHandoverDTO.class));
		
		if(null != dto.getConstrGroundHandoverId()){
			query.setParameter("constrGroundHandoverId", dto.getConstrGroundHandoverId());
		}
		
		return (ConstrGroundHandoverDTO) query.uniqueResult();
	}
	
	public  String getCode( String tableName, String value) {
		SQLQuery query = getSession().createSQLQuery(Joiner.on("").join("select get_next_code('", tableName, "', 'CODE','", value, "',6) code from dual"));
		query.addScalar("code", StandardBasicTypes.STRING);

		return (String) query.uniqueResult();
	}
	
	
	public boolean checkStatusDatabase(Long constrGroundHandoverId) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT SC.STATUS_CA status"
				+ " FROM CONSTR_GROUND_HANDOVER SC "
				+ " WHERE SC.CONSTR_GROUND_HANDOVER_ID = :constrGroundHandoverId");
				
		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("status", LongType.INSTANCE);
		query.setParameter("constrGroundHandoverId", constrGroundHandoverId);
		
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
						+ "where ca.CONSTR_COMP_RE_MAP_ID = :ccrmID and ca.EMPLOYEE_ID = :eID AND ROWNUM = 1");
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
					session.createSQLQuery("update CONSTR_GROUND_HANDOVER com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 2 "
							+ " where  com.CONSTR_GROUND_HANDOVER_ID = :id")
							.setParameter("appDate", new Date())
							.setParameter("id", obj.getConstrGroundHandoverId()).executeUpdate();
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
				session.createSQLQuery("update CONSTR_GROUND_HANDOVER com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 3 "
						+ " where  com.CONSTR_GROUND_HANDOVER_ID = :id")
						.setParameter("appDate", new Date())
						.setParameter("id", obj.getConstrGroundHandoverId()).executeUpdate();
				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
	
	
	@Transactional
	public boolean deleteOneEntity(Long id) {
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(
				"UPDATE CONSTR_GROUND_HANDOVER u SET u.IS_ACTIVE = 0 WHERE u.CONSTR_GROUND_HANDOVER_ID = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		session.createSQLQuery(
				"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'CONSTR_GROUND_HANDOVER' AND recod.DATA_TABLE_ID_VALUE = :id")
				.setParameter("id", id).executeUpdate();
		return true;
	}
	

}
