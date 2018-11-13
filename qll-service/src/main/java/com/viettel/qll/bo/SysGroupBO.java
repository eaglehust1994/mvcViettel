package com.viettel.qll.bo;

import com.viettel.qll.dto.SysGroupDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.viettel.service.base.model.BaseFWModelImpl;
@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.SysGroupBO")
@Table(name = "SYS_GROUP")
/**
 *
 * @author: hailh10
 */
public class SysGroupBO extends BaseFWModelImpl {
     

	@Column(name = "GROUP_NAME_LEVEL2", length = 1000)
	private java.lang.String groupNameLevel2;
	@Column(name = "GROUP_NAME_LEVEL3", length = 1000)
	private java.lang.String groupNameLevel3;
	public java.lang.String getGroupNameLevel3() {
		return groupNameLevel3;
	}

	public void setGroupNameLevel3(java.lang.String groupNameLevel3) {
		this.groupNameLevel3 = groupNameLevel3;
	}

	@Column(name = "GROUP_LEVEL", length = 20)
	private java.lang.String groupLevel;
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SYS_GROUP_SEQ") })
	@Column(name = "SYS_GROUP_ID", length = 22)
	private java.lang.Long derpartmentId;
	
	@Column(name = "NAME", length = 400)
	private java.lang.String derpartmentName;
	
	@Column(name = "PATH", length = 400)
	private java.lang.String path;
	public java.lang.String getPath() {
		return path;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
	}

	@Column(name = "STATUS", length = 80)
	private java.lang.String status;

	

	
	public java.lang.String getGroupNameLevel2(){
		return groupNameLevel2;
	}
	
	public void setGroupNameLevel2(java.lang.String groupNameLevel2)
	{
		this.groupNameLevel2 = groupNameLevel2;
	}
	

	

	public java.lang.String getGroupLevel(){
		return groupLevel;
	}
	
	public void setGroupLevel(java.lang.String groupLevel)
	{
		this.groupLevel = groupLevel;
	}
	
	
   
    public java.lang.Long getDerpartmentId() {
		return derpartmentId;
	}

	public void setDerpartmentId(java.lang.Long derpartmentId) {
		this.derpartmentId = derpartmentId;
	}

	public java.lang.String getDerpartmentName() {
		return derpartmentName;
	}

	public void setDerpartmentName(java.lang.String derpartmentName) {
		this.derpartmentName = derpartmentName;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	@Override
    public SysGroupDTO toDTO() {
        SysGroupDTO sysGroupDTO = new SysGroupDTO(); 
     		
        sysGroupDTO.setGroupNameLevel2(this.groupNameLevel2);		
       	
        sysGroupDTO.setGroupNameLevel3(this.groupNameLevel3);
        sysGroupDTO.setGroupLevel(this.groupLevel);
        sysGroupDTO.setPath(this.path);
        sysGroupDTO.setDerpartmentId(this.derpartmentId);		
     		
        sysGroupDTO.setDerpartmentName(this.derpartmentName);		
     	
        sysGroupDTO.setStatus(this.status);		
        return sysGroupDTO;
    }
}
