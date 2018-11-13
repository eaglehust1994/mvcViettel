/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.PartnerDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_OWNER.PARTNER")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class PartnerBO extends BaseFWModelImpl {
     
private java.lang.String type;
private java.lang.String status;
private java.lang.String name;
private java.lang.String code;
private java.lang.Long partnerId;

 public PartnerBO() {
        setColId("partnerId");
        setColName("partnerId");
        setUniqueColumn(new String[]{"partnerId"});
}

@Column(name = "TYPE", length = 2)
public java.lang.String getType(){
return type;
}
public void setType(java.lang.String type)
{
this.type = type;
}
@Column(name = "STATUS", length = 10)
public java.lang.String getStatus(){
return status;
}
public void setStatus(java.lang.String status)
{
this.status = status;
}
@Column(name = "NAME", length = 100)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "CODE", length = 100)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_OWNER.PARTNER_SEQ")
            }
    )
@Column(name = "PARTNER_ID", length = 22)
public java.lang.Long getPartnerId(){
return partnerId;
}
public void setPartnerId(java.lang.Long partnerId)
{
this.partnerId = partnerId;
}
   

    @Override
    public PartnerDTO toDTO() {
        PartnerDTO partnerDTO = new PartnerDTO();
        //set cac gia tri 
        partnerDTO.setType(this.type);
        partnerDTO.setStatus(this.status);
        partnerDTO.setName(this.name);
        partnerDTO.setCode(this.code);
        partnerDTO.setPartnerId(this.partnerId);
        return partnerDTO;
    }
}
