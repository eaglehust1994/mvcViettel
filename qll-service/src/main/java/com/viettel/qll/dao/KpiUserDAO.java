package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.KpiUserBO;

import com.viettel.qll.dto.KpiUserDTO;


import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("kpiUserDAO")
public class KpiUserDAO extends BaseFWDAOImpl<KpiUserBO, Long> {

    public KpiUserDAO() {
        this.model = new KpiUserBO();
    }

    public KpiUserDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<KpiUserDTO> checkInfoUser(KpiUserDTO obj){
    	StringBuilder sql = new StringBuilder("");
    	sql.append(" select FULL_NAME fullName, EMPLOYEE_CODE employeeCode, USER_ROLE userRole from KPI_USER where EMPLOYEE_CODE=:employeeCode ");
    	
    	 SQLQuery query = getSession().createSQLQuery(sql.toString());
    	 
    	 query.addScalar("fullName", new StringType());
		 query.addScalar("employeeCode", new StringType());
		 query.addScalar("userRole", new LongType());
		 
		 query.setResultTransformer(Transformers.aliasToBean(KpiUserDTO.class));
		 query.setParameter("employeeCode", obj.getEmployeeCode());
		 return query.list();
    	
    }
}
