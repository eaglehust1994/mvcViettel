package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatPakhBO;
import com.viettel.qll.dto.TblLoiDongAoDTO;
import com.viettel.qll.dto.TblPhatPakhDTO;
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
@Repository("tblPhatPakhDAO")
public class TblPhatPakhDAO extends BaseFWDAOImpl<TblPhatPakhBO, Long> {

    public TblPhatPakhDAO() {
        this.model = new TblPhatPakhBO();
    }

    public TblPhatPakhDAO(Session session) {
        this.session = session;
    }	
    
    public List<TblPhatPakhDTO> doSearch(TblPhatPakhDTO obj) {
    	StringBuilder sql = new StringBuilder("SELECT TBL_PHAT_PAKH_ID tblPhatPakhId, "
				+ "THANG thang, "
				+ "MA_NV maNv, "
				+ "PHAT phat "
				+ "FROM TBL_PHAT_PAKH where HOAT_DONG =1 ");
    	

		
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam)");
		}
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("tblPhatPakhId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("phat", new FloatType());

		
		query.setResultTransformer(Transformers.aliasToBean(TblPhatPakhDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			query.setParameter("exThang", obj.getExThang());
			queryCount.setParameter("exThang", obj.getExThang());
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			query.setParameter("exNam", obj.getExNam());
			queryCount.setParameter("exNam", obj.getExNam());
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();	
    }
    
    public long deletePhatPakhByMaNvAndThang(String maNv, String thang){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_PHAT_PAKH nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv)"
    				+ " AND nv.THANG= :thang ");
    		SQLQuery query= getSession().createSQLQuery(sql.toString());		
    		query.setParameter("maNv",maNv.trim());
    		query.setParameter("thang", thang.trim());
    		query.executeUpdate();
    		return 1l;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
    	
    }
}
