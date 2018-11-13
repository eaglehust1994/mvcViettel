/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.ConstrConstructionsSearchDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmExportDTO;
import com.viettel.erp.dto.PhieuYeuCauNghiemThuDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrConstructionsDAO")
public class ConstrConstructionsDAO extends BaseFWDAOImpl<ConstrConstructionsBO, Long> {
	
	@Value("${completionPartner.attachType}")
    private Long partnerAttachType;

    public ConstrConstructionsDAO() {
        this.model = new ConstrConstructionsBO();
    }

    public ConstrConstructionsDAO(Session session) {
        this.session = session;
    }
    
    public List<ConstrConstructionsDTO> getConstructions(ConstrConstructionsDTO dto) {
            StringBuilder sql = new StringBuilder();
            sql.append("select V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode, "
            		+ "V_CONSTRUCTION_HCQT.SIGNED_DATE signDate, "
            		+ "V_CONSTRUCTION_HCQT.CONSTRUCTOR_NAME partnerName, "
            		+ "V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode , "
            		+ "V_CONSTRUCTION_HCQT.CONSTRUCT_ID constructId , "
            		+ "V_CONSTRUCTION_HCQT.CONSTR_TYPE_NAME constrTypeName, "
            		+ "V_CONSTRUCTION_HCQT.STATION_CODE stationCode, "
            		+ "V_CONSTRUCTION_HCQT.PROVINCE_NAME provinceName, "
            		+ "V_CONSTRUCTION_HCQT.CONSTRT_NAME constrtName, "
            		+ "V_CONSTRUCTION_HCQT.HIRING_MONITOR_CONSULT hiringMonitorConsult, "
            		+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constrtAddress "
            		+ "from V_CONSTRUCTION_HCQT "
            		);
            sql.append(" where V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :constructId");
            
            SQLQuery query = getSession().createSQLQuery(sql.toString());
    		query.addScalar("signDate", StandardBasicTypes.TIMESTAMP);
    		query.addScalar("contractCode", StringType.INSTANCE);
    		query.addScalar("constrtCode", StringType.INSTANCE);
    		query.addScalar("constrtName", StringType.INSTANCE);
    		query.addScalar("constrtAddress", StringType.INSTANCE);
    		query.addScalar("constrTypeName", StringType.INSTANCE);
    		query.addScalar("provinceName", StringType.INSTANCE);
    		query.addScalar("constructId", LongType.INSTANCE);
    		query.addScalar("partnerName", StringType.INSTANCE);
    		query.addScalar("hiringMonitorConsult", LongType.INSTANCE);
    		query.addScalar("stationCode", StringType.INSTANCE);
    		query.setParameter("constructId", dto.getConstructId());
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(ConstrConstructionsDTO.class));
            
            return query.list();
            
            
	}
    
    public List<ConstrConstructionsBO> getAllandSearch(ConstrConstructionsSearchDTO dto) {

		Query q = getSession().createQuery(Joiner.on(" ").join("from constructions p where 1=1 ",
			//	StringUtils.isNotEmpty(dto.getContractNo()) ? " and p.batchName like :batchName" : "",
				StringUtils.isNotEmpty(dto.getConstrtCode()) ? " and p.constrtCode like :constrtCode" : "")).setMaxResults(10);

		
		if (StringUtils.isNotEmpty(dto.getConstrtCode())) {
			q.setParameter("constrtCode", "%" + dto.getConstrtCode() + "%");
		}

		return q.setCacheable(true).list();
	}

	public PhieuYeuCauNghiemThuDTO getDataExportFile(PhieuYeuCauNghiemThuDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select " 
					+ "V_CONSTRUCTION_HCQT.STATION_CODE code," 
					+" CONSTR_ACCEPTANCE_REQUEST.CONST_ACCEPTANCE_REQUEST_ID constAcceptanceRequestId, "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_NAME constrName,"
					+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode , "
					+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS address, "
					+ "V_CONSTRUCTION_HCQT.CONSTRUCTOR_NAME constractorName, "
					+ "TO_CHAR(CONSTR_ACCEPTANCE_REQUEST.ACCEPTANCE_DATE, 'DD-MM-YYYY HH24:MI:SS') acceptanceDate, "
					+ "CONSTR_ACCEPTANCE_REQUEST.ACCEPTANCE_PLACE place, "
					+ "CAT_EMPLOYEE.FULL_NAME aperson1, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.SIGN_DATE signDate, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.SIGN_PLACE signPlace, " 
					+ "CONSTR_ACCEPTANCE_REQUEST.dear_group dearGroup ,"
					+ "CONSTR_ACCEPTANCE_REQUEST.STATUS_CA statusca ,"
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT SEND_PERSON_ID FROM CONSTR_ACCEPTANCE_REQUEST WHERE CONST_ACCEPTANCE_REQUEST_ID = :constAcceptanceRequestId"+")) sendPersonName, " 
					+ "(SELECT ID FROM CAT_EMPLOYEE WHERE ID = (SELECT SEND_PERSON_ID FROM CONSTR_ACCEPTANCE_REQUEST WHERE CONST_ACCEPTANCE_REQUEST_ID = :constAcceptanceRequestId"+")) sendPersonIdReady, " 
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT SEND_PERSON_ID FROM CONSTR_ACCEPTANCE_REQUEST WHERE CONST_ACCEPTANCE_REQUEST_ID = :constAcceptanceRequestId"+")) sendPersonId, " 
					+ "(SELECT FULL_NAME FROM CAT_EMPLOYEE WHERE ID = (SELECT RECEIVE_PERSON_ID FROM CONSTR_ACCEPTANCE_REQUEST WHERE CONST_ACCEPTANCE_REQUEST_ID = :constAcceptanceRequestId"+")) receivePersonName, "
					+ "(SELECT DOCUMENT_PATH FROM UTIL_ATTACHED_DOCUMENTS WHERE TYPE = :type2 AND PARENT_ID = (SELECT RECEIVE_PERSON_ID FROM CONSTR_ACCEPTANCE_REQUEST WHERE CONST_ACCEPTANCE_REQUEST_ID = :constAcceptanceRequestId"+")) receivePersonId "
					
					
					
					+ " from CONSTR_ACCEPTANCE_REQUEST "
					+ "inner join V_CONSTRUCTION_HCQT on V_CONSTRUCTION_HCQT.CONSTRUCT_ID=CONSTR_ACCEPTANCE_REQUEST.CONSTRUCT_ID "
					+ "inner join CAT_EMPLOYEE on CAT_EMPLOYEE.ID=CONSTR_ACCEPTANCE_REQUEST.RECEIVE_PERSON_ID "
					
					+ "where CONSTR_ACCEPTANCE_REQUEST.CONST_ACCEPTANCE_REQUEST_ID=:constAcceptanceRequestId" 
					+ "");
			
			if (dto.getContractId() != null){
				sql.append(" AND V_CONSTRUCTION_HCQT.CONTRACT_ID = :contractId ");
			}

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("constrName", StringType.INSTANCE);
			query.addScalar("contractCode", StringType.INSTANCE);
			query.addScalar("address", StringType.INSTANCE);
			query.addScalar("constractorName", StringType.INSTANCE);
			query.addScalar("place", StringType.INSTANCE);
			query.addScalar("acceptanceDate", StringType.INSTANCE);
			query.addScalar("aperson1", StringType.INSTANCE);
			query.addScalar("signDate", TimestampType.INSTANCE);
			query.addScalar("signPlace", StringType.INSTANCE);
			query.addScalar("dearGroup", StringType.INSTANCE);
			query.addScalar("statusca", LongType.INSTANCE);
			query.addScalar("sendPersonName", StringType.INSTANCE);
			query.addScalar("receivePersonName", StringType.INSTANCE);
			query.addScalar("sendPersonIdReady", StringType.INSTANCE);
			query.addScalar("sendPersonId", StringType.INSTANCE);
			query.addScalar("receivePersonId", StringType.INSTANCE);
			
			query.addScalar("constAcceptanceRequestId", StringType.INSTANCE);
			query.setParameter("constAcceptanceRequestId", dto.getConstAcceptanceRequestId());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(PhieuYeuCauNghiemThuDTO.class));
			
			if (dto.getContractId() != null){
				query.setParameter("contractId", dto.getContractId());
			}
			query.setParameter("type2", partnerAttachType);

			return (PhieuYeuCauNghiemThuDTO) query.list().get(0);

	}

	public PhieuYeuCauNghiemThuDTO getDataExportExtra(PhieuYeuCauNghiemThuDTO obj){
		String sql ="SELECT appr.APPROVAL_DATE receiveDay,appr.APPROVAL_STATUS signStatus FROM APPROVAL_SIGN_MANAGEMENT appr "
+" INNER JOIN (SELECT * FROM CONSTR_COMPLETE_RECORDS_MAP Con WHERE Con.DATA_TABLE_NAME='CONSTR_ACCEPTANCE_REQUEST') SubCon "
+" ON SubCon.CONSTR_COMP_RE_MAP_ID =appr.CONSTR_COMP_RE_MAP_ID "
+" INNER JOIN CONSTR_ACCEPTANCE_REQUEST conAcc "
+" ON SubCon.DATA_TABLE_ID_VALUE = conAcc.CONST_ACCEPTANCE_REQUEST_ID "
+" WHERE appr.EMPLOYEE_ID = :employeeId AND conAcc.CONST_ACCEPTANCE_REQUEST_ID = :constrAccID ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("receiveDay", new DateType());
		query.addScalar("signStatus", LongType.INSTANCE);
		query.setParameter("employeeId", obj.getSendPersonIdReady());
		query.setParameter("constrAccID", obj.getConstAcceptanceRequestId());
		query.setResultTransformer(Transformers.aliasToBean(PhieuYeuCauNghiemThuDTO.class));
		List<PhieuYeuCauNghiemThuDTO> listResult = query.list();
		if(listResult.size()>0){
			obj.setReceiveDay(listResult.get(0).getReceiveDay());
			obj.setSignStatus(listResult.get(0).getSignStatus());
		}
		
		return obj;
	}
	
    
}
