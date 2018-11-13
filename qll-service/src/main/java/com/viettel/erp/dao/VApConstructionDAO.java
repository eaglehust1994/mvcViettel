/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.viettel.erp.bo.VApConstructionBO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("vApConstructionDAO")
public class VApConstructionDAO extends BaseFWDAOImpl<VApConstructionBO, Long> {

    public VApConstructionDAO() {
        this.model = new VApConstructionBO();
    }

    public VApConstructionDAO(Session session) {
        this.session = session;
    }
}
