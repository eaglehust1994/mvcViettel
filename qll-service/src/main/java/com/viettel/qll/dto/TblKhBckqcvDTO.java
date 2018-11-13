package com.viettel.qll.dto;

import com.viettel.qll.bo.TblKhBckqcvBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.google.common.collect.Lists;
import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_KH_BCKQCVBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblKhBckqcvDTO extends QllBaseDTO<TblKhBckqcvBO> {

	private java.lang.Long khBckqcvId;
	private java.lang.String ngay;
	private java.lang.String thang;
	private java.lang.String nam;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date thoiGian;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date thoiGianFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date thoiGianTo;
	private java.lang.String maDonVi;
	private java.lang.String tenDonVi;
	private java.lang.Long sanLuong;
	private java.lang.Long doanhThu;
	private java.lang.Long hcqt;
	private java.lang.String loai;
	private java.lang.String khoi;
	private java.lang.String thuoc;
	private java.lang.Long sanLuongKh;
	private java.lang.Long doanhThuKh;
	private java.lang.Long hcqtKh;
	private String department;
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}



	private List<Long> listSanLuong = Lists.newArrayList();
	private List<Long> listDoanhThu = Lists.newArrayList();
	private List<Long> listHcqt = Lists.newArrayList();
	private List<Date> listThoiGian = Lists.newArrayList();
	private List<String> listThang = Lists.newArrayList();
	private List<String> listDonVi = Lists.newArrayList();
	private List<Long> listSanLuongKh = Lists.newArrayList();
	private List<Long> listDoanhThuKh = Lists.newArrayList();
	private List<Long> listHcqtKh = Lists.newArrayList();
	private List<Float> listTileSl = Lists.newArrayList();
	private List<Float> listTileDt = Lists.newArrayList();
	private List<Float> listTileHcqt = Lists.newArrayList();
	
	public java.lang.String getThuoc() {
		return thuoc;
	}

	public void setThuoc(java.lang.String thuoc) {
		this.thuoc = thuoc;
	}

	public List<Float> getListTileSl() {
		return listTileSl;
	}

	public void setListTileSl(List<Float> listTileSl) {
		this.listTileSl = listTileSl;
	}

	public List<Float> getListTileDt() {
		return listTileDt;
	}

	public void setListTileDt(List<Float> listTileDt) {
		this.listTileDt = listTileDt;
	}

	public List<Float> getListTileHcqt() {
		return listTileHcqt;
	}

	public void setListTileHcqt(List<Float> listTileHcqt) {
		this.listTileHcqt = listTileHcqt;
	}

	public java.lang.String getKhoi() {
		return khoi;
	}

	public void setKhoi(java.lang.String khoi) {
		this.khoi = khoi;
	}

	public java.lang.Long getSanLuongKh() {
		return sanLuongKh;
	}

	public void setSanLuongKh(java.lang.Long sanLuongKh) {
		this.sanLuongKh = sanLuongKh;
	}

	public java.lang.Long getDoanhThuKh() {
		return doanhThuKh;
	}

	public void setDoanhThuKh(java.lang.Long doanhThuKh) {
		this.doanhThuKh = doanhThuKh;
	}

	public java.lang.Long getHcqtKh() {
		return hcqtKh;
	}

	public void setHcqtKh(java.lang.Long hcqtKh) {
		this.hcqtKh = hcqtKh;
	}

	public List<String> getListDonVi() {
		return listDonVi;
	}

	public void setListDonVi(List<String> listDonVi) {
		this.listDonVi = listDonVi;
	}

	public List<Long> getListSanLuongKh() {
		return listSanLuongKh;
	}

	public void setListSanLuongKh(List<Long> listSanLuongKh) {
		this.listSanLuongKh = listSanLuongKh;
	}

	public List<Long> getListDoanhThuKh() {
		return listDoanhThuKh;
	}

	public void setListDoanhThuKh(List<Long> listDoanhThuKh) {
		this.listDoanhThuKh = listDoanhThuKh;
	}

	public List<Long> getListHcqtKh() {
		return listHcqtKh;
	}

	public void setListHcqtKh(List<Long> listHcqtKh) {
		this.listHcqtKh = listHcqtKh;
	}



	public List<Long> getListSanLuong() {
		return listSanLuong;
	}

	public void setListSanLuong(List<Long> listSanLuong) {
		this.listSanLuong = listSanLuong;
	}

	public List<Long> getListDoanhThu() {
		return listDoanhThu;
	}

	public void setListDoanhThu(List<Long> listDoanhThu) {
		this.listDoanhThu = listDoanhThu;
	}

	public List<Long> getListHcqt() {
		return listHcqt;
	}

	public void setListHcqt(List<Long> listHcqt) {
		this.listHcqt = listHcqt;
	}

	public List<Date> getListThoiGian() {
		return listThoiGian;
	}

	public void setListThoiGian(List<Date> listThoiGian) {
		this.listThoiGian = listThoiGian;
	}

	public List<String> getListThang() {
		return listThang;
	}

	public void setListThang(List<String> listThang) {
		this.listThang = listThang;
	}

	

	private int start;
	private int maxResult;

    @Override
    public TblKhBckqcvBO toModel() {
        TblKhBckqcvBO tblKhBckqcvBO = new TblKhBckqcvBO();
        tblKhBckqcvBO.setKhBckqcvId(this.khBckqcvId);
        tblKhBckqcvBO.setNgay(this.ngay);
        tblKhBckqcvBO.setThang(this.thang);
        tblKhBckqcvBO.setNam(this.nam);
        tblKhBckqcvBO.setThoiGian(this.thoiGian);
        tblKhBckqcvBO.setMaDonVi(this.maDonVi);
        tblKhBckqcvBO.setTenDonVi(this.tenDonVi);
        tblKhBckqcvBO.setSanLuong(this.sanLuong);
        tblKhBckqcvBO.setDoanhThu(this.doanhThu);
        tblKhBckqcvBO.setHcqt(this.hcqt);
        tblKhBckqcvBO.setLoai(this.loai);
        tblKhBckqcvBO.setKhoi(this.khoi);
        tblKhBckqcvBO.setThuoc(this.thuoc);
        return tblKhBckqcvBO;
    }

    @Override
     public Long getFWModelId() {
        return khBckqcvId;
    }
   
    @Override
    public String catchName() {
        return getKhBckqcvId().toString();
    }
	
	@JsonProperty("khBckqcvId")
    public java.lang.Long getKhBckqcvId(){
		return khBckqcvId;
    }
	
    public void setKhBckqcvId(java.lang.Long khBckqcvId){
		this.khBckqcvId = khBckqcvId;
    }	
	
	@JsonProperty("ngay")
    public java.lang.String getNgay(){
		return ngay;
    }
	
    public void setNgay(java.lang.String ngay){
		this.ngay = ngay;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("nam")
    public java.lang.String getNam(){
		return nam;
    }
	
    public void setNam(java.lang.String nam){
		this.nam = nam;
    }	
	
	@JsonProperty("thoiGian")
    public java.util.Date getThoiGian(){
		return thoiGian;
    }
	
    public void setThoiGian(java.util.Date thoiGian){
		this.thoiGian = thoiGian;
    }	
	
	public java.util.Date getThoiGianFrom() {
    	return thoiGianFrom;
    }
	
    public void setThoiGianFrom(java.util.Date thoiGianFrom) {
    	this.thoiGianFrom = thoiGianFrom;
    }
	
	public java.util.Date getThoiGianTo() {
    	return thoiGianTo;
    }
	
    public void setThoiGianTo(java.util.Date thoiGianTo) {
    	this.thoiGianTo = thoiGianTo;
    }
	
	@JsonProperty("maDonVi")
    public java.lang.String getMaDonVi(){
		return maDonVi;
    }
	
    public void setMaDonVi(java.lang.String maDonVi){
		this.maDonVi = maDonVi;
    }	
	
	@JsonProperty("tenDonVi")
    public java.lang.String getTenDonVi(){
		return tenDonVi;
    }
	
    public void setTenDonVi(java.lang.String tenDonVi){
		this.tenDonVi = tenDonVi;
    }	
	
	@JsonProperty("sanLuong")
    public java.lang.Long getSanLuong(){
		return sanLuong;
    }
	
    public void setSanLuong(java.lang.Long sanLuong){
		this.sanLuong = sanLuong;
    }	
	
	@JsonProperty("doanhThu")
    public java.lang.Long getDoanhThu(){
		return doanhThu;
    }
	
    public void setDoanhThu(java.lang.Long doanhThu){
		this.doanhThu = doanhThu;
    }	
	
	@JsonProperty("hcqt")
    public java.lang.Long getHcqt(){
		return hcqt;
    }
	
    public void setHcqt(java.lang.Long hcqt){
		this.hcqt = hcqt;
    }	
	
	@JsonProperty("loai")
    public java.lang.String getLoai(){
		return loai;
    }
	
    public void setLoai(java.lang.String loai){
		this.loai = loai;
    }	
	
	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
