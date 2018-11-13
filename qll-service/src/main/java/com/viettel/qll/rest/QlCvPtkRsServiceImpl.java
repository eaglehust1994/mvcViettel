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
import com.viettel.qll.business.QlCvPtkBusinessImpl;
import com.viettel.qll.rest.QlCvPtkRsService;
import com.viettel.qll.dto.QlCvPtkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */
 
public class QlCvPtkRsServiceImpl implements QlCvPtkRsService {

	
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
	
	protected final Logger log = Logger.getLogger(QlCvPtkRsService.class);
	@Autowired
	QlCvPtkBusinessImpl qlCvPtkBusinessImpl;
	
	/**
	 * AutoComplete số hợp đồng
	 * @param obj
	 * @return
	 */
	@Override
	public Response getForAutoCompleteSHD(QlCvPtkDTO obj) {
		List<QlCvPtkDTO> ls = qlCvPtkBusinessImpl.getForAutoCompleteSHD(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response saveAddCv(QlCvPtkDTO obj) {
		long ls = qlCvPtkBusinessImpl.saveAddCv(obj);
		if (ls == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	
	@Override
	public Response doSearch(QlCvPtkDTO obj) throws Exception {
		DataListDTO data = qlCvPtkBusinessImpl.doSearch(obj);
        if (data == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(data).build();
        }
	}
	
	@Override
	public Response xuatBCTHCV( HttpServletRequest request) throws Exception {

		try {
			String result = qlCvPtkBusinessImpl.xuatBCTHCV(request);
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
	public Response updatePathFileScan(QlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = qlCvPtkBusinessImpl.updatePathFileScan(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	
	
	@Override
	public Response updatePathFileExcell(QlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = qlCvPtkBusinessImpl.updatePathFileExcell(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response updatePathFileQtk(QlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = qlCvPtkBusinessImpl.updatePathFileQtk(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response updatePathFileQtDt(QlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = qlCvPtkBusinessImpl.updatePathFileQtDt(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response deleteObj(QlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = qlCvPtkBusinessImpl.deleteObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	@Override
	public Response xuatBCTheoDauViec(QlCvPtkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = qlCvPtkBusinessImpl.xuatBCTheoDauViec(obj,request);
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
	public Response exportRowAll(QlCvPtkDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = qlCvPtkBusinessImpl.exportRowAll(obj,request);
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
				String result = qlCvPtkBusinessImpl.importFile(folderUpload + filePath,request);
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
	/** 
	 * xóa list theo điều kiện tìm kiếm 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public Response deleteListObj(QlCvPtkDTO obj) throws Exception {
		Long ids;
		try {
			ids = qlCvPtkBusinessImpl.deleteListObj(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
}
