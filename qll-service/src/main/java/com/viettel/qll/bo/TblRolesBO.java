package com.viettel.qll.bo;

import com.viettel.qll.dto.TblRolesDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblRolesBO")
@Table(name = "TBL_ROLES")
/**
 *
 * @author: hailh10
 */
public class TblRolesBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_ROLES_SEQ") })
	@Column(name = "TBL_ROLES_ID", length = 22)
	private java.lang.Long tblRolesId;
	@Column(name = "ROLE_NAME", length = 4000)
	private java.lang.String roleName;
	@Column(name = "ROLE_CODE", length = 4000)
	private java.lang.String roleCode;
	@Column(name = "IS_ACTIVE", length = 22)
	private java.lang.Long isActive;

	
	public java.lang.Long getTblRolesId(){
		return tblRolesId;
	}
	
	public void setTblRolesId(java.lang.Long tblRolesId)
	{
		this.tblRolesId = tblRolesId;
	}
	
	public java.lang.String getRoleName(){
		return roleName;
	}
	
	public void setRoleName(java.lang.String roleName)
	{
		this.roleName = roleName;
	}
	
	public java.lang.String getRoleCode(){
		return roleCode;
	}
	
	public void setRoleCode(java.lang.String roleCode)
	{
		this.roleCode = roleCode;
	}
	
	public java.lang.Long getIsActive(){
		return isActive;
	}
	
	public void setIsActive(java.lang.Long isActive)
	{
		this.isActive = isActive;
	}
   
    @Override
    public TblRolesDTO toDTO() {
        TblRolesDTO tblRolesDTO = new TblRolesDTO(); 
        tblRolesDTO.setTblRolesId(this.tblRolesId);		
        tblRolesDTO.setRoleName(this.roleName);		
        tblRolesDTO.setRoleCode(this.roleCode);		
        tblRolesDTO.setIsActive(this.isActive);		
        return tblRolesDTO;
    }
}
