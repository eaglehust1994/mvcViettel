/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.ConstrAcceptLostNoteBO;
import com.viettel.erp.dto.AssetManageReqDTO;
import com.viettel.erp.dto.ConstrAcceptLostNoteDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrAcceptLostNoteDAO")
public class ConstrAcceptLostNoteDAO extends BaseFWDAOImpl<ConstrAcceptLostNoteBO, Long> {

    public ConstrAcceptLostNoteDAO() {
        this.model = new ConstrAcceptLostNoteBO();
    }

    public ConstrAcceptLostNoteDAO(Session session) {
        this.session = session;
    }
    
    public List<ConstrAcceptLostNoteDTO> getValueLoss(Long contractId){
    	StringBuilder sql = new StringBuilder("SELECT "
    			+" SUM(NVL(lst2.count,0) *NVL(lst3.ORIGINAL_PRICE,0)) valueLoss"	
				+" FROM constr_accept_lost_note lst1, constr_accept_lost_entity lst2, mer_entity lst3"
				+" WHERE lst1.accept_note_id=lst2.accept_note_id "
				+ "AND lst2.mer_entity_id   =lst3.mer_entity_id "
				+ "AND lst3.is_temp         =0 " );
		/*SELECT SUM(lst2.count * lst3.ORIGINAL_PRICE)
	    FROM constr_accept_lost_note lst1,
	      constr_accept_lost_entity lst2,
	      mer_entity lst3
	    WHERE lst1.accept_note_id=lst2.accept_note_id
	    AND lst2.mer_entity_id   =lst3.mer_entity_id
	    AND lst3.is_temp         =0
	    AND lst1.constr_id       =?;*/
    	
    	if (contractId != null) {
    		sql.append(" AND lst1.constr_id = :contractId");
		}
    	
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("valueLoss", LongType.INSTANCE);
		if (contractId != null) {
    		query.setParameter("contractId", contractId);
		}
		query.setResultTransformer(Transformers.aliasToBean(ConstrAcceptLostNoteDTO.class));
		
		return query.list();
    }
}
