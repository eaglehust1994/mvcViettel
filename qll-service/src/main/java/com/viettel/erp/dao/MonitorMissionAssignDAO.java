/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.MonitorMissionAssignBO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.MonitorMissionAssignDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("monitorMissionAssignDAO")
public class MonitorMissionAssignDAO extends BaseFWDAOImpl<MonitorMissionAssignBO, Long> {
	
	@Value("${monitorMissionAssign.attachType}")
    private Long attachTypeMonitor;
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

    public MonitorMissionAssignDAO() {
        this.model = new MonitorMissionAssignBO();
    }

    public MonitorMissionAssignDAO(Session session) {
        this.session = session;
    }
    public List<MonitorMissionAssignDTO> getMonitorMissionAssign(MonitorMissionAssignDTO obj){
    	StringBuilder sqlbuilder = new StringBuilder(
				"SELECT V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode, MONITOR_MISSION_ASSIGN.MONITOR_MISSION_ASSIGN_ID monitorMissionAssignId, MONITOR_MISSION_ASSIGN.CODE code, V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode, V_CONSTRUCTION_HCQT.CONTRACT_NAME contractName, "
						+ " MONITOR_MISSION_ASSIGN.STATUS_CA statusCa, MONITOR_MISSION_ASSIGN.CONSTRUCT_ID constructId, "
						+ " UTIL_ATTACHED_DOCUMENTS.DOCUMENT_PATH documentPath, UTIL_ATTACHED_DOCUMENTS.DOCUMENT_NAME documentName, "
						+ " MONITOR_MISSION_ASSIGN.A_MONITOR_ID aMonitorId, MONITOR_MISSION_ASSIGN.A_DIRECTOR_ID aDirectorId, MONITOR_MISSION_ASSIGN.CREATED_DATE createdDate, MONITOR_MISSION_ASSIGN.CREATED_USER_ID createdUserId, "
						+ " MONITOR_MISSION_ASSIGN.SIGN_DATE signDate, MONITOR_MISSION_ASSIGN.SIGN_PLACE signPlace, MONITOR_MISSION_ASSIGN.MISSION_DATE missionDate, MONITOR_MISSION_ASSIGN.MONITOR_DOCUMENT monitorDocument, "
						+ " MONITOR_MISSION_ASSIGN.ASSIGN_NOTE assignNote, MONITOR_MISSION_ASSIGN.APPROVAL_DATE approvalDate, "
						+ " UTIL_ATTACHED_DOCUMENTS.ATTACH_ID attachId, "
						+ " CONSTR_COMPLETE_RECORDS_MAP.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
						+ " ASM.COMMENTS comments "
						+ " FROM MONITOR_MISSION_ASSIGN "
						+ " INNER JOIN CONSTR_COMPLETE_RECORDS_MAP ON MONITOR_MISSION_ASSIGN.MONITOR_MISSION_ASSIGN_ID = CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_ID_VALUE "
						+ " INNER JOIN V_CONSTRUCTION_HCQT ON V_CONSTRUCTION_HCQT.CONSTRUCT_ID = MONITOR_MISSION_ASSIGN.CONSTRUCT_ID "
						+ "   LEFT JOIN  APPROVAL_SIGN_MANAGEMENT ASM ON (CONSTR_COMPLETE_RECORDS_MAP.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2) "
						+ " LEFT JOIN UTIL_ATTACHED_DOCUMENTS ON MONITOR_MISSION_ASSIGN.MONITOR_MISSION_ASSIGN_ID = UTIL_ATTACHED_DOCUMENTS.PARENT_ID "
						+ " WHERE UTIL_ATTACHED_DOCUMENTS.TYPE = :type1 "
						+ " AND MONITOR_MISSION_ASSIGN.IS_ACTIVE = 1 "
						+ " AND CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_NAME = 'MONITOR_MISSION_ASSIGN' ");

		if (StringUtils.isNotEmpty(obj.getConstrtCode())) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONSTRT_CODE = :constrtCode ");
		}
		if (obj.getMonitorMissionAssignId()!=null){
			sqlbuilder.append(" AND MONITOR_MISSION_ASSIGN.MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId ");
		}
		if (obj.getConstructId() != null) {
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :constructId ");
		}
		if (obj.getContractId() != null){
			sqlbuilder.append(" AND V_CONSTRUCTION_HCQT.CONTRACT_ID = :contractId ");
		}
		sqlbuilder.append(" ORDER BY MONITOR_MISSION_ASSIGN.MONITOR_MISSION_ASSIGN_ID DESC ");
		SQLQuery query = getSession().createSQLQuery(sqlbuilder.toString());
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("monitorMissionAssignId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("documentPath", StringType.INSTANCE);
		query.addScalar("documentName", StringType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("aDirectorId", LongType.INSTANCE);
		query.addScalar("attachId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("signDate", new DateType());
		query.addScalar("missionDate", new DateType());
		query.addScalar("approvalDate", new DateType());
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("monitorDocument", StringType.INSTANCE);
		query.addScalar("assignNote", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		
		
		// query.setParameter("roll", a);
		query.setResultTransformer(Transformers.aliasToBean(MonitorMissionAssignDTO.class));

		if (StringUtils.isNotEmpty(obj.getConstrtCode())) {
			query.setParameter("constrtCode", obj.getConstrtCode());
		}
		if (obj.getConstructId()!= null) {
			query.setParameter("constructId", obj.getConstructId());
		}
		
		if (obj.getMonitorMissionAssignId()!=null){
			query.setParameter("monitorMissionAssignId", obj.getMonitorMissionAssignId());
		}
		if (obj.getContractId() != null){
			query.setParameter("contractId", obj.getContractId());
		}
		
		query.setParameter("type1", attachTypeMonitor);
		
		return query.list();
    }
    public MonitorMissionAssignDTO getDataExport(MonitorMissionAssignDTO dto){
			StringBuilder sql = new StringBuilder(); 
					sql.append("select "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constrtAddress, "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode, "
					
					+ "MMA.MONITOR_MISSION_ASSIGN_ID monitorMissionAssignId, "
					+ "MMA.CONSTRUCT_ID constructId, "
					+ "MMA.A_DIRECTOR_ID aDirectorId,  "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_DIRECTOR_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId"+")) aDirectorIdName, " 
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_DIRECTOR_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId "+")) aDirectorIdNamePath, "
					+ "(SELECT ROLENAME FROM ROLE_CA WHERE ROLEID = (SELECT ROLEID FROM SETTLEMENT_RIGHT WHERE ROWNUM = 1 AND EMPLOYEE_ID = (SELECT A_DIRECTOR_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId) AND CONSTRUCT_ID = (SELECT CONSTRUCT_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId))) aDirectorIdRoleName , "
					+ "MMA.A_MONITOR_ID aMonitorId, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_MONITOR_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId"+")) aMonitorIdName, " 
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_MONITOR_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId "+")) aMonitorIdNamePath, "
					+ "(SELECT ROLENAME FROM ROLE_CA WHERE ROLEID = (SELECT ROLEID FROM SETTLEMENT_RIGHT WHERE ROWNUM = 1 AND EMPLOYEE_ID = (SELECT A_MONITOR_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId) AND CONSTRUCT_ID = (SELECT CONSTRUCT_ID FROM MONITOR_MISSION_ASSIGN WHERE MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId))) aMonitorIdRoleName , "
					+ "MMA.SIGN_DATE signDate, "
					+ "MMA.MISSION_DATE missionDate, "
					+ "MMA.ASSIGN_NOTE assignNote, "
					+ "MMA.MONITOR_DOCUMENT monitorDocument, "
					+ "MMA.SIGN_PLACE signPlace ,"
					+ "MMA.STATUS_CA statusCa "
					
					+ "FROM MONITOR_MISSION_ASSIGN MMA "
					+ "inner join V_CONSTRUCTION_HCQT on V_CONSTRUCTION_HCQT.CONSTRUCT_ID = MMA.CONSTRUCT_ID "
					+ "where MMA.MONITOR_MISSION_ASSIGN_ID = :monitorMissionAssignId ");					
					SQLQuery query = getSession().createSQLQuery(sql.toString());
					
					query.setParameter("monitorMissionAssignId", dto.getMonitorMissionAssignId());
					
					query.addScalar("constrtAddress", StringType.INSTANCE);
					query.addScalar("constrtCode", StringType.INSTANCE);
					
					query.addScalar("monitorMissionAssignId", LongType.INSTANCE);
					query.addScalar("constructId", LongType.INSTANCE);
					query.addScalar("aDirectorId", LongType.INSTANCE);
					query.addScalar("aDirectorIdName", StringType.INSTANCE);
					query.addScalar("aDirectorIdNamePath", StringType.INSTANCE);
					query.addScalar("aDirectorIdRoleName", StringType.INSTANCE);
					query.addScalar("aMonitorId", LongType.INSTANCE);
					query.addScalar("aMonitorIdName", StringType.INSTANCE);
					query.addScalar("aMonitorIdNamePath", StringType.INSTANCE);
					query.addScalar("aMonitorIdRoleName", StringType.INSTANCE);
					query.addScalar("signDate", DateType.INSTANCE);
					query.addScalar("missionDate", DateType.INSTANCE);
					query.addScalar("assignNote", StringType.INSTANCE);
					query.addScalar("signPlace", StringType.INSTANCE);
					query.addScalar("monitorDocument", StringType.INSTANCE);
					query.addScalar("statusCa", LongType.INSTANCE);
					
					query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
					query.setResultTransformer(Transformers.aliasToBean(MonitorMissionAssignDTO.class));
					
					query.setParameter("type2", partnerAttachType);

					return (MonitorMissionAssignDTO) query.list().get(0);
    }
    
    public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('MONITOR_MISSION_ASSIGN', 'CODE','GNVGS',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}
    @Transactional
    public Long saveTable( MonitorMissionAssignDTO  monitorMissionAssign) throws Exception{
    	      Session session = getSession();
    	      Long monitorMissionAssignId = (Long) session.save(monitorMissionAssign.toModel());
    	      monitorMissionAssign.getConstrCompleteRecordMap().setDataTableIdValue(monitorMissionAssignId);     
    	      session.save(monitorMissionAssign.getConstrCompleteRecordMap().toModel());
    	      return monitorMissionAssignId;
     }
    
    public boolean updateIsActive(List<Long> monitorMissionAssignId){
    		Session session = getSession();
    		String listUpdate = new String(" IN (");
    		for (Long id : monitorMissionAssignId) {
    			listUpdate = listUpdate + id + ",";
    		}
    		listUpdate = listUpdate.substring(0, listUpdate.length() - 1);
    		listUpdate = listUpdate + ") ";
			StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE MONITOR_MISSION_ASSIGN SET MONITOR_MISSION_ASSIGN.IS_ACTIVE = '0' ");
    		sql.append("WHERE MONITOR_MISSION_ASSIGN.MONITOR_MISSION_ASSIGN_ID " + listUpdate);
    		session.createSQLQuery(sql.toString()).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'MONITOR_MISSION_ASSIGN_ID' "
    				+ "AND DATA_TABLE_ID_VALUE " + listUpdate).executeUpdate();
			return true;
    }
    
    
    public approDTO getCheckData(approDTO obj) {
		StringBuffer sql = new StringBuffer(
				"SELECT cr.LEVEL_ORDER levelOrder,ca.EMPLOYEE_ID employeeId,cr.CREATED_USER_ID createUserId, ca.APPROVAL_ORDER approvalOrder, ca.IS_LAST isLast "
						+ "from APPROVAL_SIGN_MANAGEMENT ca " + "inner join CONSTR_COMPLETE_RECORDS_MAP cr "
						+ "on ca.CONSTR_COMP_RE_MAP_ID = cr.CONSTR_COMP_RE_MAP_ID "
						+ "where ca.CONSTR_COMP_RE_MAP_ID = :ccrmID and ca.EMPLOYEE_ID = :eID AND ROWNUM = 1 ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("levelOrder", LongType.INSTANCE);
		query.addScalar("employeeId", LongType.INSTANCE);
		query.addScalar("createUserId", LongType.INSTANCE);
		query.addScalar("approvalOrder", LongType.INSTANCE);
		query.addScalar("isLast", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(approDTO.class));
		query.setParameter("ccrmID", obj.getConstrCompReMapId());
		query.setParameter("eID", obj.getEmployeeId());
		return (approDTO) query.uniqueResult();
	}

    @Transactional
	public Long appro(approDTO obj) {
		Long resp = 0l;
		approDTO appCheck = getCheckData(obj);
		if(appCheck == null) {
			return 5l;
		}
		Session session = getSession();
		if (appCheck.getLevelOrder() < appCheck.getApprovalOrder()) {
			System.out.println("Chua den luot duyet");
			resp = 1l;
		} else if (appCheck.getLevelOrder() > appCheck.getApprovalOrder()) {
			System.out.println("Da duoc duyet roi");
			resp = 2l;
		} else if (appCheck.getLevelOrder() == appCheck.getApprovalOrder()) {
			if (obj.getStatusCa() == 1) {
				if (appCheck.getIsLast() == 1) {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();
					
					//////////////Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery("update MONITOR_MISSION_ASSIGN com set com.APPROVAL_DATE = :appDate, com.STATUS_CA  = 2 "
							+ " where  com.MONITOR_MISSION_ASSIGN_ID = :id").setParameter("appDate", new Date()).setParameter("id", obj.getConstrWorkLogsId())
							.executeUpdate();
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
				session.createSQLQuery("update MONITOR_MISSION_ASSIGN com set com.APPROVAL_DATE = :appDate, com.STATUS_CA = 3 " 
						+ " where com.MONITOR_MISSION_ASSIGN_ID = :id").setParameter("appDate", new Date()).setParameter("id",
						 obj.getConstrWorkLogsId())
						 .executeUpdate();
				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
}
