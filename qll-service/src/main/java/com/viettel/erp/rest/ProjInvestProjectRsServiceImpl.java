/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.ProjInvestProjectBusinessImpl;
import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.erp.dto.ProjInvestProjectDTO;
import com.viettel.erp.dto.ProjInvestProjectSearchDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ProjInvestProjectRsServiceImpl implements ProjInvestProjectRsService {

    @Autowired
    ProjInvestProjectBusinessImpl projInvestProjectBusinessImpl;

    @Override
    public Response getProjInvestProject() {
        List<ProjInvestProjectDTO> ls = projInvestProjectBusinessImpl.getAllList();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {

            return Response.ok(ls).build();
        }
    }

    @Override
    public Response getProjInvestProjectById(Long id) {
        ProjInvestProjectDTO obj = (ProjInvestProjectDTO) projInvestProjectBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateProjInvestProject(ProjInvestProjectDTO obj) {
        Long id = projInvestProjectBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addProjInvestProject(ProjInvestProjectDTO obj) {
        Long id = projInvestProjectBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteProjInvestProject(Long id) {
        ProjInvestProjectDTO obj = (ProjInvestProjectDTO) projInvestProjectBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            projInvestProjectBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response doSearch(ProjInvestProjectSearchDTO obj) {

		    TotalNumDTO totalnum = new TotalNumDTO(0);
			
		    List<ProjInvestProjectDTO> ls = projInvestProjectBusinessImpl.doSearch(obj, totalnum);


	        if (ls == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	if (obj.getIsSize()){
	        		ProjInvestProjectDTO moreObject = new ProjInvestProjectDTO();
	    			moreObject.setId(0l);
	    			moreObject.setName("Search more");
	    			
	    			ls.add(moreObject);
	    			 return Response.ok(ls).build();
	    			
	    		}else{
	    			DataListDTO data = new DataListDTO();
	                data.setData(ls);
	                data.setTotal(totalnum.getNum());
	                data.setSize(totalnum.getNum());
	                data.setStart(1);
	                return Response.ok(data).build();
	    		}
	            
	        }
	}
}
