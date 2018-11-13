package com.viettel.qll.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.qll.dto.TblRoleUserDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblRoleUserBO")
@Table(name = "TBL_ROLE_USER")
/**
 *
 * @author: hailh10
 */
public class TblRoleUserBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "SEQ_TBL_ROLES_USER") })
	@Column(name = "TBL_ROLE_USER_ID", length = 22)
	private java.lang.Long tblRoleUserId;
	@Column(name = "TBL_ROLES_ID", length = 22)
	private java.lang.Long tblRolesId;
	@Column(name = "TBL_USERS_ID", length = 22)
	private java.lang.Long tblUsersId;

	
	public java.lang.Long getTblRoleUserId(){
		return tblRoleUserId;
	}
	
	public void setTblRoleUserId(java.lang.Long tblRoleUserId)
	{
		this.tblRoleUserId = tblRoleUserId;
	}
	
	public java.lang.Long getTblRolesId(){
		return tblRolesId;
	}
	
	public void setTblRolesId(java.lang.Long tblRolesId)
	{
		this.tblRolesId = tblRolesId;
	}
	
	public java.lang.Long getTblUsersId(){
		return tblUsersId;
	}
	
	public void setTblUsersId(java.lang.Long tblUsersId)
	{
		this.tblUsersId = tblUsersId;
	}
   
    @Override
    public TblRoleUserDTO toDTO() {
        TblRoleUserDTO tblRoleUserDTO = new TblRoleUserDTO(); 
        tblRoleUserDTO.setTblRoleUserId(this.tblRoleUserId);		
        tblRoleUserDTO.setTblRolesId(this.tblRolesId);		
        tblRoleUserDTO.setTblUsersId(this.tblUsersId);		
        return tblRoleUserDTO;
    }
}
