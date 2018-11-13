package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblLuongDayMayBO;
import com.viettel.qll.dto.TblCauHinhTienDTO;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblDmThongTinLoiDayMayDTO;
import com.viettel.qll.dto.TblDonGiaThueBaoDTO;
import com.viettel.qll.dto.TblLoiDongAoDTO;
import com.viettel.qll.dto.TblLoiLapLaiDTO;
import com.viettel.qll.dto.TblLuongDayMayDTO;
import com.viettel.qll.dto.TblNgayCongDTO;
import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.qll.dto.TblPhatPakhDTO;
import com.viettel.qll.dto.TblPhatRoiMangDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;
import com.viettel.qll.dto.TblPhiBanHangDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;

//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
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
@Repository("tblLuongDayMayDAO")
public class TblLuongDayMayDAO extends BaseFWDAOImpl<TblLuongDayMayBO, Long> {

	public TblLuongDayMayDAO() {
		this.model = new TblLuongDayMayBO();
	}

	public TblLuongDayMayDAO(Session session) {
		this.session = session;
	}

	public List<TblLuongDayMayDTO> doSearch(TblLuongDayMayDTO obj) {
		StringBuffer sql = new StringBuffer("select TBL_LUONG_DAY_MAY_ID tblLuongDayMayId," + "DON_GIA donGia,"
				+ "HE_SO_DIEU_CHINH heSoDieuChinh," + "HO_TEN hoTen," + "HOAT_DONG hoatDong," + "HUYEN huyen,"
				+ "KI_DON_VI kiDonVi," + "LOI_1 loi1," + "LOI_2 loi2," + "LOI_3 loi3," + "LOI_4 loi4," + "LOI_5 loi5,"
				+ "LOI_6 loi6," + "LOI_7 loi7," + "LOI_8 loi8," + "LOI_9 loi9," + "LOI_10 loi10," + "LOI_11 loi11,"
				+ "LOI_12 loi12," + "LUONG luong," + "LUONG_DUY_TRI luongDuyTri," + "MA_NV maNv,"
				+ "NGAY_CONG_CHE_DO ngayCongCheDo," + "NGAY_CONG_TINH_LUONG ngayCongTinhLuong,"
				+ "PHAT_GTTBRM phatGttbrm," + "PHAT_LDSVT_XLSCT phatLdsvtXlsct," + "PHAT_LYTTD phatLyttd,"
				+ "PHAT_PAKH phatPakh," + "PHAT_QCXLSC phatQcxlsc," + "PHAT_TBCDCNKTD phatTbcdcnktd,"
				+ "PHAT_TBRMKTD phatTbrmktd," + "PHAT_XLSC phatXlsc," + "PHI_BAN_HANG phiBanHang,"
				+ "SO_DAY_TB_QUI_DOI soDayTbQuiDoi," + "TDV_0 tdv0," + "TDV_3 tdv3," + "TDV_4 tdv4," + "TDV_5 tdv5,"
				+ "THANG thang," + "TINH tinh," + "TKM_0 tkm0," + "TKM_3 tkm3," + "TKM_4 tkm4," + "TKM_5 tkm5,"
				+ "XOA xoa " + " from TBL_LUONG_DAY_MAY where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') "
					+ "or (upper(TINH) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(huyen) LIKE upper(:keySearch) escape '&')  ");
		}

		if (StringUtils.isNotEmpty(obj.getThang())) {
			sql.append(" and THANG =:thang");
		}
		if (StringUtils.isNotEmpty(obj.getHuyen())) {
			sql.append(" and THANG =:huyen");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			sql.append(" and TINH =:tinh");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" and MA_NV =:maNv");
		}
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		query.addScalar("tblLuongDayMayId", new LongType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("heSoDieuChinh", new FloatType());
		query.addScalar("hoatDong", new LongType());
		query.addScalar("kiDonVi", new FloatType());
		query.addScalar("huyen", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("hoTen", new StringType());
		query.addScalar("loi1", new FloatType());
		query.addScalar("loi2", new FloatType());

		query.addScalar("loi3", new FloatType());
		query.addScalar("loi4", new FloatType());
		query.addScalar("loi5", new FloatType());
		query.addScalar("loi6", new FloatType());

		query.addScalar("loi7", new FloatType());
		query.addScalar("loi8", new FloatType());
		query.addScalar("loi9", new FloatType());
		query.addScalar("loi10", new FloatType());

		query.addScalar("loi11", new FloatType());
		query.addScalar("loi12", new FloatType());
		query.addScalar("luong", new FloatType());
		query.addScalar("luongDuyTri", new FloatType());

		query.addScalar("ngayCongCheDo", new FloatType());
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.addScalar("phatGttbrm", new FloatType());
		query.addScalar("phatLdsvtXlsct", new FloatType());

		query.addScalar("phatLyttd", new FloatType());
		query.addScalar("phatPakh", new FloatType());
		query.addScalar("phatQcxlsc", new FloatType());
		query.addScalar("phatTbcdcnktd", new FloatType());

		query.addScalar("phatTbrmktd", new FloatType());
		query.addScalar("phatXlsc", new FloatType());
		query.addScalar("phiBanHang", new FloatType());
		query.addScalar("soDayTbQuiDoi", new FloatType());

		query.addScalar("tdv0", new FloatType());
		query.addScalar("tdv3", new FloatType());
		query.addScalar("tdv4", new FloatType());
		query.addScalar("tdv5", new FloatType());

		query.addScalar("tkm0", new FloatType());
		query.addScalar("tkm3", new FloatType());
		query.addScalar("tkm4", new FloatType());
		query.addScalar("tkm5", new FloatType());

		query.addScalar("xoa", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(TblLuongDayMayDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getThang())) {
			query.setParameter("thang", obj.getThang());
			queryCount.setParameter("thang", obj.getThang());
		}
		if (StringUtils.isNotEmpty(obj.getHuyen())) {
			query.setParameter("huyen", obj.getHuyen());
			queryCount.setParameter("huyen", obj.getHuyen());
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", obj.getTinh());
			queryCount.setParameter("tinh", obj.getTinh());
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", obj.getMaNv());
			queryCount.setParameter("maNv", obj.getMaNv());
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		// query.setParameter("kyLuong", kyLuong);
		return query.list();
	}

	public List<TblDlDauVaoDayMayDTO> getListNvDayMayByThang(String kyLuong) {
		StringBuffer sql = new StringBuffer("select TBL_DL_DAU_VAO_DAY_MAY_ID tblDlDauVaoDayMayId"
				+ ",DDTBQL_DA_TOA_NHA ddtbqlDaToaNha" + ",DDTBQL_DAY_TB_AON ddtbqlDayTbAon"
				+ ",DDTBQL_DAY_TBKT ddtbqlDayTbkt," + "DDTBQL_HUONG_LUONG_DUY_TRI ddtbqlHuongLuongDuyTri,"
				+ "DDTBQL_TONG_QUY_DOI ddtbqlTongQuyDoi," + "DOI_TUONG doiTuong," + "DON_VI donVi," + "GHEP ghep,"
				+ "GHI_CHU ghiChu," + "HO_VA_TEN hoVaTen," + "HOAT_DONG hoatDong," + "KY_LUONG kyLuong," + "MA_NV maNv,"
				+ "MA_TINH maTinh," + "NHOM_TRUONG nhomTruong," + "PHAT_GTTBRM phatGttbrm,"
				+ "PHAT_LDSVT_XLSCT phatLdsvtXlsct," + "PHAT_LYTTD phatLyttd," + "PHAT_QCXLSC phatQcxlsc,"
				+ "PHAT_TBCDCNKTD phatTbcdcnktd," + "PHAT_TBRMKTD phatTbrmktd," + "TBMTBCS_0_2 tbmtbcs02,"
				+ "TBMTBCS_3 tbmtbcs3," + "TBMTBCS_4 tbmtbcs4," + "TBMTBCS_5 tbmtbcs5," + "TBMTBCS_6 tbmtbcs6,"
				+ "TBMTBDV_0_2 tbmtbdv02," + "TBMTBDV_3 tbmtbdv3," + "TBMTBDV_4 tbmtbdv4," + "TBMTBDV_5 tbmtbdv5,"
				+ "TBMTBDV_6 tbmtbdv6," + "TBMTBDV_7 tbmtbdv7," + "TBMTBKT_0_2 tbmtbkt02," + "TBMTBKT_3 tbmtbkt3,"
				+ "TBMTBKT_4 tbmtbkt4," + "TBMTBKT_5 tbmtbkt5," + "TBMTBKT_6 tbmtbkt6," + "TBMTBKT_7 tbmtbkt7,"
				+ "TBMTBT_0_2 tbmtbt02," + "TBMTBT_3 tbmtbt3," + "TBMTBT_4 tbmtbt4," + "TBMTBT_5 tbmtbt5,"
				+ "TBMTBT_6 tbmtbt6," + "TBMTBT_7 tbmtbt7," + "TBMTBWF_0_2 tbmtbwf02," + "TBMTBWF_3 tbmtbwf3,"
				+ "TBMTBWF_4 tbmtbwf4," + "TBMTBWF_5 tbmtbwf5," + "TBMTBWF_6 tbmtbwf6," + "TEN_HUYEN tenHuyen,"
				+ "XOA xoa " + " from TBL_DL_DAU_VAO_DAY_MAY  where KY_LUONG = :kyLuong");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("tblDlDauVaoDayMayId", new LongType());
		query.addScalar("ddtbqlDaToaNha", new FloatType());
		query.addScalar("ddtbqlDayTbAon", new FloatType());
		query.addScalar("ddtbqlDayTbkt", new FloatType());
		query.addScalar("ddtbqlTongQuyDoi", new FloatType());
		query.addScalar("doiTuong", new StringType());
		query.addScalar("donVi", new StringType());
		query.addScalar("ghep", new StringType());
		query.addScalar("ghiChu", new StringType());
		query.addScalar("hoVaTen", new StringType());
		query.addScalar("hoatDong", new LongType());
		query.addScalar("kyLuong", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("nhomTruong", new FloatType());
		query.addScalar("maNv", new StringType());

		query.addScalar("phatGttbrm", new FloatType());
		query.addScalar("phatLdsvtXlsct", new FloatType());
		query.addScalar("phatLyttd", new FloatType());
		query.addScalar("phatQcxlsc", new FloatType());

		query.addScalar("phatTbcdcnktd", new FloatType());
		query.addScalar("phatTbrmktd", new FloatType());
		query.addScalar("tbmtbcs02", new FloatType());
		query.addScalar("tbmtbcs3", new FloatType());

		query.addScalar("tbmtbcs4", new FloatType());
		query.addScalar("tbmtbcs5", new FloatType());
		query.addScalar("tbmtbcs6", new FloatType());
		query.addScalar("tbmtbdv02", new FloatType());

		query.addScalar("tbmtbdv3", new FloatType());
		query.addScalar("tbmtbdv4", new FloatType());
		query.addScalar("tbmtbdv5", new FloatType());
		query.addScalar("tbmtbdv6", new FloatType());

		query.addScalar("tbmtbdv7", new FloatType());
		query.addScalar("tbmtbkt02", new FloatType());
		query.addScalar("tbmtbkt3", new FloatType());
		query.addScalar("tbmtbkt4", new FloatType());

		query.addScalar("tbmtbkt5", new FloatType());
		query.addScalar("tbmtbkt6", new FloatType());
		query.addScalar("tbmtbkt7", new FloatType());
		query.addScalar("tbmtbt02", new FloatType());

		query.addScalar("tbmtbt3", new FloatType());
		query.addScalar("tbmtbt4", new FloatType());
		query.addScalar("tbmtbt5", new FloatType());
		query.addScalar("tbmtbt6", new FloatType());

		query.addScalar("tbmtbt7", new FloatType());
		query.addScalar("tbmtbwf02", new FloatType());
		query.addScalar("tbmtbwf3", new FloatType());
		query.addScalar("tbmtbwf4", new FloatType());

		query.addScalar("tbmtbwf5", new FloatType());
		query.addScalar("tbmtbwf6", new FloatType());
		query.addScalar("tenHuyen", new StringType());
		query.addScalar("xoa", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(TblDlDauVaoDayMayDTO.class));

		query.setParameter("kyLuong", kyLuong);
		return query.list();
	}

	public List<TblDmKiDonViDTO> getKiDonViByTinhAndThang(String tinh, String thang) {
		String hql = "select KI_DON_VI kiDonVi from TBL_DM_KI_DON_VI kidv where kidv.TINH = :tinh and kidv.THANG = :thang";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("kiDonVi", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDmKiDonViDTO.class));
		query.setParameter("tinh", tinh);
		query.setParameter("thang", thang);
		return query.list();
	}

	public List<TblDonGiaThueBaoDTO> getDonGiaByTinh(String tinh) {
		String hql = "select DON_GIA_MOI donGiaMoi from TBL_DON_GIA_THUE_BAO kidv where kidv.TINH = :tinh ";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("donGiaMoi", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblDonGiaThueBaoDTO.class));
		query.setParameter("tinh", tinh);

		return query.list();
	}

	public List<TblNgayCongDTO> getNgayCongByMaNvAndThang(String maNv, String thang) {
		String hql = "select NGAY_CONG_CHE_DO ngayCongCheDo,NGAY_CONG_TINH_LUONG ngayCongTinhLuong"
				+ " from TBL_NGAY_CONG nc where upper(nc.MA_NV) = upper(:maNv) and nc.THANG = :thang ";

		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("ngayCongCheDo", new FloatType());
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblNgayCongDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);

		return query.list();
	}

	public List<TblPhiBanHangDTO> getPhiBanHangByMaNvAndThang(String maNv, String thang) {
		String hql = "select PHI_TRUOC_THUE phiTruocThue from TBL_PHI_BAN_HANG nc where upper(nc.MA_NV) = upper(:maNv) and nc.THANG = :thang ";
		SQLQuery query = getSession().createSQLQuery(hql);

		query.addScalar("phiTruocThue", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblNgayCongDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);

		return query.list();
	}

	public List<TblCauHinhTienDTO> getTienByLoaiCvVaNgay(String loaiCongViec, String ngay) {
		String hql = "select MUC_TIEN mucTien from TBL_CAU_HINH_TIEN nc where nc.LOAI_CONG_VIEC = :loaiCongViec and nc.Ngay = :ngay ";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("mucTien", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblCauHinhTienDTO.class));
		query.setParameter("loaiCongViec", loaiCongViec);
		query.setParameter("ngay", ngay);

		return query.list();
	}

	public List<TblPhatXuLySuCoDTO> getPhatXlscByMaNvAndThang(String maNv, String thang) {
		String hql = "select TONG tong from TBL_PHAT_XU_LY_SU_CO nc where upper(nc.MA_NV) = upper(:maNv) and nc.THANG = :thang ";

		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("tong", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblPhatXuLySuCoDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);

		return query.list();
	}

	public List<TblPhatPakhDTO> getPhatPakhByMaNvAndThang(String maNv, String thang) {
		String hql = "select PHAT phat from TBL_PHAT_PAKH nc where upper(nc.MA_NV) = upper(:maNv) and nc.THANG = :thang ";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("phat", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblPhatPakhDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);
		return query.list();

	}

	public List<TblDmThongTinLoiDayMayDTO> getPhatKhacByMaNvAndThangAndMaLoi(String maNv, String thang, String maLoi) {

		String sql = "SELECT ttldm.ma_nv maNv,ttldm.MA_LOI maLoi,ttldm.THANG thang,ttldm.CUOC_PHAT_SINH cuocPhatSinh, "
				+ " dmml.tien_phat tienPhat " + "FROM TBL_DM_THONG_TIN_LOI_DAY_MAY ttldm "
				+ "join tbl_dm_ma_loi dmml on ttldm.MA_LOI = dmml.ma_loi "
				+ "where upper(ttldm.MA_NV) = upper(:maNv) and ttldm.THANG = :thang and ttldm.MA_LOI = :maLoi ";
		SQLQuery query = getSession().createSQLQuery(sql);

		query.addScalar("maNv", new StringType());
		query.addScalar("maLoi", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("cuocPhatSinh", new FloatType());
		query.addScalar("tienPhat", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblDmThongTinLoiDayMayDTO.class));

		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);
		query.setParameter("maLoi", maLoi);

		return query.list();

	}

	public List<TblLoiLapLaiDTO> getPhatLoiLapLaiByMaNvAndThangAndLdv(String maNv, String thang, String loaiDv) {
		String hql = "select PHAT phat from TBL_LOI_LAP_LAI nc where upper(nc.MA_NV) = upper(:maNv) and nc.thang = :thang and nc.LOAI_DV = :loaiDv ";

		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("phat", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblLoiLapLaiDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);
		query.setParameter("loaiDv", loaiDv);
		return query.list();
	}

	public List<TblPhatFcDTO> getPhatFcByMaNvAndThang(String maNv, String thang) {
		String hql = "select STPTTAG stpttag from TBL_PHAT_FC nc where upper(nc.MA_NV) = upper(:maNv) and nc.thang = :thang ";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("stpttag", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblPhatFcDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);
		return query.list();
	}

	public List<TblLoiDongAoDTO> getPhatDongAoByMaNvAndThang(String maNv, String thang) {
		String hql = "select THANH_TIEN thanhTien from TBL_LOI_DONG_AO nc where upper(nc.MA_NV) = upper(:maNv) and nc.thang = :thang ";
		SQLQuery query = getSession().createSQLQuery(hql);

		query.addScalar("thanhTien", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblLoiDongAoDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);
		return query.list();
	}

	public List<TblPhatRoiMangDTO> getPhatRoiMangByMaNvAndThang(String maTtns, String thang) {
		String hql = "select PHAT_SAU_THUE phatSauThue from TBL_PHAT_ROI_MANG nc where upper(nc.MA_TTNS) = upper(:maTtns) and nc.thang = :thang ";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("phatSauThue", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblPhatRoiMangDTO.class));
		query.setParameter("maTtns", maTtns);
		query.setParameter("thang", thang);
		return query.list();
	}

	public List<TblPhatCdtDTO> getPhatCdtByMaNvAndThang(String maNv, String thang) {
		String hql = "select PHAT_QCXLSC phatQcxlsc," + "PHAT_LDSVT_XLSCT phatLdsvtXlsct," + "PHAT_GTTBRM phatGttbrm,"
				+ "PHAT_TBCDCNKTD phatTbcdcnktd," + "PHAT_TBRMKTD phatTbrmktd," + "PHAT_LYTTD phatLyttd "
				+ " from TBL_PHAT_CDT nc where upper(nc.MA_NV) = upper(:maNv) and nc.thang = :thang ";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.addScalar("phatQcxlsc", new FloatType());
		query.addScalar("phatLdsvtXlsct", new FloatType());
		query.addScalar("phatGttbrm", new FloatType());
		query.addScalar("phatTbcdcnktd", new FloatType());
		query.addScalar("phatTbrmktd", new FloatType());
		query.addScalar("phatLyttd", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblPhatCdtDTO.class));

		query.setParameter("maNv", maNv);
		query.setParameter("thang", thang);
		return query.list();
	}

	public Long deleteObj(String thang) {
		try {
			String sql2 = "delete from TBL_LUONG_DAY_MAY  where  THANG=:thang ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("thang", thang);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	// public List<TblDanhMucDTO> getHeSoDieuChinh() {
	// String hql = "from TblDanhMuc kidv where kidv.maDanhMuc = 'TL'";
	// return result;
	// }

}
