package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblNhanVienBO;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.qll.dto.TblPhatCdtDTO;

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
@Repository("tblNhanVienDAO")
public class TblNhanVienDAO extends BaseFWDAOImpl<TblNhanVienBO, Long> {

	public TblNhanVienDAO() {
		this.model = new TblNhanVienBO();
	}

	public TblNhanVienDAO(Session session) {
		this.session = session;
	}

	public long checkExistMaNvByMaNv(String maNv) {
		StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM ( SELECT nv.MA_NV maNv FROM TBL_NHAN_VIEN nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv)) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.setParameter("maNv", maNv);

		return ((BigDecimal) query.uniqueResult()).longValue();
	}
	public List<TblNhanVienDTO> getAll() {
		StringBuilder sql = new StringBuilder(" SELECT zz.TBL_NHAN_VIEN_ID tblNhanVienId," + "zz.MA_NV maNv "

				+ "FROM TBL_NHAN_VIEN zz " + "WHERE 1 = 1 ");

		sql.append(" ORDER BY zz.TBL_NHAN_VIEN_ID");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblNhanVienId", new LongType());
		query.addScalar("maNv", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TblNhanVienDTO.class));

		return query.list();
	}

	public List<TblNhanVienDTO> checkNV(String maNV) {
		StringBuilder sql = new StringBuilder(
				" SELECT MA_NV maNv,DAY_MAY_NHA_TRAM dayMayNhaTram,HO_VA_TEN hoVaTen " + "FROM TBL_NHAN_VIEN  " + "WHERE MA_NV=:maNv ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("maNv", new StringType());
		query.addScalar("hoVaTen", new StringType());
		query.addScalar("dayMayNhaTram", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(TblNhanVienDTO.class));
		query.setParameter("maNv", maNV);

		return query.list();
	}

	public List<TblNhanVienDTO> doSearchNhanVien(TblNhanVienDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_NHAN_VIEN_ID tblNhanVienId," + "MA_NV maNv,"
				+ "CHUC_DANH chucDanh," + "DAY_MAY_NHA_TRAM dayMayNhaTram," + "DON_VI donVi," + "HO_VA_TEN hoVaTen"
				+ " from TBL_NHAN_VIEN  where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
				sql.append(" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') "
						+ "or (upper(HO_VA_TEN) LIKE upper(:keySearch) escape '&') "
						+ "or (upper(DAY_MAY_NHA_TRAM) LIKE upper(:keySearch) escape '&')"
						+ "or (upper(DON_VI) LIKE upper(:keySearch) escape '&')"
						+ "or (upper(HOAT_DONG) LIKE upper(:keySearch) escape '&')");


		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

		}

		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			sql.append(" And upper(HO_VA_TEN) like upper(:hoVaTen) ");

		}
		
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			sql.append(" And upper(DON_VI) like upper(:donVi) ");

		}
		if(obj.getDayMayNhaTram()!=null){
			sql.append(" And upper(DAY_MAY_NHA_TRAM) like upper(:dayMayNhaTram) ");
		}

		
		if (obj.getHoatDong()!=null) {
				sql.append(" And upper(HOAT_DONG) like upper(:hoatDong) ");
		}
		// sql.append(" And rownum<20");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblNhanVienId", new LongType());
		query.addScalar("maNv", new StringType());
		query.addScalar("chucDanh", new StringType());
		query.addScalar("dayMayNhaTram", new LongType());
		query.addScalar("donVi", new StringType());
		query.addScalar("hoVaTen", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TblNhanVienDTO.class));

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
		
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			query.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			queryCount.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
		}
		if (obj.getDayMayNhaTram()!=null) {
			query.setParameter("dayMayNhaTram", obj.getDayMayNhaTram());
			queryCount.setParameter("dayMayNhaTram",obj.getDayMayNhaTram());
		}
		if (obj.getHoatDong()!=null) {
			query.setParameter("hoatDong", obj.getHoatDong());
			queryCount.setParameter("hoatDong",obj.getHoatDong());
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	public long deleteNhanVien(TblNhanVienDTO dto) {
		try {
			StringBuilder sql = new StringBuilder(
					" DELETE FROM TBL_NHAN_VIEN nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv) AND nv.DON_VI= :donVi AND DAY_MAY_NHA_TRAM =:dayMayNhaTram");
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameter("maNv", dto.getMaNv());
			query.setParameter("donVi", dto.getDonVi());
			query.setParameter("dayMayNhaTram", dto.getDayMayNhaTram());
			query.executeUpdate();
			return 1l;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<TblNhanVienDTO> getForAutoCompleteNV(TblNhanVienDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_NHAN_VIEN_ID tblNhanVienId," + "MA_NV maNv,"
				+ "CHUC_DANH chucDanh," + "DAY_MAY_NHA_TRAM dayMayNhaTram," + "DON_VI donVi," + "HO_VA_TEN hoVaTen"
				+ " from TBL_NHAN_VIEN  where 1=1 ");

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append(
					" AND (upper(MA_NV) LIKE upper(:name) escape '&' OR upper(HO_VA_TEN) LIKE upper(:name) escape '&')");
		}

		stringBuilder.append(" ORDER BY TBL_NHAN_VIEN_ID");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("tblNhanVienId", new LongType());
		query.addScalar("maNv", new StringType());
		query.addScalar("chucDanh", new StringType());
		query.addScalar("dayMayNhaTram", new LongType());
		query.addScalar("donVi", new StringType());
		query.addScalar("hoVaTen", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblNhanVienDTO.class));
		

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		// if (StringUtils.isNotEmpty(obj.getMaDanhMuc())) {
		// query.setParameter("name", "%" +
		// ValidateUtils.validateKeySearch(obj.getMaDanhMuc()) + "%");
		// }

		return query.list();
	}

}