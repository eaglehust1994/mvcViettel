package com.viettel.ims.bo;

import com.viettel.ims.dto.SysGroupDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.SysGroupBO")
@Table(name = "SYS_GROUP")
/**
 *
 * @author: hailh10
 */
public class SysGroupBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SYS_GROUP_SEQ") })
	@Column(name = "SYS_GROUP_ID", length = 22)
	private java.lang.Long sysGroupId;
	@Column(name = "CODE", length = 100)
	private java.lang.String code;
	@Column(name = "NAME", length = 100)
	private java.lang.String name;
	@Column(name = "PARENT_ID", length = 22)
	private java.lang.Long parentId;
	@Column(name = "STATUS", length = 20)
	private java.lang.String status;
	@Column(name = "PATH", length = 1000)
	private java.lang.String path;
	@Column(name = "EFFECT_DATE", length = 7)
	private java.util.Date effectDate;
	@Column(name = "END_DATE", length = 7)
	private java.util.Date endDate;

	
	public java.lang.Long getSysGroupId(){
		return sysGroupId;
	}
	
	public void setSysGroupId(java.lang.Long sysGroupId)
	{
		this.sysGroupId = sysGroupId;
	}
	
	public java.lang.String getCode(){
		return code;
	}
	
	public void setCode(java.lang.String code)
	{
		this.code = code;
	}
	
	public java.lang.String getName(){
		return name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	public java.lang.Long getParentId(){
		return parentId;
	}
	
	public void setParentId(java.lang.Long parentId)
	{
		this.parentId = parentId;
	}
	
	public java.lang.String getStatus(){
		return status;
	}
	
	public void setStatus(java.lang.String status)
	{
		this.status = status;
	}
	
	public java.lang.String getPath(){
		return path;
	}
	
	public void setPath(java.lang.String path)
	{
		this.path = path;
	}
	
	public java.util.Date getEffectDate(){
		return effectDate;
	}
	
	public void setEffectDate(java.util.Date effectDate)
	{
		this.effectDate = effectDate;
	}
	
	public java.util.Date getEndDate(){
		return endDate;
	}
	
	public void setEndDate(java.util.Date endDate)
	{
		this.endDate = endDate;
	}
   
    @Override
    public SysGroupDTO toDTO() {
        SysGroupDTO sysGroupDTO = new SysGroupDTO(); 
        sysGroupDTO.setSysGroupId(this.sysGroupId);		
        sysGroupDTO.setCode(this.code);		
        sysGroupDTO.setName(this.name);		
        sysGroupDTO.setParentId(this.parentId);		
        sysGroupDTO.setStatus(this.status);		
        sysGroupDTO.setPath(this.path);		
        sysGroupDTO.setEffectDate(this.effectDate);		
        sysGroupDTO.setEndDate(this.endDate);		
        return sysGroupDTO;
    }
}
