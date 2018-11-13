package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblLoiLapLaiBO;
import com.viettel.qll.dto.TblLoiLapLaiDTO;
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
@Repository("tblLoiLapLaiDAO")
public class TblLoiLapLaiDAO extends BaseFWDAOImpl<TblLoiLapLaiBO, Long> {

    public TblLoiLapLaiDAO() {
        this.model = new TblLoiLapLaiBO();
    }

    public TblLoiLapLaiDAO(Session session) {
        this.session = session;
    }	
    
    public List<TblLoiLapLaiDTO> doSearch(TblLoiLapLaiDTO obj) {
    	StringBuilder sql = new StringBuilder("SELECT TBL_LOI_LAP_LAI_ID tblLoiLapLaiId, "
				+ "THANG thang, "
				+ "TINH tinh, "
				+ "QUAN_HUYEN quanHuyen, "
				+ "LOAI_DV loaiDv, "
				+ "ACCOUNT_KH accountKh, "
				+ "MA_NV maNv, "
				+ "SO_LAN_LAP soLanLap, "
				+ "PHAT phat "
				+ "FROM TBL_LOI_LAP_LAI where HOAT_DONG =1 ");
    	

    	if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(TINH) LIKE upper(:keySearch)) "
					+ " OR (upper(QUAN_HUYEN) LIKE upper(:keySearch)) "
					+ " OR (upper(LOAI_DV) LIKE upper(:keySearch)) "
					+ " OR (upper(ACCOUNT_KH) LIKE upper(:keySearch)) "
					+ " OR (upper(MA_NV) LIKE upper(:keySearch))) ");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			sql.append(" And upper(TINH) like upper(:tinh) ");

		}
		if (StringUtils.isNotEmpty(obj.getQuanHuyen())) {
			sql.append(" And upper(QUAN_HUYEN) like upper(:quanHuyen) ");

		}
		if (StringUtils.isNotEmpty(obj.getLoaiDv())) {
			sql.append(" And upper(LOAI_DV) like upper(:loaiDv) ");

		}
		if (StringUtils.isNotEmpty(obj.getAccountKh())) {
			sql.append(" And upper(ACCOUNT_KH) like upper(:accountKh) ");

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
		
		query.addScalar("tblLoiLapLaiId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("quanHuyen", new StringType());
		query.addScalar("loaiDv", new StringType());
		query.addScalar("accountKh", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("soLanLap", new FloatType());
		query.addScalar("phat", new FloatType());
	
		
		query.setResultTransformer(Transformers.aliasToBean(TblLoiLapLaiDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			queryCount.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getQuanHuyen())) {
			query.setParameter("quanHuyen", "%" + ValidateUtils.validateKeySearch(obj.getQuanHuyen()) + "%");
			queryCount.setParameter("quanHuyen", "%" + ValidateUtils.validateKeySearch(obj.getQuanHuyen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getLoaiDv())) {
			query.setParameter("loaiDv", "%" + ValidateUtils.validateKeySearch(obj.getLoaiDv()) + "%");
			queryCount.setParameter("loaiDv", "%" + ValidateUtils.validateKeySearch(obj.getLoaiDv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getAccountKh())) {
			query.setParameter("accountKh", "%" + ValidateUtils.validateKeySearch(obj.getAccountKh()) + "%");
			queryCount.setParameter("accountKh", "%" + ValidateUtils.validateKeySearch(obj.getAccountKh()) + "%");
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
    
    public long deletePhatLoiLapLaiByMaNvAndThangAndAccKh(String maNv, String thang, String accKh){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_LOI_LAP_LAI nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv)"
    				+ " AND nv.THANG= :thang "
    				+ " AND nv.ACCOUNT_KH= :accKh ");
    		SQLQuery query= getSession().createSQLQuery(sql.toString());		
    		query.setParameter("maNv",maNv.trim());
    		query.setParameter("thang", thang.trim());
    		query.setParameter("accKh", accKh.trim());
    		query.executeUpdate();
    		return 1l;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
    	
    }
}
