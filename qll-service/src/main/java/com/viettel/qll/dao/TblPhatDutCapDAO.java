package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatDutCapBO;
import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;
import com.viettel.qll.dto.TblPhatDutCapDTO;
//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblPhatDutCapDAO")
public class TblPhatDutCapDAO extends BaseFWDAOImpl<TblPhatDutCapBO, Long> {

	public TblPhatDutCapDAO() {
		this.model = new TblPhatDutCapBO();
	}

	public TblPhatDutCapDAO(Session session) {
		this.session = session;
	}

	

}
