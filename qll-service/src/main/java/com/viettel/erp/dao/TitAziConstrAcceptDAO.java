/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
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

import com.viettel.erp.bo.TitAziConstrAcceptBO;
import com.viettel.erp.bo.TitAziConstrAcceptListBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("titAziConstrAcceptDAO")
public class TitAziConstrAcceptDAO extends BaseFWDAOImpl<TitAziConstrAcceptBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public TitAziConstrAcceptDAO() {
		this.model = new TitAziConstrAcceptBO();
	}

	public TitAziConstrAcceptDAO(Session session) {
		this.session = session;
	}

	/*
	 * public List<TitAziConstrAcceptBO> getListById(Long constructId){
	 * TitAziConstrAcceptDTO dto= new TitAziConstrAcceptDTO(); Query
	 * q=getSession().createQuery("SELECT " + "t.code," +
	 * "t.titAziConstrAcceptId," + "t.constrConstructions.constrtCode," +
	 * "t.constrConstructions.contractCode," +
	 * "t.constrConstructions.contractName," + "t.statusCa " +
	 * "FROM titaziconstraccept t " +
	 * "WHERE t.constrConstructions.constructId = :constructId");
	 * q.setParameter("constructId", constructId); return
	 * q.setCacheable(true).list(); }
	 */

	@SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('TIT_AZI_CONSTR_ACCEPT', 'CODE','HSHC_NTHT',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<TitAziConstrAcceptDTO> findByConstructId(TitAziConstrAcceptDTO dto) {
		StringBuilder sql = new StringBuilder("SELECT " + "bang1.TIT_AZI_CONSTR_ACCEPT_ID titAziConstrAcceptId,"
				+ "bang1.CODE code," + "bang1.A_DIRECTOR_ID adirectorId, "
				+ "bang1.A_IN_CHARGE_MONITOR_ID ainchargemonitorid, " + "bang1.B_DIRECTOR_ID bdirectorId ,"
				+ "bang1.B_IN_CHARGE_CONSTRUCT_ID binchargeConstructId," + "bang1.ACCEPT_FROM_DATE acceptFromDate,"
				+ "bang1.ACCEPT_TO_DATE acceptToDate," + "bang1.ACCEPT_PLACE acceptPlace,"
				+ "bang1.APPLY_BENCHMARK applyBenchmark," + "bang1.CONSTRUCTION_QUALITY constructionQuality,"
				+ "bang1.OTHER_DOCUMENTS otherDocuments," + "bang1.COMPLETE_REQUEST completeRequest,"
				+ "bang1.CREATED_DATE createdDate," + "bang1.CREATED_USER_ID createdUserId,"
				+ "bang1.APPROVAL_DATE approvalDate," + "bang1.OTHER_COMMENTS otherComments,"
				+ "bang1.CONCLUSION conclusion," + "bang1.IS_ACTIVE isActive," + "bang1.SIGN_DATE signDate,"
				+ "bang1.SIGN_PLACE signPlace," + "bang2.CONSTRT_CODE constrtCode,"
				+ "bang2.CONTRACT_CODE contractCode," + "bang2.CONTRACT_NAME contractName,"
				+ "bang1.STATUS_CA statusCa," + "bang2.CONSTRUCT_ID constructId, "
				+ "cvc.constr_comp_re_map_id constrCompReMapId , " 
				+ "ASM.COMMENTS comments "
				+ "FROM TIT_AZI_CONSTR_ACCEPT bang1 "
				+ "join CONSTR_COMPLETE_RECORDS_MAP cvc ON bang1.TIT_AZI_CONSTR_ACCEPT_ID = cvc.data_table_id_value and cvc.data_table_name = 'TIT_AZI_CONSTR_ACCEPT' "
				+ "INNER JOIN V_CONSTRUCTION_HCQT bang2 ON bang2.CONSTRUCT_ID = bang1.CONSTRUCT_ID "
				+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (cvc.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2) "
				+ "WHERE bang1.IS_ACTIVE = 1 AND bang2.CONSTRUCT_ID = :constructId AND bang2.CONTRACT_ID = :contractId");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("titAziConstrAcceptId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);

		query.addScalar("adirectorId", LongType.INSTANCE);
		query.addScalar("ainchargemonitorid", LongType.INSTANCE);
		query.addScalar("bdirectorId", LongType.INSTANCE);
		query.addScalar("binchargeConstructId", LongType.INSTANCE);

		query.addScalar("acceptFromDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptToDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptPlace", StringType.INSTANCE);
		query.addScalar("applyBenchmark", StringType.INSTANCE);
		query.addScalar("constructionQuality", StringType.INSTANCE);
		query.addScalar("otherDocuments", StringType.INSTANCE);
		query.addScalar("completeRequest", StringType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);

		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		
		query.setParameter("constructId", dto.getConstructId());
		query.setParameter("contractId", dto.getContractId());
		
		query.setResultTransformer(Transformers.aliasToBean(TitAziConstrAcceptDTO.class));

		List<TitAziConstrAcceptDTO> list = query.list();

		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean deleteList(List<String> listId) {
			String list = new String(" IN (");
			for (String constrWorkLogsId : listId) {
				list = list + constrWorkLogsId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuffer sql = new StringBuffer(
					"UPDATE TIT_AZI_CONSTR_ACCEPT c SET c.IS_ACTIVE = 0 WHERE c.TIT_AZI_CONSTR_ACCEPT_ID");
			sql.append(list);
			System.out.println(sql.toString());
			System.out.println(listId.size());
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
			return true;
	}

	public List<TitAziConstrAcceptListBO> getListbangcon(Long titAziConstrAcceptId) {
		TitAziConstrAcceptListDTO dto = new TitAziConstrAcceptListDTO();
		dto.setTitAziConAcceptListId(titAziConstrAcceptId);
		Query q = getSession().createQuery(
				"from titaziconstracceptlist t where t.titAziConstrAccept.titAziConstrAcceptId = :titAziConstrAcceptId");
		q.setParameter("titAziConstrAcceptId", dto.getTitAziConAcceptListId());

		return q.setCacheable(true).list();
	}

	public boolean pheduyet(TitAziConstrAcceptDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("update " + " TIT_AZI_CONSTR_ACCEPT set STATUS_CA= :statusCa ");
			sql.append(" where TIT_AZI_CONSTR_ACCEPT_ID= :titAziConstrAcceptId ");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameter("statusCa", dto.getStatusCa());
			query.setParameter("titAziConstrAcceptId", dto.getTitAziConstrAcceptId());
			query.executeUpdate();
			return true;
	}

	@SuppressWarnings("unchecked")
	public TitAziConstrAcceptDTO getExportTitAzi(TitAziConstrAcceptDTO dto) {
			StringBuffer sql = new StringBuffer("SELECT "

					+ "VC.CONSTRUCT_ID constructId," + "VC.CONSTRT_CODE constrtCode," + "VC.CONTRACT_CODE contractCode,"
					+ "VC.CONTRACT_NAME contractName," + "VC.CONSTRT_NAME constrtName, "
					+ "VC.CONSTRT_ADDRESS constrtAddress, " + "VC.STATION_CODE stationCode, "
					+ "bang1.TIT_AZI_CONSTR_ACCEPT_ID titAziConstrAcceptId," + "bang1.CODE code,"
					+ "bang1.ACCEPT_FROM_DATE acceptFromDate," + "bang1.ACCEPT_TO_DATE acceptToDate,"
					+ "bang1.ACCEPT_PLACE acceptPlace," + "bang1.APPLY_BENCHMARK applyBenchmark,"
					+ "bang1.CONSTRUCTION_QUALITY constructionQuality," + "bang1.OTHER_DOCUMENTS otherDocuments,"
					+ "bang1.COMPLETE_REQUEST completeRequest," + "bang1.CREATED_DATE createdDate,"
					+ "bang1.CREATED_USER_ID createdUserId," + "bang1.APPROVAL_DATE approvalDate,"
					+ "bang1.OTHER_COMMENTS otherComments," + "bang1.CONCLUSION conclusion,"
					+ "bang1.SIGN_DATE signDate," + "bang1.SIGN_PLACE signPlace,"
					+ "bang1.STATUS_CA statusCa,"

					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = "
					+ "(SELECT A_DIRECTOR_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId )) adirectorName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID ="
					+ "(SELECT A_IN_CHARGE_MONITOR_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId )) ainchargemonitorName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = "
					+ "(SELECT B_DIRECTOR_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId )) bdirectorName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID ="
					+ "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId )) binchargeConstructName, "
					
							
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT A_DIRECTOR_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId)) adirectorPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT A_IN_CHARGE_MONITOR_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId)) ainchargemonitorPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT B_DIRECTOR_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId)) bdirectorPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM TIT_AZI_CONSTR_ACCEPT WHERE TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId)) binchargeConstructPath "
					
					+ "FROM  TIT_AZI_CONSTR_ACCEPT bang1 "
					+ "INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = bang1.CONSTRUCT_ID "
					+ "WHERE bang1.IS_ACTIVE = 1 AND  bang1.TIT_AZI_CONSTR_ACCEPT_ID = :titAziConstrAcceptId AND  VC.CONTRACT_ID = :contractId AND ROWNUM = 1 ");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("constructId", LongType.INSTANCE);
			query.addScalar("constrtCode", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("contractName", StringType.INSTANCE);
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("constrtAddress", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);

			query.addScalar("titAziConstrAcceptId", LongType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("acceptFromDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("acceptToDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("acceptPlace", StringType.INSTANCE);
			query.addScalar("applyBenchmark", StringType.INSTANCE);
			query.addScalar("constructionQuality", StringType.INSTANCE);
			query.addScalar("otherDocuments", StringType.INSTANCE);
			query.addScalar("completeRequest", StringType.INSTANCE);
			query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("createdUserId", LongType.INSTANCE);
			query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("otherComments", StringType.INSTANCE);
			query.addScalar("conclusion", LongType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);
			query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("signPlace", StringType.INSTANCE);

			query.addScalar("adirectorName", StringType.INSTANCE);
			query.addScalar("ainchargemonitorName", StringType.INSTANCE);
			query.addScalar("bdirectorName", StringType.INSTANCE);
			query.addScalar("binchargeConstructName", StringType.INSTANCE);

			query.addScalar("adirectorPath", StringType.INSTANCE);
			query.addScalar("ainchargemonitorPath", StringType.INSTANCE);
			query.addScalar("bdirectorPath", StringType.INSTANCE);
			query.addScalar("binchargeConstructPath", StringType.INSTANCE);
			
			query.setParameter("titAziConstrAcceptId", dto.getTitAziConstrAcceptId());
			query.setParameter("contractId", dto.getContractId());
			query.setParameter("type2", partnerAttachType);
			
			query.setResultTransformer(Transformers.aliasToBean(TitAziConstrAcceptDTO.class));
			return (TitAziConstrAcceptDTO) query.uniqueResult();

	}

	// -------------------- phe duyet---------------------------
	public boolean lastSignPeople(approDTO obj) {
		// kiểm tra xem đã là người kí cuois cùng chưa
		String sql = "select asm.IS_LAST isLast from APPROVAL_SIGN_MANAGEMENT  asm "
				+ "join CONSTR_COMPLETE_RECORDS_MAP ccrm "
				+ "on ccrm.CONSTR_COMP_RE_MAP_ID = asm.CONSTR_COMP_RE_MAP_ID "
				+ "where ccrm.DATA_TABLE_ID_VALUE  = :id AND asm.EMPLOYEE_ID = :imployeeId ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("imployeeId", obj.getEmployeeId());
		query.setParameter("id", obj.getTitAziConstrAcceptId());
		query.addScalar("isLast", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ApprovalSignManagementDTO.class));

		List<ApprovalSignManagementDTO> list = query.list();
		if (list.size() > 0 && list.get(0).getIsLast() != null && list.get(0).getIsLast() == 1) {
			System.out.println("last");
			return true;
		}
		return false;

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

					////////////// Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery(
							"update TIT_AZI_CONSTR_ACCEPT com set com.APPROVAL_DATE = :appDate, com.STATUS_CA  = 2 "
									+ " where  com.TIT_AZI_CONSTR_ACCEPT_ID = :id")
							.setParameter("appDate", new Date()).setParameter("id", obj.getTitAziConstrAcceptId())
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
						"update TIT_AZI_CONSTR_ACCEPT com set com.APPROVAL_DATE = :appDate, com.STATUS_CA = 3 "
								+ " where com.TIT_AZI_CONSTR_ACCEPT_ID = :id")
						.setParameter("appDate", new Date()).setParameter("id", obj.getTitAziConstrAcceptId())
						.executeUpdate();

				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
	
	public boolean updateIsActive(Long titAziConstrAcceptId){
    		Session session = getSession();
			StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE TIT_AZI_CONSTR_ACCEPT SET TIT_AZI_CONSTR_ACCEPT.IS_ACTIVE = '0' ");
    		sql.append("WHERE TIT_AZI_CONSTR_ACCEPT.TIT_AZI_CONSTR_ACCEPT_ID = " + titAziConstrAcceptId);
    		session.createSQLQuery(sql.toString()).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'TIT_AZI_CONSTR_ACCEPT_ID' "
    				+ "AND DATA_TABLE_ID_VALUE = " + titAziConstrAcceptId).executeUpdate();
			return true;
    }

}
