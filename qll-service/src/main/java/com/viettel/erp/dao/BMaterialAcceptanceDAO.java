/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.BMaterialAcceptanceBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.erp.rest.CompletionDrawingRsServiceImpl;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("bMaterialAcceptanceDAO")
public class BMaterialAcceptanceDAO extends BaseFWDAOImpl<BMaterialAcceptanceBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;
	
	static Logger LOGGER = LoggerFactory.getLogger(BMaterialAcceptanceDAO.class);

	public BMaterialAcceptanceDAO() {
		this.model = new BMaterialAcceptanceBO();
	}

	public BMaterialAcceptanceDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<BMaterialAcceptanceDTO> findByConstructId(BMaterialAcceptanceDTO dto) {
		StringBuffer sql = new StringBuffer("SELECT " + "vc.CONSTRUCT_ID constructId," + "vc.CONSTRT_CODE constrtCode," +" asm.COMMENTS comments,"
				+ "vc.CONTRACT_CODE contractCode," + "vc.CONTRACT_NAME contractName,"
				+ "vc.CONSTRUCTOR_NAME constructorName," + "vc.CONSTRT_NAME constrtName,"
				+ "vc.CONSTRT_ADDRESS constrtAddress," + "vc.STATION_CODE stationCode ,"
				+ "bma.CREATED_USER_ID createdUserId," + "bma.B_MATERIAL_ACCEPTANCE_ID bmaterialAcceptanceId,"
				+ "bma.STATUS_CA statusCa," + "bma.CODE code," + "bma.CONCLUSION conclusion,"
				+ " bma.CREATED_DATE createdDate," + "bma.A_MONITOR_ID amonitorId,"
				+ "bma.B_IN_CHARGE_CONSTRUCT_ID binChargeConstructId," + "bma.IS_ACTIVE isActive,"
				+ "bma.SIGN_DATE signDate," + "bma.ACCEPTANCE_BASE acceptanceBase," + "bma.SIGN_PLACE signPlace,"
				+ "e.FULL_NAME afullName," + "ce.FULL_NAME bfullName, "
				+ "ccrm.constr_comp_re_map_id constrCompReMapId " + "FROM B_MATERIAL_ACCEPTANCE bma "
				+ "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP ccrm ON bma.B_MATERIAL_ACCEPTANCE_ID = ccrm.data_table_id_value and ccrm.DATA_TABLE_NAME = 'B_MATERIAL_ACCEPTANCE' "
				+ "LEFT JOIN APPROVAL_SIGN_MANAGEMENT asm on (ccrm.CONSTR_COMP_RE_MAP_ID = asm.CONSTR_COMP_RE_MAP_ID and asm.APPROVAL_STATUS =2) "
				+ "INNER JOIN V_CONSTRUCTION_HCQT vc ON vc.CONSTRUCT_ID = bma.CONSTRUCT_ID "
				+ "INNER JOIN CAT_EMPLOYEE e  ON TO_NUMBER(e.ID) = bma.A_MONITOR_ID "
				+ "INNER JOIN CAT_EMPLOYEE ce ON TO_NUMBER(ce.ID) = bma.B_IN_CHARGE_CONSTRUCT_ID "
				+ "WHERE bma.IS_ACTIVE = 1 AND VC.CONSTRUCT_ID = :cnstructId  ");

		if (dto.getContractId() != null) {
			sql.append("and VC.CONTRACT_ID = :contractId ");
		}

		sql.append("order by bma.B_MATERIAL_ACCEPTANCE_ID DESC");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("constructorName", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);

		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("bmaterialAcceptanceId", LongType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);
		query.addScalar("amonitorId", LongType.INSTANCE);
		query.addScalar("binChargeConstructId", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptanceBase", StringType.INSTANCE);
		query.addScalar("signPlace", StringType.INSTANCE);

		query.addScalar("afullName", StringType.INSTANCE);
		query.addScalar("bfullName", StringType.INSTANCE);

		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);

		query.setParameter("cnstructId", dto.getConstructId());
		if (dto.getContractId() != null) {
			query.setParameter("contractId", dto.getContractId());
		}
		query.setResultTransformer(Transformers.aliasToBean(BMaterialAcceptanceDTO.class));

		List<BMaterialAcceptanceDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public BMaterialAcceptanceDTO exportListBmaterial(BMaterialAcceptanceDTO dto) {
			StringBuffer sql = new StringBuffer("SELECT "

					+ "vc.CONSTRUCT_ID 					constructId,"
					+ "vc.CONSTRT_CODE 					constrtCode,"
					+ "vc.CONTRACT_CODE 					contractCode,"
					+ "vc.CONTRACT_NAME 					contractName,"
					+ "vc.CONSTRUCTOR_NAME  				constructorName,"
					+ "vc.CONSTRT_NAME 					constrtName, "
					+ "vc.CONSTRT_ADDRESS 				constrtAddress, "
					+ "vc.STATION_CODE 					stationCode, "
					+ "bma.B_MATERIAL_ACCEPTANCE_ID 		bmaterialAcceptanceId,"
					+ "bma.STATUS_CA 					statusCa," + "bma.CODE 							code,"
					+ "bma.CREATED_DATE 					createdDate,"
					+ "bma.CREATED_USER_ID 				createdUserId,"
					+ "bma.APPROVAL_DATE 				approvalDate," + "bma.CONCLUSION 					conclusion,"
					+ "bma.SIGN_DATE 					signDate," + "bma.SIGN_PLACE 					signPlace,"
					+ "bma.ACCEPTANCE_BASE 				acceptanceBase,"

					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = "
					+ "(SELECT A_MONITOR_ID FROM B_MATERIAL_ACCEPTANCE WHERE B_MATERIAL_ACCEPTANCE_ID = :bMaterialAcceptanceId )) afullName, "

					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = "
					+ "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM B_MATERIAL_ACCEPTANCE WHERE B_MATERIAL_ACCEPTANCE_ID = :bMaterialAcceptanceId)) bfullName, "

					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT A_MONITOR_ID FROM B_MATERIAL_ACCEPTANCE WHERE B_MATERIAL_ACCEPTANCE_ID = :bMaterialAcceptanceId)) amonitoridPath, "

					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
					+ "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM B_MATERIAL_ACCEPTANCE WHERE B_MATERIAL_ACCEPTANCE_ID = :bMaterialAcceptanceId)) binchargeconstructidPath "

					+ " FROM  B_MATERIAL_ACCEPTANCE bma "
					+ "INNER JOIN V_CONSTRUCTION_HCQT vc ON vc.CONSTRUCT_ID = bma.CONSTRUCT_ID "
					+ "WHERE bma.IS_ACTIVE = 1 AND bma.B_MATERIAL_ACCEPTANCE_ID = :bMaterialAcceptanceId AND ROWNUM = 1");
			if (dto.getContractId() != null) {
				sql.append(" and vc.CONTRACT_ID = :contractId");
			}
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.setParameter("bMaterialAcceptanceId", dto.getBmaterialAcceptanceId());
			query.setParameter("type2", partnerAttachType);
			if (dto.getContractId() != null) {
				query.setParameter("contractId", dto.getContractId());
			}
			// export co chan ki
			query.addScalar("amonitoridPath", StringType.INSTANCE);
			query.addScalar("binchargeconstructidPath", StringType.INSTANCE);

			query.addScalar("constructId", LongType.INSTANCE);
			query.addScalar("constrtCode", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("contractName", StringType.INSTANCE);
			query.addScalar("constructorName", StringType.INSTANCE);
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("constrtAddress", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);

			query.addScalar("bmaterialAcceptanceId", LongType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("createdUserId", LongType.INSTANCE);
			query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("conclusion", LongType.INSTANCE);
			query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("acceptanceBase", StringType.INSTANCE);

			query.addScalar("afullName", StringType.INSTANCE);
			query.addScalar("bfullName", StringType.INSTANCE);

			query.setResultTransformer(Transformers.aliasToBean(BMaterialAcceptanceDTO.class));
			return (BMaterialAcceptanceDTO) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<BMaterialAcceptMerListDTO> getAccpetMerList(Long bMaterialAcceptanceId) {
//		System.out.println("mm[[[[[[[[[[[[[[[");
		StringBuffer sql = new StringBuffer("SELECT " + "bma.B_MATERIAL_ACCEPTANCE_ID bMaterialAcceptanceId,"
				+ "bmal.CONTRACT_QUANTITY contractQuantity , bmal.B_MAT_ACC_MER_LIST_ID bMatAccMerListId,"
				+ "bmal.MATERIAL_NAME materialName, bmal.UNIT unit, bmal.QUANTITY quantity, "
				+ "bmal.SPECIFICATION_ORIGIN specificationOrigin, bmal.QUALITY quality " + "FROM "
				+ "B_MATERIAL_ACCEPTANCE bma INNER JOIN B_MATERIAL_ACCEPT_MER_LIST bmal "
				+ "ON bma.B_MATERIAL_ACCEPTANCE_ID = bmal.B_MATERIAL_ACCEPTANCE_ID " + "WHERE "
				+ "bmal.B_MATERIAL_ACCEPTANCE_ID = :bMaterialAcceptanceId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("bMaterialAcceptanceId", LongType.INSTANCE);
		query.addScalar("contractQuantity", new DoubleType());
		query.addScalar("bMatAccMerListId", LongType.INSTANCE);
		query.addScalar("materialName", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("quantity", new DoubleType());
		query.addScalar("specificationOrigin", StringType.INSTANCE);
		query.addScalar("quality", StringType.INSTANCE);
		query.setParameter("bMaterialAcceptanceId", bMaterialAcceptanceId);
		query.setResultTransformer(Transformers.aliasToBean(BMaterialAcceptMerListDTO.class));
		List<BMaterialAcceptMerListDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('B_MATERIAL_ACCEPTANCE', 'CODE','HSHC_NTBC',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public boolean deleteResult(List<String> listString) {
//		try {
			String list = new String(" IN (");
			for (String constrWorkLogsId : listString) {
				list = list + constrWorkLogsId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuffer sql = new StringBuffer("DELETE B_MATERIAL_ACCEPT_MER_LIST where B_MAT_ACC_MER_LIST_ID");
			sql.append(list);
//			System.out.println(sql.toString());
//			System.out.println(listString.size());
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();

			return true;
                        
                        //Hanhls1 : 20170320 - khong try, catch exception trong dao de ben ngoai xu ly
//		} catch (HibernateException he) {
////			log.error(he.getMessage(), he);
//			return false;
//		}
	}

	// -------------------- phe duyet---------------------------

	public Long getconstrCompReMapId(Long id) {
		String sql = "select CONSTR_COMP_RE_MAP_ID constrCompReMapId from CONSTR_COMPLETE_RECORDS_MAP  where DATA_TABLE_ID_VALUE = :id AND DATA_TABLE_NAME = 'B_MATERIAL_ACCEPTANCE' ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));
//		System.out.println(id);
		query.setParameter("id", id);
		List<ConstrCompleteRecordsMapDTO> list = query.list();
//		System.out.println(list.size());
		return list.size() > 0 ? list.get(0).getConstrCompReMapId() : null;
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
		if (appCheck == null) {
			return 5l;
		}
		Long resp = 0l;
		Session session = getSession();
		if (appCheck.getLevelOrder() < appCheck.getApprovalOrder()) {
//			System.out.println("Chua den luot duyet");
			resp = 1l;
		} else if (appCheck.getLevelOrder() > appCheck.getApprovalOrder()) {
//			System.out.println("Da duoc duyet roi");
			resp = 2l;
		} else if (appCheck.getLevelOrder() == appCheck.getApprovalOrder()) {
			if (obj.getStatusCa() == 2) {
				if (appCheck.getIsLast() == 1) {

					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();

					////////////// Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery(
							"update B_MATERIAL_ACCEPTANCE com set com.STATUS_CA  = 2 , com.APPROVAL_DATE = :approvalDate"

									+ " where  com.B_MATERIAL_ACCEPTANCE_ID = :id")
							.setParameter("id", obj.getBmaterialAcceptanceId()).setParameter("approvalDate", new Date())
							.executeUpdate();
					///////////////////////////////////////////////////
				} else {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.LEVEL_ORDER = :lvod "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id")
							.setParameter("lvod", appCheck.getLevelOrder() + 1)
							.setParameter("id", obj.getConstrCompReMapId()).executeUpdate();
				}
				session.createSQLQuery(
						"update APPROVAL_SIGN_MANAGEMENT  asm set asm.APPROVAL_STATUS = 1 , asm.APPROVAL_DATE=:appDate"
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

				////////////// Thay đổi thành tên bảng và id của bảng mình
				session.createSQLQuery("update B_MATERIAL_ACCEPTANCE com set com.STATUS_CA = 3 "
						+ " where com.B_MATERIAL_ACCEPTANCE_ID = :id")
						.setParameter("id", obj.getBmaterialAcceptanceId()).executeUpdate();

				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}

	// xoa nhieu bma
	public boolean deleteListEntity(List<Long> listId) {
//		try {
//			String list = new String(" IN (");
//			for (Long constrWorkLogsId : listId) {
//				list = list + constrWorkLogsId + ",";
//			}
//			list = list.substring(0, list.length() - 1);
//			list = list + ") ";

			StringBuffer sql = new StringBuffer(
					"update B_MATERIAL_ACCEPTANCE bma set bma.IS_ACTIVE = 0  where bma.B_MATERIAL_ACCEPTANCE_ID IN (:list)");
			// sql.append(list);
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameterList("list", listId).executeUpdate();
			query.executeUpdate();
			getSession()
					.createSQLQuery(
							" DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'B_MATERIAL_ACCEPTANCE' AND recod.DATA_TABLE_ID_VALUE IN (:list)")
					.setParameterList("list", listId).executeUpdate();
			return true;
//		//Hanhls1 : 20170320 - khong try, catch exception trong dao de ben ngoai xu ly
//                } catch (HibernateException he) {
////			log.error(he.getMessage(), he);
//			return false;
//		}

	}
}
