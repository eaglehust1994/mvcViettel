package com.viettel.qll.dto;

import com.viettel.qll.bo.TblLuongNvTramBO;
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
@XmlRootElement(name = "TBL_LUONG_NV_TRAMBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblLuongNvTramDTO extends QllBaseDTO<TblLuongNvTramBO> {

	private java.lang.String maNv;
	private java.lang.String hoTen;
	private java.lang.String tinh;
	private java.lang.String thang;
	private java.lang.Long tblLuongNvTramId;
	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.Float luong;
	private java.lang.Float phatLoi7;
	private java.lang.Float phatLoi6;
	private java.lang.Float phatLoi5;
	private java.lang.Float phatLoi4;
	private java.lang.Float phatLoi3;
	private java.lang.Float phatLoi2;
	private java.lang.Float phatLoi1;
	private java.lang.Float phiBanHang;
	private java.lang.Float luongDuyTriTungTram;
	private java.lang.Float ngayCongCheDo;
	private java.lang.Float ngayCongTinhLuong;
	private java.lang.Float heSoDieuChinh;
	private java.lang.Float kpiDonVi;
	private java.lang.String soTramQuanLy;
	private java.lang.String huyen;
	private String text;
	private int start;
	private int maxResult;
	private String ExThang;
	private String ExNam;
    @Override
    public TblLuongNvTramBO toModel() {
        TblLuongNvTramBO tblLuongNvTramBO = new TblLuongNvTramBO();
        tblLuongNvTramBO.setMaNv(this.maNv);
        tblLuongNvTramBO.setHoTen(this.hoTen);
        tblLuongNvTramBO.setTinh(this.tinh);
        tblLuongNvTramBO.setThang(this.thang);
        tblLuongNvTramBO.setTblLuongNvTramId(this.tblLuongNvTramId);
        tblLuongNvTramBO.setHoatDong(this.hoatDong);
        tblLuongNvTramBO.setXoa(this.xoa);
        tblLuongNvTramBO.setLuong(this.luong);
        tblLuongNvTramBO.setPhatLoi7(this.phatLoi7);
        tblLuongNvTramBO.setPhatLoi6(this.phatLoi6);
        tblLuongNvTramBO.setPhatLoi5(this.phatLoi5);
        tblLuongNvTramBO.setPhatLoi4(this.phatLoi4);
        tblLuongNvTramBO.setPhatLoi3(this.phatLoi3);
        tblLuongNvTramBO.setPhatLoi2(this.phatLoi2);
        tblLuongNvTramBO.setPhatLoi1(this.phatLoi1);
        tblLuongNvTramBO.setPhiBanHang(this.phiBanHang);
        tblLuongNvTramBO.setLuongDuyTriTungTram(this.luongDuyTriTungTram);
        tblLuongNvTramBO.setNgayCongCheDo(this.ngayCongCheDo);
        tblLuongNvTramBO.setNgayCongTinhLuong(this.ngayCongTinhLuong);
        tblLuongNvTramBO.setHeSoDieuChinh(this.heSoDieuChinh);
        tblLuongNvTramBO.setKpiDonVi(this.kpiDonVi);
        tblLuongNvTramBO.setSoTramQuanLy(this.soTramQuanLy);
        tblLuongNvTramBO.setHuyen(this.huyen);
        return tblLuongNvTramBO;
    }

	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("hoTen")
    public java.lang.String getHoTen(){
		return hoTen;
    }
	
    public void setHoTen(java.lang.String hoTen){
		this.hoTen = hoTen;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
    @Override
     public Long getFWModelId() {
        return tblLuongNvTramId;
    }
   
    @Override
    public String catchName() {
        return getTblLuongNvTramId().toString();
    }
	
	@JsonProperty("tblLuongNvTramId")
    public java.lang.Long getTblLuongNvTramId(){
		return tblLuongNvTramId;
    }
	
    public void setTblLuongNvTramId(java.lang.Long tblLuongNvTramId){
		this.tblLuongNvTramId = tblLuongNvTramId;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("luong")
    public java.lang.Float getLuong(){
		return luong;
    }
	
    public void setLuong(java.lang.Float luong){
		this.luong = luong;
    }	
	
	@JsonProperty("phatLoi7")
    public java.lang.Float getPhatLoi7(){
		return phatLoi7;
    }
	
    public void setPhatLoi7(java.lang.Float phatLoi7){
		this.phatLoi7 = phatLoi7;
    }	
	
	@JsonProperty("phatLoi6")
    public java.lang.Float getPhatLoi6(){
		return phatLoi6;
    }
	
    public void setPhatLoi6(java.lang.Float phatLoi6){
		this.phatLoi6 = phatLoi6;
    }	
	
	@JsonProperty("phatLoi5")
    public java.lang.Float getPhatLoi5(){
		return phatLoi5;
    }
	
    public void setPhatLoi5(java.lang.Float phatLoi5){
		this.phatLoi5 = phatLoi5;
    }	
	
	@JsonProperty("phatLoi4")
    public java.lang.Float getPhatLoi4(){
		return phatLoi4;
    }
	
    public void setPhatLoi4(java.lang.Float phatLoi4){
		this.phatLoi4 = phatLoi4;
    }	
	
	@JsonProperty("phatLoi3")
    public java.lang.Float getPhatLoi3(){
		return phatLoi3;
    }
	
    public void setPhatLoi3(java.lang.Float phatLoi3){
		this.phatLoi3 = phatLoi3;
    }	
	
	@JsonProperty("phatLoi2")
    public java.lang.Float getPhatLoi2(){
		return phatLoi2;
    }
	
    public void setPhatLoi2(java.lang.Float phatLoi2){
		this.phatLoi2 = phatLoi2;
    }	
	
	@JsonProperty("phatLoi1")
    public java.lang.Float getPhatLoi1(){
		return phatLoi1;
    }
	
    public void setPhatLoi1(java.lang.Float phatLoi1){
		this.phatLoi1 = phatLoi1;
    }	
	
	@JsonProperty("phiBanHang")
    public java.lang.Float getPhiBanHang(){
		return phiBanHang;
    }
	
    public void setPhiBanHang(java.lang.Float phiBanHang){
		this.phiBanHang = phiBanHang;
    }	
	
	@JsonProperty("luongDuyTriTungTram")
    public java.lang.Float getLuongDuyTriTungTram(){
		return luongDuyTriTungTram;
    }
	
    public void setLuongDuyTriTungTram(java.lang.Float luongDuyTriTungTram){
		this.luongDuyTriTungTram = luongDuyTriTungTram;
    }	
	
	@JsonProperty("ngayCongCheDo")
    public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
    }
	
    public void setNgayCongCheDo(java.lang.Float ngayCongCheDo){
		this.ngayCongCheDo = ngayCongCheDo;
    }	
	
	@JsonProperty("ngayCongTinhLuong")
    public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
    }
	
    public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong){
		this.ngayCongTinhLuong = ngayCongTinhLuong;
    }	
	
	@JsonProperty("heSoDieuChinh")
    public java.lang.Float getHeSoDieuChinh(){
		return heSoDieuChinh;
    }
	
    public void setHeSoDieuChinh(java.lang.Float heSoDieuChinh){
		this.heSoDieuChinh = heSoDieuChinh;
    }	
	
	@JsonProperty("kpiDonVi")
    public java.lang.Float getKpiDonVi(){
		return kpiDonVi;
    }
	
    public void setKpiDonVi(java.lang.Float kpiDonVi){
		this.kpiDonVi = kpiDonVi;
    }	
	
	@JsonProperty("soTramQuanLy")
    public java.lang.String getSoTramQuanLy(){
		return soTramQuanLy;
    }
	
    public void setSoTramQuanLy(java.lang.String soTramQuanLy){
		this.soTramQuanLy = soTramQuanLy;
    }	
	
	@JsonProperty("huyen")
    public java.lang.String getHuyen(){
		return huyen;
    }
	
    public void setHuyen(java.lang.String huyen){
		this.huyen = huyen;
    }	
	
	public void setExThang(String ExThang)
	{
		this.ExThang=ExThang;
	}
	public String getExThang() {
		return ExThang;
	}
	
	public void setExNam(String ExNam)
	{
		this.ExNam=ExNam;
	}
	public String getExNam() {
		return ExNam;
	}
}
