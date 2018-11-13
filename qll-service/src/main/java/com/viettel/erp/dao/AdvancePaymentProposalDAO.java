/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.AdvancePaymentProposalBO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("advancePaymentProposalDAO")
public class AdvancePaymentProposalDAO extends BaseFWDAOImpl<AdvancePaymentProposalBO, Long> {

    public AdvancePaymentProposalDAO() {
        this.model = new AdvancePaymentProposalBO();
    }

    public AdvancePaymentProposalDAO(Session session) {
        this.session = session;
    }
    
    public AdvancePaymentProposalBO getAdvancePaymentByContructId(long constructId){
    	String hQuery= "from adPaymentProposal a where a.constructId= :constructId";
    	Query query  = getSession().createQuery(hQuery);  	
    	query.setParameter("constructId", constructId);
    	
    	List<AdvancePaymentProposalBO> listBO = query.list();
    	if(listBO.size()>0){
    		return listBO.get(0);
    	}else{
    		return new AdvancePaymentProposalBO();
    	}
    }
}
