/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

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

import com.viettel.erp.bo.ConstrWorkLogsBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrWorkLogsDAO")
public class ConstrWorkLogsDAO extends BaseFWDAOImpl<ConstrWorkLogsBO, Long> {
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public ConstrWorkLogsDAO() {
		this.model = new ConstrWorkLogsBO();
	}

	public ConstrWorkLogsDAO(Session session) {
		this.session = session;
	}

	public String getStringSQL(ConstrWorkLogsDTO dto) {
		String sql = new String();
		if (dto.getEstimatesWorkItemId() != null) {
			sql = sql + " AND CW.ESTIMATES_WORK_ITEM_ID = " + dto.getEstimatesWorkItemId() + " ";
		}
		if (dto.getLogDate() != null) {
			java.sql.Date dte = new java.sql.Date(dto.getLogDate().getTime());
			sql = " AND CW.LOG_DATE = TO_DATE('" + dte + "', 'yyyy-mm-dd') ";
		}
		if (dto.getWorkContent() != null && !dto.getWorkContent().isEmpty()) {
			sql = sql + " AND CW.WORK_CONTENT = '" + dto.getWorkContent() + "' ";
		}
		if (dto.getAdditionChangeArise() != null && !dto.getAdditionChangeArise().isEmpty()) {
			sql = sql + " AND CW.ADDITION_CHANGE_ARISE = '" + dto.getAdditionChangeArise() + "' ";
		}
		if (dto.getContractorComments() != null && !dto.getContractorComments().isEmpty()) {
			sql = sql + " AND CW.CONTRACTOR_COMMENTS = '" + dto.getContractorComments() + "' ";
		}
		if (dto.getMonitorComments() != null && !dto.getMonitorComments().isEmpty()) {
			sql = sql + " AND CW.MONITOR_COMMENTS = '" + dto.getMonitorComments() + "' ";
		}
		if (dto.getAMonitorId() != null) {
			sql = sql + " AND CW.A_MONITOR_ID = " + dto.getAMonitorId() + " ";
		}
		if (dto.getBConstructId() != null) {
			sql = sql + " AND CW.B_CONSTRUCT_ID = " + dto.getBConstructId() + " ";
		}
		sql = sql + " and CW.construct_id= " + dto.getConstructId() + " AND CW.is_active=1 order by CW.CONSTR_WORK_LOGS_ID DESC";
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public List<ConstrWorkLogsDTO> getAllConstrWorkLogs(ConstrWorkLogsDTO dto) {
		StringBuffer sql = new StringBuffer("SELECT "
				+ "CW.CODE code,CW.CONSTR_WORK_LOGS_ID constrWorkLogsId, CW.LOG_DATE logDate, CW.WORK_CONTENT workContent, "
				+ "CW.ADDITION_CHANGE_ARISE additionChangeArise, CW.CONTRACTOR_COMMENTS contractorComments, "
				+ "CW.MONITOR_COMMENTS monitorComments ,CW.STATUS_CA statusCa, EW.WORK_ITEM_NAME workItemName, EW.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId, "
				+ "CW.A_MONITOR_ID aMonitorId,CW.created_User_Id createdUserId,ce2.FULL_NAME aMonitorName,ce3.FULL_NAME bConstructName, CW.B_CONSTRUCT_ID bConstructId, cw.construct_Id constructId ,cvc.constr_comp_re_map_id constrCompReMapId, ASM.COMMENTS comments "
				+ " FROM  CONSTR_WORK_LOGS CW " + "left jOIN ESTIMATES_WORK_ITEMS EW "
				+ " ON EW.ESTIMATES_WORK_ITEM_ID = CW.ESTIMATES_WORK_ITEM_ID "
				+ " join CAT_EMPLOYEE ce2  on ce2.ID = cw.A_MONITOR_ID "
				+ " join CAT_EMPLOYEE ce3 on ce3.ID = cw.B_CONSTRUCT_ID "
				+ " join CONSTR_COMPLETE_RECORDS_MAP cvc on cw.CONSTR_WORK_LOGS_ID = cvc.data_table_id_value and cvc.data_table_name = 'CONSTR_WORK_LOGS'"
				+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (cvc.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2) "
				+ " WHERE 1=1 ");
		sql = sql.append(getStringSQL(dto));
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("logDate", new DateType());
		query.addScalar("workContent", StringType.INSTANCE);
		query.addScalar("additionChangeArise", StringType.INSTANCE);
		query.addScalar("contractorComments", StringType.INSTANCE);
		query.addScalar("monitorComments", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constrWorkLogsId", LongType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("bConstructId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("aMonitorName", StringType.INSTANCE);
		query.addScalar("bConstructName", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConstrWorkLogsDTO.class));

		List<ConstrWorkLogsDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public Long updateDataTable(ConstrWorkLogsDTO obj) {
		StringBuffer sql = new StringBuffer(
				"update CONSTR_WORK_Logs CW set CW.LOG_DATE =:logDate,CW.WORK_CONTENT =:workContent,"
				+ " CW.ADDITION_CHANGE_ARISE= :additionChangeArise, CW.CONTRACTOR_COMMENTS =:contractorComments,"
				+ " CW.STATUS_CA =:statusCa,"		
				+ " CW.MONITOR_COMMENTS =:monitorComments,"
				+ "CW.A_MONITOR_ID =:aMonitorId, CW.B_CONSTRUCT_ID =:bConstructId " + "where constr_work_logs_id =:constrWorkLogsId ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("logDate", obj.getLogDate());
		query.setParameter("workContent", obj.getWorkContent());
		query.setParameter("contractorComments", obj.getContractorComments());
		query.setParameter("monitorComments", obj.getMonitorComments());
		query.setParameter("additionChangeArise", obj.getAdditionChangeArise());
		query.setParameter("aMonitorId", obj.getAMonitorId());
		query.setParameter("bConstructId", obj.getBConstructId());
		query.setParameter("constrWorkLogsId", obj.getConstrWorkLogsId());
		query.setParameter("statusCa", obj.getStatusCa());
		query.executeUpdate();
		return obj.getConstrWorkLogsId();
	}
	
	public boolean checkStatusDatabase(String constrWorkLogsId) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT CW.STATUS_CA statusCa" + " FROM CONSTR_WORK_LOGS CW  "
				+ " WHERE CW.CONSTR_WORK_LOGS_ID = :constrWorkLogsId");

		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("statusCa", LongType.INSTANCE);
		query.setParameter("constrWorkLogsId", constrWorkLogsId);

		Long statusCa = (Long) query.uniqueResult();
		if (statusCa == 0L || statusCa == 3L) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void deleteConstrWorkLogs(List<String> listConstrWorkLogsId) {
		String list = new String(" IN (");
		for (String constrWorkLogsId : listConstrWorkLogsId) {
			list = list + constrWorkLogsId + ",";
		}
		list = list.substring(0, list.length() - 1);
		list = list + ") ";

		StringBuffer sql = new StringBuffer(
				"update CONSTR_WORK_Logs " + "set is_active = 0 " + "where constr_work_logs_id");
		sql.append(list);
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.executeUpdate();
		StringBuffer sqlConstr= new StringBuffer("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'CONSTR_WORK_LOGS' AND recod.DATA_TABLE_ID_VALUE ");
		sqlConstr.append(list);
		SQLQuery queryConstr = getSession().createSQLQuery(sqlConstr.toString());
		queryConstr.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<EstimatesWorkItemsDTO> getEstimatesWork(String constructId) {
		StringBuffer sql = new StringBuffer(
				"select ewi.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId,ewi.WORK_ITEM_NAME workItemName,ewi.WORK_ITEM_CODE workItemCode "
						+ " from ESTIMATES_WORK_ITEMS ewi " + " JOIN CONSTR_ESTIMATE_INFO cei "
						+ " ON ewi.CONSTR_ESTIMATE_INFO_ID = cei.CONSTR_ESTIMATE_INFO_ID "
						+ " JOIN CONSTR_CONSTRUCTIONS cc " + " ON cei.CONSTRUCTION_ID = cc.CONSTRUCT_ID "
						+ " WHERE ewi.PROGRESS_TYPE = 3 " + " and cc.CONSTRUCT_ID = :constructId ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("workItemName", StringType.INSTANCE);
		query.addScalar("workItemCode", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(EstimatesWorkItemsDTO.class));
		query.setParameter("constructId", constructId);

		List<EstimatesWorkItemsDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('constr_work_logs', 'CODE','NKCT',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	public ConstrWorkLogsDTO findById(Long constrWorkLogsId, String contractCode) {
		StringBuffer selectValue = new StringBuffer(
				"SELECT CW.code code,CW.LOG_DATE logDate, CW.WORK_CONTENT workContent, CW.Status_CA statusCa,CW.CONSTR_WORK_LOGS_ID constrWorkLogsId,"
						+ "V_CONSTRUCTION_HCQT.STATION_CODE stationCode,"
						+ "CW.ADDITION_CHANGE_ARISE additionChangeArise, CW.CONTRACTOR_COMMENTS contractorComments, "
						+ "CW.MONITOR_COMMENTS monitorComments, ce2.FULL_NAME aMonitorName, ce3.FULL_NAME bConstructName, "
						+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
						+ " (SELECT B_CONSTRUCT_ID FROM CONSTR_WORK_LOGS WHERE CONSTR_WORK_LOGS_ID = :constrWorkLogsId)) bConstructPath, "
						+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
						+ "(SELECT A_MONITOR_ID FROM CONSTR_WORK_LOGS WHERE CONSTR_WORK_LOGS_ID =:constrWorkLogsId)) aMonitorPath "
						+ "FROM CONSTR_WORK_LOGS CW "
						+ " join CAT_EMPLOYEE ce2  on ce2.ID = cw.A_MONITOR_ID "
						+ " join CAT_EMPLOYEE ce3 on ce3.ID = cw.B_CONSTRUCT_ID "
						+ "inner join V_CONSTRUCTION_HCQT on V_CONSTRUCTION_HCQT.CONSTRUCT_ID = CW.CONSTRUCT_ID ");
		if (!"".equals(contractCode)) {
			selectValue.append("and V_CONSTRUCTION_HCQT.CONTRACT_CODE= :contractCode");
		}
		selectValue.append(" WHERE CW.CONSTR_WORK_LOGS_ID = :constrWorkLogsId");
		selectValue.append(" AND ROWNUM=1 ");
		SQLQuery query = getSession().createSQLQuery(selectValue.toString());

		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("logDate", new DateType());
		query.addScalar("workContent", StringType.INSTANCE);
		query.addScalar("additionChangeArise", StringType.INSTANCE);
		query.addScalar("contractorComments", StringType.INSTANCE);
		query.addScalar("monitorComments", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("bConstructPath", StringType.INSTANCE);
		query.addScalar("aMonitorPath", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constrWorkLogsId", LongType.INSTANCE);
		query.addScalar("aMonitorName", StringType.INSTANCE);
		query.addScalar("bConstructName", StringType.INSTANCE);
		if (!"".equals(contractCode)) {
			query.setParameter("contractCode", contractCode);
		}
		query.setParameter("type2", partnerAttachType);
		
		query.setResultTransformer(Transformers.aliasToBean(ConstrWorkLogsDTO.class));
		query.setParameter("constrWorkLogsId", constrWorkLogsId);

		return (ConstrWorkLogsDTO) query.uniqueResult();
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
			if (obj.getStatusCa() == 2) {
				if (appCheck.getIsLast() == 1) {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();
					//////////////Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery("update CONSTR_WORK_LOGS com set com.APPROVAL_DATE = :appDate, com.STATUS_CA  = 2 "
							+ " where  com.CONSTR_WORK_LOGS_ID = :id").setParameter("appDate", new Date()).setParameter("id", obj.getConstrWorkLogsId())
							.executeUpdate();
					///////////////////////////////////////////////////
				} else {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.LEVEL_ORDER = :lvod "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id")
							.setParameter("lvod", appCheck.getLevelOrder() + 1)
							.setParameter("id", obj.getConstrCompReMapId()).executeUpdate();
				}
				session.createSQLQuery("update APPROVAL_SIGN_MANAGEMENT asm set asm.APPROVAL_STATUS = 1, asm.APPROVAL_DATE=:appDate "
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
				session.createSQLQuery("update CONSTR_WORK_LOGS com set com.APPROVAL_DATE = :appDate, com.STATUS_CA = 3 " 
						+ " where com.CONSTR_WORK_LOGS_ID = :id").setParameter("appDate", new Date()).setParameter("id",
						 obj.getConstrWorkLogsId())
						 .executeUpdate();
				
				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
	
}
