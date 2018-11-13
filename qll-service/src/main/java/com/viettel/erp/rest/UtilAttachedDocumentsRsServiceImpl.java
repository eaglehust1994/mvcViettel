/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.espringtran.compressor4j.bean.CompressionLevel;
import com.espringtran.compressor4j.bean.CompressionType;
import com.espringtran.compressor4j.bean.ZipLevel;
import com.espringtran.compressor4j.compressor.FileCompressor;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class UtilAttachedDocumentsRsServiceImpl implements UtilAttachedDocumentsRsService {

	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(UtilAttachedDocumentsRsServiceImpl.class);
	
	@Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;

	@Value("${folder_upload}")
	private String folder2Upload;
	
	@Value("${folder_upload2}")
    private String folder2Upload1;
	
	public Response getUtilAttachedDocuments() {
		List<UtilAttachedDocumentsDTO> ls = utilAttachedDocumentsBusinessImpl.getAll();
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
	public Response getUtilAttachedDocumentsById(Long id) {
		UtilAttachedDocumentsDTO obj = (UtilAttachedDocumentsDTO) utilAttachedDocumentsBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateUtilAttachedDocuments(UtilAttachedDocumentsDTO obj) {
		Long id = utilAttachedDocumentsBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addUtilAttachedDocuments(UtilAttachedDocumentsDTO obj) {
		Long id = utilAttachedDocumentsBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteUtilAttachedDocuments(Long id) {
		UtilAttachedDocumentsDTO obj = (UtilAttachedDocumentsDTO) utilAttachedDocumentsBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			utilAttachedDocumentsBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getListByParentIdAndType(Long parentId)throws Exception {
		List<UtilAttachedDocumentsDTO> list = utilAttachedDocumentsBusinessImpl.getListByParentIdAndType(parentId);
		for(UtilAttachedDocumentsDTO dto : list){
			dto.setDocumentPath(UEncrypt.encryptFileUploadPath(dto.getDocumentPath()));
		}
		if (list.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(list).build();
		}
	}

	@Override
	public Response exportFileZip(List<Long> listParentId)throws Exception {
		if (listParentId.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		List<UtilAttachedDocumentsDTO> list = new ArrayList<>();
		for (int i = 0; i < listParentId.size(); i++) {
			List<UtilAttachedDocumentsDTO> 	listJoin = utilAttachedDocumentsBusinessImpl.getListByParentIdAndType(listParentId.get(i));
			for(UtilAttachedDocumentsDTO dto : listJoin){
				dto.setDocumentPath(UEncrypt.encryptFileUploadPath(dto.getDocumentPath()));
			}
			list.addAll(listJoin);
		}

		if (list.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		try {
			FileCompressor fileCompressor = new FileCompressor();
			File f = new File(folder2Upload1);
			if (!f.exists()) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			String filePath = f.getCanonicalPath();

			for (UtilAttachedDocumentsDTO childls : list) {
				String srcPath = filePath + File.separatorChar + UEncrypt.decryptFileUploadPath(childls.getDocumentPath());
				String desPath = childls.getDocumentName();
				fileCompressor.add(srcPath, desPath);
			}
			fileCompressor.setType(TYPE);
			fileCompressor.setLevel(LEVEL);
			fileCompressor.setCompressedPath(folder2Upload + File.separatorChar + "PhuongAnToChucThiCong." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("PhuongAnToChucThiCong." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;

	}

	@Override
	public Response deleteDocument(List<String> listAttachId) {
		for (int i = listAttachId.size() - 1; i >= 0; i--) {
			if (!utilAttachedDocumentsBusinessImpl.checkType(listAttachId.get(i))) {
				listAttachId.remove(i);
			}
		}
		if (listAttachId.size() > 0) {
			utilAttachedDocumentsBusinessImpl.deleteDocument(listAttachId);
			return Response.ok(Response.Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Override
	public Response getListByParentIdCA(Long parentId) throws Exception {
		List<UtilAttachedDocumentsDTO> list = utilAttachedDocumentsBusinessImpl.getListByParentIdCA(parentId);
		for(UtilAttachedDocumentsDTO dto : list){
			dto.setDocumentPath(UEncrypt.encryptFileUploadPath(dto.getDocumentPath()));
		}
		if (list.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(list).build();
		}
	}

	@Override
	public Response getListOrganizationByParentId(Long parentId) throws Exception {
		// TODO Auto-generated method stub
		List<UtilAttachedDocumentsDTO> list = utilAttachedDocumentsBusinessImpl.getListOrganizationByParentId(parentId);
		for(UtilAttachedDocumentsDTO dto : list){
			dto.setDocumentPath(UEncrypt.encryptFileUploadPath(dto.getDocumentPath()));
		}
		if (list.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(list).build();
		}
	}
}
