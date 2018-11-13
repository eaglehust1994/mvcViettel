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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.ConstrCompleteRecordsMapBO;
import com.viettel.erp.bo.ConstructionAcceptanceBO;
import com.viettel.erp.bo.DistanceUnloadConstrMinutesBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@SuppressWarnings("unused")
@Repository("distanceUnloadConstrMinutesDAO")
public class DistanceUnloadConstrMinutesDAO extends BaseFWDAOImpl<DistanceUnloadConstrMinutesBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	public DistanceUnloadConstrMinutesDAO() {
		this.model = new DistanceUnloadConstrMinutesBO();
	}

	public DistanceUnloadConstrMinutesDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<DistanceUnloadConstrMinutesDTO> getListCR(Long constructId) {
		StringBuffer sql = new StringBuffer("SELECT " + "ducm.CODE code, "
				+ "ducm.DIS_UNLOAD_CONS_MIN_ID disUnloadConsMinId, " + "ducm.STATUS_CA statusCa, "
				+ "vch.CONSTRT_CODE constrtCode, " + "vch.CONTRACT_CODE contractCode, "
				+ "vch.CONTRACT_NAME contractName, " + "vch.CONSTRUCT_ID constructId,"
				+ "es.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId,es.WORK_ITEM_NAME estimatesWorkItemName "
				+ "FROM DISTANCE_UNLOAD_CONSTR_MINUTES ducm JOIN V_CONSTRUCTION_HCQT vch "
				+ "ON ducm.CONSTRUCT_ID = vch.CONSTRUCT_ID "
				+ "JOIN ESTIMATES_WORK_ITEMS es ON es.ESTIMATES_WORK_ITEM_ID = ducm.ESTIMATES_WORK_ITEM_ID "
				+ "WHERE ducm.IS_ACTIVE = 1 AND vch.CONSTRUCT_ID = :constructId ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("estimatesWorkItemName", StringType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
		query.addScalar("disUnloadConsMinId", LongType.INSTANCE);

		query.setParameter("constructId", constructId);
		query.setResultTransformer(Transformers.aliasToBean(DistanceUnloadConstrMinutesDTO.class));

		List<DistanceUnloadConstrMinutesDTO> list = query.list();
		return list;
	}

	public List<DistanceUnloadConstrMinutesDTO> getAllC( DistanceUnloadConstrMinutesDTO obj) {

		//tạm comment hql
		
/*		Query q = getSession().createQuery(Joiner.on(" ").join(
				"select a,b from distanceUnloadConstrMinutes a, constrcompleterecordsmap b join fetch a.vconstructionHcqt c "
				+ " where 1=1 and a.disUnloadConsMinId = b.dataTableIdValue "
				+ "and b.dataTableName = 'DISTANCE_UNLOAD_CONSTR_MINUTES' ",
				obj.getContractId() !=null ? " and c.contractId = :contractId" : "",
				"  and c.constructId = :constructId order by a.createdDate DESC"));

		if (obj.getConstructId() != null) {
			q.setParameter("constructId", obj.getConstructId());
		}
		if (obj.getContractId() != null)
		{
			q.setParameter("contractId", obj.getContractId());
		}

		List<DistanceUnloadConstrMinutesDTO> list = Lists.newArrayList();

		for (Object result : q.list()) {
			Object[] o = (Object[]) result;

			ConstrCompleteRecordsMapBO constrCompleteRecordsMapBO = (ConstrCompleteRecordsMapBO) o[1];
			DistanceUnloadConstrMinutesBO distanceUnloadConstrMinutesBO = (DistanceUnloadConstrMinutesBO) o[0];

			DistanceUnloadConstrMinutesDTO distanceUnloadConstrMinutesDTO = distanceUnloadConstrMinutesBO.toDTO();
			distanceUnloadConstrMinutesDTO.setConstrcompleterecordsmap(constrCompleteRecordsMapBO.toDTO());

			list.add(distanceUnloadConstrMinutesDTO);
		}
		return list;*/

		
		
		//native sql
		
		StringBuffer sql = new StringBuffer  ("SELECT " + "DUCM.DIS_UNLOAD_CONS_MIN_ID disUnloadConsMinId ,DUCM.A_DIRECTOR_ID aDirectorId ,"
				+ " DUCM.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId ,DUCM.B_DIRECTOR_ID bDirectorId ,"
				+ " DUCM.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId ,DUCM.APPROVAL_DATE approvalDate ,DUCM.CAR_DES_PLACE carDesPlace ,"
				+ " DUCM.CAR_GATHER_PLACE carGatherPlace ,DUCM.CODE code ,DUCM.CONCLUSION conclusion ,"
				+ " DUCM.CREATED_DATE createdDate ,DUCM.CREATED_USER_ID createdUserId ,DUCM.ESTIMATES_WORK_ITEM_ID estimatesWorkItemId ,"
				+ " DUCM.HANDMADE_DES_PLACE handmadeDesPlace ,DUCM.HANDMADE_GATHER_PLACE handmadeGatherPlace,"
				+ " DUCM.IS_ACTIVE isActive ,DUCM.SCENE_GEN_WORK_LIST_ID sceneGenWorkListId ,DUCM.SIGN_DATE signDate ,DUCM.SIGN_PLACE signPlace ,"
				+ " DUCM.STATUS_CA statusCa ,DUCM.TYPE type ,	DUCM.WHEELBARROW_DES_PLACE wheelbarrowDesPlace,"
				+ " DUCM.WHEELBARROW_GATHER_PLACE wheelbarrowGatherPlace ,VCH.CONSTRUCT_ID constructId ,VCH.CONTRACT_ID contractId ,"
				+ " VCH.CONTRACT_CODE contractCode ,VCH.CONSTRT_CODE constrtCode ,VCH.CONTRACT_NAME contractName ,VCH.CONSTRT_NAME constrtName , "
				
				+ " VCH.CONSTRT_ADDRESS constrtAddress ,CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId ,ASM.COMMENTS comments "
				
				+ "	from DISTANCE_UNLOAD_CONSTR_MINUTES DUCM INNER JOIN V_CONSTRUCTION_HCQT VCH ON DUCM.CONSTRUCT_ID = VCH.CONSTRUCT_ID AND VCH.IS_ACTIVE = '1'"
				+ " JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON DUCM.DIS_UNLOAD_CONS_MIN_ID = CCRM.DATA_TABLE_ID_VALUE AND CCRM.DATA_TABLE_NAME='DISTANCE_UNLOAD_CONSTR_MINUTES'  "
				+ " LEFT JOIN  APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2)  "
				+ " WHERE 1=1 ");
			if (obj.getConstructId() != null) {
				sql.append("and VCH.CONSTRUCT_ID = :constructId ");
			}
			if (obj.getContractId() != null) {
				sql.append("and VCH.CONTRACT_ID = :contractId ");
			}
			sql.append("order by DUCM.CREATED_DATE DESC");
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("disUnloadConsMinId", LongType.INSTANCE);
			query.addScalar("aDirectorId", LongType.INSTANCE);
			query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
			query.addScalar("bDirectorId", LongType.INSTANCE);
			query.addScalar("bInChargeConstructId", LongType.INSTANCE);
			query.addScalar("approvalDate", StandardBasicTypes.TIMESTAMP);				
			query.addScalar("carDesPlace", StringType.INSTANCE);
			query.addScalar("carGatherPlace", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("conclusion", StringType.INSTANCE);
//			query.addScalar("handmadeGatherPlace", StringType.INSTANCE);
			query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("createdUserId", LongType.INSTANCE);		
			query.addScalar("estimatesWorkItemId", LongType.INSTANCE);
			query.addScalar("handmadeDesPlace", StringType.INSTANCE);
			query.addScalar("handmadeGatherPlace", StringType.INSTANCE);

			query.addScalar("isActive", LongType.INSTANCE);
			query.addScalar("sceneGenWorkListId", LongType.INSTANCE);
			query.addScalar("signDate",  StandardBasicTypes.TIMESTAMP);		
			query.addScalar("signPlace", StringType.INSTANCE);
			
			query.addScalar("statusCa", LongType.INSTANCE);
			query.addScalar("type", LongType.INSTANCE);
			query.addScalar("wheelbarrowDesPlace", StringType.INSTANCE);
			query.addScalar("wheelbarrowGatherPlace", StringType.INSTANCE);
			
			
			query.addScalar("constructId", LongType.INSTANCE);
			query.addScalar("contractId", new DoubleType());			
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("constrtCode", StringType.INSTANCE);
			query.addScalar("contractName", StringType.INSTANCE);
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("constrtAddress", StringType.INSTANCE);
			query.addScalar("constrCompReMapId", LongType.INSTANCE);
			query.addScalar("comments", StringType.INSTANCE);
				
		
			if (obj.getContractId() != null) {
				query.setParameter("contractId", obj.getContractId());
			}
			if (obj.getConstructId() != null) {
				query.setParameter("constructId", obj.getConstructId());
			}
			query.setResultTransformer(Transformers.aliasToBean(DistanceUnloadConstrMinutesDTO.class));

			List<DistanceUnloadConstrMinutesDTO> list = query.list();
			return list;
	}

	public String autoGenCodeConstrOrganizationPlan() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('DISTANCE_UNLOAD_CONSTR_MINUTES', 'CODE','CLVC',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	public DistanceUnloadConstrMinutesDTO getDataExport(DistanceUnloadConstrMinutesDTO dto) {
			StringBuilder sql = new StringBuilder(
					"select VC.CONSTRT_NAME constrtName, " 
					+ "VC.STATION_CODE stationCode, "
					+ "VC.CONSTRT_ADDRESS constrtAddress, " 
					+ "VC.CONTRACT_CODE contractCode, "
					+ "DUCM.SIGN_DATE signDate, " 
					+ "DUCM.SIGN_PLACE signPlace, " 
					+ "DUCM.A_DIRECTOR_ID aDirectorId, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_DIRECTOR_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID =:disUnloadConsMinId )) aDirectorIdName, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_DIRECTOR_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId )) aDirectorIdPath, "
					+ "DUCM.A_IN_CHARGE_MONITOR_ID aInChargeMonitorId, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_IN_CHARGE_MONITOR_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId )) aInChargeMonitorIdName, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_IN_CHARGE_MONITOR_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId)) aInChargeMonitorIdPath, "
					+ "DUCM.B_DIRECTOR_ID bDirectorId, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_DIRECTOR_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId)) bDirectorIdName, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_DIRECTOR_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId)) bDirectorIdPath, "
					+ "DUCM.B_IN_CHARGE_CONSTRUCT_ID bInChargeConstructId, "
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId)) bInChargeConstructIdName, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM DISTANCE_UNLOAD_CONSTR_MINUTES WHERE DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId)) bInChargeConstructIdPath, "
					+ "DUCM.CAR_GATHER_PLACE carGatherPlace, " 
					+ "DUCM.CAR_DES_PLACE carDesPlace, "
					+ "DUCM.WHEELBARROW_GATHER_PLACE wheelbarrowGatherPlace, "
					+ "DUCM.WHEELBARROW_DES_PLACE wheelbarrowDesPlace, "
					+ "DUCM.HANDMADE_GATHER_PLACE handmadeGatherPlace, " 
					+ "DUCM.HANDMADE_DES_PLACE handmadeDesPlace, "
					+ "DUCM.STATUS_CA statusCa "

					+ "from DISTANCE_UNLOAD_CONSTR_MINUTES DUCM "
					+ "inner join V_CONSTRUCTION_HCQT VC on VC.CONSTRUCT_ID = DUCM.CONSTRUCT_ID "
					+ "inner join DISTANCE_UNLOAD_LIST DUL on DUL.DIS_UNLOAD_CONS_MIN_ID = DUCM.DIS_UNLOAD_CONS_MIN_ID ");
			sql.append("where DUCM.DIS_UNLOAD_CONS_MIN_ID = :disUnloadConsMinId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameter("disUnloadConsMinId", dto.getDisUnloadConsMinId());
			query.setParameter("type2", partnerAttachType);
			
			
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);
			query.addScalar("constrtAddress", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("aDirectorId", LongType.INSTANCE);
			query.addScalar("aDirectorIdName", StringType.INSTANCE);
			query.addScalar("aDirectorIdPath", StringType.INSTANCE);
			query.addScalar("aInChargeMonitorId", LongType.INSTANCE);
			query.addScalar("aInChargeMonitorIdName", StringType.INSTANCE);
			query.addScalar("aInChargeMonitorIdPath", StringType.INSTANCE);
			query.addScalar("bDirectorId", LongType.INSTANCE);
			query.addScalar("bDirectorIdName", StringType.INSTANCE);
			query.addScalar("bDirectorIdPath", StringType.INSTANCE);
			query.addScalar("bInChargeConstructId", LongType.INSTANCE);
			query.addScalar("bInChargeConstructIdName", StringType.INSTANCE);
			query.addScalar("bInChargeConstructIdPath", StringType.INSTANCE);
			query.addScalar("carGatherPlace", StringType.INSTANCE);
			query.addScalar("carDesPlace", StringType.INSTANCE);
			query.addScalar("wheelbarrowGatherPlace", StringType.INSTANCE);
			query.addScalar("wheelbarrowDesPlace", StringType.INSTANCE);
			query.addScalar("handmadeGatherPlace", StringType.INSTANCE);
			query.addScalar("handmadeDesPlace", StringType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);

			/*query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);*/
			query.setResultTransformer(Transformers.aliasToBean(DistanceUnloadConstrMinutesDTO.class));

			return (DistanceUnloadConstrMinutesDTO) query.list().get(0);

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
					// update vao bang cua minh
					session.createSQLQuery("update DISTANCE_UNLOAD_CONSTR_MINUTES com set com.APPROVAL_DATE = :appDate"
							+ " where  com.DIS_UNLOAD_CONS_MIN_ID = :id").setParameter("appDate", new Date())
							.setParameter("id", obj.getDisUnloadConsMinId()).executeUpdate();
					session.createSQLQuery("update DISTANCE_UNLOAD_CONSTR_MINUTES com set com.STATUS_CA  = 2 "
							+ " where  com.DIS_UNLOAD_CONS_MIN_ID = :id")
							.setParameter("id", obj.getDisUnloadConsMinId()).executeUpdate();
					session.createSQLQuery("update ESTIMATES_WORK_ITEMS com set com.STATUS  = 1 "
							+ " where  com.ESTIMATES_WORK_ITEM_ID = :id")
							.setParameter("id", obj.getEstimatesWorkItemId()).executeUpdate();
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
				session.createSQLQuery("update DISTANCE_UNLOAD_CONSTR_MINUTES com set com.STATUS_CA = 3 "
						+ " where com.DIS_UNLOAD_CONS_MIN_ID = :id").setParameter("id", obj.getDisUnloadConsMinId())
						.executeUpdate();
				// update vao bang cua minh
				session.createSQLQuery("update DISTANCE_UNLOAD_CONSTR_MINUTES com set com.APPROVAL_DATE = :appDate"
						+ " where  com.DIS_UNLOAD_CONS_MIN_ID = :id").setParameter("appDate", new Date())
						.setParameter("id", obj.getDisUnloadConsMinId()).executeUpdate();
				resp = 4l;
			}
		}
		return resp;
	}

	@Transactional
	public Long saveTable(DistanceUnloadConstrMinutesDTO disss) throws Exception {
		Session session = getSession();
		Long id = (Long) session.save(disss.toModel());
		disss.getConstrcompleterecordsmap().setDataTableIdValue(id);
		session.save(disss.getConstrcompleterecordsmap().toModel());
		return id;
	}

	@Transactional
	public Long deleteTable(DistanceUnloadConstrMinutesDTO DistanCM) throws Exception {
		Session session = getSession();
		session.delete(DistanCM.toModel());
		session.delete(DistanCM.getConstrcompleterecordsmap().toModel());
		return 0L;
	}

	public DistanceUnloadConstrMinutesDTO getByOneId(Long id) {
    	Object[] result = (Object[]) getSession().createQuery(Joiner.on(" ").join(" select a,b from distanceUnloadConstrMinutes a, constrcompleterecordsmap b  join fetch a.vconstructionHcqt c  where 1=1 and a.disUnloadConsMinId = b.dataTableIdValue and b.dataTableName = 'DISTANCE_UNLOAD_CONSTR_MINUTES' ",				                          
        		id !=null ? " and a.disUnloadConsMinId = :disUnloadConsMinId" : "")).setParameter("disUnloadConsMinId",  id).setMaxResults(1).uniqueResult();

    	DistanceUnloadConstrMinutesDTO dto = null;
		if (result != null) {
			 ConstrCompleteRecordsMapBO  constrCompleteRecordsMapBO = (ConstrCompleteRecordsMapBO) result[1];
			 DistanceUnloadConstrMinutesBO distanceUnloadConstrMinutesBO = (DistanceUnloadConstrMinutesBO) result[0];
			
			dto = distanceUnloadConstrMinutesBO.toDTO();
			dto.setConstrcompleterecordsmap(constrCompleteRecordsMapBO.toDTO());
		}
		return dto;
	}
	
	@Transactional
    public boolean updateIsActive(List<Long> listId){
    		Session session = getSession();
    		StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE DISTANCE_UNLOAD_CONSTR_MINUTES DUCM SET DUCM.IS_ACTIVE = '0' ");
    		sql.append("WHERE DUCM.DIS_UNLOAD_CONS_MIN_ID IN(:listUpdate)" );
    		session.createSQLQuery(sql.toString()).setParameterList("listUpdate", listId).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'DIS_UNLOAD_CONS_MIN_ID' " + "AND DATA_TABLE_NAME = 'DISTANCE_UNLOAD_CONSTR_MINUTES'  "
    				+ " AND DATA_TABLE_ID_VALUE IN(:listUpdate) ").setParameterList("listUpdate", listId).executeUpdate();
			return true;
    }
}
