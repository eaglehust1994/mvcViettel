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

import com.viettel.qll.bo.QlCvPtkBO;

import com.viettel.qll.dto.QlCvPtkDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("qlCvPtkDAO")
public class QlCvPtkDAO extends BaseFWDAOImpl<QlCvPtkBO, Long> {

	public QlCvPtkDAO() {
		this.model = new QlCvPtkBO();
	}

	public QlCvPtkDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<QlCvPtkDTO> doSearch(QlCvPtkDTO obj) {
		StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append("T1.QL_CV_PTK_ID qlCvPtkId ");
		stringBuilder.append(",T1.CNKV cnkv ");
		stringBuilder.append(",T1.TINH tinh ");
		stringBuilder.append(",T1.MA_TRAM_TUYEN maTramTuyen ");
		stringBuilder.append(",T1.SO_HD soHd ");
		stringBuilder.append(",T1.THUOC_DM_TT thuocDmTt ");
		stringBuilder.append(",T1.LOAI_CT loaiCt ");
		stringBuilder.append(",T1.NOI_DUNG noiDung ");
		stringBuilder.append(",T1.NGAY_GUI_HSHC ngayGuiHshc ");
		stringBuilder.append(",T1.SO_BILL soBill ");
		stringBuilder.append(",T1.GHI_CHU ghiChu ");
		stringBuilder.append(",T1.GT_SL_HT_TINH gtSlHtTinh ");
		stringBuilder.append(",T1.GT_DN_QTK_CN gtDnQtkCn ");
		stringBuilder.append(",T1.NGAY_DN_QTK ngayDnQtk ");
		stringBuilder.append(",T1.NGAY_NHAN_GUI_PTK_HSHC ngayNhanGuiPtkHshc ");
		stringBuilder.append(",T1.TINH_TRANG_CHUYEN_UNG_TU tinhTrangChuyenUngTu ");
		stringBuilder.append(",T1.NGAY_TUONG_UNG_TTCUT ngayTuongUngTtcut ");
		stringBuilder.append(",T1.GHI_CHU_HS_LOI ghiChuHsLoi ");
		stringBuilder.append(",T1.GT_PTK_TD_XONG_QTK gtPtkTdXongQtk ");
		stringBuilder.append(",T1.NGAY_PTK_TD_XONG_QTK ngayPtkTdXongQtk ");
		stringBuilder.append(",T1.GT_DN_QT_CDT gtDnQtCdt ");
		stringBuilder.append(",T1.GT_CHOT_QT_CDT gtChotQtCdt ");
		stringBuilder.append(",T1.NGAY_GT_CHOT_QT_CDT ngayGtChotQtCdt ");
		stringBuilder.append(",T1.GHI_CHU_DN ghiChuDn ");
		stringBuilder.append(",T1.THANG_QTK thangQtk ");
		stringBuilder.append(",T1.THANG_QTK_CDT thangQtkCdt ");
		stringBuilder.append(",T1.TY_LE tyLe ");
		stringBuilder.append(",T1.HS_TON_QUA_35N hsTonQua35n ");
		stringBuilder.append(",T1.GT_QH_CHUA_XL gtQhChuaXl ");
		stringBuilder.append(",T1.LUU_TAI_KHO luuTaiKho ");
		stringBuilder.append(",T1.NGUOI_DUYET_PTK nguoiDuyetPtk ");
		stringBuilder.append(",T1.NGUOI_DUYET_CDT nguoiDuyetCdt ");
		stringBuilder.append(",T1.BAN_THAM_DINH_1 banThamDinh1 ");
		stringBuilder.append(",T1.BAN_THAM_DINH_2 banThamDinh2,T1.NGUOI_TAO nguoiTao,T1.NGAY_CAP_NHAT ngayCapNhat ");
		stringBuilder.append(",T1.FILE_QTK fileQtk,T1.FILE_QT_DOI_TAC fileQtDt ");
		stringBuilder.append(" FROM QL_CV_PTK T1 where 1=1  ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder.append(" AND (upper(T1.TINH) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.CNKV) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.SO_HD) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MA_TRAM_TUYEN) LIKE upper(:keySearch) escape '&')");
		}

		if (StringUtils.isNotEmpty(obj.getCnkv())) {
			stringBuilder.append(" and  upper(T1.CNKV) like upper(:cnkv)");
		}

		if (StringUtils.isNotEmpty(obj.getTinh())) {
			stringBuilder.append(" and  upper(T1.Tinh) like upper(:tinh)");
		}

		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			stringBuilder.append(" and  upper(T1.SO_HD) like upper(:soHd)");
		}

		if (StringUtils.isNotEmpty(obj.getMaTramTuyen())) {
			stringBuilder.append(" and  upper(T1.MA_TRAM_TUYEN) like upper(:maTramTuyen)");
		}

		if (StringUtils.isNotEmpty(obj.getLoaiCt())) {
			stringBuilder.append(" and  upper(T1.LOAI_CT) like upper(:loaiCt)");
		}
		if (StringUtils.isNotEmpty(obj.getSoBill())) {
			stringBuilder.append(" and  upper(T1.SO_BILL) like upper(:soBill)");
		}
		if (StringUtils.isNotEmpty(obj.getTinhTrangChuyenUngTu())) {
			stringBuilder.append(" and  upper(T1.TINH_TRANG_CHUYEN_UNG_TU) like upper(:tinhTrangChuyenUngTu)");
		}
		if (StringUtils.isNotEmpty(obj.getNguoiDuyetPtk())) {
			stringBuilder.append(" and  upper(T1.NGUOI_DUYET_PTK) like upper(:nguoiDuyetPtk)");
		}
		if (null != obj.getNgayGuiHshcFrom() && obj.getNgayGuiHshcTo() == null) {
			stringBuilder.append(" AND T1.NGAY_GUI_HSHC >= :ngayGuiHshcFrom ");
		}
		if (null != obj.getNgayGuiHshcTo() && null != obj.getNgayGuiHshcFrom()) {
			stringBuilder.append(" AND (T1.NGAY_GUI_HSHC between :ngayGuiHshcFrom and :ngayGuiHshcTo) ");
		}

		if (null != obj.getNgayDnQtkFrom() && obj.getNgayDnQtkTo() == null) {
			stringBuilder.append("AND T1.NGAY_DN_QTK >= :ngayDnQtkFrom ");
		}
		if (null != obj.getNgayDnQtkTo() && null != obj.getNgayDnQtkFrom()) {
			stringBuilder.append(" AND (T1.NGAY_DN_QTK between :ngayDnQtkFrom and :ngayDnQtkTo) ");
		}

		stringBuilder.append(" order by T1.QL_CV_PTK_ID desc");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("qlCvPtkId", new LongType());
		query.addScalar("cnkv", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("maTramTuyen", new StringType());
		query.addScalar("soHd", new StringType());
		query.addScalar("thuocDmTt", new StringType());
		query.addScalar("loaiCt", new StringType());
		query.addScalar("noiDung", new StringType());
		query.addScalar("ngayGuiHshc", new DateType());
		query.addScalar("soBill", new StringType());
		query.addScalar("ghiChu", new StringType());
		query.addScalar("gtSlHtTinh", new FloatType());
		query.addScalar("gtDnQtkCn", new FloatType());
		query.addScalar("ngayDnQtk", new DateType());
		query.addScalar("ngayNhanGuiPtkHshc", new DateType());
		query.addScalar("tinhTrangChuyenUngTu", new StringType());
		query.addScalar("ngayTuongUngTtcut", new DateType());
		query.addScalar("ghiChuHsLoi", new StringType());
		query.addScalar("gtPtkTdXongQtk", new FloatType());
		query.addScalar("ngayPtkTdXongQtk", new DateType());
		query.addScalar("gtDnQtCdt", new FloatType());
		query.addScalar("gtChotQtCdt", new FloatType());
		query.addScalar("ngayGtChotQtCdt", new DateType());
		query.addScalar("ghiChuDn", new StringType());
		query.addScalar("thangQtk", new StringType());
		query.addScalar("thangQtkCdt", new StringType());
		query.addScalar("tyLe", new FloatType());
		query.addScalar("hsTonQua35n", new StringType());
		query.addScalar("gtQhChuaXl", new StringType());
		query.addScalar("luuTaiKho", new StringType());
		query.addScalar("nguoiDuyetPtk", new StringType());
		query.addScalar("nguoiDuyetCdt", new StringType());
		query.addScalar("banThamDinh1", new StringType());
		query.addScalar("banThamDinh2", new StringType());
		query.addScalar("nguoiTao", new StringType());
		query.addScalar("ngayCapNhat", new DateType());
		query.addScalar("fileQtk", new StringType());
		query.addScalar("fileQtDt", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(QlCvPtkDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getCnkv())) {
			query.setParameter("cnkv", "%" + ValidateUtils.validateKeySearch(obj.getCnkv()) + "%");
			queryCount.setParameter("cnkv", "%" + ValidateUtils.validateKeySearch(obj.getCnkv()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			queryCount.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			query.setParameter("soHd", "%" + ValidateUtils.validateKeySearch(obj.getSoHd()) + "%");
			queryCount.setParameter("soHd", "%" + ValidateUtils.validateKeySearch(obj.getSoHd()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getMaTramTuyen())) {
			query.setParameter("maTramTuyen", "%" + ValidateUtils.validateKeySearch(obj.getMaTramTuyen()) + "%");
			queryCount.setParameter("maTramTuyen", "%" + ValidateUtils.validateKeySearch(obj.getMaTramTuyen()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getLoaiCt())) {
			query.setParameter("loaiCt", "%" + ValidateUtils.validateKeySearch(obj.getLoaiCt()) + "%");
			queryCount.setParameter("loaiCt", "%" + ValidateUtils.validateKeySearch(obj.getLoaiCt()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getSoBill())) {
			query.setParameter("soBill", "%" + ValidateUtils.validateKeySearch(obj.getSoBill()) + "%");
			queryCount.setParameter("soBill", "%" + ValidateUtils.validateKeySearch(obj.getSoBill()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTinhTrangChuyenUngTu())) {
			query.setParameter("tinhTrangChuyenUngTu",
					"%" + ValidateUtils.validateKeySearch(obj.getTinhTrangChuyenUngTu()) + "%");
			queryCount.setParameter("tinhTrangChuyenUngTu",
					"%" + ValidateUtils.validateKeySearch(obj.getTinhTrangChuyenUngTu()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getNguoiDuyetPtk())) {
			query.setParameter("nguoiDuyetPtk", "%" + ValidateUtils.validateKeySearch(obj.getNguoiDuyetPtk()) + "%");
			queryCount.setParameter("nguoiDuyetPtk",
					"%" + ValidateUtils.validateKeySearch(obj.getNguoiDuyetPtk()) + "%");
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

		if (null != obj.getNgayDnQtkFrom() && obj.getNgayDnQtkTo() == null) {
			query.setTimestamp("ngayDnQtkFrom", obj.getNgayDnQtkFrom());
			queryCount.setTimestamp("ngayDnQtkFrom", obj.getNgayDnQtkFrom());
		}
		if (null != obj.getNgayDnQtkTo() && null != obj.getNgayDnQtkFrom()) {
			query.setTimestamp("ngayDnQtkFrom", obj.getNgayDnQtkFrom());
			queryCount.setTimestamp("ngayDnQtkFrom", obj.getNgayDnQtkFrom());

			query.setTimestamp("ngayDnQtkTo", obj.getNgayDnQtkTo());
			queryCount.setTimestamp("ngayDnQtkTo", obj.getNgayDnQtkTo());
		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<QlCvPtkDTO> getForAutoCompleteSHD(QlCvPtkDTO obj) {
		String sql = "SELECT distinct " + "T1.SO_HD soHd " + " FROM QL_CV_PTK T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			stringBuilder.append(" AND (upper(T1.SO_HD) LIKE upper(:soHd) escape '&' )");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("soHd", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(QlCvPtkDTO.class));

		if (StringUtils.isNotEmpty(obj.getSoHd())) {
			query.setParameter("soHd", "%" + ValidateUtils.validateKeySearch(obj.getSoHd()) + "%");
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<QlCvPtkDTO> getDistSoHd() {
		String sql = "SELECT distinct " + "T1.SO_HD soHd " + " FROM QL_CV_PTK T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("soHd", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(QlCvPtkDTO.class));

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<QlCvPtkDTO> getTinhTrang(QlCvPtkDTO obj) {
		StringBuilder stringBuilder = new StringBuilder(" select "
				+ " T1.QL_CV_PTK_ID qlCvPtkId ,"
				+ " T1.CNKV cnkv ,"
				+ " T1.MA_TRAM_TUYEN maTramTuyen ,"
				+ " T1.SO_HD soHd ,"
				+ " T1.GT_SL_HT_TINH gtSlHtTinh ,"
				+ " T1.NGAY_NHAN_GUI_PTK_HSHC ngayNhanGuiPtkHshc ,"
				+ " T1.TINH_TRANG_CHUYEN_UNG_TU tinhTrangChuyenUngTu ,"
				+ " T1.NGAY_TUONG_UNG_TTCUT ngayTuongUngTtcut ,"
				+ " T1.GHI_CHU_HS_LOI ghiChuHsLoi ,"
				+ " T1.GT_PTK_TD_XONG_QTK gtPtkTdXongQtk ,"
				+ " T1.GT_CHOT_QT_CDT gtChotQtCdt, "
				+ " T1.GT_DN_QT_CDT gtDnQtCdt,T1.NGUOI_DUYET_PTK nguoiDuyetPtk "
				+ " FROM QL_CV_PTK T1 where T1.TINH_TRANG_CHUYEN_UNG_TU in (:listTinhTrangChuyenUngTu)");


		stringBuilder.append(" order by T1.TINH_TRANG_CHUYEN_UNG_TU ");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("qlCvPtkId", new LongType());
		query.addScalar("cnkv", new StringType());
		query.addScalar("maTramTuyen", new StringType());
		query.addScalar("soHd", new StringType());
		query.addScalar("gtSlHtTinh", new FloatType());
		query.addScalar("ngayNhanGuiPtkHshc", new DateType());
		query.addScalar("tinhTrangChuyenUngTu", new StringType());
		query.addScalar("ngayTuongUngTtcut", new DateType());
		query.addScalar("ghiChuHsLoi", new StringType());
		query.addScalar("gtPtkTdXongQtk", new FloatType());
		query.addScalar("gtDnQtCdt", new FloatType());
		query.addScalar("gtChotQtCdt", new FloatType());
		query.addScalar("nguoiDuyetPtk", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(QlCvPtkDTO.class));

		query.setParameterList("listTinhTrangChuyenUngTu", obj.getListTinhTrangChuyenUngTu());
		
		return query.list();
	}
	
	public Long updatePathFileScan(QlCvPtkDTO obj) {
		try {

			String sql2 = "update QL_CV_PTK set BAN_THAM_DINH_1=:banThamDinh1 where QL_CV_PTK_ID =:qlCvPtkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("banThamDinh1", obj.getBanThamDinh1());
			query2.setParameter("qlCvPtkId", obj.getQlCvPtkId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	public Long updatePathFileExcell(QlCvPtkDTO obj) {
		try {

			String sql2 = "update QL_CV_PTK set BAN_THAM_DINH_2=:banThamDinh2 where QL_CV_PTK_ID =:qlCvPtkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("banThamDinh2", obj.getBanThamDinh2());
			query2.setParameter("qlCvPtkId", obj.getQlCvPtkId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	public Long updatePathFileQtk(QlCvPtkDTO obj){
		try {

			String sql = "update QL_CV_PTK set FILE_QTK=:fileQtk where QL_CV_PTK_ID =:qlCvPtkId ";
			SQLQuery query = getSession().createSQLQuery(sql);
			query.setParameter("fileQtk", obj.getFileQtk());
			query.setParameter("qlCvPtkId", obj.getQlCvPtkId());
			query.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	public Long updatePathFileQtDt(QlCvPtkDTO obj){
		try {

			String sql = "update QL_CV_PTK set FILE_QT_DOI_TAC=:fileQtDt where QL_CV_PTK_ID =:qlCvPtkId ";
			SQLQuery query = getSession().createSQLQuery(sql);
			query.setParameter("fileQtDt", obj.getFileQtDt());
			query.setParameter("qlCvPtkId", obj.getQlCvPtkId());
			query.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	public Long deleteList(List<Long> obj) {
		try {

			String sql2 = "delete from QL_CV_PTK  where QL_CV_PTK_ID in (:qlCvPtkId) ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameterList("qlCvPtkId", obj);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
}
