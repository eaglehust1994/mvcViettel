package com.viettel.qll.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viettel.qll.bo.TblKdcQuyLuongBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_KDC_QUY_LUONGBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblKdcQuyLuongDTO extends QllBaseDTO<TblKdcQuyLuongBO> {

	private java.lang.Long hoatDong;
	private java.lang.Float kdc;
	private java.lang.String thang;
	private java.lang.Long xoa;
	private java.lang.String donVi;
	private java.lang.Long tblKdcQuyLuongId;

	private String exThang;
	private String exNam;

	@Override
	public TblKdcQuyLuongBO toModel() {
		TblKdcQuyLuongBO tblKdcQuyLuongBO = new TblKdcQuyLuongBO();
		tblKdcQuyLuongBO.setHoatDong(this.hoatDong);
		tblKdcQuyLuongBO.setKdc(this.kdc);
		tblKdcQuyLuongBO.setThang(this.thang);
		tblKdcQuyLuongBO.setXoa(this.xoa);
		tblKdcQuyLuongBO.setDonVi(this.donVi);
		tblKdcQuyLuongBO.setTblKdcQuyLuongId(this.tblKdcQuyLuongId);
		return tblKdcQuyLuongBO;
	}

	@JsonProperty("hoatDong")
	public java.lang.Long getHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(java.lang.Long hoatDong) {
		this.hoatDong = hoatDong;
	}

	@JsonProperty("kdc")
	public java.lang.Float getKdc() {
		return kdc;
	}

	public void setKdc(java.lang.Float kdc) {
		this.kdc = kdc;
	}

	@JsonProperty("thang")
	public java.lang.String getThang() {
		return thang;
	}

	public void setThang(java.lang.String thang) {
		this.thang = thang;
	}

	@JsonProperty("xoa")
	public java.lang.Long getXoa() {
		return xoa;
	}

	public void setXoa(java.lang.Long xoa) {
		this.xoa = xoa;
	}

	@JsonProperty("donVi")
	public java.lang.String getDonVi() {
		return donVi;
	}

	public void setDonVi(java.lang.String donVi) {
		this.donVi = donVi;
	}

	@Override
	public Long getFWModelId() {
		return tblKdcQuyLuongId;
	}

	@Override
	public String catchName() {
		return getTblKdcQuyLuongId().toString();
	}

	@JsonProperty("tblKdcQuyLuongId")
	public java.lang.Long getTblKdcQuyLuongId() {
		return tblKdcQuyLuongId;
	}

	public void setTblKdcQuyLuongId(java.lang.Long tblKdcQuyLuongId) {
		this.tblKdcQuyLuongId = tblKdcQuyLuongId;
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
