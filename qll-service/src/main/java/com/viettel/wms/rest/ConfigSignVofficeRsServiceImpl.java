/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.service.base.dto.DataListDTO;
import com.viettel.wms.business.ConfigSignVofficeBusinessImpl;
import com.viettel.wms.dto.ConfigSignVofficeDTO;
import com.viettel.wms.dto.StockDTO;

/**
 *
 * @author TuanNB
 */
public class ConfigSignVofficeRsServiceImpl implements ConfigSignVofficeRsService {
  //  protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    ConfigSignVofficeBusinessImpl configSignVofficeBusinessImpl;
 
    @Override
    @Transactional
    public Response addConfigSignVoffice(List<ConfigSignVofficeDTO> obj) {
    	Long id =0l;
    	for(int i=0;i<obj.size();i++){
    		
    		id = configSignVofficeBusinessImpl.save(obj.get(i));
    		if(id==0l){
    			break;
    		}
    	}
        if (id==0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }
    }
    
    @Override
    @Transactional
    public Response updateConfigSignVoffice(List<ConfigSignVofficeDTO> obj) {
    	Long id =0l;
    	for(int i=0;i<obj.size();i++){
    		id = configSignVofficeBusinessImpl.update(obj.get(i));
    		if(id==0l){
    			break;
    		}
    	}
        if (id==0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }
    }

    @Override
	public Response doSearchStock(StockDTO obj) {
		// TODO Auto-generated method stub
		DataListDTO data= configSignVofficeBusinessImpl.doSearchStock(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}

	@Override
	public Response getDataByID(ConfigSignVofficeDTO obj) {
		DataListDTO data = configSignVofficeBusinessImpl.getDataByID(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(data).build();
        }
	}
}
