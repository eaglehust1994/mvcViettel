/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CatPartnersBusinessImpl;
import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.erp.dto.CatPartnersSearchDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CatPartnersRsServiceImpl implements CatPartnersRsService {

    @Autowired
    CatPartnersBusinessImpl catPartnersBusinessImpl;

    @Override
    public Response getCatPartners() {
        List<CatPartnersDTO> ls = catPartnersBusinessImpl.getAll();
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
    public Response getCatPartnersById(Long id) {
        CatPartnersDTO obj = (CatPartnersDTO) catPartnersBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCatPartners(CatPartnersDTO obj) {
        Long id = catPartnersBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCatPartners(CatPartnersDTO obj) {
        Long id = catPartnersBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCatPartners(Long id) {
        CatPartnersDTO obj = (CatPartnersDTO) catPartnersBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            catPartnersBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getCatPartnersName() {
		List<CatPartnersDTO> ls = catPartnersBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	@Override
	public Response getForAutoComplete(CatPartnersDTO query) {
		List<CatPartnersDTO> list = catPartnersBusinessImpl.getForAutoComplete(query);
		if (query.getSetIsSize()){
			CatPartnersDTO moreObject = new CatPartnersDTO();
			moreObject.setPartnerId(0l);
//			moreObject.setPartnerName(0l);
			moreObject.setPartnerName("Search more");
			
			list.add(moreObject);
		}
		return Response.ok(list).build();
	}

	@Override
	public Response doSearch(CatPartnersSearchDTO dto) {
    TotalNumDTO totalnum = new TotalNumDTO(0);
		
		List<CatPartnersDTO> ls = catPartnersBusinessImpl.doSearch(dto, totalnum);

		//String queryCount_ = vConstructionHcqtBusinessImpl.queryCount();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	if (dto.getIsSize()){
    			CatPartnersDTO moreObject = new CatPartnersDTO();
    			moreObject.setPartnerId(0l);
    			moreObject.setPartnerName("Search more");
    			
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
