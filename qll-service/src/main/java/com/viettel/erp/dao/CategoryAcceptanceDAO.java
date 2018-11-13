/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.CategoryAcceptanceBO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.CategoryAcceptanceDTO;
import com.viettel.erp.dto.CategoryAcceptanceExtDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.util.ParamUtils;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("categoryAcceptanceDAO")
public class CategoryAcceptanceDAO extends BaseFWDAOImpl<CategoryAcceptanceBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	private static Logger LOGGER = LoggerFactory.getLogger(CategoryAcceptanceDAO.class);

	public CategoryAcceptanceDAO() {
		this.model = new CategoryAcceptanceBO();
	}

	public CategoryAcceptanceDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<CategoryAcceptanceExtDTO> getAllCategoryAcceptance() {
		String sql = "select CA.CATEGORY_ACCEPTANCE_ID categoryAcceptanceId,CA.CODE code, EIC.ITEMS_CODE itemCode, EIC.ITEM_NAME itemName, CA.CONSTRUCT_ID constructId, "
				+ "V_C_HCQT.CONTRACT_CODE contractCode, V_C_HCQT.CONTRACT_NAME contractName, CA.STATUS_CA statusCa "
				+ "from CATEGORY_ACCEPTANCE CA INNER JOIN ESTIMATES_ITEMS_CHILD EIC on CA.ESTIMATES_ITEM_CHILD_ID = EIC.ESTIMATES_ITEM_CHILD_ID "
				+ "INNER JOIN V_CONSTRUCTION_HCQT V_C_HCQT on CA.CONSTRUCT_ID = V_C_HCQT.CONSTRUCT_ID where CA.IS_ACTIVE=1";
		StringBuilder sqlBuilder = new StringBuilder(sql);
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("categoryAcceptanceId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("itemCode", StringType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(CategoryAcceptanceExtDTO.class));

		// LOGGER.info("List Category Acceptance:"+query.list().size());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('CATEGORY_ACCEPTANCE', 'CODE','DSNT_GDCT',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	@Transactional
	public boolean deleteCategoryAcceptanceList(List<Long> lisItemCode) {
			Session session = getSession();
//			String list = new String("");
//			for (Long itemCode : lisItemCode) {
//				list = list + itemCode + ",";
//			}
//			list = list.substring(0, list.length() - 1);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE CATEGORY_ACCEPTANCE CA SET CA.IS_ACTIVE = '0' ");
			sql.append("WHERE CA.CATEGORY_ACCEPTANCE_ID IN(:list)");
			session.createSQLQuery(sql.toString()).setParameterList("list", lisItemCode).executeUpdate();
			session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'CATEGORY_ACCEPTANCE_ID' "
					+ "AND DATA_TABLE_ID_VALUE IN (:list)").setParameterList("list", lisItemCode).executeUpdate();
			return true;
	}

	public List<CategoryAcceptanceDTO> getCategoryAcceptanceById(Long constructId, Double contractId) {
		String sql = "select CA.CATEGORY_ACCEPTANCE_ID categoryAcceptanceId,"
				+ "CA.CODE code, "
				+ "EIC.ITEMS_CODE itemCode, " 
				+ "EIC.ITEM_NAME itemName, "
				+ "CA.CONSTRUCT_ID constructId, " 
				+ "CA.A_MONITOR_ID aMonitorId, "
				+ "CA.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId, "
				+ "CA.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId, " 
				+ "V_C_HCQT.CONTRACT_CODE contractCode, "
				+ "V_C_HCQT.CONTRACT_NAME contractName, "
				+ "V_C_HCQT.CONSTRT_CODE constrtCode, " 
				+ "CCRM.CONSTR_COMP_RE_MAP_ID constrcompreMapId, "
				+ "CA.CONCLUSION conclusion, "
				+ "CA.STATUS_CA statusCa, "
				+ "CA.CREATED_USER_ID createdUserId, "
				+ "ASM.COMMENTS comments "
				+ "from CATEGORY_ACCEPTANCE CA INNER JOIN ESTIMATES_ITEMS_CHILD EIC on CA.ESTIMATES_ITEM_CHILD_ID = EIC.ESTIMATES_ITEM_CHILD_ID "
				+ "INNER JOIN V_CONSTRUCTION_HCQT V_C_HCQT on CA.CONSTRUCT_ID = V_C_HCQT.CONSTRUCT_ID "
				+ "LEFT JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM on CA.CATEGORY_ACCEPTANCE_ID = CCRM.DATA_TABLE_ID_VALUE AND CCRM.DATA_TABLE_NAME = 'CATEGORY_ACCEPTANCE' "
				+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2) "
				+ " where CA.IS_ACTIVE=1 AND CA.CONSTRUCT_ID =:constructId AND V_C_HCQT.IS_ACTIVE=1 ";
		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (contractId != null){
			sqlBuilder.append(" AND V_C_HCQT.CONTRACT_ID = :contractId ");
		}
		sqlBuilder.append(" ORDER BY CA.CATEGORY_ACCEPTANCE_ID DESC ");
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("categoryAcceptanceId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("itemCode", StringType.INSTANCE);
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("constrcompreMapId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		
		if (constructId != null) {
			query.setParameter("constructId", constructId);
		}
		if (contractId != null){
			query.setParameter("contractId", contractId);
		}
		query.setResultTransformer(Transformers.aliasToBean(CategoryAcceptanceDTO.class));

		// LOGGER.info("List Category Acceptance:"+query.list().size());
		return query.list();
	}

	public CategoryAcceptanceDTO getCategoryAcceptanceByIdDetail(Long categoryAcceptanceId) {
		String sql = " SELECT CA.CATEGORY_ACCEPTANCE_ID categoryAcceptanceId," 
				+ " CA.CODE code,"
				+ " CA.A_MONITOR_ID aMonitorId," 
				+ " CA.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId,"
				+ " CA.ACCEPT_FROM_DATE acceptFromDate," 
				+ " CA.ACCEPT_TO_DATE acceptToDate,"
				+ " CA.ACCEPT_PLACE acceptPlace," 
				+ " CA.APPLY_BENCHMARK applyBenchmark,"
				+ " CA.CONSTRUCTION_QUALITY constructionQuality," 
				+ " CA.OTHER_DOCUMENTS otherDocuments,"
				+ " CA.OTHER_COMMENTS otherComments," 
				+ " CA.CONCLUSION conclusion,"
				+ " CA.COMPLETE_REQUEST completeRequest," 
				+ " CA.CREATED_DATE createdDate,"
				+ " CA.CREATED_USER_ID createdUserId," 
				+ " CA.APPROVAL_DATE approvalDate," 
				+ " CA.STATUS_CA statusCa,"
				+ " CA.IS_ACTIVE isActive," 
				+ " CA.SIGN_DATE signDate," 
				+ " CA.SIGN_PLACE signPlace," 
				+ " CA.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId,"
				+ " V_C_HCQT.CONTRACT_CODE contractCode," 
				+ " V_C_HCQT.CONSTRT_ADDRESS constructAddress,"
				+ " V_C_HCQT.CONTRACT_NAME contractName,"
				+ " V_C_HCQT.CONSTRT_CODE constrtCode," 
				+ " CA.CONSTRUCT_ID constructId,"
				+ " V_C_HCQT.CONSTRT_NAME constructName," 
				+ " V_C_HCQT.SIGNED_DATE contractDateSign,"
				+ " CEM.FULL_NAME amonitorName," 
				+ " CE.FULL_NAME binChargeConstructName,"
				+ " EIC.ITEM_NAME estimatesItemChildName" 
				+ " FROM CATEGORY_ACCEPTANCE CA"
				+ " INNER JOIN V_CONSTRUCTION_HCQT V_C_HCQT on CA.CONSTRUCT_ID = V_C_HCQT.CONSTRUCT_ID "
				+ " INNER JOIN CAT_EMPLOYEE CEM on CA.A_MONITOR_ID = CEM.ID "
				+ " INNER JOIN CAT_EMPLOYEE CE on CA.B_IN_CHARGE_CONSTRUCT_ID = CE.ID "
				+ " INNER JOIN ESTIMATES_ITEMS_CHILD EIC on CA.ESTIMATES_ITEM_CHILD_ID = EIC.ESTIMATES_ITEM_CHILD_ID"
				+ " where CA.IS_ACTIVE=1 AND CA.CATEGORY_ACCEPTANCE_ID =:categoryAcceptanceId AND ROWNUM = 1";
		StringBuilder sqlBuilder = new StringBuilder(sql);
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("categoryAcceptanceId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("acceptFromDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptToDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptPlace", StringType.INSTANCE);
		query.addScalar("applyBenchmark", StringType.INSTANCE);
		query.addScalar("constructionQuality", StringType.INSTANCE);
		query.addScalar("otherDocuments", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);
		query.addScalar("completeRequest", StringType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("constructAddress", StringType.INSTANCE);
		query.addScalar("constructName", StringType.INSTANCE);
		query.addScalar("contractDateSign", StandardBasicTypes.TIMESTAMP);
		query.addScalar("amonitorName", StringType.INSTANCE);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("binChargeConstructName", StringType.INSTANCE);
		query.addScalar("estimatesItemChildName", StringType.INSTANCE);

		if (categoryAcceptanceId != null) {
			query.setParameter("categoryAcceptanceId", categoryAcceptanceId);
		}
		query.setResultTransformer(Transformers.aliasToBean(CategoryAcceptanceDTO.class));

		// LOGGER.info("List Category Acceptance:"+query.list().size());
		return (CategoryAcceptanceDTO) query.uniqueResult();
	}

	public CategoryAcceptanceDTO getAllCategoryAcceptanceExportFile(CategoryAcceptanceDTO obj) {
		String sql = " SELECT CA.CATEGORY_ACCEPTANCE_ID categoryAcceptanceId," 
				+ " CA.CODE code,"
				+ " CA.A_MONITOR_ID aMonitorId," 
				+ " CA.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId,"
				+ " CA.ACCEPT_FROM_DATE acceptFromDate," 
				+ " CA.ACCEPT_TO_DATE acceptToDate,"
				+ " CA.ACCEPT_PLACE acceptPlace," 
				+ " CA.APPLY_BENCHMARK applyBenchmark,"
				+ " CA.CONSTRUCTION_QUALITY constructionQuality," 
				+ " CA.OTHER_DOCUMENTS otherDocuments,"
				+ " CA.OTHER_COMMENTS otherComments," 
				+ " CA.CONCLUSION conclusion,"
				+ " CA.COMPLETE_REQUEST completeRequest," 
				+ " CA.CREATED_DATE createdDate,"
				+ " CA.CREATED_USER_ID createdUserId," 
				+ " CA.APPROVAL_DATE approvalDate," 
				+ " CA.STATUS_CA statusCa,"
				+ " CA.IS_ACTIVE isActive,"
				+ " CA.SIGN_DATE signDate," 
				+ " CA.SIGN_PLACE signPlace," 
				+ " CA.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId,"
				+ " V_C_HCQT.CONTRACT_CODE contractCode," 
				+ " V_C_HCQT.CONSTRT_ADDRESS constructAddress,"
				+ " V_C_HCQT.CONTRACT_NAME contractName,"
				+ " V_C_HCQT.STATION_CODE stationCode," 
				+ " CA.CONSTRUCT_ID constructId,"
				+ " V_C_HCQT.CONSTRT_NAME constructName," 
				+ " V_C_HCQT.SIGNED_DATE contractDateSign,"
				
				+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_MONITOR_ID FROM CATEGORY_ACCEPTANCE WHERE CATEGORY_ACCEPTANCE_ID = :categoryId"+")) amonitorName, " 
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_MONITOR_ID FROM CATEGORY_ACCEPTANCE WHERE CATEGORY_ACCEPTANCE_ID = :categoryId"+")) amonitorNamePath, " 
				+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM CATEGORY_ACCEPTANCE WHERE CATEGORY_ACCEPTANCE_ID = :categoryId"+")) binChargeConstructName, "
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM CATEGORY_ACCEPTANCE WHERE CATEGORY_ACCEPTANCE_ID = :categoryId"+")) binChargeConstructNamePath, "
				
				+ " EIC.ITEM_NAME estimatesItemChildName" 
				+ " FROM CATEGORY_ACCEPTANCE CA"
				+ " INNER JOIN V_CONSTRUCTION_HCQT V_C_HCQT on CA.CONSTRUCT_ID = V_C_HCQT.CONSTRUCT_ID "
				+ " INNER JOIN ESTIMATES_ITEMS_CHILD EIC on CA.ESTIMATES_ITEM_CHILD_ID = EIC.ESTIMATES_ITEM_CHILD_ID"
				+ " where CA.IS_ACTIVE=1 AND ROWNUM = 1 AND CA.CATEGORY_ACCEPTANCE_ID =:categoryId";

		StringBuilder sqlBuilder = new StringBuilder(sql);
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("categoryAcceptanceId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("aMonitorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("acceptFromDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptToDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptPlace", StringType.INSTANCE);
		query.addScalar("applyBenchmark", StringType.INSTANCE);
		query.addScalar("constructionQuality", StringType.INSTANCE);
		query.addScalar("otherDocuments", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("completeRequest", StringType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("constructAddress", StringType.INSTANCE);
		query.addScalar("constructName", StringType.INSTANCE);
		query.addScalar("contractDateSign", StandardBasicTypes.TIMESTAMP);
		
		query.addScalar("amonitorName", StringType.INSTANCE);
		query.addScalar("amonitorNamePath", StringType.INSTANCE);
		query.addScalar("binChargeConstructName", StringType.INSTANCE);
		query.addScalar("binChargeConstructNamePath", StringType.INSTANCE);
		
		query.addScalar("estimatesItemChildName", StringType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.setParameter("categoryId",  obj.getCategoryAcceptanceId());
		query.setParameter("type2", partnerAttachType);

		query.setResultTransformer(Transformers.aliasToBean(CategoryAcceptanceDTO.class));
		return (CategoryAcceptanceDTO) query.uniqueResult();
	}

	// -------------------- phe duyet---------------------------
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
		Long resp = 0l;
		approDTO appCheck = getCheckData(obj);
		if(appCheck == null) {
			return 5l;
		}
		Session session = getSession();
		if (appCheck.getLevelOrder() < appCheck.getApprovalOrder()) {
			resp = 1l;
		} else if (appCheck.getLevelOrder() > appCheck.getApprovalOrder()) {
			resp = 2l;
		} else if (appCheck.getLevelOrder() == appCheck.getApprovalOrder()) {
			if (obj.getStatusCa() == 2) {
				if (appCheck.getIsLast() == 1) {
					
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();

					////////////// Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery("update CATEGORY_ACCEPTANCE com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 2 "
							+ " where  com.CATEGORY_ACCEPTANCE_ID = :id").setParameter("appDate", new Date())
							.setParameter("id", obj.getCategoryAcceptanceId()).executeUpdate();
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

				////////////// Thay đổi thành tên bảng và id của bảng mình

				session.createSQLQuery("update CATEGORY_ACCEPTANCE com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 3 "
						+ " where  com.CATEGORY_ACCEPTANCE_ID = :id").setParameter("appDate", new Date()).setParameter("id", obj.getCategoryAcceptanceId())
						.executeUpdate();

				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}

	@Transactional
    public Long saveTable( CategoryAcceptanceDTO  completionDrawing) throws Exception{
           Session session = getSession();
           Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
           completionDrawing.getConstrCompleteRecordsMap().setDataTableIdValue(completionDrawingId);     
           session.save(completionDrawing.getConstrCompleteRecordsMap().toModel());
           return completionDrawingId;
     }   

}
