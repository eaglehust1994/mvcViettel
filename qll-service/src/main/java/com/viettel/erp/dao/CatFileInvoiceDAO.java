/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.CatFileInvoiceBO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("catFileInvoiceDAO")
public class CatFileInvoiceDAO extends BaseFWDAOImpl<CatFileInvoiceBO, Long> {

    public static final String SQL_SELECT = "select invoice.CAT_FILE_INVOICE_ID catFileInvoiceId,\n" +
            "   invoice.FILE_INVOICE_CODE fileInvoiceCode,\n" +
            "   invoice.FILE_INVOICE_NAME fileInvoiceName,\n" +
            "   invoice.TYPE type,\n" +
            "   invoice.CONTRACT_TYPE contractType ,\n" +
            "   invoice.IS_ACTIVE isActive,\n" +
            "   invoice.NOTE note,\n" +
            "   invoice.SOFTWARE_LINK softwareLink,\n" +
            "   invoice.DATA_TABLE_NAME dataTableName,\n" +
            "   invoice.CREATED_DATE createdDate ,\n" +
            "   invoice.CREATED_USER_ID createdUserId, \n" +
            "   typemap.CONSTR_TYPE typeConstruction,\n" +
            "   0 isExistProfile\n" +
            " from CAT_FILE_INVOICE invoice  inner join FILE_IN_CONSTR_TYPE_MAP typemap on  typemap.CAT_FILE_INVOICE_ID = invoice.CAT_FILE_INVOICE_ID ";

    public static final String SQL_FIND_BY_EXISTS_PROFILE = "select invoice.CAT_FILE_INVOICE_ID catFileInvoiceId,\n" +
            "   invoice.FILE_INVOICE_CODE fileInvoiceCode,\n" +
            "   invoice.FILE_INVOICE_NAME fileInvoiceName,\n" +
            "   invoice.TYPE type,\n" +
            "   invoice.CONTRACT_TYPE contractType ,\n" +
            "   invoice.IS_ACTIVE isActive,\n" +
            "   invoice.NOTE note,\n" +
            "   invoice.SOFTWARE_LINK softwareLink,\n" +
            "   invoice.DATA_TABLE_NAME dataTableName,\n" +
            "   invoice.CREATED_DATE createdDate ,\n" +
            "   invoice.CREATED_USER_ID createdUserId, \n" +
            "   su.LOGIN_NAME loginName, " +
            "   typemap.CONSTR_TYPE typeConstruction,\n" +
            "  case   when invoice.cat_file_invoice_id in (select cat_file_invoice_id from CONSTR_COMPLETE_RECORDS_MAP where construction_Id = :constructionId) then 1\n" +
            "  else  0 end isExistProfile\n" +
            " from CAT_FILE_INVOICE invoice  "
            + "inner join FILE_IN_CONSTR_TYPE_MAP typemap on  typemap.CAT_FILE_INVOICE_ID = invoice.CAT_FILE_INVOICE_ID "
            +" left join sys_user su on invoice.CREATED_USER_ID=su.USER_ID " 
            + " ";

    public CatFileInvoiceDAO() {
        this.model = new CatFileInvoiceBO();
    }

    public CatFileInvoiceDAO(Session session) {
        this.session = session;
    }

    /**
     * findByExistProfile
     */
    public List<CatFileInvoiceDTO> findByExistProfile(long constructionId) {
        SQLQuery query = getSession().createSQLQuery(SQL_FIND_BY_EXISTS_PROFILE);

        query.addScalar("catFileInvoiceId", LongType.INSTANCE);
        query.addScalar("fileInvoiceCode", StringType.INSTANCE);
        query.addScalar("fileInvoiceName", StringType.INSTANCE);
        query.addScalar("type", StringType.INSTANCE);
        query.addScalar("contractType", LongType.INSTANCE);
        query.addScalar("isActive", LongType.INSTANCE);
        query.addScalar("note", StringType.INSTANCE);
        query.addScalar("softwareLink", StringType.INSTANCE);
        query.addScalar("dataTableName", StringType.INSTANCE);
        query.addScalar("createdDate", new DateType());
        query.addScalar("createdUserId", LongType.INSTANCE);
        query.addScalar("isExistProfile", StringType.INSTANCE);
        query.addScalar("typeConstruction", LongType.INSTANCE);
        query.addScalar("loginName", StringType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(CatFileInvoiceDTO.class));
        query.setParameter("constructionId", constructionId);

        return query.list();
    }


    public CatFileInvoiceDTO onlyFindByTableName(String tableName) {
        SQLQuery query = getSession().createSQLQuery("select invoice.CAT_FILE_INVOICE_ID catFileInvoiceId from CAT_FILE_INVOICE invoice " 
    + " where invoice.DATA_TABLE_NAME = :tableName AND ROWNUM = 1");

        query.addScalar("catFileInvoiceId", LongType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(CatFileInvoiceDTO.class));
        query.setParameter("tableName", tableName);

        return (CatFileInvoiceDTO) query.uniqueResult();
    }
    
    
    /**
     * findByExistProfile
     */
    public CatFileInvoiceDTO findByTableName(String tableName) {
        SQLQuery query = getSession().createSQLQuery(SQL_SELECT + " where DATA_TABLE_NAME = :tableName AND ROWNUM = 1");

        query.addScalar("catFileInvoiceId", LongType.INSTANCE);
        query.addScalar("fileInvoiceCode", StringType.INSTANCE);
        query.addScalar("fileInvoiceName", StringType.INSTANCE);
        query.addScalar("type", StringType.INSTANCE);
        query.addScalar("contractType", StringType.INSTANCE);
        query.addScalar("isActive", StringType.INSTANCE);
        query.addScalar("note", StringType.INSTANCE);
        query.addScalar("softwareLink", LongType.INSTANCE);
        query.addScalar("dataTableName", StringType.INSTANCE);
        query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
        query.addScalar("createdUserId", StringType.INSTANCE);
        query.addScalar("isExistProfile", StringType.INSTANCE);
        query.addScalar("typeConstruction", LongType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(CatFileInvoiceDTO.class));
        query.setParameter("tableName", tableName);

        return (CatFileInvoiceDTO) query.uniqueResult();
    }

	public List<CatFileInvoiceDTO> getListInvoice() {
		String sql = "select CAT_FILE_INVOICE_ID catFileInvoiceId, FILE_INVOICE_NAME fileInvoiceName from CAT_FILE_INVOICE";
		SQLQuery query = getSession().createSQLQuery(sql);

        query.addScalar("catFileInvoiceId", LongType.INSTANCE);
        query.addScalar("fileInvoiceName", StringType.INSTANCE);
        
        query.setResultTransformer(Transformers.aliasToBean(CatFileInvoiceDTO.class));

        return query.list();
	}
}
