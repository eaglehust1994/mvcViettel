/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.ConstrWorkLogsLabelBO;
import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.ConstrWorkLogsLabelDTO;
import com.viettel.erp.dto.VConstrConstructionsDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrWorkLogsLabelDAO")
public class ConstrWorkLogsLabelDAO extends BaseFWDAOImpl<ConstrWorkLogsLabelBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public ConstrWorkLogsLabelDAO() {
		this.model = new ConstrWorkLogsLabelBO();
	}

	public ConstrWorkLogsLabelDAO(Session session) {
		this.session = session;
	}

	public List<ConstrWorkLogsLabelDTO> getAllBia(Long constructId) {
		StringBuffer sqlBuffer = new StringBuffer("select cw.code code, " + "cw.STATUS_CA statusCa, "
				+ "cw.construct_Id constructId, " + "cw.A_DIRECTOR_ID aDirectorId, " + "cw.A_MONITOR_ID aMonitorId, "
				+ "cw.B_CONSTRUCT_ID bConstructId, " + "cw.B_DIRECTOR_ID bDirectorId, "
				+ "cw.CONSTR_WO_LOGS_LAB_ID constrWoLogsLabId, " + "cc.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
				+ " ce1.FULL_NAME aDirectorName,ce2.FULL_NAME aMonitorName, cw.CREATED_USER_ID createdUserId, "
				+ " ce3.FULL_NAME bConstructName, ce4.FULL_NAME bDirectorName, " 
				+ "ASM.COMMENTS comments "
				+ "from CONSTR_WORK_LOGS_LABEL cw "
				+ "left join CONSTR_COMPLETE_RECORDS_MAP cc " + "on cc.DATA_TABLE_ID_VALUE = cw.CONSTR_WO_LOGS_LAB_ID "
				+ " INNER join CAT_EMPLOYEE ce1 on ce1.ID = cw.A_DIRECTOR_ID INNER join CAT_EMPLOYEE ce2  on ce2.ID = cw.A_MONITOR_ID "
				+ " INNER join CAT_EMPLOYEE ce3 on ce3.ID = cw.B_CONSTRUCT_ID  INNER join CAT_EMPLOYEE ce4 on ce4.ID = cw.B_DIRECTOR_ID "
				+ "and cc.DATA_TABLE_NAME = 'CONSTR_WORK_LOGS_LABEL' " 
				+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (cc.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2)    "
				+ " where CONSTRUCT_ID = :constructId");

		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("constrCompReMapId", new LongType());
		query.addScalar("constrWoLogsLabId", new LongType());
		query.addScalar("constructId", new LongType());
		query.addScalar("statusCa", new LongType());
		query.addScalar("aDirectorId", new LongType());
		query.addScalar("aMonitorId", new LongType());
		query.addScalar("bConstructId", new LongType());
		query.addScalar("bDirectorId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("createdUserId", new LongType());

		query.addScalar("aDirectorName", new StringType());
		query.addScalar("aMonitorName", new StringType());
		query.addScalar("bConstructName", new StringType());
		query.addScalar("bDirectorName", new StringType());
		query.addScalar("comments", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ConstrWorkLogsLabelDTO.class));
		query.setParameter("constructId", constructId);

		List<ConstrWorkLogsLabelDTO> list = query.list();
		return list;
	}

	public boolean checkStatusDatabaseLabel(String constructId) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT CW.STATUS_CA statusCa" + " FROM CONSTR_WORK_LOGS_LABEL CW  "
				+ " WHERE CW.CONSTRUCT_ID = :constructId");

		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("statusCa", LongType.INSTANCE);
		query.setParameter("constructId", constructId);

		Long statusCa = (Long) query.uniqueResult();
		if (statusCa == 0L || statusCa == 3L) {
			return false;
		} else {
			return true;
		}
	}

	public Long updateLabel(ConstrWorkLogsLabelDTO dto) {
		StringBuffer sql = new StringBuffer(
				"update CONSTR_WORK_LOGS_LABEL set A_MONITOR_ID  =:aMonitorId, A_DIRECTOR_ID =:aDirectorId , B_CONSTRUCT_ID =:bConstructId, B_DIRECTOR_ID =:bDirectorId where CONSTRUCT_ID = :constructId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("aMonitorId", dto.getAMonitorId());
		query.setParameter("aDirectorId", dto.getADirectorId());
		query.setParameter("bConstructId", dto.getBConstructId());
		query.setParameter("bDirectorId", dto.getBDirectorId());
		query.setParameter("constructId", dto.getConstructId());

		query.executeUpdate();
		return 1l;
	}

	@SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('CONSTR_WORK_LOGS_LABEL', 'CODE','QLHC_TKBNK',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	public boolean checkBia(Long constructId) {
		StringBuffer sql = new StringBuffer(
				"select CONSTR_WO_LOGS_LAB_ID constrWoLogsLabId from CONSTR_WORK_LOGS_LABEL where CONSTRUCT_ID = :constructId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("constrWoLogsLabId", new LongType());
		query.setParameter("constructId", constructId);
		Long id = (Long) query.uniqueResult();
		if (id == null || id == 0l) {
			return false;
		}
		return true;
	}

	public VConstructionHcqtDTO findById(Long constructId, String contractCode) {
		StringBuffer selectValue = new StringBuffer(
				"select cc.CONTRACT_CODE contractCode, cc.CONSTRT_NAME constrtName , "
						+ "cc.SIGNED_DATE signed_date, cc.PROVINCE_NAME provinceName, "
						+ "cc.SUPERVISOR_NAME supervisorName , cc.CONSTRUCTOR_NAME constructorName, cw.Status_ca CaStatus, "

						+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
						+ " (SELECT A_DIRECTOR_ID  FROM CONSTR_WORK_LOGS_LABEL WHERE CONSTRUCT_ID = :constructId)) aDirectorPath, "
						+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
						+ "(SELECT A_MONITOR_ID FROM CONSTR_WORK_LOGS_LABEL WHERE CONSTRUCT_ID = :constructId)) aMonitorPath, ce1.FULL_NAME aDirectorName,ce2.FULL_NAME aMonitorName, ce3.FULL_NAME bConstructName, ce4.FULL_NAME bDirectorName,"

						+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
						+ " (SELECT B_CONSTRUCT_ID  FROM CONSTR_WORK_LOGS_LABEL WHERE CONSTRUCT_ID = :constructId)) bConstructPath, "
						+ " (SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
						+ "(SELECT B_DIRECTOR_ID FROM CONSTR_WORK_LOGS_LABEL WHERE CONSTRUCT_ID = :constructId)) bDirectorPath "
						+ " from V_CONSTRUCTION_HCQT cc left join CONSTR_WORK_LOGS_LABEL cw on cw.CONSTRUCT_ID = cc.CONSTRUCT_ID "
						+ " INNER join CAT_EMPLOYEE ce1 on ce1.ID = cw.A_DIRECTOR_ID INNER join CAT_EMPLOYEE ce2  on ce2.ID = cw.A_MONITOR_ID "
						+ " INNER join CAT_EMPLOYEE ce3 on ce3.ID = cw.B_CONSTRUCT_ID  INNER join CAT_EMPLOYEE ce4 on ce4.ID = cw.B_DIRECTOR_ID "
						+ " WHERE ROWNUM = 1 AND cc.CONSTRUCT_ID = :constructId AND ROWNUM = 1 ");
		if (StringUtils.isNotEmpty(contractCode)) {
			selectValue.append("and cc.CONTRACT_CODE= :contractCode");
		}
		SQLQuery query = getSession().createSQLQuery(selectValue.toString());

		query.addScalar("contractCode", new StringType());
		query.addScalar("signed_date", new DateType());
		query.addScalar("constrtName", new StringType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("supervisorName", new StringType());
		query.addScalar("constructorName", new StringType());

		query.addScalar("aMonitorPath", new StringType());
		query.addScalar("bConstructPath", new StringType());
		query.addScalar("aDirectorPath", new StringType());
		query.addScalar("bDirectorPath", new StringType());
		query.addScalar("CaStatus", new LongType());
		
		query.addScalar("aDirectorName", new StringType());
		query.addScalar("aMonitorName", new StringType());
		query.addScalar("bConstructName", new StringType());
		query.addScalar("bDirectorName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(VConstructionHcqtDTO.class));
		query.setParameter("constructId", constructId);
		if (StringUtils.isNotEmpty(contractCode)) {
			query.setParameter("contractCode", contractCode);
		}
		query.setParameter("type2", partnerAttachType);

		return (VConstructionHcqtDTO) query.uniqueResult();
	}

	public approDTO getCheckData(approDTO obj) {
		StringBuffer sql = new StringBuffer(
				"SELECT cr.LEVEL_ORDER levelOrder, ca.APPROVAL_ORDER approvalOrder, ca.IS_LAST isLast "
						+ "from APPROVAL_SIGN_MANAGEMENT ca " + "inner join CONSTR_COMPLETE_RECORDS_MAP cr "
						+ "on ca.CONSTR_COMP_RE_MAP_ID = cr.CONSTR_COMP_RE_MAP_ID "
						+ "where ca.CONSTR_COMP_RE_MAP_ID = :ccrmID and ca.EMPLOYEE_ID = :eID AND ROWNUM = 1 ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("levelOrder", new LongType());
		query.addScalar("approvalOrder", new LongType());
		query.addScalar("isLast", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(approDTO.class));
		query.setParameter("ccrmID", obj.getConstrCompReMapId());
		query.setParameter("eID", obj.getEmployeeId());
		return (approDTO) query.uniqueResult();
	}

	@Transactional
	public Long appro(approDTO obj) {
		Long resp = 0l;
		approDTO appCheck = getCheckData(obj);
		if (appCheck == null) {
			return new Long(5);
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

					////////////// Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery(
							"update CONSTR_WORK_LOGS_LABEL com set com.APPROVAL_DATE = :appDate, com.STATUS_CA  = 2 "
									+ " where  com.CONSTR_WO_LOGS_LAB_ID = :id")
							.setParameter("appDate", new Date()).setParameter("id", obj.getConstrWoLogsLabId())
							.executeUpdate();
					///////////////////////////////////////////////////
				} else {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.LEVEL_ORDER = :lvod "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id")
							.setParameter("lvod", appCheck.getLevelOrder() + 1)
							.setParameter("id", obj.getConstrCompReMapId()).executeUpdate();
				}
				session.createSQLQuery("update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 1, asm.APPROVAL_DATE=:appDate"
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

				////////////// Thay đổi thành tên bảng và id của bảng mình
				session.createSQLQuery(
						"update CONSTR_WORK_LOGS_LABEL com set com.APPROVAL_DATE = :appDate, com.STATUS_CA = 3 "
								+ " where com.CONSTR_WO_LOGS_LAB_ID = :id")
						.setParameter("appDate", new Date()).setParameter("id", obj.getConstrWoLogsLabId())
						.executeUpdate();

				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}

}
