/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.SettlementRightBO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.RoleCaDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("settlementRightDAO")
public class SettlementRightDAO extends BaseFWDAOImpl<SettlementRightBO, Long> {

    public SettlementRightDAO() {
        this.model = new SettlementRightBO();
    }

    public SettlementRightDAO(Session session) {
        this.session = session;
    }
    
    
    public boolean checkUnique(SettlementRightDTO dto) {
            StringBuilder sql = new StringBuilder();
            sql.append("select SETTLEMENT_RIGHT.SETTLEMENT_RIGHT_ID settlementRightId "
            		+ "from SETTLEMENT_RIGHT "
            		+ " where SETTLEMENT_RIGHT.CONSTRUCT_ID = :ConstructId  "
            		+ "and SETTLEMENT_RIGHT.EMPLOYEE_ID = :EmployeeId "
            		+ "  and SETTLEMENT_RIGHT.ROLEID = :Roleid "
            		);
            
            SQLQuery query = getSession().createSQLQuery(sql.toString());
    		
    		query.addScalar("settlementRightId", LongType.INSTANCE);
    		
    		query.setParameter("ConstructId", dto.getConstructId());
    		query.setParameter("EmployeeId", dto.getEmployeeId());
    		query.setParameter("Roleid", dto.getRoleid());
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(SettlementRightDTO.class));
            
            if(query.list().size() > 0) {
            	return false;
            }else {
            	return true;
            }
            
	}

	public List<SettlementRightDTO> getSettlementRightByConstrt(ConstrConstructionsDTO dto) {
            StringBuilder sql = new StringBuilder();
            sql.append("select DISTINCT ROLE_CA.GROUP_SIDE groupSide, "
            		+ "ROLE_CA.ROLENAME rolename, "
            		+ "SETTLEMENT_RIGHT.ROLEID roleid, "
            		+ "SETTLEMENT_RIGHT.EMPLOYEE_ID employeeId, "
            		+ "CAT_EMPLOYEE.FULL_NAME fullName , "            		
            		+ "SYS_USER.LOGIN_NAME loginName, "
            		+ "SETTLEMENT_RIGHT.IS_CURRENT isCurrent, "
            		+ "SETTLEMENT_RIGHT.SETTLEMENT_RIGHT_ID settlementRightId "
            		+ "from SETTLEMENT_RIGHT "
            		+ "inner join ROLE_CA "
            		+ "on ROLE_CA.ROLEID = SETTLEMENT_RIGHT.ROLEID "
            		+ "inner join CAT_EMPLOYEE "
            		+ "on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID "
            		+ "left join USER_EMPLOYEE "
            		+ "on USER_EMPLOYEE.EMPLOYEE_ID = CAT_EMPLOYEE.ID "
            		+ " left join SYS_USER "
            		+ "on SYS_USER.USER_ID = USER_EMPLOYEE.USER_ID "
            		+ " where SETTLEMENT_RIGHT.CONSTRUCT_ID = :ConstructId "
            		);
            
            sql.append(" ORDER BY settlementRightId DESC ");
            
            
            SQLQuery query = getSession().createSQLQuery(sql.toString());
    		query.addScalar("groupSide", LongType.INSTANCE);
    		query.addScalar("roleid", LongType.INSTANCE);
    		query.addScalar("rolename", StringType.INSTANCE);    		
    		query.addScalar("fullName", StringType.INSTANCE);
    		query.addScalar("loginName", StringType.INSTANCE);
    		query.addScalar("isCurrent", LongType.INSTANCE);
    		query.addScalar("settlementRightId", LongType.INSTANCE);
    		query.addScalar("employeeId", LongType.INSTANCE);
    		
    		query.setParameter("ConstructId", dto.getConstructId());
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(SettlementRightDTO.class));
            
            return query.list();
            
	}
	
	public String deleteMultipleSettlement(List<String> listID) {
            StringBuilder sql = new StringBuilder();
            sql.append("delete "
            		+ "from SETTLEMENT_RIGHT ");
            sql.append(" where SETTLEMENT_RIGHT.SETTLEMENT_RIGHT_ID in (:condition)");

            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.setParameterList("condition", listID);
			query.executeUpdate();
            return "SUCCESS";
	}

	public List<SettlementRightDTO> getAllAMonitorOrBInChargeByConstructId(SettlementRightDTO dto){
            StringBuilder sql = new StringBuilder();
            sql.append("select sr.EMPLOYEE_ID userId, rc.ROLENAME rolename, ce.FULL_NAME fullName, ce.EMAIL email from SETTLEMENT_RIGHT sr inner join CAT_EMPLOYEE ce "
            		+ "on sr.EMPLOYEE_ID=ce.ID inner join ROLE_CA rc on sr.ROLEID=rc.ROLEID "
            		+ "and rc.ROLEID= :Roleid where sr.CONSTRUCT_ID= :ConstructId");
            
            SQLQuery query = getSession().createSQLQuery(sql.toString());    		
    		query.addScalar("userId", StringType.INSTANCE);
    		query.addScalar("rolename", StringType.INSTANCE);
    		query.addScalar("fullName", StringType.INSTANCE);
    		query.addScalar("email", StringType.INSTANCE);
    		
    		query.setParameter("ConstructId", dto.getConstructId());
    		query.setParameter("Roleid", dto.getRoleid());
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(SettlementRightDTO.class));
            
            return query.list();
            
	}
//minhpvn : lay all danh sach nguoi ky quyet toan a-b form 6
	public List<SettlementRightDTO> getAllAMonitorOrBInChargeByConstructIdForm6(SettlementRightDTO dto){
            StringBuilder sql = new StringBuilder();
            sql.append("select sr.ROLEID roleId, sr.EMPLOYEE_ID userId, rc.ROLENAME rolename, ce.FULL_NAME fullName, ce.EMAIL email from SETTLEMENT_RIGHT sr inner join CAT_EMPLOYEE ce "
            		+ "on sr.EMPLOYEE_ID=ce.ID inner join ROLE_CA rc on sr.ROLEID=rc.ROLEID "
            		+ " where sr.CONSTRUCT_ID= :ConstructId");
            
            SQLQuery query = getSession().createSQLQuery(sql.toString());    		
    		query.addScalar("userId", StringType.INSTANCE);
    		query.addScalar("rolename", StringType.INSTANCE);
    		query.addScalar("fullName", StringType.INSTANCE);
    		query.addScalar("email", StringType.INSTANCE);
    		query.addScalar("roleid", LongType.INSTANCE);
    		
    		query.setParameter("ConstructId", dto.getConstructId());
    		
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(SettlementRightDTO.class));
            
            return query.list();
            
            
	}
	//end minhpvn
	public List<RoleCaDTO> getRoleCaByGroupSide(SettlementRightDTO settlementRightDTO) {
            StringBuilder sql = new StringBuilder();
            sql.append("select ROLE_CA.ROLEID roleid, ROLE_CA.ROLENAME rolename "
            		+ "from ROLE_CA  "
            		+ "where ROLE_CA.GROUP_SIDE= :GroupSide ");
            
            SQLQuery query = getSession().createSQLQuery(sql.toString());    		
    		query.addScalar("roleid", LongType.INSTANCE);
    		query.addScalar("rolename", StringType.INSTANCE);
    		
    		
    		query.setParameter("GroupSide", settlementRightDTO.getGroupSide());
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(SettlementRightDTO.class));
            
            return query.list();
            
            
	}

	public void updateHiringMonitorConsult(ConstrConstructionsDTO obj) {
			StringBuilder sql = new StringBuilder();
			sql.append("update " + " CONSTR_CONSTRUCTIONS set HIRING_MONITOR_CONSULT= :HiringMonitorConsult ");
			sql.append(" where CONSTRUCT_ID= :ConstructId ");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameter("ConstructId", obj.getConstructId());
    		query.setParameter("HiringMonitorConsult", obj.getHiringMonitorConsult());
			query.executeUpdate();

	}
	
}
