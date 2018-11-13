/*
+" * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.QualityCableMeaReportBO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.QualityCableMeaReportDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("qualityCableMeaReportDAO")
public class QualityCableMeaReportDAO extends BaseFWDAOImpl<QualityCableMeaReportBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public QualityCableMeaReportDAO() {
		this.model = new QualityCableMeaReportBO();
	}

	public QualityCableMeaReportDAO(Session session) {
		this.session = session;
	}

	// load thong tin chung
	@SuppressWarnings("unchecked")
	public List<QualityCableMeaReportModelDTO> getQualityReportList() {
		StringBuilder sql = new StringBuilder("SELECT "
				+ "VC.CONSTRUCT_ID constructId,VC.CONSTRT_CODE constrtCode,VC.CONTRACT_CODE contractCode,WIA.STATUS_CA statusCa,QC.CODE code,CNA.CONTRACT_NAME contractName "
				+ "FROM  QUALITY_CABLE_MEA_REPORT QC "
				+ "INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = QC.CONSTRUCT_ID "
				+ "INNER JOIN WORK_ITEMS_ACCEPTANCE WIA ON VC.CONSTRUCT_ID = WIA.CONSTRUCT_ID "
				+ "INNER JOIN CNT_CONTRACT CNA ON CNA.CONTRACT_ID = VC.CONTRACT_ID ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(QualityCableMeaReportModelDTO.class));

		List<QualityCableMeaReportModelDTO> list = query.list();
		return list;
	}

	// load phan phan quyen
	@SuppressWarnings("unchecked")
	public List<QualityCableMeaReportModelDTO> findByConstructId(
			QualityCableMeaReportModelDTO qualityCableMeaReportModelDTO) {
		StringBuffer sql = new StringBuffer(
				"SELECT " + "QC.QUALITY_CABLE_MEA_REPORT_ID qualityCableMeaReportId,VC.CONSTRUCT_ID constructId,"
						+ "VC.CONSTRT_CODE constrtCode,VC.CONTRACT_CODE contractCode,QC.STATUS_CA statusCa,QC.CREATED_DATE createdDate,"
						+ "QC.CODE code,VC.CONTRACT_NAME contractName,QC.CREATED_USER_ID createdUserId,"
						+ "QC.SIGN_DATE signDate,QC.SIGN_PLACE signPlace,"
						+ "VC.CONSTRUCTOR_NAME constructorName,E.FULL_NAME afullName,"
						+ "CE.FULL_NAME bfullName,VC.CONSTRT_NAME constrtName,VC.STATION_CODE stationCode,"
						+ "VC.CONTRACT_ID contractId,"
						+ "QC.A_MONITOR_ID aMonitorId,QC.B_CONSTRUCT_ID bConstructId,QC.IS_ACTIVE isActive,"
						+ "cvc.constr_comp_re_map_id constrCompReMapId, " 
						+ "ASM.COMMENTS comments "
						+ "FROM  QUALITY_CABLE_MEA_REPORT QC "
						+ "INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = QC.CONSTRUCT_ID "
						+ "INNER JOIN CAT_EMPLOYEE E ON TO_NUMBER(E.ID) = QC.A_MONITOR_ID "
						+ "INNER JOIN CAT_EMPLOYEE CE ON TO_NUMBER(CE.ID) = QC.B_CONSTRUCT_ID "
						+ "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP cvc "
						+ "on QC.QUALITY_CABLE_MEA_REPORT_ID = cvc.data_table_id_value "
						+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (cvc.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2) "
						+ " WHERE QC.IS_ACTIVE = 1 AND VC.CONSTRUCT_ID = :constructId AND VC.CONTRACT_ID = :contractId AND cvc.DATA_TABLE_NAME='QUALITY_CABLE_MEA_REPORT'"
						+ " order by QC.QUALITY_CABLE_MEA_REPORT_ID DESC");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("qualityCableMeaReportId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("contractId", LongType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("constructorName", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("bConstructId", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("afullName", StringType.INSTANCE);
		query.addScalar("bfullName", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("signDate", new DateType());
		query.addScalar("createdDate", new DateType());

		query.setParameter("constructId", qualityCableMeaReportModelDTO.getConstructId());
		query.setParameter("contractId", qualityCableMeaReportModelDTO.getContractId());
		query.setResultTransformer(Transformers.aliasToBean(QualityCableMeaReportModelDTO.class));

		List<QualityCableMeaReportModelDTO> list = query.list();
		return list;
	}

	public List<CatEmployeeDTO> getListEmployeeByRole(SettlementRightDTO rightDTO) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName,CAT_EMPLOYEE.ID id" + " from CAT_EMPLOYEE "
					+ "inner join SETTLEMENT_RIGHT " + "on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID "
					+ "inner join V_CONSTRUCTION_HCQT "
					+ "on V_CONSTRUCTION_HCQT.CONSTRUCT_ID = SETTLEMENT_RIGHT.CONSTRUCT_ID" + " inner join ROLE_CA "
					+ "on ROLE_CA.ROLEID = SETTLEMENT_RIGHT.ROLEID"
					+ " WHERE V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :constructId");
			// + rightDTO.getConstructId());
			// + " AND V_CONSTRUCTION_HCQT.CONTRACT_CODE = '"+
			// rightDTO.getContractCode()+"' ");

			if (null != rightDTO.getRoleid()) {
				sql.append(" AND ROLE_CA.ROLEID = :roleid");
			}

			if (StringUtils.isNotEmpty(rightDTO.getKeySearch())) {
				sql.append(" AND upper(CAT_EMPLOYEE.FULL_NAME) Like upper(:name)");
			}

			if (StringUtils.isNotEmpty(rightDTO.getContractCode())) {
				sql.append(" AND V_CONSTRUCTION_HCQT.CONTRACT_CODE = :contractCode");
				// + ""+ "'"+rightDTO.getContractCode()+"' ");
			}

			sql.append(" order by SETTLEMENT_RIGHT.IS_CURRENT DESC");
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameter("contractCode", rightDTO.getContractCode());
			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);

			if (StringUtils.isNotEmpty(rightDTO.getKeySearch())) {
				query.setParameter("name", "%" + rightDTO.getKeySearch() + "%");
			}

			if (null != rightDTO.getRoleid()) {
				query.setParameter("roleid", rightDTO.getRoleid());
			}
			query.setParameter("constructId", rightDTO.getConstructId());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<CatEmployeeDTO> getAllListEmployeeByRole(SettlementRightDTO rightDTO) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName,CAT_EMPLOYEE.ID id" + " from CAT_EMPLOYEE "
					+ "inner join SETTLEMENT_RIGHT " + "on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID "
					+ "inner join V_CONSTRUCTION_HCQT "
					+ "on V_CONSTRUCTION_HCQT.CONSTRUCT_ID = SETTLEMENT_RIGHT.CONSTRUCT_ID" + " inner join ROLE_CA "
					+ "on ROLE_CA.ROLEID = SETTLEMENT_RIGHT.ROLEID"
					+ " where V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :constructId");
			// + "order by SETTLEMENT_RIGHT.IS_CURRENT DESC");
			if (StringUtils.isNotEmpty(rightDTO.getContractCode())) {
				sql.append(" AND V_CONSTRUCTION_HCQT.CONTRACT_CODE = :contractCode");
			}
			sql.append(" order by SETTLEMENT_RIGHT.IS_CURRENT DESC");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);

			query.setParameter("constructId", rightDTO.getConstructId());
			query.setParameter("contractCode", rightDTO.getContractCode());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<QualityCableMeaResultDTO> getListQualityResualt(Long qualityCableMeaReportId) {
		StringBuilder sql = new StringBuilder("SELECT "
				+ "QC.QUALITY_CABLE_MEA_RESULT_ID qualityCableMeaResultId,QC.QUALITY_CABLE_MEA_REPORT_ID qualityCableMeaReportId,QC.OBJECT_CHECKING objectChecking,QC.LENGTH length,QC.ATTENUATION_LENGTH attenuationLength,QC.ATTENUATION_DEGREE attenuationDegree,QC.ATTENUATION_SUM attenuationSum,QC.ATTENUATION_AVERAGE attenuationAverage,QC.NOTE note"
				+ " FROM  QUALITY_CABLE_MEA_RESULT QC"
				+ " INNER JOIN QUALITY_CABLE_MEA_REPORT QCR ON QCR.QUALITY_CABLE_MEA_REPORT_ID = QC.QUALITY_CABLE_MEA_REPORT_ID"
				+ " Where QC.QUALITY_CABLE_MEA_REPORT_ID = :qualityCableMeaReportId"
				+ " order by QC.QUALITY_CABLE_MEA_RESULT_ID DESC");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("objectChecking", StringType.INSTANCE);
		query.addScalar("length", DoubleType.INSTANCE);
		query.addScalar("attenuationLength", DoubleType.INSTANCE);
		query.addScalar("attenuationDegree", DoubleType.INSTANCE);
		query.addScalar("attenuationSum", DoubleType.INSTANCE);
		query.addScalar("attenuationAverage", DoubleType.INSTANCE);
		query.addScalar("qualityCableMeaReportId", LongType.INSTANCE);
		query.addScalar("qualityCableMeaResultId", LongType.INSTANCE);
		query.addScalar("note", StringType.INSTANCE);

		query.setParameter("qualityCableMeaReportId", qualityCableMeaReportId);

		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setResultTransformer(Transformers.aliasToBean(QualityCableMeaResultDTO.class));

		List<QualityCableMeaResultDTO> list = query.list();
		return list;
	}
	// ,QC.QUALITY_CABLE_MEA_REPORT_ID
	// qualityCableMeaReportId,QC.QUALITY_CABLE_MEA_RESULT_ID
	// qualityCableMeaResultId

	public boolean saveOrUpdate(QualityCableMeaReportDTO targetObject) {
		Session session = getSession();
		List<QualityCableMeaResultDTO> listC = targetObject.getQualityCableMeaResult();
		for (int i = 0; i < listC.size(); i++) {
			if (listC.get(i).getQualityCableMeaReportId() != null) {
				// update
				session.update(listC.get(i).toModel());
			} else {
				// create
				listC.get(i).setQualityCableMeaReportId(targetObject.getQualityCableMeaReportId());
				session.save(listC.get(i).toModel());
			}
		}
		targetObject.setQualityCableMeaResult(new ArrayList<QualityCableMeaResultDTO>());
		session.update(targetObject.toModel());
		return true;
	}

	public boolean qualitySaveOrUpdate(QualityCableMeaReportDTO targetObject) {
		Session session = getSession();
		List<QualityCableMeaResultDTO> listC = targetObject.getQualityCableMeaResult();
		for (int i = 0; i < listC.size(); i++) {
			if (listC.get(i).getQualityCableMeaReportId() != null) {
				// update
				session.update(listC.get(i).toModel());
			} else {
				// create
				listC.get(i).setQualityCableMeaReportId(targetObject.getQualityCableMeaReportId());
				session.save(listC.get(i).toModel());
			}
		}
		targetObject.setQualityCableMeaResult(new ArrayList<QualityCableMeaResultDTO>());
		session.save(targetObject.toModel());
		return true;

	}

	@SuppressWarnings("unchecked")
	public boolean deleteResult(List<String> listString) {
			String list = new String(" IN (");
			for (String constrWorkLogsId : listString) {
				list = list + constrWorkLogsId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuffer sql = new StringBuffer("DELETE QUALITY_CABLE_MEA_RESULT where QUALITY_CABLE_MEA_RESULT_ID");
			sql.append(list);
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteReport(List<String> listReportID) {
			String list = new String(" IN (");
			for (String constrWorkLogsId : listReportID) {
				list = list + constrWorkLogsId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuffer sql = new StringBuffer(
					"update QUALITY_CABLE_MEA_REPORT " + "set is_active = 0 " + "where QUALITY_CABLE_MEA_REPORT_ID");
			sql.append(list);
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
			StringBuffer sqlConstr = new StringBuffer(
					"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'QUALITY_CABLE_MEA_REPORT' AND recod.DATA_TABLE_ID_VALUE ");
			sqlConstr.append(list);
			SQLQuery queryConstr = getSession().createSQLQuery(sqlConstr.toString());
			queryConstr.executeUpdate();
			return true;
	}

	@SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('QUALITY_CABLE_MEA_REPORT', 'CODE','QLHC_CLTC',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public QualityCableMeaReportModelDTO getQualityReport(QualityCableMeaReportModelDTO dto) {
			StringBuffer sql = new StringBuffer(
					"SELECT " + "QC.QUALITY_CABLE_MEA_REPORT_ID qualityCableMeaReportId,VC.CONSTRUCT_ID constructId,"
							+ "VC.CONSTRT_CODE constrtCode,VC.CONTRACT_CODE contractCode,VC.STATION_CODE stationCode,"
							+ "QC.SIGN_DATE signDate,QC.SIGN_PLACE signPlace,"
							+ "QC.CODE code,VC.CONTRACT_NAME contractName,QC.STATUS_CA statusCa,"
							+ "VC.CONSTRUCTOR_NAME constructorName,VC.CONSTRT_NAME constrtName, "
							+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = "
							+ "(SELECT A_MONITOR_ID FROM QUALITY_CABLE_MEA_REPORT WHERE QUALITY_CABLE_MEA_REPORT_ID = :qualityCableMeaReportId"
							+ ")) afullName, " + "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID ="
							+ "(SELECT B_CONSTRUCT_ID FROM QUALITY_CABLE_MEA_REPORT WHERE QUALITY_CABLE_MEA_REPORT_ID = :qualityCableMeaReportId"
							+ ")) bfullName, "
							+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
							+ "(SELECT A_MONITOR_ID FROM QUALITY_CABLE_MEA_REPORT WHERE QUALITY_CABLE_MEA_REPORT_ID = :qualityCableMeaReportId"
							+ ")) aMonitorPath, "
							+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
							+ "(SELECT B_CONSTRUCT_ID FROM QUALITY_CABLE_MEA_REPORT WHERE QUALITY_CABLE_MEA_REPORT_ID = :qualityCableMeaReportId"
							+ ")) bConstructPath " + "FROM  QUALITY_CABLE_MEA_REPORT QC "
							+ "INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = QC.CONSTRUCT_ID "
							+ "WHERE QC.IS_ACTIVE = 1 AND QC.QUALITY_CABLE_MEA_REPORT_ID = :qualityCableMeaReportId AND ROWNUM = 1" );

			if (dto.getContractId() !=  null) {
				sql.append(" AND VC.CONTRACT_ID = :contractId");
			}

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("qualityCableMeaReportId", LongType.INSTANCE);
			query.addScalar("constructId", LongType.INSTANCE);
			query.addScalar("constrtCode", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);
			query.addScalar("constructorName", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("contractName", StringType.INSTANCE);
			query.addScalar("afullName", StringType.INSTANCE);
			query.addScalar("bfullName", StringType.INSTANCE);
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("signDate", new DateType());
			query.addScalar("aMonitorPath", StringType.INSTANCE);
			query.addScalar("bConstructPath", StringType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);
			query.setParameter("qualityCableMeaReportId", dto.getQualityCableMeaReportId());
			if(dto.getContractId() != null){
				query.setParameter("contractId", dto.getContractId());
			}
			query.setParameter("type2", partnerAttachType);

			query.setResultTransformer(Transformers.aliasToBean(QualityCableMeaReportModelDTO.class));
			return (QualityCableMeaReportModelDTO) query.uniqueResult();

	}

	// -------------------- phe duyet---------------------------
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
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2"
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();

					////////////// Thay Ä‘á»•i thÃ nh tÃªn báº£ng vÃ  id cá»§a
					////////////// báº£ng mÃ¬nh
					session.createSQLQuery("update QUALITY_CABLE_MEA_REPORT com set com.STATUS_CA  = 2 "
							+ " where  com.QUALITY_CABLE_MEA_REPORT_ID = :id")
							.setParameter("id", obj.getQualityCableMeaReportId()).executeUpdate();
					///////////////////////////////////////////////////
				} else {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.LEVEL_ORDER = :lvod "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id")
							.setParameter("lvod", appCheck.getLevelOrder() + 1)
							.setParameter("id", obj.getConstrCompReMapId()).executeUpdate();
				}
				session.createSQLQuery(
						"update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 1, asm.APPROVAL_DATE=:appDate "
								+ " , asm.COMMENTS = :cmm where asm.EMPLOYEE_ID = :employeeId  AND asm.CONSTR_COMP_RE_MAP_ID  = :ccrmId ")
						.setParameter("employeeId", obj.getEmployeeId()).setParameter("cmm", obj.getComments())
						.setParameter("appDate", new Date()).setParameter("ccrmId", obj.getConstrCompReMapId())
						.executeUpdate();
				resp = 3l;
			} else {
				session.createSQLQuery("update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 2, asm.APPROVAL_DATE=:appDate "
						+ " , asm.COMMENTS = :cmm where asm.EMPLOYEE_ID = :employeeId  AND asm.CONSTR_COMP_RE_MAP_ID  = :ccrmId ")
						.setParameter("employeeId", obj.getEmployeeId()).setParameter("cmm", obj.getComments()).setParameter("appDate", new Date())
						.setParameter("ccrmId", obj.getConstrCompReMapId()).executeUpdate();
				session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 3 "
						+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
						.executeUpdate();

				////////////// Thay Ä‘á»•i thÃ nh tÃªn báº£ng vÃ  id cá»§a
				////////////// báº£ng mÃ¬nh
				session.createSQLQuery("update QUALITY_CABLE_MEA_REPORT com set com.STATUS_CA = 3 "
						+ " where com.QUALITY_CABLE_MEA_REPORT_ID = :id")
						.setParameter("id", obj.getQualityCableMeaReportId()).executeUpdate();

				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}

	///// trinh duyet///////////////
	@Transactional
	public Long saveTable(QualityCableMeaReportDTO completionDrawing) throws Exception {
		Session session = getSession();
		Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
		completionDrawing.getConstrCompleteRecordMap().setDataTableIdValue(completionDrawingId);
		session.save(completionDrawing.getConstrCompleteRecordMap().toModel());
		return completionDrawingId;
	}

	@SuppressWarnings("unchecked")
	public String getUpdateConstrCompleteRecod(Long qualityID, String nameTable) {
		StringBuffer sql = new StringBuffer("UPDATE CONSTR_COMPLETE_RECORDS_MAP set STATUS = 0,LEVEL_ORDER = 1 "
				+ "where DATA_TABLE_NAME = :nameTable AND DATA_TABLE_ID_VALUE = :qualityID");
		SQLQuery queryConstr = getSession().createSQLQuery(sql.toString());
		queryConstr.setParameter("nameTable", nameTable);
		queryConstr.setParameter("qualityID", qualityID);
		queryConstr.executeUpdate();
		return null;
	}

}
