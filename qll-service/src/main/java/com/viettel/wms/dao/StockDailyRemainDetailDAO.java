/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockDailyRemainDetailBO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("stockDailyRemainDetailDAO")
public class StockDailyRemainDetailDAO extends BaseFWDAOImpl<StockDailyRemainDetailBO, Long> {

    public StockDailyRemainDetailDAO() {
        this.model = new StockDailyRemainDetailBO();
    }

    public StockDailyRemainDetailDAO(Session session) {
        this.session = session;
    }
}
