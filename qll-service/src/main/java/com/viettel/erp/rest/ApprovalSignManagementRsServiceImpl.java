/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.business.ApprovalSignManagementBusinessImpl;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class ApprovalSignManagementRsServiceImpl implements ApprovalSignManagementRsService {

    @Autowired
    ApprovalSignManagementBusinessImpl approvalSignManagementBusinessImpl;

    @Override
    public Response getApprovalSignManagement() {
        List<ApprovalSignManagementDTO> ls = approvalSignManagementBusinessImpl.getAll();
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
    public Response getApprovalSignManagementById(Long id) {
        ApprovalSignManagementDTO obj = (ApprovalSignManagementDTO) approvalSignManagementBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateApprovalSignManagement(ApprovalSignManagementDTO obj) {
        Long id = approvalSignManagementBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addApprovalSignManagement(ApprovalSignManagementDTO obj) {
        Long id = approvalSignManagementBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteApprovalSignManagement(Long id) {
        ApprovalSignManagementDTO obj = (ApprovalSignManagementDTO) approvalSignManagementBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            approvalSignManagementBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response cancelAprroval(ApprovalSignManagementDTO dto) {
		boolean ls = approvalSignManagementBusinessImpl.cancelAprroval(dto);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
