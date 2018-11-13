package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viettel.erp.constant.ApplicationConstants;
import com.viettel.qll.bo.TblKHuyenKho1BO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_K_HUYEN_KHO_1BO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblKHuyenKho1DTO extends QllBaseDTO<TblKHuyenKho1BO> {

	private java.lang.Long tblKHuyenKhoId;
	private java.lang.String maDonVi;
	private java.lang.Float kiKhoKhan;
	private java.lang.String deXuatLoaiHuyen;
	private java.lang.Long nhomDoiTuong;
	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.String thang;
	private java.lang.String tinh;
	private String tenDonVi;
	private String exThang;
	private String exNam;

	@Override
	public TblKHuyenKho1BO toModel() {
		TblKHuyenKho1BO tblKHuyenKho1BO = new TblKHuyenKho1BO();
		tblKHuyenKho1BO.setTblKHuyenKhoId(this.tblKHuyenKhoId);
		tblKHuyenKho1BO.setMaDonVi(this.maDonVi);
		tblKHuyenKho1BO.setKiKhoKhan(this.kiKhoKhan);
		tblKHuyenKho1BO.setDeXuatLoaiHuyen(this.deXuatLoaiHuyen);
		tblKHuyenKho1BO.setNhomDoiTuong(this.nhomDoiTuong);
		tblKHuyenKho1BO.setHoatDong(this.hoatDong);
		tblKHuyenKho1BO.setXoa(this.xoa);
		tblKHuyenKho1BO.setThang(this.thang);
		tblKHuyenKho1BO.setTinh(this.tinh);
		tblKHuyenKho1BO.setTenDonVi(this.tenDonVi);
		return tblKHuyenKho1BO;
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

	@JsonProperty("kKhoKhan")
	public java.lang.Float getKiKhoKhan() {
		return kiKhoKhan;
	}

	public void setKiKhoKhan(java.lang.Float kiKhoKhan) {
		this.kiKhoKhan = kiKhoKhan;
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

	@JsonProperty("hoatDong")
	public java.lang.Long getHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(java.lang.Long hoatDong) {
		this.hoatDong = hoatDong;
	}

	@JsonProperty("xoa")
	public java.lang.Long getXoa() {
		return xoa;
	}

	public void setXoa(java.lang.Long xoa) {
		this.xoa = xoa;
	}

	@JsonProperty("thang")
	public java.lang.String getThang() {
		return thang;
	}

	public void setThang(java.lang.String thang) {
		this.thang = thang;
	}

	@JsonProperty("tinh")
	public java.lang.String getTinh() {
		return tinh;
	}

	public void setTinh(java.lang.String tinh) {
		this.tinh = tinh;
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

	public String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

}
