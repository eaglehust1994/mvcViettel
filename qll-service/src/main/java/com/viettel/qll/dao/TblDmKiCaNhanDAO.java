package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDmKiCaNhanBO;
import com.viettel.qll.dto.TblDmKiCaNhanDTO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblNhanVienDTO;

//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import net.sf.ehcache.hibernate.HibernateUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@Repository("tblDmKiCaNhanDAO")
public class TblDmKiCaNhanDAO extends BaseFWDAOImpl<TblDmKiCaNhanBO, Long> {

	public TblDmKiCaNhanDAO() {
		this.model = new TblDmKiCaNhanBO();
	}

	public TblDmKiCaNhanDAO(Session session) {
		this.session = session;
	}

	public List<TblDmKiCaNhanDTO> doSearchKiCaNhan(TblDmKiCaNhanDTO obj) {
		StringBuilder sql = new StringBuilder(" SELECT ki.TBL_DM_KI_CA_NHAN_ID tblDmKiCaNhanId," + "ki.DON_VI donVi, "
				+ "ki.HO_VA_TEN hoVaTen, " + "ki.KI_CA_NHAN kiCaNhan, " + "ki.MA_NV maNv," + "ki.THANG thang,"
				+ "ki.HE_SO heSo " + "FROM TBL_DM_KI_CA_NHAN ki " + "WHERE ki.HOAT_DONG = 1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(ki.MA_NV) LIKE upper(:keySearch) escape '&')"
					+ "AND (upper(ki.HO_VA_TEN) LIKE upper(:keySearch) escape '&')"
					+ "AND (upper(ki.DON_VI) LIKE upper(:keySearch) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			sql.append(" And  ki.HO_VA_TEN =:hoVaTen ");

		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And  ki.MA_NV =:maNv ");

		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			sql.append(" And  ki.DON_VI =:donVi ");

		}

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(ki.THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(ki.THANG, 'MM/yyyy'))in(:exNam)");
		}
		sql.append(" ORDER BY ki.TBL_DM_KI_CA_NHAN_ID");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblDmKiCaNhanId", new LongType());
		query.addScalar("donVi", new StringType());
		query.addScalar("hoVaTen", new StringType());
		query.addScalar("kiCaNhan", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("heSo", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblDmKiCaNhanDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			query.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
			queryCount.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			query.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			queryCount.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			query.setParameter("exThang", obj.getExThang());
			queryCount.setParameter("exThang", obj.getExThang());
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			query.setParameter("exNam", obj.getExNam());
			queryCount.setParameter("exNam", obj.getExNam());
		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public Long deleteObj(TblDmKiCaNhanDTO obj) {
		try {
			String sql2 = "delete from TBL_DM_KI_CA_NHAN  where  MA_NV=:maNv and DON_VI=:donVi and THANG=:thang";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maNv", obj.getMaNv());
			query2.setParameter("thang", obj.getThang());
			query2.setParameter("donVi", obj.getDonVi());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	public long countKiCaNhanByMaNv(String maNv, String thang) {
		// getting session object from session factory
		String hql = "select MA_NV maNv from TBL_DM_KI_CA_NHAN kicn where upper(kicn.MA_NV) = upper(:maNv) and kicn.THANG = :thang ";

		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("maNv", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDmKiCaNhanDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);

		List result = query.list();
		return result.size();
	}

	public long checkExistMaNvByMaNv(String maNv) {
		String hql = "select MA_NV maNv from TBL_NHAN_VIEN nv where upper(nv.MA_NV) = upper(:maNv) ";

		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("maNv", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblNhanVienDTO.class));
		query.setParameter("maNv", maNv);

		List result = query.list();
		return result.size();
	}
	
}
