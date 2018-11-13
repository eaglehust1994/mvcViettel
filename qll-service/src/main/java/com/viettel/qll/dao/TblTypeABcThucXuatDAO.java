package com.viettel.qll.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblTypeABcThucXuatBO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author hailh10
 */
@Repository("tblTypeABcThucXuatDAO")
public class TblTypeABcThucXuatDAO extends BaseFWDAOImpl<TblTypeABcThucXuatBO, Long> {

    public TblTypeABcThucXuatDAO() {
        this.model = new TblTypeABcThucXuatBO();
    }

    public TblTypeABcThucXuatDAO(Session session) {
        this.session = session;
    }	
    
}
