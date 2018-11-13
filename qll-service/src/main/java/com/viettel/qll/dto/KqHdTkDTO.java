package com.viettel.qll.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.qll.bo.KqHdTkBO;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "KQ_HD_TKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KqHdTkDTO extends QllBaseDTO<KqHdTkBO> {

	private java.lang.Long kqHdTkId;
	private java.lang.String tinh;
	private java.lang.String maTramTuyen;
	private java.lang.String nguonCapUng;
	private java.lang.String soHd;
	private java.lang.String soKhTc;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayKyKh;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayKyKhFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayKyKhTo;
	private java.lang.String thuocDmToTrinh;
	private java.lang.String loaiCt;
	private java.lang.String noiDung;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiHshc;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiHshcFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiHshcTo;
	private java.lang.String soBill;
	private java.lang.String ghiChu;
	private java.lang.Float gtQtCdtChuaVat;
	private java.lang.Float gtQtCdtCoVat;
	private java.lang.Float cpNhanCongDn;
	private java.lang.Float cpVatLieuDn;
	private java.lang.Float cpHshcDn;
	private java.lang.Float cpVanChuyenDn;
	private java.lang.Float chiPhiKhacDn;
	private java.lang.Float chiPhiLuongDn;
	private java.lang.Float vatDn;
	private java.lang.Float tongDn;
	private java.lang.String nguoiCapNhat;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiPtkHshc;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiPtkHshcFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGuiPtkHshcTo;
	private java.lang.String tinhTrangChungTu;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTutttt;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTuttttFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayTuttttTo;
	private java.lang.String ghiChuHsLoi;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayPtkTdXong;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayPtkTdXongFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayPtkTdXongTo;
	private java.lang.Float cpNhanCongTd;
	private java.lang.Float cpVatLieuTd;
	private java.lang.Float cpHshcTd;
	private java.lang.Float cpVanChuyenTd;
	private java.lang.Float cpKhacTd;
	private java.lang.Float vatTd;
	private java.lang.Float tongTd;
	private java.lang.Float gtTdPtkChuaVat;
	private java.lang.Float gtTdPtkCoVat;
	private java.lang.String thangHshcQuyLuong;
	private java.lang.String thangGhiNhanQuyLuongTqt;
	private java.lang.Float tamUngLuong;
	private java.lang.Float luongThucNhan;
	private java.lang.Float tyLe;
	private java.lang.String hsTonQua5n;
	private java.lang.String gtQhXlCt;
	private java.lang.String nguoiPheDuyetPtk;
	private java.lang.Float cpLuongTd;
	private java.lang.String tenTinh;
	private java.lang.Float checkTrinhKy;
	private java.lang.String lyDoTc;
	private java.lang.String pathFile;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayCapNhat;
	private java.lang.String nguoiTao;
	private java.lang.String trangThai;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayThiCong;
	private long ttct1, ttct2, ttct3, ttct4, ttct5, ttct6, ttct7, hsQua5N, soHs1, soHdChuaTd;
	

	private List<Long> lstObj;

	public List<Long> getLstObj() {
		return lstObj;
	}

	public void setLstObj(List<Long> lstObj) {
		this.lstObj = lstObj;
	}

	@Override
	public KqHdTkBO toModel() {
		KqHdTkBO kqHdTkBO = new KqHdTkBO();
		kqHdTkBO.setKqHdTkId(this.kqHdTkId);
		kqHdTkBO.setTinh(this.tinh);
		kqHdTkBO.setMaTramTuyen(this.maTramTuyen);
		kqHdTkBO.setNguonCapUng(this.nguonCapUng);
		kqHdTkBO.setSoHd(this.soHd);
		kqHdTkBO.setSoKhTc(this.soKhTc);
		kqHdTkBO.setNgayKyKh(this.ngayKyKh);
		kqHdTkBO.setThuocDmToTrinh(this.thuocDmToTrinh);
		kqHdTkBO.setLoaiCt(this.loaiCt);
		kqHdTkBO.setNoiDung(this.noiDung);
		kqHdTkBO.setNgayGuiHshc(this.ngayGuiHshc);
		kqHdTkBO.setSoBill(this.soBill);
		kqHdTkBO.setGhiChu(this.ghiChu);
		kqHdTkBO.setGtQtCdtChuaVat(this.gtQtCdtChuaVat);
		kqHdTkBO.setGtQtCdtCoVat(this.gtQtCdtCoVat);
		kqHdTkBO.setCpNhanCongDn(this.cpNhanCongDn);
		kqHdTkBO.setCpVatLieuDn(this.cpVatLieuDn);
		kqHdTkBO.setCpHshcDn(this.cpHshcDn);
		kqHdTkBO.setCpVanChuyenDn(this.cpVanChuyenDn);
		kqHdTkBO.setChiPhiKhacDn(this.chiPhiKhacDn);
		kqHdTkBO.setChiPhiLuongDn(this.chiPhiLuongDn);
		kqHdTkBO.setVatDn(this.vatDn);
		kqHdTkBO.setTongDn(this.tongDn);
		kqHdTkBO.setNguoiCapNhat(this.nguoiCapNhat);
		kqHdTkBO.setNgayGuiPtkHshc(this.ngayGuiPtkHshc);
		kqHdTkBO.setTinhTrangChungTu(this.tinhTrangChungTu);
		kqHdTkBO.setNgayTutttt(this.ngayTutttt);
		kqHdTkBO.setGhiChuHsLoi(this.ghiChuHsLoi);
		kqHdTkBO.setNgayPtkTdXong(this.ngayPtkTdXong);
		kqHdTkBO.setCpNhanCongTd(this.cpNhanCongTd);
		kqHdTkBO.setCpVatLieuTd(this.cpVatLieuTd);
		kqHdTkBO.setCpHshcTd(this.cpHshcTd);
		kqHdTkBO.setCpVanChuyenTd(this.cpVanChuyenTd);
		kqHdTkBO.setCpKhacTd(this.cpKhacTd);
		kqHdTkBO.setVatTd(this.vatTd);
		kqHdTkBO.setTongTd(this.tongTd);
		kqHdTkBO.setGtTdPtkChuaVat(this.gtTdPtkChuaVat);
		kqHdTkBO.setGtTdPtkCoVat(this.gtTdPtkCoVat);
		kqHdTkBO.setThangHshcQuyLuong(this.thangHshcQuyLuong);
		kqHdTkBO.setThangGhiNhanQuyLuongTqt(this.thangGhiNhanQuyLuongTqt);
		kqHdTkBO.setTamUngLuong(this.tamUngLuong);
		kqHdTkBO.setLuongThucNhan(this.luongThucNhan);
		kqHdTkBO.setTyLe(this.tyLe);
		kqHdTkBO.setHsTonQua5n(this.hsTonQua5n);
		kqHdTkBO.setGtQhXlCt(this.gtQhXlCt);
		kqHdTkBO.setNguoiPheDuyetPtk(this.nguoiPheDuyetPtk);
		kqHdTkBO.setCpLuongTd(this.cpLuongTd);
		kqHdTkBO.setTenTinh(this.tenTinh);
		kqHdTkBO.setCheckTrinhKy(this.checkTrinhKy);
		kqHdTkBO.setLyDoTc(this.lyDoTc);
		kqHdTkBO.setPathFile(this.pathFile);
		kqHdTkBO.setNgayCapNhat(this.ngayCapNhat);
		kqHdTkBO.setNguoiTao(this.nguoiTao);
		kqHdTkBO.setTrangThai(this.trangThai);
		kqHdTkBO.setNgayThiCong(ngayThiCong);
		return kqHdTkBO;
	}

	@JsonProperty("ngayThiCong")
	public java.util.Date getNgayThiCong() {
		return ngayThiCong;
	}

	public void setNgayThiCong(java.util.Date ngayThiCong) {
		this.ngayThiCong = ngayThiCong;
	}

	@Override
	public Long getFWModelId() {
		return kqHdTkId;
	}

	@Override
	public String catchName() {
		return getKqHdTkId().toString();
	}

	@JsonProperty("kqHdTkId")
	public java.lang.Long getKqHdTkId() {
		return kqHdTkId;
	}

	public void setKqHdTkId(java.lang.Long kqHdTkId) {
		this.kqHdTkId = kqHdTkId;
	}

	@JsonProperty("tinh")
	public java.lang.String getTinh() {
		return tinh;
	}

	public void setTinh(java.lang.String tinh) {
		this.tinh = tinh;
	}

	@JsonProperty("maTramTuyen")
	public java.lang.String getMaTramTuyen() {
		return maTramTuyen;
	}

	public void setMaTramTuyen(java.lang.String maTramTuyen) {
		this.maTramTuyen = maTramTuyen;
	}

	@JsonProperty("nguonCapUng")
	public java.lang.String getNguonCapUng() {
		return nguonCapUng;
	}

	public void setNguonCapUng(java.lang.String nguonCapUng) {
		this.nguonCapUng = nguonCapUng;
	}

	@JsonProperty("soHd")
	public java.lang.String getSoHd() {
		return soHd;
	}

	public void setSoHd(java.lang.String soHd) {
		this.soHd = soHd;
	}

	@JsonProperty("soKhTc")
	public java.lang.String getSoKhTc() {
		return soKhTc;
	}

	public void setSoKhTc(java.lang.String soKhTc) {
		this.soKhTc = soKhTc;
	}

	@JsonProperty("ngayKyKh")
	public java.util.Date getNgayKyKh() {
		return ngayKyKh;
	}

	public void setNgayKyKh(java.util.Date ngayKyKh) {
		this.ngayKyKh = ngayKyKh;
	}

	public java.util.Date getNgayKyKhFrom() {
		return ngayKyKhFrom;
	}

	public void setNgayKyKhFrom(java.util.Date ngayKyKhFrom) {
		this.ngayKyKhFrom = ngayKyKhFrom;
	}

	public java.util.Date getNgayKyKhTo() {
		return ngayKyKhTo;
	}

	public void setNgayKyKhTo(java.util.Date ngayKyKhTo) {
		this.ngayKyKhTo = ngayKyKhTo;
	}

	@JsonProperty("thuocDmToTrinh")
	public java.lang.String getThuocDmToTrinh() {
		return thuocDmToTrinh;
	}

	public void setThuocDmToTrinh(java.lang.String thuocDmToTrinh) {
		this.thuocDmToTrinh = thuocDmToTrinh;
	}

	@JsonProperty("loaiCt")
	public java.lang.String getLoaiCt() {
		return loaiCt;
	}

	public void setLoaiCt(java.lang.String loaiCt) {
		this.loaiCt = loaiCt;
	}

	@JsonProperty("noiDung")
	public java.lang.String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(java.lang.String noiDung) {
		this.noiDung = noiDung;
	}

	@JsonProperty("ngayGuiHshc")
	public java.util.Date getNgayGuiHshc() {
		return ngayGuiHshc;
	}

	public void setNgayGuiHshc(java.util.Date ngayGuiHshc) {
		this.ngayGuiHshc = ngayGuiHshc;
	}

	public java.util.Date getNgayGuiHshcFrom() {
		return ngayGuiHshcFrom;
	}

	public void setNgayGuiHshcFrom(java.util.Date ngayGuiHshcFrom) {
		this.ngayGuiHshcFrom = ngayGuiHshcFrom;
	}

	public java.util.Date getNgayGuiHshcTo() {
		return ngayGuiHshcTo;
	}

	public void setNgayGuiHshcTo(java.util.Date ngayGuiHshcTo) {
		this.ngayGuiHshcTo = ngayGuiHshcTo;
	}

	@JsonProperty("soBill")
	public java.lang.String getSoBill() {
		return soBill;
	}

	public void setSoBill(java.lang.String soBill) {
		this.soBill = soBill;
	}

	@JsonProperty("ghiChu")
	public java.lang.String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(java.lang.String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@JsonProperty("gtQtCdtChuaVat")
	public java.lang.Float getGtQtCdtChuaVat() {
		return gtQtCdtChuaVat;
	}

	public void setGtQtCdtChuaVat(java.lang.Float gtQtCdtChuaVat) {
		this.gtQtCdtChuaVat = gtQtCdtChuaVat;
	}

	@JsonProperty("gtQtCdtCoVat")
	public java.lang.Float getGtQtCdtCoVat() {
		return gtQtCdtCoVat;
	}

	public void setGtQtCdtCoVat(java.lang.Float gtQtCdtCoVat) {
		this.gtQtCdtCoVat = gtQtCdtCoVat;
	}

	@JsonProperty("cpNhanCongDn")
	public java.lang.Float getCpNhanCongDn() {
		return cpNhanCongDn;
	}

	public void setCpNhanCongDn(java.lang.Float cpNhanCongDn) {
		this.cpNhanCongDn = cpNhanCongDn;
	}

	@JsonProperty("cpVatLieuDn")
	public java.lang.Float getCpVatLieuDn() {
		return cpVatLieuDn;
	}

	public void setCpVatLieuDn(java.lang.Float cpVatLieuDn) {
		this.cpVatLieuDn = cpVatLieuDn;
	}

	@JsonProperty("cpHshcDn")
	public java.lang.Float getCpHshcDn() {
		return cpHshcDn;
	}

	public void setCpHshcDn(java.lang.Float cpHshcDn) {
		this.cpHshcDn = cpHshcDn;
	}

	@JsonProperty("cpVanChuyenDn")
	public java.lang.Float getCpVanChuyenDn() {
		return cpVanChuyenDn;
	}

	public void setCpVanChuyenDn(java.lang.Float cpVanChuyenDn) {
		this.cpVanChuyenDn = cpVanChuyenDn;
	}

	@JsonProperty("chiPhiKhacDn")
	public java.lang.Float getChiPhiKhacDn() {
		return chiPhiKhacDn;
	}

	public void setChiPhiKhacDn(java.lang.Float chiPhiKhacDn) {
		this.chiPhiKhacDn = chiPhiKhacDn;
	}

	@JsonProperty("chiPhiLuongDn")
	public java.lang.Float getChiPhiLuongDn() {
		return chiPhiLuongDn;
	}

	public void setChiPhiLuongDn(java.lang.Float chiPhiLuongDn) {
		this.chiPhiLuongDn = chiPhiLuongDn;
	}

	@JsonProperty("vatDn")
	public java.lang.Float getVatDn() {
		return vatDn;
	}

	public void setVatDn(java.lang.Float vatDn) {
		this.vatDn = vatDn;
	}

	@JsonProperty("tongDn")
	public java.lang.Float getTongDn() {
		return tongDn;
	}

	public void setTongDn(java.lang.Float tongDn) {
		this.tongDn = tongDn;
	}

	@JsonProperty("nguoiCapNhat")
	public java.lang.String getNguoiCapNhat() {
		return nguoiCapNhat;
	}

	public void setNguoiCapNhat(java.lang.String nguoiCapNhat) {
		this.nguoiCapNhat = nguoiCapNhat;
	}

	@JsonProperty("ngayGuiPtkHshc")
	public java.util.Date getNgayGuiPtkHshc() {
		return ngayGuiPtkHshc;
	}

	public void setNgayGuiPtkHshc(java.util.Date ngayGuiPtkHshc) {
		this.ngayGuiPtkHshc = ngayGuiPtkHshc;
	}

	public java.util.Date getNgayGuiPtkHshcFrom() {
		return ngayGuiPtkHshcFrom;
	}

	public void setNgayGuiPtkHshcFrom(java.util.Date ngayGuiPtkHshcFrom) {
		this.ngayGuiPtkHshcFrom = ngayGuiPtkHshcFrom;
	}

	public java.util.Date getNgayGuiPtkHshcTo() {
		return ngayGuiPtkHshcTo;
	}

	public void setNgayGuiPtkHshcTo(java.util.Date ngayGuiPtkHshcTo) {
		this.ngayGuiPtkHshcTo = ngayGuiPtkHshcTo;
	}

	@JsonProperty("tinhTrangChungTu")
	public java.lang.String getTinhTrangChungTu() {
		return tinhTrangChungTu;
	}

	public void setTinhTrangChungTu(java.lang.String tinhTrangChungTu) {
		this.tinhTrangChungTu = tinhTrangChungTu;
	}

	@JsonProperty("ngayTutttt")
	public java.util.Date getNgayTutttt() {
		return ngayTutttt;
	}

	public void setNgayTutttt(java.util.Date ngayTutttt) {
		this.ngayTutttt = ngayTutttt;
	}

	public java.util.Date getNgayTuttttFrom() {
		return ngayTuttttFrom;
	}

	public void setNgayTuttttFrom(java.util.Date ngayTuttttFrom) {
		this.ngayTuttttFrom = ngayTuttttFrom;
	}

	public java.util.Date getNgayTuttttTo() {
		return ngayTuttttTo;
	}

	public void setNgayTuttttTo(java.util.Date ngayTuttttTo) {
		this.ngayTuttttTo = ngayTuttttTo;
	}

	@JsonProperty("ghiChuHsLoi")
	public java.lang.String getGhiChuHsLoi() {
		return ghiChuHsLoi;
	}

	public void setGhiChuHsLoi(java.lang.String ghiChuHsLoi) {
		this.ghiChuHsLoi = ghiChuHsLoi;
	}

	@JsonProperty("ngayPtkTdXong")
	public java.util.Date getNgayPtkTdXong() {
		return ngayPtkTdXong;
	}

	public void setNgayPtkTdXong(java.util.Date ngayPtkTdXong) {
		this.ngayPtkTdXong = ngayPtkTdXong;
	}

	public java.util.Date getNgayPtkTdXongFrom() {
		return ngayPtkTdXongFrom;
	}

	public void setNgayPtkTdXongFrom(java.util.Date ngayPtkTdXongFrom) {
		this.ngayPtkTdXongFrom = ngayPtkTdXongFrom;
	}

	public java.util.Date getNgayPtkTdXongTo() {
		return ngayPtkTdXongTo;
	}

	public void setNgayPtkTdXongTo(java.util.Date ngayPtkTdXongTo) {
		this.ngayPtkTdXongTo = ngayPtkTdXongTo;
	}

	@JsonProperty("cpNhanCongTd")
	public java.lang.Float getCpNhanCongTd() {
		return cpNhanCongTd;
	}

	public void setCpNhanCongTd(java.lang.Float cpNhanCongTd) {
		this.cpNhanCongTd = cpNhanCongTd;
	}

	@JsonProperty("cpVatLieuTd")
	public java.lang.Float getCpVatLieuTd() {
		return cpVatLieuTd;
	}

	public void setCpVatLieuTd(java.lang.Float cpVatLieuTd) {
		this.cpVatLieuTd = cpVatLieuTd;
	}

	@JsonProperty("cpHshcTd")
	public java.lang.Float getCpHshcTd() {
		return cpHshcTd;
	}

	public void setCpHshcTd(java.lang.Float cpHshcTd) {
		this.cpHshcTd = cpHshcTd;
	}

	@JsonProperty("cpVanChuyenTd")
	public java.lang.Float getCpVanChuyenTd() {
		return cpVanChuyenTd;
	}

	public void setCpVanChuyenTd(java.lang.Float cpVanChuyenTd) {
		this.cpVanChuyenTd = cpVanChuyenTd;
	}

	@JsonProperty("cpKhacTd")
	public java.lang.Float getCpKhacTd() {
		return cpKhacTd;
	}

	public void setCpKhacTd(java.lang.Float cpKhacTd) {
		this.cpKhacTd = cpKhacTd;
	}

	@JsonProperty("vatTd")
	public java.lang.Float getVatTd() {
		return vatTd;
	}

	public void setVatTd(java.lang.Float vatTd) {
		this.vatTd = vatTd;
	}

	@JsonProperty("tongTd")
	public java.lang.Float getTongTd() {
		return tongTd;
	}

	public void setTongTd(java.lang.Float tongTd) {
		this.tongTd = tongTd;
	}

	@JsonProperty("gtTdPtkChuaVat")
	public java.lang.Float getGtTdPtkChuaVat() {
		return gtTdPtkChuaVat;
	}

	public void setGtTdPtkChuaVat(java.lang.Float gtTdPtkChuaVat) {
		this.gtTdPtkChuaVat = gtTdPtkChuaVat;
	}

	@JsonProperty("gtTdPtkCoVat")
	public java.lang.Float getGtTdPtkCoVat() {
		return gtTdPtkCoVat;
	}

	public void setGtTdPtkCoVat(java.lang.Float gtTdPtkCoVat) {
		this.gtTdPtkCoVat = gtTdPtkCoVat;
	}

	@JsonProperty("thangHshcQuyLuong")
	public java.lang.String getThangHshcQuyLuong() {
		return thangHshcQuyLuong;
	}

	public void setThangHshcQuyLuong(java.lang.String thangHshcQuyLuong) {
		this.thangHshcQuyLuong = thangHshcQuyLuong;
	}

	@JsonProperty("thangGhiNhanQuyLuongTqt")
	public java.lang.String getThangGhiNhanQuyLuongTqt() {
		return thangGhiNhanQuyLuongTqt;
	}

	public void setThangGhiNhanQuyLuongTqt(java.lang.String thangGhiNhanQuyLuongTqt) {
		this.thangGhiNhanQuyLuongTqt = thangGhiNhanQuyLuongTqt;
	}

	@JsonProperty("tamUngLuong")
	public java.lang.Float getTamUngLuong() {
		return tamUngLuong;
	}

	public void setTamUngLuong(java.lang.Float tamUngLuong) {
		this.tamUngLuong = tamUngLuong;
	}

	@JsonProperty("luongThucNhan")
	public java.lang.Float getLuongThucNhan() {
		return luongThucNhan;
	}

	public void setLuongThucNhan(java.lang.Float luongThucNhan) {
		this.luongThucNhan = luongThucNhan;
	}

	@JsonProperty("tyLe")
	public java.lang.Float getTyLe() {
		return tyLe;
	}

	public void setTyLe(java.lang.Float tyLe) {
		this.tyLe = tyLe;
	}

	@JsonProperty("hsTonQua5n")
	public java.lang.String getHsTonQua5n() {
		return hsTonQua5n;
	}

	public void setHsTonQua5n(java.lang.String hsTonQua5n) {
		this.hsTonQua5n = hsTonQua5n;
	}

	@JsonProperty("gtQhXlCt")
	public java.lang.String getGtQhXlCt() {
		return gtQhXlCt;
	}

	public void setGtQhXlCt(java.lang.String gtQhXlCt) {
		this.gtQhXlCt = gtQhXlCt;
	}

	@JsonProperty("nguoiPheDuyetPtk")
	public java.lang.String getNguoiPheDuyetPtk() {
		return nguoiPheDuyetPtk;
	}

	public void setNguoiPheDuyetPtk(java.lang.String nguoiPheDuyetPtk) {
		this.nguoiPheDuyetPtk = nguoiPheDuyetPtk;
	}

	@JsonProperty("cpLuongTd")
	public java.lang.Float getCpLuongTd() {
		return cpLuongTd;
	}

	public void setCpLuongTd(java.lang.Float cpLuongTd) {
		this.cpLuongTd = cpLuongTd;
	}

	@JsonProperty("tenTinh")
	public java.lang.String getTenTinh() {
		return tenTinh;
	}

	public void setTenTinh(java.lang.String tenTinh) {
		this.tenTinh = tenTinh;
	}

	public long getTtct1() {
		return ttct1;
	}

	public void setTtct1(long ttct1) {
		this.ttct1 = ttct1;
	}

	public long getTtct2() {
		return ttct2;
	}

	public void setTtct2(long ttct2) {
		this.ttct2 = ttct2;
	}

	public long getTtct3() {
		return ttct3;
	}

	public void setTtct3(long ttct3) {
		this.ttct3 = ttct3;
	}

	public long getTtct4() {
		return ttct4;
	}

	public void setTtct4(long ttct4) {
		this.ttct4 = ttct4;
	}

	public long getTtct5() {
		return ttct5;
	}

	public void setTtct5(long ttct5) {
		this.ttct5 = ttct5;
	}

	public long getTtct6() {
		return ttct6;
	}

	public void setTtct6(long ttct6) {
		this.ttct6 = ttct6;
	}

	public long getTtct7() {
		return ttct7;
	}

	public void setTtct7(long ttct7) {
		this.ttct7 = ttct7;
	}

	public long getHsQua5N() {
		return hsQua5N;
	}

	public void setHsQua5N(long hsQua5N) {
		this.hsQua5N = hsQua5N;
	}

	public long getSoHs1() {
		return soHs1;
	}

	public void setSoHs1(long soHs1) {
		this.soHs1 = soHs1;
	}

	public java.lang.Float getCheckTrinhKy() {
		return checkTrinhKy;
	}

	public void setCheckTrinhKy(java.lang.Float checkTrinhKy) {
		this.checkTrinhKy = checkTrinhKy;
	}

	public java.lang.String getLyDoTc() {
		return lyDoTc;
	}

	public void setLyDoTc(java.lang.String lyDoTc) {
		this.lyDoTc = lyDoTc;
	}

	public java.lang.String getPathFile() {
		return pathFile;
	}

	public void setPathFile(java.lang.String pathFile) {
		this.pathFile = pathFile;
	}

	public java.util.Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(java.util.Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public java.lang.String getNguoiTao() {
		return nguoiTao;
	}

	public void setNguoiTao(java.lang.String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}

	public java.lang.String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(java.lang.String trangThai) {
		this.trangThai = trangThai;
	}

	public long getSoHdChuaTd() {
		return soHdChuaTd;
	}

	public void setSoHdChuaTd(long soHdChuaTd) {
		this.soHdChuaTd = soHdChuaTd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkTrinhKy == null) ? 0 : checkTrinhKy.hashCode());
		result = prime * result + ((chiPhiKhacDn == null) ? 0 : chiPhiKhacDn.hashCode());
		result = prime * result + ((chiPhiLuongDn == null) ? 0 : chiPhiLuongDn.hashCode());
		result = prime * result + ((cpHshcDn == null) ? 0 : cpHshcDn.hashCode());
		result = prime * result + ((cpNhanCongDn == null) ? 0 : cpNhanCongDn.hashCode());
		result = prime * result + ((cpVanChuyenDn == null) ? 0 : cpVanChuyenDn.hashCode());
		result = prime * result + ((cpVatLieuDn == null) ? 0 : cpVatLieuDn.hashCode());
		result = prime * result + ((maTramTuyen == null) ? 0 : maTramTuyen.hashCode());
		result = prime * result + ((soBill == null) ? 0 : soBill.hashCode());
		result = prime * result + ((soHd == null) ? 0 : soHd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KqHdTkDTO other = (KqHdTkDTO) obj;
		if (checkTrinhKy == null) {
			if (other.checkTrinhKy != null)
				return false;
		} else if (!checkTrinhKy.equals(other.checkTrinhKy))
			return false;
		if (chiPhiKhacDn == null) {
			if (other.chiPhiKhacDn != null)
				return false;
		} else if (!chiPhiKhacDn.equals(other.chiPhiKhacDn))
			return false;
		if (chiPhiLuongDn == null) {
			if (other.chiPhiLuongDn != null)
				return false;
		} else if (!chiPhiLuongDn.equals(other.chiPhiLuongDn))
			return false;
		if (cpHshcDn == null) {
			if (other.cpHshcDn != null)
				return false;
		} else if (!cpHshcDn.equals(other.cpHshcDn))
			return false;
		if (cpNhanCongDn == null) {
			if (other.cpNhanCongDn != null)
				return false;
		} else if (!cpNhanCongDn.equals(other.cpNhanCongDn))
			return false;
		if (cpVanChuyenDn == null) {
			if (other.cpVanChuyenDn != null)
				return false;
		} else if (!cpVanChuyenDn.equals(other.cpVanChuyenDn))
			return false;
		if (cpVatLieuDn == null) {
			if (other.cpVatLieuDn != null)
				return false;
		} else if (!cpVatLieuDn.equals(other.cpVatLieuDn))
			return false;
		if (maTramTuyen == null) {
			if (other.maTramTuyen != null)
				return false;
		} else if (!maTramTuyen.equals(other.maTramTuyen))
			return false;
		if (soBill == null) {
			if (other.soBill != null)
				return false;
		} else if (!soBill.equals(other.soBill))
			return false;
		if (soHd == null) {
			if (other.soHd != null)
				return false;
		} else if (!soHd.equals(other.soHd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KqHdTkDTO [kqHdTkId=" + kqHdTkId + ", tinh=" + tinh + ", maTramTuyen=" + maTramTuyen + ", nguonCapUng="
				+ nguonCapUng + ", soHd=" + soHd + ", soKhTc=" + soKhTc + ", ngayKyKh=" + ngayKyKh + ", ngayKyKhFrom="
				+ ngayKyKhFrom + ", ngayKyKhTo=" + ngayKyKhTo + ", thuocDmToTrinh=" + thuocDmToTrinh + ", loaiCt="
				+ loaiCt + ", noiDung=" + noiDung + ", ngayGuiHshc=" + ngayGuiHshc + ", ngayGuiHshcFrom="
				+ ngayGuiHshcFrom + ", ngayGuiHshcTo=" + ngayGuiHshcTo + ", soBill=" + soBill + ", ghiChu=" + ghiChu
				+ ", gtQtCdtChuaVat=" + gtQtCdtChuaVat + ", gtQtCdtCoVat=" + gtQtCdtCoVat + ", cpNhanCongDn="
				+ cpNhanCongDn + ", cpVatLieuDn=" + cpVatLieuDn + ", cpHshcDn=" + cpHshcDn + ", cpVanChuyenDn="
				+ cpVanChuyenDn + ", chiPhiKhacDn=" + chiPhiKhacDn + ", chiPhiLuongDn=" + chiPhiLuongDn + ", vatDn="
				+ vatDn + ", tongDn=" + tongDn + ", nguoiCapNhat=" + nguoiCapNhat + ", ngayGuiPtkHshc=" + ngayGuiPtkHshc
				+ ", ngayGuiPtkHshcFrom=" + ngayGuiPtkHshcFrom + ", ngayGuiPtkHshcTo=" + ngayGuiPtkHshcTo
				+ ", tinhTrangChungTu=" + tinhTrangChungTu + ", ngayTutttt=" + ngayTutttt + ", ngayTuttttFrom="
				+ ngayTuttttFrom + ", ngayTuttttTo=" + ngayTuttttTo + ", ghiChuHsLoi=" + ghiChuHsLoi
				+ ", ngayPtkTdXong=" + ngayPtkTdXong + ", ngayPtkTdXongFrom=" + ngayPtkTdXongFrom + ", ngayPtkTdXongTo="
				+ ngayPtkTdXongTo + ", cpNhanCongTd=" + cpNhanCongTd + ", cpVatLieuTd=" + cpVatLieuTd + ", cpHshcTd="
				+ cpHshcTd + ", cpVanChuyenTd=" + cpVanChuyenTd + ", cpKhacTd=" + cpKhacTd + ", vatTd=" + vatTd
				+ ", tongTd=" + tongTd + ", gtTdPtkChuaVat=" + gtTdPtkChuaVat + ", gtTdPtkCoVat=" + gtTdPtkCoVat
				+ ", thangHshcQuyLuong=" + thangHshcQuyLuong + ", thangGhiNhanQuyLuongTqt=" + thangGhiNhanQuyLuongTqt
				+ ", tamUngLuong=" + tamUngLuong + ", luongThucNhan=" + luongThucNhan + ", tyLe=" + tyLe
				+ ", hsTonQua5n=" + hsTonQua5n + ", gtQhXlCt=" + gtQhXlCt + ", nguoiPheDuyetPtk=" + nguoiPheDuyetPtk
				+ ", cpLuongTd=" + cpLuongTd + ", tenTinh=" + tenTinh + ", checkTrinhKy=" + checkTrinhKy + ", lyDoTc="
				+ lyDoTc + ", pathFile=" + pathFile + ", ngayCapNhat=" + ngayCapNhat + ", nguoiTao=" + nguoiTao
				+ ", trangThai=" + trangThai + ", ngayThiCong=" + ngayThiCong + ", ttct1=" + ttct1 + ", ttct2=" + ttct2
				+ ", ttct3=" + ttct3 + ", ttct4=" + ttct4 + ", ttct5=" + ttct5 + ", ttct6=" + ttct6 + ", ttct7=" + ttct7
				+ ", hsQua5N=" + hsQua5N + ", soHs1=" + soHs1 + ", soHdChuaTd=" + soHdChuaTd + ", lstObj=" + lstObj
				+ "]";
	}	

}
