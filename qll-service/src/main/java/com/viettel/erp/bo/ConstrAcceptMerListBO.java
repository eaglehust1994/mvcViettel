/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ConstrAcceptMerListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CONSTR_ACCEPT_MER_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrAcceptMerListBO extends BaseFWModelImpl {

private ConstructionAcceptanceBO constructionacceptance;	
private java.lang.Long constrAcceptMerListId;
private java.lang.Long merEntityId;
private java.lang.Long merChandiseId;
private java.lang.String merName;
private java.lang.String serialNumber;
//private java.lang.Long unitId;
//private CatUnitBO catunit;

private java.lang.Double handoverQuantity;
private java.lang.Double executeQuantity;
private java.lang.String comments;
private java.lang.Long constructionAcceptanceId;

 public ConstrAcceptMerListBO() {
        setColId("constrAcceptMerListId");
        setColName("constrAcceptMerListId");
        setUniqueColumn(new String[]{"constrAcceptMerListId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_ACCEPT_MER_LIST_SEQ")
            }
    )
@Column(name = "CONSTR_ACCEPT_MER_LIST_ID", length = 22)
public java.lang.Long getConstrAcceptMerListId(){
return constrAcceptMerListId;
}
public void setConstrAcceptMerListId(java.lang.Long constrAcceptMerListId)
{
this.constrAcceptMerListId = constrAcceptMerListId;
}
@Column(name = "MER_ENTITY_ID", length = 22)
public java.lang.Long getMerEntityId(){
return merEntityId;
}
public void setMerEntityId(java.lang.Long merEntityId)
{
this.merEntityId = merEntityId;
}
@Column(name = "MERCHANDISE_ID", length = 22)
public java.lang.Long getMerChandiseId() {
	return merChandiseId;
}

public void setMerChandiseId(java.lang.Long merChandiseId) {
	this.merChandiseId = merChandiseId;
}

@Column(name = "MER_NAME", length = 1000)
public java.lang.String getMerName(){
return merName;
}
public void setMerName(java.lang.String merName)
{
this.merName = merName;
}
@Column(name = "SERIAL_NUMBER", length = 400)
public java.lang.String getSerialNumber(){
return serialNumber;
}
public void setSerialNumber(java.lang.String serialNumber)
{
this.serialNumber = serialNumber;
}
/*@Column(name = "UNIT_ID", length = 22)
public java.lang.Long getUnitId(){
return unitId;
}
public void setUnitId(java.lang.Long unitId)
{
this.unitId = unitId;
}*/

//@ManyToOne( optional = true)
//@JoinColumn(name = "UNIT_ID")
//@NotFound(action = NotFoundAction.IGNORE)
//public CatUnitBO getCatunit() {
//	return catunit;
//}
//
//public void setCatunit(CatUnitBO catunit) {
//	this.catunit = catunit;
//}

@Column(name = "HANDOVER_QUANTITY", length = 22)
public java.lang.Double getHandoverQuantity(){
return handoverQuantity;
}
public void setHandoverQuantity(java.lang.Double handoverQuantity)
{
this.handoverQuantity = handoverQuantity;
}
@Column(name = "EXECUTE_QUANTITY", length = 22)
public java.lang.Double getExecuteQuantity(){
return executeQuantity;
}
public void setExecuteQuantity(java.lang.Double executeQuantity)
{
this.executeQuantity = executeQuantity;
}
@Column(name = "COMMENTS", length = 1000)
public java.lang.String getComments(){
return comments;
}
public void setComments(java.lang.String comments)
{
this.comments = comments;
}
/*@Column(name = "CONSTRUCTION_ACCEPTANCE_ID", length = 22)
public java.lang.Long getConstructionAcceptanceId(){
return constructionAcceptanceId;
}
public void setConstructionAcceptanceId(java.lang.Long constructionAcceptanceId)
{
this.constructionAcceptanceId = constructionAcceptanceId;
}*/
   

/**
 * @return the constructionacceptance
 */
@ManyToOne
@JoinColumn(name = "CONSTRUCTION_ACCEPTANCE_ID")
public ConstructionAcceptanceBO getConstructionacceptance() {
	return constructionacceptance;
}

/**
 * @param constructionacceptance the constructionacceptance to set
 */
public void setConstructionacceptance(ConstructionAcceptanceBO constructionacceptance) {
	this.constructionacceptance = constructionacceptance;
}
    @Override
    public ConstrAcceptMerListDTO toDTO() {
        ConstrAcceptMerListDTO constrAcceptMerListDTO = new ConstrAcceptMerListDTO();
        //set cac gia tri 
        constrAcceptMerListDTO.setConstrAcceptMerListId(this.constrAcceptMerListId);
        constrAcceptMerListDTO.setMerEntityId(this.merEntityId);
        constrAcceptMerListDTO.setMerChandiseId(this.merChandiseId);
        constrAcceptMerListDTO.setMerName(this.merName);
        constrAcceptMerListDTO.setSerialNumber(this.serialNumber);
//        constrAcceptMerListDTO.setUnitId(catunit == null? null : catunit.getUnitId() );
//        constrAcceptMerListDTO.setUnitName(catunit == null? null : catunit.getName() );
        constrAcceptMerListDTO.setHandoverQuantity(this.handoverQuantity==null?0D:this.handoverQuantity);
        constrAcceptMerListDTO.setExecuteQuantity(this.executeQuantity==null?0D:this.executeQuantity);
        constrAcceptMerListDTO.setComments(this.comments==null?"":this.comments);
        constrAcceptMerListDTO.setConstructionAcceptanceId(this.constructionAcceptanceId);
        return constrAcceptMerListDTO;
    }




}
