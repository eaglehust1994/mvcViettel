package com.viettel.qll.dto;

import com.viettel.qll.bo.TblLoiDongAoBO;
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
@XmlRootElement(name = "TBL_LOI_DONG_AOBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblLoiDongAoDTO extends QllBaseDTO<TblLoiDongAoBO> {

	private java.lang.String tinh;
	private java.lang.Long xoa;
	private java.lang.Float thanhTien;
	private java.lang.Float donGia;
	private java.lang.Float tongLoi;
	private java.lang.Float loiTichHenAo;
	private java.lang.Float loiDongAo;
	private java.lang.Float loiThaiDo;
	private java.lang.Float loiNghiepVu;
	private java.lang.String maNv;
	private java.lang.String accountKh;
	private java.lang.Long tblLoiDongAoId;
	private java.lang.String thang;
	private java.lang.Long hoatDong;
	private java.lang.String quanHuyen;
	private java.lang.String loaiDv;
	private java.lang.String exThang;
	private java.lang.String exNam;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblLoiDongAoBO toModel() {
        TblLoiDongAoBO tblLoiDongAoBO = new TblLoiDongAoBO();
        tblLoiDongAoBO.setTinh(this.tinh);
        tblLoiDongAoBO.setXoa(this.xoa);
        tblLoiDongAoBO.setThanhTien(this.thanhTien);
        tblLoiDongAoBO.setDonGia(this.donGia);
        tblLoiDongAoBO.setTongLoi(this.tongLoi);
        tblLoiDongAoBO.setLoiTichHenAo(this.loiTichHenAo);
        tblLoiDongAoBO.setLoiDongAo(this.loiDongAo);
        tblLoiDongAoBO.setLoiThaiDo(this.loiThaiDo);
        tblLoiDongAoBO.setLoiNghiepVu(this.loiNghiepVu);
        tblLoiDongAoBO.setMaNv(this.maNv);
        tblLoiDongAoBO.setAccountKh(this.accountKh);
        tblLoiDongAoBO.setTblLoiDongAoId(this.tblLoiDongAoId);
        tblLoiDongAoBO.setThang(this.thang);
        tblLoiDongAoBO.setHoatDong(this.hoatDong);
        tblLoiDongAoBO.setQuanHuyen(this.quanHuyen);
        tblLoiDongAoBO.setLoaiDv(this.loaiDv);
        return tblLoiDongAoBO;
    }

    
	public java.lang.String getExThang() {
		return exThang;
	}


	public void setExThang(java.lang.String exThang) {
		this.exThang = exThang;
	}


	public java.lang.String getExNam() {
		return exNam;
	}


	public void setExNam(java.lang.String exNam) {
		this.exNam = exNam;
	}


	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("thanhTien")
    public java.lang.Float getThanhTien(){
		return thanhTien;
    }
	
    public void setThanhTien(java.lang.Float thanhTien){
		this.thanhTien = thanhTien;
    }	
	
	@JsonProperty("donGia")
    public java.lang.Float getDonGia(){
		return donGia;
    }
	
    public void setDonGia(java.lang.Float donGia){
		this.donGia = donGia;
    }	
	
	@JsonProperty("tongLoi")
    public java.lang.Float getTongLoi(){
		return tongLoi;
    }
	
    public void setTongLoi(java.lang.Float tongLoi){
		this.tongLoi = tongLoi;
    }	
	
	@JsonProperty("loiTichHenAo")
    public java.lang.Float getLoiTichHenAo(){
		return loiTichHenAo;
    }
	
    public void setLoiTichHenAo(java.lang.Float loiTichHenAo){
		this.loiTichHenAo = loiTichHenAo;
    }	
	
	@JsonProperty("loiDongAo")
    public java.lang.Float getLoiDongAo(){
		return loiDongAo;
    }
	
    public void setLoiDongAo(java.lang.Float loiDongAo){
		this.loiDongAo = loiDongAo;
    }	
	
	@JsonProperty("loiThaiDo")
    public java.lang.Float getLoiThaiDo(){
		return loiThaiDo;
    }
	
    public void setLoiThaiDo(java.lang.Float loiThaiDo){
		this.loiThaiDo = loiThaiDo;
    }	
	
	@JsonProperty("loiNghiepVu")
    public java.lang.Float getLoiNghiepVu(){
		return loiNghiepVu;
    }
	
    public void setLoiNghiepVu(java.lang.Float loiNghiepVu){
		this.loiNghiepVu = loiNghiepVu;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("accountKh")
    public java.lang.String getAccountKh(){
		return accountKh;
    }
	
    public void setAccountKh(java.lang.String accountKh){
		this.accountKh = accountKh;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblLoiDongAoId;
    }
   
    @Override
    public String catchName() {
        return getTblLoiDongAoId().toString();
    }
	
	@JsonProperty("tblLoiDongAoId")
    public java.lang.Long getTblLoiDongAoId(){
		return tblLoiDongAoId;
    }
	
    public void setTblLoiDongAoId(java.lang.Long tblLoiDongAoId){
		this.tblLoiDongAoId = tblLoiDongAoId;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("quanHuyen")
    public java.lang.String getQuanHuyen(){
		return quanHuyen;
    }
	
    public void setQuanHuyen(java.lang.String quanHuyen){
		this.quanHuyen = quanHuyen;
    }	
	
	@JsonProperty("loaiDv")
    public java.lang.String getLoaiDv(){
		return loaiDv;
    }
	
    public void setLoaiDv(java.lang.String loaiDv){
		this.loaiDv = loaiDv;
    }	
	
	
}
