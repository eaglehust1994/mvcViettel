package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDmMaLoiBO;
import com.viettel.qll.dto.TblDmMaLoiDTO;
//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblDmMaLoiDAO")
public class TblDmMaLoiDAO extends BaseFWDAOImpl<TblDmMaLoiBO, Long> {

    public TblDmMaLoiDAO() {
        this.model = new TblDmMaLoiBO();
    }

    public TblDmMaLoiDAO(Session session) {
        this.session = session;
    }	
    
    
}
