/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
public class UntilsDAO {
	public  String autoGenCode(Session session, String tableName, String value) {
		StringBuffer sql = new StringBuffer("select get_next_code(':tableName', 'CODE',':value',6) code from dual");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.addScalar("code", StandardBasicTypes.STRING);

		query.setParameter("tableName", tableName);
		query.setParameter("value", value);
		return (String) query.uniqueResult();
	}

}
