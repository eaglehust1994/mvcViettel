/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.erp.business.AbMaterialCompareBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.AbMaterialCompareDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class AbMaterialCompareRsServiceImpl implements AbMaterialCompareRsService {
	private static Logger LOGGER = LoggerFactory.getLogger(AbMaterialCompareRsServiceImpl.class);
	@Autowired
	AbMaterialCompareBusinessImpl abMaterialCompareBusinessImpl;

	@Value("${folder_upload}")
	private String folderUpload;

	@Value("${completionEstimateAB.attachType}")
	private Long attachType;
	
	@Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;
	
	@Override
	public Response getAbMaterialCompare() {
		List<AbMaterialCompareDTO> ls = abMaterialCompareBusinessImpl.getAll();
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
	public Response getAbMaterialCompareById(Long id) {
		AbMaterialCompareDTO obj = (AbMaterialCompareDTO) abMaterialCompareBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateAbMaterialCompare(AbMaterialCompareDTO obj) {
		Long id = abMaterialCompareBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addAbMaterialCompare(AbMaterialCompareDTO obj) throws Exception {
		AbMaterialCompareDTO dto = new AbMaterialCompareDTO();
		
	//	String documentPath = FileServiceImpl.fileUploadPath.substring(folderUpload.length()+1);
    //	obj.getUtilAttachedDocuments().setDocumentPath(documentPath);
	  //  System.out.println("update documentPath =  " + documentPath);
	 //   System.out.println("update documentName =  " + FileServiceImpl.fileUploadName);
		
//		String documentPath = Folder.getFolderSubfix(folder);
//		documentPath = documentPath + File.separatorChar;
//		dto.setPath(documentPath);
		Long id = 0l;
		AbMaterialCompareDTO isCheckData = (AbMaterialCompareDTO) abMaterialCompareBusinessImpl.getById(obj.getConstructId());
		
		if(isCheckData == null){
			try {
				id = abMaterialCompareBusinessImpl.saveTable(obj);
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
			dto.setDocumentCaId(id);
				return Response.ok(dto).build();
		}else{
        	AbMaterialCompareDTO objDetail = (AbMaterialCompareDTO)abMaterialCompareBusinessImpl.checkEstimateAB6(obj.getConstructId());
        	if(objDetail != null){
        		objDetail.setCode(objDetail.getCode());
        		objDetail.setIsActive(0l);
        		objDetail.setCreatedDate(new Date());
        		objDetail.setCreatedUserId(obj.getCreatedUserId());
        		objDetail.setADirectorId(obj.getADirectorId());
        		objDetail.setBDirectorId(obj.getBDirectorId());
        		objDetail.setAHeadConstructId(obj.getAHeadConstructId());
        		objDetail.setAHeadTechnicalId(obj.getAHeadTechnicalId());
        		objDetail.setAHeadFinanceId(obj.getAHeadFinanceId());
        		objDetail.setBHeadConstructId(obj.getBHeadConstructId());
        		objDetail.setBHeadAccountId(obj.getBHeadAccountId());
        		

//        		String documentPath = FileServiceImpl.fileUploadPath.substring(folderUpload.length()+1);
//            	obj.getUtilAttachedDocuments().setDocumentPath(documentPath);
//        	    System.out.println("update documentPath =  " + documentPath);
//        	    System.out.println("update documentName =  " + FileServiceImpl.fileUploadName);
//        	    
//            	String docPath = obj.getUtilAttachedDocuments().getDocumentPath().substring(folder.length() + 1);
//            	obj.getUtilAttachedDocuments().setDocumentPath(docPath);
//            	System.out.println("documentPath = " + docPath);
        		
        		obj.getUtilAttachedDocuments().setDocumentPath(UEncrypt.decryptFileUploadPath(obj.getUtilAttachedDocuments().getDocumentPath()));
        		
            	utilAttachedDocumentsBusinessImpl.updateUtilByParentIdAndType(obj.getUtilAttachedDocuments().getDocumentName(),
            			objDetail.getAbMaterialCompareId(),  obj.getUtilAttachedDocuments().getDocumentPath(), attachType);
        		
        		abMaterialCompareBusinessImpl.update(objDetail);
        		id = abMaterialCompareBusinessImpl.getConstrComReMapId(objDetail.getAbMaterialCompareId());
        		dto.setDocumentCaId(id);
        	}
        	    dto.setDocumentCaId(id);	
				return Response.ok(dto).build();
		}
		
		
	}

	@Override
	public Response deleteAbMaterialCompare(Long id) {
		AbMaterialCompareDTO obj = (AbMaterialCompareDTO) abMaterialCompareBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			abMaterialCompareBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response checkDataSaveForm6(Long constructId) {
		AbMaterialCompareDTO obj = (AbMaterialCompareDTO) abMaterialCompareBusinessImpl.checkEstimateAB6(constructId);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
           
            return Response.ok(obj).build();
        }
	}
}
