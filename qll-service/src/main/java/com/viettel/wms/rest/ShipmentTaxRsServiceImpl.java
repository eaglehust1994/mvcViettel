/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.ShipmentTaxBusinessImpl;
import com.viettel.wms.dto.ShipmentTaxDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ShipmentTaxRsServiceImpl implements ShipmentTaxRsService {

	//  protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    ShipmentTaxBusinessImpl shipmentTaxBusinessImpl;

    @Override
    public Response getShipmentTax() {
        List<ShipmentTaxDTO> ls = shipmentTaxBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            DataListDTO data = new DataListDTO();
            data.setData(ls);
            data.setTotal(ls.size());
            data.setSize(ls.size());
            data.setStart(1);
            return Response.ok(data).build();
        }
    }

    @Override
    public Response getShipmentTaxById(Long id) {
        ShipmentTaxDTO obj = (ShipmentTaxDTO) shipmentTaxBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateShipmentTax(ShipmentTaxDTO obj) {
        Long id = shipmentTaxBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addShipmentTax(ShipmentTaxDTO obj) {
        Long id = shipmentTaxBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteShipmentTax(Long id) {
        ShipmentTaxDTO obj = (ShipmentTaxDTO) shipmentTaxBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            shipmentTaxBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response doSearchTAX(ShipmentTaxDTO obj) {
		List<ShipmentTaxDTO> ls = shipmentTaxBusinessImpl.doSearchTAX(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
}
