package com.viettel.qll.bo;

import com.viettel.qll.dto.TblLuongDayMayDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblLuongDayMayBO")
@Table(name = "TBL_LUONG_DAY_MAY")
/**
 *
 * @author: hailh10
 */
public class TblLuongDayMayBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_LUONG_DAY_MAY_SEQ") })
	@Column(name = "TBL_LUONG_DAY_MAY_ID", length = 22)
	private java.lang.Long tblLuongDayMayId;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "TINH", length = 300)
	private java.lang.String tinh;
	@Column(name = "HUYEN", length = 1500)
	private java.lang.String huyen;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "HO_TEN", length = 600)
	private java.lang.String hoTen;
	@Column(name = "KI_DON_VI", length = 22)
	private java.lang.Float kiDonVi;
	@Column(name = "HE_SO_DIEU_CHINH", length = 22)
	private java.lang.Float heSoDieuChinh;
	@Column(name = "SO_DAY_TB_QUI_DOI", length = 22)
	private java.lang.Float soDayTbQuiDoi;
	@Column(name = "DON_GIA", length = 22)
	private java.lang.Float donGia;
	@Column(name = "NGAY_CONG_TINH_LUONG", length = 22)
	private java.lang.Float ngayCongTinhLuong;
	@Column(name = "NGAY_CONG_CHE_DO", length = 22)
	private java.lang.Float ngayCongCheDo;
	@Column(name = "LUONG_DUY_TRI", length = 22)
	private java.lang.Float luongDuyTri;
	@Column(name = "PHI_BAN_HANG", length = 22)
	private java.lang.Float phiBanHang;
	@Column(name = "TKM_0", length = 22)
	private java.lang.Float tkm0;
	@Column(name = "TKM_3", length = 22)
	private java.lang.Float tkm3;
	@Column(name = "TKM_4", length = 22)
	private java.lang.Float tkm4;
	@Column(name = "TKM_5", length = 22)
	private java.lang.Float tkm5;
	@Column(name = "TDV_0", length = 22)
	private java.lang.Float tdv0;
	@Column(name = "TDV_3", length = 22)
	private java.lang.Float tdv3;
	@Column(name = "TDV_4", length = 22)
	private java.lang.Float tdv4;
	@Column(name = "TDV_5", length = 22)
	private java.lang.Float tdv5;
	@Column(name = "PHAT_XLSC", length = 22)
	private java.lang.Float phatXlsc;
	@Column(name = "PHAT_PAKH", length = 22)
	private java.lang.Float phatPakh;
	@Column(name = "LOI_1", length = 22)
	private java.lang.Float loi1;
	@Column(name = "LOI_2", length = 22)
	private java.lang.Float loi2;
	@Column(name = "LOI_3", length = 22)
	private java.lang.Float loi3;
	@Column(name = "LOI_4", length = 22)
	private java.lang.Float loi4;
	@Column(name = "LOI_5", length = 22)
	private java.lang.Float loi5;
	@Column(name = "LOI_6", length = 22)
	private java.lang.Float loi6;
	@Column(name = "LOI_7", length = 22)
	private java.lang.Float loi7;
	@Column(name = "LOI_8", length = 22)
	private java.lang.Float loi8;
	@Column(name = "LOI_9", length = 22)
	private java.lang.Float loi9;
	@Column(name = "LOI_10", length = 22)
	private java.lang.Float loi10;
	@Column(name = "LOI_11", length = 22)
	private java.lang.Float loi11;
	@Column(name = "LOI_12", length = 22)
	private java.lang.Float loi12;
	@Column(name = "LUONG", length = 22)
	private java.lang.Float luong;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Column(name = "PHAT_QCXLSC", length = 22)
	private java.lang.Float phatQcxlsc;
	@Column(name = "PHAT_LDSVT_XLSCT", length = 22)
	private java.lang.Float phatLdsvtXlsct;
	@Column(name = "PHAT_GTTBRM", length = 22)
	private java.lang.Float phatGttbrm;
	@Column(name = "PHAT_TBCDCNKTD", length = 22)
	private java.lang.Float phatTbcdcnktd;
	@Column(name = "PHAT_TBRMKTD", length = 22)
	private java.lang.Float phatTbrmktd;
	@Column(name = "PHAT_LYTTD", length = 22)
	private java.lang.Float phatLyttd;

	
	public java.lang.Long getTblLuongDayMayId(){
		return tblLuongDayMayId;
	}
	
	public void setTblLuongDayMayId(java.lang.Long tblLuongDayMayId)
	{
		this.tblLuongDayMayId = tblLuongDayMayId;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.String getTinh(){
		return tinh;
	}
	
	public void setTinh(java.lang.String tinh)
	{
		this.tinh = tinh;
	}
	
	public java.lang.String getHuyen(){
		return huyen;
	}
	
	public void setHuyen(java.lang.String huyen)
	{
		this.huyen = huyen;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getHoTen(){
		return hoTen;
	}
	
	public void setHoTen(java.lang.String hoTen)
	{
		this.hoTen = hoTen;
	}
	
	public java.lang.Float getKiDonVi(){
		return kiDonVi;
	}
	
	public void setKiDonVi(java.lang.Float kiDonVi)
	{
		this.kiDonVi = kiDonVi;
	}
	
	public java.lang.Float getHeSoDieuChinh(){
		return heSoDieuChinh;
	}
	
	public void setHeSoDieuChinh(java.lang.Float heSoDieuChinh)
	{
		this.heSoDieuChinh = heSoDieuChinh;
	}
	
	public java.lang.Float getSoDayTbQuiDoi(){
		return soDayTbQuiDoi;
	}
	
	public void setSoDayTbQuiDoi(java.lang.Float soDayTbQuiDoi)
	{
		this.soDayTbQuiDoi = soDayTbQuiDoi;
	}
	
	public java.lang.Float getDonGia(){
		return donGia;
	}
	
	public void setDonGia(java.lang.Float donGia)
	{
		this.donGia = donGia;
	}
	
	public java.lang.Float getNgayCongTinhLuong(){
		return ngayCongTinhLuong;
	}
	
	public void setNgayCongTinhLuong(java.lang.Float ngayCongTinhLuong)
	{
		this.ngayCongTinhLuong = ngayCongTinhLuong;
	}
	
	public java.lang.Float getNgayCongCheDo(){
		return ngayCongCheDo;
	}
	
	public void setNgayCongCheDo(java.lang.Float ngayCongCheDo)
	{
		this.ngayCongCheDo = ngayCongCheDo;
	}
	
	public java.lang.Float getLuongDuyTri(){
		return luongDuyTri;
	}
	
	public void setLuongDuyTri(java.lang.Float luongDuyTri)
	{
		this.luongDuyTri = luongDuyTri;
	}
	
	public java.lang.Float getPhiBanHang(){
		return phiBanHang;
	}
	
	public void setPhiBanHang(java.lang.Float phiBanHang)
	{
		this.phiBanHang = phiBanHang;
	}
	
	public java.lang.Float getTkm0(){
		return tkm0;
	}
	
	public void setTkm0(java.lang.Float tkm0)
	{
		this.tkm0 = tkm0;
	}
	
	public java.lang.Float getTkm3(){
		return tkm3;
	}
	
	public void setTkm3(java.lang.Float tkm3)
	{
		this.tkm3 = tkm3;
	}
	
	public java.lang.Float getTkm4(){
		return tkm4;
	}
	
	public void setTkm4(java.lang.Float tkm4)
	{
		this.tkm4 = tkm4;
	}
	
	public java.lang.Float getTkm5(){
		return tkm5;
	}
	
	public void setTkm5(java.lang.Float tkm5)
	{
		this.tkm5 = tkm5;
	}
	
	public java.lang.Float getTdv0(){
		return tdv0;
	}
	
	public void setTdv0(java.lang.Float tdv0)
	{
		this.tdv0 = tdv0;
	}
	
	public java.lang.Float getTdv3(){
		return tdv3;
	}
	
	public void setTdv3(java.lang.Float tdv3)
	{
		this.tdv3 = tdv3;
	}
	
	public java.lang.Float getTdv4(){
		return tdv4;
	}
	
	public void setTdv4(java.lang.Float tdv4)
	{
		this.tdv4 = tdv4;
	}
	
	public java.lang.Float getTdv5(){
		return tdv5;
	}
	
	public void setTdv5(java.lang.Float tdv5)
	{
		this.tdv5 = tdv5;
	}
	
	public java.lang.Float getPhatXlsc(){
		return phatXlsc;
	}
	
	public void setPhatXlsc(java.lang.Float phatXlsc)
	{
		this.phatXlsc = phatXlsc;
	}
	
	public java.lang.Float getPhatPakh(){
		return phatPakh;
	}
	
	public void setPhatPakh(java.lang.Float phatPakh)
	{
		this.phatPakh = phatPakh;
	}
	
	public java.lang.Float getLoi1(){
		return loi1;
	}
	
	public void setLoi1(java.lang.Float loi1)
	{
		this.loi1 = loi1;
	}
	
	public java.lang.Float getLoi2(){
		return loi2;
	}
	
	public void setLoi2(java.lang.Float loi2)
	{
		this.loi2 = loi2;
	}
	
	public java.lang.Float getLoi3(){
		return loi3;
	}
	
	public void setLoi3(java.lang.Float loi3)
	{
		this.loi3 = loi3;
	}
	
	public java.lang.Float getLoi4(){
		return loi4;
	}
	
	public void setLoi4(java.lang.Float loi4)
	{
		this.loi4 = loi4;
	}
	
	public java.lang.Float getLoi5(){
		return loi5;
	}
	
	public void setLoi5(java.lang.Float loi5)
	{
		this.loi5 = loi5;
	}
	
	public java.lang.Float getLoi6(){
		return loi6;
	}
	
	public void setLoi6(java.lang.Float loi6)
	{
		this.loi6 = loi6;
	}
	
	public java.lang.Float getLoi7(){
		return loi7;
	}
	
	public void setLoi7(java.lang.Float loi7)
	{
		this.loi7 = loi7;
	}
	
	public java.lang.Float getLoi8(){
		return loi8;
	}
	
	public void setLoi8(java.lang.Float loi8)
	{
		this.loi8 = loi8;
	}
	
	public java.lang.Float getLoi9(){
		return loi9;
	}
	
	public void setLoi9(java.lang.Float loi9)
	{
		this.loi9 = loi9;
	}
	
	public java.lang.Float getLoi10(){
		return loi10;
	}
	
	public void setLoi10(java.lang.Float loi10)
	{
		this.loi10 = loi10;
	}
	
	public java.lang.Float getLoi11(){
		return loi11;
	}
	
	public void setLoi11(java.lang.Float loi11)
	{
		this.loi11 = loi11;
	}
	
	public java.lang.Float getLoi12(){
		return loi12;
	}
	
	public void setLoi12(java.lang.Float loi12)
	{
		this.loi12 = loi12;
	}
	
	public java.lang.Float getLuong(){
		return luong;
	}
	
	public void setLuong(java.lang.Float luong)
	{
		this.luong = luong;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Float getPhatQcxlsc(){
		return phatQcxlsc;
	}
	
	public void setPhatQcxlsc(java.lang.Float phatQcxlsc)
	{
		this.phatQcxlsc = phatQcxlsc;
	}
	
	public java.lang.Float getPhatLdsvtXlsct(){
		return phatLdsvtXlsct;
	}
	
	public void setPhatLdsvtXlsct(java.lang.Float phatLdsvtXlsct)
	{
		this.phatLdsvtXlsct = phatLdsvtXlsct;
	}
	
	public java.lang.Float getPhatGttbrm(){
		return phatGttbrm;
	}
	
	public void setPhatGttbrm(java.lang.Float phatGttbrm)
	{
		this.phatGttbrm = phatGttbrm;
	}
	
	public java.lang.Float getPhatTbcdcnktd(){
		return phatTbcdcnktd;
	}
	
	public void setPhatTbcdcnktd(java.lang.Float phatTbcdcnktd)
	{
		this.phatTbcdcnktd = phatTbcdcnktd;
	}
	
	public java.lang.Float getPhatTbrmktd(){
		return phatTbrmktd;
	}
	
	public void setPhatTbrmktd(java.lang.Float phatTbrmktd)
	{
		this.phatTbrmktd = phatTbrmktd;
	}
	
	public java.lang.Float getPhatLyttd(){
		return phatLyttd;
	}
	
	public void setPhatLyttd(java.lang.Float phatLyttd)
	{
		this.phatLyttd = phatLyttd;
	}
   
    @Override
    public TblLuongDayMayDTO toDTO() {
        TblLuongDayMayDTO tblLuongDayMayDTO = new TblLuongDayMayDTO(); 
        tblLuongDayMayDTO.setTblLuongDayMayId(this.tblLuongDayMayId);		
        tblLuongDayMayDTO.setThang(this.thang);		
        tblLuongDayMayDTO.setTinh(this.tinh);		
        tblLuongDayMayDTO.setHuyen(this.huyen);		
        tblLuongDayMayDTO.setMaNv(this.maNv);		
        tblLuongDayMayDTO.setHoTen(this.hoTen);		
        tblLuongDayMayDTO.setKiDonVi(this.kiDonVi);		
        tblLuongDayMayDTO.setHeSoDieuChinh(this.heSoDieuChinh);		
        tblLuongDayMayDTO.setSoDayTbQuiDoi(this.soDayTbQuiDoi);		
        tblLuongDayMayDTO.setDonGia(this.donGia);		
        tblLuongDayMayDTO.setNgayCongTinhLuong(this.ngayCongTinhLuong);		
        tblLuongDayMayDTO.setNgayCongCheDo(this.ngayCongCheDo);		
        tblLuongDayMayDTO.setLuongDuyTri(this.luongDuyTri);		
        tblLuongDayMayDTO.setPhiBanHang(this.phiBanHang);		
        tblLuongDayMayDTO.setTkm0(this.tkm0);		
        tblLuongDayMayDTO.setTkm3(this.tkm3);		
        tblLuongDayMayDTO.setTkm4(this.tkm4);		
        tblLuongDayMayDTO.setTkm5(this.tkm5);		
        tblLuongDayMayDTO.setTdv0(this.tdv0);		
        tblLuongDayMayDTO.setTdv3(this.tdv3);		
        tblLuongDayMayDTO.setTdv4(this.tdv4);		
        tblLuongDayMayDTO.setTdv5(this.tdv5);		
        tblLuongDayMayDTO.setPhatXlsc(this.phatXlsc);		
        tblLuongDayMayDTO.setPhatPakh(this.phatPakh);		
        tblLuongDayMayDTO.setLoi1(this.loi1);		
        tblLuongDayMayDTO.setLoi2(this.loi2);		
        tblLuongDayMayDTO.setLoi3(this.loi3);		
        tblLuongDayMayDTO.setLoi4(this.loi4);		
        tblLuongDayMayDTO.setLoi5(this.loi5);		
        tblLuongDayMayDTO.setLoi6(this.loi6);		
        tblLuongDayMayDTO.setLoi7(this.loi7);		
        tblLuongDayMayDTO.setLoi8(this.loi8);		
        tblLuongDayMayDTO.setLoi9(this.loi9);		
        tblLuongDayMayDTO.setLoi10(this.loi10);		
        tblLuongDayMayDTO.setLoi11(this.loi11);		
        tblLuongDayMayDTO.setLoi12(this.loi12);		
        tblLuongDayMayDTO.setLuong(this.luong);		
        tblLuongDayMayDTO.setXoa(this.xoa);		
        tblLuongDayMayDTO.setHoatDong(this.hoatDong);		
        tblLuongDayMayDTO.setPhatQcxlsc(this.phatQcxlsc);		
        tblLuongDayMayDTO.setPhatLdsvtXlsct(this.phatLdsvtXlsct);		
        tblLuongDayMayDTO.setPhatGttbrm(this.phatGttbrm);		
        tblLuongDayMayDTO.setPhatTbcdcnktd(this.phatTbcdcnktd);		
        tblLuongDayMayDTO.setPhatTbrmktd(this.phatTbrmktd);		
        tblLuongDayMayDTO.setPhatLyttd(this.phatLyttd);		
        return tblLuongDayMayDTO;
    }
}
