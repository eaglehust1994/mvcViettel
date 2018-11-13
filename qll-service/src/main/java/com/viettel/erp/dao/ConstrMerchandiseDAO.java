/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.ConstrMerchandiseBO;
import com.viettel.erp.dto.ConstrMerchandiseDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrMerchandiseDAO")
public class ConstrMerchandiseDAO extends BaseFWDAOImpl<ConstrMerchandiseBO, Long> {

    public ConstrMerchandiseDAO() {
        this.model = new ConstrMerchandiseBO();
    }

    public ConstrMerchandiseDAO(Session session) {
        this.session = session;
    }
    
    public List<ConstrMerchandiseDTO> geValueConstrMerchandise(Long contractId){
    	StringBuilder sql = new StringBuilder("SELECT "
    			+" SUM(NVL(con1.quantity,0) *NVL(con2.ORIGINAL_PRICE,0)) valueConstrMerchandise"	
				+" FROM constr_merchandise con1, mer_entity con2"
				+" WHERE con1.mer_entity_id =con2.mer_entity_id "
				+ "AND con2.is_temp = 0 " );
		/*SELECT sum(con1.quantity * con2.ORIGINAL_PRICE)
	    FROM constr_merchandise con1,
	      mer_entity con2 
	    WHERE con1.mer_entity_id =con2.mer_entity_id
	    AND con2.is_temp         =0
	    and con1.CONSTRUCT_ID = ?;*/
    	
    	if (contractId != null) {
    		sql.append(" AND con1.CONSTRUCT_ID = :contractId");
		}
    	
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("valueConstrMerchandise", LongType.INSTANCE);
		if (contractId != null) {
    		query.setParameter("contractId", contractId);
		}
		query.setResultTransformer(Transformers.aliasToBean(ConstrMerchandiseDTO.class));
		
		return query.list();
    }
}
