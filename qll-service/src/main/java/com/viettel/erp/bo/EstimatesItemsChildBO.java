/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;

@Entity(name = "estimatesitemschild")
@Table(name = "ESTIMATES_ITEMS_CHILD")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class EstimatesItemsChildBO extends BaseFWModelImpl {
     
private java.lang.Long estimatesItemChildId;
//private java.lang.Long constrEstimateInfoId;
private java.lang.String itemsCode;
private java.lang.String itemName;
private ConstrEstimateInfoBO constrestimateinfo;

 public EstimatesItemsChildBO() {
        setColId("estimatesItemChildId");
        setColName("estimatesItemChildId");
        setUniqueColumn(new String[]{"estimatesItemChildId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "ESTIMATES_ITEMS_CHILD_SEQ")
            }
    )
@Column(name = "ESTIMATES_ITEM_CHILD_ID", length = 22)
public java.lang.Long getEstimatesItemChildId(){
return estimatesItemChildId;
}
public void setEstimatesItemChildId(java.lang.Long estimatesItemChildId)
{
this.estimatesItemChildId = estimatesItemChildId;
}
/*@Column(name = "CONSTR_ESTIMATE_INFO_ID", length = 22)
public java.lang.Long getConstrEstimateInfoId(){
return constrEstimateInfoId;
}
public void setConstrEstimateInfoId(java.lang.Long constrEstimateInfoId)
{
this.constrEstimateInfoId = constrEstimateInfoId;
}*/

@ManyToOne(fetch = FetchType.LAZY, optional = true)
@NotFound(action = NotFoundAction.IGNORE)
@JoinColumn(name = "CONSTR_ESTIMATE_INFO_ID")
public ConstrEstimateInfoBO getConstrestimateinfo() {
	return constrestimateinfo;
}

public void setConstrestimateinfo(ConstrEstimateInfoBO constrestimateinfo) {
	this.constrestimateinfo = constrestimateinfo;
}

@Column(name = "ITEMS_CODE", length = 200)
public java.lang.String getItemsCode(){
return itemsCode;
}
public void setItemsCode(java.lang.String itemsCode)
{
this.itemsCode = itemsCode;
}
@Column(name = "ITEM_NAME", length = 200)
public java.lang.String getItemName(){
return itemName;
}
public void setItemName(java.lang.String itemName)
{
this.itemName = itemName;
}
   

    @Override
    public EstimatesItemsChildDTO toDTO() {
        EstimatesItemsChildDTO estimatesItemsChildDTO = new EstimatesItemsChildDTO();
        //set cac gia tri 
        estimatesItemsChildDTO.setEstimatesItemChildId(this.estimatesItemChildId);
        estimatesItemsChildDTO.setConstrEstimateInfoId(constrestimateinfo==null?null:constrestimateinfo.getConstrEstimateInfoId());
        estimatesItemsChildDTO.setItemsCode(this.itemsCode);
        estimatesItemsChildDTO.setItemName(this.itemName);
        return estimatesItemsChildDTO;
    }
}
