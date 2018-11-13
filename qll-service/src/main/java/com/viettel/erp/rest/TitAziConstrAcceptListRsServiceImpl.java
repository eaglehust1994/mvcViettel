/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.TitAziConstrAcceptBusinessImpl;
import com.viettel.erp.business.TitAziConstrAcceptListBusinessImpl;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class TitAziConstrAcceptListRsServiceImpl implements TitAziConstrAcceptListRsService {

    @Autowired
    TitAziConstrAcceptListBusinessImpl titAziConstrAcceptListBusinessImpl;
    TitAziConstrAcceptBusinessImpl titAziConstrAcceptBusinessImpl;
       
    
    
    @Override
    public Response getTitAziConstrAcceptList() {
        List<TitAziConstrAcceptListDTO> ls = titAziConstrAcceptListBusinessImpl.getAll();
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
    public Response saveListTitAziConstrAcceptList(List<TitAziConstrAcceptListDTO> obj){
    	long count=0;
    	if(obj.size() > 0 ){
    		for(TitAziConstrAcceptListDTO ls:obj){
    			TitAziConstrAcceptListDTO newObj = new TitAziConstrAcceptListDTO();
    			newObj.setContent(ls.getContent());
    			newObj.setUnit(ls.getUnit());
    			newObj.setTitleCornerBAdjust(ls.getTitleCornerBAdjust());
    			newObj.setAzimuthDirectBAdjust(ls.getAzimuthDirectBAdjust());
    			newObj.setTitleCornerAAdjust(ls.getTitleCornerAAdjust());
    			newObj.setAzimuthDirectAAdjust(ls.getAzimuthDirectAAdjust());
    			newObj.setNote(ls.getNote());
    			newObj.setTitAziConstrAcceptId(ls.getTitAziConstrAcceptId());
    			titAziConstrAcceptListBusinessImpl.save(newObj);
    			newObj.getTitAziConAcceptListId();
    			count +=1;
    		}
    	}
    	if(0 == count){
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}else{
    		return Response.ok(Response.Status.CREATED).build();
    	}
    }

    @Override
    public Response getTitAziConstrAcceptListById(Long id) {
        TitAziConstrAcceptListDTO obj = (TitAziConstrAcceptListDTO) titAziConstrAcceptListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateTitAziConstrAcceptList(TitAziConstrAcceptListDTO obj) {
        Long id = titAziConstrAcceptListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addTitAziConstrAcceptList(TitAziConstrAcceptListDTO obj) {
        Long id = titAziConstrAcceptListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteTitAziConstrAcceptList(Long id) {
        TitAziConstrAcceptListDTO obj = (TitAziConstrAcceptListDTO) titAziConstrAcceptListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            titAziConstrAcceptListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getListTitAziConstrAcceptById(Long titAziConstrAcceptId) {
		List<TitAziConstrAcceptListDTO> list= titAziConstrAcceptListBusinessImpl.listById(titAziConstrAcceptId);
		
		if (list == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	 
             return Response.ok(list).build();
        }
		
	}

	@Override
	public Response deleteList(List<String> listString) {
		
		boolean ls = titAziConstrAcceptListBusinessImpl.deleteList(listString);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	

	
	
}
