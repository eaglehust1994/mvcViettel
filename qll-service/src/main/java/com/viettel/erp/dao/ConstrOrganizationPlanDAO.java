/*

 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.ConstrOrganizationPlanBO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.ConstrOrganizationPlanDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrOrganizationPlanDAO")
public class ConstrOrganizationPlanDAO extends BaseFWDAOImpl<ConstrOrganizationPlanBO, Long> {
	
	@Value("${constrOrganization.attachTypeKey}")
    private Long attachTypeKey;
	@Value("${constrOrganization.attachTypeValue}")
    private Long attachTypeValue;
	@Value("${employee_roleID_CDT_DDPN}")
    private Long role103;
	public ConstrOrganizationPlanDAO() {
		this.model = new ConstrOrganizationPlanBO();
	}

	public ConstrOrganizationPlanDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<ConstrOrganizationPlanDTO> getAllConstrOrganizationPlan(ConstrOrganizationPlanDTO dto) {
		StringBuffer sql = new StringBuffer("select " + "co.constr_org_plan_id constrOrgPlanId ," + "co.code code,"
				+ " co.construct_id constructId, co.created_User_Id createdUserId," + " co.REPRESENT_INVESTOR_ID representInvestorId,"
				+ " co.REPRESENT_CONTRACTOR_ID representContractorId,"
				+ " co.DOCUMENT_CA_ID documentCaId, ua.DOCUMENT_NAME documentName , ua.DOCUMENT_PATH documentPath, "
				+ " co.status_ca statusCa, ce.Full_name fullName, cvc.constr_comp_re_map_id constrCompReMapId, "
				+ " DOCUMENT_CA.COMMENT_CA comments "
				+ " from CONSTR_ORGANIZATION_PLAN co "
				+ " LEFT JOIN DOCUMENT_CA ON co.DOCUMENT_CA_ID = DOCUMENT_CA.DOCUMENT_CA_ID "
				+ " left join cat_employee ce on ce.id = co.REPRESENT_INVESTOR_ID "
				+ " JOIN V_CONSTRUCTION_HCQT ON V_CONSTRUCTION_HCQT.CONSTRUCT_ID = co.CONSTRUCT_ID "
				+ " join CONSTR_COMPLETE_RECORDS_MAP cvc "
				+ " on co.CONSTR_ORG_PLAN_ID = cvc.data_table_id_value and cvc.data_table_name = 'CONSTR_ORGANIZATION_PLAN' "
				+ " join UTIL_ATTACHED_DOCUMENTS ua on ua.PARENT_ID = co.constr_org_plan_id and ua.type = :type1 "
				+ " WHERE 1=1 and co.is_active = 1 and co.construct_id = :constructId ");
		
		if (dto.getContractId() != null){
			sql.append(" AND V_CONSTRUCTION_HCQT.CONTRACT_ID = :contractId ");
		}
		sql.append(" order By co.constr_org_plan_id DESC ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrOrgPlanId", LongType.INSTANCE);
		query.addScalar("representInvestorId", LongType.INSTANCE);
		query.addScalar("representContractorId", LongType.INSTANCE);
		query.addScalar("documentCaId", LongType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("fullName", StringType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.addScalar("documentName", StringType.INSTANCE);
		query.addScalar("documentPath", StringType.INSTANCE);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("comments", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(ConstrOrganizationPlanDTO.class));
		query.setParameter("constructId", dto.getConstructId());
		
		if (dto.getContractId() != null){
			query.setParameter("contractId", dto.getContractId());
		}
		query.setParameter("type1", attachTypeKey);
		List<ConstrOrganizationPlanDTO> list = query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<ConstrOrganizationPlanDTO> getAllConstrOrganizationPlanChild(ConstrOrganizationPlanDTO dto) {
		StringBuffer sql = new StringBuffer("select " 
				+ " ua.DOCUMENT_NAME documentName , ua.DOCUMENT_PATH documentPath "
				+ " from CONSTR_ORGANIZATION_PLAN co "
				+ " join UTIL_ATTACHED_DOCUMENTS ua on ua.PARENT_ID = co.CONSTR_ORG_PLAN_ID and ua.TYPE = :type2 "
				+ " WHERE co.CONSTRUCT_ID = :constructId and co.CODE = :code ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("documentName", StringType.INSTANCE);
		query.addScalar("documentPath", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(ConstrOrganizationPlanDTO.class));
		query.setParameter("constructId", dto.getConstructId());
		query.setParameter("code", dto.getCode());
		query.setParameter("type2", attachTypeValue);
		List<ConstrOrganizationPlanDTO> list = query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public Long updateDataTable(ConstrOrganizationPlanDTO obj) {
		StringBuffer sql = new StringBuffer(
				"update CONSTR_ORGANIZATION_PLAN co set REPRESENT_INVESTOR_ID = :representInvestorId,"
				+ " co.REPRESENT_CONTRACTOR_ID = :representContractorId,co.STATUS_CA = 0 " + "where CONSTR_ORG_PLAN_ID =:constrOrgPlanId ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("representInvestorId", obj.getRepresentInvestorId());
		query.setParameter("representContractorId", obj.getRepresentContractorId());
		query.setParameter("constrOrgPlanId", obj.getConstrOrgPlanId());
		query.executeUpdate();
		return obj.getConstrOrgPlanId();
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void deleteConstrOrganizationPlan(List<String> listConstrOrgPlanId) {
		String list = new String(" IN (");
		for (String constrWorkLogsId : listConstrOrgPlanId) {
			list = list + constrWorkLogsId + ",";
		}
		list = list.substring(0, list.length() - 1);
		list = list + ") ";

		StringBuffer sql = new StringBuffer("update CONSTR_ORGANIZATION_PLAN " + "set is_active = 0"
				+ "where constr_org_plan_id ");
		sql.append(list);
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.executeUpdate();
		StringBuffer sqlConstr= new StringBuffer("DELETE FROM CONSTR_COMPLETE_RECORDS_MAP recod WHERE recod.DATA_TABLE_NAME = 'CONSTR_ORGANIZATION_PLAN' AND recod.DATA_TABLE_ID_VALUE ");
		sqlConstr.append(list);
		SQLQuery queryConstr = getSession().createSQLQuery(sqlConstr.toString());
		queryConstr.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CatEmployeeDTO> getEmployee(String contructID) {
		StringBuffer sql = new StringBuffer("SELECT CE.FULL_NAME , ce.ID id" + "FROM "
				+ "(SELECT * FROM settlement_right WHERE construct_id = :contructID and roleid = :role103) DS "
				+ "LEFT JOIN  CAT_EMPLOYEE CE " + "oN DS.EMPLOYEE_ID = CE.id");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("fullName", StringType.INSTANCE);
		query.addScalar("id", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
		query.setParameter("contructID", contructID);
		query.setParameter("role103", role103);

		List<CatEmployeeDTO> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public String autoGenCodeConstrOrganizationPlan() {
		StringBuffer sql = new StringBuffer(
				"select get_next_code('constr_organization_plan', 'CODE','PATCTC',6) code from dual");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);
		return (String) query.uniqueResult();
	}
	
	public boolean checkStatusDatabase(String constrConstrOrganizationPlan) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT CO.STATUS_CA statusCa"
				+ " FROM CONSTR_ORGANIZATION_PLAN CO  "
				+ " WHERE CO.CONSTR_ORG_PLAN_ID = :constrConstrOrganizationPlan");
				
		SQLQuery query = getSession().createSQLQuery(sqlBuffer.toString());
		query.addScalar("statusCa", LongType.INSTANCE);
		query.setParameter("constrConstrOrganizationPlan", constrConstrOrganizationPlan);
		
		Long statusCa = (Long) query.uniqueResult();
		if(statusCa==0L || statusCa==3L) {
			return false;
		} else {
			return true;
		}
	}
	
	

}
