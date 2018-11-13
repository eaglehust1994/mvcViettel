/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.ConstrWorkCompConfListDTO;
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
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CONSTR_WORK_COMP_CONF_LIST")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrWorkCompConfListBO extends BaseFWModelImpl {
     
private java.lang.Long constrWorkCompListId;
private java.lang.Double executeQuantity;
private java.lang.String comments;
private java.lang.Long estimatesWorkItemId;
private java.lang.Long constrWorkCompConfirmId;
private ConstrWorkCompConfirmBO constrWorkCompConfirmBO;

 public ConstrWorkCompConfListBO() {
        setColId("constrWorkCompListId");
        setColName("constrWorkCompListId");
        setUniqueColumn(new String[]{"constrWorkCompListId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_WORK_COMP_CONF_LIST_SEQ")
            }
    )
@Column(name = "CONSTR_WORK_COMP_LIST_ID", length = 22)
public java.lang.Long getConstrWorkCompListId(){
return constrWorkCompListId;
}
public void setConstrWorkCompListId(java.lang.Long constrWorkCompListId)
{
this.constrWorkCompListId = constrWorkCompListId;
}
@Column(name = "EXECUTE_QUANTITY", length = 22)
public java.lang.Double getExecuteQuantity(){
return executeQuantity;
}
public void setExecuteQuantity(java.lang.Double executeQuantity)
{
this.executeQuantity = executeQuantity;
}
@Column(name = "COMMENTS", length = 2000)
public java.lang.String getComments(){
return comments;
}
public void setComments(java.lang.String comments)
{
this.comments = comments;
}
@Column(name = "ESTIMATES_WORK_ITEM_ID", length = 22)
public java.lang.Long getEstimatesWorkItemId(){
return estimatesWorkItemId;
}
public void setEstimatesWorkItemId(java.lang.Long estimatesWorkItemId)
{
this.estimatesWorkItemId = estimatesWorkItemId;
}
//@Column(name = "CONSTR_WORK_COMP_CONFIRM_ID", length = 22)
//public java.lang.Long getConstrWorkCompConfirmId(){
//return constrWorkCompConfirmId;
//}
//public void setConstrWorkCompConfirmId(java.lang.Long constrWorkCompConfirmId)
//{
//this.constrWorkCompConfirmId = constrWorkCompConfirmId;
//}
   

    @Override
    public ConstrWorkCompConfListDTO toDTO() {
        ConstrWorkCompConfListDTO constrWorkCompConfListDTO = new ConstrWorkCompConfListDTO();
        //set cac gia tri 
        constrWorkCompConfListDTO.setConstrWorkCompListId(this.constrWorkCompListId);
        constrWorkCompConfListDTO.setExecuteQuantity(this.executeQuantity);
        constrWorkCompConfListDTO.setComments(this.comments);
        constrWorkCompConfListDTO.setEstimatesWorkItemId(this.estimatesWorkItemId);
        constrWorkCompConfListDTO.setConstrWorkCompConfirmId(this.constrWorkCompConfirmId);
        return constrWorkCompConfListDTO;
    }
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="CONSTR_WORK_COMP_CONFIRM_ID")
	public ConstrWorkCompConfirmBO getConstrWorkCompConfirmBO() {
		return constrWorkCompConfirmBO;
	}

	public void setConstrWorkCompConfirmBO(ConstrWorkCompConfirmBO constrWorkCompConfirmBO) {
		this.constrWorkCompConfirmBO = constrWorkCompConfirmBO;
	}
}
