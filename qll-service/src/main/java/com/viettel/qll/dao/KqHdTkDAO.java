package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.FloatType;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.KqHdTkBO;
import com.viettel.qll.dto.KqHdTkDTO;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("kqHdTkDAO")
public class KqHdTkDAO extends BaseFWDAOImpl<KqHdTkBO, Long> {

	public KqHdTkDAO() {
		this.model = new KqHdTkBO();
	}

	public KqHdTkDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<KqHdTkDTO> doSearch(KqHdTkDTO obj) {
		StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" T1.KQ_HD_TK_ID kqHdTkId ");
		stringBuilder.append(",T1.TINH tinh ");
		stringBuilder.append(",T1.MA_TRAM_TUYEN maTramTuyen ");
		stringBuilder.append(",T1.NGUON_CAP_UNG nguonCapUng ");
		stringBuilder.append(",T1.SO_HD soHd ");
		stringBuilder.append(",T1.SO_KH_TC soKhTc ");
		stringBuilder.append(",T1.NGAY_KY_KH ngayKyKh ");
		stringBuilder.append(",T1.THUOC_DM_TO_TRINH thuocDmToTrinh ");
		stringBuilder.append(",T1.LOAI_CT loaiCt ");
		stringBuilder.append(",T1.NOI_DUNG noiDung ");
		stringBuilder.append(",T1.NGAY_GUI_HSHC ngayGuiHshc ");
		stringBuilder.append(",T1.SO_BILL soBill ");
		stringBuilder.append(",T1.GHI_CHU ghiChu ");
		stringBuilder.append(",T1.GT_QT_CDT_CHUA_VAT gtQtCdtChuaVat ");
		stringBuilder.append(",T1.GT_QT_CDT_CO_VAT gtQtCdtCoVat ");
		stringBuilder.append(",T1.CP_NHAN_CONG_DN cpNhanCongDn ");
		stringBuilder.append(",T1.CP_VAT_LIEU_DN cpVatLieuDn ");
		stringBuilder.append(",T1.CP_HSHC_DN cpHshcDn ");
		stringBuilder.append(",T1.CP_VAN_CHUYEN_DN cpVanChuyenDn ");
		stringBuilder.append(",T1.CHI_PHI_KHAC_DN chiPhiKhacDn ");
		stringBuilder.append(",T1.CHI_PHI_LUONG_DN chiPhiLuongDn ");
		stringBuilder.append(",T1.VAT_DN vatDn ");
		stringBuilder.append(",T1.TONG_DN tongDn ");
		stringBuilder.append(",T1.NGUOI_CAP_NHAT nguoiCapNhat ");
		stringBuilder.append(",T1.NGAY_GUI_PTK_HSHC ngayGuiPtkHshc ");
		stringBuilder.append(",T1.TINH_TRANG_CHUNG_TU tinhTrangChungTu ");
		stringBuilder.append(",T1.NGAY_TUTTTT ngayTutttt ");
		stringBuilder.append(",T1.GHI_CHU_HS_LOI ghiChuHsLoi ");
		stringBuilder.append(",T1.NGAY_PTK_TD_XONG ngayPtkTdXong ");
		stringBuilder.append(",T1.CP_NHAN_CONG_TD cpNhanCongTd ");
		stringBuilder.append(",T1.CP_VAT_LIEU_TD cpVatLieuTd ");
		stringBuilder.append(",T1.CP_HSHC_TD cpHshcTd ");
		stringBuilder.append(",T1.CP_VAN_CHUYEN_TD cpVanChuyenTd ");
		stringBuilder.append(",T1.CP_KHAC_TD cpKhacTd ");
		stringBuilder.append(",T1.VAT_TD vatTd ");
		stringBuilder.append(",T1.TONG_TD tongTd ");
		stringBuilder.append(",T1.GT_TD_PTK_CHUA_VAT gtTdPtkChuaVat ");
		stringBuilder.append(",T1.GT_TD_PTK_CO_VAT gtTdPtkCoVat ");
		stringBuilder.append(",T1.THANG_HSHC_QUY_LUONG thangHshcQuyLuong ");
		stringBuilder.append(",T1.THANG_GHI_NHAN_QUY_LUONG_TQT thangGhiNhanQuyLuongTqt ");
		stringBuilder.append(",T1.TAM_UNG_LUONG tamUngLuong ");
		stringBuilder.append(",T1.LUONG_THUC_NHAN luongThucNhan ");
		stringBuilder.append(",T1.TY_LE tyLe ");
		stringBuilder.append(",T1.HS_TON_QUA_5N hsTonQua5n ");
		stringBuilder.append(",T1.GT_QH_XL_CT gtQhXlCt ");
		stringBuilder.append(",T1.NGAY_THI_CONG_XONG ngayThiCong");
		stringBuilder.append(
				",T1.NGUOI_PHE_DUYET_PTK nguoiPheDuyetPtk,DM.TEN_DANH_MUC tenTinh,T1.NGAY_CAP_NHAT ngayCapNhat,T1.NGUOI_TAO nguoiTao");
		stringBuilder.append(
				",T1.CP_LUONG_TD cpLuongTd,T1.CHECK_TRINH_KY checkTrinhKy,T1.LY_DO_TC lyDoTc,T1.PATH_FILE pathFile,T1.TRANG_THAI trangThai ");
		stringBuilder.append(" FROM KQ_HD_TK T1 join TBL_DANH_MUC DM on T1.TINH=DM.MA_DANH_MUC where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder.append(" AND (upper(T1.TINH) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MA_TRAM_TUYEN) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.NGUON_CAP_UNG) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.SO_HD) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.TRANG_THAI) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.SO_KH_TC) LIKE upper(:keySearch) escape '&')");
		}

		if (StringUtils.isNotEmpty(obj.getTinh())) {
			stringBuilder.append("AND (UPPER(T1.TINH) LIKE UPPER(:tinh) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getMaTramTuyen())) {
			stringBuilder.append("AND (UPPER(T1.MA_TRAM_TUYEN) LIKE UPPER(:maTramTuyen) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getNguonCapUng())) {
			stringBuilder.append("AND (UPPER(T1.NGUON_CAP_UNG) LIKE UPPER(:nguonCapUng) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			stringBuilder.append("AND (UPPER(T1.SO_HD) LIKE UPPER(:soHd) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getSoKhTc())) {
			stringBuilder.append("AND (UPPER(T1.SO_KH_TC) LIKE UPPER(:soKhTc) ESCAPE '&') ");
		}
		
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			stringBuilder.append("AND (UPPER(T1.TRANG_THAI) LIKE UPPER(:trangThai) ESCAPE '&') ");
		}
		if (null != obj.getNgayKyKhFrom() && obj.getNgayKyKhTo() == null) {
			stringBuilder.append("AND T1.NGAY_KY_KH >= :ngayKyKhFrom ");
		}
		if (null != obj.getNgayKyKhTo() && null != obj.getNgayKyKhFrom()) {
			stringBuilder.append(" AND (T1.NGAY_KY_KH between :ngayKyKhFrom and :ngayKyKhTo) ");
		}
		if (null != obj.getNgayGuiHshcFrom() && obj.getNgayGuiHshcTo() == null) {
			stringBuilder.append("AND T1.NGAY_GUI_HSHC >= :ngayGuiHshcFrom ");
		}
		if (null != obj.getNgayGuiHshcTo() && null != obj.getNgayGuiHshcFrom()) {
			stringBuilder.append(" AND (T1.NGAY_GUI_HSHC between :ngayGuiHshcFrom and :ngayGuiHshcTo) ");
		}
		if (StringUtils.isNotEmpty(obj.getLoaiCt())) {
			stringBuilder.append("AND (UPPER(T1.LOAI_CT) LIKE UPPER(:loaiCt) escape '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getNoiDung())) {
			stringBuilder.append("AND (UPPER(T1.NOI_DUNG) LIKE UPPER(:noiDung) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getSoBill())) {
			stringBuilder.append("AND (UPPER(T1.SO_BILL) LIKE UPPER(:soBill) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getTinhTrangChungTu())) {
			stringBuilder.append("AND (UPPER(T1.TINH_TRANG_CHUNG_TU) LIKE UPPER(:tinhTrangChungTu) escape '&') ");
		}

		stringBuilder.append(" order by T1.KQ_HD_TK_ID desc");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("kqHdTkId", new LongType());
		query.addScalar("tinh", new StringType());
		query.addScalar("maTramTuyen", new StringType());
		query.addScalar("nguonCapUng", new StringType());
		query.addScalar("soHd", new StringType());
		query.addScalar("soKhTc", new StringType());
		query.addScalar("ngayKyKh", new DateType());
		query.addScalar("thuocDmToTrinh", new StringType());
		query.addScalar("loaiCt", new StringType());
		query.addScalar("noiDung", new StringType());
		query.addScalar("ngayGuiHshc", new DateType());
		query.addScalar("soBill", new StringType());
		query.addScalar("ghiChu", new StringType());
		query.addScalar("gtQtCdtChuaVat", new FloatType());
		query.addScalar("gtQtCdtCoVat", new FloatType());
		query.addScalar("cpNhanCongDn", new FloatType());
		query.addScalar("cpVatLieuDn", new FloatType());
		query.addScalar("cpHshcDn", new FloatType());
		query.addScalar("cpVanChuyenDn", new FloatType());
		query.addScalar("chiPhiKhacDn", new FloatType());
		query.addScalar("chiPhiLuongDn", new FloatType());
		query.addScalar("vatDn", new FloatType());
		query.addScalar("tongDn", new FloatType());
		query.addScalar("nguoiCapNhat", new StringType());
		query.addScalar("ngayGuiPtkHshc", new DateType());
		query.addScalar("tinhTrangChungTu", new StringType());
		query.addScalar("ngayTutttt", new DateType());
		query.addScalar("ghiChuHsLoi", new StringType());
		query.addScalar("ngayPtkTdXong", new DateType());
		query.addScalar("cpNhanCongTd", new FloatType());
		query.addScalar("cpVatLieuTd", new FloatType());
		query.addScalar("cpHshcTd", new FloatType());
		query.addScalar("cpVanChuyenTd", new FloatType());
		query.addScalar("cpKhacTd", new FloatType());
		query.addScalar("vatTd", new FloatType());
		query.addScalar("tongTd", new FloatType());
		query.addScalar("gtTdPtkChuaVat", new FloatType());
		query.addScalar("gtTdPtkCoVat", new FloatType());
		query.addScalar("thangHshcQuyLuong", new StringType());
		query.addScalar("thangGhiNhanQuyLuongTqt", new StringType());
		query.addScalar("tamUngLuong", new FloatType());
		query.addScalar("luongThucNhan", new FloatType());
		query.addScalar("tyLe", new FloatType());
		query.addScalar("hsTonQua5n", new StringType());
		query.addScalar("gtQhXlCt", new StringType());
		query.addScalar("nguoiPheDuyetPtk", new StringType());
		query.addScalar("cpLuongTd", new FloatType());
		query.addScalar("tenTinh", new StringType());
		query.addScalar("checkTrinhKy", new FloatType());
		query.addScalar("lyDoTc", new StringType());
		query.addScalar("pathFile", new StringType());
		query.addScalar("ngayCapNhat", new DateType());
		query.addScalar("nguoiTao", new StringType());
		query.addScalar("trangThai", new StringType());
		query.addScalar("ngayThiCong", new DateType());
		
		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			queryCount.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			// query.setParameter("tinh", obj.getTinh());
			// queryCount.setParameter("tinh", obj.getTinh());
		}
		if (StringUtils.isNotEmpty(obj.getMaTramTuyen())) {
			query.setParameter("maTramTuyen", "%" + ValidateUtils.validateKeySearch(obj.getMaTramTuyen()) + "%");
			queryCount.setParameter("maTramTuyen", "%" + ValidateUtils.validateKeySearch(obj.getMaTramTuyen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getNguonCapUng())) {
			query.setParameter("nguonCapUng", "%" + ValidateUtils.validateKeySearch(obj.getNguonCapUng()) + "%");
			queryCount.setParameter("nguonCapUng", "%" + ValidateUtils.validateKeySearch(obj.getNguonCapUng()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			query.setParameter("soHd", "%" + ValidateUtils.validateKeySearch(obj.getSoHd()) + "%");
			queryCount.setParameter("soHd", "%" + ValidateUtils.validateKeySearch(obj.getSoHd()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getSoKhTc())) {
			query.setParameter("soKhTc", "%" + ValidateUtils.validateKeySearch(obj.getSoKhTc()) + "%");
			queryCount.setParameter("soKhTc", "%" + ValidateUtils.validateKeySearch(obj.getSoKhTc()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			query.setParameter("trangThai", "%" + ValidateUtils.validateKeySearch(obj.getTrangThai()) + "%");
			queryCount.setParameter("trangThai", "%" + ValidateUtils.validateKeySearch(obj.getTrangThai()) + "%");
		}

		if (null != obj.getNgayKyKhFrom() && obj.getNgayKyKhTo() == null) {
			query.setTimestamp("ngayKyKhFrom", obj.getNgayKyKhFrom());
			queryCount.setTimestamp("ngayKyKhFrom", obj.getNgayKyKhFrom());
		}
		if (null != obj.getNgayKyKhTo() && null != obj.getNgayKyKhFrom()) {
			query.setTimestamp("ngayKyKhFrom", obj.getNgayKyKhFrom());
			queryCount.setTimestamp("ngayKyKhFrom", obj.getNgayKyKhFrom());

			query.setTimestamp("ngayKyKhTo", obj.getNgayKyKhTo());
			queryCount.setTimestamp("ngayKyKhTo", obj.getNgayKyKhTo());
		}
		if (null != obj.getNgayGuiHshcFrom() && obj.getNgayGuiHshcTo() == null) {
			query.setTimestamp("ngayGuiHshcFrom", obj.getNgayGuiHshcFrom());
			queryCount.setTimestamp("ngayGuiHshcFrom", obj.getNgayGuiHshcFrom());
		}
		if (null != obj.getNgayGuiHshcTo() && null != obj.getNgayGuiHshcFrom()) {
			query.setTimestamp("ngayGuiHshcFrom", obj.getNgayGuiHshcFrom());
			queryCount.setTimestamp("ngayGuiHshcFrom", obj.getNgayGuiHshcFrom());

			query.setTimestamp("ngayGuiHshcTo", obj.getNgayGuiHshcTo());
			queryCount.setTimestamp("ngayGuiHshcTo", obj.getNgayGuiHshcTo());
		}
		if (StringUtils.isNotEmpty(obj.getLoaiCt())) {
			query.setParameter("loaiCt", "%" + ValidateUtils.validateKeySearch(obj.getLoaiCt()) + "%");
			queryCount.setParameter("loaiCt", "%" + ValidateUtils.validateKeySearch(obj.getLoaiCt()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getNoiDung())) {
			query.setParameter("noiDung", "%" + ValidateUtils.validateKeySearch(obj.getNoiDung()) + "%");
			queryCount.setParameter("noiDung", "%" + ValidateUtils.validateKeySearch(obj.getNoiDung()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getSoBill())) {
			query.setParameter("soBill", "%" + ValidateUtils.validateKeySearch(obj.getSoBill()) + "%");
			queryCount.setParameter("soBill", "%" + ValidateUtils.validateKeySearch(obj.getSoBill()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getTinhTrangChungTu())) {
			query.setParameter("tinhTrangChungTu",
					"%" + ValidateUtils.validateKeySearch(obj.getTinhTrangChungTu()) + "%");
			queryCount.setParameter("tinhTrangChungTu",
					"%" + ValidateUtils.validateKeySearch(obj.getTinhTrangChungTu()) + "%");
		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<KqHdTkDTO> getForAutoCompleteHD(KqHdTkDTO obj) {
		String sql = "SELECT distinct " + "T1.SO_HD soHd " + " FROM KQ_HD_TK T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			stringBuilder.append(" AND (upper(T1.SO_HD) LIKE upper(:soHd) escape '&' )");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("soHd", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));

		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			query.setParameter("soHd", "%" + ValidateUtils.validateKeySearch(obj.getSoHd()) + "%");
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<KqHdTkDTO> getForAutoCompleteKHTC(KqHdTkDTO obj) {
		String sql = "SELECT distinct " + "T1.SO_KH_TC soKhTc " + " FROM KQ_HD_TK T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getSoKhTc())) {
			stringBuilder.append(" AND (upper(T1.SO_KH_TC) LIKE upper(:soKhTc) escape '&' )");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("soKhTc", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));

		if (StringUtils.isNotEmpty(obj.getSoKhTc())) {
			query.setParameter("soKhTc", "%" + ValidateUtils.validateKeySearch(obj.getSoKhTc()) + "%");
		}

		return query.list();
	}

	/**
	 * sum lương thực nhận của từng Số kế hoạch thi công được duyệt
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KqHdTkDTO> tongLuongThucNhan(KqHdTkDTO obj) {
		String sql = "SELECT " + " T1.TINH tinh," + "T1.THANG_GHI_NHAN_QUY_LUONG_TQT thangGhiNhanQuyLuongTqt,"
				+ "T1.NGAY_KY_KH ngayKyKh," + "T1.SO_KH_TC soKhTc," + "T1.GHI_CHU ghiChu,T1.MA_TRAM_TUYEN  maTramTuyen,"
				+ "T1.LOAI_CT loaiCt,T1.TAM_UNG_LUONG tamUngLuong"
				+ ",T1.LUONG_THUC_NHAN luongThucNhan,T1.CP_LUONG_TD cpLuongTd "
				+ " FROM KQ_HD_TK T1 where T1.THANG_GHI_NHAN_QUY_LUONG_TQT =:thangGhiNhanQuyLuongTqt";

		StringBuilder stringBuilder = new StringBuilder(sql);

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("soKhTc", new StringType());
		query.addScalar("ghiChu", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("thangGhiNhanQuyLuongTqt", new StringType());
		query.addScalar("ngayKyKh", new DateType());
		query.addScalar("luongThucNhan", new FloatType());
		query.addScalar("cpLuongTd", new FloatType());
		query.addScalar("tamUngLuong", new FloatType());
		query.addScalar("loaiCt", new StringType());
		query.addScalar("maTramTuyen", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));
		query.setParameter("thangGhiNhanQuyLuongTqt", obj.getThangGhiNhanQuyLuongTqt());
		return query.list();
	}

	public KqHdTkDTO xuatBCTH(KqHdTkDTO obj) {
		StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" count(T1.SO_HD) soHs1 ");
		stringBuilder.append(",sum(T1.GT_QT_CDT_CHUA_VAT) gtQtCdtChuaVat ");
		stringBuilder.append(",sum(T1.GT_QT_CDT_CO_VAT) gtQtCdtCoVat ");
		stringBuilder.append(",sum(T1.CP_NHAN_CONG_DN) cpNhanCongDn ");
		stringBuilder.append(",sum(T1.CP_VAT_LIEU_DN) cpVatLieuDn ");
		stringBuilder.append(",sum(T1.CP_HSHC_DN) cpHshcDn ");
		stringBuilder.append(",sum(T1.CP_VAN_CHUYEN_DN) cpVanChuyenDn ");
		stringBuilder.append(",sum(T1.CHI_PHI_KHAC_DN) chiPhiKhacDn ");
		stringBuilder.append(",sum(T1.CHI_PHI_LUONG_DN) chiPhiLuongDn ");
		stringBuilder.append(",sum(T1.VAT_DN) vatDn ");
		stringBuilder.append(",sum(T1.TONG_DN) tongDn ");
		stringBuilder.append(",sum(T1.CP_NHAN_CONG_TD) cpNhanCongTd ");
		stringBuilder.append(",sum(T1.CP_VAT_LIEU_TD) cpVatLieuTd ");
		stringBuilder.append(",sum(T1.CP_HSHC_TD) cpHshcTd ");
		stringBuilder.append(",sum(T1.CP_VAN_CHUYEN_TD) cpVanChuyenTd ");
		stringBuilder.append(",sum(T1.CP_KHAC_TD) cpKhacTd ");
		stringBuilder.append(",sum(T1.VAT_TD) vatTd ");
		stringBuilder.append(",sum(T1.TONG_TD) tongTd ");
		stringBuilder.append(",sum(T1.GT_TD_PTK_CHUA_VAT) gtTdPtkChuaVat ");
		stringBuilder.append(",sum(T1.GT_TD_PTK_CO_VAT) gtTdPtkCoVat ");
		stringBuilder.append(",sum(T1.TAM_UNG_LUONG) tamUngLuong ");
		stringBuilder.append(",sum(T1.LUONG_THUC_NHAN) luongThucNhan ");
		stringBuilder.append(",sum(T1.CP_LUONG_TD) cpLuongTd ");
		stringBuilder.append(" FROM KQ_HD_TK T1 where T1.TINH =:tinh ");
		if (obj.getThangGhiNhanQuyLuongTqt() != null) {
			stringBuilder.append("AND T1.THANG_GHI_NHAN_QUY_LUONG_TQT = :thangGhiNhanQuyLuongTqt ");
		}
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() == null) {
			stringBuilder.append(" AND (T1.NGAY_GUI_PTK_HSHC >=:ngayGuiPtkHshcFrom) ");
		}

		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() != null) {
			stringBuilder.append(" AND (T1.NGAY_GUI_PTK_HSHC between :ngayGuiPtkHshcFrom and :ngayGuiPtkHshcTo) ");
		}

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		// query.addScalar("tinh", new StringType());
		query.addScalar("soHs1", new LongType());
		query.addScalar("gtQtCdtChuaVat", new FloatType());
		query.addScalar("gtQtCdtCoVat", new FloatType());
		query.addScalar("cpNhanCongDn", new FloatType());
		query.addScalar("cpVatLieuDn", new FloatType());
		query.addScalar("cpHshcDn", new FloatType());
		query.addScalar("cpVanChuyenDn", new FloatType());
		query.addScalar("chiPhiKhacDn", new FloatType());
		query.addScalar("chiPhiLuongDn", new FloatType());
		query.addScalar("vatDn", new FloatType());
		query.addScalar("tongDn", new FloatType());
		query.addScalar("cpNhanCongTd", new FloatType());
		query.addScalar("cpVatLieuTd", new FloatType());
		query.addScalar("cpHshcTd", new FloatType());
		query.addScalar("cpVanChuyenTd", new FloatType());
		query.addScalar("cpKhacTd", new FloatType());
		query.addScalar("vatTd", new FloatType());
		query.addScalar("tongTd", new FloatType());
		query.addScalar("gtTdPtkChuaVat", new FloatType());
		query.addScalar("gtTdPtkCoVat", new FloatType());
		query.addScalar("tamUngLuong", new FloatType());
		query.addScalar("luongThucNhan", new FloatType());
		query.addScalar("cpLuongTd", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));
		query.setParameter("tinh", obj.getTinh());
		if (obj.getThangGhiNhanQuyLuongTqt() != null) {
			query.setParameter("thangGhiNhanQuyLuongTqt", obj.getThangGhiNhanQuyLuongTqt());
		}
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() == null) {
			query.setTimestamp("ngayGuiPtkHshcFrom", obj.getNgayGuiPtkHshcFrom());
		}
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() != null) {
			query.setTimestamp("ngayGuiPtkHshcFrom", obj.getNgayGuiPtkHshcFrom());
			query.setTimestamp("ngayGuiPtkHshcTo", obj.getNgayGuiPtkHshcTo());
		}

		return (KqHdTkDTO) query.uniqueResult();
	}
	
	
	public List<KqHdTkDTO> lstTrangThai(KqHdTkDTO obj) {
		StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" T1.TINH_TRANG_CHUNG_TU tinhTrangChungTu,T1.HS_TON_QUA_5N hsTonQua5n,T1.NGAY_GUI_PTK_HSHC ngayGuiPtkHshc ");
		stringBuilder.append(" FROM KQ_HD_TK T1 where T1.TINH =:tinh ");
		if (obj.getThangGhiNhanQuyLuongTqt() != null) {
			stringBuilder.append("AND T1.THANG_GHI_NHAN_QUY_LUONG_TQT = :thangGhiNhanQuyLuongTqt ");
		}
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() == null) {
			stringBuilder.append(" AND (T1.NGAY_GUI_PTK_HSHC >=:ngayGuiPtkHshcFrom) ");
		}

		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() != null) {
			stringBuilder.append(" AND (T1.NGAY_GUI_PTK_HSHC between :ngayGuiPtkHshcFrom and :ngayGuiPtkHshcTo) ");
		}

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("tinhTrangChungTu", new StringType());
		query.addScalar("hsTonQua5n", new StringType());
		query.addScalar("ngayGuiPtkHshc", new DateType());

		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));
		if (obj.getThangGhiNhanQuyLuongTqt() != null) {
			query.setParameter("thangGhiNhanQuyLuongTqt", obj.getThangGhiNhanQuyLuongTqt());
		}
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() == null) {
			query.setTimestamp("ngayGuiPtkHshcFrom", obj.getNgayGuiPtkHshcFrom());
		}
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() != null) {
			query.setTimestamp("ngayGuiPtkHshcFrom", obj.getNgayGuiPtkHshcFrom());
			query.setTimestamp("ngayGuiPtkHshcTo", obj.getNgayGuiPtkHshcTo());
		}
		query.setParameter("tinh", obj.getTinh());

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<KqHdTkDTO> getMaTinh(KqHdTkDTO obj) {
		String sql = "SELECT distinct " + "T1.TINH tinh " + " FROM KQ_HD_TK T1 where T1.TINH is not null ";

		StringBuilder stringBuilder = new StringBuilder(sql);
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() == null) {
			stringBuilder.append(" AND (T1.NGAY_GUI_PTK_HSHC >=:ngayGuiPtkHshcFrom) ");
		}

		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() != null) {
			stringBuilder.append(" AND (T1.NGAY_GUI_PTK_HSHC between :ngayGuiPtkHshcFrom and :ngayGuiPtkHshcTo) ");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("tinh", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() == null) {
			query.setTimestamp("ngayGuiPtkHshcFrom", obj.getNgayGuiPtkHshcFrom());
		}
		if (null != obj.getNgayGuiPtkHshcFrom() && obj.getNgayGuiPtkHshcTo() != null) {
			query.setTimestamp("ngayGuiPtkHshcFrom", obj.getNgayGuiPtkHshcFrom());
			query.setTimestamp("ngayGuiPtkHshcTo", obj.getNgayGuiPtkHshcTo());
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<KqHdTkDTO> getAllMaTinh() {
		String sql = "SELECT " + "T1.TINH tinh,T1.NGAY_GUI_PTK_HSHC  ngayGuiPtkHshc " + " FROM KQ_HD_TK T1 where T1.TINH is not null ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("tinh", new StringType());
		query.addScalar("ngayGuiPtkHshc", new DateType());
		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));
		return query.list();
	}
//	public Long updateTd(KqHdTkDTO obj){
//		try {
//
//			String sql2 = "update KQ_HD_TK "
//							+ "set "
//							+ "TINH=:tinh,"
//							+ "MA_TRAM_TUYEN=:maTramTuyen,"
//							+ ""
//							+ ""
//							+ ""
//							+ "NGAY_GUI_PTK_HSHC=:ngayGuiPtkHshc,"
//							+ "TINH_TRANG_CHUNG_TU=:tinhTrangChungTu,"
//							+ "NGAY_TUTTTT=:ngayTutttt,"
//							+ "GHI_CHU_HS_LOI=:ghiChuHsLoi,"
//							+ "NGAY_PTK_TD_XONG=:ngayPtkTdXong,"
//							+ "CP_NHAN_CONG_TD=:cpNhanCongTd,"
//							+ "CP_VAT_LIEU_TD=:cpVatLieuTd,"
//							+ "CP_HSHC_TD=:cpHshcTd,"
//							+ "CP_VAN_CHUYEN_TD=:cpVanChuyenTd,"
//							+ "CP_KHAC_TD=:cpKhacTd,"
//							+ "CP_LUONG_TD=:cpLuongTd,"
//							+ "VAT_TD=:vatTd,"
//							+ "TONG_TD=:tongTd,"
//							+ "GT_TD_PTK_CHUA_VAT=:gtTdPtkChuaVat,"
//							+ "GT_TD_PTK_CO_VAT=:gtTdPtkCoVat,"
//							+ "THANG_HSHC_QUY_LUONG=:thangHshcQuyLuong,"
//							+ "THANG_GHI_NHAN_QUY_LUONG_TQT=:thangGhiNhanQuyLuongTqt,"
//							+ "TAM_UNG_LUONG=:tamUngLuong,"
//							+ "LUONG_THUC_NHAN=:luongThucNhan,"
//							+ "TY_LE=:tyLe,"
//							+ "HS_TON_QUA_5N=:hsTonQua5n,"
//							+ "GT_QH_XL_CT=:gtQhXlCt,"
//							+ "NGUOI_PHE_DUYET_PTK=:nguoiPheDuyetPtk,"
//							+ "LY_DO_TC=:lyDoTc,"
//							+ "TRANG_THAI=:trangThai"
//							+ " where KQ_HD_TK_ID =:kqHdTkId ";
//			SQLQuery query2 = getSession().createSQLQuery(sql2);
//			query2.setLong("kqHdTkId", obj.getKqHdTkId());
//			query2.setDate("ngayGuiPtkHshc", obj.getNgayGuiPtkHshc());
//			query2.setParameter("tinhTrangChungTu", obj.getTinhTrangChungTu());
//			query2.setDate("ngayTutttt", obj.getNgayTutttt());
//			query2.setParameter("ghiChuHsLoi", obj.getGhiChuHsLoi());
//			query2.setDate("ngayPtkTdXong", obj.getNgayPtkTdXong());
//			query2.setFloat("cpNhanCongTd", obj.getCpNhanCongTd());
//			query2.setFloat("cpVatLieuTd", obj.getCpVatLieuTd());
//			query2.setFloat("cpHshcTd", obj.getCpHshcTd());
//			query2.setFloat("cpVanChuyenTd", obj.getCpVanChuyenTd());
//			query2.setFloat("cpKhacTd", obj.getCpKhacTd());
//			query2.setFloat("cpLuongTd", obj.getCpLuongTd());
//			query2.setFloat("vatTd", obj.getVatTd());
//			query2.setFloat("tongTd", obj.getTongTd());
//			query2.setFloat("gtTdPtkChuaVat", obj.getGtTdPtkChuaVat());
//			query2.setFloat("gtTdPtkCoVat", obj.getGtTdPtkCoVat());
//			query2.setParameter("thangHshcQuyLuong", obj.getThangHshcQuyLuong());
//			query2.setParameter("thangGhiNhanQuyLuongTqt", obj.getThangGhiNhanQuyLuongTqt());
//			query2.setFloat("tamUngLuong",obj.getTamUngLuong());
//			query2.setFloat("luongThucNhan",obj.getLuongThucNhan());
//			query2.setFloat("tyLe",obj.getTyLe());
//			query2.setParameter("hsTonQua5n",obj.getHsTonQua5n());
//			query2.setParameter("gtQhXlCt",obj.getGtQhXlCt());
//			query2.setParameter("nguoiPheDuyetPtk",obj.getNguoiPheDuyetPtk());
//			query2.setParameter("lyDoTc",obj.getLyDoTc());
//			query2.setParameter("trangThai", obj.getTrangThai());
//			query2.executeUpdate();
//			return 1L;
//		} catch (Exception e) {
//			e.getMessage();
//			return 0L;
//		}
//	}
	

	public Long updatePath(KqHdTkDTO obj) {
		try {

			String sql2 = "update KQ_HD_TK set PATH_FILE=:pathFile where KQ_HD_TK_ID =:kqHdTkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("pathFile", obj.getPathFile());
			query2.setParameter("kqHdTkId", obj.getKqHdTkId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	public Long deleteObj(KqHdTkDTO obj) {
		try {

			String sql2 = "delete from KQ_HD_TK  where TINH=:tinh And MA_TRAM_TUYEN=:maTramTuyen "
						+ "and SO_HD=:soHd and SO_KH_TC=:soKhTc "
//						+ "and upper(THANG_HSHC_QUY_LUONG)=(:thangHshcQuyLuong)"
						+ "";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("tinh", obj.getTinh());
			query2.setParameter("maTramTuyen", obj.getMaTramTuyen());
			query2.setParameter("soHd", obj.getSoHd());
			query2.setParameter("soKhTc", obj.getSoKhTc());
//			query2.setParameter("thangHshcQuyLuong", obj.getThangHshcQuyLuong());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	public Long deleteList(List<Long> obj) {
		try {

			String sql2 = "delete from KQ_HD_TK  where KQ_HD_TK_ID in (:kqHdTkId) ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameterList("kqHdTkId", obj);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
//	@SuppressWarnings("unchecked")
//	public List<KqHdTkDTO> getCharTwoMonth() {
//		String sql = "select sum(T1.TONG_DN) tongDn,sum(T1.TONG_TD) tongTd,T1.THANG_GHI_NHAN_QUY_LUONG_TQT FROM KQ_HD_TK T1 where  1=1 Group by T1.THANG_GHI_NHAN_QUY_LUONG_TQT ";
//
//		StringBuilder stringBuilder = new StringBuilder(sql);
//
//		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//		query.addScalar("tongDn", new FloatType());
//		query.addScalar("tongTd", new FloatType());
//		query.addScalar("thangGhiNhanQuyLuongTqt", new StringType());
//		query.setResultTransformer(Transformers.aliasToBean(KqHdTkDTO.class));
//		
//		return query.list();
//	}

	@SuppressWarnings("unchecked")
	public List<CommonDTO> getCharTwoMonth() {
		String sql = "select sum(T1.TONG_DN) tongDn,sum(T1.TONG_TD) tongTd,T1.THANG_GHI_NHAN_QUY_LUONG_TQT thangGhiNhanQuyLuongTqt FROM KQ_HD_TK T1 where  THANG_GHI_NHAN_QUY_LUONG_TQT is not null Group by T1.THANG_GHI_NHAN_QUY_LUONG_TQT ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("tongDn", new FloatType());
		query.addScalar("tongTd", new FloatType());
		query.addScalar("thangGhiNhanQuyLuongTqt", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(CommonDTO.class));
		
		return query.list();
	}

}
