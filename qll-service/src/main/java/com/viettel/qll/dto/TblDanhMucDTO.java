package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDanhMucBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
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
@XmlRootElement(name = "TBL_DANH_MUCBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDanhMucDTO extends QllBaseDTO<TblDanhMucBO> {

	private java.lang.String giaTri;
	private java.lang.String maDanhMuc;
	private java.lang.String tenDanhMuc;
	private java.lang.Long tblDanhMucId;
	private java.lang.String loaiDanhMuc;
	private java.lang.Long hoatDong;
	private java.lang.Long danhMucCha;
	private java.lang.String maDanhMucCha;
	private String name;
	private String namePopup;

	public String getNamePopup() {
		return namePopup;
	}

	public void setNamePopup(String namePopup) {
		this.namePopup = namePopup;
	}

	@Override
	public TblDanhMucBO toModel() {
		TblDanhMucBO tblDanhMucBO = new TblDanhMucBO();
		tblDanhMucBO.setGiaTri(this.giaTri);
		tblDanhMucBO.setMaDanhMuc(this.maDanhMuc);
		tblDanhMucBO.setTenDanhMuc(this.tenDanhMuc);
		tblDanhMucBO.setTblDanhMucId(this.tblDanhMucId);
		tblDanhMucBO.setHoatDong(this.hoatDong);
		tblDanhMucBO.setDanhMucCha(this.danhMucCha);
		tblDanhMucBO.setLoaiDanhMuc(this.loaiDanhMuc);
		tblDanhMucBO.setMaDanhMucCha(this.maDanhMucCha);
		return tblDanhMucBO;
	}

	@JsonProperty("giaTri")
	public java.lang.String getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(java.lang.String giaTri) {
		this.giaTri = giaTri;
	}

	@JsonProperty("maDanhMuc")
	public java.lang.String getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(java.lang.String maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	@JsonProperty("tenDanhMuc")
	public java.lang.String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(java.lang.String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	@Override
	public Long getFWModelId() {
		return tblDanhMucId;
	}

	@Override
	public String catchName() {
		return getTblDanhMucId().toString();
	}

	@JsonProperty("tblDanhMucId")
	public java.lang.Long getTblDanhMucId() {
		return tblDanhMucId;
	}

	public void setTblDanhMucId(java.lang.Long tblDanhMucId) {
		this.tblDanhMucId = tblDanhMucId;
	}

	@JsonProperty("hoatDong")
	public java.lang.Long getHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(java.lang.Long hoatDong) {
		this.hoatDong = hoatDong;
	}

	@JsonProperty("danhMucCha")
	public java.lang.Long getDanhMucCha() {
		return danhMucCha;
	}

	public void setDanhMucCha(java.lang.Long danhMucCha) {
		this.danhMucCha = danhMucCha;
	}

	@JsonProperty("loaiDanhMuc")
	public java.lang.String getLoaiDanhMuc() {
		return loaiDanhMuc;
	}

	public void setLoaiDanhMuc(java.lang.String loaiDanhMuc) {
		this.loaiDanhMuc = loaiDanhMuc;
	}
	@JsonProperty("maDanhMucCha")
	public java.lang.String getMaDanhMucCha() {
		return maDanhMucCha;
	}

	public void setMaDanhMucCha(java.lang.String maDanhMucCha) {
		this.maDanhMucCha = maDanhMucCha;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
