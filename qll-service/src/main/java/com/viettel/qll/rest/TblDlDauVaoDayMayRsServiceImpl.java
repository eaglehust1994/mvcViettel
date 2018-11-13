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
import com.viettel.qll.business.TblDlDauVaoDayMayBusinessImpl;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class TblDlDauVaoDayMayRsServiceImpl implements TblDlDauVaoDayMayRsService {

	@Context HttpServletRequest request;
	protected final Logger log = Logger.getLogger(TblDlDauVaoDayMayRsService.class);
	@Value("${folder_upload2}")
	private String folderUpload;

	@Value("${folder_upload}")
	private String folderTemp;

	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;

	@Value("${allow.file.ext}")
	private String allowFileExt;
	@Value("${allow.folder.dir}")
	private String allowFolderDir;
	
	@Autowired
	TblDlDauVaoDayMayBusinessImpl tblDlDauVaoDayMayBusinessImpl;
	@Override
	public Response getAllDulieuduytri() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Response doSearch(TblDlDauVaoDayMayDTO obj) throws Exception {
		DataListDTO data = tblDlDauVaoDayMayBusinessImpl.doSearch(obj,request);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
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
			
			try {
				String result = tblDlDauVaoDayMayBusinessImpl.importFile(folderUpload + filePath,request);
				if(result.isEmpty()){
					return Response.ok().build();
				}else {
					return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
				e.getMessage();
				return Response.ok().entity(Collections.singletonMap("error", "Lỗi")).build();
			}

		} catch (IllegalArgumentException e) {
			//return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			e.getMessage();
			return Response.ok().entity(Collections.singletonMap("error", "Lỗi Ill")).build();
		}
	}
	@Override
	public Response exportExcelGrid(TblDlDauVaoDayMayDTO obj) throws Exception {
		try {
			String result = tblDlDauVaoDayMayBusinessImpl.exportExcelGrid(obj);
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
	@Override
	public Response downloadImportTemplate() throws Exception {
		try {
			String result = tblDlDauVaoDayMayBusinessImpl.downloadImportTemplate();
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
