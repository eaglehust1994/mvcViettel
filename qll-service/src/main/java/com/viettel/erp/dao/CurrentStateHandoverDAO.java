/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.CurrentStateHandoverBO;
import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmExportDTO;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.erp.dto.CurrentStateHandoverExtraDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
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
@Repository("currentStateHandoverDAO")
public class CurrentStateHandoverDAO extends BaseFWDAOImpl<CurrentStateHandoverBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public CurrentStateHandoverDAO() {
		this.model = new CurrentStateHandoverBO();
	}

	public CurrentStateHandoverDAO(Session session) {
		this.session = session;
	}

	public List<CurrentStateHandoverDTO> getListCurrentStateHandoverByContructId(long constructId) {
	//tạm comment HQL
		/*	String hQuery = "from currentStHandover a where a.constructId= :constructId AND a.isActive = 1";
		Query query = getSession().createQuery(hQuery);
		query.setParameter("constructId", constructId);

		List<CurrentStateHandoverBO> listBO = query.list();
		return listBO;*/
		
		
	//native SQL
		StringBuffer sql = new StringBuffer("SELECT CSH.STATUS_CA statusCa, CSH.CODE code,CSH.HANDOVER_DATE handoverDate , CSH.HANDOVER_PLACE handoverPlace, "
		+ "CSH.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId ,CSH.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId,"
        +" CSH.GROUND_HANDOVER_CONTENT groundHandoverContent,CSH.OTHER_COMMENTS otherComments ,"
        + "CSH.OTHER_DOCUMENTS otherDocuments ,CSH.CONCLUSION conclusion,CSH.IS_ACTIVE isActive,CSH.APPROVAL_DATE approvalDate,"
        +" CSH.CURRENT_STATE_HANDOVER_ID currentStateHandoverId,CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId ,ASM.COMMENTS comments "
        +" FROM  CURRENT_STATE_HANDOVER  CSH "
        +" INNER JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM "
        +" ON CSH.CURRENT_STATE_HANDOVER_ID = CCRM.DATA_TABLE_ID_VALUE  AND CCRM.DATA_TABLE_NAME = 'CURRENT_STATE_HANDOVER' "
        +" INNER JOIN V_CONSTRUCTION_HCQT VC ON VC.CONSTRUCT_ID = CSH.CONSTRUCT_ID "
        +" LEFT JOIN  APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2 ) "
        +" WHERE VC.CONSTRUCT_ID = :constructId AND CSH.IS_ACTIVE = 1" );

	/*	if (dto.getContractId() != null) {
			sql.append("and VC.CONTRACT_ID = :contractId ");
		}*/

		sql.append("order by CSH.CURRENT_STATE_HANDOVER_ID DESC");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("handoverDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("handoverPlace", StringType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("groundHandoverContent", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("otherDocuments", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);

		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("currentStateHandoverId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		
		

		query.setParameter("constructId", constructId);
/*		if (dto.getContractId() != null) {
			query.setParameter("contractId", dto.getContractId());
		}*/
		query.setResultTransformer(Transformers.aliasToBean(CurrentStateHandoverDTO.class));

		List<CurrentStateHandoverDTO> list = query.list();
		return list;
		
	}

	@Transactional
	public boolean deleteListEntity(List<Long> listId) {
		Session session = getSession();
		Query query = session.createQuery(
				"UPDATE currentStHandover u SET u.isActive = 0 WHERE u.currentStateHandoverId IN (:list) ");
		query.setParameterList("list", listId);
		query.executeUpdate();
		session.createSQLQuery(
				"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'CURRENT_STATE_HANDOVER' AND recod.DATA_TABLE_ID_VALUE IN (:list)")
				.setParameterList("list", listId).executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	public String autoGenCode(String tableName, String value) {
		SQLQuery query = getSession().createSQLQuery(
				Joiner.on("").join("select get_next_code('", tableName, "', 'CODE','", value, "',6) code from dual"));
		query.addScalar("code", StandardBasicTypes.STRING);

		return (String) query.uniqueResult();
	}
	/*
	 * @Override public Long saveObject(CurrentStateHandoverBO obj) {
	 * 
	 * for(CurrentStateHandoverListBO chil : obj.getListCurrentStateHandover()){
	 * getSession().save(chil); } // TODO Auto-generated method stub return
	 * (Long) getSession().save(obj); }
	 */
	// public boolean updateCurrentStateHandoverExtra(CurrentStateHandoverBO
	// obj){
	// Session session = getSession();
	// session.update(obj);
	// return true;
	// }

	public CurrentStateHandoverExtraDTO getCurrentStateHandoverExtraById(Long id) {
		String sql = "	select v.CONTRACT_CODE contractCode, v.CONSTRT_ADDRESS constrtAddress, v.CONSTRT_NAME constrtName , v.CONSTRT_ADDRESS constrtAddress, v.STATION_CODE station_code , "
				+ " com.HANDOVER_PLACE handoverPlace , com.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId , com.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId , com.CODE code ,"
				+ " com.GROUND_HANDOVER_CONTENT groundHandoverContent , com.STATUS_CA statusCa , com.OTHER_COMMENTS otherComments , com.CONCLUSION conclusion  ,com.HANDOVER_DATE handoverDate ,"

				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT A_IN_CHARGE_MONITOR_ID FROM CURRENT_STATE_HANDOVER WHERE CURRENT_STATE_HANDOVER_ID = :id" 
				+ ")) aInChargeMonitorPath, "
				+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = "
				+ "(SELECT B_IN_CHARGE_CONSTRUCT_ID FROM CURRENT_STATE_HANDOVER WHERE CURRENT_STATE_HANDOVER_ID = :id" 
				+ ")) bInChargeConstructPath "

				+ " from CURRENT_STATE_HANDOVER com " + " join V_CONSTRUCTION_HCQT v "
				+ " on com.CONSTRUCT_ID = v.CONSTRUCT_ID " + " where com.CURRENT_STATE_HANDOVER_ID = :id ";

		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("station_code", StringType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("handoverPlace", StringType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("groundHandoverContent", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", StringType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("handoverDate", new DateType());
		query.addScalar("aInChargeMonitorPath", StringType.INSTANCE);
		query.addScalar("bInChargeConstructPath", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(CurrentStateHandoverExtraDTO.class));
		System.out.println(id);
		query.setParameter("id", id);
		query.setParameter("type2", partnerAttachType);
		List<CurrentStateHandoverExtraDTO> list = query.list();
		System.out.println(list.size());
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<CurrentStateHandoverListBO> getCurrentStateHandoverByListId(Long id) {
		Session session = getSession();
		Query query = session.createQuery(
				"select u from currentStateHandoverList u where u.currentStateHandoverBO.currentStateHandoverId = :id");
		query.setParameter("id", id);
		System.out.println("-----------" + id);
		return query.list();
	}

	public String getNameEmployee(Long id) {
		SQLQuery query = getSession().createSQLQuery("SELECT * FROM CAT_EMPLOYEE  cat WHERE cat.ID = :id");
		query.addEntity(CatEmployeeBO.class);

		query.setParameter("id", id);
		List<CatEmployeeBO> results = query.list();
		if (results.size() > 0) {
			return results.get(0).getFullName();
		} else {
			return null;
		}

	}

	public Long getconstrCompReMapId(Long id) {
		String sql = "select CONSTR_COMP_RE_MAP_ID constrCompReMapId from CONSTR_COMPLETE_RECORDS_MAP  where DATA_TABLE_ID_VALUE = :id AND DATA_TABLE_NAME = 'CURRENT_STATE_HANDOVER' ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));
		System.out.println(id);
		query.setParameter("id", id);
		List<ConstrCompleteRecordsMapDTO> list = query.list();
		System.out.println(list.size());
		return list.size() > 0 ? list.get(0).getConstrCompReMapId() : null;
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
		approDTO appCheck = getCheckData(obj);
		Long resp = 0l;
		Session session = getSession();
		if (appCheck == null) {
			resp = 5l;
			System.out.println("Không được phép duyệt");
		} else if (appCheck.getLevelOrder() < appCheck.getApprovalOrder()) {
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
					session.createSQLQuery("update CURRENT_STATE_HANDOVER com set com.STATUS_CA  = 2 , com.APPROVAL_DATE = :approDate"
							+ " where  com.CURRENT_STATE_HANDOVER_ID = :id")
							.setParameter("id", obj.getCurrentStateHandoverId())
							.setParameter("approDate", new Date())
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
				session.createSQLQuery("update CURRENT_STATE_HANDOVER com set com.STATUS_CA = 3 "
						+ " where com.CURRENT_STATE_HANDOVER_ID = :id")
						.setParameter("id", obj.getCurrentStateHandoverId()).executeUpdate();

				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}

	// ---------------------------------- hết phe duyệt
	// ------------------------------------------------
	@Transactional
	public Long saveTable(CurrentStateHandoverDTO completionDrawing) throws Exception {
		Session session = getSession();
		Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
		completionDrawing.getConstrCompleteRecordMap().setDataTableIdValue(completionDrawingId);
		session.save(completionDrawing.getConstrCompleteRecordMap().toModel());
		return completionDrawingId;
	}
}
