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
import com.viettel.qll.business.TblQlCvPtkBusinessImpl;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;

import com.viettel.service.base.dto.DataListDTO;


/**
 * @author hailh10
 */
 
public class TblQlCvPtkRsServiceImpl implements TblQlCvPtkRsService {

	protected final Logger log = Logger.getLogger(TblQlCvPtkRsService.class);
	@Autowired
	TblQlCvPtkBusinessImpl tblQlCvPtkBusinessImpl;
	
	
	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;
	
	@Value("${folder_upload2}")
	private String folderUpload;
	@Context HttpServletRequest request;
	@Override
	public Response doSearch(TblQlCvPtkDTO obj) throws Exception {
		DataListDTO data = tblQlCvPtkBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	@Override
	public Response listTasks(TblQlCvPtkDTO obj) throws Exception {
		DataListDTO data = tblQlCvPtkBusinessImpl.listTasks(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}


	@Override
	public Response deleteObj(TblQlCvPtkDTO obj) throws Exception {
		// TODO Auto-generated method stub
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.deleteObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response deleteListObj(TblQlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.deleteListObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response saveAddCv(TblQlCvPtkDTO obj) {
		// TODO Auto-generated method stub
		long ls = tblQlCvPtkBusinessImpl.saveAddCv(obj);
		if (ls == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response updateStatus(TblQlCvPtkDTO obj) throws Exception {
		long data = tblQlCvPtkBusinessImpl.updateStatus(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	
	public Response updatePath(TblQlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.updatePath(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	public Response updateCV1(TblQlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.updateCV1(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	public Response updateCV2(TblQlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.updateCV2(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	public Response updateCV3(TblQlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.updateCV3(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	public Response updateCV4(TblQlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.updateCV4(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	public Response updateCV5(TblQlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = tblQlCvPtkBusinessImpl.updateCV5(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	@Override
	public Response updateCvPtk(TblQlCvPtkDTO obj) throws Exception {
		long data = tblQlCvPtkBusinessImpl.updateCvPtk(obj);
        if (data == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	@Override
	public Response exportAllCV(TblQlCvPtkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblQlCvPtkBusinessImpl.exportAllCV(obj,request);
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
	public Response reportForQTK(TblQlCvPtkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblQlCvPtkBusinessImpl.reportForQTK(obj,request);
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
	public Response reportTotal( TblQlCvPtkDTO obj,HttpServletRequest request) throws Exception {

		try {
			String result = tblQlCvPtkBusinessImpl.reportTotal(obj,request);
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
	//tu dong so hop dong cdt
	@Override
	public Response getForAutoCompleteSHD(TblQlCvPtkDTO obj) {
		List<TblQlCvPtkDTO> ls = tblQlCvPtkBusinessImpl.getForAutoCompleteSHD(obj);
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
				String result = tblQlCvPtkBusinessImpl.importFile(folderUpload + filePath,request);
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
	public Response importFileCV(Attachment attachments, HttpServletRequest request) throws Exception {
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
				String result = tblQlCvPtkBusinessImpl.importUpdateCV(folderUpload + filePath,request);
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
	public Response getChart(TblQlCvPtkDTO obj) throws Exception {
		return Response.ok(tblQlCvPtkBusinessImpl.getChart(obj,request)).build();
	}
	
}
