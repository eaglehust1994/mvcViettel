/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.viettel.erp.dto.ConstrAcceptLostNoteDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

@Entity
@Table(name = "CONSTR_ACCEPT_LOST_NOTE")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class ConstrAcceptLostNoteBO extends BaseFWModelImpl {
     
private java.lang.Long acceptNoteId;
private java.lang.String acceptorName;
private java.lang.String acceptorPosition;
private java.lang.Long constrId;
private java.lang.String description;
private java.util.Date createDate;
private java.lang.Long creatorId;
private java.util.Date confirmDate;

 public ConstrAcceptLostNoteBO() {
        setColId("acceptNoteId");
        setColName("acceptNoteId");
        setUniqueColumn(new String[]{"acceptNoteId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONSTR_ACCEPT_LOST_NOTE_SEQ")
            }
    )
@Column(name = "ACCEPT_NOTE_ID", length = 22)
public java.lang.Long getAcceptNoteId(){
return acceptNoteId;
}
public void setAcceptNoteId(java.lang.Long acceptNoteId)
{
this.acceptNoteId = acceptNoteId;
}
@Column(name = "ACCEPTOR_NAME", length = 200)
public java.lang.String getAcceptorName(){
return acceptorName;
}
public void setAcceptorName(java.lang.String acceptorName)
{
this.acceptorName = acceptorName;
}
@Column(name = "ACCEPTOR_POSITION", length = 200)
public java.lang.String getAcceptorPosition(){
return acceptorPosition;
}
public void setAcceptorPosition(java.lang.String acceptorPosition)
{
this.acceptorPosition = acceptorPosition;
}
@Column(name = "CONSTR_ID", length = 22)
public java.lang.Long getConstrId(){
return constrId;
}
public void setConstrId(java.lang.Long constrId)
{
this.constrId = constrId;
}
@Column(name = "DESCRIPTION", length = 1400)
public java.lang.String getDescription(){
return description;
}
public void setDescription(java.lang.String description)
{
this.description = description;
}
@Column(name = "CREATE_DATE", length = 7)
public java.util.Date getCreateDate(){
return createDate;
}
public void setCreateDate(java.util.Date createDate)
{
this.createDate = createDate;
}
@Column(name = "CREATOR_ID", length = 22)
public java.lang.Long getCreatorId(){
return creatorId;
}
public void setCreatorId(java.lang.Long creatorId)
{
this.creatorId = creatorId;
}
@Column(name = "CONFIRM_DATE", length = 7)
public java.util.Date getConfirmDate(){
return confirmDate;
}
public void setConfirmDate(java.util.Date confirmDate)
{
this.confirmDate = confirmDate;
}
   

    @Override
    public ConstrAcceptLostNoteDTO toDTO() {
        ConstrAcceptLostNoteDTO constrAcceptLostNoteDTO = new ConstrAcceptLostNoteDTO();
        //set cac gia tri 
        constrAcceptLostNoteDTO.setAcceptNoteId(this.acceptNoteId);
        constrAcceptLostNoteDTO.setAcceptorName(this.acceptorName);
        constrAcceptLostNoteDTO.setAcceptorPosition(this.acceptorPosition);
        constrAcceptLostNoteDTO.setConstrId(this.constrId);
        constrAcceptLostNoteDTO.setDescription(this.description);
        constrAcceptLostNoteDTO.setCreateDate(this.createDate);
        constrAcceptLostNoteDTO.setCreatorId(this.creatorId);
        constrAcceptLostNoteDTO.setConfirmDate(this.confirmDate);
        return constrAcceptLostNoteDTO;
    }
}
