package com.viettel.qll.bo;

import com.viettel.qll.dto.TblPhatFcDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblPhatFcBO")
@Table(name = "TBL_PHAT_FC")
/**
 *
 * @author: hailh10
 */
public class TblPhatFcBO extends BaseFWModelImpl {
     
	@Column(name = "XOA", length = 22)
	private java.lang.Long xoa;
	@Column(name = "STPTTAG", length = 22)
	private java.lang.Float stpttag;
	@Column(name = "SFCPT", length = 22)
	private java.lang.Float sfcpt;
	@Column(name = "GTTVBDPD", length = 22)
	private java.lang.Float gttvbdpd;
	@Column(name = "SLFCGTDDBH", length = 22)
	private java.lang.Float slfcgtddbh;
	@Column(name = "TSLFCLT", length = 22)
	private java.lang.Float tslfclt;
	@Column(name = "SLFCPTMVHMT", length = 22)
	private java.lang.Float slfcptmvhmt;
	@Column(name = "SLFCDSDTTCPTMT", length = 22)
	private java.lang.Float slfcdsdttcptmt;
	@Column(name = "TBGPTT", length = 22)
	private java.lang.Float tbgptt;
	@Column(name = "TSFCCPLT", length = 22)
	private java.lang.Float tsfccplt;
	@Column(name = "TSDDQL", length = 22)
	private java.lang.Float tsddql;
	@Column(name = "MA_TINH", length = 300)
	private java.lang.String maTinh;
	@Column(name = "TINH", length = 600)
	private java.lang.String tinh;
	@Column(name = "QUAN_HUYEN", length = 600)
	private java.lang.String quanHuyen;
	@Column(name = "HO_VA_TEN", length = 600)
	private java.lang.String hoVaTen;
	@Column(name = "MA_NV", length = 300)
	private java.lang.String maNv;
	@Column(name = "THANG", length = 300)
	private java.lang.String thang;
	@Column(name = "HOAT_DONG", length = 22)
	private java.lang.Long hoatDong;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "PHAT_FC_SEQ") })
	@Column(name = "TBL_PHAT_FC_ID", length = 22)
	private java.lang.Long tblPhatFcId;
	@Column(name = "SLFCTTXLSCT", length = 22)
	private java.lang.Float slfcttxlsct;

	
	public java.lang.Long getXoa(){
		return xoa;
	}
	
	public void setXoa(java.lang.Long xoa)
	{
		this.xoa = xoa;
	}
	
	public java.lang.Float getStpttag(){
		return stpttag;
	}
	
	public void setStpttag(java.lang.Float stpttag)
	{
		this.stpttag = stpttag;
	}
	
	public java.lang.Float getSfcpt(){
		return sfcpt;
	}
	
	public void setSfcpt(java.lang.Float sfcpt)
	{
		this.sfcpt = sfcpt;
	}
	
	public java.lang.Float getGttvbdpd(){
		return gttvbdpd;
	}
	
	public void setGttvbdpd(java.lang.Float gttvbdpd)
	{
		this.gttvbdpd = gttvbdpd;
	}
	
	public java.lang.Float getSlfcgtddbh(){
		return slfcgtddbh;
	}
	
	public void setSlfcgtddbh(java.lang.Float slfcgtddbh)
	{
		this.slfcgtddbh = slfcgtddbh;
	}
	
	public java.lang.Float getTslfclt(){
		return tslfclt;
	}
	
	public void setTslfclt(java.lang.Float tslfclt)
	{
		this.tslfclt = tslfclt;
	}
	
	public java.lang.Float getSlfcptmvhmt(){
		return slfcptmvhmt;
	}
	
	public void setSlfcptmvhmt(java.lang.Float slfcptmvhmt)
	{
		this.slfcptmvhmt = slfcptmvhmt;
	}
	
	public java.lang.Float getSlfcdsdttcptmt(){
		return slfcdsdttcptmt;
	}
	
	public void setSlfcdsdttcptmt(java.lang.Float slfcdsdttcptmt)
	{
		this.slfcdsdttcptmt = slfcdsdttcptmt;
	}
	
	public java.lang.Float getTbgptt(){
		return tbgptt;
	}
	
	public void setTbgptt(java.lang.Float tbgptt)
	{
		this.tbgptt = tbgptt;
	}
	
	public java.lang.Float getTsfccplt(){
		return tsfccplt;
	}
	
	public void setTsfccplt(java.lang.Float tsfccplt)
	{
		this.tsfccplt = tsfccplt;
	}
	
	public java.lang.Float getTsddql(){
		return tsddql;
	}
	
	public void setTsddql(java.lang.Float tsddql)
	{
		this.tsddql = tsddql;
	}
	
	public java.lang.String getMaTinh(){
		return maTinh;
	}
	
	public void setMaTinh(java.lang.String maTinh)
	{
		this.maTinh = maTinh;
	}
	
	public java.lang.String getTinh(){
		return tinh;
	}
	
	public void setTinh(java.lang.String tinh)
	{
		this.tinh = tinh;
	}
	
	public java.lang.String getQuanHuyen(){
		return quanHuyen;
	}
	
	public void setQuanHuyen(java.lang.String quanHuyen)
	{
		this.quanHuyen = quanHuyen;
	}
	
	public java.lang.String getHoVaTen(){
		return hoVaTen;
	}
	
	public void setHoVaTen(java.lang.String hoVaTen)
	{
		this.hoVaTen = hoVaTen;
	}
	
	public java.lang.String getMaNv(){
		return maNv;
	}
	
	public void setMaNv(java.lang.String maNv)
	{
		this.maNv = maNv;
	}
	
	public java.lang.String getThang(){
		return thang;
	}
	
	public void setThang(java.lang.String thang)
	{
		this.thang = thang;
	}
	
	public java.lang.Long getHoatDong(){
		return hoatDong;
	}
	
	public void setHoatDong(java.lang.Long hoatDong)
	{
		this.hoatDong = hoatDong;
	}
	
	public java.lang.Long getTblPhatFcId(){
		return tblPhatFcId;
	}
	
	public void setTblPhatFcId(java.lang.Long tblPhatFcId)
	{
		this.tblPhatFcId = tblPhatFcId;
	}
	
	public java.lang.Float getSlfcttxlsct(){
		return slfcttxlsct;
	}
	
	public void setSlfcttxlsct(java.lang.Float slfcttxlsct)
	{
		this.slfcttxlsct = slfcttxlsct;
	}
   
    @Override
    public TblPhatFcDTO toDTO() {
        TblPhatFcDTO tblPhatFcDTO = new TblPhatFcDTO(); 
        tblPhatFcDTO.setXoa(this.xoa);		
        tblPhatFcDTO.setStpttag(this.stpttag);		
        tblPhatFcDTO.setSfcpt(this.sfcpt);		
        tblPhatFcDTO.setGttvbdpd(this.gttvbdpd);		
        tblPhatFcDTO.setSlfcgtddbh(this.slfcgtddbh);		
        tblPhatFcDTO.setTslfclt(this.tslfclt);		
        tblPhatFcDTO.setSlfcptmvhmt(this.slfcptmvhmt);		
        tblPhatFcDTO.setSlfcdsdttcptmt(this.slfcdsdttcptmt);		
        tblPhatFcDTO.setTbgptt(this.tbgptt);		
        tblPhatFcDTO.setTsfccplt(this.tsfccplt);		
        tblPhatFcDTO.setTsddql(this.tsddql);		
        tblPhatFcDTO.setMaTinh(this.maTinh);		
        tblPhatFcDTO.setTinh(this.tinh);		
        tblPhatFcDTO.setQuanHuyen(this.quanHuyen);		
        tblPhatFcDTO.setHoVaTen(this.hoVaTen);		
        tblPhatFcDTO.setMaNv(this.maNv);		
        tblPhatFcDTO.setThang(this.thang);		
        tblPhatFcDTO.setHoatDong(this.hoatDong);		
        tblPhatFcDTO.setTblPhatFcId(this.tblPhatFcId);		
        tblPhatFcDTO.setSlfcttxlsct(this.slfcttxlsct);		
        return tblPhatFcDTO;
    }
}
