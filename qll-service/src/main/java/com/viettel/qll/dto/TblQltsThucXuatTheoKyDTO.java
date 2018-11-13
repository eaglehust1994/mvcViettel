package com.viettel.qll.dto;

import com.viettel.qll.bo.TblQltsThucXuatTheoKyBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import java.util.List;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_QLTS_THUC_XUAT_THEO_KYBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblQltsThucXuatTheoKyDTO extends QllBaseDTO<TblQltsThucXuatTheoKyBO> {

	private java.lang.Long tblQltsThucXuatTheoKyId;
	private java.lang.String soPhieuXuat;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayXuat;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayXuatFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayXuatTo;
	private java.lang.String noiDung;
	private java.lang.String maKhoXuat;
	private java.lang.String tenVatTu;
	private java.lang.String maVatTu;
	private java.lang.String donViTinh;
	private java.lang.Float soLuongXuat;
	private java.lang.Float donGia;
	private java.lang.Float thanhTien;
	private java.lang.String maCongTrinh;
	private java.lang.String nguoiNhanHang;
	private java.lang.Float soLuongYeuCau;
	private java.lang.String lyDo;
	private java.lang.String dienGiai;
	private java.lang.String tenKhoXuat;
	private java.lang.String maKhoNhan;
	private java.lang.Long xoa;
	private java.lang.Long hoatDong;
	private String soPhieuXuatBenLogistic;
	private String khoNhan;
	private String donViNhan;
	private java.lang.String maTinh;
	private java.lang.String maChiNhanh;
	private java.lang.String maNv;
	private java.lang.Float slThucTeTC;

	private java.lang.Float ttThucTeTC;

	private java.lang.Float slThuHoi;

	private java.lang.Float ttThuHoi;

	private java.lang.Float slTienDen;

	private java.lang.Float ttTienDen;
	
	private String hoVaTen;
	
	private String maHdxl;
	
	private String congTrinhNhan;
	private String maTramNhan;
	
	private List<TblQltsThucXuatTheoKyDTO> lstThucXuat;
	private java.lang.Long tblTypeAPxkId;
	private java.lang.Long checkNhapLieu;

	@Override
	public TblQltsThucXuatTheoKyBO toModel() {
		TblQltsThucXuatTheoKyBO tblQltsThucXuatTheoKyBO = new TblQltsThucXuatTheoKyBO();
		tblQltsThucXuatTheoKyBO.setTblQltsThucXuatTheoKyId(this.tblQltsThucXuatTheoKyId);
		tblQltsThucXuatTheoKyBO.setSoPhieuXuat(this.soPhieuXuat);
		tblQltsThucXuatTheoKyBO.setNgayXuat(this.ngayXuat);
		tblQltsThucXuatTheoKyBO.setNoiDung(this.noiDung);
		tblQltsThucXuatTheoKyBO.setMaKhoXuat(this.maKhoXuat);
		tblQltsThucXuatTheoKyBO.setTenVatTu(this.tenVatTu);
		tblQltsThucXuatTheoKyBO.setMaVatTu(this.maVatTu);
		tblQltsThucXuatTheoKyBO.setDonViTinh(this.donViTinh);
		tblQltsThucXuatTheoKyBO.setSoLuongXuat(this.soLuongXuat);
		tblQltsThucXuatTheoKyBO.setDonGia(this.donGia);
		tblQltsThucXuatTheoKyBO.setThanhTien(this.thanhTien);
		tblQltsThucXuatTheoKyBO.setMaCongTrinh(this.maCongTrinh);
		tblQltsThucXuatTheoKyBO.setNguoiNhanHang(this.nguoiNhanHang);
		tblQltsThucXuatTheoKyBO.setSoLuongYeuCau(this.soLuongYeuCau);
		tblQltsThucXuatTheoKyBO.setLyDo(this.lyDo);
		tblQltsThucXuatTheoKyBO.setDienGiai(this.dienGiai);
		tblQltsThucXuatTheoKyBO.setTenKhoXuat(this.tenKhoXuat);
		tblQltsThucXuatTheoKyBO.setMaKhoNhan(this.maKhoNhan);
		tblQltsThucXuatTheoKyBO.setXoa(this.xoa);
		tblQltsThucXuatTheoKyBO.setHoatDong(this.hoatDong);
		tblQltsThucXuatTheoKyBO.setSoPhieuXuatBenLogistic(this.soPhieuXuatBenLogistic);
		tblQltsThucXuatTheoKyBO.setKhoNhan(this.khoNhan);
		tblQltsThucXuatTheoKyBO.setDonViNhan(this.donViNhan);
		tblQltsThucXuatTheoKyBO.setMaChiNhanh(this.maChiNhanh);
		tblQltsThucXuatTheoKyBO.setMaTinh(this.maTinh);
		tblQltsThucXuatTheoKyBO.setMaNv(this.maNv);
		tblQltsThucXuatTheoKyBO.setSlThucTeTC(this.slThucTeTC);
		tblQltsThucXuatTheoKyBO.setTtThucTeTC(this.ttThucTeTC);
		tblQltsThucXuatTheoKyBO.setSlThuHoi(this.slThuHoi);
		tblQltsThucXuatTheoKyBO.setTtThuHoi(this.ttThuHoi);
		tblQltsThucXuatTheoKyBO.setSlTienDen(this.slTienDen);
		tblQltsThucXuatTheoKyBO.setTtTienDen(this.ttTienDen);
		return tblQltsThucXuatTheoKyBO;
	}

	@Override
	public Long getFWModelId() {
		return tblQltsThucXuatTheoKyId;
	}

	@Override
	public String catchName() {
		return getTblQltsThucXuatTheoKyId().toString();
	}

	@JsonProperty("tblQltsThucXuatTheoKyId")
	public java.lang.Long getTblQltsThucXuatTheoKyId() {
		return tblQltsThucXuatTheoKyId;
	}

	public void setTblQltsThucXuatTheoKyId(java.lang.Long tblQltsThucXuatTheoKyId) {
		this.tblQltsThucXuatTheoKyId = tblQltsThucXuatTheoKyId;
	}

	@JsonProperty("soPhieuXuat")
	public java.lang.String getSoPhieuXuat() {
		return soPhieuXuat;
	}

	public void setSoPhieuXuat(java.lang.String soPhieuXuat) {
		this.soPhieuXuat = soPhieuXuat;
	}

	@JsonProperty("ngayXuat")
	public java.util.Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(java.util.Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public java.util.Date getNgayXuatFrom() {
		return ngayXuatFrom;
	}

	public void setNgayXuatFrom(java.util.Date ngayXuatFrom) {
		this.ngayXuatFrom = ngayXuatFrom;
	}

	public java.util.Date getNgayXuatTo() {
		return ngayXuatTo;
	}

	public void setNgayXuatTo(java.util.Date ngayXuatTo) {
		this.ngayXuatTo = ngayXuatTo;
	}

	@JsonProperty("noiDung")
	public java.lang.String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(java.lang.String noiDung) {
		this.noiDung = noiDung;
	}

	@JsonProperty("maKhoXuat")
	public java.lang.String getMaKhoXuat() {
		return maKhoXuat;
	}

	public void setMaKhoXuat(java.lang.String maKhoXuat) {
		this.maKhoXuat = maKhoXuat;
	}

	@JsonProperty("tenVatTu")
	public java.lang.String getTenVatTu() {
		return tenVatTu;
	}

	public void setTenVatTu(java.lang.String tenVatTu) {
		this.tenVatTu = tenVatTu;
	}

	@JsonProperty("maVatTu")
	public java.lang.String getMaVatTu() {
		return maVatTu;
	}

	public void setMaVatTu(java.lang.String maVatTu) {
		this.maVatTu = maVatTu;
	}

	@JsonProperty("donViTinh")
	public java.lang.String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(java.lang.String donViTinh) {
		this.donViTinh = donViTinh;
	}

	@JsonProperty("soLuongXuat")
	public java.lang.Float getSoLuongXuat() {
		return soLuongXuat;
	}

	public void setSoLuongXuat(java.lang.Float soLuongXuat) {
		this.soLuongXuat = soLuongXuat;
	}

	@JsonProperty("donGia")
	public java.lang.Float getDonGia() {
		return donGia;
	}

	public void setDonGia(java.lang.Float donGia) {
		this.donGia = donGia;
	}

	@JsonProperty("thanhTien")
	public java.lang.Float getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(java.lang.Float thanhTien) {
		this.thanhTien = thanhTien;
	}

	@JsonProperty("maCongTrinh")
	public java.lang.String getMaCongTrinh() {
		return maCongTrinh;
	}

	public void setMaCongTrinh(java.lang.String maCongTrinh) {
		this.maCongTrinh = maCongTrinh;
	}

	@JsonProperty("nguoiNhanHang")
	public java.lang.String getNguoiNhanHang() {
		return nguoiNhanHang;
	}

	public void setNguoiNhanHang(java.lang.String nguoiNhanHang) {
		this.nguoiNhanHang = nguoiNhanHang;
	}

	@JsonProperty("soLuongYeuCau")
	public java.lang.Float getSoLuongYeuCau() {
		return soLuongYeuCau;
	}

	public void setSoLuongYeuCau(java.lang.Float soLuongYeuCau) {
		this.soLuongYeuCau = soLuongYeuCau;
	}

	@JsonProperty("lyDo")
	public java.lang.String getLyDo() {
		return lyDo;
	}

	public void setLyDo(java.lang.String lyDo) {
		this.lyDo = lyDo;
	}

	@JsonProperty("dienGiai")
	public java.lang.String getDienGiai() {
		return dienGiai;
	}

	public void setDienGiai(java.lang.String dienGiai) {
		this.dienGiai = dienGiai;
	}

	@JsonProperty("tenKhoXuat")
	public java.lang.String getTenKhoXuat() {
		return tenKhoXuat;
	}

	public void setTenKhoXuat(java.lang.String tenKhoXuat) {
		this.tenKhoXuat = tenKhoXuat;
	}

	@JsonProperty("maKhoNhan")
	public java.lang.String getMaKhoNhan() {
		return maKhoNhan;
	}

	public void setMaKhoNhan(java.lang.String maKhoNhan) {
		this.maKhoNhan = maKhoNhan;
	}

	@JsonProperty("xoa")
	public java.lang.Long getXoa() {
		return xoa;
	}

	public void setXoa(java.lang.Long xoa) {
		this.xoa = xoa;
	}

	@JsonProperty("hoatDong")
	public java.lang.Long getHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(java.lang.Long hoatDong) {
		this.hoatDong = hoatDong;
	}

	@JsonProperty("soPhieuXuatBenLogistic")
	public String getSoPhieuXuatBenLogistic() {
		return soPhieuXuatBenLogistic;
	}

	public void setSoPhieuXuatBenLogistic(String soPhieuXuatBenLogistic) {
		this.soPhieuXuatBenLogistic = soPhieuXuatBenLogistic;
	}

	@JsonProperty("khoNhan")
	public String getKhoNhan() {
		return khoNhan;
	}

	public void setKhoNhan(String khoNhan) {
		this.khoNhan = khoNhan;
	}

	@JsonProperty("donViNhan")
	public String getDonViNhan() {
		return donViNhan;
	}

	public void setDonViNhan(String donViNhan) {
		this.donViNhan = donViNhan;
	}

	@JsonProperty("maTinh")
	public java.lang.String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(java.lang.String maTinh) {
		this.maTinh = maTinh;
	}

	@JsonProperty("maChiNhanh")
	public java.lang.String getMaChiNhanh() {
		return maChiNhanh;
	}

	public void setMaChiNhanh(java.lang.String maChiNhanh) {
		this.maChiNhanh = maChiNhanh;
	}

	@JsonProperty("maNv")
	public java.lang.String getMaNv() {
		return maNv;
	}

	public void setMaNv(java.lang.String maNv) {
		this.maNv = maNv;
	}

	@JsonProperty("slThucTeTC")
	public java.lang.Float getSlThucTeTC() {
		return slThucTeTC;
	}

	public void setSlThucTeTC(java.lang.Float slThucTeTC) {
		this.slThucTeTC = slThucTeTC;
	}
	@JsonProperty("ttThucTeTC")
	public java.lang.Float getTtThucTeTC() {
		return ttThucTeTC;
	}

	public void setTtThucTeTC(java.lang.Float ttThucTeTC) {
		this.ttThucTeTC = ttThucTeTC;
	}
	@JsonProperty("slThuHoi")
	public java.lang.Float getSlThuHoi() {
		return slThuHoi;
	}

	public void setSlThuHoi(java.lang.Float slThuHoi) {
		this.slThuHoi = slThuHoi;
	}
	@JsonProperty("ttThuHoi")
	public java.lang.Float getTtThuHoi() {
		return ttThuHoi;
	}

	public void setTtThuHoi(java.lang.Float ttThuHoi) {
		this.ttThuHoi = ttThuHoi;
	}
	@JsonProperty("slTienDen")
	public java.lang.Float getSlTienDen() {
		return slTienDen;
	}

	public void setSlTienDen(java.lang.Float slTienDen) {
		this.slTienDen = slTienDen;
	}
	@JsonProperty("ttTienDen")
	public java.lang.Float getTtTienDen() {
		return ttTienDen;
	}

	public void setTtTienDen(java.lang.Float ttTienDen) {
		this.ttTienDen = ttTienDen;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getMaHdxl() {
		return maHdxl;
	}

	public void setMaHdxl(String maHdxl) {
		this.maHdxl = maHdxl;
	}

	public List<TblQltsThucXuatTheoKyDTO> getLstThucXuat() {
		return lstThucXuat;
	}

	public void setLstThucXuat(List<TblQltsThucXuatTheoKyDTO> lstThucXuat) {
		this.lstThucXuat = lstThucXuat;
	}

	public String getCongTrinhNhan() {
		return congTrinhNhan;
	}

	public void setCongTrinhNhan(String congTrinhNhan) {
		this.congTrinhNhan = congTrinhNhan;
	}

	public java.lang.Long getTblTypeAPxkId() {
		return tblTypeAPxkId;
	}

	public void setTblTypeAPxkId(java.lang.Long tblTypeAPxkId) {
		this.tblTypeAPxkId = tblTypeAPxkId;
	}

	public java.lang.Long getCheckNhapLieu() {
		return checkNhapLieu;
	}

	public void setCheckNhapLieu(java.lang.Long checkNhapLieu) {
		this.checkNhapLieu = checkNhapLieu;
	}
	
	public String getMaTramNhan() {
		return maTramNhan;
	}

	public void setMaTramNhan(String maTramNhan) {
		this.maTramNhan = maTramNhan;
	}

	
}
