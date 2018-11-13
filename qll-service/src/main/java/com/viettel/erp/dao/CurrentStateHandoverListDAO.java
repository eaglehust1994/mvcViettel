/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.CurrentStateHandoverBO;
import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("currentStateHandoverListDAO")
public class CurrentStateHandoverListDAO extends BaseFWDAOImpl<CurrentStateHandoverListBO, Long> {

    public CurrentStateHandoverListDAO() {
        this.model = new CurrentStateHandoverListBO();
    }

    public CurrentStateHandoverListDAO(Session session) {
        this.session = session;
    }
    
    public List<CurrentStateHandoverListBO> getCurrentStateHandoverByListId(Long id){
    	Session session =getSession();
    	Query query = session.createQuery("select u from currentStateHandoverList u where u.currentStateHandoverBO.currentStateHandoverId = :id");
    	query.setParameter("id", id);
    	System.out.println("-----------"+id);
    	return query.list();
    }
	public boolean deleteMutilRecord(List<String> listReportID) {
			String list = new String(" IN (");
			for (String constrWorkLogsId : listReportID) {
				list = list + constrWorkLogsId + ",";
			}
			list = list.substring(0, list.length() - 1);
			list = list + ") ";

			StringBuffer sql = new StringBuffer(
					"delete from CURRENT_STATE_HANDOVER_LIST where CURRENT_STATE_HANDOVER_LIST_ID");
			sql.append(list);
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
			return true;
	}
}
