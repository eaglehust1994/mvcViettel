package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblTypeAPxkBO;
import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblTypeAPxkDAO")
public class TblTypeAPxkDAO extends BaseFWDAOImpl<TblTypeAPxkBO, Long> {

	public TblTypeAPxkDAO() {
		this.model = new TblTypeAPxkBO();
	}

	public TblTypeAPxkDAO(Session session) {
		this.session = session;
	}

	public List<TblTypeAPxkDTO> doSearchPXK(TblTypeAPxkDTO obj) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.TBL_TYPE_A_PXK_ID tblTypeAPxkId ");
		stringBuilder.append(",T1.MA_PHIEU_XUAT maPhieuXuat ");
		stringBuilder.append(",T1.MA_LENH_XUAT maLenhXuat ");
		stringBuilder.append(",T1.MA_YC_XUAT maYcXuat ");
		stringBuilder.append(",T1.NGUOI_TAO_YC nguoiTaoYc ");
		stringBuilder.append(",T1.NGUOI_PHE_DUYET nguoiPheDuyet ");
		stringBuilder.append(",T1.NGAY_TAO ngayTao ");
		stringBuilder.append(",T1.NGAY_THUC_XUAT ngayThucXuat ");
		stringBuilder.append(",T1.KHO_XUAT khoXuat ");
		stringBuilder.append(",T1.LY_DO_XUAT lyDoXuat ");
		stringBuilder.append(",T1.KHO_NHAN khoNhan ");
		stringBuilder.append(",T1.DON_VI_NHAN donViNhan ");
		stringBuilder.append(",T1.CONG_TRINH_NHAN congTrinhNhan ");
		stringBuilder.append(",T1.MA_TRAM_NHAN maTramNhan ");
		stringBuilder.append(",T1.TINH_TRANG tinhTrang ");
		stringBuilder.append(",T1.LY_DO_TU_CHOI lyDoTuChoi ");
		stringBuilder.append(",T1.MA_HDXL maHdxl ");
		stringBuilder.append(",T1.TINH_TRANG_KY_CA tinhTrangKyCa ");
		stringBuilder.append(",T1.XOA xoa,T1.PATH_IMG  pathImg, "
				+ "T1.KT_KV_TTKT ktKvttkt,T1.MA_CHI_NHANH maChiNhanh,T1.MA_NV maNv,"
				+ "T1.MA_TINH maTinh,T1.CHECK_NHAP_LIEU checkNhapLieu,"
				+ "T1.NGAY_GAN_DON_VI ngayGanDonVi,"
				+ "T1.NGAY_GAN_DON_VI ngayGanNv  ");
		stringBuilder.append("FROM TBL_TYPE_A_PXK T1 where 1=1   ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder.append(" AND (upper(T1.MA_PHIEU_XUAT) LIKE upper(:keySearch) escape '&') "
					+ "or (upper(T1.MA_LENH_XUAT) LIKE upper(:keySearch) escape '&') "
					+ "or (upper(T1.MA_YC_XUAT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.NGUOI_TAO_YC) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MA_TRAM_NHAN) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.MA_TINH) LIKE upper(:keySearch) escape '&')");
		}
		if(StringUtils.isNotEmpty(obj.getDonViNhan())){
			stringBuilder.append(" and upper(T1.DON_VI_NHAN) =upper(:donViNhan) ");
		}
		if (StringUtils.isNotEmpty(obj.getMaPhieuXuat())) {
			stringBuilder.append(" and upper(T1.MA_PHIEU_XUAT) like upper(:maPhieuXuat) escape '&'");
		}

		if (StringUtils.isNotEmpty(obj.getMaLenhXuat())) {
			stringBuilder.append(" and upper(T1.MA_LENH_XUAT) like upper(:maLenhXuat) escape '&'");
		}
		if(StringUtils.isNotEmpty(obj.getMaTinh())){
			stringBuilder.append(" and upper(T1.MA_TINH) =upper(:maTinh) ");
		}

		if (StringUtils.isNotEmpty(obj.getMaYcXuat())) {
			stringBuilder.append(" and upper(T1.MA_YC_XUAT) like upper(:maYcXuat) escape '&'");
		}
		
		if (StringUtils.isNotEmpty(obj.getMaTramNhan())) {
			stringBuilder.append(" and upper(T1.MA_TRAM_NHAN) = upper(:maTramNhan)");
		}
		
		if (StringUtils.isNotEmpty(obj.getNguoiTaoYc())) {
			stringBuilder.append(" and upper(T1.NGUOI_TAO_YC) like upper(:nguoiTaoYc) escape '&'");
		}
		if (obj.getNgayTaoFrom() != null && (obj.getNgayTaoTo() == null)) {
			stringBuilder.append(" AND T1.NGAY_TAO >= :ngayTaoFrom");
		}
		if ((obj.getNgayTaoFrom() != null) && (obj.getNgayTaoTo() != null)) {
			stringBuilder.append(" AND T1.NGAY_TAO between :ngayTaoFrom and :ngayTaoTo");
		}
		if (obj.getNgayThucXuatFrom() != null && (obj.getNgayThucXuatTo() == null)) {
			stringBuilder.append(" AND T1.NGAY_THUC_XUAT >= :ngayThucXuatFrom");
		}
		if ((obj.getNgayThucXuatFrom() != null) && (obj.getNgayThucXuatTo() != null)) {
			stringBuilder.append(" AND T1.NGAY_THUC_XUAT between :ngayThucXuatFrom and :ngayThucXuatTo");
		}
		
		if(StringUtils.isNotEmpty(obj.getDonVi())){
			stringBuilder.append(" AND (T1.MA_CHI_NHANH =:donVi or T1.MA_TINH=:donVi)");
		}
		if(obj.getCheckDv()!=null&&obj.getCheckDv()==0l){
			stringBuilder.append(" AND (T1.MA_CHI_NHANH is not null or T1.MA_TINH is not null)");
		}else if(obj.getCheckDv()!=null&&obj.getCheckDv()==1l){
			stringBuilder.append(" AND T1.MA_CHI_NHANH is null and T1.MA_TINH is null");
		}
		
//		if(obj.getCheckNhapLieu()!=null&&obj.getCheckNhapLieu()==0l){
//			stringBuilder.append(" AND T1.CHECK_NHAP_LIEU is not null ");
//		}else if(obj.getCheckNhapLieu()!=null&&obj.getCheckNhapLieu()==1l){
//			stringBuilder.append(" AND T1.CHECK_NHAP_LIEU is null ");
//		}
		
		if(obj.getCheckNl()!=null&&obj.getCheckNl()==0l){
			stringBuilder.append(" AND T1.CHECK_NHAP_LIEU  is not null ");
		}else if(obj.getCheckNl()!=null&&obj.getCheckNl()==1l){
			stringBuilder.append(" AND T1.CHECK_NHAP_LIEU  is null ");
		}
		if(obj.getCheckXnnl()!=null&&obj.getCheckXnnl()==0l){
			stringBuilder.append(" AND T1.CHECK_NHAP_LIEU  is not null and T1.CHECK_NHAP_LIEU!=0");
		}else if(obj.getCheckXnnl()!=null&&obj.getCheckXnnl()==1l){
			stringBuilder.append(" AND (T1.CHECK_NHAP_LIEU  is null or T1.CHECK_NHAP_LIEU=0)");
		}
		if(StringUtils.isNotEmpty(obj.getMaNv())){
			stringBuilder.append(" AND T1.MA_NV =:maNv ");
		}
		
		if(obj.getCheckGanNV()!=null&&obj.getCheckGanNV()==0l){
			stringBuilder.append(" AND T1.MA_NV  is not null");
		}else if(obj.getCheckGanNV()!=null&&obj.getCheckGanNV()==1l){
			stringBuilder.append(" AND T1.MA_NV  is null ");
		}
		
		if(obj.getCheckTaiAnh()!=null&&obj.getCheckTaiAnh()==0l){
			stringBuilder.append(" AND T1.PATH_IMG is not null");
		}else if(obj.getCheckTaiAnh()!=null&&obj.getCheckTaiAnh()==1l){
			stringBuilder.append(" AND T1.PATH_IMG  is null ");
		}
		
		
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblTypeAPxkId", new LongType());
		query.addScalar("maPhieuXuat", new StringType());
		query.addScalar("maLenhXuat", new StringType());
		query.addScalar("maYcXuat", new StringType());
		query.addScalar("nguoiTaoYc", new StringType());
		query.addScalar("nguoiPheDuyet", new StringType());
		query.addScalar("ngayTao", new DateType());
		query.addScalar("ngayThucXuat", new DateType());
		query.addScalar("khoXuat", new StringType());
		query.addScalar("lyDoXuat", new StringType());
		query.addScalar("khoNhan", new StringType());
		query.addScalar("donViNhan", new StringType());
		query.addScalar("congTrinhNhan", new StringType());
		query.addScalar("maTramNhan", new StringType());
		query.addScalar("tinhTrang", new StringType());
		query.addScalar("lyDoTuChoi", new StringType());
		query.addScalar("maHdxl", new StringType());
		query.addScalar("tinhTrangKyCa", new StringType());
		query.addScalar("xoa", new LongType());
		query.addScalar("ktKvttkt", new LongType());
		query.addScalar("pathImg",new StringType());
		query.addScalar("maChiNhanh", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("checkNhapLieu", new LongType());
		query.addScalar("ngayGanDonVi", new DateType());
		query.addScalar("ngayGanNv", new DateType());
		
		if(StringUtils.isNotEmpty(obj.getDonViNhan())){
			query.setParameter("donViNhan",obj.getDonViNhan());
			queryCount.setParameter("donViNhan", obj.getDonViNhan());
		}
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getMaPhieuXuat())) {
			query.setParameter("maPhieuXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaPhieuXuat()) + "%");
			queryCount.setParameter("maPhieuXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaPhieuXuat()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getMaLenhXuat())) {
			query.setParameter("maLenhXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaLenhXuat()) + "%");
			queryCount.setParameter("maLenhXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaLenhXuat()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getMaYcXuat())) {
			query.setParameter("maYcXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaYcXuat()) + "%");
			queryCount.setParameter("maYcXuat", "%" + ValidateUtils.validateKeySearch(obj.getMaYcXuat()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTramNhan())) {
			query.setParameter("maTramNhan",obj.getMaTramNhan());
			queryCount.setParameter("maTramNhan",obj.getMaTramNhan());
		}
		if (StringUtils.isNotEmpty(obj.getNguoiTaoYc())) {
			query.setParameter("nguoiTaoYc", "%" + ValidateUtils.validateKeySearch(obj.getNguoiTaoYc()) + "%");
			queryCount.setParameter("nguoiTaoYc", "%" + ValidateUtils.validateKeySearch(obj.getNguoiTaoYc()) + "%");
		}

		if (obj.getNgayTaoFrom() != null && (obj.getNgayTaoTo() == null)) {
			query.setTimestamp("ngayTaoFrom", obj.getNgayTaoFrom());
			queryCount.setTimestamp("ngayTaoFrom", obj.getNgayTaoFrom());
		}
		if ((obj.getNgayTaoFrom() != null) && (obj.getNgayTaoTo() != null)) {
			query.setTimestamp("ngayTaoFrom", obj.getNgayTaoFrom());
			queryCount.setTimestamp("ngayTaoFrom", obj.getNgayTaoFrom());

			query.setTimestamp("ngayTaoTo", obj.getNgayTaoTo());
			queryCount.setTimestamp("ngayTaoTo", obj.getNgayTaoTo());
		}
		
		if(StringUtils.isNotEmpty(obj.getMaNv())){
			query.setParameter("maNv", obj.getMaNv());
			queryCount.setParameter("maNv", obj.getMaNv());
		}

		if (obj.getNgayThucXuatFrom() != null && (obj.getNgayThucXuatTo() == null)) {
			query.setTimestamp("ngayThucXuatFrom", obj.getNgayThucXuatFrom());
			queryCount.setTimestamp("ngayThucXuatFrom", obj.getNgayThucXuatFrom());
		}
		if ((obj.getNgayThucXuatFrom() != null) && (obj.getNgayThucXuatTo() != null)) {
			query.setTimestamp("ngayThucXuatFrom", obj.getNgayThucXuatFrom());
			queryCount.setTimestamp("ngayThucXuatFrom", obj.getNgayThucXuatFrom());

			query.setTimestamp("ngayThucXuatTo", obj.getNgayThucXuatTo());
			queryCount.setTimestamp("ngayThucXuatTo", obj.getNgayThucXuatTo());
		}
		
		if(StringUtils.isNotEmpty(obj.getMaTinh())){
			query.setParameter("maTinh", obj.getMaTinh());
			queryCount.setParameter("maTinh", obj.getMaTinh());
		}
		
		
//		if(obj.getCheckDv()!=null&&obj.getCheckDv()==0l){
//			if(StringUtils.isNotEmpty(obj.getMaChiNhanh())){
//				query.setParameter("checkDv", obj.getMaChiNhanh());
//				queryCount.setParameter("checkDv", obj.getMaChiNhanh());
//			}else{
//				query.setParameter("checkDv", obj.getMaTinh());
//				queryCount.setParameter("checkDv", obj.getMaTinh());
//			}
//		}else if(obj.getCheckDv()!=null&&obj.getCheckDv()==1l){
//			if(StringUtils.isNotEmpty(obj.getMaChiNhanh())){
//				query.setParameter("checkDv", obj.getMaChiNhanh());
//				queryCount.setParameter("checkDv", obj.getMaChiNhanh());
//			}else{
//				query.setParameter("checkDv", obj.getMaTinh());
//				queryCount.setParameter("checkDv", obj.getMaTinh());
//			}
//		}
//		
//		
//		if(obj.getCheckNl()!=null&&obj.getCheckNl()==0l){
//			query.setParameter("checkNl", obj.getCheckNl());
//			queryCount.setParameter("checkNl", obj.getCheckNl());
//		}else if(obj.getCheckNl()!=null&&obj.getCheckNl()==1l){
//			query.setParameter("checkNl", obj.getCheckNl());
//			queryCount.setParameter("checkNl", obj.getCheckNl());
//		}
//		if(obj.getCheckXnnl()!=null&&obj.getCheckXnnl()==0l){
//			query.setParameter("checkXnnl", obj.getCheckXnnl());
//			queryCount.setParameter("checkXnnl", obj.getCheckXnnl());
//		}else if(obj.getCheckXnnl()!=null&&obj.getCheckXnnl()==1l){
//			query.setParameter("checkXnnl", obj.getCheckXnnl());
//			queryCount.setParameter("checkXnnl", obj.getCheckXnnl());
//		}
//		
//		if(obj.getCheckGanNV()!=null&&obj.getCheckGanNV()==0l){
//			query.setParameter("checkGanNV", obj.getCheckGanNV());
//			queryCount.setParameter("checkGanNV", obj.getCheckGanNV());
//		}else if(obj.getCheckGanNV()!=null&&obj.getCheckGanNV()==1l){
//			query.setParameter("checkGanNV", obj.getCheckGanNV());
//			queryCount.setParameter("checkGanNV", obj.getCheckGanNV());
//		}
//		
//		if(obj.getCheckTaiAnh()!=null&&obj.getCheckTaiAnh()==0l){
//			query.setParameter("checkTaiAnh", obj.getCheckTaiAnh());
//			queryCount.setParameter("checkTaiAnh", obj.getCheckTaiAnh());
//		}else if(obj.getCheckTaiAnh()!=null&&obj.getCheckTaiAnh()==1l){
//			query.setParameter("checkTaiAnh", obj.getCheckTaiAnh());
//			queryCount.setParameter("checkTaiAnh", obj.getCheckTaiAnh());
//		}
		
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();

	}

	public List<TblTypeAPxkDTO> getAllMaHdxl(TblTypeAPxkDTO obj) {

		StringBuilder sql = new StringBuilder(
					" select ty.SO_PHIEU_XUAT soPhieuXuat"
						+ ",ty.MA_CONG_TRINH maCongTrinh"
						+ ",ty.NGAY_XUAT ngayXuat"
						+ ",ty.TEN_VAT_TU tenVatTu"
						+ ",ty.MA_VAT_TU maVatTu"
						+ ",ty.DON_VI_TINH donViTinh"
						+ ",ty.SO_LUONG_XUAT soLuongXuat"
						+ ",ty.DON_GIA donGia"
						+ ",ty.THANH_TIEN thanhTien"
						+ ",ty.SL_THUC_TE_TC slThucTeTC"
						+ ",ty.TT_THUC_TE_TC ttThucTeTC"
						+ ",ty.SL_THU_HOI slThuHoi"
						+ ",ty.TT_THU_HOI ttThuHoi"
						+ ",ty.SL_TIEN_DEN slTienDen"
						+ ",ty.TT_TIEN_DEN ttTienDen"
						+ ",ta.MA_HDXL maHdxl"
						+ ",ta.MA_TRAM_NHAN maTramNhan "
						+ " from TBL_TYPE_A_PXK ta  join TBL_QLTS_THUC_XUAT_THEO_KY  ty on ty.SO_PHIEU_XUAT=ta.MA_PHIEU_XUAT "
						+ "where ta.MA_HDXL=:maHdxl and ty.SO_PHIEU_XUAT=:soPhieuXuat ");

		sql.append(" ORDER BY ty.SO_PHIEU_XUAT");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("maCongTrinh", new StringType());
		query.addScalar("ngayXuat", new DateType());
		query.addScalar("tenVatTu", new StringType());
		
		query.addScalar("maVatTu", new StringType());
		query.addScalar("donViTinh", new StringType());
		query.addScalar("soLuongXuat", new FloatType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("thanhTien", new FloatType());
		query.addScalar("slThucTeTC", new FloatType());
		query.addScalar("ttThucTeTC", new FloatType());
		
		query.addScalar("slThuHoi", new FloatType());
		query.addScalar("ttThuHoi", new FloatType());
		query.addScalar("slTienDen", new FloatType());
		query.addScalar("ttTienDen", new FloatType());

		query.addScalar("maHdxl", new StringType());
		query.addScalar("maTramNhan", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		query.setParameter("maHdxl", obj.getMaHdxl());
		query.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
		return query.list();
	}
	
	
	public TblTypeAPxkDTO sumMaHdxl(TblTypeAPxkDTO obj) {

		StringBuilder sql = new StringBuilder(
					" select sum(ty.SO_LUONG_XUAT) soLuongXuat"
						+ ",sum(ty.DON_GIA) donGia"
						+ ",sum(ty.THANH_TIEN) thanhTien"
						+ ",sum(ty.SL_THUC_TE_TC) slThucTeTC"
						+ ",sum(ty.TT_THUC_TE_TC) ttThucTeTC"
						+ ",sum(ty.SL_THU_HOI) slThuHoi"
						+ ",sum(ty.TT_THU_HOI) ttThuHoi"
						+ ",sum(ty.SL_TIEN_DEN) slTienDen"
						+ ",sum(ty.TT_TIEN_DEN) ttTienDen"
						+ " from TBL_TYPE_A_PXK ta  join TBL_QLTS_THUC_XUAT_THEO_KY  ty on ty.SO_PHIEU_XUAT=ta.MA_PHIEU_XUAT "
						+ "where ta.MA_HDXL=:maHdxl ");

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

		
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		query.setParameter("maHdxl", obj.getMaHdxl());
//		query.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
		return (TblTypeAPxkDTO) query.uniqueResult();
	}
	
	public List<TblTypeAPxkDTO> getAllMaHdxlDistinct(TblTypeAPxkDTO obj) {

		StringBuilder sql = new StringBuilder(
					" select distinct ty.SO_PHIEU_XUAT soPhieuXuat,ta.MA_HDXL maHdxl "
						+ " from TBL_TYPE_A_PXK ta  join TBL_QLTS_THUC_XUAT_THEO_KY  ty on ty.SO_PHIEU_XUAT=ta.MA_PHIEU_XUAT "
						+ "where ta.MA_HDXL=:maHdxl ");
		if(StringUtils.isNotEmpty(obj.getMaTramNhan())){
			sql.append("and MA_TRAM_NHAN =:maTramNhan");
		}
		if ((obj.getNgayThucXuatFrom() != null) && (obj.getNgayThucXuatTo() != null)) {
			sql.append(" AND NGAY_THUC_XUAT between :ngayThucXuatFrom and :ngayThucXuatTo");
		}
		sql.append(" ORDER BY ty.SO_PHIEU_XUAT");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("soPhieuXuat", new StringType());
		query.addScalar("maHdxl", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		if ((obj.getNgayThucXuatFrom() != null) && (obj.getNgayThucXuatTo() != null)) {
			query.setTimestamp("ngayThucXuatFrom", obj.getNgayThucXuatFrom());
			query.setTimestamp("ngayThucXuatTo", obj.getNgayThucXuatTo());
		}
		if(StringUtils.isNotEmpty(obj.getMaTramNhan())){
			query.setParameter("maTramNhan", obj.getMaTramNhan());
		}
		
		query.setParameter("maHdxl", obj.getMaHdxl());
//		query.setParameter("soPhieuXuat", obj.getSoPhieuXuat());
		return query.list();
	}
	
	
	public List<TblTypeAPxkDTO> checkMaHdxl(TblTypeAPxkDTO obj) {

		StringBuilder sql = new StringBuilder(
					" select ta.MA_HDXL maHdxl "
						+ " from TBL_TYPE_A_PXK  ta "
						+ "where ta.MA_HDXL=:maHdxl ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("maHdxl", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		query.setParameter("maHdxl", obj.getMaHdxl());
		return query.list();
	}
	
	
	public TblTypeAPxkDTO getSoPXKStt(TblTypeAPxkDTO obj) {

		StringBuilder sql = new StringBuilder(
					" select ty.SO_PHIEU_XUAT soPhieuXuat "
						+ " from TBL_TYPE_A_PXK ta  join TBL_QLTS_THUC_XUAT_THEO_KY  ty on ty.SO_PHIEU_XUAT=ta.MA_PHIEU_XUAT "
						+ "where ta.MA_HDXL=:maHdxl and ty.SO_PHIEU_XUAT=:soPhieuXuat ");

		sql.append(" ORDER BY ty.SO_PHIEU_XUAT");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("soPhieuXuat", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		query.setParameter("maHdxl", obj.getMaHdxl());
		query.setParameter("soPhieuXuat", obj.getMaHdxl());
		return (TblTypeAPxkDTO) query.uniqueResult();
	}

	public TblTypeAPxkDTO findByValue(String value) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.TBL_TYPE_A_PXK_ID tblTypeAPxkId ");
		stringBuilder.append(",T1.MA_PHIEU_XUAT maPhieuXuat ");
		stringBuilder.append(",T1.MA_LENH_XUAT maLenhXuat ");
		stringBuilder.append(",T1.MA_YC_XUAT maYcXuat ");
		stringBuilder.append(",T1.NGUOI_TAO_YC nguoiTaoYc ");
		stringBuilder.append(",T1.NGUOI_PHE_DUYET nguoiPheDuyet ");
		stringBuilder.append(",T1.NGAY_TAO ngayTao ");
		stringBuilder.append(",T1.NGAY_THUC_XUAT ngayThucXuat ");
		stringBuilder.append(",T1.KHO_XUAT khoXuat ");
		stringBuilder.append(",T1.LY_DO_XUAT lyDoXuat ");
		stringBuilder.append(",T1.KHO_NHAN khoNhan ");
		stringBuilder.append(",T1.DON_VI_NHAN donViNhan ");
		stringBuilder.append(",T1.CONG_TRINH_NHAN congTrinhNhan ");
		stringBuilder.append(",T1.MA_TRAM_NHAN maTramNhan ");
		stringBuilder.append(",T1.TINH_TRANG tinhTrang ");
		stringBuilder.append(",T1.LY_DO_TU_CHOI lyDoTuChoi ");
		stringBuilder.append(",T1.MA_HDXL maHdxl ");
		stringBuilder.append(",T1.TINH_TRANG_KY_CA tinhTrangKyCa ");
		stringBuilder.append(",T1.XOA xoa ");

		stringBuilder.append("FROM TBL_TYPE_A_PXK T1 ");
		stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND upper(T1.VALUE) = upper(:value)");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("tblTypeAPxkId", new LongType());
		query.addScalar("maPhieuXuat", new StringType());
		query.addScalar("maLenhXuat", new StringType());
		query.addScalar("maYcXuat", new StringType());
		query.addScalar("nguoiTaoYc", new StringType());
		query.addScalar("nguoiPheDuyet", new StringType());
		query.addScalar("ngayTao", new DateType());
		query.addScalar("ngayThucXuat", new DateType());
		query.addScalar("khoXuat", new StringType());
		query.addScalar("lyDoXuat", new StringType());
		query.addScalar("khoNhan", new StringType());
		query.addScalar("donViNhan", new StringType());
		query.addScalar("congTrinhNhan", new StringType());
		query.addScalar("maTramNhan", new StringType());
		query.addScalar("tinhTrang", new StringType());
		query.addScalar("lyDoTuChoi", new StringType());
		query.addScalar("maHdxl", new StringType());
		query.addScalar("tinhTrangKyCa", new StringType());
		query.addScalar("xoa", new LongType());

		query.setParameter("value", value);
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		return (TblTypeAPxkDTO) query.uniqueResult();
	}

	@Transactional
	public String saveList1(List<TblTypeAPxkBO> obj) {
		try {
			for (TblTypeAPxkBO item : obj) {
				getSession().save(item);
				System.out.println("ok");
			}

		} catch (SecurityException ex) {
			return ex.getMessage();
		} catch (MappingException e) {
			return e.getMessage();
		}
		return "SUCCESS";
	}
	
	public Long updateTypeA(TblTypeAPxkDTO obj) {
		try {
			
			String sql2 = "update TBL_TYPE_A_PXK set KT_KV_TTKT=:ktKvttkt,MA_CHI_NHANH=:maChinhanh,MA_TINH=:maTinh,NGAY_GAN_DON_VI =:ngayGanDonVi where TBL_TYPE_A_PXK_ID =:tblTypeAPxkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("ktKvttkt", obj.getKtKvttkt());
			query2.setParameter("tblTypeAPxkId", obj.getTblTypeAPxkId());
			query2.setParameter("maChinhanh", obj.getMaChiNhanh());
			query2.setParameter("maTinh", obj.getMaTinh());
			query2.setParameter("ngayGanDonVi", obj.getNgayGanDonVi());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	public Long updateTypeNV(TblTypeAPxkDTO obj) {
		try {
			
			String sql2 = "update TBL_TYPE_A_PXK set MA_NV=:maNv,NGAY_GAN_NV=:ngayGanNv where TBL_TYPE_A_PXK_ID =:tblTypeAPxkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("tblTypeAPxkId", obj.getTblTypeAPxkId());
			query2.setParameter("maNv", obj.getMaNv());
			query2.setParameter("ngayGanNv", obj.getNgayGanNv());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	public Long updatePath(TblTypeAPxkDTO obj) {
		try {
			
			String sql2 = "update TBL_TYPE_A_PXK set PATH_IMG=:pathImg where TBL_TYPE_A_PXK_ID =:tblTypeAPxkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("pathImg", obj.getPathImg());
			query2.setParameter("tblTypeAPxkId", obj.getTblTypeAPxkId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TblTypeAPxkDTO> getForAutoCompleteTramNhan(TblTypeAPxkDTO obj) {
		String sql = "SELECT MA_TRAM_NHAN maTramNhan from TBL_TYPE_A_PXK where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getMaTramNhan())) {
			stringBuilder.append(
					" AND upper(MA_TRAM_NHAN) LIKE upper(:maTramNhan) escape '&' ");
		}

		stringBuilder.append(" ORDER BY MA_TRAM_NHAN");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("maTramNhan", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		if (StringUtils.isNotEmpty(obj.getMaTramNhan())) {
			query.setParameter("maTramNhan", "%" + ValidateUtils.validateKeySearch(obj.getMaTramNhan()) + "%");
		}

		return query.list();
	}
	
	public Long updateCheck(TblTypeAPxkDTO obj) {
		try {
			
			String sql2 = "update TBL_TYPE_A_PXK set CHECK_NHAP_LIEU=:checkNhapLieu where TBL_TYPE_A_PXK_ID =:tblTypeAPxkId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("checkNhapLieu", obj.getCheckNhapLieu());
			query2.setParameter("tblTypeAPxkId", obj.getTblTypeAPxkId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TblTypeAPxkDTO> getForAutoCompleteHDXL(TblTypeAPxkDTO obj) {
		String sql = "SELECT MA_HDXL maHdxl from TBL_TYPE_A_PXK where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getMaHdxl())) {
			stringBuilder.append(
					" AND upper(MA_HDXL) LIKE upper(:maHdxl) escape '&' ");
		}

		stringBuilder.append(" ORDER BY MA_HDXL");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("maHdxl", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));

		if (StringUtils.isNotEmpty(obj.getMaHdxl())) {
			query.setParameter("maHdxl", "%" + ValidateUtils.validateKeySearch(obj.getMaHdxl()) + "%");
		}

		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TblTypeAPxkDTO> checkDup(TblTypeAPxkDTO obj) {
		String sql = "SELECT MA_PHIEU_XUAT maPhieuXuat from TBL_TYPE_A_PXK  where  MA_PHIEU_XUAT is not null and MA_PHIEU_XUAT=:maPhieuXuat";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("maPhieuXuat", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblTypeAPxkDTO.class));
		query.setParameter("maPhieuXuat", obj.getMaPhieuXuat());
		return query.list();
	}
	
	public Long deleteObj(TblTypeAPxkDTO obj) {
		try {
			String sql2 = "delete from TBL_TYPE_A_PXK  where  MA_PHIEU_XUAT=:maPhieuXuat ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maPhieuXuat", obj.getMaPhieuXuat());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
}
