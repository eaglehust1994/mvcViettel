/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.bo.SignVofficeDetailBO;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("signVofficeDetailDAO")
public class SignVofficeDetailDAO extends BaseFWDAOImpl<SignVofficeDetailBO, Long> {

    public SignVofficeDetailDAO() {
        this.model = new SignVofficeDetailBO();
    }

    public SignVofficeDetailDAO(Session session) {
        this.session = session;
    }
    
    
    
}
