package com.viettel.qll.dto;

import com.viettel.qll.bo.TblLuongDayMayBO;
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
@XmlRootElement(name = "TBL_LUONG_DAY_MAYBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblLuongDayMayDTO extends QllBaseDTO<TblLuongDayMayBO> {

	private java.lang.Long tblLuongDayMayId;
	private java.lang.String thang;
	private java.lang.String tinh;
	private java.lang.String huyen;
	private java.lang.String maNv;
	private java.lang.String hoTen;
	private java.lang.Float kiDonVi;
	private java.lang.Float heSoDieuChinh;
	private java.lang.Float soDayTbQuiDoi;
	private java.lang.Float donGia;
	private java.lang.Float ngayCongTinhLuong;
	private java.lang.Float ngayCongCheDo;
	private java.lang.Float luongDuyTri;
	private java.lang.Float phiBanHang;
	private java.lang.Float tkm0;
	private java.lang.Float tkm3;
	private java.lang.Float tkm4;
	private java.lang.Float tkm5;
	private java.lang.Float tdv0;
	private java.lang.Float tdv3;
	private java.lang.Float tdv4;
	private java.lang.Float tdv5;
	private java.lang.Float phatXlsc;
	private java.lang.Float phatPakh;
	private java.lang.Float loi1;
	private java.lang.Float loi2;
	private java.lang.Float loi3;
	private java.lang.Float loi4;
	private java.lang.Float loi5;
	private java.lang.Float loi6;
	private java.lang.Float loi7;
	private java.lang.Float loi8;
	private java.lang.Float loi9;
	private java.lang.Float loi10;
	private java.lang.Float loi11;
	private java.lang.Float loi12;
	private java.lang.Float luong;
	private java.lang.Long xoa;
	private java.lang.Long hoatDong;
	private java.lang.Float phatQcxlsc;
	private java.lang.Float phatLdsvtXlsct;
	private java.lang.Float phatGttbrm;
	private java.lang.Float phatTbcdcnktd;
	private java.lang.Float phatTbrmktd;
	private java.lang.Float phatLyttd;
	private String text;
	private int start;
	private int maxResult;

    @Override
    public TblLuongDayMayBO toModel() {
        TblLuongDayMayBO tblLuongDayMayBO = new TblLuongDayMayBO();
        tblLuongDayMayBO.setTblLuongDayMayId(this.tblLuongDayMayId);
        tblLuongDayMayBO.setThang(this.thang);
        tblLuongDayMayBO.setTinh(this.tinh);
        tblLuongDayMayBO.setHuyen(this.huyen);
        tblLuongDayMayBO.setMaNv(this.maNv);
        tblLuongDayMayBO.setHoTen(this.hoTen);
        tblLuongDayMayBO.setKiDonVi(this.kiDonVi);
        tblLuongDayMayBO.setHeSoDieuChinh(this.heSoDieuChinh);
        tblLuongDayMayBO.setSoDayTbQuiDoi(this.soDayTbQuiDoi);
        tblLuongDayMayBO.setDonGia(this.donGia);
        tblLuongDayMayBO.setNgayCongTinhLuong(this.ngayCongTinhLuong);
        tblLuongDayMayBO.setNgayCongCheDo(this.ngayCongCheDo);
        tblLuongDayMayBO.setLuongDuyTri(this.luongDuyTri);
        tblLuongDayMayBO.setPhiBanHang(this.phiBanHang);
        tblLuongDayMayBO.setTkm0(this.tkm0);
        tblLuongDayMayBO.setTkm3(this.tkm3);
        tblLuongDayMayBO.setTkm4(this.tkm4);
        tblLuongDayMayBO.setTkm5(this.tkm5);
        tblLuongDayMayBO.setTdv0(this.tdv0);
        tblLuongDayMayBO.setTdv3(this.tdv3);
        tblLuongDayMayBO.setTdv4(this.tdv4);
        tblLuongDayMayBO.setTdv5(this.tdv5);
        tblLuongDayMayBO.setPhatXlsc(this.phatXlsc);
        tblLuongDayMayBO.setPhatPakh(this.phatPakh);
        tblLuongDayMayBO.setLoi1(this.loi1);
        tblLuongDayMayBO.setLoi2(this.loi2);
        tblLuongDayMayBO.setLoi3(this.loi3);
        tblLuongDayMayBO.setLoi4(this.loi4);
        tblLuongDayMayBO.setLoi5(this.loi5);
        tblLuongDayMayBO.setLoi6(this.loi6);
        tblLuongDayMayBO.setLoi7(this.loi7);
        tblLuongDayMayBO.setLoi8(this.loi8);
        tblLuongDayMayBO.setLoi9(this.loi9);
        tblLuongDayMayBO.setLoi10(this.loi10);
        tblLuongDayMayBO.setLoi11(this.loi11);
        tblLuongDayMayBO.setLoi12(this.loi12);
        tblLuongDayMayBO.setLuong(this.luong);
        tblLuongDayMayBO.setXoa(this.xoa);
        tblLuongDayMayBO.setHoatDong(this.hoatDong);
        tblLuongDayMayBO.setPhatQcxlsc(this.phatQcxlsc);
        tblLuongDayMayBO.setPhatLdsvtXlsct(this.phatLdsvtXlsct);
        tblLuongDayMayBO.setPhatGttbrm(this.phatGttbrm);
        tblLuongDayMayBO.setPhatTbcdcnktd(this.phatTbcdcnktd);
        tblLuongDayMayBO.setPhatTbrmktd(this.phatTbrmktd);
        tblLuongDayMayBO.setPhatLyttd(this.phatLyttd);
        return tblLuongDayMayBO;
    }

    @Override
     public Long getFWModelId() {
        return tblLuongDayMayId;
    }
   
    @Override
    public String catchName() {
        return getTblLuongDayMayId().toString();
    }
	
	@JsonProperty("tblLuongDayMayId")
    public java.lang.Long getTblLuongDayMayId(){
		return tblLuongDayMayId;
    }
	
    public void setTblLuongDayMayId(java.lang.Long tblLuongDayMayId){
		this.tblLuongDayMayId = tblLuongDayMayId;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("huyen")
    public java.lang.String getHuyen(){
		return huyen;
    }
	
    public void setHuyen(java.lang.String huyen){
		this.huyen = huyen;
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
	
	@JsonProperty("kiDonVi")
    public java.lang.Float getKiDonVi(){
		return kiDonVi;
    }
	
    public void setKiDonVi(java.lang.Float kiDonVi){
		this.kiDonVi = kiDonVi;
    }	
	
	@JsonProperty("heSoDieuChinh")
    public java.lang.Float getHeSoDieuChinh(){
		return heSoDieuChinh;
    }
	
    public void setHeSoDieuChinh(java.lang.Float heSoDieuChinh){
		this.heSoDieuChinh = heSoDieuChinh;
    }	
	
	@JsonProperty("soDayTbQuiDoi")
    public java.lang.Float getSoDayTbQuiDoi(){
		return soDayTbQuiDoi;
    }
	
    public void setSoDayTbQuiDoi(java.lang.Float soDayTbQuiDoi){
		this.soDayTbQuiDoi = soDayTbQuiDoi;
    }	
	
	@JsonProperty("donGia")
    public java.lang.Float getDonGia(){
		return donGia;
    }
	
    public void setDonGia(java.lang.Float donGia){
		this.donGia = donGia;
    }	
	
	@JsonProperty("ngayCongTinhLuong")
    public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
    }
	
    public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong){
		this.ngayCongTinhLuong = ngayCongTinhLuong;
    }	
	
	@JsonProperty("ngayCongCheDo")
    public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
    }
	
    public void setNgayCongCheDo(java.lang.Float ngayCongCheDo){
		this.ngayCongCheDo = ngayCongCheDo;
    }	
	
	@JsonProperty("luongDuyTri")
    public java.lang.Float getLuongDuyTri(){
		return luongDuyTri;
    }
	
    public void setLuongDuyTri(java.lang.Float luongDuyTri){
		this.luongDuyTri = luongDuyTri;
    }	
	
	@JsonProperty("phiBanHang")
    public java.lang.Float getPhiBanHang(){
		return phiBanHang;
    }
	
    public void setPhiBanHang(java.lang.Float phiBanHang){
		this.phiBanHang = phiBanHang;
    }	
	
	@JsonProperty("tkm0")
    public java.lang.Float getTkm0(){
		return tkm0;
    }
	
    public void setTkm0(java.lang.Float tkm0){
		this.tkm0 = tkm0;
    }	
	
	@JsonProperty("tkm3")
    public java.lang.Float getTkm3(){
		return tkm3;
    }
	
    public void setTkm3(java.lang.Float tkm3){
		this.tkm3 = tkm3;
    }	
	
	@JsonProperty("tkm4")
    public java.lang.Float getTkm4(){
		return tkm4;
    }
	
    public void setTkm4(java.lang.Float tkm4){
		this.tkm4 = tkm4;
    }	
	
	@JsonProperty("tkm5")
    public java.lang.Float getTkm5(){
		return tkm5;
    }
	
    public void setTkm5(java.lang.Float tkm5){
		this.tkm5 = tkm5;
    }	
	
	@JsonProperty("tdv0")
    public java.lang.Float getTdv0(){
		return tdv0;
    }
	
    public void setTdv0(java.lang.Float tdv0){
		this.tdv0 = tdv0;
    }	
	
	@JsonProperty("tdv3")
    public java.lang.Float getTdv3(){
		return tdv3;
    }
	
    public void setTdv3(java.lang.Float tdv3){
		this.tdv3 = tdv3;
    }	
	
	@JsonProperty("tdv4")
    public java.lang.Float getTdv4(){
		return tdv4;
    }
	
    public void setTdv4(java.lang.Float tdv4){
		this.tdv4 = tdv4;
    }	
	
	@JsonProperty("tdv5")
    public java.lang.Float getTdv5(){
		return tdv5;
    }
	
    public void setTdv5(java.lang.Float tdv5){
		this.tdv5 = tdv5;
    }	
	
	@JsonProperty("phatXlsc")
    public java.lang.Float getPhatXlsc(){
		return phatXlsc;
    }
	
    public void setPhatXlsc(java.lang.Float phatXlsc){
		this.phatXlsc = phatXlsc;
    }	
	
	@JsonProperty("phatPakh")
    public java.lang.Float getPhatPakh(){
		return phatPakh;
    }
	
    public void setPhatPakh(java.lang.Float phatPakh){
		this.phatPakh = phatPakh;
    }	
	
	@JsonProperty("loi1")
    public java.lang.Float getLoi1(){
		return loi1;
    }
	
    public void setLoi1(java.lang.Float loi1){
		this.loi1 = loi1;
    }	
	
	@JsonProperty("loi2")
    public java.lang.Float getLoi2(){
		return loi2;
    }
	
    public void setLoi2(java.lang.Float loi2){
		this.loi2 = loi2;
    }	
	
	@JsonProperty("loi3")
    public java.lang.Float getLoi3(){
		return loi3;
    }
	
    public void setLoi3(java.lang.Float loi3){
		this.loi3 = loi3;
    }	
	
	@JsonProperty("loi4")
    public java.lang.Float getLoi4(){
		return loi4;
    }
	
    public void setLoi4(java.lang.Float loi4){
		this.loi4 = loi4;
    }	
	
	@JsonProperty("loi5")
    public java.lang.Float getLoi5(){
		return loi5;
    }
	
    public void setLoi5(java.lang.Float loi5){
		this.loi5 = loi5;
    }	
	
	@JsonProperty("loi6")
    public java.lang.Float getLoi6(){
		return loi6;
    }
	
    public void setLoi6(java.lang.Float loi6){
		this.loi6 = loi6;
    }	
	
	@JsonProperty("loi7")
    public java.lang.Float getLoi7(){
		return loi7;
    }
	
    public void setLoi7(java.lang.Float loi7){
		this.loi7 = loi7;
    }	
	
	@JsonProperty("loi8")
    public java.lang.Float getLoi8(){
		return loi8;
    }
	
    public void setLoi8(java.lang.Float loi8){
		this.loi8 = loi8;
    }	
	
	@JsonProperty("loi9")
    public java.lang.Float getLoi9(){
		return loi9;
    }
	
    public void setLoi9(java.lang.Float loi9){
		this.loi9 = loi9;
    }	
	
	@JsonProperty("loi10")
    public java.lang.Float getLoi10(){
		return loi10;
    }
	
    public void setLoi10(java.lang.Float loi10){
		this.loi10 = loi10;
    }	
	
	@JsonProperty("loi11")
    public java.lang.Float getLoi11(){
		return loi11;
    }
	
    public void setLoi11(java.lang.Float loi11){
		this.loi11 = loi11;
    }	
	
	@JsonProperty("loi12")
    public java.lang.Float getLoi12(){
		return loi12;
    }
	
    public void setLoi12(java.lang.Float loi12){
		this.loi12 = loi12;
    }	
	
	@JsonProperty("luong")
    public java.lang.Float getLuong(){
		return luong;
    }
	
    public void setLuong(java.lang.Float luong){
		this.luong = luong;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("hoatDong")
    public java.lang.Long getHoatDong(){
		return hoatDong;
    }
	
    public void setHoatDong(java.lang.Long hoatDong){
		this.hoatDong = hoatDong;
    }	
	
	@JsonProperty("phatQcxlsc")
    public java.lang.Float getPhatQcxlsc(){
		return phatQcxlsc;
    }
	
    public void setPhatQcxlsc(java.lang.Float phatQcxlsc){
		this.phatQcxlsc = phatQcxlsc;
    }	
	
	@JsonProperty("phatLdsvtXlsct")
    public java.lang.Float getPhatLdsvtXlsct(){
		return phatLdsvtXlsct;
    }
	
    public void setPhatLdsvtXlsct(java.lang.Float phatLdsvtXlsct){
		this.phatLdsvtXlsct = phatLdsvtXlsct;
    }	
	
	@JsonProperty("phatGttbrm")
    public java.lang.Float getPhatGttbrm(){
		return phatGttbrm;
    }
	
    public void setPhatGttbrm(java.lang.Float phatGttbrm){
		this.phatGttbrm = phatGttbrm;
    }	
	
	@JsonProperty("phatTbcdcnktd")
    public java.lang.Float getPhatTbcdcnktd(){
		return phatTbcdcnktd;
    }
	
    public void setPhatTbcdcnktd(java.lang.Float phatTbcdcnktd){
		this.phatTbcdcnktd = phatTbcdcnktd;
    }	
	
	@JsonProperty("phatTbrmktd")
    public java.lang.Float getPhatTbrmktd(){
		return phatTbrmktd;
    }
	
    public void setPhatTbrmktd(java.lang.Float phatTbrmktd){
		this.phatTbrmktd = phatTbrmktd;
    }	
	
	@JsonProperty("phatLyttd")
    public java.lang.Float getPhatLyttd(){
		return phatLyttd;
    }
	
    public void setPhatLyttd(java.lang.Float phatLyttd){
		this.phatLyttd = phatLyttd;
    }	
	
	
}
