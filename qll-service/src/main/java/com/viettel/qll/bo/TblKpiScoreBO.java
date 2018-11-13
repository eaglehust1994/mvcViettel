package com.viettel.qll.bo;

import com.viettel.qll.dto.TblKpiScoreDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TblKpiScoreBO")
@Table(name = "TBL_KPI_SCORE")
/**
 *
 * @author: hailh10
 */
public class TblKpiScoreBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_KPI_SCORE_SEQ") })
	@Column(name = "KPIID", length = 22)
	private java.lang.Long kpiid;
	@Column(name = "DEPARTMENTID", length = 22)
	private java.lang.Long departmentid;
	@Column(name = "DEPARTMENTNAME", length = 3000)
	private java.lang.String departmentname;
	@Column(name = "SCOREBONUS", length = 22)
	private java.lang.Float scorebonus;
	@Column(name = "SCOREPENALTY", length = 22)
	private java.lang.Float scorepenalty;
	@Column(name = "REASON", length = 4000)
	private java.lang.String reason;
	@Column(name = "USERIDCREATED", length = 22)
	private java.lang.Long useridcreated;
	@Column(name = "USERNAMECREATED", length = 3000)
	private java.lang.String usernamecreated;
	@Column(name = "FULLNAMECREATED", length = 3000)
	private java.lang.String fullnamecreated;
	@Column(name = "DEPARTMENTIDCREATED", length = 22)
	private java.lang.Long departmentidcreated;
	@Column(name = "DEPARTMENTNAMECREATED", length = 3000)
	private java.lang.String departmentnamecreated;
	@Column(name = "CREATEDDATE", length = 7)
	private java.util.Date createddate;
	@Column(name = "MODIFIEDDATE", length = 7)
	private java.util.Date modifieddate;
	@Column(name = "SCOREBONUS_CONFIRM", length = 22)
	private java.lang.Float scorebonusConfirm;
	@Column(name = "SCOREPENALTY_CONFIRM", length = 22)
	private java.lang.Float scorepenaltyConfirm;
	@Column(name = "USERID_MODIFIED", length = 22)
	private java.lang.Long useridModified;
	@Column(name = "USERNAME_MODIFIED", length = 3000)
	private java.lang.String usernameModified;
	@Column(name = "FULLNAME_MODIFIED", length = 3000)
	private java.lang.String fullnameModified;
	@Column(name = "DEPARTMENTID_MODIFIED", length = 22)
	private java.lang.Long departmentidModified;
	@Column(name = "DEPARTMENTNAME_MODIFIED", length = 3000)
	private java.lang.String departmentnameModified;
	@Column(name = "REASON_CONFIRM", length=4000)
	private java.lang.String reasonConfirm;
	public java.lang.String getReasonConfirm() {
		return reasonConfirm;
	}

	public void setReasonConfirm(java.lang.String reasonConfirm) {
		this.reasonConfirm = reasonConfirm;
	}

	public java.lang.Long getUseridModified() {
		return useridModified;
	}

	public void setUseridModified(java.lang.Long useridModified) {
		this.useridModified = useridModified;
	}

	public java.lang.String getUsernameModified() {
		return usernameModified;
	}

	public void setUsernameModified(java.lang.String usernameModified) {
		this.usernameModified = usernameModified;
	}

	public java.lang.String getFullnameModified() {
		return fullnameModified;
	}

	public void setFullnameModified(java.lang.String fullnameModified) {
		this.fullnameModified = fullnameModified;
	}

	public java.lang.Float getScorebonusConfirm() {
		return scorebonusConfirm;
	}

	public void setScorebonusConfirm(java.lang.Float scorebonusConfirm) {
		this.scorebonusConfirm = scorebonusConfirm;
	}

	public java.lang.Float getScorepenaltyConfirm() {
		return scorepenaltyConfirm;
	}

	public void setScorepenaltyConfirm(java.lang.Float scorepenaltyConfirm) {
		this.scorepenaltyConfirm = scorepenaltyConfirm;
	}
	
	public java.lang.Long getDepartmentidModified() {
		return departmentidModified;
	}

	public void setDepartmentidModified(java.lang.Long departmentidModified) {
		this.departmentidModified = departmentidModified;
	}

	public java.lang.String getDepartmentnameModified() {
		return departmentnameModified;
	}

	public void setDepartmentnameModified(java.lang.String departmentnameModified) {
		this.departmentnameModified = departmentnameModified;
	}

	public java.lang.Long getKpiid(){
		return kpiid;
	}
	
	public void setKpiid(java.lang.Long kpiid)
	{
		this.kpiid = kpiid;
	}
	
	public java.lang.Long getDepartmentid(){
		return departmentid;
	}
	
	public void setDepartmentid(java.lang.Long departmentid)
	{
		this.departmentid = departmentid;
	}
	
	public java.lang.String getDepartmentname(){
		return departmentname;
	}
	
	public void setDepartmentname(java.lang.String departmentname)
	{
		this.departmentname = departmentname;
	}
	
	public java.lang.Float getScorebonus(){
		return scorebonus;
	}
	
	public void setScorebonus(java.lang.Float scorebonus)
	{
		this.scorebonus = scorebonus;
	}
	
	public java.lang.Float getScorepenalty(){
		return scorepenalty;
	}
	
	public void setScorepenalty(java.lang.Float scorepenalty)
	{
		this.scorepenalty = scorepenalty;
	}
	
	public java.lang.String getReason(){
		return reason;
	}
	
	public void setReason(java.lang.String reason)
	{
		this.reason = reason;
	}
	
	public java.lang.Long getUseridcreated(){
		return useridcreated;
	}
	
	public void setUseridcreated(java.lang.Long useridcreated)
	{
		this.useridcreated = useridcreated;
	}
	
	public java.lang.String getUsernamecreated(){
		return usernamecreated;
	}
	
	public void setUsernamecreated(java.lang.String usernamecreated)
	{
		this.usernamecreated = usernamecreated;
	}
	
	public java.lang.String getFullnamecreated(){
		return fullnamecreated;
	}
	
	public void setFullnamecreated(java.lang.String fullnamecreated)
	{
		this.fullnamecreated = fullnamecreated;
	}
	
	public java.lang.Long getDepartmentidcreated(){
		return departmentidcreated;
	}
	
	public void setDepartmentidcreated(java.lang.Long departmentidcreated)
	{
		this.departmentidcreated = departmentidcreated;
	}
	
	public java.lang.String getDepartmentnamecreated(){
		return departmentnamecreated;
	}
	
	public void setDepartmentnamecreated(java.lang.String departmentnamecreated)
	{
		this.departmentnamecreated = departmentnamecreated;
	}
	
	public java.util.Date getCreateddate(){
		return createddate;
	}
	
	public void setCreateddate(java.util.Date createddate)
	{
		this.createddate = createddate;
	}
	
	public java.util.Date getModifieddate(){
		return modifieddate;
	}
	
	public void setModifieddate(java.util.Date modifieddate)
	{
		this.modifieddate = modifieddate;
	}
   
    @Override
    public TblKpiScoreDTO toDTO() {
        TblKpiScoreDTO tblKpiScoreDTO = new TblKpiScoreDTO(); 
        tblKpiScoreDTO.setKpiid(this.kpiid);		
        tblKpiScoreDTO.setDepartmentid(this.departmentid);		
        tblKpiScoreDTO.setDepartmentname(this.departmentname);		
        tblKpiScoreDTO.setScorebonus(this.scorebonus);		
        tblKpiScoreDTO.setScorepenalty(this.scorepenalty);		
        tblKpiScoreDTO.setReason(this.reason);		
        tblKpiScoreDTO.setUseridcreated(this.useridcreated);		
        tblKpiScoreDTO.setUsernamecreated(this.usernamecreated);		
        tblKpiScoreDTO.setFullnamecreated(this.fullnamecreated);		
        tblKpiScoreDTO.setDepartmentidcreated(this.departmentidcreated);		
        tblKpiScoreDTO.setDepartmentnamecreated(this.departmentnamecreated);		
        tblKpiScoreDTO.setCreateddate(this.createddate);		
        tblKpiScoreDTO.setModifieddate(this.modifieddate);
        tblKpiScoreDTO.setScorebonusConfirm(this.scorebonusConfirm);		
        tblKpiScoreDTO.setScorepenaltyConfirm(this.scorepenaltyConfirm);
        tblKpiScoreDTO.setUseridModified(this.useridModified);		
        tblKpiScoreDTO.setUsernameModified(this.usernameModified);		
        tblKpiScoreDTO.setFullnameModified(this.fullnameModified);
        tblKpiScoreDTO.setDepartmentidModified(this.departmentidModified);		
        tblKpiScoreDTO.setDepartmentnameModified(this.departmentnameModified);
        tblKpiScoreDTO.setReasonConfirm(this.reasonConfirm);
        return tblKpiScoreDTO;
    }
}
