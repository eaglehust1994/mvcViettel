package com.viettel.qll.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viettel.qll.bo.TblRoleUserBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_ROLE_USERBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblRoleUserDTO extends QllBaseDTO<TblRoleUserBO> {

	private java.lang.Long tblRoleUserId;
	private java.lang.Long tblRolesId;
	private java.lang.String tblRolesName;
	private java.lang.Long tblUsersId;
	private java.lang.String tblUsersName;
	private List<TblRoleUserDTO> lstRolesUser;
	

    @Override
    public TblRoleUserBO toModel() {
        TblRoleUserBO tblRoleUserBO = new TblRoleUserBO();
        tblRoleUserBO.setTblRoleUserId(this.tblRoleUserId);
        tblRoleUserBO.setTblRolesId(this.tblRolesId);
        tblRoleUserBO.setTblUsersId(this.tblUsersId);
        return tblRoleUserBO;
    }

    @Override
     public Long getFWModelId() {
        return tblRoleUserId;
    }
   
    @Override
    public String catchName() {
        return getTblRoleUserId().toString();
    }
	
	@JsonProperty("tblRoleUserId")
    public java.lang.Long getTblRoleUserId(){
		return tblRoleUserId;
    }
	
    public void setTblRoleUserId(java.lang.Long tblRoleUserId){
		this.tblRoleUserId = tblRoleUserId;
    }	
	
	@JsonProperty("tblRolesId")
    public java.lang.Long getTblRolesId(){
		return tblRolesId;
    }
	
    public void setTblRolesId(java.lang.Long tblRolesId){
		this.tblRolesId = tblRolesId;
    }	
	
	@JsonProperty("tblRolesName")
    public java.lang.String getTblRolesName(){
		return tblRolesName;
    }
	
    public void setTblRolesName(java.lang.String tblRolesName){
		this.tblRolesName = tblRolesName;
    }	
	
	@JsonProperty("tblUsersId")
    public java.lang.Long getTblUsersId(){
		return tblUsersId;
    }
	
    public void setTblUsersId(java.lang.Long tblUsersId){
		this.tblUsersId = tblUsersId;
    }	
	
	@JsonProperty("tblUsersName")
    public java.lang.String getTblUsersName(){
		return tblUsersName;
    }
	
    public void setTblUsersName(java.lang.String tblUsersName){
		this.tblUsersName = tblUsersName;
    }

	public List<TblRoleUserDTO> getLstRolesUser() {
		return lstRolesUser;
	}

	public void setLstRolesUser(List<TblRoleUserDTO> lstRolesUser) {
		this.lstRolesUser = lstRolesUser;
	}	
	
    
    
	
}
