package com.viettel.qll.bo;

import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblDlDauVaoDayMayBO")
@Table(name = "TBL_DL_DAU_VAO_DAY_MAY")
/**
 *
 * @author: hailh10
 */
public class TblDlDauVaoDayMayBO extends BaseFWModelImpl {
     
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "HO_VA_TEN", length = 600)
	private java.lang.String hoVaTen;
	@Column(name = "DON_VI", length = 300)
	private java.lang.String donVi;
	@Column(name = "MA_TINH", length = 600)
	private java.lang.String maTinh;
	@Column(name = "TEN_HUYEN", length = 600)
	private java.lang.String tenHuyen;
	@Column(name = "GHEP", length = 600)
	private java.lang.String ghep;
	@Column(name = "DOI_TUONG", length = 600)
	private java.lang.String doiTuong;
	@Column(name = "DDTBQL_DAY_TBKT", length = 22)
	private java.lang.Float ddtbqlDayTbkt;
	@Column(name = "DDTBQL_DAY_TB_AON", length = 22)
	private java.lang.Float ddtbqlDayTbAon;
	@Column(name = "DDTBQL_DA_TOA_NHA", length = 22)
	private java.lang.Float ddtbqlDaToaNha;
	@Column(name = "DDTBQL_GPON_ADSL_PSTN_EOC", length = 22)
	private java.lang.Float ddtbqlGponAdslPstnEoc;
	@Column(name = "DDTBQL_TONG_QUY_DOI", length = 22)
	private java.lang.Float ddtbqlTongQuyDoi;
	@Column(name = "DDTBQL_HUONG_LUONG_DUY_TRI", length = 22)
	private java.lang.Float ddtbqlHuongLuongDuyTri;
	@Column(name = "TBMTBKT_0_2", length = 22)
	private java.lang.Float tbmtbkt02;
	@Column(name = "PHAT_QCXLSC", length = 22)
	private java.lang.Float phatQcxlsc;
	@Column(name = "TBMTBKT_3", length = 22)
	private java.lang.Float tbmtbkt3;
	@Column(name = "TBMTBKT_4", length = 22)
	private java.lang.Float tbmtbkt4;
	@Column(name = "TBMTBKT_5", length = 22)
	private java.lang.Float tbmtbkt5;
	@Column(name = "TBMTBKT_6", length = 22)
	private java.lang.Float tbmtbkt6;
	@Column(name = "TBMTBKT_7", length = 22)
	private java.lang.Float tbmtbkt7;
	@Column(name = "TBMTBT_0_2", length = 22)
	private java.lang.Float tbmtbt02;
	@Column(name = "PHAT_LDSVT_XLSCT", length = 22)
	private java.lang.Float phatLdsvtXlsct;
	@Column(name = "TBMTBT_3", length = 22)
	private java.lang.Float tbmtbt3;
	@Column(name = "TBMTBT_4", length = 22)
	private java.lang.Float tbmtbt4;
	@Column(name = "TBMTBT_5", length = 22)
	private java.lang.Float tbmtbt5;
	@Column(name = "TBMTBT_6", length = 22)
	private java.lang.Float tbmtbt6;
	@Column(name = "TBMTBT_7", length = 22)
	private java.lang.Float tbmtbt7;
	@Column(name = "TBMTBDV_0_2", length = 22)
	private java.lang.Float tbmtbdv02;
	@Column(name = "PHAT_GTTBRM", length = 22)
	private java.lang.Float phatGttbrm;
	@Column(name = "TBMTBDV_3", length = 22)
	private java.lang.Float tbmtbdv3;
	@Column(name = "TBMTBDV_4", length = 22)
	private java.lang.Float tbmtbdv4;
	@Column(name = "TBMTBDV_5", length = 22)
	private java.lang.Float tbmtbdv5;
	@Column(name = "TBMTBDV_6", length = 22)
	private java.lang.Float tbmtbdv6;
	@Column(name = "TBMTBDV_7", length = 22)
	private java.lang.Float tbmtbdv7;
	@Column(name = "TBMTBCS_0_2", length = 22)
	private java.lang.Float tbmtbcs02;
	@Column(name = "TBMTBCS_3", length = 22)
	private java.lang.Float tbmtbcs3;
	@Column(name = "TBMTBCS_4", length = 22)
	private java.lang.Float tbmtbcs4;
	@Column(name = "TBMTBCS_5", length = 22)
	private java.lang.Float tbmtbcs5;
	@Column(name = "TBMTBCS_6", length = 22)
	private java.lang.Float tbmtbcs6;
	@Column(name = "TBMTBWF_0_2", length = 22)
	private java.lang.Float tbmtbwf02;
	@Column(name = "TBMTBWF_3", length = 22)
	private java.lang.Float tbmtbwf3;
	@Column(name = "TBMTBWF_4", length = 22)
	private java.lang.Float tbmtbwf4;
	@Column(name = "TBMTBWF_5", length = 22)
	private java.lang.Float tbmtbwf5;
	@Column(name = "TBMTBWF_6", length = 22)
	private java.lang.Float tbmtbwf6;
	@Column(name = "NHOM_TRUONG", length = 22)
	private java.lang.Float nhomTruong;
	@Column(name = "GHI_CHU", length = 600)
	private java.lang.String ghiChu;
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "KY_LUONG", length = 300)
	private java.lang.String kyLuong;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_DL_DAU_VAO_DAY_MAY_SEQ") })
	@Column(name = "TBL_DL_DAU_VAO_DAY_MAY_ID", length = 22)
	private java.lang.Long tblDlDauVaoDayMayId;
	@Column(name = "PHAT_LYTTD", length = 22)
	private java.lang.Float phatLyttd;
	@Column(name = "PHAT_TBCDCNKTD", length = 22)
	private java.lang.Float phatTbcdcnktd;
	@Column(name = "PHAT_TBRMKTD", length = 22)
	private java.lang.Float phatTbrmktd;

	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getHoVaTen(){
		return hoVaTen;
	}
	
	public void setHoVaTen(java.lang.String hoVaTen)
	{
		this.hoVaTen = hoVaTen;
	}
	
	public java.lang.String getDonVi(){
		return donVi;
	}
	
	public void setDonVi(java.lang.String donVi)
	{
		this.donVi = donVi;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
	
	public java.lang.String getTenHuyen(){
		return tenHuyen;
	}
	
	public void setTenHuyen(java.lang.String tenHuyen)
	{
		this.tenHuyen = tenHuyen;
	}
	
	public java.lang.String getGhep(){
		return ghep;
	}
	
	public void setGhep(java.lang.String ghep)
	{
		this.ghep = ghep;
	}
	
	public java.lang.String getDoiTuong(){
		return doiTuong;
	}
	
	public void setDoiTuong(java.lang.String doiTuong)
	{
		this.doiTuong = doiTuong;
	}
	
	public java.lang.Float getDdtbqlDayTbkt(){
		return ddtbqlDayTbkt;
	}
	
	public void setDdtbqlDayTbkt(java.lang.Float ddtbqlDayTbkt)
	{
		this.ddtbqlDayTbkt = ddtbqlDayTbkt;
	}
	
	public java.lang.Float getDdtbqlDayTbAon(){
		return ddtbqlDayTbAon;
	}
	
	public void setDdtbqlDayTbAon(java.lang.Float ddtbqlDayTbAon)
	{
		this.ddtbqlDayTbAon = ddtbqlDayTbAon;
	}
	
	public java.lang.Float getDdtbqlDaToaNha(){
		return ddtbqlDaToaNha;
	}
	
	public void setDdtbqlDaToaNha(java.lang.Float ddtbqlDaToaNha)
	{
		this.ddtbqlDaToaNha = ddtbqlDaToaNha;
	}
	
	public java.lang.Float getDdtbqlGponAdslPstnEoc(){
		return ddtbqlGponAdslPstnEoc;
	}
	
	public void setDdtbqlGponAdslPstnEoc(java.lang.Float ddtbqlGponAdslPstnEoc)
	{
		this.ddtbqlGponAdslPstnEoc = ddtbqlGponAdslPstnEoc;
	}
	
	public java.lang.Float getDdtbqlTongQuyDoi(){
		return ddtbqlTongQuyDoi;
	}
	
	public void setDdtbqlTongQuyDoi(java.lang.Float ddtbqlTongQuyDoi)
	{
		this.ddtbqlTongQuyDoi = ddtbqlTongQuyDoi;
	}
	
	public java.lang.Float getDdtbqlHuongLuongDuyTri(){
		return ddtbqlHuongLuongDuyTri;
	}
	
	public void setDdtbqlHuongLuongDuyTri(java.lang.Float ddtbqlHuongLuongDuyTri)
	{
		this.ddtbqlHuongLuongDuyTri = ddtbqlHuongLuongDuyTri;
	}
	
	public java.lang.Float getTbmtbkt02(){
		return tbmtbkt02;
	}
	
	public void setTbmtbkt02(java.lang.Float tbmtbkt02)
	{
		this.tbmtbkt02 = tbmtbkt02;
	}
	
	public java.lang.Float getPhatQcxlsc(){
		return phatQcxlsc;
	}
	
	public void setPhatQcxlsc(java.lang.Float phatQcxlsc)
	{
		this.phatQcxlsc = phatQcxlsc;
	}
	
	public java.lang.Float getTbmtbkt3(){
		return tbmtbkt3;
	}
	
	public void setTbmtbkt3(java.lang.Float tbmtbkt3)
	{
		this.tbmtbkt3 = tbmtbkt3;
	}
	
	public java.lang.Float getTbmtbkt4(){
		return tbmtbkt4;
	}
	
	public void setTbmtbkt4(java.lang.Float tbmtbkt4)
	{
		this.tbmtbkt4 = tbmtbkt4;
	}
	
	public java.lang.Float getTbmtbkt5(){
		return tbmtbkt5;
	}
	
	public void setTbmtbkt5(java.lang.Float tbmtbkt5)
	{
		this.tbmtbkt5 = tbmtbkt5;
	}
	
	public java.lang.Float getTbmtbkt6(){
		return tbmtbkt6;
	}
	
	public void setTbmtbkt6(java.lang.Float tbmtbkt6)
	{
		this.tbmtbkt6 = tbmtbkt6;
	}
	
	public java.lang.Float getTbmtbkt7(){
		return tbmtbkt7;
	}
	
	public void setTbmtbkt7(java.lang.Float tbmtbkt7)
	{
		this.tbmtbkt7 = tbmtbkt7;
	}
	
	public java.lang.Float getTbmtbt02(){
		return tbmtbt02;
	}
	
	public void setTbmtbt02(java.lang.Float tbmtbt02)
	{
		this.tbmtbt02 = tbmtbt02;
	}
	
	public java.lang.Float getPhatLdsvtXlsct(){
		return phatLdsvtXlsct;
	}
	
	public void setPhatLdsvtXlsct(java.lang.Float phatLdsvtXlsct)
	{
		this.phatLdsvtXlsct = phatLdsvtXlsct;
	}
	
	public java.lang.Float getTbmtbt3(){
		return tbmtbt3;
	}
	
	public void setTbmtbt3(java.lang.Float tbmtbt3)
	{
		this.tbmtbt3 = tbmtbt3;
	}
	
	public java.lang.Float getTbmtbt4(){
		return tbmtbt4;
	}
	
	public void setTbmtbt4(java.lang.Float tbmtbt4)
	{
		this.tbmtbt4 = tbmtbt4;
	}
	
	public java.lang.Float getTbmtbt5(){
		return tbmtbt5;
	}
	
	public void setTbmtbt5(java.lang.Float tbmtbt5)
	{
		this.tbmtbt5 = tbmtbt5;
	}
	
	public java.lang.Float getTbmtbt6(){
		return tbmtbt6;
	}
	
	public void setTbmtbt6(java.lang.Float tbmtbt6)
	{
		this.tbmtbt6 = tbmtbt6;
	}
	
	public java.lang.Float getTbmtbt7(){
		return tbmtbt7;
	}
	
	public void setTbmtbt7(java.lang.Float tbmtbt7)
	{
		this.tbmtbt7 = tbmtbt7;
	}
	
	public java.lang.Float getTbmtbdv02(){
		return tbmtbdv02;
	}
	
	public void setTbmtbdv02(java.lang.Float tbmtbdv02)
	{
		this.tbmtbdv02 = tbmtbdv02;
	}
	
	public java.lang.Float getPhatGttbrm(){
		return phatGttbrm;
	}
	
	public void setPhatGttbrm(java.lang.Float phatGttbrm)
	{
		this.phatGttbrm = phatGttbrm;
	}
	
	public java.lang.Float getTbmtbdv3(){
		return tbmtbdv3;
	}
	
	public void setTbmtbdv3(java.lang.Float tbmtbdv3)
	{
		this.tbmtbdv3 = tbmtbdv3;
	}
	
	public java.lang.Float getTbmtbdv4(){
		return tbmtbdv4;
	}
	
	public void setTbmtbdv4(java.lang.Float tbmtbdv4)
	{
		this.tbmtbdv4 = tbmtbdv4;
	}
	
	public java.lang.Float getTbmtbdv5(){
		return tbmtbdv5;
	}
	
	public void setTbmtbdv5(java.lang.Float tbmtbdv5)
	{
		this.tbmtbdv5 = tbmtbdv5;
	}
	
	public java.lang.Float getTbmtbdv6(){
		return tbmtbdv6;
	}
	
	public void setTbmtbdv6(java.lang.Float tbmtbdv6)
	{
		this.tbmtbdv6 = tbmtbdv6;
	}
	
	public java.lang.Float getTbmtbdv7(){
		return tbmtbdv7;
	}
	
	public void setTbmtbdv7(java.lang.Float tbmtbdv7)
	{
		this.tbmtbdv7 = tbmtbdv7;
	}
	
	public java.lang.Float getTbmtbcs02(){
		return tbmtbcs02;
	}
	
	public void setTbmtbcs02(java.lang.Float tbmtbcs02)
	{
		this.tbmtbcs02 = tbmtbcs02;
	}
	
	public java.lang.Float getTbmtbcs3(){
		return tbmtbcs3;
	}
	
	public void setTbmtbcs3(java.lang.Float tbmtbcs3)
	{
		this.tbmtbcs3 = tbmtbcs3;
	}
	
	public java.lang.Float getTbmtbcs4(){
		return tbmtbcs4;
	}
	
	public void setTbmtbcs4(java.lang.Float tbmtbcs4)
	{
		this.tbmtbcs4 = tbmtbcs4;
	}
	
	public java.lang.Float getTbmtbcs5(){
		return tbmtbcs5;
	}
	
	public void setTbmtbcs5(java.lang.Float tbmtbcs5)
	{
		this.tbmtbcs5 = tbmtbcs5;
	}
	
	public java.lang.Float getTbmtbcs6(){
		return tbmtbcs6;
	}
	
	public void setTbmtbcs6(java.lang.Float tbmtbcs6)
	{
		this.tbmtbcs6 = tbmtbcs6;
	}
	
	public java.lang.Float getTbmtbwf02(){
		return tbmtbwf02;
	}
	
	public void setTbmtbwf02(java.lang.Float tbmtbwf02)
	{
		this.tbmtbwf02 = tbmtbwf02;
	}
	
	public java.lang.Float getTbmtbwf3(){
		return tbmtbwf3;
	}
	
	public void setTbmtbwf3(java.lang.Float tbmtbwf3)
	{
		this.tbmtbwf3 = tbmtbwf3;
	}
	
	public java.lang.Float getTbmtbwf4(){
		return tbmtbwf4;
	}
	
	public void setTbmtbwf4(java.lang.Float tbmtbwf4)
	{
		this.tbmtbwf4 = tbmtbwf4;
	}
	
	public java.lang.Float getTbmtbwf5(){
		return tbmtbwf5;
	}
	
	public void setTbmtbwf5(java.lang.Float tbmtbwf5)
	{
		this.tbmtbwf5 = tbmtbwf5;
	}
	
	public java.lang.Float getTbmtbwf6(){
		return tbmtbwf6;
	}
	
	public void setTbmtbwf6(java.lang.Float tbmtbwf6)
	{
		this.tbmtbwf6 = tbmtbwf6;
	}
	
	public java.lang.Float getNhomTruong(){
		return nhomTruong;
	}
	
	public void setNhomTruong(java.lang.Float nhomTruong)
	{
		this.nhomTruong = nhomTruong;
	}
	
	public java.lang.String getGhiChu(){
		return ghiChu;
	}
	
	public void setGhiChu(java.lang.String ghiChu)
	{
		this.ghiChu = ghiChu;
	}
	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.String getKyLuong(){
		return kyLuong;
	}
	
	public void setKyLuong(java.lang.String kyLuong)
	{
		this.kyLuong = kyLuong;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblDlDauVaoDayMayId(){
		return tblDlDauVaoDayMayId;
	}
	
	public void setTblDlDauVaoDayMayId(java.lang.Long tblDlDauVaoDayMayId)
	{
		this.tblDlDauVaoDayMayId = tblDlDauVaoDayMayId;
	}
	
	public java.lang.Float getPhatLyttd(){
		return phatLyttd;
	}
	
	public void setPhatLyttd(java.lang.Float phatLyttd)
	{
		this.phatLyttd = phatLyttd;
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
   
    @Override
    public TblDlDauVaoDayMayDTO toDTO() {
        TblDlDauVaoDayMayDTO tblDlDauVaoDayMayDTO = new TblDlDauVaoDayMayDTO(); 
        tblDlDauVaoDayMayDTO.setMaNv(this.maNv);		
        tblDlDauVaoDayMayDTO.setHoVaTen(this.hoVaTen);		
        tblDlDauVaoDayMayDTO.setDonVi(this.donVi);		
        tblDlDauVaoDayMayDTO.setMaTinh(this.maTinh);		
        tblDlDauVaoDayMayDTO.setTenHuyen(this.tenHuyen);		
        tblDlDauVaoDayMayDTO.setGhep(this.ghep);		
        tblDlDauVaoDayMayDTO.setDoiTuong(this.doiTuong);		
        tblDlDauVaoDayMayDTO.setDdtbqlDayTbkt(this.ddtbqlDayTbkt);		
        tblDlDauVaoDayMayDTO.setDdtbqlDayTbAon(this.ddtbqlDayTbAon);		
        tblDlDauVaoDayMayDTO.setDdtbqlDaToaNha(this.ddtbqlDaToaNha);		
        tblDlDauVaoDayMayDTO.setDdtbqlGponAdslPstnEoc(this.ddtbqlGponAdslPstnEoc);		
        tblDlDauVaoDayMayDTO.setDdtbqlTongQuyDoi(this.ddtbqlTongQuyDoi);		
        tblDlDauVaoDayMayDTO.setDdtbqlHuongLuongDuyTri(this.ddtbqlHuongLuongDuyTri);		
        tblDlDauVaoDayMayDTO.setTbmtbkt02(this.tbmtbkt02);		
        tblDlDauVaoDayMayDTO.setPhatQcxlsc(this.phatQcxlsc);		
        tblDlDauVaoDayMayDTO.setTbmtbkt3(this.tbmtbkt3);		
        tblDlDauVaoDayMayDTO.setTbmtbkt4(this.tbmtbkt4);		
        tblDlDauVaoDayMayDTO.setTbmtbkt5(this.tbmtbkt5);		
        tblDlDauVaoDayMayDTO.setTbmtbkt6(this.tbmtbkt6);		
        tblDlDauVaoDayMayDTO.setTbmtbkt7(this.tbmtbkt7);		
        tblDlDauVaoDayMayDTO.setTbmtbt02(this.tbmtbt02);		
        tblDlDauVaoDayMayDTO.setPhatLdsvtXlsct(this.phatLdsvtXlsct);		
        tblDlDauVaoDayMayDTO.setTbmtbt3(this.tbmtbt3);		
        tblDlDauVaoDayMayDTO.setTbmtbt4(this.tbmtbt4);		
        tblDlDauVaoDayMayDTO.setTbmtbt5(this.tbmtbt5);		
        tblDlDauVaoDayMayDTO.setTbmtbt6(this.tbmtbt6);		
        tblDlDauVaoDayMayDTO.setTbmtbt7(this.tbmtbt7);		
        tblDlDauVaoDayMayDTO.setTbmtbdv02(this.tbmtbdv02);		
        tblDlDauVaoDayMayDTO.setPhatGttbrm(this.phatGttbrm);		
        tblDlDauVaoDayMayDTO.setTbmtbdv3(this.tbmtbdv3);		
        tblDlDauVaoDayMayDTO.setTbmtbdv4(this.tbmtbdv4);		
        tblDlDauVaoDayMayDTO.setTbmtbdv5(this.tbmtbdv5);		
        tblDlDauVaoDayMayDTO.setTbmtbdv6(this.tbmtbdv6);		
        tblDlDauVaoDayMayDTO.setTbmtbdv7(this.tbmtbdv7);		
        tblDlDauVaoDayMayDTO.setTbmtbcs02(this.tbmtbcs02);		
        tblDlDauVaoDayMayDTO.setTbmtbcs3(this.tbmtbcs3);		
        tblDlDauVaoDayMayDTO.setTbmtbcs4(this.tbmtbcs4);		
        tblDlDauVaoDayMayDTO.setTbmtbcs5(this.tbmtbcs5);		
        tblDlDauVaoDayMayDTO.setTbmtbcs6(this.tbmtbcs6);		
        tblDlDauVaoDayMayDTO.setTbmtbwf02(this.tbmtbwf02);		
        tblDlDauVaoDayMayDTO.setTbmtbwf3(this.tbmtbwf3);		
        tblDlDauVaoDayMayDTO.setTbmtbwf4(this.tbmtbwf4);		
        tblDlDauVaoDayMayDTO.setTbmtbwf5(this.tbmtbwf5);		
        tblDlDauVaoDayMayDTO.setTbmtbwf6(this.tbmtbwf6);		
        tblDlDauVaoDayMayDTO.setNhomTruong(this.nhomTruong);		
        tblDlDauVaoDayMayDTO.setGhiChu(this.ghiChu);		
        tblDlDauVaoDayMayDTO.setXoa(this.xoa);		
        tblDlDauVaoDayMayDTO.setKyLuong(this.kyLuong);		
        tblDlDauVaoDayMayDTO.setHoatDong(this.hoatDong);		
        tblDlDauVaoDayMayDTO.setTblDlDauVaoDayMayId(this.tblDlDauVaoDayMayId);		
        tblDlDauVaoDayMayDTO.setPhatLyttd(this.phatLyttd);		
        tblDlDauVaoDayMayDTO.setPhatTbcdcnktd(this.phatTbcdcnktd);		
        tblDlDauVaoDayMayDTO.setPhatTbrmktd(this.phatTbrmktd);		
        return tblDlDauVaoDayMayDTO;
    }
}
