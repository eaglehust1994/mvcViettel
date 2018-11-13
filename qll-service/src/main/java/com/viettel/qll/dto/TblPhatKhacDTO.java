package com.viettel.qll.dto;

import com.viettel.qll.bo.TblPhatKhacBO;
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
@XmlRootElement(name = "TBL_PHAT_KHACBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblPhatKhacDTO extends QllBaseDTO<TblPhatKhacBO> {

	private java.lang.Long hoatDong;
	private java.lang.Long tblPhatKhacId;
	private java.lang.Float ucttChamDan;
	private java.lang.Float gianLanXangDau;
	private java.lang.Float loiRaVaNhaTram;
	private java.lang.Float viPhamDongAo;
	private java.lang.Float gianDoanThongTin;
	private java.lang.Float phatTrucTiep;
	private java.lang.String maTram;
	private java.lang.String maNv;
	private java.lang.String nhanVien;
	private java.lang.String maTinh;
	private java.lang.String kv;
	private java.lang.String thang;
	private java.lang.Long xoa;
	private String text;
	private int start;
	private int maxResult;
	
	private String exThang;
	private String exNam;

    @Override
    public TblPhatKhacBO toModel() {
        TblPhatKhacBO tblPhatKhacBO = new TblPhatKhacBO();
        tblPhatKhacBO.setHoatDong(this.hoatDong);
        tblPhatKhacBO.setTblPhatKhacId(this.tblPhatKhacId);
        tblPhatKhacBO.setUcttChamDan(this.ucttChamDan);
        tblPhatKhacBO.setGianLanXangDau(this.gianLanXangDau);
        tblPhatKhacBO.setLoiRaVaNhaTram(this.loiRaVaNhaTram);
        tblPhatKhacBO.setViPhamDongAo(this.viPhamDongAo);
        tblPhatKhacBO.setGianDoanThongTin(this.gianDoanThongTin);
        tblPhatKhacBO.setPhatTrucTiep(this.phatTrucTiep);
        tblPhatKhacBO.setMaTram(this.maTram);
        tblPhatKhacBO.setMaNv(this.maNv);
        tblPhatKhacBO.setNhanVien(this.nhanVien);
        tblPhatKhacBO.setMaTinh(this.maTinh);
        tblPhatKhacBO.setKv(this.kv);
        tblPhatKhacBO.setThang(this.thang);
        tblPhatKhacBO.setXoa(this.xoa);
        return tblPhatKhacBO;
    }

	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblPhatKhacId;
    }
   
    @Override
    public String catchName() {
        return getTblPhatKhacId().toString();
    }
	
	@JsonProperty("tblPhatKhacId")
    public java.lang.Long getTblPhatKhacId(){
		return tblPhatKhacId;
    }
	
    public void setTblPhatKhacId(java.lang.Long tblPhatKhacId){
		this.tblPhatKhacId = tblPhatKhacId;
    }	
	
	@JsonProperty("ucttChamDan")
    public java.lang.Float getUcttChamDan(){
		return ucttChamDan;
    }
	
    public void setUcttChamDan(java.lang.Float ucttChamDan){
		this.ucttChamDan = ucttChamDan;
    }	
	
	@JsonProperty("gianLanXangDau")
    public java.lang.Float getGianLanXangDau(){
		return gianLanXangDau;
    }
	
    public void setGianLanXangDau(java.lang.Float gianLanXangDau){
		this.gianLanXangDau = gianLanXangDau;
    }	
	
	@JsonProperty("loiRaVaNhaTram")
    public java.lang.Float getLoiRaVaNhaTram(){
		return loiRaVaNhaTram;
    }
	
    public void setLoiRaVaNhaTram(java.lang.Float loiRaVaNhaTram){
		this.loiRaVaNhaTram = loiRaVaNhaTram;
    }	
	
	@JsonProperty("viPhamDongAo")
    public java.lang.Float getViPhamDongAo(){
		return viPhamDongAo;
    }
	
    public void setViPhamDongAo(java.lang.Float viPhamDongAo){
		this.viPhamDongAo = viPhamDongAo;
    }	
	
	@JsonProperty("gianDoanThongTin")
    public java.lang.Float getGianDoanThongTin(){
		return gianDoanThongTin;
    }
	
    public void setGianDoanThongTin(java.lang.Float gianDoanThongTin){
		this.gianDoanThongTin = gianDoanThongTin;
    }	
	
	@JsonProperty("phatTrucTiep")
    public java.lang.Float getPhatTrucTiep(){
		return phatTrucTiep;
    }
	
    public void setPhatTrucTiep(java.lang.Float phatTrucTiep){
		this.phatTrucTiep = phatTrucTiep;
    }	
	
	@JsonProperty("maTram")
    public java.lang.String getMaTram(){
		return maTram;
    }
	
    public void setMaTram(java.lang.String maTram){
		this.maTram = maTram;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("nhanVien")
    public java.lang.String getNhanVien(){
		return nhanVien;
    }
	
    public void setNhanVien(java.lang.String nhanVien){
		this.nhanVien = nhanVien;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	@JsonProperty("kv")
    public java.lang.String getKv(){
		return kv;
    }
	
    public void setKv(java.lang.String kv){
		this.kv = kv;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
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
