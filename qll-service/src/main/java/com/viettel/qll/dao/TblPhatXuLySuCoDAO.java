package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatXuLySuCoBO;
import com.viettel.qll.dto.TblPhatKpiDTO;
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
@Repository("tblPhatXuLySuCoDAO")
public class TblPhatXuLySuCoDAO extends BaseFWDAOImpl<TblPhatXuLySuCoBO, Long> {

	public TblPhatXuLySuCoDAO() {
		this.model = new TblPhatXuLySuCoBO();
	}

	public TblPhatXuLySuCoDAO(Session session) {
		this.session = session;
	}

	public List<TblPhatXuLySuCoDTO> doSearchPhatKPIXLSC(TblPhatXuLySuCoDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_PHAT_XU_LY_SU_CO_ID tblPhatXuLySuCoId," + "MA_NV maNv,"
				+ "PHAT_TGXL_SL_3H phatTgxlSl3h," + "PHAT_TGXL_SL_24H phatTgxlSl24h," + "PHAT_TGXL_TL_3H phatTgxlTl3h,"
				+ "PHAT_TGXL_TL_24H phatTgxlTl24h," + "PHAT_TGXL_SLP phatTgxlSlp," + "PHAT_TGXL_TP phatTgxlTp,"
				+ "THANG thang," + "TINH tinh," + "HUYEN huyen," + "ACCOUNT account," + "TONG tong "
				+ " from TBL_PHAT_XU_LY_SU_CO  where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') or (upper(TINH) LIKE upper(:keySearch) escape '&') "
							+ " or (upper(HUYEN) LIKE upper(:keySearch) escape '&') or (upper(ACCOUNT) LIKE upper(:keySearch) escape '&')  ");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			sql.append(" And  upper(TINH) like upper(:tinh) ");
		}
		if (StringUtils.isNotEmpty(obj.getHuyen())) {
			sql.append(" and upper(HUYEN) like upper(:huyen)");
		}
		if (StringUtils.isNotEmpty(obj.getAccount())) {
			sql.append(" and upper(ACCOUNT) like upper(:account)");
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

		query.addScalar("tblPhatXuLySuCoId", new LongType());
		query.addScalar("maNv", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("phatTgxlTl3h", new FloatType());
		query.addScalar("phatTgxlSl24h", new FloatType());
		query.addScalar("phatTgxlSl3h", new FloatType());
		query.addScalar("phatTgxlTl24h", new FloatType());
		query.addScalar("phatTgxlSlp", new FloatType());
		query.addScalar("phatTgxlTp", new FloatType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("huyen", new StringType());
		query.addScalar("account", new StringType());
		query.addScalar("tong", new FloatType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblPhatXuLySuCoDTO.class));

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
		if (StringUtils.isNotEmpty(obj.getHuyen())) {
			query.setParameter("huyen", "%" + ValidateUtils.validateKeySearch(obj.getHuyen()) + "%");
			queryCount.setParameter("huyen", "%" + ValidateUtils.validateKeySearch(obj.getHuyen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getAccount())) {
			query.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getAccount()) + "%");
			queryCount.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getAccount()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			query.setParameter("exThang", obj.getExThang());
			queryCount.setParameter("exThang", obj.getExThang());
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			query.setParameter("exNam", obj.getExNam());
			queryCount.setParameter("exNam", obj.getExNam());
		}
		if(obj.getPageSize()!=null)
		{
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}
    public long deletePhatXLSCByMaNvAndThang(String maNv, String thang){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_PHAT_XU_LY_SU_CO nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv) AND nv.THANG= :thang ");
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
