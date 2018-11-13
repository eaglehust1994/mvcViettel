package com.viettel.qll.rest;

import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.viettel.qll.bo.TblNhanVienBO;
import com.viettel.qll.business.TblDanhMucBusinessImpl;
import com.viettel.qll.business.TblNhanVienBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.erp.constant.ApplicationConstants;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;
// import com.viettel.service.base.dto.ActionListDTO;
// import com.viettel.erp.utils.ExportExcel;
// import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class TblNhanVienRsServiceImpl implements TblNhanVienRsService {

	protected final Logger log = Logger.getLogger(TblNhanVienRsService.class);
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
	
	@Context 
	HttpServletRequest request;
	@Autowired
	TblNhanVienBusinessImpl tblNhanVienBusinessImpl;
	
	@Autowired
	TblDanhMucBusinessImpl danhMucBusinessImpl;
	
	
	@Override
	public Response getAllNhanVien() throws Exception {
		DataListDTO data= (DataListDTO) tblNhanVienBusinessImpl.getAllNhanVien();
		return Response.ok(data).build();
	}
	
	@Override
	public Response doSearchNhanVien(TblNhanVienDTO obj) {
		DataListDTO data = tblNhanVienBusinessImpl.doSearchNhanVien(obj);
		return Response.ok(data).build();
	}
	
	

	@Override
	public Response importNhanVien(Attachment attachments, HttpServletRequest request) throws Exception {

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
				String result = tblNhanVienBusinessImpl.importNhanVien(folderUpload + filePath,request);
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
	public Response exportExcelGrid(TblNhanVienDTO obj) throws Exception {

		try {
			String result = tblNhanVienBusinessImpl.exportExcelGrid(obj);
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
	public Response getForAutoCompleteNV(TblNhanVienDTO obj) {
		List<TblNhanVienDTO> ls = tblNhanVienBusinessImpl.getForAutoCompleteNV(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

}
