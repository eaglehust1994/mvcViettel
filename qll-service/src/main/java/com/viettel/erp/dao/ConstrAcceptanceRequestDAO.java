/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.ConstrAcceptanceRequestBO;
import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.ConstrAcceptanceRequestDTO;
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrAcceptanceRequestDAO")
public class ConstrAcceptanceRequestDAO extends BaseFWDAOImpl<ConstrAcceptanceRequestBO, Long> {

	public ConstrAcceptanceRequestDAO() {
		this.model = new ConstrAcceptanceRequestBO();
	}

	public ConstrAcceptanceRequestDAO(Session session) {
		this.session = session;
	}
	
	@Transactional
	public Long saveTable(ConstrAcceptanceRequestDTO constrAcceptanceRequestDTO) throws Exception {
		Session session = getSession();
		Long workItemsAcceptanceId = (Long) session.save(constrAcceptanceRequestDTO.toModel());
		constrAcceptanceRequestDTO.getConstrCompleteRecordsMap().setDataTableIdValue(workItemsAcceptanceId);
		session.save(constrAcceptanceRequestDTO.getConstrCompleteRecordsMap().toModel());
		return workItemsAcceptanceId;
	}

	public String autoGenCode() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('CONSTR_ACCEPTANCE_REQUEST', 'CODE','QLHC_PYCNT',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}

	
	//Todo from Dodt: phai left join den hop dong, vi co cong trinh hop dong, doi tac null 
	public List<ConstrAcceptanceRequestDTO> listConstrAcceptanceReq(ConstrAcceptanceRequestDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select " + "CONSTR_ACCEPTANCE_REQUEST.CODE code,"
					+ "V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode,"
					+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode , "
					+ "V_CONSTRUCTION_HCQT.CONTRACT_NAME contractName, "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_NAME constrtName, "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constrtAddress, "
					+ "V_CONSTRUCTION_HCQT.STATION_CODE stationCode, "
					+ "CONSTR_ACCEPTANCE_REQUEST.CONST_ACCEPTANCE_REQUEST_ID constAcceptanceRequestId, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.SEND_PERSON_ID sendPersonId, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.ACCEPTANCE_PLACE acceptancePlace, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.RECEIVE_PERSON_ID receivePersonId, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.ACCEPTANCE_DATE acceptanceDate, "  
					+ "CONSTR_ACCEPTANCE_REQUEST.STATUS_CA statusCa, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.SIGN_DATE signDate, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.SIGN_PLACE signPlace, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.CREATED_USER_ID createdUserId, "
					+ "CONSTR_ACCEPTANCE_REQUEST.DEAR_GROUP dearGroup, " 
					+ "CONSTR_COMPLETE_RECORDS_MAP.constr_Comp_Re_Map_Id constrCompReMapId, " 
					+ "V_CONSTRUCTION_HCQT.CONSTRUCTOR_ID partnerId, "
					+ " ASM.COMMENTS comments "
					
					+ " from CONSTR_ACCEPTANCE_REQUEST "
					+ "inner join V_CONSTRUCTION_HCQT on V_CONSTRUCTION_HCQT.CONSTRUCT_ID=CONSTR_ACCEPTANCE_REQUEST.CONSTRUCT_ID "
					+ "INNER join CONSTR_COMPLETE_RECORDS_MAP on CONSTR_ACCEPTANCE_REQUEST.CONST_ACCEPTANCE_REQUEST_ID = CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_ID_VALUE AND CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_NAME='CONSTR_ACCEPTANCE_REQUEST' "
					+" left join APPROVAL_SIGN_MANAGEMENT ASM ON (CONSTR_COMPLETE_RECORDS_MAP.CONSTR_COMP_RE_MAP_ID = ASM.CONSTR_COMP_RE_MAP_ID and ASM.APPROVAL_STATUS =2)"
					+ "where CONSTR_ACCEPTANCE_REQUEST.CONSTRUCT_ID=:constructId" 
					+ " and CONSTR_ACCEPTANCE_REQUEST.IS_ACTIVE=1" 
					+ " and ROWNUM=1");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("constrtCode", StringType.INSTANCE);
			query.addScalar("constrtAddress", StringType.INSTANCE);
			query.addScalar("contractName", StringType.INSTANCE);
			
			query.addScalar("constrtName", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("stationCode", StringType.INSTANCE);
			
			query.addScalar("comments", StringType.INSTANCE);
			
			query.addScalar("acceptancePlace", StringType.INSTANCE);
			query.addScalar("constAcceptanceRequestId", LongType.INSTANCE);
			query.addScalar("sendPersonId", LongType.INSTANCE);
			query.addScalar("receivePersonId", LongType.INSTANCE);
			query.addScalar("acceptanceDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("dearGroup", StringType.INSTANCE);
			query.addScalar("partnerId", LongType.INSTANCE);
			query.addScalar("statusCa", LongType.INSTANCE);
			query.addScalar("constrCompReMapId", LongType.INSTANCE);
			query.addScalar("createdUserId", LongType.INSTANCE);
			query.setParameter("constructId", dto.getConstructId());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(ConstrAcceptanceRequestDTO.class));

			return query.list();

	}
	
	public String deleteConstrAcceptanceReq(List<String> listCode) {
//			String condition = "(";
//			for (String code : listCode) {
//				condition = condition + "'" + code + "',";
//			}
//			condition = condition.substring(0, condition.length() - 1) + ")";
			StringBuilder sql = new StringBuilder();
			sql.append("update " + "CONSTR_ACCEPTANCE_REQUEST set IS_ACTIVE=0 ");
			sql.append(" where CONSTR_ACCEPTANCE_REQUEST.CODE in :condition");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameterList("condition", listCode);
			query.executeUpdate();
			
			getSession().createSQLQuery(
					"DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'CONSTR_ACCEPTANCE_REQUEST' AND recod.CODE IN (:list)")
					.setParameterList("list", listCode).executeUpdate();
			
			return "SUCCESS";

	}

	
	 @Transactional
	    public Long deleteTable( ConstrAcceptanceRequestDTO ConstrAcc) throws Exception{
	    	      Session session = getSession();
	    	      session.delete(ConstrAcc.toModel());     
	    	      session.delete(ConstrAcc.getConstrCompleteRecordsMap().toModel());
	    	      return 0L;
	     }
	
	
	public approDTO getCheckData(approDTO obj) {
		StringBuffer sql = new StringBuffer(
				"SELECT cr.LEVEL_ORDER levelOrder,ca.EMPLOYEE_ID employeeId,cr.CREATED_USER_ID createUserId, ca.APPROVAL_ORDER approvalOrder, ca.IS_LAST isLast "
						+ "from APPROVAL_SIGN_MANAGEMENT ca " + "inner join CONSTR_COMPLETE_RECORDS_MAP cr "
						+ "on ca.CONSTR_COMP_RE_MAP_ID = cr.CONSTR_COMP_RE_MAP_ID "
						+ "where ca.CONSTR_COMP_RE_MAP_ID = :ccrmID and ca.EMPLOYEE_ID = :eID AND ROWNUM = 1");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("levelOrder", LongType.INSTANCE);
		query.addScalar("employeeId", LongType.INSTANCE);
		query.addScalar("createUserId", LongType.INSTANCE);
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
			if (obj.getStatusCa() == 1) {
				if (appCheck.getIsLast() == 1) {
					session.createSQLQuery("update CONSTR_COMPLETE_RECORDS_MAP com set com.STATUS = 2 "
							+ " where  com.CONSTR_COMP_RE_MAP_ID = :id").setParameter("id", obj.getConstrCompReMapId())
							.executeUpdate();
					
					//////////////Thay đổi thành tên bảng và id của bảng mình
					session.createSQLQuery("update CONSTR_ACCEPTANCE_REQUEST com set com.APPROVAL_DATE = :appDate, com.STATUS_CA  = 2 "
							+ " where  com.CONST_ACCEPTANCE_REQUEST_ID = :id").setParameter("appDate", new Date()).setParameter("id", obj.getConstrWorkLogsId())
							.executeUpdate();
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
				
				//////////////Thay đổi thành tên bảng và id của bảng mình
				session.createSQLQuery("update CONSTR_ACCEPTANCE_REQUEST com set com.APPROVAL_DATE = :appDate, com.STATUS_CA = 3 " 
						+ " where com.CONST_ACCEPTANCE_REQUEST_ID = :id").setParameter("appDate", new Date()).setParameter("id",
						 obj.getConstrWorkLogsId())
						 .executeUpdate();
				///////////////////////////////////////////////////
				resp = 4l;
			}
		}
		return resp;
	}
}
