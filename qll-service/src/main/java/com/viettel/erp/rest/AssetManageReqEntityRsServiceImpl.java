/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.AssetManageReqEntityBusinessImpl;
import com.viettel.erp.dto.AssetManageReqEntityDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class AssetManageReqEntityRsServiceImpl implements AssetManageReqEntityRsService {

    @Autowired
    AssetManageReqEntityBusinessImpl assetManageReqEntityBusinessImpl;

    @Override
    public Response getAssetManageReqEntity() {
        List<AssetManageReqEntityDTO> ls = assetManageReqEntityBusinessImpl.getAll();
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
    public Response getAssetManageReqEntityById(Long id) {
        AssetManageReqEntityDTO obj = (AssetManageReqEntityDTO) assetManageReqEntityBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAssetManageReqEntity(AssetManageReqEntityDTO obj) {
        Long id = assetManageReqEntityBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAssetManageReqEntity(AssetManageReqEntityDTO obj) {
        Long id = assetManageReqEntityBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteAssetManageReqEntity(Long id) {
        AssetManageReqEntityDTO obj = (AssetManageReqEntityDTO) assetManageReqEntityBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            assetManageReqEntityBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
