/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.service.base.dto.DataListDTO;
import com.viettel.wms.business.SysUserQLKBusinessImpl;
//import com.viettel.wms.dto.GoodsDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.SysUserQLKDTO;

/**
 *
 * @author HungLQ9
 */
public class SysUserQLKRsServiceImpl implements SysUserQLKRsService {

//    protected final Logger log = Logger.getLogger(UserwmsRsService.class);
    @Autowired
    SysUserQLKBusinessImpl sysUserwmsBusinessImpl;

    @Override
    public Response getSysUserwms() {
        List<SysUserQLKDTO> ls = sysUserwmsBusinessImpl.getAll();
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
	public Response getForAutoComplete(SysUserQLKDTO obj) {
		return Response.ok(sysUserwmsBusinessImpl.getForAutoComplete(obj)).build();
	}
	
	@Override
	public Response doSearchUserInPopup(SysUserQLKDTO obj) {
		// TODO Auto-generated method stub
		return Response.ok(sysUserwmsBusinessImpl.doSearchUserInPopup(obj)).build();
	}
}
