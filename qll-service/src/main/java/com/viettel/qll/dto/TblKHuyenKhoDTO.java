package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viettel.qll.bo.TblKHuyenKhoBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_K_HUYEN_KHOBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblKHuyenKhoDTO extends QllBaseDTO<TblKHuyenKhoBO> {

	private java.lang.Long xoa;
	private java.lang.String tinh;
	private java.lang.String thang;
	private java.lang.String tenDonVi;
	private java.lang.Long tblKHuyenKhoId;
	private java.lang.String maDonVi;
	private java.lang.Float kKhoKhan;
	private java.lang.Long hoatDong;
	private java.lang.String deXuatLoaiHuyen;
	private java.lang.Long nhomDoiTuong;
	private String exThang;
	private String exNam;

	@Override
	public TblKHuyenKhoBO toModel() {
		TblKHuyenKhoBO tblKHuyenKhoBO = new TblKHuyenKhoBO();
		tblKHuyenKhoBO.setXoa(this.xoa);
		tblKHuyenKhoBO.setTinh(this.tinh);
		tblKHuyenKhoBO.setThang(this.thang);
		tblKHuyenKhoBO.setTenDonVi(this.tenDonVi);
		tblKHuyenKhoBO.setTblKHuyenKhoId(this.tblKHuyenKhoId);
		tblKHuyenKhoBO.setMaDonVi(this.maDonVi);
		tblKHuyenKhoBO.setKKhoKhan(this.kKhoKhan);
		tblKHuyenKhoBO.setHoatDong(this.hoatDong);
		tblKHuyenKhoBO.setDeXuatLoaiHuyen(this.deXuatLoaiHuyen);
		tblKHuyenKhoBO.setNhomDoiTuong(this.nhomDoiTuong);
		return tblKHuyenKhoBO;
	}

	@JsonProperty("xoa")
	public java.lang.Long getXoa() {
		return xoa;
	}

	public void setXoa(java.lang.Long xoa) {
		this.xoa = xoa;
	}

	@JsonProperty("tinh")
	public java.lang.String getTinh() {
		return tinh;
	}

	public void setTinh(java.lang.String tinh) {
		this.tinh = tinh;
	}

	@JsonProperty("thang")
	public java.lang.String getThang() {
		return thang;
	}

	public void setThang(java.lang.String thang) {
		this.thang = thang;
	}

	@JsonProperty("tenDonVi")
	public java.lang.String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(java.lang.String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	@Override
	public Long getFWModelId() {
		return tblKHuyenKhoId;
	}

	@Override
	public String catchName() {
		return getTblKHuyenKhoId().toString();
	}

	@JsonProperty("tblKHuyenKhoId")
	public java.lang.Long getTblKHuyenKhoId() {
		return tblKHuyenKhoId;
	}

	public void setTblKHuyenKhoId(java.lang.Long tblKHuyenKhoId) {
		this.tblKHuyenKhoId = tblKHuyenKhoId;
	}

	@JsonProperty("maDonVi")
	public java.lang.String getMaDonVi() {
		return maDonVi;
	}

	public void setMaDonVi(java.lang.String maDonVi) {
		this.maDonVi = maDonVi;
	}

	@JsonProperty("hoatDong")
	public java.lang.Long getHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(java.lang.Long hoatDong) {
		this.hoatDong = hoatDong;
	}

	@JsonProperty("deXuatLoaiHuyen")
	public java.lang.String getDeXuatLoaiHuyen() {
		return deXuatLoaiHuyen;
	}

	public void setDeXuatLoaiHuyen(java.lang.String deXuatLoaiHuyen) {
		this.deXuatLoaiHuyen = deXuatLoaiHuyen;
	}

	@JsonProperty("nhomDoiTuong")
	public java.lang.Long getNhomDoiTuong() {
		return nhomDoiTuong;
	}

	public void setNhomDoiTuong(java.lang.Long nhomDoiTuong) {
		this.nhomDoiTuong = nhomDoiTuong;
	}
	
	@JsonProperty("kKhoKhan")
	public java.lang.Float getKKhoKhan() {
		return kKhoKhan;
	}

	public void setKKhoKhan(java.lang.Float kKhoKhan) {
		this.kKhoKhan = kKhoKhan;
	}

	public String getExThang() {
		return exThang;
	}

	public void setExThang(String exThang) {
		this.exThang = exThang;
	}

	public String getExNam() {
		return exNam;
	}

	public void setExNam(String exNam) {
		this.exNam = exNam;
	}

	
}
