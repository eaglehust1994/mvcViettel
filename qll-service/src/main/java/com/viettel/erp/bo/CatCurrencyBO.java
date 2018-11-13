/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.CatCurrencyDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CAT_CURRENCY")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class CatCurrencyBO extends BaseFWModelImpl {
     
private java.lang.Long currencyId;
private java.lang.String name;
private java.lang.String code;
private java.lang.Long isActive;

 public CatCurrencyBO() {
        setColId("currencyId");
        setColName("currencyId");
        setUniqueColumn(new String[]{"currencyId"});
}
	public CatCurrencyBO(Long id) {
		this.currencyId = id;
	}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CAT_CURRENCY_SEQ")
            }
    )
@Column(name = "CURRENCY_ID", length = 22)
public java.lang.Long getCurrencyId(){
return currencyId;
}
public void setCurrencyId(java.lang.Long currencyId)
{
this.currencyId = currencyId;
}
@Column(name = "NAME", length = 400)
public java.lang.String getName(){
return name;
}
public void setName(java.lang.String name)
{
this.name = name;
}
@Column(name = "CODE", length = 20)
public java.lang.String getCode(){
return code;
}
public void setCode(java.lang.String code)
{
this.code = code;
}
@Column(name = "IS_ACTIVE", length = 22)
public java.lang.Long getIsActive(){
return isActive;
}
public void setIsActive(java.lang.Long isActive)
{
this.isActive = isActive;
}
   

    @Override
    public CatCurrencyDTO toDTO() {
        CatCurrencyDTO catCurrencyDTO = new CatCurrencyDTO();
        //set cac gia tri 
        catCurrencyDTO.setCurrencyId(this.currencyId);
        catCurrencyDTO.setName(this.name);
        catCurrencyDTO.setCode(this.code);
        catCurrencyDTO.setIsActive(this.isActive);
        return catCurrencyDTO;
    }
}
