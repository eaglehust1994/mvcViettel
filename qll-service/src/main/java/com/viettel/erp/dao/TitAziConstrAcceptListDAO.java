/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.TitAziConstrAcceptListBO;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("titAziConstrAcceptListDAO")
public class TitAziConstrAcceptListDAO extends BaseFWDAOImpl<TitAziConstrAcceptListBO, Long> {

    public TitAziConstrAcceptListDAO() {
        this.model = new TitAziConstrAcceptListBO();
    }

    public TitAziConstrAcceptListDAO(Session session) {
        this.session = session;
    }
    
    public List<TitAziConstrAcceptListBO> getListById(Long titAziConstrAcceptId){
    	TitAziConstrAcceptListDTO dto = new TitAziConstrAcceptListDTO();
    	 dto.setTitAziConAcceptListId(titAziConstrAcceptId);
    	Query q= getSession().createQuery("from titaziconstracceptlist t where t.titAziConstrAccept.titAziConstrAcceptId = :titAziConstrAcceptId");
    	q.setParameter("titAziConstrAcceptId", dto.getTitAziConAcceptListId());
    	
    	return q.setCacheable(true).list();
    }
    
    @SuppressWarnings("unchecked")
	public boolean deleteList(List<String> listString) {
			String list = new String(" IN (");
			for (String constrWorkLogsId : listString) {
				list = list + constrWorkLogsId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuffer sql = new StringBuffer("DELETE TIT_AZI_CONSTR_ACCEPT_LIST where TIT_AZI_CON_ACCEPT_LIST_ID");
			sql.append(list);
			System.out.println(sql.toString());
			System.out.println(listString.size());
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
			return true;
	}
    
}
