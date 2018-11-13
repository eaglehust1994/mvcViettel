/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.WorkItemsAcceptanceBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("workItemsAcceptanceDAO")
public class WorkItemsAcceptanceDAO extends BaseFWDAOImpl<WorkItemsAcceptanceBO, Long> {
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

	@Autowired
    private EstimatesWorkItemsDAO estimatesWorkItemsDAO;
	
    public WorkItemsAcceptanceDAO() {
        this.model = new WorkItemsAcceptanceBO();
    }

    public WorkItemsAcceptanceDAO(Session session) {
        this.session = session;
    }
    
    //tungpv
    
    @Transactional
    public Long saveTable( WorkItemsAcceptanceDTO  workItemsAcceptance) throws Exception{
           Session session = getSession();
           Long workItemsAcceptanceId = (Long) session.save(workItemsAcceptance.toModel());
           workItemsAcceptance.getConstrCompleteRecordsMap().setDataTableIdValue(workItemsAcceptanceId);     
           session.save(workItemsAcceptance.getConstrCompleteRecordsMap().toModel());
           return workItemsAcceptanceId;
     }
    
    @SuppressWarnings("unchecked")
	public List<WorkItemsAcceptanceDTO> doSearchWorkItemsAcceptance(WorkItemsAcceptanceDTO criteria) {

    	String sql = "SELECT "
    			+ "WIA.WORK_ITEMS_ACCEPTANCE_ID workItemsAcceptanceId, "
    			+ "WIA.ACCEPT_FROM_DATE acceptFromDate, "
    			+ "WIA.ACCEPT_TO_DATE acceptToDate, "
    			+ "WIA.ACCEPT_PLACE acceptPlace, "
    			+ "WIA.CREATED_DATE createdDate, "
    			+ "WIA.APPLY_BENCHMARK applyBenchmark, "
    			+ "WIA.OTHER_DOCUMENTS otherDocuments, "
    			+ "WIA.CONSTRUCTION_QUALITY constructionQuality, "
    			+ "WIA.OTHER_COMMENTS otherComments, "
    			+ "WIA.CONCLUSION conclusion, "
    			+ "WIA.COMPLETE_REQUEST completeRequest, "
				+ "WIA.CODE code, "
				+ "WIA.A_MONITOR_ID monitorId, "
				+ "WIA.B_IN_CHARGE_CONSTRUCT_ID inChargeConstructId, "
				+ "WIA.SIGN_DATE signDate, "
				+ "WIA.SIGN_PLACE signPlace, "
				+ "CC.CONSTRT_CODE constrtCode, "
				+ "CC.CONSTRUCT_ID constructId, "
				+ "CC.CONSTRT_ADDRESS constrtAddress, "
				+ "CNTC.CODE contractCode, "
				+ "CNTC.CONTRACT_NAME contractName, "
				+ "WIA.STATUS_CA statusCa, "
				+ "WIA.ACCEPT_PLACE acceptPlace, "
				+ "WIA.CREATED_USER_ID createdUserId, "
				+ "CCRM.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
				+ "ASM.COMMENTS comments  "
				+ "FROM WORK_ITEMS_ACCEPTANCE WIA "
				+ "INNER JOIN CONSTR_CONSTRUCTIONS CC ON CC.CONSTRUCT_ID = WIA.CONSTRUCT_ID "
    			+ "INNER JOIN CNT_CONSTR_REFER CNTCR ON CNTCR.CONSTRUCT_ID = CC.CONSTRUCT_ID "
    			+ "INNER JOIN CNT_CONTRACT CNTC ON CNTC.CONTRACT_ID = CNTCR.CONTRACT_ID "
    			+ "INNER JOIN CONSTR_COMPLETE_RECORDS_MAP CCRM ON CCRM.DATA_TABLE_ID_VALUE = WIA.WORK_ITEMS_ACCEPTANCE_ID "
       			+ " AND CCRM.DATA_TABLE_NAME = 'WORK_ITEMS_ACCEPTANCE' "
    			+ " left join APPROVAL_SIGN_MANAGEMENT ASM ON (CCRM.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2) "
    			+ " WHERE WIA.IS_ACTIVE = '1' ";

    	StringBuilder sqlBuilder = new StringBuilder(sql);
    	if(criteria.getConstructId() != null){
    		sqlBuilder.append(" AND WIA.CONSTRUCT_ID = :constructId");
    	}
    	if(criteria.getContractId() != null){
    		sqlBuilder.append(" AND CNTC.CONTRACT_ID = :contractId");
    	}
    	sqlBuilder.append(" ORDER BY WIA.CREATED_DATE DESC");
    	SQLQuery query = getSession().createSQLQuery(sqlBuilder.toString());
		query.addScalar("workItemsAcceptanceId", LongType.INSTANCE);
		query.addScalar("acceptFromDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptToDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("acceptPlace", StringType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("applyBenchmark", StringType.INSTANCE);
		query.addScalar("otherDocuments", StringType.INSTANCE);
		query.addScalar("constructionQuality", StringType.INSTANCE);
		query.addScalar("otherComments", StringType.INSTANCE);
		query.addScalar("conclusion", LongType.INSTANCE);
		query.addScalar("completeRequest", StringType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("monitorId", LongType.INSTANCE);
		query.addScalar("inChargeConstructId", LongType.INSTANCE);
		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("signPlace", StringType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrtAddress", StringType.INSTANCE);
		query.addScalar("contractCode", StringType.INSTANCE);
		query.addScalar("contractName", StringType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("acceptPlace", StringType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		query.setParameter("constructId", criteria.getConstructId());
		query.setParameter("contractId", criteria.getContractId());
		query.setResultTransformer(Transformers.aliasToBean(WorkItemsAcceptanceDTO.class));
		return query.list();
	}
    
    public WorkItemsAcceptanceDTO getDataExport(WorkItemsAcceptanceDTO dto){
			StringBuilder sql = new StringBuilder();
			sql.append("select V_CONSTRUCTION_HCQT.CONSTRT_NAME constrtName," 
					+ "V_CONSTRUCTION_HCQT.STATION_CODE stationCode, "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constrtAddress, "
					+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode, "
					
					+ "WIA.ACCEPT_FROM_DATE acceptFromDate, "
					+ "WIA.ACCEPT_TO_DATE acceptToDate, "
					+ "WIA.ACCEPT_PLACE acceptPlace, "
					+ "WIA.CREATED_DATE createdDate, "
					+ "WIA.APPLY_BENCHMARK applyBenchmark, "
					+ "WIA.OTHER_DOCUMENTS otherDocuments, "
					+ "WIA.CONSTRUCTION_QUALITY constructionQuality, "
					+ "WIA.OTHER_COMMENTS otherComments, "
					+ "WIA.CONCLUSION conclusion, "
					+ "WIA.COMPLETE_REQUEST completeRequest, "
					+ "WIA.CODE code, "
					+ "WIA.A_MONITOR_ID monitorId, "
					+ "WIA.B_IN_CHARGE_CONSTRUCT_ID inChargeConstructId, "
					+ "WIA.SIGN_DATE signDate, "
					+ "WIA.SIGN_PLACE signPlace, "
					+ "WIA.STATUS_CA statusCa, "
					
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT A_MONITOR_ID FROM WORK_ITEMS_ACCEPTANCE WHERE WORK_ITEMS_ACCEPTANCE_ID = :workItemsAcceptanceId"+")) monitorIdName, " 
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT A_MONITOR_ID FROM WORK_ITEMS_ACCEPTANCE WHERE WORK_ITEMS_ACCEPTANCE_ID = :workItemsAcceptanceId"+")) monitorIdNamePath, " 
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM WORK_ITEMS_ACCEPTANCE WHERE WORK_ITEMS_ACCEPTANCE_ID = :workItemsAcceptanceId"+")) inChargeConstructIdName, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT B_IN_CHARGE_CONSTRUCT_ID FROM WORK_ITEMS_ACCEPTANCE WHERE WORK_ITEMS_ACCEPTANCE_ID = :workItemsAcceptanceId"+")) inChargeConstructIdNamePath "
					
					+ "from WORK_ITEMS_ACCEPTANCE WIA "
					+ "inner join V_CONSTRUCTION_HCQT on V_CONSTRUCTION_HCQT.CONSTRUCT_ID = WIA.CONSTRUCT_ID "
					+ "");
					
			sql.append("where WIA.WORK_ITEMS_ACCEPTANCE_ID = :workItemsAcceptanceId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);
			query.addScalar("constrtAddress", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			
			query.addScalar("acceptFromDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("acceptToDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("acceptPlace", StringType.INSTANCE);
			query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("applyBenchmark", StringType.INSTANCE);
			query.addScalar("otherDocuments", StringType.INSTANCE);
			query.addScalar("constructionQuality", StringType.INSTANCE);
			query.addScalar("otherComments", StringType.INSTANCE);
			query.addScalar("conclusion", LongType.INSTANCE);
			query.addScalar("completeRequest", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("monitorId", LongType.INSTANCE);
			query.addScalar("inChargeConstructId", LongType.INSTANCE);
			query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);
			
			query.addScalar("monitorIdName", StringType.INSTANCE);
			query.addScalar("monitorIdNamePath", StringType.INSTANCE);
			query.addScalar("inChargeConstructIdName", StringType.INSTANCE);
			query.addScalar("inChargeConstructIdNamePath", StringType.INSTANCE);
			query.setParameter("workItemsAcceptanceId", dto.getWorkItemsAcceptanceId());
			query.setParameter("type2", partnerAttachType);
			
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(WorkItemsAcceptanceDTO.class));

			return (WorkItemsAcceptanceDTO) query.list().get(0);

    }
    
    @Transactional
    public boolean updateIsActive(List<Long> listId){
    		Session session = getSession();
//    		String listUpdate = new String("");
//    		for (Long id : listId) {
//    			listUpdate = listUpdate + id + ",";
//    		}
//    		listUpdate = listUpdate.substring(0, listUpdate.length() - 1);
    		StringBuilder sql = new StringBuilder();
    		sql.append("UPDATE WORK_ITEMS_ACCEPTANCE WIA SET WIA.IS_ACTIVE = '0' ");
    		sql.append("WHERE WIA.WORK_ITEMS_ACCEPTANCE_ID IN(:listUpdate)" );
    		session.createSQLQuery(sql.toString()).setParameterList("listUpdate", listId).executeUpdate();
    		session.createSQLQuery("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP WHERE DATA_TABLE_ID = 'WORK_ITEMS_ACCEPTANCE_ID' "
    				+ "AND DATA_TABLE_ID_VALUE IN(:listUpdate)").setParameterList("listUpdate", listId).executeUpdate();
			return true;
    }
    
	public boolean deleteWorkItemAcceptList(List<String> listString) {
			String list = new String(" IN (");
			for (String estimatesWorkItemId : listString) {
				list = list + estimatesWorkItemId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuilder sql = new StringBuilder("DELETE FROM WORK_ITEMS_ACCEPT_LIST WHERE ESTIMATES_WORK_ITEM_ID");
			sql.append(list);
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
			return true;
	}
	
	@Transactional
	public boolean addWorkItemAcceptList(WorkItemsAcceptanceDTO criteria) {
			for (EstimatesWorkItemsDTO dto : criteria.getCvntList()) {
				StringBuilder sql = new StringBuilder("INSERT INTO WORK_ITEMS_ACCEPT_LIST VALUES (:workItemsAcceptanceId, ");
				
				sql.append(dto.getEstimatesWorkItemId() + ")");
				SQLQuery query = getSession().createSQLQuery(sql.toString());
				query.setParameter("workItemsAcceptanceId", criteria.getWorkItemsAcceptanceId());
				query.executeUpdate();
			}
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
		WorkItemsAcceptanceDTO wDto = new WorkItemsAcceptanceDTO();
		wDto.setWorkItemsAcceptanceId(obj.getWorkItemsAcceptanceId());
		wDto.setConstructId(obj.getConstructId());
		List<EstimatesWorkItemsDTO> list = estimatesWorkItemsDAO.doSearchByWorkItemsAcceptanceId(wDto);
		String listUpdate = new String(" IN (");
		for (EstimatesWorkItemsDTO dtoEs : list) {
			listUpdate = listUpdate + dtoEs.getEstimatesWorkItemId() + ",";
		}
		listUpdate = listUpdate.substring(0, listUpdate.length() - 1);
		listUpdate = listUpdate + ") ";
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
					session.createSQLQuery("update WORK_ITEMS_ACCEPTANCE com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 2 "
							+ " where  com.WORK_ITEMS_ACCEPTANCE_ID = :id")
							.setParameter("appDate", new Date())
							.setParameter("id", obj.getWorkItemsAcceptanceId()).executeUpdate();
					session.createSQLQuery("update ESTIMATES_WORK_ITEMS set ESTIMATES_WORK_ITEMS.STATUS = 1 "
							+ " where ESTIMATES_WORK_ITEMS.ESTIMATES_WORK_ITEM_ID " + listUpdate).executeUpdate();
					////////////////////////
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
				session.createSQLQuery("update WORK_ITEMS_ACCEPTANCE com set com.APPROVAL_DATE = :appDate , com.STATUS_CA  = 3 "
						+ " where  com.WORK_ITEMS_ACCEPTANCE_ID = :id")
						.setParameter("appDate", new Date())
						.setParameter("id", obj.getWorkItemsAcceptanceId()).executeUpdate();
				///////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
	////////////////////////////////////////////////////////////////////////////
    //end tungpv
}
