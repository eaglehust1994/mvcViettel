/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.wms.business.AttachmentBusinessImpl;
import com.viettel.wms.dto.AttachmentDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class AttachmentRsServiceImpl implements AttachmentRsService {

	// protected final Logger log = Logger.getLogger(UserRsService.class);
	@Autowired
	AttachmentBusinessImpl attachmentBusinessImpl;
	private List<Long> listId;

	@Override
	public Response getAttachment() {
		List<AttachmentDTO> ls = attachmentBusinessImpl.getAll();
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
	public Response getAttachmentById(Long id) {
		AttachmentDTO obj = (AttachmentDTO) attachmentBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateAttachment(List<AttachmentDTO> list) {
		for (AttachmentDTO obj : list) {
			attachmentBusinessImpl.update(obj);
		}
		return Response.ok().build();
	}

	@Override
	public Response addAttachment(List<AttachmentDTO> list) {
		AttachmentDTO att= new AttachmentDTO();
		att.setObjectId(list.get(0).getObjectId());
		List<AttachmentDTO> lst=attachmentBusinessImpl.getById(att);
		if(lst!=null&&lst.size()!=0){
		for(AttachmentDTO o:lst){
			attachmentBusinessImpl.deleteAtt(o);
		}
		}
		for (AttachmentDTO obj : list) {
//			obj.setAppParamCode(obj.getAppParam().getCode());
			obj.setStatus("1");
			attachmentBusinessImpl.save(obj);
		}
		return Response.ok(Response.Status.CREATED).build();
	}

	@Override
	public Response doSearchFile(AttachmentDTO obj) {
		DataListDTO data = attachmentBusinessImpl.doSearchFile(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response doSearch(AttachmentDTO obj) {
		List<AttachmentDTO> ls = attachmentBusinessImpl.doSearch(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response deleteAttachment(List<AttachmentDTO> list) {
		listId = null;
		for (AttachmentDTO obj : list) {
			obj.setStatus("0");
			listId.add(attachmentBusinessImpl.update(obj));
		}
		if (listId == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}
}
