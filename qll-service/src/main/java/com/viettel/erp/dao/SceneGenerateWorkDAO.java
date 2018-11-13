/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.SceneGenerateWorkBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.io.Console;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("sceneGenerateWorkDAO")
public class SceneGenerateWorkDAO extends BaseFWDAOImpl<SceneGenerateWorkBO, Long> {
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public SceneGenerateWorkDAO() {
		this.model = new SceneGenerateWorkBO();
	}

	public SceneGenerateWorkDAO(Session session) {
		this.session = session;
	}

	// tungpv
	@SuppressWarnings("unchecked")
	public List<SceneGenerateWorkDTO> doSearchSceneGenerateWork(SceneGenerateWorkDTO criteria) {
		String sql = "SELECT " + "SGW.SCENE_GENERATE_WORK_ID sceneGenerateWorkId, " + "SGW.SIGN_DATE signDate, "
				+ "SGW.TYPE type,"
				+ "SGW.SIGN_PLACE signPlace, " + "SGW.CODE code, " + "SGW.A_DIRECTOR_ID aDirectorId, "
				+ "SGW.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId, " + "SGW.B_DIRECTOR_ID bDirectorId, "
				+ "SGW.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId, "
				+ "SGW.C_DESIGN_DIRECTION_ID cDesignDirectionId, " + "SGW.C_DESIGN_MANAGER_ID cDesignManagerId, "
				+ "SGW.INCIDENT_CONFIRM incidentConfirm, " + "SGW.OTHER_COMMENTS otherComments, "
				+ "SGW.CONCLUSION conclusion, " + "SGW.STATUS_CA statusCa, " + "SGW.CONSTRUCT_ID constructId, "
				+ "SGW.CREATED_DATE createdDate, " + "SGW.CREATED_USER_ID createdUserId, "
				+ "CC.CONSTRT_CODE constrtCode, " + "CC.CONSTRT_NAME constrtName, " + "CNTC.CODE contractCode, "
				+ "CNTC.CONTRACT_NAME contractName, " + "CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
				+ "ASM.COMMENTS comments "
				+ "FROM SCENE_GENERATE_WORK SGW "
				+ "INNER JOIN CONSTR_CONSTRUCTIONS CC ON CC.CONSTRUCT_ID = SGW.CONSTRUCT_ID "
				+ "INNER JOIN CNT_CONSTR_REFER CNTCR ON CNTCR.CONSTRUCT_ID = CC.CONSTRUCT_ID "
				+ "INNER JOIN CNT_CONTRACT CNTC ON CNTC.CONTRACT_ID = CNTCR.CONTRACT_ID "
				+ "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON CCRM.DATA_TABLE_ID_VALUE = SGW.SCENE_GENERATE_WORK_ID "
				+ "AND CCRM.DATA_TABLE_NAME = 'SCENE_GENERATE_WORK' "
				+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2 )"
				+ "WHERE SGW.IS_ACTIVE = '1' AND SGW.TYPE = 1";

		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (criteria.getConstructId() != null) {
			sqlBuilder.append(" AND SGW.CONSTRUCT_ID = :constructId");
		}
		if (criteria.getContractId() != null) {
			sqlBuilder.append(" AND CNTC.CONTRACT_ID = :contractId");
		}
		sqlBuilder.append(" ORDER BY SGW.CREATED_DATE DESC");
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("sceneGenerateWorkId", LongType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("aDirectorId", LongType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		query.addScalar("bDirectorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("cDesignDirectionId", LongType.INSTANCE);
		query.addScalar("cDesignManagerId", LongType.INSTANCE);
		query.addScalar("incidentConfirm", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.setParameter("constructId", criteria.getConstructId());
		query.setParameter("contractId", criteria.getContractId());
		query.setResultTransformer(Transformers.aliasToBean(SceneGenerateWorkDTO.class));
		// query.setMaxResults(100);
		return query.list();
	}

	//-------minhpvn-------
	
	@SuppressWarnings("unchecked")
	public List<SceneGenerateWorkDTO> doSearchSceneGenerateWorkConstruction(SceneGenerateWorkDTO criteria) {
		String sql = "SELECT " + "SGW.SCENE_GENERATE_WORK_ID sceneGenerateWorkId, " + "SGW.SIGN_DATE signDate, "
				+ "SGW.TYPE type, "
				+ "SGW.SIGN_PLACE signPlace, " + "SGW.CODE code, " + "SGW.A_DIRECTOR_ID aDirectorId, "
				+ "SGW.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId, " + "SGW.B_DIRECTOR_ID bDirectorId, "
				+ "SGW.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId, "
				+ "SGW.C_DESIGN_DIRECTION_ID cDesignDirectionId, " + "SGW.C_DESIGN_MANAGER_ID cDesignManagerId, "
				+ "SGW.INCIDENT_CONFIRM incidentConfirm, " + "SGW.OTHER_COMMENTS otherComments, "
				+ "SGW.CONCLUSION conclusion, " + "SGW.STATUS_CA statusCa, " + "SGW.CONSTRUCT_ID constructId, "
				+ "SGW.CREATED_DATE createdDate, " + "SGW.CREATED_USER_ID createdUserId, "
				+ "CC.CONSTRT_CODE constrtCode, " + "CC.CONSTRT_NAME constrtName, " + "CNTC.CODE contractCode, "
				+ "CNTC.CONTRACT_NAME contractName, " + "CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
				+ " ASM.COMMENTS comments "
				+ "FROM SCENE_GENERATE_WORK SGW "
				+ "INNER JOIN CONSTR_CONSTRUCTIONS CC ON CC.CONSTRUCT_ID = SGW.CONSTRUCT_ID "
				+ "INNER JOIN CNT_CONSTR_REFER CNTCR ON CNTCR.CONSTRUCT_ID = CC.CONSTRUCT_ID "
				+ "INNER JOIN CNT_CONTRACT CNTC ON CNTC.CONTRACT_ID = CNTCR.CONTRACT_ID "
				+ "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON CCRM.DATA_TABLE_ID_VALUE = SGW.SCENE_GENERATE_WORK_ID "
				+ "AND CCRM.DATA_TABLE_NAME = 'SCENE_GENERATE_WORK' "
				+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2)"
				+ "WHERE SGW.IS_ACTIVE = '1' AND SGW.TYPE = 2 ";

		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (criteria.getConstructId() != null) {
			sqlBuilder.append(" AND SGW.CONSTRUCT_ID = :constructId");
		}
		if (criteria.getContractId() != null) {
			sqlBuilder.append(" AND CNTC.CONTRACT_ID = :contractId");
		}
		sqlBuilder.append(" ORDER BY SGW.CREATED_DATE DESC");
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("sceneGenerateWorkId", LongType.INSTANCE);
		query.addScalar("type", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("aDirectorId", LongType.INSTANCE);
		query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
		query.addScalar("bDirectorId", LongType.INSTANCE);
		query.addScalar("bInChargeConstructId", LongType.INSTANCE);
		query.addScalar("cDesignDirectionId", LongType.INSTANCE);
		query.addScalar("cDesignManagerId", LongType.INSTANCE);
		query.addScalar("incidentConfirm", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.setParameter("constructId", criteria.getConstructId());
		query.setParameter("contractId", criteria.getContractId());
		query.setResultTransformer(Transformers.aliasToBean(SceneGenerateWorkDTO.class));
		// query.setMaxResults(100);
		return query.list();
	}
	
	
	public SceneGenerateWorkDTO getDataExport(SceneGenerateWorkDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select V_CONSTRUCTION_HCQT.CONSTRT_NAME constrtName,"
					+ "V_CONSTRUCTION_HCQT.STATION_CODE stationCode, "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constrtAddress, "
					+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode, "

					+ "SGW.SIGN_DATE signDate, " + "SGW.SIGN_PLACE signPlace, " + "SGW.CODE code, "
					+ "SGW.A_DIRECTOR_ID aDirectorId, " + "SGW.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId, "
					+ "SGW.B_DIRECTOR_ID bDirectorId, " + "SGW.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId, "
					+ "SGW.C_DESIGN_DIRECTION_ID cDesignDirectionId, " + "SGW.C_DESIGN_MANAGER_ID cDesignManagerId, "
					+ "SGW.INCIDENT_CONFIRM incidentConfirm, " + "SGW.OTHER_COMMENTS otherComments, "
					+ "SGW.CONCLUSION conclusion, " + "SGW.STATUS_CA statusCa, " + "SGW.CONSTRUCT_ID constructId, "
					+ "SGW.CREATED_DATE createdDate, " + "SGW.CREATED_USER_ID createdUserId, "
					+ "SGW.STATUS_CA statusCa, "

					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_DIRECTOR_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) aDirectorIdName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_IN_CHARGE_MONITOR_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) aInChargeMonitorIdName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_DIRECTOR_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) bDirectorIdName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) bInChargeConstructIdName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT C_DESIGN_DIRECTION_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) cDesignDirectionIdName, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT C_DESIGN_MANAGER_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) cDesignManagerIdName, "
					
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_DIRECTOR_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) aDirectorIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_IN_CHARGE_MONITOR_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) aInChargeMonitorIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_DIRECTOR_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) bDirectorIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) bInChargeConstructIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT C_DESIGN_DIRECTION_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) cDesignDirectionIdPath, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT C_DESIGN_MANAGER_ID FROM SCENE_GENERATE_WORK WHERE SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId"
					+ ")) cDesignManagerIdPath "

					+ "from SCENE_GENERATE_WORK SGW "
					+ "inner join V_CONSTRUCTION_HCQT on V_CONSTRUCTION_HCQT.CONSTRUCT_ID = SGW.CONSTRUCT_ID "
					+ "");

			sql.append("where SGW.SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);
			query.addScalar("constrtAddress", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);

			query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("aDirectorId", LongType.INSTANCE);
			query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
			query.addScalar("bDirectorId", LongType.INSTANCE);
			query.addScalar("bInChargeConstructId", LongType.INSTANCE);
			query.addScalar("cDesignDirectionId", LongType.INSTANCE);
			query.addScalar("cDesignManagerId", LongType.INSTANCE);
			query.addScalar("incidentConfirm", StringType.INSTANCE);
			query.addScalar("otherComments", StringType.INSTANCE);
			query.addScalar("conclusion", StringType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);
			query.addScalar("constructId", LongType.INSTANCE);
			query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("createdUserId", LongType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);

			query.addScalar("aDirectorIdName", StringType.INSTANCE);
			query.addScalar("aInChargeMonitorIdName", StringType.INSTANCE);
			query.addScalar("bDirectorIdName", StringType.INSTANCE);
			query.addScalar("bInChargeConstructIdName", StringType.INSTANCE);
			query.addScalar("cDesignDirectionIdName", StringType.INSTANCE);
			query.addScalar("cDesignManagerIdName", StringType.INSTANCE);
			query.addScalar("aDirectorIdPath", StringType.INSTANCE);
			query.addScalar("aInChargeMonitorIdPath", StringType.INSTANCE);
			query.addScalar("bDirectorIdPath", StringType.INSTANCE);
			query.addScalar("bInChargeConstructIdPath", StringType.INSTANCE);
			query.addScalar("cDesignDirectionIdPath", StringType.INSTANCE);
			query.addScalar("cDesignManagerIdPath", StringType.INSTANCE);
			
			query.setParameter("sceneGenerateWorkId", dto.getSceneGenerateWorkId());
			query.setParameter("type2", partnerAttachType);

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(SceneGenerateWorkDTO.class));

			return (SceneGenerateWorkDTO) query.list().get(0);

	}
	//minhpvn
	@SuppressWarnings("unchecked")
	public List<SceneGenerateWorkListDTO> getAllSceneWrkList(Long  idParent) {
		String sql = "SELECT SGWL.INCURRED_CONTENT incurredContent ,SGWL.UNIT unit, SGWL.INCURRED_QUANTITY incurredQuantity, SGWL.SCENE_GEN_WORK_LIST_ID sceneGenWorkListId, SGWL.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId,  "
					+" SGWL.WORK_ITEM_TYPE workItemType "
					+" FROM SCENE_GENERATE_WORK_LIST SGWL INNER JOIN SCENE_GENERATE_WORK SGW "
					+" ON SGWL.SCENE_GENERATE_WORK_ID = SGW.SCENE_GENERATE_WORK_ID "
					+" WHERE SGW.SCENE_GENERATE_WORK_ID = :sceneGenerateWorkId ";
		StringBuilder sqlBuilder = new StringBuilder(sql);
	
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("workItemType", LongType.INSTANCE);
		query.addScalar("incurredContent", StringType.INSTANCE);
		query.addScalar("unit", StringType.INSTANCE);
		query.addScalar("incurredQuantity", new DoubleType());
		query.addScalar("sceneGenWorkListId", LongType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		query.setParameter("sceneGenerateWorkId", idParent);
		query.setResultTransformer(Transformers.aliasToBean(SceneGenerateWorkListDTO.class));
		// query.setMaxResults(100);
		return query.list();
	}
	//minhpvn
	@Transactional
	public Boolean saveListEstimatesWorkItems(Long idParent,Long constructId) {
		List<SceneGenerateWorkListDTO> listChil = getAllSceneWrkList(idParent);
		//ArrayList<EstimatesWorkItemsDTO> listTarget = Lists.newArrayList();
		for (SceneGenerateWorkListDTO oneChild : listChil) {
			EstimatesWorkItemsDTO oneObjTarget = new EstimatesWorkItemsDTO();
			SQLQuery query =   getSession().createSQLQuery("select CONSTR_ESTIMATE_INFO_ID from CONSTR_ESTIMATE_INFO where CONSTRUCTION_ID = :constructId");
			query.setParameter("constructId", constructId);
			List<BigDecimal> numberResultList =query.list();
			Long constrEstimateInfoId = numberResultList.size()>0?(long) numberResultList.get(0).longValue():0l;
			oneObjTarget.setConstrEstimateInfoId(constrEstimateInfoId);
			oneObjTarget.setEstimatesItemChildId(oneChild.getEstimatesItemChildId());
			oneObjTarget.setWorkItemName(oneChild.getIncurredContent());
			oneObjTarget.setUnit(oneChild.getUnit());
			oneObjTarget.setWorkAmount(oneChild.getIncurredQuantity());
			oneObjTarget.setSceneGenWorkListId(oneChild.getSceneGenWorkListId());
			oneObjTarget.setProgressType(new Long(3));
			oneObjTarget.setType(new Long(2));
			oneObjTarget.setStatus(new Long(0));
			if(oneChild != null && oneChild.getWorkItemType()==3){
				oneObjTarget.setWorkType(new Long(1));
			}else if(oneChild != null && oneChild.getWorkItemType()==1||oneChild.getWorkItemType()==2){
				oneObjTarget.setWorkType(new Long(2));
			}
			getSession().save(oneObjTarget.toModel());
		}
		return true;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SceneGenerateWorkDTO> getItemNameByConstrId(SceneGenerateWorkDTO criteria) {
		String sql = "SELECT EIC.ITEM_NAME itemName, " + "EIC.ESTIMATES_ITEM_CHILD_ID estimatesItemChildId "
				+ "FROM ESTIMATES_ITEMS_CHILD EIC " + "INNER JOIN CONSTR_ESTIMATE_INFO CEI "
				+ "ON CEI.CONSTR_ESTIMATE_INFO_ID = EIC.CONSTR_ESTIMATE_INFO_ID "
				+ "INNER JOIN CONSTR_CONSTRUCTIONS CC " + "ON CC.CONSTRUCT_ID = CEI.CONSTRUCTION_ID ";
		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (criteria.getConstructId() != null) {
			sqlBuilder.append(" WHERE CC.CONSTRUCT_ID = :constructId");
		}
		SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("itemName", StringType.INSTANCE);
		query.addScalar("estimatesItemChildId", LongType.INSTANCE);
		
		query.setParameter("constructId", criteria.getConstructId());
		query.setResultTransformer(Transformers.aliasToBean(SceneGenerateWorkDTO.class));
		// query.setMaxResults(100);
		return query.list();
	}
	
	public boolean updateIsActive(List<Long> listId){
    		Session session = getSession();
    		String listUpdate = new String(" IN (");
    		for (Long id : listId) {
    			listUpdate = listUpdate + id + ",";
    		}
    		listUpdate = listUpdate.substring(0, listUpdate.length() - 1);
    		listUpdate = listUpdate + ") ";
    		
    		StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE SCENE_GENERATE_WORK SGW SET SGW.IS_ACTIVE = '0' ");
    		sql.append("WHERE SGW.SCENE_GENERATE_WORK_ID " + listUpdate);
    		session.createSQLQuery(sql.toString()).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'SCENE_GENERATE_WORK_ID' "
    				+ "AND DATA_TABLE_ID_VALUE " + listUpdate).executeUpdate();
			return true;
    }

	//////////////////////////////////// Phe duyet ////////////////////////////
	public approDTO getCheckData(approDTO obj){
		StringBuffer sql = new StringBuffer("SELECT cr.LEVEL_ORDER levelOrder, ca.APPROVAL_ORDER approvalOrder, ca.IS_LAST isLast "
				+ "from APPROVAL_SIGN_MANAGEMENT ca "
				+ "inner join CONSTR_COMPLETE_RECORDS_MAP cr "
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
		if(appCheck == null) {
			return 5l;
		}
		Long resp = 0l;
		
		Session session = getSession();
		if(appCheck.getLevelOrder() < appCheck.getApprovalOrder()){
			System.out.println("Chua den luot duyet");
			resp = 1l;
		} else if(appCheck.getLevelOrder() > appCheck.getApprovalOrder()){
			System.out.println("Da duoc duyet roi");
			resp = 2l;
		} else if(appCheck.getLevelOrder() == appCheck.getApprovalOrder()){
			//StatusCa la trang thai trong dropdownlist man hinh phe duyet moi nguoi quy dinh.
			if (obj.getStatusCa() == 1){
				if(appCheck.getIsLast() == 1){
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();
					//update vao bang cua minh
					session.createSQLQuery("update SCENE_GENERATE_WORK com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 2 "
							+ " where  com.SCENE_GENERATE_WORK_ID = :id")
							.setParameter("appDate", new Date())
							.setParameter("id", obj.getSceneGenerateWorkId()).executeUpdate();
					////////////////////////
					//minhpvn
					saveListEstimatesWorkItems(obj.getSceneGenerateWorkId(),obj.getConstructId());
					System.out.println("obj"+ obj.getConstructId());
					
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
				//update vao bang cua minh
				session.createSQLQuery("update SCENE_GENERATE_WORK com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 3 "
						+ " where  com.SCENE_GENERATE_WORK_ID = :id")
						.setParameter("appDate", new Date())
						.setParameter("id", obj.getSceneGenerateWorkId()).executeUpdate();
				///////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
	////////////////////////////////////////////////////////////////////////////
	// end tungpv
	//minhpvn hàm gọi phê duyệt công trình
	@Transactional
	public Long approCT(approDTO obj) {
		approDTO appCheck = getCheckData(obj);
		if(appCheck == null) {
			return 5l;
		}
		Long resp = 0l;
		
		Session session = getSession();
		if(appCheck.getLevelOrder() < appCheck.getApprovalOrder()){
			System.out.println("Chua den luot duyet");
			resp = 1l;
		} else if(appCheck.getLevelOrder() > appCheck.getApprovalOrder()){
			System.out.println("Da duoc duyet roi");
			resp = 2l;
		} else if(appCheck.getLevelOrder() == appCheck.getApprovalOrder()){
			//StatusCa la trang thai trong dropdownlist man hinh phe duyet moi nguoi quy dinh.
			if (obj.getStatusCa() == 1){
				if(appCheck.getIsLast() == 1){
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();
					//update vao bang cua minh
					session.createSQLQuery("update SCENE_GENERATE_WORK com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 2 "
							+ " where  com.SCENE_GENERATE_WORK_ID = :id")
							.setParameter("appDate", new Date())
							.setParameter("id", obj.getSceneGenerateWorkId()).executeUpdate();
					////////////////////////
					//minhpvn
//					saveListEstimatesWorkItems(obj.getSceneGenerateWorkId(),obj.getConstructId());
//					System.out.println("obj"+ obj.getConstructId());
					
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
				//update vao bang cua minh
				session.createSQLQuery("update SCENE_GENERATE_WORK com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 3 "
						+ " where  com.SCENE_GENERATE_WORK_ID = :id")
						.setParameter("appDate", new Date())
						.setParameter("id", obj.getSceneGenerateWorkId()).executeUpdate();
				///////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
}
