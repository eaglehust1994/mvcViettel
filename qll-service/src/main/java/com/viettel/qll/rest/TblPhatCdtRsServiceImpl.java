package com.viettel.qll.rest;

import java.io.InputStream;
import java.util.Collections;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;
import com.viettel.qll.business.TblPhatCdtBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblPhatCdtDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class TblPhatCdtRsServiceImpl implements TblPhatCdtRsService {

	protected final Logger log = Logger.getLogger(TblPhatCdtRsService.class);
	@Value("${folder_upload2}")
	private String folderUpload;

	@Value("${folder_upload}")
	private String folderTemp;
	
	@Context HttpServletRequest request;
	@Autowired
	TblPhatCdtBusinessImpl tblPhatCdtBusinessImpl;
	@Override
	public Response doSearchPhatCDT(TblPhatCdtDTO obj) {
		// TODO Auto-generated method stub
		DataListDTO data = tblPhatCdtBusinessImpl.doSearchPhatCDT(obj);
		return Response.ok(data).build();
	}
	@Override
	public Response importFile(Attachment attachments, HttpServletRequest request) throws Exception {

		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
		String filePathReturn;
		String filePath;

		DataHandler dataHandler = attachments.getDataHandler();

		// get filename to be uploaded
		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
		String fileName = UFile.getFileName(multivaluedMap);

		// write & upload file to server
		try (InputStream inputStream = dataHandler.getInputStream();) {
			filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
		} catch (Exception ex) {
			throw new BusinessException("Loi khi save file !!!", ex);
		}
	
			
			try {
				String result = "";
				result = tblPhatCdtBusinessImpl.importFile(folderUpload + filePath,request);
				if(result.isEmpty()){
					return Response.ok().build();
				}else {
					return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
				log.error(e.getMessage(), e);
				return Response.ok().entity(Collections.singletonMap("error", "Lỗi")).build();
			}
	}
	@Override
	public Response exportExcelGrid(TblPhatCdtDTO obj) throws Exception {
		try {
			String result = tblPhatCdtBusinessImpl.exportExcelGrid(obj);
			if (result.isEmpty()) {
				return Response.ok().build();
			} else {
				return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	@Override
	public Response downloadImportTemplate() throws Exception {
		try {
			String result = tblPhatCdtBusinessImpl.downloadImportTemplate();
			if (result.isEmpty()) {
				return Response.ok().build();
			} else {
				return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}
