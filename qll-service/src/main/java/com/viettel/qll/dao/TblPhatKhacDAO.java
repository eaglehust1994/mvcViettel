package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatKhacBO;
import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.qll.dto.TblPhatKhacDTO;
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
@Repository("tblPhatKhacDAO")
public class TblPhatKhacDAO extends BaseFWDAOImpl<TblPhatKhacBO, Long> {

	public TblPhatKhacDAO() {
		this.model = new TblPhatKhacBO();
	}

	public TblPhatKhacDAO(Session session) {
		this.session = session;
	}

	public List<TblPhatKhacDTO> doSearchPhatCDT(TblPhatKhacDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_PHAT_KHAC_ID tblPhatKhacId," + "MA_NV maNv," + "KV kv,"
				+ "NHAN_VIEN nhanVien," + "GIAN_DOAN_THONG_TIN gianDoanThongTin," + "MA_TRAM maTram,"
				+ "PHAT_TRUC_TIEP phatTrucTiep," + "THANG thang," + "VI_PHAM_DONG_AO viPhamDongAo,"
				+ "LOI_RA_VA_NHA_TRAM loiRaVaNhaTram," + "GIAN_LAN_XANG_DAU gianLanXangDau," + "MA_TINH maTinh,"
				+ "UCTT_CHAM_DAN ucttChamDan " + " from TBL_PHAT_KHAC  where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') or (upper(KV) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(MA_TINH) LIKE upper(:keySearch) escape '&')or (upper(NHAN_VIEN) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(MA_TRAM) LIKE upper(:keySearch) escape '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

		}

		if (StringUtils.isNotEmpty(obj.getKv())) {
			sql.append(" And upper(KV) like upper(:kv) ");

		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			sql.append(" And upper(MA_TINH) like upper(:maTinh) ");

		}
		if (StringUtils.isNotEmpty(obj.getNhanVien())) {
			sql.append(" And upper(NHAN_VIEN) like upper(:nhanVien) ");

		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			sql.append(" And upper(MA_TRAM) like upper(:maTram) ");

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

		query.addScalar("tblPhatKhacId", new LongType());
		query.addScalar("maNv", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("maTram", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("nhanVien", new StringType());
		query.addScalar("kv", new StringType());
		query.addScalar("phatTrucTiep", new FloatType());
		query.addScalar("gianDoanThongTin", new FloatType());
		query.addScalar("viPhamDongAo", new FloatType());
		query.addScalar("loiRaVaNhaTram", new FloatType());
		query.addScalar("gianLanXangDau", new FloatType());
		query.addScalar("ucttChamDan", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblPhatKhacDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getKv())) {
			query.setParameter("kv", "%" + ValidateUtils.validateKeySearch(obj.getKv()) + "%");
			queryCount.setParameter("kv", "%" + ValidateUtils.validateKeySearch(obj.getKv()) + "%");

		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			query.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
			queryCount.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");

		}
		if (StringUtils.isNotEmpty(obj.getNhanVien())) {
			query.setParameter("nhanVien", "%" + ValidateUtils.validateKeySearch(obj.getNhanVien()) + "%");
			queryCount.setParameter("nhanVien", "%" + ValidateUtils.validateKeySearch(obj.getNhanVien()) + "%");

		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			query.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
			queryCount.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");

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

	@SuppressWarnings("unchecked")
	public List<TblPhatKhacDTO> lstphatKhacByKey(TblPhatKhacDTO obj) {

		StringBuilder sql = new StringBuilder(
				"SELECT  MA_NV from TBL_PHAT_KHAC  where  MA_TRAM=:maTram and MA_NV=:maNv and THANG=:thang");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setResultTransformer(Transformers.aliasToBean(TblPhatKhacDTO.class));
		query.setParameter("maTram", obj.getMaTram());
		query.setParameter("maNv", obj.getMaNv());
		query.setParameter("thang", obj.getThang());
		return query.list();
	}

	public Long deleteObj(String maTram, String maNV, String thang) {
		try {
			String sql2 = "delete from TBL_PHAT_KHAC  where  MA_TRAM=:maTram and MA_NV=:maNv and THANG=:thang";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maTram", maTram);
			query2.setParameter("maNv", maNV);
			query2.setParameter("thang", thang);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

}
