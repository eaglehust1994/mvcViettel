package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblDlHaTangTramBO;
import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblDlHaTangTramDAO")
public class TblDlHaTangTramDAO extends BaseFWDAOImpl<TblDlHaTangTramBO, Long> {

	public TblDlHaTangTramDAO() {
		this.model = new TblDlHaTangTramBO();
	}

	public TblDlHaTangTramDAO(Session session) {
		this.session = session;
	}

	public List<TblDlHaTangTramDTO> getAllDLHaTang() {
		StringBuilder sql = new StringBuilder("SELECT  TBL_DL_HA_TANG_TRAM_ID tblDlHaTangTramId," + "KHU_VUC khuVuc,"
				+ "MA_TINH maTinh," + "HUYEN huyen," + "MA_TRAM maTram," + "LOAI_DIA_HINH loaiDiaHinh,"
				+ "PHAN_LOAI_TRAM phanLoaiTram," + "VUNG vung," + "MA_NV maNv,"
				+ "NGAY_CONG_TINH_LUONG ngayCongTinhLuong," + "NGAY_CONG_CHE_DO ngayCongCheDo," + "THANG thang"
				+ " from TBL_DL_HA_TANG_TRAM  where rownum<10");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("tblDlHaTangTramId", new LongType());
		query.addScalar("khuVuc", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("huyen", new StringType());
		query.addScalar("maTram", new StringType());
		query.addScalar("phanLoaiTram", new StringType());
		query.addScalar("vung", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.addScalar("ngayCongCheDo", new FloatType());
		query.addScalar("thang", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDlHaTangTramDTO.class));

		return query.list();
	}

	public List<TblDlHaTangTramDTO> doSearchDLHaTang(TblDlHaTangTramDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_DL_HA_TANG_TRAM_ID tblDlHaTangTramId," + "KHU_VUC khuVuc,"
				+ "MA_TINH maTinh," + "HUYEN huyen," + "MA_TRAM maTram," + "LOAI_DIA_HINH loaiDiaHinh,"
				+ "PHAN_LOAI_TRAM phanLoaiTram," + "VUNG vung," + "MA_NV maNv," + "SO_NGAY_QUAN_LY soNgayQuanLy,"
				+ "SO_NGAY_TRONG_THANG soNgayTrongThang," + "NGAY_CONG_TINH_LUONG ngayCongTinhLuong,"
				+ "NGAY_CONG_CHE_DO ngayCongCheDo," + "THANG thang" + " from TBL_DL_HA_TANG_TRAM  where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') or (upper(KHU_VUC) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(MA_TINH) LIKE upper(:keySearch) escape '&')or (upper(HUYEN) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(MA_TRAM) LIKE upper(:keySearch) escape '&') or (upper(LOAI_DIA_HINH) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(PHAN_LOAI_TRAM) LIKE upper(:keySearch) escape '&')or (upper(VUNG) LIKE upper(:keySearch) escape '&')"
							+ "");
		}

		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			sql.append(" And MA_TRAM like :maTram ");
		}
		if (StringUtils.isNotEmpty(obj.getHuyen())) {
			sql.append(" And HUYEN like:huyen ");
		}
		if (StringUtils.isNotEmpty(obj.getKhuVuc())) {
			sql.append(" And KHU_VUC like upper(:khuVuc) ");
		}
		if (StringUtils.isNotEmpty(obj.getLoaiDiaHinh())) {
			sql.append(" And LOAI_DIA_HINH like:loaiDiaHinh ");
		}
		if (StringUtils.isNotEmpty(obj.getVung())) {
			sql.append(" And VUNG like:vung ");
		}

		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And MA_NV like:maNv ");

		}
		if (StringUtils.isNotEmpty(obj.getPhanLoaiTram())) {
			sql.append(" And PHAN_LOAI_TRAM like :phanloaitram ");

		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			sql.append(" And  MA_TINH in (select TBL_DANH_MUC.MA_DANH_MUC from TBL_DANH_MUC where TBL_DANH_MUC.TEN_DANH_MUC=:maTinh and TBL_DANH_MUC.MA_DANH_MUC_CHA is null) ");

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

		query.addScalar("tblDlHaTangTramId", new LongType());
		query.addScalar("khuVuc", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("huyen", new StringType());
		query.addScalar("maTram", new StringType());
		query.addScalar("phanLoaiTram", new StringType());
		query.addScalar("vung", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.addScalar("ngayCongCheDo", new FloatType());
		query.addScalar("soNgayQuanLy", new FloatType());
		query.addScalar("soNgayTrongThang", new FloatType());
		query.addScalar("thang", new StringType());
		query.addScalar("loaiDiaHinh", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDlHaTangTramDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			query.setParameter("maTinh", "" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "");
			queryCount.setParameter("maTinh", "" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "");
		}

		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			query.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
			queryCount.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getPhanLoaiTram())) {
			query.setParameter("phanloaitram", "%" + ValidateUtils.validateKeySearch(obj.getPhanLoaiTram()) + "%");
			queryCount.setParameter("phanloaitram", "%" + ValidateUtils.validateKeySearch(obj.getPhanLoaiTram()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getHuyen())) {
			query.setParameter("huyen", "" + ValidateUtils.validateKeySearch(obj.getHuyen()) + "");
			queryCount.setParameter("huyen", "" + ValidateUtils.validateKeySearch(obj.getHuyen()) + "");
		}
		if (StringUtils.isNotEmpty(obj.getLoaiDiaHinh())) {
			query.setParameter("loaiDiaHinh", "%" + ValidateUtils.validateKeySearch(obj.getLoaiDiaHinh()) + "%");
			queryCount.setParameter("loaiDiaHinh", "%" + ValidateUtils.validateKeySearch(obj.getLoaiDiaHinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getVung())) {
			query.setParameter("vung", "%" + ValidateUtils.validateKeySearch(obj.getVung()) + "%");
			queryCount.setParameter("vung", "%" + ValidateUtils.validateKeySearch(obj.getVung()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getKhuVuc())) {
			query.setParameter("khuVuc", "%" + ValidateUtils.validateKeySearch(obj.getKhuVuc()) + "%");
			queryCount.setParameter("khuVuc", "%" + ValidateUtils.validateKeySearch(obj.getKhuVuc()) + "%");
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

}
