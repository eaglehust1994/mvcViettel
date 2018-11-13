package com.viettel.qll.rest;

import java.util.List;
import java.io.File;
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
import com.viettel.qll.bo.TblQltsThucXuatTheoKyBO;
import com.viettel.qll.business.TblQltsThucXuatTheoKyBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.erp.constant.ApplicationConstants;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;

import java.io.InputStream;

import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
// import com.viettel.erp.utils.ExportExcel;
// import com.viettel.erp.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class TblQltsThucXuatTheoKyRsServiceImpl implements TblQltsThucXuatTheoKyRsService {

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
	
	@Context HttpServletRequest request;
	protected final Logger log = Logger.getLogger(TblQltsThucXuatTheoKyRsService.class);
	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl tblQltsThucXuatTheoKyBusinessImpl;
	
	@Override
	public Response getAllBctxtk() throws Exception {
		DataListDTO data= (DataListDTO) tblQltsThucXuatTheoKyBusinessImpl.getAllBctxtk();
		return Response.ok(data).build();
	}

	@Override
	public Response doSearchBctxtk(TblQltsThucXuatTheoKyDTO obj) throws Exception {
		DataListDTO data = tblQltsThucXuatTheoKyBusinessImpl.doSearch(obj,request);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response doSearchByPXK(TblQltsThucXuatTheoKyDTO obj) throws Exception {
		DataListDTO data = tblQltsThucXuatTheoKyBusinessImpl.doSearchByPXK(obj,request);
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
//		if (UString.isNullOrWhitespace(folderParam)) {
//			folderParam = defaultSubFolderUpload;
//		} else {
//			if (!isFolderAllowFolderSave(folderParam)) {
//				throw new BusinessException("Folder khong nam trong white list: folderParam=" + folderParam);
//			}
//		}

		DataHandler dataHandler = attachments.getDataHandler();

		// get filename to be uploaded
		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
		String fileName = UFile.getFileName(multivaluedMap);

//		if (!isExtendAllowSave(fileName)) {
//			throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
//		}
		// write & upload file to server
		try (InputStream inputStream = dataHandler.getInputStream();) {
			filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
		} catch (Exception ex) {
			throw new BusinessException("Loi khi save file !!!", ex);
		}
	
			
		try {
			
			try {
				String result = tblQltsThucXuatTheoKyBusinessImpl.importFile(folderUpload + filePath,request);
				if(result.isEmpty()){
					return Response.ok().build();
				}else {
					return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
				}
				//return Response.ok(tblQltsThucXuatTheoKyBusinessImpl.importFile(folderUpload + filePath)).build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}

		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
	@Override
	public Response updateChiNhanh(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception {
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		Long ids;
		try {
			ids = tblQltsThucXuatTheoKyBusinessImpl.updateChiNhanh(obj,request);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response updateNhanVien(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception {
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		Long ids;
		try {
			ids = tblQltsThucXuatTheoKyBusinessImpl.updateNhanVien(obj,request);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response updateSLByNV(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception {
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		Long ids;
		try {
			ids = tblQltsThucXuatTheoKyBusinessImpl.updateSLByNV(obj,request);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	
	
	@Override
	public Response exportExcelTongHopTheoNV(TblQltsThucXuatTheoKyDTO kyDTO, HttpServletRequest request) throws Exception {

		try {
			String result = tblQltsThucXuatTheoKyBusinessImpl.exportExcelTongHopTheoNV(kyDTO,request);
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
	public Response exportExcelTongHopTheoDV(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblQltsThucXuatTheoKyBusinessImpl.exportExcelTongHopTheoDV(obj,request);
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
