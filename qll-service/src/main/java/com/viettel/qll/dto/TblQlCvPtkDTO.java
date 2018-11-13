package com.viettel.qll.dto;

import com.viettel.qll.bo.TblQlCvPtkBO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.collect.Lists;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_QL_CV_PTKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblQlCvPtkDTO extends QllBaseDTO<TblQlCvPtkBO> {

	private java.lang.Long tblQlCvPtkId;
	private java.lang.String cnkv;
	private java.lang.String tinh;
	private java.lang.String mttMaViTri;
	private java.lang.String mttMa2g;
	private java.lang.String mttMa3g;
	private java.lang.String mttMaXuatKho;
	private java.lang.String soHdCdt;
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
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayNhanHshc;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayNhanHshcFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayNhanHshcTo;
	private java.lang.String ghiChu;
	private java.lang.Float gtSlHtTtTong;
	private java.lang.Float gtSlHtTtXd;
	private java.lang.Float gtSlHtTtDien;
	private java.lang.Float gtSlHtTtLapDung;
	private java.lang.Float gtSlHtTtLapBts;
	private java.lang.Float gtSlHtTtKhac;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gtDnQtkCnNgay;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gtDnQtkCnNgayFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gtDnQtkCnNgayTo;
	private java.lang.Float gtDnQtkCnTong;
	private java.lang.Float gtDnQtkCnXd;
	private java.lang.Float gtDnQtkCnDien;
	private java.lang.Float gtDnQtkCnLapDung;
	private java.lang.Float gtDnQtkCnLapBts;
	private java.lang.Float gtDnQtkCnKhac;
	private java.lang.String gtDnQtkCnNguoiLap;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gtQtkPtkNgay;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gtQtkPtkNgayFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date gtQtkPtkNgayTo;
	private java.lang.Float gtQtkPtkTong;
	private java.lang.Float gtQtkPtkXd;
	private java.lang.Float gtQtkPtkDien;
	private java.lang.Float gtQtkPtkLapDung;
	private java.lang.Float gtQtkPtkLapBts;
	private java.lang.Float gtQtkPtkKhac;
	private java.lang.String gtQtkPtkNguoiChot;
	private java.lang.String gtQtkPtkDmtt;
	private java.lang.String gtQtkPtkThangQtk;
	private java.lang.String tinhTrangCct1;
	private java.lang.String ghiChuHsLoi;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date qtDtNgay;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date qtDtNgayFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date qtDtNgayTo;
	private java.lang.Float qtDtGtDnDt;
	private java.lang.String qtDtSoHd;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date qtDtNgayTdDt;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date qtDtNgayTdDtFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date qtDtNgayTdDtTo;
	private java.lang.Float qtDtTong;
	private java.lang.Float qtDtXd;
	private java.lang.Float qtDtDien;
	private java.lang.Float qtDtLapDung;
	private java.lang.Float qtDtLapBts;
	private java.lang.Float qtDtKhac;
	private java.lang.String qtDtTtDt;
	private java.lang.String qtDtNguoiTd;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date dnQtCdtNgay;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date dnQtCdtNgayFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date dnQtCdtNgayTo;
	private java.lang.Float dnQtCdtGt;
	private java.lang.String dnQtCdtNguoiLap;
	private java.lang.String dnQtCdtNguoiNhanBg;
	private java.lang.String dnQtCdtNoiDungPsCtk;
	private java.lang.String dnQtCdtKtk;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayChot;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayChotFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayChotTo;
	private java.lang.Float tdQtCdtGt;
	private java.lang.String tdQtCdtNguoiChotMvt;
	private java.lang.String tdQtCdtNguoiTdCdtMvt;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayBanTd;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayBanTdFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayBanTdTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayCtc;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayCtcFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date tdQtCdtNgayCtcTo;
	private java.lang.String tdQtCdtNoiDung;
	private java.lang.String tinhTrangCct2;
	private java.lang.String ghiChu2;
	private java.lang.String luuTaiKho;
	private java.lang.String trangThai;
	private java.lang.String pathFile;
	
	private java.lang.Long soLuongHD,soLuongGtHt,soLuongDnQtk,soLuongTdQtk,soLuongDnCdt,soLuongTdCdt,soLuongQtDt;
	private java.lang.Float sumGtHt,sumDnQtk,sumTdQtk,sumDnCdt,sumTdCdt,sumQtDt;
	private java.lang.Float gtHtWithDnQtk,gtHtWithTdQtk,gtHtWithDnCdt,gtHtWithTdCdt,gtHtWithQtDt;
	
	private java.lang.Long soLuongMaTram;
	
	private List<Float> listGtSlHtTtTong =  Lists.newArrayList();
	private List<Float> listDnQtCdtGt = Lists.newArrayList();
	private List<Float> listTdQtCdtGt = Lists.newArrayList();
	private List<String> listCnkv = Lists.newArrayList();
	private List<Long> listSoLuongMaTramGtSl = Lists.newArrayList();
	private List<Long> listSoLuongMaTramDnQtCdt = Lists.newArrayList();
	private List<Long> listSoLuongMaTramTdQtCdt = Lists.newArrayList();
	private List<Float> listGtSlHtTtTongDn = Lists.newArrayList();
	private List<Float> listGtSlHtTtTongTd = Lists.newArrayList();
	private List<String> listMaViTri = Lists.newArrayList();
	
	private String nhanVien1,nhanVien2,nhanVien3,nhanVien4,nhanVien5;
	
	
	//soLuongHD số lượng SO_HD_CDT
	//soLuongGtHt số lượng GT_SL_HT_TT_TONG
	//soLuongDnQtk số lượng GT_DN_QTK_CN_TONG
	//soLuongTdQtk số lượng GT_QTK_PTK_TONG
	//soLuongDnCdt số lượng DN_QT_CDT_GT
	//soLuongTdCdt số lượng TD_QT_CDT_GT
	//soLuongQtDt số lượng QT_DT_TONG
	
	//sumGtHt tổng GT_SL_HT_TT_TONG ...
	
	//gtHtWithDnQtk tổng GT_SL_HT_TT_TONG theo GT_DN_QTK_CN_TONG...

	public String getNhanVien1() {
		return nhanVien1;
	}

	public void setNhanVien1(String nhanVien1) {
		this.nhanVien1 = nhanVien1;
	}

	public String getNhanVien2() {
		return nhanVien2;
	}

	public void setNhanVien2(String nhanVien2) {
		this.nhanVien2 = nhanVien2;
	}

	public String getNhanVien3() {
		return nhanVien3;
	}

	public void setNhanVien3(String nhanVien3) {
		this.nhanVien3 = nhanVien3;
	}

	public String getNhanVien4() {
		return nhanVien4;
	}

	public void setNhanVien4(String nhanVien4) {
		this.nhanVien4 = nhanVien4;
	}

	public String getNhanVien5() {
		return nhanVien5;
	}

	public void setNhanVien5(String nhanVien5) {
		this.nhanVien5 = nhanVien5;
	}

	public java.lang.Float getGtHtWithDnQtk() {
		return gtHtWithDnQtk;
	}

	public void setGtHtWithDnQtk(java.lang.Float gtHtWithDnQtk) {
		this.gtHtWithDnQtk = gtHtWithDnQtk;
	}

	public java.lang.Float getGtHtWithTdQtk() {
		return gtHtWithTdQtk;
	}

	public void setGtHtWithTdQtk(java.lang.Float gtHtWithTdQtk) {
		this.gtHtWithTdQtk = gtHtWithTdQtk;
	}

	public java.lang.Float getGtHtWithDnCdt() {
		return gtHtWithDnCdt;
	}

	public void setGtHtWithDnCdt(java.lang.Float gtHtWithDnCdt) {
		this.gtHtWithDnCdt = gtHtWithDnCdt;
	}

	public java.lang.Float getGtHtWithTdCdt() {
		return gtHtWithTdCdt;
	}

	public void setGtHtWithTdCdt(java.lang.Float gtHtWithTdCdt) {
		this.gtHtWithTdCdt = gtHtWithTdCdt;
	}

	public java.lang.Float getGtHtWithQtDt() {
		return gtHtWithQtDt;
	}

	public void setGtHtWithQtDt(java.lang.Float gtHtWithQtDt) {
		this.gtHtWithQtDt = gtHtWithQtDt;
	}

	public java.lang.Long getSoLuongHD() {
		return soLuongHD;
	}



	public void setSoLuongHD(java.lang.Long soLuongHD) {
		this.soLuongHD = soLuongHD;
	}

	public java.lang.Long getSoLuongGtHt() {
		return soLuongGtHt;
	}

	public void setSoLuongGtHt(java.lang.Long soLuongGtHt) {
		this.soLuongGtHt = soLuongGtHt;
	}

	public java.lang.Long getSoLuongDnQtk() {
		return soLuongDnQtk;
	}


	public void setSoLuongDnQtk(java.lang.Long soLuongDnQtk) {
		this.soLuongDnQtk = soLuongDnQtk;
	}

	public java.lang.Long getSoLuongTdQtk() {
		return soLuongTdQtk;
	}

	public void setSoLuongTdQtk(java.lang.Long soLuongTdQtk) {
		this.soLuongTdQtk = soLuongTdQtk;
	}


	public java.lang.Long getSoLuongDnCdt() {
		return soLuongDnCdt;
	}

	public void setSoLuongDnCdt(java.lang.Long soLuongDnCdt) {
		this.soLuongDnCdt = soLuongDnCdt;
	}

	public java.lang.Long getSoLuongTdCdt() {
		return soLuongTdCdt;
	}

	public void setSoLuongTdCdt(java.lang.Long soLuongTdCdt) {
		this.soLuongTdCdt = soLuongTdCdt;
	}

	public java.lang.Long getSoLuongQtDt() {
		return soLuongQtDt;
	}

	public void setSoLuongQtDt(java.lang.Long soLuongQtDt) {
		this.soLuongQtDt = soLuongQtDt;
	}

	public java.lang.Float getSumGtHt() {
		return sumGtHt;
	}

	public void setSumGtHt(java.lang.Float sumGtHt) {
		this.sumGtHt = sumGtHt;
	}

	public java.lang.Float getSumDnQtk() {
		return sumDnQtk;
	}

	public void setSumDnQtk(java.lang.Float sumDnQtk) {
		this.sumDnQtk = sumDnQtk;
	}

	public java.lang.Float getSumTdQtk() {
		return sumTdQtk;
	}

	public void setSumTdQtk(java.lang.Float sumTdQtk) {
		this.sumTdQtk = sumTdQtk;
	}

	public java.lang.Float getSumDnCdt() {
		return sumDnCdt;
	}

	public void setSumDnCdt(java.lang.Float sumDnCdt) {
		this.sumDnCdt = sumDnCdt;
	}

	public java.lang.Float getSumTdCdt() {
		return sumTdCdt;
	}

	public void setSumTdCdt(java.lang.Float sumTdCdt) {
		this.sumTdCdt = sumTdCdt;
	}

	public java.lang.Float getSumQtDt() {
		return sumQtDt;
	}

	public void setSumQtDt(java.lang.Float sumQtDt) {
		this.sumQtDt = sumQtDt;
	}
	
	public java.lang.Long getSoLuongMaTram() {
		return soLuongMaTram;
	}

	public void setSoLuongMaTram(java.lang.Long soLuongMaTram) {
		this.soLuongMaTram = soLuongMaTram;
	}
	
	
	public List<String> getListMaViTri() {
		return listMaViTri;
	}

	public void setListMaViTri(List<String> listMaViTri) {
		this.listMaViTri = listMaViTri;
	}

	public List<Float> getListGtSlHtTtTong() {
		return listGtSlHtTtTong;
	}

	public void setListGtSlHtTtTong(List<Float> listGtSlHtTtTong) {
		this.listGtSlHtTtTong = listGtSlHtTtTong;
	}

	public List<Float> getListDnQtCdtGt() {
		return listDnQtCdtGt;
	}

	public void setListDnQtCdtGt(List<Float> listDnQtCdtGt) {
		this.listDnQtCdtGt = listDnQtCdtGt;
	}

	public List<Float> getListTdQtCdtGt() {
		return listTdQtCdtGt;
	}

	public void setListTdQtCdtGt(List<Float> listTdQtCdtGt) {
		this.listTdQtCdtGt = listTdQtCdtGt;
	}

	public List<String> getListCnkv() {
		return listCnkv;
	}

	public void setListCnkv(List<String> listCnkv) {
		this.listCnkv = listCnkv;
	}

	public List<Long> getListSoLuongMaTramGtSl() {
		return listSoLuongMaTramGtSl;
	}

	public void setListSoLuongMaTramGtSl(List<Long> listSoLuongMaTramGtSl) {
		this.listSoLuongMaTramGtSl = listSoLuongMaTramGtSl;
	}

	public List<Long> getListSoLuongMaTramDnQtCdt() {
		return listSoLuongMaTramDnQtCdt;
	}

	public void setListSoLuongMaTramDnQtCdt(List<Long> listSoLuongMaTramDnQtCdt) {
		this.listSoLuongMaTramDnQtCdt = listSoLuongMaTramDnQtCdt;
	}

	public List<Long> getListSoLuongMaTramTdQtCdt() {
		return listSoLuongMaTramTdQtCdt;
	}

	public void setListSoLuongMaTramTdQtCdt(List<Long> listSoLuongMaTramTdQtCdt) {
		this.listSoLuongMaTramTdQtCdt = listSoLuongMaTramTdQtCdt;
	}

	public List<Float> getListGtSlHtTtTongDn() {
		return listGtSlHtTtTongDn;
	}

	public void setListGtSlHtTtTongDn(List<Float> listGtSlHtTtTongDn) {
		this.listGtSlHtTtTongDn = listGtSlHtTtTongDn;
	}

	public List<Float> getListGtSlHtTtTongTd() {
		return listGtSlHtTtTongTd;
	}

	public void setListGtSlHtTtTongTd(List<Float> listGtSlHtTtTongTd) {
		this.listGtSlHtTtTongTd = listGtSlHtTtTongTd;
	}

	@Override
    public TblQlCvPtkBO toModel() {
        TblQlCvPtkBO tblQlCvPtkBO = new TblQlCvPtkBO();
        tblQlCvPtkBO.setTblQlCvPtkId(this.tblQlCvPtkId);
        tblQlCvPtkBO.setCnkv(this.cnkv);
        tblQlCvPtkBO.setTinh(this.tinh);
        tblQlCvPtkBO.setMttMaViTri(this.mttMaViTri);
        tblQlCvPtkBO.setMttMa2g(this.mttMa2g);
        tblQlCvPtkBO.setMttMa3g(this.mttMa3g);
        tblQlCvPtkBO.setMttMaXuatKho(this.mttMaXuatKho);
        tblQlCvPtkBO.setSoHdCdt(this.soHdCdt);
        tblQlCvPtkBO.setLoaiCt(this.loaiCt);
        tblQlCvPtkBO.setNoiDung(this.noiDung);
        tblQlCvPtkBO.setNgayGuiHshc(this.ngayGuiHshc);
        tblQlCvPtkBO.setSoBill(this.soBill);
        tblQlCvPtkBO.setNgayNhanHshc(this.ngayNhanHshc);
        tblQlCvPtkBO.setGhiChu(this.ghiChu);
        tblQlCvPtkBO.setGtSlHtTtTong(this.gtSlHtTtTong);
        tblQlCvPtkBO.setGtSlHtTtXd(this.gtSlHtTtXd);
        tblQlCvPtkBO.setGtSlHtTtDien(this.gtSlHtTtDien);
        tblQlCvPtkBO.setGtSlHtTtLapDung(this.gtSlHtTtLapDung);
        tblQlCvPtkBO.setGtSlHtTtLapBts(this.gtSlHtTtLapBts);
        tblQlCvPtkBO.setGtSlHtTtKhac(this.gtSlHtTtKhac);
        tblQlCvPtkBO.setGtDnQtkCnNgay(this.gtDnQtkCnNgay);
        tblQlCvPtkBO.setGtDnQtkCnTong(this.gtDnQtkCnTong);
        tblQlCvPtkBO.setGtDnQtkCnXd(this.gtDnQtkCnXd);
        tblQlCvPtkBO.setGtDnQtkCnDien(this.gtDnQtkCnDien);
        tblQlCvPtkBO.setGtDnQtkCnLapDung(this.gtDnQtkCnLapDung);
        tblQlCvPtkBO.setGtDnQtkCnLapBts(this.gtDnQtkCnLapBts);
        tblQlCvPtkBO.setGtDnQtkCnKhac(this.gtDnQtkCnKhac);
        tblQlCvPtkBO.setGtDnQtkCnNguoiLap(this.gtDnQtkCnNguoiLap);
        tblQlCvPtkBO.setGtQtkPtkNgay(this.gtQtkPtkNgay);
        tblQlCvPtkBO.setGtQtkPtkTong(this.gtQtkPtkTong);
        tblQlCvPtkBO.setGtQtkPtkXd(this.gtQtkPtkXd);
        tblQlCvPtkBO.setGtQtkPtkDien(this.gtQtkPtkDien);
        tblQlCvPtkBO.setGtQtkPtkLapDung(this.gtQtkPtkLapDung);
        tblQlCvPtkBO.setGtQtkPtkLapBts(this.gtQtkPtkLapBts);
        tblQlCvPtkBO.setGtQtkPtkKhac(this.gtQtkPtkKhac);
        tblQlCvPtkBO.setGtQtkPtkNguoiChot(this.gtQtkPtkNguoiChot);
        tblQlCvPtkBO.setGtQtkPtkDmtt(this.gtQtkPtkDmtt);
        tblQlCvPtkBO.setGtQtkPtkThangQtk(this.gtQtkPtkThangQtk);
        tblQlCvPtkBO.setTinhTrangCct1(this.tinhTrangCct1);
        tblQlCvPtkBO.setGhiChuHsLoi(this.ghiChuHsLoi);
        tblQlCvPtkBO.setQtDtNgay(this.qtDtNgay);
        tblQlCvPtkBO.setQtDtGtDnDt(this.qtDtGtDnDt);
        tblQlCvPtkBO.setQtDtSoHd(this.qtDtSoHd);
        tblQlCvPtkBO.setQtDtNgayTdDt(this.qtDtNgayTdDt);
        tblQlCvPtkBO.setQtDtTong(this.qtDtTong);
        tblQlCvPtkBO.setQtDtXd(this.qtDtXd);
        tblQlCvPtkBO.setQtDtDien(this.qtDtDien);
        tblQlCvPtkBO.setQtDtLapDung(this.qtDtLapDung);
        tblQlCvPtkBO.setQtDtLapBts(this.qtDtLapBts);
        tblQlCvPtkBO.setQtDtKhac(this.qtDtKhac);
        tblQlCvPtkBO.setQtDtTtDt(this.qtDtTtDt);
        tblQlCvPtkBO.setQtDtNguoiTd(this.qtDtNguoiTd);
        tblQlCvPtkBO.setDnQtCdtNgay(this.dnQtCdtNgay);
        tblQlCvPtkBO.setDnQtCdtGt(this.dnQtCdtGt);
        tblQlCvPtkBO.setDnQtCdtNguoiLap(this.dnQtCdtNguoiLap);
        tblQlCvPtkBO.setDnQtCdtNguoiNhanBg(this.dnQtCdtNguoiNhanBg);
        tblQlCvPtkBO.setDnQtCdtNoiDungPsCtk(this.dnQtCdtNoiDungPsCtk);
        tblQlCvPtkBO.setDnQtCdtKtk(this.dnQtCdtKtk);
        tblQlCvPtkBO.setTdQtCdtNgayChot(this.tdQtCdtNgayChot);
        tblQlCvPtkBO.setTdQtCdtGt(this.tdQtCdtGt);
        tblQlCvPtkBO.setTdQtCdtNguoiChotMvt(this.tdQtCdtNguoiChotMvt);
        tblQlCvPtkBO.setTdQtCdtNguoiTdCdtMvt(this.tdQtCdtNguoiTdCdtMvt);
        tblQlCvPtkBO.setTdQtCdtNgayBanTd(this.tdQtCdtNgayBanTd);
        tblQlCvPtkBO.setTdQtCdtNgayCtc(this.tdQtCdtNgayCtc);
        tblQlCvPtkBO.setTdQtCdtNoiDung(this.tdQtCdtNoiDung);
        tblQlCvPtkBO.setTinhTrangCct2(this.tinhTrangCct2);
        tblQlCvPtkBO.setGhiChu2(this.ghiChu2);
        tblQlCvPtkBO.setLuuTaiKho(this.luuTaiKho);
        tblQlCvPtkBO.setTrangThai(this.trangThai);
        tblQlCvPtkBO.setPathFile(this.pathFile);
       
        return tblQlCvPtkBO;
    }
	
    
	


	





	@Override
     public Long getFWModelId() {
        return tblQlCvPtkId;
    }
   
    @Override
    public String catchName() {
        return getTblQlCvPtkId().toString();
    }
	
	@JsonProperty("tblQlCvPtkId")
    public java.lang.Long getTblQlCvPtkId(){
		return tblQlCvPtkId;
    }
	
    public void setTblQlCvPtkId(java.lang.Long tblQlCvPtkId){
		this.tblQlCvPtkId = tblQlCvPtkId;
    }	
	
	@JsonProperty("cnkv")
    public java.lang.String getCnkv(){
		return cnkv;
    }
	
    public void setCnkv(java.lang.String cnkv){
		this.cnkv = cnkv;
    }	
	
	@JsonProperty("tinh")
    public java.lang.String getTinh(){
		return tinh;
    }
	
    public void setTinh(java.lang.String tinh){
		this.tinh = tinh;
    }	
	
	@JsonProperty("mttMaViTri")
    public java.lang.String getMttMaViTri(){
		return mttMaViTri;
    }
	
    public void setMttMaViTri(java.lang.String mttMaViTri){
		this.mttMaViTri = mttMaViTri;
    }	
	
	@JsonProperty("mttMa2g")
    public java.lang.String getMttMa2g(){
		return mttMa2g;
    }
	
    public void setMttMa2g(java.lang.String mttMa2g){
		this.mttMa2g = mttMa2g;
    }	
	
	@JsonProperty("mttMa3g")
    public java.lang.String getMttMa3g(){
		return mttMa3g;
    }
	
    public void setMttMa3g(java.lang.String mttMa3g){
		this.mttMa3g = mttMa3g;
    }	
	
	@JsonProperty("mttMaXuatKho")
    public java.lang.String getMttMaXuatKho(){
		return mttMaXuatKho;
    }
	
    public void setMttMaXuatKho(java.lang.String mttMaXuatKho){
		this.mttMaXuatKho = mttMaXuatKho;
    }	
	
	@JsonProperty("soHdCdt")
    public java.lang.String getSoHdCdt(){
		return soHdCdt;
    }
	
    public void setSoHdCdt(java.lang.String soHdCdt){
		this.soHdCdt = soHdCdt;
    }	
	
	@JsonProperty("loaiCt")
    public java.lang.String getLoaiCt(){
		return loaiCt;
    }
	
    public void setLoaiCt(java.lang.String loaiCt){
		this.loaiCt = loaiCt;
    }	
	
	@JsonProperty("noiDung")
    public java.lang.String getNoiDung(){
		return noiDung;
    }
	
    public void setNoiDung(java.lang.String noiDung){
		this.noiDung = noiDung;
    }	
	
	@JsonProperty("ngayGuiHshc")
    public java.util.Date getNgayGuiHshc(){
		return ngayGuiHshc;
    }
	
    public void setNgayGuiHshc(java.util.Date ngayGuiHshc){
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
    public java.lang.String getSoBill(){
		return soBill;
    }
	
    public void setSoBill(java.lang.String soBill){
		this.soBill = soBill;
    }	
	
	@JsonProperty("ngayNhanHshc")
    public java.util.Date getNgayNhanHshc(){
		return ngayNhanHshc;
    }
	
    public void setNgayNhanHshc(java.util.Date ngayNhanHshc){
		this.ngayNhanHshc = ngayNhanHshc;
    }	
	
	public java.util.Date getNgayNhanHshcFrom() {
    	return ngayNhanHshcFrom;
    }
	
    public void setNgayNhanHshcFrom(java.util.Date ngayNhanHshcFrom) {
    	this.ngayNhanHshcFrom = ngayNhanHshcFrom;
    }
	
	public java.util.Date getNgayNhanHshcTo() {
    	return ngayNhanHshcTo;
    }
	
    public void setNgayNhanHshcTo(java.util.Date ngayNhanHshcTo) {
    	this.ngayNhanHshcTo = ngayNhanHshcTo;
    }
	
	@JsonProperty("ghiChu")
    public java.lang.String getGhiChu(){
		return ghiChu;
    }
	
    public void setGhiChu(java.lang.String ghiChu){
		this.ghiChu = ghiChu;
    }	
	
	@JsonProperty("gtSlHtTtTong")
    public java.lang.Float getGtSlHtTtTong(){
		return gtSlHtTtTong;
    }
	
    public void setGtSlHtTtTong(java.lang.Float gtSlHtTtTong){
		this.gtSlHtTtTong = gtSlHtTtTong;
    }	
	
	@JsonProperty("gtSlHtTtXd")
    public java.lang.Float getGtSlHtTtXd(){
		return gtSlHtTtXd;
    }
	
    public void setGtSlHtTtXd(java.lang.Float gtSlHtTtXd){
		this.gtSlHtTtXd = gtSlHtTtXd;
    }	
	
	@JsonProperty("gtSlHtTtDien")
    public java.lang.Float getGtSlHtTtDien(){
		return gtSlHtTtDien;
    }
	
    public void setGtSlHtTtDien(java.lang.Float gtSlHtTtDien){
		this.gtSlHtTtDien = gtSlHtTtDien;
    }	
	
	@JsonProperty("gtSlHtTtLapDung")
    public java.lang.Float getGtSlHtTtLapDung(){
		return gtSlHtTtLapDung;
    }
	
    public void setGtSlHtTtLapDung(java.lang.Float gtSlHtTtLapDung){
		this.gtSlHtTtLapDung = gtSlHtTtLapDung;
    }	
	
	@JsonProperty("gtSlHtTtLapBts")
    public java.lang.Float getGtSlHtTtLapBts(){
		return gtSlHtTtLapBts;
    }
	
    public void setGtSlHtTtLapBts(java.lang.Float gtSlHtTtLapBts){
		this.gtSlHtTtLapBts = gtSlHtTtLapBts;
    }	
	
	@JsonProperty("gtSlHtTtKhac")
    public java.lang.Float getGtSlHtTtKhac(){
		return gtSlHtTtKhac;
    }
	
    public void setGtSlHtTtKhac(java.lang.Float gtSlHtTtKhac){
		this.gtSlHtTtKhac = gtSlHtTtKhac;
    }	
	
	@JsonProperty("gtDnQtkCnNgay")
    public java.util.Date getGtDnQtkCnNgay(){
		return gtDnQtkCnNgay;
    }
	
    public void setGtDnQtkCnNgay(java.util.Date gtDnQtkCnNgay){
		this.gtDnQtkCnNgay = gtDnQtkCnNgay;
    }	
	
	public java.util.Date getGtDnQtkCnNgayFrom() {
    	return gtDnQtkCnNgayFrom;
    }
	
    public void setGtDnQtkCnNgayFrom(java.util.Date gtDnQtkCnNgayFrom) {
    	this.gtDnQtkCnNgayFrom = gtDnQtkCnNgayFrom;
    }
	
	public java.util.Date getGtDnQtkCnNgayTo() {
    	return gtDnQtkCnNgayTo;
    }
	
    public void setGtDnQtkCnNgayTo(java.util.Date gtDnQtkCnNgayTo) {
    	this.gtDnQtkCnNgayTo = gtDnQtkCnNgayTo;
    }
	
	@JsonProperty("gtDnQtkCnTong")
    public java.lang.Float getGtDnQtkCnTong(){
		return gtDnQtkCnTong;
    }
	
    public void setGtDnQtkCnTong(java.lang.Float gtDnQtkCnTong){
		this.gtDnQtkCnTong = gtDnQtkCnTong;
    }	
	
	@JsonProperty("gtDnQtkCnXd")
    public java.lang.Float getGtDnQtkCnXd(){
		return gtDnQtkCnXd;
    }
	
    public void setGtDnQtkCnXd(java.lang.Float gtDnQtkCnXd){
		this.gtDnQtkCnXd = gtDnQtkCnXd;
    }	
	
	@JsonProperty("gtDnQtkCnDien")
    public java.lang.Float getGtDnQtkCnDien(){
		return gtDnQtkCnDien;
    }
	
    public void setGtDnQtkCnDien(java.lang.Float gtDnQtkCnDien){
		this.gtDnQtkCnDien = gtDnQtkCnDien;
    }	
	
	@JsonProperty("gtDnQtkCnLapDung")
    public java.lang.Float getGtDnQtkCnLapDung(){
		return gtDnQtkCnLapDung;
    }
	
    public void setGtDnQtkCnLapDung(java.lang.Float gtDnQtkCnLapDung){
		this.gtDnQtkCnLapDung = gtDnQtkCnLapDung;
    }	
	
	@JsonProperty("gtDnQtkCnLapBts")
    public java.lang.Float getGtDnQtkCnLapBts(){
		return gtDnQtkCnLapBts;
    }
	
    public void setGtDnQtkCnLapBts(java.lang.Float gtDnQtkCnLapBts){
		this.gtDnQtkCnLapBts = gtDnQtkCnLapBts;
    }	
	
	@JsonProperty("gtDnQtkCnKhac")
    public java.lang.Float getGtDnQtkCnKhac(){
		return gtDnQtkCnKhac;
    }
	
    public void setGtDnQtkCnKhac(java.lang.Float gtDnQtkCnKhac){
		this.gtDnQtkCnKhac = gtDnQtkCnKhac;
    }	
	
	@JsonProperty("gtDnQtkCnNguoiLap")
    public java.lang.String getGtDnQtkCnNguoiLap(){
		return gtDnQtkCnNguoiLap;
    }
	
    public void setGtDnQtkCnNguoiLap(java.lang.String gtDnQtkCnNguoiLap){
		this.gtDnQtkCnNguoiLap = gtDnQtkCnNguoiLap;
    }	
	
	@JsonProperty("gtQtkPtkNgay")
    public java.util.Date getGtQtkPtkNgay(){
		return gtQtkPtkNgay;
    }
	
    public void setGtQtkPtkNgay(java.util.Date gtQtkPtkNgay){
		this.gtQtkPtkNgay = gtQtkPtkNgay;
    }	
	
	public java.util.Date getGtQtkPtkNgayFrom() {
    	return gtQtkPtkNgayFrom;
    }
	
    public void setGtQtkPtkNgayFrom(java.util.Date gtQtkPtkNgayFrom) {
    	this.gtQtkPtkNgayFrom = gtQtkPtkNgayFrom;
    }
	
	public java.util.Date getGtQtkPtkNgayTo() {
    	return gtQtkPtkNgayTo;
    }
	
    public void setGtQtkPtkNgayTo(java.util.Date gtQtkPtkNgayTo) {
    	this.gtQtkPtkNgayTo = gtQtkPtkNgayTo;
    }
	
	@JsonProperty("gtQtkPtkTong")
    public java.lang.Float getGtQtkPtkTong(){
		return gtQtkPtkTong;
    }
	
    public void setGtQtkPtkTong(java.lang.Float gtQtkPtkTong){
		this.gtQtkPtkTong = gtQtkPtkTong;
    }	
	
	@JsonProperty("gtQtkPtkXd")
    public java.lang.Float getGtQtkPtkXd(){
		return gtQtkPtkXd;
    }
	
    public void setGtQtkPtkXd(java.lang.Float gtQtkPtkXd){
		this.gtQtkPtkXd = gtQtkPtkXd;
    }	
	
	@JsonProperty("gtQtkPtkDien")
    public java.lang.Float getGtQtkPtkDien(){
		return gtQtkPtkDien;
    }
	
    public void setGtQtkPtkDien(java.lang.Float gtQtkPtkDien){
		this.gtQtkPtkDien = gtQtkPtkDien;
    }	
	
	@JsonProperty("gtQtkPtkLapDung")
    public java.lang.Float getGtQtkPtkLapDung(){
		return gtQtkPtkLapDung;
    }
	
    public void setGtQtkPtkLapDung(java.lang.Float gtQtkPtkLapDung){
		this.gtQtkPtkLapDung = gtQtkPtkLapDung;
    }	
	
	@JsonProperty("gtQtkPtkLapBts")
    public java.lang.Float getGtQtkPtkLapBts(){
		return gtQtkPtkLapBts;
    }
	
    public void setGtQtkPtkLapBts(java.lang.Float gtQtkPtkLapBts){
		this.gtQtkPtkLapBts = gtQtkPtkLapBts;
    }	
	
	@JsonProperty("gtQtkPtkKhac")
    public java.lang.Float getGtQtkPtkKhac(){
		return gtQtkPtkKhac;
    }
	
    public void setGtQtkPtkKhac(java.lang.Float gtQtkPtkKhac){
		this.gtQtkPtkKhac = gtQtkPtkKhac;
    }	
	
	@JsonProperty("gtQtkPtkNguoiChot")
    public java.lang.String getGtQtkPtkNguoiChot(){
		return gtQtkPtkNguoiChot;
    }
	
    public void setGtQtkPtkNguoiChot(java.lang.String gtQtkPtkNguoiChot){
		this.gtQtkPtkNguoiChot = gtQtkPtkNguoiChot;
    }	
	
	@JsonProperty("gtQtkPtkDmtt")
    public java.lang.String getGtQtkPtkDmtt(){
		return gtQtkPtkDmtt;
    }
	
    public void setGtQtkPtkDmtt(java.lang.String gtQtkPtkDmtt){
		this.gtQtkPtkDmtt = gtQtkPtkDmtt;
    }	
	
	@JsonProperty("gtQtkPtkThangQtk")
    public java.lang.String getGtQtkPtkThangQtk(){
		return gtQtkPtkThangQtk;
    }
	
    public void setGtQtkPtkThangQtk(java.lang.String gtQtkPtkThangQtk){
		this.gtQtkPtkThangQtk = gtQtkPtkThangQtk;
    }	
	
	@JsonProperty("tinhTrangCct1")
    public java.lang.String getTinhTrangCct1(){
		return tinhTrangCct1;
    }
	
    public void setTinhTrangCct1(java.lang.String tinhTrangCct1){
		this.tinhTrangCct1 = tinhTrangCct1;
    }	
	
	@JsonProperty("ghiChuHsLoi")
    public java.lang.String getGhiChuHsLoi(){
		return ghiChuHsLoi;
    }
	
    public void setGhiChuHsLoi(java.lang.String ghiChuHsLoi){
		this.ghiChuHsLoi = ghiChuHsLoi;
    }	
	
	@JsonProperty("qtDtNgay")
    public java.util.Date getQtDtNgay(){
		return qtDtNgay;
    }
	
    public void setQtDtNgay(java.util.Date qtDtNgay){
		this.qtDtNgay = qtDtNgay;
    }	
	
	public java.util.Date getQtDtNgayFrom() {
    	return qtDtNgayFrom;
    }
	
    public void setQtDtNgayFrom(java.util.Date qtDtNgayFrom) {
    	this.qtDtNgayFrom = qtDtNgayFrom;
    }
	
	public java.util.Date getQtDtNgayTo() {
    	return qtDtNgayTo;
    }
	
    public void setQtDtNgayTo(java.util.Date qtDtNgayTo) {
    	this.qtDtNgayTo = qtDtNgayTo;
    }
	
	@JsonProperty("qtDtGtDnDt")
    public java.lang.Float getQtDtGtDnDt(){
		return qtDtGtDnDt;
    }
	
    public void setQtDtGtDnDt(java.lang.Float qtDtGtDnDt){
		this.qtDtGtDnDt = qtDtGtDnDt;
    }	
	
	@JsonProperty("qtDtSoHd")
    public java.lang.String getQtDtSoHd(){
		return qtDtSoHd;
    }
	
    public void setQtDtSoHd(java.lang.String qtDtSoHd){
		this.qtDtSoHd = qtDtSoHd;
    }	
	
	@JsonProperty("qtDtNgayTdDt")
    public java.util.Date getQtDtNgayTdDt(){
		return qtDtNgayTdDt;
    }
	
    public void setQtDtNgayTdDt(java.util.Date qtDtNgayTdDt){
		this.qtDtNgayTdDt = qtDtNgayTdDt;
    }	
	
	public java.util.Date getQtDtNgayTdDtFrom() {
    	return qtDtNgayTdDtFrom;
    }
	
    public void setQtDtNgayTdDtFrom(java.util.Date qtDtNgayTdDtFrom) {
    	this.qtDtNgayTdDtFrom = qtDtNgayTdDtFrom;
    }
	
	public java.util.Date getQtDtNgayTdDtTo() {
    	return qtDtNgayTdDtTo;
    }
	
    public void setQtDtNgayTdDtTo(java.util.Date qtDtNgayTdDtTo) {
    	this.qtDtNgayTdDtTo = qtDtNgayTdDtTo;
    }
	
	@JsonProperty("qtDtTong")
    public java.lang.Float getQtDtTong(){
		return qtDtTong;
    }
	
    public void setQtDtTong(java.lang.Float qtDtTong){
		this.qtDtTong = qtDtTong;
    }	
	
	@JsonProperty("qtDtXd")
    public java.lang.Float getQtDtXd(){
		return qtDtXd;
    }
	
    public void setQtDtXd(java.lang.Float qtDtXd){
		this.qtDtXd = qtDtXd;
    }	
	
	@JsonProperty("qtDtDien")
    public java.lang.Float getQtDtDien(){
		return qtDtDien;
    }
	
    public void setQtDtDien(java.lang.Float qtDtDien){
		this.qtDtDien = qtDtDien;
    }	
	
	@JsonProperty("qtDtLapDung")
    public java.lang.Float getQtDtLapDung(){
		return qtDtLapDung;
    }
	
    public void setQtDtLapDung(java.lang.Float qtDtLapDung){
		this.qtDtLapDung = qtDtLapDung;
    }	
	
	@JsonProperty("qtDtLapBts")
    public java.lang.Float getQtDtLapBts(){
		return qtDtLapBts;
    }
	
    public void setQtDtLapBts(java.lang.Float qtDtLapBts){
		this.qtDtLapBts = qtDtLapBts;
    }	
	
	@JsonProperty("qtDtKhac")
    public java.lang.Float getQtDtKhac(){
		return qtDtKhac;
    }
	
    public void setQtDtKhac(java.lang.Float qtDtKhac){
		this.qtDtKhac = qtDtKhac;
    }	
	
	@JsonProperty("qtDtTtDt")
    public java.lang.String getQtDtTtDt(){
		return qtDtTtDt;
    }
	
    public void setQtDtTtDt(java.lang.String qtDtTtDt){
		this.qtDtTtDt = qtDtTtDt;
    }	
	
	@JsonProperty("qtDtNguoiTd")
    public java.lang.String getQtDtNguoiTd(){
		return qtDtNguoiTd;
    }
	
    public void setQtDtNguoiTd(java.lang.String qtDtNguoiTd){
		this.qtDtNguoiTd = qtDtNguoiTd;
    }	
	
	@JsonProperty("dnQtCdtNgay")
    public java.util.Date getDnQtCdtNgay(){
		return dnQtCdtNgay;
    }
	
    public void setDnQtCdtNgay(java.util.Date dnQtCdtNgay){
		this.dnQtCdtNgay = dnQtCdtNgay;
    }	
	
	public java.util.Date getDnQtCdtNgayFrom() {
    	return dnQtCdtNgayFrom;
    }
	
    public void setDnQtCdtNgayFrom(java.util.Date dnQtCdtNgayFrom) {
    	this.dnQtCdtNgayFrom = dnQtCdtNgayFrom;
    }
	
	public java.util.Date getDnQtCdtNgayTo() {
    	return dnQtCdtNgayTo;
    }
	
    public void setDnQtCdtNgayTo(java.util.Date dnQtCdtNgayTo) {
    	this.dnQtCdtNgayTo = dnQtCdtNgayTo;
    }
	
	@JsonProperty("dnQtCdtGt")
    public java.lang.Float getDnQtCdtGt(){
		return dnQtCdtGt;
    }
	
    public void setDnQtCdtGt(java.lang.Float dnQtCdtGt){
		this.dnQtCdtGt = dnQtCdtGt;
    }	
	
	@JsonProperty("dnQtCdtNguoiLap")
    public java.lang.String getDnQtCdtNguoiLap(){
		return dnQtCdtNguoiLap;
    }
	
    public void setDnQtCdtNguoiLap(java.lang.String dnQtCdtNguoiLap){
		this.dnQtCdtNguoiLap = dnQtCdtNguoiLap;
    }	
	
	@JsonProperty("dnQtCdtNguoiNhanBg")
    public java.lang.String getDnQtCdtNguoiNhanBg(){
		return dnQtCdtNguoiNhanBg;
    }
	
    public void setDnQtCdtNguoiNhanBg(java.lang.String dnQtCdtNguoiNhanBg){
		this.dnQtCdtNguoiNhanBg = dnQtCdtNguoiNhanBg;
    }	
	
	@JsonProperty("dnQtCdtNoiDungPsCtk")
    public java.lang.String getDnQtCdtNoiDungPsCtk(){
		return dnQtCdtNoiDungPsCtk;
    }
	
    public void setDnQtCdtNoiDungPsCtk(java.lang.String dnQtCdtNoiDungPsCtk){
		this.dnQtCdtNoiDungPsCtk = dnQtCdtNoiDungPsCtk;
    }	
	
	@JsonProperty("dnQtCdtKtk")
    public java.lang.String getDnQtCdtKtk(){
		return dnQtCdtKtk;
    }
	
    public void setDnQtCdtKtk(java.lang.String dnQtCdtKtk){
		this.dnQtCdtKtk = dnQtCdtKtk;
    }	
	
	@JsonProperty("tdQtCdtNgayChot")
    public java.util.Date getTdQtCdtNgayChot(){
		return tdQtCdtNgayChot;
    }
	
    public void setTdQtCdtNgayChot(java.util.Date tdQtCdtNgayChot){
		this.tdQtCdtNgayChot = tdQtCdtNgayChot;
    }	
	
	public java.util.Date getTdQtCdtNgayChotFrom() {
    	return tdQtCdtNgayChotFrom;
    }
	
    public void setTdQtCdtNgayChotFrom(java.util.Date tdQtCdtNgayChotFrom) {
    	this.tdQtCdtNgayChotFrom = tdQtCdtNgayChotFrom;
    }
	
	public java.util.Date getTdQtCdtNgayChotTo() {
    	return tdQtCdtNgayChotTo;
    }
	
    public void setTdQtCdtNgayChotTo(java.util.Date tdQtCdtNgayChotTo) {
    	this.tdQtCdtNgayChotTo = tdQtCdtNgayChotTo;
    }
	
	@JsonProperty("tdQtCdtGt")
    public java.lang.Float getTdQtCdtGt(){
		return tdQtCdtGt;
    }
	
    public void setTdQtCdtGt(java.lang.Float tdQtCdtGt){
		this.tdQtCdtGt = tdQtCdtGt;
    }	
	
	@JsonProperty("tdQtCdtNguoiChotMvt")
    public java.lang.String getTdQtCdtNguoiChotMvt(){
		return tdQtCdtNguoiChotMvt;
    }
	
    public void setTdQtCdtNguoiChotMvt(java.lang.String tdQtCdtNguoiChotMvt){
		this.tdQtCdtNguoiChotMvt = tdQtCdtNguoiChotMvt;
    }	
	
	@JsonProperty("tdQtCdtNguoiTdCdtMvt")
    public java.lang.String getTdQtCdtNguoiTdCdtMvt(){
		return tdQtCdtNguoiTdCdtMvt;
    }
	
    public void setTdQtCdtNguoiTdCdtMvt(java.lang.String tdQtCdtNguoiTdCdtMvt){
		this.tdQtCdtNguoiTdCdtMvt = tdQtCdtNguoiTdCdtMvt;
    }	
	
	@JsonProperty("tdQtCdtNgayBanTd")
    public java.util.Date getTdQtCdtNgayBanTd(){
		return tdQtCdtNgayBanTd;
    }
	
    public void setTdQtCdtNgayBanTd(java.util.Date tdQtCdtNgayBanTd){
		this.tdQtCdtNgayBanTd = tdQtCdtNgayBanTd;
    }	
	
	public java.util.Date getTdQtCdtNgayBanTdFrom() {
    	return tdQtCdtNgayBanTdFrom;
    }
	
    public void setTdQtCdtNgayBanTdFrom(java.util.Date tdQtCdtNgayBanTdFrom) {
    	this.tdQtCdtNgayBanTdFrom = tdQtCdtNgayBanTdFrom;
    }
	
	public java.util.Date getTdQtCdtNgayBanTdTo() {
    	return tdQtCdtNgayBanTdTo;
    }
	
    public void setTdQtCdtNgayBanTdTo(java.util.Date tdQtCdtNgayBanTdTo) {
    	this.tdQtCdtNgayBanTdTo = tdQtCdtNgayBanTdTo;
    }
	
	@JsonProperty("tdQtCdtNgayCtc")
    public java.util.Date getTdQtCdtNgayCtc(){
		return tdQtCdtNgayCtc;
    }
	
    public void setTdQtCdtNgayCtc(java.util.Date tdQtCdtNgayCtc){
		this.tdQtCdtNgayCtc = tdQtCdtNgayCtc;
    }	
	
	public java.util.Date getTdQtCdtNgayCtcFrom() {
    	return tdQtCdtNgayCtcFrom;
    }
	
    public void setTdQtCdtNgayCtcFrom(java.util.Date tdQtCdtNgayCtcFrom) {
    	this.tdQtCdtNgayCtcFrom = tdQtCdtNgayCtcFrom;
    }
	
	public java.util.Date getTdQtCdtNgayCtcTo() {
    	return tdQtCdtNgayCtcTo;
    }
	
    public void setTdQtCdtNgayCtcTo(java.util.Date tdQtCdtNgayCtcTo) {
    	this.tdQtCdtNgayCtcTo = tdQtCdtNgayCtcTo;
    }
	
	@JsonProperty("tdQtCdtNoiDung")
    public java.lang.String getTdQtCdtNoiDung(){
		return tdQtCdtNoiDung;
    }
	
    public void setTdQtCdtNoiDung(java.lang.String tdQtCdtNoiDung){
		this.tdQtCdtNoiDung = tdQtCdtNoiDung;
    }	
	
	@JsonProperty("tinhTrangCct2")
    public java.lang.String getTinhTrangCct2(){
		return tinhTrangCct2;
    }
	
    public void setTinhTrangCct2(java.lang.String tinhTrangCct2){
		this.tinhTrangCct2 = tinhTrangCct2;
    }	
	
	@JsonProperty("ghiChu2")
    public java.lang.String getGhiChu2(){
		return ghiChu2;
    }
	
    public void setGhiChu2(java.lang.String ghiChu2){
		this.ghiChu2 = ghiChu2;
    }	
	
	@JsonProperty("luuTaiKho")
    public java.lang.String getLuuTaiKho(){
		return luuTaiKho;
    }
	
    public void setLuuTaiKho(java.lang.String luuTaiKho){
		this.luuTaiKho = luuTaiKho;
    }
    
    @JsonProperty("trangThai")
    public java.lang.String getTrangThai(){
		return trangThai;
    }
	public void setTrangThai(java.lang.String trangThai) {
		this.trangThai = trangThai;
		
	}
	 @JsonProperty("pathFile")
	 public java.lang.String getPathFile() {
			return pathFile;
		}

		public void setPathFile(java.lang.String pathFile) {
			this.pathFile = pathFile;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((mttMaViTri == null) ? 0 : mttMaViTri.hashCode());
			result = prime * result + ((soHdCdt == null) ? 0 : soHdCdt.hashCode());
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
			TblQlCvPtkDTO other = (TblQlCvPtkDTO) obj;
			if (mttMaViTri == null) {
				if (other.mttMaViTri != null)
					return false;
			} else if (!mttMaViTri.equals(other.mttMaViTri))
				return false;
			if (soHdCdt == null) {
				if (other.soHdCdt != null)
					return false;
			} else if (!soHdCdt.equals(other.soHdCdt))
				return false;
			return true;
		}
		
		
		
		

}
