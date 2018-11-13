package com.viettel.qll.dto;

import com.viettel.qll.bo.TblDlDauVaoDayMayBO;
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
@XmlRootElement(name = "TBL_DL_DAU_VAO_DAY_MAYBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblDlDauVaoDayMayDTO extends QllBaseDTO<TblDlDauVaoDayMayBO> {
	private java.lang.String maNv;
	private java.lang.String hoVaTen;
	private java.lang.String donVi;
	private java.lang.String maTinh;
	private java.lang.String tenHuyen;
	private java.lang.String ghep;
	private java.lang.String doiTuong;
	private java.lang.Float ddtbqlDayTbkt;
	private java.lang.Float ddtbqlDayTbAon;
	private java.lang.Float ddtbqlDaToaNha;
	private java.lang.Float ddtbqlGponAdslPstnEoc;
	private java.lang.Float ddtbqlTongQuyDoi;
	private java.lang.Float ddtbqlHuongLuongDuyTri;
	private java.lang.Float tbmtbkt02;
	private java.lang.Float phatQcxlsc;
	private java.lang.Float tbmtbkt3;
	private java.lang.Float tbmtbkt4;
	private java.lang.Float tbmtbkt5;
	private java.lang.Float tbmtbkt6;
	private java.lang.Float tbmtbkt7;
	private java.lang.Float tbmtbt02;
	private java.lang.Float phatLdsvtXlsct;
	private java.lang.Float tbmtbt3;
	private java.lang.Float tbmtbt4;
	private java.lang.Float tbmtbt5;
	private java.lang.Float tbmtbt6;
	private java.lang.Float tbmtbt7;
	private java.lang.Float tbmtbdv02;
	private java.lang.Float phatGttbrm;
	private java.lang.Float tbmtbdv3;
	private java.lang.Float tbmtbdv4;
	private java.lang.Float tbmtbdv5;
	private java.lang.Float tbmtbdv6;
	private java.lang.Float tbmtbdv7;
	private java.lang.Float tbmtbcs02;
	private java.lang.Float tbmtbcs3;
	private java.lang.Float tbmtbcs4;
	private java.lang.Float tbmtbcs5;
	private java.lang.Float tbmtbcs6;
	private java.lang.Float tbmtbwf02;
	private java.lang.Float tbmtbwf3;
	private java.lang.Float tbmtbwf4;
	private java.lang.Float tbmtbwf5;
	private java.lang.Float tbmtbwf6;
	private java.lang.Float nhomTruong;
	private java.lang.String ghiChu;
	private java.lang.Long xoa;
	private java.lang.String kyLuong;
	private java.lang.Long hoatDong;
	private java.lang.Long tblDlDauVaoDayMayId;
	private java.lang.Float phatLyttd;
	private java.lang.Float phatTbcdcnktd;
	private java.lang.Float phatTbrmktd;
	private String text;
	private int start;
	private int maxResult;
	private String exThang;
	private String exNam;
    @Override
    public TblDlDauVaoDayMayBO toModel() {
        TblDlDauVaoDayMayBO tblDlDauVaoDayMayBO = new TblDlDauVaoDayMayBO();
        tblDlDauVaoDayMayBO.setMaNv(this.maNv);
        tblDlDauVaoDayMayBO.setHoVaTen(this.hoVaTen);
        tblDlDauVaoDayMayBO.setDonVi(this.donVi);
        tblDlDauVaoDayMayBO.setMaTinh(this.maTinh);
        tblDlDauVaoDayMayBO.setTenHuyen(this.tenHuyen);
        tblDlDauVaoDayMayBO.setGhep(this.ghep);
        tblDlDauVaoDayMayBO.setDoiTuong(this.doiTuong);
        tblDlDauVaoDayMayBO.setDdtbqlDayTbkt(this.ddtbqlDayTbkt);
        tblDlDauVaoDayMayBO.setDdtbqlDayTbAon(this.ddtbqlDayTbAon);
        tblDlDauVaoDayMayBO.setDdtbqlDaToaNha(this.ddtbqlDaToaNha);
        tblDlDauVaoDayMayBO.setDdtbqlGponAdslPstnEoc(this.ddtbqlGponAdslPstnEoc);
        tblDlDauVaoDayMayBO.setDdtbqlTongQuyDoi(this.ddtbqlTongQuyDoi);
        tblDlDauVaoDayMayBO.setDdtbqlHuongLuongDuyTri(this.ddtbqlHuongLuongDuyTri);
        tblDlDauVaoDayMayBO.setTbmtbkt02(this.tbmtbkt02);
        tblDlDauVaoDayMayBO.setPhatQcxlsc(this.phatQcxlsc);
        tblDlDauVaoDayMayBO.setTbmtbkt3(this.tbmtbkt3);
        tblDlDauVaoDayMayBO.setTbmtbkt4(this.tbmtbkt4);
        tblDlDauVaoDayMayBO.setTbmtbkt5(this.tbmtbkt5);
        tblDlDauVaoDayMayBO.setTbmtbkt6(this.tbmtbkt6);
        tblDlDauVaoDayMayBO.setTbmtbkt7(this.tbmtbkt7);
        tblDlDauVaoDayMayBO.setTbmtbt02(this.tbmtbt02);
        tblDlDauVaoDayMayBO.setPhatLdsvtXlsct(this.phatLdsvtXlsct);
        tblDlDauVaoDayMayBO.setTbmtbt3(this.tbmtbt3);
        tblDlDauVaoDayMayBO.setTbmtbt4(this.tbmtbt4);
        tblDlDauVaoDayMayBO.setTbmtbt5(this.tbmtbt5);
        tblDlDauVaoDayMayBO.setTbmtbt6(this.tbmtbt6);
        tblDlDauVaoDayMayBO.setTbmtbt7(this.tbmtbt7);
        tblDlDauVaoDayMayBO.setTbmtbdv02(this.tbmtbdv02);
        tblDlDauVaoDayMayBO.setPhatGttbrm(this.phatGttbrm);
        tblDlDauVaoDayMayBO.setTbmtbdv3(this.tbmtbdv3);
        tblDlDauVaoDayMayBO.setTbmtbdv4(this.tbmtbdv4);
        tblDlDauVaoDayMayBO.setTbmtbdv5(this.tbmtbdv5);
        tblDlDauVaoDayMayBO.setTbmtbdv6(this.tbmtbdv6);
        tblDlDauVaoDayMayBO.setTbmtbdv7(this.tbmtbdv7);
        tblDlDauVaoDayMayBO.setTbmtbcs02(this.tbmtbcs02);
        tblDlDauVaoDayMayBO.setTbmtbcs3(this.tbmtbcs3);
        tblDlDauVaoDayMayBO.setTbmtbcs4(this.tbmtbcs4);
        tblDlDauVaoDayMayBO.setTbmtbcs5(this.tbmtbcs5);
        tblDlDauVaoDayMayBO.setTbmtbcs6(this.tbmtbcs6);
        tblDlDauVaoDayMayBO.setTbmtbwf02(this.tbmtbwf02);
        tblDlDauVaoDayMayBO.setTbmtbwf3(this.tbmtbwf3);
        tblDlDauVaoDayMayBO.setTbmtbwf4(this.tbmtbwf4);
        tblDlDauVaoDayMayBO.setTbmtbwf5(this.tbmtbwf5);
        tblDlDauVaoDayMayBO.setTbmtbwf6(this.tbmtbwf6);
        tblDlDauVaoDayMayBO.setNhomTruong(this.nhomTruong);
        tblDlDauVaoDayMayBO.setGhiChu(this.ghiChu);
        tblDlDauVaoDayMayBO.setXoa(this.xoa);
        tblDlDauVaoDayMayBO.setKyLuong(this.kyLuong);
        tblDlDauVaoDayMayBO.setHoatDong(this.hoatDong);
        tblDlDauVaoDayMayBO.setTblDlDauVaoDayMayId(this.tblDlDauVaoDayMayId);
        tblDlDauVaoDayMayBO.setPhatLyttd(this.phatLyttd);
        tblDlDauVaoDayMayBO.setPhatTbcdcnktd(this.phatTbcdcnktd);
        tblDlDauVaoDayMayBO.setPhatTbrmktd(this.phatTbrmktd);
        return tblDlDauVaoDayMayBO;
    }

	@JsonProperty("maNv")
    public java.lang.String getMaNv(){
		return maNv;
    }
	
    public void setMaNv(java.lang.String maNv){
		this.maNv = maNv;
    }	
	
	@JsonProperty("hoVaTen")
    public java.lang.String getHoVaTen(){
		return hoVaTen;
    }
	
    public void setHoVaTen(java.lang.String hoVaTen){
		this.hoVaTen = hoVaTen;
    }	
	
	@JsonProperty("donVi")
    public java.lang.String getDonVi(){
		return donVi;
    }
	
    public void setDonVi(java.lang.String donVi){
		this.donVi = donVi;
    }	
	
	@JsonProperty("maTinh")
    public java.lang.String getMaTinh(){
		return maTinh;
    }
	
    public void setMaTinh(java.lang.String maTinh){
		this.maTinh = maTinh;
    }	
	
	@JsonProperty("tenHuyen")
    public java.lang.String getTenHuyen(){
		return tenHuyen;
    }
	
    public void setTenHuyen(java.lang.String tenHuyen){
		this.tenHuyen = tenHuyen;
    }	
	
	@JsonProperty("ghep")
    public java.lang.String getGhep(){
		return ghep;
    }
	
    public void setGhep(java.lang.String ghep){
		this.ghep = ghep;
    }	
	
	@JsonProperty("doiTuong")
    public java.lang.String getDoiTuong(){
		return doiTuong;
    }
	
    public void setDoiTuong(java.lang.String doiTuong){
		this.doiTuong = doiTuong;
    }	
	
	@JsonProperty("ddtbqlDayTbkt")
    public java.lang.Float getDdtbqlDayTbkt(){
		return ddtbqlDayTbkt;
    }
	
    public void setDdtbqlDayTbkt(java.lang.Float ddtbqlDayTbkt){
		this.ddtbqlDayTbkt = ddtbqlDayTbkt;
    }	
	
	@JsonProperty("ddtbqlDayTbAon")
    public java.lang.Float getDdtbqlDayTbAon(){
		return ddtbqlDayTbAon;
    }
	
    public void setDdtbqlDayTbAon(java.lang.Float ddtbqlDayTbAon){
		this.ddtbqlDayTbAon = ddtbqlDayTbAon;
    }	
	
	@JsonProperty("ddtbqlDaToaNha")
    public java.lang.Float getDdtbqlDaToaNha(){
		return ddtbqlDaToaNha;
    }
	
    public void setDdtbqlDaToaNha(java.lang.Float ddtbqlDaToaNha){
		this.ddtbqlDaToaNha = ddtbqlDaToaNha;
    }	
	
	@JsonProperty("ddtbqlGponAdslPstnEoc")
    public java.lang.Float getDdtbqlGponAdslPstnEoc(){
		return ddtbqlGponAdslPstnEoc;
    }
	
    public void setDdtbqlGponAdslPstnEoc(java.lang.Float ddtbqlGponAdslPstnEoc){
		this.ddtbqlGponAdslPstnEoc = ddtbqlGponAdslPstnEoc;
    }	
	
	@JsonProperty("ddtbqlTongQuyDoi")
    public java.lang.Float getDdtbqlTongQuyDoi(){
		return ddtbqlTongQuyDoi;
    }
	
    public void setDdtbqlTongQuyDoi(java.lang.Float ddtbqlTongQuyDoi){
		this.ddtbqlTongQuyDoi = ddtbqlTongQuyDoi;
    }	
	
	@JsonProperty("ddtbqlHuongLuongDuyTri")
    public java.lang.Float getDdtbqlHuongLuongDuyTri(){
		return ddtbqlHuongLuongDuyTri;
    }
	
    public void setDdtbqlHuongLuongDuyTri(java.lang.Float ddtbqlHuongLuongDuyTri){
		this.ddtbqlHuongLuongDuyTri = ddtbqlHuongLuongDuyTri;
    }	
	
	@JsonProperty("tbmtbkt02")
    public java.lang.Float getTbmtbkt02(){
		return tbmtbkt02;
    }
	
    public void setTbmtbkt02(java.lang.Float tbmtbkt02){
		this.tbmtbkt02 = tbmtbkt02;
    }	
	
	@JsonProperty("phatQcxlsc")
    public java.lang.Float getPhatQcxlsc(){
		return phatQcxlsc;
    }
	
    public void setPhatQcxlsc(java.lang.Float phatQcxlsc){
		this.phatQcxlsc = phatQcxlsc;
    }	
	
	@JsonProperty("tbmtbkt3")
    public java.lang.Float getTbmtbkt3(){
		return tbmtbkt3;
    }
	
    public void setTbmtbkt3(java.lang.Float tbmtbkt3){
		this.tbmtbkt3 = tbmtbkt3;
    }	
	
	@JsonProperty("tbmtbkt4")
    public java.lang.Float getTbmtbkt4(){
		return tbmtbkt4;
    }
	
    public void setTbmtbkt4(java.lang.Float tbmtbkt4){
		this.tbmtbkt4 = tbmtbkt4;
    }	
	
	@JsonProperty("tbmtbkt5")
    public java.lang.Float getTbmtbkt5(){
		return tbmtbkt5;
    }
	
    public void setTbmtbkt5(java.lang.Float tbmtbkt5){
		this.tbmtbkt5 = tbmtbkt5;
    }	
	
	@JsonProperty("tbmtbkt6")
    public java.lang.Float getTbmtbkt6(){
		return tbmtbkt6;
    }
	
    public void setTbmtbkt6(java.lang.Float tbmtbkt6){
		this.tbmtbkt6 = tbmtbkt6;
    }	
	
	@JsonProperty("tbmtbkt7")
    public java.lang.Float getTbmtbkt7(){
		return tbmtbkt7;
    }
	
    public void setTbmtbkt7(java.lang.Float tbmtbkt7){
		this.tbmtbkt7 = tbmtbkt7;
    }	
	
	@JsonProperty("tbmtbt02")
    public java.lang.Float getTbmtbt02(){
		return tbmtbt02;
    }
	
    public void setTbmtbt02(java.lang.Float tbmtbt02){
		this.tbmtbt02 = tbmtbt02;
    }	
	
	@JsonProperty("phatLdsvtXlsct")
    public java.lang.Float getPhatLdsvtXlsct(){
		return phatLdsvtXlsct;
    }
	
    public void setPhatLdsvtXlsct(java.lang.Float phatLdsvtXlsct){
		this.phatLdsvtXlsct = phatLdsvtXlsct;
    }	
	
	@JsonProperty("tbmtbt3")
    public java.lang.Float getTbmtbt3(){
		return tbmtbt3;
    }
	
    public void setTbmtbt3(java.lang.Float tbmtbt3){
		this.tbmtbt3 = tbmtbt3;
    }	
	
	@JsonProperty("tbmtbt4")
    public java.lang.Float getTbmtbt4(){
		return tbmtbt4;
    }
	
    public void setTbmtbt4(java.lang.Float tbmtbt4){
		this.tbmtbt4 = tbmtbt4;
    }	
	
	@JsonProperty("tbmtbt5")
    public java.lang.Float getTbmtbt5(){
		return tbmtbt5;
    }
	
    public void setTbmtbt5(java.lang.Float tbmtbt5){
		this.tbmtbt5 = tbmtbt5;
    }	
	
	@JsonProperty("tbmtbt6")
    public java.lang.Float getTbmtbt6(){
		return tbmtbt6;
    }
	
    public void setTbmtbt6(java.lang.Float tbmtbt6){
		this.tbmtbt6 = tbmtbt6;
    }	
	
	@JsonProperty("tbmtbt7")
    public java.lang.Float getTbmtbt7(){
		return tbmtbt7;
    }
	
    public void setTbmtbt7(java.lang.Float tbmtbt7){
		this.tbmtbt7 = tbmtbt7;
    }	
	
	@JsonProperty("tbmtbdv02")
    public java.lang.Float getTbmtbdv02(){
		return tbmtbdv02;
    }
	
    public void setTbmtbdv02(java.lang.Float tbmtbdv02){
		this.tbmtbdv02 = tbmtbdv02;
    }	
	
	@JsonProperty("phatGttbrm")
    public java.lang.Float getPhatGttbrm(){
		return phatGttbrm;
    }
	
    public void setPhatGttbrm(java.lang.Float phatGttbrm){
		this.phatGttbrm = phatGttbrm;
    }	
	
	@JsonProperty("tbmtbdv3")
    public java.lang.Float getTbmtbdv3(){
		return tbmtbdv3;
    }
	
    public void setTbmtbdv3(java.lang.Float tbmtbdv3){
		this.tbmtbdv3 = tbmtbdv3;
    }	
	
	@JsonProperty("tbmtbdv4")
    public java.lang.Float getTbmtbdv4(){
		return tbmtbdv4;
    }
	
    public void setTbmtbdv4(java.lang.Float tbmtbdv4){
		this.tbmtbdv4 = tbmtbdv4;
    }	
	
	@JsonProperty("tbmtbdv5")
    public java.lang.Float getTbmtbdv5(){
		return tbmtbdv5;
    }
	
    public void setTbmtbdv5(java.lang.Float tbmtbdv5){
		this.tbmtbdv5 = tbmtbdv5;
    }	
	
	@JsonProperty("tbmtbdv6")
    public java.lang.Float getTbmtbdv6(){
		return tbmtbdv6;
    }
	
    public void setTbmtbdv6(java.lang.Float tbmtbdv6){
		this.tbmtbdv6 = tbmtbdv6;
    }	
	
	@JsonProperty("tbmtbdv7")
    public java.lang.Float getTbmtbdv7(){
		return tbmtbdv7;
    }
	
    public void setTbmtbdv7(java.lang.Float tbmtbdv7){
		this.tbmtbdv7 = tbmtbdv7;
    }	
	
	@JsonProperty("tbmtbcs02")
    public java.lang.Float getTbmtbcs02(){
		return tbmtbcs02;
    }
	
    public void setTbmtbcs02(java.lang.Float tbmtbcs02){
		this.tbmtbcs02 = tbmtbcs02;
    }	
	
	@JsonProperty("tbmtbcs3")
    public java.lang.Float getTbmtbcs3(){
		return tbmtbcs3;
    }
	
    public void setTbmtbcs3(java.lang.Float tbmtbcs3){
		this.tbmtbcs3 = tbmtbcs3;
    }	
	
	@JsonProperty("tbmtbcs4")
    public java.lang.Float getTbmtbcs4(){
		return tbmtbcs4;
    }
	
    public void setTbmtbcs4(java.lang.Float tbmtbcs4){
		this.tbmtbcs4 = tbmtbcs4;
    }	
	
	@JsonProperty("tbmtbcs5")
    public java.lang.Float getTbmtbcs5(){
		return tbmtbcs5;
    }
	
    public void setTbmtbcs5(java.lang.Float tbmtbcs5){
		this.tbmtbcs5 = tbmtbcs5;
    }	
	
	@JsonProperty("tbmtbcs6")
    public java.lang.Float getTbmtbcs6(){
		return tbmtbcs6;
    }
	
    public void setTbmtbcs6(java.lang.Float tbmtbcs6){
		this.tbmtbcs6 = tbmtbcs6;
    }	
	
	@JsonProperty("tbmtbwf02")
    public java.lang.Float getTbmtbwf02(){
		return tbmtbwf02;
    }
	
    public void setTbmtbwf02(java.lang.Float tbmtbwf02){
		this.tbmtbwf02 = tbmtbwf02;
    }	
	
	@JsonProperty("tbmtbwf3")
    public java.lang.Float getTbmtbwf3(){
		return tbmtbwf3;
    }
	
    public void setTbmtbwf3(java.lang.Float tbmtbwf3){
		this.tbmtbwf3 = tbmtbwf3;
    }	
	
	@JsonProperty("tbmtbwf4")
    public java.lang.Float getTbmtbwf4(){
		return tbmtbwf4;
    }
	
    public void setTbmtbwf4(java.lang.Float tbmtbwf4){
		this.tbmtbwf4 = tbmtbwf4;
    }	
	
	@JsonProperty("tbmtbwf5")
    public java.lang.Float getTbmtbwf5(){
		return tbmtbwf5;
    }
	
    public void setTbmtbwf5(java.lang.Float tbmtbwf5){
		this.tbmtbwf5 = tbmtbwf5;
    }	
	
	@JsonProperty("tbmtbwf6")
    public java.lang.Float getTbmtbwf6(){
		return tbmtbwf6;
    }
	
    public void setTbmtbwf6(java.lang.Float tbmtbwf6){
		this.tbmtbwf6 = tbmtbwf6;
    }	
	
	@JsonProperty("nhomTruong")
    public java.lang.Float getNhomTruong(){
		return nhomTruong;
    }
	
    public void setNhomTruong(java.lang.Float nhomTruong){
		this.nhomTruong = nhomTruong;
    }	
	
	@JsonProperty("ghiChu")
    public java.lang.String getGhiChu(){
		return ghiChu;
    }
	
    public void setGhiChu(java.lang.String ghiChu){
		this.ghiChu = ghiChu;
    }	
	
	@JsonProperty("xoa")
    public java.lang.Long getXoa(){
		return xoa;
    }
	
    public void setXoa(java.lang.Long xoa){
		this.xoa = xoa;
    }	
	
	@JsonProperty("kyLuong")
    public java.lang.String getKyLuong(){
		return kyLuong;
    }
	
    public void setKyLuong(java.lang.String kyLuong){
		this.kyLuong = kyLuong;
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
        return tblDlDauVaoDayMayId;
    }
   
    @Override
    public String catchName() {
        return getTblDlDauVaoDayMayId().toString();
    }
	
	@JsonProperty("tblDlDauVaoDayMayId")
    public java.lang.Long getTblDlDauVaoDayMayId(){
		return tblDlDauVaoDayMayId;
    }
	
    public void setTblDlDauVaoDayMayId(java.lang.Long tblDlDauVaoDayMayId){
		this.tblDlDauVaoDayMayId = tblDlDauVaoDayMayId;
    }	
	
	@JsonProperty("phatLyttd")
    public java.lang.Float getPhatLyttd(){
		return phatLyttd;
    }
	
    public void setPhatLyttd(java.lang.Float phatLyttd){
		this.phatLyttd = phatLyttd;
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
	
    public void setExThang(String exThang) {
		this.exThang = exThang;
	}
	public String getExThang() {
		return exThang;
	}
	public String getExNam() {
		return exNam;
	}

	public void setExNam(String exNam) {
		this.exNam = exNam;
	}	
}
