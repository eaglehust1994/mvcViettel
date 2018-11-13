package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatFcBO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;

//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

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
@Repository("tblPhatFcDAO")
public class TblPhatFcDAO extends BaseFWDAOImpl<TblPhatFcBO, Long> {

    public TblPhatFcDAO() {
        this.model = new TblPhatFcBO();
    }

    public TblPhatFcDAO(Session session) {
        this.session = session;
    }	
    
    public List<TblPhatFcDTO> doSearch(TblPhatFcDTO obj){
    	StringBuilder sql = new StringBuilder(" SELECT zz.TBL_PHAT_FC_ID tblPhatFcId,"
    			+ "zz.THANG thang, "
    			+ "zz.MA_NV maNv, "
    			+ "zz.HO_VA_TEN hoVaTen, "
    			+ "zz.QUAN_HUYEN quanHuyen , "
    			+ "zz.TINH tinh, "
    			+ "zz.MA_TINH maTinh, "
    			+ "zz.TSDDQL tsddql, "
    			+ "zz.TSFCCPLT tsfccplt, "
    			+ "zz.TBGPTT tbgptt, "
    			+ "zz.SLFCDSDTTCPTMT slfcdsdttcptmt, "
    			+ "zz.SLFCPTMVHMT slfcptmvhmt, "
    			+ "zz.SLFCTTXLSCT slfcttxlsct, "
    			+ "zz.TSLFCLT tslfclt, "
    			+ "zz.SLFCGTDDBH slfcgtddbh, "
    			+ "zz.GTTVBDPD gttvbdpd, "
    			+ "zz.SFCPT sfcpt, "
    			+ "zz.STPTTAG stpttag "

    			+ "FROM TBL_PHAT_FC zz "
				+ "WHERE zz.HOAT_DONG = 1 ");	
    	if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exNam)");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" AND (upper(zz.MA_NV) LIKE upper(:maNv) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			sql.append(" AND (upper(zz.HO_VA_TEN) LIKE upper(:hoVaTen) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			sql.append(" AND (upper(zz.MA_TINH) LIKE upper(:maTinh) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getQuanHuyen())) {
			sql.append(" AND (upper(zz.QUAN_HUYEN) LIKE upper(:quanHuyen) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(zz.MA_NV) LIKE upper(:keySearch)) "
					+ " OR (upper(zz.HO_VA_TEN) LIKE upper(:keySearch)) "
					+ " OR (upper(zz.TINH) LIKE upper(:keySearch)) "
					+ " OR (upper(zz.QUAN_HUYEN) LIKE upper(:keySearch))) ");
		}
		
		
		
		sql.append(" ORDER BY zz.TBL_PHAT_FC_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblPhatFcId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("hoVaTen", new StringType());
		query.addScalar("quanHuyen", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("tsddql", new FloatType());
		query.addScalar("tsfccplt", new FloatType());
		query.addScalar("tbgptt", new FloatType());
		query.addScalar("slfcdsdttcptmt", new FloatType());
		query.addScalar("slfcptmvhmt", new FloatType());
		query.addScalar("slfcttxlsct", new FloatType());
		query.addScalar("tslfclt", new FloatType());
		query.addScalar("slfcgtddbh", new FloatType());
		query.addScalar("gttvbdpd", new FloatType());
		query.addScalar("sfcpt", new FloatType());
		query.addScalar("stpttag", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblPhatFcDTO.class));

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			//sql.append(" and EXTRACT(MONTH FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exThang)");
			query.setParameter("exThang",ValidateUtils.validateKeySearch(obj.getExThang()));
			queryCount.setParameter("exThang",ValidateUtils.validateKeySearch(obj.getExThang()));
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			//sql.append(" and EXTRACT(YEAR FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exNam)");
			query.setParameter("exNam",ValidateUtils.validateKeySearch(obj.getExNam()));
			queryCount.setParameter("exNam",ValidateUtils.validateKeySearch(obj.getExNam()));
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			//sql.append(" AND (upper(zz.MA_NV) LIKE upper(:maNv) escape '&')");
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			//sql.append(" AND (upper(zz.HO_VA_TEN) LIKE upper(:hoVaTen) escape '&')");
			query.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
			queryCount.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			//sql.append(" AND (upper(zz.MA_TINH) LIKE upper(:maTinh) escape '&')");
			query.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
			queryCount.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getQuanHuyen())) {
			//sql.append(" AND (upper(zz.QUAN_HUYEN) LIKE upper(:quanHuyen) escape '&')");
			query.setParameter("quanHuyen", "%" + ValidateUtils.validateKeySearch(obj.getQuanHuyen()) + "%");
			queryCount.setParameter("quanHuyen", "%" + ValidateUtils.validateKeySearch(obj.getQuanHuyen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		
		
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
    }
    
    public long deletePhatFcByMaNvAndThang(String maNv, String thang){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_PHAT_FC nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv) AND nv.THANG= :thang ");
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
    public long checkExistMaNvByMaNv(String maNv){
    	StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ( SELECT nv.MA_NV maNv FROM TBL_NHAN_VIEN nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv)) ");
	
    	
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	
			query.setParameter("maNv",maNv);
		

		return ((BigDecimal) query.uniqueResult()).longValue();
    }
    
}
