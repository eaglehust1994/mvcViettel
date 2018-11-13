package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.LsThaoTacBO;
import com.viettel.qll.bo.TblQltsThucXuatTheoKyBO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblQltsThucXuatTheoKyDAO")
public class TblQltsThucXuatTheoKyDAO extends BaseFWDAOImpl<TblQltsThucXuatTheoKyBO, Long> {

	public TblQltsThucXuatTheoKyDAO() {
		this.model = new TblQltsThucXuatTheoKyBO();
	}

	public TblQltsThucXuatTheoKyDAO(Session session) {
		this.session = session;
	}

	public List<TblQltsThucXuatTheoKyDTO> getAll() {
		StringBuilder sql = new StringBuilder(" SELECT zz.TBL_QLTS_THUC_XUAT_THEO_KY_ID tblQltsThucXuatTheoKyId,"
				+ "zz.SO_PHIEU_XUAT soPhieuXuat, " + "zz.NOI_DUNG noiDung, " + "zz.MA_XUAT_KHO maXuatKho, "
				+ "zz.TEN_VAT_TU tenVatTu " + "FROM TBL_QLTS_THUC_XUAT_THEO_KY zz " + "WHERE 1 = 1 ");

		sql.append(" ORDER BY zz.TBL_QLTS_THUC_XUAT_THEO_KY_ID");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblQltsThucXuatTheoKyId", new LongType());
		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("noiDung", new StringType());
		query.addScalar("maXuatKho", new StringType());
		query.addScalar("tenVatTu", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));

		return query.list();
	}

	public List<TblQltsThucXuatTheoKyDTO> doSearch(TblQltsThucXuatTheoKyDTO obj) {
		StringBuilder sql = new StringBuilder(" SELECT zz.TBL_QLTS_THUC_XUAT_THEO_KY_ID tblQltsThucXuatTheoKyId,"
				+ "zz.SO_PHIEU_XUAT soPhieuXuat, " + "zz.NOI_DUNG noiDung, " + "zz.MA_KHO_XUAT maKhoXuat, "
				+ "zz.TEN_VAT_TU tenVatTu , " + "zz.NGAY_XUAT ngayXuat, " + "zz.MA_VAT_TU maVatTu, "
				+ "zz.DON_VI_TINH donViTinh, " + "zz.SO_LUONG_XUAT soLuongXuat, " + "zz.DON_GIA donGia, "
				+ "zz.THANH_TIEN thanhTien, " + "zz.MA_CONG_TRINH maCongTrinh, " + "zz.NGUOI_NHAN_HANG nguoiNhanHang, "
				+ "zz.SO_LUONG_YEU_CAU soLuongYeuCau, " + "zz.LY_DO lyDo, " + "zz.DIEN_GIAI dienGiai, "
				+ "zz.TEN_KHO_XUAT tenKhoXuat, " + "zz.MA_KHO_NHAN maKhoNhan," + "zz.DON_VI_NHAN donViNhan,"
				+ "zz.KHO_NHAN khoNhan," + "zz.MA_TINH maTinh," + "zz.MA_NV maNv," + "zz.MA_CHI_NHANH maChiNhanh, "
				+ "zz.SO_PHIEU_XUAT_BEN_LOGISTIC soPhieuXuatBenLogistic,"
				+ "zz.SL_THUC_TE_TC  slThucTeTC,zz.TT_THUC_TE_TC ttThucTeTC,zz.SL_THU_HOI slThuHoi,"
				+ "zz.TT_THU_HOI ttThuHoi,zz.SL_TIEN_DEN slTienDen,zz.TT_TIEN_DEN ttTienDen "

				+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY zz " + "WHERE zz.HOAT_DONG = 1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(zz.SO_PHIEU_XUAT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(zz.SO_PHIEU_XUAT_BEN_LOGISTIC) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(zz.MA_KHO_XUAT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(zz.MA_VAT_TU) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(zz.MA_CONG_TRINH) LIKE upper(:keySearch) escape '&')");
		}
		// if (obj.getNgayXuatFrom() != null && obj.getNgayXuatTo() != null) {
		// sql.append(" AND (zz.NGAY_XUAT BETWEEN :ngayXuatFrom AND :ngayXuatTo
		// )");
		// }
		if(StringUtils.isNotEmpty(obj.getDonViNhan())){
			sql.append(" AND upper(zz.DON_VI_NHAN) = upper(:donVinhan) ");
		}
		
		if (StringUtils.isNotEmpty(obj.getNoiDung())) {
			sql.append(" AND (upper(zz.NOI_DUNG) LIKE upper(:noiDung) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMaKhoXuat())) {
			sql.append(" AND (upper(zz.MA_KHO_XUAT) LIKE upper(:maKhoXuat) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getTenVatTu())) {
			sql.append(" AND (upper(zz.TEN_VAT_TU) LIKE upper(:tenVatTu) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMaVatTu())) {
			sql.append(" AND (upper(zz.MA_VAT_TU) LIKE upper(:maVatTu) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMaCongTrinh())) {
			sql.append(" AND (upper(zz.MA_CONG_TRINH) LIKE upper(:maCongTrinh) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getNguoiNhanHang())) {
			sql.append(" AND (upper(zz.NGUOI_NHAN_HANG) LIKE upper(:nguoiNhanHang) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getLyDo())) {
			sql.append(" AND (upper(zz.LY_DO) LIKE upper(:lyDo) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getDienGiai())) {
			sql.append(" AND (upper(zz.DIEN_GIAI) LIKE upper(:dienGiai) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getTenKhoXuat())) {
			sql.append(" AND (upper(zz.TEN_KHO_XUAT) LIKE upper(:tenKhoXuat) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getMaKhoNhan())) {
			sql.append(" AND (upper(zz.MA_KHO_NHAN) LIKE upper(:maKhoNhan) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getSoPhieuXuat())) {
			sql.append(" AND (upper(zz.SO_PHIEU_XUAT) LIKE upper(:soPhieuXuat) escape '&')");
		}

		if (StringUtils.isNotEmpty(obj.getSoPhieuXuatBenLogistic())) {
			sql.append(" AND (upper(zz.SO_PHIEU_XUAT_BEN_LOGISTIC) LIKE upper(:soPhieuXuatBenLogistic) escape '&')");
		}

		if (obj.getNgayXuatFrom() != null && (obj.getNgayXuatTo() == null)) {
			sql.append(" AND NGAY_XUAT >= :ngayXuatFrom");
		}
		if ((obj.getNgayXuatFrom() != null) && (obj.getNgayXuatFrom() != null)) {
			sql.append(" AND NGAY_XUAT between :ngayXuatFrom and :ngayXuatTo");
		}

		sql.append(" ORDER BY zz.TBL_QLTS_THUC_XUAT_THEO_KY_ID");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblQltsThucXuatTheoKyId", new LongType());
		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("noiDung", new StringType());
		query.addScalar("maKhoXuat", new StringType());
		query.addScalar("tenVatTu", new StringType());
		query.addScalar("maKhoNhan", new StringType());
		query.addScalar("maVatTu", new StringType());
		query.addScalar("donViTinh", new StringType());
		query.addScalar("soLuongXuat", new FloatType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("thanhTien", new FloatType());
		query.addScalar("maCongTrinh", new StringType());
		query.addScalar("nguoiNhanHang", new StringType());
		query.addScalar("soLuongYeuCau", new FloatType());
		query.addScalar("lyDo", new StringType());
		query.addScalar("dienGiai", new StringType());
		query.addScalar("tenKhoXuat", new StringType());
		query.addScalar("lyDo", new StringType());
		query.addScalar("soPhieuXuatBenLogistic", new StringType());
		query.addScalar("donViNhan", new StringType());
		query.addScalar("khoNhan", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("maChiNhanh", new StringType());

		query.addScalar("slThucTeTC", new FloatType());
		query.addScalar("ttThucTeTC", new FloatType());
		query.addScalar("slThuHoi", new FloatType());
		query.addScalar("ttThuHoi", new FloatType());
		query.addScalar("slTienDen", new FloatType());
		query.addScalar("ttTienDen", new FloatType());

		query.addScalar("ngayXuat", new TimestampType());

		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		if(StringUtils.isNotEmpty(obj.getDonViNhan())){
			query.setParameter("donVinhan", obj.getDonViNhan());
			queryCount.setParameter("donVinhan", obj.getDonViNhan());
		}
		if (StringUtils.isNotEmpty(obj.getNoiDung())) {
			query.setParameter("noiDung", "%" + ValidateUtils.validateKeySearch(obj.getNoiDung()) + "%");
			queryCount.setParameter("noiDung", "%" + ValidateUtils.validateKeySearch(obj.getNoiDung()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaKhoXuat())) {
			query.setParameter("maKhoXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaKhoXuat()) + "%");
			queryCount.setParameter("maKhoXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaKhoXuat()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenVatTu())) {
			query.setParameter("tenVatTu", "%" + ValidateUtils.validateKeySearch(obj.getTenVatTu()) + "%");
			queryCount.setParameter("tenVatTu", "%" + ValidateUtils.validateKeySearch(obj.getTenVatTu()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaVatTu())) {
			query.setParameter("maVatTu", "%" + ValidateUtils.validateKeySearch(obj.getMaVatTu()) + "%");
			queryCount.setParameter("maVatTu", "%" + ValidateUtils.validateKeySearch(obj.getMaVatTu()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaCongTrinh())) {
			query.setParameter("maCongTrinh", "%" + ValidateUtils.validateKeySearch(obj.getMaCongTrinh()) + "%");
			queryCount.setParameter("maCongTrinh", "%" + ValidateUtils.validateKeySearch(obj.getMaCongTrinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getNguoiNhanHang())) {
			query.setParameter("nguoiNhanHang", "%" + ValidateUtils.validateKeySearch(obj.getNguoiNhanHang()) + "%");
			queryCount.setParameter("nguoiNhanHang",
					"%" + ValidateUtils.validateKeySearch(obj.getNguoiNhanHang()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getSoPhieuXuat())) {
			query.setParameter("soPhieuXuat", "%" + ValidateUtils.validateKeySearch(obj.getSoPhieuXuat()) + "%");
			queryCount.setParameter("soPhieuXuat", "%" + ValidateUtils.validateKeySearch(obj.getSoPhieuXuat()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getLyDo())) {
			query.setParameter("lyDo", "%" + ValidateUtils.validateKeySearch(obj.getLyDo()) + "%");
			queryCount.setParameter("lyDo", "%" + ValidateUtils.validateKeySearch(obj.getLyDo()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDienGiai())) {
			query.setParameter("dienGiai", "%" + ValidateUtils.validateKeySearch(obj.getDienGiai()) + "%");
			queryCount.setParameter("dienGiai", "%" + ValidateUtils.validateKeySearch(obj.getDienGiai()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenKhoXuat())) {
			query.setParameter("tenKhoXuat", "%" + ValidateUtils.validateKeySearch(obj.getTenKhoXuat()) + "%");
			queryCount.setParameter("tenKhoXuat", "%" + ValidateUtils.validateKeySearch(obj.getTenKhoXuat()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaKhoNhan())) {
			query.setParameter("makhoNhan", "%" + ValidateUtils.validateKeySearch(obj.getMaKhoNhan()) + "%");
			queryCount.setParameter("makhoNhan", "%" + ValidateUtils.validateKeySearch(obj.getMaKhoNhan()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getSoPhieuXuatBenLogistic())) {
			query.setParameter("soPhieuXuatBenLogistic",
					"%" + ValidateUtils.validateKeySearch(obj.getSoPhieuXuatBenLogistic()) + "%");
			queryCount.setParameter("soPhieuXuatBenLogistic",
					"%" + ValidateUtils.validateKeySearch(obj.getSoPhieuXuatBenLogistic()) + "%");
		}

		if (obj.getNgayXuatFrom() != null && (obj.getNgayXuatTo() == null)) {
			query.setTimestamp("ngayXuatFrom", obj.getNgayXuatFrom());
			queryCount.setTimestamp("ngayXuatFrom", obj.getNgayXuatFrom());
		}
		if ((obj.getNgayXuatFrom() != null) && (obj.getNgayXuatTo() != null)) {
			query.setTimestamp("ngayXuatFrom", obj.getNgayXuatFrom());
			queryCount.setTimestamp("ngayXuatFrom", obj.getNgayXuatFrom());

			query.setTimestamp("ngayXuatTo", obj.getNgayXuatTo());
			queryCount.setTimestamp("ngayXuatTo", obj.getNgayXuatTo());

		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public List<TblQltsThucXuatTheoKyDTO> doSearchByPXK(TblQltsThucXuatTheoKyDTO obj) {
		StringBuilder sql = new StringBuilder(" SELECT zz.TBL_QLTS_THUC_XUAT_THEO_KY_ID tblQltsThucXuatTheoKyId,"
				+ "zz.SO_PHIEU_XUAT soPhieuXuat, " + "zz.NOI_DUNG noiDung, " + "zz.MA_KHO_XUAT maKhoXuat, "
				+ "zz.TEN_VAT_TU tenVatTu , " + "zz.NGAY_XUAT ngayXuat, " + "zz.MA_VAT_TU maVatTu, "
				+ "zz.DON_VI_TINH donViTinh, " + "zz.SO_LUONG_XUAT soLuongXuat, " + "zz.DON_GIA donGia, "
				+ "zz.THANH_TIEN thanhTien, " + "zz.MA_CONG_TRINH maCongTrinh, " + "zz.NGUOI_NHAN_HANG nguoiNhanHang, "
				+ "zz.SO_LUONG_YEU_CAU soLuongYeuCau, " + "zz.LY_DO lyDo, " + "zz.DIEN_GIAI dienGiai, "
				+ "zz.TEN_KHO_XUAT tenKhoXuat, " + "zz.MA_KHO_NHAN maKhoNhan," + "zz.DON_VI_NHAN donViNhan,"
				+ "zz.KHO_NHAN khoNhan," + "zz.MA_TINH maTinh," + "zz.MA_NV maNv," + "zz.MA_CHI_NHANH maChiNhanh, "
				+ "zz.SO_PHIEU_XUAT_BEN_LOGISTIC soPhieuXuatBenLogistic,"
				+ "zz.SL_THUC_TE_TC  slThucTeTC,zz.TT_THUC_TE_TC ttThucTeTC,zz.SL_THU_HOI slThuHoi,"
				+ "zz.TT_THU_HOI ttThuHoi,zz.SL_TIEN_DEN slTienDen,zz.TT_TIEN_DEN ttTienDen,"
				+ "tp.CONG_TRINH_NHAN congTrinhNhan,tp.MA_HDXL maHdxl "

				+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY zz "
				+ "left join TBL_TYPE_A_PXK tp on zz.SO_PHIEU_XUAT=tp.MA_PHIEU_XUAT "
				+ " WHERE zz.HOAT_DONG = 1 And zz.SO_PHIEU_XUAT=:soPhieuXuat");

		sql.append(" ORDER BY zz.TBL_QLTS_THUC_XUAT_THEO_KY_ID");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblQltsThucXuatTheoKyId", new LongType());
		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("noiDung", new StringType());
		query.addScalar("maKhoXuat", new StringType());
		query.addScalar("tenVatTu", new StringType());
		query.addScalar("maKhoNhan", new StringType());
		query.addScalar("maVatTu", new StringType());
		query.addScalar("donViTinh", new StringType());
		query.addScalar("soLuongXuat", new FloatType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("thanhTien", new FloatType());
		query.addScalar("maCongTrinh", new StringType());
		query.addScalar("nguoiNhanHang", new StringType());
		query.addScalar("soLuongYeuCau", new FloatType());
		query.addScalar("lyDo", new StringType());
		query.addScalar("dienGiai", new StringType());
		query.addScalar("tenKhoXuat", new StringType());
		query.addScalar("lyDo", new StringType());
		query.addScalar("soPhieuXuatBenLogistic", new StringType());
		query.addScalar("donViNhan", new StringType());
		query.addScalar("khoNhan", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("maChiNhanh", new StringType());
		query.addScalar("congTrinhNhan", new StringType());
		query.addScalar("maHdxl", new StringType());

		query.addScalar("slThucTeTC", new FloatType());
		query.addScalar("ttThucTeTC", new FloatType());
		query.addScalar("slThuHoi", new FloatType());
		query.addScalar("ttThuHoi", new FloatType());
		query.addScalar("slTienDen", new FloatType());
		query.addScalar("ttTienDen", new FloatType());

		query.addScalar("ngayXuat", new TimestampType());

		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));

		query.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
		queryCount.setParameter("soPhieuXuat", obj.getSoPhieuXuat());

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public List<TblQltsThucXuatTheoKyDTO> selectAllNhanVienInTX() {
		StringBuilder sql = new StringBuilder(" SELECT distinct zz.MA_NV maNv,zz.SO_PHIEU_XUAT soPhieuXuat "
				+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY zz where zz.MA_NV is not null" + " order by zz.MA_NV");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("maNv", new StringType());
		query.addScalar("soPhieuXuat", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));

		return query.list();

	}

	public List<TblQltsThucXuatTheoKyDTO> selectAllNhanVienInTX1(TblQltsThucXuatTheoKyDTO obj) {
		StringBuilder sql = new StringBuilder(
				" SELECT distinct zz.MA_NV maNv,nv.FULL_NAME hoVaTen,zz.MA_CHI_NHANH maChiNhanh,zz.MA_TINH maTinh,"
						+ "zz.SO_PHIEU_XUAT soPhieuXuat,zz.LY_DO lyDo,ty.MA_TRAM_NHAN maTramNhan,"
						+ "zz.MA_CONG_TRINH maCongTrinh,ty.MA_HDXL maHdxl "
						+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY zz left join TBL_USERS nv on zz.MA_NV =nv.USER_CODE  "
						+ " left join TBL_TYPE_A_PXK ty on zz.SO_PHIEU_XUAT=ty.MA_PHIEU_XUAT "
						+ "WHERE zz.MA_NV is not null ");

		if ((obj.getNgayXuatFrom() != null) && (obj.getNgayXuatTo() != null)) {
			sql.append(" AND zz.NGAY_XUAT between :ngayXuatFrom and :ngayXuatTo ");
		}
		if(StringUtils.isNotEmpty(obj.getMaChiNhanh())){
			sql.append(" AND zz.MA_CHI_NHANH =:maChiNhanh ");
		}
		if(StringUtils.isNotEmpty(obj.getMaTinh())){
			sql.append(" AND zz.MA_TINH =:maTinh ");
		}
		sql.append("order by zz.MA_NV");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("maNv", new StringType());
		query.addScalar("hoVaTen", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("maChiNhanh", new StringType());
		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("lyDo", new StringType());
		query.addScalar("maHdxl", new StringType());
		query.addScalar("maCongTrinh", new StringType());
		query.addScalar("maTramNhan", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));
		if ((obj.getNgayXuatFrom() != null) && (obj.getNgayXuatTo() != null)) {
			query.setTimestamp("ngayXuatFrom", obj.getNgayXuatFrom());
			query.setTimestamp("ngayXuatTo", obj.getNgayXuatTo());

		}
		if(StringUtils.isNotEmpty(obj.getMaChiNhanh())){
			query.setParameter("maChiNhanh", obj.getMaChiNhanh());
		}
		if(StringUtils.isNotEmpty(obj.getMaTinh())){
			query.setParameter("maTinh", obj.getMaTinh());
		}
		return query.list();

	}

	public List<TblQltsThucXuatTheoKyDTO> selectAllDonViInTX1(TblQltsThucXuatTheoKyDTO obj) {
		StringBuilder sql = new StringBuilder(" SELECT distinct "
				+ "zz.MA_CHI_NHANH maChiNhanh," + "zz.MA_TINH maTinh,"
				+ "zz.SO_PHIEU_XUAT soPhieuXuat,zz.MA_KHO_NHAN  maKhoNhan,zz.LY_DO lyDo "
				+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY zz  WHERE zz.MA_TINH is not null or zz.MA_CHI_NHANH is not null");
		sql.append(" order by zz.MA_CHI_NHANH");
		if ((obj.getNgayXuatFrom() != null) && (obj.getNgayXuatTo() != null)) {
			sql.append(" AND zz.NGAY_XUAT between :ngayXuatFrom and :ngayXuatTo ");
		}

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("maKhoNhan", new StringType());
		query.addScalar("lyDo", new StringType());
		query.addScalar("maChiNhanh", new StringType());
		query.addScalar("maTinh", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));
		if ((obj.getNgayXuatFrom() != null) && (obj.getNgayXuatTo() != null)) {
			query.setTimestamp("ngayXuatFrom", obj.getNgayXuatFrom());
			query.setTimestamp("ngayXuatTo", obj.getNgayXuatTo());

		}
		return query.list();

	}

	public TblQltsThucXuatTheoKyDTO sumValue(String maNv, String soPhieuXuat) {
		StringBuilder sql = new StringBuilder(" SELECT sum(SO_LUONG_XUAT) soLuongXuat,sum(DON_GIA) donGia,"
				+ "sum(THANH_TIEN) thanhTien,sum(SL_THUC_TE_TC) slThucTeTC,sum(TT_THUC_TE_TC) ttThucTeTC,"
				+ "sum(SL_THU_HOI) slThuHoi,sum(TT_THU_HOI) ttThuHoi,sum(SL_TIEN_DEN) slTienDen,sum(TT_TIEN_DEN) ttTienDen "
				+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY " + "WHERE MA_NV=:maNv and SO_PHIEU_XUAT=:soPhieuXuat ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("soLuongXuat", new FloatType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("thanhTien", new FloatType());
		query.addScalar("slThucTeTC", new FloatType());
		query.addScalar("ttThucTeTC", new FloatType());
		query.addScalar("slThuHoi", new FloatType());
		query.addScalar("ttThuHoi", new FloatType());
		query.addScalar("slTienDen", new FloatType());
		query.addScalar("ttTienDen", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));
		query.setParameter("maNv", maNv);
		query.setParameter("soPhieuXuat", soPhieuXuat);
		return (TblQltsThucXuatTheoKyDTO) query.uniqueResult();

	}

	public TblQltsThucXuatTheoKyDTO sumValueByDonVi(String maChiNhanh,String maTinh, String soPhieuXuat) {
		StringBuilder sql = new StringBuilder(" SELECT sum(SO_LUONG_XUAT) soLuongXuat,sum(DON_GIA) donGia,"
				+ "sum(THANH_TIEN) thanhTien,sum(SL_THUC_TE_TC) slThucTeTC,sum(TT_THUC_TE_TC) ttThucTeTC,"
				+ "sum(SL_THU_HOI) slThuHoi,sum(TT_THU_HOI) ttThuHoi,sum(SL_TIEN_DEN) slTienDen,sum(TT_TIEN_DEN) ttTienDen "
				+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY " + "WHERE 1=1 ");
			if(StringUtils.isNotEmpty(maChiNhanh)){
				sql.append("and MA_CHI_NHANH=:maChiNhanh and SO_PHIEU_XUAT=:soPhieuXuat ");
			}else{
				sql.append("and  MA_TINH=:maTinh and SO_PHIEU_XUAT=:soPhieuXuat ");
			}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("soLuongXuat", new FloatType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("thanhTien", new FloatType());
		query.addScalar("slThucTeTC", new FloatType());
		query.addScalar("ttThucTeTC", new FloatType());
		query.addScalar("slThuHoi", new FloatType());
		query.addScalar("ttThuHoi", new FloatType());
		query.addScalar("slTienDen", new FloatType());
		query.addScalar("ttTienDen", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));
		if(StringUtils.isNotEmpty(maChiNhanh)){
			query.setParameter("maChiNhanh", maChiNhanh);
			query.setParameter("soPhieuXuat", soPhieuXuat);
		}else{
			query.setParameter("maTinh", maTinh);
			query.setParameter("soPhieuXuat", soPhieuXuat);
		}
		
		
		return (TblQltsThucXuatTheoKyDTO) query.uniqueResult();

	}

	public Long updateList(TblQltsThucXuatTheoKyDTO obj) {
		try {

			String sql2 = "update TBL_QLTS_THUC_XUAT_THEO_KY set MA_CHI_NHANH =:maChiNhanh where SO_PHIEU_XUAT =:soPhieuXuat ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maChiNhanh", obj.getMaChiNhanh());
			query2.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	public Long updateList1(TblQltsThucXuatTheoKyDTO obj) {
		try {

			String sql2 = "update TBL_QLTS_THUC_XUAT_THEO_KY set MA_TINH =:maTinh where SO_PHIEU_XUAT =:soPhieuXuat ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maTinh", obj.getMaTinh());
			query2.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	public Long updateListNV(TblQltsThucXuatTheoKyDTO obj) {
		try {

			String sql2 = "update TBL_QLTS_THUC_XUAT_THEO_KY set MA_NV=:maNv where SO_PHIEU_XUAT =:soPhieuXuat ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maNv", obj.getMaNv());
			query2.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	public Long updateListSL(TblQltsThucXuatTheoKyDTO obj) {
		try {
			String sql2 = "update TBL_QLTS_THUC_XUAT_THEO_KY " + "set SL_THUC_TE_TC=:slThucTeTC"
					+ ",TT_THUC_TE_TC=:ttThucTeTC" + ",SL_THU_HOI =:slThuHoi" + ",TT_THU_HOI =:ttThuHoi"
					+ ",SL_TIEN_DEN =: slTienDen" + ",TT_TIEN_DEN =:ttTienDen " + " where SO_PHIEU_XUAT =:soPhieuXuat ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("slThucTeTC", obj.getSlThucTeTC());
			query2.setParameter("ttThucTeTC", obj.getTtThucTeTC());
			query2.setParameter("slThuHoi", obj.getSlThuHoi());
			query2.setParameter("ttThuHoi", obj.getTtThuHoi());
			query2.setParameter("slTienDen", obj.getSlTienDen());
			query2.setParameter("ttTienDen", obj.getTtTienDen());
			query2.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	public List<TblQltsThucXuatTheoKyDTO> getDbByKey(TblQltsThucXuatTheoKyDTO obj) {
		StringBuilder sql = new StringBuilder(" SELECT  "
				+ "zz.MA_VAT_TU maVatTu," + "zz.MA_CONG_TRINH maCongTrinh,"
				+ "zz.SO_PHIEU_XUAT soPhieuXuat "
				+ "FROM TBL_QLTS_THUC_XUAT_THEO_KY zz WHERE zz.MA_VAT_TU =:maVatTu "
				+ "and zz.MA_CONG_TRINH=:maCongTrinh and zz.SO_PHIEU_XUAT=:soPhieuXuat ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("maVatTu", new StringType());
		query.addScalar("maCongTrinh", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblQltsThucXuatTheoKyDTO.class));
		query.setParameter("maVatTu", obj.getMaVatTu());
		query.setParameter("maCongTrinh", obj.getMaCongTrinh());
		query.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
		
		return query.list();

	}
	
	public Long deleteObj(TblQltsThucXuatTheoKyDTO obj) {
		try {
			String sql2 = "delete from TBL_QLTS_THUC_XUAT_THEO_KY zz where  zz.MA_VAT_TU =:maVatTu "
				+ "and zz.MA_CONG_TRINH=:maCongTrinh and zz.SO_PHIEU_XUAT=:soPhieuXuat ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maVatTu", obj.getMaVatTu());
			query2.setParameter("maCongTrinh", obj.getMaCongTrinh());
			query2.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	@Transactional
	  public String saveList1(List<LsThaoTacBO> obj)
	  {
	    try
	    {
	      for (LsThaoTacBO item : obj){
	    	  getSession().save(item);
	    	  System.out.println("ok");
	      }
	        
	    }
	    catch (SecurityException ex)
	    {
	      return ex.getMessage();
	    }catch (MappingException e) {
	    	return e.getMessage();
		}
	    return "SUCCESS";
	  }
}
