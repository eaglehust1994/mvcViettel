package com.viettel.qll.rest;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
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
import com.viettel.qll.business.KqHdTkBusinessImpl;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class KqHdTkRsServiceImpl implements KqHdTkRsService {

	protected final Logger log = Logger.getLogger(KqHdTkRsService.class);
	@Autowired
	KqHdTkBusinessImpl kqHdTkBusinessImpl;
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
	public Response doSearch(KqHdTkDTO obj) throws Exception {
		DataListDTO data = kqHdTkBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response insertHd(KqHdTkDTO obj) throws Exception {
		long data = kqHdTkBusinessImpl.insertHD(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response updatePath(KqHdTkDTO obj) throws Exception {
		Long ids;
		try {
			ids = kqHdTkBusinessImpl.updatePath(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	

	@Override
	public Response updateHdTd(KqHdTkDTO obj) throws Exception {
		long data = kqHdTkBusinessImpl.updateHDTd(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response updateHdPopSubmit(KqHdTkDTO obj) throws Exception {
		long data = kqHdTkBusinessImpl.updateHdPopSubmit(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	@Override
	public Response deleteObj(KqHdTkDTO obj) throws Exception {
		Long ids;
		try {
			ids = kqHdTkBusinessImpl.deleteObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response deleteListObj(KqHdTkDTO obj) throws Exception {
		Long ids;
		try {
			ids = kqHdTkBusinessImpl.deleteListObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	
	@Override
	public Response exportExcelGrid(KqHdTkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = kqHdTkBusinessImpl.exportTHTheoHD(obj,request);
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
	public Response xuatBCLuong(KqHdTkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = kqHdTkBusinessImpl.xuatLuongTheoSoHdtc(obj,request);
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
	public Response xuatBCTH(KqHdTkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = kqHdTkBusinessImpl.xuatBCTH(obj,request);
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
	public Response exportAllHD(KqHdTkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = kqHdTkBusinessImpl.exportAllHD(obj,request);
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
	public Response getForAutoCompleteHD(KqHdTkDTO obj) {
		List<KqHdTkDTO> ls = kqHdTkBusinessImpl.getForAutoCompleteHD(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	@Override
	public Response getForAutoCompleteKHTC(KqHdTkDTO obj) {
		List<KqHdTkDTO> ls = kqHdTkBusinessImpl.getForAutoCompleteKHTC(obj);
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
				String result = kqHdTkBusinessImpl.importFile(folderUpload + filePath,request);
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
	public Response importFileTD(Attachment attachments, HttpServletRequest request) throws Exception {
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
				String result = kqHdTkBusinessImpl.importFileTD(folderUpload + filePath,request);
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
	
}
