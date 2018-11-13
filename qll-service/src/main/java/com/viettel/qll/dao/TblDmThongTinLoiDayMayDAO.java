package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDmThongTinLoiDayMayBO;
import com.viettel.qll.dto.TblDmThongTinLoiDayMayDTO;
import com.viettel.qll.dto.TblPhatCdtDTO;

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
@Repository("tblDmThongTinLoiDayMayDAO")
public class TblDmThongTinLoiDayMayDAO extends BaseFWDAOImpl<TblDmThongTinLoiDayMayBO, Long> {

    public TblDmThongTinLoiDayMayDAO() {
        this.model = new TblDmThongTinLoiDayMayBO();
    }

    public TblDmThongTinLoiDayMayDAO(Session session) {
        this.session = session;
    }	
    public List<TblDmThongTinLoiDayMayDTO> doSearch(TblDmThongTinLoiDayMayDTO obj) {
    	StringBuilder sql = new StringBuilder("SELECT THONG_TIN_LOI_DAY_MAY_ID thongTinLoiDayMayId, "
				+ "THANG thang, "
				+ "MA_LOI maLoi, "
				+ "THUE_BAO_KH thueBaoKh, "
				+ "MA_NV maNv, "
				+ "SO_NGAY_TON soNgayTon, "
				+ "CUOC_PHAT_SINH cuocPhatSinh "
				+ "FROM TBL_DM_THONG_TIN_LOI_DAY_MAY where HOAT_DONG =1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(MA_LOI) LIKE upper(:keySearch)) "
					+ " OR (upper(THUE_BAO_KH) LIKE upper(:keySearch)) "
					+ " OR (upper(MA_NV) LIKE upper(:keySearch))) ");
		}
		if (StringUtils.isNotEmpty(obj.getMaLoi())) {
			sql.append(" And upper(MA_LOI) like upper(:maLoi) ");
		}
		if (StringUtils.isNotEmpty(obj.getThueBaoKh())) {
			sql.append(" And upper(THUE_BAO_KH) like upper(:thueBaoKh) ");

		}
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
		
		query.addScalar("thongTinLoiDayMayId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("maLoi", new StringType());
		query.addScalar("thueBaoKh", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("soNgayTon", new FloatType());
		query.addScalar("cuocPhatSinh", new FloatType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblDmThongTinLoiDayMayDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaLoi())) {
			query.setParameter("maLoi", "%" + ValidateUtils.validateKeySearch(obj.getMaLoi()) + "%");
			queryCount.setParameter("maLoi", "%" + ValidateUtils.validateKeySearch(obj.getMaLoi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getThueBaoKh())) {
			query.setParameter("thueBaoKh", "%" + ValidateUtils.validateKeySearch(obj.getThueBaoKh()) + "%");
			queryCount.setParameter("thueBaoKh", "%" + ValidateUtils.validateKeySearch(obj.getThueBaoKh()) + "%");
		}
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
    
    
    public long checkExistMaLoiByMaLoi(String maLoi){
    	StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ( SELECT nv.MA_NV maNv FROM TBL_DM_MA_LOI nv WHERE UPPER(nv.MA_LOI) = UPPER(:maLoi)) ");
    	
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	
			query.setParameter("maLoi",maLoi);

		return ((BigDecimal) query.uniqueResult()).longValue();
    }
    
    public long deleteTblDmThongTinLoiDayMayByMaNvAndThangAndThueBaoKh(String maNv, String thang, String thueBaoKh){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_DM_THONG_TIN_LOI_DAY_MAY nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv)"
    				+ " AND nv.THANG= :thang "
    				+ " AND nv.THUE_BAO_KH = :thueBaoKh ");
    		SQLQuery query= getSession().createSQLQuery(sql.toString());		
    		query.setParameter("maNv",maNv.trim());
    		query.setParameter("thang", thang.trim());
    		query.setParameter("thueBaoKh", thueBaoKh.trim());
    		query.executeUpdate();
    		return 1l;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
    	
    }
    
}
