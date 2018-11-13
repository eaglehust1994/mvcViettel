/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.IntergratedContractBO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.erp.dto.IntergratedContractDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("intergratedContractDAO")
public class IntergratedContractDAO extends BaseFWDAOImpl<IntergratedContractBO, Long> {
	
	@SuppressWarnings("unchecked")
	public List<IntergratedContractDTO> getIntergratedContractConstrt(IntergratedContractDTO dto) {
            StringBuilder sql = new StringBuilder();
            sql.append("select V_CONSTRUCTION_HCQT.CONTRACT_NAME contractName , "
            		+ "V_CONSTRUCTION_HCQT.CONTRACT_CODE contractCode, "
            		+ "V_CONSTRUCTION_HCQT.CONSTRUCT_ID constructId , "
            		+ "V_CONSTRUCTION_HCQT.CONSTRT_CODE constrtCode , "
            		+ "V_CONSTRUCTION_HCQT.CONSTRT_NAME constrtName, "
            		+ "V_CONSTRUCTION_HCQT.CONSTRT_ADDRESS constrtAddress "
            		+ "from V_CONSTRUCTION_HCQT ");
            sql.append(" where ");
            sql.append("V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :cnstructId" );

            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.setParameter("cnstructId",  dto.getConstructId());
            
    		query.addScalar("contractName", StringType.INSTANCE);
    		query.addScalar("contractCode", StringType.INSTANCE);
    		query.addScalar("constrtCode", StringType.INSTANCE);
    		query.addScalar("constrtName", StringType.INSTANCE);
    		query.addScalar("constrtAddress", StringType.INSTANCE);
    		query.addScalar("constructId", LongType.INSTANCE);
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(IntergratedContractDTO.class));
            
            return query.list();
            
            

    }

    public IntergratedContractDAO() {
        this.model = new IntergratedContractBO();
    }

    public IntergratedContractDAO(Session session) {
        this.session = session;
    }

	public List<CatPartnersDTO> getPartner(CatEmployeeDTO dto) {
            StringBuilder sql = new StringBuilder();
            sql.append("select V_CONSTRUCTION_HCQT.CONSTRUCTOR_NAME partnerName,  V_CONSTRUCTION_HCQT.CONSTRUCTOR_ID partnerId "
            		
            		+ "from V_CONSTRUCTION_HCQT "
            		+ "");
            
            sql.append(" where ");
            sql.append("V_CONSTRUCTION_HCQT.CONSTRUCT_ID = :cnstructId");

            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.setParameter("cnstructId",  dto.getConstructId());
    		query.addScalar("partnerName", StringType.INSTANCE);
    		query.addScalar("partnerId", LongType.INSTANCE);
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(CatPartnersDTO.class));
            
            return query.list();
            
            
	}
}
