/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.ApprovalSignManagementBO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("approvalSignManagementDAO")
public class ApprovalSignManagementDAO extends BaseFWDAOImpl<ApprovalSignManagementBO, Long> {

    public ApprovalSignManagementDAO() {
        this.model = new ApprovalSignManagementBO();
    }

    public ApprovalSignManagementDAO(Session session) {
        this.session = session;
    }
    public boolean cancelAprroval(ApprovalSignManagementDTO dto){
    		Session session = getSession();
			StringBuilder sql = new StringBuilder();
    		sql.append("DELETE FROM APPROVAL_SIGN_MANAGEMENT WHERE CONSTR_COMP_RE_MAP_ID = " + dto.getConstrCompReMapId());
    		session.createSQLQuery(sql.toString()).executeUpdate();
    		session.createSQLQuery("UPDATE CONSTR_COMPLETE_RECORDS_MAP SET CONSTR_COMPLETE_RECORDS_MAP.STATUS = '0', CONSTR_COMPLETE_RECORDS_MAP.LEVEL_ORDER = '' "
    				+ "WHERE CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_NAME = :tableName"
    				+" AND CONSTR_COMPLETE_RECORDS_MAP.DATA_TABLE_ID_VALUE = :tableId")
    				.setParameter("tableName", dto.getTableName())
    				.setParameter("tableId", dto.getTableId()).executeUpdate();
    		session.createSQLQuery("UPDATE "+dto.getTableName()+" SET "+dto.getTableName()+".STATUS_CA = '0' "
    				+ "WHERE "+dto.getTableName()+"."+dto.getTableIdField()+" = :tableId")
    				.setParameter("tableId", dto.getTableId()).executeUpdate();
			return true;
    }
}
