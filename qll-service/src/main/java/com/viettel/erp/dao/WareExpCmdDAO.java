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
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.WareExpCmdBO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.WareExpCmdDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("wareExpCmdDAO")
public class WareExpCmdDAO extends BaseFWDAOImpl<WareExpCmdBO, Long> {

    public WareExpCmdDAO() {
        this.model = new WareExpCmdBO();
    }

    public WareExpCmdDAO(Session session) {
        this.session = session;
    }

	public String deleteWareExpCmd(List<String> listID) {
			String condition = "(";
			for (String code : listID) {
				condition = condition + code + ",";
			}
			condition = condition.substring(0, condition.length()-1) +")";
            StringBuilder sql = new StringBuilder();
            sql.append("delete "
            		+ "from WARE_EXP_NOTE ");
            sql.append(" where WARE_EXP_NOTE.DELIVERY_NOTE_ID in "+ condition);

            SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.executeUpdate();
            return "SUCCESS";
	}

	public List<WareExpCmdDTO> getWareExpCmdByConstrt(ConstrConstructionsDTO dto) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT A.EXP_NOTE_CODE expNoteCode ,"
            		+ "to_char(A.CREATED_DATE,'dd/mm/yyyy') createdDateStr, "
            		+ "to_char(B.ACTUAL_EXP_DATE,'dd/mm/yyyy') actualExpDateStr,  "
            		+ "B.STATUS status "
            		+ "FROM WARE_EXP_CMD A "
            		+ "JOIN ADMIN_STOCK.WARE_EXP_NOTE B ON A.EXP_CMD_ID=B.EXP_CMD_ID  "
            		+ "WHERE A.DEST_TYPE=3  AND A.STATUS=5  AND B.STATUS IN(2,4) AND A.CONSTRUCTION_ID = :constructId");
            sql.append(" ORDER BY A.EXP_NOTE_CODE");
           
            SQLQuery query = getSession().createSQLQuery(sql.toString());
    		query.addScalar("expNoteCode", StringType.INSTANCE);
    		query.addScalar("createdDateStr", StringType.INSTANCE);
    		query.addScalar("actualExpDateStr", StringType.INSTANCE);
    		query.addScalar("status", LongType.INSTANCE);
    		
    		query.setParameter("constructId", dto.getConstructId());
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(WareExpCmdDTO.class));
            
            return query.list();
	}

	public List<WareExpCmdDTO> getwareExpCmdByPxk(List<String> listPxk) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT A.EXP_NOTE_CODE expNoteCode ,"
            		+ "to_char(A.CREATED_DATE,'dd/mm/yyyy') createdDateStr, "
            		+ "to_char(B.ACTUAL_EXP_DATE,'dd/mm/yyyy') actualExpDateStr,  "
            		+ "B.STATUS status "
            		+ "FROM WARE_EXP_CMD A "
            		+ "JOIN ADMIN_STOCK.WARE_EXP_NOTE B ON A.EXP_CMD_ID=B.EXP_CMD_ID  "
            		+ "WHERE A.DEST_TYPE=3  AND A.STATUS=5  AND B.STATUS IN(2,4) AND A.EXP_NOTE_CODE in (:condition)");
            sql.append(" ORDER BY A.EXP_NOTE_CODE");
           
            SQLQuery query = getSession().createSQLQuery(sql.toString());
    		query.addScalar("expNoteCode", StringType.INSTANCE);
    		query.addScalar("createdDateStr", StringType.INSTANCE);
    		query.addScalar("actualExpDateStr", StringType.INSTANCE);
    		query.addScalar("status", LongType.INSTANCE);
    		query.setParameterList("condition", listPxk);
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(WareExpCmdDTO.class));
            
            return query.list();
	}
}
