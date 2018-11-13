package com.viettel.qll.dto;

import com.viettel.qll.bo.SysGroupBO;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "SYS_GROUPBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysGroupDTO extends QllBaseDTO<SysGroupBO> {

	
	private java.lang.String groupNameLevel2;
	private java.lang.String groupNameLevel3;
	private java.lang.String name;
	


	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getGroupNameLevel3() {
		return groupNameLevel3;
	}

	public void setGroupNameLevel3(java.lang.String groupNameLevel3) {
		this.groupNameLevel3 = groupNameLevel3;
	}

	private java.lang.String groupLevel;
	private java.lang.Long derpartmentId;
	private java.lang.String derpartmentName;
	private java.lang.String path;
	public java.lang.String getPath() {
		return path;
	}

	public void setPath(java.lang.String path) {
		this.path = path;
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

	private java.lang.String status;

	 private List<SysGroupDTO> listData;
	 
    public List<SysGroupDTO> getListData() {
		return listData;
	}

	public void setListData(List<SysGroupDTO> listData) {
		this.listData = listData;
	}

	@Override
    public SysGroupBO toModel() {
        SysGroupBO sysGroupBO = new SysGroupBO();
        sysGroupBO.setPath(this.path); 
        sysGroupBO.setGroupNameLevel2(this.groupNameLevel2); 
        sysGroupBO.setGroupNameLevel3(this.groupNameLevel3);  
        sysGroupBO.setGroupLevel(this.groupLevel);
        sysGroupBO.setDerpartmentId(this.derpartmentId);     
        sysGroupBO.setDerpartmentName(this.derpartmentName);
        sysGroupBO.setStatus(this.status);
        return sysGroupBO;
    }

	
	
	@JsonProperty("groupNameLevel2")
    public java.lang.String getGroupNameLevel2(){
		return groupNameLevel2;
    }
	
    public void setGroupNameLevel2(java.lang.String groupNameLevel2){
		this.groupNameLevel2 = groupNameLevel2;
    }	
	




	
	@JsonProperty("groupLevel")
    public java.lang.String getGroupLevel(){
		return groupLevel;
    }
	
    public void setGroupLevel(java.lang.String groupLevel){
		this.groupLevel = groupLevel;
    }

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return null;
	}	

   

	


}
