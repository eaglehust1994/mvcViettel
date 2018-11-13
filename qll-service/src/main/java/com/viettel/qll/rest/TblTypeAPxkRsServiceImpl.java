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
import com.viettel.qll.business.TblTypeAPxkBusinessImpl;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.qll.dto.TblDlHaTangTramDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class TblTypeAPxkRsServiceImpl implements TblTypeAPxkRsService {

	protected final Logger log = Logger.getLogger(TblTypeAPxkRsService.class);
	@Autowired
	TblTypeAPxkBusinessImpl tblTypeAPxkBusinessImpl;
	
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
	
	
	@Override
	public Response doSearchPXK(TblTypeAPxkDTO obj) {
		DataListDTO data = tblTypeAPxkBusinessImpl.doSearchPXK(obj);
		return Response.ok(data).build();
	}
	

	@Override
	public Response getDeptForAutocomplete(TblTypeAPxkDTO obj) {
		List<TblTypeAPxkDTO> ls = tblTypeAPxkBusinessImpl.getForAutoCompleteTramNhan(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response getForAutoCompleteHDXL(TblTypeAPxkDTO obj) {
		List<TblTypeAPxkDTO> ls = tblTypeAPxkBusinessImpl.getForAutoCompleteHDXL(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response updatePathImg(TblTypeAPxkDTO obj) throws Exception {
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		Long ids;
		try {
			ids = tblTypeAPxkBusinessImpl.updatePathImg(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	
	@Override
	public Response checkMaHdxl(TblTypeAPxkDTO obj) {
		List<TblTypeAPxkDTO> data = tblTypeAPxkBusinessImpl.checkMaHdxl(obj);
		if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response importTypePXK(Attachment attachments, HttpServletRequest request) throws Exception {
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
				String result = tblTypeAPxkBusinessImpl.importTypePXK(folderUpload + filePath,request);
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
	public Response exportExcelGrid(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblTypeAPxkBusinessImpl.exportExcelGrid(obj,request);
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
	public Response exportTHDoiSoatVatTu(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblTypeAPxkBusinessImpl.exportTHDoiSoatVatTu(obj,request);
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
