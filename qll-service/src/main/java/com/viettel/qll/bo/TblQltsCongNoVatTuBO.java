package com.viettel.qll.bo;

import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblQltsCongNoVatTuBO")
@Table(name = "TBL_QLTS_CONG_NO_VAT_TU")
/**
 *
 * @author: hailh10
 */
public class TblQltsCongNoVatTuBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_QLTS_CONG_NO_VAT_TU_SEQ") })
	@Column(name = "TBL_QLTS_CONG_NO_VAT_TU_ID", length = 22)
	private java.lang.Long tblQltsCongNoVatTuId;
	@Column(name = "DOI_TUONG_NHAN_NO", length = 200)
	private String doiTuongNhanNo;
	@Column(name = "MA_NV", length = 4000)
	private java.lang.String maNv;
	@Column(name = "DON_VI", length = 4000)
	private java.lang.String donVi;
	@Column(name = "SO_PXK", length = 4000)
	private java.lang.String soPxk;
	@Column(name = "NGAY_CHUNG_TU", length = 7)
	private java.util.Date ngayChungTu;
	@Column(name = "TEN_VAT_TU", length = 4000)
	private java.lang.String tenVatTu;
	@Column(name = "MA_VAT_TU", length = 4000)
	private java.lang.String maVatTu;
	@Column(name = "MA_TRAM", length = 4000)
	private java.lang.String maTram;
	@Column(name = "HANG_MUC", length = 4000)
	private java.lang.String hangMuc;
	@Column(name = "DVT", length = 22)
	private String dvt;
	@Column(name = "GIA", length = 22)
	private java.lang.Float gia;
	@Column(name = "KLXK_SO_LUONG", length = 22)
	private java.lang.Float klxkSoLuong;
	@Column(name = "KLXK_THANH_TIEN", length = 22)
	private java.lang.Float klxkThanhTien;
	@Column(name = "SNT_SO_LUONG", length = 22)
	private java.lang.Float sntSoLuong;
	@Column(name = "SNT_THANH_TIEN", length = 22)
	private java.lang.Float sntThanhTien;
	@Column(name = "SHHTDM_SO_LUONG", length = 22)
	private java.lang.Float shhtdmSoLuong;
	@Column(name = "SHHTDM_THANH_TIEN", length = 22)
	private java.lang.Float shhtdmThanhTien;
	@Column(name = "SDTTH_SO_LUONG", length = 22)
	private java.lang.Float sdtthSoLuong;
	@Column(name = "SDTTH_THANH_TIEN", length = 22)
	private java.lang.Float sdtthThanhTien;
	@Column(name = "SMMPBTCCT_SO_LUONG", length = 22)
	private java.lang.Float smmpbtcctSoLuong;
	@Column(name = "SMMPBTCCT_THANH_TIEN", length = 22)
	private java.lang.Float smmpbtcctThanhTien;
	@Column(name = "GHI_CHU", length = 4000)
	private java.lang.String ghiChu;
	@Column(name = "XOA", length = 22)
	private Boolean xoa;
	@Column(name = "HOAT_DONG", length = 22)
	private Boolean hoatDong;
	@Column(name = "TYPE", length = 22)
	private java.lang.Float type;
	
	@Column(name = "CHECK_DD", length = 22)
	private java.lang.Long checkDD;
	
	@Column(name = "CHECK_NL", length = 22)
	private java.lang.Long checkNl;

	
	public java.lang.Long getTblQltsCongNoVatTuId(){
		return tblQltsCongNoVatTuId;
	}
	
	public void setTblQltsCongNoVatTuId(java.lang.Long tblQltsCongNoVatTuId)
	{
		this.tblQltsCongNoVatTuId = tblQltsCongNoVatTuId;
	}
	
	public java.lang.String getDoiTuongNhanNo(){
		return doiTuongNhanNo;
	}
	
	public void setDoiTuongNhanNo(java.lang.String doiTuongNhanNo)
	{
		this.doiTuongNhanNo = doiTuongNhanNo;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getDonVi(){
		return donVi;
	}
	
	public void setDonVi(java.lang.String donVi)
	{
		this.donVi = donVi;
	}
	
	public java.lang.String getSoPxk(){
		return soPxk;
	}
	
	public void setSoPxk(java.lang.String soPxk)
	{
		this.soPxk = soPxk;
	}
	
	public java.util.Date getNgayChungTu(){
		return ngayChungTu;
	}
	
	public void setNgayChungTu(java.util.Date ngayChungTu)
	{
		this.ngayChungTu = ngayChungTu;
	}
	
	public java.lang.String getTenVatTu(){
		return tenVatTu;
	}
	
	public void setTenVatTu(java.lang.String tenVatTu)
	{
		this.tenVatTu = tenVatTu;
	}
	
	public java.lang.String getMaVatTu(){
		return maVatTu;
	}
	
	public void setMaVatTu(java.lang.String maVatTu)
	{
		this.maVatTu = maVatTu;
	}
	
	public java.lang.String getMaTram(){
		return maTram;
	}
	
	public void setMaTram(java.lang.String maTram)
	{
		this.maTram = maTram;
	}
	
	public java.lang.String getHangMuc(){
		return hangMuc;
	}
	
	public void setHangMuc(java.lang.String hangMuc)
	{
		this.hangMuc = hangMuc;
	}
	
	public java.lang.String getDvt(){
		return dvt;
	}
	
	public void setDvt(java.lang.String dvt)
	{
		this.dvt = dvt;
	}
	
	public java.lang.Float getGia(){
		return gia;
	}
	
	public void setGia(java.lang.Float gia)
	{
		this.gia = gia;
	}
	
	public java.lang.Float getKlxkSoLuong(){
		return klxkSoLuong;
	}
	
	public void setKlxkSoLuong(java.lang.Float klxkSoLuong)
	{
		this.klxkSoLuong = klxkSoLuong;
	}
	
	public java.lang.Float getKlxkThanhTien(){
		return klxkThanhTien;
	}
	
	public void setKlxkThanhTien(java.lang.Float klxkThanhTien)
	{
		this.klxkThanhTien = klxkThanhTien;
	}
	
	public java.lang.Float getSntSoLuong(){
		return sntSoLuong;
	}
	
	public void setSntSoLuong(java.lang.Float sntSoLuong)
	{
		this.sntSoLuong = sntSoLuong;
	}
	
	public java.lang.Float getSntThanhTien(){
		return sntThanhTien;
	}
	
	public void setSntThanhTien(java.lang.Float sntThanhTien)
	{
		this.sntThanhTien = sntThanhTien;
	}
	
	public java.lang.Float getShhtdmSoLuong(){
		return shhtdmSoLuong;
	}
	
	public void setShhtdmSoLuong(java.lang.Float shhtdmSoLuong)
	{
		this.shhtdmSoLuong = shhtdmSoLuong;
	}
	
	public java.lang.Float getShhtdmThanhTien(){
		return shhtdmThanhTien;
	}
	
	public void setShhtdmThanhTien(java.lang.Float shhtdmThanhTien)
	{
		this.shhtdmThanhTien = shhtdmThanhTien;
	}
	
	public java.lang.Float getSdtthSoLuong(){
		return sdtthSoLuong;
	}
	
	public void setSdtthSoLuong(java.lang.Float sdtthSoLuong)
	{
		this.sdtthSoLuong = sdtthSoLuong;
	}
	
	public java.lang.Float getSdtthThanhTien(){
		return sdtthThanhTien;
	}
	
	public void setSdtthThanhTien(java.lang.Float sdtthThanhTien)
	{
		this.sdtthThanhTien = sdtthThanhTien;
	}
	
	public java.lang.Float getSmmpbtcctSoLuong(){
		return smmpbtcctSoLuong;
	}
	
	public void setSmmpbtcctSoLuong(java.lang.Float smmpbtcctSoLuong)
	{
		this.smmpbtcctSoLuong = smmpbtcctSoLuong;
	}
	
	public java.lang.Float getSmmpbtcctThanhTien(){
		return smmpbtcctThanhTien;
	}
	
	public void setSmmpbtcctThanhTien(java.lang.Float smmpbtcctThanhTien)
	{
		this.smmpbtcctThanhTien = smmpbtcctThanhTien;
	}
	
	public java.lang.String getGhiChu(){
		return ghiChu;
	}
	
	public void setGhiChu(java.lang.String ghiChu)
	{
		this.ghiChu = ghiChu;
	}
	
	public java.lang.Boolean getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Boolean xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Boolean getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Boolean hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
   
    public java.lang.Float getType() {
		return type;
	}

	public void setType(java.lang.Float type) {
		this.type = type;
	}

	
	
	public java.lang.Long getCheckDD() {
		return checkDD;
	}

	public void setCheckDD(java.lang.Long checkDD) {
		this.checkDD = checkDD;
	}
	

	public java.lang.Long getCheckNl() {
		return checkNl;
	}

	public void setCheckNl(java.lang.Long checkNl) {
		this.checkNl = checkNl;
	}

	@Override
    public TblQltsCongNoVatTuDTO toDTO() {
        TblQltsCongNoVatTuDTO tblQltsCongNoVatTuDTO = new TblQltsCongNoVatTuDTO(); 
        tblQltsCongNoVatTuDTO.setTblQltsCongNoVatTuId(this.tblQltsCongNoVatTuId);		
        tblQltsCongNoVatTuDTO.setDoiTuongNhanNo(this.doiTuongNhanNo);		
        tblQltsCongNoVatTuDTO.setMaNv(this.maNv);		
        tblQltsCongNoVatTuDTO.setDonVi(this.donVi);		
        tblQltsCongNoVatTuDTO.setSoPxk(this.soPxk);		
        tblQltsCongNoVatTuDTO.setNgayChungTu(this.ngayChungTu);		
        tblQltsCongNoVatTuDTO.setTenVatTu(this.tenVatTu);		
        tblQltsCongNoVatTuDTO.setMaVatTu(this.maVatTu);		
        tblQltsCongNoVatTuDTO.setMaTram(this.maTram);		
        tblQltsCongNoVatTuDTO.setHangMuc(this.hangMuc);		
        tblQltsCongNoVatTuDTO.setDvt(this.dvt);		
        tblQltsCongNoVatTuDTO.setGia(this.gia);		
        tblQltsCongNoVatTuDTO.setKlxkSoLuong(this.klxkSoLuong);		
        tblQltsCongNoVatTuDTO.setKlxkThanhTien(this.klxkThanhTien);		
        tblQltsCongNoVatTuDTO.setSntSoLuong(this.sntSoLuong);		
        tblQltsCongNoVatTuDTO.setSntThanhTien(this.sntThanhTien);		
        tblQltsCongNoVatTuDTO.setShhtdmSoLuong(this.shhtdmSoLuong);		
        tblQltsCongNoVatTuDTO.setShhtdmThanhTien(this.shhtdmThanhTien);		
        tblQltsCongNoVatTuDTO.setSdtthSoLuong(this.sdtthSoLuong);		
        tblQltsCongNoVatTuDTO.setSdtthThanhTien(this.sdtthThanhTien);		
        tblQltsCongNoVatTuDTO.setSmmpbtcctSoLuong(this.smmpbtcctSoLuong);		
        tblQltsCongNoVatTuDTO.setSmmpbtcctThanhTien(this.smmpbtcctThanhTien);		
        tblQltsCongNoVatTuDTO.setGhiChu(this.ghiChu);		
        tblQltsCongNoVatTuDTO.setXoa(this.xoa);		
        tblQltsCongNoVatTuDTO.setHoatDong(this.hoatDong);	
        tblQltsCongNoVatTuDTO.setType(this.type);	
        tblQltsCongNoVatTuDTO.setCheckDD(this.checkDD);
        tblQltsCongNoVatTuDTO.setCheckNl(this.checkNl);
        return tblQltsCongNoVatTuDTO;
    }
	
	
}
