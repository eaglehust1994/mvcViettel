/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.google.common.collect.Lists;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity (name = "settlementright")
@Table(name = "SETTLEMENT_RIGHT")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class SettlementRightBO extends BaseFWModelImpl {
     
/**
 * 
 */
private static final long serialVersionUID = 6894840553342827712L;

private CatEmployeeBO catemployee;
private ConstrConstructionsBO constrconstructions;
//private VConstructionHcqtBO vconstructionhcqt;
private RoleCaBO roleca;
//private java.lang.Long constructId;
private java.lang.Long settlementRightId;
private java.lang.Long employeeId;
private java.lang.Long roleid;
private java.lang.Long isCurrent;

 public SettlementRightBO() {
        setColId("settlementRightId");
        setColName("settlementRightId");
        setUniqueColumn(new String[]{"settlementRightId"});
}


 
 
 
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "SETTLEMENT_RIGHT_SEQ")
            }
    )
@Column(name = "SETTLEMENT_RIGHT_ID", length = 22)
public java.lang.Long getSettlementRightId(){
return settlementRightId;
}
public void setSettlementRightId(java.lang.Long settlementRightId)
{
this.settlementRightId = settlementRightId;
}
/*@Column(name = "EMPLOYEE_ID", length = 22)
public java.lang.Long getEmployeeId(){
return employeeId;
}
public void setEmployeeId(java.lang.Long employeeId)
{
this.employeeId = employeeId;
}*/

@ManyToOne
@JoinColumn (name = "EMPLOYEE_ID")
public CatEmployeeBO getCatemployee() {
	return catemployee;
}
public void setCatemployee(CatEmployeeBO catemployee) {
	this.catemployee = catemployee;
}

/*@Column(name = "ROLEID", length = 22)
public java.lang.Long getRoleid(){
return roleid;
}
public void setRoleid(java.lang.Long roleid)
{
this.roleid = roleid;
}*/
@Column(name = "IS_CURRENT", length = 22)
public java.lang.Long getIsCurrent(){
return isCurrent;
}
public void setIsCurrent(java.lang.Long isCurrent)
{
this.isCurrent = isCurrent;
}
   
@ManyToOne
@JoinColumn (name = "CONSTRUCT_ID")
public ConstrConstructionsBO getConstrconstructions() {
	return constrconstructions;
}
public void setConstrconstructions(ConstrConstructionsBO constrconstructions) {
	this.constrconstructions = constrconstructions;
}

@ManyToOne
@JoinColumn (name = "ROLEID")
public RoleCaBO getRoleca() {
	return roleca;
}
public void setRoleca(RoleCaBO roleca) {
	this.roleca = roleca;
}


    @Override
    public SettlementRightDTO toDTO() {
        SettlementRightDTO settlementRightDTO = new SettlementRightDTO();
        //set cac gia tri 
        settlementRightDTO.setConstructId(constrconstructions.getConstructId());
        settlementRightDTO.setSettlementRightId(this.settlementRightId);
        settlementRightDTO.setEmployeeId(this.employeeId);
        settlementRightDTO.setRoleid(this.roleid);
        settlementRightDTO.setIsCurrent(this.isCurrent);
        
        settlementRightDTO.setUserId(catemployee == null ?null : catemployee.getId());
        settlementRightDTO.setFullName(catemployee ==null ? null  : catemployee.getFullName());
        settlementRightDTO.setRoleid(roleca == null ? null :roleca.getRoleid());
        
        
        return settlementRightDTO;
    }




























}
