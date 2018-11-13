package com.viettel.qll.dto;

import com.viettel.qll.bo.TblRolesBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_ROLESBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblRolesDTO extends BaseFWDTOImpl<TblRolesBO> {

	private java.lang.Long tblRolesId;
	private java.lang.String roleName;
	private java.lang.String roleCode;
	private java.lang.Long isActive;


    @Override
    public TblRolesBO toModel() {
        TblRolesBO tblRolesBO = new TblRolesBO();
        tblRolesBO.setTblRolesId(this.tblRolesId);
        tblRolesBO.setRoleName(this.roleName);
        tblRolesBO.setRoleCode(this.roleCode);
        tblRolesBO.setIsActive(this.isActive);
//		if (this.isActive == null || "".equalsIgnoreCase(this.isActive) || "N".equalsIgnoreCase(this.isActive)) {
//			tblRolesBO.setIsActive("N");
//		} else {
//			tblRolesBO.setIsActive("Y");
//		}
        return tblRolesBO;
    }

    @Override
     public Long getFWModelId() {
        return tblRolesId;
    }
   
    @Override
    public String catchName() {
        return getTblRolesId().toString();
    }
	
	@JsonProperty("tblRolesId")
    public java.lang.Long getTblRolesId(){
		return tblRolesId;
    }
	
    public void setTblRolesId(java.lang.Long tblRolesId){
		this.tblRolesId = tblRolesId;
    }	
	
	@JsonProperty("roleName")
    public java.lang.String getRoleName(){
		return roleName;
    }
	
    public void setRoleName(java.lang.String roleName){
		this.roleName = roleName;
    }	
	
	@JsonProperty("roleCode")
    public java.lang.String getRoleCode(){
		return roleCode;
    }
	
    public void setRoleCode(java.lang.String roleCode){
		this.roleCode = roleCode;
    }	
	
	@JsonProperty("isActive")
    public java.lang.Long getIsActive(){
		return isActive;
    }
	
    public void setIsActive(java.lang.Long isActive){
		this.isActive = isActive;
    }	
	


	
}
