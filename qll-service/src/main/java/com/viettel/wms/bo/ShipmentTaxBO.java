/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.wms.bo;

import com.viettel.wms.dto.ShipmentTaxDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WMS_OWNER_KTTS.SHIPMENT_TAX")
/**
 *
 * @author: PhongPV
 * @version: 1.0
 * @since: 1.0
 */
public class ShipmentTaxBO extends BaseFWModelImpl {
     
private java.lang.Long shipmentTaxId;
private java.lang.Long taxId;
private java.lang.Long valueshipmenttax;
private java.lang.Long shipmentId;

 public ShipmentTaxBO() {
        setColId("shipmentId");
        setColName("shipmentId");
        setUniqueColumn(new String[]{"shipmentId"});
}

@Id
@GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "WMS_OWNER_KTTS.SHIPMENT_TAX_SEQ")
            }
    )
@Column(name = "SHIPMENT_TAX_ID", length = 22)
public java.lang.Long getShipmentTaxId(){
return shipmentTaxId;
}
public void setShipmentTaxId(java.lang.Long shipmentTaxId)
{
this.shipmentTaxId = shipmentTaxId;
}
@Column(name = "TAX_ID", length = 22)
public java.lang.Long getTaxId(){
return taxId;
}
public void setTaxId(java.lang.Long taxId)
{
this.taxId = taxId;
}

@Column(name = "SHIPMENT_ID", length = 22)
public java.lang.Long getShipmentId(){
return shipmentId;
}
public void setShipmentId(java.lang.Long shipmentId)
{
this.shipmentId = shipmentId;
}

@Column(name = "VALUE", length = 15)
public java.lang.Long getValueshipmenttax() {
	return valueshipmenttax;
}

public void setValueshipmenttax(java.lang.Long valueshipmenttax) {
	this.valueshipmenttax = valueshipmenttax;
}


   

    @Override
    public ShipmentTaxDTO toDTO() {
        ShipmentTaxDTO shipmentTaxDTO = new ShipmentTaxDTO();
        //set cac gia tri 
        shipmentTaxDTO.setShipmentTaxId(this.shipmentTaxId);
        shipmentTaxDTO.setTaxId(this.taxId);
        shipmentTaxDTO.setValueshipmenttax(this.valueshipmenttax);
        shipmentTaxDTO.setShipmentId(this.shipmentId);
        return shipmentTaxDTO;
    }
}
