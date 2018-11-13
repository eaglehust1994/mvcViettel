package com.viettel.qll.bo;

import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TblGanNhiemVuBO")
@Table(name = "TBL_GAN_NHIEM_VU")
/**
 *
 * @author: hailh10
 */
public class TblGanNhiemVuBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_GAN_NHIEM_VU_SEQ") })
	@Column(name = "GAN_NV_ID", length = 22)
	private java.lang.Long ganNvId;
	@Column(name = "ID_USER", length = 500)
	private java.lang.String idUser;
	@Column(name = "FULLNAME", length = 500)
	private java.lang.String fullname;
	@Column(name = "ID_QL_CV_PTK", length = 22)
	private java.lang.Long idQlCvPtk;
	@Column(name = "TEN_NHIEM_VU", length = 2000)
	private java.lang.String tenNhiemVu;
	@Column(name = "NGAY_GIAO_NV", length = 7)
	private java.util.Date ngayGiaoNv;
	@Column(name = "NGAY_HOAN_THANH", length = 7)
	private java.util.Date ngayHoanThanh;
	@Column(name = "TRANG_THAI", length = 20)
	private java.lang.String trangThai;
	@Column(name = "TEN_CONG_VIEC ", length =2000)
	private java.lang.String tenCongViec;
	
	public java.lang.Long getGanNvId(){
		return ganNvId;
	}
	
	public java.lang.String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(java.lang.String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	public void setGanNvId(java.lang.Long ganNvId)
	{
		this.ganNvId = ganNvId;
	}
	
	public java.lang.String getIdUser(){
		return idUser;
	}
	
	public void setIdUser(java.lang.String idUser)
	{
		this.idUser = idUser;
	}
	
	public java.lang.String getFullname(){
		return fullname;
	}
	
	public void setFullname(java.lang.String fullname)
	{
		this.fullname = fullname;
	}
	
	public java.lang.Long getIdQlCvPtk(){
		return idQlCvPtk;
	}
	
	public void setIdQlCvPtk(java.lang.Long idQlCvPtk)
	{
		this.idQlCvPtk = idQlCvPtk;
	}
	
	public java.lang.String getTenNhiemVu(){
		return tenNhiemVu;
	}
	
	public void setTenNhiemVu(java.lang.String tenNhiemVu)
	{
		this.tenNhiemVu = tenNhiemVu;
	}
	
	public java.util.Date getNgayGiaoNv(){
		return ngayGiaoNv;
	}
	
	public void setNgayGiaoNv(java.util.Date ngayGiaoNv)
	{
		this.ngayGiaoNv = ngayGiaoNv;
	}
	
	public java.util.Date getNgayHoanThanh(){
		return ngayHoanThanh;
	}
	
	public void setNgayHoanThanh(java.util.Date ngayHoanThanh)
	{
		this.ngayHoanThanh = ngayHoanThanh;
	}
	
	public java.lang.String getTrangThai(){
		return trangThai;
	}
	
	public void setTrangThai(java.lang.String trangThai)
	{
		this.trangThai = trangThai;
	}
   
    @Override
    public TblGanNhiemVuDTO toDTO() {
        TblGanNhiemVuDTO tblGanNhiemVuDTO = new TblGanNhiemVuDTO(); 
        tblGanNhiemVuDTO.setGanNvId(this.ganNvId);		
        tblGanNhiemVuDTO.setIdUser(this.idUser);		
        tblGanNhiemVuDTO.setFullname(this.fullname);		
        tblGanNhiemVuDTO.setIdQlCvPtk(this.idQlCvPtk);		
        tblGanNhiemVuDTO.setTenNhiemVu(this.tenNhiemVu);		
        tblGanNhiemVuDTO.setNgayGiaoNv(this.ngayGiaoNv);		
        tblGanNhiemVuDTO.setNgayHoanThanh(this.ngayHoanThanh);		
        tblGanNhiemVuDTO.setTrangThai(this.trangThai);	
        tblGanNhiemVuDTO.setTenCongViec(this.tenCongViec);
        return tblGanNhiemVuDTO;
    }
}
