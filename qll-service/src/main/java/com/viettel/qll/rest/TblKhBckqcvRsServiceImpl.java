package com.viettel.qll.rest;


import java.io.InputStream;
import java.util.Collections;
import java.util.List;

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
import com.viettel.qll.business.TblKhBckqcvBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblKhBckqcvDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;




/**
 * @author hailh10
 */
 
public class TblKhBckqcvRsServiceImpl implements TblKhBckqcvRsService {

	protected final Logger log = Logger.getLogger(TblKhBckqcvRsService.class);
	@Autowired
	TblKhBckqcvBusinessImpl tblKhBckqcvBusinessImpl;
	
	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;
	@Value("${folder_upload2}")
	private String folderUpload;
	@Context HttpServletRequest request;
	@Override
	public Response doSearch(TblKhBckqcvDTO obj) throws Exception {
		DataListDTO data = tblKhBckqcvBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response deleteObj(TblKhBckqcvDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblKhBckqcvBusinessImpl.deleteObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response deleteListObj(TblKhBckqcvDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblKhBckqcvBusinessImpl.deleteListObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response updateKhKd(TblKhBckqcvDTO obj) throws Exception {
		long data = tblKhBckqcvBusinessImpl.updateKhKd(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	@Override
	public Response getForAutoCompleteMonth(TblKhBckqcvDTO obj) {
		List<TblKhBckqcvDTO> ls = tblKhBckqcvBusinessImpl.getForAutoCompleteMonth(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response getForAutoCompleteMaDv(TblKhBckqcvDTO obj) {
		List<TblKhBckqcvDTO> ls = tblKhBckqcvBusinessImpl.getForAutoCompleteMaDv(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	@Override
	public Response importFile(Attachment attachments, HttpServletRequest request) throws Exception {
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
				String result = tblKhBckqcvBusinessImpl.importFile(folderUpload + filePath,request);
				if (result.isEmpty()) {
					return Response.ok().build();
				} else {
					return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
				}
			} catch (Exception e) {
				System.out.println("Lỗi :"+e);
				// TODO Auto-generated catch block
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}

		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	@Override
	public Response getChartDeptDay(TblKhBckqcvDTO obj) throws Exception {
		return Response.ok(tblKhBckqcvBusinessImpl.getChartDeptDay(obj,request)).build();
	}
	
	@Override
	public Response getChartDeptMonth(TblKhBckqcvDTO obj) throws Exception {
		return Response.ok(tblKhBckqcvBusinessImpl.getChartDeptMonth(obj,request)).build();
	}
	
	@Override
	public Response getChartMonth(TblKhBckqcvDTO obj) throws Exception {
		return Response.ok(tblKhBckqcvBusinessImpl.getChartMonth(obj,request)).build();
	}
}
