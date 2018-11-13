/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.AbSettlementWorkBO;
import com.viettel.erp.dto.AbSettlementWorkDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("abSettlementWorkDAO")
public class AbSettlementWorkDAO extends BaseFWDAOImpl<AbSettlementWorkBO, Long> {

    public AbSettlementWorkDAO() {
        this.model = new AbSettlementWorkBO();
    }

    public AbSettlementWorkDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
    public String autoGenCode() {
     StringBuffer sql = new StringBuffer("select get_next_code('AB_SETTLEMENT_WORK', 'CODE','HSHC_QTAB',6) code from dual");
     SQLQuery query = getSession().createSQLQuery(sql.toString());
     query.addScalar("code", StandardBasicTypes.STRING);
     return (String) query.uniqueResult();
    }
    
    @Transactional
    public Long saveTable( AbSettlementWorkDTO  dto) throws Exception{
           Session session = getSession();
           Long id = (Long) session.save(dto.toModel());
           dto.getConstrCompleteRecordsMap().setDataTableIdValue(id);     
           session.save(dto.getConstrCompleteRecordsMap().toModel());
           return id;
     }
    public AbSettlementWorkDTO getAbSettIdByConstrId(Long constructId){
    	StringBuilder sql = new StringBuilder();
		sql.append("SELECT "
				+ "absw.AB_SETTLEMENT_WORK_ID abSettlementWorkId, "
				+ "absw.CODE code, "
				+ "absw.CREATED_DATE createdDate, "
				+ "absw.CREATED_USER_ID createdUserId, "
				+ "absw.A_DIRECTOR_ID adirectorId, "
				+ "absw.B_DIRECTOR_ID bdirectorId, "
				+ "absw.STATUS_CA statusCa, "
				+ "absw.DOCUMENT_CA_ID documentCaId, "
				+ "absw.IS_ACTIVE isActive, "
				+ "absw.CONSTRUCT_ID constructId, "
				+ "cvc.constr_comp_re_map_id constrCompReMapId "
				+ "FROM AB_SETTLEMENT_WORK absw "
				+ "LEFT JOIN CONSTR_COMPLETE_RECORDS_MAP cvc ON absw.AB_SETTLEMENT_WORK_ID = cvc.data_table_id_value and cvc.data_table_name = 'AB_SETTLEMENT_WORK' "
				+ "WHERE absw.CONSTRUCT_ID = :constructId AND ROWNUM = 1");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("abSettlementWorkId", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("createdUserId", LongType.INSTANCE);
		query.addScalar("adirectorId", LongType.INSTANCE);
		query.addScalar("bdirectorId", LongType.INSTANCE);
		query.addScalar("statusCa", LongType.INSTANCE);
		query.addScalar("documentCaId", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrCompReMapId", LongType.INSTANCE);
		query.setParameter("constructId", constructId);
		
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setResultTransformer(Transformers.aliasToBean(AbSettlementWorkDTO.class));
		return (AbSettlementWorkDTO) query.uniqueResult();
    }
    
}
