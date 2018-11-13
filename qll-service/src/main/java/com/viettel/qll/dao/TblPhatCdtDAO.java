package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatCdtBO;
import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;

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
@Repository("tblPhatCdtDAO")
public class TblPhatCdtDAO extends BaseFWDAOImpl<TblPhatCdtBO, Long> {

    public TblPhatCdtDAO() {
        this.model = new TblPhatCdtBO();
    }

    public TblPhatCdtDAO(Session session) {
        this.session = session;
    }	
    
    public List<TblPhatCdtDTO> doSearchPhatCDT(TblPhatCdtDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_PHAT_CDT_ID tblPhatCdtId," + "MA_NV maNv,"
				+ "HO_VA_TEN hoVaTen," + "PHAT_QCXLSC phatQcxlsc," + "PHAT_LDSVT_XLSCT phatLdsvtXlsct,"
				+ "PHAT_GTTBRM phatGttbrm," + "PHAT_TBCDCNKTD phatTbcdcnktd," + "PHAT_TBRMKTD phatTbrmktd,"
				+ "THANG thang," + "PHAT_LYTTD phatLyttd" 
				+ " from TBL_PHAT_CDT  where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') or (upper(HO_VA_TEN) LIKE upper(:keySearch) escape '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

		}
		
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			sql.append(" And upper(HO_VA_TEN) like upper(:hoVaTen) ");

		}
		
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam)");
		}
		// sql.append(" And rownum<20");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblPhatCdtId", new LongType());
		query.addScalar("maNv", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("hoVaTen", new StringType());
		query.addScalar("phatQcxlsc", new FloatType());
		query.addScalar("phatLdsvtXlsct", new FloatType());
		query.addScalar("phatGttbrm", new FloatType());
		query.addScalar("phatTbcdcnktd", new FloatType());
		query.addScalar("phatTbrmktd", new FloatType());
		query.addScalar("phatLyttd", new FloatType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblPhatCdtDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			query.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
			queryCount.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
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
    
    public long deletePhatCdtByMaNvAndThang(String maNv, String thang){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_PHAT_CDT nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv) AND nv.THANG= :thang ");
    		SQLQuery query= getSession().createSQLQuery(sql.toString());		
    		query.setParameter("maNv",maNv.trim());
    		query.setParameter("thang", thang.trim());
    		query.executeUpdate();
    		query.executeUpdate();
    		return 1l;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
    	
    }
}
