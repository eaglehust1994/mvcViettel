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
import com.viettel.qll.business.TblKHuyenKhoBusinessImpl;
import com.viettel.qll.dto.TblKHuyenKhoDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public class TblKHuyenKhoRsServiceImpl implements TblKHuyenKhoRsService {

	protected final Logger log = Logger.getLogger(TblKHuyenKhoRsService.class);

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
	TblKHuyenKhoBusinessImpl tblKHuyenKhoBusinessImpl;
	@Context
	HttpServletRequest request;

	@Override
	public Response doSearchKHuyenKho(TblKHuyenKhoDTO obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("zzz");
		// try{
		DataListDTO data = tblKHuyenKhoBusinessImpl.doSearchKHuyenKho(obj, request);
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(data).build();
		}
		// }catch (IllegalArgumentException e) {
		// return Response.ok().entity(Collections.singletonMap("error",
		// e.getMessage())).build();
		// }catch (Exception e) {
		// return Response.ok().entity(Collections.singletonMap("error
		// Exception", e.getMessage())).build();
		// }
	}


	@Override
	public Response importkHuyenKho(Attachment attachments, HttpServletRequest request) throws Exception {

		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
		String filePathReturn;
		String filePath;
		if (UString.isNullOrWhitespace(folderParam)) {
			folderParam = defaultSubFolderUpload;
		}
		// else {
		// if (!isFolderAllowFolderSave(folderParam)) {
		// throw new BusinessException("folder khong nam trong white list:
		// folderParam=" + folderParam);
		// }
		// }

		DataHandler dataHandler = attachments.getDataHandler();

		// get filename to be uploaded
		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
		String fileName = UFile.getFileName(multivaluedMap);

		// if (!isExtendAllowSave(fileName)) {
		// throw new BusinessException("File extension khong nam trong list duoc
		// up load, file_name:" + fileName);
		// }
		// write & upload file to server
		try (InputStream inputStream = dataHandler.getInputStream();) {
			filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
		} catch (Exception ex) {
			throw new BusinessException("Loi khi save file", ex);
		}

		try {

			try {
				String result = tblKHuyenKhoBusinessImpl.importkHuyenKho(folderUpload + filePath,request);
				if (result.isEmpty()) {
					return Response.ok().build();
				} else {
					return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
				}
			} catch (Exception e) {
				System.out.println("Lỗi :" + e);
				// TODO Auto-generated catch block
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}

		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}

	@Override
	public Response exportExcelGrid(TblKHuyenKhoDTO obj) throws Exception {

		try {
			String result = tblKHuyenKhoBusinessImpl.exportKIDonVi(obj);
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
