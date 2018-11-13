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

import com.viettel.qll.bo.TblQlCvPtkBO;

import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import com.viettel.wms.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblQlCvPtkDAO")
public class TblQlCvPtkDAO extends BaseFWDAOImpl<TblQlCvPtkBO, Long> {

    public TblQlCvPtkDAO() {
        this.model = new TblQlCvPtkBO();
    }

    public TblQlCvPtkDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TblQlCvPtkDTO> doSearch(TblQlCvPtkDTO obj) {
    	
    	
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append("T1.TBL_QL_CV_PTK_ID tblQlCvPtkId ");
		stringBuilder.append(",T1.CNKV cnkv ");
		stringBuilder.append(",T1.TINH tinh ");
		stringBuilder.append(",T1.MTT_MA_VI_TRI mttMaViTri ");
		stringBuilder.append(",T1.MTT_MA_2G mttMa2g ");
		stringBuilder.append(",T1.MTT_MA_3G mttMa3g ");
		stringBuilder.append(",T1.MTT_MA_XUAT_KHO mttMaXuatKho ");
		stringBuilder.append(",T1.SO_HD_CDT soHdCdt ");
		stringBuilder.append(",T1.LOAI_CT loaiCt ");
		stringBuilder.append(",T1.NOI_DUNG noiDung ");
		stringBuilder.append(",T1.NGAY_GUI_HSHC ngayGuiHshc ");
		stringBuilder.append(",T1.SO_BILL soBill ");
		stringBuilder.append(",T1.NGAY_NHAN_HSHC ngayNhanHshc ");
		stringBuilder.append(",T1.GHI_CHU ghiChu ");
		stringBuilder.append(",T1.GT_SL_HT_TT_TONG gtSlHtTtTong ");
		stringBuilder.append(",T1.GT_SL_HT_TT_XD gtSlHtTtXd ");
		stringBuilder.append(",T1.GT_SL_HT_TT_DIEN gtSlHtTtDien ");
		stringBuilder.append(",T1.GT_SL_HT_TT_LAP_DUNG gtSlHtTtLapDung ");
		stringBuilder.append(",T1.GT_SL_HT_TT_LAP_BTS gtSlHtTtLapBts ");
		stringBuilder.append(",T1.GT_SL_HT_TT_KHAC gtSlHtTtKhac ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_NGAY gtDnQtkCnNgay ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_TONG gtDnQtkCnTong ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_XD gtDnQtkCnXd ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_DIEN gtDnQtkCnDien ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_LAP_DUNG gtDnQtkCnLapDung ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_LAP_BTS gtDnQtkCnLapBts ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_KHAC gtDnQtkCnKhac ");
		stringBuilder.append(",T1.GT_DN_QTK_CN_NGUOI_LAP gtDnQtkCnNguoiLap ");
		stringBuilder.append(",T1.GT_QTK_PTK_NGAY gtQtkPtkNgay ");
		stringBuilder.append(",T1.GT_QTK_PTK_TONG gtQtkPtkTong ");
		stringBuilder.append(",T1.GT_QTK_PTK_XD gtQtkPtkXd ");
		stringBuilder.append(",T1.GT_QTK_PTK_DIEN gtQtkPtkDien ");
		stringBuilder.append(",T1.GT_QTK_PTK_LAP_DUNG gtQtkPtkLapDung ");
		stringBuilder.append(",T1.GT_QTK_PTK_LAP_BTS gtQtkPtkLapBts ");
		stringBuilder.append(",T1.GT_QTK_PTK_KHAC gtQtkPtkKhac ");
		stringBuilder.append(",T1.GT_QTK_PTK_NGUOI_CHOT gtQtkPtkNguoiChot ");
		stringBuilder.append(",T1.GT_QTK_PTK_DMTT gtQtkPtkDmtt ");
		stringBuilder.append(",T1.GT_QTK_PTK_THANG_QTK gtQtkPtkThangQtk ");
		stringBuilder.append(",T1.TINH_TRANG_CCT1 tinhTrangCct1 ");
		stringBuilder.append(",T1.GHI_CHU_HS_LOI ghiChuHsLoi ");
		stringBuilder.append(",T1.QT_DT_NGAY qtDtNgay ");
		stringBuilder.append(",T1.QT_DT_GT_DN_DT qtDtGtDnDt ");
		stringBuilder.append(",T1.QT_DT_SO_HD qtDtSoHd ");
		stringBuilder.append(",T1.QT_DT_NGAY_TD_DT qtDtNgayTdDt ");
		stringBuilder.append(",T1.QT_DT_TONG qtDtTong ");
		stringBuilder.append(",T1.QT_DT_XD qtDtXd ");
		stringBuilder.append(",T1.QT_DT_DIEN qtDtDien ");
		stringBuilder.append(",T1.QT_DT_LAP_DUNG qtDtLapDung ");
		stringBuilder.append(",T1.QT_DT_LAP_BTS qtDtLapBts ");
		stringBuilder.append(",T1.QT_DT_KHAC qtDtKhac ");
		stringBuilder.append(",T1.QT_DT_TT_DT qtDtTtDt ");
		stringBuilder.append(",T1.QT_DT_NGUOI_TD qtDtNguoiTd ");
		stringBuilder.append(",T1.DN_QT_CDT_NGAY dnQtCdtNgay ");
		stringBuilder.append(",T1.DN_QT_CDT_GT dnQtCdtGt ");
		stringBuilder.append(",T1.DN_QT_CDT_NGUOI_LAP dnQtCdtNguoiLap ");
		stringBuilder.append(",T1.DN_QT_CDT_NGUOI_NHAN_BG dnQtCdtNguoiNhanBg ");
		stringBuilder.append(",T1.DN_QT_CDT_NOI_DUNG_PS_CTK dnQtCdtNoiDungPsCtk ");
		stringBuilder.append(",T1.DN_QT_CDT_KTK dnQtCdtKtk ");
		stringBuilder.append(",T1.TD_QT_CDT_NGAY_CHOT tdQtCdtNgayChot ");
		stringBuilder.append(",T1.TD_QT_CDT_GT tdQtCdtGt ");
		stringBuilder.append(",T1.TD_QT_CDT_NGUOI_CHOT_MVT tdQtCdtNguoiChotMvt ");
		stringBuilder.append(",T1.TD_QT_CDT_NGUOI_TD_CDT_MVT tdQtCdtNguoiTdCdtMvt ");
		stringBuilder.append(",T1.TD_QT_CDT_NGAY_BAN_TD tdQtCdtNgayBanTd ");
		stringBuilder.append(",T1.TD_QT_CDT_NGAY_CTC tdQtCdtNgayCtc ");
		stringBuilder.append(",T1.TD_QT_CDT_NOI_DUNG tdQtCdtNoiDung ");
		stringBuilder.append(",T1.TINH_TRANG_CCT2 tinhTrangCct2 ");
		stringBuilder.append(",T1.GHI_CHU_2 ghiChu2 ");
		stringBuilder.append(",T1.LUU_TAI_KHO luuTaiKho,T1.TRANG_THAI trangThai, T1.PATH_FILE pathFile ");
    	
    	
		stringBuilder.append(" FROM TBL_QL_CV_PTK T1  where 1=1 ");
		
    	
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder.append(" AND (upper(T1.TINH) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.CNKV) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MTT_MA_VI_TRI) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MTT_MA_2G) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MTT_MA_3G) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MTT_MA_XUAT_KHO) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.SO_HD_CDT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.LOAI_CT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.NOI_DUNG) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.SO_BILL) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.GHI_CHU) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.GT_DN_QTK_CN_NGUOI_LAP) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.GT_QTK_PTK_NGUOI_CHOT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.GT_QTK_PTK_DMTT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.GT_QTK_PTK_THANG_QTK) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.TINH_TRANG_CCT1) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.GHI_CHU_HS_LOI) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.QT_DT_SO_HD) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.QT_DT_TT_DT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.QT_DT_NGUOI_TD) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.DN_QT_CDT_NGUOI_LAP) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.DN_QT_CDT_NGUOI_NHAN_BG) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.DN_QT_CDT_NOI_DUNG_PS_CTK) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.DN_QT_CDT_KTK) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.TD_QT_CDT_NGUOI_CHOT_MVT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.TD_QT_CDT_NGUOI_TD_CDT_MVT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.TD_QT_CDT_NOI_DUNG) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.TINH_TRANG_CCT2) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.GHI_CHU_2) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.LUU_TAI_KHO) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.TRANG_THAI) LIKE upper(:keySearch) escape '&')"
				

					);
		}
		if (null != obj.getTblQlCvPtkId()) {
			stringBuilder.append("AND T1.TBL_QL_CV_PTK_ID=:tblQlCvPtkId ");
		}
		if (StringUtils.isNotEmpty(obj.getCnkv())) {
			stringBuilder.append("AND (UPPER(T1.CNKV) LIKE UPPER(:cnkv)  ESCAPE '&')");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			stringBuilder.append("AND (UPPER(T1.TINH) LIKE UPPER(:tinh)  ESCAPE '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMttMaViTri())) {
			stringBuilder.append("AND (UPPER(T1.MTT_MA_VI_TRI) LIKE UPPER(:mttMaViTri)  ESCAPE '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMttMa2g())) {
			stringBuilder.append("AND (UPPER(T1.MTT_MA_2G) LIKE UPPER(:mttMa2g)  ESCAPE '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMttMa3g())) {
			stringBuilder.append("AND (UPPER(T1.MTT_MA_3G) LIKE UPPER(:mttMa3g)   ESCAPE '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMttMaXuatKho())) {
			stringBuilder.append("AND (UPPER(T1.MTT_MA_XUAT_KHO) LIKE UPPER(:mttMaXuatKho)   ESCAPE '&')");
		}
		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
			stringBuilder.append("AND (UPPER(T1.SO_HD_CDT) LIKE UPPER(:soHdCdt)   ESCAPE '&')");
		}
		if (StringUtils.isNotEmpty(obj.getLoaiCt())) {
			stringBuilder.append("AND (UPPER(T1.LOAI_CT) LIKE UPPER(:loaiCt)  ESCAPE '&')");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			stringBuilder.append("AND T1.NGAY_NHAN_HSHC >= :ngayNhanHshcFrom ");
		}
		if (null != obj.getNgayNhanHshcTo() && null != obj.getNgayNhanHshcFrom()) {
			stringBuilder.append(" AND ( T1.NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo ) ");
		}
	
		if (StringUtils.isNotEmpty(obj.getSoBill())) {
			stringBuilder.append("AND (UPPER(T1.SO_BILL) LIKE UPPER(:soBill)  ESCAPE '&') ");
		}
		
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			stringBuilder.append("AND (UPPER(T1.TRANG_THAI) LIKE UPPER(:trangThai)   ESCAPE '&')");
		}
		stringBuilder.append(" order by T1.TBL_QL_CV_PTK_ID desc");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
			query.addScalar("tblQlCvPtkId", new LongType());
			query.addScalar("cnkv", new StringType());
			query.addScalar("tinh", new StringType());
			query.addScalar("mttMaViTri", new StringType());
			query.addScalar("mttMa2g", new StringType());
			query.addScalar("mttMa3g", new StringType());
			query.addScalar("mttMaXuatKho", new StringType());
			query.addScalar("soHdCdt", new StringType());
			query.addScalar("loaiCt", new StringType());
			query.addScalar("noiDung", new StringType());
			query.addScalar("ngayGuiHshc", new DateType());
			query.addScalar("soBill", new StringType());
			query.addScalar("ngayNhanHshc", new DateType());
			query.addScalar("ghiChu", new StringType());
			query.addScalar("gtSlHtTtTong", new FloatType());
			query.addScalar("gtSlHtTtXd", new FloatType());
			query.addScalar("gtSlHtTtDien", new FloatType());
			query.addScalar("gtSlHtTtLapDung", new FloatType());
			query.addScalar("gtSlHtTtLapBts", new FloatType());
			query.addScalar("gtSlHtTtKhac", new FloatType());
			query.addScalar("gtDnQtkCnNgay", new DateType());
			query.addScalar("gtDnQtkCnTong", new FloatType());
			query.addScalar("gtDnQtkCnXd", new FloatType());
			query.addScalar("gtDnQtkCnDien", new FloatType());
			query.addScalar("gtDnQtkCnLapDung", new FloatType());
			query.addScalar("gtDnQtkCnLapBts", new FloatType());
			query.addScalar("gtDnQtkCnKhac", new FloatType());
			query.addScalar("gtDnQtkCnNguoiLap", new StringType());
			query.addScalar("gtQtkPtkNgay", new DateType());
			query.addScalar("gtQtkPtkTong", new FloatType());
			query.addScalar("gtQtkPtkXd", new FloatType());
			query.addScalar("gtQtkPtkDien", new FloatType());
			query.addScalar("gtQtkPtkLapDung", new FloatType());
			query.addScalar("gtQtkPtkLapBts", new FloatType());
			query.addScalar("gtQtkPtkKhac", new FloatType());
			query.addScalar("gtQtkPtkNguoiChot", new StringType());
			query.addScalar("gtQtkPtkDmtt", new StringType());
			query.addScalar("gtQtkPtkThangQtk", new StringType());
			query.addScalar("tinhTrangCct1", new StringType());
			query.addScalar("ghiChuHsLoi", new StringType());
			query.addScalar("qtDtNgay", new DateType());
			query.addScalar("qtDtGtDnDt", new FloatType());
			query.addScalar("qtDtSoHd", new StringType());
			query.addScalar("qtDtNgayTdDt", new DateType());
			query.addScalar("qtDtTong", new FloatType());
			query.addScalar("qtDtXd", new FloatType());
			query.addScalar("qtDtDien", new FloatType());
			query.addScalar("qtDtLapDung", new FloatType());
			query.addScalar("qtDtLapBts", new FloatType());
			query.addScalar("qtDtKhac", new FloatType());
			query.addScalar("qtDtTtDt", new StringType());
			query.addScalar("qtDtNguoiTd", new StringType());
			query.addScalar("dnQtCdtNgay", new DateType());
			query.addScalar("dnQtCdtGt", new FloatType());
			query.addScalar("dnQtCdtNguoiLap", new StringType());
			query.addScalar("dnQtCdtNguoiNhanBg", new StringType());
			query.addScalar("dnQtCdtNoiDungPsCtk", new StringType());
			query.addScalar("dnQtCdtKtk", new StringType());
			query.addScalar("tdQtCdtNgayChot", new DateType());
			query.addScalar("tdQtCdtGt", new FloatType());
			query.addScalar("tdQtCdtNguoiChotMvt", new StringType());
			query.addScalar("tdQtCdtNguoiTdCdtMvt", new StringType());
			query.addScalar("tdQtCdtNgayBanTd", new DateType());
			query.addScalar("tdQtCdtNgayCtc", new DateType());
			query.addScalar("tdQtCdtNoiDung", new StringType());
			query.addScalar("tinhTrangCct2", new StringType());
			query.addScalar("ghiChu2", new StringType());
			query.addScalar("luuTaiKho", new StringType());
			query.addScalar("trangThai", new StringType());
			query.addScalar("pathFile", new StringType());
			
		
    	
			query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
			if (StringUtils.isNotEmpty(obj.getKeySearch())) {
				query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
				queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			}
			
		if (null != obj.getTblQlCvPtkId()) {
			query.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
			queryCount.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
		}
		if (StringUtils.isNotEmpty(obj.getCnkv())) {
			query.setParameter("cnkv", "%" + ValidateUtils.validateKeySearch(obj.getCnkv()) + "%");
			queryCount.setParameter("cnkv", "%" + ValidateUtils.validateKeySearch(obj.getCnkv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			queryCount.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMttMaViTri())) {
			query.setParameter("mttMaViTri", "%" + ValidateUtils.validateKeySearch(obj.getMttMaViTri()) + "%");
			queryCount.setParameter("mttMaViTri", "%" + ValidateUtils.validateKeySearch(obj.getMttMaViTri()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMttMa2g())) {
			query.setParameter("mttMa2g", "%" + ValidateUtils.validateKeySearch(obj.getMttMa2g()) + "%");
			queryCount.setParameter("mttMa2g", "%" + ValidateUtils.validateKeySearch(obj.getMttMa2g()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMttMa3g())) {
			query.setParameter("mttMa3g", "%" + ValidateUtils.validateKeySearch(obj.getMttMa3g()) + "%");
			queryCount.setParameter("mttMa3g", "%" + ValidateUtils.validateKeySearch(obj.getMttMa3g()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMttMaXuatKho())) {
			query.setParameter("mttMaXuatKho", "%" +  ValidateUtils.validateKeySearch(obj.getMttMaXuatKho()) + "%");
			queryCount.setParameter("mttMaXuatKho", "%" + ValidateUtils.validateKeySearch(obj.getMttMaXuatKho()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
			query.setParameter("soHdCdt", "%" + ValidateUtils.validateKeySearch(obj.getSoHdCdt()) + "%");
			queryCount.setParameter("soHdCdt", "%" + ValidateUtils.validateKeySearch(obj.getSoHdCdt()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getLoaiCt())) {
			query.setParameter("loaiCt", "%" + ValidateUtils.validateKeySearch(obj.getLoaiCt()) + "%");
			queryCount.setParameter("loaiCt", "%" + ValidateUtils.validateKeySearch(obj.getLoaiCt()) + "%");
		}

		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
			queryCount.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
		}
		if (null != obj.getNgayNhanHshcTo() && null != obj.getNgayNhanHshcFrom()) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
			queryCount.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());

			query.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
			queryCount.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
		}
		if (StringUtils.isNotEmpty(obj.getSoBill())) {
			query.setParameter("soBill", "%" + ValidateUtils.validateKeySearch(obj.getSoBill()) + "%");
			queryCount.setParameter("soBill", "%" + ValidateUtils.validateKeySearch(obj.getSoBill()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			query.setParameter("trangThai", "%" + ValidateUtils.validateKeySearch(obj.getTrangThai()) + "%");
			queryCount.setParameter("trangThai", "%" + ValidateUtils.validateKeySearch(obj.getTrangThai()) + "%");
		}
		
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}  
    public Long deleteList(List<Long> obj) {
		try {

			String sql2 = "delete from TBL_QL_CV_PTK  where TBL_QL_CV_PTK_ID in (:tblQlCvPtkId) ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameterList("tblQlCvPtkId", obj);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    public Long updatePath(TblQlCvPtkDTO obj) {
		try {

			String sql2 = "update TBL_QL_CV_PTK set PATH_FILE=:pathFile where TBL_QL_CV_PTK_ID =:tblQlCvPtkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("pathFile", obj.getPathFile());
			query2.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    public Long updateStatus(TblQlCvPtkDTO obj) {
		try {

			String sql2 = "update TBL_QL_CV_PTK set TRANG_THAI=:trangThai where TBL_QL_CV_PTK_ID =:tblQlCvPtkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("trangThai", obj.getTrangThai());
			query2.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    //tự động số hợp đông cdt
    @SuppressWarnings("unchecked")
	public List<TblQlCvPtkDTO> getForAutoCompleteSHD(TblQlCvPtkDTO obj) {
		String sql = " SELECT distinct T1.SO_HD_CDT soHdCdt  FROM TBL_QL_CV_PTK T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		
		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
			stringBuilder.append(" AND (upper(T1.SO_HD_CDT) LIKE upper(:soHdCdt) escape '&' ) ");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("soHdCdt", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));

		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
			query.setParameter("soHdCdt", "%" + ValidateUtils.validateKeySearch(obj.getSoHdCdt()) + "%");
		}

		return query.list();
	}
    @SuppressWarnings("unchecked")
 	public List<TblQlCvPtkDTO> getAutoMaViTriForShd(TblQlCvPtkDTO obj) {
 		String sql = " SELECT distinct T1.MTT_MA_VI_TRI mttMaViTri  FROM TBL_QL_CV_PTK T1 where 1=1 ";

 		StringBuilder stringBuilder = new StringBuilder(sql);
 		if (StringUtils.isNotEmpty(obj.getMttMaViTri())) {
			stringBuilder.append(" AND (upper(T1.MTT_MA_VI_TRI) LIKE upper(:mttMaViTri) escape '&' ) ");
		}
 		
 		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
 			stringBuilder.append(" AND T1.SO_HD_CDT=:soHdCdt ");
 		}
 		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
 		query.addScalar("mttMaViTri", new StringType());
 		query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
 		
 		if (StringUtils.isNotEmpty(obj.getMttMaViTri())) {
			query.setParameter("mttMaViTri", "%" + ValidateUtils.validateKeySearch(obj.getMttMaViTri()) + "%");
		}
 		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
 			query.setParameter("soHdCdt",obj.getSoHdCdt());
 		}
// 		if (obj.getPage() != null && obj.getPageSize() != null) {
//			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//			query.setMaxResults(obj.getPageSize().intValue());
//		}
 		return query.list();
 	}
    // lay ma hop dong
    @SuppressWarnings("unchecked")
	public List<TblQlCvPtkDTO> getForSoHdCdt(TblQlCvPtkDTO obj) {
		

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(" SELECT  SO_HD_CDT soHdCdt,( select  count(*) from ( select  SO_HD_CDT  from TBL_QL_CV_PTK  where SO_HD_CDT is  null ) ) soLuongHD  FROM TBL_QL_CV_PTK  where SO_HD_CDT is null ");
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			stringBuilder.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			stringBuilder.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		stringBuilder.append(" UNION all ");
		stringBuilder.append(" SELECT SO_HD_CDT soHdCdt,count(SO_HD_CDT) soLuongHD FROM TBL_QL_CV_PTK  where SO_HD_CDT is not  null  ");
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			stringBuilder.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			stringBuilder.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		stringBuilder.append(" group by SO_HD_CDT  ");
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("soHdCdt", new StringType());
		query.addScalar("soLuongHD", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
			query.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
		}
		
		
		
		return query.list();
	}
    @SuppressWarnings("unchecked")
	public List<TblQlCvPtkDTO> getForCNKV(TblQlCvPtkDTO obj){
    	StringBuilder sql = new StringBuilder("");
    	sql.append(" select DISTINCT CNKV cnkv from TBL_QL_CV_PTK where 1=1 "); 
    	if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
    		sql.append(" and SO_HD_CDT =:soHdCdt ");
    	}
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    		sql.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			sql.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("cnkv", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
		if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
			query.setParameter("soHdCdt", obj.getSoHdCdt());
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
			query.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
		}
		
		
		
		return query.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<TblQlCvPtkDTO> reportChart(TblQlCvPtkDTO obj){
    	StringBuilder sql = new StringBuilder("");
    	sql.append(" select NVL(T1.soLuongGtHt,0)soLuongGtHt,NVL(T1.soLuongDnCdt,0)soLuongDnCdt,NVL(T1.soLuongTdCdt,0)soLuongTdCdt,NVL(T1.sumGtHt,0)sumGtHt,NVL(T1.sumDnCdt,0)sumDnCdt,NVL(T1.sumTdCdt,0)sumTdCdt,T1.cnkv,NVL(T2.gtHtWithDnCdt,0)gtHtWithDnCdt,NVL(T3.gtHtWithTdCdt,0)gtHtWithTdCdt from  (select " );
    	sql.append(" count(case GT_SL_HT_TT_TONG when 0 then null else GT_SL_HT_TT_TONG end) soLuongGtHt, ");
    	sql.append(" count(case DN_QT_CDT_GT when 0 then null else DN_QT_CDT_GT end) soLuongDnCdt, ");
    	sql.append(" count(case TD_QT_CDT_GT when 0 then null else TD_QT_CDT_GT end) soLuongTdCdt, ");
    	sql.append(" sum(GT_SL_HT_TT_TONG) sumGtHt, ");
    	sql.append(" sum(DN_QT_CDT_GT) sumDnCdt, ");
    	sql.append(" sum(TD_QT_CDT_GT) sumTdCdt, CNKV cnkv from TBL_QL_CV_PTK where CNKV IN (:listCnkv) ");
    	
    	if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
    		sql.append(" and SO_HD_CDT =:soHdCdt ");
    	}
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    		sql.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			sql.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		sql.append(" group by CNKV ORDER BY CNKV ) T1  LEFT join ");
		sql.append("( select sum(GT_SL_HT_TT_TONG) gtHtWithDnCdt , cnkv  from TBL_QL_CV_PTK  where DN_QT_CDT_GT!=0 and CNKV IN (:listCnkv) ");
		
		if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
    		sql.append(" and SO_HD_CDT =:soHdCdt ");
    	}
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    		sql.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			sql.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		sql.append(" group by cnkv ORDER BY cnkv) T2   on T1.cnkv=T2.cnkv   LEFT join ");
		sql.append(" ( select sum(GT_SL_HT_TT_TONG) gtHtWithTdCdt , cnkv  from TBL_QL_CV_PTK  where TD_QT_CDT_GT!=0 and CNKV IN(:listCnkv) ");
		if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
    		sql.append(" and SO_HD_CDT =:soHdCdt ");
    	}
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    		sql.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			sql.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		sql.append(" group by cnkv ORDER BY cnkv) T3   on T1.cnkv=T3.cnkv  ");
		
		sql.append("  UNION all ");
		sql.append(" select NVL(soLuongGtHt,0)soLuongGtHt,NVL(soLuongDnCdt,0)soLuongDnCdt,NVL(soLuongTdCdt,0)soLuongTdCdt,NVL(sumGtHt,0)sumGtHt,NVL(sumDnCdt,0)sumDnCdt,NVL(sumTdCdt,0)sumTdCdt,cnkv,NVL(gtHtWithDnCdt,0)gtHtWithDnCdt,NVL(gtHtWithTdCdt,0)gtHtWithTdCdt from ");
		sql.append(" (select T1.soLuongGtHt,T1.soLuongDnCdt,T1.soLuongTdCdt,T1.sumGtHt,T1.sumDnCdt,T1.sumTdCdt, 'TỔNG' cnkv , ");
		sql.append(" (select sum(GT_SL_HT_TT_TONG)from TBL_QL_CV_PTK  where DN_QT_CDT_GT!=0 ");
		if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
    		sql.append(" and SO_HD_CDT =:soHdCdt ");
    	}
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    		sql.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			sql.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		sql.append(" ) gtHtWithDnCdt , ");
		sql.append(" (select sum(GT_SL_HT_TT_TONG)from TBL_QL_CV_PTK  where TD_QT_CDT_GT!=0 ");
		if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
    		sql.append(" and SO_HD_CDT =:soHdCdt ");
    	}
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    		sql.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			sql.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		sql.append(" ) gtHtWithTdCdt  ");
		sql.append(" from ( select count(case GT_SL_HT_TT_TONG when 0 then null else GT_SL_HT_TT_TONG end) soLuongGtHt, ");
		sql.append(" count(case DN_QT_CDT_GT when 0 then null else DN_QT_CDT_GT end) soLuongDnCdt, ");
		sql.append(" count(case TD_QT_CDT_GT when 0 then null else TD_QT_CDT_GT end) soLuongTdCdt, ");
		sql.append(" sum(GT_SL_HT_TT_TONG) sumGtHt, sum(DN_QT_CDT_GT) sumDnCdt, sum(TD_QT_CDT_GT) sumTdCdt ");
		sql.append(" from TBL_QL_CV_PTK where 1=1 ");
		if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
    		sql.append(" and SO_HD_CDT =:soHdCdt ");
    	}
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    		sql.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			sql.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		sql.append(" ) T1 )"); 
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("soLuongGtHt", new LongType());
		query.addScalar("soLuongDnCdt", new LongType());
		query.addScalar("soLuongTdCdt", new LongType());
		query.addScalar("sumGtHt", new FloatType());
		query.addScalar("sumDnCdt", new FloatType());
		query.addScalar("sumTdCdt", new FloatType());
		query.addScalar("gtHtWithDnCdt", new FloatType());
		query.addScalar("gtHtWithTdCdt", new FloatType());
		query.addScalar("cnkv", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
		query.setParameterList("listCnkv", obj.getListCnkv());
		if(StringUtils.isNotEmpty(obj.getSoHdCdt())){
			query.setParameter("soHdCdt", obj.getSoHdCdt());
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
			query.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
		}
		return query.list();
		
    }
    // bao cao tong hop
    @SuppressWarnings("unchecked")
    public List<TblQlCvPtkDTO> BCTongHop(TblQlCvPtkDTO obj){
    	StringBuilder stringBuilder = new StringBuilder(" Select MTT_MA_VI_TRI mttMaViTri, ");
    	stringBuilder.append(" GT_SL_HT_TT_TONG gtSlHtTtTong, ");
    	stringBuilder.append(" GT_DN_QTK_CN_TONG gtDnQtkCnTong, ");
    	stringBuilder.append(" GT_QTK_PTK_TONG gtQtkPtkTong, ");
    	stringBuilder.append(" DN_QT_CDT_GT dnQtCdtGt, ");
    	stringBuilder.append(" TD_QT_CDT_GT tdQtCdtGt, ");
    	stringBuilder.append(" QT_DT_TONG qtDtTong ");
    	stringBuilder.append(" from TBL_QL_CV_PTK where SO_HD_CDT =:soHdCdt ");
    	
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			stringBuilder.append(" AND (NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			stringBuilder.append(" AND (NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("mttMaViTri", new StringType());
		query.addScalar("gtSlHtTtTong", new FloatType());
		query.addScalar("gtDnQtkCnTong", new FloatType());
		query.addScalar("gtQtkPtkTong", new FloatType());
		query.addScalar("dnQtCdtGt", new FloatType());
		query.addScalar("tdQtCdtGt", new FloatType());
		query.addScalar("qtDtTong", new FloatType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
		query.setParameter("soHdCdt", obj.getSoHdCdt());
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
			query.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
		}
		return query.list();
    }
    //báo cáo QTK
    @SuppressWarnings("unchecked")
	public List<TblQlCvPtkDTO> reportForQTK(TblQlCvPtkDTO obj){
    	StringBuilder stringBuilder = new StringBuilder(" select cnkv,mttMaViTri,soHdCdt,gtSlHtTtTong,gtQtkPtkTong,dnQtCdtGt,tdQtCdtGt,ghiChuHsLoi,gtDnQtkCnNguoiLap,gtQtkPtkNguoiChot from (  ");
    	stringBuilder.append(" select cnkv,mttMaViTri,soHdCdt,gtSlHtTtTong,gtQtkPtkTong,dnQtCdtGt,tdQtCdtGt,ghiChuHsLoi,gtDnQtkCnNguoiLap,gtQtkPtkNguoiChot,type from ( select ");
    	stringBuilder.append(" T1.CNKV cnkv ");
    	stringBuilder.append(",T1.MTT_MA_VI_TRI mttMaViTri ");
    	stringBuilder.append(",T1.SO_HD_CDT soHdCdt ");
    	stringBuilder.append(",T1.GT_SL_HT_TT_TONG gtSlHtTtTong ");
    	stringBuilder.append(",T1.GT_QTK_PTK_TONG gtQtkPtkTong ");
    	stringBuilder.append(",T1.DN_QT_CDT_GT dnQtCdtGt ");
    	stringBuilder.append(",T1.TD_QT_CDT_GT tdQtCdtGt ");
    	stringBuilder.append(",T1.GHI_CHU_HS_LOI ghiChuHsLoi ");
    	stringBuilder.append(",T1.GT_DN_QTK_CN_NGUOI_LAP gtDnQtkCnNguoiLap ");
    	stringBuilder.append(",T1.GT_QTK_PTK_NGUOI_CHOT gtQtkPtkNguoiChot , 2 type ");
    	stringBuilder.append(" from TBL_QL_CV_PTK T1 where 1=1 ");
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			stringBuilder.append(" AND (T1.NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			stringBuilder.append(" AND (T1.NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		stringBuilder.append(" union all select ");
		stringBuilder.append(" T1.CNKV ");
    	stringBuilder.append(",to_char(count(T1.MTT_MA_VI_TRI)) mttMaViTri ");
    	stringBuilder.append(",to_char(count(T1.SO_HD_CDT)) soHdCdt ");
    	stringBuilder.append(",count(case T1.GT_SL_HT_TT_TONG when 0 then null else T1.GT_SL_HT_TT_TONG end) gtSlHtTtTong ");
    	stringBuilder.append(",count(case T1.GT_QTK_PTK_TONG when  0 then null else T1.GT_QTK_PTK_TONG end) gtQtkPtkTong ");
    	stringBuilder.append(",count(case T1.DN_QT_CDT_GT when 0 then null else T1.DN_QT_CDT_GT end) dnQtCdtGt ");
    	stringBuilder.append(",count(case T1.TD_QT_CDT_GT when 0 then null else T1.TD_QT_CDT_GT end) tdQtCdtGt ");
    	stringBuilder.append(",null ghiChuHsLoi ");
    	stringBuilder.append(",null gtDnQtkCnNguoiLap ");
    	stringBuilder.append(",null gtQtkPtkNguoiChot , 0 type ");
    	stringBuilder.append(" from TBL_QL_CV_PTK T1 where 1=1 ");
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			stringBuilder.append(" AND (T1.NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			stringBuilder.append(" AND (T1.NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		
		stringBuilder.append(" group by T1.CNKV )  ");
		stringBuilder.append(" union all select ");
		stringBuilder.append(" T1.CNKV cnkv ");
    	stringBuilder.append(",null mttMaViTri ");
    	stringBuilder.append(",null soHdCdt ");
    	stringBuilder.append(",sum(T1.GT_SL_HT_TT_TONG) gtSlHtTtTong ");
    	stringBuilder.append(",sum(T1.GT_QTK_PTK_TONG) gtQtkPtkTong ");
    	stringBuilder.append(",sum(T1.DN_QT_CDT_GT) dnQtCdtGt ");
    	stringBuilder.append(",sum(T1.TD_QT_CDT_GT) tdQtCdtGt ");
    	stringBuilder.append(",null ghiChuHsLoi ");
    	stringBuilder.append(",null gtDnQtkCnNguoiLap ");
    	stringBuilder.append(",null gtQtkPtkNguoiChot , 1 type ");
    	stringBuilder.append(" from TBL_QL_CV_PTK T1 where 1=1 ");
    	if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			stringBuilder.append(" AND (T1.NGAY_NHAN_HSHC >=:ngayNhanHshcFrom) ");
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			stringBuilder.append(" AND (T1.NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo) ");
		}
		stringBuilder.append(" group by T1.CNKV )  ORDER by cnkv,type ");
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("cnkv", new StringType());
		query.addScalar("mttMaViTri", new StringType());
		query.addScalar("soHdCdt", new StringType());
		query.addScalar("gtSlHtTtTong", new FloatType());
		query.addScalar("gtQtkPtkTong", new FloatType());
		query.addScalar("dnQtCdtGt", new FloatType());
		query.addScalar("tdQtCdtGt", new FloatType());
		query.addScalar("ghiChuHsLoi", new StringType());
		query.addScalar("gtDnQtkCnNguoiLap", new StringType());
		query.addScalar("gtQtkPtkNguoiChot", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
		
		
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
		}
		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() != null) {
			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
			query.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
		}
		return query.list();
    }
    
    
    public Long updateCV1(TblQlCvPtkDTO obj) {
		try {

			StringBuilder sql = new StringBuilder("update TBL_QL_CV_PTK set GT_SL_HT_TT_TONG=:gtSlHtTtTong");
			if(obj.getGtSlHtTtXd()!=null){
				sql.append(", GT_SL_HT_TT_XD=:gtSlHtTtXd ");
			}
			if(obj.getGtSlHtTtDien()!=null){
				sql.append(", GT_SL_HT_TT_DIEN=:gtSlHtTtDien");
			}
			if(obj.getGtSlHtTtLapDung()!=null){
				sql.append(", GT_SL_HT_TT_LAP_DUNG=:gtSlHtTtLapDung");
			}
			if(obj.getGtSlHtTtLapBts()!=null){
				sql.append(", GT_SL_HT_TT_LAP_BTS=:gtSlHtTtLapBts");
			}
			if(obj.getGtSlHtTtKhac()!=null){
				sql.append(", GT_SL_HT_TT_KHAC=:gtSlHtTtKhac");
			}
			sql.append(" where TBL_QL_CV_PTK_ID=:tblQlCvPtkId ");
		
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.setParameter("gtSlHtTtTong", obj.getGtSlHtTtTong());
			
			if(obj.getGtSlHtTtXd()!=null){
				query.setParameter("gtSlHtTtXd", obj.getGtSlHtTtXd());
			}
			if(obj.getGtSlHtTtDien()!=null){
				query.setParameter("gtSlHtTtDien", obj.getGtSlHtTtDien());
			}
			if(obj.getGtSlHtTtLapDung()!=null){
				query.setParameter("gtSlHtTtLapDung", obj.getGtSlHtTtLapDung());
			}
			if(obj.getGtSlHtTtLapBts()!=null){
				query.setParameter("gtSlHtTtLapBts", obj.getGtSlHtTtLapBts());
			}
			if(obj.getGtSlHtTtKhac()!=null){
				query.setParameter("gtSlHtTtKhac", obj.getGtSlHtTtKhac());
			} 			
			query.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());			
			query.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    public Long updateCV2(TblQlCvPtkDTO obj) {
		try {

			StringBuilder sql = new StringBuilder("update TBL_QL_CV_PTK set GT_DN_QTK_CN_TONG=:gtDnQtkCnTong ");
			if(obj.getGtDnQtkCnNgay()!=null){
				sql.append(", GT_DN_QTK_CN_NGAY=:gtDnQtkCnNgay ");
			}
			if(obj.getGtDnQtkCnXd()!=null){
				sql.append(", GT_DN_QTK_CN_XD=:gtDnQtkCnXd ");
			}
			if(obj.getGtDnQtkCnDien()!=null){
				sql.append(", GT_DN_QTK_CN_DIEN=:gtDnQtkCnDien ");
			}
			if(obj.getGtDnQtkCnLapDung()!=null){
				sql.append(", GT_DN_QTK_CN_LAP_DUNG=:gtDnQtkCnLapDung ");
			}
			if(obj.getGtDnQtkCnLapBts()!=null){
				sql.append(", GT_DN_QTK_CN_LAP_BTS=:gtDnQtkCnLapBts ");
			}
			if(obj.getGtDnQtkCnKhac()!=null){
				sql.append(", GT_DN_QTK_CN_KHAC=:gtDnQtkCnKhac ");
			}
			if(StringUtils.isNotEmpty(obj.getGtDnQtkCnNguoiLap())){
				sql.append(", GT_DN_QTK_CN_NGUOI_LAP=:gtDnQtkCnNguoiLap ");
			}
			sql.append(" where TBL_QL_CV_PTK_ID=:tblQlCvPtkId ");
					
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.setParameter("gtDnQtkCnTong", obj.getGtDnQtkCnTong());
			
			if(null!=obj.getGtDnQtkCnNgay()){
				query.setTimestamp("gtDnQtkCnNgay", obj.getGtDnQtkCnNgay());
			}
			if(null!=obj.getGtDnQtkCnXd()){
				query.setParameter("gtDnQtkCnXd", obj.getGtDnQtkCnXd());
			}
			if(null!=obj.getGtDnQtkCnDien()){
				query.setParameter("gtDnQtkCnDien", obj.getGtDnQtkCnDien());
			}
			if(null!=obj.getGtDnQtkCnLapDung()){
				query.setParameter("gtDnQtkCnLapDung", obj.getGtDnQtkCnLapDung());
			}
			if(null!=obj.getGtDnQtkCnLapBts()){
				query.setParameter("gtDnQtkCnLapBts", obj.getGtDnQtkCnLapBts());
			}
			if(null!=obj.getGtDnQtkCnKhac()){
				query.setParameter("gtDnQtkCnKhac", obj.getGtDnQtkCnKhac());
			}
			if(StringUtils.isNotEmpty(obj.getGtDnQtkCnNguoiLap())){
				query.setParameter("gtDnQtkCnNguoiLap", obj.getGtDnQtkCnNguoiLap());
			}			
			query.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
			query.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    public Long updateCV3(TblQlCvPtkDTO obj) {
		try {
		
			StringBuilder sql = new StringBuilder("update TBL_QL_CV_PTK set GT_QTK_PTK_TONG=:gtQtkPtkTong ");
			if(obj.getGtQtkPtkNgay()!=null){
				sql.append(", GT_QTK_PTK_NGAY=:gtQtkPtkNgay");
			}
			if(obj.getGtQtkPtkXd()!=null){
				sql.append(", GT_QTK_PTK_XD=:gtQtkPtkXd ");
			}
			if(obj.getGtQtkPtkDien()!=null){
				sql.append(", GT_QTK_PTK_DIEN=:gtQtkPtkDien ");
			}
			if(obj.getGtQtkPtkLapDung()!=null){
				sql.append(", GT_QTK_PTK_LAP_DUNG=:gtQtkPtkLapDung ");
			}
			if(obj.getGtQtkPtkLapBts()!=null){
				sql.append(", GT_QTK_PTK_LAP_BTS=:gtQtkPtkLapBts ");
			}
			if(obj.getGtQtkPtkKhac()!=null){
				sql.append(", GT_QTK_PTK_KHAC=:gtQtkPtkKhac ");
			}
			if(StringUtils.isNotEmpty(obj.getGtQtkPtkNguoiChot())){
				sql.append(", GT_QTK_PTK_NGUOI_CHOT=:gtQtkPtkNguoiChot ");
			}
			if(StringUtils.isNotEmpty(obj.getGtQtkPtkDmtt())){
				sql.append(", GT_QTK_PTK_DMTT=:gtQtkPtkDmtt ");
			}
			if(StringUtils.isNotEmpty(obj.getGtQtkPtkThangQtk())){
				sql.append(", GT_QTK_PTK_THANG_QTK=:gtQtkPtkThangQtk ");
			}
			sql.append(" where TBL_QL_CV_PTK_ID=:tblQlCvPtkId ");
		
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			
			query.setParameter("gtQtkPtkTong", obj.getGtQtkPtkTong());
		
			if(null!=obj.getGtQtkPtkNgay()){
				query.setTimestamp("gtQtkPtkNgay", obj.getGtQtkPtkNgay());
			}
			
			if(null!=obj.getGtQtkPtkXd()){
				query.setParameter("gtQtkPtkXd", obj.getGtQtkPtkXd());
			}
			if(null!=obj.getGtQtkPtkDien()){
				query.setParameter("gtQtkPtkDien", obj.getGtQtkPtkDien());
			}
			if(null!=obj.getGtQtkPtkLapDung()){
				query.setParameter("gtQtkPtkLapDung", obj.getGtQtkPtkLapDung());
			}
			if(null!=obj.getGtQtkPtkLapBts()){
				query.setParameter("gtQtkPtkLapBts", obj.getGtQtkPtkLapBts());
			}
			if(null!=obj.getGtQtkPtkKhac()){
				query.setParameter("gtQtkPtkKhac", obj.getGtQtkPtkKhac());
			}
			if(StringUtils.isNotEmpty(obj.getGtQtkPtkNguoiChot())){
				query.setParameter("gtQtkPtkNguoiChot", obj.getGtQtkPtkNguoiChot());
			}
			if(StringUtils.isNotEmpty(obj.getGtQtkPtkDmtt())){
				query.setParameter("gtQtkPtkDmtt", obj.getGtQtkPtkDmtt());
			}
			if(StringUtils.isNotEmpty(obj.getGtQtkPtkThangQtk())){
				query.setParameter("gtQtkPtkThangQtk", obj.getGtQtkPtkThangQtk());
			}
			
			query.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
			query.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    public Long updateCV4(TblQlCvPtkDTO obj) {
		try {
			StringBuilder sql = new StringBuilder("update TBL_QL_CV_PTK set DN_QT_CDT_GT=:dnQtCdtGt ");
			if(obj.getDnQtCdtNgay()!=null){
				sql.append(", DN_QT_CDT_NGAY=:dnQtCdtNgay ");
			}
			if(StringUtils.isNotEmpty(obj.getDnQtCdtNguoiLap())){
				sql.append(", DN_QT_CDT_NGUOI_LAP=:dnQtCdtNguoiLap ");
			}
			if(StringUtils.isNotEmpty(obj.getDnQtCdtNoiDungPsCtk())){
				sql.append(", DN_QT_CDT_NOI_DUNG_PS_CTK=:dnQtCdtNoiDungPsCtk ");
			}
			if(StringUtils.isNotEmpty(obj.getDnQtCdtKtk())){
				sql.append(", DN_QT_CDT_KTK=:dnQtCdtKtk ");
			}
			if(StringUtils.isNotEmpty(obj.getDnQtCdtNguoiNhanBg())){
				sql.append(", DN_QT_CDT_NGUOI_NHAN_BG=:dnQtCdtNguoiNhanBg ");
			}
			sql.append(" where TBL_QL_CV_PTK_ID=:tblQlCvPtkId ");
		
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.setParameter("dnQtCdtGt", obj.getDnQtCdtGt());
			if(null!=obj.getDnQtCdtNgay()){
				query.setTimestamp("dnQtCdtNgay", obj.getDnQtCdtNgay());
			}

			if(StringUtils.isNotEmpty(obj.getDnQtCdtNguoiLap())){
				query.setParameter("dnQtCdtNguoiLap", obj.getDnQtCdtNguoiLap());
			}
			if(StringUtils.isNotEmpty(obj.getDnQtCdtNoiDungPsCtk())){
				query.setParameter("dnQtCdtNoiDungPsCtk", obj.getDnQtCdtNoiDungPsCtk());
			}
			if(StringUtils.isNotEmpty(obj.getDnQtCdtKtk())){
				query.setParameter("dnQtCdtKtk", obj.getDnQtCdtKtk());
			}
			if(StringUtils.isNotEmpty(obj.getDnQtCdtNguoiNhanBg())){
				query.setParameter("dnQtCdtNguoiNhanBg", obj.getDnQtCdtNguoiNhanBg());
			}
			query.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
			query.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    public Long updateCV5(TblQlCvPtkDTO obj) {
		try {
			StringBuilder sql = new StringBuilder(" update TBL_QL_CV_PTK set TD_QT_CDT_GT=:tdQtCdtGt");
			if(null!=obj.getTdQtCdtNgayChot()){
				sql.append(" , TD_QT_CDT_NGAY_CHOT=:tdQtCdtNgayChot ");
			}
			
			if(StringUtils.isNotEmpty(obj.getTdQtCdtNguoiChotMvt())){
				sql.append(" , TD_QT_CDT_NGUOI_CHOT_MVT=:tdQtCdtNguoiChotMvt ");
			}
			if(StringUtils.isNotEmpty(obj.getTdQtCdtNguoiTdCdtMvt())){
				sql.append(" , TD_QT_CDT_NGUOI_TD_CDT_MVT=:tdQtCdtNguoiTdCdtMvt ");
			}
			if(obj.getTdQtCdtNgayBanTd()!=null){
				sql.append(" , TD_QT_CDT_NGAY_BAN_TD=:tdQtCdtNgayBanTd ");
			}
			if(obj.getTdQtCdtNgayCtc()!=null){
				sql.append(" , TD_QT_CDT_NGAY_CTC=:tdQtCdtNgayCtc ");
			}			
			if(StringUtils.isNotEmpty(obj.getTdQtCdtNoiDung())){
				sql.append(" , TD_QT_CDT_NOI_DUNG=:tdQtCdtNoiDung ");
			}
			sql.append(" where TBL_QL_CV_PTK_ID=:tblQlCvPtkId ");
		
			SQLQuery query = getSession().createSQLQuery(sql.toString());
		
			query.setParameter("tdQtCdtGt", obj.getTdQtCdtGt());
		
			if(null!=obj.getTdQtCdtNgayChot()){
				query.setTimestamp("tdQtCdtNgayChot", obj.getTdQtCdtNgayChot());
			}
			
			if(StringUtils.isNotEmpty(obj.getTdQtCdtNguoiChotMvt())){
				query.setParameter("tdQtCdtNguoiChotMvt", obj.getTdQtCdtNguoiChotMvt());
			}
			if(StringUtils.isNotEmpty(obj.getTdQtCdtNguoiTdCdtMvt())){
				query.setParameter("tdQtCdtNguoiTdCdtMvt", obj.getTdQtCdtNguoiTdCdtMvt());
			}
			if(null!=obj.getTdQtCdtNgayBanTd()){
				query.setTimestamp("tdQtCdtNgayBanTd", obj.getTdQtCdtNgayBanTd());
			}
			if(null!=obj.getTdQtCdtNgayCtc()){
				query.setTimestamp("tdQtCdtNgayCtc", obj.getTdQtCdtNgayCtc());
			}			
			if(StringUtils.isNotEmpty(obj.getTdQtCdtNoiDung())){
				query.setParameter("tdQtCdtNoiDung", obj.getTdQtCdtNoiDung());
			}	
			query.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
			query.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    public List<TblQlCvPtkDTO> listTasks(TblQlCvPtkDTO obj){
      	StringBuilder stringBuilder = new StringBuilder("select  ");
    		stringBuilder.append("T1.TBL_QL_CV_PTK_ID tblQlCvPtkId ");
    		stringBuilder.append(",T1.CNKV cnkv ");
    		stringBuilder.append(",T1.MTT_MA_VI_TRI mttMaViTri ");
    		stringBuilder.append(",T1.SO_HD_CDT soHdCdt ");
    		stringBuilder.append(",T1.NGAY_NHAN_HSHC ngayNhanHshc ");
    		stringBuilder.append(",T1.GT_SL_HT_TT_TONG gtSlHtTtTong "); 		
    		stringBuilder.append(",T1.GT_DN_QTK_CN_TONG gtDnQtkCnTong ");
    		stringBuilder.append(",T1.GT_QTK_PTK_TONG gtQtkPtkTong ");
    		stringBuilder.append(",T1.DN_QT_CDT_GT dnQtCdtGt ");
    		stringBuilder.append(",T1.TD_QT_CDT_GT tdQtCdtGt ");
  
        	
        	
    		stringBuilder.append(" FROM TBL_QL_CV_PTK T1  where 1=1 ");
    		
        	
    		

    		if (null != obj.getTblQlCvPtkId()) {
    			stringBuilder.append("AND T1.TBL_QL_CV_PTK_ID=:tblQlCvPtkId ");
    		}
    		if (StringUtils.isNotEmpty(obj.getCnkv())) {
    			stringBuilder.append("AND (UPPER(T1.CNKV) LIKE UPPER(:cnkv)  ESCAPE '&')");
    		}
    		
    		if(obj.getListMaViTri()!=null && obj.getListMaViTri().size()>0){
        		stringBuilder.append(" and T1.MTT_MA_VI_TRI IN (:listMaViTri) ");
        	}
    
    		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
    			stringBuilder.append("AND (UPPER(T1.SO_HD_CDT) LIKE UPPER(:soHdCdt)   ESCAPE '&')");
    		}
    		
    		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    			stringBuilder.append("AND T1.NGAY_NHAN_HSHC >= :ngayNhanHshcFrom ");
    		}
    		if (null != obj.getNgayNhanHshcTo() && null != obj.getNgayNhanHshcFrom()) {
    			stringBuilder.append(" AND ( T1.NGAY_NHAN_HSHC between :ngayNhanHshcFrom and :ngayNhanHshcTo ) ");
    		}
    	
    	
    		stringBuilder.append(" order by T1.TBL_QL_CV_PTK_ID desc");
    		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
    		sqlCount.append(stringBuilder.toString());
    		sqlCount.append(")");

    		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
    		
    			query.addScalar("tblQlCvPtkId", new LongType());
    			query.addScalar("cnkv", new StringType());
    			query.addScalar("mttMaViTri", new StringType());
    			
    			query.addScalar("soHdCdt", new StringType());
    			query.addScalar("ngayNhanHshc", new DateType());
    			
    			query.addScalar("gtSlHtTtTong", new FloatType());
    			
    			query.addScalar("gtDnQtkCnTong", new FloatType());
    	
    			query.addScalar("gtQtkPtkTong", new FloatType());
    		
    		
    			query.addScalar("dnQtCdtGt", new FloatType());
    
    			query.addScalar("tdQtCdtGt", new FloatType());
    	
    			
    		
        	
    			query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
    			
    			
    		if (null != obj.getTblQlCvPtkId()) {
    			query.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
    			queryCount.setParameter("tblQlCvPtkId", obj.getTblQlCvPtkId());
    		}
    		if (StringUtils.isNotEmpty(obj.getCnkv())) {
    			query.setParameter("cnkv", "%" + ValidateUtils.validateKeySearch(obj.getCnkv()) + "%");
    			queryCount.setParameter("cnkv", "%" + ValidateUtils.validateKeySearch(obj.getCnkv()) + "%");
    		}
    	
    		if(obj.getListMaViTri()!=null && obj.getListMaViTri().size()>0){ 
        		query.setParameterList("listMaViTri", obj.getListMaViTri());
        		queryCount.setParameterList("listMaViTri", obj.getListMaViTri());
        	}
    	
    		if (StringUtils.isNotEmpty(obj.getSoHdCdt())) {
    			query.setParameter("soHdCdt", "%" + ValidateUtils.validateKeySearch(obj.getSoHdCdt()) + "%");
    			queryCount.setParameter("soHdCdt", "%" + ValidateUtils.validateKeySearch(obj.getSoHdCdt()) + "%");
    		}
    		
    		if (null != obj.getNgayNhanHshcFrom() && obj.getNgayNhanHshcTo() == null) {
    			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
    			queryCount.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
    		}
    		if (null != obj.getNgayNhanHshcTo() && null != obj.getNgayNhanHshcFrom()) {
    			query.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());
    			queryCount.setTimestamp("ngayNhanHshcFrom", obj.getNgayNhanHshcFrom());

    			query.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
    			queryCount.setTimestamp("ngayNhanHshcTo", obj.getNgayNhanHshcTo());
    		}
    		
    		
    		if (obj.getPage() != null && obj.getPageSize() != null) {
    			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
    			query.setMaxResults(obj.getPageSize().intValue());
    		}
    		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
    		return query.list();
    }
    
//    public List<TblQlCvPtkDTO> reportTotal(){
//    	StringBuilder stringBuilder = new StringBuilder(" select T1.SO_HD_CDT soHdCdt, "
//    								+ " ( select  count(*) from ( select  T1.SO_HD_CDT  from TBL_QL_CV_PTK T1 where T1.SO_HD_CDT is  null ) ) soLuongHD,"
//    								+ " count(case T1.GT_SL_HT_TT_TONG when 0 then null else T1.GT_SL_HT_TT_TONG end) soLuongGtHt, "
//    								+ " sum(T1.GT_SL_HT_TT_TONG) sumGtHt, "
//    								+ " count(case T1.GT_DN_QTK_CN_TONG when 0 then null else T1.GT_DN_QTK_CN_TONG end) soLuongDnQtk, "
//    								+ " sum(T1.GT_DN_QTK_CN_TONG) sumDnQtk, "
//    								+ " count(case T1.GT_QTK_PTK_TONG when 0 then null else T1.GT_QTK_PTK_TONG end) soLuongTdQtk, "
//    								+ " sum(T1.GT_QTK_PTK_TONG) sumTdQtk, "
//    								+ " count(case T1.DN_QT_CDT_GT when 0 then null else T1.DN_QT_CDT_GT end) soLuongDnCdt, "
//    								+ " sum(T1.DN_QT_CDT_GT) sumDnCdt, "
//    								+ " count(case T1.TD_QT_CDT_GT when 0 then null else T1.TD_QT_CDT_GT end) soLuongTdCdt, "
//    								+ " sum(T1.TD_QT_CDT_GT) sumTdCdt, "
//    								+ " count(case T1.QT_DT_TONG when 0 then null else T1.QT_DT_TONG end) soLuongQtDt, "
//    								+ " sum(T1.QT_DT_TONG) sumQtDt "
//    								+ " from TBL_QL_CV_PTK T1 where T1.SO_HD_CDT is null group by SO_HD_CDT "
//    								+ " union all "
//    								+ " select T1.SO_HD_CDT soHdCdt, count(T1.SO_HD_CDT) soLuongHD ,"
//    								+ " count(case T1.GT_SL_HT_TT_TONG when 0 then null else T1.GT_SL_HT_TT_TONG end) soLuongGtHt, "
//    								+ " sum(T1.GT_SL_HT_TT_TONG) sumGtHt, "
//    								+ " count(case T1.GT_DN_QTK_CN_TONG when 0 then null else T1.GT_DN_QTK_CN_TONG end) soLuongDnQtk, "
//    								+ " sum(T1.GT_DN_QTK_CN_TONG) sumDnQtk, "
//    								+ " count(case T1.GT_QTK_PTK_TONG when 0 then null else T1.GT_QTK_PTK_TONG end) soLuongTdQtk, "
//    								+ " sum(T1.GT_QTK_PTK_TONG) sumTdQtk, "
//    								+ " count(case T1.DN_QT_CDT_GT when 0 then null else T1.DN_QT_CDT_GT end) soLuongDnCdt, "
//    								+ " sum(T1.DN_QT_CDT_GT) sumDnCdt, "
//    								+ " count(case T1.TD_QT_CDT_GT when 0 then null else T1.TD_QT_CDT_GT end) soLuongTdCdt, "
//    								+ " sum(T1.TD_QT_CDT_GT) sumTdCdt, "
//    								+ " count(case T1.QT_DT_TONG when 0 then null else T1.QT_DT_TONG end) soLuongQtDt, "
//    								+ " sum(T1.QT_DT_TONG) sumQtDt "
//    								+ " from TBL_QL_CV_PTK T1 where T1.SO_HD_CDT is not null group by SO_HD_CDT");
//    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//    	query.addScalar("soHdCdt", new StringType());
//    	query.addScalar("soLuongHD", new LongType());
//    	query.addScalar("soLuongGtHt", new LongType());
//    	query.addScalar("sumGtHt", new FloatType());
//    	query.addScalar("soLuongDnQtk", new LongType());
//    	query.addScalar("sumDnQtk", new FloatType());
//    	query.addScalar("soLuongTdQtk", new LongType());
//    	query.addScalar("sumTdQtk", new FloatType());
//    	query.addScalar("soLuongDnCdt", new LongType());
//    	query.addScalar("sumDnCdt", new FloatType());
//    	query.addScalar("soLuongTdCdt", new LongType());
//    	query.addScalar("sumTdCdt", new FloatType());
//    	query.addScalar("soLuongQtDt", new LongType());
//    	query.addScalar("sumQtDt", new FloatType());
//    	query.setResultTransformer(Transformers.aliasToBean(TblQlCvPtkDTO.class));
//    
//    	return query.list();
//    }

}
