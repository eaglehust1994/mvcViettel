package com.viettel.qll.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblRolesBO;
import com.viettel.qll.dto.TblRolesDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author hailh10
 */
@Repository("tblRolesDAO")
public class TblRolesDAO extends BaseFWDAOImpl<TblRolesBO, Long> {

    public TblRolesDAO() {
        this.model = new TblRolesBO();
    }

    public TblRolesDAO(Session session) {
        this.session = session;
    }	
    
    
	public List<TblRolesDTO> selectRoles() {
		StringBuilder sql = new StringBuilder("Select TBL_ROLES_ID tblRolesId,ROLE_CODE roleCode,ROLE_NAME roleName "
				+ " from TBL_ROLES order by TBL_ROLES_ID");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("tblRolesId", new LongType());
		query.addScalar("roleName", new StringType());
		query.addScalar("roleCode", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblRolesDTO.class));

		return  query.list();
	} 
  
}
