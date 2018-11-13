package com.viettel.qll.dto;

import com.viettel.qll.bo.TblLuongTungTramBO;
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
@XmlRootElement(name = "TBL_LUONG_TUNG_TRAMBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblLuongTungTramDTO extends QllBaseDTO<TblLuongTungTramBO> {

	private java.lang.String loaiTram;
	private java.lang.Float donGia;
	private java.lang.String diaHinh;
	private java.lang.String vungLuong;
	private java.lang.String tram;
	private java.lang.String hoTen;
	private java.lang.String maNv;
	private java.lang.String huyen;
	private java.lang.String tinh;
	private java.lang.String thang;
	private java.lang.Long tblLuongTungTramId;
	private java.lang.Long hoatDong;
	private java.lang.Long xoa;
	private java.lang.Float luong;
	private java.lang.Float phatLoi6;
	private java.lang.Float phatLoi5;
	private java.lang.Float phatLoi4;
	private java.lang.Float phatLoi3;
	private java.lang.Float phatLoi2;
	private java.lang.Float phatLoi1;
	private java.lang.Float luongDuyTriTungTram;
	private java.lang.Float ngayCongCheDo;
	private java.lang.Float ngayCongTinhLuong;
	private java.lang.Float heSoDieuChinh;
	private java.lang.Float kpiTram;
	private java.lang.Float kiDonVi;
	private String text;
	private int start;
	private int maxResult;
	private String ExThang;
	private String ExNam;
    @Override
    public TblLuongTungTramBO toModel() {
        TblLuongTungTramBO tblLuongTungTramBO = new TblLuongTungTramBO();
        tblLuongTungTramBO.setLoaiTram(this.loaiTram);
        tblLuongTungTramBO.setDonGia(this.donGia);
        tblLuongTungTramBO.setDiaHinh(this.diaHinh);
        tblLuongTungTramBO.setVungLuong(this.vungLuong);
        tblLuongTungTramBO.setTram(this.tram);
        tblLuongTungTramBO.setHoTen(this.hoTen);
        tblLuongTungTramBO.setMaNv(this.maNv);
        tblLuongTungTramBO.setHuyen(this.huyen);
        tblLuongTungTramBO.setTinh(this.tinh);
        tblLuongTungTramBO.setThang(this.thang);
        tblLuongTungTramBO.setTblLuongTungTramId(this.tblLuongTungTramId);
        tblLuongTungTramBO.setHoatDong(this.hoatDong);
        tblLuongTungTramBO.setXoa(this.xoa);
        tblLuongTungTramBO.setLuong(this.luong);
        tblLuongTungTramBO.setPhatLoi6(this.phatLoi6);
        tblLuongTungTramBO.setPhatLoi5(this.phatLoi5);
        tblLuongTungTramBO.setPhatLoi4(this.phatLoi4);
        tblLuongTungTramBO.setPhatLoi3(this.phatLoi3);
        tblLuongTungTramBO.setPhatLoi2(this.phatLoi2);
        tblLuongTungTramBO.setPhatLoi1(this.phatLoi1);
        tblLuongTungTramBO.setLuongDuyTriTungTram(this.luongDuyTriTungTram);
        tblLuongTungTramBO.setNgayCongCheDo(this.ngayCongCheDo);
        tblLuongTungTramBO.setNgayCongTinhLuong(this.ngayCongTinhLuong);
        tblLuongTungTramBO.setHeSoDieuChinh(this.heSoDieuChinh);
        tblLuongTungTramBO.setKpiTram(this.kpiTram);
        tblLuongTungTramBO.setKiDonVi(this.kiDonVi);
        return tblLuongTungTramBO;
    }

	@JsonProperty("loaiTram")
    public java.lang.String getLoaiTram(){
		return loaiTram;
    }
	
    public void setLoaiTram(java.lang.String loaiTram){
		this.loaiTram = loaiTram;
    }	
	
	@JsonProperty("donGia")
    public java.lang.Float getDonGia(){
		return donGia;
    }
	
    public void setDonGia(java.lang.Float donGia){
		this.donGia = donGia;
    }	
	
	@JsonProperty("diaHinh")
    public java.lang.String getDiaHinh(){
		return diaHinh;
    }
	
    public void setDiaHinh(java.lang.String diaHinh){
		this.diaHinh = diaHinh;
    }	
	
	@JsonProperty("vungLuong")
    public java.lang.String getVungLuong(){
		return vungLuong;
    }
	
    public void setVungLuong(java.lang.String vungLuong){
		this.vungLuong = vungLuong;
    }	
	
	@JsonProperty("tram")
    public java.lang.String getTram(){
		return tram;
    }
	
    public void setTram(java.lang.String tram){
		this.tram = tram;
    }	
	
	@JsonProperty("hoTen")
    public java.lang.String getHoTen(){
		return hoTen;
    }
	
    public void setHoTen(java.lang.String hoTen){
		this.hoTen = hoTen;
    }	
	
	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("huyen")
    public java.lang.String getHuyen(){
		return huyen;
    }
	
    public void setHuyen(java.lang.String huyen){
		this.huyen = huyen;
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
        return tblLuongTungTramId;
    }
   
    @Override
    public String catchName() {
        return getTblLuongTungTramId().toString();
    }
	
	@JsonProperty("tblLuongTungTramId")
    public java.lang.Long getTblLuongTungTramId(){
		return tblLuongTungTramId;
    }
	
    public void setTblLuongTungTramId(java.lang.Long tblLuongTungTramId){
		this.tblLuongTungTramId = tblLuongTungTramId;
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
	
	@JsonProperty("kpiTram")
    public java.lang.Float getKpiTram(){
		return kpiTram;
    }
	
    public void setKpiTram(java.lang.Float kpiTram){
		this.kpiTram = kpiTram;
    }	
	
	@JsonProperty("kiDonVi")
    public java.lang.Float getKiDonVi(){
		return kiDonVi;
    }
	
    public void setKiDonVi(java.lang.Float kiDonVi){
		this.kiDonVi = kiDonVi;
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
