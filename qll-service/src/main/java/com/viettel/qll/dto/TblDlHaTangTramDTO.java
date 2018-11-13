package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDlHaTangTramBO;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_DL_HA_TANG_TRAMBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDlHaTangTramDTO extends QllBaseDTO<TblDlHaTangTramBO> {

	private java.lang.Float ngayCongCheDo;
	private java.lang.String thang;
	private java.lang.Float soNgayQuanLy;
	private java.lang.Float soNgayTrongThang;
	private java.lang.Long tblDlHaTangTramId;
	private java.lang.String khuVuc;
	private java.lang.String maTinh;
	private java.lang.String huyen;
	private java.lang.String maTram;
	private java.lang.String loaiDiaHinh;
	private java.lang.String phanLoaiTram;
	private java.lang.String vung;
	private java.lang.String maNv;
	private java.lang.Float ngayCongTinhLuong;
	private String text;
	private int start;
	private int maxResult;
	
	private String exThang;
	private String exNam;

    @Override
    public TblDlHaTangTramBO toModel() {
        TblDlHaTangTramBO tblDlHaTangTramBO = new TblDlHaTangTramBO();
        tblDlHaTangTramBO.setNgayCongCheDo(this.ngayCongCheDo);
        tblDlHaTangTramBO.setThang(this.thang);
        tblDlHaTangTramBO.setSoNgayQuanLy(this.soNgayQuanLy);
        tblDlHaTangTramBO.setSoNgayTrongThang(this.soNgayTrongThang);
        tblDlHaTangTramBO.setTblDlHaTangTramId(this.tblDlHaTangTramId);
        tblDlHaTangTramBO.setKhuVuc(this.khuVuc);
        tblDlHaTangTramBO.setMaTinh(this.maTinh);
        tblDlHaTangTramBO.setHuyen(this.huyen);
        tblDlHaTangTramBO.setMaTram(this.maTram);
        tblDlHaTangTramBO.setLoaiDiaHinh(this.loaiDiaHinh);
        tblDlHaTangTramBO.setPhanLoaiTram(this.phanLoaiTram);
        tblDlHaTangTramBO.setVung(this.vung);
        tblDlHaTangTramBO.setMaNv(this.maNv);
        tblDlHaTangTramBO.setNgayCongTinhLuong(this.ngayCongTinhLuong);
        return tblDlHaTangTramBO;
    }

	@JsonProperty("ngayCongCheDo")
    public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
    }
	
    public void setNgayCongCheDo(java.lang.Float ngayCongCheDo){
		this.ngayCongCheDo = ngayCongCheDo;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("soNgayQuanLy")
    public java.lang.Float getSoNgayQuanLy(){
		return soNgayQuanLy;
    }
	
    public void setSoNgayQuanLy(java.lang.Float soNgayQuanLy){
		this.soNgayQuanLy = soNgayQuanLy;
    }	
	
	@JsonProperty("soNgayTrongThang")
    public java.lang.Float getSoNgayTrongThang(){
		return soNgayTrongThang;
    }
	
    public void setSoNgayTrongThang(java.lang.Float soNgayTrongThang){
		this.soNgayTrongThang = soNgayTrongThang;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblDlHaTangTramId;
    }
   
    @Override
    public String catchName() {
        return getTblDlHaTangTramId().toString();
    }
	
	@JsonProperty("tblDlHaTangTramId")
    public java.lang.Long getTblDlHaTangTramId(){
		return tblDlHaTangTramId;
    }
	
    public void setTblDlHaTangTramId(java.lang.Long tblDlHaTangTramId){
		this.tblDlHaTangTramId = tblDlHaTangTramId;
    }	
	
	@JsonProperty("khuVuc")
    public java.lang.String getKhuVuc(){
		return khuVuc;
    }
	
    public void setKhuVuc(java.lang.String khuVuc){
		this.khuVuc = khuVuc;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	@JsonProperty("huyen")
    public java.lang.String getHuyen(){
		return huyen;
    }
	
    public void setHuyen(java.lang.String huyen){
		this.huyen = huyen;
    }	
	
	@JsonProperty("maTram")
    public java.lang.String getMaTram(){
		return maTram;
    }
	
    public void setMaTram(java.lang.String maTram){
		this.maTram = maTram;
    }	
	
	@JsonProperty("loaiDiaHinh")
    public java.lang.String getLoaiDiaHinh(){
		return loaiDiaHinh;
    }
	
    public void setLoaiDiaHinh(java.lang.String loaiDiaHinh){
		this.loaiDiaHinh = loaiDiaHinh;
    }	
	
	@JsonProperty("phanLoaiTram")
    public java.lang.String getPhanLoaiTram(){
		return phanLoaiTram;
    }
	
    public void setPhanLoaiTram(java.lang.String phanLoaiTram){
		this.phanLoaiTram = phanLoaiTram;
    }	
	
	@JsonProperty("vung")
    public java.lang.String getVung(){
		return vung;
    }
	
    public void setVung(java.lang.String vung){
		this.vung = vung;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("ngayCongTinhLuong")
    public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
    }
	
    public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong){
		this.ngayCongTinhLuong = ngayCongTinhLuong;
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
